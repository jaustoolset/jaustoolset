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
import org.jts.gui.mxGraphtoJAXB.parser.PopTransitionParser;
import org.jts.gui.mxGraphtoJAXB.parser.ParseException;
import java.util.StringTokenizer;
import org.jts.gui.mxGraphtoJAXB.parser.StateParser;

import org.jts.jsidl.binding.Transition;
import org.jts.jsidl.binding.Guard;
import org.jts.jsidl.binding.Simple;
import org.jts.jsidl.binding.Pop;
import org.jts.jsidl.binding.Parameter;
import org.jts.jsidl.binding.Argument;
import java.util.Vector;

public class mxPopTransition  extends mxTransition {
  public String name = null;
  public String interpretation = null;
  public Vector <mxParameter>params = null;
  public String guard = null;
  public Vector <mxAction>actions = null;
  public mxAction endTransition = null;
  public Node transitionCell = null;
  
  public static List<mxPopTransition> getPopTransitions( Node transitionCell, NodeList cells ) throws ParseException {
    
    // get name, guard, params, actions and interpretation
    Node valueAttrib = transitionCell.getAttributes().getNamedItem("value");
    List<mxPopTransition> list = parseValueAttrib( valueAttrib.getNodeValue() );
    
    if( list != null ) {
      Node node = transitionCell.cloneNode(true);

     for(int ii=0; ii<list.size(); ii++) 
       list.get(ii).transitionCell = node;
    }
    
    // set type
    for(int ii=0; ii<list.size(); ii++) 
          list.get(ii).type = mxTransition.TYPE_POP;
    
    return list;
  }
  
  public static List<mxPopTransition> parseValueAttrib( String value ) throws ParseException {
    
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
    
    // get name, params, actions and interpretation
    if( newValue.length() > 0 ) {
      ByteArrayInputStream stream = new ByteArrayInputStream( newValue.toString().getBytes() );  
      PopTransitionParser parser = new PopTransitionParser( stream );
      List<mxPopTransition> tr =  null;

      tr = parser.Input();  // can throw a parse exception

      // add guards
      for( int ii=0; ii< tr.size(); ii++ ) 
        tr.get(ii).guard = guards.get(ii);
        
      return tr;  
    } else return null;
  }
  
  /**
  transition =
  element transition {
    attribute name { identifier_ns },
    attribute interpretation { text }?,
    parameter*,
    guard?,
    (internal | simple | push | pop),
    (action | send_action)*, 
    mx_cell?
  }
  **/
  public Transition convert() {
    Transition tr = new Transition();
    
    // name
    tr.setName( name );
    
    // parameters
    List<Parameter> jxParams = tr.getParameter();
    for(int ii=0; ii<params.size(); ii++) {
      jxParams.add( params.get(ii).convert() );
    }
    
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
    
    // mxcell
    tr.setMxCell( mxCell.convert( transitionCell ) );
    
    return tr;
  }
  
  /******************************************************************************/
  public mxPopTransition() {
     params = new Vector();
     actions = new Vector();
  }
  
    public mxPopTransition(String name, String interpretation, Vector params, String guard, Vector actions, mxAction endTransition) {
     this.name = name;
     this.interpretation = interpretation;
     this.params = params;
     this.guard = guard;
     this.actions = actions; 
     this.endTransition = endTransition;
     super.type = TYPE_POP;
  }
  
  public String toString() {
    String paramStr = new String();
    for(int ii=0; ii<params.size(); ii++) {
       if( ii > 0) paramStr = paramStr.concat(", ");
       paramStr = paramStr.concat( params.get(ii).toString() );
    }
    
    String actionStr = new String();
    for(int ii=0; ii<actions.size(); ii++) {
       if( ii > 0) actionStr = actionStr.concat(", ");
       actionStr = actionStr.concat( actions.get(ii).toString() );
    }
    
    String et = null;
    if ( endTransition != null )
      et = endTransition.toString();
    
    return name+"("+paramStr+")["+guard+"]/"+actionStr+" {"+et+"};   // "+interpretation;
  }
  /**************************************************************************/
}