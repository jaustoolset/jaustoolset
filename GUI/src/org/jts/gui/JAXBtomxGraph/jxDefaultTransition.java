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
import java.util.Iterator;

import org.jts.jsidl.binding.StateMachine;
import org.jts.jsidl.binding.State;
import org.jts.jsidl.binding.EndState;
import org.jts.jsidl.binding.DefaultState;
import org.jts.jsidl.binding.DefaultTransition;
import org.jts.jsidl.binding.Transition;

import org.jts.mxGraph.binding.MxCell;

public class jxDefaultTransition {
  private StateMachine fsm = null;
  
  public jxDefaultTransition( StateMachine fsm ) { 
   this.fsm = fsm;
  }
  
  protected void seedId() {
    recurseSeedId( fsm.getState() );
    
    // trans/dtrans can be contained default state of fsm
    if( fsm.getDefaultState() != null )
    {
    	DefaultState dstate = fsm.getDefaultState();
    	
    	// seed default state transition ids
    	if( dstate.getTransition() != null )
    	{
    		for( DefaultTransition dtr : dstate.getDefaultTransition() )
    		{
    			if( dtr.getMxCell() != null )
    			{
    				jxUtil.seedId( dtr.getMxCell().getId() );
    			}
    		}
    	}
    }
  }
  
  private void recurseSeedId( List<State> states ) {
    
    for(int ii=0; ii< states.size(); ii++) {
      // for each state, get list of transitions in that state,
      State state = states.get(ii);
      
      // recurse through substates first
      List<State> substates = state.getState();
      if( substates != null && substates.size() > 0 ) {
        recurseSeedId( substates );
      }
      
      List<DefaultTransition> dtransitions = state.getDefaultTransition();
      
      for( int jj = 0; jj< dtransitions.size(); jj++ ) {
        org.jts.jsidl.binding.MxCell cell = dtransitions.get(jj).getMxCell();
        if( cell != null ) 
        jxUtil.seedId( cell.getId() );
      }
      
      
      // do same for default states within each state
      DefaultState dstate = state.getDefaultState();
      if( dstate != null ) {
        dtransitions = dstate.getDefaultTransition();
        
        for( int jj = 0; jj< dtransitions.size(); jj++ ) {
          org.jts.jsidl.binding.MxCell cell = dtransitions.get(jj).getMxCell();
          if( cell != null ) 
          jxUtil.seedId( cell.getId() );
        }
      }
    }
  }
  
  /*
  default_transition =   
  element default_transition { 
    attribute interpretation { text }?,
    guard?,
    (internal | simple | push | pop),
    (action | send_action)*, 
    mxCell?
  }
  */
  public List<MxCell> convert() {

    List<MxCell> list = null;

    List<State> states = fsm.getState();
    
    list = recurseThroughStates( states );
    
    // fsm may contain a default state with transitions
    if( fsm.getDefaultState() != null )
    {
    	DefaultState dState = fsm.getDefaultState();
    	
    	List< DefaultTransition > dTransitions = dState.getDefaultTransition();
		removeDuplicates( dTransitions );

		long parentId = dState.getMxCell().getParent(); 
		long sourceId = dState.getMxCell().getId();

		// then process list of transitions
		list.addAll( getDefaultTransitions( dTransitions, parentId, sourceId, dState ) );
    }
    
    return list;
  }
  
  List<MxCell> recurseThroughStates( List<State> states ) {
    List<MxCell> list = new ArrayList<MxCell>();
    
    for(int ii=0; ii< states.size(); ii++) {
      // for each state, get list of transitions in that state,
      State state = states.get(ii);
      
      // recurse through substates first
      List<State> substates = state.getState();
      if( substates != null && substates.size() > 0 ) {
        list.addAll( recurseThroughStates( substates ) );
      }
      
      List<DefaultTransition> dtransitions = state.getDefaultTransition();
      removeDuplicates( dtransitions );
      
      // then set the parent and source ids for this list of transitions
      long parentId = state.getMxCell().getParent(); 
      long sourceId = state.getMxCell().getId();
      
      // then process list of transitions
      list.addAll( getDefaultTransitions( dtransitions, parentId, sourceId, state ) );
      
      // do the same for default states within each state
      DefaultState dstate = state.getDefaultState();
      if( dstate != null ) {
        dtransitions = dstate.getDefaultTransition();
        removeDuplicates( dtransitions );
        
        parentId = dstate.getMxCell().getParent(); 
        sourceId = dstate.getMxCell().getId();
      
        // then process list of transitions
        list.addAll( getDefaultTransitions( dtransitions, parentId, sourceId, state ) );
      }
    }
    
    return list;
  }
  
  private void removeDuplicates( List<DefaultTransition> dtransitions ) {
    if( dtransitions == null || dtransitions.size() <= 1) return;
    
    long [] ids = new long[ dtransitions.size() ]; 
    
    for( int ii=0; ii< ids.length; ii++ )   
      ids[ii] = -1;
    
    int index = 0;
    Iterator<org.jts.jsidl.binding.DefaultTransition> iter = dtransitions.iterator();
    
    while( iter.hasNext() ) {
      org.jts.jsidl.binding.DefaultTransition tr = iter.next();
      org.jts.jsidl.binding.MxCell cell = tr.getMxCell();
      if( cell != null ) {
        long id = cell.getId();
        
        for( int ii=0; ii< ids.length; ii++ ) {
          if( ids[ii] == -1)
            break;
          if( ids[ii] == id ) {
            iter.remove();
            break;
          }
        }
        
        ids[index++] = id;
      }
    }
    
  }
  
  List<MxCell> getDefaultTransitions( List<DefaultTransition> dtransitions, long parentId, long sourceId, Object state ) {
    List<MxCell> list = new ArrayList<MxCell>();
    
    for(int jj=0; jj< dtransitions.size(); jj++) {
      DefaultTransition dtransition = dtransitions.get(jj);
           
      if( dtransition.getInternal() != null ) {
        jxDefaultInternalTransition dinternal = new jxDefaultInternalTransition( dtransition, parentId, sourceId );
        list.add( dinternal.convert() );
      } else if ( dtransition.getSimple() != null ) {
        
        // get targetId
        long targetId = 0;
        EndState es = dtransition.getSimple().getEndState();
        if( es == null || es.getState().contains("default_state")) {
          targetId = sourceId;
        } else {
          targetId = getTargetId( es.getState() );
        }
        
        jxDefaultSimpleTransition dsimple = new jxDefaultSimpleTransition( dtransition, parentId, sourceId, targetId );
        list.add( dsimple.convert() );
      } else if ( dtransition.getPush() != null ) {
        // get targetId
        long targetId = 0;
        EndState es = dtransition.getPush().getEndState();
        if( es == null || es.getState().contains("default_state")) {
          targetId = sourceId;
        } else {
          targetId = getTargetId( es.getState() );
        }
      
        jxDefaultPushTransition dpush = new jxDefaultPushTransition( dtransition, parentId, sourceId, targetId, state );
        list.add( dpush.convert() );
      } else if ( dtransition.getPop() != null ) {
        jxDefaultPopTransition dpop = new jxDefaultPopTransition( dtransition, parentId, sourceId );
        list.add( dpop.convert() );
      } else {
        jxDefaultSimpleTransition dsimple = new jxDefaultSimpleTransition( dtransition, parentId, sourceId, sourceId );
        list.add( dsimple.convert() );
      }
    }
    
    return list;
  }
  
  private long getTargetId( String endState ) {
    // get target state
    int index = endState.indexOf('.');
    
    String es = null;
    if( index == -1 )
      es = endState;
    else 
      es = endState.substring(0, index);
      
    State targetState = null; 
    List<State> states = fsm.getState();
    for( int ii=0; ii<states.size(); ii++ ) {
      State state = states.get(ii);
      if( state.getName().equals( es ) ) {
        if( index == -1 ) {
          return state.getMxCell().getId();
        } else {
          targetState = recurseToEndState( endState.substring( index+1, endState.length() ), state );
          break;
        }
      }
    }
    
    if( targetState == null ) {
       throw new RuntimeException("Unknown endstate name found: "+endState);
    }
    
    // get target Id
    return targetState.getMxCell().getId();
  }
  
  private State recurseToEndState( String endState, State state ) {
    int index = endState.indexOf('.');
    
    String es = null;
    if( index == -1 )
      es = endState;
    else 
      es = endState.substring(0, index);
    
    List<State> states = state.getState();
    for( int ii=0; ii<states.size(); ii++ ) {
      State st = states.get(ii);
      if( st.getName().equals( es ) ) {
        if(index == -1)
          return st;
        else
          return recurseToEndState( endState.substring( index+1, endState.length() ), st );
      }
    }
   
    return null;
  }
}