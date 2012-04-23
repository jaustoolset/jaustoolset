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

public class TypeAndUnitsEnum
{
	/**
	 * Validates a JSIDL Type and Units Enumeration.
	 * <pre>
		type_and_units_enum = 
		   element type_and_units_enum {
		     attribute name { identifier },
		     attribute index { xsd:unsignedByte },
		     	# 1. Must be greater than or equal to zero
		     attribute_field_type, 
		     attribute_field_units, 
		     (value_set | scale_range)? 
		   }     
	 * </pre>
   	 * @param typeUnitsEnum JSIDL binding object of the type and units enum to be checked
	 * @throws ValidatorException if the index is less than zero
	 */
	public static void validate(org.jts.jsidl.binding.TypeAndUnitsEnum typeUnitsEnum) throws ValidatorException
	{
		Validator.validateName(typeUnitsEnum.getName());
		
		if(typeUnitsEnum.getFieldType() == null || typeUnitsEnum.getFieldType().length() == 0)
		{
			throw new ValidatorException("Must specify field type");
		}		

		if(typeUnitsEnum.getFieldUnits() == null || typeUnitsEnum.getFieldUnits().length() == 0)
		{
			throw new ValidatorException("Must specify field units");
		}
		
		if(typeUnitsEnum.getIndex() < 0)
		{
			throw new ValidatorException("Type and Units Enum index must be greater than or equal to zero");	
		}

		if(typeUnitsEnum.getScaleRange() != null && typeUnitsEnum.getValueSet() != null)
		{
			throw new ValidatorException("Cannot have both scale range and value set defined");
		}
	}
}
