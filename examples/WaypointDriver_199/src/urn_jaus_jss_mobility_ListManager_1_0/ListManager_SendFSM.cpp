

#include "urn_jaus_jss_mobility_ListManager_1_0/ListManager_SendFSM.h"




using namespace JTS;

namespace urn_jaus_jss_mobility_ListManager_1_0
{



ListManager_SendFSM::ListManager_SendFSM(urn_jaus_jss_core_Transport_1_0::Transport_SendFSM* pTransport_SendFSM, urn_jaus_jss_core_Events_1_0::Events_SendFSM* pEvents_SendFSM, urn_jaus_jss_core_AccessControl_1_0::AccessControl_SendFSM* pAccessControl_SendFSM, urn_jaus_jss_core_Management_1_0::Management_SendFSM* pManagement_SendFSM)
{

	/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
	 */
	context = new ListManager_SendFSMContext(*this);

	this->pTransport_SendFSM = pTransport_SendFSM;
	this->pEvents_SendFSM = pEvents_SendFSM;
	this->pAccessControl_SendFSM = pAccessControl_SendFSM;
	this->pManagement_SendFSM = pManagement_SendFSM;
}



ListManager_SendFSM::~ListManager_SendFSM() 
{
	delete context;
}

void ListManager_SendFSM::setupNotifications()
{
	pManagement_SendFSM->registerNotification("Sending", ieHandler, "InternalStateChange_To_ListManager_SendFSM_Sending", "Management_SendFSM");
	registerNotification("Sending", pManagement_SendFSM->getHandler(), "InternalStateChange_To_Management_SendFSM_Sending", "ListManager_SendFSM");

}





};
