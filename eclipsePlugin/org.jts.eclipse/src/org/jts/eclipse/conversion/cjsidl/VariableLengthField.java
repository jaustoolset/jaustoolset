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

import org.jts.eclipse.cjsidl.varLenField;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class VariableLengthField extends ConversionUtil{

	/**
	 * Convert CJSIDL variable length field into JSIDL type 
	 * @param tmpobj - input CJSIDL type
	 * @return - the resulting jsidl type
	 */
	public static org.jts.jsidl.binding.VariableLengthField convert(varLenField tmpobj){
		org.jts.jsidl.binding.VariableLengthField newfield = new org.jts.jsidl.binding.VariableLengthField();
		
		newfield.setName(tmpobj.getName());
		org.jts.eclipse.cjsidl.FIELD_FORMAT format = tmpobj.getFieldFormat();
		newfield.setFieldFormat(format.getLiteral());
		
		newfield.setInterpretation(CJSIDLCommentToJSIDLInterp(tmpobj.getComment()));
		
		
		if(tmpobj.getOptional() != null){
			newfield.setOptional(tmpobj.getOptional().equals(OPTIONAL));
		}
		// set limits.  use values since JSIDL can't do references here.
		String min = "0";
		String max = "0";
		// since JSIDL doesn't handle const references here, just set the value
		if(tmpobj.getUpperLim() != null){
			max = tmpobj.getUpperLim();
		} else if(tmpobj.getUpperLimRef() != null){
			max = tmpobj.getUpperLimRef().getConstVal().getConstValue();
		} else if(tmpobj.getUpperLimScoped() != null){
			min = tmpobj.getUpperLimScoped().getType().getConstValue();
		}
		if(tmpobj.getLowerLim() != null){
			min = tmpobj.getLowerLim();
		} else if(tmpobj.getLowerLimRef() != null ){
			min = tmpobj.getLowerLimRef().getConstVal().getConstValue();
		} else if(tmpobj.getLowerLimScoped() != null){
			min = tmpobj.getLowerLimScoped().getType().getConstValue();
		}

		org.jts.jsidl.binding.CountField cf = new org.jts.jsidl.binding.CountField();
		cf.setInterpretation(CJSIDLCommentToJSIDLInterp(tmpobj.getCountComment()));
		// calculate type since cjsidl doesn't allow type input here
		cf.setFieldTypeUnsigned(rangeToJSIDLType(Long.valueOf(min), Long.valueOf(max)));
		cf.setMinCount(min);
		cf.setMaxCount(max);
		
		newfield.setCountField(cf);
				
		return newfield;
	}
	/**
	 * Convert JSIDL type to CJSIDL type
	 * @param tmpobj - the input variable length field
	 * @return - the resulting CJSIDL type
	 */
	public static varLenField convert(org.jts.jsidl.binding.VariableLengthField tmpobj) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		
		org.jts.eclipse.cjsidl.varLenField vf = factory.createvarLenField();
		
		if(tmpobj.isOptional()){
			vf.setOptional(OPTIONAL);
		}
		
		vf.setName(tmpobj.getName());
		vf.setComment(JSIDLInterpToCJSIDLComment(tmpobj.getInterpretation()));
		
		// CJSIDL format strings are the same as JSIDL, except with underscore in place of spaces
		String format = tmpobj.getFieldFormat();
		format = format.replace(" ", "_");
		vf.setFieldFormat(org.jts.eclipse.cjsidl.FIELD_FORMAT.get(format));
		// set other fields	
		
		vf.setCountComment(JSIDLInterpToCJSIDLComment(tmpobj.getCountField().getInterpretation()));
		
		String upperlim = tmpobj.getCountField().getMaxCount();
		vf.setUpperLim(upperlim);
		String lowerlim = tmpobj.getCountField().getMinCount();
		vf.setLowerLim(lowerlim);
		
		// references are not allowed in JSIDL, so these don't need to be resolved to references here
//		vf.setLowerLimRef(value)
//		vf.setUpperLimRef(value)
		
		return vf;
	}

}
