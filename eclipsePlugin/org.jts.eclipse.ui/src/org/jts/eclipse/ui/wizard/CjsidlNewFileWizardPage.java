package org.jts.eclipse.ui.wizard;

import org.eclipse.core.resources.IContainer;
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
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;

public class CjsidlNewFileWizardPage extends WizardPage{

    private Text containerText;
    private Text fileText;
    private Text serviceText;
    private Text serviceIdText;
    private Text versionText;
    private SetGroup setGroup;
        
    private ISelection selection;
    /**
     * Constructor - sets title, description, and selection handler.
     * @param selection
     */
    protected CjsidlNewFileWizardPage(ISelection selection) {        
        super("wizardPage");
        setTitle("CJSIDL File");
        setDescription("This wizard creates a new CJSIDL file ending with *.csd.");
        this.selection = selection;

    }

    /**
     * Sets up the dialog elements and listeners for mutable fields.
     * @see IDialogPage#createControl(Composite)
     */
    @Override
    public void createControl(Composite parent) {

        // Select project to add file to - defaults to current selected project.
        Composite container = new Composite(parent, SWT.NULL);
        GridLayout layout = new GridLayout();
        container.setLayout(layout);
        layout.numColumns = 3;
        layout.verticalSpacing = 9;
        Label label = new Label(container, SWT.NULL);
        label.setText("&Project:");

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
        
        
        // Set up new file name - defaults to new_file.csd
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
        

        // Add buttons for file type (service def, type set, or const set)
        setGroup = new SetGroup(container, SWT.SHADOW_ETCHED_IN);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 3;
        setGroup.setLayoutData(gd);
        
        
        // Set up file data - set name, version, and URI.
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
     * Tests if the current workbench selection is a suitable container to use.
     * 
     * Sets default text.
     */
    private void initialize() {
       
        if (selection != null && selection.isEmpty() == false
                && selection instanceof IStructuredSelection) {
            IStructuredSelection ssel = (IStructuredSelection) selection;
            if (ssel.size() > 1)
                return;
            Object obj = ssel.getFirstElement();
            if (obj instanceof IResource) {
                IContainer container;
                if (obj instanceof IContainer)
                    container = (IContainer) obj;
                else
                    container = ((IResource) obj).getParent();
                containerText.setText(container.getFullPath().toString());
            }
        }
        fileText.setText("new_file.csd");
        serviceText.setText("ExampleName");
        serviceIdText.setText("example.uri");
        versionText.setText("0.0");
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
     * Runs error checking as any field is updated.
     */

    private void dialogChanged() {

        IResource container = ResourcesPlugin.getWorkspace().getRoot()
                .findMember(new Path(getContainerName()));
        String fileName = getFileName();
        String versionNum = getVersionNum();
        String serviceName = getServiceName();
        String serviceURI = getURI();

        if (getContainerName().length() == 0) {
            updateStatus("File container must be specified");
            return;
        }

        if (container == null
                || (container.getType() & (IResource.PROJECT | IResource.FOLDER)) == 0) {
            updateStatus("File container must exist");
            return;
        }
        if (!container.isAccessible()) {
            updateStatus("Project must be writable");
            return;
        }
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
     * @return the project name.
     */
    public String getContainerName() {
        return containerText.getText();
    }

    /**
     * 
     * @return the name of the new file.
     */
    public String getFileName() {
        return fileText.getText();
    }
    
    /**
     * 
     * @return the service (or type set or const set) name.
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
     * @return if the stub contains a service def, const set, or type set.
     */
    public String getSetType(){
        return setGroup.getSelected();
    }
    
    /**
     * The set of radio buttons to select the stub content type (service def, const set, or type set) 
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
         * @return which radio button was selected.
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



