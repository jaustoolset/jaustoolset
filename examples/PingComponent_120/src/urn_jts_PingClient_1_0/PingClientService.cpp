

#include "urn_jts_PingClient_1_0/PingClientService.h"

using namespace JTS;

namespace urn_jts_PingClient_1_0
{
	
PingClientService::PingClientService(JTS::JausRouter* jausRouter ) : Service()
{
	jausRouter->setTransportType(JausRouter::Version_1_0);
	
	pPingClient_PingClientFSM = new PingClient_PingClientFSM();
	pPingClient_PingClientFSM->setHandlers(ieHandler, jausRouter);
	pPingClient_PingClientFSM->setupNotifications();

}


PingClientService::~PingClientService()
{
	delete pPingClient_PingClientFSM;

}

/**
 *	This is the function that will actually be run by the thread at start-up.
 *  We override it from EventReceiver in order to handle any entry
 *  actions defined by the initial state. 
 */
void PingClientService::run()
{
	
	/// Perform any entry actions specified by the start state.
	pPingClient_PingClientFSM->QueryHeartBeatPulseAction();

	
	/// Kick-off the receive loop...
	EventReceiver::run();
}

/**
 *	This is the function that will process an event either generated
 *  by the service, sent to it by another service on the same component,
 *  or as a message sent by a different component. 
 */
bool PingClientService::processTransitions(InternalEvent* ie)
{
        bool done = false;

   // Since this function can be called from multiple threads,
   // we use a mutex to ensure only one state transition is
   // active at a time.
   mutex.lock();

			// Invoke the FSM transition for this event.
			try
			{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("PingClient_PingClientFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == ReportHeartbeatPulse::ID)
					{
						ReportHeartbeatPulse msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pPingClient_PingClientFSM->context->ReportHeartbeatPulseTransition();
						done = true;
					}
				}
			} catch (...) {}

			 

   mutex.unlock();
   return done;
}


/**
 *	This is the function that will check for default transitions if
 *  no other transitions were satisfied. 
 */
bool PingClientService::defaultTransitions(InternalEvent* ie)
{
   bool done = false;

   // Since this function can be called from multiple threads,
   // we use a mutex to ensure only one state transition is
   // active at a time.
   mutex.lock();

			// Invoke the FSM transition for this event.
			try
		{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("PingClient_PingClientFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == ReportHeartbeatPulse::ID)
					{
						ReportHeartbeatPulse msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pPingClient_PingClientFSM->context->ReportHeartbeatPulseTransition();
						done = true;
		}
	}
			} catch (...) {}

	

   mutex.unlock();
   return done;
}


};
