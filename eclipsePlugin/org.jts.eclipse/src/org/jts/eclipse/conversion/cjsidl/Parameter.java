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

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.ecore.EObject;
import org.jts.eclipse.cjsidl.eventDef;
import org.jts.eclipse.cjsidl.messageDef;
import org.jts.eclipse.cjsidl.messageRef;
import org.jts.eclipse.cjsidl.messageScopedRef;
import org.jts.eclipse.cjsidl.scopedEventType;
import org.jts.eclipse.cjsidl.serviceDef;
import org.jts.eclipse.cjsidl.transParam;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class Parameter extends ConversionSubEObjectReferenceHelper{

	/**
	 * Converts a JSIDL transition parameter to CJSIDL  
	 * @param param - a JSIDL transition parameter
	 * @param currentObj - the serviceDef containing the parent transition
	 * @return - resulting CJSIDL transition param
	 */
	public static transParam convertToTransitionParam(org.jts.jsidl.binding.Parameter param, serviceDef currentObj) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		transParam output = factory.createtransParam();
		
		output.setName(param.getValue());
		output.setComment(JSIDLInterpToCJSIDLComment(param.getInterpretation()));
		if(param.getType().equals("unsigned byte") || param.getType().equals("unsigned short") ||
				param.getType().equals("unsigned int") || param.getType().equals("unsigned long")){
			output.setUnsignedType(JSIDLTypeToCJSIDLType(param.getType()));
			
		} else {
			String type[] = param.getType().split("[.]");
			EObject result = null;
			if(type.length == 1){
				result =Conversion.referenceHelper.getEventEObjectByName(param.getType(), currentObj); 
				if(result == null){
					result = Conversion.referenceHelper.getEObjectFromMessageSet(currentObj, param.getType());
				}
				output.setType(result);
			} else if(type.length > 1){
				result = Conversion.referenceHelper.getSubtypeByName(param.getType(), currentObj);
				output.setScopedEventType((scopedEventType) result);
			}
		}


		return output;
	}

	/**
	 * Converts a CJSIDL transition param to JSIDL
	 * @param tmpparam - a CJSIDL transition param
	 * @return - resulting JSIDL param
	 */
	public static org.jts.jsidl.binding.Parameter convert(transParam tmpparam) {
		org.jts.jsidl.binding.Parameter newparam = new org.jts.jsidl.binding.Parameter();
		
		newparam.setInterpretation(CJSIDLCommentToJSIDLInterp(tmpparam.getComment()));
		newparam.setValue(tmpparam.getName());
	
		// transition parameters can only be of these types
		if(tmpparam.getType() instanceof messageDef){
			newparam.setType(((messageDef)tmpparam.getType()).getName());
		} else if(tmpparam.getType() instanceof messageRef){
			newparam.setType(((messageRef)tmpparam.getType()).getName());			
		} else if(tmpparam.getType() instanceof eventDef){
			newparam.setType(((eventDef)tmpparam.getType()).getName());
		} else if(tmpparam.getScopedEventType() != null){
			newparam.setType(getNameFromSubEObject(tmpparam.getScopedEventType()));
		} else if(tmpparam.getType() instanceof messageScopedRef){
			newparam.setType(((messageScopedRef)tmpparam.getType()).getName());
		} else if(tmpparam.getUnsignedType() != null){
			newparam.setType(CJSIDLTypeToShortenedJSIDLType(tmpparam.getUnsignedType()));
		}
		else {
            Logger.getLogger("CJSIDL").log(Level.SEVERE,
            "Unexpected object type while converting parameter.");
		}
			
		return newparam;
	}

}
