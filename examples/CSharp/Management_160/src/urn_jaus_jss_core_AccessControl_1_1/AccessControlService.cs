

using JTS;
using System;
using System.Collections.Generic;

namespace urn_jaus_jss_core_AccessControl_1_1{

public class  AccessControlService : Service{
	public AccessControl_ReceiveFSM pAccessControl_ReceiveFSM;
public AccessControl_SendFSM pAccessControl_SendFSM;


	public AccessControlService(JausRouter jausRouter , urn_jaus_jss_core_Transport_1_1.TransportService pTransportService, urn_jaus_jss_core_Events_1_1.EventsService pEventsService)
    {

	pAccessControl_ReceiveFSM = new AccessControl_ReceiveFSM(pTransportService.pTransport_ReceiveFSM, pEventsService.pEvents_ReceiveFSM);
	pAccessControl_ReceiveFSM.setHandlers(ref ieHandler, ref jausRouter);
	pAccessControl_ReceiveFSM.setupNotifications();
	pAccessControl_SendFSM = new AccessControl_SendFSM(pTransportService.pTransport_SendFSM, pEventsService.pEvents_SendFSM);
	pAccessControl_SendFSM.setHandlers(ref ieHandler, ref jausRouter);
	pAccessControl_SendFSM.setupNotifications();


	}
	

	public override void run()
    {
		// Perform any entry actions specified by the start state.
	pAccessControl_ReceiveFSM.initAction();

		
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
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_Available") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_ControlledTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_Available") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_ControlledTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new ReleaseControl().getID())
					{
						ReleaseControl msg = new ReleaseControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_Available") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_ControlledTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new RequestControl().getID())
					{
						RequestControl msg = new RequestControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new RequestControl().getID())
					{
						RequestControl msg = new RequestControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_Available") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_ControlledTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new RequestControl().getID())
					{
						RequestControl msg = new RequestControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_Available") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlledTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_Available") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_ControlledTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_ReadyTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_ReceivingTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new QueryAuthority().getID())
					{
						QueryAuthority msg = new QueryAuthority();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new QueryTimeout().getID())
					{
						QueryTimeout msg = new QueryTimeout();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new QueryControl().getID())
					{
						QueryControl msg = new QueryControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new SetAuthority().getID())
					{
						SetAuthority msg = new SetAuthority();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_Available") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlledTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_ReadyTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_ReceivingTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new RequestControl().getID())
					{
						RequestControl msg = new RequestControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new RequestControl().getID())
					{
						RequestControl msg = new RequestControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new RequestControl().getID())
					{
						RequestControl msg = new RequestControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new ReleaseControl().getID())
					{
						ReleaseControl msg = new ReleaseControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new RequestControl().getID())
					{
						RequestControl msg = new RequestControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Timeout") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					Timeout casted_ie = (Timeout) ie;
						pAccessControl_ReceiveFSM.context.TimeoutTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_Available") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlledTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_ReadyTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_ReceivingTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new RequestControl().getID())
					{
						RequestControl msg = new RequestControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new ReleaseControl().getID())
					{
						ReleaseControl msg = new ReleaseControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Timeout") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					Timeout casted_ie = (Timeout) ie;
						pAccessControl_ReceiveFSM.context.TimeoutTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_Available") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlledTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_Available") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_ControlledTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_ReadyTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_ReceivingTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new QueryAuthority().getID())
					{
						QueryAuthority msg = new QueryAuthority();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new QueryTimeout().getID())
					{
						QueryTimeout msg = new QueryTimeout();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new QueryControl().getID())
					{
						QueryControl msg = new QueryControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
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
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new RequestControl().getID())
					{
						RequestControl msg = new RequestControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pAccessControl_ReceiveFSM.context.RequestControlTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new ReleaseControl().getID())
					{
						ReleaseControl msg = new ReleaseControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pAccessControl_ReceiveFSM.context.ReleaseControlTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new QueryControl().getID())
					{
						QueryControl msg = new QueryControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pAccessControl_ReceiveFSM.context.QueryControlTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new QueryAuthority().getID())
					{
						QueryAuthority msg = new QueryAuthority();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pAccessControl_ReceiveFSM.context.QueryAuthorityTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new SetAuthority().getID())
					{
						SetAuthority msg = new SetAuthority();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pAccessControl_ReceiveFSM.context.SetAuthorityTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new QueryTimeout().getID())
					{
						QueryTimeout msg = new QueryTimeout();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pAccessControl_ReceiveFSM.context.QueryTimeoutTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new CreateEvent().getID())
					{
						CreateEvent msg = new CreateEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pAccessControl_ReceiveFSM.context.CreateEventTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new CreateCommandEvent().getID())
					{
						CreateCommandEvent msg = new CreateCommandEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pAccessControl_ReceiveFSM.context.CreateCommandEventTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new UpdateEvent().getID())
					{
						UpdateEvent msg = new UpdateEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pAccessControl_ReceiveFSM.context.UpdateEventTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new CancelEvent().getID())
					{
						CancelEvent msg = new CancelEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pAccessControl_ReceiveFSM.context.CancelEventTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new QueryEvents().getID())
					{
						QueryEvents msg = new QueryEvents();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pAccessControl_ReceiveFSM.context.QueryEventsTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new QueryEventTimeout().getID())
					{
						QueryEventTimeout msg = new QueryEventTimeout();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pAccessControl_ReceiveFSM.context.QueryEventTimeoutTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Timeout") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					Timeout casted_ie = (Timeout) ie;
						pAccessControl_ReceiveFSM.context.TimeoutTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("EventOccurred") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					EventOccurred casted_ie = (EventOccurred) ie;
						pAccessControl_ReceiveFSM.context.EventOccurredTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("EventError") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					EventError casted_ie = (EventError) ie;
						pAccessControl_ReceiveFSM.context.EventErrorTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("CommandCompleted") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					CommandCompleted casted_ie = (CommandCompleted) ie;
						pAccessControl_ReceiveFSM.context.CommandCompletedTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("CommandExpired") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					CommandExpired casted_ie = (CommandExpired) ie;
						pAccessControl_ReceiveFSM.context.CommandExpiredTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Send") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					Send casted_ie = (Send) ie;
						pAccessControl_ReceiveFSM.context.SendTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("BroadcastLocal") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					BroadcastLocal casted_ie = (BroadcastLocal) ie;
						pAccessControl_ReceiveFSM.context.BroadcastLocalTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("BroadcastGlobal") == 0 && (ie.getSource().CompareTo("AccessControl_ReceiveFSM") != 0) && (!done))
				{
					BroadcastGlobal casted_ie = (BroadcastGlobal) ie;
						pAccessControl_ReceiveFSM.context.BroadcastGlobalTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_SendFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new RequestControl().getID())
					{
						RequestControl msg = new RequestControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pAccessControl_SendFSM.context.RequestControlTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_SendFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new ReleaseControl().getID())
					{
						ReleaseControl msg = new ReleaseControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pAccessControl_SendFSM.context.ReleaseControlTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_SendFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new QueryControl().getID())
					{
						QueryControl msg = new QueryControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pAccessControl_SendFSM.context.QueryControlTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_SendFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new QueryAuthority().getID())
					{
						QueryAuthority msg = new QueryAuthority();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pAccessControl_SendFSM.context.QueryAuthorityTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_SendFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new SetAuthority().getID())
					{
						SetAuthority msg = new SetAuthority();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pAccessControl_SendFSM.context.SetAuthorityTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_SendFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new QueryTimeout().getID())
					{
						QueryTimeout msg = new QueryTimeout();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pAccessControl_SendFSM.context.QueryTimeoutTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_SendFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new CreateEvent().getID())
					{
						CreateEvent msg = new CreateEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pAccessControl_SendFSM.context.CreateEventTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_SendFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new CreateCommandEvent().getID())
					{
						CreateCommandEvent msg = new CreateCommandEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pAccessControl_SendFSM.context.CreateCommandEventTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_SendFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new UpdateEvent().getID())
					{
						UpdateEvent msg = new UpdateEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pAccessControl_SendFSM.context.UpdateEventTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_SendFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new CancelEvent().getID())
					{
						CancelEvent msg = new CancelEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pAccessControl_SendFSM.context.CancelEventTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_SendFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new QueryEvents().getID())
					{
						QueryEvents msg = new QueryEvents();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pAccessControl_SendFSM.context.QueryEventsTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AccessControl_SendFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new QueryEventTimeout().getID())
					{
						QueryEventTimeout msg = new QueryEventTimeout();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pAccessControl_SendFSM.context.QueryEventTimeoutTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Timeout") == 0 && (ie.getSource().CompareTo("AccessControl_SendFSM") != 0) && (!done))
				{
					Timeout casted_ie = (Timeout) ie;
						pAccessControl_SendFSM.context.TimeoutTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("EventOccurred") == 0 && (ie.getSource().CompareTo("AccessControl_SendFSM") != 0) && (!done))
				{
					EventOccurred casted_ie = (EventOccurred) ie;
						pAccessControl_SendFSM.context.EventOccurredTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("EventError") == 0 && (ie.getSource().CompareTo("AccessControl_SendFSM") != 0) && (!done))
				{
					EventError casted_ie = (EventError) ie;
						pAccessControl_SendFSM.context.EventErrorTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("CommandCompleted") == 0 && (ie.getSource().CompareTo("AccessControl_SendFSM") != 0) && (!done))
				{
					CommandCompleted casted_ie = (CommandCompleted) ie;
						pAccessControl_SendFSM.context.CommandCompletedTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("CommandExpired") == 0 && (ie.getSource().CompareTo("AccessControl_SendFSM") != 0) && (!done))
				{
					CommandExpired casted_ie = (CommandExpired) ie;
						pAccessControl_SendFSM.context.CommandExpiredTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Send") == 0 && (ie.getSource().CompareTo("AccessControl_SendFSM") != 0) && (!done))
				{
					Send casted_ie = (Send) ie;
						pAccessControl_SendFSM.context.SendTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("BroadcastLocal") == 0 && (ie.getSource().CompareTo("AccessControl_SendFSM") != 0) && (!done))
				{
					BroadcastLocal casted_ie = (BroadcastLocal) ie;
						pAccessControl_SendFSM.context.BroadcastLocalTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("BroadcastGlobal") == 0 && (ie.getSource().CompareTo("AccessControl_SendFSM") != 0) && (!done))
				{
					BroadcastGlobal casted_ie = (BroadcastGlobal) ie;
						pAccessControl_SendFSM.context.BroadcastGlobalTransition();
						done = true;
				}
			} catch (Exception e) {}

	

		mutex.ReleaseMutex();
	    return done;
	}	

}

}

