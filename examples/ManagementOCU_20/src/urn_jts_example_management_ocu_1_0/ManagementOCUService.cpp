

#include "urn_jts_example_management_ocu_1_0/ManagementOCUService.h"

using namespace JTS;

namespace urn_jts_example_management_ocu_1_0
{
	
ManagementOCUService::ManagementOCUService(JTS::JausRouter* jausRouter ) : Service()
{
	jausRouter->setTransportType(JausRouter::Version_1_0);
	
	pManagementOCU_ManagementOcuFSM = new ManagementOCU_ManagementOcuFSM();
	pManagementOCU_ManagementOcuFSM->setHandlers(ieHandler, jausRouter);
	pManagementOCU_ManagementOcuFSM->setupNotifications();

}


ManagementOCUService::~ManagementOCUService()
{
	delete pManagementOCU_ManagementOcuFSM;

}

/**
 *	This is the function that will actually be run by the thread at start-up.
 *  We override it from EventReceiver in order to handle any entry
 *  actions defined by the initial state. 
 */
void ManagementOCUService::run()
{
	
	/// Perform any entry actions specified by the start state.
	pManagementOCU_ManagementOcuFSM->sendRequestControlAction();
	pManagementOCU_ManagementOcuFSM->displayMenuAction();

	
	/// Kick-off the receive loop...
	EventReceiver::run();
}

/**
 *	This is the function that will process an event either generated
 *  by the service, sent to it by another service on the same component,
 *  or as a message sent by a different component. 
 */
bool ManagementOCUService::processTransitions(InternalEvent* ie)
{
        bool done = false;

   // Since this function can be called from multiple threads,
   // we use a mutex to ensure only one state transition is
   // active at a time.
   mutex.lock();

			// Invoke the FSM transition for this event.
			try
			{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("ManagementOCU_ManagementOcuFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == ConfirmControl::ID)
					{
						ConfirmControl msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pManagementOCU_ManagementOcuFSM->context->ConfirmControlTransition(msg);
						done = true;
					}
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("MenuItemEntered") == 0 && (ie->getSource().compare("ManagementOCU_ManagementOcuFSM") != 0) && (done == false))
				{
					MenuItemEntered* casted_ie = (MenuItemEntered*) ie;
						MenuItemEntered msg = *casted_ie;
						pManagementOCU_ManagementOcuFSM->context->MenuItemEnteredTransition(msg);
						done = true;
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("MenuItemEntered") == 0 && (ie->getSource().compare("ManagementOCU_ManagementOcuFSM") != 0) && (done == false))
				{
					MenuItemEntered* casted_ie = (MenuItemEntered*) ie;
						MenuItemEntered msg = *casted_ie;
						pManagementOCU_ManagementOcuFSM->context->MenuItemEnteredTransition(msg);
						done = true;
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("ManagementOCU_ManagementOcuFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == ConfirmControl::ID)
					{
						ConfirmControl msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pManagementOCU_ManagementOcuFSM->context->ConfirmControlTransition(msg);
						done = true;
					}
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("InternalStateChange_To_ManagementOCU_ManagementOcuFSM_HandleMenu") == 0 && (ie->getSource().compare("ManagementOCU_ManagementOcuFSM") != 0) && (done == false))
				{
						pManagementOCU_ManagementOcuFSM->context->InternalStateChange_To_ManagementOCU_ManagementOcuFSM_HandleMenuTransition(ie);
						done = true;
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("MenuItemEntered") == 0 && (ie->getSource().compare("ManagementOCU_ManagementOcuFSM") != 0) && (done == false))
				{
					MenuItemEntered* casted_ie = (MenuItemEntered*) ie;
						MenuItemEntered msg = *casted_ie;
						pManagementOCU_ManagementOcuFSM->context->MenuItemEnteredTransition(msg);
						done = true;
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("MenuItemEntered") == 0 && (ie->getSource().compare("ManagementOCU_ManagementOcuFSM") != 0) && (done == false))
				{
					MenuItemEntered* casted_ie = (MenuItemEntered*) ie;
						MenuItemEntered msg = *casted_ie;
						pManagementOCU_ManagementOcuFSM->context->MenuItemEnteredTransition(msg);
						done = true;
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("ManagementOCU_ManagementOcuFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == RejectControl::ID)
					{
						RejectControl msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pManagementOCU_ManagementOcuFSM->context->RejectControlTransition(msg);
						done = true;
					}
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("ManagementOCU_ManagementOcuFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == ReportStatus::ID)
					{
						ReportStatus msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pManagementOCU_ManagementOcuFSM->context->ReportStatusTransition(msg);
						done = true;
					}
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("InternalStateChange_To_ManagementOCU_ManagementOcuFSM_WaitForControl") == 0 && (ie->getSource().compare("ManagementOCU_ManagementOcuFSM") != 0) && (done == false))
				{
						pManagementOCU_ManagementOcuFSM->context->InternalStateChange_To_ManagementOCU_ManagementOcuFSM_WaitForControlTransition(ie);
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
bool ManagementOCUService::defaultTransitions(InternalEvent* ie)
{
   bool done = false;

   // Since this function can be called from multiple threads,
   // we use a mutex to ensure only one state transition is
   // active at a time.
   mutex.lock();

			// Invoke the FSM transition for this event.
			try
			{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("ManagementOCU_ManagementOcuFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == ReportStatus::ID)
					{
						ReportStatus msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pManagementOCU_ManagementOcuFSM->context->ReportStatusTransition();
						done = true;
		}
				}
			} catch (...) {}

			try
		{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("ManagementOCU_ManagementOcuFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == ConfirmControl::ID)
					{
						ConfirmControl msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pManagementOCU_ManagementOcuFSM->context->ConfirmControlTransition();
						done = true;
		}
	}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("ManagementOCU_ManagementOcuFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == ReportControl::ID)
					{
						ReportControl msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pManagementOCU_ManagementOcuFSM->context->ReportControlTransition();
						done = true;
					}
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("Receive") == 0 && (ie->getSource().compare("ManagementOCU_ManagementOcuFSM") != 0) && (done == false))
				{
					Receive* casted_ie = (Receive*) ie;
					unsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
					if ( id == RejectControl::ID)
					{
						RejectControl msg;
						msg.decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());
						pManagementOCU_ManagementOcuFSM->context->RejectControlTransition();
						done = true;
					}
				}
			} catch (...) {}

			try
			{
				if (ie->getName().compare("MenuItemEntered") == 0 && (ie->getSource().compare("ManagementOCU_ManagementOcuFSM") != 0) && (done == false))
				{
					MenuItemEntered* casted_ie = (MenuItemEntered*) ie;
						pManagementOCU_ManagementOcuFSM->context->MenuItemEnteredTransition();
						done = true;
				}
			} catch (...) {}

	

   mutex.unlock();
   return done;
}


};
