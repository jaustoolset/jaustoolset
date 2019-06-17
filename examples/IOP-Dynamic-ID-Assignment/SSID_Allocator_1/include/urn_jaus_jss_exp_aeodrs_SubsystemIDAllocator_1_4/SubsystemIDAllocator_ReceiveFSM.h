

#ifndef SUBSYSTEMIDALLOCATOR_RECEIVEFSM_H
#define SUBSYSTEMIDALLOCATOR_RECEIVEFSM_H

#include "JausUtils.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JTSStateMachine.h"
#include "urn_jaus_jss_exp_aeodrs_SubsystemIDAllocator_1_4/Messages/MessageSet.h"
#include "urn_jaus_jss_exp_aeodrs_SubsystemIDAllocator_1_4/InternalEvents/InternalEventsSet.h"

typedef JTS::Receive_1_1 Receive;
typedef JTS::Send_1_1 Send;

#include "urn_jaus_jss_core_Transport_1_1/Transport_ReceiveFSM.h"


#include "SubsystemIDAllocator_ReceiveFSM_sm.h"

namespace urn_jaus_jss_exp_aeodrs_SubsystemIDAllocator_1_4
{
	
class DllExport SubsystemIDAllocator_ReceiveFSM : public JTS::StateMachine
{
public:
	SubsystemIDAllocator_ReceiveFSM(urn_jaus_jss_core_Transport_1_1::Transport_ReceiveFSM* pTransport_ReceiveFSM);
	virtual ~SubsystemIDAllocator_ReceiveFSM();
	
	/// Handle notifications on parent state changes
	virtual void setupNotifications();

	/// Action Methods
	virtual void sendGrantSubsystemIDAction(RequestSubsystemID msg, Receive::Body::ReceiveRec transportData);
	virtual void sendReportSubsystemIDsAction(QuerySubsystemIDs msg, Receive::Body::ReceiveRec transportData);


	/// Guard Methods

	
	
	SubsystemIDAllocator_ReceiveFSMContext *context;
	
protected:

    /// References to parent FSMs
	urn_jaus_jss_core_Transport_1_1::Transport_ReceiveFSM* pTransport_ReceiveFSM;

    
};

};

#endif // SUBSYSTEMIDALLOCATOR_RECEIVEFSM_H
