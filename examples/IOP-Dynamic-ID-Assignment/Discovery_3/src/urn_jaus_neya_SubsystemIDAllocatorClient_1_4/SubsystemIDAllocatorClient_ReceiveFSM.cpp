#include "urn_jaus_neya_SubsystemIDAllocatorClient_1_4/SubsystemIDAllocatorClient_ReceiveFSM.h"
#include <cstdlib>
#include <sstream>
#include <iomanip>

using namespace JTS;

namespace urn_jaus_neya_SubsystemIDAllocatorClient_1_4
{

static DeVivo::Junior::JrTimer* timer = NULL;
static unsigned char mac[6];
static std::string mac_string;

static void handlePeriodicTimer(void* arg0)
{
    timer->stop();
    ((SubsystemIDAllocatorClient_ReceiveFSM*) arg0)->sendRequestSubsystemIDAction();
    timer->start();
}

SubsystemIDAllocatorClient_ReceiveFSM::SubsystemIDAllocatorClient_ReceiveFSM(urn_jaus_jss_core_Transport_1_1::Transport_ReceiveFSM* pTransport_ReceiveFSM)
{

	/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
	 */
	context = new SubsystemIDAllocatorClient_ReceiveFSMContext(*this);

	this->pTransport_ReceiveFSM = pTransport_ReceiveFSM;
}



SubsystemIDAllocatorClient_ReceiveFSM::~SubsystemIDAllocatorClient_ReceiveFSM() 
{
	delete context;
}

void SubsystemIDAllocatorClient_ReceiveFSM::setupNotifications()
{
	pTransport_ReceiveFSM->registerNotification("Receiving", ieHandler, "InternalStateChange_To_SubsystemIDAllocatorClient_ReceiveFSM_Receiving", "Transport_ReceiveFSM");
	registerNotification("Receiving", pTransport_ReceiveFSM->getHandler(), "InternalStateChange_To_Transport_ReceiveFSM_Receiving", "SubsystemIDAllocatorClient_ReceiveFSM");

}

void SubsystemIDAllocatorClient_ReceiveFSM::handleGrantSubsystemIDAction(GrantSubsystemID msg)
{
    // Convert incoming MAC to string
    std::stringstream ss;
    for (int i=0; i<6; i++) 
    {
        ss << std::hex << std::setfill('0') << std::setw(2) << (int) msg.getBody()->getSubsystemRec()->getMACaddr()->getMac(i);
        if (i<5) ss << ":";
    }
    printf("Got GrantSubsytemID message from %s\n", ss.str().c_str());

    // Check the MAC address to see if this is for us.
    if (ss.str() == mac_string)
    {
        printf("Updating SubsystemID to %d for MAC:%s\n", msg.getBody()->getSubsystemRec()->getSubsystemID(), ss.str().c_str());

        JausAddress id( msg.getBody()->getSubsystemRec()->getSubsystemID(),
                        jausRouter->getJausAddress()->getNodeID(),
                        jausRouter->getJausAddress()->getComponentID() );
        jausRouter->updateJausID( id );
    }
}

void SubsystemIDAllocatorClient_ReceiveFSM::startPeriodicTimerAction()
{
    // If we have a valid subsystem ID, we don't need the timer
    if ((jausRouter->getJausAddress()->getSubsystemID() != 0xFFFF) &&
        (jausRouter->getJausAddress()->getSubsystemID() != 0))
        return;

    // Randomize a MAC address
    std::stringstream ss;
    srand( DeVivo::Junior::JrGetTimestamp() );
    for (int i=0; i<6; i++) 
    {
        mac[i] = rand() % 254 + 1; // distribution 1-254
        ss << std::hex << std::setfill('0') << std::setw(2) << (int) mac[i];
        if (i<5) ss << ":";
    }
    mac_string = ss.str();
    printf("Generated random MAC address: %s\n", mac_string.c_str());

    // Set-up a periodic timer to broadacast an allocator request
    timer = new DeVivo::Junior::JrTimer( handlePeriodicTimer, this, 1000 );
    handlePeriodicTimer( this );
}

void SubsystemIDAllocatorClient_ReceiveFSM::sendRequestSubsystemIDAction()
{
    // If we have received an ID, we can stop the timer
    if ((jausRouter->getJausAddress()->getSubsystemID() != 0xFFFF) &&
        (jausRouter->getJausAddress()->getSubsystemID() != 0))
        return;

    // Broadcast a RequestSubsystemID message
    printf("Sending RequestSubsystemID message...\n");
    RequestSubsystemID request_id;
    for (int i=0; i<6; i++) request_id.getBody()->getRequestSubsystemIDRec()->getMACaddr()->setMac(i, mac[i]);
    request_id.getBody()->getRequestSubsystemIDRec()->setType( 10001 );

    // Normally, the transport layers block messages from being sent when the 
    // local JAUS ID is not complete (contains wildcards).  However, in this case, 
    // we already know the JAUS ID is not complete as we're trying to get a subsystem
    // ID assigned to us.  Therefore, 'force' the send using the optional argument to
    // make sure the message goes out.
    sendJausMessage( request_id, JausAddress(0xFFFF, 0xFF, 0xFF), true);
}





};
