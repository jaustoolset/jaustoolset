/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2011, United States Government
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
package org.jts.protocolvalidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jts.jsidl.binding.BitField;
import org.jts.jsidl.binding.Body;
import org.jts.jsidl.binding.DeclaredBody;
import org.jts.jsidl.binding.DeclaredFooter;
import org.jts.jsidl.binding.DeclaredHeader;
import org.jts.jsidl.binding.DeclaredMessageDef;
import org.jts.jsidl.binding.Description;
import org.jts.jsidl.binding.FixedField;
import org.jts.jsidl.binding.Footer;
import org.jts.jsidl.binding.Header;
import org.jts.jsidl.binding.MessageDef;
import org.jts.jsidl.binding.Record;

/**
 * Used to process definitions found in a JSIDL data binding object and write it to a file.
 * @author cmessmer
 */
public class DefinitionGenerator extends JTSFileWriter {

    private final Map<String, String> refMap;
    private final String classID;
    private MessageProcessor msgProcessor;
    private DataTypeProcessor datatypes;
    private Map<String, String> enumList;

    public DefinitionGenerator(List<Object> root, String filepath,
            List<String> includelist, Map<String, String> refmap, String inputID,
            String version, Map<String, String> enumlist) throws Exception {
        super(filepath, includelist, inputID, version, false);
        classID = inputID;
        refMap = refmap;
        enumList = enumlist;

        // use this to process message data
        msgProcessor = new MessageProcessor(inputID, refmap, enumList);
        // use this to process basic data types
        datatypes = new DataTypeProcessor(inputID, refmap, enumList);


        processDefinitions(root);

    }

    /**
     * Main definition processor and Promela generator for a declared_type_set
     * @param list The list of objects found in the set
     * @return - generated code
     */
    private void processDefinitions(List<Object> list) throws Exception {
        List<String> defs = new ArrayList<String>();
        // figure out what type of object and process accordingly
        for (Object tmpObj : list) {
            if (tmpObj instanceof BitField) {
                BitField bitField = (BitField) tmpObj;
                defs.addAll(datatypes.processBitFieldDef(bitField, classID));

            } else if (tmpObj instanceof Header) {
                Header header = (Header) tmpObj;
                defs.addAll(msgProcessor.processHeaderDef(header, classID));
            } else if (tmpObj instanceof FixedField) {
                FixedField field = (FixedField) tmpObj;
                defs.addAll(datatypes.processFixedFieldDef(field, classID));
            } else if (tmpObj instanceof Record) {
                Record rec = (Record) tmpObj;
                defs.addAll(datatypes.processRecordDef(rec, classID));
            } else if (tmpObj instanceof MessageDef) {
                MessageDef msg = (MessageDef) tmpObj;
                defs.addAll(processMessageDef(msg));
            } else if (tmpObj instanceof DeclaredMessageDef) {
                // this reference is resolved and the declarations handled elsewhere
            } else {
                Logger.getLogger(JTSFileWriter.class.getName()).log(Level.WARNING, "not yet supporting this definition type...");
            }
        }
        // write out the data
        writeDataToFile(defs);
    }

    /**
     * Processes the message definition and writes the promela code
     * @param msgDef - data binding
     * @return - List of code lines
     */
    private List<String> processMessageDef(MessageDef msgDef) throws Exception {
        List<String> output = new ArrayList<String>();
        int startOuputSize = 0;
        // stores output that needs to wait to be written
        List<String> waitingOutput = new ArrayList<String>();

        // get some preliminary data
        Description descr = msgDef.getDescription();
        if (descr != null) {
            waitingOutput.addAll(Util.formatCommentString(descr.getContent(), true));
        }
        // need to know if our typedef contains something
        startOuputSize = waitingOutput.size();

        // get type from reference to the type
        String name = Util.getTypeNameFromDeclTypeRef(refMap, msgDef.getName(), classID);
        refMap.put(msgDef.getName(), name);
        boolean cmd = msgDef.isIsCommand();
        waitingOutput.add("typedef " + name + "{");

        // definitions and declarations need to be kept separate
        // since Promela doesn't support nested data type definitions
        // so we write the internal definitions first and then write the
        // main definition.

        // process the header
        Header header = msgDef.getHeader();
        if (header != null) {
            List<String> tmpout = msgProcessor.processHeaderDef(header, classID + ":" + msgDef.getName());
            output.addAll(tmpout);
            waitingOutput.addAll(msgProcessor.processHeader(header, classID + ":" + msgDef.getName()));
        }
        DeclaredHeader declHeader = msgDef.getDeclaredHeader();
        if (declHeader != null) {
            List<String> tmpout = msgProcessor.processDeclaredHeader(declHeader, classID + ":" + msgDef.getName());
            Util.indent(1, tmpout);
            waitingOutput.addAll(tmpout);
        }

        // process the body
        Body body = msgDef.getBody();
        if (body != null) {
            List<String> tmpout = msgProcessor.processBodyDef(body, classID + ":" + msgDef.getName());
            output.addAll(tmpout);
            waitingOutput.addAll(msgProcessor.processBody(body, classID + ":" + msgDef.getName()));
        }
        DeclaredBody declBody = msgDef.getDeclaredBody();
        if (declBody != null) {
            List<String> tmpout = msgProcessor.processDeclaredBody(declBody, classID + ":" + msgDef.getName());
            Util.indent(1, tmpout);
            waitingOutput.addAll(tmpout);
        }

        // process the footer
        Footer footer = msgDef.getFooter();
        if (footer != null) {
            List<String> tmpout = msgProcessor.processFooter(footer, classID + ":" + msgDef.getName());
            output.addAll(tmpout);
        }
        DeclaredFooter declFooter = msgDef.getDeclaredFooter();
        if (declFooter != null) {
            List<String> tmpout = msgProcessor.processDeclaredFooter(declFooter, classID + ":" + msgDef.getName());
            Util.indent(1, tmpout);
            waitingOutput.addAll(tmpout);
        }
        // if we didn't add anthing to the typedef, then add a bogus member so that
        // it doesn't cause a syntax error
        if (waitingOutput.size() == startOuputSize) {
            waitingOutput.add("\tbit reserved;");
        }
        waitingOutput.add("};");
        output.addAll(waitingOutput);
        return output;
    }
}
