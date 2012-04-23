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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jts.gui.util;

import java.awt.Component;
import java.awt.EventQueue;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 *
 * @author idurkan
 */
public class GUISupport {
    /**
     * Abstract class to streamline initializing and displaying a JDialog extender.  
     * 
     * Construct and setup a JDialog extender's instance in setupDialog, then store it using setDialogInstance.
     * Call getDialogInstance in showDialog, then display the dialog (and perform any other tasks that need to be
     * performed before/after the display call)
     * 
     * A concrete DialogDisplayer instance is a Runnable, so it can be executed on the Event Dispatch Thread.  The run()
     * method calls setupDialog() followed by showDialog().
     */
    public static abstract class DialogDisplayer implements Runnable {
        private JDialog dialogInstance;

        public abstract void setupDialog();
        public abstract void showDialog();

        public void setDialogInstance(JDialog dialog) {
            dialogInstance = dialog;
        }

        public JDialog getDialogInstance() {
            return dialogInstance;
        }

        public void run() {
            setupDialog();
            showDialog();
        }
    }

    /**
     * Utility function for running a Runnable on the Event Dispatch Thread.  Uses InvokeAndWait to run toRun.run() if
     * the caller is *not* on the EDT.  If the caller is on the EDT, just calls toRun.run().
     * @param toRun
     */
    public static void runOnEDT(Runnable toRun) throws InterruptedException, InvocationTargetException {
        if (SwingUtilities.isEventDispatchThread()) {
            // we are already on the EDT, so directly making GUI creation calls is safe.
            toRun.run();
        } else {
            // not on EDT, so need to run GUI creation code on the EDT.
            EventQueue.invokeAndWait(toRun);
        }
    }

    /**
     * Shows a browse dialog for selecting directories only, returning the selected directory
     * as a File if the user selects a directory and null otherwise.
     * @param browseStartDir
     *     The directory to start browsing in.  If null, browsing begins in user's home directory.
     * @return
     *     The directory selected or null if no selection was made or any errors occurred.
     */
    public static File browseForDirectory(Component parent, File browseStartDir) {
        // if browseStartDir is null the chooser begins in the user's home directory.
        JFileChooser dirChooser = new JFileChooser(browseStartDir);
        dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int dialogResult = dirChooser.showDialog(parent, "Select Directory");

        boolean approval = false;
        switch (dialogResult) {
            case JFileChooser.APPROVE_OPTION:
                approval = true;
                break;
            case JFileChooser.CANCEL_OPTION:
                break;
            case JFileChooser.ERROR_OPTION:
                break;
            default:
                throw new RuntimeException("Unexpected result from browse dialog.");
        }

        if (approval) {
            return dirChooser.getSelectedFile();
        } else {
            return null;
        }
    }

    /**
     * Clears the existing text in textArea, then add each string in newContents as a new line
     * in textArea.
     * @param textArea A JTextArea control.
     * @param newContents List of Strings to add to textArea.
     */
    public static void populateTextArea(JTextArea textArea, List<String> newContents) {
        textArea.setText("");

        for (Iterator<String> iter = newContents.iterator(); iter.hasNext(); ) {
            textArea.append(iter.next());

            if (iter.hasNext()) {
                textArea.append(System.getProperty("line.separator"));
            }
        }
    }
}
