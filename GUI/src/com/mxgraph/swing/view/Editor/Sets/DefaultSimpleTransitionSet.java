package com.mxgraph.swing.view.Editor.Sets;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import com.mxgraph.swing.view.Editor.Nodes.DefaultSimpleTransitionNode;


public class DefaultSimpleTransitionSet extends ExtendableSet< org.jts.jsidl.binding.DefaultTransition, DefaultSimpleTransitionNode >
{

	public DefaultSimpleTransitionSet( DefaultMutableTreeNode parent )
	{
		super( "Default Simple Transitions", parent );
	}

	@Override
	public void addMember()
	{
		DefaultSimpleTransitionNode tr = new DefaultSimpleTransitionNode( thisNode );
	}

	@Override
	public ArrayList< org.jts.jsidl.binding.DefaultTransition > getNewT1List()
	{
		ArrayList< org.jts.jsidl.binding.DefaultTransition > output = new ArrayList< org.jts.jsidl.binding.DefaultTransition >();
		
		return output;
	}
	
	@Override
	public DefaultSimpleTransitionNode getNewT2()
	{
		DefaultSimpleTransitionNode node = new DefaultSimpleTransitionNode( thisNode );
		
		return node;
	}

}
