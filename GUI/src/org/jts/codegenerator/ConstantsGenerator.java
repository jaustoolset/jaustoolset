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

package org.jts.codegenerator;

import org.jts.jsidl.binding.*;
import java.io.*;
import java.util.Vector;

import java.util.ArrayList;

/**
 * 
 * @author Mark Bofill
 * modified 3/23/10 Drew Lucas
 *
 */
 

public class ConstantsGenerator
{
	//private CodeLines.CodeType codeType;
	private org.jts.jsidl.binding.DeclaredConstSet declaredConstantSet;
        private CodeLines.CodeType codeType;
	Vector<String> methodCode = new Vector<String>();	
  
	public ConstantsGenerator(CodeLines.CodeType codeType, org.jts.jsidl.binding.DeclaredConstSet declaredSet)
	{
		this.codeType = codeType;
		this.declaredConstantSet = declaredSet;
	}
	
	public void run(String outDir)
	{
            ArrayList<ConstDef> constDef = new ArrayList<ConstDef>();
		
            //this line can throw a null pointer exception, but this is benign and is ignored when it happens.
            if (declaredConstantSet != null) {
                constDef = (ArrayList<ConstDef>) declaredConstantSet.getConstDef();
            }
          
            try
            {
                if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
                {
                    // Open an output stream
                    FileOutputStream fout = new FileOutputStream (outDir + "/include/JConstants.h");

                    // Print a line of text
                    PrintStream stream = new PrintStream(fout);
        	
                    stream.println("#ifndef JCONSTANTS_H");
                    stream.println("#define JCONSTANTS_H");
		            
                    int size = constDef.size();
                    //System.out.println(" in ConstantsGenerator.run, constDef.size = " + constDef.size());
                    for(int i = 0; i < size; i++)
                    {
                        char cQuote = 34;
				
                        String type = constDef.get(i).getConstType();
                        String name = declaredConstantSet.getName() + "_" + constDef.get(i).getName();
                        String value = constDef.get(i).getConstValue();

                        if (type.compareTo("long float")==0) type = "double";
                        if (type.compareTo("integer")==0) type = "int";
                        if (type.compareTo("string")==0) type = "char";

                        if(type.compareTo("char")==0)
                        {
                            stream.println("const "+  type + " " + name + "[] = " + cQuote + value + cQuote + ";");
                        }
                        else
                        {
                            stream.println("const "+  type + " " + name + " = " + value + ";");
                        }
			}
			
		    stream.println("#endif");

		    // Close our output stream
		    fout.close();
                }
                else if(codeType == CodeLines.CodeType.JAVA)
                {
                    // It does not appear that JConstants.java is ever used.
                    // Code temporarily commented out until this is confirmed.
                    /*
                    // Open an output stream
                    FileOutputStream fout = new FileOutputStream (outDir + "/include/JConstants.java");

                    // Print a line of text
                    PrintStream stream = new PrintStream(fout);

                    int size = constDef.size();
                    //System.out.println(" in ConstantsGenerator.run, constDef.size = " + constDef.size());
                    for(int i = 0; i < size; i++)
                    {
                        char cQuote = 34;

                        String type = constDef.get(i).getConstType();
                        String name = declaredConstantSet.getName() + "_" + constDef.get(i).getName();
                        String value = constDef.get(i).getConstValue();

                        if (type.compareTo("long float")==0) type = "double";
                        if (type.compareTo("integer")==0) type = "int";
                        if (type.compareTo("string")==0) type = "char";

                        if(type.compareTo("char")==0)
                        {
                            stream.println(type + " " + name + "[] = " + cQuote + value + cQuote + ";");
                        }
                        else
                        {
                            stream.println(type + " " + name + " = " + value + ";");
                        }
			}
			
		    // Close our output stream
		    fout.close();
                    */
                }
                else if(codeType == CodeLines.CodeType.C_SHARP)
                {
               // output stream
//                    FileOutputStream fout = new FileOutputStream (outDir + "/include/JConstants.cs");
//
//                    // Print a line of text
//                    PrintStream stream = new PrintStream(fout);
//
//                    int size = constDef.size();
//                    //System.out.println(" in ConstantsGenerator.run, constDef.size = " + constDef.size());
//                    for(int i = 0; i < size; i++)
//                    {
//                        char cQuote = 34;
//
//                        String type = constDef.get(i).getConstType();
//                        String name = declaredConstantSet.getName() + "_" + constDef.get(i).getName();
//                        String value = constDef.get(i).getConstValue();
//
//                        if (type.compareTo("long float")==0) type = "double";
//                        if (type.compareTo("integer")==0) type = "int";
//                        if (type.compareTo("string")==0) type = "char";
//
//                        if(type.compareTo("char")==0)
//                        {
//                            stream.println(type + " " + name + "[] = " + cQuote + value + cQuote + ";");
//                        }
//                        else
//                        {
//                            stream.println(type + " " + name + " = " + value + ";");
//                        }
//			}
//
//		    // Close our output stream
//		    fout.close();
                }
            }
		        
            // Catches any error conditions
            catch (IOException e)
            {
	        System.err.println ("Unable to write to file for Constants Generator");
	        System.exit(-1);
            }
            catch (Exception e)
            {
                System.out.println("Caught exception from ConstantsGenerator.run()");
                System.out.println("msg is ->" + e.getMessage() + "<-");
            }    // Open an 

      }

}

