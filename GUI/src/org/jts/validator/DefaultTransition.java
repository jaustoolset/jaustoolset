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

import java.io.ByteArrayInputStream;

public class DefaultTransition
{
	/**
	 * Validates a JSIDL default transition element.
	 * <pre>
		default_transition =   
		  element default_transition { 
		    attribute interpretation { text }?,
		    guard?,
		    	# 1. Must parse correctly
		    	# 2. Guard may not contain any parameter variables
		    (simple | push | pop),
		    	# 1. Must contain one and only one of simple, push or pop 
		    (action | send_action)* 
		    	# 1. Actions may not contain any parameter variables
		  }
	 * </pre>
  	 * @param defaultTrans JSIDL binding object of the default transition to be checked
	 * @throws ValidatorException 	
	 */
	public static void validate(org.jts.jsidl.binding.DefaultTransition transition ) throws ValidatorException
	{
	
		ByteArrayInputStream guardInputStream = new ByteArrayInputStream(transition.getGuard().getCondition().getBytes());
		
		org.jts.validator.parsers.GuardParser gp = new org.jts.validator.parsers.GuardParser(guardInputStream);

		try
		{
			gp.Condition();
		}
		catch(Exception e)
		{
			throw new ValidatorException("Guard Parse Exception: " + e);
		}
		
    	// # 1. Guard may not contain any parameter variables
		if( gp.getArgumentCount() > 0)
		{
			throw new ValidatorException("Gaurd may not contain variables because default transitions cannot have parameters");
		}
		
		// # 1. Must contain only one and only one of simple, push or pop 
		int transitionType = 0;
		
		if(transition.getSimple() != null)
		{
			transitionType++;
		}
		
		if(transition.getPush() != null)
		{
			transitionType++;
		}
		
		if(transition.getPop() != null)
		{
			transitionType++;
		}
		
		if(transitionType > 1)
		{
			throw new ValidatorException("Transition has too many transition types defined. Must be only one of simple, push or pop");
		}

		if(transitionType < 1)
		{
			throw new ValidatorException("Transition must specify a transition type: simple, push or pop");
		}

    	// # 1. Actions may not contain any parameter variables
		for(int i = 0; i < transition.getActionOrSendAction().size(); i++)
		{
			Object testObject = transition.getActionOrSendAction().get(i);
			if(testObject instanceof org.jts.jsidl.binding.SendAction)
			{
				org.jts.jsidl.binding.SendAction sendAction = (org.jts.jsidl.binding.SendAction)testObject;
				for(int j = 0; j < sendAction.getArgument().size(); j++)
				{
					String argString = sendAction.getArgument().get(j).getValue();
					
					if(!Validator.isConstantSting(argString))
					{
						throw new ValidatorException("SendAction may not contain arguments because default transitions cannot have parameters");
					}
				}
			}

			if(testObject instanceof org.jts.jsidl.binding.Action)
			{
				org.jts.jsidl.binding.Action action = (org.jts.jsidl.binding.Action)testObject;
				for(int j = 0; j < action.getArgument().size(); j++)
				{
					String argString = action.getArgument().get(j).getValue();
					
					if(!Validator.isConstantSting(argString))
					{
						throw new ValidatorException("Action may not contain variables because default transitions cannot have parameters");
					}
				}
			}
		}
		
		
	}
	
}
