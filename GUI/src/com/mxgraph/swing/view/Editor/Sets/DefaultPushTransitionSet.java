package com.mxgraph.swing.view.Editor.Sets;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import com.mxgraph.swing.view.Editor.Nodes.DefaultPushTransitionNode;


public class DefaultPushTransitionSet extends ExtendableSet< org.jts.jsidl.binding.DefaultTransition, DefaultPushTransitionNode >
{

	public DefaultPushTransitionSet( DefaultMutableTreeNode parent )
	{
		super( "Default Push Transitions", parent );
	}

	@Override
	public void addMember()
	{
		DefaultPushTransitionNode tr = new DefaultPushTransitionNode( thisNode );
	}

	@Override
	public ArrayList< org.jts.jsidl.binding.DefaultTransition > getNewT1List()
	{
		ArrayList< org.jts.jsidl.binding.DefaultTransition > output = new ArrayList< org.jts.jsidl.binding.DefaultTransition >();
		
		return output;
	}
	
	@Override
	public DefaultPushTransitionNode getNewT2()
	{
		DefaultPushTransitionNode node = new DefaultPushTransitionNode( thisNode );
		
		return node;
	}

}
