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

package org.jts.gui.importJSIDL.declaredElementsResolution;

import org.jts.gui.importJSIDL.ImportException;
import org.jts.jsidl.binding.*;

//   element array {
//      attribute name { identifier },
//      attribute optional {xsd:boolean },
//      attribute interpretation { text }?,
//      ( fixed_field | variable_field |bit_field |
//       fixed_length_string | variable_length_string|
//       variable_length_field |variable_format_field | 
//       declared_fixed_field |declared_variable_field | 
//       declared_bit_field | declared_fixed_length_string | 
//       declared_variable_length_string | declared_variable_length_field | 
//       declared_variable_format_field ),
//      element dimension {
//         attribute name { identifier },
//         attribute size { identifier_ns | xsd:unsignedInt },
//         attribute interpretation { text }?
//      }+
//   }  

public class Array 
{
	public static void importDeclaredType(org.jts.jsidl.binding.Array jxArray)
	{
		Array.resolveDeclaredElements(jxArray);		
		// Add this element to the current map
		DeclaredTypeMap declaredTypeMap = DeclaredTypeMap.getInstance();
		declaredTypeMap.addType(jxArray.getName(), jxArray);
	}

	public static void resolveDeclaredElements(org.jts.jsidl.binding.Array jxArray) 
	{
		// Resolve any internal declared types
		if(jxArray.getDeclaredBitField() != null)
		{
			// Resolve the declared type into the actual object
			org.jts.jsidl.binding.DeclaredBitField declaredBitField = jxArray.getDeclaredBitField();
			org.jts.jsidl.binding.BitField element = DeclaredTypeMap.lookupDeclaredBitField(declaredBitField);
			
			if(element == null)
			{
				// Error, not found in TypeMap
				throw new ImportException("Declared Bit Field \""+declaredBitField.getName()+"\" with type ref \""+declaredBitField.getDeclaredTypeRef()+"\" not found.");
			}
			else
			{
				// Remove from the array
				jxArray.setDeclaredBitField(null);

				// Add to the array
				jxArray.setBitField(element);
			}
		}
		else if(jxArray.getDeclaredFixedField() != null)
		{
			// Resolve the declared type into the actual object
			org.jts.jsidl.binding.DeclaredFixedField declaredFixedField = (org.jts.jsidl.binding.DeclaredFixedField) jxArray.getDeclaredFixedField();
			org.jts.jsidl.binding.FixedField element = DeclaredTypeMap.lookupDeclaredFixedField(declaredFixedField);
			
			if(element == null)
			{
				// Error, not found in TypeMap
				throw new ImportException("Declared Fixed Field \""+declaredFixedField.getName()+"\" with type ref \""+declaredFixedField.getDeclaredTypeRef()+"\" not found.");
			}
			else
			{
				// Remove from the array
				jxArray.setDeclaredFixedField(null);

				// Add to the array
				jxArray.setFixedField(element);
			}
		}
		else if(jxArray.getDeclaredFixedLengthString() != null)
		{
			// Resolve the declared type into the actual object
			org.jts.jsidl.binding.DeclaredFixedLengthString declaredFixedLengthString = (org.jts.jsidl.binding.DeclaredFixedLengthString) jxArray.getDeclaredFixedLengthString();
			org.jts.jsidl.binding.FixedLengthString element = DeclaredTypeMap.lookupDeclaredFixedLengthString(declaredFixedLengthString);
			
			if(element == null)
			{
				// Error, not found in TypeMap
				throw new ImportException("Declared Fixed Length String \""+declaredFixedLengthString.getName()+"\" with type ref \""+declaredFixedLengthString.getDeclaredTypeRef()+"\" not found.");
			}
			else
			{
				// Remove from the array
				jxArray.setDeclaredFixedLengthString(null);

				// Add to the array
				jxArray.setFixedLengthString(element);
			}
		}		
		else if(jxArray.getDeclaredVariableField() != null)
		{
			org.jts.jsidl.binding.DeclaredVariableField declaredVariableField = jxArray.getDeclaredVariableField();
			org.jts.jsidl.binding.VariableField element = DeclaredTypeMap.lookupDeclaredVariableField(declaredVariableField);
			
			if(element == null)
			{
				// Error, not found in TypeMap
				throw new ImportException("Declared Variable Field \""+declaredVariableField.getName()+"\"with type ref \""+declaredVariableField.getDeclaredTypeRef()+"\" not found.");
			}
			else
			{
				// Remove from the array
				jxArray.setDeclaredVariableField(null);

				// Add to the array
				jxArray.setVariableField(element);
			}
		}		
		else if(jxArray.getDeclaredVariableFormatField() != null)
		{
			org.jts.jsidl.binding.DeclaredVariableFormatField declaredVariableFormatField = jxArray.getDeclaredVariableFormatField();
			org.jts.jsidl.binding.VariableFormatField element = DeclaredTypeMap.lookupDeclaredVariableFormatField(declaredVariableFormatField);
			
			if(element == null)
			{
				// Error, not found in TypeMap
				throw new ImportException("Declared Variable Format Field \""+declaredVariableFormatField.getName()+"\"with type ref \""+declaredVariableFormatField.getDeclaredTypeRef()+"\" not found.");
			}
			else
			{
				// Remove from the array
				jxArray.setDeclaredVariableFormatField(null);

				// Add to the array
				jxArray.setVariableFormatField(element);
			}
		}		
		else if(jxArray.getDeclaredVariableLengthField() != null)
		{
			org.jts.jsidl.binding.DeclaredVariableLengthField declaredVariableLengthField = jxArray.getDeclaredVariableLengthField();
			org.jts.jsidl.binding.VariableLengthField element = DeclaredTypeMap.lookupDeclaredVariableLengthField(declaredVariableLengthField);
			
			if(element == null)
			{
				// Error, not found in TypeMap
				throw new ImportException("Declared Variable Length Field \""+declaredVariableLengthField.getName()+"\"with type ref \""+declaredVariableLengthField.getDeclaredTypeRef()+"\" not found.");
			}
			else
			{
				// Remove from the array
				jxArray.setDeclaredVariableLengthField(null);

				// Add to the array
				jxArray.setVariableLengthField(element);
			}
		}		
		else if(jxArray.getDeclaredVariableLengthString() != null)
		{
			org.jts.jsidl.binding.DeclaredVariableLengthString declaredVariableLengthString = jxArray.getDeclaredVariableLengthString();
			org.jts.jsidl.binding.VariableLengthString element = DeclaredTypeMap.lookupDeclaredVariableLengthString(declaredVariableLengthString);
			
			if(element == null)
			{
				// Error, not found in TypeMap
				throw new ImportException("Declared Variable Length String \""+declaredVariableLengthString.getName()+"\"with type ref \""+declaredVariableLengthString.getDeclaredTypeRef()+"\" not found.");
			}
			else
			{
				// Remove from the array
				jxArray.setDeclaredVariableLengthString(null);

				// Add to the array
				jxArray.setVariableLengthString(element);
			}
		}
	}

	public static void resolveDeclaredConstantElements(org.jts.jsidl.binding.Array array)
	{
		// fixed_field | variable_field |bit_field |
		// fixed_length_string | variable_length_string|
		// variable_length_field |variable_format_field |
		if(array.getFixedField() != null)
		{
			DeclaredConstantMap.lookupFixedField(array.getFixedField());
		}
		else if(array.getVariableField() != null)
		{
			DeclaredConstantMap.lookupVariableField(array.getVariableField());
		}
		else if(array.getBitField() != null)
		{
			DeclaredConstantMap.lookupBitField(array.getBitField());
		}
		else if(array.getFixedLengthString() != null)
		{
			DeclaredConstantMap.lookupFixedLengthString(array.getFixedLengthString());
		}
		else if(array.getVariableLengthString() != null)
		{
			DeclaredConstantMap.lookupVariableLengthString(array.getVariableLengthString());
		}
		else if(array.getVariableLengthField() != null)
		{
			DeclaredConstantMap.lookupVariableLengthField(array.getVariableLengthField());
		}
		else if(array.getVariableFormatField() != null)
		{
			DeclaredConstantMap.lookupVariableFormatField(array.getVariableFormatField());
		}
	}
	
}
