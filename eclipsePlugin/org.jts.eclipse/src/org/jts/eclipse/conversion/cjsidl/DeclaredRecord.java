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
import org.jts.eclipse.cjsidl.declaredTypeSet;
import org.jts.eclipse.cjsidl.recordDef;
import org.jts.eclipse.cjsidl.scopedTypeId;
import org.jts.eclipse.cjsidl.typeReference;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class DeclaredRecord extends BaseTypeReference{

	/**
	 * Converts a JSIDL data binding to a CJSIDL object
	 * @param declaredRecord - JSIDL data binding
	 * @return - resulting CJSIDL object
	 */
	public static recordDef convert(org.jts.jsidl.binding.DeclaredRecord declaredRecord, EObject root) {
		// need to extract the EObject from the model 
		String refName = declaredRecord.getDeclaredTypeRef();

		recordDef ref = (recordDef) Conversion.referenceHelper.getEObjectByName(refName, root);
		return ref;
	}

	/**
	 * Converts a CJSIDL object to a JSIDL data binding
	 * @param typeref - CJSIDL object
	 * @return - resulting JSIDL data binding
	 */
	public static org.jts.jsidl.binding.DeclaredRecord convert(
			typeReference typeref) {
		org.jts.jsidl.binding.DeclaredRecord newfield = new org.jts.jsidl.binding.DeclaredRecord();
		
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
	public static org.jts.jsidl.binding.DeclaredRecord convert(
			containerRef typeRef) {
		org.jts.jsidl.binding.DeclaredRecord newfield = new org.jts.jsidl.binding.DeclaredRecord();
		
		newfield.setInterpretation(CJSIDLCommentToJSIDLInterp(typeRef.getComment()));
		String name = typeRef.getName().replace(ConversionUtil.mangled, "");
		newfield.setName(name);

		// if one was specified then set it to whatever it was. default is "required"
		if(typeRef.getOptional() != null){
			newfield.setOptional(typeRef.getOptional().equals(OPTIONAL));
		}
		if(typeRef.getType() != null){
			newfield.setDeclaredTypeRef(typeRef.getType().getName());//getNameFromEObject(typeRef.getType()));
		} else {
			newfield.setDeclaredTypeRef(ScopedTypeFromRef(typeRef.getTypeScoped()));
		}
		
		return newfield;
	}
	/**
	 * Converts a declared type to a container reference
	 * @param declaredRecord - the original container type
	 * @param root - parent of the container type reference
	 * @return - resulting containerRef
	 */
	public static containerRef convertToContainerRef(org.jts.jsidl.binding.DeclaredRecord declaredRecord, EObject root) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		containerRef ref = factory.createcontainerRef();
		ref.setComment(JSIDLInterpToCJSIDLComment(declaredRecord.getInterpretation()));
		String name = declaredRecord.getName();
		
		if(declaredRecord.isOptional()){
			ref.setOptional(OPTIONAL);
		} 

		String refName = declaredRecord.getDeclaredTypeRef();
		String tmpArr[] = refName.split("[.]");
		if(tmpArr.length == 1){
			// need to extract the EObject from the model 
			EObject tmpobj = Conversion.referenceHelper.getEObjectByName(name, root);
			if(tmpobj != null || name.equals(refName)){
				name = name + ConversionUtil.mangled;
			}
			ref.setType((containerDef) Conversion.referenceHelper.getEObjectByName(refName, root));
			if(ref.getType() == null){
				DeclaredTypeSet.unresolvedRefs.put(ref, refName);
			}
		} else if(tmpArr.length > 1){
			ref.setTypeScoped(Conversion.referenceHelper.getScopedContainerEObjectByName(java.util.Arrays.asList(tmpArr), root));
		}
		ref.setName(name);
		
		return ref;	
	}

	/**
	 * Converts a declared record to a scoped type or a simple reference depending on the name
	 * of the original object
	 * @param declDef - the declared record to process
	 * @param root - parent  of the object
	 * @return - resulting object (typReference or scopedTypeId)
	 */
	public static EObject convertToRef(
			org.jts.jsidl.binding.DeclaredRecord declDef, declaredTypeSet root) {
		String typename = declDef.getDeclaredTypeRef();
		String tmpArr[] = typename.split("[.]");
		if(tmpArr.length == 1){
			// local type reference, convert to typeReference
			CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
			org.jts.eclipse.cjsidl.typeReference ref = factory.createtypeReference();
			
			ref.setComment(JSIDLInterpToCJSIDLComment(declDef.getInterpretation()));
			// need to extract the EObject from the model 
			String name = declDef.getName();
			EObject tmpobj = Conversion.referenceHelper.getEObjectByName(name, root);
			if(tmpobj != null || name.equals(typename)){
				name = name + ConversionUtil.mangled;
			}
			ref.setName(name);
			
			
			if(declDef.isOptional()){
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
		
			ref.setComment(JSIDLInterpToCJSIDLComment(declDef.getInterpretation()));
			ref.setScopedName(declDef.getName());
			
			if(declDef.isOptional()){
				ref.setOptional(OPTIONAL);
			} 
			
			ref.setRef(Conversion.referenceHelper.getScopedEObjectByName(typename, root));

			return ref;
			
		}
	}
	/**
	 * Converts a CJSIDL scoped reference to a JSIDL data binding
	 * @param ref - the input reference
	 * @return - the corresponding JSIDL data binding
	 */
	public static Object convert(scopedTypeId ref) {
		org.jts.jsidl.binding.DeclaredRecord output = new org.jts.jsidl.binding.DeclaredRecord();
		String name = ref.getScopedName().replace(ConversionUtil.mangled, "");
		output.setName(name);
		output.setDeclaredTypeRef(ScopedTypeFromRef(ref.getRef()));
		output.setInterpretation(CJSIDLCommentToJSIDLInterp(ref.getComment()));

		// if one was specified then set it to whatever it was. default is "required"
		if(ref.getOptional() != null){
			output.setOptional(ref.getOptional().equals(OPTIONAL));
		}
		return output;
	}
	


}
