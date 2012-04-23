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

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.Vector;

public class GenerateDeclaredXML
{
	private String definitionDirectory = "test/org/jts/gui/newImportExportJSIDL/xmlDefinitions/";
	private String generatedDirectory = "test/org/jts/gui/newImportExportJSIDL/generatedXML/";
	private String inputDirectory = "test/org/jts/gui/newImportExportJSIDL/inputXML/";
	private String outputDirectory = "test/org/jts/gui/newImportExportJSIDL/outputXML/";

	private String declaredXMLFileName = "DeclaredTypeSet.xml";

	// the string that is used to specify files definitions
	// for example, '++++ArrayTest1.xml++++' specifies that the definition contained in 
	// ArrayTest1.xml should be placed at that location
	private String referenceTag = "++++";

	// we will parse the namespace tags because xmlunit won't exclude these easily
	private String namespaceHeader = "xmlns=\"urn:jaus:jsidl:1.0\"";

	// this map is used to generate the file with the declared type set
	private HashMap< String, Integer > declaredMap = new HashMap< String, Integer >();

	// this map is used to hold fileName<->definitions so we can create the xml files
	// the strings are stored in vectors to allow for easy line by line manipulation
	private HashMap< String, Vector< String > > definitionMap = new HashMap< String, Vector< String > >();

	GenerateDeclaredXML()
	{
		// the following elements are those which can be called as a declared type
		declaredMap.put( "Array", new Integer( 8 ) );
		declaredMap.put( "BitField", new Integer( 1 ) );
		declaredMap.put( "Body", new Integer( 4 ) );
		declaredMap.put( "FixedField", new Integer( 3 ) );
		declaredMap.put( "FixedLengthString", new Integer( 1 ) );
		declaredMap.put( "Footer", new Integer( 4 ) );
		declaredMap.put( "Header", new Integer( 4 ) );
		declaredMap.put( "List", new Integer( 4 ) );
		declaredMap.put( "MessageDef", new Integer( 3 ) );
		declaredMap.put( "Record", new Integer( 8 ) );
		declaredMap.put( "Sequence", new Integer( 4 ) );
		declaredMap.put( "VariableField", new Integer( 1 ) );
		declaredMap.put( "VariableFormatField", new Integer( 1 ) );
		declaredMap.put( "VariableLengthField", new Integer( 1 ) );
		declaredMap.put( "VariableLengthString", new Integer( 1 ) );
		declaredMap.put( "Variant", new Integer( 4 ) );
	}

	public void run()
	{
		// clear previous files
		resetGeneratedDirectory();
		resetInputDirectory();
		resetOutputDirectory();

		// must run test generation before we create the declared types file
		runTestGeneration();
		runDeclaredGeneration();		
	}

	private void resetGeneratedDirectory()
	{
		File directory = new File( generatedDirectory );

		deleteFileOrDirectory( directory );

		new File( generatedDirectory ).mkdir();
	}

	private void resetInputDirectory()
	{
		File directory = new File( inputDirectory );

		deleteFileOrDirectory( directory );

		new File( inputDirectory ).mkdir();
	}

	private void resetOutputDirectory()
	{
		File directory = new File( outputDirectory );

		deleteFileOrDirectory( directory );

		new File( outputDirectory ).mkdir();
	}

	private void deleteFileOrDirectory( File input )
	{
		if( input.isDirectory() )
		{
			File[] files = input.listFiles();
	
			for( File file: files )
			{
				if( file.isDirectory() )
				{
					deleteFileOrDirectory( file );
				}
				else
				{
					file.delete();
				}
			}
		}
		else if( input.isFile() )
		{
			input.delete();
		}
	}

	/**
	 * Creates a file with all the possible declared types wrapped in a declared set
	 */
	private void runDeclaredGeneration()
	{
		Set< String > values = declaredMap.keySet();
		Iterator< String > it = values.iterator();
		StringBuffer buffer = new StringBuffer();

		// iterate over the set to add each definition to the buffer
		while( it.hasNext() )
		{
			String baseName = ( String )it.next();
			int count = declaredMap.get( baseName );

			for( int j = 1; j <= count ; j++ )
			{
				buffer.append( getUnwrappedModifiedElement( baseName, j ) );
				
			}
		}

		// wrap all our definitions in a declared type set
		wrapBufferInDeclaredTypeSet( buffer );

		// write the buffer to the output file
		writeToOutput( buffer, declaredXMLFileName );
	}

	/**
	 * Create the test files by mapping all the files to the tags within the definition files
	 */
	private void runTestGeneration()
	{
		File directory = new File( definitionDirectory );
		File[] files = directory.listFiles();

		// First we will go through all the files and add them to a hasmap
		// of fileName<->definition
		// the first line of the file will be discarded because it is the xml header for the file
		for (int i = 0; i < files.length; i++)
		{
			File file = files[ i ];

			// skip svn files
			if( file.isDirectory() )
			{
				continue;
			}

			try
			{
				FileReader fileReader = new FileReader( file );
				BufferedReader reader = new BufferedReader( fileReader );
				Vector< String > buffer = new Vector< String >();
				String line;

				boolean first = true;
				while( ( line = reader.readLine() ) != null )
				{
					// skip the first line, it will be the xml header
					if( first )
					{
						first = false;
						continue;
					}

					// remove all xmlns tags by parse
					// this is needed because xmlunit will not easily ignore tags
					line = line.replace( namespaceHeader, "");

					// add the line to the definition buffer
					buffer.add( line );
				}

				// the buffer is now built, add it to the map of fileName<->definitions
				definitionMap.put( file.getName(), buffer );
			}
			catch( Exception e )
			{
				System.out.println( e.toString() );
			}
		}

		// Now we can go through each of the files in the directory and fill in the 
		// definitions within the file
		// this will be a recursive call because the referenced files may contain referenced files
		// we then save the file to the xml directory
		for (int i = 0; i < files.length; i++)
		{
			File file = files[ i ];

			// skip svn files
			if( file.isDirectory() )
			{
				continue;
			}

			try
			{
				FileReader fileReader = new FileReader( file );
				BufferedReader reader = new BufferedReader( fileReader );
				StringBuffer buffer = new StringBuffer();
				String line;

				while( ( line = reader.readLine() ) != null )
				{
					if( line.contains( referenceTag ) )
					{
						buffer.append( getReferencedValue( line, "" ) );
					}
					else
					{
						buffer.append( line );
						buffer.append( System.getProperty("line.separator") );
					}
				}

				// write the buffer to the output
				writeToOutput( buffer, file.getName() );
			}
			catch( Exception e )
			{
				System.out.println( e.toString() + file.getName() );
			}
		}
		
	}

	private StringBuffer getReferencedValue( String line, String offset )
	{
		StringBuffer buffer = new StringBuffer();
	
		int startTagStart = line.indexOf( referenceTag );
		int startTagEnd = startTagStart + referenceTag.length();
		int endTagStart = line.lastIndexOf( referenceTag );

		// get the offset for the reference so the output file
		// can be easily read
		String localOffset = line.substring( 0,startTagStart ) + offset;

		String referencedFile = line.substring( startTagEnd, endTagStart );
		Vector< String > definition = definitionMap.get( referencedFile );
		
		if( definition == null )
		{
			System.out.println( "File Not Found: " + referencedFile );
			return null;
		}

		// append the definition to the buffer with the offset for each line
		for( String definitionLine: definition )
		{
			// recursive call for nested definitions
			if( definitionLine.contains( referenceTag ) )
			{
				buffer.append( getReferencedValue( definitionLine, localOffset ) );
			}
			else
			{
				buffer.append( localOffset );
				buffer.append( definitionLine );
				buffer.append( System.getProperty("line.separator") );
			}
		}
		
		return buffer;
	}

	public static void main( String[] args )
	{
		GenerateDeclaredXML gen = new GenerateDeclaredXML();
		gen.run();
	}

	private void wrapBufferInDeclaredTypeSet( StringBuffer buffer )
	{
		StringBuffer temp = new StringBuffer();

		temp.append( "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" );
		temp.append( System.getProperty("line.separator") );
		temp.append( "<declared_type_set xmlns=\"urn:jaus:jsidl:1.0\" " );
		temp.append( "name=\"DeclaredSet\" " );
		temp.append( "id=\"urn:DeclaredUnitTest\" " );
		temp.append( "version=\"1.0\">" );
		temp.append( System.getProperty("line.separator") );

		buffer.insert( 0, temp );
		buffer.append( "</declared_type_set>" );
	}

	private void writeToOutput( StringBuffer buffer, String fileName )
	{
		try
		{
			String file = generatedDirectory + fileName;
			BufferedWriter writer = new BufferedWriter( new FileWriter( new File( file ) ) );
			
			writer.write( buffer.toString() );
			writer.flush();
			writer.close();
		}
		catch( Exception e )
		{
			System.out.println( e.toString() );
			return;
		}
	}

	private String getUnwrappedModifiedElement( String baseName, int index )
	{
		try
		{
			// all examples will have the same name format ( ex. ArrayTest3.java )
			// and will be located in the xml directory
			String fileName = generatedDirectory + baseName + "Test" + Integer.toString( index ) + ".xml";
			FileReader file = new FileReader( fileName );
			BufferedReader reader = new BufferedReader( file );
			StringBuffer buffer = new StringBuffer();
			String line;

			boolean first = true;
			while( ( line = reader.readLine() ) != null )
			{
				// always skip first line (xml version note)
				if( first == true )
				{
					
				}
				// add tab to every line for clarity
				else
				{
					buffer.append( "\t" );
					buffer.append( line );
					buffer.append( System.getProperty("line.separator") );
				}

				first = false;
			}

			return buffer.toString();
		}
		catch( Exception e )
		{
			System.out.println( e.toString() );
			return null;
		}
	}

}