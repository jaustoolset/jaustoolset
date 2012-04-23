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

import java.util.List;

import org.jts.gui.importJSIDL.ImportMessages;

import com.u2d.app.Context;


// This class converts a JAXB Input Set to a JMatter input set
public class Record
{
	public static Object getStored( Object jaxbObject, Long generatedId )
	{
		org.jts.gui.util.QueryBuilder builder = new org.jts.gui.util.QueryBuilder();
		builder.setTableName( "Record" );
		builder.addNameArg();
		builder.addOptionalArg();
		builder.setFixCount();
		
		return builder.getStored( jaxbObject, generatedId );
	}

	// This static method does the conversion from JAXB Binding to JMatter object 
	public static com.u2d.generated.Record lookupOrCreate( org.jts.jsidl.binding.Record jxRecord ) 
	{
		// Get and handle on a hibernate session
		com.u2d.persist.HBMSingleSession persistenceMechanism = (com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism(); 

		com.u2d.generated.Record jmRecord = (com.u2d.generated.Record)getStored( jxRecord, null );;
		
		// if the jmRecord object is null, we will create it and add it to the db
		if( jmRecord == null )
		{
			jmRecord = new com.u2d.generated.Record();
			
			// Name
			jmRecord.getName().setValue(jxRecord.getName());
			
			// Optional
			jmRecord.getOptional().setValue(jxRecord.isOptional());
			
			// Interpretation
		    if(jxRecord.getInterpretation() != null)
		    {
		    	String interpretation = jxRecord.getInterpretation().replaceAll("\\s\\s+ | \\n | \\r | \\t", " ").trim();;
				if(interpretation.length() > 255)
				{
					String temp = interpretation.substring(0, 255);
					jmRecord.getInterpretation().setValue( temp );

					ImportMessages importMsgs = ImportMessages.getInstance();
					importMsgs.add(ImportMessages.MessageType.WARNING, "Record ("+jxRecord.getName()+") interpretation attribute was truncated to: \"" + temp +"\"");
				}
				else
				{
					jmRecord.getInterpretation().setValue(interpretation);
				}
		    }

		    // Presence Vector (dropped)
			
			// Simple Fields
			// array | fixed_field | variable_field | bit_field | 
			// fixed_length_string | variable_length_string |
			// variable_length_field | variable_format_field | 
			List<Object> jxList = jxRecord.getArrayOrFixedFieldOrVariableField();
			if(jxList != null)
			{
				List<com.u2d.generated.SimpleField> jmList = jmRecord.getSimpleFields().getItems();
				     
				for(int i = 0; i < jxList.size(); i++) 
				{
					if( jxList.get(i) instanceof org.jts.jsidl.binding.FixedField )
			        {
						com.u2d.generated.FixedField field = FixedField.lookupOrCreate((org.jts.jsidl.binding.FixedField)jxList.get(i));
			        	jmList.add(field);
			        	persistenceMechanism.updateAssociation(jmRecord, field);
			        }
					else if( jxList.get(i) instanceof org.jts.jsidl.binding.Array )
					{
						com.u2d.generated.Array field = Array.lookupOrCreate( (org.jts.jsidl.binding.Array)jxList.get(i));
			        	jmList.add(field);
			        	persistenceMechanism.updateAssociation(jmRecord, field);
					}
			        else if( jxList.get(i) instanceof org.jts.jsidl.binding.VariableField )
			        {
			        	com.u2d.generated.VariableField field = VariableField.lookupOrCreate( (org.jts.jsidl.binding.VariableField)jxList.get(i));
			        	jmList.add(field);
			        	persistenceMechanism.updateAssociation(jmRecord, field);
			        }
			        else if( jxList.get(i) instanceof org.jts.jsidl.binding.BitField )
			        {
			        	com.u2d.generated.BitField field = BitField.lookupOrCreate( (org.jts.jsidl.binding.BitField)jxList.get(i));
			        	jmList.add(field);
			        	persistenceMechanism.updateAssociation(jmRecord, field);
			        }
			        else if( jxList.get(i) instanceof org.jts.jsidl.binding.FixedLengthString )
			        {
			        	com.u2d.generated.FixedLengthString field = FixedLengthString.lookupOrCreate( (org.jts.jsidl.binding.FixedLengthString)jxList.get(i));
			        	jmList.add(field);
			        	persistenceMechanism.updateAssociation(jmRecord, field);
			        }
			        else if( jxList.get(i) instanceof org.jts.jsidl.binding.VariableLengthString )
			        {
			        	com.u2d.generated.VariableLengthString field = VariableLengthString.lookupOrCreate( (org.jts.jsidl.binding.VariableLengthString)jxList.get(i));
			        	jmList.add(field);
			        	persistenceMechanism.updateAssociation(jmRecord, field);
			        }
			        else if( jxList.get(i) instanceof org.jts.jsidl.binding.VariableLengthField )
			        {
			        	com.u2d.generated.VariableLengthField field = VariableLengthField.lookupOrCreate( (org.jts.jsidl.binding.VariableLengthField)jxList.get(i));
			        	jmList.add(field);
			        	persistenceMechanism.updateAssociation(jmRecord, field);
			        }
					else if( jxList.get(i) instanceof org.jts.jsidl.binding.VariableFormatField )
					{
						com.u2d.generated.VariableFormatField field = VariableFormatField.lookupOrCreate( (org.jts.jsidl.binding.VariableFormatField) jxList.get(i));
			        	jmList.add(field);
			        	persistenceMechanism.updateAssociation(jmRecord, field);
					}
				}
			}
			
			// Saves the Record
			persistenceMechanism.save(jmRecord);
		}		

		jmRecord.firePropertyChange("Record", jmRecord, jmRecord);

		return jmRecord;
	}	
}
