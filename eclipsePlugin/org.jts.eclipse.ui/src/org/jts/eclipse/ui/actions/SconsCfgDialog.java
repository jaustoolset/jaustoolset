package org.jts.eclipse.ui.actions;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SconsCfgDialog extends Dialog{
    private String sconsPath;

    protected SconsCfgDialog(Shell parentShell) {
        super(parentShell);
        sconsPath = "";
    }
    
    public String getPath()
    {
        return sconsPath;
    }
    
    public int open()
    {
        Shell shell = new Shell(getParentShell(), getShellStyle());
        shell.setText("Configure scons");
        createContents(shell);
        shell.pack();
        shell.open();
        Display display = getParentShell().getDisplay();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        return 0;
    }
    
    private void createContents(final Shell shell) {
        Label path = new Label(shell, SWT.NONE);
        final Text pathText = new Text(shell, SWT.BORDER);
        final Button browse = new Button(shell, SWT.PUSH);
        Button ok = new Button(shell, SWT.PUSH);
        Button cancel = new Button(shell, SWT.PUSH);
        
     // Set up layout
        int numColumns = 5;
        GridLayout layout = new GridLayout();
        layout.numColumns = numColumns;
        shell.setLayout(layout);

        
        // Show the browse text:
        path.setText("Scons program:");
        GridData data = new GridData();
        data.horizontalSpan = 5;
        path.setLayoutData(data);

        // Display the input box
        data = new GridData(GridData.FILL_HORIZONTAL);
        data.horizontalSpan = 4;
        pathText.setText(sconsPath);
        pathText.setLayoutData(data);

        // Add browse button.
        browse.setText("Browse...");
        data = new GridData();
        data.horizontalSpan = 1;
        browse.setLayoutData(data);
        browse.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                FileDialog dlg = new FileDialog(shell, SWT.OPEN);
                String fn = dlg.open();
                if (fn != null) {                 
                    pathText.setText(fn);
                }
            }
        });
     // Add OK button.
        ok.setText("Continue");
        data = new GridData(GridData.FILL_HORIZONTAL);
        data.horizontalSpan = 1;
        ok.setLayoutData(data);
        ok.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                sconsPath = pathText.getText();
                shell.close();
            }
        });

        // Add Cancel button.
        cancel.setText("Cancel");
        data = new GridData(GridData.FILL_HORIZONTAL);
        data.horizontalSpan = 1;
        cancel.setLayoutData(data);
        cancel.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                sconsPath = null;
                shell.close();
            }
        });
    }

}
