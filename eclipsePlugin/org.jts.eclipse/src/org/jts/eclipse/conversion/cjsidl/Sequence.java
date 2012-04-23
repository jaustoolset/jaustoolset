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

import java.util.ArrayList;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.jts.eclipse.cjsidl.containerRef;
import org.jts.eclipse.cjsidl.listDef;
import org.jts.eclipse.cjsidl.recordDef;
import org.jts.eclipse.cjsidl.sequenceDef;
import org.jts.eclipse.cjsidl.variantDef;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class Sequence extends ConversionUtil{

	/**
	 * Converts a CJSIDL sequence def to JSIDL
	 * @param def - a CJSIDL sequence def
	 * @return - resulting JSIDL object
	 * @throws ConversionException 
	 */
	public static org.jts.jsidl.binding.Sequence convert(sequenceDef def) throws ConversionException {
		org.jts.jsidl.binding.Sequence newdef = new org.jts.jsidl.binding.Sequence();
		newdef.setInterpretation(CJSIDLCommentToJSIDLInterp(def.getComment()));
		newdef.setName(def.getName());
		
		if(def.getOptional() != null){
			newdef.setOptional(def.getOptional().equals(OPTIONAL));
		}
		
		EList<EObject> idefs = def.getContainerTypeList();
		java.util.List<Object> newlist = newdef.getRecordOrDeclaredRecordOrList();
		
		for(EObject idef: idefs){
			if(idef instanceof containerRef){
				if(((containerRef)idef).getType() instanceof listDef){
					newlist.add(DeclaredList.convert((containerRef)idef));
				} else if(((containerRef)idef).getType() instanceof recordDef){
					newlist.add(DeclaredRecord.convert((containerRef)idef));
				} else if(((containerRef)idef).getType() instanceof sequenceDef){
					newlist.add(DeclaredSequence.convert((containerRef)idef));
				} else if(((containerRef)idef).getType() instanceof variantDef){
					newlist.add(DeclaredVariant.convert((containerRef)idef));
				} else if(((containerRef)idef).getTypeScoped().getType() instanceof listDef){
					newlist.add(DeclaredList.convert((containerRef)idef));
				} else if(((containerRef)idef).getTypeScoped().getType() instanceof recordDef){
					newlist.add(DeclaredRecord.convert((containerRef)idef));
				} else if(((containerRef)idef).getTypeScoped().getType() instanceof sequenceDef){
					newlist.add(DeclaredSequence.convert((containerRef)idef));
				} else if(((containerRef)idef).getTypeScoped().getType() instanceof variantDef){
					newlist.add(DeclaredVariant.convert((containerRef)idef));
				}
			} else if(idef instanceof listDef){
				newlist.add(List.convert((org.jts.eclipse.cjsidl.listDef) idef));
			} else if(idef instanceof recordDef){
				newlist.add(Record.convert((recordDef) idef));
			} else if(idef instanceof sequenceDef){
				newlist.add(Sequence.convert((sequenceDef) idef));
			} else if(idef instanceof variantDef){
				newlist.add(Variant.convert((variantDef) idef));
			}
				
			
		}
		org.jts.jsidl.binding.PresenceVector vec = new org.jts.jsidl.binding.PresenceVector();
		vec.setFieldTypeUnsigned(rangeToJSIDLType(0, idefs.size()));
		newdef.setPresenceVector(vec);

		return newdef;
	}

	/**
	 * Convert a JSIDL sequence to CJSIDL
	 * @param obj - JSIDL sequence
	 * @param root - servicedef/typeset that will contain the resulting sequence
	 * @return - resulting CJSIDL object
	 */
	public static sequenceDef convert(org.jts.jsidl.binding.Sequence obj, EObject root) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.sequenceDef newseq = factory.createsequenceDef();

		newseq.setName(obj.getName());
		newseq.setComment(JSIDLInterpToCJSIDLComment(obj.getInterpretation()));
		
		if(obj.isOptional()){
			newseq.setOptional(OPTIONAL);
		} 
		
		EList<EObject> newlist = newseq.getContainerTypeList();
		java.util.ArrayList<Object> tmpobjlist = (ArrayList<Object>) obj.getRecordOrDeclaredRecordOrList();
		for(int i=0; i<tmpobjlist.size(); i++){
			Object tmptype = tmpobjlist.get(i);
			if(tmptype instanceof org.jts.jsidl.binding.Record){
				newlist.add(Record.convert((org.jts.jsidl.binding.Record)tmptype, root));
			} else if(tmptype instanceof org.jts.jsidl.binding.List){
				newlist.add(List.convert((org.jts.jsidl.binding.List)tmptype, root));
			} else if(tmptype instanceof org.jts.jsidl.binding.Sequence){
				newlist.add(Sequence.convert((org.jts.jsidl.binding.Sequence)tmptype, root));
			} else if(tmptype instanceof org.jts.jsidl.binding.Variant){
				newlist.add(Variant.convert((org.jts.jsidl.binding.Variant)tmptype, root));
			} else if(tmptype instanceof org.jts.jsidl.binding.DeclaredRecord){
				newlist.add(DeclaredRecord.convertToContainerRef((org.jts.jsidl.binding.DeclaredRecord)tmptype, root));
			} else if(tmptype instanceof org.jts.jsidl.binding.DeclaredList){
				newlist.add(DeclaredList.convertToContainerRef((org.jts.jsidl.binding.DeclaredList)tmptype, root));
			} else if(tmptype instanceof org.jts.jsidl.binding.DeclaredSequence){
				newlist.add(DeclaredSequence.convertToContainerRef((org.jts.jsidl.binding.DeclaredSequence)tmptype, root));
			} else if(tmptype instanceof org.jts.jsidl.binding.DeclaredVariant){
				newlist.add(DeclaredVariant.convertToContainerRef((org.jts.jsidl.binding.DeclaredVariant)tmptype, root));
			}
			
		}

		return newseq;
	}

}
