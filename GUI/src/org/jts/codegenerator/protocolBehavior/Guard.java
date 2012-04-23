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
 * Guard.java
 * 
 */
package org.jts.codegenerator.protocolBehavior;

import java.util.*;
import org.jts.codegenerator.CodeLines;

public class Guard {

	/**
	 * Adds SMC guards to the code 
	 * @param obj
	 */
	public static StringBuffer addGuardFromTransition(Object obj)
	{
		StringBuffer localBuffer = new StringBuffer();
		
		if(obj instanceof org.jts.jsidl.binding.Transition)
		{	
			org.jts.jsidl.binding.Transition tr = (org.jts.jsidl.binding.Transition)obj;
			
			Guard.fixGuardContext(tr.getGuard());
			
			localBuffer.append("[" + tr.getGuard().getCondition() + "]");
		}
		else if(obj instanceof org.jts.jsidl.binding.DefaultTransition)
		{
			org.jts.jsidl.binding.DefaultTransition dtr = (org.jts.jsidl.binding.DefaultTransition)obj;
			
			Guard.fixGuardContext(dtr.getGuard());
			
			localBuffer.append("[" + dtr.getGuard().getCondition() + "]");
		}
		
		return localBuffer;
	}
	
	/**
	 * Creates ArrayList of guards method declarations used for cpp file
	 * @param obj
	 * @return
	 */
	public static ArrayList<String> addGuardMethodFromTransition(Object obj, CodeLines.CodeType codeType)
	{
		ArrayList<String> returnMethods = new ArrayList<String>();
		
		if(obj instanceof org.jts.jsidl.binding.Transition)
		{	
			org.jts.jsidl.binding.Transition currentTransition = (org.jts.jsidl.binding.Transition)obj;
			
			// add guards
			if(currentTransition.getGuard() != null)
			{
				if(currentTransition.getGuard().getCondition() != null)
				{
					// add guards for transition to list of all guards throughout all flattened states
					// (cxtx is already added at this point, so we parse them out)
					
					String[] temp = currentTransition.getGuard().getCondition().split("ctxt.");
					
					int argumentCount = 0;
					
					String returnMethod = "";
					
					for(int i = 0; i < temp.length; i++)
					{
						String currentString = temp[i];
						
						// check to see if current split has a method in it
						if(currentString.contains("("))
						{
							returnMethod = currentString.substring(0, currentString.indexOf("(")+1);
							
							// get rid of whitespace in variable name
							currentString = currentString.replaceAll("\\s", "");
							
							// process multiple variables if there are any
							if(currentString.contains(","))
							{
								String variables = currentString.substring(currentString.indexOf("(")+1, currentString.indexOf(")"));
								
								String[] variableSplitArray = variables.split(",");
								
								for(int j = 0; j < variableSplitArray.length; j++)
								{
									String variable = variableSplitArray[j];
									
									// check for constant argument
									if(variable.contains("'"))
									{
										returnMethod = returnMethod.concat(getGuardConstantVariable(variable, argumentCount, codeType));
										argumentCount++;
									}
									// otherwise the variable type must be in the argument list of the transition
									else
									{
										returnMethod = returnMethod.concat(getGuardVariableFromParameterList(currentTransition.getParameter(), variable, codeType));
									}
									
									// check for last in list
									if(j != variableSplitArray.length-1)
									{
										// only one in list
										returnMethod = returnMethod.concat(", ");
									}
								}
								
							}
							// check for empty function
							else if(currentString.contains("()"))
							{
								// do nothing
							}
							else
							{
								String variable = currentString.substring(currentString.indexOf("(")+1, currentString.indexOf(")"));

								// check for constant argument
								if(variable.contains("'"))
								{
									returnMethod = returnMethod.concat(getGuardConstantVariable(variable, argumentCount, codeType));
									argumentCount++;
								}
								// otherwise the variable type must be in the argument list of the transition
								else
								{
									String temps = getGuardVariableFromParameterList(currentTransition.getParameter(), variable, codeType);
									returnMethod = returnMethod.concat(temps);
								}

							}
							
							returnMethod = returnMethod.concat(")");
							returnMethods.add(returnMethod);
						}
					}
				}
			}

		}
		else if(obj instanceof org.jts.jsidl.binding.DefaultTransition)
		{
			org.jts.jsidl.binding.DefaultTransition currentDefaultTransition = (org.jts.jsidl.binding.DefaultTransition)obj;
			
			// default transitions don't have any parameters so there will be no arguments in the guard conditions
			
			// add guards
			if(currentDefaultTransition.getGuard() != null)
			{
				if(currentDefaultTransition.getGuard().getCondition() != null)
				{
					// add guards for transition to list of all guards throughout all flattened states
					// (cxtx is already added at this point, so we parse them out)
					
					String[] temp = currentDefaultTransition.getGuard().getCondition().split("ctxt.");
					
					int argumentCount = 0;
					
					String returnMethod = "";
					
					for(int i = 0; i < temp.length; i++)
					{
						String currentString = temp[i];
						
						// check to see if current split has a method in it
						if(currentString.contains("("))
						{						
							returnMethod = currentString.substring(0, currentString.indexOf("(")+1);						
							
							// get rid of whitespace in variable name
							currentString = currentString.replaceAll("\\s", "");
							
							// process multiple variables if there are any
							if(currentString.contains(","))
							{
								String variables = currentString.substring(currentString.indexOf("(")+1, currentString.indexOf(")"));
								
								String[] variableSplitArray = variables.split(",");
								
								for(int j = 0; j < variableSplitArray.length; j++)
								{
									String variable = variableSplitArray[j];
									
									// check for constant argument
									if(variable.contains("'"))
									{
										returnMethod = returnMethod.concat(getGuardConstantVariable(variable, argumentCount, codeType));
										argumentCount++;
									}
									// otherwise the variable type must be in the argument list of the transition
									else
									{
										// default transitions don't have parameters!
										//returnMethod = returnMethod.concat(getGuardVariableFromParameterList(currentDefaultTransition.getParameter(), variable));
									}
									
									// check for last in list
									if(j != variableSplitArray.length-1)
									{
										returnMethod = returnMethod.concat(", ");
									}
								}
								
							}
							// check for empty function
							else if(currentString.contains("()"))
							{
								// do nothing
							}
							else
							{
								String variable = currentString.substring(currentString.indexOf("(")+1, currentString.indexOf(")"));

								// check for constant argument
								if(variable.contains("'"))
								{
									returnMethod = returnMethod.concat(getGuardConstantVariable(variable, argumentCount, codeType));
									argumentCount++;
								}
								// otherwise the variable type must be in the argument list of the transition
								else
								{
									// default transitions don't have parameters!
									//returnMethod = returnMethod.concat(getGuardVariableFromParameterList(currentDefaultTransition.getParameter(), variable));
								}

							}
							
							returnMethod = returnMethod.concat(")");
							returnMethods.add(returnMethod);
						}
					}
				}
			}

		}
		
		return returnMethods;
	}
	
	/**
	 * Changes "'constant'" to "string arg0" based on what type is count as a primitive
	 * @param variable
	 * @return
	 */
	public static String getGuardConstantVariable(String variable, int argumentCount, CodeLines.CodeType codeType)
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
	public static String getGuardVariableFromParameterList(List<org.jts.jsidl.binding.Parameter> parameterList, String variable, CodeLines.CodeType codeType)
	{
		String returnString = "Error! Unknown type for '" + variable + "'";
		
		// return empty for emptry variable name
		if(variable.compareTo("") == 0)
		{
			return "";
		}
		
		for(int j = 0; j < parameterList.size(); j++)
		{
			org.jts.jsidl.binding.Parameter currentParameter = parameterList.get(j);
			
			if(currentParameter.getValue().compareTo(variable.replaceAll("\\s", "")) == 0)
			{
                if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
                {
                    returnString = currentParameter.getType().replaceAll("\\.", "\\:\\:") + " " + variable;
                }
                else if(codeType == CodeLines.CodeType.JAVA)
                {
                    if(currentParameter.getType().equalsIgnoreCase("unsigned int") || currentParameter.getType().equalsIgnoreCase("unsigned long"))
                    {
                        returnString = "long " + variable;
                    }
                    else if(currentParameter.getType().equalsIgnoreCase("unsigned short"))
                    {
                        returnString = "int " + variable;
                    }
                    else if(currentParameter.getType().equalsIgnoreCase("unsigned byte"))
                    {
                        returnString = "short " + variable;
                    }
                    else
                    {
                        returnString = currentParameter.getType() + " " + variable;
                    }
                }
                else if(codeType == CodeLines.CodeType.C_SHARP)
                {
                    if(currentParameter.getType().equalsIgnoreCase("unsigned int"))
                    {
                        returnString = "uint " + variable;
                    }
                    else if (currentParameter.getType().equalsIgnoreCase("unsigned long"))
                    {
                        returnString = "ulong " + variable;
                    }
                    else if(currentParameter.getType().equalsIgnoreCase("unsigned short"))
                    {
                        returnString = "ushort " + variable;
                    }
                    else if(currentParameter.getType().equalsIgnoreCase("byte"))
                    {
                        returnString = "sbyte " + variable;
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
	 * changes "msg" to "Event msg" based on the match made with the list of parameters for autocomplete
	 * @return
	 */
	public static String getAutocompleteGuardVariableFromParameterList(List<org.jts.jsidl.binding.Parameter> parameterList, String variable)
	{
		String returnString = "Error! Unknown type for '" + variable + "'";
		
		// return empty for emptry variable name
		if(variable.compareTo("") == 0)
		{
			return "";
		}
		
		for(int j = 0; j < parameterList.size(); j++)
		{
			org.jts.jsidl.binding.Parameter currentParameter = parameterList.get(j);
			
			if(currentParameter.getValue().compareTo(variable.replaceAll("\\s", "")) == 0)
			{
				//returnString = currentParameter.getType().replaceAll("\\.", "\\:\\:") + " " + variable;
				returnString = currentParameter.getType() + " " + variable;
			}
		}	
		
		return returnString;
	}
	
	/**
	 * Adds the ctxt part of the method declaration to the method
	 * @param guard
	 */
	public static void fixGuardContext(org.jts.jsidl.binding.Guard guard)
	{
		if(guard.getCondition() != null)
		{
			guard.setCondition( fixGuardString( guard ) );
		}
	}

	/**
	 * Fixes a guard string to remove the namespace and add ctxt modifier
	 * @param guard
	 */
	private static String fixGuardString(org.jts.jsidl.binding.Guard guard)
	{
	    String returnGuard = "";
		
		if(guard.getCondition() != null)
		{
			String condition = guard.getCondition();
			

			
			StringTokenizer st = new StringTokenizer(condition, " ");
			
			int tokenCount = 0;
			
			while(st.hasMoreTokens())
			{				
				String out = st.nextToken();
				
				if(out.contains("("))
				{
					// Remove namespace qualifiers
					if (out.lastIndexOf(".") != -1)
						out = out.substring(out.lastIndexOf(".")+1);
					
					// guard could have been defined as negation, parse "!" from string and put in front of ctxt
					if(out.contains("!"))
					{
						out = out.replace("!", "");

						out = "!ctxt." + out;
					}
					else
					{					
						out = "ctxt." + out;
					}
				}
				
				String middle = "";
				
				if(tokenCount == 0)
				{
					middle = "";
				}
				else
				{
					middle = " ";
				}
				
				returnGuard = returnGuard + middle + out;
				
				tokenCount++;
			}
		}
		
		return returnGuard;
	}


	
	/**
	 * Determines if two guards are different
	 * @param g1
	 * @param g2
	 * @return
	 */
	public static boolean isDifferentGuard(org.jts.jsidl.binding.Guard g1, org.jts.jsidl.binding.Guard g2)
	{
		// lack of guards means they are not unique
		if(g1 == null && g2 == null)
		{
			return false;
		}
		
		if(g1 == null && g2 != null)
		{
			return true;
		}
		
		if(g1 != null && g2 == null)
		{
			return true;
		}
		
		// Before comparing 2 guards, strip the namespace.
		if(fixGuardString(g1).compareTo(fixGuardString(g2)) == 0)
		{
			return false;
		}
		
		return true;
	}
	
	/**
	 * Determines if two guards are the same
	 * @param g1
	 * @param g2
	 * @return
	 */
	public static boolean isSameGuard(org.jts.jsidl.binding.Guard g1, org.jts.jsidl.binding.Guard g2)
	{
		return !isDifferentGuard(g1, g2);
	}
	
	/**
	 * Makes a copy of every primitive of a guard
	 * @param inputGuard
	 * @return
	 */
	public static org.jts.jsidl.binding.Guard cloneGuard(org.jts.jsidl.binding.Guard inputGuard)
	{
		org.jts.jsidl.binding.Guard returnGuard = new org.jts.jsidl.binding.Guard();
		
		returnGuard.setCondition(new String(inputGuard.getCondition()));
		
		return returnGuard;
	}
	
	public static void getGuards(org.jts.jsidl.binding.ProtocolBehavior pb, ArrayList<String> guards, CodeLines.CodeType codeType)
	{
		for(org.jts.jsidl.binding.StateMachine sm: pb.getStateMachine())
		{
			org.jts.codegenerator.protocolBehavior.Guard.getGuards(sm, guards, codeType);
		}
	}
	
	public static void getGuards(org.jts.jsidl.binding.StateMachine sm, ArrayList<String> guards, CodeLines.CodeType codeType)
	{
		for(org.jts.jsidl.binding.State st:sm.getState())
		{
			org.jts.codegenerator.protocolBehavior.Guard.getGuards(st, guards, codeType);
		}

		// process guards from default state
		if (sm.getDefaultState() != null)
		{
		    for (org.jts.jsidl.binding.Transition tr:sm.getDefaultState().getTransition())
			{
				addGuards( tr, guards, codeType );
	}
		}
	}
	
	public static void getGuards(org.jts.jsidl.binding.State st, ArrayList<String> guards, CodeLines.CodeType codeType)
	{
		for(org.jts.jsidl.binding.Transition tr:st.getTransition())
		{
		    addGuards( tr, guards, codeType ); 
		}

		// process guards from default state
		if (st.getDefaultState() != null)
		{
		    for (org.jts.jsidl.binding.Transition tr:st.getDefaultState().getTransition())
			{
				addGuards( tr, guards, codeType );
			}
		}
		
		// recurse through substates
		for(org.jts.jsidl.binding.State subSt:st.getState())
		{
			org.jts.codegenerator.protocolBehavior.Guard.getGuards(subSt, guards, codeType);
		}
	}

	public static void addGuards(org.jts.jsidl.binding.Transition tr, ArrayList<String> guards, CodeLines.CodeType codeType)
	{
			if(tr.getGuard() != null)
			{
				// boolean logical operators may be included in each condition
				// we just want the function name with arguments
				String condition = tr.getGuard().getCondition();
				
				//System.out.println("condition : " + condition);
				
				String splits[] = condition.split("\\)");
				
				for(String split:splits)
				{
					//System.out.println(split);
					
					// check for bracket ending (split will have empty string)
					if(split.lastIndexOf("(") == -1)
					{
						continue;
					}
					
					String functionName = split.substring(0, split.lastIndexOf("("));
					String functionArguments = split.substring(split.lastIndexOf("(")+1);
					
					if(functionName != null && functionArguments != null)
					{
						functionName = functionName.replace(" ", "");
						functionName = functionName.replace("!", "");
						functionName = functionName.replace("||", "");
						functionName = functionName.replace("&&", "");
						functionName = functionName.replace("<", "");
						functionName = functionName.replace(">", "");
						functionName = functionName.replace("<=", "");
						functionName = functionName.replace(">=", "");
						functionName = functionName.replace("!=", "");
						functionName = functionName.replace("==", "");
						functionName = functionName.replace("(", "");
                                                
						// get rid of whitespace in variable name
						functionArguments = functionArguments.replaceAll("\\s", "");
										
						String[] variableSplitArray = functionArguments.split(",");
						
						String parameterList = "";

						for(int j = 0; j < variableSplitArray.length; j++)
						{
							String variable = variableSplitArray[j];
							
							// check for constant argument
							if(variable.contains("'"))
							{
								parameterList = parameterList + getGuardConstantVariable(functionArguments, 0, codeType);
							}
							// otherwise the variable type must be in the argument list of the transition
							else
							{
								parameterList = parameterList + getAutocompleteGuardVariableFromParameterList(tr.getParameter(), functionArguments);
							}
							
							// check for last in list
							if(j != variableSplitArray.length-1)
							{
								// only one in list
								parameterList = parameterList + ", ";
							}
						}
							
						//}
						
						guards.add(functionName + "(" + parameterList + ")");
						//guards.add(functionName);
					}
					else
					{
						System.out.println("GUARD : getGuards() : functionName or functionArguments is NULL!");
					}
				}
		}	
			}
}