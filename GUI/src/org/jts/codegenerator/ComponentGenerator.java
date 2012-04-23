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

import org.jts.jsidl.binding.*;
import org.jts.codegenerator.support.*;
import java.util.Vector;
import java.io.File;
import java.util.Hashtable;

/**
 * Generates the code to encapsulate the services in the ServiceSet into a component.
 * 
 * @author Nicholas M. Johnson
 *
 */
public class ComponentGenerator
{
	/* List of Template Replaceable Parameters
	 * copyright
	 */
	
	private CodeLines.CodeType codeType;
	private CodeLines.BuildType buildType;
	private Hashtable<String, String> replaceTable;
	private ServiceSet reducedSet;
	
	public ComponentGenerator(CodeLines.CodeType codeType, CodeLines.BuildType buildType)
	{
		this.codeType = codeType;
		this.buildType = buildType;

		this.replaceTable = new Hashtable<String, String>();
	}
	
	
	/**
	 * 
	 * @param cmptName
	 * @param sSet
	 */
	public void run(String outDirName, String cmptName, String cmptId, ServiceSet sSet) throws CodeGeneratorException
	{
		if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
		{
			File outDir;
			Vector<String> serviceLibs = new Vector<String>();
			StringBuffer serviceNamespaceList = new StringBuffer();
			StringBuffer serviceList = new StringBuffer();
			StringBuffer serviceIncludeList = new StringBuffer();
			StringBuffer serviceConstructorList = new StringBuffer();
			
			cmptName = Util.upperCaseFirstLetter(cmptName);
			outDirName += "/" + cmptName + "_" + cmptId;
						
			replaceTable.put("%copyright%", "");
			replaceTable.put("%namespace%", "JSIDL_v_1_0");
			replaceTable.put("%component_name%", cmptName);	
			replaceTable.put("%component_name_allcaps%", cmptName.toUpperCase());
			replaceTable.put("%component_id%", cmptId);
			
			
			/*
			 * Generate the Folder for the Component
			 */
			outDir = new File(outDirName);
			if (!outDir.exists())
			{
				//System.out.println("ComponentGenerator: " + outDir.getName() + " directory does not exist. Creating.");
				
				if (!outDir.mkdirs())
				{
					throw new CodeGeneratorException("ComponentGenerator: Could not create " + outDir.getName());
				}
			}
			
			/*
			 * Create the Component include directory
			 */ 
			outDir = new File(outDirName + "/include");
			if (!outDir.exists())
			{
//				System.out.println("ComponentGenerator: " + outDir.getName() + " directory does not exist. Creating.");
				
				if (!outDir.mkdirs())
				{
					throw new CodeGeneratorException("ComponentGenerator: Could not create " + outDir.getName());
				}
			}
	
			/*
			 * Create the Component src directory
			 */
			outDir = new File(outDirName + "/src");
			if (!outDir.exists())
			{
//				System.out.println("ComponentGenerator: " + outDir.getName() + " directory does not exist. Creating.");
				
				if (!outDir.mkdirs())
				{
					throw new CodeGeneratorException("ComponentGenerator: Could not create " + outDir.getName());
				}
			}

			/*
			 * Resolve service dependencies.  This results in a reduced service set, where each 
			 * remaining service definition has all dependecies removed.
			 */
		  ServiceSetResolver ssr = new ServiceSetResolver(codeType, sSet);
		  reducedSet = ssr.run();
					
			/*
			 * Generate all the service definitions
			 */ 
			for (ServiceDef sDef : reducedSet.getServiceDef())
			{
			    // create service constructors                         
				setupServices( sDef, serviceList, serviceIncludeList, serviceNamespaceList, serviceLibs, serviceConstructorList );
				
				// now generate the actual services
			    ServiceDefGenerator sdGen = new ServiceDefGenerator(codeType, sDef, reducedSet);
				sdGen.run(outDirName, cmptName);
                                        }

			replaceTable.put("%service_list%", serviceList.toString());
			replaceTable.put("%service_include_list%", serviceIncludeList.toString());
			replaceTable.put("%service_namespace_list%", serviceNamespaceList.toString());
			replaceTable.put("%service_construction_list%", serviceConstructorList.toString());
	
			/*
			 * Generate the code and template for a component 
			 */
			TemplateHandler tplHandler = new TemplateHandler(replaceTable);
			File templateDir;
            // Handle Eclipse plugin's use of relative paths.
            if(new File("plugins/org.jts.eclipse.data_1.0/templates/component").exists())
            {
                templateDir = new File("plugins/org.jts.eclipse.data_1.0/templates/component");
            }
            else
            {
                templateDir =  new File("templates/component");
            }
            boolean validFile = false;
		
			for (File template : templateDir.listFiles(new FileExtensionFilter("tpl")))
			{
				String destFileName = tplHandler.adjustString(template.getName().substring(0, template.getName().length() - 4));
				File dest = null;
				
				if (destFileName.compareTo("main.cpp") == 0)
				{
                                    validFile = true;
                                    dest = new File(outDirName + "/src/" + destFileName);
                                    if (dest.exists())
					{
                                        dest.renameTo(new File(outDirName + "/src/" + destFileName + ".old"));
                                        dest = new File(outDirName + "/src/" + destFileName);
					}
				}
				else if (destFileName.endsWith(".h"))
				{
                                    validFile = true;
                                    dest = new File(outDirName + "/include/" + destFileName);
				}
				else if (destFileName.endsWith(".cpp"))
				{
                                    validFile = true;
                                     dest = new File(outDirName + "/src/" + destFileName);
				}else if(destFileName.endsWith(".cs") || destFileName.endsWith(".java"))
                                {
                                    validFile = false;
                                    // Don't do anything. Files not needed.
                                }
				else
				{
                                    validFile = true;
                                     dest = new File(outDirName + "/" + destFileName);
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
					
				}
			}

			if( buildType == CodeLines.BuildType.SCONS )
			{
				SconstructGenerator sconGen = new SconstructGenerator();
	
				/*
				 * Generate SConstruct File
				 */
				try
				{
		               Util.writeContents(new File(outDirName + "/Sconstruct"), sconGen.generateProgram(new File(outDirName), cmptName + "_" + cmptId, serviceLibs));
	            }
	            catch (Exception e)
	            {
	                throw new CodeGeneratorException("Could not generate Sconstruct file [CG]");
	            }
			}
			else if( buildType == CodeLines.BuildType.VS )
			{            
    			try
    			{
    				VisualStudioGenerator generator = new VisualStudioGenerator( cmptName, new File( outDirName ) );
    				generator.generateComponent( serviceLibs );
                }
                catch (Exception e)
                {
                	e.printStackTrace();
                    throw new CodeGeneratorException("Could not generate VS solution file [CG]");
                }            	
            }
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            File outDir;
            String transportVersion = "";
            Vector<String> serviceLibs = new Vector<String>();
            StringBuffer serviceList = new StringBuffer();
            StringBuffer serviceIncludeList = new StringBuffer();
			StringBuffer serviceNamespaceList = new StringBuffer();
            StringBuffer serviceConstructorList = new StringBuffer();

            cmptName = Util.upperCaseFirstLetter(cmptName);
            outDirName += "/" + cmptName + "_" + cmptId;

            replaceTable.put("%copyright%", "");
			replaceTable.put("%package%", "JSIDL_v_1_0");
			replaceTable.put("%component_name%", cmptName);
			replaceTable.put("%component_name_allcaps%", cmptName.toUpperCase());
			replaceTable.put("%component_id%", cmptId);


			/*
			 * Generate the Folder for the Component
			 */
			outDir = new File(outDirName);
			if (!outDir.exists())
			{
				//System.out.println("ComponentGenerator: " + outDir.getName() + " directory does not exist. Creating.");

				if (!outDir.mkdirs())
				{
					throw new CodeGeneratorException("ComponentGenerator: Could not create " + outDir.getName());
				}
			}

			/*
			 * Create the Component include directory
			 */
            /*
			outDir = new File(outDirName + "/include");
			if (!outDir.exists())
			{
//				System.out.println("ComponentGenerator: " + outDir.getName() + " directory does not exist. Creating.");

				if (!outDir.mkdirs())
				{
					throw new CodeGeneratorException("ComponentGenerator: Could not create " + outDir.getName());
				}
			}
             * 
             */

			/*
			 * Create the Component src directory
			 */
			outDir = new File(outDirName + "/src");
			if (!outDir.exists())
			{
//				System.out.println("ComponentGenerator: " + outDir.getName() + " directory does not exist. Creating.");

				if (!outDir.mkdirs())
				{
					throw new CodeGeneratorException("ComponentGenerator: Could not create " + outDir.getName());
				}
			}

			/*
			 * Resolve service dependencies.  This results in a reduced service set, where each
			 * remaining service definition has all dependecies removed.
			 */
		  ServiceSetResolver ssr = new ServiceSetResolver(codeType, sSet);
		  reducedSet = ssr.run();

			/*
			 * Generate all the service definitions
			 */ 
			for (ServiceDef sDef : reducedSet.getServiceDef())
			{
			    // create service constructors                         
				setupServices( sDef, serviceList, serviceIncludeList, serviceNamespaceList, serviceLibs, serviceConstructorList );
				
				// now generate the actual services
			    ServiceDefGenerator sdGen = new ServiceDefGenerator(codeType, sDef, reducedSet);
				sdGen.run(outDirName, cmptName);
                transportVersion = sdGen.getTransportVersion();
				}

			replaceTable.put("%service_list%", serviceList.toString());
			replaceTable.put("%service_include_list%", serviceIncludeList.toString());
			replaceTable.put("%service_construction_list%", serviceConstructorList.toString());

			/*
			 * Generate the code and template for a component
			 */
			TemplateHandler tplHandler = new TemplateHandler(replaceTable);
			File templateDir;
            // Handle Eclipse plugin's use of relative paths.
            if(new File("plugins/org.jts.eclipse.data_1.0/templates/component").exists())
            {
                templateDir = new File("plugins/org.jts.eclipse.data_1.0/templates/component");
            }
            else
            {
                templateDir =  new File("templates/component");
            }
                        boolean validFile = false;

			for (File template : templateDir.listFiles(new FileExtensionFilter("tpl")))
			{
				String destFileName = tplHandler.adjustString(template.getName().substring(0, template.getName().length() - 4));
				File dest = null;

				if (destFileName.compareTo("Main.java") == 0)
				{
                                    validFile = true;
                                    dest = new File(outDirName + "/src/" + destFileName);
                                    if (dest.exists())
					{
                                        dest.renameTo(new File(outDirName + "/src/" + destFileName + ".old"));
                                        dest = new File(outDirName + "/src/" + destFileName);
					}
				}
	
				else if (destFileName.endsWith(".java"))
				{
                                    validFile = true;
                                    dest = new File(outDirName + "/src/" + destFileName);
				}else if(destFileName.endsWith(".cs") || destFileName.endsWith(".h") ||destFileName.endsWith(".cpp" ))
                                {
                                    validFile = false;
                                    // Don't do anything. Files not needed.
                                }
				else
				{
                                    validFile = true;
                                    dest = new File(outDirName + "/" + destFileName);
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

				}
			}

			SconstructGenerator sconGen = new SconstructGenerator();

			/*
			 * Generate SConstruct File
			 */
			try
			{
				//Util.writeContents(new File(outDirName + "/Sconstruct"), sconGen.generateJavaSconstruct(new File(outDirName), cmptName + "_" + cmptId));

                sconGen.generateJavaSconstruct(new File(outDirName), cmptName + "_" + cmptId, transportVersion);
            }
			catch (Exception e)
			{
				throw new CodeGeneratorException("Could not generate Sconstruct file [CG]");
			}
        }
        else if(codeType == CodeLines.CodeType.C_SHARP)
        {
            File outDir;
			Vector<String> serviceLibs = new Vector<String>();
			StringBuffer serviceList = new StringBuffer();
			StringBuffer serviceNamespaceList = new StringBuffer();
			StringBuffer serviceIncludeList = new StringBuffer();
			StringBuffer serviceConstructorList = new StringBuffer();
            StringBuffer files = new StringBuffer();
            String transportVersion = "";

			cmptName = Util.upperCaseFirstLetter(cmptName);
			outDirName += "/" + cmptName + "_" + cmptId;

			replaceTable.put("%copyright%", "");
			replaceTable.put("%package%", "JSIDL_v_1_0");
			replaceTable.put("%component_name%", cmptName);
			replaceTable.put("%component_name_allcaps%", cmptName.toUpperCase());
			replaceTable.put("%component_id%", cmptId);


			/*
			 * Generate the Folder for the Component
			 */
			outDir = new File(outDirName);
			if (!outDir.exists())
			{
				//System.out.println("ComponentGenerator: " + outDir.getName() + " directory does not exist. Creating.");

				if (!outDir.mkdirs())
				{
					throw new CodeGeneratorException("ComponentGenerator: Could not create " + outDir.getName());
				}
			}

			/*
			 * Create the Component include directory
			 */
            /*
			outDir = new File(outDirName + "/include");
			if (!outDir.exists())
			{
//				System.out.println("ComponentGenerator: " + outDir.getName() + " directory does not exist. Creating.");

				if (!outDir.mkdirs())
				{
					throw new CodeGeneratorException("ComponentGenerator: Could not create " + outDir.getName());
				}
			}
             *
             */

			/*
			 * Create the Component src directory
			 */
			outDir = new File(outDirName + "/src");
			if (!outDir.exists())
			{
//				System.out.println("ComponentGenerator: " + outDir.getName() + " directory does not exist. Creating.");

				if (!outDir.mkdirs())
				{
					throw new CodeGeneratorException("ComponentGenerator: Could not create " + outDir.getName());
				}
			}

			/*
			 * Resolve service dependencies.  This results in a reduced service set, where each
			 * remaining service definition has all dependencies removed.
			 */
		  ServiceSetResolver ssr = new ServiceSetResolver(codeType, sSet);
		  reducedSet = ssr.run();

			/*
			 * Generate all the service definitions
			 */ 
			for (ServiceDef sDef : reducedSet.getServiceDef())
			{
			    // create service constructors                         
				setupServices( sDef, serviceList, serviceIncludeList, serviceNamespaceList, serviceLibs, serviceConstructorList );
				
				// now generate the actual services
			    ServiceDefGenerator sdGen = new ServiceDefGenerator(codeType, sDef, reducedSet);
				sdGen.run(outDirName, cmptName);
                transportVersion = sdGen.getTransportVersion();
				}

			replaceTable.put("%service_list%", serviceList.toString());
			replaceTable.put("%service_include_list%", serviceIncludeList.toString());
			replaceTable.put("%service_construction_list%", serviceConstructorList.toString());

			/*
			 * Generate the code and template for a component
			 */
			TemplateHandler tplHandler = new TemplateHandler(replaceTable);
			File templateDir;
            // Handle Eclipse plugin's use of relative paths.
            if(new File("plugins/org.jts.eclipse.data_1.0/templates/component").exists())
            {
                templateDir = new File("plugins/org.jts.eclipse.data_1.0/templates/component");
            }
            else
            {
                templateDir =  new File("templates/component");
            }
                        boolean validFile = false;

            for (File template : templateDir.listFiles(new FileExtensionFilter("tpl")))
            {
                String destFileName = tplHandler.adjustString(template.getName().substring(0, template.getName().length() - 4));
                File dest = null;

                if (destFileName.compareTo("main.cs") == 0)
                {
                    validFile = true;
                    dest = new File(outDirName + "/src/" + destFileName);
                    if (dest.exists())
                    {
                        dest.renameTo(new File(outDirName + "/src/" + destFileName + ".old"));
                        dest = new File(outDirName + "/src/" + destFileName);
                    }
                }
                else if (destFileName.endsWith(".cs"))
                {
                    validFile = true;
                    dest = new File(outDirName + "/src/" + destFileName);
                }
                else if (destFileName.endsWith(".java") || destFileName.endsWith(".h") || destFileName.endsWith(".cpp"))
                {
                    validFile = false;
                    // Don't do anything. Files not needed.
                }
                else
                {
                    validFile = true;
                    dest = new File(outDirName + "/" + destFileName);
                }

                try
                {
                    if (validFile)
                    {
                        Util.copyFile(template, dest);
                        tplHandler.adjustFile(dest);
                    }
                }
                catch (Exception e)
                {
                }
            }

            /*
             * Generate SConstruct File
             */
			SconstructGenerator sg = new SconstructGenerator();
            sg.generateCSharpSconstruct(new File(outDirName), cmptName, transportVersion, serviceLibs);
            }
	}

    //	
	// Since constructors now take pointers to inherited services, the order of construction is important.
	// This function is recursive so that the top-level parent is constructed first
	//
	protected void setupServices( ServiceDef sDef, 
	                              StringBuffer serviceList, 
	                              StringBuffer serviceIncludeList, 
	                              StringBuffer serviceNamespaceList,
	                              Vector<String> serviceLibs, 
	                              StringBuffer serviceConstructorList )
	{
	    try 
	    {
			// Get the parent.  If it exists, call this function recursively.
			ServiceDef parent = org.jts.codegenerator.support.InheritanceHelper.findServiceDef(
			                                    reducedSet, sDef.getReferences().getInheritsFrom().getId(),
												sDef.getReferences().getInheritsFrom().getVersion());
			if (parent != null)
				setupServices(parent, serviceList, serviceIncludeList, serviceNamespaceList, serviceLibs, serviceConstructorList);
	    }
	    catch (Exception e)
		{
			// No need to do much on this exception.  It likely means the service has no
			// references or clientOf relationships.
		}
	     
		     
		 // We need to get the service name and namespace.  Easiest way to do that is through the service def generator
		 ServiceDefGenerator sdGen = new ServiceDefGenerator(codeType, sDef, reducedSet);

		 if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
		 {		 
			 // Now we can check to see if this service def already exists in the constructor list.
			 // If it does, there is nothing more to do...
			 if (serviceConstructorList.indexOf(sdGen.getServiceName() + "* p" + sdGen.getServiceName() + " = new ") != -1)
				 return;
		     
			 // Getting here means this is a new service def.  Create the constructor, including references to all parents.
			 Vector<Reference> parentList = new Vector<Reference>();
			 org.jts.codegenerator.support.InheritanceHelper.getParentServiceList(codeType, reducedSet, sDef, parentList);
			 serviceConstructorList.append("\t" + sdGen.getServiceName() + "* p" + sdGen.getServiceName() + " = new " + sdGen.getServiceName() + "(jausRouter");
			 for (Reference ref : parentList) serviceConstructorList.append( ", p" + ref.name );
			 serviceConstructorList.append(");");
			 serviceConstructorList.append(System.getProperty("line.separator"));		 
		 
			 // Finally, add this service to the various string buffers...
			 serviceNamespaceList.append("using namespace " + sdGen.getNamespace() + ";");
			 serviceNamespaceList.append(System.getProperty("line.separator"));
			 serviceList.append("\tserviceList.push_back(p" + sdGen.getServiceName() + ");");
			 serviceList.append(System.getProperty("line.separator"));
			 serviceIncludeList.append("#include \"" + sdGen.getNamespace() + "/" + sdGen.getServiceName() + ".h\"");
			 serviceIncludeList.append(System.getProperty("line.separator"));
			 serviceLibs.add(sdGen.getNamespace() + "/lib/" + sdGen.getServiceName());		
		}
		else if (codeType == CodeLines.CodeType.JAVA)
		{
			 // Now we can check to see if this service def already exists in the constructor list.
			 // If it does, there is nothing more to do...
			 if (serviceConstructorList.indexOf(sdGen.getServiceName() + " p" + sdGen.getServiceName() + " = new ") != -1)
				 return;
		     
			 // Getting here means this is a new service def.  Create the constructor, including references to all parents.
			 Vector<Reference> parentList = new Vector<Reference>();
			 org.jts.codegenerator.support.InheritanceHelper.getParentServiceList(codeType, reducedSet, sDef, parentList);
			 serviceConstructorList.append("\t\t" + sdGen.getServiceName() + " p" + sdGen.getServiceName() + " = new " + sdGen.getServiceName() + "(jausRouter");
			 for (Reference ref : parentList) serviceConstructorList.append( ", p" + ref.name );
			 serviceConstructorList.append(");");
			 serviceConstructorList.append(System.getProperty("line.separator"));		 
		 
			 // Finally, add this service to the various string buffers...
			 serviceList.append("\t\tserviceList.add(p" + sdGen.getServiceName() + ");");
			 serviceList.append(System.getProperty("line.separator"));
			 serviceIncludeList.append("import src." + sdGen.getNamespace() + "." + sdGen.getServiceName() + ";");
			 serviceIncludeList.append(System.getProperty("line.separator"));
			 serviceLibs.add(sdGen.getNamespace() + "/lib/" + sdGen.getServiceName());	
		}
		else if (codeType == CodeLines.CodeType.C_SHARP)
		{
			 // Now we can check to see if this service def already exists in the constructor list.
			 // If it does, there is nothing more to do...
			 if (serviceConstructorList.indexOf(sdGen.getServiceName() + " p" + sdGen.getServiceName() + " = new ") != -1)
				 return;
		     
			 // Getting here means this is a new service def.  Create the constructor, including references to all parents.
			 Vector<Reference> parentList = new Vector<Reference>();
			 org.jts.codegenerator.support.InheritanceHelper.getParentServiceList(codeType, reducedSet, sDef, parentList);
			 serviceConstructorList.append("\t\t" + sdGen.getNamespace() + "." + sdGen.getServiceName() + " p" + sdGen.getServiceName() + " = new " + sdGen.getNamespace() + "." + sdGen.getServiceName() + "(jausRouter");
			 for (Reference ref : parentList) serviceConstructorList.append( ", p" + ref.name );
			 serviceConstructorList.append(");");
			 serviceConstructorList.append(System.getProperty("line.separator"));		 
		 
			 // Finally, add this service to the various string buffers...
			 serviceList.append("\t\tserviceList.Add(p" + sdGen.getServiceName() + ");");
			 serviceList.append(System.getProperty("line.separator"));
			 serviceLibs.add(sdGen.getNamespace() + ":" + sdGen.getServiceName());	
		}		
	}

}
