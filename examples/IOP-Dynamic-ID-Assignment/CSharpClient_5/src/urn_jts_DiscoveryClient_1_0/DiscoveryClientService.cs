

using JTS;
using System;
using System.Collections.Generic;

namespace urn_jts_DiscoveryClient_1_0{

public class  DiscoveryClientService : Service{
	public DiscoveryClient_ReceiveFSM pDiscoveryClient_ReceiveFSM;
public DiscoveryClient_SendFSM pDiscoveryClient_SendFSM;


	public DiscoveryClientService(JausRouter jausRouter , urn_jaus_jss_core_Transport_1_1.TransportService pTransportService)
    {

	pDiscoveryClient_ReceiveFSM = new DiscoveryClient_ReceiveFSM(pTransportService.pTransport_ReceiveFSM);
	pDiscoveryClient_ReceiveFSM.setHandlers(ref ieHandler, ref jausRouter);
	pDiscoveryClient_ReceiveFSM.setupNotifications();
	pDiscoveryClient_SendFSM = new DiscoveryClient_SendFSM(pTransportService.pTransport_SendFSM);
	pDiscoveryClient_SendFSM.setHandlers(ref ieHandler, ref jausRouter);
	pDiscoveryClient_SendFSM.setupNotifications();


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
				if ((!done) && ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("DiscoveryClient_ReceiveFSM") != 0))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new ReportHeartbeatPulse().getID())
					{
						ReportHeartbeatPulse msg = new ReportHeartbeatPulse();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pDiscoveryClient_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if ((!done) && ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("DiscoveryClient_ReceiveFSM") != 0))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new ReportServices().getID())
					{
						ReportServices msg = new ReportServices();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pDiscoveryClient_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if ((!done) && ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("DiscoveryClient_ReceiveFSM") != 0))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new ReportIdentification().getID())
					{
						ReportIdentification msg = new ReportIdentification();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pDiscoveryClient_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if ((!done) && ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("DiscoveryClient_ReceiveFSM") != 0))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new GrantNodeID().getID())
					{
						GrantNodeID msg = new GrantNodeID();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						Receive.Body.ReceiveRec transportData = casted_ie.getBody().getReceiveRec();
						pDiscoveryClient_ReceiveFSM.context.ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if ((!done) && ie.getName().CompareTo("RHP_Timeout") == 0 && (ie.getSource().CompareTo("DiscoveryClient_ReceiveFSM") != 0))
				{
					RHP_Timeout casted_ie = (RHP_Timeout) ie;
						pDiscoveryClient_ReceiveFSM.context.RHP_TimeoutTransition();
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
				if ((!done) && ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("DiscoveryClient_ReceiveFSM") != 0))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new ReportHeartbeatPulse().getID())
					{
						ReportHeartbeatPulse msg = new ReportHeartbeatPulse();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pDiscoveryClient_ReceiveFSM.context.ReportHeartbeatPulseTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if ((!done) && ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("DiscoveryClient_ReceiveFSM") != 0))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new ReportServices().getID())
					{
						ReportServices msg = new ReportServices();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pDiscoveryClient_ReceiveFSM.context.ReportServicesTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if ((!done) && ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("DiscoveryClient_ReceiveFSM") != 0))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new ReportIdentification().getID())
					{
						ReportIdentification msg = new ReportIdentification();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pDiscoveryClient_ReceiveFSM.context.ReportIdentificationTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if ((!done) && ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("DiscoveryClient_ReceiveFSM") != 0))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new GrantNodeID().getID())
					{
						GrantNodeID msg = new GrantNodeID();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pDiscoveryClient_ReceiveFSM.context.GrantNodeIDTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if ((!done) && ie.getName().CompareTo("RHP_Timeout") == 0 && (ie.getSource().CompareTo("DiscoveryClient_ReceiveFSM") != 0))
				{
					RHP_Timeout casted_ie = (RHP_Timeout) ie;
						pDiscoveryClient_ReceiveFSM.context.RHP_TimeoutTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if ((!done) && ie.getName().CompareTo("Send") == 0 && (ie.getSource().CompareTo("DiscoveryClient_ReceiveFSM") != 0))
				{
					Send casted_ie = (Send) ie;
						pDiscoveryClient_ReceiveFSM.context.SendTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if ((!done) && ie.getName().CompareTo("BroadcastLocal") == 0 && (ie.getSource().CompareTo("DiscoveryClient_ReceiveFSM") != 0))
				{
					BroadcastLocal casted_ie = (BroadcastLocal) ie;
						pDiscoveryClient_ReceiveFSM.context.BroadcastLocalTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if ((!done) && ie.getName().CompareTo("BroadcastGlobal") == 0 && (ie.getSource().CompareTo("DiscoveryClient_ReceiveFSM") != 0))
				{
					BroadcastGlobal casted_ie = (BroadcastGlobal) ie;
						pDiscoveryClient_ReceiveFSM.context.BroadcastGlobalTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if ((!done) && ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("DiscoveryClient_SendFSM") != 0))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new ReportHeartbeatPulse().getID())
					{
						ReportHeartbeatPulse msg = new ReportHeartbeatPulse();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pDiscoveryClient_SendFSM.context.ReportHeartbeatPulseTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if ((!done) && ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("DiscoveryClient_SendFSM") != 0))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new ReportServices().getID())
					{
						ReportServices msg = new ReportServices();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pDiscoveryClient_SendFSM.context.ReportServicesTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if ((!done) && ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("DiscoveryClient_SendFSM") != 0))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new ReportIdentification().getID())
					{
						ReportIdentification msg = new ReportIdentification();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pDiscoveryClient_SendFSM.context.ReportIdentificationTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if ((!done) && ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("DiscoveryClient_SendFSM") != 0))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new GrantNodeID().getID())
					{
						GrantNodeID msg = new GrantNodeID();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pDiscoveryClient_SendFSM.context.GrantNodeIDTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if ((!done) && ie.getName().CompareTo("RHP_Timeout") == 0 && (ie.getSource().CompareTo("DiscoveryClient_SendFSM") != 0))
				{
					RHP_Timeout casted_ie = (RHP_Timeout) ie;
						pDiscoveryClient_SendFSM.context.RHP_TimeoutTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if ((!done) && ie.getName().CompareTo("Send") == 0 && (ie.getSource().CompareTo("DiscoveryClient_SendFSM") != 0))
				{
					Send casted_ie = (Send) ie;
						pDiscoveryClient_SendFSM.context.SendTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if ((!done) && ie.getName().CompareTo("BroadcastLocal") == 0 && (ie.getSource().CompareTo("DiscoveryClient_SendFSM") != 0))
				{
					BroadcastLocal casted_ie = (BroadcastLocal) ie;
						pDiscoveryClient_SendFSM.context.BroadcastLocalTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if ((!done) && ie.getName().CompareTo("BroadcastGlobal") == 0 && (ie.getSource().CompareTo("DiscoveryClient_SendFSM") != 0))
				{
					BroadcastGlobal casted_ie = (BroadcastGlobal) ie;
						pDiscoveryClient_SendFSM.context.BroadcastGlobalTransition();
						done = true;
				}
			} catch (Exception e) {}

	

		mutex.ReleaseMutex();
	    return done;
	}	

}

}

