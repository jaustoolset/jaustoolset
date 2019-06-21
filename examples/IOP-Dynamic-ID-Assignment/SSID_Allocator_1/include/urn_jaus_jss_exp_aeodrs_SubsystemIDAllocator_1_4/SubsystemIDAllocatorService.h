

#ifndef SUBSYSTEMIDALLOCATORSERVICE_H
#define SUBSYSTEMIDALLOCATORSERVICE_H

#include "Service.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JConstants.h"
#include "urn_jaus_jss_exp_aeodrs_SubsystemIDAllocator_1_4/Messages/MessageSet.h"
#include "urn_jaus_jss_exp_aeodrs_SubsystemIDAllocator_1_4/InternalEvents/InternalEventsSet.h"
#include "Transport/OS.h"

#include "urn_jaus_jss_core_Transport_1_1/TransportService.h"
#include "SubsystemIDAllocator_ReceiveFSM.h"
#include "SubsystemIDAllocator_SendFSM.h"


namespace urn_jaus_jss_exp_aeodrs_SubsystemIDAllocator_1_4
{
	
class DllExport SubsystemIDAllocatorService : public JTS::Service
{
public:
	SubsystemIDAllocatorService( JTS::JausRouter* jausRouter , urn_jaus_jss_core_Transport_1_1::TransportService* pTransportService);
	virtual ~SubsystemIDAllocatorService();
	
	virtual bool processTransitions(JTS::InternalEvent* ie);
	virtual bool defaultTransitions(JTS::InternalEvent* ie);
	
	// FSMs are public so that children services can access them
	SubsystemIDAllocator_ReceiveFSM* pSubsystemIDAllocator_ReceiveFSM;
	SubsystemIDAllocator_SendFSM* pSubsystemIDAllocator_SendFSM;
	


	
protected:
	virtual void run();

};

};

#endif // SUBSYSTEMIDALLOCATORSERVICE_H
