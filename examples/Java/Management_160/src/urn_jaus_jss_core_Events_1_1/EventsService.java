
package src.urn_jaus_jss_core_Events_1_1;

import java.lang.Exception;
import java.util.logging.Level;
import java.util.logging.Logger;
import framework.Service;
import framework.JausUtils;
import framework.transport.JausRouter;
import framework.internalEvents.*;
import src.urn_jaus_jss_core_Transport_1_1.TransportService;
import src.urn_jaus_jss_core_Events_1_1.InternalEvents.*;
import src.urn_jaus_jss_core_Events_1_1.Messages.*;


public class EventsService extends Service{

		public Events_ReceiveFSM pEvents_ReceiveFSM;
	public Events_SendFSM pEvents_SendFSM;


	public EventsService(JausRouter jausRouter , TransportService pTransportService)
	{

	pEvents_ReceiveFSM = new Events_ReceiveFSM(pTransportService.pTransport_ReceiveFSM);
	pEvents_ReceiveFSM.setHandler(ieHandler, jausRouter);
	pEvents_ReceiveFSM.setupNotifications();
	pEvents_SendFSM = new Events_SendFSM(pTransportService.pTransport_SendFSM);
	pEvents_SendFSM.setHandler(ieHandler, jausRouter);
	pEvents_SendFSM.setupNotifications();


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
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("Events_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == QueryEvents.ID)
					{
						QueryEvents msg = new QueryEvents();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pEvents_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("Events_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == QueryEventTimeout.ID)
					{
						QueryEventTimeout msg = new QueryEventTimeout();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pEvents_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("Events_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == CreateEvent.ID)
					{
						CreateEvent msg = new CreateEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pEvents_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("Events_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == CreateEvent.ID)
					{
						CreateEvent msg = new CreateEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pEvents_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("Events_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == CreateEvent.ID)
					{
						CreateEvent msg = new CreateEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pEvents_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("Events_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == UpdateEvent.ID)
					{
						UpdateEvent msg = new UpdateEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pEvents_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("Events_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == UpdateEvent.ID)
					{
						UpdateEvent msg = new UpdateEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pEvents_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("Events_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == CancelEvent.ID)
					{
						CancelEvent msg = new CancelEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pEvents_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("Events_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == CancelEvent.ID)
					{
						CancelEvent msg = new CancelEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pEvents_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("EventOccurred") == 0 && (ie.getSource().compareTo("Events_ReceiveFSM") != 0) && (done == false))
				{
					EventOccurred casted_ie = (EventOccurred) ie;
						pEvents_ReceiveFSM.context.EventOccurredTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("EventError") == 0 && (ie.getSource().compareTo("Events_ReceiveFSM") != 0) && (done == false))
				{
					EventError casted_ie = (EventError) ie;
						pEvents_ReceiveFSM.context.EventErrorTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Timeout") == 0 && (ie.getSource().compareTo("Events_ReceiveFSM") != 0) && (done == false))
				{
					Timeout casted_ie = (Timeout) ie;
						pEvents_ReceiveFSM.context.TimeoutTransition();
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
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("Events_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == CreateEvent.ID)
					{
						CreateEvent msg = new CreateEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pEvents_ReceiveFSM.context.CreateEventTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("Events_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == CreateCommandEvent.ID)
					{
						CreateCommandEvent msg = new CreateCommandEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pEvents_ReceiveFSM.context.CreateCommandEventTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("Events_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == UpdateEvent.ID)
					{
						UpdateEvent msg = new UpdateEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pEvents_ReceiveFSM.context.UpdateEventTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("Events_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == CancelEvent.ID)
					{
						CancelEvent msg = new CancelEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pEvents_ReceiveFSM.context.CancelEventTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("Events_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == QueryEvents.ID)
					{
						QueryEvents msg = new QueryEvents();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pEvents_ReceiveFSM.context.QueryEventsTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("Events_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == QueryEventTimeout.ID)
					{
						QueryEventTimeout msg = new QueryEventTimeout();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pEvents_ReceiveFSM.context.QueryEventTimeoutTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("EventOccurred") == 0 && (ie.getSource().compareTo("Events_ReceiveFSM") != 0) && (done == false))
				{
					EventOccurred casted_ie = (EventOccurred) ie;
						pEvents_ReceiveFSM.context.EventOccurredTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("EventError") == 0 && (ie.getSource().compareTo("Events_ReceiveFSM") != 0) && (done == false))
				{
					EventError casted_ie = (EventError) ie;
						pEvents_ReceiveFSM.context.EventErrorTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Timeout") == 0 && (ie.getSource().compareTo("Events_ReceiveFSM") != 0) && (done == false))
				{
					Timeout casted_ie = (Timeout) ie;
						pEvents_ReceiveFSM.context.TimeoutTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("CommandCompleted") == 0 && (ie.getSource().compareTo("Events_ReceiveFSM") != 0) && (done == false))
				{
					CommandCompleted casted_ie = (CommandCompleted) ie;
						pEvents_ReceiveFSM.context.CommandCompletedTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("CommandExpired") == 0 && (ie.getSource().compareTo("Events_ReceiveFSM") != 0) && (done == false))
				{
					CommandExpired casted_ie = (CommandExpired) ie;
						pEvents_ReceiveFSM.context.CommandExpiredTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Send") == 0 && (ie.getSource().compareTo("Events_ReceiveFSM") != 0) && (done == false))
				{
					Send casted_ie = (Send) ie;
						pEvents_ReceiveFSM.context.SendTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("BroadcastLocal") == 0 && (ie.getSource().compareTo("Events_ReceiveFSM") != 0) && (done == false))
				{
					BroadcastLocal casted_ie = (BroadcastLocal) ie;
						pEvents_ReceiveFSM.context.BroadcastLocalTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("BroadcastGlobal") == 0 && (ie.getSource().compareTo("Events_ReceiveFSM") != 0) && (done == false))
				{
					BroadcastGlobal casted_ie = (BroadcastGlobal) ie;
						pEvents_ReceiveFSM.context.BroadcastGlobalTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("Events_SendFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == CreateEvent.ID)
					{
						CreateEvent msg = new CreateEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pEvents_SendFSM.context.CreateEventTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("Events_SendFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == CreateCommandEvent.ID)
					{
						CreateCommandEvent msg = new CreateCommandEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pEvents_SendFSM.context.CreateCommandEventTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("Events_SendFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == UpdateEvent.ID)
					{
						UpdateEvent msg = new UpdateEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pEvents_SendFSM.context.UpdateEventTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("Events_SendFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == CancelEvent.ID)
					{
						CancelEvent msg = new CancelEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pEvents_SendFSM.context.CancelEventTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("Events_SendFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == QueryEvents.ID)
					{
						QueryEvents msg = new QueryEvents();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pEvents_SendFSM.context.QueryEventsTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("Events_SendFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == QueryEventTimeout.ID)
					{
						QueryEventTimeout msg = new QueryEventTimeout();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pEvents_SendFSM.context.QueryEventTimeoutTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("EventOccurred") == 0 && (ie.getSource().compareTo("Events_SendFSM") != 0) && (done == false))
				{
					EventOccurred casted_ie = (EventOccurred) ie;
						pEvents_SendFSM.context.EventOccurredTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("EventError") == 0 && (ie.getSource().compareTo("Events_SendFSM") != 0) && (done == false))
				{
					EventError casted_ie = (EventError) ie;
						pEvents_SendFSM.context.EventErrorTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Timeout") == 0 && (ie.getSource().compareTo("Events_SendFSM") != 0) && (done == false))
				{
					Timeout casted_ie = (Timeout) ie;
						pEvents_SendFSM.context.TimeoutTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("CommandCompleted") == 0 && (ie.getSource().compareTo("Events_SendFSM") != 0) && (done == false))
				{
					CommandCompleted casted_ie = (CommandCompleted) ie;
						pEvents_SendFSM.context.CommandCompletedTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("CommandExpired") == 0 && (ie.getSource().compareTo("Events_SendFSM") != 0) && (done == false))
				{
					CommandExpired casted_ie = (CommandExpired) ie;
						pEvents_SendFSM.context.CommandExpiredTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Send") == 0 && (ie.getSource().compareTo("Events_SendFSM") != 0) && (done == false))
				{
					Send casted_ie = (Send) ie;
						pEvents_SendFSM.context.SendTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("BroadcastLocal") == 0 && (ie.getSource().compareTo("Events_SendFSM") != 0) && (done == false))
				{
					BroadcastLocal casted_ie = (BroadcastLocal) ie;
						pEvents_SendFSM.context.BroadcastLocalTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("BroadcastGlobal") == 0 && (ie.getSource().compareTo("Events_SendFSM") != 0) && (done == false))
				{
					BroadcastGlobal casted_ie = (BroadcastGlobal) ie;
						pEvents_SendFSM.context.BroadcastGlobalTransition();
						done = true;
				}
			}
			catch (Exception e) {}

	

	    return done;
	}

}



