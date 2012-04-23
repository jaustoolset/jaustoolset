package jtsServiceRepositoryDB;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipEntry;

import org.xml.sax.SAXException;


public class ServiceRepositoryIO {

	private static boolean gotConnection = false;
	private static Connection con = null; 
	
	private static Connection getConnection()
	{
		//Connection con = null;
	
		// TODO: Replace with check to see if connection is live. 
		if (gotConnection) {
			return con;
		}
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Got driver\n");
//			String url = "jdbc:mysql://192.168.2.114/jtsRepository";
//			String user = "jts";
//			String pw = "jaustoolset";

			String url = "jdbc:mysql://jtsrepository.db.4885254.hostedresource.com/jtsrepository";
			String user = "jtsrepository";
			String pw = "JAUSToolSet123";

			con = DriverManager.getConnection(url, user, pw);
			System.out.println("Got connection\n");
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("getConnection ClassNotFoundException: " + e.getMessage());
			//System.exit(0);
		}
		catch (SQLException e)
		{
			System.out.println("getConnection SQLException: " + e.getMessage());
			gotConnection = false;
			//System.exit(0);
		}
		
		gotConnection = true;
		return con;
	}
	
	public static ArrayList<ServiceRepositoryCategory> getCategories()
	{
		ArrayList<ServiceRepositoryCategory> sc;
		ResultSet rows = null;
		
		sc = new ArrayList<ServiceRepositoryCategory>(100);
		
		Connection con = getConnection();
		
		try
		{
			Statement s = con.createStatement();
			String select = "Select categoryTitle, categoryDescription, categoryIconName from CategoryList order by categoryTitle";
			rows = s.executeQuery(select);
			
			ServiceRepositoryCategory a = new ServiceRepositoryCategory();
			a.setTitle("All");
			sc.add(a);
			while (rows.next())
			{
				ServiceRepositoryCategory c = new ServiceRepositoryCategory();
				c.setTitle(rows.getString("categoryTitle"));
				c.setDescription(rows.getString("categoryDescription"));
				c.setIconName(rows.getString("categoryIconName"));
				sc.add(c);
			}
			
			// con.close();
		}
		catch (SQLException e)
		{
			System.out.println("getCategories SQLException: " + e.getMessage());
			gotConnection = false;
			//System.exit(0);
		}

		return sc; 
	}
	
	public static String getSchema() {
		
		ResultSet rows = null;
		
		Connection con = getConnection();
		
		try {
			Statement s = con.createStatement();
			String select = "SELECT theSchema from SchemaList";
			rows = s.executeQuery(select);
			
			rows.next();
			String ret = rows.getString("theSchema");
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			gotConnection = false;
		}
		
		return null;
	}
	
	/** 
	 * Grabs the specified service from the database and returns the XML as a string
	 * @param URI
	 * @param version
	 * @return
	 * @throws SQLException 
	 */
	public static boolean getServiceAndTitle(String URI, String version, String[] service, String[] title) throws SQLException {
		ResultSet rows = null;
		boolean gotService = false;
		
		Connection con = getConnection();
		
		Statement s = con.createStatement();
		String select = "SELECT jsidl, serviceTitle FROM ServiceEntry where " + 
		"serviceURI = " +
		"\"" + URI + "\" and version = " + 
		"\"" + version + "\"";
		
//		System.out.println("select = " + select);
		rows = s.executeQuery(select);
		
		while (rows.next()) {
			service[0] = rows.getString("jsidl");
			title[0] = rows.getString("serviceTitle");
			gotService = true;
		}
		
		return gotService;
		
	}
	
	public static void addSchema(String fileName) {
		
		// first, read the file
		StringBuffer fileData = new StringBuffer(64000);
		int numRead = 0;
		String allData = null;
		String allData2 = null;
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			char[] buf = new char[1024];
		       while((numRead=reader.read(buf)) != -1){
		            String readData = String.valueOf(buf, 0, numRead);
		            fileData.append(readData);
		            buf = new char[1024];
		        }
		        reader.close();
		        allData = fileData.toString();
		        System.out.println("string length = " + allData.length() + "\n");
		        allData2 = allData.replace("\"", "\\\"");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// System.out.println(allData);
		
		Connection con = getConnection();
		
		try {
			Statement s = con.createStatement();
			
			String insert1 = "insert into SchemaList (theSchema, schemaId)" + 
			"values (\"" + allData2 + "\", 1)";
		
			System.out.println(insert1);
			s.executeUpdate(insert1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			gotConnection = false;
		}
		
		
	}
	
	public static ArrayList<ServiceRepositoryEntry> getServicesByCategory(String category)
	{
		ArrayList<ServiceRepositoryEntry> serviceArray;
		ResultSet categoryResults = null;
		ResultSet serviceResults = null;
		
		serviceArray = new ArrayList<ServiceRepositoryEntry>(100);
		
		Connection con = getConnection();
		
		try
		{
			Statement s = con.createStatement();
			
			// First, get a list of service entries by category
			String select1;
			if (category.equals("All")) {
				select1 = "SELECT serviceURI from CategoryByService";
			} else {
				select1 = "SELECT serviceURI from CategoryByService where categoryTitle = " + "\"" + category + "\"";
			}
			
			System.out.println("  sending query: " + select1 + "\n");
			
			categoryResults = s.executeQuery(select1);
			
			while (categoryResults.next())
			{
				// Now, we have a list of service titles by category from the above query. Go through
				// each service title, and grab the full service entry
				String select2;
				select2 = "SELECT serviceURI, serviceTitle, author, briefDescription, verboseDescription, " + 
					"version from ServiceEntry where serviceURI = " +
					"\"" + categoryResults.getString(1) + "\"";
				
				Statement stmt = con.createStatement();
				System.out.println("      sending query: " + select2 + "\n");
				serviceResults = stmt.executeQuery(select2);
				
				// there should be one and only one result, but we'll handle this by iterating over all results
				while (serviceResults.next())
				{
					ServiceRepositoryEntry entry = new ServiceRepositoryEntry();
					
					entry.setURI(serviceResults.getString("serviceURI"));
					entry.setName(serviceResults.getString("serviceTitle"));
					entry.setAuthor(serviceResults.getString("author"));
					entry.setBriefDescription(serviceResults.getString("briefDescription"));
					entry.setVerboseDescription(serviceResults.getString("verboseDescription"));
					entry.setVersion(serviceResults.getString("version"));
					
					serviceArray.add(entry);
				}
			}
		}
		catch (SQLException e)
		{
			System.out.println("getCategories SQLException: " + e.getMessage());
			gotConnection = false;
			//System.exit(0);
		}
		
		return serviceArray;
	}
	
	/**
	 * This function determines whether a particular service is already in the database. It can be used
	 * to check whether dependencies are satisifed or not. 
	 * 
	 * @param URI  Unique URI of service
	 * @param version  Version of service
	 * @return true if service is present in database, false otherwise
	 * @throws SQLException
	 */
	public static boolean isServiceInRepository(String URI, String version) 
		throws SQLException {
		
		boolean isPresent = false;
		Connection con = getConnection();
		
		Statement s = con.createStatement();
		
		String select;
		
		select = "SELECT serviceURI from ServiceEntry where serviceURI = " + 
		"\"" + URI + "\" and version = " +
		"\"" + version + "\"";
		
		System.out.println("isServiceInRepository() Sending query " +  select);
		ResultSet rows = s.executeQuery(select);
		
		while (rows.next()) {
			isPresent = true;
		}
		
		return isPresent;
	}
	
	/**
	 * This function adds a service to the repository. It checks first to see if the service is 
	 * already in the repository
	 * 
	 * @param fileName
	 * @param author
	 * @param category
	 * @param basicDesc
	 * @param dependencies
	 * @return
	 */
	public static boolean addService(String fileName, String author, String category, String briefDesc) 
		throws IOException, SAXException, FileNotFoundException, SQLException
		{
		
		ServiceRepositoryJSIDL service = new ServiceRepositoryJSIDL();

		// parse the XML. 
		service.parse(fileName);
		
		// we need to escape out any quotation marks to compose the SQL statement
		String briefDescEsc = briefDesc.replaceAll("\"", "\\\"");
		
		// Compose the detailed description
		String fullDesc = service.getFullDescription(fileName);
		String fullDescEsc = fullDesc.replaceAll("\"", "\\\"");
		
		// Extract the URI
		String URI = service.getDocumentURI();
		
		// Extract the version
		String version = service.getDocumentVersion();
		
		// Extract the name
		String name = service.getDocumentName();
		
		// Now, we need to grab the whole service into a string, since it is part of the SQL command
		// that we have to create.
		int numRead;
		StringBuffer fileData = new StringBuffer(64000);
		String allData = null;
		String allData2 = null;
		
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		char[] buf = new char[1024];
		while((numRead=reader.read(buf)) != -1){
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}
		reader.close();
		allData = fileData.toString();
		System.out.println("addService() JSIDL string length = " + allData.length() + "\n");
		allData2 = allData.replace("\"", "\\\"");
		
		// Now, compose the insert string. 
		String insertService = "insert into ServiceEntry (serviceURI, serviceTitle, author, briefDescription, verboseDescription, version, jsidl)" + 
		"values (" +
		"\"" + URI + "\"," + 
		"\"" + name + "\"," + 
		"\"" + author + "\"," + 
		"\"" + briefDescEsc + "\"," + 
		"\"" + fullDescEsc + "\"," + 
		"\"" + version + "\"," + 
		"\"" + allData2 + "\")";
		
		System.out.println(insertService);
	
		Connection con = getConnection();
		try {
			Statement s = con.createStatement();
			s.executeUpdate(insertService);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			gotConnection = false;
			throw new SQLException(e); 
		}
		
		// Now, we have to add this service to the category list
		
		String insertCategory = "insert into CategoryByService (categoryTitle, serviceURI)" + 
		"values (" + 
		"\"" + category + "\"," +
		"\"" + URI + "\")";
		
		try {
			Statement s = con.createStatement();
			s.executeUpdate(insertCategory);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			gotConnection = false;
			throw new SQLException(e); 
		}
		
		// Now we need to add the appropriate entries to the dependency table to capture dependencies. 
		
		ArrayList<ServiceRepositoryDependency> deps = service.getDependencies();
		
		for (int i = 0; i < deps.size(); i++) {
			
			String insertDep = "insert into ServiceDependency (parent, parentVer, child, childVer)" + 
			"values (" +
			"\"" + URI + "\"," + 
			"\"" + version + "\"," + 
			"\"" + deps.get(i).getURI() + "\"," + 
			"\"" + deps.get(i).getVersion() + "\")";
			
			try {
				Statement s1 = con.createStatement();
				s1.executeUpdate(insertDep);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				gotConnection = false;
				throw new SQLException(e); 
			}
		}
		
		return true;
	}
	
	/**
	 * Returns a list of dependencies of the passed in service, by querying the dependency table
	 * @param URI
	 * @param version
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<ServiceRepositoryDependency> getDependency(String URI, String version) 
		throws SQLException {
		
		Connection con = getConnection();
		
		Statement s = con.createStatement();
		
		String select = "SELECT parent, parentVer, child, childVer FROM ServiceDependency " + 
		"where parent = " +
		"\"" + URI + "\" and parentVer = " +
		"\"" + version + "\"";
		
		ResultSet rows = s.executeQuery(select);
		
		ArrayList<ServiceRepositoryDependency> deps = new ArrayList<ServiceRepositoryDependency>(20);
		
		while (rows.next()) {
			ServiceRepositoryDependency d = new ServiceRepositoryDependency();
			d.setURI(rows.getString("child"));
			d.setVersion(rows.getString("childVer"));
			deps.add(d);
			// System.out.println("getDependency(): " + rows.getString("child") + " " + rows.getString("childVer"));
		}
		
		return deps;
	}
	
	/**
	 * This function returns all known dependencies of the passed in service URI. This does
	 * a depth first traversal of the dependency tree stored in the database.
	 * 
	 *  DOES NOT CHECK FOR CIRCULAR DEPENDENCIES. 
	 * @param depList  Dependency list. Null when initially called. Built up by recursion
	 * @param URI  The service URI
	 * @param version  The service version
	 * @return ArrayList of dependencies (each contains URI/version)
	 * @throws SQLException 
	 */
	
	public static ArrayList<ServiceRepositoryDependency> getAllDependencies(ArrayList<ServiceRepositoryDependency> depList, 
			String URI, String version) throws SQLException {

		boolean isFirst = false;
		if (depList == null) {
			depList = new ArrayList<ServiceRepositoryDependency>(10);
			isFirst = true;
		}
		
		ArrayList<ServiceRepositoryDependency> tempList = new ArrayList<ServiceRepositoryDependency>(10);
		
		tempList = getDependency(URI, version);
		
		Iterator<ServiceRepositoryDependency> e = tempList.iterator();
		while (e.hasNext()) {
			// Add to the accumulator array
			ServiceRepositoryDependency d = (ServiceRepositoryDependency)(e.next());
			depList.add(d);
			// Recursively descend
			getAllDependencies(depList, d.getURI(), d.getVersion());
		}

		ArrayList<ServiceRepositoryDependency> newList = new ArrayList<ServiceRepositoryDependency>(10);
		
		for (int i = 0; i < depList.size(); i++) {
			boolean shouldAdd = true;
			ServiceRepositoryDependency d1 = depList.get(i);
			for (int j = 0; j < i; j++) {
				ServiceRepositoryDependency d2 = depList.get(j);
				System.out.println(i + " " + j + ": Comparing " + d1.getURI() + " / " + d1.getVersion() + " with " + d2.getURI() + " / " + d2.getVersion() + "\n");
				if ((d1.getURI().equals(d2.getURI())) && (d1.getVersion().equals(d2.getVersion()))) {
					System.out.println("don't add\n");
					shouldAdd = false;
				}				
			}
			if (shouldAdd)
				newList.add(d1);
		}
		return newList;
	}
	
	public static void createZipFile(ArrayList<ServiceRepositoryDependency> serviceList, String fileName) 
	throws SQLException, IOException {
		
		System.out.println("createZipFile(): Saving to " + fileName + "\n");
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(fileName));
		
		for (int i = 0; i < serviceList.size(); i++) {

			String service[]=new String[1];
			String title[]=new String[1];
			boolean gotService = getServiceAndTitle(serviceList.get(i).getURI(), serviceList.get(i).getVersion(), service, title);
			
			System.out.println("createZipFile(): Adding " + serviceList.get(i).getURI() + " / " + serviceList.get(i).getVersion() + "\n");
			if (gotService == true) {
				String entryName = title[0] + ".xml";
			
				zos.putNextEntry(new ZipEntry(entryName));
			
				zos.write(service[0].getBytes());
			
				zos.closeEntry();
			}
		}
		
		zos.close();
	}
}
