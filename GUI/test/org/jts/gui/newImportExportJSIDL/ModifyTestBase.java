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

import org.junit.*;

import java.io.File;

import javax.xml.bind.JAXBContext;

public abstract class ModifyTestBase extends TestBase
{
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

	@Before
	/**
	 * Setup the database and unmarshaller before we run the unit tests
	 */
	public void setup()
	{
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

	@Test
	/**
	 * Runs all the import/export tests for the element
	 */
	public void runComparisonTests()
	{
		// clear input and output test directories
		//clearDirectory( inputDirectory );
		//clearDirectory( outputDirectory );
		//deleteDatabase();

		for( int i = 1; i <= getNumberTestFiles(); i++ )
		{
			runComparison( i );
		}
	}

	/**
	 * Run an import export test with a specific test number.
	 * @param testNumber
	 */
	private void runComparison( int testNumber )
	{
		currentTestName = getTestFileBaseName() + Integer.toString( testNumber );

		// copy the expected output into the input directory. this is the file we will
		// compare the output of the tests to
		String testXMLOutputFileName = generatedXML + getTestFileBaseName() + "ModifyOutput" + Integer.toString( testNumber );
		File outputXMLFile = new File( testXMLOutputFileName + ".xml" );

		if( outputXMLFile == null )
		{
			Assert.fail( "Output File: " + outputXMLFile + " not found!" );
		}

		// copy the expected output into the input directory (the input of what will be compared for the tests)
		// name it the current test name to make for uniform naming
		writeFileToInput( outputXMLFile, currentTestName );

		// find test xml file to load as input
		String testXMLInputFileName = generatedXML + getTestFileBaseName() + "ModifyInput" + Integer.toString( testNumber );
		File inputXMLFile = new File( testXMLInputFileName + ".xml" );
		
		if( inputXMLFile == null )
		{
			Assert.fail( "Input File: " + testXMLInputFileName + " not found!" );
		}

		// get object to test
		Object jaxbObject = getObjectFromFile( inputXMLFile );

		// convert from jaxb to jmatter
		Object jmatterObject = jaxbToJmatter( jaxbObject );

		if( jmatterObject == null )
		{
			Assert.fail( "Input File: " + testXMLInputFileName + " JAXB to jmatter conversion failed" );
		}

		// convert back to jaxb object
		Object convertedJaxbObject = jmatterToJaxb( jmatterObject );

		if( convertedJaxbObject == null )
		{
			Assert.fail( "Input File: " + testXMLInputFileName + " jmatter JAXB conversion failed" );
		}

		writeObjectToOutput( currentTestName, convertedJaxbObject );

		xmlUnitTest( currentTestName );
	}

}
