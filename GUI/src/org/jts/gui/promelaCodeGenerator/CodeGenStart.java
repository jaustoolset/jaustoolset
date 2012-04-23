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
package org.jts.gui.promelaCodeGenerator;

import com.u2d.generated.ServiceSet;
import com.u2d.generated.Component;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.jts.gui.GUI;
import org.jts.protocolvalidator.DefinitionFinder;
import org.jts.protocolvalidator.ProtocolGenerator; 

/**
 * Start point for the Promela code generation operation.  
 * @author cmessmer
 */
public class CodeGenStart {
    private org.jts.jsidl.binding.ServiceSet serviceSet;


    /**
     * The start point for the deep deletion operation called by the JMatter generated ServiceDef.
     * @param targetServiceDef
     */
    public void generateCode(Component component) {
        java.util.List ssList = component.getServiceSets().getItems();

        // The code generator cannot handle more than 1 service set, so combine
        // all of 'em into a single set.
        com.u2d.generated.ServiceSet ss = new com.u2d.generated.ServiceSet();
        for (int i = 0; i < ssList.size(); i++) {
            com.u2d.generated.ServiceSet targetServiceSet = (com.u2d.generated.ServiceSet) ssList.get(i);

            serviceSet = org.jts.gui.jmatterToJAXB.ServiceSet.convert(targetServiceSet);
            String outputPath = System.getProperty("user.home");
            JFileChooser fc = new JFileChooser(new File(outputPath));
            fc.setFileSelectionMode(fc.DIRECTORIES_ONLY);
            // Show open dialog; this method does not return until the dialog is closed
            fc.showOpenDialog(GUI.getFrame());
            File selFile = fc.getSelectedFile();
            if(selFile != null && selFile.exists()){
                try {
                    ProtocolGenerator gen = new ProtocolGenerator(serviceSet, selFile);
                } catch (Exception ex) {
                    Logger.getLogger(CodeGenStart.class.getName()).log(Level.SEVERE, null, ex);
                    System.err.println(ex.getMessage());
                    System.err.println(ex.getLocalizedMessage());
                }
            }
        }
    }

    /**
     * The start point for the deep deletion operation called by the JMatter generated ServiceDef.
     * @param targetServiceDef
     */
    public void generateCode(ServiceSet targetServiceSet) {
        serviceSet = org.jts.gui.jmatterToJAXB.ServiceSet.convert(targetServiceSet);
        String outputPath = System.getProperty("user.home");
        JFileChooser fc = new JFileChooser(new File(outputPath));
        fc.setFileSelectionMode(fc.DIRECTORIES_ONLY);
        // Show open dialog; this method does not return until the dialog is closed
        fc.showOpenDialog(GUI.getFrame());
        File selFile = fc.getSelectedFile();
        if(selFile != null && selFile.exists()){
            try {
                ProtocolGenerator gen = new ProtocolGenerator(serviceSet, selFile);
            } catch (Exception ex) {
                Logger.getLogger(CodeGenStart.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println(ex.getMessage());
                System.err.println(ex.getLocalizedMessage());
            }
        }
    }
}
