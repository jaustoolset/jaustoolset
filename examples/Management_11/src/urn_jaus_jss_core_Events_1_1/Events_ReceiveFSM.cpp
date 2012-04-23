

#include "urn_jaus_jss_core_Events_1_1/Events_ReceiveFSM.h"




using namespace JTS;

namespace urn_jaus_jss_core_Events_1_1
{



Events_ReceiveFSM::Events_ReceiveFSM(urn_jaus_jss_core_Transport_1_1::Transport_ReceiveFSM* pTransport_ReceiveFSM)
{

	/*
	 * Context has to be constructed last so that all class variables are 
	 * available if an EntryAction of the InitialState of the statemachine 
	 * needs them. 
	 */
	context = new Events_ReceiveFSMContext(*this);

	this->pTransport_ReceiveFSM = pTransport_ReceiveFSM;
}



Events_ReceiveFSM::~Events_ReceiveFSM() 
{
	delete context;
}

void Events_ReceiveFSM::setupNotifications()
{
	pTransport_ReceiveFSM->registerNotification("Receiving", ieHandler, "InternalStateChange_To_Events_ReceiveFSM_Receiving_Ready", "Transport_ReceiveFSM");
	registerNotification("Receiving_Ready", pTransport_ReceiveFSM->getHandler(), "InternalStateChange_To_Transport_ReceiveFSM_Receiving", "Events_ReceiveFSM");
	registerNotification("Receiving", pTransport_ReceiveFSM->getHandler(), "InternalStateChange_To_Transport_ReceiveFSM_Receiving", "Events_ReceiveFSM");

}

void Events_ReceiveFSM::SendAction(std::string arg0)
{
	/// Insert User Code HERE
}

void Events_ReceiveFSM::SendAction(std::string arg0, CancelEvent msg, Receive::Body::ReceiveRec transportData)
{
	/// Insert User Code HERE
}

void Events_ReceiveFSM::SendAction(std::string arg0, CreateEvnt msg, Receive::Body::ReceiveRec transportData)
{
	/// Insert User Code HERE
}

void Events_ReceiveFSM::SendAction(std::string arg0, QueryEventTimeout msg, Receive::Body::ReceiveRec transportData)
{
	/// Insert User Code HERE
}

void Events_ReceiveFSM::SendAction(std::string arg0, QueryEvents msg, Receive::Body::ReceiveRec transportData)
{
	/// Insert User Code HERE
}

void Events_ReceiveFSM::SendAction(std::string arg0, UpdateEvent msg, Receive::Body::ReceiveRec transportData)
{
	/// Insert User Code HERE
}

void Events_ReceiveFSM::cancelEventAction()
{
	/// Insert User Code HERE
}

void Events_ReceiveFSM::cancelEventAction(CancelEvent msg)
{
	/// Insert User Code HERE
}

void Events_ReceiveFSM::createEventAction(CreateEvnt msg)
{
	/// Insert User Code HERE
}

void Events_ReceiveFSM::resetEventTimerAction()
{
	/// Insert User Code HERE
}

void Events_ReceiveFSM::sendEventAction()
{
	/// Insert User Code HERE
}

void Events_ReceiveFSM::sendRejectEventRequestAction()
{
	/// Insert User Code HERE
}

void Events_ReceiveFSM::stopEventTimerAction()
{
	/// Insert User Code HERE
}

void Events_ReceiveFSM::updateEventAction(CreateEvnt msg)
{
	/// Insert User Code HERE
}

void Events_ReceiveFSM::updateEventAction(UpdateEvent msg)
{
	/// Insert User Code HERE
}



bool Events_ReceiveFSM::eventExists()
{
	/// Insert User Code HERE
	return false;
}
bool Events_ReceiveFSM::eventExists(CancelEvent msg)
{
	/// Insert User Code HERE
	return false;
}
bool Events_ReceiveFSM::eventExists(CreateEvnt msg)
{
	/// Insert User Code HERE
	return false;
}
bool Events_ReceiveFSM::eventExists(UpdateEvent msg)
{
	/// Insert User Code HERE
	return false;
}
bool Events_ReceiveFSM::isSupported(CreateEvnt msg)
{
	/// Insert User Code HERE
	return false;
}
bool Events_ReceiveFSM::isSupported(UpdateEvent msg)
{
	/// Insert User Code HERE
	return false;
}


};
