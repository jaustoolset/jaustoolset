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

public class ScaleRange 
{
	/**
	 * <pre>
	 * Validates a JSIDL Scale Range Element.
		scale_range = 
		   element scale_range {
		   		# 1. The upper limit value must be greater than the lower limit value
		      attribute real_lower_limit { text },
		      attribute real_upper_limit { text },
		      attribute integer_function { "floor" |"round" | "ceiling" },         
		      attribute interpretation { text }?
		   }
	 * </pre>
   	 * @param scaleRange JSIDL binding object of the scale range to be checked
	 * @throws ValidatorException if the upper limit is not greater than the lower limit
	 */
	public static void validate(org.jts.jsidl.binding.ScaleRange scaleRange) throws ValidatorException
	{
		// scale range may be empty
		if( scaleRange.getRealLowerLimit().isEmpty() &&
				scaleRange.getRealUpperLimit().isEmpty() )
		{
			return;
		}

		// no mechanism to check for constants resolution so only allow numbers for now within JTS scaled ranges
		double lowerLimit = 0;
		double upperLimit = 0;

		try
		{
			lowerLimit = Double.parseDouble(scaleRange.getRealLowerLimit());
		}
		catch( NumberFormatException e )
		{
			throw new ValidatorException("LowerLimit must be valid double value");
		}
		
		try
		{
			upperLimit = Double.parseDouble(scaleRange.getRealUpperLimit());
		}
		catch( NumberFormatException e )
		{
			throw new ValidatorException("UpperLimit must be valid double value");
		}
		
		if(upperLimit <= lowerLimit)
		{
			throw new ValidatorException("Scale range upper limit must be greater than lower limit");
		}
	}
}
