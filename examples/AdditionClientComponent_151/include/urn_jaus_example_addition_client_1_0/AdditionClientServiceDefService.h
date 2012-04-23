

#ifndef ADDITIONCLIENTSERVICEDEFSERVICE_H
#define ADDITIONCLIENTSERVICEDEFSERVICE_H

#include "Service.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JConstants.h"
#include "urn_jaus_example_addition_client_1_0/Messages/MessageSet.h"
#include "urn_jaus_example_addition_client_1_0/InternalEvents/InternalEventsSet.h"
#include "Transport/OS.h"

#include "AdditionClientServiceDef_additionClientFSM.h"


namespace urn_jaus_example_addition_client_1_0
{
	
class DllExport AdditionClientServiceDefService : public JTS::Service
{
public:
	AdditionClientServiceDefService( JTS::JausRouter* jausRouter );
	virtual ~AdditionClientServiceDefService();
	
	virtual bool processTransitions(JTS::InternalEvent* ie);
	virtual bool defaultTransitions(JTS::InternalEvent* ie);
	
	// FSMs are public so that children services can access them
	AdditionClientServiceDef_additionClientFSM* pAdditionClientServiceDef_additionClientFSM;
	


	
protected:
	virtual void run();

};

};

#endif // ADDITIONCLIENTSERVICEDEFSERVICE_H
