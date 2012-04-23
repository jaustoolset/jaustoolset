

#ifndef DISCOVERYSERVICE_H
#define DISCOVERYSERVICE_H

#include "Service.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JConstants.h"
#include "urn_jaus_jss_core_Discovery_1_0/Messages/MessageSet.h"
#include "urn_jaus_jss_core_Discovery_1_0/InternalEvents/InternalEventsSet.h"
#include "Transport/OS.h"

#include "urn_jaus_jss_core_Transport_1_0/TransportService.h"
#include "urn_jaus_jss_core_Events_1_0/EventsService.h"
#include "Discovery_ReceiveFSM.h"
#include "Discovery_SendFSM.h"


namespace urn_jaus_jss_core_Discovery_1_0
{
	
class DllExport DiscoveryService : public JTS::Service
{
public:
	DiscoveryService( JTS::JausRouter* jausRouter , urn_jaus_jss_core_Transport_1_0::TransportService* pTransportService, urn_jaus_jss_core_Events_1_0::EventsService* pEventsService);
	virtual ~DiscoveryService();
	
	virtual bool processTransitions(JTS::InternalEvent* ie);
	virtual bool defaultTransitions(JTS::InternalEvent* ie);
	
	// FSMs are public so that children services can access them
	Discovery_ReceiveFSM* pDiscovery_ReceiveFSM;
	Discovery_SendFSM* pDiscovery_SendFSM;
	


	
protected:
	virtual void run();

};

};

#endif // DISCOVERYSERVICE_H
