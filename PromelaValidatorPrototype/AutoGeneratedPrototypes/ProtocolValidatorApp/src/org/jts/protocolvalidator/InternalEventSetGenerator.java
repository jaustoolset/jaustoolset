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
import org.jts.jsidl.binding.*;

/**
 * Used to store JSIDL internal event data and produce Promela code for the internal events
 * @author cmessmer
 */
class InternalEventSetGenerator {

    // list of message channel definitions generated from event set
    private List<String> definitions;
    // list of channels, used to make sure we don't redefine for a different object
    private List<String> messageChannels;
    // id for the parent object
    private String ID;
    // mapping of references to id
    private Map<String, String> refMap;

    /**
     * Creates code generator for internal events
     * @param eventSet - the JSIDL event set to parse
     * @param fileFinder - finder for file inheritance or dependence
     * @param JSIDLSchemaPath - path for JSIDL schema
     * @param id - id of the parent object
     * @param refmap - mapping of references to names
     * @param enumlist - list of enums already produced
     */
    public InternalEventSetGenerator(InternalEventsSet eventSet, FileFinder fileFinder,
            String JSIDLSchemaPath, String id, Map<String, String> refmap, Map<String, String> enumlist) {
        ID = id;
        refMap = refmap;

        definitions = new ArrayList<String>();
        messageChannels = new ArrayList<String>();

        loadEventSet(eventSet);
    }

    /**
     * Retrieve output for the event set
     * @return - Promela code for the event set
     */
    List<String> getEventSetOutput() {
        return definitions;
    }

    /**
     * Loads the JSIDL binding into this object and processes the data
     * @param eventSet - the input set
     */
    private void loadEventSet(InternalEventsSet eventSet) {
        // process the input set
        definitions.addAll(Util.formatCommentString(eventSet.getInterpretation(), false));
        List<Object> indefs = eventSet.getEventDefOrDeclaredEventDef();
        for (Object tmpobj : indefs) {
            if (tmpobj instanceof EventDef) {
                definitions.addAll(getEventDef((EventDef) tmpobj));
                messageChannels.addAll(getMessageChannel((EventDef) tmpobj));
            } else if (tmpobj instanceof DeclaredEventDef) {
                definitions.addAll(getDeclaredEventDef((DeclaredEventDef) tmpobj));
            }
        }

        // separate channels from next section
        definitions.add("\n\n");

    }

    /**
     * Retrieves the channel definition code
     * @return - Promela code for channel defintions for these events
     */
    public List<String> getEventMessages() {
        return messageChannels;
    }

    /**
     * Generates code for a declared event def
     * @param inputdef - the input JSIDL binding
     * @return - Promela code for the event def
     */
    private List<String> getDeclaredEventDef(DeclaredEventDef inputdef) {
        List<String> output = new ArrayList<String>();
        String name = inputdef.getName();
        String typeref = inputdef.getDeclaredTypeRef();

        output.add("");
        output.addAll(Util.formatCommentString(inputdef.getInterpretation(), false));
        String channel = "chan " + inputdef.getName() + " = of {pid, ";

        // find corresponding definition
        String type = Util.getTypeNameFromDeclTypeRef(refMap, inputdef.getName(), ID);
        //

        channel += type + "};";
        output.add(channel);
        return output;
    }

    /**
     * Generate code for the message channel associated with an input event def
     * @param inputdef - the JSIDL data binding
     * @return - Promela code for the message channel
     */
    private List<String> getMessageChannel(EventDef inputdef) {
        List<String> output = new ArrayList<String>();
        String dataName = Util.getTypeNameFromDeclTypeRef(refMap, inputdef.getName(), ID);
        output.add("");
        if (inputdef.getDescription() != null) {
            output.addAll(Util.formatCommentString(inputdef.getDescription().getContent(), false));
        }
        String channel = "chan " + inputdef.getName() + " = [QUEUE_SIZE] of {pid};";
        output.add(channel);

        return output;
    }

    /**
     * Generates Promela code for the event def
     * @param inputdef - JSIDL binding to process
     * @return - Promela code generated for the event def
     */
    private List<String> getEventDef(EventDef inputdef) {
        List<String> output = new ArrayList<String>();

        // stores output that needs to wait to be written
        List<String> waitingOutput = new ArrayList<String>();

        // get some preliminary data
        Description descr = inputdef.getDescription();
        if (descr != null) {
            waitingOutput.addAll(Util.formatCommentString(descr.getContent(), true));
        }

        String name = Util.getTypeNameFromDeclTypeRef(refMap, inputdef.getName(), ID);
        waitingOutput.add("typedef " + name + "{");
        waitingOutput.add("\t// data added to avoid empty type definitions.");
        waitingOutput.add("\tbit reserved;");

        // definitions and declarations need to be kept separate
        // since Promela doesn't support nested data type definitions
        // so we write the internal definitions first and then write the
        // main definition.

        //TODO: Need to fix this section, since it causes syntax errors but it isn't being used right now anyhow.
        // process the header
//        Header header = inputdef.getHeader();
//        if (header != null) {
//            List<String> tmpout = msgs.processHeader(header, ID);
//            output.addAll(tmpout);
//        }
//        DeclaredHeader declHeader = inputdef.getDeclaredHeader();
//        if (declHeader != null) {
//            List<String> tmpout = msgs.processDeclaredHeader(declHeader, ID);
//            Util.indent(1, tmpout);
//            waitingOutput.addAll(tmpout);
//        }
//
//        // process the body
//        Body body = inputdef.getBody();
//        if (body != null) {
//            List<String> tmpout = msgs.processBodyDef(body, ID);
//            output.addAll(tmpout);
//            waitingOutput.addAll(msgs.processBody(body, ID));
//        }
//        DeclaredBody declBody = inputdef.getDeclaredBody();
//        if (declBody != null) {
//            List<String> tmpout = msgs.processDeclaredBody(declBody, ID);
//            Util.indent(1, tmpout);
//            waitingOutput.addAll(tmpout);
//        }
//
//        // process the footer
//        Footer footer = inputdef.getFooter();
//        if (footer != null) {
//            List<String> tmpout = msgs.processFooter(footer, ID);
//            output.addAll(tmpout);
//        }
//        DeclaredFooter declFooter = inputdef.getDeclaredFooter();
//        if (declFooter != null) {
//            List<String> tmpout = msgs.processDeclaredFooter(declFooter, ID);
//            Util.indent(1, tmpout);
//            waitingOutput.addAll(tmpout);
//        }

        waitingOutput.add("};");
        output.addAll(waitingOutput);
        return output;
    }
}
