/** 
 *  This Promela file was auto-generated using JTS on Fri May 13 14:02:55 EDT 2011.
 *  ID=urn:jaus:jss:core:Management  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

// Client implementation 
active [CLIENTS] proctype clientProcess(){
	// declare data for the messages
	//urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_impl;
	//urn_jaus_jss_core_MessageSet_CommandClass_CreateCommandEvent urn_jaus_jss_core_MessageSet_CommandClass_CreateCommandEvent_impl;
	//urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_impl;
	//urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_impl;
	//urn_jaus_jss_core_MessageSet_QueryClass_QueryEvents urn_jaus_jss_core_MessageSet_QueryClass_QueryEvents_impl;
	//urn_jaus_jss_core_MessageSet_QueryClass_QueryEventTimeout urn_jaus_jss_core_MessageSet_QueryClass_QueryEventTimeout_impl;
	urn_jaus_jss_core_MessageSet_CommandClass_RequestControl urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_impl;
	urn_jaus_jss_core_MessageSet_CommandClass_ReleaseControl urn_jaus_jss_core_MessageSet_CommandClass_ReleaseControl_impl;
	urn_jaus_jss_core_MessageSet_QueryClass_QueryControl urn_jaus_jss_core_MessageSet_QueryClass_QueryControl_impl;
	urn_jaus_jss_core_MessageSet_QueryClass_QueryAuthority urn_jaus_jss_core_MessageSet_QueryClass_QueryAuthority_impl;
	urn_jaus_jss_core_MessageSet_CommandClass_SetAuthority urn_jaus_jss_core_MessageSet_CommandClass_SetAuthority_impl;
	urn_jaus_jss_core_MessageSet_QueryClass_QueryTimeout urn_jaus_jss_core_MessageSet_QueryClass_QueryTimeout_impl;
	urn_jaus_jss_core_MessageSet_CommandClass_Shutdown urn_jaus_jss_core_MessageSet_CommandClass_Shutdown_impl;
	urn_jaus_jss_core_MessageSet_CommandClass_Standby urn_jaus_jss_core_MessageSet_CommandClass_Standby_impl;
	urn_jaus_jss_core_MessageSet_CommandClass_Resume urn_jaus_jss_core_MessageSet_CommandClass_Resume_impl;
	urn_jaus_jss_core_MessageSet_CommandClass_Reset urn_jaus_jss_core_MessageSet_CommandClass_Reset_impl;
	urn_jaus_jss_core_MessageSet_CommandClass_SetEmergency urn_jaus_jss_core_MessageSet_CommandClass_SetEmergency_impl;
	urn_jaus_jss_core_MessageSet_CommandClass_ClearEmergency urn_jaus_jss_core_MessageSet_CommandClass_ClearEmergency_impl;
	urn_jaus_jss_core_MessageSet_QueryClass_QueryStatus urn_jaus_jss_core_MessageSet_QueryClass_QueryStatus_impl;

	// put some values into the declared data here
	pid incoming_pid;
	urn_jaus_jss_core_MessageSet_InformClass_ReportControl rptCtrl;
	urn_jaus_jss_core_MessageSet_CommandClass_RejectControl rejCtrl;
	urn_jaus_jss_core_MessageSet_CommandClass_ConfirmControl confCtrl;
	urn_jaus_jss_core_MessageSet_InformClass_ReportAuthority rptAuth;
	urn_jaus_jss_core_MessageSet_InformClass_ReportTimeout rptTimeout;
	urn_jaus_jss_core_MessageSet_InformClass_ReportStatus rptStatus;
	urn_jaus_jss_core_MessageSet_CommandClass_ConfirmControl confControl;
	
CLIENT_START_STATE:
	do
		:: printf("send message RequestControl with auth=120\n");
			urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_impl.RequestControlRec.AuthorityCode = 120;
			RequestControl ! _pid, urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_impl;
			QueryStatus ! _pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryStatus_impl;
			break;
	od;
STATE0:
	do
		:: ConfirmControl[_pid] ? incoming_pid, confControl ->
			printf("ConfirmControl : %e\n", confControl.ConfirmControlRec.ResponseCode);
			break;
	od;
STATE1:
	do
		:: ReportStatus[_pid] ? incoming_pid, rptStatus ->
		   printf("status = %e\n", rptStatus.ReportStatusRec.Status);
		  break;
	od;
STATE2:
	do
		:: Resume ! _pid, urn_jaus_jss_core_MessageSet_CommandClass_Resume_impl;
			QueryStatus ! _pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryStatus_impl;
		   printf("resume ...\n");
		  break;
	od;
STATE3:
	do
		:: ReportStatus[_pid] ? incoming_pid, rptStatus ->
		   printf("status = %e\n", rptStatus.ReportStatusRec.Status);
		  break;
	od;
STATE4:
	do
		:: Standby ! _pid, urn_jaus_jss_core_MessageSet_CommandClass_Standby_impl;
			QueryStatus ! _pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryStatus_impl;
			printf("standby\n");
		  break;
	od;
STATE5:
	do
		:: ReportStatus[_pid] ? incoming_pid, rptStatus ->
		   printf("status = %e\n", rptStatus.ReportStatusRec.Status);
		  break;
	od;
STATE6:
	do
		:: printf("resume ...\n");
		   Resume ! _pid, urn_jaus_jss_core_MessageSet_CommandClass_Resume_impl;
			QueryStatus ! _pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryStatus_impl;
		  break;
	od;
STATE7:
	do
		:: ReportStatus[_pid] ? incoming_pid, rptStatus ->
		   printf("status = %e\n", rptStatus.ReportStatusRec.Status);
		  break;
	od;
STATE8:
	do
		:: SetEmergency ! _pid, urn_jaus_jss_core_MessageSet_CommandClass_SetEmergency_impl;
			QueryStatus ! _pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryStatus_impl;
			printf("SetEmergency\n");
			break;
	od;
	
STATE9:
	do
		:: ReportStatus[_pid] ? incoming_pid, rptStatus ->
		   printf("status = %e\n", rptStatus.ReportStatusRec.Status);
		  break;
	od;
STATE10:
	do
		:: ClearEmergency ! _pid, urn_jaus_jss_core_MessageSet_CommandClass_ClearEmergency_impl;
			QueryStatus ! _pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryStatus_impl;
			printf("ClearEmergency\n");
			break;
	od;
STATE11:
	do
		:: ReportStatus[_pid] ? incoming_pid, rptStatus ->
		   printf("status = %e\n", rptStatus.ReportStatusRec.Status);
		  break;
	od;
STATE12:
	do
		:: ReleaseControl ! _pid, urn_jaus_jss_core_MessageSet_CommandClass_ReleaseControl_impl;
			QueryStatus ! _pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryStatus_impl;
			printf("ReleaseControl\n");
			break;
	od;
STATE13:
	do
		:: ReportStatus[_pid] ? incoming_pid, rptStatus ->
		   printf("status = %e\n", rptStatus.ReportStatusRec.Status);
		  break;
	od;



}
// End of client implementation



