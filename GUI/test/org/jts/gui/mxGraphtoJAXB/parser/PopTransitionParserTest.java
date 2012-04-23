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

import org.jts.gui.mxGraphtoJAXB.mxParameter;
import org.jts.gui.mxGraphtoJAXB.mxAction;
import org.jts.gui.mxGraphtoJAXB.mxPopTransition;

import java.io.ByteArrayInputStream;
import java.util.Vector;


/**
 *
 * @author Arfath
 */
public class PopTransitionParserTest {

    @Test
    public void parserTest() throws ParseException {
      String test =  "trigger1;\n" +
                          "trigger2()/{};\n" +
                          "trigger3()[]/{endTransition()};\n" + 
                          "trigger4()[]/{endTransition(arg1)};\n" + 
                          "namespace.trigger5(type1 arg1)[]/action1(arg1){endTransition('6')};\n" +
                          "trigger6(type1 arg1, type2 arg2)[]/action1(arg1), action2(arg1, arg2){endTransition(arg1, arg2)};\n" +
                          "trigger7(type1 arg1, type2 '8')[]/action1('9'), action2('LIT', arg2){endTransition('6', arg2)};\n"+
                          "trigger8/;\n"+
                          "trigger9[];\n"+
                          "trigger10/{endTransition()};";
                      
      ByteArrayInputStream stream = new ByteArrayInputStream( test.getBytes() );
  
      PopTransitionParser parser = new PopTransitionParser( stream );
      Vector <mxPopTransition>transitions = parser.Input();
      
      mxPopTransition tr = transitions.get(0);
      Assert.assertEquals("incorrect value found", "trigger1", tr.name);
      Assert.assertEquals(0, tr.params.size());
      Assert.assertNull("incorrect value found", tr.guard);
      Assert.assertEquals(0, tr.actions.size());
      Assert.assertNull("incorrect value found", tr.endTransition);
      
      tr = transitions.get(1);
      Assert.assertEquals("incorrect value found", "trigger2", tr.name);
      Assert.assertEquals(0, tr.params.size());
      Assert.assertNull("incorrect value found", tr.guard);
      Assert.assertEquals(0, tr.actions.size());
      Assert.assertNull("incorrect value found", tr.endTransition);
      
      tr = transitions.get(2);
      Assert.assertEquals("incorrect value found", "trigger3", tr.name);
      Assert.assertEquals(0, tr.params.size());
      Assert.assertNull("incorrect value found", tr.guard);
      Assert.assertEquals(0, tr.actions.size());
      Assert.assertEquals("incorrect value found", "endTransition", tr.endTransition.name);
      Assert.assertEquals(0, tr.endTransition.mxargs.size());
      
      tr = transitions.get(3);
      Assert.assertEquals("incorrect value found", "trigger4", tr.name);
      Assert.assertEquals(0, tr.params.size());
      Assert.assertNull("incorrect value found", tr.guard);
      Assert.assertEquals(0, tr.actions.size());
      Assert.assertEquals("incorrect value found", "endTransition", tr.endTransition.name);
      Assert.assertEquals(1, tr.endTransition.mxargs.size());
      Assert.assertEquals("incorrect value found", "arg1", tr.endTransition.mxargs.get(0));
      
      tr = transitions.get(4);
      Assert.assertEquals("incorrect value found", "namespace.trigger5", tr.name);
      Assert.assertEquals(1, tr.params.size());
      Assert.assertEquals("incorrect value found", "type1", tr.params.get(0).type);
      Assert.assertEquals("incorrect value found", "arg1", tr.params.get(0).value);
      Assert.assertNull("incorrect value found", tr.guard);
      Assert.assertEquals(1, tr.actions.size());
      Assert.assertEquals("incorrect value found", "action1", tr.actions.get(0).name);
      Assert.assertEquals("incorrect value found", "arg1", tr.actions.get(0).mxargs.get(0));
      Assert.assertEquals("incorrect value found", "endTransition", tr.endTransition.name);
      Assert.assertEquals(1, tr.endTransition.mxargs.size());
      Assert.assertEquals("incorrect value found", "'6'", tr.endTransition.mxargs.get(0));
      
      tr = transitions.get(5);
      Assert.assertEquals("incorrect value found", "trigger6", tr.name);
      Assert.assertEquals(2, tr.params.size());
      Assert.assertEquals("incorrect value found", "type2", tr.params.get(1).type);
      Assert.assertEquals("incorrect value found", "arg2", tr.params.get(1).value);
      Assert.assertNull("incorrect value found", tr.guard);
      Assert.assertEquals(2, tr.actions.size());
      Assert.assertEquals("incorrect value found", "action2", tr.actions.get(1).name);
      Assert.assertEquals("incorrect value found", "arg2", tr.actions.get(1).mxargs.get(1));
      Assert.assertEquals("incorrect value found", "endTransition", tr.endTransition.name);
      Assert.assertEquals(2, tr.endTransition.mxargs.size());
      Assert.assertEquals("incorrect value found", "arg2", tr.endTransition.mxargs.get(1));
      
      tr = transitions.get(6);
      Assert.assertEquals("incorrect value found", "trigger7", tr.name);
      Assert.assertEquals(2, tr.params.size());
      Assert.assertEquals("incorrect value found", "type2", tr.params.get(1).type);
      Assert.assertEquals("incorrect value found", "'8'", tr.params.get(1).value);
      Assert.assertNull("incorrect value found", tr.guard);
      Assert.assertEquals(2, tr.actions.size());
      Assert.assertEquals("incorrect value found", "action2", tr.actions.get(1).name);
      Assert.assertEquals("incorrect value found", "'LIT'", tr.actions.get(1).mxargs.get(0));
      Assert.assertEquals("incorrect value found", "endTransition", tr.endTransition.name);
      Assert.assertEquals(2, tr.endTransition.mxargs.size());
      Assert.assertEquals("incorrect value found", "'6'", tr.endTransition.mxargs.get(0));
      
            /**
        public String name = null;
  public Vector <mxParameter>params = null;
  public String guard = null;
  public Vector <mxAction>actions = null;
  public mxAction endTransition = null;
  */
  }
   
   /*@Test(expected= ParseException.class) 
   public void numValueTest() throws ParseException { 
      String test = "type 5";  // number is not allowed. must be placed in single qoutes to mean constant literal
      ByteArrayInputStream stream = new ByteArrayInputStream( test.getBytes() );
  
      ParameterParser parser = new ParameterParser( stream );
      Vector <mxParameter>params = parser.Input();
   }*/
}
