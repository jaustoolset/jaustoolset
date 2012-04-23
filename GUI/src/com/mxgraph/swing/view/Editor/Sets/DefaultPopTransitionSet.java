package com.mxgraph.swing.view.Editor.Sets;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import com.mxgraph.swing.view.Editor.Nodes.DefaultPopTransitionNode;


public class DefaultPopTransitionSet extends ExtendableSet< org.jts.jsidl.binding.DefaultTransition, DefaultPopTransitionNode >
{

	public DefaultPopTransitionSet( DefaultMutableTreeNode parent )
	{
		super( "Default Pop Transitions", parent );
	}

	@Override
	public void addMember()
	{
		DefaultPopTransitionNode tr = new DefaultPopTransitionNode( thisNode );
	}

	@Override
	public ArrayList< org.jts.jsidl.binding.DefaultTransition > getNewT1List()
	{
		ArrayList< org.jts.jsidl.binding.DefaultTransition > output = new ArrayList< org.jts.jsidl.binding.DefaultTransition >();
		
		return output;
	}
	
	@Override
	public DefaultPopTransitionNode getNewT2()
	{
		DefaultPopTransitionNode node = new DefaultPopTransitionNode( thisNode );
		
		return node;
	}

}
