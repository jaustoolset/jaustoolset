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

import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class ScaleRange extends ConversionUtil{

	/**
	 * Converts a CJSIDL scale range to JSIDL
	 * @param range - a CJSIDL scale range
	 * @return - resulting JSIDL scale range
	 */
	public static org.jts.jsidl.binding.ScaleRange convert(org.jts.eclipse.cjsidl.scaledRangeDef range) {
		
		org.jts.jsidl.binding.ScaleRange newrange = new org.jts.jsidl.binding.ScaleRange();
		newrange.setIntegerFunction("");

		
		String low = null;
		if(range.getLowerLim() != null){
			low = range.getLowerLim();
		}else if(range.getLowerLimRef() != null){
			low = range.getLowerLimRef().getConstVal().getConstValue();
		} else if(range.getLowerLimScoped() != null){
			low = range.getLowerLimScoped().getType().getConstValue();
		}
		String high = null;
		if(range.getUpperLim() != null){
			high = range.getUpperLim();
		}else if(range.getUpperLimRef() != null){
			high = range.getUpperLimRef().getConstVal().getConstValue();
		} else if(range.getUpperLimScoped() != null){
			high = range.getUpperLimScoped().getType().getConstValue();
		}
		
		newrange.setRealLowerLimit(low);
		
		newrange.setRealUpperLimit(high);
		
		newrange.setIntegerFunction(range.getFunction());
		newrange.setInterpretation(CJSIDLCommentToJSIDLInterp(range.getInterp()));

		
		return newrange;
	}

	/**
	 * Converts a JSIDL scale range to CJSIDL
	 * @param scaleRange - a JSIDL scale range object
	 * @return - resulting CJSIDL scale range object
	 */
	public static org.jts.eclipse.cjsidl.scaledRangeDef convert(org.jts.jsidl.binding.ScaleRange scaleRange) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.scaledRangeDef newRange = factory.createscaledRangeDef();
		
		// set upper limit
		newRange.setUpperLim(scaleRange.getRealUpperLimit());
		// set lower limit
		newRange.setLowerLim(scaleRange.getRealLowerLimit());
		
		// set function
		newRange.setFunction(scaleRange.getIntegerFunction());

		newRange.setInterp(JSIDLInterpToCJSIDLComment(scaleRange.getInterpretation()));
		
		return newRange;		
	}

}
