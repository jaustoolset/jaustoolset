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
public class ActionTest
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
           int numberOfTests = 1;
         
           for(int i = 1; i <= numberOfTests; i++)
           {
        	   ServiceDef sd = (ServiceDef)unmarshaller.unmarshal( new File(inputDir + "FSMActionTest" + Integer.toString(i) + ".xml") );
        	   ServiceSet ss = new ServiceSet();
        	   ss.setId("abc");
        	   ss.setName("ss");
        	   ss.setVersion("1.0");
	           
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
	
/*	public void compareOutput1()
	{
		TestGenerator gen = new TestGenerator(pb, "FSMActionTest");
		StringBuffer expected = new StringBuffer();
		StringBuffer entryActionsExpected = new StringBuffer();
		
		expected.append(gen.makeSMCHeader(1, "Ready"));
		
		expected.append(gen.makeSMCStateStart("Ready"));
		
			expected.append(gen.makeSMCTransitionStart("trsTransition", "Ready"));
			expected.append(gen.makeSMCAction("Reject_RequestAction"));
			expected.append(gen.makeSMCAction("ExitSendAction"));
			expected.append(gen.makeSMCTransitionEnd());
		
		expected.append(gen.makeSMCStateEnd());
		
		expected.append(gen.makeSMCHeaderEnd());
		
		gen.makeAssertion(expected, pb.code);
	}
*/	
}
