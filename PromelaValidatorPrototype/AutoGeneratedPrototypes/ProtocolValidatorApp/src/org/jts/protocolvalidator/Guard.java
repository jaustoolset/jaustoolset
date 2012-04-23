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
import org.apache.commons.lang.StringUtils;

/**
 * Used to contain guard data and produce Promela code
 * @author cmessmer
 */
public class Guard {

    // condition that must be satisfied for guard to pass
    private String condition;
    // comment data from the JSIDL
    private String interp;
    // message data if the transition received a message
    private String messageData = "";
    // length of the _inst added to the end of data types for the instance name;
    static final int INST_LEN = 5;

    /**
     * Creates a new guard code generation object
     * @param guard - input JSIDL guard data binding.
     */
    public Guard(org.jts.jsidl.binding.Guard guard) {
        interp = guard.getInterpretation();
        condition = formatCondition(guard.getCondition());
    }

    /**
     * Generates code for this guard.
     * @return - list of code lines.
     */
    public List<String> getCode(String msgData) {
        messageData = msgData;
        List<String> output = new ArrayList<String>();
        output.addAll(Util.formatCommentString(interp, true));
        String tmpcond = this.getGuardString();
        output.add(tmpcond);
        return output;
    }

    /**
     * Retrieve a list of condition elements, since a condition can contain
     * any number of elements combined with logical operators.
     * @param msgData - message data instance name
     * @return - list of condition elements
     */
    public List<String> getConditionElement() {
        List<String> output = new ArrayList<String>();
        String tmpcond = Util.formatConditionString(condition);

        // get rid of some of the whitespace
        // remove all logical operators, since we are concerned with just the elemenats.
        tmpcond = tmpcond.replace(" && ", " \t ");
        tmpcond = tmpcond.replace(" || ", " \t ");
        tmpcond = tmpcond.replaceAll("!", "");
        // if the comments are too long, then break them up
        // on multiple lines.  at the same time remove additional spaces.
        // The assumption is that these conditions don't normally contain tabs within the element.

        String[] tmpstr = tmpcond.split("[\t]+");
        for (String element : tmpstr) {
            element = StringUtils.strip(element);
            element = StringUtils.deleteWhitespace(element);
            output.add(element);
        }

        return output;
    }

    /**
     * Retrieve a list of Promela guard function definitions
     * @return - the list of Promela code for definitions
     */
    public List<String> getConditionList() {
        List<String> output = new ArrayList<String>();
        String tmpcond = Util.formatConditionString(condition);
        tmpcond = tmpcond.replaceAll("(msg)", "incoming_pid, " + messageData);
        tmpcond = tmpcond.replace("transportData", "incoming_pid");

        // get rid of some of the whitespace
        // remove all logical operators, since we are concerned with just the elemenats.
        tmpcond = tmpcond.replace(" && ", " \t ");
        tmpcond = tmpcond.replace(" || ", " \t ");
        tmpcond = tmpcond.replaceAll("!", "");
        // if the comments are too long, then break them up
        // on multiple lines.  at the same time remove additional spaces.
        // The assumption is that these conditions don't normally contain tabs within the element.

        String[] tmpstr = tmpcond.split("[\t]+");
        for (String element : tmpstr) {
            element = StringUtils.strip(element);
            element = StringUtils.deleteWhitespace(element);
            // add prefix to make this defintion unique
            if (messageData.length() > INST_LEN && element.contains(messageData)) {
                element = messageData.substring(0, messageData.length() - INST_LEN) + "_" + element;
            }
            output.add("#define " + element + " (true)");
        }

        return output;
    }

    /**
     * Get comments for this guard
     * @return formatted comments
     */
    public List<String> getComments() {
        List<String> output = new ArrayList<String>();
        output.addAll(Util.formatCommentString(interp, true));
        output.add("// NOTICE: replace true with the code necessary for checking the guard.  The parens are required around that code");
        return output;
    }

    /**
     * Replaces xml escape sequences with characters.  Only handle &amp; at this time.
     * @param input - input condition.
     * @return - formatted condition.
     */
    private String formatCondition(String input) {
        input.replace("&amp;", "&");
        return input;
    }

    /**
     * Retrieve the guard code line
     * @param msgData - the data type represented by the Transition's data
     * @return - code line for the guard.
     */
    String getGuardString() {
        String tmpcond = "";
        if (!condition.isEmpty()) {
            tmpcond = Util.formatConditionString(condition);
            List<String> conditions = getConditionElement();
            for (String condElem : conditions) {
                String newElem = "";
                if (messageData.length() > INST_LEN && condElem.contains("(msg)")) {
                    newElem = messageData.substring(0, messageData.length() - INST_LEN) + "_" + condElem;
                    tmpcond = tmpcond.replace(condElem, newElem);
                }
            }

            // replace message and transport parameters with the message instance or client process id(pid)
            tmpcond = tmpcond.replaceAll("(msg)", "incoming_pid, " + messageData);
            tmpcond = tmpcond.replaceAll("transportData", "incoming_pid");
            tmpcond = ":: " + tmpcond + " ->";
        }
        return tmpcond;
    }
}
