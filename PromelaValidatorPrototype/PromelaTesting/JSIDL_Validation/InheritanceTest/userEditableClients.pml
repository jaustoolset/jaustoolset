/** 
 *  This Promela file was auto-generated using JTS on Wed Apr 13 15:57:49 EDT 2011.
 *  ID=urn.jts.InheritanceTest  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

// Client implementation 
active [CLIENTS] proctype clientProcess(){
	// declare data for the messages
	urn_jts_InheritanceTest_Message2 urn_jts_InheritanceTest_Message2_impl;
	urn_jts_InheritanceTest_Message1 urn_jts_InheritanceTest_Message1_impl;

	// put some values into the declared data here

CLIENT_START_STATE:
	do
		:: printf("send messages");
	
	Message2 ! _pid, urn_jts_InheritanceTest_Message2_impl;
	
	Message1 ! _pid, urn_jts_InheritanceTest_Message1_impl;
	 od;
}
// End of client implementation



