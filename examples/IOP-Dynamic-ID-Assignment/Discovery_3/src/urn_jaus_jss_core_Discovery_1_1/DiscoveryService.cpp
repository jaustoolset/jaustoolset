

#include "urn_jaus_jss_core_Discovery_1_1/DiscoveryService.h"

using namespace JTS;

namespace urn_jaus_jss_core_Discovery_1_1
{
	
DiscoveryService::DiscoveryService(JTS::JausRouter* jausRouter , urn_jaus_jss_core_Transport_1_1::TransportService* pTransportService, urn_jaus_jss_core_Events_1_1::EventsService* pEventsService) : Service()
{
	jausRouter->setTransportType(JausRouter::Version_1_1);
	
	pDiscovery_ReceiveFSM = new Discovery_ReceiveFSM(pTransportService->pTransport_ReceiveFSM, pEventsService->pEvents_ReceiveFSM);
	pDiscovery_ReceiveFSM->setHandlers(ieHandler, jausRouter);
	pDiscovery_ReceiveFSM->setupNotifications();
	pDiscovery_SendFSM = new Discovery_SendFSM(pTransportService->pTransport_SendFSM, pEventsService->pEvents_SendFSM);
	pDiscovery_SendFSM->setHandlers(ieHandler, jausRouter);
	pDiscovery_SendFSM->setupNotifications();

}


DiscoveryService::~DiscoveryService()
{
	delete pDiscovery_ReceiveFSM;
	delete pDiscovery_SendFSM;

}

/**
 *	This is the function that will actually be run by the thread at start-up.
 *  We override it from EventReceiver in order to handle any entry
 *  actions defined by the initial state. 
 */
void DiscoveryService::run()
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
bool DiscoveryService::processTransitions(InternalEvent* ie)
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

					if ((id != QueryIdentification::ID) &&
						(id != QueryConfiguration::ID) &&
						(id != QuerySubsystemList::ID) &&
						(id != QueryServices::ID) &&
						(id != QueryServiceList::ID) &&
						(id != RegisterServices::ID))
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Discovery_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == QueryIdentification::ID)
					{
						QueryIdentification msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						Receive::Body::ReceiveRec transportData = *(casted_ie->getBody()->getReceiveRec());
						pDiscovery_ReceiveFSM->context->ReceiveTransition(msg, transportData);
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Discovery_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == QueryConfiguration::ID)
					{
						QueryConfiguration msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						Receive::Body::ReceiveRec transportData = *(casted_ie->getBody()->getReceiveRec());
						pDiscovery_ReceiveFSM->context->ReceiveTransition(msg, transportData);
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Discovery_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == QuerySubsystemList::ID)
					{
						QuerySubsystemList msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						Receive::Body::ReceiveRec transportData = *(casted_ie->getBody()->getReceiveRec());
						pDiscovery_ReceiveFSM->context->ReceiveTransition(msg, transportData);
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Discovery_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == QueryServices::ID)
					{
						QueryServices msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						Receive::Body::ReceiveRec transportData = *(casted_ie->getBody()->getReceiveRec());
						pDiscovery_ReceiveFSM->context->ReceiveTransition(msg, transportData);
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Discovery_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == QueryServiceList::ID)
					{
						QueryServiceList msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						Receive::Body::ReceiveRec transportData = *(casted_ie->getBody()->getReceiveRec());
						pDiscovery_ReceiveFSM->context->ReceiveTransition(msg, transportData);
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Discovery_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == RegisterServices::ID)
					{
						RegisterServices msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						Receive::Body::ReceiveRec transportData = *(casted_ie->getBody()->getReceiveRec());
						pDiscovery_ReceiveFSM->context->ReceiveTransition(msg, transportData);
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
bool DiscoveryService::defaultTransitions(InternalEvent* ie)
{
   bool done = false;

   // Since this function can be called from multiple threads,
   // we use a mutex to ensure only one state transition is
   // active at a time.
   mutex.lock();

			// Invoke the FSM transition for this event.
			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Discovery_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == QueryIdentification::ID)
					{
						QueryIdentification msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pDiscovery_ReceiveFSM->context->QueryIdentificationTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Discovery_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == QueryConfiguration::ID)
					{
						QueryConfiguration msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pDiscovery_ReceiveFSM->context->QueryConfigurationTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Discovery_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == QuerySubsystemList::ID)
					{
						QuerySubsystemList msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pDiscovery_ReceiveFSM->context->QuerySubsystemListTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Discovery_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == QueryServices::ID)
					{
						QueryServices msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pDiscovery_ReceiveFSM->context->QueryServicesTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Discovery_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == QueryServiceList::ID)
					{
						QueryServiceList msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pDiscovery_ReceiveFSM->context->QueryServiceListTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Discovery_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == RegisterServices::ID)
					{
						RegisterServices msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pDiscovery_ReceiveFSM->context->RegisterServicesTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Discovery_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == CreateEvent::ID)
					{
						CreateEvent msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pDiscovery_ReceiveFSM->context->CreateEventTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Discovery_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == CreateCommandEvent::ID)
					{
						CreateCommandEvent msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pDiscovery_ReceiveFSM->context->CreateCommandEventTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Discovery_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == UpdateEvent::ID)
					{
						UpdateEvent msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pDiscovery_ReceiveFSM->context->UpdateEventTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Discovery_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == CancelEvent::ID)
					{
						CancelEvent msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pDiscovery_ReceiveFSM->context->CancelEventTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Discovery_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == QueryEvents::ID)
					{
						QueryEvents msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pDiscovery_ReceiveFSM->context->QueryEventsTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Discovery_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == QueryEventTimeout::ID)
					{
						QueryEventTimeout msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pDiscovery_ReceiveFSM->context->QueryEventTimeoutTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("EventOccurred") == 0 && (ie->getSource().compare("Discovery_ReceiveFSM") != 0))
				{
					EventOccurred* casted_ie = (EventOccurred*) ie;
						pDiscovery_ReceiveFSM->context->EventOccurredTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("EventError") == 0 && (ie->getSource().compare("Discovery_ReceiveFSM") != 0))
				{
					EventError* casted_ie = (EventError*) ie;
						pDiscovery_ReceiveFSM->context->EventErrorTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Timeout") == 0 && (ie->getSource().compare("Discovery_ReceiveFSM") != 0))
				{
					Timeout* casted_ie = (Timeout*) ie;
						pDiscovery_ReceiveFSM->context->TimeoutTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("CommandCompleted") == 0 && (ie->getSource().compare("Discovery_ReceiveFSM") != 0))
				{
					CommandCompleted* casted_ie = (CommandCompleted*) ie;
						pDiscovery_ReceiveFSM->context->CommandCompletedTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("CommandExpired") == 0 && (ie->getSource().compare("Discovery_ReceiveFSM") != 0))
				{
					CommandExpired* casted_ie = (CommandExpired*) ie;
						pDiscovery_ReceiveFSM->context->CommandExpiredTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Send") == 0 && (ie->getSource().compare("Discovery_ReceiveFSM") != 0))
				{
					Send* casted_ie = (Send*) ie;
						pDiscovery_ReceiveFSM->context->SendTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("BroadcastLocal") == 0 && (ie->getSource().compare("Discovery_ReceiveFSM") != 0))
				{
					BroadcastLocal* casted_ie = (BroadcastLocal*) ie;
						pDiscovery_ReceiveFSM->context->BroadcastLocalTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("BroadcastGlobal") == 0 && (ie->getSource().compare("Discovery_ReceiveFSM") != 0))
				{
					BroadcastGlobal* casted_ie = (BroadcastGlobal*) ie;
						pDiscovery_ReceiveFSM->context->BroadcastGlobalTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Discovery_SendFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == QueryIdentification::ID)
					{
						QueryIdentification msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pDiscovery_SendFSM->context->QueryIdentificationTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Discovery_SendFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == QueryConfiguration::ID)
					{
						QueryConfiguration msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pDiscovery_SendFSM->context->QueryConfigurationTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Discovery_SendFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == QuerySubsystemList::ID)
					{
						QuerySubsystemList msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pDiscovery_SendFSM->context->QuerySubsystemListTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Discovery_SendFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == QueryServices::ID)
					{
						QueryServices msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pDiscovery_SendFSM->context->QueryServicesTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Discovery_SendFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == QueryServiceList::ID)
					{
						QueryServiceList msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pDiscovery_SendFSM->context->QueryServiceListTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Discovery_SendFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == RegisterServices::ID)
					{
						RegisterServices msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pDiscovery_SendFSM->context->RegisterServicesTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Discovery_SendFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == CreateEvent::ID)
					{
						CreateEvent msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pDiscovery_SendFSM->context->CreateEventTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Discovery_SendFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == CreateCommandEvent::ID)
					{
						CreateCommandEvent msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pDiscovery_SendFSM->context->CreateCommandEventTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Discovery_SendFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == UpdateEvent::ID)
					{
						UpdateEvent msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pDiscovery_SendFSM->context->UpdateEventTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Discovery_SendFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == CancelEvent::ID)
					{
						CancelEvent msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pDiscovery_SendFSM->context->CancelEventTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Discovery_SendFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == QueryEvents::ID)
					{
						QueryEvents msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pDiscovery_SendFSM->context->QueryEventsTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("Discovery_SendFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == QueryEventTimeout::ID)
					{
						QueryEventTimeout msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pDiscovery_SendFSM->context->QueryEventTimeoutTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("EventOccurred") == 0 && (ie->getSource().compare("Discovery_SendFSM") != 0))
				{
					EventOccurred* casted_ie = (EventOccurred*) ie;
						pDiscovery_SendFSM->context->EventOccurredTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("EventError") == 0 && (ie->getSource().compare("Discovery_SendFSM") != 0))
				{
					EventError* casted_ie = (EventError*) ie;
						pDiscovery_SendFSM->context->EventErrorTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Timeout") == 0 && (ie->getSource().compare("Discovery_SendFSM") != 0))
				{
					Timeout* casted_ie = (Timeout*) ie;
						pDiscovery_SendFSM->context->TimeoutTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("CommandCompleted") == 0 && (ie->getSource().compare("Discovery_SendFSM") != 0))
				{
					CommandCompleted* casted_ie = (CommandCompleted*) ie;
						pDiscovery_SendFSM->context->CommandCompletedTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("CommandExpired") == 0 && (ie->getSource().compare("Discovery_SendFSM") != 0))
				{
					CommandExpired* casted_ie = (CommandExpired*) ie;
						pDiscovery_SendFSM->context->CommandExpiredTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Send") == 0 && (ie->getSource().compare("Discovery_SendFSM") != 0))
				{
					Send* casted_ie = (Send*) ie;
						pDiscovery_SendFSM->context->SendTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("BroadcastLocal") == 0 && (ie->getSource().compare("Discovery_SendFSM") != 0))
				{
					BroadcastLocal* casted_ie = (BroadcastLocal*) ie;
						pDiscovery_SendFSM->context->BroadcastLocalTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("BroadcastGlobal") == 0 && (ie->getSource().compare("Discovery_SendFSM") != 0))
				{
					BroadcastGlobal* casted_ie = (BroadcastGlobal*) ie;
						pDiscovery_SendFSM->context->BroadcastGlobalTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

	
leave:
   mutex.unlock();
   return done;
}


};
