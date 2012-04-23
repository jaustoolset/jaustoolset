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
 * @(#)CodeLines.java		0.1 2008/09/08
 * 
 * Copyright
 */

package org.jts.codegenerator;

import java.util.Vector;

public class CodeLines 
{
	public enum CodeType { C_PLUS_PLUS, JAVA, C_SHARP }
	public enum BuildType { SCONS, VS }
	
	private CodeType codeType;
	private String className;
	private String namespace;
	
    public Vector<String> classDefinitions;		/// Code: Full Defined Nested Classes: Unit Test Code: CPPUnit Macros
    public Vector<String> publicAttributes;
    public Vector<String> publicMethods;
    public Vector<String> protectedAttributes;
    public Vector<String> protectedMethods;
    public Vector<String> privateAttributes;
    public Vector<String> privateMethods;

    public Vector<String> methods;				/// Code: Class Methods; Unit Test Code: All tests for the current level
    public Vector<String> constructorLines;     /// Code: Class Constructor; Unit Test Code: setUp Method Code  		
    public Vector<String> destructorLines;		/// Code: Class Destructor; Unit Test Code: tearDown Method Code
    public Vector<String> encoderLines;			/// Code: Code for the encode Method; Unit Test Code: Code for higher level SetAndGet Test
    public Vector<String> decoderLines;			/// Code: Code for the decode Method; Unit Test Code: Code for higher level EncodeAndDecode Test
    public Vector<String> sizeLines;			// The code that will be placed in the getSize method
    public Vector<String> assignmentLines;		/// Code: Code for operator= method;
    public Vector<String> equalLines;			/// Code: Code for operator== method; 
    public Vector<String> notEqualLines;		// The code that will be placed in the operator!= method	


    /**
     * @param cName	a String that represents the name of the class being coded
     */
    public CodeLines(String cName, CodeType type, String namespace)
//    public CodeLines()
    {
    	this.codeType = type;
    	this.className = cName;
    	this.namespace = namespace;
    	
        classDefinitions = new Vector<String>();
        publicAttributes = new Vector<String>();
        publicMethods = new Vector<String>();
        protectedAttributes = new Vector<String>();
        protectedMethods = new Vector<String>();
        privateAttributes = new Vector<String>();
        privateMethods = new Vector<String>();
        methods = new Vector<String>();
        constructorLines = new Vector<String>();
        destructorLines = new Vector<String>();
        encoderLines = new Vector<String>();
        decoderLines = new Vector<String>();
        sizeLines = new Vector<String>();
        assignmentLines = new Vector<String>();
        equalLines = new Vector<String>();
        notEqualLines = new Vector<String>();
    }

    
    public void addAll(CodeLines code)
    {
        classDefinitions.addAll(code.classDefinitions);
        publicAttributes.addAll(code.publicAttributes);
        publicMethods.addAll(code.publicMethods);
        protectedAttributes.addAll(code.protectedAttributes);
        protectedMethods.addAll(code.protectedMethods);
        privateAttributes.addAll(code.privateAttributes);
        privateMethods.addAll(code.privateMethods);
        methods.addAll(code.methods);
        constructorLines.addAll(code.constructorLines);
        destructorLines.addAll(code.destructorLines);
        encoderLines.addAll(code.encoderLines);
        decoderLines.addAll(code.decoderLines);
        sizeLines.addAll(code.sizeLines);
        assignmentLines.addAll(code.assignmentLines);
        equalLines.addAll(code.equalLines);
        notEqualLines.addAll(code.notEqualLines);
    }
       
//    public String getClassName()
//    {
//    	return className;
//    }
    
    
//    public CodeType getCodeType()
//    {
//    	return codeType;
//    }
    
    
    public String getNameSpace()
    {
    	return namespace;
    }    
    
    
    public String toString()
    {
    	String output;
    	
    	output = "Class Definitions" + System.getProperty("line.separator");
    	for (String line: classDefinitions)
    	{
    		output = output + line + System.getProperty("line.separator");
    	}

    	output = output + System.getProperty("line.separator");
    	output = output + "Public Attributes" + System.getProperty("line.separator");
    	for (String line: publicAttributes)
    	{
    		output = output + line + System.getProperty("line.separator");
    	}

    	output = output + System.getProperty("line.separator");
    	output = output + "Public Methods" + System.getProperty("line.separator");
    	for (String line: publicMethods)
    	{
    		output = output + line + System.getProperty("line.separator");
    	}

    	output = output + System.getProperty("line.separator");
    	output = output + "Protected Attributes" + System.getProperty("line.separator");
    	for (String line: protectedAttributes)
    	{
    		output = output + line + System.getProperty("line.separator");
    	}

    	output = output + System.getProperty("line.separator");
    	output = output + "Protected Methods" + System.getProperty("line.separator");
    	for (String line: protectedMethods)
    	{
    		output = output + line + System.getProperty("line.separator");
    	}

    	output = output + System.getProperty("line.separator");
    	output = output + "Private Attributes" + System.getProperty("line.separator");
    	for (String line: privateAttributes)
    	{
    		output = output + line + System.getProperty("line.separator");
    	}

    	output = output + System.getProperty("line.separator");
    	output = output + "Private Methods" + System.getProperty("line.separator");
    	for (String line: privateMethods)
    	{
    		output = output + line + System.getProperty("line.separator");
    	}
    	
    	output = output + System.getProperty("line.separator");
    	output = output + "Methods" + System.getProperty("line.separator");
    	for (String line: methods)
    	{
    		output = output + line + System.getProperty("line.separator");
    	}

    	output = output + System.getProperty("line.separator");
    	output = output + "Constructor Lines" + System.getProperty("line.separator");
    	for (String line: constructorLines)
    	{
    		output = output + line + System.getProperty("line.separator");
    	}

    	output = output + System.getProperty("line.separator");
    	output = output + "Desctructor Lines" + System.getProperty("line.separator");
    	for (String line: destructorLines)
    	{
    		output = output + line + System.getProperty("line.separator");
    	}

    	output = output + System.getProperty("line.separator");
    	output = output + "Encoder Lines" + System.getProperty("line.separator");
    	for (String line: encoderLines)
    	{
    		output = output + line + System.getProperty("line.separator");
    	}
    	
    	output = output + System.getProperty("line.separator"); 
    	output = output + "Decoder Lines" + System.getProperty("line.separator");
    	for (String line: decoderLines)
    	{
    		output = output + line + System.getProperty("line.separator");
    	}
    	
    	output = output + System.getProperty("line.separator"); 
    	output = output + "Size Lines" + System.getProperty("line.separator");
    	for (String line: sizeLines)
    	{
    		output = output + line + System.getProperty("line.separator");
    	}

    	output = output + System.getProperty("line.separator"); 
    	output = output + "Assignment Lines" + System.getProperty("line.separator");
    	for (String line: assignmentLines)
    	{
    		output = output + line + System.getProperty("line.separator");
    	}

    	output = output + System.getProperty("line.separator"); 
    	output = output + "Equal Lines" + System.getProperty("line.separator");
    	for (String line: equalLines)
    	{
    		output = output + line + System.getProperty("line.separator");
    	}

    	output = output + System.getProperty("line.separator"); 
    	output = output + "Not Equal Lines" + System.getProperty("line.separator");
    	for (String line: notEqualLines)
    	{
    		output = output + line + System.getProperty("line.separator");
    	}

    	return output;
    }
    
    
    public void clear()
    {
        classDefinitions.clear();
        publicAttributes.clear();
        publicMethods.clear();
        protectedAttributes.clear();
        protectedMethods.clear();
        privateAttributes.clear();
        privateMethods.clear();
        methods.clear();
        constructorLines.clear();
        destructorLines.clear();
        encoderLines.clear();
        decoderLines.clear();
        sizeLines.clear();
        assignmentLines.clear();
        equalLines.clear();
        notEqualLines.clear();
    }
    
    public void copy(CodeLines code)
    {
    	classDefinitions.clear();
    	classDefinitions.addAll(code.classDefinitions);
        publicAttributes.clear();
        publicAttributes.addAll(code.publicAttributes);
        publicMethods.clear();
        publicMethods.addAll(code.publicMethods);
        protectedAttributes.clear();
        protectedAttributes.addAll(code.protectedAttributes);
        protectedMethods.clear();
        protectedMethods.addAll(code.protectedMethods);
        privateAttributes.clear();
        privateAttributes.addAll(code.privateAttributes);
        privateMethods.clear();
        privateMethods.addAll(code.privateMethods);
        methods.clear();
        methods.addAll(code.methods);
        constructorLines.clear();
        constructorLines.addAll(code.constructorLines);
        destructorLines.clear();
        destructorLines.addAll(code.destructorLines);
        encoderLines.clear();
        encoderLines.addAll(code.encoderLines);
        decoderLines.clear();
        decoderLines.addAll(code.decoderLines);
        sizeLines.clear();
        sizeLines.addAll(code.sizeLines);
        assignmentLines.clear();
        assignmentLines.addAll(code.assignmentLines);
        equalLines.clear();
        equalLines.addAll(code.equalLines);
        notEqualLines.clear();
        notEqualLines.addAll(code.notEqualLines);
    }
}
