/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2010, United States Government
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

       Redistributions of source code must retain the above copyright notice,
this list of conditions and the following disclaimer.

       Redistributions in binary form must reproduce the above copyright
notice, this list of conditions and the following disclaimer in the
documentation and/or other materials provided with the distribution.

       Neither the name of the United States Government nor the names of
its contributors may be used to endorse or promote products derived from
this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.
*********************  END OF LICENSE ***********************************/

package org.jts.pbValidator;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Drew Lucas
 *
 */
class MxCellPseudoStartState
{
	List<ValidatorResult> results = null;

	MxCellPseudoStartState()
	{
		results = new ArrayList< ValidatorResult >();
	}

	List<ValidatorResult> validate( com.mxgraph.model.mxCell cell )
	{
		if( !verifyPseudoStartStateNesting( cell ) )
		{
			return results;
		}
		
		if( !verifyPseudoStartStateLink( cell ) )
		{
			return results;
		}

		// pseudo start state cell will have no children to validate

		return results;
	}

	private StringBuffer getPath( com.mxgraph.model.mxCell cell )
	{
		StringBuffer buffer = new StringBuffer();

		// continue up chain until the root is reached ( don't process it )
		while( cell.getParent() != null && cell.getParent().getParent() != null )
		{
			if( org.jts.pbValidator.ValidatorUtils.isMxStateMachine( cell ) )
			{
				String value = ( String ) cell.getValue();

				// values not validated yet
				if( value.indexOf( ";" ) == -1 )
				{
					buffer.insert( 0, "[StateMachine] " + value );
				}
				else
				{
					buffer.insert( 0, "[StateMachine] " + value.substring( 0, value.indexOf( ";" ) ) );
				}
			}
			else if( org.jts.pbValidator.ValidatorUtils.isMxPseudoStartState( cell ) )
			{
				buffer.insert( 0, "[PseudoStartState] " );
			}
			else if( org.jts.pbValidator.ValidatorUtils.isMxState( cell ) )
			{
				String value = ( String ) cell.getValue();

				// values not validated yet
				if( value.indexOf( ";" ) == -1 )
				{
					buffer.insert( 0, "[StateMachine] " + value );
				}
				else
				{
					buffer.insert( 0, "[StateMachine] " + value.substring( 0, value.indexOf( ";" ) ) );
				}
			}
			else if( org.jts.pbValidator.ValidatorUtils.isMxTransition( cell ) )
			{
				buffer.insert( 0, "[Transition] " );
			}

			buffer.insert( 0, " : " );

			cell = ( com.mxgraph.model.mxCell ) cell.getParent();
		}
		
		return buffer;
	}

	private boolean verifyPseudoStartStateNestingInStateMachine( com.mxgraph.model.mxCell cell )
	{
		// deal with state cell
		if( org.jts.pbValidator.ValidatorUtils.isMxStateMachine( cell ) )
		{
			// a state machine must contain a pseudo start state when there are nested states
			int pseudoCount = 0;
			int subStateCount = 0;
			for( int i = 0; i < cell.getChildCount(); i++ )
			{
				com.mxgraph.model.mxICell child = cell.getChildAt( i );

				if( org.jts.pbValidator.ValidatorUtils.isMxPseudoStartState( child ) )
				{
					pseudoCount++;
				}
				else if( org.jts.pbValidator.ValidatorUtils.isMxState( child ) )
				{
					subStateCount++;
				}
			}

			// when the SM has substates, there must be a single pseudo state at the same level
			if( subStateCount > 0 )
			{
				if( pseudoCount == 0 )
				{
					results.add( new ValidatorResult( "There must be a pseudo start state at each level of nesting", getPath( cell ).toString(), ValidatorResult.TYPE_ERROR ) );
					return false;
				}
				else if( pseudoCount > 1 )
				{
					results.add( new ValidatorResult( "There must only be one pseudo start state at each level of nesting", getPath( cell ).toString(), ValidatorResult.TYPE_ERROR ) );
					return false;
				}
			}
		}

		return true;
	}

	private boolean verifyPseudoStartStateNestingInState( com.mxgraph.model.mxCell cell )
	{
		// deal with state cell
		if( org.jts.pbValidator.ValidatorUtils.isMxState( cell ) )
		{
			// pseudo start state must be a child of the state when there are nested states 

			int pseudoCount = 0;
			int subStateCount = 0;
			for( int i = 0; i < cell.getChildCount(); i++ )
			{
				com.mxgraph.model.mxICell child = cell.getChildAt( i );

				if( org.jts.pbValidator.ValidatorUtils.isMxPseudoStartState( child ) )
				{
					pseudoCount++;
				}
				else if( org.jts.pbValidator.ValidatorUtils.isMxState( child ) )
				{
					subStateCount++;
				}
			}

			// when the parent has substates, there must be a single pseudo state at the same level
			if( subStateCount > 0 )
			{
				if( pseudoCount == 0 )
				{
					results.add( new ValidatorResult( "There must be a pseudo start state at each level of nesting", getPath( cell ).toString(), ValidatorResult.TYPE_ERROR ) );
					return false;
				}
				else if( pseudoCount > 1 )
				{
					results.add( new ValidatorResult( "There must only be one pseudo start state at each level of nesting", getPath( cell ).toString(), ValidatorResult.TYPE_ERROR ) );
					return false;
				}
			}
		}

		return true;
	}	

	private boolean verifyPseudoStartStateNestingInPseudoStartState( com.mxgraph.model.mxCell cell )
	{
		// deal with pseudo start state cell
		if( org.jts.pbValidator.ValidatorUtils.isMxPseudoStartState( cell ) )
		{
			// pseudo start state cell will be the child of the state/ state machine we are trying to check
			com.mxgraph.model.mxCell parent = ( com.mxgraph.model.mxCell )cell.getParent();

			if( parent == null || 
					!org.jts.pbValidator.ValidatorUtils.isMxState( parent ) &&
					!org.jts.pbValidator.ValidatorUtils.isMxStateMachine( parent ) )
			{
				results.add( new ValidatorResult( "Pseudo start state must be nested in a state or state machine", getPath( cell ).toString(), ValidatorResult.TYPE_ERROR ) );
				return false;
			}

			int pseudoCount = 0;
			int subStateCount = 0;
			for( int i = 0; i < parent.getChildCount(); i++ )
			{
				com.mxgraph.model.mxICell child = parent.getChildAt( i );

				if( org.jts.pbValidator.ValidatorUtils.isMxPseudoStartState( child ) )
				{
					pseudoCount++;
				}
				else if( org.jts.pbValidator.ValidatorUtils.isMxState( child ) )
				{
					subStateCount++;
				}
			}

			// when the parent has substates, there must be a single pseudo state at the same level
			if( subStateCount > 0 )
			{
				if( pseudoCount > 1 )
				{
					results.add( new ValidatorResult( "There must only be one pseudo start state at each level of nesting", getPath( cell ).toString(), ValidatorResult.TYPE_ERROR ) );
					return false;
				}
			}
			// when no substates were found for the input pseudo start states to match, return error
			else
			{
				results.add( new ValidatorResult( "Pseudo start state not valid when there are no substates", getPath( cell ).toString(), ValidatorResult.TYPE_ERROR ) );
				return false;			
			}
		}

		return true;
	}

	private boolean verifyPseudoStartStateNesting( com.mxgraph.model.mxCell cell )
	{
		boolean result1 = verifyPseudoStartStateNestingInStateMachine( cell );
		boolean result2 = verifyPseudoStartStateNestingInState( cell );
		boolean result3 = verifyPseudoStartStateNestingInPseudoStartState( cell );

		return result1 && result2 && result3;
	}

	private boolean verifyPseudoStartStateLink( com.mxgraph.model.mxCell cell )
	{
		if( org.jts.pbValidator.ValidatorUtils.isMxPseudoStartState( cell ) )
		{
			// each pseudo start state must have a transition attached to it
			if( cell.getEdgeCount() == 0 )
			{
				results.add( new ValidatorResult( "Pseudo Start State must transition to a state", getPath( cell ).toString(), ValidatorResult.TYPE_ERROR ) );
				return false;
			}
		
			if( cell.getEdgeCount() > 1 )
			{
				results.add( new ValidatorResult( "Pseudo Start State must only have a single transition", getPath( cell ).toString(), ValidatorResult.TYPE_ERROR ) );
				return false;
			}			
		
			com.mxgraph.model.mxCell transition = ( com.mxgraph.model.mxCell ) cell.getEdgeAt( 0 );
		
			if( !org.jts.pbValidator.ValidatorUtils.isMxSimpleTransition( transition ) )
			{
				results.add( new ValidatorResult( "Pseudo Start State transition must be a simple transition", getPath( cell ).toString(), ValidatorResult.TYPE_ERROR ) );
				return false;
			}
		
			// target state of the transition must be a state
			if( !org.jts.pbValidator.ValidatorUtils.isMxState( transition.getTarget() ) )
			{
				results.add( new ValidatorResult( "Pseudo Start State transition point to a state", getPath( cell ).toString(), ValidatorResult.TYPE_ERROR ) );
				return false;
			}
		}

		return true;
	}
}