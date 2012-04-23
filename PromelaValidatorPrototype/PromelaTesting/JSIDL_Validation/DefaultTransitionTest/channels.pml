/** 
 *  This Promela file was auto-generated using JTS on Thu Apr 14 11:49:18 EDT 2011.
 *  ID=urn.jts.DefaultTransTest  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 



// urn.jts.DefaultTransTest Input Channels
chan Message1 = [QUEUE_SIZE] of {pid, urn_jts_DefaultTransTest_Message1}; 
chan Message2 = [QUEUE_SIZE] of {pid, urn_jts_DefaultTransTest_Message2}; 
chan Message3 = [QUEUE_SIZE] of {pid, urn_jts_DefaultTransTest_Message3}; 
chan DefaultTransMsg = [QUEUE_SIZE] of {pid, urn_jts_DefaultTransTest_DefaultTransMsg}; 


// urn.jts.DefaultTransTest Output Channels
