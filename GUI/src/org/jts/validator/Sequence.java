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

public class Sequence 
{
	/**
	 * Validates a JSIDL Sequence element.
	 * <pre>
		sequence = 
		   element sequence { 
				# 1. If at least one of the fields within a sequence is optional, then the sequence must contain a presence_vector      
				# 2. If none of the fields within a sequence are optional, then the sequence must not contain a presence_vector         
		      attribute name { identifier },
				# 1. Must follow ANSI C syntax            
		      attribute optional {xsd:boolean },
		      attribute interpretation { text }?,
		      presence_vector?,
		      	# 1. Bounds Checking: size of the presence_vector must be at least as large as the number of complex types in the sequence
		      ( record | declared_record |
		         \list | declared_list |
		         variant | declared_variant |
		         sequence | declared_sequence )+
		        # 1. Must contain a list with at least one element
		        # 2. All names in list must be unique 
		   }
	 * </pre>
   	 * @param sequence JSIDL binding object of the sequence to be checked
	 * @throws ValidatorException 	if the object has an invalid name, zero size, or repeated element names in the sequence.
	 * 								It also checks to ensure that it has a presence vector, if any optional elements
	 * 								are included in the sequence.
	 */
	public static void validate(org.jts.jsidl.binding.Sequence sequence) throws ValidatorException
	{
		Validator.validateName(sequence.getName());
		
		List<Object> recordList = sequence.getRecordOrDeclaredRecordOrList();
		if(recordList.size() == 0)
		{
			throw new ValidatorException("Sequence must contain at least one record, list or variant");			
		}
		Validator.validateUniqueNameList(recordList);

		// Verify that record has a presence vector if any optional elements are specified
		int optionalElementCount = 0;
		Class<?> cls = null;
		try
		{
			int i;
			for(i = 0; i < recordList.size(); i++) // Check each element to determine if it's optional
			{
				Object obj = recordList.get(i);		
				cls = obj.getClass(); 	// Determine the objects class
				Method isOptional = cls.getMethod("isOptional");	// Attempt to get the isOptional method
				Method getName = cls.getMethod("getName");
				Boolean bool = (Boolean)isOptional.invoke(obj);
				String name = (String)getName.invoke(obj);

				if(bool.booleanValue())
				{
					optionalElementCount++;
					if(sequence.getPresenceVector() == null)
					{
						throw new ValidatorException("Sequence with optional element '" + name + "' must contain a Presence Vector");	
					}
				}
			}
			
			if(optionalElementCount == 0 && sequence.getPresenceVector() != null )
			{
				throw new ValidatorException("Sequence with no optional elements cannot have a Presence Vector");	
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
		
		if(sequence.getPresenceVector() != null)
		{
			if(sequence.getPresenceVector().getFieldTypeUnsigned().equals("unsigned byte") && optionalElementCount > 8)
			{
				throw new ValidatorException("List contains too many elements for presence vector of type unsigned byte");				
			}
			else if(sequence.getPresenceVector().getFieldTypeUnsigned().equals("unsigned short") && optionalElementCount > 16)
			{
				throw new ValidatorException("List contains too many elements for presence vector of type unsigned short");				
			}
			else if(sequence.getPresenceVector().getFieldTypeUnsigned().equals("unsigned integer") && optionalElementCount > 32)
			{
				throw new ValidatorException("List contains too many elements for presence vector of type unsigned integer");				
			}
			else if(sequence.getPresenceVector().getFieldTypeUnsigned().equals("unsigned long integer") && optionalElementCount > 64)
			{
				throw new ValidatorException("List contains too many elements for presence vector of type unsigned long integer");				
			}
		}
	}

}
