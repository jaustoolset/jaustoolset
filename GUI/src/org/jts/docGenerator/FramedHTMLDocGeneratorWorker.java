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

package org.jts.docGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.JAXBContext;

import org.jts.docGenerator.util.DirectoryScanner;

import org.jts.docGenerator.indexFiles.AllServiceSets;
import org.jts.docGenerator.indexFiles.AllServiceDefinitions;
import org.jts.docGenerator.indexFiles.AllMessageDefinitions;
import org.jts.docGenerator.indexFiles.MainMenu;
import org.jts.docGenerator.indexFiles.SubMenuA;
import org.jts.docGenerator.indexFiles.SubMenuB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.jts.jsidl.binding.InputSet;
import org.jts.jsidl.binding.MessageDef;
import org.jts.jsidl.binding.MessageSet;
import org.jts.jsidl.binding.OutputSet;
import org.jts.jsidl.binding.ServiceDef;
import org.jts.jsidl.binding.ServiceSet;



/**
 * Hides some details of the framed HTML document generation process to make FramedHTMLDocGenerator cleaner.
 */
final class FramedHTMLDocGeneratorWorker {

    protected final int PLACEHOLDER_IMAGE_WIDTH = 32;
    protected final File destDirectory;
    protected final File custDirectory;
    protected final File stylesheetsDir;
    protected final List<ServiceSet> serviceSetList;

    protected final File serviceSetFormatFile;
    protected final File serviceDefinitionFormatFile;
    protected final File messageDefinitionFormatFile;
    protected final File mainMenuFormatFile;
    protected final File allServiceSetsFormatFile;
    protected final File allServiceDefinitionsFormatFile;
    protected final File allMesageDefinitionsFormatFile;
    protected final File subMenuAFormatFile;
    protected final File subMenuBFormatFile;

    public FramedHTMLDocGeneratorWorker(File destDir, File custDir, List svcSetList) {
        destDirectory = destDir;
        custDirectory = custDir;
        serviceSetList = svcSetList;

        stylesheetsDir = new File(custDir, AllDocGeneratorCommon.FRAMED_HTML_STYLES_PATH);

        serviceSetFormatFile = new File(stylesheetsDir, "serviceSetFormat.xsl");
        serviceDefinitionFormatFile = new File(stylesheetsDir, "serviceDefFormat.xsl");
        messageDefinitionFormatFile = new File(stylesheetsDir, "messageDefFormat.xsl");
        mainMenuFormatFile = new File(stylesheetsDir, "mainMenuFormat.xsl");
        allServiceSetsFormatFile = new File(stylesheetsDir, "allServiceSetsFormat.xsl");
        allServiceDefinitionsFormatFile = new File(stylesheetsDir, "allServiceDefinitionsFormat.xsl");
        allMesageDefinitionsFormatFile = new File(stylesheetsDir, "allMessageDefinitionsFormat.xsl");
        subMenuAFormatFile = new File(stylesheetsDir, "subMenuAFormat.xsl");
        subMenuBFormatFile = new File(stylesheetsDir, "subMenuBFormat.xsl");

    }

    ////////////////////////////////////////////////////////////////////////////////

    /**
     * Generates a set of ServiceDef and MessageDef XML files in the destination path. The files are
     * organized in a list of directories and sub-directories that are named after the
     * service sets that contain the serviceDefs and the messageDefs respectively.
     *
     */
    public void generateXMLFiles() {
        // loop through for each service set in the list
        for (int ii = 0; ii < serviceSetList.size(); ii++) {
            ServiceSet sSet = (ServiceSet) serviceSetList.get(ii);
            List<ServiceDef> serviceDefinitionList = sSet.getServiceDef();

            // create a new directory for this service set in the destination directory
            File sSetDir = new File(destDirectory, sSet.getName());
            sSetDir.mkdir();

            // marshal service set object to xml file
            try {
                JAXBContext jc = JAXBContext.newInstance("org.jts.jsidl.binding");
                Marshaller marshaller = jc.createMarshaller();

                // create service definition xml file in its service set directory in the destination directory
                FileOutputStream fos = new FileOutputStream(sSetDir + File.separator + sSet.getName() + ".xml");

                marshaller.marshal(sSet, fos);

                fos.close();
            } catch (FileNotFoundException fnfe) {
                throw new RuntimeException("Output file not found while marshalling Service Sets "
                        + "to file.", fnfe);
            } catch (IOException ioe) {
                throw new RuntimeException("Problem writing to output file while marshalling "
                        + "Service Sets. ", ioe);
            } catch (JAXBException je) {
                throw new RuntimeException("JAXB error while marshalling Service Sets to file.", je);
            }

            // create xml file for each service definition in the service set
            for (int jj = 0; jj < serviceDefinitionList.size(); jj++) {
                ServiceDef sDef = (ServiceDef) serviceDefinitionList.get(jj);

                // create directory for service definition
                File sDefDir = new File(sSetDir, sDef.getName());
                sDefDir.mkdir();

                // marshal service def object to its own directory
                try {
                    JAXBContext jc = JAXBContext.newInstance("org.jts.jsidl.binding");
                    Marshaller marshaller = jc.createMarshaller();

                    // create service definition xml file in its service set directory in the destination directory
                    FileOutputStream fos = new FileOutputStream(sDefDir + File.separator + sDef.getName() + ".xml");

                    marshaller.marshal(sDef, fos);

                    fos.close();
                } catch (FileNotFoundException fnfe) {
                    throw new RuntimeException("Output file not found while marshalling Service Defs "
                            + "to file.", fnfe);
                } catch (IOException ioe) {
                    throw new RuntimeException("Problem writing to output file while marshalling "
                            + "Service Defs. ", ioe);
                } catch (JAXBException je) {
                    throw new RuntimeException("JAXB error while marshalling Service Defs to file.", je);
                }

                // create messages directory in service def directory
                File mDefDir = new File(sDefDir, "messages");
                mDefDir.mkdir();

                MessageSet mSet = sDef.getMessageSet();
                InputSet iSet = mSet.getInputSet();
                OutputSet oSet = mSet.getOutputSet();

                List iList = iSet.getMessageDefOrDeclaredMessageDef();

                // create message xml file for each message def in input set
                for (int kk = 0; kk < iList.size(); kk++) {
                    MessageDef mDef = (MessageDef) iList.get(kk);

                    try {
                        JAXBContext jc = JAXBContext.newInstance("org.jts.jsidl.binding");
                        Marshaller marshaller = jc.createMarshaller();

                        FileOutputStream fos = new FileOutputStream(mDefDir
                                + File.separator + mDef.getName() + ".xml");

                        marshaller.marshal(mDef, fos);

                        fos.close();
                    } catch (FileNotFoundException fnfe) {
                        throw new RuntimeException("Output file not found while marshalling Message Defs"
                                + "to file.", fnfe);
                    } catch (IOException ioe) {
                        throw new RuntimeException("Problem writing to output file while marshalling "
                                + "Message Defs. ", ioe);
                    } catch (JAXBException je) {
                        throw new RuntimeException("JAXB error while marshalling Message Defs to file.", je);
                    }
                }

                List oList = oSet.getMessageDefOrDeclaredMessageDef();

                // create message xml file for each message def in output set
                for (int ll = 0; ll < oList.size(); ll++) {
                    MessageDef mDef = (MessageDef) oList.get(ll);

                    try {
                        JAXBContext jc = JAXBContext.newInstance("org.jts.jsidl.binding");
                        Marshaller marshaller = jc.createMarshaller();

                        FileOutputStream fos = new FileOutputStream(mDefDir
                                + File.separator + mDef.getName() + ".xml");

                        marshaller.marshal(mDef, fos);

                        fos.close();
                    } catch (FileNotFoundException fnfe) {
                        throw new RuntimeException("Output file not found while marshalling Message Defs "
                                + "to file.", fnfe);
                    } catch (IOException ioe) {
                        throw new RuntimeException("Problem writing to output file while marshalling "
                                + "Message Defs. ", ioe);
                    } catch (JAXBException je) {
                        throw new RuntimeException("JAXB error while marshalling Message Defs to file.", je);
                    }
                }
            }
        }
    }

    /**
     * Generates a set of index XML files for the XML files present in the
     * destination path and places them in the relevant directories. The index file
     * generator uses binding objects generated from schemas/indexFormat.xsd
     */
    public void generateIndexFiles() {
        ArrayList<File> subMenuADirs = new ArrayList<File>();
        ArrayList<File> subMenuBDirs = new ArrayList<File>();

        // make a list of directories into which submenu files need to go

        // add destination directory for both submenusA&B
        subMenuADirs.add(destDirectory);
        subMenuBDirs.add(destDirectory);

        // get a list of directories
        DirectoryScanner scanner = new DirectoryScanner(destDirectory, null);
        List<File> dirs = scanner.getDirList();

        // avoid processing the images directory.
        // TODO: adapt DirectoryScanner to filter directories on name

        // add service set directories for both submenusA&B (service set directories are children of destDir)
        Iterator<File> dirsiter = dirs.iterator();

        while (dirsiter.hasNext()) {
            File dir = dirsiter.next();

            // do not process the images directory-contains static images.
            if (dir.getName().equals("images")) {
                dirsiter.remove(); // drops last item returned by next() from underlying container.
                continue;
            }

            String parentDirPath = dir.getParent();

            // add service set directories: direct childrent of dest directory.
            if (parentDirPath != null && destDirectory.toString().equals(parentDirPath)) {
                subMenuADirs.add(dir);
                subMenuBDirs.add(dir);
            }

            // add service definition directories: grandchildren of dest directory.
            if (parentDirPath != null) {
                File parentDir = dir.getParentFile();

                if (parentDir.getParent() != null && destDirectory.toString().equals(parentDir.getParent())) {
                    subMenuBDirs.add(dir);
                }
            }
        }

        // build allServiceSets.xml index files
        AllServiceSets ass = new AllServiceSets(destDirectory);
        for (int ii = 0; ii < dirs.size(); ii++) {
            ass.build(serviceSetList, (File) dirs.get(ii));
        }

        // build allServiceDefinitions.xml index files
        AllServiceDefinitions asd = new AllServiceDefinitions(destDirectory);
        for (int ii = 0; ii < dirs.size(); ii++) {
            asd.build(serviceSetList, (File) dirs.get(ii));
        }

        // build allMessageDefinitions.xml index files
        AllMessageDefinitions amd = new AllMessageDefinitions();
        for (int ii = 0; ii < dirs.size(); ii++) {
            amd.build(serviceSetList, (File) dirs.get(ii));
        }

        // build mainMenu.xml index files
        MainMenu mm = new MainMenu();
        mm.build(destDirectory);

        // build subMenuA.xml index files
        SubMenuA sma = new SubMenuA();
        for (int ii = 0; ii < subMenuADirs.size(); ii++) {
            sma.build(destDirectory, (File) subMenuADirs.get(ii));
        }

        // build subMenuB.xml index files
        SubMenuB smb = new SubMenuB();
        for (int ii = 0; ii < subMenuBDirs.size(); ii++) {
            smb.build(destDirectory, (File) subMenuBDirs.get(ii));
        }
    }

    /**
     * Generates HTML files for all XML files in the destination directories.
     * The HTML generator uses the styles present in the styles directory.
     *
     */
    public void generateHTMLFiles() {
        ArrayList<File> serviceSetFiles = new ArrayList<File>();
        ArrayList<File> serviceDefinitionFiles = new ArrayList<File>();
        ArrayList<File> messageDefinitionFiles = new ArrayList<File>();
        ArrayList<File> allServiceSetsFiles = new ArrayList<File>();
        ArrayList<File> allServiceDefinitionsFiles = new ArrayList<File>();
        ArrayList<File> allMessageDefinitionsFiles = new ArrayList<File>();
        ArrayList<File> mainMenuFiles = new ArrayList<File>();
        ArrayList<File> subMenuAFiles = new ArrayList<File>();
        ArrayList<File> subMenuBFiles = new ArrayList<File>();

        Collection<File> files = FileUtils.listFiles(destDirectory, FileFilterUtils.suffixFileFilter(".xml"),
                FileFilterUtils.notFileFilter(FileFilterUtils.nameFileFilter("images")));

        // search through output directory structure and seperate file types for conversion
        for (File file : files) {
            if (file.getName().endsWith("mainMenu.xml")) {
                mainMenuFiles.add(file);
            } else if (file.getName().endsWith("allServiceSets.xml")) {
                allServiceSetsFiles.add(file);
            } else if (file.getName().endsWith("allServiceDefinitions.xml")) {
                allServiceDefinitionsFiles.add(file);
            } else if (file.getName().endsWith("allMessageDefinitions.xml")) {
                allMessageDefinitionsFiles.add(file);
            } else if (file.getName().endsWith("subMenuA.xml")) {
                subMenuAFiles.add(file);
            } else if (file.getName().endsWith("subMenuB.xml")) {
                subMenuBFiles.add(file);
            } else if (isServiceSetFile(file)) {
                serviceSetFiles.add(file);
            } else if (!file.getParent().endsWith("messages")) {
                serviceDefinitionFiles.add(file);
            } else if (file.getParent().endsWith("messages")) {
                messageDefinitionFiles.add(file);
            }
        }

        // ====================================
        // Convert Service Set XML Files
        // ====================================

        // create html files for all Service Set xml files
        for (File file : serviceSetFiles) {
            // convert file
            Converter.convertXMLToHTML(file, serviceSetFormatFile);
        }

        // ====================================
        // Convert Service Definition XML Files
        // ====================================

        // create html files for all Service Def xml files
        for (File file : serviceDefinitionFiles) {
            // convert file
            Converter.convertXMLToHTML(file, serviceDefinitionFormatFile);
        }

        // ====================================
        // Convert Message Definition XML Files
        // ====================================

        // create html files for all Service Def xml files
        for (File file : messageDefinitionFiles) {
            // convert file
            Converter.convertXMLToHTML(file, messageDefinitionFormatFile);
        }

        // ===================
        // Convert Index Files
        // ===================

        // main menu index file
        for (File file : mainMenuFiles) {
            // convert file
            Converter.convertXMLToHTML(file, mainMenuFormatFile);
        }

        //all service sets index file
        for (File file : allServiceSetsFiles) {
            // convert file
            Converter.convertXMLToHTML(file, allServiceSetsFormatFile);
        }

        //all service def index file
        for (File file : allServiceDefinitionsFiles) {
            // convert file
            Converter.convertXMLToHTML(file, allServiceDefinitionsFormatFile);
        }

        //all service sets index file
        for (File file : allMessageDefinitionsFiles) {
            // convert file
            Converter.convertXMLToHTML(file, allMesageDefinitionsFormatFile);
        }

        for (File file : subMenuAFiles) {
            // convert file
            Converter.convertXMLToHTML(file, subMenuAFormatFile);
        }

        for (File file : subMenuBFiles) {
            // convert file
            Converter.convertXMLToHTML(file, subMenuBFormatFile);;
        }
    }

    private boolean isServiceSetFile(File file) {

        if (file.getParentFile() == null || file.getParentFile().getParentFile() == null) {
            return false;
        }

        if (!file.getParentFile().getParent().equals(destDirectory.toString())) {
            return false;
        }

        String parentName = file.getParentFile().getName();

        String fileName = file.getName();
        fileName = fileName.substring(0, fileName.length() - 4);  // remove .xml

        if (parentName.equals(fileName)) {
            return true;
        } else {
            return false;
        }

    }
}
