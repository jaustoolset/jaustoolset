package jtsServiceRepositoryUI;
import java.awt.BorderLayout;
import java.awt.Component;

import jtsServiceRepositoryDB.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;

import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import java.awt.event.MouseAdapter;

import net.java.balloontip.BalloonTip;
import net.java.balloontip.TablecellBalloonTip;
import net.java.balloontip.styles.EdgedBalloonStyle;
import net.java.balloontip.styles.ModernBalloonStyle;
import net.java.balloontip.utils.TimingUtils;
import net.java.balloontip.utils.ToolTipUtils;

import org.jvnet.substance.*;
import org.jvnet.substance.api.skin.SubstanceGeminiLookAndFeel;
import org.jvnet.substance.api.skin.SubstanceGraphiteAquaLookAndFeel;
import org.jvnet.substance.api.skin.SubstanceMagellanLookAndFeel;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;
import org.jvnet.substance.skin.SubstanceBusinessLookAndFeel;
import org.jvnet.substance.skin.SubstanceChallengerDeepLookAndFeel;
import org.jvnet.substance.skin.SubstanceCremeLookAndFeel;
import org.jvnet.substance.skin.SubstanceRavenGraphiteLookAndFeel;
import org.jvnet.substance.skin.SubstanceRavenLookAndFeel;
import org.jvnet.substance.skin.SubstanceTwilightLookAndFeel;
import org.xml.sax.SAXException;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class ServiceRepositoryUIApplet extends javax.swing.JApplet {


	private boolean initCalled = false;
	
	private JMenuBar jMenuBar1;
	private JButton downloadButton;
	private JButton searchButton;
	private JOptionPane errorOptionPane;
	private JScrollPane jScrollPane3;
	private JButton uploadCancelButton;
	private JTextPane componentDescriptionPane;
	private JFileChooser uploadFileChooser;
	private JButton uploadBrowseButton;
	private JLabel jLabel2;
	private JTextField uploadFileName;
	private JTable categorySelectionTable;
	private JScrollPane jScrollPane1;
	private JTable serviceTable;
	private JScrollPane jScrollPane2;
	private JLabel authorLabel;
	private JTextField uploadAuthor;
	private JLabel jLabel1;
	private JTextField uploadBriefDescription;
	private JLabel uploadCategoryLabel;
	private JComboBox uploadCategoryComboBox;
	private JButton uploadOKButton;
	private JDialog uploadDialog;
	private TableModel categorySelectionTableModel;
	private JSeparator jSeparator1;
	private JTextField searchTextFields;
	private JButton uploadButton;
	private JMenu aboutMenu;
	private JMenu jMenu1;
	private JFrame mainFrame;
	
	private ArrayList<ServiceRepositoryCategory> categoryList;
	private ArrayList<ServiceRepositoryEntry> servicesList; 
	private boolean uploadReady = false;
	private int categoryTableRow = -1;
	private TablecellBalloonTip categoryTableRowBalloon=null;
	
	public ServiceRepositoryUIApplet me = null;
	/**
	* Auto-generated main method to display this 
	* JApplet inside a new JFrame.
	*/
	//public void main(String[] args) {
	public void init() {
		if (initCalled) {
			return;
		}
		
		initCalled = true;
		me = this;
		
		// Let's find our frame. We'll need it later
		Container c = this;
		boolean foundFrame = false;

		while (c != null) {
			if (c instanceof JFrame) {
				mainFrame = (JFrame)c;
				foundFrame = true;
			}
			
			c = c.getParent();
		}
		
		System.out.println("foundFrame = " + foundFrame + "\n");
		
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					JFrame.setDefaultLookAndFeelDecorated(true);

					try {
						//javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
						javax.swing.UIManager.setLookAndFeel(new SubstanceGraphiteAquaLookAndFeel());
					} catch (UnsupportedLookAndFeelException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					SwingUtilities.updateComponentTreeUI(me);
					fullInitGUI();
				}
			});
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		postInitGUI();

	}

	public void fullInitGUI() {
		System.out.println("In Constructor\n");

		initGUI();

	}
	
	public ServiceRepositoryUIApplet() {
		super();
	}
	
	private void postInitGUI() {
		// First, let's populate the list class with our categories, read from the DB. 
		categoryList = ServiceRepositoryIO.getCategories();
		int i;
		
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					for (int i = 0; i < categoryList.size(); i++) {
						((DefaultTableModel)(categorySelectionTableModel)).addRow(new Object[] { new String (categoryList.get(i).getTitle()) });
					}
					categoryTableRowBalloon = new TablecellBalloonTip(categorySelectionTable, 
							new String("This is cell " + 0), 0, 0,
							new ModernBalloonStyle(5, 10, Color.BLACK, Color.BLUE, Color.WHITE), 
							BalloonTip.Orientation.RIGHT_ABOVE, 
							BalloonTip.AttachLocation.ALIGNED, 
							20, 20,
							false);
					categoryTableRowBalloon.setVisible(false);

				}
			});
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Updates the service table based on the selected category index
	 * 
	 * @param index the category list index
	 */
	private void updateServiceTable(int index) {
		
		// This call will retrieve a list of services from the database, keyed by category. 
		servicesList = ServiceRepositoryIO.getServicesByCategory(categoryList.get(index).getTitle());
		
		int i;
		for (i = 0; i < servicesList.size(); i++)
		{
			System.out.println(i + ": " + 
					servicesList.get(i).getURI() + " : " +
					servicesList.get(i).getName() + " : " +
					servicesList.get(i).getBriefDescription() + " : " +
					servicesList.get(i).getAuthor() + " : " +
					servicesList.get(i).getVersion() + "\n");
			System.out.println("         " + servicesList.get(i).getVerboseDescription() + "\n");
		}		
		
		System.out.println("Rowcount = " + getServiceTable().getRowCount() + "\n");
		// First clear all rows in the current table
		int rowCount = ((DefaultTableModel) getServiceTable().getModel()).getRowCount();
		for (i = 0; i < rowCount; i++) {
			((DefaultTableModel) getServiceTable().getModel()).removeRow(0);
		}
		
		// Now add new rows to the table. 
		for (i = 0; i < servicesList.size(); i++) {
			((DefaultTableModel) getServiceTable().getModel()).addRow(new Object[] { servicesList.get(i).getName(), servicesList.get(i).getVersion(), 
					servicesList.get(i).getBriefDescription() } );
		}
	}

	/**
	 * Probably not the best way to clear the description area - but it works.
	 */
	private void clearDetailedDescriptionArea() {

		getComponentDescriptionPane().setEditable(true);
		getComponentDescriptionPane().setSelectionStart(0);
		getComponentDescriptionPane().setSelectionEnd(5000);
		getComponentDescriptionPane().cut();
	}
	
	/**
	 * Updates the detailed text box area with additional service information
	 */
	private void updateDetailedDescription(int index) {
		
		StyledDocument doc = getComponentDescriptionPane().getStyledDocument();
		if (servicesList.size() < 0)
		{
			clearDetailedDescriptionArea();
			try {
				doc.insertString(0, "There are no services in this category. Upload some!", null);
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return; 
		}

		if ((index < 0) || (index >= servicesList.size()))
			return;
		
		if (servicesList.size() < 0)
			return;

		// Now add the verbose description to the text area
		clearDetailedDescriptionArea();
		componentDescriptionPane.setText(servicesList.get(index).getVerboseDescription());
		/*
		try {
			doc.insertString(0, servicesList.get(index).getVerboseDescription(), null);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//getComponentDescriptionPane().insert(servicesList.get(index).getVerboseDescription(), 0);
		
	}
	
	private void initGUI() {
		try {
			{
				downloadButton = new JButton();
				downloadButton.setText("Download");
				downloadButton.setBounds(53, 79, 71, 22);
				downloadButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						downloadButtonActionPerformed(evt);
					}
				});
				URL url = ServiceRepositoryUIApplet.class.getResource("/icons/Earth-Download-24x24.png");
				if (url != null) {
					URL url2 = ServiceRepositoryUIApplet.class.getResource("./");
					System.out.println("URL2 = " + url2 + "\n");
					ImageIcon icon = new ImageIcon(url);
					downloadButton.setIcon(icon);
					downloadButton.setVerticalTextPosition(SwingConstants.BOTTOM);
					downloadButton.setHorizontalTextPosition(SwingConstants.CENTER);
				} else {
					url = ServiceRepositoryUIApplet.class.getResource("/");
					System.out.println("Can't find Download Icon: URL = " + url);
				}
			}
			{
				uploadButton = new JButton();
				uploadButton.setText("Upload");
				
				BalloonTip uploadTip = new BalloonTip(uploadButton, "Contribute services to the repository",
						new ModernBalloonStyle(5, 10, Color.WHITE, Color.BLUE, Color.BLACK), false);
				
				ToolTipUtils.balloonToToolTip(uploadTip, 500, 2000);
				
				URL url = ServiceRepositoryUIApplet.class.getResource("/icons/Earth-Upload-24x24.png");
				if (url != null) {
					ImageIcon icon = new ImageIcon(url);
					uploadButton.setIcon(icon);
					uploadButton.setVerticalTextPosition(SwingConstants.BOTTOM);
					uploadButton.setHorizontalTextPosition(SwingConstants.CENTER);
				} else {
					url = ServiceRepositoryUIApplet.class.getResource("/");
					System.out.println("Can't find Upload Icon: URL = " + url);
				}
				uploadButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						uploadButtonActionPerformed(evt);
					}
				});
			}
			{
				searchButton = new JButton();
				searchButton.setText("Search");
				URL url = ServiceRepositoryUIApplet.class.getResource("/icons/Search-Hard-Disk-24x24.png");
				if (url != null) {
					ImageIcon icon = new ImageIcon(url);
					searchButton.setIcon(icon);
					searchButton.setVerticalTextPosition(SwingConstants.BOTTOM);
					searchButton.setHorizontalTextPosition(SwingConstants.CENTER);
				} else {
					url = ServiceRepositoryUIApplet.class.getResource("/");
					System.out.println("Can't find Search Icon: URL = " + url);
				}
			}
			{
				searchTextFields = new JTextField();
				searchTextFields.setText("jTextField1");
			}
			{
				jSeparator1 = new JSeparator();
			}
			{
				jScrollPane1 = new JScrollPane();
				{
					categorySelectionTableModel = 
						new DefaultTableModel(null, new String[] { "Categories" });
					categorySelectionTable = new JTable();
					jScrollPane1.setViewportView(categorySelectionTable);
					categorySelectionTable.setModel(categorySelectionTableModel);
					categorySelectionTable.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
					categorySelectionTable.setPreferredSize(new java.awt.Dimension(144, 442));


					categorySelectionTable.addMouseMotionListener(new MouseMotionListener() {
						// We use this to pop up a balloon tip
						public void mouseMoved(MouseEvent me) {
							Point p = new Point(me.getX(), me.getY());
							int row = categorySelectionTable.rowAtPoint(p);
							System.out.println("mouse at row " + row + "\n");
							if (row >= 1) {
								categoryTableRowBalloon.setText(categoryList.get(row).getDescription());
								//categoryTableRowBalloon.setText("This is row " + row);
								categoryTableRowBalloon.setCellPosition(row, 0);
								categoryTableRowBalloon.setVisible(true);
								URL url = ServiceRepositoryUIApplet.class.getResource(new String("/icons/" + categoryList.get(row).getIconName()));
								if (url != null) {
									ImageIcon icon = new ImageIcon(url);
									categoryTableRowBalloon.setIcon(icon);
								} else {
									categoryTableRowBalloon.setIcon(null);
								}
								
							}
						}

						@Override
						public void mouseDragged(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}
					});
					categorySelectionTable.addMouseListener(new MouseInputAdapter() {
							
						public void mouseClicked(MouseEvent me) {
							Point p = new Point(me.getX(), me.getY());
							categorySelectionTable.setEditingRow(categorySelectionTable.rowAtPoint(p));
							updateServiceTable(categorySelectionTable.getSelectedRow());
						}
						
						public void mouseExited(MouseEvent me) {
							categoryTableRowBalloon.setVisible(false);

						}
					});

				}
			}
			{
				jScrollPane2 = new JScrollPane();
				{

					TableModel serviceTableModel = 
						new DefaultTableModel(
								null,
								new String[] { "Name", "Version", "Description" });

					serviceTable = new JTable();
					jScrollPane2.setViewportView(serviceTable);
					serviceTable.setFillsViewportHeight(true);
					serviceTable.setModel(serviceTableModel);
					serviceTable.setPreferredSize(new java.awt.Dimension(607, 199));

					TableColumn column = null;
					column = serviceTable.getColumnModel().getColumn(0);
					column.setPreferredWidth(50);
					column = serviceTable.getColumnModel().getColumn(1);
					column.setPreferredWidth(20);
					column = serviceTable.getColumnModel().getColumn(2);
					column.setPreferredWidth(200);
					
					// Now, add a listener for selection events
					serviceTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
						public void valueChanged(ListSelectionEvent evt) {
							if (evt.getValueIsAdjusting())
								return;
							System.out.println("Selected index " + serviceTable.getSelectedRow());
							updateDetailedDescription(serviceTable.getSelectedRow());
						}
					});
				}
			}
			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(getJMenuBar1());
				{
					jMenu1 = new JMenu();
					jMenuBar1.add(jMenu1);
					jMenu1.setText("File");
				}
				{
					aboutMenu = new JMenu();
					jMenuBar1.add(aboutMenu);
					aboutMenu.setText("About");
				}
			}
			{
				// TODO: Add error checking for icon loads
				GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
				getContentPane().setLayout(thisLayout);
				thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							    .addComponent(downloadButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							    .addComponent(uploadButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							    .addComponent(searchButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							    .addComponent(searchTextFields, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(thisLayout.createParallelGroup()
							    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
							        .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
							        .addGap(23)
							        .addComponent(getJScrollPane3(), GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE))
							    .addGroup(thisLayout.createSequentialGroup()
							        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 466, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap(63, Short.MAX_VALUE));
						thisLayout.setHorizontalGroup(thisLayout.createParallelGroup()
					.addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
					    .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 857, GroupLayout.PREFERRED_SIZE)
					    .addGap(0, 6, Short.MAX_VALUE))
					.addGroup(thisLayout.createSequentialGroup()
					    .addGap(34)
					    .addGroup(thisLayout.createParallelGroup()
					        .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
					            .addComponent(downloadButton, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					            .addComponent(uploadButton, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
					            .addGap(143)
					            .addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					            .addComponent(searchTextFields, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
					            .addGap(0, 64, Short.MAX_VALUE))
					        .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
					            .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
					            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					            .addGroup(thisLayout.createParallelGroup()
					                .addGroup(thisLayout.createSequentialGroup()
					                    .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 607, GroupLayout.PREFERRED_SIZE))
					                .addGroup(thisLayout.createSequentialGroup()
					                    .addComponent(getJScrollPane3(), GroupLayout.PREFERRED_SIZE, 607, GroupLayout.PREFERRED_SIZE)))
					            .addGap(0, 0, Short.MAX_VALUE)))
					    .addContainerGap(48, 48)));
				this.setSize(863, 619);
				}
			//pack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public JMenuBar getJMenuBar1() {
		return jMenuBar1;
	}
	
	public JTextField getSearchTextFields() {
		return searchTextFields;
	}
	
	public JTable getCategorySelectionList() {
		return categorySelectionTable;
	}
	
	public JTable getServiceTable() {
		return serviceTable;
	}
	

	/**
	 * This handles all initialization of the upload service dialog. 
	 * @return
	 */
	private JDialog getUploadDialog() {
		
		if (uploadDialog != null) {
			return uploadDialog;
		}
		
		{
			uploadOKButton = new JButton();
			uploadOKButton.setText("OK");
			uploadOKButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					uploadOKButtonActionPerformed(evt);
				}
			});
		}
		{
			uploadBriefDescription = new JTextField();
		}
		{
			jLabel1 = new JLabel();
			jLabel1.setText("Brief Description");
			jLabel1.setHorizontalTextPosition(SwingConstants.RIGHT);
		}
		{
			uploadAuthor = new JTextField();
		}
		{
			authorLabel = new JLabel();
			authorLabel.setText("Author");
		}
		{
			uploadFileName = new JTextField();
		}
		{
			jLabel2 = new JLabel();
			jLabel2.setText("File Name");
		}
		{
			uploadBrowseButton = new JButton();
			uploadBrowseButton.setText("Browse");
			uploadBrowseButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					uploadBrowseButtonActionPerformed(evt);
				}
			});
		}
		{
			// Get a list of categories. We will use this to populate the combo box
			ArrayList<ServiceRepositoryCategory> categories;
			categories = ServiceRepositoryIO.getCategories();
			String cat[] = new String[categories.size()+1];
			cat[0] = "<Select Category>";
			for (int i = 0; i < categories.size(); i++) {
				cat[i+1] = categories.get(i).getTitle();
			}

			ComboBoxModel uploadCategoryComboBoxModel =	new DefaultComboBoxModel(cat);
			uploadCategoryComboBox = new JComboBox();
			uploadCategoryComboBox.setModel(uploadCategoryComboBoxModel);
			
		}
		{
			uploadCategoryLabel = new JLabel();
			uploadCategoryLabel.setText("Category");
			uploadCategoryLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
		}

		if(uploadDialog == null) {
			uploadDialog = new JDialog(mainFrame, "modalDialog", true);
			GroupLayout uploadDialogLayout = new GroupLayout((JComponent)uploadDialog.getContentPane());
			uploadDialog.getContentPane().setLayout(uploadDialogLayout);
				uploadDialogLayout.setHorizontalGroup(uploadDialogLayout.createSequentialGroup()
					.addContainerGap(31, 31)
					.addGroup(uploadDialogLayout.createParallelGroup()
					    .addGroup(GroupLayout.Alignment.LEADING, uploadDialogLayout.createSequentialGroup()
					        .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					        .addGap(29))
					    .addComponent(jLabel1, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
					    .addGroup(GroupLayout.Alignment.LEADING, uploadDialogLayout.createSequentialGroup()
					        .addComponent(authorLabel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					        .addGap(71))
					    .addGroup(GroupLayout.Alignment.LEADING, uploadDialogLayout.createSequentialGroup()
					        .addComponent(uploadCategoryLabel, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					        .addGap(12)))
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addGroup(uploadDialogLayout.createParallelGroup()
					    .addGroup(GroupLayout.Alignment.LEADING, uploadDialogLayout.createSequentialGroup()
					        .addComponent(uploadFileName, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
					        .addComponent(getUploadCancelButton(), GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					        .addGap(0, 0, Short.MAX_VALUE))
					    .addGroup(uploadDialogLayout.createSequentialGroup()
					        .addComponent(uploadBriefDescription, GroupLayout.PREFERRED_SIZE, 335, GroupLayout.PREFERRED_SIZE)
					        .addGap(0, 0, Short.MAX_VALUE))
					    .addGroup(uploadDialogLayout.createSequentialGroup()
					        .addComponent(uploadAuthor, GroupLayout.PREFERRED_SIZE, 335, GroupLayout.PREFERRED_SIZE)
					        .addGap(0, 0, Short.MAX_VALUE))
					    .addGroup(uploadDialogLayout.createSequentialGroup()
					        .addGap(114)
					        .addGroup(uploadDialogLayout.createParallelGroup()
					            .addGroup(GroupLayout.Alignment.LEADING, uploadDialogLayout.createSequentialGroup()
					                .addGap(0, 0, Short.MAX_VALUE)
					                .addComponent(uploadOKButton, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					                .addGap(0, 32, GroupLayout.PREFERRED_SIZE)
					                .addComponent(uploadBrowseButton, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
					            .addGroup(GroupLayout.Alignment.LEADING, uploadDialogLayout.createSequentialGroup()
					                .addGap(0, 42, Short.MAX_VALUE)
					                .addComponent(uploadCategoryComboBox, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(45, 45));
				uploadDialogLayout.setVerticalGroup(uploadDialogLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(uploadDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(uploadCategoryComboBox, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(uploadCategoryLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(uploadDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(uploadAuthor, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(authorLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(uploadDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(uploadBriefDescription, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(uploadDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(uploadFileName, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel2, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(uploadBrowseButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(22)
				.addGroup(uploadDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(uploadOKButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
				    .addComponent(getUploadCancelButton(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
				.addContainerGap());
				uploadDialogLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {uploadOKButton, getUploadCancelButton()});
				uploadDialogLayout.linkSize(SwingConstants.VERTICAL, new Component[] {uploadOKButton, getUploadCancelButton()});
		}
		uploadDialog.setSize(553, 222);
		return uploadDialog;
	}
	
	private void uploadButtonActionPerformed(ActionEvent evt) {
		System.out.println("uploadButton.actionPerformed, event="+evt);
		uploadDialog = getUploadDialog();
		uploadDialog.setPreferredSize(new java.awt.Dimension(553, 222));
		uploadReady = false;
		uploadDialog.setVisible(true);
	}
	
	private void uploadOKButtonActionPerformed(ActionEvent evt) {
		// First, do sanity checks. We need to make sure that there are entries for category, 
		// author, brief description, and file. 
		// Otherwise, pop up an error window.
		System.out.println("AUTHOR = " + uploadAuthor.getText());
		if (uploadAuthor.getText().trim().equals("")) {
			doUploadError("You need to set an Author name");
			return;
		}
		
		if (uploadBriefDescription.getText().trim().equals("")) {
			doUploadError("You need to provide a brief description");
			return;
		}
		
		if (uploadCategoryComboBox.getSelectedIndex() == 0)
		{
			doUploadError("You need to select a category");
			return;
		}
		
		if (uploadFileName.getText().trim().equals(""))
		{
			doUploadError("You need to select a valid JSIDL file");
			return;
		}
				
		System.out.println("uploadOKButton.actionPerformed, event="+evt);
//			ServiceRepositoryJSIDL jsidl = new ServiceRepositoryJSIDL();
		//boolean worked = jsidl.parse(uploadFileName.getText());
		File testFile = new File(uploadFileName.getText()); 
		if (!testFile.exists()) {
			doUploadError("The selected service file was not found");
			return;
		}

		getUploadDialog().setVisible(false);
		
		// TODO: Check whether dependencies of this service are already in the repository. If not,
		// warn the user and ask him to upload them. 

		
		// At this point, we have everything we need to upload a service.
		System.out.println("category = " + uploadCategoryComboBox.getSelectedItem().toString());
		
		try {
			ServiceRepositoryIO.addService(uploadFileName.getText(), uploadAuthor.getText(), 
					uploadCategoryComboBox.getSelectedItem().toString(),
					uploadBriefDescription.getText());
			
		} catch (FileNotFoundException e) {
			doUploadError(new String("File did not upload successfully: " + e.getMessage()));
			e.printStackTrace();
			return;
		} catch (IOException e) {
			doUploadError(new String("File did not upload successfully: " + e.getMessage()));
			e.printStackTrace();
			return;
		} catch (SAXException e) {
			doUploadError(new String("File did not upload successfully: " + e.getMessage()));
			e.printStackTrace();
			return;
		} catch (SQLException e) {
			doUploadError(new String("File did not upload successfully: " + e.getMessage()));
			e.printStackTrace();
			return;
		}

		// Now, grab a list of dependencies, so we can check to see if they are satisfied.
		// Otherwise, pop up a message asking the uploader to upload the dependencies to
		// the repository
		
		ArrayList<ServiceRepositoryDependency> dep = null;
		
		ServiceRepositoryJSIDL jsidl = new ServiceRepositoryJSIDL();
		
		try {
			jsidl.parse(uploadFileName.getText());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dep = jsidl.getDependencies();
		
		StringBuffer depOutput = new StringBuffer(1024);
		depOutput.append("The following dependencies are not in the database: \n");
		boolean missingDeps = false;
		for (int i = 0; i < dep.size(); i++) {
			try {
				if (!(ServiceRepositoryIO.isServiceInRepository(dep.get(i).getURI(), dep.get(i).getVersion()))) {
					System.out.println("Dependency not satisfied: " + dep.get(i).getURI() + ", " + dep.get(i).getVersion() + "\n");
					depOutput.append("   " + dep.get(i).getURI() + ", version " + dep.get(i).getVersion() + "\n");
					missingDeps = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (missingDeps) {
			depOutput.append("Please upload these services");
			String depString = depOutput.toString();
			doUploadError(depString);
		} else {
			doStatusMessage("Upload completed successfully");
		}
		if (categorySelectionTable.getSelectedRow() >= 0) {
			updateServiceTable(categorySelectionTable.getSelectedRow());
		}
	}
	
	private JFileChooser getUploadFileChooser() {
		if(uploadFileChooser == null) {
			uploadFileChooser = new JFileChooser();
		}
		return uploadFileChooser;
	}
	
	private void uploadBrowseButtonActionPerformed(ActionEvent evt) {
		System.out.println("uploadBrowseButton.actionPerformed, event="+evt);

		int option = getUploadFileChooser().showOpenDialog(mainFrame);
		if (option == JFileChooser.APPROVE_OPTION){
			System.out.println("File chosen = " + getUploadFileChooser().getSelectedFile().getName() + "\n");
			uploadFileName.setSelectionStart(0);
			uploadFileName.setSelectionEnd(500);
			uploadFileName.cut();
			uploadFileName.setText(getUploadFileChooser().getSelectedFile().getAbsolutePath());
		 } 		
	}
	
	private JTextPane getComponentDescriptionPane() {
		if(componentDescriptionPane == null) {
			componentDescriptionPane = new JTextPane();
			componentDescriptionPane.setContentType("text/html");
			componentDescriptionPane.setText("<h1> HELLO </h1>");
		}
		return componentDescriptionPane;
	}
	
	private JOptionPane getErrorOptionPane() {
		if(errorOptionPane == null) {
			errorOptionPane = new JOptionPane();
		}
		return errorOptionPane;
	}
	
	private void doStatusMessage(String text) {
		errorOptionPane = getErrorOptionPane();
		errorOptionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
		errorOptionPane.setMessage(text);
		errorOptionPane.createDialog(mainFrame, "Status").setVisible(true);
	}

	private void doUploadError(String text) {
		errorOptionPane = getErrorOptionPane();
		errorOptionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
		errorOptionPane.setMessage(text);
		errorOptionPane.createDialog(mainFrame, "Status").setVisible(true);
	}
	
	private boolean doVerification(String text) {
		errorOptionPane = getErrorOptionPane();
		Object[] options = { "Yes", "No" };
		errorOptionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
		errorOptionPane.setOptionType(JOptionPane.YES_NO_OPTION);
		errorOptionPane.setOptions(options);
		errorOptionPane.setMessage(text);
		errorOptionPane.createDialog(mainFrame, "Status").setVisible(true);
		Object option = errorOptionPane.getValue();
		if ( ((String)(option) ).equals("Yes") )
			return true;
		return false;
	}
	
	private JButton getUploadCancelButton() {
		if(uploadCancelButton == null) {
			uploadCancelButton = new JButton();
			uploadCancelButton.setText("Cancel");
			uploadCancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					uploadCancelButtonActionPerformed(evt);
				}
			});
		}
		return uploadCancelButton;
	}
	
	private void uploadCancelButtonActionPerformed(ActionEvent evt) {
		System.out.println("uploadCancelButton.actionPerformed, event="+evt);
		uploadDialog.setVisible(false);
	}
	
	private JScrollPane getJScrollPane3() {
		if(jScrollPane3 == null) {
			jScrollPane3 = new JScrollPane();
			jScrollPane3.setViewportView(getComponentDescriptionPane());
		}
		return jScrollPane3;
	}
	
	private void downloadButtonActionPerformed(ActionEvent evt) {
		System.out.println("downloadButton.actionPerformed, event="+evt);
		//TODO add your code for downloadButton.actionPerformed
		
		// First, we need to grab the selected service
		int index = serviceTable.getSelectedRow();
		
		String URI = servicesList.get(index).getURI();
		String version = servicesList.get(index).getVersion();
		
		ArrayList<ServiceRepositoryDependency> deps = null;
		
		try {
			int option = getUploadFileChooser().showOpenDialog(mainFrame);
			if (option == JFileChooser.APPROVE_OPTION){
				System.out.println("Same name chosen = " + getUploadFileChooser().getSelectedFile().getName() + "\n");
				System.out.println("Getting all dependencies for " + URI + " / " + version + "\n");
				deps = ServiceRepositoryIO.getAllDependencies(deps, URI, version);
				// Add ourselves to the list
				ServiceRepositoryDependency d = new ServiceRepositoryDependency();
				d.setURI(URI);
				d.setVersion(version);
				deps.add(d);
				System.out.println("Adding self index = " + index + ", " + URI + " / " + version + "\n");
				
				String fileName = getUploadFileChooser().getSelectedFile().getAbsolutePath();
				File testFile = new File(fileName);
				
				boolean shouldOverwrite = true;
				if (testFile.exists()) {
					// Ask if we should over write
					shouldOverwrite = doVerification("File exists. Overwrite?");
				}
				if (shouldOverwrite) {
					ServiceRepositoryIO.createZipFile(deps, fileName);
					doStatusMessage("File successfully downloaded!");
				}
				
			} else {
				 return;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
