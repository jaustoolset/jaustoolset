

using JTS;
using System;
using System.Collections.Generic;


public class CSharpClient : EventReceiver {
	protected List<Service> serviceList;
	JausRouter jausRouter;

	public CSharpClient(ushort subsystem, byte node, byte component, bool allowWildcards = false)
    {
		jausRouter = new JausRouter(new JausAddress(subsystem, node, component), ieHandler, allowWildcards);
		serviceList = new List<Service>();
		
		/// Instantiate services
		urn_jaus_jss_core_Transport_1_1.TransportService pTransportService = new urn_jaus_jss_core_Transport_1_1.TransportService(jausRouter);
		urn_jts_DiscoveryClient_1_0.DiscoveryClientService pDiscoveryClientService = new urn_jts_DiscoveryClient_1_0.DiscoveryClientService(jausRouter, pTransportService);


		/// Add all the Services for the Component
		serviceList.Add(pTransportService);
		serviceList.Add(pDiscoveryClientService);

	}

	~CSharpClient(){
		while(serviceList.Count != 0)
        {
			serviceList.RemoveAt(serviceList.Count-1);
		}

		jausRouter = null;

	}

	public void startComponent()
    {

		jausRouter.start();
		this.start();

		for(int i = 0; i <serviceList.Count; i++)
        {
			serviceList[i].start();
		}
	}

	public void shutdownComponent(){
		for (int i=0; i<serviceList.Count; i++)
        {
			serviceList[i].stop();
		}
		this.stop();
		jausRouter.stop();
	}
	
	public override void processInternalEvent(InternalEvent ie)
	{
		bool done = false;
	
		//
		// When a component receives an internal event, it passes it
		// to the services to handling, children services first.  If the
		// event is not processed by normal transitions, it's passed
		// again to the services (children first) for default transitions.
		// A given event may only be processed by at most one service.
		//
		for (int i = serviceList.Count; i>0; i--)
		{
			if (!done) done = serviceList[i-1].processTransitions(ie);
		}
		for (int i = serviceList.Count; i>0; i--)
		{
			if (!done) done = serviceList[i-1].defaultTransitions(ie);
		}
	}

}
