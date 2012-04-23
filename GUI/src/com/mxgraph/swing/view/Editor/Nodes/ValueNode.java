package com.mxgraph.swing.view.Editor.Nodes;

import javax.swing.tree.DefaultMutableTreeNode;

import org.jts.validator.ValidatorException;


public class ValueNode extends EditableNode
{
	
	public ValueNode( String name, DefaultMutableTreeNode parent)
	{
		super( name, parent );
	}
	
	@Override
	public void validate() throws ValidatorException
	{
		if( !org.jts.pbValidator.ValidatorUtils.verifyValidCNameValue( toString() ) )
		{
			throw new ValidatorException( "Invalid C Name" );
		}
	}
}
