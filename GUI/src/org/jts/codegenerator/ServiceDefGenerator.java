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

package org.jts.codegenerator;

import org.jts.gui.util.Pair;
import org.jts.jsidl.binding.*;
import org.jts.codegenerator.support.*;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Nicholas M. Johnson
 *
 */
public class ServiceDefGenerator
{
	private CodeLines.CodeType codeType;
	private ServiceDef sDef;
	private ServiceSet sSet;
	private String namespace;
	private String serviceName;
    private String transportVersion;
	private Hashtable<String, String> replaceTable;
    private static final int CROP_TPL_NAME = 4;  // This removes .tpl from the template file names.
    private static final int CROP_H_NAME = 2; // This removes .h from the header name.
	
	public ServiceDefGenerator(CodeLines.CodeType codeType, ServiceDef sDef, ServiceSet sSet)
	{
		this.codeType = codeType;
		this.sDef = sDef;
		this.sSet = sSet;
		
		/// Makes the namespace for the Generated Service
                if (codeType == CodeLines.CodeType.C_PLUS_PLUS){
                    namespace = CppCode.makeNamespace(sDef.getId(), sDef.getVersion());
                }
                else if (codeType == CodeLines.CodeType.JAVA){
                    namespace = JavaCode.makeNamespace(sDef.getId(), sDef.getVersion());
                }
                else if (codeType == CodeLines.CodeType.C_SHARP){
                    namespace = CSharpCode.makeNamespace(sDef.getId(), sDef.getVersion());
                }

		serviceName = Util.upperCaseFirstLetter(sDef.getName()) + "Service";
		
		this.replaceTable = new Hashtable<String, String>();

		replaceTable.put("%copyright%", "");
		replaceTable.put("%service_namespace%", namespace);
		replaceTable.put("%service_name%", serviceName);	
		replaceTable.put("%service_name_allcaps%", serviceName.toUpperCase());
	}

	/*
     * @param outDir
     * @param cmptName the name of the component
     */
	public void run(String outDir, String cmptName) throws CodeGeneratorException
	{

		if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
		{
                    runCPP(outDir);
		}
                else if (codeType == CodeLines.CodeType.JAVA)
		{
                    runJava(outDir, cmptName);
		}
                else if(codeType == CodeLines.CodeType.C_SHARP)
                {
                    runCSharp(outDir);
                }
	}


        /*
         * @param outDir
         * Generate the C++ code
         */
        public void runCPP(String outDir) throws CodeGeneratorException
        {
            // We want the Service to be within a subfolder within the outDir
            String includeDir = outDir + "/include/" + namespace;
            String srcDir = outDir + "/src/" + namespace;

            // Create the directories for this service
            File tempDir = new File(includeDir);
            if (!tempDir.exists())
            {

                if (!tempDir.mkdirs())
                {
                    throw new CodeGeneratorException("ServiceDefGenerator: Could not create Service src directory");
                }
            }

            tempDir = new File(srcDir);
            if(!tempDir.exists())
            {

                if (!tempDir.mkdirs())
                {
                    throw new CodeGeneratorException("ServiceDefGenerator: Could not create Service include directory");
                }
            }

            try
            {
                StringBuffer incBuf;
                String incName;
                File msgSet;
                StringBuffer strBuf;

                /*
                * Generate the constants file
                */
                ConstantsGenerator cGen = new ConstantsGenerator(codeType, sDef.getDeclaredConstSet());
                cGen.run(outDir);

                /*
                 * Generate the MessageSet
                 */
                MessageSetGenerator msGen = new MessageSetGenerator(codeType, sDef.getMessageSet());
                msGen.run(namespace, outDir);

                /// Move the files
                Vector<String> msgInputList = msGen.getInputMsgNameList();
                Vector<String> msgOutputList = msGen.getOutputMsgNameList();
                Vector<String> msgList = new Vector<String>();
                msgList.addAll(msgInputList);
                msgList.addAll(msgOutputList);
                incBuf = new StringBuffer();
                tempDir = new File(includeDir + "/Messages");
                tempDir.mkdir();
                for (File src : tempDir.listFiles())
                {
                    if (!src.isDirectory() && msgList.contains(src.getName().substring(0, src.getName().length() - 2)))
                    {
                        incBuf.append("#include \"" + src.getName() + "\"").append(System.getProperty("line.separator"));
                    }
                }


                /// Create the MessageSet.h file
                incName = "MessageSet";
                msgSet = new File(includeDir + "/Messages/" + incName + ".h");
                strBuf = new StringBuffer();
                strBuf.append("#ifndef " + namespace.toUpperCase() + "_" + incName.toUpperCase()).append(System.getProperty("line.separator"));
                strBuf.append("#define " + namespace.toUpperCase() + "_" + incName.toUpperCase()).append(System.getProperty("line.separator"));
                strBuf.append(System.getProperty("line.separator"));
                strBuf.append(incBuf);
                strBuf.append(System.getProperty("line.separator"));
                strBuf.append("#endif //" + namespace.toUpperCase() + "_" + incName.toUpperCase()).append(System.getProperty("line.separator"));
                Util.writeContents(msgSet, strBuf.toString());


                /*
                 * Generate the InternalEventSet
                 */
                InternalEventsSetGenerator iesGen = new InternalEventsSetGenerator(codeType, sDef, sSet);
                iesGen.run(namespace, outDir);

                /// Get a list of all the files
                Vector<String> ieList = iesGen.getInternalEventNameList();
                incBuf = new StringBuffer();
                tempDir = new File(includeDir + "/InternalEvents");
                tempDir.mkdir();
                for (File src : tempDir.listFiles())
                {
                    if (!src.isDirectory() && ieList.contains(src.getName().substring(0, src.getName().length() - 2)))
                    {
                        incBuf.append("#include \"" + src.getName() + "\"").append(System.getProperty("line.separator"));
                    }
                }

                /// Create the InternalEventsSet.h file
                incName = "InternalEventsSet";
                msgSet = new File(includeDir + "/InternalEvents/" + incName + ".h");
                strBuf = new StringBuffer();
                strBuf.append("#ifndef " + namespace.toUpperCase() + "_" + incName.toUpperCase()).append(System.getProperty("line.separator"));
                strBuf.append("#define " + namespace.toUpperCase() + "_" + incName.toUpperCase()).append(System.getProperty("line.separator"));
                strBuf.append(System.getProperty("line.separator"));
                strBuf.append(incBuf);
                strBuf.append(System.getProperty("line.separator"));
                strBuf.append("#endif //" + namespace.toUpperCase() + "_" + incName.toUpperCase()).append(System.getProperty("line.separator"));
                Util.writeContents(msgSet, strBuf.toString());

                /*
                 * Generate the ProtocolBehavior State Machines
                 */
                try
                {
                    org.jts.codegenerator.ProtocolBehaviorGenerator pbGen = new org.jts.codegenerator.ProtocolBehaviorGenerator(namespace, codeType, outDir, sDef, sSet, new StringBuffer());
                    
                    /*
                     * Generate the Service Files
                     */
                    StringBuffer smVariableList = new StringBuffer();
                    StringBuffer smIncludeList = new StringBuffer();
                    StringBuffer smAssignmentList = new StringBuffer();
                    StringBuffer smDestructorList = new StringBuffer();
                    StringBuffer svcMsgList = new StringBuffer();
                    StringBuffer smArguments = new StringBuffer();
					StringBuffer smParentList = new StringBuffer();
                    StringBuffer smFSMList = new StringBuffer();
                    StringBuffer smTransportType = new StringBuffer();

                    // Get the list of parent services
                    Vector<Reference> parentList = new Vector<Reference>();
                    org.jts.codegenerator.support.InheritanceHelper.getParentServiceList( 
                                       codeType, sSet, sDef, parentList);
                                       
                    // For each parent reference, add an include and constructor pointer
                    for (Reference ref : parentList)
                    {                   
						// Add this service to the constructor and include list
						smParentList.append(", " + ref.namespace + "::" + ref.name + "* p" + ref.name);
						smIncludeList.append("#include \"" + ref.namespace + "/" + ref.name + ".h\"");
						smIncludeList.append(System.getProperty("line.separator"));
					}

                    for (String smName : pbGen.getStateMachineNames())
                    {
                        // Get any parent FSMs and restructure into a parameter list
						Vector<Reference> fsmList = new Vector<Reference>();
						org.jts.codegenerator.support.InheritanceHelper.getParentFSMList( 
															codeType, smName, sSet, sDef, fsmList);
				        StringBuffer fsmParameters = new StringBuffer();
                        for (Reference ref : fsmList)
                        {
                            if (fsmParameters.length() != 0) fsmParameters.append(", ");
                            fsmParameters.append("p" + ref.owner + "->p" + ref.name);
                        }
															
				        // Each FSM is stored as a member variable for the service
                        smVariableList.append("\t" + smName + "* p" + smName + ";");
                        smVariableList.append(System.getProperty("line.separator"));
                        smIncludeList.append("#include \"" + smName + ".h\"");
                        smIncludeList.append(System.getProperty("line.separator"));
                        smDestructorList.append("\tdelete p" + smName + ";" );
                        smDestructorList.append(System.getProperty("line.separator"));
                        
                        // When constructing each FSM, we also need to pass a pointer to all parent FSMs
                        smAssignmentList.append("\tp" + smName + " = new " + smName + "(" + fsmParameters + ");");
                        smAssignmentList.append(System.getProperty("line.separator"));
                        smAssignmentList.append("\tp" + smName + "->setHandlers(ieHandler, jausRouter);");
                        smAssignmentList.append(System.getProperty("line.separator"));
                        smAssignmentList.append("\tp" + smName + "->setupNotifications();");
                        smAssignmentList.append(System.getProperty("line.separator"));
                    }

                    // Specify the transport type...
                    ServiceDef top = org.jts.codegenerator.support.InheritanceHelper.getTopParent( sDef, sSet );
                    if ( top.getId().equalsIgnoreCase("urn:jaus:jss:core:Transport") &&
                         top.getVersion().equals("1.1") )
                    {
                        smTransportType.append("JausRouter::Version_1_1");
                    }
                    else
                    {
                        smTransportType.append("JausRouter::Version_1_0");
                    }

                    // Set-up the template replacement hash table.
                    replaceTable.put("%statemachine_variable_list%", smVariableList.toString());
					replaceTable.put("%parent_service_list%", smParentList.toString());
                    replaceTable.put("%statemachine_args%", smArguments.toString());
                    replaceTable.put("%statemachine_include_list%", smIncludeList.toString());
                    replaceTable.put("%statemachine_destruction_list%", smDestructorList.toString());
                    replaceTable.put("%statemachine_assignment_list%", smAssignmentList.toString());
                    replaceTable.put("%start_state_actions%", pbGen.entryActionCalls.toString());
                    replaceTable.put("%transition_calls%", getTransitionCallsWithParameters(pbGen, srcDir));
					replaceTable.put("%default_transition_calls%", pbGen.defaultCalls.toString());
                    replaceTable.put("%service_transport_type%", smTransportType.toString());

                    svcMsgList.append("\t/// Input Messages").append(System.getProperty("line.separator"));;
                    for (String msg : msgInputList)
                    {
                        svcMsgList.append("\tm_InputMessageList.insert(" + msg + "::ID);");
                        svcMsgList.append(System.getProperty("line.separator"));
                    }
                    svcMsgList.append(System.getProperty("line.separator"));
                    svcMsgList.append("\t/// Output Messages").append(System.getProperty("line.separator"));;
                    for (String msg : msgOutputList)
                    {
                        svcMsgList.append("\tm_OutputMessageList.insert(" + msg + "::ID);");
                        svcMsgList.append(System.getProperty("line.separator"));
                    }
                    replaceTable.put("%service_message_list%", svcMsgList.toString());
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    throw new CodeGeneratorException(e.getMessage() + " Error with ProtocolBehaviorGenerator [SDG]");
                }


                /*
                 * Generate the code and template for the service
                 */
                TemplateHandler tplHandler = new TemplateHandler(replaceTable);
                // Handle Eclipse plugin's relative paths
                File templateDir;
                if(new File("plugins/org.jts.eclipse.data_1.0/templates").exists())
                {
                    templateDir = new File("plugins/org.jts.eclipse.data_1.0/templates/service");
                }else
                {
                    templateDir = new File("templates/service");
                }
                boolean validFile = false;

                for (File template : templateDir.listFiles(new FileExtensionFilter("tpl")))
                {
                    String destFileName = tplHandler.adjustString(template.getName().substring(0, template.getName().length() - CROP_TPL_NAME));
                    File dest = null;

                    if (destFileName.endsWith(".h"))
                    {
                        validFile = true;
                        dest = new File(includeDir + "/" + destFileName);
                        if (dest.exists())
                        {
                            dest.renameTo(new File(includeDir + "/" + destFileName + ".old"));
                            dest = new File(includeDir + "/" + destFileName);
                        }
                    }
                    else if (destFileName.endsWith(".cpp"))
                    {
                        validFile = true;
                        dest = new File(srcDir + "/" + destFileName);
                        if (dest.exists())
                        {
                            dest.renameTo(new File(srcDir + "/" + destFileName + ".old"));
                            dest = new File(srcDir + "/" + destFileName);
                        }
                    }
                    else if(destFileName.endsWith(".cs") || destFileName.endsWith(".java"))
                    {
                        // Do nothing. These files aren't needed.
                        validFile = false;
                    }
                    else
                    {
                        validFile = true;
                        dest = new File(outDir + "/" + namespace + "/" + destFileName);
                        if (dest.exists())
                        {
                            dest.renameTo(new File(outDir + "/" + namespace + "/" + destFileName + ".old"));
                            dest = new File(outDir + "/" + namespace + "/" + destFileName);
                        }
                    }

                    try
                    {
                        if(validFile)
                        {
                            Util.copyFile(template, dest);
                            tplHandler.adjustFile(dest);
                        }
                    }
                    catch (Exception e)
                    {
                        throw new CodeGeneratorException("Could not Copy File [SDG]");
                    }
                }

				SconstructGenerator sconGen = new SconstructGenerator();

                /// Get the list of parent services.... these are required libraries for linking
                Vector<String> libs = new Vector<String>();
				Vector<Reference> parentList = new Vector<Reference>();
				org.jts.codegenerator.support.InheritanceHelper.getParentServiceList( codeType, sSet, sDef, parentList );
				for (int i=parentList.size(); i > 0; i--)
					libs.add( parentList.get(i-1).namespace + "/lib/" + parentList.get(i-1).name );
				
                /// Generate the Sconstruct File
                Util.writeContents(new File(srcDir + "/Sconstruct"), sconGen.generateLibrary(new File(srcDir), Util.upperCaseFirstLetter(serviceName), libs));
            }
            catch (Exception e)
            {
                    throw new CodeGeneratorException(e.getMessage() + "Could not generate Sconstruct file for Service [SDG]");
            }
        }
        
        /**
         * Get the transition calls for the given pb but with the user
         * defined parameters initialized as they were in any previous service files
         * based on the comment guid before each parameter.
         * Looks in the sourceDir for the previous service file to look up the guids
         * @param pbGen
         * @param sourceDir
         * @return
         */
        private String getTransitionCallsWithParameters( org.jts.codegenerator.ProtocolBehaviorGenerator pbGen, String sourceDir )
        {
        	String newTransitionCalls = pbGen.transitionCalls.toString();
        	
        	try
        	{
        		File previousFile = new File( sourceDir + "/" + serviceName + ".cpp" );
        		
        		// may be the first time generating the service
        		if( !previousFile.exists() )
        		{
        			return newTransitionCalls;
        		}
        		
        		Pattern p = Pattern.compile( "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}" );
        		Matcher m = p.matcher( newTransitionCalls );

        		// store the strings found along with the start location so we don't have to look it up again
        		ArrayList< Pair< Integer, String> > guids = new ArrayList< Pair< Integer, String> >();
        		while( m.find() )
        		{
        			int start = m.start();
        			int end = m.end();

        			String guid = newTransitionCalls.substring( start, end );
        			Pair< Integer, String > pair = new Pair<Integer, String>( start, guid );
        			guids.add( pair );
        		}
        		
        		if( guids.size() > 0 )
        		{
        			String oldFile = "";
        			
        			BufferedReader data = new BufferedReader( new FileReader( previousFile ) );
        			
        			while( data.ready() )
        			{
        				oldFile += data.readLine() + System.getProperty("line.separator");
        			}
        			
	        		for( Pair< Integer, String > pair : guids )
	        		{
	        			String guid = pair.second;

	        			int startLocation = oldFile.indexOf( guid );
	        			
	        			// only try to replace when the new guid was found in the old file
	        			if( startLocation > 0 )
	        			{
	        				// replacement will be guid up to next semicolon
	        				int stopLocation = oldFile.indexOf( ";", startLocation );
	        				String fromOldFile = oldFile.substring( startLocation, stopLocation + 1 );
	        				
	        				// old string to find will be old guid start up to next semicolon
	        				int newGUIDStartLocation = pair.first;
	        				String newStart = newTransitionCalls.substring( 0, newGUIDStartLocation );
	        				
	        				int newGUIDStopLocation = newTransitionCalls.indexOf( ";", newGUIDStartLocation );
	        				String newEnd = newTransitionCalls.substring( newGUIDStopLocation + 1 );
	        				
	        				// replace the string from the new file with the one from the old file
	        				newTransitionCalls = newStart + fromOldFile + newEnd;
	        			}
	        		}
        		}
        	}
        	catch( Exception e )
        	{
        		throw new CodeGeneratorException(e.getMessage() + "Problem loading previous service file for parameter replacement");
        	}
        	
        	return newTransitionCalls;
        }

        /*
         * @param outDir
         * Generate the Java code
         */
        public void runJava(String outDir, String cmptName) throws CodeGeneratorException
        {
            // We want the Service to be within a subfolder within the outDir
            String srcDir = outDir + "/src/" + namespace;

            // Create the directories for this service
            File tempDir = new File(srcDir);
            if(!tempDir.exists())
            {

                    if (!tempDir.mkdirs())
                    {
                            throw new CodeGeneratorException("ServiceDefGenerator: Could not create Service include directory");
                    }
            }

            try
            {
                    /*
                    * Generate the constants file
                    */
                    ConstantsGenerator cGen = new ConstantsGenerator(codeType, sDef.getDeclaredConstSet());
                    cGen.run(outDir);

                    /*
                     * Generate the MessageSet
                     */

                    MessageSetGenerator msGen = new MessageSetGenerator(codeType, sDef.getMessageSet());
                    msGen.run(namespace, outDir);

                    /// Move the files
                    Vector<String> msgInputList = msGen.getInputMsgNameList();
                    Vector<String> msgOutputList = msGen.getOutputMsgNameList();
                    Vector<String> msgList = new Vector<String>();
                    msgList.addAll(msgInputList);
                    msgList.addAll(msgOutputList);

                    /*
                     * Generate the InternalEvent's
                     *
                     */

                    InternalEventsSetGenerator iesGen = new InternalEventsSetGenerator(codeType, sDef, sSet);
                    iesGen.run(namespace, outDir);

                    /*
                     * Generate the ProtocolBehavior State Machines
                     */
                    try
                    {
                            org.jts.codegenerator.ProtocolBehaviorGenerator pbGen = new org.jts.codegenerator.ProtocolBehaviorGenerator(namespace, codeType, outDir, sDef, sSet, new StringBuffer());
                            transportVersion = pbGen.getTransportVersion();

                            /*
                             * Generate the Service Files
                             */
                            StringBuffer smVariableList = new StringBuffer();
                            StringBuffer smIncludeList = new StringBuffer();
                            StringBuffer smAssignmentList = new StringBuffer();
                            StringBuffer svcMsgList = new StringBuffer();
							StringBuffer smParentList = new StringBuffer();
                            StringBuffer smArguments = new StringBuffer();

							// Get the list of parent services
							Vector<Reference> parentList = new Vector<Reference>();
							org.jts.codegenerator.support.InheritanceHelper.getParentServiceList( 
											   codeType, sSet, sDef, parentList);
                                       
							// For each parent reference, add an include and constructor pointer
							for (Reference ref : parentList)
							{                   
								// Add this service to the constructor and include list
								smParentList.append(", " + ref.name + " p" + ref.name);
								smIncludeList.append("import src." + ref.namespace + "." + ref.name + ";");
								smIncludeList.append(System.getProperty("line.separator"));
							}

                            for (String smName : pbGen.getStateMachineNames())
                            {
                            
									// Get any parent FSMs and restructure into a parameter list
									Vector<Reference> fsmList = new Vector<Reference>();
									org.jts.codegenerator.support.InheritanceHelper.getParentFSMList( 
																		codeType, smName, sSet, sDef, fsmList);
									StringBuffer fsmParameters = new StringBuffer();
									for (Reference ref : fsmList)
									{
										if (fsmParameters.length() != 0) fsmParameters.append(", ");
										fsmParameters.append("p" + ref.owner + ".p" + ref.name);
									}
									                            
                                    smVariableList.append("\tpublic " + smName + " p" + smName + ";");
                                    smVariableList.append(System.getProperty("line.separator"));

                                    if (smArguments.length() != 0)
                                       smArguments.append(",").append(System.getProperty("line.separator")).append("\t\t\t\t");
                                    smArguments.append(smName + " p" + smName);

                                    smAssignmentList.append("\tp" + smName + " = new " + smName + "(" + fsmParameters + ");");
                                    smAssignmentList.append(System.getProperty("line.separator"));
                                    smAssignmentList.append("\tp" + smName + ".setHandler(ieHandler, jausRouter);");
                                    smAssignmentList.append(System.getProperty("line.separator"));
                                    smAssignmentList.append("\tp" + smName + ".setupNotifications();");
									smAssignmentList.append(System.getProperty("line.separator"));
                            }
                            
                            // only add event package imports when they exist
                            if( sDef.getInternalEventsSet() != null && 
                            		sDef.getInternalEventsSet().getEventDefOrDeclaredEventDef() != null &&
                            		sDef.getInternalEventsSet().getEventDefOrDeclaredEventDef().size() > 0 )
                            {
                            	smIncludeList.append( "import src." + namespace + ".InternalEvents.*;" );
                            	smIncludeList.append( System.getProperty( "line.separator" ) );
                            }

                            // only add message package imports when they exist
                            boolean hasInputSet = sDef.getMessageSet().getInputSet() != null &&
                            						sDef.getMessageSet().getInputSet().getMessageDefOrDeclaredMessageDef() != null &&
                            						sDef.getMessageSet().getInputSet().getMessageDefOrDeclaredMessageDef().size() > 0;
                            boolean hasOutputSet = sDef.getMessageSet().getOutputSet() != null &&
                            						sDef.getMessageSet().getOutputSet().getMessageDefOrDeclaredMessageDef() != null &&
                            						sDef.getMessageSet().getOutputSet().getMessageDefOrDeclaredMessageDef().size() > 0;

                            if( sDef.getMessageSet() != null && ( hasInputSet || hasOutputSet ) )
                            {
                            	smIncludeList.append( "import src." + namespace + ".Messages.*;" );
                            	smIncludeList.append( System.getProperty( "line.separator" ) );
                            }
                            
                            // Set-up the template replacement hash table.
                            replaceTable.put("%statemachine_variable_list%", smVariableList.toString());
							replaceTable.put("%parent_service_list%", smParentList.toString());
                            replaceTable.put("%statemachine_args%", smArguments.toString());
                            replaceTable.put("%statemachine_include_list%", smIncludeList.toString());
                            replaceTable.put("%statemachine_assignment_list%", smAssignmentList.toString());
                            replaceTable.put("%start_state_actions%", pbGen.entryActionCalls.toString());
                            replaceTable.put("%transition_calls%", pbGen.transitionCalls.toString());
							replaceTable.put("%default_transition_calls%", pbGen.defaultCalls.toString());

                            svcMsgList.append("\t/// Input Messages").append(System.getProperty("line.separator"));;
                            for (String msg : msgInputList)
                            {
                                    svcMsgList.append("\tm_InputMessageList.add(" + msg + ".ID);");
                                    svcMsgList.append(System.getProperty("line.separator"));
                            }
                            svcMsgList.append(System.getProperty("line.separator"));
                            svcMsgList.append("\t/// Output Messages").append(System.getProperty("line.separator"));;
                            for (String msg : msgOutputList)
                            {
                                    svcMsgList.append("\tm_OutputMessageList.add(" + msg + ".ID);");
                                    svcMsgList.append(System.getProperty("line.separator"));
                            }
                            replaceTable.put("%service_message_list%", svcMsgList.toString());
                            replaceTable.put("%component_name%", cmptName);
                    }
                    catch (Exception e)
                    {
                            e.printStackTrace();
                            throw new CodeGeneratorException(e.getMessage() + " Error with ProtocolBehaviorGenerator [SDG]");
                    }


                    /*
                     * Generate the code and template for the service
                     */
                    TemplateHandler tplHandler = new TemplateHandler(replaceTable);
                    // Handle Eclipse plugin's relative paths
                    File templateDir;
                    if(new File("plugins/org.jts.eclipse.data_1.0/templates").exists())
                    {
                        templateDir = new File("plugins/org.jts.eclipse.data_1.0/templates/service");
                    }else
                    {
                        templateDir = new File("templates/service");
                    }
                    boolean validFile = false;

                    for (File template : templateDir.listFiles(new FileExtensionFilter("tpl")))
                    {
                            String destFileName = tplHandler.adjustString(template.getName().substring(0, template.getName().length() - CROP_TPL_NAME));
                            File dest = null;

                            if (destFileName.endsWith(".java"))
                            {
                                validFile = true;
                                dest = new File(srcDir + "/" + destFileName);
                                if (dest.exists())
                                    {
                                    dest.renameTo(new File(srcDir + "/" + destFileName + ".old"));
                                    dest = new File(srcDir + "/" + destFileName);
                                    }
                            }
                            else if (destFileName.endsWith(".cpp") || destFileName.endsWith(".h") || destFileName.endsWith(".cs"))
                            {
                                    validFile = false;
                                    // Do nothing. These aren't needed.
                            }
                            else
                            {
                                validFile = true;
                                dest = new File(outDir + "/" + namespace + "/" + destFileName);
                                if (dest.exists())
                                    {
                                    dest.renameTo(new File(outDir + "/" + namespace + "/" + destFileName + ".old"));
                                    dest = new File(outDir + "/" + namespace + "/" + destFileName);
                                    }
                            }

                            try
                            {
                                if(validFile)
                                {
                                    Util.copyFile(template, dest);
                                    tplHandler.adjustFile(dest);
                                }
                            }
                            catch (Exception e)
                            {
                                    throw new CodeGeneratorException("Could not Copy File [SDG]");
                            }
                    }


                    SconstructGenerator sconGen = new SconstructGenerator();
                    Vector<String> libs = new Vector<String>();

                    /// Generate the Sconstruct File
                    Util.writeContents(new File(srcDir + "/Sconstruct"), sconGen.generateLibrary(new File(srcDir), Util.upperCaseFirstLetter(serviceName), libs));
            }
            catch (Exception e)
            {
                    throw new CodeGeneratorException(e.getMessage() + "Could not generate Sconstruct file for Service [SDG]");
            }

        }

        /*
         * @param outDir
         * Generate the C# code
         */
        public void runCSharp(String outDir) throws CodeGeneratorException
        {
          // We want the Service to be within a subfolder within the outDir
            //String includeDir = outDir + "/include/" + namespace;
            String srcDir = outDir + "/src/" + namespace;
            StringBuffer includes = new StringBuffer();

            // Create the directories for this service

            File tempDir = new File(srcDir);
            if(!tempDir.exists())
            {

                    if (!tempDir.mkdirs())
                    {
                            throw new CodeGeneratorException("ServiceDefGenerator: Could not create Service include directory");
                    }
            }

            try
            {
                    StringBuffer incBuf;
                    String incName;
                    File msgSet;
                    StringBuffer strBuf;

                    /*
                    * Generate the constants file
                    */
                    ConstantsGenerator cGen = new ConstantsGenerator(codeType, sDef.getDeclaredConstSet());
                    cGen.run(outDir);

                    /*
                     * Generate the MessageSet
                     */
                    MessageSetGenerator msGen = new MessageSetGenerator(codeType, sDef.getMessageSet());
                    msGen.run(namespace, outDir);

                    /// Move the files
                    Vector<String> msgInputList = msGen.getInputMsgNameList();
                    Vector<String> msgOutputList = msGen.getOutputMsgNameList();
                    Vector<String> msgList = new Vector<String>();
                    msgList.addAll(msgInputList);
                    msgList.addAll(msgOutputList);
                    incBuf = new StringBuffer();
                    tempDir = new File(srcDir + "/Messages");
                    tempDir.mkdir();
                    for (File src : tempDir.listFiles())
                    {
                            if (!src.isDirectory() && msgList.contains(src.getName().substring(0, src.getName().length() - 3)))
                            {
                                    incBuf.append("using " + namespace +".Messages." + src.getName().substring(0, src.getName().length() -3)).append(";" + System.getProperty("line.separator"));
                            }
                    }
                    includes.append(incBuf.toString());


                    /*
                     * Generate the InternalEventSet
                     */
                    InternalEventsSetGenerator iesGen = new InternalEventsSetGenerator(codeType, sDef, sSet);
                    iesGen.run(namespace, outDir);

                    /// Get a list of all the files
                    Vector<String> ieList = iesGen.getInternalEventNameList();
                    incBuf = new StringBuffer();
                    tempDir = new File(srcDir + "/InternalEvents");
                    tempDir.mkdir();


                    includes.append(incBuf.toString());


                    /*
                     * Generate the ProtocolBehavior State Machines
                     */
                    try
                    {
                            org.jts.codegenerator.ProtocolBehaviorGenerator pbGen = new org.jts.codegenerator.ProtocolBehaviorGenerator(namespace, codeType, outDir, sDef, sSet, includes);
                            transportVersion = pbGen.getTransportVersion();

                            /*
                             * Generate the Service Files
                             */
                            StringBuffer smVariableList = new StringBuffer();
                            StringBuffer smIncludeList = new StringBuffer();
                            StringBuffer smAssignmentList = new StringBuffer();
                            StringBuffer smDestructorList = new StringBuffer();
                            StringBuffer svcMsgList = new StringBuffer();
							StringBuffer smParentList = new StringBuffer();
                            StringBuffer smArguments = new StringBuffer();

							// Get the list of parent services
							Vector<Reference> parentList = new Vector<Reference>();
							org.jts.codegenerator.support.InheritanceHelper.getParentServiceList( 
											   codeType, sSet, sDef, parentList);
                                       
							// For each parent reference, add an include and constructor pointer
							for (Reference ref : parentList)
							{                   
								// Add this service to the constructor and include list
								smParentList.append(", " + ref.namespace + "." + ref.name + " p" + ref.name);
							}

                            for (String smName : pbGen.getStateMachineNames())
                            {
                            
									// Get any parent FSMs and restructure into a parameter list
									Vector<Reference> fsmList = new Vector<Reference>();
									org.jts.codegenerator.support.InheritanceHelper.getParentFSMList( 
																		codeType, smName, sSet, sDef, fsmList);
									StringBuffer fsmParameters = new StringBuffer();
									for (Reference ref : fsmList)
									{
										if (fsmParameters.length() != 0) fsmParameters.append(", ");
										fsmParameters.append("p" + ref.owner + ".p" + ref.name);
									}
                                                    
                                    smVariableList.append("public " + smName + " p" + smName + ";");
                                    smVariableList.append(System.getProperty("line.separator"));

                                    if (smArguments.length() != 0)
                                       smArguments.append(",").append(System.getProperty("line.separator")).append("\t\t\t\t");
                                    smArguments.append(smName + " p" + smName);

                                    smAssignmentList.append("\tp" + smName + " = new " + smName + "(" + fsmParameters + ");");
                                    smAssignmentList.append(System.getProperty("line.separator"));
                                    smAssignmentList.append("\tp" + smName + ".setHandlers(ref ieHandler, ref jausRouter);");
                                    smAssignmentList.append(System.getProperty("line.separator"));
                                    smAssignmentList.append("\tp" + smName + ".setupNotifications();");
									smAssignmentList.append(System.getProperty("line.separator"));
                            }

                            // Set-up the template replacement hash table.
                            replaceTable.put("%statemachine_variable_list%", smVariableList.toString());
							replaceTable.put("%parent_service_list%", smParentList.toString());
                            replaceTable.put("%statemachine_args%", smArguments.toString());
                            replaceTable.put("%statemachine_include_list%", smIncludeList.toString());
                            replaceTable.put("%statemachine_assignment_list%", smAssignmentList.toString());
                            replaceTable.put("%start_state_actions%", pbGen.entryActionCalls.toString());
                            replaceTable.put("%transition_calls%", pbGen.transitionCalls.toString());
							replaceTable.put("%default_transition_calls%", pbGen.defaultCalls.toString());

                            svcMsgList.append("\t/// Input Messages").append(System.getProperty("line.separator"));;
                            for (String msg : msgInputList)
                            {
                                    svcMsgList.append("\tm_InputMessageList.Add(new " + msg + "().getID());");
                                    svcMsgList.append(System.getProperty("line.separator"));
                            }
                            svcMsgList.append(System.getProperty("line.separator"));
                            svcMsgList.append("\t/// Output Messages").append(System.getProperty("line.separator"));;
                            for (String msg : msgOutputList)
                            {
                                    svcMsgList.append("\tm_OutputMessageList.Add(new " + msg + "().getID());");
                                    svcMsgList.append(System.getProperty("line.separator"));
                            }
                            replaceTable.put("%service_message_list%", svcMsgList.toString());
                    }
                    catch (Exception e)
                    {
                            e.printStackTrace();
                            throw new CodeGeneratorException(e.getMessage() + " Error with ProtocolBehaviorGenerator [SDG]");
                    }


                    /*
                     * Generate the code and template for the service
                     */
                    TemplateHandler tplHandler = new TemplateHandler(replaceTable);
                    // Handle Eclipse plugin's relative paths
                    File templateDir;
                    if(new File("plugins/org.jts.eclipse.data_1.0/templates").exists())
                    {
                        templateDir = new File("plugins/org.jts.eclipse.data_1.0/templates/service");
                    }else
                    {
                        templateDir = new File("templates/service");
                    }
                    boolean validFile = false;

                    for (File template : templateDir.listFiles(new FileExtensionFilter("tpl")))
                    {
                            String destFileName = tplHandler.adjustString(template.getName().substring(0, template.getName().length() - CROP_TPL_NAME));
                            File dest = null;

                            if (destFileName.endsWith(".cs"))
                            {
                                validFile = true;
                                dest = new File(srcDir + "/" + destFileName);
                                if (dest.exists())
                                    {
                                    dest.renameTo(new File(srcDir + "/" + destFileName + ".old"));
                                    dest = new File(srcDir + "/" + destFileName);
                                    }
                            }
                            else if (destFileName.endsWith(".cpp") || destFileName.endsWith(".h") || destFileName.endsWith(".java"))
                            {
                                // Do nothing. These aren't needed.
                                validFile = false;
                            }
                            else
                            {
                                validFile = true;
                                dest = new File(outDir + "/" + namespace + "/" + destFileName);
                                if (dest.exists())
                                    {
                                    dest.renameTo(new File(outDir + "/" + namespace + "/" + destFileName + ".old"));
                                    dest = new File(outDir + "/" + namespace + "/" + destFileName);
                                    }
                            }

                            try
                            {
                                if(validFile)
                                {
                                    Util.copyFile(template, dest);
                                    tplHandler.adjustFile(dest);
                                }
                            }
                            catch (Exception e)
                            {
                                    throw new CodeGeneratorException("Could not Copy File [SDG]");
                            }
                    }


				SconstructGenerator sg = new SconstructGenerator();

                /// Get the list of parent services.... these are required libraries for linking
                    Vector<String> libs = new Vector<String>();
				Vector<Reference> parentList = new Vector<Reference>();
				org.jts.codegenerator.support.InheritanceHelper.getParentServiceList( codeType, sSet, sDef, parentList );
				for (int i=parentList.size(); i > 0; i--)
					libs.add( parentList.get(i-1).name );
				
                /// Generate the Sconscript File
				sg.generateCSharpSconscript(new File(srcDir), Util.upperCaseFirstLetter(serviceName), libs);


            }
            catch (Exception e)
            {
                    throw new CodeGeneratorException(e.getMessage() + "Could not generate Sconstruct file for Service [SDG]");
            }
        }

        /*
         * @return the namespace
         */
	public String getNamespace()
	{
		return namespace;
	}

        /*
         * @return the name of the service as a string.
         */
	public String getServiceName()
	{
		return serviceName;
	}

    /*
     * @return the string representation of the component's transport version
     */
    public String getTransportVersion()
    {
        return transportVersion;
    }
	
//	private void generateVirtualService(ProtocolBehaviorGenerator pbGen, String name)
//	{
//		CodeLines code = new CodeLines("", codeType, "");
//		Vector<String> actionList = pbGen.getActionOrSendActionList();
//		
//		for (String line : actionList)
//		{
//			code.publicMethods.add("virtual void " + line + "=0;");
//		}
//		
//		new CodeWriter().writeCppCode(code, targetDir, fileName)
//	}
 }