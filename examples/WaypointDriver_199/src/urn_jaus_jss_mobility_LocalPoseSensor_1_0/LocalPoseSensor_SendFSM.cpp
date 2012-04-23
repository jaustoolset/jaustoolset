

#include "urn_jaus_jss_mobility_LocalPoseSensor_1_0/LocalPoseSensor_SendFSM.h"




using namespace JTS;

namespace urn_jaus_jss_mobility_LocalPoseSensor_1_0
{



LocalPoseSensor_SendFSM::LocalPoseSensor_SendFSM(urn_jaus_jss_core_Transport_1_0::Transport_SendFSM* pTransport_SendFSM, urn_jaus_jss_core_Events_1_0::Events_SendFSM* pEvents_SendFSM, urn_jaus_jss_core_AccessControl_1_0::AccessControl_SendFSM* pAccessControl_SendFSM)
{

	/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
	 */
	context = new LocalPoseSensor_SendFSMContext(*this);

	this->pTransport_SendFSM = pTransport_SendFSM;
	this->pEvents_SendFSM = pEvents_SendFSM;
	this->pAccessControl_SendFSM = pAccessControl_SendFSM;
}



LocalPoseSensor_SendFSM::~LocalPoseSensor_SendFSM() 
{
	delete context;
}

void LocalPoseSensor_SendFSM::setupNotifications()
{
	pAccessControl_SendFSM->registerNotification("Sending", ieHandler, "InternalStateChange_To_LocalPoseSensor_SendFSM_Sending", "AccessControl_SendFSM");
	registerNotification("Sending", pAccessControl_SendFSM->getHandler(), "InternalStateChange_To_AccessControl_SendFSM_Sending", "LocalPoseSensor_SendFSM");

}





};
