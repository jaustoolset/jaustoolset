

#ifndef LISTMANAGER_RECEIVEFSM_H
#define LISTMANAGER_RECEIVEFSM_H

#include "JausUtils.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JTSStateMachine.h"
#include "urn_jaus_jss_mobility_ListManager_1_0/Messages/MessageSet.h"
#include "urn_jaus_jss_mobility_ListManager_1_0/InternalEvents/InternalEventsSet.h"

typedef JTS::Receive_1_0 Receive;
typedef JTS::Send_1_0 Send;

#include "urn_jaus_jss_core_Transport_1_0/Transport_ReceiveFSM.h"
#include "urn_jaus_jss_core_Events_1_0/Events_ReceiveFSM.h"
#include "urn_jaus_jss_core_AccessControl_1_0/AccessControl_ReceiveFSM.h"
#include "urn_jaus_jss_core_Management_1_0/Management_ReceiveFSM.h"


#include "ListManager_ReceiveFSM_sm.h"

namespace urn_jaus_jss_mobility_ListManager_1_0
{
	
class DllExport ListManager_ReceiveFSM : public JTS::StateMachine
{
public:
	ListManager_ReceiveFSM(urn_jaus_jss_core_Transport_1_0::Transport_ReceiveFSM* pTransport_ReceiveFSM, urn_jaus_jss_core_Events_1_0::Events_ReceiveFSM* pEvents_ReceiveFSM, urn_jaus_jss_core_AccessControl_1_0::AccessControl_ReceiveFSM* pAccessControl_ReceiveFSM, urn_jaus_jss_core_Management_1_0::Management_ReceiveFSM* pManagement_ReceiveFSM);
	virtual ~ListManager_ReceiveFSM();
	
	/// Handle notifications on parent state changes
	virtual void setupNotifications();

	/// Action Methods
	virtual void SendAction(std::string arg0, Receive::Body::ReceiveRec transportData);
	virtual void deleteElementAction();
	virtual void setElementAction(SetElement msg);


	/// Guard Methods
	virtual bool elementExists(DeleteElement msg);
	virtual bool elementExists(QueryElement msg);
	virtual bool isControllingClient(Receive::Body::ReceiveRec transportData);
	virtual bool isElementSupported(SetElement msg);
	virtual bool isValidElementRequest(SetElement msg);

	
	
	ListManager_ReceiveFSMContext *context;
	
protected:

    /// References to parent FSMs
	urn_jaus_jss_core_Transport_1_0::Transport_ReceiveFSM* pTransport_ReceiveFSM;
	urn_jaus_jss_core_Events_1_0::Events_ReceiveFSM* pEvents_ReceiveFSM;
	urn_jaus_jss_core_AccessControl_1_0::AccessControl_ReceiveFSM* pAccessControl_ReceiveFSM;
	urn_jaus_jss_core_Management_1_0::Management_ReceiveFSM* pManagement_ReceiveFSM;

    
};

};

#endif // LISTMANAGER_RECEIVEFSM_H
