

#include "WaypointDriver.h"
#include "JausUtils.h"

using namespace JTS;
using namespace urn_jaus_jss_core_Transport_1_0;
using namespace urn_jaus_jss_core_Events_1_0;
using namespace urn_jaus_jss_core_AccessControl_1_0;
using namespace urn_jaus_jss_core_Discovery_1_0;
using namespace urn_jaus_jss_core_Management_1_0;
using namespace urn_jaus_jss_mobility_ListManager_1_0;
using namespace urn_jaus_jss_mobility_LocalPoseSensor_1_0;
using namespace urn_jaus_jss_mobility_LocalWaypointListDriver_1_0;
using namespace urn_jts_examples_SkidSteerVehicleSim_1_0;


WaypointDriver::WaypointDriver(unsigned int subsystem, unsigned short node, unsigned short component)
{
	jausRouter = new JausRouter(JausAddress(subsystem, node, component), ieHandler);
	
	/// Instantiate services
	TransportService* pTransportService = new TransportService(jausRouter);
	EventsService* pEventsService = new EventsService(jausRouter, pTransportService);
	AccessControlService* pAccessControlService = new AccessControlService(jausRouter, pTransportService, pEventsService);
	DiscoveryService* pDiscoveryService = new DiscoveryService(jausRouter, pTransportService, pEventsService);
	ManagementService* pManagementService = new ManagementService(jausRouter, pTransportService, pEventsService, pAccessControlService);
	ListManagerService* pListManagerService = new ListManagerService(jausRouter, pTransportService, pEventsService, pAccessControlService, pManagementService);
	LocalPoseSensorService* pLocalPoseSensorService = new LocalPoseSensorService(jausRouter, pTransportService, pEventsService, pAccessControlService);
	LocalWaypointListDriverService* pLocalWaypointListDriverService = new LocalWaypointListDriverService(jausRouter, pTransportService, pEventsService, pAccessControlService, pManagementService, pListManagerService);
	SkidSteerVehicleSimService* pSkidSteerVehicleSimService = new SkidSteerVehicleSimService(jausRouter, pTransportService, pEventsService, pAccessControlService, pManagementService);
	
	
	/// Add all the Services for the Component
	serviceList.push_back(pTransportService);
	serviceList.push_back(pEventsService);
	serviceList.push_back(pAccessControlService);
	serviceList.push_back(pDiscoveryService);
	serviceList.push_back(pManagementService);
	serviceList.push_back(pListManagerService);
	serviceList.push_back(pLocalPoseSensorService);
	serviceList.push_back(pLocalWaypointListDriverService);
	serviceList.push_back(pSkidSteerVehicleSimService);
	
}

WaypointDriver::~WaypointDriver()
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


void WaypointDriver::startComponent()
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


void WaypointDriver::shutdownComponent()
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

void WaypointDriver::processInternalEvent(InternalEvent *ie)
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



