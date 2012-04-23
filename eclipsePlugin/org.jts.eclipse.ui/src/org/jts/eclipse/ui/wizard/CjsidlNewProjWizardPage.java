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
package org.jts.eclipse.ui.wizard;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.IDialogPage;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;

/**
 * 
 * @author vnearing
 *
 */
public class CjsidlNewProjWizardPage extends WizardPage{

    private Text containerText;
    private Text fileText;

    
    private ISelection selection;
    /**
     * constructor.
     * @param selection
     */
    protected CjsidlNewProjWizardPage(ISelection selection) {
        super("wizardPage");
        setTitle("CJSIDL Project");
        setDescription("This wizard creates a new CJSIDL Project");
        this.selection = selection;
    }

    /**
     * @see IDialogPage#createControl(Composite)
     */
    @Override
    public void createControl(Composite parent) {
        final Composite container = new Composite(parent, SWT.NULL);
        GridLayout layout = new GridLayout();
        container.setLayout(layout);
        layout.numColumns = 3;
        layout.verticalSpacing = 9;
        Label label = new Label(container, SWT.NULL);
        label.setText("&Project folder name:");

        containerText = new Text(container, SWT.BORDER | SWT.SINGLE);
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        containerText.setLayoutData(gd);
        containerText.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                dialogChanged();
            }
        });

        Button button = new Button(container, SWT.PUSH);
        button.setText("Browse...");
        button.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                handleBrowse();
            }
        });
        
        label = new Label(container, SWT.SHADOW_ETCHED_IN);
        label.setText("");
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 3;
        label.setLayoutData(gd);
        
        label = new Label(container, SWT.NULL);
        label.setText("&Import JSIDL document:");
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 3;
        label.setLayoutData(gd);

        fileText = new Text(container, SWT.BORDER | SWT.SINGLE);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 2;
        fileText.setLayoutData(gd);
        fileText.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                dialogChanged();
            }
        });        
        
        // Add browse button.
        Button browse = new Button(container, SWT.PUSH);
        browse.setText("Browse...");
        gd = new GridData();
        browse.setLayoutData(gd);
        browse.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                DirectoryDialog dlg = new DirectoryDialog(container.getShell(), SWT.OPEN);
                String fn = dlg.open();
                if (fn != null) {                 
                    fileText.setText(fn);
                }
            }
        });
        
        
        dialogChanged();
        setControl(container);
    }


    /**
     * Uses the standard container selection dialog to choose the new value for
     * the container field.
     */

    private void handleBrowse() {
        ContainerSelectionDialog dialog = new ContainerSelectionDialog(
                getShell(), ResourcesPlugin.getWorkspace().getRoot(), false,
                "Select new file container");
        if (dialog.open() == ContainerSelectionDialog.OK) {
            Object[] result = dialog.getResult();
            if (result.length == 1) {
                containerText.setText(((Path) result[0]).toString());
            }
        }
    }

    /**
     * Handles errors as fields are updated.
     */
    private void dialogChanged() {
        IResource container = ResourcesPlugin.getWorkspace().getRoot()
                .findMember(new Path(getContainerName()));
        String fileName = getFileName();


        if (getContainerName().length() == 0) {
            updateStatus("File container must be specified");
            return;
        }
        if (container != null && container.getType() != 0) {
            updateStatus("File container already exists");
            return;
        }
        if (fileName.length() == 0) {            
            updateStatus(null);
            setPageComplete(true);
            return;
        }
        
        
        
//        int dotLoc = fileName.lastIndexOf('.');
//        if (dotLoc != -1) {
//            String ext = fileName.substring(dotLoc + 1);
//            if (!ext.equalsIgnoreCase("xml") && !ext.equalsIgnoreCase("jsidl")) {
//                updateStatus("File extension must be \"xml\" or \"jsidl\" ");
//                return;
//            }
//        } 
        
        if(fileText.getText() != null && !fileText.getText().equals(""))
        {
            //TODO: need validation function to determine if valid JSIDL folder.
            setPageComplete(true);
        }
        
        updateStatus(null);
    }

    /**
     * Adds error message to screen, and determines if page is finished.
     * @param message
     */
    private void updateStatus(String message) {
        setErrorMessage(message);
        setPageComplete(message == null);
    }

    /**
     * 
     * @return the project name
     */
    public String getContainerName() {
        return containerText.getText();
    }

    /**
     * 
     * @return the JSIDL file to import
     */
    public String getFileName() {
        return fileText.getText();
    }
}
