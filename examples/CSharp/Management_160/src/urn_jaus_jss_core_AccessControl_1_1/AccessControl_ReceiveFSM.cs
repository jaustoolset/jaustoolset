

using JTS;
using System;
	

	
namespace urn_jaus_jss_core_AccessControl_1_1
{
	public class AccessControl_ReceiveFSM : StateMachine
	{
		urn_jaus_jss_core_Transport_1_1.Transport_ReceiveFSM pTransport_ReceiveFSM;
		urn_jaus_jss_core_Events_1_1.Events_ReceiveFSM pEvents_ReceiveFSM;
		public AccessControl_ReceiveFSMContext context;

	    protected JausAddress currentController = null;
        protected byte currentAuthority = 0;
        protected byte defaultAuthority = 128;
		
		

		public AccessControl_ReceiveFSM(urn_jaus_jss_core_Transport_1_1.Transport_ReceiveFSM pTransport_ReceiveFSM, urn_jaus_jss_core_Events_1_1.Events_ReceiveFSM pEvents_ReceiveFSM)
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
		public override void setupNotifications()
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
		
		public virtual void ResetTimerAction()
{
	/// Insert User Code HERE
}

public virtual void SendAction(string arg0, Receive.Body.ReceiveRec transportData)
{
	/// Insert User Code HERE
}

public virtual void SendAction(string arg0, string arg1)
{
	/// Insert User Code HERE
}

public virtual void SendAction(string arg0, string arg1, Receive.Body.ReceiveRec transportData)
{
    if (arg0.CompareTo("RejectControl") == 0)
    {
        RejectControl reject_msg = new RejectControl();
        if (arg1.CompareTo("CONTROL_RELEASED") == 0) reject_msg.getBody().getRejectControlRec().setResponseCode(0);
        if (arg1.CompareTo("NOT_ACCEPTED") == 0) reject_msg.getBody().getRejectControlRec().setResponseCode(1);

        // Now send it to the requesting component
        JausAddress sender = new JausAddress((ushort)transportData.getSourceID().getSubsystemID(),
                                            (byte)transportData.getSourceID().getNodeID(),
                                            (byte)transportData.getSourceID().getComponentID());

        sendJausMessage(reject_msg, sender);

    }

    else if (arg0.CompareTo("ConfirmControl") == 0)
    {
        ConfirmControl confirm_msg = new ConfirmControl();
        byte responseCode = 0;
        if (arg1.CompareTo("CONTROL_ACCEPTED") == 0) responseCode = 0;
        if (arg1.CompareTo("NOT_AVAILABLE") == 0) responseCode = 1;
        if (arg1.CompareTo("INSUFFICIENT_AUTHORITY") == 0) responseCode = 2;
        Console.WriteLine("Sending ConfirmControl with argument " + arg1);
        confirm_msg.getBody().getConfirmControlRec().setResponseCode(responseCode);

        // Now send it to the requesting component
        JausAddress sender = new JausAddress((ushort)transportData.getSourceID().getSubsystemID(),
                                            (byte)transportData.getSourceID().getNodeID(),
                                            (byte)transportData.getSourceID().getComponentID());
        sendJausMessage(confirm_msg, sender);
    }
    else if (arg0.CompareTo("ReportControl") == 0)
    {
        ReportControl control_msg = new ReportControl();
        control_msg.getBody().getReportControlRec().setSubsystemID((ushort)(
                                         (currentController == null) ? 0 : currentController.getSubsystemID()));
        control_msg.getBody().getReportControlRec().setNodeID((byte)(
                                    (currentController == null) ? 0 : currentController.getNodeID()));
        control_msg.getBody().getReportControlRec().setComponentID((byte)(
                                         (currentController == null) ? 0 : currentController.getComponentID()));

        // Now send it to the requesting component
        JausAddress sender = new JausAddress((ushort)transportData.getSourceID().getSubsystemID(),
                                            (byte)transportData.getSourceID().getNodeID(),
                                            (byte)transportData.getSourceID().getComponentID());
        sendJausMessage(control_msg, sender);
    }
}

public virtual void SetAuthorityAction(RequestControl msg)
{
    Console.WriteLine("Storing authority code of controller as " + msg.getBody().getRequestControlRec().getAuthorityCode());
    currentAuthority = msg.getBody().getRequestControlRec().getAuthorityCode();
}

public virtual void SetAuthorityAction(SetAuthority msg)
{
	/// Insert User Code HERE
}

public virtual void StoreAddressAction(Receive.Body.ReceiveRec transportData)
{
    Console.WriteLine( "Storing address of controlling component as " + 
        (ushort)transportData.getSourceID().getSubsystemID(),
        (byte)transportData.getSourceID().getNodeID(),
        (byte)transportData.getSourceID().getComponentID());

    currentController = new JausAddress((ushort)transportData.getSourceID().getSubsystemID(),
                                           (byte)transportData.getSourceID().getNodeID(),
                                           (byte)transportData.getSourceID().getComponentID());
}

public virtual void initAction()
{
	/// Insert User Code HERE
}

public virtual void resetTimerAction()
{
	/// Insert User Code HERE
}



public virtual bool isAuthorityValid(SetAuthority msg)
{
	return true;
}

public virtual bool isControllingClient(Receive.Body.ReceiveRec transportData)
{
    JausAddress requester = new JausAddress((ushort)transportData.getSourceID().getSubsystemID(),
                                            (byte)transportData.getSourceID().getNodeID(),
                                            (byte)transportData.getSourceID().getComponentID());

    if ((currentController != null) &&
        (requester.getSubsystemID() == currentController.getSubsystemID() &&
        requester.getNodeID() == currentController.getNodeID() &&
        requester.getComponentID() == currentController.getComponentID()))
    {
        return true;
    }
    return false;
}

public virtual bool isCurrentAuthorityLess(RequestControl msg)
{
    return (currentAuthority < msg.getBody().getRequestControlRec().getAuthorityCode());
}

public virtual bool isDefaultAuthorityGreater(RequestControl msg)
{
    Console.WriteLine("isDefaultAuthorityGreater guard: Comparing default authority of " + defaultAuthority + " against " +
        "client authority of " + msg.getBody().getRequestControlRec().getAuthorityCode());
    return (defaultAuthority > msg.getBody().getRequestControlRec().getAuthorityCode());
}


	}
	
}

