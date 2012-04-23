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
import org.eclipse.emf.ecore.EObject;
import org.jts.eclipse.cjsidl.valueRange;
import org.jts.eclipse.cjsidl.valueSetDef;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class ValueSet extends ConversionUtil{

	/**
	 * converts the cjsidl xtext object to a jsidl data binding
	 * @param inputSet - incoming valueSetDef
	 * @return - org.jts.jsidl.binding.ValueSet
	 */
	public static org.jts.jsidl.binding.ValueSet convert(valueSetDef tmpobj) {
		if(tmpobj == null){
			// log problem and throw exception
		}
		org.jts.jsidl.binding.ValueSet set = new org.jts.jsidl.binding.ValueSet();
		java.util.List<Object> setVals = set.getValueRangeOrValueEnum();
		
		EList<EObject> values = tmpobj.getValue();
		for(EObject val: values)
		{
			if(val instanceof org.jts.eclipse.cjsidl.valueSpec){//val.getValue() != null){
				// must be an enum
				org.jts.jsidl.binding.ValueEnum valenum = ValueEnum.convert((org.jts.eclipse.cjsidl.valueSpec)val);
				valenum.setInterpretation(CJSIDLCommentToJSIDLInterp(((org.jts.eclipse.cjsidl.valueSpec)val).getComment()));
				setVals.add(valenum);
			}
			else{
				org.jts.jsidl.binding.ValueRange range = ValueRange.convert((org.jts.eclipse.cjsidl.valueRange)val);
				range.setInterpretation(CJSIDLCommentToJSIDLInterp(((org.jts.eclipse.cjsidl.valueRange)val).getComment()));
				setVals.add(range);
			}
		}
		
		// set the offset_to_lower_limit
		if(tmpobj.getOffset() == null || tmpobj.getOffset().isEmpty()){
			set.setOffsetToLowerLimit(false);
		} else {
			set.setOffsetToLowerLimit(true);
		}
			
		return set;
	}

	/**
	 * converts the cjsidl xtext object to a jsidl data binding
	 * @param inputSet - incoming valueRange
	 * @return - org.jts.jsidl.binding.ValueSet
	 */
	public static org.jts.jsidl.binding.ValueSet convert(valueRange tmpobj) {
		if(tmpobj == null){
			// log problem and throw exception
		}
		
		org.jts.jsidl.binding.ValueSet set = new org.jts.jsidl.binding.ValueSet();
		java.util.List<Object> setVals = set.getValueRangeOrValueEnum();
	
		String low = null;
		if(tmpobj.getLowerLim() != null){
			low = tmpobj.getLowerLim();
		}else if(tmpobj.getLowerLimRef() != null){
			low = tmpobj.getLowerLimRef().getConstVal().getConstValue();
		} else if(tmpobj.getLowerLimScoped() != null){
			low = tmpobj.getLowerLimScoped().getType().getConstValue();
		}
		String high = null;
		if(tmpobj.getUpperLim() != null){
			high = tmpobj.getUpperLim();
		}else if(tmpobj.getUpperLimRef() != null){
			high = tmpobj.getUpperLimRef().getConstVal().getConstValue();
		} else if(tmpobj.getUpperLimScoped() != null){
			high = tmpobj.getUpperLimScoped().getType().getConstValue();
		}

		org.jts.jsidl.binding.ValueRange range = ValueRange.convert(low, tmpobj.getLowerLimit_type(), high, tmpobj.getUpperLimit_type());
		range.setInterpretation(CJSIDLCommentToJSIDLInterp(tmpobj.getComment()));
		
		setVals.add(range);
		
		// since we didn't start with a ValueSet, we don't offset
		set.setOffsetToLowerLimit(false);

		return set;
	}

	/**
	 * converts the jsidl data binding to a cjsidl xtext object
	 * @param inputSet - incoming ValueSet
	 * @return - org.jts.eclipse.cjsidl.valueSetDef
	 */
	public static valueSetDef convert(org.jts.jsidl.binding.ValueSet inputSet) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.valueSetDef valueSet = factory.createvalueSetDef();
		
		EList<EObject> outputList = valueSet.getValue();
		
		java.util.List<Object> inputList = inputSet.getValueRangeOrValueEnum();
		for(Object tmpObj:inputList){
			if(tmpObj instanceof org.jts.jsidl.binding.ValueRange){
				outputList.add(ValueRange.convert((org.jts.jsidl.binding.ValueRange)tmpObj));
			} else if(tmpObj instanceof org.jts.jsidl.binding.ValueEnum){
				outputList.add(ValueEnum.convert((org.jts.jsidl.binding.ValueEnum)tmpObj));
			}
			
		}
		if(inputSet.isOffsetToLowerLimit()){
			valueSet.setOffset("offset");
		}
		// no jsidl interpretation currently available for this element
		//valueSet.setComment(inputSet.getInterpretation());
		return valueSet;
	}


}
