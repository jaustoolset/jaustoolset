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
import org.jts.eclipse.cjsidl.fixedFieldDef;
import org.jts.eclipse.cjsidl.scopedType;
import org.jts.eclipse.cjsidl.scopedTypeId;
import org.jts.eclipse.cjsidl.typeReference;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class DeclaredFixedField extends BaseTypeReference{

	/**
	 * Retrieves a specific typedef by name and returns that object
	 * @param declaredFixedField - the reference that holds the type name to look for.
	 * @param root- the object (serviceDef, typeset, constantset) that the reference comes from.
	 * @return - the fixedFieldDef that was found.
	 */
	public static fixedFieldDef convert(org.jts.jsidl.binding.DeclaredFixedField declaredFixedField, EObject root) {

		String typename = declaredFixedField.getDeclaredTypeRef();

		org.jts.eclipse.cjsidl.fixedFieldDef newdef = (fixedFieldDef) Conversion.referenceHelper.getEObjectByName(typename, root);
		return newdef;
	}
	/**
	 * Converts a CJSIDL object to a JSIDL data binding
	 * @param typeref - CJSIDL object
	 * @return - resulting JSIDL data binding
	 */
	public static org.jts.jsidl.binding.DeclaredFixedField convert(
			typeReference typeref) {
		org.jts.jsidl.binding.DeclaredFixedField newfield = new org.jts.jsidl.binding.DeclaredFixedField();
		
		newfield.setInterpretation(CJSIDLCommentToJSIDLInterp(typeref.getComment()));
		String name = typeref.getName().replace(ConversionUtil.mangled, "");
		newfield.setName(name);

		// if one was specified then set it to whatever it was. default is "required"
		if(typeref.getOptional() != null){
			newfield.setOptional(typeref.getOptional().equals(OPTIONAL));
		}
		
		newfield.setDeclaredTypeRef(getNameFromEObject(typeref.getType()));
		
		return newfield;
	}
	
	/**
	 * Converts a JSIDL data binding to either a typeReference or scopedTypeId
	 * depending on the nature of the type name 
	 * @param declaredFixedField - input declared fixed field def
	 * @param root - the typeset that contains this definition
	 * @return - the resulting reference
	 */
	public static EObject convertToRef(org.jts.jsidl.binding.DeclaredFixedField declaredFixedField, EObject root){
		String typename = declaredFixedField.getDeclaredTypeRef();
		String tmpArr[] = typename.split("[.]");
		if(tmpArr.length == 1){
			// local type reference, convert to typeReference
			CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
			org.jts.eclipse.cjsidl.typeReference ref = factory.createtypeReference();
			
			ref.setComment(JSIDLInterpToCJSIDLComment(declaredFixedField.getInterpretation()));
			
			String name = declaredFixedField.getName();
			EObject tmpobj = Conversion.referenceHelper.getEObjectByName(name, root);
			if(tmpobj != null || name.equals(typename)){
				name = name + ConversionUtil.mangled;
			}
			ref.setName(name);
			
			if(declaredFixedField.isOptional()){
				ref.setOptional(OPTIONAL);
			} 
			
			ref.setType(Conversion.referenceHelper.getEObjectByName(typename, root));
			if(ref.getType() == null){
				DeclaredTypeSet.unresolvedRefs.put(ref, typename);
			}

			return ref;
			
		} else {
			// reference located in a different file, convert to scopedTypeId
			CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
			org.jts.eclipse.cjsidl.scopedTypeId ref = factory.createscopedTypeId();
		
			ref.setComment(JSIDLInterpToCJSIDLComment(declaredFixedField.getInterpretation()));
			ref.setScopedName(declaredFixedField.getName());
			
			if(declaredFixedField.isOptional()){
				ref.setOptional(OPTIONAL);
			} 
			
			ref.setRef(Conversion.referenceHelper.getScopedEObjectByName(typename, root));

			return ref;
			
		}
			

	}
	
	public static org.jts.jsidl.binding.DeclaredFixedField convertToRef(
			fixedFieldDef type) {
		org.jts.jsidl.binding.DeclaredFixedField newfield = new org.jts.jsidl.binding.DeclaredFixedField();
		
		newfield.setInterpretation(CJSIDLCommentToJSIDLInterp(type.getComment()));
		String name = type.getName().replace(ConversionUtil.mangled, "") + "_ArrayElement";
		newfield.setName(name);
		
		if(type.getOptional() != null){
			newfield.setOptional(type.getOptional().equals(OPTIONAL));
		}
		
		newfield.setDeclaredTypeRef(type.getName());
	
		return newfield;
	}
	/**
	 * Converts a CJSIDL scoped reference to a JSIDL data binding
	 * @param ref - the input reference
	 * @return - the corresponding JSIDL data binding
	 */
	public static Object convert(scopedTypeId ref) {
		org.jts.jsidl.binding.DeclaredFixedField output = new org.jts.jsidl.binding.DeclaredFixedField();
		String name = ref.getScopedName().replace(ConversionUtil.mangled, "");
		output.setName(name);
		output.setDeclaredTypeRef(ScopedTypeFromRef(ref.getRef()));
		output.setInterpretation(CJSIDLCommentToJSIDLInterp(ref.getComment()));

		// if one was specified then set it to whatever it was. default is "required"
		if(ref.getOptional() != null){
			output.setOptional(ref.getOptional().equals(OPTIONAL));
		}
		output.setDeclaredTypeRef(ScopedTypeFromRef(ref.getRef()));
		return output;
	}
	public static org.jts.jsidl.binding.DeclaredFixedField convertToRef(
			String name, scopedType sctype) {
		org.jts.jsidl.binding.DeclaredFixedField output = new org.jts.jsidl.binding.DeclaredFixedField();
		name = name.replace(ConversionUtil.mangled, "");
		output.setName(name + "_ArrayElement");
		output.setDeclaredTypeRef(ScopedTypeFromRef(sctype));
		
		// there is no room for a comment on the type within the arraydef for CJSIDL
		//output.setInterpretation(CJSIDLCommentToJSIDLInterp(ref.getComment()));

		// it always exists or it couldn't be an array
		output.setOptional(false);
		
		return output;
	}

}
