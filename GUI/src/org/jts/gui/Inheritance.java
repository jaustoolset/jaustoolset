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

/*
 * Inheritance.java
 *
 * This class provides a special relationship between the inheritsFrom entity and the protocolBehavior entity in com.u2d.generated.ServiceDef
 */

package org.jts.gui;


import org.jts.jsidl.binding.ProtocolBehavior;
import org.jts.jsidl.binding.Start;
import org.jts.jsidl.binding.StateMachine;
import org.jts.jsidl.binding.State;
import org.jts.jsidl.binding.PseudoStartState;
import org.jts.jsidl.binding.DefaultState;
import org.jts.jsidl.binding.Transition;
import org.jts.jsidl.binding.DefaultTransition;
import org.jts.jsidl.binding.Parameter;
import org.jts.jsidl.binding.Guard;
import org.jts.jsidl.binding.MxCell;
import com.u2d.generated.ServiceDef;
import org.jts.gui.JAXBtomxGraph.jxStateMachine;
import javax.xml.bind.JAXB;
import java.util.List;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;


public  class Inheritance {

	/**
	 * Helper method to convert a database protocol behavior to a jaxb protocol behavior
	 * @param pb
	 * @return
	 */
	public static org.jts.jsidl.binding.ProtocolBehavior getAsJaxb( com.u2d.generated.ProtocolBehavior pb )
	{
		if( pb == null )
		{
			return null;
		}
		
		String pbAsString = pb.getProtocolBehavior().toString();
		
		InputStream baseStream = new ByteArrayInputStream( pbAsString.getBytes() );
		
		org.jts.jsidl.binding.ProtocolBehavior pbAsJaxb = 
			JAXB.unmarshal( baseStream, org.jts.jsidl.binding.ProtocolBehavior.class );
		
		return pbAsJaxb;
	}
	
	/**
	 * Helper method to convert a jaxb protocol behavior to a string representation to set in the database
	 * @param pb
	 * @return
	 */
	public static String getAsString( org.jts.jsidl.binding.ProtocolBehavior pb )
	{
		if( pb == null )
		{
			return null;
		}
		
	    OutputStream ostream = new ByteArrayOutputStream(); 
	    JAXB.marshal( pb, ostream );
	    
	    return ostream.toString();
	}
	
	/**
	 * Prepends the name to every state machine name and start state name
	 * NOTE: modifies the pb directly, does not modify a copy
	 * ex: transport.ReceiveFSM -> Events.transport.ReceiveFSM
	 * @param serviceName
	 * @param pb
	 */
	public static void prependServiceName( String serviceName, org.jts.jsidl.binding.ProtocolBehavior pb )
	{
		if( pb == null || serviceName == null )
		{
			return;
		}
		
		if( pb.getStart() == null || pb.getStateMachine() == null )
		{
			return;
		}

		for( org.jts.jsidl.binding.Start start : pb.getStart() )
		{
			String newStateMachineName = serviceName + "." + start.getStateMachineName();
			start.setStateMachineName( newStateMachineName );
		}
		
		for( org.jts.jsidl.binding.StateMachine sm : pb.getStateMachine() )
		{
			String newStateMachineName = serviceName + "." + sm.getName();
			sm.setName( newStateMachineName );
		}
	}
	
	/**
	 * Removes the prepended service name from all start and state machine names
	 * NOTE: modifies the pb directly, does not modify a copy
	 * ex: Events.transport.ReceiveFSM -> transport.ReceiveFSM
	 * @param pb
	 */
	public static void parsePrependedServiceName( org.jts.jsidl.binding.ProtocolBehavior pb )
	{
		if( pb == null )
		{
			return;
		}
		
		if( pb.getStart() == null || pb.getStateMachine() == null )
		{
			return;
		}

		for( org.jts.jsidl.binding.Start start : pb.getStart() )
		{
			String startStateMachineName = start.getStateMachineName();
			int firstDelimiter = startStateMachineName.indexOf( "." );
			
			if( firstDelimiter > 0 )
			{
				String newStateMachineName = startStateMachineName.substring( firstDelimiter + 1 );
				start.setStateMachineName( newStateMachineName );
			}
		}
		
		for( org.jts.jsidl.binding.StateMachine sm : pb.getStateMachine() )
		{
			String stateMachineName = sm.getName();
			int firstDelimiter = stateMachineName.indexOf( "." );
			
			if( firstDelimiter > 0 )
			{
				String newStateMachineName = stateMachineName.substring( firstDelimiter + 1 );
				sm.setName( newStateMachineName );
			}
		}
	}

	/**
	 * Removes the given service name from all start and state machine names
	 * This is different than parsePrependedServiceName, which takes the first
	 * string before the deliminator everytime without checking its contents.
	 *
	 * NOTE: modifies the pb directly, does not modify a copy
	 * ex: Events.transport.ReceiveFSM -> transport.ReceiveFSM
	 * @param pb
	 */
	public static void stripPrependedServiceName( String serviceName, org.jts.jsidl.binding.ProtocolBehavior pb )
	{
		if( pb == null )
		{
			return;
		}
		
		if( pb.getStart() == null || pb.getStateMachine() == null )
		{
			return;
		}

		for( org.jts.jsidl.binding.Start start : pb.getStart() )
		{
			String startStateMachineName = start.getStateMachineName();
			int firstDelimiter = startStateMachineName.indexOf( "." );
			
			// Only strip to the first delimiter IF the start state name begins with the service def name
			if( firstDelimiter > 0 && 
					startStateMachineName.substring( 0, firstDelimiter ).equals( serviceName ) )
			{
				String newStateMachineName = startStateMachineName.substring( firstDelimiter + 1 );
				start.setStateMachineName( newStateMachineName );
			}
		}
		
		for( org.jts.jsidl.binding.StateMachine sm : pb.getStateMachine() )
		{
			String stateMachineName = sm.getName();
			int firstDelimiter = stateMachineName.indexOf( "." );
			
			// Only strip to the first delimiter IF the state machine name begins with the service def name
			if( firstDelimiter > 0 && 
					stateMachineName.substring( 0, firstDelimiter ).equals( serviceName ) )
			{
				String newStateMachineName = stateMachineName.substring( firstDelimiter + 1 );
				sm.setName( newStateMachineName );
			}
		}
	}

	 public static void wrapWithColors( org.jts.jsidl.binding.ProtocolBehavior shell )
	 {
		 // add layout data if missing
		 jxStateMachine jxfsm = new jxStateMachine( shell );
		 jxfsm.convert();

		 for( StateMachine fsm : shell.getStateMachine() )
		 {
			 // add color to fsm
			 addColor( fsm.getMxCell(), false );

			 PseudoStartState pstate = fsm.getPseudoStartState();
			 MxCell pstateCell = pstate.getState().getMxCell();
			 addColor( pstateCell, true );     
			 MxCell ptransitionCell = pstate.getTransition().getMxCell();
			 addColor( ptransitionCell, false );

			 // update states
			 updateState( fsm.getState() );
		 }
	 }

	 public static org.jts.jsidl.binding.ProtocolBehavior getBaseBehaviorShellAsJaxb( com.u2d.generated.ProtocolBehavior pb )
	 {
 		// create base behavior shell
 		org.jts.jsidl.binding.ProtocolBehavior shell = 
 			org.jts.gui.Inheritance.getAsJaxb( pb );

 		// change mx cell colors of shell service
 		org.jts.gui.Inheritance.wrapWithColors( shell );
 		
 		return shell;
	 }

  public static ProtocolBehavior mergeBehaviors( ProtocolBehavior base, ProtocolBehavior derived, String baseServiceReferenceName ) {
    if( base == null || derived == null )
      return null;
    
   // start with base and move derived behavior into base, then return base
    
    List<StateMachine> bfsms = base.getStateMachine();
    List<StateMachine> dfsms = derived.getStateMachine();
    
    // update starts
    List<Start> bstarts = base.getStart();
    List<Start> dstarts = derived.getStart();
    
    
    for( int ii=0; ii < dstarts.size(); ii++ ) { 
    
      String dfsmName = dstarts.get(ii).getStateMachineName();
      
      StateMachine bfsm = getStateMachine( bfsms, dfsmName );
      
      if( bfsm != null ) {
        // find the right bstart and set state name
        for( int jj=0; jj < bstarts.size(); jj++ ) {
          String bfsmName = bstarts.get(jj).getStateMachineName();
          
          if( bfsmName.equals( bfsm.getName() ) )
            bstarts.get(jj).setStateName( getStateName( dstarts, dfsmName ) );
        }
      } else if( bfsm == null ) {
         // add dstart to bstart
         bstarts.add( dstarts.get(ii) );
      }  
    }


    // move isStateless
    base.setIsStateless( derived.isIsStateless() );

	for( int i = 0; i < dfsms.size(); i++ )
	{
		// current derived sm name
		String dfsmName = dfsms.get( i ).getName();
		
		// check to see if derived sm is in a base sm
		StateMachine bfsm = getStateMachine( bfsms, dfsmName );
		
		// when current sm found in base, update base
		if( bfsm != null )
		{
			updateBaseStateMachine( bfsm, dfsms.get( i ) );
		}
		// when current sm not in base, add as new sm to base
		else
		{
			bfsms.add( dfsms.get( i ) );
		}
	}

	// per JTS state machine naming convention,
	// the base will be missing the alias used for the current pb, add it here
	prependServiceName( baseServiceReferenceName, base );
    
    return base;
  }
  
  private static void updateBaseStateMachine( StateMachine base, StateMachine derived ) {
    if( base == null || derived == null )
      return;
    
    updateBaseStates( base.getState(),  derived.getState() );
  }
  
  
  private static void updateBaseStates( List<State> bstates, List<State> dstates ) {
    if( bstates == null || dstates == null )
      return;
  
    for( int ii=0; ii < dstates.size(); ii++ ) {
      
      State dstate = dstates.get(ii);
      State bstate = getState( bstates, dstate.getName() );

     
      // state
      if( bstate == null ) {
        bstates.add( dstate );
        continue;
      }
      
      // initial state
      String dinitialState = dstate.getInitialState();
      if( dinitialState != null && dinitialState.length() > 0  )
        bstate.setInitialState( dinitialState );
      
      // pseudostartstate
      bstate.setPseudoStartState( dstate.getPseudoStartState() );

	  // entry actions
	  if ((dstate.getEntry() != null) && (dstate.getEntry().getActionOrSendAction().size() > 0))
	  {
	      if (bstate.getEntry() == null) bstate.setEntry( new org.jts.jsidl.binding.Entry() );
		  for (int jjj=0; jjj < dstate.getEntry().getActionOrSendAction().size(); jjj++)
		  {
			Object daction = dstate.getEntry().getActionOrSendAction().get(jjj);
			Object baction = getAction( bstate.getEntry().getActionOrSendAction(), daction );

			// check for match.  If this is a unique action, add to the base state
			if( baction == null ) {
				bstate.getEntry().getActionOrSendAction().add( daction );
				continue;
			}
		  }
	  }


	  // exit actions
	  if ((dstate.getExit() != null) && (dstate.getExit().getActionOrSendAction().size() > 0))
	  {
	      if (bstate.getExit() == null) bstate.setExit( new org.jts.jsidl.binding.Exit() );
		  for (int jjj=0; jjj < dstate.getExit().getActionOrSendAction().size(); jjj++)
		  {
			Object daction = dstate.getExit().getActionOrSendAction().get(jjj);
			Object baction = getAction( bstate.getExit().getActionOrSendAction(), daction );

			// check for match.  If this is a unique action, add to the base state
			if( baction == null ) {
				bstate.getExit().getActionOrSendAction().add( daction );
				continue;
			}
		  }
	  }           
      // default state
      DefaultState ddstate = dstate.getDefaultState();
      DefaultState bdstate = bstate.getDefaultState();
      
      if( ddstate != null ) { 
        bstate.setDefaultState( ddstate );

        updateBaseTransitions( bstate.getDefaultState().getTransition(), ddstate.getTransition() );
        updateBaseDefaultTransitions( bstate.getDefaultState().getDefaultTransition(), ddstate.getDefaultTransition() );        
      }
     
        
      // substates  
      updateBaseStates( bstate.getState(),  dstate.getState() );
      
      // transitions
      updateBaseTransitions( bstate.getTransition(),  dstate.getTransition() );
      
      // default transitions
      updateBaseDefaultTransitions( bstate.getDefaultTransition(),  dstate.getDefaultTransition() );
    }
  }
  
  
  private static void updateBaseDefaultTransitions( List<DefaultTransition> bdtransitions, List<DefaultTransition> ddtransitions ) {
    if( bdtransitions == null || ddtransitions == null )
      return;
  
    for( int ii=0; ii < ddtransitions.size(); ii++ ) {
      
      DefaultTransition ddtransition = ddtransitions.get(ii);
      DefaultTransition bdtransition = getDefaultTransition( bdtransitions, ddtransition.getGuard() );
       
      if( bdtransition == null ) {
        bdtransitions.add( ddtransition );
        continue;
      }
    }
  }
  
  
  private static void updateBaseTransitions( List<Transition> btransitions, List<Transition> dtransitions ) {
    if( btransitions == null || dtransitions == null )
      return;
  
    for( int ii=0; ii < dtransitions.size(); ii++ ) {
      
      Transition dtransition = dtransitions.get(ii);
      Transition btransition = getTransition( btransitions, dtransition.getName(), dtransition.getParameter(), dtransition.getGuard() );
       
      if( btransition == null ) {
        btransitions.add( dtransition );
        continue;
      }
    }
  }
  
  
  private static Transition getTransition( List<Transition> transitions, String name, List<Parameter> params, Guard guard ) {
    
    for( int ii=0; ii < transitions.size(); ii++ ) {
      
      Transition transition = transitions.get(ii);
      
      // check name
      if( transition.getName().equals( name ) ) {
        
        // check parameter list
    	  List<Parameter> tparams = transition.getParameter();
    	  
    	  boolean tParamsEmpty = false;
    	  boolean paramsEmpty = false;
    	  
    	  if( tparams == null || tparams.size() == 0 )
    	  {
    		  tParamsEmpty = true;
    	  }
    	  
    	  if( params == null || params.size() == 0 )
    	  {
    		  paramsEmpty = true;
    	  }
    	  
    	  if( tParamsEmpty ^ paramsEmpty )
    	  {
    		  continue;
    	  }
    	  
    	  if( !( tParamsEmpty && paramsEmpty ) )
    	  {
    		  // First check that the number of parameters matches...
    		  if( params.size() != tparams.size() )
    		  {
    			  continue;
    		  }

    		  boolean found = true;
    		  for( Parameter p : params )
    		  {
    			  // Now check that each parameter matches
    			  if( getParameter( tparams, p ) == null )
				  {
    				  found = false;
    				  break;
				  }
    		  }
    		  
    		  // If we didn't find every single parameter, it's not a match.
    		  if( !found )
    		  {
    			  continue;
    		  }
    	  }
        
        // check guard
        Guard tguard = transition.getGuard();
        if( guard != null  && tguard != null ) {
          if( ! guard.getCondition().equals( tguard.getCondition() )  )
            continue;
        } else if( !( guard == null  && tguard == null ) )
          continue;
        
        return transition;
      }
    }
    
    return null;
  }
  
  
  private static DefaultTransition getDefaultTransition( List<DefaultTransition> dtransitions, Guard guard ) {
    
    for( int ii=0; ii < dtransitions.size(); ii++ ) {
      
      DefaultTransition dtransition = dtransitions.get(ii);
        
      // check guard
      Guard tguard = dtransition.getGuard();
      if( guard != null  && tguard != null ) {
        if( ! guard.getCondition().equals( tguard.getCondition() )  )
          continue;
      } else if( !( guard == null  && tguard == null ) )
        continue;
        
      return dtransition;
    }
    
    return null;
  }
  
  
  private static Parameter getParameter( List<Parameter> params, Parameter param ) {
    if( params == null || params.size() == 0 || param == null )
      return null;
      
    for( int ii=0; ii < params.size(); ii++ ) {
      Parameter pp = params.get(ii);
      
      if( pp.getType().equals( param.getType() )  && pp.getValue().equals( param.getValue() ) )
        return pp;
    }
    
    return null;
  }
  
  
  private static State getState( List<State> states, String name ) {
    if( states == null )
      return null;
    
    for( int ii=0; ii < states.size(); ii++ ) {
      
      State state = states.get(ii);
      
      if( state.getName().equals( name ) )
        return state;
        
    }
    
    return null;
  }
  
  private static Object getAction( List<Object> actions, Object child_action )
  {
      if ((actions == null) || (actions.size() == 0))
	     return null;

      for (int ii=0; ii < actions.size(); ii++) {
	     Object action = actions.get(ii);

		 if (action.equals( child_action ))
		     return action;
	  }

	  return null;
   }

  /**
   * This method will only be called when merging behaviors.  Per JTS state machine naming convention,
   * the stateMachine name will still contain the alias name of the state machines in the fsms
   * list.  We need to remove these before looking for the matching state machine name
   * @param fsms
   * @param stateMachineName
   * @return
   */
  private static StateMachine getStateMachine( List<StateMachine> fsms, String stateMachineName ) {
    if( fsms == null )
      return null;
      
    int index = stateMachineName.indexOf('.');
    
    if( index > 0 )
      stateMachineName = stateMachineName.substring( index + 1 );
    
    for( int ii=0; ii < fsms.size(); ii++ ) {
      
      String fsmName = fsms.get(ii).getName();

      // ignore case because some services might have incorrect names(based on service 
      // name, not the xml field alias name as it should be). By doing this, we are assuming
      // that the service name is the same as the inherits from alias name(disregarding case)
      if( fsmName.equalsIgnoreCase( stateMachineName ) )  
        return fsms.get(ii);
    }
    
    return null;
  }
  
  private static String getStateName( List<Start> starts,  String fsmName ) {
    
    for( int ii=0; ii<starts.size(); ii++ ) {
      Start start = starts.get(ii); 
      if( start.getStateMachineName().equals( fsmName ) )
        return start.getStateName();
    }
    
    return null;
  }
  
  private static void updateState( List<State> states ) {
    if( states == null )
      return;

    for( int ii=0; ii < states.size(); ii++ ) {
      State state = states.get(ii);
      
      // add color information
      MxCell stateCell = state.getMxCell();
      addColor( stateCell, false );
      
      PseudoStartState pstate = state.getPseudoStartState();
      if( pstate != null ) {  
        MxCell pstateCell = pstate.getState().getMxCell();
        addColor( pstateCell, true );
        MxCell ptransitionCell = pstate.getTransition().getMxCell();
        addColor( ptransitionCell, false );
      }
      
      // recurse
      List<State> substate = state.getState();
      if( substate != null && substate.size() > 0 )
        updateState( substate );
        
      // remove entry/exit actions
      state.setEntry(null);
      state.setExit(null);
      
      String value = stateCell.getValue();
      int index = value.indexOf(';');
      if( index > 0 )
        value = value.substring(0, index+1);
      stateCell.setValue( value );   
      
      // remove transitions
      List<Transition> transitions = state.getTransition();
      if( transitions != null )
      transitions.clear( );
      
      // remove default transitions
      List<DefaultTransition> dtransitions = state.getDefaultTransition();
      if( dtransitions != null )
       dtransitions.clear( );
       
       // remove default state's transitions and default transitions
       DefaultState dstate = state.getDefaultState();
       if( dstate != null ) {
         // add color information
        MxCell dcell = dstate.getMxCell();
        addColor( dcell, false );

         List<Transition> dstransitions = dstate.getTransition();
         if( dstransitions != null )
           dstransitions.clear();
          
         List<DefaultTransition> dsdtransitions = dstate.getDefaultTransition();
         if( dsdtransitions != null )
           dsdtransitions.clear();
       }
    }
  }

  private static void addColor( MxCell cell, boolean fill ){    
    String style = cell.getStyle();
      
    if( ! fill ) {
      if( ! style.contains("strokeColor=#BC1212;fontColor=#BC1212;") ) 
        style = style.concat("strokeColor=#BC1212;fontColor=#BC1212;");
    }
    else { 
      if( ! style.contains("fillColor=#BC1212;strokeColor=#BC1212;fontColor=#BC1212;") )
        style = style.concat("fillColor=#BC1212;strokeColor=#BC1212;fontColor=#BC1212;");
    }
    
    cell.setStyle( style );
  }
}
