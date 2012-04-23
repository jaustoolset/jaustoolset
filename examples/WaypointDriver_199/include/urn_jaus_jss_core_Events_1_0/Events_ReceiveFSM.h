

#ifndef EVENTS_RECEIVEFSM_H
#define EVENTS_RECEIVEFSM_H

#include "JausUtils.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JTSStateMachine.h"
#include "urn_jaus_jss_core_Events_1_0/Messages/MessageSet.h"
#include "urn_jaus_jss_core_Events_1_0/InternalEvents/InternalEventsSet.h"

typedef JTS::Receive_1_0 Receive;
typedef JTS::Send_1_0 Send;

#include "urn_jaus_jss_core_Transport_1_0/Transport_ReceiveFSM.h"


#include "Events_ReceiveFSM_sm.h"

namespace urn_jaus_jss_core_Events_1_0
{
	
class DllExport Events_ReceiveFSM : public JTS::StateMachine
{
public:
	Events_ReceiveFSM(urn_jaus_jss_core_Transport_1_0::Transport_ReceiveFSM* pTransport_ReceiveFSM);
	virtual ~Events_ReceiveFSM();
	
	/// Handle notifications on parent state changes
	virtual void setupNotifications();

	/// Action Methods
	virtual void SendAction(std::string arg0, CancelEvent msg, Receive::Body::ReceiveRec transportData);
	virtual void SendAction(std::string arg0, CreateEvent msg, Receive::Body::ReceiveRec transportData);
	virtual void SendAction(std::string arg0, QueryEvents msg, Receive::Body::ReceiveRec transportData);
	virtual void SendAction(std::string arg0, UpdateEvent msg, Receive::Body::ReceiveRec transportData);
	virtual void cancelEventAction(CancelEvent msg);
	virtual void createEventAction(CreateEvent msg);
	virtual void sendEventAction();
	virtual void sendRejectEventRequestAction();
	virtual void updateEventAction(CreateEvent msg);
	virtual void updateEventAction(UpdateEvent msg);


	/// Guard Methods
	virtual bool eventExists();
	virtual bool eventExists(CancelEvent msg);
	virtual bool eventExists(CreateEvent msg);
	virtual bool eventExists(UpdateEvent msg);
	virtual bool isSupported(CreateEvent msg);
	virtual bool isSupported(UpdateEvent msg);

	
	
	Events_ReceiveFSMContext *context;
	
protected:

    /// References to parent FSMs
	urn_jaus_jss_core_Transport_1_0::Transport_ReceiveFSM* pTransport_ReceiveFSM;

    
};

};

#endif // EVENTS_RECEIVEFSM_H
