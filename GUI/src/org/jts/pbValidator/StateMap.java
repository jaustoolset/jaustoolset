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

package org.jts.pbValidator;

import java.util.ArrayList;
import java.util.List;

public class StateMap
{
	private org.jts.jsidl.binding.State state;
	private org.jts.pbValidator.StateMap parent;
	private org.jts.jsidl.binding.StateMachine stateMachine;
	private List<org.jts.pbValidator.StateMap> substates;
	
	public StateMap( org.jts.jsidl.binding.StateMachine sm )
	{
		this.state = null;
		this.parent = null;
		this.stateMachine = sm;
		this.substates = new ArrayList<org.jts.pbValidator.StateMap>();

		if( sm != null && sm.getState() != null )
		{
			for( org.jts.jsidl.binding.State st : sm.getState() )
			{
				org.jts.pbValidator.StateMap newMap = new StateMap( st, this, sm ); 
				this.substates.add( newMap );
			}
		}
	}

	private StateMap( org.jts.jsidl.binding.State st, org.jts.pbValidator.StateMap pt, org.jts.jsidl.binding.StateMachine sm )
	{
		this.state = st;
		this.parent = pt;
		this.stateMachine = sm;
		this.substates = new ArrayList<org.jts.pbValidator.StateMap>();

		for( org.jts.jsidl.binding.State state : st.getState() )
		{
			org.jts.pbValidator.StateMap newMap = new StateMap( state, this, sm ); 
			this.substates.add( newMap );
		}
	}
	
	public org.jts.pbValidator.StateMap getParent()
	{
		return this.parent;
	}
	
	public org.jts.jsidl.binding.State getState()
	{
		return this.state;
	}

	public org.jts.jsidl.binding.StateMachine getStateMachine()
	{
		return this.stateMachine;
	}
	
	public org.jts.pbValidator.StateMap getRoot()
	{
		 org.jts.pbValidator.StateMap map = this;
		 
		 while( !map.isRoot() )
		 {
			 map = map.parent;
		 }
		 
		 return map;
	}

	public org.jts.pbValidator.StateMap getChild( String childStateName )
	{
		for( org.jts.pbValidator.StateMap child : this.substates )
		{
			if( child.getState() != null && child.getState().getName().compareTo( childStateName ) == 0 )
			{
				return child;
			}
		}
		return null;
	}
	
	public List<org.jts.pbValidator.StateMap> getChildren()
	{
		return this.substates;
	}
	
	public boolean isRoot()
	{
		return ( this.parent == null );
	}
	
	public boolean isSameStateMachine( org.jts.jsidl.binding.StateMachine sm )
	{
		if( this.stateMachine != null && sm != null )
		{
			String thisStateMachine = this.stateMachine.getName();
			String matchStateMachine = sm.getName();
			
			// state machine names must be the same 
			if( thisStateMachine.compareTo( matchStateMachine ) == 0 )
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isSameStateMachine( org.jts.pbValidator.StateMap mapToSearch )
	{
		if( this.stateMachine != null && mapToSearch.stateMachine != null )
		{
			String thisStateMachine = this.stateMachine.getName();
			String matchStateMachine = mapToSearch.stateMachine.getName();
			
			// state machine names must be the same 
			if( thisStateMachine.compareTo( matchStateMachine ) == 0 )
			{
				return true;
			}
		}
		
		return false;
	}
	
	public List<String> getNamesListCurrentToRoot()
	{
		List<String> stateList = new ArrayList<String>();

		org.jts.pbValidator.StateMap currentMap = this;
		while( currentMap.parent != null )
		{
			stateList.add( currentMap.state.getName() );
			currentMap = currentMap.parent;
		}
		
		return stateList;
	}
	
	public List<String> getNamesListRootToCurrent()
	{
		List<String> stateList = new ArrayList<String>();
		List<String> returnStateList = new ArrayList<String>();
		
		stateList = getNamesListCurrentToRoot();
		
		for( int i = stateList.size() - 1; i >= 0; i-- )
		{
			returnStateList.add( stateList.get( i ) );
		}
		
		return returnStateList;
	}
	
	private org.jts.pbValidator.StateMap findSubStateMatchingMap( String stateName )
	{
		for( org.jts.pbValidator.StateMap map : this.substates )
		{
			if( map.getState().getName().compareTo( stateName ) == 0 )
			{
				return map;
			}
		}
		
		return null;
	}
	
	/**
	 * Determines if the current statemap state is defined only in the derived service(current statemachine) or
	 * if was defined in a parent service originally
	 * @param parentServiceMap
	 * @return true if the state is derived(defined in the parent), 
	 * 		   false if it was not defined in the parent service(defined in current only)
	 */
	public boolean isCurrentStateDefinedInParent( org.jts.pbValidator.StateMap parentMap )
	{
		if( parentMap == null )
		{
			return false;
		}

		// get list of states from root to current state
		List<String> currentStateList = this.getNamesListRootToCurrent();
		
		// reset parent service map to root level
		org.jts.pbValidator.StateMap map = parentMap;
		
		while( map.parent != null )
		{
			map = map.parent;
		}
		
		// check each state name for match
		for( String st : currentStateList )
		{
			// try to find matching state
			map = map.findSubStateMatchingMap( st );
			
			// when no match was found, exit condition
			if( map == null )
			{
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * returns a list of matching states of the current state as represented in the parent service
	 * @param parentMap
	 * @return
	 */
	public List<String> getCurrentStateRepresentationInMap( org.jts.pbValidator.StateMap parentMap )
	{
		List<String> returnList = new ArrayList();
		
		if( parentMap == null )
		{
			return returnList;
		}
		
		// make sure parentMap is in root position
		while( parentMap.getParent() != null )
		{
			parentMap = parentMap.getParent();
		}
		
		// get list of states from root to current state
		List<String> currentStateList = this.getNamesListRootToCurrent();
		
		// check each state name for match
		for( String st : currentStateList )
		{
			parentMap = parentMap.findSubStateMatchingMap( st );
			
			if( parentMap != null )
			{
				returnList.add( st );
			}
			else
			{
				break;
			}
		}
		
		return returnList;
	}
	
	public org.jts.pbValidator.StateMap followInitialStateUntilLeaf()
	{
		org.jts.pbValidator.StateMap map = this;
		
		while( map.getChildren().size() != 0 )
		{
			map = map.getIntialState();
			
			if( map == null )
			{
				return null;
			}
		}

		return map;
	}
	
	public org.jts.pbValidator.StateMap getIntialState()
	{
		org.jts.jsidl.binding.State st = this.state;
		String initialStateName = st.getInitialState();
		
		// states don't have to have an initial state when there is only one sub state 
		if( initialStateName == null )
		{
			if( st.getState().size() == 0 )
			{
				return null;
			}
			
			initialStateName = st.getState().get( 0 ).getName();
		}

		for( org.jts.pbValidator.StateMap map : this.getChildren() )
		{
			if( map.getState().getName().compareTo( initialStateName ) == 0 )
			{
				return map;
			}
		}

		return null;
	}
	
	public boolean isMatchingTransitionNameInMapToRoot( org.jts.jsidl.binding.Transition tr )
	{
		if( tr == null )
		{
			return false;
		}
		
		org.jts.pbValidator.StateMap parentMap = this;
		
		while( parentMap != null )
		{
			if( parentMap.getState() != null && parentMap.getState().getTransition() != null )
			{
				for( org.jts.jsidl.binding.Transition transition : parentMap.getState().getTransition() )
				{
					if( transition.getName().compareTo( tr.getName() ) == 0 )
					{
						return true;
					}
				}
			}
			
			parentMap = parentMap.getParent();
		}
		
		return false;
	}

	/**
	 * finds a matching state in the state machine of the current map
	 * -expects fully qualified state names
	 * @param stateName
	 * @return
	 */
	public org.jts.pbValidator.StateMap getMatchingStateInMap( String stateName )
	{
		if( stateName == null )
		{
			return null;
		}

		// go to state machine level
		org.jts.pbValidator.StateMap map = this;

		while( map.state != null )
		{
			map = map.parent;
		}

		// we are now at the state machine level of the map
		// now, since we are guaranteed a fully qualified stateName input, 
		// simply try to match every portion of the stateName with substates
		List<String> names = this.getStateList( stateName );
		
		if( names.size() == 0 )
		{
			return null;
		}

		org.jts.pbValidator.StateMap returnMap = map;

		for( String stName : names )
		{
			returnMap = returnMap.findSubStateMatchingMap( stName );

			if( returnMap == null )
			{
				return null;
			}
		}

		return returnMap;
	}

	private List<String> getStateList( String stateName )
	{
		if( stateName == null )
		{
			return null;
		}

		List<String> stateList = new ArrayList();
		
		String[] states = stateName.split( "\\." );
		
		for( String state : states )
		{
			stateList.add( state );
		}
		
		return stateList;		
	}
	
	public org.jts.pbValidator.StateMap getMatchingStateInMap( org.jts.pbValidator.StateMap mapToSearch )
	{
		// check for null matching map
		if( mapToSearch == null )
		{
			return null;
		}

		// deal with current map being root
		if( this.isRoot() )
		{
			return null;
		}
		
		// make sure state machines are the same
		if( !this.isSameStateMachine( mapToSearch ) )
		{
			return null;
		}

		// get list of state names from current map up to root
		List<String> stateNames = this.getNamesListRootToCurrent();

		// start with map to search to find matching substates
		org.jts.pbValidator.StateMap returnMap = mapToSearch;

		for( String stateName : stateNames )
		{
			returnMap = returnMap.findSubStateMatchingMap( stateName );
			
			if( returnMap == null )
			{
				return null;
			}
		}
		
		return returnMap;
	}
	
}
