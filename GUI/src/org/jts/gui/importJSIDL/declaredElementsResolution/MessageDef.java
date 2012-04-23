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

public class MessageDef 
{
	public static void resolveDeclaredElements(org.jts.jsidl.binding.MessageDef jxMessageDef)
	{
		// Resolve any declared types
		if(jxMessageDef.getDeclaredHeader() != null)
		{
			org.jts.jsidl.binding.DeclaredHeader declaredHeader = jxMessageDef.getDeclaredHeader();
			org.jts.jsidl.binding.Header obj = DeclaredTypeMap.lookupDeclaredHeader(declaredHeader);

			if(obj == null)
			{
				// Error, declared type not found in TypeMap
				throw new ImportException("Declared Header \""+declaredHeader.getName()+"\" with type ref \""+declaredHeader.getDeclaredTypeRef()+"\" not found.");
			}
			else
			{
				Header.resolveDeclaredElements(obj);
				jxMessageDef.setDeclaredHeader(null);
				jxMessageDef.setHeader(obj);
			}
		}
		else if(jxMessageDef.getHeader() != null)
		{
			Header.resolveDeclaredElements(jxMessageDef.getHeader());
		}
		
		if(jxMessageDef.getDeclaredBody() != null)
		{
			org.jts.jsidl.binding.DeclaredBody declaredBody = jxMessageDef.getDeclaredBody();
			org.jts.jsidl.binding.Body obj = DeclaredTypeMap.lookupDeclaredBody(declaredBody);

			if(obj == null)
			{
				// Error, declared type not found in TypeMap
				throw new ImportException("Declared Body \""+declaredBody.getName()+"\" with type ref \""+declaredBody.getDeclaredTypeRef()+"\" not found.");
			}
			else
			{
				Body.resolveDeclaredElements(obj);
				jxMessageDef.setDeclaredBody(null);
				jxMessageDef.setBody(obj);
			}
		}
		else if(jxMessageDef.getBody() != null)
		{
			Body.resolveDeclaredElements(jxMessageDef.getBody());
		}
		
		if(jxMessageDef.getDeclaredFooter() != null)
		{
			org.jts.jsidl.binding.DeclaredFooter declaredFooter = jxMessageDef.getDeclaredFooter();
			org.jts.jsidl.binding.Footer obj = DeclaredTypeMap.lookupDeclaredFooter(declaredFooter);

			if(obj == null)
			{
				// Error, declared type not found in TypeMap
				throw new ImportException("Declared Footer \""+declaredFooter.getName()+"\" with type ref \""+declaredFooter.getDeclaredTypeRef()+"\" not found.");
			}
			else
			{
				Footer.resolveDeclaredElements(obj);
				jxMessageDef.setDeclaredFooter(null);
				jxMessageDef.setFooter(obj);
			}
		}
		else if(jxMessageDef.getFooter() != null)
		{
			Footer.resolveDeclaredElements(jxMessageDef.getFooter());
		}
		
	}

	public static void resolveDeclaredConstantElements(org.jts.jsidl.binding.MessageDef messageDef)
	{
		if(messageDef.getHeader() != null)
		{
			Header.resolveDeclaredConstantElements(messageDef.getHeader());
		}
		
		if(messageDef.getBody() != null)
		{
			Body.resolveDeclaredConstantElements(messageDef.getBody());
		}
		
		if(messageDef.getFooter() != null)
		{
			Footer.resolveDeclaredConstantElements(messageDef.getFooter());
		}
	}
	
}
