package com.mxgraph.swing.view.Editor.Nodes;

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import org.jts.validator.ValidatorException;

import com.mxgraph.swing.view.Editor.Sets.ActionSet;
import com.mxgraph.swing.view.Editor.Sets.GuardSet;
import com.mxgraph.swing.view.Editor.Sets.ParameterSet;
import com.mxgraph.swing.view.Editor.Sets.PopSubsequentTransitionSet;

public class PopTransitionNode extends TransitionNode
{
	PopSubsequentTransitionSet subsequent;
	
	public PopTransitionNode( DefaultMutableTreeNode parent )
	{		
		super( "modifyName", parent );

		actions = new ActionSet( thisNode );
		guards = new GuardSet( thisNode );
		parameters = new ParameterSet( thisNode );
		subsequent = new PopSubsequentTransitionSet( thisNode );
	}

	public void convertFrom( org.jts.jsidl.binding.Transition input )
	{
		setString( input.getName() );

		parameters.convertFrom( input.getParameter() );
		guards.convertFrom( input.getGuard() );
		actions.convertFrom( input.getActionOrSendAction() );
		subsequent.convertFrom( input.getPop().getTransition() );
	}
	
	public org.jts.jsidl.binding.Transition convertTo()
	{
		org.jts.jsidl.binding.Transition output = new org.jts.jsidl.binding.Transition();
		
		// name
		output.setName( toString() );

		// guard
		org.jts.jsidl.binding.Guard gds = guards.convertTo();
		output.setGuard( gds );
		
		// actions
		List< Object > acts = output.getActionOrSendAction();
		acts.addAll( actions.convertTo() );
		
		// parameters
		List< org.jts.jsidl.binding.Parameter > parms = output.getParameter();
		parms.addAll( parameters.convertTo() );

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
	
	@Override
	public void validate() throws ValidatorException
	{
		if( !org.jts.pbValidator.ValidatorUtils.verifyValidCNameWithNamespace( toString() ) )
		{
			throw new ValidatorException( "Invalid C Name" );
		}
	}
}
