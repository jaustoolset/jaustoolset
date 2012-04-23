package com.mxgraph.swing.view.Editor.Nodes;


import javax.swing.tree.DefaultMutableTreeNode;

import org.jts.validator.ValidatorException;


public class PopSubsequentTransitionValueNode extends EditableDeletableNode implements JaxbNodeConversion< String >
{
	public PopSubsequentTransitionValueNode( String name, DefaultMutableTreeNode parent )
	{
		super(name, parent);

	}

	public void convertFrom( String input )
	{
		setString( input );
	}
	
	public String convertTo()
	{
		return toString();
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