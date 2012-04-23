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

import java.util.List;
import java.util.Map;

public class ServiceSet 
{
	public static void resolveDeclaredElements( org.jts.jsidl.binding.ServiceSet jxServiceSet, Map map )
	{
		// Import declared type sets
		if(jxServiceSet.getDeclaredTypeSet() != null)
		{
			List<org.jts.jsidl.binding.DeclaredTypeSet> referenceList = jxServiceSet.getDeclaredTypeSet();
			
			for(int i = 0; i < referenceList.size(); i++)
			{
				DeclaredTypeSet.importDeclaredTypeSet( referenceList.get(i), map );
			}
		}
		
		// Import declared constants
		if(jxServiceSet.getDeclaredConstSet() != null)
		{
			List<org.jts.jsidl.binding.DeclaredConstSet> constList = jxServiceSet.getDeclaredConstSet();
			
			for(int i = 0; i < constList.size(); i++)
			{
				DeclaredConstantSet.resolveReferencedConstantSets( constList.get(i), map );
			}
		}
		
		// Import declared service defs
		// Removed from JSIDL+ D. Kent 6/1/09
/*		if(jxServiceSet.getDeclaredServiceDef() != null)
		{
			List<org.jts.jsidl.binding.DeclaredServiceDef> serviceDefList = jxServiceSet.getDeclaredServiceDef();
			
			for(int i = 0; i < serviceDefList.size(); i++)
			{
				org.jts.jsidl.binding.ServiceDef jxService = DeclaredServiceDef.importDeclaredService(serviceDefList.get(i), searchPath);

				if(jxService != null)
				{
					jxServiceSet.getServiceDef().add(jxService);
				}
			}
		}
*/		

		// For every service in this service set, resolve declared types
		if(jxServiceSet.getServiceDef() != null)
		{
			List jxList = jxServiceSet.getServiceDef();
			for(int i = 0; i < jxList.size(); i++)
			{
				ServiceDef.resolveDeclaredElements((org.jts.jsidl.binding.ServiceDef) jxList.get(i), map);
			}
		}
		
	}
	
}
