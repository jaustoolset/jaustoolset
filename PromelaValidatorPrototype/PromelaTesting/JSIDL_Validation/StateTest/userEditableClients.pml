/** 
 *  This Promela file was auto-generated using JTS on Wed Apr 13 15:56:20 EDT 2011.
 *  ID=urn.jts.StateTest  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

// Client implementation 
active [CLIENTS] proctype clientProcess(){
	// declare data for the messages
	urn_jts_StateTest_Message2 urn_jts_StateTest_Message2_impl;
	urn_jts_StateTest_Message1 urn_jts_StateTest_Message1_impl;

	// put some values into the declared data here

CLIENT_START_STATE:
	do
		:: printf("send messages");
	
	Message1 ! _pid, urn_jts_StateTest_Message1_impl;
	Message2 ! _pid, urn_jts_StateTest_Message2_impl;
	
	 od;
}
// End of client implementation



