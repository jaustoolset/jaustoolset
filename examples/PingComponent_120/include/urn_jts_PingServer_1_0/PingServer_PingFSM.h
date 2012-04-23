

#ifndef PINGSERVER_PINGFSM_H
#define PINGSERVER_PINGFSM_H

#include "JausUtils.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JTSStateMachine.h"
#include "urn_jts_PingServer_1_0/Messages/MessageSet.h"
#include "urn_jts_PingServer_1_0/InternalEvents/InternalEventsSet.h"

typedef JTS::Receive_1_0 Receive;
typedef JTS::Send_1_0 Send;



#include "PingServer_PingFSM_sm.h"

namespace urn_jts_PingServer_1_0
{
	
class DllExport PingServer_PingFSM : public JTS::StateMachine
{
public:
	PingServer_PingFSM();
	virtual ~PingServer_PingFSM();
	
	/// Handle notifications on parent state changes
	virtual void setupNotifications();

	/// Action Methods
	virtual void ReportHeartbeatPulseAction();


	/// Guard Methods

	
	
	PingServer_PingFSMContext *context;
	
protected:

    /// References to parent FSMs

    
};

};

#endif // PINGSERVER_PINGFSM_H
