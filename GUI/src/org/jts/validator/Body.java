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

public class Body 
{
	/**
	 * Validates a JSIDL body element.
	 * <pre>
		body = 
		   element body { 
		     attribute name { identifier },
				# 1. Must follow ANSI C syntax             
		     attribute interpretation { text }?,
		      ( record | declared_record | 
		       \list | declared_list | 
		       variant | declared_variant | 
		       sequence | declared_sequence )?
		   }
	 * </pre>
  	 * @param body JSIDL binding object of the body to be checked
	 * @throws ValidatorException if of the semantic checks above fail
	 */
	public static void validate(org.jts.jsidl.binding.Body body ) throws ValidatorException
	{
		Validator.validateName(body.getName());

		Object field = null;

		if( body.getList() != null )
		{
			field = body.getList();
		}
		else if( body.getRecord() != null )
		{
			field = body.getRecord();
		}
		else if( body.getSequence() != null )
		{
			field = body.getSequence();
		}
		else if( body.getVariant() != null )
		{
			field = body.getVariant();
		}

		// bodies are allowed to be empty
		if(	field == null )
		{
			return;
		}
		
		// fields in body may not be optional
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
				throw new ValidatorException("Complex Field '" + name + "' in Body may not be optional");
			}
		}
		catch(ValidatorException e)
		{
			throw e;
		}
		catch(NoSuchMethodException e)
		{
			throw new ValidatorException("Body: Attempted to get isOptional/getName method from class " + cls.getName());
		}
		catch(Exception e)
		{
			throw new ValidatorException("Body: Exception when calling isOptional/getName of class " + cls.getName());				
		}
	}

}
