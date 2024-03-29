

#include "urn_jaus_jss_iop_UnsolicitedHeartbeat_1_1/UnsolicitedHeartbeatService.h"

using namespace JTS;

namespace urn_jaus_jss_iop_UnsolicitedHeartbeat_1_1
{
	
UnsolicitedHeartbeatService::UnsolicitedHeartbeatService(JTS::JausRouter* jausRouter , urn_jaus_jss_core_Transport_1_1::TransportService* pTransportService, urn_jaus_jss_core_Events_1_1::EventsService* pEventsService, urn_jaus_jss_core_Liveness_1_1::LivenessService* pLivenessService) : Service()
{
	jausRouter->setTransportType(JausRouter::Version_1_1);
	
	pUnsolicitedHeartbeat_ReceiveFSM = new UnsolicitedHeartbeat_ReceiveFSM(pTransportService->pTransport_ReceiveFSM, pEventsService->pEvents_ReceiveFSM, pLivenessService->pLiveness_ReceiveFSM);
	pUnsolicitedHeartbeat_ReceiveFSM->setHandlers(ieHandler, jausRouter);
	pUnsolicitedHeartbeat_ReceiveFSM->setupNotifications();
	pUnsolicitedHeartbeat_SendFSM = new UnsolicitedHeartbeat_SendFSM(pTransportService->pTransport_SendFSM, pEventsService->pEvents_SendFSM, pLivenessService->pLiveness_SendFSM);
	pUnsolicitedHeartbeat_SendFSM->setHandlers(ieHandler, jausRouter);
	pUnsolicitedHeartbeat_SendFSM->setupNotifications();

}


UnsolicitedHeartbeatService::~UnsolicitedHeartbeatService()
{
	delete pUnsolicitedHeartbeat_ReceiveFSM;
	delete pUnsolicitedHeartbeat_SendFSM;

}

/**
 *	This is the function that will actually be run by the thread at start-up.
 *  We override it from EventReceiver in order to handle any entry
 *  actions defined by the initial state. 
 */
void UnsolicitedHeartbeatService::run()
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
bool UnsolicitedHeartbeatService::processTransitions(InternalEvent* ie)
{
        bool done = false;

   // Since this function can be called from multiple threads,
   // we use a mutex to ensure only one state transition is
   // active at a time.
   mutex.lock();

			// Invoke the FSM transition for this event.
			try
			{
				if ((done == false) && ie->getName().compare("PeriodicTimerTrigger") == 0 && (ie->getSource().compare("UnsolicitedHeartbeat_ReceiveFSM") != 0))
				{
					PeriodicTimerTrigger* casted_ie = (PeriodicTimerTrigger*) ie;
						pUnsolicitedHeartbeat_ReceiveFSM->context->PeriodicTimerTriggerTransition();
						done = true;
						goto leave;
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
bool UnsolicitedHeartbeatService::defaultTransitions(InternalEvent* ie)
{
   bool done = false;

   // Since this function can be called from multiple threads,
   // we use a mutex to ensure only one state transition is
   // active at a time.
   mutex.lock();

			// Invoke the FSM transition for this event.
			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("UnsolicitedHeartbeat_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == QueryHeartbeatPulse::ID)
					{
						QueryHeartbeatPulse msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pUnsolicitedHeartbeat_ReceiveFSM->context->QueryHeartbeatPulseTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("UnsolicitedHeartbeat_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == CreateEvent::ID)
					{
						CreateEvent msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pUnsolicitedHeartbeat_ReceiveFSM->context->CreateEventTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("UnsolicitedHeartbeat_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == CreateCommandEvent::ID)
					{
						CreateCommandEvent msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pUnsolicitedHeartbeat_ReceiveFSM->context->CreateCommandEventTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("UnsolicitedHeartbeat_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == UpdateEvent::ID)
					{
						UpdateEvent msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pUnsolicitedHeartbeat_ReceiveFSM->context->UpdateEventTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("UnsolicitedHeartbeat_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == CancelEvent::ID)
					{
						CancelEvent msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pUnsolicitedHeartbeat_ReceiveFSM->context->CancelEventTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("UnsolicitedHeartbeat_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == QueryEvents::ID)
					{
						QueryEvents msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pUnsolicitedHeartbeat_ReceiveFSM->context->QueryEventsTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("UnsolicitedHeartbeat_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == QueryEventTimeout::ID)
					{
						QueryEventTimeout msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pUnsolicitedHeartbeat_ReceiveFSM->context->QueryEventTimeoutTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("PeriodicTimerTrigger") == 0 && (ie->getSource().compare("UnsolicitedHeartbeat_ReceiveFSM") != 0))
				{
					PeriodicTimerTrigger* casted_ie = (PeriodicTimerTrigger*) ie;
						pUnsolicitedHeartbeat_ReceiveFSM->context->PeriodicTimerTriggerTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("EventOccurred") == 0 && (ie->getSource().compare("UnsolicitedHeartbeat_ReceiveFSM") != 0))
				{
					EventOccurred* casted_ie = (EventOccurred*) ie;
						pUnsolicitedHeartbeat_ReceiveFSM->context->EventOccurredTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("EventError") == 0 && (ie->getSource().compare("UnsolicitedHeartbeat_ReceiveFSM") != 0))
				{
					EventError* casted_ie = (EventError*) ie;
						pUnsolicitedHeartbeat_ReceiveFSM->context->EventErrorTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Timeout") == 0 && (ie->getSource().compare("UnsolicitedHeartbeat_ReceiveFSM") != 0))
				{
					Timeout* casted_ie = (Timeout*) ie;
						pUnsolicitedHeartbeat_ReceiveFSM->context->TimeoutTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("CommandCompleted") == 0 && (ie->getSource().compare("UnsolicitedHeartbeat_ReceiveFSM") != 0))
				{
					CommandCompleted* casted_ie = (CommandCompleted*) ie;
						pUnsolicitedHeartbeat_ReceiveFSM->context->CommandCompletedTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("CommandExpired") == 0 && (ie->getSource().compare("UnsolicitedHeartbeat_ReceiveFSM") != 0))
				{
					CommandExpired* casted_ie = (CommandExpired*) ie;
						pUnsolicitedHeartbeat_ReceiveFSM->context->CommandExpiredTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Send") == 0 && (ie->getSource().compare("UnsolicitedHeartbeat_ReceiveFSM") != 0))
				{
					Send* casted_ie = (Send*) ie;
						pUnsolicitedHeartbeat_ReceiveFSM->context->SendTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("BroadcastLocal") == 0 && (ie->getSource().compare("UnsolicitedHeartbeat_ReceiveFSM") != 0))
				{
					BroadcastLocal* casted_ie = (BroadcastLocal*) ie;
						pUnsolicitedHeartbeat_ReceiveFSM->context->BroadcastLocalTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("BroadcastGlobal") == 0 && (ie->getSource().compare("UnsolicitedHeartbeat_ReceiveFSM") != 0))
				{
					BroadcastGlobal* casted_ie = (BroadcastGlobal*) ie;
						pUnsolicitedHeartbeat_ReceiveFSM->context->BroadcastGlobalTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("UnsolicitedHeartbeat_SendFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == QueryHeartbeatPulse::ID)
					{
						QueryHeartbeatPulse msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pUnsolicitedHeartbeat_SendFSM->context->QueryHeartbeatPulseTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("UnsolicitedHeartbeat_SendFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == CreateEvent::ID)
					{
						CreateEvent msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pUnsolicitedHeartbeat_SendFSM->context->CreateEventTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("UnsolicitedHeartbeat_SendFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == CreateCommandEvent::ID)
					{
						CreateCommandEvent msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pUnsolicitedHeartbeat_SendFSM->context->CreateCommandEventTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("UnsolicitedHeartbeat_SendFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == UpdateEvent::ID)
					{
						UpdateEvent msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pUnsolicitedHeartbeat_SendFSM->context->UpdateEventTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("UnsolicitedHeartbeat_SendFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == CancelEvent::ID)
					{
						CancelEvent msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pUnsolicitedHeartbeat_SendFSM->context->CancelEventTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("UnsolicitedHeartbeat_SendFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == QueryEvents::ID)
					{
						QueryEvents msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pUnsolicitedHeartbeat_SendFSM->context->QueryEventsTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("UnsolicitedHeartbeat_SendFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == QueryEventTimeout::ID)
					{
						QueryEventTimeout msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pUnsolicitedHeartbeat_SendFSM->context->QueryEventTimeoutTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("PeriodicTimerTrigger") == 0 && (ie->getSource().compare("UnsolicitedHeartbeat_SendFSM") != 0))
				{
					PeriodicTimerTrigger* casted_ie = (PeriodicTimerTrigger*) ie;
						pUnsolicitedHeartbeat_SendFSM->context->PeriodicTimerTriggerTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("EventOccurred") == 0 && (ie->getSource().compare("UnsolicitedHeartbeat_SendFSM") != 0))
				{
					EventOccurred* casted_ie = (EventOccurred*) ie;
						pUnsolicitedHeartbeat_SendFSM->context->EventOccurredTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("EventError") == 0 && (ie->getSource().compare("UnsolicitedHeartbeat_SendFSM") != 0))
				{
					EventError* casted_ie = (EventError*) ie;
						pUnsolicitedHeartbeat_SendFSM->context->EventErrorTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Timeout") == 0 && (ie->getSource().compare("UnsolicitedHeartbeat_SendFSM") != 0))
				{
					Timeout* casted_ie = (Timeout*) ie;
						pUnsolicitedHeartbeat_SendFSM->context->TimeoutTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("CommandCompleted") == 0 && (ie->getSource().compare("UnsolicitedHeartbeat_SendFSM") != 0))
				{
					CommandCompleted* casted_ie = (CommandCompleted*) ie;
						pUnsolicitedHeartbeat_SendFSM->context->CommandCompletedTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("CommandExpired") == 0 && (ie->getSource().compare("UnsolicitedHeartbeat_SendFSM") != 0))
				{
					CommandExpired* casted_ie = (CommandExpired*) ie;
						pUnsolicitedHeartbeat_SendFSM->context->CommandExpiredTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Send") == 0 && (ie->getSource().compare("UnsolicitedHeartbeat_SendFSM") != 0))
				{
					Send* casted_ie = (Send*) ie;
						pUnsolicitedHeartbeat_SendFSM->context->SendTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("BroadcastLocal") == 0 && (ie->getSource().compare("UnsolicitedHeartbeat_SendFSM") != 0))
				{
					BroadcastLocal* casted_ie = (BroadcastLocal*) ie;
						pUnsolicitedHeartbeat_SendFSM->context->BroadcastLocalTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("BroadcastGlobal") == 0 && (ie->getSource().compare("UnsolicitedHeartbeat_SendFSM") != 0))
				{
					BroadcastGlobal* casted_ie = (BroadcastGlobal*) ie;
						pUnsolicitedHeartbeat_SendFSM->context->BroadcastGlobalTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

	
leave:
   mutex.unlock();
   return done;
}


};
