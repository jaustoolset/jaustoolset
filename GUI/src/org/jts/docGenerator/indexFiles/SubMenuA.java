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
package org.jts.docGenerator.indexFiles;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.io.FilenameUtils;

import org.jts.docGenerator.indexFiles.binding.*;
import org.jts.docGenerator.util.URLHelpers;

/** Builds SubMenu.xml files for each subgroup. These files contain the 
 * {name,url,description,target} set for each file contained within each subgroup
 * directory.
 *
 * @author Arfath Pasha $Date: 2005/06/13 16:38:56 $ $Revision: 1.1.1.1 $
 *
 */
public class SubMenuA {

    private org.jts.docGenerator.indexFiles.binding.ObjectFactory factory = null;
    Unmarshaller unmarshaller = null;
    private File destDir = null;
    private static final String MAIN_TARGET_FRAME = "MainFrame";

    public SubMenuA() {
        try {
            JAXBContext jc = JAXBContext.newInstance("org.jts.jsidl.binding");
            unmarshaller = jc.createUnmarshaller();
        } catch (JAXBException je) {
            je.printStackTrace();
        }

        factory = new org.jts.docGenerator.indexFiles.binding.ObjectFactory();
    }

    public void build(File destDir, File path) {
        this.destDir = destDir;

        if (path == null) {
            throw new NullPointerException(" null path found. Cannot create subMenuA.xml");
        }
        if (!path.isDirectory()) {
            throw new IllegalArgumentException(path + " is not a directory. Cannot create subMenuA.xml");
        }

        try {
            Index sdi = factory.createIndex();
            sdi.setName("Sub Menu A");
            String cls = path.toString();

            int loc = cls.lastIndexOf(File.separator) + 1;
            if (cls.equals(destDir.toString())) {
                sdi.setDescription("All");
            } else if (loc > 0 && loc < cls.length()) {
                cls = cls.substring(loc);
                sdi.setDescription(cls);
            } else {
                sdi.setDescription("");
            }
            List sdList = sdi.getItem();

            try {
                populateServiceDefinitionList(sdList, path);
            } catch (JAXBException je) {
                je.printStackTrace();
            }

            JAXBContext jc = JAXBContext.newInstance("org.jts.docGenerator.indexFiles.binding");
            Marshaller m = jc.createMarshaller();

            // create index xml file to write to
            FileOutputStream fos = new FileOutputStream(path + File.separator + "subMenuA.xml");

            // marshal JAXB index object to xml file
            m.marshal(sdi, fos);

            fos.flush();

        } catch (Exception je) {
            je.printStackTrace();
        }
    }

    private List populateServiceDefinitionList(List sdList, File path) throws JAXBException {
        List<String> sDefNames = new ArrayList<String>();
        boolean nameAlreadyExists = false;

        if (sdList == null || path == null) {
            return null;
        }

        // add submenu links
        File[] file = path.listFiles();
        for (int ii = 0; ii < file.length; ii++) {
            if (file[ii].isDirectory()) {
                // get files in current directory
                File[] childFiles = file[ii].listFiles();

                for (int jj = 0; jj < childFiles.length; jj++) {
                    // if creating subMenuA for output directory, look in each service set directory
                    if (path.getName().endsWith(destDir.getName())) {
                        // current file is a service set directory folder
                        if (childFiles[jj].isDirectory()) {
                            // get files in current service set folder
                            File[] grandChildFiles = childFiles[jj].listFiles();

                            // search through files for service def xml files
                            for (int kk = 0; kk < grandChildFiles.length; kk++) {
                                // if file is a service def xml file
                                if (grandChildFiles[kk].getName().endsWith(childFiles[jj].getName() + ".xml")) {
                                    // check if item for service def has already been added
                                    // no duplicates in menu
                                    for (int ll = 0; ll < sDefNames.size(); ll++) {
                                        String name = sDefNames.get(ll);

                                        if (name.equalsIgnoreCase(childFiles[jj].getName())) {
                                            nameAlreadyExists = true;
                                            break;
                                        }
                                    }

                                    if (!nameAlreadyExists) {
                                        Item item = factory.createItem();
                                        String filePath = grandChildFiles[kk].toString();

                                        if (!FilenameUtils.getExtension(filePath).equals("xml")) {
                                            throw new RuntimeException("Attempted to process a non-.xml file");
                                        }

                                        String name = FilenameUtils.getBaseName(childFiles[jj].toString());
                                        item.setName(name);

                                        // exchange the .xml file's path with a path to an html file with same name/path.
                                        String xmlPath = filePath.substring(0, filePath.lastIndexOf(".xml")) + ".html";

                                        String url = URLHelpers.getRelativeURL(xmlPath, destDir);
                                        item.setUrl(url);
                                        item.setDescription(name);
                                        item.setTarget(MAIN_TARGET_FRAME);
                                        sdList.add(item);

                                        sDefNames.add(childFiles[jj].getName());
                                    }
                                    nameAlreadyExists = false;
                                }
                            }
                        }
                    } // if creating subMenuA for a service set, look for all service def xml files
                    else if (childFiles[jj].getName().endsWith(file[ii].getName() + ".xml")) {
                        // for each service def xml file, check if item for service def has already been added
                        // no duplicates in subMenu
                        for (int mm = 0; mm < sDefNames.size(); mm++) {
                            String name = sDefNames.get(mm);

                            if (name.equalsIgnoreCase(file[ii].getName())) {
                                nameAlreadyExists = true;
                                break;
                            }
                        }

                        if (!nameAlreadyExists) {
                            Item item = factory.createItem();
                            String filePath = childFiles[jj].toString();

                            if (!FilenameUtils.getExtension(filePath).equals("xml")) {
                                throw new RuntimeException("Attempted to process a non-.xml file");
                            }

                            String name = FilenameUtils.getBaseName(file[ii].toString());
                            item.setName(name);

                            // exchange the .xml file's path with a path to an html file with same name/path.
                            String xmlPath = filePath.substring(0, filePath.lastIndexOf(".xml")) + ".html";

                            String url = URLHelpers.getRelativeURL(xmlPath, destDir);
                            item.setUrl(url);
                            item.setDescription(name);
                            item.setTarget(MAIN_TARGET_FRAME);
                            sdList.add(item);

                            sDefNames.add(file[ii].getName());
                        }
                        nameAlreadyExists = false;
                    }
                }
            }
        }

        return sdList;
    }
}
