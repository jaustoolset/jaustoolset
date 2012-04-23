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

import java.io.File;
import java.util.List;

/* Automatically generates jsidldoc manifest file (writes it to System.out).
 * Manifest File is automatically based on all JAR Files in lib directory.
 * Lib directory name is defined in first parameter passwd to main() method.
 *
 * @author Arfath Pasha $Date: 2005/06/13 16:38:56 $ $Revision: 1.1.1.1 $
 *
 */
public class ManifestCreator
{

  public static void main(String[] args)
  {
    if (!(args.length > 1 && args.length <= 2))
      throw new RuntimeException("must pass 1 or 2 parameters to program");
    String libDirName = args[0];
   
    // We use print() instead of println() because we want newlines to be same
    // regardless of which system the manifest (and jar file) are built on.
    System.out.print("Built-By: "+args[1]+"\n");
    System.out.print("Main-Class: org.jts.docGenerator.Main\n");
    System.out.print("Class-Path: \n");
    
    //  Get All JAR Files in lib directory.
    File libDir = new File(libDirName);
    
    /* scan with filter */
    FileFilter filter = new FileFilter() {
       /* Accepts Files Ending in .jar extension.
       * @param name File Name.
       * @return Accept or Reject Flag
       */
       public boolean accept (File file)
       {
          if (file.getName().endsWith(".jar")) { return true; }
          else { return false; }
       } 
    };
    DirectoryScanner scanner = new DirectoryScanner(libDir, filter);
    List<File> jars = scanner.getFileList();
    
    for (File jarfile : jars) {
      String path = (jarfile).getPath();
      System.out.println(path);
    }
  }
}
