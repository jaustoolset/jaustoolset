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

/*
 * @(#)ProtocolGenerator.java		0.1 2008/09/08
 *
 * Copyright
 */
package org.jts.codegenerator;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.Vector;

import org.jts.jsidl.binding.*;
import org.jts.codegenerator.support.*;

public class ProtocolBehaviorGenerator {

    public static final String C_PLUS_PLUS = "C_PLUS_PLUS";
    public static final String JAVA = "JAVA";
    public static final String C_SHARP = "C_SHARP";
    public final ArrayList<String> stateMachineNameList = new ArrayList<String>();
    public final StringBuffer entryActionCalls = new StringBuffer();
    public final StringBuffer transitionCalls = new StringBuffer();
	public final StringBuffer defaultCalls = new StringBuffer();
    private String transportVersion = "";
    
    // used for junit testing
    public static StringBuffer code = new StringBuffer();
    public static StringBuffer entryCode = new StringBuffer();
    public static StringBuffer actionMethodCode = new StringBuffer();
    private boolean debug = false;
    private CodeLines.CodeType m_codeType;
    private org.jts.jsidl.binding.ServiceDef serviceDef;
    
    private boolean serviceDefHasInternalEvents;
    private boolean serviceDefHasMessages;

    /**
     * Constructor, generates the header for the SMC file
     * @param codeType, The type of code to generate
     * @param pBehavior, The protocol behavior to process.
     * @param namespace, the namespace/package information
     * @param outDir, the folder to generate to
     * @param sd, The service definition
     * @param includes, the includes list for the C# generator.
     */
    public ProtocolBehaviorGenerator(String namespace, CodeLines.CodeType codeType, String outDir, ServiceDef sd, ServiceSet ss, StringBuffer includes) {
        ProtocolBehavior pBehavior = sd.getProtocolBehavior();
        m_codeType = codeType;
        serviceDef = sd;
        
        checkServiceDefContents();

        // allow for multiple state machines in protocol behavior definition
        // BEGIN STATE-MACHINE STUB GENERATION
        for (int i = 0; i < pBehavior.getStateMachine().size(); i++) {
            StringBuffer localBuffer = new StringBuffer();

            org.jts.jsidl.binding.StateMachine sm = pBehavior.getStateMachine().get(i);

            org.jts.jsidl.binding.Start st_start = pBehavior.getStart().get(i);
            List<org.jts.jsidl.binding.State> flattenedStates = new ArrayList();

            // a list of state wrappers for the state machine
            List<org.jts.codegenerator.protocolBehavior.StateWrapper> wrapperList = new ArrayList();

            // testing short class name
            sm.setName( org.jts.codegenerator.support.InheritanceHelper.shortenStateMachineName(sd, sm) );

            // flatten states first
            if (sm.getState().size() > 0) {
                // generates a set of all flattened states in the state machine
                generateFlattenedStateMachine(flattenedStates, sm, sd, ss, st_start, wrapperList);
            }

            if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
                // classString
                localBuffer.append("%class " + sm.getName() + System.getProperty("line.separator"));

                // packageString
                localBuffer.append(flattenString("%package " + namespace + System.getProperty("line.separator")));

                // headerString
                localBuffer.append("%header " + namespace + "/Messages/MessageSet.h" + System.getProperty("line.separator"));
                localBuffer.append("%include \"" + namespace + "/" + sm.getName() + ".h\"" + System.getProperty("line.separator"));
            } else if (codeType == CodeLines.CodeType.JAVA) {
                // classString
                localBuffer.append("%class " + sm.getName() + System.getProperty("line.separator"));

                // packageString
                localBuffer.append("%package src." + namespace + System.getProperty("line.separator"));

                // headerString
                localBuffer.append("%header " + namespace + "/Messages/MessageSet" + System.getProperty("line.separator"));
                localBuffer.append("%include " + namespace + "." + sm.getName() + System.getProperty("line.separator"));
                localBuffer.append("%import framework.internalEvents.*" + System.getProperty("line.separator"));
                
                if( serviceDefHasInternalEvents )
                {
                	localBuffer.append( "%import src." + namespace + ".InternalEvents.*" + System.getProperty("line.separator") );
                }
                
                if( serviceDefHasMessages )
                {
                	localBuffer.append( "%import src." + namespace + ".Messages.*" + System.getProperty("line.separator") );
                }
            } else if (codeType == CodeLines.CodeType.C_SHARP) {
                // classString
                localBuffer.append("%class " + sm.getName() + System.getProperty("line.separator"));

                // namespaceString
                localBuffer.append("%import JTS").append(System.getProperty("line.separator"));

                // packageString
                localBuffer.append(flattenString("%package " + namespace + System.getProperty("line.separator")));
            }


            // startString
            // a simple string replacement can't be done here because the start state may have children states in which
            // case the start state would need to be changed to whatever leaf state the start state leads to
            localBuffer.append("%start " + sm.getName() + "_SM" + "::" + flattenString(org.jts.codegenerator.protocolBehavior.State.getFlattenedEndStateName(st_start.getStateName(), wrapperList)) + System.getProperty("line.separator"));

            // mapString
            localBuffer.append("%map " + sm.getName() + "_SM" + System.getProperty("line.separator"));

            // key
            localBuffer.append("// State 	 Transition 	 End State 	 Action(s)" + System.getProperty("line.separator"));

            // start definition
            localBuffer.append("%%" + System.getProperty("line.separator"));

            // print out all flattened states for the state machine
            if (!flattenedStates.isEmpty()) {
                Iterator<org.jts.jsidl.binding.State> flattenedStateIterator = flattenedStates.iterator();

                // add all flattened states to the local string buffer
                while (flattenedStateIterator.hasNext()) {
                    org.jts.jsidl.binding.State flattenedState = flattenedStateIterator.next();

                    // add the code generated for the flattened state
                    localBuffer.append(org.jts.codegenerator.protocolBehavior.State.addState(flattenedState, codeType));
                }
            }

            localBuffer.append("%%" + System.getProperty("line.separator"));
            // END STATE MACHINE STUB GENERATION

            // print localBuffer lines to the .sm file
            printLines(localBuffer, outDir, sm.getName());

            // add current state machine name to list of all state machines defined in the behavior
            stateMachineNameList.add(sm.getName());

            // extract the version of the transport used
			ServiceDef top = org.jts.codegenerator.support.InheritanceHelper.getTopParent(sd, ss);
			if (top.getId().equals("urn:jaus:jss:core:Transport")) 
                transportVersion = sd.getVersion();

            // generate the action and guard functions for the current state machine
            if (codeType == CodeLines.CodeType.C_PLUS_PLUS) {
                generateOwnerAndImplFilesCPP(sm, sd, ss, transportVersion, outDir, namespace, flattenedStates);
            } else if (codeType == CodeLines.CodeType.JAVA) {
                generateOwnerAndImplFilesJava(sm, sd, ss, transportVersion, outDir, namespace, flattenedStates);
            } else if (codeType == CodeLines.CodeType.C_SHARP) {
                generateOwnerAndImplFilesCSharp(sm, sd, ss, transportVersion, outDir, namespace, flattenedStates, includes);
            }

            // generate the transition functions for the current state machine.
            // Note that we track the regular transitions and default transitions separately.
			org.jts.codegenerator.protocolBehavior.Transition.generateTransitionVocabularyCheck(sm, sd, transitionCalls, m_codeType);
            org.jts.codegenerator.protocolBehavior.Transition.generateTransitionFunctions(sm, sd, transitionCalls, m_codeType, false);
            org.jts.codegenerator.protocolBehavior.Transition.generateTransitionFunctions(sm, sd, defaultCalls, m_codeType, true);

            // compile state machine with SMC
            buildSMC(outDir, sm.getName(), namespace);

            //TODO: add support for multiple state machines defined in one protocolBehavior

            code = localBuffer;
        }
    }

    /**
     * Checks the service definition to see if it has internal events or messages
     * When it find these, it sets the corresponding class value which is used later
     */
    private void checkServiceDefContents()
    {
    	serviceDefHasInternalEvents = false;
    	serviceDefHasMessages = false;

        if( serviceDef != null )
        {        	
	        // only add event package imports when they exist
	        if( serviceDef.getInternalEventsSet() != null && 
	        		serviceDef.getInternalEventsSet().getEventDefOrDeclaredEventDef() != null &&
	        		serviceDef.getInternalEventsSet().getEventDefOrDeclaredEventDef().size() > 0 )
	        {
	        	serviceDefHasInternalEvents = true;
	        }
        	
        	if( serviceDef.getMessageSet() != null )
        	{
		        // only add message package imports when they exist
		        boolean hasInputSet = serviceDef.getMessageSet().getInputSet() != null &&
		        						serviceDef.getMessageSet().getInputSet().getMessageDefOrDeclaredMessageDef() != null &&
		        						serviceDef.getMessageSet().getInputSet().getMessageDefOrDeclaredMessageDef().size() > 0;
		        boolean hasOutputSet = serviceDef.getMessageSet().getOutputSet() != null &&
		        						serviceDef.getMessageSet().getOutputSet().getMessageDefOrDeclaredMessageDef() != null &&
		        						serviceDef.getMessageSet().getOutputSet().getMessageDefOrDeclaredMessageDef().size() > 0;
		
		        if( hasInputSet || hasOutputSet )
		        {
		        	serviceDefHasMessages = true;
		        }
        	}
        }
    }

    /**
     * builds the SMC files stored in the stateMachineList
     */
    public void run() {
//		for(int i = 0; i < stateMachineList.size(); i++)
//		{
//			buildSMC(outDir + stateMachineList.get(i) + ".sm");
//		}
    }

    /**
     * returns a list of all the state machines in the current protocol behavior
     * @return
     */
    public ArrayList<String> getStateMachineNames() {
        return stateMachineNameList;
    }

    /**
     * @return the string representation of the component's transport version.
     */
    public String getTransportVersion(){
        return transportVersion;
    }

    /**
     * Calls the recursive function that generates flattened states from a state machine
     * @param stateMachine_original
     * @param wrapperList
     * @return
     */
    public final void generateFlattenedStateMachine(List<org.jts.jsidl.binding.State> flattenedStateList, org.jts.jsidl.binding.StateMachine stateMachine, org.jts.jsidl.binding.ServiceDef serviceDef, org.jts.jsidl.binding.ServiceSet serviceSet, org.jts.jsidl.binding.Start start, List<org.jts.codegenerator.protocolBehavior.StateWrapper> wrapperList) {
        // move default states from sibling level to child level
        // this is so they will be easier to handle(1:1 mapping instead of 1:n)
        // each state's default state will now be set inside of the state instead of at the same level
        org.jts.codegenerator.protocolBehavior.State.moveDefaultStates(stateMachine);

        // pre-flatten end state names for all transitions
        org.jts.codegenerator.protocolBehavior.State.fixEndStateNames(stateMachine);

        // pre-flatten initial state names
        org.jts.codegenerator.protocolBehavior.State.fixInitialStateNames(stateMachine);

        // pre-flatten state names
        org.jts.codegenerator.protocolBehavior.State.fixStateNames(stateMachine);

        // pre-process transition names (add 'Transition' to all transition names)
        org.jts.codegenerator.protocolBehavior.State.fixTransitionNames(stateMachine);

		// add internal transitions between all states
		addInternalEvents( stateMachine, serviceDef, serviceSet );

        // pre-process action/send action names (add 'Action'/'SendAction' to all action/send action names)
        org.jts.codegenerator.protocolBehavior.State.fixActionSendActionNames(stateMachine);

        // calls recursive function to generate a list of state wrappers for unflattened leaf states for the state machine
        org.jts.codegenerator.protocolBehavior.State.generateStateWrapperList(stateMachine, wrapperList);

        // we need to make clones of each state in every state wrapper
        // this step is needed to insure duplicate actions are not taken on the same state
        List<org.jts.codegenerator.protocolBehavior.StateWrapper> clonedUnflattenedStateWrapperList = new ArrayList();

        Iterator<org.jts.codegenerator.protocolBehavior.StateWrapper> unflattenedStateWrapperIterator = wrapperList.iterator();

        while (unflattenedStateWrapperIterator.hasNext()) {
            org.jts.codegenerator.protocolBehavior.StateWrapper wrapper = unflattenedStateWrapperIterator.next();

            clonedUnflattenedStateWrapperList.add(org.jts.codegenerator.protocolBehavior.StateWrapper.cloneWrapper(wrapper));
        }

        // add exit actions to transitions of unflattened states
        org.jts.codegenerator.protocolBehavior.Exit.addExitActionsToWrapperList(clonedUnflattenedStateWrapperList);

        // add entry actions to transitions of unflattened states
        org.jts.codegenerator.protocolBehavior.Entry.addEntryActionsToWrapperList(clonedUnflattenedStateWrapperList);

        // set the entry actions to add to the entry stub for the state machine start state
        List<Object> startStateEntryActions = new ArrayList<Object>();
        org.jts.codegenerator.protocolBehavior.Entry.getEntryActionsForStartStateFromWrapper(startStateEntryActions, start, wrapperList, clonedUnflattenedStateWrapperList);
        generatStartStateEntryActionCalls(stateMachine.getName(), startStateEntryActions);

        // flatten all the state wrappers and put them into the list of flattened states
        org.jts.codegenerator.protocolBehavior.State.flattenStateWrappersInList(flattenedStateList, clonedUnflattenedStateWrapperList);

        // change end state names for all transitions based on the start state for each state
        org.jts.codegenerator.protocolBehavior.State.changeEndStateNames(flattenedStateList, clonedUnflattenedStateWrapperList);

        // replace all namespacing '.' with '_' so that c++ will compile
        org.jts.codegenerator.protocolBehavior.State.replaceNameSpacing(flattenedStateList);
    }

    /**
     * Replaces "." with "_"
     * @param str
     * @return
     */
    public static String flattenString(String str) {
        return str.replaceAll("\\.", "_");
    }

    /**
     * Simple test function to write simple sample case for SMC
     * @param fileName
     * @param code
     * @param outDir
     * @param stateMachineName
     */
    private void printLines(StringBuffer code, String outDir, String stateMachineName) {
        try {
            FileWriter fw = new FileWriter(outDir
                    + System.getProperty("file.separator") + stateMachineName + ".sm");

            for (int i = 0; i < code.length(); i++) {
                fw.write(code.charAt(i));
            }

            fw.flush();
            fw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Simple function to delete the left-over SM file
     * @param outDir
     * @param stateMachineName
     */
    private void deleteStateMachineMapFile(String outDir, String stateMachineName) {
        try {
            File sm_file = new File(outDir
                    + System.getProperty("file.separator") + stateMachineName + ".sm");
            sm_file.delete();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Runs the SMC compiler on the given filename
     * @param outDir
     * @param stateMachineName
     * @param namespace
     */
    public final void buildSMC(String outDir, String stateMachineName, String namespace) {
        /**
         * First we must compile the smc file
         */
        // current execution directory /branch/CodeGenerator1.0/
        // smc must be executed from the outdir so that -d and -headerd switches can be used
        // and no copying of files needs to be done
        // smc.jar must be run from the outdir because when -headerd is used, smc attaches the whole outdir to the include line in the cpp file
        // when we execute smc from the outdir and use -headerd include, #include "include/*header*.h" is attached to the source file
        // in order to retify this in the scons file, we place the root directory as an included directory (./)
        Class cl = this.getClass();
        java.net.URL smcURL = cl.getResource("/tools/Smc.jar");
        java.net.URL outDirURL = cl.getResource(outDir);

        String smcPath = null;
        String outDirPath = null;

        if (smcURL != null) {
            // Use File, since it's friendlier to cross-platform environments
            smcPath = (new File(smcURL.getPath())).getPath();
            if (debug) {
                System.out.printf("SMC path is %s\n", smcPath);
            }
        } else {
            // Hanlde Eclipse plugin's handling of relative paths
            if(new File("plugins/org.jts.eclipse.data_1.0/lib/").exists())
            {
                smcPath = (new File("plugins/org.jts.eclipse.data_1.0/lib/smc/Smc.jar")).getAbsolutePath();
            }
            else
            {
                smcPath = (new File("lib/smc/Smc.jar")).getAbsolutePath();
            }
            if (debug) {
                System.out.printf("Didn't find smc.jar.  Assuming path=%s...\n", smcPath);
            }
        }

        if (outDirURL != null) {
            outDirPath = (new File(outDirURL.getPath())).getPath();
            //outDirPath = outDirURL.getPath();
        } else {
            outDirPath = outDir;
        }

        String excstr = "";
        if (m_codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            excstr = "java -jar " + smcPath + " -c++ -g -d src/" + namespace + " -headerd include/" + namespace + " " + stateMachineName + ".sm";
        } else if (m_codeType == CodeLines.CodeType.JAVA) {
            // Generate SM files and copy library to proper directory.
            excstr = "java -jar " + smcPath + " -java -g -d src/" + namespace + " " + stateMachineName + ".sm";
        } else if (m_codeType == CodeLines.CodeType.C_SHARP) {
            excstr = "java -jar " + smcPath + " -csharp -g -d src/" + namespace + " " + stateMachineName + ".sm";
        }

        try {
            File file = new File(outDirPath);
            Process p2 = Runtime.getRuntime().exec(excstr, null, file);

            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p2.getErrorStream()));
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            if (sb.length() != 0) {
                throw new Exception("FILE: " + stateMachineName + ".sm" + "\n SMC ERRORS: " + sb.toString());
            }

            // remove .sm file when done running SMC
            // in the event of an error, an exception will be thrown
            // and the .sm file will be available for debug
            deleteStateMachineMapFile(outDir, stateMachineName);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            System.out.println("Execute : " + excstr);
        }
    }

    /**
     * Add generate action and guard code to state machine template for C++
     * @param smName
     * @param transportVersion
     * @param outDir
     * @param namespace
     * @param flattenedStates
     *
     */
    private void generateOwnerAndImplFilesCPP(StateMachine sm, ServiceDef sd, ServiceSet ss, String transportVersion, String outDir, String namespace, List<org.jts.jsidl.binding.State> flattenedStates) 
	{
		String smName = sm.getName();

        // Make a list of all unique actions and guards throughout all the flattened states of the current state machine
        TreeSet<String> actionOrSendActionList = new TreeSet<String>();
        TreeSet<String> guardList = new TreeSet<String>();

        addActionListTreeSet(flattenedStates, actionOrSendActionList);
        addGuardListTreeSet(flattenedStates, guardList);

        // Parse existing files, if any, for function definitions
        Hashtable<String, StringBuffer> replaceHandleFunction = new Hashtable<String, StringBuffer>();
        Hashtable<String, StringBuffer> replaceActionFunction = new Hashtable<String, StringBuffer>();
        Hashtable<String, StringBuffer> replaceGuardFunction = new Hashtable<String, StringBuffer>();

        StringBuffer replaceUserFunctions = new StringBuffer();
        StringBuffer replaceUserHeaders = new StringBuffer();
        StringBuffer replaceUserConstants = new StringBuffer();
        StringBuffer replaceUserConstructor = new StringBuffer();
        StringBuffer replaceUserDestructor = new StringBuffer();
        StringBuffer replaceUserNamespaces = new StringBuffer();

		// Before we generate the service files, we need to determine any inheritance requirements.
		// That is, this FSM may inherit from parent FSMs, for which a reference must be passed
		// to the service constructor.
		Vector<Reference> parentList = new Vector<Reference>();
		org.jts.codegenerator.support.InheritanceHelper.getParentFSMList( m_codeType, sm.getName(), ss, sd, parentList);

        // see if file exists to parse previous function definitions
        File fl = new File(outDir + "/src/" + namespace + "/" + smName + ".cpp");

        if (fl.exists()) {
            try {
                FileInputStream fstream = new FileInputStream(outDir + "/src/" + namespace + "/" + smName + ".cpp");
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String strLine;

                while ((strLine = br.readLine()) != null) {
                    // headers (if user added math.h etc)
                    if (strLine.startsWith("#")) {
                        parseHeader(br, strLine, replaceUserHeaders);
                    } // user defined contants
                    else if (strLine.startsWith("const")) {
                        parseConstant(br, strLine, replaceUserConstants);
                    } // namespaces
                    else if (strLine.startsWith("using namespace JTS;")) {
                        // do nothing.  This is part of the template.
                    } // constructor
                    else if (strLine.startsWith(smName + "::" + smName)) {
                        parseConstructor(br, strLine, replaceUserConstructor);
                    } // destructor
                    else if (strLine.startsWith("~" + smName + "::" + smName)) {
                        //parseDestructor(br, strLine, replaceUserDestructor);
                    } // statically defined functions
                    else if (strLine.startsWith("static ")) {
                        if (strLine.endsWith(";")) {
                            parseStaticVariable(br, strLine, replaceUserFunctions);
                        } else {
                            parseStaticFunction(br, strLine, replaceUserFunctions);
                        }
                    } else if (strLine.startsWith("extern ")) {
                        parseStaticVariable(br, strLine, replaceUserFunctions);
                    } // user defined classes
                    else if (strLine.startsWith("class")) {
                        parseClass(br, strLine, replaceUserFunctions);
                    } // action definitions
                    else if (strLine.startsWith("void")  && !strLine.endsWith("::setupNotifications()")) {
                        parseActionFunction(br, strLine, replaceActionFunction);
                    } // guard definitions
                    else if (strLine.startsWith("bool")) {
                        parseGuardFunction(br, strLine, replaceGuardFunction);
                    }
                }

                in.close();
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        // Set-up Send/Receive aliases
        StringBuffer smAliases = new StringBuffer();
        if (transportVersion.equals("1.1")) {
            smAliases.append("typedef JTS::Receive_1_1 Receive;").append(System.getProperty("line.separator"));
            smAliases.append("typedef JTS::Send_1_1 Send;").append(System.getProperty("line.separator"));
        } else {
            smAliases.append("typedef JTS::Receive_1_0 Receive;").append(System.getProperty("line.separator"));
            smAliases.append("typedef JTS::Send_1_0 Send;").append(System.getProperty("line.separator"));
        }

        //---------------------------
        // Now we generate the files for the action and guard definitions
        Hashtable<String, String> replaceTable = new Hashtable<String, String>();

        // put items in template
        replaceTable.put("%copyright%", "");
        replaceTable.put("%service_namespace%", namespace);
        replaceTable.put("%statemachine_name%", smName);
        replaceTable.put("%transport_class_aliases%", smAliases.toString());
        replaceTable.put("%statemachine_name_allcaps%", smName.toUpperCase());

        //---------------------------
        // Generate references to all parent FSMs
         StringBuffer parentFSMArguments = new StringBuffer();
         StringBuffer parentFSMReferences = new StringBuffer();
         StringBuffer parentFSMAssignments = new StringBuffer();
         StringBuffer parentFSMIncludes = new StringBuffer();
         
         for ( Reference ref : parentList )
         {             
             // Argument list for constructor...
             if (parentFSMArguments.length() != 0) parentFSMArguments.append(", ");
             parentFSMArguments.append(ref.namespace + "::" + ref.name + "* p" + ref.name);
             
             // References for member variables.
             parentFSMReferences.append("\t" + ref.namespace + "::" + ref.name + "* p" + ref.name + ";");
             parentFSMReferences.append(System.getProperty("line.separator"));
             
             // And assign incoming arguments to member variables
             parentFSMAssignments.append("\tthis->p" + ref.name + " = p" + ref.name + ";");
             parentFSMAssignments.append(System.getProperty("line.separator"));
             
             // Include list...
             parentFSMIncludes.append("#include \"" + ref.namespace + "/" + ref.name + ".h\"");
             parentFSMIncludes.append(System.getProperty("line.separator"));
         }
         
         replaceTable.put("%parent_fsm_arguments%", parentFSMArguments.toString());
         replaceTable.put("%parent_fsm_references%", parentFSMReferences.toString());
         replaceTable.put("%parent_fsm_includes%", parentFSMIncludes.toString());
        //---------------------------

        // put user changes in template
        // make sure header was included
        if (replaceUserHeaders.length() == 0) {
            replaceUserHeaders.append("#include \"" + namespace + "/" + smName + ".h\"").append(System.getProperty("line.separator"));
        }
        replaceTable.put("%user_include_definitions%", replaceUserHeaders.toString());
        replaceTable.put("%user_constants_definitions%", replaceUserConstants.toString());
        replaceTable.put("%user_definitions%", replaceUserFunctions.toString());
        //---------------------------


        //---------------------------
        // put items in template
        // make sure constructor was included, if not put in standard constructor
        if (replaceUserConstructor.length() == 0) {
            replaceUserConstructor.append("{").append(System.getProperty("line.separator"));
            replaceUserConstructor.append(System.getProperty("line.separator"));
            replaceUserConstructor.append("\t/*").append(System.getProperty("line.separator"));
            replaceUserConstructor.append("\t * If there are other variables, context must be constructed last so that all ").append(System.getProperty("line.separator"));
            replaceUserConstructor.append("\t * class variables are available if an EntryAction of the InitialState of the ").append(System.getProperty("line.separator"));
            replaceUserConstructor.append("\t * statemachine needs them. ").append(System.getProperty("line.separator"));
            replaceUserConstructor.append("\t */").append(System.getProperty("line.separator"));
            replaceUserConstructor.append("\tcontext = new " + smName + "Context(*this);").append(System.getProperty("line.separator"));
            replaceUserConstructor.append(System.getProperty("line.separator")).append(parentFSMAssignments);
            replaceUserConstructor.append("}").append(System.getProperty("line.separator"));
        }

        replaceTable.put("%sm_constuctor%", replaceUserConstructor.toString());

        //---------------------------


        //---------------------------
        // Generate the action methods
        StringBuffer virtualActionMethods = new StringBuffer();
        StringBuffer actionDeclarations = new StringBuffer();
        StringBuffer actionDefinitions = new StringBuffer();
		StringBuffer setupNotifications = new StringBuffer();

        // generate actions
        generateSMCActions(smName, transportVersion, replaceActionFunction, actionOrSendActionList, virtualActionMethods, actionDeclarations, actionDefinitions);

        // Generate popExitAction methods
        createPopExitTransitionWrappers(virtualActionMethods, actionDeclarations, actionDefinitions, flattenedStates, smName);

		// generate event notifications for coordination with composed state machines
        generateNotifications(sm, sd, ss, setupNotifications);

        // put items in template
        replaceTable.put("%pure_virtual_action_method_declarations%", virtualActionMethods.toString());
        replaceTable.put("%action_method_declarations%", actionDeclarations.toString());
        replaceTable.put("%action_method_definitions%", actionDefinitions.toString());
		replaceTable.put("%setup_notifications%", setupNotifications.toString());

        //junit testing
        actionMethodCode = actionDefinitions;
        //---------------------------


        //---------------------------
        // Generate the guard methods
        StringBuffer virtualGuardMethods = new StringBuffer();
        StringBuffer guardDeclarations = new StringBuffer();
        StringBuffer guardDefinitions = new StringBuffer();

        // generate guards
        generateSMCGuards(sm, sd, ss, replaceGuardFunction, guardList, virtualGuardMethods, guardDeclarations, guardDefinitions);

        // put items in template
        replaceTable.put("%pure_virtual_guard_method_declarations%", virtualGuardMethods.toString());
        replaceTable.put("%guard_method_declarations%", guardDeclarations.toString());
        replaceTable.put("%guard_method_definitions%", guardDefinitions.toString());
        //---------------------------


        //---------------------------
        // use filled template to generate final file
        TemplateHandler tplHandler = new TemplateHandler(replaceTable);
        File templateDir;
        // Handle Eclipse plugin's use of relative paths.
        if(new File("plugins/org.jts.eclipse.data_1.0/templates/statemachine").exists())
        {
            templateDir = new File("plugins/org.jts.eclipse.data_1.0/templates/statemachine");
        }
        else
        {
            templateDir =  new File("templates/statemachine");
        }
        boolean validFile = false;

        for (File template : templateDir.listFiles(new FileExtensionFilter("tpl"))) {
            String destFileName = tplHandler.adjustString(template.getName().substring(0, template.getName().length() - 4));
            File dest = null;

            if (destFileName.endsWith(".h")) {
                validFile = true;
                dest = new File(outDir + "/include/" + namespace + "/" + destFileName);
                if (dest.exists()) {
                    dest.renameTo(new File(outDir + "/include/" + namespace + "/" + destFileName + ".old"));
                    dest = new File(outDir + "/include/" + namespace + "/" + destFileName);
                }
            } else if (destFileName.endsWith(".cpp")) {
                validFile = true;
                dest = new File(outDir + "/src/" + namespace + "/" + destFileName);
                if (dest.exists()) {
                    dest.renameTo(new File(outDir + "/src/" + namespace + "/" + destFileName + ".old"));
                    dest = new File(outDir + "/src/" + namespace + "/" + destFileName);
                }
            } else if (destFileName.endsWith(".cs") || destFileName.endsWith(".java")) {
                validFile = false;
                // Don't do anything. Files not needed.
            } else {
                validFile = true;
                dest = new File(outDir + "/" + namespace + "/" + destFileName);
                if (dest.exists()) {
                    dest.renameTo(new File(outDir + "/" + namespace + "/" + destFileName + ".old"));
                    dest = new File(outDir + "/" + namespace + "/" + destFileName);
                }
            }

            try {
                if (validFile) {
                    Util.copyFile(template, dest);
                    tplHandler.adjustFile(dest);
                }
            } catch (Exception e) {
                throw new CodeGeneratorException("Could not Copy File [PBG]");
            }
        }



    }

    /**
     * Add generate action and guard code to state machine template for Java
     * @param smName
     * @param transportVersion
     * @param outDir
     * @param namespace
     * @param flattenedStates
     *
     */
    private void generateOwnerAndImplFilesJava(StateMachine sm, ServiceDef sd, ServiceSet ss, String transportVersion, String outDir, String namespace, List<org.jts.jsidl.binding.State> flattenedStates) 
	{
		String smName = sm.getName();

        // Make a list of all unique actions and guards throughout all the flattened states of the current state machine
        TreeSet<String> actionOrSendActionList = new TreeSet<String>();
        TreeSet<String> guardList = new TreeSet<String>();

        addActionListTreeSet(flattenedStates, actionOrSendActionList);
        addGuardListTreeSet(flattenedStates, guardList);

        // Parse existing files, if any, for function definitions
        Hashtable<String, StringBuffer> replaceHandleFunction = new Hashtable<String, StringBuffer>();
        Hashtable<String, StringBuffer> replaceActionFunction = new Hashtable<String, StringBuffer>();
        Hashtable<String, StringBuffer> replaceGuardFunction = new Hashtable<String, StringBuffer>();

        StringBuffer replaceUserFunctions = new StringBuffer();
        StringBuffer replaceUserHeaders = new StringBuffer();
        StringBuffer replaceUserConstants = new StringBuffer();
        StringBuffer replaceUserConstructor = new StringBuffer();
        StringBuffer replaceUserDestructor = new StringBuffer();
        StringBuffer replaceUserNamespaces = new StringBuffer();

		// Before we generate the service files, we need to determine any inheritance requirements.
		// That is, this FSM may inherit from parent FSMs, for which a reference must be passed
		// to the service constructor.
		Vector<Reference> parentList = new Vector<Reference>();
		org.jts.codegenerator.support.InheritanceHelper.getParentFSMList( m_codeType, sm.getName(), ss, sd, parentList);

        File fl = new File(outDir + "/src/" + namespace + "/" + smName + ".java");

        if (fl.exists()) {
            try {
                FileInputStream fstream = new FileInputStream(outDir + "/src/" + namespace + "/" + smName + ".java");
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String strLine;

                while ((strLine = br.readLine()) != null) {
                    // headers
                    if (strLine.startsWith("import")) {
                        parseHeader(br, strLine, replaceUserHeaders);
                    } // constructor
                    else if (strLine.startsWith("public " + smName)) {
                        parseConstructor(br, strLine, replaceUserConstructor);
                    }// statically defined functions
                    else if (strLine.startsWith("static ")) {
                        if (strLine.endsWith(";")) {
                            parseStaticVariable(br, strLine, replaceUserFunctions);
                        } else {
                            parseStaticFunction(br, strLine, replaceUserFunctions);
                        }
                    } /*// user defined classes
                    else if (strLine.startsWith("public class"))
                    {
                    System.out.println(replaceUserFunctions.toString());
                    parseClass(br, strLine, replaceUserFunctions);
                    } // action definitions*/ else if (strLine.startsWith("void")) {
                        parseActionFunction(br, strLine, replaceActionFunction);
                    } // guard definitions
                    else if (strLine.startsWith("boolean")) {
                        parseGuardFunction(br, strLine, replaceGuardFunction);
                    }
                }

                in.close();
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }


        //---------------------------
        // Now we generate the files for the action and guard definitions
        Hashtable<String, String> replaceTable = new Hashtable<String, String>();

        // put items in template
        replaceTable.put("%copyright%", "");
        replaceTable.put("%service_namespace%", namespace);
        replaceTable.put("%statemachine_name%", smName);
        replaceTable.put("%statemachine_name_allcaps%", smName.toUpperCase());

		//---------------------------
		// Generate references to all parent FSMssm_constuctor
			StringBuffer parentFSMArguments = new StringBuffer();
			StringBuffer parentFSMReferences = new StringBuffer();
			StringBuffer parentFSMAssignments = new StringBuffer();
			StringBuffer parentFSMIncludes = new StringBuffer();
         
			for ( Reference ref : parentList )
			{             
				// Argument list for constructor...
				if (parentFSMArguments.length() != 0) parentFSMArguments.append(", ");
				parentFSMArguments.append(ref.name + " p" + ref.name);
             
				// References for member variables.
				parentFSMReferences.append("\t" + ref.name + " p" + ref.name + ";");
				parentFSMReferences.append(System.getProperty("line.separator"));
             
				// And assign incoming arguments to member variables
				parentFSMAssignments.append("\tthis.p" + ref.name + " = p" + ref.name + ";");
				parentFSMAssignments.append(System.getProperty("line.separator"));
             
				// Include list...
				parentFSMIncludes.append("import src." + ref.namespace + "." + ref.name + ";");
				parentFSMIncludes.append(System.getProperty("line.separator"));
			}
         
			replaceTable.put("%parent_fsm_arguments%", parentFSMArguments.toString());
			replaceTable.put("%parent_fsm_references%", parentFSMReferences.toString());
			replaceTable.put("%parent_fsm_includes%", parentFSMIncludes.toString());
		//---------------------------

        StringBuffer includeList = new StringBuffer();
        
        // only add event package imports when they exist
        if( serviceDefHasInternalEvents )
        {
        	includeList.append( "import src." + namespace + ".InternalEvents.*;" );
        	includeList.append( System.getProperty( "line.separator" ) );
        }

        // only add message package imports when they exist
        if( serviceDefHasMessages )
        {
        	includeList.append( "import src." + namespace + ".Messages.*;" );
        	includeList.append( System.getProperty( "line.separator" ) );
        }
        
        replaceTable.put( "%statemachine_include_list%", includeList.toString() );
        
        // put user changes in template
        replaceTable.put("%user_include_definitions%", replaceUserHeaders.toString());
        replaceTable.put("%user_constants_definitions%", replaceUserConstants.toString());
        replaceTable.put("%user_definitions%", replaceUserFunctions.toString());
        //---------------------------


        //---------------------------
        // put items in template
        // make sure constructor was included, if not put in standard constructor
        if (replaceUserConstructor.length() == 0) {
            replaceUserConstructor.append("{").append(System.getProperty("line.separator"));
            replaceUserConstructor.append(System.getProperty("line.separator"));
            replaceUserConstructor.append("\t/*").append(System.getProperty("line.separator"));
            replaceUserConstructor.append("\t * If there are other variables, context must be constructed last so that all ").append(System.getProperty("line.separator"));
            replaceUserConstructor.append("\t * class variables are available if an EntryAction of the InitialState of the ").append(System.getProperty("line.separator"));
            replaceUserConstructor.append("\t * statemachine needs them. ").append(System.getProperty("line.separator"));
            replaceUserConstructor.append("\t */").append(System.getProperty("line.separator"));
            replaceUserConstructor.append("\t\tcontext = new " + smName + "Context(this);").append(System.getProperty("line.separator"));
			replaceUserConstructor.append(System.getProperty("line.separator")).append(parentFSMAssignments);
            replaceUserConstructor.append("}").append(System.getProperty("line.separator"));
        }

        replaceTable.put("%sm_constuctor%", replaceUserConstructor.toString());

        //---------------------------


        //---------------------------
        // Generate the action methods
        StringBuffer virtualActionMethods = new StringBuffer();
        StringBuffer actionDeclarations = new StringBuffer();
        StringBuffer actionDefinitions = new StringBuffer();
		StringBuffer setupNotifications = new StringBuffer();

        // generate actions
        generateSMCActions(smName, transportVersion, replaceActionFunction, actionOrSendActionList, virtualActionMethods, actionDeclarations, actionDefinitions);

        // Generate popExitAction methods
        createPopExitTransitionWrappers(virtualActionMethods, actionDeclarations, actionDefinitions, flattenedStates, smName);

		// generate event notifications for coordination with composed state machines
		generateNotifications(sm, sd, ss, setupNotifications);

        // put items in template
        replaceTable.put("%pure_virtual_action_method_declarations%", virtualActionMethods.toString());
        replaceTable.put("%action_method_declarations%", actionDeclarations.toString());
        replaceTable.put("%action_method_definitions%", actionDefinitions.toString());
		replaceTable.put("%setup_notifications%", setupNotifications.toString());

        //junit testing
        actionMethodCode = actionDefinitions;
        //---------------------------


        //---------------------------
        // Generate the guard methods
        StringBuffer virtualGuardMethods = new StringBuffer();
        StringBuffer guardDeclarations = new StringBuffer();
        StringBuffer guardDefinitions = new StringBuffer();

        // generate guards
        generateSMCGuards(sm, sd, ss, replaceGuardFunction, guardList, virtualGuardMethods, guardDeclarations, guardDefinitions);

        // put items in template
        replaceTable.put("%pure_virtual_guard_method_declarations%", virtualGuardMethods.toString());
        replaceTable.put("%guard_method_declarations%", guardDeclarations.toString());
        replaceTable.put("%guard_method_definitions%", guardDefinitions.toString());
        //---------------------------


        //---------------------------
        // use filled template to generate final file
        TemplateHandler tplHandler = new TemplateHandler(replaceTable);
        File templateDir;
        // Handle Eclipse plugin's use of relative paths.
        if(new File("plugins/org.jts.eclipse.data_1.0/templates/statemachine").exists())
        {
            templateDir = new File("plugins/org.jts.eclipse.data_1.0/templates/statemachine");
        }
        else
        {
            templateDir =  new File("templates/statemachine");
        }
        for (File template : templateDir.listFiles(new FileExtensionFilter("tpl"))) {
            String destFileName = tplHandler.adjustString(template.getName().substring(0, template.getName().length() - 4));
            File dest = null;
            boolean validFile = false;

            if (destFileName.endsWith(".java")) {
                validFile = true;
                dest = new File(outDir + "/src/" + namespace + "/" + destFileName);
                if (dest.exists()) {
                    dest.renameTo(new File(outDir + "/src/" + namespace + "/" + destFileName + ".old"));
                    dest = new File(outDir + "/src/" + namespace + "/" + destFileName);
                }
            } else if (destFileName.endsWith(".cs") || destFileName.endsWith(".h") || destFileName.endsWith(".cpp")) {
                validFile = false;
                // Don't do anything. Files not needed.
            } else {
                validFile = true;
                dest = new File(outDir + "/" + namespace + "/" + destFileName);
                if (dest.exists()) {
                    dest.renameTo(new File(outDir + "/" + namespace + "/" + destFileName + ".old"));
                    dest = new File(outDir + "/" + namespace + "/" + destFileName);
                }
            }

            try {
                if (validFile) {
                    Util.copyFile(template, dest);
                    tplHandler.adjustFile(dest);
                }
            } catch (Exception e) {
                throw new CodeGeneratorException("Could not Copy File [PBG]");
            }

        }

    }

    /**
     * Add generate action and guard code to state machine template for C#
     * @param smName
     * @param transportVersion
     * @param outDir
     * @param namespace
     * @param flattenedStates
     *
     */
    private void generateOwnerAndImplFilesCSharp(StateMachine sm, ServiceDef sd, ServiceSet ss, String transportVersion, String outDir, String namespace, List<org.jts.jsidl.binding.State> flattenedStates, StringBuffer inc) 
	{
		String smName = sm.getName();

        // Make a list of all unique actions and guards throughout all the flattened states of the current state machine
        TreeSet<String> actionOrSendActionList = new TreeSet<String>();
        TreeSet<String> guardList = new TreeSet<String>();

        addActionListTreeSet(flattenedStates, actionOrSendActionList);
        addGuardListTreeSet(flattenedStates, guardList);

        // Parse existing files, if any, for function definitions
        Hashtable<String, StringBuffer> replaceHandleFunction = new Hashtable<String, StringBuffer>();
        Hashtable<String, StringBuffer> replaceActionFunction = new Hashtable<String, StringBuffer>();
        Hashtable<String, StringBuffer> replaceGuardFunction = new Hashtable<String, StringBuffer>();

        StringBuffer replaceUserFunctions = new StringBuffer();
        StringBuffer replaceUserHeaders = new StringBuffer();
        StringBuffer replaceUserConstants = new StringBuffer();
        StringBuffer replaceUserConstructor = new StringBuffer();
        StringBuffer replaceUserDestructor = new StringBuffer();
        StringBuffer replaceUserNamespaces = new StringBuffer();

		// Before we generate the service files, we need to determine any inheritance requirements.
		// That is, this FSM may inherit from parent FSMs, for which a reference must be passed
		// to the service constructor.
		Vector<Reference> parentList = new Vector<Reference>();
		org.jts.codegenerator.support.InheritanceHelper.getParentFSMList( m_codeType, sm.getName(), ss, sd, parentList);

        File fl = new File(outDir + "/src/" + namespace + "/" + smName + ".cs");

        if (fl.exists()) {
            try {
                FileInputStream fstream = new FileInputStream(outDir + "/src/" + namespace + "/" + smName + ".cs");
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String strLine;

                while ((strLine = br.readLine()) != null) {
                    // headers (if user added math.h etc)
                    if (strLine.startsWith("using JTS")) {
                        // Do nothing. Part of template.
                    } else if (strLine.startsWith("using")) {
                        parseHeader(br, strLine, replaceUserHeaders);
                    } // user defined contants
                    else if (strLine.startsWith("const")) {
                        parseConstant(br, strLine, replaceUserConstants);
                    } // constructor
                    else if (strLine.startsWith(smName)) {
                        parseConstructor(br, strLine, replaceUserConstructor);
                    }// statically defined functions
                    else if (strLine.startsWith("static ")) {
                        if (strLine.endsWith(";")) {
                            parseStaticVariable(br, strLine, replaceUserFunctions);
                        } else {
                            parseStaticFunction(br, strLine, replaceUserFunctions);
                        }
                    } else if (strLine.startsWith("extern ")) {
                        parseStaticVariable(br, strLine, replaceUserFunctions);
                    } // user defined classes
                    //                        else if (strLine.startsWith("public class"))
                    //                            {
                    //                            parseClass(br, strLine, replaceUserFunctions);
                    //                            } // action definitions
                    else if (strLine.startsWith("void")) {
                        parseActionFunction(br, strLine, replaceActionFunction);
                    } // guard definitions
                    else if (strLine.startsWith("bool")) {
                        parseGuardFunction(br, strLine, replaceGuardFunction);
                    }
                }

                in.close();
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }


        //---------------------------
        // Now we generate the files for the action and guard definitions
        Hashtable<String, String> replaceTable = new Hashtable<String, String>();

        // put items in template
        replaceTable.put("%copyright%", "");
        replaceTable.put("%service_imports%", inc.toString());
        replaceTable.put("%service_namespace%", namespace);
        replaceTable.put("%statemachine_name%", smName);
        replaceTable.put("%statemachine_name_allcaps%", smName.toUpperCase());

		//---------------------------
		// Generate references to all parent FSMs
			StringBuffer parentFSMArguments = new StringBuffer();
			StringBuffer parentFSMReferences = new StringBuffer();
			StringBuffer parentFSMAssignments = new StringBuffer();
			StringBuffer parentFSMIncludes = new StringBuffer();
         
			for ( Reference ref : parentList )
			{             
				// Argument list for constructor...
				if (parentFSMArguments.length() != 0) parentFSMArguments.append(", ");
				parentFSMArguments.append(ref.namespace + "." + ref.name + " p" + ref.name);
             
				// References for member variables.
				parentFSMReferences.append("\t" + ref.namespace + "." + ref.name + " p" + ref.name + ";");
				parentFSMReferences.append(System.getProperty("line.separator"));
             
				// And assign incoming arguments to member variables
				parentFSMAssignments.append("\tthis.p" + ref.name + " = p" + ref.name + ";");
				parentFSMAssignments.append(System.getProperty("line.separator"));
             
				// Include list...
				parentFSMIncludes.append("import src." + ref.namespace + "." + ref.name + ";");
				parentFSMIncludes.append(System.getProperty("line.separator"));
			}
         
			replaceTable.put("%parent_fsm_arguments%", parentFSMArguments.toString());
			replaceTable.put("%parent_fsm_references%", parentFSMReferences.toString());
			replaceTable.put("%parent_fsm_includes%", parentFSMIncludes.toString());
		//---------------------------

        // put user changes in template
        replaceTable.put("%user_include_definitions%", replaceUserHeaders.toString());
        replaceTable.put("%user_constants_definitions%", replaceUserConstants.toString());
        replaceTable.put("%user_definitions%", replaceUserFunctions.toString());
        //---------------------------


        //---------------------------
        // put items in template
        // make sure constructor was included, if not put in standard constructor
        if (replaceUserConstructor.length() == 0) {
            replaceUserConstructor.append("\t{").append(System.getProperty("line.separator"));
            replaceUserConstructor.append(System.getProperty("line.separator"));
            replaceUserConstructor.append("\t\t/*").append(System.getProperty("line.separator"));
            replaceUserConstructor.append("\t * If there are other variables, context must be constructed last so that all ").append(System.getProperty("line.separator"));
            replaceUserConstructor.append("\t * class variables are available if an EntryAction of the InitialState of the ").append(System.getProperty("line.separator"));
            replaceUserConstructor.append("\t * statemachine needs them. ").append(System.getProperty("line.separator"));
            replaceUserConstructor.append("\t\t */").append(System.getProperty("line.separator"));
            replaceUserConstructor.append("\t\tcontext = new " + smName + "Context(this);").append(System.getProperty("line.separator"));
			replaceUserConstructor.append(System.getProperty("line.separator")).append(parentFSMAssignments);
            replaceUserConstructor.append("\t}").append(System.getProperty("line.separator"));
        }

        replaceTable.put("%sm_constuctor%", replaceUserConstructor.toString());

        //---------------------------


        //---------------------------
        // Generate the action methods
        StringBuffer virtualActionMethods = new StringBuffer();
        StringBuffer actionDeclarations = new StringBuffer();
        StringBuffer actionDefinitions = new StringBuffer();
		StringBuffer setupNotifications = new StringBuffer();

        // generate actions
        generateSMCActions(smName, transportVersion, replaceActionFunction, actionOrSendActionList, virtualActionMethods, actionDeclarations, actionDefinitions);

        // Generate popExitAction methods
        createPopExitTransitionWrappers(virtualActionMethods, actionDeclarations, actionDefinitions, flattenedStates, smName);

		// generate event notifications for coordination with composed state machines
		generateNotifications(sm, sd, ss, setupNotifications);

        // put items in template
        replaceTable.put("%pure_virtual_action_method_declarations%", virtualActionMethods.toString());
        replaceTable.put("%action_method_declarations%", actionDeclarations.toString());
        replaceTable.put("%action_method_definitions%", actionDefinitions.toString());
		replaceTable.put("%setup_notifications%", setupNotifications.toString());

        //junit testing
        actionMethodCode = actionDefinitions;
        //---------------------------


        //---------------------------
        // Generate the guard methods
        StringBuffer virtualGuardMethods = new StringBuffer();
        StringBuffer guardDeclarations = new StringBuffer();
        StringBuffer guardDefinitions = new StringBuffer();

        // generate guards
        generateSMCGuards(sm, sd, ss, replaceGuardFunction, guardList, virtualGuardMethods, guardDeclarations, guardDefinitions);

        // put items in template
        replaceTable.put("%pure_virtual_guard_method_declarations%", virtualGuardMethods.toString());
        replaceTable.put("%guard_method_declarations%", guardDeclarations.toString());
        replaceTable.put("%guard_method_definitions%", guardDefinitions.toString());
        //---------------------------


        //---------------------------
        // use filled template to generate final file
        TemplateHandler tplHandler = new TemplateHandler(replaceTable);
         File templateDir;
        // Handle Eclipse plugin's use of relative paths.
        if(new File("plugins/org.jts.eclipse.data_1.0/templates/statemachine").exists())
        {
            templateDir = new File("plugins/org.jts.eclipse.data_1.0/templates/statemachine");
        }
        else
        {
            templateDir =  new File("templates/statemachine");
        }
        boolean validFile = false;

        for (File template : templateDir.listFiles(new FileExtensionFilter("tpl"))) {
            String destFileName = tplHandler.adjustString(template.getName().substring(0, template.getName().length() - 4));
            File dest = null;

            if (destFileName.endsWith(".cs")) {
                validFile = true;
                dest = new File(outDir + "/src/" + namespace + "/" + destFileName);
                if (dest.exists()) {
                    dest.renameTo(new File(outDir + "/src/" + namespace + "/" + destFileName + ".old"));
                    dest = new File(outDir + "/src/" + namespace + "/" + destFileName);
                }
            } else if (destFileName.endsWith(".java") || destFileName.endsWith(".h") || destFileName.endsWith(".cpp")) {
                validFile = false;
                // Don't do anything. Files not needed.
            } else {
                validFile = true;
                dest = new File(outDir + "/" + namespace + "/" + destFileName);
                if (dest.exists()) {
                    dest.renameTo(new File(outDir + "/" + namespace + "/" + destFileName + ".old"));
                    dest = new File(outDir + "/" + namespace + "/" + destFileName);
                }
            }

            try {
                if (validFile) {
                    Util.copyFile(template, dest);
                    tplHandler.adjustFile(dest);
                }
            } catch (Exception e) {
                throw new CodeGeneratorException("Could not Copy File [PBG]");
            }
        }
    }

    /*
     * @param in, removes namespace information from string
     */
    private String stripNamespace(String in) {
        // Remove 'std::' and 'JTS::' from the incoming string.  We do this to migrate
        // 'old' code (without namespacing) to the new function structure (with namespacing)
        String out = in;
        while (out.contains("std::")) {
            out = out.substring(0, out.indexOf("std::"))
                    + out.substring(out.indexOf("std::") + 5, out.length());
        }
        while (out.contains("JTS::")) {
            out = out.substring(0, out.indexOf("JTS::"))
                    + out.substring(out.indexOf("JTS::") + 5, out.length());
        }
        return out;
    }


    /*
     * @param smName
     * @param transportVersion
     * @param replaceActionFunction
     * @param actionOrSendActionList
     * @param virtualActionMethods
     * @param actionDeclarations
     * @param actionDefinitions
     */
    private void generateSMCActions(String smName, String transportVersion, Hashtable<String, StringBuffer> replaceActionFunction, TreeSet<String> actionOrSendActionList, StringBuffer virtualActionMethods, StringBuffer actionDeclarations, StringBuffer actionDefinitions) {
        for (Iterator<String> it = actionOrSendActionList.iterator(); it.hasNext();) {
            String method = it.next();

            if (m_codeType == CodeLines.CodeType.C_PLUS_PLUS) {
                virtualActionMethods.append("\tvirtual void " + method + "=0;");
                virtualActionMethods.append(System.getProperty("line.separator"));

                actionDeclarations.append("\tvirtual void " + method + ";");
                actionDeclarations.append(System.getProperty("line.separator"));

                actionDefinitions.append("void " + smName + "::" + method).append(System.getProperty("line.separator"));

                // use replacement action function definitions if available
                if (replaceActionFunction.get(method) != null) {
                    actionDefinitions.append(replaceActionFunction.get(method));
                } else if (replaceActionFunction.get(stripNamespace(method)) != null) {
                    actionDefinitions.append(replaceActionFunction.get(stripNamespace(method)));
                } else if (transportVersion.equals("1.0") && smName.contains("_SendFSM") && method.equals("EnqueueAction(Send msg)")) {
                    // special handling for SendFSM: Auto generate implementation.
                    actionDefinitions.append("{").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\tsendJausMessage(").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tmsg.getBody()->getSendRec()->getMessagePayload()->getLength(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tmsg.getBody()->getSendRec()->getMessagePayload()->getData(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tJausAddress(msg.getBody()->getSendRec()->getDestSubsystemID(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\t\tmsg.getBody()->getSendRec()->getDestNodeID(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\t\tmsg.getBody()->getSendRec()->getDestComponentID()));").append(System.getProperty("line.separator"));
                    actionDefinitions.append("}").append(System.getProperty("line.separator"));
                } else if (transportVersion.equals("1.0") && smName.contains("_SendFSM") && method.equals("BroadcastGlobalEnqueueAction(BroadcastGlobal msg)")) {
                    // special handling for SendFSM: Auto generate implementation.
                    actionDefinitions.append("{").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\tsendJausMessage(").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tmsg.getBody()->getSendRec()->getMessagePayload()->getLength(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tmsg.getBody()->getSendRec()->getMessagePayload()->getData(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tJausAddress(0xFFFF, 0xFF, 0xFF));").append(System.getProperty("line.separator"));
                    actionDefinitions.append("}").append(System.getProperty("line.separator"));
                } else if (transportVersion.equals("1.0") && smName.contains("_SendFSM") && method.equals("BroadcastLocalEnqueueAction(BroadcastLocal msg)")) {
                    // special handling for SendFSM: Auto generate implementation.
                    actionDefinitions.append("{").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\tsendJausMessage(").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tmsg.getBody()->getSendRec()->getMessagePayload()->getLength(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tmsg.getBody()->getSendRec()->getMessagePayload()->getData(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tJausAddress(jausRouter->getJausAddress()->getSubsystemID(), 0xFF, 0xFF));").append(System.getProperty("line.separator"));
                    actionDefinitions.append("}").append(System.getProperty("line.separator"));
                } else if (transportVersion.equals("1.1") && smName.contains("_SendFSM") && method.equals("EnqueueAction(Send msg)")) {
                    // special handling for SendFSM: Auto generate implementation.
                    actionDefinitions.append("{").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\tsendJausMessage(").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tmsg.getBody()->getSendRec()->getMessagePayload()->getLength(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tmsg.getBody()->getSendRec()->getMessagePayload()->getData(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tJausAddress(msg.getBody()->getSendRec()->getDestinationID()->getSubsystemID(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\t\tmsg.getBody()->getSendRec()->getDestinationID()->getNodeID(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\t\tmsg.getBody()->getSendRec()->getDestinationID()->getComponentID()));").append(System.getProperty("line.separator"));
                    actionDefinitions.append("}").append(System.getProperty("line.separator"));
                } else if (transportVersion.equals("1.1") && smName.contains("_SendFSM") && method.equals("BroadcastGlobalEnqueueAction(BroadcastGlobal msg)")) {
                    // special handling for SendFSM: Auto generate implementation.
                    actionDefinitions.append("{").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\tsendJausMessage(").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tmsg.getBody()->getBroadcastRec()->getMessagePayload()->getLength(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tmsg.getBody()->getBroadcastRec()->getMessagePayload()->getData(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tJausAddress(0xFFFF, 0xFF, 0xFF));").append(System.getProperty("line.separator"));
                    actionDefinitions.append("}").append(System.getProperty("line.separator"));
                } else if (transportVersion.equals("1.1") && smName.contains("_SendFSM") && method.equals("BroadcastLocalEnqueueAction(BroadcastLocal msg)")) {
                    // special handling for SendFSM: Auto generate implementation.
                    actionDefinitions.append("{").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\tsendJausMessage(").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tmsg.getBody()->getBroadcastRec()->getMessagePayload()->getLength(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tmsg.getBody()->getBroadcastRec()->getMessagePayload()->getData(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tJausAddress(jausRouter->getJausAddress()->getSubsystemID(), 0xFF, 0xFF));").append(System.getProperty("line.separator"));
                    actionDefinitions.append("}").append(System.getProperty("line.separator"));
                } else {
                    actionDefinitions.append("{").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t/// Insert User Code HERE").append(System.getProperty("line.separator"));
                    actionDefinitions.append("}").append(System.getProperty("line.separator"));
                }

            } else if (m_codeType == CodeLines.CodeType.JAVA) {
                actionDefinitions.append("public void " + method).append(System.getProperty("line.separator"));

                // use replacement action function definitions if available
                if (replaceActionFunction.get(method) != null) {
                    actionDefinitions.append(replaceActionFunction.get(method));
                } else if (replaceActionFunction.get(stripNamespace(method)) != null) {
                    actionDefinitions.append(replaceActionFunction.get(stripNamespace(method)));
                } else if (transportVersion.equals("1.0") && smName.contains("_SendFSM") && method.equals("EnqueueAction(Send msg)")) {
                    // special handling for SendFSM: Auto generate implementation.
                    actionDefinitions.append("{").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\tsendJausMessage(").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tmsg.getBody().getSendRec().getMessagePayload().getLength(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tmsg.getBody().getSendRec().getMessagePayload().getData(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tnew JausAddress(msg.getBody().getSendRec().getDestSubsystemID(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\t\tmsg.getBody().getSendRec().getDestNodeID(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\t\tmsg.getBody().getSendRec().getDestComponentID()));").append(System.getProperty("line.separator"));
                    actionDefinitions.append("}").append(System.getProperty("line.separator"));
                } else if (transportVersion.equals("1.0") && smName.contains("_SendFSM") && method.equals("BroadcastGlobalEnqueueAction(BroadcastGlobal msg)")) {
                    // special handling for SendFSM: Auto generate implementation.
                    actionDefinitions.append("{").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\tsendJausMessage(").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tmsg.getBody().getSendRec().getMessagePayload().getLength(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tmsg.getBody().getSendRec().getMessagePayload().getData(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tnew JausAddress(0xFFFF, (short) 0xFF, (short) 0xFF));").append(System.getProperty("line.separator"));
                    actionDefinitions.append("}").append(System.getProperty("line.separator"));
                } else if (transportVersion.equals("1.0") && smName.contains("_SendFSM") && method.equals("BroadcastLocalEnqueueAction(BroadcastLocal msg)")) {
                    // special handling for SendFSM: Auto generate implementation.
                    actionDefinitions.append("{").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\tsendJausMessage(").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tmsg.getBody().getSendRec().getMessagePayload().getLength(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tmsg.getBody().getSendRec().getMessagePayload().getData(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tnew JausAddress(jausRouter.getJausAddress().getSubsystemID(), (short) 0xFF, (short) 0xFF));").append(System.getProperty("line.separator"));
                    actionDefinitions.append("}").append(System.getProperty("line.separator"));
                } else if (transportVersion.equals("1.1") && smName.contains("_SendFSM") && method.equals("EnqueueAction(Send msg)")) {
                    // special handling for SendFSM: Auto generate implementation.
                    actionDefinitions.append("{").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\tsendJausMessage(").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tmsg.getBody().getSendRec().getMessagePayload().getLength(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tmsg.getBody().getSendRec().getMessagePayload().getData(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tnew JausAddress((int)msg.getBody().getSendRec().getDestinationID().getSubsystemID(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\t\t(short) msg.getBody().getSendRec().getDestinationID().getNodeID(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\t\t(short) msg.getBody().getSendRec().getDestinationID().getComponentID()));").append(System.getProperty("line.separator"));
                    actionDefinitions.append("}").append(System.getProperty("line.separator"));
                } else if (transportVersion.equals("1.1") && smName.contains("_SendFSM") && method.equals("BroadcastGlobalEnqueueAction(BroadcastGlobal msg)")) {
                    // special handling for SendFSM: Auto generate implementation.
                    actionDefinitions.append("{").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\tsendJausMessage(").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tmsg.getBody().getBroadcastRec().getMessagePayload().getLength(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tmsg.getBody().getBroadcastRec().getMessagePayload().getData(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tnew JausAddress(0xFFFF,(short) 0xFF,(short) 0xFF));").append(System.getProperty("line.separator"));
                    actionDefinitions.append("}").append(System.getProperty("line.separator"));
                } else if (transportVersion.equals("1.1") && smName.contains("_SendFSM") && method.equals("BroadcastLocalEnqueueAction(BroadcastLocal msg)")) {
                    // special handling for SendFSM: Auto generate implementation.
                    actionDefinitions.append("{").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\tsendJausMessage(").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tmsg.getBody().getBroadcastRec().getMessagePayload().getLength(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tmsg.getBody().getBroadcastRec().getMessagePayload().getData(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tnew JausAddress(jausRouter.getJausAddress().getSubsystemID(), (short) 0xFF, (short) 0xFF));").append(System.getProperty("line.separator"));
                    actionDefinitions.append("}").append(System.getProperty("line.separator"));
                } else {
                    actionDefinitions.append("{").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t/// Insert User Code HERE").append(System.getProperty("line.separator"));
                    actionDefinitions.append("}").append(System.getProperty("line.separator"));
                }
            } else if (m_codeType == CodeLines.CodeType.C_SHARP) {
                actionDefinitions.append("public virtual void " + method).append(System.getProperty("line.separator"));

                // use replacement action function definitions if available
                if (replaceActionFunction.get(method) != null) {
                    actionDefinitions.append(replaceActionFunction.get(method));
                } else if (replaceActionFunction.get(stripNamespace(method)) != null) {
                    actionDefinitions.append(replaceActionFunction.get(stripNamespace(method)));
                } else if (transportVersion.equals("1.0") && smName.contains("_SendFSM") && method.equals("EnqueueAction(Send msg)")) {
                    // special handling for SendFSM: Auto generate implementation.
                    actionDefinitions.append("{").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\tsendJausMessage(").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tmsg.getBody().getSendRec().getMessagePayload().getLength(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tmsg.getBody().getSendRec().getMessagePayload().getData(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tnew JausAddress(msg.getBody().getSendRec().getDestSubsystemID(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\t\tmsg.getBody().getSendRec().getDestNodeID(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\t\tmsg.getBody().getSendRec().getDestComponentID()));").append(System.getProperty("line.separator"));
                    actionDefinitions.append("}").append(System.getProperty("line.separator"));
                } else if (transportVersion.equals("1.0") && smName.contains("_SendFSM") && method.equals("BroadcastGlobalEnqueueAction(BroadcastGlobal msg)")) {
                    // special handling for SendFSM: Auto generate implementation.
                    actionDefinitions.append("{").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\tsendJausMessage(").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tmsg.getBody().getSendRec().getMessagePayload().getLength(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tmsg.getBody().getSendRec().getMessagePayload().getData(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tnew JausAddress((ushort)0xFFFF, (byte) 0xFF, (byte) 0xFF));").append(System.getProperty("line.separator"));
                    actionDefinitions.append("}").append(System.getProperty("line.separator"));
                } else if (transportVersion.equals("1.0") && smName.contains("_SendFSM") && method.equals("BroadcastLocalEnqueueAction(BroadcastLocal msg)")) {
                    // special handling for SendFSM: Auto generate implementation.
                    actionDefinitions.append("{").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\tsendJausMessage(").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tmsg.getBody().getSendRec().getMessagePayload().getLength(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tmsg.getBody().getSendRec().getMessagePayload().getData(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tnew JausAddress(jausRouter.getJausAddress().getSubsystemID(), (byte) 0xFF, (byte) 0xFF));").append(System.getProperty("line.separator"));
                    actionDefinitions.append("}").append(System.getProperty("line.separator"));
                } else if (transportVersion.equals("1.1") && smName.contains("_SendFSM") && method.equals("EnqueueAction(Send msg)")) {
                    // special handling for SendFSM: Auto generate implementation.
                    actionDefinitions.append("{").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\tsendJausMessage(").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\t(uint)msg.getBody().getSendRec().getMessagePayload().getLength(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tmsg.getBody().getSendRec().getMessagePayload().getData(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tnew JausAddress((ushort)msg.getBody().getSendRec().getDestinationID().getSubsystemID(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\t\t(byte)msg.getBody().getSendRec().getDestinationID().getNodeID(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\t\t(byte)msg.getBody().getSendRec().getDestinationID().getComponentID()));").append(System.getProperty("line.separator"));
                    actionDefinitions.append("}").append(System.getProperty("line.separator"));
                } else if (transportVersion.equals("1.1") && smName.contains("_SendFSM") && method.equals("BroadcastGlobalEnqueueAction(BroadcastGlobal msg)")) {
                    // special handling for SendFSM: Auto generate implementation.
                    actionDefinitions.append("{").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\tsendJausMessage(").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\t(uint)msg.getBody().getBroadcastRec().getMessagePayload().getLength(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tmsg.getBody().getBroadcastRec().getMessagePayload().getData(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tnew JausAddress((ushort) 0xFFFF,(byte) 0xFF,(byte) 0xFF));").append(System.getProperty("line.separator"));
                    actionDefinitions.append("}").append(System.getProperty("line.separator"));
                } else if (transportVersion.equals("1.1") && smName.contains("_SendFSM") && method.equals("BroadcastLocalEnqueueAction(BroadcastLocal msg)")) {
                    // special handling for SendFSM: Auto generate implementation.
                    actionDefinitions.append("{").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\tsendJausMessage(").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\t(uint)msg.getBody().getBroadcastRec().getMessagePayload().getLength(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tmsg.getBody().getBroadcastRec().getMessagePayload().getData(),").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t\tnew JausAddress(jausRouter.getJausAddress().getSubsystemID(), (byte) 0xFF, (byte) 0xFF));").append(System.getProperty("line.separator"));
                    actionDefinitions.append("}").append(System.getProperty("line.separator"));
                } else {
                    actionDefinitions.append("{").append(System.getProperty("line.separator"));
                    actionDefinitions.append("\t/// Insert User Code HERE").append(System.getProperty("line.separator"));
                    actionDefinitions.append("}").append(System.getProperty("line.separator"));
                }
            }

            actionDefinitions.append(System.getProperty("line.separator"));
        }
    }

    /*
     * @param smName
     * @param replaceGuardFunction
     * @param guardList
     * @param virtualGuardMethods
     * @param guardDeclarations
     * @param guardDefinitions
     *
     */
    private void generateSMCGuards(StateMachine sm, ServiceDef sd, ServiceSet ss, Hashtable<String, StringBuffer> replaceGuardFunction, TreeSet<String> guardList, StringBuffer virtualGuardMethods, StringBuffer guardDeclarations, StringBuffer guardDefinitions) {
        for (Iterator<String> it = guardList.iterator(); it.hasNext();) {
            String method = it.next();
			StringBuffer implementation = new StringBuffer();

            // method could have been defined as negation, parse "!" from string
            method = method.replace("!", "").trim();

            if (m_codeType == CodeLines.CodeType.C_PLUS_PLUS) 
			{
                // Eliminate references from function name, e.g. management.accessCtrl.isControllingClient
                if (method.lastIndexOf(".") != -1) {
                    method = method.substring(method.lastIndexOf(".") + 1);
                }
                virtualGuardMethods.append("\tvirtual bool " + method + "=0;");
                virtualGuardMethods.append(System.getProperty("line.separator"));

                guardDeclarations.append("\tvirtual bool " + method + ";");
                guardDeclarations.append(System.getProperty("line.separator"));

                guardDefinitions.append("bool " + sm.getName() + "::" + method).append(System.getProperty("line.separator"));

            } 
			else if (m_codeType == CodeLines.CodeType.JAVA) 
			{
                guardDefinitions.append("public boolean " + method).append(System.getProperty("line.separator"));
            } 
			else if (m_codeType == CodeLines.CodeType.C_SHARP) 
			{
                guardDefinitions.append("public virtual bool " + method).append(System.getProperty("line.separator"));
            }

            // use replacement action function definitions if available
            if (replaceGuardFunction.get(method) != null) 
			{
                guardDefinitions.append(replaceGuardFunction.get(method));
            } 
			else if (replaceGuardFunction.get(stripNamespace(method)) != null) 
			{
                guardDefinitions.append(replaceGuardFunction.get(stripNamespace(method)));
            } 
			else 
			{
				// If this guard also exists in the parent FSM, the default implementation
				// should be to call the parent's guard function.
				String parent = org.jts.codegenerator.support.InheritanceHelper.findGuardInParentFSM(method, sm, sd, ss, m_codeType, false);
				org.jts.codegenerator.support.InheritanceHelper.generateGuardAutoImplementation( implementation, method, parent, m_codeType );

                guardDefinitions.append("{").append(System.getProperty("line.separator"));
				guardDefinitions.append( implementation );
                guardDefinitions.append("}").append(System.getProperty("line.separator"));
				guardDefinitions.append(System.getProperty("line.separator"));            
            }
        }
    }

    /*
     * @param smName
     * @param startStateEntryActions
     */
    private void generatStartStateEntryActionCalls(String smName, List<Object> startStateEntryActions) {
        for (Iterator<Object> it = startStateEntryActions.iterator(); it.hasNext();) {
            Object obj = it.next();

            if (m_codeType == CodeLines.CodeType.C_PLUS_PLUS) {
                entryActionCalls.append("\tp" + smName + "->" + org.jts.codegenerator.protocolBehavior.Action.addActionOrSendAction(obj));
            } else if (m_codeType == CodeLines.CodeType.JAVA) {
                entryActionCalls.append("\tp" + smName + "." + org.jts.codegenerator.protocolBehavior.Action.addActionOrSendAction(obj));
            } else if (m_codeType == CodeLines.CodeType.C_SHARP) {
                entryActionCalls.append("\tp" + smName + "." + org.jts.codegenerator.protocolBehavior.Action.addActionOrSendAction(obj));
            }
            entryActionCalls.append(System.getProperty("line.separator"));

        }
    }

    /*
     * @param br
     * @param strLine
     * @param replaceUserHeader
     */
    private void parseHeader(BufferedReader br, String strLine, StringBuffer replaceUserHeader) {
        StringBuffer header = new StringBuffer();

        // keep track if we are still parsing headers
        boolean keepGoing = true;

        while (keepGoing) {
            // add line to header
            header.append(strLine).append(System.getProperty("line.separator"));

            try {
                // set return position in case its not a header or comment
                br.mark(100);

                // read in line
                strLine = br.readLine();
            } catch (Exception e) {
                System.out.println(e.toString());
            }

            // take care of possible comments
            if (isLineComment(strLine)) {
                continue;
            } // take care of possible block comments
            else if (isLineBlockCommentStart(strLine)) {
                while (!isLineBlockCommentEnd(strLine)) {
                    try {
                        strLine = br.readLine();
                    } catch (Exception e) {
                    }

                    header.append(strLine).append(System.getProperty("line.separator"));
                }
                continue;
            } else if (strLine.startsWith("#")) {
                continue;
            }

            // reset buffered reader so we try to parse this line again after this function returns
            try {
                br.reset();
            } catch (Exception e) {
                System.out.println(e.toString());
            }

            // stop reading in lines
            keepGoing = false;
        }

        // put header lines/comments into buffer
        replaceUserHeader.append(header);
    }

    /*
     * @param br
     * @param strLine
     * @param replaceUserConstant
     */
    private void parseConstant(BufferedReader br, String strLine, StringBuffer replaceUserConstant) {
        StringBuffer constant = new StringBuffer();

        // keep track if we are still parsing headers
        boolean keepGoing = true;

        while (keepGoing) {
            // add line to header
            constant.append(strLine).append(System.getProperty("line.separator"));

            try {
                // set return position in case its not a header or comment
                br.mark(100);

                // read in line
                strLine = br.readLine();
            } catch (Exception e) {
                System.out.println(e.toString());
            }

            // take care of possible comments
            if (isLineComment(strLine)) {
                continue;
            } // take care of possible block comments
            else if (isLineBlockCommentStart(strLine)) {
                while (!isLineBlockCommentEnd(strLine)) {
                    try {
                        strLine = br.readLine();
                    } catch (Exception e) {
                    }

                    constant.append(strLine).append(System.getProperty("line.separator"));
                }
                continue;
            } else if (strLine.startsWith("const")) {
                continue;
            }

            // reset buffered reader so we try to parse this line again after this function returns
            try {
                br.reset();
            } catch (Exception e) {
                System.out.println(e.toString());
            }

            // stop reading in lines
            keepGoing = false;
        }

        // put header lines/comments into buffer
        replaceUserConstant.append(constant);
    }

    /*
     * @param br
     * @param strLine
     * @param replaceUserConstructor
     */
    private void parseConstructor(BufferedReader br, String strLine, StringBuffer replaceConstructor) {
        StringBuffer constructor = new StringBuffer();

        // keep track of in and out brackets, each must have a match
        int bracketCount = 0;

        do {
            try {
                strLine = br.readLine();
            } catch (Exception e) {
            }

            constructor.append(strLine).append(System.getProperty("line.separator"));

            // take care of possible comments
            if (isLineComment(strLine)) {
                continue;
            }

            // take care of possible block comments
            if (isLineBlockCommentStart(strLine)) {
                while (!isLineBlockCommentEnd(strLine)) {
                    try {
                        strLine = br.readLine();
                    } catch (Exception e) {
                    }

                    constructor.append(strLine).append(System.getProperty("line.separator"));
                }
            }

            if (strLine.contains("{")) {
                bracketCount++;
            }

            if (strLine.contains("}")) {
                bracketCount--;
            }
        } while (bracketCount != 0);

        // put function in buffer
        replaceConstructor.append(constructor);
    }

    /*
     * @param br
     * @param functionLine
     * @param hash
     */
    private void parseGenericClassFunction(BufferedReader br, String functionLine, Hashtable<String, StringBuffer> hash) {
        String functionName = new String();
        StringBuffer function = new StringBuffer();
        String strLine = new String();

        // function name will be string after "::"
        functionName = functionLine.substring(functionLine.indexOf("::") + 2);

        // keep track of in and out brackets, each must have a match
        int bracketCount = 0;

        do {
            try {
                strLine = br.readLine();
            } catch (Exception e) {
            }

            function.append(strLine).append(System.getProperty("line.separator"));

            // take care of possible comments
            if (isLineComment(strLine)) {
                continue;
            }

            // take care of possible block comments
            if (isLineBlockCommentStart(strLine)) {
                while (!isLineBlockCommentEnd(strLine)) {
                    try {
                        strLine = br.readLine();
                    } catch (Exception e) {
                    }

                    function.append(strLine).append(System.getProperty("line.separator"));
                }
            }

            if (strLine.contains("{")) {
                bracketCount++;
            }

            if (strLine.contains("}")) {
                bracketCount--;
            }
        } while (bracketCount != 0);

        // put function name and declaration in hashtable
        hash.put(functionName, function);
    }

    /*
     * @param br
     * @param functionLine
     * @param replaceGuardFunction
     */
    private void parseGuardFunction(BufferedReader br, String functionLine, Hashtable<String, StringBuffer> replaceGuardFunction) {
        parseGenericClassFunction(br, functionLine, replaceGuardFunction);
    }

    /*
     * @param br
     * @param functionLine
     * @param replaceActionFunction
     */
    private void parseActionFunction(BufferedReader br, String functionLine, Hashtable<String, StringBuffer> replaceActionFunction) {
        parseGenericClassFunction(br, functionLine, replaceActionFunction);
    }

    /*
     * @param br
     * @param variableLine
     * @param replaceUserFunction
     */
    private void parseStaticVariable(BufferedReader br, String variableLine, StringBuffer replaceUserFunction) {
        StringBuffer function = new StringBuffer();

        function.append(variableLine).append(System.getProperty("line.separator"));

        // put function name and declaration in hashtable
        replaceUserFunction.append(function);
    }

    /*
     * @param br
     * @param functionLine
     * @param replaceUserFunction
     */
    private void parseClass(BufferedReader br, String functionLine, StringBuffer replaceUserFunction) {
        StringBuffer function = new StringBuffer();
        String strLine = new String();

        function.append(functionLine).append(System.getProperty("line.separator"));

        // keep track of in and out brackets, each must have a match
        int bracketCount = 0;

        do {
            try {
                strLine = br.readLine();
            } catch (Exception e) {
            }

            function.append(strLine).append(System.getProperty("line.separator"));

            // take care of possible comments
            if (isLineComment(strLine)) {
                continue;
            }

            // take care of possible block comments
            if (isLineBlockCommentStart(strLine)) {
                while (!isLineBlockCommentEnd(strLine)) {
                    try {
                        strLine = br.readLine();
                    } catch (Exception e) {
                    }

                    function.append(strLine).append(System.getProperty("line.separator"));
                }
            }

            if (strLine.contains("{")) {
                bracketCount++;
            }

            if (strLine.contains("}")) {
                bracketCount--;
            }
        } while (bracketCount != 0);

        // put function in buffer
        replaceUserFunction.append(function);
    }

    /*
     * @param br
     * @param functionLine
     * @param replaceUserFunction
     */
    private void parseStaticFunction(BufferedReader br, String functionLine, StringBuffer replaceUserFunction) {
        StringBuffer function = new StringBuffer();
        String strLine = new String();

        function.append(functionLine).append(System.getProperty("line.separator"));

        // keep track of in and out brackets, each must have a match
        int bracketCount = 0;

        do {
            try {
                strLine = br.readLine();
            } catch (Exception e) {
            }

            function.append(strLine).append(System.getProperty("line.separator"));

            // take care of possible comments
            if (isLineComment(strLine)) {
                continue;
            }

            // take care of possible block comments
            if (isLineBlockCommentStart(strLine)) {
                while (!isLineBlockCommentEnd(strLine)) {
                    try {
                        strLine = br.readLine();
                    } catch (Exception e) {
                    }

                    function.append(strLine).append(System.getProperty("line.separator"));
                }
            }

            if (strLine.contains("{")) {
                bracketCount++;
            }

            if (strLine.contains("}")) {
                bracketCount--;
            }
        } while (bracketCount != 0);

        // put function in buffer
        replaceUserFunction.append(function);
    }

    /*
     * @param line
     */
    private boolean isLineBlockCommentStart(String line) {
        // test for start of block comment
        if (line.contains("/*")) {
            return true;
        }

        return false;
    }

    /*
     * @param line
     */
    private boolean isLineBlockCommentEnd(String line) {
        // test for end of block comment
        if (line.contains("*/")) {
            return true;
        }

        return false;
    }

    /*
     * @param line
     */
    private boolean isLineComment(String line) {
        String temp = line;

        temp.replace(" ", "");
        if (temp.startsWith("//")) {
            return true;
        }

        return false;
    }

    /*
     * @param flattenedStates
     * @param actionOrSendActionList
     */
    private void addActionListTreeSet(List<org.jts.jsidl.binding.State> flattenedStates, TreeSet<String> actionOrSendActionList) {
        // look through all states and get the actions and guards
        for (Iterator<org.jts.jsidl.binding.State> it = flattenedStates.iterator(); it.hasNext();) {
            org.jts.jsidl.binding.State currentState = it.next();

            // look through all entry actions
            if (currentState.getEntry() != null) {
                org.jts.jsidl.binding.Entry entry = currentState.getEntry();

                for (Object action : entry.getActionOrSendAction()) {
                    String toAdd = org.jts.codegenerator.protocolBehavior.Action.addActionOrSendActionMethod(action, null, m_codeType);
                    // skip null spacers
                    if (toAdd != null) {
                        actionOrSendActionList.add(toAdd);
                    }
                }
            }

            // look through all exit actions
            if (currentState.getExit() != null) {
                org.jts.jsidl.binding.Exit exit = currentState.getExit();

                for (Object action : exit.getActionOrSendAction()) {
                    String toAdd = org.jts.codegenerator.protocolBehavior.Action.addActionOrSendActionMethod(action, null, m_codeType);

                    // skip null spacers
                    if (toAdd != null) {
                        actionOrSendActionList.add(toAdd);
                    }
                }
            }

            // look through all transitions
            for (Iterator<org.jts.jsidl.binding.Transition> itTransition = currentState.getTransition().iterator(); itTransition.hasNext();) {
                org.jts.jsidl.binding.Transition currentTransition = itTransition.next();

                // add actions for transition to list of all actions throughout all flattened states
                for (int i = 0; i < currentTransition.getActionOrSendAction().size(); i++) {
                    Object action = currentTransition.getActionOrSendAction().get(i);
                    String toAdd = org.jts.codegenerator.protocolBehavior.Action.addActionOrSendActionMethod(action, currentTransition.getParameter(), m_codeType);

                    // skip null spacers
                    if (toAdd != null) {
                        actionOrSendActionList.add(toAdd);
                    }

                    // skip wrapper function definition
                    if (toAdd == null && i == 0) {
                        i++;
                        i++;
                        i++;
                    }
                }
            }

            // look through all default transitions
            for (Iterator<org.jts.jsidl.binding.DefaultTransition> itDefaultTransition = currentState.getDefaultTransition().iterator(); itDefaultTransition.hasNext();) {
                org.jts.jsidl.binding.DefaultTransition currentDefaultTransition = itDefaultTransition.next();

                // add actions for transition to list of all actions throughout all flattened states
                for (int i = 0; i < currentDefaultTransition.getActionOrSendAction().size(); i++) {
                    Object action = currentDefaultTransition.getActionOrSendAction().get(i);
                    // default transitions don't have parameters so no parameter list is sent to addActionOrSendActionMethod function
                    String toAdd = org.jts.codegenerator.protocolBehavior.Action.addActionOrSendActionMethod(action, null, m_codeType);

                    // skip null spacers
                    if (toAdd != null) {
                        actionOrSendActionList.add(toAdd);
                    }

                    // skip wrapper function definition
                    if (toAdd == null && i == 0) {
                        i++;
                        i++;
                        i++;
                    }
                }
            }
        }
    }

    /*
     * @param flattenedStates
     * @param guardList
     */
    private void addGuardListTreeSet(List<org.jts.jsidl.binding.State> flattenedStates, TreeSet<String> guardList) {
        // look through all states and get the actions and guards
        for (Iterator<org.jts.jsidl.binding.State> it = flattenedStates.iterator(); it.hasNext();) {
            org.jts.jsidl.binding.State currentState = it.next();

            // look through all transitions
            for (Iterator<org.jts.jsidl.binding.Transition> itTransition = currentState.getTransition().iterator(); itTransition.hasNext();) {
                org.jts.jsidl.binding.Transition currentTransition = itTransition.next();

                guardList.addAll(org.jts.codegenerator.protocolBehavior.Guard.addGuardMethodFromTransition(currentTransition, m_codeType));
            }

            // look through all default transitions
            for (Iterator<org.jts.jsidl.binding.DefaultTransition> itDefaultTransition = currentState.getDefaultTransition().iterator(); itDefaultTransition.hasNext();) {
                org.jts.jsidl.binding.DefaultTransition currentDefaultTransition = itDefaultTransition.next();

                guardList.addAll(org.jts.codegenerator.protocolBehavior.Guard.addGuardMethodFromTransition(currentDefaultTransition, m_codeType));
            }
        }
    }

    /*
     * @param virtualActionMethods
     * @param actionDeclarations
     * @param actionDefinitions
     * @param flattenedStates
     * @param smName
     */
    private void createPopExitTransitionWrappers(StringBuffer virtualActionMethods, StringBuffer actionDeclarations, StringBuffer actionDefinitions, List<org.jts.jsidl.binding.State> flattenedStates, String smName) {
        // look through all states and get the actions and guards
        for (Iterator<org.jts.jsidl.binding.State> it = flattenedStates.iterator(); it.hasNext();) {
            org.jts.jsidl.binding.State currentState = it.next();

            // look through all transitions
            for (Iterator<org.jts.jsidl.binding.Transition> itTransition = currentState.getTransition().iterator(); itTransition.hasNext();) {
                org.jts.jsidl.binding.Transition currentTransition = itTransition.next();

                // add actions for transition to list of all actions throughout all flattened states
                for (int i = 0; i < currentTransition.getActionOrSendAction().size(); i++) {
                    Object action = currentTransition.getActionOrSendAction().get(i);
                    String toAdd = org.jts.codegenerator.protocolBehavior.Action.addActionOrSendActionMethod(action, currentTransition.getParameter(), m_codeType);

                    // skip wrapper function definition
                    if (toAdd == null && i == 0) {
                        addExitPopTransitionFunctions(virtualActionMethods, actionDeclarations, actionDefinitions, currentTransition, smName);
                    } else {
                        break;
                    }
                }
            }

            // look through all default transitions
            for (Iterator<org.jts.jsidl.binding.DefaultTransition> itDefaultTransition = currentState.getDefaultTransition().iterator(); itDefaultTransition.hasNext();) {
                org.jts.jsidl.binding.DefaultTransition currentDefaultTransition = itDefaultTransition.next();

                // add actions for transition to list of all actions throughout all flattened states
                for (int i = 0; i < currentDefaultTransition.getActionOrSendAction().size(); i++) {
                    Object action = currentDefaultTransition.getActionOrSendAction().get(i);
                    // default transitions don't have parameters so no parameter list is sent to addActionOrSendActionMethod function
                    String toAdd = org.jts.codegenerator.protocolBehavior.Action.addActionOrSendActionMethod(action, null, m_codeType);

                    // skip wrapper function definition
                    if (toAdd == null && i == 0) {
                        //addExitPopDefaultTransitionFunctions(virtualActionMethods, actionDeclarations, actionDefinitions, currentDefaultTransition, smName);
                    } else {
                        break;
                    }
                }
            }
        }
    }

    /*
     * @param virtualActionMethods
     * @param actionDeclarations
     * @param actionDefinitions
     * @param currentTransition
     * @param smName
     */
    private void addExitPopTransitionFunctions(StringBuffer virtualActionMethods, StringBuffer actionDeclarations, StringBuffer actionDefinitions, org.jts.jsidl.binding.Transition currentTransition, String smName) {
        List<Object> actionSendActionList = currentTransition.getActionOrSendAction();

        // skip null placeholder
        int i = 1;

        Object action = actionSendActionList.get(i);
        String wrapperName = org.jts.codegenerator.protocolBehavior.Action.addActionOrSendActionMethod(action, currentTransition.getParameter(), m_codeType);
        i++;

        org.jts.jsidl.binding.Action action1 = (org.jts.jsidl.binding.Action) actionSendActionList.get(i);
        String leafStateName = flattenString(action1.getName());
        i++;

        org.jts.jsidl.binding.Action action2 = (org.jts.jsidl.binding.Action) actionSendActionList.get(i);
        String transitionDefinitionState = action2.getName();
        i++;

        if (m_codeType == CodeLines.CodeType.C_PLUS_PLUS) {
            virtualActionMethods.append("\tvirtual void " + wrapperName + "=0;");
            virtualActionMethods.append(System.getProperty("line.separator"));

            actionDeclarations.append("\tvirtual void " + wrapperName + ";");
            actionDeclarations.append(System.getProperty("line.separator"));

            actionDefinitions.append("void " + smName + "::" + wrapperName).append(System.getProperty("line.separator"));
            actionDefinitions.append("{").append(System.getProperty("line.separator"));

            actionDefinitions.append("\tstd::string tempstr(\"" + leafStateName + "\");").append(System.getProperty("line.separator"));
            actionDefinitions.append("\tstd::string tempstr2(context->peakTopStateStack());").append(System.getProperty("line.separator"));
            actionDefinitions.append("\tchar *leafStateTOK = strtok(const_cast<char*>(tempstr.c_str()),\"_\");").append(System.getProperty("line.separator"));
            actionDefinitions.append("\tchar *stackStateTOK = strtok(const_cast<char*>(tempstr2.c_str()),\"_\");").append(System.getProperty("line.separator"));
            actionDefinitions.append(System.getProperty("line.separator"));


            // handle loopback case
            actionDefinitions.append("\tif(strcmp(const_cast<char*>(tempstr2.c_str()),\"" + leafStateName + "\") == 0)").append(System.getProperty("line.separator"));
            actionDefinitions.append("\t{").append(System.getProperty("line.separator"));
        } else if (m_codeType == CodeLines.CodeType.JAVA) {
            virtualActionMethods.append("\tvoid " + wrapperName + ";");
            virtualActionMethods.append(System.getProperty("line.separator"));

            actionDeclarations.append("\t void " + wrapperName + ";");
            actionDeclarations.append(System.getProperty("line.separator"));

            actionDefinitions.append("void " + wrapperName).append(System.getProperty("line.separator"));
            actionDefinitions.append("{").append(System.getProperty("line.separator"));

            actionDefinitions.append("\tString tempstr =\"" + leafStateName + "\";").append(System.getProperty("line.separator"));
            actionDefinitions.append("\tString tempstr2 = context.peakTopStateStack();").append(System.getProperty("line.separator"));
            actionDefinitions.append("\tStringTokenizer leafStateTOK = new StringTokenizer(tempstr,\"_\");").append(System.getProperty("line.separator"));
            actionDefinitions.append("\tStringTokenizer stackStateTOK = new StringTokenizer(tempstr2,\"_\");").append(System.getProperty("line.separator"));
            actionDefinitions.append("\tString currentLeafStateTOK;").append(System.getProperty("line.separator"));
            actionDefinitions.append("\tString currentStackStateTOK;").append(System.getProperty("line.separator"));
            actionDefinitions.append(System.getProperty("line.separator"));


            // handle loopback case
            actionDefinitions.append("\tif(tempstr2.compareTo(\"" + leafStateName + "\") == 0)").append(System.getProperty("line.separator"));
            actionDefinitions.append("\t{").append(System.getProperty("line.separator"));
        } else if (m_codeType == CodeLines.CodeType.C_SHARP) {
            virtualActionMethods.append("\tvoid " + wrapperName + ";");
            virtualActionMethods.append(System.getProperty("line.separator"));

            actionDeclarations.append("\tvoid " + wrapperName + ";");
            actionDeclarations.append(System.getProperty("line.separator"));

            actionDefinitions.append("public void " + wrapperName).append(System.getProperty("line.separator"));
            actionDefinitions.append("{").append(System.getProperty("line.separator"));

            actionDefinitions.append("\tstring tempstr = \"" + leafStateName + "\";").append(System.getProperty("line.separator"));
            actionDefinitions.append("\tstring tempstr2 = context.peakTopStateStack();").append(System.getProperty("line.separator"));
            actionDefinitions.append("\tstring[] leafStateTOK = tempstr.Split(new char[] {\'_\'});").append(System.getProperty("line.separator"));
            actionDefinitions.append("\tstring[] stackStateTOK = tempstr2.Split(new char[] {\'_\'});").append(System.getProperty("line.separator"));
            actionDefinitions.append("\tint currentToken = 0;").append(System.getProperty("line.separator"));
            actionDefinitions.append(System.getProperty("line.separator"));


            // handle loopback case
            actionDefinitions.append("\tif(tempstr2.CompareTo(\"" + leafStateName + "\") == 0)").append(System.getProperty("line.separator"));
            actionDefinitions.append("\t{").append(System.getProperty("line.separator"));
        }

        int startIndex = i;

        for (int j = startIndex; j < actionSendActionList.size(); j++) {
            Object obj = actionSendActionList.get(j);

            if (obj instanceof org.jts.jsidl.binding.Action) {
                org.jts.jsidl.binding.Action act = (org.jts.jsidl.binding.Action) obj;
                StringBuffer def = org.jts.codegenerator.protocolBehavior.Action.addActionOrSendAction(act);
                actionDefinitions.append("\t\t" + def.toString() + "").append(System.getProperty("line.separator"));
            }

            if (obj instanceof org.jts.jsidl.binding.SendAction) {
                org.jts.jsidl.binding.SendAction act = (org.jts.jsidl.binding.SendAction) obj;
                StringBuffer def = org.jts.codegenerator.protocolBehavior.Action.addActionOrSendAction(act);
                actionDefinitions.append("\t\t" + def.toString() + "").append(System.getProperty("line.separator"));
            }
        }
        actionDefinitions.append("\t\treturn;").append(System.getProperty("line.separator"));
        actionDefinitions.append("\t}").append(System.getProperty("line.separator"));

        actionDefinitions.append(System.getProperty("line.separator"));

        // make all case statements
        StringTokenizer st = new StringTokenizer(leafStateName, "_");
        while (st.hasMoreTokens()) {
            String state = st.nextToken();

            if (m_codeType == CodeLines.CodeType.C_PLUS_PLUS) {
                actionDefinitions.append("\tif(strcmp(leafStateTOK,stackStateTOK) != 0)").append(System.getProperty("line.separator"));
            } else if (m_codeType == CodeLines.CodeType.JAVA) {
                actionDefinitions.append("\tcurrentLeafStateTOK = leafStateTOK.nextToken();").append(System.getProperty("line.separator"));
                actionDefinitions.append("\tcurrentStackStateTOK = stackStateTOK.nextToken();").append(System.getProperty("line.separator"));
                actionDefinitions.append("\tif(currentLeafStateTOK.compareTo(currentStackStateTOK) != 0)").append(System.getProperty("line.separator"));

            } else if (m_codeType == CodeLines.CodeType.C_SHARP) {
                actionDefinitions.append("\tif(leafStateTOK[currentToken].CompareTo(stackStateTOK[currentToken]) != 0)").append(System.getProperty("line.separator"));
            }
            actionDefinitions.append("\t{").append(System.getProperty("line.separator"));

            for (int j = startIndex; j < actionSendActionList.size(); j++) {
                Object obj = actionSendActionList.get(j);

                if (obj instanceof org.jts.jsidl.binding.Action) {
                    org.jts.jsidl.binding.Action act = (org.jts.jsidl.binding.Action) obj;
                    StringBuffer def = org.jts.codegenerator.protocolBehavior.Action.addActionOrSendAction(act);
                    actionDefinitions.append("\t\t" + def.toString() + "").append(System.getProperty("line.separator"));
                }

                if (obj instanceof org.jts.jsidl.binding.SendAction) {
                    org.jts.jsidl.binding.SendAction act = (org.jts.jsidl.binding.SendAction) obj;
                    StringBuffer def = org.jts.codegenerator.protocolBehavior.Action.addActionOrSendAction(act);
                    actionDefinitions.append("\t\t" + def.toString() + "").append(System.getProperty("line.separator"));
                }
            }
            actionDefinitions.append("\t\treturn;").append(System.getProperty("line.separator"));
            actionDefinitions.append("\t}").append(System.getProperty("line.separator"));

            if (m_codeType == CodeLines.CodeType.C_PLUS_PLUS) {
                actionDefinitions.append("\tleafStateTOK = strtok(leafStateTOK+1,\"_\");").append(System.getProperty("line.separator"));
                actionDefinitions.append("\tstackStateTOK = strtok(stackStateTOK+1,\"_\");").append(System.getProperty("line.separator"));
            } else if (m_codeType == CodeLines.CodeType.JAVA) {
                actionDefinitions.append("\tcurrentLeafStateTOK = leafStateTOK.nextToken();").append(System.getProperty("line.separator"));
                actionDefinitions.append("\tcurrentStackStateTOK = stackStateTOK.nextToken();").append(System.getProperty("line.separator"));

            } else if (m_codeType == CodeLines.CodeType.C_SHARP) {
                actionDefinitions.append("\tcurrentToken++;").append(System.getProperty("line.separator"));
                actionDefinitions.append("\tif(currentToken >= stackStateTOK.Length || currentToken >= leafStateTOK.Length)\n\t\t{").append(System.getProperty("line.separator"));
                actionDefinitions.append("\t\tConsole.WriteLine(\"Problem with StringTokenizer.\");").append(System.getProperty("line.separator"));
                actionDefinitions.append("\t}").append(System.getProperty("line.separator"));
            }
            actionDefinitions.append(System.getProperty("line.separator"));

            startIndex = readExitActionsFromList(actionSendActionList, startIndex);
        }

        actionDefinitions.append("}").append(System.getProperty("line.separator"));
        actionDefinitions.append(System.getProperty("line.separator"));
    }

    /*
     * @param actionSendActionList
     * @param startIndex
     */
    private int readExitActionsFromList(List<Object> actionSendActionList, int startIndex) {
        int returnIndex = startIndex;

        if (returnIndex == actionSendActionList.size()) {
            return returnIndex;
        }

        Object obj = actionSendActionList.get(returnIndex);
        while (obj instanceof org.jts.jsidl.binding.Action || obj instanceof org.jts.jsidl.binding.SendAction && returnIndex != actionSendActionList.size() - 1) {
            returnIndex++;
            obj = actionSendActionList.get(returnIndex);
        }

        return returnIndex + 1;
    }


     // TEST CODE 
     private void generateNotifications(StateMachine sm, ServiceDef sd, ServiceSet ss, StringBuffer setupNotifications)
     {
        try 
        {
			// Get the parent and parent FSM.  If none exists, there is nothing to do.
			ServiceDef parent = org.jts.codegenerator.support.InheritanceHelper.findServiceDef(
												   ss, sd.getReferences().getInheritsFrom().getId(),
												   sd.getReferences().getInheritsFrom().getVersion());
			StateMachine parentFSM = org.jts.codegenerator.support.InheritanceHelper.findParentFSM( ss, sd, sm );
			if ((parent == null) || (parentFSM == null)) return;
					
			// Get a list of unique states in the parent FSM.  If there is only one,
			// there is no need for notification events.
			Vector<Reference> stateList = new Vector<Reference>();
			org.jts.codegenerator.support.InheritanceHelper.getFullStateList( parentFSM, ss, parent, stateList);
			
			// For each UNIQUE state in the parent, add an entry action that forces the child FSM
			// into the correct starting state.
			for (Reference ref : stateList)
			{
			    if (ref.name.equals("Internally_Generated_State_DO_NOT_USE")) continue;
			    
			    State endstate = org.jts.codegenerator.support.InheritanceHelper.findStateFromName(ref.name, sm);
			    if (endstate != null)
			    {
					String trigger = "InternalStateChange_To_" + sm.getName() + "_" +
					       org.jts.codegenerator.support.InheritanceHelper.findFullInitialStateName( endstate, sm ).replace(".", "_");
					if (m_codeType == CodeLines.CodeType.C_PLUS_PLUS)
						setupNotifications.append("\tp" + parentFSM.getName() + "->registerNotification(\"" + endstate.getName().replace(".", "_") + "\", ieHandler, \"" + trigger + "\", \"" + parentFSM.getName() + "\");");
					else
						setupNotifications.append("\t\tp" + parentFSM.getName() + ".registerNotification(\"" + endstate.getName().replace(".", "_") + "\", ieHandler, \"" + trigger + "\", \"" + parentFSM.getName() + "\");");
					setupNotifications.append(System.getProperty("line.separator"));
				}
				else System.out.println("FAILED TO FIND ENDSTATE " + ref.name);
			}
		}
		catch (Exception e){}
		
		try 
        {
			// Get a list of unique states in this FSM.  If there is only one,
			// there is no need for notification events.
			Vector<Reference> stateList = new Vector<Reference>();
			org.jts.codegenerator.support.InheritanceHelper.getFullStateList( sm, ss, sd, stateList);
			
			// For each UNIQUE state in the FSM, add an entry action that forces the parent FSM
			// into the correct starting state.
			for (Reference ref : stateList)
			{
			    if (ref.name.equals("Internally_Generated_State_DO_NOT_USE")) continue;
			    
				State endstate = org.jts.codegenerator.support.InheritanceHelper.findStateFromName(ref.name, sm);
				StateMachine parentFSM = org.jts.codegenerator.support.InheritanceHelper.findParentFSM(ss, sd, sm);
				State parentState = org.jts.codegenerator.support.InheritanceHelper.findParentState( endstate, parentFSM );
				
			    if ((endstate != null) && (parentFSM != null) && (parentState != null))
			    {
					String trigger = "InternalStateChange_To_" + parentFSM.getName() + "_" + parentState.getName().replace(".", "_");
					if (m_codeType == CodeLines.CodeType.C_PLUS_PLUS)
						setupNotifications.append("\tregisterNotification(\"" + endstate.getName().replace(".", "_") + "\", p" + parentFSM.getName() + "->getHandler(), \"" + trigger + "\", \"" + sm.getName() + "\");");
					else
						setupNotifications.append("\t\tregisterNotification(\"" + endstate.getName().replace(".", "_") + "\", p" + parentFSM.getName() + ".getHandler(), \"" + trigger + "\", \"" + sm.getName() + "\");");
					setupNotifications.append(System.getProperty("line.separator"));
				}
			}
		}
		catch (Exception e){}

        }
     
     private void addInternalEvents( StateMachine sm, ServiceDef sDef, ServiceSet sSet )
	{
	    // Get the unique set of states defined for this protocol
		Vector<Reference> stateList = new Vector<Reference>();
		org.jts.codegenerator.support.InheritanceHelper.getFullStateList( sm, sSet, sDef, stateList);
				
		// Generate each possible state transition
		for (Reference from : stateList)
		{
			if (from.name.equals("Internally_Generated_State_DO_NOT_USE")) continue;
					
			for (Reference to : stateList)
			{
				if (to.name.equals("Internally_Generated_State_DO_NOT_USE")) continue;
				        
				if (!from.equals(to))
				{
				    // Define the internal event trigger
				    String ieName = "InternalStateChange_To_" +  
				                org.jts.codegenerator.support.InheritanceHelper.shortenStateMachineName(sDef, sm) +
				                "_" + to.name + "Transition";
				    ieName = ieName.replace(".", "_");
				                     
				    // Get a reference for the 'from' state
				    State fromState = org.jts.codegenerator.support.InheritanceHelper.findStateFromName(from.name, sm);
				    State toState = org.jts.codegenerator.support.InheritanceHelper.findStateFromName(to.name, sm);
				    String startState = org.jts.codegenerator.support.InheritanceHelper.findFullInitialStateName( fromState, sm );
				    String endState = org.jts.codegenerator.support.InheritanceHelper.findFullInitialStateName( toState, sm );
				            
				    if ((fromState != null) && !startState.equals(endState) && !from.name.equals(endState))
				    {
				        // Create a simple transition with the internal event as the trigger
				        EndState end = new EndState();
				        end.setState( to.name );
				        Simple simple = new Simple();
				        simple.setEndState( end );
				        Parameter parameter = new Parameter();
						if (m_codeType == CodeLines.CodeType.C_PLUS_PLUS) parameter.setType("JTS::InternalEvent*");
						else parameter.setType("InternalEvent");
				        parameter.setValue("ie");
				        Transition transition = new Transition();
				        transition.setName( ieName );
				        transition.setSimple( simple );
				        transition.getParameter().add( parameter );
				                
				        // Add the transition to the fromState
				        fromState.getTransition().add( transition );
					}
				}
			}
		}	        
	}
}
