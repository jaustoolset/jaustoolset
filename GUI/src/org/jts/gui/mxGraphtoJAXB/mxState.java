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

package org.jts.gui.mxGraphtoJAXB;

import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import java.util.List;
import java.util.ArrayList;
import java.io.ByteArrayInputStream;

import org.jts.jsidl.binding.Transition;
import org.jts.jsidl.binding.DefaultTransition;
import org.jts.jsidl.binding.State;
import org.jts.jsidl.binding.PseudoStartState;
import org.jts.jsidl.binding.DefaultState;
import org.jts.jsidl.binding.Entry;
import org.jts.jsidl.binding.Exit;

import org.jts.gui.mxGraphtoJAXB.parser.StateParser;
import org.jts.gui.mxGraphtoJAXB.parser.ParseException;

public class mxState {

  public String name = null;
  public String initialState = null;
  public String interpretation = null;
  public List <mxAction>entryActionList = null;
  public List <mxAction>exitActionList = null;
  public List <mxTransition>mxtransitions = null;
  public List <mxDefaultTransition> mxdefaultTransitions = null;
  public List<mxState> mxstates = null;  // substates
  public mxDefaultState mxdefaultState = null;
  public mxPseudoStartState pState = null;
  
  protected Node stateCell = null;
  
  public mxState( Node stateCell, NodeList cells ) throws ParseException {
     this.stateCell = stateCell; 
           
    // get name, entry, exit actions and interpretation
    parseValueAttrib( stateCell );
    
    // get transitions
    mxtransitions = mxTransition.getTransitions( stateCell, cells );
    
    // get default transition
    mxdefaultTransitions = mxDefaultTransition.getDefaultTransitions( stateCell, cells );
    
    // get states
    mxstates = getStates( stateCell, cells );
        
    // compute initial state and populate the pseudo start state
    pState = new mxPseudoStartState( mxstates, cells );
    mxState istate = pState.getInitialState();
    if( istate != null )
      initialState = istate.name;
    
    // get default state
    mxdefaultState = getDefaultState(stateCell, cells);
  }
  
  /**
  state = 
   element state {
     attribute name { identifier_ns },
     attribute initial_state {identifier}?,
     attribute interpretation { text }?,
     entry?,
     exit?,
     transition*, 
     default_transition*, 
     state*,
     default_state?,
     pseudo_start_state?,
     mxCell?
   }
  **/
  public State convert() {
    State state = new State();
    
    // name
    state.setName( name );
    
    // initial state
    if( initialState != null )
      state.setInitialState( initialState );
      
    // interpretation
    
    // entry actions
    if( entryActionList.size() > 0 ) {
      Entry entry = new Entry();
      List entryActions = entry.getActionOrSendAction();
      
      for( int ii=0; ii<entryActionList.size(); ii++ ) 
        entryActions.add( entryActionList.get(ii).convert() );
      
      state.setEntry( entry );  
    }
    
    // exit actions
    if( exitActionList.size() > 0 ) {
      Exit exit = new Exit();
      List exitActions = exit.getActionOrSendAction();
      
      for( int ii=0; ii<exitActionList.size(); ii++ ) 
        exitActions.add( exitActionList.get(ii).convert() );
      
      state.setExit( exit );
    }
    
    // transitions
    List<Transition> transitions =  state.getTransition();
    for( int ii=0; ii<mxtransitions.size(); ii++) 
      transitions.add( mxtransitions.get(ii).convert() );
    
    // default transitions
    List<DefaultTransition> defaultTransitions =  state.getDefaultTransition();
    for( int ii=0; ii<mxdefaultTransitions.size(); ii++) 
      defaultTransitions.add( mxdefaultTransitions.get(ii).convert() );
    
    // states
    List<State> states = state.getState();
    for(int ii=0; ii< mxstates.size(); ii++ )
      states.add( mxstates.get(ii).convert() );
    
    // default state
    if( mxdefaultState != null )
      state.setDefaultState( mxdefaultState.convert() );
    
    // pseudoStartState
    PseudoStartState jxps = pState.convert();

    if( jxps != null )
      state.setPseudoStartState( jxps );
    
    // mxcell
    state.setMxCell( mxCell.convert( stateCell ) );
    
    return state;
  }
  
  protected void parseValueAttrib( Node cell ) throws ParseException {
    Node valueAttrib = cell.getAttributes().getNamedItem("value");
    String value = valueAttrib.getNodeValue();

    ByteArrayInputStream stream = new ByteArrayInputStream( value.getBytes() );
  
    StateParser parser = new StateParser( stream );
    parser.Input();
    this.name = parser.name;
    this.entryActionList = parser.entryActionList;
    this.exitActionList = parser.exitActionList;
    this.interpretation = parser.interpretation;
  }
  
  public static List<mxState> getStates( Node stateOrStateMachineCell, NodeList cells ) throws ParseException {
    // get id of parent cell
    Node idAttrib = stateOrStateMachineCell.getAttributes().getNamedItem("id");
    String id = idAttrib.getNodeValue() ;
    
    List mxstates = new ArrayList<mxState>();
    for(int ii=0; ii< cells.getLength(); ii++) {
    	Node cell = cells.item(ii);
    	Node parentAttrib = cell.getAttributes().getNamedItem("parent");
    	
    	if( parentAttrib == null ) continue;
    	  	
    	String parent = parentAttrib.getNodeValue() ;
    	
    	// add child cell
    	if( parent.equals(id) ) {
    	    Node styleAttrib = cell.getAttributes().getNamedItem("style");
    	    
    	    if( styleAttrib == null ) continue;
    	  	
    	    String style = styleAttrib.getNodeValue() ;
    	    
    	    if( style.startsWith("fsmStyle=state;") || style.startsWith("state")) {
            mxState mxstate = new mxState( cell, cells );
            mxstates.add( mxstate );
          }
    	}
    }
    
    return mxstates;
  }
  
  public static mxDefaultState getDefaultState( Node stateCell, NodeList cells ) throws ParseException {
    // get id of parent cell
    Node idAttrib = stateCell.getAttributes().getNamedItem("id");
    String id = idAttrib.getNodeValue() ;
    
    for(int ii=0; ii< cells.getLength(); ii++) {
    	Node cell = cells.item(ii);
    	Node parentAttrib = cell.getAttributes().getNamedItem("parent");
    	
    	if( parentAttrib == null ) continue;
    	  	
    	String parent = parentAttrib.getNodeValue() ;
    	
    	// add child cell
    	if( parent.equals(id) ) {
    	    Node styleAttrib = cell.getAttributes().getNamedItem("style");
    	    
    	    if( styleAttrib == null ) continue;
    	  	
    	    String style = styleAttrib.getNodeValue() ;
    	    
    	    if( style.startsWith("fsmStyle=defaultState;") || style.startsWith("defaultState")) {
            return new mxDefaultState( cell, cells );
          }
    	}
    } 
    return null;
  }
}