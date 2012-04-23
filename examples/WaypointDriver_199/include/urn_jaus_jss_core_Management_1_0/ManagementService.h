

#ifndef MANAGEMENTSERVICE_H
#define MANAGEMENTSERVICE_H

#include "Service.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JConstants.h"
#include "urn_jaus_jss_core_Management_1_0/Messages/MessageSet.h"
#include "urn_jaus_jss_core_Management_1_0/InternalEvents/InternalEventsSet.h"
#include "Transport/OS.h"

#include "urn_jaus_jss_core_Transport_1_0/TransportService.h"
#include "urn_jaus_jss_core_Events_1_0/EventsService.h"
#include "urn_jaus_jss_core_AccessControl_1_0/AccessControlService.h"
#include "Management_ReceiveFSM.h"
#include "Management_SendFSM.h"


namespace urn_jaus_jss_core_Management_1_0
{
	
class DllExport ManagementService : public JTS::Service
{
public:
	ManagementService( JTS::JausRouter* jausRouter , urn_jaus_jss_core_Transport_1_0::TransportService* pTransportService, urn_jaus_jss_core_Events_1_0::EventsService* pEventsService, urn_jaus_jss_core_AccessControl_1_0::AccessControlService* pAccessControlService);
	virtual ~ManagementService();
	
	virtual bool processTransitions(JTS::InternalEvent* ie);
	virtual bool defaultTransitions(JTS::InternalEvent* ie);
	
	// FSMs are public so that children services can access them
	Management_ReceiveFSM* pManagement_ReceiveFSM;
	Management_SendFSM* pManagement_SendFSM;
	


	
protected:
	virtual void run();

};

};

#endif // MANAGEMENTSERVICE_H
