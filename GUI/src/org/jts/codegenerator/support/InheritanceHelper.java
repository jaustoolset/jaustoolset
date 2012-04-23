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

/*
 * InnheritanceHelper.java
 * 
 */
package org.jts.codegenerator.support;

import org.jts.jsidl.binding.*;
import java.util.Vector;
import java.util.ArrayList;
import org.jts.codegenerator.ServiceDefGenerator;
import org.jts.codegenerator.CodeLines;



public class InheritanceHelper {
      /**
	 * A helper class to parse inheritance, rifle through service sets, 
	 * and other assorted mechanics.
	 * @param obj
	 */
	 
    //
    // shorten the state machine name so that we can keep the generated class names manageable
    // 
    public static String shortenStateMachineName(org.jts.jsidl.binding.ServiceDef sd, org.jts.jsidl.binding.StateMachine sm)
    {
        String name = sd.getName() + "_" + sm.getName().replace(".", "_");

        if(name.contains("_"))
        {
            String start = name.substring(0, name.indexOf("_"));
            String end = name.substring(name.lastIndexOf("_")+1);
            name = start + "_" + end;
        }

        return name;
    }
    
    //
    // Get the state object for the given state name
    // 
    public static State findStateFromName(String name, StateMachine sm)
    {   
        State ret = null;
        for (State state : sm.getState())
        {
            ret = findStateFromName( name, state );
            if (ret != null) return ret;
        }
        
        return null;
    }
    
    //
    // Get the state object for the given state name
    // 
    public static State findStateFromName(String name, State state)
    {   
        State ret = null;
        
        // If the name matches the current state, return it
        if (name.equals(state.getName())) return state;
                
		// This gets a little awkward, since this function is called for both
		// "normal" state names and full resolved, i.e.  parent1.parent2.state1.substate1 
		// names.  In the "normal" case, we have to strip off the name of the parent
		// before we search its substates.        
        //String strippedName = name.replaceFirst( state.getName(), "" );
        //if (strippedName.startsWith(".")) strippedName = strippedName.replaceFirst(".", "");
        String strippedName = name;
        
		// Now search substates for the "stripped" name
        for (State substate : state.getState())
        {
			ret = findStateFromName( strippedName, substate );
			if (ret != null) return ret;
	    }
	    
	    return ret;
    }	    	 
	
	//
	// Returns a reference to the specified service def.  If the def is not part of the
	// service set, null is returned.
	//
	public static ServiceDef findServiceDef(ServiceSet sSet, String id, String version)
	{
		// Find the specified service in the service set
		for (ServiceDef ref : sSet.getServiceDef())
		{
			if (ref.getId().equals(id) && ref.getVersion().equals(version))
			{
				return ref;
			}
		}
		return null;
	}
	
	//
	// Returns a reference to the parent FSM, if it exists.  If no parent exists,
	// null is returned.
	//
	public static StateMachine findParentFSM(ServiceSet sSet, ServiceDef sDef, StateMachine sm)
	{
	    // The FSM name may have extra qualifiers.  Strip them.
	    String shortName = sm.getName().substring( sm.getName().lastIndexOf("_")+1 );
	    
	    // Now get the parent service def
	    try 
	    {
			// Get the parent.  If it exists, call this function recursively.
			ServiceDef parent = org.jts.codegenerator.support.InheritanceHelper.findServiceDef(
			                                   sSet, sDef.getReferences().getInheritsFrom().getId(),
											   sDef.getReferences().getInheritsFrom().getVersion());
 
			// Find the matching state machine in the service def
			for (StateMachine parentSM : parent.getProtocolBehavior().getStateMachine())
			{
				if (shortName.equals( parentSM.getName().substring(parentSM.getName().lastIndexOf("_")+1) ))
				{
					return parentSM;
				}
			}
		}
		catch (Exception e){}
		
		return null;
	}
	
	//
	// Recursive function to get a parameter list of all parents used in the service constructor
	//
	public static void getParentServiceList(CodeLines.CodeType codeType, ServiceSet sSet, ServiceDef sDef, Vector<Reference> parentList)
	{
		try 
	    {
			// Get the parent.  If it exists, call this function recursively.
			ServiceDef parent = org.jts.codegenerator.support.InheritanceHelper.findServiceDef(
			                                   sSet, sDef.getReferences().getInheritsFrom().getId(),
											   sDef.getReferences().getInheritsFrom().getVersion());
			if (parent != null) 
				getParentServiceList(codeType, sSet, parent, parentList);

			 // We need to get the service name and namespace.  Easiest way to do that is through the service def generator
			 ServiceDefGenerator sdGen = new ServiceDefGenerator(codeType, parent, sSet);
			 
			 // Add this reference to the list
			 Reference ref = new Reference();
			 ref.namespace = sdGen.getNamespace();
			 ref.name = sdGen.getServiceName();
			 parentList.add( ref );
         }
		 catch (Exception e) {}
	}
	
	//
	// Recursive function to get a parameter list of all parents used in the service constructor
	//
	public static void getParentFSMList(CodeLines.CodeType codeType, String fsmName, ServiceSet sSet, ServiceDef sDef, Vector<Reference> parentFSMList)
	{
	    String shortName = fsmName.substring(fsmName.lastIndexOf("_")+1);
		try 
	    {
			// Get the parent.  If it exists, call this function recursively.
			ServiceDef parent = org.jts.codegenerator.support.InheritanceHelper.findServiceDef(
			                                   sSet, sDef.getReferences().getInheritsFrom().getId(),
											   sDef.getReferences().getInheritsFrom().getVersion());
			if (parent != null) 
				getParentFSMList(codeType, fsmName, sSet, parent, parentFSMList);
				
			 // We need to get the service name and namespace.  Easiest way to do that is through the service def generator
			 ServiceDefGenerator sdGen = new ServiceDefGenerator(codeType, parent, sSet);				

			// Loop through all FSMs
			for (StateMachine fsm : parent.getProtocolBehavior().getStateMachine())
			{
			    if (shortName.equals( fsm.getName().substring(fsm.getName().lastIndexOf("_")+1) ))
			    {
			        // Add this FSM to the parent list
			        Reference ref = new Reference();
			        ref.namespace = sdGen.getNamespace();
			        ref.owner = sdGen.getServiceName();
			        ref.name = fsm.getName();
			        parentFSMList.add(ref);
			    }
			}
         }
		 catch (Exception e) {}
	}
		
	public static void getUniqueStateList(StateMachine sm, ServiceSet sSet, ServiceDef sDef, Vector<Reference> stateList)
	{
	    // Get a full list of all states and substates in the FSM
	    // This call is recursive, so it'll add parent state all the way up the chain
	    getFullStateList( sm, sSet, sDef, stateList );
		
		// Get a list of all parent states
		Vector<Reference> parentStates = new Vector<Reference>();
		try
	    {
			// Get the parent and parent FSM, if they exist
			ServiceDef parent = org.jts.codegenerator.support.InheritanceHelper.findServiceDef(
			                                   sSet, sDef.getReferences().getInheritsFrom().getId(),
											   sDef.getReferences().getInheritsFrom().getVersion());
		    StateMachine parentFSM = org.jts.codegenerator.support.InheritanceHelper.findParentFSM(
												   sSet, sDef, sm);
												   
			// Add all states from the parent FSM.  This call is recursive, so it'll add
			// the parent's parents and all the way up the chain.
			getFullStateList(parentFSM, sSet, parent, parentStates);
		}
		catch (Exception e){}
	    
	    // Remove any states defined by the parents
	    for (Reference ref : parentStates)
	    {
	        while (stateList.remove(ref));
	    }
	}
	
	protected static String combine(String s1, String s2)
	{
	    if (s1.equals("")) return s2;
	    return s2;//s1 + "." + s2;
	}
	
	public static void getFullStateList(StateMachine sm, ServiceSet sSet, ServiceDef sDef, Vector<Reference> stateList)
	{
	    // Get a full list of all states and substates in the FSM
	    for ( State state : sm.getState() )
	        getSubStateList( state, stateList, "" );
		
		// Get parent states, too
		try
		{
			// Get the parent and parent FSM
			ServiceDef parent = org.jts.codegenerator.support.InheritanceHelper.findServiceDef(
												   sSet, sDef.getReferences().getInheritsFrom().getId(),
												   sDef.getReferences().getInheritsFrom().getVersion());
			StateMachine parentFSM = org.jts.codegenerator.support.InheritanceHelper.findParentFSM(
												   sSet, sDef, sm);
				                                   
			// Recursively call this function
			getFullStateList( parentFSM, sSet, parent, stateList );
		}
		catch (Exception e){}
	}
	
	public static void getSubStateList(State state, Vector<Reference> stateList, String prefix)
	{
	    for ( State substate : state.getState() )
	        getSubStateList( substate, stateList, combine(prefix, state.getName()) );
	        
	    Reference ref = new Reference();
	    ref.name = combine(prefix, state.getName());
	    if (!stateList.contains(ref)) stateList.add( ref );
	}	
	
	public static ServiceDef getTopParent( ServiceDef sDef, ServiceSet sSet )
	{
	    ServiceDef ret = sDef;
	    
	    try
		{
			// Get the parent
			ServiceDef parent = org.jts.codegenerator.support.InheritanceHelper.findServiceDef(
												   sSet, sDef.getReferences().getInheritsFrom().getId(),
												   sDef.getReferences().getInheritsFrom().getVersion());
				                                   
			// Recursively call this function if a parent exists
			if (parent != null)
				ret = getTopParent( parent, sSet );
		}
		catch (Exception e)
		{
			// No parent means this is the top
		}
		
		return ret;
	}
	
	public static String findFullInitialStateName( State state, StateMachine sm )
	{
	    String ret = state.getName();
	    
	    // If this state has an initial state specification, find it.
	    if ((state.getInitialState() != null) && (state.getInitialState().length() > 0))
	    {
			State substate = findStateFromName( state.getInitialState(), sm );
			if (substate != null) ret = findFullInitialStateName( substate, sm );
		}
	    return ret;
	}
	
	public static State findParentState( State stateToFind, StateMachine sm )
	{
	   for ( State stateToSearch : sm.getState() )
	   {
	       State temp = findParentState( stateToFind, stateToSearch );
	       if (temp != null) return temp;
	   }
	   
	   return null;
	}
	
	public static State findParentState( State stateToFind, State stateToSearch )
	{
	   for ( State substate : stateToSearch.getState() )
	   {
	       State temp = findParentState( stateToFind, substate );
	       if (temp != null) return temp;
	   }
	   
	   if (stateToFind.getName().startsWith(stateToSearch.getName()))
	      return stateToSearch;
	   
	   return null;
	}

	public static String findGuardInParentFSM(String method, StateMachine sm, ServiceDef sd, ServiceSet ss, CodeLines.CodeType codeType, boolean recursed)
	{
	    String ret = null;

		// Get the parent.  If it exists, call this function recursively.
		try
		{
			ServiceDef parent = org.jts.codegenerator.support.InheritanceHelper.findServiceDef(
			                                   ss, sd.getReferences().getInheritsFrom().getId(),
											   sd.getReferences().getInheritsFrom().getVersion());
			StateMachine parentFSM = findParentFSM(ss, sd, sm);

			if ((parent != null) && (parentFSM != null)) 
			    ret = findGuardInParentFSM(method, parentFSM, parent, ss, codeType, true);
		}
		catch (Exception e){}

	    // If we still haven't found a match in our parent FSMs, check this FSM 
		// (but only if we're recursed!!! THe first level state should NOT return a match!!!)
		if ((ret == null) && recursed)
		{
			// Get a list of all guards in the FSM.  If our guard is in the list, return the FSM
			ArrayList<String> guardList = new ArrayList<String>();
			org.jts.codegenerator.protocolBehavior.Guard.getGuards(sm, guardList, codeType);
			if (guardList.contains( method.replace("::", ".")))
			{
				// We need to get the service name and namespace.  
				//Easiest way to do that is through the service def generator
				ServiceDefGenerator sdGen = new ServiceDefGenerator(codeType, sd, ss);
			    return sdGen.getNamespace() + ":" + sdGen.getServiceName() + ":" + sm.getName();
			}
		}

		// return whatever we've found, or null if no match exists
		return ret;
	}

	public static void generateGuardAutoImplementation( StringBuffer implementation, String method, String parent, CodeLines.CodeType codeType )
	{
		if ((parent != null) && (codeType == CodeLines.CodeType.C_PLUS_PLUS))
		{
			implementation.append("\t//// By default, inherited guards call the parent function.").append(System.getProperty("line.separator"));
			implementation.append("\t//// This can be replaced or modified as needed.").append(System.getProperty("line.separator"));

			// Expected parent string of the form namespace:service name:fsm name
			String[] parsed = parent.split(":");
			
			// Get the method call string.
			String call = getGuardCall( implementation, method, parent, codeType );
		    implementation.append("\treturn p").append(parsed[2]).append("->").append(call).append(System.getProperty("line.separator"));
		}
		else if (parent != null)
		{
			implementation.append("\t//// By default, inherited guards call the parent function.").append(System.getProperty("line.separator"));
			implementation.append("\t//// This can be replaced or modified as needed.").append(System.getProperty("line.separator"));

			// Expected parent string of the form namespace:service name:fsm name
			String[] parsed = parent.split(":");
			
			// Get the method call string.
			String call = getGuardCall( implementation, method, parent, codeType );
		    implementation.append("\treturn p").append(parsed[2]).append(".").append(call).append(System.getProperty("line.separator"));
		}
		else
		{
	        implementation.append("\t/// Insert User Code HERE").append(System.getProperty("line.separator"));
            implementation.append("\treturn false;").append(System.getProperty("line.separator"));
		}
	}
	
	public static String getGuardCall( StringBuffer implementation, String method, String parent, CodeLines.CodeType codeType )
	{
	    boolean firstParam = true;
		boolean firstWarn = true;
		String ret = null;

		// Add the function name to the output buffer, along with an open parenthesis
	    ret = method.substring(0, method.indexOf("(")) + "(";

		// Break the parameter list into type/value pairs based on comma separated values
		String[] paramList = method.substring(method.indexOf("(")+1, method.indexOf(")")).split(",");

		// For each type/value pair, split into type and value based on spaces
		for ( String param : paramList )
		{
			String[] pair = param.split(" ");
			if (pair.length < 2) continue;

			// Add a comma to separate parameters
			if (!firstParam) ret += ", "; firstParam = false;

			if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
			{
				// If this is not a simple type (or a send/receive type), we
				// need to cast to the target's namespace
				if (!isParamTypeSimpleOrSendReceive( pair[0], codeType ))
				{
					// Expected parent string of the form namespace:service name:fsm name.
					// Use the namespace to cast the message/internal event type
					String[] parsed = parent.split(":");
					ret += "*((" + parsed[0] + "::" + pair[0] + "*) &" + pair[1] +")";
				}
				else // Otherwise, we can just use the argument directly.
				    ret += pair[1];
			}
			else if (codeType == CodeLines.CodeType.C_SHARP)
			{
				// If this is not a simple type (or a send/receive type), we
				// need to cast to the target's namespace
				if (!isParamTypeSimpleOrSendReceive( pair[0], codeType ))
				{
				    // If this is the first casted parameter, spit out
					// the warning code and define support variables.
					if (firstWarn) 
					{
						addUserWarning( implementation );
						implementation.append("\tuint bufsize; byte[] buffer; int pos;").append(System.getProperty("line.separator"));
						implementation.append(System.getProperty("line.separator"));
						firstWarn = false;
					}

					// Expected parent string of the form namespace:service name:fsm name.
					// Use the namespace to cast the message/internal event type
					String[] parsed = parent.split(":");

					// Serialize the old type, and deserialize the new type
					implementation.append("\tbufsize = (uint) " + pair[1] + ".getSize();").append(System.getProperty("line.separator"));
					implementation.append("\tbuffer = new byte[bufsize];").append(System.getProperty("line.separator"));
					implementation.append("\tpos = 0; " + pair[1] + ".encode(buffer, pos);").append(System.getProperty("line.separator"));
					implementation.append("\t" + parsed[0] + "." + pair[0] + " casted_" + pair[1]);
					implementation.append(" = new " + parsed[0] + "." + pair[0] + "();").append(System.getProperty("line.separator"));
					implementation.append("\tpos = 0; casted_" + pair[1] + ".decode(buffer, pos);").append(System.getProperty("line.separator"));
					implementation.append(System.getProperty("line.separator"));

					// Now we add the casted type as an argument to the parent function call
					ret += "casted_" + pair[1];
				}
				else // Otherwise, we can just use the argument directly.
				    ret += pair[1];
			}
			else if (codeType == CodeLines.CodeType.JAVA)
			{
				// If this is not a simple type (or a send/receive type), we
				// need to cast to the target's namespace
				if (!isParamTypeSimpleOrSendReceive( pair[0], codeType ))
				{
				    // If this is the first casted parameter, spit out
					// the warning code and define support variables.
					if (firstWarn) 
					{
						addUserWarning( implementation );
						implementation.append("\tlong bufsize; ByteBuffer buffer; int pos;").append(System.getProperty("line.separator"));
						implementation.append(System.getProperty("line.separator"));
						firstWarn = false;
					}

					// Expected parent string of the form namespace:service name:fsm name.
					// Use the namespace to cast the message/internal event type
					String[] parsed = parent.split(":");

					// Serialize the old type, and deserialize the new type
					implementation.append("\tbufsize = " + pair[1] + ".getSize();").append(System.getProperty("line.separator"));
					implementation.append("\tbuffer = ByteBuffer.allocate((int) bufsize);").append(System.getProperty("line.separator"));
					implementation.append("\tbuffer.order(ByteOrder.LITTLE_ENDIAN);").append(System.getProperty("line.separator"));
					implementation.append("\tbuffer.clear();").append(System.getProperty("line.separator"));
					implementation.append("\tpos = 0; " + pair[1] + ".encode(buffer, pos);").append(System.getProperty("line.separator"));
					implementation.append("\tsrc." + parsed[0] + ".Messages." + pair[0] + " casted_" + pair[1]);
					implementation.append(" = new src." + parsed[0] + ".Messages." + pair[0] + "();").append(System.getProperty("line.separator"));
					implementation.append("\tpos = 0; casted_" + pair[1] + ".decode(buffer, pos);").append(System.getProperty("line.separator"));
					implementation.append(System.getProperty("line.separator"));

					// Now we add the casted type as an argument to the parent function call
					ret += "casted_" + pair[1];
				}
				else // Otherwise, we can just use the argument directly.
				    ret += pair[1];
			}
		}

		// Add a closing parenthesis
		ret += " );";

		return ret;
	}       

	protected static boolean isParamTypeSimpleOrSendReceive( String type,  CodeLines.CodeType codeType)
	{
	    if (codeType == CodeLines.CodeType.C_PLUS_PLUS)
		{
			if ((type.compareTo("Send") == 0) || (type.compareTo("Receive") == 0) ||
			    type.startsWith("Send::") || type.startsWith("Receive::") ||
				(type.compareTo("std::string") == 0) || type.contains(" "))
				return true;
		}
		else // C# or Java
		{
			if ((type.compareTo("Send") == 0) || (type.compareTo("Receive") == 0) ||
			    type.startsWith("Send.") || type.startsWith("Receive.") ||
				(type.compareTo("String") == 0) || type.contains(" "))
				return true;
		}

		// Getting here means the type is not a simple type, nor send or receive
		return false;
	}

	protected static void addUserWarning( StringBuffer implementation)
	{
		implementation.append(System.getProperty("line.separator"));
		implementation.append("\t//").append(System.getProperty("line.separator"));
		implementation.append("\t// NOTE! Messages and internal events passed as arguments").append(System.getProperty("line.separator")); 
		implementation.append("\t// in guards must be cast to the correct (parent) type.  Even").append(System.getProperty("line.separator"));
		implementation.append("\t// though these types are exactly the same, they fall under different").append(System.getProperty("line.separator"));
		implementation.append("\t// namespaces.  Instead of simple casting, Java and C# requires us to").append(System.getProperty("line.separator"));
		implementation.append("\t// serialize the child type and deserialize as the parent type so").append(System.getProperty("line.separator"));
		implementation.append("\t// this code gets a little wonky.").append(System.getProperty("line.separator"));
		implementation.append("\t//").append(System.getProperty("line.separator"));
	}
}