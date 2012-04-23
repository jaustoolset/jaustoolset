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

import org.jts.eclipse.cjsidl.valueRange;

/**
 * @author cmessmer
 *
 */
public class CountField extends ConversionUtil{

	/**
	 * Converts CJSIDL count fields to JSIDL count field
	 * @param countComment - the input comment for the count field
	 * @param cjsidlUnits - the units for the count range
	 * @param countRange - the input value range
	 * @return - the corresponding JSIDL binding 
	 */
	public static org.jts.jsidl.binding.CountField convert(String countComment,
			String cjsidlUnits, valueRange countRange) {
		org.jts.jsidl.binding.CountField field = new org.jts.jsidl.binding.CountField();
		String lowerlim = null;
		String upperlim = null;
		if(countRange.getLowerLim() != null){
			lowerlim = (countRange.getLowerLim());
		} else if (countRange.getLowerLimRef() != null){
			lowerlim = (countRange.getLowerLimRef().getConstVal().getConstValue());
		} else if(countRange.getLowerLimScoped() != null){
			lowerlim = (countRange.getLowerLimScoped().getType().getConstValue());
		}
		
		if(countRange.getUpperLim() != null){
			upperlim = (countRange.getUpperLim());
		} else if(countRange.getUpperLimRef() != null){
			upperlim = (countRange.getUpperLimRef().getConstVal().getConstValue());
		} else if(countRange.getUpperLimScoped() != null){
			upperlim = (countRange.getUpperLimScoped().getType().getConstValue());
		}
		
		if(cjsidlUnits == null || cjsidlUnits.isEmpty()){
			field.setFieldTypeUnsigned(rangeToJSIDLType(Long.valueOf(lowerlim), Long.valueOf(upperlim)));
		} else {
			field.setFieldTypeUnsigned(CJSIDLTypeToJSIDLType(cjsidlUnits));
		}
		
		field.setInterpretation(CJSIDLCommentToJSIDLInterp(countComment));
		
		field.setMaxCount(upperlim);
		field.setMinCount(lowerlim);
		
		
		return field;
	}
	
	/**
	 * Converts CJSIDL count data to a JSIDL count field
	 * @param countComment - comments associated with the count
	 * @param cjsidlUnits - the units for the count data
	 * @param min - minimum value in the count field
	 * @param max - maximum value in the count field
	 * @return - the corresponding JSIDL count field.
	 */
	public static org.jts.jsidl.binding.CountField convert(String countComment,
			String cjsidlUnits, String min, String max) {
		org.jts.jsidl.binding.CountField field = new org.jts.jsidl.binding.CountField();
		String lowerlim = min;
		String upperlim = max;

		if(cjsidlUnits == null || cjsidlUnits.isEmpty()){
			field.setFieldTypeUnsigned(rangeToJSIDLType(Long.valueOf(lowerlim), Long.valueOf(upperlim)));
		} else {
			field.setFieldTypeUnsigned(CJSIDLTypeToJSIDLType(cjsidlUnits));
		}
		
		field.setInterpretation(CJSIDLCommentToJSIDLInterp(countComment));
		field.setMaxCount(max);
		field.setMinCount(min);
		
		return field;
	}
}
