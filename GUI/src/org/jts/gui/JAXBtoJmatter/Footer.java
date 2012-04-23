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
public class Footer
{
	public static Object getStored( Object jaxbObject, Long generatedId )
	{
		org.jts.gui.util.QueryBuilder builder = new org.jts.gui.util.QueryBuilder();
		builder.setTableName( "Footer" );
		builder.addNameArg();
		builder.setFixCount();
		
		return builder.getStored( jaxbObject, generatedId );
	}

	// This static method does the conversion from JAXB Binding to JMatter object 
	public static com.u2d.generated.Footer lookupOrCreate( org.jts.jsidl.binding.Footer jxFooter ) 
	{
		// Get and handle on a hibernate session
		com.u2d.persist.HBMSingleSession persistenceMechanism = (com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism(); 
		
		com.u2d.generated.Footer jmFooter = (com.u2d.generated.Footer)getStored( jxFooter, null );
		
		// if the jmFooter object is unique, we will create it and add it to the db
		if( jmFooter == null )
		{
			jmFooter = new com.u2d.generated.Footer();
			
			// Name
			jmFooter.getName().setValue(jxFooter.getName());
			
			// Interpretation (dropped)
	
			// Record | list | variant | sequence ?
			if(jxFooter.getRecord() != null)
			{
				// Top-level complex fields are not allowed to be optional.
				jxFooter.getRecord().setOptional(false);
				jmFooter.setComplexField(Record.lookupOrCreate(jxFooter.getRecord()));
			}
			else if(jxFooter.getList() != null)
			{
				// Top-level complex fields are not allowed to be optional.
				jxFooter.getList().setOptional(false);
				jmFooter.setComplexField(List.lookupOrCreate(jxFooter.getList()));
			}
			else if(jxFooter.getVariant() != null)
			{
				// Top-level complex fields are not allowed to be optional.
				jxFooter.getVariant().setOptional(false);
				jmFooter.setComplexField(Variant.lookupOrCreate(jxFooter.getVariant()));	
			}
			else if(jxFooter.getSequence() != null)
			{
				// Top-level complex fields are not allowed to be optional.
				jxFooter.getSequence().setOptional(false);
				jmFooter.setComplexField(Sequence.lookupOrCreate(jxFooter.getSequence()));
			}
			
			// Saves the Footer
			persistenceMechanism.save(jmFooter);
		}		

		jmFooter.firePropertyChange("Footer", jmFooter, jmFooter);

		return jmFooter;
	}	
}
