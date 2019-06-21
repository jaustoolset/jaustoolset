

#ifndef UNSOLICITEDHEARTBEAT_RECEIVEFSM_H
#define UNSOLICITEDHEARTBEAT_RECEIVEFSM_H

#include "JausUtils.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JTSStateMachine.h"
#include "urn_jaus_jss_iop_UnsolicitedHeartbeat_1_1/Messages/MessageSet.h"
#include "urn_jaus_jss_iop_UnsolicitedHeartbeat_1_1/InternalEvents/InternalEventsSet.h"

typedef JTS::Receive_1_1 Receive;
typedef JTS::Send_1_1 Send;

#include "urn_jaus_jss_core_Transport_1_1/Transport_ReceiveFSM.h"
#include "urn_jaus_jss_core_Events_1_1/Events_ReceiveFSM.h"
#include "urn_jaus_jss_core_Liveness_1_1/Liveness_ReceiveFSM.h"


#include "UnsolicitedHeartbeat_ReceiveFSM_sm.h"

namespace urn_jaus_jss_iop_UnsolicitedHeartbeat_1_1
{
	
class DllExport UnsolicitedHeartbeat_ReceiveFSM : public JTS::StateMachine
{
public:
	UnsolicitedHeartbeat_ReceiveFSM(urn_jaus_jss_core_Transport_1_1::Transport_ReceiveFSM* pTransport_ReceiveFSM, urn_jaus_jss_core_Events_1_1::Events_ReceiveFSM* pEvents_ReceiveFSM, urn_jaus_jss_core_Liveness_1_1::Liveness_ReceiveFSM* pLiveness_ReceiveFSM);
	virtual ~UnsolicitedHeartbeat_ReceiveFSM();
	
	/// Handle notifications on parent state changes
	virtual void setupNotifications();

	/// Action Methods
	virtual void broadcastReportHeartBeatPulseAction();


	/// Guard Methods
	virtual bool hasJAUS_ID();

	
	
	UnsolicitedHeartbeat_ReceiveFSMContext *context;
	
protected:

    /// References to parent FSMs
	urn_jaus_jss_core_Transport_1_1::Transport_ReceiveFSM* pTransport_ReceiveFSM;
	urn_jaus_jss_core_Events_1_1::Events_ReceiveFSM* pEvents_ReceiveFSM;
	urn_jaus_jss_core_Liveness_1_1::Liveness_ReceiveFSM* pLiveness_ReceiveFSM;

    
};

};

#endif // UNSOLICITEDHEARTBEAT_RECEIVEFSM_H
