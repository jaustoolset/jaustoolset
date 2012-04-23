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

/* Converts JMatter Header to a JAXB Header.
*/
public class Header {

  public static org.jts.jsidl.binding.Header convert( com.u2d.generated.Header header ) {
    if( header == null )
      return null;
    
    org.jts.jsidl.binding.Header hh = new org.jts.jsidl.binding.Header();
    
     hh.setName( header.getName().toString() );
     
     // interpretation
     Conversion.setNonEmptyDescription(hh, header);
     
     // Record | List | Variant | Sequence
     com.u2d.generated.ComplexField complexField = header.getComplexField();
     
     // if proxy is being used, get the impl behind the proxy
     if (complexField instanceof HibernateProxy) 
 	   complexField = (com.u2d.generated.ComplexField)(
 	                  (HibernateProxy)complexField).getHibernateLazyInitializer().getImplementation();
     
     if( complexField instanceof com.u2d.generated.Record ) 
       hh.setRecord( Record.convert( (com.u2d.generated.Record)complexField ) );
     else if( complexField instanceof com.u2d.generated.List ) 
       hh.setList( org.jts.gui.jmatterToJAXB.List.convert( (com.u2d.generated.List)complexField ) );
     else if( complexField instanceof com.u2d.generated.Variant ) 
       hh.setVariant( Variant.convert( (com.u2d.generated.Variant)complexField ) );
     else if( complexField instanceof com.u2d.generated.Sequence ) 
       hh.setSequence( Sequence.convert( (com.u2d.generated.Sequence)complexField ) );  
     else
       ;
    
    return hh;
  }
}