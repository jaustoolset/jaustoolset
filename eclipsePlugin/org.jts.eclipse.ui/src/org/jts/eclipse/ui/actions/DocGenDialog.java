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
 * Provides the GUI for retrieving document generator information from the user.
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
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class DocGenDialog extends Dialog {

	private String path;
	private String name;
	private String type;
	private String stylePath = "plugins/org.jts.eclipse.data_1.0/resources/docGenerator/";
	private Boolean deleteIntermediate = true;
	private DocGroup dg;
	private DocBool db;

	public DocGenDialog(Shell parentShell) {		
		super(parentShell);
		path = "";
		name = "";
		type = "";		
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @return if intermediate files in doc generation
	 * should be deleted
	 * 
	 * defaults to true.
	 */
	public Boolean getDeleteIntermediateFiles()
	{
		return deleteIntermediate;
	}
	/**
	 * 
	 * @return type of document to generate
	 */
	public String getType() {
		return type;
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
	 * @return the location of the custom style sheet
	 */
	public String getStylePath(){
		return stylePath;
	}
	

	/**
	 * Open the dialog and return the input.
	 */
	public int open() {
		Shell shell = new Shell(getParentShell(), getShellStyle());
		shell.setText("Generate Documentation...");
		createContents(shell);
		shell.pack();
		shell.open();
		Display display = getParentShell().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		// Error checking - if all strings are legal return, else - error and cancel close.
		try
		{
			return 0;
		}
		catch(Exception e)
		{
			return 1;
		}
	}
	
	/**
	 * Creates the dialog's contents. 
	 * @param shell the dialog window
	 */
	private void createContents(final Shell shell)
	{
		// These objects appear in the dialog in the order
		// they are defined here.
		
		// Row 1
		Label servName = new Label(shell, SWT.NONE);	
		final Text servNameText = new Text (shell, SWT.BORDER);		
		
		// Row 2
		Label outPath = new Label(shell, SWT.NONE);
		
		// Row 3
		final Text outPathText = new Text(shell, SWT.BORDER);
		final Button browse1 = new Button(shell, SWT.PUSH);
		
		// Row 4
		Label schemaPath = new Label(shell, SWT.NONE);
		
		// Row 5
		final Text schemaPathText = new Text(shell, SWT.BORDER);
		final Button browse2 = new Button(shell, SWT.PUSH);
		
		// Row 6
		dg = new DocGroup(shell, SWT.SHADOW_ETCHED_IN);
		dg.setLocation(shell.getSize());
		db = new DocBool(shell, SWT.SHADOW_ETCHED_IN);
		db.setLocation(shell.getSize());
		Button ok = new Button(shell, SWT.PUSH);
		Button cancel = new Button(shell, SWT.PUSH);
		
		// Set up layout
		int numColumns = 4;
		GridLayout layout = new GridLayout();
		layout.numColumns = numColumns;
		shell.setLayout(layout);
		
		// Show the Service name text:
		servName.setText("Component Name:");
		GridData data = new GridData();
		data.horizontalSpan = 4;
		servName.setLayoutData(data);		
		
		// Display the name input box
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 4;
		servNameText.setText(name);
		servNameText.setLayoutData(data);					
		
		// Show the browse text:
		outPath.setText("Output Path:");
		data = new GridData();
		data.horizontalSpan = 4;		
		outPath.setLayoutData(data);
		
		// Display the input box
		outPathText.setText(path);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 3;
		outPathText.setLayoutData(data);
		
		// Add browse button.
		browse1.setText("Browse...");
		data = new GridData();
		data.horizontalSpan = 1;
		browse1.setLayoutData(data);
		browse1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				DirectoryDialog dlg = new DirectoryDialog(shell, SWT.OPEN);
		        String fn = dlg.open();
		        if (fn != null) {		          
					outPathText.setText(fn);
				}
			}
		});
		
		// Show the browse text:
		schemaPath.setText("Custom Style Sheet Path:");
		data = new GridData();
		data.horizontalSpan = 4;
		schemaPath.setLayoutData(data);
		
		// Display the input box
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 3;
		schemaPathText.setText(stylePath);
		schemaPathText.setLayoutData(data);
		
		// Add browse button.
		browse2.setText("Browse...");
		data = new GridData();
		data.horizontalSpan = 1;
		browse2.setLayoutData(data);
		browse2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				FileDialog dlg = new FileDialog(shell, SWT.OPEN);
		        String fn = dlg.open();
		        if (fn != null) {		          
					schemaPathText.setText(fn);
				}
			}
		});
		
		// Add OK button.
		ok.setText("Generate...");
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 1;
		ok.setLayoutData(data);
		ok.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent event){
				name = servNameText.getText();
				path = outPathText.getText();
				type = dg.getSelected();//lg.getSelected();
				stylePath = schemaPathText.getText();
				shell.close();
			}
		});
		
		cancel.setText("Cancel");
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 1;
	    cancel.setLayoutData(data);
	    cancel.addSelectionListener(new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent event) {
	        name = null;
	        path = null;
	        type = null;
	        shell.close();
	      }
	    });
		
		
		// Create the OK button and a handler so that pressing it will
		// capture the data in the fields.
	}
	
	class DocGroup extends Composite {

		  final Button b1;

		  final Button b2;

		  final Button b3;

		  public DocGroup(Composite c, int style) {
		    super(c, SWT.NO_BACKGROUND);
		    this.setSize(150, 75);
		    this.setLayout(new FillLayout());
		    final Group g = new Group(this, style);
		    g.setSize(150, 75);
		    g.setText("Select Document Type:");
		    b1 = new Button(g, SWT.RADIO);
		    b1.setBounds(10, 20, 110, 15);
		    b1.setText("Word Document");
		    b1.setSelection(true);
		    b2 = new Button(g, SWT.RADIO);
		    b2.setBounds(10, 35, 100, 15);
		    b2.setText("Linear HTML");
		    b3 = new Button(g, SWT.RADIO);
		    b3.setBounds(10, 50, 100, 15);
		    b3.setText("Framed HTML");
		  }

		  public String getSelected() {
		    if (b1.getSelection())
		      return "word";
		    if (b2.getSelection())
		      return "linear";
		    if (b3.getSelection())
		      return "framed";
		    return "word";
		  }
	}
	
	class DocBool extends Composite {

		  final Button b1;

		  final Button b2;

		  public DocBool(Composite c, int style) {
		    super(c, SWT.NO_BACKGROUND);
		    this.setSize(110, 75);
		    this.setLayout(new FillLayout());
		    final Group g = new Group(this, style);
		    g.setSize(110, 75);
		    g.setText("Delete intermediate files:");
		    b1 = new Button(g, SWT.RADIO);
		    b1.setBounds(10, 20, 75, 15);
		    b1.setText("True");
		    b1.setSelection(true);
		    b2 = new Button(g, SWT.RADIO);
		    b2.setBounds(10, 35, 75, 15);
		    b2.setText("False");
		  }

		  public Boolean getSelected() {
		    if (b1.getSelection())
		      return true;
		    if (b2.getSelection())
		      return false;
		    return true;
		  }
	}
	 

}
