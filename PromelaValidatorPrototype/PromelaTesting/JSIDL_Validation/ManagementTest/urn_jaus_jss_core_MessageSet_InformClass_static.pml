/** 
 *  This Promela file was auto-generated using JTS on Wed May 18 12:47:44 EDT 2011.
 *  ID=urn:jaus:jss:core:MessageSet:InformClass  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

typedef urn_jaus_jss_core_MessageSet_InformClass_ReportAuthority_ReportAuthorityRec{
	urn_jaus_jss_core_MessageSet_BasicTypes_AuthorityCode AuthorityCode;
	
};


/**
*  This message is used to report the current command authority.
*/
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportAuthority{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	urn_jaus_jss_core_MessageSet_InformClass_ReportAuthority_ReportAuthorityRec ReportAuthorityRec;
};
#define urn_jaus_jss_core_MessageSet_InformClass_ReportStatus_Status mtype
urn_jaus_jss_core_MessageSet_InformClass_ReportStatus_Status {INITIALIZE, READY, STANDBY, SHUTDOWN, FAILURE, EMERGENCY};
//  This field is reserved for compatibility with previous versions of the Standard.
#define urn_jaus_jss_core_MessageSet_InformClass_ReportStatus_Reserved int
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportStatus_ReportStatusRec{
	urn_jaus_jss_core_MessageSet_InformClass_ReportStatus_Status Status;
	//  This field is reserved for compatibility with previous versions of the Standard.
	int Reserved;
};


/**
*  This message is used to report the current status of the sender of the message.
*/
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportStatus{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	urn_jaus_jss_core_MessageSet_InformClass_ReportStatus_ReportStatusRec ReportStatusRec;
};
#define urn_jaus_jss_core_MessageSet_InformClass_ReportTimeout_Timeout mtype
urn_jaus_jss_core_MessageSet_InformClass_ReportTimeout_Timeout {TIMEOUT_FEATURE_DISABLED};
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportTimeout_ReportTimoutRec{
	urn_jaus_jss_core_MessageSet_InformClass_ReportTimeout_Timeout Timeout;
};


/**
*  This message is used to report the timeout period of this message.
*/
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportTimeout{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	urn_jaus_jss_core_MessageSet_InformClass_ReportTimeout_ReportTimoutRec ReportTimoutRec;
};
/**
*  This message is used to report the current time of this service to querying clients. All times are
*  in Coordinated Universal Time (UTC).
*/
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportTime{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	urn_jaus_jss_core_MessageSet_BasicTypes_TimeRec TimeRec;
};
//  Subsystem ID
#define urn_jaus_jss_core_MessageSet_InformClass_ReportControl_SubsystemID int
//  Node ID
#define urn_jaus_jss_core_MessageSet_InformClass_ReportControl_NodeID int
//  Component ID
#define urn_jaus_jss_core_MessageSet_InformClass_ReportControl_ComponentID int
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportControl_ReportControlRec{
	//  Subsystem ID
	int SubsystemID;
	//  Node ID
	int NodeID;
	//  Component ID
	int ComponentID;
	urn_jaus_jss_core_MessageSet_BasicTypes_AuthorityCode AuthorityCode;
	
};


/**
*  This message is used to report the current state of control of this service. If the service is in the
*  Controlled state, this message reports the ID of the controlling component. The ID fields shall
*  be set to zero (0) if this service is in the NotControlled state..
*/
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportControl{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	urn_jaus_jss_core_MessageSet_InformClass_ReportControl_ReportControlRec ReportControlRec;
};
#define urn_jaus_jss_core_MessageSet_InformClass_ReportEvents_ReportEventRec_EventType mtype
//  Unique identifier of event
#define urn_jaus_jss_core_MessageSet_InformClass_ReportEvents_ReportEventRec_EventID int
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportEvents_ReportEventRec{
	//  Type of event
	urn_jaus_jss_core_MessageSet_InformClass_ReportEvents_ReportEventRec_EventType EventType;
	//  Unique identifier of event
	int EventID;
};


//  List of reported events
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportEvents_EventList{
	urn_jaus_jss_core_MessageSet_InformClass_ReportEvents_ReportEventRec ReportEventRec;
};


/**
*  This message is used to report the active event requests that match the requirements provided in
*  the QueryEvents message.
*/
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportEvents{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	// skipped processing List, since its not well supported in Promela.
};
//  Unique identifier of the enclosed event
#define urn_jaus_jss_core_MessageSet_InformClass_Event_EventID int
//  Sequential count of the number of times this event has been issued
#define urn_jaus_jss_core_MessageSet_InformClass_Event_SequenceNumber int
typedef urn_jaus_jss_core_MessageSet_InformClass_Event_EventRec{
	//  Unique identifier of the enclosed event
	int EventID;
	//  Sequential count of the number of times this event has been issued
	int SequenceNumber;
};


/**
*  The Event message is sent when event is triggered. It includes the Event ID and a sequence number to
*  allow the client to keep track of event processing.
*/
typedef urn_jaus_jss_core_MessageSet_InformClass_Event{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	urn_jaus_jss_core_MessageSet_InformClass_Event_EventRec EventRec;
};
#define urn_jaus_jss_core_MessageSet_InformClass_ReportEventTimeout_Timeout mtype
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportEventTimeout_ReportTimoutRec{
	urn_jaus_jss_core_MessageSet_InformClass_ReportEventTimeout_Timeout Timeout;
};


/**
*  This message is used to report the timeout period of this service.
*/
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportEventTimeout{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	urn_jaus_jss_core_MessageSet_InformClass_ReportEventTimeout_ReportTimoutRec ReportTimoutRec;
};
//  Unique identifier of the enclosed event
#define urn_jaus_jss_core_MessageSet_InformClass_CommandEvent_EventID int
#define urn_jaus_jss_core_MessageSet_InformClass_CommandEvent_CommandResult mtype
urn_jaus_jss_core_MessageSet_InformClass_CommandEvent_CommandResult {SUCCESSFUL, UNSUCCESSFUL};
typedef urn_jaus_jss_core_MessageSet_InformClass_CommandEvent_EventRec{
	//  Unique identifier of the enclosed event
	int EventID;
	urn_jaus_jss_core_MessageSet_InformClass_CommandEvent_CommandResult CommandResult;
};


/**
*  The CommandEvent message is sent when a command specified by a previous Create Command Event message
*  has completed or expired. It includes the Event ID and command result.
*/
typedef urn_jaus_jss_core_MessageSet_InformClass_CommandEvent{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	urn_jaus_jss_core_MessageSet_InformClass_CommandEvent_EventRec EventRec;
};
/**
*  This message notifies the receiver that the sender is alive.
*/
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportHeartbeatPulse{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
};
/**
*  This message is used to report a single element from a list. The element is uniquely identified by
*  the UID, while it’s position within the list is denoted by the previous (parent) and next (child)
*  elements. The message data is identical to the Element Record in ID 041Ah: SetElement.
*/
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportElement{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	urn_jaus_jss_core_MessageSet_BasicTypes_ElementRec ElementRec;
};
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportElementList_ElementListRec{
	//  UID of the elements in the list.
	urn_jaus_jss_core_MessageSet_BasicTypes_ElementUID ElementUID;
	
};


typedef urn_jaus_jss_core_MessageSet_InformClass_ReportElementList_ElementList{
	urn_jaus_jss_core_MessageSet_InformClass_ReportElementList_ElementListRec ElementListRec;
};


/**
*  This message is used to report the UIDs for all elements in a list.
*/
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportElementList{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	// skipped processing List, since its not well supported in Promela.
};
//  Number of elements currently in the list
#define urn_jaus_jss_core_MessageSet_InformClass_ReportElementCount_ElementCount int
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportElementCount_ElementCountRec{
	//  Number of elements currently in the list
	int ElementCount;
};


/**
*  This message is used to report the number of elements in a list.
*/
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportElementCount{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	urn_jaus_jss_core_MessageSet_InformClass_ReportElementCount_ElementCountRec ElementCountRec;
};
#define urn_jaus_jss_core_MessageSet_InformClass_ReportIdentification_QueryType mtype
urn_jaus_jss_core_MessageSet_InformClass_ReportIdentification_QueryType {Reserved, SystemIdentification, SubsystemIdentification, NodeIdentification, ComponentIdentification};
#define urn_jaus_jss_core_MessageSet_InformClass_ReportIdentification_Type mtype
urn_jaus_jss_core_MessageSet_InformClass_ReportIdentification_Type {RESERVED, VEHICLE, OCU, OTHER_SUBSYSTEM, NODE, PAYLOAD, COMPONENT};
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportIdentification_ReportIdentificationRec{
	urn_jaus_jss_core_MessageSet_InformClass_ReportIdentification_QueryType QueryType;
	//  This field identifies the particular unmanned vehicle (subsystem) type, node type or component
	//  type based on the following enumeration
	urn_jaus_jss_core_MessageSet_InformClass_ReportIdentification_Type Type;
};


/**
*  This message shall provide the requesting component an identification summary of the Subsystem,
*  Node, or Component.
*/
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportIdentification{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	urn_jaus_jss_core_MessageSet_InformClass_ReportIdentification_ReportIdentificationRec ReportIdentificationRec;
};
//  Node ID. For single Node or Component reporting this field shall contain the Node ID as specified
//  in the Destination Address of the Query Configuration message
#define urn_jaus_jss_core_MessageSet_InformClass_ReportConfiguration_NodeSeq_NodeRec_NodeID int
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportConfiguration_NodeSeq_NodeRec{
	//  Node ID. For single Node or Component reporting this field shall contain the Node ID as specified
	//  in the Destination Address of the Query Configuration message
	int NodeID;
};


//  Component ID. For Single Component reporting this field shall contain the Component ID as specified
//  in the Destination Address of the Query Configuration message and shall be the only Component reported
#define urn_jaus_jss_core_MessageSet_InformClass_ReportConfiguration_NodeSeq_ComponentList_ComponentRec_ComponentID int
//  Inst ID when legacy Components are reported; a value of zero (0) shall be used for non-legacy components.
#define urn_jaus_jss_core_MessageSet_InformClass_ReportConfiguration_NodeSeq_ComponentList_ComponentRec_InstanceID int
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportConfiguration_NodeSeq_ComponentList_ComponentRec{
	//  Component ID. For Single Component reporting this field shall contain the Component ID as specified
	//  in the Destination Address of the Query Configuration message and shall be the only Component reported
	int ComponentID;
	//  Inst ID when legacy Components are reported; a value of zero (0) shall be used for non-legacy components.
	int InstanceID;
};


typedef urn_jaus_jss_core_MessageSet_InformClass_ReportConfiguration_NodeSeq_ComponentList{
	urn_jaus_jss_core_MessageSet_InformClass_ReportConfiguration_NodeSeq_ComponentList_ComponentRec ComponentRec;
};


typedef urn_jaus_jss_core_MessageSet_InformClass_ReportConfiguration_NodeSeq{
	urn_jaus_jss_core_MessageSet_InformClass_ReportConfiguration_NodeSeq_NodeRec NodeRec;
	urn_jaus_jss_core_MessageSet_InformClass_ReportConfiguration_NodeSeq_ComponentList ComponentList[255];
};
//  # of Nodes reported. For a single Node Report this field shall be 1
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportConfiguration_NodeList{
	urn_jaus_jss_core_MessageSet_InformClass_ReportConfiguration_NodeSeq NodeSeq;
};


/**
*  This message is used to provide the receiver a table of all existing components located on the source’s
*  subsystem or node depending on the value of field 1 of the Code 2B01h: Query Configuration message.
*/
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportConfiguration{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	// skipped processing List, since its not well supported in Promela.
};
//  Subsystem ID
#define urn_jaus_jss_core_MessageSet_InformClass_ReportSubsystemList_SubsystemRec_SubsystemID int
//  Node ID used for Query Configuration.
#define urn_jaus_jss_core_MessageSet_InformClass_ReportSubsystemList_SubsystemRec_NodeID int
//  Component ID used for Query Configuration.
#define urn_jaus_jss_core_MessageSet_InformClass_ReportSubsystemList_SubsystemRec_ComponentID int
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportSubsystemList_SubsystemRec{
	//  Subsystem ID
	int SubsystemID;
	//  Node ID used for Query Configuration.
	int NodeID;
	//  Component ID used for Query Configuration.
	int ComponentID;
};


typedef urn_jaus_jss_core_MessageSet_InformClass_ReportSubsystemList_SubsystemList{
	urn_jaus_jss_core_MessageSet_InformClass_ReportSubsystemList_SubsystemRec SubsystemRec;
};


/**
*  This message shall provide the receiving component a table of all subsystems located in the source’s
*  system. It also provides the ID of the component to send a Query Configuration message within the
*  subsystem.
*/
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportSubsystemList{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	// skipped processing List, since its not well supported in Promela.
};
//  Node ID. For single Node or Component reporting this field shall contain the Node ID as specified
//  in the Destination Address of the Query Configuration message
#define urn_jaus_jss_core_MessageSet_InformClass_ReportServices_NodeSeq_NodeRec_NodeID int
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportServices_NodeSeq_NodeRec{
	//  Node ID. For single Node or Component reporting this field shall contain the Node ID as specified
	//  in the Destination Address of the Query Configuration message
	int NodeID;
};


//  Component ID. For Single Component reporting this field shall contain the Component ID as specified
//  in the Destination Address of the Query Configuration message and shall be the only Component reported
#define urn_jaus_jss_core_MessageSet_InformClass_ReportServices_NodeSeq_ComponentList_ComponentSeq_ComponentRec_ComponentID int
//  Inst ID when legacy Components are reported; a value of zero (0) shall be used for non-legacy components.
#define urn_jaus_jss_core_MessageSet_InformClass_ReportServices_NodeSeq_ComponentList_ComponentSeq_ComponentRec_InstanceID int
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportServices_NodeSeq_ComponentList_ComponentSeq_ComponentRec{
	//  Component ID. For Single Component reporting this field shall contain the Component ID as specified
	//  in the Destination Address of the Query Configuration message and shall be the only Component reported
	int ComponentID;
	//  Inst ID when legacy Components are reported; a value of zero (0) shall be used for non-legacy components.
	int InstanceID;
};


#define urn_jaus_jss_core_MessageSet_InformClass_ReportServices_NodeSeq_ComponentList_ComponentSeq_ServiceList_ServiceRec_MajorVersionNumber int
#define urn_jaus_jss_core_MessageSet_InformClass_ReportServices_NodeSeq_ComponentList_ComponentSeq_ServiceList_ServiceRec_MinorVersionNumber int
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportServices_NodeSeq_ComponentList_ComponentSeq_ServiceList_ServiceRec{
	int MajorVersionNumber;
	int MinorVersionNumber;
};


typedef urn_jaus_jss_core_MessageSet_InformClass_ReportServices_NodeSeq_ComponentList_ComponentSeq_ServiceList{
	urn_jaus_jss_core_MessageSet_InformClass_ReportServices_NodeSeq_ComponentList_ComponentSeq_ServiceList_ServiceRec ServiceRec;
};


typedef urn_jaus_jss_core_MessageSet_InformClass_ReportServices_NodeSeq_ComponentList_ComponentSeq{
	urn_jaus_jss_core_MessageSet_InformClass_ReportServices_NodeSeq_ComponentList_ComponentSeq_ComponentRec ComponentRec;
	urn_jaus_jss_core_MessageSet_InformClass_ReportServices_NodeSeq_ComponentList_ComponentSeq_ServiceList ServiceList[255];
};
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportServices_NodeSeq_ComponentList{
	urn_jaus_jss_core_MessageSet_InformClass_ReportServices_NodeSeq_ComponentList_ComponentSeq ComponentSeq;
};


typedef urn_jaus_jss_core_MessageSet_InformClass_ReportServices_NodeSeq{
	urn_jaus_jss_core_MessageSet_InformClass_ReportServices_NodeSeq_NodeRec NodeRec;
	urn_jaus_jss_core_MessageSet_InformClass_ReportServices_NodeSeq_ComponentList ComponentList[255];
};
//  # of Nodes reported. For a single Node Report this field shall be 1
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportServices_NodeList{
	urn_jaus_jss_core_MessageSet_InformClass_ReportServices_NodeSeq NodeSeq;
};


/**
*  This message allows a component to publish its capabilities, according to the Service Dictionary
*  presented below. If a component ID is specified in the RA, it may report only one service in beyond
*  the core message support, and this service must be equal to the component ID. If a component ID is not
*  listed in the RA, it may report any number of services. For example, a component with ID 33 must provide
*  only service 33. The exception to this rule is component ID 1 (the Node Manager) which may provide
*  any number of services in addition to core message support.
*/
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportServices{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	// skipped processing List, since its not well supported in Promela.
};
//  Subsystem ID
#define urn_jaus_jss_core_MessageSet_InformClass_ReportServiceList_SubsystemSeq_SubsystemRec_SubsystemID int
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportServiceList_SubsystemSeq_SubsystemRec{
	//  Subsystem ID
	int SubsystemID;
};


//  Node ID. For single Node or Component reporting this field shall contain the Node ID as specified
//  in the Destination Address of the Query Configuration message
#define urn_jaus_jss_core_MessageSet_InformClass_ReportServiceList_SubsystemSeq_NodeList_NodeSeq_NodeRec_NodeID int
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportServiceList_SubsystemSeq_NodeList_NodeSeq_NodeRec{
	//  Node ID. For single Node or Component reporting this field shall contain the Node ID as specified
	//  in the Destination Address of the Query Configuration message
	int NodeID;
};


//  Component ID. For Single Component reporting this field shall contain the Component ID as specified
//  in the Destination Address of the Query Configuration message and shall be the only Component reported
#define urn_jaus_jss_core_MessageSet_InformClass_ReportServiceList_SubsystemSeq_NodeList_NodeSeq_ComponentList_ComponentSeq_ComponentRec_ComponentID int
//  Inst ID when legacy Components are reported; a value of zero (0) shall be used for non-legacy components.
#define urn_jaus_jss_core_MessageSet_InformClass_ReportServiceList_SubsystemSeq_NodeList_NodeSeq_ComponentList_ComponentSeq_ComponentRec_InstanceID int
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportServiceList_SubsystemSeq_NodeList_NodeSeq_ComponentList_ComponentSeq_ComponentRec{
	//  Component ID. For Single Component reporting this field shall contain the Component ID as specified
	//  in the Destination Address of the Query Configuration message and shall be the only Component reported
	int ComponentID;
	//  Inst ID when legacy Components are reported; a value of zero (0) shall be used for non-legacy components.
	int InstanceID;
};


#define urn_jaus_jss_core_MessageSet_InformClass_ReportServiceList_SubsystemSeq_NodeList_NodeSeq_ComponentList_ComponentSeq_ServiceList_ServiceRec_MajorVersionNumber int
#define urn_jaus_jss_core_MessageSet_InformClass_ReportServiceList_SubsystemSeq_NodeList_NodeSeq_ComponentList_ComponentSeq_ServiceList_ServiceRec_MinorVersionNumber int
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportServiceList_SubsystemSeq_NodeList_NodeSeq_ComponentList_ComponentSeq_ServiceList_ServiceRec{
	int MajorVersionNumber;
	int MinorVersionNumber;
};


typedef urn_jaus_jss_core_MessageSet_InformClass_ReportServiceList_SubsystemSeq_NodeList_NodeSeq_ComponentList_ComponentSeq_ServiceList{
	urn_jaus_jss_core_MessageSet_InformClass_ReportServiceList_SubsystemSeq_NodeList_NodeSeq_ComponentList_ComponentSeq_ServiceList_ServiceRec ServiceRec;
};


typedef urn_jaus_jss_core_MessageSet_InformClass_ReportServiceList_SubsystemSeq_NodeList_NodeSeq_ComponentList_ComponentSeq{
	urn_jaus_jss_core_MessageSet_InformClass_ReportServiceList_SubsystemSeq_NodeList_NodeSeq_ComponentList_ComponentSeq_ComponentRec ComponentRec;
	urn_jaus_jss_core_MessageSet_InformClass_ReportServiceList_SubsystemSeq_NodeList_NodeSeq_ComponentList_ComponentSeq_ServiceList ServiceList[255];
};
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportServiceList_SubsystemSeq_NodeList_NodeSeq_ComponentList{
	urn_jaus_jss_core_MessageSet_InformClass_ReportServiceList_SubsystemSeq_NodeList_NodeSeq_ComponentList_ComponentSeq ComponentSeq;
};


typedef urn_jaus_jss_core_MessageSet_InformClass_ReportServiceList_SubsystemSeq_NodeList_NodeSeq{
	urn_jaus_jss_core_MessageSet_InformClass_ReportServiceList_SubsystemSeq_NodeList_NodeSeq_NodeRec NodeRec;
	urn_jaus_jss_core_MessageSet_InformClass_ReportServiceList_SubsystemSeq_NodeList_NodeSeq_ComponentList ComponentList[255];
};
//  # of Nodes reported. For a single Node Report this field shall be 1
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportServiceList_SubsystemSeq_NodeList{
	urn_jaus_jss_core_MessageSet_InformClass_ReportServiceList_SubsystemSeq_NodeList_NodeSeq NodeSeq;
};


typedef urn_jaus_jss_core_MessageSet_InformClass_ReportServiceList_SubsystemSeq{
	urn_jaus_jss_core_MessageSet_InformClass_ReportServiceList_SubsystemSeq_SubsystemRec SubsystemRec;
	//  # of Nodes reported. For a single Node Report this field shall be 1
	urn_jaus_jss_core_MessageSet_InformClass_ReportServiceList_SubsystemSeq_NodeList NodeList[255];
};
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportServiceList_SubsystemList{
	urn_jaus_jss_core_MessageSet_InformClass_ReportServiceList_SubsystemSeq SubsystemSeq;
};


/**
*  This message allows a component to publish its capabilities, according to the Service Dictionary
*  presented below. If a component ID is specified in the RA, it may report only one service in beyond
*  the core message support, and this service must be equal to the component ID. If a component ID is not
*  listed in the RA, it may report any number of services. For example, a component with ID 33 must provide
*  only service 33. The exception to this rule is component ID 1 (the Node Manager) which may provide
*  any number of services in addition to core message support.
*/
typedef urn_jaus_jss_core_MessageSet_InformClass_ReportServiceList{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	// skipped processing List, since its not well supported in Promela.
};
