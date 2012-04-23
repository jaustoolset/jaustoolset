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

import org.jts.jsidl.binding.StateMachine;
import org.jts.jsidl.binding.State;
import org.jts.jsidl.binding.Entry;
import org.jts.jsidl.binding.Exit;
import org.jts.jsidl.binding.Action;
import org.jts.jsidl.binding.Argument;
import org.jts.jsidl.binding.DefaultState;
import org.jts.jsidl.binding.PseudoStartState;

import org.jts.mxGraph.binding.MxCell;
import org.jts.mxGraph.binding.ObjectFactory;

public class jxState {
  private StateMachine fsm = null;
  private List<State> states = null;
  private long parentId = 0;
  private float xOffset = 0f;
  private float yOffset = OFFSET;
  public static final float OFFSET = 110f;
  
  public jxState( StateMachine fsm ) {
    this.fsm = fsm;
    states = fsm.getState();
    this.parentId = fsm.getMxCell().getId();
  }
  
  public jxState( List<State> states, long parentId, float xOffset, float yOffset ) {
    this.states = states;
    this.parentId = parentId;
    this.xOffset = xOffset;
    this.yOffset = yOffset;
  }
  
  protected void seedId( ) {
    
    for(int ii=0; ii< states.size(); ii++) {
      
      State state = states.get(ii);
      
      org.jts.jsidl.binding.MxCell cell = state.getMxCell(); 
      if( cell != null ) 
        jxUtil.seedId( cell.getId() );
     
      // nested states
      List<State> substates = state.getState();
     
      if( substates != null && substates.size() > 0 ) {
        jxState substate = new jxState( substates, 0, 0, 0 );
        substate.seedId( );  
      }
     
      // default state
      if( cell != null ) {
        jxDefaultState defaultState = new jxDefaultState( state );
        defaultState.seedId();
      }
    }
  }
  
  
  /**
  state = 
   element state {
     attribute name { identifier_ns },
     attribute initial_state {identifier}?,
     attribute interpretation { text }?,
     entry?,
     exit?,
      - transition*, 
      - default_transition*, 
      - state*,
      - default_state?,
      - pseudo_start_state?,
     mxCell?
   }
  **/
  public List<MxCell> convert() {

    List<MxCell> list = new ArrayList<MxCell>();
    
    xOffset = OFFSET;
    
    for(int ii=0; ii< states.size(); ii++) {
      State state = states.get(ii);
       
      org.jts.jsidl.binding.MxCell cell = state.getMxCell();
          
      if( cell == null ) {
        cell = createCell( state );
        state.setMxCell( cell );
        yOffset += OFFSET;
      }
          
      // convert state
      list.add( jxCell.convert( cell )  );
      
     // note: transitions and default transitions are converted in StateMachine after
     //          all states and default states have been converted. this is done
     //          to guarantee that all states have an MxCell for processing the 
     //          targetId for transitions
      
      // convert nested states
      List<State> ll = state.getState();
      if( ll != null ) {
          jxState nsstate = new jxState( ll, cell.getId(), xOffset, yOffset );
          List<MxCell> nsstateCells = nsstate.convert();

          for( int jj=0; jj< nsstateCells.size(); jj++) {
            list.add( nsstateCells.get(jj) );
          }
      }
      jxUtil.resetPointOffset();
      
      // convert default state element
      jxDefaultState dstate = new jxDefaultState( state );
       List<MxCell> dstateCells = dstate.convert(); 
      for( int jj=0; jj< dstateCells.size(); jj++) {
        list.add( dstateCells.get(jj) );
      }
      
      // convert pseudo start state element
      jxPseudoStartState psstate = new jxPseudoStartState( state );
      List<MxCell> psstateCells = psstate.convert(); 
      for( int jj=0; jj< psstateCells.size(); jj++) {
        list.add( psstateCells.get(jj) );
      }
    }
      
    return list;
  }
  
  private org.jts.jsidl.binding.MxCell createCell( State state ) {
    org.jts.jsidl.binding.ObjectFactory objf = new
                                org.jts.jsidl.binding.ObjectFactory();
    org.jts.jsidl.binding.MxCell cell = objf.createMxCell();
    
      // id { xsd:unsignedInt },
      cell.setId( new Long(jxUtil.getNextId()) );
      // parent { xsd:unsignedInt }?,
      cell.setParent( new Long(parentId) );
      // source { xsd:unsignedInt }?,
      // target { xsd:unsignedInt }?,
      // style { xsd:string },
      cell.setStyle("state;");
      // value { xsd:string }
      cell.setValue( getValue( state ) );
      // edge { xsd:unsignedInt }?,
      // vertex { xsd:unsignedInt }?,
      cell.setVertex( new Long(1));
                     
      org.jts.jsidl.binding.MxGeometry geom = objf.createMxGeometry();
      cell.setMxGeometry( geom );
      geom.setAs("geometry");
     geom.setHeight( 80f );
     geom.setWidth( 80f );
     
     //Point pt = jxUtil.getNextPointOffset();
     geom.setX( Float.valueOf(xOffset) );
     geom.setY( Float.valueOf(yOffset) );
      
      return cell;
  }
  
  /**
  name; &#10; entry|exit : actName( arg* )*;*\n
  **/
  private String getValue( State state ) {
     StringBuffer buf = new StringBuffer();
     
     buf.append( state.getName() +"; " );
     Entry entry = state.getEntry();
     if( entry == null ) {
       entry = new Entry();
       state.setEntry( entry );
     }
     List actions = entry.getActionOrSendAction();
     for( int ii=0; ii< actions.size(); ii++ ) {
       Action action = (Action)actions.get(ii);
       buf.append("entry: "+action.getName()+"( "  );
       
       List<Argument> args = action.getArgument();
       for( int jj=0; jj< args.size(); jj++ ) {
         Argument arg = args.get(jj);
         buf.append( arg.getValue() );
         if( jj < args.size() -1) buf.append( ", " );
       }
       
       buf.append(" );"  );
     }
     
     Exit exit = state.getExit();
     if( exit == null ) {
       exit = new Exit();
       state.setExit( exit );
     }
     actions = exit.getActionOrSendAction();
     for( int ii=0; ii< actions.size(); ii++ ) {
       Action action = (Action)actions.get(ii);
       buf.append("exit: "+action.getName()+"( "  );
       
       List<Argument> args = action.getArgument();
       for( int jj=0; jj< args.size(); jj++ ) {
         Argument arg = args.get(jj);
         buf.append( arg.getValue() );
         if( jj < args.size() -1) buf.append( ", " );
       }
       
       buf.append(" );"  );
     }
     
     return buf.toString();
  }
}