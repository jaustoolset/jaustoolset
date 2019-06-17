

#include "urn_jaus_jss_iop_NodeIDAllocator_1_1/NodeIDAllocatorService.h"

using namespace JTS;

namespace urn_jaus_jss_iop_NodeIDAllocator_1_1
{
	
NodeIDAllocatorService::NodeIDAllocatorService(JTS::JausRouter* jausRouter , urn_jaus_jss_core_Transport_1_1::TransportService* pTransportService) : Service()
{
	jausRouter->setTransportType(JausRouter::Version_1_1);
	
	pNodeIDAllocator_ReceiveFSM = new NodeIDAllocator_ReceiveFSM(pTransportService->pTransport_ReceiveFSM);
	pNodeIDAllocator_ReceiveFSM->setHandlers(ieHandler, jausRouter);
	pNodeIDAllocator_ReceiveFSM->setupNotifications();
	pNodeIDAllocator_SendFSM = new NodeIDAllocator_SendFSM(pTransportService->pTransport_SendFSM);
	pNodeIDAllocator_SendFSM->setHandlers(ieHandler, jausRouter);
	pNodeIDAllocator_SendFSM->setupNotifications();

}


NodeIDAllocatorService::~NodeIDAllocatorService()
{
	delete pNodeIDAllocator_ReceiveFSM;
	delete pNodeIDAllocator_SendFSM;

}

/**
 *	This is the function that will actually be run by the thread at start-up.
 *  We override it from EventReceiver in order to handle any entry
 *  actions defined by the initial state. 
 */
void NodeIDAllocatorService::run()
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
bool NodeIDAllocatorService::processTransitions(InternalEvent* ie)
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

					if ((id != RequestNodeID::ID))
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("NodeIDAllocator_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == RequestNodeID::ID)
					{
						RequestNodeID msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						Receive::Body::ReceiveRec transportData = *(casted_ie->getBody()->getReceiveRec());
						pNodeIDAllocator_ReceiveFSM->context->ReceiveTransition(msg, transportData);
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
bool NodeIDAllocatorService::defaultTransitions(InternalEvent* ie)
{
   bool done = false;

   // Since this function can be called from multiple threads,
   // we use a mutex to ensure only one state transition is
   // active at a time.
   mutex.lock();

			// Invoke the FSM transition for this event.
			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("NodeIDAllocator_ReceiveFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == RequestNodeID::ID)
					{
						RequestNodeID msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pNodeIDAllocator_ReceiveFSM->context->RequestNodeIDTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Send") == 0 && (ie->getSource().compare("NodeIDAllocator_ReceiveFSM") != 0))
				{
					Send* casted_ie = (Send*) ie;
						pNodeIDAllocator_ReceiveFSM->context->SendTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("BroadcastLocal") == 0 && (ie->getSource().compare("NodeIDAllocator_ReceiveFSM") != 0))
				{
					BroadcastLocal* casted_ie = (BroadcastLocal*) ie;
						pNodeIDAllocator_ReceiveFSM->context->BroadcastLocalTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("BroadcastGlobal") == 0 && (ie->getSource().compare("NodeIDAllocator_ReceiveFSM") != 0))
				{
					BroadcastGlobal* casted_ie = (BroadcastGlobal*) ie;
						pNodeIDAllocator_ReceiveFSM->context->BroadcastGlobalTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Receive") == 0 && (ie->getSource().compare("NodeIDAllocator_SendFSM") != 0))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					id = JSIDL_v_1_0::correctEndianness(id);
					if ( id == RequestNodeID::ID)
					{
						RequestNodeID msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pNodeIDAllocator_SendFSM->context->RequestNodeIDTransition();
						done = true;
						goto leave;
					}
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("Send") == 0 && (ie->getSource().compare("NodeIDAllocator_SendFSM") != 0))
				{
					Send* casted_ie = (Send*) ie;
						pNodeIDAllocator_SendFSM->context->SendTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("BroadcastLocal") == 0 && (ie->getSource().compare("NodeIDAllocator_SendFSM") != 0))
				{
					BroadcastLocal* casted_ie = (BroadcastLocal*) ie;
						pNodeIDAllocator_SendFSM->context->BroadcastLocalTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

			try
			{
				if ((done == false) && ie->getName().compare("BroadcastGlobal") == 0 && (ie->getSource().compare("NodeIDAllocator_SendFSM") != 0))
				{
					BroadcastGlobal* casted_ie = (BroadcastGlobal*) ie;
						pNodeIDAllocator_SendFSM->context->BroadcastGlobalTransition();
						done = true;
						goto leave;
				}
			} catch (...) {}

	
leave:
   mutex.unlock();
   return done;
}


};
