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
package org.jts.gui.exportJSIDL;

import com.u2d.list.RelationalList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.jts.gui.GUI;
import org.jts.gui.mxGraphtoJAXB.parser.ParseException;
import org.jts.gui.util.GUISupport;
import org.jts.gui.util.PathSelectDialog;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Provides utility methods for exporting JMatter-format JAUS components as JSIDL XML files.
 */
public class Export {

    private static File getServiceSetExportPath(final File lastPath) {

        try {
            GUISupport.DialogDisplayer container = new GUISupport.DialogDisplayer() {

                public void setupDialog() {
                    PathSelectDialog newDialog = new PathSelectDialog(
                            GUI.getFrame(), true, lastPath, "Export ServiceSet to JSIDL",
                            "Select an output directory where the ServiceSet will be exported as JSIDL files "
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

    public static void exportServiceSet(com.u2d.generated.ServiceSet serviceSet) throws ExportException {
        if (serviceSet == null) {
            throw new ExportException("Null serviceSet passed into exporter");
        }

        File startingPath = lastServiceSetExportPath;
        if (startingPath == null) {
            startingPath = new File(System.getProperty("user.home"));
        }

        File outputFile = getServiceSetExportPath(startingPath);

        if (outputFile != null) {
            exportServiceSet(serviceSet, outputFile);
        }
    }

    public static void exportServiceSet(com.u2d.generated.ServiceSet serviceSet, File outputFile) {
        lastServiceSetExportPath = outputFile.getParentFile();

        RelationalList defs = serviceSet.getServiceDefs();
        com.u2d.app.Context.getInstance().getViewMechanism().message(
                "Exporting ServiceDefs from ServiceSet... ");

        java.util.List<Object> items = defs.getItems();

        for (Object def : items) {
            if (def instanceof com.u2d.generated.ServiceDef) {

                org.jts.jsidl.binding.ServiceDef sd = org.jts.gui.jmatterToJAXB.ServiceDef.convert((com.u2d.generated.ServiceDef) def);
                RemoveJSIDLPlus.removeJSIDLPlus(sd);
 
                try {
                   serializeJAXB(sd, outputFile.getCanonicalPath());
                 } catch (IOException ex) {
                    Logger.getLogger(Export.class.getName()).log(Level.SEVERE, null, ex);
                }


            } else {
                String message = "Invalid object type found when processing serviceDefs: " + def.getClass().getName();
                com.u2d.app.Context.getInstance().getViewMechanism().message(message);
                JOptionPane.showMessageDialog(GUI.getFrame(), "ServiceSet Export Failed!\n"
                    +  message);

            }
        }
         com.u2d.app.Context.getInstance().getViewMechanism().message("Export Complete! ");

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
            throw new RuntimeException("Error while displaying dialog to get service definition export path.", ex);
        }
    }

    private static File lastServiceDefExportPath = null;

    public static void exportServiceDefJSIDLPlus(com.u2d.generated.ServiceDef serviceDef) throws ExportException
    {
        if(serviceDef == null)
        {
            throw new ExportException("Null service definition passed to exporter");
        }

        File startingPath = lastServiceDefExportPath;
        if(startingPath == null)
        {
            startingPath = new File(System.getProperty("user.home"));
        }

        File outputFile = getServiceDefExportPath(startingPath);

        if (outputFile != null)
        {
        	exportServiceDefJSIDLPlus(serviceDef, outputFile);
        }
    }

    public static void exportServiceDefJSIDLPlus(com.u2d.generated.ServiceDef serviceDef, File outputFile)
    {
        lastServiceDefExportPath = outputFile.getParentFile();

        com.u2d.app.Context.getInstance().getViewMechanism().message(
                    "Exporting ServiceDef... ");

        org.jts.jsidl.binding.ServiceDef sd = org.jts.gui.jmatterToJAXB.ServiceDef.convert(serviceDef);
        
        org.jts.jsidl.binding.ProtocolBehavior pb = sd.getProtocolBehavior();
        
        org.jts.gui.JAXBtomxGraph.jxProtocolBehavior jx = new org.jts.gui.JAXBtomxGraph.jxProtocolBehavior( pb );
        
        org.w3c.dom.Document document = jx.convert();

        com.mxgraph.io.mxCodec codec = new com.mxgraph.io.mxCodec( document );
        com.mxgraph.model.mxGraphModel model = new com.mxgraph.model.mxGraphModel();
        
        codec.decode( document.getDocumentElement(), model );
        
        org.w3c.dom.Node node = codec.encode( model );
        
		Node root = node.getFirstChild();
		NodeList cells = root.getChildNodes();
		
        org.jts.gui.mxGraphtoJAXB.mxProtocolBehavior mxpb = new org.jts.gui.mxGraphtoJAXB.mxProtocolBehavior( cells );
        
        org.jts.jsidl.binding.ProtocolBehavior jxpb = null;

        try
        {
        	jxpb = mxpb.convert();
        }
        catch( Exception pe )
        {
        	pe.printStackTrace();
        }
        
        sd.setProtocolBehavior( jxpb );
        
        try
        {
            java.io.FileWriter fw = new FileWriter(outputFile);
            javax.xml.bind.JAXB.marshal(sd, fw);
            
            // close the file to release control to user
            fw.close();

            com.u2d.app.Context.getInstance().getViewMechanism().message(
                    "ServiceDef Export Complete!  ");
        }
        catch (IOException ex)
        {
            String message = "Invalid path or file name when exporting a service def: " + outputFile;
            System.out.println(message);
            throw new ExportException(message, ex);
        }
    }

    public static void exportServiceDefJSIDL(com.u2d.generated.ServiceDef serviceDef) throws ExportException
    {
        if(serviceDef == null)
        {
            throw new ExportException("Null service definition passed to exporter");
        }

        File startingPath = lastServiceDefExportPath;
        if(startingPath == null)
        {
            startingPath = new File(System.getProperty("user.home"));
        }

        File outputFile = getServiceDefExportPath(startingPath);

        if(outputFile != null)
        {
            exportServiceDefJSIDL(serviceDef, outputFile);
        }
    }

    public static void exportServiceDefJSIDL(com.u2d.generated.ServiceDef serviceDef, File outputFile)
    {
        lastServiceDefExportPath = outputFile.getParentFile();

        com.u2d.app.Context.getInstance().getViewMechanism().message(
                    "Exporting ServiceDef... ");

        org.jts.jsidl.binding.ServiceDef sd = org.jts.gui.jmatterToJAXB.ServiceDef.convert(serviceDef);
        try
        {
            java.io.FileWriter fw = new FileWriter(outputFile);
            JAXBContext jc = JAXBContext.newInstance( "org.jts.jsidl.binding" );

            Marshaller m = jc.createMarshaller();
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema;
            //Handle Eclipse plugin's handling of relative paths
            if(new File("plugins/org.jts.eclipse.data_1.0/resources").exists())
            {
                schema =  sf.newSchema(new File("plugins/org.jts.eclipse.data_1.0/resources/schema/JSIDL_Plus/version_1_1.xsd"));
            }
            else
            {
                schema =  sf.newSchema(new File("resources/schema/JSIDL_Plus/version_1_1.xsd"));
            }

            m.setSchema(schema);
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // remove all jsidl plus elements from the definition
            RemoveJSIDLPlus.removeJSIDLPlus( sd );
            
            m.marshal( sd, fw );
            
            // close the file to release control to user
            fw.close();

            com.u2d.app.Context.getInstance().getViewMechanism().message(
                    "ServiceDef Export Complete!  ");
        }
        catch( SAXException e )
        {
        	System.out.println( "SAXException: " );
        	e.printStackTrace();
        }
        catch(JAXBException  e )
        {
        	System.out.println( "JAXBException: " );
        	e.printStackTrace();
        }
        catch(IOException ex)
        {
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

    public static void exportConstantSets(com.u2d.generated.ConstantSet constantSet) throws ExportException {
        if (constantSet == null) {
            throw new ExportException("Null constant set passed to exporter");
        }

        File startingPath = lastConstantSetExportPath;
        if (startingPath == null) {
            startingPath = new File(System.getProperty("user.home"));
        }

        File outputFile = getConstantSetExportPath(startingPath);

        if (outputFile != null) {
            lastConstantSetExportPath = outputFile.getParentFile();

            org.jts.jsidl.binding.DeclaredConstSet jxConstantSet = org.jts.gui.jmatterToJAXB.ConstantSet.convert(constantSet);
            try {
                java.io.FileWriter fw = new FileWriter(outputFile);
                javax.xml.bind.JAXB.marshal(jxConstantSet, fw);
            } catch (IOException ex) {
                String message = "Invalid path or file name when exporting a constant set: " + outputFile.getAbsolutePath();
                System.out.println(message);
                throw new ExportException(message, ex);
            }
        }
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
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return tmpfile;
    }

}
