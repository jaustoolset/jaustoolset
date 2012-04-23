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
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.jts.eclipse.cjsidl.bodyDef;
import org.jts.eclipse.cjsidl.bodyScopedRef;
import org.jts.eclipse.cjsidl.constDef;
import org.jts.eclipse.cjsidl.containerDef;
import org.jts.eclipse.cjsidl.declaredConstSet;
import org.jts.eclipse.cjsidl.declaredConstSetRef;
import org.jts.eclipse.cjsidl.declaredEventDef;
import org.jts.eclipse.cjsidl.declaredTypeSet;
import org.jts.eclipse.cjsidl.declaredTypeSetRef;
import org.jts.eclipse.cjsidl.eventDef;
import org.jts.eclipse.cjsidl.footerDef;
import org.jts.eclipse.cjsidl.footerScopedRef;
import org.jts.eclipse.cjsidl.headerDef;
import org.jts.eclipse.cjsidl.headerScopedRef;
import org.jts.eclipse.cjsidl.internalEventSet;
import org.jts.eclipse.cjsidl.messageDef;
import org.jts.eclipse.cjsidl.messageRef;
import org.jts.eclipse.cjsidl.messageScopedRef;
import org.jts.eclipse.cjsidl.messageSet;
import org.jts.eclipse.cjsidl.messages;
import org.jts.eclipse.cjsidl.refAttr;
import org.jts.eclipse.cjsidl.scopedEventType;
import org.jts.eclipse.cjsidl.scopedType;
import org.jts.eclipse.cjsidl.serviceDef;
import org.jts.eclipse.cjsidl.transParam;
import org.jts.eclipse.cjsidl.transParams;
import org.jts.eclipse.cjsidl.transition;
import org.jts.eclipse.cjsidl.typeDef;
import org.jts.eclipse.cjsidl.typeReference;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

class ConversionObject{
	org.eclipse.emf.ecore.resource.Resource emfResource;
	Object jaxbBinding;
}

/**
 * @author cmessmer
 *	This class is used to help other objects resolve CJSIDL references.
 */
public class ConversionReferenceHelper extends HashMap<String, ConversionObject>{


	/**
	 * required serial version
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Maps a resource to an ID
	 * @param id - the JSIDL ID from a service definition, type set or constant set
	 * @param res - an EMF resource containing a service def, type set or const set
	 */
	public  void setResource(String id, org.eclipse.emf.ecore.resource.Resource res){
		ConversionObject obj = this.get(id);
		if(obj == null){
			obj = new ConversionObject();
		} 
		obj.emfResource = res;
		this.put(id, obj);
	}
	/**
	 * Maps a JAXB data binding to an ID
	 * @param id - the JSIDL ID from a service definition, type set or constant set
	 * @param binding - a JAXB data binding
	 */
	public  void setBinding(String id, Object binding){
		ConversionObject obj = this.get(id);
		if(obj == null){
			obj = new ConversionObject();
		} 
		obj.jaxbBinding = binding;
		this.put(id, obj);
	}
	/**
	 * Attaches an EObject(typically a "jaus" object)to an existing resource already mapped to an ID
	 * @param id - the ID that an existing resource is mapped to
	 * @param eobj - the top level container(jaus) for a CJSIDL binding
	 */
	public void setResourceEObject(String id, EObject eobj){
		Resource resource = this.get(id).emfResource;
		// by doing this we can avoid having to check to see if we should add
		// this object to the resource.
		resource.getContents().clear();
		if(eobj!=null)
		{
		    resource.getContents().add(eobj);
		}

		
	}
	/**
	 * retrieves the EObject from a resource that is mapped to a specified ID
	 * @param id - the ID to search for
	 * @return - the EObject extracted from the resource with ID=id
	 */
	public EObject getResourceEObject(String id){
		EObject result = null;
		result = this.get(id).emfResource.getContents().get(0);
		return result;
	}
	/**
	 * Retrieves the resource that is mapped to id
	 * @param id - the ID the resource is mapped to
	 * @return - the corresponding resource
	 */
	public  org.eclipse.emf.ecore.resource.Resource getResource(String id){
		ConversionObject obj = this.get(id);
		return obj.emfResource;
	}
	/**
	 * Retrieve the JAXB binding corresponding to the specified ID
	 * @param id - the id being searched for
	 * @return - the corresponding JAXB binding
	 * @throws ConversionException 
	 */
	public  Object getBinding(String id) throws ConversionException{
		ConversionObject obj = this.get(id);
		if(obj == null || obj.jaxbBinding==null){
			throw new ConversionException("Failed to find binding with ID=" + id + ".  Probable cause: invalid JSIDL.");
		}
		return obj.jaxbBinding;
	}
	
	/**
	 * Retrieve an EObject based off a scoped name
	 * @param name - the name of the object 
	 * @param currentObj - typically a service def, type set or const set
	 * @return - some type of EObject
	 */
	public EObject getEObjectByName(String name, EObject currentObj){
		String[] tmpstr = name.split("[.]");
		java.util.List<String> subnames = new ArrayList<String> ();
		for(int i=0; i< tmpstr.length; i++){
			subnames.add(tmpstr[i]);
		}
		return getEObjectByName(subnames, currentObj);
	}
	/**
	 * Called by the the other version of this function after the scope parts are separated in a list.
	 * @param name - a list of name parts
	 * @param currentObj - the object to search
	 * @return - the object that was found during the search.
	 */
	private EObject getEObjectByName(java.util.List<String> name, EObject currentObj){
		EObject result = null;
		if(name.size() ==1){
			result = findEObject(name.get(0), currentObj);
		} else {
			EObject tmpobj = getEObjectByRef(name.get(0), currentObj);
			name.remove(0);
			result = getEObjectByName(name, tmpobj);
		}
		
		return result;
	}

	/**
	 * Finds a specific object within a container by searching for the name within each subobject.
	 * @param string - the input name of the object to look for.
	 * @param currentObject - the container to search through.
	 * @return - the resulting object
	 */
	EObject findEObject(String string, EObject currentObject) {
		EObject result = null;
		if(currentObject instanceof org.jts.eclipse.cjsidl.declaredTypeSet){
			EList<org.jts.eclipse.cjsidl.typeDef> defs = ((org.jts.eclipse.cjsidl.declaredTypeSet)currentObject).getTypeDef();
			result = getEObjectFromDefs(string, defs);
			if(result == null){
				EList<org.jts.eclipse.cjsidl.typeReference> refs = ((org.jts.eclipse.cjsidl.declaredTypeSet)currentObject).getTypeRef();
				result = getEObjectFromReference(string, refs);
			}
		} else if(currentObject instanceof org.jts.eclipse.cjsidl.declaredConstSet){
			EList<org.jts.eclipse.cjsidl.constDef> defs = ((org.jts.eclipse.cjsidl.declaredConstSet)currentObject).getConstDef();
			result = getEObjectFromConsts(string, defs);
		} else if(currentObject instanceof org.jts.eclipse.cjsidl.serviceDef){
			internalEventSet events = ((org.jts.eclipse.cjsidl.serviceDef)currentObject).getInternalEventSet();
			if(events != null){
				EList<EObject> defs = events.getDefs();
				result = getEObjectFromEvents(string, defs);
			}
			declaredConstSet consts = ((org.jts.eclipse.cjsidl.serviceDef)currentObject).getConstSet();
			if(result == null && consts != null){
				EList<org.jts.eclipse.cjsidl.constDef> defs = consts.getConstDef();
				result = getEObjectFromConsts(string, defs);
			}
			declaredTypeSet types = ((org.jts.eclipse.cjsidl.serviceDef)currentObject).getTypeSet();
			if(result == null && types != null){
				EList<org.jts.eclipse.cjsidl.typeDef> defs = types.getTypeDef();
				result = getEObjectFromDefs(string, defs);
				if(result == null){
					EList<org.jts.eclipse.cjsidl.typeReference> refs = types.getTypeRef();
					result = getEObjectFromReference(string, refs);
				}				
			}
			
			messageSet messages = ((org.jts.eclipse.cjsidl.serviceDef)currentObject).getMessageSet();
			if(result == null && messages != null){
				
				result = getEObjectFromMessageSet((serviceDef) currentObject, string);

			}
			
		}
		return result;
	}
	/**
	 * Retrieve an object by name from a message set.
	 * @param messages - the message set to search through
	 * @param name - name of the message to search for
	 * @return - the resulting object
	 */
	public EObject getEObjectFromMessageSet(serviceDef svcDef, String name) {
		messageSet messages = svcDef.getMessageSet();
		EObject result = null;
		messages inputset = messages.getInputSet();
		if(inputset != null){
			EList<messageDef> defs = inputset.getMessageDefs();
			if(defs != null){
				result = getEObjectFromMessageDefs(defs, name);
			}
			EList<messageScopedRef> scRefs = inputset.getScopedRefs();
			if(result == null && scRefs != null){
				result = getEObjectFromMessageScopedRefs(scRefs, name);
			}
			EList<messageRef> refs = inputset.getTypeRefs();
			if(result == null && refs != null){
				result = getEObjectFromMessageRefs(refs, name);
			}
		}
		messages outputset = messages.getOutputSet();		
		if(result == null && outputset != null){
			EList<messageDef> defs = outputset.getMessageDefs();
			if(defs != null){
				result = getEObjectFromMessageDefs(defs, name);
			}
			EList<messageScopedRef> scRefs = outputset.getScopedRefs();
			if(result == null && scRefs != null){
				result = getEObjectFromMessageScopedRefs(scRefs, name);
			}
			EList<messageRef> refs = outputset.getTypeRefs();
			if(result == null && refs != null){
				result = getEObjectFromMessageRefs(refs, name);
			}
		}
		if(result == null && svcDef.getRefs() != null && svcDef.getRefs().getRefInherit() != null){
			result = getEObjectFromMessageSet(svcDef.getRefs().getRefInherit().getImportedNamespace(), name);
		}
		return result;
	}
	/**
	 * Retrieve a messageDef from a list of definitions based on the name.
	 * @param defs - list of definitions to search through
	 * @param name - name to search for.
	 * @return - resulting messageDef
	 */
	private EObject getEObjectFromMessageDefs(EList<messageDef> defs, String name){
		EObject result = null;
		for(messageDef def: defs){
			if(def.getName().equals(name)){
				result = def;
			}
		}
		
		return result;
	}
	
	/**
	 * Retrieve a specific object from a list of scoped messages
	 * @param refs - list of scoped message references
	 * @param name - name of the object to find.
	 * @return - resulting object
	 */
	private EObject getEObjectFromMessageScopedRefs(EList<messageScopedRef> refs, String name){
		EObject result = null;
		for(messageScopedRef ref: refs){
			if(ref.getName().equals(name)){
				result = ref;
			}
		}
		return result;
	}
	
	/**
	 * Retrieve a specific object from a list of message references
	 * @param refs - list of message references
	 * @param name - name of the object to find.
	 * @return - resulting object
	 */
	private EObject getEObjectFromMessageRefs(EList<messageRef> refs, String name){
		EObject result = null;
		for(messageRef ref: refs){
			if(ref.getName().equals(name)){
				result = ref;
			}
		}
		return result;
	}
	/**
	 * Retrieve a specific object from a list of generic definitions
	 * @param string - name of the object to find.
	 * @param defs - list of scoped message references
	 * @return - resulting object
	 */
	private EObject getEObjectFromEvents(String string, EList<EObject> defs) {
		EObject result = null;
		for(EObject obj: defs){
			if(obj instanceof declaredEventDef){
				if(((declaredEventDef)obj).getName().equals(string)){
					result = obj;
				}
			} else if(obj instanceof eventDef){
				if(((eventDef)obj).getName().equals(string)){
					result = obj;
				}
			}
		}
		return result;
	}
	
	/**
	 * Retrieve a parameter from a transition with specified name
	 * @param name - name of the parameter to find
	 * @param trans - the transition that should contain the parameter
	 * @return - resulting parameter 
	 */
	public EObject getEObjectFromTransition(String name, transition trans) {
		EObject result = null;
		
		transParams params = trans.getParams();
		EList<transParam> paramlist = params.getParams();
		for(transParam tmpparam: paramlist){
			if(tmpparam.getName() != null && tmpparam.getName().equals(name)){
				return tmpparam;
			}
		}
		
		return result;
	}
	
	/**
	 * Searches the current object(serviceDef, type set, const set) for a reference to a different file.
	 * @param string - the value to search for.
	 * @param currentObject - the object to search
	 * @return - the resulting object
	 */
	EObject findRefEObject(String string, EObject currentObject) {
		EObject result = null;
		if(currentObject instanceof org.jts.eclipse.cjsidl.declaredTypeSet){
			EList<declaredTypeSetRef> typerefs = ((org.jts.eclipse.cjsidl.declaredTypeSet)currentObject).getDeclaredTypeSetRef();
			for(declaredTypeSetRef ref: typerefs){
				if(ref.getName().equals(string)){
					result = ref;
				}
			}
			EList<declaredConstSetRef> constrefs = ((org.jts.eclipse.cjsidl.declaredTypeSet)currentObject).getDeclaredConstSetRef();
			for(declaredConstSetRef ref: constrefs){
				if(ref.getName().equals(string)){
					result = ref;
				}
			}
		} else if(currentObject instanceof org.jts.eclipse.cjsidl.declaredConstSet){
			EList<declaredConstSetRef> constrefs = ((org.jts.eclipse.cjsidl.declaredTypeSet)currentObject).getDeclaredConstSetRef();
			for(declaredConstSetRef ref: constrefs){
				if(ref.getName().equals(string)){
					result = ref;
				}
			}
		} else if(currentObject instanceof org.jts.eclipse.cjsidl.serviceDef){
			declaredTypeSet set = ((org.jts.eclipse.cjsidl.serviceDef)currentObject).getTypeSet();
			if(set != null){
				EList<declaredTypeSetRef> typerefs = ((org.jts.eclipse.cjsidl.declaredTypeSet)set).getDeclaredTypeSetRef();
				for(declaredTypeSetRef ref: typerefs){
					if(ref.getName().equals(string)){
						result = ref;
					}
				}
				EList<declaredConstSetRef> constrefs = ((org.jts.eclipse.cjsidl.declaredTypeSet)set).getDeclaredConstSetRef();
				for(declaredConstSetRef ref: constrefs){
					if(ref.getName().equals(string)){
						result = ref;
					}
				}
			}
			if(((org.jts.eclipse.cjsidl.serviceDef)currentObject).getRefs() != null){
				EList<refAttr> clientOfs = ((org.jts.eclipse.cjsidl.serviceDef)currentObject).getRefs().getRefClient();
				for(refAttr tmpref: clientOfs){
					if(tmpref.getName().equals(string)){
						result = tmpref;
					}
				}
				refAttr inherit = ((org.jts.eclipse.cjsidl.serviceDef)currentObject).getRefs().getRefInherit();
				if(inherit != null && inherit.getName().equals(string)){
					result = inherit;
				}
				if(result == null && inherit != null){
					result = findRefEObject(string, inherit.getImportedNamespace());
				}
			}
		}
		return result;
	}
	/**
	 * Retrieve a reference from a list of references based on name
	 * @param name - the name to search for
	 * @param refs - a list of references to search
	 * @return - the resulting object
	 */
	private EObject getEObjectFromReference(String name, EList<typeReference> refs) {
		EObject result = null;
		for(typeReference ref: refs){
			if(ref.getName().equals(name)){
				result = ref;
				break;
			}
		}
		return result;
	}

	/**
	 * Retrieve an type definition by name.
	 * @param name - the name of the type definition.
	 * @param defs - a list of definitions to search
	 * @return - the resulting object
	 */
	private EObject getEObjectFromDefs(String name, EList<typeDef> defs) {
		EObject result = null;
		for(typeDef tmpdef: defs){
			if(tmpdef.getFixedFieldDef() != null && tmpdef.getFixedFieldDef().getName().equals(name)){
				result = tmpdef.getFixedFieldDef();
			}
			if(tmpdef.getBitfieldDef() != null && tmpdef.getBitfieldDef().getName().equals(name)){
				result = tmpdef.getBitfieldDef();
			}
			if(tmpdef.getMessageDef() != null && tmpdef.getMessageDef().getName().equals(name)){
				result = tmpdef.getMessageDef();
			}
			if(tmpdef.getVarFormatField() != null && tmpdef.getVarFormatField().getName().equals(name)){
				result = tmpdef.getVarFormatField();
			}
			if(tmpdef.getVarField() != null && tmpdef.getVarField().getName().equals(name)){
				result = tmpdef.getVarField();
			}
			if(tmpdef.getArrayDef() != null && tmpdef.getArrayDef().getName().equals(name)){
				result = tmpdef.getArrayDef();
			}
			if(tmpdef.getFixedLenString() != null && tmpdef.getFixedLenString().getName().equals(name)){
				result = tmpdef.getFixedLenString();
			}
			if(tmpdef.getListDef() != null && tmpdef.getListDef().getName().equals(name)){
				result = tmpdef.getListDef();
			}
			if(tmpdef.getRecordDef() != null && tmpdef.getRecordDef().getName().equals(name)){
				result = tmpdef.getRecordDef();
			}
			if(tmpdef.getSequenceDef() != null && tmpdef.getSequenceDef().getName().equals(name)){
				result = tmpdef.getSequenceDef();
			}
			if(tmpdef.getVariantDef() != null && tmpdef.getVariantDef().getName().equals(name)){
				result = tmpdef.getVariantDef();
			}
			if(tmpdef.getVarLenField() != null && tmpdef.getVarLenField().getName().equals(name)){
				result = tmpdef.getVarLenField();
			}
			if(tmpdef.getVarLenString() != null && tmpdef.getVarLenString().getName().equals(name)){
				result = tmpdef.getVarLenString();
			}
			if(tmpdef.getHeaderDef() != null && tmpdef.getHeaderDef().getName().equals(name)){
				result = tmpdef.getHeaderDef();
			}
			if(tmpdef.getBodyDef() != null && tmpdef.getBodyDef().getName().equals(name)){
				result = tmpdef.getBodyDef();
			}
			if(tmpdef.getFooterDef() != null && tmpdef.getFooterDef().getName().equals(name)){
				result = tmpdef.getFooterDef();
			}
		}
		return result;
	}

	/**
	 * Retrieve a constant definition from a list by name
	 * @param name - name of the definition to find
	 * @param defs - list of definitions to search
	 * @return - the resulting constant def
	 */
	private EObject getEObjectFromConsts(String name, EList<constDef> defs) {
		EObject result = null;
		for(constDef def : defs){
			if(def.getName().equals(name)){
				result = def;
				break;
			}
		}
		return result;
	}

	/**
	 * Retrieve an object by name from a referenced file
	 * @param name - name of the reference object
	 * @param currentObject - the object to search
	 * @return - the resulting object
	 */
	EObject getEObjectByRef(String name, EObject currentObject) {
		EObject result = null;
		if(currentObject instanceof org.jts.eclipse.cjsidl.declaredTypeSet){
			EList<org.jts.eclipse.cjsidl.declaredConstSetRef> constrefs = 
				((org.jts.eclipse.cjsidl.declaredTypeSet)currentObject).getDeclaredConstSetRef();
			for(org.jts.eclipse.cjsidl.declaredConstSetRef ref: constrefs){
				if(ref.getName().equals(name)){
					result = ref.getImportedNamespace();
				}
			}
			EList<org.jts.eclipse.cjsidl.declaredTypeSetRef> typerefs = 
				((org.jts.eclipse.cjsidl.declaredTypeSet)currentObject).getDeclaredTypeSetRef();
			for(org.jts.eclipse.cjsidl.declaredTypeSetRef ref: typerefs){
				if(ref.getName().equals(name)){
					result = ref.getImportedNamespace();
				}
			}
		} else if(currentObject instanceof org.jts.eclipse.cjsidl.declaredConstSet){
			EList<org.jts.eclipse.cjsidl.declaredConstSetRef> constrefs = 
				((org.jts.eclipse.cjsidl.declaredConstSet)currentObject).getDeclaredConstSetRef();
			for(org.jts.eclipse.cjsidl.declaredConstSetRef ref: constrefs){
				if(ref.getName().equals(name)){
					result = ref.getImportedNamespace();
				}
			}
		} else if(currentObject instanceof org.jts.eclipse.cjsidl.serviceDef){
			org.jts.eclipse.cjsidl.declaredConstSet constset = ((org.jts.eclipse.cjsidl.serviceDef)currentObject).getConstSet();
			
			if(constset != null){
				EList<org.jts.eclipse.cjsidl.declaredConstSetRef> constrefs = 
					constset.getDeclaredConstSetRef();
				for(org.jts.eclipse.cjsidl.declaredConstSetRef ref: constrefs){
					if(ref.getName().equals(name)){
						result = ref.getImportedNamespace();
					}
				}
			}
			org.jts.eclipse.cjsidl.declaredTypeSet typeset = ((org.jts.eclipse.cjsidl.serviceDef)currentObject).getTypeSet();
			if(typeset != null){
				EList<org.jts.eclipse.cjsidl.declaredTypeSetRef> typerefs = 
					typeset.getDeclaredTypeSetRef();
				if(typerefs != null){
					for(org.jts.eclipse.cjsidl.declaredTypeSetRef ref: typerefs){
						if(ref.getName().equals(name)){
							result = ref.getImportedNamespace();
						}
					}
				}
				EList<org.jts.eclipse.cjsidl.declaredConstSetRef> constrefs = 
					typeset.getDeclaredConstSetRef();
				if(constrefs != null){
					for(org.jts.eclipse.cjsidl.declaredConstSetRef ref: constrefs){
						if(ref.getName().equals(name)){
							result = ref.getImportedNamespace();
						}
					}
				}
			}
			// check references
			if(result == null && ((org.jts.eclipse.cjsidl.serviceDef)currentObject).getRefs() != null){
				EList<refAttr> clientOfs=((org.jts.eclipse.cjsidl.serviceDef)currentObject).getRefs().getRefClient();
				if(result == null && clientOfs != null){
					for(refAttr ref: clientOfs){
						if(ref.getName().equals(name)){
							result = ref.getImportedNamespace();
						}
					}
				}
				refAttr inherits = ((org.jts.eclipse.cjsidl.serviceDef)currentObject).getRefs().getRefInherit();
				if(inherits != null && inherits.getName().equals(name)){
					result = inherits.getImportedNamespace();
				}
				if(result == null && inherits != null){
					result = getEObjectByRef(name, inherits.getImportedNamespace());
				}
			}
		}


		return result;
	}

	/**
	 * Retrieve a scopedType from a typeName
	 * @param typename - the scoped type that a reference will be created from
	 * @param currentObj - the root object (type set, const set, service def) 
	 * @return - a scopedType which is a series of references used as a path to the final referenced object.
	 */
	public scopedType getScopedEObjectByName(String typename, EObject currentObj) {
		String[] tmpstr = typename.split("[.]");
		java.util.List<String> subnames = new ArrayList<String> ();
		for(int i=0; i< tmpstr.length; i++){
			subnames.add(tmpstr[i]);
		}
		return (scopedType) getScopedEObjectByName(subnames, currentObj);
	}
	
	/**
	 * Does all the work for the public method.
	 * @param name - a List of references contained within the object name
	 * @param currentObj - the root object
	 * @return - a scopedType with all the references set for the scope
	 */
	private EObject getScopedEObjectByName(java.util.List<String> name, EObject currentObj){
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.scopedType ref = factory.createscopedType();
		String typeName = "";
		EList<EObject> scopeList = ref.getNames();
		EObject referenceObject = null;
		// set references for scoped name parts
		for(String subname: name){
			// the last subname is the name of the type, so its not a root object reference
			if(name.indexOf(subname) == name.size()-1){
				typeName = subname;
				continue;
			}
			// this is the first name and is only separate from the rest of the names
			// to avoid making a fix to the grammar and scope provider
			else if(name.indexOf(subname) == 0){
				referenceObject = getEObjectByRef(subname, currentObj);
				EObject refObj = findRefEObject(subname, currentObj);
				ref.setName(refObj);
			} else {
				referenceObject = getEObjectByRef(subname, referenceObject);
				EObject refObj = findRefEObject(subname, currentObj);
				scopeList.add(refObj);
			}
			
		}
		ref.setType(findEObject(typeName, referenceObject));
		
		return ref;
	}

	/**
	 * Retrieve a scoped header reference based on name
	 * @param name - a list of name parts 
	 * @param currentObj - the current object to search
	 * @return - a resulting object
	 */
	public headerScopedRef getScopedHeaderEObjectByName(java.util.List<String> name, EObject currentObj) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.headerScopedRef ref = factory.createheaderScopedRef();
		String typeName = "";
		EList<EObject> scopeList = ref.getNames();
		EObject referenceObject = null;
		// set references for scoped name parts
		for(String subname: name){
			// the last subname is the name of the type, so its not a root object reference
			if(name.indexOf(subname) == name.size()-1){
				typeName = subname;
				continue;
			}
			// this is the first name and is only separate from the rest of the names
			// to avoid making a fix to the grammar and scope provider
			else if(name.indexOf(subname) == 0){
				referenceObject = getEObjectByRef(subname, currentObj);
				EObject refObj = findRefEObject(subname, currentObj);
				ref.setName(refObj);
			} else {
				currentObj = referenceObject;
				referenceObject = getEObjectByRef(subname, referenceObject);
				EObject refObj = findRefEObject(subname, currentObj);
				scopeList.add(refObj);
			}
			
		}
		ref.setType((headerDef) findEObject(typeName, referenceObject));
		
		return ref;
	}

	
	/**
	 * Retrieve a scoped footer reference based on name
	 * @param name - a list of name parts 
	 * @param currentObj - the current object to search
	 * @return - a resulting object
	 */
	public footerScopedRef getScopedFooterEObjectByName(
			List<String> name, EObject currentObj) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.footerScopedRef ref = factory.createfooterScopedRef();
		String typeName = "";
		EList<EObject> scopeList = ref.getNames();
		EObject referenceObject = null;
		// set references for scoped name parts
		for(String subname: name){
			// the last subname is the name of the type, so its not a root object reference
			if(name.indexOf(subname) == name.size()-1){
				typeName = subname;
				continue;
			}
			// this is the first name and is only separate from the rest of the names
			// to avoid making a fix to the grammar and scope provider
			else if(name.indexOf(subname) == 0){
				referenceObject = getEObjectByRef(subname, currentObj);
				EObject refObj = findRefEObject(subname, currentObj);
				ref.setName(refObj);
			} else {
				currentObj = referenceObject;
				referenceObject = getEObjectByRef(subname, referenceObject);
				EObject refObj = findRefEObject(subname, currentObj);
				scopeList.add(refObj);
			}
			
		}
		ref.setType((footerDef) findEObject(typeName, referenceObject));
		
		return ref;
	}

	/**
	 * Retrieve a scoped body reference based on name
	 * @param name - a list of name parts 
	 * @param currentObj - the current object to search
	 * @return - a resulting object
	 */
	public bodyScopedRef getScopedBodyEObjectByName(List<String> name,
			EObject currentObj) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.bodyScopedRef ref = factory.createbodyScopedRef();
		String typeName = "";
		EList<EObject> scopeList = ref.getNames();
		EObject referenceObject = null;
		// set references for scoped name parts
		for(String subname: name){
			// the last subname is the name of the type, so its not a root object reference
			if(name.indexOf(subname) == name.size()-1){
				typeName = subname;
				continue;
			}
			// this is the first name and is only separate from the rest of the names
			// to avoid making a fix to the grammar and scope provider
			else if(name.indexOf(subname) == 0){
				referenceObject = getEObjectByRef(subname, currentObj);
				EObject refObj = findRefEObject(subname, currentObj);
				ref.setName(refObj);
			} else {
				currentObj = referenceObject;
				referenceObject = getEObjectByRef(subname, referenceObject);
				EObject refObj = findRefEObject(subname, currentObj);
				scopeList.add(refObj);
			}
			
		}
		ref.setType((bodyDef) findEObject(typeName, referenceObject));
		
		return ref;
	}

	/**
	 * Retrieve a scoped container reference based on name
	 * @param name - a list of name parts 
	 * @param currentObj - the current object to search
	 * @return - a resulting object
	 */
	public scopedType getScopedContainerEObjectByName(java.util.List<String> name,
			EObject currentObj) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.scopedType ref = factory.createscopedType();
		String typeName = "";
		EList<EObject> scopeList = ref.getNames();
		EObject referenceObject = null;
		// set references for scoped name parts
		for(String subname: name){
			// the last subname is the name of the type, so its not a root object reference
			if(name.indexOf(subname) == name.size()-1){
				typeName = subname;
				continue;
			}
			// this is the first name and is only separate from the rest of the names
			// to avoid making a fix to the grammar and scope provider
			else if(name.indexOf(subname) == 0){
				referenceObject = getEObjectByRef(subname, currentObj);
				EObject refObj = findRefEObject(subname, currentObj);
				ref.setName(refObj);
			} else {
				referenceObject = getEObjectByRef(subname, referenceObject);
				EObject refObj = findRefEObject(subname, currentObj);
				scopeList.add(refObj);
			}
			
		}
		ref.setType((containerDef) findEObject(typeName, referenceObject));
		
		return ref;
	}

	/**
	 * Retrieve a scoped event definition based on name
	 * @param name - a list of name parts 
	 * @param currentObj - the current object to search
	 * @return - a resulting object
	 */
	public EObject getEventEObjectByName(String type, serviceDef currentObj) {
		EObject result = null;
		if(currentObj.getInternalEventSet() != null){
			EList<EObject> defs = currentObj.getInternalEventSet().getDefs();
			for(EObject def: defs){
				if(def instanceof eventDef){
					if(((eventDef)def).getName().equals(type)){
						return def;
					}
				} else if(def instanceof declaredEventDef){
					if(((declaredEventDef)def).getName().equals(type)){
						return def;
					}
				}
			}
		}
		if(currentObj.getRefs() != null && currentObj.getRefs().getRefInherit() != null){
			refAttr inherit = currentObj.getRefs().getRefInherit();
			result = getEventEObjectByName(type, inherit.getImportedNamespace());
		}
		return result;
	}

	/**
	 * Used in transitions which allow parameters to be a subtype of an event or message
	 * @param type - the scoped subtype to search for
	 * @param currentObj - the current object to search
	 * @return - the resulting object
	 */
	public EObject getSubtypeByName(String type, serviceDef currentObj) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		String[] tmpstr = type.split("[.]");
		scopedEventType sctype = factory.createscopedEventType();

		// get the first object and set the name to it, It must be an Event or Message (Ref/Def)
		EObject result =Conversion.referenceHelper.getEventEObjectByName(tmpstr[0], currentObj); 
		if(result == null){
			result = Conversion.referenceHelper.getEObjectFromMessageSet(currentObj, tmpstr[0]);
		}			
		sctype.setName(result);
		
		EList<EObject> names = sctype.getNames();
		for(int i=1; i< (tmpstr.length-1); i++){
			result = getSubEObjectByName(tmpstr[i], result);
			names.add(result);
		}
		// set type using the last subname
		String lastSubname = tmpstr[tmpstr.length-1];
		sctype.setType(getSubEObjectByName(lastSubname, result));
		
		return sctype;
	}

	/**
	 * Retrieve a sub-object based on name.
	 * @param string - the name to search for.
	 * @param obj - the object to search in.
	 * @return - the resulting object
	 */
	private EObject getSubEObjectByName(String string, EObject obj) {
		EObject result = null;

		ConversionSubEObjectReferenceHelper helper = new ConversionSubEObjectReferenceHelper();
		result = helper.getSubEObjectByName(string, obj);
		
		return result;
	}


	

}
