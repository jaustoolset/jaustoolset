/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2011, United States Government
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
package org.jts.protocolvalidator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

/**
 *  FileFinder is used to keep track of the files that are required for processing
 *  the original JSIDL.  This is necessary because of inheritance and definitions
 *  that are buried in other files.
 *
 * @author cmessmer
 */
public class FileFinder {

    // mapp of file path to id
    private Map fileMap;
    // map of id to file path
    private Map reverseFileMap;
    // map showing if a specific id/file has been processed
    private Map processedFileMap;

    /**
     * Creates a new object used to find files in the path
     * @param path - the path this object searches
     */
    public FileFinder(String path) throws Exception {
        File filePath = new File(path);
        List<File> fileList = getFileList(filePath);

        populateIDMap(fileList);
    }

    /**
     * Retrieves the file path from a given ID
     * @param id - the JSIDL ID for the root element in a file
     * @return - The path for the file that contains the given ID.
     */
    public String getFilePathFromID(String id) {
        return (String) reverseFileMap.get(id);
    }

    /**
     * Retrieves the File object from a given ID
     * @param id - the JSIDL ID for the root element in a file
     * @return - The path for the file that contains the given ID.
     */
    public File findFileByID(String inputID) {
        String filename = getFilePathFromID(inputID);

        return new File(filename);
    }

    /**
     * Retrieve the JSIDL ID for the root element in a specified file.
     * @param filePath - the file to get the ID from.
     * @return - the ID of the root element in the given file
     */
    public String getIDFromFilePath(String filePath) {
        return (String) fileMap.get(filePath);
    }

    /**
     * Allows for tracking which files are done being processed,
     * so they won't be processed again.
     * @param id - JSIDL ID for the root node in the file.
     */
    public void setProcessedID(String id) {
        processedFileMap.put(id, "true");
    }

    /**
     * Allows for tracking which files are done being processed,
     * so they won't be processed again.
     * @param path - path of the file.
     */
    public void setProcessedPath(String path) {
        String id = getIDFromFilePath(path);
        setProcessedID(id);
    }

    /**
     * Determines if a file has already been processed.
     * @param id - JSIDL ID for the root node of the file.
     * @return - true if the file has been marked as processed.
     */
    public boolean isProcessedID(String id) {
        String result = (String) processedFileMap.get(id);
        if (result != null && result.equals("true")) {
            return true;
        }
        return false;
    }

    /**
     * Determines if a file has already been processed.
     * @param path - path of the file.
     * @return - true if the file has been marked as processed.
     */
    public boolean isProcessedPath(String path) {
        String id = getIDFromFilePath(path);
        return isProcessedID(id);
    }

    /**
     * Returns a List of Files under the specified path whose name ends with the extension '.xml' or '.jsidl'.
     * If path refers to a single file, returns a list containing one element-the file referred to by path itself,
     * if that file's name ends with extension '.xml' or '.jsidl'.
     */
    private static List<File> getFileList(File path) {
        if (path.isFile()) {
            List<File> list = new java.util.ArrayList<File>();
            if (path.getName().endsWith(".xml") || path.getName().endsWith(".jsidl")) {
                list.add(path);
            }
            return list;
        } else {
            // recursively searches through files under directory at path, for files ending with .xml or .jsidl.
            // note the 'TrueFileFilter' is being provided as the filter for subdirectories-TrueFileFilter returns
            // true for every subdirectory, so the search will be fully recursive.
            return new ArrayList<File>(FileUtils.listFiles(path,
                    new SuffixFileFilter(new String[]{".xml", ".jsidl"}), TrueFileFilter.INSTANCE));
        }
    }

    /**
     * Filter jsidl files and place in a Map with associated id.
     * @param fileList list of JSIDL XML files
     */
    private void populateIDMap(List<File> fileList) throws Exception {
        fileMap = new HashMap();
        reverseFileMap = new HashMap();
        processedFileMap = new HashMap();

        Document doc = null;
        DocumentBuilder db = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        String id = "";

        try {
            db = dbf.newDocumentBuilder();
        } catch (javax.xml.parsers.ParserConfigurationException pce) {
            Logger.getLogger(JTSFileWriter.class.getName()).log(Level.SEVERE,
                    "Exception while configuring parser: " + pce.toString(), pce);
            throw new CodeGeneratorException("Exception while configuring parser: " + pce.getMessage());
        }

        // store the path/id for each path in the list
        for (int ii = 0; ii < fileList.size(); ii++) {
            File file = fileList.get(ii);
            final String fileName = file.getPath();

            // see if the file can be parsed
            try {
                doc = db.parse(file);
            } catch (final IOException ioe) {
                Logger.getLogger(JTSFileWriter.class.getName()).log(Level.SEVERE,
                        "Unable to read file" + fileName + " \n " + ioe.toString(), ioe);
                continue;   // weaken import to allow bad files in target dir
            } catch (final SAXException saxe) {
                Logger.getLogger(JTSFileWriter.class.getName()).log(Level.SEVERE,
                        "Unable to parse file" + fileName + " \n " + saxe.toString(), saxe);
                continue;    // weaken import to allow bad files in target dir
            }

            // getting the id from the file
            Element root = doc.getDocumentElement();
            id = root.getAttribute("id");

            if (root.getAttribute("xmlns").equals("urn:jaus:jsidl:1.0") && !id.isEmpty()) {
                fileMap.put(fileName, root.getAttribute("id"));
                reverseFileMap.put(root.getAttribute("id"), fileName);
            }
        }

    }
}
