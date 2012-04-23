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

public class VtagField 
{
	/**
	 * Validates a JSIDL Vtag Field element.
	 * <pre>
		vtag_field = 
		   element vtag_field { 
		   		# 1. Max count must be greater than min count
		     attribute_field_type_unsigned, 
		   		# 1. Bounds Checking: min and max count should be within range of the chosen type
		     attribute min_count { identifier_ns | xsd:unsignedInt }?,
		     	# 1. Must be greater than zero
		     attribute max_count{ identifier_ns | xsd:unsignedInt }?,
		     	# 1. Must be greater than zero
		     attribute interpretation { text }?
		   }
	 * </pre>
   	 * @param vtag JSIDL binding object of the vtag field to be checked
	 * @throws ValidatorException if min or max count is less than 1 or if the min count is greater than or eqaul to the max count
	 */
	public static void validate(org.jts.jsidl.binding.VtagField vtag) throws ValidatorException
	{
		int minCount = Integer.parseInt(vtag.getMinCount());
		int maxCount = Integer.parseInt(vtag.getMaxCount());
		
		Validator.validateFieldTypeValue(vtag.getFieldTypeUnsigned(), vtag.getMaxCount());
		Validator.validateFieldTypeValue(vtag.getFieldTypeUnsigned(), vtag.getMinCount());

		if(minCount < 0)
		{
			throw new ValidatorException("Min count must be greater than or equal to zero");
		}
		
		if(maxCount < 1)
		{
			throw new ValidatorException("Max count must be greater than zero");
		}
		
		if(minCount >= maxCount)
		{
			throw new ValidatorException("Max count must be greater than min count");
		}		
	}
}
