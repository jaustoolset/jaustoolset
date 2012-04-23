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

package org.jts.codegenerator.support;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.UUID;

import org.jts.codegenerator.Util;

public class VisualStudioGenerator
{
	private final String fileSep = System.getProperty("file.separator");
	private final String lineSep = System.getProperty("line.separator");
	private File _rootDirectory;
	private String _componentName;
	private String _currentProjectName;
	private ArrayList< String > _serviceNames;
	
	public VisualStudioGenerator( String componentName, File componentDirectory )
	{
		_componentName = componentName;
		_rootDirectory = componentDirectory;
	}
	
	/**
	 * Convenience class for the StringBuffer so we can easily format
	 * the visual studio files
	 * @author d
	 *
	 */
	private class LineStringBuffer
	{
		private StringBuffer _buffer;
		private int _indent = 0;
		
		public LineStringBuffer()
		{
			_buffer = new StringBuffer();
		}
		
		public void setIndent( int i )
		{
			_indent = i;
		}
		
		public void indent()
		{
			_indent++;
		}
		
		public void unIndent()
		{
			if( _indent > 0 )
			{
				_indent--;
			}
		}
		
		public void appendOnSameLine( String str )
		{
			_buffer.append( str );
		}
		
		public void appendLine( String str )
		{
			for( int i = 0; i < _indent; i++)
			{
				_buffer.append( "\t" );
			}
			
			_buffer.append( str ).append( lineSep );
		}
		
		public String getBuffer()
		{
			return _buffer.toString();
		}
	}
	
	/**
	 * Convenience method for creating an attribute tag
	 * @param name
	 * @param value
	 * @return
	 */
	private String getAttributeString( String name, String value )
	{
		return name + "=" + getQuotedString( value );
	}
	
	/**
	 * Convenience method for quoting a string
	 * @param str
	 * @return
	 */
	private String getQuotedString( String str )
	{
		return "\"" + str + "\"";
	}
	
	/**
	 * Calls all solution and project methods for creating the component
	 * @param serviceOrder
	 * @throws Exception
	 */
	public void generateComponent( List< String > serviceOrder ) throws Exception
	{
		_serviceNames = new ArrayList< String >();
		
		for( String str : serviceOrder )
		{
			_serviceNames.add( str.substring(0, str.indexOf( "/" ) ) );
		}
		
		// make blank solution file for component
		// visual studio will populate needed information when opened
		makeSolutionFile();

		// make blank project files for each of the services
		// these will be placed in the same directory as the solution
		// because services are not placed in different directories 
		for( String serviceName : _serviceNames )
		{
			_currentProjectName = serviceName;
			generateLibraryProject();
		}
		
		// create a top level project for the component
		_currentProjectName = _componentName;
		generateExecutableProject();
	}
	
	/**
	 * Creates the solution file which includes the component and service projects
	 * @throws Exception
	 */
	private void makeSolutionFile() throws Exception
	{
		LineStringBuffer buffer = new LineStringBuffer();
		
		buffer.appendLine( "Microsoft Visual Studio Solution File, Format Version 10.00" );
		buffer.appendLine( "# Visual Studio 2008" );
		
		// make a guid for each project, they will be added in same order as projects
		ArrayList< String > guids = new ArrayList< String >();
		for( String serviceName : _serviceNames )
		{
			String guid = UUID.nameUUIDFromBytes( serviceName.getBytes() ).toString();
			guids.add( guid );
		}
		
		// this guid will be set as the first identifier for each of the projects
		String solutionGUID = UUID.nameUUIDFromBytes( "changeNameSolution".getBytes() ).toString().toUpperCase();
		
		// assume that the last name will be the component and all preceding ones are services
		// we must generate the component project first because visual studio will set the first
		// project defined in the solution file as the startup project
		// add component project
		// Project("{}") = "service", "service.vcproj", "{}"
		buffer.appendOnSameLine( "Project(\"{" + solutionGUID + "}\") = \"" );
		buffer.appendOnSameLine( _componentName );
		buffer.appendOnSameLine( "\", \"" );
		buffer.appendOnSameLine( _componentName );
		buffer.appendLine( ".vcproj\", \"{}\"" );
		buffer.indent();

			buffer.appendLine( "ProjectSection(ProjectDependencies) = postProject" );
			buffer.indent();
		// component depends on all services
		for( int i = 0; i < _serviceNames.size(); i++ )
		{
				buffer.appendLine( "{" + guids.get( i ) + "} = {" + guids.get( i ) + "}" );
		}
				buffer.unIndent();
			buffer.appendLine( "EndProjectSection" );
			buffer.unIndent();
		
		buffer.appendLine( "EndProject" );
		
		// now add each of the service projects including their dependencies
		for( int i = 0; i < _serviceNames.size(); i++ )
		{
			String serviceName = _serviceNames.get( i );
			
			// Project("{}") = "service", "service.vcproj", "{}"
			// let VS make a new guid for the project
			buffer.appendOnSameLine( "Project(\"{" + solutionGUID + "}\") = \"" );
			buffer.appendOnSameLine( serviceName );
			buffer.appendOnSameLine( "\", \"" );
			buffer.appendOnSameLine( serviceName );
			// use our guid to make the project dependencies
			buffer.appendLine( ".vcproj\", \"{" + guids.get( i ) + "}\"" );
			buffer.indent();

			// project dependencies, assume we will always have a component and at least one service
			if( i > 0 )
			{
				buffer.appendLine( "ProjectSection(ProjectDependencies) = postProject" );
				buffer.indent();

				for( int j = i - 1; j >= 0; j-- )
				{
					buffer.appendLine( "{" + guids.get( j ) + "} = {" + guids.get( j ) + "}" );
				}
					buffer.unIndent();
				
				buffer.appendLine( "EndProjectSection" );
			}
				buffer.unIndent();
			
			buffer.appendLine( "EndProject" );
		}
		
		Util.writeContents( new File( _rootDirectory + fileSep + _componentName + ".sln" ), buffer.getBuffer() );
	}
	
	/**
	 * Creates the service dll project
	 * @throws Exception
	 */
	private void generateLibraryProject() throws Exception
	{
		LineStringBuffer buffer = new LineStringBuffer();
		
		buffer.appendLine( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" );
		generateVisualStudioProjectTag( buffer );
			generatePlatformsTag( buffer );
			generateToolsFilesTag( buffer );
			generateLibraryConfigurationsTag( buffer );
			generateReferencesTag( buffer );
			generateFilesTag( buffer );
			generateGlobalsTag( buffer );
			buffer.unIndent();
		buffer.appendLine( "</VisualStudioProject>" );
		
		Util.writeContents( new File( _rootDirectory + fileSep + _currentProjectName + ".vcproj" ), buffer.getBuffer() );
	}
	
	/**
	 * Creates the component executable project
	 * @throws Exception
	 */
	private void generateExecutableProject() throws Exception
	{
		LineStringBuffer buffer = new LineStringBuffer();
		
		buffer.appendLine( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" );
		generateVisualStudioProjectTag( buffer );
			generatePlatformsTag( buffer );
			generateToolsFilesTag( buffer );
			generateExecutableConfigurationsTag( buffer );
			generateReferencesTag( buffer );
			generateFilesTag( buffer );
			generateGlobalsTag( buffer );
			buffer.unIndent();
		buffer.appendLine( "</VisualStudioProject>" );
		
		Util.writeContents( new File( _rootDirectory + fileSep + _currentProjectName + ".vcproj" ), buffer.getBuffer() );
	}
	
	/**
	 * Adds the main project tag to the buffer
	 * @param buffer
	 */
	private void generateVisualStudioProjectTag( LineStringBuffer buffer )
	{	
		buffer.appendLine( "<VisualStudioProject " );
		buffer.indent();
			buffer.appendLine( getAttributeString( "ProjectType", "Visual C++" ) );
			buffer.appendLine( getAttributeString( "Version", "9.00" ) );
			buffer.appendLine( getAttributeString( "Name", _currentProjectName ) );
			buffer.appendLine( getAttributeString( "RootNamespace", _currentProjectName ) );
			buffer.appendLine( getAttributeString( "Keyword", "Win32Proj" ) );
			buffer.appendLine( getAttributeString( "TargetFrameworkVersion", "0" ) );
			buffer.appendLine( ">" );
			
	}

	/**
	 * Adds the Platforms tag to the buffer
	 * @param buffer
	 */
	private void generatePlatformsTag( LineStringBuffer buffer )
	{
		buffer.appendLine( "<Platforms>" );
		buffer.indent();
			buffer.appendLine( "<Platform "  );
			buffer.indent();
				buffer.appendLine( getAttributeString( "Name", "Win32" ) );
				buffer.unIndent();
			buffer.appendLine( "/>" );
			buffer.unIndent();
		buffer.appendLine( "</Platforms>" );
	}
	
	/**
	 * Adds a ToolFiles tag to the buffer
	 * @param buffer
	 */
	private void generateToolsFilesTag( LineStringBuffer buffer )
	{
		buffer.appendLine( "<ToolFiles>" );
		buffer.appendLine( "</ToolFiles>" );
	}
	
	/**
	 * Adds the dll configuration to the buffer
	 * @param buffer
	 */
	private void generateLibraryConfigurationsTag( LineStringBuffer buffer )
	{
		buffer.appendLine( "<Configurations>" );
		buffer.indent();
			buffer.appendLine( "<Configuration" );
			buffer.indent();
				buffer.appendLine( getAttributeString( "Name", "Debug|Win32" ) );
				buffer.appendLine( getAttributeString( "OutputDirectory", ".\\lib" ) );
				buffer.appendLine( getAttributeString( "IntermediateDirectory", ".\\Build\\" + _currentProjectName) );
				buffer.appendLine( getAttributeString( "ConfigurationType", "2" ) );
				buffer.appendLine( ">" );
				buffer.appendLine( "<Tool" );
				buffer.indent();
					buffer.appendLine( getAttributeString( "Name", "VCCLCompilerTool" ) );
					buffer.appendLine( getAttributeString( "AdditionalOptions", "/MP" ) );
					buffer.appendLine( getAttributeString( "Optimization", "0" ) );
					buffer.appendLine( getAttributeString( "AdditionalIncludeDirectories", ".\\;.\\include;&quot;$(JTS_COMMON_PATH)\\include&quot;" ) );
					buffer.appendLine( getAttributeString( "PreprocessorDefinitions", "WIN32;_DEBUG;_WINDOWS;_CRT_SECURE_NO_WARNINGS; _CRT_SECURE_NO_DEPRECATE" ) );
					buffer.appendLine( getAttributeString( "MinimalRebuild", "false" ) );
					buffer.appendLine( getAttributeString( "BasicRuntimeChecks", "3" ) );
					buffer.appendLine( getAttributeString( "RuntimeLibrary", "3" ) );
					buffer.appendLine( getAttributeString( "UsePrecompiledHeader", "0" ) );
					buffer.appendLine( getAttributeString( "WarningLevel", "3" ) );
					buffer.appendLine( getAttributeString( "Detect64BitPortabilityProblems", "true" ) );
					buffer.appendLine( getAttributeString( "DebugInformationFormat", "4" ) );
					buffer.unIndent();
				buffer.appendLine( "/>" );
				buffer.appendLine( "<Tool" );
				buffer.indent();
					buffer.appendLine( getAttributeString( "Name", "VCLinkerTool" ) );
					buffer.appendLine( getAttributeString( "AdditionalDependencies", getLibDependencies() ) );
					buffer.appendLine( getAttributeString( "AdditionalLibraryDirectories", getLibDirectories() ) );
					buffer.appendLine( getAttributeString( "LinkIncremental", "2" ) );
					buffer.appendLine( getAttributeString( "GenerateDebugInformation", "true" ) );
					buffer.appendLine( getAttributeString( "SubSystem", "2" ) );
					buffer.appendLine( getAttributeString( "RandomizedBaseAddress", "1" ) );
					buffer.appendLine( getAttributeString( "DataExecutionPrevention", "0" ) );
					buffer.appendLine( getAttributeString( "TargetMachine", "1" ) );
					buffer.unIndent();
				buffer.appendLine( "/>" );
				buffer.unIndent();
			buffer.appendLine( "</Configuration>" );
			
			buffer.appendLine( "<Configuration" );
			buffer.indent();
				buffer.appendLine( getAttributeString( "Name", "Release|Win32" ) );
				buffer.appendLine( getAttributeString( "OutputDirectory", ".\\lib" ) );
				buffer.appendLine( getAttributeString( "IntermediateDirectory", ".\\Build\\" + _currentProjectName) );
				buffer.appendLine( getAttributeString( "ConfigurationType", "2" ) );
				buffer.appendLine( ">" );
				buffer.appendLine( "<Tool" );
				buffer.indent();
					buffer.appendLine( getAttributeString( "Name", "VCCLCompilerTool" ) );
					buffer.appendLine( getAttributeString( "Optimization", "2" ) );
					buffer.appendLine( getAttributeString( "AdditionalIncludeDirectories", ".\\include;&quot;$(JTS_COMMON_PATH)\\include&quot;" ) );
					buffer.appendLine( getAttributeString( "PreprocessorDefinitions", "WIN32;NDEBUG;_WINDOWS;_CRT_SECURE_NO_WARNINGS" ) );
					buffer.appendLine( getAttributeString( "RuntimeLibrary", "2" ) );
					buffer.appendLine( getAttributeString( "UsePrecompiledHeader", "0" ) );
					buffer.appendLine( getAttributeString( "WarningLevel", "3" ) );
					buffer.appendLine( getAttributeString( "Detect64BitPortabilityProblems", "true" ) );
					buffer.appendLine( getAttributeString( "DebugInformationFormat", "3" ) );
					buffer.unIndent();
				buffer.appendLine( "/>" );
				buffer.appendLine( "<Tool" );
				buffer.indent();
					buffer.appendLine( getAttributeString( "Name", "VCLinkerTool" ) );
					buffer.appendLine( getAttributeString( "AdditionalDependencies", getLibDependencies() ) );
					buffer.appendLine( getAttributeString( "AdditionalLibraryDirectories", getLibDirectories() ) );
					buffer.appendLine( getAttributeString( "LinkIncremental", "0" ) );
					buffer.appendLine( getAttributeString( "GenerateDebugInformation", "false" ) );
					buffer.appendLine( getAttributeString( "SubSystem", "2" ) );
					buffer.appendLine( getAttributeString( "OptimizeReferences", "2" ) );
					buffer.appendLine( getAttributeString( "EnableCOMDATFolding", "2" ) );
					buffer.appendLine( getAttributeString( "RandomizedBaseAddress", "1" ) );
					buffer.appendLine( getAttributeString( "DataExecutionPrevention", "0" ) );
					buffer.appendLine( getAttributeString( "TargetMachine", "1" ) );
					buffer.unIndent();
				buffer.appendLine( "/>" );
				buffer.unIndent();
			buffer.appendLine( "</Configuration>" );
		buffer.appendLine( "</Configurations>" );
	}

	/**
	 * Adds the an executable configuration to the buffer
	 * @param buffer
	 */
	private void generateExecutableConfigurationsTag( LineStringBuffer buffer )
	{
		buffer.appendLine( "<Configurations>" );
		buffer.indent();
			buffer.appendLine( "<Configuration" );
			buffer.indent();
				buffer.appendLine( getAttributeString( "Name", "Debug|Win32" ) );
				buffer.appendLine( getAttributeString( "OutputDirectory", ".\\bin" ) );
				buffer.appendLine( getAttributeString( "IntermediateDirectory", ".\\Build\\" + _currentProjectName) );
				buffer.appendLine( getAttributeString( "ConfigurationType", "1" ) );
				buffer.appendLine( ">" );
				buffer.appendLine( "<Tool" );
				buffer.indent();
					buffer.appendLine( getAttributeString( "Name", "VCCLCompilerTool" ) );
					buffer.appendLine( getAttributeString( "AdditionalOptions", "/MP" ) );
					buffer.appendLine( getAttributeString( "Optimization", "0" ) );
					buffer.appendLine( getAttributeString( "AdditionalIncludeDirectories", ".\\;.\\include;&quot;$(JTS_COMMON_PATH)\\include&quot;" ) );
					buffer.appendLine( getAttributeString( "PreprocessorDefinitions", "WIN32;_DEBUG;_WINDOWS;_CRT_SECURE_NO_WARNINGS; _CRT_SECURE_NO_DEPRECATE" ) );
					buffer.appendLine( getAttributeString( "MinimalRebuild", "false" ) );
					buffer.appendLine( getAttributeString( "BasicRuntimeChecks", "3" ) );
					buffer.appendLine( getAttributeString( "RuntimeLibrary", "3" ) );
					buffer.appendLine( getAttributeString( "UsePrecompiledHeader", "0" ) );
					buffer.appendLine( getAttributeString( "WarningLevel", "3" ) );
					buffer.appendLine( getAttributeString( "Detect64BitPortabilityProblems", "true" ) );
					buffer.appendLine( getAttributeString( "DebugInformationFormat", "4" ) );
					buffer.unIndent();
				buffer.appendLine( "/>" );
				buffer.appendLine( "<Tool" );
				buffer.indent();
					buffer.appendLine( getAttributeString( "Name", "VCPostBuildEventTool" ) );
					buffer.appendLine( getAttributeString( "Description", "Copy DLLs to local directory" ) );
					buffer.appendLine( getAttributeString( "CommandLine", getDllDependencies() ) );
					buffer.unIndent();
				buffer.appendLine( "/>" );
				buffer.appendLine( "<Tool" );
				buffer.indent();
					buffer.appendLine( getAttributeString( "Name", "VCLinkerTool" ) );
					buffer.appendLine( getAttributeString( "AdditionalDependencies", getLibDependencies() ) );
					buffer.appendLine( getAttributeString( "AdditionalLibraryDirectories", getLibDirectories() ) );
					buffer.appendLine( getAttributeString( "LinkIncremental", "2" ) );
					buffer.appendLine( getAttributeString( "GenerateDebugInformation", "true" ) );
					buffer.appendLine( getAttributeString( "SubSystem", "1" ) );
					buffer.appendLine( getAttributeString( "RandomizedBaseAddress", "1" ) );
					buffer.appendLine( getAttributeString( "DataExecutionPrevention", "0" ) );
					buffer.appendLine( getAttributeString( "TargetMachine", "1" ) );
					buffer.unIndent();
				buffer.appendLine( "/>" );
				buffer.unIndent();
			buffer.appendLine( "</Configuration>" );
			
			buffer.appendLine( "<Configuration" );
			buffer.indent();
				buffer.appendLine( getAttributeString( "Name", "Release|Win32" ) );
				buffer.appendLine( getAttributeString( "OutputDirectory", ".\\bin" ) );
				buffer.appendLine( getAttributeString( "IntermediateDirectory", ".\\Build\\" + _currentProjectName) );
				buffer.appendLine( getAttributeString( "ConfigurationType", "1" ) );
				buffer.appendLine( ">" );
				buffer.appendLine( "<Tool" );
				buffer.indent();
					buffer.appendLine( getAttributeString( "Name", "VCCLCompilerTool" ) );
					buffer.appendLine( getAttributeString( "Optimization", "2" ) );
					buffer.appendLine( getAttributeString( "AdditionalIncludeDirectories", ".\\include;&quot;$(JTS_COMMON_PATH)\\include&quot;" ) );
					buffer.appendLine( getAttributeString( "PreprocessorDefinitions", "WIN32;NDEBUG;_WINDOWS;_CRT_SECURE_NO_WARNINGS" ) );
					buffer.appendLine( getAttributeString( "RuntimeLibrary", "2" ) );
					buffer.appendLine( getAttributeString( "UsePrecompiledHeader", "0" ) );
					buffer.appendLine( getAttributeString( "WarningLevel", "3" ) );
					buffer.appendLine( getAttributeString( "Detect64BitPortabilityProblems", "true" ) );
					buffer.appendLine( getAttributeString( "DebugInformationFormat", "3" ) );
					buffer.unIndent();
				buffer.appendLine( "/>" );
				buffer.appendLine( "<Tool" );
				buffer.indent();
					buffer.appendLine( getAttributeString( "Name", "VCPostBuildEventTool" ) );
					buffer.appendLine( getAttributeString( "Description", "Copy DLLs to local directory" ) );
					buffer.appendLine( getAttributeString( "CommandLine", getDllDependencies() ) );
					buffer.unIndent();
				buffer.appendLine( "/>" );
				buffer.appendLine( "<Tool" );
				buffer.indent();
					buffer.appendLine( getAttributeString( "Name", "VCLinkerTool" ) );
					buffer.appendLine( getAttributeString( "AdditionalDependencies", getLibDependencies() ) );
					buffer.appendLine( getAttributeString( "AdditionalLibraryDirectories", getLibDirectories() ) );
					buffer.appendLine( getAttributeString( "LinkIncremental", "0" ) );
					buffer.appendLine( getAttributeString( "GenerateDebugInformation", "false" ) );
					buffer.appendLine( getAttributeString( "SubSystem", "1" ) );
					buffer.appendLine( getAttributeString( "OptimizeReferences", "2" ) );
					buffer.appendLine( getAttributeString( "EnableCOMDATFolding", "2" ) );
					buffer.appendLine( getAttributeString( "RandomizedBaseAddress", "1" ) );
					buffer.appendLine( getAttributeString( "DataExecutionPrevention", "0" ) );
					buffer.appendLine( getAttributeString( "TargetMachine", "1" ) );
					buffer.unIndent();
				buffer.appendLine( "/>" );
				buffer.unIndent();
			buffer.appendLine( "</Configuration>" );
		buffer.appendLine( "</Configurations>" );
	}
	
	/**
	 * Gets a string with all the dlls needed for the project
	 * @return
	 */
	private String getDllDependencies()
	{
		String dllString = "";
		
		// include all service dlls in string
		for( String name : _serviceNames )
		{
			dllString += "copy .\\lib\\" + name + ".dll .\\bin&#x0D;&#x0A;";
		}
		
		// copy common.dll to executable dir
		dllString += "copy &quot;%JTS_COMMON_PATH%&quot;\\lib\\Common.dll .\\bin&#x0D;&#x0A;";
		
		return dllString;
	}
	
	/**
	 * Gets the string which includes the common directory and all the libraries needed for the project
	 * @return
	 */
	private String getLibDependencies()
	{
		String libs = "Common.lib ";
		
		// only include libs of inherited services
		for( String name : _serviceNames )
		{
			// add all services libs when creating the component
			// or stop adding when current project is found
			// We can do this because the service names will be in order already
			if( !isCreatingComponent() && name.compareTo( _currentProjectName ) == 0 )
			{
				break;
			}
			
			libs += name + ".lib ";
		}
		
		return libs;
	}
	
	/**
	 * Gets the library directory string which includes the common lib and local lib directory
	 * @return
	 */
	private String getLibDirectories()
	{
		String libs = "$(JTS_COMMON_PATH)\\lib;.\\lib";
		
		return libs;
	}
	
	/**
	 * Adds the References tag to the buffer
	 * @param buffer
	 */
	private void generateReferencesTag( LineStringBuffer buffer )
	{
		buffer.appendLine( "<References>" );
		buffer.appendLine( "</References>" );
	}
	
	/**
	 * Adds the Files filter to the buffer which includes source and header filters
	 * @param buffer
	 */
	private void generateFilesTag( LineStringBuffer buffer )
	{
		buffer.appendLine( "<Files>" );
		buffer.indent();

			generateHeaderFilter( buffer );
			generateSourceFilter( buffer );
		
		buffer.appendLine( "</Files>" );
	}
	
	/**
	 * Adds a header file filter to the buffer
	 * @param buffer
	 */
	private void generateHeaderFilter( LineStringBuffer buffer )
	{
		generateFilter( buffer, getServiceHeaderPath(), "Header Files", "h" );
	}
	
	/**
	 * Adds a source file filter to the buffer
	 * @param buffer
	 */
	private void generateSourceFilter( LineStringBuffer buffer )
	{
		generateFilter( buffer, getServiceSourcePath(), "Source Files", "cpp" );
	}
	
	/**
	 * Adds a file filter to the buffer
	 * @param buffer
	 * @param path
	 * @param filterName
	 * @param filterType
	 */
	private void generateFilter( LineStringBuffer buffer, File path, String filterName, String filterType )
	{
		TreeSet< String > subFiles = getSubFileNames( path );
		
		buffer.appendLine( "<Filter" );
		buffer.indent();
			buffer.appendLine( getAttributeString( "Name", filterName ) );
			buffer.appendLine( getAttributeString( "Filter", filterType ) );
			buffer.appendLine( ">" );

		// generate filters for sub directories but only for services, not the component
		if( !isCreatingComponent() )
		{
			TreeSet< File > subDirectories = getSubDirectories( path );
			for( File file : subDirectories )
			{
				buffer.indent();
					generateFilter( buffer, file, getFilterNameFromPath( file ), filterType );
					buffer.unIndent();
			}
		}			
			
		// generate file tages for all other files in current directory
		for( String fileName : subFiles )
		{
			buffer.appendLine( "<File" );
			buffer.indent();
				buffer.appendLine( getAttributeString( "RelativePath", getRelativeFilePath( fileName ) ) );
				buffer.appendLine( ">" );
				buffer.unIndent();
			buffer.appendLine( "</File>" );
		}
			
			buffer.unIndent();
		buffer.appendLine( "</Filter>" );
	}
	
	/**
	 * Tells whether we are currently generating the code for a component
	 * @return
	 */
	private boolean isCreatingComponent()
	{
		return _currentProjectName.compareTo( _componentName ) == 0;
	}
	
	/**
	 * Adds a relative path tag to the input string
	 * @param path
	 * @return
	 */
	private String getRelativeFilePath( String path )
	{
		return ".\\" + path; 
	}

	/**
	 * Gets the source path depending on what is currently being generated
	 * @return
	 */
	private File getServiceSourcePath()
	{
		if( isCreatingComponent() )
		{
			return new File( _rootDirectory.getPath() + fileSep + "src" );
		}
		
		return new File( _rootDirectory.getPath() + fileSep + "src" + fileSep + _currentProjectName );
	}

	/**
	 * Gets the header path depending on what is currently being generated
	 * @return
	 */
	private File getServiceHeaderPath()
	{
		if( isCreatingComponent() )
		{
			return new File( _rootDirectory.getPath() + fileSep + "include" );
		}
		
		return new File( _rootDirectory.getPath() + fileSep + "include" + fileSep + _currentProjectName );
	}
	
	/**
	 * Adds the Globals tag to the buffer
	 * @param buffer
	 */
	private void generateGlobalsTag( LineStringBuffer buffer )
	{
		buffer.appendLine( "<Globals>" );
		buffer.appendLine( "</Globals>" );
	}
	
	/**
	 * Gets a list of all the service names contained in the root directory
	 * @return
	 */
	private TreeSet< String > getServices()
	{
		TreeSet< String > services = new TreeSet< String >();
		
		// look in root directory include directory to make list of services that are included
		File lookup = new File( _rootDirectory + fileSep + "include" );
		
		for( File file : lookup.listFiles() )
		{
			if( file.isDirectory() )
			{
				services.add( file.getName() );
			}
		}
		
		return services;
	}

	/**
	 * Looks through the provided directory and returns a Set with a list of the all the sub paths that contain include files
	 * 
	 * @param dir
	 * @return
	 */
	private TreeSet<String> getIncludePaths(File dir) throws Exception
	{
		if (dir.isDirectory())
		{
			TreeSet<String> includeSet = new TreeSet<String>();
			
			for (File file: dir.listFiles())
			{
				if (file.isDirectory())
				{
					includeSet.addAll(getIncludePaths(file));
				}
				else
				{
					if (file.getName().endsWith(".h"))
					{
						includeSet.add(file.getParent());
					}
				}
			}
			
			return includeSet;
		}
		else
		{
			throw new Exception("ComponentGenerator::getIncludePaths(): Invalid Directory Provided");
		}
	}
	
	/**
	 * Returns all the cpp and h files within the provided current directory
	 * Does not look in sub folders
	 * @param dir
	 * @return
	 * @throws Exception
	 */
	private TreeSet<String> getSubFileNames( File dir )
	{
		TreeSet<String> srcSet = new TreeSet<String>();
		int toSkip = _rootDirectory.getPath().length();
		
		for( File file: dir.listFiles())
		{
			if( !file.isDirectory() )
			{
				if( file.getName().endsWith(".cpp") || file.getName().endsWith(".h") )
				{
					srcSet.add( file.getPath().substring( toSkip + 1) );
				}
			}
		}
		
		return srcSet;
	}
	
	/**
	 * A list of all files in current and in all sub directories
	 * @param dir
	 * @return
	 */
	private TreeSet< File > getSubDirectories( File dir )
	{
		TreeSet< File > set = new TreeSet< File >();
		
		for( File file : dir.listFiles() )
		{
			if( file.isDirectory() )
			{
				set.add( file);
			}
		}
		
		return set;
	}
	
	/**
	 * Convenience method for getting the name from a file object
	 * @param path
	 * @return
	 */
	private String getFilterNameFromPath( File path )
	{
		return getFilterNameFromPath( path.getPath() );
	}
	
	/**
	 * Gets the last portion of a file path, from the last 
	 * separator to the end of the string
	 * @param name
	 * @return
	 */
	private String getFilterNameFromPath( String name )
	{
		return name.substring( name.lastIndexOf( fileSep ) + 1 );
	}
	
	
}
