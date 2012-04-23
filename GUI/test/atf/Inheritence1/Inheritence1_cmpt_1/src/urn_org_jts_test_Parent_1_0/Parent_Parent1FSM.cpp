

#include "urn_org_jts_test_Parent_1_0/Parent_Parent1FSM.h"
#include "TestHarness.h"




using namespace JTS;

namespace urn_org_jts_test_Parent_1_0
{

extern "C" unsigned long parent_action = 0;


Parent_Parent1FSM::Parent_Parent1FSM()
{

	/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
	 */
	context = new Parent_Parent1FSMContext(*this);
}



Parent_Parent1FSM::~Parent_Parent1FSM() 
{
	delete context;
}

void Parent_Parent1FSM::setupNotifications()
{

}

void Parent_Parent1FSM::Action_Top1_To_Top2Action()
{
	parent_action |= CAction_Top1_To_Top2Action;
}

void Parent_Parent1FSM::Top2_EntryAction()
{
	parent_action |= CTop2_EntryAction;
}

void Parent_Parent1FSM::Top2_ExitAction()
{
	parent_action |= CTop2_ExitAction;
}





};
