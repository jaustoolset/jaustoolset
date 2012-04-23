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
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class ValueRange extends ConversionUtil{

	/**
	 * converts a cjsidl xtext object to a jsidl data binding
	 * @param valueRange - incoming valueRange
	 * @return org.jts.jsidl.binding.ValueRange
	 */
	public static org.jts.jsidl.binding.ValueRange convert(valueRange valueRange) {
		String type = "inclusive";
		
		org.jts.jsidl.binding.ValueRange vr = new org.jts.jsidl.binding.ValueRange();
		vr.setLowerLimit(valueRange.getLowerLim());
		if(valueRange.getLowerLimit_type().equals("(")){
			type = "exclusive";
		}
		vr.setLowerLimitType(type);
		vr.setUpperLimit(valueRange.getUpperLim());
		type = "inclusive";
		if(valueRange.getUpperLimit_type().equals(")")){
			type = "exclusive";
		}
		
		vr.setUpperLimitType(type);
		
		
		return vr;
	}
	/**
	 * converts a jsidl data binding to a cjsidl xtext object
	 * @param valueRange - incoming org.jts.jsidl.binding.ValueRange
	 * @return resulting valueRange
	 */
	public static valueRange convert(org.jts.jsidl.binding.ValueRange valueRange) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		String type = "[";

		org.jts.eclipse.cjsidl.valueRange vr = factory.createvalueRange();
		
		vr.setLowerLim(valueRange.getLowerLimit());
		
		if(valueRange.getLowerLimitType().equals("exclusive")){
			type = "(";
		}
		vr.setLowerLimit_type(type);
		
		vr.setUpperLim(valueRange.getUpperLimit());
		type = "]";
		if(valueRange.getUpperLimitType().equals("exclusive")){
			type = ")";
		}
		
		vr.setUpperLimit_type(type);
		
		return vr;
	}
	
	/**
	 * used to convert raw data into a jsidl data binding
	 * @param lowerLimit - lower limit for the range
	 * @param lowerLimit_type - inclusive or exclusive
	 * @param upperLimit - upper limit for the range
	 * @param upperLimit_type - inclusive or exclusive
	 * @return - resulting org.jts.jsidl.binding.ValueRange
	 */
	public static org.jts.jsidl.binding.ValueRange convert(String lowerLimit,
			String lowerLimit_type, String upperLimit, String upperLimit_type) {
		
		String type = "inclusive";
		
		org.jts.jsidl.binding.ValueRange vr = new org.jts.jsidl.binding.ValueRange();
		vr.setLowerLimit(lowerLimit);
		if(lowerLimit_type.equals("(")){
			type = "exclusive";
		}
		vr.setLowerLimitType(type);
		
		vr.setUpperLimit(upperLimit);
		type = "inclusive";
		if(upperLimit_type.equals(")")){
			type = "exclusive";
		}
		
		vr.setUpperLimitType(type);
		
		
		return vr;
	}
	/**
	 * converts binding to cjsidl object, starting with a value_set
	 * @param valueSet - the input valueset containing a valuerange
	 * @return - cjsidl valueRange object
	 */
	public static valueRange convert(org.jts.jsidl.binding.ValueSet valueSet) {
		org.jts.eclipse.cjsidl.valueRange vr = null;
		// there should be only one
		if(valueSet.getValueRangeOrValueEnum().get(0) instanceof org.jts.jsidl.binding.ValueEnum){
			return null;
		} else{
			org.jts.jsidl.binding.ValueRange inputrange = (org.jts.jsidl.binding.ValueRange) valueSet.getValueRangeOrValueEnum().get(0);
			vr = ValueRange.convert(inputrange);
		}
		return vr;
	}
	/**
	 * Special case for use with variableFormatField
	 * @param minCount - minimum value
	 * @param maxCount - maximum value
	 * @return - resulting cjsidl object
	 */
	public static valueRange convert(String minCount, String maxCount) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.valueRange vr = factory.createvalueRange();

		vr.setLowerLim(minCount);
		vr.setUpperLim(maxCount);
		vr.setLowerLimit_type("[");
		vr.setUpperLimit_type("]");
		
		return vr;
	}

}
