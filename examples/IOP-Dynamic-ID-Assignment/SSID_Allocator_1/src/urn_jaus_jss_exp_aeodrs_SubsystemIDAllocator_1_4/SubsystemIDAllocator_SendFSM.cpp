

#include "urn_jaus_jss_exp_aeodrs_SubsystemIDAllocator_1_4/SubsystemIDAllocator_SendFSM.h"




using namespace JTS;

namespace urn_jaus_jss_exp_aeodrs_SubsystemIDAllocator_1_4
{



SubsystemIDAllocator_SendFSM::SubsystemIDAllocator_SendFSM(urn_jaus_jss_core_Transport_1_1::Transport_SendFSM* pTransport_SendFSM)
{

	/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
	 */
	context = new SubsystemIDAllocator_SendFSMContext(*this);

	this->pTransport_SendFSM = pTransport_SendFSM;
}



SubsystemIDAllocator_SendFSM::~SubsystemIDAllocator_SendFSM() 
{
	delete context;
}

void SubsystemIDAllocator_SendFSM::setupNotifications()
{
	pTransport_SendFSM->registerNotification("Sending", ieHandler, "InternalStateChange_To_SubsystemIDAllocator_SendFSM_Sending", "Transport_SendFSM");
	registerNotification("Sending", pTransport_SendFSM->getHandler(), "InternalStateChange_To_Transport_SendFSM_Sending", "SubsystemIDAllocator_SendFSM");

}





};
