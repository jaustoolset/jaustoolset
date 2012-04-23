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

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.List;
import java.util.ArrayList;
import java.io.ByteArrayInputStream;
import org.jts.gui.mxGraphtoJAXB.parser.DefaultPopTransitionParser;
import org.jts.gui.mxGraphtoJAXB.parser.ParseException;
import java.util.StringTokenizer;
import java.util.Vector;
import org.jts.gui.mxGraphtoJAXB.parser.StateParser;

import org.jts.jsidl.binding.Guard;
import org.jts.jsidl.binding.DefaultTransition;
import org.jts.jsidl.binding.Pop;
import org.jts.jsidl.binding.Argument;
import java.util.Vector;

public class mxDefaultPopTransition  extends mxDefaultTransition {
  public String interpretation = null;
  public String guard = null;
  public Vector <mxAction>actions = null;
  public mxAction endTransition = null;
  public Node transitionCell;
  
  /**
  default_transition =   
  element default_transition { 
    attribute interpretation { text }?,
    guard?,
    (internal | simple | push | pop),
    (action | send_action)*, 
    mx_cell?
  }
  **/
  public static List<mxDefaultPopTransition> getDefaultPopTransitions( Node transitionCell, NodeList cells ) throws ParseException {
    
    // get guard, actions and interpretation
    Node valueAttrib = transitionCell.getAttributes().getNamedItem("value");
    List<mxDefaultPopTransition> list = parseValueAttrib( valueAttrib.getNodeValue() );
    
    if( list != null ) {
      Node node = transitionCell.cloneNode(true);

     for(int ii=0; ii<list.size(); ii++) 
       list.get(ii).transitionCell = node;
    }
        
    // set type
    for(int ii=0; ii<list.size(); ii++) 
          list.get(ii).type = mxDefaultTransition.TYPE_DEFAULT_POP;
    
    return list;
  }
  
  public static List<mxDefaultPopTransition> parseValueAttrib( String value ) throws ParseException {
    
    StringBuffer newValue = new StringBuffer();
    // get guard conditions
    List<String> guards = new ArrayList<String>();
    StringTokenizer trTokens = new StringTokenizer(value, ";");
    while( trTokens.hasMoreElements() ) {
      String tr = trTokens.nextToken();
      
      tr = tr.trim();
      if( tr.length() == 0 ) continue;
      
      int start = tr.indexOf('[');
      int end = tr.indexOf(']');
      
      if( start != -1 && end != -1 ) {
        String guard = tr.substring( start+1, end );
        guards.add( guard );
        tr = tr.replace( guard, "");
      } else {
        guards.add(null);
      }
      
      newValue.append( tr +";" );
    }
    
    // get actions and interpretation
    if( newValue.length() > 0 ) {
      ByteArrayInputStream stream = new ByteArrayInputStream( newValue.toString().getBytes() );  
      DefaultPopTransitionParser parser = new DefaultPopTransitionParser( stream );
      List<mxDefaultPopTransition> tr = null;

      tr =  parser.Input();  // can throw a parse exception
      
      // add guards
      for( int ii=0; ii< tr.size(); ii++ ) 
        tr.get(ii).guard = guards.get(ii);
        
      return tr;  
    } else return null;
  }
  
  public DefaultTransition convert() {
    DefaultTransition tr = new DefaultTransition();
    
    // guard
    if( guard != null ) {
      Guard gg = new Guard();
      gg.setCondition( guard );
      tr.setGuard( gg );
    }
    
    // actions
    List jxActions = tr.getActionOrSendAction();
    for(int ii=0; ii<actions.size(); ii++) {
      jxActions.add( actions.get(ii).convert() );
    }
    
    // end transition
    if( endTransition != null ) {
      Pop pp = new Pop();
      pp.setTransition( endTransition.name );
      
      List argList = pp.getArgument();
      for( int ii=0; ii< endTransition.mxargs.size(); ii++) {
        String arg = endTransition.mxargs.get(ii);
        Argument jxarg = new Argument();
        jxarg.setValue( arg );
        argList.add( jxarg );
      }
      tr.setPop( pp );
    } else {
      tr.setPop( new Pop() );
    }
    
    // mx_cell
    tr.setMxCell( mxCell.convert( transitionCell ) );
    
    return tr;
  }
  
  
  /******************************************************************/
  public mxDefaultPopTransition() {
     actions = new Vector();
  }
  
    public mxDefaultPopTransition(String interpretation, String guard, Vector actions, mxAction endTransition) {
     this.interpretation = interpretation;
     this.guard = guard;
     this.actions = actions; 
     this.endTransition = endTransition;
     super.type = TYPE_DEFAULT_POP;
  }
  
  public String toString() {
    
    String actionStr = new String();
    for(int ii=0; ii<actions.size(); ii++) {
       if( ii > 0) actionStr = actionStr.concat(", ");
       actionStr = actionStr.concat( actions.get(ii).toString() );
    }
    
    String et = null;
    if ( endTransition != null )
      et = endTransition.toString();
    
    return "default_pop_transition: ["+guard+"]/"+actionStr+" {"+et+"};   // "+interpretation;
  }
  /******************************************************************/
}