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

public class ConstDef 
{
	/**
	 * Validates a JSIDL constDef. 
	 * <pre>
		const_def =
		   element const_def {
		      attribute name { identifier },
		      	# 1. Must follow ANSI C syntax      
		      attribute_field_units, 
		      attribute const_type {
		         "byte" | "short integer" | "integer" | "long integer" 
		       | "unsigned byte" | "unsigned short integer" 
		       | "unsigned integer" | "unsigned long integer" 
		       | "float" | "long float" | "string" },
		      attribute const_value { xsd:string },
		      	# 1. Value must fall in the range of the const_type      
		      attribute interpretation { text }?
		   }
   	 * @param constDef JSIDL binding object of the constDef to be checked. 
	 * @throws ValidatorException if the constDef has an invalid name
	 */
	public static void validate( org.jts.jsidl.binding.ConstDef constDef ) throws ValidatorException
	{
		Validator.validateName(constDef.getName());
		
      	// # 1. Value must fall in the range of the const_type      
		Validator.validateFieldTypeValue(constDef.getConstType(), constDef.getConstValue());
	}
}
