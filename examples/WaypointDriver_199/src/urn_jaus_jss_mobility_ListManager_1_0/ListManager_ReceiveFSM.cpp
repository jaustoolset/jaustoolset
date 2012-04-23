

#include "urn_jaus_jss_mobility_ListManager_1_0/ListManager_ReceiveFSM.h"




using namespace JTS;

namespace urn_jaus_jss_mobility_ListManager_1_0
{



ListManager_ReceiveFSM::ListManager_ReceiveFSM(urn_jaus_jss_core_Transport_1_0::Transport_ReceiveFSM* pTransport_ReceiveFSM, urn_jaus_jss_core_Events_1_0::Events_ReceiveFSM* pEvents_ReceiveFSM, urn_jaus_jss_core_AccessControl_1_0::AccessControl_ReceiveFSM* pAccessControl_ReceiveFSM, urn_jaus_jss_core_Management_1_0::Management_ReceiveFSM* pManagement_ReceiveFSM)
{

	/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
	 */
	context = new ListManager_ReceiveFSMContext(*this);

	this->pTransport_ReceiveFSM = pTransport_ReceiveFSM;
	this->pEvents_ReceiveFSM = pEvents_ReceiveFSM;
	this->pAccessControl_ReceiveFSM = pAccessControl_ReceiveFSM;
	this->pManagement_ReceiveFSM = pManagement_ReceiveFSM;
}



ListManager_ReceiveFSM::~ListManager_ReceiveFSM() 
{
	delete context;
}

void ListManager_ReceiveFSM::setupNotifications()
{
	pManagement_ReceiveFSM->registerNotification("Receiving_Ready_NotControlled_StateA_Standby", ieHandler, "InternalStateChange_To_ListManager_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Standby", "Management_ReceiveFSM");
	pManagement_ReceiveFSM->registerNotification("Receiving_Ready_NotControlled_StateA_Init", ieHandler, "InternalStateChange_To_ListManager_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Init", "Management_ReceiveFSM");
	pManagement_ReceiveFSM->registerNotification("Receiving_Ready_NotControlled_StateA_Failure", ieHandler, "InternalStateChange_To_ListManager_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Failure", "Management_ReceiveFSM");
	pManagement_ReceiveFSM->registerNotification("Receiving_Ready_NotControlled_StateA_Shutdown", ieHandler, "InternalStateChange_To_ListManager_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Shutdown", "Management_ReceiveFSM");
	pManagement_ReceiveFSM->registerNotification("Receiving_Ready_NotControlled_StateA_Emergency", ieHandler, "InternalStateChange_To_ListManager_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Emergency", "Management_ReceiveFSM");
	pManagement_ReceiveFSM->registerNotification("Receiving_Ready_NotControlled_StateA", ieHandler, "InternalStateChange_To_ListManager_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Init", "Management_ReceiveFSM");
	pManagement_ReceiveFSM->registerNotification("Receiving_Ready_NotControlled", ieHandler, "InternalStateChange_To_ListManager_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Init", "Management_ReceiveFSM");
	pManagement_ReceiveFSM->registerNotification("Receiving_Ready_Controlled_StateB_Standby", ieHandler, "InternalStateChange_To_ListManager_ReceiveFSM_Receiving_Ready_Controlled_StateB_Standby", "Management_ReceiveFSM");
	pManagement_ReceiveFSM->registerNotification("Receiving_Ready_Controlled_StateB_Ready", ieHandler, "InternalStateChange_To_ListManager_ReceiveFSM_Receiving_Ready_Controlled_StateB_Ready", "Management_ReceiveFSM");
	pManagement_ReceiveFSM->registerNotification("Receiving_Ready_Controlled_StateB_Emergency", ieHandler, "InternalStateChange_To_ListManager_ReceiveFSM_Receiving_Ready_Controlled_StateB_Emergency", "Management_ReceiveFSM");
	pManagement_ReceiveFSM->registerNotification("Receiving_Ready_Controlled_StateB", ieHandler, "InternalStateChange_To_ListManager_ReceiveFSM_Receiving_Ready_Controlled_StateB_Standby", "Management_ReceiveFSM");
	pManagement_ReceiveFSM->registerNotification("Receiving_Ready_Controlled", ieHandler, "InternalStateChange_To_ListManager_ReceiveFSM_Receiving_Ready_Controlled_StateB_Standby", "Management_ReceiveFSM");
	pManagement_ReceiveFSM->registerNotification("Receiving_Ready", ieHandler, "InternalStateChange_To_ListManager_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Init", "Management_ReceiveFSM");
	pManagement_ReceiveFSM->registerNotification("Receiving", ieHandler, "InternalStateChange_To_ListManager_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Init", "Management_ReceiveFSM");
	registerNotification("Receiving_Ready_NotControlled_StateA_Standby", pManagement_ReceiveFSM->getHandler(), "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Standby", "ListManager_ReceiveFSM");
	registerNotification("Receiving_Ready_NotControlled_StateA_Init", pManagement_ReceiveFSM->getHandler(), "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Init", "ListManager_ReceiveFSM");
	registerNotification("Receiving_Ready_NotControlled_StateA_Failure", pManagement_ReceiveFSM->getHandler(), "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Failure", "ListManager_ReceiveFSM");
	registerNotification("Receiving_Ready_NotControlled_StateA_Shutdown", pManagement_ReceiveFSM->getHandler(), "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Shutdown", "ListManager_ReceiveFSM");
	registerNotification("Receiving_Ready_NotControlled_StateA_Emergency", pManagement_ReceiveFSM->getHandler(), "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_StateA_Emergency", "ListManager_ReceiveFSM");
	registerNotification("Receiving_Ready_NotControlled_StateA", pManagement_ReceiveFSM->getHandler(), "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_StateA", "ListManager_ReceiveFSM");
	registerNotification("Receiving_Ready_NotControlled", pManagement_ReceiveFSM->getHandler(), "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled", "ListManager_ReceiveFSM");
	registerNotification("Receiving_Ready_Controlled_StateB_Standby", pManagement_ReceiveFSM->getHandler(), "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_StateB_Standby", "ListManager_ReceiveFSM");
	registerNotification("Receiving_Ready_Controlled_StateB_Ready", pManagement_ReceiveFSM->getHandler(), "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_StateB_Ready", "ListManager_ReceiveFSM");
	registerNotification("Receiving_Ready_Controlled_StateB_Emergency", pManagement_ReceiveFSM->getHandler(), "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_StateB_Emergency", "ListManager_ReceiveFSM");
	registerNotification("Receiving_Ready_Controlled_StateB", pManagement_ReceiveFSM->getHandler(), "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_StateB", "ListManager_ReceiveFSM");
	registerNotification("Receiving_Ready_Controlled", pManagement_ReceiveFSM->getHandler(), "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled", "ListManager_ReceiveFSM");
	registerNotification("Receiving_Ready", pManagement_ReceiveFSM->getHandler(), "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready", "ListManager_ReceiveFSM");
	registerNotification("Receiving", pManagement_ReceiveFSM->getHandler(), "InternalStateChange_To_Management_ReceiveFSM_Receiving", "ListManager_ReceiveFSM");

}

void ListManager_ReceiveFSM::SendAction(std::string arg0, Receive::Body::ReceiveRec transportData)
{
	/// Insert User Code HERE
}

void ListManager_ReceiveFSM::deleteElementAction()
{
	/// Insert User Code HERE
}

void ListManager_ReceiveFSM::setElementAction(SetElement msg)
{
	/// Insert User Code HERE
}



bool ListManager_ReceiveFSM::elementExists(DeleteElement msg)
{
	/// Insert User Code HERE
	return false;
}
bool ListManager_ReceiveFSM::elementExists(QueryElement msg)
{
	/// Insert User Code HERE
	return false;
}
bool ListManager_ReceiveFSM::isControllingClient(Receive::Body::ReceiveRec transportData)
{
	//// By default, inherited guards call the parent function.
	//// This can be replaced or modified as needed.
	return pAccessControl_ReceiveFSM->isControllingClient(transportData );
}
bool ListManager_ReceiveFSM::isElementSupported(SetElement msg)
{
	/// Insert User Code HERE
	return false;
}
bool ListManager_ReceiveFSM::isValidElementRequest(SetElement msg)
{
	/// Insert User Code HERE
	return false;
}


};
