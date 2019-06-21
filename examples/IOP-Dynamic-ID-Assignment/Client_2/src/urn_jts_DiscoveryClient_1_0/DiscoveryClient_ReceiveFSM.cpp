#include "urn_jts_DiscoveryClient_1_0/DiscoveryClient_ReceiveFSM.h"
#include <iomanip>

using namespace JTS;

namespace urn_jts_DiscoveryClient_1_0
{

static DeVivo::Junior::JrTimer* register_timer = NULL;
static DeVivo::Junior::JrTimer* node_timer = NULL;
static int dynamic_iop_tries_so_far = 0;
const int MaxNumberOfDynamicTries = 60; // increased from 15
const int AllocatorRate = 2000; // in milliseconds
const int RegistrationRate = 5000; // in milliseconds
const std::string mac_string = "de:ad:be:ef:ca:fe:01";

static void handleIDTimeout(void* arg0)
{
   register_timer->stop();
   ((DiscoveryClient_ReceiveFSM*) arg0)->sendQueryID();
    register_timer->start();
}

static void handleAllocatorTimeout(void* arg0)
{
    node_timer->stop();
    ((DiscoveryClient_ReceiveFSM*) arg0)->sendAllocatorRequest();
    node_timer->start();
}

DiscoveryClient_ReceiveFSM::DiscoveryClient_ReceiveFSM(urn_jaus_jss_core_Transport_1_1::Transport_ReceiveFSM* pTransport_ReceiveFSM)
{

   /*
    * If there are other variables, context must be constructed last so that all 
    * class variables are available if an EntryAction of the InitialState of the 
    * statemachine needs them. 
    */
   context = new DiscoveryClient_ReceiveFSMContext(*this);
   this->pTransport_ReceiveFSM = pTransport_ReceiveFSM;
}

DiscoveryClient_ReceiveFSM::~DiscoveryClient_ReceiveFSM() 
{
   delete context;
}

void DiscoveryClient_ReceiveFSM::sendAllocatorRequest()
{
	// We first have to know the subsystem ID before we can start asking for a node ID
	// If that's not set, return prematurely.
	if ((jausRouter->getJausAddress()->getSubsystemID() == 0xFFFF) ||
	    (jausRouter->getJausAddress()->getSubsystemID() == 0))
	{
		return;
	}

    // Check to see if we have an ID.  If we do, there's nothing to do
	DeVivo::Junior::JAUS_ID myId(jausRouter->getJausAddress()->get());
    if (!myId.containsWildcards())
    {
        // Nothing to do here... we just fall through the rest of the function

    }
    else if (dynamic_iop_tries_so_far++ > MaxNumberOfDynamicTries)
    {
        // If we've exceeded the max number of tries, use the fallback
        printf("Failed to obtain Node ID dynamically.  Using fallback to assign node ID to default offset...\n");
        JausAddress id( jausRouter->getJausAddress()->getSubsystemID(),
                        0x6F, // type + active offset
                        jausRouter->getJausAddress()->getComponentID() );            
        jausRouter->updateJausID( id );
    }
    else
    {
        // Broadcast a RequestNodeID message. Note that an actual implementation would have to get or know the MAC
        printf("Sending RequestNodeID message since ID=%d...\n", jausRouter->getJausAddress()->getNodeID());
        RequestNodeID request_id;
        request_id.getBody()->getReviewNodeIDRec()->getRequesterID()->setRequesterIDArrayField(6, 0x01);
		request_id.getBody()->getReviewNodeIDRec()->getRequesterID()->setRequesterIDArrayField(0, 0xDE);
		request_id.getBody()->getReviewNodeIDRec()->getRequesterID()->setRequesterIDArrayField(1, 0xAD);
		request_id.getBody()->getReviewNodeIDRec()->getRequesterID()->setRequesterIDArrayField(2, 0xBE);
		request_id.getBody()->getReviewNodeIDRec()->getRequesterID()->setRequesterIDArrayField(3, 0xEF);
		request_id.getBody()->getReviewNodeIDRec()->getRequesterID()->setRequesterIDArrayField(4, 0xCA);
		request_id.getBody()->getReviewNodeIDRec()->getRequesterID()->setRequesterIDArrayField(5, 0xFE);
		JausAddress localBroadcast( jausRouter->getJausAddress()->getSubsystemID(), 0xFF, 0xFF);

		// Use the optional 'force' flag to make the transport layer send this message,
		// even if the local JAUS ID may not be fully known.
        sendJausMessage( request_id, localBroadcast, true);
    }
}

void DiscoveryClient_ReceiveFSM::sendQueryID()
{
    // If we know our ID fully, start the dynamic service discovery process
	DeVivo::Junior::JAUS_ID myId(jausRouter->getJausAddress()->get());
    if (!myId.containsWildcards())
	{
		QueryIdentification query_msg;
		query_msg.getBody()->getQueryIdentificationRec()->setQueryType(2);
		JausAddress localBroadcast( jausRouter->getJausAddress()->getSubsystemID(), 0xFF, 0xFF);
		sendJausMessage( query_msg, localBroadcast);
	}
}


void DiscoveryClient_ReceiveFSM::setupNotifications()
{
   pTransport_ReceiveFSM->registerNotification("Receiving", ieHandler, "InternalStateChange_To_DiscoveryClient_ReceiveFSM_Receiving_Ready", "Transport_ReceiveFSM");
   registerNotification("Receiving_Ready", pTransport_ReceiveFSM->getHandler(), "InternalStateChange_To_Transport_ReceiveFSM_Receiving", "DiscoveryClient_ReceiveFSM");
   registerNotification("Receiving", pTransport_ReceiveFSM->getHandler(), "InternalStateChange_To_Transport_ReceiveFSM_Receiving", "DiscoveryClient_ReceiveFSM");

    // Set-up timer to periodically poll for discovery service
    if (register_timer == NULL)
    {
        register_timer = new DeVivo::Junior::JrTimer( handleIDTimeout, this, RegistrationRate );
        handleIDTimeout( this );
    }

    // Set-up a timer to deal with Node ID assignment
    // Set-up a periodic timer to broadacast an allocator request
    if (node_timer == NULL)
    {
        node_timer = new DeVivo::Junior::JrTimer( handleAllocatorTimeout, this, AllocatorRate );
        handleAllocatorTimeout( this );
    }
}

void DiscoveryClient_ReceiveFSM::registerServicesAction(Receive::Body::ReceiveRec transportData)
{
   // Extract the sender information
   JausAddress sender(transportData.getSourceID()->getSubsystemID(),
                        transportData.getSourceID()->getNodeID(),
                        transportData.getSourceID()->getComponentID());

   // Found a discovery service.  Register our stuffs with it.
   RegisterServices register_msg;
   RegisterServices::RegisterServicesBody::ServiceList::ServiceRec service;
   service.setMinorVersionNumber(1);
   service.setMajorVersionNumber(1);

   // Register local services
	service.setURI("urn:jaus:jss:core:Transport");
	register_msg.getRegisterServicesBody()->getServiceList()->addElement(service);

   // Send the actual message
   sendJausMessage(register_msg, sender);   
}

void DiscoveryClient_ReceiveFSM::handleMessageAction(ReportIdentification msg, Receive::Body::ReceiveRec transportData)
{
    // Since we found a discovery service, register our local services
	registerServicesAction(transportData);
}

void DiscoveryClient_ReceiveFSM::updateNodeIDAction(GrantNodeID msg, Receive::Body::ReceiveRec transportData)
{
    // Convert incoming MAC to string
    std::stringstream ss;
    for (int i=0; i<7; i++) 
    {
        ss << std::hex << std::setfill('0') << std::setw(2) << (int) msg.getBody()->getGrantNodeIDRec()->getRequesterID()->getRequesterIDArrayField(i);
        if (i<6) ss << ":";
    }
    printf("Got GrantNodeID message for %s\n", ss.str().c_str());

    // Check the MAC address to see if this is for us.
    if (ss.str() == mac_string)
    {
        printf("Updating NodeID to %d for MAC:%s\n", msg.getBody()->getGrantNodeIDRec()->getNodeID(), ss.str().c_str());

        JausAddress id( jausRouter->getJausAddress()->getSubsystemID(),
                        msg.getBody()->getGrantNodeIDRec()->getNodeID(),
                        jausRouter->getJausAddress()->getComponentID() );
        jausRouter->updateJausID( id );

        // Immediately start looking for the Discovery Service
        sendQueryID();
    } else printf("Ignoring grant message since %s != %s\n", ss.str().c_str(), mac_string.c_str());
}

void DiscoveryClient_ReceiveFSM::updateSubsystemIDAction(Receive::Body::ReceiveRec transportData)
{
    // Check to see if we already have a subsystem ID.  If so, nothing to do.
   if ((jausRouter->getJausAddress()->getSubsystemID() != 0xFFFF) && (jausRouter->getJausAddress()->getSubsystemID() != 0)) return;

   // We need to update the JAUS ID 
   printf("DiscoveryClient:: update subsystem ID to %d\n", transportData.getSourceID()->getSubsystemID());
   JausAddress id( transportData.getSourceID()->getSubsystemID(),
                   jausRouter->getJausAddress()->getNodeID(),
                   jausRouter->getJausAddress()->getComponentID() );

   // We now know (and can set) the subsystem ID, but we might still not know the Node ID.
   // Force the lower layers to accept that the ID might still contain wildcards using the
   // optional boolean argument
   jausRouter->updateJausID( id, true );
}

void DiscoveryClient_ReceiveFSM::parseServiceListAction(ReportServices msg, Receive::Body::ReceiveRec transportData)
{
    // This function is unneeded in the current configuration.
}

void DiscoveryClient_ReceiveFSM::handleTimeoutAction()
{
    // This function is unneeded in the current configuration.
}

bool DiscoveryClient_ReceiveFSM::fromMasterModule(Receive::Body::ReceiveRec transportData)
{
    // Typically, AEODRS systems use node 100 for master (discovery) while IOP use 1
    bool fromMas = (transportData.getSourceID()->getNodeID()==1) || (transportData.getSourceID()->getNodeID()==100);
   return fromMas;
}


};
