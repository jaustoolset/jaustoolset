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
 * Action.java
 * 
 */
package org.jts.codegenerator.protocolBehavior;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import org.jts.codegenerator.CodeLines;

public class Action {
      /**
	 * Creates SMC code for actions for a list of actions
	 * @param obj
	 */
	public static StringBuffer addActionOrSendAction(Object obj)
	{
		StringBuffer localBuffer = new StringBuffer();
		
		if(obj instanceof org.jts.jsidl.binding.Action)
		{
			org.jts.jsidl.binding.Action act = (org.jts.jsidl.binding.Action)obj;			
			
			localBuffer.append(act.getName() + "(");
			
			Iterator<org.jts.jsidl.binding.Argument> argumentIter = act.getArgument().iterator();
			
			while(argumentIter.hasNext())
			{
				org.jts.jsidl.binding.Argument arg = argumentIter.next();
				
				// it is a string constant
				if(arg.getValue().contains("'"))
				{			
					localBuffer.append(arg.getValue().replace("'", "\""));
				}
				else
				{
					localBuffer.append(arg.getValue());
				}
				
				if(argumentIter.hasNext())
				{
					localBuffer.append(",");
				}
			}
			
			localBuffer.append(");");			
			
		}
		else if(obj instanceof org.jts.jsidl.binding.SendAction)
		{
			org.jts.jsidl.binding.SendAction sact = (org.jts.jsidl.binding.SendAction)obj;
			//send_action doesn't have arguments
			localBuffer.append(sact.getName() + "();");
		}
		
		return localBuffer;
	}
	
	/**
	 * Creates the method declaration used in cpp file for action or send action
	 * @param obj
	 * @return
	 */
	public static String addActionOrSendActionMethod(Object obj, List<org.jts.jsidl.binding.Parameter> parameterList, CodeLines.CodeType codeType)
	{	
		String returnMethod = null;
		
		if(obj == null)
		{
			return null;
		}
		else if(obj instanceof org.jts.jsidl.binding.Action)
		{
			org.jts.jsidl.binding.Action act = (org.jts.jsidl.binding.Action)obj;		
			
			returnMethod = act.getName() + "(";
			
			Iterator<org.jts.jsidl.binding.Argument> argumentIter = act.getArgument().iterator();
			
			int argumentCount = 0;
			
			while(argumentIter.hasNext())
			{
				org.jts.jsidl.binding.Argument currentArgument = argumentIter.next();
								
				/*
				 *  first we match see if the argument is a constant
				 */
				if(currentArgument.getValue().contains("'"))
				{
					returnMethod = returnMethod.concat(getActionConstantVariable(currentArgument.getValue(), argumentCount, codeType));
					argumentCount++;
				}
				/*
				 *  otherwise the argument must have a matching variable type defined in the parameter list
				 */
				else
				{
					returnMethod = returnMethod.concat(getActionVariableFromParameterList(parameterList, currentArgument.getValue(), codeType));
				}
				
				if(argumentIter.hasNext())
				{
					returnMethod = returnMethod.concat(", ");
				}
			}
			
			returnMethod = returnMethod.concat(")");
		}
		else if(obj instanceof org.jts.jsidl.binding.SendAction)
		{
			org.jts.jsidl.binding.SendAction sact = (org.jts.jsidl.binding.SendAction)obj;
			//send_action doesn't have arguments
			returnMethod = sact.getName() + "()";
		}
	
		return returnMethod;
			
	}
	
	/**
	 * Changes "'constant'" to "string arg0" based on what type is count as a primitive
	 * @param variable
	 * @return
	 */
	public static String getActionConstantVariable(String variable, int argumentCount, CodeLines.CodeType codeType)
	{
		String returnString;
		
		// get rid of first and last ' in string
		String argumentConstant = variable.substring(variable.indexOf("'"), variable.lastIndexOf("'"));
		
		//check for numeric value
		try
		{
			Float.parseFloat(argumentConstant);
			
			//float value 
			if(argumentConstant.contains("."))
			{
				returnString = "float arg" + argumentCount;
			}
			//integer value
			else
			{
				returnString = "int arg" + argumentCount;
			}
			
		}
		// if it is not a float or integer, it must be a string
		catch (NumberFormatException e)
		{
                    if (codeType == CodeLines.CodeType.C_PLUS_PLUS){
			returnString = "std::string arg" + argumentCount;
                    }
                    else if(codeType == CodeLines.CodeType.JAVA){
                        returnString = "String arg" + argumentCount;
                    }
                    else {
                        // Otherwise, it's C#
                        returnString = "string arg" + argumentCount;
                    }
		}
		
		return returnString;
	}
	
	/**
	 * changes "msg" to "Event msg" based on the match made with the list of parameters
	 * @return
	 */
	public static String getActionVariableFromParameterList(List<org.jts.jsidl.binding.Parameter> parameterList, String variable, CodeLines.CodeType codeType)
	{
		String returnString = "Error! Unknown type for '" + variable + "'";
		
		for(int j = 0; j < parameterList.size(); j++)
        {
            org.jts.jsidl.binding.Parameter currentParameter = parameterList.get(j);

            if (currentParameter.getValue().compareTo(variable) == 0)
            {
                if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
                {
                    returnString = currentParameter.getType().replaceAll("\\.", "\\:\\:") + " " + variable;
                }
                else if (codeType == CodeLines.CodeType.JAVA)
                {
                    if (currentParameter.getType().equals("unsigned int") || currentParameter.getType().equals("unsigned long"))
                    {
                        returnString = "long " + variable;
                               }
                    else if (currentParameter.getType().equals("unsigned short"))
                    {
                        returnString = "int " + variable;
                    }
                    else
                    {
                        returnString = currentParameter.getType() + " " + variable;
                    }
                }
                else if (codeType == CodeLines.CodeType.C_SHARP)
                {

                    if (currentParameter.getType().equalsIgnoreCase("byte"))
                    {
                        returnString = "sbyte " + variable;
                    }
                    else if(currentParameter.getType().contains("unsigned"))
                    {
                        returnString = currentParameter.getType().replace("unsigned ", "u") + " " + variable;
                    }
                    else
                    {
                        returnString = currentParameter.getType() + " " + variable;
                    }
                }
            }
        }

		return returnString;
	}
	
	/**
	 * Clone primitives of action or send action 
	 * @param obj
	 * @return
	 */
	public static Object cloneActionOrSendAction(Object obj)
	{
		Object returnObject = new Object();
		
		if(obj instanceof org.jts.jsidl.binding.Action)
		{
			org.jts.jsidl.binding.Action act = (org.jts.jsidl.binding.Action)obj;
			org.jts.jsidl.binding.Action returnAction = new org.jts.jsidl.binding.Action();
			
			returnAction.setName(new String(act.getName()));
			
			for(int i = 0; i < act.getArgument().size(); i++)
			{
				org.jts.jsidl.binding.Argument arguement = act.getArgument().get(i);
				org.jts.jsidl.binding.Argument returnArguement = new org.jts.jsidl.binding.Argument();
				
				returnArguement.setValue(new String(arguement.getValue()));
				
				returnAction.getArgument().add(i,returnArguement);
			}
			
			return returnAction;
		}
		else if(obj instanceof org.jts.jsidl.binding.SendAction)
		{
			org.jts.jsidl.binding.SendAction endAction = (org.jts.jsidl.binding.SendAction)obj;
			org.jts.jsidl.binding.SendAction returnSendAction = new org.jts.jsidl.binding.SendAction();
			
			returnSendAction.setName(new String(endAction.getName()));
			
			for(int i = 0; i < endAction.getArgument().size(); i++)
			{
				org.jts.jsidl.binding.Argument arguement = endAction.getArgument().get(i);
				org.jts.jsidl.binding.Argument returnArguement = new org.jts.jsidl.binding.Argument();
				
				returnArguement.setValue(new String(arguement.getValue()));
				
				returnSendAction.getArgument().add(i,returnArguement);
			}
			
			return returnSendAction;
		}

		// java will complain if there is nothing to return
		return returnObject;
	}

	/**
	 * Appends 'Action' or 'SendAction' to the object's name
	 * @param obj
	 */
	public static void appendActionOrSendActionName(Object obj)
	{
		if(obj instanceof org.jts.jsidl.binding.Action)
		{
			org.jts.jsidl.binding.Action action = (org.jts.jsidl.binding.Action) obj;
			if ((action.getName() != null) && !action.getName().endsWith("Action"))
			{
				// strip namespacing from action names
				action.setName(action.getName().substring(action.getName().lastIndexOf(".")+1) + "Action");
			}
		}
		else if(obj instanceof org.jts.jsidl.binding.SendAction)
		{
			org.jts.jsidl.binding.SendAction sendAction = (org.jts.jsidl.binding.SendAction) obj;
			
			if((sendAction.getName() != null) && !sendAction.getName().endsWith("SendAction"))
			{
				// strip namespacing from action names
				sendAction.setName(sendAction.getName().substring(sendAction.getName().lastIndexOf(".")+1) + "SendAction");
			}
		}
	}
	
	public static void getActions(org.jts.jsidl.binding.ProtocolBehavior pb, ArrayList<String> actions)
	{
		for(org.jts.jsidl.binding.StateMachine sm: pb.getStateMachine())
		{
			org.jts.codegenerator.protocolBehavior.Action.getActions(sm, actions);
		}
	}
	
	public static void getActions(org.jts.jsidl.binding.StateMachine sm, ArrayList<String> actions)
	{
		for(org.jts.jsidl.binding.State st:sm.getState())
		{
			org.jts.codegenerator.protocolBehavior.Action.getActions(st, actions);
		}
	}
	
	public static void getActions(org.jts.jsidl.binding.State st, ArrayList<String> actions)
	{
		for(org.jts.jsidl.binding.Transition tr:st.getTransition())
		{
			for(Object obj:tr.getActionOrSendAction())
			{
				actions.add(getActionName(obj));
			}
		}
		
		for(org.jts.jsidl.binding.State subSt:st.getState())
		{
			org.jts.codegenerator.protocolBehavior.Action.getActions(subSt, actions);
		}
	}
	
	public static String getActionName(Object obj)
	{
		if(obj instanceof org.jts.jsidl.binding.Action)
		{
			org.jts.jsidl.binding.Action act = (org.jts.jsidl.binding.Action)obj;
			return act.getName();
		}
		else if(obj instanceof org.jts.jsidl.binding.SendAction)
		{
			org.jts.jsidl.binding.SendAction act = (org.jts.jsidl.binding.SendAction)obj;
			return act.getName();
		}
		
		return "";
	}
}