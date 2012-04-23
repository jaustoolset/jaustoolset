/** 
 *  This Promela file was auto-generated using JTS on Mon Jun 13 16:10:57 EDT 2011.
 *  ID=urn:jaus:jss:core:Events  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

#define urn_jaus_jss_core_Events_CreateEvent_MessageID int
typedef urn_jaus_jss_core_Events_CreateEvent_HeaderRec{
	int MessageID;
};


typedef urn_jaus_jss_core_Events_CreateEvent_MsgHeader{
	urn_jaus_jss_core_Events_CreateEvent_HeaderRec HeaderRec;
};

#define urn_jaus_jss_core_Events_CreateEvent_RequestID int
#define urn_jaus_jss_core_Events_CreateEvent_EventType mtype
urn_jaus_jss_core_Events_CreateEvent_EventType {PeriodicSC, Everychange};
#define urn_jaus_jss_core_Events_CreateEvent_RequestedPeriodicRate int
typedef urn_jaus_jss_core_Events_CreateEvent_CreateEventRec{
	int RequestID;
	urn_jaus_jss_core_Events_CreateEvent_EventType EventType;
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
typedef urn_jaus_jss_core_Events_CreateEvent{
	urn_jaus_jss_core_Events_CreateEvent_HeaderRec HeaderRec;
	urn_jaus_jss_core_Events_CreateEvent_CreateEventRec CreateEventRec;
};
#define urn_jaus_jss_core_Events_CreateCommandEvent_MessageID int
typedef urn_jaus_jss_core_Events_CreateCommandEvent_HeaderRec{
	int MessageID;
};


typedef urn_jaus_jss_core_Events_CreateCommandEvent_MsgHeader{
	urn_jaus_jss_core_Events_CreateCommandEvent_HeaderRec HeaderRec;
};

#define urn_jaus_jss_core_Events_CreateCommandEvent_RequestID int
#define urn_jaus_jss_core_Events_CreateCommandEvent_MaximumAllowedDuration int
typedef urn_jaus_jss_core_Events_CreateCommandEvent_CreateEventRec{
	int RequestID;
	int MaximumAllowedDuration;
};


/**
*  This message is used to set up a command event. Field 1 is a local request ID that the event provider
*  returns in the Confirm or Reject message. Field 2 is the maximum allowed execution time; any command
*  not completed within its specified duration is considered a failure. Field 3 contains the size of
*  the Command message that is to specify the command to be executed. Field 4 contains the encoded command
*  message (including its two byte header).
*/
typedef urn_jaus_jss_core_Events_CreateCommandEvent{
	urn_jaus_jss_core_Events_CreateCommandEvent_HeaderRec HeaderRec;
	urn_jaus_jss_core_Events_CreateCommandEvent_CreateEventRec CreateEventRec;
};
#define urn_jaus_jss_core_Events_UpdateEvent_MessageID int
typedef urn_jaus_jss_core_Events_UpdateEvent_HeaderRec{
	int MessageID;
};


typedef urn_jaus_jss_core_Events_UpdateEvent_MsgHeader{
	urn_jaus_jss_core_Events_UpdateEvent_HeaderRec HeaderRec;
};

#define urn_jaus_jss_core_Events_UpdateEvent_RequestID int
#define urn_jaus_jss_core_Events_UpdateEvent_EventType mtype
#define urn_jaus_jss_core_Events_UpdateEvent_RequestedPeriodicRate int
#define urn_jaus_jss_core_Events_UpdateEvent_EventID int
typedef urn_jaus_jss_core_Events_UpdateEvent_UpdateEventRec{
	int RequestID;
	urn_jaus_jss_core_Events_UpdateEvent_EventType EventType;
	int RequestedPeriodicRate;
	int EventID;
};


/**
*  The Update Event message allows the requester to request a rate or change. The format is the same as
*  the Create Event, only with the addition of Event ID field to specify the event that needs updating.
*/
typedef urn_jaus_jss_core_Events_UpdateEvent{
	urn_jaus_jss_core_Events_UpdateEvent_HeaderRec HeaderRec;
	urn_jaus_jss_core_Events_UpdateEvent_UpdateEventRec UpdateEventRec;
};
#define urn_jaus_jss_core_Events_CancelEvent_MessageID int
typedef urn_jaus_jss_core_Events_CancelEvent_HeaderRec{
	int MessageID;
};


typedef urn_jaus_jss_core_Events_CancelEvent_MsgHeader{
	urn_jaus_jss_core_Events_CancelEvent_HeaderRec HeaderRec;
};

#define urn_jaus_jss_core_Events_CancelEvent_RequestID int
#define urn_jaus_jss_core_Events_CancelEvent_EventID int
typedef urn_jaus_jss_core_Events_CancelEvent_CancelEventRec{
	int RequestID;
	int EventID;
};


/**
*  The Cancel Event message is used by the requester to cancel and/or request deletion of the specified
*  event.
*/
typedef urn_jaus_jss_core_Events_CancelEvent{
	urn_jaus_jss_core_Events_CancelEvent_HeaderRec HeaderRec;
	urn_jaus_jss_core_Events_CancelEvent_CancelEventRec CancelEventRec;
};
#define urn_jaus_jss_core_Events_QueryEvents_MessageID int
typedef urn_jaus_jss_core_Events_QueryEvents_HeaderRec{
	int MessageID;
};


typedef urn_jaus_jss_core_Events_QueryEvents_MsgHeader{
	urn_jaus_jss_core_Events_QueryEvents_HeaderRec HeaderRec;
};

/**
*  The Query Events message is used to request detail on events. Queries can be made by message ID, event
*  type or Event ID.
*/
typedef urn_jaus_jss_core_Events_QueryEvents{
	urn_jaus_jss_core_Events_QueryEvents_HeaderRec HeaderRec;
};
#define urn_jaus_jss_core_Events_QueryEventTimeout_MessageID int
typedef urn_jaus_jss_core_Events_QueryEventTimeout_HeaderRec{
	int MessageID;
};


typedef urn_jaus_jss_core_Events_QueryEventTimeout_MsgHeader{
	urn_jaus_jss_core_Events_QueryEventTimeout_HeaderRec HeaderRec;
};

/**
*  This message is used by clients to query the timeout period of this service.
*/
typedef urn_jaus_jss_core_Events_QueryEventTimeout{
	urn_jaus_jss_core_Events_QueryEventTimeout_HeaderRec HeaderRec;
};
#define urn_jaus_jss_core_Events_ConfirmEventRequest_MessageID int
typedef urn_jaus_jss_core_Events_ConfirmEventRequest_HeaderRec{
	int MessageID;
};


typedef urn_jaus_jss_core_Events_ConfirmEventRequest_MsgHeader{
	urn_jaus_jss_core_Events_ConfirmEventRequest_HeaderRec HeaderRec;
};

#define urn_jaus_jss_core_Events_ConfirmEventRequest_RequestID int
#define urn_jaus_jss_core_Events_ConfirmEventRequest_EventID int
#define urn_jaus_jss_core_Events_ConfirmEventRequest_ConfirmedPeriodicRate int
typedef urn_jaus_jss_core_Events_ConfirmEventRequest_ConfirmEventRequestRec{
	int RequestID;
	int EventID;
	int ConfirmedPeriodicRate;
};


/**
*  The Confirm Event message is used to confirm an Event has been created/updated/or cancelled. Field
*  1 represents the Request ID from the Create, Update, or Cancel message that initiated this message.
*  The Request ID’s scope is local to the requesting client only. Field 2, Event ID, is a globally unique
*  ID that is established for the event. Field 3 is used to specify the closest rate that the service can
*  provide if it cannot match the requested rate.
*/
typedef urn_jaus_jss_core_Events_ConfirmEventRequest{
	urn_jaus_jss_core_Events_ConfirmEventRequest_HeaderRec HeaderRec;
	urn_jaus_jss_core_Events_ConfirmEventRequest_ConfirmEventRequestRec ConfirmEventRequestRec;
};
#define urn_jaus_jss_core_Events_RejectEventRequest_MessageID int
typedef urn_jaus_jss_core_Events_RejectEventRequest_HeaderRec{
	int MessageID;
};


typedef urn_jaus_jss_core_Events_RejectEventRequest_MsgHeader{
	urn_jaus_jss_core_Events_RejectEventRequest_HeaderRec HeaderRec;
};

#define urn_jaus_jss_core_Events_RejectEventRequest_RequestID int
#define urn_jaus_jss_core_Events_RejectEventRequest_ResponseCode mtype
urn_jaus_jss_core_Events_RejectEventRequest_ResponseCode {periodiceventsnotsupported, changebasedeventsnotsupported, connectionrefused, invalideventsetup, messagenotsupported, errorinvalideventIDforupdateeventrequest};
typedef urn_jaus_jss_core_Events_RejectEventRequest_RejectEventRequestRec{
	int RequestID;
	urn_jaus_jss_core_Events_RejectEventRequest_ResponseCode ResponseCode;
};


/**
*  The Reject Event Request message is used to reject an Event creation, update or cancellation. Field
*  2 represents the Request ID from the Create, Update, or Cancel message that initiated this message.
*  The Request ID’s scope is local to the requesting client only. Field 4, Event ID, is a globally unique
*  ID that is established for the event.
*/
typedef urn_jaus_jss_core_Events_RejectEventRequest{
	urn_jaus_jss_core_Events_RejectEventRequest_HeaderRec HeaderRec;
	urn_jaus_jss_core_Events_RejectEventRequest_RejectEventRequestRec RejectEventRequestRec;
};
#define urn_jaus_jss_core_Events_ReportEvents_MessageID int
typedef urn_jaus_jss_core_Events_ReportEvents_HeaderRec{
	int MessageID;
};


typedef urn_jaus_jss_core_Events_ReportEvents_MsgHeader{
	urn_jaus_jss_core_Events_ReportEvents_HeaderRec HeaderRec;
};

#define urn_jaus_jss_core_Events_ReportEvents_ReportEventRec_EventType mtype
#define urn_jaus_jss_core_Events_ReportEvents_ReportEventRec_EventID int
typedef urn_jaus_jss_core_Events_ReportEvents_ReportEventRec{
	urn_jaus_jss_core_Events_ReportEvents_ReportEventRec_EventType EventType;
	int EventID;
};


typedef urn_jaus_jss_core_Events_ReportEvents_EventList{
	urn_jaus_jss_core_Events_ReportEvents_ReportEventRec ReportEventRec;
};


/**
*  This message is used to report the active event requests that match the requirements provided in
*  the QueryEvents message.
*/
typedef urn_jaus_jss_core_Events_ReportEvents{
	urn_jaus_jss_core_Events_ReportEvents_HeaderRec HeaderRec;
	// skipped processing List, since its not well supported in Promela.
};
#define urn_jaus_jss_core_Events_Event_MessageID int
typedef urn_jaus_jss_core_Events_Event_HeaderRec{
	int MessageID;
};


typedef urn_jaus_jss_core_Events_Event_MsgHeader{
	urn_jaus_jss_core_Events_Event_HeaderRec HeaderRec;
};

#define urn_jaus_jss_core_Events_Event_EventID int
#define urn_jaus_jss_core_Events_Event_SequenceNumber int
typedef urn_jaus_jss_core_Events_Event_EventRec{
	int EventID;
	int SequenceNumber;
};


/**
*  The Event message is sent when event is triggered. It includes the Event ID and a sequence number to
*  allow the client to keep track of event processing.
*/
typedef urn_jaus_jss_core_Events_Event{
	urn_jaus_jss_core_Events_Event_HeaderRec HeaderRec;
	urn_jaus_jss_core_Events_Event_EventRec EventRec;
};
#define urn_jaus_jss_core_Events_CommandEvent_MessageID int
typedef urn_jaus_jss_core_Events_CommandEvent_HeaderRec{
	int MessageID;
};


typedef urn_jaus_jss_core_Events_CommandEvent_MsgHeader{
	urn_jaus_jss_core_Events_CommandEvent_HeaderRec HeaderRec;
};

#define urn_jaus_jss_core_Events_CommandEvent_EventID int
#define urn_jaus_jss_core_Events_CommandEvent_CommandResult mtype
urn_jaus_jss_core_Events_CommandEvent_CommandResult {SUCCESSFUL, UNSUCCESSFUL};
typedef urn_jaus_jss_core_Events_CommandEvent_EventRec{
	int EventID;
	urn_jaus_jss_core_Events_CommandEvent_CommandResult CommandResult;
};


/**
*  The CommandEvent message is sent when a command specified by a previous Create Command Event message
*  has completed or expired. It includes the Event ID and command result.
*/
typedef urn_jaus_jss_core_Events_CommandEvent{
	urn_jaus_jss_core_Events_CommandEvent_HeaderRec HeaderRec;
	urn_jaus_jss_core_Events_CommandEvent_EventRec EventRec;
};
#define urn_jaus_jss_core_Events_ReportEventTimeout_MessageID int
typedef urn_jaus_jss_core_Events_ReportEventTimeout_HeaderRec{
	int MessageID;
};


typedef urn_jaus_jss_core_Events_ReportEventTimeout_MsgHeader{
	urn_jaus_jss_core_Events_ReportEventTimeout_HeaderRec HeaderRec;
};

#define urn_jaus_jss_core_Events_ReportEventTimeout_Timeout mtype
urn_jaus_jss_core_Events_ReportEventTimeout_Timeout {TIMEOUT_FEATURE_DISABLED};
typedef urn_jaus_jss_core_Events_ReportEventTimeout_ReportTimoutRec{
	urn_jaus_jss_core_Events_ReportEventTimeout_Timeout Timeout;
};


/**
*  This message is used to report the timeout period of this service.
*/
typedef urn_jaus_jss_core_Events_ReportEventTimeout{
	urn_jaus_jss_core_Events_ReportEventTimeout_HeaderRec HeaderRec;
	urn_jaus_jss_core_Events_ReportEventTimeout_ReportTimoutRec ReportTimoutRec;
};
