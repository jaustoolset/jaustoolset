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
 * This class will generate C++ code from a JSIDL Record 
 *
 * @version		0.1	5 Sept 2008
 * @author		Nicholas M. Johnson
 * @author		Gregory Garcia
 * @author		Jean-Francois Kamath
 *
 */
/// TODO: Fix operator=, operator==, operator!= for FixedLengthString and VariableLengthString
public class SequenceGenerator
{
//	final private boolean debug = false;
	final private boolean variantIsNested = true;			// Variant					// Currently always true
	final private boolean recordIsNested = true;			// Record					// Currently always true
	final private boolean listIsNested = true;				// List						// Currently always true
	final private boolean sequenceIsNested = true;			// Sequence					// Currently always true
	
	private CodeLines.CodeType codeType;
	private Sequence sequence;
	private boolean isList;
	private String countFieldType;
    private boolean countFieldSigned;
	
	public SequenceGenerator(CodeLines.CodeType codeType, Sequence sequence)
	{
		this.codeType = codeType;
		this.sequence = sequence;
		this.isList = false;
	}

	public SequenceGenerator(CodeLines.CodeType codeType, Sequence sequence, boolean isList, String countFieldType)
	{
		this.codeType = codeType;
		this.sequence = sequence;
		this.isList = isList;
		this.countFieldType = countFieldType;
        countFieldSigned = JavaCode.getVariableSign(countFieldType);
	}


	/**
	 * This method generates the code for creating a sequence.
	 * The generate Sequence will be placed within its own class which
	 * is nested within the encapsulating class.
	 * @param code
	 */
	public void run(String parentClassName, int pvIndex, CodeLines code)
	{
            String shortClassName = sequence.getName();
            String fullClassName = parentClassName + "::" + shortClassName; 
            
            /*
             * Create framework for parent reference
             */
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
            	CppCode.addParentReference(code, fullClassName, pvIndex, sequence.isOptional());
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                fullClassName = parentClassName + "." + shortClassName;
            	JavaCode.addParentReference(code, fullClassName, pvIndex, sequence.isOptional());
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                fullClassName = parentClassName + "." + shortClassName;
            	CSharpCode.addParentReference(code, fullClassName, pvIndex, sequence.isOptional());
            }


            /* Create the Sequence Class */
            /***************************/
            generateClass(parentClassName, shortClassName, code);

            if (isList)
            {
                generateSequenceVector(parentClassName, shortClassName, pvIndex, code);
            }
            else
            {
                generateSequenceInstance(parentClassName, shortClassName, pvIndex, code);
            }

	}
	
	/**
	 * 
	 * @param parentClassName
	 * @param shortClassName
	 * @param code
	 */
	private void generateClass(String parentClassName, String shortClassName, CodeLines code)
	{
		
            CodeLines varCode = new CodeLines("", codeType, code.getNameSpace());
            int pvIndex1 = -1;
            String fullClassName = "";
            String variableName = "";

            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                fullClassName = parentClassName + "::" + shortClassName;
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                fullClassName = parentClassName + "." + shortClassName;
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                fullClassName = parentClassName + "." + shortClassName;
            }
            
            /* Generate the code for the presenceVector */
            if (sequence.getPresenceVector() != null)
            {
                varCode.clear();

                new PresenceVectorGenerator(codeType, sequence.getPresenceVector()).run(fullClassName, true, varCode);

                // Also add an assignment handler
                code.assignmentLines.add("m_PresenceVector = value.m_PresenceVector;");
			
    		/* Add the generated code to the overall code */ 
    		code.addAll(varCode);
            }


            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                cppGenerateClass(code, varCode, pvIndex1, fullClassName, variableName);
                
                /// Overload operator==
                code.equalLines.add("");
                code.equalLines.add("return true;");

                /// Overload operator!=
                code.notEqualLines.add("");
                code.notEqualLines.add("return true;");

                /// Add a class wrapper around the generated code
                CppCode.addClassWrapper(fullClassName, code);
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                javaGenerateClass(code, varCode, pvIndex1, fullClassName, variableName);

                /// Overload operator==
                code.equalLines.add("");
                code.equalLines.add("return true;");

                /// Overload operator!=
                code.notEqualLines.add("");
                code.notEqualLines.add("return true;");

                /// Add a class wrapper around the generated code
                JavaCode.addClassWrapper(fullClassName, code);
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                csharpGenerateClass(code, varCode, pvIndex1, fullClassName, variableName);

                /// Overload operator==
                code.equalLines.add("");
                code.equalLines.add("return true;");

                /// Overload operator!=
                code.notEqualLines.add("");
                code.notEqualLines.add("return true;");

                /// Add a class wrapper around the generated code
                CSharpCode.addClassWrapper(fullClassName, code);
            }
			

        }

        /**
         * Core of C++ specific generateClass code - split from main function to simplify code.
         * @param code
         * @param varCode
         * @param pvIndex1
         * @param fullClassName
         * @param variableName
         */
        private void cppGenerateClass(CodeLines code, CodeLines varCode, int pvIndex1, String fullClassName, String variableName)
        {
            for(Object field:sequence.getRecordOrDeclaredRecordOrList())
            {
        	boolean fieldIsNested = false;
        	String fieldName = "Unknown";

        	varCode.clear();
        	if (field instanceof Variant)
                {
                    Variant variant = (Variant)field;

                    fieldName = Util.upperCaseFirstLetter(variant.getName());
                    fieldIsNested = variantIsNested;
                    if (variant.isOptional())
                    {
                	pvIndex1++;
                    }

                    new VariantGenerator(codeType, variant).run(fullClassName, pvIndex1, varCode);
                }
                else if (field instanceof Record)
                {
                    Record record = (Record)field;

                    fieldName = Util.upperCaseFirstLetter(record.getName());
                    fieldIsNested = recordIsNested;
                    if (record.isOptional())
                    {
                	pvIndex1++;
                    }

                    new RecordGenerator(codeType, record).run(fullClassName, pvIndex1, varCode, null);
                }
                else if (field instanceof List)
                {
                    List list = (List)field;

                    fieldName = Util.upperCaseFirstLetter(list.getName());
                    fieldIsNested = listIsNested;
                    if (list.isOptional())
                    {
                	pvIndex1++;
                    }

                    new ListGenerator(codeType, list).run(fullClassName, pvIndex1, varCode);
                }
                else if (field instanceof Sequence)
                {
                    Sequence sequence = (Sequence)field;

                    fieldName = Util.upperCaseFirstLetter(sequence.getName());
                    fieldIsNested = sequenceIsNested;
                    if (sequence.isOptional())
                    {
                        pvIndex1++;
                    }

                    new SequenceGenerator(codeType, sequence).run(fullClassName, pvIndex1, varCode);
                }
                else
                {
                    throw new CodeGeneratorException("SequenceGenerator: Unknown Field Encountered: " + field.getClass().getName());
                }

        	variableName = CppCode.getVariableName(fieldName);


    		/// Add the generated code to the overall code
    		code.addAll(varCode);

    		/*
    		 * If the field is nested then we need to provide code for each field
    		 * for the assignment operator, code for testing equality and inequality
    		 */
    		if (fieldIsNested)
    		{
                    /// Overload the operator=
                    /// Add code to the assignment lines of code
                    code.assignmentLines.add(variableName + " = value." + variableName + ";");


                    /// Overload operator==
                    /// Add code for checking equality
                    if (!code.equalLines.isEmpty())
                    {
                        code.equalLines.add("");
                    }
                    code.equalLines.add("if (" + variableName + " != value." + variableName + ")");
                    code.equalLines.add("{");
                    code.equalLines.add("\treturn false;");
                    code.equalLines.add("}");


                    /// Overload operator!=
                    /// Add code checking inequality
                    if (!code.notEqualLines.isEmpty())
                    {
                        code.notEqualLines.add("");
                    }
                    code.notEqualLines.add("if (" + variableName + " == value." + variableName + ")");
                    code.notEqualLines.add("{");
                    code.notEqualLines.add("\treturn false;");
                    code.notEqualLines.add("}");
    		}
            }
        }
        
       /**
         * Core of Java specific generateClass code - split from main function to simplify code.
         * @param code
         * @param varCode
         * @param pvIndex1
         * @param fullClassName
         * @param variableName
         */
        private void javaGenerateClass(CodeLines code, CodeLines varCode, int pvIndex1, String fullClassName, String variableName)
        {
            for(Object field:sequence.getRecordOrDeclaredRecordOrList())
            {
        	boolean fieldIsNested = false;
        	String fieldName = "Unknown";

        	varCode.clear();
        	if (field instanceof Variant)
                {
                    Variant variant = (Variant)field;

                    fieldName = Util.upperCaseFirstLetter(variant.getName());
                    fieldIsNested = variantIsNested;
                    if (variant.isOptional())
                    {
                	pvIndex1++;
                    }

                    new VariantGenerator(codeType, variant).run(fullClassName, pvIndex1, varCode);
                }
                else if (field instanceof Record)
                {
                    Record record = (Record)field;

                    fieldName = Util.upperCaseFirstLetter(record.getName());
                    fieldIsNested = recordIsNested;
                    if (record.isOptional())
                    {
                	pvIndex1++;
                    }

                    new RecordGenerator(codeType, record).run(fullClassName, pvIndex1, varCode, null);
                }
                else if (field instanceof List)
                {
                    List list = (List)field;

                    fieldName = Util.upperCaseFirstLetter(list.getName());
                    fieldIsNested = listIsNested;
                    if (list.isOptional())
                    {
                	pvIndex1++;
                    }

                    new ListGenerator(codeType, list).run(fullClassName, pvIndex1, varCode);
                }
                else if (field instanceof Sequence)
                {
                    Sequence sequence = (Sequence)field;

                    fieldName = Util.upperCaseFirstLetter(sequence.getName());
                    fieldIsNested = sequenceIsNested;
                    if (sequence.isOptional())
                    {
                        pvIndex1++;
                    }

                    new SequenceGenerator(codeType, sequence).run(fullClassName, pvIndex1, varCode);
                }
                else
                {
                    throw new CodeGeneratorException("SequenceGenerator: Unknown Field Encountered: " + field.getClass().getName());
                }

        	variableName = JavaCode.getVariableName(fieldName);


    		/// Add the generated code to the overall code
    		code.addAll(varCode);


    		/*
    		 * If the field is nested then we need to provide code for each field
    		 * for the assignment operator, code for testing equality and inequality
    		 */
    		if (fieldIsNested)
    		{
                    /// Overload the operator=
                    /// Add code to the assignment lines of code
                    code.assignmentLines.add(variableName + " = value." + variableName + ";");


                    /// add isEqual method for checking equality
                    if (!code.equalLines.isEmpty())
                    {
                        code.equalLines.add("");
                    }
                    code.equalLines.add("if (!" + variableName + ".isEqual(value.get" + Util.upperCaseFirstLetter(fieldName) + "()))");
                    code.equalLines.add("{");
                    code.equalLines.add("\treturn false;");
                    code.equalLines.add("}");


                    /// add notEquals method for checking inequality.
                    if (!code.notEqualLines.isEmpty())
                    {
                        code.notEqualLines.add("");
                    }
                    code.notEqualLines.add("if (" + variableName + ".isEqual(value.get" + Util.upperCaseFirstLetter(fieldName) + "()))");
                    code.notEqualLines.add("{");
                    code.notEqualLines.add("\treturn false;");
                    code.notEqualLines.add("}");
    		}
            }
        }

       /**
         * Core of C# specific generateClass code - split from main function to simplify code.
         * @param code
         * @param varCode
         * @param pvIndex1
         * @param fullClassName
         * @param variableName
         */
        private void csharpGenerateClass(CodeLines code, CodeLines varCode, int pvIndex1, String fullClassName, String variableName)
        {
            for(Object field:sequence.getRecordOrDeclaredRecordOrList())
            {
        	boolean fieldIsNested = false;
        	String fieldName = "Unknown";

        	varCode.clear();
        	if (field instanceof Variant)
                {
                    Variant variant = (Variant)field;

                    fieldName = Util.upperCaseFirstLetter(variant.getName());
                    fieldIsNested = variantIsNested;
                    if (variant.isOptional())
                    {
                	pvIndex1++;
                    }

                    new VariantGenerator(codeType, variant).run(fullClassName, pvIndex1, varCode);
                }
                else if (field instanceof Record)
                {
                    Record record = (Record)field;

                    fieldName = Util.upperCaseFirstLetter(record.getName());
                    fieldIsNested = recordIsNested;
                    if (record.isOptional())
                    {
                	pvIndex1++;
                    }

                    new RecordGenerator(codeType, record).run(fullClassName, pvIndex1, varCode, null);
                }
                else if (field instanceof List)
                {
                    List list = (List)field;

                    fieldName = Util.upperCaseFirstLetter(list.getName());
                    fieldIsNested = listIsNested;
                    if (list.isOptional())
                    {
                	pvIndex1++;
                    }

                    new ListGenerator(codeType, list).run(fullClassName, pvIndex1, varCode);
                }
                else if (field instanceof Sequence)
                {
                    Sequence sequence = (Sequence)field;

                    fieldName = Util.upperCaseFirstLetter(sequence.getName());
                    fieldIsNested = sequenceIsNested;
                    if (sequence.isOptional())
                    {
                        pvIndex1++;
                    }

                    new SequenceGenerator(codeType, sequence).run(fullClassName, pvIndex1, varCode);
                }
                else
                {
                    throw new CodeGeneratorException("SequenceGenerator: Unknown Field Encountered: " + field.getClass().getName());
                }

        	variableName = CSharpCode.getVariableName(fieldName);


    		/// Add the generated code to the overall code
    		code.addAll(varCode);


    		/*
    		 * If the field is nested then we need to provide code for each field
    		 * for the assignment operator, code for testing equality and inequality
    		 */
    		if (fieldIsNested)
    		{
                    /// Overload the operator=
                    /// Add code to the assignment lines of code
                    code.assignmentLines.add(variableName + " = value.get" + variableName.substring(variableName.indexOf(CSharpCode.VARIABLE_PREFIX)+2) + "();");


                    /// add isEqual method for checking equality
                    if (!code.equalLines.isEmpty())
                    {
                        code.equalLines.add("");
                    }
                    code.equalLines.add("if (!this.get" + variableName.substring(variableName.indexOf(CSharpCode.VARIABLE_PREFIX)+2) + "().isEqual(value.get" + variableName.substring(variableName.indexOf(CSharpCode.VARIABLE_PREFIX)+2) + "()))");
                    code.equalLines.add("{");
                    code.equalLines.add("\treturn false;");
                    code.equalLines.add("}");


                    /// add notEquals method for checking inequality
                    if (!code.notEqualLines.isEmpty())
                    {
                        code.notEqualLines.add("");
                    }
                    code.notEqualLines.add("if (!this.get" + variableName.substring(variableName.indexOf(CSharpCode.VARIABLE_PREFIX)+2) + "().notEquals(value.get" + variableName.substring(variableName.indexOf(CSharpCode.VARIABLE_PREFIX)+2) + "()))");
                    code.notEqualLines.add("{");
                    code.notEqualLines.add("\treturn false;");
                    code.notEqualLines.add("}");
    		}
            }
        }
	
	private void generateSequenceInstance(String parentClassName, String shortClassName, int pvIndex, CodeLines code)
	{
            Vector<String> methodCode = new Vector<String>();
            Vector<String> methodParam = new Vector<String>();

            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                String fullClassName = parentClassName + "::" + shortClassName;

                /*
                 * Create code lines for the encode, decode Methods
                 * Create the getMethod and setMethod
                 * This has to be done after the class wrapper has been added
                 */
                String varName = CppCode.getVariableName(shortClassName);
                
                /*
                 * Create framework for parent reference
                 */
                CppCode.addParentReferenceConstructorSetParent(code, varName);

                code.protectedAttributes.add(CppCode.createVariableDeclaration(shortClassName, shortClassName, false));
                code.constructorLines.add("/// No Initialization of " + varName + " necessary.");

                /*
                 * GetSize method
                 * only add size of array if optional is true
                 */
                methodCode.add("size += " + varName + ".getSize();");
                if (sequence.isOptional())
                {
                    methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                }
                code.sizeLines.addAll(methodCode);

                /*
                 * is[Field]valid added if optional
                 * return true if field is set in presence vector
                 */
                if (sequence.isOptional())
                {
                    code.publicMethods.add(CppCode.createMethodDeclaration("bool", "is", shortClassName + "Valid", null, true));
                    methodCode.clear();
                    methodCode.add("return true;");
                    methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                    methodCode.add("return false;");
                    code.methods.addAll(CppCode.createMethodDefinition("bool", parentClassName + "::is", shortClassName + "Valid", null, methodCode, true));
                }

                /* 	m_Record1.encode(bytes + pos);
                 *	pos += m_Record1.getSize();
                 */
                methodCode.clear();
                methodCode.add(varName + ".encode(bytes + pos);");
                methodCode.add("pos += " + varName + ".getSize();");
                if (sequence.isOptional())
                {
                    methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                }
                code.encoderLines.addAll(methodCode);


                /* 	m_Record1.decode(bytes + pos);
                 *	pos += m_Record1.getSize();
                 */
                methodCode.clear();
                methodCode.add(varName + ".decode(bytes + pos);");
                methodCode.add("pos += " + varName + ".getSize();");
                if (sequence.isOptional())
                {
                    methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                }
                code.decoderLines.addAll(methodCode);

                ///  Generate getMethod Declaration and Definition
                methodParam.clear();
                methodCode.clear();
                code.publicMethods.add(CppCode.createMethodDeclaration(shortClassName + "* const", "get", shortClassName, methodParam, false));

                methodCode.add("return &" + varName + ";");
                code.methods.addAll(CppCode.createMethodDefinition(fullClassName + "* const", parentClassName + "::get", shortClassName, methodParam, methodCode, false));


                ///  Generate setMethod Declaration and Definition
                methodParam.clear();
                methodCode.clear();
                methodParam.add("const " + shortClassName + " &value");
                code.publicMethods.add(CppCode.createMethodDeclaration("int", "set", shortClassName, methodParam, false));

                methodCode.add(varName + " = value;");
                if (sequence.isOptional())
                {
                    methodCode.add("setPresenceVector("+ pvIndex +");");
                }
                // Create framework for parent reference
                methodCode.add(CppCode.getParentReferenceSetParentPVLine());
                methodCode.add("return 0;");
                code.methods.addAll(CppCode.createMethodDefinition("int", parentClassName + "::set", shortClassName, methodParam, methodCode, false));

                /// Overload the operator=
                /// Add code to the assignment lines of code
                code.assignmentLines.add(varName + " = value." + varName + ";");
                // Create framework for parent reference
                code.assignmentLines.add(CppCode.getParentReferenceVariableSetParent(varName));

                /// Overload operator==
                /// Add code for checking equality
                if (!code.equalLines.isEmpty())
                {
                    code.equalLines.add("");
                }
                code.equalLines.add("if (" + varName + " != value." + varName + ")");
                code.equalLines.add("{");
                code.equalLines.add("\treturn false;");
                code.equalLines.add("}");

                /// Overload operator!=
                /// Add code checking inequality
                if (!code.notEqualLines.isEmpty())
                {
                    code.notEqualLines.add("");
                }
                code.notEqualLines.add("if (" + varName + " == value." + varName + ")");
                code.notEqualLines.add("{");
                code.notEqualLines.add("\treturn false;");
                code.notEqualLines.add("}");
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                String fullClassName = parentClassName + "." + shortClassName;

                /*
                 * Create code lines for the encode, decode Methods
                 * Create the getMethod and setMethod
                 * This has to be done after the class wrapper has been added
                 */
                String varName = JavaCode.getVariableName(shortClassName);
                
                code.protectedAttributes.add(JavaCode.createVariableDeclaration("protected " + shortClassName, shortClassName, false));
                code.constructorLines.add(varName + " = new " + shortClassName + "();");

                /*
                 * Create framework for parent reference
                 */
                JavaCode.addParentReferenceConstructorSetParent(code, varName);

                /*
                 * GetSize method
                 * only add size of array if optional is true
                 */
                methodCode.add("size += " + varName + ".getSize();");
                if (sequence.isOptional())
                {
                    methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                }
                code.sizeLines.addAll(methodCode);

                /*
                 * is[Field]valid added if optional
                 * return true if field is set in presence vector
                 */
                if (sequence.isOptional())
                {
                    methodCode.clear();
                    methodCode.add("return true;");
                    methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                    methodCode.add("return false;");
                    code.methods.addAll(JavaCode.createMethodDefinition("public boolean", "is", shortClassName + "Valid", null, methodCode, true));
                }

                /* 	m_Record1.encode(bytes, pos);
                 *	pos += m_Record1.getSize();
                 */
                methodCode.clear();
                methodCode.add(varName + ".encode(bytes, pos);");
                methodCode.add("pos += " + varName + ".getSize();");
                if (sequence.isOptional())
                {
                    methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                }
                code.encoderLines.addAll(methodCode);


                /* 	m_Record1.decode(bytes, pos);
                 *	pos += m_Record1.getSize();
                 */
                methodCode.clear();
                methodCode.add(varName + ".decode(bytes, pos);");
                methodCode.add("pos += " + varName + ".getSize();");
                if (sequence.isOptional())
                {
                    methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                }
                code.decoderLines.addAll(methodCode);

                ///  Generate getMethod Definition
                methodParam.clear();
                methodCode.clear();
                methodCode.add("return " + varName + ";");
                code.methods.addAll(JavaCode.createMethodDefinition("public " +fullClassName, "get", shortClassName, methodParam, methodCode, false));


                ///  Generate setMethod Definition
                methodParam.clear();
                methodCode.clear();
                methodParam.add(shortClassName + " value");

                methodCode.add(varName + " = value;");
                if (sequence.isOptional())
                {
                    methodCode.add("setPresenceVector("+ pvIndex +");");
                }
                // Create framework for parent reference
                methodCode.add(JavaCode.getParentReferenceSetParentPVLine());
                methodCode.add("return 0;");
                code.methods.addAll(JavaCode.createMethodDefinition("public int", "set", shortClassName, methodParam, methodCode, false));

                /// Overload the operator=
                /// Add code to the assignment lines of code
                code.assignmentLines.add(varName + " = value." + varName + ";");
                // Create framework for parent reference
                code.assignmentLines.add(JavaCode.getParentReferenceVariableSetParent(varName));

                /// Overload operator==
                /// Add code for checking equality
                if (!code.equalLines.isEmpty())
                {
                    code.equalLines.add("");
                }
                code.equalLines.add("if (!" + varName + ".isEqual(value.get" + Util.upperCaseFirstLetter(shortClassName) + "()))");
                code.equalLines.add("{");
                code.equalLines.add("\treturn false;");
                code.equalLines.add("}");

                /// Overload operator!=
                /// Add code checking inequality
                if (!code.notEqualLines.isEmpty())
                {
                    code.notEqualLines.add("");
                }
                code.notEqualLines.add("if (!" + varName + ".notEquals(value." + varName + "))");
                code.notEqualLines.add("{");
                code.notEqualLines.add("\treturn false;");
                code.notEqualLines.add("}");
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                String fullClassName = parentClassName + "." + shortClassName;

                /*
                 * Create code lines for the encode, decode Methods
                 * Create the getMethod and setMethod
                 * This has to be done after the class wrapper has been added
                 */
                String varName = CSharpCode.getVariableName(shortClassName);
              
                code.protectedAttributes.add(CSharpCode.createVariableDeclaration("protected " + shortClassName, shortClassName, false));
                code.constructorLines.add(varName + " = new " + shortClassName + "();");
                /*
                 * Create framework for parent reference
                 */
                CSharpCode.addParentReferenceConstructorSetParent(code, varName);

                /*
                 * GetSize method
                 * only add size of array if optional is true
                 */
                methodCode.add("size += " + varName + ".getSize();");
                if (sequence.isOptional())
                {
                    methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
                }
                code.sizeLines.addAll(methodCode);

                /*
                 * is[Field]valid added if optional
                 * return true if field is set in presence vector
                 */
                if (sequence.isOptional())
                {
                    methodCode.clear();
                    methodCode.add("return true;");
                    methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
                    methodCode.add("return false;");
                    code.methods.addAll(CSharpCode.createMethodDefinition("public bool", "is", shortClassName + "Valid", null, methodCode, true));
                }

                /* 	m_Record1.encode(bytes + pos);
                 *	pos += m_Record1.getSize();
                 */
                methodCode.clear();
                methodCode.add(varName + ".encode(bytes, pos);");
                methodCode.add("pos += " + varName + ".getSize();");
                if (sequence.isOptional())
                {
                    methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
                }
                code.encoderLines.addAll(methodCode);


                /* 	m_Record1.decode(bytes + pos);
                 *	pos += m_Record1.getSize();
                 */
                methodCode.clear();
                methodCode.add(varName + ".decode(bytes, pos);");
                methodCode.add("pos += " + varName + ".getSize();");
                if (sequence.isOptional())
                {
                    methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
                }
                code.decoderLines.addAll(methodCode);

                ///  Generate getMethod Definition
                methodParam.clear();
                methodCode.clear();

                methodCode.add("return " + varName + ";");
                code.methods.addAll(CSharpCode.createMethodDefinition("public " + fullClassName , "get", shortClassName, methodParam, methodCode, false));


                ///  Generate setMethod Definition
                methodParam.clear();
                methodCode.clear();
                methodParam.add(shortClassName + " value");

                methodCode.add(varName + " = value;");
                if (sequence.isOptional())
                {
                    methodCode.add("setPresenceVector("+ pvIndex +");");
                }
                // Create framework for parent reference
                methodCode.add(CSharpCode.getParentReferenceSetParentPVLine());
                code.methods.addAll(CSharpCode.createMethodDefinition("public void", "set", shortClassName, methodParam, methodCode, false));

                /// Overload the operator=
                /// Add code to the assignment lines of code
                code.assignmentLines.add(varName + " = value.get" + varName.substring(varName.indexOf(CSharpCode.VARIABLE_PREFIX)+2) + "();");
                // Create framework for parent reference
                code.assignmentLines.add(CSharpCode.getParentReferenceVariableSetParent(varName));

                /// Overload operator==
                /// Add code for checking equality
                if (!code.equalLines.isEmpty())
                {
                    code.equalLines.add("");
                }
                code.equalLines.add("if (!this.get" + varName.substring(varName.indexOf(CSharpCode.VARIABLE_PREFIX)+2) + "().isEqual(value.get" + varName.substring(varName.indexOf(CSharpCode.VARIABLE_PREFIX)+2) + "()))");
                code.equalLines.add("{");
                code.equalLines.add("\treturn false;");
                code.equalLines.add("}");

                /// Overload operator!=
                /// Add code checking inequality
                if (!code.notEqualLines.isEmpty())
                {
                    code.notEqualLines.add("");
                }
                code.notEqualLines.add("if (!this.get" + varName.substring(varName.indexOf(CSharpCode.VARIABLE_PREFIX)+2) + "().notEquals(value.get" + varName.substring(varName.indexOf(CSharpCode.VARIABLE_PREFIX)+2) + "()))");
                code.notEqualLines.add("{");
                code.notEqualLines.add("\treturn false;");
                code.notEqualLines.add("}");
            }
    }

   
	/**
	 * Generates the Code for a Sequence if it is a Vector
	 * @param parentClassName
	 * @param shortClassName
	 * @param pvIndex
	 * @param code
	 */
	private void generateSequenceVector(String parentClassName, String shortClassName, int pvIndex, CodeLines code)
	{
            Vector<String> methodCode = new Vector<String>();
            Vector<String> methodParam = new Vector<String>();
            String fullClassName = "";

            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                fullClassName = parentClassName + "::" + shortClassName;

                /*
                 * Create code lines for the encode, decode Methods
                 * Create the getMethod and setMethod
                 * This has to be done after the class wrapper has been added
                 */
                String varName = CppCode.getVariableName(shortClassName);
                
                /*
                 * Create framework for parent reference
                 */
                CppCode.addParentReferenceConstructorSetParrentInArray(code, varName);

                code.protectedAttributes.add(CppCode.createVariableDeclaration("std::vector<" + shortClassName + ">", shortClassName, false));
                code.constructorLines.add("/// No Initialization of " + varName + " necessary.");

                /*
                 * GetSize method
                 * only add size of array if optional is true
                 */
                methodCode.add("size += sizeof(" + CppCode.getVariableType(countFieldType) + ");");
                methodCode.add("for (int i = 0; i < " + varName + ".size(); i++)");
                methodCode.add("{");
                methodCode.add("	size += " + varName + "[i].getSize();");
                methodCode.add("}");
                if (sequence.isOptional())
                {
                    methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                }
                code.sizeLines.addAll(methodCode);

                /*
                 * is[Field]valid added if optional
                 * return true if field is set in presence vector
                 */
                if (sequence.isOptional())
                {
                    code.publicMethods.add(CppCode.createMethodDeclaration("bool", "is", shortClassName + "Valid", null, true));
                    methodCode.clear();
                    methodCode.add("return true;");
                    methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                    methodCode.add("return false;");
                    code.methods.addAll(CppCode.createMethodDefinition("bool", parentClassName + "::is", shortClassName + "Valid", null, methodCode, true));
                }

                /* 	m_Record1.encode(bytes + pos);
                 *	pos += m_Record1.getSize();
                 */
                methodCode.clear();

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
                if (sequence.isOptional())
                {
                    methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                }
                code.encoderLines.addAll(methodCode);

                /* 	m_Record1.decode(bytes + pos);
                 *	pos += m_Record1.getSize();
                 */
                methodCode.clear();

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
                if (sequence.isOptional())
                {
                    methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                }
                code.decoderLines.addAll(methodCode);


                /// getNumberOfElements()
                methodCode.clear();
                methodParam.clear();
                code.publicMethods.add(CppCode.createMethodDeclaration("unsigned int", "get", "NumberOfElements", null, true));
                methodCode.add("return (unsigned int) " + varName + ".size();");
                code.methods.addAll(CppCode.createMethodDefinition("unsigned int", parentClassName + "::get", "NumberOfElements", null, methodCode, true));

                /// getElement(index)
                methodCode.clear();
                methodParam.clear();
                methodParam.add("unsigned int " + "index");
                code.publicMethods.add(CppCode.createMethodDeclaration(shortClassName + "* const", "get", "Element", methodParam, false));
                methodCode.add("return &" + varName + ".at(index);");
                code.methods.addAll(CppCode.createMethodDefinition(fullClassName + "* const", parentClassName + "::get", "Element", methodParam, methodCode, false));

                /// setElement(index,value)
                methodCode.clear();
                methodParam.clear();
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

                /// addElement(value)
                methodCode.clear();
                methodParam.clear();
                methodParam.add("const " + shortClassName + " &value");
                code.publicMethods.add(CppCode.createMethodDeclaration("int", "add", "Element", methodParam, false));
                methodCode.add(varName + ".push_back(value);");
                // Create framework for parent reference
                methodCode.add(CppCode.getParentReferenceVariableSetParent(varName + ".back()"));
                methodCode.add(CppCode.getParentReferenceSetParentPVLine());
                methodCode.add("return 0;");
                code.methods.addAll(CppCode.createMethodDefinition("int", parentClassName + "::add", "Element", methodParam, methodCode, false));

                /// deleteElement(index)
                methodCode.clear();
                methodParam.clear();
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

                /// deleteLastElement()
                methodCode.clear();
                methodParam.clear();
                code.publicMethods.add(CppCode.createMethodDeclaration("int", "delete", "LastElement", methodParam, false));
                methodCode.add(varName + ".pop_back();");
                methodCode.add("return 0;");
                code.methods.addAll(CppCode.createMethodDefinition("int", parentClassName + "::delete", "LastElement", methodParam, methodCode, false));


                /// Overload the operator=
                /// Add code to the assignment lines of code
                methodCode.clear();
                methodCode.add(varName + ".clear();");
                methodCode.add("");
                methodCode.add("for (int i = 0; i < value." + varName + ".size(); i++)");
                methodCode.add("{");
                methodCode.add("\t" + varName + ".push_back(value." + varName + "[i]);");
                // Create framework for parent reference
                methodCode.add("\t" + CppCode.getParentReferenceVariableSetParentInVector(varName));
                methodCode.add("}");
                if (sequence.isOptional())
                {
                    methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                }
                code.assignmentLines.addAll(methodCode);


                /// Overload operator==
                /// Add code for checking equality
                methodCode.clear();
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
                if (sequence.isOptional())
                {
                    methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                }
                if (!code.equalLines.isEmpty())
                {
                    code.equalLines.add("");
                }
                code.equalLines.addAll(methodCode);

                /// Overload operator!=
                /// Add code checking inequality
                methodCode.clear();
                methodCode.add("for (int i = 0; i < " + varName + ".size(); i++)");
                methodCode.add("{");
                methodCode.add("\tif (" + varName + "[i] == value." + varName + "[i])");
                methodCode.add("\t{");
                methodCode.add("\t\treturn false;");
                methodCode.add("\t}");
                methodCode.add("}");
                if (sequence.isOptional())
                {
                    methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                }
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                fullClassName = parentClassName + "." + shortClassName;

                /*
                 * Create code lines for the encode, decode Methods
                 * Create the getMethod and setMethod
                 * This has to be done after the class wrapper has been added
                 */
                String varName = JavaCode.getVariableName(shortClassName);
    
                

                code.protectedAttributes.add(JavaCode.createVariableDeclaration("protected ArrayList<" + shortClassName + ">", shortClassName, false));
                code.constructorLines.add( varName + " = new ArrayList<" + shortClassName + ">();");

                /*
                 * Create framework for parent reference
                 */
                JavaCode.addParentReferenceConstructorSetParrentInArray(code, varName);

                /*
                 * GetSize method
                 * only add size of array if optional is true
                 */
                JavaCode.generateGetSizeLines(JavaCode.getVariableType(countFieldType), methodCode, countFieldSigned);
                methodCode.add("for (int i = 0; i < " + varName + ".size(); i++)");
                methodCode.add("{");
                methodCode.add("	size += " + varName + ".get(i).getSize();");
                methodCode.add("}");
                if (sequence.isOptional())
                {
                    methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                }
                code.sizeLines.addAll(methodCode);

                /*
                 * is[Field]valid added if optional
                 * return true if field is set in presence vector
                 */
                if (sequence.isOptional())
                {
                    methodCode.clear();
                    methodCode.add("return true;");
                    methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                    methodCode.add("return false;");
                    code.methods.addAll(JavaCode.createMethodDefinition("public boolean", "is", shortClassName + "Valid", null, methodCode, true));
                }

                /* 	m_Record1.encode(bytes + pos);
                 *	pos += m_Record1.getSize();
                 */
                methodCode.clear();

                /* First set the count */
                methodCode.add(JavaCode.getVariableType(countFieldType) + " size = (" + JavaCode.getVariableType(countFieldType) + ") " + varName + ".size();");
                JavaCode.generateEncoderLines(JavaCode.getVariableType(countFieldType), "size", methodCode, countFieldSigned);

                /* now serialize each element */
                methodCode.add("for (int i = 0; i < " + varName + ".size(); i++)");
                methodCode.add("{");
                methodCode.add("\t" + varName + ".get(i).encode(bytes, pos);");
                methodCode.add("\tpos += " + varName + ".get(i).getSize();");
                methodCode.add("}");

                if (sequence.isOptional())
                {
                    methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                }
                code.encoderLines.addAll(methodCode);

                /* 	m_Record1.decode(bytes + pos);
                 *	pos += m_Record1.getSize();
                 */
                methodCode.clear();

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
                if (sequence.isOptional())
                {
                    methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                }
                code.decoderLines.addAll(methodCode);


                /// getNumberOfElements()
                methodCode.clear();
                methodParam.clear();
                methodCode.add("return " + varName + ".size();");
                code.methods.addAll(JavaCode.createMethodDefinition("public int", "get", "NumberOfElements", null, methodCode, true));

                /// getElement(index)
                methodCode.clear();
                methodParam.clear();
                methodParam.add("int " + "index");
                methodCode.add("return " + varName + ".get(index);");
                code.methods.addAll(JavaCode.createMethodDefinition("public " + fullClassName, "get", "Element", methodParam, methodCode, false));

                /// setElement(index,value)
                methodCode.clear();
                methodParam.clear();
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

                /// addElement(value)
                methodCode.clear();
                methodParam.clear();
                methodParam.add(shortClassName + " value");
                methodCode.add(varName + ".add(value);");
                // Create framework for parent reference
                methodCode.add(JavaCode.getParentReferenceVariableSetParent(varName + ".get(" + varName + ".size() -1)"));
                methodCode.add(JavaCode.getParentReferenceSetParentPVLine());
                code.methods.addAll(JavaCode.createMethodDefinition("public void", "add", "Element", methodParam, methodCode, false));

                /// deleteElement(index)
                methodCode.clear();
                methodParam.clear();
                methodParam.add("int " + "index");
                methodCode.add("if(" + varName + ".size()-1 < index)");
                methodCode.add("{");
                methodCode.add("\treturn 1;");
                methodCode.add("}");
                methodCode.add("");
                methodCode.add(varName + ".remove(index);");
                methodCode.add("return 0;");
                code.methods.addAll(JavaCode.createMethodDefinition("public int", "delete", "Element", methodParam, methodCode, false));

                /// deleteLastElement()
                methodCode.clear();
                methodParam.clear();
                methodCode.add(varName + ".remove("+varName +".size() -1);");
                methodCode.add("return 0;");
                code.methods.addAll(JavaCode.createMethodDefinition("public int", "delete", "LastElement", methodParam, methodCode, false));


                /// Overload the operator=
                /// Add code to the assignment lines of code
                methodCode.clear();
                methodCode.add(varName + ".clear();");
                methodCode.add("");
                methodCode.add("for (int i = 0; i < value." + varName + ".size(); i++)");
                methodCode.add("{");
                methodCode.add("\t" + varName + ".add(value." + varName + ".get(i));");
                // Create framework for parent reference
                methodCode.add("\t" + JavaCode.getParentReferenceVariableSetParentInVector(varName));
                methodCode.add("}");
                if (sequence.isOptional())
                {
                    methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                }
                code.assignmentLines.addAll(methodCode);


                /// Overload operator==
                /// Add code for checking equality
                methodCode.clear();
                methodCode.add("if (" + varName + ".size() != value." + varName + ".size())");
                methodCode.add("{");
                methodCode.add("\treturn false;");
                methodCode.add("}");
                methodCode.add("");
                methodCode.add("for (int i = 0; i < " + varName + ".size(); i++)");
                methodCode.add("{");
                methodCode.add("\tif (!" + varName + ".get(i).isEqual(value." + varName + ".get(i)))");
                methodCode.add("\t{");
                methodCode.add("\t\treturn false;");
                methodCode.add("\t}");
                methodCode.add("}");
                if (sequence.isOptional())
                {
                    methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                }
                if (!code.equalLines.isEmpty())
                {
                    code.equalLines.add("");
                }
                code.equalLines.addAll(methodCode);

                /// Overload operator!=
                /// Add code checking inequality
                methodCode.clear();
                methodCode.add("for (int i = 0; i < " + varName + ".size(); i++)");
                methodCode.add("{");
                methodCode.add("\tif (!" + varName + ".get(i).notEquals(value." + varName + ".get(i)))");
                methodCode.add("\t{");
                methodCode.add("\t\treturn false;");
                methodCode.add("\t}");
                methodCode.add("}");
                if (sequence.isOptional())
                {
                    methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                }
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                fullClassName = parentClassName + "." + shortClassName;

                /*
                 * Create code lines for the encode, decode Methods
                 * Create the getMethod and setMethod
                 * This has to be done after the class wrapper has been added
                 */
                String varName = CSharpCode.getVariableName(shortClassName);

                

                code.protectedAttributes.add(CSharpCode.createVariableDeclaration("protected List<" + shortClassName + ">", shortClassName, false));
                code.constructorLines.add( varName + " = new List<" + shortClassName + ">();");
    
                /*
                 * Create framework for parent reference
                 */
                CSharpCode.addParentReferenceConstructorSetParrentInArray(code, varName);
                /*
                 * GetSize method
                 * only add size of array if optional is true
                 */                
                CSharpCode.generateSizeLines(CSharpCode.getVariableType(countFieldType), methodCode);
                methodCode.add("for (int i = 0; i < " + varName + ".Count; i++)");
                methodCode.add("{");
                methodCode.add("	size += " + varName + "[i].getSize();");
                methodCode.add("}");
                if (sequence.isOptional())
                {
                    methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
                }
                code.sizeLines.addAll(methodCode);

                /*
                 * is[Field]valid added if optional
                 * return true if field is set in presence vector
                 */
                if (sequence.isOptional())
                {
                    methodCode.clear();
                    methodCode.add("return true;");
                    methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
                    methodCode.add("return false;");
                    code.methods.addAll(CSharpCode.createMethodDefinition("public bool", "is", shortClassName + "Valid", null, methodCode, true));
                }

                /* 	m_Record1.encode(bytes + pos);
                 *	pos += m_Record1.getSize();
                 */
                methodCode.clear();

                /* First set the count */
                methodCode.add(CSharpCode.getVariableType(countFieldType) + " size = (" + CSharpCode.getVariableType(countFieldType) + ") " + varName + ".Count;");
                CSharpCode.generateEncoderLines(CSharpCode.getVariableType(countFieldType), "size", methodCode);

                /* now serialize each element */
                methodCode.add("for (int i = 0; i < " + varName + ".Count; i++)");
                methodCode.add("{");
                methodCode.add("\t" + varName + "[i].encode(bytes, pos);");
                methodCode.add("\tpos += " + varName + "[i].getSize();");
                methodCode.add("}");
                if (sequence.isOptional())
                {
                    methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
                }
                code.encoderLines.addAll(methodCode);

                /* 	m_Record1.decode(bytes + pos);
                 *	pos += m_Record1.getSize();
                 */
                methodCode.clear();

                /* pull the count */
                methodCode.add(CSharpCode.getVariableType(countFieldType) + " size;");
                CSharpCode.generateDecoderLines(CSharpCode.getVariableType(countFieldType), "size" , methodCode);
                methodCode.add(varName + " = new List<" + shortClassName + ">();");
                methodCode.add("");

                /* deserialize each element */
                methodCode.add("for (int i = 0; i < size; i++)");
                methodCode.add("{");
                methodCode.add("\t" + varName + ".Add(new " + shortClassName + "());");
                methodCode.add("\t" + varName + "[i].decode(bytes, pos);");
                methodCode.add("\tpos += " + varName + "[i].getSize();");
                methodCode.add("}");
                if (sequence.isOptional())
                {
                    methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
                }
                code.decoderLines.addAll(methodCode);


                /// getNumberOfElements()
                methodCode.clear();
                methodParam.clear();
                methodCode.add("return  " + varName + ".Count;");
                code.methods.addAll(CSharpCode.createMethodDefinition("public int", "get", "NumberOfElements", null, methodCode, true));

                /// getElement(index)
                methodCode.clear();
                methodParam.clear();
                methodParam.add("int " + "index");
                methodCode.add("return " + varName + "[index];");
                code.methods.addAll(CSharpCode.createMethodDefinition("public " + fullClassName, "get", "Element", methodParam, methodCode, false));

                /// setElement(index,value)
                methodCode.clear();
                methodParam.clear();
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

                /// addElement(value)
                methodCode.clear();
                methodParam.clear();
                methodParam.add(shortClassName + " value");
                methodCode.add(varName + ".Add(value);");
                // Create framework for parent reference
                methodCode.add(CSharpCode.getParentReferenceVariableSetParent(varName + "[" + varName + ".Count-1]"));
                methodCode.add(CSharpCode.getParentReferenceSetParentPVLine());
                code.methods.addAll(CSharpCode.createMethodDefinition("public void", "add", "Element", methodParam, methodCode, false));

                /// deleteElement(index)
                methodCode.clear();
                methodParam.clear();
                methodParam.add("int " + "index");
                methodCode.add("if(" + varName + ".Count-1 < index)");
                methodCode.add("{");
                methodCode.add("\treturn;");
                methodCode.add("}");
                methodCode.add("");
                methodCode.add(varName + ".RemoveAt(index);");
                code.methods.addAll(CSharpCode.createMethodDefinition("public void", "delete", "Element", methodParam, methodCode, false));

                /// deleteLastElement()
                methodCode.clear();
                methodParam.clear();
                methodCode.add(varName + ".RemoveAt("+ varName +".Count -1);");
                code.methods.addAll(CSharpCode.createMethodDefinition("public void", "delete", "LastElement", methodParam, methodCode, false));


                /// Overload the operator=
                /// Add code to the assignment lines of code
                methodCode.clear();
                methodCode.add(varName + ".Clear();");
                methodCode.add("");
                methodCode.add("for (int i = 0; i < value." + varName + ".Count; i++)");
                methodCode.add("{");
                methodCode.add("\t" + varName + ".Add(value." + varName + "[i]);");
                // Create framework for parent reference
                methodCode.add("\t" + CppCode.getParentReferenceVariableSetParentInVector(varName));
                methodCode.add("}");
                if (sequence.isOptional())
                {
                    methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
                }
                code.assignmentLines.addAll(methodCode);


                /// Overload operator==
                /// Add code for checking equality
                methodCode.clear();
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
                if (sequence.isOptional())
                {
                    methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
                }
                if (!code.equalLines.isEmpty())
                {
                    code.equalLines.add("");
                }
                code.equalLines.addAll(methodCode);

                /// Overload operator!=
                /// Add code checking inequality
                methodCode.clear();
                methodCode.add("for (int i = 0; i < " + varName + ".Count; i++)");
                methodCode.add("{");
                methodCode.add("\tif (!this." + varName+ "[i].notEquals(value." + varName + "[i]))");
                methodCode.add("\t{");
                methodCode.add("\t\treturn false;");
                methodCode.add("\t}");
                methodCode.add("}");
                if (sequence.isOptional())
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

