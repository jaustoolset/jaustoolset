/** 
 *  This Promela file was auto-generated using JTS on Mon Jun 13 16:10:57 EDT 2011.
 *  ID=urn:jaus:jss:core:AccessControl  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

#define urn_jaus_jss_core_AccessControl_RequestControl_MessageID int
typedef urn_jaus_jss_core_AccessControl_RequestControl_HeaderRec{
	int MessageID;
};


typedef urn_jaus_jss_core_AccessControl_RequestControl_MsgHeader{
	urn_jaus_jss_core_AccessControl_RequestControl_HeaderRec HeaderRec;
};

#define urn_jaus_jss_core_AccessControl_RequestControl_AuthorityCode byte
typedef urn_jaus_jss_core_AccessControl_RequestControl_RequestControlRec{
	urn_jaus_jss_core_AccessControl_RequestControl_AuthorityCode AuthorityCode;
};


/**
*  This message is used to request interruptible control of the receiving component. Once control
*  is established, the receiving component shall only execute commands from the sending component.
*  The authority code parameter is to be set equal to that of the sending component. The receiving component
*  must always accept the control of the highest authority component that is requesting uninterruptible
*  control. Commands from all other components are ignored unless from a component with higher authority.
*/
typedef urn_jaus_jss_core_AccessControl_RequestControl{
	urn_jaus_jss_core_AccessControl_RequestControl_HeaderRec HeaderRec;
	urn_jaus_jss_core_AccessControl_RequestControl_RequestControlRec RequestControlRec;
};
#define urn_jaus_jss_core_AccessControl_ReleaseControl_MessageID int
typedef urn_jaus_jss_core_AccessControl_ReleaseControl_HeaderRec{
	int MessageID;
};


typedef urn_jaus_jss_core_AccessControl_ReleaseControl_MsgHeader{
	urn_jaus_jss_core_AccessControl_ReleaseControl_HeaderRec HeaderRec;
};

/**
*  This message is used to relinquish uninterruptible control of the receiving component.
*/
typedef urn_jaus_jss_core_AccessControl_ReleaseControl{
	urn_jaus_jss_core_AccessControl_ReleaseControl_HeaderRec HeaderRec;
};
#define urn_jaus_jss_core_AccessControl_QueryControl_MessageID int
typedef urn_jaus_jss_core_AccessControl_QueryControl_HeaderRec{
	int MessageID;
};


typedef urn_jaus_jss_core_AccessControl_QueryControl_MsgHeader{
	urn_jaus_jss_core_AccessControl_QueryControl_HeaderRec HeaderRec;
};

/**
*  This message is used by clients to query the current control state of this service.
*/
typedef urn_jaus_jss_core_AccessControl_QueryControl{
	urn_jaus_jss_core_AccessControl_QueryControl_HeaderRec HeaderRec;
};
#define urn_jaus_jss_core_AccessControl_QueryAuthority_MessageID int
typedef urn_jaus_jss_core_AccessControl_QueryAuthority_HeaderRec{
	int MessageID;
};


typedef urn_jaus_jss_core_AccessControl_QueryAuthority_MsgHeader{
	urn_jaus_jss_core_AccessControl_QueryAuthority_HeaderRec HeaderRec;
};

/**
*  This message is used by clients to query the current value of the authority code of this service.
*/
typedef urn_jaus_jss_core_AccessControl_QueryAuthority{
	urn_jaus_jss_core_AccessControl_QueryAuthority_HeaderRec HeaderRec;
};
#define urn_jaus_jss_core_AccessControl_SetAuthority_MessageID int
typedef urn_jaus_jss_core_AccessControl_SetAuthority_HeaderRec{
	int MessageID;
};


typedef urn_jaus_jss_core_AccessControl_SetAuthority_MsgHeader{
	urn_jaus_jss_core_AccessControl_SetAuthority_HeaderRec HeaderRec;
};

#define urn_jaus_jss_core_AccessControl_SetAuthority_AuthorityCode byte
typedef urn_jaus_jss_core_AccessControl_SetAuthority_authorityRec{
	urn_jaus_jss_core_AccessControl_SetAuthority_AuthorityCode AuthorityCode;
};


/**
*  This message shall set the command authority of the receiving component. The authority bits range
*  in value from 0 to 255 with 255 being the highest.
*/
typedef urn_jaus_jss_core_AccessControl_SetAuthority{
	urn_jaus_jss_core_AccessControl_SetAuthority_HeaderRec HeaderRec;
	urn_jaus_jss_core_AccessControl_SetAuthority_authorityRec authorityRec;
};
#define urn_jaus_jss_core_AccessControl_QueryTimeout_MessageID int
typedef urn_jaus_jss_core_AccessControl_QueryTimeout_HeaderRec{
	int MessageID;
};


typedef urn_jaus_jss_core_AccessControl_QueryTimeout_MsgHeader{
	urn_jaus_jss_core_AccessControl_QueryTimeout_HeaderRec HeaderRec;
};

/**
*  This message is used by clients of this service to query the timeout period of this service.
*/
typedef urn_jaus_jss_core_AccessControl_QueryTimeout{
	urn_jaus_jss_core_AccessControl_QueryTimeout_HeaderRec HeaderRec;
};
#define urn_jaus_jss_core_AccessControl_ReportControl_MessageID int
typedef urn_jaus_jss_core_AccessControl_ReportControl_HeaderRec{
	int MessageID;
};


typedef urn_jaus_jss_core_AccessControl_ReportControl_MsgHeader{
	urn_jaus_jss_core_AccessControl_ReportControl_HeaderRec HeaderRec;
};

#define urn_jaus_jss_core_AccessControl_ReportControl_SubsystemID int
#define urn_jaus_jss_core_AccessControl_ReportControl_NodeID int
#define urn_jaus_jss_core_AccessControl_ReportControl_ComponentID int
#define urn_jaus_jss_core_AccessControl_ReportControl_AuthorityCode byte
typedef urn_jaus_jss_core_AccessControl_ReportControl_ReportControlRec{
	int SubsystemID;
	int NodeID;
	int ComponentID;
	urn_jaus_jss_core_AccessControl_ReportControl_AuthorityCode AuthorityCode;
};


/**
*  This message is used to report the current state of control of this service. If the service is in the
*  Controlled state, this message reports the ID of the controlling component. The ID fields shall
*  be set to zero (0) if this service is in the NotControlled state..
*/
typedef urn_jaus_jss_core_AccessControl_ReportControl{
	urn_jaus_jss_core_AccessControl_ReportControl_HeaderRec HeaderRec;
	urn_jaus_jss_core_AccessControl_ReportControl_ReportControlRec ReportControlRec;
};
#define urn_jaus_jss_core_AccessControl_RejectControl_MessageID int
typedef urn_jaus_jss_core_AccessControl_RejectControl_HeaderRec{
	int MessageID;
};


typedef urn_jaus_jss_core_AccessControl_RejectControl_MsgHeader{
	urn_jaus_jss_core_AccessControl_RejectControl_HeaderRec HeaderRec;
};

#define urn_jaus_jss_core_AccessControl_RejectControl_ResponseCode mtype
urn_jaus_jss_core_AccessControl_RejectControl_ResponseCode {CONTROL_RELEASED, NOT_AVAILABLE};
typedef urn_jaus_jss_core_AccessControl_RejectControl_RejectControlRec{
	urn_jaus_jss_core_AccessControl_RejectControl_ResponseCode ResponseCode;
};


/**
*  The Reject Control message is used to notify a component that control has been released (response
*  code = 0), or a request to release control could not be processed (response code = 1).
*/
typedef urn_jaus_jss_core_AccessControl_RejectControl{
	urn_jaus_jss_core_AccessControl_RejectControl_HeaderRec HeaderRec;
	urn_jaus_jss_core_AccessControl_RejectControl_RejectControlRec RejectControlRec;
};
#define urn_jaus_jss_core_AccessControl_ConfirmControl_MessageID int
typedef urn_jaus_jss_core_AccessControl_ConfirmControl_HeaderRec{
	int MessageID;
};


typedef urn_jaus_jss_core_AccessControl_ConfirmControl_MsgHeader{
	urn_jaus_jss_core_AccessControl_ConfirmControl_HeaderRec HeaderRec;
};

#define urn_jaus_jss_core_AccessControl_ConfirmControl_ResponseCode mtype
urn_jaus_jss_core_AccessControl_ConfirmControl_ResponseCode {CONTROL_ACCEPTED, INSUFFICIENT_AUTHORITY};
typedef urn_jaus_jss_core_AccessControl_ConfirmControl_ConfirmControlRec{
	urn_jaus_jss_core_AccessControl_ConfirmControl_ResponseCode ResponseCode;
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
typedef urn_jaus_jss_core_AccessControl_ConfirmControl{
	urn_jaus_jss_core_AccessControl_ConfirmControl_HeaderRec HeaderRec;
	urn_jaus_jss_core_AccessControl_ConfirmControl_ConfirmControlRec ConfirmControlRec;
};
#define urn_jaus_jss_core_AccessControl_ReportAuthority_MessageID int
typedef urn_jaus_jss_core_AccessControl_ReportAuthority_HeaderRec{
	int MessageID;
};


typedef urn_jaus_jss_core_AccessControl_ReportAuthority_MsgHeader{
	urn_jaus_jss_core_AccessControl_ReportAuthority_HeaderRec HeaderRec;
};

#define urn_jaus_jss_core_AccessControl_ReportAuthority_AuthorityCode byte
typedef urn_jaus_jss_core_AccessControl_ReportAuthority_ReportAuthorityRec{
	urn_jaus_jss_core_AccessControl_ReportAuthority_AuthorityCode AuthorityCode;
};


/**
*  This message is used to report the current command authority.
*/
typedef urn_jaus_jss_core_AccessControl_ReportAuthority{
	urn_jaus_jss_core_AccessControl_ReportAuthority_HeaderRec HeaderRec;
	urn_jaus_jss_core_AccessControl_ReportAuthority_ReportAuthorityRec ReportAuthorityRec;
};
#define urn_jaus_jss_core_AccessControl_ReportTimeout_MessageID int
typedef urn_jaus_jss_core_AccessControl_ReportTimeout_HeaderRec{
	int MessageID;
};


typedef urn_jaus_jss_core_AccessControl_ReportTimeout_MsgHeader{
	urn_jaus_jss_core_AccessControl_ReportTimeout_HeaderRec HeaderRec;
};

#define urn_jaus_jss_core_AccessControl_ReportTimeout_Timeout mtype
typedef urn_jaus_jss_core_AccessControl_ReportTimeout_ReportTimoutRec{
	urn_jaus_jss_core_AccessControl_ReportTimeout_Timeout Timeout;
};


/**
*  This message is used to report the timeout period of this message.
*/
typedef urn_jaus_jss_core_AccessControl_ReportTimeout{
	urn_jaus_jss_core_AccessControl_ReportTimeout_HeaderRec HeaderRec;
	urn_jaus_jss_core_AccessControl_ReportTimeout_ReportTimoutRec ReportTimoutRec;
};
