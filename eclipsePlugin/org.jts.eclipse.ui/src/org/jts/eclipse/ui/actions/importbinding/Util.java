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
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.jts.eclipse.ui.internal.CjsidlActivator;
import org.jts.jsidl.binding.ServiceDef;
import org.osgi.framework.Bundle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class Util {
//	private static final String SCHEMA = "resources/schema/JSIDL_Plus/jsidl_plus.xsd";
	public final static String SCHEMA_PATH="../org.jts.eclipse.data_1.0/resources/schema/JSIDL_Plus/jsidl_plus.xsd";
	public final static String JTS_SCHEMA_PATH="resources/schema/JSIDL_Plus/jsidl_plus.xsd";
	public final static String DEPLOYED_SCHEMA_PATH="plugins/org.jts.eclipse.data_1.0/resources/schema/JSIDL_Plus/jsidl_plus.xsd";
	
	public static Map getObjectMap(List<ServiceDef> defList) {
        Map objMap = new HashMap();

        for (int ii = 0; ii < defList.size(); ii++) {
            ServiceDef def = defList.get(ii);
            //final String fileName = file.toString();

           objMap.put(def.getId() + "-" + def.getVersion(), def);
        }
        return objMap;
    }
	
	/**
     * Filter jsidl files, unmarshal and place in a Map.
     * @param fileList list of JSIDL XML files containing service sets
     * @return A Map from service set ID/version strings to JAXB instances representing those service sets.
	 * @throws JAXBException 
	 * @throws SAXException 
     */
	public static Map getObjectMapFromFile(List<File> fileList, List<ServiceDef> tmp) throws JAXBException, SAXException {
		Map objMap = new HashMap();
		Document doc = null;
		DocumentBuilder db = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		Unmarshaller um = null;

		// Set up the unmarshaller with the schema included with the code
		// generator.
		try {
			JAXBContext jc = JAXBContext.newInstance("org.jts.jsidl.binding");
			um = jc.createUnmarshaller();
			SchemaFactory sf = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

			Bundle bundle = CjsidlActivator.getInstance().getBundle();
			String schema_loc;
			if(new File(SCHEMA_PATH).exists()){
				schema_loc = SCHEMA_PATH;
			} else if(new File(JTS_SCHEMA_PATH).exists()){
				schema_loc = JTS_SCHEMA_PATH;
			} else if(new File(DEPLOYED_SCHEMA_PATH).exists()){
				schema_loc = DEPLOYED_SCHEMA_PATH;
			} else {
				throw new Exception("Unable to find the schema path for jsidl_plus.xsd: " + (new File(SCHEMA_PATH).getAbsolutePath()) + "\n\t" +
						(new File(JTS_SCHEMA_PATH).getAbsolutePath()) + "\n\t" +
						(new File(DEPLOYED_SCHEMA_PATH).getAbsolutePath()));
			}
//			IPath path = new Path(schema_loc);
//			URL schemaUrl = FileLocator.find(bundle, path,
//					Collections.EMPTY_MAP);
//			File schemaFile = new File(FileLocator.toFileURL(schemaUrl).toURI());
			File schemaFile = new File(schema_loc);
			Schema schema = sf.newSchema(schemaFile);
			um.setSchema(schema);

			// Try parsing each file.

			db = dbf.newDocumentBuilder();

			for (int ii = 0; ii < fileList.size(); ii++) {
				File file = fileList.get(ii);
				final String fileName = file.toString();

				doc = db.parse(file);

				Element root = doc.getDocumentElement();

				if (root.getAttribute("xmlns").equals("urn:jaus:jsidl:1.0")) {

					Object o = um.unmarshal(file);
					objMap.put(
							root.getAttribute("id") + "-"
									+ root.getAttribute("version"), o);
					if (o instanceof ServiceDef) {
						tmp.add((ServiceDef) o);
					}

				}
			}
		} catch (JAXBException jaxbe) {
			Logger.getLogger(ImportServiceSet.class.getName()).log(
					Level.SEVERE, jaxbe.getMessage(), jaxbe);
			throw jaxbe;
		} catch (SAXException saxe) {
			Logger.getLogger(ImportServiceSet.class.getName()).log(
					Level.SEVERE, saxe.getMessage(), saxe);
			throw saxe;
		} catch (URISyntaxException e) {
			Logger.getLogger(ImportServiceSet.class.getName()).log(
					Level.SEVERE, e.getMessage(), e);
		} catch (IOException e) {
			Logger.getLogger(ImportServiceSet.class.getName()).log(
					Level.SEVERE, e.getMessage(), e);
		} catch (ParserConfigurationException e) {
			Logger.getLogger(ImportServiceSet.class.getName()).log(
					Level.SEVERE, e.getMessage(), e);
		} catch (Exception e) {
			Logger.getLogger(ImportServiceSet.class.getName()).log(
					Level.SEVERE, e.getMessage(), e);
		}
		return objMap;
    }
	
	/**
     * Returns a List of Files under the specified path whose name ends with the extension '.xml' or '.jsidl'.
     * If path refers to a single file, returns a list containing one element-the file referred to by path itself,
     * if that file's name ends with extension '.xml' or '.jsidl'.
     */
    public static List<File> getFileList(File path) {
        if (path.isFile()) {
            List<File> list = new java.util.ArrayList<File>();
            if (path.getName().endsWith(".xml") || path.getName().endsWith(".jsidl")) {
                list.add(path);
            }
            return list;
        } else {
            // recursively searches through files under directory at path, for files ending with .xml or .jsidl.
            // note the 'TrueFileFilter' is being provided as the filter for subdirectories-TrueFileFilter returns
            // true for every subdirectory, so the search will be fully recursive.
            return new ArrayList<File>(FileUtils.listFiles(path,
                    new SuffixFileFilter(new String[]{".xml", ".jsidl"}), TrueFileFilter.INSTANCE));
        }
    }
    
	public static void getInheritedServiceDefList( ServiceDef serviceDef, List<org.jts.jsidl.binding.ServiceDef> sdefs, Map serviceMap )
	{
		if( sdefs == null )
		{
			sdefs = new ArrayList<ServiceDef>();
		}

		if( serviceDef != null )
		{
			//else if( obj instanceof org.jts.jsidl.binding.ServiceDef )
			//{				
				// recurse through referenced service defs to find all other protocol behaviors along inheritance chain
				if( serviceDef.getReferences() != null && serviceDef.getReferences().getInheritsFrom() != null )
				{
					// query the database for the reference
					String id = serviceDef.getReferences().getInheritsFrom().getId();
					String version = serviceDef.getReferences().getInheritsFrom().getVersion();
	
					//com.u2d.generated.ServiceDef genServiceDef =  org.jts.gui.JAXBtoJmatter.ServiceDef.lookupServiceDef( id, version );
	
					ServiceDef genServiceDef = (ServiceDef)serviceMap.get(id + "-" + version);
					if( genServiceDef != null )
					{
						sdefs.add( genServiceDef );
	
						// recurse through chain
						getInheritedServiceDefList( genServiceDef, sdefs, serviceMap );
					}
				}
			//}
		}
	}
	
	public static void getInheritedServiceDefList( ServiceDef serviceDef, List<org.jts.jsidl.binding.ServiceDef> lookupSdefs, List<org.jts.jsidl.binding.ServiceDef> sdefs, Map serviceMap )
	{
		if( sdefs == null )
		{
			sdefs = new ArrayList<org.jts.jsidl.binding.ServiceDef>();
		}

		if( serviceDef != null )
		{
			//if( obj instanceof org.jts.jsidl.binding.ServiceDef )
			//{				
				// recurse through referenced service defs to find all other protocol behaviors along inheritance chain
				if( serviceDef.getReferences() != null && serviceDef.getReferences().getInheritsFrom() != null )
				{
					// query the database for the reference
					String id = serviceDef.getReferences().getInheritsFrom().getId();
					String version = serviceDef.getReferences().getInheritsFrom().getVersion();
	
					//com.u2d.generated.ServiceDef genServiceDef =  org.jts.gui.JAXBtoJmatter.ServiceDef.lookupServiceDef( id, version );
					ServiceDef genServiceDef = (ServiceDef) serviceMap.get(id + "-" + version);
					if( genServiceDef != null )
					{
						sdefs.add( genServiceDef );
	
						// recurse through chain
						getInheritedServiceDefList( genServiceDef, sdefs, serviceMap );
					}
					// desired sdef may be part of the lookup list as well
					else
					{
						for( ServiceDef lookupSdef : lookupSdefs )
						{
							if( lookupSdef.getId().compareTo( id ) == 0 && lookupSdef.getVersion().compareTo( version ) == 0 )
							{
								sdefs.add( lookupSdef );
								
								// recurse through chain
								getInheritedServiceDefList( lookupSdef, lookupSdefs, sdefs, serviceMap );
							}
						}
					}
				}
			//}
		}
	}
}
