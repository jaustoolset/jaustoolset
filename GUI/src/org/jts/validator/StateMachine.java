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

package org.jts.validator;

import java.util.LinkedList;
import java.util.List;

import org.jts.jsidl.binding.Transition;

public class StateMachine 
{
	/**
	 * Validates a JSIDL state machine.
	 * <pre>
		state_machine = 
		   element state_machine {
 		   		# 1. TODO: RFE: All pop transitions must be defined within state machine
		     attribute name { identifier_ns },
				# 1. Must follow ANSI C syntax           
		     attribute interpretation { text }?,
		     state+
		     	# 1. All names in list must be unique
		     	# 2. Must contain at least one state
		     	# 3. All transitions must specify an end state that exists in the state list
		     	# 4. All state default_state transitions must specify an end state that exists in the state list
		   }
	 * </pre>
   	 * @param stateMachine JSIDL binding object of the state machine to be checked
	 * @throws ValidatorException if the state machine's name is invalid or if there are repeated names within its state list
	 */
	public static void validate(org.jts.jsidl.binding.StateMachine stateMachine) throws ValidatorException
	{
		Validator.validateName(stateMachine.getName());
		Validator.validateUniqueNameList(stateMachine.getState());
		
		if(stateMachine.getState() == null || stateMachine.getState().size() < 1)
		{
			throw new ValidatorException("State machine must define at least one state.");
		}
		
		List<String> stateNames = getStateNames(stateMachine.getState());
		
		validateStateTransitions(stateNames, stateMachine.getState());
		
	}
	
	// Recursive function to extract all state names from list
	private static List<String> getStateNames(List<org.jts.jsidl.binding.State> stateList)
	{
		List<String> stateNames = new LinkedList<String>();
		
		for(int i = 0; i < stateList.size(); i++)
		{
			stateNames.add(stateList.get(i).getName());
			if(stateList.get(i).getState() != null)
			{
				stateNames.addAll( getStateNames(stateList.get(i).getState()) );
			}
		}
		
		return stateNames;
	}
	
	// Recursive function to validate all state transition end_states are defined within state machine
	private static void validateStateTransitions(List<String> stateNames, List<org.jts.jsidl.binding.State> stateList)
	{
		for(int i = 0; i < stateList.size(); i++)
		{
			List<Transition> transitionList = stateList.get(i).getTransition();
			if(stateList.get(i).getDefaultState() != null)
			{
				// Add all regular transitions to transition list for testing in for loop below
				transitionList.addAll(stateList.get(i).getDefaultState().getTransition());

				// Validate the default state of the current state
				List<org.jts.jsidl.binding.DefaultTransition> tList = stateList.get(i).getDefaultState().getDefaultTransition();
				if(tList != null)
				{
					for(int j = 0; j < tList.size(); j++)
					{
						org.jts.jsidl.binding.DefaultTransition t = tList.get(j);
	
						if(t != null && t.getSimple() != null && !stateNames.contains(t.getSimple().getEndState().getState()))
						{
							throw new ValidatorException("End state: " + t.getSimple().getEndState().getState() + " does not exist in state machine");
						}			
						
						if(t != null && t.getPush() != null)
						{
							if(!stateNames.contains(t.getPush().getEndState().getState()))
							{
								throw new ValidatorException("End state: " + t.getPush().getEndState().getState() + " does not exist in state machine");
							}
							
							if(	t.getPush().getSimple() != null && !stateNames.contains(t.getPush().getSimple().getEndState().getState()))
							{
								throw new ValidatorException("End state: " + t.getPush().getSimple().getEndState().getState() + " does not exist in state machine");
							}
						}
					}
				}

			}
			
			for(int j = 0; j < transitionList.size(); j++)
			{
				Transition t = transitionList.get(j);
				if(	t.getSimple() != null && 
					t.getSimple().getEndState() != null &&
					!stateNames.contains(t.getSimple().getEndState().getState()))
				{
					throw new ValidatorException("End state: " + t.getSimple().getEndState().getState() + " does not exist in state machine");
				}			
				
				if(t != null && t.getPush() != null)
				{
					if(!stateNames.contains(t.getPush().getEndState().getState()))
					{
						throw new ValidatorException("End state: " + t.getPush().getEndState().getState() + " does not exist in state machine");
					}
					
					if(	t.getPush().getSimple() != null && !stateNames.contains(t.getPush().getSimple().getEndState().getState()))
					{
						throw new ValidatorException("End state: " + t.getPush().getSimple().getEndState().getState() + " does not exist in state machine");
					}
				}

			}
			
			// Validate default transition of this current state
			List<org.jts.jsidl.binding.DefaultTransition> tList = stateList.get(i).getDefaultTransition();
			if(tList != null)
			{
				for(int j = 0; j < tList.size(); j++)
				{
					org.jts.jsidl.binding.DefaultTransition t = tList.get(j);

					if(t != null && t.getSimple() != null && !stateNames.contains(t.getSimple().getEndState().getState()))
					{
						throw new ValidatorException("End state: " + t.getSimple().getEndState().getState() + " does not exist in state machine");
					}			
					
					if(t != null && t.getPush() != null)
					{
						if(!stateNames.contains(t.getPush().getEndState().getState()))
						{
							throw new ValidatorException("End state: " + t.getPush().getEndState().getState() + " does not exist in state machine");
						}
						
						if(	t.getPush().getSimple() != null && !stateNames.contains(t.getPush().getSimple().getEndState().getState()))
						{
							throw new ValidatorException("End state: " + t.getPush().getSimple().getEndState().getState() + " does not exist in state machine");
						}
					}
				}
			}
			
			if(stateList.get(i).getState() != null)
			{
				validateStateTransitions(stateNames, stateList.get(i).getState());
			}
		}
	}
}
