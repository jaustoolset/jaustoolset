
package src.urn_jaus_example_addition_client_1_0;

import java.util.StringTokenizer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import framework.transport.JausRouter;
import framework.transport.JausAddress;
import framework.internalEvents.*;
import framework.StateMachine;
import framework.messages.Message;
import statemap.*;
import src.urn_jaus_example_addition_client_1_0.InternalEvents.*;
import src.urn_jaus_example_addition_client_1_0.Messages.*;



public class AdditionClientServiceDef_additionClientFSM extends StateMachine{
	protected boolean running;

    AdditionClientServiceDef_additionClientFSMContext context;

    
	
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
	public void setupNotifications()
	{

	}

	/// Access for debug purposes
	public String getStateName()
	{
		return context.getState().getName();
	}

	public void printAnswerToScreenAction(ReportAddition msg)
{
	System.out.println( "Transitioned back to Ready");
	System.out.println( "  The answer is "  
						+ msg.getAdditionOutputBody().getAdditionOutput().getAdditionResult());

}

public void serviceInitializedAction()
{
	System.out.println("In Ready state. Let's start adding...");

  // This is the basic message type for our query. 
  QueryAddition query = new QueryAddition();
  
  // The message contains a body, with a record. 
  query.getAdditionInputBody().getAdditionInput().setA1((long)500);
  query.getAdditionInputBody().getAdditionInput().setA2((long)500);

  // Send the response to the server on this subsystem and node.  The
  // Component ID is fixed at 150.
  JausAddress server = new JausAddress(
                   jausRouter.getJausAddress().getSubsystemID(),
  		        jausRouter.getJausAddress().getNodeID(),
		        (short) 150);

  // Encode the request and send it to the server.
  sendJausMessage( query, server );

  System.out.println("Send addition request");

}

public void serviceStartedAction()
{
	System.out.println( "Addition client started");

    /// We now generate an internal event, which will be handled up
    /// above, resulting in a transition call to move us from 
    /// Init to Ready
    ieHandler.invoke(new InitToReadyEventDef());
    System.out.println("Sent internal event to transition to Ready");

}



	
}

