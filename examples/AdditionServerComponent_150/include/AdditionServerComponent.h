

#ifndef ADDITIONSERVERCOMPONENT_H
#define ADDITIONSERVERCOMPONENT_H

#include <vector>
#include "EventReceiver.h"
#include "Transport/JausTransport.h"
#include "InternalEvents/InternalEvent.h"
#include "urn_jaus_example_addition_server_1_0/AdditionServerServiceDefService.h"


class AdditionServerComponent : public JTS::EventReceiver
{
public:
	AdditionServerComponent(unsigned int subsystem, unsigned short node, unsigned short component);
	virtual ~AdditionServerComponent();
	
	void startComponent();
	void shutdownComponent();
	
protected:
	virtual void processInternalEvent(JTS::InternalEvent* ie);
	
	std::vector<JTS::Service*> serviceList;
	JTS::JausRouter* jausRouter;
	
};

#endif // ADDITIONSERVERCOMPONENT_H
