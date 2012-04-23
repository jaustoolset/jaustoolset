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
 * @(#)ArrayGenerator.java		0.1 2008/10/03
 * 
 * Copyright
 */
package org.jts.codegenerator;

import java.util.Vector;

import org.jts.jsidl.binding.*;

/**
 * This class generates the code for an Array JAXB Object
 * 
 * @author Nicholas M. Johnson
 * @author Gina Nearing
 *
 */
public class ArrayGenerator
{

    final private boolean bitFieldHasClass = true;					// BitField					// Currently always true
    final private boolean fixedFieldHasClass = false;				// FixedField				// Currently always false
    final private boolean variableFieldHasClass = true;				// VariableField			// Currently always true
    final private boolean variableLengthFieldHasClass = true;		// VariableLengthField		// Currently always true
    final private boolean variableFormatFieldHasClass = true;		// VariableFormatField		// Currently always true
    final private boolean fixedLengthStringHasClass = false;		// FixedLengthString		// Currently always false
    final private boolean variableLengthStringHasClass = false;		// VariableLengthString		// Currently always false
    private CodeLines.CodeType codeType;
    private Array array;
    private Vector<String> methodCode;
    private Vector<String> methodParam;
    private String fullClassName;
    private String shortClassName;
    private String parentClassName;
    private String varName;

    /**
     *
     * @param codeType
     * @param array
     */
    public ArrayGenerator(CodeLines.CodeType codeType, Array array)
    {
        this.codeType = codeType;
        this.array = array;

        methodCode = new Vector<String>();
        methodParam = new Vector<String>();
    }

    /**
     *
     * @param parentName
     * @param pvIndex
     * @param hasClass
     * @param code
     */
    public void run(String parentName, int pvIndex, boolean hasClass, CodeLines code)
    {
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            if (hasClass)
            {
                fullClassName = parentName + "::" + array.getName();
                shortClassName = CppCode.getShortClassName(fullClassName);
                parentClassName = CppCode.getParentClassName(fullClassName);
                varName = CppCode.getVariableName(shortClassName);
            }
            else
            {
                fullClassName = parentName;
                shortClassName = CppCode.getShortClassName(fullClassName);
                parentClassName = CppCode.getParentClassName(fullClassName);
                varName = CppCode.getVariableName(shortClassName);
            }

            /*
             * Create framework for parent reference
             */
           	CppCode.addParentReference(code, fullClassName, pvIndex, array.isOptional());

            /* Generate the Array Code */
            /**********************************/
            CodeLines fieldCode = new CodeLines("", codeType, code.getNameSpace());

            if (array.getBitField() != null)
            {
                BitField bField = array.getBitField();
                new BitFieldGenerator(codeType, bField, array.getDimension()).run(fullClassName, 0, bitFieldHasClass, fieldCode);
            }
            else if (array.getFixedField() != null)
            {
                FixedField fField = array.getFixedField();
                new FixedFieldGenerator(codeType, fField, array.getDimension()).run(fullClassName, 0, fixedFieldHasClass, fieldCode, null);
            }
            else if (array.getFixedLengthString() != null)
            {
                FixedLengthString flString = array.getFixedLengthString();
                new FixedLengthStringGenerator(codeType, flString, array.getDimension()).run(fullClassName, 0, fixedLengthStringHasClass, fieldCode);
            }
            else if (array.getVariableField() != null)
            {
                VariableField vField = array.getVariableField();
                new VariableFieldGenerator(codeType, vField, array.getDimension()).run(fullClassName, 0, variableFieldHasClass, fieldCode);
            }
            else if (array.getVariableFormatField() != null)
            {
                VariableFormatField vfField = array.getVariableFormatField();
                new VariableFormatFieldGenerator(codeType, vfField, array.getDimension()).run(fullClassName, 0, variableFormatFieldHasClass, fieldCode);
            }
            else if (array.getVariableLengthField() != null)
            {
                VariableLengthField flField = array.getVariableLengthField();
                new VariableLengthFieldGenerator(codeType, flField, array.getDimension()).run(fullClassName, 0, variableLengthFieldHasClass, fieldCode);
            }
            else if (array.getVariableLengthString() != null)
            {
                VariableLengthString vlString = array.getVariableLengthString();
                new VariableLengthStringGenerator(codeType, vlString, array.getDimension()).run(fullClassName, 0, variableLengthStringHasClass, fieldCode);
            }
            else
            {
                throw new RuntimeException("Invalid Array Specification: unknown or no field definition");
            }
            
            generateInstanceOverloadAssignmentMethod(code);
            generateInstanceOverloadIsEqualMethod(code);
            generateInstanceOverloadNotEqualMethod(code);

            code.addAll(fieldCode);
            /**********************************/
            if (hasClass)
            {
                /// Add a class wrapper around the generated code
                CppCode.addClassWrapper(fullClassName, code);

                generateSingleInstance(fullClassName, pvIndex, code);
            }
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            if (hasClass)
            {
                fullClassName = parentName + "." + array.getName();
                shortClassName = JavaCode.getShortClassName(fullClassName);
                parentClassName = JavaCode.getParentClassName(fullClassName);
                varName = JavaCode.getVariableName(shortClassName);
            }
            else
            {
                fullClassName = parentName;
                shortClassName = JavaCode.getShortClassName(fullClassName);
                parentClassName = JavaCode.getParentClassName(fullClassName);
                varName = JavaCode.getVariableName(shortClassName);
            }

             /*
             * Create framework for parent reference
             */
           	JavaCode.addParentReference(code, fullClassName, pvIndex, array.isOptional());

            /* Generate the Array Code */
            /**********************************/
            CodeLines fieldCode = new CodeLines("", codeType, code.getNameSpace());

            if (array.getBitField() != null)
            {
                BitField bField = array.getBitField();
                new BitFieldGenerator(codeType, bField, array.getDimension()).run(fullClassName, 0, bitFieldHasClass, fieldCode);
            }
            else if (array.getFixedField() != null)
            {
                FixedField fField = array.getFixedField();
                new FixedFieldGenerator(codeType, fField, array.getDimension()).run(fullClassName, 0, fixedFieldHasClass, fieldCode, null);
            }
            else if (array.getFixedLengthString() != null)
            {
                FixedLengthString flString = array.getFixedLengthString();
                new FixedLengthStringGenerator(codeType, flString, array.getDimension()).run(fullClassName, 0, fixedLengthStringHasClass, fieldCode);
            }
            else if (array.getVariableField() != null)
            {
                VariableField vField = array.getVariableField();
                new VariableFieldGenerator(codeType, vField, array.getDimension()).run(fullClassName, 0, variableFieldHasClass, fieldCode);
            }
            else if (array.getVariableFormatField() != null)
            {
                VariableFormatField vfField = array.getVariableFormatField();
                new VariableFormatFieldGenerator(codeType, vfField, array.getDimension()).run(fullClassName, 0, variableFormatFieldHasClass, fieldCode);
            }
            else if (array.getVariableLengthField() != null)
            {
                VariableLengthField flField = array.getVariableLengthField();
                new VariableLengthFieldGenerator(codeType, flField, array.getDimension()).run(fullClassName, 0, variableLengthFieldHasClass, fieldCode);
            }
            else if (array.getVariableLengthString() != null)
            {
                VariableLengthString vlString = array.getVariableLengthString();
                new VariableLengthStringGenerator(codeType, vlString, array.getDimension()).run(fullClassName, 0, variableLengthStringHasClass, fieldCode);
            }
            else
            {
                throw new RuntimeException("Invalid Array Specification: unknown or no field definition");
            }


            code.addAll(fieldCode);
            /**********************************/
            if (hasClass)
            {
                /// Add a class wrapper around the generated code
                JavaCode.addClassWrapper(fullClassName, code);

                generateSingleInstance(fullClassName, pvIndex, code);
            }
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            if (hasClass)
            {
                fullClassName = parentName + "." + array.getName();
                shortClassName = CSharpCode.getShortClassName(fullClassName);
                parentClassName = CSharpCode.getParentClassName(fullClassName);
                varName = CSharpCode.getVariableName(shortClassName);
            }
            else
            {
                fullClassName = parentName;
                shortClassName = CSharpCode.getShortClassName(fullClassName);
                parentClassName = CSharpCode.getParentClassName(fullClassName);
                varName = CSharpCode.getVariableName(shortClassName);
            }

            /*
             * Create framework for parent reference
             */
           	CSharpCode.addParentReference(code, fullClassName, pvIndex, array.isOptional());

            /* Generate the Array Code */
            /**********************************/
            CodeLines fieldCode = new CodeLines("", codeType, code.getNameSpace());

            if (array.getBitField() != null)
            {
                BitField bField = array.getBitField();
                new BitFieldGenerator(codeType, bField, array.getDimension()).run(fullClassName, 0, bitFieldHasClass, fieldCode);
            }
            else if (array.getFixedField() != null)
            {
                FixedField fField = array.getFixedField();
                new FixedFieldGenerator(codeType, fField, array.getDimension()).run(fullClassName, 0, fixedFieldHasClass, fieldCode, null);
            }
            else if (array.getFixedLengthString() != null)
            {
                FixedLengthString flString = array.getFixedLengthString();
                new FixedLengthStringGenerator(codeType, flString, array.getDimension()).run(fullClassName, 0, fixedLengthStringHasClass, fieldCode);
            }
            else if (array.getVariableField() != null)
            {
                VariableField vField = array.getVariableField();
                new VariableFieldGenerator(codeType, vField, array.getDimension()).run(fullClassName, 0, variableFieldHasClass, fieldCode);
            }
            else if (array.getVariableFormatField() != null)
            {
                VariableFormatField vfField = array.getVariableFormatField();
                new VariableFormatFieldGenerator(codeType, vfField, array.getDimension()).run(fullClassName, 0, variableFormatFieldHasClass, fieldCode);
            }
            else if (array.getVariableLengthField() != null)
            {
                VariableLengthField flField = array.getVariableLengthField();
                new VariableLengthFieldGenerator(codeType, flField, array.getDimension()).run(fullClassName, 0, variableLengthFieldHasClass, fieldCode);
            }
            else if (array.getVariableLengthString() != null)
            {
                VariableLengthString vlString = array.getVariableLengthString();
                new VariableLengthStringGenerator(codeType, vlString, array.getDimension()).run(fullClassName, 0, variableLengthStringHasClass, fieldCode);
            }
            else
            {
                throw new RuntimeException("Invalid Array Specification: unknown or no field definition");
            }


            code.addAll(fieldCode);
            /**********************************/
            if (hasClass)
            {
                /// Add a class wrapper around the generated code
                CSharpCode.addClassWrapper(fullClassName, code);

                generateSingleInstance(fullClassName, pvIndex, code);
            }
        }
    }


    /*
     * @param fullClassName
     * @param pvIndex
     * @param code
     */
    private void generateSingleInstance(String fullClassName, int pvIndex, CodeLines code)
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


        generateInstanceGetSizeMethod(code, pvIndex);

        generateInstanceIsValidMethod(code, pvIndex);

        generateInstanceEncodeMethod(code, pvIndex);
        generateInstanceDecodeMethod(code, pvIndex);

        generateInstanceGetMethod(code, pvIndex);
        generateInstanceSetMethod(code, pvIndex);
    }

    /*
     * @param code
     * @param pvIndex
     */
    private void generateInstanceGetSizeMethod(CodeLines code, int pvIndex)
    {
        methodCode.clear();

        /*
         * GetSize method
         * only add size of array if optional is true
         */
        methodCode.add("size += " + varName + ".getSize();");
        if (array.isOptional())
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

    /*
     * @param code
     * @param pvIndex
     */
    private void generateInstanceIsValidMethod(CodeLines code, int pvIndex)
    {
        /*
         * is[Field]valid added if optional
         * return true if field is set in presence vector
         */
        if (array.isOptional())
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

    /*
     * @param code
     * @param pvIndex
     */
    private void generateInstanceEncodeMethod(CodeLines code, int pvIndex)
    {
        methodCode.clear();

        /* 	m_VaribleLengthField1.encode(bytes + pos);
         *	pos += m_VaribleLengthField1.getSize();
         */
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            methodCode.add(varName + ".encode(bytes + pos);");
            methodCode.add("pos += " + varName + ".getSize();");
            if (array.isOptional())
            {

                methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            methodCode.add(varName + ".encode(bytes, pos);");
            methodCode.add("pos += " + varName + ".getSize();");
            if (array.isOptional())
            {
                methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            methodCode.add(varName + ".encode(bytes, pos);");
            methodCode.add("pos += " + varName + ".getSize();");
            if (array.isOptional())
            {
                methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        code.encoderLines.addAll(methodCode);
    }

    /*
     * @param code
     * @param pvIndex
     */
    private void generateInstanceDecodeMethod(CodeLines code, int pvIndex)
    {
        methodCode.clear();

        /* 	m_VaribleLengthField1.decode(bytes + pos);
         *	pos += m_VaribleLengthField1.getSize();
         */

        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            methodCode.add(varName + ".decode(bytes + pos);");
            methodCode.add("pos += " + varName + ".getSize();");

            if (array.isOptional())
            {
                methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            methodCode.add(varName + ".decode(bytes, pos);");
            methodCode.add("pos += " + varName + ".getSize();");
            if (array.isOptional())
            {
                methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            methodCode.add(varName + ".decode(bytes, pos);");
            methodCode.add("pos += " + varName + ".getSize();");

            if (array.isOptional())
            {
                methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        code.decoderLines.addAll(methodCode);
    }

    /*
     * @param code
     * @param pvIndex
     */
    private void generateInstanceGetMethod(CodeLines code, int pvIndex)
    {
        methodParam.clear();
        methodCode.clear();

        /* Generate getMethod Declaration and Definition */
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

    /*
     * @param code
     * @param pvIndex
     */
    private void generateInstanceSetMethod(CodeLines code, int pvIndex)
    {
        methodParam.clear();
        methodCode.clear();

        /* Generate setMethod Declaration and Definition */
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            methodParam.add("const " + shortClassName + " &value");
            code.publicMethods.add(CppCode.createMethodDeclaration("int", "set", shortClassName, methodParam, false));

            methodCode.add(varName + " = value;");
            if (array.isOptional())
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
            if (array.isOptional())
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
            if (array.isOptional())
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
    	// make a temp function for now, must go back and add this code for an array
    	/*
        /// Overload the operator=
        /// Add code to the assignment lines of code]
        if (codeType == CodeLines.CodeType.C_SHARP)
        {
            code.assignmentLines.add(varName + " = value.get" + varName.substring(varName.indexOf(CSharpCode.VARIABLE_PREFIX)+2) + "();");
        }
        else
        {
            code.assignmentLines.add(varName + " = value." + varName + ";");
        }*/
    	//code.assignmentLines.add("return true;");
    	code.assignmentLines.add("// not yet implemented");
    }

    private void generateInstanceOverloadIsEqualMethod(CodeLines code)
    {
    	// make a temp function for now, must go back and add this code for an array
    	/*
        /// Overload operator==
        /// Add code for checking equality
        if (!code.equalLines.isEmpty())
        {
            code.equalLines.add("");
        }
        if (codeType == CodeLines.CodeType.C_SHARP)
        {
            code.equalLines.add("if (value.get" + varName.substring(varName.indexOf(CSharpCode.VARIABLE_PREFIX)+2) + "() != value2.get" + varName.substring(varName.indexOf(CSharpCode.VARIABLE_PREFIX)+2) + "())");
        }
        else if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            code.equalLines.add("if (" + varName + " != value." + varName + ")");
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            code.equalLines.add("if (!" + varName + ".equals(value.get" + Util.upperCaseFirstLetter(shortClassName) + "()))");
        }
        code.equalLines.add("{");
        code.equalLines.add("\treturn false;");
        code.equalLines.add("}");
        */
    	code.equalLines.add("// not yet implemented");
    	//code.equalLines.add("return true;");
    }

    private void generateInstanceOverloadNotEqualMethod(CodeLines code)
    {
    	// make a temp function for now, must go back and add this code for an array
    	/*
        /// Overload operator!=
        /// Add code checking inequality
        if (!code.notEqualLines.isEmpty())
        {
            code.notEqualLines.add("");
        }
        if (codeType == CodeLines.CodeType.C_SHARP)
        {
            code.notEqualLines.add("if (value.get" + varName.substring(varName.indexOf(CSharpCode.VARIABLE_PREFIX)+2) + "() == value2.get" + varName.substring(varName.indexOf(CSharpCode.VARIABLE_PREFIX)+2) + "())");
        }
        else if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            code.notEqualLines.add("if (" + varName + " == value." + varName + ")");
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            code.notEqualLines.add("if (" + varName + ".equals(value.get" + Util.upperCaseFirstLetter(shortClassName) + "()))");
        }
        code.notEqualLines.add("{");
        code.notEqualLines.add("\treturn false;");
        code.notEqualLines.add("}");
        */
    	code.notEqualLines.add("// not yet implemented");
    	//code.notEqualLines.add("return true;");
    }
}
