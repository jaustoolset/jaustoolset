

#ifndef EVENTS_SENDFSM_H
#define EVENTS_SENDFSM_H

#include "JausUtils.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JTSStateMachine.h"
#include "urn_jaus_jss_core_Events_1_0/Messages/MessageSet.h"
#include "urn_jaus_jss_core_Events_1_0/InternalEvents/InternalEventsSet.h"

typedef JTS::Receive_1_0 Receive;
typedef JTS::Send_1_0 Send;

#include "urn_jaus_jss_core_Transport_1_0/Transport_SendFSM.h"


#include "Events_SendFSM_sm.h"

namespace urn_jaus_jss_core_Events_1_0
{
	
class DllExport Events_SendFSM : public JTS::StateMachine
{
public:
	Events_SendFSM(urn_jaus_jss_core_Transport_1_0::Transport_SendFSM* pTransport_SendFSM);
	virtual ~Events_SendFSM();
	
	/// Handle notifications on parent state changes
	virtual void setupNotifications();

	/// Action Methods


	/// Guard Methods

	
	
	Events_SendFSMContext *context;
	
protected:

    /// References to parent FSMs
	urn_jaus_jss_core_Transport_1_0::Transport_SendFSM* pTransport_SendFSM;

    
};

};

#endif // EVENTS_SENDFSM_H
