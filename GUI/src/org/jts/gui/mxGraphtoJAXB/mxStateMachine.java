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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.DOMException;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.ByteArrayInputStream;

import org.jts.jsidl.binding.StateMachine;
import org.jts.jsidl.binding.PseudoStartState;
import org.jts.jsidl.binding.State;
import org.jts.jsidl.binding.MxCell;
import org.jts.jsidl.binding.MxGeometry;
import org.jts.jsidl.binding.MxPoint;
import org.jts.jsidl.binding.MxArray;

import org.jts.gui.mxGraphtoJAXB.parser.StateMachineParser;
import org.jts.gui.mxGraphtoJAXB.parser.ParseException;

public class mxStateMachine {
  
  public String name = null;
  public String isStateless = null;
  public String start = null;
  public String interpretation = null;
  public List<mxState> mxstates = null;
  public mxDefaultState mxdefaultState = null;
  private PseudoStartState pseudoStartState = null;
  
  protected Node stateMachineCell = null;
  
  /** Takes as input an fsm cell and the entire nodelist that represents the protocol behavior
  */
  public mxStateMachine( Node stateMachineCell, NodeList cells ) throws ParseException {
    this.stateMachineCell = stateMachineCell;
    
    // get name, isStateless, interpretation
    parseValueAttrib( stateMachineCell );
    
    // get states
    mxstates = mxState.getStates(stateMachineCell, cells);
            
    // compute start
    start = getStart( mxstates, cells );
    
    mxdefaultState = mxState.getDefaultState(stateMachineCell, cells);
  }

  public StateMachine convert( ) {
    StateMachine fsm = new StateMachine();
    
    // name
    fsm.setName(name);
    
    // interpretation
    
    // state
    List<State> states = fsm.getState();
    for( int ii=0; ii<mxstates.size(); ii++) 
      states.add( mxstates.get(ii).convert() );
      
    // pseudoStartState
    fsm.setPseudoStartState( pseudoStartState );
    
    // default state
    if( mxdefaultState != null )
    {
    	fsm.setDefaultState( mxdefaultState.convert() );
    }
    
    // mxcell
    fsm.setMxCell( mxCell.convert( stateMachineCell ) );
      
    return fsm;
  }
  
  private void parseValueAttrib( Node cell ) throws ParseException {
    Node attrib = cell.getAttributes().getNamedItem("value");
    String value = attrib.getNodeValue();
    
    ByteArrayInputStream stream = new ByteArrayInputStream( value.getBytes() );
  
    StateMachineParser parser = new StateMachineParser( stream );
    parser.Input();
    this.name = parser.name;
    this.isStateless = parser.isStateless;
    this.interpretation = parser.interpretation;  
  }
  
  private String getStart( List<mxState> mxsubStates, NodeList cells ) throws ParseException {
    
    mxPseudoStartState pstate = new mxPseudoStartState( mxsubStates, cells );
    if(pseudoStartState == null)
      pseudoStartState = pstate.convert();
    
    mxState iState = pstate.getInitialState();

    if( iState == null )
      return null;
    else {
      String str = getStart(  iState.mxstates , cells );
      
      if( str != null )
        return iState.name + "." + str;
      else
        return iState.name;
    }    
  }
}