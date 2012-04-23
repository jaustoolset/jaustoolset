/** 
 *  This Promela file was auto-generated using JTS on Wed Apr 13 15:57:49 EDT 2011.
 *  ID=urn.jts.InheritanceTest  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

// Guard definitions

// Action definitions

/**
*  Convert the destination address into an unsigned integer such that the ComponentID maps to the least
*  significant byte, NodeID to the next least significant byte and SubsystemID maps onto the remaining
*  two bytes of the integer. Package the message as specified by the transport layer specification
*  and send it to its destination as per the specified priority.
*/
inline Action_Enqueue(){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: Action_Enqueue();");
}


/**
*  Package the message as specified by the transport layer specification and send it to all endpoints
*  in the local subsystem.
*/
inline Action_BroadcastLocalEnqueue(){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: Action_BroadcastLocalEnqueue();");
}


/**
*  Package the message as specified by the transport layer specification and send it to all endpoints
*  in on all subsystems.
*/
inline Action_BroadcastGlobalEnqueue(){
 //Replace this print statement with a code line ending with a ;
// Ending a line with a back-slash allows the definiton to continue on the next line.
// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.
printf("executing action: Action_BroadcastGlobalEnqueue();");
}

