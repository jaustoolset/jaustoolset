/** 
 *  This Promela file was auto-generated using JTS on Wed Apr 13 15:57:50 EDT 2011.
 *  ID=urn:jaus:jss:core:MessageSet:BasicTypes  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

//  A two byte field to hold the message ID of a message
#define urn_jaus_jss_core_MessageSet_BasicTypes_MessageID int
typedef urn_jaus_jss_core_MessageSet_BasicTypes_HeaderRec{
	//  A two byte field to hold the message ID of a message
	int MessageID;
};


typedef urn_jaus_jss_core_MessageSet_BasicTypes_JAUSMessageHeader{
	urn_jaus_jss_core_MessageSet_BasicTypes_HeaderRec HeaderRec;
};

#define urn_jaus_jss_core_MessageSet_BasicTypes_AuthorityCode byte
#define urn_jaus_jss_core_MessageSet_BasicTypes_EmergencyCode mtype
urn_jaus_jss_core_MessageSet_BasicTypes_EmergencyCode {STOP};
// separate types defined for subfields of BitField : urn_jaus_jss_core_MessageSet_BasicTypes_TimeStamp
typedef urn_jaus_jss_core_MessageSet_BasicTypes_TimeStamp{

	short Milliseconds;

	byte Seconds;

	byte Minutes;

	byte Hour;

	byte Day;

};


// separate types defined for subfields of BitField : urn_jaus_jss_core_MessageSet_BasicTypes_DateStamp
typedef urn_jaus_jss_core_MessageSet_BasicTypes_DateStamp{

	byte Day;

	byte Month;

	byte Year;

};


typedef urn_jaus_jss_core_MessageSet_BasicTypes_TimeRec{
	urn_jaus_jss_core_MessageSet_BasicTypes_TimeStamp TimeStamp;
	urn_jaus_jss_core_MessageSet_BasicTypes_DateStamp DateStamp;
};


// separate types defined for subfields of BitField : urn_jaus_jss_core_MessageSet_BasicTypes_JausID
//  Identifier of a JAUS compliant end point
typedef urn_jaus_jss_core_MessageSet_BasicTypes_JausID{

//  Component ID where a value of 255 represents all components.
	byte ComponentID;

//  Node ID where a value of 255 represents all nodes.
	byte NodeID;

//  Subsystem ID, where a value of 65535 represents all subsystems
	short SubsystemID;

};


#define urn_jaus_jss_core_MessageSet_BasicTypes_ElementUID int
typedef urn_jaus_jss_core_MessageSet_BasicTypes_ElementRec{
	//  Values zero (0) and 65535 are invalid (reserved).
	urn_jaus_jss_core_MessageSet_BasicTypes_ElementUID ElementUID;
	
	//  UID of the previous (parent) element in the list. The value is zero (0) if this is the first (head) element.
	urn_jaus_jss_core_MessageSet_BasicTypes_ElementUID PreviousUID;
	
	//  UID of the next (child) element in the list. The value is zero (0) if this is the last (tail) element.
	urn_jaus_jss_core_MessageSet_BasicTypes_ElementUID NextUID;
	
};


