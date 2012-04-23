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
 * @(#)BitFieldGenerator.java		0.1 2008/09/03
 * 
 * Copyright
 */
package org.jts.codegenerator;

import java.util.ArrayList;

import java.util.List;
import org.jts.jsidl.binding.BitField;
import org.jts.jsidl.binding.Dimension;
import org.jts.jsidl.binding.SubField;
import org.jts.jsidl.binding.ValueSet;

// TODO: write CPPUnit Tests for operator=, operator==, operator!=
/**
 * 
 * This class generates the code required to implement a BitField in the desired language. 
 * 
 * @version 0.1 3 September 2008 
 * @author Nicholas M. Johnson
 * @author Gregory Garcia
 *
 */
public class BitFieldGenerator //extends FieldClass
{
    private CodeLines.CodeType codeType;
    private BitField bitField;
    private List<Dimension> dimList;
    // temp code generation variables
    private String bfType;
    private List<String> methodCode;
    private List<String> methodParam;
    private String fullClassName;
    private String shortClassName;
    private String parentClassName;
    private String varName;
    private boolean varSigned;
    private boolean bfSigned;
	
    /**
     *
     * @param codeType
     * @param bitField
     */
    public BitFieldGenerator(CodeLines.CodeType codeType, BitField bitField)
    {
        this.codeType = codeType;
       	this.bitField = bitField;
       	this.dimList = null;
       	if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            bfType = CppCode.getVariableType(bitField.getFieldTypeUnsigned());
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            bfType = JavaCode.getVariableType(bitField.getFieldTypeUnsigned());
            bfSigned = JavaCode.getVariableSign(bitField.getFieldTypeUnsigned());
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            bfType = CSharpCode.getVariableType(bitField.getFieldTypeUnsigned());
        }

       	methodCode = new ArrayList<String>();
       	methodParam = new ArrayList<String>();
    }

	
	/**
	 * 
	 * @param codeType
	 * @param bitField
	 */
    public BitFieldGenerator(CodeLines.CodeType codeType, BitField bitField, List<Dimension> dimList)
    {
        this.codeType = codeType;
       	this.bitField = bitField;
       	this.dimList = dimList;
       	
        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            bfType = CppCode.getVariableType(bitField.getFieldTypeUnsigned());
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            bfType = JavaCode.getVariableType(bitField.getFieldTypeUnsigned());
            bfSigned = JavaCode.getVariableSign(bitField.getFieldTypeUnsigned());
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            bfType = CSharpCode.getVariableType(bitField.getFieldTypeUnsigned());
        }       	
        methodCode = new ArrayList<String>();
       	methodParam = new ArrayList<String>();
    }
	
	
    /**
     *
     * @param pvIndex
     * @param hasClass
     * @param code
     * @throws CodeGeneratorException
     */
    public void run(String parentName, int pvIndex, boolean hasClass, CodeLines code) throws CodeGeneratorException
    {
        if(!hasClass)
        {
	        // NOT IMPLEMENTED
	        throw new CodeGeneratorException("Desired Output is Not Implemented");
        }

        CodeLines vfCode = new CodeLines("", codeType, code.getNameSpace());
		
    	if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
    	{
            fullClassName = parentName + "::" + bitField.getName();
            shortClassName = CppCode.getShortClassName(fullClassName);
            parentClassName = CppCode.getParentClassName(fullClassName);
            varName = CppCode.getVariableName(shortClassName);

            /*
             * Create framework for parent reference
             */
           	CppCode.addParentReference(code, fullClassName, pvIndex, bitField.isOptional());
    	}
        else if (codeType == CodeLines.CodeType.JAVA)
    	{
            fullClassName = parentName + "." + bitField.getName();
            shortClassName = JavaCode.getShortClassName(fullClassName);
            parentClassName = JavaCode.getParentClassName(fullClassName);
            varName = JavaCode.getVariableName(shortClassName);

            /*
             * Create framework for parent reference
             */
           	JavaCode.addParentReference(code, fullClassName, pvIndex, bitField.isOptional());
    	}
        else if (codeType == CodeLines.CodeType.C_SHARP)
    	{
            fullClassName = parentName + "." + bitField.getName();
            shortClassName = CSharpCode.getShortClassName(fullClassName);
            parentClassName = CSharpCode.getParentClassName(fullClassName);
            varName = CSharpCode.getVariableName(shortClassName);

            /*
             * Create framework for parent reference
             */
           	CSharpCode.addParentReference(code, fullClassName, pvIndex, bitField.isOptional());
    	}
    	
        generateFieldCode(fullClassName, pvIndex, vfCode);
        code.addAll(vfCode);
        
        /// Add a class wrapper around the generated code
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
     * Generate code for a single bit field occurrence
     * @param fullClassName
     * @param pvIndex
     * @param code
     * @throws CodeGeneratorException
     */
    private void generateFieldCode(String fullClassName, int pvIndex, CodeLines code) throws CodeGeneratorException
    {
        CodeLines bfCode = new CodeLines("", codeType, code.getNameSpace());

        generateFieldEncodeMethod(bfCode);
        generateFieldDecodeMethod(bfCode);

        // create variable
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            bfCode.protectedAttributes.add(CppCode.createVariableDeclaration(bfType, "subFields", false));
            bfCode.constructorLines.add("m_SubFields = 0;");
            bfCode.sizeLines.add("size += sizeof(" + bfType + ");");
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            bfCode.protectedAttributes.add(JavaCode.createVariableDeclaration("protected " + bfType, "subFields", false));
            bfCode.constructorLines.add("m_SubFields = 0;");
            JavaCode.generateGetSizeLines(bfType, bfCode.sizeLines, bfSigned);
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            bfCode.protectedAttributes.add(CSharpCode.createVariableDeclaration("protected " + bfType, "subFields", true));
            bfCode.constructorLines.add("m_SubFields = 0;");
            bfCode.sizeLines.add("size += JausUtils.getNumBytes(\"" + bfType + "\");");
        }

        String subFieldName = "";
        
        for (SubField subField : bitField.getSubField())
        {
            int fromIndex = subField.getBitRange().getFromIndex();
            int toIndex = subField.getBitRange().getToIndex();
            int size = (toIndex + 1)- fromIndex;

            subFieldName = Util.upperCaseFirstLetter(subField.getName());

            generateFieldGetMethod(bfCode, fromIndex, toIndex, size, subFieldName);
            generateFieldSetMethod(bfCode, fromIndex, toIndex, size, subFieldName, subField.getValueSet());
        }
        
        generateFieldOverloadAssignmentMethod(bfCode);
        generateFieldOverloadIsEqualMethod(bfCode, subFieldName);
        generateFieldOverloadNotEqualMethod(bfCode, subFieldName);
        
        code.addAll(bfCode);
    }
			
    private void generateFieldEncodeMethod(CodeLines code)
    {
    	/*	jUnsignedInteger m_SubFieldsTemp;
    	 * 
    	 *	m_SubFieldsTemp = JSIDL_v_1_0::correctEndianess(m_SubFields);
    	 *	memcpy(bytes + pos, &m_SubFieldsTemp, sizeof(jUnsignedInteger));
    	 *	pos += sizeof(jUnsignedInteger);
    	 */
        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            code.encoderLines.add(bfType + " m_SubFieldsTemp;");
            code.encoderLines.add("");
            code.encoderLines.add("m_SubFieldsTemp = " + code.getNameSpace() + "::correctEndianness(m_SubFields);");
            code.encoderLines.add("memcpy(bytes + pos, &m_SubFieldsTemp, sizeof(" + bfType + "));");
            code.encoderLines.add("pos += sizeof(" + bfType + ");");
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            code.encoderLines.add("");
            JavaCode.generateEncoderLines(bfType, "m_SubFields", code.encoderLines, bfSigned);
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            code.encoderLines.add(bfType + " m_SubFieldsTemp;");
            code.encoderLines.add("");
            CSharpCode.generateEncoderLines(bfType, "m_SubFields", code.encoderLines);
        }
    }
	
    private void generateFieldDecodeMethod(CodeLines code)
    {
        /* 	jUnsignedInteger m_SubFieldsTemp;
         * 
         *	memcpy(&m_SubFieldsTemp, bytes + pos, sizeof(jUnsignedInteger));
         *	m_SubFields = JSIDL_v_1_0::correctEndianess(m_SubFieldsTemp);
         *	pos += sizeof(jUnsignedInteger);
         */
        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            code.decoderLines.clear();
            code.decoderLines.add(bfType + " m_SubFieldsTemp;");
            code.decoderLines.add("");
            code.decoderLines.add("memcpy(&m_SubFieldsTemp, bytes + pos, sizeof(" + bfType + "));");
            code.decoderLines.add("m_SubFields = " + code.getNameSpace() + "::correctEndianness(m_SubFieldsTemp);");
            code.decoderLines.add("pos += sizeof(" + bfType + ");");
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            code.decoderLines.clear();
            JavaCode.generateDecoderLines(bfType, "m_SubFields", code.decoderLines, bfSigned);
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            code.decoderLines.clear();
            CSharpCode.generateDecoderLines(bfType, "m_SubFields", code.decoderLines);
        }
    }
	
    private void generateFieldGetMethod(CodeLines code, int fromIndex, int toIndex, int size, String subFieldName)
    {
    	///  Generate getMethod Declaration and Definition
    	methodParam.clear();
    	methodCode.clear();
    	//code.publicMethods.add(CppCode.createMethodDeclaration(bfType, "get", subField.getName(), methodParam, false));

        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            code.publicMethods.add(CppCode.createMethodDeclaration(bfType, "get", subFieldName, methodParam, false));

            methodCode.add("std::bitset<sizeof(" + bfType + ") * 8> bfbs((int)m_SubFields);");
            methodCode.add("std::bitset<" + size +"> sfbs;");
            methodCode.add("int i = 0;");
            methodCode.add("");
            methodCode.add("for (int index = " + fromIndex + "; index <= " + toIndex + "; index++)");
            methodCode.add("{");
            methodCode.add("    sfbs[i++] = bfbs[index];");
            methodCode.add("}");
            methodCode.add("");
            methodCode.add("return (" + bfType + ")(sfbs.to_ulong());");
            code.methods.addAll(CppCode.createMethodDefinition(bfType, fullClassName + "::get", subFieldName, methodParam, methodCode, false));
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            methodCode.add("BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);");
            methodCode.add("BitSet subFieldBitSet = new BitSet(" + size +");");
            methodCode.add("int i = 0;");
            methodCode.add("");
            methodCode.add("for (int index = " + fromIndex + "; index <= " + toIndex + "; index++)");
            methodCode.add("{");
            methodCode.add("    subFieldBitSet.set(i++, bitFieldBitSet.get(index));");
            methodCode.add("}");
            methodCode.add("");
            methodCode.add("return (" + bfType + ")JausUtils.getPVLong(subFieldBitSet);");
            code.methods.addAll(JavaCode.createMethodDefinition("public " +bfType, "get", subFieldName, methodParam, methodCode, false));
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            methodCode.add("BitArray bitFieldBitSet = JausUtils.setPV(m_SubFields);");
            methodCode.add("BitArray subFieldBitSet = new BitArray(" + size + ");");
            methodCode.add("int i = 0;");
            methodCode.add("");
            methodCode.add("for (int index = " + fromIndex + "; index <= " + toIndex + "; index++)");
            methodCode.add("{");
            methodCode.add("    subFieldBitSet.Set(i++, bitFieldBitSet.Get(index));");
            methodCode.add("}");
            methodCode.add("");
            methodCode.add("return (" + bfType + ") JausUtils.getPVint(subFieldBitSet);");
            code.methods.addAll(CSharpCode.createMethodDefinition("public " + bfType, "get", subFieldName, methodParam, methodCode, false));
        }
    }
	
    private void generateFieldSetMethod(CodeLines code, int fromIndex, int toIndex, int size, String subFieldName, ValueSet valueSet)
    {
        ///  Generate setMethod Declaration and Definition
    	methodParam.clear();
    	methodCode.clear();
        methodParam.add(bfType + " value");
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            code.publicMethods.add(CppCode.createMethodDeclaration("int", "set", subFieldName, methodParam, false));

            methodCode.add("std::bitset<sizeof(" + bfType + ") * 8> bfbs((int)m_SubFields);");
            methodCode.add("std::bitset<" + size + "> sfbs((int)value);");
            methodCode.add("int i = 0;");
            methodCode.add("");
            methodCode.add("for (int index = " + fromIndex + "; index <= " + toIndex + "; index++)");
            methodCode.add("{");
            methodCode.add("    bfbs[index] = sfbs[i++];");
            methodCode.add("}");
            methodCode.add("");
            methodCode.add("m_SubFields = (" + bfType + ")bfbs.to_ulong();");
            // Create framework for parent reference
            methodCode.add(CppCode.getParentReferenceSetParentPVLine());
            methodCode.add("return 0;");

            if (valueSet != null)
            {
            new ValueSetGenerator(codeType, valueSet).addRangeCheck("value", methodCode);
                    methodCode.add("return 1;");
            }
            code.methods.addAll(CppCode.createMethodDefinition("int", fullClassName + "::set", subFieldName, methodParam, methodCode, false));
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            methodCode.add("BitSet bfbs = JausUtils.setPV(m_SubFields);");
            methodCode.add("BitSet sfbs = new BitSet(" + size +");");
            methodCode.add("sfbs = JausUtils.setPV(value);");
            methodCode.add("int i = 0;");
            methodCode.add("");
            methodCode.add("for (int index = " + fromIndex + "; index <= " + toIndex + "; index++)");
            methodCode.add("{");
            methodCode.add("\tbfbs.set(index, sfbs.get(i++));");
            methodCode.add("}");
            methodCode.add("");
            methodCode.add("m_SubFields = (" + bfType + ") JausUtils.getPVLong(bfbs);");
            // Create framework for parent reference
            methodCode.add(JavaCode.getParentReferenceSetParentPVLine());
            methodCode.add("return 0;");

            if (valueSet != null)
            {
            new ValueSetGenerator(codeType, valueSet).addRangeCheck("value", methodCode);
                    methodCode.add("return 1;");
            }
            code.methods.addAll(JavaCode.createMethodDefinition("public int", "set", subFieldName, methodParam, methodCode, false));
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            methodCode.add("BitArray bfbs = JausUtils.setPV(m_SubFields);");
            methodCode.add("BitArray sfbs = new BitArray(" + size + ");");
            methodCode.add("sfbs = JausUtils.setPV(value);");
            methodCode.add("int i = 0;");
            methodCode.add("");
            methodCode.add("for (int index = " + fromIndex + "; index <= " + toIndex + "; index++)");
            methodCode.add("{");
            methodCode.add("    bfbs.Set(index, sfbs.Get(i++));");
            methodCode.add("}");
            methodCode.add("");
            methodCode.add("m_SubFields = (" + bfType + ") JausUtils.getPVint(bfbs);");
            // Create framework for parent reference
            methodCode.add(CSharpCode.getParentReferenceSetParentPVLine());
            methodCode.add("return 0;");

            if (valueSet != null)
            {
                new ValueSetGenerator(codeType, valueSet).addRangeCheck("value", methodCode);
                    methodCode.add("return 1;");
            }
            code.methods.addAll(CSharpCode.createMethodDefinition("public int", "set", subFieldName, methodParam, methodCode, false));
        }
    }
	
	private void generateFieldOverloadAssignmentMethod(CodeLines code)
	{
            /// Overload the operator=
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                code.assignmentLines.add("this->m_SubFields = value.m_SubFields;");
            }
            else if(codeType == CodeLines.CodeType.JAVA || codeType == CodeLines.CodeType.C_SHARP)
            {

                code.assignmentLines.add("this.m_SubFields = value.m_SubFields;");
            }
	}
	
	private void generateFieldOverloadIsEqualMethod(CodeLines code, String subFieldName)
	{
        // Overload operator==
            if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                code.equalLines.add("return (this->m_SubFields == value.m_SubFields);");
            }
            else if (codeType == CodeLines.CodeType.JAVA)
            {
                code.equalLines.add("return this.m_SubFields == value.get" + subFieldName + "();");
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                code.equalLines.add("return (this.m_SubFields == value.get" + subFieldName + "());");
            }
	}

    /*
     * @param code the holder for all the lines of code to be added to the generated file
     * @param subFieldName the string needed for the Java and C# getSubField method.
     */
	private void generateFieldOverloadNotEqualMethod(CodeLines code, String subFieldName)
	{
        // Overload operator!=
            if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                code.notEqualLines.add("return (this->m_SubFields != value.m_SubFields);");
            }
            else if (codeType == CodeLines.CodeType.JAVA)
            {
                code.notEqualLines.add("return this.m_SubFields != value.get" + subFieldName + "();");
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                code.notEqualLines.add("return (this.m_SubFields != value.get" + subFieldName + "());");
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


            generateInstanceGetSizeMethod(code,pvIndex);

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
	    methodCode.add("size += " + varName + ".getSize();");
	    
	    // only add size of array if optional is true 
	    if (bitField.isOptional()) 
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
	    if (bitField.isOptional()) 
	    {
                if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
                {
                    code.publicMethods.add(CppCode.createMethodDeclaration("bool", "is", shortClassName + "Valid", null, true));
                    methodCode.clear();
                    methodCode.add("return true;");
                    methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                    methodCode.add("return false;");

                    // DMM - Bug fix.  The full class name includes the name of the bitfield.  The is**Valid method
                    // is actually defined by the container class, so the shortClassName needs to be
                    // stripped from the fullClassName.
                    code.methods.addAll(CppCode.createMethodDefinition("bool", parentClassName + "::is", shortClassName + "Valid", null, methodCode, true));
                }
                else if (codeType == CodeLines.CodeType.JAVA)
                {
                    methodCode.clear();
                    methodCode.add("return true;");
                    methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                    methodCode.add("return false;");

                    // DMM - Bug fix.  The full class name includes the name of the bitfield.  The is**Valid method
                    // is actually defined by the container class, so the shortClassName needs to be
                    // stripped from the fullClassName.

                    code.methods.addAll(JavaCode.createMethodDefinition("public boolean", "is", shortClassName + "Valid", null, methodCode, true));
                }
                else if (codeType == CodeLines.CodeType.C_SHARP)
                {
                    methodCode.clear();
                    methodCode.add("return true;");
                    methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
                    methodCode.add("return false;");

                    // DMM - Bug fix.  The full class name includes the name of the bitfield.  The is**Valid method
                    // is actually defined by the container class, so the shortClassName needs to be
                    // stripped from the fullClassName.

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
                if (bitField.isOptional())
                {
                    if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
                    {
                        methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                    }
                }
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                /* 	m_VaribleLengthField1.encode(bytes + pos);
                 *	pos += m_VaribleLengthField1.getSize();
                 */
                methodCode.add(varName + ".encode(bytes, pos);");
                methodCode.add("pos += " + varName + ".getSize();");
                if (bitField.isOptional())
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
                if (bitField.isOptional())
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
		
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                /* 	m_VaribleLengthField1.decode(bytes + pos);
                 *	pos += m_VaribleLengthField1.getSize();
                 */
                methodCode.add(varName + ".decode(bytes + pos);");
                methodCode.add("pos += " + varName + ".getSize();");
                if (bitField.isOptional())
                {
                    if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
                    {
                        methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                    }
                }
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                /* 	m_VaribleLengthField1.decode(bytes + pos);
                 *	pos += m_VaribleLengthField1.getSize();
                 */
                methodCode.add(varName + ".decode(bytes, pos);");
                methodCode.add("pos += " + varName + ".getSize();");
                if (bitField.isOptional())
                {
                    methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                }
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                /* 	m_VaribleLengthField1.decode(bytes + pos);
                 *	pos += m_VaribleLengthField1.getSize();
                 */
                methodCode.add(varName + ".decode(bytes, pos);");
                methodCode.add("pos += " + varName + ".getSize();");
                if (bitField.isOptional())
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

            if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                ///  Generate getMethod Declaration and Definition
                code.publicMethods.add(CppCode.createMethodDeclaration(shortClassName + "* const", "get", shortClassName, methodParam, false));

                methodCode.add("return &" + varName + ";");
                code.methods.addAll(CppCode.createMethodDefinition(fullClassName + "* const", parentClassName + "::get", shortClassName, methodParam, methodCode, false));
            }
            else if (codeType == CodeLines.CodeType.JAVA)
            {
                ///  Generate getMethod Definition

                methodCode.add("return " + varName + ";");
                code.methods.addAll(JavaCode.createMethodDefinition("public " + fullClassName, "get", shortClassName, methodParam, methodCode, false));
            }
            else if (codeType == CodeLines.CodeType.C_SHARP)
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
                if (bitField.isOptional())
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
                if (bitField.isOptional())
                {
                    methodCode.add("setPresenceVector("+ pvIndex +");");
                }
                // Create framework for parent reference
                methodCode.add(JavaCode.getParentReferenceSetParentPVLine());
                code.methods.addAll(JavaCode.createMethodDefinition("public void", "set", shortClassName, methodParam, methodCode, false));
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                ///  Generate setMethod Definition
                methodParam.add(shortClassName + " value");
                methodCode.add(varName + " = value;");
                if (bitField.isOptional())
                {
                    methodCode.add("setPresenceVector("+ pvIndex +");");
                }
                // Create framework for parent reference
                methodCode.add(CSharpCode.getParentReferenceSetParentPVLine());
                code.methods.addAll(CSharpCode.createMethodDefinition("public void", "set", shortClassName, methodParam, methodCode, false));
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
	 * Generate code for an array of bit fields
	 * @param pvIndex
	 * @param code
	 */
	private void generateArray(int pvIndex, CodeLines code)
	{	
            List<String> getMethodCode = new ArrayList<String>();
            List<String> setMethodCode = new ArrayList<String>();
            List<String> paramCode = new ArrayList<String>();

            String posCalc = "";	// The calculation for the position within an 1-dimension array from the n-dimension array
            String prevCalc = "";	// The previous calculation for the position within an 1-dimension array from the n-dimension array
	    int arraySize = 1;
		
            for (int i = 0; i < dimList.size(); i++)
            {
                Dimension dim = dimList.get(i);

                String dimName = Util.upperCaseFirstLetter(dim.getName());
                String dimSizeName = dimName + "Size";

                if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
                {
                    String dimVarName = CppCode.getVariableName(dimSizeName);

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
                }
                else if(codeType == CodeLines.CodeType.JAVA)
                {
                    String dimVarName = JavaCode.getVariableName(dimSizeName);

                    code.protectedAttributes.add(JavaCode.createVariableDeclaration("protected int", dimSizeName, false));
                    code.constructorLines.add(dimVarName + " = " + dim.getSize() + ";");

                    ///  Generate getMethod Definition
                    methodCode.clear();
                    methodCode.add("return " + dimVarName + ";");
                    code.methods.addAll(JavaCode.createMethodDefinition("public int", "get", dimSizeName, null, methodCode, true));

                    /// Add the dimension to the method parameter vector for the get and set methods
                    paramCode.add("int " + dimName);
                    getMethodCode.add("if (" + dimName + " >= " + dim.getSize() + ")");
                    getMethodCode.add("{");
                    getMethodCode.add("\treturn null;");
                    getMethodCode.add("}");
                    getMethodCode.add("");
                }
                else if(codeType == CodeLines.CodeType.C_SHARP)
                {
                    String dimVarName = CSharpCode.getVariableName(dimSizeName);

                    code.protectedAttributes.add(CSharpCode.createVariableDeclaration("protected int", dimSizeName, true));
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
                }

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
		
	    /*
	     * Create code lines for the encode, decode Methods
	     * Create the getMethod and setMethod
	     * This has to be done after the class wrapper has been added
	     */
		
            // create variable
            if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                /*
                 * Create framework for parent reference
                 */
                CppCode.addParentReferenceConstructorSetParrentInArray(code, varName, arraySize);

                code.protectedAttributes.add(CppCode.createVariableDeclaration(shortClassName, shortClassName + "[" + arraySize + "]" , false));
                code.constructorLines.add("/// No Initialization of " + varName + " necessary.");
            }
            else if (codeType == CodeLines.CodeType.JAVA)
            {
                code.protectedAttributes.add(JavaCode.createVariableDeclaration("protected " + shortClassName + "[]", shortClassName, false));
                code.constructorLines.add(varName + " = new " + shortClassName + "[" + arraySize + "];");

                initArrayInstances(code, arraySize, varName, shortClassName);
                 /*
                 * Create framework for parent reference
                 */
                JavaCode.addParentReferenceConstructorSetParrentInArray(code, varName, arraySize);
            }
            else if (codeType == CodeLines.CodeType.C_SHARP)
            {
                code.protectedAttributes.add(CSharpCode.createVariableDeclaration("protected " + shortClassName +"[]", shortClassName, true));
                code.constructorLines.add(varName + " = new " + shortClassName + "[" + arraySize + "];");

                initArrayInstances(code, arraySize, varName, shortClassName);
                /*
                 * Create framework for parent reference
                 */
                CSharpCode.addParentReferenceConstructorSetParrentInArray(code, varName, arraySize);
            }

	    generateArrayGetSizeMethod(code, pvIndex, arraySize);
	    
	    generateArrayIsValidMethod(code, pvIndex);
	    
	    generateArrayEncodeMethod(code, pvIndex, arraySize);
	    generateArrayDecodeMethod(code, pvIndex, arraySize);
	    
	    generateArrayGetMethod(code, posCalc, getMethodCode, paramCode);
	    generateArraySetMethod(code, posCalc, setMethodCode, paramCode);

        generateArrayOverloadIsEqualMethod(code, arraySize);
        generateArrayOverloadNotEqualMethod(code, arraySize);
        generateArrayOverloadAssignmentMethod(code, arraySize);
	}
	
	private void generateArrayGetSizeMethod(CodeLines code, int pvIndex, int arraySize)
	{
	    /// GetSize method
	    /// only add size of array if optional is true 
		methodCode.clear();
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
	    if (bitField.isOptional())
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
	    if (bitField.isOptional()) 
	    {
                if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
                {
                    code.publicMethods.add(CppCode.createMethodDeclaration("bool", "is", shortClassName + "Valid", null, true));
                    methodCode.clear();
                    methodCode.add("return true;");
                    methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                    methodCode.add("return false;");
                    code.methods.addAll(CppCode.createMethodDefinition("bool", parentClassName + "::is", shortClassName + "Valid", null, methodCode, true));
                }
                else if(codeType == CodeLines.CodeType.JAVA)
                {
                    methodCode.clear();
                    methodCode.add("return true;");
                    methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                    methodCode.add("return false;");
                    code.methods.addAll(JavaCode.createMethodDefinition("public boolean","is", shortClassName + "Valid", null, methodCode, true));
                }
                else if(codeType == CodeLines.CodeType.C_SHARP)
                {
                    methodCode.clear();
                    methodCode.add("return true;");
                    methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
                    methodCode.add("return false;");
                    code.methods.addAll(CSharpCode.createMethodDefinition("public bool", "is", shortClassName + "Valid", null, methodCode, true));
                }
	    }
	}
		
	private void generateArrayEncodeMethod(CodeLines code, int pvIndex, int arraySize)
	{
            methodCode.clear();

	    /* for (unsigned int i = 0; i < "arraySize"; i++)
	     * {	
	     * 		m_VaribleLengthField1[i].encode(bytes + pos);
	     *		pos += m_VaribleLengthField1[i].getSize();
	     * }
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
		
	private void generateArrayDecodeMethod(CodeLines code, int pvIndex, int arraySize)
	{
            methodCode.clear();
		
	    /* for (unsigned int i = 0; i < "arraySize"; i++)
	     * {	
	     * 		m_VaribleLengthField1[i].decode(bytes + pos);
	     *		pos += m_VaribleLengthField1[i].getSize();
	     * }
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
                methodCode.add("for (int i = 0; i < "  + arraySize + "; i++)");
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
		
    private void generateArrayGetMethod(CodeLines code, String posCalc, List<String> getMethodCode, List<String> paramCode) {
        ///  Generate getMethod Declaration and Definition
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            code.publicMethods.add(CppCode.createMethodDeclaration(shortClassName + "* const", "get", shortClassName, paramCode, false));

            methodCode.clear();
            methodCode.add("unsigned int index = " + posCalc + ";");
            methodCode.add("");
            methodCode.addAll(getMethodCode);
            methodCode.add("return &" + varName + "[index];");
            code.methods.addAll(CppCode.createMethodDefinition(fullClassName + "* const", parentClassName + "::get", shortClassName, paramCode, methodCode, false));
        } else if (codeType == CodeLines.CodeType.JAVA) {
            methodCode.clear();
            methodCode.add("int index = " + posCalc + ";");
            methodCode.add("");
            methodCode.addAll(getMethodCode);
            methodCode.add("return " + varName + "[index];");
            code.methods.addAll(JavaCode.createMethodDefinition("public " + fullClassName, "get", shortClassName, paramCode, methodCode, false));
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            methodCode.clear();
            methodCode.add("int index = " + posCalc + ";");
            methodCode.add("");
            methodCode.addAll(getMethodCode);
            methodCode.add("return " + varName + "[index];");
            code.methods.addAll(CSharpCode.createMethodDefinition("public " + fullClassName, "get", shortClassName, paramCode, methodCode, false));
        }
    }
		
    private void generateArraySetMethod(CodeLines code, String posCalc, List<String> setMethodCode, List<String> paramCode) {
        ///  Generate setMethod Declaration and Definition
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            paramCode.add("const " + shortClassName + " &value");
            code.publicMethods.add(CppCode.createMethodDeclaration("int", "set", shortClassName, paramCode, false));

            methodCode.clear();
            methodCode.add("unsigned int index = " + posCalc + ";");
            methodCode.add("");
            methodCode.addAll(setMethodCode);
            methodCode.add(varName + "[index] = value;");

            // Create framework for parent reference
            methodCode.add(CppCode.getParentReferenceSetParentPVLine());
            methodCode.add("return 0;");
            code.methods.addAll(CppCode.createMethodDefinition("int", parentClassName + "::set", shortClassName, paramCode, methodCode, false));
        } else if (codeType == CodeLines.CodeType.JAVA) {
            paramCode.add(shortClassName + " value");

            methodCode.clear();
            methodCode.add("long index = " + posCalc + ";");
            methodCode.add("");
            methodCode.addAll(setMethodCode);
            methodCode.add(varName + "[(int) index] = value;");

            // Create framework for parent reference
            methodCode.add(JavaCode.getParentReferenceSetParentPVLine());
            methodCode.add("return 0;");
            code.methods.addAll(JavaCode.createMethodDefinition("public int", "set", shortClassName, paramCode, methodCode, false));
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            paramCode.add(shortClassName + " value");

            methodCode.clear();
            methodCode.add("int index = " + posCalc + ";");
            methodCode.add("");
            methodCode.addAll(setMethodCode);
            methodCode.add(varName + "[index] = value;");

            // Create framework for parent reference
            methodCode.add(CSharpCode.getParentReferenceSetParentPVLine());
            methodCode.add("return 0;");
            code.methods.addAll(CSharpCode.createMethodDefinition("public int", "set", shortClassName, paramCode, methodCode, false));
        }
    }

    private void generateArrayOverloadIsEqualMethod(CodeLines code, int arraySize) {
        /// Overload operator==
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            code.equalLines.add("for (unsigned int i = 0; i < " + arraySize + "; i++)");
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

    private void generateArrayOverloadNotEqualMethod(CodeLines code, int arraySize) {
        /// Overload operator!=
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
