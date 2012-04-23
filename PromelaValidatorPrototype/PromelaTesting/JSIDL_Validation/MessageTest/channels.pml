/** 
 *  This Promela file was auto-generated using JTS on Wed Apr 13 15:57:03 EDT 2011.
 *  ID=urn.jts.MessageTest  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 



// urn.jts.MessageTest Input Channels
chan UnsignedTestMsg = [QUEUE_SIZE] of {pid, urn_jts_MessageTest_UnsignedTestMsg}; 


// urn.jts.MessageTest Output Channels
chan UnsignedResponseMessage[CLIENT_CHANNELS] = [QUEUE_SIZE] of {pid, urn_jts_MessageTest_UnsignedResponseMessage}; 
