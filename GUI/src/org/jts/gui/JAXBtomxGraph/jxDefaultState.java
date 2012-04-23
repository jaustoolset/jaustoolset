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
import org.jts.jsidl.binding.DefaultState;
import org.jts.jsidl.binding.Transition;
import org.jts.jsidl.binding.DefaultTransition;

import org.jts.mxGraph.binding.MxCell;
import org.jts.mxGraph.binding.ObjectFactory;

public class jxDefaultState {
  private DefaultState dstate = null;
  private long parentId = 0;
 
  public jxDefaultState( State state ) {
    dstate = state.getDefaultState();
    this.parentId = state.getMxCell().getId();
  }
  
  public jxDefaultState( DefaultState dState )
  {
	  dstate = dState;
	  this.parentId = dState.getMxCell().getId();
  }
  
  protected void seedId() {
    if( dstate != null ) {
      org.jts.jsidl.binding.MxCell cell = dstate.getMxCell();
      if( cell != null ) 
        jxUtil.seedId( cell.getId() );
    }
  }
  
  /**
  default_state = 
  element default_state {
     attribute interpretation { text }?,
     transition*,
     default_transition*, 
     mxCell?
  }
  **/
  public List<MxCell> convert() {
    List<MxCell> list = new ArrayList<MxCell>();
    
    if( dstate == null )
      return list;
      
     org.jts.jsidl.binding.MxCell cell = dstate.getMxCell();
          
      if( cell == null ) {
        cell = createCell( dstate );
        dstate.setMxCell( cell );
      }
          
      // convert default state element
      list.add( jxCell.convert( cell )  );  
      
    return list;
  }
  
  
  private org.jts.jsidl.binding.MxCell createCell( DefaultState ds ) {
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
      cell.setStyle("defaultState;");
      // value { xsd:string }
      cell.setValue( "default_state" );  
      // edge { xsd:unsignedInt }?,
      // vertex { xsd:unsignedInt }?,
      cell.setVertex( new Long(1));
                     
      org.jts.jsidl.binding.MxGeometry geom = objf.createMxGeometry();
      cell.setMxGeometry( geom );
      geom.setAs("geometry");
     geom.setHeight( 80f );
     geom.setWidth( 80f );
     
     geom.setX( 100f );
     geom.setY( 0f );
      
      return cell;
  }
}