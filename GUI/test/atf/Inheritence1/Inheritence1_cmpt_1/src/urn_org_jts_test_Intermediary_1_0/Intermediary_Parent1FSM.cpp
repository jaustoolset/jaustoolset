

#include "urn_org_jts_test_Intermediary_1_0/Intermediary_Parent1FSM.h"
#include "TestHarness.h"




using namespace JTS;

namespace urn_org_jts_test_Intermediary_1_0
{

extern "C" unsigned long intermediary_action = 0;


Intermediary_Parent1FSM::Intermediary_Parent1FSM(urn_org_jts_test_Parent_1_0::Parent_Parent1FSM* pParent_Parent1FSM)
{

	/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
	 */
	context = new Intermediary_Parent1FSMContext(*this);

	this->pParent_Parent1FSM = pParent_Parent1FSM;
}



Intermediary_Parent1FSM::~Intermediary_Parent1FSM() 
{
	delete context;
}

void Intermediary_Parent1FSM::setupNotifications()
{
	pParent_Parent1FSM->registerNotification("Top1", ieHandler, "InternalStateChange_To_Intermediary_Parent1FSM_Top1_Intermediary1", "Parent_Parent1FSM");
	pParent_Parent1FSM->registerNotification("Top2", ieHandler, "InternalStateChange_To_Intermediary_Parent1FSM_Top2_Intermediary1", "Parent_Parent1FSM");
	registerNotification("Top1_Intermediary1", pParent_Parent1FSM->getHandler(), "InternalStateChange_To_Parent_Parent1FSM_Top1", "Intermediary_Parent1FSM");
	registerNotification("Top1", pParent_Parent1FSM->getHandler(), "InternalStateChange_To_Parent_Parent1FSM_Top1", "Intermediary_Parent1FSM");
	registerNotification("Top2_Intermediary1", pParent_Parent1FSM->getHandler(), "InternalStateChange_To_Parent_Parent1FSM_Top2", "Intermediary_Parent1FSM");
	registerNotification("Top2_Intermediary2", pParent_Parent1FSM->getHandler(), "InternalStateChange_To_Parent_Parent1FSM_Top2", "Intermediary_Parent1FSM");
	registerNotification("Top2", pParent_Parent1FSM->getHandler(), "InternalStateChange_To_Parent_Parent1FSM_Top2", "Intermediary_Parent1FSM");

}

void Intermediary_Parent1FSM::Top1_Intermediary1_EntryAction()
{
	intermediary_action |= CTop1_Intermediary1_EntryAction;
}

void Intermediary_Parent1FSM::Top1_Intermediary1_ExitAction()
{
	intermediary_action |= CTop1_Intermediary1_ExitAction;
}

void Intermediary_Parent1FSM::Top2_Intermediary1_EntryAction()
{
	intermediary_action |= CTop2_Intermediary1_EntryAction;
}

void Intermediary_Parent1FSM::Top2_Intermediary1_ExitAction()
{
	intermediary_action |= CTop2_Intermediary1_ExitAction;
}

void Intermediary_Parent1FSM::Top2_Intermediary2_EntryAction()
{
	intermediary_action |= CTop2_Intermediary2_EntryAction;
}

void Intermediary_Parent1FSM::Top2_Intermediary2_ExitAction()
{
	intermediary_action |= CTop2_Intermediary2_ExitAction;
}





};
