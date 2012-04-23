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
import org.apache.commons.lang.StringUtils;

/**
 * utility class used for common string manipulations.
 * @author cmessmer
 */
public class Util {

    final static int LINE_LEN = 80;

    /**
     * Taken from JTS, this function is used to indent a list of code lines.
     * @param indentLevel - how many tabs to insert for each line
     * @param strVector - the list of code lines that the indent shoudl be applied to.
     */
    public static void indent(int indentLevel, List<String> strVector) {
        int i = 0;
        for (String line : strVector) {
            strVector.set(i++, "\t" + line);
        }
    }

    /**
     * Retrieves the name from the end of an id string.
     * @param id - the id to be processed
     * @return - the last segment of an id string.
     */
    public static String getNameFromID(String id) {
        String name = "";
        if (id == null || id.isEmpty()) {
            return "";
        }
        // get tokens separated by :
        String[] tmpstr = id.split("[.:]");
        boolean firsttime = true;
        for (String part : tmpstr) {
            if (firsttime) {
                name = part;
                firsttime = false;
            } else {
                name += "_" + part;
            }
        }

        return name;
    }

    /**
     * Retrieves the name from the end of an reference string.
     * @param ref - the reference to be processed
     * @return - the last segment of an id string.
     */
    public static String getNameFromRef(String ref) {
        String name = "";
        if (ref == null || ref.isEmpty()) {
            return "";
        }
        String[] tmpstr = ref.split("[.]");
        if (tmpstr.length > 0) {
            name = tmpstr[tmpstr.length - 1];
        }
        return name;
    }

    /**
     * Creates a new name by replacing . in the id with _
     * @param ref - the JSIDL id string
     * @return - a formatted name with id information embedded
     */
    public static String formatRefName(String ref) {
        String name = "";
        if (ref == null || ref.isEmpty()) {
            return "";
        }
        String[] tmpstr = ref.split("[.]");
        boolean firsttime = true;
        for (String part : tmpstr) {
            if (firsttime) {
                name = part;
                firsttime = false;
            } else {
                name += "_" + part;
            }
        }

        return name;
    }

    /**
     * Creates a file name from a given id string
     * @param ID the ID from a top level element in a JSIDL file
     * @param isStatic true if this file should not be modified by the end user
     * @return the new promela file name corresponding to the JSIDL file that was processed.
     */
    public static String getFileNameFromID(String ID, boolean isStatic) {
        String fileName = getNameFromID(ID);
        String postfix = "";
        if (isStatic) {
            postfix = "_static";
        }
        fileName += postfix + ".pml";

        return fileName;
    }

    /**
     * Process references to find an appropriate id.  In some cases the base id will come from the parent, if
     * the element being processed isn't the root element for the document.
     * @param refmap a map of references to ids that were previously noted in the JSIDL
     * @param refid the reference id
     * @param currentTypeID The id for the parent of this node, if there is one.
     * @return a valid type name based on the input data
     */
    public static String getTypeNameFromDeclTypeRef(Map refmap, String refid, String currentTypeID) {
        String typename = "";
        if (refid.isEmpty()) {
            return "UNKNOWN REFERENCE ERROR";
        }

        String[] tmpstr = refid.split("[.]");
        if (tmpstr.length == 1) {
            // the reference is in the current file, so add the current class's id to the name
            String[] tmpids = currentTypeID.split("[:]");
            if (tmpids[tmpids.length - 1].equals(refid)) {
                typename = getNameFromID(currentTypeID);
            } else {
                typename = getNameFromID(currentTypeID) + "_" + refid;
            }
        } else if (tmpstr.length >= 2) {
            // the only problem here is that this reference map assumes that reference names are not reused

            String ref = tmpstr[tmpstr.length - 2];
            String type = tmpstr[tmpstr.length - 1];
            // get the reference from the reference map
            String id = (String) refmap.get(ref);
            if (id == null) {
                return "UNKNOWN REFERENCE ERROR";
            }

            typename = getNameFromID(id) + "_" + type;

        } else {
            return "UNKNOWN REFERENCE ERROR";
        }

        return typename;
    }

    /**
     * Takes a poorly formatted description or interpretation string and formats it
     * so that it wraps at the proper column and also removes all the obnoxious whitespace.
     * This is necessary due to the formatting imposed by the comments in the JSIDL.
     * @param comment - The input string that will become a comment in the Promela code.
     * @param isBlockComment - true if this is a block comment. Otherwise, it is a line comment
     *                          and will be broken up into multiple line comments.
     * @return - a single comment string that may represent multiple lines.
     */
    public static List<String> formatCommentString(String comment, boolean isBlockComment) {
        List<String> output = new ArrayList<String>();
        if (comment == null || comment.isEmpty()) {
            return output;
        }
        // figure out how many tabs are being used so we keep good alignment
        if (isBlockComment) {
            output.add("/**");
        }
        String returnString = "";
        // get rid of some of the whitespace
        comment = StringUtils.strip(comment);

        // if the comments are too long, then break them up
        // on multiple lines.  at the same time remove additional spaces.
        String[] tmpstr = comment.split("[ \n\t]+");
        long count = 0;
        for (String word : tmpstr) {
            // start off the comment line with the appropriate markers
            if (returnString.isEmpty()) {
                if (isBlockComment) {
                    returnString = "* ";
                } else {
                    returnString = "// ";
                }
            }
            // many whitespace characters are embedded in these strings
            // make sure we remove them.
            returnString += " " + StringUtils.strip(word);
            count += word.length();
            if (count > LINE_LEN) {
                count = 0;
                output.add(returnString);
                returnString = "";
            }

        }
        if (!returnString.isEmpty()) {
            output.add(returnString);
        }
        if (isBlockComment) {
            output.add("*/");
        }
        return output;
    }

    /**
     * Takes a poorly formatted description or interpretation string and formats it
     * so that it wraps at the proper column and also removes all the obnoxious whitespace.
     * This is necessary due to the formatting imposed by the comments in the JSIDL.
     * @param comment - The input string that will become a comment in the Promela code.
     * @param isBlockComment - true if this is a block comment. Otherwise, it is a line comment
     *                          and will be broken up into multiple line comments.
     * @return - a single comment string that may represent multiple lines.
     */
    public static String formatConditionString(String condition) {
        if (condition == null || condition.isEmpty()) {
            return condition;
        }
        String returnString = "";
        // get rid of some of the whitespace
        condition = StringUtils.strip(condition);

        // if the comments are too long, then break them up
        // on multiple lines.  at the same time remove additional spaces.
        String[] tmpstr = condition.split("[ \n\t]+");

        for (String word : tmpstr) {
            returnString += " " + StringUtils.strip(word);
        }

        return returnString;
    }

    /**
     * Clean up word formatting issues found in JTS enum constants
     * @param inputString the input string that should be represented as a single word
     * @return the single word result after cleanup
     */
    public static String formatAsWord(String inputString) {
        // many of our identifiers found in the JSIDL are multiple words or contain punctuation
        // remove punctuation and other special characters
        inputString = inputString.replaceAll("[^A-Za-z_]", "");
        // remove whitespace
        String[] tmpstr = inputString.split("[ \n\t]+");
        String tmpOutput = "";
        if (tmpstr.length > 1) {
            boolean isfirst = true;
            for (String word : tmpstr) {
                if (isfirst) {
                    isfirst = false;
                    tmpOutput = word;
                } else {
                    tmpOutput += "_" + word;
                }
            }
            return tmpOutput;
        }
        return inputString;
    }
}
