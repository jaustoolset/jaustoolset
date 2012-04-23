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

public class InternalEventsSet {

	public static void resolveDeclaredElements( org.jts.jsidl.binding.InternalEventsSet internalEventsSet) 
	{
		if(internalEventsSet.getEventDefOrDeclaredEventDef() != null)
		{
			java.util.List jxList = internalEventsSet.getEventDefOrDeclaredEventDef();
			for(int i = 0; i < jxList.size(); i++)
			{
				if(jxList.get(i) instanceof org.jts.jsidl.binding.EventDef)
				{
					EventDef.resolveDeclaredElements((org.jts.jsidl.binding.EventDef) jxList.get(i));
				}
				else if(jxList.get(i) instanceof org.jts.jsidl.binding.DeclaredEventDef)
				{
					org.jts.jsidl.binding.DeclaredEventDef declaredEventDef = (org.jts.jsidl.binding.DeclaredEventDef) jxList.get(i); 
					org.jts.jsidl.binding.EventDef obj = DeclaredTypeMap.lookupDeclaredEventDef(declaredEventDef);

					if(obj == null)
					{
						// Error, declared type not found in TypeMap
						throw new ImportException("Declared EventDef \""+declaredEventDef.getName()+"\" with type ref \""+declaredEventDef.getDeclaredTypeRef()+"\" not found.");
					}
					else
					{
						EventDef.resolveDeclaredElements(obj);
						jxList.remove(i);
						jxList.add(i, obj);
					}					
				}
			}
		}
	}

	public static void resolveDeclaredConstantElements(org.jts.jsidl.binding.InternalEventsSet internalEventsSet)
	{
		if(internalEventsSet.getEventDefOrDeclaredEventDef() != null)
		{
			java.util.List jxList = internalEventsSet.getEventDefOrDeclaredEventDef();
			
			for(int i = 0; i < jxList.size(); i++)
			{
				if(jxList.get(i) instanceof org.jts.jsidl.binding.MessageDef)
				{
					EventDef.resolveDeclaredConstantElements((org.jts.jsidl.binding.EventDef) jxList.get(i));
				}
				else
				{
					//throw new ImportException("All elements are expected to be resolved at this point");
				}
			}
		}
	}
}
