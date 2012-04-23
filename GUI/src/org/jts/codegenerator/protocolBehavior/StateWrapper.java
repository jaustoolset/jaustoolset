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
 * @(#)StateWrapper.java
 * 
 * Copyright
 */
package org.jts.codegenerator.protocolBehavior;

import java.util.Iterator;
import java.util.List;

/**
 * StateWrapper is a handle to the parent state.
 * When the attribute parentHandle is null, it implies that parentState is the shallowest nested state in its hierarchical chain (base state).
 * When the attribute parentState is null, it implies that parentHandle is a handle to the deepest nested state in its hierarchical chain.
**/
public class StateWrapper {
	
	public org.jts.jsidl.binding.State state;
	public org.jts.codegenerator.protocolBehavior.StateWrapper parent;
	public org.jts.codegenerator.protocolBehavior.StateWrapper child;

	/**
	 * A wrapper will start with a null state and a handle but end with a null handle and a state (bottom up tree)
	 * A reverse wrapper will start with a null state and a handle but end with a null handle and a state(top down tree)
	 */
	public StateWrapper(org.jts.jsidl.binding.State state, org.jts.codegenerator.protocolBehavior.StateWrapper parent, org.jts.codegenerator.protocolBehavior.StateWrapper child)
	{
		this.state = state;
		this.parent = parent;
		this.child = child;
	}
	
	/**
	 * Sets the parent of the wrapper
	 * @param wrapper
	 */
	public void setWrapperParent(org.jts.codegenerator.protocolBehavior.StateWrapper parent)
	{
		this.parent = parent;
	}
	
	/**
	 * Sets the state of the wrapper
	 * @param state
	 */
	public void setWrapperState(org.jts.jsidl.binding.State state)
	{
		this.state = state;
	}
	
	public void setWrapperChild(org.jts.codegenerator.protocolBehavior.StateWrapper child)
	{
		this.child = child;
	}
	
	public static org.jts.codegenerator.protocolBehavior.StateWrapper addChildWrapper(org.jts.codegenerator.protocolBehavior.StateWrapper parentWrapper)
	{
		org.jts.codegenerator.protocolBehavior.StateWrapper childWrapper = new org.jts.codegenerator.protocolBehavior.StateWrapper(null, parentWrapper, null);
		
		parentWrapper.setWrapperChild(childWrapper);
		
		return childWrapper;
	}
	
	public static org.jts.codegenerator.protocolBehavior.StateWrapper addParentWrapper(org.jts.codegenerator.protocolBehavior.StateWrapper childWrapper)
	{
		org.jts.codegenerator.protocolBehavior.StateWrapper parentWrapper = new org.jts.codegenerator.protocolBehavior.StateWrapper(null, null, childWrapper);
		
		childWrapper.setWrapperParent(parentWrapper);
		
		return parentWrapper;
	}
	
	public static org.jts.codegenerator.protocolBehavior.StateWrapper goToBottom(org.jts.codegenerator.protocolBehavior.StateWrapper wrapper)
	{
		while(wrapper.child != null)
		{
			wrapper = wrapper.child;
		}
		
		return wrapper;
	}
	
	public static org.jts.codegenerator.protocolBehavior.StateWrapper goToTop(org.jts.codegenerator.protocolBehavior.StateWrapper wrapper)
	{
		while(wrapper.parent != null)
		{
			wrapper = wrapper.parent;
		}
		
		return wrapper;
	}
	
	/**
	 * A reverse wrapper will start with a handle and a state but end with null handle and null state
	 * While a normal state wrapper will be bottom up(parentHandle starts at leaf state and goes to top level)
	 * a reverse wrapper will start at top level and it's parent handles will lead to the leaf state
	 * @param originalWrapper
	 * @return
	 */
	/*
	public static StateWrapper reverseStateWrapper(StateWrapper originalWrapper)
	{
		// make sure originalWrapper is at the bottom
		if(originalWrapper.state != null)
		{
			return null;
		}
		
		// step out of last generation
		if(originalWrapper.state == null)
		{
			originalWrapper = originalWrapper.parent;
		}
		
		// deal with first generation 
		StateWrapper reverseWrapper = new StateWrapper(originalWrapper.state, null, null);
		
		while(originalWrapper.parent != null)
		{
			reverseWrapper = new StateWrapper(originalWrapper.state, reverseWrapper);
			originalWrapper = originalWrapper.parent;
		}
		
		// deal with last generation of the originalWrapper
		reverseWrapper = new StateWrapper(null, reverseWrapper);
		
		return reverseWrapper;
	}
	*/
	
	/**
	 * This function is used to make a copy of every state in the wrapper and push them all into a new state wrapper
	 * @param wrapper
	 * @return
	 */
	public static org.jts.codegenerator.protocolBehavior.StateWrapper cloneWrapper(org.jts.codegenerator.protocolBehavior.StateWrapper wrapper)
	{
		// go to bottom of wrapper to process all links
		org.jts.codegenerator.protocolBehavior.StateWrapper.goToBottom(wrapper);		
		
		org.jts.codegenerator.protocolBehavior.StateWrapper clonedWrapper = null;
		
		// add children
		while(wrapper.parent != null)
		{
			org.jts.jsidl.binding.State clonedState = new org.jts.jsidl.binding.State();
			clonedState = org.jts.codegenerator.protocolBehavior.State.cloneState(wrapper.state);
			
			clonedWrapper = new org.jts.codegenerator.protocolBehavior.StateWrapper(clonedState, null, clonedWrapper);
			
			wrapper = wrapper.parent;
		}
		
		// clone top wrapper
		org.jts.jsidl.binding.State clonedState = new org.jts.jsidl.binding.State();
		clonedState = org.jts.codegenerator.protocolBehavior.State.cloneState(wrapper.state);
		
		clonedWrapper = new org.jts.codegenerator.protocolBehavior.StateWrapper(clonedState, null, clonedWrapper);
		
		// add parents
		while(clonedWrapper.child != null)
		{
			clonedWrapper.child.setWrapperParent(clonedWrapper);
			
			clonedWrapper = clonedWrapper.child;
		}
				
		return clonedWrapper;
		/*
		org.jts.jsidl.binding.State clonedState = new org.jts.jsidl.binding.State();
		
		// top down wrapper
		org.jts.codegenerator.protocolBehavior.StateWrapper reverseWrapper = reverseStateWrapper(wrapper);
		
		// step out of last generation
		reverseWrapper = reverseWrapper.parent;
		
		// initialize the cloned wrapper (the wrapper will be bottom up when returned)
		clonedState = new org.jts.jsidl.binding.State();
		clonedState = org.jts.codegenerator.protocolBehavior.State.cloneState(reverseWrapper.state);
		org.jts.codegenerator.protocolBehavior.StateWrapper stateW = new org.jts.codegenerator.protocolBehavior.StateWrapper(clonedState, null);
		
		// make clone of each state and place it in the new wrapper
		while(reverseWrapper.parent != null)
		{
			clonedState = new org.jts.jsidl.binding.State();
			clonedState = org.jts.codegenerator.protocolBehavior.State.cloneState(reverseWrapper.state);
			stateW = new org.jts.codegenerator.protocolBehavior.StateWrapper(clonedState, stateW);
			
			reverseWrapper = reverseWrapper.parent;
		}
		
		// handle bottom level of reverse wrapper (add null parentState for return wrapper)
		stateW = new org.jts.codegenerator.protocolBehavior.StateWrapper(null, stateW);
		
		return stateW;
		*/
	}
	
	/**
	 * Finds a leaf state wrapper for any end state within the wrapper (follows initial states until leaf)
	 * NOTE: function expects all states to have their full state names and full initial state names
	 * @param endStateName
	 * @param stateMap
	 * @return
	 */
	public static StateWrapper findMatchingEndStateWrapper(String endStateName, List<org.jts.codegenerator.protocolBehavior.StateWrapper> wrapperList)
	{	
		Iterator<org.jts.codegenerator.protocolBehavior.StateWrapper> stateWrapperIterator = wrapperList.iterator();

		// iterate through each state wrapper to find the correct wrapper
		while(stateWrapperIterator.hasNext())
		{
			// get next state wrapper instance of the iterator
			org.jts.codegenerator.protocolBehavior.StateWrapper stateW = stateWrapperIterator.next();
			
			StateWrapper candidateLeafWrapper = null;
			StateWrapper originalWrapper = null;
			
			// save reference to original for return value
			originalWrapper = stateW;
			
			// make sure wrapper is at top
			stateW = org.jts.codegenerator.protocolBehavior.StateWrapper.goToTop(stateW);
						
			// loop to bottom of wrapper
			while(stateW.child != null)
			{
				// try to find the matching end state in the wrapper
				if(stateW.state.getName().compareTo(endStateName) == 0)
				{
					candidateLeafWrapper = stateW;
					break;
				}
				
				// step down one level
				stateW = stateW.child;
			}
			
			// check last
			// try to find the matching end state in the wrapper
			if(stateW.state.getName().compareTo(endStateName) == 0)
			{
				candidateLeafWrapper = stateW;
			}
			
			// when a candidate is found, test subsequent states(top down wrapper) to see if they are the initial state
			if(candidateLeafWrapper != null)
			{			
				// test if already at bottom
				if(candidateLeafWrapper.child == null)
				{
					return originalWrapper;
				}
				
				// test if each child is the initial state of wrapper
				while(candidateLeafWrapper.child != null)
				{
					org.jts.jsidl.binding.State previousState = candidateLeafWrapper.state;
					
					candidateLeafWrapper = candidateLeafWrapper.child;
					
					org.jts.jsidl.binding.State currentState = candidateLeafWrapper.state;
					
					// if bottom level, candidate is true
					if(candidateLeafWrapper.child == null)
					{
						return originalWrapper;
					}
					
					// check to see if currentState is previousState's initial
					// when it isn't the candidateLeafWrapper is not the desired one
					if(previousState.getInitialState() != null)
					{
						if(previousState.getInitialState().compareTo(currentState.getName()) != 0)
						{
							break;
						}
					}
				}				
			}
			
		}
		
		return null;
	}
	
	/**
	 * Finds a state wrapper with the bottom level state name equal to the given stateName
	 * @param stateName
	 * @param wrapperList
	 * @return
	 */
	public static StateWrapper findMatchingBottomWrapper(String stateName, List<org.jts.codegenerator.protocolBehavior.StateWrapper> wrapperList)
	{
		Iterator<org.jts.codegenerator.protocolBehavior.StateWrapper> stateWrapperIterator = wrapperList.iterator();

		// iterate through each state wrapper to find the correct wrapper
		while(stateWrapperIterator.hasNext())
		{
			// get next state wrapper instance of the iterator
			org.jts.codegenerator.protocolBehavior.StateWrapper stateW = stateWrapperIterator.next();
			
			org.jts.codegenerator.protocolBehavior.StateWrapper originalWrapper = org.jts.codegenerator.protocolBehavior.StateWrapper.cloneWrapper(stateW);
			
			// compare leaf state to see if it matches the desired state
			if(stateName.compareTo(stateW.state.getName()) == 0)
			{
				return originalWrapper;
			}
		}
		
		return null;		
	}
	
	/**
	 * Finds a state wrapper that contains a state that has the same stateName (not necessarily a leaf state)
	 * @param stateName
	 * @param wrapperList
	 * @return
	 */
	public static StateWrapper findMatchingStateWrapper(String stateName, List<org.jts.codegenerator.protocolBehavior.StateWrapper> wrapperList)
	{	
		Iterator<org.jts.codegenerator.protocolBehavior.StateWrapper> stateWrapperIterator = wrapperList.iterator();

		// iterate through each state wrapper to find the correct wrapper
		while(stateWrapperIterator.hasNext())
		{
			// get next state wrapper instance of the iterator
			org.jts.codegenerator.protocolBehavior.StateWrapper stateW = stateWrapperIterator.next();
			
			if(stateW.state == null)
			{
				stateW = stateW.parent;
			}
			
			while(stateW.parent != null)
			{
				org.jts.jsidl.binding.State currentState = stateW.state;
				
				// return if the state name is matched
				if(currentState.getName().compareTo(stateName) == 0)
				{
					return stateW;
				}
				
				// step up in the wrapper 
				stateW = stateW.parent;
			}
			
			// now we deal with the top level wrapper(stateW will now be at parentHandle=null)
			org.jts.jsidl.binding.State currentState = stateW.state;
			
			// return if the state name is matched
			if(currentState.getName().compareTo(stateName) == 0)
			{
				return stateW;
			}
			
		}
		
		return null;
	}
	
	
	public static int getNumberOfStatesInWrapper(org.jts.codegenerator.protocolBehavior.StateWrapper stateW)
	{
		// step out of last generation
		if(stateW.state == null)
		{
			stateW = stateW.parent;
		}
		
		int stateCount = 0;
		
		// go through all states
		while(stateW.parent != null)
		{
			stateCount++;
			
			stateW = stateW.parent;
		}
		
		// count top level
		stateCount++;
		
		return stateCount;
	}
}