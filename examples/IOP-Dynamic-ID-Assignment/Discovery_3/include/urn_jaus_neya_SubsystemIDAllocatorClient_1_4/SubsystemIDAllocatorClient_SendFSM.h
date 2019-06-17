

#ifndef SUBSYSTEMIDALLOCATORCLIENT_SENDFSM_H
#define SUBSYSTEMIDALLOCATORCLIENT_SENDFSM_H

#include "JausUtils.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JTSStateMachine.h"
#include "urn_jaus_neya_SubsystemIDAllocatorClient_1_4/Messages/MessageSet.h"
#include "urn_jaus_neya_SubsystemIDAllocatorClient_1_4/InternalEvents/InternalEventsSet.h"

typedef JTS::Receive_1_1 Receive;
typedef JTS::Send_1_1 Send;

#include "urn_jaus_jss_core_Transport_1_1/Transport_SendFSM.h"


#include "SubsystemIDAllocatorClient_SendFSM_sm.h"

namespace urn_jaus_neya_SubsystemIDAllocatorClient_1_4
{
	
class DllExport SubsystemIDAllocatorClient_SendFSM : public JTS::StateMachine
{
public:
	SubsystemIDAllocatorClient_SendFSM(urn_jaus_jss_core_Transport_1_1::Transport_SendFSM* pTransport_SendFSM);
	virtual ~SubsystemIDAllocatorClient_SendFSM();
	
	/// Handle notifications on parent state changes
	virtual void setupNotifications();

	/// Action Methods


	/// Guard Methods

	
	
	SubsystemIDAllocatorClient_SendFSMContext *context;
	
protected:

    /// References to parent FSMs
	urn_jaus_jss_core_Transport_1_1::Transport_SendFSM* pTransport_SendFSM;

    
};

};

#endif // SUBSYSTEMIDALLOCATORCLIENT_SENDFSM_H
