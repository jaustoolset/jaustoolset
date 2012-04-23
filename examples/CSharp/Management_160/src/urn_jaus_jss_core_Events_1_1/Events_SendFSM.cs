

using JTS;
using System;
	

	
namespace urn_jaus_jss_core_Events_1_1
{
	public class Events_SendFSM : StateMachine{
	urn_jaus_jss_core_Transport_1_1.Transport_SendFSM pTransport_SendFSM;

		public Events_SendFSMContext context;
		
		

		public Events_SendFSM(urn_jaus_jss_core_Transport_1_1.Transport_SendFSM pTransport_SendFSM)
	{

		/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
		 */
		context = new Events_SendFSMContext(this);

	this.pTransport_SendFSM = pTransport_SendFSM;
	}

		
		/// Handle notifications on parent state changes
		public override void setupNotifications()
		{
		pTransport_SendFSM.registerNotification("Sending", ieHandler, "InternalStateChange_To_Events_SendFSM_Sending", "Transport_SendFSM");
		registerNotification("Sending", pTransport_SendFSM.getHandler(), "InternalStateChange_To_Transport_SendFSM_Sending", "Events_SendFSM");

		}
		
		

		
	}
	
}

