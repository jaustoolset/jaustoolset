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
 * @(#)CppCode.java		0.1 2008/09/07
 * 
 * Copyright
 */


package org.jts.codegenerator;

import java.io.File;
import java.util.ArrayList;
import java.util.Vector;
import java.util.List;
import org.jts.codegenerator.support.SconstructGenerator;
import org.jts.jsidl.binding.BitRange;
import org.jts.jsidl.binding.ScaleRange;
import org.jts.jsidl.binding.ValueEnum;
import org.jts.jsidl.binding.ValueRange;
import org.jts.jsidl.binding.ValueSet;

/**
 * 
 * This class provides a number of static functions and constants that are used by 
 * the Generator classes (Eg. BitFieldGenerator) when generating C++ code.
 * 
 * @version 0.1 7 September 2008 
 * @author Nicholas M. Johnson
 * @author Gregory Garcia
 *
 */
public class CppCode
{
	/* Public Constants */ 
	public static final int UNSIGNED_BYTE_LENGTH = 8;
	public static final int UNSIGNED_SHORT_INTEGER_LENGTH = 16;
	public static final int UNSIGNED_INTEGER_LENGTH = 32;
    
	public static final long UNSIGNED_BYTE_SIZE  = 256;
	public static final long UNSIGNED_SHORT_INTEGER_SIZE  = 65536;
	public static final long UNSIGNED_INTEGER_SIZE  = 40404040404L;
	
	/* Private Constants */
	private static final String VARIABLE_PREFIX = "m_";
    private static final String VARIABLE_SUFFIX = "";
    
	/**
	 * 
	 * @param jsidlType
	 * @return
	 */
	public static String getVariableType(String jsidlType)
	{
        if (jsidlType.equalsIgnoreCase("unsigned byte"))
        {
            return "jUnsignedByte";
        }
        else if (jsidlType.equalsIgnoreCase("unsigned short integer"))
        {
            return "jUnsignedShortInteger";
        }
        else if (jsidlType.equalsIgnoreCase("unsigned integer")) 
        {
            return "jUnsignedInteger";
        }
        else if (jsidlType.equalsIgnoreCase("unsigned long integer")) 
        {
            return "jUnsignedLongInteger";
        }
        else if (jsidlType.equalsIgnoreCase("byte")) 
        {
            return "jByte";
        }
        else if (jsidlType.equalsIgnoreCase("short integer")) 
        {
            return "jShortInteger";
        }
        else if (jsidlType.equalsIgnoreCase("integer")) 
        {
            return "jInteger";
        }
        else if (jsidlType.equalsIgnoreCase("long integer")) 
        {
            return "jLongInteger";
        }
        else if (jsidlType.equalsIgnoreCase("float")) 
        {
            return "jFloat";
        }
        else if (jsidlType.equalsIgnoreCase("long float")) 
        {
            return "jLongFloat";
        }
        else
        {
        	throw new CodeGeneratorException("JSIDL unknown type: " + jsidlType);
        }
    }

	/**
	 * Accessor function to get variable name with proper prefix and suffix case
	 * @param name
	 * @return string 
	 */
	public static String getVariableName(String name)
	{
		return VARIABLE_PREFIX + Util.upperCaseFirstLetter(name) + VARIABLE_SUFFIX; 
	}
	

	/**
	 * 
	 * @param type
	 * @param name
	 * @param isPointer
	 * @return
	 */
	public static String createVariableDeclaration(String type, String name, boolean isPointer) 
	{
       StringBuffer sb = new StringBuffer();
       sb.append(type).append(" ");
       if (isPointer) 
       {
    	   sb.append("*");
       }
       sb.append(getVariableName(name)).append(";");
       return sb.toString();
	}
	

	public static String createMethodDeclaration(String type, String prefix, String name, List<String> parameters, boolean isConst)
	{
		return createMethodSignature(type, prefix, name, parameters, isConst) + ";";
	}
	
	/**
	 * 
	 * @param type
	 * @param prefix
	 * @param name
	 * @param parameters
	 * @param methodContents
	 * @param isConst
	 * @return
	 */
	public static Vector<String> createMethodDefinition(String type, String prefix, String name, List<String> parameters, List<String> methodContents, boolean isConst)
	{
        Vector<String> codeLines = new Vector<String>();
        codeLines.add(createMethodSignature(type, prefix, name, parameters, isConst));
        codeLines.add("{");
        if (methodContents != null) 
        {
        	Util.indent(1, methodContents);
        	codeLines.addAll(methodContents);
        }
        codeLines.add("}");
        codeLines.add("");
        return codeLines;
    }
	
	
	public static Vector<String> addValidationWrapper(ValueSet valueSet, String inputVariableName, Vector<String> ifBlock, Vector<String> elseBlock)
	{
		StringBuffer sb = new StringBuffer();
        
		for(int i = 0; i < valueSet.getValueRangeOrValueEnum().size(); i++)
        {
            if (i > 0)
        	{
            	sb.append(" || ");
        	}
            
            if (valueSet.getValueRangeOrValueEnum().get(i) instanceof ValueEnum)
            {
                /// value enum
                ValueEnum ve = (ValueEnum) valueSet.getValueRangeOrValueEnum().get(i);
                sb.append("(").append(inputVariableName).append(" == ").append(ve.getEnumIndex()).append(")");
            }
            else
            {
                /// value range
                ValueRange vr = (ValueRange) valueSet.getValueRangeOrValueEnum().get(i);
                sb.append("(").append(inputVariableName);
                
                if (vr.getLowerLimitType().equalsIgnoreCase("exclusive"))
            	{
                	sb.append(" > ");
            	}
                else
            	{
                	sb.append(" >= ");
            	}
                sb.append(vr.getLowerLimit()).append(" && ").append("value");
                
                if (vr.getUpperLimitType().equalsIgnoreCase("exclusive"))
            	{
                	sb.append(" < ");
            	}
                else
            	{
                	sb.append(" <= ");
            	}
                sb.append(vr.getUpperLimit()).append(")");
            }
        }
        
        Vector<String> codes = new Vector<String>();
        
        codes.add("if (" + sb.toString() + ")");
        codes.add("{"); 
        if (ifBlock != null)
        {
            Util.indent(1, ifBlock);
            codes.addAll(ifBlock);
        }
        codes.add("}");
        
        if (elseBlock != null)
        {
            codes.add("else");
            codes.add("{");
            Util.indent(1, elseBlock);
            codes.addAll(elseBlock);
            codes.add("}");
        }
        
        return codes;
    }
	

    public static Vector<String> addValidationWrapper(ScaleRange scaleRange, String inputVariableName, Vector<String> ifBlock, Vector<String> elseBlock)
    {
        String min = scaleRange.getRealLowerLimit();
//        if (min.contains("PI")) min = min.replace("PI","3.14159265358979323846");
//        if (min.contains("e")) min = min.replace("e","2.7182818284590452354");

        String max = scaleRange.getRealUpperLimit();
//        if (max.contains("PI")) max = max.replace("PI","3.14159265358979323846");
//        if (max.contains("e")) max = max.replace("e","2.7182818284590452354");

        Vector<String> codes = new Vector<String>();
        codes.add("if (" + inputVariableName + " >= " + min + " && " + inputVariableName + " <= " + max + ")");
        codes.add("{"); 
        if (ifBlock != null)
        {
            Util.indent(1, ifBlock);
            codes.addAll(ifBlock);
        }
        codes.add("}");
        
        if (elseBlock != null)
        {
            codes.add("else");
            codes.add("{");
            Util.indent(1, elseBlock);
            codes.addAll(elseBlock);
            codes.add("}");
        }

        return codes;
    }

    /**
     * Generates the C++ Header Class from the provided CodeLines object.
     * 
     * @param className
     * @param code
     * @param strVector
     * @deprecated
     */
	public static void getClassWrapper(String className, CodeLines code, Vector<String> strVector)
	{
		Vector<String> publicMethods = new Vector<String>();
		
		/// Add Class Wrapper for the Header File
		strVector.add("class DllExport " +  className);
		strVector.add("{");
		strVector.add("public:");
		publicMethods.add(className + "();");
		publicMethods.add("virtual ~" + className + "();");
		if (!code.publicMethods.isEmpty())
		{
			publicMethods.addAll(code.publicMethods);
		}
		publicMethods.add("virtual void encode(unsigned char *bytes);");
		publicMethods.add("virtual void decode(unsigned char *bytes);");
		Util.indent(1, publicMethods);
		strVector.addAll(publicMethods);
		strVector.add("");
		if (!code.publicAttributes.isEmpty())
		{
			Util.indent(1, code.publicAttributes);
			strVector.addAll(code.publicAttributes);
			strVector.add("");
		}
		if (!code.protectedMethods.isEmpty() || !code.protectedAttributes.isEmpty())
		{
			strVector.add("protected:");
			Util.indent(1, code.protectedMethods);
			strVector.addAll(code.protectedMethods);
			Util.indent(1, code.protectedAttributes);
			strVector.addAll(code.protectedAttributes);
		}
		strVector.add("};");
	}

	
	/**
	 * Takes the provided CodeLines and wraps the header code into a class
	 * and moves that code into the classDefinitions section of the CodeLines. 
	 * Adds the Class Constructor and Destructor and the Encode and Decode
	 * Methods.
	 * The CodeLines object is modified in place.
	 * 
	 * @param className
	 * @param code
	 */
	public static void addClassWrapper(String fullClassName, CodeLines code)
	{
		List<String> classDef = new ArrayList<String>();		// The Class header definition
		List<String> methodCode = new ArrayList<String>();	//
		List<String> methodParam = new ArrayList<String>();

		String shortClassName = getShortClassName(fullClassName);
		

		/* Add additional methods */
		/**************************/
		
		/// Generate the getSize() method
//		if (!code.sizeLines.isEmpty())
//		{
			methodParam.clear();
			code.publicMethods.add(CppCode.createMethodDeclaration("const unsigned int", "getSize", null,  methodParam, false));
			
			methodCode.clear();
			methodCode.add("unsigned int size = 0;");
			methodCode.add("");
			methodCode.addAll(code.sizeLines);
			methodCode.add("");
			methodCode.add("return size;");
			code.methods.add("/**");
			code.methods.add(" * Returns the size of memory the used data members of the class occupies.");
			code.methods.add(" * This is not necessarily the same size of memory the class actually occupies.");
			code.methods.add(" * Eg. A union of an int and a double may occupy 8 bytes. However, if the data");
			code.methods.add(" *     stored is an int, this function will return a size of 4 bytes.");
			code.methods.add(" * ");
			code.methods.add(" * @return");
			code.methods.add(" */");
			code.methods.addAll(CppCode.createMethodDefinition("const unsigned int", fullClassName + "::getSize", null,  methodParam, methodCode, false));
//		}		
		
		/// Add the encode Method
//		if (!code.encoderLines.isEmpty())
//		{
			methodParam.clear();
			methodParam.add("unsigned char *bytes");
			code.publicMethods.add(CppCode.createMethodDeclaration("virtual void", "encode", null,  methodParam, false));
			
			methodCode.clear();
			methodCode.add("");
			methodCode.add("if (bytes == NULL)");
			methodCode.add("{");
			methodCode.add("\treturn;");
			methodCode.add("}");
			methodCode.add("");
			methodCode.add("int pos = 0;");
			methodCode.addAll(code.encoderLines);
			code.methods.addAll(CppCode.createMethodDefinition("void", fullClassName + "::encode", null,  methodParam, methodCode, false));
//		}
		
		/// Add the decode Method
//		if (!code.decoderLines.isEmpty())
//		{
			methodParam.clear();
			methodParam.add("const unsigned char *bytes");
			code.publicMethods.add(CppCode.createMethodDeclaration("virtual void", "decode", null,  methodParam, false));
	
			methodCode.clear();
			methodCode.add("");
			methodCode.add("if (bytes == NULL)");
			methodCode.add("{");
			methodCode.add("\treturn;");
			methodCode.add("}");
			methodCode.add("");
			methodCode.add("int pos = 0;");
			methodCode.addAll(code.decoderLines);
			code.methods.addAll(CppCode.createMethodDefinition("void", fullClassName + "::decode", null,  methodParam, methodCode, false));
//		}
		
		/// Overload operator=
		if (!code.assignmentLines.isEmpty())
		{
	        methodParam.clear();
	        methodParam.add("const " + shortClassName + " &value");
	        code.publicMethods.add(CppCode.createMethodDeclaration(shortClassName, "&operator=", null, methodParam, false));
	        
	        methodCode.clear();
	        methodCode.addAll(code.assignmentLines);
	        methodCode.add("");
	        methodCode.add("return *this;");
	        code.methods.addAll(CppCode.createMethodDefinition(fullClassName, "&" + fullClassName + "::operator=", null, methodParam, methodCode, false));
		}
		
		
		/// Overload operator== and operator!=
		if (!code.equalLines.isEmpty())
		{
	        methodParam.clear();
	        methodParam.add("const " + shortClassName + " &value");
	        code.publicMethods.add(CppCode.createMethodDeclaration("bool", "operator==", null, methodParam, true));			

	        code.publicMethods.add(CppCode.createMethodDeclaration("bool", "operator!=", null, methodParam, true));

	        methodCode.clear();
	        methodCode.addAll(code.equalLines);
	        code.methods.addAll(CppCode.createMethodDefinition("bool", fullClassName + "::operator==", null, methodParam, methodCode, true));


            methodCode.clear();
	        methodCode.add("return !((*this) == value);");
	        code.methods.addAll(CppCode.createMethodDefinition("bool", fullClassName + "::operator!=", null, methodParam, methodCode, true));
        }
		
		/// Generate the Default Constructor Method
		methodParam.clear();
		code.publicMethods.add(CppCode.createMethodDeclaration(null, shortClassName, null,  methodParam, false));
		
		methodCode.clear();
		methodCode.addAll(code.constructorLines);
		code.methods.addAll(CppCode.createMethodDefinition(null, fullClassName + "::" + shortClassName, null,  methodParam, methodCode, false));


		/// Generate the Copy Constructor
		if (!code.assignmentLines.isEmpty())
		{
			methodParam.clear();
			methodParam.add("const " + shortClassName + " &value");
			code.publicMethods.add(CppCode.createMethodDeclaration(null, shortClassName, null,  methodParam, false));
			
			methodCode.clear();
			methodCode.add("/// Initiliaze the protected variables");
	        methodCode.addAll(code.constructorLines);
	        methodCode.add("");
			methodCode.add("/// Copy the values");
	        methodCode.addAll(code.assignmentLines);
			code.methods.addAll(CppCode.createMethodDefinition(null, fullClassName + "::" + shortClassName, null,  methodParam, methodCode, false));
		}
		
		
		/// Generate the Destructor method
		methodParam.clear();
		code.publicMethods.add(CppCode.createMethodDeclaration("virtual", "~" + shortClassName, null,  methodParam, false));
		
		methodCode.clear();
		methodCode.addAll(code.destructorLines);
		code.methods.addAll(CppCode.createMethodDefinition(null, fullClassName + "::~" + shortClassName, null,  methodParam, methodCode, false));


		
		/* Create the Class Wrapper for the Header File */
		/************************************************/
		classDef.add("class DllExport " +  shortClassName);
		classDef.add("{");
		classDef.add("public:");
		if (!code.classDefinitions.isEmpty())
		{
			Util.indent(1, code.classDefinitions);
			classDef.addAll(code.classDefinitions);
			classDef.add("");
		}
		if (!code.publicMethods.isEmpty())
		{
			Util.indent(1, code.publicMethods);
			classDef.addAll(code.publicMethods);
		}
		if (!code.publicAttributes.isEmpty())
		{
			if (!code.publicMethods.isEmpty())
			{
				classDef.add("");
			}
			Util.indent(1, code.publicAttributes);
			classDef.addAll(code.publicAttributes);
			classDef.add("");
		}
		if (!code.protectedMethods.isEmpty() || !code.protectedAttributes.isEmpty())
		{
			classDef.add("");
			classDef.add("protected:");
			Util.indent(1, code.protectedMethods);
			classDef.addAll(code.protectedMethods);
			if (!code.protectedMethods.isEmpty())
			{
				classDef.add("");
			}
			Util.indent(1, code.protectedAttributes);
			classDef.addAll(code.protectedAttributes);
		}
		if (!code.privateMethods.isEmpty() || !code.privateAttributes.isEmpty())
		{
			classDef.add("");
			classDef.add("private:");
			Util.indent(1, code.privateMethods);
			classDef.addAll(code.privateMethods);
			if (!code.privateMethods.isEmpty())
			{
				classDef.add("");
			}
			Util.indent(1, code.privateAttributes);
			classDef.addAll(code.privateAttributes);
		}
		classDef.add("};");
		code.classDefinitions.addAll(classDef);

		
		/* Reorder the provided CodeLines object */
		/*****************************************/
		methodCode.clear();
		methodCode.addAll(code.methods);
		
		code.clear();
		code.classDefinitions.addAll(classDef);
		code.methods.addAll(methodCode);
	}

	
	/**
	 * Takes the provided CodeLines and wraps the header code into a class
	 * and moves that code into the classDefinitions section of the CodeLines. 
	 * Adds the Class Constructor and Destructor and the Encode and Decode
	 * Methods.
	 * The CodeLines object is modified in place.
	 * The baseClassList vector is added to the wrapper class definition 
	 * 
	 * @param className
	 * @param code
	 */
	public static void addClassWrapper(String fullClassName, CodeLines code, List<String> baseClassList)
	{
//		System.out.println("addClassWrapper(" + fullClassName + ", CodeLines code, Vector<String> baseClassList)");
		
		List<String> classDef = new ArrayList<String>();		// The Class header definition
		List<String> methodCode = new ArrayList<String>();	//
		List<String> methodParam = new ArrayList<String>();

		String shortClassName = getShortClassName(fullClassName);
		String baseClass = "";
		

		/* Add additional methods */
		/**************************/
		
		/// Generate the getSize() method
//		if (!code.sizeLines.isEmpty())
//		{
			methodParam.clear();
			code.publicMethods.add(CppCode.createMethodDeclaration("const unsigned int", "getSize", null,  methodParam, false));
			
			methodCode.clear();
			methodCode.add("unsigned int size = 0;");
			methodCode.add("");
			methodCode.addAll(code.sizeLines);
			methodCode.add("");
			methodCode.add("return size;");
			code.methods.add("/**");
			code.methods.add(" * Returns the size of memory the used data members of the class occupies.");
			code.methods.add(" * This is not necessarily the same size of memory the class actually occupies.");
			code.methods.add(" * Eg. A union of an int and a double may occupy 8 bytes. However, if the data");
			code.methods.add(" *     stored is an int, this function will return a size of 4 bytes.");
			code.methods.add(" * ");
			code.methods.add(" * @return");
			code.methods.add(" */");
			code.methods.addAll(CppCode.createMethodDefinition("const unsigned int", fullClassName + "::getSize", null,  methodParam, methodCode, false));
//		}		
		
		/// Add the encode Method
//		if (!code.encoderLines.isEmpty())
//		{
			methodParam.clear();
			methodParam.add("unsigned char *bytes");
			code.publicMethods.add(CppCode.createMethodDeclaration("virtual void", "encode", null,  methodParam, false));
			
			methodCode.clear();
			methodCode.add("");
			methodCode.add("if (bytes == NULL)");
			methodCode.add("{");
			methodCode.add("\treturn;");
			methodCode.add("}");
			methodCode.add("");
			methodCode.add("int pos = 0;");
			methodCode.addAll(code.encoderLines);
			code.methods.addAll(CppCode.createMethodDefinition("void", fullClassName + "::encode", null,  methodParam, methodCode, false));
//		}
		
		/// Add the decode Method
//		if (!code.decoderLines.isEmpty())
//		{
			methodParam.clear();
			methodParam.add("const unsigned char *bytes");
			code.publicMethods.add(CppCode.createMethodDeclaration("virtual void", "decode", null,  methodParam, false));
	
			methodCode.clear();
			methodCode.add("");
			methodCode.add("if (bytes == NULL)");
			methodCode.add("{");
			methodCode.add("\treturn;");
			methodCode.add("}");
			methodCode.add("");
			methodCode.add("int pos = 0;");
			methodCode.addAll(code.decoderLines);
			code.methods.addAll(CppCode.createMethodDefinition("void", fullClassName + "::decode", null,  methodParam, methodCode, false));
//		}
		
		/// Overload operator=
		if (!code.assignmentLines.isEmpty())
		{
	        methodParam.clear();
	        methodParam.add("const " + shortClassName + " &value");
	        code.publicMethods.add(CppCode.createMethodDeclaration(shortClassName, "&operator=", null, methodParam, false));
	        
	        methodCode.clear();
	        methodCode.addAll(code.assignmentLines);
	        methodCode.add("");
	        methodCode.add("return *this;");
	        code.methods.addAll(CppCode.createMethodDefinition(fullClassName, "&" + fullClassName + "::operator=", null, methodParam, methodCode, false));
		}
		
		
		/// Overload operator== and operator!=
		if (!code.equalLines.isEmpty())
		{
	        methodParam.clear();
	        methodParam.add("const " + shortClassName + " &value");
	        code.publicMethods.add(CppCode.createMethodDeclaration("bool", "operator==", null, methodParam, true));			

	        code.publicMethods.add(CppCode.createMethodDeclaration("bool", "operator!=", null, methodParam, true));

	        methodCode.clear();
	        methodCode.addAll(code.equalLines);
	        methodCode.add("");
	        methodCode.add("return true;");
	        code.methods.addAll(CppCode.createMethodDefinition("bool", fullClassName + "::operator==", null, methodParam, methodCode, true));

            methodCode.clear();
	        methodCode.add("return !((*this) == value);");
	        code.methods.addAll(CppCode.createMethodDefinition("bool", fullClassName + "::operator!=", null, methodParam, methodCode, true));
        }
		
		/// Generate the Default Constructor Method
		methodParam.clear();
		code.publicMethods.add(CppCode.createMethodDeclaration(null, shortClassName, null,  methodParam, false));
		
		methodCode.clear();
		methodCode.addAll(code.constructorLines);
		code.methods.addAll(CppCode.createMethodDefinition(null, fullClassName + "::" + shortClassName, null,  methodParam, methodCode, false));


		/// Generate the Copy Constructor
		if (!code.assignmentLines.isEmpty())
		{
			methodParam.clear();
			methodParam.add("const " + shortClassName + " &value");
			code.publicMethods.add(CppCode.createMethodDeclaration(null, shortClassName, null,  methodParam, false));
			
			methodCode.clear();
			methodCode.add("/// Initiliaze the protected variables");
	        methodCode.addAll(code.constructorLines);
	        methodCode.add("");
			methodCode.add("/// Copy the values");
	        methodCode.addAll(code.assignmentLines);
			code.methods.addAll(CppCode.createMethodDefinition(null, fullClassName + "::" + shortClassName, null,  methodParam, methodCode, false));
		}
		
		
		/// Generate the Destructor method
		methodParam.clear();
		code.publicMethods.add(CppCode.createMethodDeclaration("virtual", "~" + shortClassName, null,  methodParam, false));
		
		methodCode.clear();
		methodCode.addAll(code.destructorLines);
		code.methods.addAll(CppCode.createMethodDefinition(null, fullClassName + "::~" + shortClassName, null,  methodParam, methodCode, false));


		
		/* Create the Class Wrapper for the Header File */
		/************************************************/
		
		baseClass = baseClass.concat("class DllExport " +  shortClassName);
		
		if (baseClassList != null)
		{
			/// add all base classes to string
			for(int i = 0; i < baseClassList.size(); i++)
			{
				if(i == 0)
				{
					baseClass = baseClass.concat(": ");
				}
				
				baseClass = baseClass.concat(baseClassList.get(i));
				
				if(i != baseClassList.size()-1)
				{
					baseClass = baseClass.concat(", ");
				}
			}
		}
		
		classDef.add(baseClass);
		classDef.add("{");
		classDef.add("public:");
		if (!code.classDefinitions.isEmpty())
		{
			Util.indent(1, code.classDefinitions);
			classDef.addAll(code.classDefinitions);
			classDef.add("");
		}
		if (!code.publicMethods.isEmpty())
		{
			Util.indent(1, code.publicMethods);
			classDef.addAll(code.publicMethods);
		}
		if (!code.publicAttributes.isEmpty())
		{
			if (!code.publicMethods.isEmpty())
			{
				classDef.add("");
			}
			Util.indent(1, code.publicAttributes);
			classDef.addAll(code.publicAttributes);
			classDef.add("");
		}
		if (!code.protectedMethods.isEmpty() || !code.protectedAttributes.isEmpty())
		{
			classDef.add("");
			classDef.add("protected:");
			Util.indent(1, code.protectedMethods);
			classDef.addAll(code.protectedMethods);
			if (!code.protectedMethods.isEmpty())
			{
				classDef.add("");
			}
			Util.indent(1, code.protectedAttributes);
			classDef.addAll(code.protectedAttributes);
		}
		if (!code.privateMethods.isEmpty() || !code.privateAttributes.isEmpty())
		{
			classDef.add("");
			classDef.add("private:");
			Util.indent(1, code.privateMethods);
			classDef.addAll(code.privateMethods);
			if (!code.privateMethods.isEmpty())
			{
				classDef.add("");
			}
			Util.indent(1, code.privateAttributes);
			classDef.addAll(code.privateAttributes);
		}
		classDef.add("};");
		code.classDefinitions.addAll(classDef);

		
		/* Reorder the provided CodeLines object */
		/*****************************************/
		methodCode.clear();
		methodCode.addAll(code.methods);
		
		code.clear();
		code.classDefinitions.addAll(classDef);
		code.methods.addAll(methodCode);
	}
	

	/**
	 * Adds a check around the supplied code to test if bit pvIndex is set.
	 * 
	 * @param pvIndex
	 * @param code
	 * @return a Vector<String> with the additional lines
	 */
	public static Vector<String> addOptionalWrapper(int pvIndex, List<String> code)
	{
		Vector<String> temp = new Vector<String>();
		
		temp.add("if (checkPresenceVector("+ pvIndex +"))");
		temp.add("{");
		Util.indent(1, code);
		temp.addAll(code);
		temp.add("}");
		
		return temp;
	}
	
	/**
	 * Adds a namespace around the supplied code
	 * 
	 * @param namespace
	 * @param code
	 * @return a Vector<String> with the additional lines
	 */
	public static Vector<String> addNamespaceWrapper(String namespace, Vector<String> code)
	{
		Vector<String> temp = new Vector<String>();
		
		temp.add("namespace " + namespace);
		temp.add("{");
		temp.add("");
		//Util.indent(1, code);
		temp.addAll(code);
		temp.add("");
		temp.add("}");
		
		return temp;
	}
	
	
    public static Vector<String> getIntToDoubleCode(ScaleRange scaleRange, String integerType, String variableName, String convertedVariableName)
    {
        String min = scaleRange.getRealLowerLimit();
        String max = scaleRange.getRealUpperLimit();

//        if (min.contains("PI")) min = min.replace("PI","3.14159265358979323846");
//        if (min.contains("e")) min = min.replace("e","2.7182818284590452354");

//        if (max.contains("PI")) max = max.replace("PI","3.14159265358979323846");
//        if (max.contains("e")) max = max.replace("e","2.7182818284590452354");

        Vector<String> codes = new Vector<String>();

        if (integerType.startsWith("unsigned"))
        {
            double scaleFactor = (Double.valueOf(max) - Double.valueOf(min)) / getIntegerRange(integerType);
            double bias = Double.valueOf(min);

            codes.add("double scaleFactor = "+scaleFactor+";");
	        codes.add("double bias = "+bias+";");
	        codes.add("");
	        codes.add(convertedVariableName + " = " + variableName + " * scaleFactor + bias;");
        }
        else
        {
        	double maxPlusMinDiv2 = (Double.valueOf(max) + Double.valueOf(min)) / 2.0;
        	double maxMinusMin = Double.valueOf(max) - Double.valueOf(min);
        	double integerRange = getIntegerRange(integerType);
        	
	        codes.add(convertedVariableName + " = (" + variableName + " * " + maxMinusMin + ") / (2 * " + integerRange + ") + " + maxPlusMinDiv2 + ";");
        }
        
        return codes;
    }

    public static Vector<String> getDoubleToIntCode(ScaleRange scaleRange, String integerType, String variableName, String convertedVariableName)
    {
        String min = scaleRange.getRealLowerLimit();
//        if (min.contains("PI")) min = min.replace("PI","3.14159265358979323846");
//        if (min.contains("e")) min = min.replace("e","2.7182818284590452354");

        String max = scaleRange.getRealUpperLimit();
//        if (max.contains("PI")) max = max.replace("PI","3.14159265358979323846");
//        if (max.contains("e")) max = max.replace("e","2.7182818284590452354");

        Vector<String> codes = new Vector<String>();

        if (integerType.startsWith("unsigned"))
        {
            double scaleFactor = (Double.valueOf(max) - Double.valueOf(min)) / getIntegerRange(integerType);
            double bias = Double.valueOf(min);
            String integerFunction = scaleRange.getIntegerFunction();
            
            if (integerFunction.equalsIgnoreCase("round"))
        	{
            	integerFunction = "";
        	}
            
	        codes.add("double scaleFactor = " + scaleFactor + ";");
	        codes.add("double bias = " + bias + ";");
	        codes.add("");
	        codes.add(convertedVariableName + " = (" + getVariableType(integerType) + ")" + integerFunction + "((" + variableName + " - bias) / scaleFactor);");
        }
        else
        {
        	double maxPlusMinDiv2 = (Double.valueOf(max) + Double.valueOf(min)) / 2.0;
        	double maxMinusMin = Double.valueOf(max) - Double.valueOf(min);
        	double integerRange = getIntegerRange(integerType);
        	
	        codes.add(convertedVariableName + " = (" + getVariableType(integerType) + ")((" + variableName + " - " + maxPlusMinDiv2 + ") * 2 * (" + integerRange + " / " + maxMinusMin + ");");
        }
        
	    return codes;
    }

    
	private static String createMethodSignature(String type, String prefix, String name, List<String> parameters, boolean isConst)
	{
		StringBuffer sb = new StringBuffer();
		
        if (type != null   )
    	{ 
    		sb.append(type).append(" ");
    	}
        
        if (prefix != null)
    	{
    		sb.append(prefix);
    	}
        
        if (name != null)
    	{
    		sb.append(Util.upperCaseFirstLetter(name));
    	} 
        sb.append("(");
       
        if (parameters != null) 
        {
            for(int i = 0; i < parameters.size(); i++) 
            {
                if (i > 0)
            	{
            		sb.append(", ");
            	}
                sb.append(parameters.get(i));
            }
        }         
        sb.append(")");
        
        if (isConst)
        {
        	sb.append(" const");
        }
        
        return sb.toString();
    }
	
	
	public static double getIntegerRange(String jsidlType) throws RuntimeException
	{
        if (jsidlType.equalsIgnoreCase("byte"))
        {
            return 127;
        }
        else if (jsidlType.equalsIgnoreCase("unsigned byte"))
        {
            return 255;
        }
        else if (jsidlType.equalsIgnoreCase("short integer"))
        {
            return 32767;
        }
        else if (jsidlType.equalsIgnoreCase("unsigned short integer"))
        {
            return 65535;
        }
        else if (jsidlType.equalsIgnoreCase("integer"))
        {
            return 2147483647L;
        }
        else if (jsidlType.equalsIgnoreCase("unsigned integer"))
        {
            return 4294967295L;
        }
        else if (jsidlType.equalsIgnoreCase("long integer"))
        {
            return 9.22337203685e18;
        }
        else if (jsidlType.equalsIgnoreCase("unsigned long integer"))
        {
            return 1.84467440737e19;
        }
        else
        {
        	throw new RuntimeException("!!! Unknown JSIDL type : " + jsidlType);
        }
    }
	
	
	public static String getMinValueString(BitRange range) throws CodeGeneratorException
	{
		return "";
	}
	
	
	public static String getMinValueString(String jsidlType) throws CodeGeneratorException
	{
		if (jsidlType.startsWith("unsigned"))
		{
			return "0";
		}
		if (jsidlType.equalsIgnoreCase("byte"))
        {
            return Byte.toString(Byte.MIN_VALUE);
        }
        else if (jsidlType.equalsIgnoreCase("short integer"))
        {
            return Short.toString(Short.MIN_VALUE);
        }
        else if (jsidlType.equalsIgnoreCase("integer"))
        {
            return Integer.toString(Integer.MIN_VALUE);
        }
        else if (jsidlType.equalsIgnoreCase("long integer"))
        {
            return Long.toString(Long.MIN_VALUE);
        }
        else if (jsidlType.equalsIgnoreCase("float")) 
        {
            return Float.toString(Float.MIN_NORMAL);
        }
        else if (jsidlType.equalsIgnoreCase("long float")) 
        {
            return Double.toString(Double.MIN_NORMAL);
        }
        else
        {
        	throw new CodeGeneratorException("Invalid JSIDL Type Specified: " + jsidlType);
        }
	}
	
	public static String getMaxValueString(String jsidlType) throws CodeGeneratorException
	{
		if (jsidlType.equalsIgnoreCase("byte"))
		{
		    return Byte.toString(Byte.MAX_VALUE);
		}
		else if (jsidlType.equalsIgnoreCase("unsigned byte"))
		{
			return Integer.toString((int)Byte.MAX_VALUE * 2 + 1);
		}
		else if (jsidlType.equalsIgnoreCase("short integer"))
		{
		    return Short.toString(Short.MAX_VALUE);
		}
		else if (jsidlType.equalsIgnoreCase("unsigned short integer"))
		{
			return Integer.toString((int)Short.MAX_VALUE * 2 + 1);
		}
		else if (jsidlType.equalsIgnoreCase("integer"))
		{
		    return Integer.toString(Integer.MAX_VALUE);
		}
		else if (jsidlType.equalsIgnoreCase("unsigned integer"))
		{
		    return Long.toString((long)Integer.MAX_VALUE * 2 + 1);
		}
		else if (jsidlType.equalsIgnoreCase("long integer"))
		{
		    return Long.toString(Long.MAX_VALUE);
		}
		else if (jsidlType.equalsIgnoreCase("unsigned long integer"))
		{
		    return "1.84467440737e19";
		}
        else if (jsidlType.equalsIgnoreCase("float")) 
        {
            return Float.toString(Float.MAX_VALUE);
        }
        else if (jsidlType.equalsIgnoreCase("long float")) 
        {
            return Double.toString(Double.MAX_VALUE);
        }
		else
		{
        	throw new CodeGeneratorException("Invalid JSIDL Type Specified: " + jsidlType);
		}
	}
	
	public static String getMidValueString(String jsidlType) throws CodeGeneratorException
	{
		if (jsidlType.startsWith("unsigned"))
		{
			String temp = jsidlType.replace("unsigned ", "");
			
			return getMaxValueString(temp);
			
		}
		else
		{
			return "0"; 
		}
	}
	
	/**
	 * 
	 * @param targetDir
	 * @param code
	 * @param includes
	 */
	public static void createCppCode(String fullClassName, String incDir, String srcDir, CodeLines code, List<String> includes)
	{
		Vector<String> output = new Vector<String>();
//		String className = code.getClassName();
		
		String shortClassName = getShortClassName(fullClassName);
		
		// Write the .h file
		output.add("#ifndef " + shortClassName.toUpperCase() + "_H");
		output.add("#define " + shortClassName.toUpperCase() + "_H");
		output.add("");
		output.add("#include \"JausUtils.h\"");
		for (String line: includes)
		{
			output.add("#include \"" + line + "\"");
		}

		/// Add Class Wrapper for the Header File
		CppCode.addClassWrapper(fullClassName, code);
		
		output.addAll(code.classDefinitions);
		output.add("");
		output.add("#endif // " + shortClassName.toUpperCase() + "_H");
		
		new File(incDir).mkdir();
		new CodeWriter().write(output, incDir, shortClassName + ".h");
		
		output.clear();
		
		// Write the .cpp file of there are methods
		if (!code.methods.isEmpty())
		{
			output.add("#include \"" + shortClassName + ".h\"");
			output.add("");
			output.addAll(code.methods);
			
			new File(srcDir).mkdir();
			new CodeWriter().write(output, srcDir, shortClassName + ".cpp");	
		}		
	}
	
	/**
	 * Overidden function for generating cppCode with a namespace
	 * @param targetDir
	 * @param code
	 * @param includes
	 */
	public static void createCppCode(String fullClassName, String incDir, String srcDir, CodeLines code, List<String> includes, String namespace, List<String> baseClassList)
	{
		Vector<String> output = new Vector<String>();
		
		String shortClassName = getShortClassName(fullClassName);
		
		// Write the .h file
		output.add("#ifndef " + namespace.toUpperCase() + "_" + shortClassName.toUpperCase() + "_H");
		output.add("#define " + namespace.toUpperCase() + "_" + shortClassName.toUpperCase() + "_H");
		output.add("");
		output.add("#include \"JausUtils.h\"");
		for (String line: includes)
		{
			output.add("#include \"" + line + "\"");
		}
		
		/// Add Class Wrapper for the Header File 
		CppCode.addClassWrapper(fullClassName, code, baseClassList);
		
		/// Add the namespace wrapper
		output.addAll(CppCode.addNamespaceWrapper(namespace, code.classDefinitions));
		
		output.add("");
		output.add("#endif // " + namespace.toUpperCase() + "_" + shortClassName.toUpperCase() + "_H");
		
		new File(incDir).mkdir();
		new CodeWriter().write(output, incDir, shortClassName + ".h");
		
		output.clear();
		// Write the .cpp file
		output.add("#include \"" + incDir.substring(incDir.indexOf(namespace)) + "/" + shortClassName + ".h\"");
		output.add("");
		output.addAll(CppCode.addNamespaceWrapper(namespace, code.methods));
		
		new File(srcDir).mkdir();
		new CodeWriter().write(output, srcDir, shortClassName + ".cpp");
	}
	
	/**
	 * Generates the CPPUnit Code for the code provided.
	 * The .h and .cpp files will be placed in include/ and src/ directories 
	 * within the targetDir.
	 * 
	 * @param shortClassName		the short name of the class this test is being generated for
	 * @param targetDir
	 * @param code
	 */
	public static void createCppUnitTest(String shortClassName, String incDir, String srcDir, CodeLines code)
	{
		List<String> output = new ArrayList<String>();
		String testClassName = shortClassName + "Test";
		

		
		
		// Write the .h file
		output.add("#ifndef " + testClassName.toUpperCase() + "_H");
		output.add("#define " + testClassName.toUpperCase() + "_H");
		output.add("");
		output.add("#include <cppunit/extensions/HelperMacros.h>");
		output.add("#include \"" + shortClassName + ".h\"");
		output.add("");
		
		/// Add Class Wrapper for the Header File
		output.add("class " +  testClassName + " : public CppUnit::TestFixture");
		output.add("{");
		output.add("\tCPPUNIT_TEST_SUITE(" + testClassName + ");");
		if (!code.classDefinitions.isEmpty())
		{
			Util.indent(1, code.classDefinitions);
			output.addAll(code.classDefinitions);
		}
		output.add("\tCPPUNIT_TEST_SUITE_END();");
		output.add("");
		output.add("public:");
		output.add("\tvoid setUp();");
		output.add("\tvoid tearDown();");
		if (!code.publicMethods.isEmpty())
		{
			Util.indent(1, code.publicMethods);
			output.addAll(code.publicMethods);
		}
		output.add("");
		if (!code.publicAttributes.isEmpty())
		{
			Util.indent(1, code.publicAttributes);
			output.addAll(code.publicAttributes);
			output.add("");
		}
		if (!code.protectedMethods.isEmpty() || !code.protectedAttributes.isEmpty())
		{
			output.add("protected:");
			Util.indent(1, code.protectedMethods);
			output.addAll(code.protectedMethods);
			Util.indent(1, code.protectedAttributes);
			output.addAll(code.protectedAttributes);
		}
		output.add("};");
		
		output.add("");
		output.add("#endif // " + shortClassName.toUpperCase() + "_H");

		new File(incDir).mkdir();
		new CodeWriter().write(output, incDir, testClassName + ".h");

		output.clear();

		// Write the .cpp file
		output.add("#include \"" + testClassName + ".h\"");
		output.add("");
		output.add("CPPUNIT_TEST_SUITE_REGISTRATION(" + testClassName + ");");
		output.add("");
		output.add("/**");
		output.add(" * Allocate memory for test variables");
		output.add(" */");
		output.addAll(CppCode.createMethodDefinition("void", testClassName, "::setUp", null, code.constructorLines, false));
		output.add("/**");
		output.add(" * Free memory used for test variables");
		output.add(" */");
		output.addAll(CppCode.createMethodDefinition("void", testClassName, "::tearDown", null, code.destructorLines, false));
		output.addAll(code.methods);
		output.add("");
		
		new File(srcDir).mkdir();
		new CodeWriter().write(output, srcDir, testClassName + ".cpp");
	}
	
	
	static public void copyTemplateFiles(String outDir, boolean isTest) throws Exception
	{
//		try
//		{
//			Util.copyFile(new File("templates/JausUtils.h"), new File(outDir + "/include/JausUtils.h"));
//			Util.copyFile(new File("templates/jFixedLengthString.h"), new File(outDir + "/include/jFixedLengthString.h"));
//			Util.copyFile(new File("templates/jFixedLengthString.cpp"), new File(outDir + "/src/jFixedLengthString.cpp"));
//			
//			if (isTest)
//			{
//				Util.copyFile(new File("templates/testRunner.cpp"), new File(outDir + "/src/testRunner.cpp"));
//			}
//		}
//		catch (Exception e)
//		{
//			throw new Exception(e);
//		}
		
		SconstructGenerator sconGen = new SconstructGenerator();
		
		/*
		 * Copy the templates/Common Directory and generate Common Sconstruct
		 */
		try
		{
            if(new File("plugins/org.jts.eclipse.data_1.0/templates").exists())
            {
                Util.copyDirectory(new File("plugins/org.jts.eclipse.data_1.0/templates/Common"), new File(outDir));
            }
            else
            {
                Util.copyDirectory(new File("templates/Common"), new File(outDir));
            }
			Util.writeContents(new File(outDir + "/Common/Sconstruct"), sconGen.generateLibrary(new File(outDir + "/Common"), "Common", new Vector<String>()));
		}
		catch (Exception e)
		{
			System.out.println("ComponentGenerator [156]: " + e.toString());
		}
	}
	
	
    /**
     * Generates the namespace name from the given ID and version info.
     * @param id
     * @param version
     * @return
     */
     static public String makeNamespace(String id, String version)
    {
        char[] ns = (id+"_"+version).toCharArray();
        for(int i=0; i<ns.length; i++)
        {
            if (!((ns[i]>='0' && ns[i]<='9')
                ||(ns[i]>='A' && ns[i]<='Z')
                ||(ns[i]>='a' && ns[i]<='z')))
            {
                ns[i]='_';
            }
        }
        return new String(ns);
	}

     
     static public String getShortClassName(String fullClassName)
     {
    	 StringBuffer fullNameBuffer = new StringBuffer(fullClassName);
    	 StringBuffer shortNameBuffer = new StringBuffer();
    	 char currentChar;
    	 
    	 for (int i = fullNameBuffer.length() - 1; i > -1; i--)
    	 {
    		 currentChar = fullNameBuffer.charAt(i);
    		 if (currentChar != ':')
    		 {
    			 shortNameBuffer.append(currentChar);
    		 }
    		 else
    		 {
    			 break;
    		 }
    	 }

    	 shortNameBuffer.reverse();
    	 
    	 return shortNameBuffer.toString();
     }
     
     
     static public String getParentClassName(String fullClassName)
     {
    	 String shortClassName = getShortClassName(fullClassName);

    	 return fullClassName.substring(0, fullClassName.length() - (shortClassName.length() + 2));
     }
     
     static public void addParentReference(CodeLines code, String fullClassName, int parentPV, boolean isOptional)
     {
    	 String parentClassName = getParentClassName(fullClassName);
    	 parentClassName = getShortClassName(parentClassName);
    	 
    	 addReferenceDefinition(code, parentClassName);
    	 
    	 addReferenceConstuction(code, parentClassName);
    	 
    	 addReferenceSetParent(code, fullClassName, parentClassName);
    	 
    	 addReferenceSetParentPresenceVector(code, fullClassName, parentPV, isOptional);
     }

     /**
      * Adds the setParent method which will be called in the parent constructor so
      * that the child element can have a reference to it's parent
      * This will only be added to a constructor when the child element(varName) is
      * a class
      * @param code
      * @param varName
      */
     static public void addParentReferenceConstructorSetParent(CodeLines code, String varName)
     {
    	 // add the setParent method to the constructor
    	 // m_rec.setParent(this);
    	 code.constructorLines.add(getParentReferenceVariableSetParent(varName));
     }
     
     static public String getParentReferenceVariableSetParent(String varName)
     {
    	 return varName + ".setParent(this);";
     }
     
     static public String getParentReferenceVariableSetParentInVector(String varName)
     {
    	 return varName + "[i].setParent(this);";
     }

     /**
      * Adds the setParent method which will be called in the parent constructor so
      * the child element can have a reference to it's parent
      * This will only be added to a constructor when the child element(varName) is
      * a class
      * @param code
      * @param varName
      * @param arraySize
      */
     static public void addParentReferenceConstructorSetParrentInArray(CodeLines code, String varName, int arraySize)
     {
    	 // add the setParent method to the constructor for each of the elements
    	 // for( int i = 0; i < .... )
    	 // 	m_rec[ i ].setParent(this)
    	 code.constructorLines.add("for (unsigned int i = 0; i < "  + arraySize + "; i++)");
    	 code.constructorLines.add("{");
    	 code.constructorLines.add("\t" + varName + "[i].setParent(this);");
    	 code.constructorLines.add("}");
     }
     
     static public void addParentReferenceConstructorSetParrentInArray(CodeLines code, String varName)
     {
    	 // add the setParent method to the constructor for each of the elements
    	 // for( int i = 0; i < .... )
    	 // 	m_rec[ i ].setParent(this)
    	 code.constructorLines.add("for (unsigned int i = 0; i < " + varName + ".size(); i++)");
    	 code.constructorLines.add("{");
    	 code.constructorLines.add("\t" + varName + "[i].setParent(this);");
    	 code.constructorLines.add("}");
     }

     /**
      * Adds a pointer for the parent of the element to the class header
      * @param code
      * @param parentClassName
      */
     static private void addReferenceDefinition(CodeLines code, String parentClassName)
     {
    	 // add parent reference to class
    	 // seq* m_parent;
    	 code.protectedAttributes.add(parentClassName + "* m_parent;");
     }

     /**
      * Adds the parent pointer reference to the constructor so that it
      * is initialized to NULL.  This way, the class can check to see if
      * it has a valid parent or if the class was instantiated without a parent
      * @param code
      * @param parentClassName
      */
     static private void addReferenceConstuction(CodeLines code, String parentClassName)
     {
    	 // set m_parent reference to null by default
    	 // seq* m_parent = NULL;
    	 code.constructorLines.add("m_parent = NULL;");
     }

     /**
      * Adds a function that can be called to set the current element's reference
      * to its parent pointer.  The setParent method will be called from the parent's
      * constructors when the child element is a class
      * @param code
      * @param fullClassName
      * @param parentClassName
      */
     static private void addReferenceSetParent(CodeLines code, String fullClassName, String parentClassName)
     {
    	 List<String> methodCode = new ArrayList<String>();
    	 List<String> methodParam = new ArrayList<String>();

    	 // a method that will be used to set the parent instance of the current class
    	 // void setParent(seq* parent);
    	 methodParam.clear();
    	 methodParam.add(parentClassName + "* parent");
    	 code.publicMethods.add(CppCode.createMethodDeclaration("void", "setParent", null,  methodParam, false));

    	 // void Message::seq::rec::setParent(seq* parent)
    	 // {
    	 // 	m_parent = parent;
    	 // }
    	 methodCode.clear();
    	 methodCode.add("m_parent = parent;");
    	 code.methods.addAll(CppCode.createMethodDefinition("void", fullClassName + "::setParent", null,  methodParam, methodCode, false));
     }

     /**
      * Adds the setParentPresenceVector method which will first check
      * to see if the parent was set and then calls the setParentPresenceVector
      * method for the parent to set the current elements bit in it's parent's
      * presence vector
      * @param code
      * @param fullClassName
      * @param pvIndex
      * @param isOptional
      */
     static private void addReferenceSetParentPresenceVector(CodeLines code, String fullClassName, int pvIndex, boolean isOptional)
     {
    	 List<String> methodCode = new ArrayList<String>();

    	 // a convenience function to set the parent's presence vector for this class instance when needed
    	 // the method will be called by any sub elements the current class or when the current class set methods are called
    	 // void setParentPresenceVector();
    	 code.publicMethods.add(CppCode.createMethodDeclaration("void", "setParentPresenceVector", null,  null, false));

    	 // void Message::seq::rec::setParentPresenceVector()
    	 // {
    	 // 	if(m_parent != NULL )
    	 // 	{
    	 // 		m_parent->setPresenceVector(0);
    	 // 		m_parent->setParentPresenceVector();
    	 // 	}
    	 // }
    	 methodCode.clear();
    	 methodCode.add("if(m_parent != NULL )");
    	 methodCode.add("{");

    	 	// only set the presence vector if the current class was optional in it's parent class
    	 	if( isOptional )
	 		{
    	 		methodCode.add("\tm_parent->setPresenceVector(" + Integer.toString(pvIndex) + ");");
	 		}

    	 	// always call the recursive set parent pv method so all pv to root element will be set
    	 	methodCode.add("\tm_parent->setParentPresenceVector();");

    	 methodCode.add("}");
    	 code.methods.addAll(CppCode.createMethodDefinition("void", fullClassName + "::setParentPresenceVector", null,  null, methodCode, false));    	     	 
     }

     /**
      * Adds the setParentPresenceVector definition to the class
      * This will be used in header, body and footer generation because
      * these classes will be root classes and do not need to call
      * parent setParentPresenceVector methods
      * @param code
      * @param fullClassName
      */
     static public void addReferenceSetParentPresenceVector(CodeLines code, String fullClassName)
     {
    	 List<String> methodCode = new ArrayList<String>();

    	 // a convenience function to set the parent's presence vector for this class instance when needed
    	 // the method will be called by any sub elements the current class or when the current class set methods are called
    	 // void setParentPresenceVector();
    	 code.publicMethods.add(CppCode.createMethodDeclaration("void", "setParentPresenceVector", null,  null, false));

    	 // void Message::seq::rec::setParentPresenceVector()
    	 // {
    	 // 	// Nothing needed here, placeholder function
    	 // }
    	 methodCode.clear();
    	 methodCode.add("// Nothing needed here, placeholder function");
    	 code.methods.addAll(CppCode.createMethodDefinition("void", fullClassName + "::setParentPresenceVector", null,  null, methodCode, false));  
     }

     /**
      * Get the string that should be added to any set methods that are called
      * @return
      */
     static public String getParentReferenceSetParentPVLine()
     {
    	 return "setParentPresenceVector();";
     }
}


