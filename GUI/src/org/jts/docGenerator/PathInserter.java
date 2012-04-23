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
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;


/**  Inserts paths to certain files contained in the base directory in all html
 * files contained within the specified directory.
 *
 * @author Arfath Pasha $Date: 2005/07/19 18:54:42 $ $Revision: 1.2 $
 * modified by Eric Thorn (hthorny@ufl.edu) $Date: 2008/06/26
 * for JAUS Tool Set
 *
 */
final class PathInserter {

    private File dest = null;

    /**
     * @param destDir Directory containing HTML files in which paths need to be
     *                inserted.
     *
     */
    PathInserter(File destDir) {
        dest = destDir;
    }

    /**
     *
     * @param hrefs String representations of hrefs for which paths need to be
     * inserted. it is assumed that file names in href are placed in the
     * destination root directory.
     *
     */
    void insertPaths(List<String> hrefs) {

        // get all HTML files at or under dest dir
        Collection<File> htmlFiles = 
                FileUtils.listFiles(dest, FileFilterUtils.suffixFileFilter(".html"), TrueFileFilter.INSTANCE);

        FileReader reader = null;
        FileWriter writer = null;

        for (File htmlFile : htmlFiles) {

            try {
                reader = new FileReader(htmlFile);
            } catch (FileNotFoundException fnfe) {
                fnfe.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

            char[] buf = new char[(int) htmlFile.length()];

            try {
                reader.read(buf);
                reader.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

            StringBuilder builder = new StringBuilder();
            builder = builder.append(buf);

            // insert paths to specified hrefs
            for (int jj = 0; jj < hrefs.size(); jj++) {
                String path = getPath(htmlFile);
                String href = (String) hrefs.get(jj);
                int offset = 0;

                // find all incidences of href and insert path modification
                while ((offset = builder.indexOf(href, offset)) != -1) {
                    builder = builder.insert(offset, path);
                    offset += path.length() + href.length();
                }
            }

            try {
                writer = new FileWriter(htmlFile);
                writer.write(builder.toString().toCharArray());
                writer.close();
            } catch (FileNotFoundException fnfe) {
                fnfe.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    /** 
     * Formats references for datatype fields.
     */
    void formatReferences() {

        // get all HTML files at or under dest dir
        Collection<File> htmlFiles =
                FileUtils.listFiles(dest, FileFilterUtils.suffixFileFilter(".html"), TrueFileFilter.INSTANCE);

        BufferedReader reader = null;

        for (File htmlFile : htmlFiles) {
            try {
                reader = new BufferedReader(new FileReader(htmlFile));
            } catch (FileNotFoundException fnfe) {
                fnfe.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }


            StringBuilder builder = new StringBuilder();
            String str = null;

            try {
                while ((str = reader.readLine()) != null) {
                    if (str.startsWith("<DIV>Refer")) {
                        int in1 = str.indexOf('\'', 0);
                        int in2 = str.indexOf('\'', in1 + 1);
                        builder.append("<DIV>Refer ").append(str.substring(in1 + 1, in2)).append("</DIV>\n");
                    } else {
                        builder.append(str).append("\n");
                    }
                }
                if(reader != null)
                {
                	reader.close();
                }


                FileWriter writer = new FileWriter(htmlFile);
                writer.write(builder.toString().toCharArray());
                writer.close();
            } catch (FileNotFoundException fnfe) {
                fnfe.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

    }

    private StringBuilder sb = new StringBuilder();

    private String getPath(File file) {
        // make file path relative
        file = new File(
                new File(dest.getPath()).toURI().relativize(
                      new File(file.getPath()).toURI()).getPath());

        String str = file.getPath();
        int num = 0;
        int index = 0;

        while ((index = str.indexOf(File.separator, index)) != -1) {
            num++;
            index += 1;
        }

        sb.delete(0, sb.length());  // clear sb
        for (int ii = 0; ii < num; ii++) {
            sb.append("../");
        }

        return sb.toString();
    }
}
