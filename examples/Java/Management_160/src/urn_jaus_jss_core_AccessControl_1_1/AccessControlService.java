
package src.urn_jaus_jss_core_AccessControl_1_1;

import java.lang.Exception;
import java.util.logging.Level;
import java.util.logging.Logger;
import framework.Service;
import framework.JausUtils;
import framework.transport.JausRouter;
import framework.internalEvents.*;
import src.urn_jaus_jss_core_Transport_1_1.TransportService;
import src.urn_jaus_jss_core_Events_1_1.EventsService;
import src.urn_jaus_jss_core_AccessControl_1_1.InternalEvents.*;
import src.urn_jaus_jss_core_AccessControl_1_1.Messages.*;


public class AccessControlService extends Service{

		public AccessControl_ReceiveFSM pAccessControl_ReceiveFSM;
	public AccessControl_SendFSM pAccessControl_SendFSM;


	public AccessControlService(JausRouter jausRouter , TransportService pTransportService, EventsService pEventsService)
	{

	pAccessControl_ReceiveFSM = new AccessControl_ReceiveFSM(pTransportService.pTransport_ReceiveFSM, pEventsService.pEvents_ReceiveFSM);
	pAccessControl_ReceiveFSM.setHandler(ieHandler, jausRouter);
	pAccessControl_ReceiveFSM.setupNotifications();
	pAccessControl_SendFSM = new AccessControl_SendFSM(pTransportService.pTransport_SendFSM, pEventsService.pEvents_SendFSM);
	pAccessControl_SendFSM.setHandler(ieHandler, jausRouter);
	pAccessControl_SendFSM.setupNotifications();


	}
	
	public void run(){

		// Perform any entry actions specified by the start state.
	pAccessControl_ReceiveFSM.initAction();

		

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
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_Available") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_ControlledTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_Available") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_ControlledTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReleaseControl.ID)
					{
						ReleaseControl msg = new ReleaseControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_Available") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_ControlledTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == RequestControl.ID)
					{
						RequestControl msg = new RequestControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == RequestControl.ID)
					{
						RequestControl msg = new RequestControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_Available") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_ControlledTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == RequestControl.ID)
					{
						RequestControl msg = new RequestControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_Available") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlledTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_Available") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_ControlledTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_ReadyTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_ReceivingTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == QueryAuthority.ID)
					{
						QueryAuthority msg = new QueryAuthority();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == QueryTimeout.ID)
					{
						QueryTimeout msg = new QueryTimeout();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == QueryControl.ID)
					{
						QueryControl msg = new QueryControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == SetAuthority.ID)
					{
						SetAuthority msg = new SetAuthority();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_Available") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlledTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_ReadyTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_ReceivingTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == RequestControl.ID)
					{
						RequestControl msg = new RequestControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == RequestControl.ID)
					{
						RequestControl msg = new RequestControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == RequestControl.ID)
					{
						RequestControl msg = new RequestControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReleaseControl.ID)
					{
						ReleaseControl msg = new ReleaseControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == RequestControl.ID)
					{
						RequestControl msg = new RequestControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Timeout") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					Timeout casted_ie = (Timeout) ie;
						pAccessControl_ReceiveFSM.context.TimeoutTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_Available") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlledTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailable") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_NotAvailableTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_ReadyTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_ReceivingTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == RequestControl.ID)
					{
						RequestControl msg = new RequestControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReleaseControl.ID)
					{
						ReleaseControl msg = new ReleaseControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Timeout") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					Timeout casted_ie = (Timeout) ie;
						pAccessControl_ReceiveFSM.context.TimeoutTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_Available") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_AvailableTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled_NotAvailableTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlled") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_NotControlledTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_Available") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled_AvailableTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_Controlled") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready_ControlledTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_Ready") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_Receiving_ReadyTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AccessControl_ReceiveFSM_Receiving") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
						pAccessControl_ReceiveFSM.context.InternalStateChange_To_AccessControl_ReceiveFSM_ReceivingTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == QueryAuthority.ID)
					{
						QueryAuthority msg = new QueryAuthority();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == QueryTimeout.ID)
					{
						QueryTimeout msg = new QueryTimeout();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == QueryControl.ID)
					{
						QueryControl msg = new QueryControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pAccessControl_ReceiveFSM.context.ReceiveTransition(msg, transportData);
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
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == RequestControl.ID)
					{
						RequestControl msg = new RequestControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pAccessControl_ReceiveFSM.context.RequestControlTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReleaseControl.ID)
					{
						ReleaseControl msg = new ReleaseControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pAccessControl_ReceiveFSM.context.ReleaseControlTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == QueryControl.ID)
					{
						QueryControl msg = new QueryControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pAccessControl_ReceiveFSM.context.QueryControlTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == QueryAuthority.ID)
					{
						QueryAuthority msg = new QueryAuthority();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pAccessControl_ReceiveFSM.context.QueryAuthorityTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == SetAuthority.ID)
					{
						SetAuthority msg = new SetAuthority();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pAccessControl_ReceiveFSM.context.SetAuthorityTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == QueryTimeout.ID)
					{
						QueryTimeout msg = new QueryTimeout();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pAccessControl_ReceiveFSM.context.QueryTimeoutTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == CreateEvent.ID)
					{
						CreateEvent msg = new CreateEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pAccessControl_ReceiveFSM.context.CreateEventTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == CreateCommandEvent.ID)
					{
						CreateCommandEvent msg = new CreateCommandEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pAccessControl_ReceiveFSM.context.CreateCommandEventTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == UpdateEvent.ID)
					{
						UpdateEvent msg = new UpdateEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pAccessControl_ReceiveFSM.context.UpdateEventTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == CancelEvent.ID)
					{
						CancelEvent msg = new CancelEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pAccessControl_ReceiveFSM.context.CancelEventTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == QueryEvents.ID)
					{
						QueryEvents msg = new QueryEvents();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pAccessControl_ReceiveFSM.context.QueryEventsTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == QueryEventTimeout.ID)
					{
						QueryEventTimeout msg = new QueryEventTimeout();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pAccessControl_ReceiveFSM.context.QueryEventTimeoutTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Timeout") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					Timeout casted_ie = (Timeout) ie;
						pAccessControl_ReceiveFSM.context.TimeoutTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("EventOccurred") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					EventOccurred casted_ie = (EventOccurred) ie;
						pAccessControl_ReceiveFSM.context.EventOccurredTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("EventError") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					EventError casted_ie = (EventError) ie;
						pAccessControl_ReceiveFSM.context.EventErrorTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("CommandCompleted") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					CommandCompleted casted_ie = (CommandCompleted) ie;
						pAccessControl_ReceiveFSM.context.CommandCompletedTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("CommandExpired") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					CommandExpired casted_ie = (CommandExpired) ie;
						pAccessControl_ReceiveFSM.context.CommandExpiredTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Send") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					Send casted_ie = (Send) ie;
						pAccessControl_ReceiveFSM.context.SendTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("BroadcastLocal") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					BroadcastLocal casted_ie = (BroadcastLocal) ie;
						pAccessControl_ReceiveFSM.context.BroadcastLocalTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("BroadcastGlobal") == 0 && (ie.getSource().compareTo("AccessControl_ReceiveFSM") != 0) && (done == false))
				{
					BroadcastGlobal casted_ie = (BroadcastGlobal) ie;
						pAccessControl_ReceiveFSM.context.BroadcastGlobalTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_SendFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == RequestControl.ID)
					{
						RequestControl msg = new RequestControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pAccessControl_SendFSM.context.RequestControlTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_SendFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReleaseControl.ID)
					{
						ReleaseControl msg = new ReleaseControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pAccessControl_SendFSM.context.ReleaseControlTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_SendFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == QueryControl.ID)
					{
						QueryControl msg = new QueryControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pAccessControl_SendFSM.context.QueryControlTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_SendFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == QueryAuthority.ID)
					{
						QueryAuthority msg = new QueryAuthority();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pAccessControl_SendFSM.context.QueryAuthorityTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_SendFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == SetAuthority.ID)
					{
						SetAuthority msg = new SetAuthority();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pAccessControl_SendFSM.context.SetAuthorityTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_SendFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == QueryTimeout.ID)
					{
						QueryTimeout msg = new QueryTimeout();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pAccessControl_SendFSM.context.QueryTimeoutTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_SendFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == CreateEvent.ID)
					{
						CreateEvent msg = new CreateEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pAccessControl_SendFSM.context.CreateEventTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_SendFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == CreateCommandEvent.ID)
					{
						CreateCommandEvent msg = new CreateCommandEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pAccessControl_SendFSM.context.CreateCommandEventTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_SendFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == UpdateEvent.ID)
					{
						UpdateEvent msg = new UpdateEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pAccessControl_SendFSM.context.UpdateEventTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_SendFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == CancelEvent.ID)
					{
						CancelEvent msg = new CancelEvent();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pAccessControl_SendFSM.context.CancelEventTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_SendFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == QueryEvents.ID)
					{
						QueryEvents msg = new QueryEvents();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pAccessControl_SendFSM.context.QueryEventsTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AccessControl_SendFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == QueryEventTimeout.ID)
					{
						QueryEventTimeout msg = new QueryEventTimeout();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pAccessControl_SendFSM.context.QueryEventTimeoutTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Timeout") == 0 && (ie.getSource().compareTo("AccessControl_SendFSM") != 0) && (done == false))
				{
					Timeout casted_ie = (Timeout) ie;
						pAccessControl_SendFSM.context.TimeoutTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("EventOccurred") == 0 && (ie.getSource().compareTo("AccessControl_SendFSM") != 0) && (done == false))
				{
					EventOccurred casted_ie = (EventOccurred) ie;
						pAccessControl_SendFSM.context.EventOccurredTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("EventError") == 0 && (ie.getSource().compareTo("AccessControl_SendFSM") != 0) && (done == false))
				{
					EventError casted_ie = (EventError) ie;
						pAccessControl_SendFSM.context.EventErrorTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("CommandCompleted") == 0 && (ie.getSource().compareTo("AccessControl_SendFSM") != 0) && (done == false))
				{
					CommandCompleted casted_ie = (CommandCompleted) ie;
						pAccessControl_SendFSM.context.CommandCompletedTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("CommandExpired") == 0 && (ie.getSource().compareTo("AccessControl_SendFSM") != 0) && (done == false))
				{
					CommandExpired casted_ie = (CommandExpired) ie;
						pAccessControl_SendFSM.context.CommandExpiredTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Send") == 0 && (ie.getSource().compareTo("AccessControl_SendFSM") != 0) && (done == false))
				{
					Send casted_ie = (Send) ie;
						pAccessControl_SendFSM.context.SendTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("BroadcastLocal") == 0 && (ie.getSource().compareTo("AccessControl_SendFSM") != 0) && (done == false))
				{
					BroadcastLocal casted_ie = (BroadcastLocal) ie;
						pAccessControl_SendFSM.context.BroadcastLocalTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("BroadcastGlobal") == 0 && (ie.getSource().compareTo("AccessControl_SendFSM") != 0) && (done == false))
				{
					BroadcastGlobal casted_ie = (BroadcastGlobal) ie;
						pAccessControl_SendFSM.context.BroadcastGlobalTransition();
						done = true;
				}
			}
			catch (Exception e) {}

	

	    return done;
	}

}



