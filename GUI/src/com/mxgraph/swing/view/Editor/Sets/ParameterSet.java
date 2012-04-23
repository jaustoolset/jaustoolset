package com.mxgraph.swing.view.Editor.Sets;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import com.mxgraph.swing.view.Editor.Nodes.ParameterNode;

public class ParameterSet extends ExtendableSet< org.jts.jsidl.binding.Parameter, ParameterNode >
{

	public ParameterSet( DefaultMutableTreeNode parent )
	{
		super( "Parameters", parent );
	}

	@Override
	protected void addMember()
	{
		ParameterNode parameter = new ParameterNode( thisNode );
	}

	@Override
	public ArrayList< org.jts.jsidl.binding.Parameter > getNewT1List()
	{
		ArrayList< org.jts.jsidl.binding.Parameter > output = new ArrayList< org.jts.jsidl.binding.Parameter >();
		
		return output;
	}
	
	@Override
	public ParameterNode getNewT2()
	{
		ParameterNode node = new ParameterNode( thisNode );
		
		return node;
	}
}