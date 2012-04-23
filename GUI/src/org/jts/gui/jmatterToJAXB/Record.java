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

/* Converts JMatter Record to a JAXB Record.
*/
public class Record {

  public static org.jts.jsidl.binding.Record convert( com.u2d.generated.Record record ) {
    if( record == null )
      return null;
    
    org.jts.jsidl.binding.Record rr = new org.jts.jsidl.binding.Record();
    
     // attributes
     rr.setName( record.getName().toString() );
     rr.setOptional( record.getOptional().booleanValue() );
     
     // interpretation
     Conversion.setNonEmptyDescription(rr, record);
     
     // simple fields
     List jmList = record.getSimpleFields().getItems();
     List jxList = rr.getArrayOrFixedFieldOrVariableField();
     
     for( int ii=0; ii<jmList.size(); ii++) {
       com.u2d.generated.SimpleField simpleField = (com.u2d.generated.SimpleField)jmList.get(ii);
       
       // if proxy is being used, get the impl behind the proxy
       if (simpleField instanceof HibernateProxy) 
 	   simpleField = (com.u2d.generated.SimpleField)(
 	                  (HibernateProxy)simpleField).getHibernateLazyInitializer().getImplementation();
       
       if( simpleField instanceof com.u2d.generated.Array ) 
         jxList.add( Array.convert( (com.u2d.generated.Array)simpleField ) );
       else if( simpleField instanceof com.u2d.generated.FixedField ) 
         jxList.add( FixedField.convert( (com.u2d.generated.FixedField)simpleField ) );
       else if( simpleField instanceof com.u2d.generated.VariableField ) 
         jxList.add( VariableField.convert( (com.u2d.generated.VariableField)simpleField ) );
       else if( simpleField instanceof com.u2d.generated.BitField ) 
         jxList.add( BitField.convert( (com.u2d.generated.BitField)simpleField ) );
       else if( simpleField instanceof com.u2d.generated.FixedLengthString ) 
         jxList.add( FixedLengthString.convert( (com.u2d.generated.FixedLengthString)simpleField ) );
       else if( simpleField instanceof com.u2d.generated.VariableLengthString ) 
         jxList.add( VariableLengthString.convert( (com.u2d.generated.VariableLengthString)simpleField ) );
       else if( simpleField instanceof com.u2d.generated.VariableLengthField ) 
         jxList.add( VariableLengthField.convert( (com.u2d.generated.VariableLengthField)simpleField ) );
       else if( simpleField instanceof com.u2d.generated.VariableFormatField ) 
         jxList.add( VariableFormatField.convert( (com.u2d.generated.VariableFormatField)simpleField ) );
       else
         ;      
     }
     
     // presence vector
     setPresenceVector( rr );
     
     return rr;
  }
  
  private static void setPresenceVector( org.jts.jsidl.binding.Record rr ) {
    int optionalCount = 0;
    
    // get optional field count
    List jxList = rr.getArrayOrFixedFieldOrVariableField();
    
    for( int ii=0; ii<jxList.size(); ii++) {
      Object obj = jxList.get(ii);
      if( obj instanceof org.jts.jsidl.binding.Array && ((org.jts.jsidl.binding.Array)obj).isOptional() ) 
         optionalCount++;
       else if( obj instanceof org.jts.jsidl.binding.FixedField && ((org.jts.jsidl.binding.FixedField)obj).isOptional() ) 
         optionalCount++;
       else if( obj instanceof org.jts.jsidl.binding.VariableField && ((org.jts.jsidl.binding.VariableField)obj).isOptional() ) 
         optionalCount++;
       else if( obj instanceof org.jts.jsidl.binding.BitField && ((org.jts.jsidl.binding.BitField)obj).isOptional() ) 
         optionalCount++;
       else if( obj instanceof org.jts.jsidl.binding.FixedLengthString && ((org.jts.jsidl.binding.FixedLengthString)obj).isOptional() ) 
         optionalCount++;
       else if( obj instanceof org.jts.jsidl.binding.VariableLengthString && ((org.jts.jsidl.binding.VariableLengthString)obj).isOptional() ) 
         optionalCount++;
       else if( obj instanceof org.jts.jsidl.binding.VariableLengthField && ((org.jts.jsidl.binding.VariableLengthField)obj).isOptional() ) 
         optionalCount++;
       else if( obj instanceof org.jts.jsidl.binding.VariableFormatField && ((org.jts.jsidl.binding.VariableFormatField)obj).isOptional() ) 
         optionalCount++;
       else
         ;      
    }
   
    // create presence vector
    org.jts.jsidl.binding.PresenceVector pv = null; 
   
    if( optionalCount > 0 ) { 
      pv = new org.jts.jsidl.binding.PresenceVector();
   
      if( optionalCount < 8 ) {
        pv.setFieldTypeUnsigned("unsigned byte");
      } else if( optionalCount < 16 ) {
        pv.setFieldTypeUnsigned("unsigned short integer");
      }  else if( optionalCount < 32 ) {
        pv.setFieldTypeUnsigned("unsigned integer");
      }  else if( optionalCount < 64 ) {
        pv.setFieldTypeUnsigned("unsigned long integer");
      }  else ;
    }
    
    if( pv != null )
      rr.setPresenceVector( pv );
  }
}