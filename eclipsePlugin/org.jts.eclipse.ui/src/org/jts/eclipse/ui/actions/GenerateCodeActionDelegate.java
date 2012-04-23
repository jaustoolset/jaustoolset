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
 * Collects information needed to generate code and calls the codegenerator
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.launching.JavaRuntime;
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
import org.jts.codegenerator.CodeLines;
import org.jts.codegenerator.ComponentGenerator;
import org.jts.codegenerator.Util;
import org.jts.codegenerator.support.TemplateHandler;
import org.jts.eclipse.ui.actions.importbinding.ImportServiceSet;
import org.jts.jsidl.binding.ServiceDef;
import org.jts.jsidl.binding.ServiceSet;

public class GenerateCodeActionDelegate implements
		IWorkbenchWindowActionDelegate {

	private IWorkbenchWindow window;
	public static final String ID = "jts.JTS.menu.codegenerator.GenerateCodeActionDelegate";
	private static String SCONS_CFG_LOC = "plugins/org.jts.eclipse.data_1.0/scons.cfg";
	private String sconsPath;
	private Shell shell;	
	private String componentName = "NAME";
	private String componentID = "ID";
	private String codeType = "";
	private IPath sourcePath;
	private String outpath = "";
	private CodeGenDialog win;

	@Override
	public void run(IAction action) {
		
		win = new CodeGenDialog(window.getShell());
		boolean done = false;
		
		// Keep re-launching the window until it's correct.
		while (!done) {
			launch();
			
			if (componentName == null && outpath == null && codeType == null && componentID == null) {
				// Code generator canceled, do nothing.
				done = true;
			} else if (!validName()) {
				MessageDialog.openInformation(shell, "Error generating code...", "Invalid Component name. Please try again.");				
			} else if(!validOutpath())
			{
				MessageDialog.openInformation(shell, "Error generating code...", "Invalid outpath. Please try again.");	
			}else if(!validID())
			{
				MessageDialog.openInformation(shell, "Error generating code...", "Invalid Component ID. Please try again.");	
			}
			else {				
				IProject project = getProjectName();
				
				if(project == null)
				{
					MessageDialog.openInformation(shell, "Error generating code...", "Please select a valid project.");					
				}else{
					sourcePath = project.getLocation();	
					ComponentGenerator cg = null;
					if(codeType.equals("C++"))
					{
						cg = new ComponentGenerator(CodeLines.CodeType.C_PLUS_PLUS, CodeLines.BuildType.SCONS);
					}
					else if(codeType.equals("Java"))
					{
						cg = new ComponentGenerator(CodeLines.CodeType.JAVA, CodeLines.BuildType.SCONS);
					}
					else if(codeType.equals("C#"))
					{
						cg = new ComponentGenerator(CodeLines.CodeType.C_SHARP, CodeLines.BuildType.SCONS);
					}
					
					ImportServiceSet importSS = new ImportServiceSet(componentName, sourcePath, project, window.getShell());
					
					
					try
					{
						ServiceSet ss = importSS.getServiceSet();
						cg.run(outpath, componentName, componentID, ss);
					}
					catch (Exception e)
					{
						String s= e.getMessage();

						MessageDialog.openInformation(shell, "Error", "There was an error generating your code: \n" + s);// Please see the console for detailed information.");
						e.printStackTrace();
						
					}													
					
					if(win.getEclipseProj())
					{
					    compileGeneratedCode();
					    createProject();
					}
				}
				MessageDialog.openInformation(shell, "Done", "Code generation complete.");
				MessageDialog.openWarning(shell, "Restart Recommended!", "Code generation converts CJSIDL to JSIDL and causes inheritance and other file references to \n" +
						"break.  This will also cause future code generation and doc generation to fail.\n" +
						"Restarting Eclipse will fix this problem and is highly recommended.");
				done = true;
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
	/**
	 * The sequence for launching the dialog and retrieving the data from it.
	 */
	private void launch() {
		win.open();
		componentName = win.getName();
		componentID = win.getID();
		outpath = win.getPath();
		codeType = win.getCodeType();
	}
	
	/**
	 * @return if output path is valid.
	 */
	private boolean validOutpath()
	{
		if(outpath.contains(" "))
		{
			// Path cannot contain spaces.
			return false;
		}
		if(outpath.equals(""))
		{
			// Empty path.
			return false;
		}
		return true;
	}

	/**
	 * @return if component has a valid name
	 */
	private boolean validName()
	{
		if(componentName == null || componentName.equals(" ") || componentName.equals("") || componentName.contains(" "))
		{
			return false;
		}
		
		return true;
	}
	/**
	 * @return if the component is valid
	 */
	private boolean validID()
	{
		int ID = 0;
		try
		{
			ID = Integer.parseInt(componentID);
		}
		catch(Exception e)
		{
			return false;
		}
		// ID must fit within a byte.
		if(ID > 255 || ID <1)
		{
			return false;
		}
		return true;
		
	}
	
	// Gets the files from the project and packages them as 
	private List<File> getProjectFiles()
	{
		List<File> files = new ArrayList<File>();
		
		return files;
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
	 * Compile generated code so we can create an Eclipse project.
	 */
	private void compileGeneratedCode()
 {
	    
        String cmd = getSconsLoc();//"C:\\Python27\\Scripts\\scons.bat";      
        sconsPath = cmd;
        File workingDir = new File(outpath + "/" + componentName.substring(0, 1).toUpperCase() + componentName.substring(1) + "_" + componentID);
        try {
            // runs "scons" in the directory outpath, with the system environment (null)
            Process p = Runtime.getRuntime().exec(cmd, null, workingDir);
            StreamGobbler errors = new StreamGobbler(p.getErrorStream(), "ERROR");
            StreamGobbler output = new StreamGobbler(p.getInputStream(), "OUTPUT");
            errors.start();
            output.start();
            p.waitFor();

        } catch (Exception exc) {
            exc.printStackTrace();
        }
	}
	
	/**
	 * 
	 */
	private void createProject()
	{
	    IProgressMonitor monitor = new NullProgressMonitor();
	    IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
	    IProject proj = root.getProject(componentName + "_" + componentID);
	    IJavaProject javaProj;
	    try {
            proj.create(monitor);
            addFiles(proj);
            if(codeType.equalsIgnoreCase("java"))
            {
                proj.open(monitor);
                
                addJavaNature(proj);
                javaProj = JavaCore.create(proj);
                setUpJRE(javaProj);
                handleBuildScripts(javaProj);
                javaProj.open(monitor);
            }else
            {
                proj.open(monitor);
            }
//            addFiles(proj);
        } catch (CoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    
	}
	
	/**
	 * Add all of the files from the output folder to the project.
	 * @param proj
	 */
	private void addFiles(IProject proj)
	{

	    File destDir = new File(proj.getLocation().toPortableString());
	    File srcDir = new File(outpath +"/" + componentName + "_" + componentID);
	    try {
            FileUtils.copyDirectory(srcDir, destDir);
            proj.refreshLocal(IResource.DEPTH_INFINITE, null);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (CoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    
	}	

    /**
     * Takes the generated project description from the new project, and adds
     *  the xtext nature to it.
     * Code taken from http://cvalcarcel.wordpress.com/2009/07/26/writing-an-eclipse-plug-in-part-4-create-a-custom-project-in-eclipse-new-project-wizard-the-behavior/
     * @param project
     * @throws CoreException
     */
    private void addJavaNature(IProject project) throws CoreException {
        if (!project.hasNature(JavaCore.NATURE_ID)) {
            IProjectDescription description = project.getDescription();
            String[] prevNatures = description.getNatureIds();
            String[] newNatures = new String[prevNatures.length + 1];
            System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
            newNatures[prevNatures.length] = JavaCore.NATURE_ID;
            description.setNatureIds(newNatures);

            IProgressMonitor monitor = null;
            project.setDescription(description, monitor);
        }
    }
    
    /**
     * Make sure Java Runtime Environment is set up correctly.
     * @param proj
     */
    private void setUpJRE(IJavaProject proj)
    {
        try {
            Set entries = new HashSet();
            IPath libPath = proj.getProject().getFile("lib/statemap.jar").getFullPath();
            entries.addAll(Arrays.asList(proj.getRawClasspath()));
            entries.add(JavaRuntime.getDefaultJREContainerEntry());
            entries.add(JavaCore.newLibraryEntry(libPath, null, null));
           
            IClasspathEntry[] classpath = (IClasspathEntry[]) entries.toArray(new IClasspathEntry[entries.size()]);
            proj.setRawClasspath(classpath, new NullProgressMonitor());
        } catch (JavaModelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private String getSconsLoc()
    {
        String ret = null;
        // Check existing file.
        try {
            //InputStream in = new BufferedInputStream(new FileInputStream(new File(SCONS_CFG_LOC)));
        	File sconsCfg = new File(SCONS_CFG_LOC);
            if(!sconsCfg.exists())
            {
            	sconsCfg.createNewFile();
            }
            BufferedReader in = new BufferedReader(new FileReader(SCONS_CFG_LOC));
            while((ret = in.readLine()) != null)
            {
                // Is it a path? 
                in.close();
                return ret;
            }
            
            // This code will only be called if the file was empty.
            in.close();
            SconsCfgDialog dialog = new SconsCfgDialog(window.getShell());
            dialog.open();
            ret = dialog.getPath();
            
            if(ret == null)
            {
                // Action was canceled. Return without updating file.
                return ret;
            }
            BufferedWriter out = new BufferedWriter(new FileWriter(SCONS_CFG_LOC));
            out.write(ret);
            out.close();           
            
            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        return ret;
    }
    
    public void handleBuildScripts(IJavaProject proj)
    {        
        Hashtable<String, String> replaceTable = new Hashtable<String,String>();
        replaceTable.put("%project_name%", componentName + "_" + componentID);
        replaceTable.put("%scons_loc%", sconsPath);
        TemplateHandler tplHandler = new TemplateHandler(replaceTable);
        File template;
        int tmplFileExtn = 4;
        int tmplWinFileExtn = 4;
        String destFileName;
        if(System.getProperty("os.name").contains("Windows"))
        {
            template = new File("plugins/org.jts.eclipse.data_1.0/templates/eclipseProj/build.xml.tpl.win");
            destFileName = tplHandler.adjustString(template.getName().substring(0, template.getName().length() - tmplFileExtn - tmplWinFileExtn));
            
        }else
        {
            template = new File("plugins/org.jts.eclipse.data_1.0/templates/eclipseProj/build.xml.tpl");
            destFileName = tplHandler.adjustString(template.getName().substring(0, template.getName().length() - tmplFileExtn));
        }
        File dest = new File(proj.getProject().getLocation().toPortableString() + "/" + destFileName);
        
        
        try {
            Util.copyFile(template, dest);
            tplHandler.adjustFile(dest);
            copyBuilders(proj.getProject());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }        
        
        addBuilder(proj.getProject(), "sconsBuilder.launch");
    }
    
    public void copyBuilders(IProject proj) throws Exception
    {
        Hashtable<String, String> replaceTable = new Hashtable<String,String>();
        replaceTable.put("%project_name%", componentName + "_" + componentID);
        TemplateHandler tplHandler = new TemplateHandler(replaceTable);
        File src = new File("plugins/org.jts.eclipse.data_1.0/templates/eclipseProj/sconsBuilder.launch");
        
        //Need folder .externalToolBuilders
        IFolder externalTools = proj.getFolder(".externalToolBuilders");
        if(!externalTools.exists())
        {
            externalTools.create(true, true, null);
        }
        // Copy sconsBuilder.launch and add project name.
        File dest = new File(externalTools.getLocation().toPortableString() + "/" + src.getName());
        FileUtils.copyFile(src, dest);
        tplHandler.adjustFile(dest);
        
        //Copy the java builder information - this disables
//        src = new File("resources/projGenerator/org.eclipse.jdt.core.javabuilder.launch");
//        dest = new File(externalTools.getLocation().toPortableString() + "/" + src.getName());
//        FileUtils.copyFile(src, dest);
        
        proj.refreshLocal(IResource.DEPTH_INFINITE, null);
        
    }
    public void addBuilder(IProject proj, String id)
    {
        IProjectDescription desc;
        try {
            desc = proj.getDescription();
            ICommand[] cmds = desc.getBuildSpec();
            for(ICommand cmd: cmds)
            {
                if(cmd.getBuilderName().equals(id))
                {
                    return;
                }                
            }
            ICommand cmd = desc.newCommand();
            cmd.setBuilderName(id);
            Map<String,String> map = cmd.getArguments();
            map.put("LaunchConfigHandle",
            "<project>/.externalToolBuilders/"+id);
            cmd.setArguments(map);
            cmd.setBuilding(IncrementalProjectBuilder.FULL_BUILD, true);
            cmd.setBuilding(IncrementalProjectBuilder.INCREMENTAL_BUILD, true);
            ICommand[] newCmds = new ICommand[cmds.length +1];
            System.arraycopy(cmds, 0, newCmds, 1, cmds.length);
            newCmds[0] = cmd;
            desc.setBuildSpec(newCmds);
            proj.setDescription(desc, null);
        } catch (CoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * 
     * @author vnearing
     *
     */
    class StreamGobbler extends Thread
    {
        InputStream is;
        String type;
        
        StreamGobbler(InputStream is, String type)
        {
            this.is = is;
            this.type = type;
        }
        
        public void run()
        {
            try
            {
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String line=null;
                while ( (line = br.readLine()) != null)
                    System.out.println(type + ">" + line);    
                } catch (IOException ioe)
                  {
                    ioe.printStackTrace();  
                  }
        }
    }

}
