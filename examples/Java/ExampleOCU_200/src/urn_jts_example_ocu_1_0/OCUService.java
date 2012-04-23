
package src.urn_jts_example_ocu_1_0;

import java.lang.Exception;
import java.util.logging.Level;
import java.util.logging.Logger;
import framework.Service;
import framework.JausUtils;
import framework.transport.JausRouter;
import framework.internalEvents.*;
import src.urn_jts_example_ocu_1_0.InternalEvents.*;
import src.urn_jts_example_ocu_1_0.Messages.*;


public class OCUService extends Service{

		public OCU_OcuFSM pOCU_OcuFSM;


	public OCUService(JausRouter jausRouter )
	{

	pOCU_OcuFSM = new OCU_OcuFSM();
	pOCU_OcuFSM.setHandler(ieHandler, jausRouter);
	pOCU_OcuFSM.setupNotifications();


	}
	
	public void run(){

		// Perform any entry actions specified by the start state.
	pOCU_OcuFSM.sendQueryIdentificationAction();

		

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
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReportIdentification.ID)
					{
						ReportIdentification msg = new ReportIdentification();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						long source_id = (casted_ie.getBody().getReceiveRec().getSrcSubsystemID() << 16) + (casted_ie.getBody().getReceiveRec().getSrcNodeID() << 8) + casted_ie.getBody().getReceiveRec().getSrcComponentID();
                        pOCU_OcuFSM.context.ReportIdentificationTransition(source_id);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_OCU_OcuFSM_FindWaypointDriver") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
						pOCU_OcuFSM.context.InternalStateChange_To_OCU_OcuFSM_FindWaypointDriverTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_OCU_OcuFSM_WaitForControl") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
						pOCU_OcuFSM.context.InternalStateChange_To_OCU_OcuFSM_WaitForControlTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_OCU_OcuFSM_WaitForResume") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
						pOCU_OcuFSM.context.InternalStateChange_To_OCU_OcuFSM_WaitForResumeTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_OCU_OcuFSM_Running") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
						pOCU_OcuFSM.context.InternalStateChange_To_OCU_OcuFSM_RunningTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReportServices.ID)
					{
						ReportServices msg = new ReportServices();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pOCU_OcuFSM.context.ReportServicesTransition(msg);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_OCU_OcuFSM_FindDiscoveryServer") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
						pOCU_OcuFSM.context.InternalStateChange_To_OCU_OcuFSM_FindDiscoveryServerTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_OCU_OcuFSM_WaitForControl") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
						pOCU_OcuFSM.context.InternalStateChange_To_OCU_OcuFSM_WaitForControlTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_OCU_OcuFSM_WaitForResume") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
						pOCU_OcuFSM.context.InternalStateChange_To_OCU_OcuFSM_WaitForResumeTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_OCU_OcuFSM_Running") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
						pOCU_OcuFSM.context.InternalStateChange_To_OCU_OcuFSM_RunningTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ConfirmControl.ID)
					{
						ConfirmControl msg = new ConfirmControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pOCU_OcuFSM.context.ConfirmControlTransition(msg);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ConfirmControl.ID)
					{
						ConfirmControl msg = new ConfirmControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pOCU_OcuFSM.context.ConfirmControlTransition(msg);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_OCU_OcuFSM_FindDiscoveryServer") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
						pOCU_OcuFSM.context.InternalStateChange_To_OCU_OcuFSM_FindDiscoveryServerTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_OCU_OcuFSM_FindWaypointDriver") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
						pOCU_OcuFSM.context.InternalStateChange_To_OCU_OcuFSM_FindWaypointDriverTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_OCU_OcuFSM_WaitForResume") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
						pOCU_OcuFSM.context.InternalStateChange_To_OCU_OcuFSM_WaitForResumeTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_OCU_OcuFSM_Running") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
						pOCU_OcuFSM.context.InternalStateChange_To_OCU_OcuFSM_RunningTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReportStatus.ID)
					{
						ReportStatus msg = new ReportStatus();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pOCU_OcuFSM.context.ReportStatusTransition(msg);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReportStatus.ID)
					{
						ReportStatus msg = new ReportStatus();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pOCU_OcuFSM.context.ReportStatusTransition(msg);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_OCU_OcuFSM_FindDiscoveryServer") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
						pOCU_OcuFSM.context.InternalStateChange_To_OCU_OcuFSM_FindDiscoveryServerTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_OCU_OcuFSM_FindWaypointDriver") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
						pOCU_OcuFSM.context.InternalStateChange_To_OCU_OcuFSM_FindWaypointDriverTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_OCU_OcuFSM_WaitForControl") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
						pOCU_OcuFSM.context.InternalStateChange_To_OCU_OcuFSM_WaitForControlTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_OCU_OcuFSM_Running") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
						pOCU_OcuFSM.context.InternalStateChange_To_OCU_OcuFSM_RunningTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReportActiveElement.ID)
					{
						ReportActiveElement msg = new ReportActiveElement();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pOCU_OcuFSM.context.ReportActiveElementTransition(msg);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReportLocalWaypoint.ID)
					{
						ReportLocalWaypoint msg = new ReportLocalWaypoint();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pOCU_OcuFSM.context.ReportLocalWaypointTransition(msg);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReportLocalPose.ID)
					{
						ReportLocalPose msg = new ReportLocalPose();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pOCU_OcuFSM.context.ReportLocalPoseTransition(msg);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("GuiControlEntered") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
					GuiControlEntered casted_ie = (GuiControlEntered) ie;
						GuiControlEntered msg = casted_ie;
						pOCU_OcuFSM.context.GuiControlEnteredTransition(msg);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ConfirmElementRequest.ID)
					{
						ConfirmElementRequest msg = new ConfirmElementRequest();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pOCU_OcuFSM.context.ConfirmElementRequestTransition(msg);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_OCU_OcuFSM_FindDiscoveryServer") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
						pOCU_OcuFSM.context.InternalStateChange_To_OCU_OcuFSM_FindDiscoveryServerTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_OCU_OcuFSM_FindWaypointDriver") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
						pOCU_OcuFSM.context.InternalStateChange_To_OCU_OcuFSM_FindWaypointDriverTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_OCU_OcuFSM_WaitForControl") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
						pOCU_OcuFSM.context.InternalStateChange_To_OCU_OcuFSM_WaitForControlTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_OCU_OcuFSM_WaitForResume") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
						pOCU_OcuFSM.context.InternalStateChange_To_OCU_OcuFSM_WaitForResumeTransition(ie);
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
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReportLocalPose.ID)
					{
						ReportLocalPose msg = new ReportLocalPose();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pOCU_OcuFSM.context.ReportLocalPoseTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReportIdentification.ID)
					{
						ReportIdentification msg = new ReportIdentification();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pOCU_OcuFSM.context.ReportIdentificationTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ConfirmElementRequest.ID)
					{
						ConfirmElementRequest msg = new ConfirmElementRequest();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pOCU_OcuFSM.context.ConfirmElementRequestTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ConfirmControl.ID)
					{
						ConfirmControl msg = new ConfirmControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pOCU_OcuFSM.context.ConfirmControlTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReportServices.ID)
					{
						ReportServices msg = new ReportServices();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pOCU_OcuFSM.context.ReportServicesTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReportLocalWaypoint.ID)
					{
						ReportLocalWaypoint msg = new ReportLocalWaypoint();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pOCU_OcuFSM.context.ReportLocalWaypointTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReportActiveElement.ID)
					{
						ReportActiveElement msg = new ReportActiveElement();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pOCU_OcuFSM.context.ReportActiveElementTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReportStatus.ID)
					{
						ReportStatus msg = new ReportStatus();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pOCU_OcuFSM.context.ReportStatusTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReportControl.ID)
					{
						ReportControl msg = new ReportControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pOCU_OcuFSM.context.ReportControlTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("GuiControlEntered") == 0 && (ie.getSource().compareTo("OCU_OcuFSM") != 0) && (done == false))
				{
					GuiControlEntered casted_ie = (GuiControlEntered) ie;
						pOCU_OcuFSM.context.GuiControlEnteredTransition();
						done = true;
				}
			}
			catch (Exception e) {}

	

	    return done;
			}

}



