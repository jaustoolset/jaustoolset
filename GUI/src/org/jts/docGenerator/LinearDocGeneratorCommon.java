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

import com.icl.saxon.TransformerFactoryImpl;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.jdom.Element;
import org.jdom.CDATA;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.ProcessingInstruction;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;
import org.jts.docGenerator.util.IO;
import org.jts.jsidl.binding.ServiceDef;

/**
 * Contains common parts of the document generation process used by LinearHTMLDocGenerator and WordDocGenerator.
 * Designed to be independent of the file structure employed by user classes; user classes must specify what files
 * and directories they wish to use.
 *
 * See LinearHTMLDocGenerator.generate() for an example of usage.
 * @author idurkan
 */
public class LinearDocGeneratorCommon {
     /**
     * For pairing file paths with service names.
     */
    public class FileNamePair {
        public File file;
        public String svcname;

        public FileNamePair(File newFile, String newSvcname) {
            file = newFile;
            svcname = newSvcname;
        }
    }

    /**
     * for reuse of UTF-8 charset object.
     */
    protected Charset utf8Charset = null;

    /**
     *  for reuse of a Saxon 6.5.5 TransformerFactoryImpl.
     */
    protected TransformerFactoryImpl transformerFactory = null;

    /**
     * reuse regular expression pattern for finding & removing mxcells & related elements from appendix output
     */
    protected Pattern mxCellPattern = null;

    /**
     * Regex used for finding & removing mxcells & related elements from appendix output.
     */
    protected static final String MXCELL_REGEX_STRING =
            "(<(\\w+:)?pseudo_start_state.*?>.*?</(\\w+:)?pseudo_start_state>\\s)"
          + "|(<(\\w+:)?mxCell.*?>.*?</(\\w+:)?mxCell>\\s)";

    /**
     * standard namespace URI for Docbook 5 schema
     */
    protected static final String DOCBOOK_NAMESPACE_URI = "http://docbook.org/ns/docbook";
    protected static final String DOCBOOK_NAMESPACE_NAME = "dbk";

    /**
     * XPath string for finding appendix with XML ID "appendix.APPENDIX_A" in the template Docbook document.
     */
    protected static final String APPENDIX_PATH_STRING = "//dbk:appendix[@xml:id='appendix.APPENDIX_A']";

    /**
     * XPath string for finding the section with XML ID
     * "section.SERVICE_DEFINITIONS" in the template Docbook document.
     */
    protected static final String SERDEF_SECTION_PATH_STRING =
            "//dbk:section[@xml:id='section.SERVICE_DEFINITIONS']";


    /**
     * Constructor creates some common instances reused between the various functions.
     */
    public LinearDocGeneratorCommon() {
        // UTF-8 charset is always available according to Java spec
        utf8Charset = Charset.forName("UTF-8");
        transformerFactory = new TransformerFactoryImpl();

        // matches mxCell element and its contents, plus pseudo_start_state element and its contents.
        mxCellPattern = Pattern.compile(MXCELL_REGEX_STRING, Pattern.DOTALL);
    }

    /**
     * Marshal ServiceDefs into XML output files, returning list of (File, String) pairs containing output files
     * and the names of associated services.
     * @param serviceDefs ServiceDefs to make JSIDL XML for
     * @param outputDir Directory where XML should be placed.
     * @return List of the output JSIDL files, paired with their original service names.
     */
    public List<FileNamePair> createJSIDLFiles(List<ServiceDef> serviceDefs, File outputDir) {
        ArrayList<FileNamePair> jsidlFilesInfo = new ArrayList<FileNamePair>();

        try {
            for (ServiceDef inputService : serviceDefs) {
                // name JSIDL files based on service name with _jsidl.xml ending.
                File jsidlOutfile = new File(outputDir, inputService.getName() + "_jsidl.xml");
                jsidlFilesInfo.add(new FileNamePair(jsidlOutfile, inputService.getName()));

                OutputStreamWriter streamWriter =
                        new OutputStreamWriter(new FileOutputStream(jsidlOutfile), utf8Charset);

                javax.xml.bind.JAXB.marshal( inputService, streamWriter );
                streamWriter.close();
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error during JSIDL file generation:", ex);
        }

        return jsidlFilesInfo;
    }

    /**
     * Given the Files in serviceJSIDLFilesInfo, transform each JSIDL file into a Docbook file containing a
     * top-level <section>, then return a list of the files with Docbook sections.
     * @param serviceJSIDLFilesInfo List of JSIDL filenames, paired with names of original service definitions.
     * @param outputDir Directory where Docbook files should go
     * @param jsidlToDocbookStylesheet XSLT stylesheet for transforming JSIDL to Docbook
     * @return List of the Docbook files generated.
     */
    public List<File> createDocbookPerService(List<FileNamePair> serviceJSIDLFilesInfo, File outputDir,
            File jsidlToDocbookStylesheet) {
        ArrayList<File> docbookFiles = new ArrayList<File>();

        // generate paths to jsidl files and docbook output files.
        for (FileNamePair inputFilePair : serviceJSIDLFilesInfo) {

            // name docbook files based on service name but ending of "_db.xml"
            File docbookOutfile = new File(outputDir, inputFilePair.svcname + "_db.xml");
            docbookFiles.add(docbookOutfile);
        }

        // set up the transformer.
        Transformer saxonJsidlTransformer = null;
        try {
            Source tformSource = new StreamSource(jsidlToDocbookStylesheet);
            saxonJsidlTransformer = transformerFactory.newTransformer(tformSource);
        } catch (TransformerConfigurationException tcex) {
            throw new RuntimeException("Exception when setting up transformer for "
                    + "JSIDL-to-Docbook transformation.", tcex);
        }

        try {
            // transform each JSIDL XML file to a docbook XML with the appropriate name.
            for (int i = 0; i < serviceJSIDLFilesInfo.size(); ++i) {
                //StreamSource jsidlData = new StreamSource(serviceDefs.get(i));
                StreamSource jsidlData = new StreamSource(serviceJSIDLFilesInfo.get(i).file);
                StreamResult docbookOutput = new StreamResult(docbookFiles.get(i));

                saxonJsidlTransformer.transform(jsidlData, docbookOutput);
            }
        } catch (TransformerException te) {
            throw new RuntimeException("Error during transformation!", te);
        }

        return docbookFiles;
    }

    /**
     * Rationale for this function: The template Docbook document must contain certain sections where additional
     * sections will be added as children.  These certain sections must have a least a title and a single para element.
     * If a customizer does not want the certain sections to have any text in output except the title, this single
     * para must be left empty.  The single empty para will be removed and replaced with real contents.  On the other
     * hand, if the customizer has some preexisting content in the certain sections, additional sections will be
     * programmatically added following the preexisting content.
     *
     * Manipulates JDOM Element representing a section, parentSection, to add a
     * subsection element as a child.
     *
     * Assumes there will initially be a <title> and a <para> element in the parentSection,
     * when function is first called on a particular parentSection.  If the para element has no text,
     * it is removed, otherwise it is left alone.  The subsection is then added as a child of the parent.
     * @param parentSection Parent <section>.  Must be a <section> or runtime exception thrown.
     * @param newSubsection Section to make child of parentSection.
     */
    protected void addSubsectionToSection(Element parentSection, Element newSubsection) {
        if (parentSection.getName().equals("section")) {
            List<Element> sectionChildren = parentSection.getChildren();

            // must be at least two children
            if (sectionChildren.size() >= 2) {
                // is the dummy empty para still there?  if so remove and replace with subsection.
                // it should be the 1st child; title would be 0th.
                if (sectionChildren.get(1).getName().equals("para")
                        && sectionChildren.get(1).getText().equals("")) {
                    sectionChildren.remove(1);
                    sectionChildren.add(newSubsection);
                } else {
                    // no dummy, just add at end of section.
                    sectionChildren.add(newSubsection);
                }
            } else {
                throw new RuntimeException("Top-level section-addition area of template is malformed, can't add "
                        + "section for service definitions and/or type definitions");
            }
        } else {
            throw new RuntimeException("Caller attempted to add subsection to element that is not a section.");
        }
    }

    /**
     * Adds the Docbook documents (each containing a <section>) in subdocFiles to the JDOM Document representation of
     * the Docbook template document stored in templateDoc.  Assumes templateDoc has a <section> with
     * XML:id of section.SERVICE_DEFINITIONS
     * @param subdocFiles List of files Docbook containing <sections>
     * @param templateDoc a JDOM document containing a DOM representation of the Docbook template doc.
     */
    public void addServiceSubdocsToDoc(List<File> subdocFiles, Document templateDoc) {

        try {
            // must tell JDOM XPath instances about the docbook namespace.
            Namespace dbkNs = Namespace.getNamespace(DOCBOOK_NAMESPACE_NAME, DOCBOOK_NAMESPACE_URI);

            // the template docbook document must contain a <section> with XML:id of section.SERVICE_DEFINITIONS .
            XPath servDefSectionPath = XPath.newInstance(SERDEF_SECTION_PATH_STRING);
            servDefSectionPath.addNamespace(dbkNs);
            List<Object> servicesElemL = servDefSectionPath.selectNodes(templateDoc);

            // if there are zero or more than one of the section, the template document is invalid.
            if (servicesElemL.size() != 1) {
                throw new RuntimeException("Invalid template document - Multiple Service Definitions sections found, "
                        + "or no Service Definitions section found.");
            }

            if (!(servicesElemL.get(0) instanceof Element)) {
                throw new RuntimeException("Services section in template document was not an element!?");
            }

            Element servDefSection = (Element)servicesElemL.get(0);

            SAXBuilder builder = new SAXBuilder();

            // add service docbook data to the template document, under the service definitions section.
            for (int i = 0; i < subdocFiles.size(); ++i) {
                // load document with sax builder
                Document sectionDoc = builder.build(subdocFiles.get(i));

                // get the root element by detachment and add to end of article.
                Element sectionToAdd = sectionDoc.detachRootElement();

                addSubsectionToSection(servDefSection, sectionToAdd);
            }
        } catch (IOException ioex) {
            throw new RuntimeException("IOException while merging Docbook data into "
                    + "single Docbook file.", ioex);
        } catch (JDOMException jdex) {
            throw new RuntimeException("JDomException while merging Docbook data into "
                    + "single Docbook file.", jdex);
        }
    }

    /**
     * Outputs the JDOM DOM representation in combinedDoc to the file outputFile.
     * @param combinedDoc JDOM DOM representation of the combined Docbook document
     * @param outputFile File to write output.
     */
    public void outputCombinedDoc(Document combinedDoc, File outputFile) {
        try {
        // output to file.
        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        OutputStreamWriter streamWriter =
                new OutputStreamWriter(new FileOutputStream(outputFile), utf8Charset);
        xmlOutputter.output(combinedDoc, streamWriter);
        streamWriter.close();
        } catch (IOException ioex) {
            throw new RuntimeException("IOException when outputting Docbook document to file: ", ioex);
        }
    }

    /**
     * Given the combined Docbook document and a stylesheet for transforming it to the output format, outputs the
     * output document.
     * @param docbookInput The combined Docbook document from outputCombinedDoc.
     * @param output The file to use for output
     * @param transformStylesheet Stylesheet to use in transforming docbookInput.
     */
    public void transformCombinedDocbookToOutputFormat(File docbookInput, File output,
            File transformStylesheet) {
        // create the transformer.
                // set up the transformer.
        Transformer saxonDocbookTransformer = null;
        try {
            Source tformSource = new StreamSource(transformStylesheet);
            saxonDocbookTransformer = transformerFactory.newTransformer(tformSource);
        } catch (TransformerConfigurationException tcex) {
            throw new RuntimeException("Exception when setting up transformer for "
                    + "JSIDL-to-Docbook transformation.", tcex);
        }

        // do transformation
        try {
            StreamSource docbookData = new StreamSource(docbookInput);
            StreamResult xslfoData = new StreamResult(output);
            saxonDocbookTransformer.transform(docbookData, xslfoData);

        } catch (TransformerException te) {
            throw new RuntimeException("Error during transformation!", te);
        }
    }

    /**
     * Adds the source code from the JSIDL files as sections in the appendix in the JDOM DOM representation of the
     * template Docbook document found in templateDoc.  Assumes there is an <appendix> with xml:id of
     * "appendix.APPENDIX_A" in the template.  If not, quits with a RuntimeException.
     * @param jsidlFiles List pairing JSIDL files and original service names
     * @param templateDoc JDOM DOM representation of the template Docbook document
     */
    public void addServiceSourceToDocAppendix(List<FileNamePair> jsidlFiles, Document templateDoc) {
        try {

            // get Element for source code appendix via XPath.
            Namespace dbkNs = Namespace.getNamespace(DOCBOOK_NAMESPACE_NAME, DOCBOOK_NAMESPACE_URI);
            //Namespace xmlNs = Namespace.getNamespace("xml", "")

            // the template docbook document must contain an <appendix> with xml:id of "appendix.APPENDIX_A".
            XPath appendixPath = XPath.newInstance(APPENDIX_PATH_STRING);
            appendixPath.addNamespace(dbkNs);
            List<Object> appendixL = appendixPath.selectNodes(templateDoc);

            if (appendixL.size() != 1) {
                throw new RuntimeException("Appendix for adding service JSIDL source was not found!");
            }

            if (!(appendixL.get(0) instanceof Element)) {
                throw new RuntimeException("JSIDL source appendix was not an element!?");
            }

            Element appendix = (Element) appendixL.get(0);

            for (FileNamePair jsidlInfo : jsidlFiles) {
                String jsidlData = "";
                try {
                    // read file contents into string, treat file as UTF-8.
                    jsidlData = IO.readFileToString(jsidlInfo.file, utf8Charset);
                } catch (IOException ioex) {
                    throw new RuntimeException("IO exception when reading JSIDL data to add to appendix: ", ioex);
                }

                Matcher matchedData = mxCellPattern.matcher(jsidlData);
                String noMxCellsData = matchedData.replaceAll("");

                // make section entitled w/ name of service, followed by a programlisting containing the source in a
                // CDATA, then add to the source appendix.
                Element sourceSect = new Element("section");
                sourceSect.addContent(new Element("title").addContent(jsidlInfo.svcname));

                Element programListing = new Element("programlisting");
                sourceSect.addContent(programListing);

                CDATA sourceCDATA = new CDATA(noMxCellsData);
                programListing.addContent(sourceCDATA);

                // add font size reduction directive to program listing...
                programListing.addContent(new ProcessingInstruction("db-font-size", "75%"));

                appendix.addContent(sourceSect);
            }
        } catch (JDOMException jdex) {
            throw new RuntimeException("JDOM error while adding source code to appendix: ", jdex);
        }
    }
}
