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

import java.util.HashMap;
import java.util.HashSet;

import org.jts.jsidl.binding.TypeAndUnitsEnum;

public class VariableField 
{
	/**
	 * Validates a JSIDL Variable Field element.
	 * <pre>
		variable_field = 
		    element variable_field {
		        attribute name { identifier }, 
  					# 1. Must follow ANSI C syntax              
    			attribute optional {xsd:boolean },
		        attribute interpretation { text }?,  
		        element type_and_units_field {
		          type_and_units_enum+ 
		        }
		    }

    		variable_field = 
		    element variable_field {
		        attribute name { identifier },
		        attribute optional {xsd:boolean },
		        attribute interpretation { text }?,  
		        type_and_units_enum+
		        	# 1. Must be specified (cannot be null)
		    }
	 * </pre>
     * @param variableField JSIDL binding object of the variable field to be checked
	 * @throws ValidatorException if the object has an invalid name string, or does not contain any type and units.
	 */
	public static void validate(org.jts.jsidl.binding.VariableField variableField) throws ValidatorException
	{
		Validator.validateName(variableField.getName());
		
		if( variableField.getTypeAndUnitsField() == null || 
		   variableField.getTypeAndUnitsField().getTypeAndUnitsEnum() == null || 
		   variableField.getTypeAndUnitsField().getTypeAndUnitsEnum().size() == 0 )
		{
			throw new ValidatorException("At least one type and units enum must be defined");
		}
		
		// enums shouldn't have duplicate index values
		HashMap< Short, String > indices = new HashMap< Short, String >();
		for( TypeAndUnitsEnum e : variableField.getTypeAndUnitsField().getTypeAndUnitsEnum() )
		{
			if( indices.isEmpty() )
			{
				indices.put( e.getIndex(), e.getName() );
				continue;
			}
			
			if( indices.containsKey( e.getIndex() ) )
			{
				String storedEnum = indices.get( e.getIndex() );
				throw new ValidatorException("Duplicate index values not allowed: " + 
													e.getName() + 
													" and " + 
													storedEnum );
			}
			
			indices.put( e.getIndex(), e.getName() );
		}
	}
}
