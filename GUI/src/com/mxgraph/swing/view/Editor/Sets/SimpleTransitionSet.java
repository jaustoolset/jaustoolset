package com.mxgraph.swing.view.Editor.Sets;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import com.mxgraph.swing.view.Editor.Nodes.SimpleTransitionNode;

public class SimpleTransitionSet extends ExtendableSet< org.jts.jsidl.binding.Transition, SimpleTransitionNode >
{

	public SimpleTransitionSet( DefaultMutableTreeNode parent )
	{
		super( "Simple Transitions", parent );
	}

	@Override
	public void addMember()
	{
		SimpleTransitionNode tr = new SimpleTransitionNode( thisNode );
	}

	@Override
	public ArrayList< org.jts.jsidl.binding.Transition > getNewT1List()
	{
		ArrayList< org.jts.jsidl.binding.Transition > output = new ArrayList< org.jts.jsidl.binding.Transition >();
		
		return output;
	}
	
	@Override
	public SimpleTransitionNode getNewT2()
	{
		SimpleTransitionNode node = new SimpleTransitionNode( thisNode );
		
		return node;
	}

}
