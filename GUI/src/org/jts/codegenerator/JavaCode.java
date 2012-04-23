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
 * @(#)JavaCode.java		2011/04/01
 *
 * Copyright
 */


package org.jts.codegenerator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.jts.codegenerator.support.SconstructGenerator;
import org.jts.jsidl.binding.BitRange;
import org.jts.jsidl.binding.ScaleRange;
import org.jts.jsidl.binding.ValueEnum;
import org.jts.jsidl.binding.ValueRange;
import org.jts.jsidl.binding.ValueSet;

/**
 *
 * This class provides a number of static functions and constants that are used by
 * the Generator classes (Eg. BitFieldGenerator) when generating Java code.
 *
 * @version 1 April 2011
 * @author Gina Nearing
 *
 */
public class JavaCode
{
    /*Constants */
    public static final String VARIABLE_PREFIX = "m_";
    public static final String VARIABLE_SUFFIX = "";

    /**
     *
     * @param jsidlType
     * @return string the java type that will represent the jsidlType
     */
    public static String getVariableType(String jsidlType)
    {
        if (jsidlType.equalsIgnoreCase("unsigned byte"))
        {
            return "short";
        }
        else if (jsidlType.equalsIgnoreCase("unsigned short integer"))
        {
            return "int";
        }
        else if (jsidlType.equalsIgnoreCase("unsigned integer"))
        {
            return "long";
        }
        else if (jsidlType.equalsIgnoreCase("unsigned long integer"))
        {
            return "long";
        }
        else if (jsidlType.equalsIgnoreCase("byte"))
        {
            return "byte";
        }
        else if (jsidlType.equalsIgnoreCase("short integer"))
        {
            return "short";
        }
        else if (jsidlType.equalsIgnoreCase("integer"))
        {
            return "int";
        }
        else if (jsidlType.equalsIgnoreCase("long integer"))
        {
            return "long";
        }
        else if (jsidlType.equalsIgnoreCase("float"))
        {
            return "float";
        }
        else if (jsidlType.equalsIgnoreCase("long float"))
        {
            return "double";
        }
        else
        {
            throw new CodeGeneratorException("JSIDL unknown type: " + jsidlType);
        }
    }

    /**
     *
     * @param jsidlType
     * @return string the java type that will represent the jsidlType
     */
    public static boolean getVariableSign(String jsidlType)
    {
        //if(jsidlType.contentEquals("unsigned"))
            if(jsidlType.contains("unsigned"))
        {
            return true;
        }
        else
        {
            return false;
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
           sb.append(getVariableName(name)).append(";");
           return sb.toString();
	}


        /*
         * @param type
         * @param prefix
         * @param name
         * @param parameters
         * @param isConst
         */
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
	public static List<String> createMethodDefinition(String type, String prefix, String name, List<String> parameters, List<String> methodContents, boolean isConst)
	{
            List<String> codeLines = new ArrayList<String>();
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


        /*
         * @param valueSet
         * @param inputVariableName
         * @param ifBlock
         * @param elseBlock
         */
    public static List<String> addValidationWrapper(ValueSet valueSet, String inputVariableName, List<String> ifBlock, List<String> elseBlock)
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

        List<String> codes = new ArrayList<String>();

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


    /*
     * @praram scaleRange
     * @param inputVariableName
     * @param ifBlock
     * @param elseBlock
     */
    public static List<String> addValidationWrapper(ScaleRange scaleRange, String inputVariableName, List<String> ifBlock, List<String> elseBlock)
    {
        String min = scaleRange.getRealLowerLimit();

        String max = scaleRange.getRealUpperLimit();

        List<String> codes = new ArrayList<String>();
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
     * Takes the provided CodeLines and wraps the code into a class
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
        List<String> classDef = new ArrayList<String>();
        List<String> methodCode = new ArrayList<String>();
        List<String> methodParam = new ArrayList<String>();

        String shortClassName = getShortClassName(fullClassName);


        /* Add additional methods */
        /**************************/

        methodParam.clear();

        // Add the getSize method
        methodCode.clear();
        methodCode.add("long size = 0;");
        methodCode.add("");
        methodCode.addAll(code.sizeLines);
        methodCode.add("");
        methodCode.add("return size;");
        code.methods.add("/**");
        code.methods.add(" * Returns the number of bytes the used data members of the class occupies");
        code.methods.add(" * in the buffer. This is not the number of bytes the data type occupies in ");
        code.methods.add(" * Java, but the bytes expected on the wire.");
        code.methods.add(" * ");
        code.methods.add(" * @return");
        code.methods.add(" */");
        code.methods.addAll(JavaCode.createMethodDefinition("public long", "getSize", null,  methodParam, methodCode, false));

	// Add the encode Method

        methodParam.clear();
        methodParam.add("ByteBuffer bytes");
        methodParam.add("int pos");
        
        methodCode.clear();
        methodCode.add("");
        methodCode.add("if (bytes.array() == null)");
        methodCode.add("{");
        methodCode.add("\treturn;");
        methodCode.add("}");
        methodCode.add("");
        methodCode.add("if(bytes.order() != ByteOrder.LITTLE_ENDIAN)");
        methodCode.add("{");
        methodCode.add("\tbytes.order(ByteOrder.LITTLE_ENDIAN);");
        methodCode.add("}");
        methodCode.add("");
        methodCode.addAll(code.encoderLines);
        code.methods.addAll(JavaCode.createMethodDefinition("public void", "encode", null,  methodParam, methodCode, false));

	// Add the decode Method
        methodParam.clear();
        methodParam.add("ByteBuffer bytes");
        methodParam.add("int pos");

        methodCode.clear();
        methodCode.add("");
        methodCode.add("if (bytes.array() == null)");
        methodCode.add("{");
        methodCode.add("\treturn;");
        methodCode.add("}");
        methodCode.add("if(bytes.order() != ByteOrder.LITTLE_ENDIAN)");
        methodCode.add("{");
        methodCode.add("\tbytes.order(ByteOrder.LITTLE_ENDIAN);");
        methodCode.add("}");
        methodCode.add("");
        methodCode.addAll(code.decoderLines);
        code.methods.addAll(JavaCode.createMethodDefinition("public void", "decode", null,  methodParam, methodCode, false));

        // Add assignment method
        if (!code.assignmentLines.isEmpty())
        {
            methodParam.clear();
            methodParam.add(shortClassName + " value");

            methodCode.clear();
            methodCode.addAll(code.assignmentLines);
            methodCode.add("");
            methodCode.add("return this;");
            code.methods.addAll(JavaCode.createMethodDefinition("public " + fullClassName, "assign", null, methodParam, methodCode, false));
        }

        // Add isEqual method and a matching notEquals method.
        if (!code.equalLines.isEmpty())
        {
            methodParam.clear();
            methodParam.add(shortClassName + " value");

            methodCode.clear();
            methodCode.addAll(code.equalLines);
            code.methods.addAll(JavaCode.createMethodDefinition("public boolean", "isEqual", null, methodParam, methodCode, true));

            methodCode.clear();
            methodCode.add("return !this.isEqual(value);");
            code.methods.addAll(JavaCode.createMethodDefinition("public boolean", "notEquals", null, methodParam, methodCode, true));
        }

        // Generate the Default Constructor Method
        methodParam.clear();

        methodCode.clear();
        methodCode.addAll(code.constructorLines);
        code.methods.addAll(JavaCode.createMethodDefinition("public", shortClassName, null,  methodParam, methodCode, false));

        // Generate the Copy Constructor
        if (!code.assignmentLines.isEmpty())
        {
            methodParam.clear();
            methodParam.add(shortClassName + " value");

            methodCode.clear();
            methodCode.add("/// Initiliaze the protected variables");
            methodCode.addAll(code.constructorLines);
            methodCode.add("");
            methodCode.add("/// Copy the values");
            methodCode.addAll(code.assignmentLines);
            code.methods.addAll(JavaCode.createMethodDefinition("public", shortClassName, null,  methodParam, methodCode, false));
        }


        /// Generate the Destructor method
        methodParam.clear();

        methodCode.clear();
        methodCode.addAll(code.destructorLines);
        code.methods.addAll(JavaCode.createMethodDefinition("public void", "finalize", null,  methodParam, methodCode, false));



        /* Create the Class Wrapper for the Java File */
        /************************************************/
        classDef.add("public static class  " +  shortClassName);
        classDef.add("{");
        if (!code.classDefinitions.isEmpty())
        {
            Util.indent(1, code.classDefinitions);
            classDef.addAll(code.classDefinitions);
            classDef.add("");
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
            Util.indent(1, code.privateMethods);
            classDef.addAll(code.privateMethods);
            if (!code.privateMethods.isEmpty())
            {
                    classDef.add("");
            }
            Util.indent(1, code.privateAttributes);
            classDef.addAll(code.privateAttributes);
        }

        if(!code.methods.isEmpty())
        {
            classDef.add("");
            Util.indent(1, code.methods);
            classDef.addAll(code.methods);
        }

        classDef.add("}");
        code.classDefinitions.addAll(classDef);


        /* Reorder the provided CodeLines object */
        /*****************************************/
        methodCode.clear();
        methodCode.addAll(code.methods);

        code.clear();
        code.classDefinitions.addAll(classDef);
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
     * @param baseClassList
     */
    public static void addClassWrapper(String fullClassName, CodeLines code, List<String> baseClassList)
    {

            List<String> classDef = new ArrayList<String>();
            List<String> methodCode = new ArrayList<String>();
            List<String> methodParam = new ArrayList<String>();

            String shortClassName = getShortClassName(fullClassName);
            String baseClass = "";


            /* Add additional methods */
            /**************************/

            // Generate the getSize() method
            methodParam.clear();

            methodCode.clear();
            methodCode.add("long size = 0;");
            methodCode.add("");
            methodCode.addAll(code.sizeLines);
            methodCode.add("");
            methodCode.add("return size;");
            code.methods.add("/**");
            code.methods.add(" * Returns the number of bytes the used data members of the class occupies");
            code.methods.add(" * in the buffer. This is not the number of bytes the data type occupies in ");
            code.methods.add(" * Java, but the bytes expected on the wire.");
            code.methods.add(" * ");
            code.methods.add(" * @return");
            code.methods.add(" */");
            code.methods.addAll(JavaCode.createMethodDefinition("public long", "getSize", null,  methodParam, methodCode, false));

            // Add the encode Method
            methodParam.clear();
            methodParam.add("ByteBuffer bytes");

            methodCode.clear();
            methodCode.add("");
            methodCode.add("if (bytes.array() == null)");
            methodCode.add("{");
            methodCode.add("\treturn;");
            methodCode.add("}");
            methodCode.add("");
            methodCode.addAll(code.encoderLines);
            code.methods.addAll(JavaCode.createMethodDefinition("public void", "encode", null,  methodParam, methodCode, false));

            // Add the decode Method
            methodParam.clear();
            methodParam.add("ByteBuffer bytes");
            methodParam.add("int pos");

            methodCode.clear();
            methodCode.add("");
            methodCode.add("if (bytes.array() == null)");
            methodCode.add("{");
            methodCode.add("\treturn;");
            methodCode.add("}");
            methodCode.add("");
            methodCode.addAll(code.decoderLines);
            code.methods.addAll(JavaCode.createMethodDefinition("public void", "decode", null,  methodParam, methodCode, false));

            /// Add assignemnt method
            if (!code.assignmentLines.isEmpty())
            {
            methodParam.clear();
            methodParam.add( shortClassName + " value");

            methodCode.clear();
            methodCode.addAll(code.assignmentLines);
            methodCode.add("");
            methodCode.add("return this;");
            code.methods.addAll(JavaCode.createMethodDefinition("public " + fullClassName, "assign", null, methodParam, methodCode, false));
            }


            // Add isEqual method and corresponding notEquals method.
            if (!code.equalLines.isEmpty())
            {
                methodParam.clear();
                methodParam.add(shortClassName + " value");

                methodCode.clear();
                methodCode.addAll(code.equalLines);
                methodCode.add("");
                methodCode.add("return true;");
                code.methods.addAll(JavaCode.createMethodDefinition("public boolean", "isEqual", null, methodParam, methodCode, true));

                methodCode.clear();
                methodCode.add("return !this.isEqual(value);");
                code.methods.addAll(JavaCode.createMethodDefinition("public boolean", "notEquals", null, methodParam, methodCode, true));
            }

            /// Generate the Default Constructor Method
            methodParam.clear();

            methodCode.clear();
            methodCode.addAll(code.constructorLines);
            code.methods.addAll(JavaCode.createMethodDefinition("public", shortClassName, null,  methodParam, methodCode, false));


            /// Generate the Copy Constructor
            if (!code.assignmentLines.isEmpty())
            {
                methodParam.clear();
                methodParam.add(shortClassName + " value");

                methodCode.clear();
                methodCode.add("/// Initiliaze the protected variables");
                methodCode.addAll(code.constructorLines);
                methodCode.add("");
                methodCode.add("/// Copy the values");
                methodCode.addAll(code.assignmentLines);
                code.methods.addAll(JavaCode.createMethodDefinition("public", shortClassName, null,  methodParam, methodCode, false));
            }


            /// Generate the Destructor method
            methodParam.clear();

            methodCode.clear();
            methodCode.addAll(code.destructorLines);
            code.methods.addAll(JavaCode.createMethodDefinition("public void", "finalize", null,  methodParam, methodCode, false));



            /* Create the Class Wrapper */
            /************************************************/

            baseClass = baseClass.concat("public static class " +  shortClassName);

            if (baseClassList != null)
            {
                    /// add all base classes to string
                    for(int i = 0; i < baseClassList.size(); i++)
                    {
                            if(i == 0)
                            {
                                    baseClass = baseClass.concat(" extends ");
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
                    Util.indent(1, code.privateMethods);
                    classDef.addAll(code.privateMethods);
                    if (!code.privateMethods.isEmpty())
                    {
                            classDef.add("");
                    }
                    Util.indent(1, code.privateAttributes);
                    classDef.addAll(code.privateAttributes);
            }
            if(!code.methods.isEmpty())
            {
                classDef.add("");
                Util.indent(1, code.methods);
                classDef.addAll(code.methods);
            }
            classDef.add("}");
            code.classDefinitions.addAll(classDef);


            /* Reorder the provided CodeLines object */
            /*****************************************/
            methodCode.clear();
            methodCode.addAll(code.methods);

            code.clear();
            code.classDefinitions.addAll(classDef);
            //code.methods.addAll(methodCode);
    }


    /**
     * Adds a check around the supplied code to test if bit pvIndex is set.
     *
     * @param pvIndex
     * @param code
     * @return a List<String> with the additional lines
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
     * @return a List<String> with the additional lines
     */
    public static Vector<String> addNamespaceWrapper(String namespace, List<String> code)
    {
            Vector<String> temp = new Vector<String>();

            temp.add("import " + namespace + ";");
            temp.add("");
            temp.addAll(code);
            temp.add("");

            return temp;
    }


    /*
     * @param scaleRange
     * @param integerType
     * @param variableName
     * @param convertedVariableName
     */
    public static List<String> getIntToDoubleCode(ScaleRange scaleRange, String integerType, String variableName, String convertedVariableName)
    {
        String min = scaleRange.getRealLowerLimit();
        String max = scaleRange.getRealUpperLimit();

        List<String> codes = new ArrayList<String>();

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

    /*
     * @param scaleRange
     * @param integerType
     * @param variableName
     * @param convertedVariableName
     */
    public static List<String> getDoubleToIntCode(ScaleRange scaleRange, String integerType, String variableName, String convertedVariableName)
    {
        String min = scaleRange.getRealLowerLimit();

        String max = scaleRange.getRealUpperLimit();

        List<String> codes = new ArrayList<String>();

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


    /*
     * @param type
     * @param prefix
     * @param name
     * @param parameters
     * @param isConst
     */
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
            sb.append("");
        }

        return sb.toString();
    }


    /*
     * @param jsidlType
     */
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


    /*
     * @param range
     */
    public static String getMinValueString(BitRange range) throws CodeGeneratorException
    {
            return "";
    }

    /*
     * @param jsidlType
     */
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

    /*
     * @param jsidlType
     */
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

    /*
     * @param jsidlType
     */
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
     * @param fullClassName
     * @param incDir
     * @param srcDir
     * @param code
     * @param includes
     */
    public static void createJavaCode(String fullClassName, String incDir, String srcDir, CodeLines code, List<String> includes)
    {
            List<String> output = new ArrayList<String>();
            String shortClassName = getShortClassName(fullClassName);


            // Write the .java file if there are methods
            if (!code.methods.isEmpty())
            {
                // Write the imports
                output.add("import framework.JausUtils;");
                for (String line: includes)
                {
                        output.add("import " + line + ";");
                }
                output.add("");


                output.add("public class " + shortClassName);
                output.add("{");
                output.add("");
                Util.indent(1, code.classDefinitions);
                output.addAll(code.classDefinitions);
                output.add("}");

                new File(srcDir).mkdir();
                new CodeWriter().write(output, srcDir, shortClassName + ".java");
            }
    }

    /**
     * Overidden function for generating javaCode with a namespace
     * @param fullClassName
     * @param incDir
     * @param srcDir
     * @param code
     * @param includes
     * @param namespace
     * @param baseClassList
     *
     */
    public static void createJavaCode(String fullClassName, String incDir, String srcDir, CodeLines code, List<String> includes, String namespace, List<String> baseClassList)
    {
            List<String> output = new ArrayList<String>();

            String shortClassName = getShortClassName(fullClassName);

            // Write the .java file
            output.add("package src." + namespace + ";");
            output.add("");

            for (String line: includes)
            {
                    output.add("import " + line + ";");
            }
            output.add("");

            String temp = "";
            if(baseClassList != null)
            {
                temp += " extends ";
                int numBaseClasses = baseClassList.size();

                if(numBaseClasses > 1)
                {
                    for(int i = 0; i < numBaseClasses-1; i++)
                    {
                        temp += baseClassList.get(i).split(" ")[1] + ", ";
                    }
                }

                temp += baseClassList.get(baseClassList.size()-1).split(" ")[1];
            }

            // Wrap method lines inside appropriate methods
            createMethods(code, shortClassName);
            //addClassWrapper(shortClassName, code);

            // Add everything to the file
            output.add("public class " + shortClassName + temp);
            output.add("{");
            Util.indent(1, code.classDefinitions);
            output.addAll(code.classDefinitions);
            Util.indent(1, code.protectedAttributes);
            output.addAll(code.protectedAttributes);
            Util.indent(1, code.methods);
            output.addAll(code.methods);

            output.add("}");


            new File(srcDir).mkdir();
            new CodeWriter().write(output, srcDir, shortClassName + ".java");
    }


    /*
     * @param code stores the strings of generated gode
     * This method generates the methods for a message set from the generated lines of code.
     * Equivilent class in C++ geneartor is ClassWrapper
     */
    public static void createMethods(CodeLines code, String shortClassName)
    {
        List<String> methodCode = new ArrayList<String>();
        List<String> methodParam = new ArrayList<String>();

        methodCode.clear();
        methodCode.add("int size = 0;");
        methodCode.add("");
        methodCode.addAll(code.sizeLines);
        methodCode.add("");
        methodCode.add("return size;");
        code.methods.add("/**");
        code.methods.add(" * Returns the number of bytes the used data members of the class occupies");
        code.methods.add(" * in the buffer. This is not the number of bytes the data type occupies in ");
        code.methods.add(" * Java, but the bytes expected on the wire.");
        code.methods.add(" * ");
        code.methods.add(" * @return");
        code.methods.add(" */");
        code.methods.addAll(JavaCode.createMethodDefinition("public long", "getSize", null,  methodParam, methodCode, false));

        methodParam.clear();
        methodParam.add("ByteBuffer bytes");
        methodParam.add("int pos");

        methodCode.clear();
        methodCode.add("");
        methodCode.add("if (bytes.array() == null)");
        methodCode.add("{");
        methodCode.add("\treturn;");
        methodCode.add("}");
        methodCode.add("if(bytes.order() != ByteOrder.LITTLE_ENDIAN)");
        methodCode.add("{");
        methodCode.add("\tbytes.order(ByteOrder.LITTLE_ENDIAN);");
        methodCode.add("}");
        methodCode.add("");
        methodCode.addAll(code.encoderLines);
        code.methods.addAll(JavaCode.createMethodDefinition("public void", "encode", null,  methodParam, methodCode, false));

        methodParam.clear();
        methodParam.add("ByteBuffer bytes");
        methodParam.add("int pos");

        methodCode.clear();
        methodCode.add("");
        methodCode.add("if (bytes.array() == null)");
        methodCode.add("{");
        methodCode.add("\treturn;");
        methodCode.add("}");
        methodCode.add("if(bytes.order() != ByteOrder.LITTLE_ENDIAN)");
        methodCode.add("{");
        methodCode.add("\tbytes.order(ByteOrder.LITTLE_ENDIAN);");
        methodCode.add("}");
        methodCode.add("");
        methodCode.addAll(code.decoderLines);
        code.methods.addAll(JavaCode.createMethodDefinition("public void", "decode", null,  methodParam, methodCode, false));

        /// Assign
        if (!code.assignmentLines.isEmpty())
        {
            methodParam.clear();
            methodParam.add(shortClassName + " value");

            methodCode.clear();
            methodCode.addAll(code.assignmentLines);
            methodCode.add("");
            methodCode.add("return this;");
            code.methods.addAll(JavaCode.createMethodDefinition("public " + shortClassName, "setAs", null, methodParam, methodCode, false));
        }

        if (!code.equalLines.isEmpty())
        {
            methodParam.clear();
            methodParam.add(shortClassName + " value");

            methodCode.clear();
            methodCode.addAll(code.equalLines);
            methodCode.add("");
            methodCode.add("return true;");
            code.methods.addAll(JavaCode.createMethodDefinition("public boolean", "isEqual", null, methodParam, methodCode, true));

            // also add a corresponding notEquals method.
            methodCode.clear();
            methodCode.add("return !isEqual(value);");
            code.methods.addAll(JavaCode.createMethodDefinition("public boolean", "notEquals", null, methodParam, methodCode, true));
        }

        /// Generate the Default Constructor Method
        methodParam.clear();
        methodCode.clear();
        methodCode.addAll(code.constructorLines);
        code.methods.addAll(JavaCode.createMethodDefinition("public", shortClassName, null,  methodParam, methodCode, false));

        /// Generate the Copy Constructor
        if (!code.assignmentLines.isEmpty())
        {
            methodParam.clear();
            methodParam.add(shortClassName + " value");

            methodCode.clear();
            methodCode.add("/// Initiliaze the protected variables");
            methodCode.addAll(code.constructorLines);
            methodCode.add("");
            methodCode.add("/// Copy the values");
            methodCode.addAll(code.assignmentLines);
            code.methods.addAll(JavaCode.createMethodDefinition("public ", shortClassName, null,  methodParam, methodCode, false));
        }

    }
    /*
     * @param outDir
     * @param isTest
     * @throws Exception
     */
    static public void copyTemplateFiles(String outDir, boolean isTest) throws Exception
    {

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
                    Util.writeContents(new File(outDir + "/Common/Sconstruct"), 
                            sconGen.generateLibrary(new File(outDir + "/Common"), "Common", new ArrayList<String>()));
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


     /*
      * @param fullClassName
      */
     static public String getShortClassName(String fullClassName)
     {
        return fullClassName.substring(fullClassName.lastIndexOf('.') + 1);
     }


     /*
      * @param fullClassName
      */
     static public String getParentClassName(String fullClassName)
     {
    	 String shortClassName = getShortClassName(fullClassName);
         // Crop the last class off the full class name and the last '.'
    	 return fullClassName.substring(0, fullClassName.length() - ((shortClassName.length())+1));
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
    	 return varName + ".get(i).setParent(this);";
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
    	 code.constructorLines.add("for (int i = 0; i < "  + arraySize + "; i++)");
    	 code.constructorLines.add("{");
    	 code.constructorLines.add("\t" + varName + "[i].setParent(this);");
    	 code.constructorLines.add("}");
     }

     static public void addParentReferenceConstructorSetParrentInArray(CodeLines code, String varName)
     {
    	 // add the setParent method to the constructor for each of the elements
    	 // for( int i = 0; i < .... )
    	 // 	m_rec[ i ].setParent(this)
    	 code.constructorLines.add("for (int i = 0; i < " + varName + ".size(); i++)");
    	 code.constructorLines.add("{");
    	 code.constructorLines.add("\t" + varName + ".get(i).setParent(this);");
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
    	 code.protectedAttributes.add("protected " + parentClassName + " m_parent;");
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
    	 code.constructorLines.add("m_parent = null;");
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
    	 methodParam.add(parentClassName + " parent");
    	 // void Message.seq.rec.setParent(seq parent)
    	 // {
    	 // 	m_parent = parent;
    	 // }
    	 methodCode.clear();
    	 methodCode.add("m_parent = parent;");
    	 code.methods.addAll(JavaCode.createMethodDefinition("public void","setParent", null,  methodParam, methodCode, false));
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

    	 // for example: 
         // void Message.seq.rec.setParentPresenceVector()
    	 // {
    	 // 	if(m_parent != null )
    	 // 	{
    	 // 		m_parent->setPresenceVector(0);
    	 // 		m_parent->setParentPresenceVector();
    	 // 	}
    	 // }
    	 methodCode.clear();
    	 methodCode.add("if(m_parent != null )");
    	 methodCode.add("{");

        // only set the presence vector if the current class was optional in it's parent class
        if( isOptional )
        {
            methodCode.add("\tm_parent.setPresenceVector(" + Integer.toString(pvIndex) + ");");
        }

        // always call the recursive set parent pv method so all pv to root element will be set
        methodCode.add("\tm_parent.setParentPresenceVector();");

    	 methodCode.add("}");
    	 code.methods.addAll(JavaCode.createMethodDefinition("public void", "setParentPresenceVector", null,  null, methodCode, false));
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

    	 // void Message.seq.rec.setParentPresenceVector()
    	 // {
    	 // 	// Nothing needed here, placeholder function
    	 // }
    	 methodCode.clear();
    	 methodCode.add("// Nothing needed here, placeholder function");
    	 code.methods.addAll(JavaCode.createMethodDefinition("public void", "setParentPresenceVector", null,  null, methodCode, false));
     }

     /**
      * Get the string that should be added to any set methods that are called
      * @return
      */
     static public String getParentReferenceSetParentPVLine()
     {
    	 return "setParentPresenceVector();";
     }

     /*
      * Generate encoder lines for specific data types
      * @param varType the variable type
      * @param varName the variable name
      * @param CodeLines code the collection of code lines to be added to.
      * @param signed if the C++ code expects a signed or unsigned value.
      */
     static public void generateEncoderLines(String varType, String varName, List<String> code, boolean signed)
     {
         // Perform the necessary type casting based on the C++ type
         //     the Java variable represents and is expected by the Junior Framework.
         if(varType.compareTo("int") == 0 && signed)
         {
             // Variable is an int in Java, an unsigned short in the C++ library
             code.add("bytes.putShort(pos, (short) " + varName + ");");
             code.add("pos += JausUtils.getNumBytes(\"short\");");
         }
         else if(varType.compareTo("short") == 0 && signed)
         {
             // Variable is an short in Java, an unsigned byte in the C++ library
             code.add("bytes.put(pos, (byte) " + varName + ");");
             code.add("pos += JausUtils.getNumBytes(\"byte\");");
         }
         else if(varType.compareTo("long") == 0 && signed)
         {
             // Variable is an long in Java, an unsigned int in the C++ library
             code.add("bytes.putInt(pos, (int) " + varName + ");");
             code.add("pos += JausUtils.getNumBytes(\"int\");");
         }
         else if(varType.compareTo("int") == 0)
         {
             // Variable is an int in Java, an unsigned short in the C++ library
             code.add("bytes.putInt(pos, " + varName + ");");
             code.add("pos += JausUtils.getNumBytes(\"int\");");
         }
         else if(varType.compareTo("short") == 0)
         {
             // Variable is an short in Java, an unsigned byte in the C++ library
             code.add("bytes.putShort(pos, " + varName + ");");
             code.add("pos += JausUtils.getNumBytes(\"short\");");
         }
         else if(varType.compareTo("long") == 0)
         {
             // Variable is an long in Java, an unsigned int in the C++ library
             code.add("bytes.putLong(pos, " + varName + ");");
             code.add("pos += JausUtils.getNumBytes(\"long\");");
         }
         else if(varType.compareTo("byte") == 0)
         {
             // Variable is a byte in Java, a byte in the C++ library
             code.add("bytes.put(pos," + varName + ");");
             code.add("pos += JausUtils.getNumBytes(\"byte\");");
         }
         else if(varType.compareTo("float") == 0)
         {
             // Variable is a byte in Java, a byte in the C++ library
             code.add("bytes.putFloat(pos, " + varName + ");");
             code.add("pos += JausUtils.getNumBytes(\"int\");");
         }
         else if(varType.compareTo("double") == 0)
         {
             // Variable is a byte in Java, a byte in the C++ library
             code.add("bytes.putDouble(pos," + varName + ");");
             code.add("pos += JausUtils.getNumBytes(\"double\");");
         }
     }

     /*
      * Generate encoder lines for specific data types
      * @param varType the variable type
      * @param varName the variable name
      * @param CodeLines code the collection of code lines to be added to.
      * @param bytes the number of bytes to add to the buffer if the number is variable.
      */
     static public void generateEncoderLines(String varType, String varName, List<String> code, String bytes)
     {
        if(varType.compareTo("String") == 0)
         {
             // Turn the numbytes and decode to a String. Length of string decoded first.
            code.add("char[] " + varName + "Bytes = " + varName + ".toCharArray();");
            code.add("for(int str_pos = 0; str_pos<" + varName + "Bytes.length; str_pos++)");
            code.add("{");
            code.add("\tbytes.put(pos, (byte) " + varName + "Bytes[str_pos]);");
            code.add("\tpos++;");
            code.add("}");
            
         }
     }
    /*
      * Generate decoder lines for specific data types
      * @param varType the variable type
      * @param varName the variable name
      * @param CodeLines code the collection of code lines to be added to.
      * @param signed if the C++ code expects a signed or unsigned value.
      */
     static public void generateDecoderLines(String varType, String varName, List<String> code, boolean signed)
     {
         // Perform the necessary bit shift and type casting based on the C++ type
         //     the Java variable represents and is expected by the Junior Framework.
         if(varType.compareTo("int") == 0 && signed)
         {
             // Variable is an int in Java, an unsigned short in the C++ library
             code.add(varName + " = bytes.getShort(pos) & 0xffff;");
             code.add("pos += JausUtils.getNumBytes(\"short\");");
         }
         else if(varType.compareTo("short") == 0 && signed)
         {
             // Variable is an short in Java, an unsigned byte in the C++ library
             code.add(varName + " = (short) (bytes.get(pos) & 0xff);");
             code.add("pos += JausUtils.getNumBytes(\"byte\");");
         }
         else if(varType.compareTo("long") == 0 && signed)
         {
             // Variable is an int in Java, an unsigned short in the C++ library
             code.add(varName + " = bytes.getInt(pos) & 0xffffffffL;");
             code.add("pos += JausUtils.getNumBytes(\"int\");");
         }
         else if(varType.compareTo("int") == 0)
         {
             // Variable is an int in Java, an unsigned short in the C++ library
             code.add(varName + " = bytes.getInt(pos);");
             code.add("pos += JausUtils.getNumBytes(\"int\");");
         }
         else if(varType.compareTo("short") == 0)
         {
             // Variable is an short in Java, an unsigned byte in the C++ library
             code.add(varName + " = bytes.getShort(pos);");
             code.add("pos += JausUtils.getNumBytes(\"short\");");
         }
         else if(varType.compareTo("long") == 0)
         {
             // Variable is an int in Java, an unsigned short in the C++ library
             code.add(varName + " = bytes.getLong(pos);");
             code.add("pos += JausUtils.getNumBytes(\"long\");");
         }
         else if(varType.compareTo("byte") == 0)
         {
             // Variable is an int in Java, an unsigned short in the C++ library
             code.add(varName + " = bytes.get(pos);");
             code.add("pos += JausUtils.getNumBytes(\"byte\");");
         }
         else if(varType.compareTo("float") == 0)
         {
             // Variable is an int in Java, an unsigned short in the C++ library
             code.add(varName + " = bytes.getFloat(pos);");
             code.add("pos += JausUtils.getNumBytes(\"int\");");
         }
         else if(varType.compareTo("double") == 0)
         {
             // Variable is an int in Java, an unsigned short in the C++ library
             code.add(varName + " = bytes.getDouble(pos);");
             code.add("pos += JausUtils.getNumBytes(\"double\");");
         }

     }

     /*
      * Generate decoder lines for specific data types
      * @param varType the variable type
      * @param varName the variable name
      * @param CodeLines code the collection of code lines to be added to.
      * @param bytes the number of bytes to pull off the buffer if the number is variable.
      */
     public static void generateDecoderLines(String varType, String varName, List<String> code, String bytes)
     {
         if(varType.compareTo("String") == 0)
         {
             code.add("");
             code.add("// Decoding the string " + varName + "from the buffer.");
             code.add("char[] " + varName + "Bytes = new char[(int)" + bytes + "];");
             code.add("for(int decode_pos = 0; decode_pos<(int)" + bytes + ";decode_pos++)");
             code.add("{");
             code.add("\t" + varName + "Bytes[decode_pos] = (char) (bytes.get(pos) &0xff);");
             code.add("\tpos++;");
             code.add("}");
             code.add(varName + " = new String(" +varName + "Bytes);");
         }
     }

     /*
      * Encodes each byte of an array into the buffer.
      * @param varName the name of the byte array that is to be encoded.
      * @param bytes the number of bytes to be encoded.
      * @param code the holder for the code that is generated.
      * @param string if the data should be cast to a byte or not.
      */
     public static void encodeByteArrayLines(String varName, String varLen, List<String> code, boolean string)
     {
         code.add("");
         code.add("for(int i = 0; i<" + varLen + "; i++)");
         code.add("{");
         if(string)
         {
             code.add("\tbytes.put(pos, (byte)" + varName + "[i]);");
         }
         else
         {
             code.add("\tbytes.put(pos, " + varName + "[i]);");
         }
         code.add("\tpos++;");
         code.add("}");
         code.add("");
     }

     /*
      * Decodes each byte of an array from the buffer.
      * @param varName the name of the byte array that is to be decoded.
      * @param code the holder for the code that is generated.
      * @param string if the data pulled should be a byte or char
      */
     public static void decodeByteArrayLines(String varName, String varLen, List<String> code, boolean string)
     {
         code.add("");
         code.add("int i;");
         code.add("for(i=0; i<" + varLen + "; i++)");
         code.add("{");
         if(string)
         {
             code.add("\t" + varName + "[i] = (char) (bytes.get(pos) &0xff);");
         }
         else
         {
            code.add("\t" + varName + "[i] = bytes.get(pos);");
         }
         code.add("\tpos++;");
         code.add("}");
         code.add("");
     }

     /*
      * Generate getSize lines for specific data types
      * @param varType the variable type
      * @param CodeLines code the collection of code lines to be added to.
      * @param signed if the C++ code expects a signed or unsigned value.
      */
     static public void generateGetSizeLines(String varType, List<String> code, boolean signed)
     {
         // Perform the necessary bit shift and type casting based on the C++ type
         //     the Java variable represents and is expected by the Junior Framework.
         if(varType.compareTo("int") == 0 && signed)
         {
             // Variable is an int in Java, an unsigned short in the C++ library
             code.add("size += JausUtils.getNumBytes(\"short\");");
         }
         else if(varType.compareTo("short") == 0 && signed)
         {
             // Variable is an short in Java, an unsigned byte in the C++ library
             code.add("size += JausUtils.getNumBytes(\"byte\");");
         }
         else if(varType.compareTo("long") == 0 && signed)
         {
             // Variable is an int in Java, an unsigned short in the C++ library
             code.add("size += JausUtils.getNumBytes(\"int\");");
         }
         else if(varType.compareTo("int") == 0)
         {
             // Variable is an int in Java, an unsigned short in the C++ library
             code.add("size += JausUtils.getNumBytes(\"int\");");
         }
         else if(varType.compareTo("short") == 0)
         {
             // Variable is an short in Java, an unsigned byte in the C++ library
             code.add("size += JausUtils.getNumBytes(\"short\");");
         }
         else if(varType.compareTo("long") == 0)
         {
             // Variable is an int in Java, an unsigned short in the C++ library
             code.add("size += JausUtils.getNumBytes(\"long\");");
         }
         else if(varType.compareTo("byte") == 0)
         {
             // Variable is an byte in Java, a byte in the C++ library
             code.add("size += JausUtils.getNumBytes(\"byte\");");
         }
         else if(varType.compareTo("float") == 0)
         {
             // Variable is an byte in Java, a byte in the C++ library
             code.add("size += JausUtils.getNumBytes(\"int\");");
         }
         else if(varType.compareTo("double") == 0)
         {
             // Variable is an byte in Java, a byte in the C++ library
             code.add("size += JausUtils.getNumBytes(\"double\");");
         } else {
             throw new RuntimeException("Encountered unknown varType/signedness "
                     + "combination in single-instance generateGetSizeLines");
         }
     }

     /*
      * Generate getSize lines for arrays of specific data types
      * @param varType the variable type
      * @param CodeLines code the collection of code lines to be added to.
      * @param mulitiplier - some size methods for arrays such as the one in FixedFieldGenerator require a muliplier.
      * @param signed if the C++ code expects a signed or unsigned value.
      */
     static public void generateGetSizeLines(String varType, List<String> code, int multiplier, boolean signed)
     {
         // Perform the necessary bit shift and type casting based on the C++ type
         //     the Java variable represents and is expected by the Junior Framework.
         if(varType.compareTo("int") == 0 && signed)
         {
             // Variable is an int in Java, an unsigned short in the C++ library
             code.add("size += JausUtils.getNumBytes(\"short\") * " + multiplier + ";");
         }
         else if(varType.compareTo("short") == 0 && signed)
         {
             // Variable is an short in Java, an unsigned byte in the C++ library
             code.add("size += JausUtils.getNumBytes(\"byte\") * " + multiplier + ";");
         }
         else if(varType.compareTo("long") == 0 && signed)
         {
             // Variable is an int in Java, an unsigned short in the C++ library
             code.add("size += JausUtils.getNumBytes(\"int\") * " + multiplier + ";");
         }
         else if(varType.compareTo("int") == 0)
         {
             // Variable is an int in Java, an unsigned short in the C++ library
             code.add("size += JausUtils.getNumBytes(\"int\") * " + multiplier + ";");
         }
         else if(varType.compareTo("short") == 0)
         {
             // Variable is an short in Java, an unsigned byte in the C++ library
             code.add("size += JausUtils.getNumBytes(\"short\") * " + multiplier + ";");
         }
         else if(varType.compareTo("long") == 0)
         {
             // Variable is an int in Java, an unsigned short in the C++ library
             code.add("size += JausUtils.getNumBytes(\"long\") * " + multiplier + ";");
         }
         else if(varType.compareTo("byte") == 0)
         {
             // Variable is an int in Java, an unsigned short in the C++ library
             code.add("size += JausUtils.getNumBytes(\"byte\") * " + multiplier + ";");
         }
         else if(varType.compareTo("float") == 0)
         {
             // Variable is an int in Java, an unsigned short in the C++ library
             code.add("size += JausUtils.getNumBytes(\"int\") * " + multiplier + ";");
         }
         else if(varType.compareTo("double") == 0)
         {
             // Variable is an int in Java, an unsigned short in the C++ library
             code.add("size += JausUtils.getNumBytes(\"double\") * " + multiplier + ";");
         } else {
             throw new RuntimeException("Encountered unknown varType/signedness "
                     + "combination in array generateGetSizeLines");
         }
     }
}


