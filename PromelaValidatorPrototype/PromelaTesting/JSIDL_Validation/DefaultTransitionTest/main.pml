/** 
 *  This Promela file was auto-generated using JTS on Thu Apr 14 11:49:17 EDT 2011.
 *  ID=MAIN  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

/** 
 *  This is the main model file and will contain instances of all message channels and state machines.
 */ 

#include "userEditableConfig.pml"
#include "urn_jts_DefaultTransTest_static.pml"

// Create channels for all the messages and events.
#include "channels.pml"


// These includes should come after message and event channels are declared.
#include "userEditableEvents.pml"
#include "userEditableGuardsAndActions.pml"
#include "userEditableClients.pml"


// starting state machine definition : DefaultTransFSM
active proctype DefaultTransFSM(){
	pid incoming_pid = 0;
	pid current_client_pid = 0;
	urn_jts_DefaultTransTest_Message2 urn_jts_DefaultTransTest_Message2_inst;
	urn_jts_DefaultTransTest_Message3 urn_jts_DefaultTransTest_Message3_inst;
	urn_jts_DefaultTransTest_Message1 urn_jts_DefaultTransTest_Message1_inst;
goto DefaultTransFSM_State18;
	// Start Entry Actions
	DefaultTransFSM_State18:
	do
		:: true ->
		goto DefaultTransFSM_State18_IMPL;
	od;
	// End Entry Actions
	DefaultTransFSM_State18_IMPL:
	do
		:: Message1 ? incoming_pid, urn_jts_DefaultTransTest_Message1_inst ->
			if
			::  Guard() ->
				goto DefaultTransFSM_State19;
			fi
			unless {
			if
			// unguarded transition 
			:: true ->
				goto DefaultTransFSM_State19;
			fi
			}
		:: Message2 ? incoming_pid, urn_jts_DefaultTransTest_Message2_inst ->
			if
			// unguarded transition 
			:: true ->
				goto DefaultTransFSM_State19;
			fi
			
		:: true ->
			if
			// unguarded transition 
			:: true ->
				goto DefaultTransFSM_State19;
			fi
			unless {
			if
			::  Guard() ->
				goto DefaultTransFSM_State19;
			fi
			unless {
			if
			// unguarded transition 
			:: true ->
				goto DefaultTransFSM_State19;
			fi
			unless {
			if
			::  Guard() ->
				goto DefaultTransFSM_State19;
			fi
			}}}
	od;
	// Start Entry Actions
	DefaultTransFSM_State19:
	do
		:: true ->
		goto DefaultTransFSM_State19_IMPL;
	od;
	// End Entry Actions
	DefaultTransFSM_State19_IMPL:
	do
		:: Message3 ? incoming_pid, urn_jts_DefaultTransTest_Message3_inst ->
			if
			// unguarded transition 
			:: true ->
				goto DefaultTransFSM_State18;
			fi
			
		:: Message1 ? incoming_pid, urn_jts_DefaultTransTest_Message1_inst ->
			if
			::  Guard() ->
				goto DefaultTransFSM_State19;
			fi
			
		:: Message2 ? incoming_pid, urn_jts_DefaultTransTest_Message2_inst ->
			if
			// unguarded transition 
			:: true ->
				goto DefaultTransFSM_State19;
			fi
			
		:: true ->
			if
			// unguarded transition 
			:: true ->
				goto DefaultTransFSM_State19;
			fi
			unless {
			if
			::  Guard() ->
				goto DefaultTransFSM_State19;
			fi
			}
	od;
}
// ending state machine definition : DefaultTransFSM
