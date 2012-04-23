

#ifndef STILLIMAGESERVICE_H
#define STILLIMAGESERVICE_H

#include "Service.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JConstants.h"
#include "urn_jaus_jss_environmentSensing_StillImage_1_0/Messages/MessageSet.h"
#include "urn_jaus_jss_environmentSensing_StillImage_1_0/InternalEvents/InternalEventsSet.h"
#include "Transport/OS.h"

#include "urn_jaus_jss_core_Transport_1_0/TransportService.h"
#include "urn_jaus_jss_core_Events_1_0/EventsService.h"
#include "urn_jaus_jss_core_AccessControl_1_0/AccessControlService.h"
#include "urn_jaus_jss_environmentSensing_VisualSensor_1_0/VisualSensorService.h"
#include "StillImage_ReceiveFSM.h"
#include "StillImage_SendFSM.h"


namespace urn_jaus_jss_environmentSensing_StillImage_1_0
{
	
class DllExport StillImageService : public JTS::Service
{
public:
	StillImageService( JTS::JausRouter* jausRouter , urn_jaus_jss_core_Transport_1_0::TransportService* pTransportService, urn_jaus_jss_core_Events_1_0::EventsService* pEventsService, urn_jaus_jss_core_AccessControl_1_0::AccessControlService* pAccessControlService, urn_jaus_jss_environmentSensing_VisualSensor_1_0::VisualSensorService* pVisualSensorService);
	virtual ~StillImageService();
	
	virtual bool processTransitions(JTS::InternalEvent* ie);
	virtual bool defaultTransitions(JTS::InternalEvent* ie);
	
	// FSMs are public so that children services can access them
	StillImage_ReceiveFSM* pStillImage_ReceiveFSM;
	StillImage_SendFSM* pStillImage_SendFSM;
	


	
protected:
	virtual void run();

};

};

#endif // STILLIMAGESERVICE_H
