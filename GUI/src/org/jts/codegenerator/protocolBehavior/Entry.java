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
 * @(#)Entry.java
 * 
 * Copyright
 */
package org.jts.codegenerator.protocolBehavior;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Entry {
    /**
	 * Adds the entry condition of the state to parentCode
	 * @param st
	 */
	public static StringBuffer addEntry(List<Object> entryActions)
	{
		StringBuffer localBuffer = new StringBuffer();
		
		localBuffer.append("Entry\n");
		
		localBuffer.append("{\n");
		
		if(entryActions != null)
		{
			for(int i = 0; i < entryActions.size(); i++)
			{
				Object obj = entryActions.get(i);
				
				localBuffer.append("\t\t\t");
				localBuffer.append(Action.addActionOrSendAction(obj));
				localBuffer.append("\n");
			}
		}

		localBuffer.append("}\n");
		
		return localBuffer;
	}
	
	/**
	 * Adds applicable entry actions to each wrapper of the list
	 */
	public static void addEntryActionsToWrapperList(List<org.jts.codegenerator.protocolBehavior.StateWrapper> wrapperList)
	{
		Iterator<org.jts.codegenerator.protocolBehavior.StateWrapper> stateWrapperIterator = wrapperList.iterator();
		
		// iterate through each state wrapper to add entry actions onto every applicable transition
		while(stateWrapperIterator.hasNext())
		{
			// get next state wrapper instance of the iterator
			org.jts.codegenerator.protocolBehavior.StateWrapper stateW = stateWrapperIterator.next();
			
			// step out of last generation if the wrapper is in the last generation
			if(stateW.state == null)
			{
				stateW = stateW.parent;
			}
			
			while(stateW.parent != null)
			{
				org.jts.jsidl.binding.State currentState = stateW.state;
				
				// add entry actions for current state
				addEntryActionsToState(wrapperList, currentState);
				
				// step up in the wrapper 
				stateW = stateW.parent;
			}
			
			// now we deal with the top level wrapper(stateW will now be at parentHandle=null)
			org.jts.jsidl.binding.State currentState = stateW.state;
			
			// add entry actions for current state
			addEntryActionsToState(wrapperList, currentState);			
		}
	}
	
	/**
	 * Adds entry actions for a state in the wrapper list
	 * @param wrapperList
	 * @param currentState
	 */
	public static void addEntryActionsToState(List<org.jts.codegenerator.protocolBehavior.StateWrapper> wrapperList, org.jts.jsidl.binding.State currentState)
	{
		// add entry actions for each transition
		Iterator<org.jts.jsidl.binding.Transition> transitionIterator = currentState.getTransition().iterator();
		
		while(transitionIterator.hasNext())
		{
			org.jts.jsidl.binding.Transition transition = transitionIterator.next();
			
			addEntryActionsToTransition(transition, wrapperList, currentState);
		}

		// add entry actions for each default transitions
		Iterator<org.jts.jsidl.binding.DefaultTransition> defaultTransitionIterator = currentState.getDefaultTransition().iterator();
		
		while(defaultTransitionIterator.hasNext())
		{
			org.jts.jsidl.binding.DefaultTransition defaultTransition = defaultTransitionIterator.next();
			
			addEntryActionsToTransition(defaultTransition, wrapperList, currentState);
		}
		
		if(currentState.getDefaultState() != null)
		{
			// add exit actions for each default state transition
			Iterator<org.jts.jsidl.binding.Transition> defaultStateTransitionIterator = currentState.getDefaultState().getTransition().iterator();
			
			while(defaultStateTransitionIterator.hasNext())
			{
				org.jts.jsidl.binding.Transition transition = defaultStateTransitionIterator.next();
				
				addEntryActionsToTransition(transition, wrapperList, currentState);
			}
			
			// add exit actions for each default state default transitions
			Iterator<org.jts.jsidl.binding.DefaultTransition> defaultStateDefaultTransitionIterator = currentState.getDefaultState().getDefaultTransition().iterator();
			
			while(defaultStateDefaultTransitionIterator.hasNext())
			{
				org.jts.jsidl.binding.DefaultTransition defaultTransition = defaultStateDefaultTransitionIterator.next();
				
				addEntryActionsToTransition(defaultTransition, wrapperList, currentState);
			}
		}
	}
	
	/**
	 * Adds entry actions for a transition or default transition given which unflattened state the transition is in
	 * @param object
	 * @param wrapperList
	 * @param currentState
	 */
	public static void addEntryActionsToTransition(Object object, List<org.jts.codegenerator.protocolBehavior.StateWrapper> wrapperList, org.jts.jsidl.binding.State currentState)
	{
		if(object instanceof org.jts.jsidl.binding.Transition)
		{
			org.jts.jsidl.binding.Transition transition = (org.jts.jsidl.binding.Transition)object;
			
			List<Object> entries = null;
			String endStateName = null;
			
			if(transition.getSimple() != null)
			{
				endStateName = transition.getSimple().getEndState().getState();
				
				// when the transition is an external loopback, the endStateName could be null
				// in this case, we set the endStateName to the stateName
				if(endStateName == null)
				{
					endStateName = currentState.getName();
				}
			}
			else if(transition.getPush() != null)
			{
				endStateName = transition.getPush().getEndState().getState();
				
				// when the transition is an external loopback, the endStateName could be null
				// in this case, we set the endStateName to the stateName
				if(endStateName == null)
				{
					endStateName = currentState.getName();
				}
			}
			else if(transition.getPop() != null)
			{
				// pop transitions don't get entry actions
				return;
			}
			else if(transition.getInternal() != null)
			{
				// internal transition don't get entry actions
				return;
			}
			
			entries = Entry.getEntryActionsForEndState(wrapperList, endStateName, currentState);
		
			if(entries != null)
			{
				transition.getActionOrSendAction().addAll(entries);
			}
		}
		else if(object instanceof org.jts.jsidl.binding.DefaultTransition)
		{
			org.jts.jsidl.binding.DefaultTransition defaultTransition = (org.jts.jsidl.binding.DefaultTransition)object;
		
			List<Object> entries = null;
			String endStateName = null;
			
			if(defaultTransition.getSimple() != null)
			{
				endStateName = defaultTransition.getSimple().getEndState().getState();
				
				// when the transition is an external loopback, the endStateName could be null
				// in this case, we set the endStateName to the stateName
				if(endStateName == null)
				{
					endStateName = currentState.getName();
				}
			}
			else if(defaultTransition.getPush() != null)
			{
				endStateName = defaultTransition.getPush().getEndState().getState();
				
				// when the transition is an external loopback, the endStateName could be null
				// in this case, we set the endStateName to the stateName
				if(endStateName == null)
				{
					endStateName = currentState.getName();
				}
			}
			else if(defaultTransition.getPop() != null)
			{
				// pop transitions don't get entry actions
				return;
			}
			else if(defaultTransition.getInternal() != null)
			{
				// internal transition don't get entry actions
				return;
			}
			
			entries = Entry.getEntryActionsForEndState(wrapperList, endStateName, currentState);
		
			if(entries != null)
			{
				defaultTransition.getActionOrSendAction().addAll(entries);
			}
		}
	}
	
	/**
	 * Finds applicable entry actions given a desired endState and the currentState we are in
	 * @param wrapperList
	 * @param endState
	 * @param currentState
	 * @return
	 */
	public static List <Object> getEntryActionsForEndState(List<org.jts.codegenerator.protocolBehavior.StateWrapper> wrapperList, String endState, org.jts.jsidl.binding.State currentState)
	{
		List <Object> entryActions = new ArrayList<Object>();
		
		if(endState == null)
		{
			return null;
		}
		
		// find wrapper for end state
		// this is the wrapper with the endState and all initial states after that
		org.jts.codegenerator.protocolBehavior.StateWrapper wrapper = org.jts.codegenerator.protocolBehavior.StateWrapper.findMatchingEndStateWrapper(endState, wrapperList);
		
		wrapper = org.jts.codegenerator.protocolBehavior.StateWrapper.goToTop(wrapper);
		
		// split the current state name
		String currentTokens[] = currentState.getName().split("\\.");
		
		// split the end state name
		String endTokens[] = endState.split("\\.");
		
		int index = 0;
		int numberToSkip = 0;
		
		// check to see if current state or end state is top level
		if(endTokens.length == 1 || currentTokens.length == 1)
		{
			// process all entry actions from reverseLeafWrapper
			while(wrapper.child != null)
			{
				if(wrapper.state.getEntry() != null && wrapper.state.getEntry().getActionOrSendAction() != null)
				{
					entryActions.addAll(wrapper.state.getEntry().getActionOrSendAction());
				}
				
				wrapper = wrapper.child;
			}
			
			// process last
			if(wrapper.state.getEntry() != null && wrapper.state.getEntry().getActionOrSendAction() != null)
			{
				entryActions.addAll(wrapper.state.getEntry().getActionOrSendAction());
			}
			
			// processing done, so we return
			return entryActions;
		}
		
		// first we have to check for special case of when end state is same as current state
		if(currentState.getName().compareTo(endState) == 0)
		{
			// since this is a loopback, we need to skip every entry action except the current state down
			numberToSkip = currentTokens.length-1;
		}
		// otherwise, we need to find how many states to skip
		else
		{
			while(currentTokens[index].compareTo(endTokens[index]) == 0)
			{
				// increment how many states to skip
				numberToSkip++;
				
				// increment token index
				index++;
				
				// check to see if currentTokens are done
				if(currentTokens.length == index+1)
				{
					break;
				}
				// check to see if endTokens are done
				else if(endTokens.length == index+1)
				{
					numberToSkip--;
					break;
				}
			}
		}
		
		wrapper = org.jts.codegenerator.protocolBehavior.StateWrapper.goToTop(wrapper);
		
		// take off skipped states from top down wrapper
		for(int i = 0; i < numberToSkip; i++)
		{
			wrapper = wrapper.child;
		}
		
		// process all entry actions from top down wrapper from current level
		while(wrapper.child != null)
		{
			if(wrapper.state.getEntry() != null && wrapper.state.getEntry().getActionOrSendAction() != null)
			{
				entryActions.addAll(wrapper.state.getEntry().getActionOrSendAction());
			}
			
			wrapper = wrapper.child;
		}
		
		// process last
		if(wrapper.state.getEntry() != null && wrapper.state.getEntry().getActionOrSendAction() != null)
		{
			entryActions.addAll(wrapper.state.getEntry().getActionOrSendAction());
		}
		
		// processing done, so we return
		return entryActions;
	}
	
	/**
	 * Finds all entry actions that need to be appended to the start state entry stub
	 * @param entryActions
	 * @param stateW
	 */
	public static void getEntryActionsForStartStateFromWrapper(List <Object> entryActions, org.jts.jsidl.binding.Start start, List<org.jts.codegenerator.protocolBehavior.StateWrapper> wrapperList, List<org.jts.codegenerator.protocolBehavior.StateWrapper> clonedUnflattenedStateWrapperList)
	{		
		//** we have to create a special start to replace the start state so that entry action will be executed
		String startStateName = org.jts.codegenerator.protocolBehavior.State.getFlattenedEndStateName( start.getStateName(), wrapperList );

		// start state leaf wrapper
		org.jts.codegenerator.protocolBehavior.StateWrapper wrapper = org.jts.codegenerator.protocolBehavior.StateWrapper.findMatchingBottomWrapper(startStateName, clonedUnflattenedStateWrapperList);
				
		// get a top down state wrapper
		//org.jts.codegenerator.protocolBehavior.StateWrapper reversedWrapper = org.jts.codegenerator.protocolBehavior.StateWrapper.reverseStateWrapper(startLeaf);
		
		wrapper = org.jts.codegenerator.protocolBehavior.StateWrapper.goToTop(wrapper);
			
		while(wrapper.child != null)
		{
			if(wrapper.state.getEntry() != null)
			{
				if(wrapper.state.getEntry().getActionOrSendAction() != null)
				{
					entryActions.addAll(wrapper.state.getEntry().getActionOrSendAction());
				}
			}
			
			wrapper = wrapper.child;
		}
		
		// process root level
		if(wrapper.state.getEntry() != null)
		{
			if(wrapper.state.getEntry().getActionOrSendAction() != null)
			{
				entryActions.addAll(wrapper.state.getEntry().getActionOrSendAction());
			}
		}
	}
}