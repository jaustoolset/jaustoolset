

#ifndef ADDITIONCLIENTCOMPONENT_H
#define ADDITIONCLIENTCOMPONENT_H

#include <vector>
#include "EventReceiver.h"
#include "Transport/JausTransport.h"
#include "InternalEvents/InternalEvent.h"
#include "urn_jaus_example_addition_client_1_0/AdditionClientServiceDefService.h"


class AdditionClientComponent : public JTS::EventReceiver
{
public:
	AdditionClientComponent(unsigned int subsystem, unsigned short node, unsigned short component);
	virtual ~AdditionClientComponent();
	
	void startComponent();
	void shutdownComponent();
	
protected:
	virtual void processInternalEvent(JTS::InternalEvent* ie);
	
	std::vector<JTS::Service*> serviceList;
	JTS::JausRouter* jausRouter;
	
};

#endif // ADDITIONCLIENTCOMPONENT_H
