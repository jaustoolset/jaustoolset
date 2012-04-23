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

import org.eclipse.emf.ecore.EObject;
import org.jts.eclipse.cjsidl.bodyDef;
import org.jts.eclipse.cjsidl.bodyRef;
import org.jts.eclipse.cjsidl.eventDef;
import org.jts.eclipse.cjsidl.footerDef;
import org.jts.eclipse.cjsidl.footerRef;
import org.jts.eclipse.cjsidl.headerDef;
import org.jts.eclipse.cjsidl.headerRef;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class EventDef {

	/**
	 * Converts a CJSIDL event definition to a JSIDL object
	 * @param eventDef - input CJSIDL object
	 * @return - resulting JSIDL object
	 * @throws ConversionException 
	 */
	public static org.jts.jsidl.binding.EventDef convert(eventDef eventDef) throws ConversionException {
		org.jts.jsidl.binding.EventDef def = new org.jts.jsidl.binding.EventDef();

		def.setName(eventDef.getName());	
		
		// this isn't optional in JSIDL
		if(eventDef.getDescr() == null){
			def.setDescription(Description.convert(""));
		} else {
			def.setDescription(Description.convert(eventDef.getDescr().getContent()));
		}
		
		
		if(eventDef.getBody() instanceof bodyDef)
		{
			def.setBody(Body.convert((bodyDef) eventDef.getBody()));
		} else {
			def.setDeclaredBody(DeclaredBody.convert((bodyRef) eventDef.getBody()));
		}
		if(eventDef.getFooter() != null){
			if(eventDef.getFooter() instanceof footerDef){
				def.setFooter(Footer.convert((footerDef)eventDef.getFooter()));
			} else if(eventDef.getFooter() instanceof footerRef){
				def.setDeclaredFooter(DeclaredFooter.convert((footerRef)eventDef.getFooter()));
			}
		} else {
			def.setFooter(Footer.getEmpty(eventDef.getName()));
		}
		if(eventDef.getHeader() != null){
			if(eventDef.getHeader() instanceof headerDef){
				def.setHeader(Header.convert((headerDef)eventDef.getHeader()));
			} else if(eventDef.getHeader() instanceof headerRef){
				def.setDeclaredHeader(DeclaredHeader.convert((headerRef)eventDef.getHeader()));
			}
		} else{
			def.setHeader(Header.getEmpty(eventDef.getName()));
		}
		
		
		
		return def;
	}

	/**
	 * Converts a JSIDL event definition to a CJSIDL event definition
	 * @param event - the input event def
	 * @param root - root for this CJSIDL (serviceDef, typeSet, constSet)
	 * @return - resulting CJSIDL event def.
	 */
	public static eventDef convert(org.jts.jsidl.binding.EventDef event, EObject root) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();		
		org.jts.eclipse.cjsidl.eventDef output = factory.createeventDef();
		
		output.setName(event.getName());
		output.setDescr(Description.convert(event.getDescription()));
		
		if(event.getDeclaredBody() != null){
			output.setBody(DeclaredBody.convertToRef(event.getDeclaredBody(), root));
		} 
		if(event.getBody() != null){
			output.setBody(Body.convert(event.getBody(), root));
		}
		if(event.getFooter() != null){
			output.setFooter(Footer.convert(event.getFooter(), root));
		} 
		if(event.getDeclaredFooter() != null){
			output.setFooter(DeclaredFooter.convertToRef(event.getDeclaredFooter(), root));
		}
		if(event.getHeader() != null){
			output.setHeader(Header.convert(event.getHeader(), root));
		}
		if(event.getDeclaredHeader() != null){
			output.setHeader(DeclaredHeader.convertToRef(event.getDeclaredHeader(), root));
		}

		return output;
	}

}
