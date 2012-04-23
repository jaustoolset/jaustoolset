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
import java.util.Vector;

public class Transition 
{
	/**
	 * Validates a JSIDL transition element.
	 * <pre>
		transition =
		  element transition {
		    attribute name { identifier_ns },
		    	# 1. Must parse correctly
		    	# 2. Must be a valid identifier of the form (service_name.)?(message_name | internal_event_name)
		    attribute interpretation { text }?,
		    parameter*,
		    	# 1. Each parameter variable name must be unique
		    guard?,
		    	# 1. All guard action variables must be specified in parameter list
		    (simple | push | pop),
		    	# 1. Must contain only one and only one of simple, push or pop 
		    (action | send_action)*
		    	# 1. All action variables must be specified in parameter list
		 }
 	 * </pre>
  	 * @param transition JSIDL binding object of the transition to be checked
	 * @throws ValidatorException 	
	 */
	public static void validate(org.jts.jsidl.binding.Transition transition ) throws ValidatorException
	{
		Validator.validateName(transition.getName());
		
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
		
		Vector<String> paramVector = new Vector<String>();
		for(int i = 0; i < transition.getParameter().size(); i++)
		{
			paramVector.add(transition.getParameter().get(i).getValue());
		}
		
		// # 1. Each parameter variable name must be unique
		for(int i = 0; i < paramVector.size(); i++)
		{
			String currentValueString = paramVector.get(i);
			
			for(int j = 0; j < paramVector.size(); j++)
			{
				if(i!=j && paramVector.get(j).equals(currentValueString))
				{
					throw new ValidatorException("Parameter value \"" + currentValueString + "\" is defined multiple times");					
				}
			}
		}
		
		// # 1. All guard action variables must be specified in parameter list
		for(int i = 0; i < gp.getArgumentCount(); i++)
		{
			if(!paramVector.contains(gp.getArgument(i)))
			{
				throw new ValidatorException("Gaurd argument \"" + gp.getArgument(i) + "\" must be specified as a parameter value");
			}
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

		// # 1. All action variables must be specified in parameter list
		for(int i = 0; i < transition.getActionOrSendAction().size(); i++)
		{
			Object testObject = transition.getActionOrSendAction().get(i);
			if(testObject instanceof org.jts.jsidl.binding.SendAction)
			{
				org.jts.jsidl.binding.SendAction sendAction = (org.jts.jsidl.binding.SendAction)testObject;
				for(int j = 0; j < sendAction.getArgument().size(); j++)
				{
					String argString = sendAction.getArgument().get(j).getValue();
					
					if(!Validator.isConstantSting(argString) && !paramVector.contains(argString))
					{
						throw new ValidatorException("SendAction: \"" + sendAction.getName() + "\" argument \"" + argString + "\" must be specified as a parameter value");
					}
				}
			}

			if(testObject instanceof org.jts.jsidl.binding.Action)
			{
				org.jts.jsidl.binding.Action action = (org.jts.jsidl.binding.Action)testObject;
				for(int j = 0; j < action.getArgument().size(); j++)
				{
					String argString = action.getArgument().get(j).getValue();
					
					if(!Validator.isConstantSting(argString) && !paramVector.contains(argString))
					{
						throw new ValidatorException("Action: \"" + action.getName() + "\" argument \"" + argString + "\" must be specified as a parameter value");
					}
				}
			}
		}
	}
}
