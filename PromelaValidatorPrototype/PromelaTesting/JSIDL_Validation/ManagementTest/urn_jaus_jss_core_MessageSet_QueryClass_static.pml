/** 
 *  This Promela file was auto-generated using JTS on Wed May 18 12:47:45 EDT 2011.
 *  ID=urn:jaus:jss:core:MessageSet:QueryClass  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

/**
*  This message is used by clients to query the current value of the authority code of this service.
*/
typedef urn_jaus_jss_core_MessageSet_QueryClass_QueryAuthority{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
};
/**
*  This message is used by clients of this service to query the state of this service.
*/
typedef urn_jaus_jss_core_MessageSet_QueryClass_QueryStatus{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
};
/**
*  This message is used by clients of this service to query the timeout period of this service.
*/
typedef urn_jaus_jss_core_MessageSet_QueryClass_QueryTimeout{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
};
//  See corresponding report time message for presence vector mapping
#define urn_jaus_jss_core_MessageSet_QueryClass_QueryTime_PresenceVector int
typedef urn_jaus_jss_core_MessageSet_QueryClass_QueryTime_QueryTimeRec{
	//  See corresponding report time message for presence vector mapping
	int PresenceVector;
};


/**
*  This message is used by clients of this service to query the current time of this service.
*/
typedef urn_jaus_jss_core_MessageSet_QueryClass_QueryTime{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	urn_jaus_jss_core_MessageSet_QueryClass_QueryTime_QueryTimeRec QueryTimeRec;
};
/**
*  This message is used by clients to query the current control state of this service.
*/
typedef urn_jaus_jss_core_MessageSet_QueryClass_QueryControl{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
};
/**
*  The Query Events message is used to request detail on events. Queries can be made by message ID, event
*  type or Event ID.
*/
typedef urn_jaus_jss_core_MessageSet_QueryClass_QueryEvents{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
};
/**
*  This message is used by clients to query the timeout period of this service.
*/
typedef urn_jaus_jss_core_MessageSet_QueryClass_QueryEventTimeout{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
};
/**
*  This message shall be used to query for a heartbeat pulse.
*/
typedef urn_jaus_jss_core_MessageSet_QueryClass_QueryHeartbeatPulse{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
};
typedef urn_jaus_jss_core_MessageSet_QueryClass_QueryElement_QueryElementRec{
	//  UID of the queried element. A value of zero (0) represents the first (head) element in the list.
	urn_jaus_jss_core_MessageSet_BasicTypes_ElementUID ElementUID;
	
};


/**
*  This message is used to query an element from a list. The element is uniquely identified by the UID.
*/
typedef urn_jaus_jss_core_MessageSet_QueryClass_QueryElement{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	urn_jaus_jss_core_MessageSet_QueryClass_QueryElement_QueryElementRec QueryElementRec;
};
/**
*  This message is used to query all elements from a list.
*/
typedef urn_jaus_jss_core_MessageSet_QueryClass_QueryElementList{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
};
/**
*  This message is used to query the number of elements in a list.
*/
typedef urn_jaus_jss_core_MessageSet_QueryClass_QueryElementCount{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
};
#define urn_jaus_jss_core_MessageSet_QueryClass_QueryIdentification_QueryType mtype
typedef urn_jaus_jss_core_MessageSet_QueryClass_QueryIdentification_QueryIdentificationRec{
	urn_jaus_jss_core_MessageSet_QueryClass_QueryIdentification_QueryType QueryType;
};


/**
*  This message shall request the identification summary of a Subsystem, Node, or Component.
*/
typedef urn_jaus_jss_core_MessageSet_QueryClass_QueryIdentification{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	urn_jaus_jss_core_MessageSet_QueryClass_QueryIdentification_QueryIdentificationRec QueryIdentificationRec;
};
#define urn_jaus_jss_core_MessageSet_QueryClass_QueryConfiguration_QueryType mtype
typedef urn_jaus_jss_core_MessageSet_QueryClass_QueryConfiguration_QueryConfigurationRec{
	urn_jaus_jss_core_MessageSet_QueryClass_QueryConfiguration_QueryType QueryType;
};


/**
*  This message shall request the configuration summary of a subsystem or node. For example, to get
*  the complete configuration of a subsystem, field 1 shall be set to 2.
*/
typedef urn_jaus_jss_core_MessageSet_QueryClass_QueryConfiguration{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	urn_jaus_jss_core_MessageSet_QueryClass_QueryConfiguration_QueryConfigurationRec QueryConfigurationRec;
};
/**
*  This message shall request the Report Subsystem List message. There are no data fields associated
*  with this message.
*/
typedef urn_jaus_jss_core_MessageSet_QueryClass_QuerySubsystemList{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
};
#define urn_jaus_jss_core_MessageSet_QueryClass_QueryServices_NodeSeq_NodeRec_NodeID mtype
urn_jaus_jss_core_MessageSet_QueryClass_QueryServices_NodeSeq_NodeRec_NodeID {Allnodesinthesubsystem};
typedef urn_jaus_jss_core_MessageSet_QueryClass_QueryServices_NodeSeq_NodeRec{
	//  Use 255 if service information from all nodes in the subsystem is required
	urn_jaus_jss_core_MessageSet_QueryClass_QueryServices_NodeSeq_NodeRec_NodeID NodeID;
};


#define urn_jaus_jss_core_MessageSet_QueryClass_QueryServices_NodeSeq_ComponentList_ComponentRec_ComponentID mtype
urn_jaus_jss_core_MessageSet_QueryClass_QueryServices_NodeSeq_ComponentList_ComponentRec_ComponentID {Allcomponentsinthesubsystem};
typedef urn_jaus_jss_core_MessageSet_QueryClass_QueryServices_NodeSeq_ComponentList_ComponentRec{
	//  Use 255 if service information from components in the node are required
	urn_jaus_jss_core_MessageSet_QueryClass_QueryServices_NodeSeq_ComponentList_ComponentRec_ComponentID ComponentID;
};


typedef urn_jaus_jss_core_MessageSet_QueryClass_QueryServices_NodeSeq_ComponentList{
	urn_jaus_jss_core_MessageSet_QueryClass_QueryServices_NodeSeq_ComponentList_ComponentRec ComponentRec;
};


typedef urn_jaus_jss_core_MessageSet_QueryClass_QueryServices_NodeSeq{
	urn_jaus_jss_core_MessageSet_QueryClass_QueryServices_NodeSeq_NodeRec NodeRec;
	urn_jaus_jss_core_MessageSet_QueryClass_QueryServices_NodeSeq_ComponentList ComponentList[255];
};
//  # of Nodes reported. For a single Node Report this field shall be 1
typedef urn_jaus_jss_core_MessageSet_QueryClass_QueryServices_NodeList{
	urn_jaus_jss_core_MessageSet_QueryClass_QueryServices_NodeSeq NodeSeq;
};


/**
*  This message allows a component to request the service information of an entire subsystem or node,
*  or a single component. The corresponding Report Services message will respond with service information
*  only for new component implementations. It will not report service information for legacy component
*  implementations.
*/
typedef urn_jaus_jss_core_MessageSet_QueryClass_QueryServices{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	// skipped processing List, since its not well supported in Promela.
};
#define urn_jaus_jss_core_MessageSet_QueryClass_QueryServiceList_SubsystemSeq_SubsystemRec_SubsystemID mtype
urn_jaus_jss_core_MessageSet_QueryClass_QueryServiceList_SubsystemSeq_SubsystemRec_SubsystemID {Allsubsystemsinthesystem};
typedef urn_jaus_jss_core_MessageSet_QueryClass_QueryServiceList_SubsystemSeq_SubsystemRec{
	//  Use 65535 if service information from all subsystems in the system is required
	urn_jaus_jss_core_MessageSet_QueryClass_QueryServiceList_SubsystemSeq_SubsystemRec_SubsystemID SubsystemID;
};


#define urn_jaus_jss_core_MessageSet_QueryClass_QueryServiceList_SubsystemSeq_NodeList_NodeSeq_NodeRec_NodeID mtype
typedef urn_jaus_jss_core_MessageSet_QueryClass_QueryServiceList_SubsystemSeq_NodeList_NodeSeq_NodeRec{
	//  Use 255 if service information from all nodes in the subsystem is required
	urn_jaus_jss_core_MessageSet_QueryClass_QueryServiceList_SubsystemSeq_NodeList_NodeSeq_NodeRec_NodeID NodeID;
};


#define urn_jaus_jss_core_MessageSet_QueryClass_QueryServiceList_SubsystemSeq_NodeList_NodeSeq_ComponentList_ComponentRec_ComponentID mtype
typedef urn_jaus_jss_core_MessageSet_QueryClass_QueryServiceList_SubsystemSeq_NodeList_NodeSeq_ComponentList_ComponentRec{
	//  Use 255 if service information from components in the node are required
	urn_jaus_jss_core_MessageSet_QueryClass_QueryServiceList_SubsystemSeq_NodeList_NodeSeq_ComponentList_ComponentRec_ComponentID ComponentID;
};


typedef urn_jaus_jss_core_MessageSet_QueryClass_QueryServiceList_SubsystemSeq_NodeList_NodeSeq_ComponentList{
	urn_jaus_jss_core_MessageSet_QueryClass_QueryServiceList_SubsystemSeq_NodeList_NodeSeq_ComponentList_ComponentRec ComponentRec;
};


typedef urn_jaus_jss_core_MessageSet_QueryClass_QueryServiceList_SubsystemSeq_NodeList_NodeSeq{
	urn_jaus_jss_core_MessageSet_QueryClass_QueryServiceList_SubsystemSeq_NodeList_NodeSeq_NodeRec NodeRec;
	urn_jaus_jss_core_MessageSet_QueryClass_QueryServiceList_SubsystemSeq_NodeList_NodeSeq_ComponentList ComponentList[255];
};
//  # of Nodes reported. For a single Node Report this field shall be 1
typedef urn_jaus_jss_core_MessageSet_QueryClass_QueryServiceList_SubsystemSeq_NodeList{
	urn_jaus_jss_core_MessageSet_QueryClass_QueryServiceList_SubsystemSeq_NodeList_NodeSeq NodeSeq;
};


typedef urn_jaus_jss_core_MessageSet_QueryClass_QueryServiceList_SubsystemSeq{
	urn_jaus_jss_core_MessageSet_QueryClass_QueryServiceList_SubsystemSeq_SubsystemRec SubsystemRec;
	//  # of Nodes reported. For a single Node Report this field shall be 1
	urn_jaus_jss_core_MessageSet_QueryClass_QueryServiceList_SubsystemSeq_NodeList NodeList[255];
};
//  Number of subsystems reported. For a single subsystem report this field shall be 1
typedef urn_jaus_jss_core_MessageSet_QueryClass_QueryServiceList_SubsystemList{
	urn_jaus_jss_core_MessageSet_QueryClass_QueryServiceList_SubsystemSeq SubsystemSeq;
};


/**
*  This message allows a component to request the service information of services across all subsystems.
*  The corresponding Report Services message will respond with service information only for new component
*  implementations. It will not report service information for legacy component implementations.
*/
typedef urn_jaus_jss_core_MessageSet_QueryClass_QueryServiceList{
	urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader MsgHeader;
	// skipped processing List, since its not well supported in Promela.
};
