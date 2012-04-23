

using JTS;
using System;
using System.Collections.Generic;

namespace urn_jaus_jss_core_Events_1_1{

public class  EventsService : Service{
	public Events_ReceiveFSM pEvents_ReceiveFSM;
public Events_SendFSM pEvents_SendFSM;


	public EventsService(JausRouter jausRouter , urn_jaus_jss_core_Transport_1_1.TransportService pTransportService)
    {

	pEvents_ReceiveFSM = new Events_ReceiveFSM(pTransportService.pTransport_ReceiveFSM);
	pEvents_ReceiveFSM.setHandlers(ref ieHandler, ref jausRouter);
	pEvents_ReceiveFSM.setupNotifications();
	pEvents_SendFSM = new Events_SendFSM(pTransportService.pTransport_SendFSM);
	pEvents_SendFSM.setHandlers(ref ieHandler, ref jausRouter);
	pEvents_SendFSM.setupNotifications();


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
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("Events_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new QueryEvents().getID())
					{
						QueryEvents msg = new QueryEvents();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pEvents_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("Events_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new QueryEventTimeout().getID())
					{
						QueryEventTimeout msg = new QueryEventTimeout();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pEvents_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("Events_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new CreateEvent().getID())
					{
						CreateEvent msg = new CreateEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pEvents_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("Events_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new CreateEvent().getID())
					{
						CreateEvent msg = new CreateEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pEvents_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("Events_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new CreateEvent().getID())
					{
						CreateEvent msg = new CreateEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pEvents_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("Events_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new UpdateEvent().getID())
					{
						UpdateEvent msg = new UpdateEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pEvents_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("Events_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new UpdateEvent().getID())
					{
						UpdateEvent msg = new UpdateEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pEvents_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("Events_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new CancelEvent().getID())
					{
						CancelEvent msg = new CancelEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pEvents_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("Events_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new CancelEvent().getID())
					{
						CancelEvent msg = new CancelEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pEvents_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("EventOccurred") == 0 && (ie.getSource().CompareTo("Events_ReceiveFSM") != 0) && (!done))
				{
					EventOccurred casted_ie = (EventOccurred) ie;
						pEvents_ReceiveFSM.context.EventOccurredTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("EventError") == 0 && (ie.getSource().CompareTo("Events_ReceiveFSM") != 0) && (!done))
				{
					EventError casted_ie = (EventError) ie;
						pEvents_ReceiveFSM.context.EventErrorTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Timeout") == 0 && (ie.getSource().CompareTo("Events_ReceiveFSM") != 0) && (!done))
				{
					Timeout casted_ie = (Timeout) ie;
						pEvents_ReceiveFSM.context.TimeoutTransition();
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
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("Events_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new CreateEvent().getID())
					{
						CreateEvent msg = new CreateEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pEvents_ReceiveFSM.context.CreateEventTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("Events_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new CreateCommandEvent().getID())
					{
						CreateCommandEvent msg = new CreateCommandEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pEvents_ReceiveFSM.context.CreateCommandEventTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("Events_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new UpdateEvent().getID())
					{
						UpdateEvent msg = new UpdateEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pEvents_ReceiveFSM.context.UpdateEventTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("Events_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new CancelEvent().getID())
					{
						CancelEvent msg = new CancelEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pEvents_ReceiveFSM.context.CancelEventTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("Events_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new QueryEvents().getID())
					{
						QueryEvents msg = new QueryEvents();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pEvents_ReceiveFSM.context.QueryEventsTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("Events_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new QueryEventTimeout().getID())
					{
						QueryEventTimeout msg = new QueryEventTimeout();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pEvents_ReceiveFSM.context.QueryEventTimeoutTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("EventOccurred") == 0 && (ie.getSource().CompareTo("Events_ReceiveFSM") != 0) && (!done))
				{
					EventOccurred casted_ie = (EventOccurred) ie;
						pEvents_ReceiveFSM.context.EventOccurredTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("EventError") == 0 && (ie.getSource().CompareTo("Events_ReceiveFSM") != 0) && (!done))
				{
					EventError casted_ie = (EventError) ie;
						pEvents_ReceiveFSM.context.EventErrorTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Timeout") == 0 && (ie.getSource().CompareTo("Events_ReceiveFSM") != 0) && (!done))
				{
					Timeout casted_ie = (Timeout) ie;
						pEvents_ReceiveFSM.context.TimeoutTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("CommandCompleted") == 0 && (ie.getSource().CompareTo("Events_ReceiveFSM") != 0) && (!done))
				{
					CommandCompleted casted_ie = (CommandCompleted) ie;
						pEvents_ReceiveFSM.context.CommandCompletedTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("CommandExpired") == 0 && (ie.getSource().CompareTo("Events_ReceiveFSM") != 0) && (!done))
				{
					CommandExpired casted_ie = (CommandExpired) ie;
						pEvents_ReceiveFSM.context.CommandExpiredTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Send") == 0 && (ie.getSource().CompareTo("Events_ReceiveFSM") != 0) && (!done))
				{
					Send casted_ie = (Send) ie;
						pEvents_ReceiveFSM.context.SendTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("BroadcastLocal") == 0 && (ie.getSource().CompareTo("Events_ReceiveFSM") != 0) && (!done))
				{
					BroadcastLocal casted_ie = (BroadcastLocal) ie;
						pEvents_ReceiveFSM.context.BroadcastLocalTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("BroadcastGlobal") == 0 && (ie.getSource().CompareTo("Events_ReceiveFSM") != 0) && (!done))
				{
					BroadcastGlobal casted_ie = (BroadcastGlobal) ie;
						pEvents_ReceiveFSM.context.BroadcastGlobalTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("Events_SendFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new CreateEvent().getID())
					{
						CreateEvent msg = new CreateEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pEvents_SendFSM.context.CreateEventTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("Events_SendFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new CreateCommandEvent().getID())
					{
						CreateCommandEvent msg = new CreateCommandEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pEvents_SendFSM.context.CreateCommandEventTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("Events_SendFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new UpdateEvent().getID())
					{
						UpdateEvent msg = new UpdateEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pEvents_SendFSM.context.UpdateEventTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("Events_SendFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new CancelEvent().getID())
					{
						CancelEvent msg = new CancelEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pEvents_SendFSM.context.CancelEventTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("Events_SendFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new QueryEvents().getID())
					{
						QueryEvents msg = new QueryEvents();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pEvents_SendFSM.context.QueryEventsTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("Events_SendFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new QueryEventTimeout().getID())
					{
						QueryEventTimeout msg = new QueryEventTimeout();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pEvents_SendFSM.context.QueryEventTimeoutTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("EventOccurred") == 0 && (ie.getSource().CompareTo("Events_SendFSM") != 0) && (!done))
				{
					EventOccurred casted_ie = (EventOccurred) ie;
						pEvents_SendFSM.context.EventOccurredTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("EventError") == 0 && (ie.getSource().CompareTo("Events_SendFSM") != 0) && (!done))
				{
					EventError casted_ie = (EventError) ie;
						pEvents_SendFSM.context.EventErrorTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Timeout") == 0 && (ie.getSource().CompareTo("Events_SendFSM") != 0) && (!done))
				{
					Timeout casted_ie = (Timeout) ie;
						pEvents_SendFSM.context.TimeoutTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("CommandCompleted") == 0 && (ie.getSource().CompareTo("Events_SendFSM") != 0) && (!done))
				{
					CommandCompleted casted_ie = (CommandCompleted) ie;
						pEvents_SendFSM.context.CommandCompletedTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("CommandExpired") == 0 && (ie.getSource().CompareTo("Events_SendFSM") != 0) && (!done))
				{
					CommandExpired casted_ie = (CommandExpired) ie;
						pEvents_SendFSM.context.CommandExpiredTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Send") == 0 && (ie.getSource().CompareTo("Events_SendFSM") != 0) && (!done))
				{
					Send casted_ie = (Send) ie;
						pEvents_SendFSM.context.SendTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("BroadcastLocal") == 0 && (ie.getSource().CompareTo("Events_SendFSM") != 0) && (!done))
				{
					BroadcastLocal casted_ie = (BroadcastLocal) ie;
						pEvents_SendFSM.context.BroadcastLocalTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("BroadcastGlobal") == 0 && (ie.getSource().CompareTo("Events_SendFSM") != 0) && (!done))
				{
					BroadcastGlobal casted_ie = (BroadcastGlobal) ie;
						pEvents_SendFSM.context.BroadcastGlobalTransition();
						done = true;
				}
			} catch (Exception e) {}

	

		mutex.ReleaseMutex();
	    return done;
	}	

}

}

