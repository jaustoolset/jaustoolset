

#ifndef NODEIDALLOCATORSERVICE_H
#define NODEIDALLOCATORSERVICE_H

#include "Service.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JConstants.h"
#include "urn_jaus_jss_iop_NodeIDAllocator_1_1/Messages/MessageSet.h"
#include "urn_jaus_jss_iop_NodeIDAllocator_1_1/InternalEvents/InternalEventsSet.h"
#include "Transport/OS.h"

#include "urn_jaus_jss_core_Transport_1_1/TransportService.h"
#include "NodeIDAllocator_ReceiveFSM.h"
#include "NodeIDAllocator_SendFSM.h"


namespace urn_jaus_jss_iop_NodeIDAllocator_1_1
{
	
class DllExport NodeIDAllocatorService : public JTS::Service
{
public:
	NodeIDAllocatorService( JTS::JausRouter* jausRouter , urn_jaus_jss_core_Transport_1_1::TransportService* pTransportService);
	virtual ~NodeIDAllocatorService();
	
	virtual bool processTransitions(JTS::InternalEvent* ie);
	virtual bool defaultTransitions(JTS::InternalEvent* ie);
	
	// FSMs are public so that children services can access them
	NodeIDAllocator_ReceiveFSM* pNodeIDAllocator_ReceiveFSM;
	NodeIDAllocator_SendFSM* pNodeIDAllocator_SendFSM;
	


	
protected:
	virtual void run();

};

};

#endif // NODEIDALLOCATORSERVICE_H
