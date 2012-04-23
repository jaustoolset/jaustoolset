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
 * DocgenOutputOptionsDialog.java
 *
 * Created on Dec 2, 2010, 5:19:05 PM
 */

package org.jts.gui.docGenerator;

import java.awt.Frame;
import java.io.File;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import org.jts.gui.util.GUISupport;
import org.jts.gui.util.TooltipComboBoxRenderer;

/**
 * Dialog box for getting user's selections for document generation output options, including:
 *  * Type of output format
 *  * Directory where the stylization customization package is located
 *  * Directory where output will be generated
 *  * Whether to leave intermediate files behind.
 *
 * Dialog is intended to be shown modally and is constructed setting modal to true.
 *
 * User can choose either to proceed with generation ("Generate" button) or cancel.  Clicking
 * "Generate" verifies that the selected style cust. directory actually exists and is a directory,
 * and the dialog doesn't close unless verifcation succeeds.
 *
 * If the user clicks "Generate" and verification succeeds, wasGenerateConfirmed returns true.
 * If wasGenerateConfirmed returns true, getSelectedOutputType and getSelectedCustomizationDir
 * return the output format type and customization package dir.  If wasGenerateConfirmed is false,
 * the getters return null.
 *
 * @author idurkan
 */
public class DocgenOutputOptionsDialog extends javax.swing.JDialog {

    /**
     * stores the default customization dir so it can be reset easily.
     */
    protected File defaultCustomizationDir = null;

    /**
     * stores the default/initial output dir so it can be reset easily
     */
    protected File defaultOutputDir = null;

    /**
     * remains null until user clicks Generate 
     */
    protected File selectedCustomizationDir = null;

    /**
     * also remains null until Generate click
     */
    protected File selectedOutputDir = null;

    /**
     * remains null until user clicks Generate.
     */
    protected String selectedOutputType = null;

    /**
     * Flag indicating dialog closed when user clicked "Generate".
     */
    protected boolean generateConfirmed = false;

    /**
     * Prepares DocgenOutputOptionsDialog for display.  Use setVisible(true) to show the dialog.  Note
     * dialog is *always* modal.
     * @param parent
     *     Parent frame
     * @param firstOutputDir
     *     Output directory to display initially
     * @param firstCustDir
     *     Stylization customization directory to display initially.
     * @param defaultCustDir
     *     Default stylization customization directory; style. cust. dir is reset to this path when
     *     user clicks "default" button near style cust dir field.
     * @param newOutputTypes
     *     Set of document generation output types available.
     * @param newOutputTypeTooltips
     *     Tooltip strings explaining the options in newOutputTypes to the user.
     * @param firstOutputType
     *     The doc. gen. output type to have selected initially.
     */
    public DocgenOutputOptionsDialog(Frame parent, File firstOutputDir, File firstCustDir, File defaultCustDir,
            String[] newOutputTypes, String[] newOutputTypeTooltips, String firstOutputType) {

        super(parent, true);
        initComponents();

        // set up combo box
        String[] outputTypes = new String[newOutputTypes.length];
        System.arraycopy(newOutputTypes, 0, outputTypes, 0, newOutputTypes.length);
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel(outputTypes);
        outputTypeComboBox.setModel(comboModel);
        comboModel.setSelectedItem(firstOutputType);

        TooltipComboBoxRenderer boxRenderer = new TooltipComboBoxRenderer(newOutputTypeTooltips);
        outputTypeComboBox.setRenderer(boxRenderer);

        // set up directory text fields.
        defaultOutputDir = firstOutputDir.getAbsoluteFile();
        outputDirField.setText(defaultOutputDir.getAbsolutePath());

        defaultCustomizationDir = defaultCustDir.getAbsoluteFile();
        customizationDirField.setText(firstCustDir.getAbsolutePath());

        // other dialog setup
        this.getRootPane().setDefaultButton(generateButton);
        this.setTitle("Select Document Generation Output Options");

        // make Swing center the dialog on the parent window while keeping the dialog on one monitor.
        this.setLocationRelativeTo(this.getParent());
    }

    /**
     * Indicates whether the user clicked "Generate" to close the dialog.
     * @return True if the user clicked Generate, causing the dialog to close.
     */
    public boolean wasGenerateConfirmed() {
        return generateConfirmed;
    }

    /**
     * Indicates which output type option was selected when the dialog closed.
     * @return The output type option selected when the dialog closed via Generate being clicked 
     * by the user, or null if Generate was not clicked.
     */
    public String getSelectedOutputType() {
        if (selectedOutputType != null) {
            return selectedOutputType;
        } else {
            return null;
        }
    }

    /**
     * Returns a File corresponding to the path in the output dir text field when the dialog closed.
     * @return File corresponding to the path in the output dir text field when the dialog closed, or null
     * if the dialog closed by some means other than Generate being clicked.
     */
    public File getSelectedOutputDir() {
        if (selectedOutputDir != null) {
            return selectedOutputDir.getAbsoluteFile();
        } else {
            return null;
        }
    }

    /**
     * Returns a File corresponding to the path in the customization dir text field when the dialog closed.
     * @return File corresponding to the path in the customization dir text field when the dialog closed, or null
     * if the dialog closed by some means other than Generate being clicked.
     */
    public File getSelectedCustomizationDir() {
        if (selectedCustomizationDir != null) {
            return selectedCustomizationDir.getAbsoluteFile();
        } else {
            return null;
        }
    }

    /**
     * Returns the checked status of the Delete Intermediates check box.
     * @return checked status of the Delete Intermediates check box
     */
    public boolean getDeleteIntermediatesChecked() {
        return intermedFilesCheckbox.isSelected();
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        generateButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        customizationDirResetButton = new javax.swing.JButton();
        customizationDirBrowseButton = new javax.swing.JButton();
        customizationDirField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        outputTypeComboBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        outputDirField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        outputDirBrowseButton = new javax.swing.JButton();
        intermedFilesCheckbox = new javax.swing.JCheckBox();
        outputDirResetButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("");
        setResizable(false);

        generateButton.setText("Generate Documentation");
        generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        customizationDirResetButton.setText("Restore Default");
        customizationDirResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customizationDirResetButtonActionPerformed(evt);
            }
        });

        customizationDirBrowseButton.setText("Browse...");
        customizationDirBrowseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customizationDirBrowseButtonActionPerformed(evt);
            }
        });

        customizationDirField.setText("dummy");
        customizationDirField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                customizationDirFieldFocusGained(evt);
            }
        });

        jLabel1.setText("Select Custom Stylization Directory (change optionally):");

        jLabel2.setText("Select Output Type");

        outputDirField.setText("dummy");
        outputDirField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                outputDirFieldFocusGained(evt);
            }
        });

        jLabel3.setText("Select Output Directory");

        outputDirBrowseButton.setText("Browse...");
        outputDirBrowseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outputDirBrowseButtonActionPerformed(evt);
            }
        });

        intermedFilesCheckbox.setSelected(true);
        intermedFilesCheckbox.setText("Delete Intermediate Files");
        intermedFilesCheckbox.setToolTipText("If selected: intermediate files created during document generation will be deleted after generation completes. These files are helpful when developing a Custom Stylization Directory.");

        outputDirResetButton.setText("Restore Default");
        outputDirResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outputDirResetButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(generateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(outputTypeComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 386, Short.MAX_VALUE)
                                    .addComponent(customizationDirField, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(299, 299, 299))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(outputDirField, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(customizationDirBrowseButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(customizationDirResetButton))
                            .addComponent(intermedFilesCheckbox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(outputDirBrowseButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(outputDirResetButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(outputDirField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(outputDirBrowseButton)
                    .addComponent(outputDirResetButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(outputTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(intermedFilesCheckbox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customizationDirField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customizationDirResetButton)
                    .addComponent(customizationDirBrowseButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(generateButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed

        // try to turn the customization dir's text field into a file...
        File custDirInText = new File(customizationDirField.getText());
        
        File outputDirInText = new File(outputDirField.getText());

        // test for valid output dir
        if (outputDirInText.exists() && outputDirInText.isDirectory()) {

            // test for valid customization styleset dir
            if (custDirInText.exists() && custDirInText.isDirectory()) {
                selectedOutputType = (String)outputTypeComboBox.getModel().getSelectedItem();
                selectedOutputDir = outputDirInText;
                selectedCustomizationDir = custDirInText;

                this.setVisible(false);

                generateConfirmed = true;
            } else {
                // directory not valid, complain and don't close
                JOptionPane.showMessageDialog(this, "The selected custom stylization directory does"
                        + " not exist, or is not a directory.  Select an existant custom stylization directory.",
                        "Invalid Custom Stylization Directory", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // directory not valid, complain and don't close
            JOptionPane.showMessageDialog(this, "The selected output directory does"
                    + " not exist, or is not a directory.  Select an existant output directory.",
                    "Invalid Output Directory", JOptionPane.ERROR_MESSAGE);
        }
}//GEN-LAST:event_generateButtonActionPerformed

    private void customizationDirResetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customizationDirResetButtonActionPerformed
        customizationDirField.setText(defaultCustomizationDir.getAbsolutePath());
    }//GEN-LAST:event_customizationDirResetButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void customizationDirBrowseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customizationDirBrowseButtonActionPerformed
        // try to start browsing at directory shown in the text box
        File dirInText = new File(customizationDirField.getText());
        File browseStartDir = null;

        if (dirInText.exists() && dirInText.isDirectory()) {
            browseStartDir = dirInText;
        }

        File selectedDir = GUISupport.browseForDirectory(this, browseStartDir);

        // set text in text box.
        if (selectedDir != null) {
            customizationDirField.setText(selectedDir.getAbsolutePath());
        }
    }//GEN-LAST:event_customizationDirBrowseButtonActionPerformed

    private void outputDirBrowseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outputDirBrowseButtonActionPerformed
        File dirInText = new File(outputDirField.getText());
        File browseStartDir = null;

        if (dirInText.exists() && dirInText.isDirectory()) {
            browseStartDir = dirInText;
        }

        File selectedDir = GUISupport.browseForDirectory(this, browseStartDir);

        // set text in text box.
        if (selectedDir != null) {
            outputDirField.setText(selectedDir.getAbsolutePath());
        }
    }//GEN-LAST:event_outputDirBrowseButtonActionPerformed

    private void outputDirFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_outputDirFieldFocusGained
        outputDirField.selectAll();
    }//GEN-LAST:event_outputDirFieldFocusGained

    private void customizationDirFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_customizationDirFieldFocusGained
        customizationDirField.selectAll();
    }//GEN-LAST:event_customizationDirFieldFocusGained

    private void outputDirResetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outputDirResetButtonActionPerformed
        outputDirField.setText(defaultOutputDir.getAbsolutePath());
    }//GEN-LAST:event_outputDirResetButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton cancelButton;
    private javax.swing.JButton customizationDirBrowseButton;
    private javax.swing.JTextField customizationDirField;
    private javax.swing.JButton customizationDirResetButton;
    protected javax.swing.JButton generateButton;
    private javax.swing.JCheckBox intermedFilesCheckbox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton outputDirBrowseButton;
    private javax.swing.JTextField outputDirField;
    private javax.swing.JButton outputDirResetButton;
    private javax.swing.JComboBox outputTypeComboBox;
    // End of variables declaration//GEN-END:variables

}
