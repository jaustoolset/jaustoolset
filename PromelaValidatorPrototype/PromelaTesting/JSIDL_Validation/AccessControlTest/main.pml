/** 
 *  This Promela file was auto-generated using JTS on Wed May 18 13:42:51 EDT 2011.
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
#include "urn_jaus_jss_core_MessageSet_CommandClass_static.pml"
#include "urn_jaus_jss_core_MessageSet_InformClass_static.pml"
#include "urn_jaus_jss_core_MessageSet_QueryClass_static.pml"
#include "urn_jaus_jss_core_MessageSet_static.pml"
#include "urn_jaus_jss_core_Events_static.pml"
#include "urn_jaus_jss_core_AccessControl_static.pml"
#include "urn_jaus_jss_core_AccessControl_static.pml"

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
	urn_jaus_jss_core_MessageSet_QueryClass_QueryEvents urn_jaus_jss_core_MessageSet_QueryClass_QueryEvents_inst;
	//  enveloped query authority message
	//  enveloped set authority message
	urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_inst;
	urn_jaus_jss_core_MessageSet_CommandClass_SetAuthority urn_jaus_jss_core_MessageSet_CommandClass_SetAuthority_inst;
	urn_jaus_jss_core_MessageSet_QueryClass_QueryTimeout urn_jaus_jss_core_MessageSet_QueryClass_QueryTimeout_inst;
	urn_jaus_jss_core_MessageSet_QueryClass_QueryEventTimeout urn_jaus_jss_core_MessageSet_QueryClass_QueryEventTimeout_inst;
	//  enveloped query timeout message
	urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst;
	//  enveloped request control message
	urn_jaus_jss_core_MessageSet_QueryClass_QueryControl urn_jaus_jss_core_MessageSet_QueryClass_QueryControl_inst;
	urn_jaus_jss_core_MessageSet_CommandClass_ReleaseControl urn_jaus_jss_core_MessageSet_CommandClass_ReleaseControl_inst;
	//  enveloped release control message
	urn_jaus_jss_core_MessageSet_QueryClass_QueryAuthority urn_jaus_jss_core_MessageSet_QueryClass_QueryAuthority_inst;
	urn_jaus_jss_core_MessageSet_CommandClass_RequestControl urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_inst;
	urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst;
	//  enveloped query control message
goto ReceiveFSM_Receiving_Ready_NotControlled_Available;
	// Start Entry Actions
	ReceiveFSM_Receiving:
	do
		:: true ->
		goto ReceiveFSM_Receiving_IMPL;
	od;
	// End Entry Actions
	ReceiveFSM_Receiving_IMPL:
	do
		::
		// Start Entry Actions
		ReceiveFSM_Receiving_Ready:
		do
			:: true ->
			goto ReceiveFSM_Receiving_Ready_IMPL;
		od;
		// End Entry Actions
		ReceiveFSM_Receiving_Ready_IMPL:
		do
			::
			// Start Entry Actions
			ReceiveFSM_Receiving_Ready_NotControlled:
			do
				:: true ->
				Action_init();
				goto ReceiveFSM_Receiving_Ready_NotControlled_IMPL;
			od;
			// End Entry Actions
			ReceiveFSM_Receiving_Ready_NotControlled_IMPL:
			do
				::
				// Start Entry Actions
				ReceiveFSM_Receiving_Ready_NotControlled_Available:
				do
					:: true ->
					goto ReceiveFSM_Receiving_Ready_NotControlled_Available_IMPL;
				od;
				// End Entry Actions
				ReceiveFSM_Receiving_Ready_NotControlled_Available_IMPL:
				do
					:: CreateEvent ? incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst ->
						if
						/**
						*  True if parameters are not supported.
						*/
						::  ! urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_isSupported(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst) ->
							//  Send Reject Event Request message
							urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_Send_RejectEventRequest(_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst, incoming_pid);
						fi
						unless {
						if
						/**
						*  True if parameters are supported and the event already exists.
						*/
						::  urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_isSupported(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst) && urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_eventExists(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst) ->
							//  update the event
							urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_updateEvent(urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst);
							//  Send Confirm Event Request message
							urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_Send_ConfirmEventRequest(_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst, incoming_pid);
							//  Reset the timeout period for the recurring event.
							urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_resetEventTimer(urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst);
						fi
						unless {
						if
						/**
						*  True if parameters are supported and the event does not already exist.
						*/
						::  urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_isSupported(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst) && ! urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_eventExists(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst) ->
							//  create the event
							urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_createEvent(urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst);
							//  Send Confirm Event Request message
							urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_Send_ConfirmEventRequest(_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst, incoming_pid);
							//  Reset the timeout period for the recurring event.
							urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_resetEventTimer(urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst);
						fi
						}}
					:: UpdateEvent ? incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst ->
						if
						::  ! urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_isSupported(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst) || ! urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_eventExists(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst) ->
							//  Send Reject Event Request message
							urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_Action_Send_RejectEventRequest(_pid, urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst, incoming_pid);
						fi
						unless {
						if
						/**
						*  True if parameters are supported and if the specified event exists
						*/
						::  urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_isSupported(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst) && urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_eventExists(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst) ->
							//  update the event
							urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_Action_updateEvent(urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst);
							//  Send Confirm Event Request message
							urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_Action_Send_ConfirmEventRequest(_pid, urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst, incoming_pid);
							//  Reset the timeout period for the recurring event.
							urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_Action_resetEventTimer(urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst);
						fi
						}
					:: CancelEvent ? incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_inst ->
						if
						::  urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_eventExists(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_inst) ->
							//  cancel the event
							urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_Action_cancelEvent(urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_inst);
							//  Send Confirm Event Request message
							urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_Action_Send_ConfirmEventRequest(_pid, urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_inst, incoming_pid);
							//  Stop evaluating the recurring event for timeouts.
							urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_Action_stopEventTimer(urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_inst);
						fi
						unless {
						if
						::  ! urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_eventExists(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_inst) ->
							//  Send Reject Event Request message
							urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_Action_Send_RejectEventRequest(_pid, urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_inst, incoming_pid);
						fi
						}
					:: EventOccurred ? incoming_pid ->
						if
						::  eventExists() ->
							//  send an event notification
							Action_sendEvent();
						fi
						
					:: EventError ? incoming_pid ->
						if
						::  eventExists() ->
							//  Send Reject Event Request message
							Action_sendRejectEventRequest();
						fi
						
					:: RequestControl ? incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_inst ->
						if
						/**
						*  True if the base authority code of this service is greater than the authority code of the client service
						*  that triggered the corresponding transition
						*/
						::  ! urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_isDefaultAuthorityGreater(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_inst) ->
							//  Store the address of the client that sent the message that caused this action to be executed
							urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_Action_StoreAddress(urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_inst);
							//  Set the current authority value of this service to the specified authority
							urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_Action_SetAuthority(urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_inst);
							//  Reset the timer
							urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_Action_ResetTimer(urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_inst);
							//  Send a confirm control message with the specified response code to requesting client
							urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_Action_Send_ConfirmControl(_pid, CONTROL_ACCEPTED, incoming_pid);
							goto ReceiveFSM_Receiving_Ready_Controlled_Available;
						fi
						unless {
						if
						/**
						*  True if the default authority code of this service is greater than the authority code of the client
						*  service that triggered the corresponding transition
						*/
						::  urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_isDefaultAuthorityGreater(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_inst) ->
							//  Send a confirm control message with the specified response code to requesting client
							urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_Action_Send_ConfirmControl(_pid, INSUFFICIENT_AUTHORITY, incoming_pid);
						fi
						}
					:: QueryEvents ? incoming_pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryEvents_inst ->
						if
						// unguarded transition 
						:: true ->
							//  Send Report Events message to the component that sent the query
							urn_jaus_jss_core_MessageSet_QueryClass_QueryEvents_Action_Send_ReportEvents(_pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryEvents_inst, incoming_pid);
						fi
						
					:: QueryEventTimeout ? incoming_pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryEventTimeout_inst ->
						if
						// unguarded transition 
						:: true ->
							//  Send Report Event Timeout message to the component that sent the query
							urn_jaus_jss_core_MessageSet_QueryClass_QueryEventTimeout_Action_Send_ReportEventTimeout(_pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryEventTimeout_inst, incoming_pid);
						fi
						
					:: Timeout ? incoming_pid ->
						if
						// unguarded transition 
						:: true ->
							//  cancel the event
							Action_cancelEvent();
							//  Send Confirm Event Request message
							Action_Send_ConfirmEventRequest(_pid);
							//  Stop evaluating the recurring event for timeouts.
							Action_stopEventTimer();
						fi
						
					:: ReleaseControl ? incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_ReleaseControl_inst ->
						if
						// unguarded transition 
						:: true ->
							//  Send a Reject Control message to the client requesting release
							urn_jaus_jss_core_MessageSet_CommandClass_ReleaseControl_Action_Send_RejectControl(_pid, CONTROL_RELEASED, incoming_pid);
						fi
						
					:: QueryAuthority ? incoming_pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryAuthority_inst ->
						if
						// unguarded transition 
						:: true ->
							//  Send a Report Authority message to querying client
							urn_jaus_jss_core_MessageSet_QueryClass_QueryAuthority_Action_Send_ReportAuthority(_pid, incoming_pid);
						fi
						
					:: QueryTimeout ? incoming_pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryTimeout_inst ->
						if
						// unguarded transition 
						:: true ->
							//  Send a Report Timeout message to querying client
							urn_jaus_jss_core_MessageSet_QueryClass_QueryTimeout_Action_Send_ReportTimeout(_pid, incoming_pid);
						fi
						
					:: QueryControl ? incoming_pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryControl_inst ->
						if
						// unguarded transition 
						:: true ->
							//  Send a Report Control message with the specified control value
							urn_jaus_jss_core_MessageSet_QueryClass_QueryControl_Action_Send_ReportControl(_pid, incoming_pid);
						fi
						
				od;
				::
				// Start Entry Actions
				ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable:
				do
					:: true ->
					goto ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_IMPL;
				od;
				// End Entry Actions
				ReceiveFSM_Receiving_Ready_NotControlled_NotAvailable_IMPL:
				do
					:: CreateEvent ? incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst ->
						if
						/**
						*  True if parameters are not supported.
						*/
						::  ! urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_isSupported(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst) ->
							//  Send Reject Event Request message
							urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_Send_RejectEventRequest(_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst, incoming_pid);
						fi
						unless {
						if
						/**
						*  True if parameters are supported and the event already exists.
						*/
						::  urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_isSupported(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst) && urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_eventExists(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst) ->
							//  update the event
							urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_updateEvent(urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst);
							//  Send Confirm Event Request message
							urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_Send_ConfirmEventRequest(_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst, incoming_pid);
							//  Reset the timeout period for the recurring event.
							urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_resetEventTimer(urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst);
						fi
						unless {
						if
						/**
						*  True if parameters are supported and the event does not already exist.
						*/
						::  urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_isSupported(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst) && ! urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_eventExists(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst) ->
							//  create the event
							urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_createEvent(urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst);
							//  Send Confirm Event Request message
							urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_Send_ConfirmEventRequest(_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst, incoming_pid);
							//  Reset the timeout period for the recurring event.
							urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_resetEventTimer(urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst);
						fi
						}}
					:: UpdateEvent ? incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst ->
						if
						::  ! urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_isSupported(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst) || ! urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_eventExists(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst) ->
							//  Send Reject Event Request message
							urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_Action_Send_RejectEventRequest(_pid, urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst, incoming_pid);
						fi
						unless {
						if
						/**
						*  True if parameters are supported and if the specified event exists
						*/
						::  urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_isSupported(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst) && urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_eventExists(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst) ->
							//  update the event
							urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_Action_updateEvent(urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst);
							//  Send Confirm Event Request message
							urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_Action_Send_ConfirmEventRequest(_pid, urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst, incoming_pid);
							//  Reset the timeout period for the recurring event.
							urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_Action_resetEventTimer(urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst);
						fi
						}
					:: CancelEvent ? incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_inst ->
						if
						::  urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_eventExists(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_inst) ->
							//  cancel the event
							urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_Action_cancelEvent(urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_inst);
							//  Send Confirm Event Request message
							urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_Action_Send_ConfirmEventRequest(_pid, urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_inst, incoming_pid);
							//  Stop evaluating the recurring event for timeouts.
							urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_Action_stopEventTimer(urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_inst);
						fi
						unless {
						if
						::  ! urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_eventExists(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_inst) ->
							//  Send Reject Event Request message
							urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_Action_Send_RejectEventRequest(_pid, urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_inst, incoming_pid);
						fi
						}
					:: EventOccurred ? incoming_pid ->
						if
						::  eventExists() ->
							//  send an event notification
							Action_sendEvent();
						fi
						
					:: EventError ? incoming_pid ->
						if
						::  eventExists() ->
							//  Send Reject Event Request message
							Action_sendRejectEventRequest();
						fi
						
					:: QueryEvents ? incoming_pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryEvents_inst ->
						if
						// unguarded transition 
						:: true ->
							//  Send Report Events message to the component that sent the query
							urn_jaus_jss_core_MessageSet_QueryClass_QueryEvents_Action_Send_ReportEvents(_pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryEvents_inst, incoming_pid);
						fi
						
					:: QueryEventTimeout ? incoming_pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryEventTimeout_inst ->
						if
						// unguarded transition 
						:: true ->
							//  Send Report Event Timeout message to the component that sent the query
							urn_jaus_jss_core_MessageSet_QueryClass_QueryEventTimeout_Action_Send_ReportEventTimeout(_pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryEventTimeout_inst, incoming_pid);
						fi
						
					:: Timeout ? incoming_pid ->
						if
						// unguarded transition 
						:: true ->
							//  cancel the event
							Action_cancelEvent();
							//  Send Confirm Event Request message
							Action_Send_ConfirmEventRequest(_pid);
							//  Stop evaluating the recurring event for timeouts.
							Action_stopEventTimer();
						fi
						
					:: ReleaseControl ? incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_ReleaseControl_inst ->
						if
						// unguarded transition 
						:: true ->
							//  Send a Reject Control message to the client requesting release
							urn_jaus_jss_core_MessageSet_CommandClass_ReleaseControl_Action_Send_RejectControl(_pid, CONTROL_RELEASED, incoming_pid);
						fi
						
					:: RequestControl ? incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_inst ->
						if
						// unguarded transition 
						:: true ->
							//  Send a confirm component control message with the specified response code to requesting client
							urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_Action_Send_ConfirmControl(_pid, NOT_AVAILABLE, incoming_pid);
						fi
						
					:: QueryAuthority ? incoming_pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryAuthority_inst ->
						if
						// unguarded transition 
						:: true ->
							//  Send a Report Authority message to querying client
							urn_jaus_jss_core_MessageSet_QueryClass_QueryAuthority_Action_Send_ReportAuthority(_pid, incoming_pid);
						fi
						
					:: QueryTimeout ? incoming_pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryTimeout_inst ->
						if
						// unguarded transition 
						:: true ->
							//  Send a Report Timeout message to querying client
							urn_jaus_jss_core_MessageSet_QueryClass_QueryTimeout_Action_Send_ReportTimeout(_pid, incoming_pid);
						fi
						
					:: QueryControl ? incoming_pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryControl_inst ->
						if
						// unguarded transition 
						:: true ->
							//  Send a Report Control message with the specified control value
							urn_jaus_jss_core_MessageSet_QueryClass_QueryControl_Action_Send_ReportControl(_pid, incoming_pid);
						fi
						
				od;
			od;
			::
			// Start Entry Actions
			ReceiveFSM_Receiving_Ready_Controlled:
			do
				:: true ->
				goto ReceiveFSM_Receiving_Ready_Controlled_IMPL;
			od;
			// End Entry Actions
			ReceiveFSM_Receiving_Ready_Controlled_IMPL:
			do
				::
				// Start Entry Actions
				ReceiveFSM_Receiving_Ready_Controlled_Available:
				do
					:: true ->
					goto ReceiveFSM_Receiving_Ready_Controlled_Available_IMPL;
				od;
				// End Entry Actions
				ReceiveFSM_Receiving_Ready_Controlled_Available_IMPL:
				do
					:: CreateEvent ? incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst ->
						if
						/**
						*  True if parameters are not supported.
						*/
						::  ! urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_isSupported(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst) ->
							//  Send Reject Event Request message
							urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_Send_RejectEventRequest(_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst, incoming_pid);
						fi
						unless {
						if
						/**
						*  True if parameters are supported and the event already exists.
						*/
						::  urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_isSupported(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst) && urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_eventExists(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst) ->
							//  update the event
							urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_updateEvent(urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst);
							//  Send Confirm Event Request message
							urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_Send_ConfirmEventRequest(_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst, incoming_pid);
							//  Reset the timeout period for the recurring event.
							urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_resetEventTimer(urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst);
						fi
						unless {
						if
						/**
						*  True if parameters are supported and the event does not already exist.
						*/
						::  urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_isSupported(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst) && ! urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_eventExists(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst) ->
							//  create the event
							urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_createEvent(urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst);
							//  Send Confirm Event Request message
							urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_Send_ConfirmEventRequest(_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst, incoming_pid);
							//  Reset the timeout period for the recurring event.
							urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_resetEventTimer(urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst);
						fi
						}}
					:: UpdateEvent ? incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst ->
						if
						::  ! urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_isSupported(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst) || ! urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_eventExists(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst) ->
							//  Send Reject Event Request message
							urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_Action_Send_RejectEventRequest(_pid, urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst, incoming_pid);
						fi
						unless {
						if
						/**
						*  True if parameters are supported and if the specified event exists
						*/
						::  urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_isSupported(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst) && urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_eventExists(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst) ->
							//  update the event
							urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_Action_updateEvent(urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst);
							//  Send Confirm Event Request message
							urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_Action_Send_ConfirmEventRequest(_pid, urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst, incoming_pid);
							//  Reset the timeout period for the recurring event.
							urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_Action_resetEventTimer(urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst);
						fi
						}
					:: CancelEvent ? incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_inst ->
						if
						::  urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_eventExists(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_inst) ->
							//  cancel the event
							urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_Action_cancelEvent(urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_inst);
							//  Send Confirm Event Request message
							urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_Action_Send_ConfirmEventRequest(_pid, urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_inst, incoming_pid);
							//  Stop evaluating the recurring event for timeouts.
							urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_Action_stopEventTimer(urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_inst);
						fi
						unless {
						if
						::  ! urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_eventExists(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_inst) ->
							//  Send Reject Event Request message
							urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_Action_Send_RejectEventRequest(_pid, urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_inst, incoming_pid);
						fi
						}
					:: EventOccurred ? incoming_pid ->
						if
						::  eventExists() ->
							//  send an event notification
							Action_sendEvent();
						fi
						
					:: EventError ? incoming_pid ->
						if
						::  eventExists() ->
							//  Send Reject Event Request message
							Action_sendRejectEventRequest();
						fi
						
					:: SetAuthority ? incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_SetAuthority_inst ->
						if
						/**
						*  True if the value of the authority code received from the client is less than or equal to the current
						*  authority value of this service, , but greater than or equal to the receiving components default
						*  authority, and if the message that triggered the transition is received from the client that is in
						*  control of this service
						*/
						::  urn_jaus_jss_core_MessageSet_CommandClass_SetAuthority_isAuthorityValid(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_SetAuthority_inst) && isControllingClient( incoming_pid ) ->
							//  Set the current authority value of this service to the specified authority
							urn_jaus_jss_core_MessageSet_CommandClass_SetAuthority_Action_SetAuthority(urn_jaus_jss_core_MessageSet_CommandClass_SetAuthority_inst);
						fi
						
					:: RequestControl ? incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_inst ->
						if
						/**
						*  True if the default authority code of this service is greater than the authority code of the client
						*  service that triggered the corresponding transition, and if the message that triggered the transition
						*  is received from the client that is in control of this service
						*/
						::  urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_isDefaultAuthorityGreater(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_inst) && isControllingClient(incoming_pid) ->
							//  Send a Reject Control message
							urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_Action_Send_RejectControl(_pid, CONTROL_RELEASED, incoming_pid);
							goto ReceiveFSM_Receiving_Ready_NotControlled_Available;
						fi
						unless {
						if
						/**
						*  True if the default authority code of this service is not greater than the authority code of the client
						*  service that triggered the corresponding transition, and if the message that triggered the transition
						*  is received from the client that is in control of this service
						*/
						::  ! urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_isDefaultAuthorityGreater(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_inst) && isControllingClient( incoming_pid ) ->
							//  Reset the timer
							urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_Action_ResetTimer(urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_inst);
							//  Set the current authority value of this service to the specified authority
							urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_Action_SetAuthority(urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_inst);
							//  Send a confirm control message with the specified response code to requesting client
							urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_Action_Send_ConfirmControl(_pid, CONTROL_ACCEPTED, incoming_pid);
						fi
						unless {
						if
						/**
						*  True if the current authority value of this service is not less than the authority code of the client
						*  service that triggered the corresponding transition, and if the message that triggered the transition
						*  is received from the client that is in control of this service
						*/
						::  ! urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_isCurrentAuthorityLess(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_inst) && ! isControllingClient( incoming_pid ) ->
							//  Send a confirm control message with the specified response code to requesting client
							urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_Action_Send_ConfirmControl(_pid, INSUFFICIENT_AUTHORITY, incoming_pid);
						fi
						unless {
						if
						/**
						*  True if the current authority value of this service is less than the authority code of the client service
						*  that triggered the corresponding transition, and if the message that triggered the transition
						*  is not received from the client that is in control of this service
						*/
						::  urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_isCurrentAuthorityLess(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_inst) && ! isControllingClient( incoming_pid ) ->
							//  Send a Reject Control message to current controlling client
							urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_Action_Send_RejectControl(_pid, CONTROL_RELEASED, incoming_pid);
							//  Store the address of the client that sent the message that caused this action to be executed
							urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_Action_StoreAddress(urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_inst);
							//  Set the current authority value of this service to the specified authority
							urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_Action_SetAuthority(urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_inst);
							//  Reset the timer
							urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_Action_ResetTimer(urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_inst);
							//  Send a confirm control message with the specified response code to requesting client
							urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_Action_Send_ConfirmControl(_pid, CONTROL_ACCEPTED, incoming_pid);
						fi
						}}}
					:: ReleaseControl ? incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_ReleaseControl_inst ->
						if
						/**
						*  True if the message that triggered the transition is received from the client that is in control of
						*  this service
						*/
						::  isControllingClient( incoming_pid ) ->
							//  Send a Reject Control message with the specified response code to the client requesting release
							urn_jaus_jss_core_MessageSet_CommandClass_ReleaseControl_Action_Send_RejectControl(_pid, CONTROL_RELEASED, incoming_pid);
							goto ReceiveFSM_Receiving_Ready_NotControlled_Available;
						fi
						
					:: QueryEvents ? incoming_pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryEvents_inst ->
						if
						// unguarded transition 
						:: true ->
							//  Send Report Events message to the component that sent the query
							urn_jaus_jss_core_MessageSet_QueryClass_QueryEvents_Action_Send_ReportEvents(_pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryEvents_inst, incoming_pid);
						fi
						
					:: QueryEventTimeout ? incoming_pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryEventTimeout_inst ->
						if
						// unguarded transition 
						:: true ->
							//  Send Report Event Timeout message to the component that sent the query
							urn_jaus_jss_core_MessageSet_QueryClass_QueryEventTimeout_Action_Send_ReportEventTimeout(_pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryEventTimeout_inst, incoming_pid);
						fi
						
					:: Timeout ? incoming_pid ->
						if
						// unguarded transition 
						:: true ->
							//  cancel the event
							Action_cancelEvent();
							//  Send Confirm Event Request message
							Action_Send_ConfirmEventRequest(_pid);
							//  Stop evaluating the recurring event for timeouts.
							Action_stopEventTimer();
						fi
						
					:: Timedout ? incoming_pid ->
						if
						// unguarded transition 
						:: true ->
							//  Send a Reject Control message to controlling client
							Action_Send_RejectControl(_pid, CONTROL_RELEASED);
							goto ReceiveFSM_Receiving_Ready_NotControlled_Available;
						fi
						
					:: QueryAuthority ? incoming_pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryAuthority_inst ->
						if
						// unguarded transition 
						:: true ->
							//  Send a Report Authority message to querying client
							urn_jaus_jss_core_MessageSet_QueryClass_QueryAuthority_Action_Send_ReportAuthority(_pid, incoming_pid);
						fi
						
					:: QueryTimeout ? incoming_pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryTimeout_inst ->
						if
						// unguarded transition 
						:: true ->
							//  Send a Report Timeout message to querying client
							urn_jaus_jss_core_MessageSet_QueryClass_QueryTimeout_Action_Send_ReportTimeout(_pid, incoming_pid);
						fi
						
					:: QueryControl ? incoming_pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryControl_inst ->
						if
						// unguarded transition 
						:: true ->
							//  Send a Report Control message with the specified control value
							urn_jaus_jss_core_MessageSet_QueryClass_QueryControl_Action_Send_ReportControl(_pid, incoming_pid);
						fi
						
				od;
				::
				// Start Entry Actions
				ReceiveFSM_Receiving_Ready_Controlled_NotAvailable:
				do
					:: true ->
					goto ReceiveFSM_Receiving_Ready_Controlled_NotAvailable_IMPL;
				od;
				// End Entry Actions
				ReceiveFSM_Receiving_Ready_Controlled_NotAvailable_IMPL:
				do
					:: CreateEvent ? incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst ->
						if
						/**
						*  True if parameters are not supported.
						*/
						::  ! urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_isSupported(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst) ->
							//  Send Reject Event Request message
							urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_Send_RejectEventRequest(_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst, incoming_pid);
						fi
						unless {
						if
						/**
						*  True if parameters are supported and the event already exists.
						*/
						::  urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_isSupported(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst) && urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_eventExists(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst) ->
							//  update the event
							urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_updateEvent(urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst);
							//  Send Confirm Event Request message
							urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_Send_ConfirmEventRequest(_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst, incoming_pid);
							//  Reset the timeout period for the recurring event.
							urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_resetEventTimer(urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst);
						fi
						unless {
						if
						/**
						*  True if parameters are supported and the event does not already exist.
						*/
						::  urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_isSupported(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst) && ! urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_eventExists(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst) ->
							//  create the event
							urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_createEvent(urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst);
							//  Send Confirm Event Request message
							urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_Send_ConfirmEventRequest(_pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst, incoming_pid);
							//  Reset the timeout period for the recurring event.
							urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_resetEventTimer(urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst);
						fi
						}}
					:: UpdateEvent ? incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst ->
						if
						::  ! urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_isSupported(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst) || ! urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_eventExists(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst) ->
							//  Send Reject Event Request message
							urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_Action_Send_RejectEventRequest(_pid, urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst, incoming_pid);
						fi
						unless {
						if
						/**
						*  True if parameters are supported and if the specified event exists
						*/
						::  urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_isSupported(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst) && urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_eventExists(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst) ->
							//  update the event
							urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_Action_updateEvent(urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst);
							//  Send Confirm Event Request message
							urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_Action_Send_ConfirmEventRequest(_pid, urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst, incoming_pid);
							//  Reset the timeout period for the recurring event.
							urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_Action_resetEventTimer(urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst);
						fi
						}
					:: CancelEvent ? incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_inst ->
						if
						::  urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_eventExists(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_inst) ->
							//  cancel the event
							urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_Action_cancelEvent(urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_inst);
							//  Send Confirm Event Request message
							urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_Action_Send_ConfirmEventRequest(_pid, urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_inst, incoming_pid);
							//  Stop evaluating the recurring event for timeouts.
							urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_Action_stopEventTimer(urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_inst);
						fi
						unless {
						if
						::  ! urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_eventExists(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_inst) ->
							//  Send Reject Event Request message
							urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_Action_Send_RejectEventRequest(_pid, urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_inst, incoming_pid);
						fi
						}
					:: EventOccurred ? incoming_pid ->
						if
						::  eventExists() ->
							//  send an event notification
							Action_sendEvent();
						fi
						
					:: EventError ? incoming_pid ->
						if
						::  eventExists() ->
							//  Send Reject Event Request message
							Action_sendRejectEventRequest();
						fi
						
					:: SetAuthority ? incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_SetAuthority_inst ->
						if
						/**
						*  True if the value of the authority code received from the client is less than or equal to the current
						*  authority value of this service, , but greater than or equal to the receiving components default
						*  authority, and if the message that triggered the transition is received from the client that is in
						*  control of this service
						*/
						::  urn_jaus_jss_core_MessageSet_CommandClass_SetAuthority_isAuthorityValid(incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_SetAuthority_inst) && isControllingClient( incoming_pid ) ->
							//  Set the current authority value of this service to the specified authority
							urn_jaus_jss_core_MessageSet_CommandClass_SetAuthority_Action_SetAuthority(urn_jaus_jss_core_MessageSet_CommandClass_SetAuthority_inst);
						fi
						
					:: QueryEvents ? incoming_pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryEvents_inst ->
						if
						// unguarded transition 
						:: true ->
							//  Send Report Events message to the component that sent the query
							urn_jaus_jss_core_MessageSet_QueryClass_QueryEvents_Action_Send_ReportEvents(_pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryEvents_inst, incoming_pid);
						fi
						
					:: QueryEventTimeout ? incoming_pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryEventTimeout_inst ->
						if
						// unguarded transition 
						:: true ->
							//  Send Report Event Timeout message to the component that sent the query
							urn_jaus_jss_core_MessageSet_QueryClass_QueryEventTimeout_Action_Send_ReportEventTimeout(_pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryEventTimeout_inst, incoming_pid);
						fi
						
					:: Timeout ? incoming_pid ->
						if
						// unguarded transition 
						:: true ->
							//  cancel the event
							Action_cancelEvent();
							//  Send Confirm Event Request message
							Action_Send_ConfirmEventRequest(_pid);
							//  Stop evaluating the recurring event for timeouts.
							Action_stopEventTimer();
						fi
						
					:: RequestControl ? incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_inst ->
						if
						// unguarded transition 
						:: true ->
							//  Send a confirm control message with the specified response code to requesting client
							urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_Action_Send_ConfirmControl(_pid, NOT_AVAILABLE, incoming_pid);
						fi
						
					:: ReleaseControl ? incoming_pid, urn_jaus_jss_core_MessageSet_CommandClass_ReleaseControl_inst ->
						if
						// unguarded transition 
						:: true ->
							//  Send a reject control message with the specified response code to the client requesting release
							urn_jaus_jss_core_MessageSet_CommandClass_ReleaseControl_Action_Send_RejectControl(_pid, NOT_AVAILABLE, incoming_pid);
						fi
						
					:: Timedout ? incoming_pid ->
						if
						// unguarded transition 
						:: true ->
							//  reset the timer
							Action_resetTimer();
						fi
						
					:: QueryAuthority ? incoming_pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryAuthority_inst ->
						if
						// unguarded transition 
						:: true ->
							//  Send a Report Authority message to querying client
							urn_jaus_jss_core_MessageSet_QueryClass_QueryAuthority_Action_Send_ReportAuthority(_pid, incoming_pid);
						fi
						
					:: QueryTimeout ? incoming_pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryTimeout_inst ->
						if
						// unguarded transition 
						:: true ->
							//  Send a Report Timeout message to querying client
							urn_jaus_jss_core_MessageSet_QueryClass_QueryTimeout_Action_Send_ReportTimeout(_pid, incoming_pid);
						fi
						
					:: QueryControl ? incoming_pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryControl_inst ->
						if
						// unguarded transition 
						:: true ->
							//  Send a Report Control message with the specified control value
							urn_jaus_jss_core_MessageSet_QueryClass_QueryControl_Action_Send_ReportControl(_pid, incoming_pid);
						fi
						
				od;
			od;
		od;
	od;
}
// ending state machine definition : ReceiveFSM
