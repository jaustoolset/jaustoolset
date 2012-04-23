
package src.urn_jts_PingClient_1_0;

import java.lang.Exception;
import java.util.logging.Level;
import java.util.logging.Logger;
import framework.Service;
import framework.JausUtils;
import framework.transport.JausRouter;
import framework.internalEvents.*;
import src.urn_jts_PingClient_1_0.Messages.*;


public class PingClientService extends Service{

		public PingClient_PingClientFSM pPingClient_PingClientFSM;


	public PingClientService(JausRouter jausRouter )
	{

	pPingClient_PingClientFSM = new PingClient_PingClientFSM();
	pPingClient_PingClientFSM.setHandler(ieHandler, jausRouter);
	pPingClient_PingClientFSM.setupNotifications();


	}
	
	public void run(){

		// Perform any entry actions specified by the start state.
	pPingClient_PingClientFSM.QueryHeartBeatPulseAction();

		

		// Kick off  receive loop...
		super.run();
	}

	//	This is the function that will process an event either generated
	//  by the service, sent to it by another service on the same component,
	//  or as a message sent by a different component. 
	public synchronized boolean processTransitions(InternalEvent ie)
	{
		    boolean done = false;

				// Invoke the FSM transition for this event.
			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("PingClient_PingClientFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReportHeartbeatPulse.ID)
					{
						ReportHeartbeatPulse msg = new ReportHeartbeatPulse();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pPingClient_PingClientFSM.context.ReportHeartbeatPulseTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

	

	    return done;
	}


	//	This is the function that will check for default transitions if
	//  no other transitions were satisfied. 
	//
	public synchronized boolean defaultTransitions(InternalEvent ie)
	{
	    boolean done = false;

		// Invoke the FSM transition for this event.
			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("PingClient_PingClientFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReportHeartbeatPulse.ID)
					{
						ReportHeartbeatPulse msg = new ReportHeartbeatPulse();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pPingClient_PingClientFSM.context.ReportHeartbeatPulseTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

	

	    return done;
			}

}



