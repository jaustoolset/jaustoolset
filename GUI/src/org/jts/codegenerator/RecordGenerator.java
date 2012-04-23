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
 * @(#)RecordGenerator.java		0.1 2008/09/03
 * 
 * Copyright
 */
package org.jts.codegenerator;

import java.util.Vector;

import org.jts.jsidl.binding.*;

// TODO: Add code for testOperators
/**
 * This class will generate C++, Java, or C# code from a JSIDL Record
 *
 * @version		0.1	5 Sept 2008
 * @author		Nicholas M. Johnson
 * @author		Gregory Garcia
 * @author		Jean-Francois Kamath
 * @author              Gina Nearing
 *
 */
/// TODO: Fix operator=, operator==, operator!= for FixedLengthString and VariableLengthString
public class RecordGenerator
{
//	final private boolean debug = false;

    final private boolean arrayHasClass = true;						// Array					// Currently always true
    final private boolean bitFieldHasClass = true;					// BitField					// Currently always true
    final private boolean fixedFieldHasClass = false;				// FixedField				// Currently always false
    final private boolean variableFieldHasClass = true;				// VariableField			// Currently always true
    final private boolean variableLengthFieldHasClass = true;		// VariableLengthField		// Currently always true
    final private boolean variableFormatFieldHasClass = true;		// VariableFormatField		// Currently always true
    final private boolean fixedLengthStringHasClass = false;		// FixedLengthString		// Currently always false
    final private boolean variableLengthStringHasClass = false;		// VariableLengthString		// Currently always false
    private CodeLines.CodeType codeType;
    private Record record;
    private boolean isList;
    private String countFieldType;
    private boolean countFieldSigned;
    private Vector<String> methodCode;
    private Vector<String> methodParam;
    private String parentClassName;
    private String fullClassName;
    private String shortClassName;
    private String varName;

    public RecordGenerator(CodeLines.CodeType codeType, Record record)
    {
        this.codeType = codeType;
        this.record = record;
        this.isList = false;

        methodCode = new Vector<String>();
        methodParam = new Vector<String>();
        shortClassName = record.getName();
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            varName = CppCode.getVariableName(shortClassName);
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            varName = JavaCode.getVariableName(shortClassName);
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            varName = CSharpCode.getVariableName(shortClassName);
        }

    }

    public RecordGenerator(CodeLines.CodeType codeType, Record record, boolean isList, String countFieldType)
    {
        this.codeType = codeType;
        this.record = record;
        this.isList = isList;
        this.countFieldType = countFieldType;

        methodCode = new Vector<String>();
        methodParam = new Vector<String>();
        shortClassName = record.getName();
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            varName = CppCode.getVariableName(shortClassName);
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            varName = JavaCode.getVariableName(shortClassName);
            countFieldSigned = JavaCode.getVariableSign(countFieldType);
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            varName = CSharpCode.getVariableName(shortClassName);
        }
    }

    /**
     * This method generates the code for creating a record.
     * The generate Record will be placed within its own class which
     * is nested within the encapsulating class.
     * @param parentName
     * @param code
     * @param messageId
     */
    public void run(String parentName, int pvIndex, CodeLines code, String messageId)
    {
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            parentClassName = parentName;
            fullClassName = parentClassName + "::" + shortClassName;
            
            /*
             * Create framework for parent reference
             */
           	CppCode.addParentReference(code, fullClassName, pvIndex, record.isOptional());
        }
        else if (codeType == CodeLines.CodeType.JAVA )
        {
            parentClassName = parentName;
            fullClassName = parentClassName + "." + shortClassName;

            /*
             * Create framework for parent reference
             */
           	JavaCode.addParentReference(code, fullClassName, pvIndex, record.isOptional());
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            parentClassName = parentName;
            fullClassName = parentClassName + "." + shortClassName;

            /*
             * Create framework for parent reference
             */
           	CSharpCode.addParentReference(code, fullClassName, pvIndex, record.isOptional());
        }
        
        /* Create the Record Class */
        /***************************/
        generateClass(code, messageId);
        
        if (isList)
        {
            generateRecordVector(pvIndex, code);
        }
        else
        {
            generateRecordInstance(pvIndex, code);
        }
    }

    /**
     *
     * @param messageId
     * @param code
     */
    private void generateClass(CodeLines code, String messageId)
    {
        CodeLines recCode = new CodeLines("", codeType, code.getNameSpace());
        int pvIndex1 = -1;

        String variableName;


        /* Generate the code for the presenceVector */
        if (record.getPresenceVector() != null)
        {
            recCode.clear();

            new PresenceVectorGenerator(codeType, record.getPresenceVector()).run(fullClassName, true, recCode);

            // Also add an assignment handler
            code.assignmentLines.add("m_PresenceVector = value.m_PresenceVector;");

            /* Add the generated code to the overall code */
            code.addAll(recCode);
        }

        for (Object field : record.getArrayOrFixedFieldOrVariableField())
        {
            boolean fieldIsNested = false;
            String fieldName = "Unknown";

            recCode.clear();
            if (field instanceof Array)
            {
                Array array = (Array) field;

                fieldName = Util.upperCaseFirstLetter(array.getName());
                fieldIsNested = arrayHasClass;
                if (array.isOptional())
                {
                    pvIndex1++;
                }

                new ArrayGenerator(codeType, array).run(fullClassName, pvIndex1, arrayHasClass, recCode);
            }
            else if (field instanceof BitField)
            {
                BitField bitField = (BitField) field;

                fieldName = Util.upperCaseFirstLetter(bitField.getName());
                fieldIsNested = bitFieldHasClass;

                if (bitField.isOptional())
                {
                    pvIndex1++;
                }

                new BitFieldGenerator(codeType, bitField).run(fullClassName, pvIndex1, bitFieldHasClass, recCode);
            }
            else if (field instanceof FixedField)
            {
                FixedField fixedField = (FixedField) field;

                fieldName = Util.upperCaseFirstLetter(fixedField.getName());
                fieldIsNested = fixedFieldHasClass;

                if (fixedField.isOptional())
                {
                    pvIndex1++;
                }

                // If a messageId has been specified, and this fixed field is an unsigned short,
                // set the constructor of the field to automatically set the variables to the
                // message id value.  This can be overwritten at run-time.
                String ffValue = null;
                if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
                {
                    if ((messageId != null) && (CppCode.getVariableType(fixedField.getFieldType()).equals("jUnsignedShortInteger")))
                    {
                        ffValue = "0x" + messageId;
                    }
                }
                else if (codeType == CodeLines.CodeType.JAVA)
                {
                    if ((messageId != null) && (JavaCode.getVariableType(fixedField.getFieldType()).equals("int")))
                    {
                        ffValue = "0x" + messageId;
                    }
                }
                else if (codeType == CodeLines.CodeType.C_SHARP)
                {
                    if ((messageId != null) && (CSharpCode.getVariableType(fixedField.getFieldType()).equals("ushort")))
                    {
                        ffValue = "0x" + messageId;
                    }
                }

                new FixedFieldGenerator(codeType, fixedField).run(fullClassName, pvIndex1, fixedFieldHasClass, recCode, ffValue);
            }
            else if (field instanceof FixedLengthString)
            {
                FixedLengthString fixedLengthString = (FixedLengthString) field;

                fieldName = Util.upperCaseFirstLetter(fixedLengthString.getName());
                fieldIsNested = fixedLengthStringHasClass;

                if (fixedLengthString.isOptional())
                {
                    pvIndex1++;
                }

                new FixedLengthStringGenerator(codeType, fixedLengthString).run(fullClassName, pvIndex1, fixedLengthStringHasClass, recCode);
            }
            else if (field instanceof VariableField)
            {
                VariableField variableField = (VariableField) field;

                fieldName = Util.upperCaseFirstLetter(variableField.getName());
                fieldIsNested = variableFieldHasClass;

                if (variableField.isOptional())
                {
                    pvIndex1++;
                }

                new VariableFieldGenerator(codeType, variableField).run(fullClassName, pvIndex1, variableFieldHasClass, recCode);
            }
            else if (field instanceof VariableFormatField)
            {
                VariableFormatField variableFormatField = (VariableFormatField) field;

                fieldName = Util.upperCaseFirstLetter(variableFormatField.getName());
                fieldIsNested = variableFormatFieldHasClass;

                if (variableFormatField.isOptional())
                {
                    pvIndex1++;
                }

                new VariableFormatFieldGenerator(codeType, variableFormatField).run(fullClassName, pvIndex1, variableFormatFieldHasClass, recCode);
            }
            else if (field instanceof VariableLengthField)
            {
                VariableLengthField variableLengthField = (VariableLengthField) field;

                fieldName = Util.upperCaseFirstLetter(variableLengthField.getName());
                fieldIsNested = variableLengthFieldHasClass;

                if (variableLengthField.isOptional())
                {
                    pvIndex1++;
                }

                new VariableLengthFieldGenerator(codeType, variableLengthField).run(fullClassName, pvIndex1, variableLengthFieldHasClass, recCode);
            }
            else if (field instanceof VariableLengthString)
            {
                VariableLengthString variableLengthString = (VariableLengthString) field;

                fieldName = Util.upperCaseFirstLetter(variableLengthString.getName());
                fieldIsNested = variableLengthStringHasClass;

                if (variableLengthString.isOptional())
                {
                    pvIndex1++;
                }

                new VariableLengthStringGenerator(codeType, variableLengthString).run(fullClassName, pvIndex1, variableLengthStringHasClass, recCode);
            }
            else
            {
                throw new CodeGeneratorException("RecordGenerator: Unknown Field Encountered: " + field.getClass().getName());
            }

            if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                variableName = CppCode.getVariableName(fieldName);
            }
            else if (codeType == CodeLines.CodeType.JAVA)
            {
                variableName = JavaCode.getVariableName(fieldName);
            }
            else if (codeType == CodeLines.CodeType.C_SHARP)
            {
                variableName = CSharpCode.getVariableName(fieldName);
            }
            else
            {
                // Unkown code type
                variableName = "";
            }


            /* Add the generated code to the overall code */
            code.addAll(recCode);

            /*
             * If the field is nested then we need to provide code for each field
             * for the assignment operator, code for testing equality and inequality
             */
            if (fieldIsNested)
            {
            	

                /// Add method for checking equality
                /// and
                /// Add method for assigning new value to the field.  

                if (!code.equalLines.isEmpty())
                {
                    code.equalLines.add("");
                }

                if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
                {
                    code.assignmentLines.add(variableName + " = value." + variableName + ";");
                    code.equalLines.add("if (" + variableName + " != value." + variableName + ")");
                }
                else if (codeType == CodeLines.CodeType.JAVA)
                {
                    code.assignmentLines.add(variableName + " = value." + variableName + ";");
                    code.equalLines.add("if (!" + variableName + ".isEqual(value.get" + Util.upperCaseFirstLetter(fieldName) + "()))");
                }
                else if (codeType == CodeLines.CodeType.C_SHARP)
                {
                    code.assignmentLines.add(variableName + " = value.get" +  variableName.substring(variableName.indexOf(CSharpCode.VARIABLE_PREFIX)+2) + "();");
                    code.equalLines.add("if (!this.get" + variableName.substring(variableName.indexOf(CSharpCode.VARIABLE_PREFIX)+2)
                            + "().isEqual(value.get" + variableName.substring(variableName.indexOf(CSharpCode.VARIABLE_PREFIX)+2) + "()))");
                }
                code.equalLines.add("{");
                code.equalLines.add("\treturn false;");
                code.equalLines.add("}");


                /// Add method for checking inequality
                if (!code.notEqualLines.isEmpty())
                {
                    code.notEqualLines.add("");
                }
                if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
                {
                    code.notEqualLines.add("if (" + variableName + " == value." + variableName + ")");
                }
                else if (codeType == CodeLines.CodeType.JAVA)
                {
                    code.notEqualLines.add("if (!" + variableName + ".notEquals(value.get" + Util.upperCaseFirstLetter(fieldName) + "()))");
                }
                else if (codeType == CodeLines.CodeType.C_SHARP)
                {
                    code.notEqualLines.add("if (!this.get" + variableName.substring(variableName.indexOf(CSharpCode.VARIABLE_PREFIX)+2)
                            + "().notEquals(value.get" + variableName.substring(variableName.indexOf(CSharpCode.VARIABLE_PREFIX)+2) + "()))");
                }
                code.notEqualLines.add("{");
                code.notEqualLines.add("\treturn false;");
                code.notEqualLines.add("}");
            }
        }


        /// Overload operator==
        code.equalLines.add("");
        code.equalLines.add("return true;");


        /// Overload operator!=
        code.notEqualLines.add("");
        code.notEqualLines.add("return true;");

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
    }

    /**
     * Generate instance
     * @param pvIndex
     * @param code
     */
    private void generateRecordInstance(int pvIndex, CodeLines code)
    {
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
            CppCode.addParentReferenceConstructorSetParent(code, varName);

            code.protectedAttributes.add(CppCode.createVariableDeclaration(shortClassName, shortClassName, false));
            code.constructorLines.add("/// No Initialization of " + varName + " necessary.");
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            
            code.protectedAttributes.add(JavaCode.createVariableDeclaration("protected " + shortClassName, shortClassName, false));
            code.constructorLines.add(varName + " = new " + shortClassName + "();");
            /*
             * Create framework for parent reference
             */
            JavaCode.addParentReferenceConstructorSetParent(code, varName);
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {           
            code.protectedAttributes.add(CSharpCode.createVariableDeclaration("protected " + shortClassName, shortClassName, false));
            code.constructorLines.add(varName + " = new " + shortClassName + "();");

            /*
             * Create framework for parent reference
             */
            CSharpCode.addParentReferenceConstructorSetParent(code, varName);
        }

        generateInstanceGetMethod(code, pvIndex);

        generateInstanceIsValidMethod(code, pvIndex);

        generateInstanceEncodeMethod(code, pvIndex);
        generateInstanceDecodeMethod(code, pvIndex);

        generateInstanceGetMethod(code);
        generateInstanceSetMethod(code, pvIndex);

        generateInstanceOverloadAssignmentMethod(code);
        generateInstanceOverloadIsEqualMethod(code);
        generateInstanceOverloadNotEqualMethod(code);
    }

    private void generateInstanceGetMethod(CodeLines code, int pvIndex)
    {
        /*
         * GetSize method
         * only add size of array if optional is true
         */
        methodCode.add("size += " + varName + ".getSize();");
        if (record.isOptional())
        {
            if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
            }
            else if (codeType == CodeLines.CodeType.JAVA)
            {
                methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
            }
            else if (codeType == CodeLines.CodeType.C_SHARP)
            {
                methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        code.sizeLines.addAll(methodCode);
    }

    private void generateInstanceIsValidMethod(CodeLines code, int pvIndex)
    {
        /*
         * is[Field]valid added if optional
         * return true if field is set in presence vector
         */
        if (record.isOptional())
        {
            if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                code.publicMethods.add(CppCode.createMethodDeclaration("bool", "is", shortClassName + "Valid", null, true));
                methodCode.clear();
                methodCode.add("return true;");
                methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                methodCode.add("return false;");
                code.methods.addAll(CppCode.createMethodDefinition("bool", parentClassName + "::is", shortClassName + "Valid", null, methodCode, true));
            }
            else if (codeType == CodeLines.CodeType.JAVA)
            {
                methodCode.clear();
                methodCode.add("return true;");
                methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                methodCode.add("return false;");
                code.methods.addAll(JavaCode.createMethodDefinition("public boolean", "is", shortClassName + "Valid", null, methodCode, true));
            }
            else if (codeType == CodeLines.CodeType.C_SHARP)
            {
                methodCode.clear();
                methodCode.add("return true;");
                methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
                methodCode.add("return false;");
                code.methods.addAll(CSharpCode.createMethodDefinition("public bool", "is", shortClassName + "Valid", null, methodCode, true));
            }
        }
    }

    private void generateInstanceEncodeMethod(CodeLines code, int pvIndex)
    {
        /* 	m_Record1.encode(bytes + pos);
         *	pos += m_Record1.getSize();
         */

        methodCode.clear();

        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            methodCode.add(varName + ".encode(bytes + pos);");
            methodCode.add("pos += " + varName + ".getSize();");

            if (record.isOptional())
            {
                methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            methodCode.add(varName + ".encode(bytes, pos);");
            methodCode.add("pos += " + varName + ".getSize();");

            if (record.isOptional())
            {
                methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            methodCode.add(varName + ".encode(bytes, pos);");
            methodCode.add("pos += " + varName + ".getSize();");

            if (record.isOptional())
            {
                methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }

        code.encoderLines.addAll(methodCode);
    }

    private void generateInstanceDecodeMethod(CodeLines code, int pvIndex)
    {
        /* 	m_Record1.decode(bytes + pos);
         *	pos += m_Record1.getSize();
         */
        methodCode.clear();
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            methodCode.add(varName + ".decode(bytes + pos);");
            methodCode.add("pos += " + varName + ".getSize();");
            if (record.isOptional())
            {

                methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            methodCode.add(varName + ".decode(bytes, pos);");
            methodCode.add("pos += " + varName + ".getSize();");
            if (record.isOptional())
            {
                methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            methodCode.add(varName + ".decode(bytes, pos);");
            methodCode.add("pos += " + varName + ".getSize();");
            if (record.isOptional())
            {
                methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        code.decoderLines.addAll(methodCode);
    }

    private void generateInstanceGetMethod(CodeLines code)
    {
        ///  Generate getMethod Declaration and Definition
        methodParam.clear();
        methodCode.clear();

        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            code.publicMethods.add(CppCode.createMethodDeclaration(shortClassName + "* const", "get", shortClassName, methodParam, false));

            methodCode.add("return &" + varName + ";");
            code.methods.addAll(CppCode.createMethodDefinition(fullClassName + "* const", parentClassName + "::get", shortClassName, methodParam, methodCode, false));

        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            methodCode.add("return " + varName + ";");
            code.methods.addAll(JavaCode.createMethodDefinition("public " + fullClassName, "get", shortClassName, methodParam, methodCode, false));

        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            methodCode.add("return " + varName + ";");
            code.methods.addAll(CSharpCode.createMethodDefinition("public " + fullClassName, "get", shortClassName, methodParam, methodCode, false));
        }
    }

    private void generateInstanceSetMethod(CodeLines code, int pvIndex)
    {
        ///  Generate setMethod Declaration and Definition
        methodParam.clear();
        methodCode.clear();
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            methodParam.add("const " + shortClassName + " &value");
            code.publicMethods.add(CppCode.createMethodDeclaration("int", "set", shortClassName, methodParam, false));
            methodCode.add(varName + " = value;");
            if (record.isOptional())
            {
                methodCode.add("setPresenceVector(" + pvIndex + ");");
            }
            // Create framework for parent reference
            methodCode.add(CppCode.getParentReferenceSetParentPVLine());
            methodCode.add("return 0;");
            code.methods.addAll(CppCode.createMethodDefinition("int", parentClassName + "::set", shortClassName, methodParam, methodCode, false));
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            methodParam.add(shortClassName + " value");
            methodCode.add(varName + " = value;");
            if (record.isOptional())
            {
                methodCode.add("setPresenceVector(" + pvIndex + ");");
            }
            // Create framework for parent reference
            methodCode.add(JavaCode.getParentReferenceSetParentPVLine());
            code.methods.addAll(JavaCode.createMethodDefinition("public void", "set", shortClassName, methodParam, methodCode, false));
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            methodParam.add(shortClassName + " value");
            methodCode.add(varName + " = value;");
            if (record.isOptional())
            {
                methodCode.add("setPresenceVector(" + pvIndex + ");");
            }
            // Create framework for parent reference
            methodCode.add(CSharpCode.getParentReferenceSetParentPVLine());
            code.methods.addAll(CSharpCode.createMethodDefinition("public void", "set", shortClassName, methodParam, methodCode, false));
        }
    }

    private void generateInstanceOverloadAssignmentMethod(CodeLines code)
    {
        /// Overload the operator=
        /// Add code to the assignment lines of code]
        if (codeType == CodeLines.CodeType.C_SHARP)
        {
            code.assignmentLines.add(varName + " = value.get" + varName.substring(varName.indexOf(CSharpCode.VARIABLE_PREFIX)+2) + "();");
            // Create framework for parent reference
            code.assignmentLines.add(CSharpCode.getParentReferenceVariableSetParent(varName));
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            code.assignmentLines.add(varName + " = value." + varName + ";");
            // Create framework for parent reference
            code.assignmentLines.add(JavaCode.getParentReferenceVariableSetParent(varName));
        }
        else
        {
            code.assignmentLines.add(varName + " = value." + varName + ";");
            // Create framework for parent reference
            code.assignmentLines.add(CppCode.getParentReferenceVariableSetParent(varName));
        }
    }

    private void generateInstanceOverloadIsEqualMethod(CodeLines code)
    {
        /// Overload operator==
        /// Add code for checking equality
        if (!code.equalLines.isEmpty())
        {
            code.equalLines.add("");
        }
        if (codeType == CodeLines.CodeType.C_SHARP)
        {
            code.equalLines.add("if (!this.get" + varName.substring(varName.indexOf(CSharpCode.VARIABLE_PREFIX)+2)
                    + "().isEqual(value.get" + varName.substring(varName.indexOf(CSharpCode.VARIABLE_PREFIX)+2) + "()))");
        }
        else if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            code.equalLines.add("if (" + varName + " != value." + varName + ")");
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            code.equalLines.add("if (!" + varName + ".isEqual(value.get" + Util.upperCaseFirstLetter(shortClassName) + "()))");
        }
        code.equalLines.add("{");
        code.equalLines.add("\treturn false;");
        code.equalLines.add("}");
    }

    private void generateInstanceOverloadNotEqualMethod(CodeLines code)
    {
        /// Overload operator!=
        /// Add code checking inequality
        if (!code.notEqualLines.isEmpty())
        {
            code.notEqualLines.add("");
        }
        if (codeType == CodeLines.CodeType.C_SHARP)
        {
            code.notEqualLines.add("if (!this.get" + varName.substring(varName.indexOf(CSharpCode.VARIABLE_PREFIX)+2)
                    + "().notEquals(value.get" + varName.substring(varName.indexOf(CSharpCode.VARIABLE_PREFIX)+2) + "()))");
        }
        else if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            code.notEqualLines.add("if (" + varName + " == value." + varName + ")");
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            code.notEqualLines.add("if (" + varName + ".isEqual(value.get" + Util.upperCaseFirstLetter(shortClassName) + "()))");
        }
        code.notEqualLines.add("{");
        code.notEqualLines.add("\treturn false;");
        code.notEqualLines.add("}");
    }

    /**
     * Generates the Code for a Record if is a Vector
     * @param pvIndex
     * @param code
     */
    private void generateRecordVector(int pvIndex, CodeLines code)
    {
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
            CppCode.addParentReferenceConstructorSetParrentInArray(code, varName);

            code.protectedAttributes.add(CppCode.createVariableDeclaration("std::vector<" + shortClassName + ">", shortClassName, false));
            code.constructorLines.add("/// No Initialization of " + varName + " necessary.");
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {           
            code.protectedAttributes.add(JavaCode.createVariableDeclaration("protected ArrayList<" + shortClassName + ">", shortClassName, false));
            code.constructorLines.add(varName + " = new ArrayList<" + shortClassName + ">();");

            /*
             * Create framework for parent reference
             */
            JavaCode.addParentReferenceConstructorSetParrentInArray(code, varName);
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            
            code.protectedAttributes.add(CSharpCode.createVariableDeclaration("protected List<" + shortClassName + ">", shortClassName, false));
            code.constructorLines.add(varName + " = new List<" + shortClassName + ">();");

            /*
             * Create framework for parent reference
             */
            CSharpCode.addParentReferenceConstructorSetParrentInArray(code, varName);
        }

        generateVectorGetSizeMethod(code, pvIndex);

        generateVectorIsValidMethod(code, pvIndex);

        generateVectorEncodeMethod(code, pvIndex);
        generateVectorDecodeMethod(code, pvIndex);

        generateVectorGetNumberElementsMethod(code);
        generateVectorGetElementMethod(code);

        generateVectorSetElementMethod(code);
        generateVectorAddElementMethod(code);

        generateVectorDeleteElementMethod(code);
        generateVectorDeleteLastElementMethod(code);

        generateVectorOverloadAssignmentMethod(code, pvIndex);
        generateVectorOverloadIsEqualMethod(code, pvIndex);
        generateVectorOverloadNotEqualMethod(code, pvIndex);
    }

    private void generateVectorGetSizeMethod(CodeLines code, int pvIndex)
    {
        /*
         * GetSize method
         * only add size of array if optional is true
         */
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            methodCode.add("size += sizeof(" + CppCode.getVariableType(countFieldType) + ");");
            methodCode.add("for (int i = 0; i < " + varName + ".size(); i++)");
            methodCode.add("{");
            methodCode.add("	size += " + varName + "[i].getSize();");
            methodCode.add("}");
            if (record.isOptional())
            {
                methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
            }
            code.sizeLines.addAll(methodCode);
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            JavaCode.generateGetSizeLines(JavaCode.getVariableType(countFieldType), methodCode, countFieldSigned);
            methodCode.add("for (int i = 0; i < " + varName + ".size(); i++)");
            methodCode.add("{");
            methodCode.add("	size += " + varName + ".get(i).getSize();");
            methodCode.add("}");
            if (record.isOptional())
            {
                methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
            }
            code.sizeLines.addAll(methodCode);
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            CSharpCode.generateSizeLines(CSharpCode.getVariableType(countFieldType), methodCode);
            methodCode.add("for (int i = 0; i < " + varName + ".Count; i++)");
            methodCode.add("{");
            methodCode.add("	size += " + varName + "[i].getSize();");
            methodCode.add("}");
            if (record.isOptional())
            {
                methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
            }
            code.sizeLines.addAll(methodCode);
        }
    }

    private void generateVectorIsValidMethod(CodeLines code, int pvIndex)
    {
        /*
         * is[Field]valid added if optional
         * return true if field is set in presence vector
         */
        if (record.isOptional())
        {
            if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                code.publicMethods.add(CppCode.createMethodDeclaration("bool", "is", shortClassName + "Valid", null, true));
                methodCode.clear();
                methodCode.add("return true;");
                methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                methodCode.add("return false;");
                code.methods.addAll(CppCode.createMethodDefinition("bool", parentClassName + "::is", shortClassName + "Valid", null, methodCode, true));
            }
            else if (codeType == CodeLines.CodeType.JAVA)
            {
                methodCode.clear();
                methodCode.add("return true;");
                methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                methodCode.add("return false;");
                code.methods.addAll(JavaCode.createMethodDefinition("public boolean", "is", shortClassName + "Valid", null, methodCode, true));
            }
            else if (codeType == CodeLines.CodeType.C_SHARP)
            {
                methodCode.clear();
                methodCode.add("return true;");
                methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
                methodCode.add("return false;");
                code.methods.addAll(CSharpCode.createMethodDefinition("public bool", "is", shortClassName + "Valid", null, methodCode, true));
            }

        }
    }

    private void generateVectorEncodeMethod(CodeLines code, int pvIndex)
    {
        /* 	m_Record1.encode(bytes + pos);
         *	pos += m_Record1.getSize();
         */
        methodCode.clear();

        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            /* First set the count */
            methodCode.add(CppCode.getVariableType(countFieldType) + " size = (" + CppCode.getVariableType(countFieldType) + ") " + varName + ".size();");
            methodCode.add("memcpy( bytes, &size, sizeof(size));");
            methodCode.add("pos += sizeof(size);");

            /* now serialize each element */
            methodCode.add("for (int i = 0; i < " + varName + ".size(); i++)");
            methodCode.add("{");
            methodCode.add("\t" + varName + "[i].encode(bytes + pos);");
            methodCode.add("\tpos += " + varName + "[i].getSize();");
            methodCode.add("}");
            if (record.isOptional())
            {
                methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {

            /* First set the count */
            methodCode.add(JavaCode.getVariableType(countFieldType) + " size = (" + JavaCode.getVariableType(countFieldType) + ") " + varName + ".size();");
            JavaCode.generateEncoderLines(JavaCode.getVariableType(countFieldType), "size", methodCode, countFieldSigned);

            /* now serialize each element */
            methodCode.add("for (int i = 0; i < " + varName + ".size(); i++)");
            methodCode.add("{");
            methodCode.add("\t" + varName + ".get(i).encode(bytes, pos);");
            methodCode.add("\tpos += " + varName + ".get(i).getSize();");
            methodCode.add("}");
            if (record.isOptional())
            {
                methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            /* First set the count */
            methodCode.add(CSharpCode.getVariableType(countFieldType) + " size = (" + CSharpCode.getVariableType(countFieldType) + ") " + varName + ".Count;");
            CSharpCode.generateEncoderLines(CSharpCode.getVariableType(countFieldType), "size", methodCode);

            /* now serialize each element */
            methodCode.add("for (int i = 0; i < " + varName + ".Count; i++)");
            methodCode.add("{");
            methodCode.add("\t" + varName + "[i].encode(bytes, pos);");
            methodCode.add("\tpos += " + varName + "[i].getSize();");
            methodCode.add("}");
            if (record.isOptional())
            {
                methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        code.encoderLines.addAll(methodCode);
    }

    private void generateVectorDecodeMethod(CodeLines code, int pvIndex)
    {
        /* 	m_Record1.decode(bytes + pos);
         *	pos += m_Record1.getSize();
         */
        methodCode.clear();

        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            /* pull the count */
            methodCode.add(CppCode.getVariableType(countFieldType) + " size;");
            methodCode.add("memcpy( &size, bytes, sizeof(size));");
            methodCode.add("pos += sizeof(size);");
            methodCode.add(varName + ".resize(size);");

            /* deserialize each element */
            methodCode.add("for (int i = 0; i < " + varName + ".size(); i++)");
            methodCode.add("{");
            methodCode.add("\t" + varName + "[i].decode(bytes + pos);");
            methodCode.add("\tpos += " + varName + "[i].getSize();");
            methodCode.add("}");
            if (record.isOptional())
            {
                methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            /* pull the count */
            methodCode.add(JavaCode.getVariableType(countFieldType) + " size;");
            JavaCode.generateDecoderLines(JavaCode.getVariableType(countFieldType), "size", methodCode, countFieldSigned);

            /* deserialize each element */
            methodCode.add(varName + " = new ArrayList<" + shortClassName +  ">();");
            methodCode.add("for (int i = 0; i <  size; i++)");
            methodCode.add("{");
            methodCode.add("\t" + shortClassName + " item = new " + shortClassName + "();");
            methodCode.add("\titem.decode(bytes, pos);");
            methodCode.add("\t" + varName + ".add(item);");
            methodCode.add("\tpos += item.getSize();");
            methodCode.add("}");            
            if (record.isOptional())
            {
                methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            /* pull the count */
            methodCode.add(CSharpCode.getVariableType(countFieldType) + " size;");
            CSharpCode.generateDecoderLines(CSharpCode.getVariableType(countFieldType), "size", methodCode);

            methodCode.add(varName + " = new List<" + shortClassName + ">();");

            /* deserialize each element */
            methodCode.add("for (int i = 0; i < size; i++)");
            methodCode.add("{");
            methodCode.add("\t" + varName + ".Add( new " + shortClassName +"());");
            methodCode.add("\t" + varName + "[i].decode(bytes, pos);");
            methodCode.add("\tpos += " + varName + "[i].getSize();");
            methodCode.add("}");
            if (record.isOptional())
            {
                methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        code.decoderLines.addAll(methodCode);
    }

    private void generateVectorGetNumberElementsMethod(CodeLines code)
    {
        /// getNumberOfElements()
        methodCode.clear();
        methodParam.clear();
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            code.publicMethods.add(CppCode.createMethodDeclaration("unsigned int", "get", "NumberOfElements", null, true));
            methodCode.add("return (unsigned int) " + varName + ".size();");
            code.methods.addAll(CppCode.createMethodDefinition("unsigned int", parentClassName + "::get", "NumberOfElements", null, methodCode, true));
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            methodCode.add("return (long) " + varName + ".size();");
            code.methods.addAll(JavaCode.createMethodDefinition("public long", "get", "NumberOfElements", null, methodCode, true));
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            methodCode.add("return (int) " + varName + ".Count;");
            code.methods.addAll(CSharpCode.createMethodDefinition("public int", "get", "NumberOfElements", null, methodCode, true));
        }
    }

    private void generateVectorGetElementMethod(CodeLines code)
    {
        /// getElement(index)
        methodCode.clear();
        methodParam.clear();
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            methodParam.add("unsigned int " + "index");
            code.publicMethods.add(CppCode.createMethodDeclaration(shortClassName + "* const", "get", "Element", methodParam, false));
            methodCode.add("return &" + varName + ".at(index);");
            code.methods.addAll(CppCode.createMethodDefinition(fullClassName + "* const", parentClassName + "::get", "Element", methodParam, methodCode, false));
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            methodParam.add("int " + "index");
            methodCode.add("return " + varName + ".get(index);");
            code.methods.addAll(JavaCode.createMethodDefinition("public " + fullClassName, "get", "Element", methodParam, methodCode, false));
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            methodParam.add("int " + "index");
            methodCode.add("return " + varName + "[index];");
            code.methods.addAll(CSharpCode.createMethodDefinition("public " + fullClassName, "get", "Element", methodParam, methodCode, false));
        }
    }

    private void generateVectorSetElementMethod(CodeLines code)
    {
        /// setElement(index,value)
        methodCode.clear();
        methodParam.clear();
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            methodParam.add("unsigned int " + "index");
            methodParam.add("const " + shortClassName + " &value");
            code.publicMethods.add(CppCode.createMethodDeclaration("int", "set", "Element", methodParam, false));
            methodCode.add("if(" + varName + ".size()-1 < index)");
            methodCode.add("{");
            methodCode.add("\treturn 1;");
            methodCode.add("}");
            methodCode.add("");

            methodCode.add(varName + ".at(index) = value;");

            // Create framework for parent reference
            methodCode.add(CppCode.getParentReferenceVariableSetParent(varName + ".at(index)"));
            methodCode.add(CppCode.getParentReferenceSetParentPVLine());

            methodCode.add("return 0;");
            code.methods.addAll(CppCode.createMethodDefinition("int", parentClassName + "::set", "Element", methodParam, methodCode, false));
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            methodParam.add("int " + "index");
            methodParam.add(shortClassName + " value");
            methodCode.add("if(" + varName + ".size()-1 < index)");
            methodCode.add("{");
            methodCode.add("\treturn;");
            methodCode.add("}");
            methodCode.add("");
            methodCode.add(varName + ".set(index, value);");

            // Create framework for parent reference
            methodCode.add(JavaCode.getParentReferenceVariableSetParent(varName + ".get(index)"));
            methodCode.add(JavaCode.getParentReferenceSetParentPVLine());

            code.methods.addAll(JavaCode.createMethodDefinition("public void", "set", "Element", methodParam, methodCode, false));
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            methodParam.add("int " + "index");
            methodParam.add(shortClassName + " value");
            methodCode.add("if(" + varName + ".Count-1 < index)");
            methodCode.add("{");
            methodCode.add("\treturn;");
            methodCode.add("}");
            methodCode.add("");
            methodCode.add(varName + "[index] = value;");
            // Create framework for parent reference
            methodCode.add(CSharpCode.getParentReferenceVariableSetParent(varName + "[index]"));
            methodCode.add(CSharpCode.getParentReferenceSetParentPVLine());
            code.methods.addAll(CSharpCode.createMethodDefinition("public void", "set", "Element", methodParam, methodCode, false));
        }
    }

    private void generateVectorAddElementMethod(CodeLines code)
    {
        /// addElement(value)
        methodCode.clear();
        methodParam.clear();
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            methodParam.add("const " + shortClassName + " &value");
            code.publicMethods.add(CppCode.createMethodDeclaration("int", "add", "Element", methodParam, false));

            methodCode.add(varName + ".push_back(value);");

            // Create framework for parent reference
            methodCode.add(CppCode.getParentReferenceVariableSetParent(varName + ".back()"));
            methodCode.add(CppCode.getParentReferenceSetParentPVLine());

            methodCode.add("return 0;");
            code.methods.addAll(CppCode.createMethodDefinition("int", parentClassName + "::add", "Element", methodParam, methodCode, false));
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            methodParam.add(shortClassName + " value");
            methodCode.add(varName + ".add(value);");

            // Create framework for parent reference
            methodCode.add(JavaCode.getParentReferenceVariableSetParent(varName + ".get(" + varName + ".size() -1 )"));
            methodCode.add(JavaCode.getParentReferenceSetParentPVLine());
            code.methods.addAll(JavaCode.createMethodDefinition("public void", "add", "Element", methodParam, methodCode, false));
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            methodParam.add(shortClassName + " value");
            methodCode.add(varName + ".Add(value);");

            // Create framework for parent reference
            methodCode.add(CSharpCode.getParentReferenceVariableSetParent(varName + "[" + varName + ".Count-1]"));
            methodCode.add(CSharpCode.getParentReferenceSetParentPVLine());
            code.methods.addAll(CSharpCode.createMethodDefinition("public void", "add", "Element", methodParam, methodCode, false));
        }
    }

    private void generateVectorDeleteElementMethod(CodeLines code)
    {
        /// deleteElement(index)
        methodCode.clear();
        methodParam.clear();
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            methodParam.add("unsigned int " + "index");
            code.publicMethods.add(CppCode.createMethodDeclaration("int", "delete", "Element", methodParam, false));
            methodCode.add("if(" + varName + ".size()-1 < index)");
            methodCode.add("{");
            methodCode.add("\treturn 1;");
            methodCode.add("}");
            methodCode.add("");
            methodCode.add(varName + ".erase(" + varName + ".begin()+index);");
            methodCode.add("return 0;");
            code.methods.addAll(CppCode.createMethodDefinition("int", parentClassName + "::delete", "Element", methodParam, methodCode, false));
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            methodParam.add("int " + "index");
            methodCode.add("if(" + varName + ".size()-1 < index)");
            methodCode.add("{");
            methodCode.add("\treturn 1;");
            methodCode.add("}");
            methodCode.add("");
            methodCode.add(varName + ".remove(index);");
            methodCode.add("return 0;");
            code.methods.addAll(JavaCode.createMethodDefinition("public int", "delete", "Element", methodParam, methodCode, false));
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            methodParam.add("int " + "index");
            methodCode.add("if(" + varName + ".Count-1 < index)");
            methodCode.add("{");
            methodCode.add("\treturn;");
            methodCode.add("}");
            methodCode.add("");
            methodCode.add(varName + ".RemoveAt(index);");
            code.methods.addAll(CSharpCode.createMethodDefinition("public void", "delete", "Element", methodParam, methodCode, false));
        }
    }

    private void generateVectorDeleteLastElementMethod(CodeLines code)
    {
        /// deleteLastElement()
        methodCode.clear();
        methodParam.clear();
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            code.publicMethods.add(CppCode.createMethodDeclaration("int", "delete", "LastElement", methodParam, false));
            methodCode.add(varName + ".pop_back();");
            methodCode.add("return 0;");
            code.methods.addAll(CppCode.createMethodDefinition("int", parentClassName + "::delete", "LastElement", methodParam, methodCode, false));
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            methodCode.add(varName + ".remove(" + varName + ".size()-1);");
            methodCode.add("return 0;");
            code.methods.addAll(JavaCode.createMethodDefinition("public int", "delete", "LastElement", methodParam, methodCode, false));
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            methodCode.add(varName + ".RemoveAt(" + varName + ".Count-1);");
            code.methods.addAll(CSharpCode.createMethodDefinition("public void", "delete", "LastElement", methodParam, methodCode, false));
        }
    }

    private void generateVectorOverloadAssignmentMethod(CodeLines code, int pvIndex)
    {
        /// Overload the operator=
        /// Add code to the assignment lines of code
        methodCode.clear();
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            methodCode.add(varName + ".clear();");
            methodCode.add("");
            methodCode.add("for (int i = 0; i < value." + varName + ".size(); i++)");
            methodCode.add("{");
            methodCode.add("\t" + varName + ".push_back(value." + varName + "[i]);");
            // Create framework for parent reference
            methodCode.add("\t" + CppCode.getParentReferenceVariableSetParentInVector(varName));
            methodCode.add("}");
            if (record.isOptional())
            {
                methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            methodCode.add(varName + ".clear();");
            methodCode.add("");
            methodCode.add("for (int i = 0; i < value." + varName + ".size(); i++)");
            methodCode.add("{");
            methodCode.add("\t" + varName + ".add(value." + varName + ".get(i));");
            // Create framework for parent reference
            methodCode.add("\t" + JavaCode.getParentReferenceVariableSetParentInVector(varName));
            methodCode.add("}");
            if (record.isOptional())
            {
                methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            methodCode.add(varName + ".Clear();");
            methodCode.add("");
            methodCode.add("for (int i = 0; i < value." + varName + ".Count; i++)");
            methodCode.add("{");
            methodCode.add("\t" + varName + ".Add(value." + varName + "[i]);");
            // Create framework for parent reference
            methodCode.add("\t" + CSharpCode.getParentReferenceVariableSetParentInVector(varName));
            methodCode.add("}");
            if (record.isOptional())
            {
                methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        code.assignmentLines.addAll(methodCode);
    }

    private void generateVectorOverloadIsEqualMethod(CodeLines code, int pvIndex)
    {
        /// Overload operator==
        /// Add code for checking equality
        methodCode.clear();
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            methodCode.add("for (int i = 0; i < " + varName + ".size(); i++)");
            methodCode.add("{");
            methodCode.add("\tif (" + varName + "[i] !=  value." + varName + "[i])");
            methodCode.add("\t{");
            methodCode.add("\t\treturn false;");
            methodCode.add("\t}");
            methodCode.add("}");
            if (record.isOptional())
            {
                methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            methodCode.add("for (int i = 0; i < " + varName + ".size(); i++)");
            methodCode.add("{");
            methodCode.add("\tif (!" + varName + ".get(i).isEqual(value.getElement(i)))");
            methodCode.add("\t{");
            methodCode.add("\t\treturn false;");
            methodCode.add("\t}");
            methodCode.add("}");
            if (record.isOptional())
            {
                methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            methodCode.add("for (int i = 0; i < value." + varName + ".Count; i++)");
            methodCode.add("{");
            methodCode.add("\tif (!this." + varName + "[i].isEqual(value." + varName + "[i]))");
            methodCode.add("\t{");
            methodCode.add("\t\treturn false;");
            methodCode.add("\t}");
            methodCode.add("}");
            if (record.isOptional())
            {
                methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        if (!code.equalLines.isEmpty())
        {
            code.equalLines.add("");
        }
        code.equalLines.addAll(methodCode);
    }

    private void generateVectorOverloadNotEqualMethod(CodeLines code, int pvIndex)
    {

        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            /// Overload operator!=
            /// Add code checking inequality
            methodCode.clear();
            methodCode.add("if (" + varName + " == value." + varName + ")");
            methodCode.add("{");
            methodCode.add("\treturn false;");
            methodCode.add("}");
            if (record.isOptional())
            {
                methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            /// Overload operator!=
            /// Add code checking inequality
            methodCode.clear();
            methodCode.add("for (int i = 0; i < " + varName + ".size(); i++)");
            methodCode.add("{");
            methodCode.add("\tif (" + varName + ".get(i).isEqual(value.getElement(i)))");
            methodCode.add("\t{");
            methodCode.add("\t\treturn false;");
            methodCode.add("\t}");
            methodCode.add("}");
            if (record.isOptional())
            {
                methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);

            }
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            /// add implementation of notEquals that reuses isEqual 
            methodCode.clear();
            methodCode.add("if (this.isEqual(value))");
            methodCode.add("{");
            methodCode.add("\treturn false;");
            methodCode.add("}");
            if (record.isOptional())
            {

                methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);

            }
        }
        if (!code.notEqualLines.isEmpty())
        {
            code.notEqualLines.add("");
        }
        code.notEqualLines.addAll(methodCode);
    }
}
