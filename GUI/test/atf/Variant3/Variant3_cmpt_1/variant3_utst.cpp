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

#include "urn_org_jts_test_Variant3_1_0/Messages/MessageSet.h"

#include "cppunit/TestCaller.h"
#include "cppunit/TestCaller.h"
#include "cppunit/ui/text/TestRunner.h"
#include "cppunit/TestSuite.h"

using namespace urn_org_jts_test_Variant3_1_0;

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

class JTSVariant3MessageTest : public CppUnit::TestFixture {
             
private:
  MsgIn msgIn1,msgIn2;
  int bVerbose;
  
public:
  JTSVariant3MessageTest() {
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
    CPPUNIT_ASSERT( msgIn1.getHeader()->getHeaderRec()->getMessageID() == 1 );
    CPPUNIT_ASSERT( msgIn2.getHeader()->getHeaderRec()->getMessageID() == 1 );

	// check vtags are assigned 0 initially
    CPPUNIT_ASSERT(0 == msgIn1.getBody()->getVariantTop()->getFieldValue());
    CPPUNIT_ASSERT(0 == msgIn2.getBody()->getVariantTop()->getFieldValue());

    // check lists are empty initially
    CPPUNIT_ASSERT(0 == msgIn2.getBody()->getVariantTop()->getListA()->getNumberOfElements());
    CPPUNIT_ASSERT(0 == msgIn2.getBody()->getVariantTop()->getListB()->getNumberOfElements());

    OUT_1 << std::endl << "  [completed test testConstructionDefaults] " << std::endl;
  }
  
  void testSetGetOperations()
  {
    OUT_1 << std::endl << "  [executing test (testSetGetOperations)(TP_3.3.5.1)] " << std::endl;
    
    // check header field changes are recorded properly
    msgIn1.getHeader()->getHeaderRec()->setMessageID(5);
    CPPUNIT_ASSERT(5 == msgIn1.getHeader()->getHeaderRec()->getMessageID());
    msgIn1.getHeader()->getHeaderRec()->setMessageID(1);
    CPPUNIT_ASSERT(1 == msgIn1.getHeader()->getHeaderRec()->getMessageID());

    // check vtag field changes are recorded properly
    msgIn1.getBody()->getVariantTop()->setFieldValue(1);
    CPPUNIT_ASSERT(1 == msgIn1.getBody()->getVariantTop()->getFieldValue());
    msgIn1.getBody()->getVariantTop()->setFieldValue(0);
    CPPUNIT_ASSERT(0 == msgIn1.getBody()->getVariantTop()->getFieldValue());
    
    OUT_1 <<  "  [completed test (testSetGetOperations)] " << std::endl;
    
    // add elements (records with unsigned shorts) to ListA.
    for (unsigned short i = 0; i < 10; ++i)
    {
       MsgIn::Body::VariantTop::ListA::RecA1 temprec;
       temprec.setFieldA1(10+i);
       msgIn1.getBody()->getVariantTop()->getListA()->addElement(temprec);
    }
    
    // verify new contents were stored 
    CPPUNIT_ASSERT(10 == msgIn1.getBody()->getVariantTop()->getListA()->getNumberOfElements());

    for (unsigned short i = 0; i < 10; ++i)
    {
       CPPUNIT_ASSERT(10+i == msgIn1.getBody()->getVariantTop()->getListA()->getElement(i)->getFieldA1());
    }
    
    // try changing and deleting some elements and ensure expected results occur.
    MsgIn::Body::VariantTop::ListA& list_alias = (*msgIn1.getBody()->getVariantTop()->getListA()); // less typing
    
    list_alias.deleteElement(1);
    list_alias.deleteElement(1);
    list_alias.deleteLastElement();
    list_alias.deleteLastElement();
    
    CPPUNIT_ASSERT(6 == list_alias.getNumberOfElements());
    
    CPPUNIT_ASSERT(10 == list_alias.getElement(0)->getFieldA1());
    CPPUNIT_ASSERT(13 == list_alias.getElement(1)->getFieldA1());
    CPPUNIT_ASSERT(14 == list_alias.getElement(2)->getFieldA1());
    CPPUNIT_ASSERT(15 == list_alias.getElement(3)->getFieldA1());
    CPPUNIT_ASSERT(16 == list_alias.getElement(4)->getFieldA1());
    CPPUNIT_ASSERT(17 == list_alias.getElement(5)->getFieldA1());
    
    MsgIn::Body::VariantTop::ListA::RecA1 rec1;
    MsgIn::Body::VariantTop::ListA::RecA1 rec2;
    MsgIn::Body::VariantTop::ListA::RecA1 rec3;
    rec1.setFieldA1(99);
    rec2.setFieldA1(199);
    rec3.setFieldA1(299);
    
    list_alias.setElement(5, rec1);
    list_alias.addElement(rec2);
    list_alias.addElement(rec3);

    CPPUNIT_ASSERT(10 == list_alias.getElement(0)->getFieldA1());
    CPPUNIT_ASSERT(13 == list_alias.getElement(1)->getFieldA1());
    CPPUNIT_ASSERT(14 == list_alias.getElement(2)->getFieldA1());
    CPPUNIT_ASSERT(15 == list_alias.getElement(3)->getFieldA1());
    CPPUNIT_ASSERT(16 == list_alias.getElement(4)->getFieldA1());
    CPPUNIT_ASSERT(99 == list_alias.getElement(5)->getFieldA1());
    CPPUNIT_ASSERT(199 == list_alias.getElement(6)->getFieldA1());
    CPPUNIT_ASSERT(299 == list_alias.getElement(7)->getFieldA1());
        
    // check size is correct
    // 2-byte header, 1 1-byte vtag field, 1 1-byte count field, 8 2-byte store shorts
    CPPUNIT_ASSERT(2 + 1 + 1 + 2*8 == msgIn1.getSize());
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
    
    msgIn1.getHeader()->getHeaderRec()->setMessageID(500);
    msgIn1.getBody()->getVariantTop()->setFieldValue(1);
    
    // fill list and encode into buff
    for (unsigned short i = 0; i < 10; ++i)
    {
       MsgIn::Body::VariantTop::ListB::RecB1 temprec;
       temprec.setFieldB1(20+i);
       msgIn1.getBody()->getVariantTop()->getListB()->addElement(temprec);
    }
    
    msgIn1.encode(buff);
    
    // verify encoding matches expectations from spec document.
    
    // offset 0:   unsigned short w/ message ID
    CPPUNIT_ASSERT(500 == ushortAtOffset(buff, 0));

    // offset 2:   unsigned char w/ VariantTop's vtag field
    CPPUNIT_ASSERT(1 == buff[2]);
    
    // offset 3:   unsigned char w/ ListB's length
    CPPUNIT_ASSERT(10 == buff[3]);
    
    int offset = 4; // list data starts at offset 4, ends at offset 22.
    for (unsigned short i = 0; i < 10; ++i)
    {
        CPPUNIT_ASSERT(20 + i == ushortAtOffset(buff, offset));
        offset += 2;
    }
    
    // byte 23 should be 0, past end of message data.
    CPPUNIT_ASSERT(0 == buff[23]);
    
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

    // fill msg1, select ListA as active.
    msgIn1.getHeader()->getHeaderRec()->setMessageID(500);
    msgIn1.getBody()->getVariantTop()->setFieldValue(0);
    for (unsigned short i = 0; i < 5; ++i)
    {
       MsgIn::Body::VariantTop::ListA::RecA1 temprec;
       temprec.setFieldA1(10+i);
       msgIn1.getBody()->getVariantTop()->getListA()->addElement(temprec);
    }

    // fill msg2, select ListB as active.
    msgIn2.getHeader()->getHeaderRec()->setMessageID(1000);
    msgIn2.getBody()->getVariantTop()->setFieldValue(1);
    for (unsigned short i = 0; i < 10; ++i)
    {
       MsgIn::Body::VariantTop::ListB::RecB1 temprec;
       temprec.setFieldB1(20+i);
       msgIn2.getBody()->getVariantTop()->getListB()->addElement(temprec);
    }
    
    // encode/decode msg2 into msg1.  verify ListB now active in msg1 and verify list contents copied over
    msgIn2.encode(buff);
    msgIn1.decode(buff);
    
    CPPUNIT_ASSERT(1000 == msgIn1.getHeader()->getHeaderRec()->getMessageID());
    CPPUNIT_ASSERT(1 == msgIn1.getBody()->getVariantTop()->getFieldValue());
    for (unsigned short i = 0; i < 10; ++i)
    {
       CPPUNIT_ASSERT(20+i == msgIn1.getBody()->getVariantTop()->getListB()->getElement(i)->getFieldB1());
    }
    
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
    CppUnit::TestSuite *suiteOfTests = new CppUnit::TestSuite( "JTSVariant3MessageTest" );
    
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSVariant3MessageTest>( 
                                   "testConstructionDefaults", 
                                   &JTSVariant3MessageTest::testConstructionDefaults ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSVariant3MessageTest>( 
                                   "testSetGetOperations (TP_3.3.5.1)", 
                                   &JTSVariant3MessageTest::testSetGetOperations ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSVariant3MessageTest>( 
                                   "testEncodeToSpec (TP_3.3.5.2)", 
                                   &JTSVariant3MessageTest::testEncodeToSpec ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSVariant3MessageTest>( 
                                   "testEncodeDecodeOperations (TP_3.3.5.3)", 
                                   &JTSVariant3MessageTest::testEncodeDecodeOperations ) );                                   
                                                                      
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSVariant3MessageTest>( 
                                   "testEquality", 
                                   &JTSVariant3MessageTest::testEquality ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSVariant3MessageTest>(
                                   "testInequality",
                                   &JTSVariant3MessageTest::testInequality ) );
    return suiteOfTests;
  }

};


int main( int ac, char **av )
{
  CppUnit::TextUi::TestRunner runner;
  runner.addTest( JTSVariant3MessageTest::suite() );
  
  OUT_0 << std::endl << "------------------------------------------------" << std::endl;
  OUT_0 << "Executing suite JTSVariant3MessageTest" << std::endl;
  runner.run();
  OUT_0 << std::endl << "Completed suite JTSVariant3MessageTest" << std::endl;
  OUT_0 << "------------------------------------------------" << std::endl;  
}

