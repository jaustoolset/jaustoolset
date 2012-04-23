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

#include "urn_org_jts_test_Sequence1_1_0/Messages/MessageSet.h"

#include "cppunit/TestCaller.h"
#include "cppunit/TestCaller.h"
#include "cppunit/ui/text/TestRunner.h"
#include "cppunit/TestSuite.h"

using namespace urn_org_jts_test_Sequence1_1_0;

#define OUT_0  std::cout
#define OUT_1  if (bVerbose<=2) std::cout 
#define OUT_2  if (bVerbose<=1) std::cout

/*
  Body->Sequence1->Record1
    where Record1 has a Field1 byte and a Field2 integer
  or
  Body->Sequence1->List1->Record1
    where Record1 has a ushort Field1
*/

class JTSSequence1MessageTest : public CppUnit::TestFixture {
             
private:
  MsgIn m_MsgIn1,m_MsgIn2;
  int bVerbose;
  
public:
  JTSSequence1MessageTest() {
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
    CPPUNIT_ASSERT( m_MsgIn1.getHeader()->getHeaderRec()->getMessageID() == 1 );
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getHeaderRec()->getMessageID() == 1 );

  }

  
  void testSetGetOperations()
  {
    OUT_1 << std::endl << "  [executing test (testSetGetOperations)(TP_3.3.5.1)] " << std::endl;
    
    OUT_2 << "     --- verifying setMessageID/getMessageID reciprocity" << std::endl;
    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(5);
    CPPUNIT_ASSERT( m_MsgIn1.getHeader()->getHeaderRec()->getMessageID() == 5 );
    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(1);
    CPPUNIT_ASSERT( m_MsgIn1.getHeader()->getHeaderRec()->getMessageID() == 1 );

    OUT_2 << "     --- verifying setField1/getField1 reciprocity" << std::endl;    
    m_MsgIn1.getBody()->getSequence1()->getRecord1()->setField1(25);
    m_MsgIn2.getBody()->getSequence1()->getRecord1()->setField1(-15);    
    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getSequence1()->getRecord1()->getField1() == 25);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getSequence1()->getRecord1()->getField1() == -15);    

    m_MsgIn1.getBody()->getSequence1()->getRecord1()->setField2(3000);
    m_MsgIn2.getBody()->getSequence1()->getRecord1()->setField2(-3125);    

    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getSequence1()->getRecord1()->getField1() == 25);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getSequence1()->getRecord1()->getField1() == -15);    
    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getSequence1()->getRecord1()->getField2() == 3000);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getSequence1()->getRecord1()->getField2() == -3125);  
    
    MsgIn::Body::Sequence1::List1::Record1 R1,R2;    
    R1.setField1(99);
    R2.setField1(7);
    m_MsgIn1.getBody()->getSequence1()->getList1()->addElement(R1);
    m_MsgIn2.getBody()->getSequence1()->getList1()->addElement(R2);
    
    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getSequence1()->getRecord1()->getField1() == 25);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getSequence1()->getRecord1()->getField1() == -15);    
    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getSequence1()->getRecord1()->getField2() == 3000);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getSequence1()->getRecord1()->getField2() == -3125);  
    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getSequence1()->getList1()->getElement(0)->getField1()==99);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getSequence1()->getList1()->getElement(0)->getField1()==7);
        
    OUT_1 <<  "  [completed test (testSetGetOperations)] " << std::endl;
             
  }

  void testEncodeToSpec()
  {
    unsigned char buff[1024];
    int i;
       
    OUT_1 << std::endl << "  [executing test (testEncodeToSpec)(TP_3.3.5.2)] " << std::endl;
    
    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(256);
    m_MsgIn1.getBody()->getSequence1()->getRecord1()->setField1(2);
    m_MsgIn1.getBody()->getSequence1()->getRecord1()->setField2(100992003);
    MsgIn::Body::Sequence1::List1::Record1 R1;
    R1.setField1(2312);
    m_MsgIn1.getBody()->getSequence1()->getList1()->addElement(R1);
    R1.setField1(2826);
    m_MsgIn1.getBody()->getSequence1()->getList1()->addElement(R1);
    R1.setField1(3340);
    m_MsgIn1.getBody()->getSequence1()->getList1()->addElement(R1);
    R1.setField1(3854);
    m_MsgIn1.getBody()->getSequence1()->getList1()->addElement(R1);
    R1.setField1(4368);
    m_MsgIn1.getBody()->getSequence1()->getList1()->addElement(R1);
    R1.setField1(4882);
    m_MsgIn1.getBody()->getSequence1()->getList1()->addElement(R1);
    R1.setField1(5396);
    m_MsgIn1.getBody()->getSequence1()->getList1()->addElement(R1);
           
    
    OUT_2 << "     --- verifying message level encode is AS-5684 compliant" << std::endl;
    m_MsgIn1.encode(buff);
    
    for (i=0;i<m_MsgIn1.getSize();i++)
       CPPUNIT_ASSERT( buff[i]==i );

    OUT_1 << "  [completed test (testEncodeToSpec)] " << std::endl;
       
  }
  
  void testEncodeDecodeOperations()
  {
    unsigned char buff[1024];
    int i;
    
    for (i=0;i<1024;i++)
       buff[i] = ((3711*i)+65543) % 256;
    
    OUT_1 << std::endl << "  [executing test (testEncodeDecodeOperations)(TP_3.3.5.3)] " << std::endl;
    
    OUT_2 << "     --- verifying message level encode/decode reciprocity" << std::endl;

    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(81);
    m_MsgIn1.getBody()->getSequence1()->getRecord1()->setField1(71);
    m_MsgIn1.getBody()->getSequence1()->getRecord1()->setField2(1234);
    MsgIn::Body::Sequence1::List1::Record1 R1;    
    R1.setField1(99);
    m_MsgIn1.getBody()->getSequence1()->getList1()->addElement(R1);

    m_MsgIn2.getHeader()->getHeaderRec()->setMessageID(127);
    m_MsgIn2.getBody()->getSequence1()->getRecord1()->setField1(127);
    m_MsgIn2.getBody()->getSequence1()->getRecord1()->setField2(127);

    R1.setField1(127);
    m_MsgIn2.getBody()->getSequence1()->getList1()->addElement(R1);
    R1.setField1(127);
    m_MsgIn2.getBody()->getSequence1()->getList1()->addElement(R1);
    
    m_MsgIn1.encode(buff);
    m_MsgIn2.decode(buff);
    
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getHeaderRec()->getMessageID() == 81);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getSequence1()->getRecord1()->getField1()==71);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getSequence1()->getRecord1()->getField2()==1234);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getSequence1()->getList1()->getNumberOfElements()==1);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getSequence1()->getList1()->getElement(0)->getField1()==99);

    OUT_2 << "     --- verifying header level encode/decode reciprocity" << std::endl;
    
    m_MsgIn2.getHeader()->getHeaderRec()->setMessageID(0);
    m_MsgIn1.getHeader()->encode(buff);
    m_MsgIn2.getHeader()->decode(buff);
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getHeaderRec()->getMessageID() == 81);

    m_MsgIn2.getHeader()->getHeaderRec()->setMessageID(0);
    m_MsgIn1.getHeader()->getHeaderRec()->encode(buff);
    m_MsgIn2.getHeader()->getHeaderRec()->decode(buff);
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getHeaderRec()->getMessageID() == 81);

    OUT_2 << "     --- verifying record level encode/decode reciprocity" << std::endl;

    m_MsgIn2.getHeader()->getHeaderRec()->setMessageID(0);
    m_MsgIn2.getBody()->getSequence1()->getRecord1()->setField1(255);
    m_MsgIn2.getBody()->getSequence1()->getRecord1()->setField2(0);

    R1.setField1(1);
    m_MsgIn2.getBody()->getSequence1()->getList1()->addElement(R1);
    R1.setField1(254);
    m_MsgIn2.getBody()->getSequence1()->getList1()->addElement(R1);
        
    m_MsgIn1.getBody()->getSequence1()->encode(buff);
    m_MsgIn2.getBody()->getSequence1()->decode(buff);

    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getSequence1()->getRecord1()->getField1()==71);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getSequence1()->getRecord1()->getField2()==1234);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getSequence1()->getList1()->getNumberOfElements()==1);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getSequence1()->getList1()->getElement(0)->getField1()==99);

    m_MsgIn2.getBody()->getSequence1()->getRecord1()->setField1(0);
    m_MsgIn2.getBody()->getSequence1()->getRecord1()->setField2(0);
        
    m_MsgIn1.getBody()->getSequence1()->getRecord1()->encode(buff);
    m_MsgIn2.getBody()->getSequence1()->getRecord1()->decode(buff);

    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getSequence1()->getRecord1()->getField1()==71);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getSequence1()->getRecord1()->getField2()==1234);
            
    OUT_1 << "  [completed test (testEncodeDecodeOperations)] " << std::endl;
       
  }

  void testEquality()
  {
    OUT_1 << std::endl << "  [executing test (testEquality)] " << std::endl;
    OUT_2 << "     --- verifying message equality operator" << std::endl;    
    CPPUNIT_ASSERT( m_MsgIn1 == m_MsgIn2 );
    OUT_1 << "  [completed test (testEquality)] " << std::endl;
    
  }
  
  void testInequality()
  {
    OUT_1 << std::endl << "  [executing test (testInequality)] " << std::endl;
    OUT_2 << "     --- verifying message inequality operator" << std::endl;    
       
    CPPUNIT_ASSERT( !(m_MsgIn1 != m_MsgIn2) );
    OUT_1 << "  [completed test (testInequality)] " << std::endl;
    
  }

  static CppUnit::Test *suite()
  {
    CppUnit::TestSuite *suiteOfTests = new CppUnit::TestSuite( "JTSSequence1MessageTest" );
    
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSSequence1MessageTest>( 
                                   "testConstructionDefaults", 
                                   &JTSSequence1MessageTest::testConstructionDefaults ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSSequence1MessageTest>( 
                                   "testSetGetOperations (TP_3.3.5.1)", 
                                   &JTSSequence1MessageTest::testSetGetOperations ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSSequence1MessageTest>( 
                                   "testEncodeToSpec (TP_3.3.5.2)", 
                                   &JTSSequence1MessageTest::testEncodeToSpec ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSSequence1MessageTest>( 
                                   "testEncodeDecodeOperations (TP_3.3.5.3)", 
                                   &JTSSequence1MessageTest::testEncodeDecodeOperations ) );                                   
                                                                      
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSSequence1MessageTest>( 
                                   "testEquality", 
                                   &JTSSequence1MessageTest::testEquality ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSSequence1MessageTest>(
                                   "testInequality",
                                   &JTSSequence1MessageTest::testInequality ) );
    return suiteOfTests;
  }

};


int main( int ac, char **av )
{
  CppUnit::TextUi::TestRunner runner;
  runner.addTest( JTSSequence1MessageTest::suite() );
  
  OUT_0 << std::endl << "------------------------------------------------" << std::endl;
  OUT_0 << "Executing suite JTSSequence1MessageTest" << std::endl;
  runner.run();
  OUT_0 << std::endl << "Completed suite JTSSequence1MessageTest" << std::endl;
  OUT_0 << "------------------------------------------------" << std::endl;  
}

