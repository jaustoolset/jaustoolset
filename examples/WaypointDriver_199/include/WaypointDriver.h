

#ifndef WAYPOINTDRIVER_H
#define WAYPOINTDRIVER_H

#include <vector>
#include "EventReceiver.h"
#include "Transport/JausTransport.h"
#include "InternalEvents/InternalEvent.h"
#include "urn_jaus_jss_core_Transport_1_0/TransportService.h"
#include "urn_jaus_jss_core_Events_1_0/EventsService.h"
#include "urn_jaus_jss_core_AccessControl_1_0/AccessControlService.h"
#include "urn_jaus_jss_core_Discovery_1_0/DiscoveryService.h"
#include "urn_jaus_jss_core_Management_1_0/ManagementService.h"
#include "urn_jaus_jss_mobility_ListManager_1_0/ListManagerService.h"
#include "urn_jaus_jss_mobility_LocalPoseSensor_1_0/LocalPoseSensorService.h"
#include "urn_jaus_jss_mobility_LocalWaypointListDriver_1_0/LocalWaypointListDriverService.h"
#include "urn_jts_examples_SkidSteerVehicleSim_1_0/SkidSteerVehicleSimService.h"


class WaypointDriver : public JTS::EventReceiver
{
public:
	WaypointDriver(unsigned int subsystem, unsigned short node, unsigned short component);
	virtual ~WaypointDriver();
	
	void startComponent();
	void shutdownComponent();
	
protected:
	virtual void processInternalEvent(JTS::InternalEvent* ie);
	
	std::vector<JTS::Service*> serviceList;
	JTS::JausRouter* jausRouter;
	
};

#endif // WAYPOINTDRIVER_H
