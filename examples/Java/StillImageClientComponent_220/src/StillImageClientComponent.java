
package src;

import framework.transport.JausRouter;
import framework.transport.JausAddress;
import java.util.ArrayList;
import framework.EventReceiver;
import framework.Service;
import framework.internalEvents.InternalEvent;
import src.urn_jts_StillImageClient_1_0.StillImageClientService;



class StillImageClientComponent extends EventReceiver
    {
	protected ArrayList<Service> serviceList = new ArrayList<Service>();
	JausRouter jausRouter;

	StillImageClientComponent(int subsystem, short node, short component)
    {
		jausRouter = new JausRouter(new JausAddress(subsystem, node, component), ieHandler);
		
	/// Instantiate services
		StillImageClientService pStillImageClientService = new StillImageClientService(jausRouter);


	/// Add all the Services for the Component
		serviceList.add(pStillImageClientService);

	}

	public void startComponent()
    {
		jausRouter.start();
		this.start();

		for(int i = 0; i <serviceList.size(); i++)
        {
			serviceList.get(i).start();
		}
	}

	public void shutdownComponent()
    {
		for (int i=0; i<serviceList.size(); i++)
        {
			serviceList.get(i).stop();
		}
		this.stop();
		jausRouter.stop();
	}

	public void processInternalEvent(InternalEvent ie)
	{
		boolean done = false;
	
		//
		// When a component receives an internal event, it passes it
		// to the services to handling, children services first.  If the
		// event is not processed by normal transitions, it's passed
		// again to the services (children first) for default transitions.
		// A given event may only be processed by at most one service.
		//
		for (int i = serviceList.size(); i>0; i--)
		{
			if (!done) done = serviceList.get(i-1).processTransitions(ie);
		}
		for (int i = serviceList.size(); i>0; i--)
		{
			if (!done) done = serviceList.get(i-1).defaultTransitions(ie);
		}
	}

}
