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

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.ArrayList;

import org.jts.gui.mxGraphtoJAXB.parser.StateParser;
import org.w3c.dom.Node;

/**
 *
 * @author Drew Lucas
 *
 */
class MxCellState
{
	List< ValidatorResult > results = null;

	MxCellState()
	{
		results = new ArrayList< ValidatorResult >();
	}

	List<ValidatorResult> validate( com.mxgraph.model.mxCell cell )
	{
		if( org.jts.pbValidator.ValidatorUtils.isMxState( cell ) )
		{
			if( !verifyIsContained( cell ) )
			{
				return results;
			}

			if( !verifyNumberDefaultStates( cell ) )
			{
				return results;
			}

			//---------- per unfixed SMC bug, state names must be capitalized ---------//
			verifyStateNameIsCapital( cell );
	
			// state must have a valid pseudo start state
			MxCellPseudoStartState pseudoStartValidate = new MxCellPseudoStartState();
			results.addAll( pseudoStartValidate.validate( cell ) );

			MxCell mxCell = new MxCell();

			// validate sub parts of the state
			for( int i = 0; i < cell.getChildCount(); i++ )
			{
				com.mxgraph.model.mxCell child = ( com.mxgraph.model.mxCell )cell.getChildAt( i );
				results.addAll( mxCell.validate( child ) );
			}
		}
		else
		{
			// validate successors
			results.addAll( ( new MxCellPseudoStartState() ).validate( cell ) );

			results.addAll( ( new MxCellState() ).validate( cell ) );

			results.addAll( ( new MxCellTransition() ).validate( cell ) );	
		}

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
				int index = value.indexOf( ";" );
				
				if( index != -1 )
				{
					buffer.insert( 0, "[StateMachine] " + value.substring( 0, value.indexOf( ";" ) ) );
				}
				else
				{
					buffer.insert( 0, "[StateMachine] " + value );
				}
			}
			else if( org.jts.pbValidator.ValidatorUtils.isMxPseudoStartState( cell ) )
			{
				buffer.insert( 0, "[PseudoStartState] " );
			}
			else if( org.jts.pbValidator.ValidatorUtils.isMxState( cell ) )
			{
				String value = ( String ) cell.getValue();
				int index = value.indexOf( ";" );
				
				if( index != -1 )
				{
					buffer.insert( 0, "[State] " + value.substring( 0, value.indexOf( ";" ) ) );
				}
				else
				{
					buffer.insert( 0, "[State] " + value );
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

	private boolean verifyIsContained( com.mxgraph.model.mxCell cell )
	{
		com.mxgraph.model.mxCell parent = ( com.mxgraph.model.mxCell ) cell.getParent();

		// a state must be contained in another state or a state machine
		if( parent == null || 
				!org.jts.pbValidator.ValidatorUtils.isMxState( parent ) &&
				!org.jts.pbValidator.ValidatorUtils.isMxStateMachine( parent ) )
		{
			results.add( new ValidatorResult( "State must be contained in a state or a state machine", getPath( cell ).toString(), ValidatorResult.TYPE_ERROR ) );
		}

		return true;
	}

	private boolean verifyNumberDefaultStates( com.mxgraph.model.mxCell cell )
	{
		if( cell == null )
		{
			return false;
		}

		int defaultStateCount = 0;
		int subStateCount = 0;
		for( int i = 0; i < cell.getChildCount(); i++ )
		{
			com.mxgraph.model.mxICell child = cell.getChildAt( i );

			if( org.jts.pbValidator.ValidatorUtils.isMxState( child ) )
			{
				subStateCount++;
			}
			else if( org.jts.pbValidator.ValidatorUtils.isMxDefaultState( child ) )
			{
				defaultStateCount++;
			}
		}

		// when the state has substates, there may be one or zero default states
		// CHANGED: 4/26/11 per dmm: states are allowed to have a zero or one default state
		if( defaultStateCount > 1 )
		{
			results.add( new ValidatorResult( "There may only be one default state at each level of nesting", getPath( cell ).toString(), ValidatorResult.TYPE_ERROR ) );
			return false;
		}
		//if( subStateCount > 0 )
		//{
		//	if( defaultStateCount > 1 )
		//	{
		//		results.add( new ValidatorResult( "There may only be one default state at each level of nesting", getPath( cell ).toString(), ValidatorResult.TYPE_ERROR ) );
		//		return false;
		//	}
		//}
		// otherwise, default states are not allowed
		//else if( defaultStateCount > 0 )
		//{
		//	results.add( new ValidatorResult( "There may only be default states when there are states at the same level of nesting", getPath( cell ).toString(), ValidatorResult.TYPE_ERROR ) );
		//	return false;
		//}
		

		return true;
	}

	/**
	 * SMC has an unfixed bug which requires state names be be capital.  This method
	 * checks for a capital start and changes it to upper case when the state name starts
	 * with lower case.  A warning is then sent to the user notifying of the change
	 * @param state
	 * @param successorPath
	 */
	private void verifyStateNameIsCapital( com.mxgraph.model.mxCell cell )
	{
		try
		{
			String value = ( String )cell.getValue();
			ByteArrayInputStream stream = new ByteArrayInputStream( value.getBytes() );

			StateParser parser = new StateParser( stream );
			parser.Input();

			String stateName = parser.name;

			String upperCase = stateName.substring( 0, 1 ).toUpperCase().concat( stateName.substring( 1 ) );

			if( stateName.compareTo( upperCase ) != 0 )
			{
				// change the name of the state
				// assume there is only one instance of the name in the cell value
				cell.setValue( value.replace( stateName, upperCase ) );

				results.add( new ValidatorResult( "State names must be capital, name modified to " + upperCase, getPath( cell ).toString(), ValidatorResult.TYPE_WARNING ) );
			}
		}
		catch( Exception e )
		{
			System.out.println("State validation: verifyStateNameIsCapital: " + e.toString() );
		}
	}
}