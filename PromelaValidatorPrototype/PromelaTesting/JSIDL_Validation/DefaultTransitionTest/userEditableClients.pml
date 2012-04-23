/** 
 *  This Promela file was auto-generated using JTS on Thu Apr 14 11:49:18 EDT 2011.
 *  ID=urn.jts.DefaultTransTest  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

// Client implementation 
active [CLIENTS] proctype clientProcess(){
	// declare data for the messages
	urn_jts_DefaultTransTest_Message1 urn_jts_DefaultTransTest_Message1_impl;
	urn_jts_DefaultTransTest_Message2 urn_jts_DefaultTransTest_Message2_impl;
	urn_jts_DefaultTransTest_Message3 urn_jts_DefaultTransTest_Message3_impl;
	urn_jts_DefaultTransTest_DefaultTransMsg urn_jts_DefaultTransTest_DefaultTransMsg_impl;

	// put some values into the declared data here

CLIENT_START_STATE:
	do
		:: printf("send messages");
	
	Message1 ! _pid, urn_jts_DefaultTransTest_Message1_impl;
	Message3 ! _pid, urn_jts_DefaultTransTest_Message3_impl;
	DefaultTransMsg ! _pid, urn_jts_DefaultTransTest_DefaultTransMsg_impl;
	Message3 ! _pid, urn_jts_DefaultTransTest_Message3_impl;
	Message2 ! _pid, urn_jts_DefaultTransTest_Message2_impl;
	Message3 ! _pid, urn_jts_DefaultTransTest_Message3_impl;
	guardValue = 1;
	Message1 ! _pid, urn_jts_DefaultTransTest_Message1_impl;
	Message3 ! _pid, urn_jts_DefaultTransTest_Message3_impl;	
	DefaultTransMsg ! _pid, urn_jts_DefaultTransTest_DefaultTransMsg_impl;
	 od;
}
// End of client implementation



