

#include "urn_jaus_example_addition_server_1_0/AdditionServerServiceDef_additionServerFSM.h"




using namespace JTS;

namespace urn_jaus_example_addition_server_1_0
{



AdditionServerServiceDef_additionServerFSM::AdditionServerServiceDef_additionServerFSM()
{

	/*
	 * Context has to be constructed last so that all class variables are 
	 * available if an EntryAction of the InitialState of the statemachine 
	 * needs them. 
	 */
	context = new AdditionServerServiceDef_additionServerFSMContext(*this);
}



AdditionServerServiceDef_additionServerFSM::~AdditionServerServiceDef_additionServerFSM() 
{
	delete context;
}

void AdditionServerServiceDef_additionServerFSM::setupNotifications()
{

}

void AdditionServerServiceDef_additionServerFSM::fsmStartedAction()
{
    /// We now generate an internal event, which will be handled up
    /// above, resulting in a transition call to move us from Init to Ready
    std::cout << "Addition server started\n";
    ieHandler->invoke(new InitToReadyEventDef());
    std::cout << "Sent internal event to transition to Ready\n";
}

void AdditionServerServiceDef_additionServerFSM::sendReportAdditionAction(QueryAddition msg, unsigned int sender)
{
   // Pull out the data.
	int A1 = msg.getAdditionInputBody()->getAdditionInput()->getA1();
	int A2 = msg.getAdditionInputBody()->getAdditionInput()->getA2();

	// Now, lets pull out the two numbers we received
	std::cout << " Need to add " << A1 << " + " << A2 << std::endl;

	// Now let's formulate a response
	int answer;
	answer = A1 + A2;
	ReportAddition theAnswer;
	theAnswer.getAdditionOutputBody()->getAdditionOutput()->setAdditionResult(answer);
	
	// Encode the response and send it back to the requestor.
	sendJausMessage( theAnswer, JausAddress(sender) );

	std::cout << "answer sent to client\n";
}

void AdditionServerServiceDef_additionServerFSM::serverInitializedAction()
{
    /// This is the action for the transitionToReady Transition. 
    /// Add in whatever code is needed when transitioning from Init to Ready

    std::cout << "Transitioned from Init to Ready. Ready to begin adding!\n";

    // Nothing else needs to be done here. We'll sit in READY until we get a QueryAddition
    // message. When that happens, we'll trigger a self-transition back into READY that 
    // computes the answer and sends it back to the requestor.
}





};
