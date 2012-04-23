

#include "urn_jaus_jss_mobility_LocalWaypointListDriver_1_0/LocalWaypointListDriver_ReceiveFSM.h"
#include "urn_jts_examples_SkidSteerVehicleSim_1_0/Messages/SetWrenchEffort.h"
#include "SharedData.h"
#include <math.h>
#define FIND_MAX(a,b) ((a < b) ? b : a)
#define FIND_MIN(a,b) ((a < b) ? a : b)


const double PI = 3.14159265358979323846;


using namespace JTS;

namespace urn_jaus_jss_mobility_LocalWaypointListDriver_1_0
{

static double activeWaypoint = 0;
static double speed = 0;
static bool running = false;
static urn_jaus_jss_mobility_LocalWaypointListDriver_1_0::SetElement::Body::SetElementSeq::ElementList waypointList;
static SetElement::Body::SetElementSeq::ElementList::ElementRec* findElement(unsigned short uid)
{
	for (int i=0; waypointList.getNumberOfElements(); i++)
	{
		if (waypointList.getElement(i)->getElementUID() == uid)
			return waypointList.getElement(i);
	}

	return NULL;
}
static void computeNextWrenchEffort(void* jausRouter)
{
	/// Start a timer to regularly do this update.
	static DeVivo::Junior::JrTimer* timer = new DeVivo::Junior::JrTimer(&computeNextWrenchEffort, jausRouter, 10);
	running = true;

	// Local vars...
	double lin_x = 0, rot_z = 0;
	ReportLocalWaypoint target;
	ReportLocalWaypoint::Body::LocalWaypointRec* targetData;

	// If we have an active waypoint, check to see if it's satisfied
	if (activeWaypoint != 0)
	{
		// First get the current target waypoint.
		target.decode(findElement(activeWaypoint)->getElementData()->getData());
		targetData = target.getBody()->getLocalWaypointRec();

		// Now find the close enough distance.  If not explicitly specified, use some default
		double closeEnoughTol = (targetData->isWaypointToleranceValid() ? targetData->getWaypointTolerance() : 5.0);
		if (closeEnoughTol < 1.0) closeEnoughTol = 1.0; // prevent hunting

		// Check to see if we are within the close enough tolerance in all specified dimensions
		if ((fabs(targetData->getX() - SharedData::get()->getX()) < closeEnoughTol) &&
		    (fabs(targetData->getY() - SharedData::get()->getY()) < closeEnoughTol)) 
		{
			// within tolerance.  Move to the next waypoint.
			activeWaypoint = findElement(activeWaypoint)->getNextUID();
		}
	}

	// If we *still* have an active waypoint, send a wrench command to head in the right direction
	if (activeWaypoint != 0)
	{
		// First get the current target waypoint.
		target.decode(findElement(activeWaypoint)->getElementData()->getData());
		targetData = target.getBody()->getLocalWaypointRec();

		// Figure out the heading and distance required between current pose and target pose
		double targetHeading = atan2(targetData->getY() - SharedData::get()->getY(),
			                         targetData->getX() - SharedData::get()->getX());
		double distance = sqrt((targetData->getY() - SharedData::get()->getY())*(targetData->getY() - SharedData::get()->getY())+
			                   (targetData->getX() - SharedData::get()->getX())*(targetData->getX() - SharedData::get()->getX()));

		fprintf(stdout, "Target heading: %g, Distance=%g, current heading=%g   \r", targetHeading, distance, SharedData::get()->getYaw());
		fflush(stdout);

		// Take the short way to the proper heading
		double rotDiff = targetHeading - SharedData::get()->getYaw();
		while (rotDiff > PI) rotDiff -= 2*PI;
		while (rotDiff <= -PI) rotDiff += 2*PI;

		// We take a trivially easy approach to waypoint following.  Turn until we're lined up
		// to an arbitrary tolerance, then move forward.  
		// We never turn and move forward at the same time.
		if (fabs(rotDiff) > .05)
		{
			rot_z = FIND_MAX(FIND_MIN((-1.0*rotDiff)*20.0, 100.0), -100.0); // scaling is arbitrary
			lin_x = 0;
		}
		else
		{
			// linear movement.  Based off speed and distance to go, but never more than 100% effort.
			lin_x = FIND_MAX(FIND_MIN(distance * speed/100.0, 100.0), -100.0); // scaling is arbitrary
			rot_z = 0;
		}
	}

	// Send the waypoint message to the vehicle simulator
	// (which in this case resides on our local component)
	urn_jts_examples_SkidSteerVehicleSim_1_0::SetWrenchEffort wrench_msg;
	wrench_msg.getBody()->getWrenchEffortRec()->setPropulsiveLinearEffortX(lin_x);
	wrench_msg.getBody()->getWrenchEffortRec()->setPropulsiveRotationalEffortZ(rot_z);
	
	// Since we're outside the StateMachine class, we don't have access to the sendJausMessage
	// function and have to form the call manually...
	unsigned int bufsize = wrench_msg.getSize();
	unsigned char* buffer = new unsigned char[bufsize];
	wrench_msg.encode(buffer);
	Send_1_0 response;
	response.getBody()->getSendRec()->getMessagePayload()->set(bufsize, buffer);
	response.getBody()->getSendRec()->setDestSubsystemID(((JausRouter*) jausRouter)->getJausAddress()->getSubsystemID());
	response.getBody()->getSendRec()->setDestNodeID(((JausRouter*) jausRouter)->getJausAddress()->getNodeID());
	response.getBody()->getSendRec()->setDestComponentID(((JausRouter*) jausRouter)->getJausAddress()->getComponentID());
	((JausRouter*) jausRouter)->sendMessage( &response );
	delete[] buffer;

	// Restart the timer so we send another update in 10 ms
	timer->start();
}


LocalWaypointListDriver_ReceiveFSM::LocalWaypointListDriver_ReceiveFSM(urn_jaus_jss_core_Transport_1_0::Transport_ReceiveFSM* pTransport_ReceiveFSM, urn_jaus_jss_core_Events_1_0::Events_ReceiveFSM* pEvents_ReceiveFSM, urn_jaus_jss_core_AccessControl_1_0::AccessControl_ReceiveFSM* pAccessControl_ReceiveFSM, urn_jaus_jss_core_Management_1_0::Management_ReceiveFSM* pManagement_ReceiveFSM, urn_jaus_jss_mobility_ListManager_1_0::ListManager_ReceiveFSM* pListManager_ReceiveFSM)
{

	/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
	 */
	context = new LocalWaypointListDriver_ReceiveFSMContext(*this);

	this->pTransport_ReceiveFSM = pTransport_ReceiveFSM;
	this->pEvents_ReceiveFSM = pEvents_ReceiveFSM;
	this->pAccessControl_ReceiveFSM = pAccessControl_ReceiveFSM;
	this->pManagement_ReceiveFSM = pManagement_ReceiveFSM;
	this->pListManager_ReceiveFSM = pListManager_ReceiveFSM;
}



LocalWaypointListDriver_ReceiveFSM::~LocalWaypointListDriver_ReceiveFSM() 
{
	delete context;
}

void LocalWaypointListDriver_ReceiveFSM::setupNotifications()
{
	pListManager_ReceiveFSM->registerNotification("Receiving_Ready_NotControlled_StateA_Standby", ieHandler, "InternalStateChange_To_LocalWaypointListDriver_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Standby", "ListManager_ReceiveFSM");
	pListManager_ReceiveFSM->registerNotification("Receiving_Ready_NotControlled_StateA_Init", ieHandler, "InternalStateChange_To_LocalWaypointListDriver_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Init", "ListManager_ReceiveFSM");
	pListManager_ReceiveFSM->registerNotification("Receiving_Ready_NotControlled_StateA_Failure", ieHandler, "InternalStateChange_To_LocalWaypointListDriver_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Failure", "ListManager_ReceiveFSM");
	pListManager_ReceiveFSM->registerNotification("Receiving_Ready_NotControlled_StateA_Shutdown", ieHandler, "InternalStateChange_To_LocalWaypointListDriver_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Shutdown", "ListManager_ReceiveFSM");
	pListManager_ReceiveFSM->registerNotification("Receiving_Ready_NotControlled_StateA_Emergency", ieHandler, "InternalStateChange_To_LocalWaypointListDriver_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Emergency", "ListManager_ReceiveFSM");
	pListManager_ReceiveFSM->registerNotification("Receiving_Ready_NotControlled_StateA", ieHandler, "InternalStateChange_To_LocalWaypointListDriver_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Init", "ListManager_ReceiveFSM");
	pListManager_ReceiveFSM->registerNotification("Receiving_Ready_NotControlled", ieHandler, "InternalStateChange_To_LocalWaypointListDriver_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Init", "ListManager_ReceiveFSM");
	pListManager_ReceiveFSM->registerNotification("Receiving_Ready_Controlled_StateB_Standby", ieHandler, "InternalStateChange_To_LocalWaypointListDriver_ReceiveFSM_Receiving_Ready_Controlled_StateB_Standby", "ListManager_ReceiveFSM");
	pListManager_ReceiveFSM->registerNotification("Receiving_Ready_Controlled_StateB_Ready", ieHandler, "InternalStateChange_To_LocalWaypointListDriver_ReceiveFSM_Receiving_Ready_Controlled_StateB_Ready", "ListManager_ReceiveFSM");
	pListManager_ReceiveFSM->registerNotification("Receiving_Ready_Controlled_StateB_Emergency", ieHandler, "InternalStateChange_To_LocalWaypointListDriver_ReceiveFSM_Receiving_Ready_Controlled_StateB_Emergency", "ListManager_ReceiveFSM");
	pListManager_ReceiveFSM->registerNotification("Receiving_Ready_Controlled_StateB", ieHandler, "InternalStateChange_To_LocalWaypointListDriver_ReceiveFSM_Receiving_Ready_Controlled_StateB_Standby", "ListManager_ReceiveFSM");
	pListManager_ReceiveFSM->registerNotification("Receiving_Ready_Controlled", ieHandler, "InternalStateChange_To_LocalWaypointListDriver_ReceiveFSM_Receiving_Ready_Controlled_StateB_Standby", "ListManager_ReceiveFSM");
	pListManager_ReceiveFSM->registerNotification("Receiving_Ready", ieHandler, "InternalStateChange_To_LocalWaypointListDriver_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Init", "ListManager_ReceiveFSM");
	pListManager_ReceiveFSM->registerNotification("Receiving", ieHandler, "InternalStateChange_To_LocalWaypointListDriver_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Init", "ListManager_ReceiveFSM");
	registerNotification("Receiving_Ready_NotControlled_StateA_Standby", pListManager_ReceiveFSM->getHandler(), "InternalStateChange_To_ListManager_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Standby", "LocalWaypointListDriver_ReceiveFSM");
	registerNotification("Receiving_Ready_NotControlled_StateA_Init", pListManager_ReceiveFSM->getHandler(), "InternalStateChange_To_ListManager_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Init", "LocalWaypointListDriver_ReceiveFSM");
	registerNotification("Receiving_Ready_NotControlled_StateA_Failure", pListManager_ReceiveFSM->getHandler(), "InternalStateChange_To_ListManager_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Failure", "LocalWaypointListDriver_ReceiveFSM");
	registerNotification("Receiving_Ready_NotControlled_StateA_Shutdown", pListManager_ReceiveFSM->getHandler(), "InternalStateChange_To_ListManager_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Shutdown", "LocalWaypointListDriver_ReceiveFSM");
	registerNotification("Receiving_Ready_NotControlled_StateA_Emergency", pListManager_ReceiveFSM->getHandler(), "InternalStateChange_To_ListManager_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Emergency", "LocalWaypointListDriver_ReceiveFSM");
	registerNotification("Receiving_Ready_NotControlled_StateA", pListManager_ReceiveFSM->getHandler(), "InternalStateChange_To_ListManager_ReceiveFSM_Receiving_Ready_NotControlled_StateA", "LocalWaypointListDriver_ReceiveFSM");
	registerNotification("Receiving_Ready_NotControlled", pListManager_ReceiveFSM->getHandler(), "InternalStateChange_To_ListManager_ReceiveFSM_Receiving_Ready_NotControlled", "LocalWaypointListDriver_ReceiveFSM");
	registerNotification("Receiving_Ready_Controlled_StateB_Standby", pListManager_ReceiveFSM->getHandler(), "InternalStateChange_To_ListManager_ReceiveFSM_Receiving_Ready_Controlled_StateB_Standby", "LocalWaypointListDriver_ReceiveFSM");
	registerNotification("Receiving_Ready_Controlled_StateB_Ready", pListManager_ReceiveFSM->getHandler(), "InternalStateChange_To_ListManager_ReceiveFSM_Receiving_Ready_Controlled_StateB_Ready", "LocalWaypointListDriver_ReceiveFSM");
	registerNotification("Receiving_Ready_Controlled_StateB_Emergency", pListManager_ReceiveFSM->getHandler(), "InternalStateChange_To_ListManager_ReceiveFSM_Receiving_Ready_Controlled_StateB_Emergency", "LocalWaypointListDriver_ReceiveFSM");
	registerNotification("Receiving_Ready_Controlled_StateB", pListManager_ReceiveFSM->getHandler(), "InternalStateChange_To_ListManager_ReceiveFSM_Receiving_Ready_Controlled_StateB", "LocalWaypointListDriver_ReceiveFSM");
	registerNotification("Receiving_Ready_Controlled", pListManager_ReceiveFSM->getHandler(), "InternalStateChange_To_ListManager_ReceiveFSM_Receiving_Ready_Controlled", "LocalWaypointListDriver_ReceiveFSM");
	registerNotification("Receiving_Ready", pListManager_ReceiveFSM->getHandler(), "InternalStateChange_To_ListManager_ReceiveFSM_Receiving_Ready", "LocalWaypointListDriver_ReceiveFSM");
	registerNotification("Receiving", pListManager_ReceiveFSM->getHandler(), "InternalStateChange_To_ListManager_ReceiveFSM_Receiving", "LocalWaypointListDriver_ReceiveFSM");

}

void LocalWaypointListDriver_ReceiveFSM::SendAction(std::string arg0, Receive::Body::ReceiveRec transportData)
{
	// Extract the sender information
	JausAddress sender(transportData.getSrcSubsystemID(),
					   transportData.getSrcNodeID(),
					   transportData.getSrcComponentID());

	if (arg0 == "ReportActiveElement")
	{
		ReportActiveElement active_element_msg;
		active_element_msg.getBody()->getActiveElementRec()->setElementUID(activeWaypoint);
		sendJausMessage( active_element_msg, sender );
	}
	else if (arg0 == "ConfirmElementRequest")
	{
		ConfirmElementRequest confirm_element_msg;
		// this should actually be populated with the request ID
		sendJausMessage( confirm_element_msg, sender );
	}
	else if (arg0 == "ReportLocalWaypoint")
	{
		// Get the current waypoint if active, otherwise first in list
		ReportLocalWaypoint waypoint;
		waypoint.decode(findElement((activeWaypoint == 0) ? 1 : activeWaypoint)->getElementData()->getData());
		ReportLocalWaypoint report_waypoint_msg;
		report_waypoint_msg.getBody()->getLocalWaypointRec()->setX(waypoint.getBody()->getLocalWaypointRec()->getX());
		report_waypoint_msg.getBody()->getLocalWaypointRec()->setY(waypoint.getBody()->getLocalWaypointRec()->getY());
		sendJausMessage( report_waypoint_msg, sender );
	}
}

void LocalWaypointListDriver_ReceiveFSM::executeWaypointListAction(ExecuteList msg)
{
	// Set the active element and the speed
	printf("Received execute command...\n");
	speed = msg.getBody()->getExecuteListRec()->getSpeed();
	if (msg.getBody()->getExecuteListRec()->isElementUIDValid())
	    activeWaypoint = msg.getBody()->getExecuteListRec()->isElementUIDValid() ?      // if the starting element is specified
		           msg.getBody()->getExecuteListRec()->getElementUID() : //use it.
				   waypointList.getElement(0)->getElementUID();                         // otherwise use first in the list.
		
	// If the processing loop isn't running, kick it off.
	if (!running) computeNextWrenchEffort(jausRouter);

}

void LocalWaypointListDriver_ReceiveFSM::modifyTravelSpeedAction(ExecuteList msg)
{
    printf("Received modify speed command...\n");
	speed = msg.getBody()->getExecuteListRec()->getSpeed();
}

void LocalWaypointListDriver_ReceiveFSM::resetTravelSpeedAction()
{
	/// Insert User Code HERE
}

void LocalWaypointListDriver_ReceiveFSM::setElementAction(SetElement msg)
{
	printf("Receiving waypoint list...\n");
	waypointList = *msg.getBody()->getSetElementSeq()->getElementList();
}



bool LocalWaypointListDriver_ReceiveFSM::elementExists(ExecuteList msg)
{
	// if an element UID is specified, it must exist in the list.
	if (msg.getBody()->getExecuteListRec()->isElementUIDValid())
	{
		if (findElement(msg.getBody()->getExecuteListRec()->getElementUID()) != NULL)
			return true; // element found
	}
	else
		return true; // element not specified.

	// getting here implies we didn't find a match
	return false;
}
bool LocalWaypointListDriver_ReceiveFSM::elementSpecified(ExecuteList msg)
{
	return msg.getBody()->getExecuteListRec()->isElementUIDValid();
}
bool LocalWaypointListDriver_ReceiveFSM::isControllingClient(Receive::Body::ReceiveRec transportData)
{
	//// By default, inherited guards call the parent function.
	//// This can be replaced or modified as needed.
	return pAccessControl_ReceiveFSM->isControllingClient(transportData );
}
bool LocalWaypointListDriver_ReceiveFSM::isElementSupported(SetElement msg)
{
	// Make sure the payload contains only Set Local Waypoint messages (based on the message id)
	for (int i=0; i < msg.getBody()->getSetElementSeq()->getElementList()->getNumberOfElements(); i++)
	{
		// cast the first two bytes of the payload data to an unsigned short, and compare to the 
		// Set Local Waypoint message ID
		if (*((unsigned short*) msg.getBody()->getSetElementSeq()->getElementList()->getElement(i)->getElementData()->getData()) != 0x040D)
		{
			printf("Received element payload that is not a Set Local Waypoint message!\n");
			return false;
		}
	}

	// getting here implies success
	return true;
}
bool LocalWaypointListDriver_ReceiveFSM::isValidElementRequest(SetElement msg)
{
	// need more debug here to make sure the incoming message meets appropriate constraints...
	return true;
}
bool LocalWaypointListDriver_ReceiveFSM::waypointExists(QueryLocalWaypoint msg)
{
	// placeholder...
	return (waypointList.getNumberOfElements() != 0);
}


};
