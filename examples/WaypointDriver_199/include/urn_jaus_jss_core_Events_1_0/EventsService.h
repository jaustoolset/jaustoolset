

#ifndef EVENTSSERVICE_H
#define EVENTSSERVICE_H

#include "Service.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JConstants.h"
#include "urn_jaus_jss_core_Events_1_0/Messages/MessageSet.h"
#include "urn_jaus_jss_core_Events_1_0/InternalEvents/InternalEventsSet.h"
#include "Transport/OS.h"

#include "urn_jaus_jss_core_Transport_1_0/TransportService.h"
#include "Events_ReceiveFSM.h"
#include "Events_SendFSM.h"


namespace urn_jaus_jss_core_Events_1_0
{
	
class DllExport EventsService : public JTS::Service
{
public:
	EventsService( JTS::JausRouter* jausRouter , urn_jaus_jss_core_Transport_1_0::TransportService* pTransportService);
	virtual ~EventsService();
	
	virtual bool processTransitions(JTS::InternalEvent* ie);
	virtual bool defaultTransitions(JTS::InternalEvent* ie);
	
	// FSMs are public so that children services can access them
	Events_ReceiveFSM* pEvents_ReceiveFSM;
	Events_SendFSM* pEvents_SendFSM;
	


	
protected:
	virtual void run();

};

};

#endif // EVENTSSERVICE_H
