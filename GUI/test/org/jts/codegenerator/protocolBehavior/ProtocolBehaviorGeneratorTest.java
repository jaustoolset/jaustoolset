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
public class ProtocolBehaviorGeneratorTest
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
           int numberOfTests = 1;
         
           for(int i = 1; i <= numberOfTests; i++)
           {
        	   //ServiceDef sd = (ServiceDef)unmarshaller.unmarshal( new File(inputDir + "FSMProtocolBehaviorGeneratorTest" + Integer.toString(i) + ".xml") );
        	   ServiceSet ss = (ServiceSet)unmarshaller.unmarshal( new File(inputDir + "Ping.xml") );
        	   //ServiceDef sd = (ServiceDef)unmarshaller.unmarshal( new File(inputDir + "NestedSet.xml") );
        	   ServiceDef sd = ss.getServiceDef().get(1);
	           
        	   pb = new org.jts.codegenerator.ProtocolBehaviorGenerator("namespace", CodeLines.CodeType.C_PLUS_PLUS, outDir, sd, ss, new StringBuffer());
	           
	           switch(i)
	           {
	           case 1:
	        	   //compareOutput1();
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
		expected.append("%class FSMProtocolBehaviorGeneratorTest1\n");
		expected.append("%package namespace\n");
		expected.append("%header <MessageSet.h>\n");
		expected.append("%include \"FSMProtocolBehaviorGeneratorTest1.h\"\n");
		expected.append("%start FSMProtocolBehaviorGeneratorTest1_SM::Ready\n");
		expected.append("%map FSMProtocolBehaviorGeneratorTest1_SM\n");
		expected.append("// State 	 Transition 	 End State 	 Action(s)\n");
		expected.append("%%\n");
		
		expected.append("Ready\n");
		expected.append("{\n");
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
