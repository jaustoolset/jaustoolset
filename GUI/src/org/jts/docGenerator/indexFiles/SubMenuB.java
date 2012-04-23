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
import java.util.Collections;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.io.FilenameUtils;
import org.jts.docGenerator.AllDocGeneratorCommon;

import org.jts.docGenerator.indexFiles.binding.*;
import org.jts.docGenerator.util.URLHelpers;

/** Builds SubMenu.xml files for each subgroup. These files contain the 
 * {name,url,description,target} set for each file contained within each subgroup
 * directory.
 *
 * @author Arfath Pasha $Date: 2005/06/13 16:38:56 $ $Revision: 1.1.1.1 $
 *
 */
public class SubMenuB {

    private org.jts.docGenerator.indexFiles.binding.ObjectFactory factory = null;
    Unmarshaller unmarshaller = null;
    private File destDir = null;
    private static final String MAIN_TARGET_FRAME = "MainFrame";

    public SubMenuB() {
        try {
            JAXBContext jc = JAXBContext.newInstance("org.jts.jsidl.binding");
            unmarshaller = jc.createUnmarshaller();
        } catch (JAXBException je) {
            je.printStackTrace();
        }

        factory = new org.jts.docGenerator.indexFiles.binding.ObjectFactory();
    }

    public void build(File destDir, File curDir) {
        this.destDir = destDir;

        if (curDir == null) {
            throw new NullPointerException(" null path found. Cannot create subMenuB.xml");
        }
        if (!curDir.isDirectory()) {
            throw new IllegalArgumentException(curDir + " is not a directory. Cannot create subMenuB.xml");
        }

        try {
            Index mdi = factory.createIndex();
            mdi.setName("Sub Menu B");
            String cls = curDir.toString();

            int loc = cls.lastIndexOf(File.separator) + 1;
            if (cls.equals(destDir.toString())) {
                mdi.setDescription("All");
            } else if (loc > 0 && loc < cls.length()) {
                cls = cls.substring(loc);
                mdi.setDescription(cls);
            } else {
                mdi.setDescription("");
            }
            List mdList = mdi.getItem();

            try {
                populateMessageDefinitionList(mdList, destDir, curDir);
            } catch (JAXBException je) {
                je.printStackTrace();
            }

            JAXBContext jc = JAXBContext.newInstance("org.jts.docGenerator.indexFiles.binding");
            Marshaller m = jc.createMarshaller();

            // create index xml file to write to
            FileOutputStream fos = new FileOutputStream(curDir + File.separator + "subMenuB.xml");

            // marshal JAXB index object to xml file
            m.marshal(mdi, fos);

            fos.flush();

        } catch (Exception je) {
            je.printStackTrace();
        }
    }

    private List populateMessageDefinitionList(List mdList, File destDir, 
            File curDir) throws JAXBException {
        ArrayList<File> toAdd = new ArrayList<File>();
        ArrayList<String> mDefNames = new ArrayList<String>();
        boolean nameAlreadyExists = false;

        if (mdList == null || curDir == null) {
            return null;
        }

        // add subMenuB links
        File[] file = curDir.listFiles();
        for (int ii = 0; ii < file.length; ii++) {
            if (file[ii].isDirectory()) {
                File[] childFiles = file[ii].listFiles();

                for (int jj = 0; jj < childFiles.length; jj++) {
                    // if in the messages directory, add an item for each message def file
                    // means path is a service definition directory
                    if (file[ii].getName().endsWith("messages")) {
                        for (int mm = 0; mm < mDefNames.size(); mm++) {
                            String name = mDefNames.get(mm);

                            if (name.equalsIgnoreCase(childFiles[jj].getName())) {
                                nameAlreadyExists = true;
                                break;
                            }
                        }

                        if (!nameAlreadyExists) {
                            toAdd.add(childFiles[jj]);

                            mDefNames.add(childFiles[jj].getName());
                        }

                        nameAlreadyExists = false;
                    } else if (childFiles[jj].isDirectory()) {
                        File[] grandChildFiles = childFiles[jj].listFiles();
                        for (int kk = 0; kk < grandChildFiles.length; kk++) {
                            // if in the messages directory, add an item for each message def file
                            // means path is a service set directory
                            if (childFiles[jj].getName().endsWith("messages")) {
                                for (int nn = 0; nn < mDefNames.size(); nn++) {
                                    String name = mDefNames.get(nn);

                                    if (name.equalsIgnoreCase(grandChildFiles[kk].getName())) {
                                        nameAlreadyExists = true;
                                        break;
                                    }
                                }

                                if (!nameAlreadyExists) {
                                    toAdd.add(grandChildFiles[kk]);

                                    mDefNames.add(grandChildFiles[kk].getName());
                                }

                                nameAlreadyExists = false;
                            } else if (grandChildFiles[kk].isDirectory()) {
                                File[] greatGrandChildFiles = grandChildFiles[kk].listFiles();
                                for (int ll = 0; ll < greatGrandChildFiles.length; ll++) {
                                    // if in the messages directory, add an item for each message def file
                                    // means path is output directory
                                    if (grandChildFiles[kk].getName().endsWith("messages")) {
                                        for (int pp = 0; pp < mDefNames.size(); pp++) {
                                            String name = mDefNames.get(pp);

                                            if (name.equalsIgnoreCase(greatGrandChildFiles[ll].getName())) {
                                                nameAlreadyExists = true;
                                                break;
                                            }
                                        }

                                        if (!nameAlreadyExists) {
                                            toAdd.add(greatGrandChildFiles[ll]);

                                            mDefNames.add(greatGrandChildFiles[ll].getName());
                                        }

                                        nameAlreadyExists = false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // sort file list by comparing their filenames.
        Collections.sort(toAdd, AllDocGeneratorCommon.FilenameFileComparator.INSTANCE);

        for (File newItemFile : toAdd) {
            Item item = factory.createItem();
            String filePath = newItemFile.toString();

            if (!FilenameUtils.getExtension(filePath).equals("xml")) {
                // the algorithm above picks up files that are not .xml files.
                continue;
            }

            String name = FilenameUtils.getBaseName(filePath);
            item.setName(name);
            
            // exchange the .xml file's path with a path to an html file with same name/path.
            String xmlPath = filePath.substring(0, filePath.lastIndexOf(".xml")) + ".html";

            String url = URLHelpers.getRelativeURL(xmlPath, destDir);
            item.setUrl(url);
            item.setDescription(name);
            item.setTarget(MAIN_TARGET_FRAME);
            mdList.add(item);
        }

        return mdList;
    }
}
