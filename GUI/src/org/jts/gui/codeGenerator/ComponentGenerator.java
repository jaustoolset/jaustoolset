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

package org.jts.gui.codeGenerator;

import com.u2d.app.Context;
import com.u2d.generated.Component;
import java.io.File;
import org.jts.codegenerator.CodeLines;
import org.jts.gui.GUI;
import org.jts.gui.util.GUISupport;

/* This class delegates the task of generating code to org.jts.codegenerator.ComponentGenerator
*/
public class ComponentGenerator {

    private static class GeneratorRunner implements Runnable {
        private Component component;
        private File path;
        private CodeLines.CodeType language;
        private CodeLines.BuildType build;

        public GeneratorRunner(Component inputComponent, File outputPath, CodeLines.CodeType outputLanguage, CodeLines.BuildType buildType) {
            component = inputComponent;
            path = outputPath;
            language = outputLanguage;
            build = buildType;
        }

        public void run() {
            Context.getInstance().getViewMechanism().message("Starting code generation... ");

            org.jts.codegenerator.ComponentGenerator cg =
                    new org.jts.codegenerator.ComponentGenerator(language, build);

            java.util.List ssList = component.getServiceSets().getItems();

            // The code generator cannot handle more than 1 service set, so combine
            // all of 'em into a single set.
            com.u2d.generated.ServiceSet ss = new com.u2d.generated.ServiceSet();
            for (int ii = 0; ii < ssList.size(); ii++) {
                com.u2d.generated.ServiceSet source = (com.u2d.generated.ServiceSet) ssList.get(ii);

                for (int jj = 0; jj < source.getServiceDefs().getItems().size(); jj++) {
                    ss.getServiceDefs().add((com.u2d.generated.ServiceDef) source.getServiceDefs().getItems().get(jj));
                }
            }

            // convert service set to a jaxb service set
            org.jts.jsidl.binding.ServiceSet jaxbSS =
                    org.jts.gui.jmatterToJAXB.ServiceSet.convert(ss);

            // call code generator
            cg.run(path.getAbsolutePath(),
                    component.getName().toString(),
                    Integer.toString(component.getComponentId().intValue()),
                    jaxbSS);

            Context.getInstance().getViewMechanism().message("Code generation complete. ");
        }
    }

    /**
     * allows reuse of the user's last selected output dir.
     */
    private static File lastOutputDir = null;

    /**
     * allows reuse of the user's last selected output format.
     */
    private static CodeLines.CodeType lastOutputLanguage = null;
    private static CodeLines.BuildType lastBuildType = null;

    private Component inputComponent;

    public ComponentGenerator(Component component) {
        inputComponent = component;
    }

    public void generate() {
        final File startingOutputDir = 
                (lastOutputDir == null) ? new File(System.getProperty("user.home")) : lastOutputDir;
        final CodeLines.CodeType startingOutputLanguage = 
                (lastOutputLanguage == null) ? CodeLines.CodeType.C_PLUS_PLUS : lastOutputLanguage;
        final CodeLines.BuildType startingBuildType =
                (lastBuildType == null) ? CodeLines.BuildType.SCONS : lastBuildType;

        GUISupport.DialogDisplayer displayer = new GUISupport.DialogDisplayer() {
            public void setupDialog() {
                CodegenOutputOptionsDialog optionsDialog = new CodegenOutputOptionsDialog(
                        GUI.getFrame(),
                        startingOutputDir,
                        startingOutputLanguage,
                        startingBuildType
                        );
                setDialogInstance(optionsDialog);
            }

            public void showDialog() {
                getDialogInstance().setVisible(true);
            }
        };

        try {
            GUISupport.runOnEDT(displayer);
        } catch (Exception ex) {
            throw new RuntimeException("Error while displaying output options dialog.", ex);
        }

        CodegenOutputOptionsDialog optionsDialog = (CodegenOutputOptionsDialog)displayer.getDialogInstance();

        if (optionsDialog.wasGenerateConfirmed()) {

            lastOutputDir = optionsDialog.getSelectedOutputDir();
            lastOutputLanguage = optionsDialog.getSelectedOutputLanguage();
            lastBuildType = optionsDialog.getSelectedBuildType();

            GeneratorRunner genRunner = new GeneratorRunner(inputComponent, lastOutputDir, lastOutputLanguage, lastBuildType);
            new Thread(genRunner).start();

        }
    }
}