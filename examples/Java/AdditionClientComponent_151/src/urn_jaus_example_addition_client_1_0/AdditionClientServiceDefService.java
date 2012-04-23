
package src.urn_jaus_example_addition_client_1_0;

import java.lang.Exception;
import java.util.logging.Level;
import java.util.logging.Logger;
import framework.Service;
import framework.JausUtils;
import framework.transport.JausRouter;
import framework.internalEvents.*;
import src.urn_jaus_example_addition_client_1_0.InternalEvents.*;
import src.urn_jaus_example_addition_client_1_0.Messages.*;


public class AdditionClientServiceDefService extends Service{

		public AdditionClientServiceDef_additionClientFSM pAdditionClientServiceDef_additionClientFSM;


	public AdditionClientServiceDefService(JausRouter jausRouter )
	{

	pAdditionClientServiceDef_additionClientFSM = new AdditionClientServiceDef_additionClientFSM();
	pAdditionClientServiceDef_additionClientFSM.setHandler(ieHandler, jausRouter);
	pAdditionClientServiceDef_additionClientFSM.setupNotifications();


	}
	
	public void run(){

		// Perform any entry actions specified by the start state.
	pAdditionClientServiceDef_additionClientFSM.serviceStartedAction();

		

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
				if (ie.getName().compareTo("InitToReadyEventDef") == 0 && (ie.getSource().compareTo("AdditionClientServiceDef_additionClientFSM") != 0) && (done == false))
				{
					InitToReadyEventDef casted_ie = (InitToReadyEventDef) ie;
						pAdditionClientServiceDef_additionClientFSM.context.InitToReadyEventDefTransition();
						done = true;
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AdditionClientServiceDef_additionClientFSM_Ready") == 0 && (ie.getSource().compareTo("AdditionClientServiceDef_additionClientFSM") != 0) && (done == false))
				{
						pAdditionClientServiceDef_additionClientFSM.context.InternalStateChange_To_AdditionClientServiceDef_additionClientFSM_ReadyTransition(ie);
						done = true;
					}
				}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AdditionClientServiceDef_additionClientFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReportAddition.ID)
					{
						ReportAddition msg = new ReportAddition();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pAdditionClientServiceDef_additionClientFSM.context.ReportAdditionTransition(msg);
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InternalStateChange_To_AdditionClientServiceDef_additionClientFSM_Init") == 0 && (ie.getSource().compareTo("AdditionClientServiceDef_additionClientFSM") != 0) && (done == false))
				{
						pAdditionClientServiceDef_additionClientFSM.context.InternalStateChange_To_AdditionClientServiceDef_additionClientFSM_InitTransition(ie);
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
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("AdditionClientServiceDef_additionClientFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReportAddition.ID)
					{
						ReportAddition msg = new ReportAddition();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pAdditionClientServiceDef_additionClientFSM.context.ReportAdditionTransition();
						done = true;
				}
			}
		}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("InitToReadyEventDef") == 0 && (ie.getSource().compareTo("AdditionClientServiceDef_additionClientFSM") != 0) && (done == false))
				{
					InitToReadyEventDef casted_ie = (InitToReadyEventDef) ie;
						pAdditionClientServiceDef_additionClientFSM.context.InitToReadyEventDefTransition();
						done = true;
	}
			}
			catch (Exception e) {}

	

	    return done;
	}

}



