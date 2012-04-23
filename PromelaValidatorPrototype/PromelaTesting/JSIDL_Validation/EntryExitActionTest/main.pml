/** 
 *  This Promela file was auto-generated using JTS on Wed Apr 13 15:59:46 EDT 2011.
 *  ID=MAIN  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

/** 
 *  This is the main model file and will contain instances of all message channels and state machines.
 */ 

#include "userEditableConfig.pml"
#include "urn_jts_EntryExitActionTest_static.pml"

// Create channels for all the messages and events.
#include "channels.pml"


// These includes should come after message and event channels are declared.
#include "userEditableEvents.pml"
#include "userEditableGuardsAndActions.pml"
#include "userEditableClients.pml"


// starting state machine definition : EntryExitActionFSM
active proctype EntryExitActionFSM(){
	pid incoming_pid = 0;
	pid current_client_pid = 0;
	urn_jts_EntryExitActionTest_PopMessage urn_jts_EntryExitActionTest_PopMessage_inst;
	urn_jts_EntryExitActionTest_Message1 urn_jts_EntryExitActionTest_Message1_inst;
	urn_jts_EntryExitActionTest_Message2 urn_jts_EntryExitActionTest_Message2_inst;
	urn_jts_EntryExitActionTest_PushMessage urn_jts_EntryExitActionTest_PushMessage_inst;
goto EntryExitActionFSM_State9;
	// Start Entry Actions
	EntryExitActionFSM_State9:
	do
		:: true ->
		goto EntryExitActionFSM_State9_IMPL;
	od;
	// End Entry Actions
	EntryExitActionFSM_State9_IMPL:
	do
		:: PushMessage ? incoming_pid, urn_jts_EntryExitActionTest_PushMessage_inst ->
			if
			// unguarded transition 
			:: true ->
				goto EntryExitActionFSM_State9_EntryExitActionFSM_State11;
			fi
			
		:: Message1 ? incoming_pid, urn_jts_EntryExitActionTest_Message1_inst ->
			if
			// unguarded transition 
			:: true ->
				printf("No actions expected here, based on JSIDL definition.");
			fi
			
		:: Message2 ? incoming_pid, urn_jts_EntryExitActionTest_Message2_inst ->
			if
			// unguarded transition 
			:: true ->
				goto EntryExitActionFSM_State10;
			fi
			
	od;
	// Start Entry Actions
	EntryExitActionFSM_State11:
	do
		:: true ->
		goto EntryExitActionFSM_State11_IMPL;
	od;
	// End Entry Actions
	EntryExitActionFSM_State11_IMPL:
	do
		:: PopMessage ? incoming_pid, urn_jts_EntryExitActionTest_PopMessage_inst ->
			if
			// unguarded transition 
			:: true ->
				goto EntryExitActionFSM_State11;
			fi
			
	od;
	// Start Entry Actions
	EntryExitActionFSM_State10:
	do
		:: true ->
		goto EntryExitActionFSM_State10_IMPL;
	od;
	// End Entry Actions
	EntryExitActionFSM_State10_IMPL:
	do
		:: printf("Empty state expected here, based on JSIDL definition.")
	od;
EntryExitActionFSM_State9_EntryExitActionFSM_State11:
do
	:: PopMessage ? incoming_pid, urn_jts_EntryExitActionTest_PopMessage_inst ->
		if
		// unguarded transition 
		:: true ->
			goto EntryExitActionFSM_State9;
		fi
		
od;
}
// ending state machine definition : EntryExitActionFSM
