

using JTS;
using System;
	

	
namespace urn_jaus_example_addition_server_1_0
{
	public class AdditionServerServiceDef_additionServerFSM : StateMachine{

		public AdditionServerServiceDef_additionServerFSMContext context;
		
		

		public AdditionServerServiceDef_additionServerFSM()
	{

		/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
		 */
		context = new AdditionServerServiceDef_additionServerFSMContext(this);

	}

	
		/// Handle notifications on parent state changes
		public override void setupNotifications()
		{

		}
	
		public virtual void fsmStartedAction()
{
    /// We now generate an internal event, which will be handled up
    /// above, resulting in a transition call to move us from 
    /// Init to Ready
    Console.WriteLine("Addition server started ");
    ieHandler.invoke(new InitToReadyEventDef());
    Console.WriteLine("Sent internal event to transition to Ready");

}

public virtual void sendReportAdditionAction(QueryAddition msg, uint sender)
{
    uint A1 = msg.getAdditionInputBody().getAdditionInput().getA1();
    uint A2 = msg.getAdditionInputBody().getAdditionInput().getA2();

    // Now, lets pull out the two numbers we received
    Console.WriteLine(" Need to add " + A1 + " + " + A2);

    // Now let's formulate a response
    uint answer;
    answer = A1 + A2;
    ReportAddition theAnswer = new ReportAddition();
    theAnswer.getAdditionOutputBody().
              getAdditionOutput().setAdditionResult(answer);

    // Encode the response and send it back to the requestor.
    sendJausMessage(theAnswer, new JausAddress(sender));

    Console.WriteLine("answer sent to client");

}

public virtual void serverInitializedAction()
{
    /// This is the action for the transitionToReady Transition. 
    /// Add in whatever code is needed when transitioning from 
    /// Init to Ready

    Console.WriteLine("Transitioned from Init to Ready. Ready to begin adding");

    // Nothing else needs to be done here. We'll sit in 
    // READY until we get a QueryAddition
    // message. When that happens, we'll trigger a self-transition 
    // back into READY that 
    // computes the answer and sends it back to the requestor.

}



		
	}
	
}

