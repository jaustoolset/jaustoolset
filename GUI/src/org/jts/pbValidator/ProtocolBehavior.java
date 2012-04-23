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
 * @author Arfath Pasha
 *
 */
class ProtocolBehavior
{
	List<ValidatorResult> results = null;

	ProtocolBehavior()
	{
		results = new ArrayList();
	}

	// assumes we are validating the pb in the leaf service in the list of service defs
	List<ValidatorResult> validate( List<org.jts.jsidl.binding.ServiceDef> sdefs, Object toValidate )
	{
		StringBuffer path = new StringBuffer();

		if( toValidate instanceof org.jts.jsidl.binding.ProtocolBehavior )
		{
			// cast object to protocol behavior
			org.jts.jsidl.binding.ProtocolBehavior protocolBehavior = (org.jts.jsidl.binding.ProtocolBehavior)toValidate;

			// update path
			updatePath(protocolBehavior, path);

			// validate protocol behavior
			validateProtocolBehavior(sdefs, protocolBehavior, path);
		}
		else
		{
			// validate successors
			results.addAll( (new Start()).validate( sdefs, toValidate, path ) );

			results.addAll( (new StateMachine()).validate( sdefs, toValidate, path ) );
		}

		return results;
	}

	private void updatePath( org.jts.jsidl.binding.ProtocolBehavior protocolBehavior, StringBuffer path )
	{
		path.append( "[ProtocolBehavior]");
	}

	private void validateProtocolBehavior( List<org.jts.jsidl.binding.ServiceDef> sdefs, org.jts.jsidl.binding.ProtocolBehavior protocolBehavior, StringBuffer successorPath )
	{
		if(protocolBehavior.getStart() == null)
		{
			results.add( new ValidatorResult( "Protocol Behavior must have a start state", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
			return;
		}
		
		if(protocolBehavior.getStateMachine() == null || protocolBehavior.getStateMachine().size() < 1)
		{
			results.add( new ValidatorResult( "Protocol Behavior must have one or more state machines", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
			return;
		}

		if(protocolBehavior.getStart().size() != protocolBehavior.getStateMachine().size())
		{
			results.add( new ValidatorResult( "Protocol Behavior starts count must be equal to state machine count", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
			return;
		}

		verifyMatchingInheritedProperties( sdefs, successorPath );

		List<org.jts.jsidl.binding.Start> starts = protocolBehavior.getStart();
		for( org.jts.jsidl.binding.Start start : starts )
		{
			results.addAll( (new Start()).validate( sdefs, start, protocolBehavior, successorPath ) );
		}

		List<org.jts.jsidl.binding.StateMachine> stateMachines = protocolBehavior.getStateMachine();
		for( org.jts.jsidl.binding.StateMachine sm : stateMachines )
		{
			results.addAll( (new StateMachine()).validate( sdefs, sm, successorPath ) );
		}
	}

	private void verifyMatchingInheritedProperties( List<org.jts.jsidl.binding.ServiceDef> sdefs, StringBuffer successorPath )
	{
		org.jts.jsidl.binding.ServiceDef leaf = org.jts.pbValidator.ValidatorUtils.findLeafServiceDefInList( sdefs );

		// could be only service
		if( leaf == null || leaf.getReferences() == null || leaf.getReferences().getInheritsFrom() == null || leaf.getReferences().getInheritsFrom().getId() == null )
		{
			return;
		}

		org.jts.jsidl.binding.ServiceDef parent = org.jts.pbValidator.ValidatorUtils.getServiceDefInList( sdefs, leaf.getReferences().getInheritsFrom().getId() );

		verifyMatchingInheritedProperties( leaf, parent, successorPath );
		
		return;
	}

	private void verifyMatchingInheritedProperties( org.jts.jsidl.binding.ServiceDef childSD, org.jts.jsidl.binding.ServiceDef parentSD, StringBuffer successorPath )
	{
		// case 1:
		// when there is no inheritance, protocol behavior there is no inheritance matching to do
		if( childSD == null || parentSD == null )
		{
			return;
		}

		org.jts.jsidl.binding.ProtocolBehavior childPB = childSD.getProtocolBehavior();
		org.jts.jsidl.binding.ProtocolBehavior parentPB = parentSD.getProtocolBehavior();

		// each service definition should have a protocol behavior
		if( childPB == null || parentPB == null )
		{
			results.add( new ValidatorResult( "Missing protocol behavior", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
			return;
		}
		
		// make sure all state machines in parent are defined in child
		for( org.jts.jsidl.binding.StateMachine sm : parentPB.getStateMachine() )
		{
			getMatchingStateMachine( childPB, sm, successorPath );
		}
		
		// NOTE: assume for now that state machine name will not be overriden in a child protocol behavior
		// make sure all state machines (names) in parent are defined in child
		for( org.jts.jsidl.binding.StateMachine parentSM : parentPB.getStateMachine() )
		{
			String parentStateMachineName = getFinalStateMachineName( parentSM );
		
			getMatchingInheritedStateMachine( childPB, parentStateMachineName, successorPath );
		}
		
		// make sure start is pointing to same state as parent
		for( org.jts.jsidl.binding.Start parentStart : parentPB.getStart() )
		{
			if( parentStart == null )
			{
				results.add( new ValidatorResult( "Invalid inherited start", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
				return;
			}
			
			String parentStateMachineName = getFinalStateMachineName( parentStart );
			
			if( parentStateMachineName == null )
			{
				results.add( new ValidatorResult( "Invalid inherited state machine name in start", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
				return;
			}

			org.jts.jsidl.binding.StateMachine parentStateMachine = getStateMachineFromName( parentPB, parentStateMachineName );

			String parentStartStateName = findFlattenedLeafStateNameInStateMachine( parentStateMachine, parentStart.getStateName() );

			if( parentStartStateName == null )
			{
				results.add( new ValidatorResult( "Start state name not valid for state machine: " + parentStateMachine.getName(), successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
				return;
			}
			
			// now match against the matching child start
			for( org.jts.jsidl.binding.Start childStart : childPB.getStart() )
			{
				if( childStart == null )
				{
					results.add( new ValidatorResult( "Invalid start", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
					return;
				}
				
				String childStateMachineName = getFinalStateMachineName( childStart );
				
				if( childStateMachineName == null )
				{
					results.add( new ValidatorResult( "Invalid inherited state machine name in start", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
					return;
				}

				// get child state machine before we trim the name to match inherited state machine name
				org.jts.jsidl.binding.StateMachine childStateMachine = getStateMachineFromName( childPB, childStateMachineName );

				// check if our state machines are matching for this start
				if( childStateMachineName.compareTo( parentStateMachineName ) == 0 )
				{
					String childStartStateName = findFlattenedLeafStateNameInStateMachine( childStateMachine, childStart.getStateName() );
					
					if( childStartStateName == null )
					{
						results.add( new ValidatorResult( "Start state name not valid for state machine: " + childStateMachine.getName(), successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
						return;
					}
					
					if( !childStartStateName.startsWith( parentStartStateName ) )
					{
						results.add( new ValidatorResult( "Start state does not match inherited properties sm: " + childStateMachineName + 
								" childStartStateName: " + childStartStateName + 
								" parentStartStateName: " + parentStartStateName, successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
						return;
					}
				}
			}
		}

		return;
	}

	private void getMatchingInheritedStateMachine( org.jts.jsidl.binding.ProtocolBehavior child, String parentStateMachineName, StringBuffer successorPath )
	{
		if( child == null )
		{
			results.add( new ValidatorResult( "Invalid protocol behavior", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
			return;
		}
		else if( parentStateMachineName == null )
		{
			results.add( new ValidatorResult( "Invalid inherited state machine name", successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
			return;
		}

		for( org.jts.jsidl.binding.StateMachine childSM : child.getStateMachine() )
		{
			String childStateMachineName = getFinalStateMachineName( childSM );
			
			if( childStateMachineName.compareTo( parentStateMachineName ) == 0 )
			{
				return;
			}
		}

		// parent state machine not included in child protocol behavior
		// TODO: make sure this should be excluded as an error, does an inheriting PB need to include all state machines of parent
		//results.add( new ValidatorResult( "Inherited state machines must be included in child protocol behavior: " + parentStateMachineName, successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
		return;
	}

	private void getMatchingStateMachine( org.jts.jsidl.binding.ProtocolBehavior childPB, org.jts.jsidl.binding.StateMachine parentSM, StringBuffer successorPath )
	{
		for( org.jts.jsidl.binding.StateMachine childSM : childPB.getStateMachine() )
		{
			String childStateMachineName = getFinalStateMachineName( childSM );
			String parentStateMachineName = getFinalStateMachineName( parentSM );

			if( childStateMachineName.compareTo( parentStateMachineName ) == 0 )
			{
				// recurse through states to find matching parent states in child
				for( org.jts.jsidl.binding.State st : parentSM.getState())
				{
					getMatchingState( childSM, st, successorPath );
				}
			}
		}

		return;
	}

	private void getMatchingState( org.jts.jsidl.binding.StateMachine childSM, org.jts.jsidl.binding.State parentST, StringBuffer successorPath )
	{
		for( org.jts.jsidl.binding.State childST : childSM.getState() )
		{
			if( childST.getName().compareTo( parentST.getName() ) == 0 )
			{
				String childInitial = childST.getInitialState();
				String parentInitial = parentST.getInitialState();

				if( childInitial == null || parentInitial == null )
				{
					if( childST.getState() != null && parentST.getState() != null )
					{
						if( childST.getState().size() >= parentST.getState().size() )
						{
							// recurse through states to find matching parent states in child
							for( org.jts.jsidl.binding.State st : parentST.getState())
							{
								getMatchingState( childST, st, successorPath ) ;
							}

							return;
						}
						else
						{
							results.add( new ValidatorResult( "Inheriting state must reimplement all inherited states from parent: "+parentST.getName(), successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
							return;
						}
					}
					else
					{
						results.add( new ValidatorResult( "State initial state must match inherited state properties: "+parentST.getName(), successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
						return;
					}
				}
				else
				{
					if( childInitial.compareTo( parentInitial ) == 0 )
					{
						// recurse through states to find matching parent states in child
						for( org.jts.jsidl.binding.State st : parentST.getState())
						{
							getMatchingState( childST, st, successorPath );
						}

						return;
					}
					else
					{
						results.add( new ValidatorResult( "State initial state must match inherited state properties: "+parentST.getName(), successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
						return;
					}
				}
			}
		}

		results.add( new ValidatorResult( "State must match inherited state properties: "+parentST.getName(), successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
		return;
	}

	private void getMatchingState( org.jts.jsidl.binding.State childST, org.jts.jsidl.binding.State parentNestedST, StringBuffer successorPath )
	{
		for( org.jts.jsidl.binding.State st : childST.getState() )
		{
			if( st.getName().compareTo( parentNestedST.getName() ) == 0 )
			{
				String childInitial = st.getInitialState();
				String parentInitial = parentNestedST.getInitialState();

				if( childInitial == null || parentInitial == null )
				{
					if( st.getState() != null && parentNestedST.getState() != null )
					{
						if( st.getState().size() >= parentNestedST.getState().size() )
						{
							// recurse through states to find matching parent states in child
							for( org.jts.jsidl.binding.State state : parentNestedST.getState())
							{
								getMatchingState( st, state, successorPath );
							}

							return;
						}
						else
						{
							results.add( new ValidatorResult( "Inheriting state must reimplement all inherited states from parent: "+parentNestedST.getName(), successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
							return;
						}
					}
					else
					{
						results.add( new ValidatorResult( "State initial state must match inherited state properties: "+parentNestedST.getName(), successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
						return;
					}
				}
				else
				{
					if( childInitial.compareTo( parentInitial ) == 0 )
					{
						// recurse through states to find matching parent states in child
						for( org.jts.jsidl.binding.State state : parentNestedST.getState())
						{
							getMatchingState( st, state, successorPath );
						}

						return;
					}
					else
					{
						results.add( new ValidatorResult( "State initial state must match inherited state properties: "+parentNestedST.getName(), successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
						return;
					}
				}
			}
		}

		results.add( new ValidatorResult( "State must match inherited state properties: "+parentNestedST.getName(), successorPath.toString(), ValidatorResult.TYPE_ERROR ) );
		return;
	}

	/**
	 * Finds the statemachine with a given name within a protocol behavior, null if not found
	 * @param protocolBehavior
	 * @param stateMachineName
	 * @return
	 */
	private org.jts.jsidl.binding.StateMachine getStateMachineFromName( org.jts.jsidl.binding.ProtocolBehavior protocolBehavior, String stateMachineName )
	{
		if( protocolBehavior == null || stateMachineName == null )
		{
			return null;
		}
		
		for( org.jts.jsidl.binding.StateMachine stateMachine : protocolBehavior.getStateMachine() )
		{
			String smName = getFinalStateMachineName( stateMachine );

			if( smName != null && smName.compareTo( stateMachineName ) == 0 )
			{
				return stateMachine;
			}
		}

		return null;
	}

	/**
	 * Finds a flattened state name from root to leaf of a state in the given statemachine
	 * @param stateMachine
	 * @param stateName
	 * @return
	 */
	private String findFlattenedLeafStateNameInStateMachine( org.jts.jsidl.binding.StateMachine stateMachine, String stateName )
	{
		if( stateMachine == null || stateName == null )
		{
			return null;
		}

		org.jts.jsidl.binding.State state = org.jts.pbValidator.ValidatorUtils.getStateInStateMachine( stateMachine, stateName );

		String appendToStateName = "";

		// follow state until leaf
		while( state != null && state.getState() != null && state.getState().size() != 0 )
		{
			// state may not have an initial state if only one substate
			if( state.getState().size() == 1 )
			{
				state = state.getState().get( 0 );
			}
			else
			{
				String initialState = state.getInitialState();
				
				if( initialState == null )
				{
					return null;
				}
				
				// check if initial state condition was met since initial states
				// may not have been validated
				boolean entered = false;

				for( org.jts.jsidl.binding.State st : state.getState() )
				{
					if( st == null || st.getName() == null )
					{
						return null;
					}

					if( st.getName().compareTo( initialState ) == 0 )
					{
						state = st;
						entered = true;
					}
				}
				
				// don't continue if initial state was not found in state
				if( entered == false )
				{
					return null;
				}
			}
			
			appendToStateName += "." + state.getName();
		}
		
		String flattenedLeafStateName = stateName + appendToStateName; 

		return flattenedLeafStateName;
	}
	
	private String getFinalStateMachineName( org.jts.jsidl.binding.StateMachine stateMachine )
	{
		if( stateMachine == null || stateMachine.getName() == null )
		{
			return null;
		}
		
		String fullName = stateMachine.getName();
		
		fullName = getFinalStateMachineName( fullName );
		
		return fullName;
	}
	
	private String getFinalStateMachineName( org.jts.jsidl.binding.Start start )
	{
		if( start == null || start.getStateMachineName() == null )
		{
			return null;
		}
		
		String fullName = start.getStateMachineName();
		
		fullName = getFinalStateMachineName( fullName );
		
		return fullName;
	}
	
	private String getFinalStateMachineName( String fullName )
	{
		if( fullName == null )
		{
			return null;
		}
		
		if( fullName.indexOf( "." ) == -1 )
		{
			return fullName;
		}
		
		fullName = fullName.substring( fullName.indexOf( "." ) + 1 );
		
		return fullName;
	}
	
}
