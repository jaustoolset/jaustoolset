

#ifndef PINGCOMPONENT_H
#define PINGCOMPONENT_H

#include <vector>
#include "EventReceiver.h"
#include "Transport/JausTransport.h"
#include "InternalEvents/InternalEvent.h"
#include "urn_jts_PingServer_1_0/PingServerService.h"
#include "urn_jts_PingClient_1_0/PingClientService.h"


class PingComponent : public JTS::EventReceiver
{
public:
	PingComponent(unsigned int subsystem, unsigned short node, unsigned short component);
	virtual ~PingComponent();
	
	void startComponent();
	void shutdownComponent();
	
protected:
	virtual void processInternalEvent(JTS::InternalEvent* ie);
	
	std::vector<JTS::Service*> serviceList;
	JTS::JausRouter* jausRouter;
	
};

#endif // PINGCOMPONENT_H
