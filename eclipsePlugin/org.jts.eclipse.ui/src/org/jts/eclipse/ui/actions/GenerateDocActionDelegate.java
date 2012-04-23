/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2011, United States Government
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

package org.jts.eclipse.ui.actions;

/**
 * @author vnearing
 * Collects information needed to generate documentation and calls the docgenerator
 */
import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.jts.docGenerator.DocGenerator;
import org.jts.docGenerator.FramedHTMLDocGenerator;
import org.jts.docGenerator.LinearHTMLDocGenerator;
import org.jts.docGenerator.WordDocGenerator;
import org.jts.eclipse.ui.actions.importbinding.ImportServiceSet;
import org.jts.gui.docGenerator.DocgenStatusMonitor;
import org.jts.jsidl.binding.ServiceSet;

public class GenerateDocActionDelegate implements IWorkbenchWindowActionDelegate {

    private IWorkbenchWindow window;
    public static final String ID = "jts.JTS.menu.codegenerator.GenerateDocActionDelegate";
    private Shell shell;

    private String docType = "";
    private String servName = "";
    private String outpath = "";
    private String stylePath = "plugins/org.jts.eclipse.data_1.0/resources/docGenerator/";
    private Boolean delIntermediateFiles = true;
    private DocGenDialog dgd;

    @Override
    public void run(IAction action) {
        dgd = new DocGenDialog(shell);
        boolean done = false;

        // Keep re-launching the window until input is correct or generation
        // canceled.
        while (!done) {
            launch();
            IProject project = getProjectName();

            if (project == null) {
                MessageDialog.openInformation(shell, "Error generating code...", "Please select a valid project.");
            } else {

                if (docType == null || servName == null || outpath == null || stylePath == null || delIntermediateFiles == null) {
                    // Don't generate anything - was canceled.
                    done = true;
                } else if (!isServNameValid()) {
                    MessageDialog.openInformation(shell, "Error generating document...", "Invalid service name. Please try again.");
                } else if (!isOutpathValid()) {
                    MessageDialog.openInformation(shell, "Error generating document...", "Invalid output path. Please try again.");
                } else if (!isStylepathValid()) {
                    MessageDialog.openInformation(shell, "Error generating document...", "Invalid style path. Please try again.");
                } else {
                	try {
                    File styleDir = new File(stylePath);
                    File outDir = new File(outpath);
                    ImportServiceSet importSS = new ImportServiceSet(servName, project.getLocation(), project, window.getShell());
                    ServiceSet ss;
					
						ss = importSS.getServiceSet();
					
                   
                    DocGenerator gen = null;
                    if (docType.equalsIgnoreCase("word")) {
                         gen = new WordDocGenerator(ss, outDir, styleDir,
                         delIntermediateFiles);
                    } else if (docType.equalsIgnoreCase("linear")) {
                         gen = new LinearHTMLDocGenerator(ss, outDir,
                         styleDir,
                         delIntermediateFiles);
                    } else if (docType.equalsIgnoreCase("framed")) {
                         gen = new FramedHTMLDocGenerator(ss, outDir,
                         styleDir);
                    }
                    DocgenStatusMonitor dgsm = new DocgenStatusMonitor() {

                        public void updateStatus(String string) {
                            System.out.println(string);
                        }

                        public void postFailure(String string, Exception excptn) {
                            System.out.println(string);
                        }

                        public void postSuccess(String string) {
                            System.out.println(string);
                        }
                    };
                    gen.setStatusMonitor(dgsm);
                    gen.generate();
                	} catch (Exception e) {
						String s = "";
						s+= e.getMessage();

						MessageDialog.openInformation(shell, "Error", "There was an error generating your document: \n" + s);// Please see the console for detailed information.");
						e.printStackTrace();
					}
//                     new Thread(wg).start();
                	MessageDialog.openInformation(shell, "Done", "Document generation complete.");
                	MessageDialog.openWarning(shell, "Restart Recommended!", "Document generation converts CJSIDL to JSIDL and causes inheritance and other file references to \n" +
    						"break.  This will also cause future code generation and doc generation to fail.\n" +
    						"Restarting Eclipse will fix this problem and is highly recommended.");
                    done = true;
                }
            }
        }

    }

    /**
     * The sequence for launching the dialog and retrieving the data from it.
     */
    private void launch() {
        dgd.open();
        docType = dgd.getType();
        servName = dgd.getName();
        outpath = dgd.getPath();
        stylePath = dgd.getStylePath();
        delIntermediateFiles = dgd.getDeleteIntermediateFiles();
    }

    /**
     * @return if service name is valid.
     */
    private boolean isServNameValid() {
        if (servName == null || servName.equals(" ") || servName.equals("") || servName.contains(" ")) {
            return false;
        }
        return true;
    }

    /**
     * @return if output path is valid
     */
    private boolean isOutpathValid() {
        if (outpath.contains(" ")) {
            // Path cannot contain spaces.
            return false;
        }
        return true;
    }

    /**
     * @return if the location of the style sheet is valid.
     */
    private boolean isStylepathValid() {
        // TODO: fill out
        // There should be a valid style sheet there.
        return true;
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

    @Override
    public void init(IWorkbenchWindow window) {
        this.window = window;
        this.shell = this.window.getShell();

    }

    /**
     * Find the IProject information to collect project files.
     * 
     * @return the current project
     */
    private IProject getProjectName() {
        IProject project = null;
        ISelectionService selectionService = window.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
        ISelection selection = selectionService.getSelection();

        if (selection instanceof IStructuredSelection) {
            // The project was selected in the explorer. We can pull the project
            // info from the resource object.
            Object element = ((StructuredSelection) selection).getFirstElement();
            if (element instanceof IResource) {
                project = ((IResource) element).getProject();
            } else {
                // Invalid selection or no selection.
                return null;
            }
        } else if (selection instanceof ITextSelection) {
            // An editor window is selected. We can pull the project info from
            // the editor.
            IEditorPart editorPart = window.getActivePage().getActiveEditor();
            if (editorPart != null) {
                IFileEditorInput input = (IFileEditorInput) editorPart.getEditorInput();
                IFile file = input.getFile();
                project = file.getProject();
            }
        }
        return project;
    }

}
