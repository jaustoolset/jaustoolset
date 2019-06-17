

#ifndef DISCOVERYCLIENTSERVICE_H
#define DISCOVERYCLIENTSERVICE_H

#include "Service.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JConstants.h"
#include "urn_jts_DiscoveryClient_1_0/Messages/MessageSet.h"
#include "urn_jts_DiscoveryClient_1_0/InternalEvents/InternalEventsSet.h"
#include "Transport/OS.h"

#include "urn_jaus_jss_core_Transport_1_1/TransportService.h"
#include "DiscoveryClient_ReceiveFSM.h"
#include "DiscoveryClient_SendFSM.h"


namespace urn_jts_DiscoveryClient_1_0
{
	
class DllExport DiscoveryClientService : public JTS::Service
{
public:
	DiscoveryClientService( JTS::JausRouter* jausRouter , urn_jaus_jss_core_Transport_1_1::TransportService* pTransportService);
	virtual ~DiscoveryClientService();
	
	virtual bool processTransitions(JTS::InternalEvent* ie);
	virtual bool defaultTransitions(JTS::InternalEvent* ie);
	
	// FSMs are public so that children services can access them
	DiscoveryClient_ReceiveFSM* pDiscoveryClient_ReceiveFSM;
	DiscoveryClient_SendFSM* pDiscoveryClient_SendFSM;
	


	
protected:
	virtual void run();

};

};

#endif // DISCOVERYCLIENTSERVICE_H
