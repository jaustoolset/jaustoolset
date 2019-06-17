

#ifndef DISCOVERYCLIENT_RECEIVEFSM_H
#define DISCOVERYCLIENT_RECEIVEFSM_H

#include "JausUtils.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JTSStateMachine.h"
#include "urn_jts_DiscoveryClient_1_0/Messages/MessageSet.h"
#include "urn_jts_DiscoveryClient_1_0/InternalEvents/InternalEventsSet.h"

typedef JTS::Receive_1_1 Receive;
typedef JTS::Send_1_1 Send;

#include "urn_jaus_jss_core_Transport_1_1/Transport_ReceiveFSM.h"


#include "DiscoveryClient_ReceiveFSM_sm.h"

namespace urn_jts_DiscoveryClient_1_0
{
	
class DllExport DiscoveryClient_ReceiveFSM : public JTS::StateMachine
{
public:
	DiscoveryClient_ReceiveFSM(urn_jaus_jss_core_Transport_1_1::Transport_ReceiveFSM* pTransport_ReceiveFSM);
	virtual ~DiscoveryClient_ReceiveFSM();
	
	/// Handle notifications on parent state changes
	virtual void setupNotifications();

	/// Action Methods
	virtual void handleMessageAction(ReportIdentification msg, Receive::Body::ReceiveRec transportData);
	virtual void handleTimeoutAction();
	virtual void parseServiceListAction(ReportServices msg, Receive::Body::ReceiveRec transportData);
	virtual void registerServicesAction(Receive::Body::ReceiveRec transportData);
	virtual void updateNodeIDAction(GrantNodeID msg, Receive::Body::ReceiveRec transportData);
	virtual void updateSubsystemIDAction(Receive::Body::ReceiveRec transportData);
	virtual void sendQueryID();
	virtual void sendAllocatorRequest();


	/// Guard Methods
	virtual bool fromMasterModule(Receive::Body::ReceiveRec transportData);

	
	
	DiscoveryClient_ReceiveFSMContext *context;
	
protected:

    /// References to parent FSMs
	urn_jaus_jss_core_Transport_1_1::Transport_ReceiveFSM* pTransport_ReceiveFSM;

    
};

};

#endif // DISCOVERYCLIENT_RECEIVEFSM_H
