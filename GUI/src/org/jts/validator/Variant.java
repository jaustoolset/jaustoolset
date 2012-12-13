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

import java.lang.reflect.Method;
import java.util.List;

import org.jts.jsidl.binding.VtagField;

public class Variant 
{
	/**
	 * Validates a JSIDL Variant element.
	 * <pre>
		variant = 
		   element variant { 
		      attribute name { identifier },
				# 1. Must follow ANSI C syntax            
		      attribute optional {xsd:boolean },
		      attribute interpretation { text }?,
		      vtag_field,
		      	# 1. Count range (max - min) must be equal to list size
		      ( record | declared_record |
		         \list | declared_list |
		         variant | declared_variant |
		         sequence | declared_sequence )*
		         # 1. All names in list must be unique
		         # 2. TODO: List must have a size greater than zero
		   }
 	 * </pre>
  	 * @param variant JSIDL binding object of the variant to be checked
	 * @throws ValidatorException 	if the object has an invalid name string, or repeated names in its record list.
	 * 								It also checks to ensure the min/max range is equal to the list size.
	 */
	public static void validate(org.jts.jsidl.binding.Variant variant) throws ValidatorException
	{
		Validator.validateName(variant.getName());
		List<Object> recordList = variant.getRecordOrDeclaredRecordOrList();
		Validator.validateUniqueNameList(recordList);

		VtagField vtField = variant.getVtagField();
		
		int maxCount = Integer.parseInt(vtField.getMaxCount());
		int minCount = Integer.parseInt(vtField.getMinCount());	
		
		if ((maxCount != 0) && (minCount != 0) && (recordList.size() != 0))	// allow for empty variants
		{
			if(maxCount - minCount + 1 != recordList.size())
			{
				throw new ValidatorException("Variant count range must be equal to record list size");	
			}
		}

		for( Object obj : variant.getRecordOrDeclaredRecordOrList() )
		{
			// fields in variant cannot be optional
			Class<?> cls = null;
			try
			{
				cls = obj.getClass();
				Method isOptional = cls.getMethod("isOptional");
				Method getName = cls.getMethod("getName");
				Boolean bool = (Boolean)isOptional.invoke(obj);
				String name = (String)getName.invoke(obj);

				if(bool.booleanValue())
				{
					throw new ValidatorException("Complex Field '" + name + "' in Variant may not be optional");
				}
			}
			catch(ValidatorException e)
			{
				throw e;
			}
			catch(NoSuchMethodException e)
			{
				throw new ValidatorException("Variant: Attempted to get isOptional/getName method from class " + cls.getName());
			}
			catch(Exception e)
			{
				throw new ValidatorException("Variant: Exception when calling isOptional/getName of class " + cls.getName());				
			}
		}
	}
}
