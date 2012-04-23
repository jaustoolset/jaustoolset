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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simple structure for storing and retrieving a dependency mapping.
 * @author cmessmer
 */
class Dependency {

    String file;
    String dependent;

    public Dependency(String inputfile, String inputdependent) {
        file = inputfile;
        dependent = inputdependent;
    }
};

/**
 * Extracts DeclaredTypeSet data from the JSIDL data binding
 * @author cmessmer
 */
public class DeclaredTypeSetGenerator {

    private FileFinder fileFinder;
    private String JSIDLSchemaPath;
    private String outputPath;
    private String parent;
    private String serviceDefID;
    private org.jts.jsidl.binding.DeclaredTypeSet typeSet;
    private Map<String, String> refMap;
    private Map<String, String> enumList;
    private List<String> includelist;
    private List<Dependency> dependencyMap;

    /**
     * Creates an object used for generating Promela code from a DeclaredTypeSet
     * @param typeSetRoot - the JSIDL object to process
     * @param inputFinder - used to find files by ID, so that inheritance and/or
     * included definitions can be processed
     * @param schemaPath - the path for the JSIDL schema
     * @param outputFilePath - where the output files will be created
     * @param containerID - the id for the service that contains the type set
     * @param refmap - a mapping of references to names
     * @param enumlist - list of enums already defined, so there are no redefinitions
     * @param parent - name of the parent object
     */
    public DeclaredTypeSetGenerator(org.jts.jsidl.binding.DeclaredTypeSet typeSetRoot,
            FileFinder inputFinder, String schemaPath, String outputFilePath,
            String containerID, Map<String, String> refmap, Map<String, String> enumlist,
            String parent) {
        includelist = new ArrayList<String>();
        typeSet = typeSetRoot;
        fileFinder = inputFinder;
        JSIDLSchemaPath = schemaPath;
        serviceDefID = containerID;
        refMap = refmap;
        enumList = enumlist;
        dependencyMap = new ArrayList<Dependency>();
        this.parent = parent;
        outputPath = outputFilePath;

    }

    /**
     * Retrieve a list of file dependencies found in the declared typeset
     * @return - the list of dependencies found
     */
    public List<Dependency> getDependencyMap() throws Exception {
        processDependencies(typeSet, parent);
        return dependencyMap;
    }

    /**
     * Searches through DeclaredTypeSet to find dependencies
     * @param tmptypeSet - the typeset to start at
     * @param tmpparent - the parent's filename
     */
    private void processDependencies(org.jts.jsidl.binding.DeclaredTypeSet tmptypeSet, String tmpparent) throws Exception {
        String name = tmptypeSet.getName();
        String ID = tmptypeSet.getId();
        // if the typeset is part of a service definition, then the typeset should use the service def's id
        if (ID == null) {
            ID = serviceDefID;
        }

        // process the references
        List<org.jts.jsidl.binding.DeclaredTypeSetRef> typeSetRefs = tmptypeSet.getDeclaredTypeSetRef();
        //Map<String, String> refmap = new HashMap<String, String>();
        for (org.jts.jsidl.binding.DeclaredTypeSetRef tmpref : typeSetRefs) {
            // get the real name from the id, so we can include the right file
            refMap.put(tmpref.getName(), tmpref.getId());
            String refname = Util.getFileNameFromID(tmpref.getId(), true);
            dependencyMap.add(new Dependency(refname, tmpparent));//includelist.add("#include \"" + refname + "\"\n");

            String id = tmpref.getId();


            File typeFile = fileFinder.findFileByID(id);

            if (!typeFile.exists()) {
                // output error and continue to process others
                Logger.getLogger(JTSFileWriter.class.getName()).log(Level.WARNING, "DeclaredTypeSetRef file not found...");
                continue;
            }

            // get the declared_type_set_ref and call this function recursively
            String refID = tmpref.getId();
            String filePath = fileFinder.getFilePathFromID(refID);
            JSIDLReader reader = new JSIDLReader(filePath, JSIDLSchemaPath);
            Object root = reader.getRootElement();
            if (root instanceof org.jts.jsidl.binding.DeclaredTypeSet) {
                // process the next nested set
                processDependencies((org.jts.jsidl.binding.DeclaredTypeSet) root, refname);
            } else if (root instanceof org.jts.jsidl.binding.ServiceDef) {
                org.jts.jsidl.binding.DeclaredTypeSet set =
                        ((org.jts.jsidl.binding.ServiceDef) root).getDeclaredTypeSet();
                processDependencies(set, refname);
            } else {
                Logger.getLogger(JTSFileWriter.class.getName()).log(Level.WARNING, "Root element type not expected.");
                throw new Exception("Root element type not expected.");
            }
        }


    }

    /**
     * Processes the data found in the DeclaredTypeSet databinding, to extract all related data for this object.
     * @param typeSet - the typeset to process
     * @param parent - the name of the parent's file
     */
    public void processDeclaredTypeSet(org.jts.jsidl.binding.DeclaredTypeSet typeSet, String parent) throws Exception {

        String name = typeSet.getName();
        String ID = typeSet.getId();
        String version = typeSet.getVersion();
        // if the typeset is part of a service definition, then the typeset should use the service def's id
        if (ID == null) {
            ID = serviceDefID;
        }

        String newFilePath = outputPath + Util.getFileNameFromID(ID, true);//Util.getTypeNameFromDeclTypeRef(null, name, serviceDefID);

        // process the references
        List<org.jts.jsidl.binding.DeclaredTypeSetRef> typeSetRefs = typeSet.getDeclaredTypeSetRef();
        //Map<String, String> refmap = new HashMap<String, String>();
        for (org.jts.jsidl.binding.DeclaredTypeSetRef tmpref : typeSetRefs) {
            // get the real name from the id, so we can include the right file
            refMap.put(tmpref.getName(), tmpref.getId());
            String refname = Util.getFileNameFromID(tmpref.getId(), true);

            String id = tmpref.getId();
            if (!fileFinder.isProcessedID(id)) {

                File typeFile = fileFinder.findFileByID(id);

                if (!typeFile.exists()) {
                    // output error and continue to process others
                    Logger.getLogger(JTSFileWriter.class.getName()).log(Level.WARNING, "DeclaredTypeSetRef file not found...");
                    continue;
                }

                // get the declared_type_set_ref and call this function recursively
                String refID = tmpref.getId();
                String filePath = fileFinder.getFilePathFromID(refID);
                JSIDLReader reader = new JSIDLReader(filePath, JSIDLSchemaPath);
                Object root = reader.getRootElement();
                if (root instanceof org.jts.jsidl.binding.DeclaredTypeSet) {
                    // process the next nested set
                    fileFinder.setProcessedID(refID);
                    processDeclaredTypeSet((org.jts.jsidl.binding.DeclaredTypeSet) root, refname);
                } else if (root instanceof org.jts.jsidl.binding.ServiceDef) {
                    org.jts.jsidl.binding.DeclaredTypeSet set =
                            ((org.jts.jsidl.binding.ServiceDef) root).getDeclaredTypeSet();
                    fileFinder.setProcessedID(refID);
                    processDeclaredTypeSet(set, refname);
                } else {
                    Logger.getLogger(JTSFileWriter.class.getName()).log(Level.WARNING, "Root element type not expected.");
                    throw new Exception("Root element type not expected.");
                }
            }

        }
        // now we process the definitions contained in the file, if there are any
        if (typeSet.getMessageDefOrHeaderOrBody() != null) {
            DefinitionGenerator msgDefGen = new DefinitionGenerator(typeSet.getMessageDefOrHeaderOrBody(),
                    fileFinder, JSIDLSchemaPath, newFilePath, includelist, refMap, ID, version, enumList);
        }

    }
}
