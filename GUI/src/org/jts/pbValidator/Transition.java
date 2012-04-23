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
class Transition
{
	List<ValidatorResult> results = null;

	Transition()
	{
		results = new ArrayList();
	}

	List<ValidatorResult> validate( List<org.jts.jsidl.binding.ServiceDef> sdefs, Object toValidate, StringBuffer path )
	{
		if( toValidate instanceof org.jts.jsidl.binding.Transition )
		{
			// cast object to transition
			org.jts.jsidl.binding.Transition transition = (org.jts.jsidl.binding.Transition)toValidate;

			// update path
			updatePath( transition, path  );

			// validate transition
			validateTransition( sdefs, transition, null, path );
		}
		else
		{
			// no successors
		}

		return results;
	}

	List<ValidatorResult> validate( List<org.jts.jsidl.binding.ServiceDef> sdefs, Object toValidate, org.jts.pbValidator.StateMap map, StringBuffer path )
	{
		if( toValidate instanceof org.jts.jsidl.binding.Transition )
		{
			// cast object to transition
			org.jts.jsidl.binding.Transition transition = (org.jts.jsidl.binding.Transition)toValidate;

			// update path
			updatePath( transition, path  );

			// validate transition
			validateTransition( sdefs, transition, map, path );
		}
		else
		{
			// no successors
		}

		return results;
	}

	/**
	 * Updates the error path to include the transition
	 * @param transition
	 * @param path
	 */
	private void updatePath( org.jts.jsidl.binding.Transition transition, StringBuffer path )
	{
		String name = transition.getName();
		int dot = name.lastIndexOf('.');

		if ( dot != -1 )
		{
			name = name.substring( dot+1 ) ;
		}

		StringBuffer pbuf = new StringBuffer();
		List<org.jts.jsidl.binding.Parameter> params = transition.getParameter();

		if( params != null )
		{
			for( int ii=0; ii<params.size(); ii++ )
			{
				pbuf.append( params.get(ii).getType()+" "+params.get(ii).getValue() );
				if( ii < params.size() )
				{
					pbuf.append( ", " );
				}
			}
		}
		path.append(": "+name+"("+pbuf.toString()+")"+"[Transition]");
	}

	/**
	 * Validates the transition by verifying
	 * -transition's name syntax
	 * -transition's name source
	 * -transition's unique triplet
	 * -transition's type
	 * and validating the transition's
	 * -parameters
	 * -guards
	 * -actions
	 * -mxcells
	 * Expects a non-null transition
	 * @param sdefs
	 * @param transition
	 * @param action
	 * @param successorPath
	 */
	private void validateTransition( List<org.jts.jsidl.binding.ServiceDef> sdefs, org.jts.jsidl.binding.Transition transition, org.jts.pbValidator.StateMap map, StringBuffer successorPath )
	{
		//---------- name of transition must be valid C identifier ----------//
		if( !verifyTransitionNameSyntax( transition.getName(), successorPath) )
			return;

		//---------- name of transition must be message name or internal event name ----------//
		if( !verifyTransitionNameSource( transition.getName(), sdefs, successorPath) )
			return;

		//---------- transition must have unique (name:params:guards) combination within the scope of its inheritence chain ----------//
		if( !verifyUniqueTransitionCombination( transition, map, successorPath ) )
			return;

		//---------- transition must be one of the types ----------//
		if( !org.jts.pbValidator.ValidatorUtils.verifyTransitionHasType( transition, results, successorPath ) )
		{
			// TODO: continue validation?
			return;
		}

		//---------- simple definition must be valid ----------//
		if( !org.jts.pbValidator.ValidatorUtils.verifySimpleTransition( transition.getSimple(), map, results, successorPath ) )
		{
			// TODO: continue validation?
			return;
		}

		//---------- push definition must be valid ----------//
		if( !org.jts.pbValidator.ValidatorUtils.verifyPushTransition( transition.getPush(), map, results, successorPath ) )
		{
			// TODO: continue validation?
			return;
		}

		//---------- pop definition must be valid ----------//
		if( !org.jts.pbValidator.ValidatorUtils.verifyPopTransition( transition, transition.getPop(), results, successorPath ) )
		{
			// TODO: continue validation?
			return;
		}

		//---------- internal definition must be valid ----------//
		if( !org.jts.pbValidator.ValidatorUtils.verifyInternalTransition( transition.getInternal(), results, successorPath ) )
		{
			// TODO: continue validation?
			return;
		}
		
		//---------- warn when transition overrides parent service transition ----------//
		warnTransitionOverridesParentTransition( transition, sdefs, map, successorPath );

		//---------- warn when transition changes parent service state (push and simple)----------//
		warnTransitionChangesParentState( transition, sdefs, map, successorPath );

		// validate zero or more parameters for the transition
		List<org.jts.jsidl.binding.Parameter> parameters = transition.getParameter();
		if( parameters != null )
		{
			for( org.jts.jsidl.binding.Parameter parameter : parameters )
			{
				results.addAll( (new Parameter()).validate( sdefs, parameter, new StringBuffer( successorPath ) ) );
			}
		}

		// validate zero or more guards for the transition
		org.jts.jsidl.binding.Guard guard = transition.getGuard();
		if( guard != null )
		{
			results.addAll( (new Guard()).validate( sdefs, transition, guard, new StringBuffer( successorPath ) ) );
		}

		// validate zero or more actions for the transition
		List actionOrSendActions = transition.getActionOrSendAction();
		if( actionOrSendActions != null )
		{
			for( Object actionOrSendAction : actionOrSendActions )
			{
				results.addAll( (new Action()).validate( sdefs, transition, actionOrSendAction, new StringBuffer( successorPath ) ) );
				results.addAll( (new SendAction()).validate( sdefs, actionOrSendAction, new StringBuffer( successorPath ) ) );
			}
		}

		// validate zero or more cells for the transition
		org.jts.jsidl.binding.MxCell cell = transition.getMxCell();
		if( cell != null )
		{
			results.addAll( (new MxCell()).validate( sdefs, cell, new StringBuffer( successorPath ) ) );
		}

		// validate internal, simple, push, pop, end_state internally in this class
	}

	/**
	 * Verifies that a transition has a valid name identifier in the C programming language
	 * Expects transition to be non-null
	 * @param transition
	 * @param successorPath
	 * @return
	 */
	private boolean verifyTransitionNameSyntax( String transitionName, StringBuffer successorPath )
	{
		// Make sure the string exists
		if( !org.jts.pbValidator.ValidatorUtils.verifyValidCNameType( transitionName ) )
		{
			results.add( new ValidatorResult( "Transition name must be a valid identifier in the C programming language", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
			// TODO:continue validation?
		}

		return true;
	}

	/**
	 * Verifies that the transition name is the same as a message definition or internal event as defined within the service def
	 * Expects a non-null transition name
	 * Expects a non-null sdefs list
	 * @param transitionName
	 * @param successorPath
	 * @return
	 */
	private boolean verifyTransitionNameSource( String transitionName, List<org.jts.jsidl.binding.ServiceDef> sdefs, StringBuffer successorPath )
	{
		org.jts.jsidl.binding.ServiceDef currentServiceDef = org.jts.pbValidator.ValidatorUtils.getCurrentServiceDef( sdefs );
		
		// we may be trying to validate a protocol that hasn't been attached to a service yet
		// just throw warning so the user will be able to save the pb
		if( currentServiceDef == null )
		{
			results.add( new ValidatorResult( "Transition cannot be validated without attaching the protocol behavior to a service definition: "+transitionName, successorPath.toString(), ValidatorResult.TYPE_WARNING ) );
			return true;
		}

		if( transitionNameValidInService( currentServiceDef, transitionName ) )
		{
			return true;
		}

		// otherwise it must be a namespaced name
		if( isNamespacedMessageOrEvent( sdefs, currentServiceDef, transitionName ) )
		{
			return true;
		}

		results.add( new ValidatorResult( "Transition name must match a message name or internal event name:"+transitionName, successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
		// TODO:continue validation?
		
		return true;
	}

	/**
	 * Verifies that the transition definition has a unique triple (name:parameters:guards) within the context of the flattened state
	 * Expects a non-null transition
	 * Accepts a null transition
	 * @param transition
	 * @param state
	 * @param successorPath
	 * @return
	 */
	private boolean verifyUniqueTransitionCombination( org.jts.jsidl.binding.Transition transition, org.jts.pbValidator.StateMap map, StringBuffer successorPath )
	{
		if( map == null || map.getState() == null )
		{       // TODO: need to find a way to do this when validating a transition in a default state
			//results.add( new ValidatorResult( "Transition cannot be validated aganist its siblings when not passed with the state that contains the transition", successorPath.toString(), ValidatorResult.TYPE_WARNING ) );
			// TODO:continue validation?
		}
		else
		{
			org.jts.jsidl.binding.State state = map.getState();

			int count = 0;
			for( org.jts.jsidl.binding.Transition tr : state.getTransition() )
			{
				if( org.jts.pbValidator.ValidatorUtils.isTransitionSame( tr, transition ) )
				{
					count++;
				}
			}
			
			if( count != 1 )
			{
				results.add( new ValidatorResult( "Transition is not unique within its state, must have unique name:guards:parameters combination", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
			}
		}
		return true;
	}

	private boolean transitionNameValidInService( org.jts.jsidl.binding.ServiceDef serviceDef, String transitionName )
	{
		for( Object obj : serviceDef.getMessageSet().getInputSet().getMessageDefOrDeclaredMessageDef() )
		{
			if( transitionNameMatchesMessageName( transitionName, obj ) )
			{
				return true;
			}
		}
		
		for( Object obj : serviceDef.getMessageSet().getOutputSet().getMessageDefOrDeclaredMessageDef() )
		{
			if( transitionNameMatchesMessageName( transitionName, obj ) )
			{
				return true;
			}
		}
		
		for( Object obj : serviceDef.getInternalEventsSet().getEventDefOrDeclaredEventDef() )
		{
			if( transitionNameMatchesMessageName( transitionName, obj ) )
			{
				return true;
			}
		}

		return false;
	}
	
	/**
	 * Check to see if a transitionName matches the message def name or event def name
	 * @param transitionName
	 * @param obj
	 * @return
	 */
	private boolean transitionNameMatchesMessageName( String transitionName, Object obj )
	{
		if( obj instanceof org.jts.jsidl.binding.MessageDef )
		{
			org.jts.jsidl.binding.MessageDef msgDef = (org.jts.jsidl.binding.MessageDef) obj;
			
			if( transitionName.compareTo( msgDef.getName() ) == 0 )
			{
				return true;
			}
		}
		else if( obj instanceof org.jts.jsidl.binding.EventDef )
		{
			org.jts.jsidl.binding.EventDef eventDef = (org.jts.jsidl.binding.EventDef) obj;
			
			if( transitionName.compareTo( eventDef.getName() ) == 0 )
			{
				return true;
			}
		}
		
		return false;
	}

	/* original check for namespaced message names in an inheritance chain
	private boolean isNamespacedMessageOrEvent( List<org.jts.jsidl.binding.ServiceDef> sdefs, org.jts.jsidl.binding.ServiceDef currentServiceDef, String transitionName )
	{
		if( transitionName == null )
		{
			return false;
		}

		if( !transitionName.contains( "." ) )
		{
			return false;
		}

		String[] parts = transitionName.split( "\\." );
		String objectName = parts[ parts.length - 1 ];

		for( int i = 0; i < parts.length - 1; i++ )
		{
			if( currentServiceDef == null ||
					currentServiceDef.getReferences() == null ||
					currentServiceDef.getReferences().getInheritsFrom() == null )
			{
				return false;
			}

			String topName = parts[ i ];
			String currentReference = currentServiceDef.getReferences().getInheritsFrom().getName();

			if( topName.compareTo( currentReference ) != 0 )
			{
				return false;
			}

			currentServiceDef = org.jts.newValidator.ValidatorUtils.getServiceDefInList( sdefs, currentServiceDef.getReferences().getInheritsFrom().getId() );
		}

		if( transitionNameValidInService( currentServiceDef, objectName ) )
		{
			return true;
		}
		
		return false;
	}*/
	
	// new check that only checks the message/internal event names and does not consider the namespacing
	private boolean isNamespacedMessageOrEvent( List<org.jts.jsidl.binding.ServiceDef> sdefs, org.jts.jsidl.binding.ServiceDef currentServiceDef, String transitionName )
	{
		if( transitionName == null )
		{
			return false;
		}
		
		// remove any namespacing
		if( transitionName.indexOf( "." ) != -1 )
		{
			transitionName = transitionName.substring( transitionName.lastIndexOf( "." ) + 1 );
		}

		// check referenced service defs
		if( currentServiceDef == null &&
				currentServiceDef.getReferences() == null &&
				currentServiceDef.getReferences().getInheritsFrom() == null &&
				currentServiceDef.getReferences().getClientOf() == null )
		{
			return false;
		}

		// check inherits from service defs
		if( currentServiceDef != null &&
				currentServiceDef.getReferences() != null &&
				currentServiceDef.getReferences().getInheritsFrom() != null )
		{
			currentServiceDef = org.jts.pbValidator.ValidatorUtils.getServiceDefInList( sdefs, currentServiceDef.getReferences().getInheritsFrom().getId() );
		
			// check the inherited service def for the transition name
			if( transitionNameValidInService( currentServiceDef, transitionName ) )
			{
				return true;
			}
			
			// when the transition was not valid in the parent service, recurse with the parent service def
			if( isNamespacedMessageOrEvent( sdefs, currentServiceDef, transitionName ) )
			{
				return true;
			}
		}
		
		// check client of service defs
		if( currentServiceDef != null &&
				currentServiceDef.getReferences() != null &&
				currentServiceDef.getReferences().getClientOf() != null )
		{
			for( org.jts.jsidl.binding.ClientOf clientOf : currentServiceDef.getReferences().getClientOf() )
			{
				currentServiceDef = org.jts.pbValidator.ValidatorUtils.getServiceDefInList( sdefs, clientOf.getId() );
				
				// check the inherited service def for the transition name
				if( transitionNameValidInService( currentServiceDef, transitionName ) )
				{
					return true;
				}
			
				// when the transition was not valid in the parent service, recurse with the parent service def
				if( isNamespacedMessageOrEvent( sdefs, currentServiceDef, transitionName ) )
				{
					return true;
				}
			}
		}
		
		return false;
	}
	
	private boolean warnTransitionOverridesParentTransition( org.jts.jsidl.binding.Transition transition, List<org.jts.jsidl.binding.ServiceDef> sdefs, org.jts.pbValidator.StateMap map, StringBuffer successorPath )
	{
		if( map == null )
		{
			//results.add( new ValidatorResult( "Cannot verify warning without complete definition", successorPath.toString(), ValidatorResult.TYPE_WARNING ) );
			return false;
		}
		
		org.jts.jsidl.binding.ServiceDef parentServiceDef = org.jts.pbValidator.ValidatorUtils.getParentServiceDef( sdefs );
		
		while( parentServiceDef != null )
		{
			for( org.jts.jsidl.binding.StateMachine sm : parentServiceDef.getProtocolBehavior().getStateMachine() )
			{
				org.jts.pbValidator.StateMap parentMap = new StateMap( sm );
				
				org.jts.pbValidator.StateMap foundMap = map.getMatchingStateInMap( parentMap );
				
				if( foundMap != null && foundMap.isMatchingTransitionNameInMapToRoot( transition ) )
				{
					results.add( new ValidatorResult( "Trigger being overriden, make sure all instances are overriden", successorPath.toString(), ValidatorResult.TYPE_WARNING ) );
					return false;
				}
			}

			parentServiceDef = org.jts.pbValidator.ValidatorUtils.getParentServiceDef( sdefs, parentServiceDef );
		}

		return true;
	}
	
	private boolean warnTransitionChangesParentState( org.jts.jsidl.binding.Transition transition, List<org.jts.jsidl.binding.ServiceDef> sdefs, org.jts.pbValidator.StateMap map, StringBuffer successorPath )
	{
		if( map == null )
		{
			//results.add( new ValidatorResult( "Cannot verify warning without complete definition", successorPath.toString(), ValidatorResult.TYPE_WARNING ) );
			return false;
		}

        org.jts.jsidl.binding.Simple simple = transition.getSimple();
        org.jts.jsidl.binding.Push push = transition.getPush();
        String endState = null;

        if( simple != null )
        {
            if( simple.getEndState() != null )
            {
                endState = simple.getEndState().getState();
            }
        }
        else if( push != null )
        {
            if( push.getEndState() != null )
            {
                endState = push.getEndState().getState();
            }
        }
        else
        {
            return true;
        }

        // loopback doesn't change inherited state
        if( endState == null )
        {
            return true;
        }
		
		org.jts.jsidl.binding.ServiceDef parentServiceDef = org.jts.pbValidator.ValidatorUtils.getParentServiceDef( sdefs );
		
		if( parentServiceDef != null )
		{
			for( org.jts.jsidl.binding.StateMachine sm : parentServiceDef.getProtocolBehavior().getStateMachine() )
			{
				if( map.isSameStateMachine( sm ) )
				{
					org.jts.pbValidator.StateMap parentMap = new StateMap( sm );
					
					// get map for endstate
					org.jts.pbValidator.StateMap foundMap = map.getMatchingStateInMap( endState );
					
					// end state might only be defined in parent service
					if( foundMap == null )
					{
						foundMap = parentMap.getMatchingStateInMap( endState );
					}
					
					// get the end state as defined in the parent service
					foundMap = foundMap.getMatchingStateInMap( parentMap );
					
					// follow end state to leaf state in parent service
					foundMap = foundMap.followInitialStateUntilLeaf();
					
					// find the endstate representation in the parent service 
					List<String> endStateLeafRepresentationInParentService = foundMap.getNamesListRootToCurrent();
					
					// find the current state representation in the parent service 
					List<String> currentStateRepresentationInParentService = map.getCurrentStateRepresentationInMap( parentMap );
					
					// there will never be a case where the leaf list will have a smaller size than the current
					// and it not be an change in the parent state
					if( endStateLeafRepresentationInParentService.size() < currentStateRepresentationInParentService.size() )
					{
						results.add( new ValidatorResult( "EndState causes a state change in the parent service", successorPath.toString(), ValidatorResult.TYPE_WARNING ) );
						return false;
					}
					
					for( int i = 0; i < currentStateRepresentationInParentService.size(); i++ )
					{
						String endStatePart = endStateLeafRepresentationInParentService.get( i );
						String currentStatePart = currentStateRepresentationInParentService.get( i );

						// warn if parts are different (we are switching states)
						if( endStatePart.compareTo( currentStatePart ) != 0 )
						{
							results.add( new ValidatorResult( "EndState causes a state change in the parent service", successorPath.toString(), ValidatorResult.TYPE_WARNING ) );
							return false;
						}
						
					}
				}
			}
		}

		return true;
	}
}
