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
 * Transition.java
 * 
 */
package org.jts.codegenerator.protocolBehavior;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jts.codegenerator.CodeLines;

public class Transition {

	/**
	 * Creates SMC transition syntax for a flattened state
	 * @param transitions
	 * @param stateName
     * @param codeType to ensure correct data types in a given language.
	 */
	static StringBuffer addStateTransitions(List<org.jts.jsidl.binding.Transition> transitions, String stateName, CodeLines.CodeType codeType)
	{
		StringBuffer localBuffer = new StringBuffer();
		
		for(int i = 0; i < transitions.size(); i++)
		{
			String endstate = "";
			org.jts.jsidl.binding.Transition tr = transitions.get(i);
			localBuffer.append("\t\t");
			
			//add TRANSITION NAME
			localBuffer.append(tr.getName());
			
			//add PARAMETERS
			if(tr.getParameter() != null)
			{
				localBuffer.append(Parameter.addParametersFromTransition(tr, codeType));
			}

			//add GUARDS
			if(tr.getGuard() != null)
			{
				localBuffer.append("\n\t\t");
				localBuffer.append(Guard.addGuardFromTransition(tr));
			}
			
			//add NEXT STATE
			localBuffer.append("\n\t\t");
			
			if(tr.getSimple() != null)
			{
				//add loop-back transition if no end state is specified
				if(tr.getSimple().getEndState() == null)
				{
					localBuffer.append(stateName);
				}
				else
				{
					localBuffer.append(tr.getSimple().getEndState().getState());
					endstate = "\"" + tr.getSimple().getEndState().getState() + "\"";
				}
			}
			else if(tr.getPop() != null)
			{
				// add pop syntax
				endstate = "context.peakTopStateStack()";
				localBuffer.append("pop(");
				
				if(tr.getPop().getTransition() != null)
				{
					// a pop may have a transition
					localBuffer.append(tr.getPop().getTransition());
				
					// a pop may have arguments to pass to the next state
					if(tr.getPop().getArgument() != null)
					{
						for(int j = 0; j < tr.getPop().getArgument().size(); j++)
						{
							localBuffer.append(",");
							localBuffer.append(tr.getPop().getArgument().get(j).getValue());
						}
					}
				}
				
				// add end of syntax
				localBuffer.append(")");
			}
			else if(tr.getPush() != null)
			{
				// add push syntax
				localBuffer.append("push(");
				
				// add where to push to
				localBuffer.append(tr.getPush().getEndState().getState());
				endstate = "\"" + tr.getPush().getEndState().getState() + "\"";
				
				// add end of syntax
				localBuffer.append(")");

				//TODO: add optional transition before push
			}
			else if(tr.getInternal() != null)
			{
				localBuffer.append("nil");
			}
			
			//add ACTIONS
			localBuffer.append("\n\t\t{");
			
			for(int j = 0; j < tr.getActionOrSendAction().size(); j++)
			{
				Object obj = tr.getActionOrSendAction().get(j);
				
				// only process wrapper if a pop transition
				if(obj instanceof org.jts.jsidl.binding.Action || obj instanceof org.jts.jsidl.binding.SendAction)
				{
					localBuffer.append("\n\t\t\t");
					localBuffer.append(Action.addActionOrSendAction(obj));
				}
				else
				{
					j++;
					obj = tr.getActionOrSendAction().get(j);
					localBuffer.append("\n\t\t\t");
					localBuffer.append(Action.addActionOrSendAction(obj));
					break;
				}
			}

			// Add transition action for notification handling
			if (endstate.length() > 0)
			{
				localBuffer.append("\n\t\t\t");
				localBuffer.append("processNotifications(" + endstate);
				if (tr.getName().startsWith("InternalStateChange_To_"))
				    localBuffer.append(", ie");
				else if (codeType != CodeLines.CodeType.C_PLUS_PLUS)
					localBuffer.append(", null");
				localBuffer.append(");");
			}
			
			//make orderly lines
			if(transitions.size() != 1 && i != transitions.size()-1)
			{
				localBuffer.append("\n\t\t}\n\n");
			}
			else
			{
				localBuffer.append("\n\t\t}\n");
			}
		}
		
		return localBuffer;
	}
	
	/**
	 * Creates SMC transition syntax for a flattened state
	 * @param transitions
	 * @param stateName
	 */
	static StringBuffer addStateDefaultTransitions(List<org.jts.jsidl.binding.DefaultTransition> defaultTransitions, String stateName)
	{
		StringBuffer localBuffer = new StringBuffer();
		
		for(int i = 0; i < defaultTransitions.size(); i++)
		{
			org.jts.jsidl.binding.DefaultTransition tr = defaultTransitions.get(i);
			localBuffer.append("\t\t");
			
			//add TRANSITION NAME
			localBuffer.append("Default");
			
			//add PARAMETERS
			// default transitions don't get parameters

			//add GUARDS
			if(tr.getGuard() != null)
			{
				localBuffer.append("\n\t\t");
				localBuffer.append(Guard.addGuardFromTransition(tr));
			}
			
			//add NEXT STATE
			localBuffer.append("\n\t\t");
			
			if(tr.getSimple() != null)
			{
				//add loop-back transition if no end state is specified
				if(tr.getSimple().getEndState() == null)
				{
					localBuffer.append(stateName);
				}
				else
				{
					localBuffer.append(tr.getSimple().getEndState().getState());
				}
			}
			else if(tr.getPop() != null)
			{
				// add pop syntax
				localBuffer.append("pop(");
				
				if(tr.getPop().getTransition() != null)
				{
				
					// a pop may have a transition
					localBuffer.append(tr.getPop().getTransition());
					
					// a pop may have arguments to pass to the next state
					if(tr.getPop().getArgument() != null)
					{
						for(int j = 0; j < tr.getPop().getArgument().size(); j++)
						{
							localBuffer.append(",");
							localBuffer.append(tr.getPop().getArgument().get(j).getValue());
						}
					}
				}
				
				// add end of syntax
				localBuffer.append(")");
			}
			else if(tr.getPush() != null)
			{
				// add push syntax
				localBuffer.append("push(");
				
				// add where to push to
				localBuffer.append(tr.getPush().getEndState().getState());
				
				// add end of syntax
				localBuffer.append(")");

				//TODO: add optional transition before push
			}
			else if(tr.getInternal() != null)
			{
				localBuffer.append("nil");
			}
			
			//add ACTIONS
			localBuffer.append("\n\t\t{");
			
			for(int j = 0; j < tr.getActionOrSendAction().size(); j++)
			{
				Object obj = tr.getActionOrSendAction().get(j);
				
				// only process wrapper if a pop transition
				if(obj instanceof org.jts.jsidl.binding.Action || obj instanceof org.jts.jsidl.binding.SendAction)
				{
				}
				else
				{
					j++;
					obj = tr.getActionOrSendAction().get(j);
					localBuffer.append("\n\t\t\t");
					localBuffer.append(Action.addActionOrSendAction(obj));
					break;
				}
				
				localBuffer.append("\n\t\t\t");
				localBuffer.append(Action.addActionOrSendAction(obj));
			}
			
			//make orderly lines
			if(defaultTransitions.size() != 1 && i != defaultTransitions.size()-1)
			{
				localBuffer.append("\n\t\t}\n\n");
			}
			else
			{
				localBuffer.append("\n\t\t}\n");
			}
		}
		
		return localBuffer;
	}
	
	/**
	 * Determines if the end states of two simple transitions are different
	 * @param simple1
	 * @param simple2
	 * @return
	 */
	static boolean isDifferetSimpleTransition(org.jts.jsidl.binding.Simple simple1, org.jts.jsidl.binding.Simple simple2)
	{
		// check for unique endState names
		if(simple1.getEndState().getState().compareTo(simple2.getEndState().getState()) == 0)
		{
			return false;
		}
		
		return true;
	}
	
	/**
	 * Determines if the end states of two simple transitions are the same
	 * @param simple1
	 * @param simple2
	 * @return
	 */
	static boolean isSameSimpleTransition(org.jts.jsidl.binding.Simple simple1, org.jts.jsidl.binding.Simple simple2)
	{
		return !isDifferetSimpleTransition(simple1, simple2);
	}
	
	/**
	 * Determines if the names of two pop transitions are different
	 * @param pop1
	 * @param pop2
	 * @return
	 */
	static boolean isDifferentPopTransition(org.jts.jsidl.binding.Pop pop1, org.jts.jsidl.binding.Pop pop2)
	{
		// check for unique transition names in the pop transitions 
		if(pop1.getTransition().compareTo(pop2.getTransition()) == 0)
		{
			return false;
		}
		
		//TODO: compare argument Lists?
		
		return true;
	}
	
	/**
	 * Determines if the names of two pop transitions are the same
	 * @param pop1
	 * @param pop2
	 * @return
	 */
	static boolean isSamePopTransition(org.jts.jsidl.binding.Pop pop1, org.jts.jsidl.binding.Pop pop2)
	{
		return !isDifferentPopTransition(pop1, pop2);
	}
	
	/**
	 * Determines if two push transitions are different based on their end states and if their simple simple transitions are the same
	 * @param push1
	 * @param push2
	 * @return
	 */
	static boolean isDifferentPushTransition(org.jts.jsidl.binding.Push push1, org.jts.jsidl.binding.Push push2)
	{
		// check for unique endState names 
		if(isSameEndState(push1.getEndState(), push2.getEndState()))
		{
			// push transitions may have simple transitions defined (optional)
			if(push1.getSimple() != null && push2.getSimple() != null)
			{
				if(isSameSimpleTransition(push1.getSimple(),push2.getSimple()))
				{
					// if end states are the same and their simple transitions are the same, push transition is not unique
					return false; 
				}
			}
			else if(push1.getSimple() == null && push2.getSimple() == null)
			{
				// if endStates are the same and there are no simple transitions, push transition is not unique
				return false;
			}
			else
			{
				// if endStates are the same but one push has simple transition but the other push doesn't, push transition is unique
				return true;
			}
		}
		
		return true;
	}
	
	/**
	 * Determines if two push transitions are the same based on their end states and if their simple simple transitions are the same
	 * @param push1
	 * @param push2
	 * @return
	 */
	static boolean isSamePushTransition(org.jts.jsidl.binding.Push push1, org.jts.jsidl.binding.Push push2)
	{
		return !isDifferentPushTransition(push1, push2);
	}
	
	/**
	 * Determines if two end states are the different by comparing their names
	 * @param end1
	 * @param end2
	 * @return
	 */
	static boolean isDifferentEndState(org.jts.jsidl.binding.EndState end1, org.jts.jsidl.binding.EndState end2)
	{
		// check for unique enState names
		if(end1.getState().compareTo(end2.getState()) == 0)
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Determines if two end states are the same by comparing their names
	 * @param end1
	 * @param end2
	 * @return
	 */
	static boolean isSameEndState(org.jts.jsidl.binding.EndState end1, org.jts.jsidl.binding.EndState end2)
	{
		return !isDifferentEndState(end1, end2);
	}
	
	/**
	 * Determines if the two transitions or default transitions have different names
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	static boolean isDifferentTransitionName(Object obj1, Object obj2)
	{
		if(obj1 instanceof org.jts.jsidl.binding.Transition && obj2 instanceof org.jts.jsidl.binding.Transition)
		{
			org.jts.jsidl.binding.Transition transition1 = (org.jts.jsidl.binding.Transition)obj1;
			org.jts.jsidl.binding.Transition transition2 = (org.jts.jsidl.binding.Transition)obj2;
			
			// Strip any namepacing, e.g. parentState.transitionName
			String name1 = transition1.getName().substring(transition1.getName().lastIndexOf(".") + 1);
			String name2 = transition2.getName().substring(transition2.getName().lastIndexOf(".") + 1);			
			
			if(name1.compareTo(name2) == 0 )
			{
				return false;
			}
					
		}
		else if(obj1 instanceof org.jts.jsidl.binding.DefaultTransition && obj2 instanceof org.jts.jsidl.binding.DefaultTransition)
		{
			// default transitions don't have a name
			
			return false;
		}
		
		return true;		
	}
	
	/**
	 * Determines if the two transitions or default transitions have the same names
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	static boolean isSameTransitionName(Object obj1, Object obj2)
	{
		return !isDifferentTransitionName(obj1, obj2);
	}
	
	/**
	 * Determines if two transitions or default transitions are unique by comparing them based on their type(simple, pop, push, etc)
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	static boolean isDifferentTransitionType(Object obj1, Object obj2)
	{
		if(obj1 instanceof org.jts.jsidl.binding.Transition)
		{
			org.jts.jsidl.binding.Transition transition1 = (org.jts.jsidl.binding.Transition)obj1;
			org.jts.jsidl.binding.Transition transition2 = (org.jts.jsidl.binding.Transition)obj2;
			
			if(transition1.getSimple() != null && transition2.getSimple() != null)
			{
				if(isSameSimpleTransition(transition1.getSimple(), transition2.getSimple()))
				{
					return false;
				}
			}
			else if(transition1.getPop() != null && transition2.getPop() != null)
			{
				if(isSamePopTransition(transition1.getPop(), transition2.getPop()))
				{
					return false;
				}
			}
			else if(transition1.getPush() != null && transition2.getPush() != null)
			{
				if(isSamePushTransition(transition1.getPush(), transition2.getPush()))
				{
					return false;
				}
			}
			//otherwise it is a combination of transition types (i.e simple and pop) and they are unique transitions
		}
		else if(obj1 instanceof org.jts.jsidl.binding.DefaultTransition)
		{
			org.jts.jsidl.binding.DefaultTransition transition1 = (org.jts.jsidl.binding.DefaultTransition)obj1;
			org.jts.jsidl.binding.DefaultTransition transition2 = (org.jts.jsidl.binding.DefaultTransition)obj2;
			
			if(transition1.getSimple() != null && transition2.getSimple() != null)
			{
				if(isSameSimpleTransition(transition1.getSimple(), transition2.getSimple()))
				{
					return false;
				}
			}
			else if(transition1.getPop() != null && transition2.getPop() != null)
			{
				if(isSamePopTransition(transition1.getPop(), transition2.getPop()))
				{
					return false;
				}
			}
			else if(transition1.getPush() != null && transition2.getPush() != null)
			{
				if(isSamePushTransition(transition1.getPush(), transition2.getPush()))
				{
					return false;
				}
			}
			//otherwise it is a combination of transition types (i.e simple and pop) and they are different transition types
		}
		
		return true;
	}

	/**
	 * Determines if two transitions or default transitions are the same
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	static boolean isSameTransitionType(Object obj1, Object obj2)
	{
		return !isDifferentTransitionType(obj1, obj2);
	}
	
	/**
	 * Determines if the two transitions or default transitions have different guards
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	static boolean isDifferentTransitionGuard(Object obj1, Object obj2)
	{
		if(obj1 instanceof org.jts.jsidl.binding.Transition && obj2 instanceof org.jts.jsidl.binding.Transition)
		{
			org.jts.jsidl.binding.Transition transition1 = (org.jts.jsidl.binding.Transition)obj1;
			org.jts.jsidl.binding.Transition transition2 = (org.jts.jsidl.binding.Transition)obj2;
			
			if(org.jts.codegenerator.protocolBehavior.Guard.isSameGuard(transition1.getGuard(), transition2.getGuard()))
			{
				return false;
			}		
		}
		else if(obj1 instanceof org.jts.jsidl.binding.DefaultTransition && obj2 instanceof org.jts.jsidl.binding.DefaultTransition)
		{
			org.jts.jsidl.binding.DefaultTransition defaultTransition1 = (org.jts.jsidl.binding.DefaultTransition)obj1;
			org.jts.jsidl.binding.DefaultTransition defaultTransition2 = (org.jts.jsidl.binding.DefaultTransition)obj2;
			
			if(org.jts.codegenerator.protocolBehavior.Guard.isSameGuard(defaultTransition1.getGuard(), defaultTransition2.getGuard()))
			{
				return false;
			}
		}
		
		return true;		
	}
	
	/**
	 * Determines if the two transitions or default transitions have the same guards
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	static boolean isSameTransitionGuard(Object obj1, Object obj2)
	{
		return !isDifferentTransitionGuard(obj1, obj2);
	}	
	
	/**
	 * Determines if two transitions have the same name and parameter lists 
	 * these two properties differentiate transitions and this is all that smc checks when looking for a transition to evaluate
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	static boolean isSameTransitionNameAndParameters(Object obj1, Object obj2)
	{
		if(obj1 instanceof org.jts.jsidl.binding.Transition && obj2 instanceof org.jts.jsidl.binding.Transition)
		{
			org.jts.jsidl.binding.Transition transition1 = (org.jts.jsidl.binding.Transition)obj1;
			org.jts.jsidl.binding.Transition transition2 = (org.jts.jsidl.binding.Transition)obj2;
			
			// the two transitions are the same if their names are the same and their parameters are the same
			// the end state, type and guards don't define a transition, they define what happens after the transition is reached
			if(isSameTransitionName(transition1, transition2) &&
					Parameter.isSameParameterList(transition1.getParameter(),transition2.getParameter()))
			{
				return true;
			}
					
		}
		else if(obj1 instanceof org.jts.jsidl.binding.DefaultTransition && obj2 instanceof org.jts.jsidl.binding.DefaultTransition)
		{
			//org.jts.jsidl.binding.DefaultTransition defaultTransition1 = (org.jts.jsidl.binding.DefaultTransition)obj1;
			//org.jts.jsidl.binding.DefaultTransition defaultTransition2 = (org.jts.jsidl.binding.DefaultTransition)obj2;
			
			return true;
			
			//TODO: add case? (default transitions don't have names or parameters so its likely that they can all be treated the same)
		}
		
		return false;
	}
	
	/**
	 * Determines if transition is ok to add to the state by checking for unguarded transitions and if the transition is a duplicate
	 * @param obj
	 * @param state
	 * @return
	 */
	static boolean isTransitionOkToAdd(Object obj, GenericState state)
	{
		if(obj instanceof org.jts.jsidl.binding.Transition)
		{
			org.jts.jsidl.binding.Transition transition = (org.jts.jsidl.binding.Transition)obj;
			
			for(int i = 0; i < state.getTransition().size(); i++)
			{
				org.jts.jsidl.binding.Transition stateTransition = state.getTransition().get(i);
				
				// only add the transition if no unguarded transitions of the same name and parameters that already exist in the state
				// only concerned with adding of guarded transitions after unguarded transitions
				if(transition.getGuard() != null &&
						stateTransition.getGuard() == null &&
						isSameTransitionNameAndParameters(transition, stateTransition))
				{
					return false;
				}
				
				// we also need to make sure it is not the same transition
				if (isSameTransitionNameAndParameters(transition, stateTransition) && 
					     isSameTransitionGuard(transition, stateTransition))
				{
					return false;
				}
			}
			
		}
		else if(obj instanceof org.jts.jsidl.binding.DefaultTransition)
		{
			org.jts.jsidl.binding.DefaultTransition defaultTransition = (org.jts.jsidl.binding.DefaultTransition)obj;
			
			for(int i = 0; i < state.getDefaultTransition().size(); i++)
			{
				org.jts.jsidl.binding.DefaultTransition stateDefaultTransition = state.getDefaultTransition().get(i);
				
				// only add the transition if no unguarded transitions of the same name and parameters that already exist in the state
				// only concerned with adding of guarded transitions after unguarded transitions
				if(defaultTransition.getGuard() != null &&
						stateDefaultTransition.getGuard() == null &&
						isSameTransitionNameAndParameters(defaultTransition, stateDefaultTransition))
				{
					return false;
				}
				
				// we also need to make sure it is not the same transition
				if (isSameTransitionNameAndParameters(defaultTransition, stateDefaultTransition) && 
					     isSameTransitionGuard(defaultTransition, stateDefaultTransition))
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Adds a transition to the state if it there is not matching unguarded transition in the state
	 * @param obj
	 * @param state
	 */
	public static void addTransitionToState(Object obj, GenericState state)
	{
		if(obj instanceof org.jts.jsidl.binding.Transition)
		{
			org.jts.jsidl.binding.Transition transition = (org.jts.jsidl.binding.Transition)obj;
					
			if(isTransitionOkToAdd(transition, state))
			{
				state.getTransition().add(transition);	
			}
		}
		else if(obj instanceof org.jts.jsidl.binding.DefaultTransition)
		{
			org.jts.jsidl.binding.DefaultTransition defaultTransition = (org.jts.jsidl.binding.DefaultTransition)obj;
			
			//TODO: check this
			if(isTransitionOkToAdd(defaultTransition, state))
			{
				state.getDefaultTransition().add(defaultTransition);	
			}
		}
	}
	
	/**
	 * Returns a clone of the transition
	 * @param inputTransition
	 * @return
	 */
	static org.jts.jsidl.binding.Transition cloneTransition(org.jts.jsidl.binding.Transition inputTransition)
	{
		 org.jts.jsidl.binding.Transition returnTransition = new  org.jts.jsidl.binding.Transition();
		 
		 // clone actions
		 if(inputTransition.getActionOrSendAction() != null)
		 {			 
			 for(int i = 0; i < inputTransition.getActionOrSendAction().size(); i++)
			 {
				 returnTransition.getActionOrSendAction().add(Action.cloneActionOrSendAction(inputTransition.getActionOrSendAction().get(i)));
			 }
		 }
		 
		 // clone guards
		 if(inputTransition.getGuard() != null)
		 {
			 returnTransition.setGuard(Guard.cloneGuard(inputTransition.getGuard()));
		 }
		 
		 // clone internal transition
		 if(inputTransition.getInternal() != null)
		 {
			 returnTransition.setInternal(inputTransition.getInternal());
		 }
		 
		 // clone transition Name
		 returnTransition.setName(inputTransition.getName());
		 
		 // clone parameter list
		 for(int i = 0; i < inputTransition.getParameter().size(); i++)
		 {
			 returnTransition.getParameter().add(i, Parameter.cloneParameter(inputTransition.getParameter().get(i)));
		 }
		 
		 // clone pop transition
		 if(inputTransition.getPop() != null)
		 {			 
			 returnTransition.setPop(Transition.clonePopTransition(inputTransition.getPop()));
		 }
		 
		 // clone push transition
		 if(inputTransition.getPush() != null)
		 {			 
			 returnTransition.setPush(Transition.clonePushTransition(inputTransition.getPush()));
		 }
		 
		 // clone simple transition
		 if(inputTransition.getSimple() != null)
		 {
			 returnTransition.setSimple(Transition.cloneSimpleTransition(inputTransition.getSimple()));
		 }
		 
		 return returnTransition;
	}
	
	/**
	 * Returns a clone of the default transition
	 * @param inputDefaultTransition
	 * @return
	 */
	static org.jts.jsidl.binding.DefaultTransition cloneDefaultTransition(org.jts.jsidl.binding.DefaultTransition inputDefaultTransition)
	{
		 org.jts.jsidl.binding.DefaultTransition returnDefaultTransition = new  org.jts.jsidl.binding.DefaultTransition();
		 
		 // clone actions
		 if(inputDefaultTransition.getActionOrSendAction() != null)
		 {			 
			 for(int i = 0; i < inputDefaultTransition.getActionOrSendAction().size(); i++)
			 {
				 returnDefaultTransition.getActionOrSendAction().add(Action.cloneActionOrSendAction(inputDefaultTransition.getActionOrSendAction().get(i)));
			 }
		 }
		 
		 // clone guards
		 if(inputDefaultTransition.getGuard() != null)
		 {
			 returnDefaultTransition.setGuard(Guard.cloneGuard(inputDefaultTransition.getGuard()));
		 }
		 
		 // clone internal transition
		 if(inputDefaultTransition.getInternal() != null)
		 {
			 returnDefaultTransition.setInternal(inputDefaultTransition.getInternal());
		 }
		 
		 // default transition doesn't have a name
		 // clone transition Name 
//		 returnDefaultTransition.setName(inputDefaultTransition.getName());
		 
		 // default transition doesn't have parameters
		 // clone parameter list
//		 for(int i = 0; i < inputDefaultTransition.getParameter().size(); i++)
//		 {
//			 returnDefaultTransition.getParameter().add(i, Parameter.cloneParameter(inputDefaultTransition.getParameter().get(i)));
//		 }
		 
		 // clone pop transition
		 if(inputDefaultTransition.getPop() != null)
		 {			 
			 returnDefaultTransition.setPop(Transition.clonePopTransition(inputDefaultTransition.getPop()));
		 }
		 
		 // clone push transition
		 if(inputDefaultTransition.getPush() != null)
		 {			 
			 returnDefaultTransition.setPush(Transition.clonePushTransition(inputDefaultTransition.getPush()));
		 }
		 
		 // clone simple transition
		 if(inputDefaultTransition.getSimple() != null)
		 {
			 returnDefaultTransition.setSimple(Transition.cloneSimpleTransition(inputDefaultTransition.getSimple()));
		 }
		 
		 return returnDefaultTransition;
	}
	
	/**
	 * Returns a clone of the pop transition
	 * @param inputPopTransition
	 * @return
	 */
	static org.jts.jsidl.binding.Pop clonePopTransition(org.jts.jsidl.binding.Pop inputPopTransition)
	{
		 org.jts.jsidl.binding.Pop returnPopTransition = new org.jts.jsidl.binding.Pop();
		 
		 // set pop name
		 if(inputPopTransition.getTransition() != null)
		 {
			 returnPopTransition.setTransition(inputPopTransition.getTransition());
		 }
		 
		 // set arguments
		 if(inputPopTransition.getArgument() != null)
		 {
			 for(int i = 0; i < inputPopTransition.getArgument().size(); i++)
			 {
				 org.jts.jsidl.binding.Argument inputArguement = inputPopTransition.getArgument().get(i);
				 org.jts.jsidl.binding.Argument returnArguement = new org.jts.jsidl.binding.Argument();
				 
				 returnArguement.setValue(inputArguement.getValue());
				 
				 returnPopTransition.getArgument().add(returnArguement);
			 }
		 }
		 
		 return returnPopTransition;
	}
	
	/**
	 * Returns a clone of the push transition
	 * @param inputPushTransition
	 * @return
	 */
	static org.jts.jsidl.binding.Push clonePushTransition(org.jts.jsidl.binding.Push inputPushTransition)
	{
		 org.jts.jsidl.binding.Push returnPush = new org.jts.jsidl.binding.Push();
		 
		 if(inputPushTransition.getEndState() != null)
		 {
			 // make new end state
			 org.jts.jsidl.binding.EndState returnEndState = new org.jts.jsidl.binding.EndState();
			 
			 // copy end state string
			 returnEndState.setState(inputPushTransition.getEndState().getState());
			 
			 // set end state to returnPush
			 returnPush.setEndState(returnEndState);
		 }
		 
		 if(inputPushTransition.getSimple() != null)
		 {
			 org.jts.jsidl.binding.Simple returnSimple = new org.jts.jsidl.binding.Simple();
			 org.jts.jsidl.binding.EndState returnEndState = new org.jts.jsidl.binding.EndState();
			 
			 returnSimple.setEndState(returnEndState);
			 
			 returnEndState.setState(inputPushTransition.getSimple().getEndState().getState());
			 
			 returnPush.setSimple(returnSimple);
		 }
		 
		 return returnPush;
	}
	
	/**
	 * Returns a clone of the simple transition
	 * @param inputSimpleTranstition
	 * @return
	 */
	static org.jts.jsidl.binding.Simple cloneSimpleTransition(org.jts.jsidl.binding.Simple inputSimpleTranstition)
	{
		 org.jts.jsidl.binding.Simple returnSimple = new org.jts.jsidl.binding.Simple();
		 org.jts.jsidl.binding.EndState returnEndState = new org.jts.jsidl.binding.EndState();
		 
		 if(inputSimpleTranstition.getEndState() != null)
		 {
			 if(inputSimpleTranstition.getEndState().getState() != null)
			 {
				 returnEndState.setState(inputSimpleTranstition.getEndState().getState());
			 }
		 }

		 returnSimple.setEndState(returnEndState);
		 
		 return returnSimple;
	}
	
	/**
	 * Appends 'Transition' to the transition's name
	 * @param transition
	 */
	static void appendTransitionName(Object obj)
	{
		if(obj instanceof org.jts.jsidl.binding.Transition)
		{
			org.jts.jsidl.binding.Transition transition = (org.jts.jsidl.binding.Transition) obj;
			
			if((transition.getName() != null) && !transition.getName().endsWith("Transition"))
			{
				transition.setName(transition.getName().substring(transition.getName().lastIndexOf(".")+1) + "Transition");
			}
			
			if(transition.getPop() != null)
			{
				if((transition.getPop().getTransition() != null)  && !transition.getPop().getTransition().endsWith("Transition"))
				{
					transition.getPop().setTransition(transition.getPop().getTransition() + "Transition");
				}
			}
		}
		else if(obj instanceof org.jts.jsidl.binding.DefaultTransition)
		{			
			org.jts.jsidl.binding.DefaultTransition defaultTransition = (org.jts.jsidl.binding.DefaultTransition) obj;
		
			// default transitions don't have a transition name
			
			if(defaultTransition.getPop() != null)
			{
				if(( defaultTransition.getPop().getTransition() != null)  && !defaultTransition.getPop().getTransition().endsWith("Transition"))
				{
					defaultTransition.getPop().setTransition(defaultTransition.getPop().getTransition() + "Transition");
				}
			}
		}
	}
	
	public static void getEndStateNames(org.jts.jsidl.binding.ProtocolBehavior pBehavior, ArrayList<String> names)
	{	
		for(int i = 0; i < pBehavior.getStateMachine().size(); i++)
		{
			org.jts.jsidl.binding.StateMachine stateMachine = pBehavior.getStateMachine().get(i);
			org.jts.codegenerator.protocolBehavior.State.fixStateNames(stateMachine);
			
			for(org.jts.jsidl.binding.State st:stateMachine.getState())
			{
				org.jts.codegenerator.protocolBehavior.State.getAllStateNames(st, names);
			}
		}
				
	}
	
	public static void getTransitionNames(org.jts.jsidl.binding.ProtocolBehavior pb, ArrayList<String> transitionNames)
	{
		ArrayList<org.jts.jsidl.binding.Transition> transitions = new ArrayList<org.jts.jsidl.binding.Transition>();
		
		for(org.jts.jsidl.binding.StateMachine sm:pb.getStateMachine())
		{
			org.jts.codegenerator.protocolBehavior.Transition.getTransitions(sm, transitions);
		}
		
		for(org.jts.jsidl.binding.Transition tr:transitions)
		{
			transitionNames.add(tr.getName());
		}
		
	}

	/**
	 * Get all transitions within a protocolBehavior
	 * @param pb
	 * @param transitions
	 */
	public static void getTransitions(org.jts.jsidl.binding.ProtocolBehavior pb, ArrayList<org.jts.jsidl.binding.Transition> transitions, Boolean defaultOnly)
	{
		for(org.jts.jsidl.binding.StateMachine sm:pb.getStateMachine())
		{
			org.jts.codegenerator.protocolBehavior.Transition.getTransitions(sm, transitions, defaultOnly);
		}
	}
	
	/**
	 * Get all transitions within a stateMachine
	 * @param sm
	 * @param transitions
	 */
	public static void getTransitions(org.jts.jsidl.binding.StateMachine sm, ArrayList<org.jts.jsidl.binding.Transition> transitions, Boolean defaultOnly)
	{
		for(org.jts.jsidl.binding.State st:sm.getState())
		{
		    // Behavior here is dependent on the defaultOnly flag.  If the flag is set, we only get transitions
		    // from the Internally_Generated state.  Otherwise, we get transitions from everything BUT the
		    // internally generated state....
		    if ((defaultOnly && (st.getName().compareTo("Internally_Generated_State_DO_NOT_USE") == 0)) ||
		        (!defaultOnly && (st.getName().compareTo("Internally_Generated_State_DO_NOT_USE") != 0)) )
		    {
			org.jts.codegenerator.protocolBehavior.Transition.getTransitions(st, transitions);
		}
	}
	}
	
	/**
	 * Get all transitions within a state
	 * @param obj casted to State
	 * @param transitions
	 */
	public static void getTransitions(Object obj, ArrayList<org.jts.jsidl.binding.Transition> transitions)
	{
		if(obj instanceof org.jts.jsidl.binding.State)
		{
			org.jts.jsidl.binding.State st = (org.jts.jsidl.binding.State) obj;
			
			for(org.jts.jsidl.binding.Transition tr:st.getTransition())
			{
				transitions.add(tr);
			}
			
			for(org.jts.jsidl.binding.State subSt:st.getState())
			{
				org.jts.codegenerator.protocolBehavior.Transition.getTransitions(subSt, transitions);
			}
			
			org.jts.codegenerator.protocolBehavior.Transition.getTransitions(st.getDefaultState(), transitions);
		}
		else if(obj instanceof org.jts.jsidl.binding.DefaultState)
		{
			org.jts.jsidl.binding.DefaultState dst = (org.jts.jsidl.binding.DefaultState) obj;
			
			for(org.jts.jsidl.binding.Transition tr:dst.getTransition())
			{
				transitions.add(tr);
			}
		}
	}

	/*
         * @param sm State Machine
         * @param sd Service Definition
         * @param messageTransitions
         * @param codeType for language specific synatax
         * @param serviceName for generating logger in catch block.
         */
	public static void generateTransitionFunctions(org.jts.jsidl.binding.StateMachine sm, org.jts.jsidl.binding.ServiceDef sd, StringBuffer messageTransitions, CodeLines.CodeType codeType, Boolean defaultOnly )
	{
		// Get a list of all transitions in the state machine
		ArrayList<org.jts.jsidl.binding.Transition> transitions = new ArrayList<org.jts.jsidl.binding.Transition>();
		getTransitions(sm, transitions, defaultOnly);
		
		// For each transition, add a handler.
		for(org.jts.jsidl.binding.Transition tr : transitions)
		{
			generateTransitionFunctions(tr, sm, sd, messageTransitions, codeType);
		}
	}

        /*
         * @param tm Transition
         * @param sm State Machine
         * @param sd Service Definition
         * @param messageTransitions
         * @param codeType for language specific synatax
         */
	public static void generateTransitionFunctions(org.jts.jsidl.binding.Transition tr, org.jts.jsidl.binding.StateMachine sm, org.jts.jsidl.binding.ServiceDef sd, StringBuffer messageTransitions, CodeLines.CodeType codeType)
	{
	    StringBuilder msg_type = new StringBuilder();
	    StringBuilder msg_value = new StringBuilder();
	    		
		// Special!  If this is a ReceiveTransition but has no arguments, we have no idea
		// of what the trigger actually is.  Don't create it.
		if (( tr.getName().compareTo("ReceiveTransition") == 0) && tr.getParameter().isEmpty())
			return;
		
		// set-up the try/catch block
		messageTransitions.append("\t\t\ttry"); 
		messageTransitions.append(System.getProperty("line.separator"));		
		messageTransitions.append("\t\t\t{");
		messageTransitions.append(System.getProperty("line.separator"));

                if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
                {
                    // switch based on the transition trigger
                    messageTransitions.append("\t\t\t\tif ((done == false) && ie->getName().compare(\"").append(getTriggerName(tr, sd)).append("\") == 0 && (ie->getSource().compare(\"" + sm.getName() + "\") != 0))");
                    messageTransitions.append(System.getProperty("line.separator"));
                    messageTransitions.append("\t\t\t\t{");
                    messageTransitions.append(System.getProperty("line.separator"));

                    // Cast the generic internal event to the appropriate type, but only if the event is a Receive
                    // or the transition itself has parameters...
                    if (!getTriggerName(tr, sd).startsWith("InternalStateChange_To_") || !(tr.getParameter().size() == 1))
                    {
						messageTransitions.append("\t\t\t\t\t" + getTriggerName(tr, sd) + "* casted_ie = (" + getTriggerName(tr, sd) + "*) ie;");
                    messageTransitions.append(System.getProperty("line.separator"));
					}

                    // SPECIAL!!!!  If this is a receive event, it contains an embedded message.
                    // pull the ID as the first 2 bytes of the payload then add a check to make
                    // sure the ID matches the transition arguments...
                    if (getTriggerName(tr, sd).compareTo("Receive") == 0)
                    {
                        messageTransitions.append("\t\t\t\t\tunsigned short id = *((unsigned short*) casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());");
						messageTransitions.append(System.getProperty("line.separator"));
						messageTransitions.append("\t\t\t\t\tid = JSIDL_v_1_0::correctEndianness(id);");
                        messageTransitions.append(System.getProperty("line.separator"));

                            findInputMessage(tr, msg_type, msg_value, sd);
                            if (msg_type.length() != 0)
                            {
                                    messageTransitions.append("\t\t\t\t\tif ( id == ").append(msg_type.toString()).append("::ID)");
                                    messageTransitions.append(System.getProperty("line.separator"));
                                    messageTransitions.append("\t\t\t\t\t{");
                                    messageTransitions.append(System.getProperty("line.separator"));
                                    messageTransitions.append("\t\t\t\t\t\t").append(msg_type.toString()).append(" ").append(msg_value.toString()).append(";");
                                    messageTransitions.append(System.getProperty("line.separator"));
                                    messageTransitions.append("\t\t\t\t\t\t").append(msg_value.toString()).append(".decode(casted_ie->getBody()->getReceiveRec()->getMessagePayload()->getData());");
                                    messageTransitions.append(System.getProperty("line.separator"));
                            }
                    }

                    // Instantiate the other parameters
                    declareParameters(tr, sd, messageTransitions, codeType);

                    // Call the actual transition function, passing in the parameter list.
                    messageTransitions.append("\t\t\t\t\t\tp").append(sm.getName()).append("->context->").append(tr.getName()).append("(");
                    addParameterCallStack(tr.getParameter(), messageTransitions);
                    messageTransitions.append(");");
                    messageTransitions.append(System.getProperty("line.separator"));

                    // If the call is successful, stop checking for other transitions.  Goto the 'done' label
                    // for clean-up and exit.
                    messageTransitions.append("\t\t\t\t\t\tdone = true;");
                    messageTransitions.append(System.getProperty("line.separator"));

                    // If this transition is triggered on an input message, we have an additional level
                    // of nesting to pop.
                    if (msg_type.length() != 0)
                    {
                            messageTransitions.append("\t\t\t\t\t}");
                            messageTransitions.append(System.getProperty("line.separator"));
                    }

                    // Pop the rest of the way, and add an empty catch.
                    messageTransitions.append("\t\t\t\t}");
                    messageTransitions.append(System.getProperty("line.separator"));
                    messageTransitions.append("\t\t\t} catch (...) {}");
                    messageTransitions.append(System.getProperty("line.separator"));
                    messageTransitions.append(System.getProperty("line.separator"));
                }
                else if(codeType == CodeLines.CodeType.JAVA)
                {
                    // switch based on the transition trigger
                    messageTransitions.append("\t\t\t\tif ((done == false) && ie.getName().compareTo(\"").append(getTriggerName(tr, sd)).append("\") == 0 && (ie.getSource().compareTo(\"" + sm.getName() + "\") != 0))");
                    messageTransitions.append(System.getProperty("line.separator"));
                    messageTransitions.append("\t\t\t\t{");
                    messageTransitions.append(System.getProperty("line.separator"));

                    // Cast the generic internal event to the appropriate type, but only if the event is a Receive
                    // or the transition itself has parameters...
                    if (!getTriggerName(tr, sd).startsWith("InternalStateChange_To_") || !(tr.getParameter().size() == 1))
                    {
                    messageTransitions.append("\t\t\t\t\t").append(getTriggerName(tr, sd)).append(" casted_ie = (").append(getTriggerName(tr, sd)).append(") ie;");
                    messageTransitions.append(System.getProperty("line.separator"));
					}

                    // SPECIAL!!!!  If this is a receive event, it contains an embedded message.
                    // pull the ID as the first 2 bytes of the payload then add a check to make
                    // sure the ID matches the transition arguments...
                    if (getTriggerName(tr, sd).compareTo("Receive") == 0)
                    {
                        messageTransitions.append("\t\t\t\t\tint id = (int) (casted_ie.getBody().getReceiveRec().getMessagePayload().getData().getShort() & 0xffff);");
                        messageTransitions.append(System.getProperty("line.separator"));

                            findInputMessage(tr, msg_type, msg_value, sd);
                            if (msg_type.length() != 0)
                            {
                                    messageTransitions.append("\t\t\t\t\tif ( id == ").append(msg_type.toString()).append(".ID)");
                                    messageTransitions.append(System.getProperty("line.separator"));
                                    messageTransitions.append("\t\t\t\t\t{");
                                    messageTransitions.append(System.getProperty("line.separator"));
                                    messageTransitions.append("\t\t\t\t\t\t").append(msg_type.toString()).append(" ").append(msg_value.toString()).append(" = new ").append(msg_type.toString()).append("();");
                                    messageTransitions.append(System.getProperty("line.separator"));
                                    messageTransitions.append("\t\t\t\t\t\t").append(msg_value.toString()).append(".decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), 0);");
                                    messageTransitions.append(System.getProperty("line.separator"));
                            }
                    }

                    // Instantiate the other parameters
                    declareParameters(tr, sd, messageTransitions, codeType);

                    // Call the actual transition function, passing in the parameter list.
                    messageTransitions.append("\t\t\t\t\t\tp").append(sm.getName()).append(".context.").append(tr.getName()).append("(");
                    addParameterCallStack(tr.getParameter(), messageTransitions);
                    messageTransitions.append(");");
                    messageTransitions.append(System.getProperty("line.separator"));

                    // If the call is successful, stop checking for other transitions.  Goto the 'done' label
                    // for clean-up and exit.
                    messageTransitions.append("\t\t\t\t\t\tdone = true;");
                    messageTransitions.append(System.getProperty("line.separator"));

                    // If this transition is triggered on an input message, we have an additional level
                    // of nesting to pop.
                    if (msg_type.length() != 0)
                    {
                            messageTransitions.append("\t\t\t\t\t}");
                            messageTransitions.append(System.getProperty("line.separator"));
                    }

                    // Pop the rest of the way, and add an empty catch.
                    messageTransitions.append("\t\t\t\t}");
                    messageTransitions.append(System.getProperty("line.separator"));
                    messageTransitions.append("\t\t\t}").append(System.getProperty("line.separator"));
                    messageTransitions.append("\t\t\tcatch (Exception e) {}").append(System.getProperty("line.separator"));
                    messageTransitions.append(System.getProperty("line.separator"));
                }
                else if (codeType == CodeLines.CodeType.C_SHARP)
                {
                     // switch based on the transition trigger
                    messageTransitions.append("\t\t\t\tif ((!done) && ie.getName().CompareTo(\"").append(getTriggerName(tr, sd)).append("\") == 0 && (ie.getSource().CompareTo(\"" + sm.getName() + "\") != 0))");
                    messageTransitions.append(System.getProperty("line.separator"));
                    messageTransitions.append("\t\t\t\t{");
                    messageTransitions.append(System.getProperty("line.separator"));

                    // Cast the generic internal event to the appropriate type, but only if the event is a Receive
                    // or the transition itself has parameters...
                    if (!getTriggerName(tr, sd).startsWith("InternalStateChange_To_") || !(tr.getParameter().size() == 1))
                    {
                    messageTransitions.append("\t\t\t\t\t").append(getTriggerName(tr, sd)).append(" casted_ie = (").append(getTriggerName(tr, sd)).append(") ie;");
                    messageTransitions.append(System.getProperty("line.separator"));
					}

                    // SPECIAL!!!!  If this is a receive event, it contains an embedded message.
                    // pull the ID as the first 2 bytes of the payload then add a check to make
                    // sure the ID matches the transition arguments...
                    if (getTriggerName(tr, sd).compareTo("Receive") == 0)
                    {
                        messageTransitions.append("\t\t\t\t\tint pos = 0;").append(System.getProperty("line.separator"));
                        messageTransitions.append("\t\t\t\t\tushort id = BitConverter.ToUInt16(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);");
                        messageTransitions.append(System.getProperty("line.separator"));

                            findInputMessage(tr, msg_type, msg_value, sd);
                            if (msg_type.length() != 0)
                            {
                                    messageTransitions.append("\t\t\t\t\tif ( id == new ").append(msg_type.toString()).append("().getID())");
                                    messageTransitions.append(System.getProperty("line.separator"));
                                    messageTransitions.append("\t\t\t\t\t{");
                                    messageTransitions.append(System.getProperty("line.separator"));
                                    messageTransitions.append("\t\t\t\t\t\t").append(msg_type.toString()).append(" ").append(msg_value.toString()).append(" = new ").append(msg_type.toString()).append("();");
                                    messageTransitions.append(System.getProperty("line.separator"));
                                    messageTransitions.append("\t\t\t\t\t\t").append(msg_value.toString()).append(".decode(casted_ie.getBody().getReceiveRec().getMessagePayload().getData(), pos);");
                                    messageTransitions.append(System.getProperty("line.separator"));
                            }
                    }

                    // Instantiate the other parameters
                    declareParameters(tr, sd, messageTransitions, codeType);

                    // Call the actual transition function, passing in the parameter list.
                    messageTransitions.append("\t\t\t\t\t\tp").append(sm.getName()).append(".context.").append(tr.getName()).append("(");
                    addParameterCallStack(tr.getParameter(), messageTransitions);
                    messageTransitions.append(");");
                    messageTransitions.append(System.getProperty("line.separator"));

                    // If the call is successful, stop checking for other transitions.  'done' set to false
                    // for clean-up and exit.
                    messageTransitions.append("\t\t\t\t\t\tdone = true;");
                    messageTransitions.append(System.getProperty("line.separator"));

                    // If this transition is triggered on an input message, we have an additional level
                    // of nesting to pop.
                    if (msg_type.length() != 0)
                    {
                            messageTransitions.append("\t\t\t\t\t}");
                            messageTransitions.append(System.getProperty("line.separator"));
                    }

                    // Pop the rest of the way, and add an empty catch.
                    messageTransitions.append("\t\t\t\t}");
                    messageTransitions.append(System.getProperty("line.separator"));
                    messageTransitions.append("\t\t\t} catch (Exception e) {}");
                    messageTransitions.append(System.getProperty("line.separator"));
                    messageTransitions.append(System.getProperty("line.separator"));
                }
	}
	
	/** 
	 * Find the message argument in the parameter list
	 * @param tr Transition
         * @param sb_type
         * @param sb_value
         * @param sd Service Definition
	 */
	public static void findInputMessage(org.jts.jsidl.binding.Transition tr, StringBuilder sb_type, StringBuilder sb_value, org.jts.jsidl.binding.ServiceDef sd)
	{
		String type, value;
		
		for (org.jts.jsidl.binding.Parameter param : tr.getParameter())
		{
			// check for supported primitive types
			if ( param.getType().contains(" ") )
				continue;
				
			// Getting here implies it's not a primitive type or string literal.
			// Extract all the element references to get the message or internal
			// event name.
			type = param.getType();
			value = param.getValue();
			if (type.contains("."))
			{
			   type = type.substring(0, param.getType().indexOf("."));
			   value = "msg_with_reference_to_sub_element";
			}
			
			// Make sure we've got an input message...
			if (!isInputMessage(type, sd))
				continue;
			
			// Return the new values.
			sb_type.append(type);
			sb_value.append(value);
			return;
		}
		
		// Getting to this point means we haven't found an input message in the parameter list.
		// It *could* still be specified in the transition name...
		if ( isInputMessage(tr.getName().substring(0, tr.getName().lastIndexOf("Transition")), sd) )
		{
			sb_type.append(tr.getName().substring(0, tr.getName().lastIndexOf("Transition")));
			sb_value.append("msg");
		}
	}

        /*
         * @param tr Transition
         * @param sd Service Definition
         * @param messageTransitions
         * @param codeType for language specific synatax
         * @param codeType to ensure correct data types in a given language.
         */
	public static void declareParameters(org.jts.jsidl.binding.Transition tr, org.jts.jsidl.binding.ServiceDef sd, StringBuffer messageTransitions, CodeLines.CodeType codeType)
	{
        // We need to know the input message...
        StringBuilder msg_type = new StringBuilder();
        StringBuilder msg_value = new StringBuilder();
        findInputMessage(tr, msg_type, msg_value, sd);

        if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
        {
            declareCPPParams(tr, sd, messageTransitions, msg_type, msg_value);
        }
        else if (codeType == CodeLines.CodeType.JAVA)
        {
            declareJavaParams(tr, sd, messageTransitions, msg_type, msg_value);
        }
        else if (codeType == CodeLines.CodeType.C_SHARP)
        {
            declareCSharpParams(tr, sd, messageTransitions, msg_type, msg_value);
        }


	}

    /*
     * @param tr Transition
     * @param sd Service Definition
     * @param messageTransitions
     * @param msg_type message type being checked for
     * @param msg_value
     */
    public static void declareCPPParams(org.jts.jsidl.binding.Transition tr, org.jts.jsidl.binding.ServiceDef sd, StringBuffer messageTransitions, StringBuilder msg_type, StringBuilder msg_value) {
        // For each parameter, add the declaration line.
        for (org.jts.jsidl.binding.Parameter param : tr.getParameter()) {
            // check for supported primitive types
            if (param.getType().contains(" ")) {

            	// since the user will need to initialize this value, make a guid so we can look it up again
            	// and replace the initialization with the previous one whenever the file is regenerated
            	String guid = UUID.nameUUIDFromBytes( getUniqueString(param,tr,sd).getBytes() ).toString();
                messageTransitions.append("\t\t\t\t\t\t")
                		.append("// Unique identifier used to replace previous edits upon regeneration, do not delete this comment:")
                		.append(guid)
                		.append(System.getProperty("line.separator"));;

                messageTransitions.append("\t\t\t\t\t\t").append(param.getType()).append(" ").
                        append(param.getValue()).append(";");
                messageTransitions.append(System.getProperty("line.separator"));
                messageTransitions.append("\t\t\t\t\t\tprintf(\"WARNING!  Using parameter \'").
                        append(param.getValue()).append("\' without initialization!\\n\");");
                messageTransitions.append(System.getProperty("line.separator"));
            } // Handle elements.
            else if (param.getType().contains(".")) {
                messageTransitions.append("\t\t\t\t\t\t").append(param.getType().replace(".", "::")).
                        append(" ").append(param.getValue()).append(" = *(");
                messageTransitions.append(param.getType().replace(".", "()->get").replaceFirst(getTriggerName(tr, sd)
                        + "\\(\\)->get", "casted_ie->get").replaceFirst(msg_type.toString() + "\\(\\)->get", msg_value.toString() + ".get").concat("());"));
                messageTransitions.append(System.getProperty("line.separator"));

            } // Handle whole events.
            else {
                if (param.getType().equals(getTriggerName(tr, sd))) {
                    messageTransitions.append("\t\t\t\t\t\t").append(param.getType()).append(" ").
                            append(param.getValue()).append(" = *casted_ie;");
                    messageTransitions.append(System.getProperty("line.separator"));
                }
            }
        }
    }

    /**
     * Generates a unique string based on the transition and serviceDef definitions
     * by using all of their properties appended onto one string separated by underscores
     * @param tr
     * @param sd
     */
    private static String getUniqueString(org.jts.jsidl.binding.Parameter currentParam, org.jts.jsidl.binding.Transition tr, org.jts.jsidl.binding.ServiceDef sd)
    {
    	String unique = "";
    	
    	if( currentParam != null )
    	{
    		unique += currentParam.getType();
    		unique += currentParam.getValue();
    	}
    	
    	if( sd != null )
    	{
    		unique += sd.getName() + "_";
    		unique += sd.getId() + "_";
    	}
    	
    	if( tr != null )
    	{
    		unique += tr.getName() + "_";
    		
    		if( tr.getGuard() != null )
    		{
    			unique += tr.getGuard().getCondition() + "_";
    		}
    		
    		if( tr.getParameter() != null )
    		{
    			for( org.jts.jsidl.binding.Parameter p : tr.getParameter() )
    			{
    				unique += p.getType() + "_";
    				unique += p.getValue() + "_";
    			}
    		}
    		
    		if( tr.getActionOrSendAction() != null )
    		{
    			for( Object obj : tr.getActionOrSendAction() )
    			{
    				if( obj instanceof org.jts.jsidl.binding.SendAction )
    				{
    					org.jts.jsidl.binding.SendAction act = (org.jts.jsidl.binding.SendAction)obj;

    					unique += act.getName() + "_";
    					
    					if( act.getArgument() != null )
    					{
    						for( org.jts.jsidl.binding.Argument arg : act.getArgument() )
    						{
    							unique += arg.getValue() + "_";
    						}
    					}
    				}
    				else if( obj instanceof org.jts.jsidl.binding.Action )
    				{
    					org.jts.jsidl.binding.Action act = (org.jts.jsidl.binding.Action)obj;

    					unique += act.getName() + "_";
    					
    					if( act.getArgument() != null )
    					{
    						for( org.jts.jsidl.binding.Argument arg : act.getArgument() )
    						{
    							unique += arg.getValue() + "_";
    						}
    					}
    				}
    			}
    		}
    		
    		if( tr.getInternal() != null )
    		{
    			unique += "Internal" + "_";
    		}
    		else if( tr.getSimple() != null )
    		{
    			unique += "Simple" + "_";
    		}
    		else if( tr.getPush() != null )
    		{
    			unique += "Push" + "_";
    		}
    		else if( tr.getPop() != null )
    		{
    			unique += "Pop" + "_";
    		}
    	}
    	
    	return unique;
    }

    /*
     * @param tr Transition
     * @param sd Service Definition
     * @param messageTransitions
     * @param msg_type message type being checked for
     * @param msg_value
     */
    public static void declareJavaParams(org.jts.jsidl.binding.Transition tr, org.jts.jsidl.binding.ServiceDef sd, StringBuffer messageTransitions, StringBuilder msg_type, StringBuilder msg_value) {
        // For each parameter, add the declaration line.
        for (org.jts.jsidl.binding.Parameter param : tr.getParameter()) {
            // check for supported primitive types
            // Since jsidl can have unsigned primatives, we'll catch them here.
            if (param.getType().contains(" ")) {
                String type = "";
                if (param.getType().compareTo("unsigned int") == 0) {
                    type = "long";
                } else if (param.getType().compareTo("unsigned byte") == 0) {
                    type = "byte";
                } else if (param.getType().compareTo("unsigned short") == 0) {
                    type = "int";
                } else {
                    type = param.getType();
                }
                messageTransitions.append("\t\t\t\t\t\t").append(type).append(" ").append(param.getValue()).append(" = 0;");
                messageTransitions.append(System.getProperty("line.separator"));
                messageTransitions.append("\t\t\t\t\t\tSystem.out.println(\"WARNING!  Using parameter \'").append(param.getValue()).append("\' without initialization!\\n\");");
                messageTransitions.append(System.getProperty("line.separator"));
            } // Handle elements.
            else if (param.getType().contains(".")) {
                messageTransitions.append("\t\t\t\t\t\t").append(param.getType()).append(" ").append(param.getValue()).append(" = ");
                messageTransitions.append(param.getType().replace(".", "().get").replaceFirst(getTriggerName(tr, sd) + "\\(\\)", "casted_ie").replaceFirst(msg_type.toString() + "\\(\\)", msg_value.toString()).concat("();"));
                messageTransitions.append(System.getProperty("line.separator"));

            } // Handle whole events.
            else {
                if (param.getType().equals(getTriggerName(tr, sd))) {
                    messageTransitions.append("\t\t\t\t\t\t").append(param.getType()).append(" ").append(param.getValue()).append(" = casted_ie;");
                    messageTransitions.append(System.getProperty("line.separator"));
                }
            }
        }
    }

    /*
     * @param tr Transition
     * @param sd Service Definition
     * @param messageTransitions
     * @param msg_type message type being checked for
     * @param msg_value
     */
    public static void declareCSharpParams(org.jts.jsidl.binding.Transition tr, org.jts.jsidl.binding.ServiceDef sd, StringBuffer messageTransitions, StringBuilder msg_type, StringBuilder msg_value)
    {
        // For each parameter, add the declaration line.
        for (org.jts.jsidl.binding.Parameter param : tr.getParameter())
        {
            // check for supported primitive types
            if ( param.getType().contains(" ") )
            {
                String type = "";
                if (param.getType().compareTo("unsigned int") == 0)
                {
                    type = "uint";
                }
                else if (param.getType().compareTo("byte") == 0)
                {
                    type = "sbyte";
                }
                else if (param.getType().compareTo("unsigned short") == 0)
                {
                    type = "ushort";
                }
                else
                {
                    type = param.getType();
                }
                messageTransitions.append("\t\t\t\t\t\t").append(type).append(" ").append(param.getValue()).append(" = 0;");
                messageTransitions.append(System.getProperty("line.separator"));
                messageTransitions.append("\t\t\t\t\t\tConsole.WriteLine(\"WARNING!  Using parameter \'").append(param.getValue()).append("\' without initialization!\\n\");");
                messageTransitions.append(System.getProperty("line.separator"));
            }

            // Handle elements.
            else if (param.getType().contains("."))
            {
                messageTransitions.append("\t\t\t\t\t\t").append(param.getType()).append(" ").append(param.getValue()).append(" = ");
                messageTransitions.append(param.getType().replace(".", "().get").replaceFirst(getTriggerName(tr, sd) + "\\(\\)", "casted_ie").replaceFirst(msg_type.toString() + "\\(\\)", msg_value.toString()).concat("();"));
                messageTransitions.append(System.getProperty("line.separator"));

            }

            // Handle whole events.
            else
            {
                if (param.getType().equals(getTriggerName(tr, sd)))
                {
                    messageTransitions.append("\t\t\t\t\t\t").append(param.getType()).append(" ").append(param.getValue()).append(" = casted_ie;");
                    messageTransitions.append(System.getProperty("line.separator"));

                }
            }
        }
    }

	
	/*
         * @param list List of parameters
         * @param messageTransitions.
         */
        public static void addParameterCallStack(List<org.jts.jsidl.binding.Parameter> list, StringBuffer messageTransitions)
	{
	    boolean first = true;
	    for (org.jts.jsidl.binding.Parameter param : list)
		{
			if (!first) messageTransitions.append(", ");
			messageTransitions.append(param.getValue());
			first = false;
		}
	}
	
	/*
         * @param name
         * @param sd Service Definition
         */
        public static boolean isInputMessage(String name, org.jts.jsidl.binding.ServiceDef sd)
	{
		// Search the input message set for the trigger.
		// Return TRUE if found; FALSE otherwise.
		for (Object msg : sd.getMessageSet().getInputSet().getMessageDefOrDeclaredMessageDef())
		{
			if (msg instanceof org.jts.jsidl.binding.MessageDef)
			{
				if (((org.jts.jsidl.binding.MessageDef) msg).getName().compareTo(name) == 0)
					return true;
			}
		}
		return false;
	}

	/*
         * @param tr Transition
         * @param sd Service Definition
         */
	public static String getTriggerName(org.jts.jsidl.binding.Transition tr, org.jts.jsidl.binding.ServiceDef sd)
	{
	    // The validator ensures that the transition name is also it's trigger, but with the 
		// word "Transition" appended.  Strip it off...
		String trigger = tr.getName().substring(0, tr.getName().lastIndexOf("Transition"));	
		
		// If the trigger is actually in input message, then the "real" trigger is a "Receive"
		// event that wraps the incoming message.
		if (isInputMessage(trigger, sd)) trigger = "Receive";
		
		return trigger;
	}

	/*
         * @param tr Transition
         */
	public static void replaceNameSpacing( org.jts.jsidl.binding.Transition tr )
	{
		if( tr == null )
		{
			return;
		}
		
		// namespaced elements may be in the simple transition end state
		if( tr.getSimple() != null )
		{
			org.jts.jsidl.binding.Simple simple = tr.getSimple();
			
			if( simple.getEndState() != null && simple.getEndState().getState() != null )
			{
				simple.getEndState().setState( simple.getEndState().getState().replaceAll("\\.", "_") );
			}
		}
		
		// napespaced elements may be in the push transition simple transition end state
		if( tr.getPush() != null )
		{
			org.jts.jsidl.binding.Push push = tr.getPush();
			
			// end state for push
			if( push.getEndState() != null && push.getEndState().getState() != null )
			{
				push.getEndState().setState( push.getEndState().getState().replaceAll("\\.", "_") );
			}
			
			// end state for simple
			if( push.getSimple() != null )
			{
				org.jts.jsidl.binding.Simple simple = tr.getSimple();
				
				if( simple.getEndState() != null && simple.getEndState().getState() != null )
				{
					simple.getEndState().setState( simple.getEndState().getState().replaceAll("\\.", "_") );
				}
			}
		}
	}

        /*
         * @param tr Default Transition
         */
	public static void replaceNameSpacing( org.jts.jsidl.binding.DefaultTransition tr )
	{
		if( tr == null )
		{
			return;
		}
		
		// namespaced elements may be in the simple transition end state
		if( tr.getSimple() != null )
		{
			org.jts.jsidl.binding.Simple simple = tr.getSimple();
			
			if( simple.getEndState() != null && simple.getEndState().getState() != null )
			{
				simple.getEndState().setState( simple.getEndState().getState().replaceAll("\\.", "_") );
			}
		}
		
		// napespaced elements may be in the push transition simple transition end state
		if( tr.getPush() != null )
		{
			org.jts.jsidl.binding.Push push = tr.getPush();
			
			// end state for push
			if( push.getEndState() != null && push.getEndState().getState() != null )
			{
				push.getEndState().setState( push.getEndState().getState().replaceAll("\\.", "_") );
			}
			
			// end state for simple
			if( push.getSimple() != null )
			{
				org.jts.jsidl.binding.Simple simple = tr.getSimple();
				
				if( simple.getEndState() != null && simple.getEndState().getState() != null )
				{
					simple.getEndState().setState( simple.getEndState().getState().replaceAll("\\.", "_") );
				}
			}
		}
	}
	
}