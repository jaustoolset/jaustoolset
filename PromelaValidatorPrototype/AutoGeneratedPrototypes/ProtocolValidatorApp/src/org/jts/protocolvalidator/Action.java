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
import org.jts.jsidl.binding.Argument;

/**
 * Action object used to generate Promela code for a JSIDL action.
 * @author cmessmer
 */
public class Action {

    // length of the _inst added to the end of data types for the instance name;
    static final int INST_LEN = 5;
    // list of arguments used by the action
    private List<String> arguments;
    // name of the action
    private String name;
    // comments that describe the action, taken from the JSIDL
    private String interp;
    // flag for showing an action that sends a message
    private boolean isSendAction;
    // Actions are part of a transition. if the transition includes receiving
    // a message, then the action needs to have access to the message data.
    private String messageData = "";

    /**
     * Constructs an action code generation object
     * @param action - input action data binding
     */
    public Action(org.jts.jsidl.binding.Action action) {
        interp = action.getInterpretation();
        name = action.getName();
        isSendAction = false;
        if (Util.getNameFromRef(name).equals("Send")) {
            isSendAction = true;
        }
        loadArguements(action.getArgument());
    }

    /**
     * Constructs a send action code generation object
     * @param action - input send action data binding
     */
    public Action(org.jts.jsidl.binding.SendAction action) {
        interp = action.getInterpretation();
        name = action.getName();
        loadArguements(action.getArgument());
        isSendAction = true;
    }

    /**
     * Loads argument data into the object.
     * @param inputargument - input argument list
     */
    private void loadArguements(List<Argument> inputargument) {
        arguments = new ArrayList<String>();
        for (Argument arg : inputargument) {
            arguments.add(arg.getValue());
        }
    }

    /**
     * Generates code for the Action
     * @return - generated code lines
     */
    public List<String> getCode(String msgData) {
        messageData = msgData;
        List<String> output = new ArrayList<String>();
        output.addAll(Util.formatCommentString(interp, false));
        output.add(getActionString(false));
        return output;
    }

    /**
     * Generates the Promela definition for this action
     * @return - code lines representing the definition for the action
     */
    public List<String> getActionDefinition() {
        List<String> output = new ArrayList<String>();
        output.add("");
        output.addAll(Util.formatCommentString(interp, true));
        String actionStr = getActionString(true);
        actionStr = actionStr.replace(';', '{');
        output.add("inline " + actionStr);
        output.add(" //Replace this print statement with a code line ending with a ;");
        output.add("// Ending a line with a back-slash allows the definiton to continue on the next line.");
        output.add("// This can be repeated as many times as necessary.  All 3 of these lines would make up the definition.");
        output.add("printf(\"executing action: " + getActionString(true) + "\");");

        output.add("}");
        output.add("");
        return output;
    }

    /**
     * Retrieve the action code line.
     * @param msgData - the data passed in by the transition
     * @return - code line for the action
     */
    public String getActionString(boolean isDefinition) {
        String actionStr = "";
        String msgData = messageData;
        String inputPID = "_pid";
        String newPrefix = "";

        // use a prefix for the action, if the action contains message data
        // this is necessary to make the action function unique and avoid Promela syntax errors
        if (messageData.length() > INST_LEN) {
            newPrefix = messageData.substring(0, messageData.length() - INST_LEN) + "_";
        }

        // since this function handles both definitions and instances, need to know which this is
        if (isDefinition) {
            if (!messageData.isEmpty()) {
                msgData = "inputMessage";
            }
            inputPID = "sender_pid";
        }
        // sending information in an action requires different parameters
        if (isSendAction) {
            boolean first = true;
            int paramCount = 1;
            for (String tmparg : arguments) {
                // most strings need to be formatted for consistency since the JSIDL doesn't enforce any
                tmparg = Util.formatAsWord(tmparg);
                if (first) {
                    actionStr = newPrefix + "Action_Send_" + tmparg + "(" + inputPID;
                    first = false;
                } else {
                    // definitions can use generic parameter names, while implementations require a specific variable
                    if (isDefinition && !tmparg.equals("msg") && !tmparg.equals("transportData")) {
                        actionStr += ", param" + paramCount;
                        paramCount++;
                    } else {
                        actionStr += ", " + tmparg;
                    }

                }
            }
            actionStr += ");";
            // received messages pass along information about the message and the transport
            // in our case, we need the actual message instance
            // the promela implementation for transportData is just passing along the client's pid
            actionStr = actionStr.replaceAll(", msg,", ", " + msgData + ",");
            actionStr = actionStr.replaceAll("transportData", "incoming_pid");
        } else {
            // it's a much simpler process if we aren't sending a message
            actionStr = newPrefix + "Action_" + name + "(" + msgData + ");";
        }

        return actionStr;
    }
}
