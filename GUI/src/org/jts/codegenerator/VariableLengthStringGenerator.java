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
 * @(#)VariableLengthStringGenerator.java		0.1 2008/09/08
 * 
 * Copyright
 */

package org.jts.codegenerator;

import java.util.ArrayList;
import org.jts.jsidl.binding.*;

import java.util.List;

public class VariableLengthStringGenerator
{
	private CodeLines.CodeType codeType;
	private VariableLengthString vlString;
	private List<Dimension> dimList;
	
	// temp code generation variables
	private List<String> methodCode;
	private List<String> methodParam;
	private String fieldName;
	private String variableName;
	private String parentClassName;
	private String fullClassName;
	private String lengthType;
    private boolean lengthSigned;
	private String tempVariableName;
	private String variableType;
	private String variableTypeDecoding;
	private String minLength;
	private String maxLength;
	private String stringLength;
    private String dimSizeName;
	
	/**
	 * Constructor
	 * 
	 * @param codeType
	 * @param fixedLengthString
	 */
	public VariableLengthStringGenerator(CodeLines.CodeType codeType, VariableLengthString vlString)
	{
            this.codeType = codeType;
            this.vlString = vlString;
            this.dimList = null;

            // temp code generation variables
            methodCode = new ArrayList<String>();
            methodParam = new ArrayList<String>();
            fieldName = Util.upperCaseFirstLetter(vlString.getName());
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                variableName = CppCode.getVariableName(fieldName);
                lengthType = CppCode.getVariableType(vlString.getCountField().getFieldTypeUnsigned());
                tempVariableName = variableName + "Temp";
                variableType = "jVariableLengthString";
                variableTypeDecoding = "char";
                minLength = vlString.getCountField().getMinCount();
                maxLength = vlString.getCountField().getMaxCount();
                stringLength = variableName + ".length()";
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                variableName = JavaCode.getVariableName(fieldName);
                lengthType = JavaCode.getVariableType(vlString.getCountField().getFieldTypeUnsigned());
                lengthSigned = JavaCode.getVariableSign(vlString.getCountField().getFieldTypeUnsigned());
                
                tempVariableName = variableName + "Temp";
                variableType = "String";
                variableTypeDecoding = "char";
                minLength = vlString.getCountField().getMinCount();
                maxLength = vlString.getCountField().getMaxCount();
                stringLength = variableName + ".length()";
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                variableName = CSharpCode.getVariableName(fieldName);
                lengthType = CSharpCode.getVariableType(vlString.getCountField().getFieldTypeUnsigned());
                tempVariableName = variableName + "Temp";
                variableType = "string";
                variableTypeDecoding = "char";
                minLength = vlString.getCountField().getMinCount();
                maxLength = vlString.getCountField().getMaxCount();
                stringLength = variableName + ".Length";
            }
	}

	
	/**
	 * Constructor
	 * 
	 * @param codeType
	 * @param fixedLengthString
	 */
	public VariableLengthStringGenerator(CodeLines.CodeType codeType, VariableLengthString vlString, List<Dimension> dimList)
	{
            this.codeType = codeType;
            this.vlString = vlString;
            this.dimList = dimList;

            // temp code generation variables
            methodCode = new ArrayList<String>();
            methodParam = new ArrayList<String>();
            fieldName = Util.upperCaseFirstLetter(vlString.getName());
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                variableName = CppCode.getVariableName(fieldName);
                lengthType = CppCode.getVariableType(vlString.getCountField().getFieldTypeUnsigned());
                tempVariableName = variableName + "Temp";
                variableType = "jVariableLengthString";
                variableTypeDecoding = "char";
                minLength = vlString.getCountField().getMinCount();
                maxLength = vlString.getCountField().getMaxCount();
                stringLength = variableName + ".length()";
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                variableName = JavaCode.getVariableName(fieldName);
                lengthType = JavaCode.getVariableType(vlString.getCountField().getFieldTypeUnsigned());
                lengthSigned = JavaCode.getVariableSign(vlString.getCountField().getFieldTypeUnsigned());
                tempVariableName = variableName + "Temp";
                variableType = "String";
                variableTypeDecoding = "char";
                minLength = vlString.getCountField().getMinCount();
                maxLength = vlString.getCountField().getMaxCount();
                stringLength = variableName + ".length()";
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                variableName = CSharpCode.getVariableName(fieldName);
                lengthType = CSharpCode.getVariableType(vlString.getCountField().getFieldTypeUnsigned());
                tempVariableName = variableName + "Temp";
                variableType = "string";
                variableTypeDecoding = "char";
                minLength = vlString.getCountField().getMinCount();
                maxLength = vlString.getCountField().getMaxCount();
                stringLength = variableName + ".Length";
            }
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
	public void run(String parentName, int pvIndex, boolean hasClass, CodeLines code) throws CodeGeneratorException
	{
        if (hasClass)
        {
            // NOT IMPLEMENTED
            throw new CodeGeneratorException("Desired Output is Not Implemented");
        }

		CodeLines vlsCode = new CodeLines("", codeType, code.getNameSpace());
		
		if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
		{
		    fullClassName = parentName;
		    parentClassName = CppCode.getParentClassName(fullClassName);
		}
		else if (codeType == CodeLines.CodeType.JAVA)
		{
		    fullClassName = parentName;
		    parentClassName = JavaCode.getParentClassName(fullClassName);
		}
		else if (codeType == CodeLines.CodeType.C_SHARP)
		{
		    fullClassName = parentName;
		    parentClassName = CSharpCode.getParentClassName(fullClassName);
		}

        if (dimList == null)
        {
            generateFieldInstance(pvIndex, vlsCode);           
        }
        else
        {
        	generateFieldArray(pvIndex, vlsCode);
        }

        code.addAll(vlsCode);
	}
	
	/**
	 * Generate the field
	 * @param pvIndex
	 * @param code
	 * @throws CodeGeneratorException
	 */
	private void generateFieldInstance(int pvIndex, CodeLines code) throws CodeGeneratorException
	{
            // create variable
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                code.protectedAttributes.add(CppCode.createVariableDeclaration(variableType, fieldName, false));
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                code.protectedAttributes.add(JavaCode.createVariableDeclaration("protected " + variableType, fieldName, false));
                code.constructorLines.add(variableName + " = new " + variableType + "();");
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                code.protectedAttributes.add(CSharpCode.createVariableDeclaration("protected " + variableType, fieldName, true));
                code.constructorLines.add(variableName + " = \"\";");

            }

            // create methods for variable
            generateInstanceGetSizeMethod(code, pvIndex);

            generateInstanceIsValidMethod(code, pvIndex);

            generateInstanceEncodeMethod(code, pvIndex);
            generateInstanceDecodeMethod(code, pvIndex);

            generateInstanceGetMethod(code);
            generateInstanceSetMethod(code, pvIndex);

            generateInstanceOverloadAssignmentMethod(code);
            generateInstanceOverloadIsEqualMethod(code);
            generateInstanceOverloadNotEqualMethod(code);
	}
		
	private void generateInstanceGetSizeMethod(CodeLines code, int pvIndex)
	{
            methodCode.clear();

            // variable length string contains both an unsigned short length value
            // and the string data itself.
		
	    // GetSize method
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                methodCode.add("size += sizeof(" + lengthType + ");");
                methodCode.add("size += " + stringLength + ";");
    	
                /// only add size of array if optional is true
                if (vlString.isOptional())
                {
                    methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                }
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                JavaCode.generateGetSizeLines(lengthType, methodCode, lengthSigned);
                methodCode.add("size += " + stringLength + ";");

                /// only add size of array if optional is true
                if (vlString.isOptional())
                {
                    methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                }
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                methodCode.add("size += JausUtils.getNumBytes(\"" + lengthType + "\");");
                methodCode.add("size += " + stringLength + ";");

                /// only add size of array if optional is true
                if (vlString.isOptional())
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
	    if (vlString.isOptional()) 
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

            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                //For Encoding
                methodCode.add("");
                methodCode.add(lengthType + " " + variableName + "Length = " + stringLength + ";");
                methodCode.add(variableName + "Length = " + code.getNameSpace() + "::correctEndianness(" + variableName + "Length);");
                methodCode.add("memcpy(bytes+pos, (unsigned char*)&" + variableName + "Length, sizeof(" + lengthType + "));");
                methodCode.add("pos += sizeof(" + lengthType + ");");
                methodCode.add("");
                methodCode.add("memcpy(bytes+pos, " + variableName + ".c_str(), " + stringLength + ");");
                methodCode.add("pos += " + stringLength + ";");

                if (vlString.isOptional())
                {
                    methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                }
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                //For Encoding
                methodCode.add("");
                methodCode.add("try");
                methodCode.add("{");
                JavaCode.generateEncoderLines(lengthType, stringLength, methodCode, lengthSigned);
                methodCode.add("");
                JavaCode.generateEncoderLines(variableType, variableName, methodCode, stringLength);
                
                methodCode.add("}");
                methodCode.add("catch (Exception e)");
                methodCode.add("{");
                methodCode.add("\t");
                methodCode.add("}");
                if (vlString.isOptional())
                {
                    methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                }

            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                //For Encoding
                methodCode.add("");
                CSharpCode.generateEncoderLines(lengthType, stringLength, methodCode);
                methodCode.add("");
                CSharpCode.generateEncoderLines(variableType, variableName, methodCode, stringLength);
                if (vlString.isOptional())
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

            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                // For Decoding
                methodCode.add("");
                methodCode.add(lengthType + " " + variableName + "Length = 0;");
                methodCode.add("memcpy((unsigned char*)&" + variableName + "Length, bytes+pos, sizeof( " + variableName + "Length ));" );
                methodCode.add(variableName + "Length = " + code.getNameSpace() + "::correctEndianness(" + variableName + "Length);");
                methodCode.add("pos += sizeof( " + variableName + "Length );");
                methodCode.add("");
                methodCode.add(variableTypeDecoding + "* " + tempVariableName + " = new " + variableTypeDecoding + "[" + variableName + "Length+1];");
                methodCode.add("memcpy("+ tempVariableName + ", bytes+pos, " + variableName + "Length );" );
                methodCode.add(tempVariableName+"[" + variableName + "Length] = '\\0';");
                methodCode.add(variableName + " = " + tempVariableName + ";");
                methodCode.add("pos += " + variableName + "Length ;");
                methodCode.add("delete " + tempVariableName + ";");

                if (vlString.isOptional())
                {
                    methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                }
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                // For Decoding
                methodCode.add("");
                methodCode.add(lengthType + " " + variableName + "Length = 0;");

                JavaCode.generateDecoderLines(lengthType, variableName + "Length", methodCode, lengthSigned);
                methodCode.add("");
                JavaCode.generateDecoderLines(variableType, variableName, methodCode, variableName + "Length");
                if (vlString.isOptional())
                {
                    methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                }

            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                // For Decoding
                methodCode.add("");
                methodCode.add(lengthType + " " + variableName + "Length = 0;");
                CSharpCode.generateDecoderLines(lengthType, variableName + "Length", methodCode);
                methodCode.add("");
                CSharpCode.generateDecoderLines(variableType, variableName, methodCode, variableName + "Length");

                if (vlString.isOptional())
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

            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                // get declaration
                code.publicMethods.add(CppCode.createMethodDeclaration(variableType, "get", fieldName, null, false));

                // get method
                methodCode.add("return " + CppCode.getVariableName(fieldName) + ";");
                code.methods.addAll(CppCode.createMethodDefinition(variableType, fullClassName + "::get", fieldName, null, methodCode, false));
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                // get method
                methodCode.add("return " + JavaCode.getVariableName(fieldName) + ";");
                code.methods.addAll(JavaCode.createMethodDefinition("public " + variableType, "get", fieldName, null, methodCode, false));
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                // get method
                methodCode.add("return " + CSharpCode.getVariableName(fieldName) + ";");
                code.methods.addAll(CSharpCode.createMethodDefinition("public " + variableType, "get", fieldName, null, methodCode, false));
            }
        }
	
	private void generateInstanceSetMethod(CodeLines code, int pvIndex)
	{
            methodCode.clear();
            methodParam.clear();


            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                // set parameter
                methodParam.add("jVariableLengthString value");

                code.publicMethods.add(CppCode.createMethodDeclaration("int", "set", fieldName, methodParam, false));

                // set method
                methodCode.add("if ( value.length() > " + maxLength + ")");
                methodCode.add("{");
                methodCode.add("\treturn 1;");
                methodCode.add("}");
                methodCode.add("");
                methodCode.add(variableName + " = value;");
                methodCode.add("if (" + stringLength + " < " + minLength + ")");
                methodCode.add("{");
                methodCode.add("\t" + variableName + ".resize(" + minLength + ");");
                methodCode.add("}");

                if (vlString.isOptional())
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
                // set parameter
                methodParam.add("String value");

                // set method
                methodCode.add("if ( value.length() > " + maxLength + ")");
                methodCode.add("{");
                methodCode.add("\treturn;");
                methodCode.add("}");
                methodCode.add("");
                methodCode.add(variableName + " = value;");
                methodCode.add("if (" + stringLength + " < " + minLength + ")");
                methodCode.add("{");
                methodCode.add("\t" + variableName + " = " + variableName + ".substring(0, " + minLength + ");");
                methodCode.add("}");

                if (vlString.isOptional())
                {
                    methodCode.add("setPresenceVector("+ pvIndex +");");
                }
                // Create framework for parent reference
                methodCode.add(JavaCode.getParentReferenceSetParentPVLine());

                code.methods.addAll(JavaCode.createMethodDefinition("public void", "set", fieldName, methodParam, methodCode, false));
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                // set parameter
                methodParam.add("string value");
                
                // set method
                methodCode.add("if ( value.Length > " + maxLength + ")");
                methodCode.add("{");
                methodCode.add("\treturn;");
                methodCode.add("}");
                methodCode.add("");
                methodCode.add(variableName + " = value;");
                methodCode.add("if (" + stringLength + " < " + minLength + ")");
                methodCode.add("{");
                methodCode.add("\t//Resizing a stiring like this is not possible in C#.");
                //methodCode.add("\t" + variableName + " = " + variableName + ".Substring(0, " + minLength + ");");
                methodCode.add("}");

                if (vlString.isOptional())
                {
                    methodCode.add("setPresenceVector("+ pvIndex +");");
                }
                // Create framework for parent reference
                methodCode.add(CSharpCode.getParentReferenceSetParentPVLine());

                code.methods.addAll(CSharpCode.createMethodDefinition("public void", "set", fieldName, methodParam, methodCode, false));
            }
	}
	
	private void generateInstanceOverloadAssignmentMethod(CodeLines code)
	{
    	/// Overload the operator=
    	code.assignmentLines.add(variableName + " = value." + variableName + ";");
	}
	
	private void generateInstanceOverloadIsEqualMethod(CodeLines code)
	{
        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            /// Overload operator==
            code.equalLines.add("if ((" + stringLength + " != value." + variableName + ".length()) || (" + variableName + ".compare(value." + variableName + ") != 0))");
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            code.equalLines.add("if ((" + stringLength + " != value." + variableName + ".length()) || (" + variableName + ".compareTo(value." + variableName + ") != 0))");
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            /// Overload operator==
            code.equalLines.add("if ((" + stringLength + " != value." + variableName + ".Length) || (" + variableName + ".CompareTo(value." + variableName + ") != 0))");
        }
        code.equalLines.add("{");
        code.equalLines.add("\treturn false;");
        code.equalLines.add("}");

	}
	
	private void generateInstanceOverloadNotEqualMethod(CodeLines code)
	{
        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            /// Overload operator!=
            code.notEqualLines.add("if ((" + stringLength + " == value." + variableName + ".length()) && (" + variableName + ".compare(value." + variableName + ") == 0))");
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            /// Overload operator!=
            code.notEqualLines.add("if ((" + stringLength + " == value." + variableName + ".Length) && (" + variableName + ".CompareTo(value." + variableName + ") == 0))");
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            code.notEqualLines.add("if ((" + stringLength + " == value." + variableName + ".length()) && (" + variableName + ".compareTo(value." + variableName + ") == 0))");
        }
    	code.notEqualLines.add("{");
    	code.notEqualLines.add("\treturn false;");
    	code.notEqualLines.add("}");
	}
	
	/**
	 * Generate the array
	 * @param pvIndex
	 * @param code
	 * @throws CodeGeneratorException
	 */
    private void generateFieldArray(int pvIndex, CodeLines code) throws CodeGeneratorException {
        List<String> getMethodCode = new ArrayList<String>();
        List<String> setMethodCode = new ArrayList<String>();

        // code will fill this with the common dimension parameters used by the array
        // getter and setter methods.
        List<String> getterSetterParamsList = new ArrayList<String>();

        String posCalc = "";	// The calculation for the position within an 1-dimension array from the n-dimension array
        String prevCalc = "";	// The previous calculation for the position within an 1-dimension array from the n-dimension array
        int arraySize = 1;

        methodParam.clear();
        for (int i = 0; i < dimList.size(); i++) {
            Dimension dim = dimList.get(i);

            String dimName = Util.upperCaseFirstLetter(dim.getName());
            dimSizeName = dimName + "Size";
            if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
                String dimVarName = CppCode.getVariableName(dimSizeName);

                code.protectedAttributes.add(CppCode.createVariableDeclaration("unsigned int", dimSizeName, false));
                code.constructorLines.add(dimVarName + " = " + dim.getSize() + ";");

                ///  Generate getMethod Declaration and Definition
                code.publicMethods.add(CppCode.createMethodDeclaration("const unsigned int", "get", dimSizeName, null, true));

                methodCode.clear();
                methodCode.add("return " + dimVarName + ";");
                code.methods.addAll(CppCode.createMethodDefinition("const unsigned int", fullClassName + "::get", dimSizeName, null, methodCode, true));

                /// Add the dimension to the method parameter vector for the get and set methods
                methodParam.add("unsigned int " + dimName);

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

                getterSetterParamsList.add("int " + dimName);
            } else if (codeType == CodeLines.CodeType.JAVA) {
                String dimVarName = JavaCode.getVariableName(dimSizeName);

                code.protectedAttributes.add(JavaCode.createVariableDeclaration("protected long", dimSizeName, false));
                code.constructorLines.add(dimVarName + " = " + dim.getSize() + ";");

                ///  Generate getMethod Definition
                methodCode.clear();
                methodCode.add("return " + dimVarName + ";");
                code.methods.addAll(JavaCode.createMethodDefinition("public long", "get", dimSizeName, null, methodCode, true));

                /// Add the dimension to the method parameter vector for the get and set methods
                methodParam.add("long " + dimName);

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

                getterSetterParamsList.add("int " + dimName);
            } else if (codeType == CodeLines.CodeType.C_SHARP) {
                String dimVarName = CSharpCode.getVariableName(dimSizeName);

                code.protectedAttributes.add(CSharpCode.createVariableDeclaration("int", dimSizeName, true));
                code.constructorLines.add(dimVarName + " = " + dim.getSize() + ";");

                ///  Generate getMethod Definition
                methodCode.clear();
                methodCode.add("return " + dimVarName + ";");
                code.methods.addAll(CSharpCode.createMethodDefinition("public int", "get", dimSizeName, null, methodCode, true));

                /// Add the dimension to the method parameter vector for the get and set methods
                methodParam.add("int " + dimName);

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

                getterSetterParamsList.add("int " + dimName);
            }


            int dimSize = Integer.parseInt(dim.getSize());
            arraySize = arraySize * dimSize;

            if (i > 0) {
                prevCalc += " * ";
                posCalc += " + (" + prevCalc + dimName + ")";
            } else {
                posCalc = dimName;
            }

            prevCalc += dim.getSize();
        }

        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            //create variable
            code.protectedAttributes.add(CppCode.createVariableDeclaration(variableType, fieldName + "[" + arraySize + "]", false));
            code.constructorLines.add("for (unsigned int i = 0; i < " + arraySize + "; i++)");
            code.constructorLines.add("{");
            code.constructorLines.add("/// Set the size of all the members of the array");
            code.constructorLines.add("\t" + variableName + "[i] == \"\";");
            code.constructorLines.add("}");
        } else if (codeType == CodeLines.CodeType.JAVA) {
            //create variable
            code.protectedAttributes.add(JavaCode.createVariableDeclaration("protected " + variableType + "[]", fieldName + " = new " + variableType + "[" + arraySize + "]", false));
            code.constructorLines.add("for (int i = 0; i < " + arraySize + "; i++)");
            code.constructorLines.add("{");
            code.constructorLines.add("/// Set the size of all the members of the array");
            code.constructorLines.add("\t" + variableName + "[i] = new " + variableType + "();");//.setSize(" + stringLength + ");");
            code.constructorLines.add("}");
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            //create variable
            code.protectedAttributes.add(CSharpCode.createVariableDeclaration("protected " + variableType + "[]", fieldName + " = new " + variableType + "[" + arraySize + "]", true));
            code.constructorLines.add("for (int i = 0; i < " + arraySize + "; i++)");
            code.constructorLines.add("{");
            code.constructorLines.add("/// Set the size of all the members of the array");
            code.constructorLines.add("\t" + variableName + "[i] = \"\";");
            code.constructorLines.add("}");
        }

        generateArrayGetSizeMethod(code, pvIndex, arraySize);

        generateArrayIsValidMethod(code, pvIndex);

        generateArrayEncodeMethod(code, arraySize, pvIndex);
        generateArrayDecodeMethod(code, arraySize, pvIndex);

        generateArrayGetMethod(code, posCalc, getMethodCode, getterSetterParamsList);
        generateArraySetMethod(code, posCalc, setMethodCode, getterSetterParamsList, pvIndex);

        generateArrayOverloadIsEqualMethod(code, arraySize);
        generateArrayOverloadNotEqualMethod(code, arraySize);
        generateArrayOverloadAssignmentMethod(code, arraySize);
    }
	
	private void generateArrayGetSizeMethod(CodeLines code, int pvIndex, int arraySize)
	{
            methodCode.clear();
		
	    // GetSize method
            if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
                methodCode.add("for (int i = 0; i < " + arraySize + "; ++i) ");
                methodCode.add("{");
                methodCode.add("\tsize += sizeof(" + lengthType + ");");
                methodCode.add("\tsize += " + variableName + "[i].size();");
            } else if (codeType == CodeLines.CodeType.JAVA) {
                methodCode.add("for (int i = 0; i < " + variableName + ".length; ++i) ");
                methodCode.add("{");
                JavaCode.generateGetSizeLines(lengthType, methodCode, lengthSigned);
                methodCode.add("\tsize += " + variableName + "[i].length();");
            } else if (codeType == CodeLines.CodeType.C_SHARP) {
                methodCode.add("for (int i = 0; i < " + variableName + ".Length; ++i) ");
                methodCode.add("{");
                methodCode.add("\tsize += JausUtils.getNumBytes(\"" + lengthType + "\");");
                methodCode.add("\tsize += " + variableName + "[i].Length;");
            }
            methodCode.add("}");
	    
	    // only add size of array if optional is true
	    if (vlString.isOptional()) 
	    {
                if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
                {
                    methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                }
                else if(codeType == CodeLines.CodeType.JAVA)
                {
                    methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                }
                else if(codeType == CodeLines.CodeType.C_SHARP)
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
	    if (vlString.isOptional()) 
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
	
    private void generateArrayEncodeMethod(CodeLines code, int arraySize, int pvIndex) {
        methodCode.clear();

        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            //For Encoding
            methodCode.add("int countfieldSize = sizeof(" + lengthType + ");");

            methodCode.add("for (int i = 0; i < " + arraySize + "; ++i) ");
            methodCode.add("{");
            methodCode.add("\tint strlen = " + variableName + "[i].length();");
            methodCode.add("\tmemcpy(bytes+pos, (unsigned char*)&strlen, countfieldSize);");
            methodCode.add("\tpos += countfieldSize;");
            methodCode.add("\tmemcpy(bytes+pos, " + variableName + "[i].c_str(), " + variableName + "[i].length());");
            methodCode.add("\tpos += " + variableName + "[i].length();");
            methodCode.add("");
            methodCode.add("}");


            if (vlString.isOptional()) {
                methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
            }
        } else if (codeType == CodeLines.CodeType.JAVA) {
            //For Encoding
            methodCode.add("");
            methodCode.add("try");
            methodCode.add("{");
            methodCode.add("");
            methodCode.add("for(int j = 0; j< (int) " + arraySize + "; j++)");
            methodCode.add("{");
            JavaCode.generateEncoderLines("int", variableName + "[j].length()", methodCode, true);
            methodCode.add("");
            methodCode.add("\tchar[] tmp = " + variableName + "[j].toCharArray();");
            JavaCode.encodeByteArrayLines("tmp", variableName + "[j].length()", methodCode, true);
            methodCode.add("}");
            methodCode.add("}");
            methodCode.add("catch (Exception e) {");
            methodCode.add("\tlogger.log(Level.SEVERE, null, e);");
            methodCode.add("}");

            if (vlString.isOptional()) {
                methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
            }
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            //For Encoding
            methodCode.add("");
            methodCode.add("for(int j = 0; j < (int) " + arraySize + "; j++)");
            methodCode.add("{");
            methodCode.add("\tint tmp = 0;");
            CSharpCode.generateEncoderLines(lengthType, variableName + "[j].Length", methodCode);
            methodCode.add("");
            CSharpCode.generateEncoderLines(variableType, variableName + "[j]", methodCode, variableName + "[j].Length");
            methodCode.add("}");

            if (vlString.isOptional()) {
                methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }

        if (!code.encoderLines.isEmpty()) {
            code.encoderLines.add("");
        }
        code.encoderLines.addAll(methodCode);
    }
		
	private void generateArrayDecodeMethod(CodeLines code, int arraySize, int pvIndex)
	{
            methodCode.clear();

            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                // jVariableLengthString
                code.decoderLines.add("int countfield = 0;");
                code.decoderLines.add("for (int i = 0; i < " + arraySize + "; ++i)");
                code.decoderLines.add("{");
                // get the length data
                code.decoderLines.add("\tmemcpy(&countfield, bytes+pos, sizeof(" + lengthType + "));");
                code.decoderLines.add("\tcountfield = " + code.getNameSpace() + "::correctEndianness(countfield);");
                code.decoderLines.add("\tpos += sizeof(" + lengthType + ");");
                code.decoderLines.add("");
                code.decoderLines.add("\tchar string_data[countfield+1];");
                code.decoderLines.add("\tmemcpy(string_data, bytes+pos, countfield);");
                code.decoderLines.add("\tstring_data[countfield] = 0;");
                code.decoderLines.add("\tpos += countfield;");
                code.decoderLines.add("\tstd::string tmp_string(string_data);");
                code.decoderLines.add("");
                code.decoderLines.add("\t" + variableName + "[i] = tmp_string;");
                code.decoderLines.add("}");

                if (vlString.isOptional())
                {
                    methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                }
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                //For Decoding
                methodCode.add("");
                methodCode.add("try");
                methodCode.add("{");
                methodCode.add("");

                methodCode.add("for(int j = 0; j<(int) get" + dimSizeName +"(); j++)");
                methodCode.add("{");
                methodCode.add("int strLen = 0;");
                JavaCode.generateDecoderLines("int", "strLen", methodCode, true);
                methodCode.add("");
                methodCode.add("char[] tmp = new char[strLen];");
                methodCode.add("");
                JavaCode.decodeByteArrayLines("tmp", "strLen", methodCode, true);
                methodCode.add(variableName + "[j] = new String(tmp);");
                methodCode.add("}");
                methodCode.add("}");
                methodCode.add("catch (Exception e)");
                methodCode.add("{");
                methodCode.add("\tlogger.log(Level.SEVERE, null, e);");
                methodCode.add("}");

                if (vlString.isOptional())
                {
                    methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                }
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                //For Decoding
                methodCode.add("");

                methodCode.add("for(int j = 0; j<(int) get" + dimSizeName +"(); j++)");
                methodCode.add("{");
                methodCode.add("int strLen = 0;");
                CSharpCode.generateDecoderLines(lengthType, "strLen", methodCode);
                methodCode.add("");
                CSharpCode.generateDecoderLines(variableType, variableName + "[j]", methodCode, "strLen");
                methodCode.add("}");
                if (vlString.isOptional())
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

    /**
     * Generates a method for setting a single value in the array.
     * @param code
     * @param posCalcExpr string for computing position in the underlying string
     *     array, based on array dimensions.
     * @param rangeCheckCode code for checking index parameter range validity
     * @param paramsList list of Strings containing the array dimension variable names
     */
    private void generateArrayGetMethod(CodeLines code, String posCalcExpr,
            List<String> rangeCheckCode, List<String> paramsList) {
        methodCode.clear();

        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            //Generates the getMethod Declaration and Definition
            code.publicMethods.add(CppCode.createMethodDeclaration(variableType, "get", fieldName, paramsList, false));
            methodCode.addAll(rangeCheckCode);
            methodCode.add("int index = " + posCalcExpr + ";");
            methodCode.add("return " + variableName + "[index];");
            code.methods.addAll(CppCode.createMethodDefinition(variableType, fullClassName + "::get", fieldName, paramsList, methodCode, false));
        } else if (codeType == CodeLines.CodeType.JAVA) {
            //Generates the getMethod Definition
            methodCode.addAll(rangeCheckCode);
            methodCode.add("int index = " + posCalcExpr + ";");
            methodCode.add("return " + variableName + "[index];");
            code.methods.addAll(JavaCode.createMethodDefinition("public " + variableType, "get", fieldName, paramsList, methodCode, false));
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            //Generates the getMethod Definition
            methodCode.addAll(rangeCheckCode);
            methodCode.add("int index = " + posCalcExpr + ";");
            methodCode.add("return " + variableName + "[index];");
            code.methods.addAll(CSharpCode.createMethodDefinition("public " + variableType, "get", fieldName, paramsList, methodCode, false));
        }
    }

    /**
     * Generates a method for setting a single value in the array.
     * @param code
     * @param posCalcExpr string for computing position in the underlying string
     *     array, based on array dimensions.
     * @param rangeCheckCode code for checking index parameter range validity
     * @param paramsList list of Strings containing the array dimension variable names
     */
    private void generateArraySetMethod(CodeLines code, String posCalcExpr,
            List<String> rangeCheckCode, List<String> paramsList, int pvIndex) {
        methodCode.clear();

        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {

            //method code
            methodCode.addAll(rangeCheckCode);

            methodCode.add("unsigned int index = " + posCalcExpr + ";");
            methodCode.add("");
            methodCode.add(variableName + "[index] = value;");

            if (vlString.isOptional()) {
                methodCode.add("setPresenceVector(" + pvIndex + ");");
            }

            // Create framework for parent reference
            methodCode.add(CppCode.getParentReferenceSetParentPVLine());
            methodCode.add("return 0;");

            paramsList.add("const jVariableLengthString& value");
            code.publicMethods.add(CppCode.createMethodDeclaration("int", "set", fieldName, paramsList, false));
            code.methods.addAll(CppCode.createMethodDefinition("int", fullClassName + "::set", fieldName, paramsList, methodCode, false));
        } else if (codeType == CodeLines.CodeType.JAVA) {
            //method code
            methodCode.addAll(rangeCheckCode);

            methodCode.add("int index = " + posCalcExpr + ";");

            methodCode.add(variableName + "[index] = value;");
            if (vlString.isOptional()) {
                methodCode.add("setPresenceVector(" + pvIndex + ");");
            }
            // Create framework for parent reference
            methodCode.add(JavaCode.getParentReferenceSetParentPVLine());
            methodCode.add("return 0;");

            //set parameter
            paramsList.add("String value");
            code.methods.addAll(JavaCode.createMethodDefinition("public int", "set", fieldName, paramsList, methodCode, false));
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            //method code
            methodCode.addAll(rangeCheckCode);

            methodCode.add("int index = " + posCalcExpr + ";");

            methodCode.add(variableName + "[index] = value;");
            if (vlString.isOptional()) {
                methodCode.add("setPresenceVector(" + pvIndex + ");");
            }
            // Create framework for parent reference
            methodCode.add(CSharpCode.getParentReferenceSetParentPVLine());
            methodCode.add("return 0;");

            //set parameter
            paramsList.add("string value");
            code.methods.addAll(CSharpCode.createMethodDefinition("public int", "set", fieldName, paramsList, methodCode, false));
        }
    }
		
    private void generateArrayOverloadIsEqualMethod(CodeLines code, int arraySize) {
        /// Overload operator==
        code.equalLines.add("for (int i = 0; i < " + arraySize + "; ++i)");
        code.equalLines.add("{");

        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            code.equalLines.add("\tif ((this->" + variableName + "[i].length() != value." + variableName + "[i].length()) "
                    + "|| (this->" + variableName + "[i].compare(value." + variableName + "[i]) != 0))");
        } else if (codeType == CodeLines.CodeType.JAVA) {
            code.equalLines.add("\tif ((this." + variableName + "[i].length() != value." + variableName + "[i].length()) "
                    + "|| (this." + variableName + "[i].compareTo(value." + variableName + "[i]) != 0))");
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            code.equalLines.add("\tif ((this." + variableName + "[i].Length != value." + variableName + "[i].Length) "
                    + "|| (this." + variableName + "[i].CompareTo(value." + variableName + "[i]) != 0))");
        }
        code.equalLines.add("\t{");
        code.equalLines.add("\t\treturn false;");
        code.equalLines.add("\t}");
        code.equalLines.add("}");
        code.equalLines.add("");
        code.equalLines.add("return true;");
    }
		
    private void generateArrayOverloadNotEqualMethod(CodeLines code, int arraySize) {
        /// define in terms of method added in generateArrayOverloadIsEqualMethod
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            code.notEqualLines.add("if (this->operator==(value))");
        } else if (codeType == CodeLines.CodeType.JAVA) {
            code.notEqualLines.add("if (this.isEqual(value))");
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            code.notEqualLines.add("if (this.isEqual(value))");
        }
        code.notEqualLines.add("{");
        code.notEqualLines.add("\treturn false;");
        code.notEqualLines.add("}");
        code.notEqualLines.add("");
        code.notEqualLines.add("return true;");
    }

    private void generateArrayOverloadAssignmentMethod(CodeLines code, int arraySize) {
        /// Overload the operator=
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            code.assignmentLines.add("for (unsigned int i = 0; i < " + arraySize + "; i++)");
            code.assignmentLines.add("{");
            code.assignmentLines.add("\t" + variableName + "[i] = value." + variableName + "[i];");
            code.assignmentLines.add("}");
        } else if (codeType == CodeLines.CodeType.JAVA) {
            // implement assignment function
            code.assignmentLines.add("for (int i = 0; i < " + arraySize + "; i++)");
            code.assignmentLines.add("{");
            code.assignmentLines.add("\t" + variableName + "[i] = value." + variableName + "[i];");
            code.assignmentLines.add("}");
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            // implement assignment function
            code.assignmentLines.add("for (int i = 0; i < " + arraySize + "; i++)");
            code.assignmentLines.add("{");
            code.assignmentLines.add("\t" + variableName + "[i] = value." + variableName + "[i];");
            code.assignmentLines.add("}");
        }
    }
}