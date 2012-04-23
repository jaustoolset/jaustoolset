

using JTS;
using System;
	

	
namespace urn_jaus_jss_core_Management_1_1
{
	public class Management_ReceiveFSM : StateMachine{
	urn_jaus_jss_core_Transport_1_1.Transport_ReceiveFSM pTransport_ReceiveFSM;
	urn_jaus_jss_core_Events_1_1.Events_ReceiveFSM pEvents_ReceiveFSM;
	urn_jaus_jss_core_AccessControl_1_1.AccessControl_ReceiveFSM pAccessControl_ReceiveFSM;

		public Management_ReceiveFSMContext context;
		
		

		public Management_ReceiveFSM(urn_jaus_jss_core_Transport_1_1.Transport_ReceiveFSM pTransport_ReceiveFSM, urn_jaus_jss_core_Events_1_1.Events_ReceiveFSM pEvents_ReceiveFSM, urn_jaus_jss_core_AccessControl_1_1.AccessControl_ReceiveFSM pAccessControl_ReceiveFSM)
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
		public override void setupNotifications()
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
	
		public virtual void DeleteIDAction(Receive.Body.ReceiveRec transportData)
{
	/// Insert User Code HERE
}

public virtual void SendAction(string arg0, Receive.Body.ReceiveRec transportData)
{
    if (arg0.CompareTo("ReportStatus") == 0)
    {
        ReportStatus status_msg = new ReportStatus();

        // Get the state from the context.  Note that since we are inside a transition, the "current state"
        // is ill-defined.  We instead use the state we left to execute this transition.  Recall that the state
        // is actually an amalgamation of all parent states, we're only concerned with the management
        string currentState = context.PreviousState.Name;  //getPreviousState().getName();
        if (currentState.Contains("_"))
            currentState = currentState.Substring(currentState.LastIndexOf("_") + 1);
        if (currentState.CompareTo("Init") == 0) status_msg.getBody().getReportStatusRec().setStatus(0);
        if (currentState.CompareTo("Ready") == 0) status_msg.getBody().getReportStatusRec().setStatus(1);
        if (currentState.CompareTo("Standby") == 0) status_msg.getBody().getReportStatusRec().setStatus(2);
        if (currentState.CompareTo("Shutdown") == 0) status_msg.getBody().getReportStatusRec().setStatus(3);
        if (currentState.CompareTo("Failure") == 0) status_msg.getBody().getReportStatusRec().setStatus(4);
        if (currentState.CompareTo("Emergency") == 0) status_msg.getBody().getReportStatusRec().setStatus(5);

        Console.WriteLine("Sending ReportStatus: " + currentState);

        // Now send it to the requesting component
        JausAddress sender = new JausAddress((ushort)transportData.getSourceID().getSubsystemID(),
                                               (byte)transportData.getSourceID().getNodeID(),
                                               (byte)transportData.getSourceID().getComponentID());
        sendJausMessage(status_msg, sender);
    } 
}


public virtual void SendAction(string arg0, string arg1)
{
	/// Insert User Code HERE
}

public virtual void SendAction(string arg0, string arg1, Receive.Body.ReceiveRec transportData)
{
	/// Insert User Code HERE
}

public virtual void StoreIDAction(Receive.Body.ReceiveRec transportData)
{
	/// Insert User Code HERE
}

public virtual void initializeAction()
{
    Console.WriteLine("In initializeAction()");
    ieHandler.invoke(new Initialized());
}

public void popWrapper_367bc5868aa53b23a6c5a07701058fbf(ClearEmergency msg, Receive.Body.ReceiveRec transportData)
{
	string tempstr = "Receiving_Ready_NotControlled_NotAvailable_Emergency";
	string tempstr2 = context.peakTopStateStack();
	string[] leafStateTOK = tempstr.Split(new char[] {'_'});
	string[] stackStateTOK = tempstr2.Split(new char[] {'_'});
	int currentToken = 0;

	if(tempstr2.CompareTo("Receiving_Ready_NotControlled_NotAvailable_Emergency") == 0)
	{
		DeleteIDAction(transportData);
		return;
	}

	if(leafStateTOK[currentToken].CompareTo(stackStateTOK[currentToken]) != 0)
	{
		DeleteIDAction(transportData);
		return;
	}
	currentToken++;
	if(currentToken >= stackStateTOK.Length || currentToken >= leafStateTOK.Length)
		{
		Console.WriteLine("Problem with StringTokenizer.");
	}

	if(leafStateTOK[currentToken].CompareTo(stackStateTOK[currentToken]) != 0)
	{
		DeleteIDAction(transportData);
		return;
	}
	currentToken++;
	if(currentToken >= stackStateTOK.Length || currentToken >= leafStateTOK.Length)
		{
		Console.WriteLine("Problem with StringTokenizer.");
	}

	if(leafStateTOK[currentToken].CompareTo(stackStateTOK[currentToken]) != 0)
	{
		DeleteIDAction(transportData);
		return;
	}
	currentToken++;
	if(currentToken >= stackStateTOK.Length || currentToken >= leafStateTOK.Length)
		{
		Console.WriteLine("Problem with StringTokenizer.");
	}

	if(leafStateTOK[currentToken].CompareTo(stackStateTOK[currentToken]) != 0)
	{
		DeleteIDAction(transportData);
		return;
	}
	currentToken++;
	if(currentToken >= stackStateTOK.Length || currentToken >= leafStateTOK.Length)
		{
		Console.WriteLine("Problem with StringTokenizer.");
	}

	if(leafStateTOK[currentToken].CompareTo(stackStateTOK[currentToken]) != 0)
	{
		DeleteIDAction(transportData);
		return;
	}
	currentToken++;
	if(currentToken >= stackStateTOK.Length || currentToken >= leafStateTOK.Length)
		{
		Console.WriteLine("Problem with StringTokenizer.");
	}

}

public void popWrapper_1014a7ff54cb3a51af2f80235d77d9d1(ClearEmergency msg, Receive.Body.ReceiveRec transportData)
{
	string tempstr = "Receiving_Ready_Controlled_NotAvailable_Emergency";
	string tempstr2 = context.peakTopStateStack();
	string[] leafStateTOK = tempstr.Split(new char[] {'_'});
	string[] stackStateTOK = tempstr2.Split(new char[] {'_'});
	int currentToken = 0;

	if(tempstr2.CompareTo("Receiving_Ready_Controlled_NotAvailable_Emergency") == 0)
	{
		DeleteIDAction(transportData);
		return;
	}

	if(leafStateTOK[currentToken].CompareTo(stackStateTOK[currentToken]) != 0)
	{
		DeleteIDAction(transportData);
		return;
	}
	currentToken++;
	if(currentToken >= stackStateTOK.Length || currentToken >= leafStateTOK.Length)
		{
		Console.WriteLine("Problem with StringTokenizer.");
	}

	if(leafStateTOK[currentToken].CompareTo(stackStateTOK[currentToken]) != 0)
	{
		DeleteIDAction(transportData);
		return;
	}
	currentToken++;
	if(currentToken >= stackStateTOK.Length || currentToken >= leafStateTOK.Length)
		{
		Console.WriteLine("Problem with StringTokenizer.");
	}

	if(leafStateTOK[currentToken].CompareTo(stackStateTOK[currentToken]) != 0)
	{
		DeleteIDAction(transportData);
		return;
	}
	currentToken++;
	if(currentToken >= stackStateTOK.Length || currentToken >= leafStateTOK.Length)
		{
		Console.WriteLine("Problem with StringTokenizer.");
	}

	if(leafStateTOK[currentToken].CompareTo(stackStateTOK[currentToken]) != 0)
	{
		DeleteIDAction(transportData);
		return;
	}
	currentToken++;
	if(currentToken >= stackStateTOK.Length || currentToken >= leafStateTOK.Length)
		{
		Console.WriteLine("Problem with StringTokenizer.");
	}

	if(leafStateTOK[currentToken].CompareTo(stackStateTOK[currentToken]) != 0)
	{
		DeleteIDAction(transportData);
		return;
	}
	currentToken++;
	if(currentToken >= stackStateTOK.Length || currentToken >= leafStateTOK.Length)
		{
		Console.WriteLine("Problem with StringTokenizer.");
	}

}



		public virtual bool isControllingClient(Receive.Body.ReceiveRec transportData)
{
	//// By default, inherited guards call the parent function.
	//// This can be replaced or modified as needed.
	return pAccessControl_ReceiveFSM.isControllingClient( transportData );
}

public virtual bool isIDStored(Receive.Body.ReceiveRec transportData)
{
	/// Insert User Code HERE
	return true;
}


	}
	
}

