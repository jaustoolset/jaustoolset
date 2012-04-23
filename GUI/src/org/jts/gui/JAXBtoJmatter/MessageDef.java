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

import org.jts.gui.util.TypeConverter;

import com.u2d.app.Context;

// This class converts a JAXB Input Set to a JMatter input set
public class MessageDef
{
	public static Object getStored( Object jaxbObject, Long generatedId, String messageId )
	{
		org.jts.gui.util.QueryBuilder builder = new org.jts.gui.util.QueryBuilder();
		builder.setTableName( "MessageDef" );
		builder.addArg( "messageId", messageId );
		
		return builder.getStored( jaxbObject, generatedId );
	}

	// This static method does the conversion from JAXB Binding to JMatter object
	// First it checks that the defined Message Def does not already exist in the Hibernate Database 
	public static com.u2d.generated.MessageDef lookupOrCreate( org.jts.jsidl.binding.MessageDef jxMessageDef) 
	{
		if(jxMessageDef == null) return null;
		
		// First... look up id this Service Set is already defined in our Hibernate DB
		// Get and handle on a hibernate session
		com.u2d.persist.HBMSingleSession persistenceMechanism = (com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism(); 
		
		String messageId = TypeConverter.byteArrayToHexString(jxMessageDef.getMessageId());
		
		// Get result of our query
		// NOTE: Special bypass for messageDefs, we only care about matching the ID, not any other members
		com.u2d.generated.MessageDef jmMessageDef = org.jts.validator.MessageDef.getDuplicateMessageID( messageId );
    
		// if the returned jmMessageDef object is null, then the db does not contain this messageDef
		// so we will create it and add it to the db
		if(jmMessageDef == null)
		{
			jmMessageDef = new com.u2d.generated.MessageDef();
			
			// Set Name
			jmMessageDef.getName().setValue(jxMessageDef.getName());

			// Message Id
			jmMessageDef.getMessageId().setValue(messageId);
			
			// isCommand
			jmMessageDef.getIsCommand().setValue(jxMessageDef.isIsCommand());
			
			// Description
			String description = jxMessageDef.getDescription().getContent().replaceAll("\\s\\s+ | \\n | \\r | \\t", " ").trim();;			
			jmMessageDef.getDescription().setValue(description);
			
			// Header
			if(jxMessageDef.getHeader() != null)
			{
				jmMessageDef.setHeader(Header.lookupOrCreate(jxMessageDef.getHeader()));
			}

			// Body
			if(jxMessageDef.getBody() != null)
			{
				jmMessageDef.setBody(Body.lookupOrCreate(jxMessageDef.getBody()));
			}
			
			// Footer
			if(jxMessageDef.getFooter() != null)
			{
				jmMessageDef.setFooter(Footer.lookupOrCreate(jxMessageDef.getFooter()));
			}
			
			// Saves the messageDef
			persistenceMechanism.save(jmMessageDef);
		}

		jmMessageDef.firePropertyChange("MessageDef", jmMessageDef, jmMessageDef);
		
		return jmMessageDef;
	}
}


