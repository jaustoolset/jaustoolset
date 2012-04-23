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

package org.jts.gui.JAXBtomxGraph;

import java.util.List;
import java.util.ArrayList;

import java.awt.Point;

import org.jts.jsidl.binding.State;
import org.jts.jsidl.binding.StateMachine;
import org.jts.jsidl.binding.ProtocolBehavior;
import org.jts.jsidl.binding.PseudoStartState;

import org.jts.mxGraph.binding.MxCell;
import org.jts.mxGraph.binding.MxGeometry;
import org.jts.mxGraph.binding.ObjectFactory;

public class jxPseudoStartState {
  private State initialState = null;  // initial substate
  private PseudoStartState pstate = null;  // pseudostart state element of parent state's nested state set
  private long parentId = 0;
  private State parentState = null;
  private StateMachine parentStateMachine = null;
 
  public jxPseudoStartState( State state ) {
    this.parentId = state.getMxCell().getId();
    pstate = state.getPseudoStartState();
    this.parentState = state;
    
    if( pstate == null ) {
    
      // find initial state
      List<State> substates = state.getState();
      
      if( substates != null ) {
        if( substates.size() == 0 ) {
          ;  // no substates
        }  
        else if( substates.size() == 1 ) {
          initialState = substates.get(0);
        }
        else {
          String iname = state.getInitialState();
          
          for( int ii=0; ii<substates.size(); ii++) {
            State ss = substates.get(ii);
  
            if( ss.getName().equals( iname )) {
              initialState = ss;
              break;
            } 
          }
          
        }
      }
    }
 
  }
  
  public jxPseudoStartState( StateMachine fsm, State initialState ) {
    this.parentId = fsm.getMxCell().getId();
    pstate = fsm.getPseudoStartState();
    this.initialState = initialState;
    this.parentStateMachine = fsm;
  }
  
  
  /**
pseudo_start_state = 
   element pseudo_start_state {
     element state { mxCell }?,
     element transition { mxCell }?
   }
  **/
  public List<MxCell> convert() {
    List<MxCell> list = new ArrayList<MxCell>();
    
    if( pstate != null ) {
      PseudoStartState.State pss = pstate.getState();
      PseudoStartState.Transition pst = pstate.getTransition();
      
      list.add( jxCell.convert( pss.getMxCell() ) );
      list.add( jxCell.convert( pst.getMxCell() ) );
    } else {
      List<org.jts.jsidl.binding.MxCell> cells = createCells();
      if( cells != null ) {
        PseudoStartState.State pss = new PseudoStartState.State();
        pss.setMxCell( cells.get(0) );
        PseudoStartState.Transition pst = new PseudoStartState.Transition();
        pst.setMxCell( cells.get(1) );
        pstate = new PseudoStartState();
        pstate.setState( pss );
        pstate.setTransition( pst );
        
        if( parentStateMachine != null ) 
          parentStateMachine.setPseudoStartState( pstate );
        else
          parentState.setPseudoStartState( pstate );
        
        list.add( jxCell.convert( cells.get(0) ));
        list.add( jxCell.convert( cells.get(1) ));
      }    
    }
        
    return list;
  }
  
  /**
    creates mxcells for the pseudostartstate and the transition between 
    the pseudostartstate and the state
  **/
  private List<org.jts.jsidl.binding.MxCell> createCells( ) {
    if( initialState == null )
      return null;
    
    List<org.jts.jsidl.binding.MxCell> list = new ArrayList<org.jts.jsidl.binding.MxCell>();
    
    /* create pseudo state cell */
    org.jts.jsidl.binding.ObjectFactory objf = new org.jts.jsidl.binding.ObjectFactory();
    org.jts.jsidl.binding.MxCell scell = objf.createMxCell();
    
      // id { xsd:unsignedInt },
      Long sid = new Long(jxUtil.getNextId()); 
      scell.setId( sid );
      // parent { xsd:unsignedInt }?,
      scell.setParent( new Long(parentId) );
      // source { xsd:unsignedInt }?,
      // target { xsd:unsignedInt }?,
      // style { xsd:string },
      scell.setStyle("pseudoStartState;");
      // value { xsd:string }
      scell.setValue("");
      // edge { xsd:unsignedInt }?,
      // vertex { xsd:unsignedInt }?,
      scell.setVertex( new Long(1) );
                     
      org.jts.jsidl.binding.MxGeometry sgeom = objf.createMxGeometry();
      sgeom.setAs("geometry");
      sgeom.setHeight( 20f );
      sgeom.setWidth( 20f );
  
     float sx = initialState.getMxCell().getMxGeometry().getX() - 40;
     float sy = initialState.getMxCell().getMxGeometry().getY() - 40; 
     sgeom.setX( sx );
     sgeom.setY( sy );
     
     scell.setMxGeometry( sgeom );
     list.add(scell);
     
     /* create transition cell from pseudo state cell to initial state */
     org.jts.jsidl.binding.MxCell tcell = objf.createMxCell();
    
      // id { xsd:unsignedInt },
      tcell.setId( new Long(jxUtil.getNextId()) );
      // parent { xsd:unsignedInt }?,
      tcell.setParent( new Long(parentId) );
      // source { xsd:unsignedInt }?,
      tcell.setSource( sid );
      // target { xsd:unsignedInt }?,
      tcell.setTarget( initialState.getMxCell().getId() );
      // style { xsd:string },
      tcell.setStyle("simpleTransition;");
      // value { xsd:string }
      tcell.setValue("");
      // edge { xsd:unsignedInt }?,
      tcell.setEdge( new Long(1) );
      // vertex { xsd:unsignedInt }?,
      
     org.jts.jsidl.binding.MxGeometry tgeom = objf.createMxGeometry();
      tgeom.setAs("geometry");
      tgeom.setHeight( 20f );
      tgeom.setWidth( 20f );
     float tx = initialState.getMxCell().getMxGeometry().getX() - 40;
     float ty = initialState.getMxCell().getMxGeometry().getY() - 40;
     tgeom.setX( tx );
     tgeom.setY( ty );
     
     tcell.setMxGeometry( tgeom );
     list.add(tcell);
     
      return list;
  }
}