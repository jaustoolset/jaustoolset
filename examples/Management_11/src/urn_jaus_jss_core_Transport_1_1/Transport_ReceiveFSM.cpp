

#include "urn_jaus_jss_core_Transport_1_1/Transport_ReceiveFSM.h"




using namespace JTS;

namespace urn_jaus_jss_core_Transport_1_1
{



Transport_ReceiveFSM::Transport_ReceiveFSM()
{

	/*
	 * Context has to be constructed last so that all class variables are 
	 * available if an EntryAction of the InitialState of the statemachine 
	 * needs them. 
	 */
	context = new Transport_ReceiveFSMContext(*this);

}



Transport_ReceiveFSM::~Transport_ReceiveFSM() 
{
	delete context;
}

void Transport_ReceiveFSM::setupNotifications()
{

}





};
