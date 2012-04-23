
package src.urn_jaus_jss_core_Transport_1_1;

import java.util.StringTokenizer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import framework.transport.JausRouter;
import framework.transport.JausAddress;
import framework.internalEvents.*;
import framework.StateMachine;
import framework.messages.Message;
import statemap.*;
import src.urn_jaus_jss_core_Transport_1_1.InternalEvents.*;



public class Transport_SendFSM extends StateMachine{
	protected boolean running;

    Transport_SendFSMContext context;

    
	
	public Transport_SendFSM()
{

	/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
	 */
		context = new Transport_SendFSMContext(this);

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

	public void BroadcastGlobalEnqueueAction(BroadcastGlobal msg)
{
	sendJausMessage(
		msg.getBody().getBroadcastRec().getMessagePayload().getLength(),
		msg.getBody().getBroadcastRec().getMessagePayload().getData(),
		new JausAddress(0xFFFF,(short) 0xFF,(short) 0xFF));
}

public void BroadcastLocalEnqueueAction(BroadcastLocal msg)
{
	sendJausMessage(
		msg.getBody().getBroadcastRec().getMessagePayload().getLength(),
		msg.getBody().getBroadcastRec().getMessagePayload().getData(),
		new JausAddress(jausRouter.getJausAddress().getSubsystemID(), (short) 0xFF, (short) 0xFF));
}

public void EnqueueAction(Send msg)
{
	sendJausMessage(
		msg.getBody().getSendRec().getMessagePayload().getLength(),
		msg.getBody().getSendRec().getMessagePayload().getData(),
		new JausAddress((int)msg.getBody().getSendRec().getDestinationID().getSubsystemID(),
			(short) msg.getBody().getSendRec().getDestinationID().getNodeID(),
			(short) msg.getBody().getSendRec().getDestinationID().getComponentID()));
}



	
}

