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

import java.util.regex.Pattern;

public class Argument 
{
	/**
	 * Validates a JSIDL argument element.
	 * <pre>
		argument = 
		  element argument {
		    attribute value { text },  
				#1. Value must be defined (not null)
				#2. Value must be in single quotes
		    attribute interpretation { text }?
		  }
  	 * </pre>
  	 * @param argument JSIDL binding object of the argument to be checked
	 * @throws ValidatorException 	
	 */
	public static void validate(org.jts.jsidl.binding.Argument argument ) throws ValidatorException
	{
		if(argument.getValue() == null)
		{
			throw new ValidatorException("Argument value needs to be defined");
		}
		
		if( !isValueValidString( argument.getValue() ) )
		{
			throw new ValidatorException("Argument value needs to be in single quotes");
		}
		
	}
	
	public static boolean isValueValidString( String argumentValue )
	{
		if( argumentValue == null )
		{
			return false;
		}

		return Pattern.matches("\\s*[']{1}+.*[']{1}+\\s*" , argumentValue);
	}
}
