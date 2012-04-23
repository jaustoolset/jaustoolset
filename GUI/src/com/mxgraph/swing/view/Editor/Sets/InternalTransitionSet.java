package com.mxgraph.swing.view.Editor.Sets;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import com.mxgraph.swing.view.Editor.Nodes.InternalTransitionNode;

public class InternalTransitionSet extends ExtendableSet< org.jts.jsidl.binding.Transition, InternalTransitionNode >
{

	public InternalTransitionSet( DefaultMutableTreeNode parent )
	{
		super( "Internal Transitions", parent );
	}

	@Override
	public void addMember()
	{
		InternalTransitionNode tr = new InternalTransitionNode( thisNode );
	}

	@Override
	public ArrayList< org.jts.jsidl.binding.Transition > getNewT1List()
	{
		ArrayList< org.jts.jsidl.binding.Transition > output = new ArrayList< org.jts.jsidl.binding.Transition >();
		
		return output;
	}
	
	@Override
	public InternalTransitionNode getNewT2()
	{
		InternalTransitionNode node = new InternalTransitionNode( thisNode );
		
		return node;
	}

}
