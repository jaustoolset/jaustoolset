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

/** Implements updates that need to be made to Record.java. 
*
*/
class ConstantSetUpdates {
  ConstantSetUpdates() {
      try {
      
         // import
         FindReplace.replaceInFile( "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ConstantSet.java", 
                                                 "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ConstantSet.java", 
                                                 "/** place holder for more imports **/", 
                                                 "import com.u2d.reflection.Cmd;"+
                                                 "import com.u2d.reflection.Arg;\r\n"+
                                                 "import com.u2d.element.CommandInfo;" );
        
        // relation
        FindReplace.replaceInFile( "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ConstantSet.java", 
                                                 "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ConstantSet.java", 
                                                 "public static final boolean constantsRelationIsInverse = true;", 
                                                 "public static final boolean constantsRelationIsInverse = false;" );
                                                 
        // title
        FindReplace.replaceInFile( "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ConstantSet.java", 
                                                 "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ConstantSet.java", 
                                                "name.title() + \" [ConstantSet]\"", 
                                                "name.title() + \" v\" + _version.title()+ \" [ConstantSet]\"" );
                                                
         // command
         FindReplace.replaceInFile( "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ConstantSet.java", 
                                                 "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ConstantSet.java", 
                                                 "/**@Cmd place holder**/",
                                                 "@Cmd(mnemonic='i')\r\n"+
                                                 "public static Object ImportFromJSIDL(CommandInfo cmdInfo)\r\n"+
                                                "{\r\n"+
                                                "      return org.jts.gui.importJSIDL.Import.importConstantSets( );\r\n"+
                                                "}\r\n"+
                                                 "@Cmd(mnemonic='x')\r\n"+
                                                 "public Object ExportToJSIDL(com.u2d.element.CommandInfo cmdInfo) {\r\n"+
                                                 "   org.jts.gui.exportJSIDL.Export.exportConstantSets( this );\r\n"+
                                                 "   return null;\r\n"+
                                                 "}\r\n");  
         
         // relational list no edit 
         FindReplace.replaceInFile( "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ConstantSet.java", 
                                                  "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ConstantSet.java", 
                                                  "RelationalList referencingServiceDefs = new RelationalList", 
                                                  "RelationalListNoEdit referencingServiceDefs = new RelationalListNoEdit" );
         
         FindReplace.replaceInFile( "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ComplexField.java", 
 								                "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ComplexField.java", 
 								                "RelationalList getReferencingServiceDefs", 
 								                "RelationalListNoEdit getReferencingServiceDefs" );
         
         // relational list no edit 
         FindReplace.replaceInFile( "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ConstantSet.java", 
                                                  "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ConstantSet.java", 
                                                  "RelationalList referencingServiceSets = new RelationalList", 
                                                  "RelationalListNoEdit referencingServiceSets = new RelationalListNoEdit" );
         
         FindReplace.replaceInFile( "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ComplexField.java", 
 								                "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ComplexField.java", 
 								                "RelationalList getReferencingServiceSets", 
 								                "RelationalListNoEdit getReferencingServiceSets" );
         
      } catch(IOException ioe) {
        ioe.printStackTrace();
     }
  }
} 