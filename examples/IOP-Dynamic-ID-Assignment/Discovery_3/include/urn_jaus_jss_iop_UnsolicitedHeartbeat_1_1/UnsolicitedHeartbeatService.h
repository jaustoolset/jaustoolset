

#ifndef UNSOLICITEDHEARTBEATSERVICE_H
#define UNSOLICITEDHEARTBEATSERVICE_H

#include "Service.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JConstants.h"
#include "urn_jaus_jss_iop_UnsolicitedHeartbeat_1_1/Messages/MessageSet.h"
#include "urn_jaus_jss_iop_UnsolicitedHeartbeat_1_1/InternalEvents/InternalEventsSet.h"
#include "Transport/OS.h"

#include "urn_jaus_jss_core_Transport_1_1/TransportService.h"
#include "urn_jaus_jss_core_Events_1_1/EventsService.h"
#include "urn_jaus_jss_core_Liveness_1_1/LivenessService.h"
#include "UnsolicitedHeartbeat_ReceiveFSM.h"
#include "UnsolicitedHeartbeat_SendFSM.h"


namespace urn_jaus_jss_iop_UnsolicitedHeartbeat_1_1
{
	
class DllExport UnsolicitedHeartbeatService : public JTS::Service
{
public:
	UnsolicitedHeartbeatService( JTS::JausRouter* jausRouter , urn_jaus_jss_core_Transport_1_1::TransportService* pTransportService, urn_jaus_jss_core_Events_1_1::EventsService* pEventsService, urn_jaus_jss_core_Liveness_1_1::LivenessService* pLivenessService);
	virtual ~UnsolicitedHeartbeatService();
	
	virtual bool processTransitions(JTS::InternalEvent* ie);
	virtual bool defaultTransitions(JTS::InternalEvent* ie);
	
	// FSMs are public so that children services can access them
	UnsolicitedHeartbeat_ReceiveFSM* pUnsolicitedHeartbeat_ReceiveFSM;
	UnsolicitedHeartbeat_SendFSM* pUnsolicitedHeartbeat_SendFSM;
	


	
protected:
	virtual void run();

};

};

#endif // UNSOLICITEDHEARTBEATSERVICE_H
