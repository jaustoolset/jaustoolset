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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jts.jsidl.binding.*;

/**
 *  This class is used to generate message definition code lines in Promela,
 *  based on the data found in a JSIDL MessageSet.
 * @author cmessmer
 */
class MessageSetGenerator {

    // the output definitions
    private List<String> definitions;
    // output for the client implementation
    private List<String> clientImpl;
    // instances of data used in the application
    // process scope definition seems to be necessary
    private List<String> dataInst;
    // parent's ID
    private String ID;
    // a local link to the reference map
    private Map<String, String> refMap;
    // a local link to a mapping between the data name and type
    private Map<String, String> messageDataMap;

    /**
     * Initializes the data for the MessageSetGenerator
     * @param msgSet - the input message set
     * @param fileFinder - helper class used to find files based on reference
     * @param JSIDLSchemaPath - the path for the JSIDL schema
     * @param id - the id of the parent of the MessageSet
     * @param refmap - mapping of name to reference
     */
    MessageSetGenerator(MessageSet msgSet, String id, Map<String, String> refmap) {
        ID = id;
        refMap = refmap;

        definitions = new ArrayList<String>();
        clientImpl = new ArrayList<String>();
        dataInst = new ArrayList<String>();
        messageDataMap = new HashMap<String, String>();
        loadMessageSet(msgSet);
    }

    /**
     * Loads the data from a message set, to build message data from.
     * @param msgSet - the input MessageSet JSIDL binding
     */
    private void loadMessageSet(MessageSet msgSet) {
        // process the input set
        definitions.add("\n\n// " + ID + " Input Channels");
        InputSet input = msgSet.getInputSet();
        definitions.addAll(Util.formatCommentString(input.getInterpretation(), false));
        List<Object> indefs = input.getMessageDefOrDeclaredMessageDef();
        for (Object tmpobj : indefs) {
            if (tmpobj instanceof MessageDef) {
                definitions.addAll(getMessageDef((MessageDef) tmpobj, false));
                clientImpl.addAll(getMessageDefClientImpl((MessageDef) tmpobj));
                dataInst.addAll(getMessageDataInstClientImpl((MessageDef) tmpobj));
            } else if (tmpobj instanceof DeclaredMessageDef) {
                definitions.addAll(getDeclaredMessageDef((DeclaredMessageDef) tmpobj, false));
                clientImpl.addAll(getDeclaredMessageDefClientImpl((DeclaredMessageDef) tmpobj));
                dataInst.addAll(getDeclaredMessageDataInstClientImpl((DeclaredMessageDef) tmpobj));
            }
        }

        // separate input message channels from output channels
        definitions.add("\n\n// " + ID + " Output Channels");

        // process the output set
        OutputSet output = msgSet.getOutputSet();
        definitions.addAll(Util.formatCommentString(output.getInterpretation(), false));
        List<Object> outdefs = output.getMessageDefOrDeclaredMessageDef();
        for (Object tmpobj : outdefs) {
            if (tmpobj instanceof MessageDef) {
                definitions.addAll(getMessageDef((MessageDef) tmpobj, true));
            } else if (tmpobj instanceof DeclaredMessageDef) {
                definitions.addAll(getDeclaredMessageDef((DeclaredMessageDef) tmpobj, true));
            }
        }
    }

    /**
     * Retrieve a few code lines that implement sending a message from client to server.
     * @param inputdef - The input message definition
     * @return - code lines including data declaration for the message content.
     */
    private List<String> getDeclaredMessageDefClientImpl(DeclaredMessageDef inputdef) {
        List<String> output = new ArrayList<String>();
        String typeref = inputdef.getDeclaredTypeRef();

        output.add("");
        output.addAll(Util.formatCommentString(inputdef.getInterpretation(), false));

        // find corresponding definition
        String type = resolveDeclaredMessageType(typeref);
        String sendCmd = ":: " + inputdef.getName() + " ! _pid, " + type + "_impl;";

        output.add(sendCmd);
        return output;
    }

    /**
     * Retrieve a few code lines that implement sending a message from client to server.
     * @param inputdef - The input message definition
     * @return - code lines including data declaration for the message content.
     */
    private List<String> getMessageDefClientImpl(MessageDef inputdef) {
        List<String> output = new ArrayList<String>();
        String typeref = inputdef.getName();  // TODO: fix this .getTypeRef();

        output.add("");
        output.addAll(Util.formatCommentString(inputdef.getDescription().getContent(), false));

        // find corresponding definition
        String type = resolveDeclaredMessageType(typeref);
        String sendCmd = inputdef.getName() + " ! _pid, " + type + "_impl;";

        output.add(sendCmd);

        return output;
    }

    /**
     * Retrieve a few code lines that implement data instantiation for a message.
     * @param inputdef - The input message definition
     * @return - code lines including data declaration for the message content.
     */
    private List<String> getDeclaredMessageDataInstClientImpl(DeclaredMessageDef inputdef) {
        List<String> output = new ArrayList<String>();
        String typeref = inputdef.getDeclaredTypeRef();

        // find corresponding definition
        String type = resolveDeclaredMessageType(typeref);
        String typeDecl = type + " " + type + "_impl;";

        output.add(typeDecl);
        return output;
    }

    /**
     * Retrieve a few code lines that implement data instantiation for a message.
     * @param inputdef - The input message definition
     * @return - code lines including data declaration for the message content.
     */
    private List<String> getMessageDataInstClientImpl(MessageDef inputdef) {
        List<String> output = new ArrayList<String>();
        String typeref = inputdef.getName();  // TODO: fix this .getTypeRef();

        // find corresponding definition
        String type = resolveDeclaredMessageType(typeref);
        String typeDecl = type + " " + type + "_impl;";

        output.add(typeDecl);

        return output;
    }

    /**
     * Retrieves the code lines for the Promela defintions of the messages
     * @param inputdef - a DeclaredMessageDef JSIDL binding
     * @return - code lines for the definition
     */
    private List<String> getDeclaredMessageDef(DeclaredMessageDef inputdef, boolean isOutput) {
        List<String> output = new ArrayList<String>();
        String clientChannels = "[CLIENT_CHANNELS]";
        String name = inputdef.getName();
        String typeref = inputdef.getDeclaredTypeRef();

        // output messages need to be on an array of channels so each client
        // only gets the messages intended for it.  Input channels are for a single server
        // so no array is necessary.
        if (!isOutput) {
            clientChannels = "";
        }
        output.add("");
        output.addAll(Util.formatCommentString(inputdef.getInterpretation(), false));
        String channel = "chan " + inputdef.getName() + clientChannels + " = [QUEUE_SIZE] of {pid, ";

        // find corresponding definition
        String type = resolveDeclaredMessageType(typeref);

        channel += type + "};";
        messageDataMap.put(inputdef.getName(), type);
        output.add(channel);
        return output;
    }

    /**
     * Retrieve the code lines for a message definition
     * @param inputdef - the MessageDef being processed
     * @param isOutput - true if this is an output message
     * @return - Promela code lines
     */
    private List<String> getMessageDef(MessageDef inputdef, boolean isOutput) {
        List<String> output = new ArrayList<String>();
        String clientChannels = "[CLIENT_CHANNELS]";
        // output messages need to be on an array of channels so each client
        // only gets the messages intended for it.  Input channels are for a single server
        // so no array is necessary.
        if (!isOutput) {
            clientChannels = "";
        }
        
        String type = Util.getNameFromID(ID) + "_" + inputdef.getName();

        output.addAll(Util.formatCommentString(inputdef.getDescription().getContent(), false));
        // chan <msg name> = [<size of message buffer>] of { <comma delim set of data types making up the message>}
        String channel = "chan " + inputdef.getName()  + clientChannels +  " = [QUEUE_SIZE] of {pid, " + type + "}; ";
        messageDataMap.put(inputdef.getName(), type);
        output.add(channel);
        return output;
    }

    /**
     * Retrieves all the code lines for the message set definitions in Promela
     * @return Promela code lines for the message set definitions
     */
    public List<String> getMessageSetOutput() {
        return definitions;
    }

    /**
     * Retrieves the code lines used to send the messages found in this message set
     * @return - Promela code lines used for the client to send messages
     */
    public List<String> getClientMessageImplementation() {
        return clientImpl;
    }

    /**
     * Retrieve the string associated with a specific type, based on parent ID,
     * type reference, and a reference mapping
     * @param typeref - the type reference to generate a message type from
     * @return
     */
    private String resolveDeclaredMessageType(String typeref) {

        return Util.getTypeNameFromDeclTypeRef(refMap, typeref, ID);
    }

    /**
     * Retrieve the reference to the message data map
     * @return - the message data map
     */
    public Map<String, String> getMessageDataMap() {
        return messageDataMap;
    }

    /**
     * Retrieve the client data definitions
     * @return - Promela code for client data
     */
    List<String> getClientDataDefinitions() {
        return dataInst;
    }
}
