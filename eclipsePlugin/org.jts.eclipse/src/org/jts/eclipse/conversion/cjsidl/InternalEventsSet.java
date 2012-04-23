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
import org.jts.eclipse.cjsidl.internalEventSet;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class InternalEventsSet extends ConversionUtil{

	/**
	 * Converts a CJSIDL events set to a JSIDL events set
	 * @param internalEventSet - the input CJSIDL interna events set
	 * @return - resulting JSIDL events set
	 * @throws ConversionException 
	 */
	public static org.jts.jsidl.binding.InternalEventsSet convert(internalEventSet internalEventSet) throws ConversionException {
		org.jts.jsidl.binding.InternalEventsSet outputset = new org.jts.jsidl.binding.InternalEventsSet();
		
		outputset.setInterpretation(CJSIDLCommentToJSIDLInterp(internalEventSet.getComment()));

		EList<EObject> inputdefs = internalEventSet.getDefs();
		
		java.util.List<Object> outputdefs = outputset.getEventDefOrDeclaredEventDef();
		for(EObject def: inputdefs){
			if(def instanceof org.jts.eclipse.cjsidl.eventDef){
				outputdefs.add((Object)EventDef.convert((org.jts.eclipse.cjsidl.eventDef)def));
			} else if(def instanceof org.jts.eclipse.cjsidl.declaredEventDef){
				outputdefs.add((Object)DeclaredEventDef.convert((org.jts.eclipse.cjsidl.declaredEventDef)def));
			}
		}
		
		return outputset;
	}

	/**
	 * Converts a JSIDL events set to a CJSIDL events set
	 * @param inputset - JSIDL events set to convert
	 * @param root - root object for the set(serviceDef)
	 * @return - resulting CJSIDL object
	 */
	public static internalEventSet convert(org.jts.jsidl.binding.InternalEventsSet inputset, EObject root) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.internalEventSet output = factory.createinternalEventSet();

		output.setComment(JSIDLInterpToCJSIDLComment(inputset.getInterpretation()));
		java.util.List<Object> inputdefs = inputset.getEventDefOrDeclaredEventDef();
		EList<EObject> outputset = output.getDefs();
		for(Object tmpobj: inputdefs){
			if(tmpobj instanceof org.jts.jsidl.binding.EventDef){
				outputset.add(EventDef.convert((org.jts.jsidl.binding.EventDef)tmpobj, root));
			} else if(tmpobj instanceof org.jts.jsidl.binding.DeclaredEventDef){
				outputset.add(DeclaredEventDef.convert((org.jts.jsidl.binding.DeclaredEventDef)tmpobj, root));
			}
		}
		
		return output;
	}

}
