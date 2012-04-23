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
package org.jts.gui.importJSIDL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.io.File;

import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Schema;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.XMLConstants;
import org.xml.sax.SAXException;

import org.jts.gui.JAXBtoJmatter.ServiceDef;
import org.jts.jsidl.binding.References;
import org.jts.jsidl.binding.InheritsFrom;
import org.jts.jsidl.binding.ClientOf;

import org.jts.gui.GUI;
import org.jts.gui.GUIError;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.jts.gui.util.GUISupport;
import org.jts.gui.util.PathSelectDialog;

/** 
 * Imports XML content in JSIDL
 */
public class Import {

    private static Unmarshaller um = null;
    private static final String sourceSchema = "resources/schema/JSIDL_Plus/jsidl_plus.xsd";

    public Import() {
        try {
            if (um == null) {
                JAXBContext jc = JAXBContext.newInstance("org.jts.jsidl.binding");
                um = jc.createUnmarshaller();
                SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                Schema schema = sf.newSchema(new File(sourceSchema));
                um.setSchema(schema);
            }
        } catch (JAXBException jaxbe) {
            throw new GUIError(jaxbe.getCause());
        } catch (SAXException saxe) {
            throw new GUIError(saxe.getCause());
        }
    }

    public interface ImportStatusMonitor {
        public void updateStatus(final String status);
        public void reportFailure(final String message, final Exception cause);
        public void reportCompletion(final String message);
    }

    public interface ImportErrorReportLogger {
        public void addError(String str);
        public void printErrorReports();
    }

    class ErrorReportLoggerImpl implements ImportErrorReportLogger {
        ArrayList<String> errorReports = new ArrayList<String>();

        public void addError(String error) {
            errorReports.add(error);
        }

        public void printErrorReports() {
            if (!errorReports.isEmpty()) {
                System.out.println(">> Import Errors:");
                for (int ii = 0; ii < errorReports.size(); ii++) {
                    System.out.println(">> Import Error: " + errorReports.get(ii) + "\n");
                }

                System.out.println(">> Found " + errorReports.size() + " import errors...");
            }
        }
    }

    private static File getServiceImportPath(final File startingPath) {

        try {
            GUISupport.DialogDisplayer container = new GUISupport.DialogDisplayer() {

                public void setupDialog() {
                    PathSelectDialog newDialog = new PathSelectDialog(
                            GUI.getFrame(), true, startingPath, "Import JSIDL",
                            "Select a JSIDL XML file or directory containing JSIDL XMLs files, then click Import.",
                            "Import");
                    newDialog.setFileSelectionMode(PathSelectDialog.FILES_AND_DIRECTORIES);
                    newDialog.setSaveMode(false);

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
            throw new RuntimeException("Error while displaying dialog to get service def import path.", ex);
        }
    }

    /**
     * Performs the task of importing a set of JSIDL files into JTS.  Since it's a Runnable, may
     * be used in a thread.
     */
    private class ImportDefsWorker implements Runnable {

        private File inputPath;
        private ImportStatusMonitor statusMonitor;
        private ImportErrorReportLogger errorLogger;
        private boolean importSuccess = false;
        private ArrayList<org.jts.jsidl.binding.ServiceDef> resultServiceDefs;
        private boolean canceled = false;

        /**
         * provide an ActionListener that can invoke our cancel method()
         */
        private class CancelActionListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                ImportDefsWorker.this.cancel();
            }
        }

        /**
         * Keep one instance of CancelActionListener around.
         */
        private CancelActionListener cancelListenerInstance;

        /**
         * Access the CancelActionListener instance.
         * @return the ImportDefsWorker's CancelActionListener instance.
         */
        public ActionListener getCancelActionListener() {
            return cancelListenerInstance;
        }

        /**
         * Determine whether the import process finished successfully.  Note it is not
         * set to true until run() finishes running.
         * @return
         */
        public boolean importSucceeded() {
            return importSuccess;
        }

        /**
         * Get the set of JAXB-format service definitions imported into the database.  Is not
         * filled until run() finishes running.
         * @return
         */
        public List<org.jts.jsidl.binding.ServiceDef> getResults() {
            return resultServiceDefs;
        }

        /**
         * Create an InputDefsWorker for importing the JSIDL file given in newInputPath, or if newInputPath
         * refers to a directory, all the JSIDL files within.  Provide stub implementations for newStatusMonitor
         * and newErrorLogger if status monitoring and/or error logging are not needed.  All args assumed
         * to be non-null.
         * @param newInputPath A path to a JSIDL file or a directory containing JSIDL files.
         * @param newStatusMonitor A concrete ImportStatusMonitor instance for reporting status info.
         * @param newErrorLogger A concrete ImportErrorReportLogger instances for recording errors.
         */
        ImportDefsWorker(File newInputPath, ImportStatusMonitor newStatusMonitor,
                ImportErrorReportLogger newErrorLogger) {
            inputPath = newInputPath;
            statusMonitor = newStatusMonitor;
            errorLogger = newErrorLogger;

            importSuccess = false;
            resultServiceDefs = new ArrayList<org.jts.jsidl.binding.ServiceDef>();

            cancelListenerInstance = new CancelActionListener();
        }

        /**
         * Sets the canceled flag, causing performImport to return at the next "quit point".
         */
        private void cancel() {
            // if cancel is invoked by the ImportStatusMonitor, it's up to the ImportStatusMonitor to
            // clean itself up if it's a dialog or other GUI feature.
            canceled = true;
            statusMonitor.reportFailure("Import cancelled by user.", null);
        }

        /**
         * Calls performInput.
         */
        public void run() {
            try {
                performImport();
            } catch (Exception ex) {
                // catch anything not caught inside performImport itself and show error in the
                // status monitor.
                com.u2d.app.Context.getInstance().getViewMechanism().message(
                        "Error while importing service definitions! ");
                statusMonitor.reportFailure("An error occurred while importing service definitions, file must contain a valid service definition", ex);
                throw new RuntimeException("Error while importing service definitions.", ex);
            }
        }

        /**
         * Filter jsidl files, unmarshal and place in a Map.
         * @param fileList list of JSIDL XML files containing service sets
         * @return A Map from service set ID/version strings to JAXB instances representing those service sets.
         */
        private Map getObjectMap(List<File> fileList) {
            Map objMap = new HashMap();
            Document doc = null;
            DocumentBuilder db = null;
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);

            try {
                db = dbf.newDocumentBuilder();
            } catch (javax.xml.parsers.ParserConfigurationException pce) {
                errorLogger.addError("Exception while configuring parser: " + pce.toString());
            }

            for (int ii = 0; ii < fileList.size(); ii++) {
                File file = fileList.get(ii);
                final String fileName = file.toString();

                try {
                    doc = db.parse(file);
                } catch (final IOException ioe) {
                    errorLogger.addError("Unable to read file" + fileName + " \n " + ioe.toString());
                    continue;   // weaken import to allow bad files in target dir
                } catch (final SAXException saxe) {
                    errorLogger.addError("Unable to parse file" + fileName + " \n " + saxe.toString());
                    continue;    // weaken import to allow bad files in target dir
                }

                statusMonitor.updateStatus("Unmarshalling files...");
                Element root = doc.getDocumentElement();

                if (root.getAttribute("xmlns").equals("urn:jaus:jsidl:1.0")) {

                    try {
                        objMap.put(root.getAttribute("id") + "-" + root.getAttribute("version"),
                                um.unmarshal(file));
                    } catch (final JAXBException jaxbe) {
                        errorLogger.addError("Unable to unmarshal file" + fileName + " \n " + jaxbe.toString());
                        continue;    // weaken import to allow bad files in target dir
                    }
                }
            }

            return objMap;
        }

        /**
         * Does the work of importing JSIDL files into JTS' database.
         */
        private void performImport() {
            statusMonitor.updateStatus("searching " + inputPath.getAbsolutePath() + " for JSIDL files ...");

            List<File> fileList = getFileList(inputPath);

            if (fileList.isEmpty()) {
                final String message = "Error: No XML file(s) found at selected path.";
                statusMonitor.reportFailure(message, null);
                errorLogger.addError(message);
                errorLogger.printErrorReports();
                return;
            } else {
                statusMonitor.updateStatus("Found " + fileList.size() + " XML file(s) at "
                        + inputPath.getAbsolutePath() + "...");
            }

            if (canceled) {
                return;
            }

            // construct map from JSIDL entity IDs to Jaxb instances
            Map jaxbObjMap = getObjectMap(fileList);

            if (canceled) {
                return;
            }

            // if single file is selected, simply import all declared types,
            // in parent dir in addition to base and client servicesto ensure that
            // all external references will be available when external references
            // are being resolved
            if (fileList.size() == 1) {
                if (jaxbObjMap.isEmpty()) {
                    final String message =  "Unable to parse " + fileList.get(0).getName();
                    statusMonitor.reportFailure(message, null);
                    errorLogger.addError(message);
                    errorLogger.printErrorReports();
                    return;
                }

                List<File> tmpList = getFileList(inputPath.getParentFile());
                Map tmpMap = null;

                tmpMap = getObjectMap(tmpList);

                // get inherited services
                org.jts.jsidl.binding.ServiceDef service =
                        (org.jts.jsidl.binding.ServiceDef) jaxbObjMap.values().iterator().next();

                String baseKey = null;
                while ((baseKey = getBaseServiceKey(service)) != null) {
                    if (ServiceDef.lookupServiceDef(getBaseServiceId(service),
                            getBaseServiceVersion(service)) != null) {
                        break;
                    }

                    service = (org.jts.jsidl.binding.ServiceDef) tmpMap.get(baseKey);

                    if (service != null) {
                        jaxbObjMap.put(baseKey, service);
                    } else {
                        final String message = "Inherited ServiceDef " + baseKey + " not found (or unparsable).";
                        statusMonitor.reportFailure(message, null);
                        errorLogger.addError(message);
                        errorLogger.printErrorReports();
                        return;
                    }
                }

                // get client services
                org.jts.jsidl.binding.References ref = service.getReferences();
                List<org.jts.jsidl.binding.ClientOf> clients = null;
                if (ref != null) {
                    clients = ref.getClientOf();
                }

                if (clients != null) {
                    for (int ii = 0; ii < clients.size(); ii++) {
                        org.jts.jsidl.binding.ClientOf cref = clients.get(ii);
                        String clientKey = cref.getId() + "-" + cref.getVersion();
                        Object value = tmpMap.get(clientKey);
                        if (value != null) {
                            jaxbObjMap.put(clientKey, value);
                        } else if (ServiceDef.lookupServiceDef(cref.getId(),
                                cref.getVersion()) != null) {
                            continue;
                        } else {
                            final String message = "client ServiceDef " + clientKey + " not found (or unparsable).";
                            statusMonitor.reportFailure(message, null);
                            errorLogger.addError(message);
                            errorLogger.printErrorReports();
                            return;
                        }
                    }
                }

                // add all declared types
                Iterator<String> iter = tmpMap.keySet().iterator();

                while (iter.hasNext()) {
                    String key = iter.next();
                    Object value = tmpMap.get(key);

                    if (value instanceof org.jts.jsidl.binding.DeclaredTypeSet
                            || value instanceof org.jts.jsidl.binding.DeclaredConstSet) {
                        jaxbObjMap.put(key, value);
                    }
                }
            }

            // resolve declared type references
            statusMonitor.updateStatus("Resolving declared type references....");
            ArrayList<org.jts.jsidl.binding.ServiceDef> toCommit =
                    new ArrayList<org.jts.jsidl.binding.ServiceDef>();

            for (Iterator<String> iter = jaxbObjMap.keySet().iterator(); iter.hasNext();) {
                String key = iter.next();
                Object obj = jaxbObjMap.get(key);

                if (obj instanceof org.jts.jsidl.binding.ServiceDef) {
                    try {
                        org.jts.jsidl.binding.ServiceDef sdef =
                                org.jts.gui.importJSIDL.declaredElementsResolution.ServiceDef.resolveDeclaredElements((org.jts.jsidl.binding.ServiceDef) obj, jaxbObjMap);
                        toCommit.add(sdef);
                    } catch (final ImportException iee) {
                        statusMonitor.reportFailure("Error while resolving declared type references:\n"
                                + iee.getMessage(), iee);
                        errorLogger.addError(iee.getMessage());
                        errorLogger.printErrorReports();
                        throw new RuntimeException(iee);
                    }
                }
            }

            if (canceled) {
                return;
            }

            // check inherits-from references
            statusMonitor.updateStatus("Resolving inherits-from references...");
            for (Iterator<org.jts.jsidl.binding.ServiceDef> iter = toCommit.iterator(); iter.hasNext();) {
                org.jts.jsidl.binding.ServiceDef sdef = iter.next();
                References refs = sdef.getReferences();

                if (refs == null) {
                    continue;
                }

                final InheritsFrom iref = refs.getInheritsFrom();

                if (iref == null) {
                    continue;
                }

                String key = iref.getId() + "-" + iref.getVersion();
                if (jaxbObjMap.get(key) == null && ServiceDef.lookupServiceDef(iref.getId(), iref.getVersion()) == null) {
                    final String message = "Inherited ServiceDef " + iref.getName() + " v"
                            + iref.getVersion() + " not found (or unparsable).";
                    statusMonitor.reportFailure(message, null);
                    errorLogger.addError(message);
                    errorLogger.printErrorReports();
                    return;
                }
            }

            if (canceled) {
                return;
            }

            // check client-of references
            statusMonitor.updateStatus("Resolving client-of references...");
            for (Iterator<org.jts.jsidl.binding.ServiceDef> iter = toCommit.iterator(); iter.hasNext();) {
                org.jts.jsidl.binding.ServiceDef sdef = iter.next();
                References refs = sdef.getReferences();

                if (refs == null) {
                    continue;
                }

                List<ClientOf> crefs = refs.getClientOf();

                if (crefs == null) {
                    continue;
                }

                for (final ClientOf cref : crefs) {
                    String key = cref.getId() + "-" + cref.getVersion();

                    if (jaxbObjMap.get(key) == null && ServiceDef.lookupServiceDef(cref.getId(), cref.getVersion()) == null) {
                        final String message = "Client ServiceDef " + cref.getName() + " v"
                                + cref.getVersion() + " not found (or unparsable).";
                        statusMonitor.reportFailure(message, null);
                        errorLogger.addError(message);
                        errorLogger.printErrorReports();
                        return;
                    }
                }
            }

            if (canceled) {
                return;
            }

            String validatingServiceDefName = "";

            statusMonitor.updateStatus("Validating protocol behavior...");

            // validate protocol behavior
            try {
                // protocol validator needs a list of all parent service definitions
                List<org.jts.jsidl.binding.ServiceDef> sdefs = new ArrayList<org.jts.jsidl.binding.ServiceDef>();
                sdefs.addAll(toCommit);

                // must keep track of service defs that were validated
                List<org.jts.jsidl.binding.ServiceDef> validatedSdefs = new ArrayList<org.jts.jsidl.binding.ServiceDef>();

                while (sdefs.size() > 0) {
                    org.jts.jsidl.binding.ServiceDef sdef = org.jts.pbValidator.ValidatorUtils.getRootServiceDefInList(sdefs);

                    // add any service defs that were found from lookup
                    List<org.jts.jsidl.binding.ServiceDef> inheritedSdefs = new ArrayList<org.jts.jsidl.binding.ServiceDef>();

                    inheritedSdefs.add(sdef);
                    org.jts.pbValidator.ValidatorUtils.getInheritedServiceDefList(sdef, toCommit, inheritedSdefs);

                    // add any service defs that were already validated but not commited
                    inheritedSdefs.addAll(validatedSdefs);

                    // keep track of what is being validated in case there is an error
                    validatingServiceDefName = sdef.getName();

                    // try validate the current service def
                    org.jts.pbValidator.Validator validator = new org.jts.pbValidator.Validator();
                    validator.validate(inheritedSdefs, sdef.getProtocolBehavior());

                    // add current service def to validated list
                    validatedSdefs.add(sdef);

                    // remove current service def from list so we don't catch it as a root anymore
                    sdefs.remove(sdef);
                }
            } catch (org.jts.pbValidator.ValidatorException e) {
                String results = "";
                // only stop import when there is an error, not a warning
                boolean stopImport = false;

                for (org.jts.pbValidator.ValidatorResult result : e.getResults()) {
                    results += "Validation Error with service def " + validatingServiceDefName + " : " + result.getPath() + System.getProperty("line.separator");
                    results += result.getDescription() + System.getProperty("line.separator");
                    
                    if( result.isError() )
                    {
                    	stopImport = true;
                    }
                }
                statusMonitor.reportFailure(results, null);
                errorLogger.addError(results);
                errorLogger.printErrorReports();
                
                
                if( stopImport )
                {
                	throw new RuntimeException(e);
                }
            }

            if (canceled) {
                return;
            }

            statusMonitor.updateStatus("Committing imported services to database...");

            try {
                // commitInOrder empties the argument list out for some reason, so let's copy contents to our list for
                // returning (for now)
                // TODO: why in the world does it 'eat' the input list?????
                resultServiceDefs = new ArrayList(toCommit);
                commitInOrder(toCommit);
                importSuccess = true;
                statusMonitor.reportCompletion("Service definition import completed.");
            } catch (ImportException ie) {
                errorLogger.addError(ie.getMessage());
                errorLogger.printErrorReports();
                statusMonitor.reportFailure("Import exception while committing: \n" + ie.getMessage(), ie);
                throw new RuntimeException(ie);
            }

            errorLogger.printErrorReports();
        }
    }

    // stores path to parent directory of last import path (whether import path was a single file or a directory)
    private static File lastImportPath = null;

    /**
     * Import JSIDL service definitions into database from a path specified by user via a browse dialog.
     * @return
     * @throws ImportException
     */
    public Object importServiceDefs() throws ImportException {

        File startingPath = lastImportPath;
        if (startingPath == null) {
            startingPath = new File(System.getProperty("user.home"));
        }

        // path may be a single JSIDL file or a directory with one or more JSIDL files beneath it.
        // may also be null indicating the user canceled the import dialog.
        final File inputPath = getServiceImportPath(startingPath);

        if (inputPath != null) {
            // start the next browse dialog in the parent directory of whatever path was selected
            lastImportPath = inputPath.getParentFile();

            // set up the progress indicator dialog
            InfoDialogDisplayer dialogDisplayer = new InfoDialogDisplayer(GUI.getFrame());

            ErrorReportLoggerImpl errorLogger  = new ErrorReportLoggerImpl();

            ImportDefsWorker importDefsWorker = new ImportDefsWorker(inputPath, dialogDisplayer, errorLogger);

            // enable the user to cancel processing via the progress indicator dialog.
            ActionListener cancelListenerInstance = importDefsWorker.getCancelActionListener();
            dialogDisplayer.setCancelActionListener(cancelListenerInstance);

            // running the dialogDisplayer causes a progress dialog to be set up and displayed.
            try {
                GUISupport.runOnEDT(dialogDisplayer);
            } catch (Exception ex) {
                throw new RuntimeException("Error when displaying the import progress info dialog.", ex);
            }

            // perform the real work in a thread.
            new Thread((Runnable)importDefsWorker).start();
        }

        return "Import Successful!";
    }

    /**
     * Import service definitions into database from a path to JSIDL XML file(s) specified in inputPath.  Returns JAXB
     * instances representing the imported service definitions.
     * @param inputPath may be a directory containing one or more JSIDL files, or a
     * @return A List of JAXB ServiceDefs.
     * @throws ImportException
     */
    public List<org.jts.jsidl.binding.ServiceDef> importServiceDefs(final File inputPath) throws ImportException {
        ImportStatusMonitor dummyMonitor = new ImportStatusMonitor() {
            public void updateStatus(String status) { 
                System.out.println("Import Status:" + status);
            }
            public void reportFailure(String message, Exception cause) { 
                System.out.println("Import Failure:" + message);
            }
            public void reportCompletion(String message) { 
                System.out.println("Import Complete: " + message);
            }
        };
        
        ImportErrorReportLogger dummyLogger = new ImportErrorReportLogger() {
            public void addError(String str) { 
                System.out.println("Import Error Reported: " + str);
            }
            public void printErrorReports() { }
        };

        ImportDefsWorker importWorker = new ImportDefsWorker(inputPath, dummyMonitor, dummyLogger);

        importWorker.run();

        if (importWorker.importSucceeded()) {
            return importWorker.getResults();
        } else {
            return null;
        }
    }

    /**
     * Gets the 'key' for a JAXB service def instance's base service def, or null if the service def instance has no
     * base service.  The 'key' is a string consisting of the base service def's ID, and version, connected by a hyphen.
     * @param service The JAXB service def instance in question.
     * @return
     */
    private String getBaseServiceKey(org.jts.jsidl.binding.ServiceDef service) {
        org.jts.jsidl.binding.References ref = service.getReferences();

        if (ref == null) {
            return null;
        }

        org.jts.jsidl.binding.InheritsFrom inh = ref.getInheritsFrom();

        if (inh == null) {
            return null;
        } else {
            return inh.getId() + "-" + inh.getVersion();
        }
    }

    /**
     * Returns the service ID of a JAXB service_def instance's base service_def, or null if the service_def instance
     * inherits from no other service_def.
     * @param service The JAXB service def instance in question.
     * @return
     */
    private static String getBaseServiceId(org.jts.jsidl.binding.ServiceDef service) {
        org.jts.jsidl.binding.References ref = service.getReferences();

        if (ref == null) {
            return null;
        }

        org.jts.jsidl.binding.InheritsFrom inh = ref.getInheritsFrom();

        if (inh == null) {
            return null;
        } else {
            return inh.getId();
        }
    }

    /**
     * Returns the version of a JAXB service_def instance's base service_def, or null if the service_def instance
     * inherits from no other service_def.
     * @param service The JAXB service def instance in question.
     * @return
     */
    private static String getBaseServiceVersion(org.jts.jsidl.binding.ServiceDef service) {
        org.jts.jsidl.binding.References ref = service.getReferences();

        if (ref == null) {
            return null;
        }

        org.jts.jsidl.binding.InheritsFrom inh = ref.getInheritsFrom();

        if (inh == null) {
            return null;
        } else {
            return inh.getVersion();
        }
    }

    private static void commitInOrder(List<org.jts.jsidl.binding.ServiceDef> list) {
        if (list.size() == 1) {
            ServiceDef.lookupOrCreate(list.remove(0));
            return;
        }

        while (!list.isEmpty()) {
            boolean restart = false;
            for (int jj = 1; jj < list.size(); jj++) {
                if (compare(list.get(0), list.get(jj)) == 1) {
                    Collections.swap(list, 0, jj);
                    restart = true;
                    break;
                }
            }

            if (!restart) {
                ServiceDef.lookupOrCreate(list.remove(0));
            }
        }
    }

    /**
     * @Returns:  a negative integer, zero, or a positive integer as s1 is less (dependent) than, equal to, or greater (in dependency) than s2.
     *
     */
    private static int compare(org.jts.jsidl.binding.ServiceDef s1, org.jts.jsidl.binding.ServiceDef s2) {
        References r1 = s1.getReferences();
        References r2 = s2.getReferences();

        if ((r1 == null) && (r2 == null)) {
            return 0;
        } else if ((r1 == null) && (r2 != null)) {
            return -1;
        } else if ((r1 != null) && (r2 == null)) {
            return 1;
        }

        // compute case of (r1 != null)  && (r2 != null)
        boolean s1Rs2 = false;
        boolean s2Rs1 = false;

        // collect s1 reference uids
        List<String> refs1 = new ArrayList<String>();
        InheritsFrom iref1 = r1.getInheritsFrom();
        if (iref1 != null) {
            refs1.add(iref1.getId() + "-" + iref1.getVersion());
        }
        List<ClientOf> crefs1 = r1.getClientOf();
        if (crefs1 != null) {
            for (ClientOf cref : crefs1) {
                refs1.add(cref.getId() + "-" + cref.getVersion());
            }
        }

        // compare s1 reference uids to uid2
        String uid2 = s2.getId() + "-" + s2.getVersion();
        for (String uid : refs1) {
            if (uid.equals(uid2)) {
                s1Rs2 = true;
                break;
            }
        }

        // collect s2 reference uids
        List<String> refs2 = new ArrayList<String>();
        InheritsFrom iref2 = r2.getInheritsFrom();
        if (iref2 != null) {
            refs2.add(iref2.getId() + "-" + iref2.getVersion());
        }
        List<ClientOf> crefs2 = r2.getClientOf();
        if (crefs2 != null) {
            for (ClientOf cref : crefs2) {
                refs2.add(cref.getId() + "-" + cref.getVersion());
            }
        }

        // compare s2 reference uids to uid1
        String uid1 = s1.getId() + "-" + s1.getVersion();
        for (String uid : refs2) {
            if (uid.equals(uid1)) {
                s2Rs1 = true;
                break;
            }
        }

        if (s1Rs2 && s2Rs1) {
            if (!uid1.equals(uid2)) {
                throw new ImportException("Invalid cyclic dependency between " + uid1 + " and " + uid2);
            } else {
                return 0;
            }
        } else if (!s1Rs2 && s2Rs1) {
            return -1;
        } else if (s1Rs2 && !s2Rs1) {
            return 1;
        } else {
            return 0;
        }  // !s1Rs2 && !s2Rs1
    }

    // tbd
    public static Object importConstantSets() {
        /*List fileList = getFileList( path );
        Unmarshaller um = createUnmarshaller();

        ImportMessages importMsgs = ImportMessages.getInstance();
        importMsgs.add(ImportMessages.MessageType.INFO, "Importing file: "+path);

        // validate all service sets in specified path
        int val = validate(path, um);

        String rVal = new String();
        if ( val < 0 )
        {
        return "Import unsuccessful. Syntactic error found!";
        }
        else if( val > 0 )
        {
        rVal = new String( "But, "+ val+" semantic validation warning(s) encountered!");
        }

        // import constant sets
        int count = 0;
        for( int ii=0; ii < fileList.size(); ii++ )
        {
        try
        {
        Object obj = um.unmarshal( (File)fileList.get(ii) );

        if( obj instanceof org.jts.jsidl.binding.DeclaredConstSet)
        {
        // Get our JAXB Object
        org.jts.jsidl.binding.DeclaredConstSet jxConstSet = (org.jts.jsidl.binding.DeclaredConstSet) obj;

        // Resolve any referenced constant sets
        jxConstSet = org.jts.gui.importJSIDL.declaredElementsResolution.DeclaredConstantSet.resolveReferencedConstantSets(jxConstSet, path);

        ConstantSet.lookupOrCreate((org.jts.jsidl.binding.DeclaredConstSet) obj);
        count++;
        }

        }
        catch( Exception ex )
        {
        ex.printStackTrace();
        return "Import Unsuccessful!";
        }
        }

        System.out.println("Import successful."+rVal+" Imported "+count+" service set(s)...");
        if(rVal.length() == 0)
        {
        if(importMsgs.getMessages(ImportMessages.MessageType.WARNING).size() > 0)
        {
        Vector<String> warnings = importMsgs.getMessages(ImportMessages.MessageType.WARNING);
        for(int i = 0; i < warnings.size(); i++)
        {
        System.out.println("[Warning] "+ warnings.get(i));
        }
        return "Import successful, but with warnings.";
        }
        else
        {
        return "Import successful... ";
        }
        }
        else
        {
        return "Import successful, but semantic error(s) encountered! ";
        }*/
        return null;
    }

    /**
     * Returns a List of Files under the specified path whose name ends with the extension '.xml' or '.jsidl'.
     * If path refers to a single file, returns a list containing one element-the file referred to by path itself,
     * if that file's name ends with extension '.xml' or '.jsidl'.
     */
    private static List<File> getFileList(File path) {
        if (path.isFile()) {
            List<File> list = new java.util.ArrayList<File>();
            if (path.getName().endsWith(".xml") || path.getName().endsWith(".jsidl")) {
                list.add(path);
            }
            return list;
        } else {
            // recursively searches through files under directory at path, for files ending with .xml or .jsidl.
            // note the 'TrueFileFilter' is being provided as the filter for subdirectories-TrueFileFilter returns
            // true for every subdirectory, so the search will be fully recursive.
            return new ArrayList<File>(FileUtils.listFiles(path,
                    new SuffixFileFilter(new String[]{".xml", ".jsidl"}), TrueFileFilter.INSTANCE));
        }
    }
}
