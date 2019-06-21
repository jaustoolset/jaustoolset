

#ifndef DISCOVERY_H
#define DISCOVERY_H

#include <vector>
#include "EventReceiver.h"
#include "Transport/JausTransport.h"
#include "InternalEvents/InternalEvent.h"
#include "urn_jaus_jss_core_Transport_1_1/TransportService.h"
#include "urn_jaus_jss_core_Events_1_1/EventsService.h"
#include "urn_jaus_jss_core_Liveness_1_1/LivenessService.h"
#include "urn_jaus_jss_iop_UnsolicitedHeartbeat_1_1/UnsolicitedHeartbeatService.h"
#include "urn_jaus_neya_SubsystemIDAllocatorClient_1_4/SubsystemIDAllocatorClientService.h"
#include "urn_jaus_jss_iop_NodeIDAllocator_1_1/NodeIDAllocatorService.h"
#include "urn_jaus_jss_core_Discovery_1_1/DiscoveryService.h"


class Discovery : public JTS::EventReceiver
{
public:
	Discovery(unsigned int subsystem, unsigned short node, unsigned short component, bool allowWildcards = false);
	virtual ~Discovery();
	
	void startComponent();
	void shutdownComponent();
	
protected:
	virtual void processInternalEvent(JTS::InternalEvent* ie);
	
	std::vector<JTS::Service*> serviceList;
	JTS::JausRouter* jausRouter;
	
};

#endif // DISCOVERY_H
