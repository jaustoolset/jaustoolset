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

import java.io.File;
import java.io.FileFilter;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.jts.gui.importJSIDL.ImportException;

public class DeclaredConstantSet 
{
	public static org.jts.jsidl.binding.DeclaredConstSet resolveReferencedConstantSets(org.jts.jsidl.binding.DeclaredConstSet jxConstSet, Map map) throws ImportException
	{
		return resolveReferencedConstantSets(jxConstSet, map, null);
	}
	
	private static org.jts.jsidl.binding.DeclaredConstSet resolveReferencedConstantSets(org.jts.jsidl.binding.DeclaredConstSet jxConstSet, Map map, org.jts.jsidl.binding.DeclaredConstSet parent) throws ImportException
	{
		// Find declared_const_set_ref and add them to this declared_const_set
		List<org.jts.jsidl.binding.DeclaredConstSetRef> refList = jxConstSet.getDeclaredConstSetRef();
		if(refList  != null)
		{			
			// Create a hashMap for DeclaredConstSets from the search directory
			HashMap<String, org.jts.jsidl.binding.DeclaredConstSet> declaredConstSets = new HashMap<String, org.jts.jsidl.binding.DeclaredConstSet>();
			
		    
		    
			for( Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); )
			{
				// Unmarshall the file using JAXB
				try
				{
					String key = iter.next();
				         Object obj = map.get(key);
			         
					if( obj instanceof org.jts.jsidl.binding.DeclaredConstSet )
					{
						declaredConstSets.put(key, ( org.jts.jsidl.binding.DeclaredConstSet) obj );
					}
				}
				catch( Exception ex ) 
				{
					ex.printStackTrace();
				}
			}

			for(org.jts.jsidl.binding.DeclaredConstSetRef reference:refList)
			{
				// Generate the HashMap key string
				String key = reference.getId() + "-" + reference.getVersion();
				
				// Get object from HashMap
				org.jts.jsidl.binding.DeclaredConstSet constSet = declaredConstSets.get(key); 

				// Recursive Import
				if(constSet != null)
				{
					if(parent == null)
					{
						resolveReferencedConstantSets(constSet, map, jxConstSet);
					}
					else
					{
						resolveReferencedConstantSets(constSet, map, parent);
					}
				}
				else
				{
					// Unfound reference!
					throw new ImportException("Constant Set resolution error. declared_const_set_ref with name " + reference.getName() + "-v" + reference.getVersion() + " not found. Referenced by " + jxConstSet.getName());
				}
			}
		}
	
		if(parent != null)
		{
			// need to move our "constants" into our parent's container
			parent.getConstDef().addAll(jxConstSet.getConstDef());
		}
		
		return jxConstSet;
	}  
}