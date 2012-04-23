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

package org.jts.codegenerator.support;

import org.jts.jsidl.binding.ClientOf;
import org.jts.jsidl.binding.InheritsFrom;
import org.jts.jsidl.binding.ServiceDef;

public class ReferenceHolder
{
	String name;
	String id;
	String version;
	
	public ReferenceHolder()
	{
	}
	
	public ReferenceHolder(String name, String id, String version)
	{
		this.name = name.toLowerCase();
		this.id = id.toLowerCase();
		this.version = version.toLowerCase();
	}
	
	public ReferenceHolder(InheritsFrom ref)
	{
		if(ref != null)
		{
			this.name = ref.getName().toLowerCase();
			this.id = ref.getId().toLowerCase();
			this.version = ref.getVersion().toLowerCase();
		}
	}
	
	public ReferenceHolder(ClientOf ref)
	{
		if(ref != null)
		{
			this.name = ref.getName().toLowerCase();
			this.id = ref.getId().toLowerCase();
			this.version = ref.getVersion().toLowerCase();
		}
	}
	
	public ReferenceHolder(ServiceDef ref)
	{
		if(ref != null)
		{
			this.name = ref.getName().toLowerCase();
			this.id = ref.getId().toLowerCase();
			this.version = ref.getVersion().toLowerCase();
		}
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj != null)
		{
			if(obj instanceof ReferenceHolder)
			{
				return name.equals(((ReferenceHolder) obj).getName().toLowerCase())
				&& id.equals(((ReferenceHolder) obj).getId().toLowerCase())
				&& version.equals(((ReferenceHolder) obj).getVersion().toLowerCase());
			}
			else if(obj instanceof InheritsFrom)
			{
				return name.equals(((InheritsFrom) obj).getName().toLowerCase())
				&& id.equals(((InheritsFrom) obj).getId().toLowerCase())
				&& version.equals(((InheritsFrom) obj).getVersion().toLowerCase());
			}
			else if(obj instanceof ClientOf)
			{
				return name.equals(((ClientOf) obj).getName().toLowerCase())
				&& id.equals(((ClientOf) obj).getId().toLowerCase())
				&& version.equals(((ClientOf) obj).getVersion().toLowerCase());	
			}
			else if(obj instanceof ServiceDef)
			{
				return name.equals(((ServiceDef) obj).getName().toLowerCase())
				&& id.equals(((ServiceDef) obj).getId().toLowerCase())
				&& version.equals(((ServiceDef) obj).getVersion().toLowerCase());
			}
		}
		
		return false;
	}

	public int hashCode()
	{
		return this.toString().hashCode();
	}
	
	@Override
	public String toString()
	{
		return name + "::" + id + "::" + version;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
