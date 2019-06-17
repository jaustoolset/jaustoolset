

#include "urn_jaus_jss_iop_UnsolicitedHeartbeat_1_1/UnsolicitedHeartbeat_SendFSM.h"




using namespace JTS;

namespace urn_jaus_jss_iop_UnsolicitedHeartbeat_1_1
{



UnsolicitedHeartbeat_SendFSM::UnsolicitedHeartbeat_SendFSM(urn_jaus_jss_core_Transport_1_1::Transport_SendFSM* pTransport_SendFSM, urn_jaus_jss_core_Events_1_1::Events_SendFSM* pEvents_SendFSM, urn_jaus_jss_core_Liveness_1_1::Liveness_SendFSM* pLiveness_SendFSM)
{

	/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
	 */
	context = new UnsolicitedHeartbeat_SendFSMContext(*this);

	this->pTransport_SendFSM = pTransport_SendFSM;
	this->pEvents_SendFSM = pEvents_SendFSM;
	this->pLiveness_SendFSM = pLiveness_SendFSM;
}



UnsolicitedHeartbeat_SendFSM::~UnsolicitedHeartbeat_SendFSM() 
{
	delete context;
}

void UnsolicitedHeartbeat_SendFSM::setupNotifications()
{
	pLiveness_SendFSM->registerNotification("Sending", ieHandler, "InternalStateChange_To_UnsolicitedHeartbeat_SendFSM_Sending", "Liveness_SendFSM");
	registerNotification("Sending", pLiveness_SendFSM->getHandler(), "InternalStateChange_To_Liveness_SendFSM_Sending", "UnsolicitedHeartbeat_SendFSM");

}





};
