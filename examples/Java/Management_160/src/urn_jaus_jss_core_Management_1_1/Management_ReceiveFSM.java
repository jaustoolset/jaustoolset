
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

import src.urn_jaus_jss_core_Transport_1_1.Transport_ReceiveFSM;
import src.urn_jaus_jss_core_Events_1_1.Events_ReceiveFSM;
import src.urn_jaus_jss_core_AccessControl_1_1.AccessControl_ReceiveFSM;


public class Management_ReceiveFSM extends StateMachine{
	protected boolean running;
	Transport_ReceiveFSM pTransport_ReceiveFSM;
	Events_ReceiveFSM pEvents_ReceiveFSM;
	AccessControl_ReceiveFSM pAccessControl_ReceiveFSM;
    Management_ReceiveFSMContext context;    
		
	public Management_ReceiveFSM(Transport_ReceiveFSM pTransport_ReceiveFSM, Events_ReceiveFSM pEvents_ReceiveFSM, AccessControl_ReceiveFSM pAccessControl_ReceiveFSM)
{

	/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
	 */
	context = new Management_ReceiveFSMContext(this);

	this.pTransport_ReceiveFSM = pTransport_ReceiveFSM;
	this.pEvents_ReceiveFSM = pEvents_ReceiveFSM;
	this.pAccessControl_ReceiveFSM = pAccessControl_ReceiveFSM;
}

	
	/// Handle notifications on parent state changes
	public void setupNotifications()
	{
		pAccessControl_ReceiveFSM.registerNotification("Receiving_Ready_NotControlled_Available", ieHandler, "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_Available_Standby", "AccessControl_ReceiveFSM");
		pAccessControl_ReceiveFSM.registerNotification("Receiving_Ready_NotControlled_NotAvailable", ieHandler, "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_Init", "AccessControl_ReceiveFSM");
		pAccessControl_ReceiveFSM.registerNotification("Receiving_Ready_NotControlled", ieHandler, "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_Available_Standby", "AccessControl_ReceiveFSM");
		pAccessControl_ReceiveFSM.registerNotification("Receiving_Ready_Controlled_Available", ieHandler, "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_Standby", "AccessControl_ReceiveFSM");
		pAccessControl_ReceiveFSM.registerNotification("Receiving_Ready_Controlled_NotAvailable", ieHandler, "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable_Emergency", "AccessControl_ReceiveFSM");
		pAccessControl_ReceiveFSM.registerNotification("Receiving_Ready_Controlled", ieHandler, "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_Controlled_Available_Standby", "AccessControl_ReceiveFSM");
		pAccessControl_ReceiveFSM.registerNotification("Receiving_Ready", ieHandler, "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_Available_Standby", "AccessControl_ReceiveFSM");
		pAccessControl_ReceiveFSM.registerNotification("Receiving", ieHandler, "InternalStateChange_To_Management_ReceiveFSM_Receiving_Ready_NotControlled_Available_Standby", "AccessControl_ReceiveFSM");
		registerNotification("Receiving_Ready_NotControlled_Available_Standby", pAccessControl_ReceiveFSM.getHandler(), "InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_Available", "Management_ReceiveFSM");
		registerNotification("Receiving_Ready_NotControlled_Available", pAccessControl_ReceiveFSM.getHandler(), "InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_Available", "Management_ReceiveFSM");
		registerNotification("Receiving_Ready_NotControlled_NotAvailable_Init", pAccessControl_ReceiveFSM.getHandler(), "InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable", "Management_ReceiveFSM");
		registerNotification("Receiving_Ready_NotControlled_NotAvailable_Failure", pAccessControl_ReceiveFSM.getHandler(), "InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable", "Management_ReceiveFSM");
		registerNotification("Receiving_Ready_NotControlled_NotAvailable_Shutdown", pAccessControl_ReceiveFSM.getHandler(), "InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable", "Management_ReceiveFSM");
		registerNotification("Receiving_Ready_NotControlled_NotAvailable_Emergency", pAccessControl_ReceiveFSM.getHandler(), "InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable", "Management_ReceiveFSM");
		registerNotification("Receiving_Ready_NotControlled_NotAvailable", pAccessControl_ReceiveFSM.getHandler(), "InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable", "Management_ReceiveFSM");
		registerNotification("Receiving_Ready_NotControlled", pAccessControl_ReceiveFSM.getHandler(), "InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled", "Management_ReceiveFSM");
		registerNotification("Receiving_Ready_Controlled_Available_Standby", pAccessControl_ReceiveFSM.getHandler(), "InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_Available", "Management_ReceiveFSM");
		registerNotification("Receiving_Ready_Controlled_Available_Ready", pAccessControl_ReceiveFSM.getHandler(), "InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_Available", "Management_ReceiveFSM");
		registerNotification("Receiving_Ready_Controlled_Available", pAccessControl_ReceiveFSM.getHandler(), "InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_Available", "Management_ReceiveFSM");
		registerNotification("Receiving_Ready_Controlled_NotAvailable_Emergency", pAccessControl_ReceiveFSM.getHandler(), "InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable", "Management_ReceiveFSM");
		registerNotification("Receiving_Ready_Controlled_NotAvailable", pAccessControl_ReceiveFSM.getHandler(), "InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable", "Management_ReceiveFSM");
		registerNotification("Receiving_Ready_Controlled", pAccessControl_ReceiveFSM.getHandler(), "InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled", "Management_ReceiveFSM");
		registerNotification("Receiving_Ready", pAccessControl_ReceiveFSM.getHandler(), "InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready", "Management_ReceiveFSM");
		registerNotification("Receiving", pAccessControl_ReceiveFSM.getHandler(), "InternalStateChange_To_AccessControl_ReceiveFSM_Receiving", "Management_ReceiveFSM");

	}

	/// Access for debug purposes
	public String getStateName()
	{
		return context.getState().getName();
	}

	public void DeleteIDAction(Receive.Body.ReceiveRec transportData)
{
	/// Insert User Code HERE
}

public void SendAction(String arg0, Receive.Body.ReceiveRec transportData)
{
	// This function gets called for all send actions. Basically, if we get trigger on QueryXXX, this function
	// will be called with a arg0 parameter of "ReportXXX". 

	System.out.println("In SendAction: " );
	
	// I.e., this function is called by the state machine whenever context.events_transport_RecieveTransition(XX) is called.

	if (arg0.equalsIgnoreCase("ReportStatus"))
	{
		
		ReportStatus status_msg = new ReportStatus();

		// Get the state from the context.  Note that since we are inside a transition, the "current state"
		// is ill-defined.  We instead use the state we left to execute this transition.  Recall that the state
		// is actually an amalgamation of all parent states, we're only concerned with the management
		String currentState = context.getPreviousState().getName();
		if (currentState.contains("_"))
		  currentState = currentState.substring(currentState.lastIndexOf("_")+1);
		if (currentState.equalsIgnoreCase("Init")) status_msg.getBody().getReportStatusRec().setStatus((short) 0);
		if (currentState.equalsIgnoreCase("Ready")) status_msg.getBody().getReportStatusRec().setStatus((short)1);
		if (currentState.equalsIgnoreCase("Standby")) status_msg.getBody().getReportStatusRec().setStatus((short)2);
		if (currentState.equalsIgnoreCase("Shutdown")) status_msg.getBody().getReportStatusRec().setStatus((short)3);
		if (currentState.equalsIgnoreCase("Failure")) status_msg.getBody().getReportStatusRec().setStatus((short)4);
		if (currentState.equalsIgnoreCase("Emergency")) status_msg.getBody().getReportStatusRec().setStatus((short)5);

		System.out.println("Sending ReportStatus: " + currentState);
		// Now send it to the requesting component
		JausAddress sender = new JausAddress( transportData.getSourceID().getSubFields());
	    sendJausMessage( status_msg, sender );
	}
}

public void SendAction(String arg0, String arg1)
{
	/// Insert User Code HERE
}

public void SendAction(String arg0, String arg1, Receive.Body.ReceiveRec transportData)
{
	/// Insert User Code HERE
}

public void StoreIDAction(Receive.Body.ReceiveRec transportData)
{
	/// Insert User Code HERE
}

public void initializeAction()
{
	// After initialization, send the Initialized event to ourselves
	System.out.println("In initializeAction()");
	ieHandler.invoke(new Initialized());
}

void popWrapper_367bc5868aa53b23a6c5a07701058fbf(ClearEmergency msg, Receive.Body.ReceiveRec transportData)
{
	String tempstr ="Receiving_Ready_NotControlled_NotAvailable_Emergency";
	String tempstr2 = context.peakTopStateStack();
	StringTokenizer leafStateTOK = new StringTokenizer(tempstr,"_");
	StringTokenizer stackStateTOK = new StringTokenizer(tempstr2,"_");
	String currentLeafStateTOK;
	String currentStackStateTOK;

	if(tempstr2.compareTo("Receiving_Ready_NotControlled_NotAvailable_Emergency") == 0)
	{
		DeleteIDAction(transportData);
		return;
	}

	currentLeafStateTOK = leafStateTOK.nextToken();
	currentStackStateTOK = stackStateTOK.nextToken();
	if(currentLeafStateTOK.compareTo(currentStackStateTOK) != 0)
	{
		DeleteIDAction(transportData);
		return;
	}
	currentLeafStateTOK = leafStateTOK.nextToken();
	currentStackStateTOK = stackStateTOK.nextToken();

	currentLeafStateTOK = leafStateTOK.nextToken();
	currentStackStateTOK = stackStateTOK.nextToken();
	if(currentLeafStateTOK.compareTo(currentStackStateTOK) != 0)
	{
		DeleteIDAction(transportData);
		return;
	}
	currentLeafStateTOK = leafStateTOK.nextToken();
	currentStackStateTOK = stackStateTOK.nextToken();

	currentLeafStateTOK = leafStateTOK.nextToken();
	currentStackStateTOK = stackStateTOK.nextToken();
	if(currentLeafStateTOK.compareTo(currentStackStateTOK) != 0)
	{
		DeleteIDAction(transportData);
		return;
	}
	currentLeafStateTOK = leafStateTOK.nextToken();
	currentStackStateTOK = stackStateTOK.nextToken();

	currentLeafStateTOK = leafStateTOK.nextToken();
	currentStackStateTOK = stackStateTOK.nextToken();
	if(currentLeafStateTOK.compareTo(currentStackStateTOK) != 0)
	{
		DeleteIDAction(transportData);
		return;
	}
	currentLeafStateTOK = leafStateTOK.nextToken();
	currentStackStateTOK = stackStateTOK.nextToken();

	currentLeafStateTOK = leafStateTOK.nextToken();
	currentStackStateTOK = stackStateTOK.nextToken();
	if(currentLeafStateTOK.compareTo(currentStackStateTOK) != 0)
	{
		DeleteIDAction(transportData);
		return;
	}
	currentLeafStateTOK = leafStateTOK.nextToken();
	currentStackStateTOK = stackStateTOK.nextToken();

}

void popWrapper_1014a7ff54cb3a51af2f80235d77d9d1(ClearEmergency msg, Receive.Body.ReceiveRec transportData)
{
	String tempstr ="Receiving_Ready_Controlled_NotAvailable_Emergency";
	String tempstr2 = context.peakTopStateStack();
	StringTokenizer leafStateTOK = new StringTokenizer(tempstr,"_");
	StringTokenizer stackStateTOK = new StringTokenizer(tempstr2,"_");
	String currentLeafStateTOK;
	String currentStackStateTOK;

	if(tempstr2.compareTo("Receiving_Ready_Controlled_NotAvailable_Emergency") == 0)
	{
		DeleteIDAction(transportData);
		return;
	}

	currentLeafStateTOK = leafStateTOK.nextToken();
	currentStackStateTOK = stackStateTOK.nextToken();
	if(currentLeafStateTOK.compareTo(currentStackStateTOK) != 0)
	{
		DeleteIDAction(transportData);
		return;
	}
	currentLeafStateTOK = leafStateTOK.nextToken();
	currentStackStateTOK = stackStateTOK.nextToken();

	currentLeafStateTOK = leafStateTOK.nextToken();
	currentStackStateTOK = stackStateTOK.nextToken();
	if(currentLeafStateTOK.compareTo(currentStackStateTOK) != 0)
	{
		DeleteIDAction(transportData);
		return;
	}
	currentLeafStateTOK = leafStateTOK.nextToken();
	currentStackStateTOK = stackStateTOK.nextToken();

	currentLeafStateTOK = leafStateTOK.nextToken();
	currentStackStateTOK = stackStateTOK.nextToken();
	if(currentLeafStateTOK.compareTo(currentStackStateTOK) != 0)
	{
		DeleteIDAction(transportData);
		return;
	}
	currentLeafStateTOK = leafStateTOK.nextToken();
	currentStackStateTOK = stackStateTOK.nextToken();

	currentLeafStateTOK = leafStateTOK.nextToken();
	currentStackStateTOK = stackStateTOK.nextToken();
	if(currentLeafStateTOK.compareTo(currentStackStateTOK) != 0)
	{
		DeleteIDAction(transportData);
		return;
	}
	currentLeafStateTOK = leafStateTOK.nextToken();
	currentStackStateTOK = stackStateTOK.nextToken();

	currentLeafStateTOK = leafStateTOK.nextToken();
	currentStackStateTOK = stackStateTOK.nextToken();
	if(currentLeafStateTOK.compareTo(currentStackStateTOK) != 0)
	{
		DeleteIDAction(transportData);
		return;
	}
	currentLeafStateTOK = leafStateTOK.nextToken();
	currentStackStateTOK = stackStateTOK.nextToken();

}



	public boolean isControllingClient(Receive.Body.ReceiveRec transportData)
{
	//// By default, inherited guards call the parent function.
	//// This can be replaced or modified as needed.
	return pAccessControl_ReceiveFSM.isControllingClient(transportData );
}

public boolean isIDStored(Receive.Body.ReceiveRec transportData)
{
	/// Insert User Code HERE
	return true;
}


}

