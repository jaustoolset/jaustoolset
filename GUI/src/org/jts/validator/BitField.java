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

public class BitField 
{
	/** 
	 * Validates a JSIDL Bit Field
	 * <pre>
		bit_field =
		   element bit_field {
		      attribute name { identifier },
				# 1. Must follow ANSI C syntax            
		      attribute_field_type_unsigned,  
		      attribute optional {xsd:boolean },
		      attribute interpretation { text }?,  
		      sub_field+
		      	# 1. Must contain elements with unique names
		      	# 2. Bit ranges in list must not overlap
		   }    
	 * </pre>
	 * @param bifField a JSIDL binding of the bit field to be validated
	 * @throws ValidatorException 	if the BitField has an invalid name, the sub field list has repeated names or 
	 * 								the bit field indexes overlap.								
	 */
	public static void validate(org.jts.jsidl.binding.BitField bitField) throws ValidatorException
	{
		Validator.validateName(bitField.getName());
		Validator.validateUniqueNameList(bitField.getSubField());
		
		// bitfield must contain at least one subfield
		if( bitField.getSubField().size() == 0 )
		{
			throw new ValidatorException("BitField must contain at least one subfield");
		}

		if( bitField.getFieldTypeUnsigned() == null || bitField.getFieldTypeUnsigned().isEmpty() )
		{
			throw new ValidatorException("Please select Type value");
		}
		
		// Test to check that indexes do not overlap
		short maxIndex = 0;
		for(int i=0; i<bitField.getSubField().size(); i++)
		{
			short fromIndex = bitField.getSubField().get(i).getBitRange().getFromIndex();
			short toIndex = bitField.getSubField().get(i).getBitRange().getToIndex();

			if( toIndex > maxIndex )
			{
				maxIndex = toIndex;
			}
			
			for(int j=0; j<bitField.getSubField().size(); j++)
			{
				if(i!=j)
				{
					short lowIndex = bitField.getSubField().get(j).getBitRange().getFromIndex();
					short highIndex = bitField.getSubField().get(j).getBitRange().getToIndex();
					
					if(fromIndex >= lowIndex && fromIndex <= highIndex)
					{
						throw new ValidatorException("BitField #" + i + " from index overlaps another index range in BitField");
					}
					if(toIndex >= lowIndex && toIndex <= highIndex)
					{
						throw new ValidatorException("BitField #" + i + " to index overlaps another index range in BitField");
					}
				}
			}
		}
		
		// this element contains the encoding type used, now make sure values being encoded can fit in the primitive domain
		String primitiveType = bitField.getFieldTypeUnsigned();
		int numberBits = 0;
		
		if(primitiveType.equalsIgnoreCase("unsigned byte"))
		{
			numberBits = 8;
		}
		else if(primitiveType.equalsIgnoreCase("unsigned short integer"))
		{
			numberBits = 16;
		}
		else if(primitiveType.equalsIgnoreCase("unsigned integer"))
		{
			numberBits = 32;
		}
		else if(primitiveType.equalsIgnoreCase("unsigned long integer"))
		{
			numberBits = 64;
		}
		
		// check to make sure bit indeces are within the field's data type
		for(org.jts.jsidl.binding.SubField subField : bitField.getSubField())
		{
			org.jts.validator.SubField.contextValidate(subField, numberBits);
		}
	}

}
