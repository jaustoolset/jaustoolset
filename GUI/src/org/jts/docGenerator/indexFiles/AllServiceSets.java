/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2010, 2011 United States Government
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
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.jts.jsidl.binding.ServiceSet;

import java.util.List;
import java.util.Collections;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.io.FilenameUtils;
import org.jts.docGenerator.AllDocGeneratorCommon;
import org.jts.docGenerator.indexFiles.binding.Index;
import org.jts.docGenerator.indexFiles.binding.Item;
import org.jts.docGenerator.indexFiles.binding.ObjectFactory;

import org.jts.docGenerator.util.IO;
import org.jts.docGenerator.util.URLHelpers;

/** Builds AllServiceSets.xml. This file contains the {name,url,description,target}
 * set for each file contained in the directory whole path is provided to the
 * build method.
 *
 * @author Eric Thorn $Date: 2008/09/17 16:38:56 $ $Revision: 1.1.1.1 $
 *
 */
public class AllServiceSets {

    private ObjectFactory factory = null;
    Unmarshaller unmarshaller = null;
    private static final String TARGET_FRAME = "MainFrame";
    private File destDir = null;

    public AllServiceSets(File destDir) {
        this.destDir = destDir;

        try {
            JAXBContext jc = JAXBContext.newInstance("org.jts.jsidl.binding");
            unmarshaller = jc.createUnmarshaller();
        } catch (JAXBException je) {
            je.printStackTrace();
        }

        factory = new ObjectFactory();
    }

    public void build(List serviceSetList, File outputDir) {
        if (outputDir == null) {
            throw new NullPointerException(" null path found. Cannot create allServiceSets.xml");
        }
        if (!outputDir.isDirectory()) {
            throw new IllegalArgumentException(outputDir + " is not a directory. Cannot create allServiceSets.xml");
        }
        if (!outputDir.toString().equals(destDir.toString())) {
            return;
        }

        try {
            Index ssi = factory.createIndex();
            ssi.setName("All Service Sets");
            ssi.setDescription("");

            List<Item> ssList = ssi.getItem();

            populateServiceSetList(serviceSetList, ssList, outputDir);

            JAXBContext jc = JAXBContext.newInstance("org.jts.docGenerator.indexFiles.binding");
            Marshaller marshaller = jc.createMarshaller();

            // create index xml file to write to
            FileOutputStream fos = new FileOutputStream(outputDir + File.separator + "allServiceSets.xml");

            // marshal JAXB index object to xml file
            marshaller.marshal(ssi, fos);

            fos.flush();

        } catch (Exception je) {
            je.printStackTrace();
        }
    }

    /**
     * Populate a list of links to service sets.
     * @param serviceSetList
     * @param ssetLinkList
     * @param outputDir
     * @return
     */
    private List populateServiceSetList(List<ServiceSet> serviceSetList, List<Item> ssetLinkList, File outputDir) {
        if (ssetLinkList == null || outputDir == null) {
            return null;
        }

        // each service set should have a corresponding directory under the output dir at this point,
        // with each s. set's dir containing an xml file inside named after the service set.
        // for example, service set called "FooSet" will have FooSet/FooSet.xml .
        ArrayList<File> ssetXMLFiles = new ArrayList<File>();


        for (ServiceSet sset : serviceSetList) {
            String ssetName = sset.getName();
            File correspondingXMLFile = new File(outputDir, ssetName + File.separatorChar + ssetName + ".xml");
            if (correspondingXMLFile.isFile()) {
                ssetXMLFiles.add(correspondingXMLFile);
            } else {
                throw new RuntimeException("The XML file for a service set was missing.");
            }
        }

        // sort file list by comparing their filenames.
        Collections.sort(ssetXMLFiles, AllDocGeneratorCommon.FilenameFileComparator.INSTANCE);

        StringBuilder urlBuilder = new StringBuilder();

        // populate service set list from file list
        for (File file : ssetXMLFiles) {
            try {
                InputStream is = IO.getInputStream(file);

                if (is != null) {
                    unmarshaller.unmarshal(is);
                    is.close();
                }

                // make sure it's an XML file.
                String filePath = file.toString();
                if (!filePath.endsWith(".xml")) {
                    throw new RuntimeException("Attempted to process a non-.xml file");
                }

                Item item = factory.createItem();

                // set link text to the name of the file, without extension.
                item.setName(FilenameUtils.getBaseName(filePath));

                // clears the stringbuilder
                urlBuilder.setLength(0);

                // put in part excluding '.xml' and append '.html'.
                urlBuilder.append(filePath.substring(0, filePath.lastIndexOf(".xml")));
                urlBuilder.append(".html");

                String url = URLHelpers.getRelativeURL(urlBuilder, outputDir);
                item.setUrl(url);

                // get the actual description of the service set and set it as the service set item's description
                for (int jj = 0; jj < serviceSetList.size(); jj++) {
                    ServiceSet sSet = (ServiceSet) serviceSetList.get(jj);

                    if (sSet.getName().equalsIgnoreCase(item.getName())) {
                        item.setDescription(sSet.getDescription().getContent());
                        break;
                    }
                }

                item.setTarget(TARGET_FRAME);
                ssetLinkList.add(item);

            } catch (JAXBException je) {
                je.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

        return ssetLinkList;
    }
}
