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
package org.jts.gui.importCJSIDL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.Unmarshaller;


import org.jts.gui.GUI;

import java.io.IOException;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.jts.gui.util.GUISupport;
import org.jts.gui.util.PathSelectDialog;
import org.jts.gui.importJSIDL.ImportException;
import org.jts.gui.importCJSIDL.Import.ImportStatusMonitor;

/**
 * Imports XML content in JSIDL
 */
public class Import {

    private static Unmarshaller um = null;
    private static final String sourceSchema = "resources/schema/JSIDL_Plus/jsidl_plus.xsd";
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


    public Import() {

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
                            GUI.getFrame(), true, startingPath, "Import CJSIDL",
                            "Select a CJSIDL directory containing CJSIDL CSD files, then click Import.",
                            "Import");
                    newDialog.setFileSelectionMode(PathSelectDialog.DIRECTORIES_ONLY);
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
         * Does the work of importing JSIDL files into JTS' database.
         */
        private void performImport() {
            Exception savedException = null;
            statusMonitor.updateStatus("searching " + inputPath.getAbsolutePath() + " for CJSIDL files ...");

            List<File> fileList = getFileList(inputPath);

            if (fileList.isEmpty()) {
                final String message = "Error: No CSD file(s) found at selected path.";
                statusMonitor.reportFailure(message, null);
                errorLogger.addError(message);
                errorLogger.printErrorReports();
                return;
            } else {
                statusMonitor.updateStatus("Found " + fileList.size() + " CSD file(s) at "
                        + inputPath.getAbsolutePath() + "...");
            }

            if (canceled) {
                return;
            }

            String importStatus = "Import Successful!";
            try {
//                Conversion conv = new Conversion();
                File outputPath = createTempDir();
                // could import and call the converter, but a library version conflict with antlr prevents this
//                conv.convertToJSIDL(inputPath.getCanonicalPath(), outputPath.getCanonicalPath());
                statusMonitor.updateStatus("Initiating Conversion process 'convertToJSIDL'");
                String execStr = ("java " + classpath + " org.jts.eclipse.conversion.cjsidl.Conversion \"convertToJSIDL\" \"" + inputPath.getCanonicalPath() + "\" \"" +  outputPath.getCanonicalPath() + "\"");
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
                    statusMonitor.updateStatus("Conversion to JSIDL complete...");

                } catch (InterruptedException ex) {
                    savedException = ex;
                    errorLogger.addError(ex.getMessage());
                    Logger.getLogger(Import.class.getName()).log(Level.SEVERE, null, ex);
                    statusMonitor.updateStatus("Conversion process interrupted: The conversion process may not have been completed and should be executed again.");
                    importStatus = "Import Failed!...";
                }
                String errors = gerrors.getData();
                String log = goutput.getData();
                System.out.println("Process exited with code = " + p.exitValue());
                if(!errors.isEmpty()){
                    errorLogger.addError(errors);
                    Logger.getLogger(Import.class.getName()).log(Level.SEVERE, errors);
                    JOptionPane.showMessageDialog(GUI.getFrame(), errors, "CJSIDL Import Error", JOptionPane.ERROR_MESSAGE);
                    importStatus = "Import Failed!...";
                }
                statusMonitor.updateStatus("Importing converted files...");

                org.jts.gui.importJSIDL.Import _import = new org.jts.gui.importJSIDL.Import();
	        _import.importServiceDefs(outputPath);


            } catch (IOException ex) {
                savedException = ex;
                errorLogger.addError(ex.getMessage());
                Logger.getLogger(Import.class.getName()).log(Level.SEVERE, null, ex);
                statusMonitor.updateStatus(ex.getMessage());
                importStatus = "Import Failed!...";
            }
            if(importStatus.equals("Import Failed!...")){
                statusMonitor.reportFailure(importStatus, savedException);
            } else{
                statusMonitor.reportCompletion("Import Successful!");
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
        final File outputPath;
        if (inputPath != null) {
            // start the next browse dialog in the parent directory of whatever path was selected
            lastImportPath = inputPath.getParentFile();
            // set up the progress indicator dialog
            InfoDialogDisplayer dialogDisplayer = new  InfoDialogDisplayer(GUI.getFrame()); 
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
 * Create a new temporary directory.
 *
 * @return  the new directory
 * @throws IOException if there is an error creating the temporary directory
 */
public static File createTempDir() throws IOException
{
    final File sysTempDir = new File(System.getProperty("java.io.tmpdir"));
    File newTempDir;
    final int maxAttempts = 9;
    int attemptCount = 0;
    do
    {
        attemptCount++;
        if(attemptCount > maxAttempts)
        {
            throw new IOException(
                    "The highly improbable has occurred! Failed to " +
                    "create a unique temporary directory after " +
                    maxAttempts + " attempts.");
        }
        String dirName = "CJSIDL_" + Long.toString(System.nanoTime());
        newTempDir = new File(sysTempDir, dirName);
    } while(newTempDir.exists());

    if(newTempDir.mkdirs())
    {
        return newTempDir;
    }
    else
    {
        throw new IOException(
                "Failed to create temp dir named " +
                newTempDir.getAbsolutePath());
    }
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
     * Returns a List of Files under the specified path whose name ends with the extension '.csd'.
     * If path refers to a single file, returns a list containing one element-the file referred to by path itself,
     * if that file's name ends with extension '.csd'.
     */
    private static List<File> getFileList(File path) {
        if (path.isFile()) {
            List<File> list = new java.util.ArrayList<File>();
            if (path.getName().endsWith(".csd") ) {
                list.add(path);
            }
            return list;
        } else {
            // recursively searches through files under directory at path, for files ending with .xml or .jsidl.
            // note the 'TrueFileFilter' is being provided as the filter for subdirectories-TrueFileFilter returns
            // true for every subdirectory, so the search will be fully recursive.
            return new ArrayList<File>(FileUtils.listFiles(path,
                    new SuffixFileFilter(new String[]{".csd"}), TrueFileFilter.INSTANCE));
        }
    }
}
