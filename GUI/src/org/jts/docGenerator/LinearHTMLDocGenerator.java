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
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.jts.docGenerator.LinearDocGeneratorCommon.FileNamePair;
import org.jts.jsidl.binding.ServiceSet;
import org.jts.jsidl.binding.ServiceDef;

/**
 * DocGenerator implementer for generating "linear" (single page) HTML output from a JAXB ServiceSet
 * representation.  Relies on LinearDocGeneratorCommon for most of its behavior.  Is responsible for
 * creating its own output directory structure and for locating all file resources to pass to
 * LinearDocGeneratorCommon methods.  Also uses ProtocolImageGenerator and MessageDiagramGenerator 
 * for creating protocol behavior diagrams and message structure diagrams, respectively.
 * @author idurkan
 */
public class LinearHTMLDocGenerator extends DocGenerator {
    protected ServiceSet serviceSet = null;

    // directory under which all our work gets done
    protected File outputDir = null;

    // directory for 'intermediates' - generated docbook and XSL-FO
    protected File intermedsDir = null;

    // preexsiting dir where stylesheets stored for Word document generation process
    protected File stylesheetsDir = null;
    // preexisting dir where static files are located.
    protected File staticFileDir = null;
    // directory for generated JSIDL files
    protected File jsidlDir = null;
    // directory for generated image files.
    protected File localImgDir = null;

    protected final String templateFilename = "html_template_db.xml";

    // hardcoded XML filenames.
    //protected File docbookTemplateFile = new File(staticFileDir, templateFilename);
    protected File docbookTemplateLocalFile = null;
    protected File docbookOutputFile = null;
    protected File htmlOutputFile = null;

    // hardcoded XSL filsnames
    protected File jsidlToDocbookStylesheet = null;
    protected File docbookToHtmlStylesheet = null;

    protected boolean doCleanupDirectories;

    /**
     * Create LinearHTMLDocGenerator instance.   prepared to generate for given ServiceSet, using given directories.
     * @param inputServiceSet JAXB ServiceSet representation of the service set to create documentation for
     * @param destPath Directory where output will be placed.
     * @param styleCustDir Stylization customization directory
     * @param cleanupDirectories If true, directories containing "intermediate" files generated during the doc gen
     * process such as JSIDL and Docbook XML will be deleted after document generation is complete.  If false they
     * are left untouched.
     */
    public LinearHTMLDocGenerator(ServiceSet inputServiceSet, File destPath, File styleCustDir,
            boolean cleanupDirectories) {
        serviceSet = inputServiceSet;
        outputDir = destPath;
        doCleanupDirectories = cleanupDirectories;

        intermedsDir = new File(outputDir, "intermediates/");
        jsidlDir = new File(outputDir, "jsidl/");
        localImgDir = new File(outputDir, "images/");

        docbookOutputFile = new File(intermedsDir, AllDocGeneratorCommon.MERGED_DB_FILENAME);
        htmlOutputFile = new File(outputDir, inputServiceSet.getName() + "_output.html");
        docbookTemplateLocalFile = new File(outputDir, templateFilename);

        // determine locations of stylesheets, resources based on styleset customization dir
        stylesheetsDir = new File(styleCustDir, AllDocGeneratorCommon.LINEAR_HTML_STYLES_PATH);
        staticFileDir = new File(styleCustDir, AllDocGeneratorCommon.STATIC_FILES_PATH); 
        jsidlToDocbookStylesheet = new File(stylesheetsDir, "servdef_jsidl_db_cals.xsl");
        docbookToHtmlStylesheet = new File(stylesheetsDir, "docbook_cals_html.xsl");
    }

    /**
     * Creates directories where output files are created.
     */
    protected void createDirectories() {
        // make directory where all our work gets done
        if (!outputDir.exists() && !outputDir.mkdirs()) {
            throw new RuntimeException("Unable to create Linear HTML document generation main output dir: "
                    + outputDir.getAbsolutePath());
        }

        // directory for 'intermediates' - generated docbook and XSL-FO
        if (!intermedsDir.exists() && !intermedsDir.mkdirs()) {
            throw new RuntimeException("Unable to create intermediate files directory "
                    + "for Linear HTML document generation: " + intermedsDir.getAbsolutePath());
        }

        // directory for generated JSIDL files
        if (!jsidlDir.exists() && !jsidlDir.mkdirs()) {
            throw new RuntimeException("Unable to create JSIDL files directory for Linear HTML document generation:"
                    + jsidlDir.getAbsolutePath());
        }

        // directory for generated image files.
        if (!localImgDir.exists() && !localImgDir.mkdirs()) {
            throw new RuntimeException("Unable to create image files directory for Linear HTML document generation!"
                    + localImgDir.getAbsolutePath());
        }
    }

    /**
     * Cleans up directories not needed after docgen completed.
     */
    protected void cleanupDirectories() {
        if (intermedsDir.exists()) {
            try {
                FileUtils.deleteDirectory(intermedsDir);
            } catch (IOException ioex) {
                throw new RuntimeException("Error while deleting intermediate files directory.", ioex);
            }
        }

        if (jsidlDir.exists()) {
            try {
                FileUtils.deleteDirectory(jsidlDir);
            } catch (IOException ioex) {
                throw new RuntimeException("Error while deleting intermediate files directory.", ioex);
            }
        }
    }

    /**
     * Performs document generation for the ServiceSet provided to the constructor.
     */
    @Override
    public void generate() {
        createDirectories();

        AllDocGeneratorCommon.copyStaticResources(staticFileDir, outputDir);

        List<ServiceDef> serviceDefs = serviceSet.getServiceDef();

        List<File> docbookFiles = null;
        List<FileNamePair> jsidlFilesInfo = null;

        // get template Docbook as a JDOM document
        Document templateDoc;
        SAXBuilder builder = new SAXBuilder();
        try {
            templateDoc = builder.build(docbookTemplateLocalFile);
        } catch (Exception ex) {
            throw new RuntimeException("Error when loading template Docbook document", ex);
        }

        statusMonitor.updateStatus("Generating message structure diagrams...");
        MessageDiagramGenerator diagGenerator = new MessageDiagramGenerator();
        diagGenerator.generateMessageDigrams(localImgDir, serviceDefs);

        LinearDocGeneratorCommon commonDocgen = new LinearDocGeneratorCommon();

        statusMonitor.updateStatus("Creating JSIDL files...");
        jsidlFilesInfo = commonDocgen.createJSIDLFiles(serviceDefs, jsidlDir);

        statusMonitor.updateStatus("Transforming JSIDL to Docbook format...");
        docbookFiles = commonDocgen.createDocbookPerService(
                jsidlFilesInfo, intermedsDir, jsidlToDocbookStylesheet);

        statusMonitor.updateStatus("Combining Docbook into single document...");
        commonDocgen.addServiceSubdocsToDoc(docbookFiles, templateDoc);

        commonDocgen.addServiceSourceToDocAppendix(jsidlFilesInfo, templateDoc);

        commonDocgen.outputCombinedDoc(templateDoc, docbookOutputFile);

        statusMonitor.updateStatus("Transforming Docbook to HTML document...");
        commonDocgen.transformCombinedDocbookToOutputFormat(
                docbookOutputFile, htmlOutputFile, docbookToHtmlStylesheet);

        if (doCleanupDirectories) {
            cleanupDirectories();
        }

    }

}
