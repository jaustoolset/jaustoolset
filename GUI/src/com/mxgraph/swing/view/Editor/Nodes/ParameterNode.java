package com.mxgraph.swing.view.Editor.Nodes;

import javax.swing.tree.DefaultMutableTreeNode;


public class ParameterNode extends NonEditableDeletableNode implements JaxbNodeConversion< org.jts.jsidl.binding.Parameter >
{
	private TypeNode type;
	private ValueNode value;

	public ParameterNode( DefaultMutableTreeNode parent )
	{
		super( "parameter", parent );

		value = new ValueNode( "modifyName", thisNode );
		type = new TypeNode( "modifyName", thisNode );
	}
	
	public void convertFrom( org.jts.jsidl.binding.Parameter input )
	{
		type.setString( input.getType() );
		value.setString( input.getValue() );
	}
	
	public org.jts.jsidl.binding.Parameter convertTo()
	{
		org.jts.jsidl.binding.Parameter output = new org.jts.jsidl.binding.Parameter();
		
		output.setType( type.toString() );
		output.setValue( value.toString() );
		
		return output;
	}
}
