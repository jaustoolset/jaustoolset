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

#include "urn_org_jts_test_Sequence3_1_0/Messages/MessageSet.h"

#include "cppunit/TestCaller.h"
#include "cppunit/TestCaller.h"
#include "cppunit/ui/text/TestRunner.h"
#include "cppunit/TestSuite.h"

using namespace urn_org_jts_test_Sequence3_1_0;

#define OUT_0  std::cout
#define OUT_1  if (bVerbose<=2) std::cout 
#define OUT_2  if (bVerbose<=1) std::cout

class JTSSequence3MessageTest : public CppUnit::TestFixture {
             
private:
  MsgIn m_MsgIn1,m_MsgIn2;
  int bVerbose;
  
public:
  JTSSequence3MessageTest() {
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

	//test assignment and compare operators.
	MsgIn temp1, temp2, temp3;
    temp1.getBody()->getSequence3()->getVariant1()->getEventTypeRec()->setEventType(1);
	temp1.getBody()->getSequence3()->getVariant1()->setFieldValue(1);
	temp2.getBody()->getSequence3()->getVariant1()->getEventTypeRec()->setEventType(1);
	temp2.getBody()->getSequence3()->getVariant1()->setFieldValue(1);
	CPPUNIT_ASSERT( temp1 == temp2 );
	temp3 = temp1;
	CPPUNIT_ASSERT( temp1 == temp3 );
	CPPUNIT_ASSERT( temp3.getBody()->getSequence3()->getVariant1()->getFieldValue() == 1 );
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
    m_MsgIn1.getBody()->getSequence3()->getVariant1()->getMessageIDRec()->setMessageCode(65535);
    m_MsgIn2.getBody()->getSequence3()->getVariant1()->getMessageIDRec()->setMessageCode(0);
    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getSequence3()->getVariant1()->getMessageIDRec()->getMessageCode() == 65535);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getSequence3()->getVariant1()->getMessageIDRec()->getMessageCode() == 0);
        
    m_MsgIn1.getBody()->getSequence3()->getVariant1()->getEventTypeRec()->setEventType(1);
    //this should fail as event type may only be 0 or 1
    m_MsgIn1.getBody()->getSequence3()->getVariant1()->getEventTypeRec()->setEventType(129);
    m_MsgIn2.getBody()->getSequence3()->getVariant1()->getEventTypeRec()->setEventType(0);
    //this should fail as event type may only be 0 or 1        
    m_MsgIn2.getBody()->getSequence3()->getVariant1()->getEventTypeRec()->setEventType(251);
    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getSequence3()->getVariant1()->getEventTypeRec()->getEventType()==1);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getSequence3()->getVariant1()->getEventTypeRec()->getEventType()==0);
    
    m_MsgIn1.getBody()->getSequence3()->getVariant1()->getEventIDRec()->setEventID(0);
    m_MsgIn2.getBody()->getSequence3()->getVariant1()->getEventIDRec()->setEventID(1);
    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getSequence3()->getVariant1()->getEventIDRec()->getEventID()==0);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getSequence3()->getVariant1()->getEventIDRec()->getEventID()==1);

    m_MsgIn1.getBody()->getSequence3()->getVariant1()->getAllEventsRec()->setAllEvents(0);
    //this should fail as allevents may only be 0    
    m_MsgIn1.getBody()->getSequence3()->getVariant1()->getAllEventsRec()->setAllEvents(33);
    m_MsgIn2.getBody()->getSequence3()->getVariant1()->getAllEventsRec()->setAllEvents(0);
    m_MsgIn2.getBody()->getSequence3()->getVariant1()->getAllEventsRec()->setAllEvents(64);
    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getSequence3()->getVariant1()->getAllEventsRec()->getAllEvents()==0);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getSequence3()->getVariant1()->getAllEventsRec()->getAllEvents()==0);
    
    OUT_1 <<  "  [completed test (testSetGetOperations)] " << std::endl;
             
  }

  void testEncodeToSpec()
  {
    unsigned char buff[1024];
    int i;
       
    OUT_1 << std::endl << "  [executing test (testEncodeToSpec)(TP_3.3.5.2)] " << std::endl;
    
    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(256);
    
    OUT_2 << "     --- verifying message level encode is AS-5684 compliant" << std::endl;
        
    //case 1 - variant containing MessageIDRec 
    m_MsgIn1.getBody()->getSequence3()->getVariant1()->getMessageIDRec()->setMessageCode(1027);
	m_MsgIn1.getBody()->getSequence3()->getVariant1()->setFieldValue(0);
    m_MsgIn1.encode(buff);
    CPPUNIT_ASSERT(m_MsgIn1.getSize()==5);
    CPPUNIT_ASSERT(buff[0]==0);  //lsb of MessageID
    CPPUNIT_ASSERT(buff[1]==1);  //msb of MessageID
    CPPUNIT_ASSERT(buff[2]==0);  //vtag_field (run time record type)
    CPPUNIT_ASSERT(buff[3]==3);  //lsb of MessageCode
    CPPUNIT_ASSERT(buff[4]==4);  //msb of MessageCode

    //case 2 - variant containing EventTypeRec 
    m_MsgIn1.getBody()->getSequence3()->getVariant1()->getEventTypeRec()->setEventType(1);
	m_MsgIn1.getBody()->getSequence3()->getVariant1()->setFieldValue(1);
	m_MsgIn1.encode(buff);
    CPPUNIT_ASSERT(m_MsgIn1.getSize()==4);
    CPPUNIT_ASSERT(buff[0]==0);  //lsb of MessageID
    CPPUNIT_ASSERT(buff[1]==1);  //msb of MessageID
    CPPUNIT_ASSERT(buff[2]==1);  //vtag_field (run time record type)
    CPPUNIT_ASSERT(buff[3]==1);  //EventType

    //case 3 - variant containing EventIDRec 
    m_MsgIn1.getBody()->getSequence3()->getVariant1()->getEventIDRec()->setEventID(4);
	m_MsgIn1.getBody()->getSequence3()->getVariant1()->setFieldValue(2);
	m_MsgIn1.encode(buff);
    CPPUNIT_ASSERT(m_MsgIn1.getSize()==4);
    CPPUNIT_ASSERT(buff[0]==0);  //lsb of MessageID
    CPPUNIT_ASSERT(buff[1]==1);  //msb of MessageID
    CPPUNIT_ASSERT(buff[2]==2);  //vtag_field (run time record type)
    CPPUNIT_ASSERT(buff[3]==4);  //EventID

    //case 4 - variant containing AllEventsRec 
    m_MsgIn1.getBody()->getSequence3()->getVariant1()->getAllEventsRec()->setAllEvents(0);
	m_MsgIn1.getBody()->getSequence3()->getVariant1()->setFieldValue(3);
	m_MsgIn1.encode(buff);
    CPPUNIT_ASSERT(m_MsgIn1.getSize()==4);
    CPPUNIT_ASSERT(buff[0]==0);  //lsb of MessageID
    CPPUNIT_ASSERT(buff[1]==1);  //msb of MessageID
    CPPUNIT_ASSERT(buff[2]==3);  //vtag_field (run time record type)
    CPPUNIT_ASSERT(buff[3]==0);  //allEvents

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

    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(121);
	m_MsgIn1.getBody()->getSequence3()->getVariant1()->setFieldValue(0);
    m_MsgIn2.getHeader()->getHeaderRec()->setMessageID(127);
    
    m_MsgIn1.encode(buff);
    m_MsgIn2.decode(buff);
    
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getHeaderRec()->getMessageID() == 121);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getSequence3()->getVariant1()->getFieldValue()==0 );

    m_MsgIn2.getBody()->getSequence3()->getVariant1()->getMessageIDRec()->setMessageCode(127);   
    m_MsgIn2.getHeader()->getHeaderRec()->setMessageID(127);
    
    m_MsgIn1.encode(buff);
    m_MsgIn2.decode(buff);

    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getHeaderRec()->getMessageID() == 121);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getSequence3()->getVariant1()->getFieldValue()==0 );

    m_MsgIn1.getBody()->getSequence3()->getVariant1()->getEventTypeRec()->setEventType(1);
	m_MsgIn1.getBody()->getSequence3()->getVariant1()->setFieldValue(1);

    m_MsgIn2.getHeader()->getHeaderRec()->setMessageID(127);    
    m_MsgIn2.getBody()->getSequence3()->getVariant1()->getMessageIDRec()->setMessageCode(127);   

    m_MsgIn1.encode(buff);
    m_MsgIn2.decode(buff);
    
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getHeaderRec()->getMessageID() == 121);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getSequence3()->getVariant1()->getFieldValue()==1 );
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getSequence3()->getVariant1()->getEventTypeRec()->getEventType()==1 );

    m_MsgIn1.getBody()->getSequence3()->getVariant1()->getEventIDRec()->setEventID(101);
	m_MsgIn1.getBody()->getSequence3()->getVariant1()->setFieldValue(2);
    m_MsgIn2.getHeader()->getHeaderRec()->setMessageID(127);    
    m_MsgIn2.getBody()->getSequence3()->getVariant1()->getMessageIDRec()->setMessageCode(127);   

    m_MsgIn1.encode(buff);
    m_MsgIn2.decode(buff);
    
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getHeaderRec()->getMessageID() == 121);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getSequence3()->getVariant1()->getFieldValue()==2 );
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getSequence3()->getVariant1()->getEventIDRec()->getEventID()==101 );
    
    OUT_2 << "     --- verifying header level encode/decode reciprocity" << std::endl;
    
    m_MsgIn2.getHeader()->getHeaderRec()->setMessageID(0);
    m_MsgIn1.getHeader()->encode(buff);
    m_MsgIn2.getHeader()->decode(buff);
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getHeaderRec()->getMessageID() == 121);

    m_MsgIn2.getHeader()->getHeaderRec()->setMessageID(0);
    m_MsgIn1.getHeader()->getHeaderRec()->encode(buff);
    m_MsgIn2.getHeader()->getHeaderRec()->decode(buff);
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getHeaderRec()->getMessageID() == 121);

    OUT_2 << "     --- verifying record level encode/decode reciprocity" << std::endl;

    m_MsgIn2.getBody()->getSequence3()->getVariant1()->getMessageIDRec()->setMessageCode(0);   
        
    m_MsgIn1.getBody()->encode(buff);
    m_MsgIn2.getBody()->decode(buff);

//    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getSequence3()->getVariant1()->getFieldValue()==3 );
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getSequence3()->getVariant1()->getEventIDRec()->getEventID()==101 );

    m_MsgIn2.getBody()->getSequence3()->getVariant1()->getMessageIDRec()->setMessageCode(0);   
        
    m_MsgIn1.getBody()->getSequence3()->getVariant1()->encode(buff);
    m_MsgIn2.getBody()->getSequence3()->getVariant1()->decode(buff);

//    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getSequence3()->getVariant1()->getFieldValue()==3 );
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getSequence3()->getVariant1()->getEventIDRec()->getEventID()==101 );
            
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
    CppUnit::TestSuite *suiteOfTests = new CppUnit::TestSuite( "JTSSequence3MessageTest" );
    
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSSequence3MessageTest>( 
                                   "testConstructionDefaults", 
                                   &JTSSequence3MessageTest::testConstructionDefaults ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSSequence3MessageTest>( 
                                   "testSetGetOperations (TP_3.3.5.1)", 
                                   &JTSSequence3MessageTest::testSetGetOperations ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSSequence3MessageTest>( 
                                   "testEncodeToSpec (TP_3.3.5.2)", 
                                   &JTSSequence3MessageTest::testEncodeToSpec ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSSequence3MessageTest>( 
                                   "testEncodeDecodeOperations (TP_3.3.5.3)", 
                                   &JTSSequence3MessageTest::testEncodeDecodeOperations ) );                                   
                                                                      
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSSequence3MessageTest>( 
                                   "testEquality", 
                                   &JTSSequence3MessageTest::testEquality ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSSequence3MessageTest>(
                                   "testInequality",
                                   &JTSSequence3MessageTest::testInequality ) );
    return suiteOfTests;
  }

};


int main( int ac, char **av )
{
  CppUnit::TextUi::TestRunner runner;
  runner.addTest( JTSSequence3MessageTest::suite() );
  
  OUT_0 << std::endl << "------------------------------------------------" << std::endl;
  OUT_0 << "Executing suite JTSSequence3MessageTest" << std::endl;
  runner.run();
  OUT_0 << std::endl << "Completed suite JTSSequence3MessageTest" << std::endl;
  OUT_0 << "------------------------------------------------" << std::endl;  
}

