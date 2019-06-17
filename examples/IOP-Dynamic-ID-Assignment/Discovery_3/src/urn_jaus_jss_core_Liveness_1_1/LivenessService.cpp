

#include "urn_jaus_jss_core_Liveness_1_1/LivenessService.h"

using namespace JTS;

namespace urn_jaus_jss_core_Liveness_1_1
{
	
LivenessService::LivenessService(JTS::JausRouter* jausRouter , urn_jaus_jss_core_Transport_1_1::TransportService* pTransportService, urn_jaus_jss_core_Events_1_1::EventsService* pEventsService) : Service()
{
	jausRouter->setTransportType(JausRouter::Version_1_1);
	
	pLiveness_ReceiveFSM = new Liveness_ReceiveFSM(pTransportService->pTransport_ReceiveFSM, pEventsService->pEvents_ReceiveFSM);
	pLiveness_ReceiveFSM->setHandlers(ieHandler, jausRouter);
	pLiveness_ReceiveFSM->setupNotifications();
	pLiveness_SendFSM = new Liveness_SendFSM(pTransportService->pTransport_SendFSM, pEventsService->pEvents_SendFSM);
	pLiveness_SendFSM->setHandlers(ieHandler, jausRouter);
	pLiveness_SendFSM->setupNotifications();

}


LivenessService::~LivenessService()
{
	delete pLiveness_ReceiveFSM;
	delete pLiveness_SendFSM;

}

/**
 *	This is the function that will actually be run by the thread at start-up.
 *  We override it from EventReceiver in order to handle any entry
 *  actions defined by the initial state. 
 */
void LivenessService::run()
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
bool LivenessService::processTransitions(InternalEvent* ie)
{
        bool done = false;

   // Since this function can be called from multiple threads,
   // we use a mutex to ensure only one state transition is
   // active at a time.
   mutex.lock();

			// Invoke the FSM transition for this event.
			try
			{
				if ((done == false) && (ie->getName().compare("Receive") == 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());

					if ((id != QueryHeartbeatPulse::ID))
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Liveness_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == QueryHeartbeatPulse::ID)
					{
						QueryHeartbeatPulse msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						Receive::Body::ReceiveRec transportData = *(casted_ie->getBody()->getReceiveRec());
						pLiveness_ReceiveFSM->context->ReceiveTransition(msg, transportData);
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			 
leave:
   mutex.unlock();
   return done;
}


/**
 *	This is the function that will check for default transitions if
 *  no other transitions were satisfied. 
 */
bool LivenessService::defaultTransitions(InternalEvent* ie)
{
   bool done = false;

   // Since this function can be called from multiple threads,
   // we use a mutex to ensure only one state transition is
   // active at a time.
   mutex.lock();

			// Invoke the FSM transition for this event.
			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Liveness_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == QueryHeartbeatPulse::ID)
					{
						QueryHeartbeatPulse msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pLiveness_ReceiveFSM->context->QueryHeartbeatPulseTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Liveness_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == CreateEvent::ID)
					{
						CreateEvent msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pLiveness_ReceiveFSM->context->CreateEventTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Liveness_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == CreateCommandEvent::ID)
					{
						CreateCommandEvent msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pLiveness_ReceiveFSM->context->CreateCommandEventTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Liveness_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == UpdateEvent::ID)
					{
						UpdateEvent msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pLiveness_ReceiveFSM->context->UpdateEventTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Liveness_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == CancelEvent::ID)
					{
						CancelEvent msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pLiveness_ReceiveFSM->context->CancelEventTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Liveness_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == QueryEvents::ID)
					{
						QueryEvents msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pLiveness_ReceiveFSM->context->QueryEventsTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Liveness_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == QueryEventTimeout::ID)
					{
						QueryEventTimeout msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pLiveness_ReceiveFSM->context->QueryEventTimeoutTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("EventOccurred") == 0 && (ie->getSource().compare("Liveness_ReceiveFSM") != 0))
				{
					EventOccurred* casted_ie = (EventOccurred*) ie;
						pLiveness_ReceiveFSM->context->EventOccurredTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("EventError") == 0 && (ie->getSource().compare("Liveness_ReceiveFSM") != 0))
				{
					EventError* casted_ie = (EventError*) ie;
						pLiveness_ReceiveFSM->context->EventErrorTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Timeout") == 0 && (ie->getSource().compare("Liveness_ReceiveFSM") != 0))
				{
					Timeout* casted_ie = (Timeout*) ie;
						pLiveness_ReceiveFSM->context->TimeoutTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("CommandCompleted") == 0 && (ie->getSource().compare("Liveness_ReceiveFSM") != 0))
				{
					CommandCompleted* casted_ie = (CommandCompleted*) ie;
						pLiveness_ReceiveFSM->context->CommandCompletedTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("CommandExpired") == 0 && (ie->getSource().compare("Liveness_ReceiveFSM") != 0))
				{
					CommandExpired* casted_ie = (CommandExpired*) ie;
						pLiveness_ReceiveFSM->context->CommandExpiredTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Send") == 0 && (ie->getSource().compare("Liveness_ReceiveFSM") != 0))
				{
					Send* casted_ie = (Send*) ie;
						pLiveness_ReceiveFSM->context->SendTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("BroadcastLocal") == 0 && (ie->getSource().compare("Liveness_ReceiveFSM") != 0))
				{
					BroadcastLocal* casted_ie = (BroadcastLocal*) ie;
						pLiveness_ReceiveFSM->context->BroadcastLocalTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("BroadcastGlobal") == 0 && (ie->getSource().compare("Liveness_ReceiveFSM") != 0))
				{
					BroadcastGlobal* casted_ie = (BroadcastGlobal*) ie;
						pLiveness_ReceiveFSM->context->BroadcastGlobalTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Liveness_SendFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == QueryHeartbeatPulse::ID)
					{
						QueryHeartbeatPulse msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pLiveness_SendFSM->context->QueryHeartbeatPulseTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Liveness_SendFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == CreateEvent::ID)
					{
						CreateEvent msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pLiveness_SendFSM->context->CreateEventTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Liveness_SendFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == CreateCommandEvent::ID)
					{
						CreateCommandEvent msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pLiveness_SendFSM->context->CreateCommandEventTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Liveness_SendFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == UpdateEvent::ID)
					{
						UpdateEvent msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pLiveness_SendFSM->context->UpdateEventTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Liveness_SendFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == CancelEvent::ID)
					{
						CancelEvent msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pLiveness_SendFSM->context->CancelEventTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Liveness_SendFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == QueryEvents::ID)
					{
						QueryEvents msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pLiveness_SendFSM->context->QueryEventsTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Liveness_SendFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == QueryEventTimeout::ID)
					{
						QueryEventTimeout msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pLiveness_SendFSM->context->QueryEventTimeoutTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("EventOccurred") == 0 && (ie->getSource().compare("Liveness_SendFSM") != 0))
				{
					EventOccurred* casted_ie = (EventOccurred*) ie;
						pLiveness_SendFSM->context->EventOccurredTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("EventError") == 0 && (ie->getSource().compare("Liveness_SendFSM") != 0))
				{
					EventError* casted_ie = (EventError*) ie;
						pLiveness_SendFSM->context->EventErrorTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Timeout") == 0 && (ie->getSource().compare("Liveness_SendFSM") != 0))
				{
					Timeout* casted_ie = (Timeout*) ie;
						pLiveness_SendFSM->context->TimeoutTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("CommandCompleted") == 0 && (ie->getSource().compare("Liveness_SendFSM") != 0))
				{
					CommandCompleted* casted_ie = (CommandCompleted*) ie;
						pLiveness_SendFSM->context->CommandCompletedTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("CommandExpired") == 0 && (ie->getSource().compare("Liveness_SendFSM") != 0))
				{
					CommandExpired* casted_ie = (CommandExpired*) ie;
						pLiveness_SendFSM->context->CommandExpiredTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Send") == 0 && (ie->getSource().compare("Liveness_SendFSM") != 0))
				{
					Send* casted_ie = (Send*) ie;
						pLiveness_SendFSM->context->SendTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("BroadcastLocal") == 0 && (ie->getSource().compare("Liveness_SendFSM") != 0))
				{
					BroadcastLocal* casted_ie = (BroadcastLocal*) ie;
						pLiveness_SendFSM->context->BroadcastLocalTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("BroadcastGlobal") == 0 && (ie->getSource().compare("Liveness_SendFSM") != 0))
				{
					BroadcastGlobal* casted_ie = (BroadcastGlobal*) ie;
						pLiveness_SendFSM->context->BroadcastGlobalTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

	
leave:
   mutex.unlock();
   return done;
}


};
