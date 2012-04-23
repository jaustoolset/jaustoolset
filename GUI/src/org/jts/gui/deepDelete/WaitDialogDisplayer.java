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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.lang.reflect.InvocationTargetException;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jts.gui.GUI;
import org.jts.gui.util.GUISupport;

/**
 * The WaitDialogDisplayer provides a way to "lock down" the JTS JMatter GUI while a long operation occurs where
 * we do not want the user to be able to change database contents.  It modally displays a simple dialog with a label
 * centered in the middle, and no other controls.
 * @author idurkan
 */
class WaitDialogDisplayer {

    private final String message;
    private final String title;
    private JDialog waiter = null;
    boolean alreadyClosed = false;

    /**
     * Create a WaitDialogDisplayer that will show a wait dialog with the given message and title.
     * @param message Message to show in the label.
     * @param title Title to use in the dialog
     */
    public WaitDialogDisplayer(String message, String title) {
        this.message = message;
        this.title = title;
    }

    /**
     * Set up a simple dialog showing a label that cannot be closed by normal means.
     */
    private class WaitDialog extends JDialog {

        public WaitDialog(Frame parent) {
            super(parent, true); /* dialog is always modal */

            final int BORDER_WIDTH = 10;

            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.setBorder(BorderFactory.createEmptyBorder(
                    BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH));

            JLabel label = new JLabel(WaitDialogDisplayer.this.message);

            panel.add(label, BorderLayout.CENTER);

            this.setContentPane(panel);
            this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

            this.setPreferredSize(new Dimension(250, 100));
            this.setTitle(WaitDialogDisplayer.this.title);

            this.setResizable(false);
            this.setLocationRelativeTo(parent);
            this.pack();
        }
    };

    /**
     * Shows a wait dialog modally on a separate thread, blocking that thread rather than the caller.
     */
    public void show() {
        // launch the code to show the modal dialog on a separate thread so the caller can continue.
        new Thread(new Runnable() {

            public void run() {
                try {
                    GUISupport.runOnEDT(new Runnable() {

                        public void run() {
                            // watch out for race condition if the work thread calls close() very
                            // quickly after calling show().
                            if (!alreadyClosed) {
                                waiter = new WaitDialog(GUI.getFrame());
                                waiter.setVisible(true);
                            }

                            alreadyClosed = false;
                        }
                    });
                } catch (InterruptedException ieex) {
                    throw new RuntimeException(ieex);
                } catch (InvocationTargetException itex) {
                    throw new RuntimeException(itex);
                }

            }
        }).start();
    }

    /**
     * Close and dispose the wait dialog if it is visible.
     */
    public void close() {
        try {
            GUISupport.runOnEDT(new Runnable() {

                public void run() {
                    // avoid race condition if the work thread calls close() very
                    // quickly after calling show().
                    alreadyClosed = true;

                    // close() might be called accidentally before waiter has been set up.
                    if (waiter != null && waiter.isVisible()) {
                        waiter.setVisible(false);
                        waiter.dispose();
                    }
                }
            });
        } catch (InterruptedException ieex) {
            throw new RuntimeException(ieex);
        } catch (InvocationTargetException itex) {
            throw new RuntimeException(itex);
        }
    }
}
