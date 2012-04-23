package org.jts.eclipse.ui.wizard;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.xtext.ui.XtextProjectHelper;

public class NewCjsidlEmptyProjWizard extends Wizard implements INewWizard{

    private CjsidlNewEmptyProjWizardPage page;
    private ISelection selection;
    
    /**
     * Constructor.
     */
    public NewCjsidlEmptyProjWizard() {
        super();
        setNeedsProgressMonitor(true);
    }

    /**
     * Initialization
     * @param workbench - the current workbench.
     * @param selection - the selection handler.
     */
    @Override
    public void init(IWorkbench workbench, IStructuredSelection selection) {
       this.selection = selection;

    }

    /**
     * Called when the finish button is selected.
     * Based off of Multi-Page example included in the Eclipse Plug-in Develpment Environment.
     * 
     * @return if finished without errors.
     */
    @Override
    public boolean performFinish() {
        // New project name.
        final String containerName = page.getContainerName();
        
        
        IRunnableWithProgress op = new IRunnableWithProgress() {
            public void run(IProgressMonitor monitor) throws InvocationTargetException {
                try {
                    doFinish(containerName, monitor);
                } catch (CoreException e) {
                    throw new InvocationTargetException(e);
                } finally {
                    monitor.done();
                }
            }
        };
        
        // Run the finishing thread.
        try {
            
            op.run(new NullProgressMonitor());
            
        } catch (InterruptedException e) {
            return false;
        } catch (InvocationTargetException e) {
            Throwable realException = e.getTargetException();
            MessageDialog.openError(getShell(), "Error", realException.getMessage());
            return false;
        }
        return true;
    }
    
    /**
     * The worker method. It will create a new project and open it.
     * 
     * If a JSIDL document was given, the CJSIDL converter will be called.
     *  Otherwise, a new file will be created with the data from the second page.
     * 
     */

    private void doFinish(
        String containerName,
        IProgressMonitor monitor)
        throws CoreException {
        monitor.beginTask("Creating new CJSIDL project", 2);
        // Create the project file.
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IProject proj = null;

        proj = root.getProject(containerName);
        proj.create(monitor);        
        
        proj.open(monitor);
        addNature(proj);
        
        monitor.worked(1);
    }
    
    
    /**
     * Adding the pages to the wizard.
     */
    public void addPages() {
        page = new CjsidlNewEmptyProjWizardPage(selection);
        addPage(page);
    }
    
    /**
     * Takes the generated project description from the new project, and adds
     *  the xtext nature to it.
     * Code taken from http://cvalcarcel.wordpress.com/2009/07/26/writing-an-eclipse-plug-in-part-4-create-a-custom-project-in-eclipse-new-project-wizard-the-behavior/
     * @param project
     * @throws CoreException
     */
    private static void addNature(IProject project) throws CoreException {
        if (!project.hasNature(XtextProjectHelper.NATURE_ID)) {
            IProjectDescription description = project.getDescription();
            String[] prevNatures = description.getNatureIds();
            String[] newNatures = new String[prevNatures.length + 1];
            System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
            newNatures[prevNatures.length] = XtextProjectHelper.NATURE_ID;
            description.setNatureIds(newNatures);

            IProgressMonitor monitor = null;
            project.setDescription(description, monitor);
        }
    }

}
