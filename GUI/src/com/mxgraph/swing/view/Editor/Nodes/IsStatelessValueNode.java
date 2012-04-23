package com.mxgraph.swing.view.Editor.Nodes;

import javax.swing.tree.DefaultMutableTreeNode;

import org.jts.validator.ValidatorException;

public class IsStatelessValueNode extends EditableNode
{
	
	public IsStatelessValueNode( String name, DefaultMutableTreeNode parent )
	{
		super(name, parent);

	}

	@Override
	public void validate() throws ValidatorException
	{
		if( !toString().equalsIgnoreCase( "true" ) && 
				!toString().equalsIgnoreCase( "false" ) )
		{
			throw new ValidatorException( "Must be true or false" );
		}
	}
}
