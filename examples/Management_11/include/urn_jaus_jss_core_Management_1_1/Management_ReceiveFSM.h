

#ifndef MANAGEMENT_RECEIVEFSM_H
#define MANAGEMENT_RECEIVEFSM_H

#include "JausUtils.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JTSStateMachine.h"
#include "urn_jaus_jss_core_Management_1_1/Messages/MessageSet.h"
#include "urn_jaus_jss_core_Management_1_1/InternalEvents/InternalEventsSet.h"

typedef JTS::Receive_1_1 Receive;
typedef JTS::Send_1_1 Send;

#include "urn_jaus_jss_core_Transport_1_1/Transport_ReceiveFSM.h"
#include "urn_jaus_jss_core_Events_1_1/Events_ReceiveFSM.h"
#include "urn_jaus_jss_core_AccessControl_1_1/AccessControl_ReceiveFSM.h"


#include "Management_ReceiveFSM_sm.h"

namespace urn_jaus_jss_core_Management_1_1
{
	
class DllExport Management_ReceiveFSM : public JTS::StateMachine
{
public:
	Management_ReceiveFSM(urn_jaus_jss_core_Transport_1_1::Transport_ReceiveFSM* pTransport_ReceiveFSM, urn_jaus_jss_core_Events_1_1::Events_ReceiveFSM* pEvents_ReceiveFSM, urn_jaus_jss_core_AccessControl_1_1::AccessControl_ReceiveFSM* pAccessControl_ReceiveFSM);
	virtual ~Management_ReceiveFSM();
	
	/// Handle notifications on parent state changes
	virtual void setupNotifications();

	/// Action Methods
	virtual void DeleteIDAction(Receive::Body::ReceiveRec transportData);
	virtual void SendAction(std::string arg0, Receive::Body::ReceiveRec transportData);
	virtual void SendAction(std::string arg0, std::string arg1);
	virtual void SendAction(std::string arg0, std::string arg1, Receive::Body::ReceiveRec transportData);
	virtual void StoreIDAction(Receive::Body::ReceiveRec transportData);
	virtual void initializeAction();
	virtual void popWrapper_367bc5868aa53b23a6c5a07701058fbf(ClearEmergency msg, Receive::Body::ReceiveRec transportData);
	virtual void popWrapper_1014a7ff54cb3a51af2f80235d77d9d1(ClearEmergency msg, Receive::Body::ReceiveRec transportData);


	/// Guard Methods
	virtual bool isControllingClient(Receive::Body::ReceiveRec transportData);
	virtual bool isIDStored(Receive::Body::ReceiveRec transportData);

	
	
	Management_ReceiveFSMContext *context;
	
protected:

    /// References to parent FSMs
	urn_jaus_jss_core_Transport_1_1::Transport_ReceiveFSM* pTransport_ReceiveFSM;
	urn_jaus_jss_core_Events_1_1::Events_ReceiveFSM* pEvents_ReceiveFSM;
	urn_jaus_jss_core_AccessControl_1_1::AccessControl_ReceiveFSM* pAccessControl_ReceiveFSM;

    
};

};

#endif // MANAGEMENT_RECEIVEFSM_H
