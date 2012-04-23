

#include "urn_jts_PingServer_1_0/PingServerService.h"

using namespace JTS;

namespace urn_jts_PingServer_1_0
{
	
PingServerService::PingServerService(JTS::JausRouter* jausRouter ) : Service()
{
	jausRouter->setTransportType(JausRouter::Version_1_0);
	
	pPingServer_PingFSM = new PingServer_PingFSM();
	pPingServer_PingFSM->setHandlers(ieHandler, jausRouter);
	pPingServer_PingFSM->setupNotifications();

}


PingServerService::~PingServerService()
{
	delete pPingServer_PingFSM;

}

/**
 *	This is the function that will actually be run by the thread at start-up.
 *  We override it from EventReceiver in order to handle any entry
 *  actions defined by the initial state. 
 */
void PingServerService::run()
{
	
	/// Perform any entry actions specified by the start state.

	
	/// Kick-off the receive loop...
	EventReceiver::run();
}

/**
 *	This is the function that will process an event either generated
 *  by the service, sent to it by another service on the same component,
 *  or as a message sent by a different component. 
 */
bool PingServerService::processTransitions(InternalEvent* ie)
{
        bool done = false;

   // Since this function can be called from multiple threads,
   // we use a mutex to ensure only one state transition is
   // active at a time.
   mutex.lock();

			// Invoke the FSM transition for this event.
			try
			{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("PingServer_PingFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == QueryHeartbeatPulse::ID)
					{
						QueryHeartbeatPulse msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pPingServer_PingFSM->context->QueryHeartbeatPulseTransition();
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
bool PingServerService::defaultTransitions(InternalEvent* ie)
{
   bool done = false;

   // Since this function can be called from multiple threads,
   // we use a mutex to ensure only one state transition is
   // active at a time.
   mutex.lock();

			// Invoke the FSM transition for this event.
			try
		{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("PingServer_PingFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == QueryHeartbeatPulse::ID)
					{
						QueryHeartbeatPulse msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pPingServer_PingFSM->context->QueryHeartbeatPulseTransition();
						done = true;
		}
	}
			} catch (...) {}

	

   mutex.unlock();
   return done;
}


};
