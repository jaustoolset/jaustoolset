

#ifndef SUBSYSTEMIDALLOCATORCLIENTSERVICE_H
#define SUBSYSTEMIDALLOCATORCLIENTSERVICE_H

#include "Service.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JConstants.h"
#include "urn_jaus_neya_SubsystemIDAllocatorClient_1_4/Messages/MessageSet.h"
#include "urn_jaus_neya_SubsystemIDAllocatorClient_1_4/InternalEvents/InternalEventsSet.h"
#include "Transport/OS.h"

#include "urn_jaus_jss_core_Transport_1_1/TransportService.h"
#include "SubsystemIDAllocatorClient_ReceiveFSM.h"
#include "SubsystemIDAllocatorClient_SendFSM.h"


namespace urn_jaus_neya_SubsystemIDAllocatorClient_1_4
{
	
class DllExport SubsystemIDAllocatorClientService : public JTS::Service
{
public:
	SubsystemIDAllocatorClientService( JTS::JausRouter* jausRouter , urn_jaus_jss_core_Transport_1_1::TransportService* pTransportService);
	virtual ~SubsystemIDAllocatorClientService();
	
	virtual bool processTransitions(JTS::InternalEvent* ie);
	virtual bool defaultTransitions(JTS::InternalEvent* ie);
	
	// FSMs are public so that children services can access them
	SubsystemIDAllocatorClient_ReceiveFSM* pSubsystemIDAllocatorClient_ReceiveFSM;
	SubsystemIDAllocatorClient_SendFSM* pSubsystemIDAllocatorClient_SendFSM;
	


	
protected:
	virtual void run();

};

};

#endif // SUBSYSTEMIDALLOCATORCLIENTSERVICE_H
