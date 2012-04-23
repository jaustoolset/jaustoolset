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

#include "urn_org_jts_test_List2_1_0/Messages/MessageSet.h"

#include "cppunit/TestCaller.h"
#include "cppunit/TestCaller.h"
#include "cppunit/ui/text/TestRunner.h"
#include "cppunit/TestSuite.h"

using namespace urn_org_jts_test_List2_1_0;

#define OUT_0  std::cout
#define OUT_1  if (bVerbose<=2) std::cout 
#define OUT_2  if (bVerbose<=1) std::cout



class JTSList2MessageTest : public CppUnit::TestFixture {
             
private:
  MsgIn m_MsgIn1,m_MsgIn2;
  int bVerbose;
  
public:
  JTSList2MessageTest() {
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

    OUT_2 << "     --- verifying MsgIn constructs with default empty lists" << std::endl;

    CPPUNIT_ASSERT(m_MsgIn1.getBody()->getList2()->getNumberOfElements()==0);
    CPPUNIT_ASSERT(m_MsgIn2.getBody()->getList2()->getNumberOfElements()==0);  

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
    
	MsgIn::Body::List2::Sublist1 L1, L2;
	MsgIn::Body::List2::Sublist1::Record1 R1,R2, R3, R4;
    
    R1.setField1(5);
    R2.setField1(6);
	R3.setField1(7);
	R4.setField1(8);
    L1.addElement(R1);
    L1.addElement(R2);
	L2.addElement(R3);
	L2.addElement(R4);
	m_MsgIn1.getBody()->getList2()->addElement(L1);
	m_MsgIn1.getBody()->getList2()->addElement(L2);

    CPPUNIT_ASSERT(m_MsgIn1.getBody()->getList2()->getElement(0)->getElement(0)->getField1()==5);
    CPPUNIT_ASSERT(m_MsgIn1.getBody()->getList2()->getElement(0)->getElement(1)->getField1()==6);
	CPPUNIT_ASSERT(m_MsgIn1.getBody()->getList2()->getElement(1)->getElement(0)->getField1()==7);
    CPPUNIT_ASSERT(m_MsgIn1.getBody()->getList2()->getElement(1)->getElement(1)->getField1()==8);
    
	// Test copy constructor
	OUT_2 << "     --- verifying copy constructor" << std::endl;    
	m_MsgIn2 = m_MsgIn1;
    CPPUNIT_ASSERT( m_MsgIn1 == m_MsgIn2 );
	CPPUNIT_ASSERT( m_MsgIn1.getBody()->getList2()->getNumberOfElements() == m_MsgIn2.getBody()->getList2()->getNumberOfElements());        
	CPPUNIT_ASSERT( m_MsgIn1.getBody()->getList2()->getElement(0)->getNumberOfElements() == m_MsgIn2.getBody()->getList2()->getElement(0)->getNumberOfElements());        

    OUT_1 <<  "  [completed test (testSetGetOperations)] " << std::endl;
             
  }

  void testEncodeToSpec()
  {
    unsigned char buff[1024];
    int i;
	MsgIn::Body::List2::Sublist1 L1, L2;
	MsgIn::Body::List2::Sublist1::Record1 R1;
    
       
    OUT_1 << std::endl << "  [executing test (testEncodeToSpec)(TP_3.3.5.2)] " << std::endl;

    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(256);

	// First sublist should be three elements long
	for (int i=0; i<3; i++)
	{
		R1.setField1(i+4);
		L1.addElement(R1);
	}

	// Second sublist should be 7 elements long
	for (int i=0; i<7; i++)
	{
		R1.setField1(i+8);
		L2.addElement(R1);
	}

	// Add both sublists to the main list
	m_MsgIn1.getBody()->getList2()->addElement(L1);
	m_MsgIn1.getBody()->getList2()->addElement(L2);    
    
    OUT_2 << "     --- verifying message level encode is AS-5684 compliant" << std::endl;
    m_MsgIn1.encode(buff);

	CPPUNIT_ASSERT( m_MsgIn1.getSize() == 15);
    
    for (i=0;i<m_MsgIn1.getSize();i++)
       CPPUNIT_ASSERT( buff[i]==i );

    OUT_1 << "  [completed test (testEncodeToSpec)] " << std::endl;
       
  }
  
  void testEncodeDecodeOperations()
  {
    unsigned char buff[1024];
    int i;
	MsgIn::Body::List2::Sublist1 L1;
	MsgIn::Body::List2::Sublist1::Record1 R1;
    
    for (i=0;i<1024;i++)
       buff[i] = ((2101*i)+65543) % 256;
    
    OUT_1 << std::endl << "  [executing test (testEncodeDecodeOperations)(TP_3.3.5.3)] " << std::endl;
    
    OUT_2 << "     --- verifying message level encode/decode reciprocity" << std::endl;
    

    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(41);
	m_MsgIn1.getBody()->getList2()->addElement(L1);
    m_MsgIn1.getBody()->getList2()->getElement(0)->addElement(R1);
    m_MsgIn1.getBody()->getList2()->getElement(0)->addElement(R1);    
    m_MsgIn1.getBody()->getList2()->getElement(0)->getElement(0)->setField1(15);
    m_MsgIn1.getBody()->getList2()->getElement(0)->getElement(1)->setField1(0);
    
    m_MsgIn2.getHeader()->getHeaderRec()->setMessageID(127);
	m_MsgIn2.getBody()->getList2()->addElement(L1);
    m_MsgIn2.getBody()->getList2()->getElement(0)->addElement(R1);    
    m_MsgIn2.getBody()->getList2()->getElement(0)->getElement(0)->setField1(127);
    
    m_MsgIn1.encode(buff);
    m_MsgIn2.decode(buff);

    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getHeaderRec()->getMessageID() == 41);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList2()->getNumberOfElements()==1);
	CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList2()->getElement(0)->getNumberOfElements()==2);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList2()->getElement(0)->getElement(0)->getField1() == 15);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList2()->getElement(0)->getElement(1)->getField1() == 0);    
    
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

    m_MsgIn2.getBody()->getList2()->getElement(0)->getElement(0)->setField1(255);
    m_MsgIn2.getBody()->getList2()->getElement(0)->getElement(1)->setField1(255);
    R1.setField1(255);
    m_MsgIn2.getBody()->getList2()->getElement(0)->addElement(R1);
    
    m_MsgIn1.getBody()->encode(buff);
    m_MsgIn2.getBody()->decode(buff);
    
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList2()->getNumberOfElements()==1);
	CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList2()->getElement(0)->getNumberOfElements()==2);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList2()->getElement(0)->getElement(0)->getField1() == 15);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList2()->getElement(0)->getElement(1)->getField1() == 0); 


    OUT_2 << "     --- verifying list level encode/decode reciprocity" << std::endl;
    
    m_MsgIn2.getBody()->getList2()->getElement(0)->getElement(0)->setField1(255);
    m_MsgIn2.getBody()->getList2()->getElement(0)->getElement(1)->setField1(255);
    R1.setField1(255);
    m_MsgIn2.getBody()->getList2()->getElement(0)->addElement(R1);
    
    m_MsgIn1.getBody()->getList2()->encode(buff);
    m_MsgIn2.getBody()->getList2()->decode(buff);
    
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList2()->getNumberOfElements()==1);
	CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList2()->getElement(0)->getNumberOfElements()==2);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList2()->getElement(0)->getElement(0)->getField1() == 15);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList2()->getElement(0)->getElement(1)->getField1() == 0); 
    
    OUT_2 << "     --- verifying record level encode/decode reciprocity" << std::endl;
    m_MsgIn2.getBody()->getList2()->getElement(0)->getElement(0)->setField1(255);
    
    m_MsgIn1.getBody()->getList2()->getElement(0)->getElement(0)->encode(buff);
    m_MsgIn2.getBody()->getList2()->getElement(0)->getElement(0)->decode(buff);
    
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getList2()->getElement(0)->getElement(0)->getField1() == 15);
    
            
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
	MsgIn::Body::List2 List;
	MsgIn::Body::List2::Sublist1 L1;
	MsgIn::Body::List2::Sublist1::Record1 R1;
	m_MsgIn2.getBody()->setList2( List );
	m_MsgIn2.getBody()->setList2( List ); 
    int i,j;
    
    OUT_1 << std::endl << "  [executing test (testListOperations)] " << std::endl;
    
    OUT_2 << "     --- verifying MsgIn getNumberOfElements with Add and Delete operations" << std::endl;    
    
    for (i=0;i<4;i++) {
       CPPUNIT_ASSERT(m_MsgIn2.getBody()->getList2()->getNumberOfElements()==i);
	   m_MsgIn2.getBody()->getList2()->addElement(L1);

       for (j=0;j<4;j++) {
          CPPUNIT_ASSERT(m_MsgIn2.getBody()->getList2()->getElement(i)->getNumberOfElements()==j);
          m_MsgIn2.getBody()->getList2()->getElement(i)->addElement(R1);           
       }
    }
	for (i=4;i>0;i--) {
		CPPUNIT_ASSERT(m_MsgIn2.getBody()->getList2()->getNumberOfElements() == i);
		m_MsgIn2.getBody()->getList2()->deleteLastElement();
	}

	OUT_2 << "     --- verifying deleteElement preserves order & corrects count of remaining elements" << std::endl;                          
    for (i=0;i<4;i++) {
		m_MsgIn1.getBody()->getList2()->addElement(L1);

		for (j=0; j<4; j++) {
	      m_MsgIn1.getBody()->getList2()->getElement(i)->addElement(R1);
		  m_MsgIn1.getBody()->getList2()->getElement(i)->getElement(j)->setField1( (i*4)+j );
		}
    }

	// Delete a sampling of elemments from the top list and the sublists...
    m_MsgIn1.getBody()->getList2()->deleteElement(0);
	m_MsgIn1.getBody()->getList2()->getElement(0)->deleteElement(0);
	m_MsgIn1.getBody()->getList2()->getElement(1)->deleteElement(1);
	m_MsgIn1.getBody()->getList2()->getElement(2)->deleteElement(2);

	// Verify...
	CPPUNIT_ASSERT(m_MsgIn1.getBody()->getList2()->getNumberOfElements() == 3);
    for (i=0;i<3;i++) {

		CPPUNIT_ASSERT(m_MsgIn1.getBody()->getList2()->getElement(i)->getNumberOfElements() == 3);

		for (j=0; j<3; j++) {
			int target_value = (i>j ? ((i+1)*4)+j :  ((i+1)*4)+j+1); 
			 CPPUNIT_ASSERT(m_MsgIn1.getBody()->getList2()->getElement(i)->getElement(j)->getField1() == target_value);
		}
	}
    
    OUT_1 << "  [completed test (testListOperations)] " << std::endl;
       
  }

  static CppUnit::Test *suite()
  {
    CppUnit::TestSuite *suiteOfTests = new CppUnit::TestSuite( "JTSList2MessageTest" );
    
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSList2MessageTest>( 
                                   "testConstructionDefaults", 
                                   &JTSList2MessageTest::testConstructionDefaults ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSList2MessageTest>( 
                                   "testSetGetOperations (TP_3.3.5.1)", 
                                   &JTSList2MessageTest::testSetGetOperations ) );

    suiteOfTests->addTest( new CppUnit::TestCaller<JTSList2MessageTest>( 
                                   "testEquality", 
                                   &JTSList2MessageTest::testEquality ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSList2MessageTest>(
                                   "testInequality",
                                   &JTSList2MessageTest::testInequality ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSList2MessageTest>( 
                                   "testEncodeToSpec (TP_3.3.5.2)", 
                                   &JTSList2MessageTest::testEncodeToSpec ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSList2MessageTest>( 
                                   "testEncodeDecodeOperations (TP_3.3.5.3)", 
                                   &JTSList2MessageTest::testEncodeDecodeOperations ) );                                   
                                                                      
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSList2MessageTest>(
                                   "testListOperations",
                                   &JTSList2MessageTest::testListOperations ) );
                                   
    return suiteOfTests;
  }

};


int main( int ac, char **av )
{
  CppUnit::TextUi::TestRunner runner;
  runner.addTest( JTSList2MessageTest::suite() );
  
  OUT_0 << std::endl << "------------------------------------------------" << std::endl;
  OUT_0 << "Executing suite JTSList2MessageTest" << std::endl;
  runner.run();
  OUT_0 << std::endl << "Completed suite JTSList2MessageTest" << std::endl;
  OUT_0 << "------------------------------------------------" << std::endl;  
}

