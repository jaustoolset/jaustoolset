package org.jts.eclipse.ui.actions;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;

public class ConversionDialog extends Dialog{
//    private Text source;
    private Text output;
//    private String src;
    private String dst;

    protected ConversionDialog(Shell parentShell) {
        super(parentShell);
        // TODO Auto-generated constructor stub
    }

    /**
     * Open the dialog and return the input.
     */
    public int open() {
        Shell shell = new Shell(getParentShell(), getShellStyle());
        shell.setText("Export to JSIDL...");
        createContents(shell);
        shell.pack();
        shell.open();
        Display display = getParentShell().getDisplay();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        return 1;
    }
    
//    public String getSource()
//    {
//        return src;
//    }
//    
    public String getDest()
    {
        return dst;
    }
    
    /**
     * Creates the dialog's contents.
     * 
     * @param shell
     *            the dialog window
     */
    private void createContents(final Shell shell) {               

        GridLayout layout = new GridLayout();
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        
        shell.setLayout(layout);
        layout.numColumns = 3;
        layout.verticalSpacing = 9;
        Label label =  new Label(shell, SWT.SHADOW_ETCHED_IN);
//        label.setText("&Project folder name:");
//        gd = new GridData(GridData.FILL_HORIZONTAL);
//        gd.horizontalSpan = 3;
//        label.setLayoutData(gd);
//        
//        
//        source = new Text(shell, SWT.BORDER | SWT.SINGLE);
//        gd = new GridData(GridData.FILL_HORIZONTAL);
//        gd.horizontalSpan = 2;
//        source.setLayoutData(gd);
//
//
//        Button button = new Button(shell, SWT.PUSH);
//        button.setText("Browse...");
//        button.addSelectionListener(new SelectionAdapter() {
//            public void widgetSelected(SelectionEvent e) {
//                handleBrowse(source);
//            }
//        });
//        
//        label = new Label(shell, SWT.SHADOW_ETCHED_IN);
//        label.setText("");
//        gd = new GridData(GridData.FILL_HORIZONTAL);
//        gd.horizontalSpan = 3;
//        label.setLayoutData(gd);
        
        label = new Label(shell, SWT.NULL);
        label.setText("&Export to folder:");
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 3;
        label.setLayoutData(gd);

        output = new Text(shell, SWT.BORDER | SWT.SINGLE);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 2;
        output.setLayoutData(gd);
//        output.addModifyListener(new ModifyListener() {
//            public void modifyText(ModifyEvent e) {
//                dialogChanged();
//            }
//        });        
        
        // Add browse button.
        Button browse = new Button(shell, SWT.PUSH);
        browse.setText("Browse...");
        gd = new GridData();
        browse.setLayoutData(gd);
        browse.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                DirectoryDialog dlg = new DirectoryDialog(shell.getShell(), SWT.OPEN);
                String fn = dlg.open();
                if (fn != null) {                 
                    output.setText(fn);
                }
            }
        });

        Button ok = new Button(shell, SWT.PUSH);
        Button cancel = new Button(shell, SWT.PUSH);
        
        // Add OK button.
        ok.setText("Export...");
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 1;
        ok.setLayoutData(gd);
        ok.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                dst = output.getText();
                //src = source.getText();
                shell.close();
            }
        });

        // Add Cancel button.
        cancel.setText("Cancel");
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 1;
        cancel.setLayoutData(gd);
        cancel.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                dst = null;
                //src = null;                
                shell.close();
            }
        });

        // Create the OK button and a handler so that pressing it will
        // capture the data in the fields.
    }
    
    /**
     * Uses the standard container selection dialog to choose the new value for
     * the container field.
     */

    private void handleBrowse(Text toChange) {
        ContainerSelectionDialog dialog = new ContainerSelectionDialog(
                getShell(), ResourcesPlugin.getWorkspace().getRoot(), false,
                "Select new file container");
        if (dialog.open() == ContainerSelectionDialog.OK) {
            Object[] result = dialog.getResult();
            if (result.length == 1) {
                toChange.setText(((Path) result[0]).toString());
            }
        }
    }
}
