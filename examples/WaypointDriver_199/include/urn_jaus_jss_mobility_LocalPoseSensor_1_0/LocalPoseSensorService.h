

#ifndef LOCALPOSESENSORSERVICE_H
#define LOCALPOSESENSORSERVICE_H

#include "Service.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JConstants.h"
#include "urn_jaus_jss_mobility_LocalPoseSensor_1_0/Messages/MessageSet.h"
#include "urn_jaus_jss_mobility_LocalPoseSensor_1_0/InternalEvents/InternalEventsSet.h"
#include "Transport/OS.h"

#include "urn_jaus_jss_core_Transport_1_0/TransportService.h"
#include "urn_jaus_jss_core_Events_1_0/EventsService.h"
#include "urn_jaus_jss_core_AccessControl_1_0/AccessControlService.h"
#include "LocalPoseSensor_ReceiveFSM.h"
#include "LocalPoseSensor_SendFSM.h"


namespace urn_jaus_jss_mobility_LocalPoseSensor_1_0
{
	
class DllExport LocalPoseSensorService : public JTS::Service
{
public:
	LocalPoseSensorService( JTS::JausRouter* jausRouter , urn_jaus_jss_core_Transport_1_0::TransportService* pTransportService, urn_jaus_jss_core_Events_1_0::EventsService* pEventsService, urn_jaus_jss_core_AccessControl_1_0::AccessControlService* pAccessControlService);
	virtual ~LocalPoseSensorService();
	
	virtual bool processTransitions(JTS::InternalEvent* ie);
	virtual bool defaultTransitions(JTS::InternalEvent* ie);
	
	// FSMs are public so that children services can access them
	LocalPoseSensor_ReceiveFSM* pLocalPoseSensor_ReceiveFSM;
	LocalPoseSensor_SendFSM* pLocalPoseSensor_SendFSM;
	


	
protected:
	virtual void run();

};

};

#endif // LOCALPOSESENSORSERVICE_H
