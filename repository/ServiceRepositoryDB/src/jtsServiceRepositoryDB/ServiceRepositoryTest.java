package jtsServiceRepositoryDB;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import org.xml.sax.SAXException;

public class ServiceRepositoryTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i;
		
		// Test createZipFile();
		/*
		ArrayList<ServiceRepositoryDependency> depList = null;
		
		try {
			depList = ServiceRepositoryIO.getAllDependencies(depList, "urn:jaus:jss:core:Management", "1.0");
			
			ServiceRepositoryDependency d = new ServiceRepositoryDependency();
			d.setURI("urn:jaus:jss:core:AccessControl");
			d.setVersion("1.0");
			depList.add(d);
			ServiceRepositoryIO.createZipFile(depList, "/home/parag/services.zip");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		

		// Test getService();
		/*
		try {
			String service[] = new String[1];
			String title[] = new String[1];
			ServiceRepositoryIO.getServiceAndTitle("urn:jaus:jss:core:AccessControl", "1.0",
					service, title);
			System.out.println(title[0] + service[0]);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		// Test getAllDependencies();
		
		ArrayList<ServiceRepositoryDependency> depList = null;
		try {
			depList = ServiceRepositoryIO.getAllDependencies(depList, "urn:jaus:jss:core:Management", "1.0");
			System.out.println("The dependencies of urn:jaus:jss:core:Management / 1.0 are: \n");
			for (i = 0; i < depList.size(); i++) {
				System.out.println("  " + depList.get(i).getURI() + " / " + depList.get(i).getVersion());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		try {
			ServiceRepositoryIO.getDependency("urn:jaus:jss:core:MessageSet", "1.0");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*

		/*
		ServiceRepositoryJSIDL jsidl = new ServiceRepositoryJSIDL();
		
		try {
			jsidl.parse("/home/parag/management.jsidl");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<ServiceRepositoryDependency> dep = jsidl.getDependencies();
		jsidl.getDocumentURI();
		/*
		
		/*
		ServiceRepositoryIO.addSchema("/home/parag/jsidl_plus.xsd");
		String s = ServiceRepositoryIO.getSchema();
		System.out.println(s);
		*/
		
		/*
		ArrayList<ServiceRepositoryCategory> sc = ServiceRepositoryIO.getCategories();
		
		for (i = 0; i < sc.size(); i++)
		{
			String title = (sc.get(i)).title;
			System.out.println("Title " + i + " = " + title);
		}
		
		ArrayList<ServiceRepositoryEntry> services = ServiceRepositoryIO.getServicesByCategory("UGV");
		
		for (i = 0; i < services.size(); i++)
		{
			System.out.println(i + ": " + 
					services.get(i).getName() + " : " +
					services.get(i).getBriefDescription() + " : " +
					services.get(i).getAuthor() + " : " +
					services.get(i).getVersion() + "\n");
			System.out.println("         " + services.get(i).getVerboseDescription() + "\n");
		}
		*/
	}

}
