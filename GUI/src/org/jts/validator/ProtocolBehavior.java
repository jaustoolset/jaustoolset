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

import org.jts.jsidl.binding.Start;
import org.jts.jsidl.binding.StateMachine;

public class ProtocolBehavior
{
	/**
	 * Validates a JSIDL Protocol Behavior.
	 * <pre>
		protocol_behavior =
		  element protocol_behavior {
		    attribute is_stateless { xsd:boolean }?,
				# 1. Compatibility: ensure that if is_stateless is true, then only one state is present for each state machine
				# 2. Compatibility: ensure that if only one state is present for each state machine, then is_stateless must be true
		    \start+,
		    	# 1. Must be defined
		    	# 2. Must be found in state_machine list
		    	# 3. Must specify one and only one start state for each state machine
		    state_machine+
		    	# 1. Must specify at least one state machine
		    	# 2. All names in list must be unique 
		  }
	 * </pre>
  	 * @param protocol JSIDL binding object of the protocol behavior to be checked
	 * @throws ValidatorException if no state machines are defined or the state machines do not have correct start states defined
	 */
	public static void validate(org.jts.jsidl.binding.ProtocolBehavior protocol) throws ValidatorException
	{
		if(protocol.getStateMachine().size() < 1)
		{
			throw new ValidatorException("Protocol must specify at least one state machine");
		}
		
		Validator.validateUniqueNameList(protocol.getStateMachine());
		
		if(protocol.isIsStateless().booleanValue())
		{
			for(int i = 0; i < protocol.getStateMachine().size(); i++)
			{
				if(protocol.getStateMachine().get(i).getState().size() != 1)
				{
					throw new ValidatorException("Protocol behavior is stateless, state machine: \"" + protocol.getStateMachine().get(i).getName() + "\"has multiple states");
				}
			}
		}
		else
		{
			boolean stateLess = true;
			for(int i = 0; i < protocol.getStateMachine().size(); i++)
			{
				if(protocol.getStateMachine().get(i).getState().size() > 1)
				{
					stateLess = false;
				}
			}
			if(stateLess)
			{
				throw new ValidatorException("Protocol behavior has only state machines with single states, it must be declared as stateless");
			}
		}
		
		for(int i = 0; i < protocol.getStateMachine().size(); i++)
		{
			boolean startFound = false;
			StateMachine stateMachine = protocol.getStateMachine().get(i);
			
			for(int j = 0; j < protocol.getStart().size(); j++)
			{
				if(stateMachine.getName().equals(protocol.getStart().get(j).getStateMachineName()))
				{
					startFound = true;
					boolean stateFound = false;
					Start start = protocol.getStart().get(j);

					for(int k = 0; k < stateMachine.getState().size(); k++)
					{
						if(start.getStateName().equals(stateMachine.getState().get(k).getName()))
						{
							stateFound = true;
						}
					}
					
					if(!stateFound)
					{
						throw new ValidatorException("Start state: " + start.getStateName() + " does not exist in state machine");						
					}
				}
			}
			
			if(!startFound)
			{
				throw new ValidatorException("State machine: " + stateMachine.getName() + " does not have a start defined");
			}
		}	
		
		if(protocol.getStart().size() > protocol.getStateMachine().size())
		{
			throw new ValidatorException("Protocol must specify exactly one state machine for each start state defined");
		}
		
	}

}
