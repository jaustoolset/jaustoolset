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

import org.jts.jsidl.binding.DefaultState;
import org.jts.jsidl.binding.State;
import org.jts.jsidl.binding.DefaultTransition;
import org.jts.jsidl.binding.Guard;
import org.jts.jsidl.binding.Action;
import org.jts.jsidl.binding.Argument;
import org.jts.jsidl.binding.Push;
import org.jts.jsidl.binding.Simple;
import org.jts.jsidl.binding.EndState;

import org.jts.mxGraph.binding.MxCell;
import org.jts.mxGraph.binding.ObjectFactory;


public class jxDefaultPushTransition {
  
  private DefaultTransition dtransition = null;
  private long parentId = 0;
  private long sourceId = 0;
  private long targetId = 0;
  private Object sourceState = null;
  
  public jxDefaultPushTransition( DefaultTransition dtransition, long parentId, long sourceId, long targetId, Object sourceState ) { 
   this.dtransition = dtransition;
   this.parentId = parentId;
   this.sourceId = sourceId;
   this.targetId = targetId;
   this.sourceState = sourceState;
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
 
push = 
  element push {
    attribute interpretation { text }?,
    end_state, 
    simple?
  }
  */
  public MxCell convert() {
    org.jts.jsidl.binding.MxCell cell = dtransition.getMxCell();
          
    if( cell == null ) {
      cell = createCell( dtransition );
      dtransition.setMxCell( cell );
    }
    
    return jxCell.convert( cell );
  }
  
  private org.jts.jsidl.binding.MxCell createCell( DefaultTransition dtr ) {
    org.jts.jsidl.binding.ObjectFactory objf = new
                                org.jts.jsidl.binding.ObjectFactory();
    org.jts.jsidl.binding.MxCell tcell = objf.createMxCell();
    
      // id { xsd:unsignedInt },
      tcell.setId( new Long(jxUtil.getNextId()) );
      // parent { xsd:unsignedInt }?,
      tcell.setParent( new Long(parentId) );
      // source { xsd:unsignedInt }?,
      tcell.setSource( sourceId );
      // target { xsd:unsignedInt }?,
      tcell.setTarget( targetId );
      // style { xsd:string },
      tcell.setStyle("defaultPushTransition;");
      // value { xsd:string }
      tcell.setValue( getValue( dtr ) );
      // edge { xsd:unsignedInt }?,
      tcell.setEdge( new Long(1) );
      // vertex { xsd:unsignedInt }?,
      
      org.jts.jsidl.binding.MxGeometry tgeom = objf.createMxGeometry();
      tgeom.setAs("geometry");
      tgeom.setHeight( 0f );
      tgeom.setWidth( 0f );
     tgeom.setX( 0f );
     tgeom.setY( 0f );
     tgeom.setRelative( new Long(1) );
     
     tcell.setMxGeometry( tgeom );
      
      return tcell;
  }
  
  /**
  [guard]/action(arg1, .....), ....{end_state};
  **/
  private String getValue( DefaultTransition dtr ) {
     StringBuffer buf = new StringBuffer();
     
     // guard
     Guard guard = dtr.getGuard();
     if( guard != null ) {
       buf.append( "[ " );
       buf.append( guard.getCondition() );
       buf.append( " ]" );
     }
     
     buf.append( " / " );
     
     // actions
     List actions = dtr.getActionOrSendAction();
     if( actions != null && actions.size() > 0 ) {
       for( int ii=0; ii<actions.size(); ii++ ) {
         Action action = (Action)actions.get(ii);
         buf.append( action.getName() );
         
         List<Argument> args = action.getArgument();
         buf.append( "( " );
         if( args != null && args.size() > 0 ) {
           
           for(int jj=0; jj<args.size(); jj++) {
             Argument arg = args.get(jj);
             buf.append( arg.getValue()+" " );
             if( jj < args.size() -1 ) buf.append( ", " );
           }
           
         }
         buf.append( " )" );
         if( ii < actions.size() -1 ) buf.append( ", " );
       }
     }
     
     // endstate
     Push push = dtr.getPush();
     if( push != null ){
       
       
       Simple simple = push.getSimple();
       if( simple != null ) {
         buf.append( "{ " ); 
         EndState es = simple.getEndState();
         if( es == null ) {
           // parent state
        	 String name = "Unknown";
			 if( sourceState instanceof State )
			 {
			   name = ( ( State )sourceState ).getName();
			 }
			 else if( sourceState instanceof DefaultState )
			 {
			   name = "Default State";
			 }

           throw new RuntimeException("Explicit final end state name is required for default push transition under state "+ name );
         } else {
           buf.append( es.getState() );
         }
         buf.append( " }" );
       }
     }
     buf.append( " ;" );
     
     return buf.toString();
  }
}