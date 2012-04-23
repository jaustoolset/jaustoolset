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

// This class converts a JAXB VariableFormatField to a JMatter VariableFormatField
public class VariableFormatField
{
	public static Object getStored( Object jaxbObject, Long generatedId )
	{
		org.jts.gui.util.QueryBuilder builder = new org.jts.gui.util.QueryBuilder();
		builder.setTableName( "VariableFormatField" );
		builder.addNameArg();
		builder.addOptionalArg();
		builder.setFixCount();
		
		return builder.getStored( jaxbObject, generatedId );
	}

	// This static method does the conversion from JAXB Binding to JMatter object 
	public static com.u2d.generated.VariableFormatField lookupOrCreate(org.jts.jsidl.binding.VariableFormatField jxVariableFormatField ) 
	{
		// Get and handle on a hibernate session
		com.u2d.persist.HBMSingleSession persistenceMechanism = (com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism(); 

		com.u2d.generated.VariableFormatField jmVariableFormatField = 
			(com.u2d.generated.VariableFormatField)getStored( jxVariableFormatField, null );

		// if the jmVariableFormatField object is unique, we will create it and add it to the db
		if( jmVariableFormatField == null )
		{
			jmVariableFormatField = new com.u2d.generated.VariableFormatField();
			
			// name
			jmVariableFormatField.getName().setValue(jxVariableFormatField.getName());

		    // Optional
		    jmVariableFormatField.getOptional().setValue(jxVariableFormatField.isOptional());
		    
		    // Interpretation
		    if(jxVariableFormatField.getInterpretation() != null)
		    {
		    	String interpretation = jxVariableFormatField.getInterpretation().replaceAll("\\s\\s+ | \\n | \\r | \\t", " ").trim();;
				if(interpretation.length() > 255)
				{
					String temp = interpretation.substring(0, 255);
					jmVariableFormatField.getInterpretation().setValue(temp);

					ImportMessages importMsgs = ImportMessages.getInstance();
					importMsgs.add(ImportMessages.MessageType.WARNING, "VariableFormatField ("+jxVariableFormatField.getName()+") interpretation attribute was truncated to: \"" + temp +"\"");
				}
				else
				{
					jmVariableFormatField.getInterpretation().setValue(interpretation);
				}
		    }

		    java.util.List jxList = jxVariableFormatField.getFormatField().getFormatEnum();
		    if(jxList != null)
		    {
		    	com.u2d.generated.FormatField jmFormatField = new com.u2d.generated.FormatField();
		    	
		    	java.util.List jmList = jmFormatField.getFormatEnums().getItems();
		    	for(int i = 0; i < jxList.size(); i++)
		    	{
		    		com.u2d.generated.FormatEnum formatEnum = FormatEnum.lookupOrCreate((org.jts.jsidl.binding.FormatEnum) jxList.get(i)); 
		    		jmList.add(formatEnum);
		    		persistenceMechanism.updateAssociation(jmFormatField, formatEnum);
		    	}
		    	
		    	jmVariableFormatField.setFormatField(jmFormatField);
		    }
		    
		    // Count Field.  Since it's been 'fixed' earlier, we don't need to handle
		    // a lot of conditional checks.  We know min and max have already been
		    // properly populated (and truncated, if necessary)
	    	jmVariableFormatField.getMinSize().setValue(Long.parseLong(jxVariableFormatField.getCountField().getMinCount()));
	    	jmVariableFormatField.getMaxSize().setValue(Long.parseLong(jxVariableFormatField.getCountField().getMaxCount()));
		    
			// Saves the VariableFormatField
			persistenceMechanism.save(jmVariableFormatField);
		}		

		jmVariableFormatField.firePropertyChange("VariableFormatField", jmVariableFormatField, jmVariableFormatField);

		return jmVariableFormatField;
	}	
}
