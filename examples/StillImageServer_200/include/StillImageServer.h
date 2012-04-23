

#ifndef STILLIMAGESERVER_H
#define STILLIMAGESERVER_H

#include <vector>
#include "EventReceiver.h"
#include "Transport/JausTransport.h"
#include "InternalEvents/InternalEvent.h"
#include "urn_jaus_jss_core_Transport_1_0/TransportService.h"
#include "urn_jaus_jss_core_Events_1_0/EventsService.h"
#include "urn_jaus_jss_core_AccessControl_1_0/AccessControlService.h"
#include "urn_jaus_jss_environmentSensing_VisualSensor_1_0/VisualSensorService.h"
#include "urn_jaus_jss_environmentSensing_StillImage_1_0/StillImageService.h"


class StillImageServer : public JTS::EventReceiver
{
public:
	StillImageServer(unsigned int subsystem, unsigned short node, unsigned short component);
	virtual ~StillImageServer();
	
	void startComponent();
	void shutdownComponent();
	
protected:
	virtual void processInternalEvent(JTS::InternalEvent* ie);
	
	std::vector<JTS::Service*> serviceList;
	JTS::JausRouter* jausRouter;
	
};

#endif // STILLIMAGESERVER_H
