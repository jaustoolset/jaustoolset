

#ifndef TRANSPORT_SENDFSM_H
#define TRANSPORT_SENDFSM_H

#include "JausUtils.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JTSStateMachine.h"
#include "urn_jaus_jss_core_Transport_1_1/Messages/MessageSet.h"
#include "urn_jaus_jss_core_Transport_1_1/InternalEvents/InternalEventsSet.h"

typedef JTS::Receive_1_1 Receive;
typedef JTS::Send_1_1 Send;



#include "Transport_SendFSM_sm.h"

namespace urn_jaus_jss_core_Transport_1_1
{
	
class DllExport Transport_SendFSM : public JTS::StateMachine
{
public:
	Transport_SendFSM();
	virtual ~Transport_SendFSM();
	
	/// Handle notifications on parent state changes
	virtual void setupNotifications();

	/// Action Methods
	virtual void BroadcastGlobalEnqueueAction(BroadcastGlobal msg);
	virtual void BroadcastLocalEnqueueAction(BroadcastLocal msg);
	virtual void EnqueueAction(Send msg);


	/// Guard Methods

	
	
	Transport_SendFSMContext *context;
	
protected:

    /// References to parent FSMs

    
};

};

#endif // TRANSPORT_SENDFSM_H
