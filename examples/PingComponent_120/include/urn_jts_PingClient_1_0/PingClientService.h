

#ifndef PINGCLIENTSERVICE_H
#define PINGCLIENTSERVICE_H

#include "Service.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JConstants.h"
#include "urn_jts_PingClient_1_0/Messages/MessageSet.h"
#include "urn_jts_PingClient_1_0/InternalEvents/InternalEventsSet.h"
#include "Transport/OS.h"

#include "PingClient_PingClientFSM.h"


namespace urn_jts_PingClient_1_0
{
	
class DllExport PingClientService : public JTS::Service
{
public:
	PingClientService( JTS::JausRouter* jausRouter );
	virtual ~PingClientService();
	
	virtual bool processTransitions(JTS::InternalEvent* ie);
	virtual bool defaultTransitions(JTS::InternalEvent* ie);
	
	// FSMs are public so that children services can access them
	PingClient_PingClientFSM* pPingClient_PingClientFSM;
	


	
protected:
	virtual void run();

};

};

#endif // PINGCLIENTSERVICE_H
