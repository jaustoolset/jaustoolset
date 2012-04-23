

#ifndef ADDITIONCLIENTSERVICEDEF_ADDITIONCLIENTFSM_H
#define ADDITIONCLIENTSERVICEDEF_ADDITIONCLIENTFSM_H

#include "JausUtils.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JTSStateMachine.h"
#include "urn_jaus_example_addition_client_1_0/Messages/MessageSet.h"
#include "urn_jaus_example_addition_client_1_0/InternalEvents/InternalEventsSet.h"

typedef JTS::Receive_1_0 Receive;
typedef JTS::Send_1_0 Send;



#include "AdditionClientServiceDef_additionClientFSM_sm.h"

namespace urn_jaus_example_addition_client_1_0
{
	
class DllExport AdditionClientServiceDef_additionClientFSM : public JTS::StateMachine
{
public:
	AdditionClientServiceDef_additionClientFSM();
	virtual ~AdditionClientServiceDef_additionClientFSM();
	
	/// Handle notifications on parent state changes
	virtual void setupNotifications();

	/// Action Methods
	virtual void printAnswerToScreenAction(ReportAddition msg);
	virtual void serviceInitializedAction();
	virtual void serviceStartedAction();


	/// Guard Methods

	
	
	AdditionClientServiceDef_additionClientFSMContext *context;
	
protected:

    /// References to parent FSMs

    
};

};

#endif // ADDITIONCLIENTSERVICEDEF_ADDITIONCLIENTFSM_H
