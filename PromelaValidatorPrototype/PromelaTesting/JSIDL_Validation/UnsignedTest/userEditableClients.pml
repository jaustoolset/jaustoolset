/** 
 *  This Promela file was auto-generated using JTS on Fri Apr 08 10:55:23 EDT 2011.
 *  ID=urn.jts.TestingUnsigned  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

// Client implementation 
active [CLIENTS] proctype clientProcess(){
	// declare data for the messages
	urn_jts_TestingUnsigned_UnsignedTestMsg x;
	unsigned val:16 = 1;
	// put some values into the declared data here

CLIENT_START_STATE:
	do
		:: printf("send messages");
		val = val * 4;
		x.UnsignedRec.UnsignedByte = val;
		x.UnsignedRec.UnsignedShort = val;
		x.UnsignedRec.UnsignedInt = val;
		x.UnsignedRec.UnsignedLong = val;

		UnsignedTestMsg ! _pid, x;
	 od;
}
// End of client implementation



