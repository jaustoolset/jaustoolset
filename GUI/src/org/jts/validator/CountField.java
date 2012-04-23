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

public class CountField
{	
	/**
	 * Validates a JSIDL Count Field.
	 * <pre>
		count_field = 
		   element count_field { 
		     attribute_field_type_unsigned, 
		     	# 1. Bounds Checking: min and max count should be within range of the chosen type
		     attribute min_count { identifier_ns | xsd:unsignedInt }?,
		     	# 1. Must be greater than 0
		     attribute max_count{ identifier_ns | xsd:unsignedInt }?,
		     	# 1. Must be greater than 0
		     	# 2. Must be greater than min count
		     attribute interpretation { text }?
		   }
 	 * </pre>
  	 * @param countField a JSIDL binding of the count field to be validated
	 * @throws ValidatorException if the min or max count is less than 1 or in the min count is greater than or
	 * 								equal to the max count.
	 */
	public static void validate(org.jts.jsidl.binding.CountField countField) throws ValidatorException
	{
		long minCount = Long.parseLong(countField.getMinCount());
		long maxCount = Long.parseLong(countField.getMaxCount());
		
		Validator.validateFieldTypeValue(countField.getFieldTypeUnsigned(), countField.getMaxCount());
		Validator.validateFieldTypeValue(countField.getFieldTypeUnsigned(), countField.getMinCount());
		
		if(minCount < 0)
		{
			throw new ValidatorException("Min count must be zero or greater");
		}
		
		if(maxCount < 1)
		{
			throw new ValidatorException("Max count must be greater than zero");
		}
		
		if(minCount > maxCount)
		{
			throw new ValidatorException("Max count must be equal or greater than min count");
		}
	}
}
