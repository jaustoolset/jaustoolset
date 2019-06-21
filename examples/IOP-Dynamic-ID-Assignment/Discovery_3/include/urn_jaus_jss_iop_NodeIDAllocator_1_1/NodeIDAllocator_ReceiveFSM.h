

#ifndef NODEIDALLOCATOR_RECEIVEFSM_H
#define NODEIDALLOCATOR_RECEIVEFSM_H

#include "JausUtils.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JTSStateMachine.h"
#include "urn_jaus_jss_iop_NodeIDAllocator_1_1/Messages/MessageSet.h"
#include "urn_jaus_jss_iop_NodeIDAllocator_1_1/InternalEvents/InternalEventsSet.h"

typedef JTS::Receive_1_1 Receive;
typedef JTS::Send_1_1 Send;

#include "urn_jaus_jss_core_Transport_1_1/Transport_ReceiveFSM.h"


#include "NodeIDAllocator_ReceiveFSM_sm.h"

namespace urn_jaus_jss_iop_NodeIDAllocator_1_1
{
	
class DllExport NodeIDAllocator_ReceiveFSM : public JTS::StateMachine
{
public:
	NodeIDAllocator_ReceiveFSM(urn_jaus_jss_core_Transport_1_1::Transport_ReceiveFSM* pTransport_ReceiveFSM);
	virtual ~NodeIDAllocator_ReceiveFSM();
	
	/// Handle notifications on parent state changes
	virtual void setupNotifications();

	/// Action Methods
	virtual void sendGrantNodeIDAction(RequestNodeID msg);


	/// Guard Methods

	
	
	NodeIDAllocator_ReceiveFSMContext *context;
	
protected:

    /// References to parent FSMs
	urn_jaus_jss_core_Transport_1_1::Transport_ReceiveFSM* pTransport_ReceiveFSM;

    
};

};

#endif // NODEIDALLOCATOR_RECEIVEFSM_H
