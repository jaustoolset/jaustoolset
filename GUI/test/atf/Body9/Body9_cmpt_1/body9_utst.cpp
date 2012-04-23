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

#include "urn_org_jts_test_Record1_1_0/Messages/MessageSet.h"

#include "cppunit/TestCaller.h"
#include "cppunit/TestCaller.h"
#include "cppunit/ui/text/TestRunner.h"
#include "cppunit/TestSuite.h"

using namespace urn_org_jts_test_Record1_1_0;

#define OUT_0  std::cout
#define OUT_1  if (bVerbose<=2) std::cout 
#define OUT_2  if (bVerbose<=1) std::cout

class JTSBody9MessageTest : public CppUnit::TestFixture {
             
private:
  MsgIn m_MsgIn1,m_MsgIn2;
  int bVerbose;
  
public:
  JTSBody9MessageTest() {
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
  }
  
  void testSetGetOperations()
  {
	  unsigned char data[10] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
	  OUT_1 << std::endl << "  [executing test (testSetGetOperations)(TP_3.3.5.1)] " << std::endl;

	  CPPUNIT_ASSERT( m_MsgIn1.getBody9()->getRecord9()->getPresenceVector() == 0 );

	  // Set the first entry in the record.
	  OUT_2 << "     --- verifying optional fixed field handling" << std::endl;
	  CPPUNIT_ASSERT ( m_MsgIn1.getBody9()->getRecord9()->isField1Valid() == false );
	  m_MsgIn1.getBody9()->getRecord9()->setField1(5);
	  CPPUNIT_ASSERT ( m_MsgIn1.getBody9()->getRecord9()->isField1Valid() == true );
	  CPPUNIT_ASSERT( m_MsgIn1.getBody9()->getRecord9()->getPresenceVector() == 1 );

	  OUT_2 << "     --- verifying optional variable length field handling" << std::endl;
	  CPPUNIT_ASSERT ( m_MsgIn1.getBody9()->getRecord9()->isVariableLengthField1Valid() == false );
	  MsgIn::Body9::Record9::VariableLengthField1 vl1; vl1.set(10, data);
	  m_MsgIn1.getBody9()->getRecord9()->setVariableLengthField1(vl1);
	  CPPUNIT_ASSERT ( m_MsgIn1.getBody9()->getRecord9()->isVariableLengthField1Valid() == true );
	  CPPUNIT_ASSERT( m_MsgIn1.getBody9()->getRecord9()->getPresenceVector() == 3 );

	  OUT_2 << "     --- verifying optional array handling" << std::endl;
	  CPPUNIT_ASSERT ( m_MsgIn1.getBody9()->getRecord9()->isArray1Valid() == false );
	  MsgIn::Body9::Record9::Array1 array1; array1.setElement1(0, 0);
	  m_MsgIn1.getBody9()->getRecord9()->setArray1(array1);
	  CPPUNIT_ASSERT ( m_MsgIn1.getBody9()->getRecord9()->isArray1Valid() == true );
	  CPPUNIT_ASSERT( m_MsgIn1.getBody9()->getRecord9()->getPresenceVector() == 7 );

	  OUT_2 << "     --- verifying optional bitfield handling" << std::endl;
	  CPPUNIT_ASSERT ( m_MsgIn1.getBody9()->getRecord9()->isBitField1Valid() == false );
	  MsgIn::Body9::Record9::BitField1 bf1; bf1.setSubfield1(1);
	  m_MsgIn1.getBody9()->getRecord9()->setBitField1(bf1);
	  CPPUNIT_ASSERT ( m_MsgIn1.getBody9()->getRecord9()->isBitField1Valid() == true );
	  CPPUNIT_ASSERT( m_MsgIn1.getBody9()->getRecord9()->getPresenceVector() == 15 );

	  OUT_2 << "     --- verifying optional fixed length string handling" << std::endl;
	  CPPUNIT_ASSERT ( m_MsgIn1.getBody9()->getRecord9()->isFixedLengthString1Valid() == false );
	  m_MsgIn1.getBody9()->getRecord9()->setFixedLengthString1("wtf");
	  CPPUNIT_ASSERT ( m_MsgIn1.getBody9()->getRecord9()->isFixedLengthString1Valid() == true );
	  CPPUNIT_ASSERT( m_MsgIn1.getBody9()->getRecord9()->getPresenceVector() == 31 );

	  OUT_2 << "     --- verifying optional variable field handling" << std::endl;
	  CPPUNIT_ASSERT ( m_MsgIn1.getBody9()->getRecord9()->isVariableField1Valid() == false );
	  MsgIn::Body9::Record9::VariableField1 vf1; vf1.setOneAsUnsignedByteAt0(1);
	  m_MsgIn1.getBody9()->getRecord9()->setVariableField1(vf1);
	  CPPUNIT_ASSERT ( m_MsgIn1.getBody9()->getRecord9()->isVariableField1Valid() == true );
	  CPPUNIT_ASSERT( m_MsgIn1.getBody9()->getRecord9()->getPresenceVector() == 63 );

	  OUT_2 << "     --- verifying optional variable length string handling" << std::endl;
	  CPPUNIT_ASSERT ( m_MsgIn1.getBody9()->getRecord9()->isVariableLengthString1Valid() == false );
	  m_MsgIn1.getBody9()->getRecord9()->setVariableLengthString1("jts");
	  CPPUNIT_ASSERT ( m_MsgIn1.getBody9()->getRecord9()->isVariableLengthString1Valid() == true );
	  CPPUNIT_ASSERT( m_MsgIn1.getBody9()->getRecord9()->getPresenceVector() == 127 );

	  OUT_2 << "     --- verifying optional variable format field handling" << std::endl;
	  CPPUNIT_ASSERT ( m_MsgIn1.getBody9()->getRecord9()->isVariableFormatField1Valid() == false );
	  MsgIn::Body9::Record9::VariableFormatField1 vff1; vff1.set(0, 10, data);
	  m_MsgIn1.getBody9()->getRecord9()->setVariableFormatField1(vff1);
	  CPPUNIT_ASSERT ( m_MsgIn1.getBody9()->getRecord9()->isVariableFormatField1Valid() == true );
	  CPPUNIT_ASSERT( m_MsgIn1.getBody9()->getRecord9()->getPresenceVector() == 255 );	  
  }

  void testEncodeToSpec()
  {
  }
  
  void testEncodeDecodeOperations()
  {
  }

  void testEquality()
  {
  }
  
  void testInequality()
  {
  }

  static CppUnit::Test *suite()
  {
    CppUnit::TestSuite *suiteOfTests = new CppUnit::TestSuite( "JTSBody9MessageTest" );
    
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSBody9MessageTest>( 
                                   "testSetGetOperations (TP_3.3.5.1)", 
                                   &JTSBody9MessageTest::testSetGetOperations ) );
                                   
    return suiteOfTests;
  }

};


int main( int ac, char **av )
{
  CppUnit::TextUi::TestRunner runner;
  runner.addTest( JTSBody9MessageTest::suite() );
  
  OUT_0 << std::endl << "------------------------------------------------" << std::endl;
  OUT_0 << "Executing suite JTSBody9MessageTest" << std::endl;
  runner.run();
  OUT_0 << std::endl << "Completed suite JTSBody9MessageTest" << std::endl;
  OUT_0 << "------------------------------------------------" << std::endl;  
}

