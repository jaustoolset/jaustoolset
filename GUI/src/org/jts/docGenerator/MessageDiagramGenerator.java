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

package org.jts.docGenerator;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import org.jts.jsidl.binding.EventDef;
import org.jts.jsidl.binding.MessageDef;
import org.jts.jsidl.binding.ServiceDef;
import org.jts.docGenerator.diagramModel.MessageDefRepresentation;

/**
 * Class takes list of JAXB service definition representations and produces image files, containing diagrams showing
 * structure of message encodings (MessageDefs) used by those service definitions.  Also creates similar diagrams for
 * internal events (EventDefs).
 *
 * Note the filenames used for the image files are based solely on message names, allowing other parts of the
 * documentation generation process to find the files based on message name alone (plus knowing the directory where
 * the files are located!).
 *
 * Creating the diagrams is a three-step process:
 *  1. Create a "hierarchical representation" of each message and event definition using the classes in the diagramModel
 *     package.
 *  2. For each message/event def, compute the positioning of items in its hierarchical representation for future
 *     output onto an image.
 *  3. For each message/event def, traverse through the hierarchical representation and render each item onto a
 *     Graphics2D canvas.
 *
 * Please see org.jts.docGenerator.diagramModel.MessageDefRepresentation for details about the hierchical
 * representation classes.
 *
 * @author idurkan
 */
public class MessageDiagramGenerator {

    HashMap<String, MessageDefRepresentation> messageDefsMap;

    public MessageDiagramGenerator() {
        messageDefsMap = new HashMap<String, MessageDefRepresentation>();
    }

    /**
     * For each message def or event def in each ServiceDef in serviceDefs:
     *
     * Creates a hierarchy of representation objects for each JAUS message in the ServiceDef or EventDef.  Hierarchy 
     * stores key information about all components of the message.
     * 
     * Adds each hierarchical representation to a hashmap mapping message name strings to hier. reps, stored in
     * messageDefsMap.
     *
     * @param serviceDefs
     */
    private void createRepresentations(List<ServiceDef> serviceDefs) {
        String defFilename = "";

        // add mappings from future output filename to message structure representation, for all messages in all
        // service definitions.
        for (ServiceDef svcdef : serviceDefs) {
            // note about the MessageDef and EventDef names below: they will never contain spaces, and must be
            // named like C identifiers, so there's no need to reprocess them to make them suitable for use in file
            // names.
            
            // process input set
            for (Object defOrDeclaredDef : svcdef.getMessageSet().getInputSet().getMessageDefOrDeclaredMessageDef()) {
                // JSIDL allows both message defs and declared message defs; however our generated JSIDL should have
                // no declared message defs - declarations are resolved when importing.
                if (!(defOrDeclaredDef instanceof MessageDef)) {
                    throw new RuntimeException("Found invalid message definition in input set when creating "
                            + "message structure diagram.");
                }

                MessageDef msgdef = (MessageDef) defOrDeclaredDef;
                defFilename = "msg_" + msgdef.getName() + ".png";

                // don't bother creating a new message repr. if we have it already.
                if (!messageDefsMap.containsKey(defFilename)) {
                    MessageDefRepresentation defrep = new MessageDefRepresentation(msgdef);
                    messageDefsMap.put(defFilename, defrep);
                }
            }

            // process output set
            for (Object defOrDeclaredDef : svcdef.getMessageSet().getOutputSet().getMessageDefOrDeclaredMessageDef()) {
                if (!(defOrDeclaredDef instanceof MessageDef)) {
                    throw new RuntimeException("Found invalid message definition in output set when creating "
                            + "message structure diagram.");
                }

                MessageDef msgdef = (MessageDef) defOrDeclaredDef;
                defFilename = "msg_" + msgdef.getName() + ".png";

                if (!messageDefsMap.containsKey(defFilename)) {
                    MessageDefRepresentation defrep = new MessageDefRepresentation(msgdef);
                    messageDefsMap.put(defFilename, defrep);
                }
            }

            // get all event defs in internal events set (basically identical to message defs in content)
            for (Object defOrDeclaredDef : svcdef.getInternalEventsSet().getEventDefOrDeclaredEventDef()) {
                if (!(defOrDeclaredDef instanceof EventDef)) {
                    throw new RuntimeException("Found invalid message definition in internal event set when creating "
                            + "message structure diagram.");
                }

                EventDef eventdef = (EventDef) defOrDeclaredDef;
                defFilename = "event_" + eventdef.getName() + ".png";

                if (!messageDefsMap.containsKey(defFilename)) {
                    MessageDefRepresentation defrep = new MessageDefRepresentation(eventdef);
                    messageDefsMap.put(defFilename, defrep);
                }
            }
        }
    }

    /**
     * For each message hierarchy representation in messageDefsMap, recurses through the hierarchy and computes
     * positioning info for the diagram to be rendered later by renderImages.
     */
    private void computeDiagramPositioning() {
        for (MessageDefRepresentation msgDef : messageDefsMap.values()) {
            msgDef.computePositioning();
        }
    }

    /**
     * For each message hierarchy representation in messageDefsMap, creates its diagram in outputDir.  Diagrams
     * are generated in PNG format, with filename named after the message/event in question.
     * @param outputDir
     */
    private void renderImages(File outputDir) {
        Set<Entry<String, MessageDefRepresentation>> allPairs = messageDefsMap.entrySet();

        for (Entry<String, MessageDefRepresentation> pair : allPairs) {
            File outputFile = new File(outputDir, pair.getKey());
            pair.getValue().renderImage(outputFile);
        }
    }

    /**
     * Carries out the entire process of creating message structure diagrams for all the ServiceDefs in a list.
     * @param outputDir
     *     Directory where the diagrams will be output, in PNG format.
     * @param serviceDefs
     *     Abstract list of ServiceDefs to process.
     */
    public void generateMessageDigrams(File outputDir, List<ServiceDef> serviceDefs) {
        createRepresentations(serviceDefs);

        computeDiagramPositioning();

        renderImages(outputDir);
    }

}
