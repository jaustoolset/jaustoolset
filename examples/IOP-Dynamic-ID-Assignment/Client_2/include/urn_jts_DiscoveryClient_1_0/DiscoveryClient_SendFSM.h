

#ifndef DISCOVERYCLIENT_SENDFSM_H
#define DISCOVERYCLIENT_SENDFSM_H

#include "JausUtils.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JTSStateMachine.h"
#include "urn_jts_DiscoveryClient_1_0/Messages/MessageSet.h"
#include "urn_jts_DiscoveryClient_1_0/InternalEvents/InternalEventsSet.h"

typedef JTS::Receive_1_1 Receive;
typedef JTS::Send_1_1 Send;

#include "urn_jaus_jss_core_Transport_1_1/Transport_SendFSM.h"


#include "DiscoveryClient_SendFSM_sm.h"

namespace urn_jts_DiscoveryClient_1_0
{
	
class DllExport DiscoveryClient_SendFSM : public JTS::StateMachine
{
public:
	DiscoveryClient_SendFSM(urn_jaus_jss_core_Transport_1_1::Transport_SendFSM* pTransport_SendFSM);
	virtual ~DiscoveryClient_SendFSM();
	
	/// Handle notifications on parent state changes
	virtual void setupNotifications();

	/// Action Methods


	/// Guard Methods

	
	
	DiscoveryClient_SendFSMContext *context;
	
protected:

    /// References to parent FSMs
	urn_jaus_jss_core_Transport_1_1::Transport_SendFSM* pTransport_SendFSM;

    
};

};

#endif // DISCOVERYCLIENT_SENDFSM_H
