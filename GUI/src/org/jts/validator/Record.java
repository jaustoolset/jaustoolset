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

public class Record 
{
	/**
	 * Validates a JSIDL Record element.
	 * <pre>
		record =
		   element record {
				# 1. If at least one of the fields within a record is optional, then the record must contain a presence_vector      
				# 2. If none of the fields within a record are optional, then the record must not contain a presence_vector         
		      attribute name { identifier },
				# 1. Must follow ANSI C syntax            
		      attribute optional {xsd:boolean },
		      attribute interpretation { text }?,
		      presence_vector?,
		      ( array | fixed_field | variable_field | bit_field |  
		        fixed_length_string | variable_length_string |
		        variable_length_field |variable_format_field | 
		        declared_array | declared_fixed_field |declared_variable_field | 
		       declared_bit_field | declared_fixed_length_string | 
		       declared_variable_length_string | declared_variable_length_field | 
		       declared_variable_format_field )+
		       	# 1. Must contain a list with at least one element
		       	# 2. All names in list must be unique 
		   }
	 * </pre>
	 * @param record JSIDL binding object of the record to be checked
	 * @throws ValidatorException 	if the object has an invalid name, zero size, or repeated elements in its list.
	 * 								It also checks to ensure that it has a presence vector, if any optional elements
	 * 								are included in the record.
	 */
	public static void validate(org.jts.jsidl.binding.Record record) throws ValidatorException
	{
		Validator.validateName(record.getName());
		
		List<Object> simpleFieldOrArrayList = record.getArrayOrFixedFieldOrVariableField();
		if(simpleFieldOrArrayList.size() == 0)
		{
			throw new ValidatorException("Record must contain at least one Simple Field or Array");			
		}
		Validator.validateUniqueNameList(simpleFieldOrArrayList);

		// Verify that record has a presence vector if any optional elements are specified
		Class<?> cls = null;
		int optionalElementCount = 0;
		try
		{
			int i;
			for(i = 0; i < simpleFieldOrArrayList.size(); i++) // Check each element to determine if it's optional
			{
				Object obj = simpleFieldOrArrayList.get(i);		
				cls = obj.getClass(); 	// Determine the objects class
				Method isOptional = cls.getMethod("isOptional");	// Attempt to get the getName method
				Boolean bool = (Boolean)isOptional.invoke(obj);				
				if(bool.booleanValue())
				{
					optionalElementCount++;
					if( record.getPresenceVector() == null)
					{
						throw new ValidatorException("Record with optional elements must contain a Presence Vector");	
					}
				}
			}
			
			if(optionalElementCount == 0 && record.getPresenceVector() != null )
			{
				throw new ValidatorException("Record with no optional elements cannot have a Presence Vector");	
			}
		}
		catch(ValidatorException e)
		{
			throw e;
		}
		catch(NoSuchMethodException e)
		{
			throw new ValidatorException("Attempted to get isOptional method from class " + cls.getName());
		}
		catch(Exception e)
		{
			throw new ValidatorException("Exception when calling isOptional of class " + cls.getName());				
		}			
		
		if(record.getPresenceVector() != null)
		{
			if(record.getPresenceVector().getFieldTypeUnsigned().equals("unsigned byte") && optionalElementCount > 8)
			{
				throw new ValidatorException("Record contains too many elements for presence vector of type unsigned byte");				
			}
			else if(record.getPresenceVector().getFieldTypeUnsigned().equals("unsigned short") && optionalElementCount > 16)
			{
				throw new ValidatorException("Record contains too many elements for presence vector of type unsigned short");				
			}
			else if(record.getPresenceVector().getFieldTypeUnsigned().equals("unsigned integer") && optionalElementCount > 32)
			{
				throw new ValidatorException("Record contains too many elements for presence vector of type unsigned integer");				
			}
			else if(record.getPresenceVector().getFieldTypeUnsigned().equals("unsigned long integer") && optionalElementCount > 64)
			{
				throw new ValidatorException("Record contains too many elements for presence vector of type unsigned long integer");				
			}
		}
		
	}
}
