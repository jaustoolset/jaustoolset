package com.mxgraph.swing.view.Editor.Sets;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import com.mxgraph.swing.view.Editor.Nodes.GuardLinkNode;

public class GuardLinkSet extends ZeroOrOneSet< String, GuardLinkNode >
{
	public GuardLinkSet(String name, DefaultMutableTreeNode parent)
	{
		super(name, parent);
	}

	@Override
	public GuardLinkNode getNewT2()
	{
		return new GuardLinkNode( "&&", thisNode );
	}

	@Override
	protected void addSingleMember()
	{
		GuardLinkNode tr = new GuardLinkNode( "&&", thisNode );
	}

	@Override
	public ArrayList< String > getNewT1List()
	{
		ArrayList< String > output = new ArrayList< String >();
		
		return output;
	}

}