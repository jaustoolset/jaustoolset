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
 * @(#)VariableFormatFieldGenerator.java		0.1 2008/10/08
 * 
 * Copyright
 */
package org.jts.codegenerator;


import java.util.ArrayList;
import java.util.List;
import org.jts.jsidl.binding.Dimension;
import org.jts.jsidl.binding.FormatEnum;
import org.jts.jsidl.binding.VariableFormatField;

/**
 * This class generates the code needed for a Variable Format Field JAXB Object
 * 
 * @author Nicholas M. Johnson
 * @author Gina Nearing
 *
 */
public class VariableFormatFieldGenerator
{
	private CodeLines.CodeType codeType;
	private VariableFormatField vfField;
	private List<Dimension> dimList;
	
	private List<String> methodCode;
	private List<String> methodParam;
	private String formatVarType;
	private String formatVarName;
    private boolean formatVarSigned = false;
	private String tempVarName;
	private String fullClassName;
	private String lengthType;
	private String lengthVarName;
    private boolean lengthSigned;
	private String dataVarName;
	private String shortClassName;
	private String parentClassName;
	private String varName;

        /*
         * @param codeType for generating language specific syntax
         * @param vfField
         */
	public VariableFormatFieldGenerator(CodeLines.CodeType codeType, VariableFormatField vfField)
	{
		this.codeType = codeType;
		this.vfField = vfField;
		this.dimList = null;
		
		methodCode = new ArrayList<String>();
		methodParam = new ArrayList<String>();
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            formatVarType = "jUnsignedByte";
            formatVarName = CppCode.getVariableName("format");
            lengthType = CppCode.getVariableType(vfField.getCountField().getFieldTypeUnsigned());
            lengthVarName = CppCode.getVariableName("length");
            dataVarName = CppCode.getVariableName("data");
        } else if (codeType == CodeLines.CodeType.JAVA) {
            formatVarType = "short";
            formatVarSigned = true; // must use a short to accomodate an unsigned byte in java.
            formatVarName = JavaCode.getVariableName("format");
            lengthType = JavaCode.getVariableType(vfField.getCountField().getFieldTypeUnsigned());
            lengthSigned = JavaCode.getVariableSign(vfField.getCountField().getFieldTypeUnsigned());
            lengthVarName = JavaCode.getVariableName("length");
            dataVarName = JavaCode.getVariableName("data");
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            formatVarType = "byte";
            formatVarName = CSharpCode.getVariableName("format");
            lengthType = CSharpCode.getVariableType(vfField.getCountField().getFieldTypeUnsigned());
            lengthVarName = CSharpCode.getVariableName("length");
            dataVarName = CSharpCode.getVariableName("data");
        }
	}
	
	public VariableFormatFieldGenerator(CodeLines.CodeType codeType, VariableFormatField vfField, List<Dimension> dimList)
	{
		this.codeType = codeType;
		this.vfField = vfField;
		this.dimList = dimList;
		
		methodCode = new ArrayList<String>();
		methodParam = new ArrayList<String>();
		

        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            formatVarType = "jUnsignedByte";
            formatVarName = CppCode.getVariableName("format");
            tempVarName = formatVarName + "Temp";
            lengthType = CppCode.getVariableType(vfField.getCountField().getFieldTypeUnsigned());
            lengthVarName = CppCode.getVariableName("length");
            dataVarName = CppCode.getVariableName("data");
        } else if (codeType == CodeLines.CodeType.JAVA) {
            formatVarType = "short";
            formatVarSigned = true; // must use a short to accomodate an unsigned byte in java.
            formatVarName = JavaCode.getVariableName("format");
            tempVarName = formatVarName + "Temp";
            lengthType = JavaCode.getVariableType(vfField.getCountField().getFieldTypeUnsigned());
            lengthSigned = JavaCode.getVariableSign(vfField.getCountField().getFieldTypeUnsigned());
            lengthVarName = JavaCode.getVariableName("length");
            dataVarName = JavaCode.getVariableName("data");
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            formatVarType = "byte";
            formatVarName = CSharpCode.getVariableName("format");
            tempVarName = formatVarName + "Temp";
            lengthType = CSharpCode.getVariableType(vfField.getCountField().getFieldTypeUnsigned());
            lengthVarName = CSharpCode.getVariableName("length");
            dataVarName = CSharpCode.getVariableName("data");
        }
    }

	
	public void run(String parentName, int pvIndex, boolean isNested, CodeLines code) throws CodeGeneratorException
	{
		if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
		{
		        fullClassName = parentName + "::" + vfField.getName();
		        shortClassName = CppCode.getShortClassName(fullClassName);
		        parentClassName = CppCode.getParentClassName(fullClassName);
		        varName = CppCode.getVariableName(shortClassName);
		    
		    /*
		     * Create framework for parent reference
		     */
		   	CppCode.addParentReference(code, fullClassName, pvIndex, vfField.isOptional());
		}
		else if (codeType == CodeLines.CodeType.JAVA)
		{
		    fullClassName = parentName + "." + vfField.getName();
		    shortClassName = JavaCode.getShortClassName(fullClassName);
		    parentClassName = JavaCode.getParentClassName(fullClassName);
		    varName = JavaCode.getVariableName(shortClassName);
            /*
		     * Create framework for parent reference
		     */
		   	JavaCode.addParentReference(code, fullClassName, pvIndex, vfField.isOptional());
		}
		else if (codeType == CodeLines.CodeType.C_SHARP)
		{
		    fullClassName = parentName + "." + vfField.getName();
		    shortClassName = CSharpCode.getShortClassName(fullClassName);
		    parentClassName = CSharpCode.getParentClassName(fullClassName);
		    varName = CSharpCode.getVariableName(shortClassName);
            /*
		     * Create framework for parent reference
		     */
		   	CSharpCode.addParentReference(code, fullClassName, pvIndex, vfField.isOptional());
		}
		
		CodeLines vfCode = new CodeLines("", codeType, code.getNameSpace());
		
		generateFieldCode(pvIndex, vfCode);
		code.addAll(vfCode);
		
		if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
		{
			CppCode.addClassWrapper(fullClassName, code);
		}
		else if (codeType == CodeLines.CodeType.JAVA)
		{
			JavaCode.addClassWrapper(fullClassName, code);
		}
		else if (codeType == CodeLines.CodeType.C_SHARP)
		{
			CSharpCode.addClassWrapper(fullClassName, code);
		}
		
		if (dimList == null)
		{
		    generateSingleInstance(pvIndex, code);
		}
		else
		{
			generateArray(pvIndex, code);
		}
	}
	
	/**
	 * Generate field
	 * @param pvIndex
	 * @param code
	 * @throws CodeGeneratorException
	 */
	private void generateFieldCode(int pvIndex, CodeLines code) throws CodeGeneratorException
	{
            // create variables

            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                // field format
                code.protectedAttributes.add(CppCode.createVariableDeclaration(formatVarType, "format", false));
                /// field length
                code.protectedAttributes.add(CppCode.createVariableDeclaration(lengthType, "length", false));
                code.constructorLines.add(lengthVarName + " = 0;");
                /// field data
                code.protectedAttributes.add(CppCode.createVariableDeclaration("unsigned char", "data", true));
                code.constructorLines.add(dataVarName + " = NULL;");
                code.destructorLines.add("delete[] " + dataVarName + ";");
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                // field format
                code.protectedAttributes.add(JavaCode.createVariableDeclaration("protected " + formatVarType, "format", false));
                /// field length
                code.protectedAttributes.add(JavaCode.createVariableDeclaration("protected " + lengthType, "length", false));
                code.constructorLines.add(lengthVarName + " = MAX_JTS_MESSAGE_SIZE;");
                /// field data
                code.protectedAttributes.add(JavaCode.createVariableDeclaration("protected ByteBuffer", "data", true));
                code.constructorLines.add(dataVarName + " = ByteBuffer.allocate((int)" + lengthVarName + ");");
                code.constructorLines.add(dataVarName + ".order(ByteOrder.LITTLE_ENDIAN);");
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                // field format
                code.protectedAttributes.add(CSharpCode.createVariableDeclaration(formatVarType, "format", true));
                /// field length
                code.protectedAttributes.add(CSharpCode.createVariableDeclaration(lengthType, "length", true));
                code.constructorLines.add(lengthVarName + " = 0;");
                /// field data
                code.protectedAttributes.add(CSharpCode.createVariableDeclaration("byte[]", "data", true));
                code.constructorLines.add(dataVarName + " = new byte[" + lengthVarName + "];");
            }
        
            StringBuffer validFormatEnum = new StringBuffer();
            /// Get the format enum to validate the input value
            for(int i = 0; i < vfField.getFormatField().getFormatEnum().size(); i++)
            {
                FormatEnum formatEnum = vfField.getFormatField().getFormatEnum().get(i);
                String enumString = "(format == " + formatEnum.getIndex() + ")";
                if (i == 0)
                {
                    code.constructorLines.add(formatVarName + " = " + formatEnum.getIndex() + ";");
                }
                else
                {
                    validFormatEnum.append("||");
                }
                validFormatEnum.append(enumString);
            }
        
            generateFieldGetSizeMethod(code);

            generateFieldEncodeMethod(code);
            generateFieldDecodeMethod(code);

            generateFieldGetFormatMethod(code);
            generateFieldGetLengthMethod(code);
            generateFieldGetDataMethod(code);
            generateFieldSetMethod(code, validFormatEnum);

            generateFieldOverloadAssignmentMethod(code);
            generateFieldOverloadIsEqualMethod(code);
            generateFieldOverloadNotEqualMethod(code);
	}
	
	private void generateFieldGetSizeMethod(CodeLines code)
	{
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                /// Add the code for the field format
                code.sizeLines.add("size += sizeof(" + formatVarType + ");");

                /// Add the code for the field length
                code.sizeLines.add("size += sizeof(" + lengthType + ");");

                /// Add the code for the field length
                code.sizeLines.add("size += " + lengthVarName + ";");
            }
            else if (codeType == CodeLines.CodeType.JAVA)
            {
                /// Add the code for the field format
                JavaCode.generateGetSizeLines(formatVarType, code.sizeLines, formatVarSigned);

                /// Add the code for the field length
                JavaCode.generateGetSizeLines(lengthType, code.sizeLines, lengthSigned);

                /// Add the code for the field length
                code.sizeLines.add("size += " + lengthVarName + ";");
            }
            else if (codeType == CodeLines.CodeType.C_SHARP)
            {
                /// Add the code for the field format
                code.sizeLines.add("size += JausUtils.getNumBytes(\"" + formatVarType + "\");");

                /// Add the code for the field length
                code.sizeLines.add("size += JausUtils.getNumBytes(\"" + lengthType + "\");");

                /// Add the code for the field length
                code.sizeLines.add("size += (int)" + lengthVarName + ";");
            }
	}
	
    private void generateFieldEncodeMethod(CodeLines code) {

        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            /// Add the code for the field format
            tempVarName = formatVarName + "Temp";
            code.encoderLines.add(formatVarType + " " + tempVarName + ";");
            code.encoderLines.add("");

            code.encoderLines.add(tempVarName + " = " + code.getNameSpace() + "::correctEndianness(" + formatVarName + ");");
            code.encoderLines.add("memcpy(bytes+pos, &" + tempVarName + ", sizeof(" + formatVarType + "));");
            code.encoderLines.add("pos += sizeof(" + formatVarType + ");");

            /// Add the code for the field length
            tempVarName = lengthVarName + "Temp";
            code.encoderLines.add("");
            code.encoderLines.add(lengthType + " " + tempVarName + ";");
            code.encoderLines.add("");
            code.encoderLines.add(tempVarName + " = " + code.getNameSpace() + "::correctEndianness(" + lengthVarName + ");");
            code.encoderLines.add("memcpy(bytes+pos, &" + tempVarName + ", sizeof(" + lengthType + "));");
            code.encoderLines.add("pos += sizeof(" + lengthType + ");");

            /// Add the code for the field length
            code.encoderLines.add("");
            code.encoderLines.add("memcpy(bytes+pos, " + dataVarName + ", " + lengthVarName + ");");
            code.encoderLines.add("pos += " + lengthVarName + ";");
        } else if (codeType == CodeLines.CodeType.JAVA) {
            JavaCode.generateEncoderLines(formatVarType, formatVarName, code.encoderLines, formatVarSigned);

            /// Add the code for the field length
            code.encoderLines.add("");
            JavaCode.generateEncoderLines(lengthType, lengthVarName, code.encoderLines, lengthSigned);

            /// Add the code for the field length
            code.encoderLines.add("");
            code.encoderLines.add("byte[] dataBytes = " + dataVarName + ".array();");
            JavaCode.encodeByteArrayLines("dataBytes", "(int) " + lengthVarName, code.encoderLines, false);

        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            CSharpCode.generateEncoderLines(formatVarType, formatVarName, code.encoderLines);

            /// Add the code for the field length
            code.encoderLines.add("");
            CSharpCode.generateEncoderLines(lengthType, lengthVarName, code.encoderLines);

            /// Add the code for the field length
            code.encoderLines.add("");
            code.encoderLines.add("bytes = JausUtils.addToBuffer(bytes, " + dataVarName + ", pos, (int)" + lengthVarName + ", true);");
            code.encoderLines.add("pos += (int)" + lengthVarName + ";");
        }
    }
	
	private void generateFieldDecodeMethod(CodeLines code)
	{
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                /// Add the code for the field format
                tempVarName = formatVarName + "Temp";
                code.decoderLines.add(formatVarType + " " + tempVarName + ";");
                code.decoderLines.add("");
                code.decoderLines.add("memcpy(&" + tempVarName + ", bytes+pos, sizeof(" + formatVarType + "));");
                code.decoderLines.add(formatVarName + " = " + code.getNameSpace() + "::correctEndianness(" + tempVarName + ");");
                code.decoderLines.add("pos += sizeof(" + formatVarType + ");");

                /// Add the code for the field length
                tempVarName = lengthVarName + "Temp";
                code.decoderLines.add("");
                code.decoderLines.add(lengthType + " " + tempVarName + ";");
                code.decoderLines.add("");
                code.decoderLines.add("memcpy(&" + tempVarName + ", bytes+pos, sizeof(" + lengthType + "));");
                code.decoderLines.add(lengthVarName + " = " + code.getNameSpace() + "::correctEndianness(" + tempVarName +");");
                code.decoderLines.add("pos += sizeof(" + lengthType + ");");

                /// Add the code for the field length
                code.decoderLines.add("");
                code.decoderLines.add("delete[] " + dataVarName + ";");
                code.decoderLines.add(dataVarName + " = NULL;");
                code.decoderLines.add("");
                code.decoderLines.add("if (" + lengthVarName + " > 0)");
                code.decoderLines.add("{");
                code.decoderLines.add("\t" + dataVarName + " = new unsigned char[" + lengthVarName +"];");
                code.decoderLines.add("\tmemcpy(" + dataVarName +", bytes+pos, " + lengthVarName + ");");
                code.decoderLines.add("\tpos += " + lengthVarName + ";");
                code.decoderLines.add("}");
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                // Add the code for the field format
                JavaCode.generateDecoderLines(formatVarType, formatVarName, code.decoderLines, formatVarSigned);

                /// Add the code for the field length
                code.decoderLines.add("");
                JavaCode.generateDecoderLines(lengthType, lengthVarName, code.decoderLines, lengthSigned);

                /// Add the code for the field length
                code.decoderLines.add("");
                code.decoderLines.add("byte[] dataBytes = new byte[(int)" + lengthVarName + "];");
                JavaCode.decodeByteArrayLines("dataBytes", "(int)" + lengthVarName, code.decoderLines, false);
                code.decoderLines.add(dataVarName + " = ByteBuffer.allocate((int)" + lengthVarName + ").order(ByteOrder.LITTLE_ENDIAN);");
                code.decoderLines.add(dataVarName + ".put(dataBytes);");
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                /// Add the code for the field format
                CSharpCode.generateDecoderLines(formatVarType, formatVarName, code.decoderLines);;

                /// Add the code for the field length
                code.decoderLines.add("");
                CSharpCode.generateDecoderLines(lengthType, lengthVarName, code.decoderLines);

                /// Add the code for the field length
                code.decoderLines.add("");
                code.decoderLines.add(dataVarName + " = null;");
                code.decoderLines.add("");
                code.decoderLines.add("if (" + lengthVarName + " > 0)");
                code.decoderLines.add("{");
                code.decoderLines.add("\t" + dataVarName + " = new byte[(int) " + lengthVarName +"];");
                code.decoderLines.add("\t" + dataVarName + " = JausUtils.getFromBuffer(bytes, pos, (int)" + lengthVarName + ", true);");
                code.decoderLines.add("\tpos += (int)" + lengthVarName + ";");
                code.decoderLines.add("}");
            }
	}
	
	private void generateFieldGetFormatMethod(CodeLines code)
	{
            methodCode.clear();
            methodParam.clear();
		
            /// Add the code for the field format
            /// Add the getFormat() Method
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                code.publicMethods.add(CppCode.createMethodDeclaration("const " + formatVarType, "get", "format", methodParam, true));

                methodCode.add("return " + formatVarName + ";");
                code.methods.addAll(CppCode.createMethodDefinition("const " + formatVarType, fullClassName + "::get", "format", methodParam, methodCode, true));
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                methodCode.add("return " + formatVarName + ";");
                code.methods.addAll(JavaCode.createMethodDefinition("public " + formatVarType, "get", "format", methodParam, methodCode, true));
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                methodCode.add("return " + formatVarName + ";");
                code.methods.addAll(CSharpCode.createMethodDefinition("public " + formatVarType, "get", "format", methodParam, methodCode, true));
            }
	}
	
	private void generateFieldGetLengthMethod(CodeLines code)
	{
            methodCode.clear();
            methodParam.clear();
		
            /// Add getLength() method
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                code.publicMethods.add(CppCode.createMethodDeclaration("const " + lengthType, "get", "length", methodParam, true));
           
                methodCode.add("return " + lengthVarName + ";");
                code.methods.addAll(CppCode.createMethodDefinition("const " + lengthType, fullClassName + "::get", "length", methodParam, methodCode, true));
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                methodCode.add("return " + lengthVarName + ";");
                code.methods.addAll(JavaCode.createMethodDefinition("public " + lengthType, "get", "length", methodParam, methodCode, true));
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                methodCode.add("return " + lengthVarName + ";");
                code.methods.addAll(CSharpCode.createMethodDefinition("public " + lengthType, "get", "length", methodParam, methodCode, true));
            }
	}
	
	private void generateFieldGetDataMethod(CodeLines code)
	{
            methodCode.clear();
            methodParam.clear();

            /// Add getData() method
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                code.publicMethods.add(CppCode.createMethodDeclaration("const unsigned char", "*get", "data", null, true));

                methodCode.add("return " + dataVarName + ";");
                code.methods.addAll(CppCode.createMethodDefinition("const unsigned char", "*" + fullClassName + "::get", "data", methodParam, methodCode, true));
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                methodCode.add(dataVarName + ".rewind();");
                methodCode.add("ByteBuffer ret = ByteBuffer.allocate(" + dataVarName + ".array().length);");
                methodCode.add("ret.order(ByteOrder.LITTLE_ENDIAN);");
                methodCode.add("ret.put(" + dataVarName + ");");
                methodCode.add("return ret;");
                code.methods.addAll(JavaCode.createMethodDefinition("public ByteBuffer", "get", "data", methodParam, methodCode, true));
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                methodCode.add("return " + dataVarName + ";");
                code.methods.addAll(CSharpCode.createMethodDefinition("public byte[]", "get", "data", methodParam, methodCode, true));
            }
	}
	
	private void generateFieldSetMethod(CodeLines code, StringBuffer validFormatEnum)
	{
            methodCode.clear();
            methodParam.clear();

            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                // Add code for the set(jUnsignedByte format, <lengthType> length, unsigned char *data) method
                methodParam.add(formatVarType + " format");
                methodParam.add(lengthType + " length");
                methodParam.add("unsigned char *data");
                code.publicMethods.add(CppCode.createMethodDeclaration("int", "set", null, methodParam, false));

                methodCode.add("if (" + validFormatEnum + ")");
                methodCode.add("{");
                methodCode.add("\t" + CppCode.getVariableName("format") + " = format;");
                methodCode.add("\t" + CppCode.getVariableName("length") + " = length;");
                methodCode.add("");
                methodCode.add("\tdelete[] " + dataVarName + ";");
                methodCode.add("\t" + dataVarName + " = NULL;");
                methodCode.add("");
                methodCode.add("\t" + dataVarName + " = new unsigned char[length];");
                methodCode.add("\tmemcpy(" + dataVarName + ", data, length);");
                methodCode.add("");
                methodCode.add(CppCode.getParentReferenceSetParentPVLine());
                methodCode.add("");
                methodCode.add("\treturn 0;");
                methodCode.add("}");
                methodCode.add("");
                methodCode.add("return 1;");
                code.methods.addAll(CppCode.createMethodDefinition("int", fullClassName + "::set", null, methodParam, methodCode, false));
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                // Add code for the set(jUnsignedByte format, <lengthType> length, ByteBuffer data) method
                methodParam.add(formatVarType + " format");
                methodParam.add(lengthType + " length");
                methodParam.add("ByteBuffer data");

                methodCode.add("if (" + validFormatEnum + ")");
                methodCode.add("{");
                methodCode.add("\t" + JavaCode.getVariableName("format") + " = format;");
                methodCode.add("\t" + JavaCode.getVariableName("length") + " = length;");
                methodCode.add("");
                methodCode.add("\t" + dataVarName + ".clear();");
                methodCode.add("");
                methodCode.add("\tdata.rewind();");
                methodCode.add("\t" + dataVarName + " = ByteBuffer.allocate(data.array().length);");
                methodCode.add("\t"+ dataVarName + ".order(ByteOrder.LITTLE_ENDIAN);");
                methodCode.add("\t" + dataVarName + ".put(data);");
                methodCode.add("");
                methodCode.add(JavaCode.getParentReferenceSetParentPVLine());
                methodCode.add("");                
                methodCode.add("\treturn;");
                methodCode.add("}");
                code.methods.addAll(JavaCode.createMethodDefinition("public void", "set", null, methodParam, methodCode, false));
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                // Add code for the set(jUnsignedByte format, <lengthType> length, unsigned char *data) method
                methodParam.add(formatVarType + " format");
                methodParam.add(lengthType + " length");
                methodParam.add("byte[] data");

                methodCode.add("if (" + validFormatEnum + ")");
                methodCode.add("{");
                methodCode.add("\t" + CSharpCode.getVariableName("format") + " = format;");
                methodCode.add("\t" + CSharpCode.getVariableName("length") + " = length;");
                methodCode.add("");
                methodCode.add("\t" + dataVarName + " = new byte[(int) length];");
                methodCode.add("");
                methodCode.add("\tfor(int i = 0; i < length; i++)");
                methodCode.add("\t" + dataVarName + "[i] = data[i];");
                methodCode.add("");
                // Create framework for parent reference
                methodCode.add(CSharpCode.getParentReferenceSetParentPVLine());
                methodCode.add("");                
                methodCode.add("\treturn;");
                methodCode.add("}");
                code.methods.addAll(CSharpCode.createMethodDefinition("public void", "set", null, methodParam, methodCode, false));
            }
	}
	
	private void generateFieldOverloadAssignmentMethod(CodeLines code)
	{
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                /// Overload the operator=
                code.assignmentLines.add("this->" + formatVarName + " = value." + formatVarName + ";");
                code.assignmentLines.add("this->" + lengthVarName + " = value." + lengthVarName + ";");
                code.assignmentLines.add("");
                code.assignmentLines.add("delete[] " + dataVarName + ";");
                code.assignmentLines.add(dataVarName + " = NULL;");
                code.assignmentLines.add("");
                code.assignmentLines.add("if (" + lengthVarName + " > 0)");
                code.assignmentLines.add("{");
                code.assignmentLines.add("\t" + dataVarName + " = new unsigned char[this->" + lengthVarName + "];");
                code.assignmentLines.add("\tmemcpy(this->" + dataVarName + ", value." + dataVarName + ", this->" + lengthVarName + ");");
                code.assignmentLines.add("}");
                /// Return value is added automatically
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                /// Overload the operator=
                code.assignmentLines.add("this." + formatVarName + " = value." + formatVarName + ";");
                code.assignmentLines.add("this." + lengthVarName + " = value." + lengthVarName + ";");
                code.assignmentLines.add("");
                code.assignmentLines.add(dataVarName + ".clear();");
                code.assignmentLines.add("");
                code.assignmentLines.add("if (" + lengthVarName + " > 0)");
                code.assignmentLines.add("{");
                code.assignmentLines.add("this." + dataVarName + " = ByteBuffer.wrap(value." + dataVarName + ".array()).order(ByteOrder.LITTLE_ENDIAN);");
                code.assignmentLines.add("}");
                /// Return value is added automatically
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                /// Overload the operator=
                code.assignmentLines.add("this." + formatVarName + " = value." + formatVarName + ";");
                code.assignmentLines.add("this." + lengthVarName + " = value." + lengthVarName + ";");
                code.assignmentLines.add("");
                code.assignmentLines.add(dataVarName + " = null;");
                code.assignmentLines.add("");
                code.assignmentLines.add("if (" + lengthVarName + " > 0)");
                code.assignmentLines.add("{");
                code.assignmentLines.add("\t" + dataVarName + " = value." + dataVarName + ";");
                code.assignmentLines.add("}");
                /// Return value is added automatically
            }
	}
	
	private void generateFieldOverloadIsEqualMethod(CodeLines code)
	{
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                /// Overload operator==
                code.equalLines.add("if (this->" + formatVarName + " != value." + formatVarName + ")");
                code.equalLines.add("{");
                code.equalLines.add("\treturn false;");
                code.equalLines.add("}");
                code.equalLines.add("");
                code.equalLines.add("if (this->" + lengthVarName + " != value." + lengthVarName + ")");
                code.equalLines.add("{");
                code.equalLines.add("\treturn false;");
                code.equalLines.add("}");
                code.equalLines.add("");
                code.equalLines.add("if ((this->" + dataVarName + " != NULL) && (value." + dataVarName + "!= NULL))");
                code.equalLines.add("{");
                code.equalLines.add("\tif (memcmp(this->" + dataVarName + ", value." + dataVarName + ", this->" + lengthVarName + ") != 0)");
                code.equalLines.add("\t{");
                code.equalLines.add("\t\treturn false;");
                code.equalLines.add("\t}");
                code.equalLines.add("}");
                code.equalLines.add("// This case should never be true since it should not be possible");
                code.equalLines.add("// for the two variables to have equal lengths but one has no data.");
                code.equalLines.add("// This check is placed here as a secondary check.");
                code.equalLines.add("else if ((this->" + dataVarName + " != NULL) || (value." + dataVarName + " != NULL))");
                code.equalLines.add("{");
                code.equalLines.add("\treturn false;");
                code.equalLines.add("}");
                code.equalLines.add("");
                code.equalLines.add("return true;");
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                /// implement isEqual
                code.equalLines.add("if (this." + formatVarName + " != value." + formatVarName + ")");
                code.equalLines.add("{");
                code.equalLines.add("\treturn false;");
                code.equalLines.add("}");
                code.equalLines.add("");
                code.equalLines.add("if (this." + lengthVarName + " != value." + lengthVarName + ")");
                code.equalLines.add("{");
                code.equalLines.add("\treturn false;");
                code.equalLines.add("}");
                code.equalLines.add("");
                code.equalLines.add("if ((this." + dataVarName + ".array() != null) && (value." + dataVarName + ".array() != null))");
                code.equalLines.add("{");
                code.equalLines.add("\tif(!Arrays.equals(this." + dataVarName + ".array(), value." + dataVarName + ".array()))");
                code.equalLines.add("\t{");
                code.equalLines.add("\t\treturn false;");
                code.equalLines.add("\t}");
                code.equalLines.add("}");
                code.equalLines.add("// This case should never be true since it should not be possible");
                code.equalLines.add("// for the two variables to have equal lengths but one has no data.");
                code.equalLines.add("// This check is placed here as a secondary check.");
                code.equalLines.add("else if ((this." + dataVarName + ".array() != null) || (value." + dataVarName + ".array() != null))");
                code.equalLines.add("{");
                code.equalLines.add("\treturn false;");
                code.equalLines.add("}");
                code.equalLines.add("");
                code.equalLines.add("return true;");
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                /// implement isEqual
                code.equalLines.add("if (this." + formatVarName + " != value." + formatVarName + ")");
                code.equalLines.add("{");
                code.equalLines.add("\treturn false;");
                code.equalLines.add("}");
                code.equalLines.add("");
                code.equalLines.add("if (this." + lengthVarName + " != value." + lengthVarName + ")");
                code.equalLines.add("{");
                code.equalLines.add("\treturn false;");
                code.equalLines.add("}");
                code.equalLines.add("");
                code.equalLines.add("if ((this." + dataVarName + " != null) && (value." + dataVarName + "!= null))");
                code.equalLines.add("{");
                code.equalLines.add("\tfor(int i = 0; i < value." + lengthVarName + "; i++)");
                code.equalLines.add("\t{");
                code.equalLines.add("\t\tif (this." + dataVarName + "[i] != value." + dataVarName + "[i])");
                code.equalLines.add("\t\t{");
                code.equalLines.add("\t\t\treturn false;");
                code.equalLines.add("\t\t}");
                code.equalLines.add("\t}");
                code.equalLines.add("}");
                code.equalLines.add("// This case should never be true since it should not be possible");
                code.equalLines.add("// for the two variables to have equal lengths but one has no data.");
                code.equalLines.add("// This check is placed here as a secondary check.");
                code.equalLines.add("else if ((this." + dataVarName + " != null) || (value." + dataVarName + " != null))");
                code.equalLines.add("{");
                code.equalLines.add("\treturn false;");
                code.equalLines.add("}");
                code.equalLines.add("");
                code.equalLines.add("return true;");
            }
	}
	
	private void generateFieldOverloadNotEqualMethod(CodeLines code)
	{
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                /// Overload operator!=
                code.notEqualLines.add("if (this->" + formatVarName + " == value." + formatVarName + ")");
                code.notEqualLines.add("{");
                code.notEqualLines.add("\treturn false;");
                code.notEqualLines.add("}");
                code.notEqualLines.add("");
                code.notEqualLines.add("if (this->" + lengthVarName + " == value." + lengthVarName + ")");
                code.notEqualLines.add("{");
                code.notEqualLines.add("\treturn false;");
                code.notEqualLines.add("}");
                code.notEqualLines.add("");
                code.notEqualLines.add("if ((this->m_Data != NULL) && (value.m_Data != NULL))");
                code.notEqualLines.add("{");
                code.notEqualLines.add("\tif (memcmp(this->" + dataVarName + ", value." + dataVarName + ", this->" + lengthVarName + ") == 0)");
                code.notEqualLines.add("\t{");
                code.notEqualLines.add("\t\treturn false;");
                code.notEqualLines.add("\t}");
                code.notEqualLines.add("}");
                code.notEqualLines.add("// This case should never be true since length should be equal but is");
                code.notEqualLines.add("// placed here as a secondary check");
                code.notEqualLines.add("else if ((this->m_Data == NULL) && (value.m_Data == NULL))");
                code.notEqualLines.add("{");
                code.notEqualLines.add("\treturn false;");
                code.notEqualLines.add("}");
                code.notEqualLines.add("");
                code.notEqualLines.add("return true;");
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                // implement notEquals()
                code.notEqualLines.add("return !this.isEqual(value);");
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                // implement notEquals()
                code.notEqualLines.add("return !(this.isEqual(value));");
            }
	}
	
	/**
	 * Generate instance
	 * @param pvIndex
	 * @param code
	 */
	private void generateSingleInstance(int pvIndex, CodeLines code)
	{
            /*
             * Create code lines for the encode, decode Methods
             * Create the getMethod and setMethod
             * This has to be done after the class wrapper has been added
             */
		
            // create variable
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                code.protectedAttributes.add(CppCode.createVariableDeclaration(shortClassName, shortClassName, false));
                CppCode.addParentReferenceConstructorSetParent(code, varName);
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                code.protectedAttributes.add(JavaCode.createVariableDeclaration("protected " + shortClassName, shortClassName, false));
                code.constructorLines.add(varName + " = new " + shortClassName + "();");
                JavaCode.addParentReferenceConstructorSetParent(code, varName);
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
				code.protectedAttributes.add(CSharpCode.createVariableDeclaration("protected " + shortClassName, shortClassName, false));
                code.constructorLines.add(varName + " = new " + shortClassName + "();");
                CSharpCode.addParentReferenceConstructorSetParent(code, varName);
            }

            generateInstanceGetSizeMethod(code, pvIndex);
            generateInstanceIsValidMethod(code, pvIndex);

            generateInstanceEncodeMethod(code, pvIndex);
            generateInstanceDecodeMethod(code, pvIndex);

            generateInstanceGetMethod(code);
            generateInstanceSetMethod(code, pvIndex);
	}

	private void generateInstanceGetSizeMethod(CodeLines code, int pvIndex)
	{
            methodCode.clear();
		
	    /// GetSize method
	    /// only add size of array if optional is true 
	    methodCode.add("size += " + varName + ".getSize();");
	    if (vfField.isOptional()) 
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
	
	private void generateInstanceIsValidMethod(CodeLines code, int pvIndex)
	{
	    /// is[Field]valid added if optional
	    /// return true if field is set in presence vector
	    if (vfField.isOptional()) 
	    {
	    	methodCode.clear();
	    	if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
                {
                    code.publicMethods.add(CppCode.createMethodDeclaration("bool", "is", shortClassName + "Valid", null, true));

                    methodCode.add("return true;");
                    methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                    methodCode.add("return false;");
                    code.methods.addAll(CppCode.createMethodDefinition("bool", parentClassName + "::is", shortClassName + "Valid", null, methodCode, true));

                }
                else if(codeType == CodeLines.CodeType.JAVA)
                {
                    methodCode.add("return true;");
                    methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                    methodCode.add("return false;");
                    code.methods.addAll(JavaCode.createMethodDefinition("public boolean", "is", shortClassName + "Valid", null, methodCode, true));

                }
                else if(codeType == CodeLines.CodeType.C_SHARP)
                {
                    methodCode.add("return true;");
                    methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
                    methodCode.add("return false;");
                    code.methods.addAll(CSharpCode.createMethodDefinition("public bool", "is", shortClassName + "Valid", null, methodCode, true));
                }
	    }
	}
	
	private void generateInstanceEncodeMethod(CodeLines code, int pvIndex)
	{
            methodCode.clear();

            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                /* 	m_VaribleLengthField1.encode(bytes + pos);
                 *	pos += m_VaribleLengthField1.getSize();
                 */
                methodCode.add(varName + ".encode(bytes + pos);");
                methodCode.add("pos += " + varName + ".getSize();");
                if (vfField.isOptional())
                {
                    methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                }
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                /* 	m_VaribleLengthField1.encode(bytes, pos);
                 *	pos += m_VaribleLengthField1.getSize();
                 */
                methodCode.add(varName + ".encode(bytes, pos);");
                methodCode.add("pos += " + varName + ".getSize();");
                if (vfField.isOptional())
                {
                    methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                }
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                /* 	m_VaribleLengthField1.encode(bytes + pos);
                 *	pos += m_VaribleLengthField1.getSize();
                 */
                methodCode.add(varName + ".encode(bytes, pos);");
                methodCode.add("pos += " + varName + ".getSize();");
                if (vfField.isOptional())
                {
                    methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
                }
            }

            /// If the encoder lines are not empty then put a line to increase readability
            if (!code.encoderLines.isEmpty())
            {
        	code.encoderLines.add("");
            }
            code.encoderLines.addAll(methodCode);
	}
	
	private void generateInstanceDecodeMethod(CodeLines code, int pvIndex)
	{
            methodCode.clear();
		
            /* 	m_VaribleLengthField1.decode(bytes + pos);
             *	pos += m_VaribleLengthField1.getSize();
             */
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                methodCode.add(varName + ".decode(bytes + pos);");
                methodCode.add("pos += " + varName + ".getSize();");
                if (vfField.isOptional())
                {
                    methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                }
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                methodCode.add(varName + ".decode(bytes, pos);");
                methodCode.add("pos += " + varName + ".getSize();");
                if (vfField.isOptional())
                {
                    methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                }
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                methodCode.add(varName + ".decode(bytes, pos);");
                methodCode.add("pos += " + varName + ".getSize();");
                if (vfField.isOptional())
                {
                    methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
                }
            }

            /// If the decoder lines are not empty then put a line to increase readability
            if (!code.decoderLines.isEmpty())
            {
        	code.decoderLines.add("");
            }
            code.decoderLines.addAll(methodCode);
	}
	
	private void generateInstanceGetMethod(CodeLines code)
	{
            methodParam.clear();
            methodCode.clear();

            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                ///  Generate getMethod Declaration and Definition
                code.publicMethods.add(CppCode.createMethodDeclaration(shortClassName  + "* const", "get", shortClassName, methodParam, false));
    	
                methodCode.add("return &" + varName + ";");
		code.methods.addAll(CppCode.createMethodDefinition(fullClassName + "* const", parentClassName + "::get", shortClassName, methodParam, methodCode, false));
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                ///  Generate getMethod Definition

                methodCode.add("return " + varName + ";");
		code.methods.addAll(JavaCode.createMethodDefinition("public " + fullClassName, "get", shortClassName, methodParam, methodCode, false));
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                ///  Generate getMethod Definition

                methodCode.add("return " + varName + ";");
		code.methods.addAll(CSharpCode.createMethodDefinition("public " + fullClassName, "get", shortClassName, methodParam, methodCode, false));
            }
        }
	
	private void generateInstanceSetMethod(CodeLines code, int pvIndex)
	{
            methodParam.clear();
            methodCode.clear();

            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                ///  Generate setMethod Declaration and Definition
                methodParam.add("const " + shortClassName + " &value");
                code.publicMethods.add(CppCode.createMethodDeclaration("int", "set", shortClassName, methodParam, false));

                methodCode.add(varName + " = value;");
                if (vfField.isOptional())
                {
                    methodCode.add("setPresenceVector("+ pvIndex +");");
                }
                // Create framework for parent reference
                methodCode.add(CppCode.getParentReferenceSetParentPVLine());
                methodCode.add("return 0;");
                code.methods.addAll(CppCode.createMethodDefinition("int", parentClassName + "::set", shortClassName, methodParam, methodCode, false));
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                ///  Generate setMethod Definition
                methodParam.add(shortClassName + " value");

                methodCode.add(varName + " = value;");
                if (vfField.isOptional())
                {
                    methodCode.add("setPresenceVector("+ pvIndex +");");
                }
                // Create framework for parent reference
                methodCode.add(JavaCode.getParentReferenceSetParentPVLine());
                methodCode.add("return 0;");
                code.methods.addAll(JavaCode.createMethodDefinition("public int", "set", shortClassName, methodParam, methodCode, false));
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                ///  Generate setMethod Definition
                methodParam.add(shortClassName + " value");

                methodCode.add(varName + " = value;");
                if (vfField.isOptional())
                {
                    methodCode.add("setPresenceVector("+ pvIndex +");");
                }
                // Create framework for parent reference
                methodCode.add(CSharpCode.getParentReferenceSetParentPVLine());
                methodCode.add("return 0;");
                code.methods.addAll(CSharpCode.createMethodDefinition("public int", "set", shortClassName, methodParam, methodCode, false));
            }
	}

        /**
         * For adding code to the constructor for an array in C# or Java.
         * @param code the CodeLines object being populated
         * @param arraySize Size of the array being generated
         * @param var Name of the array data variable
         * @param className Name of the class of the array data variable.
         */
        private void initArrayInstances(CodeLines code, int arraySize, String var, String className) {
             // initialize individual instances in array
            code.constructorLines.add("for (int i = 0; i < " + arraySize + "; ++i)");
            code.constructorLines.add("{");
            code.constructorLines.add("\t" + var + "[i] = new " + className + "();");
            code.constructorLines.add("}");
        }

	/**
	 * Generate array
	 * @param pvIndex
	 * @param code
	 */
	private void generateArray(int pvIndex, CodeLines code)
	{
            List<String> paramCode = new ArrayList<String>();
            List<String> getMethodCode = new ArrayList<String>();
            List<String> setMethodCode = new ArrayList<String>();

            String posCalc = "";	// The calculation for the position within an 1-dimension array from the n-dimension array
            String prevCalc = "";	// The previous calculation for the position within an 1-dimension array from the n-dimension array
	    int arraySize = 1;
		
	    paramCode.clear();
            for (int i = 0; i < dimList.size(); i++)
            {
                Dimension dim = dimList.get(i);

                String dimName = Util.upperCaseFirstLetter(dim.getName());
                String dimSizeName = dimName + "Size";
                String dimVarName = "";

                if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
                {
                    dimVarName = CppCode.getVariableName(dimSizeName);

                    code.protectedAttributes.add(CppCode.createVariableDeclaration("unsigned int", dimSizeName, false));
                    code.constructorLines.add(dimVarName + " = " + dim.getSize() + ";");

                    ///  Generate getMethod Declaration and Definition
                    code.publicMethods.add(CppCode.createMethodDeclaration("const unsigned int", "get", dimSizeName, null, true));

                    methodCode.clear();
                    methodCode.add("return " + dimVarName + ";");
                    code.methods.addAll(CppCode.createMethodDefinition("const unsigned int", parentClassName + "::get", dimSizeName, null, methodCode, true));

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
                }
                else if(codeType == CodeLines.CodeType.JAVA)
                {
                    dimVarName = JavaCode.getVariableName(dimSizeName);

                    code.protectedAttributes.add(JavaCode.createVariableDeclaration("protected long", dimSizeName, false));
                    code.constructorLines.add(dimVarName + " = " + dim.getSize() + ";");

                    ///  Generate getMethod Definition
                    methodCode.clear();
                    methodCode.add("return " + dimVarName + ";");
                    code.methods.addAll(JavaCode.createMethodDefinition("public long", "get", dimSizeName, null, methodCode, true));

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
                }
                else if(codeType == CodeLines.CodeType.C_SHARP)
                {
                    dimVarName = CSharpCode.getVariableName(dimSizeName);

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
                }
	       
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
		
	    /*
	     * Create code lines for the encode, decode Methods
	     * Create the getMethod and setMethod
	     * This has to be done after the class wrapper has been added
	     */
		
            // create variable
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                /*
                 * Create framework for parent reference
                 */
                CppCode.addParentReferenceConstructorSetParrentInArray(code, varName, arraySize);

                code.protectedAttributes.add(CppCode.createVariableDeclaration(shortClassName, shortClassName + "[" + arraySize + "]" , false));
                code.constructorLines.add("/// No Initialization of " + varName + " necessary.");
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {              
                code.protectedAttributes.add(JavaCode.createVariableDeclaration("protected " + shortClassName+ "[]", shortClassName + " = new " + shortClassName + "[" + arraySize + "]" , false));
                code.constructorLines.add(varName + " = new " + shortClassName + "[" + arraySize + "];");

                initArrayInstances(code, arraySize, varName, shortClassName);

                /*
                 * Create framework for parent reference
                 */
                JavaCode.addParentReferenceConstructorSetParrentInArray(code, varName, arraySize);
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {              
                code.protectedAttributes.add(CSharpCode.createVariableDeclaration("protected " + shortClassName+ "[]", shortClassName + " = new " + shortClassName + "[" + arraySize + "]" , true));
                code.constructorLines.add(varName + " = new " + shortClassName + "[" + arraySize + "];");

                initArrayInstances(code, arraySize, varName, shortClassName);

                /*
                 * Create framework for parent reference
                 */
                CSharpCode.addParentReferenceConstructorSetParrentInArray(code, varName, arraySize);
            }

	    generateArrayGetSizeMethod(code, pvIndex, arraySize);
	    generateArrayIsValidMethod(code, pvIndex);
	
	    generateArrayEncodeMethod(code, arraySize);
	    generateArrayDecodeMethod(code, arraySize);

	    generateArrayGetMethod(code, posCalc, paramCode, getMethodCode);
        generateArraySetMethod(code, posCalc, paramCode, setMethodCode);

        generateArrayOverloadIsEqualMethod(code, arraySize);
        generateArrayOverloadNotEqualMethod(code, arraySize);
        generateArrayOverloadAssignmentMethod(code, arraySize);
	}
	
	private void generateArrayGetSizeMethod(CodeLines code, int pvIndex, int arraySize)
	{
		methodCode.clear();
		
	    /// GetSize method
	    /// only add size of array if optional is true 
		if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
    		methodCode.add("for (unsigned int i = 0; i < "  + arraySize + "; i++)");
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
    		methodCode.add("for (int i = 0; i < (int)"  + arraySize + "; i++)");
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
    		methodCode.add("for (int i = 0; i < (int)"  + arraySize + "; i++)");
        }
		methodCode.add("{");
		methodCode.add("\tsize += " + varName + "[i].getSize();");
		methodCode.add("}");
	    if (vfField.isOptional()) 
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
	    if (vfField.isOptional()) 
	    {
	    	methodCode.clear();

                if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
                {
                    code.publicMethods.add(CppCode.createMethodDeclaration("bool", "is", shortClassName + "Valid", null, true));

                    methodCode.add("return true;");
                    methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                    methodCode.add("return false;");
                    code.methods.addAll(CppCode.createMethodDefinition("bool", parentClassName + "::is", shortClassName + "Valid", null, methodCode, true));
                }
                else if(codeType == CodeLines.CodeType.JAVA)
                {
                    methodCode.add("return true;");
                    methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                    methodCode.add("return false;");
                    code.methods.addAll(JavaCode.createMethodDefinition("public boolean", "is", shortClassName + "Valid", null, methodCode, true));
                }
                else if(codeType == CodeLines.CodeType.C_SHARP)
                {
                    methodCode.add("return true;");
                    methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
                    methodCode.add("return false;");
                    code.methods.addAll(CSharpCode.createMethodDefinition("public bool", "is", shortClassName + "Valid", null, methodCode, true));
                }
	    }
	}
	
	private void generateArrayEncodeMethod(CodeLines code, int arraySize)
	{
            methodCode.clear();
		
	    /*  
             * C++:
             * for (unsigned int i = 0; i < "arraySize"; i++)
	     * {	
	     * 		m_VaribleLengthField1[i].encode(bytes + pos);
	     *		pos += m_VaribleLengthField1[i].getSize();
	     * }
             * Java:
             * for(long i = 0; i< "arraySize"; i++)
             * {
             *          m_VariableLenghtField.get(i).encode(bytes, pos);
             *          pos += m_VariableLengthField.get(i).getSize();
             *
	     */
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                methodCode.add("for (unsigned int i = 0; i < "  + arraySize + "; i++)");
                methodCode.add("{");
                methodCode.add("\t" + varName + "[i].encode(bytes + pos);");
                methodCode.add("\tpos += " + varName + "[i].getSize();");
                methodCode.add("}");
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                methodCode.add("for (int i = 0; i < "  + arraySize + "; i++)");
                methodCode.add("{");
                methodCode.add("\t" + varName + "[i].encode(bytes, pos);");
                methodCode.add("\tpos += " + varName + "[i].getSize();");
                methodCode.add("}");
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                methodCode.add("for (int i = 0; i < "  + arraySize + "; i++)");
                methodCode.add("{");
                methodCode.add("\t" + varName + "[i].encode(bytes, pos);");
                methodCode.add("\tpos += " + varName + "[i].getSize();");
                methodCode.add("}");
            }
	    
	    /// If the encoder lines are not empty then put a line to increase readability
	    if (!code.encoderLines.isEmpty())
	    {
	    	code.encoderLines.add("");
	    }
	    code.encoderLines.addAll(methodCode);
	}
	
	private void generateArrayDecodeMethod(CodeLines code, int arraySize)
	{
            methodCode.clear();
		
	    /* C++:
             * for (unsigned int i = 0; i < "arraySize"; i++)
	     * {	
	     * 		m_VaribleLengthField1[i].decode(bytes + pos);
	     *		pos += m_VaribleLengthField1[i].getSize();
	     * }
             *
             * Java:
             * for(long i = 0; i< "arraySize"; i++)
             * {
             *      m_VariableLengthField.get(i).decode(bytes, pos);
             * ...
             *
             * C#:
             * for(uint i = 0; i< "arraySize"; i++)
             * {
             *      m_VariableLengthField[i].decode(bytes, pos);
             * ...
	     */
             if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
             {
                methodCode.add("for (unsigned int i = 0; i < "  + arraySize + "; i++)");
                methodCode.add("{");
                methodCode.add("\t" + varName + "[i].decode(bytes + pos);");
                methodCode.add("\tpos += " + varName + "[i].getSize();");
                methodCode.add("}");
             }
             else if(codeType == CodeLines.CodeType.JAVA)
             {
                methodCode.add("for (int i = 0; i < "  + arraySize + "; i++)");
                methodCode.add("{");
                methodCode.add("\t" + varName + "[i].decode(bytes, pos);");
                methodCode.add("\tpos += " + varName + "[i].getSize();");
                methodCode.add("}");
             }
             else if(codeType == CodeLines.CodeType.C_SHARP)
             {
                methodCode.add("for (uint i = 0; i < "  + arraySize + "; i++)");
                methodCode.add("{");
                methodCode.add("\t" + varName + "[i].decode(bytes, pos);");
                methodCode.add("\tpos += " + varName + "[i].getSize();");
                methodCode.add("}");
             }

	    /// If the decoder lines are not empty then put a line to increase readability
	    if (!code.decoderLines.isEmpty())
	    {
	    	code.decoderLines.add("");
	    }
	    code.decoderLines.addAll(methodCode);
	}
	
	private void generateArrayGetMethod(CodeLines code, String posCalc, List<String> paramCode, List<String> getMethodCode)
	{
		methodCode.clear();
		
		///  Generate getMethod Declaration and Definition
                if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
                {
                    code.publicMethods.add(CppCode.createMethodDeclaration(shortClassName  + "* const", "get", shortClassName, paramCode, false));

                    methodCode.add("unsigned int index = " + posCalc + ";");
                    methodCode.add("");
                    methodCode.addAll(getMethodCode);
                    methodCode.add("return &" + varName + "[index];");
                    code.methods.addAll(CppCode.createMethodDefinition(fullClassName + "* const", parentClassName + "::get", shortClassName, paramCode, methodCode, false));
                }
                else if(codeType == CodeLines.CodeType.JAVA)
                {
                    methodCode.add("long index = " + posCalc + ";");
                    methodCode.add("");
                    methodCode.addAll(getMethodCode);
                    methodCode.add("return " + varName + "[(int)index];");
                    code.methods.addAll(JavaCode.createMethodDefinition("public "+fullClassName, "get", shortClassName, paramCode, methodCode, false));
                }
                else if(codeType == CodeLines.CodeType.C_SHARP)
                {
                    methodCode.add("int index = " + posCalc + ";");
                    methodCode.add("");
                    methodCode.addAll(getMethodCode);
                    methodCode.add("return " + varName + "[index];");
                    code.methods.addAll(CSharpCode.createMethodDefinition("public " + fullClassName, "get", shortClassName, paramCode, methodCode, false));
                }
	}
	
	private void generateArraySetMethod(CodeLines code, String posCalc, List<String> paramCode, List<String> setMethodCode)
	{
		methodCode.clear();
		
                ///  Generate setMethod Declaration and Definition
                if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
                {
                    paramCode.add("const " + shortClassName + " &value");
                    code.publicMethods.add(CppCode.createMethodDeclaration("int", "set", shortClassName, paramCode, false));

                    methodCode.add("unsigned int index = " + posCalc + ";");
                    methodCode.add("");
                    methodCode.addAll(setMethodCode);
                    methodCode.add(varName + "[index] = value;");

            		// Create framework for parent reference
                    methodCode.add(CppCode.getParentReferenceSetParentPVLine());
                    methodCode.add("return 0;");
                    code.methods.addAll(CppCode.createMethodDefinition("int", parentClassName + "::set", shortClassName, paramCode, methodCode, false));
                }
                else if(codeType == CodeLines.CodeType.JAVA)
                {
                    paramCode.add(shortClassName + " value");

                    methodCode.add("int index = " + posCalc + ";");
                    methodCode.add("");
                    methodCode.addAll(setMethodCode);
                    methodCode.add(varName + "[index].assign(value);");

                    // Create framework for parent reference
                    methodCode.add(JavaCode.getParentReferenceSetParentPVLine());
                    methodCode.add("return 0;");
                    code.methods.addAll(JavaCode.createMethodDefinition("public int", "set", shortClassName, paramCode, methodCode, false));
                }
                else if(codeType == CodeLines.CodeType.C_SHARP)
                {
                    paramCode.add(shortClassName + " value");

                    methodCode.add("int index = " + posCalc + ";");
                    methodCode.add("");
                    methodCode.addAll(setMethodCode);
                    methodCode.add(varName + "[index].set" + shortClassName + "(value);");

                    // Create framework for parent reference
                    methodCode.add(CSharpCode.getParentReferenceSetParentPVLine());
                    methodCode.add("return 0;");
                    code.methods.addAll(CSharpCode.createMethodDefinition("public int", "set", shortClassName, paramCode, methodCode, false));
                }
	}

    /**
     * Adds body code for the equality check function
     * @param code CodeLines whose equalLines will be populated
     * @param arraySize Length of the array being generated
     */
    private void generateArrayOverloadIsEqualMethod(CodeLines code, int arraySize) {

        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            code.equalLines.add("for (int i = 0; i < " + arraySize + "; i++)");
            code.equalLines.add("{");
            code.equalLines.add("\tif (" + varName + "[i] != value." + varName + "[i])");
        } else if (codeType == CodeLines.CodeType.JAVA || codeType == CodeLines.CodeType.C_SHARP) {
            code.equalLines.add("for (int i = 0; i < " + arraySize + "; i++)");
            code.equalLines.add("{");
            code.equalLines.add("\tif (!" + varName + "[i].isEqual(value." + varName + "[i]))");
        }

        code.equalLines.add("\t{");
        code.equalLines.add("\t\treturn false;");
        code.equalLines.add("\t}");
        code.equalLines.add("}");
        code.equalLines.add("return true;");
    }

    /**
     * Adds body code for the inequality check function.
     * @param code CodeLines whose notEqualLines will be populated.
     * @param arraySize Length of the array being generated
     */
    private void generateArrayOverloadNotEqualMethod(CodeLines code, int arraySize) {
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            code.notEqualLines.add("for (unsigned int i = 0; i < " + arraySize + "; i++)");
            code.notEqualLines.add("{");
            code.notEqualLines.add("\tif (" + varName + "[i] != value." + varName + "[i])");
        } else if (codeType == CodeLines.CodeType.JAVA || codeType == CodeLines.CodeType.C_SHARP) {
            code.notEqualLines.add("for (int i = 0; i < " + arraySize + "; i++)");
            code.notEqualLines.add("{");
            code.notEqualLines.add("\tif (!" + varName + "[i].notEquals(value." + varName + "[i]))");
        }

        code.notEqualLines.add("\t{");
        code.notEqualLines.add("\t\treturn false;");
        code.notEqualLines.add("\t}");
        code.notEqualLines.add("}");
        code.notEqualLines.add("return true;");
    }

    private void generateArrayOverloadAssignmentMethod(CodeLines code, int arraySize) {
        /// Overload the operator=
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            code.assignmentLines.add("for (unsigned int i = 0; i < " + arraySize + "; i++)");
            code.assignmentLines.add("{");
            code.assignmentLines.add("\t" + varName + "[i] = value." + varName + "[i];");
            code.assignmentLines.add("}");
        } else if (codeType == CodeLines.CodeType.JAVA) {
            // implement assignment function
            code.assignmentLines.add("for (int i = 0; i < " + arraySize + "; i++)");
            code.assignmentLines.add("{");
            code.assignmentLines.add("\t" + varName + "[i] = value." + varName + "[i];");
            code.assignmentLines.add("}");
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            // implement assignment function
            code.assignmentLines.add("for (int i = 0; i < " + arraySize + "; i++)");
            code.assignmentLines.add("{");
            code.assignmentLines.add("\t" + varName + "[i] = value." + varName + "[i];");
            code.assignmentLines.add("}");
        }
    }
}
