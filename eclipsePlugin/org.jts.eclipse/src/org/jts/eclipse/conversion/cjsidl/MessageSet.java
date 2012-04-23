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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.jts.eclipse.cjsidl.messageDef;
import org.jts.eclipse.cjsidl.messageRef;
import org.jts.eclipse.cjsidl.messageSet;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class MessageSet extends ConversionUtil{
	// used to store objects that reference messages which haven't been processed yet
	public static Map<messageRef, String> messageMap;
	/**
	 * Converts a message set from CJSIDL to JSIDL
	 * @param messageSet - the input CJSIDL message set
	 * @return - resulting JSIDL message set
	 * @throws ConversionException 
	 */
	public static org.jts.jsidl.binding.MessageSet convert(messageSet messageSet) throws ConversionException {
		org.jts.jsidl.binding.MessageSet newset = new org.jts.jsidl.binding.MessageSet();
		newset.setInterpretation(CJSIDLCommentToJSIDLInterp(messageSet.getComment()));
		
		newset.setInputSet(InputSet.convert(messageSet.getInputSet(), messageSet.getInputComment()));
		newset.setOutputSet(OutputSet.convert(messageSet.getOutputSet(), messageSet.getOutputComment()));
		
		return newset;
	}

	/**
	 * Converts a JSIDL message set to CJSIDL
	 * @param messageSet - the JSIDL message set
	 * @param root - root object that will contain the message set(serviceDef)
	 * @return - resulting CJSIDL message set
	 */
	public static messageSet convert(org.jts.jsidl.binding.MessageSet messageSet, EObject root) {
		// some references cannot be resolved due to processing order so we store them here for later.
		messageMap = new HashMap<messageRef, String>();
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.messageSet output = factory.createmessageSet();
		output.setInputComment(JSIDLInterpToCJSIDLComment(messageSet.getInputSet().getInterpretation()));
		output.setOutputComment(JSIDLInterpToCJSIDLComment(messageSet.getOutputSet().getInterpretation()));
		
		output.setComment(JSIDLInterpToCJSIDLComment(messageSet.getInterpretation()));
		output.setInputSet(InputSet.convert(messageSet.getInputSet(), root));
		output.setOutputSet(OutputSet.convert(messageSet.getOutputSet(), root));
		
		postProcessMissingMessageRefs(output);
		return output;
	}

	/**
	 * Resolves missing references due to processing order. called after other processing is complete.
	 * @param output - a message set to be processed.
	 */
	private static void postProcessMissingMessageRefs(messageSet output) {
		Set<messageRef> refs = messageMap.keySet();
		
		for(messageRef ref: refs){
			boolean found = false;
			String name = messageMap.get(ref);
			EList<messageDef> defs = output.getInputSet().getMessageDefs();
			for(messageDef def: defs){
				if(def.getName().equals(name)){
					found = true;
					ref.setRef(def);
					break;
				}
			}
			if(!found){
				EList<messageDef> defs2 = output.getOutputSet().getMessageDefs();
				for(messageDef def: defs2){
					if(def.getName().equals(name)){
						found = true;
						ref.setRef(def);
						break;
					}
				}
			}
			if(!found){
                Logger.getLogger("CJSIDL").log(Level.SEVERE,
                        "messageDef \"" + name + "\"  was not found within the messageset." + output);
			}
			
		}
		
	}

}
