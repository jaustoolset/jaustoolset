/** 
 *  This Promela file was auto-generated using JTS on Wed Apr 13 15:59:47 EDT 2011.
 *  ID=urn.jts.EntryExitActionTest  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

// Client implementation 
active [CLIENTS] proctype clientProcess(){
	// declare data for the messages
	urn_jts_EntryExitActionTest_Message3 urn_jts_EntryExitActionTest_Message3_impl;
	urn_jts_EntryExitActionTest_Message2 urn_jts_EntryExitActionTest_Message2_impl;
	urn_jts_EntryExitActionTest_Message1 urn_jts_EntryExitActionTest_Message1_impl;
	urn_jts_EntryExitActionTest_PushMessage urn_jts_EntryExitActionTest_PushMessage_impl;
	urn_jts_EntryExitActionTest_PopMessage urn_jts_EntryExitActionTest_PopMessage_impl;

	// put some values into the declared data here

CLIENT_START_STATE:
	do
		:: printf("send messages");
	
		PushMessage ! _pid, urn_jts_EntryExitActionTest_PushMessage_impl;	
		PopMessage ! _pid, urn_jts_EntryExitActionTest_PopMessage_impl;
		Message1 ! _pid, urn_jts_EntryExitActionTest_Message1_impl;
		Message2 ! _pid, urn_jts_EntryExitActionTest_Message2_impl;
		Message3 ! _pid, urn_jts_EntryExitActionTest_Message3_impl;
	od;
}
// End of client implementation



