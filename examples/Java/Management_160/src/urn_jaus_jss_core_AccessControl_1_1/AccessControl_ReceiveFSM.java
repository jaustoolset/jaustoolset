
package src.urn_jaus_jss_core_AccessControl_1_1;

import java.util.StringTokenizer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import framework.transport.JausRouter;
import framework.transport.JausAddress;
import framework.internalEvents.*;
import framework.StateMachine;
import framework.messages.Message;
import statemap.*;
import src.urn_jaus_jss_core_AccessControl_1_1.InternalEvents.*;
import src.urn_jaus_jss_core_AccessControl_1_1.Messages.*;

import src.urn_jaus_jss_core_Transport_1_1.Transport_ReceiveFSM;
import src.urn_jaus_jss_core_Events_1_1.Events_ReceiveFSM;


public class AccessControl_ReceiveFSM extends StateMachine{
	protected boolean running;
	Transport_ReceiveFSM pTransport_ReceiveFSM;
	Events_ReceiveFSM pEvents_ReceiveFSM;

    AccessControl_ReceiveFSMContext context;

    
	
	JausAddress currentController = null;
	short currentAuthority = 0;
	short defaultAuthority = 128; 
	
	public AccessControl_ReceiveFSM(Transport_ReceiveFSM pTransport_ReceiveFSM, Events_ReceiveFSM pEvents_ReceiveFSM)
{

	/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
	 */
		context = new AccessControl_ReceiveFSMContext(this);

	this.pTransport_ReceiveFSM = pTransport_ReceiveFSM;
	this.pEvents_ReceiveFSM = pEvents_ReceiveFSM;
}

	
	/// Handle notifications on parent state changes
	public void setupNotifications()
	{
		pEvents_ReceiveFSM.registerNotification("Receiving_Ready", ieHandler, "InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_Available", "Events_ReceiveFSM");
		pEvents_ReceiveFSM.registerNotification("Receiving", ieHandler, "InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_Available", "Events_ReceiveFSM");
		registerNotification("Receiving_Ready_NotControlled_Available", pEvents_ReceiveFSM.getHandler(), "InternalStateChange_To_Events_ReceiveFSM_Receiving_Ready", "AccessControl_ReceiveFSM");
		registerNotification("Receiving_Ready_NotControlled_NotAvailable", pEvents_ReceiveFSM.getHandler(), "InternalStateChange_To_Events_ReceiveFSM_Receiving_Ready", "AccessControl_ReceiveFSM");
		registerNotification("Receiving_Ready_NotControlled", pEvents_ReceiveFSM.getHandler(), "InternalStateChange_To_Events_ReceiveFSM_Receiving_Ready", "AccessControl_ReceiveFSM");
		registerNotification("Receiving_Ready_Controlled_Available", pEvents_ReceiveFSM.getHandler(), "InternalStateChange_To_Events_ReceiveFSM_Receiving_Ready", "AccessControl_ReceiveFSM");
		registerNotification("Receiving_Ready_Controlled_NotAvailable", pEvents_ReceiveFSM.getHandler(), "InternalStateChange_To_Events_ReceiveFSM_Receiving_Ready", "AccessControl_ReceiveFSM");
		registerNotification("Receiving_Ready_Controlled", pEvents_ReceiveFSM.getHandler(), "InternalStateChange_To_Events_ReceiveFSM_Receiving_Ready", "AccessControl_ReceiveFSM");
		registerNotification("Receiving_Ready", pEvents_ReceiveFSM.getHandler(), "InternalStateChange_To_Events_ReceiveFSM_Receiving_Ready", "AccessControl_ReceiveFSM");
		registerNotification("Receiving", pEvents_ReceiveFSM.getHandler(), "InternalStateChange_To_Events_ReceiveFSM_Receiving", "AccessControl_ReceiveFSM");

	}

	/// Access for debug purposes
	public String getStateName()
	{
		return context.getState().getName();
	}

	public void ResetTimerAction()
{
	/// Insert User Code HERE
}

public void SendAction(String arg0, Receive.Body.ReceiveRec transportData)
{
	/// Insert User Code HERE
}

public void SendAction(String arg0, String arg1)
{
	/// Insert User Code HERE
}

public void SendAction(String arg0, String arg1, Receive.Body.ReceiveRec transportData)
{
	if (arg0.equalsIgnoreCase("RejectControl"))
	{
		RejectControl reject_msg = new RejectControl();
		if (arg1.equalsIgnoreCase("CONTROL_RELEASED")) reject_msg.getBody().getRejectControlRec().setResponseCode((short)0);
		if (arg1.equalsIgnoreCase("NOT_ACCEPTED")) reject_msg.getBody().getRejectControlRec().setResponseCode((short)1);

		// Now send it to the requesting component
		JausAddress sender = new JausAddress( transportData.getSourceID().getSubFields());

	    sendJausMessage( reject_msg, sender );

	}

	else if (arg0.equalsIgnoreCase("ConfirmControl"))
	{
		ConfirmControl confirm_msg = new ConfirmControl();
		short responseCode = 0;
		if (arg1.equalsIgnoreCase("CONTROL_ACCEPTED")) responseCode = 0;
		if (arg1.equalsIgnoreCase("NOT_AVAILABLE")) responseCode = 1;
		if (arg1.equalsIgnoreCase("INSUFFICIENT_AUTHORITY")) responseCode = 2;
		System.out.println("Sending ConfirmControl with argument " + arg1 );
		confirm_msg.getBody().getConfirmControlRec().setResponseCode(responseCode);
		
		// Now send it to the requesting component
		JausAddress sender = new JausAddress( transportData.getSourceID().getSubFields());
		sendJausMessage( confirm_msg, sender );
	}
	else if (arg0.equalsIgnoreCase("ReportControl"))
	{
		ReportControl control_msg = new ReportControl();
		control_msg.getBody().getReportControlRec().setSubsystemID( 
										 (currentController == null) ? 0 : currentController.getSubsystemID() );
		control_msg.getBody().getReportControlRec().setNodeID( 
									(currentController == null) ? 0 : currentController.getNodeID() );
		control_msg.getBody().getReportControlRec().setComponentID( 
										 (currentController == null) ? 0 : currentController.getComponentID() );
		
		// Now send it to the requesting component
		JausAddress sender = new JausAddress(transportData.getSourceID().getSubFields());
	    sendJausMessage( control_msg, sender );
	}
}

public void SetAuthorityAction(RequestControl msg)
{
	System.out.println("Storing authority code of controller as " +
		(int) msg.getBody().getRequestControlRec().getAuthorityCode());

	currentAuthority = msg.getBody().getRequestControlRec().getAuthorityCode();
}

public void SetAuthorityAction(SetAuthority msg)
{
	/// Insert User Code HERE
}

public void StoreAddressAction(Receive.Body.ReceiveRec transportData)
{
	System.out.println("Storing address of controlling component as " + 
		(int)transportData.getSourceID().getSubsystemID() + "." + 
		(int)transportData.getSourceID().getNodeID() + "." +
		(int)transportData.getSourceID().getComponentID());

	currentController = new JausAddress(transportData.getSourceID().getSubFields());
}

public void initAction()
{
	/// Insert User Code HERE
}

public void resetTimerAction()
{
	/// Insert User Code HERE
}



	public boolean isAuthorityValid(SetAuthority msg)
{
	/// Insert User Code HERE
	return true;
}

public boolean isControllingClient(Receive.Body.ReceiveRec transportData)
{
	JausAddress requester = new JausAddress(transportData.getSourceID().getSubFields());
	if ((currentController != null) &&
		(requester.getSubsystemID() == currentController.getSubsystemID() &&
		requester.getNodeID()       == currentController.getNodeID() &&
		requester.getComponentID()  == currentController.getComponentID()))
	{
		return true;
	}
	return false;
}

public boolean isCurrentAuthorityLess(RequestControl msg)
{
	return (currentAuthority < msg.getBody().getRequestControlRec().getAuthorityCode());
}

public boolean isDefaultAuthorityGreater(RequestControl msg)
{
	System.out.println("isDefaultAuthorityGreater guard: Comparing default authority of " + defaultAuthority +" against " +
		"client authority of " + msg.getBody().getRequestControlRec().getAuthorityCode());
	return (defaultAuthority > msg.getBody().getRequestControlRec().getAuthorityCode());
}


}

