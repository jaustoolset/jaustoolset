package com.mxgraph.swing.view.Editor.Editors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.mxgraph.swing.view.Editor.AutoCompleteTextArea;
import com.mxgraph.swing.view.Editor.Tree;
import com.mxgraph.swing.view.Editor.Nodes.EditableDeletableNode;
import com.mxgraph.swing.view.Editor.Nodes.NonEditableDeletableNode;

/**
 *
 * @author drew lucas
 */
public abstract class Editor< T > extends JInternalFrame
{
	protected Tree tree;
	protected AutoCompleteTextArea textEditor;

	private JPanel structuredEditorPanel;
	private JTabbedPane tab;
	private JButton cancelButton;
	private JButton acceptButton;
	private JButton deleteButton;
	private com.mxgraph.swing.view.Editor.EditorPanel controller;
	
	private int movementPreviousX;
	private int movementPreviousY;
	
	public Editor( com.u2d.generated.ProtocolBehavior pb )
	{
		tree = new Tree( pb );
		
		controller = null;

		// create display items
		deleteButton = new JButton( "Delete" );
		cancelButton = new JButton( "Cancel" );
		acceptButton = new JButton( "Accept" );
		structuredEditorPanel = new JPanel();
		textEditor = new AutoCompleteTextArea( pb );
		tab = new JTabbedPane();

		// local display items
		JPanel treePanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JPanel allPanels = new JPanel();
		JScrollPane structuredScrollPane  = new JScrollPane();
		JScrollPane textScrollPane = new JScrollPane();
		
		// layouts
		//------------
		// all panels
		SpringLayout allPanelsLayout = new SpringLayout();
		allPanels.setLayout( allPanelsLayout );
		
		// tab - allPanels
		allPanels.add( tab );
		allPanelsLayout.putConstraint( SpringLayout.WEST , tab, 1, SpringLayout.WEST, allPanels );
		allPanelsLayout.putConstraint( SpringLayout.NORTH , tab, 1, SpringLayout.NORTH, allPanels );
		allPanelsLayout.putConstraint( SpringLayout.EAST , tab, -1, SpringLayout.EAST, allPanels );
		
		// buttonPanel - allPanesl
		allPanels.add( buttonPanel );
		allPanelsLayout.putConstraint( SpringLayout.EAST , buttonPanel, -1, SpringLayout.EAST, allPanels );
		allPanelsLayout.putConstraint( SpringLayout.SOUTH , buttonPanel, -1, SpringLayout.SOUTH, allPanels );
		
		// tab - buttonPanel
		allPanelsLayout.putConstraint( SpringLayout.SOUTH , tab, -1, SpringLayout.NORTH, buttonPanel );
		
		//------------
		// button panel ( flow layout )
		buttonPanel.add( cancelButton );
		buttonPanel.add( acceptButton );
		
		//------------
		// tabs
		tab.addTab( "Structured Editor", treePanel );
		tab.addTab( "Text Editor", textScrollPane );
		
		//------------
		// tree panel
		SpringLayout treePanelLayout = new SpringLayout();
		treePanel.setLayout( treePanelLayout );
		
		// structuredScrollPane - treePanel
		treePanel.add( structuredScrollPane );
		treePanelLayout.putConstraint( SpringLayout.WEST , structuredScrollPane, 1, SpringLayout.WEST, treePanel );
		treePanelLayout.putConstraint( SpringLayout.NORTH , structuredScrollPane, 1, SpringLayout.NORTH, treePanel );
		treePanelLayout.putConstraint( SpringLayout.EAST , structuredScrollPane, -1, SpringLayout.EAST, treePanel );

		// deleteButton - treePanel
		treePanel.add( deleteButton );
		treePanelLayout.putConstraint( SpringLayout.WEST , deleteButton, 1, SpringLayout.WEST, treePanel );
		treePanelLayout.putConstraint( SpringLayout.SOUTH , deleteButton, -1, SpringLayout.SOUTH, treePanel );
		
		// structuredScrollPane - deleteButton
		treePanelLayout.putConstraint( SpringLayout.SOUTH , structuredScrollPane, -1, SpringLayout.NORTH, deleteButton );
		
		//------------
		// structured Scroll Pane
		structuredScrollPane.setViewportView( structuredEditorPanel );
		
		//------------
		// structured editor panel
		//structuredEditorPanel.setBackground( Color.BLUE );
		structuredEditorPanel.setLayout( new BorderLayout() );
		structuredEditorPanel.add( tree, BorderLayout.LINE_START );

		//------------
		// text area
		textScrollPane.setViewportView( textEditor );


		// sizes
		//tree.setMinimumSize( new Dimension( 100, 100 ) );
		//tree.setPreferredSize( new Dimension( 100, 100 ) );
		treePanel.setMinimumSize( new Dimension( 200, 150 ) );
		treePanel.setPreferredSize( new Dimension( 200, 150 ) );
		this.setMinimumSize( new Dimension( 250, 200 ) );
		this.setPreferredSize( new Dimension( 250, 200 ) );
		
		// uniform colors
		Color background = new Color(245,243,241);
		tree.setBackground( background );
		textEditor.setBackground( background );
		structuredEditorPanel.setBackground( background );
		
		// add panel to content pane of internal frame
		Container contentPane = getContentPane();
		contentPane.add( allPanels );

		// must set size and location and setVisible for showing
		setSize( 200, 200 );
		setLocation( 100,100 );

		pack();
		setVisible(true);
	}

	public void setup()
	{
		// internal pane options
		setBorder();
		setResizable(true);

		// add support for moving internal pane
		createMovementSupport();
		
		// add support for cancel/accept button
		createButtonSupport();
	}
	
	public void setCurrentCellStyle( String currentStyle )
	{
		textEditor.setCurrentCellStyle( currentStyle );
	}
	
	private void createMovementSupport()
	{
		// user may be holding mouse over tree, structuredEditorPanel or content pane
		// when trying to move the internal frame location
		
		createMovement( tree );
		createMovement( structuredEditorPanel );
		createMovement( getContentPane() );
		createMovement( tab );
	}
	
	private void createButtonSupport()
	{
		// signal controller cancel ( stop edit, don't save changes )
		cancelButton.addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed( ActionEvent e )
			{
				if( controller != null )
				{
					controller.stopEditing( true );
				}
			}
		});
		
		// signal controller accept ( stop edit, save changes )
		acceptButton.addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed( ActionEvent e )
			{
				if( controller != null )
				{
					controller.stopEditing( false );
				}
			}
		});
		
		// delete button to delete deletable nodes in tree
		deleteButton.addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed( ActionEvent e )
			{
				TreePath path = tree.getSelectionPath();
				if( path != null )
				{
					Object obj = path.getLastPathComponent();
					DefaultMutableTreeNode node = ( DefaultMutableTreeNode )obj;
					Object userObject = node.getUserObject();
					
					if( userObject instanceof EditableDeletableNode || 
						userObject instanceof NonEditableDeletableNode )
					{
						// delete node
						tree.deleteNode( node );
						tree.expandPath( path.getParentPath() );
						tree.setSelectionPath( path.getParentPath() );
					}
				}
			}
		});
	}
	
	private void createMovement( Container c )
	{
		c.addMouseMotionListener( getMotionAdapterForMovement() );
		
		c.addMouseListener( getMouseAdapterForMovement() );
	}
	
	private MouseMotionAdapter getMotionAdapterForMovement()
	{
		return new MouseMotionAdapter()
		{
			@Override
			public void mouseDragged(MouseEvent e)
			{
				int changeX = e.getLocationOnScreen().x - movementPreviousX;
				int changeY = e.getLocationOnScreen().y - movementPreviousY;
				
				setLocation( getX() + changeX, getY() + changeY );
				revalidate();
				
				// reset movement for next event
				movementPreviousX = e.getLocationOnScreen().x;
				movementPreviousY = e.getLocationOnScreen().y;
			}
		};
	}

	private MouseAdapter getMouseAdapterForMovement()
	{
		return new MouseAdapter()
		{
			public void mousePressed( MouseEvent e )
			{
				// reset variables for movement
				movementPreviousX = e.getLocationOnScreen().x;
				movementPreviousY = e.getLocationOnScreen().y;
			}
		};
	}

	private void setBorder()
	{
		Border bev = javax.swing.BorderFactory.createBevelBorder(BevelBorder.RAISED);
		Border bev2 = javax.swing.BorderFactory.createBevelBorder(BevelBorder.LOWERED);
		setBorder(javax.swing.BorderFactory.createCompoundBorder(bev, bev2));
		((javax.swing.plaf.basic.BasicInternalFrameUI)getUI()).setNorthPane(null);
	}
	
	public abstract ArrayList< T > convertTo();
	
	protected void setTextArea( String text )
	{
		textEditor.setText( text );
	}
	
	public void setController( com.mxgraph.swing.view.Editor.EditorPanel panel )
	{
		controller = panel;
	}
	
	public boolean isEditingText()
	{
		if( tab.getSelectedIndex() == 1 )
		{
			return true;
		}
		
		return false;
	}
	
	public String getEditingText()
	{
		return textEditor.getText();
	}
}
