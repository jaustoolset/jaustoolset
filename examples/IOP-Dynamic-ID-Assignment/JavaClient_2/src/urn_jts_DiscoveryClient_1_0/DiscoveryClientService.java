
package src.urn_jts_DiscoveryClient_1_0;

import java.lang.Exception;
import java.util.logging.Level;
import java.util.logging.Logger;
import framework.Service;
import framework.JausUtils;
import framework.transport.JausRouter;
import framework.internalEvents.*;
import src.urn_jaus_jss_core_Transport_1_1.TransportService;
import src.urn_jts_DiscoveryClient_1_0.InternalEvents.*;
import src.urn_jts_DiscoveryClient_1_0.Messages.*;


public class DiscoveryClientService extends Service{

		public DiscoveryClient_ReceiveFSM pDiscoveryClient_ReceiveFSM;
	public DiscoveryClient_SendFSM pDiscoveryClient_SendFSM;


	public DiscoveryClientService(JausRouter jausRouter , TransportService pTransportService)
	{

	pDiscoveryClient_ReceiveFSM = new DiscoveryClient_ReceiveFSM(pTransportService.pTransport_ReceiveFSM);
	pDiscoveryClient_ReceiveFSM.setHandler(ieHandler, jausRouter);
	pDiscoveryClient_ReceiveFSM.setupNotifications();
	pDiscoveryClient_SendFSM = new DiscoveryClient_SendFSM(pTransportService.pTransport_SendFSM);
	pDiscoveryClient_SendFSM.setHandler(ieHandler, jausRouter);
	pDiscoveryClient_SendFSM.setupNotifications();


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
				if ((done == false) && ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("DiscoveryClient_ReceiveFSM") != 0))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReportHeartbeatPulse.ID)
					{
						ReportHeartbeatPulse msg = new ReportHeartbeatPulse();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pDiscoveryClient_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if ((done == false) && ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("DiscoveryClient_ReceiveFSM") != 0))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReportServices.ID)
					{
						ReportServices msg = new ReportServices();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pDiscoveryClient_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if ((done == false) && ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("DiscoveryClient_ReceiveFSM") != 0))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReportIdentification.ID)
					{
						ReportIdentification msg = new ReportIdentification();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pDiscoveryClient_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if ((done == false) && ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("DiscoveryClient_ReceiveFSM") != 0))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == GrantNodeID.ID)
					{
						GrantNodeID msg = new GrantNodeID();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pDiscoveryClient_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if ((done == false) && ie.getName().compareTo("RHP_Timeout") == 0 && (ie.getSource().compareTo("DiscoveryClient_ReceiveFSM") != 0))
				{
					RHP_Timeout casted_ie = (RHP_Timeout) ie;
						pDiscoveryClient_ReceiveFSM.context.RHP_TimeoutTransition();
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
				if ((done == false) && ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("DiscoveryClient_ReceiveFSM") != 0))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReportHeartbeatPulse.ID)
					{
						ReportHeartbeatPulse msg = new ReportHeartbeatPulse();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pDiscoveryClient_ReceiveFSM.context.ReportHeartbeatPulseTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if ((done == false) && ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("DiscoveryClient_ReceiveFSM") != 0))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReportServices.ID)
					{
						ReportServices msg = new ReportServices();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pDiscoveryClient_ReceiveFSM.context.ReportServicesTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if ((done == false) && ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("DiscoveryClient_ReceiveFSM") != 0))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReportIdentification.ID)
					{
						ReportIdentification msg = new ReportIdentification();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pDiscoveryClient_ReceiveFSM.context.ReportIdentificationTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if ((done == false) && ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("DiscoveryClient_ReceiveFSM") != 0))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == GrantNodeID.ID)
					{
						GrantNodeID msg = new GrantNodeID();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pDiscoveryClient_ReceiveFSM.context.GrantNodeIDTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if ((done == false) && ie.getName().compareTo("RHP_Timeout") == 0 && (ie.getSource().compareTo("DiscoveryClient_ReceiveFSM") != 0))
				{
					RHP_Timeout casted_ie = (RHP_Timeout) ie;
						pDiscoveryClient_ReceiveFSM.context.RHP_TimeoutTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if ((done == false) && ie.getName().compareTo("Send") == 0 && (ie.getSource().compareTo("DiscoveryClient_ReceiveFSM") != 0))
				{
					Send casted_ie = (Send) ie;
						pDiscoveryClient_ReceiveFSM.context.SendTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if ((done == false) && ie.getName().compareTo("BroadcastLocal") == 0 && (ie.getSource().compareTo("DiscoveryClient_ReceiveFSM") != 0))
				{
					BroadcastLocal casted_ie = (BroadcastLocal) ie;
						pDiscoveryClient_ReceiveFSM.context.BroadcastLocalTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if ((done == false) && ie.getName().compareTo("BroadcastGlobal") == 0 && (ie.getSource().compareTo("DiscoveryClient_ReceiveFSM") != 0))
				{
					BroadcastGlobal casted_ie = (BroadcastGlobal) ie;
						pDiscoveryClient_ReceiveFSM.context.BroadcastGlobalTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if ((done == false) && ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("DiscoveryClient_SendFSM") != 0))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReportHeartbeatPulse.ID)
					{
						ReportHeartbeatPulse msg = new ReportHeartbeatPulse();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pDiscoveryClient_SendFSM.context.ReportHeartbeatPulseTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if ((done == false) && ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("DiscoveryClient_SendFSM") != 0))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReportServices.ID)
					{
						ReportServices msg = new ReportServices();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pDiscoveryClient_SendFSM.context.ReportServicesTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if ((done == false) && ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("DiscoveryClient_SendFSM") != 0))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReportIdentification.ID)
					{
						ReportIdentification msg = new ReportIdentification();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pDiscoveryClient_SendFSM.context.ReportIdentificationTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if ((done == false) && ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("DiscoveryClient_SendFSM") != 0))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == GrantNodeID.ID)
					{
						GrantNodeID msg = new GrantNodeID();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pDiscoveryClient_SendFSM.context.GrantNodeIDTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if ((done == false) && ie.getName().compareTo("RHP_Timeout") == 0 && (ie.getSource().compareTo("DiscoveryClient_SendFSM") != 0))
				{
					RHP_Timeout casted_ie = (RHP_Timeout) ie;
						pDiscoveryClient_SendFSM.context.RHP_TimeoutTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if ((done == false) && ie.getName().compareTo("Send") == 0 && (ie.getSource().compareTo("DiscoveryClient_SendFSM") != 0))
				{
					Send casted_ie = (Send) ie;
						pDiscoveryClient_SendFSM.context.SendTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if ((done == false) && ie.getName().compareTo("BroadcastLocal") == 0 && (ie.getSource().compareTo("DiscoveryClient_SendFSM") != 0))
				{
					BroadcastLocal casted_ie = (BroadcastLocal) ie;
						pDiscoveryClient_SendFSM.context.BroadcastLocalTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if ((done == false) && ie.getName().compareTo("BroadcastGlobal") == 0 && (ie.getSource().compareTo("DiscoveryClient_SendFSM") != 0))
				{
					BroadcastGlobal casted_ie = (BroadcastGlobal) ie;
						pDiscoveryClient_SendFSM.context.BroadcastGlobalTransition();
						done = true;
				}
			}
			catch (Exception e) {}

	

	    return done;
			}

}



