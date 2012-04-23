
package src.urn_jaus_jss_core_Transport_1_1;

import java.lang.Exception;
import java.util.logging.Level;
import java.util.logging.Logger;
import framework.Service;
import framework.JausUtils;
import framework.transport.JausRouter;
import framework.internalEvents.*;
import src.urn_jaus_jss_core_Transport_1_1.InternalEvents.*;


public class TransportService extends Service{

		public Transport_ReceiveFSM pTransport_ReceiveFSM;
	public Transport_SendFSM pTransport_SendFSM;


	public TransportService(JausRouter jausRouter )
	{

	pTransport_ReceiveFSM = new Transport_ReceiveFSM();
	pTransport_ReceiveFSM.setHandler(ieHandler, jausRouter);
	pTransport_ReceiveFSM.setupNotifications();
	pTransport_SendFSM = new Transport_SendFSM();
	pTransport_SendFSM.setHandler(ieHandler, jausRouter);
	pTransport_SendFSM.setupNotifications();


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
				if (ie.getName().compareTo("Send") == 0 && (ie.getSource().compareTo("Transport_SendFSM") != 0) && (done == false))
				{
					Send casted_ie = (Send) ie;
						Send msg = casted_ie;
						pTransport_SendFSM.context.SendTransition(msg);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("BroadcastLocal") == 0 && (ie.getSource().compareTo("Transport_SendFSM") != 0) && (done == false))
				{
					BroadcastLocal casted_ie = (BroadcastLocal) ie;
						BroadcastLocal msg = casted_ie;
						pTransport_SendFSM.context.BroadcastLocalTransition(msg);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("BroadcastGlobal") == 0 && (ie.getSource().compareTo("Transport_SendFSM") != 0) && (done == false))
				{
					BroadcastGlobal casted_ie = (BroadcastGlobal) ie;
						BroadcastGlobal msg = casted_ie;
						pTransport_SendFSM.context.BroadcastGlobalTransition(msg);
						done = true;
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
				if (ie.getName().compareTo("Send") == 0 && (ie.getSource().compareTo("Transport_ReceiveFSM") != 0) && (done == false))
				{
					Send casted_ie = (Send) ie;
						pTransport_ReceiveFSM.context.SendTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("BroadcastLocal") == 0 && (ie.getSource().compareTo("Transport_ReceiveFSM") != 0) && (done == false))
				{
					BroadcastLocal casted_ie = (BroadcastLocal) ie;
						pTransport_ReceiveFSM.context.BroadcastLocalTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("BroadcastGlobal") == 0 && (ie.getSource().compareTo("Transport_ReceiveFSM") != 0) && (done == false))
				{
					BroadcastGlobal casted_ie = (BroadcastGlobal) ie;
						pTransport_ReceiveFSM.context.BroadcastGlobalTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Send") == 0 && (ie.getSource().compareTo("Transport_SendFSM") != 0) && (done == false))
				{
					Send casted_ie = (Send) ie;
						pTransport_SendFSM.context.SendTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("BroadcastLocal") == 0 && (ie.getSource().compareTo("Transport_SendFSM") != 0) && (done == false))
				{
					BroadcastLocal casted_ie = (BroadcastLocal) ie;
						pTransport_SendFSM.context.BroadcastLocalTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("BroadcastGlobal") == 0 && (ie.getSource().compareTo("Transport_SendFSM") != 0) && (done == false))
				{
					BroadcastGlobal casted_ie = (BroadcastGlobal) ie;
						pTransport_SendFSM.context.BroadcastGlobalTransition();
						done = true;
				}
			}
			catch (Exception e) {}

	

	    return done;
	}

}



