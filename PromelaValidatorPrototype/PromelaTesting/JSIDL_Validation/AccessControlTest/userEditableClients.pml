/** 
 *  This Promela file was auto-generated using JTS on Mon May 16 12:54:31 EDT 2011.
 *  ID=urn:jaus:jss:core:AccessControl  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 
byte auth_code = 120;

// Client implementation 
active [CLIENTS] proctype clientProcess(){
	// declare data for the messages
	urn_jaus_jss_core_MessageSet_CommandClass_RequestControl urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_impl;
	urn_jaus_jss_core_MessageSet_CommandClass_ReleaseControl urn_jaus_jss_core_MessageSet_CommandClass_ReleaseControl_impl;
	urn_jaus_jss_core_MessageSet_QueryClass_QueryControl urn_jaus_jss_core_MessageSet_QueryClass_QueryControl_impl;
	urn_jaus_jss_core_MessageSet_QueryClass_QueryAuthority urn_jaus_jss_core_MessageSet_QueryClass_QueryAuthority_impl;
	urn_jaus_jss_core_MessageSet_CommandClass_SetAuthority urn_jaus_jss_core_MessageSet_CommandClass_SetAuthority_impl;
	urn_jaus_jss_core_MessageSet_QueryClass_QueryTimeout urn_jaus_jss_core_MessageSet_QueryClass_QueryTimeout_impl;

	// put some values into the declared data here
	pid incoming_pid;
	urn_jaus_jss_core_MessageSet_InformClass_ReportControl rptCtrl;
	urn_jaus_jss_core_MessageSet_CommandClass_RejectControl rejCtrl;
	urn_jaus_jss_core_MessageSet_CommandClass_ConfirmControl confCtrl;
	urn_jaus_jss_core_MessageSet_InformClass_ReportAuthority rptAuth;
	urn_jaus_jss_core_MessageSet_InformClass_ReportTimeout rptTimeout;
	urn_jaus_jss_core_MessageSet_InformClass_ReportStatus rptStatus;
	urn_jaus_jss_core_MessageSet_CommandClass_ConfirmControl confControl;
	urn_jaus_jss_core_MessageSet_CommandClass_ReleaseControl relControl;

	NOT_CONTROLLING:
	do
		:: auth_code = auth_code + 6;
		:: auth_code = auth_code - 5;
		
		:: printf("send message RequestControl with auth=%d\n", auth_code);
			urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_impl.RequestControlRec.AuthorityCode = auth_code;
			RequestControl ! _pid, urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_impl;
			break;
	od;
	
	REQUESTING:
	do
		:: ConfirmControl[_pid] ? incoming_pid, confControl ->
			printf("ConfirmControl : %e\n", confControl.ConfirmControlRec.ResponseCode);
			if
				:: confControl.ConfirmControlRec.ResponseCode == CONTROL_ACCEPTED ->
					printf("accepted\n");
					goto CONTROLLING;
				:: else ->
					goto NOT_CONTROLLING;
			fi;
			
		:: RejectControl[_pid] ? incoming_pid, rejCtrl ->
			goto NOT_CONTROLLING;
	od;

CONTROLLING:
	do
		:: ReleaseControl ! _pid, relControl ->
			goto PRE_RELEASING;
			
	od;
	
PRE_RELEASING:
	do
		:: printf("going to RELEASING\n");
		goto RELEASING;
	od;
RELEASING:
	do
		:: ConfirmControl[_pid] ? incoming_pid, confControl ->
			printf("ConfirmControl : %e\n", confControl.ConfirmControlRec.ResponseCode);
			if
				:: confControl.ConfirmControlRec.ResponseCode == NOT_AVAILABLE || confControl.ConfirmControlRec.ResponseCode == INSUFFICIENT_AUTHORITY ->
					goto NOT_CONTROLLING;
			fi;
		
		:: RejectControl[_pid] ? incoming_pid, rejCtrl ->
			goto NOT_CONTROLLING;
	od;

}
// End of client implementation



