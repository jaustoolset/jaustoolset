package com.mxgraph.swing.view.Editor.Nodes;


import javax.swing.tree.DefaultMutableTreeNode;

import org.jts.validator.ValidatorException;

public class EndStateValueNode extends EditableDeletableNode
{

	public EndStateValueNode( String name, DefaultMutableTreeNode parent )
	{
		super(name, parent);

	}
	
	public void convertFrom( org.jts.jsidl.binding.EndState input )
	{
		setString( input.getState() );
	}
	
	public org.jts.jsidl.binding.EndState convertTo()
	{
		org.jts.jsidl.binding.EndState output = new org.jts.jsidl.binding.EndState();
		
		// name
		output.setState( toString() );
		
		return output;
	}

	@Override
	public void validate() throws ValidatorException
	{
		// check for namespacing
		// split doesn't take endings into account
		if( toString().endsWith( "." ) )
		{
			throw new ValidatorException( "Invalid C Name" );
		}

		String[] names = toString().split("\\.");

		for( String nm : names )
		{
			if( nm.toLowerCase().startsWith( nm.substring( 0, 1 ) ) )
			{
				throw new ValidatorException( "State Names must start with capital letter" );
			}

			// Make sure the string exists
			if( !org.jts.pbValidator.ValidatorUtils.verifyValidCNameValue( nm ) )
			{
				throw new ValidatorException( "Invalid C Name" );
			}
		}
	}
}
