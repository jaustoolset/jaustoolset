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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

/** Recursively scans a directory and lists files that meets the specified 
* filter's requirements.
*
* @author Arfath Pasha $Date: 2005/06/13 16:38:56 $ $Revision: 1.1.1.1 $
*
*/
public class DirectoryScanner
{
   private ArrayList<File> fileList = null;
   private ArrayList<File> dirList = null;
   private FileFilter filter = null;
   
   /** Creates an instance of a Directory Scanner for the specified directory.
   *
   * @param dir directory to scan
   * @param filter filter to use. null if no specified filter
   *
   */
    public DirectoryScanner(File dir, FileFilter filter) {
        this.filter = filter;
        fileList = new ArrayList<File>();
        dirList = new ArrayList<File>();

        scan(dir);
    }

    /** returns a list of files that meet the specified filter's requirements.
     * an empty list is returned if no files were found to meet the filter's
     * requirements.
     *
     */
    public List<File> getFileList() {
        return fileList;
    }

    public List<File> getDirList() {
        return dirList;
    }

    /** Starts scanning the specified directory.
     *
     */
    private void scan(File dir) {
        if (dir == null) {
            return;
        }

        if (dir.isDirectory() && !dirList.contains(dir)) {
            dirList.add(dir);
        }

        File[] files = dir.listFiles();

        if (files == null) {
            return;
        }

        for (int ii = 0; ii < files.length; ii++) {
            if (files[ii].isDirectory()) {
                if (!dirList.contains(files[ii])) {
                    dirList.add(files[ii]);
                }
                scan(files[ii]);  // recursion
                continue;
            }

            if (filter != null) {
                if (filter.accept(files[ii])) {
                    fileList.add(files[ii]);
                }
            } else {
                fileList.add(files[ii]);
            }
        }
    }

   private static void compareAndPrint(List<File> list1, List<File> list2) {
        if (list1.size() != list2.size()) {
            System.out.println(" Results pairs had different sizes!" + list1.size() + " vs " + list2.size());

            System.out.println("list 1 contents: ");
            for (File file : list1) {
                System.out.println(file.getName());
            }

            System.out.println("List 2 contents: ");
            for (File file : list2) {
                System.out.println(file.getName());
            }
        } else {

            for (int i = 0; i < list1.size(); ++i) {
                File file1 = list1.get(i);
                File file2 = list2.get(i);

                if (!file1.equals(file2)) {
                    System.out.println("Unequal files: " + file1.getName() + ", " + file2.getName());
                }

                System.out.println("Equal files: " + file1.getName() + ", " + file2.getName());
            }
       }
   }

   public static void main(String[] args) {
       File sourceDir = new File("C:/Users/idurkan/Documents/NetBeansProjects/JAUSToolset/output/framed_html");

        // filter all xml files
        FileFilter serviceSetFilter = new FileFilter() {

            /**
             * Accepts Files Ending in .xml extension.
             * @param name File Name.
             * @return Accept or Reject Flag
             */
            public boolean accept(File file) {
                if (file.getName().endsWith(".xml") && !file.isDirectory()) {
                    return true;
                } else {
                    return false;
                }
            }
        };

        // file suffix scanners.
        DirectoryScanner scanner = new DirectoryScanner(sourceDir, serviceSetFilter);

        List<File> result1list = scanner.getFileList();

        IOFileFilter xmlFilter = FileFilterUtils.suffixFileFilter(".xml");
        Collection<File> result2 = FileUtils.listFiles(sourceDir,xmlFilter, TrueFileFilter.INSTANCE);
        ArrayList<File> result2list = new ArrayList<File>(result2);

        // directory scanners
        DirectoryScanner dirscanner = new DirectoryScanner(sourceDir, null);
        List<File> result3list = scanner.getDirList();

        Collection<File> result4 = FileUtils.listFiles(sourceDir,DirectoryFileFilter.DIRECTORY , TrueFileFilter.INSTANCE);
        ArrayList<File> result4list = new ArrayList<File>(result4);

        Collections.sort(result1list);
        Collections.sort(result2list);
        Collections.sort(result3list);
        Collections.sort(result4list);

        compareAndPrint(result1list, result2list);
        compareAndPrint(result3list, result4list);
   }
}