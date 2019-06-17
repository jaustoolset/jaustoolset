

#ifndef SSID_ALLOCATOR_H
#define SSID_ALLOCATOR_H

#include <vector>
#include "EventReceiver.h"
#include "Transport/JausTransport.h"
#include "InternalEvents/InternalEvent.h"
#include "urn_jaus_jss_core_Transport_1_1/TransportService.h"
#include "urn_jaus_jss_exp_aeodrs_SubsystemIDAllocator_1_4/SubsystemIDAllocatorService.h"


class SSID_Allocator : public JTS::EventReceiver
{
public:
	SSID_Allocator(unsigned int subsystem, unsigned short node, unsigned short component);
	virtual ~SSID_Allocator();
	
	void startComponent();
	void shutdownComponent();
	
protected:
	virtual void processInternalEvent(JTS::InternalEvent* ie);
	
	std::vector<JTS::Service*> serviceList;
	JTS::JausRouter* jausRouter;
	
};

#endif // SSID_ALLOCATOR_H
