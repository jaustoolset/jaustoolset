package com.mxgraph.swing.view.Editor.Nodes;


import javax.swing.tree.DefaultMutableTreeNode;

import com.mxgraph.swing.view.Editor.Sets.ActionSet;
import com.mxgraph.swing.view.Editor.Sets.GuardSet;


public abstract class DefaultTransitionNode extends NonEditableDeletableNode implements JaxbNodeConversion< org.jts.jsidl.binding.DefaultTransition >
{
	protected GuardSet guards;
	protected ActionSet actions;

	public DefaultTransitionNode( String name, DefaultMutableTreeNode parent )
	{
		super(name, parent);
	}

}
