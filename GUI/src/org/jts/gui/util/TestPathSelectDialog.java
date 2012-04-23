/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2010-2011, United States Government
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

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author idurkan
 */
public class TestPathSelectDialog {

    public static void showDialogPrintResults(PathSelectDialog toShow) {
        toShow.setVisible(true);

        System.out.println();
        if (toShow.userSelectedPath()) {
            if (toShow.getSelectedPath() != null) {
                System.out.println("User selected path:" + toShow.getSelectedPath());
            } else {
                System.out.println("Selected path was null!");
            }
        } else {
            
            System.out.println("User cancelled path selection!");
        }
    }

    public static void runTests() {
        PathSelectDialog dialog1 = new PathSelectDialog(null, true, 
                new File(System.getProperty("user.home")), "SELECT DATA",
                "USER: Choose a directory or file immediately!", "Accept");
        dialog1.setFileSelectionMode(PathSelectDialog.FILES_AND_DIRECTORIES);

        PathSelectDialog dialog2 = new PathSelectDialog(null, true,
                new File(System.getProperty("user.home")), "Export JSIDL File",
                "Enter path to a JSIDL file for export.", "Export");
        dialog2.setFileSelectionMode(PathSelectDialog.FILES_ONLY);

        PathSelectDialog dialog3 = new PathSelectDialog(null, true,
                new File(System.getProperty("user.home")), "Import JSIDL File(s)",
                "Select a directory containing one or more JSIDL files", "Import");
        dialog3.setFileSelectionMode(PathSelectDialog.DIRECTORIES_ONLY);

        showDialogPrintResults(dialog1);
        showDialogPrintResults(dialog2);
        showDialogPrintResults(dialog3);


        PathSelectDialog dialog4 = new PathSelectDialog(null, true,
                new File(System.getProperty("user.home")), "Export JSIDL File",
                "Enter path to a JSIDL file for export.", "Export");
        dialog4.setFileSelectionMode(PathSelectDialog.FILES_ONLY);
        dialog4.setSaveMode(true);

        PathSelectDialog dialog5 = new PathSelectDialog(null, true,
                new File(System.getProperty("user.home")), "Export JSIDL File(s)",
                "Select a directory containing one or more JSIDL files", "Import");
        dialog5.setFileSelectionMode(PathSelectDialog.DIRECTORIES_ONLY);
        dialog5.setSaveMode(true);

        showDialogPrintResults(dialog4);
        showDialogPrintResults(dialog5);
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
            Logger.getLogger(TestPathSelectDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(TestPathSelectDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
