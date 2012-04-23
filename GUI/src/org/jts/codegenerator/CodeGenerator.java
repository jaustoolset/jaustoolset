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

/*
 * @(#)CodeGenerator.java		0.1 2008/09/02
 * 
 * Copyright
 */

package org.jts.codegenerator;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
//import javax.xml.bind.UnmarshalException;
import javax.xml.bind.JAXBException;
import javax.xml.validation.*;
import org.jts.jsidl.binding.ServiceSet;
import org.xml.sax.SAXParseException;
 import org.xml.sax.SAXException; 
import jargs.gnu.CmdLineParser;


/**
 * This class will generate C++ code from a JSIDL Service Definition.
 * The class will run within a separate thread. If an error occurs
 * a CodeGeneratorException will be thrown. 
 *
 * @version		0.1	2 Sept 2008 	
 * @author		Nicholas M. Johnson
 * @author		Gregory Garcia
 * @author		Jean-Francois Kamath
 * 
 *  
 */
 
 
public class CodeGenerator extends Thread 
{
   private String targetDir = null;
   private String cmptName = null;
   private String cmptId = null;
   private ServiceSet serviceSet = null;
   private CodeLines.CodeType codeType;

    /**
     * @param sDef		the ServiceDef to auto-generate code for 
     * @param targetDir	a String which specifies the directory where the code should placed	
     */	
    public CodeGenerator(String outDirName, String cmptName, String cmptId, ServiceSet sSet, CodeLines.CodeType codeType)
    {
    	this.serviceSet = sSet;
    	this.targetDir = outDirName;
    	this.cmptName = cmptName;
    	this.cmptId = cmptId;
        this.codeType = codeType;
    }
    
    /**
     * This method will generate the directories needed within the target directory provided
     * in the constructor.
     * 
     * @throws CodeGeneratorException If there is any error with execution 
     */
    public void run() throws CodeGeneratorException
    { 
    		/* Check that the target directory exists. */
    		if (!(new File(targetDir).exists()))
    		{
                throw (CodeGeneratorException) new CodeGeneratorException("Target Directory (" + targetDir + ") does not exist");
    		}
    		
              /* Run the Component Generator */
    		new ComponentGenerator(CodeLines.CodeType.C_PLUS_PLUS, CodeLines.BuildType.SCONS).run(targetDir, cmptName, cmptId, serviceSet);
    }

    private static void printUsage() {
        System.err.println(
"Usage: ant run [options]\n"+
  "options: \n"+
  "[{-i, --input} path to service set]\n"+
  "[{-o,--outdir} path to output directory (default= generatedOutput)]\n"+
  "[{-n,--name} component name (default = JAUSComponent)]\n"+
  "[{--id} component id (default = 1)]" +
  "[{--c++} set language to C++]"+
  "[{--java} set language to Java]" +
  "[{--cs} set language to C#]" +
  "[{--scons} set build to scons]" +
  "[{--vs} set build to visual studios]");
    }
 
    public static void main( String[] args ) {
 
        // create a CmdLineParser, and add to it theappropriate Options.
        CmdLineParser parser = new CmdLineParser();
        CmdLineParser.Option inputPath = parser.addStringOption('i', "input");
        CmdLineParser.Option outputPath = parser.addStringOption('o', "outdir");
        CmdLineParser.Option name = parser.addStringOption('n', "name");
        CmdLineParser.Option id = parser.addStringOption("id");
        CmdLineParser.Option cpp = parser.addBooleanOption("c++");
        CmdLineParser.Option java = parser.addBooleanOption("java");
        CmdLineParser.Option cs = parser.addBooleanOption("cs");
        CmdLineParser.Option scons = parser.addBooleanOption("scons");
        CmdLineParser.Option vs = parser.addBooleanOption("vs");
 
        // parse the user-provided command line arguments, and
        // catch any errors therein.
        try {
            parser.parse(args);
        }
        catch ( CmdLineParser.OptionException e ) {
            System.err.println(e.getMessage());
            printUsage();
            System.exit(2);
        }
        
        // validate input
        String inputPathValue = (String)parser.getOptionValue(inputPath);
        String outputPathValue = (String)parser.getOptionValue(outputPath, "generatedOutput");
        String nameValue = (String)parser.getOptionValue(name, "JAUSComponent");
        String idValue = (String)parser.getOptionValue(id, "1");
        Boolean cppValue = (Boolean)parser.getOptionValue(cpp);
        Boolean javaValue = (Boolean)parser.getOptionValue(java);
        Boolean csValue = (Boolean)parser.getOptionValue(cs);
        Boolean sconsValue = (Boolean)parser.getOptionValue(scons);
        Boolean vsValue = (Boolean)parser.getOptionValue(vs);
              
        if( ! validateInput( inputPathValue, outputPathValue, nameValue, idValue, cppValue, javaValue, csValue, sconsValue, vsValue ) ) {
            printUsage();
            System.exit(2);
        }



 
        // unmarshal input file
        ServiceSet serviceSet = null;
        try {
          JAXBContext jc = JAXBContext.newInstance("org.jts.jsidl.binding");
          Unmarshaller unmarshaller = jc.createUnmarshaller();

          // Handle the Eclipse plugin's special handling of relative paths.
          if(new File("plugins/org.jts.eclipse.data_1.0/resources").exists())
          {
            unmarshaller.setSchema(SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema").newSchema(new File("plugins/org.jts.eclipse.data_1.0/resources/schema/JSIDL_Plus/jsidl_plus.xsd")));

          }
          else
          {
            unmarshaller.setSchema(SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema").newSchema(new File("resources/schema/JSIDL_Plus/jsidl_plus.xsd")));
          }
          serviceSet = (ServiceSet)unmarshaller.unmarshal(new File(inputPathValue));
	  } catch ( JAXBException jaxbe ) {
	    jaxbe.printStackTrace();
	  } catch( SAXParseException saxe ) { 
	    saxe.printStackTrace();
	  } catch( SAXException saxe ) { 
	    saxe.printStackTrace();
	  }

	  // call code generator		
        if(cppValue != null && cppValue.booleanValue()){
        	if( vsValue != null && vsValue.booleanValue() )
        	{
        		new ComponentGenerator(CodeLines.CodeType.C_PLUS_PLUS, CodeLines.BuildType.VS).run(outputPathValue, nameValue, idValue.toString(), serviceSet);
        	}
        	else
        	{
        		new ComponentGenerator(CodeLines.CodeType.C_PLUS_PLUS, CodeLines.BuildType.SCONS).run(outputPathValue, nameValue, idValue.toString(), serviceSet);
        	}
        }
        else if (javaValue != null && javaValue.booleanValue()){
            new ComponentGenerator(CodeLines.CodeType.JAVA, CodeLines.BuildType.SCONS).run(outputPathValue, nameValue, idValue.toString(), serviceSet);
        }
        else if (csValue != null && csValue.booleanValue()){
            new ComponentGenerator(CodeLines.CodeType.C_SHARP, CodeLines.BuildType.SCONS).run(outputPathValue, nameValue, idValue.toString(), serviceSet);
        }

        System.exit(0);
    }
    
    /* TBD */
    private static boolean validateInput( String inputPathValue, String outputPathValue, 
    										String nameValue, String idValue, Boolean cppValue, 
    										Boolean javaValue, Boolean csValue, Boolean sconsValue, Boolean vsValue ) {
      if( inputPathValue == null)
        return false;
        
      return true;
    }		
}