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
package org.jts.eclipse.ui.actions.importbinding;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.xtext.ui.resource.XtextResourceSetProvider;
import org.jts.eclipse.conversion.cjsidl.Conversion;
import org.jts.eclipse.conversion.cjsidl.ConversionException;
import org.jts.eclipse.ui.internal.CjsidlActivator;
import org.jts.gui.importJSIDL.ImportException;
import org.jts.jsidl.binding.ClientOf;
import org.jts.jsidl.binding.Description;
import org.jts.jsidl.binding.InheritsFrom;
import org.jts.jsidl.binding.References;
import org.jts.jsidl.binding.ServiceDef;
import org.jts.jsidl.binding.ServiceSet;
import org.jts.jsidl.binding.Start;
import org.jts.jsidl.binding.State;
import org.jts.jsidl.binding.StateMachine;
import org.xml.sax.SAXException;

import com.google.inject.Inject;
import com.google.inject.Injector;

public class ImportServiceSet {
	
	private static final String SERVICE_VERSION = "1.1"; // Not sure how else to define the version right now 6/17
	private static final String CORE_XML = "lib/resources/xml";
	private String serviceName = "";	
	private IPath sourcePath;
	private String outputFolder = "";
	private String tmpFlder = "";
	private IProject proj;
	private Shell shell;
	@Inject
	private XtextResourceSetProvider resourceSetProvider;

	/**
	 * 
	 * @param name the name of the component
	 * @param path
	 */
	public ImportServiceSet(String name, IPath path, IProject project, Shell shell)
	{
		serviceName = name + "Service";
		sourcePath = path;
		proj = project;
		this.shell = shell;
		outputFolder =proj.getLocation().toPortableString() + "/jsidl_output";
		File output = new File(outputFolder);
		if(!output.exists())
		{
		    output.mkdir();
		}
		else
		{
			for(File f: output.listFiles())
			{
				if(f.isDirectory())
				{
					for(File fl: f.listFiles())
					{
						fl.delete();
					}
				}
				f.delete();
			}
		}
		tmpFlder = outputFolder + "/flattened_jsidl";
		File tmp = new File(tmpFlder);
		if(!tmp.exists())
		{
			tmp.mkdir();
		}
		
		
	}
	/**
	 * 
	 * @return The formatted JSIDL binding Service Set.
	 * @throws Exception 
	 */
	public ServiceSet getServiceSet() throws Exception
	{
		// Set up service set
		ServiceSet set = new ServiceSet();
		String id = "urn.jaus." + serviceName;
		
		set.setId(id);
		set.setName(serviceName);
		set.setVersion(SERVICE_VERSION);
		Description desc = new Description();
		desc.setContent("");
		set.setDescription(desc);
		
		// populate service set service defs.
		List<ServiceDef> serviceDefList = set.getServiceDef();
		List<ServiceDef> projectDefsList = getServiceDefs();
		
		Map<String, List> rootFSMs = new HashMap<String, List>();
		
		// Ensures correct handling of inherited state machines.
		java.util.Collections.reverse(projectDefsList);
		
		for(ServiceDef sd: projectDefsList)
		{
			addMissingStates(sd, projectDefsList);
			//processStateMachineNames(sd, rootFSMs, projectDefsList);
			serviceDefList.add(sd);
		}
		
		sourcePath.toString();
//		File tmp = new File(outputFolder);
//		tmp.delete();
		for(ServiceDef sd: serviceDefList)
		{
			// flattened service defs shouldn't need these.
	        sd.setDeclaredTypeSet(null);
	        sd.setDeclaredConstSet(null);

			try {
				serializeJAXB(sd, tmpFlder);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return set;
	}
	
	
	private void serializeJAXB(ServiceDef sd, String tmpFlder2) throws FileNotFoundException {
		String id = sd.getId();
        id = id.replace(":", "_");
        File tmpfile = new File(tmpFlder2 + "/" + id + ".xml");
        if (tmpfile.isDirectory() && !tmpfile.exists()) {
            boolean success = tmpfile.mkdirs();
            if (!success) {
                throw new FileNotFoundException("Failed to make new directory. Path is either bad or this application"
                        + "doesn't have the permissions necessary.  Check your path and try again.");
            }
        }

        java.io.FileWriter fw;
        try {
            fw = new java.io.FileWriter(tmpfile);
            javax.xml.bind.JAXB.marshal(sd, fw);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
	}
	/**
	 * Performs a search of the source folder to find all necessary service
	 * definitions.
	 * 
	 * @return a list of all the service definitions in the project and their
	 *         parent files.
	 * @throws Exception 
	 */
	private List<ServiceDef> getServiceDefs() throws Exception {
	    List<ServiceDef> defs = new ArrayList<ServiceDef>();
	    List<File> files = new ArrayList<File>();
	    
	    convertFilesToJSIDL();	    
	    for(File f: new File(outputFolder).listFiles())
	    {
	        if(f.getName().endsWith(".xml"))
	        {
	            files.add(f);
	        }	        
	    }
	    
	    try {
            Util.getObjectMapFromFile(files, defs);

			// Process dependencies and format bindings correctly.
			defs = processServices(defs);
			
	    } catch (JAXBException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return defs;
	}
	/**
     * Reads state machines and incorporates state data into defined FSMs
     * @param serviceDef - the service definition which may contain FSMs.
     * @param rootFSMs - A list of state machines the service def has inherited.
     */
    private void addMissingStates(ServiceDef serviceDef, List<ServiceDef> projDefs)
    {
        List<StateMachine> fsmlist = serviceDef.getProtocolBehavior().getStateMachine();
        String parent = "";
        String parentName = "";
        if(serviceDef.getReferences()!= null && serviceDef.getReferences().getInheritsFrom() != null)
        {
        	parent = serviceDef.getReferences().getInheritsFrom().getId();
    		parentName = serviceDef.getReferences().getInheritsFrom().getName();
        }
        
        for (StateMachine fsm : fsmlist)
        {
        	String fsmName = fsm.getName();
        	 // Add parent states.
            ServiceDef parentDef = null;
            for(ServiceDef def: projDefs)
            {
            	if(def.getId().equals(parent))
            	{
            		parentDef = def;
            	}
            }
            // If the state machine has a parent.
            if(parentDef != null)
            {            	
            	// Find a matching state machine.
            	for(StateMachine sm: parentDef.getProtocolBehavior().getStateMachine())
            	{
            		String smName = parentName + "." + sm.getName();
            		if(smName.equals(fsmName))
            		{
            			// Find the matching states.
            			for(State s: fsm.getState())
            			{
            				List<State> potentialMissingStates = new ArrayList<State>();
            				potentialMissingStates.addAll(sm.getState());
            				
            				for(State ps: sm.getState())
            				{
            					if(s.getName().equals(ps.getName()))
            					{
            						// Same state in first level of state machine.
            						potentialMissingStates.remove(ps);
            						recursiveCheckSubStateInheritance(s, ps);
            					}            					
            				}

            			}
            		}
            	}
            	
            }
            
        }


    }
	

	/**
     * Reads state machines and incorporates state data into defined FSMs
     * @param serviceDef - the service definition which may contain FSMs.
     * @param rootFSMs - A list of state machines the service def has inherited.
     */
    private void processStateMachineNames(ServiceDef serviceDef, Map<String, List> rootFSMs, List<ServiceDef> projDefs)
    {
        List<Start> startlist = serviceDef.getProtocolBehavior().getStart();
        List<StateMachine> fsmlist = serviceDef.getProtocolBehavior().getStateMachine();
        List<String> localFSMs = new ArrayList<String>();
        String tmp = "";
        String name = "";

        // Clean up the names of the state machines.
        // This is based off of how the Hibernate DB adjusts the names.
        for (StateMachine fsm : fsmlist)
        {
            // Adjust name
            name = fsm.getName();
            name = serviceDef.getName() + "." + name;
            tmp = name.substring(name.lastIndexOf(".") + 1);
            localFSMs.add(tmp);
            if(!rootFSMs.containsKey(tmp))
            {
                rootFSMs.put(tmp, fsm.getState());
            }
            fsm.setName(name);
            name = name.substring(0, 1).toUpperCase() + name.substring(1, name.lastIndexOf("."));
            
           
            
        }

        // Update the names of the start states.
        for (Start start : startlist)
        {
            name = start.getStateMachineName();
            name = serviceDef.getName() + "." + name;
            start.setStateMachineName(name);
        }


        // Check to see if the service def is missing any state machines that are inherited
        // from the parent. Add a dummy state machine to force ServiceSetResolver.java to properly
        // merge the protocol behavior.
//        for (String s : rootFSMs.keySet())
//        {
//            if (!localFSMs.contains(s))
//            {
//                StateMachine sm = new StateMachine();
//                sm.setName(name.substring(0, name.lastIndexOf(".")) + "." + getPBInheritanceString(serviceDef, s, projDefs) );
//                fsmlist.add(sm);
//                Start state = null;
//                org.jts.jsidl.binding.State tmpStart = null;
//
//                for(Object st: rootFSMs.get(s))
//                {
//                    tmpStart = (org.jts.jsidl.binding.State) st;
//                    state = new Start();
//                    state.setStateMachineName(sm.getName());
//                    state.setStateName(tmpStart.getName());
//                    startlist.add(state);
//                }
//            }
//        }
    }
    
    private void recursiveCheckSubStateInheritance(State child, State parent) {
    	// make a new list so we don't corrupt the original data    	
    	List<State> missingStates = new ArrayList<State>();
    	// assume all states are missing till we know better
    	missingStates.addAll(parent.getState());
    	
    	List<State> toAdd = new ArrayList<State>();
    	for(State childSub: child.getState())
    	{
    		for(State parentSub: parent.getState())
    		{
    			if(childSub.getName().equals(parentSub.getName()))
    			{
    				missingStates.remove(parentSub);
    			}
    		}
    	}
    	for(State add: missingStates)
    	{
    		State newState = new State();
    		newState.setName(add.getName());
    		//newState.setInitialState(add.getInitialState());
    		toAdd.add(newState);
    	}
    	
    	child.getState().addAll(toAdd);
    	if(child.getInitialState() == null && parent.getInitialState() != null){
    		child.setInitialState(parent.getInitialState());
    	}
    		
    	for(State childSub: child.getState())
    	{
    		for(State parentSub: parent.getState())
    		{
    			recursiveCheckSubStateInheritance(childSub, parentSub);
    		}
    	}
		
	}
	/**
     * 
     * @param ref - the inherited service that contains the missing state machine.
     * @param toFind - the state machine to be located
     * @param searchList - the list of all service defs in the project.
     * @return - the string needed to complete the state machine reference.
     */
    private String getPBInheritanceString(ServiceDef ref, String toFind, List<ServiceDef> searchList)
    {
    	
        String refID = "";
        if(ref.getReferences() != null && ref.getReferences().getInheritsFrom() != null){
        	refID = ref.getReferences().getInheritsFrom().getId();
            for(ServiceDef def: searchList)
            {
                if(def.getId().equals(refID))
                {
                    for(StateMachine sm: def.getProtocolBehavior().getStateMachine())
                    {
                        if((sm.getName().lastIndexOf('.') != -1 && sm.getName().substring(sm.getName().lastIndexOf('.')+1).equals(toFind)) ||
                                sm.getName().equals(toFind))
                        {
                            return sm.getName();
                        }
                    }
                }
            }
        }
        
        
        return "";
    }
    
    /**
     * Imports the JSIDL and resolves dependencies of the stored JSIDL file.
     * @return a list of all the service definitions needed for the component.
     * @throws Exception 
     */
    private List<ServiceDef> processServices(List<ServiceDef> serviceDefs) throws Exception
    {
        if (serviceDefs.isEmpty())
        {
            final String message = "Error: No service definitions given from CJSIDL conversion..";
            System.out.println(message);
            return null;
        }        

        // construct map from JSIDL entity IDs to Jaxb instances
        Map jaxbObjMap = Util.getObjectMap(serviceDefs);

        // if single file is selected, simply import all declared types,
        // in parent dir in addition to base and client services to ensure that
        // all external references will be available when external references
        // are being resolved
        //if (serviceDefs.size() == 1)
        //{
            if (jaxbObjMap.isEmpty())
            {
                final String message = "Unable to parse " + serviceDefs.get(0).getName();
                throw new Exception(message);
            }

            Map tmpMap = getPossibleParents();            
            
            // get inherited services
            org.jts.jsidl.binding.ServiceDef service =
                    (org.jts.jsidl.binding.ServiceDef) jaxbObjMap.values().iterator().next();

            //  JSIDL XML files.
            String baseKey = null;
            while ((baseKey = getBaseServiceKey(service)) != null)
            {
                service = (org.jts.jsidl.binding.ServiceDef) tmpMap.get(baseKey);

                if (service != null)
                {
                    jaxbObjMap.put(baseKey, service);
                }
                else
                {
                    final String message = "Inherited ServiceDef " + baseKey + " not found (or unparsable).";
                    throw new Exception(message);
                }
            }

            // get client services
            org.jts.jsidl.binding.References ref = service.getReferences();
            List<org.jts.jsidl.binding.ClientOf> clients = null;
            if (ref != null)
            {
                clients = ref.getClientOf();
            }

            if (clients != null)
            {
                for (int ii = 0; ii < clients.size(); ii++)
                {
                    org.jts.jsidl.binding.ClientOf cref = clients.get(ii);
                    String clientKey = cref.getId() + "-" + cref.getVersion();
                    Object value = tmpMap.get(clientKey);
                    if (value != null)
                    {
                        jaxbObjMap.put(clientKey, value);
                    }
                    else
                    {
                        final String message = "client ServiceDef " + clientKey + " not found (or unparsable).";
                        throw new Exception(message);
                    }
                }
            }

            // add all declared types
            Iterator<String> iter = tmpMap.keySet().iterator();

            while (iter.hasNext())
            {
                String key = iter.next();
                Object value = tmpMap.get(key);

                if (value instanceof org.jts.jsidl.binding.DeclaredTypeSet
                        || value instanceof org.jts.jsidl.binding.DeclaredConstSet)
                {
                    jaxbObjMap.put(key, value);
                }
            }
        //}

        // resolve declared type references
        System.out.println("Resolving declared type references....");
        ArrayList<org.jts.jsidl.binding.ServiceDef> toCommit =
                new ArrayList<org.jts.jsidl.binding.ServiceDef>();

        for (Iterator<String> iter2 = jaxbObjMap.keySet().iterator(); iter2.hasNext();)
        {
            String key = iter2.next();
            Object obj = jaxbObjMap.get(key);

            if (obj instanceof org.jts.jsidl.binding.ServiceDef)
            {
                try
                {
                    org.jts.jsidl.binding.ServiceDef sdef =
                            org.jts.gui.importJSIDL.declaredElementsResolution.ServiceDef.resolveDeclaredElements((org.jts.jsidl.binding.ServiceDef) obj, jaxbObjMap);
                    toCommit.add(sdef);
                }
                catch (final ImportException iee)
                {
                    System.out.println("Error while resolving declared type references:\n"
                            + iee.getMessage());
                    throw new RuntimeException(iee);
                }
            }
        }

        // check inherits-from references
        System.out.println("Resolving inherits-from references...");
        for (Iterator<org.jts.jsidl.binding.ServiceDef> iter3 = toCommit.iterator(); iter3.hasNext();)
        {
            org.jts.jsidl.binding.ServiceDef sdef = iter3.next();
            References refs = sdef.getReferences();

            if (refs == null)
            {
                continue;
            }

            final InheritsFrom iref = refs.getInheritsFrom();

            if (iref == null)
            {
                continue;
            }

            String key = iref.getId() + "-" + iref.getVersion();
            if (jaxbObjMap.get(key) == null)
            {
                final String message = "Inherited ServiceDef " + iref.getName() + " v"
                        + iref.getVersion() + " not found (or unparsable).";
                throw new Exception(message);
            }
        }
        // check client-of references
        System.out.println("Resolving client-of references...");
        for (Iterator<org.jts.jsidl.binding.ServiceDef> iter4 = toCommit.iterator(); iter4.hasNext();)
        {
            org.jts.jsidl.binding.ServiceDef sdef = iter4.next();
            References refs = sdef.getReferences();

            if (refs == null)
            {
                continue;
            }

            List<ClientOf> crefs = refs.getClientOf();

            if (crefs == null)
            {
                continue;
            }

            for (final ClientOf cref : crefs)
            {
                String key = cref.getId() + "-" + cref.getVersion();

                if (jaxbObjMap.get(key) == null)
                {
                    final String message = "Client ServiceDef " + cref.getName() + " v"
                            + cref.getVersion() + " not found (or unparsable).";
                    throw new Exception(message);
                }
            }
        }

        String validatingServiceDefName = "";

        System.out.println("Validating protocol behavior...");
        // validate protocol behavior        
        try
        {
            // protocol validator needs a list of all parent service definitions
            List<org.jts.jsidl.binding.ServiceDef> sdefs = new ArrayList<org.jts.jsidl.binding.ServiceDef>();
            sdefs.addAll(toCommit);

            // must keep track of service defs that were validated
            List<org.jts.jsidl.binding.ServiceDef> validatedSdefs = new ArrayList<org.jts.jsidl.binding.ServiceDef>();

            while (sdefs.size() > 0)
            {
                ServiceDef sdef = org.jts.pbValidator.ValidatorUtils.getRootServiceDefInList(sdefs);

                // add any service defs that were found from lookup
                List<ServiceDef> inheritedSdefs = new ArrayList<ServiceDef>();

                inheritedSdefs.add(sdef);
                Util.getInheritedServiceDefList(sdef, toCommit, inheritedSdefs, jaxbObjMap);

                // add any service defs that were already validated but not commited
                inheritedSdefs.addAll(validatedSdefs);

                // keep track of what is being validated in case there is an error
                validatingServiceDefName = sdef.getName();

                // try validate the current service def
                org.jts.pbValidator.Validator validator = new org.jts.pbValidator.Validator();
                validator.validate(inheritedSdefs, sdef.getProtocolBehavior());

                // add current service def to validated list
                validatedSdefs.add(sdef);

                // remove current service def from list so we don't catch it as a root anymore
                sdefs.remove(sdef);
            }
        }
        catch (org.jts.pbValidator.ValidatorException e)
        {
            String results = "";
            for (org.jts.pbValidator.ValidatorResult result : e.getResults())
            {
                results += "Validation Error with service def " + validatingServiceDefName + " : " + result.getPath() + System.getProperty("line.separator");
                results += result.getDescription() + System.getProperty("line.separator");
            }
            System.out.println(results);
            throw new RuntimeException(results);
        }
        System.out.println("Finished validation");     
        return toCommit;    
    }
    
    /**
     * retrieves possible parents from resources folder and project folder.
     * Looks for JSIDL XML in project folder.
     * @throws IOException 
     * @throws URISyntaxException 
     */
    private Map getPossibleParents() throws URISyntaxException, IOException
    {
    	Map ret = new HashMap();
    	
    	// Get all the files from the project path. Will gather all available JSIDL files.
    	List<File> fileList = Util.getFileList(sourcePath.toFile().getParentFile());   
    	List<File> local = Util.getFileList(new File(proj.getLocation().toPortableString()));
		try {
			ret = Util.getObjectMapFromFile(fileList, new ArrayList<ServiceDef>());
			ret.putAll(Util.getObjectMapFromFile(local, new ArrayList<ServiceDef>())); // temporary - for test.
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} 	
    	return ret;
    }
    
    /**
     * Gets the 'key' for a JAXB service def instance's base service def, or null if the service def instance has no
     * base service.  The 'key' is a string consisting of the base service def's ID, and version, connected by a hyphen.
     * @param service The JAXB service def instance in question.
     * @return
     */
    private String getBaseServiceKey(org.jts.jsidl.binding.ServiceDef service) {
        org.jts.jsidl.binding.References ref = service.getReferences();

        if (ref == null) {
            return null;
        }

        org.jts.jsidl.binding.InheritsFrom inh = ref.getInheritsFrom();

        if (inh == null) {
            return null;
        } else {
            return inh.getId() + "-" + inh.getVersion();
        }
    }
    
    /**
     * Sets up the converter and performs the actual file conversion from CJSIDL to JSIDL
     * @return
     */
    private List<Object> convertFilesToJSIDL()
    {
        List<Object> ret = new ArrayList<Object>();
        Injector injector = CjsidlActivator.getInstance().getInjector("org.jts.eclipse.Cjsidl");
        injector.injectMembers(this);        
        ResourceSet set = resourceSetProvider.get(proj);        
        Conversion conv = new Conversion();//injector);
//        Injector injector = Guice.createInjector(new org.jts.eclipse.CjsidlRuntimeModule());
//    	// TODO: the next two lines cause refAttr to break in all files,
//    	// but are required to, strangely enough, resolve those references.
//    	// This is apparently an injector issue within eclipse and needs to be 
//    	// investigated further.
//		org.eclipse.xtext.resource.IResourceFactory resourceFactory = injector.getInstance(org.eclipse.xtext.resource.IResourceFactory.class);
//		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("csd", resourceFactory);
//
//        Conversion conv = new Conversion(injector);
//        XtextResourceSet set = conv.getResourceSet();
        URI uri;
        
        try {
            for(IResource resource: proj.members())
            {
                if(resource.getType() == org.eclipse.core.resources.IResource.FOLDER)
                {
                    recursiveGetResource(resource, set);
                }
                if(resource.getName().endsWith(".csd") || resource.getName().endsWith(".cjsidl"))
                {
                    uri = URI.createFileURI(resource.getLocation().toPortableString());
                    set.createResource(uri);
                    set.getResource(uri, true);
                }
            }
        
            ret = conv.convertToJSIDL(set, outputFolder);        
        } catch (CoreException e) {
	      	IStatus status = new Status(IStatus.ERROR, "org.jts.eclipse", 1, e.getMessage(), e);
        	ErrorDialog.openError(shell, "Import Error", "Import Error", status);
            e.printStackTrace();
        } catch (FileNotFoundException e) {
	      	IStatus status = new Status(IStatus.ERROR, "org.jts.eclipse", 1, e.getMessage(), e);
        	ErrorDialog.openError(shell, "Import Error", "Import Error", status);
            e.printStackTrace();
        } catch (ConversionException e) {
	      	IStatus status = new Status(IStatus.ERROR, "org.jts.eclipse", 1, e.getMessage(), e);
        	ErrorDialog.openError(shell, "Import Error", "Import Error", status);
        	e.printStackTrace();
		}
        return ret;
    }
    
    /**
     * Filters through the folders of a project to add all sub files to the set.
     * @param resource
     * @param set
     */
    private void recursiveGetResource(IResource resource, ResourceSet set) {
        URI uri;
        try {
            for(IResource r: ((IFolder)resource).members())
            {
                if(r.getType() == org.eclipse.core.resources.IResource.FOLDER)
                {
                    recursiveGetResource(r, set);
                }
                if(r.getName().endsWith(".csd") || r.getName().endsWith(".cjsidl"))
                {
                    uri = URI.createFileURI(r.getFullPath().toPortableString());
                    set.createResource(uri);
                    set.getResource(uri, true);
                }
            }
        } catch (CoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    /**
     * File filter to pull out CJSIDL files from project directory.
     * @author vnearing
     *
     */
    private class CjsidlFileFilter implements FileFilter {
        /**
         * @return if a file ends with the correct extension.
         */
        public boolean accept(File f) {
            if (f.isDirectory())
                return true;
            String name = f.getName().toLowerCase();
            return name.endsWith("csd") || name.endsWith("cjsidl");
        }
    }

    
}
