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
 * @(#)MessageDefGenerator.java		 2011/04/01
 * 
 * Copyright
 */

package org.jts.codegenerator;

import org.jts.jsidl.binding.*;
import java.util.Vector;

/**
 * This class will generate C++, Java, and C# code from a JSIDL Message Def
 *
 * @version		1 April 2011
 * @author		Nicholas M. Johnson
 * @author		Gregory Garcia
 * @author		Jean-Francois Kamath
 * @author      Gina Nearing
 *
 */
public class EventDefGenerator
{
//	final private boolean debug = false;
	final private boolean headerIsNested = true;
	final private boolean bodyIsNested = true;
	final private boolean footerIsNested = true;
	
	private CodeLines.CodeType codeType;
	private EventDef eventDef;
	private String msgIdConstant;
	
	public EventDefGenerator(CodeLines.CodeType codeType, EventDef eventDef)
	{
		this.codeType = codeType;
		this.eventDef = eventDef;
//		this.namespace = "";
//		this.baseClassList = new Vector<String>();
	}
	
	
	public void run(String namespace, String outDir)
	{
		if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
		{
                    CodeLines code = new CodeLines("", codeType, "JSIDL_v_1_0");
                    Vector<String> includes = new Vector<String>();
                    Vector<String> baseClassList = new Vector<String>();

                    String fullClassName;	// The fully qualified class name
                    String shortClassName;	// The short class name

                     fullClassName = eventDef.getName();
                     shortClassName = eventDef.getName();

                     msgIdConstant = shortClassName + "::Id";

                     try
                     {
                         CodeLines subCode = new CodeLines("", codeType, "JSIDL_v_1_0");

                         if (eventDef.getHeader() != null)
                         {
                            subCode.clear();
                            new HeaderGenerator(codeType, eventDef.getHeader()).run(fullClassName, headerIsNested, subCode, null);
                            code.addAll(subCode);
                         }

                        if (eventDef.getBody() != null)
                        {
                            subCode.clear();
                            new BodyGenerator(codeType, eventDef.getBody()).run(fullClassName, bodyIsNested, subCode);
                            code.addAll(subCode);
                        }

                        if (eventDef.getFooter() != null)
                        {
                            subCode.clear();
                            new FooterGenerator(codeType, eventDef.getFooter()).run(fullClassName, footerIsNested, subCode);
                            code.addAll(subCode);
                        }
		        
                        /// Initialize Name variable
                        code.constructorLines.add("m_Name = \"" + eventDef.getName() + "\";");

                        includes.add("InternalEvents/InternalEvent.h");
                        baseClassList.add("public JTS::InternalEvent");
		        if(!namespace.isEmpty())
		        {
                            CppCode.createCppCode(fullClassName, outDir + "/include/" + namespace + "/InternalEvents", outDir + "/src/" + namespace + "/InternalEvents", code, includes, namespace, baseClassList);
		        }
		        else
		        {
                            CppCode.createCppCode(fullClassName, outDir + "/include/" + namespace + "/InternalEvents", outDir + "/src/" + namespace + "/InternalEvents", code, includes);
		        }

			}
			catch (Exception e)
			{
                            System.out.println(e.getMessage());
			}
		}
                else if(codeType == CodeLines.CodeType.JAVA)
                {
                    CodeLines code = new CodeLines("", codeType, "JSIDL_v_1_0");
                    Vector<String> includes = new Vector<String>();
                    Vector<String> baseClassList = new Vector<String>();

                    String fullClassName;	// The fully qualified class name
                    String shortClassName;	// The short class name
                    fullClassName = eventDef.getName();
                    shortClassName = eventDef.getName();

                    // Define the logger so it's right under public class ...{
                    code.classDefinitions.add("protected static Logger logger = Logger.getLogger(\"framework.logger\");");

                    msgIdConstant = shortClassName + ".Id";

                    try
                    {
                        CodeLines subCode = new CodeLines("", codeType, "JSIDL_v_1_0");

                        if (eventDef.getHeader() != null)
                        {
                           subCode.clear();
                           new HeaderGenerator(codeType, eventDef.getHeader()).run(fullClassName, headerIsNested, subCode, null);
                           code.addAll(subCode);
                        }

                        if (eventDef.getBody() != null)
                        {
                            subCode.clear();
                            new BodyGenerator(codeType, eventDef.getBody()).run(fullClassName, bodyIsNested, subCode);
                            code.addAll(subCode);
                        }

                        if (eventDef.getFooter() != null)
                        {
                            subCode.clear();
                            new FooterGenerator(codeType, eventDef.getFooter()).run(fullClassName, footerIsNested, subCode);
                            code.addAll(subCode);
                        }
                        /// Initialize Name variable
                        code.constructorLines.add("m_Name = \"" + eventDef.getName() + "\";");

                        includes.add("framework.internalEvents.InternalEvent");
                        includes.add("framework.JausUtils");
                        includes.add("java.nio.ByteBuffer");
                        includes.add("java.nio.ByteOrder");
                        includes.add("java.util.ArrayList");
                        includes.add("java.util.Arrays");
                        includes.add("java.util.BitSet");
                        includes.add("java.util.logging.Logger");
                        includes.add("java.util.logging.Level");
                        baseClassList.add("public InternalEvent");

                        String directoryName = "InternalEvents";
                        String includeDirectory = outDir + "/include/" + namespace + "/" + directoryName;
                        String sourceDirectory = outDir + "/src/" + namespace + "/" + directoryName;

                        if (!namespace.isEmpty())
                        {
                        	// package name for java class will be namespace plus the directory 
                        	String packageName = namespace + "." +  directoryName;

                            JavaCode.createJavaCode(fullClassName, includeDirectory, sourceDirectory, code, includes, packageName, baseClassList);
                        }
                        else
                        {
                            JavaCode.createJavaCode(fullClassName, includeDirectory, sourceDirectory, code, includes);
                        }

			}
			catch (Exception e)
			{
                            System.out.println("Error creating Event Def Gen");
                            System.out.println(e.getMessage());
			}
                }
                else if(codeType == CodeLines.CodeType.C_SHARP)
                {
                    CodeLines code = new CodeLines("", codeType, "JSIDL_v_1_0");
                    Vector<String> includes = new Vector<String>();
                    Vector<String> baseClassList = new Vector<String>();

                    String fullClassName;	// The fully qualified class name
                    String shortClassName;	// The short class name

                     fullClassName = eventDef.getName();
                     shortClassName = eventDef.getName();

                     msgIdConstant = shortClassName + ".Id";
                     try
                     {
                         CodeLines subCode = new CodeLines("", codeType, "JSIDL_v_1_0");

                         if (eventDef.getHeader() != null)
                         {
                            subCode.clear();
                            new HeaderGenerator(codeType, eventDef.getHeader()).run(fullClassName, headerIsNested, subCode, null);
                            code.addAll(subCode);
                         }

                        if (eventDef.getBody() != null)
                        {
                            subCode.clear();
                            new BodyGenerator(codeType, eventDef.getBody()).run(fullClassName, bodyIsNested, subCode);
                            code.addAll(subCode);
                        }

                        if (eventDef.getFooter() != null)
                        {
                            subCode.clear();
                            new FooterGenerator(codeType, eventDef.getFooter()).run(fullClassName, footerIsNested, subCode);
                            code.addAll(subCode);
                        }
                         /// Initialize Name variable
                        code.constructorLines.add("m_Name = \"" + eventDef.getName() + "\";");

                        
                        baseClassList.add("InternalEvent");
		        if(!namespace.isEmpty())
		        {
                            CSharpCode.createCSharpCode(fullClassName, outDir + "/include/" + namespace + "/InternalEvents", outDir + "/src/" + namespace + "/InternalEvents", code, includes, namespace, baseClassList);
		        }
		        else
		        {
                            CSharpCode.createCSharpCode(fullClassName, outDir + "/include/" + namespace + "/InternalEvents", outDir + "/src/" + namespace + "/InternalEvents", code, includes);
		        }

			}
			catch (Exception e)
			{
                            System.out.println(e.getMessage());
			}
                }
		else
		{
			throw new CodeGeneratorException("Unhandled code type specified when generating EventDef");
		}
	}
	
	
	
	public String getIdConstant()
	{
		return msgIdConstant;
	}

}
