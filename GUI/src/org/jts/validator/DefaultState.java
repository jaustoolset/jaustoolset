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

public class DefaultState 
{
	/**
	 * Validates a JSIDL defaultState element.
	 * <pre>
		default_state = 
		  element default_state {
		     attribute interpretation { text }?,
		     transition*,
	     		# 1. All must be unique in at least one of name, parameter list or guard condition
		     default_transition? 
		  }
  	 * </pre>
  	 * @param defaultState JSIDL binding object of the defaultState to be checked
	 */
	public static void validate(org.jts.jsidl.binding.DefaultState defaultState )
	{
		// # 1. All transitions must be unique in at least one of name, parameter list or guard condition
		List<org.jts.jsidl.binding.Transition> transitionList = defaultState.getTransition();
		
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

	private static boolean parameterListsEqual(	List<org.jts.jsidl.binding.Parameter> paramList1,
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
