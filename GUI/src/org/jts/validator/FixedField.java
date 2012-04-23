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

public class FixedField 
{
	/**
	 * Validates a JSIDL Fixed Field.
	 * <pre>
		fixed_field =
		   element fixed_field {
		      attribute name { identifier }, 
			 	# 1. Must follow ANSI C syntax 
		      attribute_field_type,
		      	# 1. Must be specified
		      attribute_field_units, 
		      	# 1. Must be specified
		      attribute optional {xsd:boolean },
		      attribute interpretation { text }?,  
		      (scale_range | value_set)? 
		   }
 	 * </pre>
  	 * @param fixedField JSIDL binding object of the fixed field to be checked
	 * @throws ValidatorException if the fixed field has an invalid name string
	 */
	public static void validate(org.jts.jsidl.binding.FixedField fixedField) throws ValidatorException
	{
		Validator.validateName(fixedField.getName());
		if(fixedField.getFieldType() == null || fixedField.getFieldType().length() == 0)
		{
			throw new ValidatorException("Must specify field type");
		}		

		if(fixedField.getFieldUnits() == null || fixedField.getFieldUnits().length() == 0)
		{
			throw new ValidatorException("Must specify units");
		}		

		if(fixedField.getScaleRange() != null && fixedField.getValueSet() != null)
		{
			throw new ValidatorException("Fixed field cannot have both scale range and value set defined");
		}
		
		// check to make sure value range or enums can be expressed with field type
		org.jts.validator.ValueSet.validateInContext(fixedField.getValueSet(), fixedField.getFieldType());
	}
}
