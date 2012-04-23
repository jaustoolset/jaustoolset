package com.mxgraph.swing.view.Editor.Sets;


import javax.swing.tree.DefaultMutableTreeNode;

public abstract class ZeroOrOneSet< T1, T2 > extends ExtendableSet< T1, T2 >
{

	public ZeroOrOneSet(String name, DefaultMutableTreeNode parent)
	{
		super(name, parent);
	}

	@Override
	protected void addMember()
	{
		// child count will be 1 because of the addition node which is a child of the set node
		// only add a child node when there is only this addition node
		if( thisNode.getChildCount() == 1 )
		{
			addSingleMember();
		}
	}
	
	protected abstract void addSingleMember();

}
