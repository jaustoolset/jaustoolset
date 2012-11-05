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

import java.util.List;
import java.util.ArrayList;

public class ListGenerator {

    private CodeLines.CodeType codeType;
    private org.jts.jsidl.binding.List list;
    private boolean isList;
    private String countFieldType;
    private boolean countFieldSigned;

    public ListGenerator(CodeLines.CodeType codeType, org.jts.jsidl.binding.List list) {
        this.codeType = codeType;
        this.list = list;
        this.isList = false;
    }

    public ListGenerator(CodeLines.CodeType codeType, org.jts.jsidl.binding.List list, boolean isList, String countFieldType) {
        this.codeType = codeType;
        this.list = list;
        this.isList = isList;
        this.countFieldType = countFieldType;
        countFieldSigned = JavaCode.getVariableSign(countFieldType);
    }

    /**
     * This method generates the code for creating a record.
     * The generate Record will be placed within its own class which
     * is nested within the encapsulating class.
     * @param code
     */
    public void run(String parentClassName, int pvIndex, CodeLines code) {
        String shortClassName = list.getName();
        String fullClassName = "";

        /*
         * Create framework for parent reference
         */
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            fullClassName = parentClassName + "::" + shortClassName;
            CppCode.addParentReference(code, fullClassName, pvIndex, list.isOptional());
        } else if (codeType == CodeLines.CodeType.JAVA) {
            fullClassName = parentClassName + "." + shortClassName;
            JavaCode.addParentReference(code, fullClassName, pvIndex, list.isOptional());
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            fullClassName = parentClassName + "." + shortClassName;
            CSharpCode.addParentReference(code, fullClassName, pvIndex, list.isOptional());
        }

        /* Create the Record Class */
        /***************************/
        generateClass(parentClassName, shortClassName, code);


        if (isList) {
            generateListVector(parentClassName, shortClassName, pvIndex, code);
        } else {
            generateListInstance(parentClassName, shortClassName, pvIndex, code);
        }

        //TESTING 123
        //if(codeType == CodeLines.CodeType.C_SHARP)
        //CSharpCode.addParentReference(code, fullClassName, pvIndex, list.isOptional());

    }


    /**
     *
     * @param parentClassName
     * @param shortClassName
     * @param code
     */
    private void generateClass(String parentClassName, String shortClassName, CodeLines code) {
        CodeLines listCode = new CodeLines("", codeType, code.getNameSpace());

        String fullClassName = "";
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            fullClassName = parentClassName + "::" + shortClassName;
        } else if (codeType == CodeLines.CodeType.JAVA || codeType == CodeLines.CodeType.C_SHARP) {
            fullClassName = parentClassName + "." + shortClassName;
        }

        if (list.getDeclaredList() != null) {
            /// TODO: Add support for all other field types
        } else if (list.getDeclaredRecord() != null) {
            /// TODO: Add support for all other field types
        } else if (list.getDeclaredSequence() != null) {
            /// TODO: Add support for all other field types
        } else if (list.getDeclaredVariant() != null) {
            /// TODO: Add support for all other field types
        } else if (list.getList() != null) {
            new ListGenerator(codeType, list.getList(), true, list.getCountField().getFieldTypeUnsigned()).run(fullClassName, 0, listCode);
        } else if (list.getRecord() != null) {
            new RecordGenerator(codeType, list.getRecord(), true, list.getCountField().getFieldTypeUnsigned()).run(fullClassName, 0, listCode, null);
        } else if (list.getSequence() != null) {
            new SequenceGenerator(codeType, list.getSequence(), true, list.getCountField().getFieldTypeUnsigned()).run(fullClassName, 0, listCode);
        } else if (list.getVariant() != null) {
            new VariantGenerator(codeType, list.getVariant(), true, list.getCountField().getFieldTypeUnsigned()).run(fullClassName, 0, listCode);
        } else {
            throw new CodeGeneratorException("RecordGenerator: Unknown Field Encountered: " + list.getClass().getName());
        }
        // drew 3/29/11 : why clear methods here? causes problems when elements where generated before this, removing it...
        //code.publicMethods.clear();

        code.addAll(listCode);

        /// Overload operator==
        code.equalLines.add("");
        code.equalLines.add("return true;");

        /// Overload operator!=
        code.notEqualLines.add("");
        code.notEqualLines.add("return true;");

        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            CppCode.addClassWrapper(fullClassName, code);
        } else if (codeType == CodeLines.CodeType.JAVA) {
            JavaCode.addClassWrapper(fullClassName, code);
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            CSharpCode.addClassWrapper(fullClassName, code);
        }
    }

    private void generateListInstanceCpp(String parentClassName, String shortClassName, int pvIndex, CodeLines code) {
            List<String> methodCode = new ArrayList<String>();
            List<String> methodParam = new ArrayList<String>();

            String fullClassName = "";
            String varName = "";

            fullClassName = parentClassName + "::" + shortClassName;
            varName = CppCode.getVariableName(shortClassName);

            /*
             * Create framework for parent reference
             */
            CppCode.addParentReferenceConstructorSetParent(code, varName);

            code.protectedAttributes.add(CppCode.createVariableDeclaration(shortClassName, shortClassName, false));
            code.constructorLines.add("/// No Initialization of " + varName + " necessary.");

            /// GetSize method
            /// only add size of array if optional is true
            methodCode.add("size += " + varName + ".getSize();");

            if (list.isOptional()) {
                methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
            }

            code.sizeLines.addAll(methodCode);

            /// is[Field]valid added if optional
            /// return true if field is set in presence vector
            if (list.isOptional()) {
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

            if (list.isOptional()) {
                methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
            }

            code.encoderLines.addAll(methodCode);


            /* 	m_Record1.decode(bytes + pos);
             *	pos += m_Record1.getSize();
             */
            methodCode.clear();
            methodCode.add(varName + ".decode(bytes + pos);");
            methodCode.add("pos += " + varName + ".getSize();");

            if (list.isOptional()) {
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

            if (list.isOptional()) {
                methodCode.add("setPresenceVector(" + pvIndex + ");");
            }
            // Create framework for parent reference
            methodCode.add(CppCode.getParentReferenceSetParentPVLine());
            methodCode.add("return 0;");

            code.methods.addAll(CppCode.createMethodDefinition("int", parentClassName + "::set", shortClassName, methodParam, methodCode, false));

            // operator=
            code.assignmentLines.add(varName + " = value." + varName + ";");
            // Create framework for parent reference
            code.assignmentLines.add(CppCode.getParentReferenceVariableSetParent(varName));

            /// Overload operator==
            /// Add code for checking equality
            if (!code.equalLines.isEmpty()) {
                code.equalLines.add("");
            }

            code.equalLines.add("if (" + varName + " != value." + varName + ")");

            code.equalLines.add("{");
            code.equalLines.add("\treturn false;");
            code.equalLines.add("}");

            /// Overload operator!=
            /// Add code checking inequality
            if (!code.notEqualLines.isEmpty()) {
                code.notEqualLines.add("");
            }

            code.notEqualLines.add("if (" + varName + " == value." + varName + ")");
            code.notEqualLines.add("{");
            code.notEqualLines.add("\treturn false;");
            code.notEqualLines.add("}");
    }

    private void generateListInstanceJava(String parentClassName, String shortClassName, int pvIndex, CodeLines code) {
            List<String> methodCode = new ArrayList<String>();
            List<String> methodParam = new ArrayList<String>();

            String fullClassName = "";
            String varName = "";

            fullClassName = parentClassName + "." + shortClassName;
            varName = JavaCode.getVariableName(shortClassName);

            code.protectedAttributes.add(JavaCode.createVariableDeclaration("protected " + shortClassName, shortClassName, false));
            code.constructorLines.add(varName + " = new " + shortClassName + "();");

            /*
             * Create framework for parent reference
             */
            JavaCode.addParentReferenceConstructorSetParent(code, varName);

            /// GetSize method
            /// only add size of array if optional is true
            methodCode.add("size += " + varName + ".getSize();");

            if (list.isOptional()) {
                methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
            }

            code.sizeLines.addAll(methodCode);

            /// is[Field]valid added if optional
            /// return true if field is set in presence vector
            if (list.isOptional()) {
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
            methodCode.add(varName + ".encode(bytes, pos);");
            methodCode.add("pos += " + varName + ".getSize();");

            if (list.isOptional()) {
                methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
            }

            code.encoderLines.addAll(methodCode);


            /* 	m_Record1.decode(bytes + pos);
             *	pos += m_Record1.getSize();
             */
            methodCode.clear();
            methodCode.add(varName + ".decode(bytes, pos);");
            methodCode.add("pos += " + varName + ".getSize();");

            if (list.isOptional()) {
                methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
            }

            code.decoderLines.addAll(methodCode);

            ///  Generate getMethod Declaration and Definition
            methodParam.clear();
            methodCode.clear();

            methodCode.add("return " + varName + ";");
            code.methods.addAll(JavaCode.createMethodDefinition("public " + fullClassName, "get", shortClassName, methodParam, methodCode, false));

            ///  Generate setMethod Declaration and Definition
            methodParam.clear();
            methodCode.clear();

            methodParam.add(shortClassName + " value");
            methodCode.add(varName + " = value;");

            if (list.isOptional()) {
                methodCode.add("setPresenceVector(" + pvIndex + ");");
            }

            // Create framework for parent reference
            methodCode.add(JavaCode.getParentReferenceSetParentPVLine());


            code.methods.addAll(JavaCode.createMethodDefinition("public void", "set", shortClassName, methodParam, methodCode, false));

            // operator=
            code.assignmentLines.add(varName + " = value." + varName + ";");
            // Create framework for parent reference
            code.assignmentLines.add(JavaCode.getParentReferenceVariableSetParent(varName));

            /// Overload operator==
            /// Add code for checking equality
            if (!code.equalLines.isEmpty()) {
                code.equalLines.add("");
            }

            code.equalLines.add("if (!" + varName + ".isEqual(value." + varName + "))");

            code.equalLines.add("{");
            code.equalLines.add("\treturn false;");
            code.equalLines.add("}");

            /// Overload operator!=
            /// Add code checking inequality
            if (!code.notEqualLines.isEmpty()) {
                code.notEqualLines.add("");
            }
            code.notEqualLines.add("if (" + varName + ".isEqual(value." + varName + "))");
            code.notEqualLines.add("{");
            code.notEqualLines.add("\treturn false;");
            code.notEqualLines.add("}");
    }

    private void generateListInstanceCsharp(String parentClassName, String shortClassName, int pvIndex, CodeLines code) {
            List<String> methodCode = new ArrayList<String>();
            List<String> methodParam = new ArrayList<String>();

            String fullClassName = "";
            String varName = "";

            fullClassName = parentClassName + "." + shortClassName;
            varName = CSharpCode.getVariableName(shortClassName);

            code.protectedAttributes.add(CSharpCode.createVariableDeclaration("protected " + shortClassName, shortClassName, false));
            code.constructorLines.add(varName + " = new " + shortClassName + "();");

            /*
             * Create framework for parent reference
             */
            CSharpCode.addParentReferenceConstructorSetParent(code, varName);


            /// GetSize method
            /// only add size of array if optional is true
            methodCode.add("size += " + varName + ".getSize();");

            if (list.isOptional()) {
                methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
            }

            code.sizeLines.addAll(methodCode);

            /// is[Field]valid added if optional
            /// return true if field is set in presence vector
            if (list.isOptional()) {
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

            if (list.isOptional()) {
                methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
            }

            code.encoderLines.addAll(methodCode);


            /* 	m_Record1.decode(bytes + pos);
             *	pos += m_Record1.getSize();
             */
            methodCode.clear();
            methodCode.add(varName + ".decode(bytes, pos);");
            methodCode.add("pos += " + varName + ".getSize();");

            if (list.isOptional()) {
                methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
            }

            code.decoderLines.addAll(methodCode);

            ///  Generate getMethod Declaration and Definition
            methodParam.clear();
            methodCode.clear();

            methodCode.add("return " + varName + ";");
            code.methods.addAll(CSharpCode.createMethodDefinition("public " + fullClassName, "get", shortClassName, methodParam, methodCode, false));

            ///  Generate setMethod Declaration and Definition
            methodParam.clear();
            methodCode.clear();

            methodParam.add(shortClassName + " value");
            methodCode.add(varName + " = value;");

            if (list.isOptional()) {
                methodCode.add("setPresenceVector(" + pvIndex + ");");
            }

            // Create framework for parent reference
            methodCode.add(CSharpCode.getParentReferenceSetParentPVLine());

            code.methods.addAll(CSharpCode.createMethodDefinition("public void", "set", shortClassName, methodParam, methodCode, false));

            // operator=
            code.assignmentLines.add(varName + " = value." + varName + ";");
            // Create framework for parent reference
            code.assignmentLines.add(CSharpCode.getParentReferenceVariableSetParent(varName));

            /// Overload operator==
            /// Add code for checking equality
            if (!code.equalLines.isEmpty()) {
                code.equalLines.add("");
            }

            code.equalLines.add("if (!this." + varName + ".isEqual(value." + varName + "))");

            code.equalLines.add("{");
            code.equalLines.add("\treturn false;");
            code.equalLines.add("}");

            /// Overload operator!=
            /// Add code checking inequality
            if (!code.notEqualLines.isEmpty()) {
                code.notEqualLines.add("");
            }

            code.notEqualLines.add("if (this." + varName + ".isEqual(value." + varName + "))");

            code.notEqualLines.add("{");
            code.notEqualLines.add("\treturn false;");
            code.notEqualLines.add("}");
    }

    private void generateListInstance(String parentClassName, String shortClassName, int pvIndex, CodeLines code) {
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            generateListInstanceCpp(parentClassName, shortClassName, pvIndex, code);
        } else if (codeType == CodeLines.CodeType.JAVA) {
            generateListInstanceJava(parentClassName, shortClassName, pvIndex, code);
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            generateListInstanceCsharp(parentClassName, shortClassName, pvIndex, code);
        }
    }

    private void generateListVectorCpp(String parentClassName, String shortClassName, int pvIndex, CodeLines code) {
        List<String> methodCode = new ArrayList<String>();
        List<String> methodParam = new ArrayList<String>();

        String fullClassName = parentClassName + "::" + shortClassName;
        String varName = "";

        /*
         * Create code lines for the encode, decode Methods
         * Create the getMethod and setMethod
         * This has to be done after the class wrapper has been added
         */

        varName = CppCode.getVariableName(shortClassName);

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
        if (list.isOptional()) {
            methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
        }

        code.sizeLines.addAll(methodCode);

        /*
         * is[Field]valid added if optional
         * return true if field is set in presence vector
         */
        if (list.isOptional()) {
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

        methodCode.add(CppCode.getVariableType(countFieldType) + " size = " + varName + ".size();");
        methodCode.add("memcpy( bytes, &size, sizeof(size));");
        methodCode.add("pos += sizeof(size);");

        methodCode.add("for (int i = 0; i < " + varName + ".size(); i++)");
        methodCode.add("{");
        methodCode.add("\t" + varName + "[i].encode(bytes + pos);");
        methodCode.add("\tpos += " + varName + "[i].getSize();");
        methodCode.add("}");

        if (list.isOptional()) {
            methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
        }

        code.encoderLines.addAll(methodCode);


        /* 	m_Record1.decode(bytes + pos);
         *	pos += m_Record1.getSize();
         */
        methodCode.clear();

        methodCode.add(CppCode.getVariableType(countFieldType) + " size;");
        methodCode.add("memcpy( &size, bytes, sizeof(size));");
        methodCode.add("pos += sizeof(size);");
        methodCode.add(varName + ".resize(size);");

        methodCode.add("for (int i = 0; i < " + varName + ".size(); i++)");
        methodCode.add("{");
        methodCode.add("\t" + varName + "[i].decode(bytes + pos);");
        methodCode.add("\tpos += " + varName + "[i].getSize();");
        methodCode.add("}");
        if (list.isOptional()) {
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
        if (list.isOptional()) {
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
        if (list.isOptional()) {
            methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
        }

        if (!code.equalLines.isEmpty()) {
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
        if (list.isOptional()) {
            methodCode = CppCode.addOptionalWrapper(pvIndex, methodCode);
        }


        if (!code.notEqualLines.isEmpty()) {
            code.notEqualLines.add("");
        }

        code.notEqualLines.addAll(methodCode);
    }

    private void generateListVectorJava(String parentClassName, String shortClassName, int pvIndex, CodeLines code) {
        /*
         * Create code lines for the encode, decode Methods
         * Create the getMethod and setMethod
         * This has to be done after the class wrapper has been added
         */

        List<String> methodCode = new ArrayList<String>();
        List<String> methodParam = new ArrayList<String>();

        String fullClassName = parentClassName + "." + shortClassName;
        String varName = "";

        varName = JavaCode.getVariableName(shortClassName);

        code.protectedAttributes.add(JavaCode.createVariableDeclaration("protected ArrayList<" + shortClassName + ">", shortClassName, false));

        code.constructorLines.add(varName + " = new ArrayList<" + shortClassName + ">();");

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
        if (list.isOptional()) {
            methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
        }

        code.sizeLines.addAll(methodCode);

        /*
         * is[Field]valid added if optional
         * return true if field is set in presence vector
         */
        if (list.isOptional()) {
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

        methodCode.add(JavaCode.getVariableType(countFieldType) + " size = (" + JavaCode.getVariableType(countFieldType) + ") " + varName + ".size();");
        methodCode.add("");
        JavaCode.generateEncoderLines(JavaCode.getVariableType(countFieldType), "size", methodCode, countFieldSigned);
        methodCode.add("");
        methodCode.add("for (int i = 0; i < " + varName + ".size(); i++)");
        methodCode.add("{");
        methodCode.add("\t" + varName + ".get(i).encode(bytes, pos);");
        methodCode.add("\tpos += " + varName + ".get(i).getSize();");
        methodCode.add("}");

        if (list.isOptional()) {
            methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
        }

        code.encoderLines.addAll(methodCode);


        /* 	m_Record1.decode(bytes + pos);
         *	pos += m_Record1.getSize();
         */
        methodCode.clear();

        methodCode.add(JavaCode.getVariableType(countFieldType) + " size;");
        JavaCode.generateDecoderLines(JavaCode.getVariableType(countFieldType), "size", methodCode, countFieldSigned);

        methodCode.add("");
        methodCode.add(varName + " = new ArrayList<" + shortClassName + ">();");
        methodCode.add("for (int i = 0; i <  size; i++)");
        methodCode.add("{");
        methodCode.add("\t" + shortClassName + " item = new " + shortClassName + "();");
        methodCode.add("\titem.decode(bytes, pos);");
        methodCode.add("\t" + varName + ".add(item);");
        methodCode.add("\tpos += item.getSize();");
        methodCode.add("}");

        if (list.isOptional()) {
            methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
        }

        code.decoderLines.addAll(methodCode);


        /// getNumberOfElements()
        methodCode.clear();
        methodParam.clear();

        methodCode.add("return (long) " + varName + ".size();");
        code.methods.addAll(JavaCode.createMethodDefinition("public long", "get", "NumberOfElements", null, methodCode, true));

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
        methodCode.add(JavaCode.getParentReferenceVariableSetParent(varName + ".get(" + varName + ".size()-1)"));
        methodCode.add(JavaCode.getParentReferenceSetParentPVLine());
        code.methods.addAll(JavaCode.createMethodDefinition("public void", "add", "Element", methodParam, methodCode, false));

        /// deleteElement(index)
        methodCode.clear();
        methodParam.clear();

        methodParam.add("int " + "index");
        methodCode.add("if(" + varName + ".size()-1 < index)");
        methodCode.add("{");
        methodCode.add("\treturn;");
        methodCode.add("}");
        methodCode.add("");
        methodCode.add(varName + ".remove(index);");
        code.methods.addAll(JavaCode.createMethodDefinition("public void", "delete", "Element", methodParam, methodCode, false));

        /// deleteLastElement()
        methodCode.clear();
        methodParam.clear();

        methodCode.add(varName + ".remove(" + varName + ".size()-1);");
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
        if (list.isOptional()) {
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
        methodCode.add("\tif (!" + varName + ".get(i).isEqual(value.getElement(i)))");
        methodCode.add("\t{");
        methodCode.add("\t\treturn false;");
        methodCode.add("\t}");
        methodCode.add("}");
        if (list.isOptional()) {
            methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
        }

        if (!code.equalLines.isEmpty()) {
            code.equalLines.add("");
        }

        code.equalLines.addAll(methodCode);

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
        if (list.isOptional()) {
            methodCode = JavaCode.addOptionalWrapper(pvIndex, methodCode);
        }


        if (!code.notEqualLines.isEmpty()) {
            code.notEqualLines.add("");
        }

        code.notEqualLines.addAll(methodCode);

    }

    private void generateListVectorCSharp(String parentClassName, String shortClassName, int pvIndex, CodeLines code) {
        /*
         * Create code lines for the encode, decode Methods
         * Create the getMethod and setMethod
         * This has to be done after the class wrapper has been added
         */
        List<String> methodCode = new ArrayList<String>();
        List<String> methodParam = new ArrayList<String>();

        String fullClassName = parentClassName + "." + shortClassName;
        String varName = "";

        varName = CSharpCode.getVariableName(shortClassName);

        code.protectedAttributes.add(CSharpCode.createVariableDeclaration("protected List<" + shortClassName + ">", shortClassName, false));

        code.constructorLines.add(varName + " = new List<" + shortClassName + ">();");

        /*
         * Create framework for parent reference
         */
        CSharpCode.addParentReferenceConstructorSetParrentInArray(code, varName);

        /*
         * GetSize method
         * only add size of array if optional is true
         */
        methodCode.add("size += JausUtils.getNumBytes(\"" + CSharpCode.getVariableType(countFieldType) + "\");");
        methodCode.add("for (int i = 0; i < " + varName + ".Count; i++)");
        methodCode.add("{");
        methodCode.add("	size += " + varName + "[i].getSize();");
        methodCode.add("}");
        if (list.isOptional()) {
            methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
        }

        code.sizeLines.addAll(methodCode);

        /*
         * is[Field]valid added if optional
         * return true if field is set in presence vector
         */
        if (list.isOptional()) {
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

        methodCode.add(CSharpCode.getVariableType(countFieldType) + " size = (" + CSharpCode.getVariableType(countFieldType) + ") " + varName + ".Count;");
        CSharpCode.generateEncoderLines(CSharpCode.getVariableType(countFieldType), "size", methodCode);
        methodCode.add("for (int i = 0; i < " + varName + ".Count; i++)");
        methodCode.add("{");
        methodCode.add("\t" + varName + "[i].encode(bytes, pos);");
        methodCode.add("\tpos += " + varName + "[i].getSize();");
        methodCode.add("}");

        if (list.isOptional()) {
            methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
        }

        code.encoderLines.addAll(methodCode);


        /* 	m_Record1.decode(bytes + pos);
         *	pos += m_Record1.getSize();
         */
        methodCode.clear();

        methodCode.add(CSharpCode.getVariableType(countFieldType) + " size;");
        CSharpCode.generateDecoderLines(CSharpCode.getVariableType(countFieldType), "size", methodCode);
        methodCode.add(varName + " = new List<" + shortClassName + ">();");
        methodCode.add(shortClassName + " tmp;");

        methodCode.add("for (int i = 0; i < size; i++)");
        methodCode.add("{");
        methodCode.add("\ttmp = new " + shortClassName + "();");
        methodCode.add("\t" + varName + ".Add(tmp);");
        methodCode.add("\t" + varName + "[i].decode(bytes, pos);");
        methodCode.add("\tpos += " + varName + "[i].getSize();");
        methodCode.add("}");
        if (list.isOptional()) {
            methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
        }

        code.decoderLines.addAll(methodCode);


        /// getNumberOfElements()
        methodCode.clear();
        methodParam.clear();

        methodCode.add("return " + varName + ".Count;");
        code.methods.addAll(CSharpCode.createMethodDefinition("public int", "get", "NumberOfElements", null, methodCode, true));



        /// getElement(index)
        methodCode.clear();
        methodParam.clear();


        methodParam.add("int index");
        methodCode.add("return " + varName + "[index];");
        code.methods.addAll(CSharpCode.createMethodDefinition("public " + fullClassName, "get", "Element", methodParam, methodCode, false));


        /// setElement(index,value)
        methodCode.clear();
        methodParam.clear();

        methodParam.add("int index");
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

        methodCode.add(varName + ".RemoveAt(" + varName + ".Count-1);");
        methodCode.add("return 0;");
        code.methods.addAll(CSharpCode.createMethodDefinition("public int", "delete", "LastElement", methodParam, methodCode, false));

        /// Overload the operator=
        /// Add code to the assignment lines of code
        methodCode.clear();

        methodCode.add(varName + ".Clear();");
        methodCode.add("");
        methodCode.add("for (int i = 0; i < value." + varName + ".Count; i++)");
        methodCode.add("{");
        methodCode.add("\t" + varName + ".Add(value." + varName + "[i]);");
        // Create framework for parent reference
        methodCode.add("\t" + CSharpCode.getParentReferenceVariableSetParentInVector(varName));
        methodCode.add("}");
        if (list.isOptional()) {
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
        methodCode.add("for (int i = 0; i < value." + varName + ".Count; i++)");
        methodCode.add("{");
        methodCode.add("\tif (!this." + varName + "[i].isEqual(value." + varName + "[i]))");
        methodCode.add("\t{");
        methodCode.add("\t\treturn false;");
        methodCode.add("\t}");
        methodCode.add("}");
        if (list.isOptional()) {
            methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
        }

        if (!code.equalLines.isEmpty()) {
            code.equalLines.add("");
        }

        code.equalLines.addAll(methodCode);

        /// Overload operator!=
        /// Add code checking inequality
        methodCode.clear();

        methodCode.add("for (int i = 0; i < " + varName + ".Count; i++)");
        methodCode.add("{");
        methodCode.add("\tif (!this." + varName + "[i].notEquals(value." + varName + "[i]))");
        methodCode.add("\t{");
        methodCode.add("\t\treturn false;");
        methodCode.add("\t}");
        methodCode.add("}");
        if (list.isOptional()) {
            methodCode = CSharpCode.addOptionalWrapper(pvIndex, methodCode);
        }

        if (!code.notEqualLines.isEmpty()) {
            code.notEqualLines.add("");
        }

        code.notEqualLines.addAll(methodCode);

    }

    /**
     * Generates the Code for a Record if is a Vector
     * @param parentClassName
     * @param shortClassName
     * @param pvIndex
     * @param code
     */
    private void generateListVector(String parentClassName, String shortClassName, int pvIndex, CodeLines code) {
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            generateListVectorCpp(parentClassName, shortClassName, pvIndex, code);
        } else if (codeType == CodeLines.CodeType.JAVA) {
            generateListVectorJava(parentClassName, shortClassName, pvIndex, code);
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            generateListVectorCSharp(parentClassName, shortClassName, pvIndex, code);
        }
    }
}
