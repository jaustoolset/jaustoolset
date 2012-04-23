

#include "urn_jaus_jss_core_Events_1_1/Events_SendFSM.h"




using namespace JTS;

namespace urn_jaus_jss_core_Events_1_1
{



Events_SendFSM::Events_SendFSM(urn_jaus_jss_core_Transport_1_1::Transport_SendFSM* pTransport_SendFSM)
{

	/*
	 * Context has to be constructed last so that all class variables are 
	 * available if an EntryAction of the InitialState of the statemachine 
	 * needs them. 
	 */
	context = new Events_SendFSMContext(*this);

	this->pTransport_SendFSM = pTransport_SendFSM;
}



Events_SendFSM::~Events_SendFSM() 
{
	delete context;
}

void Events_SendFSM::setupNotifications()
{
	pTransport_SendFSM->registerNotification("Sending", ieHandler, "InternalStateChange_To_Events_SendFSM_Sending", "Transport_SendFSM");
	registerNotification("Sending", pTransport_SendFSM->getHandler(), "InternalStateChange_To_Transport_SendFSM_Sending", "Events_SendFSM");

}





};
