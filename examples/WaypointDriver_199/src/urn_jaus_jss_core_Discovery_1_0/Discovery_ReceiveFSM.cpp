

#include "urn_jaus_jss_core_Discovery_1_0/Discovery_ReceiveFSM.h"




using namespace JTS;

namespace urn_jaus_jss_core_Discovery_1_0
{

class ServiceLocationList
{
  public:
    class ServiceLocation
	{
	  public:
		ServiceLocation(JausAddress jaus_id, RegisterServices::RegisterServicesBody::ServiceList::ServiceRec service_id)
		{
			this->jaus_id = jaus_id;
			this->service_id = service_id;
		}

		RegisterServices::RegisterServicesBody::ServiceList::ServiceRec service_id;
		JausAddress jaus_id;
	};

	void add(JausAddress jaus_id, RegisterServices::RegisterServicesBody::ServiceList::ServiceRec service_id)
	{
		services.push_back(new ServiceLocation(jaus_id, service_id));
		printf("Adding services named: %s at %d:%d:%d\n", 
			service_id.getURI().c_str(), jaus_id.getSubsystemID(), 
			jaus_id.getNodeID(), jaus_id.getComponentID());
	}

	std::vector<ServiceLocation*>& getList()
	{
		return services;
	}

  protected:
	std::vector<ServiceLocation*> services;
};
static ServiceLocationList ListOfServices;


Discovery_ReceiveFSM::Discovery_ReceiveFSM(urn_jaus_jss_core_Transport_1_0::Transport_ReceiveFSM* pTransport_ReceiveFSM, urn_jaus_jss_core_Events_1_0::Events_ReceiveFSM* pEvents_ReceiveFSM)
{

	/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
	 */
	context = new Discovery_ReceiveFSMContext(*this);

	this->pTransport_ReceiveFSM = pTransport_ReceiveFSM;
	this->pEvents_ReceiveFSM = pEvents_ReceiveFSM;
}



Discovery_ReceiveFSM::~Discovery_ReceiveFSM() 
{
	delete context;
}

void Discovery_ReceiveFSM::setupNotifications()
{
	pEvents_ReceiveFSM->registerNotification("Receiving_Ready", ieHandler, "InternalStateChange_To_Discovery_ReceiveFSM_Receiving_Ready", "Events_ReceiveFSM");
	pEvents_ReceiveFSM->registerNotification("Receiving", ieHandler, "InternalStateChange_To_Discovery_ReceiveFSM_Receiving_Ready", "Events_ReceiveFSM");
	registerNotification("Receiving_Ready", pEvents_ReceiveFSM->getHandler(), "InternalStateChange_To_Events_ReceiveFSM_Receiving_Ready", "Discovery_ReceiveFSM");
	registerNotification("Receiving", pEvents_ReceiveFSM->getHandler(), "InternalStateChange_To_Events_ReceiveFSM_Receiving", "Discovery_ReceiveFSM");

}

void Discovery_ReceiveFSM::PublishServicesAction(RegisterServices msg, Receive::Body::ReceiveRec transportData)
{
	// Extract the sender information
	JausAddress sender(transportData.getSrcSubsystemID(),
					   transportData.getSrcNodeID(),
					   transportData.getSrcComponentID());

	/// Add the reporting components services to the current list
	for (int i=0; i<msg.getRegisterServicesBody()->getServiceList()->getNumberOfElements(); i++)
	    ListOfServices.add(sender, *msg.getRegisterServicesBody()->getServiceList()->getElement(i));
}

void Discovery_ReceiveFSM::SendAction(std::string arg0, Receive::Body::ReceiveRec transportData)
{
	// We can cheat a bit here.  Since we know this Discovery Service is part
	// of the Waypoint Driver component, we can pre-seed the service list with
	// those services so they don't have to register...
	static bool doOnce = false;
	if (!doOnce)
	{
		doOnce = true;
		RegisterServices::RegisterServicesBody::ServiceList::ServiceRec service;
		service.setMinorVersionNumber(0);
		service.setMajorVersionNumber(1);
		service.setURI("urn:jaus:jss:mobility:LocalPoseSensor");
		ListOfServices.add( *jausRouter->getJausAddress(), service);
		service.setURI("urn:jaus:jss:mobility:LocalWaypointListDriver");
		ListOfServices.add( *jausRouter->getJausAddress(), service);
	}

	// Extract the sender information
	JausAddress sender(transportData.getSrcSubsystemID(),
						transportData.getSrcNodeID(),
						transportData.getSrcComponentID());

	// Formulate the requested response
	if (arg0 == "ReportIdentification")
	{
		ReportIdentification report_msg;
		report_msg.getBody()->getReportIdentificationRec()->setQueryType(2); // report as subsystem
		report_msg.getBody()->getReportIdentificationRec()->setType(10001); // report as vehicle
		report_msg.getBody()->getReportIdentificationRec()->setIdentification("SimulatedVehicle"); // report as vehicle
		sendJausMessage( report_msg, sender );
	}
	else if (arg0 == "ReportServices")
	{
		ReportServices report_msg;
		for (int i=0; i < ListOfServices.getList().size(); i++)
		{
			ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq comp;
			comp.getComponentRec()->setComponentID(ListOfServices.getList()[i]->jaus_id.getComponentID());
			comp.getServiceList()->addElement(
				*((ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec*) &ListOfServices.getList()[i]->service_id));
			ReportServices::Body::NodeList::NodeSeq node;
			node.getNodeRec()->setNodeID(ListOfServices.getList()[i]->jaus_id.getNodeID());
			node.getComponentList()->addElement(comp);
			report_msg.getBody()->getNodeList()->addElement(node);
		}
		sendJausMessage( report_msg, sender );
	}
}





};
