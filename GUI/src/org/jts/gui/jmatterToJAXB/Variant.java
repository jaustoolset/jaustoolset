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

/* Converts JMatter Variant to a JAXB Variant.
*/
public class Variant {

  public static org.jts.jsidl.binding.Variant convert( com.u2d.generated.Variant variant ) {
    if( variant == null )
      return null;
    
    org.jts.jsidl.binding.Variant vv = new org.jts.jsidl.binding.Variant();
    
    //attributes
    vv.setName( variant.getName().toString() );
    vv.setOptional( variant.getOptional().booleanValue() );
     
     // interpretation
    Conversion.setNonEmptyDescription(vv, variant);
     
     // vtagField 
    org.jts.jsidl.binding.VtagField vtf = 
                new org.jts.jsidl.binding.VtagField();
    
    //
    // Since the variant is based on a zero-based index, the
    // maximum value of the index is actually 1 *less* than the 
    // total number of fields in the list.
    //
    int maxCount = variant.getComplexFields().getSize()-1;
    if (maxCount < 0) maxCount = 0;
    vtf.setMinCount( "0" );
    vtf.setMaxCount( Integer.toString(maxCount) );
    
    // Set the type
    if( maxCount <= 255)
      vtf.setFieldTypeUnsigned("unsigned byte");
    else
      vtf.setFieldTypeUnsigned("unsigned short integer");
    vv.setVtagField( vtf );
     
     // Record | List | Variant | Sequence
    List jmList = variant.getComplexFields().getItems();
    List jxList = vv.getRecordOrDeclaredRecordOrList();
    
    for(int ii=0; ii<jmList.size(); ii++) {
      com.u2d.generated.ComplexField complexField = (com.u2d.generated.ComplexField)jmList.get(ii);
      
      // if proxy is being used, get the impl behind the proxy
     if (complexField instanceof HibernateProxy) 
 	   complexField = (com.u2d.generated.ComplexField)(
 	                  (HibernateProxy)complexField).getHibernateLazyInitializer().getImplementation();
      
      if( complexField instanceof com.u2d.generated.Record ) {
        jxList.add( Record.convert( (com.u2d.generated.Record)complexField ) );
      } else if ( complexField instanceof com.u2d.generated.List ) {
        jxList.add( org.jts.gui.jmatterToJAXB.List.convert( (com.u2d.generated.List)complexField ) );      
      } else if ( complexField instanceof com.u2d.generated.Variant ) {
        jxList.add( Variant.convert( (com.u2d.generated.Variant)complexField ) );    
      } else if ( complexField instanceof com.u2d.generated.Sequence ) {
        jxList.add( Sequence.convert( (com.u2d.generated.Sequence)complexField ) );        
      } else ;
    }
    
    return vv;
  }
}