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

import java.util.ArrayList;
import java.util.List;

import com.u2d.list.RelationalList;

public class ServiceSet
{
	/**
	 * Validates a JSIDL service set element.
	 * <pre>
		service_set = 
		  element service_set {
		  		# 1. TODO: Must have at least one element in one of the contained lists
		    attribute name { identifier },
				# 1. Must follow ANSI C syntax      
		    attribute id { xsd:anyURI },
		    attribute version { version_num },
		    	# 1. Must follow JSIDL syntax     
		    description,
		    declared_const_set*,
		    	# 1. All names in list must be unique 
		    declared_type_set*,
		    	# 1. All names in list must be unique
		    declared_service_set_ref*,
		    	# 1. All names in list must be unique
		    declared_service_def*,
		    	# 1. All names in list must be unique
		    service_def*
		    	# 1. All names in list must be unique
		    	# 2. All services must inherit from services found within the set
		    	# 3. Uniqueness: ensure that message names in inheriting services are different from those in the base services
				# 4. All services must be clients of services found within the set
		  }
	 * </pre>
  	 * @param sSet JSIDL binding object of the service set to be checked
	 * @throws ValidatorException 	if the object has an invalid name, version string or repeated elements in any
	 * 								of its lists
	 */
	public static void validate(org.jts.jsidl.binding.ServiceSet sSet) throws ValidatorException
	{
		Validator.validateName(sSet.getName());
		Validator.validateVersion(sSet.getVersion());
		Validator.validateURI(sSet.getId());
		
		List<org.jts.jsidl.binding.DeclaredConstSet> constList = sSet.getDeclaredConstSet();
		Validator.validateUniqueNameList(constList);

		List<org.jts.jsidl.binding.DeclaredTypeSet> typeList = sSet.getDeclaredTypeSet();
		Validator.validateUniqueNameList(typeList);

		List<org.jts.jsidl.binding.ServiceDef> sDefList = sSet.getServiceDef();
		Validator.validateUniqueNameList(sDefList);
		
		validateServiceDefReferences( sDefList );
		
		validateServiceDefHasProtocol( sDefList );
	}

	private static void validateServiceDefReferences( List<org.jts.jsidl.binding.ServiceDef> sDefList ) throws ValidatorException
	{
		for(int i = 0; i < sDefList.size(); i++)
		{
			org.jts.jsidl.binding.References refs = sDefList.get(i).getReferences();
			if(	refs != null &&	refs.getInheritsFrom() != null)
			{
				org.jts.jsidl.binding.ServiceDef baseDef = 
					findService(sDefList,
								refs.getInheritsFrom().getId(),
								refs.getInheritsFrom().getVersion());
				// # 2. All services must inherit from services found within the set
				if(baseDef == null)
				{
					throw new ValidatorException("Service: " + sDefList.get(i).getName() + " inherits from undefined service: " + refs.getInheritsFrom().getId() + ", please add to list of services");
				}
				
		    	// # 3. Uniqueness: ensure that message names in inheriting services are different from those in the base services
				if(	baseDef.getMessageSet() != null && 
					baseDef.getMessageSet().getInputSet() != null &&
					baseDef.getMessageSet().getInputSet().getMessageDefOrDeclaredMessageDef() != null &&
					sDefList.get(i).getMessageSet() != null && 
					sDefList.get(i).getMessageSet().getInputSet() != null &&
					sDefList.get(i).getMessageSet().getInputSet().getMessageDefOrDeclaredMessageDef() != null)
				{
					if(	!Validator.namesListsUnique(baseDef.getMessageSet().getInputSet().getMessageDefOrDeclaredMessageDef(),
													sDefList.get(i).getMessageSet().getInputSet().getMessageDefOrDeclaredMessageDef()))
					{
						throw new ValidatorException("Service: " + sDefList.get(i).getName() + " has messages defined in its base (inherits_from) service");
					}
				}
				if(	baseDef.getMessageSet() != null && 
					baseDef.getMessageSet().getOutputSet() != null &&
					baseDef.getMessageSet().getOutputSet().getMessageDefOrDeclaredMessageDef() != null &&
					sDefList.get(i).getMessageSet() != null && 
					sDefList.get(i).getMessageSet().getOutputSet() != null &&
					sDefList.get(i).getMessageSet().getOutputSet().getMessageDefOrDeclaredMessageDef() != null)
				{
					if(	!Validator.namesListsUnique(baseDef.getMessageSet().getOutputSet().getMessageDefOrDeclaredMessageDef(),
													sDefList.get(i).getMessageSet().getOutputSet().getMessageDefOrDeclaredMessageDef()))
					{
						throw new ValidatorException("Service: " + sDefList.get(i).getName() + " has messages defined in its base (inherits_from) service");
					}
				}
			}
			
			if(	refs != null &&	refs.getClientOf() != null)
			{
				for(int j = 0; j < refs.getClientOf().size(); j++)
				{
					org.jts.jsidl.binding.ServiceDef serverDef = 
						findService(sDefList,
									refs.getClientOf().get(j).getId(),
									refs.getClientOf().get(j).getVersion());

					// # 4. All services must be clients of services found within the set
					if(serverDef == null)
					{
						throw new ValidatorException("Service: " + sDefList.get(i).getName() + " is client of undefined service");
					}
				} // for all clients				
			}			
		}// For all services in set
	}
	
	private static 	org.jts.jsidl.binding.ServiceDef 
					findService(List<org.jts.jsidl.binding.ServiceDef> sList,
								String id, String version)
	{
		for(int i = 0; i < sList.size(); i++)
		{
			if(	sList.get(i).getId().equals(id) &&
				sList.get(i).getVersion().equals(version) )
			{
				return sList.get(i);
			}
		}
		return null;
	}

	/**
	 * Loops through all service definitions in input list and throws exception
	 * when a service does does not have a protocol behavior defined for it
	 * (Note: currently, when a service def is saved, an empty place holder
	 * is stored whenever a protocol is not added to the definition.  This was done
	 * so that exported xml is still valid.  This method will check against the
	 * 'null' protocol that is inserted.  This may need to change for the edge case
	 * of a user actually wanting a pb with this information)
	 * @param sDefList
	 * @throws ValidatorException
	 */
	private static void validateServiceDefHasProtocol( List<org.jts.jsidl.binding.ServiceDef> sDefList ) throws ValidatorException
	{
		for( org.jts.jsidl.binding.ServiceDef sdef : sDefList )
		{
			org.jts.jsidl.binding.ProtocolBehavior pb = sdef.getProtocolBehavior();

			if( pb == null )
			{
				throw new ValidatorException("Service: " + sdef.getName() + " needs a protocol behavior");
			}

			// check for place holder pb
			if( pb.getStart().size() == 1 && pb.getStateMachine().size() == 1 )
			{
				org.jts.jsidl.binding.Start st = pb.getStart().get( 0 );
				org.jts.jsidl.binding.StateMachine sm = pb.getStateMachine().get( 0 );

				if( st.getStateMachineName().compareTo( "NullFSM" ) == 0 &&
						st.getStateName().compareTo( "Null" ) == 0 &&
						sm.getName().compareTo( "NullFSM" ) == 0 )
				{
					throw new ValidatorException("Service: " + sdef.getName() + " needs a protocol behavior");
				}
			}
		}
	}

	/**
	 * Method to add inherited services not included in the service set definition but needed for
	 * a complete service set
	 * @param input
	 */
	public static void tryToAddBaseServices( com.u2d.generated.ServiceSet input )
	{
		org.jts.jsidl.binding.ServiceSet set = org.jts.gui.jmatterToJAXB.ServiceSet.convert( input );
		
		List<org.jts.jsidl.binding.ServiceDef> currentInteritsFrom = set.getServiceDef();

		if( addReferencedInheritsFrom( currentInteritsFrom ) )
		{
			addBaseServices( currentInteritsFrom, input.getServiceDefs() );
			//new ValidatorWarning("Inherited services added to set").run();
		}
	}

	/**
	 * Recurse to get a list of every reference service def until no references are found
	 * @param currentInteritsFrom
	 */
	private static boolean addReferencedInheritsFrom( List<org.jts.jsidl.binding.ServiceDef> currentInteritsFrom )
	{
		boolean throwError = false;

		for( int i = 0; i < currentInteritsFrom.size(); i++ )
		{
			org.jts.jsidl.binding.ServiceDef sDef = currentInteritsFrom.get( i );

			if( sDef.getReferences() == null || 
					sDef.getReferences().getInheritsFrom() == null )
			{
				continue;
			}

			String id = sDef.getReferences().getInheritsFrom().getId();
			String version = sDef.getReferences().getInheritsFrom().getVersion();

			org.jts.jsidl.binding.ServiceDef foundService = findService( currentInteritsFrom, id, version );
			if( foundService == null )
			{
				currentInteritsFrom.add( lookupServiceDef( id, version ) );
				throwError = true;
			}
		}
		
		return throwError;
	}

	/**
	 * Convenience method to lookup and convert generated services to binding objects
	 * @param id
	 * @param version
	 * @return
	 */
	private static org.jts.jsidl.binding.ServiceDef lookupServiceDef( String id, String version )
	{
		com.u2d.generated.ServiceDef generatedService = 
			org.jts.gui.JAXBtoJmatter.ServiceDef.lookupServiceDef( id, version );

		return org.jts.gui.jmatterToJAXB.ServiceDef.convert( generatedService );
	}

	/**
	 * Lookup and add every binding serviceDef to the input list
	 * @param serviceList
	 * @param inputList
	 */
	private static void addBaseServices( List<org.jts.jsidl.binding.ServiceDef> serviceList, RelationalList inputList )
	{
		inputList.clear();

		for( org.jts.jsidl.binding.ServiceDef service : serviceList )
		{
			String id = service.getId();
			String version = service.getVersion();

			com.u2d.generated.ServiceDef generatedToAdd = 
				org.jts.gui.JAXBtoJmatter.ServiceDef.lookupServiceDef( id, version );

			inputList.add( generatedToAdd );
		}
	}
	
}
