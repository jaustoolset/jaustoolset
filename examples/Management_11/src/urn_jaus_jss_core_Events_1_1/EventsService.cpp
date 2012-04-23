

#include "urn_jaus_jss_core_Events_1_1/EventsService.h"

using namespace JTS;

namespace urn_jaus_jss_core_Events_1_1
{
	
EventsService::EventsService(JTS::JausRouter* jausRouter , urn_jaus_jss_core_Transport_1_1::TransportService* pTransportService) : Service()
{
	jausRouter->setTransportType(JausRouter::Version_1_1);
	
	pEvents_ReceiveFSM = new Events_ReceiveFSM(pTransportService->pTransport_ReceiveFSM);
	pEvents_ReceiveFSM->setHandlers(ieHandler, jausRouter);
	pEvents_ReceiveFSM->setupNotifications();
	pEvents_SendFSM = new Events_SendFSM(pTransportService->pTransport_SendFSM);
	pEvents_SendFSM->setHandlers(ieHandler, jausRouter);
	pEvents_SendFSM->setupNotifications();

}


EventsService::~EventsService()
{
	delete pEvents_ReceiveFSM;
	delete pEvents_SendFSM;

}

/**
 *	This is the function that will actually be run by the thread at start-up.
 *  We override it from EventReceiver in order to handle any entry
 *  actions defined by the initial state. 
 */
void EventsService::run()
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
bool EventsService::processTransitions(InternalEvent* ie)
{
   bool done = false;

   // Since this function can be called from multiple threads,
   // we use a mutex to ensure only one state transition is
   // active at a time.
   mutex.lock();

			// Invoke the FSM transition for this event.
			try
			{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Events_ReceiveFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == QueryEvents::ID)
					{
						QueryEvents msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						Receive::Body::ReceiveRec transportData = *(casted_ie->getBody()->getReceiveRec());
						pEvents_ReceiveFSM->context->ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Events_ReceiveFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == QueryEventTimeout::ID)
					{
						QueryEventTimeout msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						Receive::Body::ReceiveRec transportData = *(casted_ie->getBody()->getReceiveRec());
						pEvents_ReceiveFSM->context->ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Events_ReceiveFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == CreateEvnt::ID)
					{
						CreateEvnt msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						Receive::Body::ReceiveRec transportData = *(casted_ie->getBody()->getReceiveRec());
						pEvents_ReceiveFSM->context->ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Events_ReceiveFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == CreateEvnt::ID)
					{
						CreateEvnt msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						Receive::Body::ReceiveRec transportData = *(casted_ie->getBody()->getReceiveRec());
						pEvents_ReceiveFSM->context->ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Events_ReceiveFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == CreateEvnt::ID)
					{
						CreateEvnt msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						Receive::Body::ReceiveRec transportData = *(casted_ie->getBody()->getReceiveRec());
						pEvents_ReceiveFSM->context->ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Events_ReceiveFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == UpdateEvent::ID)
					{
						UpdateEvent msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						Receive::Body::ReceiveRec transportData = *(casted_ie->getBody()->getReceiveRec());
						pEvents_ReceiveFSM->context->ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Events_ReceiveFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == UpdateEvent::ID)
					{
						UpdateEvent msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						Receive::Body::ReceiveRec transportData = *(casted_ie->getBody()->getReceiveRec());
						pEvents_ReceiveFSM->context->ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Events_ReceiveFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == CancelEvent::ID)
					{
						CancelEvent msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						Receive::Body::ReceiveRec transportData = *(casted_ie->getBody()->getReceiveRec());
						pEvents_ReceiveFSM->context->ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Events_ReceiveFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == CancelEvent::ID)
					{
						CancelEvent msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						Receive::Body::ReceiveRec transportData = *(casted_ie->getBody()->getReceiveRec());
						pEvents_ReceiveFSM->context->ReceiveTransition(msg, transportData);
						done = true;
					}
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("EventOccurred") == 0 && (ie->getSource().compare("Events_ReceiveFSM") != 0) && (done == false))
				{
					EventOccurred* casted_ie = (EventOccurred*) ie;
						pEvents_ReceiveFSM->context->EventOccurredTransition();
						done = true;
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("EventError") == 0 && (ie->getSource().compare("Events_ReceiveFSM") != 0) && (done == false))
				{
					EventError* casted_ie = (EventError*) ie;
						pEvents_ReceiveFSM->context->EventErrorTransition();
						done = true;
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("Timeout") == 0 && (ie->getSource().compare("Events_ReceiveFSM") != 0) && (done == false))
				{
					Timeout* casted_ie = (Timeout*) ie;
						pEvents_ReceiveFSM->context->TimeoutTransition();
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
bool EventsService::defaultTransitions(InternalEvent* ie)
{
   bool done = false;

   // Since this function can be called from multiple threads,
   // we use a mutex to ensure only one state transition is
   // active at a time.
   mutex.lock();

			// Invoke the FSM transition for this event.
			try
			{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Events_ReceiveFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == CreateEvnt::ID)
					{
						CreateEvnt msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pEvents_ReceiveFSM->context->CreateEvntTransition();
						done = true;
					}
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Events_ReceiveFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == CreateCommandEvent::ID)
					{
						CreateCommandEvent msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pEvents_ReceiveFSM->context->CreateCommandEventTransition();
						done = true;
					}
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Events_ReceiveFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == UpdateEvent::ID)
					{
						UpdateEvent msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pEvents_ReceiveFSM->context->UpdateEventTransition();
						done = true;
					}
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Events_ReceiveFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == CancelEvent::ID)
					{
						CancelEvent msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pEvents_ReceiveFSM->context->CancelEventTransition();
						done = true;
					}
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Events_ReceiveFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == QueryEvents::ID)
					{
						QueryEvents msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pEvents_ReceiveFSM->context->QueryEventsTransition();
						done = true;
					}
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Events_ReceiveFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == QueryEventTimeout::ID)
					{
						QueryEventTimeout msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pEvents_ReceiveFSM->context->QueryEventTimeoutTransition();
						done = true;
					}
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("EventOccurred") == 0 && (ie->getSource().compare("Events_ReceiveFSM") != 0) && (done == false))
				{
					EventOccurred* casted_ie = (EventOccurred*) ie;
						pEvents_ReceiveFSM->context->EventOccurredTransition();
						done = true;
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("EventError") == 0 && (ie->getSource().compare("Events_ReceiveFSM") != 0) && (done == false))
				{
					EventError* casted_ie = (EventError*) ie;
						pEvents_ReceiveFSM->context->EventErrorTransition();
						done = true;
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("Timeout") == 0 && (ie->getSource().compare("Events_ReceiveFSM") != 0) && (done == false))
				{
					Timeout* casted_ie = (Timeout*) ie;
						pEvents_ReceiveFSM->context->TimeoutTransition();
						done = true;
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("CommandCompleted") == 0 && (ie->getSource().compare("Events_ReceiveFSM") != 0) && (done == false))
				{
					CommandCompleted* casted_ie = (CommandCompleted*) ie;
						pEvents_ReceiveFSM->context->CommandCompletedTransition();
						done = true;
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("CommandExpired") == 0 && (ie->getSource().compare("Events_ReceiveFSM") != 0) && (done == false))
				{
					CommandExpired* casted_ie = (CommandExpired*) ie;
						pEvents_ReceiveFSM->context->CommandExpiredTransition();
						done = true;
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("Send") == 0 && (ie->getSource().compare("Events_ReceiveFSM") != 0) && (done == false))
				{
					Send* casted_ie = (Send*) ie;
						pEvents_ReceiveFSM->context->SendTransition();
						done = true;
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("BroadcastLocal") == 0 && (ie->getSource().compare("Events_ReceiveFSM") != 0) && (done == false))
				{
					BroadcastLocal* casted_ie = (BroadcastLocal*) ie;
						pEvents_ReceiveFSM->context->BroadcastLocalTransition();
						done = true;
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("BroadcastGlobal") == 0 && (ie->getSource().compare("Events_ReceiveFSM") != 0) && (done == false))
				{
					BroadcastGlobal* casted_ie = (BroadcastGlobal*) ie;
						pEvents_ReceiveFSM->context->BroadcastGlobalTransition();
						done = true;
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Events_SendFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == CreateEvnt::ID)
					{
						CreateEvnt msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pEvents_SendFSM->context->CreateEvntTransition();
						done = true;
					}
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Events_SendFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == CreateCommandEvent::ID)
					{
						CreateCommandEvent msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pEvents_SendFSM->context->CreateCommandEventTransition();
						done = true;
					}
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Events_SendFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == UpdateEvent::ID)
					{
						UpdateEvent msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pEvents_SendFSM->context->UpdateEventTransition();
						done = true;
					}
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Events_SendFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == CancelEvent::ID)
					{
						CancelEvent msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pEvents_SendFSM->context->CancelEventTransition();
						done = true;
					}
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Events_SendFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == QueryEvents::ID)
					{
						QueryEvents msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pEvents_SendFSM->context->QueryEventsTransition();
						done = true;
					}
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Events_SendFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == QueryEventTimeout::ID)
					{
						QueryEventTimeout msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pEvents_SendFSM->context->QueryEventTimeoutTransition();
						done = true;
					}
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("EventOccurred") == 0 && (ie->getSource().compare("Events_SendFSM") != 0) && (done == false))
				{
					EventOccurred* casted_ie = (EventOccurred*) ie;
						pEvents_SendFSM->context->EventOccurredTransition();
						done = true;
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("EventError") == 0 && (ie->getSource().compare("Events_SendFSM") != 0) && (done == false))
				{
					EventError* casted_ie = (EventError*) ie;
						pEvents_SendFSM->context->EventErrorTransition();
						done = true;
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("Timeout") == 0 && (ie->getSource().compare("Events_SendFSM") != 0) && (done == false))
				{
					Timeout* casted_ie = (Timeout*) ie;
						pEvents_SendFSM->context->TimeoutTransition();
						done = true;
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("CommandCompleted") == 0 && (ie->getSource().compare("Events_SendFSM") != 0) && (done == false))
				{
					CommandCompleted* casted_ie = (CommandCompleted*) ie;
						pEvents_SendFSM->context->CommandCompletedTransition();
						done = true;
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("CommandExpired") == 0 && (ie->getSource().compare("Events_SendFSM") != 0) && (done == false))
				{
					CommandExpired* casted_ie = (CommandExpired*) ie;
						pEvents_SendFSM->context->CommandExpiredTransition();
						done = true;
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("Send") == 0 && (ie->getSource().compare("Events_SendFSM") != 0) && (done == false))
				{
					Send* casted_ie = (Send*) ie;
						pEvents_SendFSM->context->SendTransition();
						done = true;
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("BroadcastLocal") == 0 && (ie->getSource().compare("Events_SendFSM") != 0) && (done == false))
				{
					BroadcastLocal* casted_ie = (BroadcastLocal*) ie;
						pEvents_SendFSM->context->BroadcastLocalTransition();
						done = true;
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("BroadcastGlobal") == 0 && (ie->getSource().compare("Events_SendFSM") != 0) && (done == false))
				{
					BroadcastGlobal* casted_ie = (BroadcastGlobal*) ie;
						pEvents_SendFSM->context->BroadcastGlobalTransition();
						done = true;
				}
			} catch (...) {}

	

   mutex.unlock();
   return done;
}


};
