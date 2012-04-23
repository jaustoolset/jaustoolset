package com.mxgraph.swing.view.Editor.Nodes;

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import com.mxgraph.swing.view.Editor.Sets.ArgumentsSet;
import org.jts.validator.ValidatorException;

public class ActionNode extends EditableDeletableNode implements JaxbNodeConversion< org.jts.jsidl.binding.Action >
{
	private ArgumentsSet arguments;

	public ActionNode( DefaultMutableTreeNode parent )
	{
		super( "modifyName", parent );
		
		arguments = new ArgumentsSet( thisNode );
	}
	
	public void convertFrom( org.jts.jsidl.binding.Action input )
	{
		setString( input.getName() );

		arguments.convertFrom( input.getArgument() );
	}
	
	public org.jts.jsidl.binding.Action convertTo()
	{
		org.jts.jsidl.binding.Action output = new org.jts.jsidl.binding.Action();
		
		output.setName( toString() );

		List< org.jts.jsidl.binding.Argument > addTo = output.getArgument();
		
		addTo.addAll( arguments.convertTo() );
		
		return output;
	}
	
	@Override
	public void validate() throws ValidatorException
	{
		if( !org.jts.pbValidator.ValidatorUtils.verifyValidCNameWithNamespace( toString() ) )
		{
			throw new ValidatorException( "Invalid C Name" );
		}
	}
}
