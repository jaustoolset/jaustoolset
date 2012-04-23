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
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import org.jts.codegenerator.CodeGeneratorException;
import org.jts.codegenerator.Util;


public class SconstructGenerator
{
    private static final int CROP_TPL_NAME = 4;

	public SconstructGenerator()
	{
	}

	/**
	 * 
	 * @param outDir
	 * @param name
	 * @param libs	List<String> will fully qualified library names
	 * @return
	 */
	public String generateLibrary(File outDir, String name, List<String> libs)
	{
		String fileSep = System.getProperty("file.separator");
		List<String> libPaths = new ArrayList<String>();
		List<String> libNames = new ArrayList<String>();
		TreeSet<String> srcSet;
    	StringBuffer buf = new StringBuffer();
    	boolean first = false;    	
    	String outDirStr = dirToString( outDir );
    	
    	/// Extract
    	for (String lib : libs)
    	{
    		String path = lib.substring(0, lib.lastIndexOf("/")); 
    		String libname = lib.substring(lib.lastIndexOf("/")+1, lib.length()); 

    		if (path.compareTo("") != 0)
    		{
    			libPaths.add(path);
//    			System.out.println(path + " length = " + path.length());
    		}
    		
    		if (libname.compareTo("") != 0)
    		{
    			libNames.add(libname);
    		}
    	}
    	
    	
        // Create dependent Scons file.
        buf.append("VariantDir('Build', '.', duplicate=0)").append(System.getProperty("line.separator"));    		
		buf.append("sources = [");
		try
		{
		    srcSet = getSourceFiles(new File(outDir.getPath()), false);

			first = true;
			for (Iterator<String> it = srcSet.iterator(); it.hasNext(); )
			{
				if (!first)
				{
					buf.append(", ");
				}
				else
				{
					first = false;
				}
				
				String srcFile = it.next();
				
				/// Change to relative path and change src to Build
				buf.append("'Build/" + srcFile.replaceFirst(outDirStr, "").replace(fileSep,"/") + "'");
			}
		}
		catch (Exception e)
		{
			throw new CodeGeneratorException(e.getMessage());
		}
		buf.append("]").append(System.getProperty("line.separator"));
        buf.append("Import('env')").append(System.getProperty("line.separator"));
		buf.append("lib = env.SharedLibrary('./lib/" + name + "', sources, LIBS=['Common'");
		for (String lib: libNames)
		{
			buf.append(", ");	
			buf.append("'" + lib + "'");
		}
		buf.append("])").append(System.getProperty("line.separator"));
		buf.append("env.Install( env['INSTALL_LIB'], lib )").append(System.getProperty("line.separator"));
		buf.append("env.Install( env['BINPATH'], lib )").append(System.getProperty("line.separator"));
    	return buf.toString();
		
	}
	
    public String generateProgram(File outDir, String name, List<String> libs)
    {
		List<String> libPaths = new ArrayList<String>();
		List<String> libNames = new ArrayList<String>();
		
    	StringBuffer buf = new StringBuffer();
    	boolean first = true;

        // All programs rely on the Software Framework in 'Common'.
        // The generated scons file relies on an environmental variable
        // to find the common directory at compile time.  It generates
        // an error to the user if the environmental variable is not set.
        buf.append("import os").append(System.getProperty("line.separator"));
        buf.append("if ('JTS_COMMON_PATH' in os.environ):").append(System.getProperty("line.separator"));
        buf.append("\tcommon_dir = os.environ['JTS_COMMON_PATH']").append(System.getProperty("line.separator"));
        buf.append("else:").append(System.getProperty("line.separator"));
        buf.append("\tprint 'Must define JTS_COMMON_PATH before building'").append(System.getProperty("line.separator"));
        buf.append("\texit(-1)").append(System.getProperty("line.separator"));
        buf.append(System.getProperty("line.separator"));
            	    	
    	/// Extract libraries from the list, parsing path and lib name
    	for (String lib : libs)
    	{
    		String path = lib.substring(0, lib.lastIndexOf("/") + 1); 
    		String libname = lib.substring(lib.lastIndexOf("/") + 1, lib.length()); 

			if (path.compareTo("") != 0)
    		{
    			libPaths.add(path);
    		}
    		
    		if (libname.compareTo("") != 0)
    		{
    			libNames.add(libname);
    		}
    	}

    	// Manually add Common library
		libPaths.add("' + common_dir + '/lib/");
		libNames.add("Common");
    	
	 	try
	 	{
	 		buf.append(generateTopSconstruct(outDir, libPaths));
	 	}
	 	catch (Exception e)
	 	{
	 		throw new CodeGeneratorException(e.getMessage());
	 	}
	 	
	 	// Create the program gen line
		buf.append("env.Program('./bin/").append(name).append("', sources, LIBS=[");

		for (String lib: libNames)
		{
			if (!first)
			{
				buf.append(", ");	
			}
			else
			{
				first = false;
			}
			buf.append("'").append(lib).append("'");
		}
		buf.append("])");
		    	
    	return buf.toString();
    }
    
    
    public String generateTopSconstruct(File outDir, List<String> libPaths) throws Exception
    {
    	String fileSep = System.getProperty("file.separator");
    	TreeSet<String> sconSet;
		TreeSet<String> includePaths;
		TreeSet<String> srcSet;
		StringBuffer buf = new StringBuffer();
		boolean first;
		String outDirStr = dirToString( outDir );
				
		/* If this is the 'top', manually add common includes and lib path */
	    buf.append("VariantDir('Build', 'src', duplicate=0)").append(System.getProperty("line.separator"));
	    
	    buf.append("include = [ '#.', '#include', common_dir + '/include']");
	    buf.append(System.getProperty("line.separator"));
	    
	    buf.append("libpath = ['#lib', common_dir + '/lib']");
	    buf.append(System.getProperty("line.separator"));		

        // Add source files in top level 'src'		    		
		buf.append("sources = [");
		try
		{
		    srcSet = getSourceFiles(new File(outDir.getPath() + "/src"), true);
			
			first = true;
			for (Iterator<String> it = srcSet.iterator(); it.hasNext(); )
			{
				if (!first)
				{
					buf.append(", ");
				}
				else
				{
					first = false;
				}
				
				String srcFile = it.next();
				
				/// Change to relative path and change src to Build
				buf.append("'").append(srcFile.replaceFirst(outDirStr, "").replace("src", "Build").replace(fileSep, "/")).append("'");
			}
		}
		catch (Exception e)
		{
			throw new Exception("3 " + e.getMessage());
		}
		buf.append("]").append(System.getProperty("line.separator"));

        // Set-up platform specific stuff....
		buf.append(System.getProperty("line.separator"));		
		buf.append("# Generate the environment").append(System.getProperty("line.separator"));
		buf.append("env = Environment(ENV=os.environ)").append(System.getProperty("line.separator"));
		buf.append("env.Append( CPPPATH = include)").append(System.getProperty("line.separator"));
		buf.append("env.Append( LIBPATH = libpath)").append(System.getProperty("line.separator"));
		buf.append("env.Append( INSTALL_LIB = '#./lib')").append(System.getProperty("line.separator"));
		buf.append("env.Append( BINPATH = '#./bin')").append(System.getProperty("line.separator"));
		buf.append("").append(System.getProperty("line.separator"));
		buf.append("# Some stuff is platform specific").append(System.getProperty("line.separator"));
		buf.append("if env['PLATFORM'] == 'cygwin':").append(System.getProperty("line.separator"));
		buf.append("\tprint 'scons: Building for CYGWIN...'").append(System.getProperty("line.separator"));
		buf.append("\tenv.Append( CCFLAGS = ['-D__CYGWIN__'] )").append(System.getProperty("line.separator"));
		buf.append("\tenv.Append( LINKFLAGS = ['-Wl,--enable-auto-import'] )").append(System.getProperty("line.separator"));
		buf.append("elif os.name == 'nt':").append(System.getProperty("line.separator"));                     
		buf.append("\tprint 'scons: Building for Windows...'").append(System.getProperty("line.separator"));
		buf.append("\tenv.Append( CCFLAGS = ['-DWIN32', '-DWINDOWS', '/MD', '-EHsc','-D_CRT_SECURE_NO_DEPRECATE'])").append(System.getProperty("line.separator"));
		buf.append("\tenv.Append( LINKFLAGS = ['/DEFAULTLIB:\"WSock32.Lib\"'] )").append(System.getProperty("line.separator"));
		buf.append("elif env['PLATFORM'] == 'darwin':").append(System.getProperty("line.separator"));
		buf.append("\tenv.Append( LINKFLAGS = ['-lpthread'] )").append(System.getProperty("line.separator"));
		buf.append("\tenv.Append( CPPFLAGS = ['-g', '-Wno-write-strings'])").append(System.getProperty("line.separator"));
		buf.append("\tenv.Append( CCFLAGS = ['-D__MAC__'] )").append(System.getProperty("line.separator"));		
		buf.append("elif os.name == 'posix':").append(System.getProperty("line.separator"));
		buf.append("\tenv.Append( LINKFLAGS = ['-lpthread', '-lrt'] )").append(System.getProperty("line.separator"));
		buf.append("\tenv.Append( CPPFLAGS = ['-g', '-Wno-write-strings'])").append(System.getProperty("line.separator"));
		buf.append("Export('env')").append(System.getProperty("line.separator"));
		buf.append(System.getProperty("line.separator"));			
		
		// If this is the top level scons, we need to build all dependent children
	    buf.append("SConscript([");
	    buf.append("common_dir + '/Sconstruct' ");
	
		// Now build the sub directories
		try
		{
			sconSet = getSconstructFiles(outDir);	

			for (Iterator<String> it = sconSet.iterator(); it.hasNext();)
			{
				/// Change to relative path and append to Sconstruct list
				buf.append(", ");				
				String path = it.next().replaceFirst(outDirStr, "");
				buf.append("'").append(path.replace("\\", "/")).append("'");
			}
		}
		catch (Exception e)
		{
			throw new Exception("1 " + e.getMessage());
		}
		buf.append("])").append(System.getProperty("line.separator"));
		
    	return buf.toString();
    }
    
	/**
	 * Looks through the provided directory and returns a Set with a list of the all the sub paths that contain include files
	 * 
	 * @param dir
	 * @return
	 */
	public TreeSet<String> getIncludePaths(File dir) throws Exception
	{
		if (dir.isDirectory())
		{
			TreeSet<String> includeSet = new TreeSet<String>();
			
			for (File file: dir.listFiles())
			{
//				System.out.println("Looking at:" + file.getAbsolutePath());
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
			
//			System.out.println(includeSet.toString());
			return includeSet;
		}
		else
		{
			throw new Exception("ComponentGenerator::getIncludePaths(): Invalid Directory Provided");
		}
	}
	
	/**
	 * Returns all the cpp files within the provided dir and all sub folders
	 * @param dir
	 * @return
	 * @throws Exception
	 */
	public TreeSet<String> getSourceFiles(File dir, boolean top) throws Exception
	{
		if (dir.isDirectory())
		{
			TreeSet<String> srcSet = new TreeSet<String>();
			
			for (File file: dir.listFiles())
			{
				if (file.isDirectory() && !top)
				{
					srcSet.addAll(getSourceFiles(file, false));
				}
				else
				{
					if (file.getName().endsWith(".cpp"))
					{
						srcSet.add(file.getPath());
					}
				}
			}
			
			return srcSet;
		}
		else
		{
			throw new Exception("ComponentGenerator::getIncludePaths(): Invalid Directory Provided");
		}
	}
	
	
	/**
	 * Checks all the folders one level down from the of the provided dir
	 * if they have an Sconstruct file. If it does then it adds the path of the file
	 * to the TreeSet<String>.
	 * 
	 * @param dir
	 * @param level
	 * @return
	 */
	public TreeSet<String> getSconstructFiles(File dir) throws Exception
	{
		String fileSep = System.getProperty("file.separator");

		if (dir.isDirectory())
		{
			TreeSet<String> sconsSet = new TreeSet<String>();
					
			for (File file: dir.listFiles())
			{
				if (file.isDirectory())
				{
					if (hasSconstruct(file))
					{
						sconsSet.add(file.getPath() + "/Sconstruct");
					}

					if (hasSconscript(file))
					{
						sconsSet.add(file.getPath() + "/Sconscript");
					}
					
					// Search through all nesting layers...
					sconsSet.addAll(getSconstructFiles(file));
				}
			}
			
			return sconsSet;
		}
		else
		{
			throw new Exception("ComponentGenerator::getIncludePaths(): Invalid Directory Provided");
		}
	}
	
	
	/**
	 * Return true of the passed directory has a Sconstruct file
	 * 
	 * @param dir
	 * @return
	 * @throws Exception
	 */
	public boolean hasSconstruct(File dir) throws Exception
	{	
		if (dir.isDirectory())
		{
			if (dir.listFiles(new FileFilter("Sconstruct")).length > 0)
			{
				return true;
			}
			
			return false;
		}
		else
		{
			throw new Exception("Util::hasSconstruct(): Invalid Directory Provided");
		}
	}

    /**
	 * Return true of the passed directory has a Sconscript file
	 * 
	 * @param dir
	 * @return
	 * @throws Exception
	 */
	public boolean hasSconscript(File dir) throws Exception
	{	
		if (dir.isDirectory())
		{
			if (dir.listFiles(new FileFilter("Sconscript")).length > 0)
			{
				return true;
			}
			
			return false;
		}
		else
		{
			throw new Exception("Util::hasSconscript(): Invalid Directory Provided");
		}
	}

    /**
     * Create the Java SConstruct file from the template located in the Common folder.
     *
     * @param outDir the location for the generated SConstruct
     * @param name the name of the component
     */
    public void generateJavaSconstruct(File outDir, String name, String transportVersion)
    {      
        Hashtable<String, String> replaceTable = new Hashtable<String, String>();

        replaceTable.put("%file_name%", name);
        if(transportVersion.equals("1.1"))
        {
            replaceTable.put("%transport_version%", "1_1");
        }
        else if(transportVersion.equals("1.0"))
        {
            replaceTable.put("%transport_version%", "1_0");
        }
        else
        {
            // Default to 1.0.
            replaceTable.put("%transport_version%", "1_0");
        }
        TemplateHandler tplHandler = new TemplateHandler(replaceTable);
        // Handle Eclipse relative paths.
        File templateDir;
        if(new File("plugins/org.jts.eclipse.data_1.0/templates/Common").exists())
        {
            templateDir = new File("plugins/org.jts.eclipse.data_1.0/templates/Common/libJava");
        }else
        {
            templateDir = new File("templates/Common/libJava");
        }
        for (File template : templateDir.listFiles(new FileExtensionFilter("tpl")))
        {
            String destFileName = tplHandler.adjustString(template.getName().substring(0, template.getName().length() - CROP_TPL_NAME));
            File dest = null;

            if (destFileName.equalsIgnoreCase("Sconstruct"))
            {
                dest = new File(outDir + "/" + destFileName);

            }
            try
            {
                System.out.println("Trying to copy file to:" + dest.getAbsolutePath());
                Util.copyFile(template, dest);
                tplHandler.adjustFile(dest);
            }
            catch (Exception e)
            {
                throw new CodeGeneratorException("Could not Copy File [SDG]");
            }
        }

    }

    /**
     * Creates the C# SConstruct by cycling through the directories
     *      and pulling all the file names that need to be compiled.
     *
     * Also copies the scons tools into the generated code.
     *
     * @param outDir the directory for the generated SConstruct file
     * @param name the name of the component
     */
    public void generateCSharpSconstruct(File outDir, String name, String transportVersion, List<String> libs)
    {
        Hashtable<String, String> replaceTable = new Hashtable<String, String>();
		StringBuffer source_list = new StringBuffer();
		StringBuffer build_list = new StringBuffer();
		StringBuffer service_libs = new StringBuffer();
    	String outDirStr = dirToString( outDir );

        replaceTable.put("%file_name%", name);

		// generate the list of source files
		for (String file : getCSharpFiles(new File(outDirStr + "src" + System.getProperty("file.separator")), false))
		{
		    source_list.append(file);
		}
        replaceTable.put("%source_list%", source_list.toString());
		
	    // specify the transport version
        if(transportVersion.equals("1.1"))
        {
            replaceTable.put("%transport_version%", "1_1");
        }
        else if(transportVersion.equals("1.0"))
        {
            replaceTable.put("%transport_version%", "1_0");
        }
        else
        {
            // Default to 1.0.
            replaceTable.put("%transport_version%", "1_0");
        }

		// Add each service as a dependent library
		boolean first = true;
		for (String service : libs)
		{
		    String[] parsed = service.split(":");
			if (!first) build_list.append(", "); first = false;
		    build_list.append("'src" + System.getProperty("file.separator") + parsed[0] + System.getProperty("file.separator") + "Sconscript'");
			service_libs.append(", '" + parsed[1] + "'");
		}
		replaceTable.put("%service_libs%", service_libs.toString());
		replaceTable.put("%build_list%", build_list.toString());

        TemplateHandler tplHandler = new TemplateHandler(replaceTable);
        // Handle Eclipse relative paths.
        File scons;
        if(new File("plugins/org.jts.eclipse.data_1.0/templates/Common").exists())
        {
            scons = new File("plugins/org.jts.eclipse.data_1.0/templates/Common/libCSharp/Sconstruct.tpl");
        }else
        {
            scons = new File("templates/Common/libCSharp/Sconstruct.tpl");
        }

        String destFileName = tplHandler.adjustString(scons.getName().substring(0, scons.getName().length() - CROP_TPL_NAME));
        File dest = null;

        if (destFileName.equalsIgnoreCase("Sconstruct"))
        {
            dest = new File(outDir + "/" + destFileName);
        }

        try
        {
            Util.copyFile(scons, dest);
            tplHandler.adjustFile(dest);
        }
        catch (Exception e)
        {
            throw new CodeGeneratorException("Could not Copy File [SDG]");
        }

        try
        {
            // Handle Eclipse relative paths.
            File scons_tool;
            if(new File("plugins/org.jts.eclipse.data_1.0/templates/Common").exists())
            {
                scons_tool = new File("plugins/org.jts.eclipse.data_1.0/templates/Common/libCSharp/site_scons");
            }else
            {
                scons_tool = new File("templates/Common/libCSharp/site_scons");
            }
            Util.copyDirectory(scons_tool, outDir);
        }
        catch(Exception e)
        {

        }
    }

    /**
     * Creates the C# Sconscript (library builder) by cycling through the directories
     *      and pulling all the file names that need to be compiled.
     *
     * Also copies the scons tools into the generated code.
     *
     * @param outDir the directory for the generated SConstruct file
     * @param name the name of the service
     */
    public void generateCSharpSconscript(File outDir, String name, List<String> libs)
    {
        Hashtable<String, String> replaceTable = new Hashtable<String, String>();
		StringBuffer service_libs = new StringBuffer();
		StringBuffer source_list = new StringBuffer();

		// Replace the name of the target
		replaceTable.put("%service_name%", name);

		// generate the list of source files
		for (String file : getCSharpFiles(outDir, true))
		{
			// files come back with as "src/namespace/<files>".  We need to strip it
			try {
				file = file.substring(file.indexOf( System.getProperty("file.separator")) + 1);
				file = file.substring(file.indexOf( System.getProperty("file.separator")) + 1);
			} catch (Exception e){}
		    source_list.append("\t\t" + file);
		}
        replaceTable.put("%source_list%", source_list.toString());

		// Add each parent library as a dependent
		for (String service : libs)
		{
			service_libs.append(", '" + service + "'");
		}
		replaceTable.put("%service_libs%", service_libs.toString());

        TemplateHandler tplHandler = new TemplateHandler(replaceTable);
        File scons;
        if(new File("plugins/org.jts.eclipse.data_1.0/templates").exists())
        {
            scons = new File("plugins/org.jts.eclipse.data_1.0/templates/Common/libCSharp/Sconscript.tpl");
        }else
        {
            scons = new File("templates/Common/libCSharp/Sconscript.tpl");
        }

        String destFileName = tplHandler.adjustString(scons.getName().substring(0, scons.getName().length() - CROP_TPL_NAME));
        File dest = null;

        if (destFileName.equalsIgnoreCase("Sconscript"))
        {
            dest = new File(outDir + "/" + destFileName);
        }

        try
        {
            Util.copyFile(scons, dest);
            tplHandler.adjustFile(dest);
        }
        catch (Exception e)
        {
            throw new CodeGeneratorException("Could not Copy File [SDG]");
        }
    }

    /**
     * Looks through component directories for .cs files to add to the
     * SConstruct source list.
     * @param  the project root directory
	 * @param  boolean option to recurse into subdirectories
     * @return the formatted list of files.
     */
    private List<String> getCSharpFiles(File outDir, boolean recurse)
    {
        List<String> files = new ArrayList<String>();

        for (File f:outDir.listFiles())
        {
            
            if (f.isDirectory() && recurse)
            {
                files.addAll(getCSharpFiles(f, recurse));
            }
            else if (f.isFile() && f.getName().endsWith(".cs"))
            {
                String s = f.getPath();
                files.add("\t" + s.substring(s.indexOf("src")) + System.getProperty("line.separator"));
            }
        }

        return files;
    
    }

	private String dirToString( File outDir )
	{
    	String outDirStr = null;
		if (System.getProperty("file.separator").compareTo("\\") == 0)
		{
	    	outDirStr = outDir.getPath().replace("\\", "\\\\") + "\\\\";
		}
		else
		{
			outDirStr = outDir.getPath() + System.getProperty("file.separator");
		}

		return outDirStr;
	}
}