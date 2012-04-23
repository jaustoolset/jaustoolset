/** 
 *  This Promela file was auto-generated using JTS on Wed Apr 13 16:00:32 EDT 2011.
 *  ID=urn.jts.TestingUnsigned  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

// Guard definitions

// Action definitions

inline Action_printResults(){
unsigned tmp:16 = urn_jts_TestingUnsigned_UnsignedTestMsg_inst.UnsignedRec.UnsignedLong * 4;
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("UnsignedByte = %d\n", urn_jts_TestingUnsigned_UnsignedTestMsg_inst.UnsignedRec.UnsignedByte);
printf("UnsignedShort = %d\n", urn_jts_TestingUnsigned_UnsignedTestMsg_inst.UnsignedRec.UnsignedShort);
printf("UnsignedInt = %d\n", urn_jts_TestingUnsigned_UnsignedTestMsg_inst.UnsignedRec.UnsignedInt);
printf("UnsignedLong = %d\n", urn_jts_TestingUnsigned_UnsignedTestMsg_inst.UnsignedRec.UnsignedLong);
printf("executing action: Action_printResults();");
}

