package com.mxgraph.swing.view.Editor.Nodes;

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import com.mxgraph.swing.view.Editor.Sets.ActionSet;
import com.mxgraph.swing.view.Editor.Sets.GuardSet;
import com.mxgraph.swing.view.Editor.Sets.PopSubsequentTransitionSet;


public class DefaultPopTransitionNode extends DefaultTransitionNode
{
	PopSubsequentTransitionSet subsequent;
	
	public DefaultPopTransitionNode( DefaultMutableTreeNode parent )
	{		
		super( "default", parent );

		actions = new ActionSet( thisNode );
		guards = new GuardSet( thisNode );
		subsequent = new PopSubsequentTransitionSet( thisNode );
	}

	public void convertFrom( org.jts.jsidl.binding.DefaultTransition input )
	{
		setString( "default" );

		guards.convertFrom( input.getGuard() );
		actions.convertFrom( input.getActionOrSendAction() );
		subsequent.convertFrom( input.getPop().getTransition() );
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
		org.jts.jsidl.binding.Pop tr = new org.jts.jsidl.binding.Pop();
		output.setPop( tr );
		
		// transition
		String value = subsequent.convertTo();
		if( value != null )
		{
			tr.setTransition( value );
		}
		
		return output;
	}

}
