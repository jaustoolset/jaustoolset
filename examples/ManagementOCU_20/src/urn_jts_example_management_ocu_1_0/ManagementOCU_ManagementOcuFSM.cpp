

#include "urn_jts_example_management_ocu_1_0/ManagementOCU_ManagementOcuFSM.h"




using namespace JTS;

namespace urn_jts_example_management_ocu_1_0
{

static void *displayMenuAndGrabInput(void *param)
{
	InternalEventHandler *ieHandler = (InternalEventHandler *)param;
	unsigned char selection = 255;

	do {
		std::cout << "Menu" << std::endl;
		std::cout << "____________________________________" << std::endl;
		std::cout << " 1) Send Resume" << std::endl;
		std::cout << " 2) Send Standby" << std::endl;
		std::cout << " 3) Send Set Emergency" << std::endl;
		std::cout << " 4) Send Clear Emergency" << std::endl;
		std::cout << " 5) Send Query Status" << std::endl;
		std::cout << " 6) Send Shutdown" << std::endl;
		std::cout << " 7) Send Release Control" << std::endl;
		std::cout << " 8) Quit and exit" << std::endl;
		std::cout << "____________________________________" << std::endl;
		std::cout << "[Input]: ";

		selection = std::cin.get();

		// This action will trigger our other thread to send the message,
		// assuming we have something more interesting than the 'enter' key.
		if (selection != '\n')
		{
			MenuItemEntered* item = new MenuItemEntered();
			item->getMenuItemEnteredBody()->getMenuItemEnteredRecord()->setSelection( selection );
			ieHandler->invoke(item);
		}

	} while (selection != '8');

	return NULL;
}


ManagementOCU_ManagementOcuFSM::ManagementOCU_ManagementOcuFSM()
{

	/*
	 * Context has to be constructed last so that all class variables are 
	 * available if an EntryAction of the InitialState of the statemachine 
	 * needs them. 
	 */
	context = new ManagementOCU_ManagementOcuFSMContext(*this);
}



ManagementOCU_ManagementOcuFSM::~ManagementOCU_ManagementOcuFSM() 
{
	delete context;
}

void ManagementOCU_ManagementOcuFSM::setupNotifications()
{

}

void ManagementOCU_ManagementOcuFSM::displayMenuAction()
{
	static bool doOnce = false;

	// If we have not yet done it, spawn a thread to monitor the menu.
	if (!doOnce) DeVivo::Junior::JrSpawnThread(displayMenuAndGrabInput, ieHandler);
	doOnce = true;
}

void ManagementOCU_ManagementOcuFSM::displayStatusAction(ReportStatus msg)
{
	// Simplify debug output
	std::string mgmt_states[6] = {"Init", "Ready", "Standby", "Shutdown", "Failure", "Emergency"};
	std::cout << "Management server status: " << mgmt_states[msg.getBody()->getReportStatusRec()->getStatus()] << std::endl;
}

void ManagementOCU_ManagementOcuFSM::printMessageAction(std::string arg0)
{
	std::cout << arg0 << std::endl;
}

void ManagementOCU_ManagementOcuFSM::sendManagementMessageAction(MenuItemEntered msg)
{
	Resume resume_msg;
	Standby standby_msg;
	SetEmergency set_emergency_msg;
	ClearEmergency clear_emergency_msg;
	QueryStatus query_status_msg;
	ReleaseControl release_control_msg;
	Shutdown shutdown_msg;

	JausAddress dest( jausRouter->getJausAddress()->getSubsystemID(),
			          jausRouter->getJausAddress()->getNodeID(),
			          11);

	switch(msg.getMenuItemEnteredBody()->getMenuItemEnteredRecord()->getSelection()) {
		case '1': 
			std::cout << "Sending Resume" << std::endl;
            sendJausMessage( resume_msg, dest);
			break;
		case '2':
			std::cout << "Sending Standby" << std::endl;
            sendJausMessage( standby_msg, dest);
			break;
		case '3':
			std::cout << "Sending SetEmergency" << std::endl;
            sendJausMessage( set_emergency_msg, dest);
			break;
		case '4':
			std::cout << "Sending ClearEmergency" << std::endl;
            sendJausMessage( clear_emergency_msg, dest);
			break;
		case '5':
			std::cout << "Sending QueryStatus" << std::endl;
            sendJausMessage( query_status_msg, dest);
			break;
		case '6':
			std::cout << "Sending Shutdown" << std::endl;
            sendJausMessage( shutdown_msg, dest);
			break;
		case '7':
			std::cout << "Sending ReleaseControl" << std::endl;
            sendJausMessage( release_control_msg, dest);
			break;
		default:
			std::cout << "Unknown input: " << msg.getMenuItemEnteredBody()->getMenuItemEnteredRecord()->getSelection() << std::endl;
			break;
	}
}

void ManagementOCU_ManagementOcuFSM::sendRequestControlAction()
{
	/// Insert User Code HERE
	std::cout << "Sending Request for control" << std::endl;

	// what should really happen here is that an internal event is called, so that
	// the send happens inside of a sendAction(), rather than here. 

	// Now, we need to send a RequestComponentControl to the management service
	RequestControl request_control_msg;
	request_control_msg.getBody()->getRequestControlRec()->setAuthorityCode(200);

	// send a message, to this subsystem, this node, component 11. 
	// Again, discovery should be used to find the JAUS ID of the service we want to 
	// address, rather than hardcoding the address
	JausAddress dest( jausRouter->getJausAddress()->getSubsystemID(), 
					  jausRouter->getJausAddress()->getNodeID(),
					  11);
    sendJausMessage( request_control_msg, dest);
}

void ManagementOCU_ManagementOcuFSM::terminateServiceAction()
{
	std::cout << "Terminating OCU..." << std::endl;
	exit(0);
}



bool ManagementOCU_ManagementOcuFSM::isControlAccepted(ConfirmControl msg)
{
	/// Insert User Code HERE
	std::cout << "isControlAccepted guard: Got response code " <<
		(unsigned short) msg.getBody()->getConfirmControlRec()->getResponseCode() << std::endl;
	if (msg.getBody()->getConfirmControlRec()->getResponseCode() == 0)
		return true;
	return false;
}
bool ManagementOCU_ManagementOcuFSM::isSelectionToEnd(MenuItemEntered msg)
{
	/// Insert User Code HERE
	if (msg.getMenuItemEnteredBody()->getMenuItemEnteredRecord()->getSelection() == '8') return true;
	return false;
}


};
