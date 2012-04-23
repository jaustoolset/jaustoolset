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
import org.hibernate.proxy.HibernateProxy;
import org.jts.gui.util.Conversion;

/* Converts JMatter Array to a JAXB Array.
*/
public class Array {

  public static org.jts.jsidl.binding.Array convert( com.u2d.generated.Array array ) {
    if( array == null )
      return null;
    
    org.jts.jsidl.binding.Array aa = new org.jts.jsidl.binding.Array();
    
     // attributes
    aa.setName( array.getName().toString() );
    aa.setOptional( array.getOptional().booleanValue() );
     
     // interpretation
    Conversion.setNonEmptyDescription(aa, array);
     
     // dimensions 
    List dimensionList = array.getDimensions().getItems();
    List<org.jts.jsidl.binding.Dimension> convertedDimensionList = aa.getDimension();

    for( int i=0; i < dimensionList.size(); i++ )
    {
    	convertedDimensionList.add( Dimension.convert( (com.u2d.generated.Dimension)dimensionList.get( i ) ) );
    }
     
     // SimpleField
    com.u2d.generated.SimpleField simpleField = array.getArrayElementType();
    
     // if proxy is being used, get the impl behind the proxy
     if (simpleField instanceof HibernateProxy) 
 	   simpleField = (com.u2d.generated.SimpleField)(
 	                  (HibernateProxy)simpleField).getHibernateLazyInitializer().getImplementation();
 	                  
 	                      
    if( simpleField instanceof com.u2d.generated.BitField ) {
       aa.setBitField( BitField.convert( (com.u2d.generated.BitField)simpleField) );
     
    } else if ( simpleField instanceof com.u2d.generated.FixedField ) {
      aa.setFixedField( FixedField.convert( (com.u2d.generated.FixedField)simpleField) );
      
    } else if ( simpleField instanceof com.u2d.generated.VariableField ) {
      aa.setVariableField( VariableField.convert( (com.u2d.generated.VariableField)simpleField) );
      
    } else if ( simpleField instanceof com.u2d.generated.FixedLengthString ) {
       aa.setFixedLengthString( FixedLengthString.convert( (com.u2d.generated.FixedLengthString)simpleField) );
      
    } else if ( simpleField instanceof com.u2d.generated.VariableLengthString ) {
       aa.setVariableLengthString( VariableLengthString.convert( (com.u2d.generated.VariableLengthString)simpleField) );
      
    } else if ( simpleField instanceof com.u2d.generated.VariableLengthField ) {
       aa.setVariableLengthField( VariableLengthField.convert( (com.u2d.generated.VariableLengthField)simpleField) );
      
    } else if ( simpleField instanceof com.u2d.generated.VariableFormatField ) {
       aa.setVariableFormatField( VariableFormatField.convert( (com.u2d.generated.VariableFormatField)simpleField) );
      
    } else ;
    
    return aa;
  }
}