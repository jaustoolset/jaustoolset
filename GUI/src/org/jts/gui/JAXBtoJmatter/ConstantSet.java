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

import org.hibernate.Query;
import org.hibernate.Session;

import com.u2d.app.Context;


/*
element declared_const_set 
{
    attribute name { identifier },
    ( attribute id { xsd:anyURI },
    attribute version { version_num } )?,
    declared_const_set_ref*,
    const_def*
 }
*/

// This class converts a JAXB ConstSet to a JMatter ConstSet
public class ConstantSet
{
	public static Object getStored( Object jaxbObject, Long generatedId )
	{
		org.jts.gui.util.QueryBuilder builder = new org.jts.gui.util.QueryBuilder();
		builder.setTableName( "ConstantSet" );
		builder.addNameArg();
		
		return builder.getStored( jaxbObject, generatedId );
	}

	public static java.util.List<com.u2d.generated.ConstantSet> lookupConstantSet(String name)
	{
		// We are going to need some Hibernate DB stuff...
		// Get and handle on a hibernate session
		com.u2d.persist.HBMSingleSession persistenceMechanism = (com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism(); 
		Session hibernateSession = persistenceMechanism.getSession();
		
		// Form our Hibernate Query Language (hql) query into the hibernate database
		// This will test if any Service Set in the db is equal to our jxServiceSet
		Session session = persistenceMechanism.getSession();
		String hql = "from ConstantSet ConstSet where ConstSet.name=:name";
		Query hibernateQuery = session.createQuery(hql);
		
		hibernateQuery.setParameter("name", name);
		
		return hibernateQuery.list();
	}
	
	// This static method does the conversion from JAXB Binding to JMatter object 
	public static com.u2d.generated.ConstantSet lookupOrCreate( org.jts.jsidl.binding.DeclaredConstSet jxConstSet) 
	{
		// Get and handle on a hibernate session
		com.u2d.persist.HBMSingleSession persistenceMechanism = (com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism(); 
		
		com.u2d.generated.ConstantSet jmConstSet = (com.u2d.generated.ConstantSet)getStored( jxConstSet, null );
		
		// if the jmConstSet object is null, we will create it and add it to the db
		if( jmConstSet == null )
		{
			jmConstSet = new com.u2d.generated.ConstantSet();
			
			// Name
			jmConstSet.getName().setValue( jxConstSet.getName() );
			
			// Version (optional)
			if(jxConstSet.getVersion() != null)
			{
				jmConstSet.get_version().setValue(jxConstSet.getVersion());
			}

			// ConstSetId (optional)
			if(jxConstSet.getId() != null)
			{
				jmConstSet.getConstantSetId().setValue(jxConstSet.getId());
			}
			
			// constants
			java.util.List<org.jts.jsidl.binding.ConstDef> jxConstList = jxConstSet.getConstDef();
			if(jxConstList != null)
			{
				java.util.List jmConstList = jmConstSet.getConstants().getItems();
				
				for(int i = 0; i < jxConstList.size(); i++) 
				{
					com.u2d.generated.Constant jmConstant = Constant.lookupOrCreate((org.jts.jsidl.binding.ConstDef) jxConstList.get(i));
					jmConstList.add( jmConstant );
					
					// This step is important to link the inputSet to its messageDefs
					persistenceMechanism.updateAssociation(jmConstSet, jmConstant);
				}
			}
			
			// Saves the ConstSet
			persistenceMechanism.save(jmConstSet);
		}		

		jmConstSet.firePropertyChange("ConstSet", jmConstSet, jmConstSet);

		return jmConstSet;
	}	
}
