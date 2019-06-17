

#include "Discovery.h"
#include "JausUtils.h"

using namespace JTS;
using namespace urn_jaus_jss_core_Transport_1_1;
using namespace urn_jaus_jss_core_Events_1_1;
using namespace urn_jaus_jss_core_Liveness_1_1;
using namespace urn_jaus_jss_iop_UnsolicitedHeartbeat_1_1;
using namespace urn_jaus_neya_SubsystemIDAllocatorClient_1_4;
using namespace urn_jaus_jss_iop_NodeIDAllocator_1_1;
using namespace urn_jaus_jss_core_Discovery_1_1;


Discovery::Discovery(unsigned int subsystem, unsigned short node, unsigned short component, bool allowWildcards)
{
	jausRouter = new JausRouter(JausAddress(subsystem, node, component), ieHandler, allowWildcards);
	
	/// Instantiate services
	TransportService* pTransportService = new TransportService(jausRouter);
	EventsService* pEventsService = new EventsService(jausRouter, pTransportService);
	LivenessService* pLivenessService = new LivenessService(jausRouter, pTransportService, pEventsService);
	UnsolicitedHeartbeatService* pUnsolicitedHeartbeatService = new UnsolicitedHeartbeatService(jausRouter, pTransportService, pEventsService, pLivenessService);
	SubsystemIDAllocatorClientService* pSubsystemIDAllocatorClientService = new SubsystemIDAllocatorClientService(jausRouter, pTransportService);
	NodeIDAllocatorService* pNodeIDAllocatorService = new NodeIDAllocatorService(jausRouter, pTransportService);
	DiscoveryService* pDiscoveryService = new DiscoveryService(jausRouter, pTransportService, pEventsService);
	
	
	/// Add all the Services for the Component
	serviceList.push_back(pTransportService);
	serviceList.push_back(pEventsService);
	serviceList.push_back(pLivenessService);
	serviceList.push_back(pUnsolicitedHeartbeatService);
	serviceList.push_back(pSubsystemIDAllocatorClientService);
	serviceList.push_back(pNodeIDAllocatorService);
	serviceList.push_back(pDiscoveryService);
	
}

Discovery::~Discovery()
{
	Service* service;
	
	while (!serviceList.empty())
	{
		service = serviceList.back();
		serviceList.pop_back();
		
		delete service;
	}
	
	delete jausRouter;
}


void Discovery::startComponent()
{
	Service* service;
	
	jausRouter->start();
	this->start();
	
	for (unsigned int i = 0; i < serviceList.size(); i++)
	{
		 service = serviceList.at(i);
		 service->start();
	}
}


void Discovery::shutdownComponent()
{
	Service* service;
	
	for (unsigned int i = 0; i < serviceList.size(); i++)
	{
		 service = serviceList.at(i);
		 service->stop();
	}
	
	this->stop();
	jausRouter->stop();
}

void Discovery::processInternalEvent(InternalEvent *ie)
{
    bool done = false;
	
	//
	// When a component receives an internal event, it passes it
	// to the services to handling, children services first.  If the
	// event is not processed by normal transitions, it's passed
	// again to the services (children first) for default transitions.
	// A given event may only be processed by at most one service.
	//
	for (unsigned int i = serviceList.size(); i>0; i--)
	{
		if (!done) done = serviceList.at(i-1)->processTransitions(ie);
	}
	for (unsigned int i = serviceList.size(); i>0; i--)
	{
		if (!done) done = serviceList.at(i-1)->defaultTransitions(ie);
	}
}



