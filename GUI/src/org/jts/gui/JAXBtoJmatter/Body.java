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

import com.u2d.app.Context;

// This class converts a JAXB Body to a JMatter Body
public class Body
{
	public static Object getStored( Object jaxbObject, Long generatedId )
	{
		org.jts.gui.util.QueryBuilder builder = new org.jts.gui.util.QueryBuilder( );
		builder.setTableName( "Body" );
		builder.addNameArg();
		builder.setFixCount();
		
		return builder.getStored( jaxbObject, generatedId );
	}

	// This static method does the conversion from JAXB Binding to JMatter object 
	public static com.u2d.generated.Body lookupOrCreate( org.jts.jsidl.binding.Body jxBody ) 
	{
		// Get and handle on a hibernate session
		com.u2d.persist.HBMSingleSession persistenceMechanism = (com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism(); 
		
		com.u2d.generated.Body jmBody = (com.u2d.generated.Body)getStored( jxBody, null );
		
		// if the jmBody object is null, we will create it and add it to the db
		if( jmBody == null )
		{
			jmBody = new com.u2d.generated.Body();
			
			// Name
			jmBody.getName().setValue(jxBody.getName());
			
			// Interpretation (dropped)
			// Record | list | variant | sequence ?
			if(jxBody.getRecord() != null)
			{
				// Top-level complex fields are not allowed to be optional.
				jxBody.getRecord().setOptional(false);
				jmBody.setComplexField(Record.lookupOrCreate(jxBody.getRecord()));
			}
			else if(jxBody.getList() != null)
			{
				// Top-level complex fields are not allowed to be optional.
				jxBody.getList().setOptional(false);
				jmBody.setComplexField(List.lookupOrCreate(jxBody.getList()));
			}
			else if(jxBody.getVariant() != null)
			{
				// Top-level complex fields are not allowed to be optional.
				jxBody.getVariant().setOptional(false);
				jmBody.setComplexField(Variant.lookupOrCreate(jxBody.getVariant()));	
			}
			else if(jxBody.getSequence() != null)
			{
				// Top-level complex fields are not allowed to be optional.
				jxBody.getSequence().setOptional(false);
				jmBody.setComplexField(Sequence.lookupOrCreate(jxBody.getSequence()));
			}
			
			// Saves the Body
			persistenceMechanism.save(jmBody);
		}		

		jmBody.firePropertyChange("Body", jmBody, jmBody);

		return jmBody;
	}	
}
