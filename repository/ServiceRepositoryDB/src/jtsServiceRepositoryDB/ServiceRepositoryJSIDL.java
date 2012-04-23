package jtsServiceRepositoryDB;

import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.*;
import org.w3c.dom.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.*;
import java.util.*;
import java.lang.Class.*;



public class ServiceRepositoryJSIDL {
	
	private Document doc = null;
	private boolean haveDoc = false;
	
	/**
	 * This function parses a JSIDL doc into a DOM object
	 * 
	 * @param jsidl fileName with JSIDL
	 */
	public boolean parse(String fileName) 
	throws IOException, SAXException {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			
			/*
			factory.setIgnoringComments(true);
			factory.setIgnoringElementContentWhitespace(true);
			factory.setValidating(false);
			
			SchemaFactory schFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			String schemaSource = ServiceRepositoryIO.getSchema();

			Schema sch = schFactory.newSchema(new StreamSource(new StringReader(schemaSource)));

			factory.setSchema(sch);
			*/

			DocumentBuilder builder = factory.newDocumentBuilder();
			
			doc = builder.parse(new InputSource(fileName));
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	
		haveDoc = true;
		return true;
	}

	
	public String getDocumentURI() {

		if (!haveDoc) { return null; }
		
		Element root = doc.getDocumentElement();
		System.out.println("root id: " + root.getAttribute("id"));
		
		String ret = root.getAttribute("id");

		return ret;
	}
	
	public String getDocumentName() {

		if (!haveDoc) { return null; }
		
		Element root = doc.getDocumentElement();
		System.out.println("root id: " + root.getAttribute("name"));
		
		String ret = root.getAttribute("name");

		return ret;
	}
	
	public String getDocumentVersion() {

		if (!haveDoc) { return null; }
		
		Element root = doc.getDocumentElement();
		System.out.println("root version: " + root.getAttribute("version"));
		
		String ret = root.getAttribute("version");

		return ret;
	}
	
	public String getDocumentDescription() {
		if (!haveDoc) { return null; }
		
		Element root = doc.getDocumentElement();

		// Now, we need to get the children of the root, and look for the first element at the child level that has
		// a description field. This will likely only be true for service_def elements. 
		
		if ((root.getNodeName() == "service_def") || (root.getNodeName() == "service_set")) {
			NodeList nl = root.getChildNodes();
			for (int i = 0; i < nl.getLength(); i++) {
				Node n = nl.item(i);
				if (n.getNodeName() == "description") {
					String desc = n.getFirstChild().getNodeValue();
					return desc;
				}
			}
		}			

		return null;
	}
	
	/**
	 * This function builds a full description from the service file. This includes:
	 * 
	 * Name
	 * URI
	 * Dependencies / Version
	 * Type - service def, message set, or declared_ref
	 *	
	 * @param name
	 * @return
	 */
	public String getFullDescription(String name) {
		StringBuffer description = new StringBuffer(64000);
		
		description.append("<BODY TEXT=#FFFFFF>");
		description.append("\n<H3><u>Name</u></H3>\n");
		description.append(getDocumentName() + "\n\n");
		
		description.append("\n<H3><u>Description</u></H3>\n");
		description.append(getDocumentDescription());
		
		description.append("\n\n<H3><u>URI</u></H3>\n");
		description.append(getDocumentURI());
		description.append("/");
		description.append(getDocumentVersion());
		
		description.append("\n\n<H3><u>Dependencies</u></H3>\n");
		description.append(getDocumentDependencies());

		description.append("</BODY>");
		System.out.println(description.toString());
		
		return description.toString();
	}
	
	
	/**
	 * Returns a string of all document dependencies in the form
	 * URI / Version
	 * 
	 * @return
	 */
	public String getDocumentDependencies() {
		ArrayList<ServiceRepositoryDependency> dep = getDependencies();
		StringBuffer buffer = new StringBuffer(4096);
		int i;
		
		for (i = 0; i < dep.size(); i++) {
			buffer.append(new String(dep.get(i).getURI() + "/" + dep.get(i).getVersion() + "<br>\n"));
		}
		
		return buffer.toString();
	}

	/**
	 * This function parses the JSIDL file and returns an array of URI dependencies 
	 * based either on inheritance or declared types.
	 * @return
	 */
	public ArrayList<ServiceRepositoryDependency> getDependencies() {
		
		if (!haveDoc) return null;
		
		ArrayList<ServiceRepositoryDependency> depList = new ArrayList<ServiceRepositoryDependency>(10);
		
		// First, find all <inherits_from> tags
		NodeList inheritNodes = doc.getElementsByTagName("inherits_from");
		elementToDependency(depList, inheritNodes);
		
		NodeList declaredNodes = doc.getElementsByTagName("declared_type_set_ref");
		elementToDependency(depList, declaredNodes);
		
		return depList;
		
	}

	/**
	 * Takes a JSIDL element, and pulls out the attributes from it that describe the dependency
	 */

	private void elementToDependency(
			ArrayList<ServiceRepositoryDependency> depList,
			NodeList inheritNodes) {
		int i;
		System.out.println("Node = " + inheritNodes.getLength() + "\n");
		for (i = 0; i < inheritNodes.getLength(); i++) {
			Node n = inheritNodes.item(i);
			System.out.println("  Node = " + n.getNodeName() + " " + n.getNodeType());
			System.out.println("  Attr = " + ((Element)n).getAttribute("id"));
			System.out.println("   Ver = " + ((Element)n).getAttribute("version"));
			
			String id, ver;
			id = ((Element)n).getAttribute("id");
			ver = ((Element)n).getAttribute("version");
			
			if ((id != null) && (ver != null)) {
				ServiceRepositoryDependency d = new ServiceRepositoryDependency();
				d.setURI(id);
				d.setVersion(ver);
				depList.add(d);
			}
		}
	}

}
