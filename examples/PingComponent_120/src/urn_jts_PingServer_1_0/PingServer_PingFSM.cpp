

#include "urn_jts_PingServer_1_0/PingServer_PingFSM.h"




using namespace JTS;

namespace urn_jts_PingServer_1_0
{



PingServer_PingFSM::PingServer_PingFSM()
{

	/*
	 * Context has to be constructed last so that all class variables are 
	 * available if an EntryAction of the InitialState of the statemachine 
	 * needs them. 
	 */
	context = new PingServer_PingFSMContext(*this);
}



PingServer_PingFSM::~PingServer_PingFSM() 
{
	delete context;
}

void PingServer_PingFSM::setupNotifications()
{

}

void PingServer_PingFSM::ReportHeartbeatPulseAction()
{
	// Send a ReportHeartbeatPulse message back to the local component.
	ReportHeartbeatPulse response;
	sendJausMessage(response, *jausRouter->getJausAddress());
}





};
