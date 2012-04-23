/** 
 *  This Promela file was auto-generated using JTS on Wed Apr 13 15:58:22 EDT 2011.
 *  ID=urn.jts.EnumTest  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

// Guard definitions

// Action definitions

inline Action_printEnumResults(){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: Action_printEnumResults(); with enum value = ");
printm(urn_jts_EnumTest_EnumTestMessage_inst.EnumRec.EnumField);
printf("\n");
}

