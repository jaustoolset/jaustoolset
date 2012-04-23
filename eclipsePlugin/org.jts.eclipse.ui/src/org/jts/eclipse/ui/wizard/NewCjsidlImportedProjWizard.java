package org.jts.eclipse.ui.wizard;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.ErrorDialog;
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
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.xtext.resource.SaveOptions;
import org.eclipse.xtext.resource.SaveOptions.Builder;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.ui.XtextProjectHelper;
import org.jts.eclipse.conversion.cjsidl.Conversion;
import org.jts.eclipse.conversion.cjsidl.ConversionException;
import org.jts.eclipse.ui.internal.CjsidlActivator;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class NewCjsidlImportedProjWizard extends Wizard implements INewWizard{

    private CjsidlNewProjWizardPage page;
    private ISelection selection;

    /**
     * Constructor.
     */
    public NewCjsidlImportedProjWizard() {
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
        // imported JSIDL file
        final String jsidlFile = page.getFileName();
        
       
        IRunnableWithProgress op = new IRunnableWithProgress() {
            public void run(IProgressMonitor monitor) throws InvocationTargetException {
                try {
                    doFinish(containerName, jsidlFile, monitor);
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
        String fileName,
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


        IContainer container = (IContainer) proj;

        final IFile file = container.getFile(new Path(fileName));
       
       
    	File location = new File(fileName);
    	java.util.List<File> fileList = new ArrayList<File>();
    	
    	if(location.isDirectory()){
    		fileList.addAll(FileUtils.listFiles(location, new SuffixFileFilter(new String[]{".xml"}), TrueFileFilter.INSTANCE));
    	} else if(location.isFile() && location.getName().endsWith(".xml")){
    		fileList.add(location);
    	} else{
    		// TODO: show error message
    	}
    	IPath projPath = proj.getFullPath().makeAbsolute();

    	Injector injector = Guice.createInjector(new org.jts.eclipse.CjsidlRuntimeModule());
    	
    	// TODO: the next two lines cause refAttr to break in all files,
    	// but are required to, strangely enough, resolve those references.
    	// This is apparently an injector issue within eclipse and needs to be 
    	// investigated further.
		org.eclipse.xtext.resource.IResourceFactory resourceFactory = injector.getInstance(org.eclipse.xtext.resource.IResourceFactory.class);
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("csd", resourceFactory);

		XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
		resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
		

		Conversion conv = new Conversion(injector);
		String outputPath = root.getLocation().toString() + "/" + containerName;
		
		try {
			File tmpfile = new File(outputPath);
			outputPath = tmpfile.getCanonicalPath();
		} catch (IOException e1) {
	      	IStatus status = new Status(IStatus.ERROR, "org.jts.eclipse", 1, e1.getMessage(), e1);
        	StatusManager.getManager().handle(status, StatusManager.SHOW|StatusManager.LOG);
        	ErrorDialog.openError(this.getShell(), "Import Error", "Import Error", status);
  			e1.printStackTrace();
		}
		try {
			resourceSet = conv.convertFromJSIDL(fileList, outputPath, resourceSet);
		} catch (ConversionException e1) {
	      	IStatus status = new Status(IStatus.ERROR, "org.jts.eclipse", 1, e1.getMessage(), e1);
        	StatusManager.getManager().handle(status, StatusManager.SHOW|StatusManager.LOG);
  			e1.printStackTrace();
        	ErrorDialog.openError(this.getShell(), "Import Error", "Import Error", status);
		}
		for(Resource resource: resourceSet.getResources()){
		try {
				String tmpstr[] = resource.getURI().toString().split("[/]");
				String tmpname = tmpstr[tmpstr.length-1];
				ByteArrayOutputStream  outputStream = new java.io.ByteArrayOutputStream();
				Map<Object, Object> map = new HashMap<Object, Object>();
				Builder builder = SaveOptions.newBuilder();
				builder.format();
				SaveOptions options = builder.getOptions();
				options.addTo(map);
				try {
					if(resource.getContents().size() != 1){
						throw new ConversionException(resource.getURI() + " could not be saved, due to an " +
								"unknown conversion failure.");
					} else{
						resource.save(outputStream, map);
					}
				} catch (Exception e) {
			      	IStatus status = new Status(IStatus.ERROR, "org.jts.eclipse", 1, e.getMessage(), e);
		        	StatusManager.getManager().handle(status, StatusManager.SHOW|StatusManager.LOG);
		        	ErrorDialog.openError(this.getShell(), "Import Error", "Import Error", status);
		  			e.printStackTrace();
				}
				
				final IFile tmpfile = container.getFile(new Path(tmpname));					
	            try {
	                InputStream stream = new ByteArrayInputStream(outputStream.toByteArray());
	                if (tmpfile.exists()) {
	                	tmpfile.setContents(stream, true, true, monitor);
	                } else {
	                	tmpfile.create(stream, true, monitor);
	                }
	                stream.close();
	            } catch (IOException e) {
	              	IStatus status = new Status(IStatus.ERROR, "org.jts.eclipse", 1, e.getMessage(), e);
	            	StatusManager.getManager().handle(status, StatusManager.SHOW|StatusManager.LOG);	            
		        	ErrorDialog.openError(this.getShell(), "Import Error", "Import Error", status);
	            }
	            monitor.worked(1);
	            monitor.setTaskName("Opening file for editing...");
	            getShell().getDisplay().asyncExec(new Runnable() {
	                public void run() {
	                    IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	                    try {
	                        IDE.openEditor(page, tmpfile, true);
	                    } catch (PartInitException e) {
	                    }
	                }
	            });

			} catch (Exception e) {
		      	IStatus status = new Status(IStatus.ERROR, "org.jts.eclipse", 1, e.getMessage(), e);
	        	StatusManager.getManager().handle(status, StatusManager.SHOW|StatusManager.LOG);
	        	ErrorDialog.openError(this.getShell(), "Import Error", "Import Error", status);
				e.printStackTrace();
			}
		}
		MessageDialog.openWarning(this.getShell(), "Restart Recommended!", "Importing JSIDL causes inheritance and other file references to \n" +
				"break.  This will also cause code generation and doc generation to fail.\n" +
				"Restarting Eclipse will fix this problem and is highly recommended.");
		monitor.worked(1);
    }
    
    /**
     * Adding the pages to the wizard.
     */
    public void addPages() {
        page = new CjsidlNewProjWizardPage(selection);
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
