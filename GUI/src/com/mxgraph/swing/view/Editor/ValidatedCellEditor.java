package com.mxgraph.swing.view.Editor;

import java.lang.reflect.Method;
import java.util.EventObject;

import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.jts.validator.ValidatorException;

import com.mxgraph.swing.view.Editor.Nodes.ActionNode;
import com.mxgraph.swing.view.Editor.Nodes.ArgumentNode;
import com.mxgraph.swing.view.Editor.Nodes.EditableDeletableNode;
import com.mxgraph.swing.view.Editor.Nodes.EditableNode;
import com.mxgraph.swing.view.Editor.Nodes.GuardNode;
import com.mxgraph.swing.view.Editor.Nodes.InternalTransitionNode;
import com.mxgraph.swing.view.Editor.Nodes.NonEditableDeletableNode;
import com.mxgraph.swing.view.Editor.Nodes.SimpleTransitionNode;
import com.mxgraph.swing.view.Editor.Nodes.TypeNode;
import com.mxgraph.swing.view.Editor.Nodes.ValueNode;

public class ValidatedCellEditor extends DefaultCellEditor
{
	private Tree tree;
	private AutoCompleteField autocompleteField;
	
	public ValidatedCellEditor( AutoCompleteField field )
	{
		super( field );
		tree = null;
		autocompleteField = field;
	}
	
	public void setTree( Tree input )
	{
		tree = input;
	}

	@Override
	public boolean stopCellEditing()
	{
		TreePath path = tree.getSelectionPath();
		if( path != null )
		{
			Object obj = path.getLastPathComponent();
			DefaultMutableTreeNode node = ( DefaultMutableTreeNode )obj;
			Object userObject = node.getUserObject();
			String text = autocompleteField.getText();

			if( text.isEmpty() )
			{
				// delete empty nodes when they are allowed to be deleted
				if( userObject instanceof EditableDeletableNode || 
						userObject instanceof NonEditableDeletableNode )
				{
					// delete node
					tree.deleteNode( node );
					tree.expandPath(path.getParentPath());
					
					return false;
				}
				// otherwise set placeholder for cell
				else
				{
					Class<?> cls = null;
					try
					{
						cls = userObject.getClass();
						Class[] parm = new Class[1];
						parm[0] = text.getClass();
						Method convert = cls.getMethod( "setString", parm );
						convert.invoke( node, new Object[]{ "modify string" } );
					}
					catch( NoSuchMethodException e )
					{
						throw new ValidatorException("stopCellEditing: Attempted to get setString method from class " + cls.getName());
					}
					catch( Exception e )
					{
						
					}
					
					return false;
				}
			}
			else
			{
				// modify user object text when editable
				if( userObject instanceof EditableNode )
				{
					EditableNode n = ( EditableNode )userObject;
					String previousText = n.toString();
					
					n.setString( text );

					try
					{
						n.validate();
					}
					catch( Exception e )
					{
						JOptionPane.showMessageDialog(getComponent(), "Validation error: " + e.getMessage());
						n.setString( previousText );

						return false;
					}
					
					// important: don't allow modification of object directly,
					// instead we set the text of the object which will be displayed
					// when the cell renders with the toString method
					// To achieve this, we must cancel editing before the user object
					// is changed to a string by the editor
					super.cancelCellEditing();
				}
			}
		}

		return super.stopCellEditing();
	}
	
	@Override
	public boolean isCellEditable( EventObject e )
	{
		TreePath path = tree.getSelectionPath();

		if( path != null )
		{
			Object obj = path.getLastPathComponent();
			DefaultMutableTreeNode node = ( DefaultMutableTreeNode )obj;
			Object userObject = node.getUserObject();
			
			// fill drop down values depending on node type
			fillAutocomplete( userObject );

			if( !( userObject instanceof EditableNode ) )
			{
				return false;
			}
		}

		return super.isCellEditable( e );
	}
	
	private void fillAutocomplete( Object userObject )
	{
		if( userObject instanceof ArgumentNode )
		{
			autocompleteField.setItems( "Arguments", tree.getCurrentArguments() );
		}
		else if( userObject instanceof TypeNode )
		{
			autocompleteField.setItems( "Types", tree.getCurrentTypes() );
		}
		else if( userObject instanceof ValueNode )
		{
			autocompleteField.setItems( "Values", tree.getCurrentValues() );
		}
		else if( userObject instanceof ActionNode )
		{
			autocompleteField.setItems( "Actions", tree.getActions() );
		}
		else if( userObject instanceof GuardNode )
		{
			autocompleteField.setItems( "Guards", tree.getGuards() );
		}
		else if( userObject instanceof SimpleTransitionNode ||
				userObject instanceof InternalTransitionNode )
		{
			autocompleteField.setItems( "Messages", tree.getMessages() );
		}
	}
}
