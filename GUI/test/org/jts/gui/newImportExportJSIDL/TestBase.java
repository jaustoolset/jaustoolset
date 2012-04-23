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

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.junit.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.u2d.app.Application;
import com.u2d.app.Context;

import javax.xml.bind.JAXBException;

public abstract class TestBase
{
	protected JAXBContext jc;
	protected Unmarshaller unmarshaller;
	protected Map< String, Object > declaredElementsMap;

	protected String generatedXML = "test/org/jts/gui/newImportExportJSIDL/generatedXML/";
	protected String declaredXMLFile = generatedXML + "DeclaredTypeSet.xml";
	protected String inputDirectory = "test/org/jts/gui/newImportExportJSIDL/inputXML/";
	protected String outputDirectory = "test/org/jts/gui/newImportExportJSIDL/outputXML/";

	// skips countfield ( not in database )
	protected String[] databaseTables = { 
			"Array", "BitField", "Body",
			"Constant", "ConstantSet", "Dimension",
			"EventDef", "FixedField", "FixedLengthString",
			"Footer", "FormatEnum", "Header",
			"InputSet", "List", "MessageDef",
			"OutputSet", "ProtocolBehavior", "Record",
			"Sequence", "ServiceDef", "ServiceSet",
			"SubField", "TypesAndUnitsEnum", "ValueEnum",
			"ValueRange", "ValueSet", "VariableField",
			"VariableFormatField", "VariableLengthField", "VariableLengthString",
			"Variant"			
	};

	protected String currentTestName;

	/**
	 * Marshal the output Object to the outputFile
	 * @param output
	 * @param outputFile
	 */
	abstract void marshal( Object output, File outputFile );

	/**
	 * Initialize the database so we can store and retrieve elements
	 */
	protected void seedDatabase()
	{
		Logger.getLogger("org.springframework").setLevel(Level.WARNING);
		ApplicationContext context =
		new ClassPathXmlApplicationContext("applicationContext.xml");
		((Application) context.getBean("application")).seedDatabase();
	}

	/**
	 * Copy the contents of a file to a new file in the input directory
	 * @param original
	 * @return
	 */
	protected File writeFileToInput( File original )
	{
		if( original == null )
		{
			return null;
		}

		File copyFile = new File( inputDirectory + original.getName() );

		try
		{
			FileReader input = new FileReader( original );
			FileWriter output = new FileWriter( copyFile );
			
			int i;
			
			while( (i = input.read() ) != -1 )
			{
				output.write( i );
			}
			
			input.close();
			output.close();
		}
		catch( IOException e )
		{
			Assert.fail( e.getMessage() );
			return null;
		}

		return copyFile;
	}

	/**
	 * Copy the contents of a file to a new file in the input directory
	 * with and name it newName
	 * @param original
	 * @param newName
	 * @return
	 */
	protected File writeFileToInput( File original, String testName )
	{
		if( original == null )
		{
			return null;
		}

		File copyFile = new File( inputDirectory + testName + ".xml" );

		try
		{
			FileReader input = new FileReader( original );
			FileWriter output = new FileWriter( copyFile );
			
			int i;
			
			while( (i = input.read() ) != -1 )
			{
				output.write( i );
			}
			
			input.close();
			output.close();
		}
		catch( IOException e )
		{
			Assert.fail( e.getMessage() );
			return null;
		}

		return copyFile;
	}

	/**
	 * Write a file to the outputDirectory with the name of fileName by
	 * marshalling the output object into the file
	 * @param fileName
	 * @param output
	 */
	protected void writeObjectToOutput( String fileName, Object output )
	{
		try
		{
			File outputFile = new File( outputDirectory + fileName + ".xml" );
			marshal( output, outputFile );
		}
		catch( Exception e )
		{
			Assert.fail( e.getMessage() );
		}
	}

	/**
	 * Unmarshal a file into an object
	 * @param file
	 * @return
	 */
	protected Object getObjectFromFile( File file )
	{
		try
		{
			Object returnObject = unmarshaller.unmarshal( file );
			return returnObject;
		}
		catch( JAXBException e )
		{
			Assert.fail( e.getMessage() );
			return null;
		}		
	}

	/**
	 * Clear the contents of a directory
	 * @param path
	 */
	protected void clearDirectory( String path )
	{
		File directory = new File( path );

		File[] files = directory.listFiles();

		for( int i = 0; i < files.length; i++ )
		{
			File file = files[i];
			file.delete();
		}
	}

	/**
	 * Create a string representation of a file given by the fileName
	 * @param fileName
	 * @return
	 */
	protected String fileToString( String fileName )
	{
		try
		{
			FileReader file = new FileReader( fileName );
			BufferedReader reader = new BufferedReader( file );
			StringBuffer buffer = new StringBuffer();
			String line;

			while( ( line = reader.readLine() ) != null )
			{
				buffer.append( line );
			}
			
			return buffer.toString();
		}
		catch( Exception e )
		{
			Assert.fail( e.getMessage() );
			return null;
		}
	}

	/**
	 * Runs a xmlunit test on currentTest files.  There should
	 * be a file named currentTest in the input and output directories
	 * that will be compared.
	 * @param currentTest
	 */
	protected void xmlUnitTest( String currentTest )
	{
		try
		{
			String input = fileToString( inputDirectory + currentTest + ".xml" );
			String output = fileToString( outputDirectory + currentTest + ".xml" );

			TestXML unitXML = new TestXML( "" );
			unitXML.test( input, output );
		}
		catch( Exception e )
		{
			Assert.fail( e.toString() );
		}
		
		Assert.assertTrue( true );
	}

	/**
	 * Delete all elements store in the database by sending queries for all of
	 * the jaxbToJmatter class names
	 * NOTE: this does not delete some field, like format enum, but these will
	 * get overwritten anyway which is the desired result
	 * NOTE: we always need to clean the entire database with each call
	 * because we will be running this function in a generic context which
	 * can be using any of the jaxb elements
	 */
	protected boolean deleteDatabase()
	{
		for( int i = 0; i < databaseTables.length; i++ )
		{
			String tableName = databaseTables[ i ];

			// Get and handle on a hibernate session
			com.u2d.persist.HBMSingleSession persistenceMechanism = (com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism(); 
			
			try
			{
				Session session = persistenceMechanism.getSession();
				Transaction t = session.beginTransaction();
				session.createQuery( "delete " + tableName ).executeUpdate();
				t.commit();
			}
			catch( HibernateException e )
			{
				return false;
			}
		}

		return true;
	}

}
