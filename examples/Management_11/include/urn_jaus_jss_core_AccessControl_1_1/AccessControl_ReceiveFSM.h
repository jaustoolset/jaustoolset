

#ifndef ACCESSCONTROL_RECEIVEFSM_H
#define ACCESSCONTROL_RECEIVEFSM_H

#include "JausUtils.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JTSStateMachine.h"
#include "urn_jaus_jss_core_AccessControl_1_1/Messages/MessageSet.h"
#include "urn_jaus_jss_core_AccessControl_1_1/InternalEvents/InternalEventsSet.h"

typedef JTS::Receive_1_1 Receive;
typedef JTS::Send_1_1 Send;

#include "urn_jaus_jss_core_Transport_1_1/Transport_ReceiveFSM.h"
#include "urn_jaus_jss_core_Events_1_1/Events_ReceiveFSM.h"


#include "AccessControl_ReceiveFSM_sm.h"

namespace urn_jaus_jss_core_AccessControl_1_1
{
	
class DllExport AccessControl_ReceiveFSM : public JTS::StateMachine
{
public:
	AccessControl_ReceiveFSM(urn_jaus_jss_core_Transport_1_1::Transport_ReceiveFSM* pTransport_ReceiveFSM, urn_jaus_jss_core_Events_1_1::Events_ReceiveFSM* pEvents_ReceiveFSM);
	virtual ~AccessControl_ReceiveFSM();
	
	/// Handle notifications on parent state changes
	virtual void setupNotifications();

	/// Action Methods
	virtual void ResetTimerAction();
	virtual void SendAction(std::string arg0, Receive::Body::ReceiveRec transportData);
	virtual void SendAction(std::string arg0, std::string arg1);
	virtual void SendAction(std::string arg0, std::string arg1, Receive::Body::ReceiveRec transportData);
	virtual void SetAuthorityAction(RequestControl msg);
	virtual void SetAuthorityAction(SetAuthority msg);
	virtual void StoreAddressAction(Receive::Body::ReceiveRec transportData);
	virtual void initAction();
	virtual void resetTimerAction();


	/// Guard Methods
	virtual bool isAuthorityValid(SetAuthority msg);
	virtual bool isControllingClient(Receive::Body::ReceiveRec transportData);
	virtual bool isCurrentAuthorityLess(RequestControl msg);
	virtual bool isDefaultAuthorityGreater(RequestControl msg);

	
	
	AccessControl_ReceiveFSMContext *context;
	
protected:

    /// References to parent FSMs
	urn_jaus_jss_core_Transport_1_1::Transport_ReceiveFSM* pTransport_ReceiveFSM;
	urn_jaus_jss_core_Events_1_1::Events_ReceiveFSM* pEvents_ReceiveFSM;

    
};

};

#endif // ACCESSCONTROL_RECEIVEFSM_H
