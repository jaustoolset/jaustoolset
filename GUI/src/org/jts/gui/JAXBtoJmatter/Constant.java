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


/*
*/

// This class converts a JAXB ConstDef to a JMatter ConstDef
public class Constant
{
	public static Object getStored(Object jaxbObject, Long generatedId )
	{
		org.jts.gui.util.QueryBuilder builder = new org.jts.gui.util.QueryBuilder();
		builder.setTableName( "Constant" );
		builder.addNameArg();
		builder.addArg( "value", ((org.jts.jsidl.binding.ConstDef)jaxbObject).getConstValue() );
		
		return builder.getStored( jaxbObject, generatedId );
	}

	// This static method does the conversion from JAXB Binding to JMatter object 
	public static com.u2d.generated.Constant lookupOrCreate( org.jts.jsidl.binding.ConstDef jxConstDef) 
	{
		// Get and handle on a hibernate session
		com.u2d.persist.HBMSingleSession persistenceMechanism = (com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism(); 
		
		com.u2d.generated.Constant jmConstDef = (com.u2d.generated.Constant)getStored( jxConstDef, null );
		
		// if the jmConstDef object is null, we will create it and add it to the db
		if( jmConstDef == null )
		{
			jmConstDef = new com.u2d.generated.Constant();
			
			// Name
			jmConstDef.getName().setValue(jxConstDef.getName());

			// Value
			jmConstDef.getValue().setValue(jxConstDef.getConstValue());
			
			// Interpretation (Optional)
			if(jxConstDef.getInterpretation() != null)
			{
		    	String interpretation = jxConstDef.getInterpretation().replaceAll("\\s\\s+ | \\n | \\r | \\t", " ").trim();;
				if(interpretation.length() > 255)
				{
					String temp = interpretation.substring(0, 255);
					jmConstDef.getInterpretation().setValue(temp);

					ImportMessages importMsgs = ImportMessages.getInstance();
					importMsgs.add(ImportMessages.MessageType.WARNING, "Constant ("+jmConstDef.getName()+") interpretation attribute was truncated to: \"" + temp +"\"");
				}
				else
				{
					jmConstDef.getInterpretation().setValue(interpretation);
				}
			}
			
			// ConstType
			jmConstDef.getType().getCode().setValue(jxConstDef.getConstType());
			jmConstDef.getType().getCaption().setValue(jxConstDef.getConstType());
			
			// Units
		    jmConstDef.getUnits().getCode().setValue(jxConstDef.getFieldUnits());
		    jmConstDef.getUnits().getCaption().setValue(jxConstDef.getFieldUnits()); 
			
			// Saves the ConstDef
			persistenceMechanism.save(jmConstDef);
		}		

		jmConstDef.firePropertyChange("ConstDef", jmConstDef, jmConstDef);

		return jmConstDef;
	}	
}
