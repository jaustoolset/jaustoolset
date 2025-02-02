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

import java.io.ByteArrayInputStream;

public class Guard 
{
	/**
	 * Validates a JSIDL guard element.
	 * <pre>
		guard = 
		  element guard {
		    attribute condition { text }, 
		    	#1. Guard syntax must be valid
		    attribute interpretation { text }? 
		  }
  	 * </pre>
  	 * @param guard JSIDL binding object of the guard to be checked
	 * @throws ValidatorException 	
	 */
	public static void validate(org.jts.jsidl.binding.Guard guard ) throws ValidatorException
	{
		ByteArrayInputStream guardInputStream = new ByteArrayInputStream(guard.getCondition().getBytes());
		
		org.jts.validator.parsers.GuardParser gp = new org.jts.validator.parsers.GuardParser(guardInputStream);

		try
		{
			gp.Condition();
		}
		catch(Exception e)
		{
			throw new ValidatorException("Guard Parse Exception: " + e);
		}
		catch(Error err)
		{
			throw new ValidatorException("Guard Parse Error: " + err);
		}
	}
}
