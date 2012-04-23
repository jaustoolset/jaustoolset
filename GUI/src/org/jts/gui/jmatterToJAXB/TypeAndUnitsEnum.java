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

/* Converts JMatter TypeAndUnitsEnum to a JAXB TypeAndUnitsEnum.
*/
public class TypeAndUnitsEnum {

  public static org.jts.jsidl.binding.TypeAndUnitsEnum convert( com.u2d.generated.TypeAndUnitsEnum typeAndUnitsEnum ) {
    if( typeAndUnitsEnum == null )
      return null;
    
    org.jts.jsidl.binding.TypeAndUnitsEnum tue = 
                new org.jts.jsidl.binding.TypeAndUnitsEnum();
    
    tue.setName( typeAndUnitsEnum.getName().toString() );
    tue.setIndex( (short)typeAndUnitsEnum.getIndex().intValue() );
    tue.setFieldType( typeAndUnitsEnum.getType().toString() );
    tue.setFieldUnits( typeAndUnitsEnum.getUnits().toString() ); 
    
    // scale range
     org.jts.jsidl.binding.ScaleRange sr = ScaleRange.convert( typeAndUnitsEnum.getScaleRange() );
     if( sr != null )
       tue.setScaleRange( sr );
     
     // value set 
     org.jts.jsidl.binding.ValueSet vs = ValueSet.convert( typeAndUnitsEnum.getValueSet() );
     if( vs != null ) 
       tue.setValueSet(vs);
    
    return tue;
  }
}