

#include "urn_jts_PingClient_1_0/PingClient_PingClientFSM.h"




using namespace JTS;

namespace urn_jts_PingClient_1_0
{



PingClient_PingClientFSM::PingClient_PingClientFSM()
{

	/*
	 * Context has to be constructed last so that all class variables are 
	 * available if an EntryAction of the InitialState of the statemachine 
	 * needs them. 
	 */
	context = new PingClient_PingClientFSMContext(*this);
}



PingClient_PingClientFSM::~PingClient_PingClientFSM() 
{
	delete context;
}

void PingClient_PingClientFSM::setupNotifications()
{

}

void PingClient_PingClientFSM::QueryHeartBeatPulseAction()
{
	// Send the QueryHeartbeat message to the local component
	QueryHeartbeatPulse query;
	sendJausMessage( query, *jausRouter->getJausAddress());
}

void PingClient_PingClientFSM::printToScreenAction()
{
	/// Insert User Code HERE
    printf("Hello world!\n");
}





};
