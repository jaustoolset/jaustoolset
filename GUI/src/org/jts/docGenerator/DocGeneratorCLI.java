/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2010-2011, United States Government
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
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jts.docGenerator;

import com.u2d.app.Application;
import jargs.gnu.CmdLineParser;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.SchemaFactory;
import org.jts.gui.importJSIDL.Import;
import org.jts.jsidl.binding.ServiceDef;
import org.jts.jsidl.binding.ServiceSet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Command-line interface to documentation generation functionality.
 * @author idurkan
 */
public class DocGeneratorCLI {

    private static String nl = System.getProperty("line.separator");

    private static void printUsage() {
        System.err.println(
            "Usage: java org.jts.docGenerator.DocGeneratorCLI [options] [args]" + nl +
            "options: " + nl +
            "    {'-o','--output'} : Path to output directory, required. " + nl +
            "    '--styleCust' : Path to custom stylization directory, optional. " + nl +
            "    '--lhtml' : Produce output in Linear HTML format." + nl +
            "    '--fhtml' : Produce output in Framed HTML format." + nl +
            "    '--word' : Produce output in Word .docx format." + nl +
            "    '--keepIntermeds' : Don't delete the intermediate files produced during document generation.  "
            +            "Optional; has no effect on Framed HTML output." + nl +
            "args: " + nl +
            "    * One or more paths to XML files containing JSIDL service_defs." + nl
        );
    }

    private static void printUsageMessageAndQuit(String message) {
            System.err.println(message);
            printUsage();
            System.exit(2);
    }

    /**
     * Parses the JSIDL ServiceSet in servSetFile into a JAXB ServiceSet instance.
     * Assumes servSetFile exists and is actually a JSIDL XML File with a service set inside.
     * @param servSetFile
     * @return
     */
    static ServiceSet prepareServiceSetFromFile(File servSetFile) {
        ServiceSet set = null;
        try {
            JAXBContext jc = JAXBContext.newInstance("org.jts.jsidl.binding");
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            unmarshaller.setSchema(SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema").newSchema(
                    new File("resources/schema/JSIDL_Plus/jsidl_plus.xsd")));

            set = (ServiceSet)unmarshaller.unmarshal(servSetFile);

        } catch (JAXBException jaxbe) {
            throw new RuntimeException("JAXB Exception when parsing service set from file.", jaxbe);
        } catch (SAXParseException saxpe) {
            throw new RuntimeException("SAX Parsing Exception when parsing service set from file.", saxpe);
        } catch (SAXException saxe) {
            throw new RuntimeException("", saxe);
        }

        return set;
    }

    /**
     * Uses the JTS Import class to import the service definitions in the given JSIDL XML files into JTS' database,
     * and synthesizes a ServiceSet containing those service definitions.  Note: the Hibernate database
     * needs to be loaded into memory for this call to succeed.
     * @param servDefFiles List of XML files containing JSIDL for service_defs
     * @return A JAXB ServiceSet containing JAXB representations of all the service_defs in servDefFiles.
     */
    static ServiceSet prepareServiceSetFromServiceDefs(List<File> servDefFiles) {

        ServiceSet set = new ServiceSet();
        set.setVersion("1.0");
        set.setId("dummy.dummy");
        set.setName("DummyServiceSet");

        try {
            // perform the import.
            Import importer = new Import();

            for (File servDefFile : servDefFiles) {
                List<ServiceDef> servDefs = importer.importServiceDefs(servDefFile);

                if (servDefs != null) {
                    // copy the imported ServiceDefs into the new set's serv def list.
                    List<ServiceDef> setServDefs = set.getServiceDef();
                    for (ServiceDef servDef : servDefs) {
                        setServDefs.add(servDef);
                    }
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error during import step of document generation.", ex);
        }

        return set;
    }

    public static void main(String[] args) {

        CmdLineParser parser = new CmdLineParser();

        // path options
        CmdLineParser.Option outputPathOpt = parser.addStringOption('o', "output");
        CmdLineParser.Option styleCustPathOpt = parser.addStringOption("styleCust");

        // output type options
        CmdLineParser.Option linearHTMLOpt = parser.addBooleanOption("lhtml");
        CmdLineParser.Option framedHTMLOpt = parser.addBooleanOption("fhtml");
        CmdLineParser.Option wordDocxOpt = parser.addBooleanOption("word");

        // other options
        CmdLineParser.Option keepIntermedsOpt = parser.addBooleanOption("keepIntermeds");

        try {
            parser.parse(args);
        } catch ( CmdLineParser.OptionException e) {
            System.err.println(e.getMessage());
            printUsage();
            System.exit(2);
        }

        // get path options settings
        String outputPath = (String)parser.getOptionValue(outputPathOpt);
        if (outputPath == null) {
            printUsageMessageAndQuit("The output path option '-o'/'--output' must be specified.");
        }
        File outputDir = new File(outputPath);

        String styleCustPath;
        // The eclipse plugin uses a different path than regular JTS.
        if(new File(AllDocGeneratorCommon.ECLIPSE_STYLESET_PATH).exists())
        {
            styleCustPath = (String)parser.getOptionValue(styleCustPathOpt,
                AllDocGeneratorCommon.ECLIPSE_STYLESET_PATH);
        }
        else
        {
            styleCustPath = (String)parser.getOptionValue(styleCustPathOpt,
                AllDocGeneratorCommon.DEFAULT_STYLESET_PATH);
        }
        if (styleCustPath == null) {
            printUsageMessageAndQuit("The custom stylization path ");
        }
        File styleCustDir = new File(styleCustPath);

        // get keep-intermediates setting; note doc generators expect a boolean indicating whether to *delete*,
        // not whether to *keep* intermediates.
        boolean deleteIntermediates =
                !(((Boolean)parser.getOptionValue(keepIntermedsOpt, Boolean.FALSE)).booleanValue());
             
        // get output type setting
        boolean generateLinearHTML = ((Boolean)parser.getOptionValue(linearHTMLOpt, Boolean.FALSE));
        boolean generateFramedHTML = ((Boolean)parser.getOptionValue(framedHTMLOpt, Boolean.FALSE));
        boolean generateWordDocx = ((Boolean)parser.getOptionValue(wordDocxOpt, Boolean.FALSE));

        int numFormatsSet = 0;
        if (generateLinearHTML) { numFormatsSet++; }
        if (generateFramedHTML) { numFormatsSet++; }
        if (generateWordDocx) { numFormatsSet++; }

        if (numFormatsSet < 1) {
            printUsageMessageAndQuit("One of options '--lhtml', '--fhtml' or '--word' must be specified.");
        } else if (numFormatsSet > 1) {
            printUsageMessageAndQuit("Specify only one of options '--lhtml', '--fhtml', or '--word'.");
        }
        
        String[] otherArgs = parser.getRemainingArgs();
        ServiceSet inputSet = null;

        // load up the JTS application's Hibernate database.
        loadHibernateDB();

        if (otherArgs.length <= 0) {
            printUsageMessageAndQuit("One or more JSIDL XML files must be provided as positional arguments");
        }
        List<File> argsAsFiles = new ArrayList();
        for (String arg : otherArgs) {
            argsAsFiles.add(new File(arg));
        }
        inputSet = prepareServiceSetFromServiceDefs(argsAsFiles);

        if (inputSet.getServiceDef().isEmpty()) {
            System.err.println("No input service definitions were valid for import, DocGeneratorCLI exiting.");
            System.exit(2);
        }

        DocGenerator docGen = null;

        if (generateLinearHTML) {
            docGen = new LinearHTMLDocGenerator(inputSet, outputDir, styleCustDir, deleteIntermediates);

        } else if (generateFramedHTML) {
            docGen = new FramedHTMLDocGenerator(inputSet, outputDir, styleCustDir);

        } else if (generateWordDocx) {
            docGen = new WordDocGenerator(inputSet, outputDir, styleCustDir, deleteIntermediates);

        } else {
            printUsageMessageAndQuit("One of options '--lhtml', '--fhtml' or '--word' must be specified.");
        }

        new Thread(docGen).start();
    }

    private static void loadHibernateDB() {
        // find applicationContext.xml somewhere in our classpath.
        ApplicationContext context =
                    new ClassPathXmlApplicationContext("applicationContext.xml");
        // applicationContext.xml contains the information about where the Hibernate database is located.
        ((Application) context.getBean("application")).seedDatabase();

    }
}
