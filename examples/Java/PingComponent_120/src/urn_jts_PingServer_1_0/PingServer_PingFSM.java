
package src.urn_jts_PingServer_1_0;

import java.util.StringTokenizer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import framework.transport.JausRouter;
import framework.transport.JausAddress;
import framework.internalEvents.*;
import framework.StateMachine;
import framework.messages.Message;
import statemap.*;
import src.urn_jts_PingServer_1_0.Messages.*;



public class PingServer_PingFSM extends StateMachine{
	protected boolean running;

    PingServer_PingFSMContext context;

    
	
	public PingServer_PingFSM()
{

	/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
	 */
		context = new PingServer_PingFSMContext(this);

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

	public void ReportHeartbeatPulseAction()
{
	// Send a RHP message to the local component.
	ReportHeartbeatPulse response = new ReportHeartbeatPulse();
	sendJausMessage(response, jausRouter.getJausAddress());
}



	
}

