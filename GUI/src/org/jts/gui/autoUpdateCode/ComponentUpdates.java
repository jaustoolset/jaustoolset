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

package org.jts.gui.autoUpdateCode;

import java.io.*;

/** Implements updates that need to be made to Component.java. 
*
*/
class ComponentUpdates {
  ComponentUpdates() {
      try {
         // import
         FindReplace.replaceInFile( "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "Component.java", 
                                                 "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "Component.java", 
                                                 "/** place holder for more imports **/", 
                                                 "import com.u2d.reflection.Cmd;" );
        // title
         FindReplace.replaceInFile( "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "Component.java", 
                                                 "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "Component.java", 
                                                 "name.title() + \" [Component]\"", 
                                                 "name.title() + \" ID: \" + componentId.title()+ \" [Component]\"" );
                                                 
        // relation
        FindReplace.replaceInFile( "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "Component.java", 
                                                 "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "Component.java", 
                                                 "public static final boolean serviceSetsRelationIsInverse = true;", 
                                                 "public static final boolean serviceSetsRelationIsInverse = false;" );
                                                 
         // field order
        FindReplace.replaceInFile( "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "Component.java", 
                                                 "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "Component.java",
                                                 "\"name\", \"componentId\", \"path\", \"serviceSet\"", 
                                                 "\"name\", \"componentId\", \"serviceSet\", \"path\"" );
        
         // command
         FindReplace.replaceInFile( "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "Component.java", 
                                                 "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "Component.java", 
                                                 "/**@Cmd place holder**/", 
                                                 "@Cmd(mnemonic='g')\r\n"+
                                                 "public Object AutoGenerateCode(com.u2d.element.CommandInfo cmdInfo)\r\n"+
                                                 "{\r\n"+
                                                 "      com.u2d.app.Context.getInstance().getViewMechanism().message(\"Generating Code, Please Wait... \");\r\n"+
                                                 "      try\r\n"+
                                                 "      {\r\n"+
                                                 "	    org.jts.gui.codeGenerator.ComponentGenerator generator = new org.jts.gui.codeGenerator.ComponentGenerator( this );\r\n"+
                                                 "	    generator.generate();\r\n"+
                                                 "       }\r\n"+
                                                 "      catch(org.jts.codegenerator.CodeGeneratorException e)\r\n"+
                                                 "      {\r\n"+
                                                 "            com.u2d.app.Context.getInstance().getViewMechanism().message(\"Code Generation Unsuccessful. \"+ e.toString() +\"...\");\r\n"+
                                                 "      }\r\n"+
                                                 "return null;\r\n"+
                                                 "}\r\n"+
                                                 "\t@Cmd(mnemonic='p')\r\n"+
                                                 "\tpublic Object generatePromelaCode(com.u2d.element.CommandInfo cmdInfo)\r\n"+
                                                 "\t{\r\n"+
                                                 "\t      org.jts.gui.promelaCodeGenerator.CodeGenStart gen = new org.jts.gui.promelaCodeGenerator.CodeGenStart();"+
                                                 "\t      gen.generateCode( this );\r\n"+
                                                 "\t	 return null;\r\n"+
                                                 "\t}\r\n");
         
      } catch(IOException ioe) {
        ioe.printStackTrace();
      }
  }
}  