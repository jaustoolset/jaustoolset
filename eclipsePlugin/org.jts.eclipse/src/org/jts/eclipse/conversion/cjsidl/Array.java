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

import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.ecore.EObject;
import org.jts.eclipse.cjsidl.arrayDef;
import org.jts.eclipse.cjsidl.bitfieldDef;
import org.jts.eclipse.cjsidl.declaredTypeSet;
import org.jts.eclipse.cjsidl.fixedFieldDef;
import org.jts.eclipse.cjsidl.fixedLenString;
import org.jts.eclipse.cjsidl.scopedType;
import org.jts.eclipse.cjsidl.scopedTypeId;
import org.jts.eclipse.cjsidl.typeReference;
import org.jts.eclipse.cjsidl.varField;
import org.jts.eclipse.cjsidl.varFormatField;
import org.jts.eclipse.cjsidl.varLenField;
import org.jts.eclipse.cjsidl.varLenString;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

public class Array extends BaseTypeReference{

	/**
	 * Converts a CJSIDL array to a JSIDL array
	 * @param arrayDef - CJSIDL array
	 * @return - resulting JSIDL array
	 * @throws ConversionException 
	 */
	public static org.jts.jsidl.binding.Array convert(arrayDef arrayDef) throws ConversionException {
		org.jts.jsidl.binding.Array newarray = new org.jts.jsidl.binding.Array();
		
		newarray.setName(arrayDef.getName());
		newarray.setInterpretation(CJSIDLCommentToJSIDLInterp(arrayDef.getComment()));

		// set the dimensions for 1D array
		java.util.List<org.jts.jsidl.binding.Dimension> dimList = newarray.getDimension();
		org.jts.jsidl.binding.Dimension dim = new org.jts.jsidl.binding.Dimension();
		dim.setSize(arrayDef.getArraySize());
		dim.setName(arrayDef.getName()+ "_Dim");
		
		dimList.add(dim);
		

		// if one was specified then set it to whatever it was. default is "required"
		if(arrayDef.getOptional() != null){
			newarray.setOptional(arrayDef.getOptional().equals(OPTIONAL));
		}
		
		// get the type reference information and convert
		if(arrayDef.getTypeRef()!= null){
			EObject type = arrayDef.getTypeRef();
			if(type instanceof bitfieldDef){
				newarray.setDeclaredBitField(DeclaredBitField.convertToRef((bitfieldDef)type));
			} else if(type instanceof fixedFieldDef){
				newarray.setDeclaredFixedField(DeclaredFixedField.convertToRef((fixedFieldDef)type));
			} else if(type instanceof fixedLenString){
				newarray.setDeclaredFixedLengthString(DeclaredFixedLengthString.convertToRef((fixedLenString)type));
			} else if(type instanceof varField){
				newarray.setDeclaredVariableField(DeclaredVariableField.convertToRef((varField)type));
			} else if(type instanceof varFormatField){
				newarray.setDeclaredVariableFormatField(DeclaredVariableFormatField.convertToRef((varFormatField)type));
			} else if(type instanceof varLenString){
				newarray.setDeclaredVariableLengthString(DeclaredVariableLengthString.convertToRef((varLenString)type));
			} else if(type instanceof varLenField){
				newarray.setDeclaredVariableLengthField(DeclaredVariableLengthField.convertToRef((varLenField)type));
			} else{
	            Logger.getLogger("CJSIDL").log(Level.SEVERE,
	            "Unexpected type \"" + type + "\" used for array.");
	            throw new ConversionException("Unexpected type \"" + type + "\" used for array.");
			}
		} else if(arrayDef.getScopedType() != null){
			scopedType sctype = arrayDef.getScopedType();
			EObject type = sctype.getType();
			if(type instanceof bitfieldDef){
				newarray.setDeclaredBitField(DeclaredBitField.convertToRef(arrayDef.getName(), sctype));
			} else if(type instanceof fixedFieldDef){
				newarray.setDeclaredFixedField(DeclaredFixedField.convertToRef(arrayDef.getName(), sctype));
			} else if(type instanceof fixedLenString){
				newarray.setDeclaredFixedLengthString(DeclaredFixedLengthString.convertToRef(arrayDef.getName(), sctype));
			} else if(type instanceof varField){
				newarray.setDeclaredVariableField(DeclaredVariableField.convertToRef(arrayDef.getName(), sctype));
			} else if(type instanceof varFormatField){
				newarray.setDeclaredVariableFormatField(DeclaredVariableFormatField.convertToRef(arrayDef.getName(), sctype));
			} else if(type instanceof varLenString){
				newarray.setDeclaredVariableLengthString(DeclaredVariableLengthString.convertToRef(arrayDef.getName(), sctype));
			} else if(type instanceof varLenField){
				newarray.setDeclaredVariableLengthField(DeclaredVariableLengthField.convertToRef(arrayDef.getName(), sctype));
			} else{
	            Logger.getLogger("CJSIDL").log(Level.SEVERE,
	            "Unexpected type \"" + type + "\" used for array.");
	            throw new ConversionException("Unexpected type \"" + type + "\" used for array.");
			}
		}
		return newarray;
	}

	/**
	 * converts a JSIDL array to a CJSIDL array
	 * @param tmpdef - JSIDL array
	 * @return - resulting CJSIDL array
	 */
	public static arrayDef convert(org.jts.jsidl.binding.Array tmpdef, EObject root) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();

		// this serviceDef doesn't have a DeclaredTypeSet created
		if(DeclaredTypeSet.additionalTypes == null){
			declaredTypeSet newtypeset = factory.createdeclaredTypeSet();
			newtypeset.setName(((org.jts.eclipse.cjsidl.serviceDef)root).getName() + ".typeset");
			newtypeset.setTypeName(((org.jts.eclipse.cjsidl.serviceDef)root).getServiceName() + "TypeSet");
			newtypeset.setVersion("1.0");
			
			((org.jts.eclipse.cjsidl.serviceDef)root).setTypeSet(newtypeset);
			DeclaredTypeSet.typeMap = new HashMap<String, EObject>();
			DeclaredTypeSet.additionalTypes = new HashSet<EObject>();
			DeclaredTypeSet.unresolvedRefs = new HashMap<EObject, String>();
		}
		org.jts.eclipse.cjsidl.arrayDef newdef = factory.createarrayDef();
		newdef.setArraySize(tmpdef.getDimension().get(0).getSize());
		newdef.setComment(JSIDLInterpToCJSIDLComment(tmpdef.getInterpretation()));
		newdef.setName(tmpdef.getName());
	
		if(tmpdef.isOptional()){
			newdef.setOptional(OPTIONAL);
		} 
		// create the type reference
		EObject ref = null;
		
		// what do we do if this is not a reference?  
		// we should create two definitions and include the first
		// definition in the "types" section, while adding a reference
		// to that type here.
		if(tmpdef.getBitField() != null){
			ref = BitField.convert(tmpdef.getBitField());
			DeclaredTypeSet.additionalTypes.add(ref);
			DeclaredTypeSet.typeMap.put(tmpdef.getBitField().getName(), ref);
		} else if(tmpdef.getFixedField() != null){
			ref = FixedField.convert(tmpdef.getFixedField());
			DeclaredTypeSet.additionalTypes.add(ref);
			DeclaredTypeSet.typeMap.put(tmpdef.getFixedField().getName(), ref);
		} else if(tmpdef.getFixedLengthString() != null){
			ref = FixedLengthString.convert(tmpdef.getFixedLengthString());
			DeclaredTypeSet.additionalTypes.add(ref);
			DeclaredTypeSet.typeMap.put(tmpdef.getFixedLengthString().getName(), ref);
		} else if(tmpdef.getVariableField() != null){
			ref = VariableField.convert(tmpdef.getVariableField());
			DeclaredTypeSet.additionalTypes.add(ref);
			DeclaredTypeSet.typeMap.put(tmpdef.getVariableField().getName(), ref);
		} else if(tmpdef.getVariableLengthField() != null){
			ref = VariableLengthField.convert(tmpdef.getVariableLengthField());
			DeclaredTypeSet.additionalTypes.add(ref);
			DeclaredTypeSet.typeMap.put(tmpdef.getVariableLengthField().getName(), ref);
		} else if(tmpdef.getVariableLengthString() != null){
			ref = VariableLengthString.convert(tmpdef.getVariableLengthString());
			DeclaredTypeSet.additionalTypes.add(ref);
			DeclaredTypeSet.typeMap.put(tmpdef.getVariableLengthString().getName(), ref);
		} else if(tmpdef.getDeclaredBitField() != null){
			ref = DeclaredBitField.convertToRef(tmpdef.getDeclaredBitField(), root);
		} else if(tmpdef.getDeclaredFixedField() != null){
			ref = DeclaredFixedField.convertToRef(tmpdef.getDeclaredFixedField(), root);
		} else if(tmpdef.getDeclaredFixedLengthString() != null){
			ref = DeclaredFixedLengthString.convertToRef(tmpdef.getDeclaredFixedLengthString(), root);
		} else if(tmpdef.getDeclaredVariableField() != null){
			ref = DeclaredVariableField.convertToRef(tmpdef.getDeclaredVariableField(), root);
		} else if(tmpdef.getDeclaredVariableLengthField() != null){
			ref = DeclaredVariableLengthField.convertToRef(tmpdef.getDeclaredVariableLengthField(), root);
		} else if(tmpdef.getDeclaredVariableLengthString() != null){
			ref = DeclaredVariableLengthString.convertToRef(tmpdef.getDeclaredVariableLengthString(), root);
		} 
		
		if(ref instanceof scopedTypeId){
			newdef.setScopedType(((scopedTypeId) ref).getRef());
		}else if(ref instanceof typeReference){
			newdef.setTypeRef(((typeReference) ref).getType());
		}else{
			newdef.setTypeRef(ref);
		}
		
		return newdef;
	}

}
