

#ifndef PINGCLIENT_PINGCLIENTFSM_H
#define PINGCLIENT_PINGCLIENTFSM_H

#include "JausUtils.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JTSStateMachine.h"
#include "urn_jts_PingClient_1_0/Messages/MessageSet.h"
#include "urn_jts_PingClient_1_0/InternalEvents/InternalEventsSet.h"

typedef JTS::Receive_1_0 Receive;
typedef JTS::Send_1_0 Send;



#include "PingClient_PingClientFSM_sm.h"

namespace urn_jts_PingClient_1_0
{
	
class DllExport PingClient_PingClientFSM : public JTS::StateMachine
{
public:
	PingClient_PingClientFSM();
	virtual ~PingClient_PingClientFSM();
	
	/// Handle notifications on parent state changes
	virtual void setupNotifications();

	/// Action Methods
	virtual void QueryHeartBeatPulseAction();
	virtual void printToScreenAction();


	/// Guard Methods

	
	
	PingClient_PingClientFSMContext *context;
	
protected:

    /// References to parent FSMs

    
};

};

#endif // PINGCLIENT_PINGCLIENTFSM_H
