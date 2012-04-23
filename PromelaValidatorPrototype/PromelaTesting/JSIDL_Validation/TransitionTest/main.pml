/** 
 *  This Promela file was auto-generated using JTS on Thu Apr 14 11:45:11 EDT 2011.
 *  ID=MAIN  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

/** 
 *  This is the main model file and will contain instances of all message channels and state machines.
 */ 

#include "userEditableConfig.pml"
#include "urn_jts_TransitionTest_static.pml"

// Create channels for all the messages and events.
#include "channels.pml"


// These includes should come after message and event channels are declared.
#include "userEditableEvents.pml"
#include "userEditableGuardsAndActions.pml"
#include "userEditableClients.pml"


// starting state machine definition : TransitionFSM
active proctype TransitionFSM(){
	pid incoming_pid = 0;
	pid current_client_pid = 0;
	urn_jts_TransitionTest_PushMessage urn_jts_TransitionTest_PushMessage_inst;
	urn_jts_TransitionTest_Message1 urn_jts_TransitionTest_Message1_inst;
	urn_jts_TransitionTest_ExitDefaultStateMessage urn_jts_TransitionTest_ExitDefaultStateMessage_inst;
	urn_jts_TransitionTest_PopMessage urn_jts_TransitionTest_PopMessage_inst;
	urn_jts_TransitionTest_Message2 urn_jts_TransitionTest_Message2_inst;
	urn_jts_TransitionTest_Message3 urn_jts_TransitionTest_Message3_inst;
	
goto TransitionFSM_State13;
	// Start Entry Actions
	TransitionFSM_State13:
	do
		:: true ->
		goto TransitionFSM_State13_IMPL;
	od;
	// End Entry Actions
	TransitionFSM_State13_IMPL:
	do
		:: Message1 ? incoming_pid, urn_jts_TransitionTest_Message1_inst ->
			if
			// unguarded transition 
			:: true ->
				printf("unguarded internal transition taken\n");
			fi
			unless {
			if
			::  Guard1() ->
				printf("guarded internal transition taken\n");
			fi
			}
		:: Message2 ? incoming_pid, urn_jts_TransitionTest_Message2_inst ->
			if
			// unguarded transition 
			:: true ->
				goto TransitionFSM_State14;
			fi
			unless {
			if
			::  Guard2() ->
				goto TransitionFSM_State14;
			fi
			}
		:: PushMessage ? incoming_pid, urn_jts_TransitionTest_PushMessage_inst ->
			if
			// unguarded transition 
			:: true ->
				goto TransitionFSM_State13_TransitionFSM_State15;
			fi
			unless {
			if
			::  Guard4() ->
				goto TransitionFSM_State13_TransitionFSM_State15;
			fi
			}
		:: ExitDefaultStateMessage ? incoming_pid, urn_jts_TransitionTest_ExitDefaultStateMessage_inst ->
			if
			// unguarded transition 
			:: true ->
				goto TransitionFSM_State14;
			fi
			unless {
			if
			::  DefaultStateGuard() ->
				goto TransitionFSM_State14;
			fi
			}
	od;
	// Start Entry Actions
	TransitionFSM_State15:
	do
		:: true ->
		goto TransitionFSM_State15_IMPL;
	od;
	// End Entry Actions
	TransitionFSM_State15_IMPL:
	do
		:: PopMessage ? incoming_pid, urn_jts_TransitionTest_PopMessage_inst ->
			if
			// unguarded transition 
			:: true ->
				goto TransitionFSM_State15;
			fi
			unless {
			if
			::  Guard4() ->
				goto TransitionFSM_State15;
			fi
			}
		:: ExitDefaultStateMessage ? incoming_pid, urn_jts_TransitionTest_ExitDefaultStateMessage_inst ->
			if
			// unguarded transition 
			:: true ->
				goto TransitionFSM_State14;
			fi
			unless {
			if
			::  DefaultStateGuard() ->
				goto TransitionFSM_State14;
			fi
			}
	od;
	// Start Entry Actions
	TransitionFSM_State14:
	do
		:: true ->
		goto TransitionFSM_State14_IMPL;
	od;
	// End Entry Actions
	TransitionFSM_State14_IMPL:
	do
		:: ExitDefaultStateMessage ? incoming_pid, urn_jts_TransitionTest_ExitDefaultStateMessage_inst ->
			if
			// unguarded transition 
			:: true ->
				goto TransitionFSM_State14;
			fi
			unless {
			if
			::  DefaultStateGuard() ->
				goto TransitionFSM_State14;
			fi
			}
		:: Message3 ? incoming_pid, urn_jts_TransitionTest_Message3_inst ->
			goto TransitionFSM_State13;
	od;
TransitionFSM_State13_TransitionFSM_State15:
do
	:: PopMessage ? incoming_pid, urn_jts_TransitionTest_PopMessage_inst ->
		if
		// unguarded transition 
		:: true ->
			goto TransitionFSM_State13;
		fi
		unless {
		if
		::  Guard4() ->
			goto TransitionFSM_State13;
		fi
		}
	:: ExitDefaultStateMessage ? incoming_pid, urn_jts_TransitionTest_ExitDefaultStateMessage_inst ->
		if
		// unguarded transition 
		:: true ->
			goto TransitionFSM_State14;
		fi
		unless {
		if
		::  DefaultStateGuard() ->
			goto TransitionFSM_State14;
		fi
		}
od;
}
// ending state machine definition : TransitionFSM
