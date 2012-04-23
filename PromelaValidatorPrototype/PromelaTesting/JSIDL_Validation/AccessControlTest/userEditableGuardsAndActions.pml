/** 
 *  This Promela file was auto-generated using JTS on Mon May 16 12:54:31 EDT 2011.
 *  ID=urn:jaus:jss:core:AccessControl  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 
 byte current_client_auth = 0;
 mtype service_status = STANDBY;

// Guard definitions
/**
*  True if parameters are supported and the event does not already exist.
*/
// NOTICE: replace true with the code necessary for checking the guard.  The parens are required around that code
#define urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_isSupported(incoming_pid,urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst) (true)
/**
*  True if parameters are supported and the event does not already exist.
*/
// NOTICE: replace true with the code necessary for checking the guard.  The parens are required around that code
#define urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_eventExists(incoming_pid,urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_inst) (true)
/**
*  True if parameters are supported and if the specified event exists
*/
// NOTICE: replace true with the code necessary for checking the guard.  The parens are required around that code
#define urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_isSupported(incoming_pid,urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst) (true)
/**
*  True if parameters are supported and if the specified event exists
*/
// NOTICE: replace true with the code necessary for checking the guard.  The parens are required around that code
#define urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_eventExists(incoming_pid,urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_inst) (true)
// NOTICE: replace true with the code necessary for checking the guard.  The parens are required around that code
#define urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_eventExists(incoming_pid,urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_inst) (true)
// NOTICE: replace true with the code necessary for checking the guard.  The parens are required around that code
#define eventExists() (true)
/**
*  True if the default authority code of this service is greater than the authority code of the client
*  service that triggered the corresponding transition
*/
// NOTICE: replace true with the code necessary for checking the guard.  The parens are required around that code
#define urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_isDefaultAuthorityGreater(incoming_pid,urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_inst) (DEFAULT_AUTH > urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_inst.RequestControlRec.AuthorityCode)
/**
*  True if the value of the authority code received from the client is less than or equal to the current
*  authority value of this service, , but greater than or equal to the receiving components default
*  authority, and if the message that triggered the transition is received from the client that is in
*  control of this service
*/
// NOTICE: replace true with the code necessary for checking the guard.  The parens are required around that code
#define urn_jaus_jss_core_MessageSet_CommandClass_SetAuthority_isAuthorityValid(incoming_pid,urn_jaus_jss_core_MessageSet_CommandClass_SetAuthority_inst) (true)
/**
*  True if the value of the authority code received from the client is less than or equal to the current
*  authority value of this service, , but greater than or equal to the receiving components default
*  authority, and if the message that triggered the transition is received from the client that is in
*  control of this service
*/
// NOTICE: replace true with the code necessary for checking the guard.  The parens are required around that code
#define isControllingClient(incoming_pid) (incoming_pid == current_client_pid)
/**
*  True if the current authority value of this service is less than the authority code of the client service
*  that triggered the corresponding transition, and if the message that triggered the transition
*  is not received from the client that is in control of this service
*/
// NOTICE: replace true with the code necessary for checking the guard.  The parens are required around that code
#define urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_isCurrentAuthorityLess(incoming_pid,urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_inst) (current_client_auth < urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_inst.RequestControlRec.AuthorityCode)

// Action definitions

/**
*  Convert the destination address into an unsigned integer such that the ComponentID maps to the least
*  significant byte, NodeID to the next least significant byte and SubsystemID maps onto the remaining
*  two bytes of the integer. Package the message as specified by the transport layer specification
*  and send it to its destination as per the specified priority.
*/
inline Action_Enqueue(){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: Action_Enqueue();\n");
}


/**
*  Package the message as specified by the transport layer specification and send it to all endpoints
*  in the local subsystem.
*/
inline Action_BroadcastLocalEnqueue(){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: Action_BroadcastLocalEnqueue();\n");
}


/**
*  Package the message as specified by the transport layer specification and send it to all endpoints
*  in on all subsystems.
*/
inline Action_BroadcastGlobalEnqueue(){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: Action_BroadcastGlobalEnqueue();");
}


/**
*  create the event
*/
inline urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_createEvent(inputMessage){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_createEvent(inputMessage);");
}


/**
*  Send Confirm Event Request message
*/
inline urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_Send_ConfirmEventRequest(sender_pid, inputMessage, incoming_pid){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_Send_ConfirmEventRequest(sender_pid, inputMessage, incoming_pid);");
}


/**
*  Reset the timeout period for the recurring event.
*/
inline urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_resetEventTimer(inputMessage){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_resetEventTimer(inputMessage);");
}


/**
*  update the event
*/
inline urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_updateEvent(inputMessage){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_updateEvent(inputMessage);");
}


/**
*  Send Reject Event Request message
*/
inline urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_Send_RejectEventRequest(sender_pid, inputMessage, incoming_pid){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_Action_Send_RejectEventRequest(sender_pid, inputMessage, incoming_pid);");
}


/**
*  update the event
*/
inline urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_Action_updateEvent(inputMessage){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_Action_updateEvent(inputMessage);");
}


/**
*  Send Confirm Event Request message
*/
inline urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_Action_Send_ConfirmEventRequest(sender_pid, inputMessage, incoming_pid){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_Action_Send_ConfirmEventRequest(sender_pid, inputMessage, incoming_pid);");
}


/**
*  Reset the timeout period for the recurring event.
*/
inline urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_Action_resetEventTimer(inputMessage){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_Action_resetEventTimer(inputMessage);");
}


/**
*  Send Reject Event Request message
*/
inline urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_Action_Send_RejectEventRequest(sender_pid, inputMessage, incoming_pid){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_Action_Send_RejectEventRequest(sender_pid, inputMessage, incoming_pid);");
}


/**
*  Send Reject Event Request message
*/
inline urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_Action_Send_RejectEventRequest(sender_pid, inputMessage, incoming_pid){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_Action_Send_RejectEventRequest(sender_pid, inputMessage, incoming_pid);");
}


/**
*  cancel the event
*/
inline urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_Action_cancelEvent(inputMessage){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_Action_cancelEvent(inputMessage);");
}


/**
*  Send Confirm Event Request message
*/
inline urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_Action_Send_ConfirmEventRequest(sender_pid, inputMessage, incoming_pid){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_Action_Send_ConfirmEventRequest(sender_pid, inputMessage, incoming_pid);");
}


/**
*  Stop evaluating the recurring event for timeouts.
*/
inline urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_Action_stopEventTimer(inputMessage){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_Action_stopEventTimer(inputMessage);");
}


/**
*  send an event notification
*/
inline Action_sendEvent(){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: Action_sendEvent();");
}


/**
*  Send Reject Event Request message
*/
inline Action_sendRejectEventRequest(){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: Action_sendRejectEventRequest();");
}


/**
*  Send a confirm control message with the specified response code to requesting client
*/
inline urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_Action_Send_ConfirmControl(sender_pid, param1, incoming_pid){
	printf("executing action: urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_Action_Send_ConfirmControl(sender_pid, param1, incoming_pid);\n");
	urn_jaus_jss_core_MessageSet_CommandClass_ConfirmControl conf;
	conf.ConfirmControlRec.ResponseCode = param1;
	if
		:: param1 == CONTROL_ACCEPTED ->
			service_status = READY;
		:: param1 == NOT_AVAILABLE ->
			service_status = EMERGENCY;
		:: param1 == INSUFFICIENT_AUTHORITY ->
			service_status = STANDBY;
	fi;
	ConfirmControl[incoming_pid] ! sender_pid, conf;
}


/**
*  Store the address of the client that sent the message that caused this action to be executed
*/
inline urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_Action_StoreAddress(inputMessage){
	printf("executing action: urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_Action_StoreAddress(inputMessage);\n");
	printf ("storing incoming_pid=%d and current_client_pid=%d", incoming_pid, current_client_pid);
	current_client_pid = incoming_pid;
}


/**
*  Set the current authority value of this service to the specified authority
*/
inline urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_Action_SetAuthority(inputMessage){
	printf("executing action: urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_Action_SetAuthority(inputMessage);\n");
	current_client_auth = inputMessage.RequestControlRec.AuthorityCode;
}


/**
*  Reset the timer
*/
inline urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_Action_ResetTimer(inputMessage){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_Action_ResetTimer(inputMessage);\n");
}


/**
*  Send Report Events message to the component that sent the query
*/
inline urn_jaus_jss_core_MessageSet_QueryClass_QueryEvents_Action_Send_ReportEvents(sender_pid, inputMessage, incoming_pid){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: urn_jaus_jss_core_MessageSet_QueryClass_QueryEvents_Action_Send_ReportEvents(sender_pid, inputMessage, incoming_pid);");
}


/**
*  Send Report Event Timeout message to the component that sent the query
*/
inline urn_jaus_jss_core_MessageSet_QueryClass_QueryEventTimeout_Action_Send_ReportEventTimeout(sender_pid, inputMessage, incoming_pid){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: urn_jaus_jss_core_MessageSet_QueryClass_QueryEventTimeout_Action_Send_ReportEventTimeout(sender_pid, inputMessage, incoming_pid);");
}


/**
*  cancel the event
*/
inline Action_cancelEvent(){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: Action_cancelEvent();");
}


/**
*  Send Confirm Event Request message
*/
inline Action_Send_ConfirmEventRequest(sender_pid){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: Action_Send_ConfirmEventRequest(sender_pid);");
}


/**
*  Stop evaluating the recurring event for timeouts.
*/
inline Action_stopEventTimer(){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: Action_stopEventTimer();");
}


/**
*  Send a Reject Control message to the client requesting release
*/
inline urn_jaus_jss_core_MessageSet_CommandClass_ReleaseControl_Action_Send_RejectControl(sender_pid, param1, incoming_pid){
	printf("executing action: urn_jaus_jss_core_MessageSet_CommandClass_ReleaseControl_Action_Send_RejectControl(sender_pid, param1, incoming_pid);\n");
	urn_jaus_jss_core_MessageSet_CommandClass_RejectControl conf;
	conf.RejectControlRec.ResponseCode = param1;
	RejectControl[incoming_pid] ! sender_pid, conf;
}


/**
*  Send a Report Authority message to querying client
*/
inline urn_jaus_jss_core_MessageSet_QueryClass_QueryAuthority_Action_Send_ReportAuthority(sender_pid, incoming_pid){
	printf("executing action: urn_jaus_jss_core_MessageSet_QueryClass_QueryAuthority_Action_Send_ReportAuthority(sender_pid, incoming_pid);\n");
	urn_jaus_jss_core_MessageSet_InformClass_ReportAuthority rpt;
	rpt.ReportAuthorityRec.AuthorityCode = current_client_auth;
	ReportAuthority[incoming_pid] ! sender_pid, rpt;
}


/**
*  Send a Report Timeout message to querying client
*/
inline urn_jaus_jss_core_MessageSet_QueryClass_QueryTimeout_Action_Send_ReportTimeout(sender_pid, incoming_pid){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: urn_jaus_jss_core_MessageSet_QueryClass_QueryTimeout_Action_Send_ReportTimeout(sender_pid, incoming_pid);");
}


/**
*  Send a Report Control message with the specified control value
*/
inline urn_jaus_jss_core_MessageSet_QueryClass_QueryControl_Action_Send_ReportControl(sender_pid, incoming_pid){
	printf("executing action: urn_jaus_jss_core_MessageSet_QueryClass_QueryControl_Action_Send_ReportControl(sender_pid, incoming_pid);\n");
	urn_jaus_jss_core_MessageSet_InformClass_ReportControl rpt;
	rpt.ReportControlRec.AuthorityCode = current_client_auth;
	rpt.ReportControlRec.SubsystemID = 0;
	rpt.ReportControlRec.NodeID = 0;
	rpt.ReportControlRec.ComponentID = 0;
	ReportControl[incoming_pid] ! sender_pid, rpt;
}


inline Action_init(){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: Action_init();");
}


/**
*  Set the current authority value of this service to the specified authority
*/
inline urn_jaus_jss_core_MessageSet_CommandClass_SetAuthority_Action_SetAuthority(inputMessage){
printf("executing action: urn_jaus_jss_core_MessageSet_CommandClass_SetAuthority_Action_SetAuthority(inputMessage);\n");
	current_client_auth = inputMessage.authorityRec.AuthorityCode;
}


/**
*  Send a Reject Control message to current controlling client
*/
inline urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_Action_Send_RejectControl(sender_pid, param1, incoming_pid){
 	printf("executing action: urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_Action_Send_RejectControl(sender_pid, param1, incoming_pid);\n");
	urn_jaus_jss_core_MessageSet_CommandClass_RejectControl conf;
	conf.RejectControlRec.ResponseCode = param1;
	RejectControl[incoming_pid] ! sender_pid, conf;
}


/**
*  Send a Reject Control message to controlling client
*/
inline Action_Send_RejectControl(sender_pid, param1){
	printf("executing action: Action_Send_RejectControl(sender_pid, param1);\n");
	urn_jaus_jss_core_MessageSet_CommandClass_RejectControl rej;
	rej.RejectControlRec.ResponseCode = param1;
	service_status = STANDBY;
	RejectControl[current_client_pid] ! sender_pid, rej;
}


/**
*  reset the timer
*/
inline Action_resetTimer(){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: Action_resetTimer();\n");
}

