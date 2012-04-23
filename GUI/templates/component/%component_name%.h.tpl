%copyright%

#ifndef %component_name_allcaps%_H
#define %component_name_allcaps%_H

#include <vector>
#include "EventReceiver.h"
#include "Transport/JausTransport.h"
#include "InternalEvents/InternalEvent.h"
%service_include_list%

class %component_name% : public JTS::EventReceiver
{
public:
	%component_name%(unsigned int subsystem, unsigned short node, unsigned short component);
	virtual ~%component_name%();
	
	void startComponent();
	void shutdownComponent();
	
protected:
	virtual void processInternalEvent(JTS::InternalEvent* ie);
	
	std::vector<JTS::Service*> serviceList;
	JTS::JausRouter* jausRouter;
	
};

#endif // %component_name_allcaps%_H
