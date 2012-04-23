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

import java.io.*;
import java.util.List;

//import org.jts.jsidl.binding.*;


/**
 * This class is responsible for outputting the code stored in the CodeLines
 * to the correct files within the target directory
 * 
 * @author Nicholas M. Johnson
 */
public class CodeWriter
{
	public CodeWriter()
	{
	}
	
	
	public void write(List<String> code, String targetDir, String fileName)
	{
		File file = new File(targetDir + "/" + fileName);
		StringBuffer strBuffer = new StringBuffer();
		
		for (String line: code)
		{
			strBuffer.append(line + System.getProperty("line.separator"));
		}
		
		try
        {
	        if (!file.exists())
	        {
	            file.createNewFile();
	        }
	        
	        if (file.isFile() && file.canWrite())
	        {
	        	Writer output = new BufferedWriter(new FileWriter(file));
	            output.write(strBuffer.toString());
	            output.close();
	        }
	        else
	        {
	            throw new Exception("Problem writing file: " + file);
	        }
        }
        catch (Exception e)
        {
        	System.out.println(e);        	
        	System.out.println("Trying to write to file: " + file.getPath() +  file.getName());
        }
	}
	
	/**
	 * Pushes all of the output to the specified file without any special  formatting
	 * 
	 * @param code
	 * @param targetDir
	 * @param fileName
	 */
	public void write(CodeLines code, String targetDir, String fileName)
	{
		File file = new File(targetDir + "/" + fileName);

        StringBuffer output = new StringBuffer();
	     
        
        output.append("Class Definitions:").append(System.getProperty("line.separator"));
        for (String line: code.classDefinitions)
        {
        	output.append(line).append(System.getProperty("line.separator"));
        }        
        output.append(System.getProperty("line.separator"));
        
        output.append("Public Attributes:").append(System.getProperty("line.separator"));
        for (String line: code.publicAttributes)
        {
        	output.append(line).append(System.getProperty("line.separator"));
        }
        output.append(System.getProperty("line.separator"));

        output.append("Public Methods:").append(System.getProperty("line.separator"));
        for (String line: code.publicMethods)
        {
        	output.append(line).append(System.getProperty("line.separator"));	        	
        }
        output.append(System.getProperty("line.separator"));

        output.append("Protected Attributes:").append(System.getProperty("line.separator"));
        for (String line: code.protectedAttributes)
        {
        	output.append(line).append(System.getProperty("line.separator"));
        }
        output.append(System.getProperty("line.separator"));

        output.append("Protected Methods:").append(System.getProperty("line.separator"));
        for (String line: code.protectedMethods)
        {
        	output.append(line).append(System.getProperty("line.separator"));	
        }
        output.append(System.getProperty("line.separator"));
        
        output.append("Constructor Lines:").append(System.getProperty("line.separator"));
        for (String line: code.constructorLines)
        {
        	output.append(line).append(System.getProperty("line.separator"));        	
        }
        output.append(System.getProperty("line.separator"));

        output.append("Destructor Lines:").append(System.getProperty("line.separator"));
        for (String line: code.destructorLines)
        {
        	output.append(line).append(System.getProperty("line.separator"));	
        }
        output.append(System.getProperty("line.separator"));

        output.append("Methods:").append(System.getProperty("line.separator"));
        for (String line: code.methods)
        {
        	output.append(line).append(System.getProperty("line.separator"));	        	
        }
        output.append(System.getProperty("line.separator"));

        output.append("Encoder Lines:").append(System.getProperty("line.separator"));
        for (String line: code.encoderLines)
        {
        	output.append(line).append(System.getProperty("line.separator"));
        }
        output.append(System.getProperty("line.separator"));

        output.append("Decoder Lines:").append(System.getProperty("line.separator"));
        for (String line: code.decoderLines)
        {
        	output.append(line).append(System.getProperty("line.separator"));	        	
        }
        
        try
        {
        	if (!file.exists())
	        {
	            file.createNewFile();
	        }
	        
	        if (file.isFile() && file.canWrite())
	        {
	        	Writer out = new BufferedWriter(new FileWriter(file));
	            out.write(output.toString());
	            out.close();
	        }
	        else
	        {
	            throw new Exception("Problem writing file: " + file);
	        }	
        }
        catch (Exception e)
        {
        	System.out.println(e);        	
        	System.out.println("Trying to write to file: " + file.getPath() +  file.getName());
        }
	}
	
	
	public void writeCppCode(CodeLines code, String targetDir, String fileName)
	{
//        StringBuffer header = new StringBuffer();
//        StringBuffer source = new StringBuffer();
//	        
//		//  Output the Header File
//        for (String line: code.classDefinitions)
//        {
//        	header.append(line).append(System.getProperty("line.separator"));
//        }
//        
//        header.append(System.getProperty("line.separator"));
//        header.append(System.getProperty("line.separator"));
//        
//        for (String line: code.publicAttributes)
//        {
//        	header.append(line).append(System.getProperty("line.separator"));
//        }
//
//        header.append(System.getProperty("line.separator"));
//        header.append(System.getProperty("line.separator"));
//
//        for (String line: code.publicMethods)
//        {
//        	header.append(line).append(System.getProperty("line.separator"));	        	
//        }
//        
//        header.append(System.getProperty("line.separator"));
//        header.append(System.getProperty("line.separator"));
//
//        for (String line: code.protectedAttributes)
//        {
//        	header.append(line).append(System.getProperty("line.separator"));
//        }
//        
//        header.append(System.getProperty("line.separator"));
//        header.append(System.getProperty("line.separator"));
//
//        for (String line: code.protectedMethods)
//        {
//        	header.append(line).append(System.getProperty("line.separator"));	
//        }
//
//        // Output the source file
//        for (String line: code.constructorLines)
//        {
//        	source.append(line).append(System.getProperty("line.separator"));        	
//        }
//        
//        source.append(System.getProperty("line.separator"));
//        source.append(System.getProperty("line.separator"));
//
//        for (String line: code.destructorLines)
//        {
//        	source.append(line).append(System.getProperty("line.separator"));	
//        }
//
//        source.append(System.getProperty("line.separator"));
//        source.append(System.getProperty("line.separator"));
//
//        for (String line: code.methods)
//        {
//        	source.append(line).append(System.getProperty("line.separator"));	        	
//        }
//
//        source.append(System.getProperty("line.separator"));
//        source.append(System.getProperty("line.separator"));
//
//        for (String line: code.encoderLines)
//        {
//        	source.append(line).append(System.getProperty("line.separator"));
//        }
//
//        source.append(System.getProperty("line.separator"));
//        source.append(System.getProperty("line.separator"));
//
//        for (String line: code.decoderLines)
//        {
//        	source.append(line).append(System.getProperty("line.separator"));	        	
//        }
//        
//        try
//        {
//			file = new File(targetDir + "include/" + fileName + ".h");
//	        if (!file.exists())
//	        {
//	            file.createNewFile();
//	        }
//	        
//	        if (file.isFile() && file.canWrite())
//	        {
//	        	Writer output = new BufferedWriter(new FileWriter(file));
//	            output.write(header.toString());
//	            output.close();
//	        }
//	        else
//	        {
//	            throw new Exception("Problem writing file: " + file);
//	        }
//	
//			file = new File(targetDir + "src/" + fileName + ".cpp");
//	        if (!file.exists())
//	        {
//	            file.createNewFile();
//	        }
//	        
//	        if (file.isFile() && file.canWrite())
//	        {
//	        	Writer output = new BufferedWriter(new FileWriter(file));
//	            output.write(source.toString());
//	            output.close();
//	        }
//	        else
//	        {
//	            throw new Exception("Problem writing file: " + file);
//	        }
//        }
//        catch (Exception e)
//        {
//        	System.out.println(e);        	
//        	System.out.println("Trying to write to file: " + file.getPath() +  file.getName());
//        }
	}
}
