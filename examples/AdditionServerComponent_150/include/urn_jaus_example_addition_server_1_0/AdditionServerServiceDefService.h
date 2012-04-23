

#ifndef ADDITIONSERVERSERVICEDEFSERVICE_H
#define ADDITIONSERVERSERVICEDEFSERVICE_H

#include "Service.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JConstants.h"
#include "urn_jaus_example_addition_server_1_0/Messages/MessageSet.h"
#include "urn_jaus_example_addition_server_1_0/InternalEvents/InternalEventsSet.h"
#include "Transport/OS.h"

#include "AdditionServerServiceDef_additionServerFSM.h"


namespace urn_jaus_example_addition_server_1_0
{
	
class DllExport AdditionServerServiceDefService : public JTS::Service
{
public:
	AdditionServerServiceDefService( JTS::JausRouter* jausRouter );
	virtual ~AdditionServerServiceDefService();
	
	virtual bool processTransitions(JTS::InternalEvent* ie);
	virtual bool defaultTransitions(JTS::InternalEvent* ie);
	
	// FSMs are public so that children services can access them
	AdditionServerServiceDef_additionServerFSM* pAdditionServerServiceDef_additionServerFSM;
	


	
protected:
	virtual void run();

};

};

#endif // ADDITIONSERVERSERVICEDEFSERVICE_H
