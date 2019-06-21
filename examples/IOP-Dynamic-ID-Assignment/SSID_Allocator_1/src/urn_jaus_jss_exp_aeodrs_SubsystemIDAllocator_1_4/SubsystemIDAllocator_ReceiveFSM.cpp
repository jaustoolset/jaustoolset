#include <map>
#include <sstream>
#include <iomanip>

#include "urn_jaus_jss_exp_aeodrs_SubsystemIDAllocator_1_4/SubsystemIDAllocator_ReceiveFSM.h"

using namespace JTS;

typedef struct  {
  unsigned char MAC[6];
  unsigned short type; 
  unsigned short ssid;
} SubsystemInfo;

static std::map< std::string, SubsystemInfo > SubsystemIDMap;
static std::map< std::string, SubsystemInfo >::iterator SubsystemIDMapIter;
static unsigned short ssid_count = 100;

namespace urn_jaus_jss_exp_aeodrs_SubsystemIDAllocator_1_4
{



SubsystemIDAllocator_ReceiveFSM::SubsystemIDAllocator_ReceiveFSM(urn_jaus_jss_core_Transport_1_1::Transport_ReceiveFSM* pTransport_ReceiveFSM)
{

    /*
     * If there are other variables, context must be constructed last so that all 
     * class variables are available if an EntryAction of the InitialState of the 
     * statemachine needs them. 
     */
    context = new SubsystemIDAllocator_ReceiveFSMContext(*this);

    this->pTransport_ReceiveFSM = pTransport_ReceiveFSM;
}

SubsystemIDAllocator_ReceiveFSM::~SubsystemIDAllocator_ReceiveFSM() 
{
    delete context;
}

void SubsystemIDAllocator_ReceiveFSM::setupNotifications()
{
    pTransport_ReceiveFSM->registerNotification("Receiving", ieHandler, "InternalStateChange_To_SubsystemIDAllocator_ReceiveFSM_Receiving", "Transport_ReceiveFSM");
    registerNotification("Receiving", pTransport_ReceiveFSM->getHandler(), "InternalStateChange_To_Transport_ReceiveFSM_Receiving", "SubsystemIDAllocator_ReceiveFSM");

    printf("Subsystem ID Allocator running...\n");
}

void SubsystemIDAllocator_ReceiveFSM::sendGrantSubsystemIDAction(RequestSubsystemID msg, Receive::Body::ReceiveRec transportData)
{
    // Convert MAC address to string
    std::stringstream ss;
    for (int i=0; i<6; i++)
    {
        ss << std::hex << std::setfill('0') << std::setw(2) << (int) msg.getBody()->getRequestSubsystemIDRec()->getMACaddr()->getMac(i);
        if (i<5) ss << ":";
    }

    // Check for existence in the map
    if (SubsystemIDMap.count( ss.str() ) == 0)
    {
        printf("Adding SubsystemID %d for %s\n", ssid_count, ss.str().c_str());
        SubsystemInfo info;
        info.type = msg.getBody()->getRequestSubsystemIDRec()->getType();
        info.ssid = ssid_count++;
        for (int i=0; i<6; i++)
            info.MAC[i] = msg.getBody()->getRequestSubsystemIDRec()->getMACaddr()->getMac(i);
        SubsystemIDMap[ ss.str() ] = info;
    }
    else
    {
        printf("Request for subsystem ID from known MAC (%s->%d)\n", ss.str().c_str(), SubsystemIDMap[ss.str()].ssid);
    }

    // Broadcast the response
    GrantSubsystemID response;
    for (int i=0; i<6; i++)
        response.getBody()->getSubsystemRec()->getMACaddr()->setMac(i, SubsystemIDMap[ ss.str() ].MAC[i] );
    response.getBody()->getSubsystemRec()->setSubsystemID( SubsystemIDMap[ ss.str() ].ssid );
    response.getBody()->getSubsystemRec()->setType( SubsystemIDMap[ ss.str() ].type );
    sendJausMessage( response, JausAddress(0xFFFF, 0xFF, 0xFF) );
}

void SubsystemIDAllocator_ReceiveFSM::sendReportSubsystemIDsAction(QuerySubsystemIDs msg, Receive::Body::ReceiveRec transportData)
{
   // Extract the sender information
   JausAddress sender( transportData.getSourceID()->getSubsystemID(), 
                  transportData.getSourceID()->getNodeID(), 
                  transportData.getSourceID()->getComponentID());

   // Convert MAC address in the query to a string
    std::stringstream ss;
    for (int i=0; i<6; i++)
    {
        ss << std::hex << std::setfill('0') << std::setw(2) << (int) msg.getBody()->getQuerySubsystemIDsRec()->getMACaddr()->getMac(i);
        if (i<5) ss << ":";
    }
    std::string MAC = ss.str();

   // Populate a response
    ReportSubsystemIDs report_msg;
    unsigned short type = msg.getBody()->getQuerySubsystemIDsRec()->getSubsystemIDType();
    report_msg.getBody()->getReportSubsystemIDsSeq()->getSubsystemIDTypeRec()->setSubsystemIDType(type);

    // Loop through everything in the map
    for (SubsystemIDMapIter = SubsystemIDMap.begin(); SubsystemIDMapIter != SubsystemIDMap.end(); ++SubsystemIDMapIter)
    {
        // The query message contains search parameters, so only send back matches
        if ((type == 0) || // type is everything
            ((type == 1) && (SubsystemIDMapIter->second.type == 20001)) || // type is OCU AND we have a match
            ((type == 2) && (SubsystemIDMapIter->second.type == 10001)) || // type is vehicle AND we have a match
            ((type == 3) && (SubsystemIDMapIter->first == MAC))) // type is MAC AND we have a match (based on string compare)
        {
            ReportSubsystemIDs::Body::ReportSubsystemIDsSeq::SubsystemIDsList::SubsystemIDRec rec;
            rec.setSubsystemID( SubsystemIDMapIter->second.ssid );
            rec.setSubsystemType( SubsystemIDMapIter->second.type );
            for (int i=0; i<6; i++) rec.getMACaddr()->setMac(i, SubsystemIDMapIter->second.MAC[i]);
            report_msg.getBody()->getReportSubsystemIDsSeq()->getSubsystemIDsList()->addElement(rec);
        }
    }

    // And finally, send the message
    sendJausMessage( report_msg, sender );
}


};
