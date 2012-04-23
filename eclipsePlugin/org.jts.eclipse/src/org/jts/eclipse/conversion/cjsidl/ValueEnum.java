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

import org.jts.eclipse.cjsidl.valueSpec;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class ValueEnum extends ConversionUtil{

	/**
	 * converts cjsidl xtext object into jsidl data binding
	 * @param val - input valueSpec
	 * @return - resulting org.jts.jsidl.binding.ValueEnum
	 */
	public static org.jts.jsidl.binding.ValueEnum convert(valueSpec val) {
		org.jts.jsidl.binding.ValueEnum valenum = new org.jts.jsidl.binding.ValueEnum();
		String name = val.getName();
		name = name.replace("\"", "");
		valenum.setEnumConst(name);
		valenum.setEnumIndex(Long.valueOf(val.getValue()));
		valenum.setInterpretation(CJSIDLCommentToJSIDLInterp(val.getComment()));
		return valenum;
	}

	/**
	 * converts jsidl data binding to a cjsidl xtext object
	 * @param val - input org.jts.jsidl.binding.ValueEnum
	 * @return resulting valueSpec
	 */
	public static valueSpec convert(org.jts.jsidl.binding.ValueEnum val) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		
		org.jts.eclipse.cjsidl.valueSpec valenum = factory.createvalueSpec();
		valenum.setName("\"" + val.getEnumConst() + "\"");
		valenum.setValue(Long.toString(val.getEnumIndex()));
		valenum.setComment(JSIDLInterpToCJSIDLComment(val.getInterpretation()));
		return valenum;
	}

}
