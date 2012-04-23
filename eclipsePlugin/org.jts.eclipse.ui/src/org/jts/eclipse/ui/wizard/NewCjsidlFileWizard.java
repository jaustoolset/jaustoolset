package org.jts.eclipse.ui.wizard;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
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

public class NewCjsidlFileWizard extends Wizard implements INewWizard{

    private CjsidlNewFileWizardPage page;
    private ISelection selection;
    
    private String serviceName;
    private String URI;
    private String versionNum;
    private String fileType;

    /**
     * Constructor
     */
    public NewCjsidlFileWizard() {
        super();
        setNeedsProgressMonitor(true);
    }

    /**
     * @param workbench - the current workbench
     * @param selection - the selection handler
     */
    @Override
    public void init(IWorkbench workbench, IStructuredSelection selection) {
       this.selection = selection;

    }

    /**
     * performs all of the actions required once finish has been hit on the dialog.
     * Based off of code from the multi-page example included in the Eclipse Plug-In Development Environment
     *  
     * @return if finished correctly.
     */
    @Override
    public boolean performFinish() {
        final String containerName = page.getContainerName();
        final String fileName = page.getFileName();
        
        fileType = page.getSetType();
        versionNum = page.getVersionNum();
        URI = page.getURI();
        serviceName = page.getServiceName();
        
        IRunnableWithProgress op = new IRunnableWithProgress() {
            public void run(IProgressMonitor monitor) throws InvocationTargetException {
                try {
                    doFinish(containerName, fileName, monitor);
                } catch (CoreException e) {
                    throw new InvocationTargetException(e);
                } finally {
                    monitor.done();
                }
            }
        };
        try {
            getContainer().run(true, false, op);
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
     * The worker method. It will find the container, create the
     * file if missing or just replace its contents, and open
     * the editor on the newly created file.
     * 
     * Based off of the Multi-Page example included with the Eclipse Plug-in Development Environment.
     */

    private void doFinish(
        String containerName,
        String fileName,
        IProgressMonitor monitor)
        throws CoreException {
        // create a sample file
        monitor.beginTask("Creating " + fileName, 2);
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IResource resource = root.findMember(new Path(containerName));
        if (!resource.exists() || !(resource instanceof IContainer)) {
            throwCoreException("Container \"" + containerName + "\" does not exist.");
        }
        IContainer container = (IContainer) resource;
        final IFile file = container.getFile(new Path(fileName));
        try {
            InputStream stream = openContentStream();
            if (file.exists()) {
                file.setContents(stream, true, true, monitor);
            } else {
                file.create(stream, true, monitor);
            }
            stream.close();
        } catch (IOException e) {
        }
        monitor.worked(1);
        monitor.setTaskName("Opening file for editing...");
        getShell().getDisplay().asyncExec(new Runnable() {
            public void run() {
                IWorkbenchPage page =
                    PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                try {
                    IDE.openEditor(page, file, true);
                } catch (PartInitException e) {
                }
            }
        });
        monitor.worked(1);
    }
    
    /**
     * Initialize file contents with a sample text.
     * @return serialized contents of file.
     */
    private InputStream openContentStream() {
        
        String contents = "";
        if(fileType.equalsIgnoreCase("service"))
        {
            contents = "service " + serviceName + " (id=" + URI+", version=" + versionNum + "){\n //This is a generated stub file\n " + 
            "\tdescription \"\"; "+
            "\n\tassumptions \"\"; "+
            "\n\tmessages{"+
            "\n\t\tinput{}"+
            "\n\t\toutput{}"+
            "\n\t }"+
            "\n\t eventset{}"+
            "\n\t protocol{" + 
            "\n\t\tstate_machine stateMachine (start StartState){"+
            "\n\t\t\tstate StartState{}"+
            "\n\t\t}\n\t}\n}";
        }
        if(fileType.equalsIgnoreCase("type"))
        {
            contents = "typeset " + serviceName + " (id=" + URI+", version=" + versionNum + "){\n\n //This is a generated stub file\n }";
        }
        if(fileType.equalsIgnoreCase("const"))
        {
            contents = "constants " + serviceName + " (id=" + URI+", version=" + versionNum + "){\n\n //This is a generated stub file\n}";
        }
        return new ByteArrayInputStream(contents.getBytes());
    }

    /**
     * Prompts error pop up dialog.
     * @param message
     * @throws CoreException
     */
    private void throwCoreException(String message) throws CoreException {
        IStatus status =
            new Status(IStatus.ERROR, "CjsidlEditor", IStatus.OK, message, null);
        throw new CoreException(status);
    }
    
    /**
     * Adding the page to the wizard.
     */
    public void addPages() {
        page = new CjsidlNewFileWizardPage(selection);
        addPage(page);
    }
}
