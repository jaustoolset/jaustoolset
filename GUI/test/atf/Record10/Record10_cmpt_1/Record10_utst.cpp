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

#include "urn_org_jts_test_VariableField1_1_0/Messages/MessageSet.h"

#include "cppunit/TestCaller.h"
#include "cppunit/TestCaller.h"
#include "cppunit/ui/text/TestRunner.h"
#include "cppunit/TestSuite.h"

using namespace urn_org_jts_test_VariableField1_1_0;

#define OUT_0  std::cout
#define OUT_1  if (bVerbose<=2) std::cout 
#define OUT_2  if (bVerbose<=1) std::cout

class JTSRecord10MessageTest : public CppUnit::TestFixture {
             
private:
  MsgIn m_MsgIn1,m_MsgIn2;
  int bVerbose;
  
public:
  JTSRecord10MessageTest() {
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

    OUT_2 << "     --- verifying set/get reciprocity" << std::endl;    
        
    m_MsgIn1.getBody()->getRecord10()->getVariableField1()->setDegreeAsIntegerAt0(999);
    m_MsgIn2.getBody()->getRecord10()->getVariableField1()->setDegreeAsIntegerAt0(-100);    
    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getRecord10()->getVariableField1()->getDegreeAsIntegerAt0() == 999);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord10()->getVariableField1()->getDegreeAsIntegerAt0() == -100);

    m_MsgIn1.getBody()->getRecord10()->getVariableField1()->setKelvinAsUnsignedShortIntegerAt1(23);
    m_MsgIn2.getBody()->getRecord10()->getVariableField1()->setKelvinAsUnsignedShortIntegerAt1(15);    
    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getRecord10()->getVariableField1()->getKelvinAsUnsignedShortIntegerAt1() == 23);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord10()->getVariableField1()->getKelvinAsUnsignedShortIntegerAt1() == 15);
        
    OUT_1 <<  "  [completed test (testSetGetOperations)] " << std::endl;
             
  }

  void testEncodeToSpec()
  {
    unsigned char buff[1024];
    int i;
       
    OUT_1 << std::endl << "  [executing test (testEncodeToSpec)(TP_3.3.5.2)] " << std::endl;
    
    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(256);
    m_MsgIn1.getBody()->getRecord10()->getVariableField1()->setDegreeAsIntegerAt0(258);
    

    OUT_2 << "     --- verifying message level encode is AS-5684 compliant" << std::endl;
    m_MsgIn1.encode(buff);
    
    CPPUNIT_ASSERT( buff[0]==0 );
    CPPUNIT_ASSERT( buff[1]==1 );
    
    CPPUNIT_ASSERT( buff[2]==0 );    
    CPPUNIT_ASSERT( buff[3]==2 );
    CPPUNIT_ASSERT( buff[4]==1 );
    CPPUNIT_ASSERT( buff[5]==0 );
    CPPUNIT_ASSERT( buff[6]==0 );
    CPPUNIT_ASSERT( m_MsgIn1.getSize() == 7 );
    
    m_MsgIn1.getBody()->getRecord10()->getVariableField1()->setKelvinAsUnsignedShortIntegerAt1(770);
    m_MsgIn1.encode(buff);
    
    CPPUNIT_ASSERT( buff[0]==0 );
    CPPUNIT_ASSERT( buff[1]==1 );
    
    CPPUNIT_ASSERT( buff[2]==1 );
    CPPUNIT_ASSERT( buff[3]==2 );
    CPPUNIT_ASSERT( buff[4]==3 );
    CPPUNIT_ASSERT( m_MsgIn1.getSize() == 5 );
    
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

    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(41);
    m_MsgIn1.getBody()->getRecord10()->getVariableField1()->setDegreeAsIntegerAt0(35);

    m_MsgIn2.getHeader()->getHeaderRec()->setMessageID(127);
    m_MsgIn2.getBody()->getRecord10()->getVariableField1()->setKelvinAsUnsignedShortIntegerAt1(127);
    
    m_MsgIn1.encode(buff);
    m_MsgIn2.decode(buff);
    
    CPPUNIT_ASSERT( m_MsgIn2.getSize() == 7);
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getHeaderRec()->getMessageID() == 41);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord10()->getVariableField1()->getIndex() == 0);    
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord10()->getVariableField1()->getDegreeAsIntegerAt0()==35);
    
    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(66);
    m_MsgIn1.getBody()->getRecord10()->getVariableField1()->setKelvinAsUnsignedShortIntegerAt1(3500);
    
    m_MsgIn1.encode(buff);
    m_MsgIn2.decode(buff);

    CPPUNIT_ASSERT( m_MsgIn2.getSize() == 5);
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getHeaderRec()->getMessageID() == 66);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord10()->getVariableField1()->getIndex() == 1);    
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord10()->getVariableField1()->getKelvinAsUnsignedShortIntegerAt1()==3500);
        
    OUT_2 << "     --- verifying header level encode/decode reciprocity" << std::endl;
    
    m_MsgIn2.getHeader()->getHeaderRec()->setMessageID(0);
    m_MsgIn1.getHeader()->encode(buff);
    m_MsgIn2.getHeader()->decode(buff);
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getHeaderRec()->getMessageID() == 66);

    m_MsgIn2.getHeader()->getHeaderRec()->setMessageID(0);
    m_MsgIn1.getHeader()->getHeaderRec()->encode(buff);
    m_MsgIn2.getHeader()->getHeaderRec()->decode(buff);
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getHeaderRec()->getMessageID() == 66);

    OUT_2 << "     --- verifying record level encode/decode reciprocity" << std::endl;

    m_MsgIn1.getBody()->getRecord10()->getVariableField1()->setDegreeAsIntegerAt0(1111);
    m_MsgIn2.getBody()->getRecord10()->getVariableField1()->setKelvinAsUnsignedShortIntegerAt1(0);
    
    m_MsgIn1.getBody()->encode(buff);
    m_MsgIn2.getBody()->decode(buff);
    
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord10()->getVariableField1()->getIndex() == 0);    
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord10()->getVariableField1()->getDegreeAsIntegerAt0()==1111);
    
    m_MsgIn1.getBody()->getRecord10()->getVariableField1()->setKelvinAsUnsignedShortIntegerAt1(1234);
    
    m_MsgIn1.getBody()->encode(buff);
    m_MsgIn2.getBody()->decode(buff);

    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord10()->getVariableField1()->getIndex() == 1);    
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord10()->getVariableField1()->getKelvinAsUnsignedShortIntegerAt1()==1234);

    m_MsgIn1.getBody()->getRecord10()->getVariableField1()->setDegreeAsIntegerAt0(-100);
    m_MsgIn2.getBody()->getRecord10()->getVariableField1()->setKelvinAsUnsignedShortIntegerAt1(7);
    
    m_MsgIn1.getBody()->getRecord10()->getVariableField1()->encode(buff);
    m_MsgIn2.getBody()->getRecord10()->getVariableField1()->decode(buff);
    
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord10()->getVariableField1()->getIndex() == 0);    
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord10()->getVariableField1()->getDegreeAsIntegerAt0()==-100);
    
    m_MsgIn1.getBody()->getRecord10()->getVariableField1()->setKelvinAsUnsignedShortIntegerAt1(2323);
    
    m_MsgIn1.getBody()->getRecord10()->getVariableField1()->encode(buff);
    m_MsgIn2.getBody()->getRecord10()->getVariableField1()->decode(buff);

    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord10()->getVariableField1()->getIndex() == 1);    
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord10()->getVariableField1()->getKelvinAsUnsignedShortIntegerAt1()==2323);
            
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
    CppUnit::TestSuite *suiteOfTests = new CppUnit::TestSuite( "JTSRecord10MessageTest" );
    
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSRecord10MessageTest>( 
                                   "testConstructionDefaults", 
                                   &JTSRecord10MessageTest::testConstructionDefaults ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSRecord10MessageTest>( 
                                   "testSetGetOperations (TP_3.3.5.1)", 
                                   &JTSRecord10MessageTest::testSetGetOperations ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSRecord10MessageTest>( 
                                   "testEncodeToSpec (TP_3.3.5.2)", 
                                   &JTSRecord10MessageTest::testEncodeToSpec ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSRecord10MessageTest>( 
                                   "testEncodeDecodeOperations (TP_3.3.5.3)", 
                                   &JTSRecord10MessageTest::testEncodeDecodeOperations ) );                                   
                                                                      
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSRecord10MessageTest>( 
                                   "testEquality", 
                                   &JTSRecord10MessageTest::testEquality ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSRecord10MessageTest>(
                                   "testInequality",
                                   &JTSRecord10MessageTest::testInequality ) );
    return suiteOfTests;
  }

};


int main( int ac, char **av )
{
  CppUnit::TextUi::TestRunner runner;
  runner.addTest( JTSRecord10MessageTest::suite() );
  
  OUT_0 << std::endl << "------------------------------------------------" << std::endl;
  OUT_0 << "Executing suite JTSRecord10MessageTest" << std::endl;
  runner.run();
  OUT_0 << std::endl << "Completed suite JTSRecord10MessageTest" << std::endl;
  OUT_0 << "------------------------------------------------" << std::endl;  
}

