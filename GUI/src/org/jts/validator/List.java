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

public class List 
{
    /**
     * Validates a JSIDL List.
     * <pre>
            list =
               element list {
                            # 1. Must contain only one composite field type
                  attribute name { identifier },
                            # 1. Must follow ANSI C syntax
                  attribute optional {xsd:boolean },
                  attribute interpretation { text }?,
                  count_field,
                  ( record | declared_record |
                     \list | declared_list |
                     variant | declared_variant |
                     sequence | declared_sequence )
                     # 1. Count field type must be specified
               }
     * </pre>
     * @param list JSIDL binding object of the list to be checked
     * @throws ValidatorException 	if the object has an invalid name, no type specified, or contains more
     * 								than one composite field type
     */
public static void validate(org.jts.jsidl.binding.List list) throws ValidatorException {
        Validator.validateName(list.getName());

        if (list.getCountField() == null) {
            throw new ValidatorException("Count field not specified");
        }

        if (list.getCountField().getFieldTypeUnsigned() == null || list.getCountField().getFieldTypeUnsigned().length() == 0) {
            throw new ValidatorException("List field type must be specified");
        }

		Object field = null;
		if( list.getDeclaredList() != null )
		{
			field = list.getDeclaredList();
		}
		else if( list.getDeclaredRecord() != null )
		{
			field = list.getDeclaredRecord();
		}
		else if( list.getDeclaredSequence() != null )
		{
			field = list.getDeclaredSequence();
		}
		else if( list.getDeclaredVariant() != null )
		{
			field = list.getDeclaredVariant();
		}
		else if( list.getList() != null )
		{
			field = list.getList();
		}
		else if( list.getRecord() != null )
		{
			field = list.getRecord();
		}
		else if( list.getSequence() != null )
		{
			field = list.getSequence();
		}
		else if( list.getVariant() != null )
		{
			field = list.getVariant();
		}

		if( field == null )
		{
			throw new ValidatorException("List must contain one record, list, sequence, or variant Complex Field.");
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
				throw new ValidatorException("Field '" + name + "' in List may not be optional");
			}
		}
		catch(ValidatorException e)
		{
			throw e;
		}
		catch(NoSuchMethodException e)
		{
			throw new ValidatorException("List: Attempted to get isOptional/getName method from class " + cls.getName());
		}
		catch(Exception e)
		{
			throw new ValidatorException("List: Exception when calling isOptional/getName of class " + cls.getName());				
		}
    }
}
