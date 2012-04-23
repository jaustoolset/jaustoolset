

#ifndef LOCALWAYPOINTLISTDRIVER_RECEIVEFSM_H
#define LOCALWAYPOINTLISTDRIVER_RECEIVEFSM_H

#include "JausUtils.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JTSStateMachine.h"
#include "urn_jaus_jss_mobility_LocalWaypointListDriver_1_0/Messages/MessageSet.h"
#include "urn_jaus_jss_mobility_LocalWaypointListDriver_1_0/InternalEvents/InternalEventsSet.h"

typedef JTS::Receive_1_0 Receive;
typedef JTS::Send_1_0 Send;

#include "urn_jaus_jss_core_Transport_1_0/Transport_ReceiveFSM.h"
#include "urn_jaus_jss_core_Events_1_0/Events_ReceiveFSM.h"
#include "urn_jaus_jss_core_AccessControl_1_0/AccessControl_ReceiveFSM.h"
#include "urn_jaus_jss_core_Management_1_0/Management_ReceiveFSM.h"
#include "urn_jaus_jss_mobility_ListManager_1_0/ListManager_ReceiveFSM.h"


#include "LocalWaypointListDriver_ReceiveFSM_sm.h"

namespace urn_jaus_jss_mobility_LocalWaypointListDriver_1_0
{
	
class DllExport LocalWaypointListDriver_ReceiveFSM : public JTS::StateMachine
{
public:
	LocalWaypointListDriver_ReceiveFSM(urn_jaus_jss_core_Transport_1_0::Transport_ReceiveFSM* pTransport_ReceiveFSM, urn_jaus_jss_core_Events_1_0::Events_ReceiveFSM* pEvents_ReceiveFSM, urn_jaus_jss_core_AccessControl_1_0::AccessControl_ReceiveFSM* pAccessControl_ReceiveFSM, urn_jaus_jss_core_Management_1_0::Management_ReceiveFSM* pManagement_ReceiveFSM, urn_jaus_jss_mobility_ListManager_1_0::ListManager_ReceiveFSM* pListManager_ReceiveFSM);
	virtual ~LocalWaypointListDriver_ReceiveFSM();
	
	/// Handle notifications on parent state changes
	virtual void setupNotifications();

	/// Action Methods
	virtual void SendAction(std::string arg0, Receive::Body::ReceiveRec transportData);
	virtual void executeWaypointListAction(ExecuteList msg);
	virtual void modifyTravelSpeedAction(ExecuteList msg);
	virtual void resetTravelSpeedAction();
	virtual void setElementAction(SetElement msg);


	/// Guard Methods
	virtual bool elementExists(ExecuteList msg);
	virtual bool elementSpecified(ExecuteList msg);
	virtual bool isControllingClient(Receive::Body::ReceiveRec transportData);
	virtual bool isElementSupported(SetElement msg);
	virtual bool isValidElementRequest(SetElement msg);
	virtual bool waypointExists(QueryLocalWaypoint msg);

	
	
	LocalWaypointListDriver_ReceiveFSMContext *context;
	
protected:

    /// References to parent FSMs
	urn_jaus_jss_core_Transport_1_0::Transport_ReceiveFSM* pTransport_ReceiveFSM;
	urn_jaus_jss_core_Events_1_0::Events_ReceiveFSM* pEvents_ReceiveFSM;
	urn_jaus_jss_core_AccessControl_1_0::AccessControl_ReceiveFSM* pAccessControl_ReceiveFSM;
	urn_jaus_jss_core_Management_1_0::Management_ReceiveFSM* pManagement_ReceiveFSM;
	urn_jaus_jss_mobility_ListManager_1_0::ListManager_ReceiveFSM* pListManager_ReceiveFSM;

    
};

};

#endif // LOCALWAYPOINTLISTDRIVER_RECEIVEFSM_H
