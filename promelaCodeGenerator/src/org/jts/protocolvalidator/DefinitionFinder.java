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
import javax.xml.bind.JAXBException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.jts.jsidl.binding.DeclaredConstSet;
import org.jts.jsidl.binding.DeclaredConstSetRef;
import org.jts.jsidl.binding.DeclaredTypeSet;
import org.jts.jsidl.binding.DeclaredTypeSetRef;
import org.jts.jsidl.binding.Description;
import org.jts.jsidl.binding.ServiceDef;
import org.jts.jsidl.binding.ServiceSet;

/**
 *  FileFinder is used to keep track of the files that are required for processing
 *  the original JSIDL.  This is necessary because of inheritance and definitions
 *  that are buried in other files.
 *
 * @author cmessmer
 */
public class DefinitionFinder {

    // map showing if a specific id/file has been processed
    private Map processedFileMap;
    private ServiceSet serviceSet;
    private ServiceDef mainServiceDef;
    // maps for quick lookup
    private Map<String, ServiceDef> serviceDefMap;
    private Map<String, DeclaredTypeSet> typeSetMap;
    private Map<String, DeclaredConstSet> constSetMap;

    /**
     * Creates a new object used to find root level elements with a specific ID.  This constructor
     * also generates a new service set from all the JSIDL found at the specified path.
     * @param path - the path this object searches
     */
    public DefinitionFinder(String mainFile, String path, String schema) throws Exception {
        File filePath = new File(path);
        File mainFilePath = new File(mainFile);
        File schemaPath = new File(schema);
        processedFileMap = new HashMap();
        serviceDefMap = new HashMap<String, ServiceDef>();
        typeSetMap = new HashMap<String, DeclaredTypeSet>();
        constSetMap = new HashMap<String, DeclaredConstSet>();
        List<File> fileList = getFileList(mainFilePath, filePath, schemaPath);
        serviceSet = new ServiceSet();
        serviceSet.setId("urn.jts.generatedServiceSet");
        serviceSet.setName("GeneratedServiceSet");
        Description description = new Description();
        description.setContent("This service set was generated from a set of JSIDL files representing the set.");
        description.setSpace(" ");
        serviceSet.setDescription(description);

        // this is what we are supposed to be processing, so we need to make it clear
        // unrelated definitions will be ignored.
        JSIDLReader reader = new JSIDLReader(mainFile, schema);
        Object tmpObj = reader.getRootElement();
        if (tmpObj instanceof ServiceDef) {
            mainServiceDef = ((ServiceDef) tmpObj);
        }
        populateIDMap(fileList, schema);
    }

    /**
     * Creates a new object from an existing ServiceSet and creates ID maps for all root level elements of the binding.
     * @param serviceSet - existing service set
     * @throws Exception
     */
    public DefinitionFinder(ServiceSet serviceSet) throws Exception {
        this.serviceSet = serviceSet;
        processedFileMap = new HashMap();
        serviceDefMap = new HashMap<String, ServiceDef>();
        typeSetMap = new HashMap<String, DeclaredTypeSet>();
        constSetMap = new HashMap<String, DeclaredConstSet>();

        List<ServiceDef> defs = serviceSet.getServiceDef();
        String mainServiceDefID = "";
        for (ServiceDef def : defs) {
            // check for dependencies
            if (mainServiceDefID.equals("") || (def.getReferences() != null && def.getReferences().getInheritsFrom() != null
                    && mainServiceDefID.equals(def.getReferences().getInheritsFrom().getId()))) {
                mainServiceDefID = def.getId();
            }
        }
        populateIDMap(serviceSet);
        mainServiceDef = serviceDefMap.get(mainServiceDefID);

    }

    /**
     * This is the main service definition that was found.  This is based on input or
     * the fact that this serviceDef is not a dependency of any others.
     * @return - the main service definition that should be processed.
     */
    public ServiceDef getMainServiceDef() {
        return mainServiceDef;
    }

    public ServiceSet getServiceSet() {
        return serviceSet;
    }

    public ServiceDef getServiceDefFromID(String id) {
        return serviceDefMap.get(id);
    }

    public DeclaredConstSet getConstSetFromID(String id) {
        return constSetMap.get(id);
    }

    public DeclaredTypeSet getTypeSetFromID(String id) {
        return typeSetMap.get(id);
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
     * Returns a List of Files under the specified path whose name ends with the extension '.xml' or '.jsidl'.
     * If path refers to a single file, returns a list containing one element-the file referred to by path itself,
     * if that file's name ends with extension '.xml' or '.jsidl'.
     */
    private static List<File> getFileList(File mainFilePath, File path, File schema) {

        List<File> fileList = new ArrayList<File>();
        List<File> tmpFileList = new ArrayList<File>(FileUtils.listFiles(path, new SuffixFileFilter(new String[]{".xml", ".jsidl"}), TrueFileFilter.INSTANCE));
        if (mainFilePath.isDirectory() || path.isFile()) {
            // input error
            return null;
        }

        fileList.add(mainFilePath);
        fileList.addAll(getDependencies(mainFilePath, path, schema, tmpFileList));

        return fileList;
    }

    private static File getFileWithID(List<File> files, String ID) {
        File selectedFile = new File("");
        for (File file : files) {
            String tmpID = "";
            try {
                tmpID = getFileID(file);
            } catch (Exception ex) {
                Logger.getLogger(DefinitionFinder.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (ID.equals(tmpID)) {
                selectedFile = file;
            }
        }
        return selectedFile;
    }

    /**
     * Takes a list of all .xml and .jsidl files within a directory and determines which ones
     * are dependencies of the main JSIDL file.
     * @param file - the main JSIDL
     * @param path - path to dependencies
     * @param schema - the JSIDL schema
     * @param filelist - list of all the .xml and .jsidl files at the path
     * @return - sublist of the filelist that contains only the dependencies of the main JSIDL file.
     */
    private static List<File> getDependencies(File file, File path, File schema, List<File> filelist) {
        List<File> files = new ArrayList<File>();

        try {
            JSIDLReader reader = new JSIDLReader(file.getCanonicalPath(), schema.getCanonicalPath());

            Object root = reader.getRootElement();
            if (root instanceof ServiceDef) {
                DeclaredConstSet declConstSet = ((ServiceDef) root).getDeclaredConstSet();
                if (declConstSet != null && declConstSet.getId() != null) {
                    String tmpID = declConstSet.getId();
                    File constFile = getFileWithID(filelist, tmpID);
                    files.add(constFile);
                    files.addAll(getDependencies(constFile, path, schema, filelist));
                }
                DeclaredTypeSet declTypeSet = ((ServiceDef) root).getDeclaredTypeSet();
                if (declTypeSet != null) {
                    if (declTypeSet.getId() != null) {
                        String tmpID = declTypeSet.getId();
                        File typeFile = getFileWithID(filelist, tmpID);
                        files.add(typeFile);
                        files.addAll(getDependencies(typeFile, path, schema, filelist));
                    }
                    List<DeclaredConstSetRef> declConstSetRefs = declTypeSet.getDeclaredConstSetRef();
                    for (DeclaredConstSetRef ref : declConstSetRefs) {
                        String tmpID = ref.getId();
                        File tmpfile = getFileWithID(filelist, tmpID);
                        files.add(tmpfile);
                        files.addAll(getDependencies(tmpfile, path, schema, filelist));
                    }
                    List<DeclaredTypeSetRef> declTypeSetRefs = declTypeSet.getDeclaredTypeSetRef();
                    for (DeclaredTypeSetRef ref : declTypeSetRefs) {
                        String tmpID = ref.getId();
                        File tmpfile = getFileWithID(filelist, tmpID);
                        files.add(tmpfile);
                        files.addAll(getDependencies(tmpfile, path, schema, filelist));
                    }


                }

                if (((ServiceDef) root).getReferences() != null && ((ServiceDef) root).getReferences().getInheritsFrom() != null) {
                    String tmpID = ((ServiceDef) root).getReferences().getInheritsFrom().getId();
                    File defFile = getFileWithID(filelist, tmpID);
                    files.add(defFile);
                    files.addAll(getDependencies(defFile, path, schema, filelist));
                }

            } else if (root instanceof DeclaredTypeSet) {
                List<DeclaredTypeSetRef> typesets = ((DeclaredTypeSet) root).getDeclaredTypeSetRef();
                for (DeclaredTypeSetRef ref : typesets) {
                    String typeID = ref.getId();
                    File typeFile = getFileWithID(filelist, typeID);
                    files.add(typeFile);
                    files.addAll(getDependencies(typeFile, path, schema, filelist));
                }
            } else if (root instanceof DeclaredConstSet) {
                List<DeclaredConstSetRef> constsets = ((DeclaredConstSet) root).getDeclaredConstSetRef();
                for (DeclaredConstSetRef ref : constsets) {
                    String constID = ref.getId();
                    File constFile = getFileWithID(filelist, constID);
                    files.add(constFile);
                    files.addAll(getDependencies(constFile, path, schema, filelist));
                }
            }

        } catch (JAXBException ex) {
            Logger.getLogger(DefinitionFinder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(DefinitionFinder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DefinitionFinder.class.getName()).log(Level.SEVERE, null, ex);
        }

        return files;
    }

    private static String getFileID(File file) throws Exception {
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
        // see if the file can be parsed
        try {
            doc = db.parse(file);
        } catch (final IOException ioe) {
            Logger.getLogger(JTSFileWriter.class.getName()).log(Level.SEVERE,
                    "Unable to read file" + file.getName() + " \n " + ioe.toString(), ioe);
        } catch (final SAXException saxe) {
            Logger.getLogger(JTSFileWriter.class.getName()).log(Level.SEVERE,
                    "Unable to parse file" + file.getName() + " \n " + saxe.toString(), saxe);
        }

        // getting the id from the file
        Element root = doc.getDocumentElement();

        if (root.getAttribute("xmlns").equals("urn:jaus:jsidl:1.0")) {
            id = root.getAttribute("id");
        }

        return id;
    }

    /**
     * Navigates service set and populates the ID maps
     * @param set - the input service set
     */
    private void populateIDMap(ServiceSet set) {
        List<ServiceDef> defs = serviceSet.getServiceDef();
        for (ServiceDef def : defs) {
            populateIDMap(def);
        }

        List<DeclaredTypeSet> types = serviceSet.getDeclaredTypeSet();
        for (DeclaredTypeSet type : types) {
            populateIDMap(type);
        }


        List<DeclaredConstSet> consts = serviceSet.getDeclaredConstSet();
        for (DeclaredConstSet constSet : consts) {
            populateIDMap(constSet);
        }

    }

    private void populateIDMap(ServiceDef def) {
        serviceDefMap.put(def.getId(), def);
        if (def.getReferences() != null && def.getReferences().getInheritsFrom() != null) {
        }
        def.getDeclaredConstSet();
        def.getDeclaredTypeSet();
        def.getReferences().getInheritsFrom();
    }

    private void populateIDMap(DeclaredTypeSet typeSet) {
        typeSetMap.put(typeSet.getId(), typeSet);
    }

    private void populateIDMap(DeclaredConstSet constSet) {
        constSetMap.put(constSet.getId(), constSet);
    }

    /**
     * Filter jsidl files and place in a Map with associated id.
     * @param fileList list of JSIDL XML files
     */
    private void populateIDMap(List<File> fileList, String schemaPath) throws Exception {


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

            if (root.getAttribute("xmlns").equals("urn:jaus:jsidl:1.0")) {
                JSIDLReader reader = new JSIDLReader(fileName, schemaPath);
                Object rootObj = reader.getRootElement();
                if (rootObj instanceof ServiceDef) {
                    serviceDefMap.put(((ServiceDef) rootObj).getId(), ((ServiceDef) rootObj));
                    serviceSet.getServiceDef().add(((ServiceDef) rootObj));
                } else if (rootObj instanceof DeclaredTypeSet) {
                    typeSetMap.put(((DeclaredTypeSet) rootObj).getId(), ((DeclaredTypeSet) rootObj));
                    serviceSet.getDeclaredTypeSet().add(((DeclaredTypeSet) rootObj));
                } else if (rootObj instanceof DeclaredConstSet) {
                    constSetMap.put(((DeclaredConstSet) rootObj).getId(), ((DeclaredConstSet) rootObj));
                    serviceSet.getDeclaredConstSet().add(((DeclaredConstSet) rootObj));
                }
            }
        }

    }
}
