/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2010-2011, United States Government
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jts.docGenerator;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Comparator;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;

/**
 * There are some commonalities shared between both the framed HTML document generation process and
 * the linear document generation process (linear HTML & Word docs), including paths, filenames, and some
 * static functions.  This class contains them.
 * @author idurkan
 */
public class AllDocGeneratorCommon {
    // common directory paths
    public final static String STATIC_FILES_PATH = "staticFiles/";
    public final static String WORD_STYLES_PATH = "wordStyles/";
    public final static String LINEAR_HTML_STYLES_PATH = "linearHTMLStyles/";
    public final static String FRAMED_HTML_STYLES_PATH = "framedHTMLStyles/";

    /**
     * relative path to the docgen stylization resources directory from JTS base dir
     */
    public static final String DEFAULT_STYLESET_PATH = "resources/docGenerator/";
    public static final String ECLIPSE_STYLESET_PATH = "plugins/org.jts.eclipse.data_1.0/resources/docGenerator/";

    // common filenames
    public final static String MERGED_DB_FILENAME = "jsidl_merged_db.xml";

    /**
     * Move the contents of staticFileSourceDir to outputDir, excluding .svn directories.
     * @param staticFileSourceDir Source directory where files to copy are found.
     * @param outputDir Target directory for copying files.
     */
    public static void copyStaticResources(File staticFileSourceDir, File outputDir) {
        try {
            FileFilter noSvnFilter = FileFilterUtils.notFileFilter(
                    FileFilterUtils.nameFileFilter(".svn"));

            FileUtils.copyDirectory(staticFileSourceDir, outputDir, noSvnFilter);

        } catch (IOException ioex) {
            throw new RuntimeException("IO Exception while copying static files to output dir.", ioex);
        }
    }

    /**
     * For comparing files by their filename.  Used several places in framed HTML document generation.
     * Will cause a cast exception if used with objects that are not files!
     */
    public static class FilenameFileComparator implements Comparator {
        public final static FilenameFileComparator INSTANCE = new FilenameFileComparator();

        public int compare(Object o1, Object o2) {
            File f1 = (File) o1;
            File f2 = (File) o2;
            // use String's comparison method.
            return f1.getName().compareTo(f2.getName());
        }
    }
}
