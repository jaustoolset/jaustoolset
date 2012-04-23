

#ifndef VISUALSENSORSERVICE_H
#define VISUALSENSORSERVICE_H

#include "Service.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JConstants.h"
#include "urn_jaus_jss_environmentSensing_VisualSensor_1_0/Messages/MessageSet.h"
#include "urn_jaus_jss_environmentSensing_VisualSensor_1_0/InternalEvents/InternalEventsSet.h"
#include "Transport/OS.h"

#include "urn_jaus_jss_core_Transport_1_0/TransportService.h"
#include "urn_jaus_jss_core_Events_1_0/EventsService.h"
#include "urn_jaus_jss_core_AccessControl_1_0/AccessControlService.h"
#include "VisualSensor_ReceiveFSM.h"
#include "VisualSensor_SendFSM.h"


namespace urn_jaus_jss_environmentSensing_VisualSensor_1_0
{
	
class DllExport VisualSensorService : public JTS::Service
{
public:
	VisualSensorService( JTS::JausRouter* jausRouter , urn_jaus_jss_core_Transport_1_0::TransportService* pTransportService, urn_jaus_jss_core_Events_1_0::EventsService* pEventsService, urn_jaus_jss_core_AccessControl_1_0::AccessControlService* pAccessControlService);
	virtual ~VisualSensorService();
	
	virtual bool processTransitions(JTS::InternalEvent* ie);
	virtual bool defaultTransitions(JTS::InternalEvent* ie);
	
	// FSMs are public so that children services can access them
	VisualSensor_ReceiveFSM* pVisualSensor_ReceiveFSM;
	VisualSensor_SendFSM* pVisualSensor_SendFSM;
	


	
protected:
	virtual void run();

};

};

#endif // VISUALSENSORSERVICE_H
