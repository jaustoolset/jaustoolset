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
class State
{
	List<ValidatorResult> results = null;
        private boolean isTesting = false;

	State()
	{
		results = new ArrayList();
	}

	List<ValidatorResult> validate( List<org.jts.jsidl.binding.ServiceDef> sdefs, Object toValidate, StringBuffer path )
	{
		if( toValidate instanceof org.jts.jsidl.binding.State )
		{
			// cast object to state
			org.jts.jsidl.binding.State state = (org.jts.jsidl.binding.State)toValidate;

			// update path
			updatePath( state, path );

			// validate state
			validateState( sdefs, null, state, path );
		}
		else if( toValidate instanceof org.jts.pbValidator.StateMap )
		{
			// cast object to state
			org.jts.pbValidator.StateMap map = (org.jts.pbValidator.StateMap)toValidate;

			// update path
			updatePath( map.getState(), path );

			// validate state
			validateState( sdefs, map, path );
		}
		// need to catch a null object so recursive state validation call doesn't overflow
		else if( toValidate == null )
		{
			return results;
		}
		else
		{
			// validate successors
			results.addAll( (new Entry()).validate( sdefs, toValidate, path ) );

			results.addAll( (new Exit()).validate( sdefs, toValidate, path ) );

			results.addAll( (new Transition()).validate( sdefs, toValidate, path ) );

			results.addAll( (new DefaultTransition()).validate( sdefs, toValidate, path ) );

			results.addAll( (new State()).validate( sdefs, toValidate, path ) );

			results.addAll( (new DefaultState()).validate( sdefs, toValidate, path ) );

			results.addAll( (new PseudoStartState()).validate( sdefs, toValidate, path ) );

			results.addAll( (new MxCell()).validate( sdefs, toValidate, path ) );
		}

		return results;
	}

	List<ValidatorResult> validate( List<org.jts.jsidl.binding.ServiceDef> sdefs, org.jts.jsidl.binding.StateMachine sm, Object toValidate, StringBuffer path )
	{
		if( toValidate instanceof org.jts.jsidl.binding.State )
		{
			// cast object to state
			org.jts.jsidl.binding.State state = (org.jts.jsidl.binding.State)toValidate;

			// update path
			updatePath( state, path );

			// validate state
			validateState( sdefs, sm, state, path );
		}
		// need to catch a null object so recursive state validation call doesn't overflow
		else if( toValidate == null )
		{
			return results;
		}
		else
		{
			// validate successors
			results.addAll( (new Entry()).validate( sdefs, toValidate, path ) );

			results.addAll( (new Exit()).validate( sdefs, toValidate, path ) );

			results.addAll( (new Transition()).validate( sdefs, toValidate, path ) );

			results.addAll( (new DefaultTransition()).validate( sdefs, toValidate, path ) );

			results.addAll( (new State()).validate( sdefs, toValidate, path ) );

			results.addAll( (new DefaultState()).validate( sdefs, toValidate, path ) );

			results.addAll( (new PseudoStartState()).validate( sdefs, toValidate, path ) );

			results.addAll( (new MxCell()).validate( sdefs, toValidate, path ) );
		}

		return results;
	}

	/**
	 * Updates the error path to include the state
	 * @param state
	 * @param path
	 */
	private void updatePath( org.jts.jsidl.binding.State state, StringBuffer path )
	{
		path.append(": "+state.getName()+"[State]");
	}
	
	/**
	 * Validates the state by verifying the state's
	 * -name
	 * -name's uniqueness
	 * -has a pseudo start when there are nested states within the state
	 * and validating the state's
	 * -entry
	 * -exit
	 * -transition
	 * -default transition
	 * -states
	 * -default state
	 * -pseudo start state
	 * -mxcell
	 * @param sdefs
	 * @param state
	 * @param successorPath
	 */
	private void validateState( List<org.jts.jsidl.binding.ServiceDef> sdefs, org.jts.pbValidator.StateMap map, StringBuffer successorPath )
	{
		//---------- name of state must be valid ----------//
		if( !verifyStateName( map.getState(), successorPath ) )
			return;

		//---------- name of state must be unique within its parents ----------//
		if( !verifyUniqueStateName( map.getState(), successorPath ) )
			return;

		if( !verifyContainsPseudoStartState( map.getState(), successorPath ) )
			return;

		if( !verifyValidInitialState( map.getState(), successorPath ) )
			return;

		//---------- per unfixed SMC bug, state names must be capitalized ---------//
		verifyStateNameIsCapital( map.getState(), successorPath );
		
		// may be more efficient checking initial states here rather than PB
		//org.jts.jsidl.binding.ServiceDef parentServiceDef = org.jts.newValidator.ValidatorUtils.getParentServiceDef( sdefs );
		//if( !verifyMatchingInheritedInitialState( map, parentServiceDef, successorPath ) )
		//	return;

		//---------- must have at most one pseudo start state ----------//
		// built into definition

		//---------- must have zero or more states ----------//
		// built into definition

		//---------- must have zero or more transitions ----------//
		// built into definition

		//---------- must have zero or more entry actions ----------//
		// built into definition

		//---------- must have zero or more exit actions ----------//
		// built into definition

		//---------- must have at most one default state ----------//
		// built into definition

		//---------- must have zero or more default transitions ----------//
		// built into definition

		// Validate entry within the state
		org.jts.jsidl.binding.Entry entry = map.getState().getEntry();
		if( entry != null )
		{
			results.addAll( (new Entry()).validate( sdefs, entry, new StringBuffer( successorPath ) ) );
		}

		// Validate exit within the state
		org.jts.jsidl.binding.Exit exit = map.getState().getExit();
		if( exit != null )
		{
			results.addAll( (new Exit()).validate( sdefs, exit, new StringBuffer( successorPath ) ) );
		}

		// Validate transitions within the state
		List<org.jts.jsidl.binding.Transition> transitions = map.getState().getTransition();
		if( transitions != null )
		{
			for( org.jts.jsidl.binding.Transition transition : transitions )
			{
				results.addAll( (new Transition()).validate( sdefs, transition, map, new StringBuffer( successorPath ) ) );
			}
		}

		// Validate default transitions within the state
		List<org.jts.jsidl.binding.DefaultTransition> dts = map.getState().getDefaultTransition();
		if( dts != null )
		{
			for( org.jts.jsidl.binding.DefaultTransition dt : dts )
			{
				results.addAll( (new DefaultTransition()).validate( sdefs, dt, map, new StringBuffer( successorPath ) ) );
			}
		}

		// Validate states within the state
		List<org.jts.pbValidator.StateMap> substates = map.getChildren();
		if( substates != null )
		{
			for( org.jts.pbValidator.StateMap substate : substates )
			{
				results.addAll( (new State()).validate( sdefs, substate, new StringBuffer( successorPath ) ) );
			}
		}

		// Validate default state within the state
		org.jts.jsidl.binding.DefaultState ds = map.getState().getDefaultState();
		if( ds != null )
		{
			results.addAll( (new DefaultState()).validate( sdefs, map.getStateMachine(), ds, new StringBuffer( successorPath ) ) );
		}

		// Validate pseudo start state within the state
		org.jts.jsidl.binding.PseudoStartState pss = map.getState().getPseudoStartState();
		if( pss != null )
		{
			results.addAll( (new PseudoStartState()).validate( sdefs, pss, new StringBuffer( successorPath ) ) );
		}

		// Validate cell within the state
		org.jts.jsidl.binding.MxCell cell = map.getState().getMxCell();
		if( cell != null )
		{
			results.addAll( (new MxCell()).validate( sdefs, cell, new StringBuffer( successorPath ) ) );
		}
	}

	/**
	 * Validates the state by verifying the state's
	 * -name
	 * -name's uniqueness
	 * -has a pseudo start when there are nested states within the state
	 * and validating the state's
	 * -entry
	 * -exit
	 * -transition
	 * -default transition
	 * -states
	 * -default state
	 * -pseudo start state
	 * -mxcell
	 * @param sdefs
	 * @param state
	 * @param successorPath
	 */
	private void validateState( List<org.jts.jsidl.binding.ServiceDef> sdefs, org.jts.jsidl.binding.StateMachine sm, org.jts.jsidl.binding.State state, StringBuffer successorPath )
	{
		//---------- name of state must be valid ----------//
		if( !verifyStateName( state, successorPath ) )
			return;

		//---------- name of state must be unique within its parents ----------//
		if( !verifyUniqueStateName( state, successorPath ) )
			return;

		if( !verifyContainsPseudoStartState( state, successorPath ) )
			return;

		if( !verifyValidInitialState( state, successorPath ) )
			return;

		//---------- per unfixed SMC bug, state names must be capitalized ---------//
		verifyStateNameIsCapital( state, successorPath );

		//---------- must have at most one pseudo start state ----------//
		// built into definition

		//---------- must have zero or more states ----------//
		// built into definition

		//---------- must have zero or more transitions ----------//
		// built into definition

		//---------- must have zero or more entry actions ----------//
		// built into definition

		//---------- must have zero or more exit actions ----------//
		// built into definition

		//---------- must have at most one default state ----------//
		// built into definition

		//---------- must have zero or more default transitions ----------//
		// built into definition

		// Validate entry within the state
		org.jts.jsidl.binding.Entry entry = state.getEntry();
		if( entry != null )
		{
			results.addAll( (new Entry()).validate( sdefs, entry, new StringBuffer( successorPath ) ) );
		}

		// Validate exit within the state
		org.jts.jsidl.binding.Exit exit = state.getExit();
		if( exit != null )
		{
			results.addAll( (new Exit()).validate( sdefs, exit, new StringBuffer( successorPath ) ) );
		}

		// Validate transitions within the state
		List<org.jts.jsidl.binding.Transition> transitions = state.getTransition();
		if( transitions != null )
		{
			for( org.jts.jsidl.binding.Transition transition : transitions )
			{
				results.addAll( (new Transition()).validate( sdefs, transition, new org.jts.pbValidator.StateMap( sm ), new StringBuffer( successorPath ) ) );
			}
		}

		// Validate default transitions within the state
		List<org.jts.jsidl.binding.DefaultTransition> dts = state.getDefaultTransition();
		if( dts != null )
		{
			for( org.jts.jsidl.binding.DefaultTransition dt : dts )
			{
				results.addAll( (new DefaultTransition()).validate( sdefs, dt, new org.jts.pbValidator.StateMap( sm ), new StringBuffer( successorPath ) ) );
			}
		}

		// Validate states within the state
		List<org.jts.jsidl.binding.State> substates = state.getState();
		if( substates != null )
		{
			for( org.jts.jsidl.binding.State substate : substates )
			{
				results.addAll( (new State()).validate( sdefs, sm, substate, new StringBuffer( successorPath ) ) );
			}
		}

		// Validate default state within the state
		org.jts.jsidl.binding.DefaultState ds = state.getDefaultState();
		if( ds != null )
		{
			results.addAll( (new DefaultState()).validate( sdefs, sm, ds, new StringBuffer( successorPath ) ) );
		}

		// Validate pseudo start state within the state
		org.jts.jsidl.binding.PseudoStartState pss = state.getPseudoStartState();
		if( pss != null )
		{
			results.addAll( (new PseudoStartState()).validate( sdefs, pss, new StringBuffer( successorPath ) ) );
		}

		// Validate cell within the state
		org.jts.jsidl.binding.MxCell cell = state.getMxCell();
		if( cell != null )
		{
			results.addAll( (new MxCell()).validate( sdefs, cell, new StringBuffer( successorPath ) ) );
		}
	}

	/**
	 * Verifies that the state name is a valid C identifier
	 * Expects a non-null state
	 * @param state
	 * @param successorPath
	 * @return
	 */
	private boolean verifyStateName( org.jts.jsidl.binding.State state, StringBuffer successorPath )
	{
		if( !org.jts.pbValidator.ValidatorUtils.verifyValidCNameValue( state.getName() ) )
		{
			results.add( new ValidatorResult( "State name must be a valid identifier in the C programming language", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
			// TODO:continue validation?
		}

		return true;
	}

	/**
	 * Verifies that the state name is unique within the context of it inheritance chain
	 * Expects a non-null state
	 * @param state
	 * @param successorPath
	 * @return
	 */
	private boolean verifyUniqueStateName( org.jts.jsidl.binding.State state, StringBuffer successorPath )
	{
		String stateName = state.getName();

		if(stateName != null)
		{
			// all we have to do is check that each sub state if unique within the context of its siblings
			if( !org.jts.pbValidator.ValidatorUtils.subStatesHaveUniqueNames( state ) )
			{
				results.add( new ValidatorResult( "State must have substates unique within the context of their siblings", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
				// TODO:continue validation?
			}
		}

		return true;
	}

	/**
	 * Verifies that when a state has nested states, it must contain a pseudo start state
	 * Expects a non-null state
	 * @param state
	 * @param successorPath
	 * @return
	 */
	private boolean verifyContainsPseudoStartState( org.jts.jsidl.binding.State state, StringBuffer successorPath )
	{
		if( state.getState() != null && state.getState().size() != 0 && state.getPseudoStartState() == null )
		{
			// removed because we need to validate non-mxgraph also
			//results.add( new ValidatorResult( "A state that contains nested states must have a pseudo start state", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
			// TODO:continue validation?
		}

		return true;
	}

	/**
	 * Verifies that the initial state is there when the state has substates
	 * and checks to see if the initial state is a valid substate
	 * Expects a non-null state
	 * NOTE: accepts the assumption that when a single state only has one substate, an initial state is implied and not necessary
	 * @param state
	 * @param successorPath
	 * @return
	 */
	private boolean verifyValidInitialState( org.jts.jsidl.binding.State state, StringBuffer successorPath )
	{
		if( state.getState() != null && state.getState().size() >= 2 )
		{
			if( state.getInitialState() == null )
			{
				results.add( new ValidatorResult( "A state that contains more than one nested state must have an initial state", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
				// TODO:continue validation?
			}
			else
			{
				int count = 0;
				for( org.jts.jsidl.binding.State substate : state.getState() )
				{
					if( substate.getName().compareTo( state.getInitialState() ) == 0 )
					{
						count++;
					}
				}

				if( count != 1 )
				{
					results.add( new ValidatorResult( "A state's initial state must point to one sub state within the state", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
					// TODO:continue validation?
				}
			}
		}

		return true;
	}

	/**
	 * SMC has an unfixed bug which requires state names be be capital.  This method
	 * checks for a capital start and changes it to upper case when the state name starts
	 * with lower case.  A warning is then sent to the user notifying of the change
	 * @param state
	 * @param successorPath
	 */
	private void verifyStateNameIsCapital( org.jts.jsidl.binding.State state, StringBuffer successorPath )
	{
		try
		{
			String stateName = state.getName();

			String upperCase = stateName.substring( 0, 1 ).toUpperCase().concat( stateName.substring( 1 ) );

			if( stateName.compareTo( upperCase ) != 0 )
			{
				// change the name of the state
				state.setName( upperCase );

				results.add( new ValidatorResult( "State names must be capital, name modified to " + upperCase, successorPath.toString(), ValidatorResult.TYPE_WARNING ) );
			}
		}
		catch( Exception e )
		{
			System.out.println("State validation: verifyStateNameIsCapital: " + e.toString() );
		}
	}
	
	private boolean verifyMatchingInheritedInitialState( org.jts.pbValidator.StateMap map, org.jts.jsidl.binding.ServiceDef parentServiceDef, StringBuffer successorPath )
	{
		// don't have to inherit from another service
		if( parentServiceDef == null )
		{
			return true;
		}

		for( org.jts.jsidl.binding.StateMachine sm : parentServiceDef.getProtocolBehavior().getStateMachine() )
		{
			org.jts.pbValidator.StateMap parentMap = new org.jts.pbValidator.StateMap( sm );
			
			org.jts.pbValidator.StateMap matchingParent = map.getMatchingStateInMap( parentMap );
			
			if( matchingParent != null )
			{
				String parentInitial = matchingParent.getState().getInitialState();
				String currentInitial = map.getState().getInitialState();
				
				if( parentInitial == null || currentInitial == null)
				{
					if( parentInitial == null && currentInitial == null )
					{
						return true;
					}
					
					return false;
				}
				
				if( parentInitial.compareTo( currentInitial ) == 0 )
				{
					return true;
				}
			}
		}
		
		results.add( new ValidatorResult( "A state's initial state must point to same state as inherited state", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
		// TODO:continue validation?
		
		return false;
	}

}
