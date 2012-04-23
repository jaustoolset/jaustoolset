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

import com.u2d.app.Context;

// This class converts a JAXB Input Set to a JMatter input set
public class OutputSet
{
	public static Object getStored( Object jaxbObject, Long generatedId, String queryName )
	{
		org.jts.gui.util.QueryBuilder builder = new org.jts.gui.util.QueryBuilder();
		builder.setTableName( "OutputSet" );
		builder.addArg( "name", queryName );
		
		return builder.getStored( jaxbObject, generatedId );
	}

	// This static method does the conversion from JAXB Binding to JMatter object
	// First it checks that the defined Input Set does not already exist in the Hibernate Database 
	public static com.u2d.generated.OutputSet lookupOrCreate( org.jts.jsidl.binding.OutputSet jxOutputSet, String dbName ) 
	{
		// First... look up if this Output Set is already defined in our Hibernate DB
		// Get and handle on a hibernate session
		com.u2d.persist.HBMSingleSession persistenceMechanism = (com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism(); 

		String queryName = dbName + "_OutputSet";
		
		com.u2d.generated.OutputSet jmOutputSet = (com.u2d.generated.OutputSet)getStored( jxOutputSet, null, queryName );
		
		// if the jmOutputSet object is unique we will create it and add it to the db
		if( jmOutputSet == null )
		{
			jmOutputSet = new com.u2d.generated.OutputSet();
			
			// Set Name
			jmOutputSet.getName().setValue(queryName);

			// Message Defs
			// Note: For now we will ignore DeclaredMessageDefs
			List jxList = jxOutputSet.getMessageDefOrDeclaredMessageDef(); 
			
			if( jxList != null )
			{
				List<com.u2d.generated.MessageDef> jmList = jmOutputSet.getMessageDefs().getItems();
				     
				for(int i = 0; i < jxList.size(); i++) 
				{
					// This checks for objects of type DeclaredMessageDef in the list
					// These will eventually be resolved (removed) by a pre-proccessing step
					if(jxList.get(i) instanceof org.jts.jsidl.binding.MessageDef) 
					{
						com.u2d.generated.MessageDef jmMessageDef = MessageDef.lookupOrCreate( (org.jts.jsidl.binding.MessageDef)jxList.get(i));
						jmList.add( jmMessageDef );
						
						// This step is important to link the outputSet to its messageDefs
						persistenceMechanism.updateAssociation(jmOutputSet, jmMessageDef);
					}
				}
				
				// Saves the outputSet
				persistenceMechanism.save(jmOutputSet);
			}
		}
			
		jmOutputSet.firePropertyChange("OutputSet", jmOutputSet, jmOutputSet);
		return jmOutputSet;
	}	
}
