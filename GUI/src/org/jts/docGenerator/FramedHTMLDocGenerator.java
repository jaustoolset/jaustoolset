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

import java.util.List;
import java.io.File;
import java.util.ArrayList;
import org.jts.jsidl.binding.ServiceSet;

/**
 * Creates "framed" HTML documentation output from a JAXB ServiceSet representation.
 *
 * TODO: merge this class and DefaultDoc; this class is basically pointless cruft wrapping DefaultDoc.
 *
 * @author Eric Thorn $Date: 2008/06/26 18:54:42 $ $Revision: 1.2 $
 *
 */
public final class FramedHTMLDocGenerator extends DocGenerator {

    private final FramedHTMLDocGeneratorWorker worker;
    protected final List<ServiceSet> serviceSetList;
    protected final File destDir;
    protected final File custDir;

    /**
     * Creates an instance of the framed HTML documentation generator.
     */
    public FramedHTMLDocGenerator(List<ServiceSet> servSetList, File destPath, File custPath) {
        destDir = destPath;
        custDir = custPath;
        serviceSetList = servSetList;

        worker = new FramedHTMLDocGeneratorWorker(destDir, custDir, serviceSetList);
    }

    /**
     * Create instance without specifying a list of service sets.
     */
    public FramedHTMLDocGenerator(ServiceSet servSet, File destPath, File custPath) {
        destDir = destPath;
        custDir = custPath;
        serviceSetList = new ArrayList<ServiceSet>();
        serviceSetList.add(servSet);

        worker = new FramedHTMLDocGeneratorWorker(destDir, custDir, serviceSetList);
    }

    /**
     * Generates the HTML output.
     */
    @Override
    public void generate() {
        // ==========
        // PREPROCESS
        // ==========
        if (!destDir.exists() && !destDir.mkdirs()) {
            throw new RuntimeException("Problem creating framed HTML output directory: " + destDir.getAbsolutePath());
        }

        // copy all files from static files directory to output directory - files that don't change
        File staticFileDir = new File(custDir, AllDocGeneratorCommon.STATIC_FILES_PATH);
        AllDocGeneratorCommon.copyStaticResources(staticFileDir, destDir);

        statusMonitor.updateStatus("Generating XML files...");
        // create xml files for all service definitions and message definitions in each service set
        // create output directory structure and place these new files in it
        
        worker.generateXMLFiles();
        
        // =======
        // PROCESS
        // =======

        // generate all index files
        // mainMenu, subMenuA, subMenuB, allServiceSets, allServiceDefinitions, allMessageDefinitions
        statusMonitor.updateStatus("Generating index files...");
        worker.generateIndexFiles();

        // convert xml files to html files using xsl transform files in styles directory
        statusMonitor.updateStatus("Transforming XML files to HTML files...");
        worker.generateHTMLFiles();

        // ============
        // POST-PROCESS
        // ============
        statusMonitor.updateStatus("Completing document generation...");

        // rename and copy index files from sub-directories to output directory for display
        copyFiles();

        // insert paths into html files where necessary and format their references
        insertPaths(destDir);
    }

    /** 
     * Moves sub menu files from the service set directories to the output directory
     */
    private void copyFiles() {
        new FileCopier(destDir).copy();
    }

    /** 
     * Inserts paths to static files in the base directory of the destination.
     */
    private void insertPaths(File destDir) {
        ArrayList<String> hrefs = new ArrayList<String>();
        hrefs.add("framedStyleSheet.css");
        hrefs.add("images/branch.gif");
        PathInserter inserter = new PathInserter(destDir);

        inserter.insertPaths(hrefs);
        inserter.formatReferences();
    }
}
