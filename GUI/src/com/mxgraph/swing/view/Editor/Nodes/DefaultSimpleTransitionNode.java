package com.mxgraph.swing.view.Editor.Nodes;

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import com.mxgraph.swing.view.Editor.Sets.ActionSet;
import com.mxgraph.swing.view.Editor.Sets.GuardSet;


public class DefaultSimpleTransitionNode extends DefaultTransitionNode
{
	
	public DefaultSimpleTransitionNode( DefaultMutableTreeNode parent )
	{		
		super( "default", parent );

		actions = new ActionSet( thisNode );
		guards = new GuardSet( thisNode );
	}

	public void convertFrom( org.jts.jsidl.binding.DefaultTransition input )
	{
		setString( "default" );

		guards.convertFrom( input.getGuard() );
		actions.convertFrom( input.getActionOrSendAction() );
	}
	
	public org.jts.jsidl.binding.DefaultTransition convertTo()
	{
		org.jts.jsidl.binding.DefaultTransition output = new org.jts.jsidl.binding.DefaultTransition();

		// guard
		org.jts.jsidl.binding.Guard gds = guards.convertTo();
		output.setGuard( gds );
		
		// actions
		List< Object > acts = output.getActionOrSendAction();
		acts.addAll( actions.convertTo() );

		// type
		org.jts.jsidl.binding.Simple tr = new org.jts.jsidl.binding.Simple();
		output.setSimple( tr );
		
		return output;
	}

}
