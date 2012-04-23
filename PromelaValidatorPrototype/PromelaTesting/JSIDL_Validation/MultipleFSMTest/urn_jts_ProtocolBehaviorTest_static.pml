/** 
 *  This Promela file was auto-generated using JTS on Wed Apr 13 16:18:34 EDT 2011.
 *  ID=urn.jts.ProtocolBehaviorTest  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

#define urn_jts_ProtocolBehaviorTest_Message1_MessageID int
typedef urn_jts_ProtocolBehaviorTest_Message1_DefaultHeaderRec{
	int MessageID;
};


typedef urn_jts_ProtocolBehaviorTest_Message1_JTS_DefaultHeader{
	urn_jts_ProtocolBehaviorTest_Message1_DefaultHeaderRec DefaultHeaderRec;
};

typedef urn_jts_ProtocolBehaviorTest_Message1{
	urn_jts_ProtocolBehaviorTest_Message1_DefaultHeaderRec DefaultHeaderRec;
};
#define urn_jts_ProtocolBehaviorTest_UnsignedTestMsg_MessageID int
typedef urn_jts_ProtocolBehaviorTest_UnsignedTestMsg_DefaultHeaderRec{
	int MessageID;
};


typedef urn_jts_ProtocolBehaviorTest_UnsignedTestMsg_JTS_DefaultHeader{
	urn_jts_ProtocolBehaviorTest_UnsignedTestMsg_DefaultHeaderRec DefaultHeaderRec;
};

#define urn_jts_ProtocolBehaviorTest_UnsignedTestMsg_UnsignedByte int
#define urn_jts_ProtocolBehaviorTest_UnsignedTestMsg_UnsignedShort int
#define urn_jts_ProtocolBehaviorTest_UnsignedTestMsg_UnsignedInt int
#define urn_jts_ProtocolBehaviorTest_UnsignedTestMsg_UnsignedLong int
typedef urn_jts_ProtocolBehaviorTest_UnsignedTestMsg_UnsignedRec{
	int UnsignedByte;
	int UnsignedShort;
	int UnsignedInt;
	int UnsignedLong;
};


typedef urn_jts_ProtocolBehaviorTest_UnsignedTestMsg{
	urn_jts_ProtocolBehaviorTest_UnsignedTestMsg_DefaultHeaderRec DefaultHeaderRec;
	urn_jts_ProtocolBehaviorTest_UnsignedTestMsg_UnsignedRec UnsignedRec;
};
#define urn_jts_ProtocolBehaviorTest_UnsignedResponseMessage_MessageID int
typedef urn_jts_ProtocolBehaviorTest_UnsignedResponseMessage_DefaultHeaderRec{
	int MessageID;
};


typedef urn_jts_ProtocolBehaviorTest_UnsignedResponseMessage_JTS_DefaultHeader{
	urn_jts_ProtocolBehaviorTest_UnsignedResponseMessage_DefaultHeaderRec DefaultHeaderRec;
};

#define urn_jts_ProtocolBehaviorTest_UnsignedResponseMessage_UnsignedByte int
#define urn_jts_ProtocolBehaviorTest_UnsignedResponseMessage_UnsignedShort int
#define urn_jts_ProtocolBehaviorTest_UnsignedResponseMessage_UnsignedInt int
#define urn_jts_ProtocolBehaviorTest_UnsignedResponseMessage_UnsignedLong int
typedef urn_jts_ProtocolBehaviorTest_UnsignedResponseMessage_UnsignedRec{
	int UnsignedByte;
	int UnsignedShort;
	int UnsignedInt;
	int UnsignedLong;
};


typedef urn_jts_ProtocolBehaviorTest_UnsignedResponseMessage{
	urn_jts_ProtocolBehaviorTest_UnsignedResponseMessage_DefaultHeaderRec DefaultHeaderRec;
	urn_jts_ProtocolBehaviorTest_UnsignedResponseMessage_UnsignedRec UnsignedRec;
};
