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

import org.jts.eclipse.cjsidl.constDef;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class ConstDef extends ConversionUtil{
	/**
	 * Converts a constant definition to a JSIDL binding 
	 * @param def - input constant
	 * @return - resulting JSIDL data binding
	 */
	public static org.jts.jsidl.binding.ConstDef convert(constDef def) {
		org.jts.jsidl.binding.ConstDef newdef = new org.jts.jsidl.binding.ConstDef();
		
		newdef.setConstType(CJSIDLTypeToJSIDLType(def.getConstType().getType()));
		newdef.setConstValue(def.getConstValue());
		org.jts.eclipse.cjsidl.UNIT units = def.getFieldUnits();
		newdef.setFieldUnits(JSIDLUnitFromCJSIDLUnit(units.getLiteral()));
		newdef.setInterpretation(CJSIDLCommentToJSIDLInterp(def.getComment()));
		newdef.setName(def.getName());
		
		return newdef;
	}

	/**
	 * Converts a JSIDL constant to a CJSIDL object
	 * @param def - input constant definition
	 * @return - resulting CJSIDL object
	 */
	public static constDef convert(org.jts.jsidl.binding.ConstDef def) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();		
		org.jts.eclipse.cjsidl.constDef newdef = factory.createconstDef();
		
		newdef.setName(def.getName());
		newdef.setComment(JSIDLInterpToCJSIDLComment(def.getInterpretation()));
		
		// set other fields
		String units = def.getFieldUnits();
		newdef.setFieldUnits(org.jts.eclipse.cjsidl.UNIT.get(CJSIDLUnitFromJSIDLUnit(units)));

		org.jts.eclipse.cjsidl.simpleNumericType simpleType = factory.createsimpleNumericType(); 
		simpleType.setType(JSIDLTypeToCJSIDLType(def.getConstType()));
		
		newdef.setConstType(simpleType);

		newdef.setConstValue(def.getConstValue());
		
		return newdef;
	}

}
