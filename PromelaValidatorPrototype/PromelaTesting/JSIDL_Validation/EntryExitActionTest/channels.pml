/** 
 *  This Promela file was auto-generated using JTS on Wed Apr 13 15:59:47 EDT 2011.
 *  ID=urn.jts.EntryExitActionTest  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 



// urn.jts.EntryExitActionTest Input Channels
chan Message3 = [QUEUE_SIZE] of {pid, urn_jts_EntryExitActionTest_Message3}; 
chan Message2 = [QUEUE_SIZE] of {pid, urn_jts_EntryExitActionTest_Message2}; 
chan Message1 = [QUEUE_SIZE] of {pid, urn_jts_EntryExitActionTest_Message1}; 
chan PushMessage = [QUEUE_SIZE] of {pid, urn_jts_EntryExitActionTest_PushMessage}; 
chan PopMessage = [QUEUE_SIZE] of {pid, urn_jts_EntryExitActionTest_PopMessage}; 


// urn.jts.EntryExitActionTest Output Channels
