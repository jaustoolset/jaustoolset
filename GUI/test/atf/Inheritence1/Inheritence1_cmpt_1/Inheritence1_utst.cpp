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

#include "cppunit/TestCaller.h"
#include "cppunit/TestCaller.h"
#include "cppunit/ui/text/TestRunner.h"
#include "cppunit/TestSuite.h"
#include "Inheritence1_cmpt.h"

#include "TestHarness.h"
#include "Transport/OS.h"

#include "urn_org_jts_test_Child_1_0/Messages/MessageSet.h"


using namespace urn_org_jts_test_Child_1_0;

extern "C" unsigned long intermediary_action;
extern "C" unsigned long child_action;
extern "C" unsigned long parent_action;

unsigned long total_action() {return intermediary_action | child_action | parent_action;}
void reset_actions() {intermediary_action = child_action = parent_action = 0;}


#define OUT_0  std::cout
#define OUT_1  if (bVerbose<=2) std::cout 
#define OUT_2  if (bVerbose<=1) std::cout

class TestComponent : public Inheritence1_cmpt
{
	public: TestComponent():Inheritence1_cmpt(126, 1, 1)
			{ 
				DeVivo::Junior::JrSleep(100);
			};
	public: ~TestComponent()
			{
				DeVivo::Junior::JrSleep(100);
			};
	public: void routeMessage(JTS::Message* msg)
			{
				// Encode the message
				unsigned int bufsize = msg->getSize();
				unsigned char* buffer = new unsigned char[bufsize];
				msg->encode(buffer);

				// Send the response.  We enclose
				// the response message in a transport envelope that includes
				// the destination address.
				JTS::Send_1_0 response;
				response.getBody()->getSendRec()->getMessagePayload()->set(bufsize, buffer);
				response.getBody()->getSendRec()->setDestSubsystemID(126);
				response.getBody()->getSendRec()->setDestNodeID(1);
				response.getBody()->getSendRec()->setDestComponentID(1);
				jausRouter->sendMessage( &response );

				// Free the buffer we used to encode the message
				delete[] buffer;

				DeVivo::Junior::JrSleep(100);
			};
};

class JTSInheritenceTest : public CppUnit::TestFixture {
             
private:

  int bVerbose;
  
public: 
         
  JTSInheritenceTest() 
	{
     bVerbose=0;
  }   
      
  void setUp()
  {
  }

  void tearDown() 
  {
  }

  void testInitState()
  {
	   OUT_1 << "Checking initial state..." << std::endl;

	 TestComponent cmpt;
	 cmpt.startComponent();
	 DeVivo::Junior::JrSleep(100);
     
	 CPPUNIT_ASSERT(intermediary_action == CTop1_Intermediary1_EntryAction);

	  cmpt.shutdownComponent();
	}

	void parentTransitions()
	{
	   OUT_1 << "Checking transitions between parent states..." << std::endl;
		TestComponent cmpt;
		cmpt.startComponent();
		DeVivo::Junior::JrSleep(100);

		 // Send it a message ParentInputMessage1 to transition
		 reset_actions();
		 OUT_1 << "Checking transitions between parent states..." << std::endl;
		 cmpt.routeMessage( new ParentInputMessage1() );
		 CPPUNIT_ASSERT(total_action() == (CTop1_Intermediary1_ExitAction + CAction_Top1_To_Top2Action + CTop2_EntryAction + CTop2_Intermediary1_EntryAction));

		 // Put it back using ParentInputMessage2
		 reset_actions();
		 cmpt.routeMessage( new ParentInputMessage2());
		 CPPUNIT_ASSERT(total_action() == (CTop2_Intermediary1_ExitAction + CTop2_ExitAction + CTop1_Intermediary1_EntryAction));

		 cmpt.shutdownComponent();
	}

	void intermediaryTransitions()
	{
	   OUT_1 << "Checking transitions between intermediary states..." << std::endl;
		 TestComponent cmpt;
		cmpt.startComponent();
		DeVivo::Junior::JrSleep(1000);

		 // Send it a message ParentInputMessage1 to transition to Top2_Intermediary1
		reset_actions();
		cmpt.routeMessage( new ParentInputMessage1());
		CPPUNIT_ASSERT(total_action() == (CTop1_Intermediary1_ExitAction + CAction_Top1_To_Top2Action + CTop2_EntryAction + CTop2_Intermediary1_EntryAction));

		 // Put it into Top2_Intermediary2
		 reset_actions();
		 cmpt.routeMessage( new IntermediaryInputMessage1());
		 CPPUNIT_ASSERT(total_action() == (CTop2_Intermediary1_ExitAction + CTop2_Intermediary2_EntryAction));

		 // Put it back to the initial Top1_Intermediary1 state
		 reset_actions();
		 cmpt.routeMessage( new ParentInputMessage2());
		 CPPUNIT_ASSERT(total_action() == (CTop2_Intermediary2_ExitAction + CTop2_ExitAction + CTop1_Intermediary1_EntryAction));

		 cmpt.shutdownComponent();
	}

	void childTransitions()
	{
	   OUT_1 << "Checking transitions using default state..." << std::endl;
		 TestComponent cmpt;
		cmpt.startComponent();
		DeVivo::Junior::JrSleep(1000);

		 // Send it a message ParentInputMessage1 to transition to Top2_Intermediary1
		reset_actions();
		cmpt.routeMessage( new ParentInputMessage1());
		CPPUNIT_ASSERT(total_action() == (CTop1_Intermediary1_ExitAction + CAction_Top1_To_Top2Action + CTop2_EntryAction + CTop2_Intermediary1_EntryAction));

		 // Top2_Intemediary1 does not support pim1 message.  Verify that this tickles the default transition
		 reset_actions();
		 cmpt.routeMessage( new ParentInputMessage1());
		 CPPUNIT_ASSERT(total_action() == CDefaultTransitionAction);

		 // Lastly, the CIM3 message should transition back to the Top1_Intermediary1 state
		 reset_actions();
		 cmpt.routeMessage( new ChildInputMessage3());
		 CPPUNIT_ASSERT(total_action() == (CTop2_Intermediary1_ExitAction + CTop2_ExitAction + CTop2Intermediary1_toTop1Intermediary1Action + CTop1_Intermediary1_EntryAction));

		 // Send it a message ParentInputMessage1 to transition to Top2_Intermediary1
		 reset_actions();
		 cmpt.routeMessage( new ParentInputMessage1());
		 CPPUNIT_ASSERT(total_action() == (CTop1_Intermediary1_ExitAction + CAction_Top1_To_Top2Action + CTop2_EntryAction + CTop2_Intermediary1_EntryAction));

		 // Put it into Top2_Intermediary2
		 reset_actions();
		 cmpt.routeMessage( new IntermediaryInputMessage1());
		 CPPUNIT_ASSERT(total_action() == (CTop2_Intermediary1_ExitAction + CTop2_Intermediary2_EntryAction));

		 // From Top2_Intermediary2, pim1 and cim3 are not supported.  These should do the default transition
		 reset_actions();
		 cmpt.routeMessage( new ParentInputMessage1());
		 CPPUNIT_ASSERT(total_action() == CDefaultTransitionAction);
		 reset_actions();
		 cmpt.routeMessage( new ChildInputMessage3());
		 CPPUNIT_ASSERT(total_action() == CDefaultTransitionAction);

		 // From Top2_Intermediary2, a default internal transition causes us to stay in the same state
		 reset_actions();
		 cmpt.routeMessage( new ChildInputMessage2());
		 CPPUNIT_ASSERT(total_action() == 0);

		 /*   As of Drew's update Dec 4, a simple default transition returns to the original nested state */
		 reset_actions();
		 cmpt.routeMessage( new ChildInputMessage1());
		 CPPUNIT_ASSERT(total_action() == 0);

		 cmpt.shutdownComponent();
	}

  void finalize()
	{
     OUT_0 << " TEST COMPLETE " << std::endl;
  }
  

  static CppUnit::Test *suite()
  {
    CppUnit::TestSuite *suiteOfTests = new CppUnit::TestSuite( "JTSInheritenceTest" );
    
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSInheritenceTest>( 
                                   "testInitState", 
                                   &JTSInheritenceTest::testInitState ) );

    suiteOfTests->addTest( new CppUnit::TestCaller<JTSInheritenceTest>( 
                                   "parentTransitions", 
                                   &JTSInheritenceTest::parentTransitions ) );
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSInheritenceTest>( 
                                   "intermediaryTransitions", 
                                   &JTSInheritenceTest::intermediaryTransitions ) );
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSInheritenceTest>( 
                                   "childTransitions", 
                                   &JTSInheritenceTest::childTransitions ) );

    suiteOfTests->addTest( new CppUnit::TestCaller<JTSInheritenceTest>( 
                                   "finalize", 
                                   &JTSInheritenceTest::finalize ) );
                                   
    return suiteOfTests;
  }

};


int main( int ac, char **av )
{
    
  CppUnit::TextUi::TestRunner runner;
  runner.addTest( JTSInheritenceTest::suite() );
  
  OUT_0 << std::endl << "------------------------------------------------" << std::endl;
  OUT_0 << "Executing suite JTSInheritenceTest" << std::endl;
  runner.run();
  OUT_0 << std::endl << "Completed suite JTSInheritenceTest" << std::endl;
  OUT_0 << "------------------------------------------------" << std::endl;  
}

