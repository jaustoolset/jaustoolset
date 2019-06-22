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

public class VariableField 
{
	public static void importDeclaredType(org.jts.jsidl.binding.VariableField jxVariableField)
	{
		VariableField.resolveDeclaredElements(jxVariableField);		
		// Add this element to the current map
		DeclaredTypeMap declaredTypeMap = DeclaredTypeMap.getInstance();
		declaredTypeMap.addType(jxVariableField.getName(), jxVariableField);
	}

	public static void resolveDeclaredElements(org.jts.jsidl.binding.VariableField jxVariableField) 
	{
		// Resolve any internal declared types in the type and units field
        if (jxVariableField.getTypeAndUnitsField() != null)
        {
			for (TypeAndUnitsEnum type : jxVariableField.getTypeAndUnitsField().getTypeAndUnitsEnum())
			{
				if (type.getDeclaredValueSet() != null)
				{
					// Resolve the declared type into the actual object
					org.jts.jsidl.binding.DeclaredValueSet declaredValueSet = type.getDeclaredValueSet();
					org.jts.jsidl.binding.ValueSet element = DeclaredTypeMap.lookupDeclaredValueSet(declaredValueSet);
				
					if(element == null)
					{
						// Error, not found in TypeMap
						throw new ImportException("Declared Value Set \""+declaredValueSet.getName()+"\" with type ref \""+declaredValueSet.getDeclaredTypeRef()+"\" not found.");
					}
					else
					{
						// Remove from the array
						type.setDeclaredValueSet(null);

						// Add to the array
						type.setValueSet(element);
					}
				}
			}
        }
	}
}
