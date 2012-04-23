
package src.urn_jts_example_management_ocu_1_0;

import java.lang.Exception;
import java.util.logging.Level;
import java.util.logging.Logger;
import framework.Service;
import framework.JausUtils;
import framework.transport.JausRouter;
import framework.internalEvents.*;
import src.urn_jts_example_management_ocu_1_0.InternalEvents.*;
import src.urn_jts_example_management_ocu_1_0.Messages.*;


public class ManagementOCUService extends Service{

		public ManagementOCU_ManagementOcuFSM pManagementOCU_ManagementOcuFSM;


	public ManagementOCUService(JausRouter jausRouter )
	{

	pManagementOCU_ManagementOcuFSM = new ManagementOCU_ManagementOcuFSM();
	pManagementOCU_ManagementOcuFSM.setHandler(ieHandler, jausRouter);
	pManagementOCU_ManagementOcuFSM.setupNotifications();


	}
	
	public void run(){

		// Perform any entry actions specified by the start state.
	pManagementOCU_ManagementOcuFSM.sendRequestControlAction();
	pManagementOCU_ManagementOcuFSM.displayMenuAction();

		

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
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("ManagementOCU_ManagementOcuFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ConfirmControl.ID)
					{
						ConfirmControl msg = new ConfirmControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pManagementOCU_ManagementOcuFSM.context.ConfirmControlTransition(msg);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("MenuItemEntered") == 0 && (ie.getSource().compareTo("ManagementOCU_ManagementOcuFSM") != 0) && (done == false))
				{
					MenuItemEntered casted_ie = (MenuItemEntered) ie;
						MenuItemEntered msg = casted_ie;
						pManagementOCU_ManagementOcuFSM.context.MenuItemEnteredTransition(msg);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("MenuItemEntered") == 0 && (ie.getSource().compareTo("ManagementOCU_ManagementOcuFSM") != 0) && (done == false))
				{
					MenuItemEntered casted_ie = (MenuItemEntered) ie;
						MenuItemEntered msg = casted_ie;
						pManagementOCU_ManagementOcuFSM.context.MenuItemEnteredTransition(msg);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("ManagementOCU_ManagementOcuFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ConfirmControl.ID)
					{
						ConfirmControl msg = new ConfirmControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pManagementOCU_ManagementOcuFSM.context.ConfirmControlTransition(msg);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_ManagementOCU_ManagementOcuFSM_HandleMenu") == 0 && (ie.getSource().compareTo("ManagementOCU_ManagementOcuFSM") != 0) && (done == false))
				{
						pManagementOCU_ManagementOcuFSM.context.InternalStateChange_To_ManagementOCU_ManagementOcuFSM_HandleMenuTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("MenuItemEntered") == 0 && (ie.getSource().compareTo("ManagementOCU_ManagementOcuFSM") != 0) && (done == false))
				{
					MenuItemEntered casted_ie = (MenuItemEntered) ie;
						MenuItemEntered msg = casted_ie;
						pManagementOCU_ManagementOcuFSM.context.MenuItemEnteredTransition(msg);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("MenuItemEntered") == 0 && (ie.getSource().compareTo("ManagementOCU_ManagementOcuFSM") != 0) && (done == false))
				{
					MenuItemEntered casted_ie = (MenuItemEntered) ie;
						MenuItemEntered msg = casted_ie;
						pManagementOCU_ManagementOcuFSM.context.MenuItemEnteredTransition(msg);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("ManagementOCU_ManagementOcuFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == RejectControl.ID)
					{
						RejectControl msg = new RejectControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pManagementOCU_ManagementOcuFSM.context.RejectControlTransition(msg);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("ManagementOCU_ManagementOcuFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReportStatus.ID)
					{
						ReportStatus msg = new ReportStatus();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pManagementOCU_ManagementOcuFSM.context.ReportStatusTransition(msg);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_ManagementOCU_ManagementOcuFSM_WaitForControl") == 0 && (ie.getSource().compareTo("ManagementOCU_ManagementOcuFSM") != 0) && (done == false))
				{
						pManagementOCU_ManagementOcuFSM.context.InternalStateChange_To_ManagementOCU_ManagementOcuFSM_WaitForControlTransition(ie);
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
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("ManagementOCU_ManagementOcuFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReportStatus.ID)
					{
						ReportStatus msg = new ReportStatus();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pManagementOCU_ManagementOcuFSM.context.ReportStatusTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("ManagementOCU_ManagementOcuFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ConfirmControl.ID)
					{
						ConfirmControl msg = new ConfirmControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pManagementOCU_ManagementOcuFSM.context.ConfirmControlTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("ManagementOCU_ManagementOcuFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReportControl.ID)
					{
						ReportControl msg = new ReportControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pManagementOCU_ManagementOcuFSM.context.ReportControlTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("ManagementOCU_ManagementOcuFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == RejectControl.ID)
					{
						RejectControl msg = new RejectControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pManagementOCU_ManagementOcuFSM.context.RejectControlTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("MenuItemEntered") == 0 && (ie.getSource().compareTo("ManagementOCU_ManagementOcuFSM") != 0) && (done == false))
				{
					MenuItemEntered casted_ie = (MenuItemEntered) ie;
						pManagementOCU_ManagementOcuFSM.context.MenuItemEnteredTransition();
						done = true;
				}
			}
			catch (Exception e) {}

	

	    return done;
			}

}



