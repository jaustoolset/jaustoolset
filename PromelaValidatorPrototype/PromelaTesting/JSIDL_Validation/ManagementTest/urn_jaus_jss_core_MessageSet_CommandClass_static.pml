/** 
 *  This Promela file was auto-generated using JTS on Wed May 18 12:47:44 EDT 2011.
 *  ID=urn:jaus:jss:core:MessageSet:CommandClass  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

typedef urn_jaus_jss_core_MessageSet_CommandClass_SetAuthority_authorityRec{
	urn_jaus_jss_core_MessageSet_BasicTypes_AuthorityCode AuthorityCode;
	
};


/**
*  This message shall set the command authority of the receiving component. The authority bits range
*  in value from 0 to 255 with 255 being the highest.
*/
typedef urn_jaus_jss_core_MessageSet_CommandClass_SetAuthority{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	urn_jaus_jss_core_MessageSet_CommandClass_SetAuthority_authorityRec authorityRec;
};
/**
*  This message is used to cause the receiving component to free all of the resources allocated to its
*  process by the system and then to shutdown.
*/
typedef urn_jaus_jss_core_MessageSet_CommandClass_Shutdown{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
};
/**
*  This message is used to transition the receiving component to the standby state.
*/
typedef urn_jaus_jss_core_MessageSet_CommandClass_Standby{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
};
/**
*  This message is used to transition the receiving component to the ready state.
*/
typedef urn_jaus_jss_core_MessageSet_CommandClass_Resume{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
};
/**
*  This message is used to cause the receiving component to reinitialize.
*/
typedef urn_jaus_jss_core_MessageSet_CommandClass_Reset{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
};
typedef urn_jaus_jss_core_MessageSet_CommandClass_SetEmergency_SetEmergencyRec{
	urn_jaus_jss_core_MessageSet_BasicTypes_EmergencyCode EmergencyCode;
	
};


/**
*  This message is used to alert the component to a safety critical situation. The component that sends
*  the emergency command shall set the message priority to the safety critical priority range as defined
*  by the Transport. Receive of the emergency command shall result in the component transitioning
*  into the emergency state.
*/
typedef urn_jaus_jss_core_MessageSet_CommandClass_SetEmergency{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	urn_jaus_jss_core_MessageSet_CommandClass_SetEmergency_SetEmergencyRec SetEmergencyRec;
};
typedef urn_jaus_jss_core_MessageSet_CommandClass_ClearEmergency_ClearEmergencyRec{
	urn_jaus_jss_core_MessageSet_BasicTypes_EmergencyCode EmergencyCode;
	
};


/**
*  This message shall notify the receiving component that the current emergency condition is to be
*  reset and that the component shall transition back to the Ready or Standby state, provided that all
*  emergency conditions have been cleared. JAUS currently defines only one emergency condition,
*  the "Stop" condition. Future versions of this document could define other emergency conditions.
*/
typedef urn_jaus_jss_core_MessageSet_CommandClass_ClearEmergency{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	urn_jaus_jss_core_MessageSet_CommandClass_ClearEmergency_ClearEmergencyRec ClearEmergencyRec;
};
typedef urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_RequestControlRec{
	urn_jaus_jss_core_MessageSet_BasicTypes_AuthorityCode AuthorityCode;
	
};


/**
*  This message is used to request interruptible control of the receiving component. Once control
*  is established, the receiving component shall only execute commands from the sending component.
*  The authority code parameter is to be set equal to that of the sending component. The receiving component
*  must always accept the control of the highest authority component that is requesting uninterruptible
*  control. Commands from all other components are ignored unless from a component with higher authority.
*/
typedef urn_jaus_jss_core_MessageSet_CommandClass_RequestControl{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	urn_jaus_jss_core_MessageSet_CommandClass_RequestControl_RequestControlRec RequestControlRec;
};
/**
*  This message is used to relinquish uninterruptible control of the receiving component.
*/
typedef urn_jaus_jss_core_MessageSet_CommandClass_ReleaseControl{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
};
#define urn_jaus_jss_core_MessageSet_CommandClass_ConfirmControl_ResponseCode mtype
urn_jaus_jss_core_MessageSet_CommandClass_ConfirmControl_ResponseCode {CONTROL_ACCEPTED, NOT_AVAILABLE, INSUFFICIENT_AUTHORITY};
typedef urn_jaus_jss_core_MessageSet_CommandClass_ConfirmControl_ConfirmControlRec{
	urn_jaus_jss_core_MessageSet_CommandClass_ConfirmControl_ResponseCode ResponseCode;
};


/**
*  The Confirm Control message is used to notify a component that it accepts control from that component
*  (or not). When control has been granted, response code of 0, the component under control will only
*  execute messages from the controlling component until control is released or interrupted. When
*  the requesting component has lower authority than the current controlling entity, the response
*  will be 2. For components not supporting interruptible control, or if the component is engaged in
*  other operations that would prevent this service from performing its actions to grant control,
*  the response code value of 1 can be used.
*/
typedef urn_jaus_jss_core_MessageSet_CommandClass_ConfirmControl{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	urn_jaus_jss_core_MessageSet_CommandClass_ConfirmControl_ConfirmControlRec ConfirmControlRec;
};
#define urn_jaus_jss_core_MessageSet_CommandClass_RejectControl_ResponseCode mtype
urn_jaus_jss_core_MessageSet_CommandClass_RejectControl_ResponseCode {CONTROL_RELEASED};
typedef urn_jaus_jss_core_MessageSet_CommandClass_RejectControl_RejectControlRec{
	urn_jaus_jss_core_MessageSet_CommandClass_RejectControl_ResponseCode ResponseCode;
};


/**
*  The Reject Control message is used to notify a component that control has been released (response
*  code = 0), or a request to release control could not be processed (response code = 1).
*/
typedef urn_jaus_jss_core_MessageSet_CommandClass_RejectControl{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	urn_jaus_jss_core_MessageSet_CommandClass_RejectControl_RejectControlRec RejectControlRec;
};
/**
*  Time is configured within a JAUS system using the following message. Accuracy of the time may be dependent
*  on latencies in the transmission of the message. Proper systems engineering procedures should
*  be used to insure the accuracy of the time messages are within the system tolerance. All times are
*  in Coordinated Universal Time (UTC).
*/
typedef urn_jaus_jss_core_MessageSet_CommandClass_SetTime{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	urn_jaus_jss_core_MessageSet_BasicTypes_TimeRec TimeRec;
};
//  Local request ID for use in confirm event
#define urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_RequestID int
#define urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_EventType mtype
urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_EventType {PeriodicSC, Everychange};
//  Must be specified for periodic event, and set to 0 for every change
#define urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_RequestedPeriodicRate int
typedef urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_CreateEventRec{
	//  Local request ID for use in confirm event
	int RequestID;
	//  Type of event
	urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_EventType EventType;
	//  Must be specified for periodic event, and set to 0 for every change
	int RequestedPeriodicRate;
};


/**
*  This message is used to set up an event. Field 1 is a local request ID that the event provider returns
*  in the Confirm or Reject message. Field 2 is the Event Type, which allows the requester to specify
*  the type of event – Periodic specifies that the event is a service connection request and should not
*  be queued, in which case field 3 (Requested periodic rate) must be set to a non-zero value. Event type
*  of Every Change specifies that the corresponding Report message should be sent every time the data
*  associated with that message changes. Field 4 contains the size of the Query message that is to specify
*  the contents of the Report. Field 5 contains the Query message (including its two byte header).
*/
typedef urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	urn_jaus_jss_core_MessageSet_CommandClass_CreateEvent_CreateEventRec CreateEventRec;
};
//  Local request ID for use in confirm event
#define urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_RequestID int
#define urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_EventType mtype
//  Must be specified for periodic event, and set to 0 for every change
#define urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_RequestedPeriodicRate int
//  Unique identifier of existing event to update
#define urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_EventID int
typedef urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_UpdateEventRec{
	//  Local request ID for use in confirm event
	int RequestID;
	//  Type of event
	urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_EventType EventType;
	//  Must be specified for periodic event, and set to 0 for every change
	int RequestedPeriodicRate;
	//  Unique identifier of existing event to update
	int EventID;
};


/**
*  The Update Event message allows the requester to request a rate or change. The format is the same as
*  the Create Event, only with the addition of Event ID field to specify the event that needs updating.
*/
typedef urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	urn_jaus_jss_core_MessageSet_CommandClass_UpdateEvent_UpdateEventRec UpdateEventRec;
};
//  Local request id for use in confirm/reject message
#define urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_RequestID int
//  unique id of the event to be removed
#define urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_EventID int
typedef urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_CancelEventRec{
	//  Local request id for use in confirm/reject message
	int RequestID;
	//  unique id of the event to be removed
	int EventID;
};


/**
*  The Cancel Event message is used by the requester to cancel and/or request deletion of the specified
*  event.
*/
typedef urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	urn_jaus_jss_core_MessageSet_CommandClass_CancelEvent_CancelEventRec CancelEventRec;
};
//  ID of the event maintenance request (Create, Update, or Cancel)
#define urn_jaus_jss_core_MessageSet_CommandClass_ConfirmEventRequest_RequestID int
//  The identifier of the specific event
#define urn_jaus_jss_core_MessageSet_CommandClass_ConfirmEventRequest_EventID int
//  Requested rate or closest to requested rate
#define urn_jaus_jss_core_MessageSet_CommandClass_ConfirmEventRequest_ConfirmedPeriodicRate int
typedef urn_jaus_jss_core_MessageSet_CommandClass_ConfirmEventRequest_ConfirmEventRequestRec{
	//  ID of the event maintenance request (Create, Update, or Cancel)
	int RequestID;
	//  The identifier of the specific event
	int EventID;
	//  Requested rate or closest to requested rate
	int ConfirmedPeriodicRate;
};


/**
*  The Confirm Event message is used to confirm an Event has been created/updated/or cancelled. Field
*  1 represents the Request ID from the Create, Update, or Cancel message that initiated this message.
*  The Request ID’s scope is local to the requesting client only. Field 2, Event ID, is a globally unique
*  ID that is established for the event. Field 3 is used to specify the closest rate that the service can
*  provide if it cannot match the requested rate.
*/
typedef urn_jaus_jss_core_MessageSet_CommandClass_ConfirmEventRequest{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	urn_jaus_jss_core_MessageSet_CommandClass_ConfirmEventRequest_ConfirmEventRequestRec ConfirmEventRequestRec;
};
//  ID of the event maintenance request (create, update, or cancel)
#define urn_jaus_jss_core_MessageSet_CommandClass_RejectEventRequest_RequestID int
#define urn_jaus_jss_core_MessageSet_CommandClass_RejectEventRequest_ResponseCode mtype
urn_jaus_jss_core_MessageSet_CommandClass_RejectEventRequest_ResponseCode {periodiceventsnotsupported, changebasedeventsnotsupported, connectionrefused, invalideventsetup, messagenotsupported, errorinvalideventIDforupdateeventrequest};
typedef urn_jaus_jss_core_MessageSet_CommandClass_RejectEventRequest_RejectEventRequestRec{
	//  ID of the event maintenance request (create, update, or cancel)
	int RequestID;
	urn_jaus_jss_core_MessageSet_CommandClass_RejectEventRequest_ResponseCode ResponseCode;
};


/**
*  The Reject Event Request message is used to reject an Event creation, update or cancellation. Field
*  2 represents the Request ID from the Create, Update, or Cancel message that initiated this message.
*  The Request ID’s scope is local to the requesting client only. Field 4, Event ID, is a globally unique
*  ID that is established for the event.
*/
typedef urn_jaus_jss_core_MessageSet_CommandClass_RejectEventRequest{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	urn_jaus_jss_core_MessageSet_CommandClass_RejectEventRequest_RejectEventRequestRec RejectEventRequestRec;
};
//  Local request ID for use in confirm event
#define urn_jaus_jss_core_MessageSet_CommandClass_CreateCommandEvent_RequestID int
//  Any commands not executed within the maximum allowed time are considered a failure.
#define urn_jaus_jss_core_MessageSet_CommandClass_CreateCommandEvent_MaximumAllowedDuration int
typedef urn_jaus_jss_core_MessageSet_CommandClass_CreateCommandEvent_CreateEventRec{
	//  Local request ID for use in confirm event
	int RequestID;
	//  Any commands not executed within the maximum allowed time are considered a failure.
	int MaximumAllowedDuration;
};


/**
*  This message is used to set up a command event. Field 1 is a local request ID that the event provider
*  returns in the Confirm or Reject message. Field 2 is the maximum allowed execution time; any command
*  not completed within its specified duration is considered a failure. Field 3 contains the size of
*  the Command message that is to specify the command to be executed. Field 4 contains the encoded command
*  message (including its two byte header).
*/
typedef urn_jaus_jss_core_MessageSet_CommandClass_CreateCommandEvent{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	urn_jaus_jss_core_MessageSet_CommandClass_CreateCommandEvent_CreateEventRec CreateEventRec;
};
/**
*  This message is used to add, insert or replace one or more elements in a list. Each element is uniquely
*  identified by the UID, and the sequence within the list is specified by the previous (parent) and
*  next (child) elements. This message can also be used to update an existing element.
*/
typedef urn_jaus_jss_core_MessageSet_CommandClass_SetElement{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
};
/**
*  This message is used to delete one or more elements from a list. Each element is uniquely identified
*  by the UID.
*/
typedef urn_jaus_jss_core_MessageSet_CommandClass_DeleteElement{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
};
//  ID of the request that generated this confirmation message.
#define urn_jaus_jss_core_MessageSet_CommandClass_ConfirmElementRequest_RequestID int
typedef urn_jaus_jss_core_MessageSet_CommandClass_ConfirmElementRequest_RequestIDRec{
	//  ID of the request that generated this confirmation message.
	int RequestID;
};


/**
*  This message is used to confirm successful operation on an element list.
*/
typedef urn_jaus_jss_core_MessageSet_CommandClass_ConfirmElementRequest{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	urn_jaus_jss_core_MessageSet_CommandClass_ConfirmElementRequest_RequestIDRec RequestIDRec;
};
//  ID of the request that generated this confirmation message.
#define urn_jaus_jss_core_MessageSet_CommandClass_RejectElementRequest_RequestID int
#define urn_jaus_jss_core_MessageSet_CommandClass_RejectElementRequest_ResponseCode mtype
urn_jaus_jss_core_MessageSet_CommandClass_RejectElementRequest_ResponseCode {InvalidelementID, Invalidpreviouselement, Invalidnextelement, Unsupportedelementtype, ElementIDnotfound, Outofmemory, Unspecifiederror};
typedef urn_jaus_jss_core_MessageSet_CommandClass_RejectElementRequest_RejectElementRec{
	//  ID of the request that generated this confirmation message.
	int RequestID;
	//  ID of the request that generated this confirmation message.
	urn_jaus_jss_core_MessageSet_CommandClass_RejectElementRequest_ResponseCode ResponseCode;
};


/**
*  This message is used to reject an operation on an element list.
*/
typedef urn_jaus_jss_core_MessageSet_CommandClass_RejectElementRequest{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	urn_jaus_jss_core_MessageSet_CommandClass_RejectElementRequest_RejectElementRec RejectElementRec;
};
//  Major version number of the service
#define urn_jaus_jss_core_MessageSet_CommandClass_RegisterServices_ServiceRec_MajorVersionNumber int
//  Minor version number of the service
#define urn_jaus_jss_core_MessageSet_CommandClass_RegisterServices_ServiceRec_MinorVersionNumber int
typedef urn_jaus_jss_core_MessageSet_CommandClass_RegisterServices_ServiceRec{
	//  Major version number of the service
	int MajorVersionNumber;
	//  Minor version number of the service
	int MinorVersionNumber;
};


typedef urn_jaus_jss_core_MessageSet_CommandClass_RegisterServices_ServiceList{
	urn_jaus_jss_core_MessageSet_CommandClass_RegisterServices_ServiceRec ServiceRec;
};


/**
*  This message allows a component to register its capabilities with a Discovery service. If a component
*  ID is specified in the JAUS Reference Architecture version 3.2+, it may report only one service beyond
*  the core message support, and this service must be equal to the component ID. If a component ID is not
*  listed in the RA, it may report any number of services. For example, a component with ID 33 must provide
*  only service 33. The exception to this rule is component ID 1 (the Node Manager) which may provide
*  any number of services in addition to core message support. Note that this restriction may be removed
*  in future versions of this Standard.
*/
typedef urn_jaus_jss_core_MessageSet_CommandClass_RegisterServices{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	// skipped processing List, since its not well supported in Promela.
};
