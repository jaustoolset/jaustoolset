/** 
 *  This Promela file was auto-generated using JTS on Thu Apr 14 11:45:34 EDT 2011.
 *  ID=MAIN  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

/** 
 *  This is the main model file and will contain instances of all message channels and state machines.
 */ 

#include "userEditableConfig.pml"
#include "urn_jts_DefaultStateTest_static.pml"

// Create channels for all the messages and events.
#include "channels.pml"


// These includes should come after message and event channels are declared.
#include "userEditableEvents.pml"
#include "userEditableGuardsAndActions.pml"
#include "userEditableClients.pml"


// starting state machine definition : DefaultStateFSM
active proctype DefaultStateFSM(){
	pid incoming_pid = 0;
	pid current_client_pid = 0;
	urn_jts_DefaultStateTest_Message2 urn_jts_DefaultStateTest_Message2_inst;
	urn_jts_DefaultStateTest_Message3 urn_jts_DefaultStateTest_Message3_inst;
	urn_jts_DefaultStateTest_Message1 urn_jts_DefaultStateTest_Message1_inst;
goto DefaultStateFSM_State18;
	// Start Entry Actions
	DefaultStateFSM_State18:
	do
		:: true ->
		goto DefaultStateFSM_State18_IMPL;
	od;
	// End Entry Actions
	DefaultStateFSM_State18_IMPL:
	do
		:: Message1 ? incoming_pid, urn_jts_DefaultStateTest_Message1_inst ->
			if
			::  Guard() ->
				goto DefaultStateFSM_State19;
			fi
			unless {
			if
			// unguarded transition 
			:: true ->
				goto DefaultStateFSM_State19;
			fi
			}
		:: Message2 ? incoming_pid, urn_jts_DefaultStateTest_Message2_inst ->
			if
			// unguarded transition 
			:: true ->
				goto DefaultStateFSM_State19;
			fi
			
	od;
	// Start Entry Actions
	DefaultStateFSM_State19:
	do
		:: true ->
		goto DefaultStateFSM_State19_IMPL;
	od;
	// End Entry Actions
	DefaultStateFSM_State19_IMPL:
	do
		:: Message3 ? incoming_pid, urn_jts_DefaultStateTest_Message3_inst ->
			if
			// unguarded transition 
			:: true ->
				goto DefaultStateFSM_State18;
			fi
			
		:: Message1 ? incoming_pid, urn_jts_DefaultStateTest_Message1_inst ->
			if
			::  Guard() ->
				goto DefaultStateFSM_State19;
			fi
			
		:: Message2 ? incoming_pid, urn_jts_DefaultStateTest_Message2_inst ->
			if
			// unguarded transition 
			:: true ->
				goto DefaultStateFSM_State19;
			fi
			
	od;
}
// ending state machine definition : DefaultStateFSM
