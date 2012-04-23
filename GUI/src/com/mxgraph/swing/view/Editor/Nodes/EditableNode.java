package com.mxgraph.swing.view.Editor.Nodes;

import javax.swing.tree.DefaultMutableTreeNode;

import org.jts.validator.ValidatorException;


public abstract class EditableNode extends Node
{
	public EditableNode( String name, DefaultMutableTreeNode parent )
	{
		super( name, parent );
	}
	
	public abstract void validate() throws ValidatorException;
}
