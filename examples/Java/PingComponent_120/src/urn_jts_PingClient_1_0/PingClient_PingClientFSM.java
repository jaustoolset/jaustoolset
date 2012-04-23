
package src.urn_jts_PingClient_1_0;

import java.util.StringTokenizer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import framework.transport.JausRouter;
import framework.transport.JausAddress;
import framework.internalEvents.*;
import framework.StateMachine;
import framework.messages.Message;
import statemap.*;
import src.urn_jts_PingClient_1_0.Messages.*;



public class PingClient_PingClientFSM extends StateMachine{
	protected boolean running;

    PingClient_PingClientFSMContext context;

    
	
	public PingClient_PingClientFSM()
{

	/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
	 */
		context = new PingClient_PingClientFSMContext(this);

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

	public void QueryHeartBeatPulseAction()
{
	QueryHeartbeatPulse query = new QueryHeartbeatPulse();
	sendJausMessage(query, jausRouter.getJausAddress());
}

public void printToScreenAction()
{
	System.out.println("Hello World!");
}



	
}

