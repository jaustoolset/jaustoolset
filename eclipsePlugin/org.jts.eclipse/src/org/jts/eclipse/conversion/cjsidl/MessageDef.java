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
import org.jts.eclipse.cjsidl.footerDef;
import org.jts.eclipse.cjsidl.footerRef;
import org.jts.eclipse.cjsidl.headerDef;
import org.jts.eclipse.cjsidl.headerRef;
import org.jts.eclipse.cjsidl.messageDef;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class MessageDef extends ConversionUtil{

	/**
	 * Converts xtext object to a jsidl data binding
	 * @param messageDef - input xtext object
	 * @return - resulting data binding
	 * @throws ConversionException 
	 */
	public static org.jts.jsidl.binding.MessageDef convert(
			messageDef messageDef) throws ConversionException {
		org.jts.jsidl.binding.MessageDef def = new org.jts.jsidl.binding.MessageDef();

		def.setName(messageDef.getName());		
		def.setMessageId(hexStringToByteArray(messageDef.getMessageID()));
		def.setDescription(Description.convert(messageDef.getDescr().getContent()));
		
		if(messageDef.getCommand() != null){
			def.setIsCommand(messageDef.getCommand().equals(COMMAND));
		} 
		
		if(messageDef.getBody() instanceof bodyDef)
		{
			def.setBody(Body.convert((bodyDef) messageDef.getBody()));
		} else {
			def.setDeclaredBody(DeclaredBody.convert((bodyRef) messageDef.getBody()));
		}
		if(messageDef.getFooter() != null){
			if(messageDef.getFooter() instanceof footerDef){
				def.setFooter(Footer.convert((footerDef)messageDef.getFooter()));
			} else if(messageDef.getFooter() instanceof footerRef){
				def.setDeclaredFooter(DeclaredFooter.convert((footerRef)messageDef.getFooter()));
			}
		} else {
			def.setFooter(Footer.getEmpty(messageDef.getName()));
		}
		if(messageDef.getHeader() != null){
			if(messageDef.getHeader() instanceof headerDef){
				def.setHeader(Header.convert((headerDef)messageDef.getHeader()));
			} else if(messageDef.getHeader() instanceof headerRef){
				def.setDeclaredHeader(DeclaredHeader.convert((headerRef)messageDef.getHeader()));
			}
		} else{
			def.setHeader(Header.getEmpty(messageDef.getName()));
		}
		
	
	
		// CJSIDL won't allow declared header/footer/body, so we can ignore these
		// for the conversion
//		def.setDeclaredBody(value)
//		def.setDeclaredFooter(value)
//		def.setDeclaredHeader(value)
		
		return def;
	}

	/**
	 * Converts a message def binding to a cjsidl object
	 * @param tmpdef - input data binding
	 * @return - resulting xtext object
	 */
	public static messageDef convert(org.jts.jsidl.binding.MessageDef tmpdef, EObject root) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.messageDef msg = factory.createmessageDef();
		
		msg.setName(tmpdef.getName());
		if(tmpdef.isIsCommand()){
			msg.setCommand(COMMAND);
		}
		msg.setMessageID("0x" + byteArrayToHexString(tmpdef.getMessageId()));
		msg.setDescr(Description.convert(tmpdef.getDescription()));
		
		if(tmpdef.getDeclaredBody() != null){
			msg.setBody(DeclaredBody.convertToRef(tmpdef.getDeclaredBody(), root));
		} 
		if(tmpdef.getBody() != null){
			msg.setBody(Body.convert(tmpdef.getBody(), root));
		}
		if(tmpdef.getFooter() != null){
			msg.setFooter(Footer.convert(tmpdef.getFooter(), root));
		} 
		if(tmpdef.getDeclaredFooter() != null){
			msg.setFooter(DeclaredFooter.convertToRef(tmpdef.getDeclaredFooter(), root));
		}
		if(tmpdef.getHeader() != null){
			msg.setHeader(Header.convert(tmpdef.getHeader(), root));
		}
		if(tmpdef.getDeclaredHeader() != null){
			msg.setHeader(DeclaredHeader.convertToRef(tmpdef.getDeclaredHeader(), root));
		}

		return msg;
	}

}
