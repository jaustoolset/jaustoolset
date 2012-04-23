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

import org.hibernate.Query;
import org.hibernate.Session;
import com.u2d.app.Context;

// This class converts a JAXB ValueSet to a JMatter ValueSet
public class ValueSet
{
	// This static method does the conversion from JAXB Binding to JMatter object 
	public static com.u2d.generated.ValueSet lookupOrCreate( org.jts.jsidl.binding.ValueSet jxValueSet ) 
	{
		// Get and handle on a Hibernate session
		com.u2d.persist.HBMSingleSession persistenceMechanism = (com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism(); 

		// Since a jsidl ValueSet object doesn't have a name or a lot of distinguishing features, we will treat each as a new object
		com.u2d.generated.ValueSet jmValueSet = new com.u2d.generated.ValueSet();
		
		// set name
		jmValueSet.getName().setValue("MUST_RENAME");
		
		// Offset To Lower Limit
		jmValueSet.getOffsetToLowerLimit().setValue(jxValueSet.isOffsetToLowerLimit());
		
		List<Object> jxList = jxValueSet.getValueRangeOrValueEnum();
		if(jxList != null)
		{
			List<com.u2d.generated.ValueRange> jmValueRangeList = jmValueSet.getValueRanges().getItems();
			List<com.u2d.generated.ValueEnum> jmValueEnumList = jmValueSet.getValueEnums().getItems();
			
			for(int i = 0; i < jxList.size(); i++)
			{
				if(jxList.get(i) instanceof org.jts.jsidl.binding.ValueRange)
				{
					com.u2d.generated.ValueRange vr = ValueRange.lookupOrCreate((org.jts.jsidl.binding.ValueRange) jxList.get(i));
					jmValueRangeList.add(vr);
					persistenceMechanism.updateAssociation(jmValueSet, vr);					
				}
				else if(jxList.get(i) instanceof org.jts.jsidl.binding.ValueEnum)
				{
					com.u2d.generated.ValueEnum ve = ValueEnum.lookupOrCreate((org.jts.jsidl.binding.ValueEnum) jxList.get(i));
					jmValueEnumList.add(ve);
					persistenceMechanism.updateAssociation(jmValueSet, ve);					
				}
			}
		}
		
		// Saves the ValueSet
		persistenceMechanism.save(jmValueSet);

		jmValueSet.firePropertyChange("ValueSet", jmValueSet, jmValueSet);

		return jmValueSet;
	}	
}
