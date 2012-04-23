/** 
 *  This Promela file was auto-generated using JTS on Thu Apr 14 11:45:14 EDT 2011.
 *  ID=urn.jts.TransitionTest  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

#define urn_jts_TransitionTest_Message1_MessageID int
typedef urn_jts_TransitionTest_Message1_DefaultHeaderRec{
	int MessageID;
};


typedef urn_jts_TransitionTest_Message1_JTS_DefaultHeader{
	urn_jts_TransitionTest_Message1_DefaultHeaderRec DefaultHeaderRec;
};

typedef urn_jts_TransitionTest_Message1{
	urn_jts_TransitionTest_Message1_DefaultHeaderRec DefaultHeaderRec;
};
#define urn_jts_TransitionTest_Message2_MessageID int
typedef urn_jts_TransitionTest_Message2_DefaultHeaderRec{
	int MessageID;
};


typedef urn_jts_TransitionTest_Message2_JTS_DefaultHeader{
	urn_jts_TransitionTest_Message2_DefaultHeaderRec DefaultHeaderRec;
};

typedef urn_jts_TransitionTest_Message2{
	urn_jts_TransitionTest_Message2_DefaultHeaderRec DefaultHeaderRec;
};
#define urn_jts_TransitionTest_Message3_MessageID int
typedef urn_jts_TransitionTest_Message3_DefaultHeaderRec{
	int MessageID;
};


typedef urn_jts_TransitionTest_Message3_JTS_DefaultHeader{
	urn_jts_TransitionTest_Message3_DefaultHeaderRec DefaultHeaderRec;
};

typedef urn_jts_TransitionTest_Message3{
	urn_jts_TransitionTest_Message3_DefaultHeaderRec DefaultHeaderRec;
};
#define urn_jts_TransitionTest_PushMessage_MessageID int
typedef urn_jts_TransitionTest_PushMessage_DefaultHeaderRec{
	int MessageID;
};


typedef urn_jts_TransitionTest_PushMessage_JTS_DefaultHeader{
	urn_jts_TransitionTest_PushMessage_DefaultHeaderRec DefaultHeaderRec;
};

typedef urn_jts_TransitionTest_PushMessage{
	urn_jts_TransitionTest_PushMessage_DefaultHeaderRec DefaultHeaderRec;
};
#define urn_jts_TransitionTest_PopMessage_MessageID int
typedef urn_jts_TransitionTest_PopMessage_DefaultHeaderRec{
	int MessageID;
};


typedef urn_jts_TransitionTest_PopMessage_JTS_DefaultHeader{
	urn_jts_TransitionTest_PopMessage_DefaultHeaderRec DefaultHeaderRec;
};

typedef urn_jts_TransitionTest_PopMessage{
	urn_jts_TransitionTest_PopMessage_DefaultHeaderRec DefaultHeaderRec;
};
#define urn_jts_TransitionTest_ExitDefaultStateMessage_MessageID int
typedef urn_jts_TransitionTest_ExitDefaultStateMessage_DefaultHeaderRec{
	int MessageID;
};


typedef urn_jts_TransitionTest_ExitDefaultStateMessage_JTS_DefaultHeader{
	urn_jts_TransitionTest_ExitDefaultStateMessage_DefaultHeaderRec DefaultHeaderRec;
};

#define urn_jts_TransitionTest_ExitDefaultStateMessage_SignedInt int
#define urn_jts_TransitionTest_ExitDefaultStateMessage_SignedShort int
#define urn_jts_TransitionTest_ExitDefaultStateMessage_SignedLong int
#define urn_jts_TransitionTest_ExitDefaultStateMessage_SignedByte int
typedef urn_jts_TransitionTest_ExitDefaultStateMessage_SignedRec{
	int SignedInt;
	int SignedShort;
	int SignedLong;
	int SignedByte;
};


typedef urn_jts_TransitionTest_ExitDefaultStateMessage{
	urn_jts_TransitionTest_ExitDefaultStateMessage_DefaultHeaderRec DefaultHeaderRec;
	urn_jts_TransitionTest_ExitDefaultStateMessage_SignedRec SignedRec;
};
