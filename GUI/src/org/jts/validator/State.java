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
import java.util.List;

public class State 
{
	/**
	 * Validates a JSIDL state element.
	 * <pre>
		state = 
			   element state {
			     attribute name { identifier_ns },
					# 1. Must follow ANSI C syntax           
			     attribute initial_state {identifier}?,
			     	# 1. Must be defined if state contains nested states
			     	# 2. Initial state name must be found in list of states
			     attribute interpretation { text }?,
			     entry?,
			     exit?,
			     transition*, 
			     	# 1. All must be unique in at least one of name, parameter list or guard condition
			     default_transition?, 
			     state*,
			     	# 1. All state names in list must be unique
			     default_state?
			   }
	 * </pre>
	 * @param State JSIDL binding object of the state to be checked
	 * @throws ValidatorException if the state's name is invalid or if there are repeated names within its transition list
	 */
	public static void validate(org.jts.jsidl.binding.State state) throws ValidatorException
	{
		Validator.validateName(state.getName());
		
		// # 1. All transitions must be unique in at least one of name, parameter list or guard condition
		List<org.jts.jsidl.binding.Transition> transitionList = state.getTransition();
		
		for(int i = 0; i < transitionList.size(); i++)
		{
			org.jts.jsidl.binding.Transition transition1 = transitionList.get(i);
			
			for(int j = 0; j < transitionList.size(); j++)
			{
				org.jts.jsidl.binding.Transition transition2 = transitionList.get(j);
				
				if(	j != i && 
					transition1.getName().equals(transition2.getName()) &&
					parameterListsEqual(transition1.getParameter(), transition2.getParameter()) &&
					guardsEqual(transition1.getGuard(), transition2.getGuard())) // TODO: RFE: test for guard condition logic equivalence 
				{
					throw new ValidatorException("Transition: \"" + transition1.getName() + "\" is defined more than once. All transitions must be unique.");
				}
			}
		}
		
		Validator.validateUniqueNameList(state.getState());
		
		if(	state.getState() != null && 
			state.getState().size() > 0 && 
			(state.getInitialState() == null || state.getInitialState().isEmpty()))
		{
			throw new ValidatorException("State must define an initial state for nested states.");
		}
		
		if( state.getInitialState() != null && !state.getInitialState().isEmpty())
		{
			boolean initialStateFound = false;
			for(int i = 0; i < state.getState().size(); i++)
			{
				if(state.getState().get(i).getName().equals(state.getInitialState()))
				{
					initialStateFound = true;
				}
			}
			if(!initialStateFound)
			{
				throw new ValidatorException("Initial State \"" + state.getInitialState() + "\" not found in nested state list.");
			}
		}
	}

	private static boolean guardsEqual(	org.jts.jsidl.binding.Guard guard1,
										org.jts.jsidl.binding.Guard guard2)
	{
		if(guard1 == null && guard2 == null)
		{
			return true;
		}

		if(guard1 == null && guard2 != null)
		{
			return false;
		}

		if(guard1 != null && guard2 == null)
		{
			return false;
		}
		
		if(guard1.getCondition().equals(guard2.getCondition()))
		{
			return true;
		}
		
		return false;
	}
			
	private static boolean parameterListsEqual(List<org.jts.jsidl.binding.Parameter> paramList1,
										List<org.jts.jsidl.binding.Parameter> paramList2)
	{
		if(paramList1 == null && paramList2 == null)
		{
			return true;
		}

		if(paramList1 != null && paramList2 == null)
		{
			return false;
		}
		
		if(paramList1 == null && paramList2 != null)
		{
			return false;
		}
		
		if(paramList1.size() != paramList2.size())
		{
			return false;
		}
		
		for(int i = 0; i < paramList1.size(); i++)
		{
			org.jts.jsidl.binding.Parameter param1 = paramList1.get(i);
			boolean matchFound = false;
			
			for(int j = 0; j < paramList2.size(); j++)
			{
				if(	param1.getType().equals(paramList2.get(j).getType()) &&
					param1.getValue().equals(paramList2.get(j).getValue()))
				{
					matchFound = true;
					break;
				}
			}
			
			if(!matchFound)
			{
				return false;
			}
		}
		
		return true;
	}
}
