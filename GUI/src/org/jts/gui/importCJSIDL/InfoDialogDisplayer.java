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
package org.jts.gui.importCJSIDL;

import org.jts.gui.importJSIDL.*;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.BorderFactory;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;
import org.jts.gui.importCJSIDL.Import.ImportStatusMonitor;
import org.jts.gui.util.GUISupport;

/**
 *
 * @author Arfath Pasha
 *
 */
class InfoDialogDisplayer implements Runnable, ImportStatusMonitor {

    Frame parentFrame;
    JPanel panel;
    //JLabel label;
    JTextArea labelArea;
    JButton cancelButton;
    JDialog dialog;
    // provided so outside classes can listen for cancel button click
    ActionListener externalCancelListener;
    // so the dialog can eventually be closed.
    ActionListener internalCancelListener;

    InfoDialogDisplayer(Frame newParentFrame) {
        parentFrame = newParentFrame;
    }

    public void run() {
        setupAndShowDialog();
    }

    void setupAndShowDialog() {
        // check dependencies before showing
        if (externalCancelListener == null) {
            throw new DependencyException("A cancel button ActionListener is required for the import InfoDialog");
        }

        // create a listener that will close the dialog, but note it's not attached to the cancel button at this point.
        internalCancelListener = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                InfoDialogDisplayer.this.dispose();
            }
        };

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setPreferredSize(new Dimension(400, 400));

        labelArea = new JTextArea("Searching for CJSIDL files...", 5, 25);
        labelArea.setEditable(false);
        labelArea.setLineWrap(true);

        // make the text area always scroll so newly-appended text is visible.
        DefaultCaret caret = (DefaultCaret)labelArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        JScrollPane labelScroller = new JScrollPane(labelArea);

        panel.add(labelScroller);
        panel.add(Box.createRigidArea(new Dimension(10, 10)));


        cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("Cancel");
        cancelButton.addActionListener(externalCancelListener);

        panel.add(cancelButton);

        dialog = new JDialog(parentFrame, "Processing Import...", false);

        dialog.setContentPane(panel);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        dialog.setPreferredSize(new Dimension(650, 340));
        dialog.pack();
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }

    void setCancelActionListener(ActionListener listener) {
        externalCancelListener = listener;
    }

    void dispose() {
        dialog.setVisible(false);
        dialog.dispose();
    }

    @Override
    public void updateStatus(final String message) {
        try {
            GUISupport.runOnEDT(new Runnable() {

                public void run() {
                    labelArea.append("\n" + message);

                    // update caret so scroll pane will move to end of text area
                    labelArea.setCaretPosition( labelArea.getDocument().getLength() );
                }
            });
        } catch (Exception ex) {
            throw new RuntimeException("Error when updating status label in info dialog.", ex);
        }
    }

    @Override
    public void reportFailure(final String message, final Exception cause) {
        try {
            GUISupport.runOnEDT(new Runnable() {

                public void run() {
                    labelArea.append("\n" + message);
                    cancelButton.setText("Close");
                    dialog.setTitle("Error During Import!");

                    // NOW the cancel button will trigger the dialog to close.
                    cancelButton.removeActionListener(externalCancelListener);
                    cancelButton.addActionListener(internalCancelListener);
                }
            });

        } catch (Exception ex) {
            throw new RuntimeException("Error when altering info dialog after failure report.", ex);
        }
    }

    @Override
    public void reportCompletion(final String message) {
        try {
            GUISupport.runOnEDT(new Runnable() {

                public void run() {
                    labelArea.append("\n" + message);
                    cancelButton.setText("Close");
                    dialog.setTitle("Import Complete!");

                    // NOW the cancel button will trigger the dialog to close.
                    cancelButton.removeActionListener(externalCancelListener);
                    cancelButton.addActionListener(internalCancelListener);
                }
            });

        } catch (Exception ex) {
            throw new RuntimeException("Error when altering info dialog after import completed.", ex);
        }
    }

    public static void main(String[] args) {
        try {
            GUISupport.runOnEDT(new Runnable() {

                public void run() {
                    final InfoDialogDisplayer displayer = new InfoDialogDisplayer(null);

                    displayer.setCancelActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e) {
                            displayer.dispose();
                        }
                    });

                    displayer.setupAndShowDialog();
                }
            });
        } catch (InterruptedException ex) {
            Logger.getLogger(InfoDialogDisplayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(InfoDialogDisplayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
