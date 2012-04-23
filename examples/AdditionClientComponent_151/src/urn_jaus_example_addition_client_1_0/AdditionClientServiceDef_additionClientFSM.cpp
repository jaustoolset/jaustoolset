

#include "urn_jaus_example_addition_client_1_0/AdditionClientServiceDef_additionClientFSM.h"




using namespace JTS;

namespace urn_jaus_example_addition_client_1_0
{



AdditionClientServiceDef_additionClientFSM::AdditionClientServiceDef_additionClientFSM()
{

	/*
	 * Context has to be constructed last so that all class variables are 
	 * available if an EntryAction of the InitialState of the statemachine 
	 * needs them. 
	 */
	context = new AdditionClientServiceDef_additionClientFSMContext(*this);
}



AdditionClientServiceDef_additionClientFSM::~AdditionClientServiceDef_additionClientFSM() 
{
	delete context;
}

void AdditionClientServiceDef_additionClientFSM::setupNotifications()
{

}

void AdditionClientServiceDef_additionClientFSM::printAnswerToScreenAction(ReportAddition msg)
{
	std::cout << "Transitioned back to Ready\n";
	std::cout << "  The answer is "<< msg.getAdditionOutputBody()->getAdditionOutput()->getAdditionResult() << std::endl;
}

void AdditionClientServiceDef_additionClientFSM::serviceInitializedAction()
{
	std::cout << "In Ready state. Let's start adding...\n";

	// This is the basic message type for our query. 
	QueryAddition query;

	// The message contains a body, with a record. 
	query.getAdditionInputBody()->getAdditionInput()->setA1(500);
	query.getAdditionInputBody()->getAdditionInput()->setA2(500);

	// Send the response to the server on this subsystem and node.  The
	// Component ID is fixed at 150.
	JausAddress server(jausRouter->getJausAddress()->getSubsystemID(),
					   jausRouter->getJausAddress()->getNodeID(),
					   150);


	// Encode the request and send it to the server.
	sendJausMessage( query, server );

	std::cout << "Send addition request\n";
}

void AdditionClientServiceDef_additionClientFSM::serviceStartedAction()
{
    std::cout << "Addition client started\n";

    /// We now generate an internal event, which will be handled up
    /// above, resulting in a transition call to move us from Init to Ready
    ieHandler->invoke(new InitToReadyEventDef());
    std::cout << "Sent internal event to transition to Ready\n";
}





};
