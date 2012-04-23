
package src.urn_jts_PingServer_1_0;

import java.lang.Exception;
import java.util.logging.Level;
import java.util.logging.Logger;
import framework.Service;
import framework.JausUtils;
import framework.transport.JausRouter;
import framework.internalEvents.*;
import src.urn_jts_PingServer_1_0.Messages.*;


public class PingServerService extends Service{

		public PingServer_PingFSM pPingServer_PingFSM;


	public PingServerService(JausRouter jausRouter )
	{

	pPingServer_PingFSM = new PingServer_PingFSM();
	pPingServer_PingFSM.setHandler(ieHandler, jausRouter);
	pPingServer_PingFSM.setupNotifications();


	}
	
	public void run(){

		// Perform any entry actions specified by the start state.

		

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
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("PingServer_PingFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == QueryHeartbeatPulse.ID)
					{
						QueryHeartbeatPulse msg = new QueryHeartbeatPulse();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pPingServer_PingFSM.context.QueryHeartbeatPulseTransition();
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
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("PingServer_PingFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == QueryHeartbeatPulse.ID)
					{
						QueryHeartbeatPulse msg = new QueryHeartbeatPulse();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pPingServer_PingFSM.context.QueryHeartbeatPulseTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

	

	    return done;
			}

}



