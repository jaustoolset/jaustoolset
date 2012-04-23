package com.mxgraph.swing.view.Editor.Nodes;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import org.jts.jsidl.binding.EndState;

import com.mxgraph.swing.view.Editor.Sets.ActionSet;
import com.mxgraph.swing.view.Editor.Sets.EndStateSet;
import com.mxgraph.swing.view.Editor.Sets.GuardSet;


public class DefaultPushTransitionNode extends DefaultTransitionNode
{
	private EndStateSet endState;

	public DefaultPushTransitionNode( DefaultMutableTreeNode parent )
	{		
		super( "default", parent );

		actions = new ActionSet( thisNode );
		guards = new GuardSet( thisNode );
		endState = new EndStateSet( "Simple Transition End State", thisNode );
	}

	public void convertFrom( org.jts.jsidl.binding.DefaultTransition input )
	{
		setString( "default" );

		guards.convertFrom( input.getGuard() );
		actions.convertFrom( input.getActionOrSendAction() );

		if( input.getPush().getSimple() != null )
		{
			endState.convertFrom( input.getPush().getSimple().getEndState() );
		}
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
		org.jts.jsidl.binding.Push tr = new org.jts.jsidl.binding.Push();
		output.setPush( tr );
		
		// optional simple transition with an end state
		ArrayList< EndState > endStates = endState.convertTo();
		if( endStates.size() > 0 )
		{
			org.jts.jsidl.binding.EndState end = endStates.get( 0 );
			org.jts.jsidl.binding.Simple simple = new org.jts.jsidl.binding.Simple();
			simple.setEndState( end );
			tr.setSimple( simple );
		}
		
		return output;
	}

}
