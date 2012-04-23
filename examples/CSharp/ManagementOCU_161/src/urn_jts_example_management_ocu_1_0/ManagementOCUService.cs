

using JTS;
using System;
using System.Collections.Generic;

namespace urn_jts_example_management_ocu_1_0{

public class  ManagementOCUService : Service{
	public ManagementOCU_ManagementOcuFSM pManagementOCU_ManagementOcuFSM;


	public ManagementOCUService(JausRouter jausRouter )
    {

	pManagementOCU_ManagementOcuFSM = new ManagementOCU_ManagementOcuFSM();
	pManagementOCU_ManagementOcuFSM.setHandlers(ref ieHandler, ref jausRouter);
	pManagementOCU_ManagementOcuFSM.setupNotifications();


	}
	

	public override void run()
    {
		// Perform any entry actions specified by the start state.
	pManagementOCU_ManagementOcuFSM.sendRequestControlAction();
	pManagementOCU_ManagementOcuFSM.displayMenuAction();

		
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
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("ManagementOCU_ManagementOcuFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new ConfirmControl().getID())
					{
						ConfirmControl msg = new ConfirmControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pManagementOCU_ManagementOcuFSM.context.ConfirmControlTransition(msg);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("MenuItemEntered") == 0 && (ie.getSource().CompareTo("ManagementOCU_ManagementOcuFSM") != 0) && (!done))
				{
					MenuItemEntered casted_ie = (MenuItemEntered) ie;
						MenuItemEntered msg = casted_ie;
						pManagementOCU_ManagementOcuFSM.context.MenuItemEnteredTransition(msg);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("MenuItemEntered") == 0 && (ie.getSource().CompareTo("ManagementOCU_ManagementOcuFSM") != 0) && (!done))
				{
					MenuItemEntered casted_ie = (MenuItemEntered) ie;
						MenuItemEntered msg = casted_ie;
						pManagementOCU_ManagementOcuFSM.context.MenuItemEnteredTransition(msg);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("ManagementOCU_ManagementOcuFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new ConfirmControl().getID())
					{
						ConfirmControl msg = new ConfirmControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pManagementOCU_ManagementOcuFSM.context.ConfirmControlTransition(msg);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_ManagementOCU_ManagementOcuFSM_HandleMenu") == 0 && (ie.getSource().CompareTo("ManagementOCU_ManagementOcuFSM") != 0) && (!done))
				{
						pManagementOCU_ManagementOcuFSM.context.InternalStateChange_To_ManagementOCU_ManagementOcuFSM_HandleMenuTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("MenuItemEntered") == 0 && (ie.getSource().CompareTo("ManagementOCU_ManagementOcuFSM") != 0) && (!done))
				{
					MenuItemEntered casted_ie = (MenuItemEntered) ie;
						MenuItemEntered msg = casted_ie;
						pManagementOCU_ManagementOcuFSM.context.MenuItemEnteredTransition(msg);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("MenuItemEntered") == 0 && (ie.getSource().CompareTo("ManagementOCU_ManagementOcuFSM") != 0) && (!done))
				{
					MenuItemEntered casted_ie = (MenuItemEntered) ie;
						MenuItemEntered msg = casted_ie;
						pManagementOCU_ManagementOcuFSM.context.MenuItemEnteredTransition(msg);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("ManagementOCU_ManagementOcuFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new RejectControl().getID())
					{
						RejectControl msg = new RejectControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pManagementOCU_ManagementOcuFSM.context.RejectControlTransition(msg);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("ManagementOCU_ManagementOcuFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new ReportStatus().getID())
					{
						ReportStatus msg = new ReportStatus();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pManagementOCU_ManagementOcuFSM.context.ReportStatusTransition(msg);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_ManagementOCU_ManagementOcuFSM_WaitForControl") == 0 && (ie.getSource().CompareTo("ManagementOCU_ManagementOcuFSM") != 0) && (!done))
				{
						pManagementOCU_ManagementOcuFSM.context.InternalStateChange_To_ManagementOCU_ManagementOcuFSM_WaitForControlTransition(ie);
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
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("ManagementOCU_ManagementOcuFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new ReportStatus().getID())
					{
						ReportStatus msg = new ReportStatus();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pManagementOCU_ManagementOcuFSM.context.ReportStatusTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("ManagementOCU_ManagementOcuFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new ConfirmControl().getID())
					{
						ConfirmControl msg = new ConfirmControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pManagementOCU_ManagementOcuFSM.context.ConfirmControlTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("ManagementOCU_ManagementOcuFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new ReportControl().getID())
					{
						ReportControl msg = new ReportControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pManagementOCU_ManagementOcuFSM.context.ReportControlTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("ManagementOCU_ManagementOcuFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new RejectControl().getID())
					{
						RejectControl msg = new RejectControl();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pManagementOCU_ManagementOcuFSM.context.RejectControlTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("MenuItemEntered") == 0 && (ie.getSource().CompareTo("ManagementOCU_ManagementOcuFSM") != 0) && (!done))
				{
					MenuItemEntered casted_ie = (MenuItemEntered) ie;
						pManagementOCU_ManagementOcuFSM.context.MenuItemEnteredTransition();
						done = true;
				}
			} catch (Exception e) {}

	

		mutex.ReleaseMutex();
	    return done;
	}	

}

}

