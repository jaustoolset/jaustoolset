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

import static org.junit.Assert.*;

public class TestGenerator
{
	private org.jts.codegenerator.ProtocolBehaviorGenerator pb;
	private String testType;
	private int number;
	
	public TestGenerator(org.jts.codegenerator.ProtocolBehaviorGenerator pbg, String type)
	{
		pb = pbg;
		testType = type;
	}
	
	public StringBuffer makeSMCHeader(int testNumber, String entryState)
	{
		StringBuffer expected = new StringBuffer();
		expected.append("%class " + testType + testNumber + System.getProperty("line.separator"));
		expected.append("%package namespace" + System.getProperty("line.separator"));
		expected.append("%header <MessageSet.h>" + System.getProperty("line.separator"));
		expected.append("%include \"" + testType + testNumber + ".h\"" + System.getProperty("line.separator"));
		expected.append("%start " + testType + testNumber + "_SM::" + entryState + System.getProperty("line.separator"));
		expected.append("%map " + testType + testNumber + "_SM" + System.getProperty("line.separator"));
		expected.append("// State 	 Transition 	 End State 	 Action(s)" + System.getProperty("line.separator"));
		expected.append("%%" + System.getProperty("line.separator"));
		
		number = testNumber;
		
		return expected;
	}
	
	public StringBuffer makeSMCHeaderEnd()
	{
		StringBuffer expected = new StringBuffer();
		expected.append("%%" + System.getProperty("line.separator"));
		
		return expected;		
	}
	
	public StringBuffer makeSMCStateStart(String stateName)
	{
		StringBuffer expected = new StringBuffer();
		
		expected.append(stateName + "\n");
		expected.append("{\n");

		return expected;
	}
	
	public StringBuffer makeSMCStateEnd()
	{
		StringBuffer expected = new StringBuffer();
		
		expected.append("}\n\n");

		return expected;
	}
	
	public StringBuffer makeSMCTransitionStart(String transitionName, String endState)
	{
		StringBuffer expected = new StringBuffer();
		
		expected.append("\t\t");
		expected.append(transitionName + "()\n");
		expected.append("\t\t");
		expected.append(endState + "\n");
		expected.append("\t\t");
		expected.append("{\n");

		return expected;
	}
	
	public StringBuffer makeSMCTransitionStart(String transitionName, String guard, String endState)
	{
		StringBuffer expected = new StringBuffer();
		
		expected.append("\t\t");
		expected.append(transitionName + "\n");
		expected.append("\t\t");
		expected.append(guard + "\n");
		expected.append("\t\t");
		expected.append(endState + "\n");
		expected.append("\t\t");
		expected.append("{\n");
		
		return expected;
	}
	
	public StringBuffer makeSMCTransitionEnd()
	{
		StringBuffer expected = new StringBuffer();
		
		expected.append("\t\t");
		expected.append("}\n");		
		
		return expected;
	}
	
	public StringBuffer makeSMCAction(String actionName)
	{
		StringBuffer expected = new StringBuffer();
		
		expected.append("\t\t\t");
		expected.append(actionName + "();\n");

		return expected;
	}
	
	public StringBuffer makeSMCEntryAction(String actionName)
	{
		StringBuffer expected = new StringBuffer();
		
		expected.append("\t");
		expected.append(actionName + "();");
		expected.append(System.getProperty("line.separator"));

		return expected;
	}
	
	public StringBuffer makeMethodDefinitionDefault(int testNumber, String entryState)
	{
		StringBuffer expected = new StringBuffer();
		
		expected.append("void " + testType + testNumber + "::HandleInternalEvent(InternalEvent* ie)").append(System.getProperty("line.separator"));;
		expected.append("{").append(System.getProperty("line.separator"));
		expected.append("\t/// Insert User Code HERE").append(System.getProperty("line.separator"));;
		expected.append("}").append(System.getProperty("line.separator"));
		
		expected.append(System.getProperty("line.separator"));
		
		expected.append("void " + testType + testNumber + "::HandleMessage(Message* msg)").append(System.getProperty("line.separator"));;
		expected.append("{").append(System.getProperty("line.separator"));
		expected.append("\t/// Insert User Code HERE").append(System.getProperty("line.separator"));;
		expected.append("}").append(System.getProperty("line.separator"));
		
		expected.append(System.getProperty("line.separator"));
		
		expected.append("void " + testType + testNumber + "::HandleComponentMessage(ComponentMessage* cmptMsg)").append(System.getProperty("line.separator"));;
		expected.append("{").append(System.getProperty("line.separator"));
		expected.append("\t/// Insert User Code HERE").append(System.getProperty("line.separator"));;
		expected.append("}").append(System.getProperty("line.separator"));
		
		expected.append(System.getProperty("line.separator"));
		
		return expected;
	}
	
	public StringBuffer makeMethodDefinitionFunction(String functionName)
	{
		StringBuffer expected = new StringBuffer();
		
		expected.append("void " + testType + number + "::" + functionName + "()").append(System.getProperty("line.separator"));;
		expected.append("{").append(System.getProperty("line.separator"));
		expected.append("\t/// Insert User Code HERE").append(System.getProperty("line.separator"));;
		expected.append("}").append(System.getProperty("line.separator"));
		
		expected.append(System.getProperty("line.separator"));
		
		return expected;
	}
	
	public StringBuffer makeMethodDefinitionWrapper(int testNumber, String functionName)
	{
		StringBuffer expected = new StringBuffer();
		
		expected.append("void " + testType + testNumber + "::" + functionName + "()").append(System.getProperty("line.separator"));;
		expected.append("{").append(System.getProperty("line.separator"));
		expected.append("\t/// Insert User Code HERE").append(System.getProperty("line.separator"));;
		expected.append("}").append(System.getProperty("line.separator"));
		
		expected.append(System.getProperty("line.separator"));
		
		return expected;
	}
	
	public void makeAssertion(StringBuffer expected, StringBuffer match)
	{
		String flat = expected.toString();
		String flatCode = match.toString();

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