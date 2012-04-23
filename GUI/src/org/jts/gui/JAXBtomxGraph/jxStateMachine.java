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

import org.jts.jsidl.binding.ProtocolBehavior;
import org.jts.jsidl.binding.StateMachine;
import org.jts.jsidl.binding.State;
import org.jts.jsidl.binding.Start;

import org.jts.mxGraph.binding.MxCell;
import org.jts.mxGraph.binding.ObjectFactory;

public class jxStateMachine {
  private ProtocolBehavior pb = null;
 
  public jxStateMachine( ProtocolBehavior pb ) {
    this.pb = pb;
  }
  
  protected void seedId( ) {
    
    List<StateMachine> fsms = pb.getStateMachine();
    for(int ii=0; ii< fsms.size(); ii++) {
      
      StateMachine fsm = fsms.get(ii);

      org.jts.jsidl.binding.MxCell cell = fsm.getMxCell(); 
      if( cell != null ) {  
        jxUtil.seedId( cell.getId() );

        jxState state = new jxState( fsm );
        state.seedId();
      }

      // transition
      jxTransition transition = new jxTransition( fsm );
      transition.seedId();

      // default transition
      jxDefaultTransition dtransition = new jxDefaultTransition( fsm );
      dtransition.seedId();
    }
  }
  
  public List<MxCell> convert() {
    List<MxCell> list = new ArrayList<MxCell>();
    
    List<StateMachine> fsms = pb.getStateMachine();
    for(int ii=0; ii< fsms.size(); ii++) {
      jxUtil.resetPointOffset();
      
      StateMachine fsm = fsms.get(ii);
       
      // convert state machine elements
      org.jts.jsidl.binding.MxCell cell = fsm.getMxCell();
      if( cell == null ) {
        cell = createCell( fsm, ii );
        fsm.setMxCell( cell );
      } else {
        cell.setValue( "name = "+fsm.getName()+"; isStateless = false;" );
      }
      list.add( jxCell.convert( cell )  );
       
      // convert state and default state elements
      jxState state = new jxState( fsm );
      List<MxCell> stateCells = state.convert(); 
      for( int jj=0; jj< stateCells.size(); jj++) {
        list.add( stateCells.get(jj) );
      }
      
      // convert possible default state contained in state machine
      if( fsm.getDefaultState() != null )
      {
    	jxDefaultState defaultState = new jxDefaultState( fsm.getDefaultState() );
    	List< MxCell > defaultStateCell = defaultState.convert();
    	for( int jj = 0; jj < defaultStateCell.size(); jj++ )
    	{
    		list.add( defaultStateCell.get( jj ) );
    	}
      }
    
      // then convert transition elements
      jxTransition transition = new jxTransition( fsm );
      List<MxCell> transitionCells = transition.convert(); 
      for( int jj=0; jj< transitionCells.size(); jj++) {
        list.add( transitionCells.get(jj) );
      }
      
      // then convert default transition elements
      jxDefaultTransition dtransition = new jxDefaultTransition( fsm );
      List<MxCell> dtransitionCells = dtransition.convert(); 
      for( int jj=0; jj< dtransitionCells.size(); jj++) {
        list.add( dtransitionCells.get(jj) );
      }
    }
    
    
    // then convert pseudo start state elements for each state machine element
    List<Start> starts = pb.getStart();
    for(int ii = 0; ii< starts.size(); ii++ ) {
      
      // get fsm name and state name
      Start start = starts.get(ii);
      String fsmName = start.getStateMachineName();
      String stateName = start.getStateName();
      int endIndex = stateName.indexOf('.');
      if( endIndex == -1 ) endIndex = stateName.length();
      stateName = stateName.substring(0, endIndex );
     
      // get the fsm and state
      for(int jj = 0; jj< fsms.size(); jj++ ) {
        StateMachine fsm = fsms.get(jj);

        if( fsm.getName().equals( fsmName )) {  
          List<State> states = fsm.getState();

          for(int kk = 0; kk< states.size(); kk++ ) {
            State state = states.get(kk);

            if( state.getName().equals( stateName )) {
              jxPseudoStartState pstate = new jxPseudoStartState( fsm, state );
              List<MxCell> pstateCells = pstate.convert(); 
              for( int mm=0; mm< pstateCells.size(); mm++) {
                list.add( pstateCells.get(mm) );
              }
            }
            
          }
          
        }
      }
    }
    
    return list;
  }
  
  private org.jts.jsidl.binding.MxCell createCell( StateMachine fsm, int fsmNum ) {
    org.jts.jsidl.binding.ObjectFactory objf = new
                                org.jts.jsidl.binding.ObjectFactory();
    org.jts.jsidl.binding.MxCell cell = objf.createMxCell();
        
      // id { xsd:unsignedInt },
      cell.setId( new Long(jxUtil.getNextId()) );
      // parent { xsd:unsignedInt }?,
      cell.setParent( new Long(1) );
      // source { xsd:unsignedInt }?,
      // target { xsd:unsignedInt }?,
      // style { xsd:string },
      cell.setStyle("finiteStateMachine;");
      // value { xsd:string }
      String isStateless = "false";
      if( pb.isIsStateless() != null )
        isStateless = pb.isIsStateless().toString(); 
      cell.setValue( "name = "+fsm.getName()+"; isStateless = "+isStateless+";" );
      // edge { xsd:unsignedInt }?,
      // vertex { xsd:unsignedInt }?,
      cell.setVertex( new Long(1));
                     
      org.jts.jsidl.binding.MxGeometry geom = objf.createMxGeometry();
      cell.setMxGeometry( geom );
      geom.setAs("geometry");
      geom.setHeight( 400f );
      geom.setWidth( 850f );
      geom.setX( 0f );
      geom.setY( 400f*fsmNum );
      
      fsm.setMxCell( cell );
      
      return cell;
  }
}