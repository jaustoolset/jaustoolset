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

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for testing DocgenOutputOptionsDialog.
 * @author idurkan
 */
public class TestOutputOptionsDialog {
    public static void showDialogPrintResults(DocgenOutputOptionsDialog toShow) {
        toShow.setVisible(true);
        System.out.println("setVisible call ended!");

        System.out.println("User confirmed generate: " + toShow.wasGenerateConfirmed());

        if (toShow.getSelectedOutputType() != null) {
            System.out.println("User selected output type: " + toShow.getSelectedOutputType());
        }

        if (toShow.getSelectedOutputDir() != null) {
            System.out.println("User selected output dir: " + toShow.getSelectedOutputDir().getAbsolutePath());
        }

        if (toShow.getSelectedCustomizationDir() != null) {
            System.out.println("User selected cust dir: " + toShow.getSelectedCustomizationDir().getAbsolutePath());
        }
        System.out.println();
    }

    public static void runTests() {

        String[] selections1 = new String[] {"Apples", "Oranges", "Guavas"};
        String[] tooltips1   = new String[] {"Malus domestica", "Citrus sinensis", "Psidium guajava"};

        DocgenOutputOptionsDialog dialog1 = new DocgenOutputOptionsDialog(new javax.swing.JFrame(), new File("foobar"), new File("foobar"),
                new File("foobar"),
        selections1, tooltips1, "Guavas");

        DocgenOutputOptionsDialog dialog2 =
                new DocgenOutputOptionsDialog(
                    new javax.swing.JFrame(), new File(System.getProperty("user.home")),
                    new File("\\projects\\"), new File("\\projects\\"), new String[] {}, new String[] {}, "Guavas");

        String[] selections3 = new String[] {"Apples", "Oranges", "Pears", "Grapes", "Guavas", "Cauliflower"};
        String[] tooltips3 = new String[] {"Malus domestica", "Citrus sinensis", "Pyrus communis",
            "Vitis vinifera", "Psidium guajava", "Brassica oleracea"};

        DocgenOutputOptionsDialog dialog3 = new DocgenOutputOptionsDialog(new javax.swing.JFrame(), new File("\\demo\\"),
                new File("\\projects\\JTS-branch"), new File("\\projects\\JTS-branch"),
                selections3, tooltips3, "Zerglings");

        DocgenOutputOptionsDialog dialog4 = new DocgenOutputOptionsDialog(new javax.swing.JFrame(), new File("\\demo\\"),
                new File("\\projects\\JTS-branch"), new File("\\projects\\JTS-branch"),
                selections3, new String[] {}, "Zerglings");

        showDialogPrintResults(dialog1);

        showDialogPrintResults(dialog2);

        showDialogPrintResults(dialog3);

        showDialogPrintResults(dialog4);

        dialog1.dispose();
        dialog2.dispose();
        dialog3.dispose();
        dialog4.dispose();
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
