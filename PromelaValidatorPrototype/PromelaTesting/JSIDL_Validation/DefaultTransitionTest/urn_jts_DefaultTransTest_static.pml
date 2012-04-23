/** 
 *  This Promela file was auto-generated using JTS on Thu Apr 14 11:49:18 EDT 2011.
 *  ID=urn.jts.DefaultTransTest  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

#define urn_jts_DefaultTransTest_Message1_MessageID int
typedef urn_jts_DefaultTransTest_Message1_DefaultHeaderRec{
	int MessageID;
};


typedef urn_jts_DefaultTransTest_Message1_JTS_DefaultHeader{
	urn_jts_DefaultTransTest_Message1_DefaultHeaderRec DefaultHeaderRec;
};

typedef urn_jts_DefaultTransTest_Message1{
	urn_jts_DefaultTransTest_Message1_DefaultHeaderRec DefaultHeaderRec;
};
#define urn_jts_DefaultTransTest_Message2_MessageID int
typedef urn_jts_DefaultTransTest_Message2_DefaultHeaderRec{
	int MessageID;
};


typedef urn_jts_DefaultTransTest_Message2_JTS_DefaultHeader{
	urn_jts_DefaultTransTest_Message2_DefaultHeaderRec DefaultHeaderRec;
};

typedef urn_jts_DefaultTransTest_Message2{
	urn_jts_DefaultTransTest_Message2_DefaultHeaderRec DefaultHeaderRec;
};
#define urn_jts_DefaultTransTest_Message3_MessageID int
typedef urn_jts_DefaultTransTest_Message3_DefaultHeaderRec{
	int MessageID;
};


typedef urn_jts_DefaultTransTest_Message3_JTS_DefaultHeader{
	urn_jts_DefaultTransTest_Message3_DefaultHeaderRec DefaultHeaderRec;
};

typedef urn_jts_DefaultTransTest_Message3{
	urn_jts_DefaultTransTest_Message3_DefaultHeaderRec DefaultHeaderRec;
};
#define urn_jts_DefaultTransTest_DefaultTransMsg_MessageID int
typedef urn_jts_DefaultTransTest_DefaultTransMsg_DefaultHeaderRec{
	int MessageID;
};


typedef urn_jts_DefaultTransTest_DefaultTransMsg_JTS_DefaultHeader{
	urn_jts_DefaultTransTest_DefaultTransMsg_DefaultHeaderRec DefaultHeaderRec;
};

#define urn_jts_DefaultTransTest_DefaultTransMsg_intField int
typedef urn_jts_DefaultTransTest_DefaultTransMsg_GuardRec{
	int intField;
};


typedef urn_jts_DefaultTransTest_DefaultTransMsg{
	urn_jts_DefaultTransTest_DefaultTransMsg_DefaultHeaderRec DefaultHeaderRec;
	urn_jts_DefaultTransTest_DefaultTransMsg_GuardRec GuardRec;
};
