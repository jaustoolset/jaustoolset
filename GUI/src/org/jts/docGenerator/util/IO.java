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

package org.jts.docGenerator.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/** Set of static methods for commonly used IO operations.
*
* @author Arfath Pasha $Date: 2005/06/13 16:38:56 $ $Revision: 1.1.1.1 $
*
*/
public class IO
{
   /** Returns an input stream for the specified file, or null if unable to 
   * build the input stream.
   *
   */
	/*
   public static InputStream getInputStream( File file )
   {
      if( file == null )
         throw new IllegalArgumentException(" null file name. ");
      
      try {
         // read in xml file 
         InputStream is = null;
         
         if(file.exists())
         {
            // move xml from file to byte array
            byte [] xmlBytes = new byte[(int)file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(xmlBytes, 0, xmlBytes.length);
            fis.close();
            
            // move xml from stringbufffer to an input stream
            is = new ByteArrayInputStream( xmlBytes );
         }
         else
         {
        	 System.out.println("IO: ERROR: file does not exist");
         }
            
         return is;
         
      } catch( IOException ioe ) {
          ioe.printStackTrace();
      }
      
      return null;
   }
   */
   
   public static InputStream getInputStream( File file )
   {
      if( file == null )
         throw new IllegalArgumentException(" null file name. ");
      
      try {
         /* read in xml file */
         InputStream is = null;
         
         if(file.exists())
         {
            // move xml from file to byte array
            byte [] xmlBytes = new byte[(int)file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(xmlBytes, 0, xmlBytes.length);
            fis.close();
            
            // move xml from stringbufffer to an input stream
            is = new ByteArrayInputStream( xmlBytes );
         }
         else
         {
        	 //System.out.println("IO: ERROR: file does not exist");
         }
            
         return is;
         
      } catch( IOException ioe ) {
          ioe.printStackTrace();
      }
      
      return null;
   }
   
   public static boolean copyFile( File src, File dest )
   {     
      // make sure file exists and is valid
      if( !src.exists() )
         throw new IllegalArgumentException("File to copy "+src.getPath()+" does not exist");
      if( !src.isFile() )
         throw new IllegalArgumentException("File to copy "+src.getPath()+" is not a valid file");
      
      // create directory path
      File parentFile = dest.getParentFile();
      if( parentFile != null && !parentFile.exists() && !parentFile.mkdirs() ) {
         //System.out.println("Error: Unable to create parent directories for file "+dest.getPath());
         return false; 
      }
      
      // copy file to destination directory
      FileInputStream fis = null;
      FileOutputStream fos = null;
      try {
         fis = new FileInputStream( src );
      } 
      catch(FileNotFoundException fnfe){ fnfe.printStackTrace(); }
      catch(IOException ioe){ ioe.printStackTrace(); }
            
      byte [] buf = new byte[ (int)src.length() ];
         
      try {
         fis.read( buf );
         fis.close();
      } 
      catch(IOException ioe){ ioe.printStackTrace(); }
            
      try {
         fos = new FileOutputStream( dest );
         fos.write(buf);
         fos.close();
      } 
      catch(FileNotFoundException fnfe){ fnfe.printStackTrace(); }
      catch(IOException ioe){ ioe.printStackTrace(); }
      
      return true;
   }

   /**
    * See http://stackoverflow.com/questions/326390/is-there-an-alternative-to-this-way-of-read-file-to-a-string-in-java
    * for algorithm's origin.  Reads file at inputFile into a string, treating contents of
    * inputFile as if it were encoded in the encoding given by encodingCharset.
    *
    * @param inputFile File pointing at the file to read.
    * @param encodingCharset a Charset representing the encoding of the input file.
    * @return String containing verbatim contents of inputFile
    * @throws IOException Re-throws any caught IOExceptions.
    */
   public static String readFileToString(File inputFile, Charset encodingCharset)
           throws IOException {
       FileInputStream instream = new FileInputStream(inputFile);

       try {
           FileChannel channel = instream.getChannel();
           MappedByteBuffer bytebuf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());

           return encodingCharset.decode(bytebuf).toString();

       } finally {
           instream.close(); // always close stream in case of exception
       }
   }
}