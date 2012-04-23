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


import org.jts.gui.importJSIDL.ImportMessages;

import com.u2d.app.Context;


// This class converts a JAXB Sequence to a JMatter Sequence
public class Sequence
{
	public static Object getStored( Object jaxbObject, Long generatedId )
	{
		org.jts.gui.util.QueryBuilder builder = new org.jts.gui.util.QueryBuilder();
		builder.setTableName( "Sequence" );
		builder.addNameArg();
		builder.addOptionalArg();
		builder.setFixCount();
		
		return builder.getStored( jaxbObject, generatedId );
	}

	// This static method does the conversion from JAXB Binding to JMatter object 
	public static com.u2d.generated.Sequence lookupOrCreate( org.jts.jsidl.binding.Sequence jxSequence ) 
	{
		// Get and handle on a hibernate session
		com.u2d.persist.HBMSingleSession persistenceMechanism = (com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism(); 

		com.u2d.generated.Sequence jmSequence = (com.u2d.generated.Sequence)getStored( jxSequence, null );

		// if the returned jxSequence object is null, then the db does not contain this Sequence
		// so we will create it and add it to the db
		if( jmSequence == null )
		{
			jmSequence = new com.u2d.generated.Sequence();
			
			// Name
			jmSequence.getName().setValue(jxSequence.getName());
			
			// Interpretation
		    if(jxSequence.getInterpretation() != null)
		    {
		    	String interpretation = jxSequence.getInterpretation().replaceAll("\\s\\s+ | \\n | \\r | \\t", " ").trim();;
				if(interpretation.length() > 255)
				{
					String temp = interpretation.substring(0, 255);
					jmSequence.getInterpretation().setValue(temp);

					ImportMessages importMsgs = ImportMessages.getInstance();
					importMsgs.add(ImportMessages.MessageType.WARNING, "Sequence ("+jxSequence.getName()+") interpretation attribute was truncated to: \"" + temp +"\"");
				}
				else
				{
					jmSequence.getInterpretation().setValue(interpretation);
				}
		    }
			
			// Optional
			jmSequence.getOptional().setValue(jxSequence.isOptional());

			// Presence Vector (dropped)
			
			// Complex Fields
		    List jxList = jxSequence.getRecordOrDeclaredRecordOrList();
		    if(jxList != null)
		    {
		        List jmList = jmSequence.getComplexFields().getItems();
		    	
				for(int i = 0; i < jxList.size(); i++)
				{
					if( jxList.get(i) instanceof org.jts.jsidl.binding.Record )
					{
						com.u2d.generated.Record jmRecord = Record.lookupOrCreate((org.jts.jsidl.binding.Record) jxList.get(i));  
						
						jmList.add(jmRecord);
						persistenceMechanism.updateAssociation(jmSequence, jmRecord);
					}
			        else if( jxList.get(i) instanceof org.jts.jsidl.binding.List )
					{
			        	com.u2d.generated.List jmListEntity = org.jts.gui.JAXBtoJmatter.List.lookupOrCreate((org.jts.jsidl.binding.List) jxList.get(i));
						jmList.add(jmListEntity);
						persistenceMechanism.updateAssociation(jmSequence, jmListEntity);
					}
			        else if( jxList.get(i) instanceof org.jts.jsidl.binding.Variant )
					{
			        	com.u2d.generated.Variant jmVariantEntity = Variant.lookupOrCreate((org.jts.jsidl.binding.Variant) jxList.get(i)); 
						jmList.add(jmVariantEntity);
						persistenceMechanism.updateAssociation(jmSequence, jmVariantEntity);
					}
			        else if( jxList.get(i) instanceof org.jts.jsidl.binding.Sequence )
					{
			        	com.u2d.generated.Sequence jmSequenceEntity = Sequence.lookupOrCreate((org.jts.jsidl.binding.Sequence) jxList.get(i)); 
						jmList.add(jmSequenceEntity);
						persistenceMechanism.updateAssociation(jmSequence, jmSequenceEntity);
					}
				}
		    }			
			
			// Saves the Sequence
			persistenceMechanism.save(jmSequence);
		}		

		jmSequence.firePropertyChange("Sequence", jmSequence, jmSequence);

		return jmSequence;
	}
}
