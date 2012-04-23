/** 
 *  This Promela file was auto-generated using JTS on Mon Jun 13 16:10:57 EDT 2011.
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
//  This message is used to set up an event. Field 1 is a local request ID that the event provider returns
//  in the Confirm or Reject message. Field 2 is the Event Type, which allows the requester to specify
//  the type of event – Periodic specifies that the event is a service connection request and should not
//  be queued, in which case field 3 (Requested periodic rate) must be set to a non-zero value. Event type
//  of Every Change specifies that the corresponding Report message should be sent every time the data
//  associated with that message changes. Field 4 contains the size of the Query message that is to specify
//  the contents of the Report. Field 5 contains the Query message (including its two byte header).
chan CreateEvent = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_Events_CreateEvent}; 
//  This message is used to set up a command event. Field 1 is a local request ID that the event provider
//  returns in the Confirm or Reject message. Field 2 is the maximum allowed execution time; any command
//  not completed within its specified duration is considered a failure. Field 3 contains the size of
//  the Command message that is to specify the command to be executed. Field 4 contains the encoded command
//  message (including its two byte header).
chan CreateCommandEvent = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_Events_CreateCommandEvent}; 
//  The Update Event message allows the requester to request a rate or change. The format is the same as
//  the Create Event, only with the addition of Event ID field to specify the event that needs updating.
chan UpdateEvent = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_Events_UpdateEvent}; 
//  The Cancel Event message is used by the requester to cancel and/or request deletion of the specified
//  event.
chan CancelEvent = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_Events_CancelEvent}; 
//  The Query Events message is used to request detail on events. Queries can be made by message ID, event
//  type or Event ID.
chan QueryEvents = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_Events_QueryEvents}; 
//  This message is used by clients to query the timeout period of this service.
chan QueryEventTimeout = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_Events_QueryEventTimeout}; 


// urn:jaus:jss:core:Events Output Channels
//  The Confirm Event message is used to confirm an Event has been created/updated/or cancelled. Field
//  1 represents the Request ID from the Create, Update, or Cancel message that initiated this message.
//  The Request ID’s scope is local to the requesting client only. Field 2, Event ID, is a globally unique
//  ID that is established for the event. Field 3 is used to specify the closest rate that the service can
//  provide if it cannot match the requested rate.
chan ConfirmEventRequest[CLIENT_CHANNELS] = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_Events_ConfirmEventRequest}; 
//  The Reject Event Request message is used to reject an Event creation, update or cancellation. Field
//  2 represents the Request ID from the Create, Update, or Cancel message that initiated this message.
//  The Request ID’s scope is local to the requesting client only. Field 4, Event ID, is a globally unique
//  ID that is established for the event.
chan RejectEventRequest[CLIENT_CHANNELS] = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_Events_RejectEventRequest}; 
//  This message is used to report the active event requests that match the requirements provided in
//  the QueryEvents message.
chan ReportEvents[CLIENT_CHANNELS] = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_Events_ReportEvents}; 
//  The Event message is sent when event is triggered. It includes the Event ID and a sequence number to
//  allow the client to keep track of event processing.
chan Event[CLIENT_CHANNELS] = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_Events_Event}; 
//  The CommandEvent message is sent when a command specified by a previous Create Command Event message
//  has completed or expired. It includes the Event ID and command result.
chan CommandEvent[CLIENT_CHANNELS] = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_Events_CommandEvent}; 
//  This message is used to report the timeout period of this service.
chan ReportEventTimeout[CLIENT_CHANNELS] = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_Events_ReportEventTimeout}; 

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
//  This message is used to request interruptible control of the receiving component. Once control
//  is established, the receiving component shall only execute commands from the sending component.
//  The authority code parameter is to be set equal to that of the sending component. The receiving component
//  must always accept the control of the highest authority component that is requesting uninterruptible
//  control. Commands from all other components are ignored unless from a component with higher authority.
chan RequestControl = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_AccessControl_RequestControl}; 
//  This message is used to relinquish uninterruptible control of the receiving component.
chan ReleaseControl = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_AccessControl_ReleaseControl}; 
//  This message is used by clients to query the current control state of this service.
chan QueryControl = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_AccessControl_QueryControl}; 
//  This message is used by clients to query the current value of the authority code of this service.
chan QueryAuthority = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_AccessControl_QueryAuthority}; 
//  This message shall set the command authority of the receiving component. The authority bits range
//  in value from 0 to 255 with 255 being the highest.
chan SetAuthority = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_AccessControl_SetAuthority}; 
//  This message is used by clients of this service to query the timeout period of this service.
chan QueryTimeout = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_AccessControl_QueryTimeout}; 


// urn:jaus:jss:core:AccessControl Output Channels
//  This message is used to report the current state of control of this service. If the service is in the
//  Controlled state, this message reports the ID of the controlling component. The ID fields shall
//  be set to zero (0) if this service is in the NotControlled state..
chan ReportControl[CLIENT_CHANNELS] = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_AccessControl_ReportControl}; 
//  The Reject Control message is used to notify a component that control has been released (response
//  code = 0), or a request to release control could not be processed (response code = 1).
chan RejectControl[CLIENT_CHANNELS] = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_AccessControl_RejectControl}; 
//  The Confirm Control message is used to notify a component that it accepts control from that component
//  (or not). When control has been granted, response code of 0, the component under control will only
//  execute messages from the controlling component until control is released or interrupted. When
//  the requesting component has lower authority than the current controlling entity, the response
//  will be 2. For components not supporting interruptible control, or if the component is engaged in
//  other operations that would prevent this service from performing its actions to grant control,
//  the response code value of 1 can be used.
chan ConfirmControl[CLIENT_CHANNELS] = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_AccessControl_ConfirmControl}; 
//  This message is used to report the current command authority.
chan ReportAuthority[CLIENT_CHANNELS] = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_AccessControl_ReportAuthority}; 
//  This message is used to report the timeout period of this message.
chan ReportTimeout[CLIENT_CHANNELS] = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_AccessControl_ReportTimeout}; 

//  Occurs when access is not re-acquired periodically
chan Timedout = [QUEUE_SIZE] of {pid};


// urn:jaus:jss:core:Management Input Channels
//  This message is used to transition the receiving component to the ready state.
chan Resume = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_Management_Resume}; 
//  This message is used to cause the receiving component to reinitialize.
chan Reset = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_Management_Reset}; 
//  This message is used to alert the component to a safety critical situation. The component that sends
//  the emergency command shall set the message priority to the safety critical priority range as defined
//  by the Transport. Receive of the emergency command shall result in the component transitioning
//  into the emergency state.
chan SetEmergency = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_Management_SetEmergency}; 
//  This message shall notify the receiving component that the current emergency condition is to be
//  reset and that the component shall transition back to the Ready or Standby state, provided that all
//  emergency conditions have been cleared. JAUS currently defines only one emergency condition,
//  the "Stop" condition. Future versions of this document could define other emergency conditions.
chan ClearEmergency = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_Management_ClearEmergency}; 
//  This message is used by clients of this service to query the state of this service.
chan QueryStatus = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_Management_QueryStatus}; 
//  This message is used to cause the receiving component to free all of the resources allocated to its
//  process by the system and then to shutdown.
chan Shutdown = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_Management_Shutdown}; 
//  This message is used to transition the receiving component to the standby state.
chan Standby = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_Management_Standby}; 


// urn:jaus:jss:core:Management Output Channels
//  This message is used to report the current status of the sender of the message.
chan ReportStatus[CLIENT_CHANNELS] = [QUEUE_SIZE] of {pid, urn_jaus_jss_core_Management_ReportStatus}; 

//  An internally generated event that marks the completion of the initialization cycle.
chan Initialized = [QUEUE_SIZE] of {pid};

//  An internally generated event that marks forces the component to a failure state.
chan Failure = [QUEUE_SIZE] of {pid};
