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

package org.jts.gui.JAXBtoJmatter;


import org.jts.gui.importJSIDL.ImportMessages;

import com.u2d.app.Context;

// This class converts a JAXB Input Set to a JMatter input set
public class List
{
	public static Object getStored( Object jaxbObject, Long generatedId )
	{
		org.jts.gui.util.QueryBuilder builder = new org.jts.gui.util.QueryBuilder();
		builder.setTableName( "List" );
		builder.addNameArg();
		builder.setFixCount();
		
		return builder.getStored( jaxbObject, generatedId );
	}

	// This static method does the conversion from JAXB Binding to JMatter object 
	public static com.u2d.generated.List lookupOrCreate( org.jts.jsidl.binding.List jxList ) 
	{
		// Get and handle on a hibernate session
		com.u2d.persist.HBMSingleSession persistenceMechanism = (com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism(); 
		
		com.u2d.generated.List jmList = (com.u2d.generated.List)getStored( jxList, null );
		
		// if the returned jmMessageDef object is null, then the db does not contain this messageDef
		// so we will create it and add it to the db
		if( jmList == null )
		{
			jmList = new com.u2d.generated.List();
			
			// Name
			jmList.getName().setValue(jxList.getName());
			
			// Interpretation
		    if(jxList.getInterpretation() != null)
		    {
		    	String interpretation = jxList.getInterpretation().replaceAll("\\s\\s+ | \\n | \\r | \\t", " ").trim();;
				if(interpretation.length() > 255)
				{
					String temp = interpretation.substring(0, 255);
					jmList.getInterpretation().setValue(temp);

					ImportMessages importMsgs = ImportMessages.getInstance();
					importMsgs.add(ImportMessages.MessageType.WARNING, "List ("+jxList.getName()+") interpretation attribute was truncated to: \"" + temp +"\"");
				}
				else
				{
					jmList.getInterpretation().setValue(interpretation);
				}
		    }
			
			// Optional
			jmList.getOptional().setValue(jxList.isOptional());

		    // Count Field.  Since it's been 'fixed' earlier, we don't need to handle
		    // a lot of conditional checks.  We know min and max have already been
		    // properly populated (and truncated, if necessary)
	    	jmList.getMinSize().setValue(Long.parseLong(jxList.getCountField().getMinCount()));
	    	jmList.getMaxSize().setValue(Long.parseLong(jxList.getCountField().getMaxCount()));

			// Record | list | variant | sequence ?
			if(jxList.getRecord() != null)
			{
				jmList.setListElementType(Record.lookupOrCreate(jxList.getRecord()));
			}
			else if(jxList.getList() != null)
			{
				jmList.setListElementType(List.lookupOrCreate(jxList.getList()));
			}
			else if(jxList.getVariant() != null)
			{
				jmList.setListElementType(Variant.lookupOrCreate(jxList.getVariant()));	
			}
			else if(jxList.getSequence() != null)
			{
				jmList.setListElementType(Sequence.lookupOrCreate(jxList.getSequence()));
			}
			
			// Saves the List
			persistenceMechanism.save(jmList);
		}		

		jmList.firePropertyChange("List", jmList, jmList);

		return jmList;
	}
}
