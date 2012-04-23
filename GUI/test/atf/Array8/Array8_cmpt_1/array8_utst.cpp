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

// test for message structure with <array> of <bit_field>s

#include <iostream>

#include "urn_org_jts_test_Array8_1_0/Messages/MessageSet.h"
#include "cppunit/TestCaller.h"
#include "cppunit/TestCaller.h"
#include "cppunit/ui/text/TestRunner.h"
#include "cppunit/TestSuite.h"

using namespace urn_org_jts_test_Array8_1_0;

#define OUT_0  std::cout
#define OUT_1  if (bVerbose<=2) std::cout
#define OUT_2  if (bVerbose<=1) std::cout

unsigned short ushortAtOffset(unsigned char* buffer, unsigned int offset)
{
    // take address at given offset into buffer, treat pointer as unsigned short pointer, then dereference an
    // unsigned short.
    return *((unsigned short*)&(buffer[offset]));
}

unsigned short rgbToUnsignedShortBitfield(unsigned short R, unsigned short G, unsigned short B)
{
    return (B << 11) | (G << 5) | (R);
}

class JTSArray8MessageTest : public CppUnit::TestFixture {

private:
  MsgIn m_MsgIn1,m_MsgIn2;
  int bVerbose;

public:
  JTSArray8MessageTest() {
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
    CPPUNIT_ASSERT( 1 == m_MsgIn1.getHeader()->getHeaderRec()->getMessageID());
    CPPUNIT_ASSERT( 1 == m_MsgIn2.getHeader()->getHeaderRec()->getMessageID());

    OUT_2 << "     --- verifying MsgIn constructs array with correct default DimensionSize" << std::endl;
    CPPUNIT_ASSERT( 2 == m_MsgIn1.getBody()->getRecord()->getArray8()->getXSize());
    CPPUNIT_ASSERT( 4 == m_MsgIn1.getBody()->getRecord()->getArray8()->getYSize());
    CPPUNIT_ASSERT( 2 == m_MsgIn2.getBody()->getRecord()->getArray8()->getXSize());
    CPPUNIT_ASSERT( 4 == m_MsgIn2.getBody()->getRecord()->getArray8()->getYSize());

    OUT_2 << "     --- verifying MsgIn bit fields with correct zero starting value." << std::endl;
    CPPUNIT_ASSERT( 0 == m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(1, 3)->getR());
    CPPUNIT_ASSERT( 0 == m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(1, 3)->getG());
    CPPUNIT_ASSERT( 0 == m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(1, 3)->getB());
    CPPUNIT_ASSERT( 0 == m_MsgIn2.getBody()->getRecord()->getArray8()->getColorField(1, 3)->getR());
    CPPUNIT_ASSERT( 0 == m_MsgIn2.getBody()->getRecord()->getArray8()->getColorField(1, 3)->getG());
    CPPUNIT_ASSERT( 0 == m_MsgIn2.getBody()->getRecord()->getArray8()->getColorField(1, 3)->getB());

    OUT_1 << "  [completed test (testConstructionDefaults)] " << std::endl;
  }

  void testSetGetOperations()
  {
    OUT_1 << std::endl << "  [executing test (testSetGetOperations)(TP_3.3.5.1)] " << std::endl;
    
    OUT_2 << "     --- verifying setMessageID/getMessageID reciprocity" << std::endl;
    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(5);
    CPPUNIT_ASSERT( m_MsgIn1.getHeader()->getHeaderRec()->getMessageID() == 5 );
    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(1);
    CPPUNIT_ASSERT( m_MsgIn1.getHeader()->getHeaderRec()->getMessageID() == 1 );

    OUT_2 << "     --- verifying color field get/set reciprocity" << std::endl;

    for (int row = 0; row < m_MsgIn1.getBody()->getRecord()->getArray8()->getYSize(); ++row)
    {
        for (int col = 0; col < m_MsgIn1.getBody()->getRecord()->getArray8()->getXSize(); ++col)
        {
            m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(col, row)->setR(31);
            m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(col, row)->setG(63);
            m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(col, row)->setB(31);
        }
    }

    for (int row = 0; row < m_MsgIn2.getBody()->getRecord()->getArray8()->getYSize(); ++row)
    {
        for (int col = 0; col < m_MsgIn2.getBody()->getRecord()->getArray8()->getXSize(); ++col)
        {
            m_MsgIn2.getBody()->getRecord()->getArray8()->getColorField(col, row)->setR(20);
            m_MsgIn2.getBody()->getRecord()->getArray8()->getColorField(col, row)->setG(31);
            m_MsgIn2.getBody()->getRecord()->getArray8()->getColorField(col, row)->setB(20);
        }
    }

    for (int row = 0; row < m_MsgIn1.getBody()->getRecord()->getArray8()->getYSize(); ++row)
    {
        for (int col = 0; col < m_MsgIn1.getBody()->getRecord()->getArray8()->getXSize(); ++col)
        {
            CPPUNIT_ASSERT(31 == m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(col, row)->getR());
            CPPUNIT_ASSERT(63 == m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(col, row)->getG());
            CPPUNIT_ASSERT(31 == m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(col, row)->getB());
        }
    }

    for (int row = 0; row < m_MsgIn2.getBody()->getRecord()->getArray8()->getYSize(); ++row)
    {
        for (int col = 0; col < m_MsgIn2.getBody()->getRecord()->getArray8()->getXSize(); ++col)
        {
            CPPUNIT_ASSERT(20 == m_MsgIn2.getBody()->getRecord()->getArray8()->getColorField(col, row)->getR());
            CPPUNIT_ASSERT(31 == m_MsgIn2.getBody()->getRecord()->getArray8()->getColorField(col, row)->getG());
            CPPUNIT_ASSERT(20 == m_MsgIn2.getBody()->getRecord()->getArray8()->getColorField(col, row)->getB());
        }
    }

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

    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(99);

    // at X = 0, Y = 0 in array, RGB = (5, 10, 5)
    m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(0, 0)->setR(5);
    m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(0, 0)->setG(10);
    m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(0, 0)->setB(5);

    // at 1 = 3, Y = 0 in array, RGB = (13, 27, 2)
    m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(1, 0)->setR(13);
    m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(1, 0)->setG(27);
    m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(1, 0)->setB(2);

    // at X = 0, Y = 3 in array, RGB = (31,63,9)
    m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(0, 3)->setR(31);
    m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(0, 3)->setG(63);
    m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(0, 3)->setB(9);

    // at X = 1, Y = 3 in array, RGB = (0,35,15)
    m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(1, 3)->setR(0);
    m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(1, 3)->setG(35);
    m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(1, 3)->setB(15);

    m_MsgIn1.encode(buff);

    // offset 0   : unsigned short with message ID
    CPPUNIT_ASSERT(99 == ushortAtOffset(buff, 0));
    // offset 2   : start of array data, unsigned short at x = 0, y = 0
    CPPUNIT_ASSERT(rgbToUnsignedShortBitfield(5,  10, 5) == ushortAtOffset(buff, 2 + 0));
    // offset 4   : 2nd entry in array data, unsigned short at x = 1, y = 0
    CPPUNIT_ASSERT(rgbToUnsignedShortBitfield(13, 27, 2) == ushortAtOffset(buff, 2 + 2));
    // offset 14   : 7th entry in array data, unsigned short at x = 0, y = 3
    CPPUNIT_ASSERT(rgbToUnsignedShortBitfield(31, 63, 9) == ushortAtOffset(buff, 2 + 12));
    // offset 16   : 8th entry in array data, unsigned short at x = 1, y = 3
    CPPUNIT_ASSERT(rgbToUnsignedShortBitfield(0, 35, 15) == ushortAtOffset(buff, 2 + 14));

    // offset 18   : 0, past end of encoded message!
    CPPUNIT_ASSERT(0 == buff[18]);

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

    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(1);

    for (int row = 0; row < m_MsgIn1.getBody()->getRecord()->getArray8()->getYSize(); ++row)
    {
        for (int col = 0; col < m_MsgIn1.getBody()->getRecord()->getArray8()->getXSize(); ++col)
        {

            m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(col, row)->setR(31);
            m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(col, row)->setG(63);
            m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(col, row)->setB(31);
        }
    }

    m_MsgIn2.getHeader()->getHeaderRec()->setMessageID(999);

    for (int row = 0; row < m_MsgIn2.getBody()->getRecord()->getArray8()->getYSize(); ++row)
    {
        for (int col = 0; col < m_MsgIn2.getBody()->getRecord()->getArray8()->getXSize(); ++col)
        {
            m_MsgIn2.getBody()->getRecord()->getArray8()->getColorField(col, row)->setR(0);
            m_MsgIn2.getBody()->getRecord()->getArray8()->getColorField(col, row)->setG(0);
            m_MsgIn2.getBody()->getRecord()->getArray8()->getColorField(col, row)->setB(0);
        }
    }

    m_MsgIn1.encode(buff);

    // decode contents of msg1 into msg2.  fields of msg2 should now match fields of msg1.

    m_MsgIn2.decode(buff);

    CPPUNIT_ASSERT( m_MsgIn1.getHeader()->getHeaderRec()->getMessageID()
            == m_MsgIn2.getHeader()->getHeaderRec()->getMessageID());

    for (int row = 0; row < m_MsgIn2.getBody()->getRecord()->getArray8()->getYSize(); ++row)
    {
        for (int col = 0; col < m_MsgIn2.getBody()->getRecord()->getArray8()->getXSize(); ++col)
        {
            CPPUNIT_ASSERT(m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(col, row)->getR() == m_MsgIn2.getBody()->getRecord()->getArray8()->getColorField(col, row)->getR());
            CPPUNIT_ASSERT(m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(col, row)->getG() == m_MsgIn2.getBody()->getRecord()->getArray8()->getColorField(col, row)->getG());
            CPPUNIT_ASSERT(m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(col, row)->getB() == m_MsgIn2.getBody()->getRecord()->getArray8()->getColorField(col, row)->getB());
        }
    }

    // change msg1's message ID..
    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(9000);

    // change contents of msg2's array, encode the array *alone* into the buffer, then decode
    // the buffer into msg1's array.
    for (int row = 0; row < m_MsgIn2.getBody()->getRecord()->getArray8()->getYSize(); ++row)
    {
        for (int col = 0; col < m_MsgIn2.getBody()->getRecord()->getArray8()->getXSize(); ++col)
        {
            m_MsgIn2.getBody()->getRecord()->getArray8()->getColorField(col, row)->setR(15);
            m_MsgIn2.getBody()->getRecord()->getArray8()->getColorField(col, row)->setG(8);
            m_MsgIn2.getBody()->getRecord()->getArray8()->getColorField(col, row)->setB(4);
        }
    }

    m_MsgIn2.getBody()->getRecord()->getArray8()->encode(buff);
    m_MsgIn1.getBody()->getRecord()->getArray8()->decode(buff);
    
    // since the encode/decode only touched the array, msg1's message ID should still be 9000!
    CPPUNIT_ASSERT(9000 == m_MsgIn1.getHeader()->getHeaderRec()->getMessageID());

    // the R/G/B values for msg1's array should now match what was set in msg2's array.
    for (int row = 0; row < m_MsgIn1.getBody()->getRecord()->getArray8()->getYSize(); ++row)
    {
        for (int col = 0; col < m_MsgIn1.getBody()->getRecord()->getArray8()->getXSize(); ++col)
        {
            CPPUNIT_ASSERT(15 == m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(col, row)->getR());
            CPPUNIT_ASSERT(8 == m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(col, row)->getG());
            CPPUNIT_ASSERT(4 == m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(col, row)->getB());
        }
    }

    OUT_1 << "  [completed test (testEncodeDecodeOperations)] " << std::endl;

  }

  void testEquality()
  {
    OUT_1 << std::endl << "  [executing test (testEquality)] " << std::endl;
    OUT_2 << "     --- verifying message equality operator" << std::endl;
    CPPUNIT_ASSERT( m_MsgIn1 == m_MsgIn2 );
    CPPUNIT_ASSERT( m_MsgIn2 == m_MsgIn1 );
    CPPUNIT_ASSERT( m_MsgIn1 == m_MsgIn1 );

    // change contents, still equal?
    m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(0, 0)->setR(31);
    m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(0, 0)->setG(63);
    m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(0, 0)->setB(31);

    CPPUNIT_ASSERT( !(m_MsgIn1 == m_MsgIn2) );
    CPPUNIT_ASSERT( !(m_MsgIn2 == m_MsgIn1) );

    OUT_1 << "  [completed test (testEquality)] " << std::endl;
  }

  void testInequality()
  {
    OUT_1 << std::endl << "  [executing test (testInequality)] " << std::endl;
    OUT_2 << "     --- verifying message inequality operator" << std::endl;

    CPPUNIT_ASSERT( !(m_MsgIn1 != m_MsgIn2) );
    CPPUNIT_ASSERT( !(m_MsgIn2 != m_MsgIn1) );

    // change contents, should now be inequal
    m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(0, 0)->setR(31);
    m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(0, 0)->setG(63);
    m_MsgIn1.getBody()->getRecord()->getArray8()->getColorField(0, 0)->setB(31);

    CPPUNIT_ASSERT( (m_MsgIn1 != m_MsgIn2) );
    CPPUNIT_ASSERT( (m_MsgIn2 != m_MsgIn1) );

    OUT_1 << "  [completed test (testInequality)] " << std::endl;
  }

  static CppUnit::Test *suite()
  {
    CppUnit::TestSuite *suiteOfTests = new CppUnit::TestSuite( "JTSArray8MessageTest" );

    suiteOfTests->addTest( new CppUnit::TestCaller<JTSArray8MessageTest>(
                                   "testConstructionDefaults",
                                   &JTSArray8MessageTest::testConstructionDefaults ) );

    suiteOfTests->addTest( new CppUnit::TestCaller<JTSArray8MessageTest>(
                                   "testSetGetOperations (TP_3.3.5.1)",
                                   &JTSArray8MessageTest::testSetGetOperations ) );
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSArray8MessageTest>(
                                   "testEncodeToSpec (TP_3.3.5.2)",
                                   &JTSArray8MessageTest::testEncodeToSpec ) );
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSArray8MessageTest>(
                                   "testEncodeDecodeOperations (TP_3.3.5.3)",
                                   &JTSArray8MessageTest::testEncodeDecodeOperations ) );

    suiteOfTests->addTest( new CppUnit::TestCaller<JTSArray8MessageTest>(
                                   "testEquality",
                                   &JTSArray8MessageTest::testEquality ) );

    suiteOfTests->addTest( new CppUnit::TestCaller<JTSArray8MessageTest>(
                                   "testInequality",
                                   &JTSArray8MessageTest::testInequality ) );

     return suiteOfTests;
  }
};



int main( int ac, char **av )
{
  CppUnit::TextUi::TestRunner runner;
  runner.addTest( JTSArray8MessageTest::suite() );

  OUT_0 << std::endl << "------------------------------------------------" << std::endl;
  OUT_0 << "Executing suite JTSArray8MessageTest" << std::endl;
  runner.run();
  OUT_0 << std::endl << "Completed suite JTSArray8MessageTest" << std::endl;
  OUT_0 << "------------------------------------------------" << std::endl;
}

