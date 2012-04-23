
package src.urn_jaus_example_addition_server_1_0;

import java.util.StringTokenizer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import framework.transport.JausRouter;
import framework.transport.JausAddress;
import framework.internalEvents.*;
import framework.StateMachine;
import framework.messages.Message;
import statemap.*;
import src.urn_jaus_example_addition_server_1_0.InternalEvents.*;
import src.urn_jaus_example_addition_server_1_0.Messages.*;



public class AdditionServerServiceDef_additionServerFSM extends StateMachine{
	protected boolean running;

    AdditionServerServiceDef_additionServerFSMContext context;

    
	
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
	public void setupNotifications()
	{

	}

	/// Access for debug purposes
	public String getStateName()
	{
		return context.getState().getName();
	}

	public void fsmStartedAction()
{
	/// We now generate an internal event, which will be handled up
    /// above, resulting in a transition call to move us from 
    /// Init to Ready
    System.out.println("Addition server started");
    ieHandler.invoke(new InitToReadyEventDef());
    System.out.println("Sent internal event to transition to Ready");

}

public void sendReportAdditionAction(QueryAddition msg, long sender)
{
	long A1=msg.getAdditionInputBody().getAdditionInput().getA1();
	long A2=msg.getAdditionInputBody().getAdditionInput().getA2();
	
      // Now, lets pull out the two numbers we received
	System.out.println(" Need to add " + A1 + " + " + A2 );

	// Now let's formulate a response
	long answer;
	answer = A1 + A2;
	ReportAddition theAnswer = new ReportAddition();
	theAnswer.getAdditionOutputBody().getAdditionOutput().setAdditionResult(answer);
	
	// Encode the response and send it back to the requestor.
	sendJausMessage( theAnswer, new JausAddress(sender) );

	System.out.println("answer sent to client");

}

public void serverInitializedAction()
{
	/// This is the action for the transitionToReady Transition. 
    /// Add in whatever code is needed when transitioning from 
    /// Init to Ready

    System.out.println("Transitioned from Init to Ready. Ready to begin adding!");

    // Nothing else needs to be done here. We'll sit in 
    // READY until we get a QueryAddition
    // message. When that happens, we'll trigger a self-transition 
    // back into READY that 
    // computes the answer and sends it back to the requestor.

}



	
}

