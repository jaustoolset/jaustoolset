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

#include "urn_org_jts_test_VariableLengthField1_1_0/Messages/MessageSet.h"

#include "cppunit/TestCaller.h"
#include "cppunit/TestCaller.h"
#include "cppunit/ui/text/TestRunner.h"
#include "cppunit/TestSuite.h"

using namespace urn_org_jts_test_VariableLengthField1_1_0;

#define OUT_0  std::cout
#define OUT_1  if (bVerbose<=2) std::cout 
#define OUT_2  if (bVerbose<=1) std::cout

class JTSBody5MessageTest : public CppUnit::TestFixture {
             
private:
  MsgIn m_MsgIn1,m_MsgIn2;
  int bVerbose;
  
public:
  JTSBody5MessageTest() {
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
    unsigned char buff1[4096],buff2[4096],t;
    int i;
    
    OUT_1 << std::endl << "  [executing test (testSetGetOperations)(TP_3.3.5.1)] " << std::endl;
    
    OUT_2 << "     --- verifying setMessageID/getMessageID reciprocity" << std::endl;
    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(5);
    CPPUNIT_ASSERT( m_MsgIn1.getHeader()->getHeaderRec()->getMessageID() == 5 );
    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(1);
    CPPUNIT_ASSERT( m_MsgIn1.getHeader()->getHeaderRec()->getMessageID() == 1 );
    
    OUT_2 << "     --- verifying setField/getField reciprocity" << std::endl;    
    m_MsgIn1.getBody5()->getRecord5()->setField1(126);
    m_MsgIn2.getBody5()->getRecord5()->setField1(-127);
    m_MsgIn1.getBody5()->getRecord5()->setField2(-40000);
    m_MsgIn2.getBody5()->getRecord5()->setField2(43210);
    m_MsgIn1.getBody5()->getRecord5()->setField3(0.5);
    m_MsgIn2.getBody5()->getRecord5()->setField3(-32.125);
    m_MsgIn1.getBody5()->getRecord5()->setField4(255);
    m_MsgIn2.getBody5()->getRecord5()->setField4(0);
        
    CPPUNIT_ASSERT( m_MsgIn1.getBody5()->getRecord5()->getField1() == 126);
    CPPUNIT_ASSERT( m_MsgIn2.getBody5()->getRecord5()->getField1() == -127);    
    CPPUNIT_ASSERT( m_MsgIn1.getBody5()->getRecord5()->getField2() == -40000);
    CPPUNIT_ASSERT( m_MsgIn2.getBody5()->getRecord5()->getField2() == 43210);    
    CPPUNIT_ASSERT( m_MsgIn1.getBody5()->getRecord5()->getField3() == 0.5);
    CPPUNIT_ASSERT( m_MsgIn2.getBody5()->getRecord5()->getField3() == -32.125);    
    CPPUNIT_ASSERT( m_MsgIn1.getBody5()->getRecord5()->getField4() == 255);
    CPPUNIT_ASSERT( m_MsgIn2.getBody5()->getRecord5()->getField4() == 0);    
    

    OUT_2 << "     --- verifying set/getData reciprocity and getLength consistency" << std::endl;   
    for (i=0,t=51;i<4000;i++) {
        buff1[i]=t;
        t=(t*71)+13;
        buff2[i]=t;
        t=(t*71)+13;        
    } 
    m_MsgIn1.getBody5()->getRecord5()->getVariableLengthField1()->set(4000,buff1);
    m_MsgIn2.getBody5()->getRecord5()->getVariableLengthField1()->set(4000,buff2);

    CPPUNIT_ASSERT( m_MsgIn1.getBody5()->getRecord5()->getVariableLengthField1()->getLength() == 4000);
    CPPUNIT_ASSERT( m_MsgIn2.getBody5()->getRecord5()->getVariableLengthField1()->getLength() == 4000);
    
    CPPUNIT_ASSERT( m_MsgIn1.getBody5()->getRecord5()->getField1() == 126);
    CPPUNIT_ASSERT( m_MsgIn2.getBody5()->getRecord5()->getField1() == -127);    
    CPPUNIT_ASSERT( m_MsgIn1.getBody5()->getRecord5()->getField2() == -40000);
    CPPUNIT_ASSERT( m_MsgIn2.getBody5()->getRecord5()->getField2() == 43210);    
    CPPUNIT_ASSERT( m_MsgIn1.getBody5()->getRecord5()->getField3() == 0.5);
    CPPUNIT_ASSERT( m_MsgIn2.getBody5()->getRecord5()->getField3() == -32.125);    
    CPPUNIT_ASSERT( m_MsgIn1.getBody5()->getRecord5()->getField4() == 255);
    CPPUNIT_ASSERT( m_MsgIn2.getBody5()->getRecord5()->getField4() == 0);    
    

    for (i=0;i<4000;i++) {        
      CPPUNIT_ASSERT( m_MsgIn1.getBody5()->getRecord5()->getVariableLengthField1()->getData()[i] == buff1[i]);
      CPPUNIT_ASSERT( m_MsgIn2.getBody5()->getRecord5()->getVariableLengthField1()->getData()[i] == buff2[i]);
    }    
    OUT_1 <<  "  [completed test (testSetGetOperations)] " << std::endl;
             
  }

  void testEncodeToSpec()
  {
    unsigned char buff[70000];
    unsigned int i;
    
    for (i=0;i<65786;i++)
       buff[i]=(i+6)%256;

    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(256);
    m_MsgIn1.getBody5()->getRecord5()->getVariableLengthField1()->set(65786,buff);  
    m_MsgIn1.getBody5()->getRecord5()->setField1(0);
    m_MsgIn1.getBody5()->getRecord5()->setField2(67305985);  //4.06321607e-34
    m_MsgIn1.getBody5()->getRecord5()->setField3(.0000000000000000000000000000000004063216068939722949825023716001);
    m_MsgIn1.getBody5()->getRecord5()->setField4(9);
     

    OUT_2 << "     --- verifying message level encode is AS-5684 compliant" << std::endl;
    m_MsgIn1.encode(buff);    
    
    for (i=0;i<m_MsgIn1.getSize();i++) { //FA 00 01 00
        if      (i==2) CPPUNIT_ASSERT( buff[i]==250 );
        else if (i==3) CPPUNIT_ASSERT( buff[i]==0);
        else if (i==4) CPPUNIT_ASSERT( buff[i]==1);
        else if (i==5) CPPUNIT_ASSERT( buff[i]==0);
        else         
           CPPUNIT_ASSERT( buff[i]==(i%256));
        
    }
       
    OUT_1 << "  [completed test (testEncodeToSpec)] " << std::endl;
       
  }
  
  void testEncodeDecodeOperations()
  {
    unsigned char buff[1024];
    unsigned char data[1000];
    int i;
    
    for (i=0;i<1024;i++)
       buff[i] = ((4101*i)+65543) % 256;
    
    for (i=0;i<1000;i++)
       data[i]=255-i;
       
    OUT_1 << std::endl << "  [executing test (testEncodeDecodeOperations)(TP_3.3.5.3)] " << std::endl;
    
    OUT_2 << "     --- verifying message level encode/decode reciprocity" << std::endl;

    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(41);
    m_MsgIn1.getBody5()->getRecord5()->getVariableLengthField1()->set(1000,data);

    m_MsgIn2.getHeader()->getHeaderRec()->setMessageID(127);
    m_MsgIn2.getBody5()->getRecord5()->getVariableLengthField1()->set(1011,buff);
    
    m_MsgIn1.getBody5()->getRecord5()->setField1(24);
    m_MsgIn1.getBody5()->getRecord5()->setField2(25);
    m_MsgIn1.getBody5()->getRecord5()->setField3(-16.75);
    m_MsgIn1.getBody5()->getRecord5()->setField4(27);

    m_MsgIn2.getBody5()->getRecord5()->setField1(127);
    m_MsgIn2.getBody5()->getRecord5()->setField2(127);
    m_MsgIn2.getBody5()->getRecord5()->setField3(127);
    m_MsgIn2.getBody5()->getRecord5()->setField4(127);
    
    m_MsgIn1.encode(buff);
    m_MsgIn2.decode(buff);
        
    
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getHeaderRec()->getMessageID() == 41);
    CPPUNIT_ASSERT( m_MsgIn2.getBody5()->getRecord5()->getVariableLengthField1()->getLength()==1000);
    for (i=0;i<1000;i++)
       CPPUNIT_ASSERT( m_MsgIn2.getBody5()->getRecord5()->getVariableLengthField1()->getData()[i]==data[i]);
    
    CPPUNIT_ASSERT( m_MsgIn2.getBody5()->getRecord5()->getField1()==24);
    CPPUNIT_ASSERT( m_MsgIn2.getBody5()->getRecord5()->getField2()==25);
    CPPUNIT_ASSERT( m_MsgIn2.getBody5()->getRecord5()->getField3()==-16.75);
    CPPUNIT_ASSERT( m_MsgIn2.getBody5()->getRecord5()->getField4()==27);
   
           
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

    for (i=0;i<1011;i++) buff[i]=0;
    m_MsgIn2.getBody5()->getRecord5()->getVariableLengthField1()->set(1011,buff);

    m_MsgIn2.getBody5()->getRecord5()->setField1(0);
    m_MsgIn2.getBody5()->getRecord5()->setField2(0);
    m_MsgIn2.getBody5()->getRecord5()->setField3(0);
    m_MsgIn2.getBody5()->getRecord5()->setField4(0);
    
        
    m_MsgIn1.getBody5()->encode(buff);
    m_MsgIn2.getBody5()->decode(buff);

    CPPUNIT_ASSERT( m_MsgIn2.getBody5()->getRecord5()->getVariableLengthField1()->getLength()==1000);
    for (i=0;i<1000;i++)
       CPPUNIT_ASSERT( m_MsgIn2.getBody5()->getRecord5()->getVariableLengthField1()->getData()[i]==data[i]);
    CPPUNIT_ASSERT( m_MsgIn2.getBody5()->getRecord5()->getField1()==24);
    CPPUNIT_ASSERT( m_MsgIn2.getBody5()->getRecord5()->getField2()==25);
    CPPUNIT_ASSERT( m_MsgIn2.getBody5()->getRecord5()->getField3()==-16.75);
    CPPUNIT_ASSERT( m_MsgIn2.getBody5()->getRecord5()->getField4()==27);
       

    for (i=0;i<1011;i++) buff[i]=0;
    m_MsgIn2.getHeader()->getHeaderRec()->setMessageID(127);
    m_MsgIn2.getBody5()->getRecord5()->getVariableLengthField1()->set(1011,buff);
    m_MsgIn2.getBody5()->getRecord5()->setField1(0);
    m_MsgIn2.getBody5()->getRecord5()->setField2(0);
    m_MsgIn2.getBody5()->getRecord5()->setField3(0);
    m_MsgIn2.getBody5()->getRecord5()->setField4(0);
        
    m_MsgIn1.getBody5()->getRecord5()->encode(buff);
    m_MsgIn2.getBody5()->getRecord5()->decode(buff);

    CPPUNIT_ASSERT( m_MsgIn2.getBody5()->getRecord5()->getVariableLengthField1()->getLength()==1000);
    for (i=0;i<1000;i++)
       CPPUNIT_ASSERT( m_MsgIn2.getBody5()->getRecord5()->getVariableLengthField1()->getData()[i]==data[i]);

    CPPUNIT_ASSERT( m_MsgIn2.getBody5()->getRecord5()->getField1()==24);
    CPPUNIT_ASSERT( m_MsgIn2.getBody5()->getRecord5()->getField2()==25);
    CPPUNIT_ASSERT( m_MsgIn2.getBody5()->getRecord5()->getField3()==-16.75);
    CPPUNIT_ASSERT( m_MsgIn2.getBody5()->getRecord5()->getField4()==27);
            
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
    CppUnit::TestSuite *suiteOfTests = new CppUnit::TestSuite( "JTSBody5MessageTest" );
    
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSBody5MessageTest>( 
                                   "testConstructionDefaults", 
                                   &JTSBody5MessageTest::testConstructionDefaults ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSBody5MessageTest>( 
                                   "testSetGetOperations (TP_3.3.5.1)", 
                                   &JTSBody5MessageTest::testSetGetOperations ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSBody5MessageTest>( 
                                   "testEncodeToSpec (TP_3.3.5.2)", 
                                   &JTSBody5MessageTest::testEncodeToSpec ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSBody5MessageTest>( 
                                   "testEncodeDecodeOperations (TP_3.3.5.3)", 
                                   &JTSBody5MessageTest::testEncodeDecodeOperations ) );                                   
                                                                      
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSBody5MessageTest>( 
                                   "testEquality", 
                                   &JTSBody5MessageTest::testEquality ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSBody5MessageTest>(
                                   "testInequality",
                                   &JTSBody5MessageTest::testInequality ) );
    return suiteOfTests;
  }

};


int main( int ac, char **av )
{
  CppUnit::TextUi::TestRunner runner;
  runner.addTest( JTSBody5MessageTest::suite() );
  
  OUT_0 << std::endl << "------------------------------------------------" << std::endl;
  OUT_0 << "Executing suite JTSBody5MessageTest" << std::endl;
  runner.run();
  OUT_0 << std::endl << "Completed suite JTSBody5MessageTest" << std::endl;
  OUT_0 << "------------------------------------------------" << std::endl;  
}

