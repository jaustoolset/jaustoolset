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
import org.jts.eclipse.cjsidl.containerDef;
import org.jts.eclipse.cjsidl.containerRef;
import org.jts.eclipse.cjsidl.listDef;
import org.jts.eclipse.cjsidl.recordDef;
import org.jts.eclipse.cjsidl.sequenceDef;
import org.jts.eclipse.cjsidl.taggedItemDef;
import org.jts.eclipse.cjsidl.variantDef;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class Variant extends ConversionUtil{

	/**
	 * Converts cjsidl variant to a JSIDL variant
	 * @param tmpdef - the input CJSIDL definition
	 * @return - the resulting JSIDL variant
	 * @throws ConversionException 
	 */
	public static org.jts.jsidl.binding.Variant convert(variantDef tmpdef) throws ConversionException {
		org.jts.jsidl.binding.Variant var = new org.jts.jsidl.binding.Variant();
		var.setInterpretation(CJSIDLCommentToJSIDLInterp(tmpdef.getComment()));
		var.setName(tmpdef.getName());
		
		if(tmpdef.getOptional() != null){
			var.setOptional(tmpdef.getOptional().equals(OPTIONAL));
		}
		
		org.jts.jsidl.binding.VtagField tag = new org.jts.jsidl.binding.VtagField();
		// since JSIDL doesn't handle references here, just get the values
		String min = "0";
		String max = "0";
		
		tag.setInterpretation(CJSIDLCommentToJSIDLInterp(tmpdef.getVtagComment()));
		if(tmpdef.getMaxCount() != null){
			max = tmpdef.getMaxCount();
		} else if(tmpdef.getMaxCountRef() != null){
			max = tmpdef.getMaxCountRef().getConstVal().getConstValue();
		} else if(tmpdef.getMaxCountScoped() != null){
			min = tmpdef.getMaxCountScoped().getType().getConstValue();
		}
		if(tmpdef.getMinCount() != null){
			min = tmpdef.getMinCount();
		} else if(tmpdef.getMinCountRef() != null ){
			min = tmpdef.getMinCountRef().getConstVal().getConstValue();
		} else if(tmpdef.getMinCountScoped() != null){
			min = tmpdef.getMinCountScoped().getType().getConstValue();
		}
		tag.setMaxCount(max);
		tag.setMinCount(min);
		
		// calculate the type, since we don't use type for CJSIDL
		tag.setFieldTypeUnsigned(rangeToJSIDLType(Long.valueOf(min),Long.valueOf(max)));
		
		var.setVtagField(tag);	
		
		EList<taggedItemDef> taggedList = tmpdef.getItemList();
		java.util.List<Object> newList = var.getRecordOrDeclaredRecordOrList();
		for(taggedItemDef def: taggedList){
			
			// check the container defs
			if(def.getContainerDef() != null){
				if(def.getContainerDef() instanceof listDef){
					org.jts.jsidl.binding.List list = List.convert((listDef) def.getContainerDef());
					newList.add((Object)list);
				} else if(def.getContainerDef() instanceof recordDef){
					org.jts.jsidl.binding.Record rec = Record.convert((recordDef) def.getContainerDef());
					newList.add((Object)rec);
				} else if(def.getContainerDef() instanceof sequenceDef){
					org.jts.jsidl.binding.Sequence seq = Sequence.convert((sequenceDef) def.getContainerDef());
					newList.add((Object)seq);
				} else if(def.getContainerDef() instanceof variantDef){
					org.jts.jsidl.binding.Variant vartype = Variant.convert((variantDef) def.getContainerDef());
					newList.add((Object)vartype);
				}
			}
			// check for a reference
			if(def.getContainerRef() != null){
				if(def.getContainerRef().getType() instanceof listDef){
					org.jts.jsidl.binding.DeclaredList declList = DeclaredList.convert(def.getContainerRef());
					newList.add((Object)declList);
				} else if(def.getContainerRef().getType() instanceof recordDef){
					org.jts.jsidl.binding.DeclaredRecord declRec = DeclaredRecord.convert(def.getContainerRef());
					newList.add((Object)declRec);
				} else if(def.getContainerRef().getType() instanceof sequenceDef){
					org.jts.jsidl.binding.DeclaredSequence declSeq = DeclaredSequence.convert(def.getContainerRef());
					newList.add((Object)declSeq);
				} else if(def.getContainerRef().getType() instanceof variantDef){
					org.jts.jsidl.binding.DeclaredVariant declVar = DeclaredVariant.convert(def.getContainerRef());
					newList.add((Object)declVar);
				} else if(def.getContainerRef().getTypeScoped().getType() instanceof listDef){
					org.jts.jsidl.binding.DeclaredList declList = DeclaredList.convert(def.getContainerRef());
					newList.add((Object)declList);
				} else if(def.getContainerRef().getTypeScoped().getType() instanceof recordDef){
					org.jts.jsidl.binding.DeclaredRecord declRec = DeclaredRecord.convert(def.getContainerRef());
					newList.add((Object)declRec);
				} else if(def.getContainerRef().getTypeScoped().getType() instanceof sequenceDef){
					org.jts.jsidl.binding.DeclaredSequence declSeq = DeclaredSequence.convert(def.getContainerRef());
					newList.add((Object)declSeq);
				} else if(def.getContainerRef().getTypeScoped().getType() instanceof variantDef){
					org.jts.jsidl.binding.DeclaredVariant declVar = DeclaredVariant.convert(def.getContainerRef());
					newList.add((Object)declVar);
				} 
 
			}
		}
		return var;
	}

	/**
	 * Convert from JSIDL variant to CJSIDL variant
	 * @param tmpdef - the input variant definition
	 * @param root - the root document object used to resolve references
	 * @return - the resulting CJSIDL variant object
	 */
	public static variantDef convert(org.jts.jsidl.binding.Variant tmpdef, EObject root) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.variantDef newvar = factory.createvariantDef();

		newvar.setName(tmpdef.getName());
		newvar.setComment(JSIDLInterpToCJSIDLComment(tmpdef.getInterpretation()));
		
		// vtag data
		newvar.setVtagComment(JSIDLInterpToCJSIDLComment(tmpdef.getVtagField().getInterpretation()));		
		newvar.setMaxCount(tmpdef.getVtagField().getMaxCount());
		newvar.setMinCount(tmpdef.getVtagField().getMinCount());
		
		
		if(tmpdef.isOptional()){
			newvar.setOptional(OPTIONAL);
		} 

		// references are not allowed in JSIDL here, so these don't need to be resolved
//		newvar.setMaxCountRef(value)
//		newvar.setMinCountRef(value)
//		
		java.util.List<Object> objlist = tmpdef.getRecordOrDeclaredRecordOrList();
		EList<taggedItemDef> newList = newvar.getItemList();
		for(Object tmpobj: objlist){
			taggedItemDef newdef = factory.createtaggedItemDef();
			containerDef container = null;
			containerRef ref = null;
			 
			if(tmpobj instanceof org.jts.jsidl.binding.Record){
				container = (Record.convert((org.jts.jsidl.binding.Record)tmpobj, root));
			} else if(tmpobj instanceof org.jts.jsidl.binding.List){
				 container = (List.convert((org.jts.jsidl.binding.List)tmpobj, root));
			} else if(tmpobj instanceof org.jts.jsidl.binding.Sequence){
				container = (Sequence.convert((org.jts.jsidl.binding.Sequence)tmpobj, root));
			} else if(tmpobj instanceof org.jts.jsidl.binding.Variant){
				container = (Variant.convert((org.jts.jsidl.binding.Variant)tmpobj, root));
			} else if(tmpobj instanceof org.jts.jsidl.binding.DeclaredRecord){
				ref = DeclaredRecord.convertToContainerRef((org.jts.jsidl.binding.DeclaredRecord)tmpobj, root);
			} else if(tmpobj instanceof org.jts.jsidl.binding.DeclaredList){
				ref = DeclaredList.convertToContainerRef((org.jts.jsidl.binding.DeclaredList)tmpobj, root);
			} else if(tmpobj instanceof org.jts.jsidl.binding.DeclaredSequence){
				ref = DeclaredSequence.convertToContainerRef((org.jts.jsidl.binding.DeclaredSequence)tmpobj, root);
			} else if(tmpobj instanceof org.jts.jsidl.binding.DeclaredVariant){
				ref = DeclaredVariant.convertToContainerRef((org.jts.jsidl.binding.DeclaredVariant)tmpobj, root);
			}
			
			if(container != null){
				newdef.setContainerDef(container);
			}
			if(ref != null){
				newdef.setContainerRef(ref);
			}
			
			newList.add(newdef);
		}
		
		return newvar;
	}

}
