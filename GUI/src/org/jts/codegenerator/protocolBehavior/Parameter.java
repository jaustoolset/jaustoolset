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
 * Parameter.java
 * 
 */
package org.jts.codegenerator.protocolBehavior;

import java.util.List;
import org.jts.codegenerator.CodeLines;

public class Parameter {
	/**
	 * Adds parameters to code in SMC syntax
	 * @param obj is a transition which holds the list of parameters
     * @param codeType allows for handling of data types in other languages.
	 */
	static StringBuffer addParametersFromTransition(Object obj, CodeLines.CodeType codeType)
	{
		StringBuffer localBuffer = new StringBuffer();
		
		if(obj instanceof org.jts.jsidl.binding.Transition)
		{
			org.jts.jsidl.binding.Transition tr = (org.jts.jsidl.binding.Transition)obj;
			
			localBuffer.append("(");
			
			for(int i = 0; i < tr.getParameter().size(); i++)
			{
				org.jts.jsidl.binding.Parameter par = (org.jts.jsidl.binding.Parameter)tr.getParameter().get(i);
				String type = par.getType();
                if(codeType == CodeLines.CodeType.C_PLUS_PLUS)
                {
                    localBuffer.append(par.getValue() + ":" + type.replaceAll("\\.", "\\:\\:"));
                }
                else if(codeType == CodeLines.CodeType.JAVA)
                {
                    if(type.compareTo("unsigned int") == 0)
                    {
                        type = "long";
                    }
                    else if(type.compareTo("unsigned short") == 0)
                    {
                        type = "int";
                    }
                    else if(type.compareTo("unsigned byte") == 0)
                    {
                        type = "short";
                    }
                    localBuffer.append(par.getValue() + ":" + type);
                }
                else if(codeType == CodeLines.CodeType.C_SHARP)
                {
                    if(type.compareTo("unsigned int") == 0)
                    {
                        type = "uint";
                    }
                    else if(type.compareTo("unsigned short") == 0)
                    {
                        type = "ushort";
                    }
                    else if(type.compareTo("byte") == 0)
                    {
                        type = "sbyte";
                    }
                    localBuffer.append(par.getValue() + ":" + type);
                }
				
				//add comma if not last in list or one variable in list
				if(tr.getParameter().size() != 1 && i != tr.getParameter().size()-1)
				{
					localBuffer.append(",");
				}
			}
			localBuffer.append(")");
		}
		
		// NOTE:default transitions don't have parameters
		else if(obj instanceof org.jts.jsidl.binding.DefaultTransition)
		{
			//org.jts.jsidl.binding.DefaultTransition dtr = (org.jts.jsidl.binding.DefaultTransition)obj;
		}
		
		return localBuffer;
	}
	
	/**
	 * Checks to see if two lists of parameters have different parameters (parameters don't have to be in the same order)
	 * @param parameters1
	 * @param parameters2
	 * @return
	 */
	public static boolean isDifferentParameterList(List <org.jts.jsidl.binding.Parameter> parameters1, List <org.jts.jsidl.binding.Parameter> parameters2)
	{
		// size must be equal for lists to hold all the same parameters
		if(parameters1.size() != parameters2.size())
		{
			return true;
		}
		
		// check for equality for each parameter in the first list against every other parameter in the second list
		// all instances have to have a corresponding entry in the other list for it to be the same parameters		
		
		for(int i = 0; i < parameters1.size(); i++)
		{
			org.jts.jsidl.binding.Parameter par1 = parameters1.get(i);
			
			int numberDifferences = 0;
			
			for(int j = 0; j < parameters1.size(); j++)
			{
				org.jts.jsidl.binding.Parameter par2 = parameters2.get(j);
				
				// type and value must be the same for parameters to be the the same 
				if(isDifferentParameter(par1, par2))
				{
					numberDifferences++;
				}
			}
			
			// parameter was not found in the second list if size of count of differences is equal to size of list
			if(numberDifferences == parameters1.size())
			{
				return true;
			}
		}
		
		// otherwise, every instance of each list was found in the other list so the lists are are not different
		return false;
	}
	
	/**
	 * Checks to see if two lists of parameter have the same parameters
	 * @param parameters1
	 * @param parameters2
	 * @return
	 */
	public static boolean isSameParameterList(List <org.jts.jsidl.binding.Parameter> parameters1, List <org.jts.jsidl.binding.Parameter> parameters2)
	{
		return !isDifferentParameterList(parameters1, parameters2);
	}
	
	/**
	 * Checks types and values of two  parameters to see if the parameters are different
	 * @param parameter1
	 * @param parameter2
	 * @return
	 */
	public static boolean isDifferentParameter(org.jts.jsidl.binding.Parameter parameter1, org.jts.jsidl.binding.Parameter parameter2)
	{
		return !isSameParameter(parameter1, parameter2);
	}
	
	/**
	 * Check types and values of two parameters to see if the parameters are the same
	 * @param parameter1
	 * @param parameter2
	 * @return
	 */
	public static boolean isSameParameter(org.jts.jsidl.binding.Parameter parameter1, org.jts.jsidl.binding.Parameter parameter2)
	{
		// types and values of parameters must be the same for parameters to be the same
		if(parameter1.getType().compareTo(parameter2.getType()) == 0 && 
				parameter1.getValue().compareTo(parameter2.getValue()) == 0)
		{
			return true;
		}
		
		return false;
	}
	
	/** 
	 * Makes a clone of the parameter
	 * @param inputParameter
	 * @return
	 */
	public static org.jts.jsidl.binding.Parameter cloneParameter(org.jts.jsidl.binding.Parameter inputParameter)
	{
		org.jts.jsidl.binding.Parameter returnParameter = new org.jts.jsidl.binding.Parameter();
		
		returnParameter.setType(new String(inputParameter.getType()));
		returnParameter.setValue(new String(inputParameter.getValue()));
		
		return returnParameter;
	}
}