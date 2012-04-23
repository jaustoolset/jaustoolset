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
 * @(#)VariableFieldGenerator.java		0.1 2008/10/06
 * 
 * Copyright
 */
package org.jts.codegenerator;


import org.jts.jsidl.binding.*;

import java.util.List;
import java.util.ArrayList;


// TODO: write CPPUnit Tests for operator=, operator==, operator!=
/**
 * This class will generate the code for a VariableField JAXB Object 
 * @author Nicholas M. Johnson
 *
 */
public class VariableFieldGenerator
{
	private CodeLines.CodeType codeType;
    private VariableField variableField;
    private List<Dimension> dimList;
    
	private List<String> methodCode;
	private List<String> methodParam;
	private String indexVarType;
	private String indexVarName;
    private boolean indexVarSigned;
	private String tempVarName;
	private String fullClassName;
	private String varName;
	private String shortClassName;
	private String parentClassName;
	private String fieldName;
	private String variableName;

    private boolean teSigned;
	
    
    /**
     * Constructor.
     * 
     * @param codeType
     * @param variableField
     */
    public VariableFieldGenerator(CodeLines.CodeType codeType, VariableField variableField) 
    {
    	this.codeType = codeType;
        this.variableField = variableField;
        this.dimList = null;
        
        methodCode = new ArrayList<String>();
        methodParam = new ArrayList<String>();
        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            indexVarType = CppCode.getVariableType("unsigned byte");	// The variable type for this field within the Variable Field
            indexVarName =  CppCode.getVariableName("index");
            tempVarName = indexVarName + "Temp";
            fieldName = Util.upperCaseFirstLetter(variableField.getName());
            variableName =  CppCode.getVariableName(fieldName);
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            indexVarType = JavaCode.getVariableType("unsigned byte");	// The variable type for this field within the Variable Field
            indexVarSigned = true;
            indexVarName =  JavaCode.getVariableName("index");
            tempVarName = indexVarName + "Temp";
            fieldName = Util.upperCaseFirstLetter(variableField.getName());
            variableName =  JavaCode.getVariableName(fieldName);
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            indexVarType = CSharpCode.getVariableType("unsigned byte");	// The variable type for this field within the Variable Field
            indexVarName =  CSharpCode.getVariableName("index");
            tempVarName = indexVarName + "Temp";
            fieldName = Util.upperCaseFirstLetter(variableField.getName());
            variableName =  CSharpCode.getVariableName(fieldName);
        }
    }
    

    /**
     * Constructor.
     * 
     * @param codeType
     * @param variableField
     */
    public VariableFieldGenerator(CodeLines.CodeType codeType, VariableField variableField, List<Dimension> dimList) 
    {
    	this.codeType = codeType;
        this.variableField = variableField;
        this.dimList = dimList;
        
        methodCode = new ArrayList<String>();
        methodParam = new ArrayList<String>();
        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            indexVarType = CppCode.getVariableType("unsigned byte");	// The variable type for this field within the Variable Field
            indexVarName =  CppCode.getVariableName("index");
            tempVarName = indexVarName + "Temp";
            fieldName = Util.upperCaseFirstLetter(variableField.getName());
            variableName =  CppCode.getVariableName(fieldName);
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            indexVarType = JavaCode.getVariableType("byte");	// The variable type for this field within the Variable Field
            indexVarSigned = true;
            indexVarName =  JavaCode.getVariableName("index");
            tempVarName = indexVarName + "Temp";
            fieldName = Util.upperCaseFirstLetter(variableField.getName());
            variableName =  JavaCode.getVariableName(fieldName);
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            indexVarType = CSharpCode.getVariableType("byte");	// The variable type for this field within the Variable Field
            indexVarName =  CSharpCode.getVariableName("index");
            tempVarName = indexVarName + "Temp";
            fieldName = Util.upperCaseFirstLetter(variableField.getName());
            variableName =  CSharpCode.getVariableName(fieldName);
        }
    }
   
 
    /**
     * After calling this function, the CodeLines object provided will be filled with the necessary
	 * code lines. 
     * 
     * @param pvIndex
     * @param isNested
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
            fullClassName = parentName + "::" + variableField.getName();
            shortClassName = CppCode.getShortClassName(fullClassName);
            parentClassName = CppCode.getParentClassName(fullClassName);
            varName = CppCode.getVariableName(shortClassName);
            
            /*
             * Create framework for parent reference
             */
           	CppCode.addParentReference(code, fullClassName, pvIndex, variableField.isOptional());
    	}
        else if (codeType == CodeLines.CodeType.JAVA)
    	{
            fullClassName = parentName + "." + variableField.getName();
            shortClassName = JavaCode.getShortClassName(fullClassName);
            parentClassName = JavaCode.getParentClassName(fullClassName);
            varName = JavaCode.getVariableName(shortClassName);

            /*
             * Create framework for parent reference
             */
           	JavaCode.addParentReference(code, fullClassName, pvIndex, variableField.isOptional());
    	}
        else if (codeType == CodeLines.CodeType.C_SHARP)
    	{
            fullClassName = parentName + "." + variableField.getName();
            shortClassName = CSharpCode.getShortClassName(fullClassName);
            parentClassName = CSharpCode.getParentClassName(fullClassName);
            varName = CSharpCode.getVariableName(shortClassName);
            /*
             * Create framework for parent reference
             */
           	CSharpCode.addParentReference(code, fullClassName, pvIndex, variableField.isOptional());
    	}

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
            /// Generate an array of VariableFields
            generateArray(pvIndex, code);
        }
    }
   
	/**
     *	Generate field
     * 
     * @param parentClassName
     * @param isNested
     * @param code
     * @throws CodeGeneratorException
     */
    private void generateFieldCode(int pvIndex, CodeLines vfCode) throws CodeGeneratorException
    {
        String unionFieldName = "";
        String unionFieldVarName = "";
    	// Create the protected Attributes
        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            vfCode.protectedAttributes.add(CppCode.createVariableDeclaration(indexVarType, "index", false));
        
            vfCode.constructorLines.add(indexVarName + " = " + variableField.getTypeAndUnitsField().getTypeAndUnitsEnum().get(0).getIndex()+ ";");
            vfCode.sizeLines.add("size += sizeof(" + indexVarType + ");");

            generateFieldEncodeMethod(vfCode, pvIndex);
            generateFieldDecodeMethod(vfCode, pvIndex);

            generateFieldGetMethod(vfCode);
            generateFieldSetMethod(vfCode);

            generateFieldOverloadAssignmentMethod(vfCode);
            generateFieldOverloadIsEqualMethod(vfCode);
            generateFieldOverloadNotEqualMethod(vfCode);

            /// Generate the relevant lines for each data type within the Variant Field
            /// ***********************************************************************
            boolean first = true;

            unionFieldName = "variable_field_type";
            vfCode.protectedAttributes.add("union");
            vfCode.protectedAttributes.add("{");
            int enumIndex = 0;
            for(TypeAndUnitsEnum taue: variableField.getTypeAndUnitsField().getTypeAndUnitsEnum())
            {
                int index = taue.getIndex();
                String teType = CppCode.getVariableType(taue.getFieldType());
                String teName = Util.makeOneWord(taue.getFieldUnits()) + "As" + Util.makeOneWord(taue.getFieldType()) + "At" + Integer.toString( enumIndex++ );
                String teVarName = CppCode.getVariableName(teName);
                String teVarQualifiedName = unionFieldName + "." + teVarName;
                String teFieldType = taue.getFieldType();

                vfCode.protectedAttributes.add("\t" + teType + " " + teVarName  + ";");
                if (first)
                {
                    first = false;
	            vfCode.constructorLines.add(teVarQualifiedName + " = 0;");
                }

                generateTAUEGetSizeMethod(vfCode, teType, index);
                generateTAUEEncodeMethod(vfCode, teType, teVarQualifiedName, index);
                generateTAUEDecodeMethod(vfCode, teType, teVarQualifiedName, index);

                generateTAUEOverloadAssignmentMethod(vfCode, teVarQualifiedName, index, teType);
                generateTAUEOverloadIsEqualMethod(vfCode, teVarQualifiedName, index);
                generateTAUEOverloadNotEqualMethod(vfCode, teVarQualifiedName, index);

                /// If this TypeAndUnitsEnum is a Scaled Range
                if (taue.getScaleRange() != null)
                {
                    ScaleRangeGenerator srGen = null;

                    try
                    {
                        srGen = new ScaleRangeGenerator(codeType, taue.getScaleRange());
                    }
                    catch(Exception ex)
                    {
                        throw new RuntimeException("Exception creating scale range generator", ex);
                    }

                    // Generate the classCode to change the value to a scaled value
                    /// scaled integer (float to integer and integer to float conversion required)
                    generateTAUEGetMethod(vfCode, teFieldType, teName, teVarQualifiedName, srGen);
                    generateTAUESetMethod(vfCode, index, teFieldType, teName, teVarQualifiedName, srGen);
                }
                /// The TypeAndUnitEnums is not a Scaled Range
                else
                {
                    generateTAUEGetMethod(vfCode, teType, teName, teVarQualifiedName);
                    generateTAUESetMethod(vfCode, index, teType, teName, teVarQualifiedName, taue.getValueSet());
                }
            }
            vfCode.protectedAttributes.add("} "+ unionFieldName +";");  
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            vfCode.protectedAttributes.add(JavaCode.createVariableDeclaration("protected " + indexVarType, "index", false));

            vfCode.constructorLines.add(indexVarName + " = " + variableField.getTypeAndUnitsField().getTypeAndUnitsEnum().get(0).getIndex()+ ";");
            JavaCode.generateGetSizeLines(indexVarType, vfCode.sizeLines, indexVarSigned);

            generateFieldEncodeMethod(vfCode, pvIndex);
            generateFieldDecodeMethod(vfCode, pvIndex);

            generateFieldGetMethod(vfCode);
            generateFieldSetMethod(vfCode);

            generateFieldOverloadAssignmentMethod(vfCode);
            generateFieldOverloadIsEqualMethod(vfCode);
            generateFieldOverloadNotEqualMethod(vfCode);

            /// Generate the relevant lines for each data type within the Variant Field
            /// ***********************************************************************
            boolean first = true;

            //unionFieldName = "variable_field_type";
//            vfCode.protectedAttributes.add("union");
//            vfCode.protectedAttributes.add("{");
            int enumIndex = 0;
            for(TypeAndUnitsEnum taue: variableField.getTypeAndUnitsField().getTypeAndUnitsEnum())
            {
                int index = taue.getIndex();
                String teType = JavaCode.getVariableType(taue.getFieldType());
                teSigned = JavaCode.getVariableSign(taue.getFieldType());
                String teName = Util.makeOneWord(taue.getFieldUnits()) + "As" + Util.makeOneWord(taue.getFieldType()) + "At" + Integer.toString( enumIndex++ );
                String teVarName = JavaCode.getVariableName(teName);
                String teVarQualifiedName = teVarName;
                String teFieldType = taue.getFieldType();

                vfCode.protectedAttributes.add( teType + " " + teVarName  + ";");
                if (first)
                {
                    first = false;
	            vfCode.constructorLines.add(teVarQualifiedName + " = 0;");
                }

                generateTAUEGetSizeMethod(vfCode, teType, index);
                generateTAUEEncodeMethod(vfCode, teType, teVarQualifiedName, index);
                generateTAUEDecodeMethod(vfCode, teType, teVarQualifiedName, index);

                generateTAUEOverloadAssignmentMethod(vfCode, teVarQualifiedName, index, teType);
                generateTAUEOverloadIsEqualMethod(vfCode, teVarQualifiedName, index);
                generateTAUEOverloadNotEqualMethod(vfCode, teVarQualifiedName, index);

                /// If this TypeAndUnitsEnum is a Scaled Range
                if (taue.getScaleRange() != null)
                {
                    ScaleRangeGenerator srGen = null;

                    try
                    {
                        srGen = new ScaleRangeGenerator(codeType, taue.getScaleRange());
                    }
                    catch(Exception e)
                    {
                        throw new RuntimeException("Exception creating scale range generator", e);
                    }

                    // Generate the classCode to change the value to a scaled value
                    /// scaled integer (float to integer and integer to float conversion required)
                    generateTAUEGetMethod(vfCode, teFieldType, teName, teVarQualifiedName, srGen);
                    generateTAUESetMethod(vfCode, index, teFieldType, teName, teVarQualifiedName, srGen);
                }
                /// The TypeAndUnitEnums is not a Scaled Range
                else
                {
                    generateTAUEGetMethod(vfCode, teType, teName, teVarQualifiedName);
                    generateTAUESetMethod(vfCode, index, teType, teName, teVarQualifiedName, taue.getValueSet());
                }
            }
            //vfCode.protectedAttributes.add("} "+ unionFieldName +";");
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            vfCode.protectedAttributes.add(CSharpCode.createVariableDeclaration("protected " + indexVarType, "index", true));

            vfCode.constructorLines.add(indexVarName + " = " + variableField.getTypeAndUnitsField().getTypeAndUnitsEnum().get(0).getIndex()+ ";");
            vfCode.sizeLines.add("size += sizeof(" + indexVarType + ");");

            generateFieldEncodeMethod(vfCode, pvIndex);
            generateFieldDecodeMethod(vfCode, pvIndex);

            generateFieldGetMethod(vfCode);
            generateFieldSetMethod(vfCode);

            generateFieldOverloadAssignmentMethod(vfCode);
            generateFieldOverloadIsEqualMethod(vfCode);
            generateFieldOverloadNotEqualMethod(vfCode);

            /// Generate the relevant lines for each data type within the Variant Field
            /// ***********************************************************************
            boolean first = true;

            unionFieldVarName = "variable_field_type";
            unionFieldName = "VariableFieldType";            
            vfCode.protectedAttributes.add("[StructLayout(LayoutKind.Explicit)]");
            vfCode.protectedAttributes.add("protected struct " + unionFieldName);
            vfCode.protectedAttributes.add("{");
            int enumIndex = 0;
            for(TypeAndUnitsEnum taue: variableField.getTypeAndUnitsField().getTypeAndUnitsEnum())
            {
                int index = taue.getIndex();
                String teType = CSharpCode.getVariableType(taue.getFieldType());
                String teName = Util.makeOneWord(taue.getFieldUnits()) + "As" + Util.makeOneWord(taue.getFieldType()) + "At" + Integer.toString( enumIndex++ );
                String teVarName = CSharpCode.getVariableName(teName);
                String teVarQualifiedName = unionFieldVarName + "." + teVarName;
                String teFieldType = taue.getFieldType();

                vfCode.protectedAttributes.add("\t[FieldOffset(0)]");
                vfCode.protectedAttributes.add("\tpublic " + teType + " " + teVarName  + ";");
                if (first)
                {
                    first = false;
	            vfCode.constructorLines.add(teVarQualifiedName + " = 0;");
                }

                generateTAUEGetSizeMethod(vfCode, teType, index);
                generateTAUEEncodeMethod(vfCode, teType, teVarQualifiedName, index);
                generateTAUEDecodeMethod(vfCode, teType, teVarQualifiedName, index);

                generateTAUEOverloadAssignmentMethod(vfCode, teVarQualifiedName, index, teType);
                generateTAUEOverloadIsEqualMethod(vfCode, teVarQualifiedName, index);
                generateTAUEOverloadNotEqualMethod(vfCode, teVarQualifiedName, index);

                /// If this TypeAndUnitsEnum is a Scaled Range
                if (taue.getScaleRange() != null)
                {
                    ScaleRangeGenerator srGen = null;

                    try
                    {
                        srGen = new ScaleRangeGenerator(codeType, taue.getScaleRange());
                    }
                    catch(Exception e)
                    {
                        throw new RuntimeException("Exception creating scale range generator", e);
                    }

                    // Generate the classCode to change the value to a scaled value
                    /// scaled integer (float to integer and integer to float conversion required)
                    generateTAUEGetMethod(vfCode, teFieldType, teName, teVarQualifiedName, srGen);
                    generateTAUESetMethod(vfCode, index, teFieldType, teName, teVarQualifiedName, srGen);
                }
                /// The TypeAndUnitEnums is not a Scaled Range
                else
                {
                    generateTAUEGetMethod(vfCode, teType, teName, teVarQualifiedName);
                    generateTAUESetMethod(vfCode, index, teType, teName, teVarQualifiedName, taue.getValueSet());
                }
            }            
            vfCode.protectedAttributes.add("}");
            vfCode.protectedAttributes.add(unionFieldName + " " + unionFieldVarName + "= new " + unionFieldName + "();");
        }
        
         
        vfCode.equalLines.add("");
        vfCode.equalLines.add("return true;");
        
        vfCode.notEqualLines.add("");
        vfCode.notEqualLines.add("return true;");
    }
        
    private void generateFieldEncodeMethod(CodeLines code, int pvIndex)
    {
    	methodCode.clear(); 	

        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            /// Create the encoder lines
            methodCode.add(indexVarType + " " + tempVarName + ";");
            methodCode.add("");
            methodCode.add(tempVarName + " = " + code.getNameSpace() + "::correctEndianness(" + indexVarName + ");");
            methodCode.add("memcpy(bytes + pos, &" + tempVarName + ", sizeof(" + indexVarType + "));");
            methodCode.add("pos += sizeof(" + indexVarType + ");");
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            JavaCode.generateEncoderLines(indexVarType, indexVarName, methodCode, indexVarSigned);
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            CSharpCode.generateEncoderLines(indexVarType, indexVarName, methodCode);
        }
        
	    /// If the encoder lines are not empty then put a line to increase readability
	    if (!code.encoderLines.isEmpty())
	    {
	    	code.encoderLines.add("");
	    }
	    
        code.encoderLines.addAll(methodCode);
    }
    
    private void generateFieldDecodeMethod(CodeLines code, int pvIndex)
    {
    	methodCode.clear();
    	
        /// Create the decoder lines
    	methodCode.add(indexVarType + " " + tempVarName + ";");
    	methodCode.add("");
        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            methodCode.add("memcpy(&" + tempVarName + ", bytes + pos, sizeof(" + indexVarType + "));");
            methodCode.add(indexVarName + " = " + code.getNameSpace() +  "::correctEndianness(" + tempVarName + ");");
            methodCode.add("pos += sizeof(" + indexVarType + ");");
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            JavaCode.generateDecoderLines(indexVarType, indexVarName, methodCode, indexVarSigned);

        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            CSharpCode.generateDecoderLines(indexVarType, indexVarName, methodCode);
        }
        
	    /// If the encoder lines are not empty then put a line to increase readability
	    if (!code.decoderLines.isEmpty())
	    {
	    	code.decoderLines.add("");
	    }
	    
        code.decoderLines.addAll(methodCode);
    }
    
    private void generateFieldGetMethod(CodeLines code)
    {
    	///  Generate getMethod Declaration and Definition
        methodParam.clear();
        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            code.publicMethods.add(CppCode.createMethodDeclaration("const " + indexVarType, "get", "index", methodParam, true));

            methodCode.clear();
            methodCode.add("return " + indexVarName + ";");
            code.methods.addAll(CppCode.createMethodDefinition("const " + indexVarType, fullClassName + "::get", "index", methodParam, methodCode, true));
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            methodCode.clear();
            methodCode.add("return " + indexVarName + ";");
            code.methods.addAll(JavaCode.createMethodDefinition("public " + indexVarType, "get", "index", methodParam, methodCode, true));
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            methodCode.clear();
            methodCode.add("return " + indexVarName + ";");
            code.methods.addAll(CSharpCode.createMethodDefinition("public " + indexVarType, "get", "index", methodParam, methodCode, true));
        }
    }
    
    private void generateFieldSetMethod(CodeLines code)
    {
    	methodCode.clear();
    	methodParam.clear();
    	
        ///  Generate setMethod Declaration and Definition
        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            methodParam.add(indexVarType + " value");
            code.publicMethods.add(CppCode.createMethodDeclaration("int", "set", "index", methodParam, false));

            methodCode.add(indexVarName + " = value;");
            // Create framework for parent reference
            methodCode.add(CppCode.getParentReferenceSetParentPVLine());
            methodCode.add("return 0;");
            addIndexCheck("value", methodCode);
            methodCode.add("return 1;");
            code.methods.addAll(CppCode.createMethodDefinition("int", fullClassName + "::set", "index", methodParam, methodCode, false));
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            methodParam.add(indexVarType + " value");
            methodCode.add(indexVarName + " = value;");
            methodCode.add(JavaCode.getParentReferenceSetParentPVLine());
            methodCode.add("return 0;");
            addIndexCheck("value", methodCode);
            methodCode.add("return 1;");
            code.methods.addAll(JavaCode.createMethodDefinition("public int", "set", "index", methodParam, methodCode, false));
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            methodParam.add(indexVarType + " value");
            methodCode.add(indexVarName + " = value;");
            methodCode.add(CSharpCode.getParentReferenceSetParentPVLine());
            methodCode.add("return 0;");
            addIndexCheck("value", methodCode);
            methodCode.add("return 1;");
            code.methods.addAll(CSharpCode.createMethodDefinition("public int", "set", "index", methodParam, methodCode, false));
        }
    }
    
    private void generateFieldOverloadAssignmentMethod(CodeLines code)
    {
    	/// Add code to Overload the operator=
        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            code.assignmentLines.add("this->" + indexVarName + " = value." + indexVarName + ";");
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            code.assignmentLines.add("this." + indexVarName + "= value." + indexVarName + ";");
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            code.assignmentLines.add("this." + indexVarName + "= value.get" + indexVarName.substring(indexVarName.indexOf(CSharpCode.VARIABLE_PREFIX)+2) + "();");
        }
        
    }
    
    private void generateFieldOverloadIsEqualMethod(CodeLines code)
    {
        /// Add code to Overload operator==
        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            code.equalLines.add("if (this->" + indexVarName + " != value." + indexVarName + ")");
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            code.equalLines.add("if (this." + indexVarName + " != value." + indexVarName + ")");
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            code.equalLines.add("if (this.get" + indexVarName.substring(indexVarName.indexOf(CSharpCode.VARIABLE_PREFIX)+2) + "() != value.get" + indexVarName.substring(indexVarName.indexOf(CSharpCode.VARIABLE_PREFIX)+2) + "())");
        }
    	code.equalLines.add("{");
    	code.equalLines.add("\treturn false;");
    	code.equalLines.add("}");
    }
    
    private void generateFieldOverloadNotEqualMethod(CodeLines code)
    {
        /// Add code to Overload operator!=
    	 if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            code.notEqualLines.add("if (this->" + indexVarName + " == value." + indexVarName + ")");
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            code.notEqualLines.add("if (this." + indexVarName + " == value." + indexVarName + ")");
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            code.notEqualLines.add("if (this.get" + indexVarName.substring(indexVarName.indexOf(CSharpCode.VARIABLE_PREFIX)+2) + "() == value.get" + indexVarName.substring(indexVarName.indexOf(CSharpCode.VARIABLE_PREFIX)+2) + "())");
        }
    	code.notEqualLines.add("{");
    	code.notEqualLines.add("\treturn false;");
    	code.notEqualLines.add("}");
    }

    /**
     * Generates GetSize method for a type-and-units-enum (TAUE) within a variable field.
     * @param code
     * @param teType
     * @param index
     */
    private void generateTAUEGetSizeMethod(CodeLines code, String teType, int index)
    {
        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            code.sizeLines.add("");
            code.sizeLines.add("if (" + indexVarName + " == " + index + ")");
            code.sizeLines.add("{");
            code.sizeLines.add("\tsize += sizeof(" + teType + ");");
            code.sizeLines.add("}");
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            code.sizeLines.add("");
            code.sizeLines.add("if (" + indexVarName + " == " + index + ")");
            code.sizeLines.add("{");
            JavaCode.generateGetSizeLines(teType, code.sizeLines, teSigned);
            code.sizeLines.add("}");
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            code.sizeLines.add("");
            code.sizeLines.add("if (" + indexVarName + " == " + index + ")");
            code.sizeLines.add("{");
            code.sizeLines.add("\tsize += JausUtils.getNumBytes(\"" + teType + "\");");
            code.sizeLines.add("}");
        }
    }

    /**
     * Generates encode method for a type-and-units-enum (TAUE) within a variable field.
     * @param code
     * @param teType
     * @param teVarQualifiedName
     * @param index
     */
    private void generateTAUEEncodeMethod(CodeLines code, String teType, String teVarQualifiedName, int index)
    {
    	code.encoderLines.add("");
    	code.encoderLines.add("if (" + indexVarName + " == " + index + ")");
    	code.encoderLines.add("{");

        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            code.encoderLines.add("\t" + teType + " tempValue;");
            code.encoderLines.add("");
            code.encoderLines.add("\ttempValue = " + code.getNameSpace() + "::correctEndianness(" + teVarQualifiedName + ");");
            code.encoderLines.add("\tmemcpy(bytes+pos, &tempValue, sizeof(" + teType + "));");
            code.encoderLines.add("\tpos += sizeof(" + teType + ");");
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            code.encoderLines.add("\ttry");
            code.encoderLines.add("\t{");
            JavaCode.generateEncoderLines(teType, teVarQualifiedName, code.encoderLines, teSigned);
            code.encoderLines.add("\t}");
            code.encoderLines.add("\tcatch (Exception e)");
            code.encoderLines.add("{");
            code.encoderLines.add("\t\tlogger.log(Level.SEVERE, null, e);");
            code.encoderLines.add("\t}");
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {           
            code.encoderLines.add("");
            CSharpCode.generateEncoderLines(teType, teVarQualifiedName, code.encoderLines);
        }
    	code.encoderLines.add("}");
    }

    /**
     * Generates decode method for a type-and-units-enum (TAUE) within a variable field.
     * @param code
     * @param teType
     * @param teVarQualifiedName
     * @param index
     */
    private void generateTAUEDecodeMethod(CodeLines code, String teType, String teVarQualifiedName, int index)
    {
    	code.decoderLines.add("");
    	code.decoderLines.add("if (" + indexVarName + " == " + index + ")");
    	code.decoderLines.add("{");
    	code.decoderLines.add("\t" + teType + " tempValue;");
    	code.decoderLines.add("");
        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            code.decoderLines.add("\tmemcpy(&tempValue, bytes+pos, sizeof(" + teType + "));");
            code.decoderLines.add("\t" + teVarQualifiedName + " = " + code.getNameSpace() + "::correctEndianness(tempValue);");
            code.decoderLines.add("\tpos += sizeof(" + teType + ");");
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            code.decoderLines.add("try");
            code.decoderLines.add("{");
            JavaCode.generateDecoderLines(teType, teVarQualifiedName, code.decoderLines, teSigned);
            code.decoderLines.add("}");
            code.decoderLines.add("catch (Exception e)");
            code.decoderLines.add("{");
            code.decoderLines.add("\tlogger.log(Level.SEVERE, null, e);");
            code.decoderLines.add("}");
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            code.decoderLines.add("");
            CSharpCode.generateDecoderLines(teType, teVarQualifiedName, code.decoderLines);
        }
    	code.decoderLines.add("}");
    }

    /**
     * Generates an assignment operator or assignment method for a type-and-units-enum (TAUE) within a variable field.
     * @param code
     * @param teVarQualifiedName
     * @param index
     */
    private void generateTAUEOverloadAssignmentMethod(CodeLines code, String teVarQualifiedName, int index, String teVarType)
    {
    	/// Add code to Overload the operator=
    	code.assignmentLines.add("");
    	code.assignmentLines.add("if (" + indexVarName + " == " + index + ")");
    	code.assignmentLines.add("{");
        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            code.assignmentLines.add("\tthis->" + teVarQualifiedName + " = value." + teVarQualifiedName + ";");
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            code.assignmentLines.add("\tthis." + teVarQualifiedName + " = (" + teVarType + ")value." + teVarQualifiedName + ";");
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            code.assignmentLines.add("\t" + teVarQualifiedName + " = (" + teVarType + ")value.get" + teVarQualifiedName.substring(teVarQualifiedName.indexOf(CSharpCode.VARIABLE_PREFIX)+2) + "();");
        }
    	code.assignmentLines.add("}");
    }

    /**
     * Generates an equality test operator or method method for a type-and-units-enum (TAUE) within a variable field.
     * @param code
     * @param teVarQualifiedName
     * @param index
     */
    private void generateTAUEOverloadIsEqualMethod(CodeLines code, String teVarQualifiedName, int index)
    {
        /// Add code to Overload operator==

        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            code.equalLines.add("");
            code.equalLines.add("if (" + indexVarName + " == " + index + ")");
            code.equalLines.add("{");
            code.equalLines.add("\tif (this->" + teVarQualifiedName + " != value." + teVarQualifiedName + ")");
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            code.equalLines.add("");
            code.equalLines.add("if (" + indexVarName + " == " + index + ")");
            code.equalLines.add("{");
            code.equalLines.add("\tif (this." + teVarQualifiedName + " != value." + teVarQualifiedName + ")");
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            code.equalLines.add("");
            code.equalLines.add("if (this." + indexVarName + " == " + index + ")");
            code.equalLines.add("{");
            code.equalLines.add("\tif (this.get" + teVarQualifiedName.substring(teVarQualifiedName.indexOf(CSharpCode.VARIABLE_PREFIX)+2) + "() != value.get" + teVarQualifiedName.substring(teVarQualifiedName.indexOf(CSharpCode.VARIABLE_PREFIX)+2) + "())");
        }
    	code.equalLines.add("\t{");
    	code.equalLines.add("\t\treturn false;");
    	code.equalLines.add("\t}");
    	code.equalLines.add("}");
    }

    /**
     * Generates an inequality method or operator for a type-and-units-enum (TAUE) within a variable field.
     * @param code
     * @param teVarQualifiedName
     * @param index
     */
    private void generateTAUEOverloadNotEqualMethod(CodeLines code, String teVarQualifiedName, int index)
    {
        /// Add code to Overload operator!=

        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            code.notEqualLines.add("");
            code.notEqualLines.add("if (" + indexVarName + " == " + index + ")");
            code.notEqualLines.add("{");
            code.notEqualLines.add("\tif (this->" + teVarQualifiedName + " == value." + teVarQualifiedName + ")");
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            code.notEqualLines.add("");
            code.notEqualLines.add("if (" + indexVarName + " == " + index + ")");
            code.notEqualLines.add("{");
            code.notEqualLines.add("\tif (this." + teVarQualifiedName + " == value." + teVarQualifiedName + ")");
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            code.notEqualLines.add("");
            code.notEqualLines.add("if (this." + indexVarName + " == " + index + ")");
            code.notEqualLines.add("{");
            code.notEqualLines.add("\tif (this.get" + teVarQualifiedName.substring(teVarQualifiedName.indexOf(CSharpCode.VARIABLE_PREFIX)+2) + "() == value.get" + teVarQualifiedName.substring(teVarQualifiedName.indexOf(CSharpCode.VARIABLE_PREFIX)+2) + "())");
        }
    	code.notEqualLines.add("\t{");
    	code.notEqualLines.add("\t\treturn false;");
    	code.notEqualLines.add("\t}");
    	code.notEqualLines.add("}");
    }

    /**
     * Generates getter method for a type-and-units-enum (TAUE) within a variable field.
     * @param code
     * @param teFieldType
     * @param teName
     * @param teVarQualifiedName
     * @param srGen
     */
    private void generateTAUEGetMethod(CodeLines code, String teFieldType, String teName, String teVarQualifiedName, ScaleRangeGenerator srGen)
    {
    	///  Generate getMethod Declaration and Definition
        methodParam.clear();
        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            code.publicMethods.add(CppCode.createMethodDeclaration("const double", "get", teName, null, true));

            methodCode.clear();
            methodCode.add("double value;");
            methodCode.add("");
            srGen.getIntToDoubleConversion(teFieldType, teVarQualifiedName, "value", methodCode);
            methodCode.add("");
            methodCode.add("return value;");
            code.methods.addAll(CppCode.createMethodDefinition("const double", fullClassName + "::get", teName, methodParam, methodCode, true));
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            methodCode.clear();
            methodCode.add("double value;");
            methodCode.add("");
            srGen.getIntToDoubleConversion(teFieldType, teVarQualifiedName, "value", methodCode);
            methodCode.add("");
            methodCode.add("return value;");
            code.methods.addAll(JavaCode.createMethodDefinition("public double", "get", teName, methodParam, methodCode, true));
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            methodCode.clear();
            methodCode.add("double value;");
            methodCode.add("");
            srGen.getIntToDoubleConversion(teFieldType, teVarQualifiedName, "value", methodCode);
            methodCode.add("");
            methodCode.add("return value;");
            code.methods.addAll(CSharpCode.createMethodDefinition("public double", "get", teName, methodParam, methodCode, true));
        }
    }

    /**
     * Generates getter method for a type-and-units-enum (TAUE) within a variable field.
     * @param code
     * @param teType
     * @param teName
     * @param teVarQualifiedName
     */
    private void generateTAUEGetMethod(CodeLines code, String teType, String teName, String teVarQualifiedName)
    {
    	/* Create the get Method Declaration and Definitions */
    	methodParam.clear();
        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            code.publicMethods.add(CppCode.createMethodDeclaration("const " + teType, "get", teName, methodParam, true));


            // Add the following to the C++ source file
            /* jUnsignedShortInteger SendRec::getDestSubsystemID()
             * {
             * 		return m_DestSubsystemID;
             * };
             */
            methodCode.clear();
            methodCode.add("return " + teVarQualifiedName + ";");
            code.methods.addAll(CppCode.createMethodDefinition("const " + teType, fullClassName + "::get", teName, methodParam, methodCode, true));
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            methodCode.clear();
            methodCode.add("return " + teVarQualifiedName + ";");
            code.methods.addAll(JavaCode.createMethodDefinition("public " +  teType, "get", teName, methodParam, methodCode, true));
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            methodCode.clear();
            methodCode.add("return " + teVarQualifiedName + ";");
            code.methods.addAll(CSharpCode.createMethodDefinition("public " + teType, "get", teName, methodParam, methodCode, true));
        }
    }

    /**
     * Generates a setter method for a type-and-units-enum (TAUE) within a variable field.
     * @param code
     * @param index
     * @param teFieldType
     * @param teName
     * @param teVarQualifiedName
     * @param srGen
     */
    private void generateTAUESetMethod(CodeLines code, int index, String teFieldType, String teName, String teVarQualifiedName, ScaleRangeGenerator srGen)
    {
        ///  Generate setMethod Declaration and Definition
        methodParam.clear();
        methodParam.add("double value");
        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            code.publicMethods.add(CppCode.createMethodDeclaration("int", "set", teName, methodParam, false));

            methodCode.clear();
            methodCode.add(indexVarName + " = " + index + ";");
            srGen.getDoubleToIntConversion(teFieldType, teVarQualifiedName, "value", methodCode);
            // Create framework for parent reference
            methodCode.add(CppCode.getParentReferenceSetParentPVLine());
            methodCode.add("return 0;");
            srGen.addRangeCheck("value", methodCode);
            methodCode.add("return 1;");
            code.methods.addAll(CppCode.createMethodDefinition("int", fullClassName + "::set", teName, methodParam, methodCode, false));
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            methodCode.clear();
            methodCode.add(indexVarName + " = " + index + ";");
            srGen.getDoubleToIntConversion(teFieldType, teVarQualifiedName, "value", methodCode);
            // Create framework for parent reference
            methodCode.add(JavaCode.getParentReferenceSetParentPVLine());
            methodCode.add("return 0;");
            srGen.addRangeCheck("value", methodCode);
            methodCode.add("return 1;");
            code.methods.addAll(JavaCode.createMethodDefinition("public int", "set", teName, methodParam, methodCode, false));
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            methodCode.clear();
            methodCode.add(indexVarName + " = " + index + ";");
            srGen.getDoubleToIntConversion(teFieldType, teVarQualifiedName, "value", methodCode);
            // Create framework for parent reference
            methodCode.add(CSharpCode.getParentReferenceSetParentPVLine());
            methodCode.add("return 0;");
            srGen.addRangeCheck("value", methodCode);
            methodCode.add("return 1;");
            code.methods.addAll(CSharpCode.createMethodDefinition("public int", "set", teName, methodParam, methodCode, false));
        }
    }

    /**
     * Generates a setter method for a type-and-units-enum (TAUE) within a variable field.
     * @param code
     * @param index
     * @param teType
     * @param teName
     * @param teVarQualifiedName
     * @param valueSet
     */
    private void generateTAUESetMethod(CodeLines code, int index, String teType, String teName, String teVarQualifiedName, ValueSet valueSet)
    {
        /* Create the set Method Declaration and Definitions */
        methodParam.clear();
        methodParam.add(teType + " value");
        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            code.publicMethods.add(CppCode.createMethodDeclaration("int", "set", teName, methodParam, false));

            // Add the following to the C++ source file
            /* int SendRec::setDestSubsystemID(jUnsignedShortInteger value)
             * {
             * 		m_DestSubsystemID = value;
             * 		return 0;
             * };
             */
            methodCode.clear();
            methodCode.add(indexVarName + " = " + index + ";");
            methodCode.add(teVarQualifiedName + " = value;");
            // Create framework for parent reference
            methodCode.add(CppCode.getParentReferenceSetParentPVLine());
            methodCode.add("return 0;");

            if (valueSet != null)
            {
        	new ValueSetGenerator(codeType, valueSet).addRangeCheck("value", methodCode);
        	methodCode.add("return 1;");
            }

            code.methods.addAll(CppCode.createMethodDefinition("int", fullClassName + "::set", teName, methodParam, methodCode, false));
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            methodCode.clear();
            methodCode.add(indexVarName + " = " + index + ";");
            methodCode.add(teVarQualifiedName + " = value;");
            // Create framework for parent reference
            methodCode.add(JavaCode.getParentReferenceSetParentPVLine());
            methodCode.add("return 0;");

            if (valueSet != null)
            {
        	new ValueSetGenerator(codeType, valueSet).addRangeCheck("value", methodCode);
        	methodCode.add("return 1;");
            }

            code.methods.addAll(JavaCode.createMethodDefinition("public int", "set", teName, methodParam, methodCode, false));
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            methodCode.clear();
            methodCode.add(indexVarName + " = " + index + ";");
            methodCode.add(teVarQualifiedName + " = value;");
            // Create framework for parent reference
            methodCode.add(CSharpCode.getParentReferenceSetParentPVLine());
            methodCode.add("return 0;");

            if (valueSet != null)
            {
        	new ValueSetGenerator(codeType, valueSet).addRangeCheck("value", methodCode);
        	methodCode.add("return 1;");
            }

            code.methods.addAll(CSharpCode.createMethodDefinition("public int", "set", teName, methodParam, methodCode, false));
        }
    }
    
    /**
     * Generate Instance
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

	    generateInstanceGetSizeMethod(code, pvIndex);
	    generateInstanceIsValidMethod(code, pvIndex);

	    generateInstanceEncodeMethod(code, pvIndex);
	    generateInstanceDecodeMethod(code, pvIndex);

	    generateInstanceGetMethod(code, pvIndex);
	    generateInstanceSetMethod(code, pvIndex);
	}
    
    private void generateInstanceGetSizeMethod(CodeLines code, int pvIndex)
    {
    	methodCode.clear();
    	
	    /// GetSize method
	    /// only add size of array if optional is true 
	    methodCode.add("size += " + varName + ".getSize();");
	    if (variableField.isOptional()) 
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
        if (variableField.isOptional())
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
    
    private void generateInstanceEncodeMethod(CodeLines code, int pvIndex) {
        /* 	m_VaribleLengthField1.encode(bytes + pos);
         *	pos += m_VaribleLengthField1.getSize();
         */
        methodCode.clear();
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            methodCode.add(varName + ".encode(bytes + pos);");
        } else if (codeType == CodeLines.CodeType.JAVA) {
            methodCode.add(varName + ".encode(bytes, pos);");
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            methodCode.add(varName + ".encode(bytes, pos);");
        }

        methodCode.add("pos += " + varName + ".getSize();");
        if (variableField.isOptional()) {
            methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
        }

        /// If the encoder lines are not empty then put a line to increase readability
        if (!code.encoderLines.isEmpty()) {
            code.encoderLines.add("");
        }
        code.encoderLines.addAll(methodCode);
    }
    
    private void generateInstanceDecodeMethod(CodeLines code, int pvIndex) {
        /* 	m_VaribleLengthField1.decode(bytes + pos);
         *	pos += m_VaribleLengthField1.getSize();
         */
        methodCode.clear();
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            methodCode.add(varName + ".decode(bytes + pos);");
        } else if (codeType == CodeLines.CodeType.JAVA) {
            methodCode.add(varName + ".decode(bytes, pos);");
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            methodCode.add(varName + ".decode(bytes, pos);");
        }
        methodCode.add("pos += " + varName + ".getSize();");

        /// If the decoder lines are not empty then put a line to increase readability
        if (!code.decoderLines.isEmpty()) {
            code.decoderLines.add("");
        }
        code.decoderLines.addAll(methodCode);
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

                    code.protectedAttributes.add(JavaCode.createVariableDeclaration("protected long", dimSizeName, false));
                    code.constructorLines.add(dimVarName + " = " + dim.getSize() + ";");

                    ///  Generate getMethod Declaration and Definition
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
                }
                else if(codeType == CodeLines.CodeType.C_SHARP)
                {
                    String dimVarName = CSharpCode.getVariableName(dimSizeName);

                    code.protectedAttributes.add(CSharpCode.createVariableDeclaration("int", dimSizeName, true));
                    code.constructorLines.add(dimVarName + " = " + dim.getSize() + ";");

                    ///  Generate getMethod Declaration and Definition
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
		
            /// create variable
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
                

                code.protectedAttributes.add(JavaCode.createVariableDeclaration("protected " + shortClassName + "[]", shortClassName + " = new " + shortClassName + "[" + arraySize + "]" , false));
                code.constructorLines.add("for(int i= 0; i<" + arraySize + "; i++)");
                code.constructorLines.add("{");
                code.constructorLines.add("\t" + varName + "[i] = new " + shortClassName + "();");
                code.constructorLines.add("}");
                /*
                 * Create framework for parent reference
                 */
                JavaCode.addParentReferenceConstructorSetParrentInArray(code, varName, arraySize);
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                

                code.protectedAttributes.add(CSharpCode.createVariableDeclaration("protected " + shortClassName+ "[]", shortClassName + " = new " + shortClassName + "[" + arraySize + "]" , true));
                code.constructorLines.add("for(int i= 0; i<" + arraySize + "; i++)");
                code.constructorLines.add("{");
                code.constructorLines.add("\t" + varName + "[i] = new " + shortClassName + "();");
                code.constructorLines.add("}");

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
    	
            generateArrayOverloadAssignmentMethod(code, arraySize);
            generateArrayOverloadIsEqualMethod(code, arraySize);
            generateArrayOverloadNotEqualMethod(code, arraySize);
	}
        
    
    private void generateInstanceGetMethod(CodeLines code, int pvIndex)
    {
    	methodCode.clear();
    	methodParam.clear();

        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            ///  Generate getMethod Declaration and Definition
            code.publicMethods.add(CppCode.createMethodDeclaration(shortClassName  + "* const", "get", shortClassName, methodParam, false));

            methodCode.add("return &" + varName + ";");
            code.methods.addAll(CppCode.createMethodDefinition(fullClassName  + "* const", parentClassName + "::get", shortClassName, methodParam, methodCode, false));
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            ///  Generate getMethod Declaration and Definition

            methodCode.add("return " + varName + ";");
            code.methods.addAll(JavaCode.createMethodDefinition("public " + fullClassName, "get", shortClassName, methodParam, methodCode, false));
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            methodCode.add("return " + varName + ";");
            code.methods.addAll(CSharpCode.createMethodDefinition("public " + fullClassName, "get", shortClassName, methodParam, methodCode, false));
        }
    }
    
    private void generateInstanceSetMethod(CodeLines code, int pvIndex)
    {
    	methodCode.clear();
    	methodParam.clear();
    	
        ///  Generate setMethod Declaration and Definition
        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            methodParam.add("const " + shortClassName + " &value");
            code.publicMethods.add(CppCode.createMethodDeclaration("int", "set", shortClassName, methodParam, false));
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            methodParam.add(shortClassName + " value");
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            methodParam.add(shortClassName + " value");
        }

        methodCode.add(varName + " = value;");
        if (variableField.isOptional())
        {
            methodCode.add("setPresenceVector("+ pvIndex +");");
        }
        

        if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            // Create framework for parent reference
            methodCode.add(CppCode.getParentReferenceSetParentPVLine());
            methodCode.add("return 0;");
            code.methods.addAll(CppCode.createMethodDefinition("int", parentClassName + "::set", shortClassName, methodParam, methodCode, false));
        }
        else if(codeType == CodeLines.CodeType.JAVA)
        {
            // Create framework for parent reference
            methodCode.add(JavaCode.getParentReferenceSetParentPVLine());
            methodCode.add("return 0;");
            code.methods.addAll(JavaCode.createMethodDefinition("public int", "set", shortClassName, methodParam, methodCode, false));
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            // Create framework for parent reference
            methodCode.add(CSharpCode.getParentReferenceSetParentPVLine());
            methodCode.add("return 0;");
            code.methods.addAll(CSharpCode.createMethodDefinition("public int", "set", shortClassName, methodParam, methodCode, false));
        }
    }
	
	private void generateArrayGetSizeMethod(CodeLines code, int pvIndex, int arraySize)
	{
            methodCode.clear();
		
	    /// GetSize method
	    /// only add size of array if optional is true
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                methodCode.add("for (unsigned int i = 0; i < "  + arraySize + "; i++)");
                methodCode.add("{");
                methodCode.add("\tsize += " + varName + "[i].getSize();");
                methodCode.add("}");
                if (variableField.isOptional())
                {
                    methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                }
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                methodCode.add("for (int i = 0; i < "  + arraySize + "; i++)");
                methodCode.add("{");
                methodCode.add("\tsize += " + varName + "[i].getSize();");
                methodCode.add("}");
                if (variableField.isOptional())
                {
                    methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                }
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                methodCode.add("for (int i = 0; i < "  + arraySize + "; i++)");
                methodCode.add("{");
                methodCode.add("\tsize += " + varName + "[i].getSize();");
                methodCode.add("}");
                if (variableField.isOptional())
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
	    if (variableField.isOptional()) 
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
                    code.methods.addAll(JavaCode.createMethodDefinition("public boolean", "is", shortClassName + "Valid", null, methodCode, true));
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
	
	
	private void generateArrayEncodeMethod(CodeLines code, int arraySize)
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
	
	
	private void generateArrayDecodeMethod(CodeLines code, int arraySize)
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
	
	
	private void generateArrayGetMethod(CodeLines code, String posCalc, List<String> paramCode, List<String> getMethodCode)
	{
            methodCode.clear();

            ///  Generate getMethod Declaration and Definition
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            code.publicMethods.add(CppCode.createMethodDeclaration(shortClassName + "* const", "get", shortClassName, paramCode, false));

            methodCode.add("unsigned int index = " + posCalc + ";");
            methodCode.add("");
            methodCode.addAll(getMethodCode);
            methodCode.add("return &" + varName + "[index];");
            code.methods.addAll(CppCode.createMethodDefinition(fullClassName + "* const", parentClassName + "::get", shortClassName, paramCode, methodCode, false));
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            methodCode.add("long index = " + posCalc + ";");
            methodCode.add("");
            methodCode.addAll(getMethodCode);
            methodCode.add("return " + varName + "[(int) index];");
            code.methods.addAll(JavaCode.createMethodDefinition("public " + fullClassName, "get", shortClassName, paramCode, methodCode, false));
            // Overload the get method for the purpose of equality testing.
            methodCode.clear();
            List<String> tmpParams = new ArrayList<String>();
            tmpParams.add("int index");
            methodCode.add("return " + varName + "[index];");
            code.methods.addAll(JavaCode.createMethodDefinition("public " + fullClassName, "get", shortClassName, tmpParams, methodCode, false));
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
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
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
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
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            paramCode.add(shortClassName + " value");

            methodCode.add("long index = " + posCalc + ";");
            methodCode.add("");
            methodCode.addAll(setMethodCode);
            methodCode.add(varName + "[(int) index] = value;");
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
		methodCode.add(varName + "[index] = value;");
        // Create framework for parent reference
        methodCode.add(CSharpCode.getParentReferenceSetParentPVLine());

		methodCode.add("return 0;");
		code.methods.addAll(CSharpCode.createMethodDefinition("public int", "set", shortClassName, paramCode, methodCode, false));
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
                code.equalLines.add("\t{");
                code.equalLines.add("\t\treturn false;");
                code.equalLines.add("\t}");
                code.equalLines.add("}");
                code.equalLines.add("return true;");
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                code.equalLines.add("for (int i = 0; i < " + arraySize + "; i++)");
                code.equalLines.add("{");
                code.equalLines.add("\tif (!" + variableName + "[i].isEqual(value.get" + Util.upperCaseFirstLetter(shortClassName) + "(i)))");
                code.equalLines.add("\t{");
                code.equalLines.add("\t\treturn false;");
                code.equalLines.add("\t}");
                code.equalLines.add("}");
                code.equalLines.add("return true;");
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                code.equalLines.add("for (int i = 0; i < " + arraySize + "; i++)");
                code.equalLines.add("{");
                code.equalLines.add("\tif (!this." + variableName + "[i].isEqual(value." + variableName + "[i]))");
                code.equalLines.add("\t{");
                code.equalLines.add("\t\treturn false;");
                code.equalLines.add("\t}");
                code.equalLines.add("}");
                code.equalLines.add("return true;");
            }
	}

	
	
	private void generateArrayOverloadNotEqualMethod(CodeLines code, int arraySize)
	{
            if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
            {
                /// Overload operator!=
                code.notEqualLines.add("for (unsigned int i = 0; i < " + arraySize + "; i++)");
                code.notEqualLines.add("{");
                code.notEqualLines.add("\tif (" + variableName + "[i] == value." + variableName + "[i])");
                code.notEqualLines.add("\t{");
                code.notEqualLines.add("\t\treturn false;");
                code.notEqualLines.add("\t}");
                code.notEqualLines.add("}");
                code.notEqualLines.add("return true;");
            }
            else if(codeType == CodeLines.CodeType.JAVA)
            {
                /// Overload operator!=
                code.notEqualLines.add("for (int i = 0; i < " + arraySize + "; i++)");
                code.notEqualLines.add("{");
                code.notEqualLines.add("\tif (" + variableName + "[i].isEqual(value.get" + Util.upperCaseFirstLetter(shortClassName) + "(i)))");
                code.notEqualLines.add("\t{");
                code.notEqualLines.add("\t\treturn false;");
                code.notEqualLines.add("\t}");
                code.notEqualLines.add("}");
                code.notEqualLines.add("return true;");
            }
            else if(codeType == CodeLines.CodeType.C_SHARP)
            {
                /// Overload operator!=
                code.notEqualLines.add("for (int i = 0; i < " + arraySize + "; i++)");
                code.notEqualLines.add("{");
                code.notEqualLines.add("\tif (this." + variableName + "[i].isEqual(value." + variableName + "[i]))");
                code.notEqualLines.add("\t{");
                code.notEqualLines.add("\t\treturn false;");
                code.notEqualLines.add("\t}");
                code.notEqualLines.add("}");
                code.notEqualLines.add("return true;");
            }
	}
	
	/**
	 * Takes the code provided in the Vector<String> and adds an if statement checking
	 * that varName is a valid index value
	 *  
	 * @param varName
	 * @param code
	 */
    private void addIndexCheck(String varName, List<String> code) {
        StringBuilder sb = new StringBuilder();
        boolean notFirst = false;

        for (TypeAndUnitsEnum taue : variableField.getTypeAndUnitsField().getTypeAndUnitsEnum()) {
            if (notFirst) {
                sb.append(" || ");
            } else {
                notFirst = true;
            }

            sb.append("(");
            sb.append(varName);
            sb.append(" == ");
            sb.append(taue.getIndex());
            sb.append(")");
        }

        Util.indent(1, code);
        code.add(0, "if (" + sb.toString() + ")");
        code.add(1, "{");
        code.add("}");
    }
}

