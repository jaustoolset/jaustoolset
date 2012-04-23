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


// This class converts a JAXB EventDef to a JMatter EventDef
public class EventDef
{
	public static Object getStored( Object jaxbObject, Long generatedId )
	{
		org.jts.gui.util.QueryBuilder builder = new org.jts.gui.util.QueryBuilder();
		builder.setTableName( "EventDef" );
		builder.addNameArg();
		builder.addDescriptionArg();
		builder.setFixCount();
		
		return builder.getStored( jaxbObject, generatedId );
	}

	// This static method does the conversion from JAXB Binding to JMatter object
	public static com.u2d.generated.EventDef lookupOrCreate( org.jts.jsidl.binding.EventDef jxEventDef ) 
	{
		// First... look up id this Service Set is already defined in our Hibernate DB
		// Get and handle on a hibernate session
		com.u2d.persist.HBMSingleSession persistenceMechanism = (com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism(); 
    
		com.u2d.generated.EventDef jmEventDef = (com.u2d.generated.EventDef)getStored( jxEventDef, null );
		
		// if the returned jmEventDef object is null, then the db does not contain this eventDef so we will create it and add it to the db
		if( jmEventDef == null )
		{
			jmEventDef = new com.u2d.generated.EventDef();
			
			// Set Name
			jmEventDef.getName().setValue(jxEventDef.getName());

			// Description
	    	String description = jxEventDef.getDescription().getContent().replaceAll("\\s\\s+ | \\n | \\r | \\t", " ").trim();;
			jmEventDef.getDescription().setValue(description);
			
			// Header
			if(jxEventDef.getHeader() != null)
			{
				jmEventDef.setHeader(Header.lookupOrCreate(jxEventDef.getHeader()));
			}

			// Body
			if(jxEventDef.getBody() != null)
			{
				jmEventDef.setBody(Body.lookupOrCreate(jxEventDef.getBody()));
			}
			
			// Footer
			if(jxEventDef.getFooter() != null)
			{
				jmEventDef.setFooter(Footer.lookupOrCreate(jxEventDef.getFooter()));
			}
			
			// Saves the eventDef
			persistenceMechanism.save(jmEventDef);
		}

		jmEventDef.firePropertyChange("EventDef", jmEventDef, jmEventDef);
		
		return jmEventDef;
	}
	
}


