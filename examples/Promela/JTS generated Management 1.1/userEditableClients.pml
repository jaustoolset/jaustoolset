/** 
 *  This Promela file was auto-generated using JTS on Mon Jun 13 16:10:57 EDT 2011.
 *  ID=urn:jaus:jss:core:Management  vsersion=1.1
 *  This file is intended to be edited by the user prior to using the model for validation.
 *  This file contains modifiable code extracted from the JSIDL that was used to generate it.
 */ 

// Client implementation 
active [CLIENTS] proctype clientProcess(){
	// declare data for the messages
	urn_jaus_jss_core_AccessControl_RequestControl urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_impl;
	urn_jaus_jss_core_AccessControl_ReleaseControl urn_jaus_jss_core_MessageSet_CommandClass_ReleaseControl_impl;
	urn_jaus_jss_core_AccessControl_QueryControl urn_jaus_jss_core_MessageSet_QueryClass_QueryControl_impl;
	urn_jaus_jss_core_AccessControl_QueryAuthority urn_jaus_jss_core_MessageSet_QueryClass_QueryAuthority_impl;
	urn_jaus_jss_core_AccessControl_SetAuthority urn_jaus_jss_core_MessageSet_CommandClass_SetAuthority_impl;
	urn_jaus_jss_core_AccessControl_QueryTimeout urn_jaus_jss_core_MessageSet_QueryClass_QueryTimeout_impl;
	urn_jaus_jss_core_Management_Shutdown urn_jaus_jss_core_MessageSet_CommandClass_Shutdown_impl;
	urn_jaus_jss_core_Management_Standby urn_jaus_jss_core_MessageSet_CommandClass_Standby_impl;
	urn_jaus_jss_core_Management_Resume urn_jaus_jss_core_MessageSet_CommandClass_Resume_impl;
	urn_jaus_jss_core_Management_Reset urn_jaus_jss_core_MessageSet_CommandClass_Reset_impl;
	urn_jaus_jss_core_Management_SetEmergency urn_jaus_jss_core_MessageSet_CommandClass_SetEmergency_impl;
	urn_jaus_jss_core_Management_ClearEmergency urn_jaus_jss_core_MessageSet_CommandClass_ClearEmergency_impl;
	urn_jaus_jss_core_Management_QueryStatus urn_jaus_jss_core_MessageSet_QueryClass_QueryStatus_impl;

	// put some values into the declared data here
	pid incoming_pid;
	urn_jaus_jss_core_AccessControl_ReportControl rptCtrl;
	urn_jaus_jss_core_AccessControl_RejectControl rejCtrl;
	urn_jaus_jss_core_AccessControl_ReportAuthority rptAuth;
	urn_jaus_jss_core_AccessControl_ReportTimeout rptTimeout;
	urn_jaus_jss_core_Management_ReportStatus rptStatus;
	urn_jaus_jss_core_AccessControl_ConfirmControl confControl;
	
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



