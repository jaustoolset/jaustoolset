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

import com.u2d.type.atom.BooleanEO;
import com.u2d.type.atom.StringEO;
import com.u2d.type.atom.TextEO;

/** Implements updates that need to be made to Record.java. 
*
*/
class MessageDefUpdates {
  MessageDefUpdates() {
      try {
        String targetFilename = "src" + File.separator + "com" + File.separator + "u2d" + File.separator +
                "generated" + File.separator + "MessageDef.java";

        // add "implements HasHeaderBodyFooter" to class type.
        FindReplace.replaceInFile( targetFilename, targetFilename, "extends AbstractComplexEObject_JTS{",
                "extends AbstractComplexEObject_JTS implements HasHeaderBodyFooter {");

        // field order
        FindReplace.replaceInFile( targetFilename, targetFilename,
                                                 "\"name\", \"messageId\", \"description\", \"isCommand\", \"body\", \"header\", \"footer\", \"referencingInputSets\", \"referencingOutputSets\"", 
                                                 "\"name\", \"messageId\", \"description\", \"isCommand\", \"header\", \"body\", \"footer\", \"referencingElements\"" );
                                                 
        // referencingElements                                          
        FindReplace.replaceInFile( targetFilename, targetFilename,
                                                 "//    public static String[] fieldOrder        = {\"fieldname1\", \"fieldname2\"};", 
                                                 "public static String[] readOnly  = {\"referencingElements\"};\r\n"+
                                                 "// ******    referencingElements   ******\r\n"+
                                                 "   private final StringEO referencingElements = new StringEO(\"{Input Sets, Output Sets}\");\r\n"+
                                                 "   public StringEO getReferencingElements() { return referencingElements;}" );

        // message name, description, isCommand set methods
        FindReplace.replaceInFileNoMatching( targetFilename, targetFilename,
                "    public StringEO getName() { return name;}", 
                "    public StringEO getName() { return name;}\r\n" + 
                "    public void setName( String s ) { name.setValue( s ); }");
        
        FindReplace.replaceInFileNoMatching( targetFilename, targetFilename,
                "    public TextEO getDescription() { return description;}", 
                "    public TextEO getDescription() { return description;}\r\n" +
        		"    public void setDescription( String s ) { description.setValue( s ); }");

        FindReplace.replaceInFileNoMatching( targetFilename, targetFilename,
                "    public BooleanEO getIsCommand() { return isCommand;}", 
                "    public BooleanEO getIsCommand() { return isCommand;}\r\n" +
        		"    public void setIsCommand( boolean b ) { isCommand.setValue( b ); }");

        // title
        FindReplace.replaceInFile( targetFilename, targetFilename,
                                                "name.title() + \" [MessageDef]\"", 
                                                "name.title() + \" 0x\" + messageId.title()+\" [MessageDef]\"" );
        
        // use title field to set the messageId to uppercase so that the string will change before validation
        FindReplace.replaceInFileNoMatching( targetFilename, targetFilename,
                "        return new Title(text.toString());", 
                "        messageId.setValue( messageId.toString().toUpperCase() );\r\n" +
        		"        return new Title(text.toString());");
                                                
        // relational list no edit                                
        FindReplace.replaceInFile( targetFilename, targetFilename,
                                                 "RelationalList referencingInputSets = new RelationalList", 
                                                 "RelationalListNoEdit referencingInputSets = new RelationalListNoEdit" );
        
        FindReplace.replaceInFile( targetFilename, targetFilename,
                                                "RelationalList getReferencingInputSets",
                                                "RelationalListNoEdit getReferencingInputSets" );
        
        // relational list no edit                                
        FindReplace.replaceInFile( targetFilename, targetFilename,
                                                 "RelationalList referencingOutputSets = new RelationalList", 
                                                 "RelationalListNoEdit referencingOutputSets = new RelationalListNoEdit" );
        
        FindReplace.replaceInFile( targetFilename, targetFilename,
                                                "RelationalList getReferencingOutputSets",
                                                "RelationalListNoEdit getReferencingOutputSets" );
                                                  
                                                
		// imports
        FindReplace.replaceInFile( targetFilename, targetFilename,
                                                "/** place holder for more imports **/", 
                                                "import org.hibernate.Query;\r\n" );                                                
                                                
        // Auto-gen the default header
        FindReplace.replaceInFile( targetFilename, targetFilename,
                                                "/**@Cmd place holder**/",
                                                "\r\n" +
                                                "public void initialize() {\r\n" +
                                                "	super.initialize(); \r\n" +
                                                "	Query hibernateQuery = hbmPersistor().getSession().createQuery(\"from Header hdr where hdr.name=:name\"); \r\n" +
                                                "	hibernateQuery.setParameter(\"name\", \"JTS_DefaultHeader\" );\r\n" +
                                                "	com.u2d.generated.Header defHeader = (com.u2d.generated.Header) hibernateQuery.uniqueResult();\r\n\r\n" +
                                                "	// Default header does not exist.  Create it.       \r\n" +
                                                "	if (defHeader == null)\r\n" + 
                                                "	{\r\n" +
                                                "		defHeader = new com.u2d.generated.Header();\r\n" +
                                                "		defHeader.getName().setValue(\"JTS_DefaultHeader\"); \r\n" +
                                                "		com.u2d.generated.Record defRecord = new com.u2d.generated.Record();\r\n" +
                                                "		defRecord.getName().setValue(\"DefaultHeaderRec\"); \r\n" +
                                                "		com.u2d.generated.FixedField defField = new com.u2d.generated.FixedField(); \r\n" +
                                                "	 	defField.getName().setValue(\"MessageID\"); \r\n" +
                                                "	 	defField.getOptional().setValue(false); \r\n" +
                                                "		defField.getUnits().getCode().setValue(\"one\");\r\n " +
                                                "		defField.getUnits().getCaption().setValue(\"one\"); \r\n" +
                                                "		defField.getType().getCode().setValue(\"unsigned short integer\");\r\n" +
                                                "	 	defField.getType().getCaption().setValue(\"unsigned short integer\");\r\n" +
                                                "	 	defRecord.getSimpleFields().add(defField);\r\n" +
                                                "		defHeader.setComplexField(defRecord);\r\n" +
                                                "		defField.save();\r\n" +
                                                "	 	defRecord.save();\r\n" +
                                                "		defHeader.save();\r\n" +
                                                "	}\r\n\r\n" +
                                                "	this.header = defHeader;\r\n" +
                                                "}\r\n\r\n" );                                               
        
        
        
                                                  
      } catch(IOException ioe) {
        ioe.printStackTrace();
      }
  }
} 