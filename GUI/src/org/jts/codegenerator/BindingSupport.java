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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jts.jsidl.binding.Action;
import org.jts.jsidl.binding.Argument;
import org.jts.jsidl.binding.ClientOf;
import org.jts.jsidl.binding.DeclaredEventDef;
import org.jts.jsidl.binding.DeclaredMessageDef;
import org.jts.jsidl.binding.DefaultState;
import org.jts.jsidl.binding.EndState;
import org.jts.jsidl.binding.Entry;
import org.jts.jsidl.binding.EventDef;
import org.jts.jsidl.binding.Exit;
import org.jts.jsidl.binding.Guard;
import org.jts.jsidl.binding.InheritsFrom;
import org.jts.jsidl.binding.MessageDef;
import org.jts.jsidl.binding.Parameter;
import org.jts.jsidl.binding.Pop;
import org.jts.jsidl.binding.Push;
import org.jts.jsidl.binding.SendAction;
import org.jts.jsidl.binding.ServiceDef;
import org.jts.jsidl.binding.ServiceSet;
import org.jts.jsidl.binding.Simple;
import org.jts.jsidl.binding.Start;
import org.jts.jsidl.binding.State;
import org.jts.jsidl.binding.StateMachine;
import org.jts.jsidl.binding.Transition;

/**
 * Provides static functionality for comparison and sorting of JAXB generated objects.
 * @author JFK
 *
 */
public class BindingSupport
{
	/**
	 * Gets all of the default states defined in the list of states.
	 * @param states
	 * @return
	 */
	public static List<DefaultState> getDefaultStates(List<State> states)
	{
		if(states != null)
		{
			List<DefaultState> defStates = new ArrayList<DefaultState>();
			
			for(State state : states)
			{
				DefaultState def = state.getDefaultState();
				if(def != null)
				{
					defStates.add(def);
				}
				else
				{
					defStates.addAll(getDefaultStates(state.getState()));
				}
			}
			
			return defStates;
		}
		
		return null;
	}
	
	/**
	 * Compares nested default states for transitions common to all states.
	 * @param defStates
	 * @return A list of common transitions extracted from the default states.
	 */
	public static DefaultState getCommonDefaultTransitions(List<DefaultState> defStates)
	{
		// allocate the holder list
		DefaultState retval = new DefaultState();
		List<Transition> common = retval.getTransition();
		
		// iterate through the default states
		int size = defStates.size();
		for(int i = 0; i < size - 1; i++)
		{
			for(int j = i+1; j < size; j++)
			{
				List<Transition> ti = defStates.get(i).getTransition();
				List<Transition> tj = defStates.get(j).getTransition();

				// find and store the common transitions.
				for(Transition trans : getCommonTransitions(ti, tj))
				{
					if(!common.contains(trans))
					{
						common.add(trans);
					}
				}
			}
		}
		
		return retval;
	}
	
	/**
	 * Finds identical transitions in two lists and removes them from the lists.
	 * @param t1
	 * @param t2
	 * @return A list of identical transitions.
	 */
	public static List<Transition> getCommonTransitions(List<Transition> t1, List<Transition> t2)
	{
		ArrayList<Transition> common = new ArrayList<Transition>();
		ArrayList<Transition> secondary = new ArrayList<Transition>();
		
		// Find common transitions.
		for(int i = 0; i < t1.size(); i++)
		{
			for(int j = 0; j < t2.size(); j++)
			{
				Transition trans1 = t1.get(i);
				Transition trans2 = t2.get(j);
				
				if(compareTransitions(trans1, trans2))
				{
					// Store data.
					common.add(trans1);
					secondary.add(trans2);
				}
			}
		}
		
		// Remove from lists.
		t1.removeAll(common);
		t2.removeAll(secondary);
		
		return common;
	}
	
	/**
	 * Merges the non-common transitions from the base list into the target list.
	 * The common transitions are determined by the 'compareTransitions()' method.
	 * @param target
	 * @param base
	 */
	public static void mergeTransitionLists(List<Transition> target, List<Transition> base)
	{
		if(target != null && base != null)
		{
			for(Transition b : base)
			{
				boolean exists = false;
				for(Transition t : target)
				{
					if(compareTransitions(t, b))
					{
						exists = true;
						break;
					}
				}
				if(!exists)
					target.add(b);
			}
		}
	}
	
	public static boolean compareTransitionLists(List<Transition> tL1, List<Transition> tL2)
	{
		if(tL1 != null && tL2 != null)
		{
			for(Transition t1 : tL1)
			{
				for(Transition t2 : tL2)
					if(!compareTransitions(t1, t2))
						return false;
			}
			
			return true;
		}
		else if(tL1 == null && tL2 == null)
			return true;
		
		return false;
	}
	
	/**
	 * Determines if two Transitions are equivalent.
	 * @param t1
	 * @param t2
	 * @return
	 */
	public static boolean compareTransitions(Transition t1, Transition t2)
	{
		// Compare names.
		//System.out.println("names");
		if(!t1.getName().equals(t2.getName()))
		{
			return false;
		}
		
//		// Compare guards.
//		if(!compareGuards(t1.getGuard(), t2.getGuard()))
//		{
//			return false;
//		}
//			
//		// Compare simple/push/pop.
//		//System.out.println("simple/push/pop");
//		switch(compareSimples(t1.getSimple(), t2.getSimple()))
//		{
//		case -1:
//			return false;
//		case 0:
//			//System.out.println("push/pop");
//			switch(comparePushes(t1.getPush(), t2.getPush()))
//			{
//			case -1:
//				return false;
//			case 0:
//				//System.out.println("pop");
//				switch(comparePops(t1.getPop(), t2.getPop()))
//				{
//				case -1:
//				case 0:
//					return false;
//				case 1:
//					break;
//				}
//			case 1:
//				break;
//			}
//		case 1:
//			break;
//		}
//
//		// Compare parameter lists.
//		//System.out.println("parameters");
//		List<Parameter> params1 = t1.getParameter();
//		List<Parameter> params2 = t2.getParameter();
//		if(params1.size() == params2.size())
//		{
//			for(int i = 0; i < params1.size(); i++)
//			{
//				if(!compareParameters(params1.get(i), params2.get(i)))
//				{
//					break;
//				}
//			}
//		}
//			
//		// Compare actions.
//		//System.out.println("actions");
//		List<Object> a1 = t1.getActionOrSendAction();
//		List<Object> a2 = t2.getActionOrSendAction();
//			
//		if(a1.size() == a2.size())
//		{
//			for(int i = 0; i < a1.size(); i++)
//				if(!compareActions(a1.get(i), a2.get(i)))
//					return false;
//		}
		
		return true;
	}
	
	/**
	 * Determines if two Parameters are equivalent.
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static boolean compareParameters(Parameter p1, Parameter p2)
	{
		return p1.getType().equals(p2.getType()) && p1.getValue().equals(p2.getValue());
	}
	
	public static boolean compareActionLists(List<Object> aL1, List<Object> aL2)
	{
		if(aL1 != null && aL2 != null)
		{
			for(Object a1 : aL1)
			{
				for(Object a2 : aL2)
					if(!compareActions(a1, a2))
						return false;
			}
			
			return true;
		}
		else if(aL1 == null && aL2 == null)
			return true;
		
		return false;
	}
	
	/**
	 * Determines if two Actions are equivalent.
	 * Handles both Action & SendAction.
	 * @param a1
	 * @param a2
	 * @return
	 */
	public static boolean compareActions(Object a1, Object a2)
	{
		if(a1 instanceof Action && a2 instanceof Action)
		{
			Action act1 = (Action)a1;
			Action act2 = (Action)a2;
			if(act1.getName().equals(act2.getName()))
			{
				return compareArguments(act1.getArgument(), act2.getArgument());
			}
		}
		else if(a1 instanceof SendAction && a2 instanceof SendAction)
		{
			SendAction act1 = (SendAction)a1;
			SendAction act2 = (SendAction)a2;
			if(act1.getName().equals(act2.getName()))
			{
				return compareArguments(act1.getArgument(), act2.getArgument());
			}
		}
		
		return false;
	}
	
	/**
	 * Determines if two Simples are equivalent.
	 * @param s1
	 * @param s2
	 * @return 0: not equal; 1: equal; -1: one is null, the other is not
	 */
	public static int compareSimples(Simple s1, Simple s2)
	{
		if(s1 != null && s2 != null)
		{
			return compareEndStates(s1.getEndState(), s2.getEndState()) ? 1 : 0;
		}
		else if(s1 != null || s2 != null)
		{
			return -1;
		}
		
		return 0;
	}
	
	/**
	 * Determines if two EndStates are equivalent.
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean compareEndStates(EndState s1, EndState s2)
	{
		if(s1 != null && s2 != null)
		{
			return s1.getState().equals(s2.getState());
		}
		else if(s1 != null || s2 != null)
		{
			return false;
		}

		return true;
	}
	
	/**
	 * Determines if two Pushes are equivalent.
	 * @param s1
	 * @param s2
	 * @return 0: not equal; 1: equal; -1: one is null, the other is not
	 */
	public static int comparePushes(Push s1, Push s2)
	{
		if(s1 != null && s2 != null)
		{
			if(compareEndStates(s1.getEndState(), s2.getEndState()))
			{
				switch(compareSimples(s1.getSimple(), s2.getSimple()))
				{
				case -1:
					return 0;
				case 0:
				case 1:
					return 1;
				}
			}
		}
		else if(s1 != null || s2 != null)
		{
			return -1;
		}
		
		return 0;
	}
	
	/**
	 * Determines if two Pops are equivalent.
	 * @param p1
	 * @param p2
	 * @return 0: not equal; 1: equal; -1: one is null, the other is not
	 */
	public static int comparePops(Pop p1, Pop p2)
	{
		if(p1 != null && p2 != null)
		{
			if(p1.getTransition().equals(p2.getTransition()) && compareArguments(p1.getArgument(), p2.getArgument()))
			{
				return 1;
			}
		}
		else if(p1 != null || p2 != null)
		{
			return -1;
		}
		
		return 0;
	}
	
	/**
	 * Determines if two Arguments are equivalent.
	 * @param args1
	 * @param args2
	 * @return
	 */
	public static boolean compareArguments(List<Argument> args1, List<Argument> args2)
	{
		if(args1.size() == args2.size())
		{
			for(int i = 0; i < args1.size(); i++)
			{
				if(!args1.get(i).getValue().equals(args2.get(i).getValue()))
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Determines if two Guards are equivalent.
	 * @param g1
	 * @param g2
	 * @return
	 */
	public static boolean compareGuards(Guard g1, Guard g2)
	{
		if(g1 != null && g2 != null)
		{
			return g1.getCondition().equals(g2.getCondition());
		}
		else if(g1 != null || g2 != null)
		{
			return false;
		}
		
		return true;
	}

	/**
	 * Compares an inheritance specification with a service definition to determine a match.
	 * @param baseService Identifies which service to inherit from.
	 * @param serviceDef A service definition to compare against.
	 * @return 'true' if the information matches.
	 */
	public static boolean compareInheritance(InheritsFrom baseService, ServiceDef serviceDef)
	{
		if(baseService != null && serviceDef != null)
		{
			return baseService.getName().equals(serviceDef.getName())
					&& baseService.getId().equals(serviceDef.getId())
					&& baseService.getVersion().equals(serviceDef.getVersion());
		}
		
		return false;
	}
	
	/**
	 * Compares an inheritance specification with a number of service definitions.
	 * @param baseService Identifies which service to inherit from.
	 * @param serviceDefs A collection of service definitions to compare against.
	 * @return The first matching service definition from the collection. 'null' if no match is found.
	 */
	public static ServiceDef retrieveInheritance(InheritsFrom baseService, Collection<ServiceDef> serviceDefs)
	{
		if(baseService != null && serviceDefs != null)
		{
			for(ServiceDef serviceDef : serviceDefs)
			{
				if(compareInheritance(baseService, serviceDef))
					return serviceDef;
			}
		}
		
		return null;
	}
	
	public static ServiceDef retrieveInheritanceSet(InheritsFrom baseService, Collection<ServiceSet> serviceSets)
	{
		if(baseService != null && serviceSets != null)
		{
			for(ServiceSet serviceSet : serviceSets)
			{
				ServiceDef serviceDef = retrieveInheritance(baseService, serviceSet.getServiceDef());
				if(serviceDef != null)
					return serviceDef;
			}
		}
		
		return null;
	}
	
	/**
	 * Compares a client of spec with a service def to determine a match.
	 * @param baseService
	 * @param serviceDef
	 * @return
	 */
	public static boolean compareClientOf(ClientOf baseService, ServiceDef serviceDef)
	{
		if(baseService != null && serviceDef != null)
		{
			return baseService.getName().equals(serviceDef.getName())
					&& baseService.getId().equals(serviceDef.getId())
					&& baseService.getVersion().equals(serviceDef.getVersion());
		}
		
		return false;
	}
	
	/**
	 * Compares a client of spec with a collection of service defs to find a match.
	 * @param baseService
	 * @param serviceDefs
	 * @return
	 */
	public static ServiceDef retrieveClientOf(ClientOf baseService, Collection<ServiceDef> serviceDefs)
	{
		if(baseService != null && serviceDefs != null)
		{
			for(ServiceDef serviceDef : serviceDefs)
			{
				if(compareClientOf(baseService, serviceDef))
					return serviceDef;
			}
		}
		
		return null;
	}

	public static ServiceDef retrieveClientOfSet(ClientOf baseService, Collection<ServiceSet> serviceSets)
	{
		if(baseService != null && serviceSets != null)
		{
			for(ServiceSet serviceSet : serviceSets)
			{
				ServiceDef serviceDef = retrieveClientOf(baseService, serviceSet.getServiceDef());
				if(serviceDef != null)
					return serviceDef;
			}
		}
		
		return null;
	}

	public static boolean compareMessageDefs(Object def1, Object def2)
	{
		if(def1 instanceof MessageDef && def2 instanceof MessageDef)
			return compareMessageDefs((MessageDef)def1, (MessageDef)def2);
		else if(def1 instanceof DeclaredMessageDef && def2 instanceof DeclaredMessageDef)
			return compareDeclaredMessageDefs((DeclaredMessageDef)def1, (DeclaredMessageDef)def2);
		
		return false;
	}
	
	public static boolean compareMessageDefs(MessageDef def1, MessageDef def2)
	{
		if(def1 != null && def2 != null)
		{
			byte[] id1 = def1.getMessageId();
			byte[] id2 = def2.getMessageId();
			
			if(id1.length == id2.length)
			{
				for(int i = 0; i < id1.length; i++)
					if(id1[i] != id2[i])
						return false;
			}
			
			return true;
		}
		
		return false;
	}
	
	public static boolean compareDeclaredMessageDefs(DeclaredMessageDef def1, DeclaredMessageDef def2)
	{
		// TODO: finish this
		return false;
	}

	/**
	 * Compares two starts to determine if they are equal.
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean compareStarts(Start s1, Start s2)
	{
		return (s1.getStateMachineName().equals(s2.getStateMachineName()))
				&& (s1.getStateName().equals(s2.getStateName()));
	}

	public static boolean compareStateLists(List<State> sL1, List<State> sL2)
	{
		if(sL1 != null && sL2 != null)
		{
			for(State s1 : sL1)
			{
				for(State s2 : sL2)
					if(!compareStates(s1, s2))
						return false;
			}
			
			return true;
		}
		else if(sL1 == null && sL2 == null)
			return true;
		
		return false;
	}
	
	/**
	 * Compares two states to see if they are equivalent.
	 * NOTE: This does NOT compare nested or initial states.
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean compareStates(State s1, State s2)
	{
		if(s1 != null && s2 != null)
		{
			// compare name
			if(!s1.getName().equals(s2.getName()))
				return false;

			// compare entry
			if(!compareEntries(s1.getEntry(), s2.getEntry()))
				return false;
			
			// compare exit
			if(!compareExits(s1.getExit(), s2.getExit()))
				return false;

			// compare transitions
			if(!compareTransitionLists(s1.getTransition(), s2.getTransition()))
				return false;

			// compare initial state
			//if(!s1.getInitialState().equals(s2.getInitialState()))
				//return false;

			// compare states
			//if(!compareStateLists(s1.getState(), s2.getState()))
				//return false;
			
			// everything matches
			return true;
		}
		else if(s1 == null && s2 == null)
			return true;
		
		return false;
	}
	
	public static boolean compareEntries(Entry e1, Entry e2)
	{
		if(e1 != null && e2 != null)
		{
			return compareActionLists(e1.getActionOrSendAction(), e2.getActionOrSendAction());
		}
		else if(e1 == null && e2 == null)
			return true;
		
		return false;
	}
	
	public static boolean compareExits(Exit e1, Exit e2)
	{
		if(e1 != null && e2 != null)
		{
			return compareActionLists(e1.getActionOrSendAction(), e2.getActionOrSendAction());
		}
		else if(e1 == null && e2 == null)
			return true;
		
		return false;
	}

	public static boolean compareEvents (Object o1, Object o2)
	{
		if(o1 instanceof EventDef && o2 instanceof EventDef)
		{
			return ((EventDef)o1).getName().equals(((EventDef)o2).getName());
		}
		else if(o1 instanceof DeclaredEventDef && o2 instanceof DeclaredEventDef)
		{
			return ((DeclaredEventDef)o1).getName().equals(((DeclaredEventDef)o2).getName());
		}

		return false;
	}
	
	/**
	 * Attaches the specified namespace to the state machine name.
	 * @param machine
	 * @param namespace
	 * @return
	 */
	public static String pushNamespace(StateMachine machine, String namespace)
	{
		namespace = namespace.substring(0, 1).toLowerCase() + namespace.substring(1) + ".";
		return namespace + machine.getName();
	}
	
	public static String pushNamespace(String target, String namespace)
	{
		namespace = namespace.substring(0, 1).toLowerCase() + namespace.substring(1) + ".";
		return namespace + target;
	}
	
	/**
	 * Adds the name of the service definition to all of its state machines.
	 * This is to address the namespace issues in referencing.
	 * @param def The service definition to process.
	 */
	public static void pushNamespace(ServiceDef def)
	{
		String name = def.getName();
		name = name.substring(0, 1).toLowerCase() + name.substring(1) + ".";
		for(StateMachine machine : def.getProtocolBehavior().getStateMachine())
		{
			machine.setName(name + machine.getName());
		}
	}
}
