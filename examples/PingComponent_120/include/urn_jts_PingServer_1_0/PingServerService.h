

#ifndef PINGSERVERSERVICE_H
#define PINGSERVERSERVICE_H

#include "Service.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JConstants.h"
#include "urn_jts_PingServer_1_0/Messages/MessageSet.h"
#include "urn_jts_PingServer_1_0/InternalEvents/InternalEventsSet.h"
#include "Transport/OS.h"

#include "PingServer_PingFSM.h"


namespace urn_jts_PingServer_1_0
{
	
class DllExport PingServerService : public JTS::Service
{
public:
	PingServerService( JTS::JausRouter* jausRouter );
	virtual ~PingServerService();
	
	virtual bool processTransitions(JTS::InternalEvent* ie);
	virtual bool defaultTransitions(JTS::InternalEvent* ie);
	
	// FSMs are public so that children services can access them
	PingServer_PingFSM* pPingServer_PingFSM;
	


	
protected:
	virtual void run();

};

};

#endif // PINGSERVERSERVICE_H
