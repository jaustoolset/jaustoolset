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

/** Implements updates that need to be made to ProtocolBehavior.java. 
*
*/
class ProtocolBehaviorUpdates {
  ProtocolBehaviorUpdates() {
      try {
       
       // import
         FindReplace.replaceInFile( "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ProtocolBehavior.java", 
                                                 "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ProtocolBehavior.java", 
                                                 "/** place holder for more imports **/",
                                                 "import java.util.List;\r\n"+
                                                 "import javax.xml.bind.JAXBContext;\r\n"+
                                                 "import javax.xml.bind.Unmarshaller;\r\n"+
                                                 "import javax.xml.transform.stream.StreamSource;\r\n"+
                                                 "import javax.swing.JOptionPane;\r\n"+
                                                 "import java.io.StringReader;\r\n" );
        
        // title
        FindReplace.replaceInFile( "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ProtocolBehavior.java", 
                                                 "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ProtocolBehavior.java", 
                                                "StringBuffer text = new StringBuffer();",
                                                "org.jts.jsidl.binding.ProtocolBehavior jxpb = new org.jts.jsidl.binding.ProtocolBehavior();");
        FindReplace.replaceInFile( "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ProtocolBehavior.java", 
                                                 "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ProtocolBehavior.java", 
                                                "text.append(protocolBehavior.title() + \" [ProtocolBehavior]\");",
                                                "\r\n"+
                                                "        try{\r\n"+ 
                                                "        JAXBContext jc = JAXBContext.newInstance( \"org.jts.jsidl.binding\" );\r\n"+
                                                "        Unmarshaller u = jc.createUnmarshaller();\r\n"+
                                                "        String xmlStr = getProtocolBehavior().stringValue();\r\n"+
                                                "        if( xmlStr != null && xmlStr.length() > 0)\r\n"+
                                                "          jxpb = (org.jts.jsidl.binding.ProtocolBehavior)u.unmarshal( new StreamSource( new StringReader( xmlStr ) ) );\r\n"+
                                                "        } catch( Exception ex ) {\r\n"+
                                                "          ex.printStackTrace();\r\n"+
                                                "        }\r\n"+
                                                "        \r\n"+
                                                "        StringBuffer titleBuf = new StringBuffer();\r\n"+
                                                "        List fsmList = jxpb.getStateMachine();\r\n"+
                                                "        for(int ii=0; ii<fsmList.size(); ii++) {\r\n"+
                                                "          org.jts.jsidl.binding.StateMachine jxfsm = (org.jts.jsidl.binding.StateMachine) fsmList.get(ii);\r\n"+
                                                "          titleBuf.append( jxfsm.getName() );\r\n"+
                                                "          if( ii != fsmList.size()-1 )\r\n"+
                                                "            titleBuf.append( \", \" );\r\n"+
                                                "        }");
        FindReplace.replaceInFile( "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ProtocolBehavior.java", 
                                                 "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ProtocolBehavior.java", 
                                                "return new Title(text.toString());",
                                                "return new Title(titleBuf.toString() + \"  [ProtocolBehavior]\");");
                                                
        
       // command
         FindReplace.replaceInFile( "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ProtocolBehavior.java", 
                                                 "src" + File.separator + "com" + File.separator + "u2d" + File.separator + "generated" + File.separator + "ProtocolBehavior.java", 
                                                 "/**@Cmd place holder**/", 
                                                 "public com.u2d.view.EView getMainView()   {\r\n"+
                                                  "  try {\r\n"+
                                                  "    if( getReferencingServiceDefs() != null && getReferencingServiceDefs().getItems().size() > 0) \r\n"+
                                                  "      org.jts.validator.Validator.validateName( ((ServiceDef)getReferencingServiceDefs().getItems().get(0)).getName().stringValue() );\r\n"+
                                                  "  } catch( org.jts.validator.ValidatorException ve ) {            \r\n"+
                                                  "    JOptionPane.showMessageDialog( null, \"Please enter a valid name for the associated service definition first\", \"Protocol Behavior Error\", JOptionPane.ERROR_MESSAGE);\r\n"+
                                                  "    return null;\r\n"+
                                                  "  } \r\n"+
                                                  "  \r\n"+
                                                  "  return new com.mxgraph.swing.examples.FreeGraphEditor(this);\n"+
                                                 "}\r\n"+
                                                 "\r\n"); 
      } catch(IOException ioe) {
        ioe.printStackTrace();
      }
  }
} 

