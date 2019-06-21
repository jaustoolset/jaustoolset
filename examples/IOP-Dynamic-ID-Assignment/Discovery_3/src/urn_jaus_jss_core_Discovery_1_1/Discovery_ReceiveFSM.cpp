#include "urn_jaus_jss_core_Discovery_1_1/Discovery_ReceiveFSM.h"

using namespace JTS;

namespace urn_jaus_jss_core_Discovery_1_1
{

// Nested maps for subsystem->nodes->components->services
typedef RegisterServices::RegisterServicesBody::ServiceList::ServiceRec ServiceID;
typedef std::list<ServiceID> ServiceIdList;
typedef std::list<ServiceID>::iterator ServiceIdListIterator;
typedef std::map<unsigned char, ServiceIdList> ComponentMap;
typedef std::map<unsigned char, ServiceIdList>::iterator ComponentMapIter;
typedef std::map<unsigned char, ComponentMap> NodeMap;
typedef std::map<unsigned char, ComponentMap>::iterator NodeMapIter;
typedef std::map<unsigned short, NodeMap> SubsystemMap;
typedef std::map<unsigned short, NodeMap>::iterator SubsystemMapIter;
static SubsystemMap service_map;

Discovery_ReceiveFSM::Discovery_ReceiveFSM(urn_jaus_jss_core_Transport_1_1::Transport_ReceiveFSM* pTransport_ReceiveFSM, urn_jaus_jss_core_Events_1_1::Events_ReceiveFSM* pEvents_ReceiveFSM)
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
    unsigned short ssid = transportData.getSourceID()->getSubsystemID();
    unsigned char nid = transportData.getSourceID()->getNodeID();
    unsigned char cid = transportData.getSourceID()->getComponentID();
    JausAddress sender(ssid, nid, cid);

    /// Add the reporting components services to the current list, but only if its unique
    for (int i=0; i<msg.getRegisterServicesBody()->getServiceList()->getNumberOfElements(); i++)
    {
        ServiceIdListIterator siter = service_map[ssid][nid][cid].begin();
        for (siter; siter != service_map[ssid][nid][cid].end(); siter++)
        {
            if (*msg.getRegisterServicesBody()->getServiceList()->getElement(i) == *siter) break;
        }
        if (siter == service_map[ssid][nid][cid].end())
        {
            service_map[ssid][nid][cid].push_back( *msg.getRegisterServicesBody()->getServiceList()->getElement(i) );
            printf("Adding %s to %d:%d:%d\n", msg.getRegisterServicesBody()->getServiceList()->getElement(i)->getURI().c_str(),
                ssid, nid, cid);
        }
    }
}

void Discovery_ReceiveFSM::SendAction(QueryIdentification query, Receive::Body::ReceiveRec transportData)
{
    // Extract the sender information
    JausAddress sender(transportData.getSourceID()->getSubsystemID(),
                        transportData.getSourceID()->getNodeID(),
                        transportData.getSourceID()->getComponentID());

    // Formulate the requested response, depend on what's requested.
    ReportIdentification report_msg;
    report_msg.getBody()->getReportIdentificationRec()->setQueryType(query.getBody()->getQueryIdentificationRec()->getQueryType());
    report_msg.getBody()->getReportIdentificationRec()->setType(10001); // report as vehicle
    report_msg.getBody()->getReportIdentificationRec()->setIdentification("Example"); // report as vehicle
    
    // Finally, send message
    sendJausMessage( report_msg, sender );
}

void Discovery_ReceiveFSM::SendAction(QueryConfiguration query, Receive::Body::ReceiveRec transportData)
{
    // Extract the sender information
    JausAddress sender(transportData.getSourceID()->getSubsystemID(),
                        transportData.getSourceID()->getNodeID(),
                        transportData.getSourceID()->getComponentID());

    // Formulate the requested response
    ReportConfiguration config_msg;
    for (SubsystemMapIter ssiter = service_map.begin(); ssiter != service_map.end(); ssiter++)
    {
        // Only report services hosted on the local platform.
        if (ssiter->first != jausRouter->getJausAddress()->getSubsystemID()) continue;

        for (NodeMapIter niter = ssiter->second.begin(); niter != ssiter->second.end(); niter++)
        {
            ReportConfiguration::Body::NodeList::NodeSeq nodeSeq;
            for (ComponentMapIter citer = niter->second.begin(); citer != niter->second.end(); citer++)
            {
                ReportConfiguration::Body::NodeList::NodeSeq::ComponentList::ComponentRec rec;
                rec.setComponentID(citer->first);
                rec.setInstanceID(0);
                nodeSeq.getComponentList()->addElement( rec );
            }
            nodeSeq.getNodeRec()->setNodeID(niter->first);
            config_msg.getBody()->getNodeList()->addElement(nodeSeq);    
        }
    }
    sendJausMessage( config_msg, sender );
}

void Discovery_ReceiveFSM::SendAction(QuerySubsystemList query, Receive::Body::ReceiveRec transportData)
{
    // Extract the sender information
    JausAddress sender(transportData.getSourceID()->getSubsystemID(),
                        transportData.getSourceID()->getNodeID(),
                        transportData.getSourceID()->getComponentID());

    // Formulate the requested response
    ReportSubsystemList subsystem_msg;
    ReportSubsystemList::Body::SubsystemList::SubsystemRec rec;
    rec.setSubsystemID( jausRouter->getJausAddress()->getSubsystemID() );
    rec.setNodeID( jausRouter->getJausAddress()->getNodeID() );
    rec.setComponentID( jausRouter->getJausAddress()->getComponentID() );
    subsystem_msg.getBody()->getSubsystemList()->addElement( rec );
    sendJausMessage( subsystem_msg, sender );
}

void Discovery_ReceiveFSM::SendAction(QueryServices query, Receive::Body::ReceiveRec transportData)
{
    // Extract the sender information
    JausAddress sender(transportData.getSourceID()->getSubsystemID(),
                        transportData.getSourceID()->getNodeID(),
                        transportData.getSourceID()->getComponentID());

    // Formulate the requested response
    ReportServices report_msg;

    // Loop through all subsystems...
    for (SubsystemMapIter ssiter = service_map.begin(); ssiter != service_map.end(); ssiter++)
    {
        // Only report services hosted on the local platform.
        if (ssiter->first != jausRouter->getJausAddress()->getSubsystemID()) continue;

        // Loop through all nodes...
        for (NodeMapIter niter = ssiter->second.begin(); niter != ssiter->second.end(); niter++)
        {
            // Make sure this node matches one given in the query message
            for (int i=0; i < query.getBody()->getNodeList()->getNumberOfElements(); i++)
            {
                QueryServices::Body::NodeList::NodeSeq* node = query.getBody()->getNodeList()->getElement(i);
                if ((node->getNodeRec()->getNodeID() == 255) || (node->getNodeRec()->getNodeID() == niter->first))
                {
                    // Loop through all components, since this node was selected
                    ReportServices::Body::NodeList::NodeSeq nodeSeq;
                    for (ComponentMapIter citer = niter->second.begin(); citer != niter->second.end(); citer++)
                    {
                        // Make sure this component matches one given in the query message
                        for (int j=0; j < node->getComponentList()->getNumberOfElements(); j++)
                        {
                            QueryServices::Body::NodeList::NodeSeq::ComponentList::ComponentRec* comp = node->getComponentList()->getElement(j);
                            if ((comp->getComponentID() == 255) || (comp->getComponentID() == citer->first))
                            {
                                // Add all the services, then insert into node list, since this node was selected
                                ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq compSeq;
                                for (ServiceIdListIterator siter = citer->second.begin(); siter != citer->second.end(); siter++)
                                {
                                    compSeq.getServiceList()->addElement( 
                                        *((ReportServices::Body::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec*)(&*siter)) );
                                }
                                compSeq.getComponentRec()->setComponentID(citer->first);
                                compSeq.getComponentRec()->setInstanceID(0);
                                nodeSeq.getComponentList()->addElement( compSeq );
                            }
                        }

                        // insert the node list into the message
                        nodeSeq.getNodeRec()->setNodeID(niter->first);
                        report_msg.getBody()->getNodeList()->addElement(nodeSeq);  
                    }
                }
            }
        }
    }

    // Finally, send the populated message
    sendJausMessage( report_msg, sender );
}

void Discovery_ReceiveFSM::SendAction(QueryServiceList query, Receive::Body::ReceiveRec transportData)
{
    // Extract the sender information
    JausAddress sender(transportData.getSourceID()->getSubsystemID(),
                        transportData.getSourceID()->getNodeID(),
                        transportData.getSourceID()->getComponentID());

    // Formulate the requested response
    ReportServiceList report_list_msg;

    // Loop through all subsystems...
    for (SubsystemMapIter ssiter = service_map.begin(); ssiter != service_map.end(); ssiter++)
    {
        // Loop through all nodes...
        ReportServiceList::Body::SubsystemList::SubsystemSeq subsystemSeq;
        for (NodeMapIter niter = ssiter->second.begin(); niter != ssiter->second.end(); niter++)
        {
            // Loop through all components
            ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq nodeSeq;
            for (ComponentMapIter citer = niter->second.begin(); citer != niter->second.end(); citer++)
            {
                // For each component, add all the services, then insert into node list.
                ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq compSeq;
                for (ServiceIdListIterator siter = citer->second.begin(); siter != citer->second.end(); siter++)
                {
                    compSeq.getServiceList()->addElement( 
                        *((ReportServiceList::Body::SubsystemList::SubsystemSeq::NodeList::NodeSeq::ComponentList::ComponentSeq::ServiceList::ServiceRec*)(&*siter)) );
                }
                compSeq.getComponentRec()->setComponentID(citer->first);
                compSeq.getComponentRec()->setInstanceID(0);
                nodeSeq.getComponentList()->addElement( compSeq );
            }
            // insert the node list into subsystem seq
            nodeSeq.getNodeRec()->setNodeID(niter->first);
            subsystemSeq.getNodeList()->addElement(nodeSeq);    
        }

        // insert the subsystem list into the message
        subsystemSeq.getSubsystemRec()->setSubsystemID(ssiter->first);
        report_list_msg.getBody()->getSubsystemList()->addElement( subsystemSeq );
    }

    // Finally, send the populated message
    sendJausMessage( report_list_msg, sender );
}



};
