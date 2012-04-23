package com.mxgraph.swing.view.Editor.Sets;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import com.mxgraph.swing.view.Editor.Nodes.PopTransitionNode;

public class PopTransitionSet extends ExtendableSet< org.jts.jsidl.binding.Transition, PopTransitionNode >
{

	public PopTransitionSet( DefaultMutableTreeNode parent )
	{
		super( "Pop Transitions", parent );
	}

	@Override
	public void addMember()
	{
		PopTransitionNode tr = new PopTransitionNode( thisNode );
	}

	@Override
	public ArrayList< org.jts.jsidl.binding.Transition > getNewT1List()
	{
		ArrayList< org.jts.jsidl.binding.Transition > output = new ArrayList< org.jts.jsidl.binding.Transition >();
		
		return output;
	}
	
	@Override
	public PopTransitionNode getNewT2()
	{
		PopTransitionNode node = new PopTransitionNode( thisNode );
		
		return node;
	}

}
