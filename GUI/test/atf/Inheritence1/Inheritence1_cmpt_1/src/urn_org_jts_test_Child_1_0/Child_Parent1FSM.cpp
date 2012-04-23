

#include "urn_org_jts_test_Child_1_0/Child_Parent1FSM.h"
#include "TestHarness.h"




using namespace JTS;

namespace urn_org_jts_test_Child_1_0
{

extern "C" unsigned long child_action = 0;


Child_Parent1FSM::Child_Parent1FSM(urn_org_jts_test_Parent_1_0::Parent_Parent1FSM* pParent_Parent1FSM, urn_org_jts_test_Intermediary_1_0::Intermediary_Parent1FSM* pIntermediary_Parent1FSM)
{

	/*
	 * If there are other variables, context must be constructed last so that all 
	 * class variables are available if an EntryAction of the InitialState of the 
	 * statemachine needs them. 
	 */
	context = new Child_Parent1FSMContext(*this);

	this->pParent_Parent1FSM = pParent_Parent1FSM;
	this->pIntermediary_Parent1FSM = pIntermediary_Parent1FSM;
}



Child_Parent1FSM::~Child_Parent1FSM() 
{
	delete context;
}

void Child_Parent1FSM::setupNotifications()
{
	pIntermediary_Parent1FSM->registerNotification("Top1_Intermediary1", ieHandler, "InternalStateChange_To_Child_Parent1FSM_Top1_Intermediary1", "Intermediary_Parent1FSM");
	pIntermediary_Parent1FSM->registerNotification("Top1", ieHandler, "InternalStateChange_To_Child_Parent1FSM_Top1_Intermediary1", "Intermediary_Parent1FSM");
	pIntermediary_Parent1FSM->registerNotification("Top2_Intermediary1", ieHandler, "InternalStateChange_To_Child_Parent1FSM_Top2_Intermediary1", "Intermediary_Parent1FSM");
	pIntermediary_Parent1FSM->registerNotification("Top2_Intermediary2", ieHandler, "InternalStateChange_To_Child_Parent1FSM_Top2_Intermediary2", "Intermediary_Parent1FSM");
	pIntermediary_Parent1FSM->registerNotification("Top2", ieHandler, "InternalStateChange_To_Child_Parent1FSM_Top2_Intermediary1", "Intermediary_Parent1FSM");
	registerNotification("Top1_Intermediary1", pIntermediary_Parent1FSM->getHandler(), "InternalStateChange_To_Intermediary_Parent1FSM_Top1_Intermediary1", "Child_Parent1FSM");
	registerNotification("Top1", pIntermediary_Parent1FSM->getHandler(), "InternalStateChange_To_Intermediary_Parent1FSM_Top1", "Child_Parent1FSM");
	registerNotification("Top2_Intermediary1", pIntermediary_Parent1FSM->getHandler(), "InternalStateChange_To_Intermediary_Parent1FSM_Top2_Intermediary1", "Child_Parent1FSM");
	registerNotification("Top2_Intermediary2", pIntermediary_Parent1FSM->getHandler(), "InternalStateChange_To_Intermediary_Parent1FSM_Top2_Intermediary2", "Child_Parent1FSM");
	registerNotification("Top2", pIntermediary_Parent1FSM->getHandler(), "InternalStateChange_To_Intermediary_Parent1FSM_Top2", "Child_Parent1FSM");

}

void Child_Parent1FSM::DefaultTransitionAction()
{
	child_action |= CDefaultTransitionAction;
}

void Child_Parent1FSM::Top2Intermediary1_toTop1Intermediary1Action()
{
	child_action |= CTop2Intermediary1_toTop1Intermediary1Action;
}





};
