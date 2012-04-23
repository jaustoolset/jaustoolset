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

import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Query;
import org.jts.gui.importJSIDL.ImportException;

import com.u2d.app.Context;

/* Saves the specified ServiceSet to the database.
*/
public class ServiceSet 
{
	public static Object getStored( Object jaxbObject, Long generatedId )
	{
		org.jts.gui.util.QueryBuilder builder = new org.jts.gui.util.QueryBuilder();
		builder.setTableName( "ServiceSet" );
		builder.addIdArg();
		builder.addVersionArg();
		
		return builder.getStored( jaxbObject, generatedId );
	}

	public static com.u2d.generated.ServiceSet lookupServiceSet(String id, String version)
	{
		// We are going to need some Hibernate DB stuff...
		// Get and handle on a hibernate session
		com.u2d.persist.HBMSingleSession persistenceMechanism = (com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism(); 
		Session hibernateSession = persistenceMechanism.getSession();
		
		String hql = "from ServiceSet ss where ss.serviceSetId=:id and ss._version=:version";
		Query hibernateQuery = hibernateSession.createQuery(hql);
		hibernateQuery.setParameter("id", id );
		hibernateQuery.setParameter("version", version );

		// Get result of our query
		com.u2d.generated.ServiceSet jmServiceSet = (com.u2d.generated.ServiceSet) hibernateQuery.uniqueResult();
    
		return jmServiceSet;
	}
	
	public static void lookupOrCreate( org.jts.jsidl.binding.ServiceSet jxServiceSet) 
	{
		// First... look up id this Service Set is already defined in our Hibernate DB
		// Get and handle on a hibernate session
		com.u2d.persist.HBMSingleSession persistenceMechanism = (com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism(); 

		// Get result of our query
		com.u2d.generated.ServiceSet jmServiceSet = (com.u2d.generated.ServiceSet)getStored( jxServiceSet, null );
    
		// if the returned jmServiceSet object is null, then the db does not contain this serviceSet
		// so we will create it and add it to the db
		if (jmServiceSet == null)
		{
			// Check if the input set's references are properly defined
			// Note: This method throws an exception if all service references are not properly defined
			ServiceSet.checkReferences(jxServiceSet);
			
			// create a new service set
			jmServiceSet = new com.u2d.generated.ServiceSet();

			// name
			jmServiceSet.getName().setValue( jxServiceSet.getName() );

			// id
			jmServiceSet.getServiceSetId().setValue( jxServiceSet.getId() );

			// version
			jmServiceSet.get_version().setValue( jxServiceSet.getVersion() );

			// description
			String description = jxServiceSet.getDescription().getContent().replaceAll("\\s\\s+ | \\n | \\r | \\t", " ").trim();;
			jmServiceSet.getDescription().setValue(description);

			// Constant Sets
			List<org.jts.jsidl.binding.DeclaredConstSet> jxConstantSetList = jxServiceSet.getDeclaredConstSet();
			if(jxConstantSetList != null)
			{
				List<com.u2d.generated.ConstantSet> jmConstantSetList = jmServiceSet.getConstantSets().getItems();
				
				for(int i = 0; i < jxConstantSetList.size(); i++)
				{
					com.u2d.generated.ConstantSet jmConstantSet = ConstantSet.lookupOrCreate(jxConstantSetList.get(i));
					jmConstantSetList.add( jmConstantSet );
					
					// This step is important to link the serviceSet to its serviceDefs
					persistenceMechanism.updateAssociation(jmServiceSet, jmConstantSet);
				}
			}
			
			// service_defs
			List<org.jts.jsidl.binding.ServiceDef> jxServiceDefList = jxServiceSet.getServiceDef(); 
			
			if( jxServiceDefList != null ) 
			{
				List<com.u2d.generated.ServiceDef> jmServiceDefList = jmServiceSet.getServiceDefs().getItems();
				     
				for( int ii=0; ii<jxServiceDefList.size(); ii++ ) 
				{
					com.u2d.generated.ServiceDef sd = ServiceDef.createServiceDef( (org.jts.jsidl.binding.ServiceDef)jxServiceDefList.get(ii));
					jmServiceDefList.add( sd );
					
					// This step is important to link the serviceSet to its serviceDefs
					persistenceMechanism.updateAssociation(jmServiceSet, sd);
				}
				
				for( int ii=0; ii<jxServiceDefList.size(); ii++ ) 
				{
					if(((org.jts.jsidl.binding.ServiceDef)jxServiceDefList.get(ii)).getReferences() != null)
					{
						ServiceDef.updateReferences(jxServiceDefList.get(ii));
					}
				}				
			}
   
			persistenceMechanism.save(jmServiceSet); 
		}
		
		jmServiceSet.firePropertyChange("ServiceSet", jmServiceSet, jmServiceSet);
	}

	public static void checkReferences(org.jts.jsidl.binding.ServiceSet jxServiceSet) throws ImportException
	{
		// This method takes a JAXB service set and ensures that for each service defined in the input set, 
		// the references (inheritance and client-of) are properly defined, either in the db or the input set

		// Create a hashMap of input services, this will simplify resolving our dependencies
		List<org.jts.jsidl.binding.ServiceDef> jxList = jxServiceSet.getServiceDef();
		HashMap<String, String> importSetMap = new HashMap<String, String>(jxList.size());
		if(jxList != null)
		{
			for(int i=0; i < jxList.size(); i++)
			{
				org.jts.jsidl.binding.ServiceDef jxServiceDef = jxList.get(i);
				importSetMap.put(jxServiceDef.getId(), jxServiceDef.getVersion());
			}

			// Okay, now we will check our import ServiceDefs
			for(int i=0; i < jxList.size(); i++)
			{
				String referenceId;
				String referenceVersion;
				
				org.jts.jsidl.binding.ServiceDef jxServiceDef = jxList.get(i);
				
				// Check if this Service has any references
				if(jxServiceDef.getReferences() == null)
				{
					continue;
				}
				
				// Check inheritsFrom first
				if(jxServiceDef.getReferences().getInheritsFrom() != null)
				{
					referenceId = jxServiceDef.getReferences().getInheritsFrom().getId();
					referenceVersion = jxServiceDef.getReferences().getInheritsFrom().getVersion();
					
					// Check HashMap
					if(importSetMap.get(referenceId) == null || !importSetMap.get(referenceId).equals(referenceVersion))
					{
						// Check Database
						if(ServiceDef.lookupServiceDef(referenceId, referenceVersion) == null)
						{
							// The referenced service cannot be found
							// Throw exception
							throw new ImportException("ServiceDef with ID: (" + referenceId + ") and Version: (" + referenceVersion +") not found. " +
									                  "Inherited by ServiceDef with ID: " + jxServiceDef.getId() + " and Version: " + jxServiceDef.getVersion());
						}
					}
				}
				
				if(jxServiceDef.getReferences().getClientOf().size() > 0)
				{
					List<org.jts.jsidl.binding.ClientOf> clientList = jxServiceDef.getReferences().getClientOf();
					
					for(int j = 0; j < clientList.size(); j++)
					{
						referenceId = clientList.get(j).getId();
						referenceVersion = clientList.get(j).getVersion();

						if(importSetMap.get(referenceId) == null || !importSetMap.get(referenceId).equals(referenceVersion))
						{
							// Check Database
							if(ServiceDef.lookupServiceDef(referenceId, referenceVersion) == null)
							{
								// The referenced service cannot be found
								// Throw exception
								throw new ImportException("ServiceDef with ID: (" + referenceId + ") and Version: (" + referenceVersion +") not found. " +
										                  "Client of ServiceDef with ID: " + jxServiceDef.getId() + " and Version: " + jxServiceDef.getVersion());
							}
						}
					}
				}
			}
		}
	}
}

