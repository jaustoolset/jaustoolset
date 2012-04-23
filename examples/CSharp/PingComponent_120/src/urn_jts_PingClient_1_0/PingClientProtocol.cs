

using JTS;
using System;
	

	
namespace urn_jts_PingClient_1_0
{
	public class PingClientProtocol : StateMachine{
		protected bool running;
		public PingClientProtocolContext context;
		
		

		public PingClientProtocol()
	{

		/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
		 */
		context = new PingClientProtocolContext(this);
	}

	
		public void stop()
		{
		}
	
		public void run()
		{
		}

		public virtual void broadcastQueryHeartbeatPulseAction()
{
    QueryHeartbeatPulse query = new QueryHeartbeatPulse();
    sendJausMessage(query, jausRouter.getJausAddress());

}

public virtual void printToScreenAction()
{
    Console.WriteLine("Hello World!");
}



		
	}
	
}

