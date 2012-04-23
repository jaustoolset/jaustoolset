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

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jts.codegenerator.CodeLines.BuildType;
import org.jts.codegenerator.CodeLines.CodeType;
import org.jts.gui.docGenerator.DocgenOutputOptionsDialog;

/**
 *
 * @author idurkan
 */
public class TestCodegenOutputOptionsDialog {

    private static void showDialogPrintResults(CodegenOutputOptionsDialog toShow) {
        toShow.setVisible(true);

        System.out.println("setVisible call ended!");

        System.out.println("User confirmed generate: " + toShow.wasGenerateConfirmed());

        if (toShow.getSelectedOutputDir() != null) {
            System.out.println("Selected output dir: " + toShow.getSelectedOutputDir());
        } else {
            System.out.println("Selected output dir was null.");
        }

        if (toShow.getSelectedOutputLanguage() != null) {
            System.out.println("Selected output language: " + toShow.getSelectedOutputLanguage());
        } else {
            System.out.println("Selected output language was null.");
        }

        System.out.println("");
    }

    private static void runTests() {
        CodegenOutputOptionsDialog dialog1 = new CodegenOutputOptionsDialog(null, 
                new File("\\projects\\"), CodeType.C_PLUS_PLUS, BuildType.SCONS);

        CodegenOutputOptionsDialog dialog2 = new CodegenOutputOptionsDialog(null,
                new File("\\projects\\"), CodeType.C_SHARP, BuildType.SCONS);

        CodegenOutputOptionsDialog dialog3 = new CodegenOutputOptionsDialog(null,
                new File(System.getProperty("user.home")), CodeType.JAVA, BuildType.SCONS);

        showDialogPrintResults(dialog1);
        showDialogPrintResults(dialog2);
        showDialogPrintResults(dialog3);

        dialog1.dispose();
        dialog2.dispose();
        dialog3.dispose();
    }

    public static void main(String args[]) {
        try {
            java.awt.EventQueue.invokeAndWait(
                    new Runnable() {

                        public void run() {
                            runTests();
                        }
                    });

            System.out.println("invokeAndWait call ended!");
        } catch (InterruptedException ex) {
            Logger.getLogger(DocgenOutputOptionsDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(DocgenOutputOptionsDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
