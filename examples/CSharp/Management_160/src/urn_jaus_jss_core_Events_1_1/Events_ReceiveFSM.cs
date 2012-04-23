

using JTS;
using System;
	

	
namespace urn_jaus_jss_core_Events_1_1
{
	public class Events_ReceiveFSM : StateMachine{
	urn_jaus_jss_core_Transport_1_1.Transport_ReceiveFSM pTransport_ReceiveFSM;

		public Events_ReceiveFSMContext context;
		
		

		public Events_ReceiveFSM(urn_jaus_jss_core_Transport_1_1.Transport_ReceiveFSM pTransport_ReceiveFSM)
	{

		/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
		 */
		context = new Events_ReceiveFSMContext(this);

	this.pTransport_ReceiveFSM = pTransport_ReceiveFSM;
	}

		
		/// Handle notifications on parent state changes
		public override void setupNotifications()
		{
		pTransport_ReceiveFSM.registerNotification("Receiving", ieHandler, "InternalStateChange_To_Events_ReceiveFSM_Receiving_Ready", "Transport_ReceiveFSM");
		registerNotification("Receiving_Ready", pTransport_ReceiveFSM.getHandler(), "InternalStateChange_To_Transport_ReceiveFSM_Receiving", "Events_ReceiveFSM");
		registerNotification("Receiving", pTransport_ReceiveFSM.getHandler(), "InternalStateChange_To_Transport_ReceiveFSM_Receiving", "Events_ReceiveFSM");

		}
		
		public virtual void SendAction(string arg0)
{
	/// Insert User Code HERE
}

public virtual void SendAction(string arg0, CancelEvent msg, Receive.Body.ReceiveRec transportData)
{
	/// Insert User Code HERE
}

public virtual void SendAction(string arg0, CreateEvent msg, Receive.Body.ReceiveRec transportData)
{
	/// Insert User Code HERE
}

public virtual void SendAction(string arg0, QueryEventTimeout msg, Receive.Body.ReceiveRec transportData)
{
	/// Insert User Code HERE
}

public virtual void SendAction(string arg0, QueryEvents msg, Receive.Body.ReceiveRec transportData)
{
	/// Insert User Code HERE
}

public virtual void SendAction(string arg0, UpdateEvent msg, Receive.Body.ReceiveRec transportData)
{
	/// Insert User Code HERE
}

public virtual void cancelEventAction()
{
	/// Insert User Code HERE
}

public virtual void cancelEventAction(CancelEvent msg)
{
	/// Insert User Code HERE
}

public virtual void createEventAction(CreateEvent msg)
{
	/// Insert User Code HERE
}

public virtual void resetEventTimerAction()
{
	/// Insert User Code HERE
}

public virtual void sendEventAction()
{
	/// Insert User Code HERE
}

public virtual void sendRejectEventRequestAction()
{
	/// Insert User Code HERE
}

public virtual void stopEventTimerAction()
{
	/// Insert User Code HERE
}

public virtual void updateEventAction(CreateEvent msg)
{
	/// Insert User Code HERE
}

public virtual void updateEventAction(UpdateEvent msg)
{
	/// Insert User Code HERE
}



		public virtual bool eventExists()
{
	/// Insert User Code HERE
	return false;
}

public virtual bool eventExists(CancelEvent msg)
{
	/// Insert User Code HERE
	return false;
}

public virtual bool eventExists(CreateEvent msg)
{
	/// Insert User Code HERE
	return false;
}

public virtual bool eventExists(UpdateEvent msg)
{
	/// Insert User Code HERE
	return false;
}

public virtual bool isSupported(CreateEvent msg)
{
	/// Insert User Code HERE
	return false;
}

public virtual bool isSupported(UpdateEvent msg)
{
	/// Insert User Code HERE
	return false;
}


	}
	
}

