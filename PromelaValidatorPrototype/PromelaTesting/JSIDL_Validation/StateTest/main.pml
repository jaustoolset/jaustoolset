/** 
 *  This Promela file was auto-generated using JTS on Wed Apr 13 15:56:19 EDT 2011.
 *  ID=MAIN  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

/** 
 *  This is the main model file and will contain instances of all message channels and state machines.
 */ 

#include "userEditableConfig.pml"
#include "urn_jts_StateTest_static.pml"

// Create channels for all the messages and events.
#include "channels.pml"


// These includes should come after message and event channels are declared.
#include "userEditableEvents.pml"
#include "userEditableGuardsAndActions.pml"
#include "userEditableClients.pml"


// starting state machine definition : StateTest
active proctype StateTest(){
	pid incoming_pid = 0;
	pid current_client_pid = 0;
	urn_jts_StateTest_Message2 urn_jts_StateTest_Message2_inst;
	urn_jts_StateTest_Message1 urn_jts_StateTest_Message1_inst;
goto StateTest_ParentState_ChildState1;
	// Start Entry Actions
	StateTest_ParentState:
	do
		:: true ->
		goto StateTest_ParentState_IMPL;
	od;
	// End Entry Actions
	StateTest_ParentState_IMPL:
	do
		::
		// Start Entry Actions
		StateTest_ParentState_ChildState1:
		do
			:: true ->
			goto StateTest_ParentState_ChildState1_IMPL;
		od;
		// End Entry Actions
		StateTest_ParentState_ChildState1_IMPL:
		do
			:: Message2 ? incoming_pid, urn_jts_StateTest_Message2_inst ->
				if
				// unguarded transition 
				:: true ->
					goto StateTest_FinalState;
				fi
				
			:: Message1 ? incoming_pid, urn_jts_StateTest_Message1_inst ->
				if
				// unguarded transition 
				:: true ->
					goto StateTest_ParentState_ChildState2;
				fi
				
		od;
		::
		// Start Entry Actions
		StateTest_ParentState_ChildState2:
		do
			:: true ->
			goto StateTest_ParentState_ChildState2_IMPL;
		od;
		// End Entry Actions
		StateTest_ParentState_ChildState2_IMPL:
		do
			:: Message2 ? incoming_pid, urn_jts_StateTest_Message2_inst ->
				if
				// unguarded transition 
				:: true ->
					goto StateTest_FinalState;
				fi
				
		od;
	od;
	// Start Entry Actions
	StateTest_FinalState:
	do
		:: true ->
		goto StateTest_FinalState_IMPL;
	od;
	// End Entry Actions
	StateTest_FinalState_IMPL:
	do
		:: printf("Empty state expected here, based on JSIDL definition.")
	od;
}
// ending state machine definition : StateTest
