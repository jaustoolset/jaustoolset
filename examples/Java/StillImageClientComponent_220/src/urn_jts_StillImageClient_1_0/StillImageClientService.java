
package src.urn_jts_StillImageClient_1_0;

import java.lang.Exception;
import java.util.logging.Level;
import java.util.logging.Logger;
import framework.Service;
import framework.JausUtils;
import framework.transport.JausRouter;
import framework.internalEvents.*;
import src.urn_jts_StillImageClient_1_0.Messages.*;


public class StillImageClientService extends Service{

		public StillImageClient_StillImageClientFSM pStillImageClient_StillImageClientFSM;


	public StillImageClientService(JausRouter jausRouter )
	{

	pStillImageClient_StillImageClientFSM = new StillImageClient_StillImageClientFSM();
	pStillImageClient_StillImageClientFSM.setHandler(ieHandler, jausRouter);
	pStillImageClient_StillImageClientFSM.setupNotifications();


	}
	
	public void run(){

		// Perform any entry actions specified by the start state.
	pStillImageClient_StillImageClientFSM.SendQueryStillImageDataAction();

		

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
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("StillImageClient_StillImageClientFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReportStillImageData.ID)
					{
						ReportStillImageData msg = new ReportStillImageData();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pStillImageClient_StillImageClientFSM.context.ReportStillImageDataTransition(msg);
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
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("StillImageClient_StillImageClientFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReportStillImageSensorCapabilities.ID)
					{
						ReportStillImageSensorCapabilities msg = new ReportStillImageSensorCapabilities();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pStillImageClient_StillImageClientFSM.context.ReportStillImageSensorCapabilitiesTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("StillImageClient_StillImageClientFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReportStillImageSensorConfiguration.ID)
					{
						ReportStillImageSensorConfiguration msg = new ReportStillImageSensorConfiguration();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pStillImageClient_StillImageClientFSM.context.ReportStillImageSensorConfigurationTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

			try
			{
				if (ie.getName().compareTo("Receive") == 0 && (ie.getSource().compareTo("StillImageClient_StillImageClientFSM") != 0) && (done == false))
				{
					Receive casted_ie = (Receive) ie;
					int id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);
					if ( id == ReportStillImageData.ID)
					{
						ReportStillImageData msg = new ReportStillImageData();
						msg.decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);
						pStillImageClient_StillImageClientFSM.context.ReportStillImageDataTransition();
						done = true;
					}
				}
			}
			catch (Exception e) {}

	

	    return done;
			}

}



