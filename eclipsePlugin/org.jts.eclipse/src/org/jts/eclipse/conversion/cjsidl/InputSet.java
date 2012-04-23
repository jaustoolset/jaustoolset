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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.jts.eclipse.cjsidl.messageRef;
import org.jts.eclipse.cjsidl.messageScopedRef;
import org.jts.eclipse.cjsidl.messages;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class InputSet extends ConversionUtil{

	/**
	 * Converts an input message set to a JSIDL object
	 * @param inputSet - input CJSIDL message set
	 * @param comment - a comment to add to the JSIDL object
	 * @return - resulting JSIDL object
	 * @throws ConversionException 
	 */
	public static org.jts.jsidl.binding.InputSet convert(messages inputSet, String comment) throws ConversionException {

		org.jts.jsidl.binding.InputSet newset = new org.jts.jsidl.binding.InputSet();
		
		java.util.List<Object> newdefs = newset.getMessageDefOrDeclaredMessageDef();
		EList<org.jts.eclipse.cjsidl.messageDef> defs= inputSet.getMessageDefs();
		for(org.jts.eclipse.cjsidl.messageDef def: defs){
			newdefs.add((Object)MessageDef.convert(def));
		}
		EList<org.jts.eclipse.cjsidl.messageRef> refs= inputSet.getTypeRefs();
		for(org.jts.eclipse.cjsidl.messageRef ref: refs){
			newdefs.add((Object)DeclaredMessageDef.convert(ref));
		}
		EList<org.jts.eclipse.cjsidl.messageScopedRef> screfs= inputSet.getScopedRefs();
		for(org.jts.eclipse.cjsidl.messageScopedRef ref: screfs){
			newdefs.add((Object)DeclaredMessageDef.convert(ref));
		}
		return newset;
	}

	/**
	 * Convert from a JSIDL input message set to a CJSIDL input message set
	 * @param inputSet - JSIDL input message set
	 * @param root - root object for this message set(serviceDef)
	 * @return - resulting CJSIDL object
	 */
	public static messages convert(org.jts.jsidl.binding.InputSet inputSet, EObject root) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.messages output = factory.createmessages();
		
		
		java.util.List<Object> inputlist = inputSet.getMessageDefOrDeclaredMessageDef();
		EList<org.jts.eclipse.cjsidl.messageDef> defs = output.getMessageDefs();
		EList<org.jts.eclipse.cjsidl.messageRef> refs = output.getTypeRefs();
		EList<org.jts.eclipse.cjsidl.messageScopedRef> scopedRefs = output.getScopedRefs();
		for(Object tmpobj: inputlist){
			if(tmpobj instanceof org.jts.jsidl.binding.MessageDef){
				defs.add(MessageDef.convert((org.jts.jsidl.binding.MessageDef)tmpobj, root));
			} else if(tmpobj instanceof org.jts.jsidl.binding.DeclaredMessageDef){
				EObject result = DeclaredMessageDef.convertToRef((org.jts.jsidl.binding.DeclaredMessageDef)tmpobj, root);
				if(result instanceof messageRef){
					refs.add((messageRef) result);
				} else if(result instanceof messageScopedRef){
					scopedRefs.add((messageScopedRef) result);
				} 
			} 
		}
		return output;
	}

}
