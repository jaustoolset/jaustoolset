

#ifndef NODEIDALLOCATOR_SENDFSM_H
#define NODEIDALLOCATOR_SENDFSM_H

#include "JausUtils.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JTSStateMachine.h"
#include "urn_jaus_jss_iop_NodeIDAllocator_1_1/Messages/MessageSet.h"
#include "urn_jaus_jss_iop_NodeIDAllocator_1_1/InternalEvents/InternalEventsSet.h"

typedef JTS::Receive_1_1 Receive;
typedef JTS::Send_1_1 Send;

#include "urn_jaus_jss_core_Transport_1_1/Transport_SendFSM.h"


#include "NodeIDAllocator_SendFSM_sm.h"

namespace urn_jaus_jss_iop_NodeIDAllocator_1_1
{
	
class DllExport NodeIDAllocator_SendFSM : public JTS::StateMachine
{
public:
	NodeIDAllocator_SendFSM(urn_jaus_jss_core_Transport_1_1::Transport_SendFSM* pTransport_SendFSM);
	virtual ~NodeIDAllocator_SendFSM();
	
	/// Handle notifications on parent state changes
	virtual void setupNotifications();

	/// Action Methods


	/// Guard Methods

	
	
	NodeIDAllocator_SendFSMContext *context;
	
protected:

    /// References to parent FSMs
	urn_jaus_jss_core_Transport_1_1::Transport_SendFSM* pTransport_SendFSM;

    
};

};

#endif // NODEIDALLOCATOR_SENDFSM_H
