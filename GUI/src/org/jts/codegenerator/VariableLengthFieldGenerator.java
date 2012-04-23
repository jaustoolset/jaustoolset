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
 * @(#)VariableLengthFieldGenerator.java		0.1 2008/10/08
 * 
 * Copyright
 */
package org.jts.codegenerator;

import java.util.ArrayList;

import java.util.List;
import org.jts.jsidl.binding.Dimension;
import org.jts.jsidl.binding.VariableLengthField;

/**
 * This class generates the code needed for a Variable Length Field JAXB Object
 * 
 * @author Nicholas M. Johnson
 *
 */
public class VariableLengthFieldGenerator {

    private CodeLines.CodeType codeType;
    private VariableLengthField vlField;
    private List<Dimension> dimList;
    private String shortClassName;
    private String parentClassName;
    private String fullClassName;
    private String lengthType;
    private boolean lengthSigned;
    private String lengthVarName;
    private String tempVarName;
    private List<String> methodCode;
    private List<String> methodParam;
    private String dataVarName;
    private String varName;

    public VariableLengthFieldGenerator(CodeLines.CodeType codeType, VariableLengthField vlField) {
        this.codeType = codeType;
        this.vlField = vlField;
        this.dimList = null;

        methodCode = new ArrayList<String>();
        methodParam = new ArrayList<String>();

        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            lengthType = CppCode.getVariableType(vlField.getCountField().getFieldTypeUnsigned());
            lengthVarName = CppCode.getVariableName("length");
            tempVarName = lengthVarName + "Temp";
            dataVarName = CppCode.getVariableName("data");
        } else if (codeType == CodeLines.CodeType.JAVA) {
            lengthType = JavaCode.getVariableType(vlField.getCountField().getFieldTypeUnsigned());
            lengthSigned = JavaCode.getVariableSign(vlField.getCountField().getFieldTypeUnsigned());
            lengthVarName = JavaCode.getVariableName("length");
            tempVarName = lengthVarName + "Temp";
            dataVarName = JavaCode.getVariableName("data");
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            lengthType = CSharpCode.getVariableType(vlField.getCountField().getFieldTypeUnsigned());
            lengthVarName = CSharpCode.getVariableName("length");
            tempVarName = lengthVarName + "Temp";
            dataVarName = CSharpCode.getVariableName("data");
        }
    }

    public VariableLengthFieldGenerator(CodeLines.CodeType codeType, VariableLengthField vlField, List<Dimension> dimList) {
        this.codeType = codeType;
        this.vlField = vlField;
        this.dimList = dimList;

        methodCode = new ArrayList<String>();
        methodParam = new ArrayList<String>();

        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            lengthType = CppCode.getVariableType(vlField.getCountField().getFieldTypeUnsigned());
            lengthVarName = CppCode.getVariableName("length");
            tempVarName = lengthVarName + "Temp";
            dataVarName = CppCode.getVariableName("data");
        } else if (codeType == CodeLines.CodeType.JAVA) {
            lengthType = JavaCode.getVariableType(vlField.getCountField().getFieldTypeUnsigned());
            lengthVarName = JavaCode.getVariableName("length");
            lengthSigned = JavaCode.getVariableSign(vlField.getCountField().getFieldTypeUnsigned());
            tempVarName = lengthVarName + "Temp";
            dataVarName = JavaCode.getVariableName("data");
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            lengthType = CSharpCode.getVariableType(vlField.getCountField().getFieldTypeUnsigned());
            lengthVarName = CSharpCode.getVariableName("length");
            tempVarName = lengthVarName + "Temp";
            dataVarName = CSharpCode.getVariableName("data");
        }
    }

    public void run(String parentName, int pvIndex, boolean isNested, CodeLines code) throws CodeGeneratorException {
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            if (isNested) {
                fullClassName = parentName + "::" + vlField.getName();
            } else {
                fullClassName = parentName;
            }

            shortClassName = CppCode.getShortClassName(fullClassName);
            parentClassName = CppCode.getParentClassName(fullClassName);
            varName = CppCode.getVariableName(shortClassName);
            
            /*
             * Create framework for parent reference
             */
           	CppCode.addParentReference(code, fullClassName, pvIndex, vlField.isOptional());

            if (dimList == null) {
                if (isNested) {
                    CodeLines vfCode = new CodeLines("", codeType, code.getNameSpace());

                    generateFieldCode(pvIndex, vfCode);
                    code.addAll(vfCode);

                    /// Add a class wrapper around the generated code
                    CppCode.addClassWrapper(fullClassName, code);

                    generateSingleInstance(pvIndex, code);
                } else {
                    /// NOT IMPLEMENTED
                    throw new CodeGeneratorException("Desired Output is Not Implemented");
                }
            } else {
                if (isNested) {
                    /* Generate the the code for a single instance of a VariableField */
                    /*****************************************/
                    CodeLines vfCode = new CodeLines("", codeType, code.getNameSpace());

                    generateFieldCode(pvIndex, vfCode);
                    code.addAll(vfCode);
                    /*****************************************/
                    /// Add a class wrapper around the generated code
                    CppCode.addClassWrapper(fullClassName, code);

                    /// Generate an array of VariableFields
                    generateArray(pvIndex, code);
                } else {
                    /// NOT IMPLEMENTED
                    throw new CodeGeneratorException("Desired Output is Not Implemented");
                }
            }
        } else if (codeType == CodeLines.CodeType.JAVA) {
            if (isNested) {
                fullClassName = parentName + "." + vlField.getName();
            } else {
                fullClassName = parentName;
            }

            shortClassName = JavaCode.getShortClassName(fullClassName);
            parentClassName = JavaCode.getParentClassName(fullClassName);
            varName = JavaCode.getVariableName(shortClassName);

            JavaCode.addParentReference(code, fullClassName, pvIndex, vlField.isOptional());

            if (dimList == null) {
                if (isNested) {
                    CodeLines vfCode = new CodeLines("", codeType, code.getNameSpace());

                    generateFieldCode(pvIndex, vfCode);
                    code.addAll(vfCode);

                    /// Add a class wrapper around the generated code
                    JavaCode.addClassWrapper(fullClassName, code);

                    generateSingleInstance(pvIndex, code);
                } else {
                    /// NOT IMPLEMENTED
                    throw new CodeGeneratorException("Desired Output is Not Implemented");
                }
            } else {
                if (isNested) {
                    /* Generate the the code for a single instance of a VariableField */
                    /*****************************************/
                    CodeLines vfCode = new CodeLines("", codeType, code.getNameSpace());

                    generateFieldCode(pvIndex, vfCode);
                    code.addAll(vfCode);
                    /*****************************************/
                    /// Add a class wrapper around the generated code
                    JavaCode.addClassWrapper(fullClassName, code);

                    /// Generate an array of VariableFields
                    generateArray(pvIndex, code);
                } else {
                    /// NOT IMPLEMENTED
                    throw new CodeGeneratorException("Desired Output is Not Implemented");
                }
            }
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            if (isNested) {
                fullClassName = parentName + "." + vlField.getName();
            } else {
                fullClassName = parentName;
            }

            shortClassName = CSharpCode.getShortClassName(fullClassName);
            parentClassName = CSharpCode.getParentClassName(fullClassName);
            varName = CSharpCode.getVariableName(shortClassName);

            /*
             * Create framework for parent reference
             */
           	CSharpCode.addParentReference(code, fullClassName, pvIndex, vlField.isOptional());

            if (dimList == null) {
                if (isNested) {
                    CodeLines vfCode = new CodeLines("", codeType, code.getNameSpace());

                    generateFieldCode(pvIndex, vfCode);
                    code.addAll(vfCode);

                    /// Add a class wrapper around the generated code
                    CSharpCode.addClassWrapper(fullClassName, code);

                    generateSingleInstance(pvIndex, code);
                } else {
                    /// NOT IMPLEMENTED
                    throw new CodeGeneratorException("Desired Output is Not Implemented");
                }
            } else {
                if (isNested) {
                    /* Generate the the code for a single instance of a VariableField */
                    /*****************************************/
                    CodeLines vfCode = new CodeLines("", codeType, code.getNameSpace());

                    generateFieldCode(pvIndex, vfCode);
                    code.addAll(vfCode);
                    /*****************************************/
                    /// Add a class wrapper around the generated code
                    CSharpCode.addClassWrapper(fullClassName, code);

                    /// Generate an array of VariableFields
                    generateArray(pvIndex, code);
                } else {
                    /// NOT IMPLEMENTED
                    throw new CodeGeneratorException("Desired Output is Not Implemented");
                }
            }
        }
    }

    /**
     * Generate field
     * @param pvIndex
     * @param code
     * @throws CodeGeneratorException
     */
    private void generateFieldCode(int pvIndex, CodeLines code) throws CodeGeneratorException {
        /// Add the code for the field length
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            code.protectedAttributes.add(CppCode.createVariableDeclaration(lengthType, "length", false));
            code.constructorLines.add(lengthVarName + " = 0;");
            code.sizeLines.add("size += sizeof(" + lengthType + ");");

            /// Add the code for the field data
            code.protectedAttributes.add(CppCode.createVariableDeclaration("unsigned char", "data", true));
            code.constructorLines.add(dataVarName + " = NULL;");
            code.destructorLines.add("delete[] " + dataVarName + ";");
            code.sizeLines.add("size += " + lengthVarName + ";");
        } else if (codeType == CodeLines.CodeType.JAVA) {
            code.protectedAttributes.add(JavaCode.createVariableDeclaration("protected " + lengthType, "length", false));
            code.constructorLines.add(lengthVarName + " = MAX_JTS_MESSAGE_SIZE;");
            JavaCode.generateGetSizeLines(lengthType, code.sizeLines, lengthSigned);

            /// Add the code for the field data
            code.protectedAttributes.add(JavaCode.createVariableDeclaration("protected ByteBuffer", "data", true));
            code.constructorLines.add(dataVarName + " = ByteBuffer.allocate((int)" + lengthVarName + ");");
            code.constructorLines.add(dataVarName + ".order(ByteOrder.LITTLE_ENDIAN); ");
            code.sizeLines.add("size += " + lengthVarName + ";");
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            code.protectedAttributes.add(CSharpCode.createVariableDeclaration(lengthType, "length", true));
            code.constructorLines.add(lengthVarName + " = 0;");
            code.sizeLines.add("size += JausUtils.getNumBytes(\"" + lengthType + "\");");

            /// Add the code for the field data
            code.protectedAttributes.add(CSharpCode.createVariableDeclaration("byte[]", "data", true));
            code.constructorLines.add(dataVarName + " = null;");
            code.sizeLines.add("size += (int)" + lengthVarName + ";");
        }

        generateFieldEncodeMethod(code);
        generateFieldDecodeMethod(code);

        generateFieldGetLengthMethod(code);

        generateFieldGetDataMethod(code);
        generateFieldSetMethod(code);

        generateFieldOverloadAssignmentMethod(code);
        generateFieldOverloadIsEqualMethod(code);
        generateFieldOverloadNotEqualMethod(code);
    }

    private void generateFieldEncodeMethod(CodeLines code) {
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            code.encoderLines.add(lengthType + " " + tempVarName + ";");
            code.encoderLines.add("");
            code.encoderLines.add(tempVarName + " = " + code.getNameSpace() + "::correctEndianness(" + lengthVarName + ");");
            code.encoderLines.add("memcpy(bytes+pos, &" + tempVarName + ", sizeof(" + lengthType + "));");
            code.encoderLines.add("pos += sizeof(" + lengthType + ");");

            /// Add code for the encode method
            code.encoderLines.add("");
            code.encoderLines.add("memcpy(bytes+pos, " + dataVarName + ", " + lengthVarName + ");");
            code.encoderLines.add("pos += " + lengthVarName + ";");
        } else if (codeType == CodeLines.CodeType.JAVA) {
            code.encoderLines.add("");
            JavaCode.generateEncoderLines(lengthType, lengthVarName, code.encoderLines, lengthSigned);

            /// Add code for the encode method
            code.encoderLines.add("");
            code.encoderLines.add("byte[] byteArray = " + dataVarName + ".array();");
            JavaCode.encodeByteArrayLines("byteArray", "(int) " + lengthVarName, code.encoderLines, false);

        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            CSharpCode.generateEncoderLines(lengthType, lengthVarName, code.encoderLines);

            /// Add code for the encode method
            code.encoderLines.add("");
            code.encoderLines.add("bytes = JausUtils.addToBuffer(bytes, " + dataVarName + ", pos, (int)" + lengthVarName + ", true);");
            code.encoderLines.add("");
            code.encoderLines.add("pos += (int)" + lengthVarName + ";");
        }
    }

    private void generateFieldDecodeMethod(CodeLines code) {
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            code.decoderLines.add(lengthType + " " + tempVarName + ";");
            code.decoderLines.add("");
            code.decoderLines.add("memcpy(&" + tempVarName + ", bytes+pos, sizeof(" + lengthType + "));");
            code.decoderLines.add(lengthVarName + " = " + code.getNameSpace() + "::correctEndianness(" + tempVarName + ");");
            code.decoderLines.add("pos += sizeof(" + lengthType + ");");

            /// Add code for the decode method
            code.decoderLines.add("");
            code.decoderLines.add("delete[] " + dataVarName + ";");
            code.decoderLines.add(dataVarName + " = NULL;");
            code.decoderLines.add("");
            code.decoderLines.add("if (" + lengthVarName + " > 0)");
            code.decoderLines.add("{");
            code.decoderLines.add("\t" + dataVarName + " = new unsigned char[" + lengthVarName + "];");
            code.decoderLines.add("\tmemcpy(" + dataVarName + ", bytes+pos, " + lengthVarName + ");");
            code.decoderLines.add("\tpos += " + lengthVarName + ";");
            code.decoderLines.add("}");
        } else if (codeType == CodeLines.CodeType.JAVA) {
            code.decoderLines.add("");
            JavaCode.generateDecoderLines(lengthType, lengthVarName, code.decoderLines, lengthSigned);

            /// Add code for the decode method
            code.decoderLines.add("byte[] byteArray = new byte[(int)" + lengthVarName + "];");
            JavaCode.decodeByteArrayLines("byteArray", "(int) " + lengthVarName, code.decoderLines, false);
            code.decoderLines.add(dataVarName + " = ByteBuffer.allocate((int)" + lengthVarName + ").order(ByteOrder.LITTLE_ENDIAN);");
            code.decoderLines.add(dataVarName + ".put(byteArray);");

        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            code.decoderLines.add("");
            CSharpCode.generateDecoderLines(lengthType, lengthVarName, code.decoderLines);

            /// Add code for the decode method
            code.decoderLines.add("");
            code.decoderLines.add(dataVarName + " = null;");
            code.decoderLines.add("");
            code.decoderLines.add("if (" + lengthVarName + " > 0)");
            code.decoderLines.add("{");
            code.decoderLines.add("\t" + dataVarName + " = new byte[(int)" + lengthVarName + "];");           
            code.decoderLines.add("\t" + dataVarName + " = JausUtils.getFromBuffer(bytes, pos, (int)" + lengthVarName + ", true);");
            
            code.decoderLines.add("\tpos += (int)" + lengthVarName + ";");
            code.decoderLines.add("}");
        }
    }

    private void generateFieldGetLengthMethod(CodeLines code) {
        methodCode.clear();
        methodParam.clear();

        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            /// Add getLength() method
            code.publicMethods.add(CppCode.createMethodDeclaration("const " + lengthType, "get", "length", methodParam, true));

            methodCode.add("return " + lengthVarName + ";");
            code.methods.addAll(CppCode.createMethodDefinition("const " + lengthType, fullClassName + "::get", "length", methodParam, methodCode, true));
        } else if (codeType == CodeLines.CodeType.JAVA) {
            /// Add getLength() method
            methodCode.add("return " + lengthVarName + ";");
            code.methods.addAll(JavaCode.createMethodDefinition("public " + lengthType, "get", "length", methodParam, methodCode, true));
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            /// Add getLength() method
            methodCode.add("return " + lengthVarName + ";");
            code.methods.addAll(CSharpCode.createMethodDefinition("public " + lengthType, "get", "length", methodParam, methodCode, true));
        }
    }

    private void generateFieldGetDataMethod(CodeLines code) {
        methodCode.clear();
        methodParam.clear();

        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            /// Add getData() method
            code.publicMethods.add(CppCode.createMethodDeclaration("const unsigned char", "*get", "data", null, true));

            methodCode.add("return " + dataVarName + ";");
            code.methods.addAll(CppCode.createMethodDefinition("const unsigned char", "*" + fullClassName + "::get", "data", methodParam, methodCode, true));
        } else if (codeType == CodeLines.CodeType.JAVA) {
            /// Add getData() method
            methodCode.add(dataVarName + ".rewind();");
            methodCode.add("ByteBuffer ret = ByteBuffer.allocate(" + dataVarName + ".array().length);");
            methodCode.add("ret.order(ByteOrder.LITTLE_ENDIAN);");
            methodCode.add("ret.put(" + dataVarName + ");");
            methodCode.add("return ret;");
            code.methods.addAll(JavaCode.createMethodDefinition("public ByteBuffer", "get", "data", methodParam, methodCode, true));
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            /// Add getData() method
            methodCode.add("return " + dataVarName + ";");
            code.methods.addAll(CSharpCode.createMethodDefinition("public byte[] ", "get", "data", methodParam, methodCode, true));
        }
    }

    private void generateFieldSetMethod(CodeLines code) {
        methodCode.clear();
        methodParam.clear();

        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            /// Add the set() method
            methodParam.add("const " + lengthType + " &length");
            methodParam.add("const unsigned char *data");
            code.publicMethods.add(CppCode.createMethodDeclaration("int", "set", null, methodParam, false));

            methodCode.add(lengthVarName + " = length;");
            methodCode.add("");
            methodCode.add("delete[] " + dataVarName + ";");
            methodCode.add(dataVarName + " = NULL;");
            methodCode.add("");
            methodCode.add("if (" + lengthVarName + " > 0)");
            methodCode.add("{");
            methodCode.add("\t" + dataVarName + " = new unsigned char[length];");
            methodCode.add("\tmemcpy(" + dataVarName + ", data, length);");
            methodCode.add("}");
            methodCode.add("");
			// Create framework for parent reference
			methodCode.add(CppCode.getParentReferenceSetParentPVLine());
            methodCode.add("return 0;");
            code.methods.addAll(CppCode.createMethodDefinition("int", fullClassName + "::set", null, methodParam, methodCode, false));
        } else if (codeType == CodeLines.CodeType.JAVA) {
            /// Add the set() method
            methodParam.add(lengthType + " length");
            methodParam.add("ByteBuffer data");

            methodCode.add(lengthVarName + " = length;");
            methodCode.add("");
            methodCode.add(dataVarName + ".clear();");
            methodCode.add("");
            methodCode.add("if (" + lengthVarName + " > 0)");
            methodCode.add("{");
            methodCode.add("\tdata.rewind();");
            methodCode.add("\t" + dataVarName + " = ByteBuffer.allocate(data.array().length);");
            methodCode.add("\t" + dataVarName + ".order(ByteOrder.LITTLE_ENDIAN);");
            methodCode.add("\t" + dataVarName + ".put(data);");
            methodCode.add("}");
            methodCode.add("");
            // Create framework for parent reference
			methodCode.add(JavaCode.getParentReferenceSetParentPVLine());
            code.methods.addAll(JavaCode.createMethodDefinition("public void", "set", null, methodParam, methodCode, false));
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            /// Add the set() method
            methodParam.add(lengthType + " length");
            methodParam.add("byte[] data");

            methodCode.add(lengthVarName + " = length;");
            methodCode.add("");
            methodCode.add(dataVarName + " = null;");
            methodCode.add("");
            methodCode.add("if (" + lengthVarName + " > 0)");
            methodCode.add("{");
            methodCode.add("\t" + dataVarName + " = new byte[length];");
            methodCode.add("\tfor(int i = 0; i< " + lengthVarName + "; i++)");
            methodCode.add("\t\t" + dataVarName + "[i] = data[i];");
            methodCode.add("}");
            methodCode.add("");
            // Create framework for parent reference
			methodCode.add(CSharpCode.getParentReferenceSetParentPVLine());
            code.methods.addAll(CSharpCode.createMethodDefinition("public void", "set", null, methodParam, methodCode, false));
        }
    }

    private void generateFieldOverloadAssignmentMethod(CodeLines code) {
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            /// Overload the operator=
            code.assignmentLines.add("this->" + lengthVarName + " = value." + lengthVarName + ";");
            code.assignmentLines.add("");
            code.assignmentLines.add("delete[] " + dataVarName + ";");
            code.assignmentLines.add(dataVarName + " = NULL;");
            code.assignmentLines.add("");
            code.assignmentLines.add("if (" + lengthVarName + " > 0)");
            code.assignmentLines.add("{");
            code.assignmentLines.add("\t" + dataVarName + " = new unsigned char[this->" + lengthVarName + "];");
            code.assignmentLines.add("\tmemcpy(this->" + dataVarName + ", value." + dataVarName + ", this->" + lengthVarName + ");");
            code.assignmentLines.add("}");
            /// Return value is added automatically
        } else if (codeType == CodeLines.CodeType.JAVA) {
            /// Overload the operator=
            code.assignmentLines.add("this." + lengthVarName + " = value." + lengthVarName + ";");
            code.assignmentLines.add("");
            code.assignmentLines.add(dataVarName + ".clear();");
            code.assignmentLines.add("");
            code.assignmentLines.add("if (" + lengthVarName + " > 0)");
            code.assignmentLines.add("{");
            code.assignmentLines.add("\tthis." + dataVarName + ".put(value." + dataVarName + ");");
            code.assignmentLines.add("}");
            /// Return value is added automatically
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            /// Overload the operator=
            code.assignmentLines.add("this." + lengthVarName + " = value." + lengthVarName + ";");
            code.assignmentLines.add("");
            code.assignmentLines.add(dataVarName + " = null;");
            code.assignmentLines.add("");
            code.assignmentLines.add("if (" + lengthVarName + " > 0)");
            code.assignmentLines.add("{");
            code.assignmentLines.add("\t" + dataVarName + " = new byte[(int)" + lengthVarName + "];");
            code.assignmentLines.add("\tthis." + dataVarName + " = value." + dataVarName + ";");
            code.assignmentLines.add("}");
            /// Return value is added automatically
        }
    }

    private void generateFieldOverloadIsEqualMethod(CodeLines code) {
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            /// Overload operator==
            code.equalLines.add("if (this->" + lengthVarName + " != value." + lengthVarName + ")");
            code.equalLines.add("{");
            code.equalLines.add("\treturn false;");
            code.equalLines.add("}");
            code.equalLines.add("");
            code.equalLines.add("if ((this->" + dataVarName + " != NULL) && (value." + dataVarName + "!= NULL))");
            code.equalLines.add("{");
            code.equalLines.add("\tif (memcmp(this->" + dataVarName + ", value." + dataVarName + ", this->" + lengthVarName + ") != 0)");
            code.equalLines.add("\t{");
            code.equalLines.add("\t\treturn false;");
            code.equalLines.add("\t}");
            code.equalLines.add("}");
            code.equalLines.add("// This case should never be true since it should not be possible");
            code.equalLines.add("// for the two variables to have equal lengths but one has no data.");
            code.equalLines.add("// This check is placed here as a secondary check.");
            code.equalLines.add("else if ((this->" + dataVarName + " != NULL) || (value." + dataVarName + " != NULL))");
            code.equalLines.add("{");
            code.equalLines.add("\treturn false;");
            code.equalLines.add("}");
            code.equalLines.add("");
            code.equalLines.add("return true;");
        } else if (codeType == CodeLines.CodeType.JAVA) {
            /// add isEquals method
            code.equalLines.add("if (this." + lengthVarName + " != value.get" + lengthVarName.substring(lengthVarName.indexOf(JavaCode.VARIABLE_PREFIX) + 2) + "())");
            code.equalLines.add("{");
            code.equalLines.add("\treturn false;");
            code.equalLines.add("}");
            code.equalLines.add("");
            code.equalLines.add("if ((this." + dataVarName + " != null) && (value." + dataVarName + "!= null))");
            code.equalLines.add("{");
            code.equalLines.add("\tif(!Arrays.equals(this." + dataVarName + ".array(), value." + dataVarName + ".array()))");
            code.equalLines.add("\t{");
            code.equalLines.add("\t\treturn false;");
            code.equalLines.add("\t}");
            code.equalLines.add("}");
            code.equalLines.add("// This case should never be true since it should not be possible");
            code.equalLines.add("// for the two variables to have equal lengths but one has no data.");
            code.equalLines.add("// This check is placed here as a secondary check.");
            code.equalLines.add("else if ((this." + dataVarName + " != null) || (value." + dataVarName + " != null))");
            code.equalLines.add("{");
            code.equalLines.add("\treturn false;");
            code.equalLines.add("}");
            code.equalLines.add("");
            code.equalLines.add("return true;");
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            /// add isEquals method
            code.equalLines.add("if (this." + lengthVarName + " != value." + lengthVarName + ")");
            code.equalLines.add("{");
            code.equalLines.add("\treturn false;");
            code.equalLines.add("}");
            code.equalLines.add("");
            code.equalLines.add("if ((this." + dataVarName + " != null) && (value." + dataVarName + "!= null))");
            code.equalLines.add("{");
            code.equalLines.add("\tfor(int i = 0; i < value." + lengthVarName + "; i++)");
            code.equalLines.add("\t{");
            code.equalLines.add("\t\tif (this." + dataVarName + "[i] != value." + dataVarName + "[i])");
            code.equalLines.add("\t\t{");
            code.equalLines.add("\t\t\treturn false;");
            code.equalLines.add("\t\t}");
            code.equalLines.add("\t}");
            code.equalLines.add("}");
            code.equalLines.add("// This case should never be true since it should not be possible");
            code.equalLines.add("// for the two variables to have equal lengths but one has no data.");
            code.equalLines.add("// This check is placed here as a secondary check.");
            code.equalLines.add("else if ((this." + dataVarName + " != null) || (value." + dataVarName + " != null))");
            code.equalLines.add("{");
            code.equalLines.add("\treturn false;");
            code.equalLines.add("}");
            code.equalLines.add("");
            code.equalLines.add("return true;");
        }
    }

    private void generateFieldOverloadNotEqualMethod(CodeLines code) {
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            /// Overload operator!=
            code.notEqualLines.add("if (this->" + lengthVarName + " == value." + lengthVarName + ")");
            code.notEqualLines.add("{");
            code.notEqualLines.add("\treturn false;");
            code.notEqualLines.add("}");
            code.notEqualLines.add("");
            code.notEqualLines.add("if ((this->m_Data != NULL) && (value.m_Data != NULL))");
            code.notEqualLines.add("{");
            code.notEqualLines.add("\tif (memcmp(this->" + dataVarName + ", value." + dataVarName + ", this->" + lengthVarName + ") == 0)");
            code.notEqualLines.add("\t{");
            code.notEqualLines.add("\t\treturn false;");
            code.notEqualLines.add("\t}");
            code.notEqualLines.add("}");
            code.notEqualLines.add("// This case should never be true since length should be equal but is");
            code.notEqualLines.add("// placed here as a secondary check");
            code.notEqualLines.add("else if ((this->m_Data == NULL) && (value.m_Data == NULL))");
            code.notEqualLines.add("{");
            code.notEqualLines.add("\treturn false;");
            code.notEqualLines.add("}");
            code.notEqualLines.add("");
            code.notEqualLines.add("return true;");
        } else if (codeType == CodeLines.CodeType.JAVA) {
            /// Overload operator!=
            code.notEqualLines.add("return !(this.isEqual(value));");
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            /// Overload operator!=
            code.notEqualLines.add("return !(this.isEqual(value));");
        }
    }

    /**
     * Generate instance
     * @param pvIndex
     * @param code
     */
    private void generateSingleInstance(int pvIndex, CodeLines code) {
        /*
         * Create code lines for the encode, decode Methods
         * Create the getMethod and setMethod
         * This has to be done after the class wrapper has been added
         */

        // create variable
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            code.protectedAttributes.add(CppCode.createVariableDeclaration(shortClassName, shortClassName, false));
            CppCode.addParentReferenceConstructorSetParent(code, varName);
        } else if (codeType == CodeLines.CodeType.JAVA) {
			code.protectedAttributes.add(JavaCode.createVariableDeclaration("protected " + shortClassName, shortClassName, false));
            code.constructorLines.add("\t" + varName + " = new " + shortClassName + "();");
            JavaCode.addParentReferenceConstructorSetParent(code, varName);
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
			code.protectedAttributes.add(CSharpCode.createVariableDeclaration("protected " + shortClassName, shortClassName, false));
            code.constructorLines.add("\t" + varName + " = new " + shortClassName + "();");
            CSharpCode.addParentReferenceConstructorSetParent(code, varName);
        }


        generateInstanceGetSizeMethod(code, pvIndex);
        generateInstanceIsValidMethod(code, pvIndex);

        generateInstanceEncodeMethod(code, pvIndex);
        generateInstanceDecodeMethod(code, pvIndex);

        generateInstanceGetMethod(code);
        generateInstanceSetMethod(code, pvIndex);
    }

    private void generateInstanceGetSizeMethod(CodeLines code, int pvIndex) {
        methodCode.clear();

        /// GetSize method
        /// only add size of array if optional is true
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            methodCode.add("size += " + varName + ".getSize();");
            if (vlField.isOptional()) {
                methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
            }
        } else if (codeType == CodeLines.CodeType.JAVA) {
            methodCode.add("size += " + varName + ".getSize();");
            if (vlField.isOptional()) {
                methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
            }
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            methodCode.add("size += " + varName + ".getSize();");
            if (vlField.isOptional()) {
                methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        code.sizeLines.addAll(methodCode);
    }

    private void generateInstanceIsValidMethod(CodeLines code, int pvIndex) {
        /// is[Field]valid added if optional
        /// return true if field is set in presence vector
        if (vlField.isOptional()) {
            methodCode.clear();

            if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
                code.publicMethods.add(CppCode.createMethodDeclaration("bool", "is", shortClassName + "Valid", null, true));

                methodCode.add("return true;");
                methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                methodCode.add("return false;");
                code.methods.addAll(CppCode.createMethodDefinition("bool", parentClassName + "::is", shortClassName + "Valid", null, methodCode, true));
            } else if (codeType == CodeLines.CodeType.JAVA) {
                methodCode.add("return true;");
                methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                methodCode.add("return false;");
                code.methods.addAll(JavaCode.createMethodDefinition("public boolean", "is", shortClassName + "Valid", null, methodCode, true));
            } else if (codeType == CodeLines.CodeType.C_SHARP) {
                methodCode.add("return true;");
                methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
                methodCode.add("return false;");
                code.methods.addAll(CSharpCode.createMethodDefinition("public bool", "is", shortClassName + "Valid", null, methodCode, true));
            }
        }
    }

    private void generateInstanceEncodeMethod(CodeLines code, int pvIndex) {
        methodCode.clear();

        /* 	m_VaribleLengthField1.encode(bytes + pos);
         *	pos += m_VaribleLengthField1.getSize();
         */
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            methodCode.add(varName + ".encode(bytes + pos);");
            methodCode.add("pos += " + varName + ".getSize();");
            if (vlField.isOptional()) {
                methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
            }
        } else if (codeType == CodeLines.CodeType.JAVA) {
            methodCode.add(varName + ".encode(bytes, pos);");
            methodCode.add("pos += " + varName + ".getSize();");
            if (vlField.isOptional()) {
                methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
            }
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            methodCode.add(varName + ".encode(bytes, pos);");
            methodCode.add("pos += " + varName + ".getSize();");
            if (vlField.isOptional()) {
                methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }

        /// If the encoder lines are not empty then put a line to increase readability
        if (!code.encoderLines.isEmpty()) {
            code.encoderLines.add("");
        }
        code.encoderLines.addAll(methodCode);
    }

    private void generateInstanceDecodeMethod(CodeLines code, int pvIndex) {
        methodCode.clear();

        /* 	m_VaribleLengthField1.decode(bytes + pos);
         *	pos += m_VaribleLengthField1.getSize();
         */
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            methodCode.add(varName + ".decode(bytes + pos);");
            methodCode.add("pos += " + varName + ".getSize();");
            if (vlField.isOptional()) {
                methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
            }
        } else if (codeType == CodeLines.CodeType.JAVA) {
            methodCode.add(varName + ".decode(bytes, pos);");
            methodCode.add("pos += " + varName + ".getSize();");
            if (vlField.isOptional()) {
                methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
            }
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            methodCode.add(varName + ".decode(bytes, pos);");
            methodCode.add("pos += " + varName + ".getSize();");
            if (vlField.isOptional()) {
                methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }

        /// If the decoder lines are not empty then put a line to increase readability
        if (!code.decoderLines.isEmpty()) {
            code.decoderLines.add("");
        }
        code.decoderLines.addAll(methodCode);
    }

    private void generateInstanceGetMethod(CodeLines code) {
        methodParam.clear();
        methodCode.clear();

        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            ///  Generate getMethod Declaration and Definition
            code.publicMethods.add(CppCode.createMethodDeclaration(shortClassName + "* const", "get", shortClassName, methodParam, false));

            methodCode.add("return &" + varName + ";");
            code.methods.addAll(CppCode.createMethodDefinition(fullClassName + "* const", parentClassName + "::get", shortClassName, methodParam, methodCode, false));
        } else if (codeType == CodeLines.CodeType.JAVA) {
            ///  Generate getMethod Definition
            methodCode.add("return " + varName + ";");
            code.methods.addAll(JavaCode.createMethodDefinition("public " + fullClassName, "get", shortClassName, methodParam, methodCode, false));
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            ///  Generate getMethod Definition
            methodCode.add("return " + varName + ";");
            code.methods.addAll(CSharpCode.createMethodDefinition("public " + fullClassName, "get", shortClassName, methodParam, methodCode, false));
        }
    }

    private void generateInstanceSetMethod(CodeLines code, int pvIndex) {
        methodParam.clear();
        methodCode.clear();

        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            ///  Generate setMethod Declaration and Definition
            methodParam.add("const " + shortClassName + " &value");
            code.publicMethods.add(CppCode.createMethodDeclaration("int", "set", shortClassName, methodParam, false));

            methodCode.add(varName + " = value;");
            if (vlField.isOptional()) {
                methodCode.add("setPresenceVector(" + pvIndex + ");");
            }
            // Create framework for parent reference
            methodCode.add(CppCode.getParentReferenceSetParentPVLine());
            methodCode.add("return 0;");
            code.methods.addAll(CppCode.createMethodDefinition("int", parentClassName + "::set", shortClassName, methodParam, methodCode, false));
        } else if (codeType == CodeLines.CodeType.JAVA) {
            ///  Generate setMethod Definition
            methodParam.add(shortClassName + " value");

            methodCode.add(varName + " = value;");
            if (vlField.isOptional()) {
                methodCode.add("setPresenceVector(" + pvIndex + ");");
            }
            // Create framework for parent reference
            methodCode.add(JavaCode.getParentReferenceSetParentPVLine());
            code.methods.addAll(JavaCode.createMethodDefinition("public void", "set", shortClassName, methodParam, methodCode, false));
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            ///  Generate setMethod Definition
            methodParam.add(shortClassName + " value");

            methodCode.add(varName + " = value;");
            if (vlField.isOptional()) {
                methodCode.add("setPresenceVector(" + pvIndex + ");");
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
     * Generate array
     * @param pvIndex
     * @param code
     */
    private void generateArray(int pvIndex, CodeLines code) {
        List<String> paramCode = new ArrayList<String>();

        List<String> getMethodCode = new ArrayList<String>();
        List<String> setMethodCode = new ArrayList<String>();

        String posCalc = "";	// The calculation for the position within an 1-dimension array from the n-dimension array
        String prevCalc = "";	// The previous calculation for the position within an 1-dimension array from the n-dimension array
        int arraySize = 1;

        paramCode.clear();
        for (int i = 0; i < dimList.size(); i++) {
            Dimension dim = dimList.get(i);
            String dimName = "";


            if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
                dimName = Util.upperCaseFirstLetter(dim.getName());
                String dimSizeName = dimName + "Size";
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

                setMethodCode.add("if (" + dimName + " >= " + dim.getSize() + ")");
                setMethodCode.add("{");
                setMethodCode.add("\treturn 1;");
                setMethodCode.add("}");
                setMethodCode.add("");
            } else if (codeType == CodeLines.CodeType.JAVA) {
                dimName = Util.upperCaseFirstLetter(dim.getName());
                String dimSizeName = dimName + "Size";
                String dimVarName = JavaCode.getVariableName(dimSizeName);

                code.protectedAttributes.add(JavaCode.createVariableDeclaration("protected long", dimSizeName, false));
                code.constructorLines.add(dimVarName + " = " + dim.getSize() + ";");

                ///  Generate getMethod Definition
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

                setMethodCode.add("if (" + dimName + " >= " + dim.getSize() + ")");
                setMethodCode.add("{");
                setMethodCode.add("\treturn 1;");
                setMethodCode.add("}");
                setMethodCode.add("");
            } else if (codeType == CodeLines.CodeType.C_SHARP) {
                dimName = Util.upperCaseFirstLetter(dim.getName());
                String dimSizeName = dimName + "Size";
                String dimVarName = CSharpCode.getVariableName(dimSizeName);

                code.protectedAttributes.add(CSharpCode.createVariableDeclaration("int", dimSizeName, true));
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

                setMethodCode.add("if (" + dimName + " >= " + dim.getSize() + ")");
                setMethodCode.add("{");
                setMethodCode.add("\treturn 1;");
                setMethodCode.add("}");
                setMethodCode.add("");
            }


            int dimSize = Integer.parseInt(dim.getSize());
            arraySize = arraySize * dimSize;

            if (i > 0) {
                prevCalc += " * ";
                posCalc += " + (" + prevCalc + dimName + ")";
            } else {
                posCalc = dimName;
            }

            prevCalc += dim.getSize();
        }


        /*
         * Create code lines for the encode, decode Methods
         * Create the getMethod and setMethod
         * This has to be done after the class wrapper has been added
         */

        /// Add all the required code for storing and accessing the array values
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            /*
             * Create framework for parent reference
             */
            CppCode.addParentReferenceConstructorSetParrentInArray(code, varName, arraySize);

            code.protectedAttributes.add(CppCode.createVariableDeclaration(shortClassName, shortClassName + "[" + arraySize + "]", false));
            code.constructorLines.add("/// No Initialization of " + varName + " necessary.");

        } else if (codeType == CodeLines.CodeType.JAVA) {
            code.protectedAttributes.add(JavaCode.createVariableDeclaration("protected " + shortClassName + "[]", shortClassName + " = new " + shortClassName + "[" + arraySize + "]", false));
            code.constructorLines.add(varName + " = new " + shortClassName + "[" + arraySize + "];");

            initArrayInstances(code, arraySize, varName, shortClassName);
            /*
             * Create framework for parent reference
             */
            JavaCode.addParentReferenceConstructorSetParrentInArray(code, varName, arraySize);

        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            code.protectedAttributes.add(CSharpCode.createVariableDeclaration("protected " + shortClassName + "[]", shortClassName + " = new " + shortClassName + "[" + arraySize + "]", true));
            code.constructorLines.add(varName + " = new " + shortClassName + "[" + arraySize + "];");

            initArrayInstances(code, arraySize, varName, shortClassName);
            /*
             * Create framework for parent reference
             */
            CSharpCode.addParentReferenceConstructorSetParrentInArray(code, varName, arraySize);
        }

        generateArrayGetSizeMethod(code, arraySize, pvIndex);

        generateArrayIsValidMethod(code, pvIndex);

        generateArrayEncodeMethod(code, arraySize);
        generateArrayDecodeMethod(code, arraySize);

        generateArrayGetMethod(code, posCalc, paramCode, getMethodCode);
        generateArraySetMethod(code, posCalc, paramCode, setMethodCode);

        generateArrayOverloadIsEqualMethod(code, arraySize);
        generateArrayOverloadNotEqualMethod(code, arraySize);
        generateArrayOverloadAssignmentMethod(code, arraySize);
    }

    private void generateArrayGetSizeMethod(CodeLines code, int arraySize, int pvIndex) {
        methodCode.clear();

        /// GetSize method
        /// only add size of array if optional is true
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
			methodCode.clear();
			methodCode.add("for (unsigned int i = 0; i < "  + arraySize + "; i++)");
			methodCode.add("{");
			methodCode.add("\tsize += " + varName + "[i].getSize();");
			methodCode.add("}");
            if (vlField.isOptional()) {
                methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
            }
        } else if (codeType == CodeLines.CodeType.JAVA) {
            methodCode.add("for (int i = 0; i< " + arraySize + "; i++)");
            methodCode.add("{");
            methodCode.add("\tsize += " + varName + "[i].getSize();");
            methodCode.add("}");
            if (vlField.isOptional()) {
                methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
            }
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            methodCode.add("for (int i = 0; i< " + arraySize + "; i++)");
            methodCode.add("{");
            methodCode.add("\tsize += " + varName + "[i].getSize();");
            methodCode.add("}");
            if (vlField.isOptional()) {
                methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
            }
        }
        code.sizeLines.addAll(methodCode);
    }

    private void generateArrayIsValidMethod(CodeLines code, int pvIndex) {
        /// is[Field]valid added if optional
        /// return true if field is set in presence vector
        if (vlField.isOptional()) {
            methodCode.clear();

            if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
                code.publicMethods.add(CppCode.createMethodDeclaration("bool", "is", shortClassName + "Valid", null, true));

                methodCode.add("return true;");
                methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
                methodCode.add("return false;");
                code.methods.addAll(CppCode.createMethodDefinition("bool", parentClassName + "::is", shortClassName + "Valid", null, methodCode, true));
            } else if (codeType == CodeLines.CodeType.JAVA) {
                methodCode.add("return true;");
                methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
                methodCode.add("return false;");
                code.methods.addAll(JavaCode.createMethodDefinition("public boolean", "is", shortClassName + "Valid", null, methodCode, true));
            } else if (codeType == CodeLines.CodeType.C_SHARP) {
                methodCode.add("return true;");
                methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
                methodCode.add("return false;");
                code.methods.addAll(CSharpCode.createMethodDefinition("public bool", "is", shortClassName + "Valid", null, methodCode, true));
            }
        }
    }

    private void generateArrayEncodeMethod(CodeLines code, int arraySize) {
        methodCode.clear();

        /* for (unsigned int i = 0; i < "arraySize"; i++)
         * {
         * 		m_VaribleLengthField1[i].encode(bytes + pos);
         *		pos += m_VaribleLengthField1[i].getSize();
         * }
         */
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            methodCode.add("for (unsigned int i = 0; i < " + arraySize + "; i++)");
            methodCode.add("{");
            methodCode.add("\t" + varName + "[i].encode(bytes + pos);");
            methodCode.add("\tpos += " + varName + "[i].getSize();");
            methodCode.add("}");
        } else if (codeType == CodeLines.CodeType.JAVA) {
            methodCode.add("for (int i = 0; i < " + arraySize + "; i++)");
            methodCode.add("{");
            methodCode.add("\t" + varName + "[i].encode(bytes, pos);");
            methodCode.add("\tpos += " + varName + "[i].getSize();");
            methodCode.add("}");
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            methodCode.add("for (int i = 0; i < " + arraySize + "; i++)");
            methodCode.add("{");
            methodCode.add("\t" + varName + "[i].encode(bytes, pos);");
            methodCode.add("\tpos += " + varName + "[i].getSize();");
            methodCode.add("}");
        }


        /// If the encoder lines are not empty then put a line to increase readability
        if (!code.encoderLines.isEmpty()) {
            code.encoderLines.add("");
        }
        code.encoderLines.addAll(methodCode);
    }

    private void generateArrayDecodeMethod(CodeLines code, int arraySize) {
        methodCode.clear();
        /* for (unsigned int i = 0; i < "arraySize"; i++)
         * {
         * 		m_VaribleLengthField1[i].decode(bytes + pos);
         *		pos += m_VaribleLengthField1[i].getSize();
         * }
         */
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            methodCode.add("for (unsigned int i = 0; i < " + arraySize + "; i++)");
            methodCode.add("{");
            methodCode.add("\t" + varName + "[i].decode(bytes + pos);");
            methodCode.add("\tpos += " + varName + "[i].getSize();");
			methodCode.add("}");
        } else if (codeType == CodeLines.CodeType.JAVA) {
            methodCode.add("for (int i = 0; i < " + arraySize + "; i++)");
            methodCode.add("{");
            methodCode.add("\t" + varName + "[i].decode(bytes, pos);");
            methodCode.add("\tpos += " + varName + "[i].getSize();");
			methodCode.add("}");
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            methodCode.add("for (int i = 0; i < " + arraySize + "; i++)");
            methodCode.add("{");
            methodCode.add("\t" + varName + "[i].decode(bytes, pos);");
            methodCode.add("\tpos += " + varName + "[i].getSize();");
			methodCode.add("}");
        }


        /// If the decoder lines are not empty then put a line to increase readability
        if (!code.decoderLines.isEmpty()) {
            code.decoderLines.add("");
        }
        code.decoderLines.addAll(methodCode);
    }

    private void generateArrayGetMethod(CodeLines code, String posCalc, List<String> paramCode, List<String> getMethodCode) {
        methodCode.clear();

        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            ///  Generate getMethod Declaration and Definition
            code.publicMethods.add(CppCode.createMethodDeclaration(shortClassName + "* const", "get", shortClassName, paramCode, false));

            methodCode.add("unsigned int index = " + posCalc + ";");
            methodCode.add("");
            methodCode.addAll(getMethodCode);
            methodCode.add("return &" + varName + "[index];");
            code.methods.addAll(CppCode.createMethodDefinition(fullClassName + "* const", parentClassName + "::get", shortClassName, paramCode, methodCode, false));
        } else if (codeType == CodeLines.CodeType.JAVA) {
            ///  Generate getMethod Definition
            methodCode.add("int index = (int)" + posCalc + ";");
            methodCode.add("");
            methodCode.addAll(getMethodCode);
            methodCode.add("return " + varName + "[(int)index];");
            code.methods.addAll(JavaCode.createMethodDefinition("public " + fullClassName, "get", shortClassName, paramCode, methodCode, false));
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            ///  Generate getMethod Definition
            methodCode.add("int index = " + posCalc + ";");
            methodCode.add("");
            methodCode.addAll(getMethodCode);
            methodCode.add("return " + varName + "[(int) index];");
            code.methods.addAll(CSharpCode.createMethodDefinition("public " + fullClassName, "get", shortClassName, paramCode, methodCode, false));
        }
    }

    private void generateArraySetMethod(CodeLines code, String posCalc, List<String> paramCode, List<String> setMethodCode) {
        methodCode.clear();

        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            ///  Generate setMethod Declaration and Definition
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
        } else if (codeType == CodeLines.CodeType.JAVA) {
            ///  Generate setMethod Definition
            paramCode.add(shortClassName + " value");

            methodCode.add("long index = " + posCalc + ";");
            methodCode.add("");
            methodCode.addAll(setMethodCode);
            methodCode.add(varName + "[(int)index] = value;");
            // Create framework for parent reference
			methodCode.add(JavaCode.getParentReferenceSetParentPVLine());

            methodCode.add("return 0;");
            code.methods.addAll(JavaCode.createMethodDefinition("public int", "set", shortClassName, paramCode, methodCode, false));
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            ///  Generate setMethod Definition
            paramCode.add(shortClassName + " value");

            methodCode.add("long index = " + posCalc + ";");
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
            // add isEqual method code.
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
