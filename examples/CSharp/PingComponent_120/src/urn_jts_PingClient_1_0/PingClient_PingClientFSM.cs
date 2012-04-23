

using JTS;
using System;
	

	
namespace urn_jts_PingClient_1_0
{
	public class PingClient_PingClientFSM : StateMachine{

		public PingClient_PingClientFSMContext context;
		
		

		public PingClient_PingClientFSM()
	{

		/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
		 */
		context = new PingClient_PingClientFSMContext(this);

	}

		
		/// Handle notifications on parent state changes
		public override void setupNotifications()
		{

		}
		
		public virtual void QueryHeartBeatPulseAction()
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

