/** 
 *  This Promela file was auto-generated using JTS on Wed Apr 13 16:18:33 EDT 2011.
 *  ID=MAIN  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

/** 
 *  This is the main model file and will contain instances of all message channels and state machines.
 */ 

#include "userEditableConfig.pml"
#include "urn_jts_ProtocolBehaviorTest_static.pml"

// Create channels for all the messages and events.
#include "channels.pml"


// These includes should come after message and event channels are declared.
#include "userEditableEvents.pml"
#include "userEditableGuardsAndActions.pml"
#include "userEditableClients.pml"


// starting state machine definition : ProtocolInternalEventsFSM
active proctype ProtocolInternalEventsFSM(){
	pid incoming_pid = 0;
	pid current_client_pid = 0;
goto ProtocolInternalEventsFSM_ReceivingEvents;
	// Start Entry Actions
	ProtocolInternalEventsFSM_ReceivingEvents:
	do
		:: true ->
		goto ProtocolInternalEventsFSM_ReceivingEvents_IMPL;
	od;
	// End Entry Actions
	ProtocolInternalEventsFSM_ReceivingEvents_IMPL:
	do
		:: TestEvent ? incoming_pid ->
			if
			// unguarded transition 
			:: true ->
				Action_printTestEvent();
			fi
			
	od;
}
// ending state machine definition : ProtocolInternalEventsFSM
// starting state machine definition : ProtocolMessagesFSM
active proctype ProtocolMessagesFSM(){
	pid incoming_pid = 0;
	pid current_client_pid = 0;
	urn_jts_ProtocolBehaviorTest_Message1 urn_jts_ProtocolBehaviorTest_Message1_inst;
goto ProtocolMessagesFSM_ReceivingMessages;
	// Start Entry Actions
	ProtocolMessagesFSM_ReceivingMessages:
	do
		:: true ->
		goto ProtocolMessagesFSM_ReceivingMessages_IMPL;
	od;
	// End Entry Actions
	ProtocolMessagesFSM_ReceivingMessages_IMPL:
	do
		:: Message1 ? incoming_pid, urn_jts_ProtocolBehaviorTest_Message1_inst ->
			if
			// unguarded transition 
			:: true ->
				Action_printMessage();
			fi
			
	od;
}
// ending state machine definition : ProtocolMessagesFSM
