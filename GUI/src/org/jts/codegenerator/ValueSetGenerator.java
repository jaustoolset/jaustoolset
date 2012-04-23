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
 * @(#)ValueSetGenerator.java		0.1 2008/09/19
 * 
 * Copyright
 */
package org.jts.codegenerator;

import java.util.ArrayList;
import java.util.List;
import org.jts.jsidl.binding.ValueEnum;
import org.jts.jsidl.binding.ValueRange;
import org.jts.jsidl.binding.ValueSet;


public class ValueSetGenerator
{
	private ValueSet valueSet;
//	private Vector<String> code;
	private CodeLines.CodeType codeType;
	
	private List<String> invalidRangeValues;
	private List<String> validRangeValues;
	private List<String> validEnumValues;
	
	/**
	 * This class adds the value set validation if statement around the provided code in the 
	 * Vector<String>
	 * 
	 * @param enumObject
	 * @param code
	 */
	public ValueSetGenerator(CodeLines.CodeType codeType, ValueSet valueSet)
	{
		this.valueSet = valueSet;
		this.codeType =  codeType;
		
		this.invalidRangeValues = new ArrayList<String>();
		this.validRangeValues = new ArrayList<String>();
		this.validEnumValues = new ArrayList<String>();
		

		/* Generates the list of valid and invalid test values.
		 * For the ValueEnum only a list of valid test values can be generated.
		 * The current implementation does not do a thorough job of checking which
		 * values are valid and invalid. It may be possible for a  value to be placed
		 * in the invalid list but is really valid.
		 */
		for(Object object: valueSet.getValueRangeOrValueEnum())
        {
            if  (object instanceof ValueEnum)
            {
            	ValueEnum valueEnum = (ValueEnum)object;
            	validEnumValues.add(Long.toString(valueEnum.getEnumIndex()));
            }
            else if (object instanceof ValueRange)
            {
            	ValueRange valueRange = (ValueRange)object;
            	String min = valueRange.getLowerLimit();
            	String max = valueRange.getUpperLimit();
                int maxNum = Double.valueOf(max.replaceAll("\\,", "")).intValue();
                int minNum = Double.valueOf(min.replaceAll("\\,", "")).intValue();
                int midNum = (maxNum - minNum) / 2;

                // The below and above values are dependent on the fieldType otherwise there
                // will be overflow;
//                String below = Double.toString(minNum - midNum);
//                String above = Double.toString(maxNum +  midNum);
//                
//                invalidRangeValues.add(below);
//                invalidRangeValues.add(above);
                
                if (valueRange.getLowerLimitType().equalsIgnoreCase("exclusive"))
            	{
	                invalidRangeValues.add(min);
            	}
                else
            	{
                	validRangeValues.add(min);
            	}
                
                if (valueRange.getUpperLimitType().equalsIgnoreCase("exclusive"))
            	{
                	invalidRangeValues.add(max);
            	}
                else
            	{
                	validRangeValues.add(max);
            	}
                
                validRangeValues.add(Integer.toString(midNum));
            }
        }
	}
	
	
	public void addRangeCheck(String varName,  List<String> code)
	{
            if (valueSet != null)
            {
                StringBuffer sb = new StringBuffer();
                boolean notFirst = false;

                for(Object object: valueSet.getValueRangeOrValueEnum())
                {
                    if (notFirst)
                    {
                        sb.append(" || ");
                    }
                    else
                    {
                        notFirst = true;
                    }

                    if  (object instanceof ValueEnum)
                    {
                        ValueEnum valueEnum = (ValueEnum)object;

                        sb.append("(" + varName + " == ").append(valueEnum.getEnumIndex()).append(")");
                    }
                    else if (object instanceof ValueRange)
                    {
                        ValueRange valueRange = (ValueRange)object;

                        sb.append("((" + varName);

                        if (valueRange.getLowerLimitType().equalsIgnoreCase("exclusive"))
                        {
                            sb.append(" > ");
                        }
                        else
                        {
                            sb.append(" >= ");
                        }
                        sb.append(valueRange.getLowerLimit()).append(") && (" + varName);

                        if (valueRange.getUpperLimitType().equalsIgnoreCase("exclusive"))
                        {
                            sb.append(" < ");
                        }
                        else
                        {
                            sb.append(" <= ");
                        }
                        sb.append(valueRange.getUpperLimit()).append("))");
                    }
                    else
                    {
                        throw new CodeGeneratorException("Unknown type within ValueSet");
                    }
                }
	
                Util.indent(1, code);
                code.add(0, "if (" + sb.toString() + ")");
                code.add(1, "{");
                code.add("}");
            }
		
	}
	
	

	/**
	 * Returns the Vector<String> that lists all the invalid values
	 * to be used for testing
	 * 
	 * @return
	 */
	public List<String> getInvalidRangeValues()
	{
		return invalidRangeValues;
	}

	/**
	 * Returns the Vector<String> that lists all the valid values
	 * to be used for testing
	 * 
	 * @return
	 */
	public List<String> getValidRangeValues()
	{
		return  validRangeValues;
	}
	

	/**
	 * Returns the Vector<String> that lists all the valid values
	 * to be used for testing
	 * 
	 * @return
	 */
	public List<String> getValidEnumValues()
	{
		return  validEnumValues;
	}
}
