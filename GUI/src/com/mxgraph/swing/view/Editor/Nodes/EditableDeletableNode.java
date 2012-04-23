package com.mxgraph.swing.view.Editor.Nodes;

import javax.swing.tree.DefaultMutableTreeNode;

import org.jts.validator.ValidatorException;


public abstract class EditableDeletableNode extends EditableNode
{

	public EditableDeletableNode( String name, DefaultMutableTreeNode parent )
	{
		super( name, parent);
	}
	
	@Override
	public abstract void validate() throws ValidatorException;
}
