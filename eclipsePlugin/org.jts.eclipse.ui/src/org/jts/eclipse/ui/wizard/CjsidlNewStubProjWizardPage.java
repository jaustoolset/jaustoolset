package org.jts.eclipse.ui.wizard;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.IDialogPage;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;

public class CjsidlNewStubProjWizardPage extends WizardPage{
    private Text containerText;
    private Text fileText;
    private Text serviceText;
    private Text serviceIdText;
    private Text versionText;
    private SetGroup setGroup;
        
    /**
     * Constructor.
     * @param selection
     */
    protected CjsidlNewStubProjWizardPage(ISelection selection) {        
        super("wizardPage");
        setTitle("CJSIDL File");
        setDescription("This wizard creates a new CJSIDL project with a file ending with *.csd.");

    }

    /**
     * @see IDialogPage#createControl(Composite)
     */
    @Override
    public void createControl(Composite parent) {

        Composite container = new Composite(parent, SWT.NULL);
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

        label = new Label(container, SWT.NULL);
        label.setText("&File name:");

        fileText = new Text(container, SWT.BORDER | SWT.SINGLE);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 2;
        fileText.setLayoutData(gd);
        fileText.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                dialogChanged();
            }
        });
        
        setGroup = new SetGroup(container, SWT.SHADOW_ETCHED_IN);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 3;
        setGroup.setLayoutData(gd);
        
        label = new Label(container, SWT.NULL);
        label.setText("&Service name:");
        serviceText = new Text(container, SWT.BORDER | SWT.SINGLE);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 2;
        serviceText.setLayoutData(gd);
        serviceText.addModifyListener(new ModifyListener(){
            public void modifyText(ModifyEvent e){
                dialogChanged();
            }
        });

        
        // Version
        label = new Label(container, SWT.NULL);
        label.setText("&Version number:");
        versionText = new Text(container, SWT.BORDER | SWT.SINGLE);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 2;
        versionText.setLayoutData(gd);
        versionText.addModifyListener(new ModifyListener(){
            public void modifyText(ModifyEvent e){
                dialogChanged();
            }
        });
        
        
        // URI
        label = new Label(container, SWT.NULL);
        label.setText("&Service id:");
        serviceIdText = new Text(container, SWT.BORDER | SWT.SINGLE);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 2;
        serviceIdText.setLayoutData(gd);
        serviceIdText.addModifyListener(new ModifyListener(){
            public void modifyText(ModifyEvent e){
                dialogChanged();
            }
        });
        
        initialize();
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
     * Set default text.
     */

    private void initialize() {
        fileText.setText("new_file.csd");
        serviceText.setText("ExampleName");
        serviceIdText.setText("example.uri");
        versionText.setText("0.0");
    }


    /**
     * Runs error handling as any field is updated.
     */

    private void dialogChanged() {

        String fileName = getFileName();
        String versionNum = getVersionNum();
        String serviceName = getServiceName();
        String serviceURI = getURI();

        if (fileName.length() == 0) {
            updateStatus("File name must be specified");
            return;
        }
        if (fileName.replace('\\', '/').indexOf('/', 1) > 0) {
            updateStatus("File name must be valid");
            return;
        }
        int dotLoc = fileName.lastIndexOf('.');
        if (dotLoc != -1) {
            String ext = fileName.substring(dotLoc + 1);
            if (!ext.equalsIgnoreCase("csd") && !ext.equalsIgnoreCase("cjsidl")) {
                updateStatus("File extension must be \"csd\" or \"cjsidl\" ");
                return;
            }
        }        
        
        // Service name error handling
        if (serviceName.length() == 0) {
            updateStatus("Service name must be specified");
            return;
        }
        if (serviceName.length() == 1) {
            updateStatus("Service name must be longer than one character");
            return;
        }
        if (serviceName.contains(" ") || serviceName.contains("!") || serviceName.contains("@") || serviceName.contains("*")|| serviceName.contains("$")|| serviceName.contains("^")|| serviceName.contains("+") || serviceName.contains("@")|| serviceName.contains("&")|| serviceName.contains("\\")|| serviceName.contains("/")) {
            updateStatus("Service name contains an illegal character");
            return;
        }
        
        // Version number error handling.
        dotLoc = versionNum.lastIndexOf('.');
        if(dotLoc != -1){
            String num1 = versionNum.substring(0, dotLoc);
            String num2 = versionNum.substring(dotLoc +1);
            int a = 0, b = 0;

            try{
                a = Integer.parseInt(num1);
                b = Integer.parseInt(num2);
            }catch(Exception e)
            {
                updateStatus("Version number must be in the format \"integer '.' integer\"");
                return;
            }
            
            if(a < 0 || b < 0)
            {
                updateStatus("Version number must be in the format \"integer '.' integer\" - negative intagers are not allowed.");
                return;
            }
        }else{
            updateStatus("Version number must be in the format \"integer '.' integer\"");
            return;
        }
        
        
        // URI error handling.
        dotLoc = serviceURI.lastIndexOf('.');
        if(dotLoc == -1){
            updateStatus("Service id must contain at least one '.'");
            return;
        }
        
        if (serviceURI.contains(" ") || serviceURI.contains("!") || serviceURI.contains("@") || serviceURI.contains("*")|| serviceURI.contains("$")|| serviceURI.contains("^")|| serviceURI.contains("+") || serviceURI.contains("@")|| serviceURI.contains("&")|| serviceURI.contains("\\")|| serviceURI.contains("/")) {
            updateStatus("Service id contains an illegal character");
            return;
        }
        
        if(serviceURI.lastIndexOf('.') == (serviceURI.length()-1))
        {
            updateStatus("Service id cannot end with a '.'");
            return;
        }
        if(serviceURI.charAt(0) == '.')
        {
            updateStatus("Service id cannot start with a '.'");
            return;
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
     * @return the new file name.
     */
    public String getFileName() {
        return fileText.getText();
    }
    
    /**
     * 
     * @return the service def name (or const set, or type set).
     */
    public String getServiceName()
    {
        return serviceText.getText();
    }
    
    /**
     * 
     * @return the file version number.
     */
    public String getVersionNum()
    {
        return versionText.getText();
    }
    
    /**
     * 
     * @return the file ID.
     */
    public String getURI()
    {
        return serviceIdText.getText();
    }
    
    /**
     * 
     * @return the content of the stub file - service def, type set, or const set.
     */
    public String getSetType(){
        return setGroup.getSelected();
    }
    
    /**
     * Sub-class to handle the radio buttons for the type of set inside the
     * stub file (service def, type set, or const set).
     * @author vnearing
     *
     */
    class SetGroup extends Composite {

        final Button b1;

        final Button b2;

        final Button b3;

        /**
         * GUI information.
         * @param c - the shell
         * @param style - style enum (located in SWT)
         */
        public SetGroup(Composite c, int style) {            
            super(c, SWT.NO_BACKGROUND);
            this.setSize(600, 75);
            this.setLayout(new FillLayout());
            final Group g = new Group(this, style);
            g.setSize(600, 75);
            g.setText("Select Set Type:");
            b1 = new Button(g, SWT.RADIO);
            b1.setBounds(10, 20, 300, 15);
            b1.setText("Service Definition");
            b1.setSelection(true);
            b2 = new Button(g, SWT.RADIO);
            b2.setBounds(10, 35, 300, 15);
            b2.setText("Type Set");
            b3 = new Button(g, SWT.RADIO);
            b3.setBounds(10, 50, 300, 15);
            b3.setText("Constant Set");
        }

        /**
         * 
         * @return what type the contents of the stub file should be.
         */
        public String getSelected() {
            if (b1.getSelection())
                return "service";
            if (b2.getSelection())
                return "type";
            if (b3.getSelection())
                return "const";
            return "service";
        }
    }


}
