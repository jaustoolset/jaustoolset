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
 * @(#)MessageDefGenerator.java		0.1 2008/09/03
 * 
 * Copyright
 */
package org.jts.codegenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.jts.jsidl.binding.Body;
import org.jts.jsidl.binding.Footer;
import org.jts.jsidl.binding.Header;
import org.jts.jsidl.binding.MessageDef;

/**
 * This class will generate C++ code from a JSIDL Message Def 
 *
 * @version		0.1	5 Sept 2008
 * @author		Nicholas M. Johnson
 * @author		Gregory Garcia
 * @author		Jean-Francois Kamath
 * @author              Gina Nearing
 *
 */
public class MessageDefGenerator {
//	final private boolean debug = false;

    final private boolean headerIsNested = true;
    final private boolean bodyIsNested = true;
    final private boolean footerIsNested = true;
    final private boolean usingJaus = true;
    private CodeLines.CodeType codeType;
    private MessageDef msgDef;
    private String msgIdConstant;

    public MessageDefGenerator(CodeLines.CodeType codeType, MessageDef msgDef) {
        this.codeType = codeType;
        this.msgDef = msgDef;
    }

    public void run(String namespace, String outDir) {
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            CodeLines code = new CodeLines("", codeType, "JSIDL_v_1_0");
            List<String> includes = new ArrayList<String>();
            List<String> baseClassList = new ArrayList<String>();

            String fullClassName;	// The fully qualified class name
            String shortClassName;	// The short class name
            fullClassName = msgDef.getName();
            shortClassName = msgDef.getName();

            msgIdConstant = shortClassName + "::Id";

            try {
                CodeLines subCode = new CodeLines("", codeType, "JSIDL_v_1_0");

                if (msgDef.getHeader() != null) {
                    subCode.clear();

                    /// Begin the messageId line
                    String messageIdLine = "static const int ID = 0x";
                    String messageId = "";

                    /// Get the actual byte array from the messageDef xml function
                    byte[] id = msgDef.getMessageId();

                    /// Convert byte array to string and add to public attributes string
                    for (int i = 0; i < id.length; i++) {
                        messageId = messageId.concat(Integer.toString((id[i] & 0xff) + 0x100, 16).substring(1));
                    }

                    /// Add last part to messageId line
                    messageIdLine = messageIdLine.concat(messageId + ";");

                    /// Add the message Id under class definitions lines so it is the first thing under public:
                    code.classDefinitions.add(messageIdLine);
                    code.classDefinitions.add("");

                    code.publicMethods.add("unsigned int getID() { return ID; };");

                    /// Generate a header, passing the messageID
                    new HeaderGenerator(codeType, msgDef.getHeader()).run(fullClassName, headerIsNested, subCode, messageId);
                    code.addAll(subCode);
                }

                if (msgDef.getBody() != null) {
                    subCode.clear();
                    new BodyGenerator(codeType, msgDef.getBody()).run(fullClassName, bodyIsNested, subCode);
                    code.addAll(subCode);
                }

                if (msgDef.getFooter() != null) {
                    subCode.clear();
                    new FooterGenerator(codeType, msgDef.getFooter()).run(fullClassName, footerIsNested, subCode);
                    code.addAll(subCode);
                }

                /// Initialize isCommand variable
                code.constructorLines.add("m_IsCommand = " + String.valueOf(msgDef.isIsCommand()) + ";");

                includes.add("Messages/Message.h");
                includes.add("JConstants.h");
                baseClassList.add("public JTS::Message");
                if (!namespace.isEmpty()) {
                    CppCode.createCppCode(fullClassName, outDir + "/include/" + namespace + "/Messages", outDir + "/src/" + namespace + "/Messages", code, includes, namespace, baseClassList);
                } else {
                    CppCode.createCppCode(fullClassName, outDir + "/include/" + namespace + "/Messages", outDir + "/src/" + namespace + "/Messages", code, includes);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (codeType == CodeLines.CodeType.JAVA) {
            CodeLines code = new CodeLines("", codeType, "JSIDL_v_1_0");
            Vector<String> includes = new Vector<String>();
            Vector<String> baseClassList = new Vector<String>();

            String fullClassName;	// The fully qualified class name
            String shortClassName;	// The short class name
//			String variableName;	// The private variable name
            fullClassName = msgDef.getName();
            shortClassName = msgDef.getName();

            msgIdConstant = shortClassName + ".Id";

            try {
                CodeLines subCode = new CodeLines("", codeType, "JSIDL_v_1_0");

                if (msgDef.getHeader() != null) {
                    subCode.clear();

                    /// Begin the messageId line
                    String messageIdLine = "public static int ID = 0x";
                    String messageId = "";

                    /// Get the actual byte array from the messageDef xml function
                    byte[] id = msgDef.getMessageId();

                    /// Convert byte array to string and add to public attributes string
                    for (int i = 0; i < id.length; i++) {
                        messageId = messageId.concat(Integer.toString((id[i] & 0xff) + 0x100, 16).substring(1));
                    }

                    /// Add last part to messageId line
                    messageIdLine = messageIdLine.concat(messageId + ";");


                    /// Add the message Id and logger under class definitions lines so it is the first thing under public class ...{
                    code.classDefinitions.add("protected static Logger logger = Logger.getLogger(\"framework.logger\");");
                    code.classDefinitions.add(messageIdLine);
                    code.classDefinitions.add("");

                    code.methods.add("public long getID()");
                    code.methods.add("{\n\treturn ID;\n }");

                    /// Generate a header, passing the messageID
                    new HeaderGenerator(codeType, msgDef.getHeader()).run(fullClassName, headerIsNested, subCode, messageId);
                    code.addAll(subCode);
                }

                if (msgDef.getBody() != null) {
                    subCode.clear();
                    new BodyGenerator(codeType, msgDef.getBody()).run(fullClassName, bodyIsNested, subCode);
                    code.addAll(subCode);
                }

                if (msgDef.getFooter() != null) {
                    subCode.clear();
                    new FooterGenerator(codeType, msgDef.getFooter()).run(fullClassName, footerIsNested, subCode);
                    code.addAll(subCode);
                }
                /// Initialize isCommand variable
                code.constructorLines.add("m_IsCommand = " + String.valueOf(msgDef.isIsCommand()) + ";");

                includes.add("framework.messages.Message");
                includes.add("framework.JausUtils");
                includes.add("java.nio.ByteBuffer");
                includes.add("java.nio.ByteOrder");
                includes.add("java.util.BitSet");
                includes.add("java.util.ArrayList");
                includes.add("java.util.Arrays");
                includes.add("java.util.logging.Level");
                includes.add("java.util.logging.Logger");

                baseClassList.add("public Message");
                
                String directoryName = "Messages";
                String includeDirectory = outDir + "/include/" + namespace + "/" + directoryName;
                String sourceDirectory = outDir + "/src/" + namespace + "/" + directoryName;

                if (!namespace.isEmpty()) {
                	// package name for java class will be namespace plus the directory 
                	String packageName = namespace + "." +  directoryName;

                    JavaCode.createJavaCode(fullClassName, includeDirectory, sourceDirectory, code, includes, packageName, baseClassList);
                } else {
                    JavaCode.createJavaCode(fullClassName, includeDirectory, sourceDirectory, code, includes);
                }
            } catch (Exception e) {
                System.out.println("MessageDefGenerator.run(): " + e.getMessage());
                e.printStackTrace();
            }
        } else if (codeType == CodeLines.CodeType.C_SHARP) {

            CodeLines code = new CodeLines("", codeType, "JSIDL_v_1_0");
            Vector<String> includes = new Vector<String>();
            Vector<String> baseClassList = new Vector<String>();

            includes.add("System.Linq");
            includes.add("System.Collections");
            includes.add("System.Runtime.InteropServices");
            includes.add("System.Collections.Generic");

            String fullClassName;	// The fully qualified class name
            String shortClassName;	// The short class name
//			String variableName;	// The private variable name
            fullClassName = msgDef.getName();
            shortClassName = msgDef.getName();

            msgIdConstant = shortClassName + ".Id";

            try {
                CodeLines subCode = new CodeLines("", codeType, "JSIDL_v_1_0");

                if (msgDef.getHeader() != null) {
                    subCode.clear();

                    /// Begin the messageId line
                    String messageIdLine = "protected ushort ID = 0x";
                    String messageId = "";

                    /// Get the actual byte array from the messageDef xml function
                    byte[] id = msgDef.getMessageId();

                    /// Convert byte array to string and add to public attributes string
                    for (int i = 0; i < id.length; i++) {
                        messageId = messageId.concat(Integer.toString((id[i] & 0xff) + 0x100, 16).substring(1));
                    }

                    /// Add last part to messageId line
                    messageIdLine = messageIdLine.concat(messageId + ";");

                    /// Add the message Id under class definitions lines so it is the first thing under public:
                    code.classDefinitions.add(messageIdLine);
                    code.classDefinitions.add("");

                    code.methods.add("public override ushort getID()");
                    code.methods.add("{");
                    code.methods.add("\treturn ID;");
                    code.methods.add("}");

                    /// Generate a header, passing the messageID
                    new HeaderGenerator(codeType, msgDef.getHeader()).run(fullClassName, headerIsNested, subCode, messageId);
                    code.addAll(subCode);
                }

                if (msgDef.getBody() != null) {
                    subCode.clear();
                    new BodyGenerator(codeType, msgDef.getBody()).run(fullClassName, bodyIsNested, subCode);
                    code.addAll(subCode);
                }

                if (msgDef.getFooter() != null) {
                    subCode.clear();
                    new FooterGenerator(codeType, msgDef.getFooter()).run(fullClassName, footerIsNested, subCode);
                    code.addAll(subCode);
                }
                /// Initialize isCommand variable
                code.constructorLines.add("m_IsCommand = " + String.valueOf(msgDef.isIsCommand()) + ";");

                baseClassList.add("JTS.Message");
                if (!namespace.isEmpty()) {
                    CSharpCode.createCSharpCode(fullClassName, outDir + "/include/" + namespace + "/Messages", outDir + "/src/" + namespace + "/Messages", code, includes, namespace, baseClassList);
                } else {
                    CSharpCode.createCSharpCode(fullClassName, outDir + "/include/" + namespace + "/Messages", outDir + "/src/" + namespace + "/Messages", code, includes);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new CodeGeneratorException("Unhandled Code Type Specified");
        }
    }

    public String getIdConstant() {
        return msgIdConstant;
    }

    /**
     * Helper functions to get types of composites stored in each section of the message
     * @param object(Header, Body, or Footer)
     */
    public String getCompositeType(Object messageComponent) {
        String variableName = null;
        if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            if (messageComponent instanceof Header) {
                Header header = (Header) messageComponent;

                if (header.getRecord() != null) {
                    variableName = CppCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getHeader().getRecord().getName()));
                } else if (header.getList() != null) {
                    variableName = CppCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getHeader().getList().getName()));
                } else if (header.getSequence() != null) {
                    variableName = CppCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getHeader().getSequence().getName()));
                } else if (header.getVariant() != null) {
                    variableName = CppCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getHeader().getVariant().getName()));
                } else {
                    variableName = null;
                }
            } else if (messageComponent instanceof Body) {
                Body body = (Body) messageComponent;

                if (body.getRecord() != null) {
                    variableName = CppCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getBody().getRecord().getName()));
                } else if (body.getList() != null) {
                    variableName = CppCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getBody().getList().getName()));
                } else if (body.getSequence() != null) {
                    variableName = CppCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getBody().getSequence().getName()));
                } else if (body.getVariant() != null) {
                    variableName = CppCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getBody().getVariant().getName()));
                } else {
                    variableName = null;
                }
            } else if (messageComponent instanceof Footer) {
                Footer footer = (Footer) messageComponent;

                if (footer.getRecord() != null) {
                    variableName = CppCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getFooter().getRecord().getName()));
                } else if (footer.getList() != null) {
                    variableName = CppCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getFooter().getList().getName()));
                } else if (footer.getSequence() != null) {
                    variableName = CppCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getFooter().getSequence().getName()));
                } else if (footer.getVariant() != null) {
                    variableName = CppCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getFooter().getVariant().getName()));
                } else {
                    variableName = null;
                }
            } else {
                throw new CodeGeneratorException("Unhandled Code Type Specified");
            }
        } else if (codeType == CodeLines.CodeType.JAVA) {
            if (messageComponent instanceof Header) {
                Header header = (Header) messageComponent;

                if (header.getRecord() != null) {
                    variableName = JavaCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getHeader().getRecord().getName()));
                } else if (header.getList() != null) {
                    variableName = JavaCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getHeader().getList().getName()));
                } else if (header.getSequence() != null) {
                    variableName = JavaCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getHeader().getSequence().getName()));
                } else if (header.getVariant() != null) {
                    variableName = JavaCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getHeader().getVariant().getName()));
                } else {
                    variableName = null;
                }
            } else if (messageComponent instanceof Body) {
                Body body = (Body) messageComponent;

                if (body.getRecord() != null) {
                    variableName = JavaCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getBody().getRecord().getName()));
                } else if (body.getList() != null) {
                    variableName = JavaCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getBody().getList().getName()));
                } else if (body.getSequence() != null) {
                    variableName = JavaCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getBody().getSequence().getName()));
                } else if (body.getVariant() != null) {
                    variableName = JavaCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getBody().getVariant().getName()));
                } else {
                    variableName = null;
                }
            } else if (messageComponent instanceof Footer) {
                Footer footer = (Footer) messageComponent;

                if (footer.getRecord() != null) {
                    variableName = JavaCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getFooter().getRecord().getName()));
                } else if (footer.getList() != null) {
                    variableName = JavaCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getFooter().getList().getName()));
                } else if (footer.getSequence() != null) {
                    variableName = JavaCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getFooter().getSequence().getName()));
                } else if (footer.getVariant() != null) {
                    variableName = JavaCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getFooter().getVariant().getName()));
                } else {
                    variableName = null;
                }
            } else {
                throw new CodeGeneratorException("Unhandled Code Type Specified");
            }
        } else if (codeType == CodeLines.CodeType.C_SHARP) {
            if (messageComponent instanceof Header) {
                Header header = (Header) messageComponent;

                if (header.getRecord() != null) {
                    variableName = CSharpCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getHeader().getRecord().getName()));
                } else if (header.getList() != null) {
                    variableName = CSharpCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getHeader().getList().getName()));
                } else if (header.getSequence() != null) {
                    variableName = CSharpCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getHeader().getSequence().getName()));
                } else if (header.getVariant() != null) {
                    variableName = CSharpCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getHeader().getVariant().getName()));
                } else {
                    variableName = null;
                }
            } else if (messageComponent instanceof Body) {
                Body body = (Body) messageComponent;

                if (body.getRecord() != null) {
                    variableName = CSharpCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getBody().getRecord().getName()));
                } else if (body.getList() != null) {
                    variableName = CSharpCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getBody().getList().getName()));
                } else if (body.getSequence() != null) {
                    variableName = CSharpCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getBody().getSequence().getName()));
                } else if (body.getVariant() != null) {
                    variableName = CSharpCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getBody().getVariant().getName()));
                } else {
                    variableName = null;
                }
            } else if (messageComponent instanceof Footer) {
                Footer footer = (Footer) messageComponent;

                if (footer.getRecord() != null) {
                    variableName = CSharpCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getFooter().getRecord().getName()));
                } else if (footer.getList() != null) {
                    variableName = CSharpCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getFooter().getList().getName()));
                } else if (footer.getSequence() != null) {
                    variableName = CSharpCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getFooter().getSequence().getName()));
                } else if (footer.getVariant() != null) {
                    variableName = CSharpCode.getVariableName(Util.upperCaseFirstLetter(msgDef.getFooter().getVariant().getName()));
                } else {
                    variableName = null;
                }
            } else {
                throw new CodeGeneratorException("Unhandled Code Type Specified");
            }
        }

        return variableName;
    }
}
