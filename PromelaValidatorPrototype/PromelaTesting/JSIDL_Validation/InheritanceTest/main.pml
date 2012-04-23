/** 
 *  This Promela file was auto-generated using JTS on Wed Apr 13 15:57:47 EDT 2011.
 *  ID=MAIN  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

/** 
 *  This is the main model file and will contain instances of all message channels and state machines.
 */ 

#include "userEditableConfig.pml"
#include "urn_jaus_jss_core_MessageSet_BasicTypes_static.pml"
#include "urn_jaus_jss_core_Transport_static.pml"
#include "urn_jts_InheritanceTest_static.pml"

// Create channels for all the messages and events.
#include "channels.pml"


// These includes should come after message and event channels are declared.
#include "userEditableEvents.pml"
#include "userEditableGuardsAndActions.pml"
#include "userEditableClients.pml"


// starting state machine definition : SendFSM
active proctype SendFSM(){
	pid incoming_pid = 0;
	pid current_client_pid = 0;
goto SendFSM_Sending;
	// Start Entry Actions
	SendFSM_Sending:
	do
		:: true ->
		goto SendFSM_Sending_IMPL;
	od;
	// End Entry Actions
	SendFSM_Sending_IMPL:
	do
		:: Send ? incoming_pid ->
			if
			// unguarded transition 
			:: true ->
				//  Convert the destination address into an unsigned integer such that the ComponentID maps to the least
				//  significant byte, NodeID to the next least significant byte and SubsystemID maps onto the remaining
				//  two bytes of the integer. Package the message as specified by the transport layer specification
				//  and send it to its destination as per the specified priority.
				Action_Enqueue();
			fi
			
		:: BroadcastLocal ? incoming_pid ->
			if
			// unguarded transition 
			:: true ->
				//  Package the message as specified by the transport layer specification and send it to all endpoints
				//  in the local subsystem.
				Action_BroadcastLocalEnqueue();
			fi
			
		:: BroadcastGlobal ? incoming_pid ->
			if
			// unguarded transition 
			:: true ->
				//  Package the message as specified by the transport layer specification and send it to all endpoints
				//  in on all subsystems.
				Action_BroadcastGlobalEnqueue();
			fi
			
	od;
}
// ending state machine definition : SendFSM
// starting state machine definition : ReceiveFSM
active proctype ReceiveFSM(){
	pid incoming_pid = 0;
	pid current_client_pid = 0;
	urn_jts_InheritanceTest_Message1 urn_jts_InheritanceTest_Message1_inst;
goto ReceiveFSM_Receiving;
	// Start Entry Actions
	ReceiveFSM_Receiving:
	do
		:: true ->
		goto ReceiveFSM_Receiving_IMPL;
	od;
	// End Entry Actions
	ReceiveFSM_Receiving_IMPL:
	do
		:: Message1 ? incoming_pid, urn_jts_InheritanceTest_Message1_inst ->
			if
			// unguarded transition 
			:: true ->
				goto ReceiveFSM_NewState;
			fi
			
	od;
	// Start Entry Actions
	ReceiveFSM_NewState:
	do
		:: true ->
		goto ReceiveFSM_NewState_IMPL;
	od;
	// End Entry Actions
	ReceiveFSM_NewState_IMPL:
	do
		:: printf("Empty state expected here, based on JSIDL definition.")
	od;
}
// ending state machine definition : ReceiveFSM
