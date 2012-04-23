

using JTS;
using System;
	

	
namespace urn_jts_PingServer_1_0
{
	public class PingServer_PingFSM : StateMachine{

		public PingServer_PingFSMContext context;
		
		

		public PingServer_PingFSM()
	{

		/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
		 */
		context = new PingServer_PingFSMContext(this);

	}

		
		/// Handle notifications on parent state changes
		public override void setupNotifications()
		{

		}
		
		public virtual void ReportHeartbeatPulseAction()
{
    // Send a RHP message to the local component.
    ReportHeartbeatPulse response = new ReportHeartbeatPulse();
    sendJausMessage(response, jausRouter.getJausAddress());
}



		
	}
	
}

