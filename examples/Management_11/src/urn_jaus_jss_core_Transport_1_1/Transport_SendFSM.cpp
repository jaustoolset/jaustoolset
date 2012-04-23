

#include "urn_jaus_jss_core_Transport_1_1/Transport_SendFSM.h"




using namespace JTS;

namespace urn_jaus_jss_core_Transport_1_1
{



Transport_SendFSM::Transport_SendFSM()
{

	/*
	 * Context has to be constructed last so that all class variables are 
	 * available if an EntryAction of the InitialState of the statemachine 
	 * needs them. 
	 */
	context = new Transport_SendFSMContext(*this);

}



Transport_SendFSM::~Transport_SendFSM() 
{
	delete context;
}

void Transport_SendFSM::setupNotifications()
{

}

void Transport_SendFSM::BroadcastGlobalEnqueueAction(BroadcastGlobal msg)
{
	sendJausMessage(
		msg.getBody()->getBroadcastRec()->getMessagePayload()->getLength(),
		msg.getBody()->getBroadcastRec()->getMessagePayload()->getData(),
		JausAddress(0xFFFF, 0xFF, 0xFF));
}

void Transport_SendFSM::BroadcastLocalEnqueueAction(BroadcastLocal msg)
{
	sendJausMessage(
		msg.getBody()->getBroadcastRec()->getMessagePayload()->getLength(),
		msg.getBody()->getBroadcastRec()->getMessagePayload()->getData(),
		JausAddress(jausRouter->getJausAddress()->getSubsystemID(), 0xFF, 0xFF));
}

void Transport_SendFSM::EnqueueAction(Send msg)
{
	sendJausMessage(
		msg.getBody()->getSendRec()->getMessagePayload()->getLength(),
		msg.getBody()->getSendRec()->getMessagePayload()->getData(),
		JausAddress(msg.getBody()->getSendRec()->getDestinationID()->getSubsystemID(),
			msg.getBody()->getSendRec()->getDestinationID()->getNodeID(),
			msg.getBody()->getSendRec()->getDestinationID()->getComponentID()));
}





};
