

#ifndef LIVENESSSERVICE_H
#define LIVENESSSERVICE_H

#include "Service.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JConstants.h"
#include "urn_jaus_jss_core_Liveness_1_1/Messages/MessageSet.h"
#include "urn_jaus_jss_core_Liveness_1_1/InternalEvents/InternalEventsSet.h"
#include "Transport/OS.h"

#include "urn_jaus_jss_core_Transport_1_1/TransportService.h"
#include "urn_jaus_jss_core_Events_1_1/EventsService.h"
#include "Liveness_ReceiveFSM.h"
#include "Liveness_SendFSM.h"


namespace urn_jaus_jss_core_Liveness_1_1
{
	
class DllExport LivenessService : public JTS::Service
{
public:
	LivenessService( JTS::JausRouter* jausRouter , urn_jaus_jss_core_Transport_1_1::TransportService* pTransportService, urn_jaus_jss_core_Events_1_1::EventsService* pEventsService);
	virtual ~LivenessService();
	
	virtual bool processTransitions(JTS::InternalEvent* ie);
	virtual bool defaultTransitions(JTS::InternalEvent* ie);
	
	// FSMs are public so that children services can access them
	Liveness_ReceiveFSM* pLiveness_ReceiveFSM;
	Liveness_SendFSM* pLiveness_SendFSM;
	


	
protected:
	virtual void run();

};

};

#endif // LIVENESSSERVICE_H
