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

import org.jts.jsidl.binding.MxPoint;


public class mxPoint {

  /**
  mx_point = 
  element mx_point {
    attribute as { xsd:string },
    attribute x { xsd:float },
    attribute y { xsd:float }
  }
  **/
  public static MxPoint convert( Node cell ) {
     MxPoint jxPoint = new MxPoint();
     NamedNodeMap map = cell.getAttributes();
     
     // as
     Node attrib = map.getNamedItem("as");
     String value = null;
     if( attrib != null ) {
      value = attrib.getNodeValue();
      jxPoint.setAs( value );
    }
     
     // x 
    attrib = map.getNamedItem("x");
    if( attrib != null ) {
      value = attrib.getNodeValue();
      jxPoint.setX( Float.parseFloat( value ) );
    }
     
     // y
     attrib = map.getNamedItem("y");
    if( attrib != null ) {
      value = attrib.getNodeValue();
      jxPoint.setY( Float.parseFloat( value ) );
    }
    
    return jxPoint;  
  }
}