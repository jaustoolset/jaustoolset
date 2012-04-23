

#ifndef VISUALSENSOR_SENDFSM_H
#define VISUALSENSOR_SENDFSM_H

#include "JausUtils.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JTSStateMachine.h"
#include "urn_jaus_jss_environmentSensing_VisualSensor_1_0/Messages/MessageSet.h"
#include "urn_jaus_jss_environmentSensing_VisualSensor_1_0/InternalEvents/InternalEventsSet.h"

typedef JTS::Receive_1_0 Receive;
typedef JTS::Send_1_0 Send;

#include "urn_jaus_jss_core_Transport_1_0/Transport_SendFSM.h"
#include "urn_jaus_jss_core_Events_1_0/Events_SendFSM.h"
#include "urn_jaus_jss_core_AccessControl_1_0/AccessControl_SendFSM.h"


#include "VisualSensor_SendFSM_sm.h"

namespace urn_jaus_jss_environmentSensing_VisualSensor_1_0
{
	
class DllExport VisualSensor_SendFSM : public JTS::StateMachine
{
public:
	VisualSensor_SendFSM(urn_jaus_jss_core_Transport_1_0::Transport_SendFSM* pTransport_SendFSM, urn_jaus_jss_core_Events_1_0::Events_SendFSM* pEvents_SendFSM, urn_jaus_jss_core_AccessControl_1_0::AccessControl_SendFSM* pAccessControl_SendFSM);
	virtual ~VisualSensor_SendFSM();
	
	/// Handle notifications on parent state changes
	virtual void setupNotifications();

	/// Action Methods


	/// Guard Methods

	
	
	VisualSensor_SendFSMContext *context;
	
protected:

    /// References to parent FSMs
	urn_jaus_jss_core_Transport_1_0::Transport_SendFSM* pTransport_SendFSM;
	urn_jaus_jss_core_Events_1_0::Events_SendFSM* pEvents_SendFSM;
	urn_jaus_jss_core_AccessControl_1_0::AccessControl_SendFSM* pAccessControl_SendFSM;

    
};

};

#endif // VISUALSENSOR_SENDFSM_H
