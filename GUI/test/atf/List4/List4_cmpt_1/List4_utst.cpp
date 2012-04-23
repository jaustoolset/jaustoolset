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

#include "urn_org_jts_test_List4_1_0/Messages/MessageSet.h"

#include "cppunit/TestCaller.h"
#include "cppunit/TestCaller.h"
#include "cppunit/ui/text/TestRunner.h"
#include "cppunit/TestSuite.h"

using namespace urn_org_jts_test_List4_1_0;

#define OUT_0  std::cout
#define OUT_1  if (bVerbose<=2) std::cout 
#define OUT_2  if (bVerbose<=1) std::cout



class JTSList4MessageTest : public CppUnit::TestFixture {
             
private:
  MsgIn m_MsgIn1,m_MsgIn2;
  int bVerbose;
  
public:
  JTSList4MessageTest() {
     bVerbose=0;
  }    

  void populate()
  {
	MsgIn::Body::List4 List;
	m_MsgIn1.getBody()->setList4( List );
	MsgIn::Body::List4::Variant1 V1,V2,V3;
	MsgIn::Body::List4::Variant1::List1::Record2 R2;
	
	V1.setFieldValue(0);
    V1.getRecord1()->setField1(1);
    V1.getRecord1()->setField2(770);

	V2.setFieldValue(1);
	R2.setField1(3); V2.getList1()->addElement(R2);
	R2.setField1(4); V2.getList1()->addElement(R2);

	V3.setFieldValue(2);
	V3.getSequence1()->getRecord3()->setField1(3);
    V3.getSequence1()->getRecord3()->setField2(1284);

    m_MsgIn1.getBody()->getList4()->addElement(V1);
    m_MsgIn1.getBody()->getList4()->addElement(V2);
	m_MsgIn1.getBody()->getList4()->addElement(V3);
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

    OUT_2 << "     --- verifying MsgIn constructs with default empty lists" << std::endl;

    CPPUNIT_ASSERT(m_MsgIn1.getBody()->getList4()->getNumberOfElements()==0);
    CPPUNIT_ASSERT(m_MsgIn2.getBody()->getList4()->getNumberOfElements()==0);  

  }
  
  void testSetGetOperations()
  {
    OUT_1 << std::endl << "  [executing test (testSetGetOperations)(TP_3.3.5.1)] " << std::endl;
    
    OUT_2 << "     --- verifying setMessageID/getMessageID reciprocity" << std::endl;
    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(5);
    CPPUNIT_ASSERT( m_MsgIn1.getHeader()->getHeaderRec()->getMessageID() == 5 );
    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(1);
    CPPUNIT_ASSERT( m_MsgIn1.getHeader()->getHeaderRec()->getMessageID() == 1 );
    
    OUT_2 << "     --- verifying setField/getField reciprocity" << std::endl;
    
    MsgIn::Body::List4::Variant1 V1,V2;
    
    V1.getRecord1()->setField1(5);
    V1.getRecord1()->setField2(500);
	V1.setFieldValue(0);
    V2.getSequence1()->getRecord3()->setField1(6);
    V2.getSequence1()->getRecord3()->setField2(600);
	V2.setFieldValue(2);
    m_MsgIn1.getBody()->getList4()->addElement(V1);
    m_MsgIn2.getBody()->getList4()->addElement(V2);
    CPPUNIT_ASSERT(m_MsgIn1.getBody()->getList4()->getElement(0)->getRecord1()->getField1()==5);
    CPPUNIT_ASSERT(m_MsgIn1.getBody()->getList4()->getElement(0)->getRecord1()->getField2()==500);
    CPPUNIT_ASSERT(m_MsgIn2.getBody()->getList4()->getElement(0)->getSequence1()->getRecord3()->getField1()==6);
    CPPUNIT_ASSERT(m_MsgIn2.getBody()->getList4()->getElement(0)->getSequence1()->getRecord3()->getField2()==600);

	// Test copy constructor
	OUT_2 << "     --- verifying copy constructor" << std::endl;    
	m_MsgIn2 = m_MsgIn1;
    CPPUNIT_ASSERT( m_MsgIn1 == m_MsgIn2 );
	CPPUNIT_ASSERT( m_MsgIn1.getBody()->getList4()->getNumberOfElements() == m_MsgIn2.getBody()->getList4()->getNumberOfElements());        

    OUT_1 <<  "  [completed test (testSetGetOperations)] " << std::endl;
             
  }

  void testEncodeToSpec()
  {
    unsigned char buff[1024];
    int i, j;
       
    OUT_1 << std::endl << "  [executing test (testEncodeToSpec)(TP_3.3.5.2)] " << std::endl;

    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(256);
    populate();
    
    OUT_2 << "     --- verifying message level encode is AS-5684 compliant" << std::endl;
    m_MsgIn1.encode(buff);

	CPPUNIT_ASSERT( buff[0] == 0 ); CPPUNIT_ASSERT( buff[1] == 1 ); // msg_id
	CPPUNIT_ASSERT( buff[2] == 3 );                                // str length
	for (i=3, j=0; i<7; i++, j++) CPPUNIT_ASSERT( buff[i] == j); // record type
	for (i=7, j=1; i<11; i++, j++) CPPUNIT_ASSERT( buff[i] == j); // list type
	for (i=11, j=2; i<15; i++, j++) CPPUNIT_ASSERT( buff[i] == j); // sequence type

    OUT_1 << "  [completed test (testEncodeToSpec)] " << std::endl;
       
  }
  
  void testEncodeDecodeOperations()
  {
    unsigned char buff[1024];
    int i;
    MsgIn::Body::List4::Variant1 V1;
    
    for (i=0;i<1024;i++)
       buff[i] = ((2101*i)+65543) % 256;
    
    OUT_1 << std::endl << "  [executing test (testEncodeDecodeOperations)(TP_3.3.5.3)] " << std::endl;
    
    OUT_2 << "     --- verifying message level encode/decode reciprocity" << std::endl;
    

    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(41);
    m_MsgIn1.getBody()->getList4()->addElement(V1);
    m_MsgIn1.getBody()->getList4()->addElement(V1);
	m_MsgIn1.getBody()->getList4()->getElement(0)->setFieldValue(0);
    m_MsgIn1.getBody()->getList4()->getElement(0)->getRecord1()->setField1(15);
    m_MsgIn1.getBody()->getList4()->getElement(0)->getRecord1()->setField2(300);
	m_MsgIn1.getBody()->getList4()->getElement(1)->setFieldValue(2);
    m_MsgIn1.getBody()->getList4()->getElement(1)->getSequence1()->getRecord3()->setField1(0);
    m_MsgIn1.getBody()->getList4()->getElement(1)->getSequence1()->getRecord3()->setField2(6525);
    
    m_MsgIn2.getHeader()->getHeaderRec()->setMessageID(127);
    m_MsgIn2.getBody()->getList4()->addElement(V1);
	m_MsgIn1.getBody()->getList4()->getElement(0)->setFieldValue(0);
    m_MsgIn2.getBody()->getList4()->getElement(0)->getRecord1()->setField1(127);
    m_MsgIn2.getBody()->getList4()->getElement(0)->getRecord1()->setField2(127);
    
    m_MsgIn1.encode(buff);
    m_MsgIn2.decode(buff);

    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getHeaderRec()->getMessageID() == 41);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList4()->getNumberOfElements()==2);
	CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList4()->getElement(0)->getFieldValue() == 0);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList4()->getElement(0)->getRecord1()->getField1() == 15);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList4()->getElement(0)->getRecord1()->getField2() == 300);
	CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList4()->getElement(1)->getFieldValue() == 2);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList4()->getElement(1)->getSequence1()->getRecord3()->getField1() == 0);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList4()->getElement(1)->getSequence1()->getRecord3()->getField2() == 6525);
    
    
    OUT_2 << "     --- verifying header level encode/decode reciprocity" << std::endl;
    
    m_MsgIn2.getHeader()->getHeaderRec()->setMessageID(0);
    m_MsgIn1.getHeader()->encode(buff);
    m_MsgIn2.getHeader()->decode(buff);
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getHeaderRec()->getMessageID() == 41);

    m_MsgIn2.getHeader()->getHeaderRec()->setMessageID(0);
    m_MsgIn1.getHeader()->getHeaderRec()->encode(buff);
    m_MsgIn2.getHeader()->getHeaderRec()->decode(buff);
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getHeaderRec()->getMessageID() == 41);
    
    OUT_2 << "     --- verifying body level encode/decode reciprocity" << std::endl;

	m_MsgIn2.getBody()->getList4()->getElement(0)->setFieldValue(0);
    m_MsgIn2.getBody()->getList4()->getElement(0)->getRecord1()->setField1(255);
    m_MsgIn2.getBody()->getList4()->getElement(0)->getRecord1()->setField2(255);
	m_MsgIn2.getBody()->getList4()->getElement(0)->setFieldValue(0);
    m_MsgIn2.getBody()->getList4()->getElement(1)->getRecord1()->setField1(255);
    m_MsgIn2.getBody()->getList4()->getElement(1)->getRecord1()->setField2(255);
    V1.getRecord1()->setField1(255); V1.getRecord1()->setField2(255);
	m_MsgIn2.getBody()->getList4()->addElement(V1);
    
    m_MsgIn1.getBody()->encode(buff);
    m_MsgIn2.getBody()->decode(buff);
    
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList4()->getNumberOfElements()==2);
	CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList4()->getElement(0)->getFieldValue() == 0);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList4()->getElement(0)->getRecord1()->getField1() == 15);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList4()->getElement(0)->getRecord1()->getField2() == 300);
	CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList4()->getElement(1)->getFieldValue() == 2);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList4()->getElement(1)->getSequence1()->getRecord3()->getField1() == 0);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList4()->getElement(1)->getSequence1()->getRecord3()->getField2() == 6525);


    OUT_2 << "     --- verifying list level encode/decode reciprocity" << std::endl;
    
	m_MsgIn2.getBody()->getList4()->getElement(0)->setFieldValue(0);
    m_MsgIn2.getBody()->getList4()->getElement(0)->getRecord1()->setField1(255);
    m_MsgIn2.getBody()->getList4()->getElement(0)->getRecord1()->setField2(255);
	m_MsgIn2.getBody()->getList4()->getElement(0)->setFieldValue(1);
    m_MsgIn2.getBody()->getList4()->getElement(1)->getRecord1()->setField1(255);
    m_MsgIn2.getBody()->getList4()->getElement(1)->getRecord1()->setField2(255);
    V1.getRecord1()->setField1(255); V1.getRecord1()->setField2(255);
    m_MsgIn2.getBody()->getList4()->addElement(V1);
    
    m_MsgIn1.getBody()->getList4()->encode(buff);
    m_MsgIn2.getBody()->getList4()->decode(buff);
    
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList4()->getNumberOfElements()==2);
	CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList4()->getElement(0)->getFieldValue()==0);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList4()->getElement(0)->getRecord1()->getField1() == 15);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList4()->getElement(0)->getRecord1()->getField2() == 300);
	CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList4()->getElement(1)->getFieldValue()==2);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList4()->getElement(1)->getSequence1()->getRecord3()->getField1() == 0);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList4()->getElement(1)->getSequence1()->getRecord3()->getField2() == 6525);
    
    OUT_2 << "     --- verifying record level encode/decode reciprocity" << std::endl;
    m_MsgIn2.getBody()->getList4()->getElement(0)->getRecord1()->setField1(255);
    m_MsgIn2.getBody()->getList4()->getElement(0)->getRecord1()->setField2(255);
    
    m_MsgIn1.getBody()->getList4()->getElement(0)->encode(buff);
    m_MsgIn2.getBody()->getList4()->getElement(0)->decode(buff);
    
	CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList4()->getElement(0)->getFieldValue()==0);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList4()->getElement(0)->getRecord1()->getField1() == 15);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList4()->getElement(0)->getRecord1()->getField2() == 300);
    
            
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

  void testListOperations()
  {
	MsgIn::Body::List4 List;
    MsgIn::Body::List4::Variant1 V1;
	m_MsgIn2.getBody()->setList4( List );
    int i,j;
    
    OUT_1 << std::endl << "  [executing test (testListOperations)] " << std::endl;
    
    OUT_2 << "     --- verifying MsgIn getNumberOfElements with Add and Delete operations" << std::endl;    
    i=j=0;  
    for (i=0;i<4;i++) {
       CPPUNIT_ASSERT(m_MsgIn2.getBody()->getList4()->getNumberOfElements()==i);
       m_MsgIn2.getBody()->getList4()->addElement(V1);
    }   
    for (j=0,i=4;j<4;j++,i--) {
       CPPUNIT_ASSERT(m_MsgIn2.getBody()->getList4()->getNumberOfElements() == i);
       m_MsgIn2.getBody()->getList4()->deleteLastElement();
    }

    OUT_2 << "     --- verifying deleteElement preserves order & corrects count of remaining elements" << std::endl;                          
    
	populate();

    m_MsgIn1.getBody()->getList4()->deleteElement(0);
    CPPUNIT_ASSERT(m_MsgIn1.getBody()->getList4()->getNumberOfElements() == 2);

	CPPUNIT_ASSERT(m_MsgIn1.getBody()->getList4()->getElement(0)->getFieldValue() == 1);
    CPPUNIT_ASSERT(m_MsgIn1.getBody()->getList4()->getElement(0)->getList1()->getNumberOfElements() == 2);
	CPPUNIT_ASSERT(m_MsgIn1.getBody()->getList4()->getElement(0)->getList1()->getElement(0)->getField1() == 3);
	CPPUNIT_ASSERT(m_MsgIn1.getBody()->getList4()->getElement(0)->getList1()->getElement(1)->getField1() == 4);
    CPPUNIT_ASSERT(m_MsgIn1.getBody()->getList4()->getElement(1)->getFieldValue() == 2);
	CPPUNIT_ASSERT(m_MsgIn1.getBody()->getList4()->getElement(1)->getSequence1()->getRecord3()->getField1() == 3);
	CPPUNIT_ASSERT(m_MsgIn1.getBody()->getList4()->getElement(1)->getSequence1()->getRecord3()->getField2() == 1284);
    
    m_MsgIn1.getBody()->getList4()->deleteElement(1);
    CPPUNIT_ASSERT(m_MsgIn1.getBody()->getList4()->getNumberOfElements() == 1);
    
    CPPUNIT_ASSERT(m_MsgIn1.getBody()->getList4()->getElement(0)->getFieldValue() == 1);
    CPPUNIT_ASSERT(m_MsgIn1.getBody()->getList4()->getElement(0)->getList1()->getNumberOfElements() == 2);
	CPPUNIT_ASSERT(m_MsgIn1.getBody()->getList4()->getElement(0)->getList1()->getElement(0)->getField1() == 3);
	CPPUNIT_ASSERT(m_MsgIn1.getBody()->getList4()->getElement(0)->getList1()->getElement(1)->getField1() == 4);
    
    m_MsgIn1.getBody()->getList4()->deleteElement(0);
    CPPUNIT_ASSERT(m_MsgIn1.getBody()->getList4()->getNumberOfElements() == 0);
             
    OUT_1 << "  [completed test (testListOperations)] " << std::endl;
       
  }

  static CppUnit::Test *suite()
  {
    CppUnit::TestSuite *suiteOfTests = new CppUnit::TestSuite( "JTSList4MessageTest" );
    
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSList4MessageTest>( 
                                   "testConstructionDefaults", 
                                   &JTSList4MessageTest::testConstructionDefaults ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSList4MessageTest>( 
                                   "testSetGetOperations (TP_3.3.5.1)", 
                                   &JTSList4MessageTest::testSetGetOperations ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSList4MessageTest>( 
                                   "testEquality", 
                                   &JTSList4MessageTest::testEquality ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSList4MessageTest>(
                                   "testInequality",
                                   &JTSList4MessageTest::testInequality ) );

    suiteOfTests->addTest( new CppUnit::TestCaller<JTSList4MessageTest>( 
                                   "testEncodeToSpec (TP_3.3.5.2)", 
                                   &JTSList4MessageTest::testEncodeToSpec ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSList4MessageTest>( 
                                   "testEncodeDecodeOperations (TP_3.3.5.3)", 
                                   &JTSList4MessageTest::testEncodeDecodeOperations ) );                                   
                                                                      
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSList4MessageTest>(
                                   "testListOperations",
                                   &JTSList4MessageTest::testListOperations ) );
                                   
    return suiteOfTests;
  }

};


int main( int ac, char **av )
{
  CppUnit::TextUi::TestRunner runner;
  runner.addTest( JTSList4MessageTest::suite() );
  
  OUT_0 << std::endl << "------------------------------------------------" << std::endl;
  OUT_0 << "Executing suite JTSList4MessageTest" << std::endl;
  runner.run();
  OUT_0 << std::endl << "Completed suite JTSList4MessageTest" << std::endl;
  OUT_0 << "------------------------------------------------" << std::endl;  
}
