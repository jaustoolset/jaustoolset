

#ifndef MANAGEMENT_H
#define MANAGEMENT_H

#include <vector>
#include "EventReceiver.h"
#include "Transport/JausTransport.h"
#include "InternalEvents/InternalEvent.h"
#include "urn_jaus_jss_core_Transport_1_1/TransportService.h"
#include "urn_jaus_jss_core_Events_1_1/EventsService.h"
#include "urn_jaus_jss_core_AccessControl_1_1/AccessControlService.h"
#include "urn_jaus_jss_core_Management_1_1/ManagementService.h"


class Management : public JTS::EventReceiver
{
public:
	Management(unsigned int subsystem, unsigned short node, unsigned short component);
	virtual ~Management();
	
	void startComponent();
	void shutdownComponent();
	
protected:
	virtual void processInternalEvent(JTS::InternalEvent* ie);
	
	std::vector<JTS::Service*> serviceList;
	JTS::JausRouter* jausRouter;
	
};

#endif // MANAGEMENT_H
