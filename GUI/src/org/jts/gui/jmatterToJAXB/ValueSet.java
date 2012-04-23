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

/* Converts JMatter ValueSet to a JAXB ValueSet.
*/
public class ValueSet {

  public static org.jts.jsidl.binding.ValueSet convert( com.u2d.generated.ValueSet valueSet ) {
    if( valueSet == null )
      return null;
    
    
    org.jts.jsidl.binding.ValueSet vs = 
                new org.jts.jsidl.binding.ValueSet();
    vs.setOffsetToLowerLimit( valueSet.getOffsetToLowerLimit().booleanValue() );
    
    
    List jxList = vs.getValueRangeOrValueEnum();
    
    // value ranges
    List jmList = valueSet.getValueRanges().getItems();
    for(int ii=0; ii<jmList.size(); ii++) {
      jxList.add( ValueRange.convert( (com.u2d.generated.ValueRange)jmList.get(ii) ) );
    }
  
  
    // value enums
    jmList = valueSet.getValueEnums().getItems();
    for(int ii=0; ii<jmList.size(); ii++) {
      jxList.add( ValueEnum.convert( (com.u2d.generated.ValueEnum)jmList.get(ii) ) );
    }
    
    return vs;
  }
}