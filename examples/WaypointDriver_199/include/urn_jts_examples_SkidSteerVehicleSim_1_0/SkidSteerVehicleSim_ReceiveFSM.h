

#ifndef SKIDSTEERVEHICLESIM_RECEIVEFSM_H
#define SKIDSTEERVEHICLESIM_RECEIVEFSM_H

#include "JausUtils.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JTSStateMachine.h"
#include "urn_jts_examples_SkidSteerVehicleSim_1_0/Messages/MessageSet.h"
#include "urn_jts_examples_SkidSteerVehicleSim_1_0/InternalEvents/InternalEventsSet.h"

typedef JTS::Receive_1_0 Receive;
typedef JTS::Send_1_0 Send;

#include "urn_jaus_jss_core_Transport_1_0/Transport_ReceiveFSM.h"
#include "urn_jaus_jss_core_Events_1_0/Events_ReceiveFSM.h"
#include "urn_jaus_jss_core_AccessControl_1_0/AccessControl_ReceiveFSM.h"
#include "urn_jaus_jss_core_Management_1_0/Management_ReceiveFSM.h"


#include "SkidSteerVehicleSim_ReceiveFSM_sm.h"

namespace urn_jts_examples_SkidSteerVehicleSim_1_0
{
	
class DllExport SkidSteerVehicleSim_ReceiveFSM : public JTS::StateMachine
{
public:
	SkidSteerVehicleSim_ReceiveFSM(urn_jaus_jss_core_Transport_1_0::Transport_ReceiveFSM* pTransport_ReceiveFSM, urn_jaus_jss_core_Events_1_0::Events_ReceiveFSM* pEvents_ReceiveFSM, urn_jaus_jss_core_AccessControl_1_0::AccessControl_ReceiveFSM* pAccessControl_ReceiveFSM, urn_jaus_jss_core_Management_1_0::Management_ReceiveFSM* pManagement_ReceiveFSM);
	virtual ~SkidSteerVehicleSim_ReceiveFSM();
	
	/// Handle notifications on parent state changes
	virtual void setupNotifications();

	/// Action Methods
	virtual void SendAction(std::string arg0, Receive::Body::ReceiveRec transportData);
	virtual void setWrenchEffortAction(SetWrenchEffort msg);


	/// Guard Methods
	virtual bool isControllingClient(Receive::Body::ReceiveRec transportData);

	
	
	SkidSteerVehicleSim_ReceiveFSMContext *context;
	
protected:

    /// References to parent FSMs
	urn_jaus_jss_core_Transport_1_0::Transport_ReceiveFSM* pTransport_ReceiveFSM;
	urn_jaus_jss_core_Events_1_0::Events_ReceiveFSM* pEvents_ReceiveFSM;
	urn_jaus_jss_core_AccessControl_1_0::AccessControl_ReceiveFSM* pAccessControl_ReceiveFSM;
	urn_jaus_jss_core_Management_1_0::Management_ReceiveFSM* pManagement_ReceiveFSM;

    
};

};

#endif // SKIDSTEERVEHICLESIM_RECEIVEFSM_H
