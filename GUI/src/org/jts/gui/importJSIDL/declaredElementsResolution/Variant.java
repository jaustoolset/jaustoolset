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

//	   element variant { 
//	      attribute name { identifier },
//	      attribute optional {xsd:boolean },
//	      attribute interpretation { text }?,
//	      vtag_field,
//	      ( record | declared_record |
//	         \list | declared_list |
//	         variant | declared_variant |
//	         sequence | declared_sequence )*
//	   }

public class Variant 
{
	public static void importDeclaredType(org.jts.jsidl.binding.Variant jxVariant)
	{
		Variant.resolveDeclaredElements(jxVariant);
		
		// Add this element to the current map
		DeclaredTypeMap declaredTypeMap = DeclaredTypeMap.getInstance();
		declaredTypeMap.addType(jxVariant.getName(), jxVariant);
	}

	public static void resolveDeclaredElements(org.jts.jsidl.binding.Variant jxVariant)
	{
		// Resolve any internal declared types
		// Complex Fields
	    java.util.List jxList = jxVariant.getRecordOrDeclaredRecordOrList();
	    if(jxList != null)
	    {
			for(int i = 0; i < jxList.size(); i++)
			{
				if( jxList.get(i) instanceof org.jts.jsidl.binding.DeclaredRecord )
				{
					org.jts.jsidl.binding.DeclaredRecord declaredRecord = (org.jts.jsidl.binding.DeclaredRecord) jxList.get(i);
					org.jts.jsidl.binding.Record obj = DeclaredTypeMap.lookupDeclaredRecord(declaredRecord);

					if(obj == null)
					{
						// Error, not found in TypeMap
						throw new ImportException("Declared Record \""+declaredRecord.getName()+"\" with type ref \""+declaredRecord.getDeclaredTypeRef()+"\" not found.");
					}
					else
					{
						Record.resolveDeclaredElements(obj);
						jxList.remove(i);
						jxList.add(i, obj);
					}
				}
		        else if( jxList.get(i) instanceof org.jts.jsidl.binding.DeclaredList )
				{
					org.jts.jsidl.binding.DeclaredList declaredList = (org.jts.jsidl.binding.DeclaredList) jxList.get(i);
					org.jts.jsidl.binding.List obj = DeclaredTypeMap.lookupDeclaredList(declaredList);

					if(obj == null)
					{
						// Error, declared type not found in TypeMap
						throw new ImportException("Declared List \""+declaredList.getName()+"\" with type ref \""+declaredList.getDeclaredTypeRef()+"\" not found.");
					}
					else
					{
						List.resolveDeclaredElements(obj);
						jxList.remove(i);
						jxList.add(i, obj);
					}
				}
		        else if( jxList.get(i) instanceof org.jts.jsidl.binding.DeclaredVariant )
				{
		        	org.jts.jsidl.binding.DeclaredVariant declaredVariant = (org.jts.jsidl.binding.DeclaredVariant) jxList.get(i);
					org.jts.jsidl.binding.Variant obj = DeclaredTypeMap.lookupDeclaredVariant(declaredVariant);

					if(obj == null)
					{
						// Error, declared type not found in TypeMap
						throw new ImportException("Declared Variant \""+declaredVariant.getName()+"\" with type ref \""+declaredVariant.getDeclaredTypeRef()+"\" not found.");
					}
					else
					{
						Variant.resolveDeclaredElements(obj);						
						jxList.remove(i);
						jxList.add(i, obj);
					}
				}
		        else if( jxList.get(i) instanceof org.jts.jsidl.binding.DeclaredSequence )
				{
					org.jts.jsidl.binding.DeclaredSequence declaredSequence = (org.jts.jsidl.binding.DeclaredSequence) jxList.get(i);
					org.jts.jsidl.binding.Sequence obj = DeclaredTypeMap.lookupDeclaredSequence(declaredSequence);

					if(obj == null)
					{
						// Error, declared type not found in TypeMap
						throw new ImportException("Declared Sequence \""+declaredSequence.getName()+"\" with type ref \""+declaredSequence.getDeclaredTypeRef()+"\" not found.");
					}
					else
					{
						jxList.remove(i);
						jxList.add(i, obj);
					}
				}
				else if(jxList.get(i) instanceof org.jts.jsidl.binding.Record )
				{
					Record.resolveDeclaredElements((org.jts.jsidl.binding.Record) jxList.get(i));
				}
				else if(jxList.get(i) instanceof org.jts.jsidl.binding.List )
				{
					List.resolveDeclaredElements((org.jts.jsidl.binding.List) jxList.get(i));
				}
				else if(jxList.get(i) instanceof org.jts.jsidl.binding.Variant )
				{
					Variant.resolveDeclaredElements((org.jts.jsidl.binding.Variant) jxList.get(i));
				}
				else if(jxList.get(i) instanceof org.jts.jsidl.binding.Sequence )
				{
					Sequence.resolveDeclaredElements((org.jts.jsidl.binding.Sequence) jxList.get(i));
				}
			}
	    }			
	}

	public static void resolveDeclaredConstantElements(org.jts.jsidl.binding.Variant variant)
	{
	    java.util.List jxList = variant.getRecordOrDeclaredRecordOrList();
	    
	    if(jxList != null)
	    {
			for(int i = 0; i < jxList.size(); i++)
			{
				if(jxList.get(i) instanceof org.jts.jsidl.binding.Record )
				{
					Record.resolveDeclaredConstantElements((org.jts.jsidl.binding.Record) jxList.get(i));
				}
				else if(jxList.get(i) instanceof org.jts.jsidl.binding.List )
				{
					List.resolveDeclaredConstantElements((org.jts.jsidl.binding.List) jxList.get(i));
				}
				else if(jxList.get(i) instanceof org.jts.jsidl.binding.Variant )
				{
					Variant.resolveDeclaredConstantElements((org.jts.jsidl.binding.Variant) jxList.get(i));
				}
				else if(jxList.get(i) instanceof org.jts.jsidl.binding.Sequence )
				{
					Sequence.resolveDeclaredConstantElements((org.jts.jsidl.binding.Sequence) jxList.get(i));
				}
			}
	    }
	}

}
