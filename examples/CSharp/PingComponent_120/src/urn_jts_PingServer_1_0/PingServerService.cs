

using JTS;
using System;
using System.Collections.Generic;

namespace urn_jts_PingServer_1_0{

public class  PingServerService : Service{
	public PingServer_PingFSM pPingServer_PingFSM;


	public PingServerService(JausRouter jausRouter )
    {

	pPingServer_PingFSM = new PingServer_PingFSM();
	pPingServer_PingFSM.setHandlers(ref ieHandler, ref jausRouter);
	pPingServer_PingFSM.setupNotifications();


	}
	

	public override void run()
    {
		// Perform any entry actions specified by the start state.

		
		// Kick off  receive loop...
		base.run();
		}
		
	//	This is the function that will process an event either generated
	//  by the service, sent to it by another service on the same component,
	//  or as a message sent by a different component. 
	public override bool processTransitions(InternalEvent ie)
        {
	    bool done = false;

	   // Since this function can be called from multiple threads,
	   // we use a mutex to ensure only one state transition is
	   // active at a time.
	   mutex.WaitOne();

				// Invoke the FSM transition for this event.
			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("PingServer_PingFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new QueryHeartbeatPulse().getID())
					{
						QueryHeartbeatPulse msg = new QueryHeartbeatPulse();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pPingServer_PingFSM.context.QueryHeartbeatPulseTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

	

		mutex.ReleaseMutex();
	    return done;
	}


	//	This is the function that will check for default transitions if
	//  no other transitions were satisfied. 
	//
	public override bool defaultTransitions(InternalEvent ie)
	{
	    bool done = false;

	   // Since this function can be called from multiple threads,
	   // we use a mutex to ensure only one state transition is
	   // active at a time.
	   mutex.WaitOne();

		// Invoke the FSM transition for this event.
			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("PingServer_PingFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new QueryHeartbeatPulse().getID())
					{
						QueryHeartbeatPulse msg = new QueryHeartbeatPulse();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pPingServer_PingFSM.context.QueryHeartbeatPulseTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

	

		mutex.ReleaseMutex();
	    return done;
	}	

}

}

