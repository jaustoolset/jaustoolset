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

import org.jts.eclipse.cjsidl.varLenString;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class VariableLengthString extends ConversionUtil{

	/**
	 * converts a cjsidl xtext object to a jsidl data binding
	 * @param tmpobj incoming varLenString
	 * @return - resulting org.jts.jsidl.binding.VariableLengthString
	 */
	public static org.jts.jsidl.binding.VariableLengthString convert(
			varLenString tmpobj) {
		if(tmpobj == null | (tmpobj.getUpperLim() == null && 
				(tmpobj.getUpperLimRef() == null || tmpobj.getUpperLimRef().getConstVal().getConstValue()==null)))
		{
			// log problem and throw exception
		}
		org.jts.jsidl.binding.VariableLengthString str = new org.jts.jsidl.binding.VariableLengthString();
		str.setName(tmpobj.getName());
		str.setInterpretation(CJSIDLCommentToJSIDLInterp(tmpobj.getComment()));
		
		if(tmpobj.getOptional() != null){
			str.setOptional(tmpobj.getOptional().equals(OPTIONAL));
		}

		// set limits.  get values from references since JSIDL can't do references here
		String upperlim = null;
		if(tmpobj.getUpperLim() != null){
			upperlim = tmpobj.getUpperLim();
		}else if(tmpobj.getLowerLimRef() != null){
			upperlim = tmpobj.getUpperLimRef().getConstVal().getConstValue();
		} else if(tmpobj.getLowerLimScoped() != null){
			upperlim = tmpobj.getUpperLimScoped().getType().getConstValue();
		}

		String lowerlim = null;
		if(tmpobj.getLowerLim() != null){
			lowerlim = tmpobj.getLowerLim();
		} else if(tmpobj.getLowerLimRef() != null){
			lowerlim = tmpobj.getLowerLimRef().getConstVal().getConstValue();
		} else if(tmpobj.getLowerLimScoped() != null){
			lowerlim = tmpobj.getLowerLimScoped().getType().getConstValue();
		}

		org.jts.jsidl.binding.CountField countField= new org.jts.jsidl.binding.CountField();
		// calculate the range type, since we aren't keeping the type in text
		countField.setFieldTypeUnsigned(rangeToJSIDLType(Long.valueOf(lowerlim), Long.valueOf(upperlim)));
		countField.setMinCount(lowerlim);
		countField.setMaxCount(upperlim);
		str.setCountField(countField);
	
		return str;
	}

	/**
	 * converts a jsidl data binding to a cjsidl xtext object
	 * @param tmpobj - input jsidl binding
	 * @return - resulting varLenString
	 */
	public static varLenString convert(
			org.jts.jsidl.binding.VariableLengthString tmpobj) {
		if(tmpobj == null | tmpobj.getCountField().getMaxCount() == null)
		{
			// log problem and throw exception
		}
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		
		org.jts.eclipse.cjsidl.varLenString str = factory.createvarLenString();

		str.setName(tmpobj.getName());
		str.setComment((JSIDLInterpToCJSIDLComment(tmpobj.getInterpretation())));
		
		if(tmpobj.isOptional()){
			str.setOptional(OPTIONAL);
		}
		
		String upperlim = getCountMax(tmpobj.getCountField().getMaxCount(), tmpobj.getCountField().getFieldTypeUnsigned());
		str.setUpperLim(upperlim);
		String lowerlim = getCountMin(tmpobj.getCountField().getMinCount(), tmpobj.getCountField().getFieldTypeUnsigned());
		str.setLowerLim(lowerlim);

		return str;	
	}
}
