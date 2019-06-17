#include "urn_jaus_jss_iop_UnsolicitedHeartbeat_1_1/UnsolicitedHeartbeat_ReceiveFSM.h"

using namespace JTS;

namespace urn_jaus_jss_iop_UnsolicitedHeartbeat_1_1
{


static DeVivo::Junior::JrTimer* rhp_timer = NULL;
static void handleTimer(void* arg0)
{
    rhp_timer->stop();
    ((InternalEventHandler*) arg0)->invoke(new PeriodicTimerTrigger());
    rhp_timer->start();
}

UnsolicitedHeartbeat_ReceiveFSM::UnsolicitedHeartbeat_ReceiveFSM(urn_jaus_jss_core_Transport_1_1::Transport_ReceiveFSM* pTransport_ReceiveFSM, urn_jaus_jss_core_Events_1_1::Events_ReceiveFSM* pEvents_ReceiveFSM, urn_jaus_jss_core_Liveness_1_1::Liveness_ReceiveFSM* pLiveness_ReceiveFSM)
{

	/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
	 */
	context = new UnsolicitedHeartbeat_ReceiveFSMContext(*this);

	this->pTransport_ReceiveFSM = pTransport_ReceiveFSM;
	this->pEvents_ReceiveFSM = pEvents_ReceiveFSM;
	this->pLiveness_ReceiveFSM = pLiveness_ReceiveFSM;
}



UnsolicitedHeartbeat_ReceiveFSM::~UnsolicitedHeartbeat_ReceiveFSM() 
{
	delete context;
}

void UnsolicitedHeartbeat_ReceiveFSM::setupNotifications()
{
	pLiveness_ReceiveFSM->registerNotification("Receiving_Ready", ieHandler, "InternalStateChange_To_UnsolicitedHeartbeat_ReceiveFSM_Receiving_Ready", "Liveness_ReceiveFSM");
	pLiveness_ReceiveFSM->registerNotification("Receiving", ieHandler, "InternalStateChange_To_UnsolicitedHeartbeat_ReceiveFSM_Receiving_Ready", "Liveness_ReceiveFSM");
	registerNotification("Receiving_Ready", pLiveness_ReceiveFSM->getHandler(), "InternalStateChange_To_Liveness_ReceiveFSM_Receiving_Ready", "UnsolicitedHeartbeat_ReceiveFSM");
	registerNotification("Receiving", pLiveness_ReceiveFSM->getHandler(), "InternalStateChange_To_Liveness_ReceiveFSM_Receiving", "UnsolicitedHeartbeat_ReceiveFSM");

    // Create a timer to periodically broadcast an RHP
    rhp_timer = new DeVivo::Junior::JrTimer( handleTimer, ieHandler, 1000 );
    rhp_timer->start();
}

void UnsolicitedHeartbeat_ReceiveFSM::broadcastReportHeartBeatPulseAction()
{
    ReportHeartbeatPulse rhp;
    sendJausMessage( rhp, JausAddress(jausRouter->getJausAddress()->getSubsystemID(), 0xFF, 0xFF) );
}

bool UnsolicitedHeartbeat_ReceiveFSM::hasJAUS_ID()
{
    DeVivo::Junior::JAUS_ID myId(jausRouter->getJausAddress()->get());
    return !myId.containsWildcards();
}



};
