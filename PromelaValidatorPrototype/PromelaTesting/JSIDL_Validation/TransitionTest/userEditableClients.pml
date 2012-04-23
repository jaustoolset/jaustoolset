/** 
 *  This Promela file was auto-generated using JTS on Thu Apr 14 11:45:14 EDT 2011.
 *  ID=urn.jts.TransitionTest  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

// Client implementation 
active [CLIENTS] proctype clientProcess(){
	// declare data for the messages
	urn_jts_TransitionTest_Message1 urn_jts_TransitionTest_Message1_impl;
	urn_jts_TransitionTest_Message2 urn_jts_TransitionTest_Message2_impl;
	urn_jts_TransitionTest_Message3 urn_jts_TransitionTest_Message3_impl;
	urn_jts_TransitionTest_PushMessage urn_jts_TransitionTest_PushMessage_impl;
	urn_jts_TransitionTest_PopMessage urn_jts_TransitionTest_PopMessage_impl;
	urn_jts_TransitionTest_ExitDefaultStateMessage urn_jts_TransitionTest_ExitDefaultStateMessage_impl;

	// put some values into the declared data here

CLIENT_START_STATE:
	do
		:: printf("send messages");
	// do guarded push/pop
	guardValue = 1;
	PushMessage ! _pid, urn_jts_TransitionTest_PushMessage_impl;
	PopMessage ! _pid, urn_jts_TransitionTest_PopMessage_impl;
	
	// do unguarded push/pop
	guardValue = 0;
	PushMessage ! _pid, urn_jts_TransitionTest_PushMessage_impl;
	PopMessage ! _pid, urn_jts_TransitionTest_PopMessage_impl;
	
	// guarded internal
	guardValue = 1;
	Message1 ! _pid, urn_jts_TransitionTest_Message1_impl;
	// unguarded internal
	guardValue = 0;
	Message1 ! _pid, urn_jts_TransitionTest_Message1_impl;
	
	// guarded from default state
	guardValue = 1;
	ExitDefaultStateMessage ! _pid, urn_jts_TransitionTest_ExitDefaultStateMessage_impl;
	Message3 ! _pid, urn_jts_TransitionTest_Message3_impl;
	
	guardValue = 0;
	ExitDefaultStateMessage ! _pid, urn_jts_TransitionTest_ExitDefaultStateMessage_impl;
	Message3 ! _pid, urn_jts_TransitionTest_Message3_impl;
	
	//guarded simple
	guardValue = 1;
	Message2 ! _pid, urn_jts_TransitionTest_Message2_impl;
	Message3 ! _pid, urn_jts_TransitionTest_Message3_impl;

	//unguarded simple
	guardValue = 0;
	Message2 ! _pid, urn_jts_TransitionTest_Message2_impl;	
	Message3 ! _pid, urn_jts_TransitionTest_Message3_impl;
	
	
	
	 od;
}
// End of client implementation



