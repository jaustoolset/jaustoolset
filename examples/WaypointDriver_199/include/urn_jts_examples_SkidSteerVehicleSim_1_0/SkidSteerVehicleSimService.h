

#ifndef SKIDSTEERVEHICLESIMSERVICE_H
#define SKIDSTEERVEHICLESIMSERVICE_H

#include "Service.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JConstants.h"
#include "urn_jts_examples_SkidSteerVehicleSim_1_0/Messages/MessageSet.h"
#include "urn_jts_examples_SkidSteerVehicleSim_1_0/InternalEvents/InternalEventsSet.h"
#include "Transport/OS.h"

#include "urn_jaus_jss_core_Transport_1_0/TransportService.h"
#include "urn_jaus_jss_core_Events_1_0/EventsService.h"
#include "urn_jaus_jss_core_AccessControl_1_0/AccessControlService.h"
#include "urn_jaus_jss_core_Management_1_0/ManagementService.h"
#include "SkidSteerVehicleSim_ReceiveFSM.h"
#include "SkidSteerVehicleSim_SendFSM.h"


namespace urn_jts_examples_SkidSteerVehicleSim_1_0
{
	
class DllExport SkidSteerVehicleSimService : public JTS::Service
{
public:
	SkidSteerVehicleSimService( JTS::JausRouter* jausRouter , urn_jaus_jss_core_Transport_1_0::TransportService* pTransportService, urn_jaus_jss_core_Events_1_0::EventsService* pEventsService, urn_jaus_jss_core_AccessControl_1_0::AccessControlService* pAccessControlService, urn_jaus_jss_core_Management_1_0::ManagementService* pManagementService);
	virtual ~SkidSteerVehicleSimService();
	
	virtual bool processTransitions(JTS::InternalEvent* ie);
	virtual bool defaultTransitions(JTS::InternalEvent* ie);
	
	// FSMs are public so that children services can access them
	SkidSteerVehicleSim_ReceiveFSM* pSkidSteerVehicleSim_ReceiveFSM;
	SkidSteerVehicleSim_SendFSM* pSkidSteerVehicleSim_SendFSM;
	


	
protected:
	virtual void run();

};

};

#endif // SKIDSTEERVEHICLESIMSERVICE_H
