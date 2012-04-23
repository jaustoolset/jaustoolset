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

// This class converts a JAXB VariableLengthField to a JMatter VariableLengthField
public class VariableLengthField
{
	public static Object getStored( Object jaxbObject, Long generatedId )
	{
		org.jts.gui.util.QueryBuilder builder = new org.jts.gui.util.QueryBuilder();
		builder.setTableName( "VariableLengthField" );
		builder.addNameArg();
		builder.addOptionalArg();
		builder.setFixCount();
		
		return builder.getStored( jaxbObject, generatedId );
	}

	// This static method does the conversion from JAXB Binding to JMatter object 
	public static com.u2d.generated.VariableLengthField lookupOrCreate( org.jts.jsidl.binding.VariableLengthField jxVariableLengthField ) 
	{
		// Get and handle on a hibernate session
		com.u2d.persist.HBMSingleSession persistenceMechanism = (com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism(); 
		
		com.u2d.generated.VariableLengthField jmVariableLengthField = 
			(com.u2d.generated.VariableLengthField)getStored( jxVariableLengthField, null );
    
		// if the jmVariableLengthField object is unique, we will create it and add it to the db
		if( jmVariableLengthField == null )
		{
			jmVariableLengthField = new com.u2d.generated.VariableLengthField();
			
			// name
			jmVariableLengthField.getName().setValue(jxVariableLengthField.getName());

		    // Optional
			jmVariableLengthField.getOptional().setValue(jxVariableLengthField.isOptional());
		    
		    // Interpretation
			if(jxVariableLengthField.getInterpretation() != null)
			{
				String interpretation = jxVariableLengthField.getInterpretation().replaceAll("\\s\\s+ | \\n | \\r | \\t", " ").trim();;
				if(interpretation.length() > 255)
				{
					String temp = interpretation.substring(0, 255);
					jmVariableLengthField.getInterpretation().setValue(temp);

					ImportMessages importMsgs = ImportMessages.getInstance();
					importMsgs.add(ImportMessages.MessageType.WARNING, "VariableLengthField ("+jxVariableLengthField.getName()+") interpretation attribute was truncated to: \"" + temp +"\"");
				}
				else
				{
					jmVariableLengthField.getInterpretation().setValue(interpretation);
				}
			}

		    // Count Field.  Since it's been 'fixed' earlier, we don't need to handle
		    // a lot of conditional checks.  We know min and max have already been
		    // properly populated (and truncated, if necessary)
	    	jmVariableLengthField.getMinSize().setValue(Long.parseLong(jxVariableLengthField.getCountField().getMinCount()));
	    	jmVariableLengthField.getMaxSize().setValue(Long.parseLong(jxVariableLengthField.getCountField().getMaxCount()));
		    
		    // Attribute_field_format
		    jmVariableLengthField.getFieldFormat().getCode().setValue(jxVariableLengthField.getFieldFormat());
		    jmVariableLengthField.getFieldFormat().getCaption().setValue(jxVariableLengthField.getFieldFormat());
		    
			// Saves the VariableLengthField
			persistenceMechanism.save(jmVariableLengthField);
		}

		jmVariableLengthField.firePropertyChange("VariableLengthField", jmVariableLengthField, jmVariableLengthField);

		return jmVariableLengthField;
	}	
}
