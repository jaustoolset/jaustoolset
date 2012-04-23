

using JTS;
using System;
using System.Collections.Generic;


public class Management : EventReceiver {
	protected List<Service> serviceList;
	JausRouter jausRouter;

	public Management(ushort subsystem, byte node, byte component)
    {
		jausRouter = new JausRouter(new JausAddress(subsystem, node, component), ieHandler);
		serviceList = new List<Service>();
		
		/// Instantiate services
		urn_jaus_jss_core_Transport_1_1.TransportService pTransportService = new urn_jaus_jss_core_Transport_1_1.TransportService(jausRouter);
		urn_jaus_jss_core_Events_1_1.EventsService pEventsService = new urn_jaus_jss_core_Events_1_1.EventsService(jausRouter, pTransportService);
		urn_jaus_jss_core_AccessControl_1_1.AccessControlService pAccessControlService = new urn_jaus_jss_core_AccessControl_1_1.AccessControlService(jausRouter, pTransportService, pEventsService);
		urn_jaus_jss_core_Management_1_1.ManagementService pManagementService = new urn_jaus_jss_core_Management_1_1.ManagementService(jausRouter, pTransportService, pEventsService, pAccessControlService);


		/// Add all the Services for the Component
		serviceList.Add(pTransportService);
		serviceList.Add(pEventsService);
		serviceList.Add(pAccessControlService);
	serviceList.Add(pManagementService);

	}

	~Management(){
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
