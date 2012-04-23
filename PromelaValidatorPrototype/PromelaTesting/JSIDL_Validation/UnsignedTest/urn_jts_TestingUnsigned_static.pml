/** 
 *  This Promela file was auto-generated using JTS on Wed Apr 13 16:00:32 EDT 2011.
 *  ID=urn.jts.TestingUnsigned  vsersion=1.1
 *  DO NOT MODIFY
 *  This file contains type definitions found in the JSIDL that was used to generate it.
 */ 

#define urn_jts_TestingUnsigned_UnsignedTestMsg_MessageID int
typedef urn_jts_TestingUnsigned_UnsignedTestMsg_DefaultHeaderRec{
	int MessageID;
};


typedef urn_jts_TestingUnsigned_UnsignedTestMsg_JTS_DefaultHeader{
	urn_jts_TestingUnsigned_UnsignedTestMsg_DefaultHeaderRec DefaultHeaderRec;
};

#define urn_jts_TestingUnsigned_UnsignedTestMsg_UnsignedByte int
#define urn_jts_TestingUnsigned_UnsignedTestMsg_UnsignedShort int
#define urn_jts_TestingUnsigned_UnsignedTestMsg_UnsignedInt int
#define urn_jts_TestingUnsigned_UnsignedTestMsg_UnsignedLong int
typedef urn_jts_TestingUnsigned_UnsignedTestMsg_UnsignedRec{
	int UnsignedByte;
	int UnsignedShort;
	int UnsignedInt;
	int UnsignedLong;
};


typedef urn_jts_TestingUnsigned_UnsignedTestMsg{
	urn_jts_TestingUnsigned_UnsignedTestMsg_DefaultHeaderRec DefaultHeaderRec;
	urn_jts_TestingUnsigned_UnsignedTestMsg_UnsignedRec UnsignedRec;
};
