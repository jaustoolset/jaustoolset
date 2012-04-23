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
LIABLE FOR ANY DIlistT, INDIlistT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.
 *********************  END OF LICENSE ***********************************/
package org.jts.eclipse.conversion.cjsidl;

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
public class List extends ConversionUtil{

	/**
	 * Converts a CJSIDL list to a JSIDL list
	 * @param listDef - the input CJSIDL list
	 * @return - resulting JSIDL list
	 * @throws ConversionException 
	 */
	public static org.jts.jsidl.binding.List convert(listDef listDef) throws ConversionException {
		org.jts.jsidl.binding.List newlist = new org.jts.jsidl.binding.List();
		
		newlist.setInterpretation(CJSIDLCommentToJSIDLInterp(listDef.getComment()));
		newlist.setName(listDef.getName());

		// if one was specified then set it to whatever it was. default is "required"
		if(listDef.getOptional() != null){
			newlist.setOptional(listDef.getOptional().equals(OPTIONAL));
		}
		
		
		String min = "0";
		String max = "0";
		// since JSIDL doesn't handle const references here, just set the value
		if(listDef.getMaxCount() != null){
			max = listDef.getMaxCount();
		} else if(listDef.getMaxCountRef() != null){
			max = listDef.getMaxCountRef().getConstVal().getConstValue();
		} else if(listDef.getMaxCountScoped() != null){
			min = listDef.getMaxCountScoped().getType().getConstValue();
		}
		if(listDef.getMinCount() != null){
			min = listDef.getMinCount();
		} else if(listDef.getMinCountRef() != null ){
			min = listDef.getMinCountRef().getConstVal().getConstValue();
		} else if(listDef.getMinCountScoped() != null){
			min = listDef.getMinCountScoped().getType().getConstValue();
		}

		
		newlist.setCountField(CountField.convert(listDef.getCountComment(), 
				null, min, max));

		// figure out what this is a list of
		if(listDef.getContainerRef() instanceof containerRef){
			if(((containerRef)listDef.getContainerRef()).getType() instanceof listDef){
				newlist.setDeclaredList(DeclaredList.convert(listDef.getContainerRef()));
			} else if(((containerRef)listDef.getContainerRef()).getType() instanceof recordDef){
				newlist.setDeclaredRecord(DeclaredRecord.convert(listDef.getContainerRef()));
			} else if(((containerRef)listDef.getContainerRef()).getType() instanceof sequenceDef){
				newlist.setDeclaredSequence(DeclaredSequence.convert(listDef.getContainerRef()));
			} else if(((containerRef)listDef.getContainerRef()).getType() instanceof variantDef){
				newlist.setDeclaredVariant(DeclaredVariant.convert(listDef.getContainerRef()));
			} else if(((containerRef)listDef.getContainerRef()).getTypeScoped().getType() instanceof listDef){
				newlist.setDeclaredList(DeclaredList.convert(listDef.getContainerRef()));
			} else if(((containerRef)listDef.getContainerRef()).getTypeScoped().getType() instanceof recordDef){
				newlist.setDeclaredRecord(DeclaredRecord.convert(listDef.getContainerRef()));
			} else if(((containerRef)listDef.getContainerRef()).getTypeScoped().getType() instanceof sequenceDef){
				newlist.setDeclaredSequence(DeclaredSequence.convert(listDef.getContainerRef()));
			} else if(((containerRef)listDef.getContainerRef()).getTypeScoped().getType() instanceof variantDef){
				newlist.setDeclaredVariant(DeclaredVariant.convert(listDef.getContainerRef()));
			} 

		} else if(listDef.getContainerDef() instanceof listDef){
			newlist.setList(List.convert((org.jts.eclipse.cjsidl.listDef) listDef.getContainerDef()));
		} else if(listDef.getContainerDef() instanceof recordDef){
			newlist.setRecord(Record.convert((recordDef) listDef.getContainerDef()));
		} else if(listDef.getContainerDef() instanceof sequenceDef){
			newlist.setSequence(Sequence.convert((sequenceDef) listDef.getContainerDef()));
		} else if(listDef.getContainerDef() instanceof variantDef){
			newlist.setVariant(Variant.convert((variantDef) listDef.getContainerDef()));
		}

		return newlist;
	}

	/**
	 * Converts from a JSIDL list to a CJSIDL list
	 * @param tmpdef - a JSIDL list
	 * @param root - root that will contain the new CJSIDL list
	 * @return - resulting CJSIDL list
	 */
	public static listDef convert(org.jts.jsidl.binding.List tmpdef, EObject root) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.listDef newlist = factory.createlistDef();

		newlist.setName(tmpdef.getName());
		newlist.setComment(JSIDLInterpToCJSIDLComment(tmpdef.getInterpretation()));

		if(tmpdef.isOptional()){
			newlist.setOptional(OPTIONAL);
		}
		
		newlist.setCountComment(JSIDLInterpToCJSIDLComment(tmpdef.getCountField().getInterpretation()));
		
		String max = getCountMax(tmpdef.getCountField().getMaxCount(), tmpdef.getCountField().getFieldTypeUnsigned());
		String min = getCountMin(tmpdef.getCountField().getMinCount(), tmpdef.getCountField().getFieldTypeUnsigned());
		newlist.setMaxCount(max);
		newlist.setMinCount(min);
		
		if(tmpdef.getDeclaredList() != null){
			newlist.setContainerRef(DeclaredList.convertToContainerRef(tmpdef.getDeclaredList(), root));
		} else if(tmpdef.getDeclaredRecord() != null){
			newlist.setContainerRef(DeclaredRecord.convertToContainerRef(tmpdef.getDeclaredRecord(), root));
		} else if(tmpdef.getDeclaredSequence() != null){
			newlist.setContainerRef(DeclaredSequence.convertToContainerRef(tmpdef.getDeclaredSequence(), root));
		} else if(tmpdef.getDeclaredVariant() != null){
			newlist.setContainerRef(DeclaredVariant.convertToContainerRef(tmpdef.getDeclaredVariant(), root));
		} else if(tmpdef.getList() != null){
			newlist.setContainerDef(List.convert(tmpdef.getList(), root));
		} else if(tmpdef.getRecord() != null){
			newlist.setContainerDef(Record.convert(tmpdef.getRecord(), root));
		} else if(tmpdef.getSequence() != null){
			newlist.setContainerDef(Sequence.convert(tmpdef.getSequence(), root));
		} else if(tmpdef.getVariant() != null){
			newlist.setContainerDef(Variant.convert(tmpdef.getVariant(), root));
		}

		return newlist;
	}

}
