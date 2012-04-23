

using JTS;
using System;
using System.Collections.Generic;

namespace urn_jaus_example_addition_server_1_0{

public class  AdditionServerServiceDefService : Service{
	public AdditionServerServiceDef_additionServerFSM pAdditionServerServiceDef_additionServerFSM;


	public AdditionServerServiceDefService(JausRouter jausRouter )
    {

	pAdditionServerServiceDef_additionServerFSM = new AdditionServerServiceDef_additionServerFSM();
	pAdditionServerServiceDef_additionServerFSM.setHandlers(ref ieHandler, ref jausRouter);
	pAdditionServerServiceDef_additionServerFSM.setupNotifications();


	}
	

	public override void run()
    {
		// Perform any entry actions specified by the start state.
	pAdditionServerServiceDef_additionServerFSM.fsmStartedAction();

		
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
				if (ie.getName().CompareTo("InitToReadyEventDef") == 0 && (ie.getSource().CompareTo("AdditionServerServiceDef_additionServerFSM") != 0) && (!done))
				{
					InitToReadyEventDef casted_ie = (InitToReadyEventDef) ie;
						pAdditionServerServiceDef_additionServerFSM.context.InitToReadyEventDefTransition();
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AdditionServerServiceDef_additionServerFSM_Ready") == 0 && (ie.getSource().CompareTo("AdditionServerServiceDef_additionServerFSM") != 0) && (!done))
				{
						pAdditionServerServiceDef_additionServerFSM.context.InternalStateChange_To_AdditionServerServiceDef_additionServerFSM_ReadyTransition(ie);
						done = true;
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AdditionServerServiceDef_additionServerFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new QueryAddition().getID())
					{
						QueryAddition msg = new QueryAddition();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
                        uint sender = (uint)
                          ((casted_ie.getBody().getReceiveRec().getSrcSubsystemID() << 16) +
                          (casted_ie.getBody().getReceiveRec().getSrcNodeID() << 8) +
                          (casted_ie.getBody().getReceiveRec().getSrcComponentID()));

						pAdditionServerServiceDef_additionServerFSM.context.QueryAdditionTransition(msg, sender);
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InternalStateChange_To_AdditionServerServiceDef_additionServerFSM_Init") == 0 && (ie.getSource().CompareTo("AdditionServerServiceDef_additionServerFSM") != 0) && (!done))
				{
						pAdditionServerServiceDef_additionServerFSM.context.InternalStateChange_To_AdditionServerServiceDef_additionServerFSM_InitTransition(ie);
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
				if (ie.getName().CompareTo("Receive") == 0 && (ie.getSource().CompareTo("AdditionServerServiceDef_additionServerFSM") != 0) && (!done))
				{
					Receive casted_ie = (Receive) ie;
					int pos = 0;
					ushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
					if ( id == new QueryAddition().getID())
					{
						QueryAddition msg = new QueryAddition();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);
						pAdditionServerServiceDef_additionServerFSM.context.QueryAdditionTransition();
						done = true;
					}
				}
			} catch (Exception e) {}

			try
			{
				if (ie.getName().CompareTo("InitToReadyEventDef") == 0 && (ie.getSource().CompareTo("AdditionServerServiceDef_additionServerFSM") != 0) && (!done))
				{
					InitToReadyEventDef casted_ie = (InitToReadyEventDef) ie;
						pAdditionServerServiceDef_additionServerFSM.context.InitToReadyEventDefTransition();
						done = true;
				}
			} catch (Exception e) {}

	

		mutex.ReleaseMutex();
	    return done;
	}	

}

}

