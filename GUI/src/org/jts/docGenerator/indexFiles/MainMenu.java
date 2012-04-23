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
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;

import org.jts.docGenerator.indexFiles.binding.*;
import org.jts.docGenerator.util.URLHelpers;

/** Builds MainMenu.xml. This file contains the {name,url,description,target}
 * set for each file contained in the directory whole path is provided to the
 * build method.
 *
 * @author Arfath Pasha $Date: 2005/06/13 16:38:56 $ $Revision: 1.1.1.1 $
 *
 */
public class MainMenu {

    private org.jts.docGenerator.indexFiles.binding.ObjectFactory factory = null;
    Unmarshaller unmarshaller = null;
    private File destDir = null;
    private static final String OVERVIEW_TARGET_FRAME = "MainFrame";
    private static final String SERVICE_SET_TARGET_FRAME = "MainFrame";
    private static final String SERVICE_DEFINITION_TARGET_FRAME = "MainFrame";
    private static final String MESSAGE_DEFINITION_TARGET_FRAME = "MainFrame";

    public MainMenu() {
        try {
            JAXBContext jc = JAXBContext.newInstance("org.jts.jsidl.binding");
            unmarshaller = jc.createUnmarshaller();
        } catch (JAXBException je) {
            je.printStackTrace();
        }

        factory = new org.jts.docGenerator.indexFiles.binding.ObjectFactory();
    }

    public void build(File path) {
        if (path == null) {
            throw new NullPointerException(" null path found. Cannot create mainMenu.xml");
        }
        if (!path.isDirectory()) {
            throw new IllegalArgumentException(path + " is not a directory. Cannot create mainMenu.xml");
        }

        destDir = path;

        //System.out.println("Generating mainMenu.xml for "+path+" ...");

        try {
            Index index = factory.createIndex();
            index.setName("Main Menu");
            index.setDescription("");
            List ssList = index.getItem();

            try {
                populateMainMenu(ssList, path);
            } catch (JAXBException je) {
                je.printStackTrace();
            }

            JAXBContext jc = JAXBContext.newInstance("org.jts.docGenerator.indexFiles.binding");
            Marshaller m = jc.createMarshaller();

            // create index xml file to write to
            FileOutputStream fos = new FileOutputStream(path + File.separator + "mainMenu.xml");

            // marshal JAXB index object to xml file
            m.marshal(index, fos);

            fos.flush();

        } catch (Exception je) {
            je.printStackTrace();
        }
    }

    private List populateMainMenu(List ssList, File path) throws JAXBException {
        if (ssList == null || path == null) {
            return null;
        }


        // add a link for overview-summary.html
        Item item = factory.createItem();
        item.setName("Overview");
        item.setUrl("overview-summary.html");
        item.setDescription("Overview of Jaus Service Set");
        item.setTarget(OVERVIEW_TARGET_FRAME);
        ssList.add(item);

        // add a link to allServiceSets.html
        item = factory.createItem();
        item.setName("All Service Sets");
        item.setUrl("allServiceSets.html");
        item.setDescription("All Service Sets");
        item.setTarget(SERVICE_SET_TARGET_FRAME);
        ssList.add(item);

        // add a link to allServiceDefinitions.html
        item = factory.createItem();
        item.setName("All Service Definitions");
        item.setUrl("allServiceDefinitions.html");
        item.setDescription("All Service Definitions");
        item.setTarget(SERVICE_DEFINITION_TARGET_FRAME);
        ssList.add(item);

        // add a link to allMessageDefinitions.html
        item = factory.createItem();
        item.setName("All Message Definitions");
        item.setUrl("allMessageDefinitions.html");
        item.setDescription("All Message Definitions");
        item.setTarget(MESSAGE_DEFINITION_TARGET_FRAME);
        ssList.add(item);

        // add mainMenu links
        File[] file = path.listFiles((FileFilter) FileFilterUtils.notFileFilter(
                FileFilterUtils.nameFileFilter("images")));
        for (int ii = 0; ii < file.length; ii++) {
            if (file[ii].isDirectory()) {
                item = factory.createItem();
                String name = FilenameUtils.getBaseName(file[ii].getPath());
                item.setName(name);

                String url = getUrl(file[ii].toString());

                item.setUrl(url);

                item.setDescription(name);

                item.setTarget(SERVICE_SET_TARGET_FRAME);

                ssList.add(item);
            }
        }

        return ssList;
    }

    private String getUrl(String url) {
        url = new File(
                new File(destDir.getPath()).toURI().relativize(
                new File(url).toURI()).getPath()).getPath();

        StringBuilder urlBuilder = new StringBuilder(url);

        // add filename + html extension
        String filename = urlBuilder.substring(urlBuilder.lastIndexOf(File.separator) + 1, urlBuilder.length()) + ".html";
        urlBuilder.append(File.separator);
        urlBuilder.append(filename);

        URLHelpers.urlize(urlBuilder);

        return urlBuilder.toString();
    }
}
