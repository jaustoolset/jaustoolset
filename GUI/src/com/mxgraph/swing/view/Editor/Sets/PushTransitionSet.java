package com.mxgraph.swing.view.Editor.Sets;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import com.mxgraph.swing.view.Editor.Nodes.PushTransitionNode;

public class PushTransitionSet extends ExtendableSet< org.jts.jsidl.binding.Transition, PushTransitionNode >
{

	public PushTransitionSet( DefaultMutableTreeNode parent )
	{
		super( "Push Transitions", parent );
	}

	@Override
	public void addMember()
	{
		PushTransitionNode tr = new PushTransitionNode( thisNode );
	}

	@Override
	public ArrayList< org.jts.jsidl.binding.Transition > getNewT1List()
	{
		ArrayList< org.jts.jsidl.binding.Transition > output = new ArrayList< org.jts.jsidl.binding.Transition >();
		
		return output;
	}
	
	@Override
	public PushTransitionNode getNewT2()
	{
		PushTransitionNode node = new PushTransitionNode( thisNode );
		
		return node;
	}

}
