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

import org.jts.jsidl.binding.Action;
import org.jts.jsidl.binding.Argument;

import java.util.Vector;
import java.util.List;

public class mxAction {
  public String name = null;
  public Vector<String> mxargs = null;
  public String type = null;
  
  public static final String TYPE_ENTRY = "entry";
  public static final String TYPE_EXIT = "exit";
  public static final String TYPE_TRANSITION = "transition";
  
  public Action convert() {
    Action action = new Action();
    action.setName( name );
    List args = action.getArgument();
    for(int ii=0; ii<mxargs.size(); ii++) {
      Argument arg = new Argument();
      arg.setValue( mxargs.get(ii) );
      args.add( arg );
    }
    return action;
  }
  
  public mxAction() {
     mxargs = new Vector();
  }
  
  public mxAction( String name, Vector mxargs, String type) {
     this.name = name;
     this.mxargs = mxargs;
     this.type = type;
  }
  
  public String toString() {
    
    String argStr = new String();    
    for(int ii=0; ii<mxargs.size(); ii++) {
       if( ii > 0) argStr = argStr.concat(", ");
       argStr = argStr.concat( mxargs.get(ii) );
    }
    
    if( type.equals( TYPE_ENTRY ))
      return "entry: "+name+ "( "+argStr+" )";
      
    else if( type.equals( TYPE_EXIT ))
      return "exit: "+name+ "( "+argStr+" )";
    
    else if( type.equals( TYPE_TRANSITION ))
       return name+ "( "+argStr+" )";
    
    else 
       return "unknownTypemxAction: "+name+ "( "+argStr+" ); ";
  }
}