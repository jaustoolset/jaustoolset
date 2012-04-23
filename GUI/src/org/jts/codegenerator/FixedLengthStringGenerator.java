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
 * @(#)FixedLengthStringGenerator.java		0.1 2008/09/08
 * 
 * Copyright
 */

package org.jts.codegenerator;

import org.jts.jsidl.binding.*;

import java.util.List;
import java.util.Vector;

/**
 * NOTE: Does not support being nested.
 * 
 * @author Nicholas M. Johnson
 *
 */
public class FixedLengthStringGenerator
{
	private CodeLines.CodeType codeType;
	private FixedLengthString fixedLengthString;
	private List<Dimension> dimList;
	
	private Vector<String> methodCode;
	private Vector<String> methodParam;
	private String stringLength;
	private String variableType;
	private String variableTypeDecoding;
	private String fieldName;
	private String variableName;
	private String tempVariableName;
	private String fullClassName;

	/**
	 * Constructor
	 * 
	 * @param codeType
	 * @param fixedLengthString
	 */
	public FixedLengthStringGenerator(CodeLines.CodeType codeType, FixedLengthString fixedLengthString)
	{
    	this.codeType = codeType;
		this.fixedLengthString = fixedLengthString;
		this.dimList = null;
		
		 methodCode = new Vector<String>();
		 methodParam = new Vector<String>();
		 stringLength = fixedLengthString.getStringLength();
         if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
         {
             variableType = "jFixedLengthString";
             variableTypeDecoding = "char";
             fieldName = Util.upperCaseFirstLetter(fixedLengthString.getName());
             variableName =  CppCode.getVariableName(fieldName);
         }
         else if(codeType == CodeLines.CodeType.JAVA)
         {
             variableType = "String";
             variableTypeDecoding = "char[]";
             fieldName = Util.upperCaseFirstLetter(fixedLengthString.getName());
             variableName =  JavaCode.getVariableName(fieldName);
         }
         else if(codeType == CodeLines.CodeType.C_SHARP)
         {
             variableType = "string";
             variableTypeDecoding = "char[]";
             fieldName = Util.upperCaseFirstLetter(fixedLengthString.getName());
             variableName =  CppCode.getVariableName(fieldName);
         }
		 tempVariableName = variableName + "Temp";
	}

	
	public FixedLengthStringGenerator(CodeLines.CodeType codeType, FixedLengthString fixedLengthString, List<Dimension> dimList)
	{
    	this.codeType = codeType;
		this.fixedLengthString = fixedLengthString;
		this.dimList = dimList;
		
		 methodCode = new Vector<String>();
		 methodParam = new Vector<String>();
		 stringLength = fixedLengthString.getStringLength();
         if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
         {
             variableType = "jFixedLengthString";
             variableTypeDecoding = "char";
             fieldName = Util.upperCaseFirstLetter(fixedLengthString.getName());
             variableName =  CppCode.getVariableName(fieldName);
         }
         else if(codeType == CodeLines.CodeType.JAVA)
         {
             variableType = "String";
             variableTypeDecoding = "char[]";
             fieldName = Util.upperCaseFirstLetter(fixedLengthString.getName());
             variableName =  JavaCode.getVariableName(fieldName);
         }
         else if(codeType == CodeLines.CodeType.C_SHARP)
         {
             variableType = "string";
             variableTypeDecoding = "char[]";
             fieldName = Util.upperCaseFirstLetter(fixedLengthString.getName());
             variableName =  CSharpCode.getVariableName(fieldName);
         }
		 

		 tempVariableName = variableName + "Temp";
	}
	
	
	/**
	 * Creates the code required to access, modify, encode and decode a FixedLenghtString
	 * 
	 * @param parentClassName
	 * @param pvIndex
	 * @param isNested
	 * @param code
	 * @throws CodeGeneratorException
	 */
	public void run(String parentClassName, int pvIndex, boolean isNested, CodeLines code) throws CodeGeneratorException
	{
		if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
		{			
			if (isNested)
			{
				fullClassName = parentClassName + "::" + fixedLengthString.getName();
			}
			else
			{
				fullClassName = parentClassName;
			}
			
			if (dimList == null)
			{
				if (isNested)
				{
					/// NOT IMPLEMENTED
					throw new CodeGeneratorException("Desired Output is Not Implemented");
				}
				else
				{
					CodeLines flsCode = new CodeLines("", codeType, code.getNameSpace());
					
					generateFieldInstance(pvIndex, flsCode);
					code.addAll(flsCode);
				}
			}
			else
			{
				if (isNested)
				{
					/// NOT IMPLEMENTED
					throw new CodeGeneratorException("Desired Output is Not Implemented");
				}
				else
				{
					CodeLines flsCode = new CodeLines("", codeType, code.getNameSpace());
					
					generateFieldArray(pvIndex, flsCode);
					code.addAll(flsCode);
				}
			}
		}
                else if (codeType == CodeLines.CodeType.JAVA)
		{
			if (isNested)
			{
				fullClassName = parentClassName + "." + fixedLengthString.getName();
			}
			else
			{
				fullClassName = parentClassName;
			}

			if (dimList == null)
			{
				if (isNested)
				{
					/// NOT IMPLEMENTED
					throw new CodeGeneratorException("Desired Output is Not Implemented");
				}
				else
				{
					CodeLines flsCode = new CodeLines("", codeType, code.getNameSpace());

					generateFieldInstance(pvIndex, flsCode);
					code.addAll(flsCode);
				}
			}
			else
			{
				if (isNested)
				{
					/// NOT IMPLEMENTED
					throw new CodeGeneratorException("Desired Output is Not Implemented");
				}
				else
				{
					CodeLines flsCode = new CodeLines("", codeType, code.getNameSpace());

					generateFieldArray(pvIndex, flsCode);
					code.addAll(flsCode);
				}
			}
		}
                else if (codeType == CodeLines.CodeType.C_SHARP)
		{
			if (isNested)
			{
				fullClassName = parentClassName + "." + fixedLengthString.getName();
			}
			else
			{
				fullClassName = parentClassName;
			}

			if (dimList == null)
			{
				if (isNested)
				{
					/// NOT IMPLEMENTED
					throw new CodeGeneratorException("Desired Output is Not Implemented");
				}
				else
				{
					CodeLines flsCode = new CodeLines("", codeType, code.getNameSpace());

					generateFieldInstance(pvIndex, flsCode);
					code.addAll(flsCode);
				}
			}
			else
			{
				if (isNested)
				{
					/// NOT IMPLEMENTED
					throw new CodeGeneratorException("Desired Output is Not Implemented");
				}
				else
				{
					CodeLines flsCode = new CodeLines("", codeType, code.getNameSpace());

					generateFieldArray(pvIndex, flsCode);
					code.addAll(flsCode);
				}
			}
		}
	}
	
	/**
	 * Generate the field
	 * @param fullClassName
	 * @param pvIndex
	 * @param code
	 * @throws CodeGeneratorException
	 */
    private void generateFieldInstance(int pvIndex, CodeLines code) throws CodeGeneratorException
    {
        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            code.protectedAttributes.add(CppCode.createVariableDeclaration(variableType, fieldName, false));
            code.constructorLines.add(variableName + ".setSize(" + stringLength + ");");

        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            code.protectedAttributes.add(JavaCode.createVariableDeclaration(variableType, fieldName, false));
            code.constructorLines.add(variableName + " = \"\";");

        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            code.protectedAttributes.add(CSharpCode.createVariableDeclaration(variableType, fieldName, true));
            code.constructorLines.add(variableName + " = \"\";");

        }
        generateInstanceGetSizeMethod(code, pvIndex);
        
        generateInstanceIsValidMethod(code, pvIndex);

        generateInstanceEncodeMethod(code, pvIndex);
        generateInstanceDecodeMethod(code, pvIndex);
        
        generateInstanceGetMethod(code);
    	generateInstanceSetStringMethod(code, pvIndex);
    	generateInstanceSetValueMethod(code, pvIndex);
    	
    	generateInstanceOverloadAssignementMethod(code);
    	generateInstanceOverloadIsEqualMethod(code);
    	generateInstanceOverloadNotEqualsMethod(code);
    }
    
    private void generateInstanceGetSizeMethod(CodeLines code, int pvIndex)
    {
    	methodCode.clear();
    	methodCode.clear();
        /// GetSize method
        /// only add size of array if optional is true
        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            methodCode.add("size += " + stringLength + ";");
            if (fixedLengthString.isOptional())
            {
                methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            methodCode.add("size += " + stringLength + ";");
            if (fixedLengthString.isOptional())
            {
                methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            methodCode.add("size += (int)" + stringLength + ";");
            if (fixedLengthString.isOptional())
            {
                methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        code.sizeLines.addAll(methodCode);
    }
    
    private void generateInstanceIsValidMethod(CodeLines code, int pvIndex)
    {
	    /// is[Field]valid added if optional
	    /// return true if field is set in presence vector
	    if (fixedLengthString.isOptional()) 
	    {
	    	methodCode.clear();

                if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
                {
                    code.publicMethods.add(CppCode.createMethodDeclaration("bool", "is", fieldName + "Valid", null, true));

                    methodCode.add("return true;");
                    methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                    methodCode.add("return false;");
                    code.methods.addAll(CppCode.createMethodDefinition("bool", fullClassName + "::is", fieldName + "Valid", null, methodCode, true));
                }
                else if(codeType == CodeLines.CodeType.JAVA)
                {
                    methodCode.add("return true;");
                    methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                    methodCode.add("return false;");
                    code.methods.addAll(JavaCode.createMethodDefinition("public boolean", "is", fieldName + "Valid", null, methodCode, true));
                }
                else if(codeType == CodeLines.CodeType.C_SHARP)
                {
                    methodCode.add("return true;");
                    methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
                    methodCode.add("return false;");
                    code.methods.addAll(CSharpCode.createMethodDefinition("public bool", "is", fieldName + "Valid", null, methodCode, true));
                }
	    }
    }
    
    private void generateInstanceEncodeMethod(CodeLines code, int pvIndex)
    {
    	methodCode.clear();
    	
        //For Encoding
        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            methodCode.add(variableType + " " + tempVariableName + "(" + stringLength +");");
            methodCode.add("");
            methodCode.add(tempVariableName + " = " + code.getNameSpace() + "::correctEndianness(" + variableName + ".c_str());");
            methodCode.add("memcpy(bytes+pos, " + tempVariableName + ".c_str(), " + stringLength + ");");
            methodCode.add("pos += " + fixedLengthString.getStringLength() + ";");
            if (fixedLengthString.isOptional())
            {
        	methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            methodCode.add(variableTypeDecoding + " " + tempVariableName + " = new char[" + stringLength +"];");
            methodCode.add(tempVariableName + " = " + variableName + ".toCharArray();");

            JavaCode.encodeByteArrayLines(tempVariableName, tempVariableName + ".length" ,methodCode, true);
            methodCode.add("");
            methodCode.add("for(int i = " + tempVariableName + ".length; i<" + stringLength + "; i++)");
            methodCode.add("{");
            methodCode.add("\tbytes.put(pos, (byte) 0);");
            methodCode.add("\tpos++;");
            methodCode.add("}");
            if (fixedLengthString.isOptional())
            {
        	methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            //methodCode.add(variableTypeDecoding + " " + tempVariableName + " = new char[" + stringLength +"];");
            CSharpCode.generateEncoderLines(variableType, variableName, methodCode, stringLength);
            if (fixedLengthString.isOptional())
            {
        	methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        if (!code.encoderLines.isEmpty())
        {
        	code.encoderLines.add("");
        }
        code.encoderLines.addAll(methodCode);
    }
    
    private void generateInstanceDecodeMethod(CodeLines code, int pvIndex)
    {
    	methodCode.clear();
    	
        //For Decoding
        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            methodCode.add(variableTypeDecoding + " " + tempVariableName + "[" + stringLength +"];");
            methodCode.add("");
            methodCode.add("memcpy("+ tempVariableName + ", bytes+pos, " + stringLength + ");" );
            methodCode.add(variableName + " = " + code.getNameSpace() + "::correctEndianness(" + tempVariableName + ");");
            methodCode.add("pos += " + stringLength + ";");
            if (fixedLengthString.isOptional())
            {
        	methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            methodCode.add(variableTypeDecoding + " " + tempVariableName + "= new char[(int)" + stringLength + "];");
            methodCode.add("");
            methodCode.add("int i; byte by; int tmp = pos;");
            methodCode.add("for(i=0; i<" + stringLength + "; i++)");
            methodCode.add("{");
            methodCode.add("\tby = bytes.get(tmp);");
            methodCode.add("\tif(by == 0)");
            methodCode.add("\t{");
            methodCode.add("\t\tbreak;");
            methodCode.add("\t}");
            methodCode.add("\t" + tempVariableName + "[i] = (char) (by&0xff);");
            methodCode.add("\ttmp++;");
            methodCode.add("}");
            methodCode.add("pos += " + stringLength + ";");
            methodCode.add("");
            methodCode.add(variableName + " = new String(" + tempVariableName + ");");
            methodCode.add("if(i<80)");
			methodCode.add("{");
			methodCode.add("\t" + variableName + " = " + variableName + ".substring(0, i);");
            methodCode.add("}");

            if (fixedLengthString.isOptional())
            {
                methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
            }

        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {            
            CSharpCode.generateDecoderLines(variableType, variableName, methodCode, stringLength);


            if (fixedLengthString.isOptional())
            {
        	methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        
        if (!code.decoderLines.isEmpty())
        {
        	code.decoderLines.add("");
        }
        code.decoderLines.addAll(methodCode);
    }
    
    private void generateInstanceGetMethod(CodeLines code)
    {
    	methodCode.clear();
    	
        //Generates the getMethod Declaration and Definition
        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            code.publicMethods.add(CppCode.createMethodDeclaration(variableType, "get", fieldName, null, false));	//should this be fixed length string
        
            methodCode.add("return " + CppCode.getVariableName(fieldName) + ";");
            code.methods.addAll(CppCode.createMethodDefinition(variableType, fullClassName + "::get", fieldName, null, methodCode, false)); //methodCode?? and prefix?
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            methodCode.add("return " + JavaCode.getVariableName(fieldName) + ";");
            code.methods.addAll(JavaCode.createMethodDefinition("public " + variableType, "get", fieldName, null, methodCode, false)); //methodCode?? and prefix?
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            methodCode.add("return " + CSharpCode.getVariableName(fieldName) + ";");
            code.methods.addAll(CSharpCode.createMethodDefinition("public " + variableType, "get", fieldName, null, methodCode, false)); //methodCode?? and prefix?
        }
    }
    
    private void generateInstanceSetStringMethod(CodeLines code, int pvIndex)
    {
    	methodCode.clear();
    	methodParam.clear();
    	
        //Generate SetMethod Declaration and Definition for a string
        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            methodParam.add("std::string value");
            code.publicMethods.add(CppCode.createMethodDeclaration("int", "set", fieldName, methodParam, false));

            //method code
            methodCode.add(""+ variableName +" = value;");
            if (fixedLengthString.isOptional())
            {
                    methodCode.add("setPresenceVector("+ pvIndex +");");
            }
            // Create framework for parent reference
            methodCode.add(CppCode.getParentReferenceSetParentPVLine());
            methodCode.add("return 0;");

            code.methods.addAll(CppCode.createMethodDefinition("int", fullClassName + "::set", fieldName, methodParam, methodCode, false));
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            methodParam.add("String value");

            //method code
            methodCode.add(""+ variableName +" = value;");
            if (fixedLengthString.isOptional())
            {
                    methodCode.add("setPresenceVector("+ pvIndex +");");
            }
            // Create framework for parent reference
            methodCode.add(JavaCode.getParentReferenceSetParentPVLine());
            methodCode.add("return 0;");

            code.methods.addAll(JavaCode.createMethodDefinition("public int", "set", fieldName, methodParam, methodCode, false));
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            methodParam.add("string value");

            //method code
            methodCode.add(""+ variableName +" = value;");
            if (fixedLengthString.isOptional())
            {
                    methodCode.add("setPresenceVector("+ pvIndex +");");
            }
            // Create framework for parent reference
            methodCode.add(CSharpCode.getParentReferenceSetParentPVLine());
            methodCode.add("return 0;");

            code.methods.addAll(CSharpCode.createMethodDefinition("public int", "set", fieldName, methodParam, methodCode, false));
        }        
    }
    
    private void generateInstanceSetValueMethod(CodeLines code, int pvIndex)
    {
    	methodCode.clear();
    	methodParam.clear();
    	
        //Generate SetMethod Declaration and Definition for a jFixedLengthString
        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            methodParam.add("jFixedLengthString value");
            code.publicMethods.add(CppCode.createMethodDeclaration("int", "set", fieldName, methodParam, false));
        
            //method code
            methodCode.add(""+ variableName +" = value;");
            if (fixedLengthString.isOptional())
            {
                methodCode.add("setPresenceVector("+ pvIndex +");");
            }

            // Create framework for parent reference
            methodCode.add(CppCode.getParentReferenceSetParentPVLine());

            methodCode.add("return 0;");
            code.methods.addAll(CppCode.createMethodDefinition("int", fullClassName + "::set", fieldName, methodParam, methodCode, false));
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            // If the code is in Java, then this method creates a duplicate method to generateInstanceSetStringMethod(...).
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            // If the code is in C#, then this method is a duplicate.
        }
    }
    
    private void generateInstanceOverloadAssignementMethod(CodeLines code)
    {
		/// Overload the operator=
        if(codeType == CodeLines.CodeType.C_SHARP)
        {
            code.assignmentLines.add(variableName + " = value.get" + variableName.substring(variableName.indexOf(CSharpCode.VARIABLE_PREFIX) + 2) + "();");
        }
        else
        {
            code.assignmentLines.add(variableName + " = value." + variableName + ";");
        }
    }
    
    private void generateInstanceOverloadIsEqualMethod(CodeLines code)
    {
        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            /// Overload operator==
            code.equalLines.add("if (" + variableName + " != value." + variableName + ")");
            code.equalLines.add("{");
            code.equalLines.add("\treturn false;");
            code.equalLines.add("}");
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            code.equalLines.add("if (" + variableName + " != value." + variableName + ")");
            code.equalLines.add("{");
            code.equalLines.add("\treturn false;");
            code.equalLines.add("}");
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            code.equalLines.add("if (this.get" + variableName.substring(variableName.indexOf(CSharpCode.VARIABLE_PREFIX) + 2) + "() != value.get" + variableName.substring(variableName.indexOf(CSharpCode.VARIABLE_PREFIX) + 2) + "())");
            code.equalLines.add("{");
            code.equalLines.add("\treturn false;");
            code.equalLines.add("}");
        }
    }
    
    private void generateInstanceOverloadNotEqualsMethod(CodeLines code)
    {
        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            /// Overload operator!=
            code.notEqualLines.add("if (" + variableName + " == value." + variableName + ")");
            code.notEqualLines.add("{");
            code.notEqualLines.add("\treturn false;");
            code.notEqualLines.add("}");
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            code.notEqualLines.add("if (" + variableName + " == value." + variableName + ")");
            code.notEqualLines.add("{");
            code.notEqualLines.add("\treturn false;");
            code.notEqualLines.add("}");
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            code.notEqualLines.add("if (this.get" + variableName.substring(variableName.indexOf(CSharpCode.VARIABLE_PREFIX) + 2) + "() == value.get" + variableName.substring(variableName.indexOf(CSharpCode.VARIABLE_PREFIX) + 2) + "())");
            code.notEqualLines.add("{");
            code.notEqualLines.add("\treturn false;");
            code.notEqualLines.add("}");
        }
    }	
	
    /**
     * Generate the array
     * @param fullClassName
     * @param pvIndex
     * @param code
     * @throws CodeGeneratorException
     */
	private void generateFieldArray(int pvIndex, CodeLines code) throws CodeGeneratorException
    {
    	Vector<String> paramCode = new Vector<String>();    	

    	Vector<String> getMethodCode = new Vector<String>();
        Vector<String> setMethodCode = new Vector<String>();

        String posCalc = "";	// The calculation for the position within an 1-dimension array from the n-dimension array
        String prevCalc = "";	// The previous calculation for the position within an 1-dimension array from the n-dimension array
        int arraySize = 1;

        paramCode.clear();
        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            for (int i = 0; i < dimList.size(); i++)
            {
                Dimension dim = dimList.get(i);

                String dimName = Util.upperCaseFirstLetter(dim.getName());
                String dimSizeName = dimName + "Size";
                String dimVarName = CppCode.getVariableName(dimSizeName);

                code.protectedAttributes.add(CppCode.createVariableDeclaration("unsigned int", dimSizeName, false));
                code.constructorLines.add(dimVarName + " = " + dim.getSize() + ";");

                ///  Generate getMethod Declaration and Definition
                code.publicMethods.add(CppCode.createMethodDeclaration("const unsigned int", "get", dimSizeName, null, true));

                methodCode.clear();
                methodCode.add("return " + dimVarName + ";");
                code.methods.addAll(CppCode.createMethodDefinition("const unsigned int", fullClassName + "::get", dimSizeName, null, methodCode, true));

                /// Add the dimension to the method parameter vector for the get and set methods
                paramCode.add("unsigned int " + dimName);

                getMethodCode.add("if (" + dimName + " >= " + dim.getSize() + ")");
                getMethodCode.add("{");
                getMethodCode.add("\treturn 0;");
                getMethodCode.add("}");
                getMethodCode.add("");

                setMethodCode.add("if (" + dimName + " >= " + dim.getSize() + ")");
                setMethodCode.add("{");
                setMethodCode.add("\treturn 1;");
                setMethodCode.add("}");
                setMethodCode.add("");


                int dimSize = Integer.parseInt(dim.getSize());
                arraySize = arraySize * dimSize;

                if (i > 0)
                {
                    prevCalc += " * ";
                    posCalc += " + (" + prevCalc + dimName + ")";
                }
                else
                {
                    posCalc = dimName;
                }

                prevCalc += dim.getSize();
            }


        code.protectedAttributes.add(CppCode.createVariableDeclaration(variableType, fieldName + "[" + arraySize + "]", false));
        code.constructorLines.add("for (unsigned int i = 0; i < " + arraySize + "; i++)");
        code.constructorLines.add("{");
        code.constructorLines.add("/// Set the size of all the members of the array");
        code.constructorLines.add("\t" + variableName + "[i].setSize(" + stringLength + ");");
        code.constructorLines.add("}");
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            for (int i = 0; i < dimList.size(); i++)
            {
                Dimension dim = dimList.get(i);

                String dimName = Util.upperCaseFirstLetter(dim.getName());
                String dimSizeName = dimName + "Size";
                String dimVarName = JavaCode.getVariableName(dimSizeName);

                code.protectedAttributes.add(JavaCode.createVariableDeclaration("protected long", dimSizeName, false));
                code.constructorLines.add(dimVarName + " = " + dim.getSize() + ";");

                ///  Generate getMethod Definition
                methodCode.clear();
                methodCode.add("return " + dimVarName + ";");
                code.methods.addAll(JavaCode.createMethodDefinition("public long", "get", dimSizeName, null, methodCode, true));

                /// Add the dimension to the method parameter vector for the get and set methods
                paramCode.add("long " + dimName);

                getMethodCode.add("if (" + dimName + " >= " + dim.getSize() + ")");
                getMethodCode.add("{");
                getMethodCode.add("\treturn null;");
                getMethodCode.add("}");
                getMethodCode.add("");

                setMethodCode.add("if (" + dimName + " >= " + dim.getSize() + ")");
                setMethodCode.add("{");
                setMethodCode.add("\treturn;");
                setMethodCode.add("}");
                setMethodCode.add("");


                int dimSize = Integer.parseInt(dim.getSize());
                arraySize = arraySize * dimSize;

                if (i > 0)
                {
                    prevCalc += " * ";
                    posCalc += " + (" + prevCalc + dimName + ")";
                }
                else
                {
                    posCalc = dimName;
                }

                prevCalc += dim.getSize();
            }


        code.protectedAttributes.add(JavaCode.createVariableDeclaration("protected " + variableType + "[]", fieldName + " = new " + variableType +"[" + arraySize + "]", false));
        code.constructorLines.add("for (int i = 0; i < " + arraySize + "; i++)");
        code.constructorLines.add("{");
        code.constructorLines.add("/// Set the size of all the members of the array");
        code.constructorLines.add("\t" + variableName + "[i] = new " + variableType + "();");
        code.constructorLines.add("}");
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            for (int i = 0; i < dimList.size(); i++)
            {
                Dimension dim = dimList.get(i);

                String dimName = Util.upperCaseFirstLetter(dim.getName());
                String dimSizeName = dimName + "Size";
                String dimVarName = CSharpCode.getVariableName(dimSizeName);

                code.protectedAttributes.add(CSharpCode.createVariableDeclaration("int", dimSizeName, true));
                code.constructorLines.add(dimVarName + " = " + dim.getSize() + ";");

                ///  Generate getMethod Definition

                methodCode.clear();
                methodCode.add("return " + dimVarName + ";");
                code.methods.addAll(CSharpCode.createMethodDefinition("public int", "get", dimSizeName, null, methodCode, true));

                /// Add the dimension to the method parameter vector for the get and set methods
                paramCode.add("int " + dimName);

                getMethodCode.add("if (" + dimName + " >= " + dim.getSize() + ")");
                getMethodCode.add("{");
                getMethodCode.add("\treturn null;");
                getMethodCode.add("}");
                getMethodCode.add("");

                setMethodCode.add("if (" + dimName + " >= " + dim.getSize() + ")");
                setMethodCode.add("{");
                setMethodCode.add("\treturn 1;");
                setMethodCode.add("}");
                setMethodCode.add("");


                int dimSize = Integer.parseInt(dim.getSize());
                arraySize = arraySize * dimSize;

                if (i > 0)
                {
                    prevCalc += " * ";
                    posCalc += " + (" + prevCalc + dimName + ")";
                }
                else
                {
                    posCalc = dimName;
                }

                prevCalc += dim.getSize();
            }


            code.protectedAttributes.add(CSharpCode.createVariableDeclaration("protected " + variableType+ "[]", fieldName + " = new " + variableType +"[" + arraySize + "]", true));
            code.constructorLines.add("for (uint i = 0; i < " + arraySize + "; i++)");
            code.constructorLines.add("{");
            code.constructorLines.add("// Initialize of all the members of the array");
            code.constructorLines.add("\t" + variableName + "[i] = \"\";");
            code.constructorLines.add("}");
        }

        generateArrayGetSizeMethod(code, arraySize, pvIndex);

        generateArrayIsValidMethod(code, pvIndex);

        generateArrayEncodeMethod(code, arraySize, pvIndex);
        generateArrayDecodeMethod(code, arraySize, pvIndex);

        generateArrayGetMethod(code, posCalc, paramCode, getMethodCode);
        generateArraySetStringMethod(code, posCalc, pvIndex, paramCode, setMethodCode);
        generateArraySetVariableMethod(code, posCalc, pvIndex, paramCode, setMethodCode);

        generateArrayOverloadAssignmentMethod(code, arraySize);
        generateArrayOverloadIsEqualMethod(code, arraySize);
        generateArrayOverloadNotEqualMethod(code, arraySize);
        }
	
	private void generateArrayGetSizeMethod(CodeLines code, int arraySize, int pvIndex)
	{
	    /// GetSize method
	    /// only add size of array if optional is true
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
                {
                methodCode.clear();
                methodCode.add("size += (" + stringLength + " * " + arraySize + ");");
                if (fixedLengthString.isOptional())
                {
                    methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                }
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                methodCode.clear();
                methodCode.add("size += (" + stringLength + " * " + arraySize + ");");
                if (fixedLengthString.isOptional())
                {
                    methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                }
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                methodCode.clear();
                methodCode.add("size += (" + stringLength + " * " + arraySize + ");");
                if (fixedLengthString.isOptional())
                {
                    methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
                }
	    }
	    code.sizeLines.addAll(methodCode);
	}
	
	private void generateArrayIsValidMethod(CodeLines code, int pvIndex)
	{
	    /// is[Field]valid added if optional
	    /// return true if field is set in presence vector
	    if (fixedLengthString.isOptional()) 
	    {
                if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
                {
                    code.publicMethods.add(CppCode.createMethodDeclaration("bool", "is", fieldName + "Valid", null, true));
                    methodCode.clear();
                    methodCode.add("return true;");
                    methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                    methodCode.add("return false;");
                    code.methods.addAll(CppCode.createMethodDefinition("bool", fullClassName + "::is", fieldName + "Valid", null, methodCode, true));
                }
                else if(codeType == CodeLines.CodeType.JAVA)
                {
                    methodCode.clear();
                    methodCode.add("return true;");
                    methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                    methodCode.add("return false;");
                    code.methods.addAll(JavaCode.createMethodDefinition("public boolean", "is", fieldName + "Valid", null, methodCode, true));
                }
                else if(codeType == CodeLines.CodeType.C_SHARP)
                {
                    methodCode.clear();
                    methodCode.add("return true;");
                    methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
                    methodCode.add("return false;");
                    code.methods.addAll(CSharpCode.createMethodDefinition("public bool", "is", fieldName + "Valid", null, methodCode, true));
                }
	    }
	}
	
	private void generateArrayEncodeMethod(CodeLines code, int arraySize, int pvIndex)
	{
            //For Encoding
            methodCode.clear();
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                methodCode.add(variableType + " " + tempVariableName + "(" + stringLength +");");
                methodCode.add("");
                methodCode.add("for (unsigned int i = 0; i < " + arraySize + "; i++)");
                methodCode.add("{");
                methodCode.add("\t" + tempVariableName + " = " + code.getNameSpace() + "::correctEndianness(" + variableName + "[i].c_str());");
                methodCode.add("\tmemcpy(bytes+pos, " + tempVariableName + ".c_str(), " + stringLength + ");");
                methodCode.add("\tpos += " + fixedLengthString.getStringLength() + ";");
                methodCode.add("}");
                if (fixedLengthString.isOptional())
                {
                    methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                }
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                methodCode.add("");
                methodCode.add("char[] " + tempVariableName + " = new char[" + stringLength + "];");
                methodCode.add("for (int j = 0; j < " + arraySize + "; j++)");
                methodCode.add("{");
                methodCode.add("\tif(" + variableName + "[j].length()> " + stringLength + ")");
                methodCode.add("\t{");
                methodCode.add("\t\t"+ variableName + "[j] = " + variableName + "[j].substring(0, " + stringLength + ");");
                methodCode.add("\t}");
                methodCode.add("\t" + tempVariableName + " = " + variableName + "[j].toCharArray();");
                methodCode.add("");
                JavaCode.encodeByteArrayLines(tempVariableName, tempVariableName + ".length", methodCode, true);
                methodCode.add("for(int i = " + tempVariableName  + ".length; i<" + stringLength + ";i++)");
                methodCode.add("{");
                methodCode.add("\tbytes.put(pos, (byte) 0);");
                methodCode.add("\tpos++;");
                methodCode.add("}");
                methodCode.add("");
                methodCode.add("}");
                if (fixedLengthString.isOptional())
                {
                    methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                }
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                methodCode.add("for (int i = 0; i < " + arraySize + "; i++)");
                methodCode.add("{");
                methodCode.add("");
                CSharpCode.generateEncoderLines(variableType, variableName + "[i]", methodCode, stringLength);                
                methodCode.add("}");
                if (fixedLengthString.isOptional())
                {
                    methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
                }
            }

            if (!code.encoderLines.isEmpty())
            {
                code.encoderLines.add("");
            }
            code.encoderLines.addAll(methodCode);
	}
	
	private void generateArrayDecodeMethod(CodeLines code, int arraySize, int pvIndex)
	{
        //For Decoding
        methodCode.clear();
        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            methodCode.add(variableTypeDecoding + " " + tempVariableName + "[" + stringLength +"];");
            methodCode.add("");
            methodCode.add("for (unsigned int i = 0; i < " + arraySize + "; i++)");
            methodCode.add("{");
            methodCode.add("\tmemcpy("+ tempVariableName + ", bytes+pos, " + stringLength + ");" );
            methodCode.add("\t" + variableName + "[i] = " + code.getNameSpace() + "::correctEndianness(" + tempVariableName + ");");
            methodCode.add("\tpos += " + stringLength + ";");
            methodCode.add("}");
            if (fixedLengthString.isOptional())
            {
        	methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            methodCode.add(variableTypeDecoding + " " + tempVariableName + " = new char[" + stringLength +"];");
            methodCode.add("");
            methodCode.add("for (int i = 0; i < " + arraySize + "; i++)");
            methodCode.add("{");
            methodCode.add("");
            methodCode.add("\tint j; byte by; int tmp = pos;");
            methodCode.add("\tfor(j=0; j<" + stringLength + "; j++)");
            methodCode.add("\t{");
            methodCode.add("\t\tby = bytes.get(tmp);");
            methodCode.add("\t\tif(by == 0)");
            methodCode.add("\t\t{");
            methodCode.add("\t\t\tbreak;");
            methodCode.add("\t\t}");
            methodCode.add("\t\t" + tempVariableName + "[j] = (char)(by &0xff);");
            methodCode.add("\t\ttmp++;");
            methodCode.add("\t}");
            methodCode.add("");
            methodCode.add("\tpos += " + stringLength + ";");
            methodCode.add("\t" + variableName + "[i] = new String(" + tempVariableName + ");");
            methodCode.add("\tif(j<" + stringLength + "){ " + variableName + "[i] = " + variableName + "[i].substring(0, j);}");
            methodCode.add("}");
            if (fixedLengthString.isOptional())
            {
        	methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            methodCode.add("for (int i = 0; i < " + arraySize + "; i++)");
            methodCode.add("{");
            CSharpCode.generateDecoderLines(variableType, variableName + "[i]", methodCode, stringLength);
            methodCode.add("}");
            if (fixedLengthString.isOptional())
            {
        	methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        
        if (!code.decoderLines.isEmpty())
        {
        	code.decoderLines.add("");
        }
        code.decoderLines.addAll(methodCode);
	}
	
	private void generateArrayGetMethod(CodeLines code, String posCalc, Vector<String> paramCode, Vector<String> getMethodCode)
	{
        //Generates the getMethod Declaration and Definition
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                code.publicMethods.add(CppCode.createMethodDeclaration(variableType, "get", fieldName, paramCode, false));

                methodCode.clear();
                methodCode.add("unsigned int index = " + posCalc + ";");
                methodCode.add("");
                methodCode.addAll(getMethodCode);
                methodCode.add("return " + variableName + "[index];");
                code.methods.addAll(CppCode.createMethodDefinition(variableType, fullClassName + "::get", fieldName, paramCode, methodCode, false));
            }
            else if (codeType == CodeLines.CodeType.JAVA)
            {
                methodCode.clear();
                methodCode.add("long index = " + posCalc + ";");
                methodCode.add("");
                methodCode.addAll(getMethodCode);
                methodCode.add("return " + variableName + "[(int) index];");
                code.methods.addAll(JavaCode.createMethodDefinition("public " + variableType, "get", fieldName, paramCode, methodCode, false));
                //Overload the get method for equality testing
                methodCode.clear();
                Vector<String> params = new Vector<String>();
                params.add("int index");
                methodCode.add("return " + variableName + "[index];");
                code.methods.addAll(JavaCode.createMethodDefinition("public " + variableType, "get", fieldName, params, methodCode, false));

            }
            else if (codeType == CodeLines.CodeType.C_SHARP)
            {
                methodCode.clear();
                methodCode.add("int index = " + posCalc + ";");
                methodCode.add("");
                methodCode.addAll(getMethodCode);
                methodCode.add("return " + variableName + "[index];");
                code.methods.addAll(CSharpCode.createMethodDefinition("public " + variableType, "get", fieldName, paramCode, methodCode, false));
            }
	}
	
	private void generateArraySetStringMethod(CodeLines code, String posCalc, int pvIndex, Vector<String> paramCode, Vector<String> setMethodCode)
	{
            //Generate SetMethod Declaration and Definition for a string
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                paramCode.add("std::string value");	//can I use this or does it need to be a char*
                code.publicMethods.add(CppCode.createMethodDeclaration("int", "set", fieldName, paramCode, false));

                //method code
                methodCode.clear();
                methodCode.add("unsigned int index = " + posCalc + ";");
                methodCode.add("");
                methodCode.addAll(setMethodCode);
                methodCode.add(""+ variableName +"[index] = value;");
                if (fixedLengthString.isOptional())
                {
                    methodCode.add("setPresenceVector("+ pvIndex +");");
                }
            	// Create framework for parent reference
            	methodCode.add(CppCode.getParentReferenceSetParentPVLine());
                methodCode.add("return 0;");
                code.methods.addAll(CppCode.createMethodDefinition("int", fullClassName + "::set", fieldName, paramCode, methodCode, false));
            }
            else if (codeType == CodeLines.CodeType.JAVA)
            {
                paramCode.add("String value");

                //method code
                methodCode.clear();
                methodCode.add("long index = " + posCalc + ";");
                methodCode.add("");
                methodCode.addAll(setMethodCode);
                methodCode.add(""+ variableName +"[(int)index] = value;");
                if (fixedLengthString.isOptional())
                {
                    methodCode.add("setPresenceVector("+ pvIndex +");");
                }
                // Create framework for parent reference
            	methodCode.add(JavaCode.getParentReferenceSetParentPVLine());
                code.methods.addAll(JavaCode.createMethodDefinition("public void", "set", fieldName, paramCode, methodCode, false));
            }
            else if (codeType == CodeLines.CodeType.C_SHARP)
            {
                paramCode.add("string value");

                //method code
                methodCode.clear();
                methodCode.add("int index = " + posCalc + ";");
                methodCode.add("");
                methodCode.addAll(setMethodCode);
                methodCode.add(""+ variableName +"[index] = value;");
                if (fixedLengthString.isOptional())
                {
                    methodCode.add("setPresenceVector("+ pvIndex +");");
                }
                // Create framework for parent reference
            	methodCode.add(CSharpCode.getParentReferenceSetParentPVLine());
                methodCode.add("return 0;");
                code.methods.addAll(CSharpCode.createMethodDefinition("public int", "set", fieldName, paramCode, methodCode, false));
            }
	}
	
	private void generateArraySetVariableMethod(CodeLines code, String posCalc, int pvIndex, Vector<String> paramCode, Vector<String> setMethodCode)
	{
            //Generate SetMethod Declaration and Definition for a jFixedLengthString
            paramCode.remove(paramCode.size() - 1);            
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                paramCode.add("jFixedLengthString value");
                code.publicMethods.add(CppCode.createMethodDeclaration("int", "set", fieldName, paramCode, false));

                //method code
                methodCode.clear();
                methodCode.add("unsigned int index = " + posCalc + ";");
                methodCode.add("");
                methodCode.addAll(setMethodCode);
                methodCode.add(""+ variableName +"[index] = value;");
                if (fixedLengthString.isOptional())
                {
                    methodCode.add("setPresenceVector("+ pvIndex +");");
                }

            	// Create framework for parent reference
            	methodCode.add(CppCode.getParentReferenceSetParentPVLine());
                methodCode.add("return 0;");
                code.methods.addAll(CppCode.createMethodDefinition("int", fullClassName + "::set", fieldName, paramCode, methodCode, false));
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                // this generates duplicate code since there is no jFixedLengthString typemap.
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                // this generates duplicate code since there is no jFixedLengthString typemap.
            }
	}
	
	private void generateArrayOverloadAssignmentMethod(CodeLines code, int arraySize)
	{
            /// Overload the operator=
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                code.assignmentLines.add("for (unsigned int i = 0; i < " + arraySize + "; i++)");
                code.assignmentLines.add("{");
                code.assignmentLines.add("\t" + variableName + "[i] = value." + variableName + "[i];");
                code.assignmentLines.add("}");
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                code.assignmentLines.add("for (int i = 0; i < " + arraySize + "; i++)");
                code.assignmentLines.add("{");
                code.assignmentLines.add("\t" + variableName + "[i] = value." + variableName + "[i];");
                code.assignmentLines.add("}");
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                code.assignmentLines.add("for (int i = 0; i < " + arraySize + "; i++)");
                code.assignmentLines.add("{");
                code.assignmentLines.add("\t" + variableName + "[i] = value." + variableName + "[i];");
                code.assignmentLines.add("}");
            }
	}
	
	private void generateArrayOverloadIsEqualMethod(CodeLines code, int arraySize)
	{
            /// Overload operator==
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                code.equalLines.add("for (unsigned int i = 0; i < " + arraySize + "; i++)");
                code.equalLines.add("{");
                code.equalLines.add("\tif (" + variableName + "[i] != value." + variableName + "[i])");
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                code.equalLines.add("for (int i = 0; i < " + arraySize + "; i++)");
                code.equalLines.add("{");
                code.equalLines.add("\tif (" + variableName + "[i].compareTo(value.get" + variableName.substring(variableName.indexOf(JavaCode.VARIABLE_PREFIX) + 2) + "(i))!=0)");
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                code.equalLines.add("for (int i = 0; i < " + arraySize + "; i++)");
                code.equalLines.add("{");
                code.equalLines.add("\tif (this." + variableName + "[i] != value." + variableName + "[i])");
            }

            code.equalLines.add("\t{");
            code.equalLines.add("\t\treturn false;");
            code.equalLines.add("\t}");
            code.equalLines.add("}");
            code.equalLines.add("return true;");
	}
	
	private void generateArrayOverloadNotEqualMethod(CodeLines code, int arraySize)
	{
            /// Overload operator!=
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                code.notEqualLines.add("for (unsigned int i = 0; i < " + arraySize + "; i++)");
                code.notEqualLines.add("{");
                code.notEqualLines.add("\tif (" + variableName + "[i] == value." + variableName + "[i])");
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                code.notEqualLines.add("for (int i = 0; i < " + arraySize + "; i++)");
                code.notEqualLines.add("{");
                code.notEqualLines.add("\tif (" + variableName + "[i].compareTo(value.get" + variableName.substring(variableName.indexOf(JavaCode.VARIABLE_PREFIX) + 2) + "(i)) == 0)");
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                code.notEqualLines.add("for (int i = 0; i < " + arraySize + "; i++)");
                code.notEqualLines.add("{");
                code.notEqualLines.add("\tif (this." + variableName + "[i] == value." + variableName + "[i])");
            }
            code.notEqualLines.add("\t{");
            code.notEqualLines.add("\t\treturn false;");
            code.notEqualLines.add("\t}");
            code.notEqualLines.add("}");
		code.notEqualLines.add("return true;");	
	}
    
}