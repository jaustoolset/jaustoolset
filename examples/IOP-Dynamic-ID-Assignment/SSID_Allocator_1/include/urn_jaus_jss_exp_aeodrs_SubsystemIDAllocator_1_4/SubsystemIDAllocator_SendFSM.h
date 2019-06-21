

#ifndef SUBSYSTEMIDALLOCATOR_SENDFSM_H
#define SUBSYSTEMIDALLOCATOR_SENDFSM_H

#include "JausUtils.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JTSStateMachine.h"
#include "urn_jaus_jss_exp_aeodrs_SubsystemIDAllocator_1_4/Messages/MessageSet.h"
#include "urn_jaus_jss_exp_aeodrs_SubsystemIDAllocator_1_4/InternalEvents/InternalEventsSet.h"

typedef JTS::Receive_1_1 Receive;
typedef JTS::Send_1_1 Send;

#include "urn_jaus_jss_core_Transport_1_1/Transport_SendFSM.h"


#include "SubsystemIDAllocator_SendFSM_sm.h"

namespace urn_jaus_jss_exp_aeodrs_SubsystemIDAllocator_1_4
{
	
class DllExport SubsystemIDAllocator_SendFSM : public JTS::StateMachine
{
public:
	SubsystemIDAllocator_SendFSM(urn_jaus_jss_core_Transport_1_1::Transport_SendFSM* pTransport_SendFSM);
	virtual ~SubsystemIDAllocator_SendFSM();
	
	/// Handle notifications on parent state changes
	virtual void setupNotifications();

	/// Action Methods


	/// Guard Methods

	
	
	SubsystemIDAllocator_SendFSMContext *context;
	
protected:

    /// References to parent FSMs
	urn_jaus_jss_core_Transport_1_1::Transport_SendFSM* pTransport_SendFSM;

    
};

};

#endif // SUBSYSTEMIDALLOCATOR_SENDFSM_H
