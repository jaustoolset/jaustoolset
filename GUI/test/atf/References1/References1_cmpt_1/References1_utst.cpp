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
#ifndef WIN32
#include <dirent.h>
#endif

#include "urn_org_jts_test_Client1_1_0/Messages/MessageSet.h"
#include "urn_org_jts_test_Client1_1_0/InternalEvents/InternalEventsSet.h"

#include "cppunit/TestCaller.h"
#include "cppunit/TestCaller.h"
#include "cppunit/ui/text/TestRunner.h"
#include "cppunit/TestSuite.h"

using namespace urn_org_jts_test_Client1_1_0;

#define OUT_0  std::cout 
#define OUT_1  if (bVerbose<=2) std::cout 
#define OUT_2  if (bVerbose<=1) std::cout

class JTSReferences1MessageTest : public CppUnit::TestFixture {
             
private:
  ServerInputMessage1 m_MsgIn1;
  ServerOutputMessage1 m_MsgOut1;
  ServerInputMessage2 m_MsgIn2;
  ServerOutputMessage2 m_MsgOut2;
  ParentInputMessage1 m_MsgIn3;
  ParentOutputMessage1 m_MsgOut3;
  IntermediaryInputMessage1 m_MsgIn4;
  IntermediaryOutputMessage1 m_MsgOut4;
  ServerParentInputMessage1 m_MsgIn5;
  ServerParentOutputMessage1 m_MsgOut5;
  ParentServerInputMessage1 m_MsgIn6;
  ParentServerOutputMessage1 m_MsgOut6;

  ParentEvent1 m_Event1;
  char bVerbose;
  
public:
  JTSReferences1MessageTest() 
  {
	  bVerbose = 0;
  }     
       
  void setUp()
  {
  }

  void tearDown() 
  {
  }

  void testSimpleClientOf()
  {
    OUT_1 << std::endl << "  [executing test (testSimpleClientOf)] " << std::endl;

    OUT_2 << "     --- verifying MsgIn1 constructs with correct default MessageID" << std::endl;
    CPPUNIT_ASSERT( m_MsgIn1.getHeader()->getHeaderRec()->getMessageID() == 1 );

    OUT_2 << "     --- verifying MsgOut1 constructs with correct default MessageID" << std::endl;
    CPPUNIT_ASSERT( m_MsgOut1.getHeader()->getHeaderRec()->getMessageID() == 2 );

    OUT_2 << "     --- verifying MsgIn2 constructs with correct default MessageID" << std::endl;
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getHeaderRec()->getMessageID() == 3 );

    OUT_2 << "     --- verifying MsgOut2 constructs with correct default MessageID" << std::endl;
    CPPUNIT_ASSERT( m_MsgOut2.getHeader()->getHeaderRec()->getMessageID() == 4 );
  }

  void testSimpleInheritence()
  {
    OUT_1 << std::endl << "  [executing test (testSimpleInheritence)] " << std::endl;

    OUT_2 << "     --- verifying MsgIn3 constructs with correct default MessageID" << std::endl;
    CPPUNIT_ASSERT( m_MsgIn3.getHeader()->getHeaderRec()->getMessageID() == 5 );

    OUT_2 << "     --- verifying MsgOut3 constructs with correct default MessageID" << std::endl;
    CPPUNIT_ASSERT( m_MsgOut3.getHeader()->getHeaderRec()->getMessageID() == 6 );

    OUT_2 << "     --- verifying MsgIn4 constructs with correct default MessageID" << std::endl;
    CPPUNIT_ASSERT( m_MsgIn4.getHeader()->getHeaderRec()->getMessageID() == 7 );

    OUT_2 << "     --- verifying MsgOut4 constructs with correct default MessageID" << std::endl;
    CPPUNIT_ASSERT( m_MsgOut4.getHeader()->getHeaderRec()->getMessageID() == 8 );

    OUT_2 << "     --- verifying m_Event1 constructs with correct size" << std::endl;
    CPPUNIT_ASSERT( m_Event1.getSize() == 0 );
  }

  void testComplexCases()
  {
    OUT_1 << std::endl << "  [executing test (testComplexCases)] " << std::endl;

    OUT_2 << "     --- verifying MsgIn5 constructs with correct default MessageID" << std::endl;
    CPPUNIT_ASSERT( m_MsgIn5.getHeader()->getHeaderRec()->getMessageID() == 16 );

    OUT_2 << "     --- verifying MsgOut5 constructs with correct default MessageID" << std::endl;
    CPPUNIT_ASSERT( m_MsgOut5.getHeader()->getHeaderRec()->getMessageID() == 17 );

    OUT_2 << "     --- verifying MsgIn6 constructs with correct default MessageID" << std::endl;
    CPPUNIT_ASSERT( m_MsgIn6.getHeader()->getHeaderRec()->getMessageID() == 18 );

    OUT_2 << "     --- verifying MsgOut6 constructs with correct default MessageID" << std::endl;
    CPPUNIT_ASSERT( m_MsgOut6.getHeader()->getHeaderRec()->getMessageID() == 19 );
  }

  //
  // Helper function returns TRUE is the <filename> is found anywhere in <path>
  //
  bool checkDirForFile(char* path, char* filename)
  {
	  bool found = false;

#ifndef WIN32
	  // Open the directory for reading.
	  DIR *pdir = opendir(path);
	  if (pdir == NULL)
	  {
		  printf("Failed to open directory: %s\n", path);
		  return true;
	  }

	  // Loop through all file names in the dir
	  struct dirent *pent = NULL;
	  while (pent = readdir(pdir))
		  if (strcmp(pent->d_name, filename) == 0) 
			  found = true;
#endif
	  return found;
  }

  void testNegativeCases()
  {
    OUT_1 << std::endl << "  [executing test (testNegativeCases)] " << std::endl;

	// This is a little tricky.  We need to confirm that various "hidden" messages and
	// events were not generated (to ensure that when A is a client-of B and B is a client-of C
	// that the vocabulary of C is not automatically added to A).  Note that we therefore
	// need to verify that header files are missing or absent from the service without
	// generating compiler errors that would prevent the whole test from failing.  I'm going
	// to parse the directory for the hidden header files, but I'm open to alternatives
	// if anyone has ideas...
	
    OUT_2 << "     --- verifying HiddenInputMessage1 not resolved " << std::endl;
    CPPUNIT_ASSERT( checkDirForFile("include/urn_org_jts_test_Client1_1_0/Messages", "HiddenInputMessage1.h") == false );

    OUT_2 << "     --- verifying HiddenOutputMessage1 not resolved " << std::endl;
    CPPUNIT_ASSERT( checkDirForFile("include/urn_org_jts_test_Client1_1_0/Messages", "HiddenOutputMessage1.h") == false );

    OUT_2 << "     --- verifying HiddenEvent1 not resolved " << std::endl;
    CPPUNIT_ASSERT( checkDirForFile("include/urn_org_jts_test_Client1_1_0/InternalEvents", "HiddenEvent1.h") == false );

    OUT_2 << "     --- verifying HiddenParentServerEvent1 not resolved " << std::endl;
    CPPUNIT_ASSERT( checkDirForFile("include/urn_org_jts_test_Client1_1_0/InternalEvents", "HiddenParentServerEvent1.h") == false );

  }

  static CppUnit::Test *suite()
  {
    CppUnit::TestSuite *suiteOfTests = new CppUnit::TestSuite( "JTSClientOf1MessageTest" );
    
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSReferences1MessageTest>( 
                                   "testConstructionDefaults", 
                                   &JTSReferences1MessageTest::testSimpleClientOf ) );

    suiteOfTests->addTest( new CppUnit::TestCaller<JTSReferences1MessageTest>( 
                                   "testSimpleInheritence", 
                                   &JTSReferences1MessageTest::testSimpleInheritence ) );

	suiteOfTests->addTest( new CppUnit::TestCaller<JTSReferences1MessageTest>( 
                                   "testComplexCases", 
                                   &JTSReferences1MessageTest::testComplexCases ) );

	suiteOfTests->addTest( new CppUnit::TestCaller<JTSReferences1MessageTest>( 
                                   "testNegativeCases", 
                                   &JTSReferences1MessageTest::testNegativeCases ) );
    return suiteOfTests;
  }

};


int main( int ac, char **av )
{
  CppUnit::TextUi::TestRunner runner;
  runner.addTest( JTSReferences1MessageTest::suite() );
  
  OUT_0 << std::endl << "------------------------------------------------" << std::endl;
  OUT_0 << "Executing suite JTSReferences1MessageTest" << std::endl;
  runner.run();
  OUT_0 << std::endl << "Completed suite JTSReferences1MessageTest" << std::endl;
  OUT_0 << "------------------------------------------------" << std::endl;  
}

