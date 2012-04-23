/** 
 *  This Promela file was auto-generated using JTS on Wed May 18 12:47:38 EDT 2011.
 *  ID=urn:jaus:jss:core:Management  vsersion=1.1
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


// urn:jaus:jss:core:Events Input Channels

chan CreateEvent = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent};

chan CreateCommandEvent = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_MessageSet_CommandClass_CreateCommandEvent};

chan UpdateEvent = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent};

chan CancelEvent = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent};

chan QueryEvents = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryEvents};

chan QueryEventTimeout = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryEventTimeout};


// urn:jaus:jss:core:Events Output Channels

chan ConfirmEventRequest[CLIENT_CHANNELS] = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_MessageSet_CommandClass_ConfirmEventRequest};

chan RejectEventRequest[CLIENT_CHANNELS] = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_MessageSet_CommandClass_RejectEventRequest};

chan ReportEvents[CLIENT_CHANNELS] = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_MessageSet_InformClass_ReportEvents};

chan Event[CLIENT_CHANNELS] = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_MessageSet_InformClass_Event};

chan CommandEvent[CLIENT_CHANNELS] = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_MessageSet_InformClass_CommandEvent};

chan ReportEventTimeout[CLIENT_CHANNELS] = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_MessageSet_InformClass_ReportEventTimeout};

//  Received when an event occurs.
chan EventOccurred = [QUEUE_SIZE] of {pid};

//  Received when an event error occurs.
chan EventError = [QUEUE_SIZE] of {pid};

//  Received when a timeout occurs.
chan Timeout = [QUEUE_SIZE] of {pid};

//  Must occur when a command event is completed.
chan CommandCompleted = [QUEUE_SIZE] of {pid};

//  Must occur when a command event does not complete within its specified period of time.
chan CommandExpired = [QUEUE_SIZE] of {pid};


// urn:jaus:jss:core:AccessControl Input Channels

chan RequestControl = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_MessageSet_CommandClass_RequestControl};

chan ReleaseControl = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_MessageSet_CommandClass_ReleaseControl};

chan QueryControl = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryControl};

chan QueryAuthority = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryAuthority};

chan SetAuthority = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_MessageSet_CommandClass_SetAuthority};

chan QueryTimeout = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryTimeout};


// urn:jaus:jss:core:AccessControl Output Channels

chan ReportControl[CLIENT_CHANNELS] = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_MessageSet_InformClass_ReportControl};

chan RejectControl[CLIENT_CHANNELS] = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_MessageSet_CommandClass_RejectControl};

chan ConfirmControl[CLIENT_CHANNELS] = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_MessageSet_CommandClass_ConfirmControl};

chan ReportAuthority[CLIENT_CHANNELS] = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_MessageSet_InformClass_ReportAuthority};

chan ReportTimeout[CLIENT_CHANNELS] = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_MessageSet_InformClass_ReportTimeout};

//  Occurs when access is not re-acquired periodically
chan Timedout = [QUEUE_SIZE] of {pid};


// urn:jaus:jss:core:Management Input Channels

chan Shutdown = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_MessageSet_CommandClass_Shutdown};

chan Standby = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_MessageSet_CommandClass_Standby};

chan Resume = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_MessageSet_CommandClass_Resume};

chan Reset = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_MessageSet_CommandClass_Reset};

chan SetEmergency = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_MessageSet_CommandClass_SetEmergency};

chan ClearEmergency = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_MessageSet_CommandClass_ClearEmergency};

chan QueryStatus = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_MessageSet_QueryClass_QueryStatus};


// urn:jaus:jss:core:Management Output Channels

chan ReportStatus[CLIENT_CHANNELS] = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_MessageSet_InformClass_ReportStatus};

//  An internally generated event that marks the completion of the initialization cycle.
chan Initialized = [QUEUE_SIZE] of {pid};

//  An internally generated event that marks forces the component to a failure state.
chan Failure = [QUEUE_SIZE] of {pid};
