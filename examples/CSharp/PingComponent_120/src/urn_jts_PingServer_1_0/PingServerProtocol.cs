

using JTS;
using System;
	

	
namespace urn_jts_PingServer_1_0
{
	public class PingServerProtocol : StateMachine{
		protected bool running;
		public PingServerProtocolContext context;
		
		

		public PingServerProtocol()
	{

		/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
		 */
		context = new PingServerProtocolContext(this);
	}

	
		public void stop()
		{
		}
	
		public void run()
		{
		}

		public virtual void sendReportHeartbeatPulseAction()
{
    // Send a RHP message to the local component.
    ReportHeartbeatPulse response = new ReportHeartbeatPulse();
    sendJausMessage(response, jausRouter.getJausAddress());

}



		
	}
	
}

