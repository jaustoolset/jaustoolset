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

package org.jts.codegenerator.protocolBehavior;


import java.io.File;
import java.util.Vector;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.SchemaFactory;

import javax.xml.bind.JAXBException;
import org.xml.sax.SAXException;

import org.junit.*;

import static org.junit.Assert.*;

import org.jts.codegenerator.CodeLines;
import org.jts.jsidl.binding.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * This class runs the CodeGenerator using the xml files within the testOutput/jts_final_test folder
 * 
 */
public class EntryTest
{
	private String outDir;						// Directory to put output into
	private String inputDir;
	private org.jts.codegenerator.ProtocolBehaviorGenerator pb;
	private javax.xml.bind.Unmarshaller unmarshaller;
	
	@Before
	public void setUp()
	{		
        inputDir = "testXML/";
		outDir = "generatedOutput/ProtocolBehaviorGenerator/";
        
        try
		{
		   javax.xml.bind.JAXBContext jc = javax.xml.bind.JAXBContext.newInstance( "org.jts.jsidl.binding" );
           unmarshaller = jc.createUnmarshaller();
        } catch(Exception e) {
        }
	}
	
	
	@Test
	public void generateProtocolBehavior()
	{
			
		try
		{           
           int numberOfTests = 15;
         
           for(int i = 1; i <= numberOfTests; i++)
           {
        	   ServiceDef sd = (ServiceDef)unmarshaller.unmarshal( new File(inputDir + "FSMEntryTest" + Integer.toString(i) + ".xml") );
        	   ServiceSet ss = new ServiceSet();
        	   ss.setId("abc");
        	   ss.setName("ss");
        	   ss.setVersion("1.0");
	           
        	   pb = new org.jts.codegenerator.ProtocolBehaviorGenerator("namespace", CodeLines.CodeType.C_PLUS_PLUS, outDir, sd, ss, new StringBuffer());
	           
	           switch(i)
	           {
	           case 1:
	        	   compareOutput1();
	        	   break;
	           case 2:
	        	   compareOutput2();
	        	   break;
	           case 3:
	        	   compareOutput3();
	        	   break;
	           case 4:
	        	   compareOutput4();
	        	   break;
	           case 5:
	        	   compareOutput5();
	        	   break;
	           case 6:
	        	   compareOutput6();
	        	   break;
	           case 7:
	        	   compareOutput7();
	        	   break;
	           case 8:
	        	   compareOutput8();
	        	   break;
	           case 9:
	        	   compareOutput9();
	        	   break;
	           case 10:
	        	   compareOutput10();
	        	   break;
	           case 11:
	        	   compareOutput11();
	        	   break;
	           case 12:
	        	   compareOutput12();
	        	   break;
	           case 13:
	        	   compareOutput13();
	        	   break;
	           case 14:
	        	   compareOutput14();
	        	   break;
	           case 15:
	        	   compareOutput15();
	        	   break;
	           }
           }
           
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
		}
	}
	
	public void compareOutput1()
	{
		TestGenerator gen = new TestGenerator(pb, "FSMEntryTest");
		StringBuffer expected = new StringBuffer();
		StringBuffer entryActionsExpected = new StringBuffer();
		
		expected.append(gen.makeSMCHeader(1, "A"));
		
		entryActionsExpected.append(gen.makeSMCEntryAction("EntryActionSendAction"));
		
		expected.append(gen.makeSMCStateStart("A"));
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCStateStart("B"));
		
			expected.append(gen.makeSMCTransitionStart("T1Transition", "A"));
			expected.append(gen.makeSMCAction("TransitionActionAction"));
			expected.append(gen.makeSMCAction("EntryActionSendAction"));
			expected.append(gen.makeSMCTransitionEnd());
			
			expected.append("\n");
			
			expected.append(gen.makeSMCTransitionStart("T2Transition", "B"));
			expected.append(gen.makeSMCTransitionEnd());
		
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCHeaderEnd());
		
		gen.makeAssertion(expected, pb.code);
		gen.makeAssertion(entryActionsExpected, pb.entryCode);
	}
	
	public void compareOutput2()
	{
		TestGenerator gen = new TestGenerator(pb, "FSMEntryTest");
		StringBuffer expected = new StringBuffer();
		StringBuffer entryActionsExpected = new StringBuffer();
		
		expected.append(gen.makeSMCHeader(2, "parent_B"));
			
		expected.append(gen.makeSMCStateStart("parent_A"));
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCStateStart("parent_B"));
		
			expected.append(gen.makeSMCTransitionStart("T1Transition", "parent_A"));
			expected.append(gen.makeSMCAction("TransitionActionAction"));
			expected.append(gen.makeSMCAction("EntryActionSendAction"));
			expected.append(gen.makeSMCTransitionEnd());
			
			expected.append("\n");
			
			expected.append(gen.makeSMCTransitionStart("T2Transition", "parent_B"));
			expected.append(gen.makeSMCTransitionEnd());
		
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCHeaderEnd());
		
		gen.makeAssertion(expected, pb.code);
		gen.makeAssertion(entryActionsExpected, pb.entryCode);
	}
	
	public void compareOutput3()
	{
		TestGenerator gen = new TestGenerator(pb, "FSMEntryTest");
		StringBuffer expected = new StringBuffer();
		StringBuffer entryActionsExpected = new StringBuffer();
		
		expected.append(gen.makeSMCHeader(3, "A_nested_initial"));
		
		entryActionsExpected.append(gen.makeSMCEntryAction("EntryASendAction"));
		entryActionsExpected.append(gen.makeSMCEntryAction("EntryNestedA_initialSendAction"));
		
		expected.append(gen.makeSMCStateStart("A_nested_initial"));
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCStateStart("A_nested_not"));
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCStateStart("B"));
		
			expected.append(gen.makeSMCTransitionStart("T1Transition", "A_nested_initial"));
			expected.append(gen.makeSMCAction("BT1ActionAction"));
			expected.append(gen.makeSMCAction("EntryASendAction"));
			expected.append(gen.makeSMCAction("EntryNestedA_initialSendAction"));
			expected.append(gen.makeSMCTransitionEnd());
			
			expected.append("\n");
			
			expected.append(gen.makeSMCTransitionStart("T2Transition", "A_nested_initial"));
			expected.append(gen.makeSMCAction("BT2ActionAction"));
			expected.append(gen.makeSMCAction("EntryASendAction"));
			expected.append(gen.makeSMCAction("EntryNestedA_initialSendAction"));
			expected.append(gen.makeSMCTransitionEnd());
			
			expected.append("\n");
			
			expected.append(gen.makeSMCTransitionStart("T3Transition", "A_nested_not"));
			expected.append(gen.makeSMCAction("BT3ActionAction"));
			expected.append(gen.makeSMCAction("EntryASendAction"));
			expected.append(gen.makeSMCAction("EntryNestedA_not_initialSendAction"));
			expected.append(gen.makeSMCTransitionEnd());
			
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCHeaderEnd());
		
		gen.makeAssertion(expected, pb.code);
		gen.makeAssertion(entryActionsExpected, pb.entryCode);
	}
	
	public void compareOutput4()
	{
		TestGenerator gen = new TestGenerator(pb, "FSMEntryTest");
		StringBuffer expected = new StringBuffer();
		StringBuffer entryActionsExpected = new StringBuffer();
		
		expected.append(gen.makeSMCHeader(4, "A"));
		
		expected.append(gen.makeSMCStateStart("A"));
		
			expected.append(gen.makeSMCTransitionStart("T1Transition", "B_B_initial"));
			expected.append(gen.makeSMCAction("AT1ActionAction"));
			expected.append(gen.makeSMCAction("EnterBAction"));
			expected.append(gen.makeSMCAction("EnterB_initialAction"));
			expected.append(gen.makeSMCTransitionEnd());
			
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCStateStart("B_B_initial"));
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCStateStart("B_B_not_initial"));
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCHeaderEnd());
		
		gen.makeAssertion(expected, pb.code);
		gen.makeAssertion(entryActionsExpected, pb.entryCode);
	}
	
	public void compareOutput5()
	{
		TestGenerator gen = new TestGenerator(pb, "FSMEntryTest");
		StringBuffer expected = new StringBuffer();
		StringBuffer entryActionsExpected = new StringBuffer();
		
		expected.append(gen.makeSMCHeader(5, "A"));	
		
		expected.append(gen.makeSMCStateStart("A"));
		
			expected.append(gen.makeSMCTransitionStart("T1Transition", "B_B_not_initial"));
			expected.append(gen.makeSMCAction("AT1ActionAction"));
			expected.append(gen.makeSMCAction("EnterBAction"));
			expected.append(gen.makeSMCAction("EnterB_not_initialAction"));
			expected.append(gen.makeSMCTransitionEnd());
			
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCStateStart("B_B_initial"));
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCStateStart("B_B_not_initial"));
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCHeaderEnd());
		
		gen.makeAssertion(expected, pb.code);
		gen.makeAssertion(entryActionsExpected, pb.entryCode);
	}
	
	public void compareOutput6()
	{
		TestGenerator gen = new TestGenerator(pb, "FSMEntryTest");
		StringBuffer expected = new StringBuffer();
		StringBuffer entryActionsExpected = new StringBuffer();
		
		expected.append(gen.makeSMCHeader(6, "B_BB1"));
		
		entryActionsExpected.append(gen.makeSMCEntryAction("EnterBAction"));
		entryActionsExpected.append(gen.makeSMCEntryAction("EnterBB1Action"));
		
		expected.append(gen.makeSMCStateStart("B_BB1"));

			expected.append(gen.makeSMCTransitionStart("T1Transition", "B_BB1"));
			expected.append(gen.makeSMCAction("T1ActionAction"));
			expected.append(gen.makeSMCAction("EnterBB1Action"));
			expected.append(gen.makeSMCTransitionEnd());
			
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCStateStart("B_BB2"));
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCHeaderEnd());
		
		gen.makeAssertion(expected, pb.code);
		gen.makeAssertion(entryActionsExpected, pb.entryCode);
	}
	
	public void compareOutput7()
	{
		TestGenerator gen = new TestGenerator(pb, "FSMEntryTest");
		StringBuffer expected = new StringBuffer();
		StringBuffer entryActionsExpected = new StringBuffer();
		
		expected.append(gen.makeSMCHeader(7, "B_BB1"));
		
		entryActionsExpected.append(gen.makeSMCEntryAction("EnterBAction"));
		entryActionsExpected.append(gen.makeSMCEntryAction("EnterBB1Action"));
		
		expected.append(gen.makeSMCStateStart("B_BB1"));

			expected.append(gen.makeSMCTransitionStart("T1Transition", "B_BB1"));
			expected.append(gen.makeSMCAction("T1ActionAction"));
			expected.append(gen.makeSMCAction("EnterBB1Action"));
			expected.append(gen.makeSMCTransitionEnd());
			
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCStateStart("B_BB2"));
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCHeaderEnd());
		
		gen.makeAssertion(expected, pb.code);
		gen.makeAssertion(entryActionsExpected, pb.entryCode);
	}
	
	public void compareOutput8()
	{
		TestGenerator gen = new TestGenerator(pb, "FSMEntryTest");
		StringBuffer expected = new StringBuffer();
		StringBuffer entryActionsExpected = new StringBuffer();
		
		expected.append(gen.makeSMCHeader(8, "B_BB1"));
		
		entryActionsExpected.append(gen.makeSMCEntryAction("EnterBAction"));
		entryActionsExpected.append(gen.makeSMCEntryAction("EnterBB1Action"));
		
		expected.append(gen.makeSMCStateStart("B_BB1"));

			expected.append(gen.makeSMCTransitionStart("T1Transition", "B_BB2"));
			expected.append(gen.makeSMCAction("T1ActionAction"));
			expected.append(gen.makeSMCAction("EnterBB2Action"));
			expected.append(gen.makeSMCTransitionEnd());
			
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCStateStart("B_BB2"));
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCHeaderEnd());
		
		gen.makeAssertion(expected, pb.code);
		gen.makeAssertion(entryActionsExpected, pb.entryCode);
	}

	public void compareOutput9()
	{
		TestGenerator gen = new TestGenerator(pb, "FSMEntryTest");
		StringBuffer expected = new StringBuffer();
		StringBuffer entryActionsExpected = new StringBuffer();
		
		expected.append(gen.makeSMCHeader(9, "B_BB1"));
		
		entryActionsExpected.append(gen.makeSMCEntryAction("EnterBAction"));
		entryActionsExpected.append(gen.makeSMCEntryAction("EnterBB1Action"));
		
		expected.append(gen.makeSMCStateStart("B_BB1"));

			expected.append(gen.makeSMCTransitionStart("T1Transition", "B_BB2"));
			expected.append(gen.makeSMCAction("T1ActionAction"));
			expected.append(gen.makeSMCAction("EnterBB2Action"));
			expected.append(gen.makeSMCTransitionEnd());
			
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCStateStart("B_BB2"));
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCHeaderEnd());
		
		gen.makeAssertion(expected, pb.code);
		gen.makeAssertion(entryActionsExpected, pb.entryCode);
	}
	
	public void compareOutput10()
	{
		TestGenerator gen = new TestGenerator(pb, "FSMEntryTest");
		StringBuffer expected = new StringBuffer();
		StringBuffer entryActionsExpected = new StringBuffer();
		
		expected.append(gen.makeSMCHeader(10, "B_BB1"));
		
		entryActionsExpected.append(gen.makeSMCEntryAction("EnterBAction"));
		entryActionsExpected.append(gen.makeSMCEntryAction("EnterBB1Action"));
		
		expected.append(gen.makeSMCStateStart("B_BB1"));

			expected.append(gen.makeSMCTransitionStart("T1Transition", "B_BB1"));
			expected.append(gen.makeSMCAction("T1ActionAction"));
			expected.append(gen.makeSMCAction("EnterBAction"));
			expected.append(gen.makeSMCAction("EnterBB1Action"));
			expected.append(gen.makeSMCTransitionEnd());
			
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCStateStart("B_BB2"));
		
		expected.append(gen.makeSMCTransitionStart("T1Transition", "B_BB1"));
		expected.append(gen.makeSMCAction("T1ActionAction"));
		expected.append(gen.makeSMCAction("EnterBAction"));
		expected.append(gen.makeSMCAction("EnterBB1Action"));
		expected.append(gen.makeSMCTransitionEnd());
		
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCHeaderEnd());
		
		gen.makeAssertion(expected, pb.code);
		gen.makeAssertion(entryActionsExpected, pb.entryCode);
	}
	
	public void compareOutput11()
	{
		TestGenerator gen = new TestGenerator(pb, "FSMEntryTest");
		StringBuffer expected = new StringBuffer();
		StringBuffer entryActionsExpected = new StringBuffer();
		
		expected.append(gen.makeSMCHeader(11, "B_BB1"));
		
		entryActionsExpected.append(gen.makeSMCEntryAction("EnterBAction"));
		entryActionsExpected.append(gen.makeSMCEntryAction("EnterBB1Action"));
		
		expected.append(gen.makeSMCStateStart("B_BB1"));

			expected.append(gen.makeSMCTransitionStart("T1Transition", "B_BB1"));
			expected.append(gen.makeSMCAction("T1ActionAction"));
			expected.append(gen.makeSMCAction("EnterBAction"));
			expected.append(gen.makeSMCAction("EnterBB1Action"));
			expected.append(gen.makeSMCTransitionEnd());
			
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCStateStart("B_BB2"));
		
		expected.append(gen.makeSMCTransitionStart("T1Transition", "B_BB1"));
		expected.append(gen.makeSMCAction("T1ActionAction"));
		expected.append(gen.makeSMCAction("EnterBAction"));
		expected.append(gen.makeSMCAction("EnterBB1Action"));
		expected.append(gen.makeSMCTransitionEnd());
		
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCHeaderEnd());
		
		gen.makeAssertion(expected, pb.code);
		gen.makeAssertion(entryActionsExpected, pb.entryCode);
	}
	
	public void compareOutput12()
	{
		TestGenerator gen = new TestGenerator(pb, "FSMEntryTest");
		StringBuffer expected = new StringBuffer();
		StringBuffer entryActionsExpected = new StringBuffer();
		
		expected.append(gen.makeSMCHeader(12, "B_BB1"));
		
		entryActionsExpected.append(gen.makeSMCEntryAction("EnterBAction"));
		entryActionsExpected.append(gen.makeSMCEntryAction("EnterBB1Action"));
		
		expected.append(gen.makeSMCStateStart("B_BB1"));

			expected.append(gen.makeSMCTransitionStart("T1Transition", "B_BB2"));
			expected.append(gen.makeSMCAction("T1ActionAction"));
			expected.append(gen.makeSMCAction("EnterBAction"));
			expected.append(gen.makeSMCAction("EnterBB2Action"));
			expected.append(gen.makeSMCTransitionEnd());
			
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCStateStart("B_BB2"));
		
			expected.append(gen.makeSMCTransitionStart("T1Transition", "B_BB2"));
			expected.append(gen.makeSMCAction("T1ActionAction"));
			expected.append(gen.makeSMCAction("EnterBAction"));
			expected.append(gen.makeSMCAction("EnterBB2Action"));
			expected.append(gen.makeSMCTransitionEnd());
		
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCHeaderEnd());
		
		gen.makeAssertion(expected, pb.code);
		gen.makeAssertion(entryActionsExpected, pb.entryCode);
	}
	
	public void compareOutput13()
	{
		TestGenerator gen = new TestGenerator(pb, "FSMEntryTest");
		StringBuffer expected = new StringBuffer();
		StringBuffer entryActionsExpected = new StringBuffer();
		
		expected.append(gen.makeSMCHeader(13, "B_BB1"));
		
		entryActionsExpected.append(gen.makeSMCEntryAction("EnterBAction"));
		entryActionsExpected.append(gen.makeSMCEntryAction("EnterBB1Action"));
		
		expected.append(gen.makeSMCStateStart("B_BB1"));
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCStateStart("B_BB2"));
		
			expected.append(gen.makeSMCTransitionStart("T1Transition", "B_BB2"));
			expected.append(gen.makeSMCAction("T1ActionAction"));
			expected.append(gen.makeSMCAction("EnterBB2Action"));
			expected.append(gen.makeSMCTransitionEnd());
		
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCHeaderEnd());
		
		gen.makeAssertion(expected, pb.code);
		gen.makeAssertion(entryActionsExpected, pb.entryCode);
	}
	
	public void compareOutput14()
	{
		TestGenerator gen = new TestGenerator(pb, "FSMEntryTest");
		StringBuffer expected = new StringBuffer();
		StringBuffer entryActionsExpected = new StringBuffer();
		
		expected.append(gen.makeSMCHeader(14, "B_BB1"));
		
		entryActionsExpected.append(gen.makeSMCEntryAction("EnterBAction"));
		entryActionsExpected.append(gen.makeSMCEntryAction("EnterBB1Action"));

		expected.append(gen.makeSMCStateStart("B_BB1"));
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCStateStart("B_BB2"));
		
			expected.append(gen.makeSMCTransitionStart("T1Transition", "B_BB1"));
			expected.append(gen.makeSMCAction("T1ActionAction"));
			expected.append(gen.makeSMCAction("EnterBAction"));
			expected.append(gen.makeSMCAction("EnterBB1Action"));
			expected.append(gen.makeSMCTransitionEnd());
		
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCHeaderEnd());
		
		gen.makeAssertion(expected, pb.code);
		gen.makeAssertion(entryActionsExpected, pb.entryCode);
	}
	
	public void compareOutput15()
	{
		TestGenerator gen = new TestGenerator(pb, "FSMEntryTest");
		StringBuffer expected = new StringBuffer();
		StringBuffer entryActionsExpected = new StringBuffer();
		StringBuffer actionDefinitions = new StringBuffer();
		
		expected.append(gen.makeSMCHeader(15, "B_BB1"));
		
		entryActionsExpected.append(gen.makeSMCEntryAction("EnterBAction"));
		entryActionsExpected.append(gen.makeSMCEntryAction("EnterBB1Action"));
		
		expected.append(gen.makeSMCStateStart("B_BB1"));
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCStateStart("B_BB2"));
		
			expected.append(gen.makeSMCTransitionStart("T1Transition", "B_BB1"));
			expected.append(gen.makeSMCAction("TransitionActionAction"));
			expected.append(gen.makeSMCAction("EnterBAction"));
			expected.append(gen.makeSMCAction("EnterBB1Action"));
			expected.append(gen.makeSMCTransitionEnd());
			
			expected.append("\n");
			
			expected.append(gen.makeSMCTransitionStart("T2Transition", "B_BB2"));
			expected.append(gen.makeSMCAction("TransitionActionAction"));
			expected.append(gen.makeSMCAction("EnterBB2Action"));
			expected.append(gen.makeSMCTransitionEnd());
			
			expected.append("\n");
			
			expected.append(gen.makeSMCTransitionStart("T3Transition", "push(B_BB1)"));
			expected.append(gen.makeSMCAction("TransitionActionAction"));
			expected.append(gen.makeSMCAction("EnterBAction"));
			expected.append(gen.makeSMCAction("EnterBB1Action"));
			expected.append(gen.makeSMCTransitionEnd());
			
			expected.append("\n");
			
			expected.append(gen.makeSMCTransitionStart("T4Transition", "nil"));
			expected.append(gen.makeSMCAction("TransitionActionAction"));
			expected.append(gen.makeSMCTransitionEnd());
			
			expected.append("\n");
			
			expected.append(gen.makeSMCTransitionStart("T5Transition", "pop()"));
			expected.append(gen.makeSMCAction("B_BB2PopWrapper"));
			expected.append(gen.makeSMCTransitionEnd());
		
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCHeaderEnd());
		
		gen.makeAssertion(expected, pb.code);
		gen.makeAssertion(entryActionsExpected, pb.entryCode);
		
		actionDefinitions.append(gen.makeMethodDefinitionFunction("EnterBAction"));
		actionDefinitions.append(gen.makeMethodDefinitionFunction("EnterBB1Action"));
		actionDefinitions.append(gen.makeMethodDefinitionFunction("EnterBB2Action"));
		actionDefinitions.append(gen.makeMethodDefinitionFunction("TransitionActionAction"));
		
		//------------------wrapper----------------------
		actionDefinitions.append("void FSMEntryTest15::B_BB2PopWrapper()").append(System.getProperty("line.separator"));
		actionDefinitions.append("{").append(System.getProperty("line.separator"));
			actionDefinitions.append("\tchar *leafStateTOK = strtok(\"B_BB2\",\"_\");").append(System.getProperty("line.separator"));
			actionDefinitions.append("\tchar *stackStateTOK = strtok(context->peakTopStateStack(),\"_\");").append(System.getProperty("line.separator"));
			
			actionDefinitions.append(System.getProperty("line.separator"));
			
			actionDefinitions.append("\tif(strcmp(context->peakTopStateStack(),\"B_BB2\") == 0)").append(System.getProperty("line.separator"));
			actionDefinitions.append("\t{").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\tTransitionActionAction();").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\treturn;").append(System.getProperty("line.separator"));
			actionDefinitions.append("\t}").append(System.getProperty("line.separator"));
			
			actionDefinitions.append(System.getProperty("line.separator"));
			
			actionDefinitions.append("\tif(strcmp(leafStateTOK,stackStateTOK) != 0)").append(System.getProperty("line.separator"));
			actionDefinitions.append("\t{").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\tTransitionActionAction();").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\treturn;").append(System.getProperty("line.separator"));
			actionDefinitions.append("\t}").append(System.getProperty("line.separator"));
			actionDefinitions.append("\tleafStateTOK = strtok(leafStateTOK+1,\"_\");").append(System.getProperty("line.separator"));
			actionDefinitions.append("\tstackStateTOK = strtok(stackStateTOK+1,\"_\");").append(System.getProperty("line.separator"));
			
			actionDefinitions.append(System.getProperty("line.separator"));
			
			actionDefinitions.append("\tif(strcmp(leafStateTOK,stackStateTOK) != 0)").append(System.getProperty("line.separator"));
			actionDefinitions.append("\t{").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\tTransitionActionAction();").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\treturn;").append(System.getProperty("line.separator"));
			actionDefinitions.append("\t}").append(System.getProperty("line.separator"));
			actionDefinitions.append("\tleafStateTOK = strtok(leafStateTOK+1,\"_\");").append(System.getProperty("line.separator"));
			actionDefinitions.append("\tstackStateTOK = strtok(stackStateTOK+1,\"_\");").append(System.getProperty("line.separator"));
			
			actionDefinitions.append(System.getProperty("line.separator"));
		
		actionDefinitions.append("}").append(System.getProperty("line.separator"));
		
		actionDefinitions.append(System.getProperty("line.separator"));		
		
		gen.makeAssertion(actionDefinitions, pb.actionMethodCode);
	}
	
}
