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
import org.jts.eclipse.cjsidl.containerDef;
import org.jts.eclipse.cjsidl.containerRef;
import org.jts.eclipse.cjsidl.footerDef;
import org.jts.eclipse.cjsidl.listDef;
import org.jts.eclipse.cjsidl.recordDef;
import org.jts.eclipse.cjsidl.scopedType;
import org.jts.eclipse.cjsidl.sequenceDef;
import org.jts.eclipse.cjsidl.variantDef;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class Footer extends ConversionUtil{

	/**
	 * Converts a CJSIDL def to a JSIDL type	
	 * @param def - input CJSIDL type def
	 * @return - resulting JSIDL type
	 * @throws ConversionException 
	 */
	public static org.jts.jsidl.binding.Footer convert(footerDef def) throws ConversionException {
		org.jts.jsidl.binding.Footer newelement = new org.jts.jsidl.binding.Footer();
		
		newelement.setName(def.getName());
		newelement.setInterpretation(CJSIDLCommentToJSIDLInterp(def.getComment()));
		
		if(def.getRecordListSequenceVariant() instanceof listDef){
			newelement.setList(List.convert((listDef) def.getRecordListSequenceVariant()));
		} else if(def.getRecordListSequenceVariant() instanceof recordDef){
			newelement.setRecord(Record.convert((recordDef) def.getRecordListSequenceVariant()));
		} else if(def.getRecordListSequenceVariant() instanceof sequenceDef){
			newelement.setSequence(Sequence.convert((sequenceDef) def.getRecordListSequenceVariant()));
		} else if(def.getRecordListSequenceVariant() instanceof variantDef){
			newelement.setVariant(Variant.convert((variantDef) def.getRecordListSequenceVariant()));
		} else if(def.getRecordListSequenceVariant() instanceof containerRef){
			if(((containerRef) def.getRecordListSequenceVariant()).getType() != null){
				containerDef tmpdef = ((containerRef) def.getRecordListSequenceVariant()).getType();
				if(tmpdef instanceof listDef){
					newelement.setDeclaredList(DeclaredList.convert((containerRef) def.getRecordListSequenceVariant()));
				} else if(tmpdef instanceof recordDef){
					newelement.setDeclaredRecord(DeclaredRecord.convert((containerRef) def.getRecordListSequenceVariant()));
				} else if(tmpdef instanceof sequenceDef){
					newelement.setDeclaredSequence(DeclaredSequence.convert((containerRef) def.getRecordListSequenceVariant()));
				} else if(tmpdef instanceof variantDef){
					newelement.setDeclaredVariant(DeclaredVariant.convert((containerRef) def.getRecordListSequenceVariant()));
				}				
			} else if(((containerRef) def.getRecordListSequenceVariant()).getTypeScoped() != null){
				scopedType tmpref = ((containerRef) def.getRecordListSequenceVariant()).getTypeScoped();
				containerDef tmpdef = (containerDef) tmpref.getType();
				if(tmpdef instanceof listDef){
					newelement.setDeclaredList(DeclaredList.convert((containerRef) def.getRecordListSequenceVariant()));
				} else if(tmpdef instanceof recordDef){
					newelement.setDeclaredRecord(DeclaredRecord.convert((containerRef) def.getRecordListSequenceVariant()));
				} else if(tmpdef instanceof sequenceDef){
					newelement.setDeclaredSequence(DeclaredSequence.convert((containerRef) def.getRecordListSequenceVariant()));
				} else if(tmpdef instanceof variantDef){
					newelement.setDeclaredVariant(DeclaredVariant.convert((containerRef) def.getRecordListSequenceVariant()));
				}				
			}
		}
		
		return newelement;
	}

	/**
	 * Converts a JSIDL type to a CJSIDL type
	 * @param footer - input JSIDL type
	 * @return - resulting CJSIDL type
	 */
	public static footerDef convert(org.jts.jsidl.binding.Footer element, EObject root) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.footerDef newdef = factory.createfooterDef();
		
		newdef.setComment(JSIDLInterpToCJSIDLComment(element.getInterpretation()));
		
		newdef.setName(element.getName());
		
		if(element.getList() != null){
			newdef.setRecordListSequenceVariant(List.convert(element.getList(), root));
		} else if (element.getRecord() != null){
			newdef.setRecordListSequenceVariant(Record.convert(element.getRecord(), root));
		} else if (element.getSequence() != null){
			newdef.setRecordListSequenceVariant(Sequence.convert(element.getSequence(), root));
		} else if (element.getVariant() != null){
			newdef.setRecordListSequenceVariant(Variant.convert(element.getVariant(), root));
		} else if (element.getDeclaredVariant() != null){
			newdef.setRecordListSequenceVariant(DeclaredVariant.convertToContainerRef(element.getDeclaredVariant(), root));
		} else if (element.getDeclaredSequence() != null){
			newdef.setRecordListSequenceVariant(DeclaredSequence.convertToContainerRef(element.getDeclaredSequence(), root));
		} else if (element.getDeclaredRecord() != null){
			newdef.setRecordListSequenceVariant(DeclaredRecord.convertToContainerRef(element.getDeclaredRecord(), root));
		} else if (element.getDeclaredList() != null){
			newdef.setRecordListSequenceVariant(DeclaredList.convertToContainerRef(element.getDeclaredList(), root));
		}
		
		return newdef;
	}

	/**
	 * Used to create an empty Footer in case one was not defined
	 * @param parentName name for the parent message
	 * @return an empty Footer
	 */
	public static org.jts.jsidl.binding.Footer getEmpty(String parentName) {
		org.jts.jsidl.binding.Footer newelement = new org.jts.jsidl.binding.Footer();
		
		newelement.setName(parentName + "_footer");
		newelement.setInterpretation("Default empty Footer added for JSIDL compatibility.");
				
		return newelement;
	}


}
