
package src.urn_jaus_example_addition_server_1_0;

import java.lang.Exception;
import java.util.logging.Level;
import java.util.logging.Logger;
import framework.Service;
import framework.JausUtils;
import framework.transport.JausRouter;
import framework.internalEvents.*;
import src.urn_jaus_example_addition_server_1_0.InternalEvents.*;
import src.urn_jaus_example_addition_server_1_0.Messages.*;


public class AdditionServerServiceDefService extends Service{

		public AdditionServerServiceDef_additionServerFSM pAdditionServerServiceDef_additionServerFSM;


	public AdditionServerServiceDefService(JausRouter jausRouter )
	{

	pAdditionServerServiceDef_additionServerFSM = new AdditionServerServiceDef_additionServerFSM();
	pAdditionServerServiceDef_additionServerFSM.setHandler(ieHandler, jausRouter);
	pAdditionServerServiceDef_additionServerFSM.setupNotifications();


	}
	
	public void run(){

		// Perform any entry actions specified by the start state.
	pAdditionServerServiceDef_additionServerFSM.fsmStartedAction();

		

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
				if (ie.getName().compareTo("InitToReadyEventDef") == 0 && (ie.getSource().compareTo("AdditionServerServiceDef_additionServerFSM") != 0) && (done == false))
				{
					InitToReadyEventDef casted_ie = (InitToReadyEventDef) ie;
						pAdditionServerServiceDef_additionServerFSM.context.InitToReadyEventDefTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AdditionServerServiceDef_additionServerFSM_Ready") == 0 && (ie.getSource().compareTo("AdditionServerServiceDef_additionServerFSM") != 0) && (done == false))
				{
						pAdditionServerServiceDef_additionServerFSM.context.InternalStateChange_To_AdditionServerServiceDef_additionServerFSM_ReadyTransition(ie);
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AdditionServerServiceDef_additionServerFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == QueryAddition.ID)
					{
						QueryAddition msg = new QueryAddition();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						long sender = (long)
							(casted_ie.getBody().getReceiveRec().getSrcSubsystemID() << 16) +   
							(casted_ie.getBody().getReceiveRec().getSrcNodeID() << 8) + 
							(casted_ie.getBody().getReceiveRec().getSrcComponentID());

						pAdditionServerServiceDef_additionServerFSM.context.QueryAdditionTransition(msg, sender);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AdditionServerServiceDef_additionServerFSM_Init") == 0 && (ie.getSource().compareTo("AdditionServerServiceDef_additionServerFSM") != 0) && (done == false))
				{
						pAdditionServerServiceDef_additionServerFSM.context.InternalStateChange_To_AdditionServerServiceDef_additionServerFSM_InitTransition(ie);
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
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AdditionServerServiceDef_additionServerFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == QueryAddition.ID)
					{
						QueryAddition msg = new QueryAddition();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pAdditionServerServiceDef_additionServerFSM.context.QueryAdditionTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InitToReadyEventDef") == 0 && (ie.getSource().compareTo("AdditionServerServiceDef_additionServerFSM") != 0) && (done == false))
				{
					InitToReadyEventDef casted_ie = (InitToReadyEventDef) ie;
						pAdditionServerServiceDef_additionServerFSM.context.InitToReadyEventDefTransition();
						done = true;
				}
			}
			catch (Exception e) {}

	

	    return done;
			}

}



