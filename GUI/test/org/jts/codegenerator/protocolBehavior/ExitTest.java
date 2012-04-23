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


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
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


/**
 * This class runs the CodeGenerator using the xml files within the testOutput/jts_final_test folder
 * 
 */
public class ExitTest
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
	public void generateProtocolBehavior1()
	{
			
		try
		{
           int numberOfTests = 8;
         
           for(int i = 1; i <= numberOfTests; i++)
           {
        	   ServiceDef sd = (ServiceDef)unmarshaller.unmarshal( new File(inputDir + "FSMExitTest" + Integer.toString(i) + ".xml") );
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
		TestGenerator gen = new TestGenerator(pb, "FSMExitTest");
		StringBuffer expected = new StringBuffer();
		StringBuffer entryActionsExpected = new StringBuffer();
		
		expected.append(gen.makeSMCHeader(1, "A"));
		
		expected.append(gen.makeSMCStateStart("A"));
		
			expected.append(gen.makeSMCTransitionStart("T1Transition", "B"));
			expected.append(gen.makeSMCAction("ExitActionSendAction"));
			expected.append(gen.makeSMCAction("TransitionActionAction"));
			expected.append(gen.makeSMCTransitionEnd());
		
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCStateStart("B"));
		
			expected.append(gen.makeSMCTransitionStart("T2Transition", "A"));
			expected.append(gen.makeSMCTransitionEnd());
		
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCHeaderEnd());
		
		gen.makeAssertion(expected, pb.code);
		gen.makeAssertion(entryActionsExpected, pb.entryCode);		
	}
	
	public void compareOutput2()
	{
		TestGenerator gen = new TestGenerator(pb, "FSMExitTest");
		StringBuffer expected = new StringBuffer();
		StringBuffer entryActionsExpected = new StringBuffer();
		
		expected.append(gen.makeSMCHeader(2, "parent_B"));
		
		expected.append(gen.makeSMCStateStart("parent_A"));
		
			expected.append(gen.makeSMCTransitionStart("T1Transition", "parent_B"));
			expected.append(gen.makeSMCAction("ExitActionSendAction"));
			expected.append(gen.makeSMCAction("TransitionActionAction"));
			expected.append(gen.makeSMCTransitionEnd());
		
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCStateStart("parent_B"));
		
			expected.append(gen.makeSMCTransitionStart("T2Transition", "parent_A"));
			expected.append(gen.makeSMCTransitionEnd());
		
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCHeaderEnd());
		
		gen.makeAssertion(expected, pb.code);
		gen.makeAssertion(entryActionsExpected, pb.entryCode);		
	}
	
	public void compareOutput3()
	{
		TestGenerator gen = new TestGenerator(pb, "FSMExitTest");
		StringBuffer expected = new StringBuffer();
		StringBuffer entryActionsExpected = new StringBuffer();
		StringBuffer actionDefinitions = new StringBuffer();
		
		expected.append(gen.makeSMCHeader(3, "A"));
				
		expected.append(gen.makeSMCStateStart("A"));
		
			expected.append(gen.makeSMCTransitionStart("T1Transition", "B"));
			expected.append(gen.makeSMCAction("ExitActionSendAction"));
			expected.append(gen.makeSMCAction("TransitionActionAction"));
			expected.append(gen.makeSMCTransitionEnd());
			expected.append("\n");
			
			expected.append(gen.makeSMCTransitionStart("T2Transition", "A"));
			expected.append(gen.makeSMCAction("TransitionActionAction"));
			expected.append(gen.makeSMCTransitionEnd());
			expected.append("\n");
			
			expected.append(gen.makeSMCTransitionStart("T3Transition", "push(A)"));
			expected.append(gen.makeSMCAction("TransitionActionAction"));
			expected.append(gen.makeSMCTransitionEnd());
			expected.append("\n");
			
			expected.append(gen.makeSMCTransitionStart("T4Transition", "nil"));
			expected.append(gen.makeSMCAction("TransitionActionAction"));
			expected.append(gen.makeSMCTransitionEnd());
			expected.append("\n");
			
			expected.append(gen.makeSMCTransitionStart("T5Transition", "pop()"));
			expected.append(gen.makeSMCAction("APopWrapper"));
			expected.append(gen.makeSMCTransitionEnd());
		
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCStateStart("B"));
		
			expected.append(gen.makeSMCTransitionStart("T2Transition", "A"));
			expected.append(gen.makeSMCTransitionEnd());
		
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCHeaderEnd());
		
		gen.makeAssertion(expected, pb.code);
		gen.makeAssertion(entryActionsExpected, pb.entryCode);
		
		actionDefinitions.append(gen.makeMethodDefinitionFunction("ExitActionSendAction"));
		actionDefinitions.append(gen.makeMethodDefinitionFunction("TransitionActionAction"));
		
		//------------------wrapper----------------------
		actionDefinitions.append("void FSMExitTest3::APopWrapper()").append(System.getProperty("line.separator"));
		actionDefinitions.append("{").append(System.getProperty("line.separator"));
			actionDefinitions.append("\tchar *leafStateTOK = strtok(\"A\",\"_\");").append(System.getProperty("line.separator"));
			actionDefinitions.append("\tchar *stackStateTOK = strtok(context->peakTopStateStack(),\"_\");").append(System.getProperty("line.separator"));
			
			actionDefinitions.append(System.getProperty("line.separator"));
			
			actionDefinitions.append("\tif(strcmp(context->peakTopStateStack(),\"A\") == 0)").append(System.getProperty("line.separator"));
			actionDefinitions.append("\t{").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\tExitActionSendAction();").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\tTransitionActionAction();").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\treturn;").append(System.getProperty("line.separator"));
			actionDefinitions.append("\t}").append(System.getProperty("line.separator"));
			
			actionDefinitions.append(System.getProperty("line.separator"));
			
			actionDefinitions.append("\tif(strcmp(leafStateTOK,stackStateTOK) != 0)").append(System.getProperty("line.separator"));
			actionDefinitions.append("\t{").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\tExitActionSendAction();").append(System.getProperty("line.separator"));
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
	
	public void compareOutput4()
	{
		TestGenerator gen = new TestGenerator(pb, "FSMExitTest");
		StringBuffer expected = new StringBuffer();
		
		expected.append(gen.makeSMCHeader(4, "A_initial"));
				
		expected.append(gen.makeSMCStateStart("A_initial"));
		
			expected.append(gen.makeSMCTransitionStart("T1Transition", "B"));
			expected.append(gen.makeSMCAction("ExitInitialSendAction"));
			expected.append(gen.makeSMCAction("ExitASendAction"));
			expected.append(gen.makeSMCAction("T1Action"));
			expected.append(gen.makeSMCTransitionEnd());
			expected.append("\n");
			
			expected.append(gen.makeSMCTransitionStart("StateATransitionTransition", "B"));
			expected.append(gen.makeSMCAction("ExitInitialSendAction"));
			expected.append(gen.makeSMCAction("ExitASendAction"));
			expected.append(gen.makeSMCAction("StateATransitionAction"));
			expected.append(gen.makeSMCTransitionEnd());
		
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCStateStart("A_notInitial"));
		
			expected.append(gen.makeSMCTransitionStart("T1Transition", "B"));
			expected.append(gen.makeSMCAction("ExitNotInitialSendAction"));
			expected.append(gen.makeSMCAction("ExitASendAction"));
			expected.append(gen.makeSMCAction("T1Action"));
			expected.append(gen.makeSMCTransitionEnd());
			expected.append("\n");
			
			expected.append(gen.makeSMCTransitionStart("StateATransitionTransition", "B"));
			expected.append(gen.makeSMCAction("ExitNotInitialSendAction"));
			expected.append(gen.makeSMCAction("ExitASendAction"));
			expected.append(gen.makeSMCAction("StateATransitionAction"));
			expected.append(gen.makeSMCTransitionEnd());
	
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCStateStart("B"));		
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCHeaderEnd());
		
		gen.makeAssertion(expected, pb.code);		
	}
	
	public void compareOutput5()
	{
		TestGenerator gen = new TestGenerator(pb, "FSMExitTest");
		StringBuffer expected = new StringBuffer();
		
		expected.append(gen.makeSMCHeader(5, "NsmStandbyState"));
				
		expected.append(gen.makeSMCStateStart("NsmStandbyState"));		
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCStateStart("PA_K1"));
		
			expected.append(gen.makeSMCTransitionStart("K1ToPC_SimpleTransition", "PC"));
			expected.append(gen.makeSMCAction("Exited_K1Action"));
			expected.append(gen.makeSMCAction("Exited_PAAction"));
			expected.append(gen.makeSMCAction("FromK1ToPC_SimpleAction"));
			expected.append(gen.makeSMCAction("Entered_PCAction"));
			expected.append(gen.makeSMCTransitionEnd());
			expected.append("\n");
			
			expected.append(gen.makeSMCTransitionStart("K1ToK2_SimpleTransition", "PA_K2"));
			expected.append(gen.makeSMCAction("Exited_K1Action"));
			expected.append(gen.makeSMCAction("FromK1ToPC_SimpleAction"));
			expected.append(gen.makeSMCAction("Entered_K2Action"));
			expected.append(gen.makeSMCTransitionEnd());
	
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCStateStart("PA_K2"));		
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCStateStart("PC"));		
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCHeaderEnd());
		
		gen.makeAssertion(expected, pb.code);	
	}
	
	public void compareOutput6()
	{
		TestGenerator gen = new TestGenerator(pb, "FSMExitTest");
		StringBuffer expected = new StringBuffer();
		StringBuffer actionDefinitions = new StringBuffer();
		
		expected.append(gen.makeSMCHeader(6, "A_B_C"));
				
		expected.append(gen.makeSMCStateStart("A_B_C"));
		
			expected.append(gen.makeSMCTransitionStart("PopTransition", "pop()"));
			expected.append(gen.makeSMCAction("A_B_CPopWrapper"));
			expected.append(gen.makeSMCTransitionEnd());
		
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCStateStart("D"));		
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCHeaderEnd());
		
		gen.makeAssertion(expected, pb.code);
		
		actionDefinitions.append(gen.makeMethodDefinitionFunction("ExitAAction"));
		actionDefinitions.append(gen.makeMethodDefinitionFunction("ExitBAction"));
		actionDefinitions.append(gen.makeMethodDefinitionFunction("ExitCAction"));
		
		//------------------wrapper----------------------
		actionDefinitions.append("void FSMExitTest6::A_B_CPopWrapper()").append(System.getProperty("line.separator"));
		actionDefinitions.append("{").append(System.getProperty("line.separator"));
			actionDefinitions.append("\tchar *leafStateTOK = strtok(\"A_B_C\",\"_\");").append(System.getProperty("line.separator"));
			actionDefinitions.append("\tchar *stackStateTOK = strtok(context->peakTopStateStack(),\"_\");").append(System.getProperty("line.separator"));
			
			actionDefinitions.append(System.getProperty("line.separator"));
			
			actionDefinitions.append("\tif(strcmp(context->peakTopStateStack(),\"A_B_C\") == 0)").append(System.getProperty("line.separator"));
			actionDefinitions.append("\t{").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\tExitCAction();").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\tExitBAction();").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\tExitAAction();").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\treturn;").append(System.getProperty("line.separator"));
			actionDefinitions.append("\t}").append(System.getProperty("line.separator"));
			
			actionDefinitions.append(System.getProperty("line.separator"));
			
			actionDefinitions.append("\tif(strcmp(leafStateTOK,stackStateTOK) != 0)").append(System.getProperty("line.separator"));
			actionDefinitions.append("\t{").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\tExitCAction();").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\tExitBAction();").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\tExitAAction();").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\treturn;").append(System.getProperty("line.separator"));
			actionDefinitions.append("\t}").append(System.getProperty("line.separator"));
			actionDefinitions.append("\tleafStateTOK = strtok(leafStateTOK+1,\"_\");").append(System.getProperty("line.separator"));
			actionDefinitions.append("\tstackStateTOK = strtok(stackStateTOK+1,\"_\");").append(System.getProperty("line.separator"));
			
			actionDefinitions.append(System.getProperty("line.separator"));
			
			actionDefinitions.append("\tif(strcmp(leafStateTOK,stackStateTOK) != 0)").append(System.getProperty("line.separator"));
			actionDefinitions.append("\t{").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\tExitBAction();").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\tExitAAction();").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\treturn;").append(System.getProperty("line.separator"));
			actionDefinitions.append("\t}").append(System.getProperty("line.separator"));
			actionDefinitions.append("\tleafStateTOK = strtok(leafStateTOK+1,\"_\");").append(System.getProperty("line.separator"));
			actionDefinitions.append("\tstackStateTOK = strtok(stackStateTOK+1,\"_\");").append(System.getProperty("line.separator"));
			
			actionDefinitions.append(System.getProperty("line.separator"));
			
			actionDefinitions.append("\tif(strcmp(leafStateTOK,stackStateTOK) != 0)").append(System.getProperty("line.separator"));
			actionDefinitions.append("\t{").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\tExitAAction();").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\treturn;").append(System.getProperty("line.separator"));
			actionDefinitions.append("\t}").append(System.getProperty("line.separator"));
			actionDefinitions.append("\tleafStateTOK = strtok(leafStateTOK+1,\"_\");").append(System.getProperty("line.separator"));
			actionDefinitions.append("\tstackStateTOK = strtok(stackStateTOK+1,\"_\");").append(System.getProperty("line.separator"));
			
			actionDefinitions.append(System.getProperty("line.separator"));
		
		actionDefinitions.append("}").append(System.getProperty("line.separator"));
		
		actionDefinitions.append(System.getProperty("line.separator"));		
		
		gen.makeAssertion(actionDefinitions, pb.actionMethodCode);
	}
	
	public void compareOutput7()
	{
		TestGenerator gen = new TestGenerator(pb, "FSMExitTest");
		StringBuffer expected = new StringBuffer();
		StringBuffer actionDefinitions = new StringBuffer();
		
		expected.append(gen.makeSMCHeader(7, "A_B_C"));
				
		expected.append(gen.makeSMCStateStart("A_B_C"));
		
			expected.append(gen.makeSMCTransitionStart("PopTransition", "pop()"));
			expected.append(gen.makeSMCAction("A_BPopWrapper"));
			expected.append(gen.makeSMCTransitionEnd());
		
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCStateStart("D"));		
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCHeaderEnd());
		
		gen.makeAssertion(expected, pb.code);
		
		actionDefinitions.append(gen.makeMethodDefinitionFunction("ExitAAction"));
		actionDefinitions.append(gen.makeMethodDefinitionFunction("ExitBAction"));
		actionDefinitions.append(gen.makeMethodDefinitionFunction("ExitCAction"));
		
		//------------------wrapper----------------------
		actionDefinitions.append("void FSMExitTest7::A_BPopWrapper()").append(System.getProperty("line.separator"));
		actionDefinitions.append("{").append(System.getProperty("line.separator"));
			actionDefinitions.append("\tchar *leafStateTOK = strtok(\"A_B_C\",\"_\");").append(System.getProperty("line.separator"));
			actionDefinitions.append("\tchar *stackStateTOK = strtok(context->peakTopStateStack(),\"_\");").append(System.getProperty("line.separator"));
			
			actionDefinitions.append(System.getProperty("line.separator"));
			
			actionDefinitions.append("\tif(strcmp(context->peakTopStateStack(),\"A_B_C\") == 0)").append(System.getProperty("line.separator"));
			actionDefinitions.append("\t{").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\tExitCAction();").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\tExitBAction();").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\tExitAAction();").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\treturn;").append(System.getProperty("line.separator"));
			actionDefinitions.append("\t}").append(System.getProperty("line.separator"));
			
			actionDefinitions.append(System.getProperty("line.separator"));
			
			actionDefinitions.append("\tif(strcmp(leafStateTOK,stackStateTOK) != 0)").append(System.getProperty("line.separator"));
			actionDefinitions.append("\t{").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\tExitCAction();").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\tExitBAction();").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\tExitAAction();").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\treturn;").append(System.getProperty("line.separator"));
			actionDefinitions.append("\t}").append(System.getProperty("line.separator"));
			actionDefinitions.append("\tleafStateTOK = strtok(leafStateTOK+1,\"_\");").append(System.getProperty("line.separator"));
			actionDefinitions.append("\tstackStateTOK = strtok(stackStateTOK+1,\"_\");").append(System.getProperty("line.separator"));
			
			actionDefinitions.append(System.getProperty("line.separator"));
			
			actionDefinitions.append("\tif(strcmp(leafStateTOK,stackStateTOK) != 0)").append(System.getProperty("line.separator"));
			actionDefinitions.append("\t{").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\tExitBAction();").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\tExitAAction();").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\treturn;").append(System.getProperty("line.separator"));
			actionDefinitions.append("\t}").append(System.getProperty("line.separator"));
			actionDefinitions.append("\tleafStateTOK = strtok(leafStateTOK+1,\"_\");").append(System.getProperty("line.separator"));
			actionDefinitions.append("\tstackStateTOK = strtok(stackStateTOK+1,\"_\");").append(System.getProperty("line.separator"));
			
			actionDefinitions.append(System.getProperty("line.separator"));
			
			actionDefinitions.append("\tif(strcmp(leafStateTOK,stackStateTOK) != 0)").append(System.getProperty("line.separator"));
			actionDefinitions.append("\t{").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\tExitAAction();").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\treturn;").append(System.getProperty("line.separator"));
			actionDefinitions.append("\t}").append(System.getProperty("line.separator"));
			actionDefinitions.append("\tleafStateTOK = strtok(leafStateTOK+1,\"_\");").append(System.getProperty("line.separator"));
			actionDefinitions.append("\tstackStateTOK = strtok(stackStateTOK+1,\"_\");").append(System.getProperty("line.separator"));
			
			actionDefinitions.append(System.getProperty("line.separator"));
		
		actionDefinitions.append("}").append(System.getProperty("line.separator"));
		
		actionDefinitions.append(System.getProperty("line.separator"));		
		
		gen.makeAssertion(actionDefinitions, pb.actionMethodCode);
	}
	
	public void compareOutput8()
	{
		TestGenerator gen = new TestGenerator(pb, "FSMExitTest");
		StringBuffer expected = new StringBuffer();
		StringBuffer actionDefinitions = new StringBuffer();
		
		expected.append(gen.makeSMCHeader(8, "A_B_C"));
				
		expected.append(gen.makeSMCStateStart("A_B_C"));
		
			expected.append(gen.makeSMCTransitionStart("PopTransition", "pop()"));
			expected.append(gen.makeSMCAction("APopWrapper"));
			expected.append(gen.makeSMCTransitionEnd());
		
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCStateStart("D"));		
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCHeaderEnd());
		
		gen.makeAssertion(expected, pb.code);
		
		actionDefinitions.append(gen.makeMethodDefinitionFunction("ExitAAction"));
		actionDefinitions.append(gen.makeMethodDefinitionFunction("ExitBAction"));
		actionDefinitions.append(gen.makeMethodDefinitionFunction("ExitCAction"));
		
		//------------------wrapper----------------------
		actionDefinitions.append("void FSMExitTest8::APopWrapper()").append(System.getProperty("line.separator"));
		actionDefinitions.append("{").append(System.getProperty("line.separator"));
			actionDefinitions.append("\tchar *leafStateTOK = strtok(\"A_B_C\",\"_\");").append(System.getProperty("line.separator"));
			actionDefinitions.append("\tchar *stackStateTOK = strtok(context->peakTopStateStack(),\"_\");").append(System.getProperty("line.separator"));
			
			actionDefinitions.append(System.getProperty("line.separator"));
			
			actionDefinitions.append("\tif(strcmp(context->peakTopStateStack(),\"A_B_C\") == 0)").append(System.getProperty("line.separator"));
			actionDefinitions.append("\t{").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\tExitCAction();").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\tExitBAction();").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\tExitAAction();").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\treturn;").append(System.getProperty("line.separator"));
			actionDefinitions.append("\t}").append(System.getProperty("line.separator"));
			
			actionDefinitions.append(System.getProperty("line.separator"));
			
			actionDefinitions.append("\tif(strcmp(leafStateTOK,stackStateTOK) != 0)").append(System.getProperty("line.separator"));
			actionDefinitions.append("\t{").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\tExitCAction();").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\tExitBAction();").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\tExitAAction();").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\treturn;").append(System.getProperty("line.separator"));
			actionDefinitions.append("\t}").append(System.getProperty("line.separator"));
			actionDefinitions.append("\tleafStateTOK = strtok(leafStateTOK+1,\"_\");").append(System.getProperty("line.separator"));
			actionDefinitions.append("\tstackStateTOK = strtok(stackStateTOK+1,\"_\");").append(System.getProperty("line.separator"));
			
			actionDefinitions.append(System.getProperty("line.separator"));
			
			actionDefinitions.append("\tif(strcmp(leafStateTOK,stackStateTOK) != 0)").append(System.getProperty("line.separator"));
			actionDefinitions.append("\t{").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\tExitBAction();").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\tExitAAction();").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\treturn;").append(System.getProperty("line.separator"));
			actionDefinitions.append("\t}").append(System.getProperty("line.separator"));
			actionDefinitions.append("\tleafStateTOK = strtok(leafStateTOK+1,\"_\");").append(System.getProperty("line.separator"));
			actionDefinitions.append("\tstackStateTOK = strtok(stackStateTOK+1,\"_\");").append(System.getProperty("line.separator"));
			
			actionDefinitions.append(System.getProperty("line.separator"));
			
			actionDefinitions.append("\tif(strcmp(leafStateTOK,stackStateTOK) != 0)").append(System.getProperty("line.separator"));
			actionDefinitions.append("\t{").append(System.getProperty("line.separator"));
				actionDefinitions.append("\t\tExitAAction();").append(System.getProperty("line.separator"));
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
