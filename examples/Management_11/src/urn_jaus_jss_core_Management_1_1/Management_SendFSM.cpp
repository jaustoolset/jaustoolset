

#include "urn_jaus_jss_core_Management_1_1/Management_SendFSM.h"




using namespace JTS;

namespace urn_jaus_jss_core_Management_1_1
{



Management_SendFSM::Management_SendFSM(urn_jaus_jss_core_Transport_1_1::Transport_SendFSM* pTransport_SendFSM, urn_jaus_jss_core_Events_1_1::Events_SendFSM* pEvents_SendFSM, urn_jaus_jss_core_AccessControl_1_1::AccessControl_SendFSM* pAccessControl_SendFSM)
{

	/*
	 * Context has to be constructed last so that all class variables are 
	 * available if an EntryAction of the InitialState of the statemachine 
	 * needs them. 
	 */
	context = new Management_SendFSMContext(*this);

	this->pTransport_SendFSM = pTransport_SendFSM;
	this->pEvents_SendFSM = pEvents_SendFSM;
	this->pAccessControl_SendFSM = pAccessControl_SendFSM;
}



Management_SendFSM::~Management_SendFSM() 
{
	delete context;
}

void Management_SendFSM::setupNotifications()
{
	pAccessControl_SendFSM->registerNotification("Sending", ieHandler, "InternalStateChange_To_Management_SendFSM_Sending", "AccessControl_SendFSM");
	registerNotification("Sending", pAccessControl_SendFSM->getHandler(), "InternalStateChange_To_AccessControl_SendFSM_Sending", "Management_SendFSM");

}





};
