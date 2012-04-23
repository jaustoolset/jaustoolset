/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2010, 2011 United States Government
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

package org.jts.docGenerator;

import java.io.File;
import java.util.Collection;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;


/** Recursively cleans out all files with the specified extension files from the 
* specified path.
*
* @author Arfath Pasha $Date: 2005/06/13 16:38:56 $ $Revision: 1.1.1.1 $
* modified by Eric Thorn (hthorny@ufl.edu) $Date: 2008/06/26
* for JAUS Tool Set
*
*/
final class DirectoryCleaner
{
   private File destDir = null;
   
   /** 
    * Creates an instance of the directory cleaner for the specified directory.
    */
   DirectoryCleaner( File destDir )
   {
      this.destDir = destDir;
   }
   
   /** 
    * Attempts to delete all files in the given directory; prints an error to standard output if a particular file
    * cannot be deleted.
    */
   void clean( final String extn )
   {
      Collection<File> files = FileUtils.listFiles(destDir, FileFilterUtils.suffixFileFilter(extn),
          TrueFileFilter.INSTANCE);

       for (File file : files) {
           if (!file.delete()) {
               System.out.println("Error: Unable to delete file " + file);
           }
       }
   }   
}