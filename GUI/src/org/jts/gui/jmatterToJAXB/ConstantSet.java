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

/* Converts JMatter ServiceSet to a JAXB ServiceSet.
*/
public class ConstantSet 
{

  public static org.jts.jsidl.binding.DeclaredConstSet convert( com.u2d.generated.ConstantSet jmConstantSet ) 
  {
    if( jmConstantSet == null )
      return null;
    
    org.jts.jsidl.binding.DeclaredConstSet jxConstantSet = new org.jts.jsidl.binding.DeclaredConstSet();
    
    // Name
    jxConstantSet.setName(jmConstantSet.getName().stringValue());
    
    // ID
    if(jmConstantSet.getID() != null && !jmConstantSet.getConstantSetId().stringValue().isEmpty())
    {
    	jxConstantSet.setId(jmConstantSet.getConstantSetId().stringValue());
    }
       
    // Version
    if(jmConstantSet.get_version() != null && !jmConstantSet.get_version().stringValue().isEmpty())
    {
    	jxConstantSet.setVersion(jmConstantSet.get_version().stringValue());
    }

    // Constants
    java.util.List<com.u2d.generated.Constant> jmList = jmConstantSet.getConstants().getItems(); 
    if(jmList != null)
    {
	    java.util.List jxList = jxConstantSet.getConstDef(); 
	
	    for(int i = 0; i < jmList.size(); i++)
	    {
	    	jxList.add(Constant.convert(jmList.get(i)));
	    }
    }
    
    return jxConstantSet;
  }
  
  
}