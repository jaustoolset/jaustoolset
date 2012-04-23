/** 
 *  This Promela file was auto-generated using JTS on Wed Apr 13 15:57:03 EDT 2011.
 *  ID=urn.jts.MessageTest  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

// Client implementation 
active [CLIENTS] proctype clientProcess(){
	// declare data for the messages
	urn_jts_MessageTest_UnsignedTestMsg urn_jts_MessageTest_UnsignedTestMsg_impl;
	urn_jts_MessageTest_UnsignedResponseMessage Message_inst;
	// put some values into the declared data here
	pid incoming_pid;
	
CLIENT_START_STATE:
	do
		:: printf("send messages");
	
		UnsignedTestMsg ! _pid, urn_jts_MessageTest_UnsignedTestMsg_impl;
		goto RECEIVE_MESSAGES;
	 od;
	 
RECEIVE_MESSAGES:
	do
		:: UnsignedResponseMessage[_pid] ? incoming_pid, Message_inst ->
		printf("ResponseData %d, %d, %d, %d.\n",Message_inst.UnsignedRec.UnsignedByte, Message_inst.UnsignedRec.UnsignedShort, Message_inst.UnsignedRec.UnsignedInt, Message_inst.UnsignedRec.UnsignedLong);

	od;
}
// End of client implementation



