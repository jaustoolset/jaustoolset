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

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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
import org.jts.eclipse.cjsidl.scopedTypeId;
import org.jts.eclipse.cjsidl.sequenceDef;
import org.jts.eclipse.cjsidl.typeDef;
import org.jts.eclipse.cjsidl.typeReference;
import org.jts.eclipse.cjsidl.varField;
import org.jts.eclipse.cjsidl.varFormatField;
import org.jts.eclipse.cjsidl.varLenField;
import org.jts.eclipse.cjsidl.varLenString;
import org.jts.eclipse.cjsidl.variantDef;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class DeclaredTypeSet {
	// mapping of type name to the actual type object
	// this is used to resolve references as data is processed
	public static Map<String, EObject> typeMap;
	// JSIDL can declare types within an array def, but CJSIDL can't, so
	// these objects are created and then added to the model after the other
	// processing is done
	public static Set<EObject> additionalTypes;
	// this is a map of unresolved references to the with the name of the referenced
	// object that doesn't exist yet.  These are also processed after we are done.
	public static Map<EObject, String> unresolvedRefs;

	/**
	 * Converts a typeset to a JSIDL declared type set
	 * @param input - the input CJSIDL type set
	 * @return - the resulting JSIDL declared type set
	 * @throws ConversionException 
	 */
	public static org.jts.jsidl.binding.DeclaredTypeSet convert(org.jts.eclipse.cjsidl.declaredTypeSet input) throws ConversionException
	{
		org.jts.jsidl.binding.DeclaredTypeSet output = new org.jts.jsidl.binding.DeclaredTypeSet();
		
		// handle typeSetRef
		EList<org.jts.eclipse.cjsidl.declaredTypeSetRef> typerefs =  input.getDeclaredTypeSetRef();
		java.util.List<org.jts.jsidl.binding.DeclaredTypeSetRef> typesetref = output.getDeclaredTypeSetRef();
		for(org.jts.eclipse.cjsidl.declaredTypeSetRef tmpref: typerefs)
		{
			typesetref.add(DeclaredTypeSetRef.convert(tmpref));
		}
		// handle constSetRef
		EList<org.jts.eclipse.cjsidl.declaredConstSetRef> constrefs =  input.getDeclaredConstSetRef();
		java.util.List<org.jts.jsidl.binding.DeclaredConstSetRef> constsetref = output.getDeclaredConstSetRef();
		for(org.jts.eclipse.cjsidl.declaredConstSetRef tmpref: constrefs)
		{
			constsetref.add(DeclaredConstSetRef.convert(tmpref));
		}
		
		EList<org.jts.eclipse.cjsidl.typeDef>typedefs = input.getTypeDef();
		java.util.List<Object> outputTypes = output.getMessageDefOrHeaderOrBody();
		for(org.jts.eclipse.cjsidl.typeDef tmpdef: typedefs){
			if(tmpdef.getFixedFieldDef() != null){
				org.jts.jsidl.binding.FixedField field = FixedField.convert(tmpdef.getFixedFieldDef());
				outputTypes.add(((Object)field));
			}
			if(tmpdef.getBitfieldDef() != null){
				org.jts.jsidl.binding.BitField bitfield = BitField.convert(tmpdef.getBitfieldDef());
				outputTypes.add(((Object)bitfield));
			}
			if(tmpdef.getMessageDef() != null){
				org.jts.jsidl.binding.MessageDef def = MessageDef.convert(tmpdef.getMessageDef());
				outputTypes.add(((Object)def));
			}
			if(tmpdef.getVarFormatField() != null){
				org.jts.jsidl.binding.VariableFormatField field = VariableFormatField.convert(tmpdef.getVarFormatField());
				outputTypes.add(((Object)field));
			}
			if(tmpdef.getVarField() != null){
				org.jts.jsidl.binding.VariableField field = VariableField.convert(tmpdef.getVarField());
				outputTypes.add(((Object)field));
			}
			if(tmpdef.getArrayDef() != null){
				org.jts.jsidl.binding.Array array = Array.convert(tmpdef.getArrayDef());
				outputTypes.add(((Object)array));
			}
			if(tmpdef.getFixedLenString() != null){
				org.jts.jsidl.binding.FixedLengthString str = FixedLengthString.convert(tmpdef.getFixedLenString());
				outputTypes.add(((Object)str));
			}
			if(tmpdef.getListDef() != null){
				org.jts.jsidl.binding.List list = List.convert(tmpdef.getListDef());
				outputTypes.add(((Object)list));
			}
			if(tmpdef.getRecordDef() != null){
				org.jts.jsidl.binding.Record rec = Record.convert(tmpdef.getRecordDef());
				outputTypes.add(((Object)rec));
			}
			if(tmpdef.getSequenceDef() != null){
				org.jts.jsidl.binding.Sequence seq = Sequence.convert(tmpdef.getSequenceDef());
				outputTypes.add(((Object)seq));
			}
			if(tmpdef.getVariantDef() != null){
				org.jts.jsidl.binding.Variant var = Variant.convert(tmpdef.getVariantDef());
				outputTypes.add(((Object)var));
			}
			if(tmpdef.getVarLenField() != null)
			{
				org.jts.jsidl.binding.VariableLengthField str = VariableLengthField.convert(tmpdef.getVarLenField());
				outputTypes.add(((Object)str));
			}
			if(tmpdef.getVarLenString() != null)
			{
				org.jts.jsidl.binding.VariableLengthString str = VariableLengthString.convert(tmpdef.getVarLenString());
				outputTypes.add(((Object)str));
			}
			if(tmpdef.getHeaderDef() != null)
			{
				org.jts.jsidl.binding.Header hdr = Header.convert(tmpdef.getHeaderDef());
				outputTypes.add(((Object)hdr));
			}
			if(tmpdef.getBodyDef() != null)
			{
				org.jts.jsidl.binding.Body body = Body.convert(tmpdef.getBodyDef());
				outputTypes.add(((Object)body));
			}
			if(tmpdef.getFooterDef() != null)
			{
				org.jts.jsidl.binding.Footer ftr = Footer.convert(tmpdef.getFooterDef());
				outputTypes.add(((Object)ftr));
			}
		}
		
		EList<org.jts.eclipse.cjsidl.typeReference>inputtyperefs = input.getTypeRef();
		for(typeReference ref: inputtyperefs){
			if(ref.getType() instanceof messageDef){
				outputTypes.add(DeclaredMessageDef.convert(ref));
			}else if(ref.getType() instanceof eventDef){
				outputTypes.add(DeclaredEventDef.convert(ref));
			}else if(ref.getType() instanceof headerDef){
				outputTypes.add(DeclaredHeader.convert(ref));
			}else if(ref.getType() instanceof bodyDef){
				outputTypes.add(DeclaredBody.convert(ref));
			}else if(ref.getType() instanceof footerDef){
				outputTypes.add(DeclaredFooter.convert(ref));
			}else if(ref.getType() instanceof listDef){
				outputTypes.add(DeclaredList.convert(ref));
			}else if(ref.getType() instanceof sequenceDef){
				outputTypes.add(DeclaredSequence.convert(ref));
			}else if(ref.getType() instanceof variantDef){
				outputTypes.add(DeclaredVariant.convert(ref));
			}else if(ref.getType() instanceof recordDef){
				outputTypes.add(DeclaredRecord.convert(ref));
			}else if(ref.getType() instanceof arrayDef){
				outputTypes.add(DeclaredArray.convert(ref));
			}else if(ref.getType() instanceof fixedFieldDef){
				outputTypes.add(DeclaredFixedField.convert(ref));
			}else if(ref.getType() instanceof bitfieldDef){
				outputTypes.add(DeclaredBitField.convert(ref));
			}else if(ref.getType() instanceof fixedLenString){
				outputTypes.add(DeclaredFixedLengthString.convert(ref));
			}else if(ref.getType() instanceof varLenString){
				outputTypes.add(DeclaredVariableLengthString.convert(ref));
			}else if(ref.getType() instanceof varField){
				outputTypes.add(DeclaredVariableField.convert(ref));
			}else if(ref.getType() instanceof varLenField){
				outputTypes.add(DeclaredVariableLengthField.convert(ref));
			}else if(ref.getType() instanceof varFormatField){
				outputTypes.add(DeclaredVariableFormatField.convert(ref));
			}
		}
		EList<org.jts.eclipse.cjsidl.scopedTypeId>inputscopedrefs = input.getScopedRef();
		for(scopedTypeId ref: inputscopedrefs){
			if(ref.getRef().getType() instanceof messageDef){
				outputTypes.add(DeclaredMessageDef.convert(ref));
			}else if(ref.getRef().getType() instanceof eventDef){
				outputTypes.add(DeclaredEventDef.convert(ref));
			}else if(ref.getRef().getType() instanceof headerDef){
				outputTypes.add(DeclaredHeader.convert(ref));
			}else if(ref.getRef().getType() instanceof bodyDef){
				outputTypes.add(DeclaredBody.convert(ref));
			}else if(ref.getRef().getType() instanceof footerDef){
				outputTypes.add(DeclaredFooter.convert(ref));
			}else if(ref.getRef().getType() instanceof listDef){
				outputTypes.add(DeclaredList.convert(ref));
			}else if(ref.getRef().getType() instanceof sequenceDef){
				outputTypes.add(DeclaredSequence.convert(ref));
			}else if(ref.getRef().getType() instanceof variantDef){
				outputTypes.add(DeclaredVariant.convert(ref));
			}else if(ref.getRef().getType() instanceof recordDef){
				outputTypes.add(DeclaredRecord.convert(ref));
			}else if(ref.getRef().getType() instanceof arrayDef){
				outputTypes.add(DeclaredArray.convert(ref));
			}else if(ref.getRef().getType() instanceof fixedFieldDef){
				outputTypes.add(DeclaredFixedField.convert(ref));
			}else if(ref.getRef().getType() instanceof bitfieldDef){
				outputTypes.add(DeclaredBitField.convert(ref));
			}else if(ref.getRef().getType() instanceof fixedLenString){
				outputTypes.add(DeclaredFixedLengthString.convert(ref));
			}else if(ref.getRef().getType() instanceof varLenString){
				outputTypes.add(DeclaredVariableLengthString.convert(ref));
			}else if(ref.getRef().getType() instanceof varField){
				outputTypes.add(DeclaredVariableField.convert(ref));
			}else if(ref.getRef().getType() instanceof varLenField){
				outputTypes.add(DeclaredVariableLengthField.convert(ref));
			}else if(ref.getRef().getType() instanceof varFormatField){
				outputTypes.add(DeclaredVariableFormatField.convert(ref));
			} 
		}
		output.setName(input.getTypeName());
		String id = input.getName();
		if(id != null){
			id = id.replace("'", "");
			id = id.replace("\"", "");
			id = id.replace(".", ":");
			output.setId(id);
		}
		String version = input.getVersion();
		if(version != null){
			version = version.replace("'", "");
			version = version.replace("\"", "");
			output.setVersion(version);
		}
		
		
		return output;
	}
	
	/**
	 * Converts a JSIDL declared type set to a CJSIDL type set
	 * @param input - the JSIDL declared type set to convert
	 * @return - the resulting CJSIDL type set
	 */
	public static org.jts.eclipse.cjsidl.declaredTypeSet convert(org.jts.jsidl.binding.DeclaredTypeSet input)
	{
		typeMap = new HashMap<String, EObject>();
		additionalTypes = new HashSet<EObject>();
		unresolvedRefs = new HashMap<EObject, String>();

		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		
		org.jts.eclipse.cjsidl.declaredTypeSet output = factory.createdeclaredTypeSet();
		output.setTypeName(input.getName());
		
		if(input.getId()!= null && !input.getId().isEmpty()){
			String tmpid = input.getId().replace(":", ".");
			tmpid = tmpid.replace("\"", "");
			tmpid = tmpid.replace("'", "");		
			output.setName( input.getId().replace(":", ".") );
		}
		if(input.getVersion() != null && !input.getVersion().isEmpty()){
			String tmpversion = input.getVersion().replace("\"", "");
			tmpversion = tmpversion.replace("'", "");
			
			output.setVersion( input.getVersion());
		}

		// handle typeSetRef
		 java.util.List<org.jts.jsidl.binding.DeclaredTypeSetRef> inputtyperefs =  input.getDeclaredTypeSetRef();
		 EList<org.jts.eclipse.cjsidl.declaredTypeSetRef> outputtyperef = output.getDeclaredTypeSetRef();
		
		for(org.jts.jsidl.binding.DeclaredTypeSetRef tmpref: inputtyperefs)
		{
			outputtyperef.add(DeclaredTypeSetRef.convert(tmpref));
		}
		// handle constSetRef
		java.util.List<org.jts.jsidl.binding.DeclaredConstSetRef> inputconstrefs =  input.getDeclaredConstSetRef();
		EList<org.jts.eclipse.cjsidl.declaredConstSetRef> outputconstref = output.getDeclaredConstSetRef();
		for(org.jts.jsidl.binding.DeclaredConstSetRef tmpref: inputconstrefs){
			outputconstref.add(DeclaredConstSetRef.convert(tmpref));
		}
		
		EList<org.jts.eclipse.cjsidl.typeDef> outputtypedefs = output.getTypeDef();
		EList<org.jts.eclipse.cjsidl.typeReference> outputtyperefs = output.getTypeRef();
		EList<org.jts.eclipse.cjsidl.scopedTypeId> outputscopedrefs = output.getScopedRef();
		
		java.util.List<Object> inputTypes = input.getMessageDefOrHeaderOrBody();
		
		
		for(Object tmpdef: inputTypes){
			org.jts.eclipse.cjsidl.typeDef tmptypedef = factory.createtypeDef();
		
			if(tmpdef instanceof org.jts.jsidl.binding.Header){
				org.jts.eclipse.cjsidl.headerDef field = Header.convert((org.jts.jsidl.binding.Header)tmpdef, output);
				
				tmptypedef.setHeaderDef(field);
				outputtypedefs.add(tmptypedef);
				typeMap.put(field.getName(), field);
			}
			if(tmpdef instanceof org.jts.jsidl.binding.Body){
				org.jts.eclipse.cjsidl.bodyDef field = Body.convert((org.jts.jsidl.binding.Body)tmpdef, output);
				
				tmptypedef.setBodyDef(field);
				outputtypedefs.add(tmptypedef);
				typeMap.put(field.getName(), field);
			}
			if(tmpdef instanceof org.jts.jsidl.binding.Footer){
				org.jts.eclipse.cjsidl.footerDef field = Footer.convert((org.jts.jsidl.binding.Footer)tmpdef, output);
				
				tmptypedef.setFooterDef(field);
				outputtypedefs.add(tmptypedef);
				typeMap.put(field.getName(), field);
			}

			if(tmpdef instanceof org.jts.jsidl.binding.FixedField){
				org.jts.eclipse.cjsidl.fixedFieldDef field = FixedField.convert((org.jts.jsidl.binding.FixedField)tmpdef);
				
				tmptypedef.setFixedFieldDef(field);
				outputtypedefs.add(tmptypedef);
				typeMap.put(field.getName(), field);
			}
			if(tmpdef instanceof org.jts.jsidl.binding.BitField){
				org.jts.eclipse.cjsidl.bitfieldDef bitfield = BitField.convert((org.jts.jsidl.binding.BitField)tmpdef);
				tmptypedef.setBitfieldDef(bitfield);
				outputtypedefs.add(tmptypedef);
				typeMap.put(bitfield.getName(), bitfield);
			}
			if(tmpdef instanceof org.jts.jsidl.binding.MessageDef){
				org.jts.eclipse.cjsidl.messageDef field = MessageDef.convert((org.jts.jsidl.binding.MessageDef)tmpdef, output);
				tmptypedef.setMessageDef(field);
				outputtypedefs.add(tmptypedef);
				typeMap.put(field.getName(), field);
			}
			if(tmpdef instanceof org.jts.jsidl.binding.VariableFormatField){
				org.jts.eclipse.cjsidl.varFormatField field = VariableFormatField.convert((org.jts.jsidl.binding.VariableFormatField)tmpdef);
				tmptypedef.setVarFormatField(field);
				outputtypedefs.add(tmptypedef);
				typeMap.put(field.getName(), field);
			}
			if(tmpdef instanceof org.jts.jsidl.binding.VariableField){
				org.jts.eclipse.cjsidl.varField field = VariableField.convert((org.jts.jsidl.binding.VariableField)tmpdef);
				tmptypedef.setVarField(field);
				outputtypedefs.add(tmptypedef);
				typeMap.put(field.getName(), field);
			}
			if(tmpdef instanceof org.jts.jsidl.binding.Array){
				org.jts.eclipse.cjsidl.arrayDef field = Array.convert((org.jts.jsidl.binding.Array)tmpdef, output);
				tmptypedef.setArrayDef(field);
				outputtypedefs.add(tmptypedef);
				typeMap.put(field.getName(), field);
			}
			if(tmpdef instanceof org.jts.jsidl.binding.FixedLengthString){
				org.jts.eclipse.cjsidl.fixedLenString str = FixedLengthString.convert((org.jts.jsidl.binding.FixedLengthString)tmpdef);
				
				tmptypedef.setFixedLenString(str);
				outputtypedefs.add(tmptypedef);
				typeMap.put(str.getName(), str);
			}
			if(tmpdef instanceof org.jts.jsidl.binding.List){
				org.jts.eclipse.cjsidl.listDef field = List.convert((org.jts.jsidl.binding.List)tmpdef, output);
				tmptypedef.setListDef(field);
				outputtypedefs.add(tmptypedef);
				typeMap.put(field.getName(), field);
			}
			if(tmpdef instanceof org.jts.jsidl.binding.Record){
				org.jts.eclipse.cjsidl.recordDef field = Record.convert((org.jts.jsidl.binding.Record)tmpdef, output);
				tmptypedef.setRecordDef(field);
				outputtypedefs.add(tmptypedef);
				typeMap.put(field.getName(), field);
			}
			if(tmpdef instanceof org.jts.jsidl.binding.Sequence){
				org.jts.eclipse.cjsidl.sequenceDef field = Sequence.convert((org.jts.jsidl.binding.Sequence)tmpdef, output);
				tmptypedef.setSequenceDef(field);
				outputtypedefs.add(tmptypedef);
				typeMap.put(field.getName(), field);
			}
			if(tmpdef instanceof org.jts.jsidl.binding.Variant){
				org.jts.eclipse.cjsidl.variantDef field = Variant.convert((org.jts.jsidl.binding.Variant)tmpdef, output);
				tmptypedef.setVariantDef(field);
				outputtypedefs.add(tmptypedef);
				typeMap.put(field.getName(), field);
			}
			if(tmpdef instanceof org.jts.jsidl.binding.VariableLengthField){
				org.jts.eclipse.cjsidl.varLenField field = VariableLengthField.convert((org.jts.jsidl.binding.VariableLengthField)tmpdef);
				tmptypedef.setVarLenField(field);
				outputtypedefs.add(tmptypedef);
				typeMap.put(field.getName(), field);
			}
			if(tmpdef instanceof org.jts.jsidl.binding.VariableLengthString){
				org.jts.eclipse.cjsidl.varLenString str = VariableLengthString.convert((org.jts.jsidl.binding.VariableLengthString)tmpdef);
				
				tmptypedef.setVarLenString(str);
				outputtypedefs.add(tmptypedef);
				typeMap.put(str.getName(), str);
			}
			
			
			
			
			
			if(tmpdef instanceof org.jts.jsidl.binding.DeclaredMessageDef){
				EObject ref = DeclaredMessageDef.convertToRef((org.jts.jsidl.binding.DeclaredMessageDef)tmpdef, output);
				String name = null;
				if(ref instanceof typeReference){
					outputtyperefs.add((typeReference) ref);
					name = ((typeReference) ref).getName();
				} else if(ref instanceof scopedTypeId){
					outputscopedrefs.add((scopedTypeId) ref);
					name = ((scopedTypeId) ref).getScopedName();
				}
				typeMap.put(name, ref);
			}
			if(tmpdef instanceof org.jts.jsidl.binding.DeclaredEventDef){
				EObject ref = DeclaredEventDef.convertToRef((org.jts.jsidl.binding.DeclaredEventDef)tmpdef, output);
				String name = null;
				if(ref instanceof typeReference){
					outputtyperefs.add((typeReference) ref);
					name = ((typeReference) ref).getName();
				} else if(ref instanceof scopedTypeId){
					outputscopedrefs.add((scopedTypeId) ref);
					name = ((scopedTypeId) ref).getScopedName();
				}
				typeMap.put(name, ref);
			}
			if(tmpdef instanceof org.jts.jsidl.binding.DeclaredHeader){
				EObject ref = DeclaredHeader.convertToRef((org.jts.jsidl.binding.DeclaredHeader)tmpdef, output);
				String name = null;
				if(ref instanceof typeReference){
					outputtyperefs.add((typeReference) ref);
					name = ((typeReference) ref).getName();
				} else if(ref instanceof scopedTypeId){
					outputscopedrefs.add((scopedTypeId) ref);
					name = ((scopedTypeId) ref).getScopedName();
				}
				typeMap.put(name, ref);
			}
			if(tmpdef instanceof org.jts.jsidl.binding.DeclaredBody){
				EObject ref = DeclaredBody.convertToRef((org.jts.jsidl.binding.DeclaredBody)tmpdef, output);
				String name = null;
				if(ref instanceof typeReference){
					outputtyperefs.add((typeReference) ref);
					name = ((typeReference) ref).getName();
				} else if(ref instanceof scopedTypeId){
					outputscopedrefs.add((scopedTypeId) ref);
					name = ((scopedTypeId) ref).getScopedName();
				}
				typeMap.put(name, ref);
			}
			if(tmpdef instanceof org.jts.jsidl.binding.DeclaredFooter){
				EObject ref = DeclaredFooter.convertToRef((org.jts.jsidl.binding.DeclaredFooter)tmpdef, output);
				String name = null;
				if(ref instanceof typeReference){
					outputtyperefs.add((typeReference) ref);
					name = ((typeReference) ref).getName();
				} else if(ref instanceof scopedTypeId){
					outputscopedrefs.add((scopedTypeId) ref);
					name = ((scopedTypeId) ref).getScopedName();
				}
				typeMap.put(name, ref);
			}
			if(tmpdef instanceof org.jts.jsidl.binding.DeclaredList){
				EObject ref = DeclaredList.convertToRef((org.jts.jsidl.binding.DeclaredList)tmpdef, output);
				String name = null;
				if(ref instanceof typeReference){
					outputtyperefs.add((typeReference) ref);
					name = ((typeReference) ref).getName();
				} else if(ref instanceof scopedTypeId){
					outputscopedrefs.add((scopedTypeId) ref);
					name = ((scopedTypeId) ref).getScopedName();
				}
				typeMap.put(name, ref);
			}
			if(tmpdef instanceof org.jts.jsidl.binding.DeclaredSequence){
				EObject ref = DeclaredSequence.convertToRef((org.jts.jsidl.binding.DeclaredSequence)tmpdef, output);
				String name = null;
				if(ref instanceof typeReference){
					outputtyperefs.add((typeReference) ref);
					name = ((typeReference) ref).getName();
				} else if(ref instanceof scopedTypeId){
					outputscopedrefs.add((scopedTypeId) ref);
					name = ((scopedTypeId) ref).getScopedName();
				}
				typeMap.put(name, ref);
			}
			if(tmpdef instanceof org.jts.jsidl.binding.DeclaredVariant){
				EObject ref = DeclaredVariant.convertToRef((org.jts.jsidl.binding.DeclaredVariant)tmpdef, output);
				String name = null;
				if(ref instanceof typeReference){
					outputtyperefs.add((typeReference) ref);
					name = ((typeReference) ref).getName();
				} else if(ref instanceof scopedTypeId){
					outputscopedrefs.add((scopedTypeId) ref);
					name = ((scopedTypeId) ref).getScopedName();
				}
				typeMap.put(name, ref);
			}
			if(tmpdef instanceof org.jts.jsidl.binding.DeclaredRecord){
				EObject ref = DeclaredRecord.convertToRef((org.jts.jsidl.binding.DeclaredRecord)tmpdef, output);
				String name = null;
				if(ref instanceof typeReference){
					outputtyperefs.add((typeReference) ref);
					name = ((typeReference) ref).getName();
				} else if(ref instanceof scopedTypeId){
					outputscopedrefs.add((scopedTypeId) ref);
					name = ((scopedTypeId) ref).getScopedName();
				}
				typeMap.put(name, ref);
			}
			if(tmpdef instanceof org.jts.jsidl.binding.DeclaredArray){
				EObject ref = DeclaredArray.convertToRef((org.jts.jsidl.binding.DeclaredArray)tmpdef, output);
				String name = null;
				if(ref instanceof typeReference){
					outputtyperefs.add((typeReference) ref);
					name = ((typeReference) ref).getName();
				} else if(ref instanceof scopedTypeId){
					outputscopedrefs.add((scopedTypeId) ref);
					name = ((scopedTypeId) ref).getScopedName();
				}
				typeMap.put(name, ref);
			}
			if(tmpdef instanceof org.jts.jsidl.binding.DeclaredFixedField){
				EObject ref = DeclaredFixedField.convertToRef((org.jts.jsidl.binding.DeclaredFixedField)tmpdef, output);
				String name = null;
				if(ref instanceof typeReference){
					outputtyperefs.add((typeReference) ref);
					name = ((typeReference) ref).getName();
				} else if(ref instanceof scopedTypeId){
					outputscopedrefs.add((scopedTypeId) ref);
					name = ((scopedTypeId) ref).getScopedName();
				}
				typeMap.put(name, ref);
			}
			if(tmpdef instanceof org.jts.jsidl.binding.DeclaredVariableField){
				EObject ref = DeclaredVariableField.convertToRef((org.jts.jsidl.binding.DeclaredVariableField)tmpdef, output);
				String name = null;
				if(ref instanceof typeReference){
					outputtyperefs.add((typeReference) ref);
					name = ((typeReference) ref).getName();
				} else if(ref instanceof scopedTypeId){
					outputscopedrefs.add((scopedTypeId) ref);
					name = ((scopedTypeId) ref).getScopedName();
				}
				typeMap.put(name, ref);
			}
			if(tmpdef instanceof org.jts.jsidl.binding.DeclaredBitField){
				EObject ref = DeclaredBitField.convertToRef((org.jts.jsidl.binding.DeclaredBitField)tmpdef, output);
				String name = null;
				if(ref instanceof typeReference){
					outputtyperefs.add((typeReference) ref);
					name = ((typeReference) ref).getName();
				} else if(ref instanceof scopedTypeId){
					outputscopedrefs.add((scopedTypeId) ref);
					name = ((scopedTypeId) ref).getScopedName();
				}
				typeMap.put(name, ref);
			}
			if(tmpdef instanceof org.jts.jsidl.binding.DeclaredFixedLengthString){
				EObject ref = DeclaredFixedLengthString.convertToRef((org.jts.jsidl.binding.DeclaredFixedLengthString)tmpdef, output);
				String name = null;
				if(ref instanceof typeReference){
					outputtyperefs.add((typeReference) ref);
					name = ((typeReference) ref).getName();
				} else if(ref instanceof scopedTypeId){
					outputscopedrefs.add((scopedTypeId) ref);
					name = ((scopedTypeId) ref).getScopedName();
				}
				typeMap.put(name, ref);
			}
			if(tmpdef instanceof org.jts.jsidl.binding.DeclaredVariableLengthString){
				EObject ref = DeclaredVariableLengthString.convertToRef((org.jts.jsidl.binding.DeclaredVariableLengthString)tmpdef, output);
				String name = null;
				if(ref instanceof typeReference){
					outputtyperefs.add((typeReference) ref);
					name = ((typeReference) ref).getName();
				} else if(ref instanceof scopedTypeId){
					outputscopedrefs.add((scopedTypeId) ref);
					name = ((scopedTypeId) ref).getScopedName();
				}
				typeMap.put(name, ref);
			}
			if(tmpdef instanceof org.jts.jsidl.binding.DeclaredVariableLengthField){
				EObject ref = DeclaredVariableLengthField.convertToRef((org.jts.jsidl.binding.DeclaredVariableLengthField)tmpdef, output);
				String name = null;
				if(ref instanceof typeReference){
					outputtyperefs.add((typeReference) ref);
					name = ((typeReference) ref).getName();
				} else if(ref instanceof scopedTypeId){
					outputscopedrefs.add((scopedTypeId) ref);
					name = ((scopedTypeId) ref).getScopedName();
				}
				typeMap.put(name, ref);
			}
			if(tmpdef instanceof org.jts.jsidl.binding.DeclaredVariableFormatField){
				EObject ref = DeclaredVariableFormatField.convertToRef((org.jts.jsidl.binding.DeclaredVariableFormatField)tmpdef, output);
				String name = null;
				if(ref instanceof typeReference){
					outputtyperefs.add((typeReference) ref);
					name = ((typeReference) ref).getName();
				} else if(ref instanceof scopedTypeId){
					outputscopedrefs.add((scopedTypeId) ref);
					name = ((scopedTypeId) ref).getScopedName();
				}
				typeMap.put(name, ref);
			}

		}

		// its possible that some of the typeReferences were not set completely due
		// to it being a reference for a type that was not defined first.
		// Now look for those and resolve the references before moving on.
		Set<EObject> unresRefs = unresolvedRefs.keySet();
		for(EObject tmpObj: unresRefs){
			String typename = unresolvedRefs.get(tmpObj);
			boolean found = false;
			for(String tmpname: typeMap.keySet()){
				if(typename.equals(tmpname)){
					found = true;
					if(tmpObj instanceof typeReference){
						((typeReference)tmpObj).setType(typeMap.get(tmpname));
					} else if(tmpObj instanceof headerRef){
						((headerRef)tmpObj).setTypeRef((headerDef) typeMap.get(tmpname));
					} else if(tmpObj instanceof bodyRef){
						((bodyRef)tmpObj).setTypeRef((bodyDef) typeMap.get(tmpname));
					} else if(tmpObj instanceof footerRef){
						((footerRef)tmpObj).setTypeRef((footerDef) typeMap.get(tmpname));
					} else if(tmpObj instanceof containerRef){
						((containerRef)tmpObj).setType((containerDef) typeMap.get(tmpname));
					} else {
			            Logger.getLogger("CJSIDL").log(Level.SEVERE,
			    	            "Unexpected type reference object type \"" + tmpObj + "\" found and unresolved.");
					}
					
				}
			}
			if(!found){
	            Logger.getLogger("CJSIDL").log(Level.SEVERE,
	    	            "Unexpected type reference \"" + typename + "\" remains unresolved.");
			}
		}
		
		
		// if we had to convert any types to references, then we need to add the 
		// new type defs to the typeSet and then start over on the list.
		addAdditionalTypes(outputtypedefs);

		return output;
	}
	private static void addAdditionalTypes(EList<typeDef> outputtypedefs){
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		if(additionalTypes.size()>0){
			for(EObject tmpobj: additionalTypes){
				org.jts.eclipse.cjsidl.typeDef tmptypedef = factory.createtypeDef();
				if(tmpobj instanceof fixedFieldDef){
					tmptypedef.setFixedFieldDef((fixedFieldDef) tmpobj);
				} else if(tmpobj instanceof bitfieldDef){
					tmptypedef.setBitfieldDef((bitfieldDef) tmpobj);
				} else if(tmpobj instanceof varFormatField){
					tmptypedef.setVarFormatField((varFormatField) tmpobj);
				} else if(tmpobj instanceof varField){
					tmptypedef.setVarField((varField) tmpobj);
				} else if(tmpobj instanceof fixedLenString){
					tmptypedef.setFixedLenString((fixedLenString) tmpobj);
				} else if(tmpobj instanceof listDef){
					tmptypedef.setListDef((listDef) tmpobj);
				} else if(tmpobj instanceof recordDef){
					tmptypedef.setRecordDef((recordDef) tmpobj);
				} else if(tmpobj instanceof sequenceDef){
					tmptypedef.setSequenceDef((sequenceDef) tmpobj);
				} else if(tmpobj instanceof variantDef){
					tmptypedef.setVariantDef((variantDef) tmpobj);
				} else if(tmpobj instanceof varLenField){
					tmptypedef.setVarLenField((varLenField) tmpobj);
				} else if(tmpobj instanceof varLenString){
					tmptypedef.setVarLenString((varLenString) tmpobj);
				}
				
				outputtypedefs.add(tmptypedef);
			}
			additionalTypes.clear();
		}
	}
		
}
