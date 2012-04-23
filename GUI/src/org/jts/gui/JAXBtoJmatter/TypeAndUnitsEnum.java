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

// This class converts a JAXB TypeAndUnitsEnum to a JMatter TypeAndUnitsEnum
public class TypeAndUnitsEnum
{
	// This static method does the conversion from JAXB Binding to JMatter object 
	public static com.u2d.generated.TypeAndUnitsEnum lookupOrCreate( org.jts.jsidl.binding.TypeAndUnitsEnum jxTypeAndUnitsEnum ) 
	{
		// Get and handle on a hibernate session
		com.u2d.persist.HBMSingleSession persistenceMechanism = (com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism(); 
		
		// NOTE: There is nothing significant in a TypeAndUnitsEnum's definition that would allow it to be looked up in the db
		
		com.u2d.generated.TypeAndUnitsEnum jmTypeAndUnitsEnum = new com.u2d.generated.TypeAndUnitsEnum();
		
		jmTypeAndUnitsEnum.getName().setValue("MUST_RENAME");
		
		// Index
		jmTypeAndUnitsEnum.getIndex().setValue(jxTypeAndUnitsEnum.getIndex());
		
		// PrimitiveField Type
	    jmTypeAndUnitsEnum.getType().getCode().setValue(jxTypeAndUnitsEnum.getFieldType());
	    jmTypeAndUnitsEnum.getType().getCaption().setValue(jxTypeAndUnitsEnum.getFieldType());

	    // SI Units
	    jmTypeAndUnitsEnum.getUnits().getCode().setValue(jxTypeAndUnitsEnum.getFieldUnits());
	    jmTypeAndUnitsEnum.getUnits().getCaption().setValue(jxTypeAndUnitsEnum.getFieldUnits()); 
	    
	    // scale range
	    org.jts.jsidl.binding.ScaleRange jxScaleRange = jxTypeAndUnitsEnum.getScaleRange();
	    if(jxScaleRange != null)
	    {
	    	com.u2d.generated.ScaleRange jmScaleRange = jmTypeAndUnitsEnum.getScaleRange();

	    	// ******    realLowerLimit   ******
	    	jmScaleRange.getRealLowerLimit().setValue( jxScaleRange.getRealLowerLimit() );

	        // ******    realUpperLimit   ******
	        jmScaleRange.getRealUpperLimit().setValue( jxScaleRange.getRealUpperLimit() );

	        // ******    interpretation   ******
		    if(jxScaleRange.getInterpretation() != null)
		    {
		    	String interpretation = jxScaleRange.getInterpretation().replaceAll("\\s\\s+ | \\n | \\r | \\t", " ").trim();;
				if(interpretation.length() > 255)
				{
					String temp = interpretation.substring(0, 255);
					jmScaleRange.getInterpretation().setValue(temp);

					ImportMessages importMsgs = ImportMessages.getInstance();
					importMsgs.add(ImportMessages.MessageType.WARNING, "ScaleRange interpretation attribute was truncated to: \"" + temp +"\"");
				}
				else
				{
					jmScaleRange.getInterpretation().setValue(interpretation);
				}
		    }
	    }
	    
	    // value set 
	    org.jts.jsidl.binding.ValueSet jxValueSet = jxTypeAndUnitsEnum.getValueSet();
	    if(jxValueSet != null)
	    {
	    	jmTypeAndUnitsEnum.setValueSet(ValueSet.lookupOrCreate(jxValueSet));
	    }
	    
		// Saves the TypeAndUnitsEnum
		persistenceMechanism.save(jmTypeAndUnitsEnum);

		jmTypeAndUnitsEnum.firePropertyChange("TypeAndUnitsEnum", jmTypeAndUnitsEnum, jmTypeAndUnitsEnum);

		return jmTypeAndUnitsEnum;
	}	
}
