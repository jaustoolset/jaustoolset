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
 * @(#)Exit.java
 * 
 * Copyright
 */
package org.jts.codegenerator.protocolBehavior;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.UUID;

public class Exit {
    /**
	 * Adds the exit condition of the state to parentCode
	 * @param st
	 */
	public static StringBuffer addExit(Vector<org.jts.jsidl.binding.Exit> exitStates)
	{
		StringBuffer localBuffer = new StringBuffer();
		
		localBuffer.append("\tExit\n");
		
		localBuffer.append("\t{\n");
		
		for(int i = 0; i < exitStates.size(); i++)
		{
			org.jts.jsidl.binding.Exit ex = (org.jts.jsidl.binding.Exit)exitStates.elementAt(i);
			
			for(int j = 0; j < ex.getActionOrSendAction().size(); j++)
			{
				Object obj = ex.getActionOrSendAction().get(j);
				
				localBuffer.append("\t\t\t");
				localBuffer.append(Action.addActionOrSendAction(obj));
				localBuffer.append("\n");
			}
		}

		localBuffer.append("\t}\n");
		
		return localBuffer;
	}
	
	public static void addExitActionsToWrapperList(List<org.jts.codegenerator.protocolBehavior.StateWrapper> wrapperList)
	{
		Iterator<org.jts.codegenerator.protocolBehavior.StateWrapper> stateWrapperIterator = wrapperList.iterator();
		
		// iterate through each state wrapper to add entry actions onto every applicable transition
		while(stateWrapperIterator.hasNext())
		{
			// get next state wrapper instance of the iterator
			org.jts.codegenerator.protocolBehavior.StateWrapper stateW = stateWrapperIterator.next();
			
			// get reference for current wrapper to pass to sub-processing functions
			org.jts.codegenerator.protocolBehavior.StateWrapper originalWrapper = stateW;
			
			// go to bottom
			stateW = org.jts.codegenerator.protocolBehavior.StateWrapper.goToBottom(stateW);
						
			while(stateW.parent != null)
			{
				org.jts.jsidl.binding.State currentState = stateW.state;
				
				// add exit actions for current state
				addExitActionsToState(originalWrapper, currentState);
				
				// step up in the wrapper 
				stateW = stateW.parent;
			}
			
			// now we deal with the top level wrapper(stateW will now be at parentHandle=null)
			org.jts.jsidl.binding.State currentState = stateW.state;
			
			// add entry actions for current state
			addExitActionsToState(originalWrapper, currentState);			
		}
	}
	
	public static void addExitActionsToState(org.jts.codegenerator.protocolBehavior.StateWrapper currentWrapper, org.jts.jsidl.binding.State currentState)
	{
		// add exit actions for each transition
		Iterator<org.jts.jsidl.binding.Transition> transitionIterator = currentState.getTransition().iterator();
		
		while(transitionIterator.hasNext())
		{
			org.jts.jsidl.binding.Transition transition = transitionIterator.next();
			
			addExitActionsToTransition(transition, currentWrapper, currentState);
		}

		// add exit actions for each default transitions
		Iterator<org.jts.jsidl.binding.DefaultTransition> defaultTransitionIterator = currentState.getDefaultTransition().iterator();
		
		while(defaultTransitionIterator.hasNext())
		{
			org.jts.jsidl.binding.DefaultTransition defaultTransition = defaultTransitionIterator.next();
			
			addExitActionsToTransition(defaultTransition, currentWrapper, currentState);
		}
		
		if(currentState.getDefaultState() != null)
		{
			// add exit actions for each default state transition
			Iterator<org.jts.jsidl.binding.Transition> defaultStateTransitionIterator = currentState.getDefaultState().getTransition().iterator();
			
			while(defaultStateTransitionIterator.hasNext())
			{
				org.jts.jsidl.binding.Transition transition = defaultStateTransitionIterator.next();
				
				addExitActionsToTransition(transition, currentWrapper, currentState);
			}
			
			// add exit actions for each default state default transitions
			Iterator<org.jts.jsidl.binding.DefaultTransition> defaultStateDefaultTransitionIterator = currentState.getDefaultState().getDefaultTransition().iterator();
			
			while(defaultStateDefaultTransitionIterator.hasNext())
			{
				org.jts.jsidl.binding.DefaultTransition defaultTransition = defaultStateDefaultTransitionIterator.next();
				
				addExitActionsToTransition(defaultTransition, currentWrapper, currentState);
			}
		}
	}
	
	public static void addExitActionsToTransition(Object object, org.jts.codegenerator.protocolBehavior.StateWrapper currentWrapper, org.jts.jsidl.binding.State currentState)
	{
		if(object instanceof org.jts.jsidl.binding.Transition)
		{
			org.jts.jsidl.binding.Transition transition = (org.jts.jsidl.binding.Transition)object;
			
			List<Object> exits = null;
			String endStateName = null;
								
			if(transition.getSimple() != null)
			{
				endStateName = transition.getSimple().getEndState().getState();
				
				// when the transition is an external loopback, the endStateName could be null
				// in this case, we set the endStateName to the current stateName
				if(endStateName == null)
				{
					endStateName = currentState.getName();
				}
				
				exits = getExitActionsForEndStateSimpleTransition(currentWrapper, endStateName, currentState);
			}
			else if(transition.getPop() != null)
			{
				// let endStateName for a pop transition stay as null
				// because we don't know what state the pop will end up until runtime
				exits = getExitActionsForEndStatePopTransition(currentWrapper, transition, currentState);
			}
			else if(transition.getInternal() != null)
			{
				// no exit actions for an internal transition
				return;
			}
			else if(transition.getPush() != null)
			{
				// no exit actions for a push transition
				return;
			}
			
			// prepend actions onto transition action list
			if(exits != null)
			{
				transition.getActionOrSendAction().addAll(0, exits);
			}
		}
		else if(object instanceof org.jts.jsidl.binding.DefaultTransition)
		{
			org.jts.jsidl.binding.DefaultTransition defaultTransition = (org.jts.jsidl.binding.DefaultTransition)object;
		
			List<Object> exits = null;
			String endStateName = null;
								
			if(defaultTransition.getSimple() != null)
			{
				endStateName = defaultTransition.getSimple().getEndState().getState();
				
				// when the transition is an external loopback, the endStateName could be null
				// in this case, we set the endStateName to the current stateName
				if(endStateName == null)
				{
					endStateName = currentState.getName();
				}
				
				exits = getExitActionsForEndStateSimpleTransition(currentWrapper, endStateName, currentState);
			}
			else if(defaultTransition.getPop() != null)
			{
				// let endStateName for a pop transition stay as null
				// because we don't know what state the pop will end up in until runtime
				exits = getExitActionsForEndStatePopTransition(currentWrapper, null, currentState);
			}
			else if(defaultTransition.getInternal() != null)
			{
				// no exit actions for an internal transition
				return;
			}
			else if(defaultTransition.getPush() != null)
			{
				// no exit actions for a push transition
				return;
			}
		
			// prepend actions onto transition action list
			if(exits != null)
			{
				defaultTransition.getActionOrSendAction().addAll(0, exits);
			}
		}
	}
	
	public static List<Object> getExitActionsForEndStatePopTransition(org.jts.codegenerator.protocolBehavior.StateWrapper currentWrapper, org.jts.jsidl.binding.Transition transition, org.jts.jsidl.binding.State currentState)
	{
		List<Object> exitActions = new ArrayList<Object>();

		org.jts.codegenerator.protocolBehavior.StateWrapper stateW = org.jts.codegenerator.protocolBehavior.StateWrapper.goToBottom(currentWrapper);
		
		String seperator = null;
		
		exitActions.add(seperator);
		
		// we must store the leaf state name to figure out when there is a loopback
		org.jts.jsidl.binding.Action actionStateName1 = new org.jts.jsidl.binding.Action();
		actionStateName1.setName(stateW.state.getName());
		exitActions.add(actionStateName1);
		
		// we must also store what state the transition was defined in
		org.jts.jsidl.binding.Action actionStateName2 = new org.jts.jsidl.binding.Action();
		actionStateName2.setName(currentState.getName());
		exitActions.add(actionStateName2);
		
		List<Object> bottomUpActions = new ArrayList<Object>();
		
		// check each level of state wrapper
		while(stateW.parent != null)
		{
			
			if(stateW.state.getExit() != null)
			{
				if(stateW.state.getExit().getActionOrSendAction() != null)
				{
					bottomUpActions.addAll(stateW.state.getExit().getActionOrSendAction());
				}
			}
			bottomUpActions.add(seperator);
			
			stateW = stateW.parent;
		}
		
		bottomUpActions.add(seperator);
		
		// check top level
		if(stateW.state.getExit() != null)
		{
			if(stateW.state.getExit().getActionOrSendAction() != null)
			{
				bottomUpActions.addAll(stateW.state.getExit().getActionOrSendAction());
			}
		}
		
		//java.util.Collections.reverse(bottomUpActions);
		
		bottomUpActions.add(seperator);
		bottomUpActions.add(seperator);
		exitActions.addAll(bottomUpActions);
		
		org.jts.jsidl.binding.Action action = new org.jts.jsidl.binding.Action();

		// transfer parameters to new action wrapper
		if(transition != null && transition.getParameter() != null)
		{
			for(org.jts.jsidl.binding.Parameter parameter : transition.getParameter())
			{
				org.jts.jsidl.binding.Argument tempArgument = new org.jts.jsidl.binding.Argument();
				tempArgument.setValue(parameter.getValue());

				action.getArgument().add(tempArgument);
			}
		}

		// create new name for function
		// we can guarantee a unique name if we use the state name + transition guards + transition parameters
		String functionName = currentState.getName() + transition.getName();
		
		// add guard definition to function name
		if(transition != null && transition.getGuard() != null)
		{
		    functionName += transition.getGuard().getCondition();
		}
		
		// add parameter types and values to function name
		if(transition != null && transition.getParameter() != null)
		{
		    for(org.jts.jsidl.binding.Parameter parameter : transition.getParameter())
		    {
		        functionName += parameter.getType() + parameter.getValue();
		    }
		}
		
		// c++ functions can't contain '-'
		functionName = UUID.nameUUIDFromBytes(functionName.getBytes()).toString().replace("-", "");
		
		// c++ function names can't start with possible number of uuid
		action.setName("popWrapper_" + functionName);

		exitActions.add(1, action);
		
		return exitActions;
	}
	
	public static List<Object> getExitActionsForEndStateSimpleTransition(org.jts.codegenerator.protocolBehavior.StateWrapper currentWrapper, String endStateName, org.jts.jsidl.binding.State currentState)
	{
		List<Object> exitActions = new ArrayList<Object>();
		
		// if loopback, we are only exiting the current state
		if(endStateName.compareTo(currentState.getName()) == 0)
		{
			// we are only exiting the current state if it is a loopback
			if(currentWrapper.state.getExit() != null)
			{
				if(currentWrapper.state.getExit().getActionOrSendAction() != null)
				{
					exitActions.addAll(currentWrapper.state.getExit().getActionOrSendAction());
				}
			}
			
			return exitActions;
		}
		
		// tokenize state levels
		String[] endLevels = endStateName.split("\\.");
		String[] currentLevels = currentState.getName().split("\\.");
		int levelIndex = 0;
		
		// number of levels in current state
		int numCurrentLevels = currentLevels.length;
		int numEndLevels = endLevels.length;
		int matchingTopLevelStates = 0;
		
		org.jts.codegenerator.protocolBehavior.StateWrapper stateW = currentWrapper;
		stateW = org.jts.codegenerator.protocolBehavior.StateWrapper.goToBottom(stateW);
		
		do
		{
			// check for matching top level states
			if(endLevels[levelIndex].compareTo(currentLevels[levelIndex]) == 0)
			{
				matchingTopLevelStates++;
			}
			else
			{
				break;
			}
			
			levelIndex++;			
		}
		while(levelIndex < numCurrentLevels && levelIndex < numEndLevels);
		
		// current state is entirely enclosed in endstate
		if(matchingTopLevelStates == numCurrentLevels)
		{
			// external loopback, process all exit actions
			while(stateW.parent != null)
			{
				if(stateW.state.getExit() != null)
				{
					if(stateW.state.getExit().getActionOrSendAction() != null)
					{
						exitActions.addAll(stateW.state.getExit().getActionOrSendAction());
					}
				}
				
				stateW = stateW.parent;
			}
			
			// process top
			if(stateW.state.getExit() != null)
			{
				if(stateW.state.getExit().getActionOrSendAction() != null)
				{
					exitActions.addAll(stateW.state.getExit().getActionOrSendAction());
				}
			}
			
			return exitActions;
		}
		
		int numberOfStatesInWrapper = org.jts.codegenerator.protocolBehavior.StateWrapper.getNumberOfStatesInWrapper(stateW);
		
		int levelsToTraverse = numberOfStatesInWrapper -  matchingTopLevelStates;
		
		for(int i = 0; i < levelsToTraverse; i++)
		{
			if(stateW.state.getExit() != null)
			{
				if(stateW.state.getExit().getActionOrSendAction() != null)
				{
					exitActions.addAll(stateW.state.getExit().getActionOrSendAction());
				}
			}
			
			stateW = stateW.parent;
		}
		
		return exitActions;
	}
	
	public static List<Object> getExitActionsFromBottomToCurrentState(org.jts.codegenerator.protocolBehavior.StateWrapper stateW, String currentStateName)
	{
		List<Object> exitActions = new ArrayList<Object>();
		
		// if wrapper is empty, the currentStateName wasn't in the wrapper
		if(stateW == null)
		{
			return exitActions;
		}
		
		// if top level return exits if any
		if(stateW.parent == null)
		{
			if(stateW.state.getExit() != null)
			{
				if(stateW.state.getExit().getActionOrSendAction() != null)
				{
					exitActions.addAll(stateW.state.getExit().getActionOrSendAction());
				}
			}			
			
			return exitActions;
		}
		
		// add all exit actions from bottom level up to where the current transition is
		while(stateW.state.getName().compareTo(currentStateName) != 0)
		{			
			if(stateW.state.getExit() != null)
			{
				if(stateW.state.getExit().getActionOrSendAction() != null)
				{
					exitActions.addAll(stateW.state.getExit().getActionOrSendAction());
				}
			}			
			stateW = stateW.parent;			
		}
		
		// handle current level
		if(stateW.state.getExit() != null)
		{
			if(stateW.state.getExit().getActionOrSendAction() != null)
			{
				exitActions.addAll(stateW.state.getExit().getActionOrSendAction());
			}
		}
		
		return exitActions;
	}
}
