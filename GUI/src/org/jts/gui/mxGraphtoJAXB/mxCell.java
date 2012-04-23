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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.DOMException;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.ByteArrayInputStream;

import org.jts.jsidl.binding.MxCell;
import org.jts.jsidl.binding.MxGeometry;
import org.jts.jsidl.binding.MxPoint;
import org.jts.jsidl.binding.MxArray;


public class mxCell {

   /**
  
  mx_cell = 
  element mx_cell {
    attribute id { xsd:unsignedInt },
    attribute parent { xsd:unsignedInt }?,
    attribute source { xsd:unsignedInt }?,
    attribute target { xsd:unsignedInt }?,
    attribute style { xsd:string },
    #attribute value { xsd:string }, value is already contained in the encapsulating element
    attribute edge { xsd:unsignedInt }?,
    attribute vertex { xsd:unsignedInt }?,
    mx_geometry?
  }
   
  **/
  public static MxCell convert( Node cell ) {
    MxCell jxCell = new MxCell();
	
	if( cell == null )
	{
		return jxCell;
	}
	
    NamedNodeMap map = cell.getAttributes(); 
    
    // id
    Node attrib = map.getNamedItem("id");
    String value = attrib.getNodeValue();
    jxCell.setId( Long.parseLong( value ) );
    
    // parent
    attrib = map.getNamedItem("parent");
    if( attrib != null ) {
      value = attrib.getNodeValue();
      jxCell.setParent( Long.parseLong( value ) );
    }
    
    // source
    attrib = map.getNamedItem("source");
    if( attrib != null ) {
      value = attrib.getNodeValue();
      jxCell.setSource( Long.parseLong( value ) );
    }
    
    // target
    attrib = map.getNamedItem("target");
    if( attrib != null ) {
      value = attrib.getNodeValue();
      jxCell.setTarget( Long.parseLong( value ) );
    }
    
    // style
    attrib = map.getNamedItem("style");
    if( attrib != null ) {
      value = attrib.getNodeValue();
      jxCell.setStyle( value );
    }
    
    // edge
    attrib = map.getNamedItem("edge");
    if( attrib != null ) {
      value = attrib.getNodeValue();
      jxCell.setEdge( Long.parseLong( value ) );
    }
    
    // vertex
    attrib = map.getNamedItem("vertex");
    if( attrib != null ) {
      value = attrib.getNodeValue();
      jxCell.setVertex( Long.parseLong( value ) );
    }
    
    // value
    attrib = map.getNamedItem("value");
    if( attrib != null ) {
      value = attrib.getNodeValue();
      jxCell.setValue( value );
    }
    
    // mx_geometry
    NodeList list = cell.getChildNodes();
    for(int ii=0; ii< list.getLength(); ii++) {
       Node node = list.item(ii);
       
       if( node.getNodeName().equals("mxGeometry"))
         jxCell.setMxGeometry( mxGeometry.convert( node ));
    }
    
    return jxCell;
  }

}