

#ifndef CLIENT_H
#define CLIENT_H

#include <vector>
#include "EventReceiver.h"
#include "Transport/JausTransport.h"
#include "InternalEvents/InternalEvent.h"
#include "urn_jaus_jss_core_Transport_1_1/TransportService.h"
#include "urn_jts_DiscoveryClient_1_0/DiscoveryClientService.h"


class Client : public JTS::EventReceiver
{
public:
	Client(unsigned int subsystem, unsigned short node, unsigned short component, bool allowWildcards = false);
	virtual ~Client();
	
	void startComponent();
	void shutdownComponent();
	
protected:
	virtual void processInternalEvent(JTS::InternalEvent* ie);
	
	std::vector<JTS::Service*> serviceList;
	JTS::JausRouter* jausRouter;
	
};

#endif // CLIENT_H
