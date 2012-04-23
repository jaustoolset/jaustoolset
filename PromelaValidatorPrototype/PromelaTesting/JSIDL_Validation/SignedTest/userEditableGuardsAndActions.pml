/** 
 *  This Promela file was auto-generated using JTS on Wed Apr 13 15:56:34 EDT 2011.
 *  ID=urn.jts.SignedTest  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

// Guard definitions

// Action definitions

inline Action_printSignedResults(){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: Action_printSignedResults();\n");
printf("ResponseData %d, %d, %d, %d.\n",urn_jts_SignedTest_SignedTestMessage_inst.SignedRec.SignedByte, urn_jts_SignedTest_SignedTestMessage_inst.SignedRec.SignedShort, urn_jts_SignedTest_SignedTestMessage_inst.SignedRec.SignedInt, urn_jts_SignedTest_SignedTestMessage_inst.SignedRec.SignedLong);

}

