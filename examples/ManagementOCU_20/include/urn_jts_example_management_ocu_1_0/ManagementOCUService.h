

#ifndef MANAGEMENTOCUSERVICE_H
#define MANAGEMENTOCUSERVICE_H

#include "Service.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JConstants.h"
#include "urn_jts_example_management_ocu_1_0/Messages/MessageSet.h"
#include "urn_jts_example_management_ocu_1_0/InternalEvents/InternalEventsSet.h"
#include "Transport/OS.h"

#include "ManagementOCU_ManagementOcuFSM.h"


namespace urn_jts_example_management_ocu_1_0
{
	
class DllExport ManagementOCUService : public JTS::Service
{
public:
	ManagementOCUService( JTS::JausRouter* jausRouter );
	virtual ~ManagementOCUService();
	
	virtual bool processTransitions(JTS::InternalEvent* ie);
	virtual bool defaultTransitions(JTS::InternalEvent* ie);
	
	// FSMs are public so that children services can access them
	ManagementOCU_ManagementOcuFSM* pManagementOCU_ManagementOcuFSM;
	


	
protected:
	virtual void run();

};

};

#endif // MANAGEMENTOCUSERVICE_H
