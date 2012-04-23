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

import java.util.List;

import org.jts.jsidl.binding.ValueEnum;

public class ValueSet 
{
	/**
	 * Validates a JSIDL Value Set.
	 * <pre>
		value_set = 
		   element value_set {
		   		# 1. Value range and enums must have unique index values
		   		# 2. Value enums indexes must be within the value range - see note below
		      attribute offset_to_lower_limit { xsd:boolean },
		      (value_range | value_enum)+
		   }
	 * </pre>
   	 * @param valueSet JSIDL binding object of the value set to be checked
	 * @throws ValidatorException if the value set contains more than one enum with the same index value
	 */
	public static void validate(org.jts.jsidl.binding.ValueSet valueSet) throws ValidatorException
	{
		List<Object> enums = valueSet.getValueRangeOrValueEnum();
		for(int i = 0; i < enums.size(); i++)
		{
			Object object1 = enums.get(i);
			for(int j = 0; j < enums.size(); j++)
			{
				Object object2 = enums.get(j);
				if(	i != j &&
					object1 instanceof org.jts.jsidl.binding.ValueEnum &&
					object2 instanceof org.jts.jsidl.binding.ValueEnum &&
					((ValueEnum)object1).getEnumIndex() == ((ValueEnum)object2).getEnumIndex())
				{
					throw new ValidatorException("Value Set cannot have enums with same index");
				}
			}
		}
		
		// value set needs at least one value range or value enum(schema needs to be changed to require this property)
		if( valueSet.getValueRangeOrValueEnum().size() == 0 )
		{
			throw new ValidatorException("Value Set needs at least one value range or value enum");
		}
		
		// NOTE: item 2 for the validation requirements above is not valid since AS5684v1.0 states that the value set
		// is the union of all the ranges and enums which means that enum values need not be within any of the
		// value ranges defined for the set
		
	}
	
	public static void validateInContext(org.jts.jsidl.binding.ValueSet set, String fieldType )  throws ValidatorException
	{
		if(fieldType.equals("byte"))
		{
			org.jts.validator.ValueSet.validateInContext(set, Byte.MAX_VALUE, Byte.MIN_VALUE, fieldType);
		}
		else if(fieldType.equals("short integer"))
		{
			org.jts.validator.ValueSet.validateInContext(set, Short.MAX_VALUE, Short.MIN_VALUE, fieldType);
		}
		else if(fieldType.equals("integer"))
		{
			org.jts.validator.ValueSet.validateInContext(set, Integer.MAX_VALUE, Integer.MIN_VALUE, fieldType);		
		}
		else if(fieldType.equals("long integer"))
		{
			org.jts.validator.ValueSet.validateInContext(set, Long.MAX_VALUE, Long.MIN_VALUE, fieldType);			
		}
		else if(fieldType.equals("unsigned byte"))
		{
			org.jts.validator.ValueSet.validateInContext(set, Byte.MAX_VALUE * 2 + 1, 0, fieldType);
		}
		else if(fieldType.equals("unsigned short integer"))
		{
			org.jts.validator.ValueSet.validateInContext(set, Short.MAX_VALUE * 2 + 1, 0, fieldType);
		}
		else if(fieldType.equals("unsigned integer"))
		{
			org.jts.validator.ValueSet.validateInContext(set, Integer.MAX_VALUE * 2 + 1, 0, fieldType);
		}
		else if(fieldType.equals("unsigned long integer"))
		{
			org.jts.validator.ValueSet.validateInContext(set, Long.MAX_VALUE * 2 + 1, 0, fieldType);			
		}
		else if(fieldType.equals("float"))
		{
		}
		else if(fieldType.equals("long float"))
		{
		}
	}
	
	public static void validateInContext(org.jts.jsidl.binding.ValueSet set, long maxRange, long minRange, String errorString) throws ValidatorException
	{
		if( set == null )
		{
			return;
		}

		// when offset to lower limit, we are only concerned with checking that the range of values
		// is within the allowable bits as the values themselves may be out of the bit domain
		if( set.isOffsetToLowerLimit() )
		{
			contextValidateWithOffset( set, maxRange, minRange, errorString );
		}
		// otherwise we are interested in all the values being between minRange and maxRange
		else
		{
			contextValidateWithoutOffset( set, maxRange, minRange, errorString );
		}
	}
	
	private static void contextValidateWithOffset(org.jts.jsidl.binding.ValueSet set, long maxRange, long minRange, String errorString) throws ValidatorException
	{
		long lowerLimit = 0;
		boolean init = false;
		for( Object obj : set.getValueRangeOrValueEnum() )
		{
			if( obj instanceof org.jts.jsidl.binding.ValueRange )
			{
				org.jts.jsidl.binding.ValueRange rangeValue = (org.jts.jsidl.binding.ValueRange)obj;
				
				long lower = Long.parseLong( rangeValue.getLowerLimit() );

				if( !init )
				{
					init = true;
					lowerLimit = lower;
				}
				else if( lower < lowerLimit )
				{
					lowerLimit = lower;
				}
			}
			else if( obj instanceof org.jts.jsidl.binding.ValueEnum )
			{
				org.jts.jsidl.binding.ValueEnum enumValue = (org.jts.jsidl.binding.ValueEnum)obj;

				long value = enumValue.getEnumIndex();
				
				if( !init )
				{
					init = true;
					lowerLimit = value;
				}
				else if( value < lowerLimit )
				{
					lowerLimit = value;
				}
			}
		}
		
		// Now that we have the lowest limit of the union of values, we can compare
		// each of the offsets to see if they can be expressed in the bit domain
		for( Object obj : set.getValueRangeOrValueEnum() )
		{
			if( obj instanceof org.jts.jsidl.binding.ValueRange )
			{
				org.jts.jsidl.binding.ValueRange rangeValue = (org.jts.jsidl.binding.ValueRange)obj;
				
				long upper = Long.parseLong( rangeValue.getUpperLimit() );
				
				long diff = upper - lowerLimit;
				
				if( diff > maxRange )
				{
					throw new ValidatorException("Value Upper Limit: " + Long.toString( upper ) + 
							" must be representable with " + errorString + 
							" when offset from lower limit: " + lowerLimit);
				}
			}
			else if( obj instanceof org.jts.jsidl.binding.ValueEnum )
			{
				org.jts.jsidl.binding.ValueEnum enumValue = (org.jts.jsidl.binding.ValueEnum)obj;

				long value = enumValue.getEnumIndex();
				
				long diff = value - lowerLimit;
				
				if( diff > maxRange )
				{
					throw new ValidatorException("Value Enum: " + Long.toString( value ) + 
							" must be representable with " + errorString + 
							" when offset from lower limit: " + lowerLimit);
				}
			}
		}
	}
	
	private static void contextValidateWithoutOffset(org.jts.jsidl.binding.ValueSet set, long maxRange, long minRange, String errorString) throws ValidatorException
	{
		for( Object obj : set.getValueRangeOrValueEnum() )
		{
			if( obj instanceof org.jts.jsidl.binding.ValueRange )
			{
				org.jts.jsidl.binding.ValueRange rangeValue = (org.jts.jsidl.binding.ValueRange)obj;
				
				long lower = Long.parseLong( rangeValue.getLowerLimit() );
				long upper = Long.parseLong( rangeValue.getUpperLimit() );
				
				if( lower < minRange )
				{
					throw new ValidatorException("Value Lower Limit: " + Long.toString( lower ) + 
													" must be representable with " + errorString);
				}
				
				if( upper > maxRange )
				{
					throw new ValidatorException("Value Upper Limit: " + Long.toString( upper ) + 
													" must be representable with " + errorString);
				}
			}
			else if( obj instanceof org.jts.jsidl.binding.ValueEnum )
			{
				org.jts.jsidl.binding.ValueEnum enumValue = (org.jts.jsidl.binding.ValueEnum)obj;

				if( enumValue.getEnumIndex() > maxRange ||
						enumValue.getEnumIndex() < minRange)
				{
					throw new ValidatorException("Value Enum: " + Long.toString( enumValue.getEnumIndex() ) + 
													" must be representable with " + errorString);
				}
			}
		}
	}
}
