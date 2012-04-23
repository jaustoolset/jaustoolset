package com.mxgraph.swing.view.Editor.Sets;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import com.mxgraph.swing.view.Editor.Nodes.ArgumentNode;

public class ArgumentsSet extends ExtendableSet< org.jts.jsidl.binding.Argument, ArgumentNode >
{
	public ArgumentsSet( DefaultMutableTreeNode parent )
	{
		super( "Arguments", parent );
	}

	@Override
	protected void addMember()
	{
		ArgumentNode argument = new ArgumentNode( thisNode );
	}

	@Override
	public ArrayList< org.jts.jsidl.binding.Argument > getNewT1List()
	{
		ArrayList< org.jts.jsidl.binding.Argument > output = new ArrayList< org.jts.jsidl.binding.Argument >();
		
		return output;
	}
	
	@Override
	public ArgumentNode getNewT2()
	{
		ArgumentNode node = new ArgumentNode( thisNode );
		
		return node;
	}
}
