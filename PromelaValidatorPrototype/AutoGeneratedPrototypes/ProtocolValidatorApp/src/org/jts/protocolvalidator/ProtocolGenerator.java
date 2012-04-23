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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jts.jsidl.binding.ServiceDef;
import org.jts.jsidl.binding.Start;
import org.jts.jsidl.binding.StateMachine;

/**
 * Main processor for generating Promela code to match the JSIDL specified.
 * @author cmessmer
 */
public class ProtocolGenerator extends JTSFileWriter {

    private FileFinder fileFinder;
    private Map<String, String> refmap;
    private List<String> mainOutput;
    private Map<String, String> enumList;
    private Map<String, FiniteStateMachine> stateMachines;
    // mapping of message name to the name of the data structure
    // contained within the message.
    private Map<String, String> messageDataMap;
    private String JSIDLSchemaPath;
    private String outputPath;
    private JTSFileWriter channelOutput;
    private JTSFileWriter eventsOutput;
    private JTSFileWriter guardsAndActions;
    private JTSFileWriter clientImpl;
    private DependencyList includeList;
    private List<String> messageDefs;
    private List<String> clientCode;
    private List<String> clientDefs;
    private List<String> eventsCode;

    /**
     * Checks for input data errors and then begins the generation process.
     * @param args - Command-line arguments(JSIDL file to process, path to search
     *                                      for dependencies, schema path, output folder)
     */
    public ProtocolGenerator(String mainJSIDLFilePath, String JSIDLSearchPath, String JSIDLSchemaPath, String outputPath) throws Exception {
        super(outputPath + "main.pml", "MAIN", "1.1");
        // save command line data for later
        this.JSIDLSchemaPath = JSIDLSchemaPath;
        this.outputPath = outputPath;

        File inputFile = new File(mainJSIDLFilePath);
        File searchPath = new File(JSIDLSearchPath);
        File schemaPath = new File(JSIDLSchemaPath);
        if (!inputFile.exists()) {
            Logger.getLogger(JTSFileWriter.class.getName()).log(Level.SEVERE, "JSIDL protocol file does not exist.  Exiting...");
            throw new Exception("ERROR: JSIDL input file does not exist. " + inputFile);
        }
        if (!searchPath.exists()) {
            Logger.getLogger(JTSFileWriter.class.getName()).log(Level.SEVERE, "XML search path does not exist.  Exiting...");
            throw new Exception("ERROR: XML search path does not exist. " + searchPath);
        }
        if (!schemaPath.exists()) {
            Logger.getLogger(JTSFileWriter.class.getName()).log(Level.SEVERE, "Schema file does not exist.  Exiting...");
            throw new Exception("ERROR: JSIDL schema does not exist. " + schemaPath);
        }
        refmap = new HashMap<String, String>();
        messageDataMap = new HashMap<String, String>();
        mainOutput = new ArrayList<String>();
        messageDefs = new ArrayList<String>();
        clientCode = new ArrayList<String>();
        clientDefs = new ArrayList<String>();
        eventsCode = new ArrayList<String>();
        includeList = new DependencyList();
        enumList = new HashMap<String, String>();
        stateMachines = new HashMap<String, FiniteStateMachine>();
        // find all of our files
        fileFinder = new FileFinder(JSIDLSearchPath);


        // begin processing the input Service Definition file
        JSIDLReader reader = new JSIDLReader(inputFile.getPath(), JSIDLSchemaPath);
        ServiceDef serviceDef;
        if (reader.getRootElement() instanceof org.jts.jsidl.binding.ServiceDef) {
            serviceDef = (org.jts.jsidl.binding.ServiceDef) reader.getRootElement();
            ID = serviceDef.getId();
            version = serviceDef.getVersion();
        }


        channelOutput = new JTSFileWriter(outputPath + "channels.pml", ID, version);
        eventsOutput = new JTSFileWriter(outputPath + "userEditableEvents.pml", ID, version);
        guardsAndActions = new JTSFileWriter(outputPath + "userEditableGuardsAndActions.pml", ID, version);
        clientImpl = new JTSFileWriter(outputPath + "userEditableClients.pml", ID, version);

        // create the new main output file

        //setReferenceMap(reader);
        processDependencies(reader);
        processServiceDef(reader);
        // add includes to the top of the main file
        // mainOutput.addAll(mainIncludes);
        writeMainFileHeader();
        writeConfigFile();
        mainOutput.add("#include \"userEditableConfig.pml\"");

        List<String> tmplist = getFSMCode();
        mainOutput.addAll(includeList.getIncludeList(true));
        mainOutput.add("#include \""+  Util.getFileNameFromID(ID, true) + "\"");
        // some includes need to come after the message definitions
        mainOutput.add("\n// Create channels for all the messages and events.");
        mainOutput.add("#include \"channels.pml\"");
        mainOutput.add("\n\n// These includes should come after message and event channels are declared.");
        mainOutput.add("#include \"userEditableEvents.pml\"");
        mainOutput.add("#include \"userEditableGuardsAndActions.pml\"");
        mainOutput.add("#include \"userEditableClients.pml\"\n\n");

        // add the main implementation for the state machines
        mainOutput.addAll(tmplist);
        // write the main output file
        writeDataToFile(mainOutput);
        // write the guards and actions file
        guardsAndActions.writeDataToFile(getGuardsAndActionDefs());
        // write the client implementation file
        clientImpl.writeDataToFile(getClientCode());
        channelOutput.writeDataToFile(messageDefs);

        eventsOutput.writeDataToFile(eventsCode);
    }

    /**
     * Not being used by test sets
     */
    private void processDeclaredConstSet() {
        // TODO: need to process
    }

    private void processDependencies(JSIDLReader reader) throws Exception {
        org.jts.jsidl.binding.ServiceDef serviceDef = null;
        String id = "";

        // locate inherits_from
        Object root = reader.getRootElement();
        if (root == null) {
            // the file doesn't contain a valid root element, so no need to process it
            Logger.getLogger(JTSFileWriter.class.getName()).log(Level.SEVERE, "Empty root element found. Processing terminated.");
            throw new Exception("Empty root element found. Processing terminated.");
        }
        if (root instanceof org.jts.jsidl.binding.ServiceDef) {
            serviceDef = (org.jts.jsidl.binding.ServiceDef) root;
        } else {
            // if its not a servicedef, there's nothing to do
            Logger.getLogger(JTSFileWriter.class.getName()).log(Level.SEVERE, "Root element found is not a ServiceDef. Processing terminated.");
            throw new Exception("Root element found is not a ServiceDef. Processing terminated.");
        }
        id = serviceDef.getId();
        org.jts.jsidl.binding.DeclaredTypeSet typeSet =
                serviceDef.getDeclaredTypeSet();
        if(typeSet != null)
        {
            // this reference map is used to resolve declared types to a specific type by mapping
            // a reference to the real ID.
            List<org.jts.jsidl.binding.DeclaredTypeSetRef> typeSetRefs = typeSet.getDeclaredTypeSetRef();
            String ID = typeSet.getId();
            // if the typeset is part of a service definition, then the typeset should use the service def's id
            if (ID == null) {
                ID = id;
            }
            String includename = Util.getFileNameFromID(ID, true);

            for (org.jts.jsidl.binding.DeclaredTypeSetRef tmpref : typeSetRefs) {
                String tmpname = Util.getFileNameFromID(tmpref.getId(), true);
                includeList.addDependency(tmpname, includename);
                // get the real name from the id, so we can include the right file
                refmap.put(tmpref.getName(), tmpref.getId());
            }
            DeclaredTypeSetGenerator typeSetGen = new DeclaredTypeSetGenerator(typeSet, fileFinder,
                    JSIDLSchemaPath, outputPath, id, refmap, enumList, includename);
            processTypeSetDependencies(typeSetGen.getDependencyMap());
        }
        String inherits_from = "";
        if (serviceDef.getReferences() != null && serviceDef.getReferences().getInheritsFrom() != null) {
            inherits_from = serviceDef.getReferences().getInheritsFrom().getId();
        }
        if (!inherits_from.isEmpty()) {
            // locate File for the inherited definition
            File inheritedFile = fileFinder.findFileByID(inherits_from);
            // process the file
            JSIDLReader inheritedFileReader = new JSIDLReader(inheritedFile.getPath(), JSIDLSchemaPath);
            processDependencies(inheritedFileReader);
        }
    }

    /**
     * Performs processing for a ServiceDef, including reading the JSIDL and writing output.
     * @param reader - the JSIDL reader to use when reading an IDL file.
     */
    private void processServiceDef(JSIDLReader reader) throws Exception {
        org.jts.jsidl.binding.ServiceDef serviceDef = null;
        String id = "";

        // locate inherits_from
        Object root = reader.getRootElement();
        if (root == null) {
            // the file doesn't contain a valid root element, so no need to process it
            Logger.getLogger(JTSFileWriter.class.getName()).log(Level.SEVERE, "Empty root element found. Processing terminated.");
            throw new Exception("Empty root element found. Processing terminated.");
        }
        if (root instanceof org.jts.jsidl.binding.ServiceDef) {
            serviceDef = (org.jts.jsidl.binding.ServiceDef) root;
        } else {
            // if its not a servicedef, there's nothing to do
            Logger.getLogger(JTSFileWriter.class.getName()).log(Level.SEVERE, "Root element found is not a ServiceDef. Processing terminated.");
            throw new Exception("Empty root element found. Processing terminated.");
        }
        id = serviceDef.getId();
        org.jts.jsidl.binding.DeclaredTypeSet typeSet =
                serviceDef.getDeclaredTypeSet();

        if(typeSet != null)
        {
            // this reference map is used to resolve declared types to a specific type by mapping
            // a reference to the real ID.
            List<org.jts.jsidl.binding.DeclaredTypeSetRef> typeSetRefs = typeSet.getDeclaredTypeSetRef();
            String ID = typeSet.getId();
            // if the typeset is part of a service definition, then the typeset should use the service def's id
            if (ID == null) {
                ID = id;
            }
            String includename = Util.getFileNameFromID(ID, true);

            // generate reference map
            for (org.jts.jsidl.binding.DeclaredTypeSetRef tmpref : typeSetRefs) {
                // get the real name from the id, so we can include the right file
                refmap.put(tmpref.getName(), tmpref.getId());
            }


            DeclaredTypeSetGenerator typeSetGen = new DeclaredTypeSetGenerator(typeSet, fileFinder,
                    JSIDLSchemaPath, outputPath, id, refmap, enumList, includename);
            typeSetGen.processDeclaredTypeSet(typeSet, includename);
        }
        

        String inherits_from = "";
        if (serviceDef.getReferences() != null && serviceDef.getReferences().getInheritsFrom() != null) {
            inherits_from = serviceDef.getReferences().getInheritsFrom().getId();
        }
        if (!inherits_from.isEmpty() && !fileFinder.isProcessedID(inherits_from)) {
            // locate File for the inherited definition
            File inheritedFile = fileFinder.findFileByID(inherits_from);
            // show that this id has been processed
            fileFinder.setProcessedID(inherits_from);
            // process the file
            JSIDLReader inheritedFileReader = new JSIDLReader(inheritedFile.getPath(), JSIDLSchemaPath);
            processServiceDef(inheritedFileReader);
        }

        messageDefs.addAll(processMessages(serviceDef, refmap));
        clientCode.addAll(getClientImplementation(serviceDef, refmap));
        clientDefs.addAll(getClientDataDefs(serviceDef, refmap));
        eventsCode.addAll(processInternalEvents(serviceDef, refmap));
        processStateMachines(serviceDef);

    }

    /**
     * Reads state machines and incorporates state data into defined FSMs
     * @param serviceDef - the service definition which may contain FSMs.
     */
    private void processStateMachines(ServiceDef serviceDef) {

        List<Start> startlist = serviceDef.getProtocolBehavior().getStart();
        List<StateMachine> fsmlist = serviceDef.getProtocolBehavior().getStateMachine();
        for (StateMachine fsm : fsmlist) {
            String fsmname = fsm.getName();
            for (Start start : startlist) {
                if (fsmname.equals(start.getStateMachineName())) {
                    FiniteStateMachine machine = stateMachines.get(Util.getNameFromRef(fsmname));
                    if (machine == null) {
                        machine = new FiniteStateMachine(fsm, start);
                        stateMachines.put(fsmname, machine);
                    } else {
                        machine.addStates(fsm, start);
                    }
                }

            }
        }
    }

    /**
     * Processes the internal events sets to ensure message channels exist for internal events.
     * @param serviceDef - the input service definition
     * @param refmap - mapping of references to names
     * @return
     */
    private List<String> processInternalEvents(ServiceDef serviceDef, Map<String, String> refmap) {
        List<String> output = new ArrayList<String>();
        // add header instructions for implementing events
        output.add("/**");
        output.add("*\tImplementing Internal Events");
        output.add("*\t\tEvents are implmented as messages.  In order to fire an event, send a ");
        output.add("*\t\tmessage with the event name as the message channel followed by _pid.");
        output.add("*\t\tThe _pid is just the processID of the process that sends the message.");
        output.add("*\t\tThe processID may not be necessary, but is sent as a default parameter");
        output.add("*\t\tbecause Promela requires a payload for messages.");
        output.add("*\t\tExample: You want to fire a Timeout event.");
        output.add("*\t\t\tactive proctype fireEvents{");
        output.add("*\t\t\t\tTimeout ! _pid;");
        output.add("*\t\t\t} ");
        output.add("*\t\tMore complex processing is possible for advanced users of Promela.");
        output.add("*");
        // we aren't using the event type definitons, so make sure the user knows.
        output.add("*\tNOTE: The following type definitions are for reference purposes only and are not");
        output.add("*\tbeing used in the implementation of the model.");
        output.add("*/");
        //create channels for event messages to be sent
        org.jts.jsidl.binding.InternalEventsSet eventSet = serviceDef.getInternalEventsSet();
        InternalEventSetGenerator eventSetGen = new InternalEventSetGenerator(eventSet,
                fileFinder, JSIDLSchemaPath, serviceDef.getId(), refmap, enumList);
        messageDefs.addAll(eventSetGen.getEventMessages());
        output.addAll(eventSetGen.getEventSetOutput());
        return output;
    }

    /**
     * Processes message definitions to ensure that message channels exist for messages.
     * @param serviceDef - input service definition
     * @param refmap - mapping of references to names
     * @return - code lines representing message channel definitions
     */
    private List<String> processMessages(ServiceDef serviceDef, Map<String, String> refmap) throws Exception {
        // create the channels for defined message sets
        org.jts.jsidl.binding.MessageSet msgSet = serviceDef.getMessageSet();
        List<String> includelist = new ArrayList<String>();
        String newFilePath = outputPath + "/" + Util.getFileNameFromID(ID, true);

        if(msgSet != null)
        {
            List<Object> msgList = new ArrayList<Object>();
            if(msgSet.getInputSet()!= null)
            {
                msgList.addAll(((List<Object>) msgSet.getInputSet().getMessageDefOrDeclaredMessageDef()));
            }
            if(msgSet.getOutputSet() != null)
            {
                msgList.addAll(((List<Object>) msgSet.getOutputSet().getMessageDefOrDeclaredMessageDef()));
            }

            DefinitionGenerator defGen = new DefinitionGenerator(msgList,
                        fileFinder, JSIDLSchemaPath, newFilePath, includelist, refmap, ID, version, enumList);
        }
        MessageSetGenerator msgSetGen = new MessageSetGenerator(msgSet,
                fileFinder, JSIDLSchemaPath, serviceDef.getId(), refmap);

        messageDataMap.putAll(msgSetGen.getMessageDataMap());
        return msgSetGen.getMessageSetOutput();
    }

    /**
     * Header for the main file that will be written at the top of the file.
     */
    private void writeMainFileHeader() throws IOException {
        out.write("/** \n");
        out.write(" *  This is the main model file and will contain instances of all message channels and state machines.\n");
        out.write(" */ \n\n");
        out.flush();
    }

    /**
     * FSM code is generated and placed in a list of strings for easy output.
     * @return Lines of FSM code to be written to the end of the main output file.
     */
    private List<String> getFSMCode() {
        List<String> output = new ArrayList<String>();

        // all FSMs need to have code generated
        for (FiniteStateMachine fsm : stateMachines.values()) {

            output.addAll(fsm.getCode(messageDataMap));
        }
        return output;
    }

    /**
     * Retrieves a list of unique action and guard definitions, since the Promela
     * implementation for these are global.
     * @return code lines for the definitions.
     */
    private List<String> getGuardsAndActionDefs() {
        List<String> output = new ArrayList<String>();

        // lists of unique guards and actions
        List<Guard> allguards = new ArrayList<Guard>();
        List<Action> allactions = new ArrayList<Action>();

        // collect all guards and actions
        for (FiniteStateMachine fsm : stateMachines.values()) {
            List<Guard> guards = fsm.getGuards();
            allguards.addAll(guards);
            List<Action> actions = fsm.getActions();
            allactions.addAll(actions);
        }

        // add section headings for the definitions
        output.add("// Guard definitions");
        output.addAll(getUniqueConditionElements(allguards));

        output.add("");
        output.add("// Action definitions");
        output.addAll(getUniqueActions(allactions));

        return output;
    }

    private List<String> getUniqueConditionElements(List<Guard> guards) {
        List<String> tmplist = new ArrayList<String>();
        Set<String> result = new HashSet<String>();

        for (Guard guard : guards) {

            List<String> gs = guard.getConditionList();
            for (String tmpelem : gs) {
                boolean success = result.add(tmpelem);
                if (success) {
                    tmplist.addAll(guard.getComments());
                    tmplist.add(tmpelem);
                }
            }
        }

        return tmplist;
    }

    private List<String> getUniqueActions(List<Action> actions) {
        List<String> tmplist = new ArrayList<String>();
        Set<String> result = new HashSet<String>();

        for (Action action : actions) {
            String actionstr = action.getActionString(true);
            boolean success = result.add(actionstr);
            if (success) {
                tmplist.addAll(action.getActionDefinition());
            }
        }

        return tmplist;
    }

    /**
     * Add dependencies to our list from a dependency map
     * @param dependencyMap - mapping of files to dependent files
     */
    private void processTypeSetDependencies(List<Dependency> dependencyMap) {

        for (Dependency dep : dependencyMap) {
            String file2 = dep.dependent;
            String file1 = dep.file;
            includeList.addDependency(file1, file2);
        }
    }

    private List<String> getClientImplementation(ServiceDef serviceDef, Map<String, String> refmap) {
        // create the channels for defined message sets
        org.jts.jsidl.binding.MessageSet msgSet = serviceDef.getMessageSet();
        MessageSetGenerator msgSetGen = new MessageSetGenerator(msgSet,
                fileFinder, JSIDLSchemaPath, serviceDef.getId(), refmap);

        return msgSetGen.getClientMessageImplementation();

    }

    private List<String> getClientDataDefs(ServiceDef serviceDef, Map<String, String> refmap) {
        // create the channels for defined message sets
        org.jts.jsidl.binding.MessageSet msgSet = serviceDef.getMessageSet();
        MessageSetGenerator msgSetGen = new MessageSetGenerator(msgSet,
                fileFinder, JSIDLSchemaPath, serviceDef.getId(), refmap);

        return msgSetGen.getClientDataDefinitions();

    }

    private List<String> getClientCode() {
        List<String> output = new ArrayList<String>();

        output.add("// Client implementation ");
        output.add("active [CLIENTS] proctype clientProcess(){");
        output.add("\t// declare data for the messages");
        Util.indent(1, clientDefs);
        output.addAll(clientDefs);
        output.add("");
        output.add("\t// put some values into the declared data here\n");
        output.add("CLIENT_START_STATE:");
        output.add("\tdo");
        output.add("\t\t:: printf(\"send messages\");");
        Util.indent(2, clientCode);
        output.addAll(clientCode);
        output.add("\t od;");
        output.add("}");
        output.add("// End of client implementation");
        output.add("\n\n");
        return output;
    }

    /**
     * Writes configuration items to a config file.  In our case,
     */
    private void writeConfigFile() throws Exception {
        JTSFileWriter config = new JTSFileWriter(outputPath + "userEditableConfig.pml", ID, version);
        List<String> output = new ArrayList<String>();

        output.add("#define CLIENTS 1");
        output.add("#define QUEUE_SIZE 10");
        output.add("#define CLIENT_CHANNELS 1");
        config.writeDataToFile(output);
    }


}
