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

#include "urn_org_jts_test_Header2_1_0/Messages/MessageSet.h"

#include "cppunit/TestCaller.h"
#include "cppunit/TestCaller.h"
#include "cppunit/ui/text/TestRunner.h"
#include "cppunit/TestSuite.h"

using namespace urn_org_jts_test_Header2_1_0;

#define OUT_0  std::cout
#define OUT_1  if (bVerbose<=2) std::cout 
#define OUT_2  if (bVerbose<=1) std::cout



class JTSList1MessageTest : public CppUnit::TestFixture {
             
private:
  MsgIn m_MsgIn1,m_MsgIn2;
  int bVerbose;
  
public:
  JTSList1MessageTest() {
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

    OUT_2 << "     --- verifying MsgIn constructs with default empty lists" << std::endl;

    CPPUNIT_ASSERT(m_MsgIn1.getHeader()->getList1()->getNumberOfElements()==0);
    CPPUNIT_ASSERT(m_MsgIn2.getHeader()->getList1()->getNumberOfElements()==0);  

  }
  
  void testSetGetOperations()
  {
    OUT_1 << std::endl << "  [executing test (testSetGetOperations)(TP_3.3.5.1)] " << std::endl;
        
    OUT_2 << "     --- verifying setField/getField reciprocity" << std::endl;
    
    MsgIn::Header::List1::Record1 R1,R2;
    
    R1.setField1(5);
    R1.setField2(500);
    R2.setField1(6);
    R2.setField2(600);
    m_MsgIn1.getHeader()->getList1()->addElement(R1);
    m_MsgIn2.getHeader()->getList1()->addElement(R2);
    CPPUNIT_ASSERT(m_MsgIn1.getHeader()->getList1()->getElement(0)->getField1()==5);
    CPPUNIT_ASSERT(m_MsgIn1.getHeader()->getList1()->getElement(0)->getField2()==500);
    CPPUNIT_ASSERT(m_MsgIn2.getHeader()->getList1()->getElement(0)->getField1()==6);
    CPPUNIT_ASSERT(m_MsgIn2.getHeader()->getList1()->getElement(0)->getField2()==600);
    m_MsgIn2.getHeader()->getList1()->getElement(0)->setField1(5);
    m_MsgIn2.getHeader()->getList1()->getElement(0)->setField2(500);
    CPPUNIT_ASSERT(m_MsgIn1.getHeader()->getList1()->getElement(0)->getField1()
                   ==m_MsgIn2.getHeader()->getList1()->getElement(0)->getField1());
    CPPUNIT_ASSERT(m_MsgIn1.getHeader()->getList1()->getElement(0)->getField2()
                   ==m_MsgIn2.getHeader()->getList1()->getElement(0)->getField2());

	// Test copy constructor
	OUT_2 << "     --- verifying copy constructor" << std::endl;    
	MsgIn copy1 = m_MsgIn1;
    CPPUNIT_ASSERT( m_MsgIn1 == copy1 );
	CPPUNIT_ASSERT( m_MsgIn1.getHeader()->getList1()->getNumberOfElements() == copy1.getHeader()->getList1()->getNumberOfElements());        

    OUT_1 <<  "  [completed test (testSetGetOperations)] " << std::endl;
             
  }

  void testEncodeToSpec()
  {
    unsigned char buff[1024];
    int i;
    MsgIn::Header::List1::Record1 R1,R2;
    
       
    OUT_1 << std::endl << "  [executing test (testEncodeToSpec)(TP_3.3.5.2)] " << std::endl;
         
    R1.setField1(3);
    R1.setField2(117835012);
    R2.setField1(8);
    R2.setField2(202050057);
    m_MsgIn1.getHeader()->getList1()->addElement(R1);
    m_MsgIn1.getHeader()->getList1()->addElement(R2);
    
    
    OUT_2 << "     --- verifying message level encode is AS-5684 compliant" << std::endl;
    m_MsgIn1.encode(buff);
    
    for (i=0;i<m_MsgIn1.getSize();i++)
       CPPUNIT_ASSERT( buff[i]==(i+2) );

    OUT_1 << "  [completed test (testEncodeToSpec)] " << std::endl;
       
  }
  
  void testEncodeDecodeOperations()
  {
    unsigned char buff[1024];
    int i;
    MsgIn::Header::List1::Record1 R1;
    
    for (i=0;i<1024;i++)
       buff[i] = ((2101*i)+65543) % 256;
    
    OUT_1 << std::endl << "  [executing test (testEncodeDecodeOperations)(TP_3.3.5.3)] " << std::endl;
    
    OUT_2 << "     --- verifying message level encode/decode reciprocity" << std::endl;
    
    m_MsgIn1.getHeader()->getList1()->addElement(R1);
    m_MsgIn1.getHeader()->getList1()->addElement(R1);    
    m_MsgIn1.getHeader()->getList1()->getElement(0)->setField1(15);
    m_MsgIn1.getHeader()->getList1()->getElement(0)->setField2(300);
    m_MsgIn1.getHeader()->getList1()->getElement(1)->setField1(0);
    m_MsgIn1.getHeader()->getList1()->getElement(1)->setField2(65825);
    
    m_MsgIn2.getHeader()->getList1()->addElement(R1);
    
    m_MsgIn2.getHeader()->getList1()->getElement(0)->setField1(127);
    m_MsgIn2.getHeader()->getList1()->getElement(0)->setField2(127);
    
    m_MsgIn1.encode(buff);
    m_MsgIn2.decode(buff);

    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getList1()->getNumberOfElements()==2);
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getList1()->getElement(0)->getField1() == 15);
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getList1()->getElement(0)->getField2() == 300);
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getList1()->getElement(1)->getField1() == 0);
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getList1()->getElement(1)->getField2() == 65825);
        
    OUT_2 << "     --- verifying body level encode/decode reciprocity" << std::endl;

    m_MsgIn2.getHeader()->getList1()->getElement(0)->setField1(255);
    m_MsgIn2.getHeader()->getList1()->getElement(0)->setField2(255);
    m_MsgIn2.getHeader()->getList1()->getElement(1)->setField1(255);
    m_MsgIn2.getHeader()->getList1()->getElement(1)->setField2(255);
    R1.setField1(255);
    R1.setField2(255);
    m_MsgIn2.getHeader()->getList1()->addElement(R1);
    
    m_MsgIn1.getHeader()->encode(buff);
    m_MsgIn2.getHeader()->decode(buff);
    
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getList1()->getNumberOfElements()==2);
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getList1()->getElement(0)->getField1() == 15);
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getList1()->getElement(0)->getField2() == 300);
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getList1()->getElement(1)->getField1() == 0);
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getList1()->getElement(1)->getField2() == 65825);


    OUT_2 << "     --- verifying list level encode/decode reciprocity" << std::endl;
    
    m_MsgIn2.getHeader()->getList1()->getElement(0)->setField1(255);
    m_MsgIn2.getHeader()->getList1()->getElement(0)->setField2(255);
    m_MsgIn2.getHeader()->getList1()->getElement(1)->setField1(255);
    m_MsgIn2.getHeader()->getList1()->getElement(1)->setField2(255);
    R1.setField1(255);
    R1.setField2(255);
    m_MsgIn2.getHeader()->getList1()->addElement(R1);
    
    m_MsgIn1.getHeader()->getList1()->encode(buff);
    m_MsgIn2.getHeader()->getList1()->decode(buff);
    
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getList1()->getNumberOfElements()==2);
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getList1()->getElement(0)->getField1() == 15);
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getList1()->getElement(0)->getField2() == 300);
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getList1()->getElement(1)->getField1() == 0);
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getList1()->getElement(1)->getField2() == 65825);
    
    OUT_2 << "     --- verifying record level encode/decode reciprocity" << std::endl;
    m_MsgIn2.getHeader()->getList1()->getElement(0)->setField1(255);
    m_MsgIn2.getHeader()->getList1()->getElement(0)->setField2(255);
    
    m_MsgIn1.getHeader()->getList1()->getElement(0)->encode(buff);
    m_MsgIn2.getHeader()->getList1()->getElement(0)->decode(buff);
    
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getList1()->getElement(0)->getField1() == 15);
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getList1()->getElement(0)->getField2() == 300);
    
            
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
    MsgIn::Header::List1::Record1 R1;
    int i,j;
    
    OUT_1 << std::endl << "  [executing test (testListOperations)] " << std::endl;
    
    OUT_2 << "     --- verifying MsgIn getNumberOfElements with Add and Delete operations" << std::endl;    
    i=j=0;  
    for (i=0;i<4;i++) {
       CPPUNIT_ASSERT(m_MsgIn1.getHeader()->getList1()->getNumberOfElements()==i);
       for (j=0;j<4;j++) {
          CPPUNIT_ASSERT(m_MsgIn2.getHeader()->getList1()->getNumberOfElements()==(i*4)+j);
          m_MsgIn2.getHeader()->getList1()->addElement(R1);           
       }
       m_MsgIn1.getHeader()->getList1()->addElement(R1);
    }   
    for (j=0,i=16;j<16;j++,i--) {
       CPPUNIT_ASSERT(m_MsgIn2.getHeader()->getList1()->getNumberOfElements() == i);
       m_MsgIn2.getHeader()->getList1()->deleteLastElement();
    }

    OUT_2 << "     --- verifying deleteElement preserves order & corrects count of remaining elements" << std::endl;                          
    for (i=0;i<4;i++) {
      m_MsgIn1.getHeader()->getList1()->getElement(i)->setField1(i+1);
      m_MsgIn1.getHeader()->getList1()->getElement(i)->setField2((i+1)*10);
    }
    

    m_MsgIn1.getHeader()->getList1()->deleteElement(0);
    CPPUNIT_ASSERT(m_MsgIn1.getHeader()->getList1()->getNumberOfElements() == 3);

    CPPUNIT_ASSERT(m_MsgIn1.getHeader()->getList1()->getElement(0)->getField1() == 2);
    CPPUNIT_ASSERT(m_MsgIn1.getHeader()->getList1()->getElement(0)->getField2() == 20);
    CPPUNIT_ASSERT(m_MsgIn1.getHeader()->getList1()->getElement(1)->getField1() == 3);
    CPPUNIT_ASSERT(m_MsgIn1.getHeader()->getList1()->getElement(1)->getField2() == 30);
    CPPUNIT_ASSERT(m_MsgIn1.getHeader()->getList1()->getElement(2)->getField1() == 4);
    CPPUNIT_ASSERT(m_MsgIn1.getHeader()->getList1()->getElement(2)->getField2() == 40);
    
    m_MsgIn1.getHeader()->getList1()->deleteElement(1);
    CPPUNIT_ASSERT(m_MsgIn1.getHeader()->getList1()->getNumberOfElements() == 2);
    
    CPPUNIT_ASSERT(m_MsgIn1.getHeader()->getList1()->getElement(0)->getField1() == 2);
    CPPUNIT_ASSERT(m_MsgIn1.getHeader()->getList1()->getElement(0)->getField2() == 20);
    CPPUNIT_ASSERT(m_MsgIn1.getHeader()->getList1()->getElement(1)->getField1() == 4);
    CPPUNIT_ASSERT(m_MsgIn1.getHeader()->getList1()->getElement(1)->getField2() == 40);
    
    m_MsgIn1.getHeader()->getList1()->deleteElement(1);
    CPPUNIT_ASSERT(m_MsgIn1.getHeader()->getList1()->getNumberOfElements() == 1);
    
    CPPUNIT_ASSERT(m_MsgIn1.getHeader()->getList1()->getElement(0)->getField1() == 2);
    CPPUNIT_ASSERT(m_MsgIn1.getHeader()->getList1()->getElement(0)->getField2() == 20);
    
    m_MsgIn1.getHeader()->getList1()->deleteElement(0);        
    CPPUNIT_ASSERT(m_MsgIn1.getHeader()->getList1()->getNumberOfElements() == 0);
             
    OUT_1 << "  [completed test (testListOperations)] " << std::endl;
       
  }

  static CppUnit::Test *suite()
  {
    CppUnit::TestSuite *suiteOfTests = new CppUnit::TestSuite( "JTSList1MessageTest" );
    
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSList1MessageTest>( 
                                   "testConstructionDefaults", 
                                   &JTSList1MessageTest::testConstructionDefaults ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSList1MessageTest>( 
                                   "testSetGetOperations (TP_3.3.5.1)", 
                                   &JTSList1MessageTest::testSetGetOperations ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSList1MessageTest>( 
                                   "testEncodeToSpec (TP_3.3.5.2)", 
                                   &JTSList1MessageTest::testEncodeToSpec ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSList1MessageTest>( 
                                   "testEncodeDecodeOperations (TP_3.3.5.3)", 
                                   &JTSList1MessageTest::testEncodeDecodeOperations ) );                                   
                                                                      
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSList1MessageTest>( 
                                   "testEquality", 
                                   &JTSList1MessageTest::testEquality ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSList1MessageTest>(
                                   "testInequality",
                                   &JTSList1MessageTest::testInequality ) );

    suiteOfTests->addTest( new CppUnit::TestCaller<JTSList1MessageTest>(
                                   "testListOperations",
                                   &JTSList1MessageTest::testListOperations ) );
                                   
    return suiteOfTests;
  }

};


int main( int ac, char **av )
{
  CppUnit::TextUi::TestRunner runner;
  runner.addTest( JTSList1MessageTest::suite() );
  
  OUT_0 << std::endl << "------------------------------------------------" << std::endl;
  OUT_0 << "Executing suite JTSHeader2MessageTest" << std::endl;
  runner.run();
  OUT_0 << std::endl << "Completed suite JTSHeader2MessageTest" << std::endl;
  OUT_0 << "------------------------------------------------" << std::endl;  
}

