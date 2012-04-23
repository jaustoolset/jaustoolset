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
#include "urn_DeVivo_jaus_services_NestedDef_1_0/NestedDef2_NestedStateMachn.h"
#include "TestFlagsClass.h"


#define OUT_0  std::cout
#define OUT_1  if (bVerbose<=2) std::cout 
#define OUT_2  if (bVerbose<=1) std::cout

class NestedStateMachnImpl : public urn_DeVivo_jaus_services_NestedDef_1_0::NestedDef2_NestedStateMachn
{
public:
	NestedStateMachnImpl(){};
	virtual ~NestedStateMachnImpl(){};

	/// Action Methods
	virtual void EnteredStandbyStateAction(){};
	virtual void Entered_K1Action(){TFC.StateHistory[TFC.LastState++]=1;};
	virtual void Entered_K2Action(){TFC.StateHistory[TFC.LastState++]=2;};
	virtual void Entered_K3Action(){TFC.StateHistory[TFC.LastState++]=3;};
	virtual void Entered_PAAction(){};
	virtual void Entered_PBAction(){};
	virtual void Entered_PCAction(){TFC.StateHistory[TFC.LastState++]=4;};
	virtual void Exited_K1Action(){TFC.StateHistory[TFC.LastState++]=5;};
	virtual void Exited_K2Action(){TFC.StateHistory[TFC.LastState++]=6;};
	virtual void Exited_K3Action(){TFC.StateHistory[TFC.LastState++]=7;};
	virtual void Exited_PAAction(){};
	virtual void Exited_PBAction(){};
	virtual void Exited_PCAction(){TFC.StateHistory[TFC.LastState++]=8;};
	virtual void FromK1ToK2_SimpleAction(){TFC.StateHistory[TFC.LastState++]=9;};
	virtual void FromK1ToPC_PushAction(){TFC.StateHistory[TFC.LastState++]=20;};
	virtual void FromK1ToPC_SimpleAction(){TFC.StateHistory[TFC.LastState++]=10;};
	virtual void FromK1_PopAction(){TFC.StateHistory[TFC.LastState++]=11;};
	virtual void FromK2ToK3_SimpleAction(){ TFC.StateHistory[TFC.LastState++]=12;};
	virtual void FromK2ToPA_PushAction(){TFC.StateHistory[TFC.LastState++]=13;};
	virtual void FromK3ToK1_PushAction(){TFC.StateHistory[TFC.LastState++]=14;};
	virtual void FromK3ToPC_SimpleAction(){TFC.StateHistory[TFC.LastState++]=15;};
	virtual void FromPCToK1_PushAction(){TFC.StateHistory[TFC.LastState++]=16;};
	virtual void FromPCToK1_SimpleAction(){TFC.StateHistory[TFC.LastState++]=17;};
	virtual void FromPC_PopAction(){TFC.StateHistory[TFC.LastState++]=18;};
	virtual void FromStandbyToK3Action(){TFC.StateHistory[TFC.LastState++]=19;};

	// user-variables
	TestFlagsClass TFC;

};


class JTSNestedDef2MessageTest : public CppUnit::TestFixture {
             
private:

  int bVerbose;
  NestedStateMachnImpl* fsm;
  
public:
         
  JTSNestedDef2MessageTest() {
     bVerbose=0;

	 // Create the FSM and call the entry action manually
     fsm = new NestedStateMachnImpl();
	 fsm->EnteredStandbyStateAction();
  }     
  
       
  void setUp()
  {

       fsm->TFC.LastState = 0;
       int i;
       for (i=0;i<255;i++)
          fsm->TFC.StateHistory[i]=255;

	   // trigger the test transitions
	fsm->context->StandbyToK3_SimpleTransition();
	fsm->context->K3ToPC_SimpleTransition();
	fsm->context->PCToK1_PushTransition();
	fsm->context->K1_PopTransition();
	fsm->context->PCToK1_SimpleTransition();
	fsm->context->K1ToPC_PushTransition(); 
	fsm->context->PC_PopTransition(); 
  }

  void tearDown() 
  {
  }

  void testProtocol()
  {
     int i;

     OUT_0 << "[Action log summary:]" << std::endl;
     printf("[fsm->TFC.LastState == %ld]\n",fsm->TFC.LastState);
     for (i=0;i<fsm->TFC.LastState+1;i++) {
     printf("[fsm->TFC.StateHistory[%ld] == %ld]\n",i,fsm->TFC.StateHistory[i]);    
     }

/*
(1)fsm->TFC.StateHistory[0] == 19 	FromStandbyToK3Action
(1)fsm->TFC.StateHistory[1] == 3	Entered_K3Action
(2)fsm->TFC.StateHistory[2] == 7	Exited_K3Action
(2)fsm->TFC.StateHistory[3] == 15	FromK3ToPC_SimpleAction
(2)fsm->TFC.StateHistory[4] == 4	Entered_PCAction
--stops here, should see:
(3)fsm->TFC.StateHistory[5] == 16	FromPCToK1_PushAction
(3)fsm->TFC.StateHistory[6] == 1	EnteredK1_Action
(4)fsm->TFC.StateHistory[7] == 5	ExitedK1_Action
(4)fsm->TFC.StateHistory[8] == 11	FromK1_PopAction
(5)fsm->TFC.StateHistory[9] == 8	Exited_PCAction
(5)fsm->TFC.StateHistory[10] == 17	FromPCToK1_SimpleAction
(5)fsm->TFC.StateHistory[11] == 1	EnteredK1_Action
(6)                             FromK1ToPC_PushAction

 ----code generator never produced stub for FromK1ToPC_PushAction

(6)fsm->TFC.StateHistory[13] == 4	EnteredPC_Action
(7)fsm->TFC.StateHistory[14] == 8	ExitedPC_Action
(7)fsm->TFC.StateHistory[15] == 18	FromPC_PopAction
*/     
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[0] ==  19); //FromStandbyToK3Action 
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[1] ==  3); //Entered_K3Action
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[2] ==  7); //Exited_K3Action
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[3] ==  15); //FromK3ToPC_SimpleAction
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[4] ==  4); //Entered_PCAction
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[5] ==  16); //FromPCToK1_PushAction
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[6] ==  1); //EnteredK1_Action
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[7] ==  5); //ExitedK1_Action 
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[8] ==  11); //FromK1_PopAction
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[9] ==  8); //Exited_PCAction 
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[10] ==  17); //FromPCToK1_SimpleAction
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[11] ==  1); //EnteredK1_Action
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[12] ==  20); //FromK1ToPC_PushAction
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[13] ==  4); //EnteredPC_Action
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[14] ==  8); //ExitedPC_Action
     CPPUNIT_ASSERT(fsm->TFC.StateHistory[15] ==  18); //FromPC_PopAction
         
     OUT_0 << " TEST COMPLETE " << std::endl;
  }
  

  static CppUnit::Test *suite()
  {
    CppUnit::TestSuite *suiteOfTests = new CppUnit::TestSuite( "JTSNestedDef2MessageTest" );
    
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSNestedDef2MessageTest>( 
                                   "testProtocol", 
                                   &JTSNestedDef2MessageTest::testProtocol ) );
                                   
    return suiteOfTests;
  }

};


int main( int ac, char **av )
{
    
  CppUnit::TextUi::TestRunner runner;
  runner.addTest( JTSNestedDef2MessageTest::suite() );
  
  OUT_0 << std::endl << "------------------------------------------------" << std::endl;
  OUT_0 << "Executing suite JTSNestedDef2MessageTest" << std::endl;
  runner.run();
  OUT_0 << std::endl << "Completed suite JTSNestedDef2MessageTest" << std::endl;
  OUT_0 << "------------------------------------------------" << std::endl;  
}
