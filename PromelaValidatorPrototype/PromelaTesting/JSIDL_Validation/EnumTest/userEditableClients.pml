/** 
 *  This Promela file was auto-generated using JTS on Wed Apr 13 15:58:22 EDT 2011.
 *  ID=urn.jts.EnumTest  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

// Client implementation 
active [CLIENTS] proctype clientProcess(){
	// declare data for the messages
	urn_jts_EnumTest_EnumTestMessage urn_jts_EnumTest_EnumTestMessage_impl;

	// put some values into the declared data here

CLIENT_START_STATE:
	do
		:: printf("send messages");
		urn_jts_EnumTest_EnumTestMessage_impl.EnumRec.EnumField = enum;
		EnumTestMessage ! _pid, urn_jts_EnumTest_EnumTestMessage_impl;
		urn_jts_EnumTest_EnumTestMessage_impl.EnumRec.EnumField = enumwithspaces;
		EnumTestMessage ! _pid, urn_jts_EnumTest_EnumTestMessage_impl;
		
		
	 od;
}
// End of client implementation



