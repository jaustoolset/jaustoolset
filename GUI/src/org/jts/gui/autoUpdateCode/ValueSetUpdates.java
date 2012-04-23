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

package org.jts.gui.autoUpdateCode;

import java.io.*;

import com.u2d.type.atom.BooleanEO;

/** Implements updates that need to be made to ValueSet.java. 
*
*/
class ValueSetUpdates {
  ValueSetUpdates() {

	  String generatedFileName = "src" + File.separator + "com" + File.separator + "u2d" + File.separator +
      							"generated" + File.separator + "ValueSet.java";

	  String bindingFileName = "src" + File.separator + "org" + File.separator + "jts" + File.separator + 
	  							"jsidl" + File.separator + "binding" + File.separator + "ValueSet.java";

      try {
        // title
        FindReplace.replaceInFile( generatedFileName, 
        							generatedFileName, 
        							"offsetToLowerLimit.title() + \" [ValueSet]\"", 
        							"\"numRanges: \"+getValueRanges().getSize()+\" numEnums: \"+getValueEnums().getSize()+\" [ValueSet]\"" );                                          
      } catch(IOException ioe) {
        ioe.printStackTrace();
      }
      
      try {
        // ignore 'name' in comparison operator since it's not defined by JSIDL
        FindReplace.replaceInFile( bindingFileName, 
        							bindingFileName, 
        							"String[] ignore={\"interpretation\"};", 
        							"String[] ignore={\"interpretation\", \"name\"};" );                                          
      } catch(IOException ioe) {
        ioe.printStackTrace();
      }
      
      try
      {
    	  // add tooltip for offsetToLowerLimit field
          FindReplace.replaceInFileNoMatching( generatedFileName, 
        		  						generatedFileName, 
        		  						"    public BooleanEO getOffsetToLowerLimit() { return offsetToLowerLimit;}", 
        		  						"    @Fld( description = \"Setting this to true will map the actual range into the value range starting at the lowest offset\" )\r\n" + 
          								"    public BooleanEO getOffsetToLowerLimit() { return offsetToLowerLimit;}" );
      }
      catch( IOException ioe )
      {
    	  ioe.printStackTrace();
      }
  }
} 