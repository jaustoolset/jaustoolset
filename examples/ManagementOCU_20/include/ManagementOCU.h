

#ifndef MANAGEMENTOCU_H
#define MANAGEMENTOCU_H

#include <vector>
#include "EventReceiver.h"
#include "Transport/JausTransport.h"
#include "InternalEvents/InternalEvent.h"
#include "urn_jts_example_management_ocu_1_0/ManagementOCUService.h"


class ManagementOCU : public JTS::EventReceiver
{
public:
	ManagementOCU(unsigned int subsystem, unsigned short node, unsigned short component);
	virtual ~ManagementOCU();
	
	void startComponent();
	void shutdownComponent();
	
protected:
	virtual void processInternalEvent(JTS::InternalEvent* ie);
	
	std::vector<JTS::Service*> serviceList;
	JTS::JausRouter* jausRouter;
	
};

#endif // MANAGEMENTOCU_H
