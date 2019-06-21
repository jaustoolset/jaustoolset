

#include "urn_jts_DiscoveryClient_1_0/DiscoveryClient_SendFSM.h"




using namespace JTS;

namespace urn_jts_DiscoveryClient_1_0
{



DiscoveryClient_SendFSM::DiscoveryClient_SendFSM(urn_jaus_jss_core_Transport_1_1::Transport_SendFSM* pTransport_SendFSM)
{

	/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
	 */
	context = new DiscoveryClient_SendFSMContext(*this);

	this->pTransport_SendFSM = pTransport_SendFSM;
}



DiscoveryClient_SendFSM::~DiscoveryClient_SendFSM() 
{
	delete context;
}

void DiscoveryClient_SendFSM::setupNotifications()
{
	pTransport_SendFSM->registerNotification("Sending", ieHandler, "InternalStateChange_To_DiscoveryClient_SendFSM_Sending", "Transport_SendFSM");
	registerNotification("Sending", pTransport_SendFSM->getHandler(), "InternalStateChange_To_Transport_SendFSM_Sending", "DiscoveryClient_SendFSM");

}





};
