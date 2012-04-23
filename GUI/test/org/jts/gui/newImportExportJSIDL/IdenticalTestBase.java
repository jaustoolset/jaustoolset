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

package org.jts.gui.newImportExportJSIDL;

import org.jts.gui.importJSIDL.declaredElementsResolution.DeclaredTypeMap;
import org.jts.gui.importJSIDL.declaredElementsResolution.DeclaredTypeSet;
import org.junit.*;

import java.io.File;

import javax.xml.bind.JAXBContext;

import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public abstract class IdenticalTestBase extends TestBase
{
	/**
	 * Get whether the declared resolution should be run
	 * for the inheriting element.
	 * This should be based upon if the element can contain declared
	 * elements.
	 * @return
	 */
	abstract boolean getRunDeclaredResolution();

	/**
	 * Get whether the interpretation tests should be run
	 * for this element
	 * @return
	 */
	abstract boolean getRunInterpretation();

	/**
	 * Get whether the nonUnique test should be run for 
	 * this element
	 * @return
	 */
	abstract boolean getRunNonUnique();

	/**
	 * Gets the name of the test case for the current class
	 * ex. ArrayTest will return 'ArrayTest' as the base
	 * test file name.
	 * @return
	 */
	abstract String getTestFileBaseName();

	/**
	 * Get the number of tests defined for this element.
	 * @return
	 */
	abstract int getNumberTestFiles();

	/**
	 * Get the length of the element's interpretation attribute.
	 * Returns -1 if element does not have the attribute.
	 * @param object
	 * @return
	 */
	abstract int getInterpretationLength( Object object );

	/**
	 * Converts the incoming object from jaxb to a jmatter object
	 * and returns an Object cast of the jmatter object 
	 * @param object
	 * @return
	 */
	abstract Object jaxbToJmatter( Object object );

	/**
	 * Converts the incoming object from jmatter to a jaxb object
	 * and returns an Object cast of the jaxb object 
	 * @param object
	 * @return
	 */
	abstract Object jmatterToJaxb( Object object );

	/**
	 * Try to resolve declared elements that are contained in the
	 * input object.  Return null when resolution test not applicable.
	 * @param inputObject
	 * @return
	 */
	abstract Object runResolveDeclaredElements( Object inputObject );

	@Before
	/**
	 * Setup the database and unmarshaller before we run the unit tests
	 */
	public void setup()
	{
		// only create the hash map of declared elements if this inheriting
		// element is going to run the declared resolution tests
		if( getRunDeclaredResolution() )
		{
			declaredElementsMap = new HashMap< String, Object >();
		}

		seedDatabase();

		try
		{
			jc = JAXBContext.newInstance("org.jts.jsidl.binding");
			unmarshaller = jc.createUnmarshaller();
		}
		catch( Exception e )
		{
			Assert.fail( e.getMessage() );
		}
	}

	/**
	 * Builds a hash map of name<->elements.  The name is composed of
	 * the elements id and version
	 * @return
	 */
	private void mapDeclaredValues()
	{
		Document doc = null;
		DocumentBuilder db = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);

		try
		{
			db = dbf.newDocumentBuilder();
		}
		catch( javax.xml.parsers.ParserConfigurationException pce )
		{
			pce.printStackTrace();
		}

		File file = new File( declaredXMLFile );

		try
		{
			doc = db.parse( file );
		}
		catch( Exception e )
		{
			Assert.fail( "Unable to parse file" + file.getName() + " \n " + e.toString() );
		}

		Element root = doc.getDocumentElement();
	
		if( root.getAttribute( "xmlns" ).equals( "urn:jaus:jsidl:1.0" ) )
		{
			try
			{
				declaredElementsMap.put( root.getAttribute( "id" ) + "-" + root.getAttribute( "version" ), unmarshaller.unmarshal( file ) );
			}
			catch( JAXBException jaxbe )
			{
				Assert.fail( "Unable to unmarshal file" + file.getName() + " \n " + jaxbe.toString() );
			}
		}
		else
		{
			Assert.fail( "File root is not 1.0" );
		}
	}

	@Test
	/**
	 * Runs all the import/export tests for the element
	 */
	public void importExportTests()
	{
		// clear input and output test directories
		//clearDirectory( inputDirectory );
		//clearDirectory( outputDirectory );
		//deleteDatabase();

		for( int i = 1; i <= getNumberTestFiles(); i++ )
		{
			runImportExportTest( i );
		}
	}

	@Test
	public void declaredResolutionTests()
	{
		// clear input and output test directories
		//clearDirectory( inputDirectory );
		//clearDirectory( outputDirectory );
		//deleteDatabase();

		if( !getRunDeclaredResolution() )
		{
			return;
		}

		// map declared elements in declaredXML directory to map of id<->binding object
		mapDeclaredValues();

		// run the declared test for each file
		for( int i = 1; i <= getNumberTestFiles(); i++ )
		{
			runDeclaredResolutionTest( i );
		}
	}

	/**
	 * Run an import export test with a specific test number.
	 * @param testNumber
	 */
	private void runImportExportTest( int testNumber )
	{
		currentTestName = getTestFileBaseName() + Integer.toString( testNumber );

		// find test xml file
		String testXMLFileName = generatedXML + currentTestName;
		File testXMLFile = new File( testXMLFileName + ".xml" );
		
		if( testXMLFile == null )
		{
			Assert.fail( "Input File: " + testXMLFileName + " not found!" );
		}

		// copy current test file to test input directory
		File inputFile = writeFileToInput( testXMLFile );

		// get object to test
		Object jaxbObject = getObjectFromFile( inputFile );

		// convert from jaxb to jmatter
		Object jmatterObject = jaxbToJmatter( jaxbObject );

		if( jmatterObject == null )
		{
			Assert.fail( "Input File: " + testXMLFileName + " JAXB to jmatter conversion failed" );
		}

		// convert back to jaxb object
		Object convertedJaxbObject = jmatterToJaxb( jmatterObject );

		if( convertedJaxbObject == null )
		{
			Assert.fail( "Input File: " + testXMLFileName + " jmatter JAXB conversion failed" );
		}

		writeObjectToOutput( currentTestName, convertedJaxbObject );

		xmlUnitTest( currentTestName );
	}

	/**
	 * Run a declared resolution test with a specific test Number
	 * using a map of stored declared elements. Note: this can only be run
	 * after the map of declared elements is generated.
	 * @param map
	 * @param testNumber
	 */
	private void runDeclaredResolutionTest( int testNumber )
	{
		// our input file will be a copy of the base test file for the test
		currentTestName = getTestFileBaseName() + Integer.toString( testNumber );
		File testXMLFile = new File( generatedXML + currentTestName + ".xml" );

		if( testXMLFile == null )
		{
			Assert.fail( "Input File: " + currentTestName + " not found!" );
		}

		// get current test file for the declared test
		String currentDeclaredTestName = "Declared" + getTestFileBaseName() + Integer.toString( testNumber );
		File declaredTestXMLFile = new File( generatedXML + currentDeclaredTestName + ".xml" );

		if( declaredTestXMLFile == null )
		{
			Assert.fail( "Declared Input File: " + currentDeclaredTestName + " not found!" );
		}

		// copy the base input file to the input directory and rename it as the declared test
		writeFileToInput( testXMLFile, currentDeclaredTestName );

		Object inputObject = getObjectFromFile( declaredTestXMLFile );



		// Programatically generate a declared type set so that we can set up the DeclaredTypeSet instance.
		// we do this so we don't have to wrap the test xml in needless complex structures
		org.jts.jsidl.binding.DeclaredTypeSet declaredTypeSet = new org.jts.jsidl.binding.DeclaredTypeSet();
		declaredTypeSet.setName( "base" );

		// create a reference
		org.jts.jsidl.binding.DeclaredTypeSetRef declaredTypeSetRef = new org.jts.jsidl.binding.DeclaredTypeSetRef();
		declaredTypeSetRef.setName( "unitTest" );
		declaredTypeSetRef.setId( "urn:DeclaredUnitTest" );
		declaredTypeSetRef.setVersion( "1.0" );

		// add the reference to the type set
		declaredTypeSet.getDeclaredTypeSetRef().add( declaredTypeSetRef );

		// map our declared objects into the DeclaredTypeMap instance so we can look them up later
		DeclaredTypeSet.importDeclaredTypeSet( declaredTypeSet, declaredElementsMap );

		// we must set the current package for this instance of the declared type mapping
		DeclaredTypeMap.getInstance().setPackage( declaredTypeSet.getName() );



		// resolve current test declared elements from map instance
		Object ouputObject = runResolveDeclaredElements( inputObject );

		// marshal object to file so we can compare with xmlunit
		writeObjectToOutput( currentDeclaredTestName, ouputObject );

		// tests for differences
		xmlUnitTest( currentDeclaredTestName );
	}

	@Test
	/**
	 * Test trying to insert duplicates into the database
	 */
	public void runNonUniqueTest()
	{
		if( !getRunNonUnique() )
		{
			return;
		}

		currentTestName = getTestFileBaseName() + "NonUnique";

		// find test xml file
		String testXMLFileName = generatedXML + currentTestName + ".xml";
		File testXMLFile = new File( testXMLFileName );
		
		if( testXMLFile == null )
		{
			Assert.fail( "Input File: " + testXMLFileName + " not found!" );
		}

		// get object to test
		Object jaxbObject = getObjectFromFile( testXMLFile );

		// convert from jaxb to jmatter
		Object jmatterObject = jaxbToJmatter( jaxbObject );

		// convert from jaxb to jmatter again
		jmatterObject = jaxbToJmatter( jaxbObject );

		Assert.assertNotNull( jmatterObject );
	}

	@Test
	/**
	 * Test whether interpretations are transfered during import/export
	 */
	public void runInterpretationTests1()
	{
		if( !getRunInterpretation() )
		{
			return;
		}

		currentTestName = getTestFileBaseName() + "Interpretation" + "1";

		// find test xml file
		String testXMLFileName = generatedXML + currentTestName + ".xml";
		File testXMLFile = new File( testXMLFileName );
		
		if( testXMLFile == null )
		{
			Assert.fail( "Input File: " + testXMLFileName + " not found!" );
		}

		// get object to test
		Object jaxbObject = getObjectFromFile( testXMLFile );

		// convert from jaxb to jmatter
		Object jmatterObject = jaxbToJmatter( jaxbObject );

		Assert.assertEquals( 1, getInterpretationLength( jmatterObject ) );
	}
	
	@Test
	/**
	 * Test whether interpretations are concatenated during import/export
	 */
	public void runInterpretationTests2()
	{
		if( !getRunInterpretation() )
		{
			return;
		}

		currentTestName = getTestFileBaseName() + "Interpretation" + "2";

		// find test xml file
		String testXMLFileName = generatedXML + currentTestName + ".xml";
		File testXMLFile = new File( testXMLFileName );
		
		if( testXMLFile == null )
		{
			Assert.fail( "Input File: " + testXMLFileName + " not found!" );
		}

		// get object to test
		Object jaxbObject = getObjectFromFile( testXMLFile );

		// convert from jaxb to jmatter
		Object jmatterObject = jaxbToJmatter( jaxbObject );

		Assert.assertEquals( 255, getInterpretationLength( jmatterObject ) );
	}

}
