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
#include "urn_DeVivo_jaus_services_LoopbackDef_1_0/LoopbackDef_LoopbackStateMachn.h"
#include "TestFlagsClass.h"

#define OUT_0  std::cout
#define OUT_1  if (bVerbose<=2) std::cout 
#define OUT_2  if (bVerbose<=1) std::cout


class LoopbackStateMachnImpl : public urn_DeVivo_jaus_services_LoopbackDef_1_0::LoopbackDef_LoopbackStateMachn
{
public:
	LoopbackStateMachnImpl(){};
	virtual ~LoopbackStateMachnImpl(){};

	/// Action Methods
	virtual void EnteredReadyStateAction(){ TFC.StateHistory[TFC.LastState++]=1;};
	virtual void ExitedReadyStateAction(){ TFC.StateHistory[TFC.LastState++]=2;};
	virtual void FromReadyToReadyInternalAction(){ TFC.StateHistory[TFC.LastState++]=3;};
	virtual void FromReadyToReadySimpleAction(){ TFC.StateHistory[TFC.LastState++]=4;};
	virtual void FromStandbyToReadyAction(){ TFC.StateHistory[TFC.LastState++]=5;};


	/// Guard Methods

	TestFlagsClass TFC;
};


class JTSLoopbackDefMessageTest : public CppUnit::TestFixture {
             
private:

  int bVerbose;
   LoopbackStateMachnImpl* fsm;
  
public:  
         
  JTSLoopbackDefMessageTest() {
     bVerbose=0;

		// Create the FSM and call the entry action manually
     fsm = new LoopbackStateMachnImpl();
  }     
  
       
  void setUp()
  {
       fsm->TFC.LastState = 0;
       int i;
       for (i=0;i<255;i++)
          fsm->TFC.StateHistory[i]=255;
	 	fsm->context->FromStandbyToReadyMsgTransition();
		fsm->context->FromReadyToReadySimpleMsgTransition(); 
		fsm->context->FromReadyToReadyInternalMsgTransition();
		fsm->context->FromReadyToReadySimpleMsgTransition(); 
		fsm->context->FromReadyToReadyInternalMsgTransition();



  }

  void tearDown() 
  {
  }

  void testProtocol()
  {
     int i;

     printf("fsm->fsm->TFC.LastState == %ld\n",fsm->TFC.LastState);
     for (i=0;i<fsm->TFC.LastState+2;i++) {
     printf("fsm->TFC.StateHistory[%ld] == %ld\n",i,fsm->TFC.StateHistory[i]);    
     }
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[0] ==  5); 
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[1] ==  1); 
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[2] ==  2); 
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[3] ==  4); 
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[4] ==  1); 
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[5] ==  3); 
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[6] ==  2); 
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[7] ==  4); 
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[8] ==  1); 
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[9] ==  3); 
         
     OUT_0 << " TEST COMPLETE " << std::endl;
  }
  

  static CppUnit::Test *suite()
  {
    CppUnit::TestSuite *suiteOfTests = new CppUnit::TestSuite( "JTSLoopbackDefMessageTest" );
    
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSLoopbackDefMessageTest>( 
                                   "testProtocol", 
                                   &JTSLoopbackDefMessageTest::testProtocol ) );
                                   
    return suiteOfTests;
  }

};


int main( int ac, char **av )
{
    
  CppUnit::TextUi::TestRunner runner;
  runner.addTest( JTSLoopbackDefMessageTest::suite() );
  
  OUT_0 << std::endl << "------------------------------------------------" << std::endl;
  OUT_0 << "Executing suite JTSLoopbackDefMessageTest" << std::endl;
  runner.run();
  OUT_0 << std::endl << "Completed suite JTSLoopbackDefMessageTest" << std::endl;
  OUT_0 << "------------------------------------------------" << std::endl;  
}

