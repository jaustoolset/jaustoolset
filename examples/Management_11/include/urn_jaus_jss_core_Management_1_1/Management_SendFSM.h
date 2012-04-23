

#ifndef MANAGEMENT_SENDFSM_H
#define MANAGEMENT_SENDFSM_H

#include "JausUtils.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JTSStateMachine.h"
#include "urn_jaus_jss_core_Management_1_1/Messages/MessageSet.h"
#include "urn_jaus_jss_core_Management_1_1/InternalEvents/InternalEventsSet.h"

typedef JTS::Receive_1_1 Receive;
typedef JTS::Send_1_1 Send;

#include "urn_jaus_jss_core_Transport_1_1/Transport_SendFSM.h"
#include "urn_jaus_jss_core_Events_1_1/Events_SendFSM.h"
#include "urn_jaus_jss_core_AccessControl_1_1/AccessControl_SendFSM.h"


#include "Management_SendFSM_sm.h"

namespace urn_jaus_jss_core_Management_1_1
{
	
class DllExport Management_SendFSM : public JTS::StateMachine
{
public:
	Management_SendFSM(urn_jaus_jss_core_Transport_1_1::Transport_SendFSM* pTransport_SendFSM, urn_jaus_jss_core_Events_1_1::Events_SendFSM* pEvents_SendFSM, urn_jaus_jss_core_AccessControl_1_1::AccessControl_SendFSM* pAccessControl_SendFSM);
	virtual ~Management_SendFSM();
	
	/// Handle notifications on parent state changes
	virtual void setupNotifications();

	/// Action Methods


	/// Guard Methods

	
	
	Management_SendFSMContext *context;
	
protected:

    /// References to parent FSMs
	urn_jaus_jss_core_Transport_1_1::Transport_SendFSM* pTransport_SendFSM;
	urn_jaus_jss_core_Events_1_1::Events_SendFSM* pEvents_SendFSM;
	urn_jaus_jss_core_AccessControl_1_1::AccessControl_SendFSM* pAccessControl_SendFSM;

    
};

};

#endif // MANAGEMENT_SENDFSM_H
