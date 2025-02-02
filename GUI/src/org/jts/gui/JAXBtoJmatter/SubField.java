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

import org.hibernate.Query;
import org.hibernate.Session;
import org.jts.gui.importJSIDL.ImportMessages;

import com.u2d.app.Context;

// This class converts a JAXB SubField to a JMatter SubField
public class SubField
{
	// This static method does the conversion from JAXB Binding to JMatter object 
	public static com.u2d.generated.SubField lookupOrCreate( org.jts.jsidl.binding.SubField jxSubField ) 
	{
		// Get and handle on a hibernate session
		com.u2d.persist.HBMSingleSession persistenceMechanism = (com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism(); 
		
		// Subfields are considered unique.  Create a new one and add it to the database.
		com.u2d.generated.SubField jmSubField = new com.u2d.generated.SubField();
		
		// name
		jmSubField.getName().setValue(jxSubField.getName());

	    // Interpretation
	    if(jxSubField.getInterpretation() != null)
	    {
	    	String interpretation = jxSubField.getInterpretation().replaceAll("\\s\\s+ | \\n | \\r | \\t", " ").trim();;
//			if(interpretation.length() > 255)
//			{
//				String temp = interpretation.substring(0, 255);
//				jmSubField.getInterpretation().setValue(temp);
//
//				ImportMessages importMsgs = ImportMessages.getInstance();
//				importMsgs.add(ImportMessages.MessageType.WARNING, "SubField ("+jxSubField.getName()+") interpretation attribute was truncated to: \"" + temp +"\"");
//			}
//			else
//			{
				jmSubField.getInterpretation().setValue(interpretation);
//			}
	    }

	    // BitRange
		com.u2d.generated.BitRange jmBitRange = jmSubField.getBitRange();
		org.jts.jsidl.binding.BitRange jxBitRange = jxSubField.getBitRange();
	    
		// From Index
		jmBitRange.getFromIndex().setValue(jxBitRange.getFromIndex());
		
		// To Index
		jmBitRange.getToIndex().setValue(jxBitRange.getToIndex());
		
		// Interpretation
		if(jxBitRange.getInterpretation() != null)
		{
			String interpretation = jxBitRange.getInterpretation().replaceAll("\\s\\s+ | \\n | \\r | \\t", " ").trim();;
//			if(interpretation.length() > 255)
//			{
//				String temp = interpretation.substring(0, 255);
//				jmBitRange.getInterpretation().setValue(temp);
//
//				ImportMessages importMsgs = ImportMessages.getInstance();
//				importMsgs.add(ImportMessages.MessageType.WARNING, "BitRange interpretation attribute was truncated to: \"" + temp +"\"");
//			}
//			else
//			{
				jmBitRange.getInterpretation().setValue(interpretation);
//			}
		}
		
		// Value Set
		jmSubField.setValueSet(ValueSet.lookupOrCreate(jxSubField.getValueSet()));			
		
		// Saves the SubField
		persistenceMechanism.save(jmSubField);
		jmSubField.firePropertyChange("SubField", jmSubField, jmSubField);

		return jmSubField;
	}	
}
