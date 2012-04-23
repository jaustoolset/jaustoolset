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

package org.jts.codegenerator;

//import java.util.Iterator;
//import java.util.TreeSet;
import java.io.*;
import java.util.List;

//import org.jts.codegenerator.support.FileExtensionFilter;
//import org.jts.codegenerator.support.FileFilter;

public class Util 
{
	/**
	 * Returns string with first letter capitalized
	 * @param name
	 * @return string
	 */
	public static String upperCaseFirstLetter(String name) 
	{
        return name.substring(0,1).toUpperCase() + name.substring(1);
    }
	
	/**
	 * Inserts n number of tabs and returns string
	 * @param indentLevel
	 * @return string
	 */
	public static String tabs(int indentLevel) {
        StringBuffer tab = new StringBuffer("    ");
        for(int i=0; i<indentLevel;i++) 
        {
            tab.append("    ");
        }
        return tab.toString();
    }
	
	public static void indent(int indentLevel, List<String> strList)
	{
		int i = 0;
		for (String line: strList)
		{
			strList.set(i++, "\t" + line);
		}
	}
	
	
    static public String getContents(File file) throws Exception
    {
        StringBuffer contents = new StringBuffer();
        BufferedReader input = new BufferedReader(new FileReader(file));
        String line;
        
        while ((line = input.readLine()) != null)
        {
            contents.append(line);
            contents.append(System.getProperty("line.separator"));
        }
        input.close();
        
        return contents.toString();
    }

    
    static public void writeContents(File dst, String contents) throws Exception
    {
	    if (!dst.exists())
	    {
	    	if (!dst.createNewFile())
	    	{
	    		throw new Exception("Could not create Destination File");
	    	}
	    }
	    
        if (dst.isFile() && dst.canWrite())
        {
        	try
        	{
		        Writer output = new BufferedWriter(new FileWriter(dst));
		        output.write(contents);
		        output.close();
        	}
        	catch (Exception e)
        	{
        		throw new Exception("Util [81]: " + e.getMessage());
        	}
        }
        else
        {
        	throw new Exception("Problem writing file: " + dst);
        }

    }

    
    /**
     * Copies a file.  
     * 
     * @param src	the File to be copied
     * @param dst	the File to be created
     */
    static public void copyFile(File src, File dst) throws Exception
    {
    	if (src.isFile())
    	{    	
		    if (!src.exists())
		    {
		    	throw new Exception("Source File does not exist");
	        }
		    
		    if (!dst.exists())
		    {	    	
		    	if (!dst.createNewFile())
		    	{
		    		throw new Exception("Could not create Destination File");
		    	}
		    }
		    
	        if (dst.isFile() && dst.canWrite())
	        {
		        Writer output = new BufferedWriter(new FileWriter(dst));
		        output.write(getContents(src));
		        output.close();
	        }
	        else
	        {
	        	throw new Exception("Problem writing file: " + dst);
	        }
    	}
    	else
    	{
    		throw new Exception("Util: Invalid File Provided");
    	}
    }
    
    
    /**
     * Deletes the directory and all sub directories given by the string
     * @param path
     * @return
     */
    static public boolean deleteDir(String path)
    {
    	return deleteDir(new File(path));
    }
    
    
    /**
     * Deletes the directory and all sub directories given by the File Object
     * @param path
     * @return
     */
    static public boolean deleteDir(File path)
    {
//    	File path = new File(dir);
    	
    	if (path.exists())
    	{
    		File[] files = path.listFiles();
    		for (File cFile : files)
    		{
    			if (cFile.isDirectory())
    			{
    				deleteDir(cFile);
    			}
    			else
    			{
    				cFile.delete();
    			}
    		}
    	}
    	return path.delete();
    }
    
    /**
     * Takes the String input and removes all spaces. All words that were preceded with a space will be
     * capitalized. E.g: the brown fox ==> TheBrownFox
     * @param input
     * @return
     */
    static public String makeOneWord(String input)
    {
    	String temp = input;
    	String retStr = new String();
    	boolean spaceFound = false;
    	char cChar;
    	
    	/// Remove extra spaces at the start and end
    	temp.trim();
    	for (int i = 0; i < temp.length(); i++)
    	{
    		cChar = temp.charAt(i);
    		if (cChar == ' ')
    		{
    			spaceFound = true;
    		}
    		else
    		{
    			if (spaceFound)
    			{
    				spaceFound = false;
        			retStr += temp.substring(i, i + 1).toUpperCase();
    			}
    			else
    			{
        			retStr += cChar;
    			}
    		}
    	}
    	
    	return Util.upperCaseFirstLetter(retStr);
    }
    
    /**
     * Copies a directory to another location recursively
     * 
     * @param targetDir
     */
    static public void copyDirectory(File src, File dest) throws Exception
    {
    	if (src.isDirectory())
    	{
	    	File destFile = new File(dest.getAbsolutePath() + "/" + src.getName());
	    	
	       	if (!destFile.exists())
	    	{
	    		if (!destFile.mkdirs())
	    		{
	    			throw new Exception("Could not Create " + destFile.getAbsolutePath());
	    		}
	    	}
	 
	    	for (File srcFile : src.listFiles())
	    	{
	    		if (srcFile.isDirectory())
	    		{
	    			copyDirectory(srcFile, destFile);
	    		}
	    		else
	    		{
		    		try
		    		{
		    			copyFile(srcFile, new File(destFile.getAbsolutePath() + "/" + srcFile.getName()));
		    		}
		    		catch (Exception e)
		    		{
		    			throw new Exception("Util [236]: " + e.toString());
		    		}
	    		}
	    	}
    	}
    	else
    	{
    		throw new Exception("Util [243]: Invalid Directory provided");
    	}
    }
}
