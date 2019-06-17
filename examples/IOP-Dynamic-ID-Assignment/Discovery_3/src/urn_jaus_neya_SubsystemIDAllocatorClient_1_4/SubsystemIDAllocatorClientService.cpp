

#include "urn_jaus_neya_SubsystemIDAllocatorClient_1_4/SubsystemIDAllocatorClientService.h"

using namespace JTS;

namespace urn_jaus_neya_SubsystemIDAllocatorClient_1_4
{
	
SubsystemIDAllocatorClientService::SubsystemIDAllocatorClientService(JTS::JausRouter* jausRouter , urn_jaus_jss_core_Transport_1_1::TransportService* pTransportService) : Service()
{
	jausRouter->setTransportType(JausRouter::Version_1_1);
	
	pSubsystemIDAllocatorClient_ReceiveFSM = new SubsystemIDAllocatorClient_ReceiveFSM(pTransportService->pTransport_ReceiveFSM);
	pSubsystemIDAllocatorClient_ReceiveFSM->setHandlers(ieHandler, jausRouter);
	pSubsystemIDAllocatorClient_ReceiveFSM->setupNotifications();
	pSubsystemIDAllocatorClient_SendFSM = new SubsystemIDAllocatorClient_SendFSM(pTransportService->pTransport_SendFSM);
	pSubsystemIDAllocatorClient_SendFSM->setHandlers(ieHandler, jausRouter);
	pSubsystemIDAllocatorClient_SendFSM->setupNotifications();

}


SubsystemIDAllocatorClientService::~SubsystemIDAllocatorClientService()
{
	delete pSubsystemIDAllocatorClient_ReceiveFSM;
	delete pSubsystemIDAllocatorClient_SendFSM;

}

/**
 *	This is the function that will actually be run by the thread at start-up.
 *  We override it from EventReceiver in order to handle any entry
 *  actions defined by the initial state. 
 */
void SubsystemIDAllocatorClientService::run()
{
	
	/// Perform any entry actions specified by the start state.
	pSubsystemIDAllocatorClient_ReceiveFSM->startPeriodicTimerAction();

	
	/// Kick-off the receive loop...
	EventReceiver::run();
}

/**
 *	This is the function that will process an event either generated
 *  by the service, sent to it by another service on the same component,
 *  or as a message sent by a different component. 
 */
bool SubsystemIDAllocatorClientService::processTransitions(InternalEvent* ie)
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

					if ((id != GrantSubsystemID::ID))
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("SubsystemIDAllocatorClient_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == GrantSubsystemID::ID)
					{
						GrantSubsystemID msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						Receive::Body::ReceiveRec transportData = *(casted_ie->getBody()->getReceiveRec());
						pSubsystemIDAllocatorClient_ReceiveFSM->context->ReceiveTransition(msg, transportData);
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
bool SubsystemIDAllocatorClientService::defaultTransitions(InternalEvent* ie)
{
   bool done = false;

   // Since this function can be called from multiple threads,
   // we use a mutex to ensure only one state transition is
   // active at a time.
   mutex.lock();

			// Invoke the FSM transition for this event.
			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("SubsystemIDAllocatorClient_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == GrantSubsystemID::ID)
					{
						GrantSubsystemID msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pSubsystemIDAllocatorClient_ReceiveFSM->context->GrantSubsystemIDTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Send") == 0 && (ie->getSource().compare("SubsystemIDAllocatorClient_ReceiveFSM") != 0))
				{
					Send* casted_ie = (Send*) ie;
						pSubsystemIDAllocatorClient_ReceiveFSM->context->SendTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("BroadcastLocal") == 0 && (ie->getSource().compare("SubsystemIDAllocatorClient_ReceiveFSM") != 0))
				{
					BroadcastLocal* casted_ie = (BroadcastLocal*) ie;
						pSubsystemIDAllocatorClient_ReceiveFSM->context->BroadcastLocalTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("BroadcastGlobal") == 0 && (ie->getSource().compare("SubsystemIDAllocatorClient_ReceiveFSM") != 0))
				{
					BroadcastGlobal* casted_ie = (BroadcastGlobal*) ie;
						pSubsystemIDAllocatorClient_ReceiveFSM->context->BroadcastGlobalTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("SubsystemIDAllocatorClient_SendFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == GrantSubsystemID::ID)
					{
						GrantSubsystemID msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pSubsystemIDAllocatorClient_SendFSM->context->GrantSubsystemIDTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Send") == 0 && (ie->getSource().compare("SubsystemIDAllocatorClient_SendFSM") != 0))
				{
					Send* casted_ie = (Send*) ie;
						pSubsystemIDAllocatorClient_SendFSM->context->SendTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("BroadcastLocal") == 0 && (ie->getSource().compare("SubsystemIDAllocatorClient_SendFSM") != 0))
				{
					BroadcastLocal* casted_ie = (BroadcastLocal*) ie;
						pSubsystemIDAllocatorClient_SendFSM->context->BroadcastLocalTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("BroadcastGlobal") == 0 && (ie->getSource().compare("SubsystemIDAllocatorClient_SendFSM") != 0))
				{
					BroadcastGlobal* casted_ie = (BroadcastGlobal*) ie;
						pSubsystemIDAllocatorClient_SendFSM->context->BroadcastGlobalTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

	
leave:
   mutex.unlock();
   return done;
}


};
