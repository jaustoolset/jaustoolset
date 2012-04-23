/** 
 *  This Promela file was auto-generated using JTS on Wed Apr 13 15:58:22 EDT 2011.
 *  ID=urn.jts.EnumTest  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

#define urn_jts_EnumTest_EnumTestMessage_MessageID int
typedef urn_jts_EnumTest_EnumTestMessage_DefaultHeaderRec{
	int MessageID;
};


typedef urn_jts_EnumTest_EnumTestMessage_JTS_DefaultHeader{
	urn_jts_EnumTest_EnumTestMessage_DefaultHeaderRec DefaultHeaderRec;
};

#define urn_jts_EnumTest_EnumTestMessage_EnumField mtype
urn_jts_EnumTest_EnumTestMessage_EnumField {enum, enumwithspaces};
typedef urn_jts_EnumTest_EnumTestMessage_EnumRec{
	urn_jts_EnumTest_EnumTestMessage_EnumField EnumField;
};


typedef urn_jts_EnumTest_EnumTestMessage{
	urn_jts_EnumTest_EnumTestMessage_DefaultHeaderRec DefaultHeaderRec;
	urn_jts_EnumTest_EnumTestMessage_EnumRec EnumRec;
};
