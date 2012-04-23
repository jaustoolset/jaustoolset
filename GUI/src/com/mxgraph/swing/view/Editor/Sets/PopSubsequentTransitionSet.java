package com.mxgraph.swing.view.Editor.Sets;

import javax.swing.tree.DefaultMutableTreeNode;

import com.mxgraph.swing.view.Editor.Nodes.PopSubsequentTransitionValueNode;

public class PopSubsequentTransitionSet extends NonExtendableSet< String, PopSubsequentTransitionValueNode >
{

	public PopSubsequentTransitionSet( DefaultMutableTreeNode parent )
	{
		super( "Transition", parent );
	}
	
	@Override
	protected void addMember()
	{
		// only add a child when there is only an addition node
		if( thisNode.getChildCount() == 1 )
		{
			PopSubsequentTransitionValueNode value = new PopSubsequentTransitionValueNode( "modifyName", thisNode );
		}
	}
	
	@Override
	public PopSubsequentTransitionValueNode getNewT2()
	{
		PopSubsequentTransitionValueNode node = new PopSubsequentTransitionValueNode( "modifyName", thisNode );
		
		return node;
	}
}
