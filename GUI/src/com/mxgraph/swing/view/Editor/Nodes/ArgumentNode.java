package com.mxgraph.swing.view.Editor.Nodes;

import javax.swing.tree.DefaultMutableTreeNode;

import org.jts.validator.ValidatorException;


public class ArgumentNode extends EditableDeletableNode implements JaxbNodeConversion< org.jts.jsidl.binding.Argument >
{
	public ArgumentNode( DefaultMutableTreeNode parent )
	{
		super( "modifyName", parent );
	}

	public void convertFrom( org.jts.jsidl.binding.Argument input )
	{
		setString( input.getValue() );
	}
	
	public org.jts.jsidl.binding.Argument convertTo()
	{
		org.jts.jsidl.binding.Argument output = new org.jts.jsidl.binding.Argument();
		
		output.setValue( toString() );
		
		return output;
	}
	
	@Override
	public void validate() throws ValidatorException
	{
		boolean validCName = org.jts.pbValidator.ValidatorUtils.verifyValidCNameValue( toString() );
		boolean validStringArgument = org.jts.validator.Argument.isValueValidString( toString() );
		if( !validStringArgument && !validCName )
		{
			throw new ValidatorException( "Argument must be valid C Name or string literal" );
		}
	}
}
