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

package org.jts.gui.mxGraphtoJAXB.parser;

import org.junit.*;

import org.jts.gui.mxGraphtoJAXB.mxState;
import org.jts.gui.mxGraphtoJAXB.mxAction;
import org.jts.gui.mxGraphtoJAXB.mxParameter;

import java.io.ByteArrayInputStream;
import java.util.Vector;


/**
 *
 * @author Arfath
 */
public class StateParserTest {

    @Test
    public void parserTest() throws ParseException {
      String test = "stateName entry: init(); exit: finalize();\n"+
                         " entry: action1(arg1);\n"+
                         "exit: action2(arg1, arg2 );\n"+ 
                         "exit: action3( '5' );\n"+ 
                         " entry: action4( arg1, arg2, arg3 );\n"+ 
                         " exit: action5( '5', arg2, 'STR_LIT' )";
                         
      ByteArrayInputStream stream = new ByteArrayInputStream( test.getBytes() );
  
      StateParser parser = new StateParser( stream );
      parser.Input();
      
      Assert.assertEquals("incorrect value found", "stateName", parser.name);
      
      mxAction action = parser.entryActionList.get(0);
      Assert.assertEquals("incorrect type found", mxAction.TYPE_ENTRY, action.type);
      Assert.assertEquals("incorrect value found", "init", action.name);
      Assert.assertEquals(0, action.mxargs.size());
      
      action = parser.exitActionList.get(0);
      Assert.assertEquals("incorrect type found", mxAction.TYPE_EXIT, action.type);
      Assert.assertEquals("incorrect value found", "finalize", action.name);
      Assert.assertEquals(0, action.mxargs.size());
      
      action = parser.entryActionList.get(1);
      Assert.assertEquals("incorrect type found", mxAction.TYPE_ENTRY, action.type);
      Assert.assertEquals("incorrect value found", "action1", action.name);
      Assert.assertEquals(1, action.mxargs.size());
      Assert.assertEquals("incorrect value found", "arg1", action.mxargs.get(0));
      
      action = parser.exitActionList.get(1);
       Assert.assertEquals("incorrect type found", mxAction.TYPE_EXIT, action.type);
      Assert.assertEquals("incorrect value found", "action2", action.name);
      Assert.assertEquals(2, action.mxargs.size());
      Assert.assertEquals("incorrect value found", "arg1", action.mxargs.get(0));
      Assert.assertEquals("incorrect value found", "arg2", action.mxargs.get(1));
      
      action = parser.exitActionList.get(2);
      Assert.assertEquals("incorrect type found", mxAction.TYPE_EXIT, action.type);
      Assert.assertEquals("incorrect value found", "action3", action.name);
      Assert.assertEquals(1, action.mxargs.size());
      Assert.assertEquals("incorrect value found", "'5'", action.mxargs.get(0));
     
      action = parser.entryActionList.get(2);
      Assert.assertEquals("incorrect type found", mxAction.TYPE_ENTRY, action.type);
      Assert.assertEquals("incorrect value found", "action4", action.name);
      Assert.assertEquals(3, action.mxargs.size());
      Assert.assertEquals("incorrect value found", "arg1", action.mxargs.get(0));
      Assert.assertEquals("incorrect value found", "arg2", action.mxargs.get(1));
      Assert.assertEquals("incorrect value found", "arg3", action.mxargs.get(2));
      
      action = parser.exitActionList.get(3);
      Assert.assertEquals("incorrect type found", mxAction.TYPE_EXIT, action.type);
      Assert.assertEquals("incorrect value found", "action5", action.name);
      Assert.assertEquals(3, action.mxargs.size());
      Assert.assertEquals("incorrect value found", "'5'", action.mxargs.get(0));
      Assert.assertEquals("incorrect value found", "arg2", action.mxargs.get(1));
      Assert.assertEquals("incorrect value found", "'STR_LIT'", action.mxargs.get(2));
   }
   
   /*@Test(expected= ParseException.class) 
   public void numValueTest() throws ParseException { 
      String test = "type 5";  // number is not allowed. must be placed in single qoutes to mean constant literal
      ByteArrayInputStream stream = new ByteArrayInputStream( test.getBytes() );
  
      ParameterParser parser = new ParameterParser( stream );
      Vector <mxParameter>params = parser.Input();
   }*/
}
