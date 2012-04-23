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

package org.jts.validator;

import org.hibernate.Session;
import org.hibernate.Query;
import com.u2d.app.Context;

public class ServiceDef
{
	/**
	 * Validates a JSIDL Service Definition Element.
	 * <pre>
		service_def =
		  element service_def {
		  		# 1. TODO: RFE: All Send_Action messages must be defined in message set 
		    attribute name { identifier },
				# 1. Must follow ANSI C syntax          
		    attribute id { xsd:anyURI },
		    attribute version { version_num },
		    	# 1. Must follow JSIDL syntax     
		    description,
		    assumptions,
		    references?,
		    	# 1. Referencing: ensure that service does not inherit from itself
		    declared_const_set?,
		    declared_type_set?,
		    message_set,
		  		# 1: Must define a message set
		    internal_event_set,
		    protocol_behavior
		  		# TODO: 1: Must define a protocol behavior
		  }
	 * </pre>
  	 * @param sDef JSIDL binding object of the  to be checked
	 * @throws ValidatorException
	 */
	public static void validate(org.jts.jsidl.binding.ServiceDef sDef) throws ValidatorException
	{
		Validator.validateName(sDef.getName());
		Validator.validateVersion(sDef.getVersion());
		Validator.validateURI(sDef.getId());
		
		validateAgainstDatabase( sDef.getVersion(), sDef.getId() );

		if(	sDef.getReferences() != null &&
			sDef.getReferences().getInheritsFrom() != null &&
			sDef.getReferences().getInheritsFrom().getId().equals(sDef.getId()) &&
			sDef.getReferences().getInheritsFrom().getVersion().equals(sDef.getVersion()) )
		{
			throw new ValidatorException("Service definition cannot inherit from itself");
		}
		
		if(sDef.getMessageSet() == null)
		{
			throw new ValidatorException("Service definition must define a message set");			
		}
		MessageSet.validate(sDef.getMessageSet());

             // relax this constraint to allow user to save serviceDef before editing protocol behavior in case of
             // inheritance
		/*if (sDef.getProtocolBehavior() == null)
		{
				throw new ValidatorException("Service definition must define protocol behavior");
		}*/


	}
	
	private static void validateAgainstDatabase( String version, String id ) {
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
        	if( hibernateQuery.uniqueResult() != null ) {
        	      java.awt.EventQueue.invokeLater( new Runnable() {
	        	         public void run() {
	        	            int rval = javax.swing.JOptionPane.showConfirmDialog( null,
	        	            "A service definition with this id already exists in the database. \n Do you want to overwrite this service?",
	        	            "JTS Confirm Dialog", javax.swing.JOptionPane.YES_NO_OPTION) ;
	        	            setUserOption( rval );
	        	           
	        	         }
	         	     });
	 	   
	 	   while( userOption == -1 ) 
	 	      try { Thread.sleep(500); } catch(InterruptedException ie) { }

                if( userOption == 0 ) {  // yes
                   userOption = -1;
                   return;
                } else if( userOption == 1 || userOption == 2 )  // no, cancel
                   userOption = -1;
                   throw new ValidatorException("To avoid an overwrite, enter a unique service id");
            }
	}
	
	private static int userOption = -1;
	private static void setUserOption( int option ) {
	   userOption = option;
	}
}
