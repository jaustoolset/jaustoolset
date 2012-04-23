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

import org.jts.eclipse.cjsidl.FIELD_FORMAT;
import org.jts.eclipse.cjsidl.formatEnumDef;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class FormatEnum {

	/**
	 * Converts a CJSIDL format enum to a JSIDL object
	 * @param def - input CJSIDL object
	 * @return - resulting JSIDL object
	 */
	public static org.jts.jsidl.binding.FormatEnum convert(formatEnumDef def) {
		org.jts.jsidl.binding.FormatEnum newenum = new org.jts.jsidl.binding.FormatEnum();
		if(def.getFieldFormatStr() != null && !def.getFieldFormatStr().isEmpty()){
			newenum.setFieldFormat(def.getFieldFormatStr());			
		} else {
			newenum.setFieldFormat(def.getFieldFormat().getLiteral());
		}
		if(def.getIndex() != null){
			newenum.setIndex(Short.valueOf(def.getIndex()));
		} else if(def.getConstRef() != null){
			newenum.setIndex(Short.valueOf(def.getConstRef().getConstVal().getConstValue()));
		} else if(def.getConstScopedRef() != null){
			newenum.setIndex(Short.valueOf(def.getConstScopedRef().getType().getConstValue()));
		}
		
		return newenum;
	}

	/**
	 * converts a jsidl binding to cjsidl object
	 * @param item - the input format enum
	 * @return - the resulting object
	 */
	public static formatEnumDef convert(org.jts.jsidl.binding.FormatEnum item) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.formatEnumDef newenum = factory.createformatEnumDef();

		String format = item.getFieldFormat();
		if(format.startsWith("\"") || format.startsWith("'")){
			newenum.setFieldFormatStr(format);
		} else {
			// this should be removed if JSIDL removes spaces from formats 
			format = format.replace(" ", "_");
			newenum.setFieldFormat(FIELD_FORMAT.get(format));
		}
		
		newenum.setIndex(Short.toString(item.getIndex()));
		
		return newenum;
	}

}
