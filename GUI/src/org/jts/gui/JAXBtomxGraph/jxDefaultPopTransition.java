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

import org.jts.jsidl.binding.DefaultTransition;
import org.jts.jsidl.binding.Guard;
import org.jts.jsidl.binding.Action;
import org.jts.jsidl.binding.Argument;
import org.jts.jsidl.binding.Pop;
import org.jts.jsidl.binding.EndState;

import org.jts.mxGraph.binding.MxCell;
import org.jts.mxGraph.binding.ObjectFactory;


public class jxDefaultPopTransition {
  
  private DefaultTransition dtransition = null;
  private long parentId = 0;
  private long sourceId = 0;
  
  public jxDefaultPopTransition( DefaultTransition dtransition, long parentId, long sourceId ) { 
   this.dtransition = dtransition;
   this.parentId = parentId;
   this.sourceId = sourceId;
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
 
pop = 
  element pop {
    attribute transition { identifier }?,
    attribute interpretation { text }?, 
    argument*
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
      //tcell.setTarget( targetId );
      // style { xsd:string },
      tcell.setStyle("defaultPopTransition;");
      // value { xsd:string }
      tcell.setValue( getValue( dtr ) );
      // edge { xsd:unsignedInt }?,
      tcell.setEdge( new Long(1) );
      // vertex { xsd:unsignedInt }?,
      
      /**
            <mxGeometry as="geometry" height="100.0" relative="1" width="100.0"
        x="1.0" y="10.0">
        <mxPoint as="sourcePoint" x="550.0" y="460.0"/>
        <mxPoint as="targetPoint" x="620.0" y="290.0"/>
        <mxPoint as="offset" y="10.0"/>
      </mxGeometry>
      
      **/
      
      org.jts.jsidl.binding.MxGeometry tgeom = objf.createMxGeometry();
      tgeom.setAs("geometry");
      tgeom.setHeight( 20f );
      tgeom.setWidth( 20f );
     // tgeom.setRelative( new Long(1) );
     tgeom.setX( 1f );
     tgeom.setY( 10f );
     tgeom.setRelative( new Long(1) );
     
     List<org.jts.jsidl.binding.MxPoint> points = tgeom.getMxPoint();
     org.jts.jsidl.binding.MxPoint sourcePoint = objf.createMxPoint();
     sourcePoint.setAs("sourcePoint");
     sourcePoint.setX( 40f );
     sourcePoint.setY( 0f );
     points.add(sourcePoint);
     org.jts.jsidl.binding.MxPoint targetPoint = objf.createMxPoint();
     targetPoint.setAs("targetPoint");
     targetPoint.setX( 80f );
     targetPoint.setY( 0f );
     points.add(targetPoint);
     org.jts.jsidl.binding.MxPoint offset = objf.createMxPoint();
     offset.setAs("offset");
     offset.setX( 0f );
     offset.setY( 10f );
     points.add(offset);
     
     tcell.setMxGeometry( tgeom );
      
      return tcell;
  }
  
  /**
  [guard]/action(arg1, .....), ....{end_transition(arg1, ...)};
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
     
     // end transition
     Pop pop = dtr.getPop();
     String transition = pop.getTransition();
     if( transition != null ) {
       buf.append( " { "+transition );
       List<Argument> args = pop.getArgument();
       buf.append( "( " );
       if( args != null && args.size() > 0 ) {
         for(int ii=0; ii<args.size(); ii++) {
             Argument arg = args.get(ii);
             buf.append( arg.getValue()+" " );
             if( ii < args.size() -1 ) buf.append( ", " );
           }
       }
       buf.append( " )" );
       buf.append( " }" );
     }
     
     return buf.toString();
  }
}