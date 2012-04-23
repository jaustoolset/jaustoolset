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
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.ByteArrayInputStream;

class ProtocolBehaviorTree {
  
  NodeList cells = null;
  HashMap<Integer, List> cellMapKeyParent = null;
  
  

  ProtocolBehaviorTree( NodeList cells ) {
    this.cells = cells;
    
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
  
  /* for entire tree, set depth = 1 and parent = 1*/
  String space = "  "; 
  String toString( int depth, Integer parent ) {
    String str = null;
    
    // root cells
    List<Node> cells = cellMapKeyParent.get( parent );
    
    if(cells == null )
        return str;
        
    depth++;
    for( int ii=0; ii<cells.size(); ii++ ) {
       Node cell = cells.get(ii).getAttributes().getNamedItem("value");
       Node cellStyle = cells.get(ii).getAttributes().getNamedItem("style");
       Node cellEdge = cells.get(ii).getAttributes().getNamedItem("edge");
       
       if ( cellEdge == null ) {
         for(int jj=0; jj<depth; jj++)
           str = str + space; 
           
         String value =  cell.getNodeValue();
         if( value.length() == 0 )
           str = str + "pseudoStartState" +"\n";
         else {
          
           
           /*if( cellStyle.getNodeValue().contains("state") ) {
             try {
                 ByteArrayInputStream stream = new ByteArrayInputStream( value.getBytes() );
                 org.jts.gui.mxGraphtoJAXB.parser.StateParser parser = new org.jts.gui.mxGraphtoJAXB.parser.StateParser( stream );
                 mxState mxstate = parser.Input();         
                 
                 org.jts.jsidl.binding.State jxState = mxstate.convert( mxState );
                 str = str + ">>" + jxState.getName()+"\n";
                 
              } catch( org.jts.gui.mxGraphtoJAXB.parser.ParseException pe ) {
                System.out.println(pe.getMessage());
              }
            } 
           
           else
             str = str + value +"\n";*/
         }
         
         Node cellId = cells.get(ii).getAttributes().getNamedItem("id");
         Integer cellIdValue = Integer.valueOf( cellId.getNodeValue() );
       
         str = str + toString( depth, cellIdValue );
       }
    }
    
    return str;
  }
}
