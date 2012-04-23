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

#include "urn_org_jts_test_BitField1_1_0/Messages/MessageSet.h"

#include "cppunit/TestCaller.h"
#include "cppunit/TestCaller.h"
#include "cppunit/ui/text/TestRunner.h"
#include "cppunit/TestSuite.h"

using namespace urn_org_jts_test_BitField1_1_0;

#define OUT_0  std::cout
#define OUT_1  if (bVerbose<=2) std::cout 
#define OUT_2  if (bVerbose<=1) std::cout

class JTSBitField1MessageTest : public CppUnit::TestFixture {
             
private:
  MsgIn m_MsgIn1,m_MsgIn2;
  int bVerbose;
  
public:
  JTSBitField1MessageTest() {
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

    OUT_2 << "     --- verifying setMilliseconds/getMilliseconds reciprocity" << std::endl;    
    m_MsgIn1.getBody()->getRecord()->getBitField1()->setMilliseconds(715);
    m_MsgIn2.getBody()->getRecord()->getBitField1()->setMilliseconds(3);    
    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getRecord()->getBitField1()->getMilliseconds() == 715);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getMilliseconds() == 3);
    
    OUT_2 << "     --- verifying setSeconds/getSeconds reciprocity" << std::endl;    
    m_MsgIn1.getBody()->getRecord()->getBitField1()->setSeconds(41);
    m_MsgIn2.getBody()->getRecord()->getBitField1()->setSeconds(0);
    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getRecord()->getBitField1()->getMilliseconds() == 715);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getMilliseconds() == 3);        
    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getRecord()->getBitField1()->getSeconds() == 41);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getSeconds() == 0);

    OUT_2 << "     --- verifying setMinutes/getMinutes reciprocity" << std::endl;    
    m_MsgIn1.getBody()->getRecord()->getBitField1()->setMinutes(12);
    m_MsgIn2.getBody()->getRecord()->getBitField1()->setMinutes(59);
    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getRecord()->getBitField1()->getMilliseconds() == 715);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getMilliseconds() == 3);        
    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getRecord()->getBitField1()->getSeconds() == 41);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getSeconds() == 0);        
    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getRecord()->getBitField1()->getMinutes() == 12);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getMinutes() == 59);
        
    OUT_2 << "     --- verifying setHour/getHour reciprocity" << std::endl;    
    m_MsgIn1.getBody()->getRecord()->getBitField1()->setHour(12);
    m_MsgIn2.getBody()->getRecord()->getBitField1()->setHour(19);
    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getRecord()->getBitField1()->getMilliseconds() == 715);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getMilliseconds() == 3);        
    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getRecord()->getBitField1()->getSeconds() == 41);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getSeconds() == 0);        
    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getRecord()->getBitField1()->getMinutes() == 12);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getMinutes() == 59);        
    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getRecord()->getBitField1()->getHour() == 12);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getHour() == 19);

    OUT_2 << "     --- verifying setDay/getDay reciprocity" << std::endl;    
    m_MsgIn1.getBody()->getRecord()->getBitField1()->setDay(1);
    m_MsgIn2.getBody()->getRecord()->getBitField1()->setDay(31);
    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getRecord()->getBitField1()->getMilliseconds() == 715);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getMilliseconds() == 3);        
    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getRecord()->getBitField1()->getSeconds() == 41);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getSeconds() == 0);        
    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getRecord()->getBitField1()->getMinutes() == 12);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getMinutes() == 59);        
    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getRecord()->getBitField1()->getHour() == 12);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getHour() == 19);        
    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getRecord()->getBitField1()->getDay() == 1);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getDay() == 31);
    
    OUT_1 <<  "  [completed test (testSetGetOperations)] " << std::endl;
             
  }

  void testEncodeToSpec()
  {
    unsigned char buff[1024];
    unsigned char ho[4];
    int i;
       
    OUT_1 << std::endl << "  [executing test (testEncodeToSpec)(TP_3.3.5.2)] " << std::endl;
    
    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(256);
    
    m_MsgIn1.getBody()->getRecord()->getBitField1()->setMilliseconds(770);
    m_MsgIn1.getBody()->getRecord()->getBitField1()->setSeconds(0);
    m_MsgIn1.getBody()->getRecord()->getBitField1()->setMinutes(4);
    m_MsgIn1.getBody()->getRecord()->getBitField1()->setHour(20);
    m_MsgIn1.getBody()->getRecord()->getBitField1()->setDay(1);    
    

        
    OUT_2 << "     --- verifying message level encode is AS-5684 compliant" << std::endl;
    m_MsgIn1.encode(buff);
/*    
    OUT_0 << "---------------------------------" << std::endl;
    for (i=0;i<m_MsgIn1.getSize();i++) {
        if (!(i%16)) OUT_0 << std::endl;
        ho[0] = buff[i]/16;
        if (ho[0]>9)
           ho[0]+=55;
        else
           ho[0]+=48;
        ho[1] = buff[i]%16;
        if (ho[1]>9)
           ho[1]+=55;
        else
           ho[1]+=48;
        ho[2]=' ';
        ho[3]=0;
        
        OUT_0 << ho;           
    }
    OUT_0 << std::endl << "---------------------------------" << std::endl;        
  */  
    for (i=0;i<m_MsgIn1.getSize()-1;i++)
       CPPUNIT_ASSERT( buff[i]==i );
    CPPUNIT_ASSERT(buff[m_MsgIn1.getSize()-1]==13);

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
    m_MsgIn1.getBody()->getRecord()->getBitField1()->setMilliseconds(333);
    m_MsgIn1.getBody()->getRecord()->getBitField1()->setSeconds(15);
    m_MsgIn1.getBody()->getRecord()->getBitField1()->setMinutes(49);
    m_MsgIn1.getBody()->getRecord()->getBitField1()->setHour(3);
    m_MsgIn1.getBody()->getRecord()->getBitField1()->setDay(21);

    m_MsgIn2.getHeader()->getHeaderRec()->setMessageID(0);
    m_MsgIn2.getBody()->getRecord()->getBitField1()->setMilliseconds(0);
    m_MsgIn2.getBody()->getRecord()->getBitField1()->setSeconds(0);
    m_MsgIn2.getBody()->getRecord()->getBitField1()->setMinutes(0);
    m_MsgIn2.getBody()->getRecord()->getBitField1()->setHour(0);
    m_MsgIn2.getBody()->getRecord()->getBitField1()->setHour(0);
    
    m_MsgIn1.encode(buff);
    m_MsgIn2.decode(buff);
    
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getHeaderRec()->getMessageID() == 41);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getMilliseconds()==333);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getSeconds()==15);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getMinutes()==49);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getHour()==3);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getDay()==21);
    
    OUT_2 << "     --- verifying header level encode/decode reciprocity" << std::endl;
    
    m_MsgIn2.getHeader()->getHeaderRec()->setMessageID(0);
    m_MsgIn1.getHeader()->encode(buff);
    m_MsgIn2.getHeader()->decode(buff);
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getHeaderRec()->getMessageID() == 41);

    m_MsgIn2.getHeader()->getHeaderRec()->setMessageID(0);
    m_MsgIn1.getHeader()->getHeaderRec()->encode(buff);
    m_MsgIn2.getHeader()->getHeaderRec()->decode(buff);
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getHeaderRec()->getMessageID() == 41);

    OUT_2 << "     --- verifying record level encode/decode reciprocity" << std::endl;

    m_MsgIn2.getBody()->getRecord()->getBitField1()->setMilliseconds(0);
    m_MsgIn2.getBody()->getRecord()->getBitField1()->setSeconds(0);
    m_MsgIn2.getBody()->getRecord()->getBitField1()->setMinutes(0);
    m_MsgIn2.getBody()->getRecord()->getBitField1()->setHour(0);
    m_MsgIn2.getBody()->getRecord()->getBitField1()->setHour(0);
        
    m_MsgIn1.getBody()->encode(buff);
    m_MsgIn2.getBody()->decode(buff);

    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getMilliseconds()==333);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getSeconds()==15);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getMinutes()==49);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getHour()==3);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getDay()==21);

    m_MsgIn2.getBody()->getRecord()->getBitField1()->setMilliseconds(0);
    m_MsgIn2.getBody()->getRecord()->getBitField1()->setSeconds(0);
    m_MsgIn2.getBody()->getRecord()->getBitField1()->setMinutes(0);
    m_MsgIn2.getBody()->getRecord()->getBitField1()->setHour(0);
    m_MsgIn2.getBody()->getRecord()->getBitField1()->setHour(0);
        
    m_MsgIn1.getBody()->getRecord()->encode(buff);
    m_MsgIn2.getBody()->getRecord()->decode(buff);

    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getMilliseconds()==333);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getSeconds()==15);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getMinutes()==49);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getHour()==3);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getDay()==21);
        
    m_MsgIn2.getBody()->getRecord()->getBitField1()->setMilliseconds(0);
    m_MsgIn2.getBody()->getRecord()->getBitField1()->setSeconds(0);
    m_MsgIn2.getBody()->getRecord()->getBitField1()->setMinutes(0);
    m_MsgIn2.getBody()->getRecord()->getBitField1()->setHour(0);
    m_MsgIn2.getBody()->getRecord()->getBitField1()->setHour(0);
        
    m_MsgIn1.getBody()->getRecord()->getBitField1()->encode(buff);
    m_MsgIn2.getBody()->getRecord()->getBitField1()->decode(buff);

    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getMilliseconds()==333);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getSeconds()==15);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getMinutes()==49);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getHour()==3);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getBitField1()->getDay()==21);
    
    
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
    CppUnit::TestSuite *suiteOfTests = new CppUnit::TestSuite( "JTSBitField1MessageTest" );
    
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSBitField1MessageTest>( 
                                   "testConstructionDefaults", 
                                   &JTSBitField1MessageTest::testConstructionDefaults ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSBitField1MessageTest>( 
                                   "testSetGetOperations (TP_3.3.5.1)", 
                                   &JTSBitField1MessageTest::testSetGetOperations ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSBitField1MessageTest>( 
                                   "testEncodeToSpec (TP_3.3.5.2)", 
                                   &JTSBitField1MessageTest::testEncodeToSpec ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSBitField1MessageTest>( 
                                   "testEncodeDecodeOperations (TP_3.3.5.3)", 
                                   &JTSBitField1MessageTest::testEncodeDecodeOperations ) );                                   
                                                                      
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSBitField1MessageTest>( 
                                   "testEquality", 
                                   &JTSBitField1MessageTest::testEquality ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSBitField1MessageTest>(
                                   "testInequality",
                                   &JTSBitField1MessageTest::testInequality ) );
    return suiteOfTests;
  }

};


int main( int ac, char **av )
{
  CppUnit::TextUi::TestRunner runner;
  runner.addTest( JTSBitField1MessageTest::suite() );
  
  OUT_0 << std::endl << "------------------------------------------------" << std::endl;
  OUT_0 << "Executing suite JTSBitField1MessageTest" << std::endl;
  runner.run();
  OUT_0 << std::endl << "Completed suite JTSBitField1MessageTest" << std::endl;
  OUT_0 << "------------------------------------------------" << std::endl;  
}

