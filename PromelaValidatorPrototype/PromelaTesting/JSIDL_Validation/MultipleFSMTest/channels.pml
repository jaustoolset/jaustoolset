/** 
 *  This Promela file was auto-generated using JTS on Wed Apr 13 16:18:34 EDT 2011.
 *  ID=urn.jts.ProtocolBehaviorTest  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 



// urn.jts.ProtocolBehaviorTest Input Channels
chan Message1 = [QUEUE_SIZE] of {pid, urn_jts_ProtocolBehaviorTest_Message1}; 
chan UnsignedTestMsg = [QUEUE_SIZE] of {pid, urn_jts_ProtocolBehaviorTest_UnsignedTestMsg}; 


// urn.jts.ProtocolBehaviorTest Output Channels
chan UnsignedResponseMessage[CLIENT_CHANNELS] = [QUEUE_SIZE] of {pid, urn_jts_ProtocolBehaviorTest_UnsignedResponseMessage}; 

chan TestEvent = [QUEUE_SIZE] of {pid};
