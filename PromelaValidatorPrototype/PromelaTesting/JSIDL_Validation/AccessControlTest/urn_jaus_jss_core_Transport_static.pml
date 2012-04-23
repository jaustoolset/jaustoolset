/** 
 *  This Promela file was auto-generated using JTS on Wed May 18 13:42:57 EDT 2011.
 *  ID=urn:jaus:jss:core:Transport  vsersion=null
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

#define urn_jaus_jss_core_Transport_ReliableDelivery mtype
urn_jaus_jss_core_Transport_ReliableDelivery {Unreliable, Reliable};
#define urn_jaus_jss_core_Transport_Priority mtype
urn_jaus_jss_core_Transport_Priority {LOW, NORMAL, HIGH, SAFETY};
typedef urn_jaus_jss_core_Transport_SendRec{
	urn_jaus_jss_core_Transport_ReliableDelivery ReliableDelivery;
	//  Identifier of destination to send the message
	urn_jaus_jss_core_MessageSet_BasicTypes_JausID DestinationID;
	//  Identifier of the sender of the message
	urn_jaus_jss_core_MessageSet_BasicTypes_JausID SourceID;
	//  Priority level of this message. If not specified, the normal priority level is used.
	urn_jaus_jss_core_Transport_Priority Priority;
};


#define urn_jaus_jss_core_Transport_Priority mtype
typedef urn_jaus_jss_core_Transport_BroadcastRec{
	//  Identifier of destination to send the message
	urn_jaus_jss_core_MessageSet_BasicTypes_JausID DestinationID;
	//  Identifier of the sender of the message
	urn_jaus_jss_core_MessageSet_BasicTypes_JausID SourceID;
	//  Priority level of this message. If not specified, the normal priority level is used.
	urn_jaus_jss_core_Transport_Priority Priority;
};


