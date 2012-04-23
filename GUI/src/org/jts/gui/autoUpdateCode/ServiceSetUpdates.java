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
class ServiceSetUpdates {
  ServiceSetUpdates() {
      try {
         // import
         FindReplace.replaceInFile( "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ServiceSet.java", 
                                                 "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ServiceSet.java", 
                                                 "/** place holder for more imports **/", 
                                                 "import com.u2d.reflection.Cmd;\r\n"+
                                                 "import com.u2d.reflection.Arg;\r\n"+
                                                 "import com.u2d.element.CommandInfo;\r\n");
                                                 
        // field order
        FindReplace.replaceInFile( "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ServiceSet.java", 
                                                    "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ServiceSet.java",
                                                 "\"name\", \"serviceSetId\", \"_version\", \"description\", \"serviceDefs\", \"referencingComponents\", \"constantSets\"",
                                                 "\"name\", \"serviceSetId\", \"_version\", \"description\", \"serviceDefs\", \"constantSets\", \"referencingElements\"" );
                                                 
         // referencingElements                                          
        FindReplace.replaceInFile( "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ServiceSet.java",
                                                     "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ServiceSet.java", 
                                                 "//    public static String[] fieldOrder        = {\"fieldname1\", \"fieldname2\"};", 
                                                 "public static String[] readOnly  = {\"referencingElements\"};\r\n"+
                                                 "// ******    referencingElements   ******\r\n"+
                                                 "   private final StringEO referencingElements = new StringEO(\"{Components}\");\r\n"+
                                                 "   public StringEO getReferencingElements() { return referencingElements;}" );                                           
        
        // relation
        FindReplace.replaceInFile( "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ServiceSet.java", 
                                                 "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ServiceSet.java", 
                                                 "public static final boolean serviceDefsRelationIsInverse = true;", 
                                                 "public static final boolean serviceDefsRelationIsInverse = false;" );
                                                 
        // relation
        FindReplace.replaceInFile( "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ServiceSet.java", 
                                                 "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ServiceSet.java", 
                                                 "public static final boolean constantSetsRelationIsInverse = true;", 
                                                 "public static final boolean constantSetsRelationIsInverse = false;" );                                                      
                                                 
        // title
         FindReplace.replaceInFile( "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ServiceSet.java", 
                                                 "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ServiceSet.java", 
                                                 "name.title() + \" [ServiceSet]\"", 
                                                 "name.title() + \" v\" + _version.title()+ \" [ServiceSet]\"" );
                                                 
        // command
         FindReplace.replaceInFile( "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ServiceSet.java", 
                                                 "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ServiceSet.java", 
                                                 "/**@Cmd place holder**/",
                                                 "\t@Cmd(mnemonic='o')\r\n"+
                                                 "\tpublic Object GenerateDocumentation(com.u2d.element.CommandInfo cmdInfo )\r\n"+
                                                 "\t{\r\n"+
                                                 "\t      org.jts.gui.docGenerator.DocGeneratorStart docGenerator = new org.jts.gui.docGenerator.DocGeneratorStart( this );\r\n"+
                                                 "\t      docGenerator.performGeneration();\r\n"+
                                                 "\t      return null;\r\n"+
                                                 "\t}\r\n"+
                                                 "//\t@Cmd(mnemonic='c')\r\n"+
                                                 "//\tpublic Object ImportFromCJSIDL(com.u2d.element.CommandInfo cmdInfo )\r\n"+
                                                 "//\t{\r\n"+
                                                 "//\t     org.jts.gui.importCJSIDL.Import _import = new org.jts.gui.importCJSIDL.Import();\r\n"+
	                                         "//\t       _import.importServiceDefs();\r\n"+
                                                 "//\t      return null;\r\n"+
                                                 "//\t}\r\n"+
                                                 "\t@Cmd(mnemonic='C')\r\n"+
                                                 "\tpublic Object ExportToCJSIDL(com.u2d.element.CommandInfo cmdInfo )\r\n"+
                                                 "\t{\r\n"+
                                              	"\t      org.jts.gui.exportCJSIDL.Export.exportServiceSetCJSIDL( this );\r\n"+
                                                 "\t	 return null;\r\n"+
                                                 "\t}\r\n"+
                                                 "//\t@Cmd(mnemonic='i')\r\n"+
                                                 "//\tpublic static Object ImportFromJSIDL(CommandInfo cmdInfo,\r\n"+
                                                 "//\t                                         @Arg(\"Path\") FileEO path)\r\n"+
                                                 "//\t{\r\n"+
                                                 "//\t      org.jts.gui.importJSIDL.Import _import = new org.jts.gui.importJSIDL.Import();\r\n"+
                                                 "//\t      _import.importServiceDefs( );\r\n"+
                                                 "//\t      return null;\r\n"+
                                                 "//\t}\r\n"+
                                                 "\t@Cmd(mnemonic='x')\r\n"+
                                                 "\tpublic Object ExportToJSIDL(com.u2d.element.CommandInfo cmdInfo)\r\n"+
                                                 "\t{\r\n"+
                                                 "\t      org.jts.gui.exportJSIDL.Export _export = new org.jts.gui.exportJSIDL.Export();\r\n"+
                                                 "\t      _export.exportServiceSet( this );\r\n"+
                                                 "\t      return null;\r\n"+
                                                 "\t}\r\n"+
                                                 "\t@Cmd(mnemonic='p')\r\n"+
                                                 "\tpublic Object generatePromelaCode(com.u2d.element.CommandInfo cmdInfo)\r\n"+
                                                 "\t{\r\n"+
                                                 "\t      org.jts.gui.promelaCodeGenerator.CodeGenStart gen = new org.jts.gui.promelaCodeGenerator.CodeGenStart();"+
                                                 "\t      gen.generateCode( this );\r\n"+
                                                 "\t	 return null;\r\n"+
                                                 "\t}\r\n");

         // relational list no edit
         FindReplace.replaceInFile( "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ServiceSet.java", 
								                 "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ServiceSet.java", 
								                 "RelationalList referencingComponents = new RelationalList", 
								                 "RelationalListNoEdit referencingComponents = new RelationalListNoEdit" );

         FindReplace.replaceInFile( "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ServiceSet.java", 
								                "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ServiceSet.java", 
								                "RelationalList getReferencingComponents", 
								                "RelationalListNoEdit getReferencingComponents" );
      } catch(IOException ioe) {
        ioe.printStackTrace();
      }
  }
} 