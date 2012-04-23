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
 * Provides the GUI for retrieving code generator information from the user.
 * 
 */

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class CodeGenDialog extends Dialog {

	private String path;
	private String name;
	private String id;
	private String codeType;
	private LanguageGroup lg;
	private boolean generate = false; // default to false

	/**
	 * Set default parameters
	 * @param parentShell
	 */
	public CodeGenDialog(Shell parentShell) {
		super(parentShell);
		path = "";
		name = "";
		id = "";
		codeType = "C++";
		lg = null;
	}

	/**
	 * 
	 * @return stored component ID
	 */
	public String getID() {
		return id;
	}

	/**
	 * 
	 * @return stored component name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return the output path for the generated code
	 */
	public String getPath() {
		return path;
	}

	/**
	 * 
	 * @return the type of code to be generated
	 */
	public String getCodeType() {
		return codeType;
	}
	
	/**
	 * 
	 * @return if an eclipse project should be generated.
	 */
	public boolean getEclipseProj(){
	    return generate;
	}

	/**
	 * Open the dialog and return the input.
	 */
	public int open() {
		Shell shell = new Shell(getParentShell(), getShellStyle());
		shell.setText("Generate Code...");
		createContents(shell);
		shell.pack();
		shell.open();
		Display display = getParentShell().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		// Error checking - if all strings are legal return, else - error and
		// cancel close.
		try {
			return Integer.parseInt(id);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * Creates the dialog's contents.
	 * 
	 * @param shell
	 *            the dialog window
	 */
	private void createContents(final Shell shell) {
		
		// These objects appear in the dialog in the order
		// they are defined here.
		// Row 1
		Label cmptName = new Label(shell, SWT.NONE);
		Label cmptID = new Label(shell, SWT.NONE);
		
		// Row 2
		final Text cmptNameText = new Text(shell, SWT.BORDER);
		final Text cmptIDText = new Text(shell, SWT.BORDER);
		
		// Row 3
		Label cmptPath = new Label(shell, SWT.NONE);
		
		// Row 4
		final Text cmptPathText = new Text(shell, SWT.BORDER);
		final Button browse = new Button(shell, SWT.PUSH);
		
		// Row 5
		lg = new LanguageGroup(shell, SWT.SHADOW_ETCHED_IN);
		lg.setLocation(shell.getSize());
		final Button eclipseProj = new Button(shell, SWT.CHECK);
		
		// Row 6
		Label spacer = new Label(shell, SWT.NONE);
		Button ok = new Button(shell, SWT.PUSH);
		Button cancel = new Button(shell, SWT.PUSH);
		
		
		

		// Set up layout
		int numColumns = 5;
		GridLayout layout = new GridLayout();
		layout.numColumns = numColumns;
		shell.setLayout(layout);

		// Show the Component text:
		cmptName.setText("Component Name:");
		GridData data = new GridData();
		data.horizontalSpan = 3;
		cmptName.setLayoutData(data);

		// Show the ID text:
		cmptID.setText("Component ID:");
		data = new GridData();
		data.horizontalSpan = 2;
		cmptID.setLayoutData(data);

		// Display the name input box
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 3;
		cmptNameText.setText(name);
		cmptNameText.setLayoutData(data);

		// Display the ID input box
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		cmptIDText.setText(id);
		cmptIDText.setLayoutData(data);

		// Show the browse text:
		cmptPath.setText("Output Path:");
		data = new GridData();
		data.horizontalSpan = 5;
		cmptPath.setLayoutData(data);

		// Display the input box
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 4;
		cmptPathText.setText(path);
		cmptPathText.setLayoutData(data);

		// Add browse button.
		browse.setText("Browse...");
		data = new GridData();
		data.horizontalSpan = 1;
		browse.setLayoutData(data);
		browse.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				DirectoryDialog dlg = new DirectoryDialog(shell, SWT.OPEN);
		        String fn = dlg.open();
		        if (fn != null) {		          
					cmptPathText.setText(fn);
				}
			}
		});
		// Add tick box for eclipse project
		eclipseProj.setText("Generate Eclipse Project");
        data = new GridData(GridData.FILL_HORIZONTAL);
        data.horizontalSpan=3;
        eclipseProj.setLayoutData(data);

        // Add spacer for buttons.
        spacer.setText("");
        data = new GridData(GridData.FILL_HORIZONTAL);
        data.horizontalSpan = 3;
        spacer.setLayoutData(data);
        
		// Add OK button.
		ok.setText("Generate...");
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 1;
		ok.setLayoutData(data);
		ok.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				id = cmptIDText.getText();
				name = cmptNameText.getText();
				path = cmptPathText.getText();
				codeType = lg.getSelected();
				generate = eclipseProj.getSelection();
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
				id = null;
				name = null;
				path = null;
				codeType = null;
				generate = false;
				shell.close();
			}
		});
		
		
	}

	class LanguageGroup extends Composite {

		final Button b1;

		final Button b2;

		final Button b3;

		public LanguageGroup(Composite c, int style) {
			super(c, SWT.NO_BACKGROUND);
			this.setSize(110, 75);
			this.setLayout(new FillLayout());
			final Group g = new Group(this, style);
			g.setSize(110, 75);
			g.setText("Select Code Type:");
			b1 = new Button(g, SWT.RADIO);
			b1.setBounds(10, 20, 75, 15);
			b1.setText("C++");
			b1.setSelection(true);
			b2 = new Button(g, SWT.RADIO);
			b2.setBounds(10, 35, 75, 15);
			b2.setText("Java");
			b3 = new Button(g, SWT.RADIO);
			b3.setBounds(10, 50, 80, 15);
			b3.setText("C#");
		}

		public String getSelected() {
			if (b1.getSelection())
				return "C++";
			if (b2.getSelection())
				return "Java";
			if (b3.getSelection())
				return "C#";
			return "C++";
		}
	}

}
