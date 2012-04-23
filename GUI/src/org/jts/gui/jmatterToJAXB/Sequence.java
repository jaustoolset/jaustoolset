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

/* Converts JMatter Sequence to a JAXB Sequence.
*/
public class Sequence {

  public static org.jts.jsidl.binding.Sequence convert( com.u2d.generated.Sequence sequence ) {
    if( sequence == null )
      return null;
    
    org.jts.jsidl.binding.Sequence ss = new org.jts.jsidl.binding.Sequence();
    
    //attributes
    ss.setName( sequence.getName().toString() );
    ss.setOptional( sequence.getOptional().booleanValue() );
     
    // interpretation
    Conversion.setNonEmptyDescription(ss, sequence);
     
    // ComplexFields
     List jmList = sequence.getComplexFields().getItems();
     List jxList = ss.getRecordOrDeclaredRecordOrList();
     
     for( int ii=0; ii<jmList.size(); ii++) {
       com.u2d.generated.ComplexField complexField = (com.u2d.generated.ComplexField)jmList.get(ii);
       
       // if proxy is being used, get the impl behind the proxy
     if (complexField instanceof HibernateProxy) 
 	   complexField = (com.u2d.generated.ComplexField)(
 	                  (HibernateProxy)complexField).getHibernateLazyInitializer().getImplementation();
       
       if( complexField instanceof com.u2d.generated.Record ) 
         jxList.add( Record.convert( (com.u2d.generated.Record)complexField ) );
       else if( complexField instanceof com.u2d.generated.List ) 
         jxList.add( org.jts.gui.jmatterToJAXB.List.convert( (com.u2d.generated.List)complexField ) );
       else if( complexField instanceof com.u2d.generated.Variant ) 
         jxList.add( Variant.convert( (com.u2d.generated.Variant)complexField ) );
       else if( complexField instanceof com.u2d.generated.Sequence ) 
         jxList.add( Sequence.convert( (com.u2d.generated.Sequence)complexField ) );
       else
         ;      
     }
     
     // presence vector
     setPresenceVector( ss );
     
     return ss;
  }
  
  private static void setPresenceVector( org.jts.jsidl.binding.Sequence ss ) {
    int optionalCount = 0;
    
    // get optional field count
    List jxList = ss.getRecordOrDeclaredRecordOrList();
    
    for( int ii=0; ii<jxList.size(); ii++) {
      Object obj = jxList.get(ii);
      if( obj instanceof org.jts.jsidl.binding.Record && ((org.jts.jsidl.binding.Record)obj).isOptional() ) 
         optionalCount++;
       else if( obj instanceof org.jts.jsidl.binding.List && ((org.jts.jsidl.binding.List)obj).isOptional() ) 
         optionalCount++;
       else if( obj instanceof org.jts.jsidl.binding.Variant && ((org.jts.jsidl.binding.Variant)obj).isOptional() ) 
         optionalCount++;
       else if( obj instanceof org.jts.jsidl.binding.Sequence && ((org.jts.jsidl.binding.Sequence)obj).isOptional() ) 
         optionalCount++;
       else
         ;      
    }
   
    // create presence vector
    org.jts.jsidl.binding.PresenceVector pv = null; 
   
    if( optionalCount > 0 ) 
      pv = new org.jts.jsidl.binding.PresenceVector();
   
    if( optionalCount > 0 && optionalCount < 8 ) {    	
      pv.setFieldTypeUnsigned("unsigned byte");
    } else if( optionalCount > 0 && optionalCount < 16 ){
      pv.setFieldTypeUnsigned("unsigned short integer");
    }  else if( optionalCount > 0 && optionalCount < 32 ) {
      pv.setFieldTypeUnsigned("unsigned integer");
    }  else if( optionalCount > 0 && optionalCount < 64 ) {
      pv.setFieldTypeUnsigned("unsigned long integer");
    }  else ;
    
    if( pv != null )
      ss.setPresenceVector( pv );
  }
}