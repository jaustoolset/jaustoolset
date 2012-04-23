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
import org.jts.eclipse.cjsidl.UNIT;
import org.jts.eclipse.cjsidl.simpleNumericType;
import org.jts.eclipse.cjsidl.taggedUnitsEnum;
import org.jts.eclipse.cjsidl.varField;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class VariableField extends ConversionUtil{

	/**
	 * Converts a CJSIDL object to a JSIDL data binding
	 * @param tmpdef - the input CJSIDL object
	 * @return - the resulting JSIDL data binding
	 */
	public static org.jts.jsidl.binding.VariableField convert(
			varField tmpdef) {
		org.jts.jsidl.binding.VariableField vf = new org.jts.jsidl.binding.VariableField();
		
		vf.setInterpretation(CJSIDLCommentToJSIDLInterp(tmpdef.getComment()));
		vf.setName(tmpdef.getName());
		
		if(tmpdef.getOptional() != null){
			vf.setOptional(tmpdef.getOptional().equals(OPTIONAL));
		}
		
		EList<taggedUnitsEnum> enumlist = tmpdef.getVtagField();
		
		org.jts.jsidl.binding.TypeAndUnitsField tauf = new org.jts.jsidl.binding.TypeAndUnitsField();
		java.util.List<org.jts.jsidl.binding.TypeAndUnitsEnum> newEnumList = tauf.getTypeAndUnitsEnum();
		for(taggedUnitsEnum def: enumlist){
			org.jts.jsidl.binding.TypeAndUnitsEnum newenum = new org.jts.jsidl.binding.TypeAndUnitsEnum();
			newenum.setFieldType(CJSIDLTypeToJSIDLType(def.getType().getType()));
			newenum.setFieldUnits(JSIDLUnitFromCJSIDLUnit(def.getFieldUnit().getLiteral()));
			newenum.setName(def.getName());
			if(def.getScaledRangeDef() != null){
				newenum.setScaleRange(ScaleRange.convert(def.getScaledRangeDef()));
			}
			if(def.getValueSetDef() != null){
				newenum.setValueSet(ValueSet.convert(def.getValueSetDef()));
			}
			
			// deal with this reference
			if(def.getConst_tag() != null){
				newenum.setIndex(Short.valueOf(def.getConst_tag()));
			} else if(def.getTag()!=null && def.getTag().getConstVal()!= null){
				newenum.setIndex(Short.valueOf(def.getTag().getConstVal().getConstValue()));
			} else if(def.getScopedTag() != null && def.getScopedTag().getType() != null){
				newenum.setIndex(Short.valueOf(def.getScopedTag().getType().getConstValue()));
			} else{
                Logger.getLogger("CJSIDL").log(Level.SEVERE,
                "VariableField tag evaluates to null.");
			}
			
			
			newEnumList.add(newenum);
		}
		
		vf.setTypeAndUnitsField(tauf);
		
		return vf;
	}
	/**
	 * Converts a JSIDL variable field to CJSIDL
	 * @param tmpdef - a JSIDL variable field
	 * @return - resulting CJSIDL object
	 */
	public static varField convert(org.jts.jsidl.binding.VariableField tmpdef) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		
		org.jts.eclipse.cjsidl.varField vf = factory.createvarField();
		
		vf.setComment(JSIDLInterpToCJSIDLComment(tmpdef.getInterpretation()));
		vf.setName(tmpdef.getName());
		if(tmpdef.isOptional()){
			vf.setOptional(OPTIONAL);
		} 
		
		EList<taggedUnitsEnum> newEnumList =vf.getVtagField();
		java.util.List<org.jts.jsidl.binding.TypeAndUnitsEnum> enumList = tmpdef.getTypeAndUnitsField().getTypeAndUnitsEnum();
		for(org.jts.jsidl.binding.TypeAndUnitsEnum enumitem: enumList){
			taggedUnitsEnum newdef = factory.createtaggedUnitsEnum();
			newdef.setConst_tag(Short.toString(enumitem.getIndex()));
			
			newdef.setFieldUnit(UNIT.get(CJSIDLUnitFromJSIDLUnit(enumitem.getFieldUnits())));
			newdef.setName(enumitem.getName());
			if(enumitem.getScaleRange()!= null){
				newdef.setScaledRangeDef(ScaleRange.convert(enumitem.getScaleRange()));
			}
			simpleNumericType newtype = factory.createsimpleNumericType();
			newtype.setType(JSIDLTypeToCJSIDLType(enumitem.getFieldType()));
			newdef.setType(newtype);
			if(enumitem.getValueSet() != null){
				newdef.setValueSetDef(ValueSet.convert(enumitem.getValueSet()));
			}
			
			newEnumList.add(newdef);
		}
		return vf;
	}

}
