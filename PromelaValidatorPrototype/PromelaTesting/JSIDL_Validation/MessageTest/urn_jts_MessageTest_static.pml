/** 
 *  This Promela file was auto-generated using JTS on Wed Apr 13 15:57:03 EDT 2011.
 *  ID=urn.jts.MessageTest  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

#define urn_jts_MessageTest_UnsignedTestMsg_MessageID int
typedef urn_jts_MessageTest_UnsignedTestMsg_DefaultHeaderRec{
	int MessageID;
};


typedef urn_jts_MessageTest_UnsignedTestMsg_JTS_DefaultHeader{
	urn_jts_MessageTest_UnsignedTestMsg_DefaultHeaderRec DefaultHeaderRec;
};

#define urn_jts_MessageTest_UnsignedTestMsg_UnsignedByte int
#define urn_jts_MessageTest_UnsignedTestMsg_UnsignedShort int
#define urn_jts_MessageTest_UnsignedTestMsg_UnsignedInt int
#define urn_jts_MessageTest_UnsignedTestMsg_UnsignedLong int
typedef urn_jts_MessageTest_UnsignedTestMsg_UnsignedRec{
	int UnsignedByte;
	int UnsignedShort;
	int UnsignedInt;
	int UnsignedLong;
};


typedef urn_jts_MessageTest_UnsignedTestMsg{
	urn_jts_MessageTest_UnsignedTestMsg_DefaultHeaderRec DefaultHeaderRec;
	urn_jts_MessageTest_UnsignedTestMsg_UnsignedRec UnsignedRec;
};
#define urn_jts_MessageTest_UnsignedResponseMessage_MessageID int
typedef urn_jts_MessageTest_UnsignedResponseMessage_DefaultHeaderRec{
	int MessageID;
};


typedef urn_jts_MessageTest_UnsignedResponseMessage_JTS_DefaultHeader{
	urn_jts_MessageTest_UnsignedResponseMessage_DefaultHeaderRec DefaultHeaderRec;
};

#define urn_jts_MessageTest_UnsignedResponseMessage_UnsignedByte int
#define urn_jts_MessageTest_UnsignedResponseMessage_UnsignedShort int
#define urn_jts_MessageTest_UnsignedResponseMessage_UnsignedInt int
#define urn_jts_MessageTest_UnsignedResponseMessage_UnsignedLong int
typedef urn_jts_MessageTest_UnsignedResponseMessage_UnsignedRec{
	int UnsignedByte;
	int UnsignedShort;
	int UnsignedInt;
	int UnsignedLong;
};


typedef urn_jts_MessageTest_UnsignedResponseMessage{
	urn_jts_MessageTest_UnsignedResponseMessage_DefaultHeaderRec DefaultHeaderRec;
	urn_jts_MessageTest_UnsignedResponseMessage_UnsignedRec UnsignedRec;
};
