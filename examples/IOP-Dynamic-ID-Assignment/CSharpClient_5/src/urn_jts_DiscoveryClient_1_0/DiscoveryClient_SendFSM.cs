

using JTS;
using System;
	

	
namespace urn_jts_DiscoveryClient_1_0
{
	public class DiscoveryClient_SendFSM : StateMachine{
	urn_jaus_jss_core_Transport_1_1.Transport_SendFSM pTransport_SendFSM;

		public DiscoveryClient_SendFSMContext context;
		
		

		public DiscoveryClient_SendFSM(urn_jaus_jss_core_Transport_1_1.Transport_SendFSM pTransport_SendFSM)
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
		public override void setupNotifications()
		{
		pTransport_SendFSM.registerNotification("Sending", ieHandler, "InternalStateChange_To_DiscoveryClient_SendFSM_Sending", "Transport_SendFSM");
		registerNotification("Sending", pTransport_SendFSM.getHandler(), "InternalStateChange_To_Transport_SendFSM_Sending", "DiscoveryClient_SendFSM");

		}
	
		

		
	}
	
}

