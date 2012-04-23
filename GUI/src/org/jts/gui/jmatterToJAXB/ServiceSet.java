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

/* Converts JMatter ServiceSet to a JAXB ServiceSet.
*/
public class ServiceSet {

  public static org.jts.jsidl.binding.ServiceSet convert( com.u2d.generated.ServiceSet serviceSet ) {
    if( serviceSet == null )
      return null;
    
    org.jts.jsidl.binding.ServiceSet ss = 
                new org.jts.jsidl.binding.ServiceSet();
    
    ss.setName( serviceSet.getName().toString() );
    ss.setId( serviceSet.getServiceSetId().toString() );
    ss.setVersion( serviceSet.get_version().toString() );
    org.jts.jsidl.binding.Description desc = new org.jts.jsidl.binding.Description();
    desc.setContent( serviceSet.getDescription().toString() );
    ss.setDescription( desc );
    
    List jmList = serviceSet.getServiceDefs().getItems();
    
    if( jmList != null ) {
      List jxList = ss.getServiceDef();
      for( int ii=0; ii<jmList.size(); ii++ ) {
        org.jts.jsidl.binding.ServiceDef sd = ServiceDef.convert( (com.u2d.generated.ServiceDef)jmList.get(ii) );
        if( sd != null ) {
          jxList.add( sd );
        }
      }
    }
    
    // declared_constant_set
    jmList = serviceSet.getConstantSets().getItems();
    if(jmList != null)
    {
    	List jxList = ss.getDeclaredConstSet();
    	for(int i = 0; i < jmList.size(); i++)
    	{
    		org.jts.jsidl.binding.DeclaredConstSet constantSet = ConstantSet.convert((com.u2d.generated.ConstantSet) jmList.get(i));
    		if(constantSet != null)
    		{
    			jxList.add(constantSet);
    		}
    	}
    }
    
    return ss;
  }
  
  
}