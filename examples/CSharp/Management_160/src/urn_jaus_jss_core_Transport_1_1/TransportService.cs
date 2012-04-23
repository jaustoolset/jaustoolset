

using JTS;
using System;
using System.Collections.Generic;

namespace urn_jaus_jss_core_Transport_1_1{

public class  TransportService : Service{
	public Transport_ReceiveFSM pTransport_ReceiveFSM;
public Transport_SendFSM pTransport_SendFSM;


	public TransportService(JausRouter jausRouter )
    {

	pTransport_ReceiveFSM = new Transport_ReceiveFSM();
	pTransport_ReceiveFSM.setHandlers(ref ieHandler, ref jausRouter);
	pTransport_ReceiveFSM.setupNotifications();
	pTransport_SendFSM = new Transport_SendFSM();
	pTransport_SendFSM.setHandlers(ref ieHandler, ref jausRouter);
	pTransport_SendFSM.setupNotifications();


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
				if (ie.getName().CompareTo("Send") == 0 && (ie.getSource().CompareTo("Transport_SendFSM") != 0) && (!done))
				{
					Send casted_ie = (Send) ie;
						Send msg = casted_ie;
						pTransport_SendFSM.context.SendTransition(msg);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("BroadcastLocal") == 0 && (ie.getSource().CompareTo("Transport_SendFSM") != 0) && (!done))
				{
					BroadcastLocal casted_ie = (BroadcastLocal) ie;
						BroadcastLocal msg = casted_ie;
						pTransport_SendFSM.context.BroadcastLocalTransition(msg);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("BroadcastGlobal") == 0 && (ie.getSource().CompareTo("Transport_SendFSM") != 0) && (!done))
				{
					BroadcastGlobal casted_ie = (BroadcastGlobal) ie;
						BroadcastGlobal msg = casted_ie;
						pTransport_SendFSM.context.BroadcastGlobalTransition(msg);
						done = true;
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
				if (ie.getName().CompareTo("Send") == 0 && (ie.getSource().CompareTo("Transport_ReceiveFSM") != 0) && (!done))
				{
					Send casted_ie = (Send) ie;
						pTransport_ReceiveFSM.context.SendTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("BroadcastLocal") == 0 && (ie.getSource().CompareTo("Transport_ReceiveFSM") != 0) && (!done))
				{
					BroadcastLocal casted_ie = (BroadcastLocal) ie;
						pTransport_ReceiveFSM.context.BroadcastLocalTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("BroadcastGlobal") == 0 && (ie.getSource().CompareTo("Transport_ReceiveFSM") != 0) && (!done))
				{
					BroadcastGlobal casted_ie = (BroadcastGlobal) ie;
						pTransport_ReceiveFSM.context.BroadcastGlobalTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Send") == 0 && (ie.getSource().CompareTo("Transport_SendFSM") != 0) && (!done))
				{
					Send casted_ie = (Send) ie;
						pTransport_SendFSM.context.SendTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("BroadcastLocal") == 0 && (ie.getSource().CompareTo("Transport_SendFSM") != 0) && (!done))
				{
					BroadcastLocal casted_ie = (BroadcastLocal) ie;
						pTransport_SendFSM.context.BroadcastLocalTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("BroadcastGlobal") == 0 && (ie.getSource().CompareTo("Transport_SendFSM") != 0) && (!done))
				{
					BroadcastGlobal casted_ie = (BroadcastGlobal) ie;
						pTransport_SendFSM.context.BroadcastGlobalTransition();
						done = true;
				}
			} catch (Exception e) {}

	

		mutex.ReleaseMutex();
	    return done;
	}	

}

}

