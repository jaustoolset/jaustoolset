

#include "urn_jts_examples_SkidSteerVehicleSim_1_0/SkidSteerVehicleSim_ReceiveFSM.h"
#include "SharedData.h"
#include <math.h>


const double DeltaTime = 0.02;
const double PI = 3.14159265358979323846;


using namespace JTS;

namespace urn_jts_examples_SkidSteerVehicleSim_1_0
{

static double currentLinX = 0;
static double currentRotZ = 0;


SkidSteerVehicleSim_ReceiveFSM::SkidSteerVehicleSim_ReceiveFSM(urn_jaus_jss_core_Transport_1_0::Transport_ReceiveFSM* pTransport_ReceiveFSM, urn_jaus_jss_core_Events_1_0::Events_ReceiveFSM* pEvents_ReceiveFSM, urn_jaus_jss_core_AccessControl_1_0::AccessControl_ReceiveFSM* pAccessControl_ReceiveFSM, urn_jaus_jss_core_Management_1_0::Management_ReceiveFSM* pManagement_ReceiveFSM)
{

	/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
	 */
	context = new SkidSteerVehicleSim_ReceiveFSMContext(*this);

	this->pTransport_ReceiveFSM = pTransport_ReceiveFSM;
	this->pEvents_ReceiveFSM = pEvents_ReceiveFSM;
	this->pAccessControl_ReceiveFSM = pAccessControl_ReceiveFSM;
	this->pManagement_ReceiveFSM = pManagement_ReceiveFSM;

}



SkidSteerVehicleSim_ReceiveFSM::~SkidSteerVehicleSim_ReceiveFSM() 
{
	delete context;
}

void SkidSteerVehicleSim_ReceiveFSM::setupNotifications()
{
	pManagement_ReceiveFSM->registerNotification("Receiving_Ready_NotControlled_StateA_Standby", ieHandler, "InternalStateChange_To_SkidSteerVehicleSim_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Standby", "Management_ReceiveFSM");
	pManagement_ReceiveFSM->registerNotification("Receiving_Ready_NotControlled_StateA_Init", ieHandler, "InternalStateChange_To_SkidSteerVehicleSim_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Init", "Management_ReceiveFSM");
	pManagement_ReceiveFSM->registerNotification("Receiving_Ready_NotControlled_StateA_Failure", ieHandler, "InternalStateChange_To_SkidSteerVehicleSim_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Failure", "Management_ReceiveFSM");
	pManagement_ReceiveFSM->registerNotification("Receiving_Ready_NotControlled_StateA_Shutdown", ieHandler, "InternalStateChange_To_SkidSteerVehicleSim_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Shutdown", "Management_ReceiveFSM");
	pManagement_ReceiveFSM->registerNotification("Receiving_Ready_NotControlled_StateA_Emergency", ieHandler, "InternalStateChange_To_SkidSteerVehicleSim_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Emergency", "Management_ReceiveFSM");
	pManagement_ReceiveFSM->registerNotification("Receiving_Ready_NotControlled_StateA", ieHandler, "InternalStateChange_To_SkidSteerVehicleSim_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Init", "Management_ReceiveFSM");
	pManagement_ReceiveFSM->registerNotification("Receiving_Ready_NotControlled", ieHandler, "InternalStateChange_To_SkidSteerVehicleSim_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Init", "Management_ReceiveFSM");
	pManagement_ReceiveFSM->registerNotification("Receiving_Ready_Controlled_StateB_Standby", ieHandler, "InternalStateChange_To_SkidSteerVehicleSim_ReceiveFSM_Receiving_Ready_Controlled_StateB_Standby", "Management_ReceiveFSM");
	pManagement_ReceiveFSM->registerNotification("Receiving_Ready_Controlled_StateB_Ready", ieHandler, "InternalStateChange_To_SkidSteerVehicleSim_ReceiveFSM_Receiving_Ready_Controlled_StateB_Ready", "Management_ReceiveFSM");
	pManagement_ReceiveFSM->registerNotification("Receiving_Ready_Controlled_StateB_Emergency", ieHandler, "InternalStateChange_To_SkidSteerVehicleSim_ReceiveFSM_Receiving_Ready_Controlled_StateB_Emergency", "Management_ReceiveFSM");
	pManagement_ReceiveFSM->registerNotification("Receiving_Ready_Controlled_StateB", ieHandler, "InternalStateChange_To_SkidSteerVehicleSim_ReceiveFSM_Receiving_Ready_Controlled_StateB_Standby", "Management_ReceiveFSM");
	pManagement_ReceiveFSM->registerNotification("Receiving_Ready_Controlled", ieHandler, "InternalStateChange_To_SkidSteerVehicleSim_ReceiveFSM_Receiving_Ready_Controlled_StateB_Standby", "Management_ReceiveFSM");
	pManagement_ReceiveFSM->registerNotification("Receiving_Ready", ieHandler, "InternalStateChange_To_SkidSteerVehicleSim_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Init", "Management_ReceiveFSM");
	pManagement_ReceiveFSM->registerNotification("Receiving", ieHandler, "InternalStateChange_To_SkidSteerVehicleSim_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Init", "Management_ReceiveFSM");
	registerNotification("Receiving_Ready_NotControlled_StateA_Standby", pManagement_ReceiveFSM->getHandler(), "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Standby", "SkidSteerVehicleSim_ReceiveFSM");
	registerNotification("Receiving_Ready_NotControlled_StateA_Init", pManagement_ReceiveFSM->getHandler(), "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Init", "SkidSteerVehicleSim_ReceiveFSM");
	registerNotification("Receiving_Ready_NotControlled_StateA_Failure", pManagement_ReceiveFSM->getHandler(), "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Failure", "SkidSteerVehicleSim_ReceiveFSM");
	registerNotification("Receiving_Ready_NotControlled_StateA_Shutdown", pManagement_ReceiveFSM->getHandler(), "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Shutdown", "SkidSteerVehicleSim_ReceiveFSM");
	registerNotification("Receiving_Ready_NotControlled_StateA_Emergency", pManagement_ReceiveFSM->getHandler(), "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Emergency", "SkidSteerVehicleSim_ReceiveFSM");
	registerNotification("Receiving_Ready_NotControlled_StateA", pManagement_ReceiveFSM->getHandler(), "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_StateA", "SkidSteerVehicleSim_ReceiveFSM");
	registerNotification("Receiving_Ready_NotControlled", pManagement_ReceiveFSM->getHandler(), "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled", "SkidSteerVehicleSim_ReceiveFSM");
	registerNotification("Receiving_Ready_Controlled_StateB_Standby", pManagement_ReceiveFSM->getHandler(), "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_StateB_Standby", "SkidSteerVehicleSim_ReceiveFSM");
	registerNotification("Receiving_Ready_Controlled_StateB_Ready", pManagement_ReceiveFSM->getHandler(), "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_StateB_Ready", "SkidSteerVehicleSim_ReceiveFSM");
	registerNotification("Receiving_Ready_Controlled_StateB_Emergency", pManagement_ReceiveFSM->getHandler(), "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_StateB_Emergency", "SkidSteerVehicleSim_ReceiveFSM");
	registerNotification("Receiving_Ready_Controlled_StateB", pManagement_ReceiveFSM->getHandler(), "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_StateB", "SkidSteerVehicleSim_ReceiveFSM");
	registerNotification("Receiving_Ready_Controlled", pManagement_ReceiveFSM->getHandler(), "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled", "SkidSteerVehicleSim_ReceiveFSM");
	registerNotification("Receiving_Ready", pManagement_ReceiveFSM->getHandler(), "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready", "SkidSteerVehicleSim_ReceiveFSM");
	registerNotification("Receiving", pManagement_ReceiveFSM->getHandler(), "InternalStateChange_To_Management_ReceiveFSM_Receiving", "SkidSteerVehicleSim_ReceiveFSM");

}

void SkidSteerVehicleSim_ReceiveFSM::SendAction(std::string arg0, Receive::Body::ReceiveRec transportData)
{
	// Extract the sender information
	JausAddress sender(transportData.getSrcSubsystemID(),
					   transportData.getSrcNodeID(),
					   transportData.getSrcComponentID());

	// Formulate the requested response
	if (arg0 == "ReportSimulatedPose")
	{
		ReportSimulatedPose pose_msg;
		pose_msg.getBody()->getLocalPoseRec()->setX( SharedData::get()->getX() );
		pose_msg.getBody()->getLocalPoseRec()->setY( SharedData::get()->getY() );
		pose_msg.getBody()->getLocalPoseRec()->setYaw( SharedData::get()->getYaw() );
		sendJausMessage(pose_msg, sender);
	}
	else if (arg0 == "ReportWrenchEffort")
	{
		ReportWrenchEffort wrench_msg;
		wrench_msg.getBody()->getWrenchEffortRec()->setPropulsiveLinearEffortX( currentLinX );
		wrench_msg.getBody()->getWrenchEffortRec()->setPropulsiveRotationalEffortZ( currentRotZ );
		sendJausMessage(wrench_msg, sender);
	}
}

void SkidSteerVehicleSim_ReceiveFSM::setWrenchEffortAction(SetWrenchEffort msg)
{
	/// Copy the requested wrench effort.  If it's not specified, it's zero.
	currentLinX = msg.getBody()->getWrenchEffortRec()->isPropulsiveLinearEffortXValid() ? 
						msg.getBody()->getWrenchEffortRec()->getPropulsiveLinearEffortX() : 0.0;
	currentRotZ = msg.getBody()->getWrenchEffortRec()->isPropulsiveRotationalEffortZValid() ? 
						msg.getBody()->getWrenchEffortRec()->getPropulsiveRotationalEffortZ() : 0.0;

	// For convenience, we update the simulation one step for every SetWrenchEffort message.
	// This is not generally applicable, but simplifies our example to avoid the use
	// of multi-threading, timers, and other complex code structures.

	// Change orientation first, watching for rollover
	double newValue = SharedData::get()->getYaw() - currentRotZ/100.0 * DeltaTime;
	while (newValue > PI) newValue -= 2*PI;
	while (newValue <= -1.0*PI) newValue += 2*PI;
	SharedData::get()->setYaw(newValue);

	// Update position based on forward movement at the current heading
	double x = SharedData::get()->getX() + currentLinX * DeltaTime * cos( SharedData::get()->getYaw() );
	double y = SharedData::get()->getY() + currentLinX * DeltaTime * sin( SharedData::get()->getYaw() );
	SharedData::get()->setX(x); SharedData::get()->setY(y);
}



bool SkidSteerVehicleSim_ReceiveFSM::isControllingClient(Receive::Body::ReceiveRec transportData)
{
	// Since this is an internal facing service, we only accept messages from this component.
	// Rather than using access control 'isControllingClient', we override this guard to
	// make sure the message is from the local component.
	JausAddress sender(transportData.getSrcSubsystemID(),
					   transportData.getSrcNodeID(),
					   transportData.getSrcComponentID());
	return (sender == *jausRouter->getJausAddress());
}


};
