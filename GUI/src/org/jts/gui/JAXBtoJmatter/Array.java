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

import java.util.List;

// This class converts a JAXB Array to a JMatter Array
public class Array
{
	public static Object getStored( Object jaxbObject, Long generatedId )
	{
		org.jts.gui.util.QueryBuilder builder = new org.jts.gui.util.QueryBuilder();
		builder.setTableName( "Array" );
		builder.addNameArg();
		builder.addOptionalArg();
		builder.setFixCount();
		
		return builder.getStored( jaxbObject, generatedId );
	}

	// This static method does the conversion from JAXB Binding to JMatter object 
	public static com.u2d.generated.Array lookupOrCreate( org.jts.jsidl.binding.Array jxArray ) 
	{
		// Get and handle on a hibernate session
		com.u2d.persist.HBMSingleSession persistenceMechanism = (com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism();

		com.u2d.generated.Array jmArray = (com.u2d.generated.Array)getStored( jxArray, null );

		// if the jmArray object is unique, we will create it and add it to the db
		if( jmArray == null )
		{
			jmArray = new com.u2d.generated.Array();
			
			//name
			jmArray.getName().setValue(jxArray.getName());
	
		    // optional
		    jmArray.getOptional().setValue(jxArray.isOptional());

		    // Interpretation
		    if(jxArray.getInterpretation() != null)
		    {
		    	String interpretation = jxArray.getInterpretation().replaceAll("\\s\\s+ | \\n | \\r | \\t", " ").trim();;
				if(interpretation.length() > 255)
				{
					String temp = interpretation.substring(0, 255);
					jmArray.getInterpretation().setValue(temp);
					
					ImportMessages importMsgs = ImportMessages.getInstance();
					importMsgs.add(ImportMessages.MessageType.WARNING, "Array ("+jxArray.getName()+") interpretation attribute was truncated to: \"" + temp +"\"");
				}
				else
				{
					jmArray.getInterpretation().setValue(interpretation);
				}
		    }

		    // Array Field Type
			if(jxArray.getFixedField() != null)
			{
				jmArray.setArrayElementType(FixedField.lookupOrCreate(jxArray.getFixedField()));
			}
			else if(jxArray.getVariableField() != null)
			{
				jmArray.setArrayElementType(VariableField.lookupOrCreate(jxArray.getVariableField()));
			}
			else if(jxArray.getBitField() != null)
			{
				jmArray.setArrayElementType(BitField.lookupOrCreate(jxArray.getBitField()));
			}
			else if(jxArray.getFixedLengthString() != null)
			{
				jmArray.setArrayElementType(FixedLengthString.lookupOrCreate(jxArray.getFixedLengthString()));
			}
			else if(jxArray.getVariableLengthString() != null)
			{
				jmArray.setArrayElementType(VariableLengthString.lookupOrCreate(jxArray.getVariableLengthString()));
			}
			else if(jxArray.getVariableLengthField() != null)
			{
				jmArray.setArrayElementType(VariableLengthField.lookupOrCreate(jxArray.getVariableLengthField()));
			}
			else if(jxArray.getVariableFormatField() != null)
			{
				jmArray.setArrayElementType(VariableFormatField.lookupOrCreate(jxArray.getVariableFormatField()));
			}
		    
			List jxList = jxArray.getDimension();
			if(jxList != null)
			{
				List jmList = jmArray.getDimensions().getItems();
				for(int i = 0; i < jxList.size(); i++)
				{
					com.u2d.generated.Dimension dimension = Dimension.lookupOrCreate((org.jts.jsidl.binding.Dimension) jxList.get(i)); 
					jmList.add(dimension);
					persistenceMechanism.updateAssociation(jmArray, dimension);
				}
			}
			
			// Saves the Array
			persistenceMechanism.save(jmArray);
		}		

		jmArray.firePropertyChange("Array", jmArray, jmArray);

		return jmArray;
	}

}
