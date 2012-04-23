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
package org.jts.gui.deepDelete;

import com.u2d.generated.ServiceDef;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import org.jts.deepDelete.DeepDeleteProcessor;
import org.jts.gui.GUI;
import org.jts.gui.util.GUISupport;

/**
 * Start point for the deep deletion operation.  See org.jts.deepDelete.DeepDeleteProcessor class comment for futher
 * explanation of the deep deletion process.
 * @author idurkan
 */
public class DeepDeleteStart {

    /**
     * Drives the complete deep delete interaction with the user.  Performed via a runnable so this time-consuming work
     * can be taken off the EDT.
     */
    private static class UserInteractionDriver implements Runnable {

        private ServiceDef deletionTarget = null;

        /**
         * Creates a UserInteractionDriver instance that displays GUI prompts leading the user through recursive
         * deletion of the service def deletionTarget.
         * @param deletionTarget Service def that will be deleted if the user confirms its deletion.
         */
        public UserInteractionDriver(final ServiceDef deletionTarget) {
            this.deletionTarget = deletionTarget;
        }

        /**
         * Performs several steps of the recursive deletion process:
         *  1. Determines if any service definitions or service sets depend on deletionTarget.  If any do, a list
         *  dialog is displayed to the user showing those dependents and the process ends.
         *  2. Computes "deletion results": finds the set of subelements of deletionTarget that have no dependent
         *  elements outside of deletionTarget.  These elements may be safely deleted along with deletionTarget
         *  itself.  Subelements that *do* have dependents outside deletionTarget are called "retained" elements and
         *  may not be deleted safely.
         *  3. Displays a dialog to the user listing both safely-deletable and retained elements, asking the user to
         *  confirm deletion of the deletable elements.  The user may cancel, aborting the recursive deletion process.
         *  4. Deletes the safely-deletable elements.
         *
         * Note most algorithmic logic is delegated to DeepDeleteProcessor.  Detailed explanation of the algorithms
         * used is documented in that class.
         */
        public void run() {
            WaitDialogDisplayer waiterInstance = new WaitDialogDisplayer("Determining elements to delete...",
                    "Please Wait...");

            try {
                DeepDeleteProcessor deleteProcessor = new DeepDeleteProcessor(deletionTarget);

                final String targetName = deletionTarget.getName().stringValue()
                        + " ( Service UID: " + deletionTarget.getServiceId().toString()
                        + ", service version: " + deletionTarget.get_version().toString() + " )";

                final List<String> dependantSSets = deleteProcessor.getReferencingEntityInfo();

                if (!dependantSSets.isEmpty()) {
                    // show listing of service sets that depend on the target service def
                    GUISupport.DialogDisplayer listDisplayer = new GUISupport.DialogDisplayer() {

                        public void setupDialog() {
                            ListDisplayDialog listDialog = new ListDisplayDialog(
                                    GUI.getFrame(), true,
                                    "Cannot Recursively Delete Service Definition",
                                    "The following ServiceSets and/or ServiceDefs depend on " + targetName + ":",
                                    dependantSSets);
                            setDialogInstance(listDialog);
                        }

                        public void showDialog() {
                            getDialogInstance().setVisible(true);
                        }
                    };

                    // show the output options dialog - will block.
                    try {
                        GUISupport.runOnEDT(listDisplayer);
                    } catch (Exception ex) {
                        throw new RuntimeException("Error while displaying output options dialog.", ex);
                    }

                    return;
                }

                waiterInstance.show();

                deleteProcessor.computeDeletionResults();

                waiterInstance.close();

                // get strings describing the names and types of both deletable and retained elements.
                final List<String> deletableElementsInfo = deleteProcessor.getDeletableElementsInfo();
                final List<String> retainedElementsInfo = deleteProcessor.getRetainedElementsInfo();

                Collections.sort(deletableElementsInfo);
                Collections.sort(retainedElementsInfo);

                // show user which elements will be deleted and which will remain after the deep
                // delete operation is complete, and allow them to choose whether to continue or give up.
                GUISupport.DialogDisplayer confirmDisplayer = new GUISupport.DialogDisplayer() {

                    public void setupDialog() {
                        DeleteConfirmationDialog listDialog = new DeleteConfirmationDialog(GUI.getFrame(),
                                targetName, deletableElementsInfo, retainedElementsInfo);
                        setDialogInstance(listDialog);
                    }

                    public void showDialog() {
                        getDialogInstance().setVisible(true);
                    }
                };

                // displaying modal dialog, will block
                try {
                    GUISupport.runOnEDT(confirmDisplayer);
                } catch (Exception ex) {
                    throw new RuntimeException("Error while displaying output options dialog.", ex);
                }

                DeleteConfirmationDialog confirmDialog =
                        (DeleteConfirmationDialog) confirmDisplayer.getDialogInstance();

                if (!confirmDialog.getUserConfirmed()) {
                    return;
                }

                // show a modal wait dialog and perform the deletion
                waiterInstance = new WaitDialogDisplayer("Deleting elements valid for deletion...", "Please Wait...");
                waiterInstance.show();

                deleteProcessor.performDeletion();

                // knock down the wait dialog.
                waiterInstance.close();

                try {
                    GUISupport.runOnEDT(new Runnable() {

                        public void run() {
                            JOptionPane.showMessageDialog(GUI.getFrame(), "The service definition was successfully deleted",
                                    "Deletion Complete", JOptionPane.INFORMATION_MESSAGE);
                        }
                    });
                } catch (Exception ex) {
                    throw new RuntimeException("Error while displaying completion message.", ex);
                }
            } catch (Exception ex) {
                try {
                    waiterInstance.close();
                    GUISupport.runOnEDT(new Runnable() {

                        public void run() {
                            JOptionPane.showMessageDialog(GUI.getFrame(), "An error occurred during deep deletion "
                                    + "processing.  Refer to console output for stack trace.", "Deletion Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    });
                } catch (Exception ex_inner) { 
                    throw new RuntimeException("Error while displaying error message, " + ex_inner.toString() 
                            + " thrown while showing error popup, original exception was: ", ex);
                }

                throw new RuntimeException("Exception during deep delete processing: ", ex);
            }
        }
    }

    /**
     * The start point for the deep deletion operation called by the JMatter generated ServiceDef.
     * @param deletionTarget
     */
    public static void performDelete(ServiceDef deletionTarget) {
        UserInteractionDriver driver = new UserInteractionDriver(deletionTarget);

        new Thread(driver).start();
    }
}
