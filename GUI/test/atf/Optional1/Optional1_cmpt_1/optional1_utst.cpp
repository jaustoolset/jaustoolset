/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2010, United States Government
All rights reserved.

Redistribution and use in source and binary forms, with or without 
modification, are permitted provided that the following conditions are met:

       Redistributions of source code must retain the above copyright notice, 
this list of conditions and the following disclaimer.

       Redistributions in binary form must reproduce the above copyright 
notice, this list of conditions and the following disclaimer in the 
documentation and/or other materials provided with the distribution.

       Neither the name of the United States Government nor the names of 
its contributors may be used to endorse or promote products derived from 
this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
POSSIBILITY OF SUCH DAMAGE.
*********************  END OF LICENSE ***********************************/

#include <iostream>

#include "urn_org_jts_test_Optional1_1_0/Messages/MessageSet.h"

#include "cppunit/TestCaller.h"
#include "cppunit/TestCaller.h"
#include "cppunit/ui/text/TestRunner.h"
#include "cppunit/TestSuite.h"

using namespace urn_org_jts_test_Optional1_1_0;

#define OUT_0  std::cout
#define OUT_1  if (bVerbose<=2) std::cout 
#define OUT_2  if (bVerbose<=1) std::cout

unsigned int uintAtOffset(unsigned char* buffer, unsigned int offset) {
    // take address at given offset into buffer, treat pointer as unsigned int pointer, then dereference an
    // unsigned int.
    return *((unsigned int*)&(buffer[offset]));
}

unsigned short ushortAtOffset(unsigned char* buffer, unsigned int offset) {
    // take address at given offset into buffer, treat pointer as unsigned short pointer, then dereference an
    // unsigned short.
    return *((unsigned short*)&(buffer[offset]));
}

unsigned char ucharAtOffset(unsigned char* buffer, unsigned int offset) {
    // take address at given offset into buffer, treat pointer as unsigned char pointer, then dereference an
    // unsigned char.
    return *((unsigned char*)&(buffer[offset]));
}

// assert if the contents of two byte buffers are equal, given first offset into buff one and length following
// that offset.  comparison is against contents of buff2 from offset zero to offset length-1.
void assertBufferSliceEqual(unsigned char* data1, unsigned char* data2, int first, int length) {
    for (int i = 0; i < length; ++i) {
        CPPUNIT_ASSERT(data2[i] == data1[first + i]);
    }
}

void assertConstBuffersEqual(const unsigned char* data1, const unsigned char* data2, int length) {
    for (int i = 0; i < length; ++i) {
        CPPUNIT_ASSERT(data2[i] == data1[i]);
    }
}

void assertBuffersEqual(unsigned char* data1, unsigned char* data2, int length) {
    assertConstBuffersEqual(data1, data2, length);
}

class JTSOptional1MessageTest : public CppUnit::TestFixture {
             
private:
  int bVerbose;
  
public:
  JTSOptional1MessageTest() {
     bVerbose=0;
  }     
       
  void setUp()
  {
  }

  void tearDown() 
  {
  }

  void testSetGetOperations()
  {
	  unsigned char data[10] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
	  OUT_1 << std::endl << "  [executing test (testSetGetOperations)(TP_3.3.5.1)] " << std::endl;

	  // Test initial state
	  MsgIn msgIn1;
	  CPPUNIT_ASSERT( msgIn1.getBody()->getSequence()->getPresenceVector() == 0 );
	  CPPUNIT_ASSERT( msgIn1.getBody()->getSequence()->getRecord1()->getPresenceVector() == 0 );

	  // Set the first entry in the first optional record, then ensure all the PVs got set correctly
	  msgIn1.getBody()->getSequence()->getRecord1()->setField1(1);
	  CPPUNIT_ASSERT( msgIn1.getBody()->getSequence()->getPresenceVector() == 1 );
	  CPPUNIT_ASSERT( msgIn1.getBody()->getSequence()->getRecord1()->getPresenceVector() == 1 );

	  // Set the first entry in the first optional record, then ensure all the PVs got set correctly
	  msgIn1.getBody()->getSequence()->getRecord2()->getVariableLengthField1()->set(10, data);
	  CPPUNIT_ASSERT( msgIn1.getBody()->getSequence()->getPresenceVector() == 3 );
	  CPPUNIT_ASSERT( msgIn1.getBody()->getSequence()->getRecord2()->getPresenceVector() == 2 );

	  // Set the first entry in the first optional record, then ensure all the PVs got set correctly
	  for (int i=0; i<10; i++)
		msgIn1.getBody()->getSequence()->getRecord3()->getArray1()->setElement1(i, data[i]);
	  CPPUNIT_ASSERT( msgIn1.getBody()->getSequence()->getPresenceVector() == 7 );
	  CPPUNIT_ASSERT( msgIn1.getBody()->getSequence()->getRecord3()->getPresenceVector() == 4 );

	  // Set the first entry in the first optional record, then ensure all the PVs got set correctly
	  msgIn1.getBody()->getSequence()->getRecord4()->getBitField1()->setSubfield1(1);
	  CPPUNIT_ASSERT( msgIn1.getBody()->getSequence()->getPresenceVector() == 15 );
	  CPPUNIT_ASSERT( msgIn1.getBody()->getSequence()->getRecord4()->getPresenceVector() == 8 );

	  // Set the first entry in the first optional record, then ensure all the PVs got set correctly
	  msgIn1.getBody()->getSequence()->getRecord5()->setFixedLengthString1("Hello World!");
	  CPPUNIT_ASSERT( msgIn1.getBody()->getSequence()->getPresenceVector() == 31 );
	  CPPUNIT_ASSERT( msgIn1.getBody()->getSequence()->getRecord5()->getPresenceVector() == 16 );

	  // Set the first entry in the first optional record, then ensure all the PVs got set correctly
	  msgIn1.getBody()->getSequence()->getRecord6()->getVariableField1()->setIndex(0);
	  CPPUNIT_ASSERT( msgIn1.getBody()->getSequence()->getPresenceVector() == 63 );
	  CPPUNIT_ASSERT( msgIn1.getBody()->getSequence()->getRecord6()->getPresenceVector() == 32 );

	  // Set the first entry in the first optional record, then ensure all the PVs got set correctly
	  msgIn1.getBody()->getSequence()->getRecord7()->setVariableLengthString1("I'm Gumby, Dammit!");
	  CPPUNIT_ASSERT( msgIn1.getBody()->getSequence()->getPresenceVector() == 127 );
	  CPPUNIT_ASSERT( msgIn1.getBody()->getSequence()->getRecord7()->getPresenceVector() == 64 );

	  // Set the first entry in the first optional record, then ensure all the PVs got set correctly
	  msgIn1.getBody()->getSequence()->getRecord8()->getVariableFormatField1()->set(0, 10, data);
	  CPPUNIT_ASSERT( msgIn1.getBody()->getSequence()->getPresenceVector() == 255 );
	  CPPUNIT_ASSERT( msgIn1.getBody()->getSequence()->getRecord8()->getPresenceVector() == 128 );

	  // Add an element to the optional list
	  MsgIn::Body::Sequence::List::Record rec1;
	  msgIn1.getBody()->getSequence()->getList()->addElement( rec1 );
	  CPPUNIT_ASSERT( msgIn1.getBody()->getSequence()->getPresenceVector() == 511 );
	  CPPUNIT_ASSERT( msgIn1.getBody()->getSequence()->getList()->getElement(0)->getPresenceVector() == 0 );
	  
	  // Nested sequences...
	  msgIn1.getBody()->getSequence()->getSubsequence()->getRecord()->getVariableField1()->setIndex(0);
	  CPPUNIT_ASSERT( msgIn1.getBody()->getSequence()->getPresenceVector() == 1023 );
	  CPPUNIT_ASSERT( msgIn1.getBody()->getSequence()->getSubsequence()->getPresenceVector() == 1 );
	  CPPUNIT_ASSERT( msgIn1.getBody()->getSequence()->getSubsequence()->getRecord()->getPresenceVector() == 32 );

	  // Variants
	  msgIn1.getBody()->getSequence()->getVariant()->setFieldValue(1);
	  CPPUNIT_ASSERT( msgIn1.getBody()->getSequence()->getPresenceVector() == 2047 );
	  CPPUNIT_ASSERT( msgIn1.getBody()->getSequence()->getVariant()->getRecord()->getPresenceVector() == 0 );
  }

    void testEncodeToSpec()
    {
        OUT_1 << std::endl << "[executing test (testEncodeToSpec)(TP_3.3.5.2)]" << std::endl;

        // buffer for encoding
        unsigned char buff[10000];
		for (int i = 0; i < 10000; ++i) 
		{
			buff[i] = 0;
		}

        // *** first test message: activate only Record1, activate all fields inside of record1.
        unsigned char data1[10];
        unsigned char data2[5];

        for (int i = 0; i < 10; ++i)
        {
            data1[i] = 10 + i;
        }
        for (int i = 0; i < 5; ++i)
        {
            data2[i] = 20 + i;
        }

        MsgIn msg1;

        msg1.getHeader()->getHeaderRec()->setMessageID(999);

        msg1.getBody()->getSequence()->getRecord1()->setField1(77);
        msg1.getBody()->getSequence()->getRecord1()->getVariableLengthField1()->set(10, data1);
        msg1.getBody()->getSequence()->getRecord1()->getArray1()->setElement1(1, 10);
        msg1.getBody()->getSequence()->getRecord1()->getArray1()->setElement1(3, 20);
        msg1.getBody()->getSequence()->getRecord1()->getArray1()->setElement1(5, 30);
        msg1.getBody()->getSequence()->getRecord1()->getArray1()->setElement1(7, 40);
        msg1.getBody()->getSequence()->getRecord1()->getArray1()->setElement1(9, 50);
        msg1.getBody()->getSequence()->getRecord1()->getBitField1()->setSubfield1(1);
        msg1.getBody()->getSequence()->getRecord1()->setFixedLengthString1("ABCDE");
        msg1.getBody()->getSequence()->getRecord1()->getVariableField1()->setMeterAsUnsignedByteAt1(33);
        msg1.getBody()->getSequence()->getRecord1()->setVariableLengthString1("hello world");
        msg1.getBody()->getSequence()->getRecord1()->getVariableFormatField1()->set(0, 5, data2);

        msg1.encode(buff);

        // offset 0:    ushort w/ message ID
        CPPUNIT_ASSERT(999 == ushortAtOffset(buff, 0));

        // offset 2:    ushort w/ Sequence's presence vector - should be 1 indicating only first
        // record is present
        CPPUNIT_ASSERT(1 == ushortAtOffset(buff, 2));

        // offset 4: unsigned byte w/ Record1's presence vector, 8 bits all on, 255
        CPPUNIT_ASSERT(255 == buff[4]);

        // offset 5:    signed byte, Field1 fixed field
        CPPUNIT_ASSERT(77 == buff[5]);

        // offset 6:    uint, variableLengthField1 count field...
        CPPUNIT_ASSERT(10 == buff[6]);

        // offset 10-19: 10-byte variableLengthField1.
        assertBufferSliceEqual(buff, data1, 10, 10);

        // offset 20: Array1[0]
        CPPUNIT_ASSERT(0 == buff[20]);

        // offset 21: Array1[1]
        CPPUNIT_ASSERT(10 == buff[21]);

        // offset 22: Array1[2]
        CPPUNIT_ASSERT(0 == buff[22]);

        // offset 23: Array1[3]
        CPPUNIT_ASSERT(20 == buff[23]);

        // offset 25: Array1[5]
        CPPUNIT_ASSERT(30 == buff[25]);

        // offset 27: Array1[7]
        CPPUNIT_ASSERT(40 == buff[27]);

        // offset 29: Array1[9]
        CPPUNIT_ASSERT(50 == buff[29]);

        // offset 30: BitField1, single unsigned byte
        CPPUNIT_ASSERT(1 == buff[30]);

        // offset 31: 15-byte fixed length string.  first 5 chars: "ABCDE"
        CPPUNIT_ASSERT(65 == buff[31]);

        // offset 32: 15-byte fixed length string.  first 5 chars: "ABCDE"
        CPPUNIT_ASSERT(66 == buff[32]);

        // offset 33: 15-byte fixed length string.  first 5 chars: "ABCDE"
        CPPUNIT_ASSERT(67 == buff[33]);

        // offset 34: 15-byte fixed length string.  first 5 chars: "ABCDE"
        CPPUNIT_ASSERT(68 == buff[34]);

        // offset 35: 15-byte fixed length string.  first 5 chars: "ABCDE"
        CPPUNIT_ASSERT(69 == buff[35]);

        // offset 36: zeroed part of fixed length string
        CPPUNIT_ASSERT(0 == buff[36]);

        // offset 45: last zeroed byte of fixed length string
        CPPUNIT_ASSERT(0 == buff[45]);

        // offset 46: type/units enum of VariableField1, single byte
        CPPUNIT_ASSERT(1 == buff[46]);

        // offset 47: contents of VariableField1
        CPPUNIT_ASSERT(33 == buff[47]);

        // offset 48: 2-byte unsigned short, count field for VariableLengthString1
        CPPUNIT_ASSERT(11 == ushortAtOffset(buff, 48));

        // offset 50-60: 11 characters of VariableLengthString1 "hello world"
        CPPUNIT_ASSERT(104 == buff[50]);
        CPPUNIT_ASSERT(101 == buff[51]);
        CPPUNIT_ASSERT(108 == buff[52]);
        CPPUNIT_ASSERT(108 == buff[53]);
        CPPUNIT_ASSERT(111 == buff[54]);
        CPPUNIT_ASSERT(32 == buff[55]);
        CPPUNIT_ASSERT(119 == buff[56]);
        CPPUNIT_ASSERT(111 == buff[57]);
        CPPUNIT_ASSERT(114 == buff[58]);
        CPPUNIT_ASSERT(108 == buff[59]);
        CPPUNIT_ASSERT(100 == buff[60]);

        // offset 61: format field of VariableFormatField1, unsigned byte
        CPPUNIT_ASSERT(0 == buff[61]);

        // offset 62: count field of VariableFormatField1, unsigned int
        CPPUNIT_ASSERT(5 == ushortAtOffset(buff, 62));

        // offset 66-70: 5 byte data of VariableFormatField1
        assertBufferSliceEqual(buff, data2, 66, 5);

        for (int i = 0; i < 10000; ++i) 
		{
			buff[i] = 0;
		}


        // *** 2nd test message, activate Record 2, Record 4, Record 6, Record 8, activate different
        // fields within each record.
        MsgIn msg2;

        msg2.getHeader()->getHeaderRec()->setMessageID(999);

        msg2.getBody()->getSequence()->getRecord2()->setField1(77);
        msg2.getBody()->getSequence()->getRecord2()->getVariableLengthField1()->set(10, data1);
        msg2.getBody()->getSequence()->getRecord4()->getArray1()->setElement1(1, 10);
        msg2.getBody()->getSequence()->getRecord4()->getArray1()->setElement1(3, 20);
        msg2.getBody()->getSequence()->getRecord4()->getArray1()->setElement1(5, 30);
        msg2.getBody()->getSequence()->getRecord4()->getArray1()->setElement1(7, 40);
        msg2.getBody()->getSequence()->getRecord4()->getArray1()->setElement1(9, 50);
        msg2.getBody()->getSequence()->getRecord4()->getBitField1()->setSubfield1(1);
        msg2.getBody()->getSequence()->getRecord6()->setFixedLengthString1("ABCDE");
        msg2.getBody()->getSequence()->getRecord6()->getVariableField1()->setMeterAsUnsignedByteAt1(33);
        msg2.getBody()->getSequence()->getRecord8()->setVariableLengthString1("hello world");
        msg2.getBody()->getSequence()->getRecord8()->getVariableFormatField1()->set(0, 5, data2);

        msg2.encode(buff);

        // offset 0:   ushort w/ msg ID
        CPPUNIT_ASSERT(999 == ushortAtOffset(buff, 0));

        // offset 2:   ushort w/ Sequence pv
        CPPUNIT_ASSERT(170 == ushortAtOffset(buff, 2));

        // offset 4:   ubyte w/ Record2 pv
        CPPUNIT_ASSERT(3 == buff[4]);

        // offset 5:   byte w/ Record2.Field1
        CPPUNIT_ASSERT(77 == buff[5]);

        // offset 6:   uint w/ Record2.VariableLengthField1 count field
        CPPUNIT_ASSERT(10 == buff[6]);

        // offset 10-19: ubyte data of Record2.VariableLengthField1
        assertBufferSliceEqual(buff, data1, 10, 10);

        // offset 20:   ubyte w/ Record4 pv
        CPPUNIT_ASSERT(12 == buff[20]);

        // offset 21:   byte, Record4.Array1[0]
        CPPUNIT_ASSERT(0 == buff[21]);

        // offset 22:   byte, Record4.Array1[1]
        CPPUNIT_ASSERT(10 == buff[22]);

        // offset 23:   byte, Record4.Array1[2]
        CPPUNIT_ASSERT(0 == buff[23]);

        // offset 29:   byte, Record4.Array1[8]
        CPPUNIT_ASSERT(0 == buff[29]);

        // offset 30:   byte, Record4.Array1[9]
        CPPUNIT_ASSERT(50 == buff[30]);

        // offset 31:   byte, Record4.BitField1
        CPPUNIT_ASSERT(1 == buff[31]);

        // offset 32:   ubyte w/ Record6 pv
        CPPUNIT_ASSERT(48 == buff[32]);

        // offset 33: 15-byte Record6.FixedLengthString1.  first 5 chars: "ABCDE"
        CPPUNIT_ASSERT(65 == buff[33]);

        // offset 34: 15-byte fixed length string.  first 5 chars: "ABCDE"
        CPPUNIT_ASSERT(66 == buff[34]);

        // offset 35: 15-byte fixed length string.  first 5 chars: "ABCDE"
        CPPUNIT_ASSERT(67 == buff[35]);

        // offset 36: 15-byte fixed length string.  first 5 chars: "ABCDE"
        CPPUNIT_ASSERT(68 == buff[36]);

        // offset 37: 15-byte fixed length string.  first 5 chars: "ABCDE"
        CPPUNIT_ASSERT(69 == buff[37]);

        // offset 38: zeroed part of fixed length string
        CPPUNIT_ASSERT(0 == buff[38]);

        // offset 47: last zeroed byte of fixed length string
        CPPUNIT_ASSERT(0 == buff[47]);

        // offset 48: type/units enum of VariableField1, single byte
        CPPUNIT_ASSERT(1 == buff[48]);

        // offset 49: byte contents of VariableField1
        CPPUNIT_ASSERT(33 == buff[49]);

        // offset 50:   ubyte w/ Record8 pv
        CPPUNIT_ASSERT(192 == buff[50]);
        
        // offset 51: 2-byte unsigned short, count field for Record8.VariableLengthString1
        CPPUNIT_ASSERT(11 == ushortAtOffset(buff, 51));

        // offset 53-63: 11 characters of Record8.VariableLengthString1 "hello world"
        CPPUNIT_ASSERT(104 == buff[53]);
        CPPUNIT_ASSERT(101 == buff[54]);
        CPPUNIT_ASSERT(108 == buff[55]);
        CPPUNIT_ASSERT(108 == buff[56]);
        CPPUNIT_ASSERT(111 == buff[57]);
        CPPUNIT_ASSERT(32 == buff[58]);
        CPPUNIT_ASSERT(119 == buff[59]);
        CPPUNIT_ASSERT(111 == buff[60]);
        CPPUNIT_ASSERT(114 == buff[61]);
        CPPUNIT_ASSERT(108 == buff[62]);
        CPPUNIT_ASSERT(100 == buff[63]);

        // offset 64: format field of Record8.VariableFormatField1, unsigned byte
        CPPUNIT_ASSERT(0 == buff[64]);

        // offset 65: count field of Record8.VariableFormatField1, unsigned int
        CPPUNIT_ASSERT(5 == ushortAtOffset(buff, 65));

        // offset 69-73: 5 byte data of Record8.VariableFormatField1
        assertBufferSliceEqual(buff, data2, 69, 5);

        for (int i = 0; i < 10000; ++i) 
		{
			buff[i] = 0;
		}

        // 3rd msg: activate record 3, List, and Variant, and activate the record
        // inside List and Variant, activate all fields inside each record.
        MsgIn msg3;

        msg3.getHeader()->getHeaderRec()->setMessageID(999);

        MsgIn::Body::Sequence::Record3& rec3 = *(msg3.getBody()->getSequence()->getRecord3());

        rec3.setField1(77);
        rec3.getVariableLengthField1()->set(10, data1);
        rec3.getArray1()->setElement1(1, 10);
        rec3.getArray1()->setElement1(3, 20);
        rec3.getArray1()->setElement1(5, 30);
        rec3.getArray1()->setElement1(7, 40);
        rec3.getArray1()->setElement1(9, 50);
        rec3.getBitField1()->setSubfield1(1);
        rec3.setFixedLengthString1("ABCDE");
        rec3.getVariableField1()->setMeterAsUnsignedByteAt1(33);
        rec3.setVariableLengthString1("hello world");
        rec3.getVariableFormatField1()->set(0, 5, data2);

	    MsgIn::Body::Sequence::List::Record listrec;

        listrec.setField1(77);
        listrec.getVariableLengthField1()->set(10, data1);
        listrec.getArray1()->setElement1(1, 60);
        listrec.getArray1()->setElement1(3, 70);
        listrec.getArray1()->setElement1(5, 80);
        listrec.getArray1()->setElement1(7, 90);
        listrec.getArray1()->setElement1(9, 100);
        listrec.getBitField1()->setSubfield1(1);
        listrec.setFixedLengthString1("FGHIJK");
        listrec.getVariableField1()->setMeterAsUnsignedByteAt1(44);
        listrec.setVariableLengthString1("aabbccddeeff");
        listrec.getVariableFormatField1()->set(0, 5, data2);

	    msg3.getBody()->getSequence()->getList()->addElement( listrec );


        // Variants
        msg3.getBody()->getSequence()->getVariant()->setFieldValue(1); // option 1: Record
        MsgIn::Body::Sequence::Variant::Record& varrec = *(msg3.getBody()->getSequence()->getVariant()->getRecord());

        varrec.setField1(99);
        varrec.getVariableLengthField1()->set(10, data1);
        varrec.getArray1()->setElement1(1, 65);
        varrec.getArray1()->setElement1(3, 75);
        varrec.getArray1()->setElement1(5, 85);
        varrec.getArray1()->setElement1(7, 95);
        varrec.getArray1()->setElement1(9, 105);
        varrec.getBitField1()->setSubfield1(1);
        varrec.setFixedLengthString1("LMNOPQR");
        varrec.getVariableField1()->setMeterAsUnsignedByteAt1(55);
        varrec.setVariableLengthString1("hello world");
        varrec.getVariableFormatField1()->set(0, 5, data2);

        msg3.encode(buff);

        // offset 0:    ushort w/ message ID
        CPPUNIT_ASSERT(999 == ushortAtOffset(buff, 0));

        // offset 2:    ushort w/ Sequence's presence vector - 1284 indicates record 3, list, variant present
        CPPUNIT_ASSERT(1284 == ushortAtOffset(buff, 2));

        // offset 4: unsigned byte w/ Record3's presence vector, 8 bits all on, 255
        CPPUNIT_ASSERT(255 == buff[4]);

        // offset 5:    signed byte, Record3.Field1 fixed field
        CPPUNIT_ASSERT(77 == buff[5]);

        // offset 6:    uint, Record3.variableLengthField1 count field...
        CPPUNIT_ASSERT(10 == buff[6]);

        // offset 10-19: 10-byte Record3.variableLengthField1.
        assertBufferSliceEqual(buff, data1, 10, 10);

        // offset 20: Record3.Array1[0]
        CPPUNIT_ASSERT(0 == buff[20]);

        // offset 21: Record3.Array1[1]
        CPPUNIT_ASSERT(10 == buff[21]);

        // offset 22: Record3.Array1[2]
        CPPUNIT_ASSERT(0 == buff[22]);

        // offset 23: Record3.Array1[3]
        CPPUNIT_ASSERT(20 == buff[23]);

        // offset 25: Record3.Array1[5]
        CPPUNIT_ASSERT(30 == buff[25]);

        // offset 27: Record3.Array1[7]
        CPPUNIT_ASSERT(40 == buff[27]);

        // offset 29: Record3.Array1[9]
        CPPUNIT_ASSERT(50 == buff[29]);

        // offset 30: Record3.BitField1, single unsigned byte
        CPPUNIT_ASSERT(1 == buff[30]);

        // offset 31: 15-byte fixed length string.  first 5 chars: "ABCDE"
        CPPUNIT_ASSERT(65 == buff[31]);

        // offset 32: 15-byte fixed length string.  first 5 chars: "ABCDE"
        CPPUNIT_ASSERT(66 == buff[32]);

        // offset 33: 15-byte fixed length string.  first 5 chars: "ABCDE"
        CPPUNIT_ASSERT(67 == buff[33]);

        // offset 34: 15-byte fixed length string.  first 5 chars: "ABCDE"
        CPPUNIT_ASSERT(68 == buff[34]);

        // offset 35: 15-byte fixed length string.  first 5 chars: "ABCDE"
        CPPUNIT_ASSERT(69 == buff[35]);

        // offset 36: zeroed part of fixed length string
        CPPUNIT_ASSERT(0 == buff[36]);

        // offset 45: last zeroed byte of fixed length string
        CPPUNIT_ASSERT(0 == buff[45]);

        // offset 46: type/units enum of Record3.VariableField1, single byte
        CPPUNIT_ASSERT(1 == buff[46]);

        // offset 47: contents of Record3.VariableField1
        CPPUNIT_ASSERT(33 == buff[47]);

        // offset 48: 2-byte unsigned short, count field for Record3.VariableLengthString1
        CPPUNIT_ASSERT(11 == ushortAtOffset(buff, 48));

        // offset 50-60: 11 characters of VariableLengthString1 "hello world"
        CPPUNIT_ASSERT(104 == buff[50]);
        CPPUNIT_ASSERT(101 == buff[51]);
        CPPUNIT_ASSERT(108 == buff[52]);
        CPPUNIT_ASSERT(108 == buff[53]);
        CPPUNIT_ASSERT(111 == buff[54]);
        CPPUNIT_ASSERT(32 == buff[55]);
        CPPUNIT_ASSERT(119 == buff[56]);
        CPPUNIT_ASSERT(111 == buff[57]);
        CPPUNIT_ASSERT(114 == buff[58]);
        CPPUNIT_ASSERT(108 == buff[59]);
        CPPUNIT_ASSERT(100 == buff[60]);

        // offset 61: format field of VariableFormatField1, unsigned byte
        CPPUNIT_ASSERT(0 == buff[61]);

        // offset 62: count field of VariableFormatField1, unsigned int
        CPPUNIT_ASSERT(5 == ushortAtOffset(buff, 62));

        // offset 66-70: 5 byte data of VariableFormatField1
        assertBufferSliceEqual(buff, data2, 66, 5);

        // *** list and record inside list ***

        // offset 71: ubyte count field for list == 1
        CPPUNIT_ASSERT(1 == buff[71]);

        // offset 72: ubyte presence vector for List.Record
        CPPUNIT_ASSERT(255 == buff[72]);

        // offset 73:    signed byte, Record3.Field1 fixed field
        CPPUNIT_ASSERT(77 == buff[73]);

        // offset 74:    uint, Record3.variableLengthField1 count field...
        CPPUNIT_ASSERT(10 == buff[74]);

        // offset 78-87: 10-byte Record3.variableLengthField1.
        assertBufferSliceEqual(buff, data1, 78, 10);

        // offset 88: Record3.Array1[0]
        CPPUNIT_ASSERT(0 == buff[88]);

        // offset 89: Record3.Array1[1]
        CPPUNIT_ASSERT(60 == buff[89]);

        // offset 90: Record3.Array1[2]
        CPPUNIT_ASSERT(0 == buff[90]);

        // offset 91: Record3.Array1[3]
        CPPUNIT_ASSERT(70 == buff[91]);

        // offset 93: Record3.Array1[5]
        CPPUNIT_ASSERT(80 == buff[93]);

        // offset 95: Record3.Array1[7]
        CPPUNIT_ASSERT(90 == buff[95]);

        // offset 97: Record3.Array1[9]
        CPPUNIT_ASSERT(100 == buff[97]);

        // offset 98: Record3.BitField1, single unsigned byte
        CPPUNIT_ASSERT(1 == buff[98]);

        // offset 99: 15-byte fixed length string.  first 6 chars: "FGHIJK"
        CPPUNIT_ASSERT(70 == buff[99]);

        // offset 100: 15-byte fixed length string.
        CPPUNIT_ASSERT(71 == buff[100]);

        // offset 101: 15-byte fixed length string.
        CPPUNIT_ASSERT(72 == buff[101]);

        // offset 102: 15-byte fixed length string.
        CPPUNIT_ASSERT(73 == buff[102]);

        // offset 103: 15-byte fixed length string.
        CPPUNIT_ASSERT(74 == buff[103]);

        // offset 104: 15-byte fixed length string.
        CPPUNIT_ASSERT(75 == buff[104]);

        // offset 105: zeroed part of fixed length string
        CPPUNIT_ASSERT(0 == buff[105]);

        // offset 113: last zeroed byte of fixed length string
        CPPUNIT_ASSERT(0 == buff[113]);

        // offset 114: type/units enum of Record3.VariableField1, single byte
        CPPUNIT_ASSERT(1 == buff[114]);

        // offset 115: contents of Record3.VariableField1
        CPPUNIT_ASSERT(44 == buff[115]);

        // offset 116: 2-byte unsigned short, count field for Record3.VariableLengthString1
        CPPUNIT_ASSERT(12 == ushortAtOffset(buff, 116));

        // offset 118-129: 12 characters of VariableLengthString1 "aabbccddeeff"
        CPPUNIT_ASSERT(97 == buff[118]);
        CPPUNIT_ASSERT(97 == buff[119]);
        CPPUNIT_ASSERT(98 == buff[120]);
        CPPUNIT_ASSERT(98 == buff[121]);
        CPPUNIT_ASSERT(99 == buff[122]);
        CPPUNIT_ASSERT(99 == buff[123]);
        CPPUNIT_ASSERT(100 == buff[124]);
        CPPUNIT_ASSERT(100 == buff[125]);
        CPPUNIT_ASSERT(101 == buff[126]);
        CPPUNIT_ASSERT(101 == buff[127]);
        CPPUNIT_ASSERT(102 == buff[128]);
        CPPUNIT_ASSERT(102 == buff[129]);

        // offset 130: format field of VariableFormatField1, unsigned byte
        CPPUNIT_ASSERT(0 == buff[130]);

        // offset 131: count field of VariableFormatField1, unsigned int
        CPPUNIT_ASSERT(5 == ushortAtOffset(buff, 131));

        // offset 135-139: 5 byte data of VariableFormatField1
        assertBufferSliceEqual(buff, data2, 135, 5);

        // *** variant and record inside variant ***

        // offset 140: ubyte vtag field for variant
        CPPUNIT_ASSERT(1 == buff[140]);

        // offset 141: ubyte presence vector for Variant.Record.
        CPPUNIT_ASSERT(255 == buff[141]);

        // offset 142:    signed byte, Record3.Field1 fixed field
        CPPUNIT_ASSERT(99 == buff[142]);

        // offset 143:    uint, Record3.variableLengthField1 count field...
        CPPUNIT_ASSERT(10 == buff[143]);

        // offset 147-156: 10-byte Record3.variableLengthField1.
        assertBufferSliceEqual(buff, data1, 147, 10);

        // offset 157: Record3.Array1[0]
        CPPUNIT_ASSERT(0 == buff[157]);

        // offset 158: Record3.Array1[1]
        CPPUNIT_ASSERT(65 == buff[158]);

        // offset 159: Record3.Array1[2]
        CPPUNIT_ASSERT(0 == buff[159]);

        // offset 160: Record3.Array1[3]
        CPPUNIT_ASSERT(75 == buff[160]);

        // offset 162: Record3.Array1[5]
        CPPUNIT_ASSERT(85 == buff[162]);

        // offset 164: Record3.Array1[7]
        CPPUNIT_ASSERT(95 == buff[164]);

        // offset 166: Record3.Array1[9]
        CPPUNIT_ASSERT(105 == buff[166]);

        // offset 167: Record3.BitField1, single unsigned byte
        CPPUNIT_ASSERT(1 == buff[167]);

        // offset 168: 15-byte fixed length string.  first 7 chars: "LMNOPQR"
        CPPUNIT_ASSERT(76 == buff[168]);

        // offset 169: 15-byte fixed length string.
        CPPUNIT_ASSERT(77 == buff[169]);

        // offset 170: 15-byte fixed length string.
        CPPUNIT_ASSERT(78 == buff[170]);

        // offset 171: 15-byte fixed length string.
        CPPUNIT_ASSERT(79 == buff[171]);

        // offset 172: 15-byte fixed length string.
        CPPUNIT_ASSERT(80 == buff[172]);

        // offset 173: 15-byte fixed length string.
        CPPUNIT_ASSERT(81 == buff[173]);

        // offset 174: 15-byte fixed length string.
        CPPUNIT_ASSERT(82 == buff[174]);

        // offset 175: zeroed part of fixed length string
        CPPUNIT_ASSERT(0 == buff[175]);

        // offset 182: last zeroed byte of fixed length string
        CPPUNIT_ASSERT(0 == buff[182]);

        // offset 183: type/units enum of Record3.VariableField1, single byte
        CPPUNIT_ASSERT(1 == buff[183]);

        // offset 184: contents of Record3.VariableField1
        CPPUNIT_ASSERT(55 == buff[184]);

        // offset 185: 2-byte unsigned short, count field for Record3.VariableLengthString1
        CPPUNIT_ASSERT(11 == ushortAtOffset(buff, 185));

        // offset 50-60: 11 characters of VariableLengthString1 "hello world"
        CPPUNIT_ASSERT(104 == buff[187]);
        CPPUNIT_ASSERT(101 == buff[188]);
        CPPUNIT_ASSERT(108 == buff[189]);
        CPPUNIT_ASSERT(108 == buff[190]);
        CPPUNIT_ASSERT(111 == buff[191]);
        CPPUNIT_ASSERT(32 == buff[192]);
        CPPUNIT_ASSERT(119 == buff[193]);
        CPPUNIT_ASSERT(111 == buff[194]);
        CPPUNIT_ASSERT(114 == buff[195]);
        CPPUNIT_ASSERT(108 == buff[196]);
        CPPUNIT_ASSERT(100 == buff[197]);

        // offset 61: format field of VariableFormatField1, unsigned byte
        CPPUNIT_ASSERT(0 == buff[198]);

        // offset 62: count field of VariableFormatField1, unsigned int
        CPPUNIT_ASSERT(5 == ushortAtOffset(buff, 199));

        // offset 66-70: 5 byte data of VariableFormatField1
        assertBufferSliceEqual(buff, data2, 203, 5);

        for (int i = 0; i < 10000; ++i) 
		{
			buff[i] = 0;
		}

        // 4th msg: activate the subsequence, activate only some fields inside subsequence's record...
        MsgIn msg4;

        msg4.getHeader()->getHeaderRec()->setMessageID(999);

        MsgIn::Body::Sequence::Subsequence::Record& sseqRec = 
            *(msg4.getBody()->getSequence()->getSubsequence()->getRecord());

        sseqRec.setField1(77);
        sseqRec.getVariableLengthField1()->set(10, data1);
        sseqRec.getArray1()->setElement1(1, 10);
        sseqRec.getArray1()->setElement1(3, 20);
        sseqRec.getArray1()->setElement1(5, 30);
        sseqRec.getArray1()->setElement1(7, 40);
        sseqRec.getArray1()->setElement1(9, 50);
        sseqRec.getBitField1()->setSubfield1(1);
        sseqRec.setFixedLengthString1("ABCDE");
        sseqRec.getVariableField1()->setMeterAsUnsignedByteAt1(33);
        sseqRec.setVariableLengthString1("JTS=PWNT");
        sseqRec.getVariableFormatField1()->set(0, 5, data2);

        msg4.encode(buff);

        // offset 0:    ushort w/ message ID
        CPPUNIT_ASSERT(999 == ushortAtOffset(buff, 0));

        // offset 2:    ushort w/ Sequence's presence vector - 512 indicates only Subsequence present
        CPPUNIT_ASSERT(512 == ushortAtOffset(buff, 2));

        // offset 4:    ubyte w/ Subsequence's presence vector - should be 1 indicating only first
        // record is present
        CPPUNIT_ASSERT(1 == buff[4]);

        // offset 5: ubyte w/ Record's presence vector - 255 indicates all elements present
        CPPUNIT_ASSERT(255 == buff[5]);

        // offset 6:    signed byte, Field1 fixed field
        CPPUNIT_ASSERT(77 == buff[6]);

        // offset 7:    uint, variableLengthField1 count field...
        CPPUNIT_ASSERT(10 == buff[7]);

        // offset 11-20: 10-byte variableLengthField1.
        assertBufferSliceEqual(buff, data1, 11, 10);

        // offset 21: Array1[0]
        CPPUNIT_ASSERT(0 == buff[21]);

        // offset 22: Array1[1]
        CPPUNIT_ASSERT(10 == buff[22]);

        // offset 23: Array1[2]
        CPPUNIT_ASSERT(0 == buff[23]);

        // offset 24: Array1[3]
        CPPUNIT_ASSERT(20 == buff[24]);

        // offset 26: Array1[5]
        CPPUNIT_ASSERT(30 == buff[26]);

        // offset 28: Array1[7]
        CPPUNIT_ASSERT(40 == buff[28]);

        // offset 30: Array1[9]
        CPPUNIT_ASSERT(50 == buff[30]);

        // offset 31: BitField1, single unsigned byte
        CPPUNIT_ASSERT(1 == buff[31]);

        // offset 32: 15-byte fixed length string.  first 5 chars: "ABCDE"
        CPPUNIT_ASSERT(65 == buff[32]);

        // offset 33: 15-byte fixed length string.  first 5 chars: "ABCDE"
        CPPUNIT_ASSERT(66 == buff[33]);

        // offset 34: 15-byte fixed length string.  first 5 chars: "ABCDE"
        CPPUNIT_ASSERT(67 == buff[34]);

        // offset 35: 15-byte fixed length string.  first 5 chars: "ABCDE"
        CPPUNIT_ASSERT(68 == buff[35]);

        // offset 36: 15-byte fixed length string.  first 5 chars: "ABCDE"
        CPPUNIT_ASSERT(69 == buff[36]);

        // offset 37: zeroed part of fixed length string
        CPPUNIT_ASSERT(0 == buff[37]);

        // offset 46: last zeroed byte of fixed length string
        CPPUNIT_ASSERT(0 == buff[46]);

        // offset 47: type/units enum of VariableField1, single byte
        CPPUNIT_ASSERT(1 == buff[47]);

        // offset 48: contents of VariableField1
        CPPUNIT_ASSERT(33 == buff[48]);

        // offset 49: 2-byte unsigned short, count field for VariableLengthString1
        CPPUNIT_ASSERT(8 == ushortAtOffset(buff, 49));

        // offset 51-61: 11 characters of VariableLengthString1 "hello world"
        CPPUNIT_ASSERT(74 == buff[51]);
        CPPUNIT_ASSERT(84 == buff[52]);
        CPPUNIT_ASSERT(83 == buff[53]);
        CPPUNIT_ASSERT(61 == buff[54]);
        CPPUNIT_ASSERT(80 == buff[55]);
        CPPUNIT_ASSERT(87 == buff[56]);
        CPPUNIT_ASSERT(78 == buff[57]);
        CPPUNIT_ASSERT(84 == buff[58]);

        // offset 59: format field of VariableFormatField1, unsigned byte
        CPPUNIT_ASSERT(0 == buff[59]);

        // offset 60: count field of VariableFormatField1, unsigned int
        CPPUNIT_ASSERT(5 == ushortAtOffset(buff, 60));

        // offset 64-69: 5 byte data of VariableFormatField1
        assertBufferSliceEqual(buff, data2, 64, 5);
  }
  
  void testEncodeDecodeOperations() 
  {
        OUT_1 << std::endl << "[executing test (testEncodeDecodeOperations)(TP_3.3.5.3)]" << std::endl;

        MsgIn msg1;
        MsgIn msg2;

        unsigned char data1[10];
        unsigned char data2[5];

        for (int i = 0; i < 10; ++i) {
            data1[i] = 10+i;
        }
        for (int i = 0; i < 5; ++i) {
            data2[i] = 20+i;
        }

        msg1.getHeader()->getHeaderRec()->setMessageID(999);

        MsgIn::Body::Sequence::Record3& rec3_i = *(msg1.getBody()->getSequence()->getRecord3());

        rec3_i.setField1(77);
        rec3_i.getVariableLengthField1()->set(10, data1);
        rec3_i.getArray1()->setElement1(1, 10);
        rec3_i.getArray1()->setElement1(3, 20);
        rec3_i.getArray1()->setElement1(5, 30);
        rec3_i.getArray1()->setElement1(7, 40);
        rec3_i.getArray1()->setElement1(9, 50);
        rec3_i.getBitField1()->setSubfield1(1);
        rec3_i.setFixedLengthString1("ABCDE");
        rec3_i.getVariableField1()->setMeterAsUnsignedByteAt1(33);
        rec3_i.setVariableLengthString1("hello world");
        rec3_i.getVariableFormatField1()->set(0, 5, data2);

	    MsgIn::Body::Sequence::List::Record listrec_i;
        listrec_i.setField1(77);
        listrec_i.getVariableLengthField1()->set(10, data1);
        listrec_i.getArray1()->setElement1(1, 60);
        listrec_i.getArray1()->setElement1(3, 70);
        listrec_i.getArray1()->setElement1(5, 80);
        listrec_i.getArray1()->setElement1(7, 90);
        listrec_i.getArray1()->setElement1(9, 100);
        listrec_i.getBitField1()->setSubfield1(1);
        listrec_i.setFixedLengthString1("FGHIJK");
        listrec_i.getVariableField1()->setMeterAsUnsignedByteAt1(44);
        listrec_i.setVariableLengthString1("aabbccddeeff");
        listrec_i.getVariableFormatField1()->set(0, 5, data2);

	    msg1.getBody()->getSequence()->getList()->addElement( listrec_i );

        // Variants
        msg1.getBody()->getSequence()->getVariant()->setFieldValue(1); // option 1: Record
        MsgIn::Body::Sequence::Variant::Record& varrec_i = 
            *(msg1.getBody()->getSequence()->getVariant()->getRecord());

        varrec_i.setField1(99);
        varrec_i.getVariableLengthField1()->set(10, data1);
        varrec_i.getArray1()->setElement1(1, 65);
        varrec_i.getArray1()->setElement1(3, 75);
        varrec_i.getArray1()->setElement1(5, 85);
        varrec_i.getArray1()->setElement1(7, 95);
        varrec_i.getArray1()->setElement1(9, 105);
        varrec_i.getBitField1()->setSubfield1(1);
        varrec_i.setFixedLengthString1("LMNOPQR");
        varrec_i.getVariableField1()->setMeterAsUnsignedByteAt1(55);
        varrec_i.setVariableLengthString1("hello world");
        varrec_i.getVariableFormatField1()->set(0, 5, data2);

        unsigned char buff[1000];

        for (int i = 0; i < 1000; ++i) 
		{
			buff[i] = 0;
		}
        
        const unsigned char* fieldBuff_i = NULL;
        const unsigned char* fieldBuff_o = NULL;

        msg1.encode(buff);
        msg2.decode(buff);

        MsgIn::Body::Sequence::Record3& rec3_o = *(msg2.getBody()->getSequence()->getRecord3());
	    MsgIn::Body::Sequence::List::Record& listrec_o = 
	        *(msg2.getBody()->getSequence()->getList()->getElement(0));
        MsgIn::Body::Sequence::Variant::Record& varrec_o = 
            *(msg2.getBody()->getSequence()->getVariant()->getRecord());

        // *** ensure Record3 was decoded correctly

        // compare non bytefield data
        CPPUNIT_ASSERT(rec3_i.getField1() == rec3_o.getField1());
        for (int i = 0; i < 10; ++i) {
            CPPUNIT_ASSERT(rec3_i.getArray1()->getElement1(i) == rec3_o.getArray1()->getElement1(i));
        }
        CPPUNIT_ASSERT(rec3_i.getBitField1()->getSubfield1() == rec3_o.getBitField1()->getSubfield1());
        CPPUNIT_ASSERT(rec3_i.getFixedLengthString1() == rec3_o.getFixedLengthString1());
        CPPUNIT_ASSERT(rec3_i.getVariableField1()->getMeterAsUnsignedByteAt1()
                == rec3_o.getVariableField1()->getMeterAsUnsignedByteAt1());
        CPPUNIT_ASSERT(rec3_i.getVariableLengthString1() == rec3_o.getVariableLengthString1());

        // compare bytefield data
        fieldBuff_i = rec3_i.getVariableLengthField1()->getData();
        fieldBuff_o = rec3_o.getVariableLengthField1()->getData();
        assertConstBuffersEqual(fieldBuff_i, fieldBuff_o, 10);

        fieldBuff_i = rec3_i.getVariableFormatField1()->getData();
        fieldBuff_o = rec3_o.getVariableFormatField1()->getData();
        CPPUNIT_ASSERT(rec3_i.getVariableFormatField1()->getFormat()
                == rec3_o.getVariableFormatField1()->getFormat());
        assertConstBuffersEqual(fieldBuff_i, fieldBuff_o, 5);

        // *** ensure Record under List was decoded correctly

        // compare non-bytefield data
        CPPUNIT_ASSERT(listrec_i.getField1() == listrec_o.getField1());
        for (int i = 0; i < 10; ++i) {
            CPPUNIT_ASSERT(listrec_i.getArray1()->getElement1(i) == listrec_o.getArray1()->getElement1(i));
        }
        CPPUNIT_ASSERT(listrec_i.getBitField1()->getSubfield1() == listrec_o.getBitField1()->getSubfield1());
        CPPUNIT_ASSERT(listrec_i.getFixedLengthString1() == listrec_o.getFixedLengthString1());
        CPPUNIT_ASSERT(listrec_i.getVariableField1()->getMeterAsUnsignedByteAt1()
                == listrec_o.getVariableField1()->getMeterAsUnsignedByteAt1());
        CPPUNIT_ASSERT(listrec_i.getVariableLengthString1() == listrec_o.getVariableLengthString1());

        // compare bytefield data
        fieldBuff_i = listrec_i.getVariableLengthField1()->getData();
        fieldBuff_o = listrec_o.getVariableLengthField1()->getData();
        assertConstBuffersEqual(fieldBuff_i, fieldBuff_o, 10);

        fieldBuff_i = listrec_i.getVariableFormatField1()->getData();
        fieldBuff_o = listrec_o.getVariableFormatField1()->getData();
        CPPUNIT_ASSERT(listrec_i.getVariableFormatField1()->getFormat()
                == listrec_o.getVariableFormatField1()->getFormat());
        assertConstBuffersEqual(fieldBuff_i, fieldBuff_o, 5);

        // *** ensure Record under Variant was decoded correctly.

        // compare non-bytefield data
        CPPUNIT_ASSERT(varrec_i.getField1() == varrec_o.getField1());
        for (int i = 0; i < 10; ++i) {
            CPPUNIT_ASSERT(varrec_i.getArray1()->getElement1(i) == varrec_o.getArray1()->getElement1(i));
        }
        CPPUNIT_ASSERT(varrec_i.getBitField1()->getSubfield1() == varrec_o.getBitField1()->getSubfield1());
        CPPUNIT_ASSERT(varrec_i.getFixedLengthString1() == varrec_o.getFixedLengthString1());
        CPPUNIT_ASSERT(varrec_i.getVariableField1()->getMeterAsUnsignedByteAt1()
                == varrec_o.getVariableField1()->getMeterAsUnsignedByteAt1());
        CPPUNIT_ASSERT(varrec_i.getVariableLengthString1() == varrec_o.getVariableLengthString1());

        // compare bytefield data
        fieldBuff_i = varrec_i.getVariableLengthField1()->getData();
        fieldBuff_o = varrec_o.getVariableLengthField1()->getData();
        assertConstBuffersEqual(fieldBuff_i, fieldBuff_o, 10);

        fieldBuff_i = varrec_i.getVariableFormatField1()->getData();
        fieldBuff_o = varrec_o.getVariableFormatField1()->getData();
        CPPUNIT_ASSERT(varrec_i.getVariableFormatField1()->getFormat()
                == varrec_o.getVariableFormatField1()->getFormat());
        assertConstBuffersEqual(fieldBuff_i, fieldBuff_o, 5);
  }

  void testEquality()
  {
	  OUT_1 << std::endl << "[executing test (testEquality)]" << std::endl;

        MsgIn msg1;
        unsigned char data1[10];
        unsigned char data2[5];

        for (int i = 0; i < 10; ++i) {
            data1[i] = 10+i;
        }
        for (int i = 0; i < 5; ++i) {
            data2[i] = 20+i;
        }

        msg1.getHeader()->getHeaderRec()->setMessageID(999);

        msg1.getBody()->getSequence()->getRecord2()->setField1(77);
        msg1.getBody()->getSequence()->getRecord2()->getVariableLengthField1()->set(10, data1);
        msg1.getBody()->getSequence()->getRecord4()->getArray1()->setElement1(1, 10);
        msg1.getBody()->getSequence()->getRecord4()->getArray1()->setElement1(3, 20);
        msg1.getBody()->getSequence()->getRecord4()->getArray1()->setElement1(5, 30);
        msg1.getBody()->getSequence()->getRecord4()->getArray1()->setElement1(7, 40);
        msg1.getBody()->getSequence()->getRecord4()->getArray1()->setElement1(9, 50);
        msg1.getBody()->getSequence()->getRecord4()->getBitField1()->setSubfield1(1);
        msg1.getBody()->getSequence()->getRecord6()->setFixedLengthString1("ABCDE");
        msg1.getBody()->getSequence()->getRecord6()->getVariableField1()->setMeterAsUnsignedByteAt1(33);
        msg1.getBody()->getSequence()->getRecord8()->setVariableLengthString1("hello world");
        msg1.getBody()->getSequence()->getRecord8()->getVariableFormatField1()->set(0, 5, data2);

        MsgIn msg2;

        msg2.getHeader()->getHeaderRec()->setMessageID(999);

        msg2.getBody()->getSequence()->getRecord2()->setField1(77);
        msg2.getBody()->getSequence()->getRecord2()->getVariableLengthField1()->set(10, data1);
        msg2.getBody()->getSequence()->getRecord4()->getArray1()->setElement1(1, 10);
        msg2.getBody()->getSequence()->getRecord4()->getArray1()->setElement1(3, 20);
        msg2.getBody()->getSequence()->getRecord4()->getArray1()->setElement1(5, 30);
        msg2.getBody()->getSequence()->getRecord4()->getArray1()->setElement1(7, 40);
        msg2.getBody()->getSequence()->getRecord4()->getArray1()->setElement1(9, 50);
        msg2.getBody()->getSequence()->getRecord4()->getBitField1()->setSubfield1(1);
        msg2.getBody()->getSequence()->getRecord6()->setFixedLengthString1("ABCDE");
        msg2.getBody()->getSequence()->getRecord6()->getVariableField1()->setMeterAsUnsignedByteAt1(33);
        msg2.getBody()->getSequence()->getRecord8()->setVariableLengthString1("hello world");
        msg2.getBody()->getSequence()->getRecord8()->getVariableFormatField1()->set(0, 5, data2);

        CPPUNIT_ASSERT(msg1 == msg2);
        CPPUNIT_ASSERT(msg2 == msg1);
        CPPUNIT_ASSERT(msg1 == msg1);

        msg2.getBody()->getSequence()->getRecord6()->setFixedLengthString1("ABCD");

        CPPUNIT_ASSERT(msg1 != msg2);
        CPPUNIT_ASSERT(msg2 != msg1);

		msg2.getBody()->getSequence()->getRecord6()->setFixedLengthString1("ABCDE");
		CPPUNIT_ASSERT(msg1 == msg2);
		msg2.getBody()->getSequence()->getRecord1()->setField1(0);
		CPPUNIT_ASSERT(msg1 != msg2); 
									 

  }
  
  void testInequality()
  {
        OUT_1 << std::endl << "[executing test (testInequality)]" << std::endl;

        MsgIn msg1;
        unsigned char data1[10];
        unsigned char data2[5];

        for (int i = 0; i < 10; ++i)
        {
            data1[i] = (10 + i);
        }
        for (int i = 0; i < 5; ++i)
        {
            data2[i] = (20 + i);
        }

        msg1.getHeader()->getHeaderRec()->setMessageID(999);

        msg1.getBody()->getSequence()->getRecord2()->setField1(77);
        msg1.getBody()->getSequence()->getRecord2()->getVariableLengthField1()->set(10, data1);
        msg1.getBody()->getSequence()->getRecord4()->getArray1()->setElement1(1, 10);
        msg1.getBody()->getSequence()->getRecord4()->getArray1()->setElement1(3, 20);
        msg1.getBody()->getSequence()->getRecord4()->getArray1()->setElement1(5, 30);
        msg1.getBody()->getSequence()->getRecord4()->getArray1()->setElement1(7, 40);
        msg1.getBody()->getSequence()->getRecord4()->getArray1()->setElement1(9, 50);
        msg1.getBody()->getSequence()->getRecord4()->getBitField1()->setSubfield1(1);
        msg1.getBody()->getSequence()->getRecord6()->setFixedLengthString1("ABCDE");
        msg1.getBody()->getSequence()->getRecord6()->getVariableField1()->setMeterAsUnsignedByteAt1(33);
        msg1.getBody()->getSequence()->getRecord8()->setVariableLengthString1("hello world");
        msg1.getBody()->getSequence()->getRecord8()->getVariableFormatField1()->set(0, 5, data2);

        MsgIn msg2;

        msg2.getHeader()->getHeaderRec()->setMessageID(999);

        msg2.getBody()->getSequence()->getRecord2()->setField1(77);
        msg2.getBody()->getSequence()->getRecord2()->getVariableLengthField1()->set(10, data1);
        msg2.getBody()->getSequence()->getRecord4()->getArray1()->setElement1(1, 10);
        msg2.getBody()->getSequence()->getRecord4()->getArray1()->setElement1(3, 20);
        msg2.getBody()->getSequence()->getRecord4()->getArray1()->setElement1(5, 30);
        msg2.getBody()->getSequence()->getRecord4()->getArray1()->setElement1(7, 40);
        msg2.getBody()->getSequence()->getRecord4()->getArray1()->setElement1(9, 50);
        msg2.getBody()->getSequence()->getRecord4()->getBitField1()->setSubfield1(1);
        msg2.getBody()->getSequence()->getRecord6()->setFixedLengthString1("ABCDE");
        msg2.getBody()->getSequence()->getRecord6()->getVariableField1()->setMeterAsUnsignedByteAt1(33);
        msg2.getBody()->getSequence()->getRecord8()->setVariableLengthString1("hello world");
        msg2.getBody()->getSequence()->getRecord8()->getVariableFormatField1()->set(0, 5, data2);

        // messages are equal now - notEquals should be false
        CPPUNIT_ASSERT(!(msg1 != msg2));
        CPPUNIT_ASSERT(!(msg2 != msg1));
        CPPUNIT_ASSERT(!(msg1 != msg1));

        // alter msg2, notEquals should now be true
        msg2.getBody()->getSequence()->getRecord6()->setFixedLengthString1("ABCDEFG");

        CPPUNIT_ASSERT(msg1 != msg2);
        CPPUNIT_ASSERT(msg2 != msg1);
  }

  static CppUnit::Test *suite()
  {
    CppUnit::TestSuite *suiteOfTests = new CppUnit::TestSuite( "JTSOptional1MessageTest" );
    
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSOptional1MessageTest>( 
                                   "testSetGetOperations (TP_3.3.5.1)", 
                                   &JTSOptional1MessageTest::testSetGetOperations ) );
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSOptional1MessageTest>( 
                                   "testEncodeToSpec (TP_3.3.5.2)", 
                                   &JTSOptional1MessageTest::testEncodeToSpec ) );
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSOptional1MessageTest>( 
                                   "testEncodeDecodeOperations (TP_3.3.5.3)", 
                                   &JTSOptional1MessageTest::testEncodeDecodeOperations ) );
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSOptional1MessageTest>( 
                                   "testEquality", 
                                   &JTSOptional1MessageTest::testEquality ) );
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSOptional1MessageTest>( 
                                   "testInequality", 
                                   &JTSOptional1MessageTest::testInequality ) );
                                   
    return suiteOfTests;
  }

};


int main( int ac, char **av )
{
  CppUnit::TextUi::TestRunner runner;
  runner.addTest( JTSOptional1MessageTest::suite() );
  
  OUT_0 << std::endl << "------------------------------------------------" << std::endl;
  OUT_0 << "Executing suite JTSOptional1MessageTest" << std::endl;
  runner.run();
  OUT_0 << std::endl << "Completed suite JTSOptional1MessageTest" << std::endl;
  OUT_0 << "------------------------------------------------" << std::endl;  
}

