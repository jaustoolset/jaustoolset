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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author Arfath Pasha
 *
 */
public class ValidatorUtils
{

	public static void printResultErrors( List<ValidatorResult> results )
	{
		if( results != null )
		{
			for( ValidatorResult res : results )
			{
				if( res.getType().compareTo( ValidatorResult.TYPE_ERROR ) == 0 )
				{
					System.out.println( res.getDescription() );
				}
			}
		}
	}

	/**
	 * Returns the amount of errors in the results list
	 * @param results
	 * @return
	 */
	public static int getErrorCount( List<ValidatorResult> results )
	{
		if( results == null )
			return 0;

		int errors = 0;
		for( ValidatorResult result : results )
		{
			if( result.getType().compareTo( ValidatorResult.TYPE_ERROR ) == 0 )
			{
				errors++;
			}
		}

		return errors;
	}

	/**
	 * Returns the amount of warnings in the results list
	 * @param results
	 * @return
	 */
	public static int getWarningCount( List<ValidatorResult> results )
	{
		if( results == null )
			return 0;

		int warnings = 0;
		for( ValidatorResult result : results )
		{
			if( result.getType().compareTo( ValidatorResult.TYPE_WARNING ) == 0 )
			{
				warnings++;
			}
		}

		return warnings;
	}

	/**
	 * Verifies that the name is a valid C identifier
	 * Accepts a null name
	 * @param name
	 * @return
	 */
	public static boolean verifyValidCNameValue( String name )
	{
		// Make sure the string exists
		if( name == null || name.length() == 0 )
		{
			return false;
		}
		// string must be a valid c identifier
		else if( !Pattern.matches("[a-zA-Z_]+[a-zA-Z_0-9]*" , name ))
		{
			return false;
		}

		return true;
	}

	/**
	 * Verifies that the name is a valid C identifier
	 * Accepts a null name
	 * @param name
	 * @return
	 */
	public static boolean verifyValidCNameType( String name )
	{
		// Make sure the string exists
		if( name == null || name.length() == 0 )
		{
			return false;
		}
		// string must be a valid c identifier
		else
		{
			// check for 'unsigned byte' type
			if( name.compareTo( "unsigned byte" ) == 0 )
			{
				return true;
			}
			// check for 'unsigned short' type
			else if( name.compareTo( "unsigned short" ) == 0 )
			{
				return true;
			}
			// check for 'unsigned int' type
			else if( name.compareTo( "unsigned int" ) == 0 )
			{
				return true;
			}
			// check for 'unsigned long' type
			else if( name.compareTo( "unsigned long" ) == 0 )
			{
				return true;
			}

			if( !verifyValidCNameWithNamespace( name ) )
			{
				return false;
			}
		}

		return true;
	}
	
	/**
	 * Verifies that the name is a valid C identifier
	 * Accepts a null name and possible namespacing
	 * @param name
	 * @return
	 */
	public static boolean verifyValidCNameWithNamespace( String name )
	{
		// Make sure the string exists
		if( name == null || name.length() == 0 )
		{
			return false;
		}
		
		// check for namespacing
		// split doesn't take endings into account
		if( name.endsWith( "." ) )
		{
			return false;
		}

		String[] names = name.split("\\.");

		for( String nm : names )
		{
			// Make sure the string exists
			if( !org.jts.pbValidator.ValidatorUtils.verifyValidCNameValue( nm ) )
			{
				return false;
			}
		}
		
		return true;
	}

	/**
	 * Checks the transition's parameters to see if the argument is defined within the parameters
	 * Accepts null parameters
	 * Accepts null argumentName
	 * @param transition
	 * @param argumentName
	 * @return
	 */
	public static boolean isArgumentInParameters( org.jts.jsidl.binding.Transition transition, String argumentName )
	{
		// when there is no transition, action arguments must be only constants
		// expecting format (whitespace)Type(whitespace)Value(whitespace)
		// return error when there are no parameters for the transition
		if( transition == null || transition.getParameter() == null )
		{
			return false;
		}
		else
		{
			// look through each of the parameters for a match to the argument name
			for( org.jts.jsidl.binding.Parameter parameter : transition.getParameter() )
			{
				if( parameter.getValue().compareTo(argumentName) == 0 )
				{
					return true;
				}
			}

			// no parameter was matched to the argument
			return false;
		}
	}

	/**
	 * Verifies that all arguments for the action were defined in the transition's parameters
	 * Accepts null parameters
	 * Accepts a null argument list
	 * @param transition
	 * @param arguments
	 * @return
	 */
	public static boolean verifyActionArguments( org.jts.jsidl.binding.Transition transition, List<org.jts.jsidl.binding.Argument> arguments )
	{
		// return no error when there are no arguments to verify
		if( arguments != null )
		{
			// try to cast each argument as either number, constant or transition parameter
			for( org.jts.jsidl.binding.Argument argument : arguments )
			{
				// skip empty arguments
				if( argument == null )
					continue;

				String argumentValue = argument.getValue();

				// skip empty argument values
				if( argumentValue == null)
					continue;

				// check for valid string definition
				// must begin with a ['] and end with a [']
				if( org.jts.validator.Argument.isValueValidString( argumentValue ) )
					continue;

				// remove whitespaces
				String noWhitespaceArgumentValue = argumentValue.replaceAll("\\s", "");

				// make sure we have a valid variable name
				if( !verifyValidCNameValue( noWhitespaceArgumentValue ) )
					return false;

				// at this point, the argument must be in the transition parameter list
				if( !isArgumentInParameters( transition, noWhitespaceArgumentValue ) )
					return false;
			}
		}

		return true;
	}

	/**
	 * Verifies that a transition has one and only one type specified
	 * Expects obj to be non-null
	 * @param transition
	 * @param results
	 * @param successorPath
	 * @return
	 */
	public static boolean verifyTransitionHasType( Object obj, List<ValidatorResult> results, StringBuffer successorPath )
	{
		int count = 0;
		String types = "";

		if( obj instanceof org.jts.jsidl.binding.Transition )
		{
			org.jts.jsidl.binding.Transition transition = (org.jts.jsidl.binding.Transition) obj;

			if(transition.getSimple() != null)
			{
				types += " Simple";
				count++;
			}
			if(transition.getPush() != null)
			{
				types += " Push";
				count++;
			}
			if(transition.getPop() != null)
			{
				types += " Pop";
				count++;
			}
			if(transition.getInternal() != null)
			{
				types += " Internal";
				count++;
			}
		}
		else if( obj instanceof org.jts.jsidl.binding.DefaultTransition )
		{
			org.jts.jsidl.binding.DefaultTransition transition = (org.jts.jsidl.binding.DefaultTransition) obj;

			if(transition.getSimple() != null)
			{
				types += " Simple";
				count++;
			}
			if(transition.getPush() != null)
			{
				types += " Push";
				count++;
			}
			if(transition.getPop() != null)
			{
				types += " Pop";
				count++;
			}
			if(transition.getInternal() != null)
			{
				types += " Internal";
				count++;
			}
		}

		if(count != 1)
		{
			results.add( new ValidatorResult( "Transition must only be of one type, found multiple types: " + types, successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
			return false;
		}

		return true;
	}

	/**
	 * Verifies that a simple transition has an end state and that is a valid state
	 * Accepts a null simple
	 * @param simple
	 * @param results
	 * @param successorPath
	 * @return
	 */
	public static boolean verifySimpleTransition( org.jts.jsidl.binding.Simple simple, org.jts.pbValidator.StateMap map, List<ValidatorResult> results, StringBuffer successorPath )
	{
		if( simple != null )
		{
			if( !verifyEndState( simple.getEndState(), map, results, successorPath ) )
			{
				// TODO: continue validation?
			}
		}

		return true;
	}

	/**
	 * Verifies that a push transition has an end state that is a valid state
	 * Also verifies the optional simple transition if defined
	 * Accepts a null push
	 * @param transition
	 * @param results
	 * @param successorPath
	 * @return
	 */
	public static boolean verifyPushTransition( org.jts.jsidl.binding.Push push, org.jts.pbValidator.StateMap map, List<ValidatorResult> results, StringBuffer successorPath )
	{
		if( push != null )
		{
			// end state is required
			org.jts.jsidl.binding.EndState endState = push.getEndState();
			if( endState == null )
			{
				results.add( new ValidatorResult( "Push transition must have an end state", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
				// TODO:continue validation?
			}
			else
			{
				String endStateName = endState.getState();

				if( endStateName == null )
				{
					results.add( new ValidatorResult( "Push transition end state must have an end state name", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
					// TODO:continue validation?
				}
			}

			if( !verifyEndState( push.getEndState(), map, results, successorPath ) )
			{
				// TODO: continue validation?
			}

			// simple transition is optional
			if( !verifySimpleTransition( push.getSimple(), map, results, successorPath ) )
			{
				// TODO:continue validation?
			}
			
			if( map == null )
			{
				//results.add( new ValidatorResult( "Cannot verify without complete definition", successorPath.toString(), ValidatorResult.TYPE_WARNING ) );
				return false;
			}

			// each push to state must have a pop
			if( push.getEndState() != null )
			{
				String end = push.getEndState().getState();

				// when there is a simple tr attached to the push trigger,
				// the behavior is to move to that state and then push to the 
				// specified state, no need to alter the end state pop check
				/*
				if( push.getSimple() != null )
				{
					// loopback
					if( push.getSimple().getEndState() == null )
					{
						end = map.getState().getName();
					}
					else
					{
						end = push.getSimple().getEndState().getState();
					}
				}
				*/

				// TODO: must also find default state if applicable and check for pops
				// verify end state or initial substates have pops
				org.jts.jsidl.binding.State st = getStateInStateMachine( map.getStateMachine(), end );

				if( !stateOrInitialSubStatesHasPopTransition( st ) )
				{
					results.add( new ValidatorResult( "Target end state from push must have corresponding pop:"+st.getName(), successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
					// TODO:continue validation?
				}
			}
		}

		return true;
	}

	public static boolean stateOrInitialSubStatesHasPopTransition( org.jts.jsidl.binding.State state )
	{
		for( org.jts.jsidl.binding.Transition tr : state.getTransition() )
		{
			if( tr.getPop() != null )
			{
				return true;
			}
		}

		for( org.jts.jsidl.binding.DefaultTransition dtr : state.getDefaultTransition() )
		{
			if( dtr.getPop() != null )
			{
				return true;
			}
		}

		if( state.getInitialState() != null )
		{
			for( org.jts.jsidl.binding.State st : state.getState() )
			{
				if( state.getInitialState().compareTo( st.getName() ) == 0 )
				{
					if( stateOrInitialSubStatesHasPopTransition( st ) )
					{
						return true;
					}
					break;
				}
			}
		}

		return false;
	}

	/**
	 * Verifies that the pop transition
	 * Accepts null parameters
	 * Accepts a null pop
	 * @param parameters
	 * @param pop
	 * @param results
	 * @param successorPath
	 * @return
	 */
	public static boolean verifyPopTransition( org.jts.jsidl.binding.Transition transition, org.jts.jsidl.binding.Pop pop, List<ValidatorResult> results, StringBuffer successorPath )
	{
		if( pop != null )
		{
			// transition is optional for pop
			String transitionName = pop.getTransition();
			if( transitionName != null )
			{
				if( !verifyValidCNameValue( transitionName ) )
				{
					results.add( new ValidatorResult( "Pop transition Transition name must be a valid identifier in the C programming language", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
					// TODO:continue validation?
				}

				if( !verifyActionArguments( transition, pop.getArgument() ) )
				{
					results.add( new ValidatorResult( "Pop transition Transition arguments must be defined in calling transition", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
					// TODO:continue validation?
				}
			}
		}

		return true;
	}

	/**
	 * Verify that the internal transition is properly defined
	 * Accepts a null internal
	 * @param internal
	 * @param results
	 * @param successorPath
	 * @return
	 */
	public static boolean verifyInternalTransition( org.jts.jsidl.binding.Internal internal, List<ValidatorResult> results, StringBuffer successorPath )
	{
		if( internal != null )
		{
			// internal transition does not have any properties to verify
		}

		return true;
	}

	/**
	 * Check to see if two transitions are the same (name:guards:parameters are all matching)
	 * Expects non-null transitions
	 * @param tr1
	 * @param tr2
	 * @return
	 */
	public static boolean isTransitionSame( org.jts.jsidl.binding.Transition tr1, org.jts.jsidl.binding.Transition tr2 )
	{
		if( tr1.getName().compareTo( tr2.getName() ) == 0 )
		{
			if( isParametersSame( tr1.getParameter(), tr2.getParameter() ) )
			{
				if( isGuardsSame( tr1.getGuard(), tr2.getGuard() ) )
				{
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Check to see if two parameter lists have the same parameters
	 * the parameters do not have to be in the same order to have the same list of parameters
	 * Accepts null lists
	 * @param pr1
	 * @param pr2
	 * @return
	 */
	public static boolean isParametersSame( List<org.jts.jsidl.binding.Parameter> pr1, List<org.jts.jsidl.binding.Parameter> pr2)
	{
		if( ( pr1 == null || pr2 == null ) )
		{
			if( pr1 == null && pr2 == null )
				return true;
			else
				return false;
		}

		if( pr1.size() != pr2.size())
		{
			return false;
		}

		int matches = 0;
		for( org.jts.jsidl.binding.Parameter parameter : pr1 )
		{
			for( org.jts.jsidl.binding.Parameter parameterCompare : pr2 )
			{
				String type1 = parameter.getType();
				String type2 = parameterCompare.getType();

				if( ( type1 == null || type2 == null ) )
				{
					if( type1 == null && type2 == null )
						return true;
					else
						return false;
				}

				String value1 = parameter.getValue();
				String value2 = parameterCompare.getValue();

				if( ( value1 == null || value2 == null ) )
				{
					if( value1 == null && value2 == null )
						return true;
					else
						return false;
				}

				if( type1.compareTo( type2 ) == 0 && value1.compareTo( value2 ) == 0 )
				{
					matches++;
				}
			}
		}

		if( matches == pr1.size() )
		{
			return true;
		}

		return false;
	}

	/**
	 * Checks whether two guards are the same
	 * Accepts null guards
	 * @param guard1
	 * @param guard2
	 * @return
	 */
	public static boolean isGuardsSame( org.jts.jsidl.binding.Guard g1, org.jts.jsidl.binding.Guard g2 )
	{
		if( ( g1 == null || g2 == null ) )
		{
			if( g1 == null && g2 == null )
				return true;
			else
				return false;
		}

		String guard1 = g1.getCondition();
		String guard2 = g2.getCondition();

		if( ( guard1 == null || guard2 == null ) )
		{
			if( guard1 == null && guard2 == null )
				return true;
			else
				return false;
		}

		String noWhitespaceGuard1 = guard1.replaceAll("\\s", "");
		String noWhitespaceGuard2 = guard2.replaceAll("\\s", "");

		if(noWhitespaceGuard1.compareTo( noWhitespaceGuard2 ) == 0 )
		{
			return true;
		}

		return false;
	}

	/**
	 * Verify that an endstate has a name and it is a valid name
	 * @param endState
	 * @param results
	 * @param successorPath
	 * @return
	 */
	public static boolean verifyEndState( org.jts.jsidl.binding.EndState endState, org.jts.pbValidator.StateMap map, List<ValidatorResult> results, StringBuffer successorPath )
	{
		if( endState == null )
		{
			// deprecated but not an error
			//results.add( new ValidatorResult( "Must have an end state", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
			// TODO:continue validation?
			return true;
		}

		String endStateName = endState.getState();

		if( endStateName == null )
		{
			// deprecated but not an error
			//results.add( new ValidatorResult( "End state must have an end state name", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
			// TODO:continue validation?
			return true;
		}

		// check to see that end state name is valid
		if( map == null || map.getStateMachine() == null )
		{
			results.add( new ValidatorResult( "Simple transition end state cannot be validated without the containing state machine", successorPath.toString(), ValidatorResult.TYPE_WARNING ) );
			// TODO:continue validation?
		}
		else
		{
			if( !isStateInStateMachine( map.getStateMachine(), endStateName ) )
			{
				results.add( new ValidatorResult( "End state name must be a state within the same state machine", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
				// TODO:continue validation?
			}
		}
		
		return true;
	}

	/**
	* Checks to see if state is a valid state name within the stateMachine
	* Expects a non-null stateMachine
	* Expects a non-null state
	* @param stateMachine
	* @param state
	* @return
	*/
	public static boolean isStateInStateMachine( org.jts.jsidl.binding.StateMachine stateMachine, String state )
	{
		for( org.jts.jsidl.binding.State st : stateMachine.getState() )
		{
			if( checkSubStatesForMatchingStateName(st, null, state ) )
			{
				return true;
			}
		}

		return false;
	}

	public static org.jts.jsidl.binding.State getStateInStateMachine( org.jts.jsidl.binding.StateMachine stateMachine, String stateName)
	{
		if( stateMachine == null || stateName == null )
		{
			return null;
		}

		for( org.jts.jsidl.binding.State st : stateMachine.getState() )
		{
			org.jts.jsidl.binding.State foundState = getStateInState( st, null, stateName );
			if( foundState != null )
			{
				return foundState;
			}
		}

		return null;
	}

	public static org.jts.jsidl.binding.State getStateInState( org.jts.jsidl.binding.State st, String previousStates, String toCheck )
	{
		if( toCheck == null )
		{
			return null;
		}

		String currentState = null;

		if( previousStates == null )
		{
			currentState = st.getName();
		}
		else
		{
			currentState = previousStates + "." + st.getName();
		}

		if( toCheck.compareTo(currentState) == 0 )
		{
			return st;
		}
		else
		{
			for( org.jts.jsidl.binding.State subState : st.getState() )
			{
				org.jts.jsidl.binding.State foundState = getStateInState( subState, currentState, toCheck );
				if( foundState != null )
				{
					return foundState;
				}
			}
		}

		return null;
	}

	/**
	* Recursive check to find if a stateName(tocheck) is valid within the nested states
	* Accepts a null previous state (at top of states)
	* Expects a non-null st
	* Expects a non-null toCheck
	* @param st
	* @param previousStates
	* @param toCheck
	* @return
	*/
	public static boolean checkSubStatesForMatchingStateName( org.jts.jsidl.binding.State st, String previousStates, String toCheck )
	{
		if( toCheck == null )
		{
			return false;
		}

		String currentState = null;

		if( previousStates == null )
		{
			currentState = st.getName();
		}
		else
		{
			currentState = previousStates + "." + st.getName();
		}

		if( toCheck.compareTo(currentState) == 0 )
		{
			return true;
		}
		else
		{
			for( org.jts.jsidl.binding.State subState : st.getState() )
			{
				if( checkSubStatesForMatchingStateName( subState, currentState, toCheck ) )
				{
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Recursive check to see if a state has substates with unique names within the context of their siblings
	 * Expects a non-null state
	 * @param state
	 * @return
	 */
	public static boolean subStatesHaveUniqueNames( org.jts.jsidl.binding.State state )
	{
		List<org.jts.jsidl.binding.State> subStateList = state.getState();

		if( subStateList != null )
		{
			for( int i = 0; i < subStateList.size(); i++ )
			{
				org.jts.jsidl.binding.State currentState = subStateList.get( i );
				String currentStateName = currentState.getName();

				for( int j = i + 1; j < subStateList.size(); j++ )
				{
					String compareStateName = subStateList.get( j ).getName();

					if( currentStateName.compareTo( compareStateName ) == 0 )
					{
						return false;
					}
				}

				if( !subStatesHaveUniqueNames( currentState ) )
				{
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Recursive check to see if a state has substates with unique names within the context of their siblings
	 * Expects a non-null state
	 * @param state
	 * @return
	 */
	public static boolean subStatesHaveUniqueNames( org.jts.jsidl.binding.StateMachine stateMachine )
	{
		List<org.jts.jsidl.binding.State> subStateList = stateMachine.getState();

		if( subStateList != null )
		{
			for( int i = 0; i < subStateList.size(); i++ )
			{
				org.jts.jsidl.binding.State currentState = subStateList.get( i );
				String currentStateName = currentState.getName();

				for( int j = i + 1; j < subStateList.size(); j++ )
				{
					String compareStateName = subStateList.get( j ).getName();

					if( currentStateName.compareTo( compareStateName ) == 0 )
					{
						return false;
					}
				}

				if( !subStatesHaveUniqueNames( currentState ) )
				{
					return false;
				}
			}
		}

		return true;
	}

	public static boolean isNameMatchingMessageOrEventPart( List<org.jts.jsidl.binding.ServiceDef> sdefs, String messageParts )
	{
		String[] parts = messageParts.split( "\\." );
		int partIndex = 0;

		for( org.jts.jsidl.binding.ServiceDef sdef : sdefs )
		{
			for( Object obj  : sdef.getMessageSet().getInputSet().getMessageDefOrDeclaredMessageDef() )
			{
				if( obj instanceof org.jts.jsidl.binding.MessageDef )
				{
					org.jts.jsidl.binding.MessageDef messageDef = (org.jts.jsidl.binding.MessageDef) obj;
	
					if( isNameMatchingMessageDef( messageDef, partIndex, parts ) )
					{
						return true;
					}
				}
			}

			for( Object obj  : sdef.getMessageSet().getOutputSet().getMessageDefOrDeclaredMessageDef() )
			{
				if( obj instanceof org.jts.jsidl.binding.MessageDef )
				{
					org.jts.jsidl.binding.MessageDef messageDef = (org.jts.jsidl.binding.MessageDef) obj;
	
					if( isNameMatchingMessageDef( messageDef, partIndex, parts ) )
					{
						return true;
					}
				}
			}

			for( Object obj  : sdef.getInternalEventsSet().getEventDefOrDeclaredEventDef() )
			{
				if( obj instanceof org.jts.jsidl.binding.EventDef )
				{
					org.jts.jsidl.binding.EventDef eventDef = (org.jts.jsidl.binding.EventDef) obj;

					if( isNameMatchingEventDef( eventDef, partIndex, parts ) )
					{
						return true;
					}
				}
			}
		}

		return false;
	}

	public static boolean isNameMatchingMessageDef( org.jts.jsidl.binding.MessageDef messageDef, int partIndex, String[] parts )
	{
		if( messageDef == null )
		{
			return false;
		}

		if( messageDef.getName().compareTo( parts[ partIndex ] ) == 0 )
		{
			if( partIndex == parts.length - 1 )
			{
				return true;
			}

			partIndex++;

			org.jts.jsidl.binding.Header header = messageDef.getHeader();
			org.jts.jsidl.binding.Body body = messageDef.getBody();
			org.jts.jsidl.binding.Footer footer = messageDef.getFooter();

			if( isNameMatchingHeader( header, partIndex, parts ) ||
					isNameMatchingBody( body, partIndex, parts ) ||
					isNameMatchingFooter( footer, partIndex, parts ) )
			{
				return true;
			}
		}

		return false;
	}

	public static boolean isNameMatchingEventDef( org.jts.jsidl.binding.EventDef eventDef, int partIndex, String[] parts )
	{
		if( eventDef == null )
		{
			return false;
		}

		if( eventDef.getName().compareTo( parts[ partIndex ] ) == 0 )
		{
			if( partIndex == parts.length - 1 )
			{
				return true;
			}

			partIndex++;

			org.jts.jsidl.binding.Header header = eventDef.getHeader();
			org.jts.jsidl.binding.Body body = eventDef.getBody();
			org.jts.jsidl.binding.Footer footer = eventDef.getFooter();

			if( isNameMatchingHeader( header, partIndex, parts ) ||
					isNameMatchingBody( body, partIndex, parts ) ||
					isNameMatchingFooter( footer, partIndex, parts ) )
			{
				return true;
			}
		}

		return false;
	}

	public static boolean isNameMatchingHeader( org.jts.jsidl.binding.Header header, int partIndex, String[] parts )
	{
		if( header == null )
		{
			return false;
		}

		if( header.getName().compareTo( parts[ partIndex ] ) == 0 )
		{
			if( partIndex == parts.length - 1 )
			{
				return true;
			}

			partIndex++;

			org.jts.jsidl.binding.List list = header.getList();
			org.jts.jsidl.binding.Record record = header.getRecord();
			org.jts.jsidl.binding.Sequence sequence = header.getSequence();
			org.jts.jsidl.binding.Variant variant = header.getVariant();

			if( isNameMatchingList( list, partIndex, parts ) ||
					isNameMatchingRecord( record, partIndex, parts ) ||
					isNameMatchingSequence( sequence, partIndex, parts ) ||
					isNameMatchingVariant( variant, partIndex, parts ) )
			{
				return true;
			}
		}

		return false;
	}

	public static boolean isNameMatchingBody( org.jts.jsidl.binding.Body body, int partIndex, String[] parts )
	{
		if( body == null )
		{
			return false;
		}

		if( body.getName().compareTo( parts[ partIndex ] ) == 0 )
		{
			if( partIndex == parts.length - 1 )
			{
				return true;
			}

			partIndex++;

			org.jts.jsidl.binding.List list = body.getList();
			org.jts.jsidl.binding.Record record = body.getRecord();
			org.jts.jsidl.binding.Sequence sequence = body.getSequence();
			org.jts.jsidl.binding.Variant variant = body.getVariant();

			if( isNameMatchingList( list, partIndex, parts ) ||
					isNameMatchingRecord( record, partIndex, parts ) ||
					isNameMatchingSequence( sequence, partIndex, parts ) ||
					isNameMatchingVariant( variant, partIndex, parts ) )
			{
				return true;
			}
		}

		return false;
	}

	public static boolean isNameMatchingFooter( org.jts.jsidl.binding.Footer footer, int partIndex, String[] parts )
	{
		if( footer == null )
		{
			return false;
		}

		if( footer.getName().compareTo( parts[ partIndex ] ) == 0 )
		{
			if( partIndex == parts.length - 1 )
			{
				return true;
			}

			partIndex++;

			org.jts.jsidl.binding.List list = footer.getList();
			org.jts.jsidl.binding.Record record = footer.getRecord();
			org.jts.jsidl.binding.Sequence sequence = footer.getSequence();
			org.jts.jsidl.binding.Variant variant = footer.getVariant();

			if( isNameMatchingList( list, partIndex, parts ) ||
					isNameMatchingRecord( record, partIndex, parts ) ||
					isNameMatchingSequence( sequence, partIndex, parts ) ||
					isNameMatchingVariant( variant, partIndex, parts ) )
			{
				return true;
			}
		}

		return false;
	}

	public static boolean isNameMatchingList( org.jts.jsidl.binding.List list, int partIndex, String[] parts )
	{
		if( list == null )
		{
			return false;
		}

		if( list.getName().compareTo( parts[ partIndex ] ) == 0 )
		{
			if( partIndex == parts.length - 1 )
			{
				return true;
			}

			partIndex++;

			org.jts.jsidl.binding.CountField countField = list.getCountField();
			org.jts.jsidl.binding.List subList = list.getList();
			org.jts.jsidl.binding.Record record = list.getRecord();
			org.jts.jsidl.binding.Sequence sequence = list.getSequence();
			org.jts.jsidl.binding.Variant variant = list.getVariant();

			if( ifNameMatchingCountField( countField, partIndex, parts ) ||
					isNameMatchingList( subList, partIndex, parts ) ||
					isNameMatchingRecord( record, partIndex, parts ) ||
					isNameMatchingSequence( sequence, partIndex, parts ) ||
					isNameMatchingVariant( variant, partIndex, parts ) )
			{
				return true;
			}
		}

		return false;
	}

	public static boolean isNameMatchingRecord( org.jts.jsidl.binding.Record record, int partIndex, String[] parts )
	{
		if( record == null )
		{
			return false;
		}

		if( record.getName().compareTo( parts[ partIndex ] ) == 0 )
		{
			if( partIndex == parts.length - 1 )
			{
				return true;
			}

			partIndex++;

			org.jts.jsidl.binding.PresenceVector presenceVector = record.getPresenceVector();

			if( isNameMatchingPresenceVector( presenceVector, partIndex, parts ) )
			{
				return true;
			}

			for( Object obj : record.getArrayOrFixedFieldOrVariableField() )
			{
				if( obj instanceof org.jts.jsidl.binding.Array )
				{
					org.jts.jsidl.binding.Array array = ( org.jts.jsidl.binding.Array ) obj;

					if( isNameMatchingArray( array, partIndex, parts ) )
					{
						return true;
					}
				}
				else if( obj instanceof org.jts.jsidl.binding.FixedField )
				{
					org.jts.jsidl.binding.FixedField fixedField = ( org.jts.jsidl.binding.FixedField ) obj;

					if( isNameMatchingFixedField( fixedField, partIndex, parts ) )
					{
						return true;
					}
				}
				else if( obj instanceof org.jts.jsidl.binding.VariableField )
				{
					org.jts.jsidl.binding.VariableField variableField = ( org.jts.jsidl.binding.VariableField ) obj;

					if( isNameMatchingVariableField( variableField, partIndex, parts ) )
					{
						return true;
					}
				}
			}
		}

		return false;
	}

	public static boolean isNameMatchingSequence( org.jts.jsidl.binding.Sequence sequence, int partIndex, String[] parts )
	{
		if( sequence == null )
		{
			return false;
		}

		if( sequence.getName().compareTo( parts[ partIndex ] ) == 0 )
		{
			if( partIndex == parts.length - 1 )
			{
				return true;
			}

			partIndex++;

			org.jts.jsidl.binding.PresenceVector presenceVector = sequence.getPresenceVector();

			if( isNameMatchingPresenceVector( presenceVector, partIndex, parts ) )
			{
				return true;
			}

			for( Object obj : sequence.getRecordOrDeclaredRecordOrList() )
			{
				if( obj instanceof org.jts.jsidl.binding.Record )
				{
					org.jts.jsidl.binding.Record record = ( org.jts.jsidl.binding.Record ) obj;

					if( isNameMatchingRecord( record, partIndex, parts ) )
					{
						return true;
					}
				}
				else if( obj instanceof org.jts.jsidl.binding.List )
				{
					org.jts.jsidl.binding.List list = ( org.jts.jsidl.binding.List ) obj;

					if( isNameMatchingList( list, partIndex, parts ) )
					{
						return true;
					}
				}
			}
		}

		return false;
	}

	public static boolean isNameMatchingVariant( org.jts.jsidl.binding.Variant variant, int partIndex, String[] parts )
	{
		if( variant == null )
		{
			return false;
		}

		if( variant.getName().compareTo( parts[ partIndex ] ) == 0 )
		{
			if( partIndex == parts.length - 1 )
			{
				return true;
			}

			partIndex++;

			org.jts.jsidl.binding.VtagField vtagField = variant.getVtagField();

			if( isNameMatchingVtagField( vtagField, partIndex, parts ) )
			{
				return true;
			}

			for( Object obj : variant.getRecordOrDeclaredRecordOrList() )
			{
				if( obj instanceof org.jts.jsidl.binding.Record )
				{
					org.jts.jsidl.binding.Record record = ( org.jts.jsidl.binding.Record ) obj;

					if( isNameMatchingRecord( record, partIndex, parts ) )
					{
						return true;
					}
				}
				else if( obj instanceof org.jts.jsidl.binding.List )
				{
					org.jts.jsidl.binding.List list = ( org.jts.jsidl.binding.List ) obj;

					if( isNameMatchingList( list, partIndex, parts ) )
					{
						return true;
					}
				}
			}
		}

		return false;
	}

	public static boolean ifNameMatchingCountField( org.jts.jsidl.binding.CountField countField, int partIndex, String[] parts )
	{
		if( countField == null )
		{
			return false;
		}

		if( partIndex == parts.length - 1 )
		{
			return true;
		}

		return false;
	}

	public static boolean isNameMatchingPresenceVector( org.jts.jsidl.binding.PresenceVector presenceVector, int partIndex, String[] parts )
	{
		if( presenceVector == null )
		{
			return false;
		}

		if( partIndex == parts.length - 1 )
		{
			return true;
		}

		return false;
	}

	public static boolean isNameMatchingArray( org.jts.jsidl.binding.Array array, int partIndex, String[] parts )
	{
		if( array == null )
		{
			return false;
		}

		if( array.getName().compareTo( parts[ partIndex ] ) == 0 )
		{
			if( partIndex == parts.length - 1 )
			{
				return true;
			}

			partIndex++;

			org.jts.jsidl.binding.BitField bitField = array.getBitField();
			org.jts.jsidl.binding.FixedField fixedField = array.getFixedField();
			org.jts.jsidl.binding.FixedLengthString fixedLengthString = array.getFixedLengthString();
			org.jts.jsidl.binding.VariableField variableField = array.getVariableField();
			org.jts.jsidl.binding.VariableFormatField variableFormatField = array.getVariableFormatField();
			org.jts.jsidl.binding.VariableLengthField variableLengthField = array.getVariableLengthField();
			org.jts.jsidl.binding.VariableLengthString variableLengthString = array.getVariableLengthString();

			if( isNameMatchingBitField( bitField, partIndex, parts ) ||
					isNameMatchingFixedField( fixedField, partIndex, parts ) ||
					isNameMatchingFixedLengthString( fixedLengthString, partIndex, parts ) ||
					isNameMatchingVariableField( variableField, partIndex, parts ) ||
					isNameMatchingVariableFormatField( variableFormatField, partIndex, parts ) ||
					isNameMatchingVariableLengthField( variableLengthField, partIndex, parts ) ||
					isNameMatchingVariableLengthString( variableLengthString, partIndex, parts ) )
			{
				return true;
			}

			for( Object obj : array.getDimension() )
			{
				if( obj instanceof org.jts.jsidl.binding.Dimension )
				{
					org.jts.jsidl.binding.Dimension dimension = ( org.jts.jsidl.binding.Dimension ) obj;

					if( isNameMatchingDimension( dimension, partIndex, parts ) )
					{
						return true;
					}
				}
			}
		}

		return false;
	}

	public static boolean isNameMatchingFixedField( org.jts.jsidl.binding.FixedField fixedField, int partIndex, String[] parts )
	{
		if( fixedField == null )
		{
			return false;
		}

		if( fixedField.getName().compareTo( parts[ partIndex ] ) == 0 )
		{
			if( partIndex == parts.length - 1 )
			{
				return true;
			}

			partIndex++;

			org.jts.jsidl.binding.ScaleRange scaleRange = fixedField.getScaleRange();
			org.jts.jsidl.binding.ValueSet valueSet = fixedField.getValueSet();

			if( isNameMatchingScaleRange( scaleRange, partIndex, parts ) ||
					isNameMatchingValueSet( valueSet, partIndex, parts ) )
			{
				return true;
			}
		}

		return false;
	}

	public static boolean isNameMatchingVariableField( org.jts.jsidl.binding.VariableField variableField, int partIndex, String[] parts )
	{
		if( variableField == null )
		{
			return false;
		}

		if( variableField.getName().compareTo( parts[ partIndex ] ) == 0 )
		{
			if( partIndex == parts.length - 1 )
			{
				return true;
			}

			partIndex++;

			org.jts.jsidl.binding.TypeAndUnitsField typeAndUnitsField = variableField.getTypeAndUnitsField();

			if( isNameMatchingTypeAndUnitsField( typeAndUnitsField, partIndex, parts ) )
			{
				return true;
			}
		}

		return false;
	}

	public static boolean isNameMatchingVtagField( org.jts.jsidl.binding.VtagField vtagField, int partIndex, String[] parts )
	{
		if( vtagField == null )
		{
			return false;
		}

		if( partIndex == parts.length - 1 )
		{
			return true;
		}

		return false;
	}

	public static boolean isNameMatchingBitField( org.jts.jsidl.binding.BitField bitField, int partIndex, String[] parts )
	{
		if( bitField == null )
		{
			return false;
		}

		if( bitField.getName().compareTo( parts[ partIndex ] ) == 0 )
		{
			if( partIndex == parts.length - 1 )
			{
				return true;
			}

			partIndex++;

			for( Object obj : bitField.getSubField() )
			{
				if( obj instanceof org.jts.jsidl.binding.SubField )
				{
					org.jts.jsidl.binding.SubField subField = ( org.jts.jsidl.binding.SubField ) obj;

					if( isNameMatchingSubField( subField, partIndex, parts ) )
					{
						return true;
					}
				}
			}
		}

		return false;
	}

	public static boolean isNameMatchingFixedLengthString( org.jts.jsidl.binding.FixedLengthString fixedLengthString, int partIndex, String[] parts )
	{
		if( fixedLengthString == null )
		{
			return false;
		}

		if( partIndex == parts.length - 1 )
		{
			return true;
		}

		return false;
	}

	public static boolean isNameMatchingVariableFormatField( org.jts.jsidl.binding.VariableFormatField variableFormatField, int partIndex, String[] parts )
	{
		if( variableFormatField == null )
		{
			return false;
		}

		if( variableFormatField.getName().compareTo( parts[ partIndex ] ) == 0 )
		{
			if( partIndex == parts.length - 1 )
			{
				return true;
			}

			partIndex++;

			org.jts.jsidl.binding.CountField countField = variableFormatField.getCountField();
			org.jts.jsidl.binding.FormatField formatField = variableFormatField.getFormatField();

			if( isNameMatchingCountField( countField, partIndex, parts ) ||
					isNameMatchingFormatField( formatField, partIndex, parts ) )
			{
				return true;
			}
		}

		return false;
	}

	public static boolean isNameMatchingVariableLengthField( org.jts.jsidl.binding.VariableLengthField variableLengthField, int partIndex, String[] parts )
	{
		if( variableLengthField == null )
		{
			return false;
		}

		if( variableLengthField.getName().compareTo( parts[ partIndex ] ) == 0 )
		{
			if( partIndex == parts.length - 1 )
			{
				return true;
			}

			partIndex++;

			org.jts.jsidl.binding.CountField countField = variableLengthField.getCountField();

			if( isNameMatchingCountField( countField, partIndex, parts ) )
			{
				return true;
			}
		}

		return false;
	}

	public static boolean isNameMatchingVariableLengthString( org.jts.jsidl.binding.VariableLengthString variableLengthString, int partIndex, String[] parts )
	{
		if( variableLengthString == null )
		{
			return false;
		}

		if( variableLengthString.getName().compareTo( parts[ partIndex ] ) == 0 )
		{
			if( partIndex == parts.length - 1 )
			{
				return true;
			}

			partIndex++;

			org.jts.jsidl.binding.CountField countField = variableLengthString.getCountField();

			if( isNameMatchingCountField( countField, partIndex, parts ) )
			{
				return true;
			}
		}

		return false;
	}

	public static boolean isNameMatchingDimension( org.jts.jsidl.binding.Dimension dimension, int partIndex, String[] parts )
	{
		if( dimension == null )
		{
			return false;
		}

		if( partIndex == parts.length - 1 )
		{
			return true;
		}

		return false;
	}

	public static boolean isNameMatchingScaleRange( org.jts.jsidl.binding.ScaleRange scaleRange, int partIndex, String[] parts )
	{
		if( scaleRange == null )
		{
			return false;
		}

		if( partIndex == parts.length - 1 )
		{
			return true;
		}

		return false;
	}

	public static boolean isNameMatchingValueSet( org.jts.jsidl.binding.ValueSet valueSet, int partIndex, String[] parts )
	{
		if( valueSet == null )
		{
			return false;
		}

		if( partIndex == parts.length - 1 )
		{
			return true;
		}

		// value set does not have a name, so don't continue with getValueRangeOrValueEnum()

		return false;
	}

	public static boolean isNameMatchingTypeAndUnitsField( org.jts.jsidl.binding.TypeAndUnitsField typeAndUnitsField, int partIndex, String[] parts )
	{
		if( typeAndUnitsField == null )
		{
			return false;
		}

		if( partIndex == parts.length - 1 )
		{
			return true;
		}

		// typeAndUnitsField does not have a name, so don't continue with getValueRangeOrValueEnum()

		return false;
	}

	public static boolean isNameMatchingSubField( org.jts.jsidl.binding.SubField subField, int partIndex, String[] parts )
	{
		if( subField == null )
		{
			return false;
		}

		if( subField.getName().compareTo( parts[ partIndex ] ) == 0 )
		{
			if( partIndex == parts.length - 1 )
			{
				return true;
			}

			partIndex++;

			org.jts.jsidl.binding.BitRange bitRange = subField.getBitRange();
			org.jts.jsidl.binding.ValueSet valueSet = subField.getValueSet();

			if( isNameMatchingBitRange( bitRange, partIndex, parts ) ||
					isNameMatchingValueSet( valueSet, partIndex, parts ) )
			{
				return true;
			}
		}

		return false;
	}

	public static boolean isNameMatchingCountField( org.jts.jsidl.binding.CountField countField, int partIndex, String[] parts )
	{
		if( countField == null )
		{
			return false;
		}

		if( partIndex == parts.length - 1 )
		{
			return true;
		}

		return false;
	}

	public static boolean isNameMatchingFormatField( org.jts.jsidl.binding.FormatField formatField, int partIndex, String[] parts )
	{
		if( formatField == null )
		{
			return false;
		}

		if( partIndex == parts.length - 1 )
		{
			return true;
		}

		// formatField does not have a name, so don't continue with getFormatEnum()

		return false;
	}

	public static boolean isNameMatchingBitRange( org.jts.jsidl.binding.BitRange bitRange, int partIndex, String[] parts )
	{
		if( bitRange == null )
		{
			return false;
		}

		if( partIndex == parts.length - 1 )
		{
			return true;
		}

		return false;
	}
	
	public static void getInheritedServiceDefList( Object obj, List<org.jts.jsidl.binding.ServiceDef> sdefs )
	{
		if( sdefs == null )
		{
			sdefs = new ArrayList<org.jts.jsidl.binding.ServiceDef>();
		}

		if( obj != null )
		{
			if( obj instanceof com.u2d.generated.ServiceDef )
			{
				com.u2d.generated.ServiceDef serviceDef = ( com.u2d.generated.ServiceDef ) obj;
				// recurse through referenced service defs to find all other protocol behaviors along inheritance chain
				if( serviceDef.getInheritsFrom() != null )
				{
					// query the database for the reference
					String id = serviceDef.getInheritsFrom().getServiceId().stringValue();
					String version = serviceDef.getInheritsFrom().get_version().toString();
	
					com.u2d.generated.ServiceDef genServiceDef =  org.jts.gui.JAXBtoJmatter.ServiceDef.lookupServiceDef( id, version );
	
					if( genServiceDef != null )
					{
						org.jts.jsidl.binding.ServiceDef sd = org.jts.gui.jmatterToJAXB.ServiceDef.convert( genServiceDef );
						sdefs.add( sd );
	
						// recurse through chain
						getInheritedServiceDefList( genServiceDef, sdefs );
					}
				}
			}
			else if( obj instanceof org.jts.jsidl.binding.ServiceDef )
			{
				org.jts.jsidl.binding.ServiceDef serviceDef = ( org.jts.jsidl.binding.ServiceDef ) obj;
				
				// recurse through referenced service defs to find all other protocol behaviors along inheritance chain
				if( serviceDef.getReferences() != null && serviceDef.getReferences().getInheritsFrom() != null )
				{
					// query the database for the reference
					String id = serviceDef.getReferences().getInheritsFrom().getId();
					String version = serviceDef.getReferences().getInheritsFrom().getVersion();
	
					com.u2d.generated.ServiceDef genServiceDef =  org.jts.gui.JAXBtoJmatter.ServiceDef.lookupServiceDef( id, version );
	
					if( genServiceDef != null )
					{
						org.jts.jsidl.binding.ServiceDef sd = org.jts.gui.jmatterToJAXB.ServiceDef.convert( genServiceDef );
						sdefs.add( sd );
	
						// recurse through chain
						getInheritedServiceDefList( sd, sdefs );
					}
				}
			}
		}
	}
	
	public static void getInheritedServiceDefList( Object obj, List<org.jts.jsidl.binding.ServiceDef> lookupSdefs, List<org.jts.jsidl.binding.ServiceDef> sdefs )
	{
		if( sdefs == null )
		{
			sdefs = new ArrayList<org.jts.jsidl.binding.ServiceDef>();
		}

		if( obj != null )
		{
			if( obj instanceof com.u2d.generated.ServiceDef )
			{
				com.u2d.generated.ServiceDef serviceDef = ( com.u2d.generated.ServiceDef ) obj;
				// recurse through referenced service defs to find all other protocol behaviors along inheritance chain
				if( serviceDef.getInheritsFrom() != null )
				{
					// query the database for the reference
					String id = serviceDef.getInheritsFrom().getServiceId().stringValue();
					String version = serviceDef.get_version().toString();
	
					com.u2d.generated.ServiceDef genServiceDef =  org.jts.gui.JAXBtoJmatter.ServiceDef.lookupServiceDef( id, version );
	
					if( genServiceDef != null )
					{
						org.jts.jsidl.binding.ServiceDef sd = org.jts.gui.jmatterToJAXB.ServiceDef.convert( genServiceDef );
						sdefs.add( sd );
	
						// recurse through chain
						getInheritedServiceDefList( genServiceDef, sdefs );
					}
					// desired sdef may be part of the lookup list as well
					else
					{
						for( org.jts.jsidl.binding.ServiceDef lookupSdef : lookupSdefs )
						{
							if( lookupSdef.getId().compareTo( id ) == 0 && lookupSdef.getVersion().compareTo( version ) == 0 )
							{
								sdefs.add( lookupSdef );
								
								// recurse through chain
								getInheritedServiceDefList( lookupSdef, lookupSdefs, sdefs );
							}
						}
					}
						
				}
			}
			else if( obj instanceof org.jts.jsidl.binding.ServiceDef )
			{
				org.jts.jsidl.binding.ServiceDef serviceDef = ( org.jts.jsidl.binding.ServiceDef ) obj;
				
				// recurse through referenced service defs to find all other protocol behaviors along inheritance chain
				if( serviceDef.getReferences() != null && serviceDef.getReferences().getInheritsFrom() != null )
				{
					// query the database for the reference
					String id = serviceDef.getReferences().getInheritsFrom().getId();
					String version = serviceDef.getReferences().getInheritsFrom().getVersion();
	
					com.u2d.generated.ServiceDef genServiceDef =  org.jts.gui.JAXBtoJmatter.ServiceDef.lookupServiceDef( id, version );
	
					if( genServiceDef != null )
					{
						org.jts.jsidl.binding.ServiceDef sd = org.jts.gui.jmatterToJAXB.ServiceDef.convert( genServiceDef );
						sdefs.add( sd );
	
						// recurse through chain
						getInheritedServiceDefList( sd, sdefs );
					}
					// desired sdef may be part of the lookup list as well
					else
					{
						for( org.jts.jsidl.binding.ServiceDef lookupSdef : lookupSdefs )
						{
							if( lookupSdef.getId().compareTo( id ) == 0 && lookupSdef.getVersion().compareTo( version ) == 0 )
							{
								sdefs.add( lookupSdef );
								
								// recurse through chain
								getInheritedServiceDefList( lookupSdef, lookupSdefs, sdefs );
							}
						}
					}
				}
			}
		}
	}

	public static org.jts.jsidl.binding.ServiceDef findLeafServiceDefInList( List<org.jts.jsidl.binding.ServiceDef> sdefs )
	{
		String rootId = getRootServiceDefIdInList( sdefs );

		return getChildServiceDefInList( sdefs, rootId );
	}

	public static org.jts.jsidl.binding.ServiceDef getChildServiceDefInList( List<org.jts.jsidl.binding.ServiceDef> sdefs, String parentId )
	{
		for( org.jts.jsidl.binding.ServiceDef sdef : sdefs )
		{
			if( sdef.getReferences() != null && 
					sdef.getReferences().getInheritsFrom() != null &&
					sdef.getReferences().getInheritsFrom().getId() != null &&
					sdef.getReferences().getInheritsFrom().getId().compareTo( parentId ) == 0 )
			{
				return getChildServiceDefInList( sdefs, sdef.getId() );
			}
		}

		for( org.jts.jsidl.binding.ServiceDef sdef : sdefs )
		{
			if( sdef.getId().compareTo( parentId ) == 0 )
			{
				return sdef;
			}
		}
		
		return null;
	}

	public static String getRootServiceDefIdInList( List<org.jts.jsidl.binding.ServiceDef> sdefs )
	{
		for( org.jts.jsidl.binding.ServiceDef sdef : sdefs )
		{
			if( sdef.getReferences() == null || sdef.getReferences().getInheritsFrom() == null )
			{
				return sdef.getId();
			}
		}

		return null;
	}

	/**
	 * Finds the the top most service def in the list
	 * The one that either has no references or the one that has a reference not found in the list
	 */
	public static org.jts.jsidl.binding.ServiceDef getRootServiceDefInList( List<org.jts.jsidl.binding.ServiceDef> sdefs )
	{
		for( org.jts.jsidl.binding.ServiceDef sdef : sdefs )
		{
			org.jts.jsidl.binding.ServiceDef parent = org.jts.pbValidator.ValidatorUtils.getParentServiceDef( sdefs, sdef );

			// no parent service def in list OR no parent service def defined
			if( parent == null )
			{
				return sdef;
			}
		}

		return null;
	}

	public static org.jts.jsidl.binding.ServiceDef getServiceDefInList( List<org.jts.jsidl.binding.ServiceDef> sdefs, String sdefId )
	{
		for( org.jts.jsidl.binding.ServiceDef sdef : sdefs )
		{
			if( sdefId.compareTo( sdef.getId() ) == 0 )
			{
				return sdef;
			}
		}

		return null;
	}

	public static org.jts.jsidl.binding.ServiceDef getCurrentServiceDef( List<org.jts.jsidl.binding.ServiceDef> sdefs )
	{
		org.jts.jsidl.binding.ServiceDef currentServiceDef = org.jts.pbValidator.ValidatorUtils.findLeafServiceDefInList( sdefs );

		return currentServiceDef;
	}
	
	public static org.jts.jsidl.binding.ServiceDef getParentServiceDef( List<org.jts.jsidl.binding.ServiceDef> sdefs )
	{
		org.jts.jsidl.binding.ServiceDef currentServiceDef = org.jts.pbValidator.ValidatorUtils.getCurrentServiceDef( sdefs );
		org.jts.jsidl.binding.ServiceDef parentServiceDef = null;
		
		if( currentServiceDef != null && 
				currentServiceDef.getReferences() != null && 
				currentServiceDef.getReferences().getInheritsFrom() != null &&
				currentServiceDef.getReferences().getInheritsFrom().getId() != null )
		{
			parentServiceDef = org.jts.pbValidator.ValidatorUtils.getServiceDefInList( sdefs, currentServiceDef.getReferences().getInheritsFrom().getId() );
		}

		return parentServiceDef;
	}

	public static org.jts.jsidl.binding.ServiceDef getParentServiceDef( List<org.jts.jsidl.binding.ServiceDef> sdefs, org.jts.jsidl.binding.ServiceDef currentServiceDef )
	{
		org.jts.jsidl.binding.ServiceDef parentServiceDef = null;
		
		if( currentServiceDef != null && 
				currentServiceDef.getReferences() != null && 
				currentServiceDef.getReferences().getInheritsFrom() != null &&
				currentServiceDef.getReferences().getInheritsFrom().getId() != null )
		{
			parentServiceDef = org.jts.pbValidator.ValidatorUtils.getServiceDefInList( sdefs, currentServiceDef.getReferences().getInheritsFrom().getId() );
		}

		return parentServiceDef;
	}

	public static boolean isMxStateMachine( Object obj )
	{
		String value = "";

		if( obj instanceof com.mxgraph.model.mxCell )
		{
			value = ( ( com.mxgraph.model.mxCell )obj ).getStyle();
		}
		else if( obj instanceof com.mxgraph.model.mxICell )
		{
			value = ( ( com.mxgraph.model.mxICell )obj ).getStyle();
		}

		if( value.startsWith("finiteStateMachine") )
		{
			return true;
		}

		return false;
	}

	public static boolean isMxState( Object obj )
	{
		String value = "";

		if( obj instanceof com.mxgraph.model.mxCell )
		{
			value = ( ( com.mxgraph.model.mxCell )obj ).getStyle();
		}
		else if( obj instanceof com.mxgraph.model.mxICell )
		{
			value = ( ( com.mxgraph.model.mxICell )obj ).getStyle();
		}

		if( value.startsWith("state") )
		{
			return true;
		}

		return false;
	}

	public static boolean isMxDefaultState( Object obj )
	{
		String value = "";

		if( obj instanceof com.mxgraph.model.mxCell )
		{
			value = ( ( com.mxgraph.model.mxCell )obj ).getStyle();
		}
		else if( obj instanceof com.mxgraph.model.mxICell )
		{
			value = ( ( com.mxgraph.model.mxICell )obj ).getStyle();
		}

		if( value.startsWith("defaultState") )
		{
			return true;
		}

		return false;
	}

	public static boolean isMxPseudoStartState( Object obj )
	{
		String value = "";

		if( obj instanceof com.mxgraph.model.mxCell )
		{
			value = ( ( com.mxgraph.model.mxCell )obj ).getStyle();
		}
		else if( obj instanceof com.mxgraph.model.mxICell )
		{
			value = ( ( com.mxgraph.model.mxICell )obj ).getStyle();
		}

		if( value.startsWith("pseudoStartState") )
		{
			return true;
		}

		return false;
	}

	public static boolean isMxTransition( Object obj )
	{
		if( isMxInternalTransition( obj ) ||
				isMxDefaultInternalTransition( obj ) ||
				isMxSimpleTransition( obj ) ||
				isMxDefaultSimpleTransition( obj ) ||
				isMxPushTransition( obj ) ||
				isMxDefaultPushTransition( obj ) ||
				isMxPopTransition( obj ) ||
				isMxDefaultPopTransition( obj ) )
		{
			return true;
		}

		return false;
	}

	public static boolean isMxInternalTransition( Object obj )
	{
		String value = "";

		if( obj instanceof com.mxgraph.model.mxCell )
		{
			value = ( ( com.mxgraph.model.mxCell )obj ).getStyle();
		}
		else if( obj instanceof com.mxgraph.model.mxICell )
		{
			value = ( ( com.mxgraph.model.mxICell )obj ).getStyle();
		}

		if( value.startsWith("internalTransition") )
		{
			return true;
		}

		return false;
	}

	public static boolean isMxDefaultInternalTransition( Object obj )
	{
		String value = "";

		if( obj instanceof com.mxgraph.model.mxCell )
		{
			value = ( ( com.mxgraph.model.mxCell )obj ).getStyle();
		}
		else if( obj instanceof com.mxgraph.model.mxICell )
		{
			value = ( ( com.mxgraph.model.mxICell )obj ).getStyle();
		}

		if( value.startsWith("defualtInternalTransition") )
		{
			return true;
		}

		return false;
	}
	
	public static boolean isMxSimpleTransition( Object obj )
	{
		String value = "";

		if( obj instanceof com.mxgraph.model.mxCell )
		{
			value = ( ( com.mxgraph.model.mxCell )obj ).getStyle();
		}
		else if( obj instanceof com.mxgraph.model.mxICell )
		{
			value = ( ( com.mxgraph.model.mxICell )obj ).getStyle();
		}

		if( value.contains("simpleTransition") )
		{
			return true;
		}

		return false;
	}

	public static boolean isMxDefaultSimpleTransition( Object obj )
	{
		String value = "";

		if( obj instanceof com.mxgraph.model.mxCell )
		{
			value = ( ( com.mxgraph.model.mxCell )obj ).getStyle();
		}
		else if( obj instanceof com.mxgraph.model.mxICell )
		{
			value = ( ( com.mxgraph.model.mxICell )obj ).getStyle();
		}

		if( value.startsWith("defaultSimpleTransition") )
		{
			return true;
		}

		return false;
	}
	
	public static boolean isMxPushTransition( Object obj )
	{
		String value = "";

		if( obj instanceof com.mxgraph.model.mxCell )
		{
			value = ( ( com.mxgraph.model.mxCell )obj ).getStyle();
		}
		else if( obj instanceof com.mxgraph.model.mxICell )
		{
			value = ( ( com.mxgraph.model.mxICell )obj ).getStyle();
		}

		if( value.startsWith("pushTransition") )
		{
			return true;
		}

		return false;
	}

	public static boolean isMxDefaultPushTransition( Object obj )
	{
		String value = "";

		if( obj instanceof com.mxgraph.model.mxCell )
		{
			value = ( ( com.mxgraph.model.mxCell )obj ).getStyle();
		}
		else if( obj instanceof com.mxgraph.model.mxICell )
		{
			value = ( ( com.mxgraph.model.mxICell )obj ).getStyle();
		}

		if( value.startsWith("defaultPushTransition") )
		{
			return true;
		}

		return false;
	}

	public static boolean isMxPopTransition( Object obj )
	{
		String value = "";

		if( obj instanceof com.mxgraph.model.mxCell )
		{
			value = ( ( com.mxgraph.model.mxCell )obj ).getStyle();
		}
		else if( obj instanceof com.mxgraph.model.mxICell )
		{
			value = ( ( com.mxgraph.model.mxICell )obj ).getStyle();
		}

		if( value.startsWith("popTransition") )
		{
			return true;
		}

		return false;
	}

	public static boolean isMxDefaultPopTransition( Object obj )
	{
		String value = "";

		if( obj instanceof com.mxgraph.model.mxCell )
		{
			value = ( ( com.mxgraph.model.mxCell )obj ).getStyle();
		}
		else if( obj instanceof com.mxgraph.model.mxICell )
		{
			value = ( ( com.mxgraph.model.mxICell )obj ).getStyle();
		}

		if( value.startsWith("defaultPopTransition") )
		{
			return true;
		}

		return false;
	}

}
