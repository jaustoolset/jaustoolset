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

import org.jts.eclipse.cjsidl.subField;
import org.jts.eclipse.cjsidl.valueSpec;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class SubField extends ConversionUtil{

	/**
	 * Converts a subfield from CJSIDL to JSIDL
	 * @param sub - the subfield to convert
	 * @return - resulting JSIDL subfield
	 */
	public static org.jts.jsidl.binding.SubField convert(subField sub) {
		
		org.jts.jsidl.binding.SubField newsub = new org.jts.jsidl.binding.SubField();
		newsub.setInterpretation(CJSIDLCommentToJSIDLInterp(sub.getComment()));
		newsub.setName(sub.getName());
		
		newsub.setBitRange(BitRange.convert(sub.getFromIndex(), 
				sub.getToIndex()));
		if(sub.getValueSet() != null){
			newsub.setValueSet(ValueSet.convert(sub.getValueSet()));
		}
		
		return newsub;
	}

	/**
	 * Converts a JSIDL subfield to CJSIDL
	 * @param sub - a JSIDL subfield
	 * @return - resulting CJSIDL object
	 */
	public static subField convert(org.jts.jsidl.binding.SubField sub) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.subField newsub = factory.createsubField();

		newsub.setName(sub.getName());
		newsub.setComment(JSIDLInterpToCJSIDLComment(sub.getInterpretation()));
		
		newsub.setValueSet(ValueSet.convert(sub.getValueSet()));
		newsub.setFromIndex(Short.toString(sub.getBitRange().getFromIndex()));
		newsub.setToIndex(Short.toString(sub.getBitRange().getToIndex()));
		//newsub.setRangeID(value);
		
		return newsub;
	}

}
