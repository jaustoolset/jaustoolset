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

public class Array
{

	/**
	 * Validates a JSIDL array.
	 * <pre>
		array = 
		   element array { 
		      attribute name { identifier }, 
				# 1. Must follow ANSI C syntax             
		      attribute optional {xsd:boolean }, 
		      attribute interpretation { text }?, 
		      ( fixed_field | variable_field |bit_field | 
		       fixed_length_string | variable_length_string| 
		       variable_length_field |variable_format_field |  
		       declared_fixed_field |declared_variable_field |  
		       declared_bit_field | declared_fixed_length_string |  
		       declared_variable_length_string | declared_variable_length_field |  
		       declared_variable_format_field ), 
		       	# 1. At least one of the simple fields must be non-null 
		      element dimension { 
		      		# 1. All names in list must be unique  
		         attribute name { identifier }, 
					# 1. Must follow ANSI C syntax                
		         attribute size { identifier_ns | xsd:unsignedInt }, 
		         	# 1. Must be greater than 0 
		         attribute interpretation { text }? 
		      }+ 
		   }
	 * </pre>
	 * @param array JSIDL binding of the array to be checked. 
	 * @throws ValidatorException if the array dimension is zero or the array does not contain any simple fields.
	 */
	public static void validate(org.jts.jsidl.binding.Array array) throws ValidatorException
	{
		Validator.validateName(array.getName());

		Validator.validateUniqueNameList(array.getDimension());
		
		if(array.getDimension().size() < 1)	// Make sure minimum amount of dimensions exists
		{
			throw new ValidatorException("Array must contain at least one dimension");
		}
		
		Object field = null;

		if( array.getBitField() != null )
		{
			field = array.getBitField();
		}
		else if( array.getDeclaredBitField() != null )
		{
			field = array.getDeclaredBitField();
		}
		else if( array.getDeclaredFixedField() != null )
		{
			field = array.getDeclaredFixedField();
		}
		else if( array.getDeclaredFixedLengthString() != null )
		{
			field = array.getDeclaredFixedLengthString();
		}
		else if( array.getDeclaredVariableField() != null )
		{
			field = array.getDeclaredVariableField();
		}
		else if( array.getDeclaredVariableFormatField() != null )
		{
			field = array.getDeclaredVariableFormatField();
		}
		else if( array.getDeclaredVariableLengthString() != null )
		{
			field = array.getDeclaredVariableLengthString();
		}
		else if( array.getFixedField() != null )
		{
			field = array.getFixedField();
		}
		else if( array.getFixedLengthString() != null )
		{
			field = array.getFixedLengthString();
		}
		else if( array.getVariableField() != null )
		{
			field = array.getVariableField();
		}
		else if( array.getVariableFormatField() != null )
		{
			field = array.getVariableFormatField();
		}
		else if( array.getVariableLengthField() != null )
		{
			field = array.getVariableLengthField();
		}
		else if( array.getVariableLengthString() != null )
		{
			field = array.getVariableLengthString();
		}
		
		// Make sure that a field is specified
		if(	field == null )
		{
			throw new ValidatorException("Array must contain a Simple Field");
		}
		
		// fields in array cannot be optional
		Class<?> cls = null;
		try
		{
			cls = field.getClass();
			Method isOptional = cls.getMethod("isOptional");
			Method getName = cls.getMethod("getName");
			Boolean bool = (Boolean)isOptional.invoke(field);
			String name = (String)getName.invoke(field);

			if(bool.booleanValue())
			{
				throw new ValidatorException("Simple Field '" + name + "' in Array may not be optional");
			}
		}
		catch(ValidatorException e)
		{
			throw e;
		}
		catch(NoSuchMethodException e)
		{
			throw new ValidatorException("Array: Attempted to get isOptional/getName method from class " + cls.getName());
		}
		catch(Exception e)
		{
			throw new ValidatorException("Array: Exception when calling isOptional/getName of class " + cls.getName());				
		}
	}	
}
