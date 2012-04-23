

#include "urn_jaus_jss_core_Transport_1_1/TransportService.h"

using namespace JTS;

namespace urn_jaus_jss_core_Transport_1_1
{
	
TransportService::TransportService(JTS::JausRouter* jausRouter ) : Service()
{
	jausRouter->setTransportType(JausRouter::Version_1_1);
	
	pTransport_ReceiveFSM = new Transport_ReceiveFSM();
	pTransport_ReceiveFSM->setHandlers(ieHandler, jausRouter);
	pTransport_ReceiveFSM->setupNotifications();
	pTransport_SendFSM = new Transport_SendFSM();
	pTransport_SendFSM->setHandlers(ieHandler, jausRouter);
	pTransport_SendFSM->setupNotifications();

}


TransportService::~TransportService()
{
	delete pTransport_ReceiveFSM;
	delete pTransport_SendFSM;

}

/**
 *	This is the function that will actually be run by the thread at start-up.
 *  We override it from EventReceiver in order to handle any entry
 *  actions defined by the initial state. 
 */
void TransportService::run()
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
bool TransportService::processTransitions(InternalEvent* ie)
{
   bool done = false;

   // Since this function can be called from multiple threads,
   // we use a mutex to ensure only one state transition is
   // active at a time.
   mutex.lock();

			// Invoke the FSM transition for this event.
			try
			{
				if (ie->getName().compare("Send") == 0 && (ie->getSource().compare("Transport_SendFSM") != 0) && (done == false))
				{
					Send* casted_ie = (Send*) ie;
						Send msg = *casted_ie;
						pTransport_SendFSM->context->SendTransition(msg);
						done = true;
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("BroadcastLocal") == 0 && (ie->getSource().compare("Transport_SendFSM") != 0) && (done == false))
				{
					BroadcastLocal* casted_ie = (BroadcastLocal*) ie;
						BroadcastLocal msg = *casted_ie;
						pTransport_SendFSM->context->BroadcastLocalTransition(msg);
						done = true;
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("BroadcastGlobal") == 0 && (ie->getSource().compare("Transport_SendFSM") != 0) && (done == false))
				{
					BroadcastGlobal* casted_ie = (BroadcastGlobal*) ie;
						BroadcastGlobal msg = *casted_ie;
						pTransport_SendFSM->context->BroadcastGlobalTransition(msg);
						done = true;
				}
			} catch (...) {}

	

   mutex.unlock();
   return done;
}


/**
 *	This is the function that will check for default transitions if
 *  no other transitions were satisfied. 
 */
bool TransportService::defaultTransitions(InternalEvent* ie)
{
   bool done = false;

   // Since this function can be called from multiple threads,
   // we use a mutex to ensure only one state transition is
   // active at a time.
   mutex.lock();

			// Invoke the FSM transition for this event.
			try
			{
				if (ie->getName().compare("Send") == 0 && (ie->getSource().compare("Transport_ReceiveFSM") != 0) && (done == false))
				{
					Send* casted_ie = (Send*) ie;
						pTransport_ReceiveFSM->context->SendTransition();
						done = true;
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("BroadcastLocal") == 0 && (ie->getSource().compare("Transport_ReceiveFSM") != 0) && (done == false))
				{
					BroadcastLocal* casted_ie = (BroadcastLocal*) ie;
						pTransport_ReceiveFSM->context->BroadcastLocalTransition();
						done = true;
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("BroadcastGlobal") == 0 && (ie->getSource().compare("Transport_ReceiveFSM") != 0) && (done == false))
				{
					BroadcastGlobal* casted_ie = (BroadcastGlobal*) ie;
						pTransport_ReceiveFSM->context->BroadcastGlobalTransition();
						done = true;
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("Send") == 0 && (ie->getSource().compare("Transport_SendFSM") != 0) && (done == false))
				{
					Send* casted_ie = (Send*) ie;
						pTransport_SendFSM->context->SendTransition();
						done = true;
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("BroadcastLocal") == 0 && (ie->getSource().compare("Transport_SendFSM") != 0) && (done == false))
				{
					BroadcastLocal* casted_ie = (BroadcastLocal*) ie;
						pTransport_SendFSM->context->BroadcastLocalTransition();
						done = true;
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("BroadcastGlobal") == 0 && (ie->getSource().compare("Transport_SendFSM") != 0) && (done == false))
				{
					BroadcastGlobal* casted_ie = (BroadcastGlobal*) ie;
						pTransport_SendFSM->context->BroadcastGlobalTransition();
						done = true;
				}
			} catch (...) {}

	

   mutex.unlock();
   return done;
}


};
