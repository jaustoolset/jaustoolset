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

import java.util.List;
import java.util.ArrayList;

import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import org.jts.jsidl.binding.Transition;
import org.jts.jsidl.binding.State;
import org.jts.jsidl.binding.PseudoStartState;


public class mxPseudoStartState {
  private mxState istate = null;
  private mxPseudoStartState.mxStateTransitionPair stateTransitionPair = null;
  
  public mxPseudoStartState( List<mxState> mxstates, NodeList cells ) {
     findInitialState( mxstates, cells );
  }
  
  public mxState getInitialState() {    
    return istate;
  }
  
  /**
pseudo_start_state = 
   element pseudo_start_state {
     element state { mxCell }?,
     element transition { mxCell }?
   }
  **/
  public PseudoStartState convert() {
  
    if( istate == null || stateTransitionPair == null ) 
      return null;
  
    PseudoStartState jxps = new PseudoStartState();
    PseudoStartState.State state = new PseudoStartState.State();
    PseudoStartState.Transition transition = new PseudoStartState.Transition(); 
   
    state.setMxCell( mxCell.convert( stateTransitionPair.getState() ) ); 
    transition.setMxCell( mxCell.convert( stateTransitionPair.getTransition() ) );
   
    jxps.setState( state );
    jxps.setTransition( transition ); 
   
    return jxps;
  }
  
  // finds the initial state in the specified list of sub-states, or null if the list of sub-states is empty
  private void findInitialState( List<mxState> mxsubStates, NodeList cells ) {
    String initialState = null;
    
    if( mxsubStates == null || mxsubStates.size() == 0 ) return;
    
    // make a list of all pseudo start states
    List<Node> pstates = new ArrayList<Node>();
    for(int ii=0; ii< cells.getLength(); ii++) {
      Node cell = cells.item(ii);
    	Node styleAttrib = cell.getAttributes().getNamedItem("style");
    	
    	if( styleAttrib == null ) continue;
    	
    	String style = styleAttrib.getNodeValue() ;
    	
    	if( style.startsWith("fsmStyle=pseudoStartState;") || style.startsWith("pseudoStartState")) 
    	  pstates.add( cell );
    }
    
    // make a list of all simple transitions whose sources are pseudo start states
    List<mxPseudoStartState.mxStateTransitionPair> stateTransitionPairs = 
                                                       new ArrayList<mxPseudoStartState.mxStateTransitionPair>();
    for(int ii=0; ii< cells.getLength(); ii++) {
      Node cell = cells.item(ii);
    	Node styleAttrib = cell.getAttributes().getNamedItem("style");
    	Node sourceAttrib = cell.getAttributes().getNamedItem("source");
    	
    	if( styleAttrib == null || sourceAttrib == null ) continue;
    	
    	String style = styleAttrib.getNodeValue() ;
    	String source = sourceAttrib.getNodeValue() ;
    	
    	Node node = null;
    	if( (style.startsWith("fsmStyle=simpleTransition;") || style.startsWith("simpleTransition")) && (node = getNode(source, pstates)) != null ) {
    	  mxPseudoStartState.mxStateTransitionPair pair = new mxPseudoStartState.mxStateTransitionPair( node, cell );
    	  stateTransitionPairs.add( pair );
    	}
    }
       
    // find the name of a node (substate) that is the target of a simple transition 
    // and whose source is a pseudo start state
    for(int ii=0; ii< stateTransitionPairs.size(); ii++) {
      mxPseudoStartState.mxStateTransitionPair pair = stateTransitionPairs.get(ii);
    	Node cell = pair.getTransition();
    	String target = cell.getAttributes().getNamedItem("target").getNodeValue();
    	
    	mxState state = null;
    	if( (state = getmxState( target, mxsubStates  )) != null ) {
    	  istate = state;
    	  stateTransitionPair = pair;
    	  return;
      }
    }
  }
  
  // returns first state found with specified id, or null if none is found
  private mxState getmxState( String id, List<mxState> mxstates) {
    for(int ii=0; ii<mxstates.size(); ii++ ) {
      mxState state = mxstates.get(ii); 
      Node cell = state.stateCell;
    	String idAttrib = cell.getAttributes().getNamedItem("id").getNodeValue();
    	
    	if( idAttrib.equals(id) ) return state;
    }
    
    return null;
  }
  
  // returns first node found with specified id, or null if none is found
  private static Node getNode( String id, List<Node> cells) {
    for(int ii=0; ii<cells.size(); ii++ ) {
      Node cell = cells.get(ii); 
    	String idAttrib = cell.getAttributes().getNamedItem("id").getNodeValue();
    	
    	if( idAttrib.equals(id) ) return cell;
    }
    
    return null;
  }
  
  class mxStateTransitionPair {
    private Node state = null;
    private Node transition = null;
    
    mxStateTransitionPair( Node state, Node transition ) {
      this.state = state;
      this.transition = transition;
    }
    
    Node getState() {
      return state;
    }
    
    Node getTransition() {
      return transition;
    }
  }
}