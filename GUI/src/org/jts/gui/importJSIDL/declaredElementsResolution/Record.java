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

/*
 * record =
 * element record {
 *     attribute name { identifier }, 
 *     attribute optional {xsd:boolean },
 *     attribute interpretation { text }?,
 *     presence_vector?,
 *     ( array | fixed_field | variable_field | bit_field |  
 *       fixed_length_string | variable_length_string |
 *       variable_length_field |variable_format_field | 
 *       declared_array | declared_fixed_field |declared_variable_field | 
 *       declared_bit_field | declared_fixed_length_string | 
 *       declared_variable_length_string | declared_variable_length_field | 
 *       declared_variable_format_field )+
 *  }
 */

public class Record 
{
	public static void resolveDeclaredElements(org.jts.jsidl.binding.Record jxRecord)
	{
		// Resolve any internal declared types
		java.util.List<Object> jxList = jxRecord.getArrayOrFixedFieldOrVariableField();
		if(jxList != null)
		{
			for(int i = 0; i < jxList.size(); i++) 
			{
				Object obj = jxList.get(i);
				
				if( obj instanceof org.jts.jsidl.binding.DeclaredArray )
		        {
					// Resolve the declared type into the actual object
					org.jts.jsidl.binding.DeclaredArray declaredArray = (org.jts.jsidl.binding.DeclaredArray) obj;
					org.jts.jsidl.binding.Array element = DeclaredTypeMap.lookupDeclaredArray(declaredArray);
					
					if(element == null)
					{
						// Error, not found in TypeMap
						throw new ImportException("Declared Array \""+declaredArray.getName()+"\" with type ref \""+declaredArray.getDeclaredTypeRef()+"\" not found.");
					}
					else
					{
						Array.resolveDeclaredElements(element);
						jxList.remove(i);
						jxList.add(i, element);
					}
		        }
				else if( obj instanceof org.jts.jsidl.binding.DeclaredFixedField )
				{
					// Resolve the declared type into the actual object
					org.jts.jsidl.binding.DeclaredFixedField declaredFixedField = (org.jts.jsidl.binding.DeclaredFixedField) obj;
					org.jts.jsidl.binding.FixedField element = DeclaredTypeMap.lookupDeclaredFixedField(declaredFixedField);
					
					if(element == null)
					{
						// Error, not found in TypeMap
						throw new ImportException("Declared Fixed Field \""+declaredFixedField.getName()+"\" with type ref \""+declaredFixedField.getDeclaredTypeRef()+"\" not found.");
					}
					else
					{
						jxList.remove(i);
						jxList.add(i, element);
					}
				}
				else if( obj instanceof org.jts.jsidl.binding.DeclaredVariableField )
				{
					// Resolve the declared type into the actual object
					org.jts.jsidl.binding.DeclaredVariableField declaredVariableField = (org.jts.jsidl.binding.DeclaredVariableField) obj;
					org.jts.jsidl.binding.VariableField element = DeclaredTypeMap.lookupDeclaredVariableField(declaredVariableField);
					
					if(element == null)
					{
						// Error, not found in TypeMap
						throw new ImportException("Declared Variable Field \""+declaredVariableField.getName()+"\" with type ref \""+declaredVariableField.getDeclaredTypeRef()+"\" not found.");
					}
					else
					{
						jxList.remove(i);
						jxList.add(i, element);
					}
				}
				else if( obj instanceof org.jts.jsidl.binding.DeclaredBitField )
				{
					// Resolve the declared type into the actual object
					org.jts.jsidl.binding.DeclaredBitField declaredBitField = (org.jts.jsidl.binding.DeclaredBitField) obj;
					org.jts.jsidl.binding.BitField element = DeclaredTypeMap.lookupDeclaredBitField(declaredBitField);
					
					if(element == null)
					{
						// Error, not found in TypeMap
						throw new ImportException("Declared Bit Field \""+declaredBitField.getName()+"\" with type ref \""+declaredBitField.getDeclaredTypeRef()+"\" not found.");
					}
					else
					{
						jxList.remove(i);
						jxList.add(i, element);
					}
				}
				else if( obj instanceof org.jts.jsidl.binding.DeclaredFixedLengthString )
				{
					// Resolve the declared type into the actual object
					org.jts.jsidl.binding.DeclaredFixedLengthString declaredFixedLengthString = (org.jts.jsidl.binding.DeclaredFixedLengthString) obj;
					org.jts.jsidl.binding.FixedLengthString element = DeclaredTypeMap.lookupDeclaredFixedLengthString(declaredFixedLengthString);
					
					if(element == null)
					{
						// Error, not found in TypeMap
						throw new ImportException("Declared Fixed Length String \""+declaredFixedLengthString.getName()+"\"with type ref \""+declaredFixedLengthString.getDeclaredTypeRef()+"\" not found.");
					}
					else
					{
						jxList.remove(i);
						jxList.add(i, element);
					}
				}
				else if( obj instanceof org.jts.jsidl.binding.DeclaredVariableLengthString )
				{
					// Resolve the declared type into the actual object
					org.jts.jsidl.binding.DeclaredVariableLengthString declaredVariableLengthString = (org.jts.jsidl.binding.DeclaredVariableLengthString) obj;
					org.jts.jsidl.binding.VariableLengthString element = DeclaredTypeMap.lookupDeclaredVariableLengthString(declaredVariableLengthString);
					
					if(element == null)
					{
						// Error, not found in TypeMap
						throw new ImportException("Declared Variable Length String \""+declaredVariableLengthString.getName()+"\" with type ref \""+declaredVariableLengthString.getDeclaredTypeRef()+"\" not found.");
					}
					else
					{
						jxList.remove(i);
						jxList.add(i, element);
					}
				}
				else if( obj instanceof org.jts.jsidl.binding.DeclaredVariableField )
				{
					// Resolve the declared type into the actual object
					org.jts.jsidl.binding.DeclaredVariableField declaredVariableField = (org.jts.jsidl.binding.DeclaredVariableField) obj;
					org.jts.jsidl.binding.VariableField element = DeclaredTypeMap.lookupDeclaredVariableField(declaredVariableField);
					
					if(element == null)
					{
						// Error, not found in TypeMap
						throw new ImportException("Declared Variable Field \""+declaredVariableField.getName()+"\"with type ref \""+declaredVariableField.getDeclaredTypeRef()+"\" not found.");
					}
					else
					{
						jxList.remove(i);
						jxList.add(i, element);
					}
				}
				else if( obj instanceof org.jts.jsidl.binding.DeclaredVariableFormatField )
				{
					// Resolve the declared type into the actual object
					org.jts.jsidl.binding.DeclaredVariableFormatField declaredVariableFormatField = (org.jts.jsidl.binding.DeclaredVariableFormatField) obj;
					org.jts.jsidl.binding.VariableFormatField element = DeclaredTypeMap.lookupDeclaredVariableFormatField(declaredVariableFormatField);
					
					if(element == null)
					{
						// Error, not found in TypeMap
						throw new ImportException("Declared Variable Format Field \""+declaredVariableFormatField.getName()+"\" with type ref \""+declaredVariableFormatField.getDeclaredTypeRef()+"\" not found.");
					}
					else
					{
						jxList.remove(i);
						jxList.add(i, element);
					}
				}
				else if( obj instanceof org.jts.jsidl.binding.DeclaredVariableLengthField )
				{
					// Resolve the declared type into the actual object
					org.jts.jsidl.binding.DeclaredVariableLengthField declaredVariableLengthField = (org.jts.jsidl.binding.DeclaredVariableLengthField) obj;
					org.jts.jsidl.binding.VariableLengthField element = DeclaredTypeMap.lookupDeclaredVariableLengthField(declaredVariableLengthField);
					
					if(element == null)
					{
						// Error, not found in TypeMap
						throw new ImportException("Declared Variable Length Field \""+declaredVariableLengthField.getName()+"\" with type ref \""+declaredVariableLengthField.getDeclaredTypeRef()+"\" not found.");
					}
					else
					{
						jxList.remove(i);
						jxList.add(i, element);
					}
				}				
				else if( obj instanceof org.jts.jsidl.binding.Array )
				{
					Array.resolveDeclaredElements((org.jts.jsidl.binding.Array) obj);
				}
			}
		}
	}

	public static void resolveDeclaredConstantElements(org.jts.jsidl.binding.Record record)
	{
		// array | fixed_field | variable_field | bit_field |  
		// fixed_length_string | variable_length_string |
		// variable_length_field |variable_format_field
		
		java.util.List<Object> jxList = record.getArrayOrFixedFieldOrVariableField();
		
		if(jxList != null)
		{
			for(int i = 0; i < jxList.size(); i++) 
			{
				Object obj = jxList.get(i);
				
				if(obj instanceof org.jts.jsidl.binding.Array)
		        {
					Array.resolveDeclaredConstantElements((org.jts.jsidl.binding.Array) obj);
		        }
				else if(obj instanceof org.jts.jsidl.binding.FixedField)
		        {
					DeclaredConstantMap.lookupFixedField((org.jts.jsidl.binding.FixedField) obj);
		        }
				else if(obj instanceof org.jts.jsidl.binding.VariableField)
		        {
					DeclaredConstantMap.lookupVariableField((org.jts.jsidl.binding.VariableField) obj);
		        }
				else if(obj instanceof org.jts.jsidl.binding.BitField)
		        {
					DeclaredConstantMap.lookupBitField((org.jts.jsidl.binding.BitField) obj);
		        }
				else if(obj instanceof org.jts.jsidl.binding.FixedLengthString)
		        {
					DeclaredConstantMap.lookupFixedLengthString((org.jts.jsidl.binding.FixedLengthString) obj);
		        }
				else if(obj instanceof org.jts.jsidl.binding.VariableLengthString)
		        {
					DeclaredConstantMap.lookupVariableLengthString((org.jts.jsidl.binding.VariableLengthString) obj);
		        }
				else if(obj instanceof org.jts.jsidl.binding.VariableLengthField)
		        {
					DeclaredConstantMap.lookupVariableLengthField((org.jts.jsidl.binding.VariableLengthField) obj);
		        }
				else if(obj instanceof org.jts.jsidl.binding.VariableFormatField)
		        {
					DeclaredConstantMap.lookupVariableFormatField((org.jts.jsidl.binding.VariableFormatField) obj);
		        }
			}
		}		
	}
	
}
