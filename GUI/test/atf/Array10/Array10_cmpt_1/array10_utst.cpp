/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2011, United States Government
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

// test for message structure with <array> of <variable_format_field>s
#define STRINGIFY(x) #x
#define TOSTRING(x) STRINGIFY(x)
#define LINE_NUM TOSTRING(__LINE__)

#include <iostream>

#include "urn_org_jts_test_Array10_1_0/Messages/MessageSet.h"
#include "cppunit/TestCaller.h"
#include "cppunit/TestCaller.h"
#include "cppunit/ui/text/TestRunner.h"
#include "cppunit/TestSuite.h"

using namespace urn_org_jts_test_Array10_1_0;
using namespace std;

#define OUT_0  std::cout
#define OUT_1  if (bVerbose<=2) std::cout
#define OUT_2  if (bVerbose<=1) std::cout

// retrieve unsigned short from buffer at given offset.
unsigned short ushortAtOffset(unsigned char* buffer, unsigned int offset)
{
    // take address at given offset into buffer, treat pointer as unsigned short pointer, then dereference an
    // unsigned short.
    return *((unsigned short*)&(buffer[offset]));
}

// retrieve unsigned int from buffer at given offset.
unsigned int uintAtOffset(unsigned char* buffer, unsigned int offset)
{
    // take address at given offset into buffer, treat pointer as unsigned int pointer, then dereference an
    // unsigned int.
    return *((unsigned int*)&(buffer[offset]));
}

// assert if two arrays of unsigned chars are equal, given a length value.
void assertArraysEqualNC(unsigned char* arr_data1, unsigned char* arr_data2, int length, string line_info) {
    for (int i = 0; i < length; ++i) {
        CPPUNIT_ASSERT_MESSAGE(string("Array equal assertion failed @ line ").append(line_info), arr_data1[i] == arr_data2[i]);
    }
}

// assert if two const arrays of unsigned chars are equal given a length value.
void assertArraysEqual(const unsigned char* arr_data1, const unsigned char* arr_data2, int length, string line_info) {
    assertArraysEqualNC(const_cast<unsigned char*>(arr_data1), const_cast<unsigned char*>(arr_data2), length, line_info);
}

class JTSArray10MessageTest : public CppUnit::TestFixture {

private:
  MsgIn msgIn1,msgIn2;
  int bVerbose;

public:
  JTSArray10MessageTest() {
     bVerbose=0;
  }

  void setUp()
  {
  }

  void tearDown()
  {
  }

  void testConstructionDefaults()
  {
    OUT_1 << std::endl << "  [executing test (testConstructionDefaults)] " << std::endl;

    OUT_2 << "     --- verifying MsgIn constructs with correct default MessageID" << std::endl;
    CPPUNIT_ASSERT( 1==msgIn1.getHeader()->getHeaderRec()->getMessageID());
    CPPUNIT_ASSERT( 1==msgIn2.getHeader()->getHeaderRec()->getMessageID());

    OUT_2 << "     --- verifying MsgIn constructs array with correct default DimensionSize" << std::endl;
    CPPUNIT_ASSERT( 10 == msgIn1.getBody()->getRecord()->getArray10()->getIDXSize());
    CPPUNIT_ASSERT( 10 == msgIn2.getBody()->getRecord()->getArray10()->getIDXSize());

    OUT_2 << "     --- verifying MsgIn variable format field data starts as null for all indices in array." << std::endl;

    for (int i = 0; i < msgIn1.getBody()->getRecord()->getArray10()->getIDXSize(); ++i)
    {
        CPPUNIT_ASSERT(NULL == msgIn1.getBody()->getRecord()->getArray10()->getVFF1(i)->getData());
        CPPUNIT_ASSERT(NULL == msgIn2.getBody()->getRecord()->getArray10()->getVFF1(i)->getData());
        CPPUNIT_ASSERT(0 == msgIn1.getBody()->getRecord()->getArray10()->getVFF1(i)->getFormat());
        CPPUNIT_ASSERT(0 == msgIn2.getBody()->getRecord()->getArray10()->getVFF1(i)->getFormat());
    }

    OUT_1 << "  [completed test (testConstructionDefaults)] " << std::endl;
  }

  void testSetGetOperations()
  {
    OUT_1 << std::endl << "  [executing test (testSetGetOperations)(TP_3.3.5.1)] " << std::endl;
    
    msgIn1.getHeader()->getHeaderRec()->setMessageID(99);

    unsigned char data1[1] = {0};
    unsigned char data2[2] = {0, 1};
    unsigned char data3[3] = {0, 1, 2};
    unsigned char data4[4] = {0, 1, 2, 3};
    unsigned char data5[5] = {0, 1, 2, 3, 4};
    unsigned char data6[6] = {0, 1, 2, 3, 4, 5};
    unsigned char data7[7] = {0, 1, 2, 3, 4, 5, 6};
    unsigned char data8[8] = {0, 1, 2, 3, 4, 5, 6, 7};
    unsigned char data9[9] = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    unsigned char data10[10] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(0)->set(0, 1, data1);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(1)->set(0, 2, data2);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(2)->set(0, 3, data3);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(3)->set(0, 4, data4);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(4)->set(0, 5, data5);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(5)->set(0, 6, data6);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(6)->set(0, 7, data7);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(7)->set(0, 8, data8);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(8)->set(0, 9, data9);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(9)->set(0, 10, data10);

    // check get results against what we set...
    CPPUNIT_ASSERT(99 == msgIn1.getHeader()->getHeaderRec()->getMessageID());

    // check format was recorded for some examples
    CPPUNIT_ASSERT(0 == msgIn1.getBody()->getRecord()->getArray10()->getVFF1(0)->getFormat());
    CPPUNIT_ASSERT(0 == msgIn1.getBody()->getRecord()->getArray10()->getVFF1(5)->getFormat());
    CPPUNIT_ASSERT(0 == msgIn1.getBody()->getRecord()->getArray10()->getVFF1(9)->getFormat());

    assertArraysEqual(data1, msgIn1.getBody()->getRecord()->getArray10()->getVFF1(0)->getData(), 1, LINE_NUM);
    assertArraysEqual(data2, msgIn1.getBody()->getRecord()->getArray10()->getVFF1(1)->getData(), 2, LINE_NUM);
    assertArraysEqual(data3, msgIn1.getBody()->getRecord()->getArray10()->getVFF1(2)->getData(), 3, LINE_NUM);
    assertArraysEqual(data4, msgIn1.getBody()->getRecord()->getArray10()->getVFF1(3)->getData(), 4, LINE_NUM);
    assertArraysEqual(data5, msgIn1.getBody()->getRecord()->getArray10()->getVFF1(4)->getData(), 5, LINE_NUM);
    assertArraysEqual(data6, msgIn1.getBody()->getRecord()->getArray10()->getVFF1(5)->getData(), 6, LINE_NUM);
    assertArraysEqual(data7, msgIn1.getBody()->getRecord()->getArray10()->getVFF1(6)->getData(), 7, LINE_NUM);
    assertArraysEqual(data8, msgIn1.getBody()->getRecord()->getArray10()->getVFF1(7)->getData(), 8, LINE_NUM);
    assertArraysEqual(data9, msgIn1.getBody()->getRecord()->getArray10()->getVFF1(8)->getData(), 9, LINE_NUM);
    assertArraysEqual(data10, msgIn1.getBody()->getRecord()->getArray10()->getVFF1(9)->getData(), 10, LINE_NUM);

    // change format...
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(0)->set(1, 1, data1);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(1)->set(1, 2, data2);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(2)->set(1, 3, data3);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(3)->set(1, 4, data4);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(4)->set(1, 5, data5);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(5)->set(1, 6, data6);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(6)->set(1, 7, data7);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(7)->set(1, 8, data8);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(8)->set(1, 9, data9);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(9)->set(1, 10, data10);

    // check new format was recorded
    CPPUNIT_ASSERT(1 == msgIn1.getBody()->getRecord()->getArray10()->getVFF1(0)->getFormat());
    CPPUNIT_ASSERT(1 == msgIn1.getBody()->getRecord()->getArray10()->getVFF1(5)->getFormat());
    CPPUNIT_ASSERT(1 == msgIn1.getBody()->getRecord()->getArray10()->getVFF1(9)->getFormat());

    // data arrays should be unchanged.
    assertArraysEqual(data1, msgIn1.getBody()->getRecord()->getArray10()->getVFF1(0)->getData(), 1, LINE_NUM);
    assertArraysEqual(data2, msgIn1.getBody()->getRecord()->getArray10()->getVFF1(1)->getData(), 2, LINE_NUM);
    assertArraysEqual(data3, msgIn1.getBody()->getRecord()->getArray10()->getVFF1(2)->getData(), 3, LINE_NUM);
    assertArraysEqual(data4, msgIn1.getBody()->getRecord()->getArray10()->getVFF1(3)->getData(), 4, LINE_NUM);
    assertArraysEqual(data5, msgIn1.getBody()->getRecord()->getArray10()->getVFF1(4)->getData(), 5, LINE_NUM);
    assertArraysEqual(data6, msgIn1.getBody()->getRecord()->getArray10()->getVFF1(5)->getData(), 6, LINE_NUM);
    assertArraysEqual(data7, msgIn1.getBody()->getRecord()->getArray10()->getVFF1(6)->getData(), 7, LINE_NUM);
    assertArraysEqual(data8, msgIn1.getBody()->getRecord()->getArray10()->getVFF1(7)->getData(), 8, LINE_NUM);
    assertArraysEqual(data9, msgIn1.getBody()->getRecord()->getArray10()->getVFF1(8)->getData(), 9, LINE_NUM);
    assertArraysEqual(data10, msgIn1.getBody()->getRecord()->getArray10()->getVFF1(9)->getData(), 10, LINE_NUM);

    // check getSize() returns accurate value.
    // 2-byte header, 10 2-byte count fields, 10 1-byte format fields, 55 total VFF data bytes.
    CPPUNIT_ASSERT(msgIn1.getSize() == 2 + 10*2 + 10*1 + 55);

    OUT_1 <<  "  [completed test (testSetGetOperations)] " << std::endl;
  }

  void testEncodeToSpec()
  {
    unsigned char buff[1024];

    int i;

    for (i = 0; i < 1024; ++i)
    {
        buff[i] = 0;
    }

    OUT_1 << std::endl << "  [executing test (testEncodeToSpec)(TP_3.3.5.2)] " << std::endl;

    msgIn1.getHeader()->getHeaderRec()->setMessageID(999);

    unsigned char data1[1] = {10};
    unsigned char data2[2] = {10, 11};
    unsigned char data3[3] = {10, 11, 12};
    unsigned char data4[4] = {10, 11, 12, 13};
    unsigned char data5[5] = {10, 11, 12, 13, 14};
    unsigned char data6[6] = {10, 11, 12, 13, 14, 15};
    unsigned char data7[7] = {10, 11, 12, 13, 14, 15, 16};
    unsigned char data8[8] = {10, 11, 12, 13, 14, 15, 16, 17};
    unsigned char data9[9] = {10, 11, 12, 13, 14, 15, 16, 17, 18};
    unsigned char data10[10] = {10, 11, 12, 13, 14, 15, 16, 17, 18, 19};

    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(0)->set(1, 1, data1);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(1)->set(1, 2, data2);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(2)->set(1, 3, data3);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(3)->set(1, 4, data4);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(4)->set(1, 5, data5);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(5)->set(1, 6, data6);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(6)->set(1, 7, data7);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(7)->set(1, 8, data8);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(8)->set(1, 9, data9);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(9)->set(1, 10, data10);

    msgIn1.encode(buff);
    
    // offset 0:   unsigned short w/ message ID
    CPPUNIT_ASSERT(999 == ushortAtOffset(buff, 0));

    // offset 2: unsigned char w/ format field
    CPPUNIT_ASSERT(1 == buff[2]);

    // offset 3:  unsigned short w/ count field = 1
    CPPUNIT_ASSERT(1 == ushortAtOffset(buff, 3));

    // offset 5: single unsigned char from 1st VFF
    CPPUNIT_ASSERT(10 == buff[5]);

    // offset 6: unsigned char w/ format field
    CPPUNIT_ASSERT(1 == buff[6]);

    // offset 7:  unsigned short w/ count field = 2
    CPPUNIT_ASSERT(2 == ushortAtOffset(buff, 7));

    // offset 9: unsigned char from 2nd VFF
    CPPUNIT_ASSERT(10 == buff[9]);

    // offset 10: unsigned char from 2nd VFF
    CPPUNIT_ASSERT(11 == buff[10]);

    // offset 11: unsigned char w/ format field
    CPPUNIT_ASSERT(1 == buff[11]);

    // offset 12:  unsigned short w/ count field = 3
    CPPUNIT_ASSERT(3 == ushortAtOffset(buff, 12));

    // array of uchar starting at offset 14: 3rd VFF data
    assertArraysEqualNC(&buff[14], data3, 3, LINE_NUM);
    
    // skip to the last VFF
    // offset 74: unsigned char w/ format field
    CPPUNIT_ASSERT(1 == buff[74]);
    
    // offset 75: unsigned short w/ count field = 10
    CPPUNIT_ASSERT(10 == ushortAtOffset(buff, 75));
    
    // array of uchar starting at offset 77: 10th VFF data
    assertArraysEqualNC(&buff[77], data10, 10, LINE_NUM);

    OUT_1 << "  [completed test (testEncodeToSpec)] " << std::endl;
  }

  void testEncodeDecodeOperations()
  {
    unsigned char buff[1024];
    int i;

    for (i=0;i<1024;i++)
       buff[i] = ((4101*i)+65543) % 256;

    OUT_1 << std::endl << "  [executing test (testEncodeDecodeOperations)(TP_3.3.5.3)] " << std::endl;

    OUT_2 << "     --- verifying message level encode/decode reciprocity" << std::endl;

    // set initial contents of messages to differ.

    OUT_1 << "  [completed test (testEncodeDecodeOperations)] " << std::endl;

    msgIn1.getHeader()->getHeaderRec()->setMessageID(50);

    unsigned char data1[1] = {10};
    unsigned char data2[2] = {10, 11};
    unsigned char data3[3] = {10, 11, 12};
    unsigned char data4[4] = {10, 11, 12, 13};
    unsigned char data5[5] = {10, 11, 12, 13, 14};
    unsigned char data6[6] = {10, 11, 12, 13, 14, 15};
    unsigned char data7[7] = {10, 11, 12, 13, 14, 15, 16};
    unsigned char data8[8] = {10, 11, 12, 13, 14, 15, 16, 17};
    unsigned char data9[9] = {10, 11, 12, 13, 14, 15, 16, 17, 18};
    unsigned char data10[10] = {10, 11, 12, 13, 14, 15, 16, 17, 18, 19};

    unsigned char data1_b[1] = {20};
    unsigned char data2_b[2] = {20, 11};
    unsigned char data3_b[3] = {20, 11, 12};
    unsigned char data4_b[4] = {20, 11, 12, 13};
    unsigned char data5_b[5] = {20, 11, 12, 13, 14};
    unsigned char data6_b[6] = {20, 11, 12, 13, 14, 15};
    unsigned char data7_b[7] = {20, 11, 12, 13, 14, 15, 16};
    unsigned char data8_b[8] = {20, 11, 12, 13, 14, 15, 16, 17};
    unsigned char data9_b[9] = {20, 11, 12, 13, 14, 15, 16, 17, 18};
    unsigned char data10_b[10] = {20, 11, 12, 13, 14, 15, 16, 17, 18, 19};

    unsigned char data1_c[1] = {30};
    unsigned char data2_c[2] = {30, 11};
    unsigned char data3_c[3] = {30, 11, 12};
    unsigned char data4_c[4] = {30, 11, 12, 13};
    unsigned char data5_c[5] = {30, 11, 12, 13, 14};
    unsigned char data6_c[6] = {30, 11, 12, 13, 14, 15};
    unsigned char data7_c[7] = {30, 11, 12, 13, 14, 15, 16};
    unsigned char data8_c[8] = {30, 11, 12, 13, 14, 15, 16, 17};
    unsigned char data9_c[9] = {30, 11, 12, 13, 14, 15, 16, 17, 18};
    unsigned char data10_c[10] = {30, 11, 12, 13, 14, 15, 16, 17, 18, 19};

    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(0)->set(0, 1, data1);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(1)->set(0, 2, data2);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(2)->set(0, 3, data3);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(3)->set(0, 4, data4);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(4)->set(0, 5, data5);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(5)->set(0, 6, data6);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(6)->set(0, 7, data7);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(7)->set(0, 8, data8);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(8)->set(0, 9, data9);
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(9)->set(0, 10, data10);

    msgIn2.getHeader()->getHeaderRec()->setMessageID(100);

    msgIn2.getBody()->getRecord()->getArray10()->getVFF1(0)->set(1, 1, data1_b);
    msgIn2.getBody()->getRecord()->getArray10()->getVFF1(1)->set(1, 2, data2_b);
    msgIn2.getBody()->getRecord()->getArray10()->getVFF1(2)->set(1, 3, data3_b);
    msgIn2.getBody()->getRecord()->getArray10()->getVFF1(3)->set(1, 4, data4_b);
    msgIn2.getBody()->getRecord()->getArray10()->getVFF1(4)->set(1, 5, data5_b);
    msgIn2.getBody()->getRecord()->getArray10()->getVFF1(5)->set(1, 6, data6_b);
    msgIn2.getBody()->getRecord()->getArray10()->getVFF1(6)->set(1, 7, data7_b);
    msgIn2.getBody()->getRecord()->getArray10()->getVFF1(7)->set(1, 8, data8_b);
    msgIn2.getBody()->getRecord()->getArray10()->getVFF1(8)->set(1, 9, data9_b);
    msgIn2.getBody()->getRecord()->getArray10()->getVFF1(9)->set(1, 10, data10_b);

    // decode contents of msg1 into msg2.  fields of msg2 should now match fields of msg1.
    msgIn1.encode(buff);
    msgIn2.decode(buff);

    CPPUNIT_ASSERT(msgIn1.getHeader()->getHeaderRec()->getMessageID() == msgIn2.getHeader()->getHeaderRec()->getMessageID());

    assertArraysEqual(msgIn1.getBody()->getRecord()->getArray10()->getVFF1(0)->getData(),
        msgIn2.getBody()->getRecord()->getArray10()->getVFF1(0)->getData(), 1, LINE_NUM);
    assertArraysEqual(msgIn1.getBody()->getRecord()->getArray10()->getVFF1(1)->getData(),
        msgIn2.getBody()->getRecord()->getArray10()->getVFF1(1)->getData(), 2, LINE_NUM);
    assertArraysEqual(msgIn1.getBody()->getRecord()->getArray10()->getVFF1(2)->getData(),
        msgIn2.getBody()->getRecord()->getArray10()->getVFF1(2)->getData(), 3, LINE_NUM);
    assertArraysEqual(msgIn1.getBody()->getRecord()->getArray10()->getVFF1(3)->getData(),
        msgIn2.getBody()->getRecord()->getArray10()->getVFF1(3)->getData(), 4, LINE_NUM);
    assertArraysEqual(msgIn1.getBody()->getRecord()->getArray10()->getVFF1(4)->getData(),
        msgIn2.getBody()->getRecord()->getArray10()->getVFF1(4)->getData(), 5, LINE_NUM);
    assertArraysEqual(msgIn1.getBody()->getRecord()->getArray10()->getVFF1(5)->getData(),
        msgIn2.getBody()->getRecord()->getArray10()->getVFF1(5)->getData(), 6, LINE_NUM);
    assertArraysEqual(msgIn1.getBody()->getRecord()->getArray10()->getVFF1(6)->getData(),
        msgIn2.getBody()->getRecord()->getArray10()->getVFF1(6)->getData(), 7, LINE_NUM);
    assertArraysEqual(msgIn1.getBody()->getRecord()->getArray10()->getVFF1(7)->getData(),
        msgIn2.getBody()->getRecord()->getArray10()->getVFF1(7)->getData(), 8, LINE_NUM);
    assertArraysEqual(msgIn1.getBody()->getRecord()->getArray10()->getVFF1(8)->getData(),
        msgIn2.getBody()->getRecord()->getArray10()->getVFF1(8)->getData(), 9, LINE_NUM);
    assertArraysEqual(msgIn1.getBody()->getRecord()->getArray10()->getVFF1(9)->getData(),
        msgIn2.getBody()->getRecord()->getArray10()->getVFF1(9)->getData(), 10, LINE_NUM);
        
    // check the format fields also
    
    CPPUNIT_ASSERT(0 == msgIn2.getBody()->getRecord()->getArray10()->getVFF1(0)->getFormat());
    CPPUNIT_ASSERT(0 == msgIn2.getBody()->getRecord()->getArray10()->getVFF1(1)->getFormat());
    CPPUNIT_ASSERT(0 == msgIn2.getBody()->getRecord()->getArray10()->getVFF1(2)->getFormat());
    CPPUNIT_ASSERT(0 == msgIn2.getBody()->getRecord()->getArray10()->getVFF1(3)->getFormat());
    CPPUNIT_ASSERT(0 == msgIn2.getBody()->getRecord()->getArray10()->getVFF1(4)->getFormat());
    CPPUNIT_ASSERT(0 == msgIn2.getBody()->getRecord()->getArray10()->getVFF1(5)->getFormat());
    CPPUNIT_ASSERT(0 == msgIn2.getBody()->getRecord()->getArray10()->getVFF1(6)->getFormat());
    CPPUNIT_ASSERT(0 == msgIn2.getBody()->getRecord()->getArray10()->getVFF1(7)->getFormat());
    CPPUNIT_ASSERT(0 == msgIn2.getBody()->getRecord()->getArray10()->getVFF1(8)->getFormat());
    CPPUNIT_ASSERT(0 == msgIn2.getBody()->getRecord()->getArray10()->getVFF1(9)->getFormat());

    // change msg1's message ID..
    msgIn1.getHeader()->getHeaderRec()->setMessageID(9000);

    // change contents of msg2's array, encode the array *alone* into the buffer, then decode
    // the buffer into msg1's array.
    msgIn2.getHeader()->getHeaderRec()->setMessageID(18000);
    msgIn2.getBody()->getRecord()->getArray10()->getVFF1(0)->set(1, 1, data1_c);
    msgIn2.getBody()->getRecord()->getArray10()->getVFF1(1)->set(1, 2, data2_c);
    msgIn2.getBody()->getRecord()->getArray10()->getVFF1(2)->set(1, 3, data3_c);
    msgIn2.getBody()->getRecord()->getArray10()->getVFF1(3)->set(1, 4, data4_c);
    msgIn2.getBody()->getRecord()->getArray10()->getVFF1(4)->set(1, 5, data5_c);
    msgIn2.getBody()->getRecord()->getArray10()->getVFF1(5)->set(1, 6, data6_c);
    msgIn2.getBody()->getRecord()->getArray10()->getVFF1(6)->set(1, 7, data7_c);
    msgIn2.getBody()->getRecord()->getArray10()->getVFF1(7)->set(1, 8, data8_c);
    msgIn2.getBody()->getRecord()->getArray10()->getVFF1(8)->set(1, 9, data9_c);
    msgIn2.getBody()->getRecord()->getArray10()->getVFF1(9)->set(1, 10, data10_c);

    msgIn2.getBody()->getRecord()->getArray10()->encode(buff);
    msgIn1.getBody()->getRecord()->getArray10()->decode(buff);
    
    // since the encode/decode only touched the array, msg1's message ID should still be 9000!
    CPPUNIT_ASSERT(9000 == msgIn1.getHeader()->getHeaderRec()->getMessageID());

    // contents of msg1's array should be equal to the _c arrays now...
    assertArraysEqual(data1_c, msgIn1.getBody()->getRecord()->getArray10()->getVFF1(0)->getData(), 1, LINE_NUM);
    assertArraysEqual(data2_c, msgIn1.getBody()->getRecord()->getArray10()->getVFF1(1)->getData(), 2, LINE_NUM);
    assertArraysEqual(data3_c, msgIn1.getBody()->getRecord()->getArray10()->getVFF1(2)->getData(), 3, LINE_NUM);
    assertArraysEqual(data4_c, msgIn1.getBody()->getRecord()->getArray10()->getVFF1(3)->getData(), 4, LINE_NUM);
    assertArraysEqual(data5_c, msgIn1.getBody()->getRecord()->getArray10()->getVFF1(4)->getData(), 5, LINE_NUM);
    assertArraysEqual(data6_c, msgIn1.getBody()->getRecord()->getArray10()->getVFF1(5)->getData(), 6, LINE_NUM);
    assertArraysEqual(data7_c, msgIn1.getBody()->getRecord()->getArray10()->getVFF1(6)->getData(), 7, LINE_NUM);
    assertArraysEqual(data8_c, msgIn1.getBody()->getRecord()->getArray10()->getVFF1(7)->getData(), 8, LINE_NUM);
    assertArraysEqual(data9_c, msgIn1.getBody()->getRecord()->getArray10()->getVFF1(8)->getData(), 9, LINE_NUM);
    assertArraysEqual(data10_c, msgIn1.getBody()->getRecord()->getArray10()->getVFF1(9)->getData(), 10, LINE_NUM);
    
    // format fields should now be 1.
    CPPUNIT_ASSERT(1 == msgIn1.getBody()->getRecord()->getArray10()->getVFF1(0)->getFormat());
    CPPUNIT_ASSERT(1 == msgIn1.getBody()->getRecord()->getArray10()->getVFF1(1)->getFormat());
    CPPUNIT_ASSERT(1 == msgIn1.getBody()->getRecord()->getArray10()->getVFF1(2)->getFormat());
    CPPUNIT_ASSERT(1 == msgIn1.getBody()->getRecord()->getArray10()->getVFF1(3)->getFormat());
    CPPUNIT_ASSERT(1 == msgIn1.getBody()->getRecord()->getArray10()->getVFF1(4)->getFormat());
    CPPUNIT_ASSERT(1 == msgIn1.getBody()->getRecord()->getArray10()->getVFF1(5)->getFormat());
    CPPUNIT_ASSERT(1 == msgIn1.getBody()->getRecord()->getArray10()->getVFF1(6)->getFormat());
    CPPUNIT_ASSERT(1 == msgIn1.getBody()->getRecord()->getArray10()->getVFF1(7)->getFormat());
    CPPUNIT_ASSERT(1 == msgIn1.getBody()->getRecord()->getArray10()->getVFF1(8)->getFormat());
    CPPUNIT_ASSERT(1 == msgIn1.getBody()->getRecord()->getArray10()->getVFF1(9)->getFormat());

    OUT_1 << "  [completed test (testEncodeDecodeOperations)] " << std::endl;
  }

  void testEquality()
  {
    OUT_1 << std::endl << "  [executing test (testEquality)] " << std::endl;
    OUT_2 << "     --- verifying message equality operator" << std::endl;
    CPPUNIT_ASSERT( msgIn1 == msgIn2 );
    OUT_1 << "  [completed test (testEquality)] " << std::endl;

    unsigned char data[10] = {30, 11, 12, 13, 14, 15, 16, 17, 18, 19};

    // change contents, still equal?
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(1)->set(1, 0, data);
    CPPUNIT_ASSERT( !(msgIn1 == msgIn2) );
    CPPUNIT_ASSERT( !(msgIn2 == msgIn1) );
  }

  void testInequality()
  {
    OUT_1 << std::endl << "  [executing test (testInequality)] " << std::endl;
    OUT_2 << "     --- verifying message inequality operator" << std::endl;

    CPPUNIT_ASSERT( !(msgIn1 != msgIn2) );

    unsigned char data[10] = {30, 11, 12, 13, 14, 15, 16, 17, 18, 19};

    // change contents, should now be inequal
    msgIn1.getBody()->getRecord()->getArray10()->getVFF1(1)->set(1, 0, data);

    CPPUNIT_ASSERT( (msgIn1 != msgIn2) );
    CPPUNIT_ASSERT( (msgIn2 != msgIn1) );

    OUT_1 << "  [completed test (testInequality)] " << std::endl;

  }

  static CppUnit::Test *suite()
  {
    CppUnit::TestSuite *suiteOfTests = new CppUnit::TestSuite( "JTSArray10MessageTest" );

    suiteOfTests->addTest( new CppUnit::TestCaller<JTSArray10MessageTest>(
                                   "testConstructionDefaults",
                                   &JTSArray10MessageTest::testConstructionDefaults ) );

    suiteOfTests->addTest( new CppUnit::TestCaller<JTSArray10MessageTest>(
                                   "testSetGetOperations (TP_3.3.5.1)",
                                   &JTSArray10MessageTest::testSetGetOperations ) );
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSArray10MessageTest>(
                                   "testEncodeToSpec (TP_3.3.5.2)",
                                   &JTSArray10MessageTest::testEncodeToSpec ) );
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSArray10MessageTest>(
                                   "testEncodeDecodeOperations (TP_3.3.5.3)",
                                   &JTSArray10MessageTest::testEncodeDecodeOperations ) );

    suiteOfTests->addTest( new CppUnit::TestCaller<JTSArray10MessageTest>(
                                   "testEquality",
                                   &JTSArray10MessageTest::testEquality ) );

    suiteOfTests->addTest( new CppUnit::TestCaller<JTSArray10MessageTest>(
                                   "testInequality",
                                   &JTSArray10MessageTest::testInequality ) );

     return suiteOfTests;
  }
};


int main( int ac, char **av )
{
  CppUnit::TextUi::TestRunner runner;
  runner.addTest( JTSArray10MessageTest::suite() );

  OUT_0 << std::endl << "------------------------------------------------" << std::endl;
  OUT_0 << "Executing suite JTSArray10MessageTest" << std::endl;
  runner.run();
  OUT_0 << std::endl << "Completed suite JTSArray10MessageTest" << std::endl;
  OUT_0 << "------------------------------------------------" << std::endl;
}

