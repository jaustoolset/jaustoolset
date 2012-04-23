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

/* Converts JMatter ServiceDef to a JAXB ServiceDef.
*/
public class ServiceDef {

  public static org.jts.jsidl.binding.ServiceDef convert( com.u2d.generated.ServiceDef serviceDef ) {
    if( serviceDef == null )
      return null;
    
    org.jts.jsidl.binding.ServiceDef sd = 
                new org.jts.jsidl.binding.ServiceDef();
    
    // attributes
    sd.setName( serviceDef.getName().toString() );
    sd.setId( serviceDef.getServiceId().toString() );
    sd.setVersion( serviceDef.get_version().toString() );
    
    // description
    sd.setDescription( Description.convert( serviceDef ) );
    
    // assumptions
    sd.setAssumptions( Assumptions.convert( serviceDef ) );
    
    // references
    sd.setReferences( References.convert( serviceDef ) );
    
    // message_set
    sd.setMessageSet( MessageSet.convert( serviceDef ) );
    
    // internal_event_set
    sd.setInternalEventsSet( InternalEventsSet.convert( serviceDef ) );
    
    // protocol_behavior
    sd.setProtocolBehavior( ProtocolBehavior.convert( serviceDef.getProtocolBehavior() ) );
    
    // declared_constant_set
    if(serviceDef.getConstantSet() != null)
    {
    	sd.setDeclaredConstSet( ConstantSet.convert( (com.u2d.generated.ConstantSet) serviceDef.getConstantSet()));
    }
    
    return sd;
  }
  
  
}