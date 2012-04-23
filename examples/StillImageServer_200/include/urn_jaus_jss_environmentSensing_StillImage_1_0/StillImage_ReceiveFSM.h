

#ifndef STILLIMAGE_RECEIVEFSM_H
#define STILLIMAGE_RECEIVEFSM_H

#include "JausUtils.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JTSStateMachine.h"
#include "urn_jaus_jss_environmentSensing_StillImage_1_0/Messages/MessageSet.h"
#include "urn_jaus_jss_environmentSensing_StillImage_1_0/InternalEvents/InternalEventsSet.h"

typedef JTS::Receive_1_0 Receive;
typedef JTS::Send_1_0 Send;

#include "urn_jaus_jss_core_Transport_1_0/Transport_ReceiveFSM.h"
#include "urn_jaus_jss_core_Events_1_0/Events_ReceiveFSM.h"
#include "urn_jaus_jss_core_AccessControl_1_0/AccessControl_ReceiveFSM.h"
#include "urn_jaus_jss_environmentSensing_VisualSensor_1_0/VisualSensor_ReceiveFSM.h"


#include "StillImage_ReceiveFSM_sm.h"

namespace urn_jaus_jss_environmentSensing_StillImage_1_0
{
	
class DllExport StillImage_ReceiveFSM : public JTS::StateMachine
{
public:
	StillImage_ReceiveFSM(urn_jaus_jss_core_Transport_1_0::Transport_ReceiveFSM* pTransport_ReceiveFSM, urn_jaus_jss_core_Events_1_0::Events_ReceiveFSM* pEvents_ReceiveFSM, urn_jaus_jss_core_AccessControl_1_0::AccessControl_ReceiveFSM* pAccessControl_ReceiveFSM, urn_jaus_jss_environmentSensing_VisualSensor_1_0::VisualSensor_ReceiveFSM* pVisualSensor_ReceiveFSM);
	virtual ~StillImage_ReceiveFSM();
	
	/// Handle notifications on parent state changes
	virtual void setupNotifications();

	/// Action Methods
	virtual void SendAction(std::string arg0, Receive::Body::ReceiveRec transportData);
	virtual void updateStillImageSensorConfigurationAction();


	/// Guard Methods
	virtual bool isControllingClient(Receive::Body::ReceiveRec transportData);
	virtual bool isCoordinateTransformSupported();

	
	
	StillImage_ReceiveFSMContext *context;
	
protected:

    /// References to parent FSMs
	urn_jaus_jss_core_Transport_1_0::Transport_ReceiveFSM* pTransport_ReceiveFSM;
	urn_jaus_jss_core_Events_1_0::Events_ReceiveFSM* pEvents_ReceiveFSM;
	urn_jaus_jss_core_AccessControl_1_0::AccessControl_ReceiveFSM* pAccessControl_ReceiveFSM;
	urn_jaus_jss_environmentSensing_VisualSensor_1_0::VisualSensor_ReceiveFSM* pVisualSensor_ReceiveFSM;

    
};

};

#endif // STILLIMAGE_RECEIVEFSM_H
