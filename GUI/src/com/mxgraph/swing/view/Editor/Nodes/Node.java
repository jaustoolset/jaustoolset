package com.mxgraph.swing.view.Editor.Nodes;

import javax.swing.tree.DefaultMutableTreeNode;

public abstract class Node
{
	protected DefaultMutableTreeNode thisNode;
	private DefaultMutableTreeNode parentNode;
	private String str;
	
	public Node( String name, DefaultMutableTreeNode parent )
	{
		str = name;
		thisNode = new DefaultMutableTreeNode( this );
		parentNode = parent;
		
		if( parentNode == null )
		{
			return;
		}
		
		if( parentNode.getChildCount() == 0 )
		{
			parentNode.add( thisNode );
		}
		else
		{
			parentNode.insert( thisNode, parentNode.getChildCount() - 1 );
		}
	}
	
	protected void addChildMember( Object obj )
	{
		thisNode.add( new DefaultMutableTreeNode( obj ) );
	}

	public String toString()
	{
		if( str == null || str.isEmpty() )
		{
			return "empty:";
		}
		return str; 
	}
	
	public void setString( String s )
	{
		str = s;
	}
	
	public DefaultMutableTreeNode getNode()
	{
		return thisNode;
	}
}
