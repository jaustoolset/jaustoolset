

using JTS;
using System;
using System.Collections.Generic;

namespace urn_jaus_example_addition_client_1_0{

public class  AdditionClientServiceDefService : Service{
	public AdditionClientServiceDef_additionClientFSM pAdditionClientServiceDef_additionClientFSM;


	public AdditionClientServiceDefService(JausRouter jausRouter )
    {

	pAdditionClientServiceDef_additionClientFSM = new AdditionClientServiceDef_additionClientFSM();
	pAdditionClientServiceDef_additionClientFSM.setHandlers(ref ieHandler, ref jausRouter);
	pAdditionClientServiceDef_additionClientFSM.setupNotifications();


	}
	

	public override void run()
    {
		// Perform any entry actions specified by the start state.
	pAdditionClientServiceDef_additionClientFSM.serviceStartedAction();

		
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
				if (ie.getName().CompareTo("InitToReadyEventDef") == 0 && (ie.getSource().CompareTo("AdditionClientServiceDef_additionClientFSM") != 0) && (!done))
				{
					InitToReadyEventDef casted_ie = (InitToReadyEventDef) ie;
						pAdditionClientServiceDef_additionClientFSM.context.InitToReadyEventDefTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AdditionClientServiceDef_additionClientFSM_Ready") == 0 && (ie.getSource().CompareTo("AdditionClientServiceDef_additionClientFSM") != 0) && (!done))
				{
						pAdditionClientServiceDef_additionClientFSM.context.InternalStateChange_To_AdditionClientServiceDef_additionClientFSM_ReadyTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AdditionClientServiceDef_additionClientFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new ReportAddition().getID())
					{
						ReportAddition msg = new ReportAddition();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pAdditionClientServiceDef_additionClientFSM.context.ReportAdditionTransition(msg);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AdditionClientServiceDef_additionClientFSM_Init") == 0 && (ie.getSource().CompareTo("AdditionClientServiceDef_additionClientFSM") != 0) && (!done))
				{
						pAdditionClientServiceDef_additionClientFSM.context.InternalStateChange_To_AdditionClientServiceDef_additionClientFSM_InitTransition(ie);
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
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AdditionClientServiceDef_additionClientFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new ReportAddition().getID())
					{
						ReportAddition msg = new ReportAddition();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pAdditionClientServiceDef_additionClientFSM.context.ReportAdditionTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InitToReadyEventDef") == 0 && (ie.getSource().CompareTo("AdditionClientServiceDef_additionClientFSM") != 0) && (!done))
				{
					InitToReadyEventDef casted_ie = (InitToReadyEventDef) ie;
						pAdditionClientServiceDef_additionClientFSM.context.InitToReadyEventDefTransition();
						done = true;
				}
			} catch (Exception e) {}

	

		mutex.ReleaseMutex();
	    return done;
	}	

}

}

