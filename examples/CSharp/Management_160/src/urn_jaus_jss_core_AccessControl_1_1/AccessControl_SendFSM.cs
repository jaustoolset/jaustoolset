

using JTS;
using System;
	

	
namespace urn_jaus_jss_core_AccessControl_1_1
{
	public class AccessControl_SendFSM : StateMachine{
	urn_jaus_jss_core_Transport_1_1.Transport_SendFSM pTransport_SendFSM;
	urn_jaus_jss_core_Events_1_1.Events_SendFSM pEvents_SendFSM;

		public AccessControl_SendFSMContext context;
		
		

		public AccessControl_SendFSM(urn_jaus_jss_core_Transport_1_1.Transport_SendFSM pTransport_SendFSM, urn_jaus_jss_core_Events_1_1.Events_SendFSM pEvents_SendFSM)
	{

		/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
		 */
		context = new AccessControl_SendFSMContext(this);

	this.pTransport_SendFSM = pTransport_SendFSM;
	this.pEvents_SendFSM = pEvents_SendFSM;
	}

		
		/// Handle notifications on parent state changes
		public override void setupNotifications()
		{
		pEvents_SendFSM.registerNotification("Sending", ieHandler, "InternalStateChange_To_AccessControl_SendFSM_Sending", "Events_SendFSM");
		registerNotification("Sending", pEvents_SendFSM.getHandler(), "InternalStateChange_To_Events_SendFSM_Sending", "AccessControl_SendFSM");

		}
		
		

		
	}
	
}

