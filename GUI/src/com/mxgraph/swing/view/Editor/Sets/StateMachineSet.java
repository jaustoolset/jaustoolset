package com.mxgraph.swing.view.Editor.Sets;

import javax.swing.tree.DefaultMutableTreeNode;

import com.mxgraph.swing.view.Editor.Nodes.StateMachineNode;

public class StateMachineSet extends NonExtendableSet< org.jts.jsidl.binding.StateMachine, StateMachineNode >
{

	public StateMachineSet( String name, DefaultMutableTreeNode parent )
	{
		super(name, parent);

	}

	@Override
	public StateMachineNode getNewT2()
	{
		return null;
	}

	@Override
	protected void addMember()
	{
		
	}
}
