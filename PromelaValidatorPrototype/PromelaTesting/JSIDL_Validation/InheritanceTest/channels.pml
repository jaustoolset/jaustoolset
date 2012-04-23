/** 
 *  This Promela file was auto-generated using JTS on Wed Apr 13 15:57:49 EDT 2011.
 *  ID=urn.jts.InheritanceTest  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 



// urn:jaus:jss:core:Transport Input Channels


// urn:jaus:jss:core:Transport Output Channels

//  The Send event is used by a derived service to hand over the payload data that it needs to send to a specified
//  destination endpoint via the transport layer. Upon receipt, this service prepares the message
//  for delivery (marshals the message) as specified by the transport layer specification and attempts
//  to deliver the encapsulated payload data to the destination endpoint.
chan Send = [QUEUE_SIZE] of {pid};

//  The Broadcast Local event is the same as the Send event except that this service sends the payload
//  to all endpoints within the subsystem.
chan BroadcastLocal = [QUEUE_SIZE] of {pid};

//  The Broadcast Global event is the same as the Send event except that this service sends the payload
//  to all endpoints on all subsystems.
chan BroadcastGlobal = [QUEUE_SIZE] of {pid};

//  The Receive event is used by derived services to hand over the data that was sent by another service
//  to an endpoint established by this service.
chan Receive = [QUEUE_SIZE] of {pid};


// urn.jts.InheritanceTest Input Channels
chan Message2 = [QUEUE_SIZE] of {pid, urn_jts_InheritanceTest_Message2}; 
chan Message1 = [QUEUE_SIZE] of {pid, urn_jts_InheritanceTest_Message1}; 


// urn.jts.InheritanceTest Output Channels
