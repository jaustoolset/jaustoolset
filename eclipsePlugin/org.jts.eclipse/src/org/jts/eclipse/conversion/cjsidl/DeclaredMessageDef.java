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
import org.jts.eclipse.cjsidl.messageDef;
import org.jts.eclipse.cjsidl.messageRef;
import org.jts.eclipse.cjsidl.messageScopedRef;
import org.jts.eclipse.cjsidl.scopedTypeId;
import org.jts.eclipse.cjsidl.typeReference;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author cmessmer
 *
 */
public class DeclaredMessageDef extends BaseTypeReference{
	/**
	 * Converts a JSIDL data binding to a CJSIDL object
	 * @param declaredMessageDef - JSIDL data binding
	 * @return - resulting CJSIDL object
	 */
	public static messageRef convert(org.jts.jsidl.binding.DeclaredMessageDef declaredMessageDef, EObject root) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		messageRef ref = factory.createmessageRef();
		ref.setName(declaredMessageDef.getName());

		// need to extract the EObject from the model 
		String refName = declaredMessageDef.getDeclaredTypeRef();
		ref.setRef((org.jts.eclipse.cjsidl.messageDef)Conversion.referenceHelper.getEObjectByName(refName, root));
		ref.setComment(JSIDLInterpToCJSIDLComment(declaredMessageDef.getInterpretation()));

		return ref;
	}
	/**
	 * Converts a CJSIDL object to a JSIDL data binding
	 * @param typeref - CJSIDL object
	 * @return - resulting JSIDL data binding
	 */
	public static org.jts.jsidl.binding.DeclaredMessageDef convert(
			typeReference typeref) {
		org.jts.jsidl.binding.DeclaredMessageDef newfield = new org.jts.jsidl.binding.DeclaredMessageDef();
		
		newfield.setInterpretation(CJSIDLCommentToJSIDLInterp(typeref.getComment()));
		String name = typeref.getName().replace(ConversionUtil.mangled, "");
		newfield.setName(name);

		newfield.setDeclaredTypeRef(getNameFromEObject(typeref.getType()));
		
		return newfield;
	}
	/**
	 * Converts a message reference into a JSIDL data binding
	 * @param typeref - the input reference
	 * @return - the corresponding JSIDL object
	 */
	public static Object convert(messageRef typeref) {
		org.jts.jsidl.binding.DeclaredMessageDef newfield = new org.jts.jsidl.binding.DeclaredMessageDef();
		
		newfield.setInterpretation(CJSIDLCommentToJSIDLInterp(typeref.getComment()));
		String name = typeref.getName().replace(ConversionUtil.mangled, "");
		newfield.setName(name);

		newfield.setDeclaredTypeRef(getNameFromEObject(typeref.getRef()));
		
		return newfield;
	}
	/**
	 * Converts a JSIDL message reference into a messageRef or messageScopedRef
	 * @param declDef - the input JSIDL message reference
	 * @param root - the root object containing the message reference
	 * @return - the corresponding CJSIDL object
	 */
	public static EObject convertToRef(	org.jts.jsidl.binding.DeclaredMessageDef declDef, EObject root) {
		String typename = declDef.getDeclaredTypeRef();
		String tmpArr[] = typename.split("[.]");
		if(tmpArr.length == 1){
			// local type reference, convert to typeReference
			CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
			org.jts.eclipse.cjsidl.messageRef ref = factory.createmessageRef();
			
			ref.setComment(JSIDLInterpToCJSIDLComment(declDef.getInterpretation()));
			String name = declDef.getName();
			EObject tmpobj = Conversion.referenceHelper.getEObjectByName(name, root);
			if(tmpobj != null || name.equals(typename)){
				name = name + ConversionUtil.mangled;
			}
			ref.setName(name);
			EObject result = Conversion.referenceHelper.getEObjectByName(typename, root);	
			
			if(result instanceof messageDef){
				ref.setRef((messageDef) result);
			} else if(result == null){ 
				MessageSet.messageMap.put(ref, typename);
			} else {
                Logger.getLogger("CJSIDL").log(Level.SEVERE,
                        "Unexpected object type: expecting messageDef \"" + typename + "\" to have been declared before this line.  No definition was found.");
			}

			return ref;
			
		} else {
			// reference located in a different file, convert to scopedTypeId
			CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
			org.jts.eclipse.cjsidl.messageScopedRef ref = factory.createmessageScopedRef();
		
			ref.setComment(JSIDLInterpToCJSIDLComment(declDef.getInterpretation()));
			ref.setName(declDef.getName());
			EList<EObject> scopeList = ref.getScopes();
			EObject currentObj = root;
			EObject referenceObject = null;
			
			// convert array to array list
			java.util.List<String> subnames = new ArrayList<String> ();
			for(int i=0; i< tmpArr.length; i++){
				subnames.add(tmpArr[i]);
			}
			String typeName = null;
			for(String subname: subnames){
				// the last subname is the name of the type, so its not a root object reference
				if(subnames.indexOf(subname) == subnames.size()-1){
					typeName = subname;
					continue;
				}
				// this is the first name and is only separate from the rest of the names
				// to avoid making a fix to the grammar and scope provider
				else if(subnames.indexOf(subname) == 0){
					referenceObject = Conversion.referenceHelper.getEObjectByRef(subname, currentObj);
					EObject refObj = Conversion.referenceHelper.findRefEObject(subname, currentObj);
					ref.setScope(refObj);
				} else {
					currentObj = referenceObject;
					referenceObject = Conversion.referenceHelper.getEObjectByRef(subname, referenceObject);
					EObject refObj = Conversion.referenceHelper.findRefEObject(subname, currentObj);
					scopeList.add(refObj);
				}
				
			}
			EObject result = Conversion.referenceHelper.findEObject(typeName, referenceObject);
			if(result instanceof messageDef){
				ref.setRef((messageDef)result);
			}

			return ref;			
		}
	}
	/**
	 * Converts a CJSIDL scoped reference to a JSIDL data binding
	 * @param ref - the input reference
	 * @return - the corresponding JSIDL data binding
	 */
	public static Object convert(scopedTypeId ref) {
		org.jts.jsidl.binding.DeclaredMessageDef output = new org.jts.jsidl.binding.DeclaredMessageDef();
		String name = ref.getScopedName().replace(ConversionUtil.mangled, "");
		output.setName(name);
		output.setDeclaredTypeRef(ScopedTypeFromRef(ref.getRef()));
		output.setInterpretation(CJSIDLCommentToJSIDLInterp(ref.getComment()));

		return output;
	}
	/**
	 * Converts a scoped message reference into a JSIDL object
	 * @param ref - input scoped message reference
	 * @return - resulting JSIDL object
	 */
	public static Object convert(messageScopedRef ref) {
		org.jts.jsidl.binding.DeclaredMessageDef output = new org.jts.jsidl.binding.DeclaredMessageDef();
		String name = ref.getName().replace(ConversionUtil.mangled, "");
		output.setName(name);
		output.setDeclaredTypeRef(ScopedTypeFromRef(ref));
		output.setInterpretation(CJSIDLCommentToJSIDLInterp(ref.getComment()));

		return output;
	}
	


}
