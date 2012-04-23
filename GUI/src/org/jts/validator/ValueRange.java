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

public class ValueRange 
{
	/**
	 * Validates a JSIDL Value Range.
	 * <pre>
		value_range = 
		   element value_range {
		   		# 1. Lower limit value must be less than upper limit value 
		      attribute lower_limit { text }, 
		      attribute lower_limit_type { "inclusive" | "exclusive" },
		      attribute upper_limit { text }, 
		      attribute upper_limit_type { "inclusive" |"exclusive" },
		      attribute interpretation { text }?
		   }
 	 * </pre>
  	 * @param valueRange JSIDL binding object of the value range to be checked
	 * @throws ValidatorException if the lower limit is not less than the upper limit
	 */
	public static void validate(org.jts.jsidl.binding.ValueRange valueRange) throws ValidatorException
	{
		float lowerLimit = 0;
		float upperLimit = 0;
		
		try
		{
			lowerLimit = Float.parseFloat(valueRange.getLowerLimit());
		}
		catch( NumberFormatException e )
		{
			throw new ValidatorException("Value range lower limit must be a valid float: " + valueRange.getLowerLimit() );
		}

		try
		{
			upperLimit = Float.parseFloat(valueRange.getUpperLimit());
		}
		catch( NumberFormatException e )
		{
			throw new ValidatorException("Value range upper limit must be a valid float: " + valueRange.getUpperLimit() );
		}
		
		if(lowerLimit >= upperLimit)
		{
			throw new ValidatorException("Value range upper limit must be greater than lower limit"); 
		}
	}
}
