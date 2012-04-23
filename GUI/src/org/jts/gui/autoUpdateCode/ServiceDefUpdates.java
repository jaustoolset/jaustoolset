/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2010-2011, United States Government
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
class ServiceDefUpdates {
  ServiceDefUpdates() {
      try {
        String filename = "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ServiceDef.java"; 
        // import
         FindReplace.replaceInFile( filename, filename, 
                                                 "/** place holder for more imports **/", 
                                                 "import com.u2d.reflection.Cmd;"+
                                                 "import com.u2d.reflection.Arg;\r\n"+
                                                 "import com.u2d.element.CommandInfo;" );
        
        // relation
        FindReplace.replaceInFile( filename, filename,
                                                 "public static final boolean messageDefsRelationIsInverse = true;", 
                                                 "public static final boolean messageDefsRelationIsInverse = false;" );
        
        // relation
        FindReplace.replaceInFile( filename, filename, 
                                                 "public static final boolean eventDefsRelationIsInverse = true;", 
                                                 "public static final boolean eventDefsRelationIsInverse = false;" );
                                                 
       // relation
        FindReplace.replaceInFile( filename, filename, 
                                                 "public static final boolean clientOfRelationIsInverse = true;", 
                                                 "public static final boolean clientOfRelationIsInverse = false;" );
                                                 
        // relation
        FindReplace.replaceInFile( filename, filename, 
                                                 "public static final boolean constantSetRelationIsInverse = true;", 
                                                 "public static final boolean constantSetRelationIsInverse = false;" );     
                                                 
        // validation
        FindReplace.replaceInFile( filename, filename, 
                                                 "ServiceDef old = this.inheritsFrom;",
                                                 "// check for a reference to self (this can't get validated through validator because\r\n"+ 
                                                 "       // the object is put into a readonly state the moment the reference has been selected\r\n"+ 
                                                 "       // in the UI, and manual save never happens. this seems to happen when\r\n"+
                                                 "       // the *InverseFieldName construct is used)\r\n"+
                                                 "       if(	inheritsFrom != null &&\r\n"+
                                        	     "         inheritsFrom.getServiceId().equals(getServiceId()) &&\r\n"+
                                        	     "         inheritsFrom.get_version().equals(get_version()) )\r\n"+
                                        	     "       {\r\n"+
                                        	     "		       java.awt.EventQueue.invokeLater( new Runnable() {\r\n"+
                                        	     "   	         public void run() {\r\n"+
                                        	     "   	            javax.swing.JOptionPane.showMessageDialog( null,\r\n"+
                                        	     "   	            \"A ServiceDef cannot inherit from itself\", \"JTS Message Dialog\",\r\n"+
                                        	     "   	            javax.swing.JOptionPane.ERROR_MESSAGE) ;\r\n"+
                                        	     "   	         }\r\n"+
                                        	     "    	     });\r\n"+
                                        	     "           return;\r\n"+
                                        	     "       }\r\n"+
                                        	     "       if( inheritsFrom == null && inheritsFromName.stringValue().length() > 0 )\r\n"+ 
                                                    "          inheritsFromName.setValue( \"\" );\r\n"+
                                                    "       else if( inheritsFrom != null && inheritsFromName.stringValue().length() == 0 )\r\n"+ 
                                                    "         inheritsFromName.setValue( inheritsFrom.getName().stringValue() ); \r\n\r\n"+
                                                 "        ServiceDef  old  =  this.inheritsFrom;" );  // added white spaces to differentiate line after autoupdate     
                                                 
        // field order
        FindReplace.replaceInFile( filename, filename,
                                                 "\"name\", \"serviceId\", \"_version\", \"description\", \"assumptions\", \"inheritsFromName\", \"referencingServiceSets\", \"protocolBehavior\", \"eventDefs\", \"inputSet\", \"outputSet\", \"baseServiceTo\", \"inheritsFrom\", \"serverTo\", \"clientOf\", \"constantSet\"",
                                                 "\"name\", \"serviceId\", \"_version\", \"description\", \"assumptions\", \"inheritsFrom\", \"clientOf\", \"inputSet\", \"outputSet\", \"eventDefs\", \"protocolBehavior\", \"constantSet\", \"referencingElements\"" );
                                                 
         // referencingElements                                          
        FindReplace.replaceInFile( filename, filename, 
                                                 "//    public static String[] fieldOrder        = {\"fieldname1\", \"fieldname2\"};", 
                                                 "public static String[] readOnly  = {\"referencingElements\"};\r\n"+
                                                 "// ******    referencingElements   ******\r\n"+
                                                 "   private final StringEO referencingElements = new StringEO(\"{ServiceDefs (clientOf, InheritsFrom), Service Sets}\");\r\n"+
                                                 "   public StringEO getReferencingElements() { return referencingElements;}" );                                            
                                                 
        // title
        FindReplace.replaceInFile( filename, filename, 
                                                "name.title() + \" [ServiceDef]\"", 
                                                "name.title() + \" v\" + _version.title()+ \" [ServiceDef]\"" );
                                                                                                                                   
                                                
         // command
         FindReplace.replaceInFile( filename, filename, 
                                                 "/**@Cmd place holder**/", 
                                                 "\t@Cmd(mnemonic='i')\r\n"+
                                                 "\tpublic static Object ImportFromJSIDL(CommandInfo cmdInfo)\r\n"+
                                                 "\t{\r\n"+
                                                 "\t     org.jts.gui.importJSIDL.Import _import = new org.jts.gui.importJSIDL.Import();\r\n"+
	                                       "\t       _import.importServiceDefs();\r\n"+
                                                "\t      return null;\r\n"+
                                                 "\t}\r\n"+
                                                 "\t@Cmd(mnemonic='c')\r\n"+
                                                 "\tpublic static Object ImportFromCJSIDL(CommandInfo cmdInfo)\r\n"+
                                                 "\t{\r\n"+
                                                 "\t     org.jts.gui.importCJSIDL.Import _import = new org.jts.gui.importCJSIDL.Import();\r\n"+
	                                       "\t       _import.importServiceDefs();\r\n"+
                                                "\t      return null;\r\n"+
                                                 "\t}\r\n"+
                                                 "\t@Cmd(mnemonic='x')\r\n"+
                                                 "\tpublic Object ExportToJSIDL(com.u2d.element.CommandInfo cmdInfo)\r\n"+
                                              	"\t{\r\n"+
                                              	"\t      org.jts.gui.exportJSIDL.Export.exportServiceDefJSIDL( this );\r\n"+
                                                 "\t	 return null;\r\n"+
                                                 "\t}\r\n" +
                                                 "\t@Cmd(mnemonic='x')\r\n"+
                                                 "\tpublic Object ExportToJSIDLPlus(com.u2d.element.CommandInfo cmdInfo)\r\n"+
                                              	"\t{\r\n"+
                                              	"\t      org.jts.gui.exportJSIDL.Export.exportServiceDefJSIDLPlus( this );\r\n"+
                                                 "\t	 return null;\r\n"+
                                                 "\t}\r\n" +
                                                 "\t@Cmd(mnemonic='C')\r\n"+
                                                 "\tpublic Object ExportToCJSIDL(com.u2d.element.CommandInfo cmdInfo)\r\n"+
                                              	"\t{\r\n"+
                                              	"\t      org.jts.gui.exportCJSIDL.Export.exportServiceDefCJSIDL( this );\r\n"+
                                                 "\t	 return null;\r\n"+
                                                 "\t}\r\n" +
                                                 "\t@Cmd(mnemonic='v')\r\n"+
                                                 "\tpublic Object recursiveDelete(com.u2d.element.CommandInfo cmdInfo)\r\n"+
                                              	"\t{\r\n"+
                                              	"\t      org.jts.gui.deepDelete.DeepDeleteStart.performDelete( this );\r\n"+
                                                 "\t	 return null;\r\n"+
                                                 "\t}\r\n"
                                                 );
                                                 
         FindReplace.replaceInFile( filename, 
        		 filename, 
                 "RelationalList referencingServiceSets = new RelationalList", 
                 "RelationalListNoEdit referencingServiceSets = new RelationalListNoEdit" );

         FindReplace.replaceInFile(filename, 
                filename, 
                "RelationalList getReferencingServiceSets", 
                "RelationalListNoEdit getReferencingServiceSets" );
      } catch(IOException ioe) {
        ioe.printStackTrace();
     }
  }
} 