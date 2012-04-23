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

import org.jts.codegenerator.*;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.io.File;

public class TemplateHandler
{
	private Hashtable<String, String> replaceTable;
	private Set<String> keySet;
	
	public TemplateHandler(Hashtable<String, String> replaceTable)
	{
		this.replaceTable = replaceTable;
		this.keySet = replaceTable.keySet();
	}
	
	public void adjustFile(File dest) throws Exception
	{
		try
		{			
			String output = Util.getContents(dest);
			
			for (Iterator<String> it = keySet.iterator(); it.hasNext(); )
			{
				String currentKey = it.next();
				output = output.replace(currentKey, (String)replaceTable.get(currentKey));
			}

			Util.writeContents(dest, output);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	
	public String adjustString(String input)
	{
		String output = input;
		
		for (Iterator<String> it = keySet.iterator(); it.hasNext(); )
		{
			String currentKey = it.next();
			output = output.replace(currentKey, replaceTable.get(currentKey));
		}
		
		return output;
	}	
}
