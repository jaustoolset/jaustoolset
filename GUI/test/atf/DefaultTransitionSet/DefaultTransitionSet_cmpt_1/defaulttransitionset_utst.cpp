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

#include "TestFlagsClass.h"
#include "urn_DeVivo_jaus_services_DefaultTransDef_1_0/DefaultTransDef_DefaultTransitionStateMachine.h"

#define OUT_0  std::cout
#define OUT_1  if (bVerbose<=2) std::cout 
#define OUT_2  if (bVerbose<=1) std::cout
	
class DefaultTransitionStateMachineImpl : public urn_DeVivo_jaus_services_DefaultTransDef_1_0::DefaultTransDef_DefaultTransitionStateMachine
{
public:
	DefaultTransitionStateMachineImpl(){};
	virtual ~DefaultTransitionStateMachineImpl(){};

	/// Action Methods
	virtual void EnteredReadyStateAction(){TFC.StateHistory[TFC.LastState++]=0;	};
	virtual void DefaultTransitionAction(){TFC.StateHistory[TFC.LastState++]=1;};

	/// Guard Methods
	virtual bool isReady(){TFC.StateHistory[TFC.LastState++]=2;return true;};

	// user-variables
	TestFlagsClass TFC;

};


class JTSDefaultTransTest : public CppUnit::TestFixture {
             
private:

  int bVerbose;
  DefaultTransitionStateMachineImpl* fsm;
  
public: 

  JTSDefaultTransTest() {
     bVerbose=0;


	 // Create the FSM and call the entry action manually
     fsm = new DefaultTransitionStateMachineImpl();
	 fsm->TFC.LastState = 0;
     for (int i=0;i<255;i++) fsm->TFC.StateHistory[i]=255;
	 fsm->EnteredReadyStateAction();

  }     
  
       
  void setUp()
  {


	   // Run through the desired transition sequence
	   try {fsm->context->InputMessage1Transition();}
	   catch (statemap::TransitionUndefinedException e){
		   fsm->TFC.StateHistory[fsm->TFC.LastState++]=12;}
		try {fsm->context->InputMessage2Transition();}
	   catch (statemap::TransitionUndefinedException e){
		   fsm->TFC.StateHistory[fsm->TFC.LastState++]=12;}
  }

  void tearDown() 
  {
  }

  void testProtocol()
  {
     int i;
     printf("fsm->TFC.LastState == %ld\n",fsm->TFC.LastState);
     for (i=0;i<fsm->TFC.LastState+1;i++) {
     printf("fsm->TFC.StateHistory[%ld] == %ld\n",i,fsm->TFC.StateHistory[i]);    
     }
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[0] ==  0); 
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[1] ==  2); 
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[2] ==  1); 
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[3] ==  2); 
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[4] ==  1); 
         
     OUT_0 << " TEST COMPLETE " << std::endl;
  }
  

  static CppUnit::Test *suite()
  {
    CppUnit::TestSuite *suiteOfTests = new CppUnit::TestSuite( "JTSDefaultTransTest" );
    
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSDefaultTransTest>( 
                                   "testProtocol", 
                                   &JTSDefaultTransTest::testProtocol ) );
                                   
    return suiteOfTests;
  }

};


int main( int ac, char **av )
{
    
  CppUnit::TextUi::TestRunner runner;
  runner.addTest( JTSDefaultTransTest::suite() );
  
  OUT_0 << std::endl << "------------------------------------------------" << std::endl;
  OUT_0 << "Executing suite JTSDefaultTransTest" << std::endl;
  runner.run();
  OUT_0 << std::endl << "Completed suite JTSDefaultTransTest" << std::endl;
  OUT_0 << "------------------------------------------------" << std::endl;  
}
