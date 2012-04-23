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

#include "urn_org_jts_test_Variant2_1_0/Messages/MessageSet.h"

#include "cppunit/TestCaller.h"
#include "cppunit/TestCaller.h"
#include "cppunit/ui/text/TestRunner.h"
#include "cppunit/TestSuite.h"

using namespace urn_org_jts_test_Variant2_1_0;

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

class JTSVariant2MessageTest : public CppUnit::TestFixture {
             
private:
  MsgIn msgIn1,msgIn2;
  int bVerbose;
  
public:
  JTSVariant2MessageTest() {
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
    CPPUNIT_ASSERT(1 == msgIn1.getHeader()->getHeaderRec()->getMessageID());
    CPPUNIT_ASSERT(1 == msgIn2.getHeader()->getHeaderRec()->getMessageID());
    
    
    // check vtag fields are assigned 0 initially
    CPPUNIT_ASSERT(0 == msgIn1.getBody()->getVariantTop()->getFieldValue());
    CPPUNIT_ASSERT(0 == msgIn1.getBody()->getVariantTop()->getVariantA()->getFieldValue());
    CPPUNIT_ASSERT(0 == msgIn1.getBody()->getVariantTop()->getVariantB()->getFieldValue());
    CPPUNIT_ASSERT(0 == msgIn2.getBody()->getVariantTop()->getFieldValue());
    CPPUNIT_ASSERT(0 == msgIn2.getBody()->getVariantTop()->getVariantA()->getFieldValue());
    CPPUNIT_ASSERT(0 == msgIn2.getBody()->getVariantTop()->getVariantB()->getFieldValue());
    
    // check all fields are 0 intially.
    CPPUNIT_ASSERT(0 == msgIn1.getBody()->getVariantTop()->getVariantA()->getRecA1()->getFieldA1());
    CPPUNIT_ASSERT(0 == msgIn1.getBody()->getVariantTop()->getVariantA()->getRecA2()->getFieldA2());
    CPPUNIT_ASSERT(0 == msgIn1.getBody()->getVariantTop()->getVariantB()->getRecB1()->getFieldB1());
    CPPUNIT_ASSERT(0 == msgIn1.getBody()->getVariantTop()->getVariantB()->getRecB2()->getFieldB2());
    CPPUNIT_ASSERT(0 == msgIn2.getBody()->getVariantTop()->getVariantA()->getRecA1()->getFieldA1());
    CPPUNIT_ASSERT(0 == msgIn2.getBody()->getVariantTop()->getVariantA()->getRecA2()->getFieldA2());
    CPPUNIT_ASSERT(0 == msgIn2.getBody()->getVariantTop()->getVariantB()->getRecB1()->getFieldB1());
    CPPUNIT_ASSERT(0 == msgIn2.getBody()->getVariantTop()->getVariantB()->getRecB2()->getFieldB2());

    OUT_1 <<  "  [completed test (testSetGetOperations)] " << std::endl;
  }
  
  void testSetGetOperations()
  {
    OUT_1 << std::endl << "  [executing test (testSetGetOperations)(TP_3.3.5.1)] " << std::endl;
    
    msgIn1.getHeader()->getHeaderRec()->setMessageID(5);
    CPPUNIT_ASSERT( msgIn1.getHeader()->getHeaderRec()->getMessageID() == 5 );
    msgIn1.getHeader()->getHeaderRec()->setMessageID(1);
    CPPUNIT_ASSERT( msgIn1.getHeader()->getHeaderRec()->getMessageID() == 1 );

    // check vtag field changes are recorded properly
    msgIn1.getBody()->getVariantTop()->setFieldValue(1);
    CPPUNIT_ASSERT(1 == msgIn1.getBody()->getVariantTop()->getFieldValue());
    msgIn1.getBody()->getVariantTop()->getVariantA()->setFieldValue(1);
    CPPUNIT_ASSERT(1 == msgIn1.getBody()->getVariantTop()->getVariantA()->getFieldValue());
    msgIn1.getBody()->getVariantTop()->getVariantB()->setFieldValue(1);
    CPPUNIT_ASSERT(1 == msgIn1.getBody()->getVariantTop()->getVariantB()->getFieldValue());

    // check field changes are recorded properly
    msgIn1.getBody()->getVariantTop()->getVariantA()->getRecA1()->setFieldA1(99);
    CPPUNIT_ASSERT(99 == msgIn1.getBody()->getVariantTop()->getVariantA()->getRecA1()->getFieldA1());
    msgIn1.getBody()->getVariantTop()->getVariantA()->getRecA2()->setFieldA2(88);
    CPPUNIT_ASSERT(88 == msgIn1.getBody()->getVariantTop()->getVariantA()->getRecA2()->getFieldA2());
    msgIn1.getBody()->getVariantTop()->getVariantB()->getRecB1()->setFieldB1(77);
    CPPUNIT_ASSERT(77 == msgIn1.getBody()->getVariantTop()->getVariantB()->getRecB1()->getFieldB1());
    msgIn1.getBody()->getVariantTop()->getVariantB()->getRecB2()->setFieldB2(66);
    CPPUNIT_ASSERT(66 == msgIn1.getBody()->getVariantTop()->getVariantB()->getRecB2()->getFieldB2());
    
    // chech getsize() returns accurate value
    // 2-byte header, 2 1-byte vtag fields, 1 2-byte unsigned short
    CPPUNIT_ASSERT(2+1+1+2 == msgIn1.getSize());
        
    OUT_1 <<  "  [completed test (testSetGetOperations)] " << std::endl;
  }

  void testEncodeToSpec()
  {
    unsigned char buff[1024];
    int i;

    for (i=0;i<1024;i++) 
    {
       buff[i] = 0;
    }
       
    OUT_1 << std::endl << "  [executing test (testEncodeToSpec)(TP_3.3.5.2)] " << std::endl;
    
    // fill msg1, select VariantA, RecA1 as active, encode.
    msgIn1.getHeader()->getHeaderRec()->setMessageID(500);

    msgIn1.getBody()->getVariantTop()->setFieldValue(0);
    msgIn1.getBody()->getVariantTop()->getVariantA()->setFieldValue(0);
    msgIn1.getBody()->getVariantTop()->getVariantA()->getRecA1()->setFieldA1(42);
    
    msgIn1.encode(buff);
    
    // verify encoding matches expectations from spec document.
    
    // offset 0:   unsigned short w/ message ID
    CPPUNIT_ASSERT(500 == ushortAtOffset(buff, 0));

    // offset 2:   unsigned char w/ VariantTop's vtag field
    CPPUNIT_ASSERT(0 == buff[2]);
    
    // offset 3:   unsigned char w/ VariantA's vtag field
    CPPUNIT_ASSERT(0 == buff[3]);
    
    // offset 4:   unsigned short w/ message ID
    CPPUNIT_ASSERT(42 == ushortAtOffset(buff, 4));
    
    // fill msg1, select VariantB, RecB1 as active
    msgIn1.getHeader()->getHeaderRec()->setMessageID(600);

    msgIn1.getBody()->getVariantTop()->setFieldValue(1);
    msgIn1.getBody()->getVariantTop()->getVariantB()->setFieldValue(0);
    msgIn1.getBody()->getVariantTop()->getVariantB()->getRecB1()->setFieldB1(3456);
    
    msgIn1.encode(buff);
    
    // verify encoding matches expectations from spec document
    // offset 0:   unsigned short w/ message ID
    CPPUNIT_ASSERT(600 == ushortAtOffset(buff, 0));

    // offset 2:   unsigned char w/ VariantTop's vtag field
    CPPUNIT_ASSERT(1 == buff[2]);
    
    // offset 3:   unsigned char w/ VariantA's vtag field
    CPPUNIT_ASSERT(0 == buff[3]);
    
    // offset 4:   unsigned short w/ message ID
    CPPUNIT_ASSERT(3456 == ushortAtOffset(buff, 4));
    
    // offset 6: 0, past end of message data.
    

    OUT_1 << "  [completed test (testEncodeToSpec)] " << std::endl;
       
  }
  
  void testEncodeDecodeOperations()
  {
    unsigned char buff[1024];
    int i;
    
    for (i=0;i<1024;i++) 
    {
       buff[i] = 0;
    }
    
    OUT_1 << std::endl << "  [executing test (testEncodeDecodeOperations)(TP_3.3.5.3)] " << std::endl;
    
    // fill msg1, select VariantA, RecA1 as active.
    msgIn1.getHeader()->getHeaderRec()->setMessageID(500);

    msgIn1.getBody()->getVariantTop()->setFieldValue(0);
    msgIn1.getBody()->getVariantTop()->getVariantA()->setFieldValue(0);
    msgIn1.getBody()->getVariantTop()->getVariantA()->getRecA1()->setFieldA1(19);
    msgIn1.getBody()->getVariantTop()->getVariantB()->getRecB2()->setFieldB2(0);

    // fill msg2, select VariantB, RecB2 as active.
    msgIn2.getHeader()->getHeaderRec()->setMessageID(1000);

    msgIn2.getBody()->getVariantTop()->setFieldValue(1);
    msgIn2.getBody()->getVariantTop()->getVariantB()->setFieldValue(1);
    msgIn2.getBody()->getVariantTop()->getVariantB()->getRecB2()->setFieldB2(29);
    
    // encode/decode msg2 into msg1.  msg1 should now show active VariantB, RecB2; verify field contents are
    // correct.
    
    msgIn2.encode(buff);
    msgIn1.decode(buff);
    
    CPPUNIT_ASSERT(1000 == msgIn1.getHeader()->getHeaderRec()->getMessageID());
    CPPUNIT_ASSERT(1 == msgIn1.getBody()->getVariantTop()->getFieldValue());
    CPPUNIT_ASSERT(1 == msgIn1.getBody()->getVariantTop()->getVariantB()->getFieldValue());
    CPPUNIT_ASSERT(29 == msgIn1.getBody()->getVariantTop()->getVariantB()->getRecB2()->getFieldB2());
    
    // change the msg ID on msg1 and fill new values in msg1.
    // fill msg1, select VariantA, RecA1 as active.
    msgIn1.getHeader()->getHeaderRec()->setMessageID(1999);

    msgIn1.getBody()->getVariantTop()->setFieldValue(0);
    msgIn1.getBody()->getVariantTop()->getVariantA()->setFieldValue(0);
    msgIn1.getBody()->getVariantTop()->getVariantA()->getRecA1()->setFieldA1(0);
    msgIn1.getBody()->getVariantTop()->getVariantA()->getRecA2()->setFieldA2(0);
    msgIn1.getBody()->getVariantTop()->getVariantB()->setFieldValue(0);
    msgIn1.getBody()->getVariantTop()->getVariantB()->getRecB1()->setFieldB1(0);
    msgIn1.getBody()->getVariantTop()->getVariantB()->getRecB2()->setFieldB2(0);
    
    // encode/decode msg2's VariantTop into msg1.  msg1 should now show changed variant, but keep the msg ID.
    msgIn2.getBody()->getVariantTop()->encode(buff);
    msgIn1.getBody()->getVariantTop()->decode(buff);

    CPPUNIT_ASSERT(1999 == msgIn1.getHeader()->getHeaderRec()->getMessageID());
    CPPUNIT_ASSERT(1 == msgIn1.getBody()->getVariantTop()->getFieldValue());
    CPPUNIT_ASSERT(0 == msgIn1.getBody()->getVariantTop()->getVariantA()->getFieldValue());
    CPPUNIT_ASSERT(1 == msgIn1.getBody()->getVariantTop()->getVariantB()->getFieldValue());
    CPPUNIT_ASSERT(29 == msgIn1.getBody()->getVariantTop()->getVariantB()->getRecB2()->getFieldB2());
        
    // set msg1's VariantTop so VariantA is active, also make RecA2 active inside.
    //  Also make VariantA active in msg2, but make RecA1 active.
    msgIn1.getBody()->getVariantTop()->setFieldValue(0);
    msgIn1.getBody()->getVariantTop()->getVariantA()->setFieldValue(1);
    msgIn1.getBody()->getVariantTop()->getVariantA()->getRecA2()->setFieldA2(9000);
    msgIn1.getBody()->getVariantTop()->getVariantA()->getRecA1()->setFieldA1(0);
    
    msgIn2.getBody()->getVariantTop()->setFieldValue(0);
    msgIn2.getBody()->getVariantTop()->getVariantA()->setFieldValue(0);
    msgIn2.getBody()->getVariantTop()->getVariantA()->getRecA1()->setFieldA1(4500);
    
    // encode/decode msg2's VariantA into msg1.  verify msg1 shows contents from msgIn2.
    msgIn2.getBody()->getVariantTop()->getVariantA()->encode(buff);
    msgIn1.getBody()->getVariantTop()->getVariantA()->decode(buff);
    
    CPPUNIT_ASSERT(0 == msgIn1.getBody()->getVariantTop()->getFieldValue());
    CPPUNIT_ASSERT(0 == msgIn1.getBody()->getVariantTop()->getVariantA()->getFieldValue());
    CPPUNIT_ASSERT(4500 == msgIn1.getBody()->getVariantTop()->getVariantA()->getRecA1()->getFieldA1());

    OUT_1 << "  [completed test (testEncodeDecodeOperations)] " << std::endl;
       
  }

  void testEquality()
  {
    OUT_1 << std::endl << "  [executing test (testEquality)] " << std::endl;
    OUT_2 << "     --- verifying message equality operator" << std::endl;    
    CPPUNIT_ASSERT( msgIn1 == msgIn2 );
    OUT_1 << "  [completed test (testEquality)] " << std::endl;
    
  }
  
  void testInequality()
  {
    OUT_1 << std::endl << "  [executing test (testInequality)] " << std::endl;
    OUT_2 << "     --- verifying message inequality operator" << std::endl;    
       
    CPPUNIT_ASSERT( !(msgIn1 != msgIn2) );
    OUT_1 << "  [completed test (testInequality)] " << std::endl;
    
  }

  static CppUnit::Test *suite()
  {
    CppUnit::TestSuite *suiteOfTests = new CppUnit::TestSuite( "JTSVariant2MessageTest" );
    
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSVariant2MessageTest>( 
                                   "testConstructionDefaults", 
                                   &JTSVariant2MessageTest::testConstructionDefaults ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSVariant2MessageTest>( 
                                   "testSetGetOperations (TP_3.3.5.1)", 
                                   &JTSVariant2MessageTest::testSetGetOperations ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSVariant2MessageTest>( 
                                   "testEncodeToSpec (TP_3.3.5.2)", 
                                   &JTSVariant2MessageTest::testEncodeToSpec ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSVariant2MessageTest>( 
                                   "testEncodeDecodeOperations (TP_3.3.5.3)", 
                                   &JTSVariant2MessageTest::testEncodeDecodeOperations ) );                                   
                                                                      
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSVariant2MessageTest>( 
                                   "testEquality", 
                                   &JTSVariant2MessageTest::testEquality ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSVariant2MessageTest>(
                                   "testInequality",
                                   &JTSVariant2MessageTest::testInequality ) );
    return suiteOfTests;
  }

};


int main( int ac, char **av )
{
  CppUnit::TextUi::TestRunner runner;
  runner.addTest( JTSVariant2MessageTest::suite() );
  
  OUT_0 << std::endl << "------------------------------------------------" << std::endl;
  OUT_0 << "Executing suite JTSVariant2MessageTest" << std::endl;
  runner.run();
  OUT_0 << std::endl << "Completed suite JTSVariant2MessageTest" << std::endl;
  OUT_0 << "------------------------------------------------" << std::endl;  
}

