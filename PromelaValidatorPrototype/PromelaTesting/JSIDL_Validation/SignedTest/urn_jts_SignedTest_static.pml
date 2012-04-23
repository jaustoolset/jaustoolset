/** 
 *  This Promela file was auto-generated using JTS on Wed Apr 13 15:56:34 EDT 2011.
 *  ID=urn.jts.SignedTest  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

#define urn_jts_SignedTest_SignedTestMessage_MessageID int
typedef urn_jts_SignedTest_SignedTestMessage_DefaultHeaderRec{
	int MessageID;
};


typedef urn_jts_SignedTest_SignedTestMessage_JTS_DefaultHeader{
	urn_jts_SignedTest_SignedTestMessage_DefaultHeaderRec DefaultHeaderRec;
};

#define urn_jts_SignedTest_SignedTestMessage_SignedInt int
#define urn_jts_SignedTest_SignedTestMessage_SignedShort int
#define urn_jts_SignedTest_SignedTestMessage_SignedLong int
#define urn_jts_SignedTest_SignedTestMessage_SignedByte int
typedef urn_jts_SignedTest_SignedTestMessage_SignedRec{
	int SignedInt;
	int SignedShort;
	int SignedLong;
	int SignedByte;
};


typedef urn_jts_SignedTest_SignedTestMessage{
	urn_jts_SignedTest_SignedTestMessage_DefaultHeaderRec DefaultHeaderRec;
	urn_jts_SignedTest_SignedTestMessage_SignedRec SignedRec;
};
