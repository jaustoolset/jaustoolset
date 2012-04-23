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
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class FixedField extends ConversionUtil{

	/**
	 * converts a cjsidl xtext object to a jsidl data binding
	 * @param fieldDef - input fixedFieldDef
	 * @return - resulting org.jts.jsidl.binding.FixedField
	 */
	public static org.jts.jsidl.binding.FixedField convert(fixedFieldDef fieldDef) {
		org.jts.jsidl.binding.FixedField fixedField = new org.jts.jsidl.binding.FixedField();
		
		// set the comments
		fixedField.setInterpretation(CJSIDLCommentToJSIDLInterp(fieldDef.getComment()));
		
		// set other fields
		org.jts.eclipse.cjsidl.UNIT units = fieldDef.getFieldUnit();
		fixedField.setFieldUnits(JSIDLUnitFromCJSIDLUnit(units.getLiteral()));
		fixedField.setName(fieldDef.getName());
		fixedField.setFieldType(CJSIDLTypeToJSIDLType(fieldDef.getType().getType()));
		
		//valueRangeSet | scaledRangeDef  | valueSetDef
		EObject tmpobj = fieldDef.getValueRange();
		if(tmpobj != null){
			if(tmpobj instanceof org.jts.eclipse.cjsidl.valueRange){
				fixedField.setValueSet(ValueSet.convert((org.jts.eclipse.cjsidl.valueRange)tmpobj));
			} else if(tmpobj instanceof org.jts.eclipse.cjsidl.scaledRangeDef){
				fixedField.setScaleRange(ScaleRange.convert((org.jts.eclipse.cjsidl.scaledRangeDef)tmpobj));			
			}else if(tmpobj instanceof org.jts.eclipse.cjsidl.valueSetDef){
				fixedField.setValueSet(ValueSet.convert((org.jts.eclipse.cjsidl.valueSetDef)tmpobj));
			}
		}
		
		// if one was specified then set it to whatever it was. default is "required"
		if(fieldDef.getOptional() != null){
			fixedField.setOptional(fieldDef.getOptional().equals(OPTIONAL));
		}

		return fixedField;
	}

	/**
	 * converts a jsidl data binding to a cjsidl xtext object
	 * @param fieldDef - input data binding
	 * @return - resulting org.jts.eclipse.cjsidl.fixedFieldDef
	 */
	public static fixedFieldDef convert(org.jts.jsidl.binding.FixedField fieldDef) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		
		org.jts.eclipse.cjsidl.fixedFieldDef fixedField = factory.createfixedFieldDef();
		
		
		// set the comments
		fixedField.setComment(JSIDLInterpToCJSIDLComment(fieldDef.getInterpretation()));
		
		// set other fields
		String units = fieldDef.getFieldUnits();
		fixedField.setFieldUnit(org.jts.eclipse.cjsidl.UNIT.get(CJSIDLUnitFromJSIDLUnit(units)));
		
		fixedField.setName(fieldDef.getName());
		org.jts.eclipse.cjsidl.simpleNumericType simpleType = factory.createsimpleNumericType(); 
		simpleType.setType(JSIDLTypeToCJSIDLType(fieldDef.getFieldType()));
		
		fixedField.setType(simpleType);
		
		
		if(fieldDef.getScaleRange() != null){
			org.jts.eclipse.cjsidl.scaledRangeDef scaledRange = ScaleRange.convert(fieldDef.getScaleRange());
			// can be a scaledRangeDef, valueSetDef, or valueRange
			fixedField.setValueRange(((EObject)scaledRange));
		} else if(fieldDef.getValueSet() != null){
			// returns ValueRange or ValueEnum
			Object valSetObj = ValueSet.convert(fieldDef.getValueSet());
			// can be a scaledRangeDef, valueSetDef, or valueRange
			fixedField.setValueRange(((EObject)valSetObj));
		}
		if(fieldDef.isOptional()){
			fixedField.setOptional(OPTIONAL);
		} 
		return fixedField;
	}





}
