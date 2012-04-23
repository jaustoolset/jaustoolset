/** 
 *  This Promela file was auto-generated using JTS on Mon Jun 13 16:10:57 EDT 2011.
 *  ID=urn:jaus:jss:core:Management  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

#define urn_jaus_jss_core_Management_Resume_MessageID int
typedef urn_jaus_jss_core_Management_Resume_HeaderRec{
	int MessageID;
};


typedef urn_jaus_jss_core_Management_Resume_MsgHeader{
	urn_jaus_jss_core_Management_Resume_HeaderRec HeaderRec;
};

/**
*  This message is used to transition the receiving component to the ready state.
*/
typedef urn_jaus_jss_core_Management_Resume{
	urn_jaus_jss_core_Management_Resume_HeaderRec HeaderRec;
};
#define urn_jaus_jss_core_Management_Reset_MessageID int
typedef urn_jaus_jss_core_Management_Reset_HeaderRec{
	int MessageID;
};


typedef urn_jaus_jss_core_Management_Reset_MsgHeader{
	urn_jaus_jss_core_Management_Reset_HeaderRec HeaderRec;
};

/**
*  This message is used to cause the receiving component to reinitialize.
*/
typedef urn_jaus_jss_core_Management_Reset{
	urn_jaus_jss_core_Management_Reset_HeaderRec HeaderRec;
};
#define urn_jaus_jss_core_Management_SetEmergency_MessageID int
typedef urn_jaus_jss_core_Management_SetEmergency_HeaderRec{
	int MessageID;
};


typedef urn_jaus_jss_core_Management_SetEmergency_MsgHeader{
	urn_jaus_jss_core_Management_SetEmergency_HeaderRec HeaderRec;
};

#define urn_jaus_jss_core_Management_SetEmergency_EmergencyCode mtype
urn_jaus_jss_core_Management_SetEmergency_EmergencyCode {STOP};
typedef urn_jaus_jss_core_Management_SetEmergency_SetEmergencyRec{
	urn_jaus_jss_core_Management_SetEmergency_EmergencyCode EmergencyCode;
};


/**
*  This message is used to alert the component to a safety critical situation. The component that sends
*  the emergency command shall set the message priority to the safety critical priority range as defined
*  by the Transport. Receive of the emergency command shall result in the component transitioning
*  into the emergency state.
*/
typedef urn_jaus_jss_core_Management_SetEmergency{
	urn_jaus_jss_core_Management_SetEmergency_HeaderRec HeaderRec;
	urn_jaus_jss_core_Management_SetEmergency_SetEmergencyRec SetEmergencyRec;
};
#define urn_jaus_jss_core_Management_ClearEmergency_MessageID int
typedef urn_jaus_jss_core_Management_ClearEmergency_HeaderRec{
	int MessageID;
};


typedef urn_jaus_jss_core_Management_ClearEmergency_MsgHeader{
	urn_jaus_jss_core_Management_ClearEmergency_HeaderRec HeaderRec;
};

#define urn_jaus_jss_core_Management_ClearEmergency_EmergencyCode mtype
typedef urn_jaus_jss_core_Management_ClearEmergency_ClearEmergencyRec{
	urn_jaus_jss_core_Management_ClearEmergency_EmergencyCode EmergencyCode;
};


/**
*  This message shall notify the receiving component that the current emergency condition is to be
*  reset and that the component shall transition back to the Ready or Standby state, provided that all
*  emergency conditions have been cleared. JAUS currently defines only one emergency condition,
*  the "Stop" condition. Future versions of this document could define other emergency conditions.
*/
typedef urn_jaus_jss_core_Management_ClearEmergency{
	urn_jaus_jss_core_Management_ClearEmergency_HeaderRec HeaderRec;
	urn_jaus_jss_core_Management_ClearEmergency_ClearEmergencyRec ClearEmergencyRec;
};
#define urn_jaus_jss_core_Management_QueryStatus_MessageID int
typedef urn_jaus_jss_core_Management_QueryStatus_HeaderRec{
	int MessageID;
};


typedef urn_jaus_jss_core_Management_QueryStatus_MsgHeader{
	urn_jaus_jss_core_Management_QueryStatus_HeaderRec HeaderRec;
};

/**
*  This message is used by clients of this service to query the state of this service.
*/
typedef urn_jaus_jss_core_Management_QueryStatus{
	urn_jaus_jss_core_Management_QueryStatus_HeaderRec HeaderRec;
};
#define urn_jaus_jss_core_Management_Shutdown_MessageID int
typedef urn_jaus_jss_core_Management_Shutdown_HeaderRec{
	int MessageID;
};


typedef urn_jaus_jss_core_Management_Shutdown_MsgHeader{
	urn_jaus_jss_core_Management_Shutdown_HeaderRec HeaderRec;
};

/**
*  This message is used to cause the receiving component to free all of the resources allocated to its
*  process by the system and then to shutdown.
*/
typedef urn_jaus_jss_core_Management_Shutdown{
	urn_jaus_jss_core_Management_Shutdown_HeaderRec HeaderRec;
};
#define urn_jaus_jss_core_Management_Standby_MessageID int
typedef urn_jaus_jss_core_Management_Standby_HeaderRec{
	int MessageID;
};


typedef urn_jaus_jss_core_Management_Standby_MsgHeader{
	urn_jaus_jss_core_Management_Standby_HeaderRec HeaderRec;
};

/**
*  This message is used to transition the receiving component to the standby state.
*/
typedef urn_jaus_jss_core_Management_Standby{
	urn_jaus_jss_core_Management_Standby_HeaderRec HeaderRec;
};
#define urn_jaus_jss_core_Management_ReportStatus_MessageID int
typedef urn_jaus_jss_core_Management_ReportStatus_HeaderRec{
	int MessageID;
};


typedef urn_jaus_jss_core_Management_ReportStatus_MsgHeader{
	urn_jaus_jss_core_Management_ReportStatus_HeaderRec HeaderRec;
};

#define urn_jaus_jss_core_Management_ReportStatus_Status mtype
urn_jaus_jss_core_Management_ReportStatus_Status {INITIALIZE, READY, STANDBY, SHUTDOWN, FAILURE, EMERGENCY};
#define urn_jaus_jss_core_Management_ReportStatus_Reserved int
typedef urn_jaus_jss_core_Management_ReportStatus_ReportStatusRec{
	urn_jaus_jss_core_Management_ReportStatus_Status Status;
	int Reserved;
};


/**
*  This message is used to report the current status of the sender of the message.
*/
typedef urn_jaus_jss_core_Management_ReportStatus{
	urn_jaus_jss_core_Management_ReportStatus_HeaderRec HeaderRec;
	urn_jaus_jss_core_Management_ReportStatus_ReportStatusRec ReportStatusRec;
};
