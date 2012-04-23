
package src.urn_jaus_jss_core_Management_1_1;

import java.util.StringTokenizer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import framework.transport.JausRouter;
import framework.transport.JausAddress;
import framework.internalEvents.*;
import framework.StateMachine;
import framework.messages.Message;
import statemap.*;
import src.urn_jaus_jss_core_Management_1_1.InternalEvents.*;
import src.urn_jaus_jss_core_Management_1_1.Messages.*;

import src.urn_jaus_jss_core_Transport_1_1.Transport_SendFSM;
import src.urn_jaus_jss_core_Events_1_1.Events_SendFSM;
import src.urn_jaus_jss_core_AccessControl_1_1.AccessControl_SendFSM;


public class Management_SendFSM extends StateMachine{
	protected boolean running;
	Transport_SendFSM pTransport_SendFSM;
	Events_SendFSM pEvents_SendFSM;
	AccessControl_SendFSM pAccessControl_SendFSM;

    Management_SendFSMContext context;

    
	
	public Management_SendFSM(Transport_SendFSM pTransport_SendFSM, Events_SendFSM pEvents_SendFSM, AccessControl_SendFSM pAccessControl_SendFSM)
{

	/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
	 */
	context = new Management_SendFSMContext(this);

	this.pTransport_SendFSM = pTransport_SendFSM;
	this.pEvents_SendFSM = pEvents_SendFSM;
	this.pAccessControl_SendFSM = pAccessControl_SendFSM;
}

	
	/// Handle notifications on parent state changes
	public void setupNotifications()
	{
		pAccessControl_SendFSM.registerNotification("Sending", ieHandler, "InternalStateChange_To_Management_SendFSM_Sending", "AccessControl_SendFSM");
		registerNotification("Sending", pAccessControl_SendFSM.getHandler(), "InternalStateChange_To_AccessControl_SendFSM_Sending", "Management_SendFSM");

	}

	/// Access for debug purposes
	public String getStateName()
	{
		return context.getState().getName();
	}

	

	
}

