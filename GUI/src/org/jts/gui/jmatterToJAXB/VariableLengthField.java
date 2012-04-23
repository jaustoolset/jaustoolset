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

import org.jts.gui.util.Conversion;

/* Converts JMatter VariableLengthField to a JAXB VariableLengthField.
*/
public class VariableLengthField {

  public static org.jts.jsidl.binding.VariableLengthField convert( com.u2d.generated.VariableLengthField variableLengthField ) {
    if( variableLengthField == null )
      return null;
    
    org.jts.jsidl.binding.VariableLengthField vlf = 
                new org.jts.jsidl.binding.VariableLengthField();
    
    //interpretation
    Conversion.setNonEmptyDescription(vlf, variableLengthField);
    
    // attributes
    vlf.setName( variableLengthField.getName().toString() );
    vlf.setOptional( variableLengthField.getOptional().booleanValue() );
    vlf.setFieldFormat( variableLengthField.getFieldFormat().toString() );
     
    // countField
    org.jts.jsidl.binding.CountField cf = 
                new org.jts.jsidl.binding.CountField();
    cf.setMinCount( variableLengthField.getMinSize().toString() );
    cf.setMaxCount( variableLengthField.getMaxSize().toString() );

	  // Set the count field type
		long maxSize = variableLengthField.getMaxSize().longValue();
		if (maxSize <= 255)
				cf.setFieldTypeUnsigned("unsigned byte");
		else if (maxSize <= 65535)
				cf.setFieldTypeUnsigned("unsigned short integer");
		else if (maxSize <= 4294967295L)
				cf.setFieldTypeUnsigned("unsigned integer");
		else
				cf.setFieldTypeUnsigned("unsigned long integer");
    vlf.setCountField( cf );
      
    return vlf;
  }
}