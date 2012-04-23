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

public class Body 
{
	public static void resolveDeclaredElements(org.jts.jsidl.binding.Body jxBody) 
	{
		// Resolve any internal declared types
		if(jxBody.getDeclaredRecord() != null)
		{
			org.jts.jsidl.binding.DeclaredRecord declaredRecord = jxBody.getDeclaredRecord();
			org.jts.jsidl.binding.Record obj = DeclaredTypeMap.lookupDeclaredRecord(declaredRecord);

			if(obj == null)
			{
				// Error, not found in TypeMap
				throw new ImportException("Declared Record \""+declaredRecord.getName()+"\" with type ref \""+declaredRecord.getDeclaredTypeRef()+"\" not found.");
			}
			else
			{
				Record.resolveDeclaredElements(obj);
				jxBody.setDeclaredRecord(null);
				jxBody.setRecord(obj);
			}
		}
		else if(jxBody.getRecord() != null)
		{
			Record.resolveDeclaredElements(jxBody.getRecord());
		}
		else if(jxBody.getDeclaredList() != null)
		{
			org.jts.jsidl.binding.DeclaredList declaredList = jxBody.getDeclaredList();
			org.jts.jsidl.binding.List obj = DeclaredTypeMap.lookupDeclaredList(declaredList);

			if(obj == null)
			{
				// Error, declared type not found in TypeMap
				throw new ImportException("Declared List \""+declaredList.getName()+"\" with type ref \""+declaredList.getDeclaredTypeRef()+"\" not found.");
			}
			else
			{
				List.resolveDeclaredElements(obj);
				jxBody.setDeclaredList(null);
				jxBody.setList(obj);
			}
		}
		else if(jxBody.getList() != null)
		{
			List.resolveDeclaredElements(jxBody.getList());
		}
		else if(jxBody.getDeclaredVariant() != null)
		{
			org.jts.jsidl.binding.DeclaredVariant declaredVariant = jxBody.getDeclaredVariant();
			org.jts.jsidl.binding.Variant obj = DeclaredTypeMap.lookupDeclaredVariant(declaredVariant);

			if(obj == null)
			{
				// Error, declared type not found in TypeMap
				throw new ImportException("Declared Variant \""+declaredVariant.getName()+"\" with type ref \""+declaredVariant.getDeclaredTypeRef()+"\" not found.");
			}
			else
			{
				Variant.resolveDeclaredElements(obj);
				jxBody.setDeclaredVariant(null);
				jxBody.setVariant(obj);
			}
		}
		else if(jxBody.getVariant() != null)
		{
			Variant.resolveDeclaredElements(jxBody.getVariant());
		}
		else if(jxBody.getDeclaredSequence() != null)
		{
			org.jts.jsidl.binding.DeclaredSequence declaredSequence = jxBody.getDeclaredSequence();
			org.jts.jsidl.binding.Sequence obj = DeclaredTypeMap.lookupDeclaredSequence(declaredSequence);

			if(obj == null)
			{
				// Error, declared type not found in TypeMap
				throw new ImportException("Declared Sequence \""+declaredSequence.getName()+"\" with type ref \""+declaredSequence.getDeclaredTypeRef()+"\" not found.");
			}
			else
			{
				Sequence.resolveDeclaredElements(obj);
				jxBody.setDeclaredSequence(null);
				jxBody.setSequence(obj);
			}
		}
		else if(jxBody.getSequence() != null)
		{
			Sequence.resolveDeclaredElements(jxBody.getSequence());
		}
	}

	public static void resolveDeclaredConstantElements(org.jts.jsidl.binding.Body body)
	{
		if(body.getRecord() != null)
		{
			Record.resolveDeclaredConstantElements(body.getRecord());
		}
		else if(body.getList() != null)
		{
			List.resolveDeclaredConstantElements(body.getList());
		}
		else if(body.getVariant() != null)
		{
			Variant.resolveDeclaredConstantElements(body.getVariant());
		}
		else if(body.getSequence() != null)
		{
			Sequence.resolveDeclaredConstantElements(body.getSequence());
		}
	}
	
}
