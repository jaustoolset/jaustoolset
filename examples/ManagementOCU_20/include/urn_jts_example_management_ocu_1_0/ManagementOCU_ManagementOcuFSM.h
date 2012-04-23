

#ifndef MANAGEMENTOCU_MANAGEMENTOCUFSM_H
#define MANAGEMENTOCU_MANAGEMENTOCUFSM_H

#include "JausUtils.h"
#include "InternalEvents/InternalEventHandler.h"
#include "Transport/JausTransport.h"
#include "JTSStateMachine.h"
#include "urn_jts_example_management_ocu_1_0/Messages/MessageSet.h"
#include "urn_jts_example_management_ocu_1_0/InternalEvents/InternalEventsSet.h"

typedef JTS::Receive_1_0 Receive;
typedef JTS::Send_1_0 Send;



#include "ManagementOCU_ManagementOcuFSM_sm.h"

namespace urn_jts_example_management_ocu_1_0
{
	
class DllExport ManagementOCU_ManagementOcuFSM : public JTS::StateMachine
{
public:
	ManagementOCU_ManagementOcuFSM();
	virtual ~ManagementOCU_ManagementOcuFSM();
	
	/// Handle notifications on parent state changes
	virtual void setupNotifications();

	/// Action Methods
	virtual void displayMenuAction();
	virtual void displayStatusAction(ReportStatus msg);
	virtual void printMessageAction(std::string arg0);
	virtual void sendManagementMessageAction(MenuItemEntered msg);
	virtual void sendRequestControlAction();
	virtual void terminateServiceAction();


	/// Guard Methods
	virtual bool isControlAccepted(ConfirmControl msg);
	virtual bool isSelectionToEnd(MenuItemEntered msg);

	
	
	ManagementOCU_ManagementOcuFSMContext *context;
	
protected:

    /// References to parent FSMs

    
};

};

#endif // MANAGEMENTOCU_MANAGEMENTOCUFSM_H
