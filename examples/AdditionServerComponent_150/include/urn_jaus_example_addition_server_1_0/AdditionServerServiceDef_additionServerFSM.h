

#ifndef ADDITIONSERVERSERVICEDEF_ADDITIONSERVERFSM_H
#define ADDITIONSERVERSERVICEDEF_ADDITIONSERVERFSM_H

#include "JausUtils.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JTSStateMachine.h"
#include "urn_jaus_example_addition_server_1_0/Messages/MessageSet.h"
#include "urn_jaus_example_addition_server_1_0/InternalEvents/InternalEventsSet.h"

typedef JTS::Receive_1_0 Receive;
typedef JTS::Send_1_0 Send;



#include "AdditionServerServiceDef_additionServerFSM_sm.h"

namespace urn_jaus_example_addition_server_1_0
{
	
class DllExport AdditionServerServiceDef_additionServerFSM : public JTS::StateMachine
{
public:
	AdditionServerServiceDef_additionServerFSM();
	virtual ~AdditionServerServiceDef_additionServerFSM();
	
	/// Handle notifications on parent state changes
	virtual void setupNotifications();

	/// Action Methods
	virtual void fsmStartedAction();
	virtual void sendReportAdditionAction(QueryAddition msg, unsigned int sender);
	virtual void serverInitializedAction();


	/// Guard Methods

	
	
	AdditionServerServiceDef_additionServerFSMContext *context;
	
protected:

    /// References to parent FSMs

    
};

};

#endif // ADDITIONSERVERSERVICEDEF_ADDITIONSERVERFSM_H
