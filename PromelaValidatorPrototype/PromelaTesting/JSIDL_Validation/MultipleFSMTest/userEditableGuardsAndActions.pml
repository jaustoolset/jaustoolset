/** 
 *  This Promela file was auto-generated using JTS on Wed Apr 13 16:18:34 EDT 2011.
 *  ID=urn.jts.ProtocolBehaviorTest  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

// Guard definitions

// Action definitions

inline Action_printTestEvent(){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: Action_printTestEvent();\n");
printf("received TestEvent from client: pid=%d\n", incoming_pid);
}


inline Action_printMessage(){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: Action_printMessage();\n");
}

