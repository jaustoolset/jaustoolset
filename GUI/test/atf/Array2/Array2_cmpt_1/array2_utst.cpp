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

#include "urn_org_jts_test_Array2_1_0/Messages/MessageSet.h"

#include "cppunit/TestCaller.h"
#include "cppunit/TestCaller.h"
#include "cppunit/ui/text/TestRunner.h"
#include "cppunit/TestSuite.h"

using namespace urn_org_jts_test_Array2_1_0;

#define OUT_0  std::cout
#define OUT_1  if (bVerbose<=2) std::cout 
#define OUT_2  if (bVerbose<=1) std::cout

class JTSArray2MessageTest : public CppUnit::TestFixture {
             
private:
  MsgIn m_MsgIn1,m_MsgIn2;
  int bVerbose;
  
public:
  JTSArray2MessageTest() {
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
    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getRecord()->getArray2()->getRGBSize() == 3 );
    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getRecord()->getArray2()->getColor_VectorSize() == 1 );
    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getRecord()->getArray2()->getWidthSize() == 100 );
    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getRecord()->getArray2()->getHeightSize() == 100 );
    
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getArray2()->getRGBSize() == 3 );
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getArray2()->getColor_VectorSize() == 1 );
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getArray2()->getWidthSize() == 100 );
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getArray2()->getHeightSize() == 100 );
    
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

    OUT_2 << "     --- verifying setPixel/getPixel reciprocity" << std::endl;    
    int i,j,k,l;
    char val = 0;

    for (i=0;i<m_MsgIn1.getBody()->getRecord()->getArray2()->getRGBSize();i++) 
      for (j=0;j<m_MsgIn1.getBody()->getRecord()->getArray2()->getColor_VectorSize();j++)
        for (k=0;k<m_MsgIn1.getBody()->getRecord()->getArray2()->getWidthSize();k++)
          for (l=0;l<m_MsgIn1.getBody()->getRecord()->getArray2()->getHeightSize();l++)
    {
        m_MsgIn1.getBody()->getRecord()->getArray2()->setPixel(i,j,k,l, val);
        val++;
        m_MsgIn2.getBody()->getRecord()->getArray2()->setPixel(i,j,k,l, val);
        val++;
    }
    
    val = 0;
    int r;
    for (i=0;i<m_MsgIn1.getBody()->getRecord()->getArray2()->getRGBSize();i++) 
      for (j=0;j<m_MsgIn1.getBody()->getRecord()->getArray2()->getColor_VectorSize();j++)
        for (k=0;k<m_MsgIn1.getBody()->getRecord()->getArray2()->getWidthSize();k++)
          for (l=0;l<m_MsgIn1.getBody()->getRecord()->getArray2()->getHeightSize();l++)
    {
        if (m_MsgIn1.getBody()->getRecord()->getArray2()->getPixel(i,j,k,l) != val) {
           r = m_MsgIn1.getBody()->getRecord()->getArray2()->getPixel(i,j,k,l);
           OUT_0 << " at MsgIn1 (" << i << "," << j << "," << k << "," << l << "), expected " << val << ", found " << r << " ... " << std::endl;         
        }
        CPPUNIT_ASSERT( m_MsgIn1.getBody()->getRecord()->getArray2()->getPixel(i,j,k,l) == val);
        val++;
        if (m_MsgIn2.getBody()->getRecord()->getArray2()->getPixel(i,j,k,l) != val) {
           r = m_MsgIn2.getBody()->getRecord()->getArray2()->getPixel(i,j,k,l);                                                                 
           OUT_0 << " at MsgIn2 (" << i << "," << j << "," << k << "," << l << "), expected " << val << ", found " << r << " ... " << std::endl;
        }
        CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getArray2()->getPixel(i,j,k,l) == val);
        val++;
    }
    
        
    
    OUT_1 <<  "  [completed test (testSetGetOperations)] " << std::endl;
             
  }

  void testEncodeToSpec()
  {
    unsigned char buff[40000];
    int i,j,k,l;
    unsigned char val;
       
    OUT_1 << std::endl << "  [executing test (testEncodeToSpec)(TP_3.3.5.2)] " << std::endl;
    
    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(256);
    val = 2;
    for (l=0;l<m_MsgIn1.getBody()->getRecord()->getArray2()->getHeightSize();l++)
      for (k=0;k<m_MsgIn1.getBody()->getRecord()->getArray2()->getWidthSize();k++)
        for (j=0;j<m_MsgIn1.getBody()->getRecord()->getArray2()->getColor_VectorSize();j++)
          for (i=0;i<m_MsgIn1.getBody()->getRecord()->getArray2()->getRGBSize();i++)   
          
    {
        m_MsgIn1.getBody()->getRecord()->getArray2()->setPixel(i,j,k,l, val);
        val++;
    }
        
    OUT_2 << "     --- verifying message level encode is AS-5684 compliant" << std::endl;
    m_MsgIn1.encode(buff);
    for (i=0,val=0;i<m_MsgIn1.getSize();i++,val++) 
       CPPUNIT_ASSERT( buff[i]==val );
    
    OUT_1 << "  [completed test (testEncodeToSpec)] " << std::endl;
       
  }
  
  void testEncodeDecodeOperations()
  {
    unsigned char buff[40000];
    int i,j,k,l;
    char val;
    
    for (i=0;i<1024;i++)
       buff[i] = ((4101*i)+65543) % 256;
    
    OUT_1 << std::endl << "  [executing test (testEncodeDecodeOperations)(TP_3.3.5.3)] " << std::endl;
    
    OUT_2 << "     --- verifying message level encode/decode reciprocity" << std::endl;

    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(41);
    m_MsgIn2.getHeader()->getHeaderRec()->setMessageID(0);
    val = 0;
    for (i=0;i<m_MsgIn1.getBody()->getRecord()->getArray2()->getRGBSize();i++) 
      for (j=0;j<m_MsgIn1.getBody()->getRecord()->getArray2()->getColor_VectorSize();j++)
        for (k=0;k<m_MsgIn1.getBody()->getRecord()->getArray2()->getWidthSize();k++)
          for (l=0;l<m_MsgIn1.getBody()->getRecord()->getArray2()->getHeightSize();l++)
    {
        m_MsgIn1.getBody()->getRecord()->getArray2()->setPixel(i,j,k,l,val);
        val+=7;
        m_MsgIn2.getBody()->getRecord()->getArray2()->setPixel(i,j,k,l,0);
    }
    m_MsgIn1.encode(buff);
    m_MsgIn2.decode(buff);
    
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getHeaderRec()->getMessageID() == 41);
    val = 0;
    for (i=0;i<m_MsgIn2.getBody()->getRecord()->getArray2()->getRGBSize();i++) 
      for (j=0;j<m_MsgIn2.getBody()->getRecord()->getArray2()->getColor_VectorSize();j++)
        for (k=0;k<m_MsgIn2.getBody()->getRecord()->getArray2()->getWidthSize();k++)
          for (l=0;l<m_MsgIn2.getBody()->getRecord()->getArray2()->getHeightSize();l++)
    {
        CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getArray2()->getPixel(i,j,k,l) == val);
        val+=7;
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

    for (i=0;i<m_MsgIn1.getBody()->getRecord()->getArray2()->getRGBSize();i++) 
      for (j=0;j<m_MsgIn1.getBody()->getRecord()->getArray2()->getColor_VectorSize();j++)
        for (k=0;k<m_MsgIn1.getBody()->getRecord()->getArray2()->getWidthSize();k++)
          for (l=0;l<m_MsgIn1.getBody()->getRecord()->getArray2()->getHeightSize();l++)
        m_MsgIn2.getBody()->getRecord()->getArray2()->setPixel(i,j,k,l,0);
        
    m_MsgIn1.getBody()->encode(buff);
    m_MsgIn2.getBody()->decode(buff);
    
    val = 0;
    for (i=0;i<m_MsgIn2.getBody()->getRecord()->getArray2()->getRGBSize();i++) 
      for (j=0;j<m_MsgIn2.getBody()->getRecord()->getArray2()->getColor_VectorSize();j++)
        for (k=0;k<m_MsgIn2.getBody()->getRecord()->getArray2()->getWidthSize();k++)
          for (l=0;l<m_MsgIn2.getBody()->getRecord()->getArray2()->getHeightSize();l++)
    {
        CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getArray2()->getPixel(i,j,k,l) == val);
        val+=7;
    }

    for (i=0;i<m_MsgIn1.getBody()->getRecord()->getArray2()->getRGBSize();i++) 
      for (j=0;j<m_MsgIn1.getBody()->getRecord()->getArray2()->getColor_VectorSize();j++)
        for (k=0;k<m_MsgIn1.getBody()->getRecord()->getArray2()->getWidthSize();k++)
          for (l=0;l<m_MsgIn1.getBody()->getRecord()->getArray2()->getHeightSize();l++)
        m_MsgIn2.getBody()->getRecord()->getArray2()->setPixel(i,j,k,l,0);
        
    m_MsgIn1.getBody()->getRecord()->encode(buff);
    m_MsgIn2.getBody()->getRecord()->decode(buff);
    
    val = 0;
    for (i=0;i<m_MsgIn2.getBody()->getRecord()->getArray2()->getRGBSize();i++) 
      for (j=0;j<m_MsgIn2.getBody()->getRecord()->getArray2()->getColor_VectorSize();j++)
        for (k=0;k<m_MsgIn2.getBody()->getRecord()->getArray2()->getWidthSize();k++)
          for (l=0;l<m_MsgIn2.getBody()->getRecord()->getArray2()->getHeightSize();l++)
    {
        CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getArray2()->getPixel(i,j,k,l) == val);
        val+=7;
    }
        
    for (i=0;i<m_MsgIn1.getBody()->getRecord()->getArray2()->getRGBSize();i++) 
      for (j=0;j<m_MsgIn1.getBody()->getRecord()->getArray2()->getColor_VectorSize();j++)
        for (k=0;k<m_MsgIn1.getBody()->getRecord()->getArray2()->getWidthSize();k++)
          for (l=0;l<m_MsgIn1.getBody()->getRecord()->getArray2()->getHeightSize();l++)
        m_MsgIn2.getBody()->getRecord()->getArray2()->setPixel(i,j,k,l,0);
        
    m_MsgIn1.getBody()->getRecord()->getArray2()->encode(buff);
    m_MsgIn2.getBody()->getRecord()->getArray2()->decode(buff);
    
    val = 0;
    for (i=0;i<m_MsgIn2.getBody()->getRecord()->getArray2()->getRGBSize();i++) 
      for (j=0;j<m_MsgIn2.getBody()->getRecord()->getArray2()->getColor_VectorSize();j++)
        for (k=0;k<m_MsgIn2.getBody()->getRecord()->getArray2()->getWidthSize();k++)
          for (l=0;l<m_MsgIn2.getBody()->getRecord()->getArray2()->getHeightSize();l++)
    {
        CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord()->getArray2()->getPixel(i,j,k,l) == val);
        val+=7;
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
    CppUnit::TestSuite *suiteOfTests = new CppUnit::TestSuite( "JTSArray2MessageTest" );
    
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSArray2MessageTest>( 
                                   "testConstructionDefaults", 
                                   &JTSArray2MessageTest::testConstructionDefaults ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSArray2MessageTest>( 
                                   "testSetGetOperations (TP_3.3.5.1)", 
                                   &JTSArray2MessageTest::testSetGetOperations ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSArray2MessageTest>( 
                                   "testEncodeToSpec (TP_3.3.5.2)", 
                                   &JTSArray2MessageTest::testEncodeToSpec ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSArray2MessageTest>( 
                                   "testEncodeDecodeOperations (TP_3.3.5.3)", 
                                   &JTSArray2MessageTest::testEncodeDecodeOperations ) );                                   
                                                                      
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSArray2MessageTest>( 
                                   "testEquality", 
                                   &JTSArray2MessageTest::testEquality ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSArray2MessageTest>(
                                   "testInequality",
                                   &JTSArray2MessageTest::testInequality ) );
    return suiteOfTests;
  }

};


int main( int ac, char **av )
{
  CppUnit::TextUi::TestRunner runner;
  runner.addTest( JTSArray2MessageTest::suite() );
  
  OUT_0 << std::endl << "------------------------------------------------" << std::endl;
  OUT_0 << "Executing suite JTSArray2MessageTest" << std::endl;
  runner.run();
  OUT_0 << std::endl << "Completed suite JTSArray2MessageTest" << std::endl;
  OUT_0 << "------------------------------------------------" << std::endl;  
}

