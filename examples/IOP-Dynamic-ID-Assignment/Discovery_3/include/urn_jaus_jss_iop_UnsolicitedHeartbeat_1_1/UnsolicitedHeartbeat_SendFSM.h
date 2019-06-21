

#ifndef UNSOLICITEDHEARTBEAT_SENDFSM_H
#define UNSOLICITEDHEARTBEAT_SENDFSM_H

#include "JausUtils.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JTSStateMachine.h"
#include "urn_jaus_jss_iop_UnsolicitedHeartbeat_1_1/Messages/MessageSet.h"
#include "urn_jaus_jss_iop_UnsolicitedHeartbeat_1_1/InternalEvents/InternalEventsSet.h"

typedef JTS::Receive_1_1 Receive;
typedef JTS::Send_1_1 Send;

#include "urn_jaus_jss_core_Transport_1_1/Transport_SendFSM.h"
#include "urn_jaus_jss_core_Events_1_1/Events_SendFSM.h"
#include "urn_jaus_jss_core_Liveness_1_1/Liveness_SendFSM.h"


#include "UnsolicitedHeartbeat_SendFSM_sm.h"

namespace urn_jaus_jss_iop_UnsolicitedHeartbeat_1_1
{
	
class DllExport UnsolicitedHeartbeat_SendFSM : public JTS::StateMachine
{
public:
	UnsolicitedHeartbeat_SendFSM(urn_jaus_jss_core_Transport_1_1::Transport_SendFSM* pTransport_SendFSM, urn_jaus_jss_core_Events_1_1::Events_SendFSM* pEvents_SendFSM, urn_jaus_jss_core_Liveness_1_1::Liveness_SendFSM* pLiveness_SendFSM);
	virtual ~UnsolicitedHeartbeat_SendFSM();
	
	/// Handle notifications on parent state changes
	virtual void setupNotifications();

	/// Action Methods


	/// Guard Methods

	
	
	UnsolicitedHeartbeat_SendFSMContext *context;
	
protected:

    /// References to parent FSMs
	urn_jaus_jss_core_Transport_1_1::Transport_SendFSM* pTransport_SendFSM;
	urn_jaus_jss_core_Events_1_1::Events_SendFSM* pEvents_SendFSM;
	urn_jaus_jss_core_Liveness_1_1::Liveness_SendFSM* pLiveness_SendFSM;

    
};

};

#endif // UNSOLICITEDHEARTBEAT_SENDFSM_H
