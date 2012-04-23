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
import java.util.ArrayList;

import org.jts.gui.mxGraphtoJAXB.parser.ParseException;
import javax.swing.JOptionPane;
import org.jts.jsidl.binding.Transition;


public class mxTransition {
  public String type = null;
  public static final String TYPE_SIMPLE = "simple";
  public static final String TYPE_INTERNAL = "internal";
  public static final String TYPE_PUSH = "push";
  public static final String TYPE_POP = "pop";
  
  public mxTransition() { }
  
  public static List<mxTransition> getTransitions( Node stateCell, NodeList cells ) throws ParseException {
    // get id of parent cell
    Node idAttrib = stateCell.getAttributes().getNamedItem("id");
    String id = idAttrib.getNodeValue() ;
    
    List mxtransitions = new ArrayList<mxTransition>();
    for(int ii=0; ii< cells.getLength(); ii++) {
    	Node cell = cells.item(ii);
    	Node sourceAttrib = cell.getAttributes().getNamedItem("source");

    	if( sourceAttrib == null ) continue;
    	  	
    	String source = sourceAttrib.getNodeValue() ;
  	
    	// add transition
    	if( source.equals(id) ) {
    	    Node styleAttrib = cell.getAttributes().getNamedItem("style");
    	    
    	    if( styleAttrib == null ) continue;
    	  	
    	    String style = styleAttrib.getNodeValue() ;
    	    
    	    if( style.contains("simpleTransition")) {
            List<mxSimpleTransition> mxts = mxSimpleTransition.getSimpleTransitions( cell, cells );  // each cell may represent multiple transitions
            for( int jj=0; jj<mxts.size(); jj++ )
               mxtransitions.add( mxts.get(jj) );
          } else if( style.contains("internalTransition")) {
            List<mxInternalTransition> mxts = mxInternalTransition.getInternalTransitions( cell, cells );  // each cell may represent multiple transitions
            for( int jj=0; jj<mxts.size(); jj++ )
               mxtransitions.add( mxts.get(jj) );
          } else if( style.contains("pushTransition")) {
            List<mxPushTransition> mxts = mxPushTransition.getPushTransitions( cell, cells );  // each cell may represent multiple transitions
            for( int jj=0; jj<mxts.size(); jj++ )
               mxtransitions.add( mxts.get(jj) );
          } else if( style.contains("popTransition") ) {
            List<mxPopTransition> mxts = mxPopTransition.getPopTransitions( cell, cells );  // each cell may represent multiple transitions
            for( int jj=0; jj<mxts.size(); jj++ )
               mxtransitions.add( mxts.get(jj) );
          } else if( style.contains("defaultSimpleTransition")) {
            ;  // do nothing
          } else if( style.contains("defaultInternalTransition")) {
            ; // do nothing
          } else if( style.contains("defaultPushTransition")) {
            ; // do nothing
          } else if( style.contains("defaultPopTransition") ) {
            ; // do nothing
          } else {
            Node valueAttrib = cell.getAttributes().getNamedItem("value");
    	    
    	      if( valueAttrib == null ) continue;
    	  	
    	      String value = valueAttrib.getNodeValue() ;
            
            JOptionPane.showMessageDialog(null, "Delete and redraw transition: "+value, "Malformed Transition", JOptionPane.ERROR_MESSAGE);
          }
    	}
    }
    
    return mxtransitions;
  }
  
  public Transition convert() {
    
    if( type == TYPE_SIMPLE ) {
      return ((mxSimpleTransition) this).convert();
    } else if ( type == TYPE_INTERNAL ) {
      return ((mxInternalTransition) this).convert();
    } else if ( type == TYPE_PUSH ) {
      return ((mxPushTransition) this).convert();
    } else if ( type == TYPE_POP ) {
      return ((mxPopTransition) this).convert();
    } else
      return null;
  }
}