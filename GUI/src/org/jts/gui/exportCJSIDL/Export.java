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
package org.jts.gui.exportCJSIDL;

import com.u2d.list.RelationalList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


import org.jts.gui.GUI;
import org.jts.gui.util.GUISupport;
import org.jts.gui.util.PathSelectDialog;

import org.jts.gui.exportJSIDL.ExportException;
import org.jts.gui.exportJSIDL.RemoveJSIDLPlus;
import org.jts.gui.importCJSIDL.StreamReader;

/**
 * Provides utility methods for exporting JMatter-format JAUS components as JSIDL XML files.
 */
public class Export {

             private final static String classpath = "-classpath lib/runtime/plugins/com.google.collect_0.8.0.v201102150722.jar;"
                  + "lib/runtime/plugins/com.google.inject_2.0.0.v201003051000.jar;lib/runtime/plugins/com.ibm.icu_4.2.1.v20100412.jar;"
                  + "lib/runtime/plugins/de.itemis.xtext.antlr_1.0.1.v201008261834.jar;"
                  + "lib/runtime/plugins/org.antlr.runtime_3.0.0.v200803061811.jar;"
                  + "lib/runtime/plugins/org.apache.commons.cli_1.0.0.v20080604-1500.jar;"
                  + "lib/runtime/plugins/org.apache.commons.lang_2.4.0.v201005080502.jar;"
                  + "lib/runtime/plugins/org.apache.commons.logging_1.1.1.v201005080502.jar;"
                  + "lib/runtime/plugins/org.apache.log4j_1.2.15.v201005080500.jar;"
                  + "lib/runtime/plugins/org.eclipse.emf.codegen.ecore_2.6.1.v20100914-1218.jar;"
                  + "lib/runtime/plugins/org.eclipse.emf.codegen_2.6.0.v20100914-1218.jar;"
                  + "lib/runtime/plugins/org.eclipse.emf.common_2.6.0.v20100914-1218.jar;"
                  + "lib/runtime/plugins/org.eclipse.emf.ecore.xmi_2.5.0.v20100521-1846.jar;"
                  + "lib/runtime/plugins/org.eclipse.emf.ecore_2.6.1.v20100914-1218.jar;"
                  + "lib/runtime/plugins/org.eclipse.emf.mwe.core_1.0.2.v201102150556.jar;"
                  + "lib/runtime/plugins/org.eclipse.emf.mwe.utils_1.0.2.v201102150556.jar;"
                  + "lib/runtime/plugins/org.eclipse.emf.mwe2.language_1.0.2.v201102151014.jar;"
                  + "lib/runtime/plugins/org.eclipse.emf.mwe2.launch_1.0.2.v201102151014.jar;"
                  + "lib/runtime/plugins/org.eclipse.emf.mwe2.runtime_1.0.2.v201102150556.jar;"
                  + "lib/runtime/plugins/org.eclipse.xpand_1.0.1.v201008251147.jar;"
                  + "lib/runtime/plugins/org.eclipse.xtend.typesystem.emf_1.0.1.v201008251147.jar;"
                  + "lib/runtime/plugins/org.eclipse.xtend_1.0.1.v201008251147.jar;"
                  + "lib/runtime/plugins/org.eclipse.xtext.common.types_1.0.2.v201102150722.jar;"
                  + "lib/runtime/plugins/org.eclipse.xtext.generator_1.0.2.v201102150722.jar;"
                  + "lib/runtime/plugins/org.eclipse.xtext.util_1.0.2.v201102150722.jar;"
                  + "lib/runtime/plugins/org.eclipse.xtext.xtend_1.0.2.v201102150722.jar;"
                  + "lib/runtime/plugins/org.eclipse.xtext_1.0.2.v201102150722.jar;"
                  + "lib/runtime/plugins/org.hamcrest.core_1.1.0.v20090501071000.jar;"
                  + "lib/runtime/plugins/org.jts.eclipse.generator_1.0.0.jar;"
                  + "lib/runtime/plugins/org.jts.eclipse.JTSGeneration_1.0.0.jar;"
                  + "lib/runtime/plugins/org.jts.eclipse.ui_1.0.0.jar;lib/runtime/plugins/org.jts.eclipse_1.0.0.jar";


    private static File getServiceSetExportPath(final File lastPath) {

        try {
            GUISupport.DialogDisplayer container = new GUISupport.DialogDisplayer() {

                public void setupDialog() {
                    PathSelectDialog newDialog = new PathSelectDialog(
                            GUI.getFrame(), true, lastPath, "Export ServiceSet to CJSIDL",
                            "Select an output directory where the ServiceSet will be exported as CJSIDL files "
                            + "and click Export.", "Export");
                    newDialog.setFileSelectionMode(PathSelectDialog.DIRECTORIES_ONLY);
                    newDialog.setSaveMode(true);

                    setDialogInstance(newDialog);
                }

                public void showDialog() {
                    getDialogInstance().setVisible(true);
                }
            };

            GUISupport.runOnEDT(container);

            PathSelectDialog dialog = (PathSelectDialog) container.getDialogInstance();

            if (dialog.userSelectedPath()) {
                return dialog.getSelectedPath();
            } else {
                return null;
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error while displaying dialog to get service set export path.", ex);
        }
    }
    private static File lastServiceSetExportPath = null;

    public static void exportServiceSetCJSIDL(com.u2d.generated.ServiceSet serviceSet) throws ExportException {
        if (serviceSet == null) {
            throw new ExportException("Null serviceSet passed into exporter");
        }

        File startingPath = lastServiceSetExportPath;
        if (startingPath == null) {
            startingPath = new File(System.getProperty("user.home"));
        }

        File outputFile = getServiceDefExportPath(startingPath);

        if (outputFile != null) {
            exportServiceSetCJSIDL(serviceSet, outputFile);
        }
    }

    public static void exportServiceSetCJSIDL(com.u2d.generated.ServiceSet serviceSet, File outputFile) throws ExportException {
        lastServiceSetExportPath = outputFile.getParentFile();

        java.util.List<File> files = new ArrayList<File>();
        File tmpfolder = null;
        try {
            tmpfolder = org.jts.gui.importCJSIDL.Import.createTempDir();
        } catch (IOException ex) {
            Logger.getLogger(Export.class.getName()).log(Level.SEVERE, null, ex);
        }
        File tmpfile = null;

        RelationalList defs = serviceSet.getServiceDefs();
        com.u2d.app.Context.getInstance().getViewMechanism().message(
                "Exporting ServiceDefs from ServiceSet... ");

        java.util.List<Object> items = defs.getItems();
        java.util.List<Object> jsidlDefs = new ArrayList<Object>();

        for (Object def : items) {
            if (def instanceof com.u2d.generated.ServiceDef) {

                org.jts.jsidl.binding.ServiceDef sd = org.jts.gui.jmatterToJAXB.ServiceDef.convert((com.u2d.generated.ServiceDef) def);
                RemoveJSIDLPlus.removeJSIDLPlus(sd);
                jsidlDefs.add(sd);

                try {
                    tmpfile = serializeJAXB(sd, tmpfolder.getCanonicalPath());
                    files.add(tmpfile);
                } catch (IOException ex) {
                    Logger.getLogger(Export.class.getName()).log(Level.SEVERE, null, ex);
                }


            } else {
                String message = "Invalid object type found when processing serviceDefs: " + def.getClass().getName();
                System.out.println(message);
                throw new ExportException(message);
            }
        }
        // this could replace the following section of code, if the antlr lib conflicts can be resolved
//        try {
//
//            Conversion conv = new Conversion();
//            System.err.println(tmpfolder.getCanonicalPath());
//            System.err.println(outputFile.getCanonicalPath());
//
//            conv.convertFromJSIDL(tmpfolder.getCanonicalPath(), outputFile.getCanonicalPath());
//
//            com.u2d.app.Context.getInstance().getViewMechanism().message(
//                    "ServiceDef Export Complete!  ");
//        } catch (ConversionException e) {
//            System.out.println("SAXException: ");
//            e.printStackTrace();
//        } catch (IOException ex) {
//            String message = "Invalid path or file name when exporting a service def: " + outputFile;
//            System.out.println(message);
//            throw new ExportException(message, ex);
//        }


        try {
            System.out.println("Initiating Conversion process 'convertFromJSIDL'");
            String execStr = ("java " + classpath + " org.jts.eclipse.conversion.cjsidl.Conversion \"convertFromJSIDL\" \"" + tmpfolder.getCanonicalPath() + "\" \"" +  outputFile.getCanonicalPath() + "\"");
            // if this is Linux or Mac OS X, we can't have double quotes around the
            // parameters, and classpath uses : instead of ;
            if(!System.getProperty("os.name").startsWith("Windows")){
                execStr = execStr.replace("\"", "");
                execStr = execStr.replace(";", ":");
            }

            java.lang.Runtime rt = java.lang.Runtime.getRuntime();
            java.lang.Process p = rt.exec(execStr);
            StreamReader gerrors = new StreamReader( p.getErrorStream(), "ERROR");
            StreamReader goutput = new StreamReader(p.getInputStream(), "OUTPUT");
            gerrors.start();
            goutput.start();
            try {
                p.waitFor();
            } catch (InterruptedException ex) {
                Logger.getLogger(Export.class.getName()).log(Level.SEVERE, null, ex);
            }
            String errors = gerrors.getData();
            String log = goutput.getData();
            if(!errors.isEmpty()){
                Logger.getLogger(Export.class.getName()).log(Level.SEVERE, errors);
                JOptionPane.showMessageDialog(GUI.getFrame(), errors, "CJSIDL Export Error", JOptionPane.ERROR_MESSAGE);
            }

            System.out.println("Process exited with code = " + p.exitValue());
        } catch (IOException ex) {
            Logger.getLogger(Export.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static File getServiceDefExportPath(final File lastPath) {
        try {
            GUISupport.DialogDisplayer container = new GUISupport.DialogDisplayer() {

                public void setupDialog() {
                    PathSelectDialog newDialog = new PathSelectDialog(
                            GUI.getFrame(),
                            true,
                            lastPath,
                            "Export ServiceDef to JSIDL",
                            "Select an output file where the ServiceDef will be exported as JSIDL, and click Export.",
                            "Export");
                    newDialog.setFileSelectionMode(PathSelectDialog.DIRECTORIES_ONLY);
                    newDialog.setSaveMode(true);

                    setDialogInstance(newDialog);
                }

                public void showDialog() {
                    getDialogInstance().setVisible(true);
                }
            };

            GUISupport.runOnEDT(container);

            PathSelectDialog dialog = (PathSelectDialog) container.getDialogInstance();

            if (dialog.userSelectedPath()) {
                return dialog.getSelectedPath();
            } else {
                return null;
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error while displaying dialog to get service definition export path.", ex);
        }
    }
    private static File lastServiceDefExportPath = null;

    public static void exportServiceDefCJSIDL(com.u2d.generated.ServiceDef serviceDef) throws ExportException {
        if (serviceDef == null) {
            throw new ExportException("Null service definition passed to exporter");
        }

        File startingPath = lastServiceDefExportPath;
        if (startingPath == null) {
            startingPath = new File(System.getProperty("user.home"));
        }

        File outputFile = getServiceDefExportPath(startingPath);

        if (outputFile != null) {
            exportServiceDefCJSIDL(serviceDef, outputFile);
        }
    }

    public static void exportServiceDefCJSIDL(com.u2d.generated.ServiceDef serviceDef, File outputFile) {
        lastServiceDefExportPath = outputFile.getParentFile();

        com.u2d.app.Context.getInstance().getViewMechanism().message(
                "Exporting ServiceDef... ");

        org.jts.jsidl.binding.ServiceDef sd = org.jts.gui.jmatterToJAXB.ServiceDef.convert(serviceDef);
        RemoveJSIDLPlus.removeJSIDLPlus(sd);
        if (hasDependencies(sd)) {
            com.u2d.app.Context.getInstance().getViewMechanism().message(
                    "ServiceDef Conversion Failed!  Dependencies detected, but not included.\n This should be done using a service set. ");

            JOptionPane.showMessageDialog(GUI.getFrame(), "ServiceDef Conversion Failed!\n"
                    + "Dependencies detected, but not included.\n"
                    + "Try exporting a service set that includes all dependencies.");
            return;
        }
        try {
            java.util.List<File> files = new ArrayList<File>();
            File tmpfolder = org.jts.gui.importCJSIDL.Import.createTempDir();
            File tmpfile = null;
            try {
                tmpfile = serializeJAXB(sd, tmpfolder.getCanonicalPath());
                files.add(tmpfile);
            } catch (FileNotFoundException ex) {
                System.out.println("Failed to create a temp file used during conversion: " + tmpfile.getCanonicalPath());
            }


            // convertFromJSIDL tmpfile.getCanonicalPath() outputFile.getCanonicalPath()
// this could be done instead of the following try/catch, but an antlr lib version conflict gets in the way
//            Conversion conv = new Conversion();
//            conv.convertFromJSIDL(tmpfile.getCanonicalPath(), outputFile.getCanonicalPath());
            try {
                System.out.println("Initiating Conversion process 'convertFromJSIDL'");
                java.lang.Runtime rt = java.lang.Runtime.getRuntime();
                String execStr = ("java " + classpath + " org.jts.eclipse.conversion.cjsidl.Conversion \"convertFromJSIDL\" \"" + tmpfile.getCanonicalPath() + "\" \"" +  outputFile.getCanonicalPath() + "\"");
                // if this is Linux or Mac OS X, we can't have double quotes around the
                // parameters, and classpath uses : instead of ;
                if(!System.getProperty("os.name").startsWith("Windows")){
                    execStr = execStr.replace("\"", "");
                    execStr = execStr.replace(";", ":");
                }
                java.lang.Process p = rt.exec(execStr);
                StreamReader gerrors = new StreamReader( p.getErrorStream(), "ERROR");
                StreamReader goutput = new StreamReader(p.getInputStream(), "OUTPUT");
                gerrors.start();
                goutput.start();
                try {
                    p.waitFor();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Export.class.getName()).log(Level.SEVERE, null, ex);
                }

                String errors = gerrors.getData();
                String log = goutput.getData();
                
                System.out.println("Process exited with code = " + p.exitValue());
                if(!errors.isEmpty()){
                    Logger.getLogger(Export.class.getName()).log(Level.SEVERE, errors);
                    JOptionPane.showMessageDialog(GUI.getFrame(), errors, "CJSIDL Export Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (IOException ex) {
                Logger.getLogger(Export.class.getName()).log(Level.SEVERE, null, ex);
            }


            com.u2d.app.Context.getInstance().getViewMechanism().message(
                    "ServiceDef Export Complete!  ");
        } catch (IOException ex) {
            String message = "Invalid path or file name when exporting a service def: " + outputFile;
            System.out.println(message);
            throw new ExportException(message, ex);
        }
    }

    private static File getConstantSetExportPath(final File lastPath) {
        try {
            GUISupport.DialogDisplayer container = new GUISupport.DialogDisplayer() {

                public void setupDialog() {
                    PathSelectDialog newDialog = new PathSelectDialog(
                            GUI.getFrame(), true, lastPath, "Export ConstantSet to JSIDL",
                            "Select an output file where the ConstantSet will be exported as JSIDL and click Export. "
                            + "and click Export.", "Export");
                    newDialog.setFileSelectionMode(PathSelectDialog.FILES_ONLY);
                    newDialog.setSaveMode(true);

                    setDialogInstance(newDialog);
                }

                public void showDialog() {
                    getDialogInstance().setVisible(true);
                }
            };

            GUISupport.runOnEDT(container);

            PathSelectDialog dialog = (PathSelectDialog) container.getDialogInstance();

            if (dialog.userSelectedPath()) {
                return dialog.getSelectedPath();
            } else {
                return null;
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error while displaying dialog to get constant set export path.", ex);
        }
    }
    private static File lastConstantSetExportPath = null;

    public static void exportConstantSetCJSIDL(com.u2d.generated.ConstantSet constantSet) throws ExportException {
//        if (constantSet == null) {
//            throw new ExportException("Null constant set passed to exporter");
//        }
//
//        File startingPath = lastConstantSetExportPath;
//        if (startingPath == null) {
//            startingPath = new File(System.getProperty("user.home"));
//        }
//
//        File outputFile = getConstantSetExportPath(startingPath);
//
//        if (outputFile != null) {
//            lastConstantSetExportPath = outputFile.getParentFile();
//
//            org.jts.jsidl.binding.DeclaredConstSet jxConstantSet = org.jts.gui.jmatterToJAXB.ConstantSet.convert(constantSet);
//            try {
//                java.io.FileWriter fw = new FileWriter(outputFile);
//                javax.xml.bind.JAXB.marshal(jxConstantSet, fw);
//            } catch (IOException ex) {
//                String message = "Invalid path or file name when exporting a constant set: " + outputFile.getAbsolutePath();
//                System.out.println(message);
//                throw new ExportException(message, ex);
//            }
//        }
    }

    /**
     * Attempts to marshal all JAXB bindings for resources that have been loaded
     * @throws IOException
     */
    public static File serializeJAXB(org.jts.jsidl.binding.ServiceDef def, String outputpath) throws FileNotFoundException {
        String id = def.getId();
        id = id.replace(":", "_");
        File tmpfile = new File(outputpath + "/" + id + ".xml");
        if (tmpfile.isDirectory() && !tmpfile.exists()) {
            boolean success = tmpfile.mkdirs();
            if (!success) {
                throw new FileNotFoundException("Failed to make new directory. Path is either bad or this application"
                        + "doesn't have the permissions necessary.  Check your path and try again.");
            }
        }

        java.io.FileWriter fw;
        try {
            fw = new java.io.FileWriter(tmpfile);
            javax.xml.bind.JAXB.marshal(def, fw);
            
            // close the file to release control to user
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return tmpfile;
    }

    private static boolean hasDependencies(org.jts.jsidl.binding.ServiceDef sd) {
        boolean dependencies = false;

        if (sd.getReferences() != null) {
            if (sd.getReferences().getInheritsFrom() != null) {
                dependencies = true;
            }
            if (sd.getReferences().getClientOf().size() > 0) {
                dependencies = true;
            }
        }
        return dependencies;
    }



}
 
