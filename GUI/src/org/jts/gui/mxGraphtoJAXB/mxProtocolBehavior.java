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

import java.util.Vector;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.jts.gui.mxGraphtoJAXB.parser.ParseException;

import org.jts.jsidl.binding.Start;
import org.jts.jsidl.binding.StateMachine;
import org.jts.jsidl.binding.ProtocolBehavior;

public class mxProtocolBehavior {
  public static HashMap <Integer, List>cellMapKeyParent = null;
  private NodeList cells = null;
  
  public String name = null;
  public String isStateless = null;
  public String start = null;
  public String interpretation = null;
  public Vector<mxState> state = null;
  
  
  /** Takes as input a hash map of the mxGraph data with parent id as key and a list of child cells as value
  */
  public mxProtocolBehavior( NodeList cells ) {
    this.cells = cells;
    createMap( cells );  
  }
  
  public ProtocolBehavior convert() throws ParseException {  
    
    ProtocolBehavior pb = new ProtocolBehavior();
    
    // add state machines
    List<mxStateMachine> mxfsms = getStateMachines( );
    
    List<StateMachine> jxfsms = pb.getStateMachine();
    for( int ii=0; ii<mxfsms.size(); ii++ ) 
      jxfsms.add( mxfsms.get(ii).convert() );

    // compute is_stateless
    pb.setIsStateless(Boolean.TRUE);
    for( int ii=0; ii<mxfsms.size(); ii++ ) { 
      if( mxfsms.get(ii).isStateless.equals("false")) {
        pb.setIsStateless(Boolean.FALSE);
        break;
      }
    }
    
    // compute start
    List<Start> startList = pb.getStart();
    for( int ii=0; ii<mxfsms.size(); ii++ ) {
      mxStateMachine mxsm = mxfsms.get(ii);
      Start start = new Start();
      start.setStateMachineName( mxsm.name );
      start.setStateName( mxsm.start ); 
      startList.add(start);
    }
    
    return pb;
  }
  
  private void createMap( NodeList cells ) {
      cellMapKeyParent = new HashMap<Integer, List>();

      for(int ii=0; ii< cells.getLength(); ii++) {
        Node cell = cells.item(ii);
        Node parent = cell.getAttributes().getNamedItem("parent");
        
        if( parent == null ) continue;
        
        Integer key = Integer.valueOf( parent.getNodeValue() );
        List<Node> value = null;
        
        if( (value = cellMapKeyParent.get(key)) != null ) {
           value.add( cell );
        }
        else {
           value = new ArrayList<Node>();
           value.add( cell );
           cellMapKeyParent.put( key, value );
        }
      }
    }
    
    private List<mxStateMachine> getStateMachines() throws ParseException {
      List<mxStateMachine> mxfsms = new ArrayList<mxStateMachine>();
      
      for(int ii=0; ii< cells.getLength(); ii++) {
        Node cell = cells.item(ii);
        Node style = cell.getAttributes().getNamedItem("style");
        
        if( style == null ) continue;
        
        String value = style.getNodeValue();
        if( value.startsWith("fsmStyle=finiteStateMachine;") || value.startsWith("finiteStateMachine") ) {
          Node id = cell.getAttributes().getNamedItem("id");
          String vv = id.getNodeValue();
          
          mxfsms.add( new mxStateMachine( cell, cells ) );
        }
      }
      
      return mxfsms;
    }
}