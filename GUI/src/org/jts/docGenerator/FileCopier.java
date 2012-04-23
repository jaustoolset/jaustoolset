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

package org.jts.docGenerator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.jts.docGenerator.util.IO;
import org.jts.docGenerator.util.DirectoryScanner;
import org.jts.docGenerator.util.FileFilter;

/** Renames subMenu and allMessageDefinitions files and copies them to output directory
*
* @author Eric Thorn $Date: 2008/10/15 16:38:56 $ $Revision: 1.1.1.1 $
*
*/
final class FileCopier {

    private File destDir = null;

    /** Creates an instance of the file copier.
     *
     */
    FileCopier(File destDir) {
        this.destDir = destDir;
    }

    /** Validates and copies all XML files in source directories to destination
     * directory.
     *
     */
    void copy() {
        ArrayList<File> toCopy = new ArrayList<File>();  // files to copy

        // filter all html files that meet file type requirements
        FileFilter filter = new FileFilter() {
            // Accepts subMenu files, allServiceDefinition files, and allMessageDefinition files with the html extension
            // @param name File Name.
            // @return Accept or Reject Flag
            //

            public boolean accept(File file) {
                if (!file.isDirectory() && !file.getParent().endsWith(destDir.getName()) && (file.getName().endsWith("subMenuA.html") || file.getName().endsWith("subMenuB.html") || file.getName().endsWith("allServiceDefinitions.html") || file.getName().endsWith("allMessageDefinitions.html"))) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        DirectoryScanner scanner = new DirectoryScanner(destDir, filter);
        List<File> files = scanner.getFileList();

        // add all files to be copied to list
        for (File file : files) {
            toCopy.add(file);
        }

        // copy files to destination directory
        for (int jj = 0; jj < toCopy.size(); jj++) {
            File file = (File) toCopy.get(jj);
            File parent = file.getParentFile();

            StringBuilder builder = new StringBuilder(file.getPath());

            // add hyphen between parent and file name
            builder = builder.replace(0, builder.lastIndexOf(File.separator) + 1, parent.getPath() + "-");

            // remove path up to parent name and replace with destination directory path
            builder = builder.replace(0, builder.lastIndexOf(File.separator), destDir.getPath());

            IO.copyFile(file, new File(builder.toString()));
        }
    }
}
