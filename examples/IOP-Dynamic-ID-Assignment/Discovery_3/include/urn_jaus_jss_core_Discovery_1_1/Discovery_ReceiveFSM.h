

#ifndef DISCOVERY_RECEIVEFSM_H
#define DISCOVERY_RECEIVEFSM_H

#include "JausUtils.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JTSStateMachine.h"
#include "urn_jaus_jss_core_Discovery_1_1/Messages/MessageSet.h"
#include "urn_jaus_jss_core_Discovery_1_1/InternalEvents/InternalEventsSet.h"

typedef JTS::Receive_1_1 Receive;
typedef JTS::Send_1_1 Send;

#include "urn_jaus_jss_core_Transport_1_1/Transport_ReceiveFSM.h"
#include "urn_jaus_jss_core_Events_1_1/Events_ReceiveFSM.h"


#include "Discovery_ReceiveFSM_sm.h"

namespace urn_jaus_jss_core_Discovery_1_1
{
	
class DllExport Discovery_ReceiveFSM : public JTS::StateMachine
{
public:
	Discovery_ReceiveFSM(urn_jaus_jss_core_Transport_1_1::Transport_ReceiveFSM* pTransport_ReceiveFSM, urn_jaus_jss_core_Events_1_1::Events_ReceiveFSM* pEvents_ReceiveFSM);
	virtual ~Discovery_ReceiveFSM();
	
	/// Handle notifications on parent state changes
	virtual void setupNotifications();

	/// Action Methods
	virtual void PublishServicesAction(RegisterServices msg, Receive::Body::ReceiveRec transportData);
	virtual void SendAction(QueryServiceList arg0, Receive::Body::ReceiveRec transportData);
	virtual void SendAction(QueryIdentification arg0, Receive::Body::ReceiveRec transportData);
	virtual void SendAction(QueryServices arg0, Receive::Body::ReceiveRec transportData);
	virtual void SendAction(QuerySubsystemList arg0, Receive::Body::ReceiveRec transportData);
	virtual void SendAction(QueryConfiguration arg0, Receive::Body::ReceiveRec transportData);


	/// Guard Methods

	
	
	Discovery_ReceiveFSMContext *context;
	
protected:

    /// References to parent FSMs
	urn_jaus_jss_core_Transport_1_1::Transport_ReceiveFSM* pTransport_ReceiveFSM;
	urn_jaus_jss_core_Events_1_1::Events_ReceiveFSM* pEvents_ReceiveFSM;

    
};

};

#endif // DISCOVERY_RECEIVEFSM_H
