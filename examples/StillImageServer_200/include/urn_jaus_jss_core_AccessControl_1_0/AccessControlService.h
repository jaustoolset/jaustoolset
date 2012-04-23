

#ifndef ACCESSCONTROLSERVICE_H
#define ACCESSCONTROLSERVICE_H

#include "Service.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JConstants.h"
#include "urn_jaus_jss_core_AccessControl_1_0/Messages/MessageSet.h"
#include "urn_jaus_jss_core_AccessControl_1_0/InternalEvents/InternalEventsSet.h"
#include "Transport/OS.h"

#include "urn_jaus_jss_core_Transport_1_0/TransportService.h"
#include "urn_jaus_jss_core_Events_1_0/EventsService.h"
#include "AccessControl_ReceiveFSM.h"
#include "AccessControl_SendFSM.h"


namespace urn_jaus_jss_core_AccessControl_1_0
{
	
class DllExport AccessControlService : public JTS::Service
{
public:
	AccessControlService( JTS::JausRouter* jausRouter , urn_jaus_jss_core_Transport_1_0::TransportService* pTransportService, urn_jaus_jss_core_Events_1_0::EventsService* pEventsService);
	virtual ~AccessControlService();
	
	virtual bool processTransitions(JTS::InternalEvent* ie);
	virtual bool defaultTransitions(JTS::InternalEvent* ie);
	
	// FSMs are public so that children services can access them
	AccessControl_ReceiveFSM* pAccessControl_ReceiveFSM;
	AccessControl_SendFSM* pAccessControl_SendFSM;
	


	
protected:
	virtual void run();

};

};

#endif // ACCESSCONTROLSERVICE_H
