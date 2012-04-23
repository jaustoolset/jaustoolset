/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2011, United States Government
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
package org.jts.eclipse.conversion.cjsidl;

import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class ServiceDef {
	/**
	 * Converts a service def from CJSIDL to JSIDL
	 * @param inputdef - CJSIDL service def
	 * @return - resulting JSIDL service def
	 * @throws ConversionException 
	 */
	public static org.jts.jsidl.binding.ServiceDef convert(org.jts.eclipse.cjsidl.serviceDef inputdef) throws ConversionException
	{
		org.jts.jsidl.binding.ServiceDef outputdef = new org.jts.jsidl.binding.ServiceDef();
		
		outputdef.setName(inputdef.getServiceName());
		// optional items checked before processing
		if(inputdef.getDescr() != null){
			outputdef.setDescription(Description.convert(inputdef.getDescr().getContent()));
		}
		outputdef.setAssumptions(Assumptions.convert(inputdef.getAssumpt()));
		if(inputdef.getConstSet() != null ){
			outputdef.setDeclaredConstSet(DeclaredConstSet.convert(inputdef.getConstSet()));
		}
		
		if(inputdef.getTypeSet() != null){
			outputdef.setDeclaredTypeSet(DeclaredTypeSet.convert(inputdef.getTypeSet()));
		}
		// fix some strings
		String id = inputdef.getName();
		id = id.replace("'", "");
		id = id.replace("\"", "");
		id = id.replace(".", ":");
		outputdef.setId(id);
		
		String version = inputdef.getServiceVersion();
		version = version.replace("'", "");
		version = version.replace("\"", "");
		outputdef.setVersion(version);

		if(inputdef.getInternalEventSet() != null){
			outputdef.setInternalEventsSet(InternalEventsSet.convert(inputdef.getInternalEventSet()));
		}

		if(inputdef.getMessageSet() != null){
			outputdef.setMessageSet(MessageSet.convert(inputdef.getMessageSet()));
		}
		
		if(inputdef.getRefs() != null){
			outputdef.setReferences(References.convert(inputdef.getRefs()));
		}
		
		if(inputdef.getProtocolBehavior() != null){
			outputdef.setProtocolBehavior(ProtocolBehavior.convert(inputdef.getProtocolBehavior()));
		}
		
		return outputdef;
	}
	/**
	 * Converts a JSIDL service def to CJSIDL
	 * @param inputdef - JSIDL service def
	 * @return - resulting CJSIDL object
	 * @throws ConversionException 
	 */
	public static org.jts.eclipse.cjsidl.serviceDef convert(org.jts.jsidl.binding.ServiceDef inputdef) throws ConversionException
	{
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.serviceDef outputDef = factory.createserviceDef();

		outputDef.setServiceName(inputdef.getName());
		outputDef.setName( inputdef.getId().replace(":", ".") );
		outputDef.setServiceVersion( inputdef.getVersion() );

		outputDef.setAssumpt(Assumptions.convert(inputdef.getAssumptions()));
		outputDef.setDescr(Description.convert(inputdef.getDescription()));
		// check optional items
		if(inputdef.getDeclaredConstSet() != null){
			outputDef.setConstSet(DeclaredConstSet.convert(inputdef.getDeclaredConstSet()));
		}
		if(inputdef.getReferences() != null && 
				(inputdef.getReferences().getInheritsFrom() != null || 
				inputdef.getReferences().getClientOf().size() > 0)){
			outputDef.setRefs(References.convert(inputdef.getReferences()));
		}
		if(inputdef.getDeclaredTypeSet() != null){
			outputDef.setTypeSet(DeclaredTypeSet.convert(inputdef.getDeclaredTypeSet()));
		}		
		if(inputdef.getInternalEventsSet() != null){
			outputDef.setInternalEventSet(InternalEventsSet.convert(inputdef.getInternalEventsSet(), outputDef));
		}
		if(inputdef.getMessageSet() != null){
			outputDef.setMessageSet(MessageSet.convert(inputdef.getMessageSet(), outputDef));
		}
		if(inputdef.getProtocolBehavior() != null){
			outputDef.setProtocolBehavior(ProtocolBehavior.convert(inputdef.getProtocolBehavior(), outputDef));
		}
		
		return outputDef;
	}

}
