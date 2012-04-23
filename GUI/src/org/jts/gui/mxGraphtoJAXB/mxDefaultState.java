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
import org.jts.jsidl.binding.DefaultState;
import org.jts.jsidl.binding.Transition;
import org.jts.jsidl.binding.DefaultTransition;

import org.jts.gui.mxGraphtoJAXB.parser.ParseException;

public class mxDefaultState {
  public String interpretation = null;
  public List <mxTransition>mxtransitions = null;
  public List <mxDefaultTransition> mxdefaultTransitions = null;
  
  protected Node defaultStateCell = null;
  
  public mxDefaultState( Node defaultStateCell, NodeList cells ) throws ParseException {
     this.defaultStateCell = defaultStateCell; 
      
    // get transitions
    mxtransitions = mxTransition.getTransitions( defaultStateCell, cells );
    
    // get default transition
    mxdefaultTransitions = mxDefaultTransition.getDefaultTransitions( defaultStateCell, cells );    
  }
  
  /**
  default_state = 
  element default_state {
     attribute interpretation { text }?,
     transition*,
     default_transition*, 
     mx_cell?
  }
  **/
public DefaultState convert() {
    DefaultState state = new DefaultState();
        
    // transitions
    List<Transition> transitions =  state.getTransition();
    for( int ii=0; ii<mxtransitions.size(); ii++) 
      transitions.add( mxtransitions.get(ii).convert() );
    
    // default transitions
    List<DefaultTransition> defaultTransitions =  state.getDefaultTransition();
    for( int ii=0; ii<mxdefaultTransitions.size(); ii++) 
      defaultTransitions.add( mxdefaultTransitions.get(ii).convert() );
    
    // mxcell
    state.setMxCell( mxCell.convert( defaultStateCell ) );
    
    return state;
  }
}