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
#include <stdio.h>

#include "urn_org_jts_test_FixedLengthString1_1_0/Messages/MessageSet.h"

#include "cppunit/TestCaller.h"
#include "cppunit/TestCaller.h"
#include "cppunit/ui/text/TestRunner.h"
#include "cppunit/TestSuite.h"

using namespace urn_org_jts_test_FixedLengthString1_1_0;

#define OUT_0  std::cout
#define OUT_1  if (bVerbose<=2) std::cout 
#define OUT_2  if (bVerbose<=1) std::cout

class JTSRecord15MessageTest : public CppUnit::TestFixture {
             
private:
  MsgIn m_MsgIn1,m_MsgIn2;
  int bVerbose;
  
public:
  JTSRecord15MessageTest() {
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

    OUT_2 << "     --- verifying MsgIn constructs with correct default DimensionSize" << std::endl;
    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getRecord15()->getNames()->getXSize() == 100 );
    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getRecord15()->getNames()->getYSize() == 10 );
    
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord15()->getNames()->getXSize() == 100 );
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord15()->getNames()->getYSize() == 10 );
    
    OUT_1 << "  [completed test (testConstructionDefaults)] " << std::endl;
  }
  
  void testSetGetOperations()
  {
    OUT_1 << std::endl << "  [executing test (testSetGetOperations)(TP_3.3.5.1)] " << std::endl;
    
    OUT_2 << "     --- verifying setMessageID/getMessageID reciprocity" << std::endl;
    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(5);
    CPPUNIT_ASSERT( m_MsgIn1.getHeader()->getHeaderRec()->getMessageID() == 5 );
    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(1);
    CPPUNIT_ASSERT( m_MsgIn1.getHeader()->getHeaderRec()->getMessageID() == 1 );

    OUT_2 << "     --- verifying set/getName reciprocity" << std::endl;    
    int i,j;
    std::string val;
    char lbuf[32];
    
    for (i=0;i<m_MsgIn1.getBody()->getRecord15()->getNames()->getXSize();i++) 
      for (j=0;j<m_MsgIn1.getBody()->getRecord15()->getNames()->getYSize();j++)
    {
        sprintf((char *)lbuf,"m_MsgIn1(%2.2ld,%2.2ld)",i,j); 
        val.clear();
        val.append(lbuf);   
        m_MsgIn1.getBody()->getRecord15()->getNames()->setName(i,j,val);
        sprintf((char *)lbuf,"m_MsgIn2(%2.2ld,%2.2ld)",i,j); 
        val.clear();
        val.append(lbuf);   
        m_MsgIn2.getBody()->getRecord15()->getNames()->setName(i,j,val);
    }
    
    int r;
    for (i=0;i<m_MsgIn1.getBody()->getRecord15()->getNames()->getXSize();i++) 
      for (j=0;j<m_MsgIn1.getBody()->getRecord15()->getNames()->getYSize();j++)
    {
        sprintf((char *)lbuf,"m_MsgIn1(%2.2ld,%2.2ld)",i,j); 
        val.clear();
        val.append(lbuf);   
        CPPUNIT_ASSERT( m_MsgIn1.getBody()->getRecord15()->getNames()->getName(i,j) == val);

        sprintf((char *)lbuf,"m_MsgIn2(%2.2ld,%2.2ld)",i,j); 
        val.clear();
        val.append(lbuf);   
        CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord15()->getNames()->getName(i,j) == val);
    }
    
        
    
    OUT_1 <<  "  [completed test (testSetGetOperations)] " << std::endl;
             
  }

  void testEncodeToSpec()
  {
    unsigned char buff[90000];
    int i,j;
    unsigned char val,buff2[81];
    jFixedLengthString *T;
    std::string S;
       
    OUT_1 << std::endl << "  [executing test (testEncodeToSpec)(TP_3.3.5.2)] " << std::endl;
    
    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(256);
    val = 2;
    for (j=0;j<m_MsgIn1.getBody()->getRecord15()->getNames()->getYSize();j++)
       for (i=0;i<m_MsgIn1.getBody()->getRecord15()->getNames()->getXSize();i++)   
          
    {
        memset(buff2,val,sizeof(buff2));
        buff2[80]=0;
        T = new jFixedLengthString((unsigned int)80,(const char *)buff2);
        m_MsgIn1.getBody()->getRecord15()->getNames()->setName(i,j,*T);
        val++;
    }
        
    OUT_2 << "     --- verifying message level encode is AS-5684 compliant" << std::endl;
    m_MsgIn1.encode(buff);
    CPPUNIT_ASSERT(buff[0]==0);
    CPPUNIT_ASSERT(buff[1]==1);
    val = 2;
    i=2;
    while (i<m_MsgIn1.getSize())          
    {
        for (j=i;j<i+80;j++)
           CPPUNIT_ASSERT(buff[j]==val );
        val++;
        i+=80;
    }
    
    
    OUT_1 << "  [completed test (testEncodeToSpec)] " << std::endl;
       
  }
  
  void testEncodeDecodeOperations()
  {
    unsigned char buff[90000];
    int i,j;
    std::string val;
    
    
    OUT_1 << std::endl << "  [executing test (testEncodeDecodeOperations)(TP_3.3.5.3)] " << std::endl;
    
    OUT_2 << "     --- verifying message level encode/decode reciprocity" << std::endl;

    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(41);
    m_MsgIn2.getHeader()->getHeaderRec()->setMessageID(0);

    char lbuf[32];
    
    for (i=0;i<m_MsgIn1.getBody()->getRecord15()->getNames()->getXSize();i++) 
      for (j=0;j<m_MsgIn1.getBody()->getRecord15()->getNames()->getYSize();j++)
    {
        sprintf((char *)lbuf,"m_MsgIn1(%2.2ld,%2.2ld)",i,j); 
        val.clear();
        val.append(lbuf);   
        m_MsgIn1.getBody()->getRecord15()->getNames()->setName(i,j,val);
        sprintf((char *)lbuf,"m_MsgIn2(%2.2ld,%2.2ld)",i,j); 
        val.clear();
        val.append(lbuf);   
        m_MsgIn2.getBody()->getRecord15()->getNames()->setName(i,j,val);
    }
    m_MsgIn1.encode(buff);
    m_MsgIn2.decode(buff);
    
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getHeaderRec()->getMessageID() == 41);
    for (i=0;i<m_MsgIn2.getBody()->getRecord15()->getNames()->getXSize();i++) 
      for (j=0;j<m_MsgIn2.getBody()->getRecord15()->getNames()->getYSize();j++)
    {
        sprintf((char *)lbuf,"m_MsgIn1(%2.2ld,%2.2ld)",i,j); 
        val.clear();
        val.append(lbuf);   
          
        CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord15()->getNames()->getName(i,j) == val);
    }
    
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

    for (i=0;i<m_MsgIn2.getBody()->getRecord15()->getNames()->getXSize();i++) 
      for (j=0;j<m_MsgIn2.getBody()->getRecord15()->getNames()->getYSize();j++)
    {
        sprintf((char *)lbuf,"m_MsgIn2(%2.2ld,%2.2ld)",i,j); 
        val.clear();
        val.append(lbuf);   
        m_MsgIn2.getBody()->getRecord15()->getNames()->setName(i,j,val);
    }
        
    m_MsgIn1.getBody()->encode(buff);
    m_MsgIn2.getBody()->decode(buff);
    
    for (i=0;i<m_MsgIn2.getBody()->getRecord15()->getNames()->getXSize();i++) 
      for (j=0;j<m_MsgIn2.getBody()->getRecord15()->getNames()->getYSize();j++)
    {
        sprintf((char *)lbuf,"m_MsgIn1(%2.2ld,%2.2ld)",i,j); 
        val.clear();
        val.append(lbuf);   
          
        CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord15()->getNames()->getName(i,j) == val);
    }

    for (i=0;i<m_MsgIn2.getBody()->getRecord15()->getNames()->getXSize();i++) 
      for (j=0;j<m_MsgIn2.getBody()->getRecord15()->getNames()->getYSize();j++)
    {
        sprintf((char *)lbuf,"m_MsgIn2(%2.2ld,%2.2ld)",i,j); 
        val.clear();
        val.append(lbuf);   
        m_MsgIn2.getBody()->getRecord15()->getNames()->setName(i,j,val);
    }
        
    m_MsgIn1.getBody()->getRecord15()->encode(buff);
    m_MsgIn2.getBody()->getRecord15()->decode(buff);
    
    for (i=0;i<m_MsgIn2.getBody()->getRecord15()->getNames()->getXSize();i++) 
      for (j=0;j<m_MsgIn2.getBody()->getRecord15()->getNames()->getYSize();j++)
    {
        sprintf((char *)lbuf,"m_MsgIn1(%2.2ld,%2.2ld)",i,j); 
        val.clear();
        val.append(lbuf);   
          
        CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord15()->getNames()->getName(i,j) == val);
    }
        
    for (i=0;i<m_MsgIn2.getBody()->getRecord15()->getNames()->getXSize();i++) 
      for (j=0;j<m_MsgIn2.getBody()->getRecord15()->getNames()->getYSize();j++)
    {
        sprintf((char *)lbuf,"m_MsgIn2(%2.2ld,%2.2ld)",i,j); 
        val.clear();
        val.append(lbuf);   
        m_MsgIn2.getBody()->getRecord15()->getNames()->setName(i,j,val);
    }
        
    m_MsgIn1.getBody()->getRecord15()->getNames()->encode(buff);
    m_MsgIn2.getBody()->getRecord15()->getNames()->decode(buff);
    
    for (i=0;i<m_MsgIn2.getBody()->getRecord15()->getNames()->getXSize();i++) 
      for (j=0;j<m_MsgIn2.getBody()->getRecord15()->getNames()->getYSize();j++)
    {
        sprintf((char *)lbuf,"m_MsgIn1(%2.2ld,%2.2ld)",i,j); 
        val.clear();
        val.append(lbuf);   
          
        CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord15()->getNames()->getName(i,j) == val);
    }
    
    
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
    CppUnit::TestSuite *suiteOfTests = new CppUnit::TestSuite( "JTSRecord15MessageTest" );
    
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSRecord15MessageTest>( 
                                   "testConstructionDefaults", 
                                   &JTSRecord15MessageTest::testConstructionDefaults ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSRecord15MessageTest>( 
                                   "testSetGetOperations (TP_3.3.5.1)", 
                                   &JTSRecord15MessageTest::testSetGetOperations ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSRecord15MessageTest>( 
                                   "testEncodeToSpec (TP_3.3.5.2)", 
                                   &JTSRecord15MessageTest::testEncodeToSpec ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSRecord15MessageTest>( 
                                   "testEncodeDecodeOperations (TP_3.3.5.3)", 
                                   &JTSRecord15MessageTest::testEncodeDecodeOperations ) );                                   
                                                                      
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSRecord15MessageTest>( 
                                   "testEquality", 
                                   &JTSRecord15MessageTest::testEquality ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSRecord15MessageTest>(
                                   "testInequality",
                                   &JTSRecord15MessageTest::testInequality ) );
    return suiteOfTests;
  }

};


int main( int ac, char **av )
{
  CppUnit::TextUi::TestRunner runner;
  runner.addTest( JTSRecord15MessageTest::suite() );
  
  OUT_0 << std::endl << "------------------------------------------------" << std::endl;
  OUT_0 << "Executing suite JTSRecord15MessageTest" << std::endl;
  runner.run();
  OUT_0 << std::endl << "Completed suite JTSRecord15MessageTest" << std::endl;
  OUT_0 << "------------------------------------------------" << std::endl;  
}

