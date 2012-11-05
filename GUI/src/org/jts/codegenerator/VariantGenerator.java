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
 * @(#)ListGenerator.java		0.1 2008/10/28
 * 
 * Copyright
 */
package org.jts.codegenerator;

import java.util.Vector;

import org.jts.jsidl.binding.*;

public class VariantGenerator
{
    private CodeLines.CodeType codeType;
    private Variant variant;
    private boolean isList;
    private String countFieldType;
    private boolean countFieldSign;
    private Vector<String> methodCode;
    private Vector<String> methodParam;
    private String shortClassName;
    private String parentClassName;
    private String fullClassName;
    private String varName;

    public VariantGenerator(CodeLines.CodeType codeType, Variant variant)
    {
        this.codeType = codeType;
        this.variant = variant;
        this.isList = false;

        methodCode = new Vector<String>();
        methodParam = new Vector<String>();
        shortClassName = variant.getName();
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

    public VariantGenerator(CodeLines.CodeType codeType, Variant variant, boolean isList, String countFieldType)
    {
        this.codeType = codeType;
        this.variant = variant;
        this.isList = isList;
        this.countFieldType = countFieldType;

        methodCode = new Vector<String>();
        methodParam = new Vector<String>();
        shortClassName = variant.getName();
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            varName = CppCode.getVariableName(shortClassName);
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            varName = JavaCode.getVariableName(shortClassName);
            countFieldSign = JavaCode.getVariableSign(countFieldType);
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            varName = CSharpCode.getVariableName(shortClassName);
        }
    }

    /**
     * This method generates the code for creating a variant.
     * The generate Variant will be placed within its own class which
     * is nested within the encapsulating class.
     * @param code
     */
    public void run(String parentName, int pvIndex, CodeLines code)
    {
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            parentClassName = parentName;
            fullClassName = parentClassName + "::" + shortClassName;
            
            /*
             * Create framework for parent reference
             */
            CppCode.addParentReference(code, fullClassName, pvIndex, variant.isOptional());
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            parentClassName = parentName;
            fullClassName = parentClassName + "." + shortClassName;
            
            /*
             * Create framework for parent reference
             */
            JavaCode.addParentReference(code, fullClassName, pvIndex, variant.isOptional());
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            parentClassName = parentName;
            fullClassName = parentClassName + "." + shortClassName;
            /*
             * Create framework for parent reference
             */
            CSharpCode.addParentReference(code, fullClassName, pvIndex, variant.isOptional());
        }
        

        /* Create the Variant Class */
        /***************************/
        generateClass(code);

        if (isList)
        {
            generateVariantVector(pvIndex, code);
        }
        else
        {
            generateVariantInstance(pvIndex, code);
        }
    }

    /**
     *
     * @param parentClassName
     * @param shortClassName
     * @param code
     */
    private void generateClass(CodeLines code)
    {
        CodeLines varCode = new CodeLines("", codeType, code.getNameSpace());
        String valueVariableName = "";

        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            // FieldValue is based on user selected type
            String fieldType = CppCode.getVariableType(variant.getVtagField().getFieldTypeUnsigned());
            valueVariableName = CppCode.getVariableName("fieldValue");
            String tempValueVariableName = valueVariableName + "Temp";

            code.protectedAttributes.add(CppCode.createVariableDeclaration(fieldType, "fieldValue", false));

            // Add fieldValue support for copy and comparison operators
            code.equalLines.add("if (" + valueVariableName + " != value." + valueVariableName + ")");
            code.equalLines.add("{");
            code.equalLines.add("\treturn false;");
            code.equalLines.add("}");
            code.notEqualLines.add("if (" + valueVariableName + " == value." + valueVariableName + ")");
            code.notEqualLines.add("{");
            code.notEqualLines.add("\treturn false;");
            code.notEqualLines.add("}");
            code.assignmentLines.add(valueVariableName + " = value." + valueVariableName + ";");
            code.constructorLines.add("m_FieldValue = 0;"); // need to the field value to a default.

            // *****
            // We need to add switch case wrappers around the the size/encode/decode variant class methods
            // so that when a variant index is selected at runtime, only that index is processed
            // NOTE: we still need to create classes for all the fields within the variant because
            // we don't know which one will be selected at runtime

            // GETSIZE METHOD
            // switch on value index
            code.sizeLines.add("size += sizeof(" + fieldType + ");");
            code.sizeLines.add("");
            code.sizeLines.add("switch(" + valueVariableName + ")");
            code.sizeLines.add("{");

            // ENCODE METHOD
            // encode index value
            code.encoderLines.add(fieldType + " " + tempValueVariableName + ";");
            code.encoderLines.add("");
            code.encoderLines.add(tempValueVariableName + " = " + code.getNameSpace() + "::correctEndianness(" + valueVariableName + ");");
            code.encoderLines.add("memcpy(bytes + pos, &" + tempValueVariableName + ", sizeof(" + fieldType + "));");
            code.encoderLines.add("pos += sizeof(" + fieldType + ");");
            code.encoderLines.add("");
            // switch on value index
            code.encoderLines.add("switch(" + valueVariableName + ")");
            code.encoderLines.add("{");

            // DECODE METHOD
            // decode index value
            code.decoderLines.add(fieldType + " " + tempValueVariableName + ";");
            code.decoderLines.add("");
            code.decoderLines.add("memcpy(&" + tempValueVariableName + ", bytes + pos, sizeof(" + fieldType + "));");
            code.decoderLines.add(valueVariableName + " = " + code.getNameSpace() + "::correctEndianness(" + tempValueVariableName + ");");
            code.decoderLines.add("pos += sizeof(" + fieldType + ");");
            code.decoderLines.add("");
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            // FieldValue is based on user selected type
            String fieldType = JavaCode.getVariableType(variant.getVtagField().getFieldTypeUnsigned());
            boolean fieldSigned = JavaCode.getVariableSign(variant.getVtagField().getFieldTypeUnsigned());
            valueVariableName = JavaCode.getVariableName("fieldValue");
            String tempValueVariableName = valueVariableName + "Temp";

            code.protectedAttributes.add(JavaCode.createVariableDeclaration("protected " + fieldType, "fieldValue", false));

            // Add fieldValue support for copy and comparison operators
            code.equalLines.add("if (" + valueVariableName + " != value." + valueVariableName + ")");
            code.equalLines.add("{");
            code.equalLines.add("\treturn false;");
            code.equalLines.add("}");
            code.notEqualLines.add("if (" + valueVariableName + " == value." + valueVariableName + ")");
            code.notEqualLines.add("{");
            code.notEqualLines.add("\treturn false;");
            code.notEqualLines.add("}");
            code.assignmentLines.add(valueVariableName + " = value." + valueVariableName + ";");

            // *****
            // We need to add switch case wrappers around the the size/encode/decode variant class methods
            // so that when a variant index is selected at runtime, only that index is processed
            // NOTE: we still need to create classes for all the fields within the variant because
            // we don't know which one will be selected at runtime

            // GETSIZE METHOD
            // switch on value index
            JavaCode.generateGetSizeLines(fieldType, code.sizeLines, fieldSigned);
            code.sizeLines.add("");
            code.sizeLines.add("switch(" + valueVariableName + ")");
            code.sizeLines.add("{");

            // ENCODE METHOD
            // encode index value
            code.encoderLines.add("try");
            code.encoderLines.add("{");
            JavaCode.generateEncoderLines(fieldType, valueVariableName, code.encoderLines, fieldSigned);
            code.encoderLines.add("}");
            code.encoderLines.add("catch (Exception e)");
            code.encoderLines.add("{");
            code.encoderLines.add("\tlogger.log(Level.SEVERE, null, e);");
            code.encoderLines.add("}");
            code.encoderLines.add("");
            // switch on value index
            code.encoderLines.add("switch(" + valueVariableName + ")");
            code.encoderLines.add("{");

            // DECODE METHOD
            // decode index value
            code.decoderLines.add("");
            code.decoderLines.add("try");
            code.decoderLines.add("{");
            JavaCode.generateDecoderLines(fieldType, valueVariableName, code.decoderLines, fieldSigned);
            code.decoderLines.add("}");
            code.decoderLines.add("catch (Exception e)");
            code.decoderLines.add("{");
            code.decoderLines.add("\tlogger.log(Level.SEVERE, null, e);");
            code.decoderLines.add("}");
            code.decoderLines.add("");
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            // FieldValue is based on user selected type
            String fieldType = CSharpCode.getVariableType(variant.getVtagField().getFieldTypeUnsigned());
            valueVariableName = CSharpCode.getVariableName("fieldValue");
            String tempValueVariableName = valueVariableName + "Temp";

            code.protectedAttributes.add(CSharpCode.createVariableDeclaration(fieldType, "fieldValue", true));

            // Add fieldValue support for copy and comparison operators
            code.equalLines.add("if (this." + valueVariableName + " != value." + valueVariableName + ")");
            code.equalLines.add("{");
            code.equalLines.add("\treturn false;");
            code.equalLines.add("}");
            code.notEqualLines.add("if (this." + valueVariableName + " == value." + valueVariableName + ")");
            code.notEqualLines.add("{");
            code.notEqualLines.add("\treturn false;");
            code.notEqualLines.add("}");
            code.assignmentLines.add(valueVariableName + " = value." + valueVariableName + ";");

            // *****
            // We need to add switch case wrappers around the the size/encode/decode variant class methods
            // so that when a variant index is selected at runtime, only that index is processed
            // NOTE: we still need to create classes for all the fields within the variant because
            // we don't know which one will be selected at runtime

            // GETSIZE METHOD
            // switch on value index
            code.sizeLines.add("size += sizeof(" + fieldType + ");");
            code.sizeLines.add("");
            code.sizeLines.add("switch(" + valueVariableName + ")");
            code.sizeLines.add("{");

            // ENCODE METHOD
            // encode index value
            CSharpCode.generateEncoderLines(fieldType, valueVariableName, code.encoderLines);
            // switch on value index
            code.encoderLines.add("switch(" + valueVariableName + ")");
            code.encoderLines.add("{");

            // DECODE METHOD
            // decode index value
            CSharpCode.generateDecoderLines(fieldType, valueVariableName, code.decoderLines);
        }

        // switch on value index
        code.decoderLines.add("switch(" + valueVariableName + ")");
        code.decoderLines.add("{");

        int index = 0;
        for (Object field : variant.getRecordOrDeclaredRecordOrList())
        {
            // each of the methods will need a case prefix for the current index
            code.sizeLines.add("\tcase " + index + ":");
            code.encoderLines.add("\tcase " + index + ":");
            code.decoderLines.add("\tcase " + index + ":");

            boolean fieldIsNested = true;
            String fieldName = "Unknown";

            varCode.clear();

            if (field instanceof Record)
            {
                Record record = (Record) field;

                fieldName = record.getName();
                if (record.isOptional())
                {
                }

                new RecordGenerator(codeType, record).run(fullClassName, 0, varCode, null);
            }
            else if (field instanceof DeclaredRecord)
            {
                /// TODO: Add support for all other field types
            }
            else if (field instanceof Sequence)
            {
                Sequence sequence = (Sequence) field;

                fieldName = sequence.getName();
                if (sequence.isOptional())
                {
                }
                /// TODO: Add support for all other field types
                new SequenceGenerator(codeType, sequence).run(fullClassName, 0, varCode);
            }
            else if (field instanceof DeclaredSequence)
            {
                /// TODO: Add support for all other field types
            }
            else if (field instanceof Variant)
            {
                Variant bvarient = (Variant) field;

                fieldName = bvarient.getName();
                if (bvarient.isOptional())
                {
                }

                new VariantGenerator(codeType, bvarient).run(fullClassName, 0, varCode);
            }
            else if (field instanceof DeclaredVariant)
            {
                /// TODO: Add support for all other field types
            }
            else if (field instanceof List)
            {
                List list = (List) field;

                fieldName = list.getName();
                if (list.isOptional())
                {
                }

                new ListGenerator(codeType, list).run(fullClassName, 0, varCode);
            }
            else if (field instanceof DeclaredList)
            {
                /// TODO: Add support for all other field types
            }
            else
            {
                throw new CodeGeneratorException("VariantGenerator: Unknown Field Encountered: " + field.getClass().getName());
            }

            //************************
            // post-process all nested lines so we can read the switch statements better
            for (int i = 0; i < varCode.sizeLines.size(); i++)
            {
                varCode.sizeLines.set(i, "\t\t" + varCode.sizeLines.get(i));
            }
            varCode.sizeLines.add("\t\tbreak;");

            for (int i = 0; i < varCode.encoderLines.size(); i++)
            {
                varCode.encoderLines.set(i, "\t\t" + varCode.encoderLines.get(i));
            }
            varCode.encoderLines.add("\t\tbreak;");

            for (int i = 0; i < varCode.decoderLines.size(); i++)
            {
                varCode.decoderLines.set(i, "\t\t" + varCode.decoderLines.get(i));
            }
            varCode.decoderLines.add("\t\tbreak;");
            //************************

            code.addAll(varCode);

            index++;
        }

        // end the switch statements for each of the variant class methods getSize/encode/decode
        code.sizeLines.add("}");
        code.encoderLines.add("}");
        code.decoderLines.add("}");

        generateClassGetFieldValue(code);
        generateClassSetFieldValue(code);

        generateClassOverloadIsEqualMethod(code);
        generateClassOverloadNotEqualMethod(code);

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

    private void generateClassGetFieldValue(CodeLines code)
    {
        /// getFieldValue()
        methodCode.clear();
        methodParam.clear();
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            // FieldValue is based on user selected type
            String fieldType = CppCode.getVariableType(variant.getVtagField().getFieldTypeUnsigned());
            code.publicMethods.add(CppCode.createMethodDeclaration(fieldType, "get", "FieldValue", null, true));
            methodCode.add("return " + "m_FieldValue;");
            code.methods.addAll(CppCode.createMethodDefinition(fieldType, fullClassName + "::get", "FieldValue", null, methodCode, true));
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            // FieldValue is based on user selected type
            String fieldType = JavaCode.getVariableType(variant.getVtagField().getFieldTypeUnsigned());
            methodCode.add("return " + "m_FieldValue;");
            code.methods.addAll(JavaCode.createMethodDefinition("public " + fieldType, "get", "FieldValue", null, methodCode, true));
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            // FieldValue is based on user selected type
            String fieldType = CSharpCode.getVariableType(variant.getVtagField().getFieldTypeUnsigned());
            methodCode.add("return " + "m_FieldValue;");
            code.methods.addAll(CSharpCode.createMethodDefinition("public " + fieldType, "get", "FieldValue", null, methodCode, true));
        }
    }

    private void generateClassSetFieldValue(CodeLines code)
    {
        // FieldValue is based on user selected type
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            String fieldType = CppCode.getVariableType(variant.getVtagField().getFieldTypeUnsigned());

            /// setFieldValue()
            methodCode.clear();
            methodParam.clear();
            methodParam.add(fieldType + " " + "fieldValue");
            code.publicMethods.add(CppCode.createMethodDeclaration("int", "set", "FieldValue", methodParam, false));

            //return error if fieldvalue is being set to greater than maxcount of variant
            methodCode.add("if(fieldValue > " + variant.getVtagField().getMaxCount() + ")");
            methodCode.add("{");
            methodCode.add("\treturn 1;");
            methodCode.add("}");
            methodCode.add("");
            methodCode.add("m_FieldValue = fieldValue;");
            // Create framework for parent reference
            methodCode.add(CppCode.getParentReferenceSetParentPVLine());
            methodCode.add("return 0;");
            code.methods.addAll(CppCode.createMethodDefinition("int", fullClassName + "::set", "FieldValue", methodParam, methodCode, false));
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            String fieldType = JavaCode.getVariableType(variant.getVtagField().getFieldTypeUnsigned());

            /// setFieldValue()
            methodCode.clear();
            methodParam.clear();
            methodParam.add(fieldType + " " + "fieldValue");

            //return error if fieldvalue is being set to greater than maxcount of variant
            methodCode.add("if(fieldValue > " + variant.getVtagField().getMaxCount() + ")");
            methodCode.add("{");
            methodCode.add("\treturn;");
            methodCode.add("}");
            methodCode.add("");
            methodCode.add("m_FieldValue = fieldValue;");
            // Create framework for parent reference
            methodCode.add(JavaCode.getParentReferenceSetParentPVLine());
            code.methods.addAll(JavaCode.createMethodDefinition("public void", "set", "FieldValue", methodParam, methodCode, false));
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            String fieldType = CSharpCode.getVariableType(variant.getVtagField().getFieldTypeUnsigned());

            /// setFieldValue()
            methodCode.clear();
            methodParam.clear();
            methodParam.add(fieldType + " " + "fieldValue");

            //return error if fieldvalue is being set to greater than maxcount of variant
            methodCode.add("if(fieldValue > " + variant.getVtagField().getMaxCount() + ")");
            methodCode.add("{");
            methodCode.add("\treturn;");
            methodCode.add("}");
            methodCode.add("");
            methodCode.add("m_FieldValue = fieldValue;");
            // Create framework for parent reference
            methodCode.add(CSharpCode.getParentReferenceSetParentPVLine());
            code.methods.addAll(CSharpCode.createMethodDefinition("public void", "set", "FieldValue", methodParam, methodCode, false));
        }
    }

    private void generateClassOverloadIsEqualMethod(CodeLines code)
    {
        /// Overload operator==
        code.equalLines.add("");
        code.equalLines.add("return true;");
    }

    private void generateClassOverloadNotEqualMethod(CodeLines code)
    {
        /// Overload operator!=
        code.notEqualLines.add("");
        code.notEqualLines.add("return true;");
    }

    /**
     * Generate when not nested
     * @param pvIndex
     * @param code
     */
    private void generateVariantInstance(int pvIndex, CodeLines code)
    {
        /*
         * Create code lines for the encode, decode Methods
         * Create the getMethod and setMethod
         * This has to be done after the class wrapper has been added
         */

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
        /// GetSize method
        /// only add size of array if optional is true
        methodCode.clear();
        methodCode.add("size += " + varName + ".getSize();");
        if (variant.isOptional())
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
        /// is[Field]valid added if optional
        /// return true if field is set in presence vector
        if (variant.isOptional())
        {
            methodCode.clear();

            if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                code.publicMethods.add(CppCode.createMethodDeclaration("bool", "is", shortClassName + "Valid", null, true));

                methodCode.add("return true;");
                methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                methodCode.add("return false;");
                code.methods.addAll(CppCode.createMethodDefinition("bool", parentClassName + "::is", shortClassName + "Valid", null, methodCode, true));
            }
            else if (codeType == CodeLines.CodeType.JAVA)
            {
                methodCode.add("return true;");
                methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                methodCode.add("return false;");
                code.methods.addAll(JavaCode.createMethodDefinition("public boolean", "is", shortClassName + "Valid", null, methodCode, true));
            }
            else if (codeType == CodeLines.CodeType.C_SHARP)
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
        /* 	m_Record1.encode(bytes + pos);
         *	pos += m_Record1.getSize();
         */
        methodCode.clear();

        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            methodCode.add(varName + ".encode(bytes + pos);");
            methodCode.add("pos += " + varName + ".getSize();");

            if (variant.isOptional())
            {
                methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            methodCode.add(varName + ".encode(bytes, pos);");
            methodCode.add("pos += " + varName + ".getSize();");

            if (variant.isOptional())
            {
                methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            methodCode.add(varName + ".encode(bytes, pos);");
            methodCode.add("pos += " + varName + ".getSize();");

            if (variant.isOptional())
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

            if (variant.isOptional())
            {
                methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            methodCode.add(varName + ".decode(bytes, pos);");
            methodCode.add("pos += " + varName + ".getSize();");

            if (variant.isOptional())
            {
                methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            methodCode.add(varName + ".decode(bytes, pos);");
            methodCode.add("pos += " + varName + ".getSize();");

            if (variant.isOptional())
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
            if (variant.isOptional())
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
            if (variant.isOptional())
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
            if (variant.isOptional())
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
        // operator=
        code.assignmentLines.add(varName + " = value." + varName + ";");
        // Create framework for parent reference
        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            code.assignmentLines.add(CppCode.getParentReferenceVariableSetParent(varName));
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            code.assignmentLines.add(JavaCode.getParentReferenceVariableSetParent(varName));
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            code.assignmentLines.add(CSharpCode.getParentReferenceVariableSetParent(varName));
        }
    }

    private void generateInstanceOverloadIsEqualMethod(CodeLines code)
    {
        /// Overload operator==
        /// Add code for checking equality
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            if (!code.equalLines.isEmpty())
            {
                code.equalLines.add("");
            }
            code.equalLines.add("if (" + varName + " != value." + varName + ")");
            code.equalLines.add("{");
            code.equalLines.add("\treturn false;");
            code.equalLines.add("}");
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            if (!code.equalLines.isEmpty())
            {
                code.equalLines.add("");
            }
            code.equalLines.add("if (!" + varName + ".isEqual(value." + varName + "))");
            code.equalLines.add("{");
            code.equalLines.add("\treturn false;");
            code.equalLines.add("}");
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            if (!code.equalLines.isEmpty())
            {
                code.equalLines.add("");
            }
            code.equalLines.add("if (!" + varName + ".isEqual(value." + varName + "))");
            code.equalLines.add("{");
            code.equalLines.add("\treturn false;");
            code.equalLines.add("}");
        }
    }

    private void generateInstanceOverloadNotEqualMethod(CodeLines code)
    {
        /// Overload operator!=
        /// Add code checking inequality
        if (!code.notEqualLines.isEmpty())
        {
            code.notEqualLines.add("");
        }
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            code.notEqualLines.add("if (" + varName + " == value." + varName + ")");
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            code.notEqualLines.add("if (!this." + varName + ".notEquals(value." + varName + "))");
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            code.notEqualLines.add("if (!this." + varName + ".notEquals(value." + varName + "))");
        }
        code.notEqualLines.add("{");
        code.notEqualLines.add("\treturn false;");
        code.notEqualLines.add("}");
    }

    /**
     * Generates the Code for a Variant if is a Vector
     * @param parentClassName
     * @param shortClassName
     * @param pvIndex
     * @param code
     */
    private void generateVariantVector(int pvIndex, CodeLines code)
    {
        /*
         * Create code lines for the encode, decode Methods
         * Create the getMethod and setMethod
         * This has to be done after the class wrapper has been added
         */
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

        generateVectorGetNumberMethod(code);

        generateVectorGetMethod(code);
        generateVectorSetMethod(code);
        generateVectorAddMethod(code);

        generateVectorDeleteMethod(code);
        generateVectorDeleteLastMethod(code);

        generateVectorOverloadAssignmentMethod(code, pvIndex);
        generateVectorOverloadIsEqualMethod(code, pvIndex);
        generateVectorOverloadNotEqualMethod(code, pvIndex);
    }

    private void generateVectorGetSizeMethod(CodeLines code, int pvIndex)
    {
        methodCode.clear();

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
            if (variant.isOptional())
            {
                methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            JavaCode.generateGetSizeLines(JavaCode.getVariableType(countFieldType), methodCode, countFieldSign);
            methodCode.add("for (int i = 0; i < " + varName + ".size(); i++)");
            methodCode.add("{");
            methodCode.add("	size += " + varName + ".get(i).getSize();");
            methodCode.add("}");
            if (variant.isOptional())
            {
                methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            methodCode.add("size += JausUtils.getNumBytes(\"" + CSharpCode.getVariableType(countFieldType) + "\");");
            methodCode.add("for (int i = 0; i < " + varName + ".Count; i++)");
            methodCode.add("{");
            methodCode.add("	size += " + varName + "[i].getSize();");
            methodCode.add("}");
            if (variant.isOptional())
            {
                methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        code.sizeLines.addAll(methodCode);
    }

    private void generateVectorIsValidMethod(CodeLines code, int pvIndex)
    {
        /*
         * is[Field]valid added if optional
         * return true if field is set in presence vector
         */
        if (variant.isOptional())
        {
            methodCode.clear();

            if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                code.publicMethods.add(CppCode.createMethodDeclaration("bool", "is", shortClassName + "Valid", null, true));

                methodCode.add("return true;");
                methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                methodCode.add("return false;");
                code.methods.addAll(CppCode.createMethodDefinition("bool", parentClassName + "::is", shortClassName + "Valid", null, methodCode, true));
            }
            else if (codeType == CodeLines.CodeType.JAVA)
            {
                methodCode.add("return true;");
                methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                methodCode.add("return false;");
                code.methods.addAll(JavaCode.createMethodDefinition("public boolean", "is", shortClassName + "Valid", null, methodCode, true));
            }
            else if (codeType == CodeLines.CodeType.C_SHARP)
            {
                methodCode.add("return true;");
                methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
                methodCode.add("return false;");
                code.methods.addAll(CSharpCode.createMethodDefinition("public bool", "is", shortClassName + "Valid", null, methodCode, true));
            }
        }
    }

    private void generateVectorEncodeMethod(CodeLines code, int pvIndex)
    {
        methodCode.clear();

        /* 	m_Record1.encode(bytes + pos);
         *	pos += m_Record1.getSize();
         */

        /* First set the count */
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            methodCode.add(CppCode.getVariableType(countFieldType) + " size = (" + CppCode.getVariableType(countFieldType) + ") " + varName + ".size();");
            methodCode.add("memcpy( bytes, &size, sizeof(size));");
            methodCode.add("pos += sizeof(size);");

            /* now serialize each element */
            methodCode.add("for (int i = 0; i < " + varName + ".size(); i++)");
            methodCode.add("{");
            methodCode.add("\t" + varName + "[i].encode(bytes + pos);");
            methodCode.add("\tpos += " + varName + "[i].getSize();");
            methodCode.add("}");
            if (variant.isOptional())
            {
                methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            methodCode.add(JavaCode.getVariableType(countFieldType) + " size = (" + JavaCode.getVariableType(countFieldType) + ") " + varName + ".size();");
            JavaCode.generateEncoderLines(JavaCode.getVariableType(countFieldType), "size", methodCode, countFieldSign);

            /* now serialize each element */
            methodCode.add("for (int i = 0; i < " + varName + ".size(); i++)");
            methodCode.add("{");
            methodCode.add("\t" + varName + ".get(i).encode(bytes, pos);");
            methodCode.add("\tpos += " + varName + ".get(i).getSize();");
            methodCode.add("}");
            if (variant.isOptional())
            {
                methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            methodCode.add(CSharpCode.getVariableType(countFieldType) + " size = (" + CSharpCode.getVariableType(countFieldType) + ") " + varName + ".Count;");
            CSharpCode.generateEncoderLines(CSharpCode.getVariableType(countFieldType), "size", methodCode);

            /* now serialize each element */
            methodCode.add("for (int i = 0; i < " + varName + ".Count; i++)");
            methodCode.add("{");
            methodCode.add("\t" + varName + "[i].encode(bytes, pos);");
            methodCode.add("\tpos += " + varName + "[i].getSize();");
            methodCode.add("}");
            if (variant.isOptional())
            {
                methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        code.encoderLines.addAll(methodCode);
    }

    private void generateVectorDecodeMethod(CodeLines code, int pvIndex)
    {
        methodCode.clear();

        /* 	m_Record1.decode(bytes + pos);
         *	pos += m_Record1.getSize();
         */

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
            if (variant.isOptional())
            {
                methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            /* pull the count */
            methodCode.add(JavaCode.getVariableType(countFieldType) + " size;");
            JavaCode.generateDecoderLines(JavaCode.getVariableType(countFieldType), "size", methodCode, countFieldSign);
            methodCode.add(varName + " = new ArrayList<" + shortClassName + ">();");

            /* deserialize each element */
            methodCode.add("for (int i = 0; i < size; i++)");
            methodCode.add("{");
            methodCode.add("\t" + varName + ".add(new " + shortClassName + "());");
            methodCode.add("\t" + varName + ".get(i).decode(bytes, pos);");
            methodCode.add("\tpos += " + varName + ".get(i).getSize();");
            methodCode.add("}");
            if (variant.isOptional())
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
            methodCode.add("\t" + varName + ".Add(new " + shortClassName + "());");
            methodCode.add("\t" + varName + "[i].decode(bytes, pos);");
            methodCode.add("\tpos += " + varName + "[i].getSize();");
            methodCode.add("}");
            if (variant.isOptional())
            {
                methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        code.decoderLines.addAll(methodCode);
    }

    private void generateVectorGetNumberMethod(CodeLines code)
    {
        methodCode.clear();
        methodParam.clear();

        /// getNumberOfElements()
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
            methodCode.add("return  " + varName + ".Count;");
            code.methods.addAll(CSharpCode.createMethodDefinition("public int", "get", "NumberOfElements", null, methodCode, true));
        }
    }

    private void generateVectorGetMethod(CodeLines code)
    {
        methodCode.clear();
        methodParam.clear();

        /// getElement(index)
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

    private void generateVectorSetMethod(CodeLines code)
    {
        methodCode.clear();
        methodParam.clear();

        /// setElement(index,value)
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
            methodCode.add(varName + ".get(index).assign(value);");
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
            methodCode.add(varName + "[index].set" + shortClassName + "(value);");
            // Create framework for parent reference
            methodCode.add(CSharpCode.getParentReferenceVariableSetParent(varName + "[index]"));
            methodCode.add(CSharpCode.getParentReferenceSetParentPVLine());
            code.methods.addAll(CSharpCode.createMethodDefinition("public void", "set", "Element", methodParam, methodCode, false));
        }
    }

    private void generateVectorAddMethod(CodeLines code)
    {
        methodCode.clear();
        methodParam.clear();

        /// addElement(value)
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
            methodCode.add(JavaCode.getParentReferenceVariableSetParent(varName + ".get(" + varName + ".size()-1)"));
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

    private void generateVectorDeleteMethod(CodeLines code)
    {
        methodCode.clear();
        methodParam.clear();

        /// deleteElement(index)
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
            methodCode.add("\treturn;");
            methodCode.add("}");
            methodCode.add("");
            methodCode.add(varName + ".remove(index);");
            code.methods.addAll(JavaCode.createMethodDefinition("public void", "delete", "Element", methodParam, methodCode, false));
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            methodParam.add("int index");

            methodCode.add("if(" + varName + ".Count-1 < index)");
            methodCode.add("{");
            methodCode.add("\treturn;");
            methodCode.add("}");
            methodCode.add("");
            methodCode.add(varName + ".RemoveAt(index);");
            code.methods.addAll(CSharpCode.createMethodDefinition("public void", "delete", "Element", methodParam, methodCode, false));
        }
    }

    private void generateVectorDeleteLastMethod(CodeLines code)
    {
        methodCode.clear();
        methodParam.clear();

        /// deleteLastElement()
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            code.publicMethods.add(CppCode.createMethodDeclaration("int", "delete", "LastElement", methodParam, false));
            methodCode.add(varName + ".pop_back();");
            methodCode.add("return 0;");
            code.methods.addAll(CppCode.createMethodDefinition("int", parentClassName + "::delete", "LastElement", methodParam, methodCode, false));
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            methodCode.add(varName + ".remove(" + varName + ".size() -1);");
            code.methods.addAll(JavaCode.createMethodDefinition("public void ", "delete", "LastElement", methodParam, methodCode, false));
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            methodCode.add(varName + ".RemoveAt(" + varName + ".Count -1);");
            code.methods.addAll(CSharpCode.createMethodDefinition("public void", "delete", "LastElement", methodParam, methodCode, false));
        }
    }

    private void generateVectorOverloadAssignmentMethod(CodeLines code, int pvIndex)
    {
        methodCode.clear();

        /// Overload the operator=
        /// Add code to the assignment lines of code
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
            if (variant.isOptional())
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
            if (variant.isOptional())
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
            if (variant.isOptional())
            {
                methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        code.assignmentLines.addAll(methodCode);
    }

    private void generateVectorOverloadIsEqualMethod(CodeLines code, int pvIndex)
    {
        methodCode.clear();

        /// Overload operator==
        /// Add code for checking equality
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            methodCode.add("if (" + varName + ".size() != value." + varName + ".size())");
            methodCode.add("{");
            methodCode.add("\treturn false;");
            methodCode.add("}");
            methodCode.add("");
            methodCode.add("for (int i = 0; i < " + varName + ".size(); i++)");
            methodCode.add("{");
            methodCode.add("\tif (" + varName + "[i] != value." + varName + "[i])");
            methodCode.add("\t{");
            methodCode.add("\t\treturn false;");
            methodCode.add("\t}");
            methodCode.add("}");
            if (variant.isOptional())
            {
                methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            methodCode.add("if (" + varName + ".size() != value." + varName + ".size())");
            methodCode.add("{");
            methodCode.add("\treturn false;");
            methodCode.add("}");
            methodCode.add("");
            methodCode.add("for (int i = 0; i < " + varName + ".size(); i++)");
            methodCode.add("{");
            methodCode.add("\tif (" + varName + ".get(i) != value." + varName + ".get(i))");
            methodCode.add("\t{");
            methodCode.add("\t\treturn false;");
            methodCode.add("\t}");
            methodCode.add("}");
            if (variant.isOptional())
            {
                methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            methodCode.add("if (" + varName + ".Count != value." + varName + ".Count)");
            methodCode.add("{");
            methodCode.add("\treturn false;");
            methodCode.add("}");
            methodCode.add("");
            methodCode.add("for (int i = 0; i < " + varName + ".Count; i++)");
            methodCode.add("{");
            methodCode.add("\tif (!this." + varName + "[i].isEqual(value." + varName + "[i]))");
            methodCode.add("\t{");
            methodCode.add("\t\treturn false;");
            methodCode.add("\t}");
            methodCode.add("}");
            if (variant.isOptional())
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
        methodCode.clear();

        /// Overload operator!=
        /// Add code checking inequality
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            methodCode.add("for (int i = 0; i < " + varName + ".size(); i++)");
            methodCode.add("{");
            methodCode.add("\tif (" + varName + "[i] == value." + varName + "[i])");
            methodCode.add("\t{");
            methodCode.add("\t\treturn false;");
            methodCode.add("\t}");
            methodCode.add("}");
            if (variant.isOptional())
            {
                methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            methodCode.add("for (int i = 0; i < " + varName + ".size(); i++)");
            methodCode.add("{");
            methodCode.add("\tif (" + varName + ".get(i) == value." + varName + ".get(i))");
            methodCode.add("\t{");
            methodCode.add("\t\treturn false;");
            methodCode.add("\t}");
            methodCode.add("}");
            if (variant.isOptional())
            {
                methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            methodCode.add("for (int i = 0; i < " + varName + ".Count; i++)");
            methodCode.add("{");
            methodCode.add("\tif (this." + varName + "[i].notEquals(value." + varName + "[i]))");
            methodCode.add("\t{");
            methodCode.add("\t\treturn false;");
            methodCode.add("\t}");
            methodCode.add("}");
            if (variant.isOptional())
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

