package com.mxgraph.swing.view.Editor.Nodes;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import com.mxgraph.swing.view.Editor.Sets.ActionSet;
import com.mxgraph.swing.view.Editor.Sets.GuardSet;
import com.mxgraph.swing.view.Editor.Sets.ParameterSet;

public abstract class TransitionNode extends EditableDeletableNode implements JaxbNodeConversion< org.jts.jsidl.binding.Transition >
{
	protected ParameterSet parameters;
	protected GuardSet guards;
	protected ActionSet actions;

	public TransitionNode( String name, DefaultMutableTreeNode parent )
	{
		super(name, parent);
	}
	
	/**
	 * Gets a list of strings which correspond to the parameter types listed
	 * for the current transition.  This will be a 'live' list which is updated
	 * using the tree model stored for the parameter set node
	 * @return
	 */
	public ArrayList< String > getParameterTypeStrings()
	{
		ArrayList< String > types = new ArrayList< String >();
		
		if( parameters != null )
		{
			ArrayList< org.jts.jsidl.binding.Parameter > parms = parameters.convertTo();
			
			for( org.jts.jsidl.binding.Parameter parm : parms )
			{
				types.add( parm.getType() );
			}
		}
		
		return types;
	}

	/**
	 * Gets a list of strings which correspond to the parameter types listed
	 * for the current transition.  This will be a 'live' list which is updated
	 * using the tree model stored for the parameter set node
	 * @return
	 */
	public ArrayList< String > getParameterValueStrings()
	{
		ArrayList< String > values = new ArrayList< String >();
		
		if( parameters != null )
		{
			ArrayList< org.jts.jsidl.binding.Parameter > parms = parameters.convertTo();
			
			for( org.jts.jsidl.binding.Parameter parm : parms )
			{
				values.add( parm.getValue() );
			}
		}
		
		return values;
	}
}
