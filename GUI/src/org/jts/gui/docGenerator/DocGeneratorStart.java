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

package org.jts.gui.docGenerator;

import com.u2d.generated.ServiceSet;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.jts.docGenerator.AllDocGeneratorCommon;
import org.jts.docGenerator.DocGenerator;
import org.jts.docGenerator.FramedHTMLDocGenerator;
import org.jts.docGenerator.LinearHTMLDocGenerator;
import org.jts.docGenerator.ProtocolImageGenerator;
import org.jts.docGenerator.WordDocGenerator;
import org.jts.gui.GUI;
import org.jts.gui.util.GUISupport;


/*
 * This class is the starting point for document generation.  The user selects a Service Set in the
 * JMatter-created GUI, and selects its "Generate Documentation" action.  JMatter then calls this class
 * from the GenerateDocumentation method of com.u2d.generated.ServiceSet, passing the ServiceSet to the
 * constructor and calling performGeneration.  performGeneration displays an DocgenOutputOptionsDialog where the
 * user can select an output directory, stylization customization directory, and output format type (framed
 * HTML, linear HTML, or Word .docx), and then generates documentation in the selected output directory
 * if the user clicks Generate.
 */
public class DocGeneratorStart {
    protected static final String FRAMED_HTML_OPTION = "Framed HTML";
    protected static final String LINEAR_HTML_OPTION = "Linear HTML";
    protected static final String WORD_DOC_OPTION = "Word Document (.docx)";


    /**
     * The JAXB ServiceSet representation to generate documentation for.
     */
    protected final ServiceSet inputServiceSet;

    /**
     * Create a DocGeneratorStart instance with serviceSet as input.
     * @param serviceSet The ServiceSet to generate documentation for.
     */
    public DocGeneratorStart(final ServiceSet serviceSet) {
        inputServiceSet = serviceSet;
    }

    /**
     * allows reuse of the user's last selected stylization customization dir
     */
    private static File lastStylesetDir = null;

    /**
     * allows reuse of the user's last selected output dir.
     */
    private static File lastOutputDir = null;

    /**
     * allows reuse of the user's last selected output format.
     */
    private static String lastOutputFormat = null;

    /**
     * Show the DocgenOutputOptionsDialog to collect user option selections, then perform document generation
     * in a separate thread.
     */
    public void performGeneration() {
        final String[] options = {FRAMED_HTML_OPTION, LINEAR_HTML_OPTION, WORD_DOC_OPTION};
        final String[] optionTooltips = {
            "Creates multiple-file HTML output documenting the Service Set, similar to Javadoc",
            "Creates single long HTML file output documenting the service set.",
            "Creates single Word document documenting the service set."
        };

        // Handle the Eclipse plugin's special relative paths.
        final File defaultStylesetDir;
        if(new File(AllDocGeneratorCommon.ECLIPSE_STYLESET_PATH).exists())
        {
            defaultStylesetDir = new File(AllDocGeneratorCommon.ECLIPSE_STYLESET_PATH);
        }
        else
        {
            defaultStylesetDir = new File(AllDocGeneratorCommon.DEFAULT_STYLESET_PATH);
        }
        // assuming the path to the docGenerator resources directory will never change
        final File startingStylesetDir =
                (lastStylesetDir == null) ? defaultStylesetDir : lastStylesetDir;

        // user.home must contain the user's home directory.
        final File startingOutputDir = 
                (lastOutputDir == null) ? new File(System.getProperty("user.home")) : lastOutputDir;

        final String startingFormatOption =
                (lastOutputFormat == null) ? FRAMED_HTML_OPTION : lastOutputFormat;

        // prepare to show the output options dialog.
        GUISupport.DialogDisplayer displayer = new GUISupport.DialogDisplayer() {
            public void setupDialog() {
                DocgenOutputOptionsDialog optionsDialog = new DocgenOutputOptionsDialog(GUI.getFrame(),
                    startingOutputDir,
                    startingStylesetDir,
                    defaultStylesetDir,
                    options,
                    optionTooltips,
                    startingFormatOption);
                setDialogInstance(optionsDialog);
            }
            public void showDialog() {
                getDialogInstance().setVisible(true);
            }
        };

        // show the output options dialog.
        try {
            GUISupport.runOnEDT(displayer);
        } catch (Exception ex) {
            throw new RuntimeException("Error while displaying output options dialog.", ex);
        } 

        DocgenOutputOptionsDialog optionsDialog = (DocgenOutputOptionsDialog)displayer.getDialogInstance();

        // true result->user clicked Generate and all fields in dialog were valid.
        if (optionsDialog.wasGenerateConfirmed()) {

            lastStylesetDir = optionsDialog.getSelectedCustomizationDir();
            lastOutputDir = optionsDialog.getSelectedOutputDir();
            lastOutputFormat = optionsDialog.getSelectedOutputType();

            org.jts.jsidl.binding.ServiceSet jaxbServiceSet =
                    org.jts.gui.jmatterToJAXB.ServiceSet.convert(inputServiceSet);

            java.util.List serviceSetList = new java.util.ArrayList();
            serviceSetList.add(jaxbServiceSet);

            DocGenerator docGen = null;

            // use appropriate generator based on user selection
            if (lastOutputFormat.equals(FRAMED_HTML_OPTION)) {
                docGen = new FramedHTMLDocGenerator( serviceSetList,
                        lastOutputDir,
                        lastStylesetDir);
            } else if (lastOutputFormat.equals(LINEAR_HTML_OPTION)) {
                docGen = new LinearHTMLDocGenerator(jaxbServiceSet,
                        lastOutputDir,
                        lastStylesetDir,
                        optionsDialog.getDeleteIntermediatesChecked());
            } else if (lastOutputFormat.equals(WORD_DOC_OPTION)) {                
                docGen = new WordDocGenerator(jaxbServiceSet,
                        lastOutputDir,
                        lastStylesetDir,
                        optionsDialog.getDeleteIntermediatesChecked());
            } else {
                com.u2d.app.Context.getInstance().getViewMechanism().message(
                        "Error while generating documention! ");
                throw new RuntimeException("Unexpected results from output options dialog dialog.");
            }

            // prepare to show the docgen status dialog.
            GUISupport.DialogDisplayer statusDisplayer = new GUISupport.DialogDisplayer() {
                public void setupDialog() {
                    DocgenStatusDialog statusDialog  = new DocgenStatusDialog(GUI.getFrame(),
                            "Beginning document generation");
                    statusDialog.setCloseButtonText("Please Wait...");
                    setDialogInstance(statusDialog);
                }
                public void showDialog() {
                    getDialogInstance().setVisible(true);
                }
            };

            // show the docgen status dialog.
            try {
                GUISupport.runOnEDT(statusDisplayer);
            } catch(Exception ex) {
                throw new RuntimeException("Error while displaying document generation status dialog.", ex);
            }

            // statusDisplayer's dialog instance must be a DocgenStatusMonitor!
            docGen.setStatusMonitor((DocgenStatusMonitor) statusDisplayer.getDialogInstance());
            new Thread(docGen).start();

            ((DocgenStatusMonitor) statusDisplayer.getDialogInstance()).updateStatus("Generating protocol behavior diagrams...");

            File imgDir = new File(lastOutputDir, "images/");
            serviceSetList = jaxbServiceSet.getServiceDef();
            ProtocolImageGenerator.generateProtocolBehaviorImagesForServiceDefs(imgDir, serviceSetList);            
            
            ((DocgenStatusMonitor) statusDisplayer.getDialogInstance()).updateStatus("Completing document generation...");

        }
    }
}
