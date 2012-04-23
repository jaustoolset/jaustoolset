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
#include "urn_DeVivo_jaus_services_SimpleDef_1_0/SimpleDef_SimpleStateMachn.h"

#define OUT_0  std::cout
#define OUT_1  if (bVerbose<=2) std::cout 
#define OUT_2  if (bVerbose<=1) std::cout
	
class SimpleStateMachnImpl : public urn_DeVivo_jaus_services_SimpleDef_1_0::SimpleDef_SimpleStateMachn
{
public:
	SimpleStateMachnImpl(){};
	virtual ~SimpleStateMachnImpl(){};

	/// Action Methods
	virtual void EnteredErrorStateAction(){TFC.StateHistory[TFC.LastState++]=5;	};
	virtual void EnteredPausedStateAction(){TFC.StateHistory[TFC.LastState++]=6;};
	virtual void EnteredReadyState2Action(){TFC.StateHistory[TFC.LastState++]=8;};
	virtual void EnteredReadyStateAction(){TFC.StateHistory[TFC.LastState++]=7;};
	virtual void EnteredStandbyStateAction(){};
	virtual void FromReadyToErrorAction(){TFC.StateHistory[TFC.LastState++]=0;};
	virtual void FromStandbyToReadyAction(){TFC.StateHistory[TFC.LastState++]=1;};
	virtual void PauseAction(std::string arg0){TFC.StateHistory[TFC.LastState++]=2;};
	virtual void ReportStateAction(){TFC.StateHistory[TFC.LastState++]=3;};
	virtual void ResumeAction(){TFC.StateHistory[TFC.LastState++]=4;};

	/// Guard Methods
	virtual bool ReadyToErrorGuard(){TFC.StateHistory[TFC.LastState++]=9;return false;};
	virtual bool StandbyToReadyGuard(){TFC.StateHistory[TFC.LastState++]=10;return true;};

	// user-variables
	TestFlagsClass TFC;

};


class JTSSimpleDefMessageTest : public CppUnit::TestFixture {
             
private:

  int bVerbose;
  SimpleStateMachnImpl* fsm;
  
public: 

  JTSSimpleDefMessageTest() {
     bVerbose=0;


	 // Create the FSM and call the entry action manually
     fsm = new SimpleStateMachnImpl();
	 fsm->EnteredStandbyStateAction();

  }     
  
       
  void setUp()
  {
       fsm->TFC.LastState = 0;
       int i;
       for (i=0;i<255;i++)
          fsm->TFC.StateHistory[i]=255;

	   // Run through the desired transition sequence
	   try {fsm->context->ReadyMessageTransition();}
	   catch (statemap::TransitionUndefinedException e){
		   fsm->TFC.StateHistory[fsm->TFC.LastState++]=12;}
		try {fsm->context->PauseMessageTransition();}
	   catch (statemap::TransitionUndefinedException e){
		   fsm->TFC.StateHistory[fsm->TFC.LastState++]=12;}
		try {fsm->context->PauseMessageTransition();}
	   catch (statemap::TransitionUndefinedException e){
		   fsm->TFC.StateHistory[fsm->TFC.LastState++]=12;}
		try {fsm->context->ResumeMessageTransition();}
	   catch (statemap::TransitionUndefinedException e){
		   fsm->TFC.StateHistory[fsm->TFC.LastState++]=12;}
		try {fsm->context->PauseMessageTransition();}
	   catch (statemap::TransitionUndefinedException e){
		   fsm->TFC.StateHistory[fsm->TFC.LastState++]=12;}
		try {fsm->context->ResumeMessageTransition();}
	   catch (statemap::TransitionUndefinedException e){
		   fsm->TFC.StateHistory[fsm->TFC.LastState++]=12;}
		try {fsm->context->ResumeMessageTransition();}
	   catch (statemap::TransitionUndefinedException e){
		   fsm->TFC.StateHistory[fsm->TFC.LastState++]=12;}
		try {fsm->context->ErrorMessageTransition();}
	   catch (statemap::TransitionUndefinedException e){
		   fsm->TFC.StateHistory[fsm->TFC.LastState++]=12;}
		try {fsm->context->QueryStateMessageTransition();}
	   catch (statemap::TransitionUndefinedException e){
		   fsm->TFC.StateHistory[fsm->TFC.LastState++]=12;}
		//SndResetMessageAction();
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
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[0] ==  10); 
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[1] ==  1); 
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[2] ==  7); 
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[3] ==  2); 
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[4] ==  6); 
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[5] ==  2); 
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[6] ==  6); 
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[7] ==  4); 
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[8] ==  12); 
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[9] ==  2); 
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[10] ==  6); 
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[11] ==  4); 
	 CPPUNIT_ASSERT(fsm->TFC.StateHistory[12] ==  12); 
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[13] ==  4); 
	 CPPUNIT_ASSERT(fsm->TFC.StateHistory[14] ==  12); 
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[15] ==  9); 
	 CPPUNIT_ASSERT(fsm->TFC.StateHistory[16] ==  12);
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[17] ==  3); 
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[18] ==  8); 
         
     OUT_0 << " TEST COMPLETE " << std::endl;
  }
  

  static CppUnit::Test *suite()
  {
    CppUnit::TestSuite *suiteOfTests = new CppUnit::TestSuite( "JTSSimpleDefMessageTest" );
    
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSSimpleDefMessageTest>( 
                                   "testProtocol", 
                                   &JTSSimpleDefMessageTest::testProtocol ) );
                                   
    return suiteOfTests;
  }

};


int main( int ac, char **av )
{
    
  CppUnit::TextUi::TestRunner runner;
  runner.addTest( JTSSimpleDefMessageTest::suite() );
  
  OUT_0 << std::endl << "------------------------------------------------" << std::endl;
  OUT_0 << "Executing suite JTSSimpleDefMessageTest" << std::endl;
  runner.run();
  OUT_0 << std::endl << "Completed suite JTSSimpleDefMessageTest" << std::endl;
  OUT_0 << "------------------------------------------------" << std::endl;  
}
