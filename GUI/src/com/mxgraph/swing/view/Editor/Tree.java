package com.mxgraph.swing.view.Editor;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.synth.Region;
import javax.swing.plaf.synth.SynthStyle;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.mxgraph.swing.view.Editor.PopupCellRenderer;
import com.mxgraph.swing.view.Editor.Nodes.AdditionNode;
import com.mxgraph.swing.view.Editor.Nodes.EditableDeletableNode;
import com.mxgraph.swing.view.Editor.Nodes.NonEditableDeletableNode;
import com.mxgraph.swing.view.Editor.Nodes.TransitionNode;
import com.mxgraph.swing.view.Editor.Sets.ExtendableSet;

public class Tree extends JTree
{
	private TreeModel treeModel;
	private DefaultMutableTreeNode root;
	com.u2d.generated.ProtocolBehavior _pb;
	

	public Tree( com.u2d.generated.ProtocolBehavior pb )
	{
		super();
		
		_pb = pb;
		
		UIManager.put("Tree.openIcon", new javax.swing.ImageIcon(""));
		UIManager.put("Tree.closedIcon", new javax.swing.ImageIcon(""));
		setShowsRootHandles(false);
		
		// cell renderer must be setup before cell editor
		setCellRenderer();
		ValidatedTreeCellEditor editor = new ValidatedTreeCellEditor( this ); 
		setCellEditor( editor );
		setCellRenderer( new PopupCellRenderer() );
		
		// set options for tree
		setToggleClickCount(1);
		setRootVisible( true );
		setEditable( true );
		getSelectionModel().setSelectionMode( TreeSelectionModel.SINGLE_TREE_SELECTION );
		putClientProperty("Tree.lineStyle", "Angled");
		
		createNodeAdditionSupport();
	}
	
	public void setRoot( DefaultMutableTreeNode node )
	{
		root = node;
		treeModel = new TreeModel( root );
		treeModel.setAutocompleteVariables( _pb );
		setModel( treeModel );
	}

	private void createNodeAdditionSupport()
	{
		// add listener for when addition node is clicked
		addMouseListener( new MouseAdapter()
		{
			public void mouseClicked( MouseEvent e )
			{
				TreePath path = getClosestPathForLocation( e.getX(), e.getY() );
				tryPathUserObjectAddMember( path );
			}
		});

		// add listener for when addition node is selected and enter is pressed
		addKeyListener( new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if( e.getKeyCode() == KeyEvent.VK_ENTER )
				{
					trySelectedUserObjectAddMember();
				}
				else if( e.getKeyCode() == KeyEvent.VK_DELETE )
				{
					tryDeleteSelected();
				}
			}
		});
	}
	
	private void setCellRenderer()
	{
		DefaultTreeCellRenderer ren = new DefaultTreeCellRenderer();
		ren.setOpenIcon(null);
		ren.setClosedIcon(null);
		ren.setLeafIcon(null);
		
		setCellRenderer(ren);
	}
	
	public DefaultMutableTreeNode getRoot()
	{
		return root;
	}
	
	public DefaultTreeModel getModel()
	{
		return treeModel;
	}
	
	public void deleteNode( DefaultMutableTreeNode node )
	{
		treeModel.removeNodeFromParent( node );
		treeModel.reload( root );
	}
	
	public void trySelectedUserObjectAddMember()
	{
		TreePath path = getSelectionPath();

		if( path != null )
		{
			Object obj = path.getLastPathComponent();
			DefaultMutableTreeNode node = ( DefaultMutableTreeNode )obj;
			Object userObject = node.getUserObject();

			if( userObject instanceof AdditionNode )
			{
				AdditionNode addition = ( AdditionNode )userObject;
				addition.addMember();
	
				treeModel.reload( root );
				expandPath( path.getParentPath() );
				setSelectionPath( path );
			}
		}
	}
	
	public void tryPathUserObjectAddMember( TreePath path )
	{
		if( path != null )
		{
			Object obj = path.getLastPathComponent();
			DefaultMutableTreeNode node = ( DefaultMutableTreeNode )obj;
			Object userObject = node.getUserObject();

			if( userObject instanceof AdditionNode )
			{
				AdditionNode addition = ( AdditionNode )userObject;
				addition.addMember();
	
				treeModel.reload( root );
				expandPath( path.getParentPath() );
				setSelectionPath( path );
			}
		}
	}
	
	public void tryDeleteSelected()
	{
		TreePath path = getSelectionPath();
		if( path != null )
		{
			Object obj = path.getLastPathComponent();
			DefaultMutableTreeNode node = ( DefaultMutableTreeNode )obj;

			Object userObject = node.getUserObject();

			// delete nodes when they are allowed to be deleted
			if( userObject instanceof EditableDeletableNode || 
					userObject instanceof NonEditableDeletableNode )
			{
				deleteNode( node );
				expandPath( path.getParentPath() );
				setSelectionPath( path.getParentPath() );
			}
		}
	}

	public TransitionNode getTranstionNode( DefaultMutableTreeNode node )
	{
		TransitionNode output = null;
		
		while( node != null )
		{
			Object userObject = node.getUserObject();
			
			if( userObject instanceof TransitionNode )
			{
				output = ( TransitionNode )userObject;
				break;
			}
			
			if( node.getParent() == null )
			{
				break;
			}
			
			node = ( DefaultMutableTreeNode )node.getParent();
		}
		
		return output;
	}
	
	public ArrayList< String > getCurrentArguments()
	{
		// get last selected node in tree
		TreePath path = getSelectionPath();

		if( path == null )
		{
			return null;
		}
		
		Object obj = path.getLastPathComponent();
		TransitionNode node = getTranstionNode( ( DefaultMutableTreeNode )obj );

		return treeModel.getCurrentArguments( node );
	}
	
	public ArrayList< String > getCurrentTypes()
	{
		// get last selected node in tree
		TreePath path = getSelectionPath();

		if( path == null )
		{
			return null;
		}
		
		Object obj = path.getLastPathComponent();
		TransitionNode node = getTranstionNode( ( DefaultMutableTreeNode )obj );

		return treeModel.getCurrentTransitionParameterTypes( node );
	}
	
	public ArrayList< String > getCurrentValues()
	{
		// get last selected node in tree
		TreePath path = getSelectionPath();

		if( path == null )
		{
			return null;
		}
		
		Object obj = path.getLastPathComponent();
		TransitionNode node = getTranstionNode( ( DefaultMutableTreeNode )obj );
		
		return treeModel.getCurrentTransitionParameterValues( node );
	}
	
	public HashMap<String, String> getMessages()
	{
		return treeModel.getMessages();
	}
	
	public ArrayList< String > getActions()
	{
		return treeModel.getActions();
	}
	
	public ArrayList< String > getGuards()
	{
		return treeModel.getGuards();
	}
}
