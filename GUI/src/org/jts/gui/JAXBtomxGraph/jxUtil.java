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

import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;  
import com.sun.xml.bind.marshaller.CharacterEscapeHandler;

import java.awt.Point;


public class jxUtil {

  private static JAXBContext jsidlJc = null;
  private static JAXBContext mxGraphJc = null;
  private static Marshaller jsidlMarshaller = null;
  private static Marshaller mxGraphMarshaller = null;
  private static Unmarshaller jsidlUnmarshaller = null;
  private static Unmarshaller mxGraphUnmarshaller = null;
  
  /* tbd: put these strings in a property file or something like that */
  public static final String JSIDL_URI = "urn:jaus:jsidl:1.0";
  public static final String MXGRAPH_URI = "urn:jaus:mxGraph:0.1";
  
  private static long id = 1;
  private static int pointOffset = 0;
  public static final int POINT_OFFSET = 110;
  
  protected static Marshaller getJSIDLMarshaller() throws JAXBException {
    
    if( jsidlJc == null )
      jsidlJc = JAXBContext.newInstance( "org.jts.jsidl.binding" );
    if( jsidlMarshaller == null )
      jsidlMarshaller = jsidlJc.createMarshaller();
          
    return jsidlMarshaller;
  }
  
  protected static Marshaller getMxGraphMarshaller() throws JAXBException  {
    if( mxGraphJc == null )
      mxGraphJc = JAXBContext.newInstance( "org.jts.mxGraph.binding" );
    if( mxGraphMarshaller == null ) {
      // set up a marshaller with a custom character encoding handler
      mxGraphMarshaller = mxGraphJc.createMarshaller();
      //mxGraphMarshaller.setProperty( "jaxb.encoding", "UTF-8" );
      //mxGraphMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
      //mxGraphMarshaller.setProperty(
      //    CharacterEscapeHandler.class.getName(),
      //    new CustomCharacterEscapeHandler() );
    }
          
    return mxGraphMarshaller;
  }
  
  protected static Unmarshaller getMxGraphUnmarshaller() throws JAXBException  {
    
    if( mxGraphJc == null )
      mxGraphJc = JAXBContext.newInstance( "org.jts.mxGraph.binding" );
    if( mxGraphUnmarshaller == null ) {
          mxGraphUnmarshaller = mxGraphJc.createUnmarshaller();
    }      
          
    return mxGraphUnmarshaller;
  }
  
  protected static Unmarshaller getJSIDLUnmarshaller() throws JAXBException  {
    
    if( jsidlJc == null )
      jsidlJc = JAXBContext.newInstance( "org.jts.jsidl.binding" );
    if( jsidlUnmarshaller == null )
          jsidlUnmarshaller = jsidlJc.createUnmarshaller();
          
    return jsidlUnmarshaller;
  }
  
  protected static void seedId( long sid ) {
    if( id <= sid ) 
      id = sid + 1;
  }
  
  protected static long getNextId() {
    return ++id;
  }
  
  /*protected static Point getNextPointOffset() {
    pointOffset += POINT_OFFSET;
  
    return new Point( pointOffset, pointOffset );
  }*/
  
  protected static void resetPointOffset() {
    pointOffset = 0;
  }
}