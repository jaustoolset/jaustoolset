package com.mxgraph.swing.view.Editor.Sets;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import com.mxgraph.swing.view.Editor.Nodes.ActionNode;

public class ExitActionSet extends ExtendableSet< org.jts.jsidl.binding.Action, ActionNode >
{
	public ExitActionSet( DefaultMutableTreeNode parent )
	{
		super( "Exit Actions", parent );
	}

	@Override
	protected void addMember()
	{
		ActionNode action = new ActionNode( thisNode );
	}

	@Override
	public ArrayList< org.jts.jsidl.binding.Action > getNewT1List()
	{
		ArrayList< org.jts.jsidl.binding.Action > output = new ArrayList< org.jts.jsidl.binding.Action >();
		
		return output;
	}

	@Override
	public ActionNode getNewT2()
	{
		ActionNode node = new ActionNode( thisNode );
		
		return node;
	}
}
