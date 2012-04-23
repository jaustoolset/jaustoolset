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

/*
 * @(#)State.java
 * 
 * Copyright
 */
package org.jts.codegenerator.protocolBehavior;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import org.jts.codegenerator.CodeLines;


public class State {
	
	public static void generateUniqueStartState(org.jts.jsidl.binding.Start start, List<org.jts.codegenerator.protocolBehavior.StateWrapper> wrapperList, List<org.jts.codegenerator.protocolBehavior.StateWrapper> clonedUnflattenedStateWrapperList)
	{
		//** we have to create a special start to replace the start state so that entry action will be executed
		String startStateName = org.jts.codegenerator.protocolBehavior.State.getFlattenedEndStateName(org.jts.codegenerator.ProtocolBehaviorGenerator.flattenString(start.getStateName()), wrapperList);
		
		// start state leaf wrapper
		org.jts.codegenerator.protocolBehavior.StateWrapper startLeaf = org.jts.codegenerator.protocolBehavior.StateWrapper.findMatchingBottomWrapper(startStateName, clonedUnflattenedStateWrapperList);
		
		// change leaf state name
		startLeaf.parent.state.setName("____ReservedStartState____");
		
		//test
		clonedUnflattenedStateWrapperList.add(0, startLeaf);
	}
	
	public static void moveDefaultStates(org.jts.jsidl.binding.StateMachine stateMachine)
	{
		for(org.jts.jsidl.binding.State state: stateMachine.getState())
		{
			//TODO: change when a stateMachine can have a default state
			//org.jts.codegenerator.protocolBehavior.State.moveDefaultStates(state, stateMachine.getDefaultState());
			org.jts.codegenerator.protocolBehavior.State.moveDefaultStates(state, null);
		}
	}
	
	public static void moveDefaultStates(org.jts.jsidl.binding.State state, org.jts.jsidl.binding.DefaultState defaultState)
	{
	    // We need to know if this is a leaf state, e.g. it has no states within it.
	    Boolean isLeafState = (state.getState().size() == 0);	    
	    
	    // Recursive call....
		for(org.jts.jsidl.binding.State currentState: state.getState())
		{
			org.jts.codegenerator.protocolBehavior.State.moveDefaultStates(currentState, state.getDefaultState());
		}

        // set default state to the copy of defaultState so when we modify end state names, they are only for that instance (not m:1 instance)
		// (dmm) However, if this is a leaf state that contains a default state, don't overwrite transitions, merge them instead
	    // TODO: remove this section when leaf states cannot hold a default state
		if (isLeafState && (state.getDefaultState() != null))
		{
            // copy the existing default state
            org.jts.jsidl.binding.DefaultState defaultStateCopy = org.jts.codegenerator.protocolBehavior.State.cloneDefaultState(state.getDefaultState());

            // fix transitions that end in the same default state
            fixDefaultStateLoopbackTransitonEndStates(defaultStateCopy, state.getName());

            // set current state's default to modified default state
            state.setDefaultState(defaultStateCopy);

		    // Create a generic wrapper so we can use the addTransitionToState function
		    org.jts.codegenerator.protocolBehavior.GenericState target = 
					new org.jts.codegenerator.protocolBehavior.GenericState(state.getDefaultState());
		    
		    // merge transitions, if they exist
		    if (defaultState != null)
		    {
		    	// default state transitions
		    	if(defaultState.getTransition() != null)
		    	{
		    		for (org.jts.jsidl.binding.Transition transition: defaultState.getTransition())
		    		{
		    			org.jts.codegenerator.protocolBehavior.Transition.addTransitionToState(transition, target);
		    		}
		    	}
		    	
		    	// default state default transitions
		    	if(defaultState.getDefaultTransition() != null)
		    	{
		    		for (org.jts.jsidl.binding.DefaultTransition defaultTransition: defaultState.getDefaultTransition())
		    		{
		    			org.jts.codegenerator.protocolBehavior.Transition.addTransitionToState(defaultTransition, target);
		    		}
		    	}
		    }
		}
        else
        {
            // copy the existing default state
            org.jts.jsidl.binding.DefaultState defaultStateCopy = org.jts.codegenerator.protocolBehavior.State.cloneDefaultState(defaultState);

            // fix transitions that end in the same default state
            fixDefaultStateLoopbackTransitonEndStates(defaultStateCopy, state.getName());

            // set current state's default to modified default state
            state.setDefaultState(defaultStateCopy);
        }
	}

        private static void fixDefaultStateLoopbackTransitonEndStates(org.jts.jsidl.binding.DefaultState defaultState, String stateName)
        {
            if(defaultState == null)
            {
                return;
            }
            
            // for the case of a loopback transition within a default state
            // we must change the end state of the transition to the current state
            // this can be done simply by changing it to <simple/>
            if(defaultState.getTransition() != null)
            {
                for(org.jts.jsidl.binding.Transition transition:defaultState.getTransition())
                {
                    if(transition.getSimple() != null &&
                    		transition.getSimple().getEndState() != null &&
                            transition.getSimple().getEndState().getState() != null &&
                            transition.getSimple().getEndState().getState().endsWith("default_state"))
                    {
                        // make new end state with target of current state
                        org.jts.jsidl.binding.EndState endState = new org.jts.jsidl.binding.EndState();
                        endState.setState(stateName);

                        transition.getSimple().setEndState(endState);
                    }
                    else if(transition.getPush() != null &&
                            transition.getPush().getEndState() != null &&
                            transition.getPush().getEndState().getState() != null &&
                            transition.getPush().getEndState().getState().endsWith("default_state"))
                    {
                        // make new end state with target of current state
                        org.jts.jsidl.binding.EndState endState = new org.jts.jsidl.binding.EndState();
                        endState.setState(stateName);

                        transition.getPush().setEndState(endState);
                    }
                }
            }

            // same for default transitions
            if(defaultState.getDefaultTransition() != null)
            {
                for(org.jts.jsidl.binding.DefaultTransition defaultTransition:defaultState.getDefaultTransition())
                {
                    if(defaultTransition.getSimple() != null &&
                            defaultTransition.getSimple().getEndState() != null &&
                            defaultTransition.getSimple().getEndState().getState() != null &&
                            defaultTransition.getSimple().getEndState().getState().endsWith("default_state"))
                    {
                        // make new end state with target of current state
                        org.jts.jsidl.binding.EndState endState = new org.jts.jsidl.binding.EndState();
                        endState.setState(stateName);

                        defaultTransition.getSimple().setEndState(endState);
                    }
                    else if(defaultTransition.getPush() != null &&
                            defaultTransition.getPush().getEndState() != null &&
                            defaultTransition.getPush().getEndState().getState() != null &&
                            defaultTransition.getPush().getEndState().getState().endsWith("default_state"))
                    {
                        // make new end state with target of current state
                        org.jts.jsidl.binding.EndState endState = new org.jts.jsidl.binding.EndState();
                        endState.setState(stateName);

                        defaultTransition.getPush().setEndState(endState);
                    }
                }
            }

        }
	
	/**
	 * Changes the initial state of non-nested states to the full state name
	 * @param state
	 * @param parentName
	 */
	public static void fixInitialStateNames(org.jts.jsidl.binding.State state, String parentName)
	{
		// check for nested states in current state
		if((state.getInitialState() != null) && !state.getInitialState().isEmpty())
		{
			state.setInitialState(parentName + "." + state.getInitialState());

			// change start states for all nested states as well
			for(int i = 0; i < state.getState().size(); i++)
			{
				fixInitialStateNames(state.getState().get(i), parentName + "." + state.getState().get(i).getName());
			}
		}	 
	}
	
	/**
	 * Overridden function for convenience 
	 * @param stateMachine
	 */
	public static void fixInitialStateNames(org.jts.jsidl.binding.StateMachine stateMachine)
	{
		for(int i = 0; i < stateMachine.getState().size(); i++)
		{
			org.jts.codegenerator.protocolBehavior.State.fixInitialStateNames(stateMachine.getState().get(i), stateMachine.getState().get(i).getName());
		}
	}
	
	/**
	 * Changes the state names to the full state name that has all parent names in it
	 * @param state
	 * @param parentName
	 */
	public static void fixStateNames(org.jts.jsidl.binding.State state, String lastStateName)
	{
		String currentStateName = state.getName();
		
		// set to parent name when its not the same as state name
		// (skips top level states)
		if(lastStateName.compareTo(currentStateName) != 0)
		{
			state.setName(lastStateName);
		}
		
		if(state.getState() != null)
		{
			for(int i = 0; i < state.getState().size(); i++)
			{
				fixStateNames(state.getState().get(i), lastStateName + "." + state.getState().get(i).getName());
			}
		}
	}
	
	/**
	 * Overridden function for convenience
	 * @param stateMachine
	 */
	public static void fixStateNames(org.jts.jsidl.binding.StateMachine stateMachine)
	{
		for(int i = 0; i < stateMachine.getState().size(); i++)
		{
			org.jts.codegenerator.protocolBehavior.State.fixStateNames(stateMachine.getState().get(i), stateMachine.getState().get(i).getName());
		}
	}
	
	/**
	 * Goes through all end states for all transition types in the current state
	 * and changes the end state to the full state name
	 * We can always search for the name of the end state in the parentName because 
	 * the end state will always have a valid parent in at least one of the top levels of its name
	 * @param currentState
	 * @param parentName
	 */
	public static void fixEndStateNames(org.jts.jsidl.binding.State currentState, org.jts.jsidl.binding.State parentState, String parentName)
	{
		org.jts.jsidl.binding.EndState endState = null;
		
		// change all end state names in current state
		if(currentState.getTransition() != null)
		{
			for(int i = 0; i < currentState.getTransition().size(); i++)
			{
				org.jts.jsidl.binding.Transition currentTransition = currentState.getTransition().get(i);
				
				if(currentTransition.getSimple() != null)
				{
					if(currentTransition.getSimple().getEndState() != null)
					{
						endState = currentTransition.getSimple().getEndState();
						
					}
					else
					{
						endState = new org.jts.jsidl.binding.EndState();
						currentTransition.getSimple().setEndState(endState);
						endState.setState(currentState.getName());
					}

					fixEndStateNameFromOriginal(endState, currentState, parentState, parentName);
				}
				
				if(currentTransition.getPush() != null)
				{
					endState = currentTransition.getPush().getEndState();
					fixEndStateNameFromOriginal(endState, currentState, parentState, parentName);
					
					if(currentTransition.getPush().getSimple() != null)
					{
						if(currentTransition.getPush().getSimple().getEndState() != null)
						{
							endState = currentTransition.getPush().getSimple().getEndState();
						}
						else
						{
							endState = new org.jts.jsidl.binding.EndState();
							currentTransition.getPush().getSimple().setEndState(endState);
							endState.setState(currentState.getName());
						}
						
						fixEndStateNameFromOriginal(endState, currentState, parentState, parentName);
					}
				}
			}
		}
		
		if(currentState.getDefaultTransition() != null)
		{
			for(int i = 0; i < currentState.getDefaultTransition().size(); i++)
			{
				org.jts.jsidl.binding.DefaultTransition currentDefaultTransition = currentState.getDefaultTransition().get(i);
				
				if(currentDefaultTransition.getSimple() != null)
				{
					if(currentDefaultTransition.getSimple().getEndState() != null)
					{
						endState = currentDefaultTransition.getSimple().getEndState();
					}
					else
					{
						endState = new org.jts.jsidl.binding.EndState();
						currentDefaultTransition.getSimple().setEndState(endState);
						endState.setState(currentState.getName());
					}

					fixEndStateNameFromOriginal(endState, currentState, parentState, parentName);
				}
				
				if(currentDefaultTransition.getPush() != null)
				{
					endState = currentDefaultTransition.getPush().getEndState();
					fixEndStateNameFromOriginal(endState, currentState, parentState, parentName);
					
					if(currentDefaultTransition.getPush().getSimple() != null)
					{
						if(currentDefaultTransition.getPush().getSimple().getEndState() != null)
						{
							endState = currentDefaultTransition.getPush().getSimple().getEndState();
						}
						else
						{
							endState = new org.jts.jsidl.binding.EndState();
							currentDefaultTransition.getPush().getSimple().setEndState(endState);
							endState.setState(currentState.getName());
						}
						
						fixEndStateNameFromOriginal(endState, currentState, parentState, parentName);
					}
				}
			}
		}
		
		// change end states for for everything in the default state of the state
		// this default state is actually only applicable for the states within the current state (at the same nesting level)
		if(currentState.getDefaultState() != null)
		{
			if(currentState.getDefaultState().getTransition() != null)
			{
				for(int i = 0; i < currentState.getDefaultState().getTransition().size(); i++)
				{
					org.jts.jsidl.binding.Transition currentTransition =currentState.getDefaultState().getTransition().get(i);
					
					if(currentTransition.getSimple() != null)
					{
						if(currentTransition.getSimple().getEndState() != null)
						{
							endState = currentTransition.getSimple().getEndState();
						}
						else
						{
							endState = new org.jts.jsidl.binding.EndState();
							currentTransition.getSimple().setEndState(endState);
							endState.setState(currentState.getName());
						}

						fixEndStateNameFromOriginal(endState, currentState, parentState, parentName);
					}
					
					if(currentTransition.getPush() != null)
					{
						endState = currentTransition.getPush().getEndState();
						fixEndStateNameFromOriginal(endState, currentState, parentState, parentName);
						
						if(currentTransition.getPush().getSimple() != null)
						{
							if(currentTransition.getPush().getSimple().getEndState() != null)
							{
								endState = currentTransition.getPush().getSimple().getEndState();
							}
							else
							{
								endState = new org.jts.jsidl.binding.EndState();
								currentTransition.getPush().getSimple().setEndState(endState);
								endState.setState(currentState.getName());
							}
							fixEndStateNameFromOriginal(endState, currentState, parentState, parentName);
						}
					}
				}
			}
			
			if(currentState.getDefaultState().getDefaultTransition() != null)
			{
				for(int i = 0; i < currentState.getDefaultState().getDefaultTransition().size(); i++)
				{
					org.jts.jsidl.binding.DefaultTransition currentDefaultTransition = currentState.getDefaultState().getDefaultTransition().get(i);
					
					if(currentDefaultTransition.getSimple() != null)
					{
						if(currentDefaultTransition.getSimple().getEndState() != null)
						{
							endState = currentDefaultTransition.getSimple().getEndState();
						}
						else
						{
							endState = new org.jts.jsidl.binding.EndState();
							currentDefaultTransition.getSimple().setEndState(endState);
							endState.setState(currentState.getName());
						}

						fixEndStateNameFromOriginal(endState, currentState, parentState, parentName);
					}
					
					if(currentDefaultTransition.getPush() != null)
					{
						endState = currentDefaultTransition.getPush().getEndState();
						fixEndStateNameFromOriginal(endState, currentState, parentState, parentName);
						
						if(currentDefaultTransition.getPush().getSimple() != null)
						{
							if(currentDefaultTransition.getPush().getSimple().getEndState() != null)
							{
								endState = currentDefaultTransition.getPush().getSimple().getEndState();
							}
							else
							{
								endState = new org.jts.jsidl.binding.EndState();
								currentDefaultTransition.getPush().getSimple().setEndState(endState);
								endState.setState(currentState.getName());
							}
							
							fixEndStateNameFromOriginal(endState, currentState, parentState, parentName);
						}
					}
				}
			}
		}
		
		// change end state names for all nested states
		if(currentState.getState() != null)
		{
			for(int i = 0; i < currentState.getState().size(); i++)
			{
				fixEndStateNames(currentState.getState().get(i), currentState, currentState.getState().get(i).getName());
			}
		}
		
	}
	
	/**
	 * Overridden function for convenience
	 * @param stateMachine
	 */
	public static void fixEndStateNames(org.jts.jsidl.binding.StateMachine stateMachine)
	{
		for(int i = 0; i < stateMachine.getState().size(); i++)
		{
			//org.jts.codegenerator.protocolBehavior.State.fixEndStateNames(stateMachine.getState().get(i), null, stateMachine.getState().get(i).getName());
			// NOTE: removed because end states are now defined as having to be fully qualified
			fixEndStateNames( stateMachine.getState().get(i), stateMachine );
		}
	}
	
	/**
	 * Goes through all end states for all transition types in the current state
	 * and changes the end state to the full state name
	 * We can always search for the name of the end state in the parentName because 
	 * the end state will always have a valid parent in at least one of the top levels of its name
	 * @param currentState
	 * @param parentName
	 */
	public static void fixEndStateNames( org.jts.jsidl.binding.State currentState, org.jts.jsidl.binding.StateMachine stateMachine )
	{
		org.jts.jsidl.binding.EndState endState = null;
		
		// change all end state names in current state
		if(currentState.getTransition() != null)
		{
			for(int i = 0; i < currentState.getTransition().size(); i++)
			{
				org.jts.jsidl.binding.Transition currentTransition = currentState.getTransition().get(i);
				
				if(currentTransition.getSimple() != null)
				{
					if(currentTransition.getSimple().getEndState() != null)
					{
						endState = currentTransition.getSimple().getEndState();
						
					}
					else
					{
						endState = new org.jts.jsidl.binding.EndState();
						currentTransition.getSimple().setEndState(endState);
						endState.setState(currentState.getName());
					}

					fixEndStateNameFromOriginal( endState, stateMachine );
				}
				
				if(currentTransition.getPush() != null)
				{
					endState = currentTransition.getPush().getEndState();
					fixEndStateNameFromOriginal( endState, stateMachine );
					
					if(currentTransition.getPush().getSimple() != null)
					{
						if(currentTransition.getPush().getSimple().getEndState() != null)
						{
							endState = currentTransition.getPush().getSimple().getEndState();
						}
						else
						{
							endState = new org.jts.jsidl.binding.EndState();
							currentTransition.getPush().getSimple().setEndState(endState);
							endState.setState(currentState.getName());
						}
						
						fixEndStateNameFromOriginal( endState, stateMachine );
					}
				}
			}
		}
		
		if(currentState.getDefaultTransition() != null)
		{
			for(int i = 0; i < currentState.getDefaultTransition().size(); i++)
			{
				org.jts.jsidl.binding.DefaultTransition currentDefaultTransition = currentState.getDefaultTransition().get(i);
				
				if(currentDefaultTransition.getSimple() != null)
				{
					if(currentDefaultTransition.getSimple().getEndState() != null)
					{
						endState = currentDefaultTransition.getSimple().getEndState();
					}
					else
					{
						endState = new org.jts.jsidl.binding.EndState();
						currentDefaultTransition.getSimple().setEndState(endState);
						endState.setState(currentState.getName());
					}

					fixEndStateNameFromOriginal( endState, stateMachine );
				}
				
				if(currentDefaultTransition.getPush() != null)
				{
					endState = currentDefaultTransition.getPush().getEndState();
					fixEndStateNameFromOriginal( endState, stateMachine );
					
					if(currentDefaultTransition.getPush().getSimple() != null)
					{
						if(currentDefaultTransition.getPush().getSimple().getEndState() != null)
						{
							endState = currentDefaultTransition.getPush().getSimple().getEndState();
						}
						else
						{
							endState = new org.jts.jsidl.binding.EndState();
							currentDefaultTransition.getPush().getSimple().setEndState(endState);
							endState.setState(currentState.getName());
						}
						
						fixEndStateNameFromOriginal( endState, stateMachine );
					}
				}
			}
		}
		
		// change end states for for everything in the default state of the state
		// this default state is actually only applicable for the states within the current state (at the same nesting level)
		if(currentState.getDefaultState() != null)
		{
			if(currentState.getDefaultState().getTransition() != null)
			{
				for(int i = 0; i < currentState.getDefaultState().getTransition().size(); i++)
				{
					org.jts.jsidl.binding.Transition currentTransition =currentState.getDefaultState().getTransition().get(i);
					
					if(currentTransition.getSimple() != null)
					{
						if(currentTransition.getSimple().getEndState() != null)
						{
							endState = currentTransition.getSimple().getEndState();
						}
						else
						{
							endState = new org.jts.jsidl.binding.EndState();
							currentTransition.getSimple().setEndState(endState);
							endState.setState(currentState.getName());
						}

						fixEndStateNameFromOriginal( endState, stateMachine );
					}
					
					if(currentTransition.getPush() != null)
					{
						endState = currentTransition.getPush().getEndState();
						fixEndStateNameFromOriginal( endState, stateMachine );
						
						if(currentTransition.getPush().getSimple() != null)
						{
							if(currentTransition.getPush().getSimple().getEndState() != null)
							{
								endState = currentTransition.getPush().getSimple().getEndState();
							}
							else
							{
								endState = new org.jts.jsidl.binding.EndState();
								currentTransition.getPush().getSimple().setEndState(endState);
								endState.setState(currentState.getName());
							}
							fixEndStateNameFromOriginal( endState, stateMachine );
						}
					}
				}
			}
			
			if(currentState.getDefaultState().getDefaultTransition() != null)
			{
				for(int i = 0; i < currentState.getDefaultState().getDefaultTransition().size(); i++)
				{
					org.jts.jsidl.binding.DefaultTransition currentDefaultTransition = currentState.getDefaultState().getDefaultTransition().get(i);
					
					if(currentDefaultTransition.getSimple() != null)
					{
						if(currentDefaultTransition.getSimple().getEndState() != null)
						{
							endState = currentDefaultTransition.getSimple().getEndState();
						}
						else
						{
							endState = new org.jts.jsidl.binding.EndState();
							currentDefaultTransition.getSimple().setEndState(endState);
							endState.setState(currentState.getName());
						}

						fixEndStateNameFromOriginal( endState, stateMachine );
					}
					
					if(currentDefaultTransition.getPush() != null)
					{
						endState = currentDefaultTransition.getPush().getEndState();
						fixEndStateNameFromOriginal( endState, stateMachine );
						
						if(currentDefaultTransition.getPush().getSimple() != null)
						{
							if(currentDefaultTransition.getPush().getSimple().getEndState() != null)
							{
								endState = currentDefaultTransition.getPush().getSimple().getEndState();
							}
							else
							{
								endState = new org.jts.jsidl.binding.EndState();
								currentDefaultTransition.getPush().getSimple().setEndState(endState);
								endState.setState(currentState.getName());
							}
							
							fixEndStateNameFromOriginal( endState, stateMachine );
						}
					}
				}
			}
		}
		
		// change end state names for all nested states
		if(currentState.getState() != null)
		{
			for(int i = 0; i < currentState.getState().size(); i++)
			{
				fixEndStateNames( currentState.getState().get(i), stateMachine );
			}
		}
		
	}
	
	/**
	 * This function replaces the end state name with the fully qualified state name
	 * NOTE: expects fully qualified name or a beginning of a fully qualified name
	 * @param endState
	 * @param stateMachine
	 */
	public static void fixEndStateNameFromOriginal( org.jts.jsidl.binding.EndState endState, org.jts.jsidl.binding.StateMachine stateMachine )
	{
		String fullEndStateName = findFullStateNameToLeaf( endState.getState(), stateMachine ); 
		endState.setState( fullEndStateName );
	}
	
	public static String findFullStateNameToLeaf( String stateName, org.jts.jsidl.binding.StateMachine stateMachine )
	{	
		// ERROR, we must have all of these to complete
		if( stateName == null || stateMachine == null || stateMachine.getState() == null )
		{
			return null;
		}
		
		String[] parts = stateName.split( "\\." );
		int arrayIndex = 0;
		org.jts.jsidl.binding.State currentState = null;
		
		// ERROR, check split size
		if( parts.length == 0 )
		{
			return null;
		}
		
		// find first state from the state machine
		for( int i = 0; i < stateMachine.getState().size(); i++ )
		{
			org.jts.jsidl.binding.State st = stateMachine.getState().get( i );
			
			if( parts[ arrayIndex ].compareTo( st.getName() ) == 0 )
			{
				currentState = st;
				break;
			}
		}
		
		// ERROR, state was not found
		if( currentState == null )
		{
			return null;
		}

		// loop through states until split list is finished
		do
		{
			String currentStateName = parts[ arrayIndex++ ];
			
			for( int i = 0; i < currentState.getState().size(); i++ )
			{
				org.jts.jsidl.binding.State st = currentState.getState().get( i );
				
				if( currentStateName.compareTo( st.getName() ) == 0 )
				{
					currentState = st;
					break;
				}
			}
			
			// stop loop if split is finished
			if( arrayIndex == parts.length )
			{
				break;
			}
		}
		while( currentState != null );
		
		// we should now have the state, have to follow initial states until a leaf
		while( currentState.getState() != null && currentState.getState().size() > 0 )
		{
			String initialState = currentState.getInitialState();
			
			for( int i = 0; i < currentState.getState().size(); i++ )
			{
				org.jts.jsidl.binding.State st = currentState.getState().get( i );
				
				// no initial state needed when only one substate
				if( initialState == null )
				{
					currentState = st;
					stateName += "." + st.getName();
					break;
				}
				
				if( initialState.compareTo( st.getName() ) == 0 )
				{
					currentState = st;
					stateName += "." + st.getName();
					break;
				}
			}
		}
		
		
		return stateName;		
	}
	
	/** 
	 * This function finds the full end state name by dealing with qualified and unqualified state name combinations
	 * and changes the endState to it's full name (NOTE: this function doesn't find the leaf end State, only the full
	 * name from it's top level to the end state)
	 * @param endState
	 * @param currentState
	 * @param parentState
	 * @param parentName
	 * @param endStateName
	 */
	public static void fixEndStateNameFromOriginal(org.jts.jsidl.binding.EndState endState, org.jts.jsidl.binding.State currentState, org.jts.jsidl.binding.State parentState, String parentName)
	{
		// transition could have been a loopback transition
		if(endState != null)
		{
			if(endState.getState() != null)
			{
				String endStateName = endState.getState();
				
				endStateName = endStateName.replaceAll("\\.", "_");
				
				String returnName = null;
				String endStateHead = null;
				String endStateTail = null;
				
				String bottomLevelParentName = null;
				String parentNameWithoutLastState = null;
				String parentPopped = null;
				
				// remove last state off of parent
				if(parentName.indexOf("_") != -1)
				{
					parentNameWithoutLastState = parentName.substring(0, parentName.lastIndexOf("_"));
				}
				else
				{
					parentNameWithoutLastState = parentName;
				}
				
				// extract head and tail of endState string
				// A_B_C: head A, tail B_C
				if(endStateName.indexOf("_") != -1)
				{
					endStateHead = endStateName.substring(0, endStateName.indexOf("_")); 
					endStateTail = endStateName.substring(endStateName.indexOf("_")+1, endStateName.length());
				}
				else
				{
					endStateHead = endStateName;
					endStateTail = endStateName.substring(endStateName.indexOf("_")+1, endStateName.length());
				}
				
				//******************************
				// ENDSTATE IS SIBILING CHECK
				// check endStatehead for match to sibling of current state
				//******************************
				if(parentState != null && parentState.getState() != null)
				{
					for(int i = 0; i < parentState.getState().size(); i++)
					{
						String currentStateSiblingName = parentState.getState().get(i).getName();
						
						bottomLevelParentName = currentStateSiblingName.substring(currentStateSiblingName.lastIndexOf("_")+1, currentStateSiblingName.length());
						
						// strange case where if you are in A_AA and call for end state AA.AAA
						if(endStateHead.compareTo(bottomLevelParentName) == 0)
						{
							returnName = parentNameWithoutLastState + "_" + endStateName;
							
							endState.setState(returnName);
							return;
						}
	
						if(bottomLevelParentName.compareTo(endStateHead) == 0)
						{
							returnName = parentNameWithoutLastState + "_" + endStateTail;
							
							endState.setState(returnName);
							return;
						}
					}
				}
				//******************************
				// if top level there will not be a parent State
				//******************************
				else
				{
					for(int i = 0; i < currentState.getState().size(); i++)
					{
						String currentStateSiblingName = currentState.getState().get(i).getName();
						
						bottomLevelParentName = currentStateSiblingName.substring(currentStateSiblingName.lastIndexOf("_")+1, currentStateSiblingName.length());
	
						if(bottomLevelParentName.compareTo(endStateHead) == 0)
						{
							returnName = parentNameWithoutLastState + "_" + endStateName;
						
							endState.setState(returnName);
							return;
						}
					}
				}
				
				//******************************
				// ENDSTATE PART OF PARENTNAME
				// traverse up parentName and check for matching of toplevel endState 		
				// check to see if parentName has nested states
				//******************************
				while(parentName.indexOf("_") != -1)
				{
					// get bottom of parentName
					parentPopped = parentName.substring(parentName.lastIndexOf("_")+1, parentName.length());
					
					// pop off bottom of parentName
					parentName = parentName.substring(0, parentName.lastIndexOf("_"));
					
					if(parentPopped.compareTo(endStateHead) == 0)
					{
						returnName = parentName + "_" + endStateName;

						endState.setState(returnName);
						return;
					}
				}
				
				returnName = endStateName;
				
				endState.setState(returnName);
				return;
			}
		}
	}
	
	/**
	 * Goes through all the transitions of a state and it's sub-states and appends 'Transition' to it's transition's names
	 * @param state
	 */
	public static void fixTransitionNames(org.jts.jsidl.binding.State state)
	{
		// change state transitions
		if(state.getTransition() != null)
		{
			for(int i = 0; i < state.getTransition().size(); i++)
			{
				org.jts.jsidl.binding.Transition transition = state.getTransition().get(i);
				
				org.jts.codegenerator.protocolBehavior.Transition.appendTransitionName(transition);
			}
		}
		
		// change state default transitions 
		if(state.getDefaultTransition() != null)
		{
			for(int i = 0; i < state.getDefaultTransition().size(); i++)
			{
				org.jts.jsidl.binding.DefaultTransition defaultTransition = state.getDefaultTransition().get(i);
				
				org.jts.codegenerator.protocolBehavior.Transition.appendTransitionName(defaultTransition);
			}
		}
		
		// change default state transitions
		if(state.getDefaultState() != null)
		{
			// change default state transitions
			if(state.getDefaultState().getTransition() != null)
			{
				for(int i = 0; i < state.getDefaultState().getTransition().size(); i++)
				{
					org.jts.jsidl.binding.Transition transition = state.getDefaultState().getTransition().get(i);
					
					org.jts.codegenerator.protocolBehavior.Transition.appendTransitionName(transition);
				}
			}
			
			// change default state default transitions 
			if(state.getDefaultState().getDefaultTransition() != null)
			{
				for(int i = 0; i < state.getDefaultState().getDefaultTransition().size(); i++)
				{
					org.jts.jsidl.binding.DefaultTransition defaultTransition = state.getDefaultState().getDefaultTransition().get(i);
					
					org.jts.codegenerator.protocolBehavior.Transition.appendTransitionName(defaultTransition);
				}
			}
		}
		
		for(int i = 0; i < state.getState().size(); i++)
		{
			fixTransitionNames(state.getState().get(i));
		}
	}
	
	/**
	 * Overridden function for convenience
	 * @param stateMachine
	 */
	public static void fixTransitionNames(org.jts.jsidl.binding.StateMachine stateMachine)
	{
		for(int i = 0; i < stateMachine.getState().size(); i++)
		{
			org.jts.codegenerator.protocolBehavior.State.fixTransitionNames(stateMachine.getState().get(i));
		}
	}
	
	/**
	 * Goes through all the actions and send actions in a state and it's sub-states and appends 'Action' or 'SendAction' to their names
	 * @param state
	 */
	public static void fixActionSendActionNames(org.jts.jsidl.binding.State state)
	{
		// change entry action names
		if(state.getEntry() != null)
		{
			for(int i = 0; i < state.getEntry().getActionOrSendAction().size(); i++)
			{
				Object actionOrSendAction = state.getEntry().getActionOrSendAction().get(i);
				
				org.jts.codegenerator.protocolBehavior.Action.appendActionOrSendActionName(actionOrSendAction);
			}
		}
		
		// change exit action names
		if(state.getExit() != null)
		{
			for(int i = 0; i < state.getExit().getActionOrSendAction().size(); i++)
			{
				Object actionOrSendAction = state.getExit().getActionOrSendAction().get(i);
	
				org.jts.codegenerator.protocolBehavior.Action.appendActionOrSendActionName(actionOrSendAction);
			}
		}
		
		// change transition action names
		if(state.getTransition() != null)
		{
			for(int i = 0; i < state.getTransition().size(); i++)
			{
				org.jts.jsidl.binding.Transition transition = state.getTransition().get(i);
				
				for(int j = 0; j < transition.getActionOrSendAction().size(); j++)
				{
					Object actionOrSendAction = transition.getActionOrSendAction().get(j);
	
					org.jts.codegenerator.protocolBehavior.Action.appendActionOrSendActionName(actionOrSendAction);
				}
			}
		}
		
		// change default transition action names
		if(state.getDefaultTransition() != null)
		{
			for(int i = 0; i < state.getDefaultTransition().size(); i++)
			{
				org.jts.jsidl.binding.DefaultTransition defaultTransition = state.getDefaultTransition().get(i);
				
				for(int j = 0; j < defaultTransition.getActionOrSendAction().size(); j++)
				{
					Object actionOrSendAction = defaultTransition.getActionOrSendAction().get(j);
	
					org.jts.codegenerator.protocolBehavior.Action.appendActionOrSendActionName(actionOrSendAction);
				}				
			}
		}
		
		// change default state action names
		if(state.getDefaultState() != null)
		{
			// change transition action names
			if(state.getDefaultState().getTransition() != null)
			{
				for(int i = 0; i < state.getDefaultState().getTransition().size(); i++)
				{
					org.jts.jsidl.binding.Transition transition = state.getDefaultState().getTransition().get(i);
					
					for(int j = 0; j < transition.getActionOrSendAction().size(); j++)
					{
						Object actionOrSendAction = transition.getActionOrSendAction().get(j);
		
						org.jts.codegenerator.protocolBehavior.Action.appendActionOrSendActionName(actionOrSendAction);
					}
				}
			}
			
			// change default transition action names
			if(state.getDefaultState().getDefaultTransition() != null)
			{
				for(int i = 0; i < state.getDefaultState().getDefaultTransition().size(); i++)
				{
					org.jts.jsidl.binding.DefaultTransition defaultTransition = state.getDefaultState().getDefaultTransition().get(i);
					
					for(int j = 0; j < defaultTransition.getActionOrSendAction().size(); j++)
					{
						Object actionOrSendAction = defaultTransition.getActionOrSendAction().get(j);
		
						org.jts.codegenerator.protocolBehavior.Action.appendActionOrSendActionName(actionOrSendAction);
					}				
				}
			}
		}
		
		if(state.getState() != null)
		{
			for(int i = 0; i < state.getState().size(); i++)
			{
				fixActionSendActionNames(state.getState().get(i));
			}
		}
	}
	
	/**
	 * Overridden function for convenience
	 * @param stateMachine
	 */
	public static void fixActionSendActionNames(org.jts.jsidl.binding.StateMachine stateMachine)
	{
		for(int i = 0; i < stateMachine.getState().size(); i++)
		{
			org.jts.codegenerator.protocolBehavior.State.fixActionSendActionNames(stateMachine.getState().get(i));
		}
	}
	
	/**
	 * Takes a state wrapper and adds to a list all flattened states of the FSM
	 * generateFlattenedStates calls itself if there are initial states(nested states) for the current state
	 * otherwise it flattens the leaf state
	 * @param stateW
	 */
	public static void generateStateWrapperList(org.jts.codegenerator.protocolBehavior.StateWrapper stateW, List<org.jts.codegenerator.protocolBehavior.StateWrapper> wrapperList) 
	{
		// see if there are nested states within the current state
		if((stateW.state.getInitialState() != null) && !stateW.state.getInitialState().isEmpty())
		{
			for(int i =0; i < stateW.state.getState().size(); i++)
			{
				org.jts.codegenerator.protocolBehavior.State.generateStateWrapperList(new org.jts.codegenerator.protocolBehavior.StateWrapper(stateW.state.getState().get(i), stateW, null), wrapperList);
			}
		}
		// otherwise the state is a leaf and we append it to the wrapper list
		else
		{
			wrapperList.add(stateW);
		}
	}
	
	/**
	 * Overridden function for convenience
	 * @param stateMachine
	 */
	public static void generateStateWrapperList(org.jts.jsidl.binding.StateMachine stateMachine, List<org.jts.codegenerator.protocolBehavior.StateWrapper> wrapperList)
	{
		for(int i = 0; i < stateMachine.getState().size(); i++)
		{
			org.jts.codegenerator.protocolBehavior.StateWrapper stateW = new org.jts.codegenerator.protocolBehavior.StateWrapper(stateMachine.getState().get(i), null, null);
			
			org.jts.codegenerator.protocolBehavior.State.generateStateWrapperList(stateW, wrapperList);
		}
	}
	
	/**
	 * Creates a flattened state based on a collection of data stored in a state wrapper
	 * @param stateW
	 * @return
	 */
	public static org.jts.jsidl.binding.State flattenStateWrapper(org.jts.codegenerator.protocolBehavior.StateWrapper stateW)
	{
		stateW = org.jts.codegenerator.protocolBehavior.StateWrapper.goToBottom(stateW);
		
		// set returned state as nested state
		org.jts.jsidl.binding.State state = new org.jts.jsidl.binding.State();	
		
		state = org.jts.codegenerator.protocolBehavior.State.cloneState(stateW.state);
				
		// push nested state's default state transitions and default state default transitions to return state
		org.jts.codegenerator.protocolBehavior.State.flattenLeafState(state);
		
		while(stateW.parent != null)
		{
			org.jts.jsidl.binding.State parentState = new org.jts.jsidl.binding.State();
			
			// make copy of parent state
			parentState = org.jts.codegenerator.protocolBehavior.State.cloneState(stateW.parent.state);
			
			// order of flattening is:
			// state transitions
			// default state transitions
			// state default transitions
			// default state default transitions
		
			// add parent state transitions to child state
			if(parentState.getTransition() != null)
			{
				for(int i = 0; i < parentState.getTransition().size(); i++)
				{
					org.jts.jsidl.binding.Transition transition = parentState.getTransition().get(i);
					
					//
					// Don't add parent InternalStateChanges, as that was already handled in the service
					// inheritence code.
					//
					if (!transition.getName().startsWith("InternalStateChange_To_"))
					org.jts.codegenerator.protocolBehavior.Transition.addTransitionToState(transition, new GenericState(state));
				}
			}
			
			// add transitions from parent default states (to the end of state's list)
			if(parentState.getDefaultState() != null && parentState.getDefaultState().getTransition() != null)
			{
				for(int i = 0; i < parentState.getDefaultState().getTransition().size(); i++)
				{
					org.jts.jsidl.binding.Transition transition = parentState.getDefaultState().getTransition().get(i);

					org.jts.codegenerator.protocolBehavior.Transition.addTransitionToState(transition, new GenericState(state));
				}
			}
			
			// add parent state default transitions to child state
			if(parentState.getDefaultTransition() != null)
			{
				for(int i = 0; i < parentState.getDefaultTransition().size(); i++)
				{
					org.jts.jsidl.binding.DefaultTransition defaultTransition = parentState.getDefaultTransition().get(i);

					org.jts.codegenerator.protocolBehavior.Transition.addTransitionToState(defaultTransition, new GenericState(state));
				}
			}
			
			// add default state default transitions from parent state (to the end of state's list)
			if(parentState.getDefaultState() != null && parentState.getDefaultState().getDefaultTransition() != null)
			{
				for(int i = 0; i < parentState.getDefaultState().getDefaultTransition().size(); i++)
				{
					org.jts.jsidl.binding.DefaultTransition defaultTransition = parentState.getDefaultState().getDefaultTransition().get(i);

					org.jts.codegenerator.protocolBehavior.Transition.addTransitionToState(defaultTransition, new GenericState(state));
				}
			}
			
			// climb up one level of state wrapper
			stateW = stateW.parent;
		}
		
		return state;
	}
	
	/**
	 * Takes a set of unflattened state wrapper in a list, flattens each one and places all the results in another list
	 * @param flattenedStateList
	 * @param clonedUnflattenedStateWrapperList
	 */
	public static void flattenStateWrappersInList(List<org.jts.jsidl.binding.State> flattenedStateList, List<org.jts.codegenerator.protocolBehavior.StateWrapper> clonedUnflattenedStateWrapperList)
	{
		Iterator<org.jts.codegenerator.protocolBehavior.StateWrapper> stateWrapperIterator = clonedUnflattenedStateWrapperList.iterator();

		// iterate through each state wrapper to add entry actions onto every applicable transition
		while(stateWrapperIterator.hasNext())
		{
			// get next state wrapper instance of the iterator
			org.jts.codegenerator.protocolBehavior.StateWrapper stateW = stateWrapperIterator.next();

			org.jts.jsidl.binding.State flattenedState = org.jts.codegenerator.protocolBehavior.State.flattenStateWrapper(stateW);

			flattenedStateList.add(flattenedState);
		}
	}
	
	/**
	 * Flattens a nested state (a state with no nested states in it) by moving 
	 * default state transitions and default state default transitions into 
	 * the current state
	 * @param state
	 * @return
	 */
	public static void flattenLeafState(org.jts.jsidl.binding.State state)
	{
		if(state.getDefaultState() != null)
		{
			if(state.getDefaultState().getTransition() != null)
			{
				// move transitions in default state to current state
				for(int i = 0; i < state.getDefaultState().getTransition().size(); i++)
				{
						Transition.addTransitionToState(state.getDefaultState().getTransition().get(i), new GenericState(state));
				}
			}
		
			if(state.getDefaultState().getDefaultTransition() != null)
			{
				// move default transitions in default state to current state
				for(int i = 0; i < state.getDefaultState().getDefaultTransition().size(); i++)
				{
						Transition.addTransitionToState(state.getDefaultState().getDefaultTransition().get(i), new GenericState(state));
				}
			}
		}
	}
	
	/**
	 * Takes in a list of parent states (inside the state wrapper for the nested state)
	 * and generates a unique state name for the flattened state
	 * @param stateW, nestedState
	 * @return
	 */
	public static String createFlattenedStateName(org.jts.codegenerator.protocolBehavior.StateWrapper stateW)
	{
		String name;
		
		// step out of last generation
		if(stateW.state == null)
		{
			stateW = stateW.parent;
		}
		
		// set initial name as nested state name
		name = stateW.state.getName();
		
		while(stateW.parent != null)
		{	
			name = stateW.parent.state.getName() + "." + name;
			
			stateW = stateW.parent;
		}

		return name;
	}
	
	/**
	 * Takes a mapping of flattened states with their respective wrappers and changes all the end states to the correct flattened state name
	 * @param stateMap
	 */
	public static void changeEndStateNames(List<org.jts.jsidl.binding.State> flattenedStateList, List<org.jts.codegenerator.protocolBehavior.StateWrapper> wrapperList)
	{
		Iterator<org.jts.jsidl.binding.State> flattenedStateIterator = flattenedStateList.iterator();
		
		// loop through each flattened state
		while(flattenedStateIterator.hasNext())
		{
			// get reference to current flattened state
			org.jts.jsidl.binding.State currentState = flattenedStateIterator.next(); 

			Iterator<org.jts.jsidl.binding.Transition> transitionIterator = currentState.getTransition().iterator();
			Iterator<org.jts.jsidl.binding.DefaultTransition> defaultTransitionIterator = currentState.getDefaultTransition().iterator();

			// loop through all transitions of the current flattened state
			while(transitionIterator.hasNext())
			{
				// get a reference to the current transition in question
				org.jts.jsidl.binding.Transition transition = transitionIterator.next();
				
				if(transition.getSimple() != null)
				{
					org.jts.jsidl.binding.EndState end = transition.getSimple().getEndState();
					String endState = end.getState();
					
					// if empty, its a loopback transition
					if(endState == null)
					{
						end.setState(currentState.getName());
					}
					else
					{
						end.setState(State.getFlattenedEndStateName(endState, wrapperList));
					}
				}
				else if(transition.getPush() != null)
				{
					org.jts.jsidl.binding.EndState end = transition.getPush().getEndState();
					String endState = end.getState();
					
					// if empty, its a loopback transition
					if(endState == null)
					{
						end.setState(currentState.getName());
					}
					else
					{
						end.setState(State.getFlattenedEndStateName(endState, wrapperList));
					}
				}
				else if(transition.getPop() != null)
				{
					// pop's don't have end states
					//endState = transition.getPop()
				}
				else if(transition.getInternal() != null)
				{
					// internal transitions will have a 'nil' endState so we don't need to add anything here
				}
				
			} // end transition iterator
			
			// loop through all default transitions of the current flattened state
			while(defaultTransitionIterator.hasNext())
			{
				// get a reference to the current transition in question
				org.jts.jsidl.binding.DefaultTransition defaultTransition = defaultTransitionIterator.next();
				
				if(defaultTransition.getSimple() != null)
				{
					org.jts.jsidl.binding.EndState end = defaultTransition.getSimple().getEndState();
					String endState = end.getState();
					
					// if empty, its a loopback transition
					if(endState == null)
					{
						end.setState(currentState.getName());
					}
					else
					{
						end.setState(State.getFlattenedEndStateName(endState, wrapperList));
					}
				}
				else if(defaultTransition.getPush() != null)
				{
					org.jts.jsidl.binding.EndState end = defaultTransition.getPush().getEndState();
					String endState = end.getState();
					
					// if empty, its a loopback transition
					if(endState == null)
					{
						end.setState(currentState.getName());
					}
					else
					{
						end.setState(State.getFlattenedEndStateName(endState, wrapperList));
					}
				}
				else if(defaultTransition.getPop() != null)
				{
					// pop's don't have end states
					//endState = transition.getPop()
				}
				else if(defaultTransition.getInternal() != null)
				{
					// internal transitions will have a 'nil' endState so we don't need to add anything here
				}
				
			} // end default transition iterator
			
			// after flattening, there are no default states

		} // end state iterator
		
	}
	
	/**
	 * Generates the flattened state name based on the endState and a mapping of the flattened states and their wrappers
	 * @param endState
	 * @param stateMap
	 * @return
	 */
	public static String getFlattenedEndStateName(String endState, List<org.jts.codegenerator.protocolBehavior.StateWrapper> wrapperList)
	{
		Iterator<org.jts.codegenerator.protocolBehavior.StateWrapper> stateWrapperIterator = wrapperList.iterator();

		// check each state wrapper for end state
		while(stateWrapperIterator.hasNext())
		{
			org.jts.codegenerator.protocolBehavior.StateWrapper currentStateWrapper = stateWrapperIterator.next();
			
			// step out of last generation 
			if(currentStateWrapper.state == null)
			{
				currentStateWrapper = currentStateWrapper.parent;
			}
			
			//******************************
			// if no nests, check current state
			//******************************
			if(currentStateWrapper.parent == null)
			{
				if(endState.compareTo(currentStateWrapper.state.getName()) == 0)
				{
					org.jts.jsidl.binding.State currentState = currentStateWrapper.state;
					
					// drill down to leaf from parent start states
					while((currentState.getInitialState() != null) && !currentState.getInitialState().isEmpty())
					{
						String initialState = currentState.getInitialState();
						
						// look through all child states of current state for start state
						for(int i = 0; i < currentState.getState().size(); i++)
						{
							if(currentState.getState().get(i).getName().compareTo(initialState) == 0)
							{
								currentState = currentState.getState().get(i);
								break;
							}
						}
					}
					
					// current state should be at leaf state now so just return the name of the leaf
					return currentState.getName();
				}
			}
			
			//******************************
			// go through all parents and check each to see if end state is the current state
			//******************************
			while(currentStateWrapper.parent != null)
			{
				if(endState.compareTo(currentStateWrapper.state.getName()) == 0)
				{
					org.jts.jsidl.binding.State currentState = currentStateWrapper.state;
					
					// drill down to leaf from parent start states
					while((currentState.getInitialState() != null) && !currentState.getInitialState().isEmpty())
					{
						String initialState = currentState.getInitialState();
						
						// look through all child states of current state for start state
						for(int i = 0; i < currentState.getState().size(); i++)
						{
							if(currentState.getState().get(i).getName().compareTo(initialState) == 0)
							{
								currentState = currentState.getState().get(i);
								break;
							}
						}
					}
					
					// current state should be at leaf state now so just return the name of the leaf
					return currentState.getName();
				}
				
				currentStateWrapper = currentStateWrapper.parent;
			}
			
			//******************************
			// check top parent for matching
			//******************************
			if(endState.compareTo(currentStateWrapper.state.getName()) == 0)
			{
				org.jts.jsidl.binding.State currentState = currentStateWrapper.state;
				
				// drill down to leaf from parent start states
				while((currentState.getInitialState() != null) && !currentState.getInitialState().isEmpty())
				{
					String initialState = currentState.getInitialState();
					
					// look through all child states of current state for start state
					for(int i = 0; i < currentState.getState().size(); i++)
					{
						if(currentState.getState().get(i).getName().compareTo(initialState) == 0)
						{
							currentState = currentState.getState().get(i);
							break;
						}
					}
				}
				
				// current state should be at leaf state now so just return the name of the leaf
				return currentState.getName();
			}
		}
		
		return null;
	}
	
	/**
	 * Function generates states in a flattened state machine
	 * 
	 * @param stateName name of the state to generate
	 */
	public static StringBuffer addState(org.jts.jsidl.binding.State st, CodeLines.CodeType codeType)
	{
		StringBuffer localBuffer = new StringBuffer();

		localBuffer.append(st.getName() + "\n");	

		localBuffer.append("{\n");	
		
		//add transitions
		localBuffer.append(Transition.addStateTransitions(st.getTransition(), st.getName(), codeType));
		//add default transitions
		localBuffer.append(Transition.addStateDefaultTransitions(st.getDefaultTransition(), st.getName()));
		
		localBuffer.append("}\n\n");
		
		return localBuffer;
	}
		
	/**
	 * Generates the state for the special case of the start state where there will be an entry stub
	 * 
	 * @param stateName name of the state to generate
     * @param codeType the code language to pass on to Transition. Ensures correct data types.
	 */
	public static StringBuffer addState(org.jts.jsidl.binding.State st, List<Object> entryActions, CodeLines.CodeType codeType)
	{
		StringBuffer localBuffer = new StringBuffer();

		localBuffer.append(st.getName() + "\n");	
		
		localBuffer.append(org.jts.codegenerator.protocolBehavior.Entry.addEntry(entryActions));

		localBuffer.append("{\n");	
		
		//add transitions
		localBuffer.append(Transition.addStateTransitions(st.getTransition(), st.getName(), codeType));
		
		//add default transitions
		localBuffer.append(Transition.addStateDefaultTransitions(st.getDefaultTransition(), st.getName()));
		
		localBuffer.append("}\n\n");
		
		return localBuffer;
	}

	/**
	 * Makes a copy of all primitives of the state instead of references
	 * @param inputState
	 * @return
	 */
	public static org.jts.jsidl.binding.State cloneState(org.jts.jsidl.binding.State inputState)
	{
		org.jts.jsidl.binding.State returnState = new org.jts.jsidl.binding.State();
		
		if(inputState.getTransition() != null)
		{
			for(int i = 0; i < inputState.getTransition().size(); i++)
			{
				org.jts.jsidl.binding.Transition returnTransition = new org.jts.jsidl.binding.Transition();
				
				returnTransition = Transition.cloneTransition(inputState.getTransition().get(i));

				returnState.getTransition().add(returnTransition);
			}
		}
		
		if(inputState.getDefaultTransition() != null)
		{
			for(int i = 0; i < inputState.getDefaultTransition().size(); i++)
			{
				org.jts.jsidl.binding.DefaultTransition returnDefaultTransition = new org.jts.jsidl.binding.DefaultTransition();
				
				returnDefaultTransition = Transition.cloneDefaultTransition(inputState.getDefaultTransition().get(i));

				returnState.getDefaultTransition().add(returnDefaultTransition);
			}
		}
		
		if(inputState.getDefaultState() != null)
		{
			org.jts.jsidl.binding.DefaultState returnDefaultState = new org.jts.jsidl.binding.DefaultState();
			
			returnDefaultState = org.jts.codegenerator.protocolBehavior.State.cloneDefaultState(inputState.getDefaultState());			
			
			returnState.setDefaultState(returnDefaultState);
		}
		
		if(inputState.getEntry() != null)
		{
			org.jts.jsidl.binding.Entry returnEntry = new org.jts.jsidl.binding.Entry();
			
			for(int i = 0; i < inputState.getEntry().getActionOrSendAction().size(); i++)
			{
				returnEntry.getActionOrSendAction().add(Action.cloneActionOrSendAction(inputState.getEntry().getActionOrSendAction().get(i)));
			}
			
			returnState.setEntry(returnEntry);
		}
		
		if(inputState.getExit() != null)
		{
			org.jts.jsidl.binding.Exit returnExit = new org.jts.jsidl.binding.Exit();
			
			for(int i = 0; i < inputState.getExit().getActionOrSendAction().size(); i++)
			{
				returnExit.getActionOrSendAction().add(Action.cloneActionOrSendAction(inputState.getExit().getActionOrSendAction().get(i)));
			}
			
			returnState.setExit(returnExit);
		}
		
		if((inputState.getInitialState() != null) && !inputState.getInitialState().isEmpty())
		{
			returnState.setInitialState(new String(inputState.getInitialState()));
		}
		
		if(inputState.getName() != null)
		{
			returnState.setName(new String(inputState.getName()));
		}
		
		if(inputState.getState() != null)
		{
			for(int i = 0; i < inputState.getState().size(); i++)
			{
				org.jts.jsidl.binding.State returnSt = new org.jts.jsidl.binding.State();
				
				returnSt = State.cloneState(inputState.getState().get(i));
				
				returnState.getState().add(returnSt);
			}
		}
				
		return returnState;
	}
	
	public static org.jts.jsidl.binding.DefaultState cloneDefaultState(org.jts.jsidl.binding.DefaultState inputDefaultState)
	{
		if(inputDefaultState == null)
		{
			return null;
		}
		
		org.jts.jsidl.binding.DefaultState returnDefaultState = new org.jts.jsidl.binding.DefaultState();
		
		if(inputDefaultState != null)
		{
			if(inputDefaultState.getTransition() != null)
			{
				for(int i = 0; i < inputDefaultState.getTransition().size(); i++)
				{
					org.jts.jsidl.binding.Transition returnTransition = new org.jts.jsidl.binding.Transition();
					
					returnTransition = Transition.cloneTransition(inputDefaultState.getTransition().get(i));
	
					returnDefaultState.getTransition().add(returnTransition);
				}
			}
			
			if(inputDefaultState.getDefaultTransition() != null)
			{
				for(int i = 0; i < inputDefaultState.getDefaultTransition().size(); i++)
				{
					org.jts.jsidl.binding.DefaultTransition returnDefaultTransition = new org.jts.jsidl.binding.DefaultTransition();
					
					returnDefaultTransition = Transition.cloneDefaultTransition(inputDefaultState.getDefaultTransition().get(i));
	
					returnDefaultState.getDefaultTransition().add(returnDefaultTransition);
				}
			}
		}
		
		return returnDefaultState;
	}
	
	/**
	 * Prints out all nested state names within a state (recursive)
	 * @param state
	 */
	public static void printAllNestedStates(org.jts.jsidl.binding.State state)
	{
		for(int i = 0; i < state.getState().size(); i++)
		{
			printAllNestedStates(state.getState().get(i));
		}
	}
	
	/**
	 * Prints out all initial states within a state (recursive)
	 * @param state
	 */
	public static void printAllInitialStates(org.jts.jsidl.binding.State state)
	{		
		for(int i = 0; i < state.getState().size(); i++)
		{
			printAllInitialStates(state.getState().get(i));
		}
	}
	
	/**
	 * Prints out all end states from simple transitions within a state (recursive) 
	 * @param state
	 */
	public static void printAllEndStates(org.jts.jsidl.binding.State state)
	{
		for(int i = 0; i < state.getTransition().size(); i++)
		{
			org.jts.jsidl.binding.Transition transition = state.getTransition().get(i);
			
		}
		
		for(int i = 0; i < state.getState().size(); i++)
		{
			printAllEndStates(state.getState().get(i));
		}
	}
	
	/**
	 * Retrieves all names of the state and substates
	 * @param state
	 * @param names
	 */
	public static void getAllStateNames(org.jts.jsidl.binding.State state, ArrayList<String> names)
	{
		String name = state.getName();
		
		if(name != null)
		{
			names.add(name);
		}
		
		for(org.jts.jsidl.binding.State st:state.getState())
		{
			getAllStateNames(st, names);
		}
	}

	/**
	 * Replaces all '.' namespacing in every state name and every end state name with '_'
	 * @param flattenedStateList
	 */
	public static void replaceNameSpacing(List<org.jts.jsidl.binding.State> flattenedStateList)
	{
		if( flattenedStateList == null )
		{
			return;
		}
		
		for( org.jts.jsidl.binding.State state : flattenedStateList )
		{
			if( state == null || state.getName() == null )
			{
				continue;
			}
			
			// replace the namespacing in the state name
			state.setName( state.getName().replaceAll("\\.", "_") );
			
			// replace the namespacing in the end state names in transitions
			if( state.getTransition() != null )
			{
				for( org.jts.jsidl.binding.Transition transition : state.getTransition() )
				{
					org.jts.codegenerator.protocolBehavior.Transition.replaceNameSpacing( transition );
				}
			}
			
			// replace the namespacing in the end state names in default transitions
			if( state.getDefaultTransition() != null )
			{
				for( org.jts.jsidl.binding.DefaultTransition defaultTransition : state.getDefaultTransition() )
				{
					org.jts.codegenerator.protocolBehavior.Transition.replaceNameSpacing( defaultTransition );
				}
			}
		}
	}
	
}