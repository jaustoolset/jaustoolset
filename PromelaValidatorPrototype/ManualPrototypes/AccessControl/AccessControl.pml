/* AccessControl.pml
 *
 * A model of AS5710 Access Control protocol.
 * Author - Chuck Messmer
 * Date - January 2011
 *
 * Run this model in simlation mode with:
 * $ spin -u<some_finite_number> AccessControl.pml
 *
 * Run this model in analysis mode per SPIN documentation.
 *
 */
  // some comment
/*
*	include outside functionality here.
*/
#include "accessControl_Timeout.pml"
#include "accessControl_Static.pml"

/* some definitions for configuration */
#define CLIENTS	1
/* right now, there can be only one */
#define SERVERS 1  
/*(CLIENTS + SERVERS)  to make sure there are enough channels allocated, even if the servers are started first */
#define CLIENT_CHANNELS	4 
#define SYSTEM_ID 50
#define NODE_ID 51
#define COMPONENT_ID 52

#define eventExists() (true)
#define eventExists2(someparam) (true)

/*  basicTypes.AuthorityCode = unsigned byte [0-255]    */
/*  this will need to be replaced everywhere with "byte"   */

/*  JAUSMessageHeader  */
/*  	fixed_field - MessageID = unsigned short integer, optional=false  */
/*  This is not necessary since we have unique message names we are using.  */

/* message_def  */
	/* input_set */
		/* declared_message_def  */
/* RequestControl message_id=000D is_command=true
*	declared_header JAUSMessageHeader 
*	body
* 		record
*			declared_fixed_field - AuthorityCode = basicTypes.AuthorityCode, optional=false
*	footer - empty
*/	
chan RequestControl = [10] of {pid , byte };

/*  ReleaseControl message_id=000E
*	declared_header JAUSMessageHeader
*	body - empty
*	footer - empty
*/	
chan ReleaseControl = [10] of {pid };

/*  QueryControl message_id=200D is_command=false
*	declared_header JAUSMessageHeader
*	body - empty
*	footer - empty
*/	
chan QueryControl = [10] of {pid };

/*  QueryAuthority message_id=2001 is_command=false
*	declared_header JAUSMessageHeader
*	body - empty
*	footer - empty
*/
chan QueryAuthority = [10] of {pid };

/*  SetAuthority message_id=0001 is_command=true
*	declared_header JAUSMessageHeader
*	body
* 		record
*			declared_fixed_field - AuthorityCode = basidTypesAuthorityCode, optional=false
*	footer - empty
*/	
chan SetAuthority = [10] of {pid , byte };

/*  QueryTimeout message_id=2003 is_command=false
*	declared_header JAUSMessageHeader
*	body - empty
*	footer - empty
*/	
chan QueryTimeout = [10] of {pid };

	/* output_set  - these message channels will be declared as arrays, so that each client instance 
					has a separate response channel.  This is required or the individual client may receive a 
					response for a different client, which removes the message from the queue so the proper client
					will never get notified of the message.  */
		/* declared_message_def  */
	/*  ReportControl message_id=400D is_command=false
	*	declared_header JAUSMessageHeader
	*	body
	* 		record
	*			fixed_field - SubsystemID = unsigned short int, optional=false
	*			fixed_field - NodeID = unsigned byte, optional=false
	*			fixed_field - ComponentID = unsigned byte, optional=false
	*			declared_fixed_field - AuthorityCode = basicTypes.AuthorityCode, optional=false
	*	footer - empty
	*/	

	chan ReportControl[CLIENT_CHANNELS] = [10] of {pid, int, byte , byte , byte };

	/*  RejectControl message_id=0010 is_command=false
	*	declared_header JAUSMessageHeader
	*	body
	* 		record
	*			fixed_field - ResponseCode = unsigned byte, optional=false, value_set[CONTROL_RELEASED, NOT_AVAILABLE]
	*	footer - empty
	*/	
	mtype {CONTROL_RELEASED, NOT_AVAILABLE};
	chan RejectControl[CLIENT_CHANNELS] = [10] of {pid , mtype };

	/*  ConfirmControl message_id=000F is_command=false
	*	declared_header JAUSMessageHeader
	*	body
	* 		record
	*			fixed_field - ResponseCode = unsigned byte, optional=false, value_set[CONTROL_ACCEPTED, NOT_AVAILABLE, INSUFFICIENT_AUTHORITY]
	*	footer - empty
	*/	
	mtype {CONTROL_ACCEPTED, NOT_AVAILABLE2, INSUFFICIENT_AUTHORITY};
	chan ConfirmControl[CLIENT_CHANNELS] = [10] of {pid , mtype };

	/*  ReportAuthority message_id=4001 is_command=false
	*	declared_header JAUSMessageHeader
	*	body
	* 		record
	*			fixed_field - AuthorityCode = basicTypes.AuthorityCode, optional=false
	*	footer - empty
	*/
	chan ReportAuthority[CLIENT_CHANNELS] = [10] of {pid , byte };
	
	/*  ReportTimeout message_id=4003 is_command=false
	*	declared_header JAUSMessageHeader
	*	body
	* 		record
	*			fixed_field - Timeout = unsigned byte, optional=false, value_set[TIMEOUT_FEATURE_DISABLED]
	*	footer - empty
	*/	
	mtype {TIMEOUT_FEATURE_DISABLED};
	chan ReportTimeout[CLIENT_CHANNELS] = [10] of {pid , mtype };


/************************************* Client Definition ******************************************************/
active [CLIENTS] proctype client_app()
{
	int x;
	mtype param;
	pid service_pid;
	int systemid;
	byte componentid;
	byte nodeid;
	
	byte auth;

CLIENT_NOT_CONTROLLING:
	do
	/* counter to determine when to quit test simulation */
	:: x++;
	/* input messages : parameter values may need to be tweaked by a developer when auto generated. */
	:: RequestControl ! _pid, 5
	:: RequestControl ! _pid, 10
	:: RequestControl ! _pid, 15
	:: RequestControl ! _pid, 20
	:: RequestControl ! _pid, 25
	:: ReleaseControl ! _pid
	:: QueryControl ! _pid
	:: QueryAuthority ! _pid
	:: SetAuthority ! _pid, 15
	:: SetAuthority ! _pid, 0
	:: QueryTimeout ! _pid

	/*output messages: caught and message ed. */
	:: ReportControl[_pid] ? service_pid, systemid, nodeid, componentid, auth ->
		printf("ReportControl : system=%d, node=%d, component=%d, auth=%d", systemid, nodeid, componentid, auth);
	:: RejectControl[_pid] ? service_pid, param ->
		printf("RejectControl : %e\n", param);
	:: ConfirmControl[_pid] ? service_pid, param ->
		printf("ConfirmControl : %e\n", param);
	:: ReportAuthority[_pid] ? service_pid, auth ->
		printf("ReportAuthority %d /n", auth);
	:: ReportTimeout[_pid]? service_pid, param ->
		printf("ReportTimeout : %e\n", param);
	/* end the simulation when we've run enough messages. */
	:: x > 10 ->
		break;
	od;

}

#define defaultState()	/*transition Receive QueryAuthority */ \
     			:: QueryAuthority ? incoming_pid -> \
					/*action Send ReportAuthority */ \
					ReportAuthority[incoming_pid]! _pid, current_client_auth; \
					/* transition Receive QueryTimeout */ \
				:: QueryTimeout ? incoming_pid -> \
					/* action Send ReportTimeout */ \
					ReportTimeout[incoming_pid]! _pid, TIMEOUT_FEATURE_DISABLED; \
					/* transition Receive QueryControl*/ \
				:: QueryControl ? incoming_pid -> \
					/* action Send ReportControl */ \
					ReportControl[incoming_pid]! current_client_pid, SYSTEM_ID, NODE_ID, COMPONENT_ID, current_client_auth; \
  

/*************************************  Service Definition  ****************************************************/
/*  internal_events_set  */
	/*  event_def  */
/* Timeout with header, body, footer def*/

/* protocol_behavior */
	/* start state_machine_name=events.transport.ReceiveFSM, state_name=Receiving.Ready.NotControlled.Available */
	/* state_machine name=events.transport.ReceiveFSM */
active proctype ReceiveFSM()
{
	/* process id used to identify specific clients */
	pid incoming_pid=0;
	byte incoming_auth=0;
	pid current_client_pid=0;
	byte current_client_auth=0;

/* if initial state is not first, the use "goto"  */
goto Receiving_Ready_NotControlled_Available;
	/* state Receiving  initial_state=Ready  */
Receiving:
	do
		/* state Ready initial_state=NotControlled */
	::
	Receiving_Ready:
		do
		::
			/* state NotControlled initial_state=Available */
		Receiving_Ready_NotControlled:
			/* entry action=init  - do some stuff here?*/
			do
			defaultState();
			::	
			/* state Available (no initial_state)  */
			ENTRYACTION:
			do
			:: true ->
			printf("");
			int y = 5;
			y = y + 9;
			goto Receiving_Ready_NotControlled_Available;
			od;
			Receiving_Ready_NotControlled_Available:
				do
				defaultState();
					/* transition Receive, type=RequestControl, value=msg */
				:: RequestControl ? incoming_pid, incoming_auth ->
					/* guard condition= ! isDefaultAuthorityGreater(msg) - this looks impossible to automate*/
					if
					::  !isDefaultAuthorityGreater(incoming_auth) ->
						printf("accepting new controller %d /n", incoming_auth);
						/* action StoreAddress, transportData  */
						StoreAddress(incoming_pid);
						/* action SetAuthority, msg */
						SetAuthorityAction(incoming_auth);
						/* action ResetTimeer */
						ResetTimer();
						/* action Send, ('ConfirmControl', 'CONTROL_ACCEPTED', transportData)  */
						ConfirmControl[incoming_pid]! _pid, CONTROL_ACCEPTED;

						/* simple end_state=Receiving.Ready.Controlled.Available  */
						goto Receiving_Ready_Controlled_Available;
					fi 
					unless {
					/* guard condition=isDefaultAuthorityGreater(msg) - this looks impossible to automate*/
					if
					::  isDefaultAuthorityGreater(incoming_auth) ->
						/* action Send ('ConfirmControl', 'INSUFFICIENT_AUTHORITY', transportData)  */
						ConfirmControl[incoming_pid] ! _pid, INSUFFICIENT_AUTHORITY;
					fi;
					}
					/* transition Redeive type, */
				od;  /* end Receiving_Ready_NotControlled_Available */
			/* state NotAvailable (no initial_state)  */
			::
			Receiving_Ready_NotControlled_NotAvailable:
				do
				defaultState();
					/* transition Receive, type=RequestControl, value=msg  */
					/* ns2:internal means that there is no goto.  I believe we can ignore this since it will happen by default  */
				:: RequestControl? incoming_pid, incoming_auth ->
					/* action name=events.transport.Send, ('ConfirmControl', 'NOT_AVAILABLE', transportData)  */
					ConfirmControl[incoming_pid] ! _pid, NOT_AVAILABLE;
				od; /* end  Receiving_Ready_NotControlled_NotAvailable*/

				/* transition Receive, type=ReleaseControl, value=msg  */
			:: ReleaseControl ? incoming_pid ->
					/* action name=events.transport.Send, ('RejectControl', 'CONTROL_RELEASED', transportData)  */
				RejectControl[incoming_pid]!_pid, CONTROL_RELEASED;
			
			od; /* end Receiving_Ready_NotControlled */


			/* state Controlled initial_state=Available */
			::
		Receiving_Ready_Controlled:
			/* entry action=init  - do some stuff here?*/
			do
			defaultState();
				/* transition Receive, type=SetAuthority  */
				:: SetAuthority?incoming_pid, incoming_auth ->
					/* guard isAuthorityValid(msg) */
					if
					:: isAuthorityValid(incoming_pid, incoming_auth) ->
						/* action SetAuthority */
						SetAuthorityAction(incoming_auth);
					fi;
					
				/* state Available */
				:: 
				bit x;
				pid tmppid;
				Receiving_Ready_Controlled_Available:
				do
				defaultState();
					/* transition Receive, type=RequestControl */
					:: RequestControl ? tmppid, incoming_auth ->
						if
						/* guard isDefaultAurhorityGreater(msg) */
						:: isDefaultAuthorityGreater(incoming_auth) ->
							/* action Send RejectControl, CONTROL_RELEASED */
							RejectControl[incoming_auth]! _pid, CONTROL_RELEASED;
							goto Receiving_Ready_NotControlled_Available;
						fi unless {
						if
						/* guard ! isDefaultAuthorityGreater(msg) */
						:: !isDefaultAuthorityGreater(incoming_auth) ->
							/* action ResetTimer */
							ResetTimer();
							/* action SetAuthority */
							SetAuthorityAction(incoming_auth);
							/* action Send, ConfirmControl, CONTROL_ACCEPTED */
							ConfirmControl[incoming_auth] ! _pid, CONTROL_ACCEPTED;
						fi unless {
						if
						/* guard ! isCurrentAuthorityLess() */
						:: !isCurrentAuthorityLess(incoming_auth) ->
							/* action Send, ConfirmControl, INSUFFICIENT_AUTHORITY  */
							ConfirmControl[incoming_pid]! _pid, INSUFFICIENT_AUTHORITY;
						fi unless {						
						/* guard isCurrentAuthorityLess() */
						if
						:: isCurrentAuthorityLess(incoming_auth) ->
							/* action Send RejectControl to current_client  */
							RejectControl[current_client_pid]!_pid, CONTROL_RELEASED;
							/* action StoreAddress */
							StoreAddress( incoming_pid);
							/* action SetAuthority  */
							SetAuthorityAction(incoming_auth);
							/* action resetTimer */
							ResetTimer();
							/* action Send, ConfirmControl, CONTROL_ACCEPTED */
							ConfirmControl[current_client_pid]! _pid, CONTROL_ACCEPTED;
						fi;
						}}} /* end all the unless statements*/
					/* transition Receive ReleaseControl */
					:: ReleaseControl ? incoming_pid ->
						/*guard isControllingClient(transportData)*/
						if
						:: isControllingClient(incoming_pid) ->
							/* action Send, RejectControl, CONTROL_RELEASED */
							RejectControl[incoming_pid]!_pid, CONTROL_RELEASED;
							goto Receiving_Ready_NotControlled_Available;
						fi;
					/*transition Timeout */
					:: Timeout ? _pid ->
						/* action RejectControl, CONTROL_RELEASED */
						RejectControl[current_client_pid]!_pid, CONTROL_RELEASED;
						/* simple end_state Receiving.Ready.NotControlled.Available */
						goto Receiving_Ready_NotControlled_Available;

				od; /* end Receiving_Ready_Controlled_Available */

				/* state NotAvailable */
				::
				Receiving_Ready_Controlled_NotAvailable:
				do
					defaultState();
					/* transition Receive, type=RequestControl */
					:: RequestControl ? incoming_pid, incoming_auth ->
						/* action Send ConfirmControl, NOT_AVAILABLE */						
						ConfirmControl[incoming_pid]! _pid, NOT_AVAILABLE;
					/* transition Receive, type=ReleaseControl */
					:: ReleaseControl? incoming_pid ->
						/*action Send RejectControl, NOT_AVAILABLE*/
						RejectControl[incoming_pid]! _pid, NOT_AVAILABLE;
					/* transition Timeout */
					:: Timeout! incoming_pid ->
						/* action resetTimer */
						ResetTimer();
				od; /*end Receiving_Ready_Controlled_NotAvailable */

			od; /* end Receiving_Ready_Controlled */
		
		od; /* end Receiving_Ready */

	od; /* end Receiving */

}
