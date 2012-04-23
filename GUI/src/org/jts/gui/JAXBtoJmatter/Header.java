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


// This class converts a JAXB Input Set to a JMatter input set
public class Header
{
	public static Object getStored( Object jaxbObject, Long generatedId )
	{
		org.jts.gui.util.QueryBuilder builder = new org.jts.gui.util.QueryBuilder();
		builder.setTableName( "Header" );
		builder.addNameArg();
		builder.setFixCount();
		
		return builder.getStored( jaxbObject, generatedId );
	}

	// This static method does the conversion from JAXB Binding to JMatter object 
	public static com.u2d.generated.Header lookupOrCreate( org.jts.jsidl.binding.Header jxHeader ) 
	{
		// Get and handle on a hibernate session
		com.u2d.persist.HBMSingleSession persistenceMechanism = (com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism(); 
		
		com.u2d.generated.Header jmHeader = (com.u2d.generated.Header)getStored( jxHeader, null );
		
		// if the returned jmHeader object is null, then the db does not contain this messageDef
		// so we will create it and add it to the db
		if( jmHeader == null )
		{
			jmHeader = new com.u2d.generated.Header();
			
			// Name
			jmHeader.getName().setValue(jxHeader.getName());
			
			// Interpretation (dropped)
			
			// Record | list | variant | sequence ?
			if(jxHeader.getRecord() != null)
			{
				// Top-level complex fields are not allowed to be optional.
				jxHeader.getRecord().setOptional(false);
				jmHeader.setComplexField(Record.lookupOrCreate(jxHeader.getRecord()));
			}
			else if(jxHeader.getList() != null)
			{
				// Top-level complex fields are not allowed to be optional.
				jxHeader.getList().setOptional(false);
				jmHeader.setComplexField(List.lookupOrCreate(jxHeader.getList()));
			}
			else if(jxHeader.getVariant() != null)
			{
				// Top-level complex fields are not allowed to be optional.
				jxHeader.getVariant().setOptional(false);
				jmHeader.setComplexField(Variant.lookupOrCreate(jxHeader.getVariant()));	
			}
			else if(jxHeader.getSequence() != null)
			{
				// Top-level complex fields are not allowed to be optional.
				jxHeader.getSequence().setOptional(false);
				jmHeader.setComplexField(Sequence.lookupOrCreate(jxHeader.getSequence()));
			}
			
			// Saves the Header
			persistenceMechanism.save(jmHeader);
		}		

		jmHeader.firePropertyChange("Header", jmHeader, jmHeader);

		return jmHeader;
	}	
}
