/** 
 *  This Promela file was auto-generated using JTS on Wed Apr 13 15:56:34 EDT 2011.
 *  ID=urn.jts.SignedTest  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

// Client implementation 
active [CLIENTS] proctype clientProcess(){
	// declare data for the messages
	urn_jts_SignedTest_SignedTestMessage urn_jts_SignedTest_SignedTestMessage_impl;
	urn_jts_SignedTest_SignedTestMessage_impl.SignedRec.SignedInt = 27;
	urn_jts_SignedTest_SignedTestMessage_impl.SignedRec.SignedShort = 28;
	urn_jts_SignedTest_SignedTestMessage_impl.SignedRec.SignedLong = 29;
	urn_jts_SignedTest_SignedTestMessage_impl.SignedRec.SignedByte = -30;
	
	// put some values into the declared data here

CLIENT_START_STATE:
	do
		:: printf("send messages");
	
	SignedTestMessage ! _pid, urn_jts_SignedTest_SignedTestMessage_impl;
	 od;
}
// End of client implementation



