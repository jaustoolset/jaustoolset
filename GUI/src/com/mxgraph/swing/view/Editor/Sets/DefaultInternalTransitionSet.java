package com.mxgraph.swing.view.Editor.Sets;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import com.mxgraph.swing.view.Editor.Nodes.DefaultInternalTransitionNode;


public class DefaultInternalTransitionSet extends ExtendableSet< org.jts.jsidl.binding.DefaultTransition, DefaultInternalTransitionNode >
{

	public DefaultInternalTransitionSet( DefaultMutableTreeNode parent )
	{
		super( "Default Internal Transitions", parent );
	}

	@Override
	public void addMember()
	{
		DefaultInternalTransitionNode tr = new DefaultInternalTransitionNode( thisNode );
	}

	@Override
	public ArrayList< org.jts.jsidl.binding.DefaultTransition > getNewT1List()
	{
		ArrayList< org.jts.jsidl.binding.DefaultTransition > output = new ArrayList< org.jts.jsidl.binding.DefaultTransition >();
		
		return output;
	}
	
	@Override
	public DefaultInternalTransitionNode getNewT2()
	{
		DefaultInternalTransitionNode node = new DefaultInternalTransitionNode( thisNode );
		
		return node;
	}

}
