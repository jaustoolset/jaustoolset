

#include "PingComponent.h"
#include "JausUtils.h"

using namespace JTS;
using namespace urn_jts_PingServer_1_0;
using namespace urn_jts_PingClient_1_0;


PingComponent::PingComponent(unsigned int subsystem, unsigned short node, unsigned short component)
{
	jausRouter = new JausRouter(JausAddress(subsystem, node, component), ieHandler);
	
	/// Instantiate services
	PingServerService* pPingServerService = new PingServerService(jausRouter);
	PingClientService* pPingClientService = new PingClientService(jausRouter);
	
	
	/// Add all the Services for the Component
	serviceList.push_back(pPingServerService);
	serviceList.push_back(pPingClientService);
	
}

PingComponent::~PingComponent()
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


void PingComponent::startComponent()
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


void PingComponent::shutdownComponent()
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

void PingComponent::processInternalEvent(InternalEvent *ie)
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



