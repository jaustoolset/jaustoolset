

#include "StillImageServer.h"
#include "JausUtils.h"

using namespace JTS;
using namespace urn_jaus_jss_core_Transport_1_0;
using namespace urn_jaus_jss_core_Events_1_0;
using namespace urn_jaus_jss_core_AccessControl_1_0;
using namespace urn_jaus_jss_environmentSensing_VisualSensor_1_0;
using namespace urn_jaus_jss_environmentSensing_StillImage_1_0;


StillImageServer::StillImageServer(unsigned int subsystem, unsigned short node, unsigned short component)
{
	jausRouter = new JausRouter(JausAddress(subsystem, node, component), ieHandler);
	
	/// Instantiate services
	TransportService* pTransportService = new TransportService(jausRouter);
	EventsService* pEventsService = new EventsService(jausRouter, pTransportService);
	AccessControlService* pAccessControlService = new AccessControlService(jausRouter, pTransportService, pEventsService);
	VisualSensorService* pVisualSensorService = new VisualSensorService(jausRouter, pTransportService, pEventsService, pAccessControlService);
	StillImageService* pStillImageService = new StillImageService(jausRouter, pTransportService, pEventsService, pAccessControlService, pVisualSensorService);
	
	
	/// Add all the Services for the Component
	serviceList.push_back(pTransportService);
	serviceList.push_back(pEventsService);
	serviceList.push_back(pAccessControlService);
	serviceList.push_back(pVisualSensorService);
	serviceList.push_back(pStillImageService);
	
}

StillImageServer::~StillImageServer()
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


void StillImageServer::startComponent()
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


void StillImageServer::shutdownComponent()
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

void StillImageServer::processInternalEvent(InternalEvent *ie)
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



