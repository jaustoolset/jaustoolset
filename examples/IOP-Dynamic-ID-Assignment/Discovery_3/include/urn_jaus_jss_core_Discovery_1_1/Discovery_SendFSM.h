

#ifndef DISCOVERY_SENDFSM_H
#define DISCOVERY_SENDFSM_H

#include "JausUtils.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JTSStateMachine.h"
#include "urn_jaus_jss_core_Discovery_1_1/Messages/MessageSet.h"
#include "urn_jaus_jss_core_Discovery_1_1/InternalEvents/InternalEventsSet.h"

typedef JTS::Receive_1_1 Receive;
typedef JTS::Send_1_1 Send;

#include "urn_jaus_jss_core_Transport_1_1/Transport_SendFSM.h"
#include "urn_jaus_jss_core_Events_1_1/Events_SendFSM.h"


#include "Discovery_SendFSM_sm.h"

namespace urn_jaus_jss_core_Discovery_1_1
{
	
class DllExport Discovery_SendFSM : public JTS::StateMachine
{
public:
	Discovery_SendFSM(urn_jaus_jss_core_Transport_1_1::Transport_SendFSM* pTransport_SendFSM, urn_jaus_jss_core_Events_1_1::Events_SendFSM* pEvents_SendFSM);
	virtual ~Discovery_SendFSM();
	
	/// Handle notifications on parent state changes
	virtual void setupNotifications();

	/// Action Methods


	/// Guard Methods

	
	
	Discovery_SendFSMContext *context;
	
protected:

    /// References to parent FSMs
	urn_jaus_jss_core_Transport_1_1::Transport_SendFSM* pTransport_SendFSM;
	urn_jaus_jss_core_Events_1_1::Events_SendFSM* pEvents_SendFSM;

    
};

};

#endif // DISCOVERY_SENDFSM_H
