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

package org.jts.gui.jmatterToJAXB;

import java.util.List;

/* Converts JMatter ScaleRange to a JAXB ScaleRange.
*/
public class ScaleRange {

  public static org.jts.jsidl.binding.ScaleRange convert( com.u2d.generated.ScaleRange scaleRange ) {
    if( scaleRange == null )
      return null;
        
    String lowerLimit = scaleRange.getRealLowerLimit().toString();
    String upperLimit = scaleRange.getRealUpperLimit().toString();

    if( !( lowerLimit.isEmpty() && upperLimit.isEmpty() ) ) {
      org.jts.jsidl.binding.ScaleRange sr = 
                new org.jts.jsidl.binding.ScaleRange();
      
      sr.setRealLowerLimit( lowerLimit );
      sr.setRealUpperLimit( upperLimit );

			// dmm - The integer_function is not user settable. For now, only "round" is supported.
			sr.setIntegerFunction("round");
      return sr;
    }

    
    return null;
  }
}