/** 
 *  This Promela file was auto-generated using JTS on Thu Apr 14 11:45:14 EDT 2011.
 *  ID=urn.jts.TransitionTest  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 



// urn.jts.TransitionTest Input Channels
chan Message1 = [QUEUE_SIZE] of {pid, urn_jts_TransitionTest_Message1}; 
chan Message2 = [QUEUE_SIZE] of {pid, urn_jts_TransitionTest_Message2}; 
chan Message3 = [QUEUE_SIZE] of {pid, urn_jts_TransitionTest_Message3}; 
chan PushMessage = [QUEUE_SIZE] of {pid, urn_jts_TransitionTest_PushMessage}; 
chan PopMessage = [QUEUE_SIZE] of {pid, urn_jts_TransitionTest_PopMessage}; 
chan ExitDefaultStateMessage = [QUEUE_SIZE] of {pid, urn_jts_TransitionTest_ExitDefaultStateMessage}; 


// urn.jts.TransitionTest Output Channels
