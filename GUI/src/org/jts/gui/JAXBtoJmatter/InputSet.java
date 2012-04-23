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
public class InputSet
{
	public static Object getStored( Object jaxbObject, Long generatedId, String name )
	{
		org.jts.gui.util.QueryBuilder builder = new org.jts.gui.util.QueryBuilder();
		builder.setTableName( "InputSet" );
		builder.addArg( "name", name );
		
		return builder.getStored( jaxbObject, generatedId );
	}
	
	// This static method does the conversion from JAXB Binding to JMatter object
	// First it checks that the defined Input Set does not already exist in the Hibernate Database 
	public static com.u2d.generated.InputSet lookupOrCreate( org.jts.jsidl.binding.InputSet jxInputSet, String dbName ) 
	{
		// First... look up id this Service Set is already defined in our Hibernate DB
		// Get and handle on a hibernate session
		com.u2d.persist.HBMSingleSession persistenceMechanism = (com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism(); 

		String queryName = dbName + "_inputSet";
		com.u2d.generated.InputSet jmInputSet = (com.u2d.generated.InputSet)getStored( jxInputSet, null, queryName );
		
		// if the jmInputSet object is unique we will create it and add it to the db
		if( jmInputSet == null )
		{
			jmInputSet = new com.u2d.generated.InputSet();
			
			// Set Name
			jmInputSet.getName().setValue(queryName);

			// Message Defs
			// We ignore DeclaredMessageDefs (which should not exist at this point)
			List jxList = jxInputSet.getMessageDefOrDeclaredMessageDef(); 
			
			if( jxList != null )
			{
				List<com.u2d.generated.MessageDef> jmList = jmInputSet.getMessageDefs().getItems();
				     
				for(int i = 0; i < jxList.size(); i++) 
				{
					// This checks for objects of type DeclaredMessageDef in the list
					// These should already be resolved (removed) by a pre-proccessing step
					if(jxList.get(i) instanceof org.jts.jsidl.binding.MessageDef) 
					{
						com.u2d.generated.MessageDef jmMessageDef = MessageDef.lookupOrCreate( (org.jts.jsidl.binding.MessageDef)jxList.get(i));
						jmList.add( jmMessageDef );
						
						// This step is important to link the inputSet to its messageDefs
						persistenceMechanism.updateAssociation(jmInputSet, jmMessageDef);
					}
				}
				
				// Saves the inputSet
				persistenceMechanism.save(jmInputSet);
			}
		}
			
		jmInputSet.firePropertyChange("InputSet", jmInputSet, jmInputSet);
		return jmInputSet;
	}	
}
