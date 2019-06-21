#include "urn_jaus_jss_iop_NodeIDAllocator_1_1/NodeIDAllocator_ReceiveFSM.h"
#include <sstream>
#include <iomanip>

using namespace JTS;

static std::map< std::string, unsigned char > NodeIDMap;
static unsigned char nid_count = 0xB0; // per IOP, dynamically assigned start at 0xB0?

namespace urn_jaus_jss_iop_NodeIDAllocator_1_1
{



NodeIDAllocator_ReceiveFSM::NodeIDAllocator_ReceiveFSM(urn_jaus_jss_core_Transport_1_1::Transport_ReceiveFSM* pTransport_ReceiveFSM)
{

    /*
     * If there are other variables, context must be constructed last so that all 
     * class variables are available if an EntryAction of the InitialState of the 
     * statemachine needs them. 
     */
    context = new NodeIDAllocator_ReceiveFSMContext(*this);

    this->pTransport_ReceiveFSM = pTransport_ReceiveFSM;
}



NodeIDAllocator_ReceiveFSM::~NodeIDAllocator_ReceiveFSM() 
{
    delete context;
}

void NodeIDAllocator_ReceiveFSM::setupNotifications()
{
    pTransport_ReceiveFSM->registerNotification("Receiving", ieHandler, "InternalStateChange_To_NodeIDAllocator_ReceiveFSM_Receiving", "Transport_ReceiveFSM");
    registerNotification("Receiving", pTransport_ReceiveFSM->getHandler(), "InternalStateChange_To_Transport_ReceiveFSM_Receiving", "NodeIDAllocator_ReceiveFSM");

    printf("Node ID Allocator running...\n");
}

void NodeIDAllocator_ReceiveFSM::sendGrantNodeIDAction(RequestNodeID msg)
{
    // Convert MAC address to string
    std::stringstream ss;
    for (int i=0; i<7; i++)
    {
        ss << std::hex << std::setfill('0') << std::setw(2) << (int) msg.getBody()->getReviewNodeIDRec()->getRequesterID()->getRequesterIDArrayField(i);
        if (i<6) ss << ":";
    }

    // Check for existence in the map
    if (NodeIDMap.count( ss.str() ) == 0)
    {
        printf("Adding NodeID %d for %s\n", nid_count, ss.str().c_str());
        NodeIDMap[ ss.str() ] = nid_count++;
    }
    else
    {
        printf("Request for node ID from known MAC (%s->%d)\n", ss.str().c_str(), NodeIDMap[ss.str()]);
    }

    // Broadcast the response to our local platform (this could be zero 
    // if the subsystem ID is not yet known)
    GrantNodeID response;
    for (int i=0; i<7; i++)
        response.getBody()->getGrantNodeIDRec()->getRequesterID()->setRequesterIDArrayField(i,
            msg.getBody()->getReviewNodeIDRec()->getRequesterID()->getRequesterIDArrayField(i) );
    response.getBody()->getGrantNodeIDRec()->setNodeID( NodeIDMap[ ss.str() ] );
    sendJausMessage( response, JausAddress(jausRouter->getJausAddress()->getSubsystemID(), 0xFF, 0xFF) );
}

};
