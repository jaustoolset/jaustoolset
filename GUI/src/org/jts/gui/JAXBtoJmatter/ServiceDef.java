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
import org.hibernate.Session;
import org.hibernate.Query;
import org.jts.gui.importJSIDL.ImportException;
import org.jts.gui.importJSIDL.ImportMessages;

import com.u2d.app.Context;

/* Saves the specified ServiceDef to the database.*/
public class ServiceDef 
{
      /** a precondition to this method is that all references have already been pre-loaded into the db
      */
	public static com.u2d.generated.ServiceDef lookupOrCreate( org.jts.jsidl.binding.ServiceDef jxServiceDef) throws ImportException 
	{
		// First... look up id this Service Set is already defined in our Hibernate DB
		// Get and handle on a hibernate session
		com.u2d.persist.HBMSingleSession persistenceMechanism = (com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism(); 
		
		// Lookup the service in the db
		com.u2d.generated.ServiceDef jmServiceDef = lookupServiceDef(jxServiceDef.getId(), jxServiceDef.getVersion());
		
		// if the returned jmServiceDef object is null, then the db does not contain this serviceSet
		// so we will create it and add it to the db
		if (jmServiceDef == null)
		{
			// check references
			//checkReferences( jxServiceDef );
			
			// Creates our serviceDef
			jmServiceDef = createServiceDef(jxServiceDef);
			
			// Saves the ServiceDef
			persistenceMechanism.save(jmServiceDef);
			
			// Updates possible associations
			//updateReferences(jxServiceDef);
		}
    
		jmServiceDef.firePropertyChange("ServiceDef", jmServiceDef, jmServiceDef);
    
		return jmServiceDef;
	}

	public static com.u2d.generated.ServiceDef createServiceDef(org.jts.jsidl.binding.ServiceDef jxServiceDef) throws ImportException
	{
        // Get and handle on a hibernate session (we'll need this to update our associations)
        com.u2d.persist.HBMSingleSession persistenceMechanism = (com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism(); 
        
        // create a new service Def
        com.u2d.generated.ServiceDef jmServiceDef = new com.u2d.generated.ServiceDef();
        
        // name
        jmServiceDef.getName().setValue( jxServiceDef.getName() );
        
        // id
        jmServiceDef.getServiceId().setValue( jxServiceDef.getId() );
        
        // version
        jmServiceDef.get_version().setValue( jxServiceDef.getVersion() );
        
        // description
        String description = jxServiceDef.getDescription().getContent().replaceAll("\\s\\s+ | \\n | \\r | \\t", " ").trim();;
        jmServiceDef.getDescription().setValue(description);
        
        // assumptions
        String assumptions = jxServiceDef.getAssumptions().getContent().replaceAll("\\s\\s+ | \\n | \\r | \\t", " ").trim();;
        if(assumptions.length() > 255)
        {
          String temp = assumptions.substring(0, 255);
          jmServiceDef.getAssumptions().setValue( temp );
          
          ImportMessages importMsgs = ImportMessages.getInstance();
          importMsgs.add(ImportMessages.MessageType.WARNING, "ServiceDef ("+jxServiceDef.getName()+") assumptions attribute was truncated to: \"" + temp +"\"");
        }
        else
        {
          jmServiceDef.getAssumptions().setValue(assumptions);
        }
        
        // references 
        org.jts.jsidl.binding.References refs = jxServiceDef.getReferences();
        
        if( refs != null ) {
         org.jts.jsidl.binding.InheritsFrom iref = refs.getInheritsFrom();
         List<org.jts.jsidl.binding.ClientOf> cref = refs.getClientOf();
          
         if( iref != null ) {
           com.u2d.generated.ServiceDef jmBaseServiceDef = lookupServiceDef( iref.getId(), iref.getVersion() );
             
           if( jmBaseServiceDef == null )
             throw new ImportException("ServiceDef with ID: (" + iref.getId() + ") and Version: (" + iref.getVersion() +") not found. " +
                                  "Inherited by ServiceDef with ID: " + jxServiceDef.getId() + " and Version: " + jxServiceDef.getVersion());
           
           jmServiceDef.setInheritsFrom( jmBaseServiceDef );
           jmServiceDef.getInheritsFromName().setValue( iref.getName() );  // set the alias
         }
          
         if( cref != null && cref.size() > 0 ) {
           List clientList = jmServiceDef.getClientOf().getItems();
           for(int ii=0; ii<cref.size(); ii++) {
             String id = cref.get(ii).getId();
             String version = cref.get(ii).getVersion();
             com.u2d.generated.ServiceDef jmClientServiceDef = lookupServiceDef( id, version );
            
             if( jmClientServiceDef == null) {
                if(  (jxServiceDef.getId().equals(  id ) && jxServiceDef.getVersion().equals( version )) ) {
                   clientList.add( jmServiceDef );  // circular reference
                   continue;
                }
                else                   
                   throw new ImportException("ServiceDef with ID: (" + cref.get(ii).getId() + ") and Version: (" + cref.get(ii).getVersion() +") not found. " +
                                  "Client of ServiceDef with ID: (" + jxServiceDef.getId() + ") and Version: (" + jxServiceDef.getVersion() +")");
             }
             clientList.add( jmClientServiceDef );  
           }
         }
        }
        
        // declared_const_set
        if(jxServiceDef.getDeclaredConstSet() != null)
        {
          com.u2d.generated.ConstantSet jmConstantSet = ConstantSet.lookupOrCreate(jxServiceDef.getDeclaredConstSet());
          jmServiceDef.setConstantSet( jmConstantSet );
          
          // This step is important to link the serviceSet to its constant sets
          persistenceMechanism.updateAssociation(jmServiceDef, jmConstantSet);
        }
        
        // message_set
        // com.u2d.generated.ServiceDef doesn't have a MessageSet member, rather it keeps a 
        // separate reference to an InputSet and an OutputSet
        String dbName = jxServiceDef.getName() + "_v" + jxServiceDef.getVersion();
        jmServiceDef.setInputSet( InputSet.lookupOrCreate( jxServiceDef.getMessageSet().getInputSet(), dbName ) );
        jmServiceDef.setOutputSet( OutputSet.lookupOrCreate( jxServiceDef.getMessageSet().getOutputSet(), dbName ) );
        
        // internal_event_set
        List jxList = jxServiceDef.getInternalEventsSet().getEventDefOrDeclaredEventDef();
        if(jxList != null)
        {
          List jmList = jmServiceDef.getEventDefs().getItems();
          for(int i = 0; i < jxList.size(); i++)
          {
            com.u2d.generated.EventDef eventDef = EventDef.lookupOrCreate((org.jts.jsidl.binding.EventDef) jxList.get(i));
            jmList.add(eventDef);
            persistenceMechanism.updateAssociation(jmServiceDef, eventDef);
          }
        }
        
        // protocol_behavior
        com.u2d.generated.ProtocolBehavior jmProtocolBehavior = ProtocolBehavior.lookupOrCreate( jxServiceDef );
        jmServiceDef.setProtocolBehavior( jmProtocolBehavior );
        
        return jmServiceDef;
	}
	
	public static com.u2d.generated.ServiceDef lookupServiceDef(String id, String version)
	{
		// We are going to need some Hibernate DB stuff...
		// Get and handle on a hibernate session
		com.u2d.persist.HBMSingleSession persistenceMechanism = (com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism(); 
		Session hibernateSession = persistenceMechanism.getSession();
		
		// Form our Hibernate Query Language (hql) query into the hibernate database
		// This will test if any Service Set in the db is equal to our jxServiceSet
		String hql = "from ServiceDef sd where sd.serviceId=:id and sd._version=:version";

		Query hibernateQuery = hibernateSession.createQuery(hql);
		hibernateQuery.setParameter("id", id );
		hibernateQuery.setParameter("version", version );
		
		// Get result of our query
		com.u2d.generated.ServiceDef jmServiceDef = (com.u2d.generated.ServiceDef) hibernateQuery.uniqueResult();

		return jmServiceDef;
	}

	private static void checkReferences(org.jts.jsidl.binding.ServiceDef jxServiceDef) throws ImportException
	{
		// This method takes a JAXB service def and ensures the references (inheritance and client-of) 
		// are properly defined in the db

		String referenceId;
		String referenceVersion;
		
		// Check if this Service has any references
		if(jxServiceDef.getReferences() == null)
		{
			return;
		}
				
		// Check inheritsFrom first
		if(jxServiceDef.getReferences().getInheritsFrom() != null)
		{
			referenceId = jxServiceDef.getReferences().getInheritsFrom().getId();
			referenceVersion = jxServiceDef.getReferences().getInheritsFrom().getVersion();
			
			// Check Database
			if(ServiceDef.lookupServiceDef(referenceId, referenceVersion) == null)
			{
				// The referenced service cannot be found
				// Throw exception
				throw new ImportException("ServiceDef with ID: (" + referenceId + ") and Version: (" + referenceVersion +") not found. " +
						                  "Inherited by ServiceDef with ID: " + jxServiceDef.getId() + " and Version: " + jxServiceDef.getVersion());
			}
		}
				
		// Check ClientOf 
		if(jxServiceDef.getReferences().getClientOf().size() > 0)
		{
			List<org.jts.jsidl.binding.ClientOf> clientList =jxServiceDef.getReferences().getClientOf();
			
			for(int j = 0; j < clientList.size(); j++)
			{
				referenceId = clientList.get(j).getId();
				referenceVersion = clientList.get(j).getVersion();

				// Check for self-reference
				if( !( referenceId.equals(jxServiceDef.getId()) && referenceVersion.equals(jxServiceDef.getVersion()) ) )
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

	public static void updateReferences(org.jts.jsidl.binding.ServiceDef jxServiceDef) throws ImportException
	{
		com.u2d.persist.HBMSingleSession persistenceMechanism = (com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism(); 
		com.u2d.generated.ServiceDef jmServiceDef = lookupServiceDef(jxServiceDef.getId(), jxServiceDef.getVersion());
		
		if(jmServiceDef == null)
		{
			// The service (whose references we need to update) cannot be found in the db
			// Throw exception
			throw new ImportException("ServiceDef with ID: (" + jxServiceDef.getId() + ") and Version: (" + 
										jxServiceDef.getVersion() +") not found in db. Cannot update references.");
		}
		
		// Check for references at all 
		// Note: this should probably be checked before calling this function, but we will check here as well for completeness 
		if(jxServiceDef.getReferences() == null)
		{
			return;
		}
		
		// Update InheritsFrom Relationship
		if(jxServiceDef.getReferences().getInheritsFrom() != null)
		{
			String referenceId = jxServiceDef.getReferences().getInheritsFrom().getId();
			String referenceVersion = jxServiceDef.getReferences().getInheritsFrom().getVersion();
			
			// Find the inheritsFrom object in the db
			com.u2d.generated.ServiceDef jmInherits = lookupServiceDef(referenceId, referenceVersion);
			
			if(jmInherits == null)
			{
				// This shouldn't happen, but we have an inheritsFrom reference which is not defined in the db
				// Throw exception
				throw new ImportException("ServiceDef with ID: (" + referenceId + ") and Version: (" + referenceVersion + ") not found in db. " +
		                     "Inherited by ServiceDef with ID: (" + jxServiceDef.getId() + ") and Version: (" + jxServiceDef.getVersion() + ")");
			}
			
			jmServiceDef.setInheritsFrom(jmInherits);
			persistenceMechanism.updateAssociation(jmServiceDef, jmInherits);
		}
		
		// Update ClientOf Relationships
		if(jxServiceDef.getReferences().getClientOf().size() > 0)
		{
			List<org.jts.jsidl.binding.ClientOf> jxClientOfList = jxServiceDef.getReferences().getClientOf();
			List<com.u2d.generated.ServiceDef> jmClientOfList = jmServiceDef.getClientOf().getItems();
			
			for(int j = 0; j < jxClientOfList.size(); j++)
			{
				String referenceId = jxClientOfList.get(j).getId();
				String referenceVersion = jxClientOfList.get(j).getVersion();
				
				// Find the inheritsFrom object in the db
				com.u2d.generated.ServiceDef jmClientOf = lookupServiceDef(referenceId, referenceVersion);
				
				if(jmClientOf == null)
				{
					// This shouldn't happen, but we have an clientOf reference which is not defined in the db
					// Throw exception
					throw new ImportException("ServiceDef with ID: (" + referenceId + ") and Version: (" + referenceVersion + ") not found in db. " +
			                                  "ClientOf Relationship in ServiceDef with ID: (" + jxServiceDef.getId() + ") and Version: (" + jxServiceDef.getVersion() + ")");
				}
				
				if(!jmClientOfList.contains(jmClientOf))
				{
					jmClientOfList.add(jmClientOf);
					persistenceMechanism.updateAssociation(jmServiceDef, jmClientOf);
				}
			}
		}
	}
}
