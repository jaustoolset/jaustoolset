

using JTS;
using System;
	

	
namespace urn_jaus_example_addition_client_1_0
{
	public class AdditionClientServiceDef_additionClientFSM : StateMachine{

		public AdditionClientServiceDef_additionClientFSMContext context;
		
		

		public AdditionClientServiceDef_additionClientFSM()
	{

		/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
		 */
		context = new AdditionClientServiceDef_additionClientFSMContext(this);

	}

	
		/// Handle notifications on parent state changes
		public override void setupNotifications()
		{

		}
	
		public virtual void printAnswerToScreenAction(ReportAddition msg)
{
    Console.WriteLine("Transitioned back to Ready");
    Console.WriteLine("  The answer is "
  + msg.getAdditionOutputBody().getAdditionOutput().getAdditionResult());

}

public virtual void serviceInitializedAction()
{
    Console.WriteLine("In Ready state. Let's start adding...");

    // This is the basic message type for our query. 
    QueryAddition query = new QueryAddition();

    // The message contains a body, with a record. 
    query.getAdditionInputBody().getAdditionInput().setA1(500);
    query.getAdditionInputBody().getAdditionInput().setA2(500);

    // Send the response to the server on this subsystem and node.  The
    // Component ID is fixed at 150.
    JausAddress server = new JausAddress(
                jausRouter.getJausAddress().getSubsystemID(),
                  jausRouter.getJausAddress().getNodeID(),
                  150);

    // Encode the request and send it to the server.
    sendJausMessage(query, server);

    Console.WriteLine("Send addition request");

}

public virtual void serviceStartedAction()
{
    Console.WriteLine("Addition client started");

    /// We now generate an internal event, which will be handled up
    /// above, resulting in a transition call to move us from 
    /// Init to Ready
    ieHandler.invoke(new InitToReadyEventDef());
    Console.WriteLine("Sent internal event to transition to Ready");

}



		
	}
	
}

