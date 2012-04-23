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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.jts.eclipse.cjsidl.arrayDef;
import org.jts.eclipse.cjsidl.bitfieldDef;
import org.jts.eclipse.cjsidl.bodyDef;
import org.jts.eclipse.cjsidl.bodyRef;
import org.jts.eclipse.cjsidl.containerDef;
import org.jts.eclipse.cjsidl.containerRef;
import org.jts.eclipse.cjsidl.eventDef;
import org.jts.eclipse.cjsidl.fixedFieldDef;
import org.jts.eclipse.cjsidl.fixedLenString;
import org.jts.eclipse.cjsidl.footerDef;
import org.jts.eclipse.cjsidl.footerRef;
import org.jts.eclipse.cjsidl.headerDef;
import org.jts.eclipse.cjsidl.headerRef;
import org.jts.eclipse.cjsidl.listDef;
import org.jts.eclipse.cjsidl.messageDef;
import org.jts.eclipse.cjsidl.recordDef;
import org.jts.eclipse.cjsidl.scopedEventType;
import org.jts.eclipse.cjsidl.scopedType;
import org.jts.eclipse.cjsidl.scopedTypeId;
import org.jts.eclipse.cjsidl.sequenceDef;
import org.jts.eclipse.cjsidl.taggedItemDef;
import org.jts.eclipse.cjsidl.typeReference;
import org.jts.eclipse.cjsidl.varField;
import org.jts.eclipse.cjsidl.varFormatField;
import org.jts.eclipse.cjsidl.varLenField;
import org.jts.eclipse.cjsidl.varLenString;
import org.jts.eclipse.cjsidl.variantDef;

/**
 * @author cmessmer
 * This class helps to create references for transition parameter types that can
 * include scoped subtypes.  for instance it is common within the core services to 
 * have a transition parameter of type "Receive.Body.ReceiveRec", which is the record 
 * contained within the body of the Receive message.
 */
public class ConversionSubEObjectReferenceHelper extends BaseTypeReference{
	
	protected static String getNameFromSubEObject(EObject input){
		String name = null;
		
		if(input instanceof scopedEventType){
			name =  getNameFromEventSubEObject((scopedEventType)input);
		} else {
            Logger.getLogger("CJSIDL").log(Level.SEVERE,
            "getNameFromSubEObject: Unexpected object type\"" + input + "\" while converting parameter.");
		}
		
		return name;
	}
	
	/**
	 * Extracts a scoped name from a scoped event reference
	 * @param input - the scopedEventType object that holds the scoped name data.
	 * @return - a scoped name for an event
	 */
	private static String getNameFromEventSubEObject(scopedEventType input) {
		String name = null;
		
		name = getNameFromEObject(input.getName());
		for(EObject tmpobj: input.getNames()){
			name = name + "." + getNameFromEObject(tmpobj); 
		}
		
		name = name + "." + getNameFromEObject(input.getType());
		if((!(input.getName() instanceof eventDef) && !(input.getName() instanceof messageDef)) &&
				(!(input.getType() instanceof eventDef) && !(input.getType() instanceof messageDef))){
            Logger.getLogger("CJSIDL").log(Level.SEVERE,
                    "getNameFromEventSubEObject: Unexpected object type\"" + name + "\" while converting parameter.\n" +
                    		"Ecpecting event, message or a subtype of the message or event.");
            
		}
		
		return name;
	}

	/**
	 * Retrieve an object based on the name.
	 * @param string - scoped name of the sub object
	 * @param obj - the top level object to search
	 * @return - the resulting object
	 */
	public EObject getSubEObjectByName(String string, EObject obj) {
		EObject result = null;
		// check the message parts.  since there is only one subelement, return it.
		if(obj instanceof headerDef){
			return ((headerDef)obj).getRecordListSequenceVariant();
		} else if(obj instanceof bodyDef){
			return ((bodyDef)obj).getRecordListSequenceVariant();
		} else if(obj instanceof footerDef){
			return ((footerDef)obj).getRecordListSequenceVariant();
		} else if(obj instanceof headerRef){
			return getSubEObjectByName(string, ((headerRef)obj).getTypeRef());
		} else if(obj instanceof bodyRef){
			return getSubEObjectByName(string, ((bodyRef)obj).getTypeRef());
		} else if(obj instanceof footerRef){
			return getSubEObjectByName(string, ((footerRef)obj).getTypeRef());
		} 
		// check containers
		else if(obj instanceof listDef){
			if(((listDef)obj).getContainerDef() != null){
				return ((listDef)obj).getContainerDef();
			} else if(((listDef)obj).getContainerRef() != null){
				return getSubEObjectByName(string, ((listDef)obj).getContainerRef().getType());
			}
		} else if(obj instanceof recordDef){
			return getSubEObjectFromRecord(string, ((recordDef)obj));
			
		}else if(obj instanceof sequenceDef){
			return getSubEObjectFromSequence(string, ((sequenceDef)obj));
		}else if (obj instanceof variantDef){
			return getSubEObjectFromVariant(string, ((variantDef)obj));
		} else if(obj instanceof containerRef){
			return getSubEObjectByName(string, ((containerRef)obj).getType());
		} else if(obj instanceof eventDef){
			return getSubEObjectFromEvent(string, ((eventDef)obj));
		}
		
		// check reference
		return result;
	}
	/**
	 * Retrieves a sub object from an event definition
	 * @param string - scoped name of the sub object
	 * @param input - event to search
	 * @return - the resulting object
	 */
	private EObject getSubEObjectFromEvent(String string, eventDef input) {
		EObject result = null;
		
		if(input.getBody() instanceof bodyDef){
			if(((bodyDef)input.getBody()).getName().equals(string)){
				return input.getBody();
			}
		}
		if(input.getBody() instanceof bodyRef){
			if(((bodyRef)input.getBody()).getName().equals(string)){
				return input.getBody();
			}
		} 
		if(input.getHeader() instanceof headerDef){
			if(((headerDef)input.getHeader()).getName().equals(string)){
				return input.getHeader();
			}
		} 
		if(input.getHeader() instanceof headerRef){
			if(((headerRef)input.getHeader()).getName().equals(string)){
				return input.getHeader();
			}
		} 
		if(input.getFooter() instanceof footerDef){
			if(((footerDef)input.getFooter()).getName().equals(string)){
				return input.getFooter();
			}
		}
		if(input.getFooter() instanceof footerRef){
			if(((footerRef)input.getFooter()).getName().equals(string)){
				return input.getFooter();
			}
		}
			
		
		return result;
	}
	/**
	 * Retrieves a sub object from a variant definition
	 * @param name - scoped name of the sub object
	 * @param variantDef - event to search
	 * @return - the resulting object
	 */
	private EObject getSubEObjectFromVariant(String name,
			variantDef variantDef) {
		EList<taggedItemDef> defs = variantDef.getItemList();
		for(taggedItemDef def: defs){
			if(def.getContainerDef() instanceof containerDef){
				if(def.getContainerDef().getName().equals(name)){
					return def.getContainerDef();
				}
				
			} else if(def.getContainerRef()!= null && def.getContainerRef().getName().equals(name)){
				if(def.getContainerRef().getType() != null){
					return def.getContainerRef().getType();
				} else if(def.getContainerRef().getTypeScoped() != null){
					return def.getContainerRef().getTypeScoped().getType();
				}
			}
		}
		return null;
	}
	/**
	 * Retrieves a sub object from a sequence definition
	 * @param name - scoped name of the sub object
	 * @param sequenceDef - event to search
	 * @return - the resulting object
	 */
	private EObject getSubEObjectFromSequence(String name,
			sequenceDef sequenceDef) {
		EList<EObject> objectList = sequenceDef.getContainerTypeList();
		
		for(EObject tmpobj: objectList){
			if(tmpobj instanceof containerDef){
				return getSubEObjectByName(name, ((containerDef)tmpobj));
			} else if(tmpobj instanceof containerRef){
				return getSubEObjectByName(name, ((containerRef)tmpobj).getType());
			} else if(tmpobj instanceof scopedType){
				return getSubEObjectByName(name, ((scopedType)tmpobj).getType());
			}
		}
		return null;
	}
	/**
	 * Retrieves a sub object from a record definition
	 * @param name - scoped name of the sub object
	 * @param recordDef - event to search
	 * @return - the resulting object
	 */
	private EObject getSubEObjectFromRecord(String name, recordDef def) {
		EObject result = null;
		EList<arrayDef> arrays = def.getArrayDef();
		for(arrayDef tmpdef: arrays){
			if(tmpdef.getName().equals(name)){
				return tmpdef;
			}				
		}
		
		EList<bitfieldDef> bitfields = def.getBitfieldDef();
		for(bitfieldDef tmpdef: bitfields){
			if(tmpdef.getName().equals(name)){
				return tmpdef;
			}				
		}
		EList<fixedFieldDef> fields = def.getFieldDef();
		for(fixedFieldDef tmpdef: fields){
			if(tmpdef.getName().equals(name)){
				return tmpdef;
			}				
		}
		EList<fixedLenString> fstrs = def.getFixedLengthStringDef();
		for(fixedLenString tmpdef: fstrs){
			if(tmpdef.getName().equals(name)){
				return tmpdef;
			}				
		}
		EList<scopedTypeId> screfs = def.getScopedRef();
		for(scopedTypeId tmpdef: screfs){
			if(tmpdef.getScopedName().equals(name)){
				return tmpdef;
			}				
		}
		EList<typeReference> refs = def.getTypeReference();
		for(typeReference tmpdef: refs){
			if(tmpdef.getName().equals(name)){
				return tmpdef;
			}				
		}
		EList<varFormatField> vffields = def.getVarFormatField();
		for(varFormatField tmpdef: vffields){
			if(tmpdef.getName().equals(name)){
				return tmpdef;
			}				
		}
		EList<varField> varfields = def.getVariableFieldDef();
		for(varField tmpdef: varfields){
			if(tmpdef.getName().equals(name)){
				return tmpdef;
			}				
		}
		EList<varLenField> vfields = def.getVariableLengthFieldDef();
		for(varLenField tmpdef: vfields){
			if(tmpdef.getName().equals(name)){
				return tmpdef;
			}				
		}
		EList<varLenString> vstrings = def.getVariableLengthStringDef();
		for(varLenString tmpdef: vstrings){
			if(tmpdef.getName().equals(name)){
				return tmpdef;
			}				
		}
		return result;
	}

}
