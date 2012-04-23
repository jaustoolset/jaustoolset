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

import org.jts.gui.mxGraphtoJAXB.mxStateMachine;

import java.io.ByteArrayInputStream;

/**
 *
 * @author Arfath
 */
public class StateMachineParserTest {

    @Test
    public void parserTest1() throws ParseException {
      String test = "name=fsm; isStateless=true";
      ByteArrayInputStream stream = new ByteArrayInputStream( test.getBytes() );
  
      StateMachineParser parser = new StateMachineParser( stream );
      parser.Input();
      
      Assert.assertEquals("incorrect value found", "fsm", parser.name);
      Assert.assertEquals("incorrect value found", "true", parser.isStateless);
   }
   
   @Test
    public void parserTest2() throws ParseException {
      String test = "name=fsm; isStateless=false";
      ByteArrayInputStream stream = new ByteArrayInputStream( test.getBytes() );
  
      StateMachineParser parser = new StateMachineParser( stream );
      parser.Input();
      
      Assert.assertEquals("incorrect value found", "fsm", parser.name);
      Assert.assertEquals("incorrect value found", "false", parser.isStateless);
   }
   
   /*@Test(expected= ParseException.class) 
   public void numValueTest() throws ParseException { 
      String test = "type 5";  // number is not allowed. must be placed in single qoutes to mean constant literal
      ByteArrayInputStream stream = new ByteArrayInputStream( test.getBytes() );
  
      ParameterParser parser = new ParameterParser( stream );
      Vector <Parameter>params = parser.Input();
   }*/
}
