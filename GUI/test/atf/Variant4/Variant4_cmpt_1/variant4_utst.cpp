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

// test for message structure with <variant> containing <sequence>s

#include <iostream>

#include "urn_org_jts_test_Variant4_1_0/Messages/MessageSet.h"

#include "cppunit/TestCaller.h"
#include "cppunit/TestCaller.h"
#include "cppunit/ui/text/TestRunner.h"
#include "cppunit/TestSuite.h"

using namespace urn_org_jts_test_Variant4_1_0;

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

// need to repeatedly reset the contents of msgIn1 in during testEncodeDecodeOperations.
void resetMessage(MsgIn& for_reset)
{
    for_reset.getHeader()->getHeaderRec()->setMessageID(1000);
    for_reset.getBody()->getVariantTop()->setFieldValue(1);
    for_reset.getBody()->getVariantTop()->getSeqB()->getRecB()->setFieldB1(9000);

    for (int i = 0; i < 10; ++i)
    {
        MsgIn::Body::VariantTop::SeqB::ListB::RecBInner temprec;
        temprec.setFieldB2(20+i);
        for_reset.getBody()->getVariantTop()->getSeqB()->getListB()->addElement(temprec);
    }
}

class JTSVariant4MessageTest : public CppUnit::TestFixture {
             
private:
  MsgIn msgIn1,msgIn2;
  int bVerbose;
  
public:
  JTSVariant4MessageTest() {
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

    CPPUNIT_ASSERT( msgIn1.getHeader()->getHeaderRec()->getMessageID() == 1 );
    CPPUNIT_ASSERT( msgIn2.getHeader()->getHeaderRec()->getMessageID() == 1 );

    // check vtag fields are assigned 0 initially
    CPPUNIT_ASSERT(0 == msgIn1.getBody()->getVariantTop()->getFieldValue());
    CPPUNIT_ASSERT(0 == msgIn2.getBody()->getVariantTop()->getFieldValue());
    
    // check fixed fields are zeroed and lists are empty initially.
    CPPUNIT_ASSERT(0 == msgIn1.getBody()->getVariantTop()->getSeqA()->getRecA()->getFieldA1());
    CPPUNIT_ASSERT(0 == msgIn1.getBody()->getVariantTop()->getSeqB()->getRecB()->getFieldB1());
    CPPUNIT_ASSERT(0 == msgIn1.getBody()->getVariantTop()->getSeqA()->getListA()->getNumberOfElements());
    CPPUNIT_ASSERT(0 == msgIn1.getBody()->getVariantTop()->getSeqB()->getListB()->getNumberOfElements());

    CPPUNIT_ASSERT(0 == msgIn2.getBody()->getVariantTop()->getSeqA()->getRecA()->getFieldA1());
    CPPUNIT_ASSERT(0 == msgIn2.getBody()->getVariantTop()->getSeqB()->getRecB()->getFieldB1());
    CPPUNIT_ASSERT(0 == msgIn2.getBody()->getVariantTop()->getSeqA()->getListA()->getNumberOfElements());
    CPPUNIT_ASSERT(0 == msgIn2.getBody()->getVariantTop()->getSeqB()->getListB()->getNumberOfElements());

    OUT_1 <<  "  [completed test (testSetGetOperations)] " << std::endl;
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
    
    // set/add elements to active sequence, SeqA, verify changes are recorded.
    msgIn1.getBody()->getVariantTop()->getSeqA()->getRecA()->setFieldA1(45);
    for (int i = 0; i < 10; ++i) 
    {
       MsgIn::Body::VariantTop::SeqA::ListA::RecAInner temprec;
       temprec.setFieldA2(10+i);
       msgIn1.getBody()->getVariantTop()->getSeqA()->getListA()->addElement(temprec);
    }
    
    CPPUNIT_ASSERT(45 == msgIn1.getBody()->getVariantTop()->getSeqA()->getRecA()->getFieldA1());
    for (int i = 0; i < 10; ++i) 
    {
       CPPUNIT_ASSERT(10+i == msgIn1.getBody()->getVariantTop()->getSeqA()->getListA()->getElement(i)->getFieldA2());
    }
    
    
    // set/add elements to active sequence, SeqB, verify changes are recorded.
    msgIn1.getBody()->getVariantTop()->setFieldValue(1);
    msgIn1.getBody()->getVariantTop()->getSeqB()->getRecB()->setFieldB1(90);
    for (int i = 0; i < 5; ++i) 
    {
       MsgIn::Body::VariantTop::SeqB::ListB::RecBInner temprec;
       temprec.setFieldB2(20+i);
       msgIn1.getBody()->getVariantTop()->getSeqB()->getListB()->addElement(temprec);
    }
    
    CPPUNIT_ASSERT(90 == msgIn1.getBody()->getVariantTop()->getSeqB()->getRecB()->getFieldB1());
    for (int i = 0; i < 5; ++i) 
    {
       CPPUNIT_ASSERT(20+i == msgIn1.getBody()->getVariantTop()->getSeqB()->getListB()->getElement(i)->getFieldB2());
    }
    
    // check message size is correct
    // 2-byte ushort msg ID header, 1-byte vtag field, 2-byte ushort in RecB, 1-byte count field for ListB, 
    // 5 2-byte unsigned shorts in ListB
    CPPUNIT_ASSERT(2 + 1 + 2 + 1 + 5*2 == msgIn1.getSize());
    
    OUT_1 <<  "  [completed test (testSetGetOperations)] " << std::endl;
             
  }

  void testEncodeToSpec()
  {
    unsigned char buff[1024];
    int i = 0;
    
    for (i=0;i<1024;i++) 
    {
       buff[i] = 0;
    }

    OUT_1 << std::endl << "  [executing test (testEncodeToSpec)(TP_3.3.5.2)] " << std::endl;

    // fill the message in and copy to buffer
    resetMessage(msgIn1);
    msgIn1.encode(buff);
    
    // verify encoding matches expectations from spec document

    // offset 0:   unsigned short w/ message ID
    CPPUNIT_ASSERT(1000 == ushortAtOffset(buff, 0));

    // offset 2:   unsigned char w/ VariantTop's vtag field
    CPPUNIT_ASSERT(1 == buff[2]);
    
    // offset 3:   unsigned short, FieldB1
    CPPUNIT_ASSERT(9000 == ushortAtOffset(buff, 3));
    
    // offset 5:   unsigned char w/ ListB count field
    CPPUNIT_ASSERT(10 == buff[5]);
    
    // offsets 6-24: 10 ushort values in list
    int offset = 6;
    for (i = 0; i < 10; ++i) 
    {
        CPPUNIT_ASSERT(20+i == ushortAtOffset(buff, offset));
        offset += 2;
    }
    
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
    
    // setup msg1
    resetMessage(msgIn1);
    
    // setup msg2
    msgIn2.getHeader()->getHeaderRec()->setMessageID(500);
    msgIn2.getBody()->getVariantTop()->setFieldValue(1);
    msgIn2.getBody()->getVariantTop()->getSeqB()->getRecB()->setFieldB1(4500);

    for (i = 0; i < 15; ++i)
    {
      MsgIn::Body::VariantTop::SeqB::ListB::RecBInner temprec;
      temprec.setFieldB2(5+i);
      msgIn2.getBody()->getVariantTop()->getSeqB()->getListB()->addElement(temprec);
    }
    
    // copy entire msg2 into msg1, verify new contents are correct
    msgIn2.encode(buff);
    msgIn1.decode(buff);
    
    CPPUNIT_ASSERT(500 == msgIn1.getHeader()->getHeaderRec()->getMessageID());
    CPPUNIT_ASSERT(1 == msgIn1.getBody()->getVariantTop()->getFieldValue());
    CPPUNIT_ASSERT(4500 == msgIn1.getBody()->getVariantTop()->getSeqB()->getRecB()->getFieldB1());
    
    for (i = 0; i < 15; ++i)
    {
        CPPUNIT_ASSERT(5+i == msgIn1.getBody()->getVariantTop()->getSeqB()->getListB()->getElement(i)->getFieldB2());
    }
    
    // reset msg1.
    resetMessage(msgIn1);
    
    // copy only variant from msg2 into msg1, verify contents
    msgIn2.getBody()->getVariantTop()->encode(buff);
    msgIn1.getBody()->getVariantTop()->decode(buff);

    CPPUNIT_ASSERT(1000 == msgIn1.getHeader()->getHeaderRec()->getMessageID()); // unchanged from reset values
    CPPUNIT_ASSERT(1 == msgIn1.getBody()->getVariantTop()->getFieldValue());
    CPPUNIT_ASSERT(4500 == msgIn1.getBody()->getVariantTop()->getSeqB()->getRecB()->getFieldB1());
    
    for (i = 0; i < 15; ++i)
    {
        CPPUNIT_ASSERT(5+i == msgIn1.getBody()->getVariantTop()->getSeqB()->getListB()->getElement(i)->getFieldB2());
    }
    
    // reset msg1.
    resetMessage(msgIn1);    

    // copy only seqB's listB from msg2 into msg1, verify contents.
    msgIn2.getBody()->getVariantTop()->getSeqB()->getListB()->encode(buff);
    msgIn1.getBody()->getVariantTop()->getSeqB()->getListB()->decode(buff);

    CPPUNIT_ASSERT(1000 == msgIn1.getHeader()->getHeaderRec()->getMessageID()); // unchanged from reset values
    CPPUNIT_ASSERT(1 == msgIn1.getBody()->getVariantTop()->getFieldValue());
    CPPUNIT_ASSERT(9000 == msgIn1.getBody()->getVariantTop()->getSeqB()->getRecB()->getFieldB1()); // should also be unchanged.
    
    for (i = 0; i < 15; ++i)
    {
        CPPUNIT_ASSERT(5+i == msgIn1.getBody()->getVariantTop()->getSeqB()->getListB()->getElement(i)->getFieldB2());
    }
    
    OUT_1 << std::endl << "  [executing test (testEncodeDecodeOperations)(TP_3.3.5.3)] " << std::endl;
    
    OUT_1 << "  [completed test (testEncodeDecodeOperations)] " << std::endl;
       
  }

  void testEquality()
  {
    OUT_1 << std::endl << "  [executing test (testEquality)] " << std::endl;
    CPPUNIT_ASSERT( msgIn1 == msgIn2 );
    OUT_1 << "  [completed test (testEquality)] " << std::endl;
    
  }
  
  void testInequality()
  {
    OUT_1 << std::endl << "  [executing test (testInequality)] " << std::endl;
    CPPUNIT_ASSERT( !(msgIn1 != msgIn2) );
    OUT_1 << "  [completed test (testInequality)] " << std::endl;
    
  }

  static CppUnit::Test *suite()
  {
    CppUnit::TestSuite *suiteOfTests = new CppUnit::TestSuite( "JTSVariant4MessageTest" );
    
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSVariant4MessageTest>( 
                                   "testConstructionDefaults", 
                                   &JTSVariant4MessageTest::testConstructionDefaults ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSVariant4MessageTest>( 
                                   "testSetGetOperations (TP_3.3.5.1)", 
                                   &JTSVariant4MessageTest::testSetGetOperations ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSVariant4MessageTest>( 
                                   "testEncodeToSpec (TP_3.3.5.2)", 
                                   &JTSVariant4MessageTest::testEncodeToSpec ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSVariant4MessageTest>( 
                                   "testEncodeDecodeOperations (TP_3.3.5.3)", 
                                   &JTSVariant4MessageTest::testEncodeDecodeOperations ) );                                   
                                                                      
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSVariant4MessageTest>( 
                                   "testEquality", 
                                   &JTSVariant4MessageTest::testEquality ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSVariant4MessageTest>(
                                   "testInequality",
                                   &JTSVariant4MessageTest::testInequality ) );
    return suiteOfTests;
  }

};


int main( int ac, char **av )
{
  CppUnit::TextUi::TestRunner runner;
  runner.addTest( JTSVariant4MessageTest::suite() );
  
  OUT_0 << std::endl << "------------------------------------------------" << std::endl;
  OUT_0 << "Executing suite JTSVariant4MessageTest" << std::endl;
  runner.run();
  OUT_0 << std::endl << "Completed suite JTSVariant4MessageTest" << std::endl;
  OUT_0 << "------------------------------------------------" << std::endl;  
}

