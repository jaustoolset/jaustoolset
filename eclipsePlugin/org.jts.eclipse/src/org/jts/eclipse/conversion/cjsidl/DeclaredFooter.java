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
import org.jts.eclipse.cjsidl.footerDef;
import org.jts.eclipse.cjsidl.footerRef;
import org.jts.eclipse.cjsidl.scopedTypeId;
import org.jts.eclipse.cjsidl.typeReference;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class DeclaredFooter extends BaseTypeReference{
	/**
	 * Converts a JSIDL data binding to a CJSIDL object
	 * @param declaredFooter - JSIDL data binding
	 * @return - resulting CJSIDL object
	 */
	public static footerDef convert(org.jts.jsidl.binding.DeclaredFooter declaredFooter, EObject root) {
		// need to extract the EObject from the model 
		String refName = declaredFooter.getDeclaredTypeRef();
		
		footerDef ref = (footerDef) Conversion.referenceHelper.getEObjectByName(refName, root);
		return ref;
	}
	/**
	 * Converts a CJSIDL object to a JSIDL data binding
	 * @param typeref - CJSIDL object
	 * @return - resulting JSIDL data binding
	 */
	public static org.jts.jsidl.binding.DeclaredFooter convert(
			typeReference typeref) {
		org.jts.jsidl.binding.DeclaredFooter newfield = new org.jts.jsidl.binding.DeclaredFooter();
		
		newfield.setInterpretation(CJSIDLCommentToJSIDLInterp(typeref.getComment()));
		String name = typeref.getName().replace(ConversionUtil.mangled, "");
		newfield.setName(name);

		newfield.setDeclaredTypeRef(getNameFromEObject(typeref.getType()));
		
		return newfield;
	}
	
	/** 
	 * Converts a CJSIDL footer reference to a JSIDL declared type
	 * @param typeref - a CJSIDL footerRef
	 * @return - the resulting JSIDL declared type
	 */
	public static org.jts.jsidl.binding.DeclaredFooter convert(footerRef typeref) {
		org.jts.jsidl.binding.DeclaredFooter newfield = new org.jts.jsidl.binding.DeclaredFooter();
		
		newfield.setInterpretation(CJSIDLCommentToJSIDLInterp(typeref.getComment()));
		String name = typeref.getName().replace(ConversionUtil.mangled, "");
		newfield.setName(name);
		
		String scopedRef = "";
		if(typeref.getScopedRef() != null){
			scopedRef = ScopedTypeFromRef(typeref.getScopedRef());
		} else if(typeref.getTypeRef() != null){
			scopedRef = getNameFromEObject(typeref.getTypeRef());
		}
		newfield.setDeclaredTypeRef(scopedRef);
		
		return newfield;
	}
	
	/**
	 * Converts a JSIDL data binding to a CJSIDL object
	 * @param declaredBitField - JSIDL data binding
	 * @return - resulting CJSIDL object
	 */
	public static EObject convertToRef(org.jts.jsidl.binding.DeclaredFooter declaredField, EObject root) {
		String typename = declaredField.getDeclaredTypeRef();
		String tmpArr[] = typename.split("[.]");
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		
		footerRef ref = factory.createfooterRef();
		ref.setName(declaredField.getName().replace(ConversionUtil.mangled, ""));
		ref.setComment(JSIDLInterpToCJSIDLComment(declaredField.getInterpretation()));

		if(tmpArr.length == 1){

			// need to extract the EObject from the model 
			String refName = declaredField.getDeclaredTypeRef();
			
			String name = declaredField.getName();
			EObject tmpobj = Conversion.referenceHelper.getEObjectByName(name, root);
			if(tmpobj != null || name.equals(typename)){
				name = name + ConversionUtil.mangled;
			}
			ref.setName(name);

			ref.setTypeRef((footerDef) Conversion.referenceHelper.getEObjectByName(refName, root));
			if(ref.getTypeRef() == null){
				DeclaredTypeSet.unresolvedRefs.put(ref, refName);
			}
			
		} else {
			// reference located in a different file, convert to scopedTypeId
			org.jts.eclipse.cjsidl.footerScopedRef scopedref = Conversion.referenceHelper.getScopedFooterEObjectByName(java.util.Arrays.asList(tmpArr), root);
			ref.setScopedRef(scopedref);
		}
		return ref;
	}
	/**
	 * Converts a CJSIDL scoped reference to a JSIDL data binding
	 * @param ref - the input reference
	 * @return - the corresponding JSIDL data binding
	 */
	public static Object convert(scopedTypeId ref) {
		org.jts.jsidl.binding.DeclaredFooter output = new org.jts.jsidl.binding.DeclaredFooter();
		String name = ref.getScopedName().replace(ConversionUtil.mangled, "");
		output.setName(name);
		output.setDeclaredTypeRef(ScopedTypeFromRef(ref.getRef()));
		output.setInterpretation(CJSIDLCommentToJSIDLInterp(ref.getComment()));

		output.setDeclaredTypeRef(ScopedTypeFromRef(ref.getRef()));
		return output;
	}
}
