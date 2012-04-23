/** 
 *  This Promela file was auto-generated using JTS on Wed Apr 13 15:57:03 EDT 2011.
 *  ID=urn.jts.MessageTest  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

// Guard definitions

// Action definitions
	urn_jts_MessageTest_UnsignedResponseMessage urn_jts_MessageTest_UnsignedResponseMessage_inst;

inline Action_sendResponse(){
	//Replace this print statement with a code line ending with a ;
	// Ending a line with a back-slash allows the definiton to continue on the next line.
	// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
	printf("executing action: Action_sendResponse();\n");
	urn_jts_MessageTest_UnsignedResponseMessage_inst.UnsignedRec.UnsignedByte = 1;
	urn_jts_MessageTest_UnsignedResponseMessage_inst.UnsignedRec.UnsignedShort = 5;
	urn_jts_MessageTest_UnsignedResponseMessage_inst.UnsignedRec.UnsignedInt = 10;
	urn_jts_MessageTest_UnsignedResponseMessage_inst.UnsignedRec.UnsignedLong = 27;
	
	UnsignedResponseMessage[incoming_pid] ! _pid, urn_jts_MessageTest_UnsignedResponseMessage_inst;
}


inline Action_printData(){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: Action_printData();\n");
printf("ResponseData %d, %d, %d, %d.\n",urn_jts_MessageTest_UnsignedResponseMessage_inst.UnsignedRec.UnsignedByte, urn_jts_MessageTest_UnsignedResponseMessage_inst.UnsignedRec.UnsignedShort, urn_jts_MessageTest_UnsignedResponseMessage_inst.UnsignedRec.UnsignedInt, urn_jts_MessageTest_UnsignedResponseMessage_inst.UnsignedRec.UnsignedLong);
}

