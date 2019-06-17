

#ifndef SUBSYSTEMIDALLOCATORCLIENT_RECEIVEFSM_H
#define SUBSYSTEMIDALLOCATORCLIENT_RECEIVEFSM_H

#include "JausUtils.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JTSStateMachine.h"
#include "urn_jaus_neya_SubsystemIDAllocatorClient_1_4/Messages/MessageSet.h"
#include "urn_jaus_neya_SubsystemIDAllocatorClient_1_4/InternalEvents/InternalEventsSet.h"

typedef JTS::Receive_1_1 Receive;
typedef JTS::Send_1_1 Send;

#include "urn_jaus_jss_core_Transport_1_1/Transport_ReceiveFSM.h"


#include "SubsystemIDAllocatorClient_ReceiveFSM_sm.h"

namespace urn_jaus_neya_SubsystemIDAllocatorClient_1_4
{
	
class DllExport SubsystemIDAllocatorClient_ReceiveFSM : public JTS::StateMachine
{
public:
	SubsystemIDAllocatorClient_ReceiveFSM(urn_jaus_jss_core_Transport_1_1::Transport_ReceiveFSM* pTransport_ReceiveFSM);
	virtual ~SubsystemIDAllocatorClient_ReceiveFSM();
	
	/// Handle notifications on parent state changes
	virtual void setupNotifications();

	/// Action Methods
	virtual void handleGrantSubsystemIDAction(GrantSubsystemID msg);
	virtual void startPeriodicTimerAction();
	virtual void sendRequestSubsystemIDAction();


	/// Guard Methods

	
	
	SubsystemIDAllocatorClient_ReceiveFSMContext *context;
	
protected:

    /// References to parent FSMs
	urn_jaus_jss_core_Transport_1_1::Transport_ReceiveFSM* pTransport_ReceiveFSM;

    
};

};

#endif // SUBSYSTEMIDALLOCATORCLIENT_RECEIVEFSM_H
