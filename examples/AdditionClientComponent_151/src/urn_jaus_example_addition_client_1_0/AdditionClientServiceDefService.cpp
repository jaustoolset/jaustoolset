

#include "urn_jaus_example_addition_client_1_0/AdditionClientServiceDefService.h"

using namespace JTS;

namespace urn_jaus_example_addition_client_1_0
{
	
AdditionClientServiceDefService::AdditionClientServiceDefService(JTS::JausRouter* jausRouter ) : Service()
{
	jausRouter->setTransportType(JausRouter::Version_1_0);
	
	pAdditionClientServiceDef_additionClientFSM = new AdditionClientServiceDef_additionClientFSM();
	pAdditionClientServiceDef_additionClientFSM->setHandlers(ieHandler, jausRouter);
	pAdditionClientServiceDef_additionClientFSM->setupNotifications();

}


AdditionClientServiceDefService::~AdditionClientServiceDefService()
{
	delete pAdditionClientServiceDef_additionClientFSM;

}

/**
 *	This is the function that will actually be run by the thread at start-up.
 *  We override it from EventReceiver in order to handle any entry
 *  actions defined by the initial state. 
 */
void AdditionClientServiceDefService::run()
{
	
	/// Perform any entry actions specified by the start state.
	pAdditionClientServiceDef_additionClientFSM->serviceStartedAction();

	
	/// Kick-off the receive loop...
	EventReceiver::run();
}

/**
 *	This is the function that will process an event either generated
 *  by the service, sent to it by another service on the same component,
 *  or as a message sent by a different component. 
 */
bool AdditionClientServiceDefService::processTransitions(InternalEvent* ie)
{
        bool done = false;

   // Since this function can be called from multiple threads,
   // we use a mutex to ensure only one state transition is
   // active at a time.
   mutex.lock();

			// Invoke the FSM transition for this event.
			try
			{
				if (ie->getName().compare("InitToReadyEventDef") == 0 && (ie->getSource().compare("AdditionClientServiceDef_additionClientFSM") != 0) && (done == false))
				{
					InitToReadyEventDef* casted_ie = (InitToReadyEventDef*) ie;
						pAdditionClientServiceDef_additionClientFSM->context->InitToReadyEventDefTransition();
						done = true;
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("InternalStateChange_To_AdditionClientServiceDef_additionClientFSM_Ready") == 0 && (ie->getSource().compare("AdditionClientServiceDef_additionClientFSM") != 0) && (done == false))
				{
						pAdditionClientServiceDef_additionClientFSM->context->InternalStateChange_To_AdditionClientServiceDef_additionClientFSM_ReadyTransition(ie);
						done = true;
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("AdditionClientServiceDef_additionClientFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == ReportAddition::ID)
					{
						ReportAddition msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pAdditionClientServiceDef_additionClientFSM->context->ReportAdditionTransition(msg);
						done = true;
					}
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("InternalStateChange_To_AdditionClientServiceDef_additionClientFSM_Init") == 0 && (ie->getSource().compare("AdditionClientServiceDef_additionClientFSM") != 0) && (done == false))
				{
						pAdditionClientServiceDef_additionClientFSM->context->InternalStateChange_To_AdditionClientServiceDef_additionClientFSM_InitTransition(ie);
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
bool AdditionClientServiceDefService::defaultTransitions(InternalEvent* ie)
{
   bool done = false;

   // Since this function can be called from multiple threads,
   // we use a mutex to ensure only one state transition is
   // active at a time.
   mutex.lock();

			// Invoke the FSM transition for this event.
			try
			{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("AdditionClientServiceDef_additionClientFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == ReportAddition::ID)
					{
						ReportAddition msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pAdditionClientServiceDef_additionClientFSM->context->ReportAdditionTransition();
						done = true;
		}
				}
			} catch (...) {}

			try
		{
				if (ie->getName().compare("InitToReadyEventDef") == 0 && (ie->getSource().compare("AdditionClientServiceDef_additionClientFSM") != 0) && (done == false))
				{
					InitToReadyEventDef* casted_ie = (InitToReadyEventDef*) ie;
						pAdditionClientServiceDef_additionClientFSM->context->InitToReadyEventDefTransition();
						done = true;
		}
			} catch (...) {}

	

   mutex.unlock();
   return done;
}


};
