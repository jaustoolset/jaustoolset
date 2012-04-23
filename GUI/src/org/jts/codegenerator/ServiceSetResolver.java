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

package org.jts.codegenerator;

import org.jts.jsidl.binding.*;
import org.jts.codegenerator.support.*;

import java.io.File;
import java.util.Hashtable;
import java.util.Vector;

/**
 * 
 * @author Dave Martin
 * 
 * This class resolves dependency for a service definition.  It
 * must have access to the complete service set to access the parent
 * and/or client services.  Note that this function operates on a
 * COPY of the service set, so nothing is permanently modified
 * in the dataabase.
 * 
 */
public class ServiceSetResolver
{
	private ServiceSet sSet;
	private Vector<ServiceDef> toBeRemoved;
	private boolean debug;
	private CodeLines.CodeType codeType;
	
	public ServiceSetResolver(CodeLines.CodeType codeType, ServiceSet sSet)
	{
		this.debug = false;
		this.sSet = sSet;
		this.codeType = codeType;
	    this.toBeRemoved = new Vector<ServiceDef>(sSet.getServiceDef().size());
	}

	public ServiceSet run() throws CodeGeneratorException
	{
		// Now resolve the messages, internal events and protocol for the service
		if (debug) System.out.println("Resolving dependencies for " + sSet.getName());

		// Resequence the service set so that parents are resolved before children.
		resequenceServiceSet();
		
		/*
		 * The order of dependency resolution is important.  We need to resolve
		 * inheritance first, then client-of.  This ensures that the server's parent(s)
		 * are also included in the client-of relationship.
		 */
		for (ServiceDef sDef : sSet.getServiceDef())
			resolveInheritence(sDef);
		resolveClientOf();
		
		// Add a dummy state that handles all inputs.  This defines
		// transitions for the default case.
		for (ServiceDef sDef : sSet.getServiceDef())
		    addDummyState(sDef);		

		if (debug) System.out.println("Done resolving depends for " + sSet.getName());
		return sSet;
	}

	protected void resequenceServiceSet()
	{
	    ServiceSet newSet = new ServiceSet();
		newSet.setName( sSet.getName() );
		newSet.setId( sSet.getId() );
		newSet.setVersion( sSet.getVersion() );
		 
		// For each service in the old set, add parents first
		for (ServiceDef sDef : sSet.getServiceDef())
		   addServicesParentFirst( sDef, newSet, sSet );

		// This newly resequenced set becomes the service set we'll work with internally
		this.sSet = newSet;
	}

	protected void addServicesParentFirst( ServiceDef sDef, ServiceSet newSet, ServiceSet oldSet )
	{
	    // If this service has a parent, allow it to add itself first
		try
		{
			ServiceDef parent = org.jts.codegenerator.support.InheritanceHelper.findServiceDef( oldSet,
												sDef.getReferences().getInheritsFrom().getId(),
												sDef.getReferences().getInheritsFrom().getVersion());
			if (parent != null) addServicesParentFirst( parent, newSet, oldSet );
		} catch (Exception e){}

		// If this service is not already in the new set, add it
		if (!newSet.getServiceDef().contains( sDef ))
		    newSet.getServiceDef().add( sDef );
	}
	
	protected void addDummyState(ServiceDef sDef)
	{
	
	   for (StateMachine sm : sDef.getProtocolBehavior().getStateMachine())
	   {
	       Vector<String> addedTransitions = new Vector<String>();
	       
	       State dummyState = new State();
	       dummyState.setName("Internally_Generated_State_DO_NOT_USE");
	       
	       // Add a transition for each input message
	       for ( Object input : sDef.getMessageSet().getInputSet().getMessageDefOrDeclaredMessageDef() )
	       {
	            if ( input instanceof MessageDef )
	            {
	                if (!addedTransitions.contains( ((MessageDef)input).getName() ))
	                {
						Transition dummyTrans = new Transition();
						Internal internal = new Internal();
						dummyTrans.setName( ((MessageDef)input).getName() );
						dummyTrans.setInternal( internal );
						dummyState.getTransition().add( dummyTrans );
						addedTransitions.add( ((MessageDef)input).getName() );
					}
				}
			}
			
			// Add a transition for each internal event
			for ( Object input : sDef.getInternalEventsSet().getEventDefOrDeclaredEventDef() )
		   {
				if ( input instanceof EventDef )
				{
					if (!addedTransitions.contains( ((EventDef)input).getName() ))
					{
						Transition dummyTrans = new Transition();
						Internal internal = new Internal();
						dummyTrans.setName( ((EventDef)input).getName() );
						dummyTrans.setInternal( internal );
						dummyState.getTransition().add( dummyTrans );
						addedTransitions.add( ((EventDef)input).getName() );
					}
				}
			}
			
			sm.getState().add( dummyState );
		}
	}
	
	protected void resolveClientOf()
	{
		java.util.Hashtable msgsToAdd = new java.util.Hashtable();

		// Resolve the client-of for each service def.  Note that we add the messages
		// to an interim location first, until all services are checked.  Only then
		// do we actually add the messages.  This solve the problem where A which 
		// is a client-of B which is a client-of C should not 
		// include C's vocabulary.  Otherwise, we would have to be careful of the sequence
		// of which services are resolved first.
		for (ServiceDef sDef : sSet.getServiceDef())
		{
				if (debug) System.out.println("Resolving client-of relationships for " + sDef.getName());

				try 
				{
						// Loop through all client-of relationship
						for (ClientOf server : sDef.getReferences().getClientOf())
						{
								// Find the referenced service in the service set
								ServiceDef ref = org.jts.codegenerator.support.InheritanceHelper.findServiceDef(sSet, server.getId(), server.getVersion());
								if (ref != null) 
								{
										// Now add its vocabulary to an temporary placeholder.
										if (debug) System.out.println("Found " + sDef.getName() + " as client-of " + ref.getName());
										if (!msgsToAdd.containsKey(sDef))
										{
												msgsToAdd.put(sDef, new ServiceDef());
												((ServiceDef) msgsToAdd.get(sDef)).setMessageSet(new MessageSet());
												((ServiceDef) msgsToAdd.get(sDef)).getMessageSet().setInputSet(new InputSet());
												((ServiceDef) msgsToAdd.get(sDef)).getMessageSet().setOutputSet(new OutputSet());
												((ServiceDef) msgsToAdd.get(sDef)).setInternalEventsSet(new InternalEventsSet());
										}
										combineVocabulary((ServiceDef) msgsToAdd.get(sDef), ref, true);
								}
						}
				}
				catch (Exception e)
				{
				// No need to do much on this exception.  It likely means the service has no
				// references or clientOf relationships.
				}
		}

		// Now that all client-of relationship are resolved, we can modify the 
		// actual service defs.
		for (ServiceDef sDef : sSet.getServiceDef())
			if (msgsToAdd.containsKey(sDef))
			   combineVocabulary(sDef, (ServiceDef) msgsToAdd.get(sDef), false);
	}

	protected void resolveInheritence(ServiceDef sDef)
	{
		if (debug) System.out.println("Resolving inheritence relationships for " + sDef.getName());
		try
		{
				// Get the parent
				ServiceDef parent = org.jts.codegenerator.support.InheritanceHelper.findServiceDef( sSet,
													sDef.getReferences().getInheritsFrom().getId(),
													sDef.getReferences().getInheritsFrom().getVersion());
				if (parent != null)
				{
						if (debug) System.out.println("Found " + parent.getName() + " as parent of " + sDef.getName());

						// This function is iterative.  When we find a parent, allow it
						// to resolve its inheritence first.
						resolveInheritence(parent);

						// Add the vocabulary of the parent
						combineVocabulary(sDef, parent, false);
						
						
						// Add the protocol behavior of the parent
						combineProtocolBehavior(sDef, parent);

						// The child also inherits any 'client-of' dependency of the parent
						try
						{
								for (ClientOf server : parent.getReferences().getClientOf())
										sDef.getReferences().getClientOf().add(server);
						}
						catch (Exception e)
						{
								// nothing to do.  just means there is no dependency.
						}
				}
		}
		catch (Exception e)
		{
		   // Nothing to do.  Probably just means the parent doesn't exist.
		}
	}

	protected void combineVocabulary(ServiceDef target, ServiceDef source, boolean asClient)
	{
		// Add the output messages to our input set.
		for (Object msg : source.getMessageSet().getOutputSet().getMessageDefOrDeclaredMessageDef())
		{
			if (asClient)
			{
				if (debug) System.out.println("Adding input message " + ((MessageDef)msg).getName());
			    target.getMessageSet().getInputSet().getMessageDefOrDeclaredMessageDef().add(msg);
			}
			else
			{
				if (debug) System.out.println("Adding Output message " + ((MessageDef)msg).getName());
				target.getMessageSet().getOutputSet().getMessageDefOrDeclaredMessageDef().add(msg);
			}
		}

		// Add the input messages to our output set
		for (Object msg : source.getMessageSet().getInputSet().getMessageDefOrDeclaredMessageDef())
		{
			if (asClient)
			{
				if (debug) System.out.println("Adding output message " + ((MessageDef)msg).getName()); 
				target.getMessageSet().getOutputSet().getMessageDefOrDeclaredMessageDef().add(msg);
			}
			else
			{
				if (debug) System.out.println("Adding input message " + ((MessageDef)msg).getName()); 
				target.getMessageSet().getInputSet().getMessageDefOrDeclaredMessageDef().add(msg);
			}
		}

		// Internal events get copied only when not a client-of relationship
		if (!asClient)
		{
			for (Object event : source.getInternalEventsSet().getEventDefOrDeclaredEventDef())
			{
				if (debug) System.out.println("Adding event " + ((EventDef)event).getName());
				target.getInternalEventsSet().getEventDefOrDeclaredEventDef().add(event);
			}
		}
	}
	
	protected void combineProtocolBehavior(ServiceDef target, ServiceDef source)
	{
			try
			{
				// Merge each state machine in the source list into the target.
				for (StateMachine sourceSM : source.getProtocolBehavior().getStateMachine())
				{
						if (debug) System.out.println("Merging state machine " + sourceSM.getName());

						// If this state machine does not exist in the target, its simply
						// an add.  Otherwise, merge the state machines.
						// NOTE: per JTS state machine naming convention, target state machine names
						// NOT have the current service names prepended because they are now jaxb objects
						StateMachine targetSM = findStateMachine(target, sourceSM.getName());
						if (targetSM == null)
						{
						        targetSM = new StateMachine();
						        targetSM.setName( sourceSM.getName() );
						        Start targetStart = new Start();
						        targetStart.setStateMachineName( findStartState(source, sourceSM.getName()).getStateMachineName() );
						        targetStart.setStateName( findStartState(source, sourceSM.getName()).getStateName() );
						        target.getProtocolBehavior().getStart().add( targetStart );
								target.getProtocolBehavior().getStateMachine().add(targetSM);
						}

						mergeStateMachines(targetSM, sourceSM);
				}
			}
			catch (Exception e)
			{
					if (debug) System.out.println("Exception in protocolBehavior combine");
			}
	}

	protected void mergeStateMachines(StateMachine target, StateMachine source)
	{
			try
			{
				// Merge each state in the source list into the target.
				for (State sourceState : source.getState())
				{
						if (debug) System.out.println("Merging state " + sourceState.getName());

						// If this state machine does not exist in the target, its simply
						// an add.  Otherwise, merge the state machines.
						State targetState = findState(target, sourceState.getName());
						if (targetState == null)
						{
						        targetState = new State();
						        targetState.setName( sourceState.getName() );
								target.getState().add(targetState);
						}

						mergeStates(targetState, sourceState);
				}
			}
			catch (Exception e)
			{
					if (debug) System.out.println("Exception in mergeStateMachines");
			}
	}

	protected void mergeStates(State target, State source)
	{
			try
			{
				// Merge each state in the source list into the target.
				for (State sourceSubState : source.getState())
				{
						if (debug) System.out.println("Merging substate " + sourceSubState.getName());

						// If this state machine does not exist in the target, its simply
						// an add.  Otherwise, merge the state machines.
						State targetSubState = findState(target, sourceSubState.getName());
						if (targetSubState == null)
						{
						        targetSubState = new State();
						        targetSubState.setName( sourceSubState.getName() );
								target.getState().add(targetSubState);
						}
						else

						mergeStates(targetSubState, sourceSubState);
				}
			}
			catch (Exception e)
			{
					// This is ignorable.  Likely means that the source contains no substates.
					if (debug) System.out.println("Exception in when merging states: " + source.getName() + " with " + target.getName());
			}

	}
	
	protected StateMachine findStateMachine(ServiceDef sDef, String name)
	{
			for (StateMachine sm : sDef.getProtocolBehavior().getStateMachine())
			{
					if (debug) System.out.println("[FindStateMachine] Comparing " + name + " versus " + sm.getName());
					
					// NOTE: per JTS state machine naming convention, the jaxb state machine will have the 
					// inherits-from sdef alias prepended to it's state machine names.  since we are trying to 
					// match the parent state machine with the derived one, we need to get rid of the first
					// portion of the derived sm name
					String smName = sm.getName();
					if( smName.contains( "." ) )
					{
						smName = smName.substring( smName.indexOf( "." ) + 1 );
					}
					if (smName.equalsIgnoreCase(name))
					{
							if (debug) System.out.println("Found state machine match for " + sm.getName() +" [" + name + "]");
							return sm;
					}
			}
			if (debug) System.out.println("Failed to find matching state machine for " + name);
			return null;
	}

	protected Start findStartState(ServiceDef sDef, String smName)
	{
			for (Start ss : sDef.getProtocolBehavior().getStart())
			{
					if (ss.getStateMachineName().equalsIgnoreCase(smName))
					{
							if (debug) System.out.println("Found start statematch for " + ss.getStateMachineName() +" [" + smName + "]");
							return ss;
					}
			}
			if (debug) System.out.println("Failed to find matching state start for " + smName);
			return null;
	}

	protected State findState(StateMachine sm, String name)
	{
			for (State ss : sm.getState())
			{
					if (ss.getName().equalsIgnoreCase(name))
					{
							if (debug) System.out.println("Found  state match for " + ss.getName() +" [" + name + "]");
							return ss;
					}
			}
			if (debug) System.out.println("Failed to find matching state for " + name);
			return null;
	}

	protected State findState(State state, String name)
	{
			for (State ss : state.getState())
			{
					if (ss.getName().equalsIgnoreCase(name))
					{
							if (debug) System.out.println("Found  state match for " + ss.getName() +" [" + name + "]");
							return ss;
					}
			}
			if (debug) System.out.println("Failed to find matching state for " + name);
			return null;
	}
	


	

}