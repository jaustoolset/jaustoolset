package org.jts.eclipse.ui.actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ErrorDialog;
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
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.jts.eclipse.conversion.cjsidl.Conversion;
import org.jts.eclipse.conversion.cjsidl.ConversionException;
import org.jts.eclipse.ui.internal.CjsidlActivator;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class ConvertJsidlActionDelegate implements IWorkbenchWindowActionDelegate {
    private IWorkbenchWindow window;
    public static final String ID = "jts.JTS.menu.codegenerator.GenerateCodeActionDelegate";
    private Shell shell;    
    private IPath source;
    private String outputPath = "";
    private IProject proj;

    private ConversionDialog win;
    @Override
    public void run(IAction action) {
        win = new ConversionDialog(window.getShell());        
        boolean done = false;
        
        // Keep re-launching the window until it's correct.
        while (!done) {
            win.open();
//            source = win.getSource();
            outputPath = win.getDest();
            if(source == null && outputPath == null)
            {
                // Cancel was called.
                done = true;
            }else if(!validPath(outputPath))
            {
                MessageDialog.openInformation(shell, "Error converting code", "Illegal destination path. Please try again.");              
            }
            else{
                proj = getProjectName();
                source = proj.getLocation(); 
                if(!new File(outputPath).exists())
                {
                    new File(outputPath).mkdir();
                }
                convertFiles();
                done = true;
            }
        }
    }

    private boolean validPath(String path) {
        if(path != null && (path.contains(" ") || path.equals("")))
        {
            return false;
        }
        File tmp = new File(path);
        if(tmp.exists() && !tmp.isDirectory())
        {
        	return false;
        }
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
     * Actually converts the JSIDL files into CJSIDL.
     */
    private void convertFiles()
    {
        File cjsidlFolder = new File(source.toOSString());
    	Injector injector = Guice.createInjector(new org.jts.eclipse.CjsidlRuntimeModule());
    	// TODO: the next two lines cause refAttr to break in all files,
    	// but are required to, strangely enough, resolve those references.
    	// This is apparently an injector issue within eclipse and needs to be 
    	// investigated further.
		org.eclipse.xtext.resource.IResourceFactory resourceFactory = injector.getInstance(org.eclipse.xtext.resource.IResourceFactory.class);
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("csd", resourceFactory);

        Conversion conv = new Conversion(injector);
        XtextResourceSet set = conv.getResourceSet();
        
        try {
            conv.convertToJSIDL(cjsidlFolder.getCanonicalPath(), outputPath);
        } catch (FileNotFoundException e) {
          	IStatus status = new Status(IStatus.ERROR, "org.jts.eclipse", 1, e.getMessage(), e);
        	ErrorDialog.openError(shell, "Import Error", "Import Error", status);
              e.printStackTrace();
        } catch (ConversionException e) {
          	IStatus status = new Status(IStatus.ERROR, "org.jts.eclipse", 1, e.getMessage(), e);
        	ErrorDialog.openError(shell, "Import Error", "Import Error", status);
  			e.printStackTrace();
		} catch (IOException e) {
	      	IStatus status = new Status(IStatus.ERROR, "org.jts.eclipse", 1, e.getMessage(), e);
        	ErrorDialog.openError(shell, "Import Error", "Import Error", status);
  			e.printStackTrace();
		}
		
		MessageDialog.openWarning(this.shell, "Restart Recommended!", "Exporting JSIDL causes inheritance and other file references to \n" +
				"break.  This will also cause code generation and doc generation to fail.\n" +
				"Restarting Eclipse will fix this problem and is highly recommended.");

    }
    
    /**
     * Filters through the folders of a project to add all sub files to the set.
     * @param resource
     * @param set
     */
    private void recursiveGetResource(File dir, ResourceSet set) {
        URI uri;
        for(File f: dir.listFiles())
        {
            if(f.isDirectory())
            {
                recursiveGetResource(f, set);
            }
            if(f.getName().endsWith(".csd") || f.getName().endsWith(".cjsidl"))
            {
                uri = URI.createFileURI(f.getPath());
                set.createResource(uri);
                set.getResource(uri, true);
            }
        }
        
    }
    
    /**
     * Find the IProject information to collect project files.
     * @return the current project
     */
    private IProject getProjectName()
    {
        IProject project = null;
        ISelectionService selectionService =window.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
        ISelection selection = selectionService.getSelection();
        
        if(selection instanceof IStructuredSelection){
            // The project was selected in the explorer. We can pull the project info from the resource object.
            Object element = ((StructuredSelection)selection).getFirstElement();            
            if(element instanceof IResource) {
                project = ((IResource)element).getProject();
            }
            else{
                // Invalid selection or no selection.
                return null;
            }
        }
        else if(selection instanceof ITextSelection){
            // An editor window is selected. We can pull the project info from the editor.
            IEditorPart editorPart = window.getActivePage().getActiveEditor();
            if(editorPart!=null)
            {
                IFileEditorInput input = (IFileEditorInput)editorPart.getEditorInput();
                IFile file = input.getFile();
                project = file.getProject();
            }               
        }
        return project;
    }

}
