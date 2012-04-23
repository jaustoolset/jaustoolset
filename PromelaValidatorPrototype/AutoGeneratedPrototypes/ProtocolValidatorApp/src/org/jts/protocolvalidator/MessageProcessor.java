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
 * Used to generate promela code for JSIDL messages
 * @author cmessmer
 */
public class MessageProcessor extends DataTypeProcessor {

    /**
     * Creates the object so it is ready to be used.
     * @param fileFinder - the FileFinder used to find files by id
     * @param JSIDLSchemaPath - path for the JSIDL schema
     * @param id - id of the parent
     * @param refmap - mapping of references to file paths
     * @param enumlist - list of enums that have been generated so far
     */
    public MessageProcessor(FileFinder fileFinder,
            String JSIDLSchemaPath, String id, Map<String, String> refmap, Map<String, String> enumlist) {
        super(fileFinder, JSIDLSchemaPath, id, refmap, enumlist);

    }

    /**
     * Processes a header and writes the result to the output file
     * @param header the input binding
     * @return - generated code
     */
    public List<String> processHeaderDef(Header header, String parentID) throws Exception {
        List<String> output = new ArrayList<String>();

        String name = Util.getTypeNameFromDeclTypeRef(refMap, header.getName(), parentID);

        // make sure all the definitions this depends on already exist
        Record rec = header.getRecord();
        if (rec != null) {
            output.addAll(processRecordDef(rec, parentID));
        }
        Sequence seq = header.getSequence();
        if (seq != null) {
            output.addAll(processSequenceDef(seq, parentID));
        }
        Variant var = header.getVariant();
        if (var != null) {
            output.addAll(processVariantDef(var, parentID));
        }


        // add the typedef content
        List<String> content = new ArrayList<String>();
        if (rec != null) {
            content.addAll(processRecord(rec, parentID));
        }
        if (seq != null) {
            content.addAll(processSequence(seq, parentID));
        }
        if (var != null) {
            content.addAll(processVariant(var, parentID));
        }
        // write out the contained elements
        DeclaredList declList = header.getDeclaredList();
        if (declList != null) {
            content.addAll(processDeclaredList(declList, parentID));
        }
        DeclaredRecord declRec = header.getDeclaredRecord();
        if (declRec != null) {
            content.addAll(processDeclaredRecord(declRec, parentID));
        }
        DeclaredSequence declSeq = header.getDeclaredSequence();
        if (declSeq != null) {
            content.addAll(processDeclaredSequence(declSeq, parentID));
        }
        DeclaredVariant declVar = header.getDeclaredVariant();
        if (declVar != null) {
            content.addAll(processDeclaredVariant(declVar, parentID));
        }
        // before adding the internal content to the output, lets indent
        Util.indent(1, content);

        if (content.size() > 0) {
            // start the typedef
            if (header.getInterpretation() != null) {
                output.addAll(Util.formatCommentString(header.getInterpretation(), false));
            }
            output.add("typedef " + name + "{");
            // now add the content to the definition
            output.addAll(content);
            // end the definition
            output.add("};\n");
        }
        return output;

    }

    /**
     * Creates a definition for the body of a message
     * @param body - the body to define
     * @return - list of code lines
     */
    public List<String> processBody(Body body, String parentID) {
        List<String> output = new ArrayList<String>();
        output.addAll(Util.formatCommentString(body.getInterpretation(), false));
        String name = body.getName();

        // handle records
        Record rec = body.getRecord();
        if (rec != null) {
            output.addAll(processRecord(rec, parentID));
        }
        DeclaredRecord declrec = body.getDeclaredRecord();
        if (declrec != null) {
            output.addAll(processDeclaredRecord(declrec, parentID));
        }

        //TODO: lists of structures are not supported by Promela
        // handle lists
        org.jts.jsidl.binding.List jtsList = body.getList();
        if (jtsList != null) {
            output.add("// skipped processing List, since its not well supported in Promela.");
            //output.addAll(processList(jtsList, parentID));
        }
        DeclaredList decllist = body.getDeclaredList();
        if (decllist != null) {
            output.add("// skipped processing DeclaredList, since its not well supported in Promela.");
            //output.addAll(processDeclaredList(decllist, parentID));
        }
        Util.indent(1, output);

        return output;
    }
    /**
     * Creates a definition for the header of a message
     * @param body - the body to define
     * @return - list of code lines
     */
    public List<String> processHeader(Header header, String parentID) {
        List<String> output = new ArrayList<String>();
        output.addAll(Util.formatCommentString(header.getInterpretation(), false));
        String name = header.getName();

        // handle records
        Record rec = header.getRecord();
        if (rec != null) {
            output.addAll(processRecord(rec, parentID));
        }
        DeclaredRecord declrec = header.getDeclaredRecord();
        if (declrec != null) {
            output.addAll(processDeclaredRecord(declrec, parentID));
        }

        //TODO: lists of structures are not supported by Promela
        // handle lists
        org.jts.jsidl.binding.List jtsList = header.getList();
        if (jtsList != null) {
            output.add("// skipped processing List, since its not supported in Promela.");
            //output.addAll(processList(jtsList, parentID));
        }
        DeclaredList decllist = header.getDeclaredList();
        if (decllist != null) {
            output.add("// skipped processing DeclaredList, since its not supported in Promela.");
            //output.addAll(processDeclaredList(decllist, parentID));
        }
        Util.indent(1, output);

        return output;
    }

    /**
     * Creates a definition for the body of a message
     * @param body - the body to define
     * @return - list of code lines
     */
    public List<String> processBodyDef(Body body, String parentID) throws Exception {
        List<String> output = new ArrayList<String>();
        output.addAll(Util.formatCommentString(body.getInterpretation(), false));
        Record rec = body.getRecord();
        org.jts.jsidl.binding.List jtsList = body.getList();
        if (rec != null) {
            output.addAll(processRecordDef(rec, parentID));
        } else if (jtsList != null) {
            output.addAll(processListDef(jtsList, parentID));
        }

        return output;
    }

    /**
     * Not implemented at this time
     * @param footer
     * @param parentID
     * @return
     */
    public List<String> processFooter(Footer footer, String parentID) {
        List<String> output = new ArrayList<String>();

        return output;
    }

    /**
     * Creates the code for a declared message header
     * @param header - externally defined header data
     * @return - list of code lines
     */
    public List<String> processDeclaredHeader(DeclaredHeader header, String parentID) {
        List<String> output = new ArrayList<String>();
        String type = Util.getTypeNameFromDeclTypeRef(refMap, header.getDeclaredTypeRef(), parentID);
        String name = header.getName();

        output.addAll(Util.formatCommentString(header.getInterpretation(), false));
        output.add(type + " " + name + ";");
        return output;
    }

    /**
     * Creates the code for a declared message body
     * @param body - externally defined body definition
     * @return - list of code lines
     */
    public List<String> processDeclaredBody(DeclaredBody body, String parentID) {
        List<String> output = new ArrayList<String>();
        String type = Util.getTypeNameFromDeclTypeRef(refMap, body.getDeclaredTypeRef(), parentID);
        String name = body.getName();

        output.addAll(Util.formatCommentString(body.getInterpretation(), false));
        output.add(type + " " + name + ";");
        return output;
    }

    /**
     * Creates the code for a declared message footer
     * @param footer - externally defined footer definition
     * @return - list of code lines
     */
    public List<String> processDeclaredFooter(DeclaredFooter footer, String parentID) {
        List<String> output = new ArrayList<String>();
        String type = Util.getTypeNameFromDeclTypeRef(refMap, footer.getDeclaredTypeRef(), parentID);
        String name = footer.getName();

        output.addAll(Util.formatCommentString(footer.getInterpretation(), false));
        output.add(type + " " + name + ";");
        return output;
    }
}
