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

// test for message structure with <array> of <variable_length_string>s

#include <iostream>
#include <string>
#include <sstream>

#include "urn_org_jts_test_Array7_1_0/Messages/MessageSet.h"
#include "cppunit/TestCaller.h"
#include "cppunit/TestCaller.h"
#include "cppunit/ui/text/TestRunner.h"
#include "cppunit/TestSuite.h"

using namespace urn_org_jts_test_Array7_1_0;
using namespace std;

#define OUT_0  std::cout
#define OUT_1  if (bVerbose<=2) std::cout
#define OUT_2  if (bVerbose<=1) std::cout

#define STRINGIFY(x) #x
#define TOSTRING(x) STRINGIFY(x)
#define LINE_NUM TOSTRING(__LINE__)

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

template <typename T>
string numToString(T num)
{
    stringstream ss;
    ss << num;
    return ss.str();
}

template <typename T>
string stringToNum(const string& text)
{
    stringstream ss(text);
    T result = 0;
    ss >> result;

    return result ? result : 0;
}

// assert if two arrays of unsigned chars are equal, given a length value.
void assertArraysEqualNC(unsigned char* arr_data1, unsigned char* arr_data2, int length, std::string test_info) {
    for (int i = 0; i < length; ++i) {
        CPPUNIT_ASSERT_MESSAGE(test_info, arr_data1[i] == arr_data2[i]);
    }
}

// assert if two const arrays of unsigned chars are equal given a length value.
void assertArraysEqual(const unsigned char* arr_data1, const unsigned char* arr_data2, int length, std::string test_info) {
    assertArraysEqualNC(const_cast<unsigned char*>(arr_data1), const_cast<unsigned char*>(arr_data2), length, test_info);
}

class JTSArray7MessageTest : public CppUnit::TestFixture {

private:
  MsgIn msgIn1,msgIn2;
  int bVerbose;

public:
  JTSArray7MessageTest() {
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

    // verifying MsgIn constructs with correct default MessageID
    CPPUNIT_ASSERT(1 == msgIn1.getHeader()->getHeaderRec()->getMessageID());
    CPPUNIT_ASSERT(1 == msgIn2.getHeader()->getHeaderRec()->getMessageID());

    // verifying MsgIn constructs with correct default DimensionSize
    CPPUNIT_ASSERT(9 == msgIn1.getBody()->getRecord()->getArray7()->getDimension1Size());
    CPPUNIT_ASSERT(9 == msgIn2.getBody()->getRecord()->getArray7()->getDimension1Size());

    // verify strings in array field are initialized to a zero-length strings
    CPPUNIT_ASSERT("" == msgIn1.getBody()->getRecord()->getArray7()->getVariableLengthString1(0));
    CPPUNIT_ASSERT("" == msgIn1.getBody()->getRecord()->getArray7()->getVariableLengthString1(8));
    CPPUNIT_ASSERT("" == msgIn2.getBody()->getRecord()->getArray7()->getVariableLengthString1(0));
    CPPUNIT_ASSERT("" == msgIn2.getBody()->getRecord()->getArray7()->getVariableLengthString1(8));

     OUT_1 << "  [completed test (testConstructionDefaults)] " << std::endl;
  }

  void testSetGetOperations()
  {
    OUT_1 << std::endl << "  [executing test (testSetGetOperations)(TP_3.3.5.1)] " << std::endl;

    msgIn1.getHeader()->getHeaderRec()->setMessageID(5);
    CPPUNIT_ASSERT( msgIn1.getHeader()->getHeaderRec()->getMessageID() == 5 );
    msgIn1.getHeader()->getHeaderRec()->setMessageID(1);
    CPPUNIT_ASSERT( msgIn1.getHeader()->getHeaderRec()->getMessageID() == 1 );

    for (int i = 0; i < msgIn1.getBody()->getRecord()->getArray7()->getDimension1Size(); i++) {
        msgIn1.getBody()->getRecord()->getArray7()->setVariableLengthString1(i, "msg1 data");
        msgIn2.getBody()->getRecord()->getArray7()->setVariableLengthString1(i, "msg2 data");
    }

    for (int i = 0; i < msgIn1.getBody()->getRecord()->getArray7()->getDimension1Size(); i++) {
        CPPUNIT_ASSERT("msg1 data" == msgIn1.getBody()->getRecord()->getArray7()->getVariableLengthString1(i));
        CPPUNIT_ASSERT("msg2 data" == msgIn2.getBody()->getRecord()->getArray7()->getVariableLengthString1(i));
    }

    OUT_1 <<  "  [completed test (testSetGetOperations)] " << std::endl;

  }

    /**
     * produce a string of length stringLength by concatenating stringLength instances of dataValue cast
     * to a char.
     * @param dataValue
     * @param stringLength
     * @return
     */
   string buildEncodeTestString(int dataValue, int stringLength) {
        string resultString = "";

        for (int i = 0; i < stringLength; ++i) {
            resultString.append(1, (char) dataValue);
        }

        return resultString;
    }

  void testEncodeToSpec()
  {
        OUT_1 << std::endl << "  [executing test (testEncodeToSpec)(TP_3.3.5.2)] " << std::endl;

        unsigned char buff[1024];
        int i = 0;

        for (i = 0; i < 1024; i++)
        {
            buff[i] = 0;
        }

        msgIn1.getHeader()->getHeaderRec()->setMessageID(256);

        // array's length should be 9.
        // populate the array with 9 strings; first is 'A', second is 'BB', up to 'IIIIIIIII' (nine 'I's)
        for (i = 0; i < msgIn1.getBody()->getRecord()->getArray7()->getDimension1Size(); i++)
        {
            msgIn1.getBody()->getRecord()->getArray7()->setVariableLengthString1(i, buildEncodeTestString(65 + i, i + 1));
        }

        // verifying message level encode is AS-5684 compliant
        msgIn1.encode(buff);

        // offset 0 in buff is message ID, should be 256 (in an unsigned short)
        CPPUNIT_ASSERT(256 == ushortAtOffset(buff, 0));

        // offset 2 in buff is length of first string, should be 1 (in an unsigned short)
        CPPUNIT_ASSERT(1 == ushortAtOffset(buff, 2));

        // offset 4 in buff is single 'A' of first string, single byte UTF-8 character
        CPPUNIT_ASSERT(65 == buff[4]);

        // offset 5 = length of 2nd string, 2 in unsigned short.
        CPPUNIT_ASSERT(2 == ushortAtOffset(buff, 5));

        // offset 7 = 1st 'B' in 2nd string, single byte UTF-8 character
        CPPUNIT_ASSERT(66 == buff[7]);

        // offset 8 = 2nd 'B' in 2nd string
        CPPUNIT_ASSERT(66 == buff[8]);

        // offset 9 = length of 3rd string, 3 in unsigned short.
        CPPUNIT_ASSERT(3 == ushortAtOffset(buff, 9));

        // offset 11 = 1st 'C' in 3rd string
        CPPUNIT_ASSERT(67 == buff[11]);

        //        12 = 2nd 'C' in 3rd string
        CPPUNIT_ASSERT(67 == buff[12]);

        //        13 = 3rd 'C' in 3rd string
        CPPUNIT_ASSERT(67 == buff[13]);

        // skipping to 9th string, should be 9 'I's, length value at offset 54
        CPPUNIT_ASSERT(9 == ushortAtOffset(buff, 54));

        //        56 = 1st 'I' in 9th string
        CPPUNIT_ASSERT(73 == buff[56]);

        //        60 = 5th 'I' in 9th string
        CPPUNIT_ASSERT(73 == buff[60]);

        //        64 = 9th 'I' in 9th string
        CPPUNIT_ASSERT(73 == buff[64]);

        //        65 = past end of message encoding, should be 0.
        CPPUNIT_ASSERT(0 == buff[65]);

     OUT_1 << "  [completed test (testEncodeToSpec)] " << std::endl;

  }

  void testEncodeDecodeOperations()
  {
        unsigned char buff[1024];

        for (int i=0;i<1024;i++)
        {
            buff[i] = 0;
        }

        OUT_1 << std::endl << "  [executing test (testEncodeDecodeOperations)(TP_3.3.5.3)] " << std::endl;

        // verifying message level encode/decode reciprocity"
        msgIn1.getHeader()->getHeaderRec()->setMessageID(41);
        msgIn2.getHeader()->getHeaderRec()->setMessageID(0);
        for (int i = 0; i < msgIn1.getBody()->getRecord()->getArray7()->getDimension1Size(); i++)
        {
            msgIn1.getBody()->getRecord()->getArray7()->setVariableLengthString1(i, numToString(i));
            msgIn2.getBody()->getRecord()->getArray7()->setVariableLengthString1(i, numToString(42));
        }
        msgIn1.encode(buff);
        msgIn2.decode(buff);

        CPPUNIT_ASSERT(41 == msgIn2.getHeader()->getHeaderRec()->getMessageID());
        for (int i = 0; i < msgIn1.getBody()->getRecord()->getArray7()->getDimension1Size(); i++)
        {
            CPPUNIT_ASSERT(numToString(i) == msgIn2.getBody()->getRecord()->getArray7()->getVariableLengthString1(i));
        }

        // verify encoded header decodes properly

        msgIn2.getHeader()->getHeaderRec()->setMessageID(0);
        msgIn1.getHeader()->encode(buff);
        msgIn2.getHeader()->decode(buff);
        CPPUNIT_ASSERT(41 == msgIn2.getHeader()->getHeaderRec()->getMessageID());


        // verify encoded body decodes properly

        for (int i = 0; i < msgIn1.getBody()->getRecord()->getArray7()->getDimension1Size(); i++)
        {
            msgIn2.getBody()->getRecord()->getArray7()->setVariableLengthString1(i, "foo");
        }

        msgIn1.getBody()->encode(buff);
        msgIn2.getBody()->decode(buff);
        for (int i = 0; i < msgIn1.getBody()->getRecord()->getArray7()->getDimension1Size(); i++)
        {
            CPPUNIT_ASSERT(numToString(i) == msgIn2.getBody()->getRecord()->getArray7()->getVariableLengthString1(i));
        }

        for (int i = 0; i < msgIn1.getBody()->getRecord()->getArray7()->getDimension1Size(); i++)
        {
            msgIn2.getBody()->getRecord()->getArray7()->setVariableLengthString1(i, "foo");
        }

        msgIn1.getBody()->getRecord()->encode(buff);
        msgIn2.getBody()->getRecord()->decode(buff);
        for (int i = 0; i < msgIn1.getBody()->getRecord()->getArray7()->getDimension1Size(); i++)
        {
            CPPUNIT_ASSERT(numToString(i) == msgIn2.getBody()->getRecord()->getArray7()->getVariableLengthString1(i));
        }

        for (int i = 0; i < msgIn1.getBody()->getRecord()->getArray7()->getDimension1Size(); i++)
        {
            msgIn2.getBody()->getRecord()->getArray7()->setVariableLengthString1(i, "foo");
        }

        msgIn1.getBody()->getRecord()->getArray7()->encode(buff);
        msgIn2.getBody()->getRecord()->getArray7()->decode(buff);
        for (int i = 0; i < msgIn1.getBody()->getRecord()->getArray7()->getDimension1Size(); i++)
        {
            CPPUNIT_ASSERT(numToString(i) == msgIn2.getBody()->getRecord()->getArray7()->getVariableLengthString1(i));
        }

    OUT_1 << "  [completed test (testEncodeDecodeOperations)] " << std::endl;

  }

  void testEquality()
  {
    OUT_1 << std::endl << "  [executing test (testEquality)] " << std::endl;
    CPPUNIT_ASSERT( msgIn1 == msgIn2 );
    CPPUNIT_ASSERT( msgIn2 == msgIn1 );
    CPPUNIT_ASSERT( msgIn1 == msgIn1 );

    // change contents, still equal?
    msgIn1.getBody()->getRecord()->getArray7()->setVariableLengthString1(1, "newcontents!");
    CPPUNIT_ASSERT( !(msgIn1 == msgIn2) );
    CPPUNIT_ASSERT( !(msgIn2 == msgIn1) );

    OUT_1 << "  [completed test (testEquality)] " << std::endl;

  }

  void testInequality()
  {
    OUT_1 << std::endl << "  [executing test (testInequality)] " << std::endl;

    CPPUNIT_ASSERT( !(msgIn1 != msgIn2) );
    CPPUNIT_ASSERT( !(msgIn2 != msgIn1) );

    // change contents, should now be inequal
    msgIn1.getBody()->getRecord()->getArray7()->setVariableLengthString1(1, "newcontents!");

    CPPUNIT_ASSERT( (msgIn1 != msgIn2) );
    CPPUNIT_ASSERT( (msgIn2 != msgIn1) );

    OUT_1 << "  [completed test (testInequality)] " << std::endl;

  }

  static CppUnit::Test *suite()
  {
    CppUnit::TestSuite *suiteOfTests = new CppUnit::TestSuite( "JTSArray7MessageTest" );

    suiteOfTests->addTest( new CppUnit::TestCaller<JTSArray7MessageTest>(
                                   "testConstructionDefaults",
                                   &JTSArray7MessageTest::testConstructionDefaults ) );

    suiteOfTests->addTest( new CppUnit::TestCaller<JTSArray7MessageTest>(
                                   "testSetGetOperations (TP_3.3.5.1)",
                                   &JTSArray7MessageTest::testSetGetOperations ) );

    suiteOfTests->addTest( new CppUnit::TestCaller<JTSArray7MessageTest>(
                                   "testEncodeToSpec (TP_3.3.5.2)",
                                   &JTSArray7MessageTest::testEncodeToSpec ) );

    suiteOfTests->addTest( new CppUnit::TestCaller<JTSArray7MessageTest>(
                                   "testEncodeDecodeOperations (TP_3.3.5.3)",
                                   &JTSArray7MessageTest::testEncodeDecodeOperations ) );

    suiteOfTests->addTest( new CppUnit::TestCaller<JTSArray7MessageTest>(
                                   "testEquality",
                                   &JTSArray7MessageTest::testEquality ) );

    suiteOfTests->addTest( new CppUnit::TestCaller<JTSArray7MessageTest>(
                                   "testInequality",
                                   &JTSArray7MessageTest::testInequality ) );
    return suiteOfTests;
  }

};


int main( int ac, char **av )
{
  CppUnit::TextUi::TestRunner runner;
  runner.addTest( JTSArray7MessageTest::suite() );

  OUT_0 << std::endl << "------------------------------------------------" << std::endl;
  OUT_0 << "Executing suite JTSArray7MessageTest" << std::endl;
  runner.run();
  OUT_0 << std::endl << "Completed suite JTSArray7MessageTest" << std::endl;
  OUT_0 << "------------------------------------------------" << std::endl;
}

