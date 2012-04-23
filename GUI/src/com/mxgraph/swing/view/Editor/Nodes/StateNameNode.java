package com.mxgraph.swing.view.Editor.Nodes;

import javax.swing.tree.DefaultMutableTreeNode;

import org.jts.validator.ValidatorException;

public class StateNameNode extends EditableNode
{
	public StateNameNode( String name, DefaultMutableTreeNode parent )
	{
		super(name, parent);
	}

	@Override
	public void validate() throws ValidatorException
	{
		if( !org.jts.pbValidator.ValidatorUtils.verifyValidCNameValue( toString() ) )
		{
			throw new ValidatorException( "Invalid C Name" );
		}
		
		if( toString().toLowerCase().startsWith( toString().substring( 0, 1 ) ) )
		{
			throw new ValidatorException( "State Name must start with capital letter" );
		}
	}
}