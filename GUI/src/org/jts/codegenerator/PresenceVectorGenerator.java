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
 * @(#)PresenceVectorGenerator.java		0.1 2008/09/03
 * 
 * Copyright
 */
package org.jts.codegenerator;

import org.jts.jsidl.binding.*;
import java.util.Vector;
// TODO: Generate JUnit and CPPUnit Tests for PresenceVector
/**
 * This class will generate the code required for a presenceVector
 * 
 * @author Nicholas M. Johnson
 *
 */
public class PresenceVectorGenerator
{
	private CodeLines.CodeType codeType;
	private PresenceVector pVector;
        private String className;
	
	/**
	 * Constructor
	 * 
	 * @param codeType
	 * @param pVector
	 */
	public PresenceVectorGenerator(CodeLines.CodeType codeType, PresenceVector pVector)
	{
		this.codeType = codeType;
		this.pVector = pVector;
	}
	
	
	/**
	 * This method fills the provided CodeLines object with the required lines of code.
	 * 
	 * @param isNested	a boolean that states whether the code is part of a class or not  
	 * @param code		the CodeLines object to populate with the generated code
	 */
	public void run(String parentClassName, boolean isNested, CodeLines code)
	{
		if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
		{
            runCPP(parentClassName, isNested, code);
		}
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            runJava(parentClassName, isNested, code);
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
		{
            runCSharp(parentClassName, isNested, code);
		}

	}


	/**
	 * This method fills the provided CodeLines object with the required lines of C++ code.
	 *
	 * @param isNested	a boolean that states whether the code is part of a class or not
	 * @param code		the CodeLines object to populate with the generated code
	 */
    public void runCPP(String parentClassName, boolean isNested, CodeLines code)
    {
        Vector<String> methodCode = new Vector<String>();
        Vector<String> methodParam = new Vector<String>();

        String variableType = CppCode.getVariableType(pVector.getFieldTypeUnsigned());
        String fieldName = Util.upperCaseFirstLetter("presenceVector");
        String variableName = CppCode.getVariableName(fieldName);
        String tempVariableName = variableName + "Temp";

        className = parentClassName;

        code.protectedAttributes.add(CppCode.createVariableDeclaration(variableType, fieldName, false));
        code.constructorLines.add(CppCode.getVariableName(fieldName) + " = 0;");
        code.sizeLines.add("size += sizeof(" + variableType + ");");

        // Add the PresenceVector Encoder Lines
        methodCode.clear();
        methodCode.add(variableType + " " + tempVariableName + ";");
        methodCode.add("");
        methodCode.add(tempVariableName + " = " + code.getNameSpace() + "::correctEndianness(" + variableName + ");");
        methodCode.add("memcpy(bytes + pos, &" + tempVariableName + ", sizeof(" + variableType + "));");
        methodCode.add("pos += sizeof(" + variableType + ");");

        /// If the encoder lines are not empty then put a line to increase readability
        if (!code.encoderLines.isEmpty())
        {
            code.encoderLines.add("");
        }
        code.encoderLines.addAll(methodCode);

        // Add the PresenceVector Decoder Lines
        methodCode.clear();
        methodCode.add(variableType + " " + tempVariableName + ";");
        methodCode.add("");
        methodCode.add("memcpy(&" + tempVariableName + ", bytes + pos, sizeof(" + variableType + "));");
        methodCode.add(variableName + " = " + code.getNameSpace() + "::correctEndianness(" + tempVariableName + ");");
        methodCode.add("pos += sizeof(" + variableType + ");");
        if (!code.decoderLines.isEmpty())
        {
            code.decoderLines.add("");
        }
        code.decoderLines.addAll(methodCode);
        
        // Add the PresenceVector equality lines
        methodCode.clear();
        methodCode.add("if (" + variableName + " != value." + variableName + ")");
        methodCode.add("{");
        methodCode.add("\treturn false;");
        methodCode.add("}");
        if (!code.equalLines.isEmpty())
        {
            code.equalLines.add("");
        }
        code.equalLines.addAll(methodCode);

        /* Create the get Method Declaration and Definitions */
        methodCode.clear();
        methodParam.clear();
        code.publicMethods.add(CppCode.createMethodDeclaration(variableType, "get", fieldName, methodParam, false));

        methodCode.add("return " + variableName + ";");
        if (isNested)
        {
            code.methods.addAll(CppCode.createMethodDefinition(variableType, className + "::get", fieldName, methodParam, methodCode, false));
        }
        else
        {
            code.methods.addAll(CppCode.createMethodDefinition(variableType, "get", fieldName, methodParam, methodCode, false));
        }

        /* Create the set Method Declaration and Definitions */
        methodCode.clear();
        methodParam.clear();
        methodParam.add("unsigned int index");
        code.protectedMethods.add(CppCode.createMethodDeclaration("int", "set", fieldName, methodParam, false));

        // Add the following to the C++ source file
                    /* int setPresenceVector(int index)
         * {
         * 		bitset<sizeof(jUnsignedByte) * 8)> pvBitSet(m_PresenceVector);
         *
         * 		pvBitSet[index] = 1;
         * 		m_PresenceVector = (jUnsignedByte)pvBitSet.to_ulong();
         * 		return 0;
         * }
         */
        methodCode.add("std::bitset<sizeof(" + variableType + ") * 8> pvBitSet((int)(" + variableName + "));");
        methodCode.add("");
        methodCode.add("pvBitSet[index] = 1;");
        methodCode.add(variableName + " = (" + variableType + ")pvBitSet.to_ulong();");
        methodCode.add("return 0;");

        if (isNested)
        {
            code.methods.addAll(CppCode.createMethodDefinition("int", className + "::set", fieldName, methodParam, methodCode, false));
        }
        else
        {
            code.methods.addAll(CppCode.createMethodDefinition("int", "set", fieldName, methodParam, methodCode, false));
        }

        /* Create the check Method Declaration and Definitions */
        methodCode.clear();
        methodParam.clear();
        methodParam.add("unsigned int index");
        code.publicMethods.add(CppCode.createMethodDeclaration("bool", "check", fieldName, methodParam, true));

        // Add the following to the C++ source file
                    /* bool checkPresenceVector(int index)
         * {
         * 		bitset<sizeof(jUnsignedByte) * 8)> pvBitSet(m_PresenceVector);
         *
         * 		return (pvBitSet[index] == 1);
         * }
         */
        methodCode.add("std::bitset<sizeof(" + variableType + ") * 8> pvBitSet((int)(" + variableName + "));");
        methodCode.add("");
        methodCode.add("return (pvBitSet[index] == 1);");

        if (isNested)
        {
            code.methods.addAll(CppCode.createMethodDefinition("bool", className + "::check", fieldName, methodParam, methodCode, true));
        }
        else
        {
            code.methods.addAll(CppCode.createMethodDefinition("bool", "check", fieldName, methodParam, methodCode, false));
        }
    }

    /**
	 * This method fills the provided CodeLines object with the required lines of Java code.
	 *
	 * @param isNested	a boolean that states whether the code is part of a class or not
	 * @param code		the CodeLines object to populate with the generated code
	 */
    public void runJava(String parentClassName, boolean isNested, CodeLines code)
    {
        Vector<String> methodCode = new Vector<String>();
        Vector<String> methodParam = new Vector<String>();

        String variableType = "BitSet";
        String fieldName = Util.upperCaseFirstLetter("presenceVector");
        String variableName = JavaCode.getVariableName(fieldName);
        String tempVariableName = variableName + "Temp";
        String tempVariableType = JavaCode.getVariableType(pVector.getFieldTypeUnsigned());
        boolean tempVarSigned = JavaCode.getVariableSign(pVector.getFieldTypeUnsigned());

        String className = parentClassName;

        code.protectedAttributes.add(JavaCode.createVariableDeclaration("protected " + variableType, fieldName, false));
        code.protectedAttributes.add(JavaCode.createVariableDeclaration("protected " + tempVariableType, fieldName + "Temp", false));
        code.constructorLines.add(variableName + " = new " + variableType + "();");
        code.constructorLines.add(tempVariableName + " = 0;");
        JavaCode.generateGetSizeLines(tempVariableType, code.sizeLines, tempVarSigned);

        // Add the PresenceVector Encoder Lines
        methodCode.clear();
        methodCode.add("try");
        methodCode.add("{");
        methodCode.add("\t" + tempVariableType + " " + tempVariableName + " = (" + tempVariableType + ") JausUtils.getPVInt(" + variableName + ");");
        JavaCode.generateEncoderLines(tempVariableType, tempVariableName, methodCode, tempVarSigned);


        /// If the encoder lines are not empty then put a line to increase readability
        if (!code.encoderLines.isEmpty())
        {
            code.encoderLines.add("");
        }
        methodCode.add("}");
        methodCode.add("catch(Exception e)");
        methodCode.add("{");
        methodCode.add("\tlogger.log(Level.SEVERE, null, e);");
        methodCode.add("}");
        code.encoderLines.addAll(methodCode);

        // Add the PresenceVector Decoder Lines
        methodCode.clear();
        methodCode.add("try");
        methodCode.add("{");
        methodCode.add("\t" + tempVariableType +  " " + tempVariableName + " = 0;");
        JavaCode.generateDecoderLines(tempVariableType, tempVariableName, methodCode, tempVarSigned);
        methodCode.add(variableName + " = JausUtils.setPV(" + tempVariableName + ");");

        if (!code.decoderLines.isEmpty())
        {
            code.decoderLines.add("");
        }
        methodCode.add("}");
        methodCode.add("catch(Exception e)");
        methodCode.add("{");
        methodCode.add("\tlogger.log(Level.SEVERE, null, e);");
        methodCode.add("}");
        code.decoderLines.addAll(methodCode);
        
        // Add the PresenceVector equality lines
        methodCode.clear();
        methodCode.add("if (!" + variableName + ".equals(value." + variableName + "))");
        methodCode.add("{");
        methodCode.add("\treturn false;");
        methodCode.add("}");
        if (!code.equalLines.isEmpty())
        {
            code.equalLines.add("");
        }
        code.equalLines.addAll(methodCode);


        /* Create the get Method Declaration and Definitions */
        methodCode.clear();
        methodParam.clear();

        methodCode.add("return " + tempVariableName + ";");
        code.methods.addAll(JavaCode.createMethodDefinition("public " + tempVariableType, "get", fieldName, methodParam, methodCode, false));


        /* Create the set Method Declaration and Definitions */
        methodCode.clear();
        methodParam.clear();
        methodParam.add("int index");

        // Add the following to the Java source file
                    /* protected int setPresenceVector(int index)
         * {
         * 		m_PresenceVector.set(index);
         * }
         */
        methodCode.add("");
        methodCode.add(variableName + ".set(index);");
        methodCode.add(tempVariableName + " = (" + tempVariableType + ") JausUtils.getPVInt(" + variableName + ");");

        code.methods.addAll(JavaCode.createMethodDefinition("protected void", "set", fieldName, methodParam, methodCode, false));


        /* Create the check Method Definitions */
        methodCode.clear();
        methodParam.clear();
        methodParam.add("int index");

        // Add the following to the java source file
                    /* public boolean checkPresenceVector(int index)
         * {
         *
         * 		return (m_presenceVector.get(index);
         * }
         */
        methodCode.add("");
        methodCode.add("return " + variableName + ".get(index);");

        code.methods.addAll(JavaCode.createMethodDefinition("public boolean", "check", fieldName, methodParam, methodCode, false));

    }

    /**
	 * This method fills the provided CodeLines object with the required lines of C# code.
	 *
	 * @param isNested	a boolean that states whether the code is part of a class or not
	 * @param code		the CodeLines object to populate with the generated code
	 */
    public void runCSharp(String parentClassName, boolean isNested, CodeLines code)
    {
        Vector<String> methodCode = new Vector<String>();
        Vector<String> methodParam = new Vector<String>();

        String variableType = CSharpCode.getVariableType(pVector.getFieldTypeUnsigned());
        String fieldName = Util.upperCaseFirstLetter("presenceVector");
        String variableName = CSharpCode.getVariableName(fieldName);
        String tempVariableName = variableName + "Temp";

        String className = parentClassName;

        code.protectedAttributes.add(CSharpCode.createVariableDeclaration(variableType, fieldName, true));
        code.constructorLines.add(CSharpCode.getVariableName(fieldName) + " = 0;");
        code.sizeLines.add("size += JausUtils.getNumBytes(\"" + variableType + "\");");

        // Add the PresenceVector Encoder Lines
        methodCode.clear();
        CSharpCode.generateEncoderLines(variableType, variableName, methodCode);

        /// If the encoder lines are not empty then put a line to increase readability
        if (!code.encoderLines.isEmpty())
        {
            code.encoderLines.add("");
        }
        code.encoderLines.addAll(methodCode);

        // Add the PresenceVector Decoder Lines
        methodCode.clear();
        CSharpCode.generateDecoderLines(variableType, variableName, methodCode);
        if (!code.decoderLines.isEmpty())
        {
            code.decoderLines.add("");
        }
        code.decoderLines.addAll(methodCode);
        
        // Add the PresenceVector equality lines
        methodCode.clear();
        methodCode.add("if (" + variableName + " != value." + variableName + ")");
        methodCode.add("{");
        methodCode.add("\treturn false;");
        methodCode.add("}");
        if (!code.equalLines.isEmpty())
        {
            code.equalLines.add("");
        }
        code.equalLines.addAll(methodCode);

        /* Create the get Method Declaration and Definitions */
        methodCode.clear();
        methodParam.clear();

        methodCode.add("return " + variableName + ";");
        
        code.methods.addAll(CSharpCode.createMethodDefinition("public " + variableType, "get", fieldName, methodParam, methodCode, false));
        

        /* Create the set Method Declaration and Definitions */
        methodCode.clear();
        methodParam.clear();
        methodParam.add("int index");

        // Add the following to the C++ source file
                    /* int setPresenceVector(int index)
         * {
         * 		bitset<sizeof(jUnsignedByte) * 8)> pvBitSet(m_PresenceVector);
         *
         * 		pvBitSet[index] = 1;
         * 		m_PresenceVector = (jUnsignedByte)pvButSet.to_ulong();
         * 		return 0;
         * }
         */
        methodCode.add("BitArray pvBitSet = JausUtils.setPV((uint) " + variableName + ");");
        methodCode.add("");
        methodCode.add("pvBitSet.Set(index, true);");
        methodCode.add(variableName + " = ("  + variableType + ")JausUtils.getPVint(pvBitSet);");
        methodCode.add("return 0;");

        
        code.methods.addAll(CSharpCode.createMethodDefinition("public int", "set", fieldName, methodParam, methodCode, false));
        

        /* Create the check Method Declaration and Definitions */
        methodCode.clear();
        methodParam.clear();
        methodParam.add("int index");

        // Add the following to the C# source file
                    /* bool checkPresenceVector(int index)
         * {
         * 		bitset<sizeof(jUnsignedByte) * 8)> pvBitSet(m_PresenceVector);
         *
         * 		return (pvBitSet[index] == 1);
         * }
         */
        methodCode.add("BitArray pvBitSet = JausUtils.setPV(" + variableName + ");");
        methodCode.add("");
        methodCode.add("return pvBitSet.Get(index);");

        
        code.methods.addAll(CSharpCode.createMethodDefinition("public bool", "check", fieldName, methodParam, methodCode, false));
        

    }
}
