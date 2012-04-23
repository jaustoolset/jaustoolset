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
 * CodegenOutputOptionsDialog.java
 *
 * Created on Jan 10, 2011, 1:46:10 PM
 */
package org.jts.gui.codeGenerator;

import java.io.File;
import javax.swing.JOptionPane;
import org.jts.codegenerator.CodeLines;
import org.jts.gui.util.GUISupport;

/**
 *
 * @author idurkan
 */
public class CodegenOutputOptionsDialog extends javax.swing.JDialog {

    private boolean generateConfirmed = false;
    private CodeLines.CodeType selectedOutputLanguage = null;
    private CodeLines.BuildType selectedBuildType = CodeLines.BuildType.SCONS;
    private File selectedOutputDir = null;
    private File lastOutputDir = null;

    /** Creates new form CodegenOutputOptionsDialog */
    public CodegenOutputOptionsDialog(java.awt.Frame parent, File initialOutputDir,
            CodeLines.CodeType initialOutputLanguage, CodeLines.BuildType initialBuildType) {
        super(parent, true);
        initComponents();

        if (initialOutputDir == null) {
            throw new RuntimeException("initialOutputDir may not be null when creating a "
                    + "CodegenOutputOptionsDialog.");
        }

        if (initialOutputLanguage == null) {
            throw new RuntimeException("initialOutputLanguage may not be null when creating a "
                    + "CodegenOutputOptionsDialog.");
        }
        
        if (initialBuildType == null) {
            throw new RuntimeException("initialBuildType may not be null when creating a "
                    + "CodegenOutputOptionsDialog.");
        }

        lastOutputDir = initialOutputDir.getAbsoluteFile();
        outputDirField.setText(lastOutputDir.getAbsolutePath());

        selectedOutputLanguage = initialOutputLanguage;
        switch (selectedOutputLanguage) {
            case C_PLUS_PLUS:
                cppRadioButton.setSelected(true);
                break;
            case C_SHARP:
                csharpRadioButton.setSelected(true);
                break;
            case JAVA:
                javaRadioButton.setSelected(true);
                break;
            default:
                throw new RuntimeException("Unexpected value of output language enum");
        }
        
        selectedBuildType = initialBuildType;
        switch( selectedBuildType )
        {
            case SCONS:
                sconsRadioButton.setSelected( true );
                break;
            case VS:
                vsRadioButton.setSelected( true );
                break;
            default:
                throw new RuntimeException("Unexpected value of output build type");
        }

        // other dialog setup
        this.getRootPane().setDefaultButton(generateButton);
        this.setTitle("Select Code Generation Options");

        // make Swing center the dialog on the parent window while keeping the dialog on one monitor.
        this.setLocationRelativeTo(this.getParent());
    }

    /**
     * Indicates whether the user clicked Generate to close the dialog.
     * @return True if the user clicked Generate, causing the dialog to close, false OTW.
     */
    public boolean wasGenerateConfirmed() {
        return generateConfirmed;
    }

    public File getSelectedOutputDir() {
        if (generateConfirmed) {
            return selectedOutputDir.getAbsoluteFile();
        } else {
            return null;
        }
    }

    public CodeLines.CodeType getSelectedOutputLanguage() {
        if (generateConfirmed) {
            return selectedOutputLanguage;
        } else {
            return null;
        }
    }
    
    public CodeLines.BuildType getSelectedBuildType()
    {
        return selectedBuildType;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        languageButtonGroup = new javax.swing.ButtonGroup();
        buildGroup = new javax.swing.ButtonGroup();
        generateButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        useLastButton = new javax.swing.JButton();
        browseButton = new javax.swing.JButton();
        outputDirField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cppRadioButton = new javax.swing.JRadioButton();
        csharpRadioButton = new javax.swing.JRadioButton();
        javaRadioButton = new javax.swing.JRadioButton();
        sconsRadioButton = new javax.swing.JRadioButton();
        vsRadioButton = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        generateButton.setText("Generate Code");
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

        useLastButton.setText("Use Last");
        useLastButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                useLastButtonActionPerformed(evt);
            }
        });

        browseButton.setText("Browse...");
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        outputDirField.setText("Dummy");
        outputDirField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                outputDirFieldFocusGained(evt);
            }
        });

        jLabel1.setText("Select Output Parent Directory");

        jLabel2.setText("Select Output Language:");

        languageButtonGroup.add(cppRadioButton);
        cppRadioButton.setText("C++");
        cppRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cppRadioButtonActionPerformed(evt);
            }
        });

        languageButtonGroup.add(csharpRadioButton);
        csharpRadioButton.setText("C#");
        csharpRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                csharpRadioButtonActionPerformed(evt);
            }
        });

        languageButtonGroup.add(javaRadioButton);
        javaRadioButton.setText("Java");
        javaRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                javaRadioButtonActionPerformed(evt);
            }
        });

        buildGroup.add(sconsRadioButton);
        sconsRadioButton.setSelected(true);
        sconsRadioButton.setText("Scons");

        buildGroup.add(vsRadioButton);
        vsRadioButton.setText("Visual Studio Project");

        jLabel3.setText("Select Build Type:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(outputDirField, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(browseButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(useLastButton)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cppRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(csharpRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(javaRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sconsRadioButton)
                        .addGap(18, 18, 18)
                        .addComponent(vsRadioButton)
                        .addGap(112, 112, 112)
                        .addComponent(generateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(287, 287, 287)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(outputDirField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseButton)
                    .addComponent(useLastButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cppRadioButton)
                    .addComponent(csharpRadioButton)
                    .addComponent(javaRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(vsRadioButton)
                        .addComponent(generateButton)
                        .addComponent(cancelButton))
                    .addComponent(sconsRadioButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed
        File outputDirInText = new File(outputDirField.getText());

        // test for valid output dir
        if (outputDirInText.exists() && outputDirInText.isDirectory()) {

            selectedOutputDir = outputDirInText;

            if (cppRadioButton.isSelected()) {
                selectedOutputLanguage = CodeLines.CodeType.C_PLUS_PLUS;
            } else if (csharpRadioButton.isSelected()) {
                selectedOutputLanguage = CodeLines.CodeType.C_SHARP;
            } else if (javaRadioButton.isSelected()) {
                selectedOutputLanguage = CodeLines.CodeType.JAVA;
            } else {
                throw new RuntimeException("No radio button was selected when user clicked 'Generate'.");
            }
            
            if( sconsRadioButton.isSelected() )
            {
                selectedBuildType = CodeLines.BuildType.SCONS;
            }
            else if( vsRadioButton.isSelected() )
            {
                selectedBuildType = CodeLines.BuildType.VS;
            }

            this.setVisible(false);

            generateConfirmed = true;
        } else {
            JOptionPane.showMessageDialog(this, "The selected output directory does"
                    + " not exist, or is not a directory.  Select an existant output directory.",
                    "Invalid Output Directory", JOptionPane.ERROR_MESSAGE);
        }
}//GEN-LAST:event_generateButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.setVisible(false);
}//GEN-LAST:event_cancelButtonActionPerformed

    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
        File dirInText = new File(outputDirField.getText());
        File browseStartDir = null;

        if (dirInText.exists() && dirInText.isDirectory()) {
            browseStartDir = dirInText;
        }

        File selectedDir = GUISupport.browseForDirectory(this, browseStartDir);

        if (selectedDir != null) {
            outputDirField.setText(selectedDir.getAbsolutePath());
        }
    }//GEN-LAST:event_browseButtonActionPerformed

    private void useLastButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_useLastButtonActionPerformed
        outputDirField.setText(lastOutputDir.getAbsolutePath());
    }//GEN-LAST:event_useLastButtonActionPerformed

    private void outputDirFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_outputDirFieldFocusGained
        outputDirField.selectAll();
    }//GEN-LAST:event_outputDirFieldFocusGained

    private void cppRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cppRadioButtonActionPerformed
        vsRadioButton.setVisible( true );
    }//GEN-LAST:event_cppRadioButtonActionPerformed

    private void csharpRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_csharpRadioButtonActionPerformed
        sconsRadioButton.setSelected( true );
        vsRadioButton.setVisible( false );
    }//GEN-LAST:event_csharpRadioButtonActionPerformed

    private void javaRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_javaRadioButtonActionPerformed
        sconsRadioButton.setSelected( true );
        vsRadioButton.setVisible( false );      
    }//GEN-LAST:event_javaRadioButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseButton;
    private javax.swing.ButtonGroup buildGroup;
    protected javax.swing.JButton cancelButton;
    private javax.swing.JRadioButton cppRadioButton;
    private javax.swing.JRadioButton csharpRadioButton;
    protected javax.swing.JButton generateButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton javaRadioButton;
    private javax.swing.ButtonGroup languageButtonGroup;
    private javax.swing.JTextField outputDirField;
    private javax.swing.JRadioButton sconsRadioButton;
    private javax.swing.JButton useLastButton;
    private javax.swing.JRadioButton vsRadioButton;
    // End of variables declaration//GEN-END:variables
}
