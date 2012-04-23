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
 * @(#)FixedFieldGenerator.java		0.1 2008/09/08
 * 
 * Copyright
 */
package org.jts.codegenerator;

import org.jts.jsidl.binding.*;

import java.util.Vector;
import java.util.List;

/**
 * NOTE: Does not support being nested.
 * 
 * @author Nicholas M. Johnson
 *
 */
public class FixedFieldGenerator
{
	private CodeLines.CodeType codeType;
	private FixedField fixedField;
	private List<Dimension> dimList;
	
	// temp code generation variables
	private Vector<String> methodCode;
	private Vector<String> methodParam;
	private String fieldName;
	private String variableName;
	private String fullClassName;
	private String tempVariableName;
	private String variableType;
    private boolean variableSigned;
	
	/**
	 * Constructor.
	 * 
	 * @param fixedField
	 * @param codeLines
	 */
	public FixedFieldGenerator(CodeLines.CodeType codeType, FixedField fixedField)
	{
		this.fixedField = fixedField;
		this.codeType = codeType;
		this.dimList = null;
		
		// temp code generation variables
        methodCode = new Vector<String>();
        methodParam = new Vector<String>();
        fieldName = Util.upperCaseFirstLetter(fixedField.getName());

        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            variableName = CppCode.getVariableName(fieldName);
            tempVariableName = variableName + "Temp";
            variableType = CppCode.getVariableType(fixedField.getFieldType());
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            variableName = JavaCode.getVariableName(fieldName);
            tempVariableName = variableName + "Temp";
            variableType = JavaCode.getVariableType(fixedField.getFieldType());
            variableSigned = JavaCode.getVariableSign(fixedField.getFieldType());
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            variableName = CSharpCode.getVariableName(fieldName);
            tempVariableName = variableName + "Temp";
            variableType = CSharpCode.getVariableType(fixedField.getFieldType());
        }
    }

	/**
	 * Constructor.
	 * 
	 * @param fixedField
	 * @param codeLines
	 */
	public FixedFieldGenerator(CodeLines.CodeType codeType, FixedField fixedField, List<Dimension> dimList)
	{
		this.fixedField = fixedField;
		this.codeType = codeType;
		this.dimList = dimList;
		
		// temp code generation variables
		methodCode = new Vector<String>();
		methodParam = new Vector<String>();
		fieldName = Util.upperCaseFirstLetter(fixedField.getName());
		
                if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
                {
                    variableName = CppCode.getVariableName(fieldName);
                    tempVariableName = variableName + "Temp";
                    variableType = CppCode.getVariableType(fixedField.getFieldType());
                }
                else if(codeType == CodeLines.CodeType.JAVA)
                {
                    variableName = JavaCode.getVariableName(fieldName);
                    tempVariableName = variableName + "Temp";
                    variableType = JavaCode.getVariableType(fixedField.getFieldType());
                    variableSigned = JavaCode.getVariableSign(fixedField.getFieldType());
                    
                }
                else if(codeType == CodeLines.CodeType.C_SHARP)
                {
                    variableName = CSharpCode.getVariableName(fieldName);
                    tempVariableName = variableName + "Temp";
                    variableType = CSharpCode.getVariableType(fixedField.getFieldType());
                }
        }
	
	
	/**
	 * Creates the code required to access, modify, encode and decode a FixedField 
	 * 
	 * @param parentClassName	the fully qualified name of the parent class (if applicable, if not leave blank)
	 * @param pvIndex			the index of the presence vector this FixedField is tied to (if applicable, if not value is not used)
	 * @param hasClass			if true the FixedField will be placed within a class
	 * @param code				the CodeLines object to fill with the generated code
	 * @throws CodeGeneratorException
	 */
	public void run(String parentClassName, int pvIndex, boolean hasClass, CodeLines code, String initValue) throws CodeGeneratorException
	{
            CodeLines ffCode = new CodeLines("", codeType, code.getNameSpace());

            if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                if (hasClass)
                {
                    fullClassName = parentClassName + "::" + fixedField.getName();
                }
                else
                {
                    fullClassName = parentClassName;
                }
			
                if (dimList == null)
                {
                    if (hasClass)
                    {
                        // NOT IMPLEMENTED
                        // **fixed field doesn't generate its own class
                        throw new CodeGeneratorException("Desired Output is Not Implemented");
                    }
                    else
                    {
                        generateFieldInstance(pvIndex, ffCode, initValue);
                        code.addAll(ffCode);
                    }
                }
                else
                {
                    if (hasClass)
                    {
                        // NOT IMPLEMENTED
                        // **fixed field doesn't generate its own class
                        throw new CodeGeneratorException("Desired Output is Not Implemented");
                    }
                    else
                    {
                        generateFieldArray(pvIndex, ffCode);
                        code.addAll(ffCode);
                    }
                }
            }
            else if (codeType == CodeLines.CodeType.JAVA)
            {
                if (hasClass)
                {
                    fullClassName = parentClassName + "." + fixedField.getName();
                }
                else
                {
                    fullClassName = parentClassName;
                }

                if (dimList == null)
                {
                    if (hasClass)
                    {
                        // NOT IMPLEMENTED
                        // **fixed field doesn't generate its own class
                        throw new CodeGeneratorException("Desired Output is Not Implemented");
                    }
                    else
                    {
                        generateFieldInstance(pvIndex, ffCode, initValue);
                        code.addAll(ffCode);
                    }
                }
                else
                {
                    if (hasClass)
                    {
                        // NOT IMPLEMENTED
                        // **fixed field doesn't generate its own class
                        throw new CodeGeneratorException("Desired Output is Not Implemented");
                    }
                    else
                    {
                        generateFieldArray(pvIndex, ffCode);
                        code.addAll(ffCode);
                    }
                }
            }
            else if (codeType == CodeLines.CodeType.C_SHARP)
            {
                if (hasClass)
                {
                    fullClassName = parentClassName + "." + fixedField.getName();
                }
                else
                {
                    fullClassName = parentClassName;
                }

                if (dimList == null)
                {
                    if (hasClass)
                    {
                        // NOT IMPLEMENTED
                        // **fixed field doesn't generate its own class
                        throw new CodeGeneratorException("Desired Output is Not Implemented");
                    }
                    else
                    {
                        generateFieldInstance(pvIndex, ffCode, initValue);
                        code.addAll(ffCode);
                    }
                }
                else
                {
                    if (hasClass)
                    {
                        // NOT IMPLEMENTED
                        // **fixed field doesn't generate its own class
                        throw new CodeGeneratorException("Desired Output is Not Implemented");
                    }
                    else
                    {
                        generateFieldArray(pvIndex, ffCode);
                        code.addAll(ffCode);
                    }
                }
            }
	}
	
	/**
	 * Generate instance
	 * @param fullClassName
	 * @param pvIndex
	 * @param code
	 * @throws CodeGeneratorException
	 */
    private void generateFieldInstance(int pvIndex, CodeLines code, String initValue) throws CodeGeneratorException
	{
            // create variable
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                code.protectedAttributes.add(CppCode.createVariableDeclaration(variableType, fieldName, false));
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                code.protectedAttributes.add(JavaCode.createVariableDeclaration("protected " + variableType, fieldName, false));
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                code.protectedAttributes.add(CSharpCode.createVariableDeclaration("protected " + variableType, fieldName, true));
            }

            if (initValue != null)
                code.constructorLines.add(variableName + " = " + initValue + ";");
            else
	        code.constructorLines.add(variableName + " = 0;");	

	    generateInstanceGetSizeMethod(code, pvIndex);
	    
	    generateInstanceIsValidMethod(code, pvIndex);
	
	    generateInstanceEncodeMethod(code, pvIndex);
	    generateInstanceDecodeMethod(code, pvIndex);

	    // If the fixed field is a scaled number
	    if (fixedField.getScaleRange() != null)
	    {
	    	ScaleRangeGenerator srGen = new ScaleRangeGenerator(codeType, fixedField.getScaleRange());
	    	
	    	// Generate the code to change the value to a scaled value
	    	/// scaled integer (float to integer and integer to float conversion required)
	    	
	    	generateInstanceGetMethod(code, srGen);
	    	generateInstanceSetMethod(code, pvIndex, srGen);
	    }
	    /// The FixedField is not a Scaled Range
	    else
	    {
	    	generateInstanceGetMethod(code);
	    	generateInstanceSetMethod(code, pvIndex);
	    }
	    
	    generateInstanceOverloadAssignmentMethod(code);
	    generateInstanceOverloadIsEqualMethod(code);
	    generateInstanceOverloadNotEqualMethod(code);
	}
	
	private void generateInstanceGetSizeMethod(CodeLines code, int pvIndex)
	{
            methodCode.clear();
		
	    /// GetSize method
	    /// only add size of array if optional is true

            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                methodCode.add("size += sizeof(" + variableType  + ");");
                if (fixedField.isOptional())
                {
                    methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                }
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                JavaCode.generateGetSizeLines(variableType, methodCode, variableSigned);
                if (fixedField.isOptional())
                {
                    methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                }
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                methodCode.add("size += JausUtils.getNumBytes(\"" + variableType  + "\");");
                if (fixedField.isOptional())
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
	    if (fixedField.isOptional()) 
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

            /*	jUnsignedShortInteger m_DestSubsystemIDTemp;
             *
             *	m_DestSubsystemIDTemp = JSIDL_V0_9::correctEndianess(m_DestSubsystemID);
             *	memcpy(bytes + pos, &m_DestSubsystemIDTemp, sizeof(jUnsignedShortInteger));
             *	pos += sizeof(jUnsignedShortInteger);
             */

            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                methodCode.add(variableType + " " + tempVariableName + ";");
                methodCode.add("");
                methodCode.add(tempVariableName + " = " + code.getNameSpace() + "::correctEndianness(" + variableName + ");");
                methodCode.add("memcpy(bytes + pos, &" + tempVariableName + ", sizeof(" + variableType + "));");
                methodCode.add("pos += sizeof(" + variableType + ");");

                if (fixedField.isOptional())
                {
                    methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                }
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                JavaCode.generateEncoderLines(variableType, variableName, methodCode, variableSigned);

                if (fixedField.isOptional())
                {
                    methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                }
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                CSharpCode.generateEncoderLines(variableType, variableName, methodCode);

                if (fixedField.isOptional())
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
		
	    /* 	jUnsignedShortInteger m_DestSubsystemIDTemp;
	     * 
	     *	memcpy(&m_DestSubsystemIDTemp, bytes + pos, sizeof(jUnsignedShortInteger));
	     *	m_DestSubsystemIDTemp = JSIDL_V0_9::correctEndianess(m_DestSubsystemIDTemp);
	     *	pos += sizeof(jUnsignedShortInteger);
	     */
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                methodCode.add(variableType + " " + tempVariableName + ";");
                methodCode.add("");
                methodCode.add("memcpy(&" + tempVariableName + ", bytes + pos, sizeof(" + variableType + "));");
                methodCode.add(variableName + " = " + code.getNameSpace() + "::correctEndianness(" + tempVariableName + ");");
                methodCode.add("pos += sizeof(" + variableType + ");");

                if (fixedField.isOptional())
                {
                    methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                }
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                JavaCode.generateDecoderLines(variableType, variableName, methodCode, variableSigned);

                if (fixedField.isOptional())
                {
                    methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                }
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {

                CSharpCode.generateDecoderLines(variableType, variableName, methodCode);

                if (fixedField.isOptional())
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
	
	private void generateInstanceGetMethod(CodeLines code, ScaleRangeGenerator srGen)
	{
            methodCode.clear();

            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                ///  Generate getMethod Declaration and Definition
                code.publicMethods.add(CppCode.createMethodDeclaration("double", "get", fieldName, null, false));

                methodCode.add("double value;");
                methodCode.add("");
                srGen.getIntToDoubleConversion(fixedField.getFieldType(), variableName, "value", methodCode);
                methodCode.add("");
                methodCode.add("return value;");

                code.methods.addAll(CppCode.createMethodDefinition("double", fullClassName + "::get", fieldName, methodParam, methodCode, false));
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                methodCode.add("double value;");
                methodCode.add("");
                srGen.getIntToDoubleConversion(fixedField.getFieldType(), variableName, "value", methodCode);
                methodCode.add("");
                methodCode.add("return value;");

                code.methods.addAll(JavaCode.createMethodDefinition("public double", "get", fieldName, methodParam, methodCode, false));
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                methodCode.add("double value;");
                methodCode.add("");
                srGen.getIntToDoubleConversion(fixedField.getFieldType(), variableName, "value", methodCode);
                methodCode.add("");
                methodCode.add("return value;");

                code.methods.addAll(CSharpCode.createMethodDefinition("public double", "get", fieldName, methodParam, methodCode, false));
            }
	}
	
	private void generateInstanceGetMethod(CodeLines code)
	{
            methodCode.clear();
		
            /* Create the get Method Declaration and Definitions */
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                code.publicMethods.add(CppCode.createMethodDeclaration(variableType, "get", fieldName, methodParam, false));

            // Add the following to the C++ source file
            /* jUnsignedShortInteger SendRec::getDestSubsystemID()
             * {
             * 		return m_DestSubsystemID;
             * };
             */
                methodCode.add("return " + variableName + ";");
                code.methods.addAll(CppCode.createMethodDefinition(variableType, fullClassName + "::get", fieldName, methodParam, methodCode, false));
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                methodCode.add("return " + variableName + ";");
                code.methods.addAll(JavaCode.createMethodDefinition("public " + variableType, "get", fieldName, methodParam, methodCode, false));
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                methodCode.add("return " + variableName + ";");
                code.methods.addAll(CSharpCode.createMethodDefinition("public " + variableType, "get", fieldName, methodParam, methodCode, false));
            }
	}
	
	private void generateInstanceSetMethod(CodeLines code, int pvIndex, ScaleRangeGenerator srGen)
	{
            methodCode.clear();
            methodParam.clear();
		
            ///  Generate setMethod Declaration and Definition
            methodParam.add("double value");

            if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                code.publicMethods.add(CppCode.createMethodDeclaration("int", "set", fieldName, methodParam, false));
            }

            srGen.getDoubleToIntConversion(fixedField.getFieldType(), variableName, "value", methodCode);
            if (fixedField.isOptional())
            {
        	methodCode.add("setPresenceVector("+ pvIndex +");");
            }
            

            if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                // Create framework for parent reference
                methodCode.add(CppCode.getParentReferenceSetParentPVLine());

                methodCode.add("return 0;");
                srGen.addRangeCheck("value", methodCode);
                methodCode.add("return 1;");
                code.methods.addAll(CppCode.createMethodDefinition("int", fullClassName + "::set", fieldName, methodParam, methodCode, false));
            }
            else if (codeType == CodeLines.CodeType.JAVA)
            {
                // Create framework for parent reference
                methodCode.add(JavaCode.getParentReferenceSetParentPVLine());

                srGen.addRangeCheck("value", methodCode);
                methodCode.add("return;");
                code.methods.addAll(JavaCode.createMethodDefinition("public void", "set", fieldName, methodParam, methodCode, false));
            }
            else if (codeType == CodeLines.CodeType.C_SHARP)
            {
                // Create framework for parent reference
                methodCode.add(CSharpCode.getParentReferenceSetParentPVLine());

                srGen.addRangeCheck("value", methodCode);
                methodCode.add("return;");
                code.methods.addAll(CSharpCode.createMethodDefinition("public void", "set", fieldName, methodParam, methodCode, false));
            }
	}
	
	private void generateInstanceSetMethod(CodeLines code, int pvIndex)
	{
            methodCode.clear();
            methodParam.clear();
		
            /* Create the set Method Declaration and Definitions */
            methodParam.add(variableType + " value");
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                code.publicMethods.add(CppCode.createMethodDeclaration("int", "set", fieldName, methodParam, false));
            }

            // Add the following to the C++ source file
            /* int SendRec::setDestSubsystemID(jUnsignedShortInteger value)
             * {
             * 		m_DestSubsystemID = value;
             * 		return 0;
             * };
             */
            methodCode.add(variableName + " = value;");
            if (fixedField.isOptional())
            {
        	methodCode.add("setPresenceVector("+ pvIndex +");");
            }

            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                // Create framework for parent reference
                methodCode.add(CppCode.getParentReferenceSetParentPVLine());

                methodCode.add("return 0;");

                if (fixedField.getValueSet() != null)
                {
                new ValueSetGenerator(codeType, fixedField.getValueSet()).addRangeCheck("value", methodCode);
                methodCode.add("return 1;");
                }

                code.methods.addAll(CppCode.createMethodDefinition("int", fullClassName + "::set", fieldName, methodParam, methodCode, false));
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                // Create framework for parent reference
                methodCode.add(JavaCode.getParentReferenceSetParentPVLine());

                if (fixedField.getValueSet() != null)
                {
                new ValueSetGenerator(codeType, fixedField.getValueSet()).addRangeCheck("value", methodCode);
                methodCode.add("return;");
                }
                code.methods.addAll(JavaCode.createMethodDefinition("public void", "set", fieldName, methodParam, methodCode, false));
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                // Create framework for parent reference
                methodCode.add(CSharpCode.getParentReferenceSetParentPVLine());

                if (fixedField.getValueSet() != null)
                {
                new ValueSetGenerator(codeType, fixedField.getValueSet()).addRangeCheck("value", methodCode);
                methodCode.add("return;");
                }
                code.methods.addAll(CSharpCode.createMethodDefinition("public void", "set", fieldName, methodParam, methodCode, false));
            }
	}
	
	private void generateInstanceOverloadAssignmentMethod(CodeLines code)
	{
            code.assignmentLines.add(variableName + " = value." + variableName + ";");
	}
	
    private void generateInstanceOverloadIsEqualMethod(CodeLines code) {
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            /// Overload operator==
            code.equalLines.add("if (" + variableName + " != value." + variableName + ")");
            code.equalLines.add("{");
            code.equalLines.add("\treturn false;");
            code.equalLines.add("}");
        } else if (codeType == CodeLines.CodeType.JAVA) {
            /// implement isEqual method
            code.equalLines.add("if (" + variableName + " != value.get" + Util.upperCaseFirstLetter(fieldName) + "())");
            code.equalLines.add("{");
            code.equalLines.add("\treturn false;");
            code.equalLines.add("}");
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            /// implement isEqual method
            code.equalLines.add("if (this.get" + variableName.substring(variableName.indexOf(CSharpCode.VARIABLE_PREFIX) + 2)
                    + "() != value.get" + variableName.substring(variableName.indexOf(CSharpCode.VARIABLE_PREFIX) + 2) + "())");
            code.equalLines.add("{");
            code.equalLines.add("\treturn false;");
            code.equalLines.add("}");
        }

    }

    private void generateInstanceOverloadNotEqualMethod(CodeLines code) {
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            /// Overload operator!=
            code.notEqualLines.add("if (" + variableName + " == value." + variableName + ")");
            code.notEqualLines.add("{");
            code.notEqualLines.add("\treturn false;");
            code.notEqualLines.add("}");
        } else if (codeType == CodeLines.CodeType.JAVA) {
            /// implement notEquals method
            code.notEqualLines.add("if (" + variableName + " == value.get" + Util.upperCaseFirstLetter(fieldName) + "())");
            code.notEqualLines.add("{");
            code.notEqualLines.add("\treturn false;");
            code.notEqualLines.add("}");
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            /// implement notEquals method
            code.notEqualLines.add("if (this.get" + variableName.substring(variableName.indexOf(CSharpCode.VARIABLE_PREFIX) + 2)
                    + "() == value.get" + variableName.substring(variableName.indexOf(CSharpCode.VARIABLE_PREFIX) + 2) + "())");
            code.notEqualLines.add("{");
            code.notEqualLines.add("\treturn false;");
            code.notEqualLines.add("}");
        }
    }
	
	/**
	 * Generate array
	 * @param fullClassName
	 * @param pvIndex
	 * @param code
	 * @throws CodeGeneratorException
	 */
	private void generateFieldArray(int pvIndex, CodeLines code) throws CodeGeneratorException
    {		
        Vector<String> getMethodCode = new Vector<String>();
        Vector<String> setMethodCode = new Vector<String>();
        Vector<String> paramCode = new Vector<String>();

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

            // create variable
            code.protectedAttributes.add(CppCode.createVariableDeclaration(variableType, fieldName + "[" + arraySize + "]", false));
            code.constructorLines.add("memset( " + variableName + ", 0, sizeof(" + variableType + ") * " + arraySize + ");");
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            for (int i = 0; i < dimList.size(); i++)
            {
                Dimension dim = dimList.get(i);

                String dimName = Util.upperCaseFirstLetter(dim.getName());
                String dimSizeName = dimName + "Size";
                String dimVarName = JavaCode.getVariableName(dimSizeName);

				code.protectedAttributes.add(JavaCode.createVariableDeclaration("int", dimSizeName, false));
                code.constructorLines.add(dimVarName + " = " + dim.getSize() + ";");

                ///  Generate getMethod Declaration and Definition

                methodCode.clear();
                methodCode.add("return " + dimVarName + ";");
                code.methods.addAll(JavaCode.createMethodDefinition("public int", "get", dimSizeName, null, methodCode, true));

                /// Add the dimension to the method parameter vector for the get and set methods
                paramCode.add("int " + dimName);

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

            // create variable
            code.protectedAttributes.add(JavaCode.createVariableDeclaration("protected " + variableType + "[]", fieldName + " = new " + variableType + "[" + arraySize + "]", false));
            //code.constructorLines.add(variableName + " = 0;");
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

                ///  Generate getMethod Declaration and Definition

                methodCode.clear();
                methodCode.add("return " + dimVarName + ";");
                code.methods.addAll(CSharpCode.createMethodDefinition("public  int", "get", dimSizeName, null, methodCode, true));

                /// Add the dimension to the method parameter vector for the get and set methods
                paramCode.add("int " + dimName);

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

            // create variable
            code.protectedAttributes.add(CSharpCode.createVariableDeclaration("protected " + variableType + "[]", fieldName + " = new " + variableType + "[" + arraySize + "]", true));
        }

        generateArrayGetSizeMethod(code, pvIndex, arraySize);
	    
        generateArrayIsValidMethod(code, pvIndex);
        
        generateArrayEncodeMethod(code, pvIndex, arraySize);
        generateArrayDecodeMethod(code, pvIndex, arraySize);
        
        // If the fixed field is a scaled number
        if (fixedField.getScaleRange() != null)
        {
            ScaleRangeGenerator srGen = new ScaleRangeGenerator(codeType, fixedField.getScaleRange());
        	
            // Generate the code to change the value to a scaled value
            /// scaled integer (float to integer and integer to float conversion required)

            generateArrayGetMethod(code, posCalc, srGen, paramCode);
            generateArraySetMethod(code, pvIndex, posCalc, srGen, paramCode);
        }
        /// The FixedField is not a Scaled Range
        else
        {
            generateArrayGetMethod(code, posCalc, paramCode);
            generateArraySetMethod(code, pvIndex, posCalc, paramCode);
        }
        
        generateArrayOverloadAssignmentMethod(code, arraySize);
        generateArrayOverloadIsEqualMethod(code, arraySize);
    	generateArrayOverloadNotEqualMethod(code, arraySize);
    }
	
	private void generateArrayGetSizeMethod(CodeLines code, int pvIndex, int arraySize)
	{
            methodCode.clear();
		
	    /// GetSize method
	    /// only add size of array if optional is true
            if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                methodCode.add("size += sizeof(" + variableType  + ") * " + arraySize + ";");
                if (fixedField.isOptional())
                {
                    methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                }
            }
            else if (codeType == CodeLines.CodeType.JAVA)
            {
                JavaCode.generateGetSizeLines(variableType, methodCode, arraySize, variableSigned);
                if (fixedField.isOptional())
                {
                    methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                }
            }
            else if (codeType == CodeLines.CodeType.C_SHARP)
            {
                methodCode.add("size += JausUtils.getNumBytes(\"" + variableType + "\") * " + arraySize + ";");
                if (fixedField.isOptional())
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
	    if (fixedField.isOptional()) 
	    {
	    	methodCode.clear();
	    	
	    	code.publicMethods.add(CppCode.createMethodDeclaration("bool", "is", fieldName + "Valid", null, true));
	        methodCode.add("return true;");
	        methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
	        methodCode.add("return false;");
	        code.methods.addAll(CppCode.createMethodDefinition("bool", fullClassName + "::is", fieldName + "Valid", null, methodCode, true));
	    }
	}
	
	private void generateArrayEncodeMethod(CodeLines code, int pvIndex, int arraySize)
	{
            methodCode.clear();
		
            /*	jUnsignedShortInteger m_DestSubsystemIDTemp;
             *
             *	m_DestSubsystemIDTemp = JSIDL_V0_9::correctEndianess(m_DestSubsystemID);
             *	memcpy(bytes + pos, &m_DestSubsystemIDTemp, sizeof(jUnsignedShortInteger));
             *	pos += sizeof(jUnsignedShortInteger);
             */
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                methodCode.add(variableType + " " + tempVariableName + ";");
                methodCode.add("");
                methodCode.add("for (unsigned int i = 0; i < "  + arraySize + "; i++)");
                methodCode.add("{");
                methodCode.add("\t" + tempVariableName + " = " + code.getNameSpace() + "::correctEndianness(" + variableName + "[i]);");
                methodCode.add("\tmemcpy(bytes + pos, &" + tempVariableName + ", sizeof(" + variableType + "));");
                methodCode.add("\tpos += sizeof(" + variableType + ");");
                methodCode.add("}");
                if (fixedField.isOptional())
                {
                    methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                }
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                methodCode.add("");
                methodCode.add("for (int i = 0; i < "  + arraySize + "; i++)");
                methodCode.add("{");
                JavaCode.generateEncoderLines(variableType, variableName + "[i]", methodCode, variableSigned);
                methodCode.add("}");
                if (fixedField.isOptional())
                {
                    methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                }
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                methodCode.add("");
                methodCode.add("for (uint i = 0; i < "  + arraySize + "; i++)");
                methodCode.add("{");
                CSharpCode.generateEncoderLines(variableType, variableName + "[i]", methodCode);
                methodCode.add("}");
                if (fixedField.isOptional())
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
	
	private void generateArrayDecodeMethod(CodeLines code, int pvIndex, int arraySize)
	{
            methodCode.clear();

            /* 	jUnsignedShortInteger m_DestSubsystemIDTemp;
             *
             * for (unsigned int i = 0; i < "arraySize"; i++)
             * {
             *		memcpy(&m_DestSubsystemIDTemp, bytes + pos, sizeof(jUnsignedShortInteger));
             *		m_DestSubsystemIDTemp = JSIDL_V0_9::correctEndianess(m_DestSubsystemIDTemp);
             *		pos += sizeof(jUnsignedShortInteger);
             * }
             */
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                methodCode.add(variableType + " " + tempVariableName + ";");
                methodCode.add("");
                methodCode.add("for (unsigned int i = 0; i < "  + arraySize + "; i++)");
                methodCode.add("{");
                methodCode.add("\tmemcpy(&" + tempVariableName + ", bytes + pos, sizeof(" + variableType + "));");
                methodCode.add("\t" + variableName + "[i] = " + code.getNameSpace() + "::correctEndianness(" + tempVariableName + ");");
                methodCode.add("\tpos += sizeof(" + variableType + ");");
                methodCode.add("}");
                if (fixedField.isOptional())
                {
                    methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                }
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                methodCode.add("");
                methodCode.add("try");
                methodCode.add("{");
                methodCode.add("\tfor (int i = 0; i < "  + arraySize + "; i++)");
                methodCode.add("\t{");
                JavaCode.generateDecoderLines(variableType, variableName + "[i]", methodCode, variableSigned);
                methodCode.add("\t}");
                methodCode.add("}");
                methodCode.add("catch(Exception e)");
                methodCode.add("{");
                methodCode.add("\tlogger.log(Level.SEVERE, null, e);");
                methodCode.add("}");
                if (fixedField.isOptional())
                {
                    methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                }
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                //methodCode.add(variableType + " " + tempVariableName + ";");
                methodCode.add("");
                methodCode.add("for (int i = 0; i < "  + arraySize + "; i++)");
                methodCode.add("{");
                CSharpCode.generateDecoderLines(variableType, variableName + "[i]", methodCode);
                methodCode.add("}");
                if (fixedField.isOptional())
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
            methodCode.clear();
	}
	
	private void generateArrayGetMethod(CodeLines code, String posCalc, ScaleRangeGenerator srGen, Vector<String> paramCode)
	{
            methodCode.clear();
		
            ///  Generate getMethod Declaration and Definition
            //methodParam.clear();
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
		code.publicMethods.add(CppCode.createMethodDeclaration("double", "get", fieldName, paramCode, false));
      
		methodCode.add("double value;");
		methodCode.add("unsigned int index = " + posCalc + ";");
		methodCode.add("");
		srGen.getIntToDoubleConversion(fixedField.getFieldType(), variableName + "[index]", "value", methodCode);
		methodCode.add("");
		methodCode.add("return value;");

		code.methods.addAll(CppCode.createMethodDefinition("double", fullClassName + "::get", fieldName, paramCode, methodCode, false));
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
		methodCode.add("double value;");
		methodCode.add("int index = " + posCalc + ";");
		methodCode.add("");
		srGen.getIntToDoubleConversion(fixedField.getFieldType(), variableName + "[index]", "value", methodCode);
		methodCode.add("");
		methodCode.add("return value;");

		code.methods.addAll(JavaCode.createMethodDefinition("public double", "get", fieldName, paramCode, methodCode, false));
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
		methodCode.add("double value;");
		methodCode.add("int index = " + posCalc + ";");
		methodCode.add("");
		srGen.getIntToDoubleConversion(fixedField.getFieldType(), variableName + "[index]", "value", methodCode);
		methodCode.add("");
		methodCode.add("return value;");

		code.methods.addAll(CSharpCode.createMethodDefinition("public double", "get", fieldName, paramCode, methodCode, false));
            }
	}
	
	private void generateArraySetMethod(CodeLines code, int pvIndex, String posCalc, ScaleRangeGenerator srGen, Vector<String> paramCode)
	{
            methodCode.clear();
		
            ///  Generate setMethod Declaration and Definition
            paramCode.add("double value");
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                code.publicMethods.add(CppCode.createMethodDeclaration("int", "set", fieldName, paramCode, false));

                methodCode.add("unsigned int index = " + posCalc + ";");
                methodCode.add("");
                srGen.getDoubleToIntConversion(fixedField.getFieldType(), variableName + "[index]", "value", methodCode);
                if (fixedField.isOptional())
                {
                    methodCode.add("setPresenceVector("+ pvIndex +");");
                }
            	// Create framework for parent reference
            	methodCode.add(CppCode.getParentReferenceSetParentPVLine());
                methodCode.add("return 0;");
                srGen.addRangeCheck("value", methodCode);
                methodCode.add("return 1;");

                code.methods.addAll(CppCode.createMethodDefinition("int", fullClassName + "::set", fieldName, paramCode, methodCode, false));
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                methodCode.add("int index = " + posCalc + ";");
                methodCode.add("");
                srGen.getDoubleToIntConversion(fixedField.getFieldType(), variableName + "[index]", "value", methodCode);
                if (fixedField.isOptional())
                {
                    methodCode.add("setPresenceVector("+ pvIndex +");");
                }
                // Create framework for parent reference
            	methodCode.add(JavaCode.getParentReferenceSetParentPVLine());
                srGen.addRangeCheck("value", methodCode);

                code.methods.addAll(JavaCode.createMethodDefinition("public void", "set", fieldName, paramCode, methodCode, false));
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                methodCode.add("int index = " + posCalc + ";");
                methodCode.add("");
                srGen.getDoubleToIntConversion(fixedField.getFieldType(), variableName + "[index]", "value", methodCode);
                if (fixedField.isOptional())
                {
                    methodCode.add("setPresenceVector("+ pvIndex +");");
                }
                // Create framework for parent reference
            	methodCode.add(CSharpCode.getParentReferenceSetParentPVLine());
                srGen.addRangeCheck("value", methodCode);

                code.methods.addAll(CSharpCode.createMethodDefinition("public void", "set", fieldName, paramCode, methodCode, false));
            }
	}
	
	private void generateArrayGetMethod(CodeLines code, String posCalc, Vector<String> paramCode)
	{
            methodCode.clear();
		
            /* Create the get Method Declaration and Definitions */
    //    	methodParam.clear();
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                code.publicMethods.add(CppCode.createMethodDeclaration(variableType, "get", fieldName, paramCode, false));

                // Add the following to the C++ source file
                /* jUnsignedShortInteger SendRec::getDestSubsystemID()
                 * {
                 * 		return m_DestSubsystemID;
                 * };
                 */
                methodCode.add("unsigned int index = " + posCalc + ";");
                methodCode.add("");
                methodCode.add("return " + variableName + "[index];");
                code.methods.addAll(CppCode.createMethodDefinition(variableType, fullClassName + "::get", fieldName, paramCode, methodCode, false));
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                methodCode.add("int index = " + posCalc + ";");
                methodCode.add("");
                methodCode.add("return " + variableName + "[index];");
                code.methods.addAll(JavaCode.createMethodDefinition("public " +variableType, "get", fieldName, paramCode, methodCode, false));
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                methodCode.add("int index = " + posCalc + ";");
                methodCode.add("");
                methodCode.add("return " + variableName + "[index];");
                code.methods.addAll(CSharpCode.createMethodDefinition("public " +variableType, "get", fieldName, paramCode, methodCode, false));
            }
	}

	private void generateArraySetMethod(CodeLines code, int pvIndex, String posCalc, Vector<String> paramCode)
	{
            methodCode.clear();
		
            /* Create the set Method Declaration and Definitions */
    //    	methodParam.clear();
            paramCode.add(variableType + " value");
            if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                code.publicMethods.add(CppCode.createMethodDeclaration("int", "set", fieldName, paramCode, false));

                // Add the following to the C++ source file
                /* int SendRec::setDestSubsystemID(jUnsignedShortInteger value)
                 * {
                 * 		m_DestSubsystemID = value;
                 * 		return 0;
                 * };
                 */
                methodCode.add("unsigned int index = " + posCalc + ";");
                methodCode.add("");
                methodCode.add(variableName + "[index] = value;");
            }
            else if (codeType == CodeLines.CodeType.JAVA)
            {
                methodCode.add("int index = " + posCalc + ";");
                methodCode.add("");
                methodCode.add(variableName + "[index] = value;");
            }
            else if (codeType == CodeLines.CodeType.C_SHARP)
            {
                methodCode.add("int index = " + posCalc + ";");
                methodCode.add("");
                methodCode.add(variableName + "[index] = value;");
            }

            if (fixedField.isOptional())
            {
        	methodCode.add("setPresenceVector("+ pvIndex +");");
            }



            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                // Create framework for parent reference
                methodCode.add(CppCode.getParentReferenceSetParentPVLine());

                methodCode.add("return 0;");

                if (fixedField.getValueSet() != null)
                {
                    new ValueSetGenerator(codeType, fixedField.getValueSet()).addRangeCheck("value", methodCode);
                    methodCode.add("return 1;");
                }
                code.methods.addAll(CppCode.createMethodDefinition("int", fullClassName + "::set", fieldName, paramCode, methodCode, false));
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                // Create framework for parent reference
                methodCode.add(JavaCode.getParentReferenceSetParentPVLine());

                if (fixedField.getValueSet() != null)
                {
                    new ValueSetGenerator(codeType, fixedField.getValueSet()).addRangeCheck("value", methodCode);
                    methodCode.add("return;");
                }
                code.methods.addAll(JavaCode.createMethodDefinition("public void", "set", fieldName, paramCode, methodCode, false));
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                // Create framework for parent reference
                methodCode.add(CSharpCode.getParentReferenceSetParentPVLine());

                if (fixedField.getValueSet() != null)
                {
                    new ValueSetGenerator(codeType, fixedField.getValueSet()).addRangeCheck("value", methodCode);
                    methodCode.add("return;");
                }
                code.methods.addAll(CSharpCode.createMethodDefinition("public void", "set", fieldName, paramCode, methodCode, false));
            }
	}
	
	private void generateArrayOverloadAssignmentMethod(CodeLines code, int arraySize)
	{
            /// Overload the operator=
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                code.assignmentLines.add("memcpy(" + variableName + ", value." + variableName + ", sizeof(" + variableType + ") * " + arraySize + ");");
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                code.assignmentLines.add(variableName + " = value." + variableName + ";");
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                code.assignmentLines.add(variableName + " = value." + variableName + ";");
            }
	}
	
	private void generateArrayOverloadIsEqualMethod(CodeLines code, int arraySize)
	{
            /// Overload operator==
            if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
            /// if (memcmp(varName, value.varName, sizeof(varType) * arraySize) != 0)
                code.equalLines.add("if (memcmp(" + variableName + ", value." + variableName + ", sizeof(" + variableType + ") * " + arraySize + ") != 0)");
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                code.equalLines.add("if(!java.util.Arrays.equals(" + variableName + ", value." + variableName + "))");

            }
            else if (codeType == CodeLines.CodeType.C_SHARP)
            {
                code.equalLines.add("if(!this." + variableName + ".SequenceEqual(value." + variableName + "))");
            }
            code.equalLines.add("{");
            code.equalLines.add("\treturn false;");
            code.equalLines.add("}");
            code.equalLines.add("");
            code.equalLines.add("return true;");
	}
	
	private void generateArrayOverloadNotEqualMethod(CodeLines code, int arraySize)
	{
            /// Overload operator!=
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                code.notEqualLines.add("if (memcmp(" + variableName + ", value." + variableName + ", sizeof(" + variableType + ") * " + arraySize + ") == 0)");
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                code.notEqualLines.add("if (java.util.Arrays.equals(" + variableName + ", value." + variableName + "))");
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                code.notEqualLines.add("if(this." + variableName + ".SequenceEqual(value." + variableName + "))");
            }
            code.notEqualLines.add("{");
            code.notEqualLines.add("\treturn false;");
            code.notEqualLines.add("}");
            code.notEqualLines.add("");
            code.notEqualLines.add("return true;");
	}
}
