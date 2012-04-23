

#ifndef TRANSPORT_RECEIVEFSM_H
#define TRANSPORT_RECEIVEFSM_H

#include "JausUtils.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JTSStateMachine.h"
#include "urn_jaus_jss_core_Transport_1_1/Messages/MessageSet.h"
#include "urn_jaus_jss_core_Transport_1_1/InternalEvents/InternalEventsSet.h"

typedef JTS::Receive_1_1 Receive;
typedef JTS::Send_1_1 Send;



#include "Transport_ReceiveFSM_sm.h"

namespace urn_jaus_jss_core_Transport_1_1
{
	
class DllExport Transport_ReceiveFSM : public JTS::StateMachine
{
public:
	Transport_ReceiveFSM();
	virtual ~Transport_ReceiveFSM();
	
	/// Handle notifications on parent state changes
	virtual void setupNotifications();

	/// Action Methods


	/// Guard Methods

	
	
	Transport_ReceiveFSMContext *context;
	
protected:

    /// References to parent FSMs

    
};

};

#endif // TRANSPORT_RECEIVEFSM_H
