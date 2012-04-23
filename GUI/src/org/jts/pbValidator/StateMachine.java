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
import java.util.regex.Pattern;

/**
*
* @author Arfath Pasha
*
*/
class StateMachine
{
	List<ValidatorResult> results = null;

	StateMachine()
	{
		results = new ArrayList();
	}

	List<ValidatorResult> validate( List<org.jts.jsidl.binding.ServiceDef> sdefs, Object toValidate, StringBuffer path )
	{
		if( toValidate instanceof org.jts.jsidl.binding.StateMachine )
		{
			// cast object into state machine
			org.jts.jsidl.binding.StateMachine sm = (org.jts.jsidl.binding.StateMachine)toValidate;

			// update path
			updatePath( sm, path );

			// validate state
			validateStateMachine( sdefs, sm, path );
		}
		else
		{
			// validate successors
			results.addAll( (new State()).validate( sdefs, toValidate, path ) );

			results.addAll( (new PseudoStartState()).validate( sdefs, toValidate, path ) );

			results.addAll( (new MxCell()).validate( sdefs, toValidate, path ) );
		}

		return results;
	}

	/**
	 * Updates the error path to include the state machine
	 * @param sm
	 * @param path
	 */
	private void updatePath( org.jts.jsidl.binding.StateMachine sm, StringBuffer path )
	{
		String name = sm.getName();
		int dot = name.lastIndexOf('.');

		if ( dot != -1 )
		{
			name = name.substring( dot+1 ) ;
		}

		path.append( name +"[StateMachine]");
	}

	/**
	 * Validates the state machine by verifying
	 * -has a pseudo start state
	 * -name is unique throughout inheritance
	 * -valid C name
	 * -has at least one state, zero if there is a default state
	 * and validates the state machine's
	 * -states
	 * -pseudo start state
	 * -mxcell
	 * Expects a non-null state machine
	 * @param sdefs
	 * @param sm
	 * @param successorPath
	 */
	private void validateStateMachine( List<org.jts.jsidl.binding.ServiceDef> sdefs, org.jts.jsidl.binding.StateMachine sm, StringBuffer successorPath )
	{
		//---------- check pseudo start state at each level of nesting ----------//
		if( !verifyPseudoStartStateExistance( sm, successorPath ) )
			return;

		//---------- names of concurrent state machines must be unique in the inheritance ----------//
		if( !verifyUniqueStateMachineNames( sdefs, sm, successorPath ) )
			return;

		//---------- name of state machine must be valid ----------//
		if( !verifyCorrectStateMachineName( sm, successorPath ) )
			return;

		//---------- must have at least one state (zero if there is a default state) ----------//
		if( !verifySingleState( sm, successorPath ) )
			return;
		
		if( !verifyUniqueSubStateNames( sm, successorPath ) )
			return;

		// Validate states within the state machine
		List<org.jts.jsidl.binding.State> states = sm.getState();
		org.jts.pbValidator.StateMap map = new org.jts.pbValidator.StateMap( sm );
		if( states != null )
		{
			for( org.jts.jsidl.binding.State state : states )
			{
				//results.addAll( (new State()).validate( sdefs, sm, state, new StringBuffer( successorPath ) ) );
				results.addAll( (new State()).validate( sdefs, map.getChild( state.getName() ), new StringBuffer( successorPath ) ) );
			}
		}

		// Validate pseudostart state within the state machine
		org.jts.jsidl.binding.PseudoStartState pss = sm.getPseudoStartState();
		if( pss != null )
		{
			results.addAll( (new PseudoStartState()).validate( sdefs, pss, new StringBuffer( successorPath ) ) );
		}

		// Validate mxCells within the state machine
		org.jts.jsidl.binding.MxCell cell = sm.getMxCell();
		if( cell != null )
		{
			results.addAll( (new MxCell()).validate( sdefs, cell, new StringBuffer( successorPath ) ) );
		}
	}

	/**
	 * Verifies that the state machine has a pseudo start state
	 * Expects a non-null state machine
	 * @param sm
	 * @param successorPath
	 * @return
	 */
	private boolean verifyPseudoStartStateExistance( org.jts.jsidl.binding.StateMachine sm, StringBuffer successorPath )
	{
		if( sm.getPseudoStartState() == null )
		{
			// removed condition because we have to verify non-mxgraph state machines
			//results.add( new ValidatorResult( "State machine must have pseudo start state", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
			// TODO:continue validation?
		}

		return true;
	}

	/**
	 * Verifies that the state machine name is unique by checking against all state machine names in all service defs
	 * Expects a non-null state machine
	 * Expects a non-null list of service definitions 
	 * @param sdefs
	 * @param sm
	 * @param successorPath
	 * @return
	 */
	private boolean verifyUniqueStateMachineNames( List<org.jts.jsidl.binding.ServiceDef> sdefs, org.jts.jsidl.binding.StateMachine sm, StringBuffer successorPath )
	{
		for( org.jts.jsidl.binding.ServiceDef serviceDefCompare : sdefs )
		{
			int count = 0;

			for( org.jts.jsidl.binding.StateMachine stateMachineCompare : serviceDefCompare.getProtocolBehavior().getStateMachine() )
			{
				String stateMachineNameCompare = stateMachineCompare.getName();

				if( sm.getName().compareTo(stateMachineNameCompare) == 0 )
				{
					if( ++count > 1 )
					{
						results.add( new ValidatorResult( "State Machine name must be unique within the inheritance chain: " + sm.getName(), successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
						// TODO:continue validation?
						break;
					}
				}
			}
		}

		return true;
	}

	/**
	 * Verifies that the state machine has a name that is a valid C identifier
	 * Expects a non-null state machine
	 * @param sm
	 * @param successorPath
	 * @return
	 */
	private boolean verifyCorrectStateMachineName( org.jts.jsidl.binding.StateMachine sm, StringBuffer successorPath )
	{
		if( !org.jts.pbValidator.ValidatorUtils.verifyValidCNameType( sm.getName() ) )
		{
			results.add( new ValidatorResult( "State machine name must be a valid identifier in the C programming language", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
			// TODO:continue validation?
		}

		return true;
	}

	/**
	 * Verifies that the state machine has at least one state or more states
	 * The amount of states can only be zero when the state machine has a default state
	 * Expects a non-null state machine
	 * @param sm
	 * @param successorPath
	 * @return
	 */
	private boolean verifySingleState( org.jts.jsidl.binding.StateMachine sm, StringBuffer successorPath )
	{
		// must have at least one state
		if(sm.getState() == null || sm.getState().size() == 0)
		{
			// when there are no states, the state machine must have a default state
			if( sm.getDefaultState() == null )
			{
				results.add( new ValidatorResult( "A state machine may only have zero states when there is a default state", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
				// TODO:continue validation?
			}
		}

		return true;
	}

	private boolean verifyUniqueSubStateNames( org.jts.jsidl.binding.StateMachine sm, StringBuffer successorPath )
	{
		if( !ValidatorUtils.subStatesHaveUniqueNames( sm ) )
		{
			results.add( new ValidatorResult( "State Machine must have substates unique within the context of their siblings", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
			// TODO:continue validation?
		}

		return true;
	}

}
