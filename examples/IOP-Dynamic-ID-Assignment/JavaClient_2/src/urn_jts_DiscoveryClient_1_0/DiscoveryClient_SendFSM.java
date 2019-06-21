
package src.urn_jts_DiscoveryClient_1_0;

import java.util.StringTokenizer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import framework.transport.JausRouter;
import framework.transport.JausAddress;
import framework.internalEvents.*;
import framework.StateMachine;
import framework.messages.Message;
import statemap.*;
import src.urn_jts_DiscoveryClient_1_0.InternalEvents.*;
import src.urn_jts_DiscoveryClient_1_0.Messages.*;

import src.urn_jaus_jss_core_Transport_1_1.Transport_SendFSM;


public class DiscoveryClient_SendFSM extends StateMachine{
	protected boolean running;
	Transport_SendFSM pTransport_SendFSM;

    DiscoveryClient_SendFSMContext context;

    
	
	public DiscoveryClient_SendFSM(Transport_SendFSM pTransport_SendFSM)
{

	/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
	 */
		context = new DiscoveryClient_SendFSMContext(this);

	this.pTransport_SendFSM = pTransport_SendFSM;
}

	
	/// Handle notifications on parent state changes
	public void setupNotifications()
	{
		pTransport_SendFSM.registerNotification("Sending", ieHandler, "InternalStateChange_To_DiscoveryClient_SendFSM_Sending", "Transport_SendFSM");
		registerNotification("Sending", pTransport_SendFSM.getHandler(), "InternalStateChange_To_Transport_SendFSM_Sending", "DiscoveryClient_SendFSM");

	}

	/// Access for debug purposes
	public String getStateName()
	{
		return context.getState().getName();
	}

	

	
}

