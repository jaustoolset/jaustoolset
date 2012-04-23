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
public class TransitionTest
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
           int numberOfTests = 8;
         
           for(int i = 1; i <= numberOfTests; i++)
           {
               ServiceDef sd;
               
	           sd = (ServiceDef)unmarshaller.unmarshal(new File(inputDir + "FSMTransitionTest" + Integer.toString(i) + ".xml"));
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
		StringBuffer expected = new StringBuffer();
		expected.append("%class FSMTransitionTest1\n");
		expected.append("%package namespace\n");
		expected.append("%header <MessageSet.h>\n");
		expected.append("%include \"FSMTransitionTest1.h\"\n");
		expected.append("%start FSMTransitionTest1_SM::A\n");
		expected.append("%map FSMTransitionTest1_SM\n");
		expected.append("// State 	 Transition 	 End State 	 Action(s)\n");
		expected.append("%%\n");
		
		// state A
		expected.append("A\n");
		expected.append("{\n");
		
		// simple transition
		expected.append("\t\t");
		expected.append("T1Transition()\n");
		expected.append("\t\t");
		expected.append("A\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// pop transition
		expected.append("\n");
		expected.append("\t\t");
		expected.append("T2Transition()\n");
		expected.append("\t\t");
		expected.append("pop(T5Transition)\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// push transition
		expected.append("\n");
		expected.append("\t\t");
		expected.append("T3Transition()\n");
		expected.append("\t\t");
		expected.append("push(A)\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// internal transition
		expected.append("\n");
		expected.append("\t\t");
		expected.append("T4Transition()\n");
		expected.append("\t\t");
		expected.append("nil\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// pop transition
		expected.append("\n");
		expected.append("\t\t");
		expected.append("T6Transition()\n");
		expected.append("\t\t");
		expected.append("pop()\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// end state A
		expected.append("}\n\n");
		
		expected.append("%%\n");
		
		String flat = expected.toString();
		String flatCode = pb.code.toString();

		//System.out.print(flat);
		//System.out.print(flatCode);
		
		try
		{
			//assertSame(pb.code, expected);
			
			assertEquals(flat,flatCode);
			
			for(int i = 0; i < pb.code.length(); i++)
			{
				//assertEquals(pb.code.charAt(i), expected.charAt(i));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
		}
	}
	
	public void compareOutput2()
	{
		StringBuffer expected = new StringBuffer();
		expected.append("%class FSMTransitionTest2\n");
		expected.append("%package namespace\n");
		expected.append("%header <MessageSet.h>\n");
		expected.append("%include \"FSMTransitionTest2.h\"\n");
		expected.append("%start FSMTransitionTest2_SM::A\n");
		expected.append("%map FSMTransitionTest2_SM\n");
		expected.append("// State 	 Transition 	 End State 	 Action(s)\n");
		expected.append("%%\n");
		
		// state A
		expected.append("A\n");
		expected.append("{\n");
		
		// simple transition
		expected.append("\t\t");
		expected.append("T1Transition(ParameterType:ParameterValue,bool:Run)\n");
		expected.append("\t\t");
		expected.append("[ctxt.guard1() == true && ctxt.guard2( Run )]\n");
		expected.append("\t\t");
		expected.append("A\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t\t");
		expected.append("ActionAction(ParameterValue,\"0\");\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// pop transition
		expected.append("\n");
		expected.append("\t\t");
		expected.append("T2Transition(ParameterType:ParameterValue,bool:Run)\n");
		expected.append("\t\t");
		expected.append("[ctxt.guard1() == true && ctxt.guard2( Run )]\n");
		expected.append("\t\t");
		expected.append("pop(T5Transition)\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t\t");
		expected.append("ActionAction(ParameterValue,\"0\");\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// push transition
		expected.append("\n");
		expected.append("\t\t");
		expected.append("T3Transition(ParameterType:ParameterValue,bool:Run)\n");
		expected.append("\t\t");
		expected.append("[ctxt.guard1() == true && ctxt.guard2( Run )]\n");
		expected.append("\t\t");
		expected.append("push(A)\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t\t");
		expected.append("ActionAction(ParameterValue,\"0\");\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// internal transition
		expected.append("\n");
		expected.append("\t\t");
		expected.append("T4Transition(ParameterType:ParameterValue,bool:Run)\n");
		expected.append("\t\t");
		expected.append("[ctxt.guard1() == true && ctxt.guard2( Run )]\n");
		expected.append("\t\t");
		expected.append("nil\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t\t");
		expected.append("ActionAction(ParameterValue,\"0\");\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// end state A
		expected.append("}\n\n");
		
		expected.append("%%\n");
		
		String flat = expected.toString();
		String flatCode = pb.code.toString();

		//System.out.print(flat);
		//System.out.print(flatCode);
		
		try
		{
			//assertSame(pb.code, expected);
			
			assertEquals(flat,flatCode);
			
			for(int i = 0; i < pb.code.length(); i++)
			{
				//assertEquals(pb.code.charAt(i), expected.charAt(i));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
		}
	}
	
	public void compareOutput3()
	{
		StringBuffer expected = new StringBuffer();
		expected.append("%class FSMTransitionTest3\n");
		expected.append("%package namespace\n");
		expected.append("%header <MessageSet.h>\n");
		expected.append("%include \"FSMTransitionTest3.h\"\n");
		expected.append("%start FSMTransitionTest3_SM::A\n");
		expected.append("%map FSMTransitionTest3_SM\n");
		expected.append("// State 	 Transition 	 End State 	 Action(s)\n");
		expected.append("%%\n");
		
		// state A
		expected.append("A\n");
		expected.append("{\n");
		
		// simple transition
		expected.append("\t\t");
		expected.append("Default\n");
		expected.append("\t\t");
		expected.append("[ctxt.g1()]\n");
		expected.append("\t\t");
		expected.append("A\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t\t");
		expected.append("ActionAction();\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// pop transition
		expected.append("\n");
		expected.append("\t\t");
		expected.append("Default\n");
		expected.append("\t\t");
		expected.append("[ctxt.g2()]\n");
		expected.append("\t\t");
		expected.append("pop(T5Transition)\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t\t");
		expected.append("ActionAction();\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// push transition
		expected.append("\n");
		expected.append("\t\t");
		expected.append("Default\n");
		expected.append("\t\t");
		expected.append("[ctxt.g3()]\n");
		expected.append("\t\t");
		expected.append("push(A)\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t\t");
		expected.append("ActionAction();\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// internal transition
		expected.append("\n");
		expected.append("\t\t");
		expected.append("Default\n");
		expected.append("\t\t");
		expected.append("[ctxt.g4()]\n");
		expected.append("\t\t");
		expected.append("nil\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t\t");
		expected.append("ActionAction();\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// end state A
		expected.append("}\n\n");
		
		expected.append("%%\n");
		
		String flat = expected.toString();
		String flatCode = pb.code.toString();

		//System.out.print(flat);
		//System.out.print(flatCode);
		
		try
		{
			//assertSame(pb.code, expected);
			
			assertEquals(flat,flatCode);
			
			for(int i = 0; i < pb.code.length(); i++)
			{
				//assertEquals(pb.code.charAt(i), expected.charAt(i));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
		}	
	}
	
	public void compareOutput4()
	{
		StringBuffer expected = new StringBuffer();
		expected.append("%class FSMTransitionTest4\n");
		expected.append("%package namespace\n");
		expected.append("%header <MessageSet.h>\n");
		expected.append("%include \"FSMTransitionTest4.h\"\n");
		expected.append("%start FSMTransitionTest4_SM::A\n");
		expected.append("%map FSMTransitionTest4_SM\n");
		expected.append("// State 	 Transition 	 End State 	 Action(s)\n");
		expected.append("%%\n");
		
		// state A
		expected.append("A\n");
		expected.append("{\n");
		
		// state transition
		expected.append("\t\t");
		expected.append("T1Transition(ParameterType:ParameterValue)\n");
		expected.append("\t\t");
		expected.append("[ctxt.g1()]\n");
		expected.append("\t\t");
		expected.append("A\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t\t");
		expected.append("ActionAction(ParameterValue,'0');\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// default state transition
		expected.append("\n");
		expected.append("\t\t");
		expected.append("T2Transition(ParameterType:ParameterValue)\n");
		expected.append("\t\t");
		expected.append("[ctxt.g3()]\n");
		expected.append("\t\t");
		expected.append("A\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t\t");
		expected.append("ActionAction(ParameterValue,'0');\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// state default transition
		expected.append("\t\t");
		expected.append("Default\n");
		expected.append("\t\t");
		expected.append("[ctxt.g2()]\n");
		expected.append("\t\t");
		expected.append("A\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t\t");
		expected.append("ActionAction(ParameterValue,'0');\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// default state default transition
		expected.append("\n");
		expected.append("\t\t");
		expected.append("Default\n");
		expected.append("\t\t");
		expected.append("[ctxt.g4()]\n");
		expected.append("\t\t");
		expected.append("A\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t\t");
		expected.append("ActionAction(ParameterValue,'0');\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// end state A
		expected.append("}\n\n");
		
		expected.append("%%\n");
		
		String flat = expected.toString();
		String flatCode = pb.code.toString();

		//System.out.print(flat);
		//System.out.print(flatCode);
		
		try
		{
			//assertSame(pb.code, expected);
			
			assertEquals(flat,flatCode);
			
			for(int i = 0; i < pb.code.length(); i++)
			{
				//assertEquals(pb.code.charAt(i), expected.charAt(i));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
		}
	}
	
	public void compareOutput5()
	{
		StringBuffer expected = new StringBuffer();
		expected.append("%class FSMTransitionTest5\n");
		expected.append("%package namespace\n");
		expected.append("%header <MessageSet.h>\n");
		expected.append("%include \"FSMTransitionTest5.h\"\n");
		expected.append("%start FSMTransitionTest5_SM::A\n");
		expected.append("%map FSMTransitionTest5_SM\n");
		expected.append("// State 	 Transition 	 End State 	 Action(s)\n");
		expected.append("%%\n");
		
		// state A
		expected.append("A\n");
		expected.append("{\n");
		
		// state transition
		expected.append("\t\t");
		expected.append("T1Transition(ParameterType:ParameterValue)\n");
		expected.append("\t\t");
		expected.append("A\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t\t");
		expected.append("ActionAction(ParameterValue,\"0\");\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// state : default transition
		expected.append("\t\t");
		expected.append("Default\n");
		expected.append("\t\t");
		expected.append("A\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t\t");
		expected.append("ActionAction();\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// end state A
		expected.append("}\n\n");
		
		expected.append("%%\n");
		
		String flat = expected.toString();
		String flatCode = pb.code.toString();

		//System.out.print(flat);
		//System.out.print(flatCode);
		
		try
		{
			//assertSame(pb.code, expected);
			
			assertEquals(flat,flatCode);
			
			for(int i = 0; i < pb.code.length(); i++)
			{
				//assertEquals(pb.code.charAt(i), expected.charAt(i));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
		}
	}
	
	public void compareOutput6()
	{
		StringBuffer expected = new StringBuffer();
		expected.append("%class FSMTransitionTest6\n");
		expected.append("%package namespace\n");
		expected.append("%header <MessageSet.h>\n");
		expected.append("%include \"FSMTransitionTest6.h\"\n");
		expected.append("%start FSMTransitionTest6_SM::A_B\n");
		expected.append("%map FSMTransitionTest6_SM\n");
		expected.append("// State 	 Transition 	 End State 	 Action(s)\n");
		expected.append("%%\n");
		
		// state A
		expected.append("A_B\n");
		expected.append("{\n");
		
		// state transition
		expected.append("\t\t");
		expected.append("T1Transition(ParameterType:ParameterValue)\n");
		expected.append("\t\t");
		expected.append("A_B\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t\t");
		expected.append("ActionAction(ParameterValue,\"0\");\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// state : default transition
		expected.append("\t\t");
		expected.append("Default\n");
		expected.append("\t\t");
		expected.append("A_B\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t\t");
		expected.append("ActionAction();\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// end state A
		expected.append("}\n\n");
		
		expected.append("%%\n");
		
		String flat = expected.toString();
		String flatCode = pb.code.toString();

		//System.out.print(flat);
		//System.out.print(flatCode);
		
		try
		{
			//assertSame(pb.code, expected);
			
			assertEquals(flat,flatCode);
			
			for(int i = 0; i < pb.code.length(); i++)
			{
				//assertEquals(pb.code.charAt(i), expected.charAt(i));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
		}
	}
	
	public void compareOutput7()
	{
		StringBuffer expected = new StringBuffer();
		expected.append("%class FSMTransitionTest7\n");
		expected.append("%package namespace\n");
		expected.append("%header <MessageSet.h>\n");
		expected.append("%include \"FSMTransitionTest7.h\"\n");
		expected.append("%start FSMTransitionTest7_SM::A_B\n");
		expected.append("%map FSMTransitionTest7_SM\n");
		expected.append("// State 	 Transition 	 End State 	 Action(s)\n");
		expected.append("%%\n");
		
		// state A_B
		expected.append("A_B\n");
		expected.append("{\n");
		
		// state B transition
		expected.append("\t\t");
		expected.append("T1Transition()\n");
		expected.append("\t\t");
		expected.append("[ctxt.g3()]\n");
		expected.append("\t\t");
		expected.append("A_B\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// state B default state transition
		expected.append("\n");
		expected.append("\t\t");
		expected.append("T1Transition()\n");
		expected.append("\t\t");
		expected.append("[ctxt.g5()]\n");
		expected.append("\t\t");
		expected.append("A_B\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// transition in A, because state B is start state of state A
		expected.append("\n");
		expected.append("\t\t");
		expected.append("T1Transition()\n");
		expected.append("\t\t");
		expected.append("[ctxt.g1()]\n");
		expected.append("\t\t");
		expected.append("A_C\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// state A default state transition added to state B
		expected.append("\n");
		expected.append("\t\t");
		expected.append("T1Transition()\n");
		expected.append("\t\t");
		expected.append("[ctxt.g11()]\n");
		expected.append("\t\t");
		expected.append("A_B\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// state B default transition
		expected.append("\t\t");
		expected.append("Default\n");
		expected.append("\t\t");
		expected.append("[ctxt.g4()]\n");
		expected.append("\t\t");
		expected.append("A_B\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// state B default state default transition
		expected.append("\n");
		expected.append("\t\t");
		expected.append("Default\n");
		expected.append("\t\t");
		expected.append("[ctxt.g6()]\n");
		expected.append("\t\t");
		expected.append("A_B\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// default transition in A, because state B is start state of state A
		expected.append("\n");
		expected.append("\t\t");
		expected.append("Default\n");
		expected.append("\t\t");
		expected.append("[ctxt.g2()]\n");
		expected.append("\t\t");
		expected.append("A_C\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// default transition in A default state
		expected.append("\n");
		expected.append("\t\t");
		expected.append("Default\n");
		expected.append("\t\t");
		expected.append("[ctxt.g12()]\n");
		expected.append("\t\t");
		expected.append("A_B\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// end state A_B
		expected.append("}\n\n");
		
		// state A_C
		expected.append("A_C\n");
		expected.append("{\n");
		
		// state C transition
		expected.append("\t\t");
		expected.append("T1Transition()\n");
		expected.append("\t\t");
		expected.append("[ctxt.g7()]\n");
		expected.append("\t\t");
		expected.append("A_B\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// state C default state transition
		expected.append("\n");
		expected.append("\t\t");
		expected.append("T1Transition()\n");
		expected.append("\t\t");
		expected.append("[ctxt.g9()]\n");
		expected.append("\t\t");
		expected.append("A_B\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// state A default state transition added to state C
		expected.append("\n");
		expected.append("\t\t");
		expected.append("T1Transition()\n");
		expected.append("\t\t");
		expected.append("[ctxt.g11()]\n");
		expected.append("\t\t");
		expected.append("A_B\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// state B default transition
		expected.append("\t\t");
		expected.append("Default\n");
		expected.append("\t\t");
		expected.append("[ctxt.g8()]\n");
		expected.append("\t\t");
		expected.append("A_B\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// state B default state default transition
		expected.append("\n");
		expected.append("\t\t");
		expected.append("Default\n");
		expected.append("\t\t");
		expected.append("[ctxt.g10()]\n");
		expected.append("\t\t");
		expected.append("A_B\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// default transition in A default state
		expected.append("\n");
		expected.append("\t\t");
		expected.append("Default\n");
		expected.append("\t\t");
		expected.append("[ctxt.g12()]\n");
		expected.append("\t\t");
		expected.append("A_B\n");
		expected.append("\t\t");
		expected.append("{\n");
		expected.append("\t\t");
		expected.append("}\n");
		
		// end state A_C
		expected.append("}\n\n");
		
		expected.append("%%\n");
		
		String flat = expected.toString();
		String flatCode = pb.code.toString();

		//System.out.print(flat);
		//System.out.print(flatCode);
		
		try
		{
			//assertSame(pb.code, expected);
			
			assertEquals(flat,flatCode);
			
			for(int i = 0; i < pb.code.length(); i++)
			{
				//assertEquals(pb.code.charAt(i), expected.charAt(i));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
		}
	}
	
}
