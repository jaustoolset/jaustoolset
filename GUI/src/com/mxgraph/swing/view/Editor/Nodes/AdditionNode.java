package com.mxgraph.swing.view.Editor.Nodes;



public class AdditionNode
{
	private NodeSet parent;
	
	public AdditionNode( NodeSet set )
	{
		parent = set;
	}
	
	public String toString()
	{
		return "Add";
	}
	
	public void addMember()
	{
		parent.addMember();
	}
}
