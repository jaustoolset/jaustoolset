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

import org.eclipse.emf.common.util.EList;
import org.jts.eclipse.cjsidl.formatEnumDef;
import org.jts.eclipse.cjsidl.varFormatField;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class VariableFormatField extends ConversionUtil{

	/**
	 * Converts from CJSIDL variable format field type to JSIDL
	 * @param tmpdef - the input CJSIDL type
	 * @return - the resulting JSIDL data binding
	 */
	public static org.jts.jsidl.binding.VariableFormatField convert(
			varFormatField tmpdef) {
		org.jts.jsidl.binding.VariableFormatField vff = new org.jts.jsidl.binding.VariableFormatField();

		vff.setInterpretation(CJSIDLCommentToJSIDLInterp(tmpdef.getComment()));
		vff.setName(tmpdef.getName());
		
		if(tmpdef.getOptional() != null){
			vff.setOptional(tmpdef.getOptional().equals(OPTIONAL));
		}
		
		vff.setCountField(CountField.convert(tmpdef.getCountComment(), tmpdef.getUnits(), tmpdef.getCountRange()));
		vff.setFormatField(FormatField.convert(tmpdef.getFormatField()));
		return vff;
	}

	/**
	 * Converts from a JSIDL data binding to a CJSIDL object
	 * @param tmpdef - the input JSIDL data binding
	 * @return - the resulting CJSIDL object
	 */
	public static varFormatField convert(org.jts.jsidl.binding.VariableFormatField tmpdef) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.varFormatField newfield = factory.createvarFormatField();
		
		newfield.setComment(JSIDLInterpToCJSIDLComment(tmpdef.getInterpretation()));
		newfield.setName(tmpdef.getName());

		if(tmpdef.isOptional()){
			newfield.setOptional(OPTIONAL);
		} 
		
		// no way to do this nicely, so this will have to do for the count field
		newfield.setCountComment(JSIDLInterpToCJSIDLComment(tmpdef.getCountField().getInterpretation()));
		String min = getCountMin(tmpdef.getCountField().getMinCount(), tmpdef.getCountField().getFieldTypeUnsigned());
			
		String max = getCountMax(tmpdef.getCountField().getMaxCount(), tmpdef.getCountField().getFieldTypeUnsigned());

		newfield.setCountRange(ValueRange.convert(min, max));
		newfield.setUnits(JSIDLTypeToCJSIDLType(tmpdef.getCountField().getFieldTypeUnsigned()));

		
		EList<formatEnumDef> newlist = newfield.getFormatField();
		
		org.jts.jsidl.binding.FormatField ff = tmpdef.getFormatField();
		java.util.List<org.jts.jsidl.binding.FormatEnum> enumlist = ff.getFormatEnum();
		for(org.jts.jsidl.binding.FormatEnum item: enumlist){
			formatEnumDef newdef = FormatEnum.convert(item);
			newlist.add(newdef);
		}
		
		return newfield;
	}

}
