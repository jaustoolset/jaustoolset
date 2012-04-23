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
package org.jts.eclipse.conversion.cjsidl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBException;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.resource.SaveOptions;
import org.eclipse.xtext.resource.SaveOptions.Builder;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.jts.eclipse.CjsidlStandaloneSetup;
import org.jts.eclipse.JSIDLFileReader;
import org.jts.eclipse.cjsidl.declaredConstSet;
import org.jts.eclipse.cjsidl.declaredConstSetRef;
import org.jts.eclipse.cjsidl.declaredTypeSet;
import org.jts.eclipse.cjsidl.declaredTypeSetRef;
import org.jts.eclipse.cjsidl.jaus;
import org.jts.eclipse.cjsidl.refAttr;
import org.jts.eclipse.cjsidl.serviceDef;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.google.inject.Injector;

/**
 * @author cmessmer
 *	This class is the interface for the converter
 */
public class Conversion {
	
    public static void main(String argv[]) {
    	Conversion conv = new Conversion();
    	if(argv.length < 3){
    		System.out.println("parameters: <function> <input path> <output path>");
    		System.out.println("function options: convertToJSIDL | convertFromJSIDL");
    		System.err.println("Invalid number of parameters(" + argv.length + ") passed to Conversion object. Number of parameters must be at least 3.");
    		return;
    	}
    	String convStr = argv[argv.length-3] + "(" + argv[argv.length-2] + "," + argv[argv.length-1] + ")";
    	if(argv[argv.length-3].equals("convertFromJSIDL")){
    		try {
				conv.convertFromJSIDL(argv[argv.length-2], argv[argv.length-1]);
			} catch (ConversionException e) {
				System.err.println("Error converting JSIDL to CJSIDL: " + e.getMessage());
				System.err.println(convStr);
			}
    	} else if(argv[argv.length-3].equals("convertToJSIDL")){
    		try {
				conv.convertToJSIDL(argv[argv.length-2], argv[argv.length-1]);
			} catch (FileNotFoundException e) {
				System.err.println("Input path error: check your configuration and try again. " + convStr);
			} catch (ConversionException e) {
				System.err.println("Error converting CJSIDL to JSIDL: " + e.getMessage());
				System.err.println(convStr);
			}
    	} else {
    		System.out.println("parameters: <function> <input path> <output path>");
    		System.out.println("function options: convertToJSIDL | convertFromJSIDL");
    		System.err.println("invalid parameters passed to Conversion object." + convStr);
    	}
    } // main

    /**
     * File filter to pull all .csd files out of a folder,
     *  ignoring any .svn or .project files/folders.
     * @author vnearing
     *
     */
    public class CjsidlFileFilter implements FileFilter {
        public boolean accept(File f) {
            if (f.isDirectory()){
                return false;
            }
            String name = f.getName().toLowerCase();
            return name.endsWith("csd") || name.endsWith("cjsidl");
        }
    }
    /**
     * File filter to pull all .jsidl files out of a folder,
     *  ignoring any .svn or .project files/folders.
     * @author vnearing
     *
     */
    public class JsidlFileFilter implements FileFilter {
        public boolean accept(File f) {
            if (f.isDirectory())
                return false;
            String name = f.getName().toLowerCase();
            return name.endsWith("xml");
        }
    }     
	private XtextResourceSet set;

	public final static String SCHEMA_PATH="../org.jts.eclipse.data_1.0/resources/schema/JSIDL_Plus/jsidl_plus.xsd";
	public final static String JTS_SCHEMA_PATH="resources/schema/JSIDL_Plus/jsidl_plus.xsd";
	public final static String DEPLOYED_SCHEMA_PATH="plugins/org.jts.eclipse.data_1.0/resources/schema/JSIDL_Plus/jsidl_plus.xsd";
	protected Injector injector = null;
	
	public static ConversionReferenceHelper referenceHelper = new ConversionReferenceHelper();
	public Conversion(Injector inject){
		set = inject.getInstance(XtextResourceSet.class);
        set.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
		this.injector = inject;
        referenceHelper = new ConversionReferenceHelper();
	}
	public Conversion(){
        CjsidlStandaloneSetup.doSetup();
        Injector injector = new CjsidlStandaloneSetup().createInjectorAndDoEMFRegistration();
        this.injector = injector;
        set = injector.getInstance(XtextResourceSet.class);
        set.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
        referenceHelper = new ConversionReferenceHelper();
	}
	public XtextResourceSet getResourceSet(){
		return set;
	}
	/**
	 * Opens a specified ResourceSet, and then serializes the data for each resource.
	 * @param set - the input resource set
	 * @param outputPath - the location where the output files will be located
	 * @throws ConversionException 
	 * @throws IOException 
	 */
	public void writeResourceFiles(ResourceSet set, String outputPath) throws ConversionException{
		
		// cycle through resources
		for(Resource resource: set.getResources()){
			if(resource.getContents().size() != 1){
				throw new ConversionException("Xtext resource error: Usually due to a " +
						"failed JSIDL conversion. Check your JSIDL and try again.");
			}
			// print errors to logger, if there are any
			if(resource.getErrors().size() > 0){
				for(Diagnostic diag: resource.getErrors()){
					String msg = "Resource Error:  " + diag.getLine() + ":" + diag.getColumn() + " Location:" +
					diag.getLocation() + "  Message:" + diag.getMessage();
	                Logger.getLogger("CJSIDL").log(Level.SEVERE,
	                msg);
//	                throw new ConversionException(msg);
				}
				// don't try to serialize data that has errors
				continue;
			}
			// attempt to write data to file
			OutputStream outputStream=null;
			try {
				String path = "";
				if(resource.getURI().toString().substring(7, 8).equals(":")){
					// windows path
					path = resource.getURI().toString().replace("file:/", "");
				} else{
					path = resource.getURI().toString().replace("file:", "");
				}
				outputStream = new java.io.FileOutputStream(new File(path));
				Map<Object, Object> map = new HashMap<Object, Object>();
				Builder builder = SaveOptions.newBuilder();
				builder.format();
				SaveOptions options = builder.getOptions();
				options.addTo(map);
				try {
					EcoreUtil.resolveAll(resource);
					resource.save(outputStream, map);
					outputStream.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					try {
						outputStream.flush();
						outputStream.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					e.printStackTrace();
					if(resource.getWarnings().size() > 0){
						for(Diagnostic diag: resource.getWarnings()){
							String msg = "Resource Warning:  " + diag.getLine() + ":" + diag.getColumn() + " Location:" +
							diag.getLocation() + "  Message:" + diag.getMessage();
			                Logger.getLogger("CJSIDL").log(Level.SEVERE,
			                msg);
			                throw new ConversionException(msg);
						}
						
					}
					if(resource.getErrors().size() > 0){
						for(Diagnostic diag: resource.getErrors()){
							String msg = "Resource Error:  " + diag.getLine() + ":" + diag.getColumn() + " Location:" +
							diag.getLocation() + "  Message:" + diag.getMessage();
			                Logger.getLogger("CJSIDL").log(Level.SEVERE,
			                msg);
			                throw new ConversionException(msg);
						}
						
					}

				}
			} catch (FileNotFoundException e) {
                throw new ConversionException(e.getMessage());
			}
		}		
	}
	
	/**
	 * Attempts to marshal all JAXB bindings for resources that have been loaded
	 * @throws ConversionException 
	 * @throws IOException 
	 */
	public void serializeJAXB(String outputpath) throws ConversionException{
		File tmpfile = new File(outputpath);
		if(tmpfile.isDirectory() && !tmpfile.exists()){
			boolean success = tmpfile.mkdirs();
			if(!success){
				throw new ConversionException("Failed to make new directory. Path is either bad or this application" +
						"doesn't have the permissions necessary.  Check your path and try again.");
			}
		}
		
		for(String id: referenceHelper.keySet()){
			String newFileName = referenceHelper.get(id).emfResource.getURI().toString();
			newFileName = outputpath + newFileName.substring(newFileName.lastIndexOf("/"), newFileName.length());
			newFileName = newFileName.substring(0, newFileName.length()-4) + ".xml";
			java.io.FileWriter fw;
			try {
				fw = new java.io.FileWriter(new File(newFileName));
				javax.xml.bind.JAXB.marshal(referenceHelper.get(id).jaxbBinding, fw);
				fw.close();
			} catch (IOException e) {
				throw new ConversionException("Failed to write file for JSIDL " + id + ": " + e.getMessage());
			}

		}
	}

	public java.util.List<Object> convertToJSIDL(java.util.List<File> files, String outputPath) throws ConversionException, FileNotFoundException{

        if(files == null || files.isEmpty()){
        	throw new ConversionException("File list is null or empty.");
        }
		updateSet(files, set);
		if(!checkDependencies(set)){
			throw new ConversionException("Conversion Failed: csd dependencies not satisfied!  All dependencies should exist in the input folder.");
		}
		return convertToJSIDL(set, outputPath);
	}
	
	private boolean checkDependencies(XtextResourceSet rs) {
		boolean success = true;
		EList<Resource> resourceList = rs.getResources();
		for(Resource res: resourceList){
			if(res.getContents().get(0) instanceof jaus){
				if(((jaus)res.getContents().get(0)).getSet() instanceof serviceDef){
					serviceDef sd = ((serviceDef)((jaus)res.getContents().get(0)).getSet());
					if(sd.getRefs()!= null && sd.getRefs().getRefInherit()!= null 
							&& sd.getRefs().getRefInherit().getImportedNamespace() == null){
						success = false;
					}
					if(sd.getRefs()!= null && sd.getRefs().getRefClient()!= null){
						for(refAttr ref: sd.getRefs().getRefClient()){
							if(ref.getImportedNamespace() == null){
								success = false;
								break;
							}
						}
					}
					if(sd.getTypeSet() != null && sd.getTypeSet().getDeclaredTypeSetRef() != null ){
						for(declaredTypeSetRef set: sd.getTypeSet().getDeclaredTypeSetRef()){
							if(set.getImportedNamespace() == null){
								success = false;
								break;
							}
						}
					}
					if(sd.getTypeSet() != null && sd.getTypeSet().getDeclaredConstSetRef() != null ){
						for(declaredConstSetRef set: sd.getTypeSet().getDeclaredConstSetRef()){
							if(set.getImportedNamespace() == null){
								success = false;
								break;
							}
						}
					}
					
				} else if(((jaus)res.getContents().get(0)).getSet() instanceof declaredTypeSet){
					declaredTypeSet ts = ((declaredTypeSet)((jaus)res.getContents().get(0)).getSet());
					
					if(ts != null && ts.getDeclaredTypeSetRef() != null ){
						for(declaredTypeSetRef set: ts.getDeclaredTypeSetRef()){
							if(set.getImportedNamespace() == null){
								success = false;
								break;
							}
						}
					}
					if(ts != null && ts.getDeclaredConstSetRef() != null ){
						for(declaredConstSetRef set: ts.getDeclaredConstSetRef()){
							if(set.getImportedNamespace() == null){
								success = false;
								break;
							}
						}
					}

				} else if(((jaus)res.getContents().get(0)).getSet() instanceof declaredConstSet){
					declaredConstSet cs = ((declaredConstSet)((jaus)res.getContents().get(0)).getSet());
					if(cs != null && cs.getDeclaredConstSetRef() != null){
						for(declaredConstSetRef set: cs.getDeclaredConstSetRef()){
							if(set.getImportedNamespace() == null){
								success = false;
								break;
							}
						}
					}
				}
			}
			if(success == false){
				break;
			}
		}
		
		return success;
	}
	/**
	 * Converts all cjsidl files at a location to JSIDL data bindings
	 * @param path - the path to search, or a single file if it has no dependencies
	 * @return - a list of JSIDL bindings 
	 * @throws ConversionException
	 * @throws FileNotFoundException 
	 */
	public java.util.List<Object> convertToJSIDL(String path, String outputPath) throws ConversionException, FileNotFoundException{
		List<File> filelist = new ArrayList<File>();
		File folder = new File(path);
		if(folder.isDirectory() && folder.exists()){
	        File[] files = folder.listFiles(new CjsidlFileFilter());
			filelist.addAll(Arrays.asList(files));
		} else if(folder.isFile() && folder.exists()){
			filelist.add(folder);
		} else {
			throw new ConversionException("conversion location \"" + path + "\" is not a valid conversion target.");			
		}

		return convertToJSIDL(filelist, outputPath);
	}
	/**
	 * Converts a ResourceSet to a list of JAXB data bindings
	 * @param set - the input resource set
	 * @return - the corresponding bindings
	 * @throws FileNotFoundException 
	 * @throws ConversionException 
	 */
	public java.util.List<Object> convertToJSIDL(ResourceSet set, String outputPath) throws FileNotFoundException, ConversionException{
		java.util.List<Object> bindings = new ArrayList<Object>();
		EList<Resource> resources = set.getResources();
		for(Resource res: resources){
			EObject eobject = res.getContents().get(0);
			String id = null;
			if(eobject instanceof org.jts.eclipse.cjsidl.jaus)
			{
				EObject objSet = ((org.jts.eclipse.cjsidl.jaus)eobject).getSet();
				
				if(objSet instanceof org.jts.eclipse.cjsidl.declaredTypeSet){
					//org.jts.eclipse.cjsidl.declaredTypeSet){
					org.jts.jsidl.binding.DeclaredTypeSet bindingset = 
						DeclaredTypeSet.convert( (org.jts.eclipse.cjsidl.declaredTypeSet)objSet);
					id = bindingset.getId();
					referenceHelper.setBinding(id, bindingset);
					referenceHelper.setResource(id, res);

					bindings.add(bindingset);

				} else if(objSet instanceof org.jts.eclipse.cjsidl.declaredConstSet){
					//org.jts.eclipse.cjsidl.declaredTypeSet){
					org.jts.jsidl.binding.DeclaredConstSet bindingset = 
						DeclaredConstSet.convert( (org.jts.eclipse.cjsidl.declaredConstSet)objSet);
					id = bindingset.getId();
					referenceHelper.setBinding(id, bindingset);
					referenceHelper.setResource(id, res);
					
					bindings.add(bindingset);
				} else if(objSet instanceof org.jts.eclipse.cjsidl.serviceDef){
					org.jts.jsidl.binding.ServiceDef bindingset = 
						ServiceDef.convert( (org.jts.eclipse.cjsidl.serviceDef)objSet);
					id = bindingset.getId();
					referenceHelper.setBinding(id, bindingset);
					referenceHelper.setResource(id, res);
					bindings.add(bindingset);
				}
			}		

		}
		serializeJAXB(outputPath);
		return bindings;
	}
	
	public XtextResourceSet convertFromJSIDL(String path, String outputPath) throws ConversionException{
		List<File> filelist = new ArrayList<File>();
		File folder = new File(path);
		if(folder.isDirectory() && folder.exists()){
	        File[] files = folder.listFiles(new JsidlFileFilter());
			filelist.addAll(Arrays.asList(files));
		} else if(folder.isFile() && folder.exists()){
			filelist.add(folder);
		} else {
			throw new ConversionException("conversion location \"" + path + "\" is not a valid conversion target.");			
		}
		XtextResourceSet tmpset = convertFromJSIDL(filelist, outputPath, set);
		writeResourceFiles(tmpset, outputPath);
		
		return tmpset; 
	}
	
	public XtextResourceSet convertFromJSIDL(Object input, String outputPath) throws ConversionException{
		String id = null;
		if(input instanceof ServiceDef){
			id = ((org.jts.jsidl.binding.ServiceDef)input).getId();
			id = id.replace(":", ".");
			referenceHelper.setBinding(id, input);
		} else if(input instanceof DeclaredTypeSet){
			id = ((org.jts.jsidl.binding.DeclaredTypeSet)input).getId();
			id = id.replace(":", ".");
			referenceHelper.setBinding(id, input);
		} else if(input instanceof DeclaredConstSet){
			id = ((org.jts.jsidl.binding.DeclaredConstSet)input).getId();
			id = id.replace(":", ".");
			referenceHelper.setBinding(id, input);
		} else{
			throw new ConversionException("Object type \"" + input.getClass().getName() + "\" not supported for Conversion");
		}
		// since we don't have a file name, we'll use the id
		String basename = id; 
		String newname = basename.replace(".", "_") + ".csd";
		
		URI uri = URI.createFileURI(outputPath + "/" +  newname);
		Resource resource = set.createResource(uri);

		referenceHelper.setResource(id, resource);

		
		for(String tmpid: referenceHelper.keySet()){
			if(!resourceExists(tmpid, set)){
				referenceHelper.setResourceEObject(tmpid, convertObject(referenceHelper.getBinding(tmpid), set));		
			}
		}
		writeResourceFiles(set, outputPath);
		return set;		
	}

	/**
	 * Converts a set of JSIDL files to CJSIDL.  New files are placed in the 
	 * outputPath with the new ".csd" extension.
	 * @param files - list of JSIDL files used for the service.
	 * @param outputPath - location to put the converted files.
	 * @param resourceSet 
	 * @throws ConversionException 
	 */
	public XtextResourceSet convertFromJSIDL(java.util.List<java.io.File> files, String outputPath, XtextResourceSet resourceSet) throws ConversionException{
		unmarshalFiles(files, outputPath, resourceSet);
		
		for(String id: referenceHelper.keySet()){
			if(!resourceExists(id, resourceSet)){
				referenceHelper.setResourceEObject(id, convertObject(referenceHelper.getBinding(id), resourceSet));		
			}
		}
		return resourceSet;
	}

	/**
	 * reads in the JSIDL files and maps the data bindings to the ID for each top level object.
	 * @param files - the input list of JSIDL files.  The assumption is that this list is complete
	 * and has been validated.
	 * @param resourceSet 
	 * @throws ConversionException 
	 */
	private void unmarshalFiles(List<File> files, String outputPath, XtextResourceSet resourceSet) throws ConversionException {
		
		for(File file: files){
			JSIDLFileReader reader=null;
			Object root = null;
			String id = "";
			String schema = null;
			if(new File(SCHEMA_PATH).exists()){
				schema = SCHEMA_PATH;
			} else if(new File(JTS_SCHEMA_PATH).exists()){
				schema = JTS_SCHEMA_PATH;
			} else if(new File(DEPLOYED_SCHEMA_PATH).exists()){
				schema = DEPLOYED_SCHEMA_PATH;
			} else {
				
			}
			
			try {
				reader = new JSIDLFileReader(file.getCanonicalPath(), schema);
			} catch (JAXBException e) {
				throw new ConversionException(file.getPath() + ": " + e.getMessage());
			} catch (SAXParseException e) {
				throw new ConversionException(file.getPath() + ": " + e.getMessage());
			} catch (IOException e) {
				throw new ConversionException(file.getPath() + ": " + e.getMessage());
			} catch (SAXException e) {
				throw new ConversionException(file.getPath() + ": " + e.getMessage());
			}
			try {
				root = reader.getRootElement();
			} catch (JAXBException e) {
				throw new ConversionException(file.getPath() + ": " + e.getMessage());
			}
			if(root instanceof org.jts.jsidl.binding.DeclaredTypeSet){
				id = ((org.jts.jsidl.binding.DeclaredTypeSet)root).getId();
			} else if(root instanceof org.jts.jsidl.binding.ServiceDef){
				id = ((org.jts.jsidl.binding.ServiceDef)root).getId();
			} else if(root instanceof org.jts.jsidl.binding.DeclaredConstSet){
				id = ((org.jts.jsidl.binding.DeclaredConstSet)root).getId();
			}
			// convert id to use . instead of : and no quotes or double quotes
			id = id.replace(":", ".");
			
			referenceHelper.setBinding(id, root);
			String basename = file.getName(); 
			int extensionPosition = basename.lastIndexOf(".");
			String newname = basename.substring(0,extensionPosition) + ".csd";
			
			URI uri = URI.createFileURI(outputPath + "/" +  newname);
			XtextResource resource = (XtextResource) resourceSet.createResource(resourceSet.getURIConverter().normalize(uri));
			//resource.setserializer(injector.getInstance(serializer.class));
//			Resource resource = resourceSet.createResource(uri);
			referenceHelper.setResource(id, resource);
			
		

		}
		
	}

	/**
	 * Recursive function designed to find dependencies and make sure they
	 * have been processed first, so that references to objects in those files
	 * will be valid.
	 * @param root - the root node of the JSIDL data binding
	 * @return - a CJSIDL object corresponding to the input node.
	 * @throws ConversionException 
	 */
	private EObject convertObject(Object root, XtextResourceSet resourceSet) throws ConversionException {
		EObject result = null;

		String id = "";
		if(root instanceof org.jts.jsidl.binding.DeclaredTypeSet){
			id = ((org.jts.jsidl.binding.DeclaredTypeSet)root).getId();
		} else if(root instanceof org.jts.jsidl.binding.ServiceDef){
			id = ((org.jts.jsidl.binding.ServiceDef)root).getId();
		} else if(root instanceof org.jts.jsidl.binding.DeclaredConstSet){
			id = ((org.jts.jsidl.binding.DeclaredConstSet)root).getId();
		} else {
			throw new ConversionException("Invalid object type used in conversion: " + root.getClass().getName());
		}
		id = id.replace(":", ".");
		if(!resourceExists(id, resourceSet)){
			java.util.List<Object> referencedObjects = getReferencedObjects(root);
			for(Object tmpobj:referencedObjects){
				String refID = null;
				if(tmpobj instanceof org.jts.jsidl.binding.DeclaredTypeSet){
					refID = ((org.jts.jsidl.binding.DeclaredTypeSet)tmpobj).getId();
				} else if(tmpobj instanceof org.jts.jsidl.binding.ServiceDef){
					refID = ((org.jts.jsidl.binding.ServiceDef)tmpobj).getId();
				} else if(tmpobj instanceof org.jts.jsidl.binding.DeclaredConstSet){
					refID = ((org.jts.jsidl.binding.DeclaredConstSet)tmpobj).getId();
				}
				refID = refID.replace(":", ".");

				if(!resourceExists(refID, resourceSet)){
					referenceHelper.setResourceEObject(refID, convertObject(tmpobj, resourceSet));
				}
			}
			result = createResource(root);
		}
		return result;
	}

	/**
	 * Takes a root JSIDL binding and converts it into a CJSIDL EMF object
	 * @param root - the root node for a JSIDL binding
	 * @return - the corresponding CJSIDL EMF object
	 * @throws ConversionException 
	 */
	private EObject createResource(Object root) throws ConversionException {
		EObject result = null;
		
		if(root instanceof org.jts.jsidl.binding.DeclaredTypeSet){
			result = DeclaredTypeSet.convert((org.jts.jsidl.binding.DeclaredTypeSet)root);
		} else if(root instanceof org.jts.jsidl.binding.ServiceDef){
			result = ServiceDef.convert((org.jts.jsidl.binding.ServiceDef)root);
		} else if(root instanceof org.jts.jsidl.binding.DeclaredConstSet){
			result = DeclaredConstSet.convert((org.jts.jsidl.binding.DeclaredConstSet)root);
		}
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		jaus jausroot = factory.createjaus();
		jausroot.setSet(result);
		
		return jausroot;
	}

	/**
	 * Retrieves a list of files that are referenced by the input object
	 * @param root - root element of the xml document, should be a DeclaredTypeSet or a ServiceDef.
	 * @return - a list of referenced files.  empty list if not a DeclaredTypeSet or a ServiceDef.
	 * @throws ConversionException 
	 */
	private List<Object> getReferencedObjects(Object root) throws ConversionException {
		List<String> ids = new ArrayList<String>();
		List<Object> objects = new ArrayList<Object>();
		
		if(root instanceof org.jts.jsidl.binding.DeclaredTypeSet){
			List<org.jts.jsidl.binding.DeclaredConstSetRef> constrefs = 
				((org.jts.jsidl.binding.DeclaredTypeSet)root).getDeclaredConstSetRef();
			if(constrefs != null){
				for(org.jts.jsidl.binding.DeclaredConstSetRef ref:constrefs){
					ids.add(ref.getId());
				}
			}
			List<org.jts.jsidl.binding.DeclaredTypeSetRef> typerefs = 
				((org.jts.jsidl.binding.DeclaredTypeSet)root).getDeclaredTypeSetRef();
			if(typerefs != null){
				for(org.jts.jsidl.binding.DeclaredTypeSetRef ref:typerefs){
					ids.add(ref.getId());
				}
			}			
		} else if(root instanceof org.jts.jsidl.binding.ServiceDef){
			org.jts.jsidl.binding.DeclaredConstSet constset = 
				((org.jts.jsidl.binding.ServiceDef)root).getDeclaredConstSet();
			if(constset != null){
				List<org.jts.jsidl.binding.DeclaredConstSetRef> constrefs = 
					constset.getDeclaredConstSetRef();
				for(org.jts.jsidl.binding.DeclaredConstSetRef ref:constrefs){
					ids.add(ref.getId());
				}
			}

			org.jts.jsidl.binding.DeclaredTypeSet typeset = 
				((org.jts.jsidl.binding.ServiceDef)root).getDeclaredTypeSet();
			if(typeset != null){
				List<org.jts.jsidl.binding.DeclaredTypeSetRef> typerefs = 
					typeset.getDeclaredTypeSetRef();
				for(org.jts.jsidl.binding.DeclaredTypeSetRef ref:typerefs){
					ids.add(ref.getId());
				}
			}
			if(((org.jts.jsidl.binding.ServiceDef)root).getReferences() != null){
				List<org.jts.jsidl.binding.ClientOf> clientrefs = ((org.jts.jsidl.binding.ServiceDef)root).getReferences().getClientOf();
				if(clientrefs != null){
					for(org.jts.jsidl.binding.ClientOf client: clientrefs){
						ids.add(client.getId());
					}
				}
				if(((org.jts.jsidl.binding.ServiceDef)root).getReferences().getInheritsFrom()!= null){
					ids.add(((org.jts.jsidl.binding.ServiceDef)root).getReferences().getInheritsFrom().getId());
				}
			}
		} 
		
		for(String id: ids){
			id = id.replace(":", ".");
			objects.add(referenceHelper.getBinding(id));
		}
		return objects;
	}


	/**
	 * Determines if a resource already exists with the given ID
	 * @param id - ID for the set
	 * @return - true if resource already exists
	 */
	public boolean resourceExists(String id, XtextResourceSet resourceSet) {
		EList<Resource> tmpresources = resourceSet.getResources();
		for(Resource tmpresource: tmpresources){
			if(tmpresource.getContents() == null || tmpresource.getContents().size() != 1){
				continue;
			}
			EObject tmpobj = tmpresource.getContents().get(0);
			if(tmpobj instanceof org.jts.eclipse.cjsidl.jaus){
				EObject objSet = ((org.jts.eclipse.cjsidl.jaus)tmpobj).getSet();
				String tmpid="";
				if(objSet instanceof org.jts.eclipse.cjsidl.declaredTypeSet){
					tmpid = ((org.jts.eclipse.cjsidl.declaredTypeSet)objSet).getName();
				} else if(objSet instanceof org.jts.eclipse.cjsidl.declaredConstSet){
					tmpid = ((org.jts.eclipse.cjsidl.declaredConstSet)objSet).getName();
				} else if(objSet instanceof org.jts.eclipse.cjsidl.serviceDef){
					tmpid = ((org.jts.eclipse.cjsidl.serviceDef)objSet).getName();
				}
				if(tmpid.equals(id)){
					if(referenceHelper.get(id).emfResource != null && referenceHelper.get(id).emfResource.getContents().size() == 1){
						return true;
					}
				}
			}
		}
		return false;
	}


    /**
     * Parse a given file to find all of the their parent URI's and 
     *  compares it to a list of URI's already added to the resource set.
     *  
     *  
     *  Updates the filesAdded list if the URI is going to be added to
     *      the resource set as well.
     * @param filesAdded - list of already imported URI's.
     * @param f - file to be parsed.
     * @return false if the file requires a URI not already in the list,
     *          true if all required URI's are in the list. 
     * @throws ConversionException 
     */
    private boolean hasParents(List<String> filesAdded, File f) throws ConversionException
    {
        String URI = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader(f));
            String str;
            while((str = in.readLine())!= null)
            {
                // The current file's URI will be the first instance of these keywords.
                if(str.contains("id") && str.contains("version") && URI.equalsIgnoreCase(""))
                {
                    URI = str.substring(str.indexOf("=")+1, str.indexOf(","));
                    URI= URI.replaceAll(" ", "");
                }
                // Find the keywords for importing a service definition.
                else if(str.contains("inherits_from") && str.contains("import") )
                {                    
                    str = str.substring(str.indexOf("import")+6, str.indexOf(" as"));
                    str = str.replaceAll(" ", "");
                    if(!uriInList(filesAdded,str))
                    {
                        return false;
                    }
                }
                // Find the keywords for importing a typeset or constant set.
                else if(str.contains("using"))
                {
                    if(str.contains("typeset"))
                    {
                        str = str.substring(str.indexOf("typeset")+7, str.indexOf(" as"));
                        str = str.replaceAll(" ", "");
                        if(!uriInList(filesAdded,str))
                        {
                            return false;
                        }
                    }
                    else if(str.contains("constants"))
                    {
                        str = str.substring(str.indexOf("constants")+9, str.indexOf(" as"));
                        str = str.replaceAll(" ", "");
                        if(!uriInList(filesAdded,str))
                        {
                            return false;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new ConversionException("failed while searching for referenced definition: " + URI.toString() + ": " + e.getMessage());
        } catch (IOException e) {
            throw new ConversionException("failed while searching for referenced definition: " + URI.toString() + ": " + e.getMessage());
        }
        // If it's made it to the end of the file, all URI's were found. Update list and return true.
        filesAdded.add(URI);
        return true;
    }
    /**
     * Helper function to find a URI in a list of strings.
     * @param list - list of URI's.
     * @param str - URI to be found.
     * @return true if found, false if it does not exist.
     */
    private boolean uriInList(List<String> list, String str)
    {
        for(String s: list)
        {
            if(s.equalsIgnoreCase(str))
            {
                return true;
            }
        }
        return false;
    }
    
    private void updateSet(List<File> inputfiles, XtextResourceSet set) throws ConversionException
    {        
        List<String> filesAdded = new ArrayList<String>(); // URI's of files already added to set.
        List<File> waitList = inputfiles; // Files who haven't been added to the resourceSet yet.
        List<File> newWaitList = new ArrayList<File>(); // Holder for files found in the current iteration of waitList that need to continue to wait.
        
        URI uri;
        boolean dependencyFailed = false;
        
        while(waitList.size() != 0 && !dependencyFailed)
        {
            for (File f : waitList) { 
                // Check to see if all dependencies are accounted for.
                if(hasParents(filesAdded, f))
                {
                    uri = URI.createFileURI(f.getPath());
                    set.createResource(uri);
                    set.getResource(uri, true);
                }
                else
                {
                    newWaitList.add(f);
                }
            }
            if(waitList.size() == newWaitList.size() && newWaitList.size() != 0){
            	dependencyFailed = true;
            	throw new ConversionException("Conversion Failed: Dependencies not resolved. All dependencies need to be in the project.");
            }
            waitList = newWaitList;
            newWaitList = new ArrayList<File>();
        }
    }

}
