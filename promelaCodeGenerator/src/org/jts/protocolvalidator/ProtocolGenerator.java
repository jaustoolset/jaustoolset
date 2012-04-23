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
import org.jts.jsidl.binding.DeclaredTypeSet;
import org.jts.jsidl.binding.ServiceDef;
import org.jts.jsidl.binding.ServiceSet;
import org.jts.jsidl.binding.Start;
import org.jts.jsidl.binding.StateMachine;

/**
 * Main processor for generating Promela code to match the JSIDL specified.
 * @author cmessmer
 */
public class ProtocolGenerator extends JTSFileWriter {

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
    private DefinitionFinder defFinder;

    /**
     * Controls code generation process for a ServiceSet.
     * @param serviceSet - input service set that code will be generated for.
     * @param outputPath - Location of the output promela code.
     * @throws Exception
     */
    public ProtocolGenerator(org.jts.jsidl.binding.ServiceSet serviceSet, File outputPath) throws Exception {
        super(outputPath.getCanonicalPath() + "/main.pml", serviceSet.getId(), serviceSet.getVersion(), false);
        defFinder = new DefinitionFinder(serviceSet);

        ServiceDef def = defFinder.getMainServiceDef();
        this.setID(def.getId());
        this.setVersion(def.getVersion());
        JSIDLSchemaPath = "resources/schema/JSIDL_Plus/jsidl_plus.xsd";
        this.outputPath = outputPath.getCanonicalPath();

        initializeData();

        processDependencies(def);
        processServiceDef(def);

        writeFiles();
    }

    /**
     * Checks for input data errors and then begins the generation process.
     * @param mainJSIDLFilePath - path to the file that needs to be processed.
     * @param JSIDLSearchPath - path for other JSIDL documents that this ServiceDef depends on.
     * @param JSIDLSchemaPath - path to the JSIDL schema
     * @param outputPath - Location where the generated code will be placed.
     * @throws Exception
     */
    public ProtocolGenerator(String mainJSIDLFilePath, String JSIDLSearchPath, String JSIDLSchemaPath, String outputPath) throws Exception {
        super(outputPath + "main.pml", "MAIN", "1.1", false);
        defFinder = new DefinitionFinder(mainJSIDLFilePath, JSIDLSearchPath, JSIDLSchemaPath);

        // save command line data for later
        this.JSIDLSchemaPath = JSIDLSchemaPath;
        this.outputPath = outputPath;

        File inputFile = new File(mainJSIDLFilePath);
        File searchPath = new File(JSIDLSearchPath);
        File schemaPath = new File(JSIDLSchemaPath);
        if (!inputFile.exists()) {
            Logger.getLogger(JTSFileWriter.class.getName()).log(Level.SEVERE, "JSIDL protocol file does not exist.  Exiting...");
            throw new CodeGeneratorException("ERROR: JSIDL input file does not exist. " + inputFile);
        }
        if (!searchPath.exists()) {
            Logger.getLogger(JTSFileWriter.class.getName()).log(Level.SEVERE, "XML search path does not exist.  Exiting...");
            throw new CodeGeneratorException("ERROR: XML search path does not exist. " + searchPath);
        }
        if (!schemaPath.exists()) {
            Logger.getLogger(JTSFileWriter.class.getName()).log(Level.SEVERE, "Schema file does not exist.  Exiting...");
            throw new CodeGeneratorException("ERROR: JSIDL schema does not exist. " + schemaPath);
        }

        // find all of our files
        defFinder = new DefinitionFinder(mainJSIDLFilePath, JSIDLSearchPath, JSIDLSchemaPath);


        // begin processing the input Service Definition file
        ServiceDef serviceDef = defFinder.getMainServiceDef();
        ID = serviceDef.getId();
        version = serviceDef.getVersion();
        initializeData();

        //setReferenceMap(reader);
        processDependencies(serviceDef);
        processServiceDef(serviceDef);

        writeFiles();
    }

    /**
     * Writes the generated Promela code to the files.
     * @throws IOException
     */
    private void writeFiles() throws IOException {
        // add includes to the top of the main file
        // mainOutput.addAll(mainIncludes);
        mainOutput.add("#include \"userEditableConfig.pml\"");

        if(includeList.isEmpty() || includeList.size() == 0){
            mainOutput.add("#include \"" + Util.formatRefName(ID) + "_static.pml\"");
        } else {
            mainOutput.addAll(includeList.getIncludeList(true));
        }

        // some includes need to come after the message definitions
        mainOutput.add("\n// Create channels for all the messages and events.");
        mainOutput.add("#include \"channels.pml\"");
        mainOutput.add("\n\n// These includes should come after message and event channels are declared.");
        mainOutput.add("#include \"userEditableEvents.pml\"");
        mainOutput.add("#include \"userEditableGuardsAndActions.pml\"");
        mainOutput.add("#include \"userEditableClients.pml\"\n\n");

        // add the main implementation for the state machines
        List<String> tmplist = getFSMCode();
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
     * Initialize data that is common to all constructors.
     * @throws Exception
     */
    private void initializeData() throws Exception {
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


        channelOutput = new JTSFileWriter(outputPath + "/channels.pml", ID, version, false);
        eventsOutput = new JTSFileWriter(outputPath + "/userEditableEvents.pml", ID, version, true);
        guardsAndActions = new JTSFileWriter(outputPath + "/userEditableGuardsAndActions.pml", ID, version, true);
        clientImpl = new JTSFileWriter(outputPath + "/userEditableClients.pml", ID, version, true);

        writeMainFileHeader();
        writeConfigFile();

    }

    /**
     * Not being used by test sets
     */
    private void processDeclaredConstSet() {
        // TODO: need to process
    }

    /**
     * Processes the dependencies for a service definition.  This determines what definitions are dependent on others.
     * The main purpose is to generate a single include file list that is ordered properly based on dependencies.
     * @param serviceDef - the root service definition for which dependencies will be checked.
     * @throws Exception
     */
    private void processDependencies(org.jts.jsidl.binding.ServiceDef serviceDef) throws Exception {
        String id = "";
        id = serviceDef.getId();
        org.jts.jsidl.binding.DeclaredTypeSet typeSet =
                serviceDef.getDeclaredTypeSet();
        if (typeSet != null) {
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
            DeclaredTypeSetGenerator typeSetGen = new DeclaredTypeSetGenerator(typeSet, defFinder,
                    JSIDLSchemaPath, outputPath, id, refmap, enumList, includename);
            processTypeSetDependencies(typeSetGen.getDependencyMap());
        }
        if (serviceDef.getReferences() != null && serviceDef.getReferences().getInheritsFrom() != null) {
            String tmpServiceID = serviceDef.getReferences().getInheritsFrom().getId();
            String tmpname = Util.getFileNameFromID(tmpServiceID, true);
            String includename = Util.getFileNameFromID(serviceDef.getId(), true);
            includeList.addDependency(tmpname, includename);
            processDependencies(defFinder.getServiceDefFromID(tmpServiceID));
        }

    }

    /**
     * Processes a ServiceDef to extract data and protocol information.
     * @param serviceDef - the ServiceDef that will be processed
     * @throws Exception
     */
    private void processServiceDef(org.jts.jsidl.binding.ServiceDef serviceDef) throws Exception {
        String id = "";

        id = serviceDef.getId();
        org.jts.jsidl.binding.DeclaredTypeSet typeSet =
                serviceDef.getDeclaredTypeSet();

        if (typeSet != null) {
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


            DeclaredTypeSetGenerator typeSetGen = new DeclaredTypeSetGenerator(typeSet, defFinder,
                    JSIDLSchemaPath, outputPath, id, refmap, enumList, includename);
            typeSetGen.processDeclaredTypeSet(typeSet, includename);
        }


        String inherits_from = "";
        if (serviceDef.getReferences() != null && serviceDef.getReferences().getInheritsFrom() != null) {
            inherits_from = serviceDef.getReferences().getInheritsFrom().getId();
        }
        if (!inherits_from.isEmpty() && !defFinder.isProcessedID(inherits_from)) {
            // locate File for the inherited definition
            ServiceDef tmpSD = defFinder.getServiceDefFromID(inherits_from);
            // show that this id has been processed
            defFinder.setProcessedID(inherits_from);
            // process the file
            processServiceDef(tmpSD);
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
                        stateMachines.put(Util.getNameFromRef(fsmname), machine);
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
        output.add("*\t\t\tactive proctype fireEvents(){");
        output.add("*\t\t\t\tTimeout ! _pid;");
        output.add("*\t\t\t} ");
        output.add("*\t\tMore complex processing is possible for advanced users of Promela.");
        output.add("*\n\tVERY IMPORTANT: For every process created here, the CLIENT_CHANNELS configuration value must be increased by 1. ");
        // we aren't using the event type definitons, so make sure the user knows.
        output.add("*\tNOTE: The following type definitions are for reference purposes only and are not");
        output.add("*\tbeing used in the implementation of the model.");
        output.add("*/");
        //create channels for event messages to be sent
        org.jts.jsidl.binding.InternalEventsSet eventSet = serviceDef.getInternalEventsSet();
        InternalEventSetGenerator eventSetGen = new InternalEventSetGenerator(eventSet,
                serviceDef.getId(), refmap, enumList);
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
        String newFilePath = outputPath + "/" + Util.getFileNameFromID(serviceDef.getId(), true);

        if (msgSet != null) {
            List<Object> msgList = new ArrayList<Object>();
            if (msgSet.getInputSet() != null) {
                msgList.addAll(((List<Object>) msgSet.getInputSet().getMessageDefOrDeclaredMessageDef()));
            }
            if (msgSet.getOutputSet() != null) {
                msgList.addAll(((List<Object>) msgSet.getOutputSet().getMessageDefOrDeclaredMessageDef()));
            }

            DefinitionGenerator defGen = new DefinitionGenerator(msgList,
                    newFilePath, includelist, refmap, serviceDef.getId(), serviceDef.getVersion(), enumList);
        }
        MessageSetGenerator msgSetGen = new MessageSetGenerator(msgSet,
                serviceDef.getId(), refmap);

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

    /**
     * Retrieves a list of unique condition elements that make up all the guards.
     * These are function calls that will be turned into #define statements in Promela.
     * @param guards - a list of all guards in the service set.
     * @return - generated Promela source code for guard function definitions.
     */
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

    /**
     * Generates promela code for action functions.  Since functions
     * can be reused, uniqueness is maintained.
     * @param actions - all actions found in the service set.
     * @return - Promela code for action function definitions.
     */
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

    /**
     * Generates Promela code for a simple client for the service definition.
     * @param serviceDef - the service definition that the client will connect to.
     * @param refmap - a mapping of references to IDs to make finding other definitions easier.
     * @return - Promela implementation of the client application.
     */
    private List<String> getClientImplementation(ServiceDef serviceDef, Map<String, String> refmap) {
        // create the channels for defined message sets
        org.jts.jsidl.binding.MessageSet msgSet = serviceDef.getMessageSet();
        MessageSetGenerator msgSetGen = new MessageSetGenerator(msgSet,
                serviceDef.getId(), refmap);

        return msgSetGen.getClientMessageImplementation();

    }

    /**
     * Generates Promela code for the data definitions used inside the client implementation
     * @param serviceDef - input service definition
     * @param refmap - a mapping of references to ids
     * @return - Promela code
     */
    private List<String> getClientDataDefs(ServiceDef serviceDef, Map<String, String> refmap) {
        // create the channels for defined message sets
        org.jts.jsidl.binding.MessageSet msgSet = serviceDef.getMessageSet();
        MessageSetGenerator msgSetGen = new MessageSetGenerator(msgSet,
                serviceDef.getId(), refmap);

        return msgSetGen.getClientDataDefinitions();

    }

    /**
     * Main function for generating client Promela code
     * @return - code lines for client implementation.
     */
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
     * Writes configuration items to a config file.  In our case, it is a simple config that is always the same.
     */
    private void writeConfigFile() throws Exception {
        JTSFileWriter config = new JTSFileWriter(outputPath + "/userEditableConfig.pml", ID, version, true);
        List<String> output = new ArrayList<String>();

        output.add("#define CLIENTS 1");
        output.add("#define QUEUE_SIZE 10");
        output.add("#define CLIENT_CHANNELS 1");
        config.writeDataToFile(output);
    }
}
