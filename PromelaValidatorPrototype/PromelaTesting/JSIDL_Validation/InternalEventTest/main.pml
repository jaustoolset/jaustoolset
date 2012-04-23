/** 
 *  This Promela file was auto-generated using JTS on Wed Apr 13 15:58:01 EDT 2011.
 *  ID=MAIN  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

/** 
 *  This is the main model file and will contain instances of all message channels and state machines.
 */ 

#include "userEditableConfig.pml"
#include "urn_jts_InternalEventTest_static.pml"

// Create channels for all the messages and events.
#include "channels.pml"


// These includes should come after message and event channels are declared.
#include "userEditableEvents.pml"
#include "userEditableGuardsAndActions.pml"
#include "userEditableClients.pml"


// starting state machine definition : InternalEventTestFSM
active proctype InternalEventTestFSM(){
	pid incoming_pid = 0;
	pid current_client_pid = 0;
goto InternalEventTestFSM_State6;
	// Start Entry Actions
	InternalEventTestFSM_State6:
	do
		:: true ->
		goto InternalEventTestFSM_State6_IMPL;
	od;
	// End Entry Actions
	InternalEventTestFSM_State6_IMPL:
	do
		:: TestEvent ? incoming_pid ->
			if
			// unguarded transition 
			:: true ->
				Action_printEventData();
			fi
			
	od;
}
// ending state machine definition : InternalEventTestFSM
