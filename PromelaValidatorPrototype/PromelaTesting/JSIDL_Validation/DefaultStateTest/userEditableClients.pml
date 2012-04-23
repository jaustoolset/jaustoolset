/** 
 *  This Promela file was auto-generated using JTS on Thu Apr 14 11:45:35 EDT 2011.
 *  ID=urn.jts.DefaultStateTest  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

// Client implementation 
active [CLIENTS] proctype clientProcess(){
	// declare data for the messages
	urn_jts_DefaultStateTest_Message2 urn_jts_DefaultStateTest_Message2_impl;
	urn_jts_DefaultStateTest_Message1 urn_jts_DefaultStateTest_Message1_impl;
	urn_jts_DefaultStateTest_Message3 urn_jts_DefaultStateTest_Message3_impl;

	// put some values into the declared data here

CLIENT_START_STATE:
	do
		:: printf("send messages");
	urn_jts_DefaultStateTest_Message1_impl.DefaultHeaderRec.MessageID = 0;
	Message1 ! _pid, urn_jts_DefaultStateTest_Message1_impl;
	Message3 ! _pid, urn_jts_DefaultStateTest_Message3_impl;
	urn_jts_DefaultStateTest_Message1_impl.DefaultHeaderRec.MessageID = 1;
	Message1 ! _pid, urn_jts_DefaultStateTest_Message1_impl;
	Message3 ! _pid, urn_jts_DefaultStateTest_Message3_impl;
	Message2 ! _pid, urn_jts_DefaultStateTest_Message2_impl;
	
	 od;
}
// End of client implementation



